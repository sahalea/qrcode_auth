package com.sahel.qrauth.rest.entity.response;

/**
 * The class SignUpResponse
 *
 * @author Sahel
 * @version 1.0
 * @since 30 May 2020
 */
public class SignUpResponse {



    private boolean success;
    private String error;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {


        private String userKey;

        public String getUserKey() {
            return userKey;
        }

        public void setUserKey(String userKey) {
            this.userKey = userKey;
        }
    }
}
