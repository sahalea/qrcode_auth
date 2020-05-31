package com.sahel.qrauth.rest.entity.request;

/**
 * The class LoginRequest
 *
 * @author Sahel
 * @version 1.0
 * @since 30 May 2020
 */
public class LoginRequest {


    public LoginRequest(String userKey) {
        this.userKey = userKey;
    }

    private String userKey;

    public String getUserKey() {
        return userKey;
    }
}
