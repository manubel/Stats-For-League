package be.manu.statsforleague.features.main;

public interface MainMVP {
    interface MainPresenter {
        void championsButtonClicked();
    }

    interface MainView{
        void displayChampionsMessage();
    }
}
