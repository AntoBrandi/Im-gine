package com.example.im_gine.ui.MainActivity.fragments.login;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.im_gine.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import custom_font.MyEditText;
import custom_font.MyTextView;

public class LoginFragment extends Fragment {

    // UI variables
    private LoginViewModel loginViewModel;
    private ProgressDialog pd;
    private MyTextView helpBtn;
    private MyEditText email;
    private MyEditText password;
    private MyTextView loginBtn;
    private MyTextView registerBtn;

    // Application variables
    private String mail;
    private String pass;

    // Firebase Variables
    private FirebaseAuth auth;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        helpBtn = (MyTextView)view.findViewById(R.id.help);
        email = (MyEditText)view.findViewById(R.id.email);
        password = (MyEditText)view.findViewById(R.id.password);
        loginBtn = (MyTextView)view.findViewById(R.id.loginBtn);
        registerBtn = (MyTextView)view.findViewById(R.id.signupBtn);

        pd = new ProgressDialog(getContext());
        auth = FirebaseAuth.getInstance();

        // listener for the password edit Text
        password.setOnEditorActionListener(new TextView.OnEditorActionListener() {
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

        // listener for the login button
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        // listener for the register button
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });

        // listener for the help page button
        helpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: help page
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
                    Snackbar.make(root, R.string.invalid_credentials, Snackbar.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

    private void login(){
        // get username and password
        mail = email.getText().toString().toLowerCase();
        pass = password.getText().toString();
        // check if the user inserted something in the username and password fields
        if (mail.equals("") || pass.equals("")){
            if(mail.equals("") ){
                email.setError(getString(R.string.blank_username));
            }
            if (pass.equals("")){
                password.setError(getString(R.string.blank_password));
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

    }
}