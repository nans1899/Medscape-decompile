package com.medscape.android.reference.view;

import android.content.Context;
import com.medscape.android.contentviewer.ContentSectionDataAdapter;
import com.medscape.android.contentviewer.CrossLink;
import com.medscape.android.contentviewer.LineItem;
import com.medscape.android.reference.model.ReferenceItem;
import com.medscape.android.util.StringUtil;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class ReferencesDataAdapter extends ContentSectionDataAdapter {
    LinkedHashMap<String, ReferenceItem> mReferences;

    public ReferencesDataAdapter(Context context, LinkedHashMap<String, ReferenceItem> linkedHashMap) {
        this.mReferences = linkedHashMap;
        this.mItems = getItems();
    }

    public ArrayList<LineItem> getItems() {
        ArrayList<LineItem> arrayList = new ArrayList<>();
        LinkedHashMap<String, ReferenceItem> linkedHashMap = this.mReferences;
        if (linkedHashMap != null && linkedHashMap.size() > 0) {
            this.mItems = new ArrayList(this.mReferences.size());
            for (String str : this.mReferences.keySet()) {
                ReferenceItem referenceItem = this.mReferences.get(str);
                CrossLink crossLink = null;
                if (StringUtil.isNotEmpty(referenceItem.href)) {
                    crossLink = new CrossLink(CrossLink.Type.A, referenceItem.href);
                }
                arrayList.add(new LineItem(crossLink, referenceItem.para, 0, false, false, true));
            }
        }
        return arrayList;
    }

    public void updateItems(LinkedHashMap<String, ReferenceItem> linkedHashMap) {
        this.mReferences = linkedHashMap;
        this.mItems = getItems();
    }
}
