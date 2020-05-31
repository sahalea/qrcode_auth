package com.sahel.qrauth.ui.fragment.stack;

import com.sahel.qrauth.ui.fragment.BaseFragment;
import com.sahel.qrauth.ui.fragment.SignUpFragment;
import com.google.auto.value.AutoValue;

/**
 * The class LoginScreenKey
 *
 * @author Sahel
 * @version 1.0
 * @since 30 May 2020
 */
@AutoValue
public abstract class SignUpScreenKey extends BaseKey {

    public static SignUpScreenKey create() {
        return new AutoValue_SignUpScreenKey();
    }

    @Override
    protected BaseFragment createFragment() {
        return SignUpFragment.newInstance();
    }
}
