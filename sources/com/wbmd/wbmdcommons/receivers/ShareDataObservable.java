package com.wbmd.wbmdcommons.receivers;

import java.util.Observable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lcom/wbmd/wbmdcommons/receivers/ShareDataObservable;", "Ljava/util/Observable;", "()V", "updateData", "", "value", "", "wbmdcommons_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: ShareReceiver.kt */
public final class ShareDataObservable extends Observable {
    public static final ShareDataObservable INSTANCE = new ShareDataObservable();

    private ShareDataObservable() {
    }

    public final void updateData(String str) {
        Intrinsics.checkNotNullParameter(str, "value");
        setChanged();
        notifyObservers(str);
    }
}
