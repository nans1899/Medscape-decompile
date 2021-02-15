package com.google.android.play.core.splitinstall;

import com.google.android.play.core.tasks.Task;
import com.google.android.play.core.tasks.Tasks;

final /* synthetic */ class g implements t {
    private final SplitInstallStateUpdatedListener a;

    g(SplitInstallStateUpdatedListener splitInstallStateUpdatedListener) {
        this.a = splitInstallStateUpdatedListener;
    }

    public final Task a(SplitInstallManager splitInstallManager) {
        splitInstallManager.registerListener(this.a);
        return Tasks.a(null);
    }
}
