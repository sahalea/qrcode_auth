package com.sahel.qrauth.ui.dialog;

import android.content.Context;
import android.support.v7.app.AppCompatDialog;

import com.sahel.qrauthenticator.R;


/**
 * The class ProgressDialogHelper
 *
 * @author Sahel
 * @version 1.0
 * @since 30 May 2020
 */

public class ProgressDialogHelper {

    private AppCompatDialog dialog = null;


    public ProgressDialogHelper() {
    }

    public void create(Context context) {
        dialog = new AppCompatDialog(context,
                R.style.ProgressDialogStyle);
        dialog.setContentView(R.layout.layout_progress_dialog);
        dialog.setCancelable(false);
    }

    public void showProgressDialog() {
        dialog.show();
    }

    public void hideProgressDialog() {
        if (dialog != null) {
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
        }
    }

    public void destroy() {
        hideProgressDialog();
        dialog = null;
    }

}
