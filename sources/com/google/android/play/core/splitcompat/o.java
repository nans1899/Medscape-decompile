package com.google.android.play.core.splitcompat;

import android.content.Context;
import com.google.android.play.core.splitinstall.ac;

final class o implements Runnable {
    final /* synthetic */ Context a;

    o(Context context) {
        this.a = context;
    }

    public final void run() {
        ac.a(this.a).a(true);
    }
}
