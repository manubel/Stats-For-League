package be.manu.statsforleague.features.Summoners;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import be.manu.statsforleague.R;
import be.manu.statsforleague.data.model.SummonerDTO;
import butterknife.ButterKnife;

public class SummonersActivity extends AppCompatActivity implements SummonersMVP.view {

    private static final String SUMMONER_NAME = "summoner_name";
    private SummonersPresenter presenter;
    private String summonerName;

    public static Intent getStartIntent(Context context, String summonerName) {
        Intent intent = new Intent(context, SummonersActivity.class);
        intent.putExtra(SUMMONER_NAME, summonerName);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summoners);
        ButterKnife.bind(this);

        summonerName = getIntent().getStringExtra(SUMMONER_NAME);

        presenter = new SummonersPresenter(this);
        presenter.getSummoner(summonerName);
    }

    @Override
    public void showSummoner(SummonerDTO summonerDTO) {
        // TODO set layout
        System.out.println();
    }
}
