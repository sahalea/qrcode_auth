package com.sahel.qrauth.presenter;

import com.sahel.qrauth.rest.ApiConstants;
import com.sahel.qrauth.rest.entity.request.SignUpRequest;
import com.sahel.qrauth.rest.entity.response.SignUpResponse;
import com.sahel.qrauth.view.SignUpView;

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
public class SignUpPresenter implements Presenter<SignUpView> {

    private SignUpView signUpView;


    public void signUp(SignUpRequest signUpRequest) {
        final Call<SignUpResponse> apiCall = dataProcessController.getRestApiClient().signUp(signUpRequest);
        apiCall.enqueue(new Callback<SignUpResponse>() {
            @Override
            public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
                if (signUpView != null) {
                    if (response.isSuccessful()) {
                        SignUpResponse apiResponse = response.body();
                        if (apiResponse.isSuccess()) {
                            dataProcessController.getDataRepository().setUserId(apiResponse.getData().getUserKey());
                            signUpView.showSignUpStatus(true, "");
                        } else {
                            signUpView.showSignUpStatus(false, apiResponse.getError());
                        }
                    } else {
                        signUpView.showSignUpStatus(false, ApiConstants.REST_ERROR_SERVER);
                    }
                }
            }

            @Override
            public void onFailure(Call<SignUpResponse> call, Throwable t) {
                if (signUpView != null) {
                    signUpView.showSignUpStatus(false, ApiConstants.REST_ERROR_NETWORK);
                }
            }
        });
    }


    @Override
    public void onAttachView(SignUpView view) {
        signUpView = view;
    }

    @Override
    public void onDeAttachView() {
        signUpView = null;
    }
}
