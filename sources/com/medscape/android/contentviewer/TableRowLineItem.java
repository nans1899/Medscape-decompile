package com.medscape.android.contentviewer;

import com.medscape.android.reference.model.Para;
import java.util.ArrayList;

public class TableRowLineItem extends LineItem {
    public boolean isTableHeader;
    public int paddingPosition = 0;
    public ArrayList<Para> tableColumns;

    public TableRowLineItem(CrossLink crossLink, ArrayList<Para> arrayList, boolean z, int i, int i2, boolean z2, boolean z3, boolean z4) {
        super(crossLink, (CharSequence) null, i2, z2, z3, z4);
        this.tableColumns = arrayList;
        this.isTableHeader = z;
        this.paddingPosition = i;
    }
}
