package com.kdp.behavior;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.kdp.behavior.activity.BottomSheetBehaviorActivity;
import com.kdp.behavior.activity.FloatingBehaviorActivity;

/**
 * Created by kangdongpu on 2017/9/27.
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    BottomSheetBehavior behavior;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.floatingbehavior).setOnClickListener(this);
        findViewById(R.id.bottombehavior).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.floatingbehavior:
                startActivity(new Intent(this, FloatingBehaviorActivity.class));
                break;
            case R.id.bottombehavior:
                startActivity(new Intent(this, BottomSheetBehaviorActivity.class));
                break;
        }

    }
}
