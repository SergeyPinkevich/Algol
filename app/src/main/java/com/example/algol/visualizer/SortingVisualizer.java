package com.example.algol.visualizer;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.TextPaint;

/**
 * Created by Сергей Пинкевич on 02.04.2017.
 */

public class SortingVisualizer extends BaseVisualizer {

    private Paint mPaint;
    private Paint mHighlightsForSwap;
    private Paint mHighlightsForTrace;
    private Paint mTextPaint;
    private int[] mArrayForSorting;

    private int highlightPositionFirst = -1;
    private int highlightPositionSecond = -1;
    private int highlightPosition = -1;
    private int lineStrokeWidth = getDimensionInPixel(10);

    public SortingVisualizer(Context context) {
        super(context);
        setWillNotDraw(false);
        setupPaint();
        setupHighlights();
        setupTextPaint();
    }

    public void setupPaint() {
        mPaint = new Paint();
        mPaint.setColor(Color.parseColor("#4392F1"));
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(lineStrokeWidth);
    }

    public void setupHighlights() {
        mHighlightsForSwap = new Paint(mPaint);
        mHighlightsForSwap.setColor(Color.parseColor("#F97748"));

        mHighlightsForTrace = new Paint(mPaint);
        mHighlightsForTrace.setColor(Color.parseColor("#98FF98"));
    }

    public void setupTextPaint() {
        mTextPaint = new TextPaint();
        mTextPaint.setColor(Color.BLACK);
        mTextPaint.setTextSize(getDimensionInPixelFromSP(16));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mArrayForSorting != null) {
            int numberOfLines = mArrayForSorting.length;

            float margin = (getWidth() - (30 * numberOfLines)) / (numberOfLines + 1);
            int height = getHeight() - 90;

            float xPos = margin + getDimensionInPixel(10);
            for (int i = 0; i < mArrayForSorting.length; i++) {

                if (i == highlightPositionFirst || i == highlightPositionSecond) {
                    canvas.drawLine(xPos, height - (float) ((mArrayForSorting[i] / 10.0) * height), xPos, height, mHighlightsForSwap);
                } else if (i == highlightPosition)
                    canvas.drawLine(xPos, height - (float) ((mArrayForSorting[i] / 10.0) * height), xPos, height, mHighlightsForTrace);
                else {
                    canvas.drawLine(xPos, height - (float) ((mArrayForSorting[i] / 10.0) * height), xPos, height, mPaint);
                }

                canvas.drawText(String.valueOf(mArrayForSorting[i]), xPos - lineStrokeWidth / 3, height - (float) ((mArrayForSorting[i] / 10.0) * height) - 30, mTextPaint);

                xPos += margin + 30;
            }
            highlightPositionFirst = -1;
            highlightPositionSecond = -1;
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    public void setData(int[] array) {
        this.mArrayForSorting = array;
        invalidate();
    }

    public void highlightSwap(int first, int second) {
        this.highlightPositionFirst = first;
        this.highlightPositionSecond = second;
        invalidate();
    }

    public void highlightTrace(int position) {
        this.highlightPosition = position;
        invalidate();
    }

    public void onCompleted() {
        this.highlightPosition = -1;
        this.highlightPositionSecond = -1;
        this.highlightPositionFirst = -1;
        invalidate();
    }
}
