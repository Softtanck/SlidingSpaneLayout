package com.softtanck.slidingspanelayout;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SlidingPaneLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.nineoldandroids.view.ViewHelper;
import com.softtanck.slidingspanelayout.fragment.Content;
import com.softtanck.slidingspanelayout.fragment.Menu;


public class MainActivity extends AppCompatActivity {

    private SlidingPaneLayout slidingPaneLayout;

    private Menu menu;
    private Content content;

    private int maxMargin = 0;
    private Display defaultDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        slidingPaneLayout = (SlidingPaneLayout) findViewById(R.id.slidingpanellayout);

        menu = new Menu();
        content = new Content();

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        fragmentTransaction.add(R.id.slidingpane_content, content);
        fragmentTransaction.add(R.id.slidingpane_menu, menu);
        fragmentTransaction.commit();

        WindowManager windowManager = getWindowManager();
        defaultDisplay = windowManager.getDefaultDisplay();
        maxMargin = defaultDisplay.getHeight() / 10;
        slidingPaneLayout.setPanelSlideListener(new SlidingPaneLayout.PanelSlideListener() {
            @Override
            public void onPanelSlide(View panel, float slideOffset) {


                ViewHelper.setScaleY(content.getCurrentView(), (1 - slideOffset * 0.2f));// 设置缩放的基准点
                float scale = 1 - ((1 - slideOffset) * maxMargin * 2)
                        / (float) defaultDisplay.getHeight();
                ViewHelper.setScaleX(menu.getCurrentView(), scale);// 设置缩放的基准点
                ViewHelper.setScaleY(menu.getCurrentView(), scale);// 设置缩放的基准点
                ViewHelper.setPivotX(menu.getCurrentView(), scale);// 设置缩放和选择的点
                ViewHelper.setPivotY(menu.getCurrentView(), defaultDisplay.getHeight() / 2);// 设置缩放和选择的点
                ViewHelper.setAlpha(menu.getCurrentView(), slideOffset);
            }

            @Override
            public void onPanelOpened(View panel) {

            }

            @Override
            public void onPanelClosed(View panel) {

            }
        });
    }


}
