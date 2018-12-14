package com.example.adan.tareajuegosclasicos.pantallaprincipal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.adan.tareajuegosclasicos.R;

public class PrefActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pref);

        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new PrefFragment())
                .commit();
    }
}
