package com.epapyrus.plugpdf.core.annotation.menu.item;

import android.content.Context;
import android.view.View;
import com.epapyrus.plugpdf.core.annotation.menu.EditWidthMenu;
import com.epapyrus.plugpdf.core.viewer.PageView;

public class WidthItem extends BaseMenuItem {
    private EditWidthMenu mMenu;

    public void execute(PageView pageView, int i) {
    }

    public boolean hasMenu() {
        return true;
    }

    public WidthItem(Context context, int i) {
        super(i);
        this.mMenu = new EditWidthMenu(context);
    }

    public void showMenu(View view, int i, int i2) {
        this.mMenu.setListener(this.mListener);
        this.mMenu.show(view, i, i2);
    }

    public static class Width extends BaseMenuItem {
        private float mWidth = 3.0f;

        public Width(int i, float f) {
            super(i);
            this.mWidth = f;
        }

        public void execute(PageView pageView, int i) {
            pageView.updateInkAnnotLineWidth(i, this.mWidth);
        }
    }
}
