package com.epapyrus.plugpdf.core.annotation.menu;

import android.content.Context;
import com.epapyrus.plugpdf.core.ResourceManager;
import com.epapyrus.plugpdf.core.annotation.menu.item.DeleteItem;
import com.epapyrus.plugpdf.core.annotation.menu.item.EditItem;

public class EditMenu extends BaseMenu {
    public EditMenu(Context context) {
        super(context);
        this.mItemList.add(new DeleteItem(ResourceManager.getStringId(context, "text_annot_menu_item_delete")));
        this.mItemList.add(new EditItem(context, ResourceManager.getStringId(context, "text_annot_menu_item_edit")));
    }
}
