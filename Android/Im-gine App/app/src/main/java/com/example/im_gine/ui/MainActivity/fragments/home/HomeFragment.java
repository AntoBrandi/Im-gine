package com.example.im_gine.ui.MainActivity.fragments.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.im_gine.R;
import com.example.im_gine.ui.MainActivity.card.CardView;
import com.example.im_gine.ui.MainActivity.card.CardViewAdapter;
import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    RecyclerView recyclerView;
    CardViewAdapter adapter;
    List<CardView> cards;

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = root.findViewById(R.id.home_recyclerView);

        cards = new ArrayList<>();
        cards.add(new CardView(R.drawable.tech, "My last tech toy", R.drawable.miriam_profile,"Miriam Leone", 1000, 23, 3));
        cards.add(new CardView(R.drawable.food, "Food", R.drawable.miriam_profile,"Miriam Leone", 1000, 23, 3));
        cards.add(new CardView(R.drawable.travel, "Travel",R.drawable.miriam_profile,"Miriam Leone", 1000, 23, 3));
        cards.add(new CardView(R.drawable.love, "Love", R.drawable.miriam_profile,"Miriam Leone", 1000, 23, 3));


        adapter = new CardViewAdapter(cards, getActivity());

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

        return root;
    }
}
