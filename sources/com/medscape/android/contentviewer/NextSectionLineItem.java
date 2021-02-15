package com.medscape.android.contentviewer;

public class NextSectionLineItem extends LineItem {
    public String nextSectionTitle;

    public NextSectionLineItem(CrossLink crossLink, String str, int i, boolean z, boolean z2, boolean z3) {
        super(crossLink, (CharSequence) null, i, z, z2, z3);
        this.nextSectionTitle = str;
    }
}
