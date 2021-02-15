package coil.extension;

import coil.collection.SparseIntArraySet;
import kotlin.Metadata;
import kotlin.collections.IntIterator;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\t\u0010\b\u001a\u00020\tH\u0002J\b\u0010\n\u001a\u00020\u0003H\u0016R\u001a\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0004\u0010\u0005\"\u0004\b\u0006\u0010\u0007¨\u0006\u000b"}, d2 = {"coil/extension/SparseIntArraySets$iterator$1", "Lkotlin/collections/IntIterator;", "index", "", "getIndex", "()I", "setIndex", "(I)V", "hasNext", "", "nextInt", "coil-base_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: SparseIntArraySets.kt */
public final class SparseIntArraySets$iterator$1 extends IntIterator {
    final /* synthetic */ SparseIntArraySet $this_iterator;
    private int index;

    SparseIntArraySets$iterator$1(SparseIntArraySet sparseIntArraySet) {
        this.$this_iterator = sparseIntArraySet;
    }

    public final int getIndex() {
        return this.index;
    }

    public final void setIndex(int i) {
        this.index = i;
    }

    public boolean hasNext() {
        return this.index < this.$this_iterator.size();
    }

    public int nextInt() {
        SparseIntArraySet sparseIntArraySet = this.$this_iterator;
        int i = this.index;
        this.index = i + 1;
        return sparseIntArraySet.elementAt(i);
    }
}
