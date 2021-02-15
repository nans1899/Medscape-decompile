package com.google.android.play.core.splitinstall;

import com.google.android.play.core.tasks.Task;
import com.google.android.play.core.tasks.Tasks;

final /* synthetic */ class k implements t {
    private final SplitInstallStateUpdatedListener a;

    k(SplitInstallStateUpdatedListener splitInstallStateUpdatedListener) {
        this.a = splitInstallStateUpdatedListener;
    }

    public final Task a(SplitInstallManager splitInstallManager) {
        splitInstallManager.unregisterListener(this.a);
        return Tasks.a(null);
    }
}
