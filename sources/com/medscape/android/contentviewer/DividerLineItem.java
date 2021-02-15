package com.medscape.android.contentviewer;

public class DividerLineItem extends LineItem {
    public boolean dragDown;
    public int layoutMarginTop;
    public String nextSection;
    public String text;
    public boolean visible;

    public DividerLineItem(CrossLink crossLink, String str, int i, boolean z, boolean z2, boolean z3, boolean z4, int i2, String str2, boolean z5) {
        super(crossLink, str, i, z, z2, z3);
        this.visible = z4;
        this.dragDown = z5;
        this.layoutMarginTop = i2;
        this.text = str;
        this.nextSection = str2;
    }
}
