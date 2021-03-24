package com.mslabs.tangetco.util;

import android.app.ProgressDialog;
import android.content.Context;

public class ProgressBarUtil {
    private static final String TAG = ProgressBarUtil.class.getSimpleName();
    private static ProgressDialog progressDialog;

    public static void showProgressDialog(Context context, String title, String message) {
        try {
            if (progressDialog == null) {
                progressDialog = ProgressDialog.show(context, title, message, true);
                progressDialog.setCancelable(true);
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void dismissProgressDialog() {
        try {
            if (progressDialog != null && progressDialog.isShowing()) {
                progressDialog.dismiss();
                progressDialog = null;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
