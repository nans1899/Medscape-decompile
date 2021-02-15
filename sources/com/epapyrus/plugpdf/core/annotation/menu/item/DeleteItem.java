package com.epapyrus.plugpdf.core.annotation.menu.item;

import com.epapyrus.plugpdf.core.viewer.PageView;

public class DeleteItem extends BaseMenuItem {
    public DeleteItem(int i) {
        super(i);
    }

    public void execute(PageView pageView, int i) {
        pageView.removeAnnotFromPDF(i);
    }
}
