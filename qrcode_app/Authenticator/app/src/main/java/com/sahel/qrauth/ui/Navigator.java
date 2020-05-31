package com.sahel.qrauth.ui;

import android.content.Intent;
import android.support.v4.app.Fragment;

import com.sahel.qrauth.ui.activity.QRCodeScannerActivity;

/**
 * The class Navigator
 *
 * @author Sahel
 * @version 1.0
 * @since 30 May 2020
 */

public class Navigator {


    public void navigateToQRScanner(Fragment fragment, int requestCode){
        Intent intent = new Intent(fragment.getActivity(), QRCodeScannerActivity.class);
        fragment.startActivityForResult(intent,requestCode);

    }

}
