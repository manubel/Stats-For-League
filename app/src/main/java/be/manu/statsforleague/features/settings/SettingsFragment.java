package be.manu.statsforleague.features.settings;

import android.os.Bundle;
import android.support.v7.preference.PreferenceFragmentCompat;

import be.manu.statsforleague.R;

public class SettingsFragment extends PreferenceFragmentCompat {
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.pref_main);
    }
}
