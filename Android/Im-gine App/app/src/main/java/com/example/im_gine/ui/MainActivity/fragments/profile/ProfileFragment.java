package com.example.im_gine.ui.MainActivity.fragments.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import com.example.im_gine.R;
import com.example.im_gine.ui.MainActivity.card.ChartAdapter;
import com.example.im_gine.ui.MainActivity.fragments.login.LoginViewModel;
import java.util.ArrayList;
import model.Chart;

public class ProfileFragment extends Fragment {

    private ProfileViewModel profileViewModel;
    private LoginViewModel loginViewModel;

    // UI variables
    //private RecyclerView chart_recyclerView;
    //private ChartAdapter chartAdapter;
    private ArrayList<Chart> charts;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        profileViewModel = ViewModelProviders.of(this).get(ProfileViewModel.class);
        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        final View view = inflater.inflate(R.layout.fragment_profile, container, false);
        //chart_recyclerView = view.findViewById(R.id.profile_chart_recyclerView);
        //chart_recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        charts = new ArrayList<>();


        final NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
        loginViewModel.authStatus.observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isAuth) {
                if(isAuth){
                    showProfile(view);
                }
                else{
                    navController.navigate(R.id.navigation_login);
                }
            }
        });
        return view;
    }

    private void showProfile(View view){
        charts.add(new Chart(R.drawable.vertical_1,"Chart1"));
        charts.add(new Chart(R.drawable.vertical_2,"Chart2"));
        charts.add(new Chart(R.drawable.vertical_3,"Chart3"));
        charts.add(new Chart(R.drawable.vertical_4,"Chart4"));
        charts.add(new Chart(R.drawable.horizontal_1,"Chart5"));
        charts.add(new Chart(R.drawable.horizontal_2,"Chart6"));
        charts.add(new Chart(R.drawable.horizontal_3,"Chart7"));
        charts.add(new Chart(R.drawable.horizontal_4,"Chart8"));
        //chartAdapter = new ChartAdapter(getActivity(), charts);
        //chart_recyclerView.setAdapter(chartAdapter);
    }
}
