package com.sahel.qrauth.ui.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;

import com.sahel.qrauth.AppConstants;
import com.sahel.qrauth.ui.dialog.ToastHelper;
import com.sahel.qrauth.util.PermissionHelper;
import com.google.zxing.Result;

import java.util.List;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

/**
 * The class QRCodeScannerActivity
 *
 * @author Sahel
 * @version 1.0
 * @since 30 May 2020
 */
public class QRCodeScannerActivity extends Activity implements ZXingScannerView.ResultHandler {

    private ZXingScannerView mScannerView;
    private PermissionHelper permissionHelper;

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        mScannerView = new ZXingScannerView(this);
        setContentView(mScannerView);
        permissionHelper = new PermissionHelper(this);
        requestCameraPermission();
    }


    @Override
    public void onResume() {
        super.onResume();
        permissionHelper.resume();
        mScannerView.setResultHandler(this);
        mScannerView.startCamera();
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        permissionHelper.onDestroy();
    }

    @Override
    public void handleResult(Result rawResult) {
        String text = rawResult.getText();
        Intent returnIntent = new Intent();
        returnIntent.putExtra(AppConstants.QR_RESULT_KEY,text);
        setResult(Activity.RESULT_OK,returnIntent);
        finish();
    }


    private void requestCameraPermission() {
        permissionHelper.setListener(permissionsListener);
        String[] requiredPermissions = new String[]{Manifest.permission.CAMERA};
        permissionHelper.requestPermission(requiredPermissions, 100);
    }

    private final PermissionHelper.PermissionsListener permissionsListener = new PermissionHelper.PermissionsListener() {
        @Override
        public void onPermissionGranted(int request_code) {
        }

        @Override
        public void onPermissionRejectedManyTimes(@NonNull List<String> rejectedPerms, int request_code, boolean neverAsk) {
            ToastHelper.show("Permission for camera access denied.");
        }

    };

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        permissionHelper.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
