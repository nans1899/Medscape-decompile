package androidx.paging;

import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

final class PagedStorage<T> extends AbstractList<T> {
    private static final List PLACEHOLDER_LIST = new ArrayList();
    private int mLeadingNullCount;
    private int mLoadedCount;
    private int mNumberAppended;
    private int mNumberPrepended;
    private int mPageSize;
    private final ArrayList<List<T>> mPages;
    private int mPositionOffset;
    private int mStorageCount;
    private int mTrailingNullCount;

    interface Callback {
        void onEmptyAppend();

        void onEmptyPrepend();

        void onInitialized(int i);

        void onPageAppended(int i, int i2, int i3);

        void onPageInserted(int i, int i2);

        void onPagePlaceholderInserted(int i);

        void onPagePrepended(int i, int i2, int i3);

        void onPagesRemoved(int i, int i2);

        void onPagesSwappedToPlaceholder(int i, int i2);
    }

    PagedStorage() {
        this.mLeadingNullCount = 0;
        this.mPages = new ArrayList<>();
        this.mTrailingNullCount = 0;
        this.mPositionOffset = 0;
        this.mLoadedCount = 0;
        this.mStorageCount = 0;
        this.mPageSize = 1;
        this.mNumberPrepended = 0;
        this.mNumberAppended = 0;
    }

    PagedStorage(int i, List<T> list, int i2) {
        this();
        init(i, list, i2, 0);
    }

    private PagedStorage(PagedStorage<T> pagedStorage) {
        this.mLeadingNullCount = pagedStorage.mLeadingNullCount;
        this.mPages = new ArrayList<>(pagedStorage.mPages);
        this.mTrailingNullCount = pagedStorage.mTrailingNullCount;
        this.mPositionOffset = pagedStorage.mPositionOffset;
        this.mLoadedCount = pagedStorage.mLoadedCount;
        this.mStorageCount = pagedStorage.mStorageCount;
        this.mPageSize = pagedStorage.mPageSize;
        this.mNumberPrepended = pagedStorage.mNumberPrepended;
        this.mNumberAppended = pagedStorage.mNumberAppended;
    }

    /* access modifiers changed from: package-private */
    public PagedStorage<T> snapshot() {
        return new PagedStorage<>(this);
    }

    private void init(int i, List<T> list, int i2, int i3) {
        this.mLeadingNullCount = i;
        this.mPages.clear();
        this.mPages.add(list);
        this.mTrailingNullCount = i2;
        this.mPositionOffset = i3;
        int size = list.size();
        this.mLoadedCount = size;
        this.mStorageCount = size;
        this.mPageSize = list.size();
        this.mNumberPrepended = 0;
        this.mNumberAppended = 0;
    }

    /* access modifiers changed from: package-private */
    public void init(int i, List<T> list, int i2, int i3, Callback callback) {
        init(i, list, i2, i3);
        callback.onInitialized(size());
    }

    public T get(int i) {
        int i2;
        if (i < 0 || i >= size()) {
            throw new IndexOutOfBoundsException("Index: " + i + ", Size: " + size());
        }
        int i3 = i - this.mLeadingNullCount;
        if (i3 >= 0 && i3 < this.mStorageCount) {
            if (isTiled()) {
                int i4 = this.mPageSize;
                i2 = i3 / i4;
                i3 %= i4;
            } else {
                int size = this.mPages.size();
                i2 = 0;
                while (i2 < size) {
                    int size2 = this.mPages.get(i2).size();
                    if (size2 > i3) {
                        break;
                    }
                    i3 -= size2;
                    i2++;
                }
            }
            List list = this.mPages.get(i2);
            if (!(list == null || list.size() == 0)) {
                return list.get(i3);
            }
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public boolean isTiled() {
        return this.mPageSize > 0;
    }

    /* access modifiers changed from: package-private */
    public int getLeadingNullCount() {
        return this.mLeadingNullCount;
    }

    /* access modifiers changed from: package-private */
    public int getTrailingNullCount() {
        return this.mTrailingNullCount;
    }

    /* access modifiers changed from: package-private */
    public int getStorageCount() {
        return this.mStorageCount;
    }

    /* access modifiers changed from: package-private */
    public int getNumberAppended() {
        return this.mNumberAppended;
    }

    /* access modifiers changed from: package-private */
    public int getNumberPrepended() {
        return this.mNumberPrepended;
    }

    /* access modifiers changed from: package-private */
    public int getPageCount() {
        return this.mPages.size();
    }

    /* access modifiers changed from: package-private */
    public int getLoadedCount() {
        return this.mLoadedCount;
    }

    /* access modifiers changed from: package-private */
    public int getPositionOffset() {
        return this.mPositionOffset;
    }

    /* access modifiers changed from: package-private */
    public int getMiddleOfLoadedRange() {
        return this.mLeadingNullCount + this.mPositionOffset + (this.mStorageCount / 2);
    }

    public int size() {
        return this.mLeadingNullCount + this.mStorageCount + this.mTrailingNullCount;
    }

    /* access modifiers changed from: package-private */
    public int computeLeadingNulls() {
        int i = this.mLeadingNullCount;
        int size = this.mPages.size();
        for (int i2 = 0; i2 < size; i2++) {
            List list = this.mPages.get(i2);
            if (list != null && list != PLACEHOLDER_LIST) {
                break;
            }
            i += this.mPageSize;
        }
        return i;
    }

    /* access modifiers changed from: package-private */
    public int computeTrailingNulls() {
        int i = this.mTrailingNullCount;
        for (int size = this.mPages.size() - 1; size >= 0; size--) {
            List list = this.mPages.get(size);
            if (list != null && list != PLACEHOLDER_LIST) {
                break;
            }
            i += this.mPageSize;
        }
        return i;
    }

    private boolean needsTrim(int i, int i2, int i3) {
        List list = this.mPages.get(i3);
        return list == null || (this.mLoadedCount > i && this.mPages.size() > 2 && list != PLACEHOLDER_LIST && this.mLoadedCount - list.size() >= i2);
    }

    /* access modifiers changed from: package-private */
    public boolean needsTrimFromFront(int i, int i2) {
        return needsTrim(i, i2, 0);
    }

    /* access modifiers changed from: package-private */
    public boolean needsTrimFromEnd(int i, int i2) {
        return needsTrim(i, i2, this.mPages.size() - 1);
    }

    /* access modifiers changed from: package-private */
    public boolean shouldPreTrimNewPage(int i, int i2, int i3) {
        if (this.mLoadedCount + i3 <= i || this.mPages.size() <= 1 || this.mLoadedCount < i2) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public boolean trimFromFront(boolean z, int i, int i2, Callback callback) {
        int i3 = 0;
        while (needsTrimFromFront(i, i2)) {
            List remove = this.mPages.remove(0);
            int size = remove == null ? this.mPageSize : remove.size();
            i3 += size;
            this.mStorageCount -= size;
            this.mLoadedCount -= remove == null ? 0 : remove.size();
        }
        if (i3 > 0) {
            if (z) {
                int i4 = this.mLeadingNullCount;
                this.mLeadingNullCount = i4 + i3;
                callback.onPagesSwappedToPlaceholder(i4, i3);
            } else {
                this.mPositionOffset += i3;
                callback.onPagesRemoved(this.mLeadingNullCount, i3);
            }
        }
        if (i3 > 0) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public boolean trimFromEnd(boolean z, int i, int i2, Callback callback) {
        int i3 = 0;
        while (needsTrimFromEnd(i, i2)) {
            ArrayList<List<T>> arrayList = this.mPages;
            List remove = arrayList.remove(arrayList.size() - 1);
            int size = remove == null ? this.mPageSize : remove.size();
            i3 += size;
            this.mStorageCount -= size;
            this.mLoadedCount -= remove == null ? 0 : remove.size();
        }
        if (i3 > 0) {
            int i4 = this.mLeadingNullCount + this.mStorageCount;
            if (z) {
                this.mTrailingNullCount += i3;
                callback.onPagesSwappedToPlaceholder(i4, i3);
            } else {
                callback.onPagesRemoved(i4, i3);
            }
        }
        if (i3 > 0) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public T getFirstLoadedItem() {
        return this.mPages.get(0).get(0);
    }

    /* access modifiers changed from: package-private */
    public T getLastLoadedItem() {
        ArrayList<List<T>> arrayList = this.mPages;
        List list = arrayList.get(arrayList.size() - 1);
        return list.get(list.size() - 1);
    }

    /* access modifiers changed from: package-private */
    public void prependPage(List<T> list, Callback callback) {
        int size = list.size();
        if (size == 0) {
            callback.onEmptyPrepend();
            return;
        }
        int i = this.mPageSize;
        if (i > 0 && size != i) {
            if (this.mPages.size() != 1 || size <= this.mPageSize) {
                this.mPageSize = -1;
            } else {
                this.mPageSize = size;
            }
        }
        this.mPages.add(0, list);
        this.mLoadedCount += size;
        this.mStorageCount += size;
        int min = Math.min(this.mLeadingNullCount, size);
        int i2 = size - min;
        if (min != 0) {
            this.mLeadingNullCount -= min;
        }
        this.mPositionOffset -= i2;
        this.mNumberPrepended += size;
        callback.onPagePrepended(this.mLeadingNullCount, min, i2);
    }

    /* access modifiers changed from: package-private */
    public void appendPage(List<T> list, Callback callback) {
        int size = list.size();
        if (size == 0) {
            callback.onEmptyAppend();
            return;
        }
        if (this.mPageSize > 0) {
            ArrayList<List<T>> arrayList = this.mPages;
            int size2 = arrayList.get(arrayList.size() - 1).size();
            int i = this.mPageSize;
            if (size2 != i || size > i) {
                this.mPageSize = -1;
            }
        }
        this.mPages.add(list);
        this.mLoadedCount += size;
        this.mStorageCount += size;
        int min = Math.min(this.mTrailingNullCount, size);
        int i2 = size - min;
        if (min != 0) {
            this.mTrailingNullCount -= min;
        }
        this.mNumberAppended += size;
        callback.onPageAppended((this.mLeadingNullCount + this.mStorageCount) - size, min, i2);
    }

    /* access modifiers changed from: package-private */
    public boolean pageWouldBeBoundary(int i, boolean z) {
        if (this.mPageSize < 1 || this.mPages.size() < 2) {
            throw new IllegalStateException("Trimming attempt before sufficient load");
        }
        int i2 = this.mLeadingNullCount;
        if (i < i2) {
            return z;
        }
        if (i >= this.mStorageCount + i2) {
            return !z;
        }
        int i3 = (i - i2) / this.mPageSize;
        if (z) {
            for (int i4 = 0; i4 < i3; i4++) {
                if (this.mPages.get(i4) != null) {
                    return false;
                }
            }
        } else {
            for (int size = this.mPages.size() - 1; size > i3; size--) {
                if (this.mPages.get(size) != null) {
                    return false;
                }
            }
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public void initAndSplit(int i, List<T> list, int i2, int i3, int i4, Callback callback) {
        int size = (list.size() + (i4 - 1)) / i4;
        int i5 = 0;
        while (i5 < size) {
            int i6 = i5 * i4;
            int i7 = i5 + 1;
            List<T> subList = list.subList(i6, Math.min(list.size(), i7 * i4));
            if (i5 == 0) {
                init(i, subList, (list.size() + i2) - subList.size(), i3);
            } else {
                insertPage(i6 + i, subList, (Callback) null);
            }
            i5 = i7;
        }
        callback.onInitialized(size());
    }

    /* access modifiers changed from: package-private */
    public void tryInsertPageAndTrim(int i, List<T> list, int i2, int i3, int i4, Callback callback) {
        boolean z = i3 != Integer.MAX_VALUE;
        boolean z2 = i2 > getMiddleOfLoadedRange();
        if (!z || !shouldPreTrimNewPage(i3, i4, list.size()) || !pageWouldBeBoundary(i, z2)) {
            insertPage(i, list, callback);
        } else {
            this.mPages.set((i - this.mLeadingNullCount) / this.mPageSize, (Object) null);
            this.mStorageCount -= list.size();
            if (z2) {
                this.mPages.remove(0);
                this.mLeadingNullCount += list.size();
            } else {
                ArrayList<List<T>> arrayList = this.mPages;
                arrayList.remove(arrayList.size() - 1);
                this.mTrailingNullCount += list.size();
            }
        }
        if (!z) {
            return;
        }
        if (z2) {
            trimFromFront(true, i3, i4, callback);
        } else {
            trimFromEnd(true, i3, i4, callback);
        }
    }

    public void insertPage(int i, List<T> list, Callback callback) {
        int size = list.size();
        if (size != this.mPageSize) {
            int size2 = size();
            int i2 = this.mPageSize;
            boolean z = false;
            boolean z2 = i == size2 - (size2 % i2) && size < i2;
            if (this.mTrailingNullCount == 0 && this.mPages.size() == 1 && size > this.mPageSize) {
                z = true;
            }
            if (!z && !z2) {
                throw new IllegalArgumentException("page introduces incorrect tiling");
            } else if (z) {
                this.mPageSize = size;
            }
        }
        int i3 = i / this.mPageSize;
        allocatePageRange(i3, i3);
        int i4 = i3 - (this.mLeadingNullCount / this.mPageSize);
        List list2 = this.mPages.get(i4);
        if (list2 == null || list2 == PLACEHOLDER_LIST) {
            this.mPages.set(i4, list);
            this.mLoadedCount += size;
            if (callback != null) {
                callback.onPageInserted(i, size);
                return;
            }
            return;
        }
        throw new IllegalArgumentException("Invalid position " + i + ": data already loaded");
    }

    /* access modifiers changed from: package-private */
    public void allocatePageRange(int i, int i2) {
        int i3;
        int i4 = this.mLeadingNullCount / this.mPageSize;
        if (i < i4) {
            int i5 = 0;
            while (true) {
                i3 = i4 - i;
                if (i5 >= i3) {
                    break;
                }
                this.mPages.add(0, (Object) null);
                i5++;
            }
            int i6 = i3 * this.mPageSize;
            this.mStorageCount += i6;
            this.mLeadingNullCount -= i6;
        } else {
            i = i4;
        }
        if (i2 >= this.mPages.size() + i) {
            int min = Math.min(this.mTrailingNullCount, ((i2 + 1) - (this.mPages.size() + i)) * this.mPageSize);
            for (int size = this.mPages.size(); size <= i2 - i; size++) {
                ArrayList<List<T>> arrayList = this.mPages;
                arrayList.add(arrayList.size(), (Object) null);
            }
            this.mStorageCount += min;
            this.mTrailingNullCount -= min;
        }
    }

    public void allocatePlaceholders(int i, int i2, int i3, Callback callback) {
        int i4 = this.mPageSize;
        if (i3 != i4) {
            if (i3 < i4) {
                throw new IllegalArgumentException("Page size cannot be reduced");
            } else if (this.mPages.size() == 1 && this.mTrailingNullCount == 0) {
                this.mPageSize = i3;
            } else {
                throw new IllegalArgumentException("Page size can change only if last page is only one present");
            }
        }
        int size = size();
        int i5 = this.mPageSize;
        int i6 = ((size + i5) - 1) / i5;
        int max = Math.max((i - i2) / i5, 0);
        int min = Math.min((i + i2) / this.mPageSize, i6 - 1);
        allocatePageRange(max, min);
        int i7 = this.mLeadingNullCount / this.mPageSize;
        while (max <= min) {
            int i8 = max - i7;
            if (this.mPages.get(i8) == null) {
                this.mPages.set(i8, PLACEHOLDER_LIST);
                callback.onPagePlaceholderInserted(max);
            }
            max++;
        }
    }

    public boolean hasPage(int i, int i2) {
        List list;
        int i3 = this.mLeadingNullCount / i;
        if (i2 < i3 || i2 >= this.mPages.size() + i3 || (list = this.mPages.get(i2 - i3)) == null || list == PLACEHOLDER_LIST) {
            return false;
        }
        return true;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("leading " + this.mLeadingNullCount + ", storage " + this.mStorageCount + ", trailing " + getTrailingNullCount());
        for (int i = 0; i < this.mPages.size(); i++) {
            sb.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
            sb.append(this.mPages.get(i));
        }
        return sb.toString();
    }
}
