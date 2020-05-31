package com.sahel.qrauth.presenter;

import com.sahel.qrauth.rest.ApiConstants;
import com.sahel.qrauth.rest.entity.request.LoginRequest;
import com.sahel.qrauth.rest.entity.response.LoginResponse;
import com.sahel.qrauth.view.LoginView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * The class LoginPresenter
 *
 * @author Sahel
 * @version 1.0
 * @since 30 May 2020
 */
public class LoginPresenter implements Presenter<LoginView> {

    private LoginView loginView;


    public void login(String userId) {
        LoginRequest loginRequest = new LoginRequest(userId);
        final Call<LoginResponse> apiCall = dataProcessController.getRestApiClient().login(loginRequest);
        apiCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (loginView != null) {
                    if (response.isSuccessful()) {
                        LoginResponse apiResponse = response.body();
                        if (apiResponse.isSuccess()) {
                            dataProcessController.getDataRepository().setUser(apiResponse.getData());
                            loginView.showLoginStatus(true, "");
                        } else {
                            loginView.showLoginStatus(false, apiResponse.getError());
                        }
                    } else {
                        loginView.showLoginStatus(false, ApiConstants.REST_ERROR_SERVER);
                    }
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                if (loginView != null) {
                    loginView.showLoginStatus(false, ApiConstants.REST_ERROR_NETWORK);
                }
            }
        });
    }


    @Override
    public void onAttachView(LoginView view) {
        loginView = view;
    }

    @Override
    public void onDeAttachView() {
        loginView = null;
    }
}
