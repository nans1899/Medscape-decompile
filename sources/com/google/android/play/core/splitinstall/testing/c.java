package com.google.android.play.core.splitinstall.testing;

import java.util.List;

final class c implements Runnable {
    final /* synthetic */ List a;
    final /* synthetic */ List b;
    final /* synthetic */ FakeSplitInstallManager c;

    c(FakeSplitInstallManager fakeSplitInstallManager, List list, List list2) {
        this.c = fakeSplitInstallManager;
        this.a = list;
        this.b = list2;
    }

    public final void run() {
        FakeSplitInstallManager.a(this.c, this.a, this.b);
    }
}
