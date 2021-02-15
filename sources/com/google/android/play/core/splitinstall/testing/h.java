package com.google.android.play.core.splitinstall.testing;

import android.content.Intent;
import com.google.android.play.core.splitinstall.b;
import java.util.List;

final class h implements b {
    final /* synthetic */ List a;
    final /* synthetic */ List b;
    final /* synthetic */ long c;
    final /* synthetic */ boolean d;
    final /* synthetic */ List e;
    final /* synthetic */ FakeSplitInstallManager f;

    h(FakeSplitInstallManager fakeSplitInstallManager, List list, List list2, long j, boolean z, List list3) {
        this.f = fakeSplitInstallManager;
        this.a = list;
        this.b = list2;
        this.c = j;
        this.d = z;
        this.e = list3;
    }

    public final void a() {
        this.f.k.addAll(this.a);
        this.f.l.addAll(this.b);
        this.f.a(5, 0, Long.valueOf(this.c), (Long) null, (List<String>) null, (Integer) null, (List<String>) null);
    }

    public final void a(int i) {
        this.f.a(6, i, (Long) null, (Long) null, (List<String>) null, (Integer) null, (List<String>) null);
    }

    public final void b() {
        if (!this.d) {
            this.f.a((List<Intent>) this.e, (List<String>) this.a, (List<String>) this.b, this.c, true);
        }
    }
}
