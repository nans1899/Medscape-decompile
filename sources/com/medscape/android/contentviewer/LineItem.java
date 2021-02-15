package com.medscape.android.contentviewer;

public class LineItem<T> {
    public CrossLink crossLink;
    public int indentation;
    public boolean isContributor;
    public boolean isHeader;
    public boolean isListItem;
    public boolean isSubsection;
    public boolean isTopDividerNeeded;
    private T mObject;
    public int sectionFirstPosition;
    public CharSequence text;

    public LineItem(CrossLink crossLink2, CharSequence charSequence, int i, boolean z, boolean z2, boolean z3) {
        setValues(crossLink2, charSequence, i, z, z2, z3, false);
    }

    public LineItem(CrossLink crossLink2, CharSequence charSequence, int i, boolean z, boolean z2, boolean z3, boolean z4) {
        setValues(crossLink2, charSequence, i, z, z2, z3, z4);
    }

    private void setValues(CrossLink crossLink2, CharSequence charSequence, int i, boolean z, boolean z2, boolean z3, boolean z4) {
        this.crossLink = crossLink2;
        this.isHeader = z;
        this.isSubsection = z2;
        this.isListItem = z3;
        this.text = charSequence;
        this.sectionFirstPosition = i;
        this.isTopDividerNeeded = false;
        this.isContributor = z4;
    }

    public T getObject() {
        return this.mObject;
    }

    public void setObject(T t) {
        this.mObject = t;
    }

    public void setIndentation(int i) {
        if (!this.isHeader) {
            this.indentation = this.isSubsection ? i - 1 : i + 1;
        }
    }
}
