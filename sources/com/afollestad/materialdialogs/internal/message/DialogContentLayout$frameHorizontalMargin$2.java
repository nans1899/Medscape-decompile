package com.afollestad.materialdialogs.internal.message;

import com.afollestad.materialdialogs.R;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\b\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 1, 16})
/* compiled from: DialogContentLayout.kt */
final class DialogContentLayout$frameHorizontalMargin$2 extends Lambda implements Function0<Integer> {
    final /* synthetic */ DialogContentLayout this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    DialogContentLayout$frameHorizontalMargin$2(DialogContentLayout dialogContentLayout) {
        super(0);
        this.this$0 = dialogContentLayout;
    }

    public final int invoke() {
        return this.this$0.getResources().getDimensionPixelSize(R.dimen.md_dialog_frame_margin_horizontal);
    }
}
