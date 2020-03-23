package com.example.im_gine;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import custom_font.MyTextView;

public class SignupActivity extends AppCompatActivity {
    TextView holiday;
    MyTextView signin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        signin = (MyTextView) findViewById(R.id.signin);

        holiday = (TextView)findViewById(R.id.holliday);

        Typeface custom_fonts = Typeface.createFromAsset(getAssets(), "fonts/ArgonPERSONAL-Regular.otf");
        holiday.setTypeface(custom_fonts);


        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(SignupActivity.this, SigninActivity.class);
                startActivity(it);
            }
        });

    }

}
