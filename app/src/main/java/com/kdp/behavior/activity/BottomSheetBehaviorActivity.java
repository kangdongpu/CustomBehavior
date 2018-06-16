package com.kdp.behavior.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;

import com.kdp.behavior.R;
import com.kdp.behavior.view.FloatingScaleBehavior;


/**
 * Created by kangdongpu on 2017/9/28.
 */

public class BottomSheetBehaviorActivity extends AppCompatActivity {
    private static final String TAG = BottomSheetBehaviorActivity.class.getSimpleName();
    private BottomSheetBehavior bottomSheetBehavior;
    private FloatingScaleBehavior floatingScaleBehavior;
    private FloatingActionButton fab;
    private LinearLayout tab;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bootom_sheet_behavior);
        fab = (FloatingActionButton) findViewById(R.id.floatingactionbutton);
        floatingScaleBehavior = FloatingScaleBehavior.from(fab);
        tab = (LinearLayout) findViewById(R.id.tab);
        bottomSheetBehavior = BottomSheetBehavior.from(tab);
        bottomSheetBehavior.setPeekHeight(0); // 设置 Tab 可见高度,当Tab折叠时不会隐藏此高度，设置大于0时，折叠时tab将不会完全隐藏；
//   设置为0时，将整个tab折叠隐藏,也可以在布局中设置此高度(app:behavior_peekHeight="0dp")
        floatingScaleBehavior.setOnStateChangedListener(onStateChangedListener);
    }


    private FloatingScaleBehavior.OnStateChangedListener onStateChangedListener = new FloatingScaleBehavior.OnStateChangedListener() {
        @Override
        public void onChanged(boolean isShow) {
            bottomSheetBehavior.setState(isShow ? BottomSheetBehavior.STATE_EXPANDED : BottomSheetBehavior.STATE_COLLAPSED);
        }
    };

    /**
     * 当前activity获取焦点后，展开底部tab
     *
     * @param hasFocus
     */
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        super.onWindowFocusChanged(hasFocus);
    }
}

