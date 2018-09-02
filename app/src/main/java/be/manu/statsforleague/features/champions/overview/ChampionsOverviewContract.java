package be.manu.statsforleague.features.champions.overview;

import java.util.List;

import be.manu.statsforleague.data.model.ChampionDTO;

interface ChampionsOverviewContract {
    interface View {
        void showChampions(List<ChampionDTO> championDTOList);
    }

    interface Presenter {
        void getSavedChampions();

        void remove(ChampionDTO championDTO);
    }
}
