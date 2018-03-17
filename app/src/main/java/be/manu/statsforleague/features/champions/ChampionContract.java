package be.manu.statsforleague.features.champions;

import be.manu.statsforleague.data.model.ChampionDTO;

public interface ChampionContract {
    interface Presenter {
        void insertChampionInDb(ChampionDTO championDTO);

        void getChampionById(int id);

        void getCountFromDb();
    }

    interface View {
        void showChampionAddedMessage();

        void showChampion(ChampionDTO championDTO);

        void showChampionsFetchError();

        void setChampion(ChampionDTO championDTO);

        void showCountToast(int i);
    }
}
