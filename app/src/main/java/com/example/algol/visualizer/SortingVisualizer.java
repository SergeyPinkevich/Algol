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
    private int[] array;

    private int highlightPositionFirst = -1;
    private int highlightPositionSecond = -1;
    private int highlightPosition = -1;
    private int lineStrokeWidth = getDimensionInPixel(10);

    public SortingVisualizer(Context context) {
        super(context);
        setupPaint();
        setupHighlights();
        setupTextPaint();
    }

    public void setupPaint() {
        mPaint = new Paint();
        mPaint.setColor(Color.parseColor("#009688"));
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(lineStrokeWidth);
    }

    public void setupHighlights() {
        mHighlightsForSwap = new Paint(mPaint);
        mHighlightsForSwap.setColor(Color.RED);

        mHighlightsForTrace = new Paint(mPaint);
        mHighlightsForTrace.setColor(Color.BLUE);
    }

    public void setupTextPaint() {
        mTextPaint = new TextPaint();
        mTextPaint.setColor(Color.BLACK);
        mTextPaint.setTextSize(getDimensionInPixelFromSP(15));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (array != null) {
            int numberOfLines = array.length;

            float margin = (getWidth() - (30 * numberOfLines)) / (numberOfLines + 1);

            float xPos = margin + getDimensionInPixel(10);
            for (int i = 0; i < array.length; i++) {

                if (i == highlightPositionFirst || i == highlightPositionSecond) {
                    canvas.drawLine(xPos, getHeight() - (float) ((array[i] / 10.0) * getHeight()), xPos, getHeight(), mHighlightsForSwap);
                } else if (i == highlightPosition)
                    canvas.drawLine(xPos, getHeight() - (float) ((array[i] / 10.0) * getHeight()), xPos, getHeight(), mHighlightsForTrace);
                else {
                    canvas.drawLine(xPos, getHeight() - (float) ((array[i] / 10.0) * getHeight()), xPos, getHeight(), mPaint);
                }

                canvas.drawText(String.valueOf(array[i]), xPos - lineStrokeWidth / 3, getHeight() - (float) ((array[i] / 10.0) * getHeight()) - 30, mTextPaint);

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
        this.array = array;
        invalidate();
    }

    public void highlightSwap(int one, int two) {
        this.highlightPositionFirst = one;
        this.highlightPositionSecond = two;
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
