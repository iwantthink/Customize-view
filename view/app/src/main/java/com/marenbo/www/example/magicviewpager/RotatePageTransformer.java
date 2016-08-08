package com.marenbo.www.example.magicviewpager;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * Created by Administrator on 2016/7/24.
 */
public class RotatePageTransformer implements ViewPager.PageTransformer {

    private static final float DEFAULT_ROTATION = 10f;

    private float mMaxRotate = DEFAULT_ROTATION;


    @Override
    public void transformPage(View view, float position) {

        if (position < -1) { // [-Infinity,-1)
            // This page is way off-screen to the left.
            view.setRotation(mMaxRotate * -1);
            view.setPivotX(view.getWidth());
            view.setPivotY(view.getHeight());

        } else if (position <= 1) { // [-1,1]

            if (position < 0)//[0，-1]
            {

                view.setPivotX((view.getWidth()) * (0.5f + 0.5f * (-position)));

                view.setPivotY(view.getHeight());

                view.setRotation(mMaxRotate * position);

            } else//[1,0]
            {
                view.setPivotX(view.getWidth() * 0.5f * (1 - position));

                view.setPivotY(view.getHeight());

                view.setRotation(mMaxRotate * position);

            }
        } else { // (1,+Infinity]
            // This page is way off-screen to the right.
            view.setRotation(mMaxRotate);
            view.setPivotX(view.getWidth() * 0);
            view.setPivotY(view.getHeight());
        }

    }
}
