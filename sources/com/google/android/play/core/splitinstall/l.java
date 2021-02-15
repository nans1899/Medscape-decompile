package com.google.android.play.core.splitinstall;

import com.google.android.play.core.tasks.Task;

final /* synthetic */ class l implements t {
    private final SplitInstallRequest a;

    l(SplitInstallRequest splitInstallRequest) {
        this.a = splitInstallRequest;
    }

    public final Task a(SplitInstallManager splitInstallManager) {
        return splitInstallManager.startInstall(this.a);
    }
}
