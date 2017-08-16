package com.marenbo.www.example.view;

import android.content.Context;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by Administrator on 2016/7/11.
 */
public class AnimListView extends ListView {

    private Matrix mMatrix;

    private Camera mCamera;

    public AnimListView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    private void init() {

        mMatrix = new Matrix();

        mCamera = new Camera();

    }

    @Override
    protected void onDraw(Canvas canvas) {

        mCamera.save();
        mCamera.rotate(10, 0, 0);
        mCamera.getMatrix(mMatrix);
        mMatrix.preTranslate(-getWidth() / 2, -getHeight() / 2);
        mMatrix.postTranslate(getWidth() / 2, getHeight() / 2);
        canvas.concat(mMatrix);
        super.onDraw(canvas);
        mCamera.restore();
    }
}
