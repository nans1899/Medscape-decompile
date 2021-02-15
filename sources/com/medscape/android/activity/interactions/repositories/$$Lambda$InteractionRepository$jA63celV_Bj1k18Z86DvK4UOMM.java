package com.medscape.android.activity.interactions.repositories;

import java.util.concurrent.Executor;

/* renamed from: com.medscape.android.activity.interactions.repositories.-$$Lambda$InteractionRepository$j-A63celV_Bj1k18Z86DvK4UOMM  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$InteractionRepository$jA63celV_Bj1k18Z86DvK4UOMM implements Executor {
    public static final /* synthetic */ $$Lambda$InteractionRepository$jA63celV_Bj1k18Z86DvK4UOMM INSTANCE = new $$Lambda$InteractionRepository$jA63celV_Bj1k18Z86DvK4UOMM();

    private /* synthetic */ $$Lambda$InteractionRepository$jA63celV_Bj1k18Z86DvK4UOMM() {
    }

    public final void execute(Runnable runnable) {
        new Thread(runnable).start();
    }
}
