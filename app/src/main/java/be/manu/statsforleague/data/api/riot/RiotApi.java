package be.manu.statsforleague.data.api.riot;

import be.manu.statsforleague.data.model.SummonerDTO;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RiotApi {
    @GET("/lol/summoner/v3/summoners/by-name/{summonerName}")
    Call<SummonerDTO> getSummonerByName(@Path("summonerName") String summonerName);
}
