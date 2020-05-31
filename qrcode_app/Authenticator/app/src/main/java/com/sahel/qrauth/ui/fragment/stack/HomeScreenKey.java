package com.sahel.qrauth.ui.fragment.stack;

import com.sahel.qrauth.ui.fragment.BaseFragment;
import com.sahel.qrauth.ui.fragment.HomeFragment;
import com.google.auto.value.AutoValue;

/**
 * The class SplashScreenKey
 *
 * @author Sahel
 * @version 1.0
 * @since 30 May 2020
 */
@AutoValue
public abstract class HomeScreenKey extends BaseKey {


    public static HomeScreenKey create() {
        return new AutoValue_HomeScreenKey();
    }

    @Override
    protected BaseFragment createFragment() {
        return HomeFragment.newInstance();
    }
}
