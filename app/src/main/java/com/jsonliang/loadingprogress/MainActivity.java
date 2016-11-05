package com.jsonliang.loadingprogress;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btn_circle)
    Button btnCircle;
    @BindView(R.id.btn_val)
    Button btnVal;
    @BindView(R.id.btn_up)
    Button btnUp;
    @BindView(R.id.btn_other)
    Button btnOther;
    @BindView(R.id.activity_main)
    LinearLayout activityMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_circle, R.id.btn_val, R.id.btn_up, R.id.btn_other})
    public void onClick(View view) {
        Intent intent = new Intent(MainActivity.this,PhotoViewActivity.class);
        switch (view.getId()) {
            case R.id.btn_circle:
                intent.putExtra("type",0);
                break;
            case R.id.btn_val:
                intent.putExtra("type",1);
                break;
            case R.id.btn_up:
                intent.putExtra("type",2);
                break;
            case R.id.btn_other:
                intent.putExtra("type",3);
                break;
        }
        startActivity(intent);
    }
}
