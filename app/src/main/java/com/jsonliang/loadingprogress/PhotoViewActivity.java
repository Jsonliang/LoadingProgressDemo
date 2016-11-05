package com.jsonliang.loadingprogress;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.helper.ProgressModelLoader;
import com.jpeng.progress.CircleProgress;
import com.jpeng.progress.enums.CircleStyle;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

public class PhotoViewActivity extends AppCompatActivity {


    @BindView(R.id.photoView)
    PhotoView photoView;
    PhotoViewAttacher mAttacher ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_view);
        ButterKnife.bind(this);

        mAttacher = new PhotoViewAttacher(photoView);
        mAttacher.setScaleType(ImageView.ScaleType.FIT_CENTER);// 剧中适应
        mAttacher.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {
            @Override
            public void onPhotoTap(View view, float v, float v1) {
                PhotoViewActivity.this.finish();
            }
        });

        int type = getIntent().getIntExtra("type",0);
        switch (type){
            case 0: // 圆形
               circle();
                break;
            case 1: // 环形
                break;
            case 2:
                break;
            case 3:
                break;
        }
    }

    private void circle(){
        Log.i("circle", "circle: 执行了" );
        final CircleProgress progress = new CircleProgress.Builder()
                 .setBottomWidth(1)
                 .setTextSize(100)
                 .setCircleWidth(200)
                  .setCircleRadius(200)
                .setTextColor(Color.RED)
                .setProgressColor(Color.GRAY)
                .setStyle(CircleStyle.FAN)
                .build();
        progress.inject(photoView);

        Glide.with(PhotoViewActivity.this)
                .using(new ProgressModelLoader(new Handler() {
            @Override
            public void handleMessage(Message msg) {
                progress.setLevel(msg.arg1);
                progress.setMaxValue(msg.arg2);
            }
        })).load("http://pic.paopaoche.net/up/2015-6/201569164043.jpg")
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(photoView);
    }

    @Override
    protected void onResume() {
        super.onResume();
       mAttacher.update();
    }
}
