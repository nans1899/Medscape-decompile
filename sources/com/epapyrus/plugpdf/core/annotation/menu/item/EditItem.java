package com.epapyrus.plugpdf.core.annotation.menu.item;

import android.content.Context;
import android.view.View;
import com.epapyrus.plugpdf.core.annotation.menu.EditItemMenu;
import com.epapyrus.plugpdf.core.viewer.PageView;

public class EditItem extends BaseMenuItem {
    private EditItemMenu mMenu;

    public void execute(PageView pageView, int i) {
    }

    public boolean hasMenu() {
        return true;
    }

    public EditItem(Context context, int i) {
        super(i);
        this.mMenu = new EditItemMenu(context);
    }

    public void showMenu(View view, int i, int i2) {
        this.mMenu.setListener(this.mListener);
        this.mMenu.show(view, i, i2);
    }
}
