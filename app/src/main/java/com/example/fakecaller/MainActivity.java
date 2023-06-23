package com.example.fakecaller;

import android.app.ActionBar;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.fakecaller.Fragments.Frag_Caller_Screen;
import com.example.fakecaller.Fragments.Frag_Home;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "msg";
    // Initiate main UI elements
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new ViewModelProvider(this).get(DataViewModel.class);

        if(savedInstanceState==null){
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragmentContainer, Frag_Home.class, null)
                    .commit();

            DataViewModel.showHomeScreen.observe(this, new Observer<Boolean>() {
                @Override
                public void onChanged(Boolean aBoolean) {
                    if(!aBoolean){
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.fragmentContainer, new Frag_Caller_Screen())
                                .commit();
                    }else{
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.fragmentContainer, new Frag_Home())
                                .commit();
                    }
                }
            });
        }

    }


    @Override
    protected void onResume() {
        super.onResume();
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this)
                .setTitle("Want to quit?")
                .setPositiveButton("Yes", (dialog1, which) -> {
                    super.onBackPressed();
                })
                .setNegativeButton("No", ((dialog1, which) -> {

                }));
        dialog.show();
    }
}