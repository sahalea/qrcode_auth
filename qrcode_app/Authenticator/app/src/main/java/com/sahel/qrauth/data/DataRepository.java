package com.sahel.qrauth.data;


import com.sahel.qrauth.rest.entity.response.LoginResponse;

/**
 * The class DataRepository
 *
 * @author Sahel
 * @version 1.0
 * @since 30 May 2020
 */
public class DataRepository {

    private LoginResponse.User user;
    private String userId;

    DataRepository() {
    }

    public LoginResponse.User getUser() {
        return user;
    }

    public void setUser(LoginResponse.User user) {
        this.user = user;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
