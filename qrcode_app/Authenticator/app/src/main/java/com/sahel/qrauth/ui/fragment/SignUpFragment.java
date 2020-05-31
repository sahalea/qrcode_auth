package com.sahel.qrauth.ui.fragment;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.widget.EditText;

import com.sahel.qrauth.event.EventToQRCode;
import com.sahel.qrauth.presenter.SignUpPresenter;
import com.sahel.qrauth.rest.entity.request.SignUpRequest;
import com.sahel.qrauth.ui.dialog.ProgressDialogHelper;
import com.sahel.qrauth.ui.dialog.ToastHelper;
import com.sahel.qrauth.view.SignUpView;
import com.sahel.qrauthenticator.R;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * The class SignUpFragment
 *
 * @author Sahel
 * @version 1.0
 * @since 30 May 2020
 */
public class SignUpFragment extends BaseFragment implements SignUpView {

    @BindView(R.id.etName)
    EditText etName;
    @BindView(R.id.etUserName)
    EditText etUserName;
    @BindView(R.id.etPasswordLayout)
    TextInputLayout etPasswordLayout;
    @BindView(R.id.etPassword)
    TextInputEditText etPassword;


    private SignUpPresenter signUpPresenter;
    private ProgressDialogHelper progressDialog;

    public static SignUpFragment newInstance() {
        Bundle args = new Bundle();
        SignUpFragment fragment = new SignUpFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        signUpPresenter = new SignUpPresenter();
        progressDialog = new ProgressDialogHelper();
        progressDialog.create(getContext());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        signUpPresenter.onDeAttachView();
    }

    @Override
    public void onDestroy() {
        progressDialog.destroy();
        super.onDestroy();
    }


    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_sign_up;
    }

    @Override
    protected void initializeUi() {
        signUpPresenter.onAttachView(this);
    }

    @Override
    protected void setEventListeners() {

    }

    @Override
    protected void doTask() {

    }

    @Override
    public void showSignUpStatus(boolean status, String description) {
        hideLoading();
        if (status) {
            EventBus.getDefault().post(new EventToQRCode());
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

    private boolean validateInputFields() {
        boolean isValid = true;
        etPasswordLayout.setError("");
        String name = etName.getText().toString();
        String userName = etUserName.getText().toString();
        String password = etPassword.getText().toString();
        if (TextUtils.isEmpty(name)) {
            isValid = false;
            etName.setError(getString(R.string.error_name));
        }
        if (TextUtils.isEmpty(userName)) {
            isValid = false;
            etUserName.setError(getString(R.string.error_user_name));
        }
        if (password.length() < 6) {
            isValid = false;
            etPasswordLayout.setError(getString(R.string.error_password));
        }
        return isValid;
    }

    private SignUpRequest getSigUpDetails() {
        String name = etName.getText().toString();
        String userName = etUserName.getText().toString();
        String password = etPassword.getText().toString();
        SignUpRequest signUpRequest = new SignUpRequest();
        signUpRequest.setName(name);
        signUpRequest.setUsername(userName);
        signUpRequest.setPassword(password);
        return signUpRequest;
    }

    @OnClick(R.id.btSignUp)
    public void onClickSignUp() {
        if (validateInputFields()) {
            showLoading();
            signUpPresenter.signUp(getSigUpDetails());
        }
    }
}
