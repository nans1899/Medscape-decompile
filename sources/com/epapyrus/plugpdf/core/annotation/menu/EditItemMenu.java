package com.epapyrus.plugpdf.core.annotation.menu;

import android.content.Context;
import com.epapyrus.plugpdf.core.ResourceManager;
import com.epapyrus.plugpdf.core.annotation.menu.item.ColorItem;
import com.epapyrus.plugpdf.core.annotation.menu.item.OpacityItem;
import com.epapyrus.plugpdf.core.annotation.menu.item.WidthItem;

public class EditItemMenu extends BaseMenu {
    public EditItemMenu(Context context) {
        super(context);
        this.mItemList.add(new ColorItem(context, ResourceManager.getStringId(context, "text_annot_menu_item_color")));
        this.mItemList.add(new OpacityItem(context, ResourceManager.getStringId(context, "text_annot_menu_item_opacity")));
        this.mItemList.add(new WidthItem(context, ResourceManager.getStringId(context, "text_annot_menu_item_width")));
    }
}
