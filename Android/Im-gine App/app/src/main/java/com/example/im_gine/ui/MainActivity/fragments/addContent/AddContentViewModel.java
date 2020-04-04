package com.example.im_gine.ui.MainActivity.fragments.addContent;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AddContentViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public AddContentViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is add content fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}