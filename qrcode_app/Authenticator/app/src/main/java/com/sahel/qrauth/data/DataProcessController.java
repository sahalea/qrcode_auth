package com.sahel.qrauth.data;

import com.sahel.qrauth.QRAuthApplication;
import com.sahel.qrauth.AppConstants;
import com.sahel.qrauth.rest.RestApiClient;
import com.sahel.qrauth.rest.ServiceGenerator;
import com.sahel.qrauth.ui.Navigator;
import com.sahel.qrauth.util.FileUtils;
import com.google.gson.Gson;


/**
 * The class DataProcessController
 *
 * @author Sahel
 * @version 1.0
 * @since 30 May 2020
 */

public class DataProcessController {

    private static volatile DataProcessController instance;
    private QRAuthApplication QRAuthApplication;

    private RestApiClient restApiClient;
    private DataRepository dataRepository;
    private Gson gson;
    private Navigator navigator;

    private DataProcessController() {
    }

    public static DataProcessController getDataProcessController() {
        if (instance == null) {
            // double check
            synchronized (DataProcessController.class) {
                if (instance == null) {
                    instance = new DataProcessController();
                }
            }
        }
        return instance;
    }

    public void initialize(QRAuthApplication application) {
        this.QRAuthApplication = application;
        initRestClient();
    }

    private void initRestClient() {
        restApiClient = ServiceGenerator.createService(RestApiClient.class);
    }


    public RestApiClient getRestApiClient() {
        return restApiClient;
    }


    public Gson getGson() {
        if (gson == null) {
            gson = new Gson();
        }
        return gson;
    }


    public DataRepository getDataRepository() {
        if (dataRepository == null) {
            dataRepository = new DataRepository();
        }
        return dataRepository;
    }

    public Navigator getNavigator() {
        if (navigator == null) {
            navigator = new Navigator();
        }
        return navigator;
    }


    public boolean createAppStorageDirectory() {
        return FileUtils.createDirectory(AppConstants.MEDIA_FOLDER);
    }

}
