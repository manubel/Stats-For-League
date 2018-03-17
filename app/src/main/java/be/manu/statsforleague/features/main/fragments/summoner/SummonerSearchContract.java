package be.manu.statsforleague.features.main.fragments.summoner;

public interface SummonerSearchContract {
    interface presenter {
        void summonersButtonClicked(String summonerName);
    }

    interface view {
        void navigateToSummonersActivity(String summonerName);
    }
}
