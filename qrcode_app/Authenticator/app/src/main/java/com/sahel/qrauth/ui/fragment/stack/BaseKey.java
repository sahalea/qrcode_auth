package com.sahel.qrauth.ui.fragment.stack;

import android.os.Bundle;
import android.os.Parcelable;

import com.sahel.qrauth.ui.fragment.BaseFragment;


/**
 * The class BaseKey
 *
 * @author Sahel
 * @version 1.0
 * @since 30 May 2020
 */
public abstract class BaseKey implements Parcelable {


    public String getFragmentTag() {
        return toString();
    }

    public final BaseFragment newFragment() {
        BaseFragment fragment = createFragment();
        Bundle bundle = fragment.getArguments();
        if (bundle == null) {
            bundle = new Bundle();
        }
        bundle.putParcelable("KEY", this);
        fragment.setArguments(bundle);
        return fragment;
    }

    protected abstract BaseFragment createFragment();

}
