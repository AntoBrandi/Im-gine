package com.example.im_gine.ui.MainActivity.fragments.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
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
import model.User;

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
        googleButton = (ImageButton) view.findViewById(R.id.google_button);


        // UI related settings
        // displays the title with a nice font
        Typeface custom_fonts = Typeface.createFromAsset(getActivity().getAssets(), "fonts/ArgonPERSONAL-Regular.otf");
        appName.setTypeface(custom_fonts);
        // prepare the dialog for the visualization
        pd = new ProgressDialog(getContext());


        // Firebase variables
        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();


        // listener for the password edit Text
        // set the confirmation button of this edit text able to throw a login event
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
        // set the confirmation button of this edit text able to throw a register event
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
        // navigate from the register CardView to the login CardView
        register_loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginCardView.setVisibility(View.VISIBLE);
                registerCardView.setVisibility(View.GONE);
            }
        });

        // listener for the register button
        // navigate from the login CardView to the register CardView
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
        // observe a variable belonging to the class view model, and when a new status is detected throw this event
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
                Intent signInIntent = googleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent, RC_GOOGLE_SIGNIN);
            }
        });

        return view;
    }


    /*
    Function that gets called when the user clicks on the login button in the login CardView

    1 - Check that the insertet username and password are not empty
    2 - Show a progress dialog while waiting for the auth result
    3 - Send an auth request to the Google Firebase with the given username and password
    4 - Add a listener to the result coming from the server
    5 - If the result is successful, dismiss the progress dialog and update the value of the variable auth to true in the loginViewModel
    6 - If the result s not successful, dismiss the progress dialog and update the value of the variable auth to false in the loginViewModel
     */
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


    /*
    Function that gets called when the user clicks on the register button in the register CardView

    1 - Check if the username and password editText are not empty
    2 - Check that the password and the username respect the security requirements
    3 - Check that the password and the password onfimation are the same
    4 - Show a progress dialog while waiting for the auth result
    5 - Send an auth request to the Google Firebase with the given username and password
    6 - Add a listener to the result coming from the server
    7 - If the result is successful, dismiss the progress dialog and update the value of the variable auth to true in the loginViewModel
    8 - If the result s not successful, dismiss the progress dialog and update the value of the variable auth to false in the loginViewModel
     */
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
                pd.setMessage(getString(R.string.loading));
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


    /*
    Function that gets called every time a new user register in the application, both manually or via social networks

    1 - Create an user object with the information about the current user
    2 - Get a reference to the Cloud Firestone database , to a specific document, named as the id of the user
    3 - Add the created object to the database
    4 - Add a listener to the result coming from the database
    5 - If the insert operation is successful, dismiss the progress dialog and show a toast to the user
    6 - If the insert operation is not successful, dismiss the progress dialog and show a toast to the user
     */
    private void insertUser(FirebaseUser user){
        User actualUser = new User(firebaseUser.getUid(), firebaseUser.getEmail());
        db.collection(DATABASE_COLLECTION)
                .document(firebaseUser.getUid())
                .set(actualUser)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        pd.dismiss();
                        Toast.makeText(getContext(), getString(R.string.welcome), Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        pd.dismiss();
                        Toast.makeText(getContext(), getString(R.string.error_generic), Toast.LENGTH_SHORT).show();
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


    /*
    Method that gets called when another activity is shown to the main screen and then it's dismissed. So the actual activity
    is again visible to the user. This happens when there is an authentication process via social networks.

    Anyway, given that this is a fragment in a navigation controller, this method for this fragment is thrown from the parent activity
     */
    @Override
    public void onActivityResult(final int requestCode, final int resultCode, @Nullable Intent data) {
        if(requestCode == RC_GOOGLE_SIGNIN){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleGoogleToken(task);
        } else{
            callbackManager.onActivityResult(requestCode, resultCode, data);
        }
    }
}