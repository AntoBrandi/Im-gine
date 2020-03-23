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
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.firebase.client.Firebase;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import model.User;
import org.json.JSONException;
import org.json.JSONObject;

public class SigninActivity extends AppCompatActivity {

    // View variables
    private TextView title;
    private MyTextView createBtn;
    private MyTextView helpBtn;
    private MyEditText username;
    private MyEditText password;
    private MyTextView loginBtn;

    // Firebase Variables
    private final String DATABASE_URL = "https://im-gine.firebaseio.com/";
    private final String USER = "users.json";

    // Application variables
    private String user;
    private String pass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        createBtn = (MyTextView)findViewById(R.id.create);
        title = (TextView)findViewById(R.id.title_textView);
        helpBtn = (MyTextView)findViewById(R.id.help);
        username = (MyEditText)findViewById(R.id.username);
        password = (MyEditText)findViewById(R.id.password);
        loginBtn = (MyTextView)findViewById(R.id.loginBtn);

        // style related stuffs
        Typeface custom_fonts = Typeface.createFromAsset(getAssets(), "fonts/ArgonPERSONAL-Regular.otf");
        title.setTypeface(custom_fonts);

        // firebase setup
        Firebase.setAndroidContext(this);


        // Button click Event Handler
        // Login into the application
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get username and password
                user = username.getText().toString();
                pass = password.getText().toString();
                // check if the user inserted something in the username and password fields
                if (user.equals("") || pass.equals("")){
                    if(user.equals("") ){
                        username.setError(getString(R.string.blank_username));
                    }
                    if (pass.equals("")){
                        password.setError(getString(R.string.blank_password));
                    }
                }
                else{
                    // send a request to the database to check user login
                    // launch a progress dialog that communicates the ongoing actions
                    final ProgressDialog pd = new ProgressDialog(SigninActivity.this);
                    pd.setMessage(getString(R.string.loading));
                    pd.show();
                    // send an HTTP request to the server
                    StringRequest request = new StringRequest(Request.Method.GET, DATABASE_URL + USER, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            if(response.equals("null")){
                                Toast.makeText(SigninActivity.this, getString(R.string.user_not_found), Toast.LENGTH_LONG).show();
                            }
                            else{
                                try {
                                    JSONObject obj = new JSONObject(response);

                                    if(!obj.has(user)){
                                        Toast.makeText(SigninActivity.this, getString(R.string.user_not_found), Toast.LENGTH_LONG).show();
                                    }
                                    else if(obj.getJSONObject(user).getString("password").equals(pass)){
                                        User activeUser = new User(user, pass);
                                        // start the main activity of the chat room
                                        startActivity(new Intent(SigninActivity.this, MainActivity.class));
                                    }
                                    else {
                                        Toast.makeText(SigninActivity.this, getString(R.string.incorrect_password), Toast.LENGTH_LONG).show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                            pd.dismiss();
                        }
                    }, new Response.ErrorListener(){
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                                //This indicates that the request has either time out or there is no connection
                                Toast.makeText(SigninActivity.this, getString(R.string.no_connection), Toast.LENGTH_LONG).show();
                            } else if (error instanceof AuthFailureError) {
                                //Error indicating that there was an Authentication Failure while performing the request
                                Toast.makeText(SigninActivity.this, getString(R.string.auth_failure), Toast.LENGTH_LONG).show();
                            } else if (error instanceof ServerError) {
                                //Indicates that the server responded with a error response
                                Toast.makeText(SigninActivity.this, getString(R.string.internal_server_error), Toast.LENGTH_LONG).show();
                            } else if (error instanceof NetworkError) {
                                //Indicates that there was network error while performing the request
                                Toast.makeText(SigninActivity.this, getString(R.string.network_error), Toast.LENGTH_LONG).show();
                            } else {
                                // All the other errors
                                Toast.makeText(SigninActivity.this, getString(R.string.unknown_error), Toast.LENGTH_LONG).show();
                            }
                            pd.dismiss();
                        }
                    });
                    // test this below
                    RequestQueue rQueue = Volley.newRequestQueue(SigninActivity.this);
                    rQueue.add(request);
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
}

