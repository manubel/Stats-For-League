package be.manu.statsforleague.features.main.fragments.champions;

import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.List;

import be.manu.statsforleague.data.api.communitydragon.CommunityDragonApi;
import be.manu.statsforleague.data.api.communitydragon.CommunityDragonApiManager;
import be.manu.statsforleague.data.model.ChampionDTO;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChampionsPresenter implements ChampionsContract.Presenter {

    private static final String ERROR = "ERROR";
    private final ChampionsContract.View view;
    private CommunityDragonApi api;

    ChampionsPresenter(ChampionsContract.View view) {
        this.view = view;
        api = CommunityDragonApiManager.getInstance().getApiService();
    }

    @Override
    public void fetchChampionList(final Parcelable listState) {
        api.getChampionList()
                .enqueue(new Callback<List<ChampionDTO>>() {
                    @Override
                    public void onResponse(@NonNull Call<List<ChampionDTO>> call, @NonNull Response<List<ChampionDTO>> response) {
                        if (response.isSuccessful()) {
                            List<ChampionDTO> championDTOList = response.body();
                            if (championDTOList != null && !championDTOList.isEmpty()) {
                                view.showChampionsList(championDTOList, listState);
                            } else {
                                Log.e(ERROR, response.message());
                                view.showChampionsFetchError();
                            }
                        } else {
                            Log.e(ERROR, "API Call failed");
                            view.showChampionsFetchError();
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<List<ChampionDTO>> call, @NonNull Throwable t) {
                        Log.e(ERROR, "API Call failed");
                        view.showChampionsFetchError();
                    }
                });
    }
}
