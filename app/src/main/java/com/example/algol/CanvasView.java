package com.example.algol;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

/**
 * Created by Сергей Пинкевич on 13.12.2016.
 */

public class CanvasView extends View {

    private Paint paint;
    private Canvas canvas;

    public CanvasView(Context context) {
        super(context);
        initPaint();
        initState();
    }

    public void initPaint() {
        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setAntiAlias(true); // for smoothing
        paint.setStyle(Paint.Style.FILL);
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.canvas = canvas;
    }

    public void initState() {
        canvas.drawRect(0, 0, 0, 0, paint);
    }
}
