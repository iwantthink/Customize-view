package com.marenbo.www.example;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by mrb on 2016/7/22.
 */
public class SquareLayout extends ViewGroup {

    private static final int ORIENTATION_HORIZONTAL = 1, ORIENTATION_VERTICAL = 2;

    private static final int DEFAULT_MAX_ROW = Integer.MAX_VALUE, DEFAULT_MAX_COLUMN = Integer.MAX_VALUE;

    private int mMaxRow = DEFAULT_MAX_ROW;

    private int mMaxColumn = DEFAULT_MAX_COLUMN;

    private int mOrientation = ORIENTATION_HORIZONTAL;

    private Rect mRect = new Rect();

    public SquareLayout(Context context) {
        super(context);

        init();
    }

    private void init() {

        mMaxRow = 2;

        mMaxColumn = 5;
    }

    public SquareLayout(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SquareLayout);

        mMaxColumn = typedArray.getInteger(R.styleable.SquareLayout_max_column, 2);

        mMaxRow = typedArray.getInteger(R.styleable.SquareLayout_max_column, 2);

//        init();
    }

    /**
     * 为了能够让我们父容器的子元素的margins（外边距）能够正确的被计算
     *
     * @param p
     * @return
     */
    @Override
    protected LayoutParams generateLayoutParams(LayoutParams p) {
        return new MarginLayoutParams(p);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new MarginLayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
    }

    @Override
    protected boolean checkLayoutParams(LayoutParams p) {
        return p instanceof MarginLayoutParams;
    }

    @Override
    public boolean shouldDelayChildPressedState() {
        return false;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int parentDesireWidth = 0;

        int parentDesireHeight = 0;

        //存储子元素的测量状态
        int childMeasureState = 0;

        int[] childWidths = new int[getChildCount()];

        int[] childHeights = new int[getChildCount()];

        if (getChildCount() > 0) {

            for (int i = 0; i < getChildCount(); i++) {

                View child = getChildAt(i);

                if (null != child && child.getVisibility() != GONE) {

                    measureChildWithMargins(child, widthMeasureSpec, 0, heightMeasureSpec, 0);

                    //取长宽较高的那个值  需不要和建议的最小值去比较呢？
                    int childMeasuredSize = Math.max(child.getMeasuredWidth(), child.getMeasuredHeight());

                    int childMeasuredSpec = MeasureSpec.makeMeasureSpec(childMeasuredSize, MeasureSpec.EXACTLY);

                    //重新测量子元素  其实就是为了保证长宽一致
                    child.measure(childMeasuredSpec, childMeasuredSpec);

                    MarginLayoutParams mlp = (MarginLayoutParams) child.getLayoutParams();

                    childWidths[i] = child.getMeasuredWidth() + mlp.leftMargin + mlp.rightMargin;

                    childHeights[i] = child.getMeasuredHeight() + mlp.topMargin + mlp.bottomMargin;

                    //合并measuredState
                    childMeasureState = combineMeasuredStates(childMeasureState, child.getMeasuredState());

                }

            }

            //垂直模式
            if (mOrientation == ORIENTATION_VERTICAL) {

                int columnCount = getChildCount() / mMaxRow;

                int remainder = getChildCount() % mMaxRow;

                int index = 0;

                if (getChildCount() > mMaxRow) {

                    for (int i = 0; i < columnCount; i++) {

                        int rowChildWidth = 0;

                        int rowChildHeight = 0;

                        for (int i1 = 0; i1 < mMaxRow; i1++) {

                            int childWidth = childWidths[index];

                            int childHeight = childHeights[index++];

                            rowChildWidth = Math.max(rowChildWidth, childWidth);

                            rowChildHeight += childHeight;
                        }

                        parentDesireHeight = Math.max(rowChildHeight, parentDesireHeight);

                        parentDesireWidth += rowChildWidth;

                    }

                    if (remainder > 0) {

                        int rowChildWidth = 0;

                        int rowChildHeight = 0;

                        for (int i1 = 0; i1 < mMaxRow; i1++) {

                            int childWidth = childWidths[index];

                            int childHeight = childHeights[index++];

                            rowChildWidth = Math.max(rowChildWidth, childWidth);

                            rowChildHeight += childHeight;
                        }

                        parentDesireHeight = Math.max(rowChildHeight, parentDesireHeight);

                        parentDesireWidth += rowChildWidth;
                    }
                } else {

                    int rowChildWidth = 0;

                    int rowChildHeight = 0;

                    for (int i1 = 0; i1 < mMaxRow; i1++) {

                        int childWidth = childWidths[index];

                        int childHeight = childHeights[index++];

                        rowChildWidth = Math.max(rowChildWidth, childWidth);

                        rowChildHeight += childHeight;
                    }

                    parentDesireHeight = Math.max(rowChildHeight, parentDesireHeight);

                    parentDesireWidth += rowChildWidth;

                }


            } else {//横向模式

                int rowCount = getChildCount() / mMaxColumn;

                int remainder = getChildCount() % mMaxColumn;

                int index = 0;

                if (getChildCount() > mMaxColumn) {

                    for (int i = 0; i < rowCount; i++) {

                        int rowChildWidth = 0;

                        int rowChildHeight = 0;

                        for (int i1 = 0; i1 < mMaxColumn; i1++) {

                            int childWidth = childWidths[index];

                            int childHeight = childHeights[index++];

                            rowChildWidth += childWidth;

                            rowChildHeight = Math.max(rowChildHeight, childHeight);
                        }

                        parentDesireHeight += rowChildHeight;

                        parentDesireWidth = Math.max(rowChildWidth, parentDesireWidth);

                    }

                    if (remainder > 0) {

                        int rowChildWidth = 0;

                        int rowChildHeight = 0;

                        for (int i = 0; i < remainder; i++) {

                            int childWidth = childWidths[index];

                            int childHeight = childHeights[index++];

                            rowChildWidth += childWidth;

                            rowChildHeight = Math.max(rowChildHeight, childHeight);
                        }

                        parentDesireHeight += rowChildHeight;

                        parentDesireWidth = Math.max(rowChildWidth, parentDesireWidth);
                    }
                } else {

                    int rowChildWidth = 0;

                    int rowChildHeight = 0;

                    for (int i = 0; i < remainder; i++) {

                        int childWidth = childWidths[index];

                        int childHeight = childHeights[index++];

                        rowChildWidth += childWidth;

                        rowChildHeight = Math.max(rowChildHeight, childHeight);
                    }

                    parentDesireHeight += rowChildHeight;

                    parentDesireWidth = Math.max(rowChildWidth, parentDesireWidth);

                }

            }

            parentDesireWidth += getPaddingLeft() + getPaddingRight();

            parentDesireHeight += getPaddingTop() + getPaddingBottom();

            parentDesireWidth = Math.max(parentDesireWidth, getSuggestedMinimumWidth());

            parentDesireHeight = Math.max(parentDesireHeight, getSuggestedMinimumHeight());

        }

        mWidth = parentDesireWidth;

        mHeight = parentDesireHeight;

        setMeasuredDimension(resolveSizeAndState(parentDesireWidth, widthMeasureSpec, childMeasureState), resolveSizeAndState(parentDesireHeight, heightMeasureSpec, childMeasureState));

//        setMeasuredDimension(resolveSize(parentDesireWidth, widthMeasureSpec), resolveSize(parentDesireHeight, heightMeasureSpec));


    }

    private int mWidth, mHeight;

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        int midData = 0;

        int curLine = 1;

        int indexMultiWidth = 0;

        int indexMultiHeight = 0;

        int lineWidth = 0;

        int lineHeight = 0;

        if (getChildCount() > 0) {

            for (int i = 0; i < getChildCount(); i++) {

                if (mOrientation == ORIENTATION_HORIZONTAL) {

                    View child = getChildAt(i);

                    MarginLayoutParams mlp = (MarginLayoutParams) child.getLayoutParams();

                    int childWidth = child.getMeasuredWidth();

                    int childHeight = child.getMeasuredHeight();

                    if (getChildCount() > mMaxColumn) {//数量过多 大于最大列数量

                        if (i < mMaxColumn * curLine) { //在每一行之内

                            Gravity.apply(Gravity.RIGHT, childWidth, childHeight, new Rect(getPaddingLeft() + mlp.leftMargin + lineWidth,
                                    getPaddingTop() + mlp.topMargin + indexMultiHeight,
                                    child.getMeasuredWidth() + getPaddingLeft() + mlp.leftMargin + lineWidth,
                                    child.getMeasuredHeight() + getPaddingTop() + mlp.topMargin + indexMultiHeight), mRect);

                            child.layout(mRect.left, mRect.top, mRect.right, mRect.bottom);

//                            child.layout(getPaddingLeft() + mlp.leftMargin + lineWidth,
//                                    getPaddingTop() + mlp.topMargin + indexMultiHeight,
//                                    child.getMeasuredWidth() + getPaddingLeft() + mlp.leftMargin + lineWidth,
//                                    child.getMeasuredHeight() + getPaddingTop() + mlp.topMargin + indexMultiHeight);

                            lineWidth += childWidth + mlp.leftMargin + mlp.rightMargin;

                            lineHeight = Math.max(lineHeight, childHeight);

                            if (i + 1 >= mMaxColumn * curLine) {

                                indexMultiWidth = Math.max(indexMultiWidth, lineWidth);

                                indexMultiHeight += lineHeight;

                                lineWidth = 0;

                                lineHeight = 0;

                                curLine++;
                            }

                        }


                    } else { //数量小于 最大列的数量

                    }

                } else {

                }


            }
        }

        Log.d("SquareLayout", "mWidth:" + mWidth);

        Log.d("SquareLayout", "mHeight:" + mHeight);

//        for (int i = 0; i < getChildCount(); i++) {
//
//            View child = getChildAt(i);
//
//            MarginLayoutParams mlp = (MarginLayoutParams) child.getLayoutParams();
//
//            if (mOrientation == ORIENTATION_VERTICAL) {
//
//                child.layout(getPaddingLeft() + mlp.leftMargin,
//                        mlp.topMargin + midData + getPaddingTop(),
//                        mlp.leftMargin + getPaddingLeft() + child.getMeasuredWidth(),
//                        midData + child.getMeasuredHeight() + mlp.topMargin + getPaddingTop());
//
//            } else {
//
//                child.layout(getPaddingLeft() + mlp.leftMargin + midData,
//                        getPaddingTop() + mlp.topMargin,
//                        getPaddingLeft() + mlp.leftMargin + midData + child.getMeasuredWidth(),
//                        child.getMeasuredWidth() + getPaddingTop() + mlp.topMargin);
//
//
//            }
//
//            midData += child.getMeasuredHeight();
//
//        }

    }

//    private int mLastX;
//
//    private int mLastY;
//
//    @Override
//    public boolean onInterceptTouchEvent(MotionEvent ev) {
//
//        boolean intercept = false;
//
//        int x = (int) ev.getX();
//
//        int y = (int) ev.getY();
//
//        switch (ev.getAction()) {
//
//            case MotionEvent.ACTION_DOWN:
//
//                intercept = true;
//
//                break;
//
//            case MotionEvent.ACTION_MOVE:
//
//                if (parentNeedEvent) {
//                    intercept = true;
//                } else {
//
//                    intercept = false;
//                }
//
//                break;
//
//            case MotionEvent.ACTION_UP:
//
//                intercept = false;
//                break;
//
//            default:
//                break;
//
//        }
//
//        mLastX = x;
//
//        mLastY = y;
//
//        return intercept;
//    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        int action = ev.getAction();

        if (action == MotionEvent.ACTION_DOWN)
        {

            return false;
        }else {

            return true;
        }

    }
}
