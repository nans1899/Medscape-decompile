package com.medscape.android.drugs.details.repositories;

import com.medscape.android.drugs.details.datamodels.InteractionListItem;
import java.util.Comparator;
import kotlin.Metadata;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\n\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u000e\u0010\u0003\u001a\n \u0004*\u0004\u0018\u0001H\u0002H\u00022\u000e\u0010\u0005\u001a\n \u0004*\u0004\u0018\u0001H\u0002H\u0002H\n¢\u0006\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"<anonymous>", "", "T", "a", "kotlin.jvm.PlatformType", "b", "compare", "(Ljava/lang/Object;Ljava/lang/Object;)I", "kotlin/comparisons/ComparisonsKt__ComparisonsKt$compareBy$2"}, k = 3, mv = {1, 4, 0})
/* compiled from: Comparisons.kt */
public final class DrugDetailInteractionRepository$sortInteractions$$inlined$compareBy$3<T> implements Comparator<T> {
    public final int compare(T t, T t2) {
        String obj = ((InteractionListItem) t).getText().toString();
        if (obj != null) {
            String lowerCase = obj.toLowerCase();
            Intrinsics.checkNotNullExpressionValue(lowerCase, "(this as java.lang.String).toLowerCase()");
            Comparable comparable = lowerCase;
            String obj2 = ((InteractionListItem) t2).getText().toString();
            if (obj2 != null) {
                String lowerCase2 = obj2.toLowerCase();
                Intrinsics.checkNotNullExpressionValue(lowerCase2, "(this as java.lang.String).toLowerCase()");
                return ComparisonsKt.compareValues(comparable, lowerCase2);
            }
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
        throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
    }
}
