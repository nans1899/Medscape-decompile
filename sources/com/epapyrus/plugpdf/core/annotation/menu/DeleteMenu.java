package com.epapyrus.plugpdf.core.annotation.menu;

import android.content.Context;
import com.epapyrus.plugpdf.core.ResourceManager;
import com.epapyrus.plugpdf.core.annotation.menu.item.DeleteItem;

public class DeleteMenu extends BaseMenu {
    public DeleteMenu(Context context) {
        super(context);
        this.mItemList.add(new DeleteItem(ResourceManager.getStringId(context, "text_annot_menu_item_delete")));
    }
}
