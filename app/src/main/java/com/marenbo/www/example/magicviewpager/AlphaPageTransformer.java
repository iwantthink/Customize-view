package com.marenbo.www.example.magicviewpager;

import android.annotation.SuppressLint;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

/**
 * Created by Administrator on 2016/7/24.
 */
public class AlphaPageTransformer implements ViewPager.PageTransformer {

    private static final float DEFAULT_MIN_ALPHA = 0.5f;

    private float mMinAlpha = DEFAULT_MIN_ALPHA;

    /**
     *  当前可见的page 3 页 position 分别为 -1  0  1
     * @param view
     * @param position
     */
    @SuppressLint("NewApi")
    public void transformPage(View view, float position) {


        if (position < -1) { //[-Infinity,-1)

            Log.d("AlphaPageTransformer", "position [-Infinity,-1) :" + position);

            view.setAlpha(mMinAlpha);

        } else if (position <= 1) { // [-1,1]

            Log.d("AlphaPageTransformer", "position [-1,1] :" + position);

            if (position < 0) //[0，-1]
            {
                Log.d("AlphaPageTransformer", "position [0，-1] :" + position);

                float factor = mMinAlpha + (1 - mMinAlpha) * (1 + position);

                view.setAlpha(factor);

            } else//[1，0]
            {

                Log.d("AlphaPageTransformer", "position [1，0] :" + position);

                float factor = mMinAlpha + (1 - mMinAlpha) * (1 - position);

                view.setAlpha(factor);

            }

        } else { // (1,+Infinity]

            Log.d("AlphaPageTransformer", "position (1,+Infinity] :" + position);

            view.setAlpha(mMinAlpha);
        }
    }
}
