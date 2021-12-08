package com.zhora.sheetsexample.ui.ammo;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AmmoViewModel extends ViewModel {

    int i = 0;
    private MutableLiveData<String> mText;

    public AmmoViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is dashboard fragment " + i);
        //ReadExcelTable.ReadTable();
        i+=1;
    }

    public LiveData<String> getText() {
        return mText;
    }
}