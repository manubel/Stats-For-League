package be.manu.statsforleague.features.main.fragments.champions;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import be.manu.statsforleague.R;
import be.manu.statsforleague.data.model.ChampionDTO;
import be.manu.statsforleague.features.champions.search.ChampionActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ChampionsRecyclerAdapter extends RecyclerView.Adapter<ChampionsRecyclerAdapter.ChampionsViewHolder> {

    private static final String BEGIN_URL = "http://cdn.communitydragon.org/8.5.2/champion/";
    private static final String END_URL = "/square";
    private final List<ChampionDTO> championList;


    ChampionsRecyclerAdapter(List<ChampionDTO> championList) {
        this.championList = championList;
        if (!championList.isEmpty()) {
            this.championList.remove(0);
        }
    }

    @NonNull
    @Override
    public ChampionsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ChampionsViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_tile, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ChampionsViewHolder holder, int position) {
        final ChampionDTO championDTO = championList.get(position);
        String url = BEGIN_URL + championDTO.getId() + END_URL;
        Picasso.get().load(url).into(holder.picture);
        holder.name.setText(championList.get(position).getName());

        final Context context = holder.itemView.getContext();

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(ChampionActivity.getStartIntent(context, championDTO.getId()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return championList.size();
    }

    public static class ChampionsViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tile_picture)
        ImageView picture;
        @BindView(R.id.tile_title)
        TextView name;

        ChampionsViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }
}
