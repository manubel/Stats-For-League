package be.manu.statsforleague.features.main;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.Objects;

import be.manu.statsforleague.R;
import be.manu.statsforleague.features.BaseActivity;
import be.manu.statsforleague.features.champions.overview.ChampionsOverviewActivity;
import be.manu.statsforleague.features.main.fragments.champions.ChampionsFragment;
import be.manu.statsforleague.features.main.fragments.home.HomeFragment;
import be.manu.statsforleague.features.main.fragments.summoner.SummonerSearchFragment;
import be.manu.statsforleague.features.settings.SettingsActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    private static final String RED = "red";

    @BindView(R.id.bottom_navigation)
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setupNavigation();
        if (savedInstanceState == null) {
            // Manueel de eerste fragment tonen bij het initieel laden van de app
            executeFragmentTransaction(HomeFragment.newInstance());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.global_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent startSettingsActivity = new Intent(this, SettingsActivity.class);
            startActivity(startSettingsActivity);
            return true;
        }
        if (id == R.id.action_champions) {
            Intent startChampionsOverviewActivity = new Intent(this, ChampionsOverviewActivity.class);
            startActivity(startChampionsOverviewActivity);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupNavigation() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String themeColor = sharedPreferences.getString(getString(R.string.pref_color_key), getString(R.string.pref_color_value_purple));
        if (Objects.equals(themeColor, RED)) {
            bottomNavigationView.setItemBackgroundResource(R.color.red);
        } else {
            bottomNavigationView.setItemBackgroundResource(R.color.colorPrimary);
        }

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        Fragment selectedFragment = null;
                        switch (item.getItemId()) {
                            case R.id.action_home:
                                selectedFragment = HomeFragment.newInstance();
                                break;
                            case R.id.action_summoner:
                                selectedFragment = SummonerSearchFragment.newInstance();
                                break;
                            case R.id.action_info:
                                selectedFragment = ChampionsFragment.newInstance();
                                break;
                        }
                        executeFragmentTransaction(selectedFragment);
                        return true;
                    }
                });
    }

    private void executeFragmentTransaction(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, fragment);
        transaction.commit();
    }
}
