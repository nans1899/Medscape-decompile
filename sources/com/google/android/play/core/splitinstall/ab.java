package com.google.android.play.core.splitinstall;

final class ab implements Runnable {
    final /* synthetic */ SplitInstallSessionState a;
    final /* synthetic */ int b;
    final /* synthetic */ int c;
    final /* synthetic */ ac d;

    ab(ac acVar, SplitInstallSessionState splitInstallSessionState, int i, int i2) {
        this.d = acVar;
        this.a = splitInstallSessionState;
        this.b = i;
        this.c = i2;
    }

    public final void run() {
        ac acVar = this.d;
        SplitInstallSessionState splitInstallSessionState = this.a;
        acVar.a(new a(splitInstallSessionState.sessionId(), this.b, this.c, splitInstallSessionState.bytesDownloaded(), splitInstallSessionState.totalBytesToDownload(), splitInstallSessionState.a(), splitInstallSessionState.b(), splitInstallSessionState.resolutionIntent(), splitInstallSessionState.c()));
    }
}
