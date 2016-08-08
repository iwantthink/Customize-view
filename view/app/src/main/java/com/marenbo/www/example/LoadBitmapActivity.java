package com.marenbo.www.example;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.util.LruCache;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoadBitmapActivity extends Activity {

    @BindView(R.id.btn_load)
    Button btnLoad;
    @BindView(R.id.iv_bitmap)
    ImageView ivBitmap;

    private Context mContext = LoadBitmapActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_bitmap);
        ButterKnife.bind(this);

        btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bitmap bitmap = dealSampledBitmapFromResource(mContext.getResources(), R.mipmap.img01, 200, 200);

                ivBitmap.setImageBitmap(bitmap);

                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);

                try {
                    File dir = new File(Environment.getExternalStorageDirectory().getPath() + "/mrb");

                    if (!dir.exists()) {
                        dir.mkdirs();
                    }

                    File file = new File(dir, "/mrb.jpg");

                    file.createNewFile();

                    FileOutputStream fileOutputStream = new FileOutputStream(file);

                    Log.d("LoadBitmapActivity", Environment.getExternalStorageDirectory().getPath());

                    fileOutputStream.write(outputStream.toByteArray());

                    fileOutputStream.flush();

                    fileOutputStream.close();

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

    }

    private Bitmap dealSampledBitmapFromResource(Resources resources, int resid, int reqWidth, int reqHeight) {

        BitmapFactory.Options options = new BitmapFactory.Options();

        options.inJustDecodeBounds = true;

        BitmapFactory.decodeResource(resources, resid, options);

        options.inSampleSize = calculateSampleSize(options, reqWidth, reqHeight);

        Log.d("LoadBitmapActivity", "options.inSampleSize:" + options.inSampleSize);

        options.inJustDecodeBounds = false;

        return BitmapFactory.decodeResource(resources, resid, options);


    }

    private int calculateSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        //req  = 151
        int outWidth = options.outWidth;//300

        int outHeight = options.outHeight;//300

        int inSampleSize = 1;//default size

        if (outWidth > reqWidth || outHeight > reqHeight) {

            int halfWidth = outWidth / 2; //150

            int halfHeight = outHeight / 2;

            while ((halfWidth / inSampleSize) >= reqWidth
                    && (halfHeight / inSampleSize) >= reqHeight) {

                inSampleSize *= 2;
            }

        }

        return inSampleSize;
    }

}
