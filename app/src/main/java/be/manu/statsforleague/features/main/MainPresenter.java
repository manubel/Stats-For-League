package be.manu.statsforleague.features.main;

public class MainPresenter implements MainMVP.MainPresenter {

    private final MainMVP.MainView view;

    MainPresenter(MainMVP.MainView view) {
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
