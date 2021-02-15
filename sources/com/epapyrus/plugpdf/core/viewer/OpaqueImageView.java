package com.epapyrus.plugpdf.core.viewer;

import android.content.Context;
import android.widget.ImageView;

/* compiled from: PageView */
class OpaqueImageView extends ImageView {
    public boolean isOpaque() {
        return true;
    }

    public OpaqueImageView(Context context) {
        super(context);
    }
}
