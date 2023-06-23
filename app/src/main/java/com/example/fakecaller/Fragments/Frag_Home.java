package com.example.fakecaller.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.fakecaller.DataViewModel;
import com.example.fakecaller.R;

public class Frag_Home extends Fragment {
    View fragment;
    Button callerScreenBtn, scheduleBtn;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragment = inflater.inflate(R.layout.frag_home, container, false);
        callerScreenBtn = fragment.findViewById(R.id.callScreenBtn);
        scheduleBtn = fragment.findViewById(R.id.scheduleBtn);
        actions();
        return fragment;
    }

    private void actions(){
        callerScreenBtn.setOnClickListener(v->{
            DataViewModel.showHomeScreen.setValue(false);
        });
    }
}
