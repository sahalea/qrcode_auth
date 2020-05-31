package com.sahel.qrauth.ui.activity;

import android.content.ComponentCallbacks2;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.View;

import com.sahel.qrauth.event.EventToHome;
import com.sahel.qrauth.event.EventToLogin;
import com.sahel.qrauth.event.EventToQRCode;
import com.sahel.qrauth.event.EventToSignUp;
import com.sahel.qrauth.ui.fragment.FragmentLoadListener;
import com.sahel.qrauth.ui.fragment.FragmentStateChanger;
import com.sahel.qrauth.ui.fragment.SplashFragment;
import com.sahel.qrauth.ui.fragment.stack.BaseKey;
import com.sahel.qrauth.ui.fragment.stack.HomeScreenKey;
import com.sahel.qrauth.ui.fragment.stack.LoginScreenKey;
import com.sahel.qrauth.ui.fragment.stack.QRCodeScreenKey;
import com.sahel.qrauth.ui.fragment.stack.SignUpScreenKey;
import com.sahel.qrauth.ui.fragment.stack.SplashScreenKey;
import com.sahel.qrauthenticator.R;
import com.zhuinden.simplestack.BackstackDelegate;
import com.zhuinden.simplestack.History;
import com.zhuinden.simplestack.StateChange;
import com.zhuinden.simplestack.StateChanger;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;


/**
 * The class HomeActivity
 *
 * @author Sahel
 * @version 1.0
 * @since 30 May 2020
 */
public class HomeActivity extends BaseActivity implements StateChanger, FragmentLoadListener {


    private BackstackDelegate backstackDelegate;
    private FragmentStateChanger fragmentStateChanger;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        backstackDelegate = new BackstackDelegate();
        backstackDelegate.onCreate(savedInstanceState,
                getLastCustomNonConfigurationInstance(),
                History.single(getInitialScreen()));
        backstackDelegate.registerForLifecycleCallbacks(this);
        setSystemUiVisibility();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        fragmentStateChanger = new FragmentStateChanger(getSupportFragmentManager(), R.id.fragment_container);
        backstackDelegate.setStateChanger(this);
        EventBus.getDefault().register(this);
        injectViews(this);
    }


    private BaseKey getInitialScreen() {
        return SplashScreenKey.create();
    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }


    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        destroyViews();
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }


    @Override
    public void onTrimMemory(int level) {
        switch (level) {
            case ComponentCallbacks2.TRIM_MEMORY_UI_HIDDEN:
                break;

            case ComponentCallbacks2.TRIM_MEMORY_RUNNING_MODERATE:
            case ComponentCallbacks2.TRIM_MEMORY_RUNNING_LOW:
            case ComponentCallbacks2.TRIM_MEMORY_RUNNING_CRITICAL:
                break;

            case ComponentCallbacks2.TRIM_MEMORY_BACKGROUND:
            case ComponentCallbacks2.TRIM_MEMORY_MODERATE:
            case ComponentCallbacks2.TRIM_MEMORY_COMPLETE:
                break;

            default:
                break;
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }


    private void replaceHistory(Object rootKey) {
        backstackDelegate.getBackstack().setHistory(History.single(rootKey), StateChange.REPLACE);
    }

    private void navigateTo(Object key) {
        backstackDelegate.getBackstack().goTo(key);
    }

    private void jumpToRoot(Object key) {
        backstackDelegate.getBackstack().jumpToRoot();
    }

    private void replaceTop(Object key) {
        backstackDelegate.getBackstack().replaceTop(key, StateChange.REPLACE);
    }


    private void setSystemUiVisibility() {
        final int flags = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        getWindow().getDecorView().setSystemUiVisibility(flags);
        final View decorView = getWindow().getDecorView();
        decorView
                .setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
                    @Override
                    public void onSystemUiVisibilityChange(int visibility) {
                        if ((visibility & View.SYSTEM_UI_FLAG_FULLSCREEN) == 0) {
                            decorView.setSystemUiVisibility(flags);
                        }
                    }
                });
    }


    @Override
    protected void onMenuInflated() {

    }

    @Override
    public void onFragmentLoaded(Fragment fragment) {
        if (fragment instanceof SplashFragment) {
            hideToolBar();
        } else {
            showToolBar();
        }
    }

    @Override
    public Object onRetainCustomNonConfigurationInstance() {
        return backstackDelegate.onRetainCustomNonConfigurationInstance();
    }

    @Override
    public void handleStateChange(@NonNull StateChange stateChange, @NonNull Callback
            completionCallback) {
        if (!stateChange.isTopNewKeyEqualToPrevious()) {
            fragmentStateChanger.handleStateChange(stateChange);
        }
        completionCallback.stateChangeComplete();
    }

    @Override
    public void toggleToolbar(boolean isSecondary) {
    }

    @Override
    public void showIndeterminateProgress() {

    }

    @Override
    public void hideIndeterminateProgress() {

    }

    @Override
    public void onBackPressed() {
        if (!backstackDelegate.getBackstack().goBack()) {
            super.onBackPressed();
        }
    }


    @Subscribe
    public void onEventToLogin(EventToLogin event) {
        replaceHistory(LoginScreenKey.create());
    }

    @Subscribe
    public void onEventToHome(EventToHome event) {
        navigateTo(HomeScreenKey.create());
    }

    @Subscribe
    public void onEventToSignUp(EventToSignUp event) {
        navigateTo(SignUpScreenKey.create());
    }

    @Subscribe
    public void onEventToQRCode(EventToQRCode event) {
        navigateTo(QRCodeScreenKey.create());
    }
}
