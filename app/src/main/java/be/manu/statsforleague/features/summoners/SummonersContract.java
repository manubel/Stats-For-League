package be.manu.statsforleague.features.summoners;

import be.manu.statsforleague.data.model.SummonerDTO;

public interface SummonersContract {
    interface presenter {
        void getSummoner(String summonerName);
    }

    interface view {
        void showSummoner(SummonerDTO summonerDTO);

        void showErrorToast(String error);
    }
}
