package be.manu.statsforleague.features.champions.overview;

import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;

import be.manu.statsforleague.R;
import be.manu.statsforleague.data.model.ChampionDTO;
import be.manu.statsforleague.features.BaseActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChampionsOverviewActivity extends BaseActivity implements ChampionsOverviewContract.View {

    @BindView(R.id.recyclerview_champions_overview)
    RecyclerView recyclerView;
    private ChampionsOverviewContract.Presenter presenter;
    private ChampionsOverviewAdapter adapter;
    private List<ChampionDTO> championDTOS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_champions_overview);
        ButterKnife.bind(this);
        presenter = new ChampionsOverviewPresenter(this, getApplicationContext());
        presenter.getSavedChampions();
        setupRecyclerView();

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public void showChampions(List<ChampionDTO> championDTOList) {
        championDTOS = championDTOList;
        adapter = new ChampionsOverviewAdapter(championDTOS);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            NavUtils.navigateUpFromSameTask(this);
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
    }

    @OnClick(R.id.floatingActionButton)
    public void deleteClicked() {
        SparseBooleanArray selectedItems = adapter.selectedItems;
        if (selectedItems.size() < 1) {
            Toast.makeText(this, "There are no items to remove", Toast.LENGTH_SHORT).show();
            return;
        }
        for (int i = 0, arraySize = selectedItems.size(); i < arraySize; i++) {
            if (selectedItems.get(selectedItems.keyAt(i))) {
                presenter.remove(championDTOS.get(selectedItems.keyAt(i)));
            }
        }
        recreate();
    }
}
