package coil.extension;

import coil.collection.SparseIntArraySet;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.IntIterator;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0002\u001a\r\u0010\u0004\u001a\u00020\u0005*\u00020\u0002H\b\u001a0\u0010\u0006\u001a\u00020\u0001*\u00020\u00022!\u0010\u0007\u001a\u001d\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000b\u0012\u0004\u0012\u00020\u00010\bH\b\u001a\r\u0010\f\u001a\u00020\r*\u00020\u0002H\b\u001a\r\u0010\u000e\u001a\u00020\r*\u00020\u0002H\b\u001a\r\u0010\u000f\u001a\u00020\u0010*\u00020\u0002H\u0002\u001a\u0015\u0010\u0011\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u000b\u001a\u00020\u0005H\n\u001a\u0015\u0010\u0012\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0002H\u0002\u001a\u0015\u0010\u0013\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u000b\u001a\u00020\u0005H\n¨\u0006\u0014"}, d2 = {"addAll", "", "Lcoil/collection/SparseIntArraySet;", "other", "count", "", "forEach", "action", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "element", "isEmpty", "", "isNotEmpty", "iterator", "Lkotlin/collections/IntIterator;", "minusAssign", "plus", "plusAssign", "coil-base_release"}, k = 2, mv = {1, 1, 16})
/* compiled from: SparseIntArraySets.kt */
public final class SparseIntArraySets {
    public static final int count(SparseIntArraySet sparseIntArraySet) {
        Intrinsics.checkParameterIsNotNull(sparseIntArraySet, "$this$count");
        return sparseIntArraySet.size();
    }

    public static final void plusAssign(SparseIntArraySet sparseIntArraySet, int i) {
        Intrinsics.checkParameterIsNotNull(sparseIntArraySet, "$this$plusAssign");
        sparseIntArraySet.add(i);
    }

    public static final void minusAssign(SparseIntArraySet sparseIntArraySet, int i) {
        Intrinsics.checkParameterIsNotNull(sparseIntArraySet, "$this$minusAssign");
        sparseIntArraySet.remove(i);
    }

    public static final boolean isEmpty(SparseIntArraySet sparseIntArraySet) {
        Intrinsics.checkParameterIsNotNull(sparseIntArraySet, "$this$isEmpty");
        return sparseIntArraySet.size() == 0;
    }

    public static final boolean isNotEmpty(SparseIntArraySet sparseIntArraySet) {
        Intrinsics.checkParameterIsNotNull(sparseIntArraySet, "$this$isNotEmpty");
        return sparseIntArraySet.size() != 0;
    }

    public static final SparseIntArraySet plus(SparseIntArraySet sparseIntArraySet, SparseIntArraySet sparseIntArraySet2) {
        Intrinsics.checkParameterIsNotNull(sparseIntArraySet, "$this$plus");
        Intrinsics.checkParameterIsNotNull(sparseIntArraySet2, "other");
        SparseIntArraySet sparseIntArraySet3 = new SparseIntArraySet(sparseIntArraySet.size() + sparseIntArraySet2.size());
        addAll(sparseIntArraySet3, sparseIntArraySet);
        addAll(sparseIntArraySet3, sparseIntArraySet2);
        return sparseIntArraySet3;
    }

    public static final void forEach(SparseIntArraySet sparseIntArraySet, Function1<? super Integer, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(sparseIntArraySet, "$this$forEach");
        Intrinsics.checkParameterIsNotNull(function1, "action");
        int size = sparseIntArraySet.size();
        for (int i = 0; i < size; i++) {
            function1.invoke(Integer.valueOf(sparseIntArraySet.elementAt(i)));
        }
    }

    public static final IntIterator iterator(SparseIntArraySet sparseIntArraySet) {
        Intrinsics.checkParameterIsNotNull(sparseIntArraySet, "$this$iterator");
        return new SparseIntArraySets$iterator$1(sparseIntArraySet);
    }

    public static final void addAll(SparseIntArraySet sparseIntArraySet, SparseIntArraySet sparseIntArraySet2) {
        Intrinsics.checkParameterIsNotNull(sparseIntArraySet, "$this$addAll");
        Intrinsics.checkParameterIsNotNull(sparseIntArraySet2, "other");
        int size = sparseIntArraySet2.size();
        for (int i = 0; i < size; i++) {
            sparseIntArraySet.add(sparseIntArraySet2.elementAt(i));
        }
    }
}
