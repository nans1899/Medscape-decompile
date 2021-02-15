package com.afollestad.date.util;

import android.view.View;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\tR\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/afollestad/date/util/Debouncer;", "", "()V", "enableAgain", "Ljava/lang/Runnable;", "enabled", "", "canPerform", "view", "Landroid/view/View;", "com.afollestad.date-picker"}, k = 1, mv = {1, 1, 15})
/* compiled from: Debouncer.kt */
public final class Debouncer {
    public static final Debouncer INSTANCE = new Debouncer();
    private static final Runnable enableAgain = Debouncer$enableAgain$1.INSTANCE;
    /* access modifiers changed from: private */
    public static volatile boolean enabled = true;

    private Debouncer() {
    }

    public final boolean canPerform(View view) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        if (!enabled) {
            return false;
        }
        enabled = false;
        view.post(enableAgain);
        return true;
    }
}
