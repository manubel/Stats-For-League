package be.manu.statsforleague.features.settings;

public class SettingsPresenter implements SettingsContract.presenter {

    private final SettingsContract.view view;

    public SettingsPresenter(SettingsContract.view view) {
        this.view = view;
    }
}
