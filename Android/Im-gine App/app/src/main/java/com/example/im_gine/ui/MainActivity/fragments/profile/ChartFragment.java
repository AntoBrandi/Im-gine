package com.example.im_gine.ui.MainActivity.fragments.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.im_gine.R;
import adapter.ChartAdapter;
import java.util.ArrayList;
import model.Chart;

public class ChartFragment extends Fragment {

    private RecyclerView chart_recyclerView;
    private ChartAdapter chartAdapter;
    private ArrayList<Chart> charts;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_profile_charts, container, false);
        chart_recyclerView = view.findViewById(R.id.profile_chart_recyclerView);
        chart_recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        charts = new ArrayList<>();
        charts.add(new Chart(R.drawable.vertical_1,"Chart1"));
        charts.add(new Chart(R.drawable.vertical_2,"Chart2"));
        charts.add(new Chart(R.drawable.vertical_3,"Chart3"));
        charts.add(new Chart(R.drawable.vertical_4,"Chart4"));
        charts.add(new Chart(R.drawable.horizontal_1,"Chart5"));
        charts.add(new Chart(R.drawable.horizontal_2,"Chart6"));
        charts.add(new Chart(R.drawable.horizontal_3,"Chart7"));
        charts.add(new Chart(R.drawable.horizontal_4,"Chart8"));
        chartAdapter = new ChartAdapter(getActivity(), charts);
        chart_recyclerView.setAdapter(chartAdapter);

        return view;
    }
}
