package com.mslabs.tangetco.common;

import android.content.Context;
import android.content.Intent;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;
import com.mslabs.tangetco.R;
import com.mslabs.tangetco.util.ProgressBarUtil;
import com.mslabs.tangetco.util.Util;


public class BaseFragment extends Fragment {

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
        Snackbar.make(getView(), message, Snackbar.LENGTH_SHORT).show();
    }

    public void showSnackbar(String message, int length) {
        Snackbar.make(getView(), message, length).show();
    }

    public void showSnackbarUp(String message, int length) {
        Snackbar snack = Snackbar.make(getView(), message, length);
        View view = snack.getView();
        FrameLayout.LayoutParams params =(FrameLayout.LayoutParams)view.getLayoutParams();
        params.gravity = Gravity.TOP;
        view.setLayoutParams(params);
        snack.show();

    }

   /* fun showSnackBar(view: View, messageResId: Int) {
        val snackbar=  Snackbar.make(view, messageResId, Snackbar.LENGTH_SHORT)
        val viewSnackbar = snackbar.view
        viewSnackbar.setBackgroundColor(view.resources.getColor(R.color.darkPeachColor))
        val params = viewSnackbar.layoutParams as FrameLayout.LayoutParams
        params.gravity = Gravity.TOP
        viewSnackbar.layoutParams = params
        snackbar.show()
    }*/

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
