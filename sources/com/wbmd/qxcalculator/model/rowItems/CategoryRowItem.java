package com.wbmd.qxcalculator.model.rowItems;

import com.qxmd.qxrecyclerview.QxRecyclerViewRowItem;
import com.wbmd.qxcalculator.model.db.DBCategory;

public abstract class CategoryRowItem extends QxRecyclerViewRowItem {
    public final DBCategory category;

    CategoryRowItem(DBCategory dBCategory) {
        this.category = dBCategory;
    }

    public String getId() {
        return this.category.getIdentifier();
    }
}
