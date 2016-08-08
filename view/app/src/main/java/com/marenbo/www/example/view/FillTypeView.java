package com.marenbo.www.example.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build;
import android.util.AttributeSet;

/**
 * Created by Administrator on 2016/8/7.
 */
public class FillTypeView extends BaseView {


    public FillTypeView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FillTypeView(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        FillType(canvas);

        op(canvas);

    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    private void op(Canvas canvas) {

        canvas.translate(mViewWidth / 2, mViewHeight / 2);

        Path path = new Path();

        Path path1 = new Path();

        path.addCircle(0, 0, 100, Path.Direction.CW);

        path1.addCircle(100, 0, 100, Path.Direction.CW);

        mDefaultPaint.setStyle(Paint.Style.FILL);

//        canvas.drawPath(path, mDefaultPaint);
//
//        canvas.drawPath(path1, mDefaultPaint);

        path.op(path1, Path.Op.DIFFERENCE);

        canvas.drawPath(path,mDefaultPaint);

    }

    private void FillType(Canvas canvas) {
        mDefaultPaint.setStyle(Paint.Style.FILL);

        Path path = new Path();

        path.setFillType(Path.FillType.EVEN_ODD);

        path.addCircle(0, 0, 100, Path.Direction.CW);
//
//        path.addRect(-200, -200, 200, 200, Path.Direction.CW);

        canvas.translate(mViewWidth / 2, mViewHeight / 2);

        canvas.drawPath(path, mDefaultPaint);
    }
}


