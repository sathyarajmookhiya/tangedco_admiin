package com.mslabs.tangetco.common;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.DrawableRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.google.android.material.snackbar.Snackbar;
import com.mslabs.tangetco.R;
import com.mslabs.tangetco.util.ProgressBarUtil;
import com.mslabs.tangetco.util.Util;

import java.util.Objects;

/**
 *
 */
public class BaseActivity extends AppCompatActivity {

    protected Toolbar mToolbar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * To setup the actionbar for the screen
     */
    public void setActionBar() {
        mToolbar = findViewById(R.id.toolbar);

        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
        }
    }

    protected void showActionBar() {
        if (mToolbar != null) {
            Objects.requireNonNull(getSupportActionBar()).show();
        }
    }

    protected void hideActionBar() {
        if (mToolbar != null) {
            Objects.requireNonNull(getSupportActionBar()).hide();
        }
    }

    public void showLoader() {
        ProgressBarUtil.showProgressDialog(this,
                Util.getStringResource(R.string.app_name),
                Util.getStringResource(R.string.Common__txt__loading));
    }

    public void dismissLoader() {
        ProgressBarUtil.dismissProgressDialog();
    }


    /**
     * @param color
     */
    public void setActionbarColor(int color) {
        if (mToolbar != null) {
            mToolbar.setBackgroundColor(color);
        }
    }

    /**
     * To set the title if the activity have toolbar
     *
     * @param title
     */
    public void setToolbarTitle(String title) {
        if (mToolbar != null) {
            mToolbar.setTitle(title);
            getSupportActionBar().setTitle(title);
        }
    }

    /**
     * To set the sub-title if the activity have toolbar
     *
     * @param subTitle
     */
    public void setSubTitle(String subTitle) {
        if (mToolbar != null) {
            mToolbar.setSubtitle(subTitle);
            getSupportActionBar().setSubtitle(subTitle);
        }
    }

    /**
     * To set the logo if the activity have toolbar
     *
     * @param resId
     */
    public void setToolbarIcon(@DrawableRes int resId) {
        if (mToolbar != null) {
            mToolbar.setLogo(resId);
        }
    }

    public void setHomeUpDisable() {
        setHomeUpAction(false);
    }

    public void setHomeUpEnable() {
        setHomeUpAction(true);
    }

    public void setHomeUpAction(boolean isEnable) {
        if (mToolbar != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(isEnable);
            getSupportActionBar().setDisplayShowHomeEnabled(isEnable);

        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void showSnackbar(View view, String message) {
        Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show();
    }

    public void showSnackbar(View view, String message, int length) {
        Snackbar.make(view, message, length).show();
    }

    private View getRootView() {
        View currentFocus = getWindow().getCurrentFocus();
        if (currentFocus != null)
            return currentFocus.getRootView();
        return null;
    }


    public void dismissLoadingDialog() {
        ProgressBarUtil.dismissProgressDialog();
    }

    protected SpannableStringBuilder getErrorMessage(String estring) {
        int ecolor = ContextCompat.getColor(this, R.color.white); // whatever color you want
        ForegroundColorSpan fgcspan = new ForegroundColorSpan(ecolor);
        SpannableStringBuilder ssbuilder = new SpannableStringBuilder(estring);
        ssbuilder.setSpan(fgcspan, 0, estring.length(), 0);
        return ssbuilder;
    }

    protected void startActivityLeftToRight(Intent intent) {
        startActivity(intent);
    }

    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

}
