package com.google.android.play.core.splitinstall;

import android.content.Context;
import com.google.android.play.core.splitcompat.d;

public class SplitInstallManagerFactory {
    public static SplitInstallManager create(Context context) {
        return u.a(context, d.a());
    }
}
