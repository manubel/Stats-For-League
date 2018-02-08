package be.manu.statsforleague.features.main;

// TODO rename naar presenter en view in plaats van main ervoor
public interface MainMVP {
    interface MainPresenter {
        void championsButtonClicked();

        void summonersButtonClicked(String summonerName);
    }

    interface MainView {
        void navigateToChampionsActivity();

        void navigateToSummonersActivity(String summonerName);
    }
}
