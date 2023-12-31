package com.example.fakecaller;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DataViewModel extends ViewModel {
    public static MutableLiveData<Integer> counter = new MutableLiveData<>();
    public static MutableLiveData<Boolean> callEnded = new MutableLiveData<>(true);
    public static MutableLiveData<Boolean> showHomeScreen = new MutableLiveData<>(true);
    public static MutableLiveData<Boolean> muteActive = new MutableLiveData<>(false);
    public static MutableLiveData<Boolean> videCallActive = new MutableLiveData<>(false);
    public static MutableLiveData<Boolean> speakerActive = new MutableLiveData<>(false);

}
