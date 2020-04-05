package com.example.im_gine.ui.MainActivity.fragments.login;

import android.app.Instrumentation;
import android.content.Intent;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.google.firebase.auth.FirebaseAuth;
import model.ResultActivity;

public class LoginViewModel extends ViewModel {

    private MutableLiveData<FirebaseAuth> mAuth = new MutableLiveData<>();
    public final MutableLiveData<Boolean> authStatus = new MutableLiveData<>();
    public final MutableLiveData<Boolean> isFirstTime = new MutableLiveData<>();
    public final MutableLiveData<ResultActivity> activityResult = new MutableLiveData<>();

    public LoginViewModel() {
        isFirstTime.setValue(true);
        mAuth.setValue(FirebaseAuth.getInstance());
        if (mAuth.getValue().getCurrentUser()!=null){
            authStatus.setValue(true);
        }
        else{
            authStatus.setValue(false);
        }
    }

    public void authenticationSuccessful(){
        this.isFirstTime.setValue(false);
        this.authStatus.setValue(true);
    }

    public void authenticationFailed(){
        this.isFirstTime.setValue(false);
        this.authStatus.setValue(false);
    }

}