package com.google.android.play.core.splitinstall.testing;

import com.google.android.play.core.splitinstall.SplitInstallException;
import com.google.android.play.core.splitinstall.SplitInstallSessionState;

final class e implements i {
    final /* synthetic */ int a;

    e(int i) {
        this.a = i;
    }

    public final SplitInstallSessionState a(SplitInstallSessionState splitInstallSessionState) {
        if (splitInstallSessionState != null && this.a == splitInstallSessionState.sessionId() && splitInstallSessionState.status() == 1) {
            return SplitInstallSessionState.create(this.a, 7, splitInstallSessionState.errorCode(), splitInstallSessionState.bytesDownloaded(), splitInstallSessionState.totalBytesToDownload(), splitInstallSessionState.moduleNames(), splitInstallSessionState.languages());
        }
        throw new SplitInstallException(-3);
    }
}
