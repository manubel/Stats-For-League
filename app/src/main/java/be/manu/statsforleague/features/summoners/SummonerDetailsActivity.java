package be.manu.statsforleague.features.summoners;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import be.manu.statsforleague.R;
import be.manu.statsforleague.data.model.SummonerDTO;
import be.manu.statsforleague.features.BaseActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class SummonerDetailsActivity extends BaseActivity implements SummonerDetailsContract.view {

    private static final String SUMMONER_NAME = "summoner_name";
    @BindView(R.id.summoner_name)
    TextView nameTextView;
    @BindView(R.id.summoner_level)
    TextView levelTextView;
    @BindView(R.id.summoner_account_id)
    TextView accountIdTextView;
    @BindView(R.id.summoner_profile_icon_id)
    TextView profileIconIdTextView;
    @BindView(R.id.summoner_summoner_id)
    TextView summonerIdTextView;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    private SummonerDetailsPresenter presenter;

    public static Intent getStartIntent(Context context, String summonerName) {
        Intent intent = new Intent(context, SummonerDetailsActivity.class);
        intent.putExtra(SUMMONER_NAME, summonerName);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summoners);
        ButterKnife.bind(this);
        presenter = new SummonerDetailsPresenter(this);

        presenter.getSummoner(getIntent().getStringExtra(SUMMONER_NAME));
    }

    @Override
    public void showSummoner(SummonerDTO summonerDTO) {
        nameTextView.setText(getString(R.string.summoner_name, summonerDTO.getName()));
        levelTextView.setText(getString(R.string.summoner_level, String.valueOf(summonerDTO.getSummonerLevel())));
        accountIdTextView.setText(getString(R.string.summoner_account_id, String.valueOf(summonerDTO.getAccountId())));
        profileIconIdTextView.setText(getString(R.string.summoner_summoner_id, String.valueOf(summonerDTO.getId())));
        summonerIdTextView.setText(getString(R.string.summoner_profile_icon_id, String.valueOf(summonerDTO.getProfileIconId())));
    }

    @Override
    public void showErrorToast(String error) {
        Toast.makeText(SummonerDetailsActivity.this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void startProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void endProgressBar() {
        progressBar.setVisibility(View.GONE);
    }
}
