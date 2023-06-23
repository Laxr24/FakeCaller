package com.example.fakecaller.Fragments;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import com.example.fakecaller.DataViewModel;
import com.example.fakecaller.R;
import com.example.fakecaller.Services.CounterService;

public class Frag_Caller_Screen extends Fragment {
    View fragment;
    ImageButton acceptBtn, rejectBtn;
    TextView counterTxt;
    Intent counterService;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragment = inflater.inflate(R.layout.frag_caller_ui, container, false);
        acceptBtn = fragment.findViewById(R.id.accept);
        rejectBtn = fragment.findViewById(R.id.reject);
        counterTxt = fragment.findViewById(R.id.inCallTime);
        counterService = new Intent(getActivity(), CounterService.class);

        setAction();

        return fragment;
    }

    private void setAction() {


        DataViewModel.counter.observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                int in = integer * 1000;
                long millis = in % 1000;
                long second = (in / 1000) % 60;
                long minute = (in / (1000 * 60)) % 60;
                long hour = (in / (1000 * 60 * 60)) % 24;
                String time = String.format("%02d:%02d", minute, second);
                String timeWithHour = String.format("%02d:%02d", hour, minute);
                if (in == 0) {
                    counterTxt.setVisibility(View.GONE);
                } else {
                    counterTxt.setVisibility(View.VISIBLE);
                    counterTxt.setText(hour > 0 ? timeWithHour : time);
                }
            }
        });

        DataViewModel.callEnded.observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                Log.i("msg", "Caller button state "+aBoolean);
                if(aBoolean){
                    acceptBtn.setVisibility(View.VISIBLE);
                } else {
                    acceptBtn.setVisibility(View.GONE);
                }
            }
        });

        rejectBtn.setOnClickListener(v -> {
            getContext().stopService(counterService);
            DataViewModel.callEnded.setValue(true);

        });
        acceptBtn.setOnClickListener(v -> {
            DataViewModel.callEnded.setValue(false);
            getContext().startService(counterService);
        });

        rejectBtn.setOnLongClickListener(v->{
            DataViewModel.showHomeScreen.setValue(true);
            return false;
        });
    }
}
