package com.example.im_gine.ui.home;

import android.animation.ArgbEvaluator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;
import com.example.im_gine.R;
import com.example.im_gine.ui.card.CardView;
import com.example.im_gine.ui.card.CardViewAdapter;
import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    ViewPager viewPager;
    CardViewAdapter adapter;
    List<CardView> cards;
    Integer[] backgroundColors = null;
    ArgbEvaluator argbEvaluator = new ArgbEvaluator();

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        cards = new ArrayList<>();
        cards.add(new CardView(R.drawable.tech, "Tech", "Join million of conversations about tech"));
        cards.add(new CardView(R.drawable.food, "Food", "Taste the world"));
        cards.add(new CardView(R.drawable.travel, "Travel", "The world is yours"));
        cards.add(new CardView(R.drawable.love, "Love", "Your true love could be anywhere"));

        adapter = new CardViewAdapter(cards, getActivity());

        viewPager = root.findViewById(R.id.home_viewPager);
        viewPager.setAdapter(adapter);
        viewPager.setPadding(130, 0, 130, 0);

        Integer[] colors_temp = {
                getResources().getColor(R.color.color1),
                getResources().getColor(R.color.color2),
                getResources().getColor(R.color.color3),
                getResources().getColor(R.color.color4)
        };
        backgroundColors = colors_temp;


        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if(position<(adapter.getCount()-1) && position < (backgroundColors.length - 1)){
                    viewPager.setBackgroundColor(

                            (Integer) argbEvaluator.evaluate(
                                    positionOffset,
                                    backgroundColors[position],
                                    backgroundColors[position + 1]
                            )
                    );
                }
                else {
                    viewPager.setBackgroundColor(backgroundColors[backgroundColors.length - 1]);
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        return root;
    }
}
