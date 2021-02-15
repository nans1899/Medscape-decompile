package com.epapyrus.plugpdf.core.annotation.menu;

import android.content.Context;
import com.epapyrus.plugpdf.core.ResourceManager;
import com.epapyrus.plugpdf.core.annotation.menu.item.WidthItem;

public class EditWidthMenu extends BaseMenu {
    public EditWidthMenu(Context context) {
        super(context);
        this.mItemList.add(new WidthItem.Width(ResourceManager.getStringId(context, "text_annot_menu_item_width_1"), 1.0f));
        this.mItemList.add(new WidthItem.Width(ResourceManager.getStringId(context, "text_annot_menu_item_width_3"), 3.0f));
        this.mItemList.add(new WidthItem.Width(ResourceManager.getStringId(context, "text_annot_menu_item_width_5"), 5.0f));
        this.mItemList.add(new WidthItem.Width(ResourceManager.getStringId(context, "text_annot_menu_item_width_7"), 7.0f));
        this.mItemList.add(new WidthItem.Width(ResourceManager.getStringId(context, "text_annot_menu_item_width_9"), 9.0f));
        this.mItemList.add(new WidthItem.Width(ResourceManager.getStringId(context, "text_annot_menu_item_width_11"), 11.0f));
    }
}
