package com.example.im_gine;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import custom_font.MyEditText;
import custom_font.MyTextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SigninActivity extends AppCompatActivity {

    // View variables
    private TextView title;
    private MyTextView createBtn;
    private MyTextView helpBtn;
    private MyEditText email;
    private MyEditText password;
    private MyTextView loginBtn;

    // Firebase Variables
    private FirebaseAuth auth;

    // Application variables
    private String mail;
    private String pass;
    private ProgressDialog pd;
    private Intent it;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        createBtn = (MyTextView)findViewById(R.id.create);
        title = (TextView)findViewById(R.id.title_textView);
        helpBtn = (MyTextView)findViewById(R.id.help);
        email = (MyEditText)findViewById(R.id.email);
        password = (MyEditText)findViewById(R.id.password);
        loginBtn = (MyTextView)findViewById(R.id.loginBtn);

        it = new Intent(SigninActivity.this, MainActivity.class);

        // style related stuffs
        Typeface custom_fonts = Typeface.createFromAsset(getAssets(), "fonts/ArgonPERSONAL-Regular.otf");
        title.setTypeface(custom_fonts);
        pd = new ProgressDialog(SigninActivity.this);

        // firebase setup
        Firebase.setAndroidContext(this);
        auth = FirebaseAuth.getInstance();


        // Button click Event Handler
        // Login into the application
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                    login();
                }
            }
        });
        // Create new account
        createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent it = new Intent(SigninActivity.this,SignupActivity.class);
                startActivity(it);
            }
        });
        // Go to the help page
        helpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: help page
            }
        });
    }

    private void login(){
        auth.signInWithEmailAndPassword(mail, pass)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(SigninActivity.this, getString(R.string.welcome), Toast.LENGTH_LONG).show();
                            pd.dismiss();
                            startActivity(it);
                        } else{
                            Toast.makeText(SigninActivity.this, getString(R.string.error_generic), Toast.LENGTH_LONG).show();
                            pd.dismiss();
                        }
                    }
                });
    }
}

