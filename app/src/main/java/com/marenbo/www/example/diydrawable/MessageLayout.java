package com.marenbo.www.example.diydrawable;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.widget.RelativeLayout;

import com.marenbo.www.example.R;

/**
 * Created by Administrator on 2016/7/25.
 */
public class MessageLayout extends RelativeLayout {


    /**
     * 将 attr 文件中自定义的属性添加到viewgroup 中去，使得我们可以使用 自定义的属性 作为一种状态 去编写selector
     */
    private static final int[] STATE_MESSAGE_READED = {R.attr.is_readed};

    private boolean mMessgeReaded = false;

    private GestureDetector mDetector;

    public MessageLayout(Context context) {
        super(context);
    }

    public MessageLayout(final Context context, AttributeSet attrs) {
        super(context, attrs);


    }

    public boolean ismMessgeReaded() {

        return mMessgeReaded;
    }

    public void setmMessgeReaded(boolean mMessgeReaded) {

        this.mMessgeReaded = mMessgeReaded;

        refreshDrawableState();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

    }

    @Override
    protected int[] onCreateDrawableState(int extraSpace) {

        if (mMessgeReaded) {

            final int[] drawableState = super
                    .onCreateDrawableState(extraSpace + 1);

            mergeDrawableStates(drawableState, STATE_MESSAGE_READED);

            return drawableState;
        }


        return super.onCreateDrawableState(extraSpace);
    }

}
