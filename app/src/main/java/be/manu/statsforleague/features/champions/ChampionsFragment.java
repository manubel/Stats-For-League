package be.manu.statsforleague.features.champions;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import be.manu.statsforleague.R;

public class ChampionsFragment extends Fragment {


    public ChampionsFragment() {
    }

    public static ChampionsFragment newInstance() {
        return new ChampionsFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_champion_info, container, false);
    }

}
