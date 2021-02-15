package com.google.android.play.core.splitinstall;

import com.google.android.play.core.tasks.Task;

final /* synthetic */ class m implements t {
    private final int a;

    m(int i) {
        this.a = i;
    }

    public final Task a(SplitInstallManager splitInstallManager) {
        return splitInstallManager.cancelInstall(this.a);
    }
}
