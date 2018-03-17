package be.manu.statsforleague.features.champions;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import be.manu.statsforleague.R;
import be.manu.statsforleague.data.model.ChampionDTO;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChampionActivity extends AppCompatActivity implements ChampionContract.View {

    private static final String CHAMPION_ID = "champion_id";
    ChampionContract.Presenter presenter;
    @BindView(R.id.champion_name)
    TextView championNameTextView;
    @BindView(R.id.champion_title)
    TextView championTitleTextView;
    @BindView(R.id.champion_bio)
    TextView championBioTextView;

    public static Intent getStartIntent(Context context, int id) {
        Intent intent = new Intent(context, ChampionActivity.class);
        intent.putExtra(CHAMPION_ID, id);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_champions);
        ButterKnife.bind(this);
        presenter = new ChampionPresenter(this);

        int id = getIntent().getIntExtra(CHAMPION_ID, 0);
        presenter.getChampionById(id);
    }

    @OnClick(R.id.fab)
    void clickFloatingActionButton() {
        presenter.floatingButtonClicked();
    }

    @Override
    public void showFloatingButtonMessage() {
        Toast.makeText(this, "You clicked me", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showChampion(ChampionDTO championDTO) {
        championNameTextView.setText(championDTO.getName());
        championTitleTextView.setText(championDTO.getTitle());
        championBioTextView.setText(championDTO.getShortBio());
    }

    @Override
    public void showChampionsFetchError() {

    }
}
