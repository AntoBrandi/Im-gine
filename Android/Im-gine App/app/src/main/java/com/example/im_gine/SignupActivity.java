package com.example.im_gine;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.firebase.client.Firebase;
import org.json.JSONException;
import org.json.JSONObject;
import custom_font.MyEditText;
import custom_font.MyTextView;

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
    private final String DATABASE_URL = "https://im-gine.firebaseio.com/";
    private final String USER = "users";
    private final String DATA_EXTENSION = ".json";

    // Application variables
    private String user;
    private String pass;
    private String pass_confirm;

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
                        // send an HTTP request to the server
                        StringRequest request = new StringRequest(Request.Method.GET, DATABASE_URL + USER + DATA_EXTENSION, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Firebase reference = new Firebase(DATABASE_URL + USER);

                                if(response.equals("null")) {
                                    reference.child(user).child("password").setValue(pass);
                                    Toast.makeText(SignupActivity.this, getString(R.string.registration_successfully), Toast.LENGTH_LONG).show();
                                }
                                else {
                                    try {
                                        JSONObject obj = new JSONObject(response);

                                        if (!obj.has(user)) {
                                            reference.child(user).child("password").setValue(pass);
                                            Toast.makeText(SignupActivity.this, getString(R.string.registration_successfully), Toast.LENGTH_LONG).show();
                                        } else {
                                            Toast.makeText(SignupActivity.this, getString(R.string.duplicated_username), Toast.LENGTH_LONG).show();
                                        }

                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                                pd.dismiss();
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                                    //This indicates that the request has either time out or there is no connection
                                    Toast.makeText(SignupActivity.this, getString(R.string.no_connection), Toast.LENGTH_LONG).show();
                                } else if (error instanceof AuthFailureError) {
                                    //Error indicating that there was an Authentication Failure while performing the request
                                    Toast.makeText(SignupActivity.this, getString(R.string.auth_failure), Toast.LENGTH_LONG).show();
                                } else if (error instanceof ServerError) {
                                    //Indicates that the server responded with a error response
                                    Toast.makeText(SignupActivity.this, getString(R.string.internal_server_error), Toast.LENGTH_LONG).show();
                                } else if (error instanceof NetworkError) {
                                    //Indicates that there was network error while performing the request
                                    Toast.makeText(SignupActivity.this, getString(R.string.network_error), Toast.LENGTH_LONG).show();
                                } else {
                                    // All the other errors
                                    Toast.makeText(SignupActivity.this, getString(R.string.unknown_error), Toast.LENGTH_LONG).show();
                                }
                            }
                        });
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
