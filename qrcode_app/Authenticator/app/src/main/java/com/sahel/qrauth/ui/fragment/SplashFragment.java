package com.sahel.qrauth.ui.fragment;

import android.os.Bundle;
import android.os.Handler;

import com.sahel.qrauth.QRAuthApplication;
import com.sahel.qrauth.event.EventToLogin;
import com.sahel.qrauthenticator.R;

import org.greenrobot.eventbus.EventBus;


/**
 * The class SplashFragment
 *
 * @author Sahel
 * @version 1.0
 * @since 30 May 2020
 */
public class SplashFragment extends BaseFragment {


    private Handler handler;
    private long timeBeforeDelay;

    public static SplashFragment newInstance() {
        Bundle args = new Bundle();
        SplashFragment fragment = new SplashFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataProcessController.initialize(QRAuthApplication.getInstance());
        handler = new Handler();
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_splash;
    }

    @Override
    protected void initializeUi() {

    }

    @Override
    protected void setEventListeners() {

    }

    @Override
    protected void doTask() {
        manageSplashDelay();
    }

    private void manageSplashDelay() {
        final long SPLASH_SCREEN_MS = 1500;
        long gapTime = System.currentTimeMillis() - timeBeforeDelay;
        if (gapTime > SPLASH_SCREEN_MS) {
            gapTime = SPLASH_SCREEN_MS;
        }
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                EventBus.getDefault().post(new EventToLogin());
            }
        }, gapTime);
        timeBeforeDelay = System.currentTimeMillis();
    }

}
