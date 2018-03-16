package be.manu.statsforleague.features.champions;

public interface ChampionsContract {
    interface Presenter {
        void floatingButtonClicked();
    }

    interface View {
        void displayFloatingButtonMessage();
    }
}
