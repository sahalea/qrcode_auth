package com.sahel.qrauth.rest;


import com.google.gson.Gson;
import com.sahel.qrauthenticator.BuildConfig;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * The class ServiceGenerator
 *
 * @author Sahel
 * @version 1.0
 * @since 30 May 2020
 */

public class ServiceGenerator {


    /**
     * The constant API_BASE_URL.
     */
    private static final String API_BASE_URL = "https://qrcode-auth-api.herokuapp.com/api/";



    /**
     * The retrofit instance.
     */
    private static Retrofit retrofit;
    /**
     * The ok httpClient instance.
     */
    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();


    /**
     * The  Retrofit builder.
     */
    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(new Gson()));

    private static final HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();


    // No need to instantiate this class.

    /**
     * Instantiates a new Service generator.
     */
    private ServiceGenerator() {
    }

    /**
     * Gets the Retrofit instance.
     *
     * @return the retrofit instance
     */
    static Retrofit retrofit() {
        return retrofit;
    }

    /**
     * Creates rest api service.
     *
     * @param <S>          the type parameter
     * @param serviceClass the rest service class
     * @return the s
     */
    public static <S> S createService(Class<S> serviceClass) {
        return createService(serviceClass, null);
    }


    /**
     * Creates rest api service.
     *
     * @param <S>          the type parameter
     * @param serviceClass the service class
     * @param authToken    the authentication token
     * @return the s
     */
    public static <S> S createService(Class<S> serviceClass, final String authToken) {
        addLoggingInterceptor();
        OkHttpClient client = httpClient.build();
        retrofit = builder.client(client).build();
        return retrofit.create(serviceClass);
    }


    private static void addLoggingInterceptor() {
        if (BuildConfig.DEBUG) {
            if (!httpClient.interceptors().contains(httpLoggingInterceptor)) {
                httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                httpClient.addInterceptor(httpLoggingInterceptor);
            }
        }
    }


}
