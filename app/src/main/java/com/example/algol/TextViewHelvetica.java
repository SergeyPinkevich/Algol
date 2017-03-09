package com.example.algol;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;

/**
 * Created by Сергей Пинкевич on 09.03.2017.
 */

public class TextViewHelvetica extends android.support.v7.widget.AppCompatTextView {
    private static final String TAG = "TextView";

    public TextViewHelvetica(Context context) {
        super(context);
    }

    public TextViewHelvetica(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setCustomFont(context, attributeSet);
    }

    public TextViewHelvetica(Context context, AttributeSet attributeSet, int defStyle) {
        super(context, attributeSet, defStyle);
        setCustomFont(context, attributeSet);
    }

    public void setCustomFont(Context context, AttributeSet attributeSet) {
        TypedArray array = context.obtainStyledAttributes(attributeSet, R.styleable.TextViewHelvetica);
        String customFont = array.getString(R.styleable.TextViewHelvetica_customFont);
        setCustomFont(context, customFont);
        array.recycle();
    }

    public boolean setCustomFont(Context context, String asset) {
        Typeface typeface;
        try {
            typeface = Typeface.createFromAsset(context.getAssets(), asset);
        } catch (Exception e) {
            Log.e(TAG, "Could not get typeface: " + e.getMessage());
            return false;
        }
        setTypeface(typeface);
        return true;
    }
}
