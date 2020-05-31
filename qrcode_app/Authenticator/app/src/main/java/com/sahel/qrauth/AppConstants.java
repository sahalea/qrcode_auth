package com.sahel.qrauth;

import android.os.Environment;

import java.io.File;

/**
 * The class AppConstants
 *
 * @author Sahel
 * @version 1.0
 * @since 30 May 2020
 */

public class AppConstants {

    public static final String QR_RESULT_KEY = "qr_result";

    //storage
    public static final String MEDIA_FOLDER = Environment.getExternalStorageDirectory() + File.separator + "QR AUTH";
    public static final String QR_IMAGE = "login_qr.jpg";
}
