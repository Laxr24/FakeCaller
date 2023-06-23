package com.example.fakecaller.Services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.fakecaller.DataViewModel;

public class CounterService extends Service {
    private static final String TAG = "msg";
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        int input = 120;
        new Thread(()->{
            int i = 0;

            while(!DataViewModel.callEnded.getValue()){
                if(DataViewModel.callEnded.getValue()){
                    Log.i(TAG, "Call ended "+i);
                    stopSelf();
                    return;
                }
                DataViewModel.counter.postValue(i);
                Log.i(TAG, "Counter: "+i+" "+Thread.currentThread().getName());
                try {
                    Thread.sleep(1200);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                i++;
            }
        }).start();
        return  START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        DataViewModel.counter.setValue(0);
        Log.i(TAG, "Service destroyed "+Thread.currentThread().getName());
    }
}
