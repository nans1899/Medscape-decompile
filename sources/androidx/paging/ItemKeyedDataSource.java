package androidx.paging;

import androidx.arch.core.util.Function;
import androidx.paging.DataSource;
import androidx.paging.PageResult;
import java.util.List;
import java.util.concurrent.Executor;

public abstract class ItemKeyedDataSource<Key, Value> extends ContiguousDataSource<Key, Value> {

    public static abstract class LoadCallback<Value> {
        public abstract void onResult(List<Value> list);
    }

    public static abstract class LoadInitialCallback<Value> extends LoadCallback<Value> {
        public abstract void onResult(List<Value> list, int i, int i2);
    }

    public abstract Key getKey(Value value);

    public abstract void loadAfter(LoadParams<Key> loadParams, LoadCallback<Value> loadCallback);

    public abstract void loadBefore(LoadParams<Key> loadParams, LoadCallback<Value> loadCallback);

    public abstract void loadInitial(LoadInitialParams<Key> loadInitialParams, LoadInitialCallback<Value> loadInitialCallback);

    public static class LoadInitialParams<Key> {
        public final boolean placeholdersEnabled;
        public final Key requestedInitialKey;
        public final int requestedLoadSize;

        public LoadInitialParams(Key key, int i, boolean z) {
            this.requestedInitialKey = key;
            this.requestedLoadSize = i;
            this.placeholdersEnabled = z;
        }
    }

    public static class LoadParams<Key> {
        public final Key key;
        public final int requestedLoadSize;

        public LoadParams(Key key2, int i) {
            this.key = key2;
            this.requestedLoadSize = i;
        }
    }

    static class LoadInitialCallbackImpl<Value> extends LoadInitialCallback<Value> {
        final DataSource.LoadCallbackHelper<Value> mCallbackHelper;
        private final boolean mCountingEnabled;

        LoadInitialCallbackImpl(ItemKeyedDataSource itemKeyedDataSource, boolean z, PageResult.Receiver<Value> receiver) {
            this.mCallbackHelper = new DataSource.LoadCallbackHelper<>(itemKeyedDataSource, 0, (Executor) null, receiver);
            this.mCountingEnabled = z;
        }

        public void onResult(List<Value> list, int i, int i2) {
            if (!this.mCallbackHelper.dispatchInvalidResultIfInvalid()) {
                DataSource.LoadCallbackHelper.validateInitialLoadParams(list, i, i2);
                int size = (i2 - i) - list.size();
                if (this.mCountingEnabled) {
                    this.mCallbackHelper.dispatchResultToReceiver(new PageResult(list, i, size, 0));
                } else {
                    this.mCallbackHelper.dispatchResultToReceiver(new PageResult(list, i));
                }
            }
        }

        public void onResult(List<Value> list) {
            if (!this.mCallbackHelper.dispatchInvalidResultIfInvalid()) {
                this.mCallbackHelper.dispatchResultToReceiver(new PageResult(list, 0, 0, 0));
            }
        }
    }

    static class LoadCallbackImpl<Value> extends LoadCallback<Value> {
        final DataSource.LoadCallbackHelper<Value> mCallbackHelper;

        LoadCallbackImpl(ItemKeyedDataSource itemKeyedDataSource, int i, Executor executor, PageResult.Receiver<Value> receiver) {
            this.mCallbackHelper = new DataSource.LoadCallbackHelper<>(itemKeyedDataSource, i, executor, receiver);
        }

        public void onResult(List<Value> list) {
            if (!this.mCallbackHelper.dispatchInvalidResultIfInvalid()) {
                this.mCallbackHelper.dispatchResultToReceiver(new PageResult(list, 0, 0, 0));
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final Key getKey(int i, Value value) {
        if (value == null) {
            return null;
        }
        return getKey(value);
    }

    /* access modifiers changed from: package-private */
    public final void dispatchLoadInitial(Key key, int i, int i2, boolean z, Executor executor, PageResult.Receiver<Value> receiver) {
        LoadInitialCallbackImpl loadInitialCallbackImpl = new LoadInitialCallbackImpl(this, z, receiver);
        loadInitial(new LoadInitialParams(key, i, z), loadInitialCallbackImpl);
        loadInitialCallbackImpl.mCallbackHelper.setPostExecutor(executor);
    }

    /* access modifiers changed from: package-private */
    public final void dispatchLoadAfter(int i, Value value, int i2, Executor executor, PageResult.Receiver<Value> receiver) {
        loadAfter(new LoadParams(getKey(value), i2), new LoadCallbackImpl(this, 1, executor, receiver));
    }

    /* access modifiers changed from: package-private */
    public final void dispatchLoadBefore(int i, Value value, int i2, Executor executor, PageResult.Receiver<Value> receiver) {
        loadBefore(new LoadParams(getKey(value), i2), new LoadCallbackImpl(this, 2, executor, receiver));
    }

    public final <ToValue> ItemKeyedDataSource<Key, ToValue> mapByPage(Function<List<Value>, List<ToValue>> function) {
        return new WrapperItemKeyedDataSource(this, function);
    }

    public final <ToValue> ItemKeyedDataSource<Key, ToValue> map(Function<Value, ToValue> function) {
        return mapByPage((Function) createListFunction(function));
    }
}
