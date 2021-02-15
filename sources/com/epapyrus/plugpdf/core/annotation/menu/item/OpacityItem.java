package com.epapyrus.plugpdf.core.annotation.menu.item;

import android.content.Context;
import android.view.View;
import com.epapyrus.plugpdf.core.annotation.menu.EditOpacityMenu;
import com.epapyrus.plugpdf.core.viewer.PageView;

public class OpacityItem extends BaseMenuItem {
    private EditOpacityMenu mMenu;

    public void execute(PageView pageView, int i) {
    }

    public boolean hasMenu() {
        return true;
    }

    public OpacityItem(Context context, int i) {
        super(i);
        this.mMenu = new EditOpacityMenu(context);
    }

    public void showMenu(View view, int i, int i2) {
        this.mMenu.setListener(this.mListener);
        this.mMenu.show(view, i, i2);
    }

    public static class Opacity extends BaseMenuItem {
        private float mOpacity = 255.0f;

        public Opacity(int i, float f) {
            super(i);
            this.mOpacity = f;
        }

        public void execute(PageView pageView, int i) {
            pageView.updateInkAnnotOpacity(i, this.mOpacity);
        }
    }
}
