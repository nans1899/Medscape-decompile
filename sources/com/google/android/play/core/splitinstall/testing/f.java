package com.google.android.play.core.splitinstall.testing;

import com.google.android.play.core.splitinstall.SplitInstallSessionState;

final class f implements Runnable {
    final /* synthetic */ SplitInstallSessionState a;
    final /* synthetic */ FakeSplitInstallManager b;

    f(FakeSplitInstallManager fakeSplitInstallManager, SplitInstallSessionState splitInstallSessionState) {
        this.b = fakeSplitInstallManager;
        this.a = splitInstallSessionState;
    }

    public final void run() {
        this.b.g.a(this.a);
    }
}
