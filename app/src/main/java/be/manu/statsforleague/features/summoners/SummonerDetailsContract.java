package be.manu.statsforleague.features.summoners;

import be.manu.statsforleague.data.model.SummonerDTO;

public interface SummonerDetailsContract {
    interface presenter {
        void getSummoner(String summonerName);
    }

    interface view {
        void showSummoner(SummonerDTO summonerDTO);

        void showErrorToast(String error);

        void startProgressBar();

        void endProgressBar();
    }
}
