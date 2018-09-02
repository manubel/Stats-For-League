package be.manu.statsforleague.features.champions.overview;

import android.content.Context;

import be.manu.statsforleague.data.database.DBHelper;
import be.manu.statsforleague.data.model.ChampionDTO;

public class ChampionsOverviewPresenter implements ChampionsOverviewContract.Presenter {
    private ChampionsOverviewContract.View view;
    private DBHelper dbHelper;

    public ChampionsOverviewPresenter(ChampionsOverviewContract.View view, Context context) {
        this.view = view;
        dbHelper = new DBHelper(context);
    }

    @Override
    public void getSavedChampions() {
        view.showChampions(dbHelper.getAllChampions());
    }

    @Override
    public void remove(ChampionDTO championDTO) {
        dbHelper.deleteChampion(championDTO.getId());
    }
}
