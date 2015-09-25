package com.softtanck.slidingspanelayout.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.softtanck.slidingspanelayout.R;

/**
 * @author : Tanck
 * @Description : TODO
 * @date 9/24/2015
 */
public class Menu extends Fragment {


    private View currentView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        currentView = inflater.inflate(R.layout.menu, container, false);
        return currentView;
    }


    public View getCurrentView() {
        return currentView;
    }
}
