package com.sahel.qrauth.ui.dialog;

import android.widget.Toast;

import com.sahel.qrauth.QRAuthApplication;


/**
 * The class ToastHelper
 *
 * @author Sahel
 * @version 1.0
 * @since 30 May 2020
 */

public class ToastHelper {

    /**
     * Displays android toast message.
     *
     * @param message the message
     */
    public static void show(String message) {
        Toast.makeText(QRAuthApplication.getInstance(), message,
                Toast.LENGTH_SHORT).show();
    }

    /**
     * Displays android toast message.
     *
     * @param message  the message
     * @param duration the duration
     */
    public static void show(String message, int duration) {
        Toast.makeText(QRAuthApplication.getInstance(), message,
                duration).show();
    }
}
