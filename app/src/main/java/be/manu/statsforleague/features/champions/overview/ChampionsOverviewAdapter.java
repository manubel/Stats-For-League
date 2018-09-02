package be.manu.statsforleague.features.champions.overview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

import be.manu.statsforleague.R;
import be.manu.statsforleague.data.model.ChampionDTO;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;

public class ChampionsOverviewAdapter extends RecyclerView.Adapter<ChampionsOverviewAdapter.OverviewViewHolder> {

    private final List<ChampionDTO> championList;
    public SparseBooleanArray selectedItems;

    public ChampionsOverviewAdapter(@NonNull List<ChampionDTO> championList) {
        this.championList = championList;
        selectedItems = new SparseBooleanArray();
    }

    @NonNull
    @Override
    public OverviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OverviewViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.champions_overview_list_item, parent, false), selectedItems);
    }

    @Override
    public void onBindViewHolder(@NonNull OverviewViewHolder holder, int position) {
        holder.textView.setText(championList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return championList.size();
    }

    public static class OverviewViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_item_champion)
        TextView textView;

        @BindView(R.id.brand_select)
        CheckBox checkBox;

        SparseBooleanArray sparseBooleanArray;

        public OverviewViewHolder(View itemView, SparseBooleanArray sparseBooleanArray) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.sparseBooleanArray = sparseBooleanArray;
        }

        @OnCheckedChanged(R.id.brand_select)
        public void checkbox(CheckBox cb, boolean checked) {
            cb.setText(checked ? "Checked" : "Unchecked");
            sparseBooleanArray.put(getAdapterPosition(), checked);
        }
    }
}
