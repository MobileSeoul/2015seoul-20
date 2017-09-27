package com.example.study.trash.Main;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;

import com.example.study.trash.R;

/**
 * Created by Changk on 2015-10-30.
 */
public class LoadingActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.load_layout);

        Handler hd = new Handler();
        hd.postDelayed(new Runnable() {

            @Override
            public void run() {
                finish();       // 3 초후 이미지를 닫아버림

                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
            }
        }, 2000);

    }
}
