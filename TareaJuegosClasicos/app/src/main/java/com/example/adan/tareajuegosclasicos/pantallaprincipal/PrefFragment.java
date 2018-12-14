package com.example.adan.tareajuegosclasicos.pantallaprincipal;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;

import com.example.adan.tareajuegosclasicos.R;

public class PrefFragment extends PreferenceFragment
{
    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferencias);
    }
}
