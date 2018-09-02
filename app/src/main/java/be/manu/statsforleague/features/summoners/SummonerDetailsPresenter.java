package be.manu.statsforleague.features.summoners;

import android.support.annotation.NonNull;
import android.util.Log;

import be.manu.statsforleague.data.api.riot.RiotApi;
import be.manu.statsforleague.data.api.riot.RiotApiManager;
import be.manu.statsforleague.data.model.SummonerDTO;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SummonerDetailsPresenter implements SummonerDetailsContract.presenter {

    private final SummonerDetailsContract.view view;

    private static final String ERROR_CALLING_API_MESSAGE = "Error calling api";
    private static final String ERROR_CALLING_API = "ERROR_CALLING_API";
    private static final String API_ERROR_MESSAGE = "API_ERROR_MESSAGE";

    SummonerDetailsPresenter(SummonerDetailsContract.view view) {
        this.view = view;
    }

    @Override
    public void getSummoner(final String summonerName) {
        view.startProgressBar();
        RiotApi riotApi = new RiotApiManager().getRiotApi();

        Call<SummonerDTO> call = riotApi.getSummonerByName(summonerName);

        call.enqueue(new Callback<SummonerDTO>() {
            @Override
            public void onResponse(@NonNull Call<SummonerDTO> call, @NonNull Response<SummonerDTO> response) {
                view.endProgressBar();
                if (response.isSuccessful()) {
                    view.showSummoner(response.body());
                } else {
                    Log.e(API_ERROR_MESSAGE, response.message());
                    view.showErrorToast(response.message());
                }
            }

            @Override
            public void onFailure(@NonNull Call<SummonerDTO> call, @NonNull Throwable t) {
                Log.e(ERROR_CALLING_API, t.getMessage());
                view.endProgressBar();
                view.showErrorToast(ERROR_CALLING_API_MESSAGE);
            }
        });


    }
}
