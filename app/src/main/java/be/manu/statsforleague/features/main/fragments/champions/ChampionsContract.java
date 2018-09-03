package be.manu.statsforleague.features.main.fragments.champions;

import android.os.Parcelable;
import android.support.annotation.NonNull;

import java.util.List;

import be.manu.statsforleague.data.model.ChampionDTO;

public interface ChampionsContract {
    interface Presenter {
        void fetchChampionList(Parcelable listState);
    }

    interface View {
        void showChampionsList(@NonNull List<ChampionDTO> championList, Parcelable liststate);

        void showChampionsFetchError();
    }
}
