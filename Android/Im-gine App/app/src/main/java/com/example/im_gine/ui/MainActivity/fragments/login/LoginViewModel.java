package com.example.im_gine.ui.MainActivity.fragments.login;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.google.firebase.auth.FirebaseAuth;

public class LoginViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private MutableLiveData<FirebaseAuth> mAuth = new MutableLiveData<>();
    public final MutableLiveData<Boolean> authStatus = new MutableLiveData<>();

    public LoginViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is login fragment");
        mAuth.setValue(FirebaseAuth.getInstance());
        authStatus.setValue(false);
    }

    public LiveData<String> getText() {
        return mText;
    }
}