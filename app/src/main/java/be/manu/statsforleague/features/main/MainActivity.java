package be.manu.statsforleague.features.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import be.manu.statsforleague.R;
import be.manu.statsforleague.features.champions.ChampionsActivity;
import be.manu.statsforleague.features.champions.ChampionsFragment;
import be.manu.statsforleague.features.summoners.SummonersActivity;
import be.manu.statsforleague.features.summoners.SummonersFragment;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainContract.MainView {

    private MainPresenter presenter;

    @BindView(R.id.bottom_navigation)
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        presenter = new MainPresenter(this);
        setupNavigation();
    }

    @Override
    public void navigateToChampionsActivity() {
        Intent intent = new Intent(MainActivity.this, ChampionsActivity.class);
        startActivity(intent);
    }

    @Override
    public void navigateToSummonersActivity(String summonerName) {
        startActivity(SummonersActivity.getStartIntent(this, summonerName));
    }

    private void setupNavigation() {
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
                                selectedFragment = SummonersFragment.newInstance();
                                break;
                            case R.id.action_info:
                                selectedFragment = ChampionsFragment.newInstance();
                                break;
                        }
                        executeFragmentTransaction(selectedFragment);
                        return true;
                    }
                });

        // Manueel de eerste fragment tonen bij het initieel laden van de app
        executeFragmentTransaction(HomeFragment.newInstance());
    }

    private void executeFragmentTransaction(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, fragment);
        transaction.commit();
    }
}
