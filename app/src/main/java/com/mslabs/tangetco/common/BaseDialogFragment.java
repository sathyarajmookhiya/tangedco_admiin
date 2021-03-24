package com.mslabs.tangetco.common;

import android.content.Context;
import android.content.Intent;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.snackbar.Snackbar;
import com.mslabs.tangetco.R;
import com.mslabs.tangetco.util.ProgressBarUtil;
import com.mslabs.tangetco.util.Util;


public class BaseDialogFragment extends DialogFragment {

    Context mContext;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    public void showLoader() {
        if (getActivity() != null) {
            ProgressBarUtil.showProgressDialog(getActivity(),
                    Util.getStringResource(R.string.app_name),
                    Util.getStringResource(R.string.Common__txt__loading));
        }
    }

    public void dismissLoadingDialog() {
        ProgressBarUtil.dismissProgressDialog();
    }

    public void showSnackbar(String message) {
        Snackbar.make(getActivity().getWindow().getDecorView(), message, Snackbar.LENGTH_SHORT).show();
    }

    public void showSnackbar(String message, int length) {
        Snackbar.make(getActivity().getWindow().getDecorView(), message, length).show();
    }

    protected SpannableStringBuilder getErrorMessage(String estring) {
        int ecolor = ContextCompat.getColor(getContext(), R.color.white); // whatever color you want
        ForegroundColorSpan fgcspan = new ForegroundColorSpan(ecolor);
        SpannableStringBuilder ssbuilder = new SpannableStringBuilder(estring);
        ssbuilder.setSpan(fgcspan, 0, estring.length(), 0);
        return ssbuilder;
    }

    public void showToast(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }
    protected void startActivityLeftToRight(Intent intent) {
        startActivity(intent);
    }
}
