package be.manu.statsforleague.features.main.fragments.summoner;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import be.manu.statsforleague.R;
import be.manu.statsforleague.features.summoners.SummonerDetailsActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class SummonerSearchFragment extends Fragment implements SummonerSearchContract.view {

    private static final String EMPTY = "";
    @BindView(R.id.usernameInput)
    EditText inputField;
    private Unbinder unbinder;
    private SummonerSearchContract.presenter presenter;

    public SummonerSearchFragment() {
    }

    public static SummonerSearchFragment newInstance() {
        return new SummonerSearchFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        presenter = new SummonerSearchPresenter(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search_summoner, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void navigateToSummonersActivity(String summonerName) {
        startActivity(SummonerDetailsActivity.getStartIntent(getContext(), summonerName));
    }

    @OnClick(R.id.submitUsernameButton)
    public void submit() {
        if (TextUtils.isEmpty(inputField.getText())) {
            inputField.setText(EMPTY);
        } else {
            presenter.summonersButtonClicked(inputField.getText().toString());
        }
    }
}
