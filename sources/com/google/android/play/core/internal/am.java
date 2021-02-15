package com.google.android.play.core.internal;

import android.content.Intent;
import android.util.Log;
import com.google.android.play.core.splitinstall.b;
import java.util.List;

final class am implements Runnable {
    final /* synthetic */ List a;
    final /* synthetic */ b b;
    final /* synthetic */ an c;

    am(an anVar, List list, b bVar) {
        this.c = anVar;
        this.a = list;
        this.b = bVar;
    }

    public final void run() {
        try {
            if (!this.c.c.a((List<Intent>) this.a)) {
                an.a(this.c, this.a, this.b);
            } else {
                an.a(this.c, this.b);
            }
        } catch (Exception e) {
            Log.e("SplitCompat", "Error checking verified files.", e);
            this.b.a(-11);
        }
    }
}
