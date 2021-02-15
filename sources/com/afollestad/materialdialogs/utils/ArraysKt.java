package com.afollestad.materialdialogs.utils;

import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0000\u001a)\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0006\b\u0000\u0010\u0002\u0018\u0001*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\b¨\u0006\u0005"}, d2 = {"pullIndices", "", "T", "indices", "", "core"}, k = 2, mv = {1, 1, 16})
/* compiled from: Arrays.kt */
public final class ArraysKt {
    public static final /* synthetic */ <T> List<T> pullIndices(List<? extends T> list, int[] iArr) {
        Intrinsics.checkParameterIsNotNull(list, "$this$pullIndices");
        Intrinsics.checkParameterIsNotNull(iArr, "indices");
        List<T> arrayList = new ArrayList<>();
        for (int i : iArr) {
            arrayList.add(list.get(i));
        }
        return arrayList;
    }
}
