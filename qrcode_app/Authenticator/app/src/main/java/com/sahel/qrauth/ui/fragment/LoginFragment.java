package com.sahel.qrauth.ui.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.sahel.qrauth.AppConstants;
import com.sahel.qrauth.event.EventToHome;
import com.sahel.qrauth.event.EventToSignUp;
import com.sahel.qrauth.presenter.LoginPresenter;
import com.sahel.qrauth.ui.dialog.ProgressDialogHelper;
import com.sahel.qrauth.ui.dialog.ToastHelper;
import com.sahel.qrauth.view.LoginView;
import com.sahel.qrauthenticator.R;

import org.greenrobot.eventbus.EventBus;

import butterknife.OnClick;

/**
 * The class LoginFragment
 *
 * @author Sahel
 * @version 1.0
 * @since 30 May 2020
 */
public class LoginFragment extends BaseFragment implements LoginView {


    private LoginPresenter loginPresenter;
    private ProgressDialogHelper progressDialog;

    private final int ACTIVITY_REQUEST_CODE = 124;


    public static LoginFragment newInstance() {
        Bundle args = new Bundle();
        LoginFragment fragment = new LoginFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginPresenter = new LoginPresenter();
        progressDialog = new ProgressDialogHelper();
        progressDialog.create(getContext());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        loginPresenter.onDeAttachView();
    }

    @Override
    public void onDestroy() {
        progressDialog.destroy();
        super.onDestroy();
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_login;
    }

    @Override
    protected void initializeUi() {
        loginPresenter.onAttachView(this);
    }

    @Override
    protected void setEventListeners() {

    }

    @Override
    protected void doTask() {

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ACTIVITY_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                showLoading();
                String result = data.getStringExtra(AppConstants.QR_RESULT_KEY);
                loginPresenter.login(result);
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                ToastHelper.show("Scanning cancelled");
            }
        }
    }

    @Override
    public void showLoginStatus(boolean status, String description) {
        hideLoading();
        if (status) {
            EventBus.getDefault().post(new EventToHome());
        } else {
            ToastHelper.show(description);
        }
    }

    @Override
    public void showLoading() {
        if (progressDialog != null) {
            progressDialog.showProgressDialog();
        }
    }

    @Override
    public void hideLoading() {
        if (progressDialog != null) {
            progressDialog.hideProgressDialog();
        }
    }


    @OnClick(R.id.btLogin)
    public void onClickLogin() {
        dataProcessController.getNavigator().navigateToQRScanner(this, ACTIVITY_REQUEST_CODE);
    }

    @OnClick(R.id.btSignUp)
    public void onClickSignUp() {
        EventBus.getDefault().post(new EventToSignUp());
    }


}
