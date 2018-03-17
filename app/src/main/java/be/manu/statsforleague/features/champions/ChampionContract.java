package be.manu.statsforleague.features.champions;

import be.manu.statsforleague.data.model.ChampionDTO;

public interface ChampionContract {
    interface Presenter {
        void floatingButtonClicked();

        void getChampionById(int id);
    }

    interface View {
        void showFloatingButtonMessage();

        void showChampion(ChampionDTO championDTO);

        void showChampionsFetchError();
    }
}
