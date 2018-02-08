package be.manu.statsforleague.features.Summoners;

import be.manu.statsforleague.data.api.SummonerService;
import be.manu.statsforleague.data.model.SummonerDTO;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SummonersPresenter implements SummonersMVP.presenter {

    private static final String SUMMONER_API_URL = "https://euw1.api.riotgames.com/lol/summoner/v3/summoners/by-name/";
    private final SummonersMVP.view view;

    public SummonersPresenter(SummonersMVP.view view) {
        this.view = view;
    }

    @Override
    public void getSummoner(final String summonerName) {
        // TODO refactoren

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(SUMMONER_API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        SummonerService summonerService = retrofit.create(SummonerService.class);

        // TODO API Key exporteren naar XML resource file
        Call<SummonerDTO> call = summonerService.getSummonerByName(summonerName, "RGAPI-e083348a-f9a3-4090-8282-3a74da8cf829");

        call.enqueue(new Callback<SummonerDTO>() {
            @Override
            public void onResponse(Call<SummonerDTO> call, Response<SummonerDTO> response) {
                view.showSummoner(response.body());
            }

            @Override
            public void onFailure(Call<SummonerDTO> call, Throwable t) {
                // TODO navigeer naar error scherm
                System.out.println();
            }
        });
    }
}
