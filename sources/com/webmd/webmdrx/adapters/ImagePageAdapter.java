package com.webmd.webmdrx.adapters;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import com.webmd.webmdrx.fragments.ImageFragment;
import java.util.ArrayList;
import java.util.List;

public class ImagePageAdapter extends FragmentPagerAdapter {
    List<String> imageUrls = new ArrayList();

    public ImagePageAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    public ImagePageAdapter(FragmentManager fragmentManager, List<String> list) {
        super(fragmentManager);
        this.imageUrls = list;
    }

    public Fragment getItem(int i) {
        String str;
        if (i >= this.imageUrls.size() || (str = this.imageUrls.get(i)) == null || str.isEmpty()) {
            return null;
        }
        ImageFragment imageFragment = new ImageFragment();
        imageFragment.setImageLink(str);
        return imageFragment;
    }

    public int getCount() {
        return this.imageUrls.size();
    }

    public void setImageUrls(List<String> list) {
        this.imageUrls = list;
    }
}
