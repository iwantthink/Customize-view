package com.marenbo.www.example.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Picture;
import android.util.AttributeSet;

/**
 * Created by Administrator on 2016/8/2.
 */
public class PictureView extends BaseView {

    private Picture mPicture = new Picture();


    public PictureView(Context context, AttributeSet attrs) {
        super(context, attrs);

        Canvas canvas = mPicture.beginRecording(200, 200);

        canvas.translate(100, 100);

        canvas.drawCircle(0, 0, 50, mDefaultPaint);

        mPicture.endRecording();

        setLayerType(LAYER_TYPE_SOFTWARE, null);

    }

    public PictureView(Context context) {
        super(context);


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        mPicture.draw(canvas);

        drawAssist(canvas);

//
//        canvas.drawPicture(mPicture);
//
//        canvas.drawPicture(mPicture, new Rect(0, 0, 200, 50));

//        PictureDrawable pictureDrawable = new PictureDrawable(mPicture);
//
//        pictureDrawable.setBounds(0, 0, 100, 200);
//
//        pictureDrawable.draw(canvas);


        canvas.drawText("abcDEF", 200, 200, mDefaultTextPaint);

    }

    private void drawAssist(Canvas canvas) {
        Path path = new Path();

        path.lineTo(200, 0);

        path.lineTo(200, 200);

        path.lineTo(0, 200);

        path.close();

        canvas.drawPath(path, mDefaultPaint);

    }
}
