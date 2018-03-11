package be.manu.statsforleague.features.Summoners;

import android.support.annotation.NonNull;
import android.util.Log;

import be.manu.statsforleague.data.api.retrofit.SummonerService;
import be.manu.statsforleague.data.api.retrofit.SummonerServiceClient;
import be.manu.statsforleague.data.model.SummonerDTO;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SummonersPresenter implements SummonersMVP.presenter {

    private final SummonersMVP.view view;

    private static final String ERROR_CALLING_API_MESSAGE = "Error calling api";
    private static final String ERROR_CALLING_API = "ERROR_CALLING_API";
    private static final String API_ERROR_MESSAGE = "API_ERROR_MESSAGE";

    SummonersPresenter(SummonersMVP.view view) {
        this.view = view;
    }

    @Override
    public void getSummoner(final String summonerName) {
        SummonerService summonerService = new SummonerServiceClient().getSummonerService();

        Call<SummonerDTO> call = summonerService.getSummonerByName(summonerName);

        call.enqueue(new Callback<SummonerDTO>() {
            @Override
            public void onResponse(@NonNull Call<SummonerDTO> call, @NonNull Response<SummonerDTO> response) {
                if (response.isSuccessful()) {
                    view.showSummoner(response.body());
                } else {
                    Log.d(API_ERROR_MESSAGE, response.message());
                    view.showErrorToast(response.message());
                }
            }

            @Override
            public void onFailure(@NonNull Call<SummonerDTO> call, @NonNull Throwable t) {
                Log.d(ERROR_CALLING_API, t.getMessage());
                view.showErrorToast(ERROR_CALLING_API_MESSAGE);
            }
        });


    }
}
