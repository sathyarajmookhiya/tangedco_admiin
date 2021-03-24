package com.mslabs.tangetco.common;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.mslabs.tangetco.R;


public class PinPadView extends RelativeLayout {

    public static final int LEFT_ACTION = -1;
    public static final int RIGHT_ACTION = -2;

    private PinPadViewListener callback;

    private Button pinButton1;
    private Button pinButton2;
    private Button pinButton3;
    private Button pinButton4;
    private Button pinButton5;
    private Button pinButton6;
    private Button pinButton7;
    private Button pinButton8;
    private Button pinButton9;
    private Button pinButton0;
    private Button pinButtonLeftAction;
    private ImageButton pinButtonRightAction;

    public PinPadView(Context context) {
        this(context, null);
    }

    public PinPadView(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.pinPadViewStyle);
    }

    public PinPadView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        LayoutInflater.from(context).inflate(R.layout.view_pin_pad, this, true);

        setupButtons();

        final TypedArray ta = getContext().obtainStyledAttributes(
                attrs, R.styleable.PinPadView, defStyle, 0);

        try {
            // L/R action text and handlers
            setButtonText(pinButtonLeftAction, ta.getString(R.styleable.PinPadView_leftActionText));
            //setButtonText(pinButtonRightAction, ta.getString(R.styleable.PinPadView_rightActionText));
        } finally {
            ta.recycle();
        }
    }

    public void setButtonText(Button button, CharSequence text) {
        button.setText(text);
    }

    public void setButtonText(Button button, int textRes) {
        setButtonText(button, getResources().getString(textRes));
    }

    public void setCallback(PinPadViewListener callback) {
        this.callback = callback;
    }

    private void setupButtons() {
        pinButton0=findViewById(R.id.pin_numpad_0);
        pinButton0.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callback != null) {
                    callback.onPinPadButtonPress(0);
                }
            }
        });
        pinButton1=findViewById(R.id.pin_numpad_1);
        pinButton1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callback != null) {
                    callback.onPinPadButtonPress(1);
                }
            }
        });
        pinButton2=findViewById(R.id.pin_numpad_2);
        pinButton2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callback != null) {
                    callback.onPinPadButtonPress(2);
                }
            }
        });
        pinButton3=findViewById(R.id.pin_numpad_3);
        pinButton3.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callback != null) {
                    callback.onPinPadButtonPress(3);
                }
            }
        });
        pinButton4 = findViewById(R.id.pin_numpad_4);
        pinButton4.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callback != null) {
                    callback.onPinPadButtonPress(4);
                }
            }
        });
        pinButton5 = findViewById(R.id.pin_numpad_5);
        pinButton5.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callback != null) {
                    callback.onPinPadButtonPress(5);
                }
            }
        });
        pinButton6 = findViewById(R.id.pin_numpad_6);
        pinButton6.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callback != null) {
                    callback.onPinPadButtonPress(6);
                }
            }
        });
        pinButton7 = findViewById(R.id.pin_numpad_7);
        pinButton7.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callback != null) {
                    callback.onPinPadButtonPress(7);
                }
            }
        });
        pinButton8 = findViewById(R.id.pin_numpad_8);
        pinButton8.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callback != null) {
                    callback.onPinPadButtonPress(8);
                }
            }
        });
        pinButton9 = findViewById(R.id.pin_numpad_9);
        pinButton9.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callback != null) {
                    callback.onPinPadButtonPress(9);
                }
            }
        });
        pinButtonLeftAction = findViewById(R.id.pin_numpad_left_action);
        pinButtonLeftAction.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callback != null) {
                    callback.onPinPadButtonPress(LEFT_ACTION);
                }
            }
        });
        pinButtonRightAction = findViewById(R.id.pin_numpad_right_action);
        pinButtonRightAction.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (callback != null) {
                    callback.onPinPadButtonPress(RIGHT_ACTION);
                }
            }
        });
    }

    public interface PinPadViewListener {
        void onPinPadButtonPress(int pinPadCode);
    }
}
