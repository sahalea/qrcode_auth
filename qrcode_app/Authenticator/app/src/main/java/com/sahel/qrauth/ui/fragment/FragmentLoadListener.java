package com.sahel.qrauth.ui.fragment;

import android.support.v4.app.Fragment;

/**
 * The class
 *
 * @author Sahel
 * @version 1.0
 * @since 30 May 2020
 */

public interface FragmentLoadListener {

    /**
     * On fragment loaded.
     *
     * @param fragment the fragment
     */
    void onFragmentLoaded(Fragment fragment);

    void toggleToolbar(boolean isSecondary);

    void showIndeterminateProgress();

    void hideIndeterminateProgress();
}
