package com.sahel.qrauth.ui.fragment.stack;

import com.sahel.qrauth.ui.fragment.BaseFragment;
import com.sahel.qrauth.ui.fragment.QRCodeFragment;
import com.google.auto.value.AutoValue;

/**
 * The class LoginScreenKey
 *
 * @author Sahel
 * @version 1.0
 * @since 30 May 2020
 */
@AutoValue
public abstract class QRCodeScreenKey extends BaseKey {

    public static QRCodeScreenKey create() {
        return new AutoValue_QRCodeScreenKey();
    }

    @Override
    protected BaseFragment createFragment() {
        return QRCodeFragment.newInstance();
    }
}
