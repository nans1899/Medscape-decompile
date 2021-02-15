package com.epapyrus.plugpdf.core.annotation.menu.item;

import android.content.Context;
import android.view.View;
import com.epapyrus.plugpdf.core.annotation.menu.EditColorMenu;
import com.epapyrus.plugpdf.core.viewer.PageView;

public class ColorItem extends BaseMenuItem {
    private EditColorMenu mMenu;

    public void execute(PageView pageView, int i) {
    }

    public boolean hasMenu() {
        return true;
    }

    public ColorItem(Context context, int i) {
        super(i);
        this.mMenu = new EditColorMenu(context);
    }

    public void showMenu(View view, int i, int i2) {
        this.mMenu.setListener(this.mListener);
        this.mMenu.show(view, i, i2);
    }

    public static class Color extends BaseMenuItem {
        private int mB;
        private int mG;
        private int mR;

        public Color(int i, int i2, int i3, int i4) {
            super(i);
            this.mR = i2;
            this.mG = i3;
            this.mB = i4;
        }

        public void execute(PageView pageView, int i) {
            pageView.updateInkAnnotColor(i, this.mR, this.mG, this.mB);
        }
    }
}
