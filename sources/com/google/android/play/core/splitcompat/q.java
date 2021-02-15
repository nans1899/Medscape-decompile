package com.google.android.play.core.splitcompat;

import android.util.Log;
import java.util.Set;

final class q implements Runnable {
    final /* synthetic */ Set a;
    final /* synthetic */ SplitCompat b;

    q(SplitCompat splitCompat, Set set) {
        this.b = splitCompat;
        this.a = set;
    }

    public final void run() {
        try {
            for (String f : this.a) {
                this.b.b.f(f);
            }
        } catch (Exception e) {
            Log.e("SplitCompat", "Failed to remove from splitcompat storage split that is already installed", e);
        }
    }
}
