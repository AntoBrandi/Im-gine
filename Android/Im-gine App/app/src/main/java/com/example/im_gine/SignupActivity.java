package com.example.im_gine;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
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
    private String mail;
    private String pass;
    private String pass_confirm;
    private User activeUser;
    private boolean IsNewUser = false;
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


        // Button click Event Handler
        // register in the application
        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mail = email.getText().toString();
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
                        activeUser = new User(mail, pass);
                        // first read from the database if the inserted user already exist
                        TryRegister();
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


    private void TryRegister(){
        db.collection(DATABASE_COLLECTION)
                .whereEqualTo("_username", mail)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            QuerySnapshot document = task.getResult();
                            if(document.isEmpty()){
                                InsertUser();
                            }
                            else{
                                Toast.makeText(SignupActivity.this, getString(R.string.user_already_registered), Toast.LENGTH_LONG).show();
                                pd.dismiss();
                            }
                        }else{
                            pd.dismiss();
                            Toast.makeText(SignupActivity.this, getString(R.string.error_generic), Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }


    private void InsertUser(){
        db.collection(DATABASE_COLLECTION)
                .add(activeUser)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
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
