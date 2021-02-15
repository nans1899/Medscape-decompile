package com.epapyrus.plugpdf.core.annotation.menu;

import android.content.Context;
import com.epapyrus.plugpdf.core.ResourceManager;
import com.epapyrus.plugpdf.core.annotation.menu.item.OpacityItem;

public class EditOpacityMenu extends BaseMenu {
    public EditOpacityMenu(Context context) {
        super(context);
        this.mItemList.add(new OpacityItem.Opacity(ResourceManager.getStringId(context, "text_annot_menu_item_opacity_10"), 25.5f));
        this.mItemList.add(new OpacityItem.Opacity(ResourceManager.getStringId(context, "text_annot_menu_item_opacity_25"), 63.75f));
        this.mItemList.add(new OpacityItem.Opacity(ResourceManager.getStringId(context, "text_annot_menu_item_opacity_50"), 127.5f));
        this.mItemList.add(new OpacityItem.Opacity(ResourceManager.getStringId(context, "text_annot_menu_item_opacity_75"), 191.25f));
        this.mItemList.add(new OpacityItem.Opacity(ResourceManager.getStringId(context, "text_annot_menu_item_opacity_100"), 255.0f));
    }
}
