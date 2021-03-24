package com.mslabs.tangetco.util;

import android.view.View;

public interface DialogListener {
    void onPosButtonPressed(View.OnClickListener onClickListener);

    void onNegButtonPressed(View.OnClickListener onClickListener);
}