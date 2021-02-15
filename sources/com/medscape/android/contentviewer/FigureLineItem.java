package com.medscape.android.contentviewer;

public class FigureLineItem extends LineItem {
    public String figureId;

    public FigureLineItem(CrossLink crossLink, String str, int i, boolean z, boolean z2, boolean z3) {
        super(crossLink, (CharSequence) null, i, z, z2, z3);
        this.figureId = str;
    }
}
