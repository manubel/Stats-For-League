package be.manu.statsforleague.features.Summoners;

import be.manu.statsforleague.data.model.SummonerDTO;

public interface SummonersMVP {
    interface presenter {
        void getSummoner(String summonerName);
    }

    interface view {
        void showSummoner(SummonerDTO summonerDTO);
    }
}
