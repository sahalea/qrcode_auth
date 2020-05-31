package com.sahel.qrauth.rest;


import com.sahel.qrauth.rest.entity.request.LoginRequest;
import com.sahel.qrauth.rest.entity.request.SignUpRequest;
import com.sahel.qrauth.rest.entity.response.LoginResponse;
import com.sahel.qrauth.rest.entity.response.SignUpResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * The class RestApiClient
 *
 * @author Sahel
 * @version 1.0
 * @since 30 May 2020
 */

public interface RestApiClient {


    @POST("login-user")
    Call<LoginResponse> login(@Body LoginRequest request);


    @POST("register-user")
    Call<SignUpResponse> signUp(@Body SignUpRequest request);
}
