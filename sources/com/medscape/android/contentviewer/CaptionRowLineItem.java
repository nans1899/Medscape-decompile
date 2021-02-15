package com.medscape.android.contentviewer;

public class CaptionRowLineItem extends LineItem {
    public boolean isTableCaption;

    public CaptionRowLineItem(CrossLink crossLink, CharSequence charSequence, boolean z, int i, boolean z2, boolean z3, boolean z4) {
        super(crossLink, charSequence, i, z2, z3, z4);
        this.isTableCaption = z;
    }
}
