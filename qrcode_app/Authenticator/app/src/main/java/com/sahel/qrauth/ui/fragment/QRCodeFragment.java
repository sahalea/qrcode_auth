package com.sahel.qrauth.ui.fragment;

import android.Manifest;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.widget.ImageView;

import com.sahel.qrauth.AppConstants;
import com.sahel.qrauth.ui.dialog.ToastHelper;
import com.sahel.qrauth.util.PermissionHelper;
import com.sahel.qrauthenticator.R;

import net.glxn.qrgen.android.QRCode;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * The class QRCodeFragment
 *
 * @author Sahel
 * @version 1.0
 * @since 30 May 2020
 */
public class QRCodeFragment extends BaseFragment {

    @BindView(R.id.ivQrCode)
    ImageView ivQRCode;

    private Bitmap qrCodeBitmap;
    private PermissionHelper permissionHelper;

    public static QRCodeFragment newInstance() {
        Bundle args = new Bundle();
        QRCodeFragment fragment = new QRCodeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        permissionHelper = new PermissionHelper(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        permissionHelper.resume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        permissionHelper.onDestroy();
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_qr_code;
    }

    @Override
    protected void initializeUi() {

    }

    @Override
    protected void setEventListeners() {

    }

    @Override
    protected void doTask() {
        generateQRCode();
    }

    private void requestStoragePermission() {
        permissionHelper.setListener(permissionsListener);
        String[] requiredPermissions = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};
        permissionHelper.requestPermission(requiredPermissions, 100);
    }

    private void generateQRCode() {
        String userId = dataProcessController.getDataRepository().getUserId();
        qrCodeBitmap = QRCode.from(userId).bitmap();
        ivQRCode.setImageBitmap(qrCodeBitmap);
    }

    @OnClick(R.id.btDownload)
    public void shareQRCode() {
        requestStoragePermission();
    }

    private void saveQRCodeImage() {
        dataProcessController.createAppStorageDirectory();
        File file = new File(AppConstants.MEDIA_FOLDER + File.separator + AppConstants.QR_IMAGE);
        if (file.exists()) file.delete();
        try {
            FileOutputStream out = new FileOutputStream(file);
            qrCodeBitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.flush();
            out.close();
            ToastHelper.show("QR code image saved to 'QR AUTH' folder.");
        } catch (Exception e) {
            e.printStackTrace();
            ToastHelper.show("Filed to save QR code image to storage folder.");
        }
    }


    private final PermissionHelper.PermissionsListener permissionsListener = new PermissionHelper.PermissionsListener() {
        @Override
        public void onPermissionGranted(int request_code) {
            saveQRCodeImage();
        }

        @Override
        public void onPermissionRejectedManyTimes(@NonNull List<String> rejectedPerms, int request_code, boolean neverAsk) {
            ToastHelper.show("Permission for storage access denied.");
        }

    };

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        permissionHelper.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
