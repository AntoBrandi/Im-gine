package com.example.rp.holliday;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import custom_font.MyTextView;

public class MainActivity extends AppCompatActivity {
    TextView holliday;
    MyTextView create;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        create = (MyTextView)findViewById(R.id.create);
        holliday = (TextView)findViewById(R.id.holliday);

        Typeface custom_fonts = Typeface.createFromAsset(getAssets(), "fonts/ArgonPERSONAL-Regular.otf");
        holliday.setTypeface(custom_fonts);

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent it = new Intent(MainActivity.this,MainActivity1.class);
                startActivity(it);
            }
        });
    }
}
