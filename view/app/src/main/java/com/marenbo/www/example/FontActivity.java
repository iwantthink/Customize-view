package com.marenbo.www.example;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.marenbo.www.example.view.AnimListView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FontActivity extends AppCompatActivity {

    @BindView(R.id.view)
    AnimListView view;

    @BindView(R.id.iv_bitmap)
    ImageView ivBitmap;

    @BindView(R.id.rl_root)
    RelativeLayout rlRoot;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_font);
        ButterKnife.bind(this);

        toolbar.setTitle("this is toolbar");

        toolbar.setTitleTextColor(getResources().getColor(android.R.color.white));

        toolbar.setSubtitle("subtitle");

        toolbar.setSubtitleTextColor(getResources().getColor(android.R.color.holo_green_light));

        toolbar.setNavigationIcon(R.mipmap.ic_launcher);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(FontActivity.this, "123", Toast.LENGTH_SHORT).show();

                Log.d("FontActivity", "Navigation");
            }
        });

        toolbar.inflateMenu(R.menu.menu_font);

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                String result = "";

                switch (item.getItemId()) {

                    case R.id.action_top:

                        result = "top";

                        break;

                    case R.id.action_friend:

                        result = "frient";

                        break;

                    case R.id.action_content:

                        result = "content";

                        break;

                    case R.id.action_mine:

                        result = "mine";

                        break;

                    case R.id.action_smile:

                        startSupportActionMode(new android.support.v7.view.ActionMode.Callback() {
                            @Override
                            public boolean onCreateActionMode(android.support.v7.view.ActionMode mode, Menu menu) {

                                mode.setTitle("actionMode");

                                mode.setSubtitle("actionMode subTitle");

                                return true;
                            }

                            @Override
                            public boolean onPrepareActionMode(android.support.v7.view.ActionMode mode, Menu menu) {
                                return false;
                            }

                            @Override
                            public boolean onActionItemClicked(android.support.v7.view.ActionMode mode, MenuItem item) {
                                return false;
                            }

                            @Override
                            public void onDestroyActionMode(android.support.v7.view.ActionMode mode) {

                            }
                        });


                        break;

                    default:

                        result = "default";

                        break;


                }

                Toast.makeText(FontActivity.this, result, Toast.LENGTH_SHORT).show();

                return true;
            }
        });

        toolbar.setPopupTheme(R.style.myTheme);



        ViewGroup viewParent = (ViewGroup) rlRoot.getParent();

        Log.d("LifeCycleView", "activity oncreate");

        Log.d("FontActivity", "ViewParent.class:" + viewParent.getClass());


//        Bitmap bitmap = Bitmap.createBitmap(200, 200, Bitmap.Config.ARGB_8888);
//
//        Canvas canvas = new Canvas(bitmap);
//
//        canvas.drawColor(Color.RED);
//
//        ivBitmap.setImageBitmap(bitmap);

//        view.setAdapter(new BaseAdapter() {
//            @Override
//            public int getCount() {
//                return 100;
//            }
//
//            @Override
//            public Object getItem(int position) {
//                return position;
//            }
//
//            @Override
//            public long getItemId(int position) {
//                return 0;
//            }
//
//            @Override
//            public View getView(int position, View convertView, ViewGroup parent) {
//
//
//                ViewHolder viewHolder = null;
//
//                if (null == convertView) {
//
//                    convertView = View.inflate(FontActivity.this, R.layout.abc_action_bar_title_item, null);
//
//                    viewHolder = new ViewHolder();
//
//                    viewHolder.tv1 = (TextView) convertView.findViewById(R.id.action_bar_title);
//
//                    viewHolder.tv2 = (TextView) convertView.findViewById(R.id.action_bar_subtitle);
//
//                    convertView.setTag(viewHolder);
//
//                } else {
//                    viewHolder = (ViewHolder) convertView.getTag();
//                }
//
//                viewHolder.tv1.setText("Title");
//
//                viewHolder.tv2
//                        .setText("SubTitle");
//
//
//                return convertView;
//            }
//
//            class ViewHolder {
//
//                private TextView tv1;
//
//                private TextView tv2;
//
//            }
//
//
//        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        Log.d("LifeCycleView", "activity onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.d("LifeCycleView", "activity onResume");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_font, menu);

        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        String result = "";

        switch (item.getItemId()) {

            case R.id.action_top:

                result = "top";

                break;

            case R.id.action_friend:

                result = "frient";

                break;

            case R.id.action_content:

                result = "content";

                break;

            case R.id.action_mine:

                result = "mine";

                break;

            default:

                result = "default";

                break;


        }

        Toast.makeText(this, result, Toast.LENGTH_SHORT).show();

        return super.onOptionsItemSelected(item);
    }
}
