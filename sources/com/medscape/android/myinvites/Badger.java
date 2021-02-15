package com.medscape.android.myinvites;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import com.medscape.android.R;

public class Badger {
    public static void setBadgeCount(Context context, LayerDrawable layerDrawable, int i) {
        BadgeDrawable badgeDrawable;
        Drawable findDrawableByLayerId = layerDrawable.findDrawableByLayerId(R.id.ic_badge);
        if (findDrawableByLayerId == null || !(findDrawableByLayerId instanceof BadgeDrawable)) {
            badgeDrawable = new BadgeDrawable(context);
        } else {
            badgeDrawable = (BadgeDrawable) findDrawableByLayerId;
        }
        badgeDrawable.setCount(String.valueOf(i));
        layerDrawable.mutate();
        layerDrawable.setDrawableByLayerId(R.id.ic_badge, badgeDrawable);
    }
}
