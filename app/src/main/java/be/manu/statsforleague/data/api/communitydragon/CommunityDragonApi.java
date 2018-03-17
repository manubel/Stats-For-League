package be.manu.statsforleague.data.api.communitydragon;

import java.util.List;

import be.manu.statsforleague.data.model.ChampionDTO;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CommunityDragonApi {
    @GET("champions/{summonerId}.json")
    Call<ChampionDTO> getchampionById(@Path("summonerId") int id);

    @GET("champion-summary.json")
    Call<List<ChampionDTO>> getChampionList();

}