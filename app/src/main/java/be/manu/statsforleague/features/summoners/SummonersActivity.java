package be.manu.statsforleague.features.summoners;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import be.manu.statsforleague.R;
import be.manu.statsforleague.data.model.SummonerDTO;
import butterknife.ButterKnife;

public class SummonersActivity extends AppCompatActivity implements SummonersContract.view {

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
        Toast.makeText(SummonersActivity.this, summonerDTO.getName(), Toast.LENGTH_SHORT).show();
        System.out.println();
    }

    @Override
    public void showErrorToast(String error) {
        Toast.makeText(SummonersActivity.this, error, Toast.LENGTH_SHORT).show();
    }
}