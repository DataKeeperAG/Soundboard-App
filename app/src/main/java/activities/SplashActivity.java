package activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;
//import androidx.preference.PreferenceManager;

import com.example.soundboard.R;

import lib.Utils;


public class SplashActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PreferenceManager.setDefaultValues(this, R.xml.root_preferences, false);
        Utils.setNightModeOnOffFromPreferenceValue(getApplicationContext(), getString(R.string.dark_mode_key));

        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();

    }

}