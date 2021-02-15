package com.epapyrus.plugpdf.core.annotation.menu.item;

import android.view.View;
import com.epapyrus.plugpdf.core.annotation.menu.BaseMenu;
import com.epapyrus.plugpdf.core.viewer.PageView;

public abstract class BaseMenuItem {
    protected BaseMenu.MenuSelectListener mListener;
    private int mTextResourceId;

    public abstract void execute(PageView pageView, int i);

    public boolean hasMenu() {
        return false;
    }

    public void showMenu(View view, int i, int i2) {
    }

    public BaseMenuItem(int i) {
        this.mTextResourceId = i;
    }

    public int textResourceId() {
        return this.mTextResourceId;
    }

    public void setListener(BaseMenu.MenuSelectListener menuSelectListener) {
        this.mListener = menuSelectListener;
    }
}
