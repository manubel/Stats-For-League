package be.manu.statsforleague.features.main.fragments.champions;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import be.manu.statsforleague.R;
import be.manu.statsforleague.data.model.ChampionDTO;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ChampionsFragment extends Fragment implements ChampionsContract.View {

    @BindView(R.id.champions_recycler_view)
    RecyclerView recyclerView;
    private Unbinder unbinder;
    private ChampionsContract.Presenter presenter;

    public ChampionsFragment() {
    }

    public static ChampionsFragment newInstance() {
        return new ChampionsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_champions, container, false);
        unbinder = ButterKnife.bind(this, view);
        presenter = new ChampionsPresenter(this);
        presenter.fetchChampionList();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void showChampionsList(@NonNull List<ChampionDTO> championList) {
        recyclerView.setAdapter(new ChampionsRecyclerAdapter(championList));
        recyclerView.setHasFixedSize(true);
        // Set padding for Tiles
        int tilePadding = getResources().getDimensionPixelSize(R.dimen.tile_padding);
        recyclerView.setPadding(tilePadding, tilePadding, tilePadding, tilePadding);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
    }

    @Override
    public void showChampionsFetchError() {

    }
}


