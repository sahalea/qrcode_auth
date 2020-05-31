package com.sahel.qrauth.ui.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.sahel.qrauth.ui.fragment.stack.BaseKey;
import com.zhuinden.simplestack.StateChange;

import java.util.List;

/**
 * The class FragmentStateChanger
 *
 * @author Sahel
 * @version 1.0
 * @since 30 May 2020
 */
public class FragmentStateChanger {

    private FragmentManager fragmentManager;
    private int containerId;

    public FragmentStateChanger(FragmentManager fragmentManager, int containerId) {
        this.fragmentManager = fragmentManager;
        this.containerId = containerId;
    }

    public void handleStateChange(StateChange stateChange) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction().disallowAddToBackStack();
        if (stateChange.getDirection() == StateChange.FORWARD) {
            //fragmentTransaction.setCustomAnimations(R.anim.slide_in_from_right, R.anim.slide_out_to_left, R.anim.slide_in_from_right, R.anim.slide_out_to_left);
        } else if (stateChange.getDirection() == StateChange.BACKWARD) {
            //fragmentTransaction.setCustomAnimations(R.anim.slide_in_from_left, R.anim.slide_out_to_right, R.anim.slide_in_from_left, R.anim.slide_out_to_right);
        }

        List<BaseKey> previousState = stateChange.getPreviousKeys();
        List<BaseKey> newState = stateChange.getNewKeys();
        for (BaseKey oldKey : previousState) {
            Fragment fragment = fragmentManager.findFragmentByTag(oldKey.getFragmentTag());
            if (fragment != null) {
                if (!newState.contains(oldKey)) {
                    fragmentTransaction.remove(fragment);
                } else if (!fragment.isDetached()) {
                    fragmentTransaction.detach(fragment);
                }
            }
        }
        for (BaseKey newKey : newState) {
            Fragment fragment = fragmentManager.findFragmentByTag(newKey.getFragmentTag());
            if (newKey.equals(stateChange.topNewKey())) {
                if (fragment != null) {
                    if (fragment.isDetached()) {
                        fragmentTransaction.attach(fragment);
                    }
                } else {
                    fragment = newKey.newFragment();
                    fragmentTransaction.add(containerId, fragment, newKey.getFragmentTag());
                }
            } else {
                if (fragment != null && !fragment.isDetached()) {
                    fragmentTransaction.detach(fragment);
                }
            }
        }
        fragmentTransaction.commitNow();
    }
}
