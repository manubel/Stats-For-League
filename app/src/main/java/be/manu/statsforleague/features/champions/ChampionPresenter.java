package be.manu.statsforleague.features.champions;

import android.support.annotation.NonNull;
import android.util.Log;

import be.manu.statsforleague.data.api.communitydragon.CommunityDragonApi;
import be.manu.statsforleague.data.api.communitydragon.CommunityDragonApiManager;
import be.manu.statsforleague.data.model.ChampionDTO;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChampionPresenter implements ChampionContract.Presenter {

    private ChampionContract.View view;
    private CommunityDragonApi api;

    ChampionPresenter(ChampionContract.View view) {
        this.view = view;
        api = CommunityDragonApiManager.getInstance().getApiService();
    }

    @Override
    public void floatingButtonClicked() {
        view.showFloatingButtonMessage();
    }

    @Override
    public void getChampionById(int id) {
        api.getchampionById(id)
                .enqueue(new Callback<ChampionDTO>() {
                    @Override
                    public void onResponse(@NonNull Call<ChampionDTO> call, @NonNull Response<ChampionDTO> response) {
                        if (response.isSuccessful()) {
                            view.showChampion(response.body());
                        } else {
                            Log.e("UNSUCCESFULL_GET", response.message());
                            view.showChampionsFetchError();
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<ChampionDTO> call, @NonNull Throwable t) {
                        Log.e("API_CALL_ERROR", t.getMessage());
                        view.showChampionsFetchError();
                    }
                });
    }
}
