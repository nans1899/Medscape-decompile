package com.afollestad.date.util;

import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "run"}, k = 3, mv = {1, 1, 15})
/* compiled from: Debouncer.kt */
final class Debouncer$enableAgain$1 implements Runnable {
    public static final Debouncer$enableAgain$1 INSTANCE = new Debouncer$enableAgain$1();

    Debouncer$enableAgain$1() {
    }

    public final void run() {
        Debouncer.enabled = true;
    }
}
