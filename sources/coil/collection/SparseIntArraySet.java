package coil.collection;

import coil.util.Collections;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0015\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0011\b\u0007\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0003J\u0006\u0010\r\u001a\u00020\u000eJ\u0011\u0010\u000f\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0003H\u0002J\u000e\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u0003J\u000e\u0010\u0012\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\u0003J\u000e\u0010\u0013\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0003J\u000e\u0010\u0014\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u0003R\u000e\u0010\u0005\u001a\u00020\u0003X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\b\u001a\u00020\u00038G¢\u0006\u0006\u001a\u0004\b\b\u0010\t¨\u0006\u0015"}, d2 = {"Lcoil/collection/SparseIntArraySet;", "", "initialCapacity", "", "(I)V", "_size", "elements", "", "size", "()I", "add", "", "element", "clear", "", "contains", "elementAt", "index", "indexOfElement", "remove", "removeAt", "coil-base_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: SparseIntArraySet.kt */
public final class SparseIntArraySet {
    private int _size;
    private int[] elements;

    public SparseIntArraySet() {
        this(0, 1, (DefaultConstructorMarker) null);
    }

    public SparseIntArraySet(int i) {
        this.elements = new int[i];
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ SparseIntArraySet(int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? 10 : i);
    }

    public final int size() {
        return this._size;
    }

    public final boolean add(int i) {
        int binarySearch$default = ArraysKt.binarySearch$default(this.elements, i, 0, this._size, 2, (Object) null);
        boolean z = binarySearch$default < 0;
        if (z) {
            this.elements = Collections.growAndInsert(this.elements, ~binarySearch$default, i, this._size);
            this._size++;
        }
        return z;
    }

    public final boolean remove(int i) {
        int binarySearch$default = ArraysKt.binarySearch$default(this.elements, i, 0, this._size, 2, (Object) null);
        boolean z = binarySearch$default >= 0;
        if (z) {
            removeAt(binarySearch$default);
        }
        return z;
    }

    public final boolean contains(int i) {
        return ArraysKt.binarySearch$default(this.elements, i, 0, this._size, 2, (Object) null) >= 0;
    }

    public final void removeAt(int i) {
        int[] iArr = this.elements;
        ArraysKt.copyInto(iArr, iArr, i, i + 1, this._size);
        this._size--;
    }

    public final int elementAt(int i) {
        return this.elements[i];
    }

    public final int indexOfElement(int i) {
        return ArraysKt.binarySearch$default(this.elements, i, 0, this._size, 2, (Object) null);
    }

    public final void clear() {
        this._size = 0;
    }
}
