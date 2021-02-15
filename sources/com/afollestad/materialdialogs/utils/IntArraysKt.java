package com.afollestad.materialdialogs.utils;

import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0015\n\u0000\n\u0002\u0010\u001e\n\u0002\u0010\b\n\u0002\b\u0002\u001a\u001a\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u0000\u001a\u001a\u0010\u0005\u001a\u00020\u0001*\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u0000¨\u0006\u0006"}, d2 = {"appendAll", "", "values", "", "", "removeAll", "core"}, k = 2, mv = {1, 1, 16})
/* compiled from: IntArrays.kt */
public final class IntArraysKt {
    public static final int[] appendAll(int[] iArr, Collection<Integer> collection) {
        Intrinsics.checkParameterIsNotNull(iArr, "$this$appendAll");
        Intrinsics.checkParameterIsNotNull(collection, "values");
        List<Integer> mutableList = ArraysKt.toMutableList(iArr);
        mutableList.addAll(collection);
        return CollectionsKt.toIntArray(mutableList);
    }

    public static final int[] removeAll(int[] iArr, Collection<Integer> collection) {
        Intrinsics.checkParameterIsNotNull(iArr, "$this$removeAll");
        Intrinsics.checkParameterIsNotNull(collection, "values");
        List<Integer> mutableList = ArraysKt.toMutableList(iArr);
        CollectionsKt.removeAll(mutableList, new IntArraysKt$removeAll$$inlined$apply$lambda$1(collection));
        return CollectionsKt.toIntArray(mutableList);
    }
}
