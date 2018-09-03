package be.manu.statsforleague.features.main.fragments.champions;


import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.Collections;
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

    private final String RECYCLER_POSITION_KEY = "recycler_position";

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
        setupRecyclerView();
        presenter = new ChampionsPresenter(this);
        if (savedInstanceState != null) {
            Parcelable listState = savedInstanceState.getParcelable(RECYCLER_POSITION_KEY);
            presenter.fetchChampionList(listState);
        } else {
            presenter.fetchChampionList(null);
        }
        return view;
    }

    private void setupRecyclerView() {
        recyclerView.setAdapter(new ChampionsRecyclerAdapter(Collections.<ChampionDTO>emptyList()));
        recyclerView.setHasFixedSize(true);
        // Set padding for Tiles
        int tilePadding = getResources().getDimensionPixelSize(R.dimen.tile_padding);
        recyclerView.setPadding(tilePadding, tilePadding, tilePadding, tilePadding);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void showChampionsList(@NonNull List<ChampionDTO> championList, Parcelable listState) {
        recyclerView.setAdapter(new ChampionsRecyclerAdapter(championList));
        if (listState != null) {
            recyclerView.getLayoutManager().onRestoreInstanceState(listState);
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Parcelable listState = recyclerView.getLayoutManager().onSaveInstanceState();
        outState.putParcelable(RECYCLER_POSITION_KEY, listState);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (savedInstanceState != null) {
            Parcelable listState = savedInstanceState.getParcelable(RECYCLER_POSITION_KEY);
            recyclerView.getLayoutManager().onRestoreInstanceState(listState);
        }
    }

    @Override
    public void showChampionsFetchError() {
        Toast.makeText(getActivity(), "Error retrieving champions from webservice!", Toast.LENGTH_SHORT).show();
    }
}


