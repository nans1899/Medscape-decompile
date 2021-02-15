package com.epapyrus.plugpdf.core.annotation.menu;

import android.content.Context;
import com.epapyrus.plugpdf.core.ResourceManager;
import com.epapyrus.plugpdf.core.annotation.menu.item.ColorItem;

public class EditColorMenu extends BaseMenu {
    public EditColorMenu(Context context) {
        super(context);
        this.mItemList.add(new ColorItem.Color(ResourceManager.getStringId(context, "text_annot_menu_item_color_white"), 255, 255, 255));
        this.mItemList.add(new ColorItem.Color(ResourceManager.getStringId(context, "text_annot_menu_item_color_black"), 0, 0, 0));
        this.mItemList.add(new ColorItem.Color(ResourceManager.getStringId(context, "text_annot_menu_item_color_red"), 255, 0, 0));
        this.mItemList.add(new ColorItem.Color(ResourceManager.getStringId(context, "text_annot_menu_item_color_orange"), 255, 165, 0));
        this.mItemList.add(new ColorItem.Color(ResourceManager.getStringId(context, "text_annot_menu_item_color_yellow"), 255, 255, 0));
        this.mItemList.add(new ColorItem.Color(ResourceManager.getStringId(context, "text_annot_menu_item_color_green"), 0, 128, 0));
        this.mItemList.add(new ColorItem.Color(ResourceManager.getStringId(context, "text_annot_menu_item_color_blue"), 0, 0, 255));
        this.mItemList.add(new ColorItem.Color(ResourceManager.getStringId(context, "text_annot_menu_item_color_purple"), 128, 0, 128));
    }
}
