package be.manu.statsforleague.features.main;

public class MainPresenter implements MainContract.MainPresenter {

    private final MainContract.MainView view;

    MainPresenter(MainContract.MainView view) {
        this.view = view;
    }

    @Override
    public void championsButtonClicked() {
        view.navigateToChampionsActivity();
    }

    @Override
    public void summonersButtonClicked(String summonerName) {
        view.navigateToSummonersActivity(summonerName);
    }
}
