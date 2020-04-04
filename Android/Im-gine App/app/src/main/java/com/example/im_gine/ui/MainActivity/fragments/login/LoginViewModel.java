package com.example.im_gine.ui.MainActivity.fragments.login;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.google.firebase.auth.FirebaseAuth;

public class LoginViewModel extends ViewModel {

    private MutableLiveData<FirebaseAuth> mAuth = new MutableLiveData<>();
    public final MutableLiveData<Boolean> authStatus = new MutableLiveData<>();

    public LoginViewModel() {
        mAuth.setValue(FirebaseAuth.getInstance());
        if (mAuth.getValue().getCurrentUser()!=null){
            authStatus.setValue(true);
        }
        else{
            authStatus.setValue(false);
        }
    }

    public void authenticationSuccessful(){
        this.authStatus.setValue(true);
    }

    public void authenticationFailed(){
        this.authStatus.setValue(false);
    }

}