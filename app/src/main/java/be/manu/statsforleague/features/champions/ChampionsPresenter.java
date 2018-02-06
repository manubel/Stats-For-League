package be.manu.statsforleague.features.champions;

public class ChampionsPresenter implements ChampionsMVP.Presenter {

    private ChampionsMVP.View view;

    ChampionsPresenter(ChampionsMVP.View view) {
        this.view = view;
    }

    @Override
    public void floatingButtonClicked() {
        view.displayFloatingButtonMessage();
    }
}
