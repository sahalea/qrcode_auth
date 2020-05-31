package com.sahel.qrauth.ui.fragment;

import android.os.Bundle;
import android.widget.TextView;

import com.sahel.qrauth.rest.entity.response.LoginResponse;
import com.sahel.qrauthenticator.R;

import butterknife.BindView;

/**
 * The class HomeFragment
 *
 * @author Sahel
 * @version 1.0
 * @since 30 May 2020
 */
public class HomeFragment extends BaseFragment {

    @BindView(R.id.tvStatus)
    TextView tvStatus;
    @BindView(R.id.tvName)
    TextView tvName;


    public static HomeFragment newInstance() {
        Bundle args = new Bundle();
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initializeUi() {

    }

    @Override
    protected void setEventListeners() {

    }

    @Override
    protected void doTask() {
        showDetails();
    }

    private void showDetails() {
        LoginResponse.User user = dataProcessController.getDataRepository().getUser();
        tvStatus.setText(getString(R.string.status_success));
        tvName.setText(user.getName());
    }

}
