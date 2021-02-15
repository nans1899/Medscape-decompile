package com.google.android.play.core.splitinstall;

import com.google.android.play.core.tasks.Task;

final /* synthetic */ class n implements t {
    private final int a;

    n(int i) {
        this.a = i;
    }

    public final Task a(SplitInstallManager splitInstallManager) {
        return splitInstallManager.getSessionState(this.a);
    }
}
