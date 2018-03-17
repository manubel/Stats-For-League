package be.manu.statsforleague.features.main.fragments.summoner;

public class SummonerSearchPresenter implements SummonerSearchContract.presenter {

    private final SummonerSearchContract.view view;

    SummonerSearchPresenter(SummonerSearchContract.view view) {
        this.view = view;
    }

    @Override
    public void summonersButtonClicked(String summonerName) {
        view.navigateToSummonersActivity(summonerName);
    }
}
