package com.mslabs.tangetco.util;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Build;
import android.provider.Settings;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.StrikethroughSpan;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;

import com.mslabs.tangetco.BaseApplication;
import com.mslabs.tangetco.R;


public class Util {

    public static String getStringResource(int id) {
        return BaseApplication.getApplication().getResources().getString(id);
    }

    public static void updateUnderlineText(TextView textView) {
        String value = textView.getText().toString();

        if (TextUtils.isEmpty(value)) {
            return;
        }

        SpannableString spannableString = new SpannableString(value);
        spannableString.setSpan(new UnderlineSpan(), 0, value.length(), 0);

        textView.setText(spannableString);
    }

    public static void updateStrikeText(TextView textView) {
        String value = textView.getText().toString();

        if (TextUtils.isEmpty(value)) {
            return;
        }

        SpannableString spannableString = new SpannableString(value);
        spannableString.setSpan(new StrikethroughSpan(), 0, value.length(), 0);

        textView.setText(spannableString);
    }

    /**
     * Get App version
     *
     * @param context
     * @return
     */
    @Nullable
    public static String getAppVersion(Context context) {
        try {
            PackageInfo pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return pInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean isValidDeviceName(String name) {
        return new RegistrationValidator().validateName(name);
    }

    public static boolean isValidRoomName(String name) {
        return new RegistrationValidator().validateName(name);
    }

    public static boolean isValidName(String name) {
        return new RegistrationValidator().validateName(name);
    }

    public static boolean isValidMobile(String mobile) {
        return new RegistrationValidator().validateMobile(mobile);
    }

    public static boolean isLocationAccessEnabled(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            // This is new method provided in API 28
            LocationManager lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
            assert lm != null;
            return lm.isLocationEnabled();
        } else {
            // This is Deprecated in API 28
            int mode = Settings.Secure.getInt(context.getContentResolver(), Settings.Secure.LOCATION_MODE,
                    Settings.Secure.LOCATION_MODE_OFF);
            return (mode != Settings.Secure.LOCATION_MODE_OFF);
        }
    }

    public static boolean isLocationPermissionEnabled(Context context) {
        return ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION)
                == PackageManager.PERMISSION_GRANTED;
    }

    public static boolean isMarshmallow() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
    }


   /* public static AlertDialog showAlertDialog(String message, Context context, final DialogListener dialogListener) {
        return showAlertDialog(null, message, context, dialogListener, null, null, true);
    }
*/
    /**
     * show alert dialog
     *
     * @param context
     * @param dialogListener
     * @return
     */
  /*  public static AlertDialog showAlertDialog(String title, String message, Context context,
                                              final DialogListener dialogListener,
                                              String posBtnText,
                                              String negBtnText, boolean isNegBtnVisible
    ) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.ThemeOverlay_App_MaterialAlertDialog);
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.base_dialog_layout, null);
        builder.setView(view);

        final TextView titleTextView = view.findViewById(R.id.title);
        if (!TextUtils.isEmpty(title)) {
            titleTextView.setText(title);
        }

        final TextView messageTextView = view.findViewById(R.id.message);
        if (!TextUtils.isEmpty(message)) {
            messageTextView.setText(message);
        }

        Button cancelButton = view.findViewById(R.id.btn_cancel);
        cancelButton.setVisibility(isNegBtnVisible ? View.VISIBLE: View.GONE);
        Button okButton = view.findViewById(R.id.btn_ok);

        if (!TextUtils.isEmpty(posBtnText)) {
            okButton.setText(posBtnText);
        }

        if (!TextUtils.isEmpty(negBtnText)) {
            cancelButton.setText(negBtnText);
        }

        //Pos btn
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogListener.onPosButtonPressed(this);
            }
        });

        //Neg btn
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogListener.onNegButtonPressed(this);
            }
        });
        builder.setCancelable(false);

        AlertDialog alertDialog = null;
        if (!((Activity) context).isFinishing()) {
            alertDialog = builder.show();
        }
        return alertDialog;
    }

    *//**
     * @param context
     * @param message
     * @param dialogListener
     *//*
    public static void showAlertConfirmationDialog(Context context, String message, final DialogListener dialogListener) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.ThemeOverlay_App_MaterialAlertDialog);
        builder.setMessage(message);
        builder.setTitle(context.getString(R.string.app_name));
        builder.setCancelable(false);
        builder.setPositiveButton(context.getString(R.string.alert_yes), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogListener.onPosButtonPressed(null);
            }
        });
        builder.setNegativeButton(context.getString(R.string.alert_no), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogListener.onNegButtonPressed(null);
            }
        });
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }*/
}
