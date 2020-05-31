package com.sahel.qrauth.ui.fragment.stack;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;

/**
 * The class SharedElement
 *
 * @author Sahel
 * @version 1.0
 * @since 30 May 2020
 */
@AutoValue
public abstract class SharedElement implements Parcelable {
    public abstract String sourceTransitionName();

    public abstract String targetTransitionName();

    public static SharedElement create(String sourceTransitionName, String targetTransitionName) {
        return new AutoValue_SharedElement(sourceTransitionName, targetTransitionName);
    }
}
