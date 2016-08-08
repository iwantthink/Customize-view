package com.marenbo.www.example.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Region;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2016/7/17.
 */
public class RegionView extends View {

    private Paint mPaint;

    private Region mRegionA, mRegionB;

    private Rect mRect;

    public RegionView(Context context) {
        super(context);
    }

    public RegionView(Context context, AttributeSet attrs) {
        super(context, attrs);

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        mPaint.setStyle(Paint.Style.STROKE);

        mPaint.setColor(Color.parseColor("#FF99FF"));

        mRegionA = new Region(0, 0, 200, 200);

        mRegionB = new Region(200, 200, 400, 400);

        mRect = new Rect(0, 0, 100, 100);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawColor(Color.GREEN);

        canvas.save();

        canvas.clipRegion(mRegionA);

        canvas.clipRegion(mRegionB, Region.Op.UNION);

        canvas.drawColor(Color.RED);

        canvas.restore();

        canvas.save();

        canvas.drawLine(0, 0, 100, 100, mPaint);

        canvas.restore();

        canvas.save();

        mPaint.setStyle(Paint.Style.FILL);

        mPaint.setColor(Color.YELLOW);

        canvas.drawRect(mRect, mPaint);

        canvas.restore();

    }
}
