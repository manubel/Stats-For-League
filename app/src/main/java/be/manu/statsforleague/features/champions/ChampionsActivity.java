package be.manu.statsforleague.features.champions;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import be.manu.statsforleague.R;
import butterknife.BindView;
import butterknife.OnClick;

public class ChampionsActivity extends AppCompatActivity implements ChampionsMVP.View {

    ChampionsMVP.Presenter presenter;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_champions);
        setSupportActionBar(toolbar);

        presenter = new ChampionsPresenter(this);
    }

    @OnClick
    void clickFloatingActionButton() {
        presenter.floatingButtonClicked();
    }

    @Override
    public void displayFloatingButtonMessage() {
        Toast.makeText(this, "You clicked me", Toast.LENGTH_SHORT).show();
    }
}
