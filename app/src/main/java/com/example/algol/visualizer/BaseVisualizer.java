package com.example.algol.visualizer;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;

/**
 * Created by Сергей Пинкевич on 02.04.2017.
 */

public abstract class BaseVisualizer extends View {

    public BaseVisualizer(Context context) {
        super(context);
        setWillNotDraw(false);
    }

    @Override
    protected void onMeasure(int width, int height) {
        super.onMeasure(width, height);
        setMeasuredDimension(getMeasuredWidth(), getDimensionInPixel(330));
    }

    public int getDimensionInPixel(int dp) {
        int density = getResources().getDisplayMetrics().densityDpi;

        int modifiedDP = dp;
        switch (density) {
            case DisplayMetrics.DENSITY_LOW:
                modifiedDP = dp - dp / 2;
                break;
            case DisplayMetrics.DENSITY_MEDIUM:
                modifiedDP = dp - dp / 3;
                break;
            case DisplayMetrics.DENSITY_HIGH:
                modifiedDP = dp - dp / 4;
                break;
            case DisplayMetrics.DENSITY_XHIGH:
            case DisplayMetrics.DENSITY_XXHIGH:
            case DisplayMetrics.DENSITY_XXXHIGH:
                modifiedDP = dp;
                break;
            default:
                break;
        }
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, modifiedDP, getResources().getDisplayMetrics());
    }

    public int getDimensionInPixelFromSP(int sp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, getResources().getDisplayMetrics());
    }
}
