package be.manu.statsforleague.data.api.retrofit;

import be.manu.statsforleague.data.api.Constants;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SummonerServiceClient {

    private SummonerService summonerService;

    public SummonerServiceClient() {
        initRetrofit();
    }

    private void initRetrofit() {
        Retrofit retrofit = retrofitBuilder();
        summonerService = retrofit.create(SummonerService.class);
    }

    private Retrofit retrofitBuilder() {
        return new Retrofit.Builder()
                .baseUrl(Constants.RIOT_API)
                .addConverterFactory(GsonConverterFactory.create())
                .client(getOkHttpClient())
                .build();
    }

    private OkHttpClient getOkHttpClient() {
        OkHttpClient.Builder client = new OkHttpClient.Builder();

        HeaderInterceptor headerInterceptor = new HeaderInterceptor();
        client.addInterceptor(headerInterceptor);

        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
        client.addInterceptor(httpLoggingInterceptor);

        return client.build();
    }

    public SummonerService getSummonerService() {
        return summonerService;
    }
}
