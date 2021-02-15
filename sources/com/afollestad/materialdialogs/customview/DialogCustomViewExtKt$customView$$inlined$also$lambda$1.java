package com.afollestad.materialdialogs.customview;

import android.view.View;
import com.afollestad.materialdialogs.MaterialDialog;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n¢\u0006\u0002\b\u0003¨\u0006\u0004"}, d2 = {"<anonymous>", "", "Landroid/view/View;", "invoke", "com/afollestad/materialdialogs/customview/DialogCustomViewExtKt$customView$1$1"}, k = 3, mv = {1, 1, 16})
/* compiled from: DialogCustomViewExt.kt */
final class DialogCustomViewExtKt$customView$$inlined$also$lambda$1 extends Lambda implements Function1<View, Unit> {
    final /* synthetic */ boolean $dialogWrapContent$inlined;
    final /* synthetic */ MaterialDialog $this_customView$inlined;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    DialogCustomViewExtKt$customView$$inlined$also$lambda$1(MaterialDialog materialDialog, boolean z) {
        super(1);
        this.$this_customView$inlined = materialDialog;
        this.$dialogWrapContent$inlined = z;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((View) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(View view) {
        Intrinsics.checkParameterIsNotNull(view, "$receiver");
        MaterialDialog.maxWidth$default(this.$this_customView$inlined, (Integer) null, Integer.valueOf(view.getMeasuredWidth()), 1, (Object) null);
    }
}
