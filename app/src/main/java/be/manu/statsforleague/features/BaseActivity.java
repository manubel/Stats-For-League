package be.manu.statsforleague.features;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.preference.PreferenceManager;

import java.util.Objects;

import be.manu.statsforleague.R;

public abstract class BaseActivity extends AppCompatActivity {

    private static final String RED = "red";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String themeColor = sharedPreferences.getString(getString(R.string.pref_color_key), getString(R.string.pref_color_value_purple));
        if (Objects.equals(themeColor, RED)) {
            getApplication().setTheme(R.style.AppThemeRed);
            setTheme(R.style.AppThemeRed);
        } else {
            getApplication().setTheme(R.style.AppTheme);
            setTheme(R.style.AppTheme);
        }

        super.onCreate(savedInstanceState);
    }
}
