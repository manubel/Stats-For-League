package be.manu.statsforleague.features.champions;

public class ChampionsPresenter implements ChampionsContract.Presenter {

    private ChampionsContract.View view;

    ChampionsPresenter(ChampionsContract.View view) {
        this.view = view;
    }

    @Override
    public void floatingButtonClicked() {
        view.displayFloatingButtonMessage();
    }
}
