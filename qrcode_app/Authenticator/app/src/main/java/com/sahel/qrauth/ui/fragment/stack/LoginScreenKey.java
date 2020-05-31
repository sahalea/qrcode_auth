package com.sahel.qrauth.ui.fragment.stack;

import com.sahel.qrauth.ui.fragment.BaseFragment;
import com.sahel.qrauth.ui.fragment.LoginFragment;
import com.google.auto.value.AutoValue;

/**
 * The class LoginScreenKey
 *
 * @author Sahel
 * @version 1.0
 * @since 30 May 2020
 */
@AutoValue
public abstract class LoginScreenKey extends BaseKey {

    public static LoginScreenKey create() {
        return new AutoValue_LoginScreenKey();
    }

    @Override
    protected BaseFragment createFragment() {
        return LoginFragment.newInstance();
    }
}
