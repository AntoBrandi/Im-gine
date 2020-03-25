package com.example.im_gine;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
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
    private final String DATABASE_COLLECTION = "users";

    // Application variables
    private String user;
    private String pass;
    private String pass_confirm;
    private User activeUser;

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

        // firebase setup
        Firebase.setAndroidContext(this);
        db = FirebaseFirestore.getInstance();


        // Button click Event Handler
        // register in the application
        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user = email.getText().toString();
                pass = password.getText().toString();
                pass_confirm = password_confirmation.getText().toString();

                // check that the password and the password confirmation are the same
                if(pass.equals(pass_confirm)){
                    if(user.equals("")){
                        email.setError(getString(R.string.blank_mail));
                    }
                    else if(pass.equals("")){
                        password.setError(getString(R.string.blank_password));
                    }
                    else if(!user.matches("[A-Za-z0-9]+")){
                        email.setError(getString(R.string.invalid_character));
                    }
                    else if(user.length()<5){
                        email.setError(getString(R.string.short_user));
                    }
                    else if(pass.length()<5){
                        password.setError(getString(R.string.short_password));
                    }
                    // send the user credential to the server for the registration
                    else{
                        // launch a progress dialog that communicates the ongoing actions
                        final ProgressDialog pd = new ProgressDialog(SignupActivity.this);
                        pd.setMessage(getString(R.string.loading));
                        pd.show();
                        activeUser = new User(user, pass);
                        // write the data on the database
                        db.collection(DATABASE_COLLECTION)
                                .add(activeUser)
                                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                    @Override
                                    public void onSuccess(DocumentReference documentReference) {
                                        pd.dismiss();
                                        Toast.makeText(SignupActivity.this, getString(R.string.welcome)+" "+user, Toast.LENGTH_LONG).show();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        pd.dismiss();
                                        Toast.makeText(SignupActivity.this, getString(R.string.error_generic), Toast.LENGTH_LONG).show();
                                    }
                                });
                        pd.dismiss();
                    }
                }
                else{
                    Toast.makeText(SignupActivity.this, getString(R.string.password_not_confirmed), Toast.LENGTH_LONG).show();
                }
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

}
