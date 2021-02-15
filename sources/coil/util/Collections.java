package coil.util;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000V\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\u0010&\n\u0000\n\u0002\u0010!\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a4\u0010\u0000\u001a\u0004\u0018\u0001H\u0001\"\u0004\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u00022\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u00020\u00050\u0004H\b¢\u0006\u0002\u0010\u0006\u001a<\u0010\u0007\u001a\u0004\u0018\u0001H\u0001\"\u0004\b\u0000\u0010\b\"\u0004\b\u0001\u0010\u0001*\b\u0012\u0004\u0012\u0002H\b0\u00022\u0014\u0010\t\u001a\u0010\u0012\u0004\u0012\u0002H\b\u0012\u0006\u0012\u0004\u0018\u0001H\u00010\u0004H\b¢\u0006\u0002\u0010\u0006\u001aF\u0010\n\u001a\u0002H\b\"\u0004\b\u0000\u0010\u0001\"\u0004\b\u0001\u0010\b*\b\u0012\u0004\u0012\u0002H\u00010\u00022\u0006\u0010\u000b\u001a\u0002H\b2\u0018\u0010\f\u001a\u0014\u0012\u0004\u0012\u0002H\b\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\b0\rH\b¢\u0006\u0002\u0010\u000e\u001a-\u0010\u000f\u001a\u00020\u0010\"\u0004\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010\u00022\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u00020\u00100\u0004H\b\u001a$\u0010\u0012\u001a\u00020\u0013*\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\u0015H\u0001\u001a9\u0010\u0018\u001a\b\u0012\u0004\u0012\u0002H\u00010\u0002\"\u0004\b\u0000\u0010\b\"\u0004\b\u0001\u0010\u0001*\b\u0012\u0004\u0012\u0002H\b0\u00022\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u0002H\b\u0012\u0004\u0012\u0002H\u00010\u0004H\b\u001a]\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u0002H\u001b\u0012\u0004\u0012\u0002H\b0\u001a\"\u0004\b\u0000\u0010\u001b\"\u0004\b\u0001\u0010\u001c\"\b\b\u0002\u0010\b*\u00020\u001d*\u000e\u0012\u0004\u0012\u0002H\u001b\u0012\u0004\u0012\u0002H\u001c0\u001a2 \u0010\t\u001a\u001c\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u001b\u0012\u0004\u0012\u0002H\u001c0\u001e\u0012\u0006\u0012\u0004\u0018\u0001H\b0\u0004H\b\u001a-\u0010\u001f\u001a\u00020\u0010\"\u0004\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010 2\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u00020\u00050\u0004H\b\u001a \u0010!\u001a\u0004\u0018\u0001H\u0001\"\u0004\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010 H\b¢\u0006\u0002\u0010\"*<\b\u0000\u0010#\u001a\u0004\b\u0000\u0010\b\u001a\u0004\b\u0001\u0010\u0001\"\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\b\u0012\u0004\u0012\u0002H\u00010$0\u00022\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\b\u0012\u0004\u0012\u0002H\u00010$0\u0002*<\b\u0000\u0010%\u001a\u0004\b\u0000\u0010\b\u001a\u0004\b\u0001\u0010\u0001\"\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\b\u0012\u0004\u0012\u0002H\u00010$0 2\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\b\u0012\u0004\u0012\u0002H\u00010$0 ¨\u0006&"}, d2 = {"findIndices", "T", "", "predicate", "Lkotlin/Function1;", "", "(Ljava/util/List;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "firstNotNullIndices", "R", "transform", "foldIndices", "initial", "operation", "Lkotlin/Function2;", "(Ljava/util/List;Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)Ljava/lang/Object;", "forEachIndices", "", "action", "growAndInsert", "", "index", "", "element", "currentSize", "mapIndices", "mapNotNullValues", "", "K", "V", "", "", "removeIfIndices", "", "removeLast", "(Ljava/util/List;)Ljava/lang/Object;", "MultiList", "Lkotlin/Pair;", "MultiMutableList", "coil-base_release"}, k = 2, mv = {1, 1, 16})
/* renamed from: coil.util.-Collections  reason: invalid class name */
/* compiled from: Collections.kt */
public final class Collections {
    public static final <T> void forEachIndices(List<? extends T> list, Function1<? super T, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(list, "$this$forEachIndices");
        Intrinsics.checkParameterIsNotNull(function1, "action");
        int size = list.size();
        for (int i = 0; i < size; i++) {
            function1.invoke(list.get(i));
        }
    }

    public static final <R, T> List<T> mapIndices(List<? extends R> list, Function1<? super R, ? extends T> function1) {
        Intrinsics.checkParameterIsNotNull(list, "$this$mapIndices");
        Intrinsics.checkParameterIsNotNull(function1, "transform");
        ArrayList arrayList = new ArrayList(list.size());
        int size = list.size();
        for (int i = 0; i < size; i++) {
            arrayList.add(function1.invoke(list.get(i)));
        }
        return arrayList;
    }

    public static final <T> T findIndices(List<? extends T> list, Function1<? super T, Boolean> function1) {
        Intrinsics.checkParameterIsNotNull(list, "$this$findIndices");
        Intrinsics.checkParameterIsNotNull(function1, "predicate");
        int size = list.size();
        for (int i = 0; i < size; i++) {
            T t = list.get(i);
            if (function1.invoke(t).booleanValue()) {
                return t;
            }
        }
        return null;
    }

    /* JADX WARNING: type inference failed for: r5v0, types: [java.lang.Object, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2<? super R, ? super T, ? extends R>] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <T, R> R foldIndices(java.util.List<? extends T> r3, R r4, kotlin.jvm.functions.Function2<? super R, ? super T, ? extends R> r5) {
        /*
            java.lang.String r0 = "$this$foldIndices"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r3, r0)
            java.lang.String r0 = "operation"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r5, r0)
            r0 = r3
            java.util.Collection r0 = (java.util.Collection) r0
            int r0 = r0.size()
            r1 = 0
        L_0x0012:
            if (r1 >= r0) goto L_0x001f
            java.lang.Object r2 = r3.get(r1)
            java.lang.Object r4 = r5.invoke(r4, r2)
            int r1 = r1 + 1
            goto L_0x0012
        L_0x001f:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: coil.util.Collections.foldIndices(java.util.List, java.lang.Object, kotlin.jvm.functions.Function2):java.lang.Object");
    }

    public static final <R, T> T firstNotNullIndices(List<? extends R> list, Function1<? super R, ? extends T> function1) {
        Intrinsics.checkParameterIsNotNull(list, "$this$firstNotNullIndices");
        Intrinsics.checkParameterIsNotNull(function1, "transform");
        int size = list.size();
        for (int i = 0; i < size; i++) {
            T invoke = function1.invoke(list.get(i));
            if (invoke != null) {
                return invoke;
            }
        }
        return null;
    }

    public static final <T> void removeIfIndices(List<T> list, Function1<? super T, Boolean> function1) {
        Intrinsics.checkParameterIsNotNull(list, "$this$removeIfIndices");
        Intrinsics.checkParameterIsNotNull(function1, "predicate");
        int size = list.size();
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            int i3 = i2 - i;
            if (function1.invoke(list.get(i3)).booleanValue()) {
                list.remove(i3);
                i++;
            }
        }
    }

    public static final <T> T removeLast(List<T> list) {
        Intrinsics.checkParameterIsNotNull(list, "$this$removeLast");
        if (!list.isEmpty()) {
            return list.remove(CollectionsKt.getLastIndex(list));
        }
        return null;
    }

    public static final <K, V, R> Map<K, R> mapNotNullValues(Map<K, ? extends V> map, Function1<? super Map.Entry<? extends K, ? extends V>, ? extends R> function1) {
        Intrinsics.checkParameterIsNotNull(map, "$this$mapNotNullValues");
        Intrinsics.checkParameterIsNotNull(function1, "transform");
        Map<K, R> linkedHashMap = new LinkedHashMap<>();
        for (Map.Entry next : map.entrySet()) {
            Object invoke = function1.invoke(next);
            if (invoke != null) {
                linkedHashMap.put(next.getKey(), invoke);
            }
        }
        return linkedHashMap;
    }

    public static final int[] growAndInsert(int[] iArr, int i, int i2, int i3) {
        Intrinsics.checkParameterIsNotNull(iArr, "$this$growAndInsert");
        if (i3 + 1 <= iArr.length) {
            ArraysKt.copyInto(iArr, iArr, i + 1, i, i3);
            iArr[i] = i2;
            return iArr;
        }
        int[] iArr2 = new int[(i3 <= 4 ? 8 : i3 * 2)];
        ArraysKt.copyInto$default(iArr, iArr2, 0, 0, i, 6, (Object) null);
        iArr2[i] = i2;
        ArraysKt.copyInto(iArr, iArr2, i + 1, i, iArr.length);
        return iArr2;
    }
}
