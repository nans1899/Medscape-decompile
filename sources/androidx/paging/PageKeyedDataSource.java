package androidx.paging;

import androidx.arch.core.util.Function;
import androidx.paging.DataSource;
import androidx.paging.PageResult;
import java.util.List;
import java.util.concurrent.Executor;

public abstract class PageKeyedDataSource<Key, Value> extends ContiguousDataSource<Key, Value> {
    private final Object mKeyLock = new Object();
    private Key mNextKey = null;
    private Key mPreviousKey = null;

    public static abstract class LoadCallback<Key, Value> {
        public abstract void onResult(List<Value> list, Key key);
    }

    public static abstract class LoadInitialCallback<Key, Value> {
        public abstract void onResult(List<Value> list, int i, int i2, Key key, Key key2);

        public abstract void onResult(List<Value> list, Key key, Key key2);
    }

    /* access modifiers changed from: package-private */
    public final Key getKey(int i, Value value) {
        return null;
    }

    public abstract void loadAfter(LoadParams<Key> loadParams, LoadCallback<Key, Value> loadCallback);

    public abstract void loadBefore(LoadParams<Key> loadParams, LoadCallback<Key, Value> loadCallback);

    public abstract void loadInitial(LoadInitialParams<Key> loadInitialParams, LoadInitialCallback<Key, Value> loadInitialCallback);

    /* access modifiers changed from: package-private */
    public boolean supportsPageDropping() {
        return false;
    }

    /* access modifiers changed from: package-private */
    public void initKeys(Key key, Key key2) {
        synchronized (this.mKeyLock) {
            this.mPreviousKey = key;
            this.mNextKey = key2;
        }
    }

    /* access modifiers changed from: package-private */
    public void setPreviousKey(Key key) {
        synchronized (this.mKeyLock) {
            this.mPreviousKey = key;
        }
    }

    /* access modifiers changed from: package-private */
    public void setNextKey(Key key) {
        synchronized (this.mKeyLock) {
            this.mNextKey = key;
        }
    }

    private Key getPreviousKey() {
        Key key;
        synchronized (this.mKeyLock) {
            key = this.mPreviousKey;
        }
        return key;
    }

    private Key getNextKey() {
        Key key;
        synchronized (this.mKeyLock) {
            key = this.mNextKey;
        }
        return key;
    }

    public static class LoadInitialParams<Key> {
        public final boolean placeholdersEnabled;
        public final int requestedLoadSize;

        public LoadInitialParams(int i, boolean z) {
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

    static class LoadInitialCallbackImpl<Key, Value> extends LoadInitialCallback<Key, Value> {
        final DataSource.LoadCallbackHelper<Value> mCallbackHelper;
        private final boolean mCountingEnabled;
        private final PageKeyedDataSource<Key, Value> mDataSource;

        LoadInitialCallbackImpl(PageKeyedDataSource<Key, Value> pageKeyedDataSource, boolean z, PageResult.Receiver<Value> receiver) {
            this.mCallbackHelper = new DataSource.LoadCallbackHelper<>(pageKeyedDataSource, 0, (Executor) null, receiver);
            this.mDataSource = pageKeyedDataSource;
            this.mCountingEnabled = z;
        }

        public void onResult(List<Value> list, int i, int i2, Key key, Key key2) {
            if (!this.mCallbackHelper.dispatchInvalidResultIfInvalid()) {
                DataSource.LoadCallbackHelper.validateInitialLoadParams(list, i, i2);
                this.mDataSource.initKeys(key, key2);
                int size = (i2 - i) - list.size();
                if (this.mCountingEnabled) {
                    this.mCallbackHelper.dispatchResultToReceiver(new PageResult(list, i, size, 0));
                } else {
                    this.mCallbackHelper.dispatchResultToReceiver(new PageResult(list, i));
                }
            }
        }

        public void onResult(List<Value> list, Key key, Key key2) {
            if (!this.mCallbackHelper.dispatchInvalidResultIfInvalid()) {
                this.mDataSource.initKeys(key, key2);
                this.mCallbackHelper.dispatchResultToReceiver(new PageResult(list, 0, 0, 0));
            }
        }
    }

    static class LoadCallbackImpl<Key, Value> extends LoadCallback<Key, Value> {
        final DataSource.LoadCallbackHelper<Value> mCallbackHelper;
        private final PageKeyedDataSource<Key, Value> mDataSource;

        LoadCallbackImpl(PageKeyedDataSource<Key, Value> pageKeyedDataSource, int i, Executor executor, PageResult.Receiver<Value> receiver) {
            this.mCallbackHelper = new DataSource.LoadCallbackHelper<>(pageKeyedDataSource, i, executor, receiver);
            this.mDataSource = pageKeyedDataSource;
        }

        public void onResult(List<Value> list, Key key) {
            if (!this.mCallbackHelper.dispatchInvalidResultIfInvalid()) {
                if (this.mCallbackHelper.mResultType == 1) {
                    this.mDataSource.setNextKey(key);
                } else {
                    this.mDataSource.setPreviousKey(key);
                }
                this.mCallbackHelper.dispatchResultToReceiver(new PageResult(list, 0, 0, 0));
            }
        }
    }

    /* access modifiers changed from: package-private */
    public final void dispatchLoadInitial(Key key, int i, int i2, boolean z, Executor executor, PageResult.Receiver<Value> receiver) {
        LoadInitialCallbackImpl loadInitialCallbackImpl = new LoadInitialCallbackImpl(this, z, receiver);
        loadInitial(new LoadInitialParams(i, z), loadInitialCallbackImpl);
        loadInitialCallbackImpl.mCallbackHelper.setPostExecutor(executor);
    }

    /* access modifiers changed from: package-private */
    public final void dispatchLoadAfter(int i, Value value, int i2, Executor executor, PageResult.Receiver<Value> receiver) {
        Object nextKey = getNextKey();
        if (nextKey != null) {
            loadAfter(new LoadParams(nextKey, i2), new LoadCallbackImpl(this, 1, executor, receiver));
        } else {
            receiver.onPageResult(1, PageResult.getEmptyResult());
        }
    }

    /* access modifiers changed from: package-private */
    public final void dispatchLoadBefore(int i, Value value, int i2, Executor executor, PageResult.Receiver<Value> receiver) {
        Object previousKey = getPreviousKey();
        if (previousKey != null) {
            loadBefore(new LoadParams(previousKey, i2), new LoadCallbackImpl(this, 2, executor, receiver));
        } else {
            receiver.onPageResult(2, PageResult.getEmptyResult());
        }
    }

    public final <ToValue> PageKeyedDataSource<Key, ToValue> mapByPage(Function<List<Value>, List<ToValue>> function) {
        return new WrapperPageKeyedDataSource(this, function);
    }

    public final <ToValue> PageKeyedDataSource<Key, ToValue> map(Function<Value, ToValue> function) {
        return mapByPage((Function) createListFunction(function));
    }
}
