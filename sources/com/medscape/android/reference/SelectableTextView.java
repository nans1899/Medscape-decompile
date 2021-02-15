package com.medscape.android.reference;

import android.content.Context;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatTextView;

public class SelectableTextView extends AppCompatTextView {
    private boolean mEnabled;

    public SelectableTextView(Context context) {
        super(context);
    }

    public SelectableTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public SelectableTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        try {
            if (this.mEnabled) {
                super.setEnabled(false);
                super.setEnabled(this.mEnabled);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setEnabled(boolean z) {
        this.mEnabled = z;
        super.setEnabled(z);
    }
}
