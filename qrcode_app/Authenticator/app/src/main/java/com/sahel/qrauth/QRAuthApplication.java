package com.sahel.qrauth;

import android.app.Application;

/**
 * The class QRAuthApplication
 *
 * @author Sahel
 * @version 1.0
 * @since 30 May 2020
 */
public class QRAuthApplication extends Application {

    private static QRAuthApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }


    public static QRAuthApplication getInstance() {
        return instance;
    }


    @Override
    public void onLowMemory() {
        super.onLowMemory();

    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
