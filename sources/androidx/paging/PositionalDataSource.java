package androidx.paging;

import androidx.arch.core.util.Function;
import androidx.paging.DataSource;
import androidx.paging.PageResult;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executor;

public abstract class PositionalDataSource<T> extends DataSource<Integer, T> {

    public static abstract class LoadInitialCallback<T> {
        public abstract void onResult(List<T> list, int i);

        public abstract void onResult(List<T> list, int i, int i2);
    }

    public static abstract class LoadRangeCallback<T> {
        public abstract void onResult(List<T> list);
    }

    /* access modifiers changed from: package-private */
    public boolean isContiguous() {
        return false;
    }

    public abstract void loadInitial(LoadInitialParams loadInitialParams, LoadInitialCallback<T> loadInitialCallback);

    public abstract void loadRange(LoadRangeParams loadRangeParams, LoadRangeCallback<T> loadRangeCallback);

    public static class LoadInitialParams {
        public final int pageSize;
        public final boolean placeholdersEnabled;
        public final int requestedLoadSize;
        public final int requestedStartPosition;

        public LoadInitialParams(int i, int i2, int i3, boolean z) {
            this.requestedStartPosition = i;
            this.requestedLoadSize = i2;
            this.pageSize = i3;
            this.placeholdersEnabled = z;
        }
    }

    public static class LoadRangeParams {
        public final int loadSize;
        public final int startPosition;

        public LoadRangeParams(int i, int i2) {
            this.startPosition = i;
            this.loadSize = i2;
        }
    }

    static class LoadInitialCallbackImpl<T> extends LoadInitialCallback<T> {
        final DataSource.LoadCallbackHelper<T> mCallbackHelper;
        private final boolean mCountingEnabled;
        private final int mPageSize;

        LoadInitialCallbackImpl(PositionalDataSource positionalDataSource, boolean z, int i, PageResult.Receiver<T> receiver) {
            this.mCallbackHelper = new DataSource.LoadCallbackHelper<>(positionalDataSource, 0, (Executor) null, receiver);
            this.mCountingEnabled = z;
            this.mPageSize = i;
            if (i < 1) {
                throw new IllegalArgumentException("Page size must be non-negative");
            }
        }

        public void onResult(List<T> list, int i, int i2) {
            if (!this.mCallbackHelper.dispatchInvalidResultIfInvalid()) {
                DataSource.LoadCallbackHelper.validateInitialLoadParams(list, i, i2);
                if (list.size() + i != i2 && list.size() % this.mPageSize != 0) {
                    throw new IllegalArgumentException("PositionalDataSource requires initial load size to be a multiple of page size to support internal tiling. loadSize " + list.size() + ", position " + i + ", totalCount " + i2 + ", pageSize " + this.mPageSize);
                } else if (this.mCountingEnabled) {
                    this.mCallbackHelper.dispatchResultToReceiver(new PageResult(list, i, (i2 - i) - list.size(), 0));
                } else {
                    this.mCallbackHelper.dispatchResultToReceiver(new PageResult(list, i));
                }
            }
        }

        public void onResult(List<T> list, int i) {
            if (this.mCallbackHelper.dispatchInvalidResultIfInvalid()) {
                return;
            }
            if (i < 0) {
                throw new IllegalArgumentException("Position must be non-negative");
            } else if (list.isEmpty() && i != 0) {
                throw new IllegalArgumentException("Initial result cannot be empty if items are present in data set.");
            } else if (!this.mCountingEnabled) {
                this.mCallbackHelper.dispatchResultToReceiver(new PageResult(list, i));
            } else {
                throw new IllegalStateException("Placeholders requested, but totalCount not provided. Please call the three-parameter onResult method, or disable placeholders in the PagedList.Config");
            }
        }
    }

    static class LoadRangeCallbackImpl<T> extends LoadRangeCallback<T> {
        private DataSource.LoadCallbackHelper<T> mCallbackHelper;
        private final int mPositionOffset;

        LoadRangeCallbackImpl(PositionalDataSource positionalDataSource, int i, int i2, Executor executor, PageResult.Receiver<T> receiver) {
            this.mCallbackHelper = new DataSource.LoadCallbackHelper<>(positionalDataSource, i, executor, receiver);
            this.mPositionOffset = i2;
        }

        public void onResult(List<T> list) {
            if (!this.mCallbackHelper.dispatchInvalidResultIfInvalid()) {
                this.mCallbackHelper.dispatchResultToReceiver(new PageResult(list, 0, 0, this.mPositionOffset));
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final void dispatchLoadInitial(boolean z, int i, int i2, int i3, Executor executor, PageResult.Receiver<T> receiver) {
        LoadInitialCallbackImpl loadInitialCallbackImpl = new LoadInitialCallbackImpl(this, z, i3, receiver);
        loadInitial(new LoadInitialParams(i, i2, i3, z), loadInitialCallbackImpl);
        loadInitialCallbackImpl.mCallbackHelper.setPostExecutor(executor);
    }

    /* access modifiers changed from: package-private */
    public final void dispatchLoadRange(int i, int i2, int i3, Executor executor, PageResult.Receiver<T> receiver) {
        LoadRangeCallbackImpl loadRangeCallbackImpl = new LoadRangeCallbackImpl(this, i, i2, executor, receiver);
        if (i3 == 0) {
            loadRangeCallbackImpl.onResult(Collections.emptyList());
        } else {
            loadRange(new LoadRangeParams(i2, i3), loadRangeCallbackImpl);
        }
    }

    /* access modifiers changed from: package-private */
    public ContiguousDataSource<Integer, T> wrapAsContiguousWithoutPlaceholders() {
        return new ContiguousWithoutPlaceholdersWrapper(this);
    }

    public static int computeInitialLoadPosition(LoadInitialParams loadInitialParams, int i) {
        int i2 = loadInitialParams.requestedStartPosition;
        int i3 = loadInitialParams.requestedLoadSize;
        int i4 = loadInitialParams.pageSize;
        return Math.max(0, Math.min(((((i - i3) + i4) - 1) / i4) * i4, (i2 / i4) * i4));
    }

    public static int computeInitialLoadSize(LoadInitialParams loadInitialParams, int i, int i2) {
        return Math.min(i2 - i, loadInitialParams.requestedLoadSize);
    }

    static class ContiguousWithoutPlaceholdersWrapper<Value> extends ContiguousDataSource<Integer, Value> {
        final PositionalDataSource<Value> mSource;

        ContiguousWithoutPlaceholdersWrapper(PositionalDataSource<Value> positionalDataSource) {
            this.mSource = positionalDataSource;
        }

        public void addInvalidatedCallback(DataSource.InvalidatedCallback invalidatedCallback) {
            this.mSource.addInvalidatedCallback(invalidatedCallback);
        }

        public void removeInvalidatedCallback(DataSource.InvalidatedCallback invalidatedCallback) {
            this.mSource.removeInvalidatedCallback(invalidatedCallback);
        }

        public void invalidate() {
            this.mSource.invalidate();
        }

        public boolean isInvalid() {
            return this.mSource.isInvalid();
        }

        public <ToValue> DataSource<Integer, ToValue> mapByPage(Function<List<Value>, List<ToValue>> function) {
            throw new UnsupportedOperationException("Inaccessible inner type doesn't support map op");
        }

        public <ToValue> DataSource<Integer, ToValue> map(Function<Value, ToValue> function) {
            throw new UnsupportedOperationException("Inaccessible inner type doesn't support map op");
        }

        /* access modifiers changed from: package-private */
        public void dispatchLoadInitial(Integer num, int i, int i2, boolean z, Executor executor, PageResult.Receiver<Value> receiver) {
            Integer num2;
            if (num == null) {
                num2 = 0;
            } else {
                i = Math.max(i / i2, 2) * i2;
                num2 = Integer.valueOf(Math.max(0, ((num.intValue() - (i / 2)) / i2) * i2));
            }
            this.mSource.dispatchLoadInitial(false, num2.intValue(), i, i2, executor, receiver);
        }

        /* access modifiers changed from: package-private */
        public void dispatchLoadAfter(int i, Value value, int i2, Executor executor, PageResult.Receiver<Value> receiver) {
            this.mSource.dispatchLoadRange(1, i + 1, i2, executor, receiver);
        }

        /* access modifiers changed from: package-private */
        public void dispatchLoadBefore(int i, Value value, int i2, Executor executor, PageResult.Receiver<Value> receiver) {
            int i3 = i - 1;
            if (i3 < 0) {
                this.mSource.dispatchLoadRange(2, i3, 0, executor, receiver);
                return;
            }
            int min = Math.min(i2, i3 + 1);
            this.mSource.dispatchLoadRange(2, (i3 - min) + 1, min, executor, receiver);
        }

        /* access modifiers changed from: package-private */
        public Integer getKey(int i, Value value) {
            return Integer.valueOf(i);
        }
    }

    public final <V> PositionalDataSource<V> mapByPage(Function<List<T>, List<V>> function) {
        return new WrapperPositionalDataSource(this, function);
    }

    public final <V> PositionalDataSource<V> map(Function<T, V> function) {
        return mapByPage((Function) createListFunction(function));
    }
}
