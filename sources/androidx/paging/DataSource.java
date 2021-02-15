package androidx.paging;

import androidx.arch.core.util.Function;
import androidx.paging.PageResult;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class DataSource<Key, Value> {
    private AtomicBoolean mInvalid = new AtomicBoolean(false);
    private CopyOnWriteArrayList<InvalidatedCallback> mOnInvalidatedCallbacks = new CopyOnWriteArrayList<>();

    public interface InvalidatedCallback {
        void onInvalidated();
    }

    /* access modifiers changed from: package-private */
    public abstract boolean isContiguous();

    public abstract <ToValue> DataSource<Key, ToValue> map(Function<Value, ToValue> function);

    public abstract <ToValue> DataSource<Key, ToValue> mapByPage(Function<List<Value>, List<ToValue>> function);

    public static abstract class Factory<Key, Value> {
        public abstract DataSource<Key, Value> create();

        public <ToValue> Factory<Key, ToValue> map(Function<Value, ToValue> function) {
            return mapByPage(DataSource.createListFunction(function));
        }

        public <ToValue> Factory<Key, ToValue> mapByPage(final Function<List<Value>, List<ToValue>> function) {
            return new Factory<Key, ToValue>() {
                public DataSource<Key, ToValue> create() {
                    return Factory.this.create().mapByPage(function);
                }
            };
        }
    }

    static <X, Y> Function<List<X>, List<Y>> createListFunction(final Function<X, Y> function) {
        return new Function<List<X>, List<Y>>() {
            public List<Y> apply(List<X> list) {
                ArrayList arrayList = new ArrayList(list.size());
                for (int i = 0; i < list.size(); i++) {
                    arrayList.add(function.apply(list.get(i)));
                }
                return arrayList;
            }
        };
    }

    static <A, B> List<B> convert(Function<List<A>, List<B>> function, List<A> list) {
        List<B> apply = function.apply(list);
        if (apply.size() == list.size()) {
            return apply;
        }
        throw new IllegalStateException("Invalid Function " + function + " changed return size. This is not supported.");
    }

    DataSource() {
    }

    static class LoadCallbackHelper<T> {
        private final DataSource mDataSource;
        private boolean mHasSignalled = false;
        private Executor mPostExecutor = null;
        final PageResult.Receiver<T> mReceiver;
        final int mResultType;
        private final Object mSignalLock = new Object();

        static void validateInitialLoadParams(List<?> list, int i, int i2) {
            if (i < 0) {
                throw new IllegalArgumentException("Position must be non-negative");
            } else if (list.size() + i > i2) {
                throw new IllegalArgumentException("List size + position too large, last item in list beyond totalCount.");
            } else if (list.size() == 0 && i2 > 0) {
                throw new IllegalArgumentException("Initial result cannot be empty if items are present in data set.");
            }
        }

        LoadCallbackHelper(DataSource dataSource, int i, Executor executor, PageResult.Receiver<T> receiver) {
            this.mDataSource = dataSource;
            this.mResultType = i;
            this.mPostExecutor = executor;
            this.mReceiver = receiver;
        }

        /* access modifiers changed from: package-private */
        public void setPostExecutor(Executor executor) {
            synchronized (this.mSignalLock) {
                this.mPostExecutor = executor;
            }
        }

        /* access modifiers changed from: package-private */
        public boolean dispatchInvalidResultIfInvalid() {
            if (!this.mDataSource.isInvalid()) {
                return false;
            }
            dispatchResultToReceiver(PageResult.getInvalidResult());
            return true;
        }

        /* access modifiers changed from: package-private */
        public void dispatchResultToReceiver(final PageResult<T> pageResult) {
            Executor executor;
            synchronized (this.mSignalLock) {
                if (!this.mHasSignalled) {
                    this.mHasSignalled = true;
                    executor = this.mPostExecutor;
                } else {
                    throw new IllegalStateException("callback.onResult already called, cannot call again.");
                }
            }
            if (executor != null) {
                executor.execute(new Runnable() {
                    public void run() {
                        LoadCallbackHelper.this.mReceiver.onPageResult(LoadCallbackHelper.this.mResultType, pageResult);
                    }
                });
            } else {
                this.mReceiver.onPageResult(this.mResultType, pageResult);
            }
        }
    }

    public void addInvalidatedCallback(InvalidatedCallback invalidatedCallback) {
        this.mOnInvalidatedCallbacks.add(invalidatedCallback);
    }

    public void removeInvalidatedCallback(InvalidatedCallback invalidatedCallback) {
        this.mOnInvalidatedCallbacks.remove(invalidatedCallback);
    }

    public void invalidate() {
        if (this.mInvalid.compareAndSet(false, true)) {
            Iterator<InvalidatedCallback> it = this.mOnInvalidatedCallbacks.iterator();
            while (it.hasNext()) {
                it.next().onInvalidated();
            }
        }
    }

    public boolean isInvalid() {
        return this.mInvalid.get();
    }
}
