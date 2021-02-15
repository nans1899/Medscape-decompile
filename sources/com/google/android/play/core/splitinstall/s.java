package com.google.android.play.core.splitinstall;

import com.google.android.play.core.tasks.Task;
import java.util.List;

final /* synthetic */ class s implements t {
    private final List a;

    s(List list) {
        this.a = list;
    }

    public final Task a(SplitInstallManager splitInstallManager) {
        return splitInstallManager.deferredLanguageUninstall(this.a);
    }
}
