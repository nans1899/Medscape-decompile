package com.google.android.play.core.splitinstall;

final /* synthetic */ class i implements Runnable {
    private final u a;
    private final com.google.android.play.core.tasks.i b;

    i(u uVar, com.google.android.play.core.tasks.i iVar) {
        this.a = uVar;
        this.b = iVar;
    }

    public final void run() {
        u uVar = this.a;
        com.google.android.play.core.tasks.i iVar = this.b;
        try {
            iVar.a(uVar.a());
        } catch (Exception e) {
            iVar.a(e);
        }
    }
}
