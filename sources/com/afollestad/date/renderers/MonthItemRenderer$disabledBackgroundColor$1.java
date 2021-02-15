package com.afollestad.date.renderers;

import com.afollestad.date.renderers.MonthItemRenderer;
import com.afollestad.date.util.ColorsKt;
import com.afollestad.date.util.ContextsKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\b\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 1, 15})
/* compiled from: MonthItemRenderer.kt */
final class MonthItemRenderer$disabledBackgroundColor$1 extends Lambda implements Function0<Integer> {
    final /* synthetic */ MonthItemRenderer this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    MonthItemRenderer$disabledBackgroundColor$1(MonthItemRenderer monthItemRenderer) {
        super(0);
        this.this$0 = monthItemRenderer;
    }

    public final int invoke() {
        int resolveColor$default = ContextsKt.resolveColor$default(this.this$0.context, 16842808, (Function0) null, 2, (Object) null);
        MonthItemRenderer.Companion unused = MonthItemRenderer.Companion;
        return ColorsKt.withAlpha(resolveColor$default, 0.3f);
    }
}
