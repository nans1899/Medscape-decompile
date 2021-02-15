package com.medscape.android.reference.view;

import com.medscape.android.contentviewer.ContentSectionDataAdapter;
import com.medscape.android.contentviewer.CrossLink;
import com.medscape.android.contentviewer.LineItem;
import com.medscape.android.reference.model.Contributor;
import com.medscape.android.util.StringUtil;
import java.util.ArrayList;
import java.util.Iterator;

public class ContributorsDataAdapter extends ContentSectionDataAdapter {
    ArrayList<Contributor> mContributors;

    public ContributorsDataAdapter(ArrayList<Contributor> arrayList) {
        this.mContributors = arrayList;
        this.mItems = getItems();
    }

    public ArrayList<LineItem> getItems() {
        ArrayList<LineItem> arrayList = new ArrayList<>();
        ArrayList<Contributor> arrayList2 = this.mContributors;
        if (arrayList2 != null && arrayList2.size() > 0) {
            this.mItems = new ArrayList();
            Iterator<Contributor> it = this.mContributors.iterator();
            while (it.hasNext()) {
                Contributor next = it.next();
                int size = arrayList.size();
                if (StringUtil.isNotEmpty(next.typeLabel)) {
                    arrayList.add(new LineItem((CrossLink) null, next.typeLabel, size, true, false, false));
                }
                if (next.contributorPara != null) {
                    arrayList.add(new LineItem((CrossLink) null, next.contributorPara, size, false, false, true));
                }
            }
        }
        return arrayList;
    }

    public void updateItems(ArrayList<Contributor> arrayList) {
        this.mContributors = arrayList;
        this.mItems = getItems();
    }
}
