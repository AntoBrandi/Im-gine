package com.example.im_gine.ui.MainActivity.fragments.login;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import com.example.im_gine.R;
import com.example.im_gine.SignupActivity;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.firestore.FirebaseFirestore;
import custom_font.MyTextView;
import model.ResultActivity;
import model.User;
import static com.facebook.FacebookSdk.getApplicationContext;

public class LoginFragment extends Fragment {

    // UI variables
    private LoginViewModel loginViewModel;
    private ProgressDialog pd;
    private TextView appName;
    private CardView loginCardView;
    private CardView registerCardView;
    private EditText login_email;
    private EditText login_password;
    private MyTextView login_loginBtn;
    private TextView login_registerBtn;
    private TextView login_passwordLost;
    // register CardView
    private EditText register_email;
    private EditText register_password;
    private EditText register_password_confirmation;
    private MyTextView register_registerBtn;
    private TextView register_loginBtn;
    private LoginButton facebookLoginButton;
    private SignInButton googleLoginButton;
    private ImageButton facebookButton;
    private ImageButton googleButton;


    // Application variables
    // login CardView
    private String mail;
    private String pass;
    // register CardView
    private String register_mail;
    private String register_pass;
    private String register_pass_confirm;
    private final String DATABASE_COLLECTION = "users";

    // Firebase Variables
    private FirebaseAuth auth;
    private FirebaseUser firebaseUser;
    private FirebaseFirestore db;

    // social login
    private CallbackManager callbackManager;
    private GoogleSignInClient googleSignInClient;
    private static final int RC_GOOGLE_SIGNIN = 1;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        loginCardView = (CardView) view.findViewById(R.id.login_cardView);
        registerCardView = (CardView) view.findViewById(R.id.register_cardView);
        appName = (TextView) view.findViewById(R.id.title_textView);
        // elements in the Login CardView
        login_email = (EditText)view.findViewById(R.id.email);
        login_password = (EditText)view.findViewById(R.id.password);
        login_loginBtn = (MyTextView)view.findViewById(R.id.loginBtn);
        login_registerBtn = (TextView)view.findViewById(R.id.signupBtn);
        login_passwordLost = (TextView)view.findViewById(R.id.password_lost);
        // elements in the Register CardVidew
        register_email = (EditText) view.findViewById(R.id.register_email);
        register_password = (EditText) view.findViewById(R.id.register_password);
        register_password_confirmation = (EditText) view.findViewById(R.id.register_password_confirmation);
        register_registerBtn = (MyTextView) view.findViewById(R.id.registerBtn);
        register_loginBtn = (TextView) view.findViewById(R.id.signinBtn);
        facebookLoginButton = (LoginButton) view.findViewById(R.id.facebook_login_button);
        facebookButton = (ImageButton) view.findViewById(R.id.facebook_button);
        googleLoginButton = (SignInButton) view.findViewById(R.id.google_login_button);
        googleButton = (ImageButton) view.findViewById(R.id.google_button);


        // UI related settings
        Typeface custom_fonts = Typeface.createFromAsset(getActivity().getAssets(), "fonts/ArgonPERSONAL-Regular.otf");
        appName.setTypeface(custom_fonts);
        pd = new ProgressDialog(getContext());


        // Firebase variables
        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();


        // listener for the password edit Text
        login_password.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if(actionId == EditorInfo.IME_ACTION_SEND){
                    login();
                    handled = true;
                }
                return handled;
            }
        });
        register_password_confirmation.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if(actionId == EditorInfo.IME_ACTION_SEND){
                    register();
                    handled = true;
                }
                return handled;
            }
        });

        // listener for the login button
        login_loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
        register_loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginCardView.setVisibility(View.VISIBLE);
                registerCardView.setVisibility(View.GONE);
            }
        });

        // listener for the register button
        login_registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerCardView.setVisibility(View.VISIBLE);
                loginCardView.setVisibility(View.GONE);
            }
        });
        register_registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });


        // listener for the password lost button
        login_passwordLost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: open page for the password reset
            }
        });

        // listener for the authStatus variable
        final NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
        final View root = view;
        loginViewModel.authStatus.observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isAuth) {
                if(isAuth){
                    navController.navigate(R.id.navigation_profile);
                }
                else{
                    if(!loginViewModel.isFirstTime.getValue()){
                        Snackbar.make(root, R.string.invalid_credentials, Snackbar.LENGTH_SHORT).show();
                    }
                }
            }
        });

        // facebook login
        FacebookSdk.sdkInitialize(getActivity());
        AppEventsLogger.activateApp(getActivity());
        callbackManager = CallbackManager.Factory.create();
        facebookLoginButton.setReadPermissions("email","public_profile");
        facebookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                facebookLoginButton.performClick();
            }
        });
        facebookLoginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                pd.show();
                handleFacebookToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                Toast.makeText(getContext(), getString(R.string.error_generic),Toast.LENGTH_SHORT).show();
                loginViewModel.authenticationFailed();
            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(getContext(), getString(R.string.error_generic),Toast.LENGTH_SHORT).show();
                loginViewModel.authenticationFailed();
            }
        });

        // google login
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        googleSignInClient = GoogleSignIn.getClient(getContext(), gso);
        googleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("BOSCH","clicked");
                Intent signInIntent = googleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent, RC_GOOGLE_SIGNIN);
            }
        });

        return view;
    }


    private void login(){
        // get username and password
        mail = login_email.getText().toString().toLowerCase();
        pass = login_password.getText().toString();
        // check if the user inserted something in the username and password fields
        if (mail.equals("") || pass.equals("")){
            if(mail.equals("") ){
                login_email.setError(getString(R.string.blank_username));
            }
            if (pass.equals("")){
                login_password.setError(getString(R.string.blank_password));
            }
        }
        else{
            // send a request to the database to check user login
            // launch a progress dialog that communicates the ongoing actions
            pd.setMessage(getString(R.string.loading));
            pd.show();
            // look if the inserted user exist in the database
            auth.signInWithEmailAndPassword(mail, pass)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(getContext(), getString(R.string.welcome), Toast.LENGTH_LONG).show();
                                pd.dismiss();
                                loginViewModel.authenticationSuccessful();
                            } else{
                                Toast.makeText(getContext(), getString(R.string.error_generic), Toast.LENGTH_LONG).show();
                                pd.dismiss();
                                loginViewModel.authenticationFailed();
                            }
                        }
                    });
        }
    }


    private void register(){
        register_mail = register_email.getText().toString().toLowerCase();
        register_pass = register_password.getText().toString();
        register_pass_confirm = register_password_confirmation.getText().toString();

        // check that the password and the password confirmation are the same
        if(register_pass.equals(register_pass_confirm)){
            if(register_mail.equals("")){
                register_email.setError(getString(R.string.blank_mail));
            }
            else if(register_pass.equals("")){
                register_password.setError(getString(R.string.blank_password));
            }
            else if(register_mail.length()<5){
                register_email.setError(getString(R.string.short_user));
            }
            else if(register_pass.length()<5){
                register_password.setError(getString(R.string.short_password));
            }
            // send the user credential to the server for the registration
            else{
                // launch a progress dialog that communicates the ongoing actions
                pd.show();
                // first read from the database if the inserted user already exist
                auth.createUserWithEmailAndPassword(register_mail, register_pass)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    firebaseUser = auth.getCurrentUser();
                                    loginViewModel.authenticationSuccessful();
                                    insertUser(firebaseUser);
                                }else{
                                    Toast.makeText(getContext(), getString(R.string.error_generic),Toast.LENGTH_SHORT).show();
                                    pd.dismiss();
                                    loginViewModel.authenticationFailed();
                                }
                            }
                        });
            }
        }
        else{
            Toast.makeText(getContext(), getString(R.string.password_not_confirmed), Toast.LENGTH_LONG).show();
        }
    }


    private void insertUser(FirebaseUser user){
        User actualUser = new User(firebaseUser.getUid(), firebaseUser.getEmail());
        db.collection(DATABASE_COLLECTION)
                .document(firebaseUser.getUid())
                .set(actualUser)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        pd.dismiss();
                        Toast.makeText(getContext(), getString(R.string.welcome), Toast.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        pd.dismiss();
                        Toast.makeText(getContext(), getString(R.string.error_generic), Toast.LENGTH_LONG).show();
                    }
                });
    }


    private void handleFacebookToken(AccessToken token) {
        AuthCredential credential= FacebookAuthProvider.getCredential(token.getToken());
        auth.signInWithCredential(credential)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            firebaseUser = auth.getCurrentUser();
                            loginViewModel.authenticationSuccessful();
                            insertUser(firebaseUser);
                        }else{
                            Toast.makeText(getContext(), getString(R.string.error_generic),Toast.LENGTH_SHORT).show();
                            loginViewModel.authenticationFailed();
                            pd.dismiss();
                        }
                    }
                });
    }


    private void handleGoogleToken(Task<GoogleSignInAccount> completedTask){
        try{
            pd.show();
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(),null);
            auth.signInWithCredential(credential)
                    .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                firebaseUser = auth.getCurrentUser();
                                loginViewModel.authenticationSuccessful();
                                insertUser(firebaseUser);
                            }
                            else{
                                Toast.makeText(getContext(), getString(R.string.error_generic), Toast.LENGTH_LONG).show();
                                loginViewModel.authenticationFailed();
                                pd.dismiss();
                            }
                        }
                    });
        }catch (ApiException e){
            Toast.makeText(getContext(), getString(R.string.error_generic), Toast.LENGTH_LONG).show();
            pd.dismiss();
        }
    }


    @Override
    public void onActivityResult(final int requestCode, final int resultCode, @Nullable Intent data) {
        Log.d("LOGINFRAGMENT","Entered in the on ativity result");
        if(requestCode == RC_GOOGLE_SIGNIN){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleGoogleToken(task);
        } else{
            callbackManager.onActivityResult(requestCode, resultCode, data);
        }
    }
}