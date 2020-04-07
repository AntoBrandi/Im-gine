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
import androidx.viewpager.widget.ViewPager;
import com.example.im_gine.R;
import com.example.im_gine.ui.MainActivity.fragments.login.LoginViewModel;
import com.google.android.material.tabs.TabLayout;
import adapter.ProfileAdapter;

public class ProfileFragment extends Fragment {

    private ProfileViewModel profileViewModel;
    private LoginViewModel loginViewModel;

    // UI variables
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ProfileAdapter profileAdapter;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        profileViewModel = ViewModelProviders.of(this).get(ProfileViewModel.class);
        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        final View view = inflater.inflate(R.layout.fragment_profile, container, false);
        tabLayout = (TabLayout) view.findViewById(R.id.tabs);
        viewPager = (ViewPager) view.findViewById(R.id.profile_viewPager);


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
        profileAdapter = new ProfileAdapter(getChildFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(profileAdapter);
    }
}
