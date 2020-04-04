package com.example.im_gine.ui.MainActivity.fragments.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import com.example.im_gine.R;
import com.example.im_gine.ui.MainActivity.fragments.login.LoginViewModel;

public class ProfileFragment extends Fragment {

    private ProfileViewModel profileViewModel;
    private LoginViewModel loginViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        profileViewModel = ViewModelProviders.of(this).get(ProfileViewModel.class);
        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        final View view = inflater.inflate(R.layout.fragment_profile, container, false);

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
        final TextView textView = view.findViewById(R.id.text_profile);
        profileViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
    }
}
