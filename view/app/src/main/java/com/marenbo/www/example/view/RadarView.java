package com.marenbo.www.example.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.util.AttributeSet;

/**
 * Created by Administrator on 2016/8/2.
 */
public class RadarView extends BaseView {



    private Path mRadarPath;

    public RadarView(Context context) {
        super(context);
    }

    public RadarView(Context context, AttributeSet attrs) {
        super(context, attrs);

        mRadarPath = new Path();


    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        drawPolygon(canvas);

//        canvas.translate(mViewWidth / 2, mViewHeight / 2);
//
//        mRadarPath.moveTo(0, 0);
//
//        for (int i = 0; i < 6; i++) {
//
//            int rotation = angle + 60 * i;
//
//            int radius = 20;
//
//            double x = radius * Math.sin(rotation);
//
//            double y = radius * Math.cos(rotation);
//
//            Log.d("RadarView", "x:" + x);
//
//            Log.d("RadarView", "y:" + y);
//
//            mRadarPath.lineTo((float) x, (float) y);
//
//        }
//
//        canvas.drawPath(mRadarPath, mDefaultPaint);
    }

    private float radius = 100;                   //网格最大半径
    private int count = 6;                //数据个数
    private float angle = (float) (Math.PI * 2 / count);
    private int centerX;                  //中心X
    private int centerY;                  //中心Y

    /**
     * 绘制正多边形
     */
    private void drawPolygon(Canvas canvas) {

        centerX = mViewWidth / 2;

        centerY = mViewHeight / 2;

        Path path = new Path();
        float r = radius / (count - 1);//r是蜘蛛丝之间的间距
        for (int i = 1; i < count; i++) {//中心点不用绘制
            float curR = r * i;//当前半径
            path.reset();
            for (int j = 0; j < count; j++) {
                if (j == 0) {
                    path.moveTo(centerX + curR, centerY);
                } else {
                    //根据半径，计算出蜘蛛丝上每个点的坐标
                    float x = (float) (centerX + curR * Math.cos(angle * j));
                    float y = (float) (centerY + curR * Math.sin(angle * j));
                    path.lineTo(x, y);
                }
            }
            path.close();//闭合路径
            canvas.drawPath(path, mDefaultPaint);
        }
    }


}
