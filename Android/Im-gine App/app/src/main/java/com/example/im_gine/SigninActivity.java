package com.example.im_gine;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import custom_font.MyTextView;
import androidx.appcompat.app.AppCompatActivity;

public class SigninActivity extends AppCompatActivity {

    TextView holiday;
    MyTextView create;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        create = (MyTextView)findViewById(R.id.create);
        holiday = (TextView)findViewById(R.id.holliday);

        Typeface custom_fonts = Typeface.createFromAsset(getAssets(), "fonts/ArgonPERSONAL-Regular.otf");
        holiday.setTypeface(custom_fonts);

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent it = new Intent(SigninActivity.this,SignupActivity.class);
                startActivity(it);
            }
        });
    }
}

