package be.manu.statsforleague.features.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import be.manu.statsforleague.R;
import be.manu.statsforleague.features.Summoners.SummonersActivity;
import be.manu.statsforleague.features.champions.ChampionsActivity;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements MainMVP.MainView {

    private MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        presenter = new MainPresenter(this);
    }

    @OnClick(R.id.championsButton)
    void clickChampionsButton() {
        presenter.championsButtonClicked();
    }

    @OnClick(R.id.summonersButton)
    void clickSummonerButton() {
        // TODO hardcoded vervangen door data uit layout
        presenter.summonersButtonClicked("furion");
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

}
