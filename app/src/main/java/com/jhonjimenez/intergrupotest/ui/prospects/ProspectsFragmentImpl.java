package com.jhonjimenez.intergrupotest.ui.prospects;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.jhonjimenez.intergrupotest.R;
import com.jhonjimenez.intergrupotest.app.App;
import com.jhonjimenez.intergrupotest.utils.Messages;
import com.jhonjimenez.intergrupotest.utils.RetrofitError;

import javax.inject.Inject;

public class ProspectsFragmentImpl extends Fragment implements ProspectsMVP.View {

    @BindView(R.id.recycler)
    RecyclerView recyclerView;
    @BindView(R.id.empty_state)
    TextView empty_state;

    private Unbinder unbinder;

    @Inject
    ProspectsMVP.Presenter presenter;

    public ProspectsFragmentImpl() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_prospects, container, false);
        unbinder = ButterKnife.bind(this, view);
        presenter.setView(this);
        return view;
    }


    @Override
    public void onStart() {
        super.onStart();
        presenter.getProspects();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        presenter.dispose();
    }

    @Override
    public void onAttach(Context context) {
        ((App) context.getApplicationContext()).getComponent().inject(this);
        super.onAttach(context);
    }

    @Override
    public void showProgressDialog(String title, String message) {
        Messages.showProgressDialog(getActivity(), title, message);
    }

    @Override
    public void hideProgressDialog() {
        Messages.dismissProgressDialog();
    }

    @Override
    public void showError(RetrofitError objectError) {
        if (getActivity() != null) {
            Messages.showCustomToast(getActivity(), 1, objectError.getError());
        }
    }
}
