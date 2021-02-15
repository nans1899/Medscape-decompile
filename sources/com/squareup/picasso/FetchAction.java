package com.squareup.picasso;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import com.squareup.picasso.Picasso;

class FetchAction extends Action<Object> {
    private Callback callback;
    private final Object target = new Object();

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    FetchAction(Picasso picasso, Request request, int i, int i2, Object obj, String str, Callback callback2) {
        super(picasso, null, request, i, i2, 0, (Drawable) null, str, obj, false);
        this.callback = callback2;
    }

    /* access modifiers changed from: package-private */
    public void complete(Bitmap bitmap, Picasso.LoadedFrom loadedFrom) {
        Callback callback2 = this.callback;
        if (callback2 != null) {
            callback2.onSuccess();
        }
    }

    /* access modifiers changed from: package-private */
    public void error(Exception exc) {
        Callback callback2 = this.callback;
        if (callback2 != null) {
            callback2.onError(exc);
        }
    }

    /* access modifiers changed from: package-private */
    public void cancel() {
        super.cancel();
        this.callback = null;
    }

    /* access modifiers changed from: package-private */
    public Object getTarget() {
        return this.target;
    }
}
