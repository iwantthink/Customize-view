package com.marenbo.www.example.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2016/7/12.
 */
public class ClipView extends View {

    private Rect mRect;

    public ClipView(Context context) {
        super(context);
    }

    public ClipView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    private void init() {

        mRect = new Rect(0, 0, 500, 500);

        mRect.intersect(250, 250, 750, 750);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawColor(Color.BLUE);

        canvas.clipRect(mRect);

        canvas.drawColor(Color.RED);

        canvas.drawCircle(500, 500, 100, new Paint(Paint.ANTI_ALIAS_FLAG));

    }
}
