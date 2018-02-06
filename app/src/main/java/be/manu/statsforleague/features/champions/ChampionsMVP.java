package be.manu.statsforleague.features.champions;

public interface ChampionsMVP {
    interface Presenter {
        void floatingButtonClicked();
    }

    interface View {
        void displayFloatingButtonMessage();
    }
}
