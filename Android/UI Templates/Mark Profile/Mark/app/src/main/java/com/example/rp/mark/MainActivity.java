package com.example.rp.mark;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {



    GridView gridView;

    private int[] Image = {R.drawable.image1,R.drawable.image2,R.drawable.image3};
    private ArrayList<Beanclass> beans;
    private GridBaseAdapter gridBaseAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        gridView= (GridView)findViewById(R.id.gridview);
        beans= new ArrayList<Beanclass>();


        for (int i= 0; i< Image.length; i++) {

            Beanclass beanclass = new Beanclass(Image[i]);
            beans.add(beanclass);

        }

   gridBaseAdapter = new GridBaseAdapter(MainActivity.this, beans);

        gridView.setAdapter(gridBaseAdapter);
    }
}
