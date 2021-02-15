package com.wbmd.decisionpoint.extensions;

import android.view.View;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0000\u001a\f\u0010\u0003\u001a\u00020\u0001*\u00020\u0002H\u0000¨\u0006\u0004"}, d2 = {"gone", "", "Landroid/view/View;", "visible", "wbmd.decisionpoint_release"}, k = 2, mv = {1, 4, 0})
/* compiled from: ViewExtensions.kt */
public final class ViewExtensionsKt {
    public static final void visible(View view) {
        Intrinsics.checkNotNullParameter(view, "$this$visible");
        view.setVisibility(0);
    }

    public static final void gone(View view) {
        Intrinsics.checkNotNullParameter(view, "$this$gone");
        view.setVisibility(8);
    }
}
