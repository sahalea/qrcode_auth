package com.sahel.qrauth.rest.entity.response;

/**
 * The class LoginResponse
 *
 * @author Sahel
 * @version 1.0
 * @since 30 May 2020
 */
public class LoginResponse {

    private boolean success;
    private String error;
    private User data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public User getData() {
        return data;
    }

    public void setData(User data) {
        this.data = data;
    }

    public static class User {

        private String name;
        private String username;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }
}
