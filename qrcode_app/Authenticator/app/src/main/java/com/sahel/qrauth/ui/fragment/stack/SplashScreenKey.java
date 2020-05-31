package com.sahel.qrauth.ui.fragment.stack;

import com.sahel.qrauth.ui.fragment.BaseFragment;
import com.sahel.qrauth.ui.fragment.SplashFragment;
import com.google.auto.value.AutoValue;

/**
 * The class SplashScreenKey
 *
 * @author Sahel
 * @version 1.0
 * @since 30 May 2020
 */
@AutoValue
public abstract class SplashScreenKey extends BaseKey {


    public static SplashScreenKey create() {
        return new AutoValue_SplashScreenKey();
    }

    @Override
    protected BaseFragment createFragment() {
        return SplashFragment.newInstance();
    }
}
