package com.marenbo.www.example.magicviewpager;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.WindowManager;
import android.widget.ImageView;

import com.marenbo.www.example.R;

/**
 * viewpager 实现的banner 拥有切换动画，能够显示前后俩个页面
 * 实现无线轮播
 */
public class VPActivity extends AppCompatActivity {

    private ViewPager viewPager;

    private int[] mRes = new int[]{R.mipmap.img02, R.mipmap.img02, R.mipmap.img02, R.mipmap.img02, R.mipmap.img02
            , R.mipmap.img02, R.mipmap.img02, R.mipmap.img02, R.mipmap.img02, R.mipmap.img02, R.mipmap.img02};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        setContentView(R.layout.activity_vp);

        viewPager = (ViewPager) findViewById(R.id.viewpager);

//        viewPager.setPageMargin(15);//设置pager之间的间隔

        viewPager.setOffscreenPageLimit(3); //设置最多显示的页面

        viewPager.setAdapter(new PagerAdapter() {


            @Override
            public Object instantiateItem(ViewGroup container, int position) {

                int pos = position % mRes.length;


                if (pos < 0) {

                    pos = mRes.length + pos;
                }
                ImageView view = new ImageView(VPActivity.this);

                view.setImageResource(mRes[pos]);

                view.setScaleType(ImageView.ScaleType.FIT_XY);

                ViewParent parent = view.getParent();

                if (null != parent) {

                    ViewGroup vp = (ViewGroup) parent;

                    vp.removeView(view);

                }

                container.addView(view);

                return view;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {

//                container.removeView((View) object);
            }

            @Override
            public int getCount() {

                return Integer.MAX_VALUE;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }
        });

        viewPager.setCurrentItem(mRes.length * 1000);

//        viewPager.setPageTransformer(false, new AlphaPageTransformer());
        viewPager.setPageTransformer(false, new RotatePageTransformer());


    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        return viewPager.dispatchTouchEvent(ev);


    }
}
