package com.wbmd.qxcalculator.views.tab;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ImageFragmentPagerAdapter extends FragmentPagerAdapter {
    public int getCount() {
        return 0;
    }

    public Fragment getItem(int i) {
        return null;
    }

    public int getTabIcon(int i) {
        return -1;
    }

    public ImageFragmentPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }
}
