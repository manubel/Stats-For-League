package be.manu.statsforleague.features.champions.search;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import be.manu.statsforleague.R;
import be.manu.statsforleague.data.model.ChampionDTO;
import be.manu.statsforleague.features.BaseActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChampionActivity extends BaseActivity implements ChampionContract.View {

    private static final String CHAMPION_ID = "champion_id";
    private static final String WIKI_BASE_URI = "http://leagueoflegends.wikia.com/wiki/";
    private ChampionContract.Presenter presenter;
    private ChampionDTO championDTO;

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
        presenter = new ChampionPresenter(this, getApplicationContext());

        int id = getIntent().getIntExtra(CHAMPION_ID, 0);
        presenter.getChampionById(id);
    }

    @OnClick(R.id.store_in_db_button)
    void storeInDb() {
        if (championDTO != null) {
            presenter.insertChampionInDb(championDTO);
        } else {
            Toast.makeText(this, "No champion loaded!", Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.check_db_count_button)
    void getCountFromDb() {
        presenter.getCountFromDb();
    }

    @OnClick(R.id.visit_champion_page)
    void visitChampionPage() {
        visitChampionWebsite();
    }

    @Override
    public void showChampionAddedMessage() {
        Toast.makeText(this, "Champion added to DB!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showChampion(ChampionDTO championDTO) {
        championNameTextView.setText(championDTO.getName());
        championTitleTextView.setText(championDTO.getTitle());
        championBioTextView.setText(championDTO.getShortBio());
    }

    @Override
    public void showChampionsFetchError() {
        Toast.makeText(this, "An error happened with the API", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setChampion(ChampionDTO championDTO) {
        this.championDTO = championDTO;
    }

    @Override
    public void showCountToast(int count) {
        Toast.makeText(this, "There are: " + count + " champions in the DB", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void visitChampionWebsite() {
        if (championDTO != null) {
            Uri webPage = Uri.parse(WIKI_BASE_URI + championDTO.getName());
            Intent intent = new Intent(Intent.ACTION_VIEW, webPage);
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }
        } else {
            Toast.makeText(this, "There was an error loading the champion info", Toast.LENGTH_SHORT).show();
        }
    }
}
