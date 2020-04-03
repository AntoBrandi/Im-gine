package com.example.im_gine;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import custom_font.MyEditText;
import custom_font.MyTextView;
import model.User;

public class SignupActivity extends AppCompatActivity {

    // View variables
    private TextView title;
    private MyTextView signinBtn;
    private MyTextView signupBtn;
    private MyTextView helpBtn;
    private MyEditText email;
    private MyEditText password;
    private MyEditText password_confirmation;

    // Firebase Variables
    private FirebaseFirestore db;
    private FirebaseAuth auth;
    private final String DATABASE_COLLECTION = "users";

    // Application variables
    private String mail;
    private String pass;
    private String pass_confirm;
    private User activeUser;
    private ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        signinBtn = (MyTextView) findViewById(R.id.signin);
        title = (TextView)findViewById(R.id.title);
        signupBtn = (MyTextView)findViewById(R.id.signup);
        helpBtn = (MyTextView)findViewById(R.id.help);
        email = (MyEditText)findViewById(R.id.email);
        password = (MyEditText)findViewById(R.id.password);
        password_confirmation = (MyEditText)findViewById(R.id.password_confirmation);

        // style related stuffs
        Typeface custom_fonts = Typeface.createFromAsset(getAssets(), "fonts/ArgonPERSONAL-Regular.otf");
        title.setTypeface(custom_fonts);
        pd = new ProgressDialog(SignupActivity.this);

        // firebase setup
        Firebase.setAndroidContext(this);
        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        // listener or the edit text
        password_confirmation.setOnEditorActionListener(new TextView.OnEditorActionListener() {
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


        // Button click Event Handler
        // register in the application
        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });
        // Move to the signin activity
        signinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(SignupActivity.this, SigninActivity.class);
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

    private void register(){
        mail = email.getText().toString().toLowerCase();
        pass = password.getText().toString();
        pass_confirm = password_confirmation.getText().toString();

        // check that the password and the password confirmation are the same
        if(pass.equals(pass_confirm)){
            if(mail.equals("")){
                email.setError(getString(R.string.blank_mail));
            }
            else if(pass.equals("")){
                password.setError(getString(R.string.blank_password));
            }
            else if(mail.length()<5){
                email.setError(getString(R.string.short_user));
            }
            else if(pass.length()<5){
                password.setError(getString(R.string.short_password));
            }
            // send the user credential to the server for the registration
            else{
                // launch a progress dialog that communicates the ongoing actions
                pd.setMessage(getString(R.string.loading));
                pd.show();
                // first read from the database if the inserted user already exist
                auth.createUserWithEmailAndPassword(mail, pass)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    FirebaseUser firebaseUser = auth.getCurrentUser();
                                    String userId = auth.getUid();
                                    activeUser = new User(userId, mail);
                                    insertUser();
                                }else{
                                    Toast.makeText(SignupActivity.this, getString(R.string.error_generic),Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        }
        else{
            Toast.makeText(SignupActivity.this, getString(R.string.password_not_confirmed), Toast.LENGTH_LONG).show();
        }
    }

    private void insertUser(){
        db.collection(DATABASE_COLLECTION)
                .document(activeUser.get_userId())
                .set(activeUser)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        pd.dismiss();
                        Toast.makeText(SignupActivity.this, getString(R.string.welcome), Toast.LENGTH_LONG).show();
                        Intent it = new Intent(SignupActivity.this, MainActivity.class);
                        startActivity(it);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        pd.dismiss();
                        Toast.makeText(SignupActivity.this, getString(R.string.error_generic), Toast.LENGTH_LONG).show();
                    }
                });
    }
}
