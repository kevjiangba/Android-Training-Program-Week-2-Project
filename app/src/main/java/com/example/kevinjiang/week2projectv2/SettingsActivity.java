package com.example.kevinjiang.week2projectv2;

import android.content.SharedPreferences;
import android.preference.PreferenceFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        getFragmentManager().beginTransaction().replace(android.R.id.content, new PreferenceScreenFragment()).commit();
        //
        //SharedPreferences preferences = getPreferenceScreen().getSharedPreferences();
        //preferences.registerOnSharedPreferenceChangeListener(SettingsActivity.this);
    }

    public static class PreferenceScreenFragment extends PreferenceFragment {
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.preferences);
        }
    }
}
