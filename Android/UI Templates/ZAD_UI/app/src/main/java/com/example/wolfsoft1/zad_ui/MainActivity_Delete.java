package com.example.wolfsoft1.zad_ui;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;

import java.util.ArrayList;



public class MainActivity_Delete extends AppCompatActivity {

    private SwipeMenuListView listView;
    private ArrayList<Data> dataArrayList;
    private ListAdapter listAdapter;
    private Data data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);

        listView = (SwipeMenuListView) findViewById(R.id.listview);
        dataArrayList = new ArrayList<>();



        dataArrayList.add(data = new Data("Eugene sent you a msg", "6386 keshlerin Canyon"));
        dataArrayList.add(data = new Data("Hellie Jefferson", "937 Feeney Stravenue"));
        dataArrayList.add(data = new Data("Eugene sent you a msg", "6386 keshlerin Canyon"));
        dataArrayList.add(data = new Data("Hellie sent you a msg", "937 Feeney Stravenue"));
        dataArrayList.add(data = new Data("Eugene sent you a msg", "6386 keshlerin Canyon"));
        dataArrayList.add(data = new Data("Hellie sent you a msg", "937 Feeney Stravenue"));
        dataArrayList.add(data = new Data("Eugene sent you a msg", "6386 keshlerin Canyon"));
        dataArrayList.add(data = new Data("Hellie sent you a msg", "937 Feeney Stravenue"));

        listAdapter = new ListAdapter(this, dataArrayList);
        listView.setAdapter(listAdapter);

        listView.setMenuCreator(creator);

        listView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:

                        Toast.makeText(MainActivity_Delete.this, "Delete", Toast.LENGTH_SHORT).show();
                        Log.e("item", String.valueOf(listView.getAdapter().getItem(position)));
                        Log.e("name", String.valueOf(dataArrayList.get(position).getName()));

                        dataArrayList.remove(position);

                        listAdapter.notifyDataSetChanged();

                        break;
                    case 1:
                        // delete
                        break;
                }
                // false : close the menu; true : not close the menu
                return false;
            }
        });

    }


    SwipeMenuCreator creator = new SwipeMenuCreator() {

        @Override
        public void create(SwipeMenu menu) {


            // create "delete" item
            SwipeMenuItem deleteItem = new SwipeMenuItem(
                    getApplicationContext());
            // set item background
            deleteItem.setBackground(new ColorDrawable(Color.parseColor("#F45557")));
            // set item width
            deleteItem.setWidth(150);

            deleteItem.setTitle("Delete");
            deleteItem.setTitleColor(Color.WHITE);
            deleteItem.setTitleSize(15);

            // add to menu
            menu.addMenuItem(deleteItem);
        }
    };
}
