package com.wbmd.wbmdcommons.utils;

import android.view.View;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "run"}, k = 3, mv = {1, 4, 0})
/* compiled from: ViewUtil.kt */
final class ViewUtil$showSaveOverLay$1 implements Runnable {
    final /* synthetic */ View $view;

    ViewUtil$showSaveOverLay$1(View view) {
        this.$view = view;
    }

    public final void run() {
        AnimUtil.INSTANCE.fadeOut(this.$view, 800);
    }
}
