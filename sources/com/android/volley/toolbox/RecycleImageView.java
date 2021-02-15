package com.android.volley.toolbox;

import android.view.View;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

public class RecycleImageView {
    protected boolean checkActualViewSize;
    protected Reference<View> viewRef;

    public RecycleImageView(View view) {
        this(view, true);
    }

    public RecycleImageView(View view, boolean z) {
        if (view != null) {
            this.viewRef = new WeakReference(view);
            this.checkActualViewSize = z;
            return;
        }
        throw new IllegalArgumentException("view must not be null");
    }

    public int getId() {
        View view = this.viewRef.get();
        return view == null ? super.hashCode() : view.hashCode();
    }
}
