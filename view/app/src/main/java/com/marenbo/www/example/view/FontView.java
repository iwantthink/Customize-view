package com.marenbo.www.example.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by Administrator on 2016/7/10.
 */
public class FontView extends View {

    private static final String str = "app麻仁博ξτβб";

    private Paint mTxtPaint;

    private Paint mLinePaint;

    private TextPaint mTextPaint;

    private StaticLayout mStaticLayout; //文本布局

    private Paint.FontMetrics mFontMetrics;

    private float mid1, mid2;

    public FontView(Context context) {
        super(context);
    }

    public FontView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    private void init() {

        mTxtPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        mTxtPaint.setTextSize(50);

        mTxtPaint.setColor(Color.BLUE);

        mTxtPaint.setTextAlign(Paint.Align.CENTER);

//        mTxtPaint.setStrikeThruText(true);
//
        mTxtPaint.setFakeBoldText(true);

        mFontMetrics = mTxtPaint.getFontMetrics();


//        Log.d("Aige", "ascent：" + mFontMetrics.ascent);
//        Log.d("Aige", "top：" + mFontMetrics.top);
//        Log.d("Aige", "leading：" + mFontMetrics.leading);
//        Log.d("Aige", "descent：" + mFontMetrics.descent);
//        Log.d("Aige", "bottom：" + mFontMetrics.bottom);

        mid1 = (Math.abs(mFontMetrics.top) + mFontMetrics.bottom) / 2;

        mid2 = (Math.abs(mFontMetrics.ascent) + mFontMetrics.descent) / 2;

        mLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        mTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);

        mTextPaint.setTextSize(80);

        mTextPaint.setUnderlineText(true);

        mTextPaint.setTextSkewX(-0.25F);

        mTextPaint.setTextScaleX(1.5f);


//        Typeface typeface = Typeface.create("SERIF", Typeface.NORMAL);

//        mTextPaint.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD_ITALIC));


        Log.d("FontView", "mTextPaint.getFontSpacing():" + mTextPaint.getFontSpacing());

        Log.d("FontView", "mTextPaint.getFontMetrics(new Paint.FontMetrics()):" + mTextPaint.getFontMetrics(new Paint.FontMetrics()));

        Log.d("FontView", "mTextPaint.getFontMetricsInt(new Paint.FontMetricsInt()):" + mTextPaint.getFontMetricsInt(new Paint.FontMetricsInt()));


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        int baseY = (int) ((canvas.getHeight() / 2) - ((mTxtPaint.descent() + mTxtPaint.ascent()) / 2));

        int baseY = canvas.getHeight() / 2;

        canvas.drawText(str, canvas.getWidth()/2, baseY, mTxtPaint);

        mLinePaint.setColor(Color.RED);

        canvas.drawLine(0, canvas.getHeight() / 2, canvas.getWidth(), canvas.getHeight() / 2, mLinePaint);


//        canvas.drawLine(0, mid1, 500, mid1, mLinePaint);
//
//        canvas.drawLine(0, mid1 * 2, 500, mid1 * 2, mLinePaint);
//
//        mLinePaint.setColor(Color.YELLOW);
//
//        canvas.drawLine(0, mid2, 500, mid2, mLinePaint);
//
//        canvas.drawLine(0, mid2 * 2, 500, mid2 * 2, mLinePaint);

        mStaticLayout = new StaticLayout("aaaaaaaaassssssssssssssssssssdddddddddddddddddddddddddddddddddddddddddddddddddd"
                , mTextPaint, 100, Layout.Alignment.ALIGN_NORMAL, 1.0F, 0.0F, false);

        mStaticLayout.draw(canvas);

        canvas.restore();
    }
}
