package com.marenbo.www.example.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/8/6.
 */
public class ToastUtils {

    private static Toast mToast;

    public static void showToast(Context context, String text) {

        if (null == mToast) {

            mToast = Toast.makeText(context, text, Toast.LENGTH_SHORT);

        } else {

            mToast.setText(text);
        }

        mToast.show();


    }


}
