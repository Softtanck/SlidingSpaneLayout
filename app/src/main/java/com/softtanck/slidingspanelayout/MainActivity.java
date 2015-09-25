package com.softtanck.slidingspanelayout;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SlidingPaneLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.FrameLayout;

import com.softtanck.slidingspanelayout.fragment.Content;
import com.softtanck.slidingspanelayout.fragment.Menu;


public class MainActivity extends AppCompatActivity {

    private SlidingPaneLayout slidingPaneLayout;

    private Menu menu;
    private Content content;

    private DisplayMetrics displayMetrics = new DisplayMetrics();
    private int maxMargin = 0;

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

        maxMargin = displayMetrics.heightPixels / 10;
        slidingPaneLayout.setPanelSlideListener(new SlidingPaneLayout.PanelSlideListener() {
            @Override
            public void onPanelSlide(View panel, float slideOffset) {
                int contentMargin = (int) (slideOffset * maxMargin);
                FrameLayout.LayoutParams contentParams = content
                        .getCurrentViewParams();
                contentParams.setMargins(0, contentMargin, 0, contentMargin);
                content.setCurrentViewPararms(contentParams);

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
