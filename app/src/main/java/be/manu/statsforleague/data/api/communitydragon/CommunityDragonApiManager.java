package be.manu.statsforleague.data.api.communitydragon;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static be.manu.statsforleague.data.api.Constants.CD_API;

public enum CommunityDragonApiManager {
    INSTANCE;

    CommunityDragonApi api;

    CommunityDragonApiManager() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(CD_API)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(CommunityDragonApi.class);
    }

    public static CommunityDragonApiManager getInstance() {
        return INSTANCE;
    }

    public CommunityDragonApi getApiService() {
        return api;
    }
}
