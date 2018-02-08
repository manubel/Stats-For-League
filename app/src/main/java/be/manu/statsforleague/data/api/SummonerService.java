package be.manu.statsforleague.data.api;

import be.manu.statsforleague.data.model.SummonerDTO;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface SummonerService {
    @GET("{summonerName}")
    Call<SummonerDTO> getSummonerByName(@Path("summonerName") String summonerName, @Query("api_key") String apiKey);
}
