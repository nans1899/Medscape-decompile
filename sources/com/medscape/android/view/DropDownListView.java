package com.medscape.android.view;

import android.content.Context;
import android.widget.ListView;

public class DropDownListView extends ListView {
    private boolean mListSelectionHidden;

    public boolean hasFocus() {
        return true;
    }

    public boolean hasWindowFocus() {
        return true;
    }

    public boolean isFocused() {
        return true;
    }

    public DropDownListView(Context context) {
        super(context);
    }

    public boolean isInTouchMode() {
        return this.mListSelectionHidden || super.isInTouchMode();
    }

    /* access modifiers changed from: protected */
    public int[] onCreateDrawableState(int i) {
        return super.onCreateDrawableState(i);
    }

    public void setListSelectionHidden(boolean z) {
        this.mListSelectionHidden = z;
    }

    public boolean getListSelectionHidden() {
        return this.mListSelectionHidden;
    }
}
