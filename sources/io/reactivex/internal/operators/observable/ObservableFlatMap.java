package io.reactivex.internal.operators.observable;

import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Function;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.fuseable.QueueDisposable;
import io.reactivex.internal.fuseable.SimplePlainQueue;
import io.reactivex.internal.fuseable.SimpleQueue;
import io.reactivex.internal.queue.SpscArrayQueue;
import io.reactivex.internal.queue.SpscLinkedArrayQueue;
import io.reactivex.internal.util.AtomicThrowable;
import io.reactivex.internal.util.ExceptionHelper;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public final class ObservableFlatMap<T, U> extends AbstractObservableWithUpstream<T, U> {
    final int bufferSize;
    final boolean delayErrors;
    final Function<? super T, ? extends ObservableSource<? extends U>> mapper;
    final int maxConcurrency;

    public ObservableFlatMap(ObservableSource<T> observableSource, Function<? super T, ? extends ObservableSource<? extends U>> function, boolean z, int i, int i2) {
        super(observableSource);
        this.mapper = function;
        this.delayErrors = z;
        this.maxConcurrency = i;
        this.bufferSize = i2;
    }

    public void subscribeActual(Observer<? super U> observer) {
        if (!ObservableScalarXMap.tryScalarXMapSubscribe(this.source, observer, this.mapper)) {
            this.source.subscribe(new MergeObserver(observer, this.mapper, this.delayErrors, this.maxConcurrency, this.bufferSize));
        }
    }

    static final class MergeObserver<T, U> extends AtomicInteger implements Disposable, Observer<T> {
        static final InnerObserver<?, ?>[] CANCELLED = new InnerObserver[0];
        static final InnerObserver<?, ?>[] EMPTY = new InnerObserver[0];
        private static final long serialVersionUID = -2117620485640801370L;
        final Observer<? super U> actual;
        final int bufferSize;
        volatile boolean cancelled;
        final boolean delayErrors;
        volatile boolean done;
        final AtomicThrowable errors = new AtomicThrowable();
        long lastId;
        int lastIndex;
        final Function<? super T, ? extends ObservableSource<? extends U>> mapper;
        final int maxConcurrency;
        final AtomicReference<InnerObserver<?, ?>[]> observers;
        volatile SimplePlainQueue<U> queue;
        Disposable s;
        Queue<ObservableSource<? extends U>> sources;
        long uniqueId;
        int wip;

        MergeObserver(Observer<? super U> observer, Function<? super T, ? extends ObservableSource<? extends U>> function, boolean z, int i, int i2) {
            this.actual = observer;
            this.mapper = function;
            this.delayErrors = z;
            this.maxConcurrency = i;
            this.bufferSize = i2;
            if (i != Integer.MAX_VALUE) {
                this.sources = new ArrayDeque(i);
            }
            this.observers = new AtomicReference<>(EMPTY);
        }

        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.validate(this.s, disposable)) {
                this.s = disposable;
                this.actual.onSubscribe(this);
            }
        }

        public void onNext(T t) {
            if (!this.done) {
                try {
                    ObservableSource observableSource = (ObservableSource) ObjectHelper.requireNonNull(this.mapper.apply(t), "The mapper returned a null ObservableSource");
                    if (this.maxConcurrency != Integer.MAX_VALUE) {
                        synchronized (this) {
                            if (this.wip == this.maxConcurrency) {
                                this.sources.offer(observableSource);
                                return;
                            }
                            this.wip++;
                        }
                    }
                    subscribeInner(observableSource);
                } catch (Throwable th) {
                    Exceptions.throwIfFatal(th);
                    this.s.dispose();
                    onError(th);
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void subscribeInner(ObservableSource<? extends U> observableSource) {
            ObservableSource<? extends U> poll;
            while (observableSource instanceof Callable) {
                if (tryEmitScalar((Callable) observableSource) && this.maxConcurrency != Integer.MAX_VALUE) {
                    boolean z = false;
                    synchronized (this) {
                        poll = this.sources.poll();
                        if (poll == null) {
                            this.wip--;
                            z = true;
                        }
                    }
                    if (z) {
                        drain();
                        return;
                    }
                    observableSource = poll;
                } else {
                    return;
                }
            }
            long j = this.uniqueId;
            this.uniqueId = 1 + j;
            InnerObserver innerObserver = new InnerObserver(this, j);
            if (addInner(innerObserver)) {
                observableSource.subscribe(innerObserver);
            }
        }

        /* access modifiers changed from: package-private */
        public boolean addInner(InnerObserver<T, U> innerObserver) {
            InnerObserver<?, ?>[] innerObserverArr;
            InnerObserver[] innerObserverArr2;
            do {
                innerObserverArr = (InnerObserver[]) this.observers.get();
                if (innerObserverArr == CANCELLED) {
                    innerObserver.dispose();
                    return false;
                }
                int length = innerObserverArr.length;
                innerObserverArr2 = new InnerObserver[(length + 1)];
                System.arraycopy(innerObserverArr, 0, innerObserverArr2, 0, length);
                innerObserverArr2[length] = innerObserver;
            } while (!this.observers.compareAndSet(innerObserverArr, innerObserverArr2));
            return true;
        }

        /* access modifiers changed from: package-private */
        public void removeInner(InnerObserver<T, U> innerObserver) {
            InnerObserver<T, U>[] innerObserverArr;
            InnerObserver<?, ?>[] innerObserverArr2;
            do {
                innerObserverArr = (InnerObserver[]) this.observers.get();
                int length = innerObserverArr.length;
                if (length != 0) {
                    int i = -1;
                    int i2 = 0;
                    while (true) {
                        if (i2 >= length) {
                            break;
                        } else if (innerObserverArr[i2] == innerObserver) {
                            i = i2;
                            break;
                        } else {
                            i2++;
                        }
                    }
                    if (i >= 0) {
                        if (length == 1) {
                            innerObserverArr2 = EMPTY;
                        } else {
                            InnerObserver<?, ?>[] innerObserverArr3 = new InnerObserver[(length - 1)];
                            System.arraycopy(innerObserverArr, 0, innerObserverArr3, 0, i);
                            System.arraycopy(innerObserverArr, i + 1, innerObserverArr3, i, (length - i) - 1);
                            innerObserverArr2 = innerObserverArr3;
                        }
                    } else {
                        return;
                    }
                } else {
                    return;
                }
            } while (!this.observers.compareAndSet(innerObserverArr, innerObserverArr2));
        }

        /* access modifiers changed from: package-private */
        public boolean tryEmitScalar(Callable<? extends U> callable) {
            try {
                Object call = callable.call();
                if (call == null) {
                    return true;
                }
                if (get() != 0 || !compareAndSet(0, 1)) {
                    SimplePlainQueue<U> simplePlainQueue = this.queue;
                    if (simplePlainQueue == null) {
                        if (this.maxConcurrency == Integer.MAX_VALUE) {
                            simplePlainQueue = new SpscLinkedArrayQueue<>(this.bufferSize);
                        } else {
                            simplePlainQueue = new SpscArrayQueue<>(this.maxConcurrency);
                        }
                        this.queue = simplePlainQueue;
                    }
                    if (!simplePlainQueue.offer(call)) {
                        onError(new IllegalStateException("Scalar queue full?!"));
                        return true;
                    } else if (getAndIncrement() != 0) {
                        return false;
                    }
                } else {
                    this.actual.onNext(call);
                    if (decrementAndGet() == 0) {
                        return true;
                    }
                }
                drainLoop();
                return true;
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                this.errors.addThrowable(th);
                drain();
                return true;
            }
        }

        /* access modifiers changed from: package-private */
        public void tryEmit(U u, InnerObserver<T, U> innerObserver) {
            if (get() != 0 || !compareAndSet(0, 1)) {
                SimpleQueue simpleQueue = innerObserver.queue;
                if (simpleQueue == null) {
                    simpleQueue = new SpscLinkedArrayQueue(this.bufferSize);
                    innerObserver.queue = simpleQueue;
                }
                simpleQueue.offer(u);
                if (getAndIncrement() != 0) {
                    return;
                }
            } else {
                this.actual.onNext(u);
                if (decrementAndGet() == 0) {
                    return;
                }
            }
            drainLoop();
        }

        public void onError(Throwable th) {
            if (this.done) {
                RxJavaPlugins.onError(th);
            } else if (this.errors.addThrowable(th)) {
                this.done = true;
                drain();
            } else {
                RxJavaPlugins.onError(th);
            }
        }

        public void onComplete() {
            if (!this.done) {
                this.done = true;
                drain();
            }
        }

        public void dispose() {
            Throwable terminate;
            if (!this.cancelled) {
                this.cancelled = true;
                if (disposeAll() && (terminate = this.errors.terminate()) != null && terminate != ExceptionHelper.TERMINATED) {
                    RxJavaPlugins.onError(terminate);
                }
            }
        }

        public boolean isDisposed() {
            return this.cancelled;
        }

        /* access modifiers changed from: package-private */
        public void drain() {
            if (getAndIncrement() == 0) {
                drainLoop();
            }
        }

        /* access modifiers changed from: package-private */
        /* JADX WARNING: Code restructure failed: missing block: B:65:0x00b5, code lost:
            if (r12 != null) goto L_0x00a3;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void drainLoop() {
            /*
                r14 = this;
                io.reactivex.Observer<? super U> r0 = r14.actual
                r1 = 1
                r2 = 1
            L_0x0004:
                boolean r3 = r14.checkTerminate()
                if (r3 == 0) goto L_0x000b
                return
            L_0x000b:
                io.reactivex.internal.fuseable.SimplePlainQueue<U> r3 = r14.queue
                if (r3 == 0) goto L_0x0023
            L_0x000f:
                boolean r4 = r14.checkTerminate()
                if (r4 == 0) goto L_0x0016
                return
            L_0x0016:
                java.lang.Object r4 = r3.poll()
                if (r4 != 0) goto L_0x001f
                if (r4 != 0) goto L_0x000f
                goto L_0x0023
            L_0x001f:
                r0.onNext(r4)
                goto L_0x000f
            L_0x0023:
                boolean r3 = r14.done
                io.reactivex.internal.fuseable.SimplePlainQueue<U> r4 = r14.queue
                java.util.concurrent.atomic.AtomicReference<io.reactivex.internal.operators.observable.ObservableFlatMap$InnerObserver<?, ?>[]> r5 = r14.observers
                java.lang.Object r5 = r5.get()
                io.reactivex.internal.operators.observable.ObservableFlatMap$InnerObserver[] r5 = (io.reactivex.internal.operators.observable.ObservableFlatMap.InnerObserver[]) r5
                int r6 = r5.length
                int r7 = r14.maxConcurrency
                r8 = 2147483647(0x7fffffff, float:NaN)
                r9 = 0
                if (r7 == r8) goto L_0x0044
                monitor-enter(r14)
                java.util.Queue<io.reactivex.ObservableSource<? extends U>> r7 = r14.sources     // Catch:{ all -> 0x0041 }
                int r7 = r7.size()     // Catch:{ all -> 0x0041 }
                monitor-exit(r14)     // Catch:{ all -> 0x0041 }
                goto L_0x0045
            L_0x0041:
                r0 = move-exception
                monitor-exit(r14)     // Catch:{ all -> 0x0041 }
                throw r0
            L_0x0044:
                r7 = 0
            L_0x0045:
                if (r3 == 0) goto L_0x0067
                if (r4 == 0) goto L_0x004f
                boolean r3 = r4.isEmpty()
                if (r3 == 0) goto L_0x0067
            L_0x004f:
                if (r6 != 0) goto L_0x0067
                if (r7 != 0) goto L_0x0067
                io.reactivex.internal.util.AtomicThrowable r1 = r14.errors
                java.lang.Throwable r1 = r1.terminate()
                java.lang.Throwable r2 = io.reactivex.internal.util.ExceptionHelper.TERMINATED
                if (r1 == r2) goto L_0x0066
                if (r1 != 0) goto L_0x0063
                r0.onComplete()
                goto L_0x0066
            L_0x0063:
                r0.onError(r1)
            L_0x0066:
                return
            L_0x0067:
                if (r6 == 0) goto L_0x0104
                long r3 = r14.lastId
                int r7 = r14.lastIndex
                if (r6 <= r7) goto L_0x0077
                r10 = r5[r7]
                long r10 = r10.id
                int r12 = (r10 > r3 ? 1 : (r10 == r3 ? 0 : -1))
                if (r12 == 0) goto L_0x0096
            L_0x0077:
                if (r6 > r7) goto L_0x007a
                r7 = 0
            L_0x007a:
                r10 = 0
            L_0x007b:
                if (r10 >= r6) goto L_0x008e
                r11 = r5[r7]
                long r11 = r11.id
                int r13 = (r11 > r3 ? 1 : (r11 == r3 ? 0 : -1))
                if (r13 != 0) goto L_0x0086
                goto L_0x008e
            L_0x0086:
                int r7 = r7 + 1
                if (r7 != r6) goto L_0x008b
                r7 = 0
            L_0x008b:
                int r10 = r10 + 1
                goto L_0x007b
            L_0x008e:
                r14.lastIndex = r7
                r3 = r5[r7]
                long r3 = r3.id
                r14.lastId = r3
            L_0x0096:
                r3 = 0
                r4 = 0
            L_0x0098:
                if (r3 >= r6) goto L_0x00fb
                boolean r10 = r14.checkTerminate()
                if (r10 == 0) goto L_0x00a1
                return
            L_0x00a1:
                r10 = r5[r7]
            L_0x00a3:
                boolean r11 = r14.checkTerminate()
                if (r11 == 0) goto L_0x00aa
                return
            L_0x00aa:
                io.reactivex.internal.fuseable.SimpleQueue<U> r11 = r10.queue
                if (r11 != 0) goto L_0x00af
                goto L_0x00b7
            L_0x00af:
                java.lang.Object r12 = r11.poll()     // Catch:{ all -> 0x00e0 }
                if (r12 != 0) goto L_0x00d6
                if (r12 != 0) goto L_0x00a3
            L_0x00b7:
                boolean r11 = r10.done
                io.reactivex.internal.fuseable.SimpleQueue<U> r12 = r10.queue
                if (r11 == 0) goto L_0x00d0
                if (r12 == 0) goto L_0x00c5
                boolean r11 = r12.isEmpty()
                if (r11 == 0) goto L_0x00d0
            L_0x00c5:
                r14.removeInner(r10)
                boolean r4 = r14.checkTerminate()
                if (r4 == 0) goto L_0x00cf
                return
            L_0x00cf:
                r4 = 1
            L_0x00d0:
                int r7 = r7 + 1
                if (r7 != r6) goto L_0x00f9
                r7 = 0
                goto L_0x00f9
            L_0x00d6:
                r0.onNext(r12)
                boolean r12 = r14.checkTerminate()
                if (r12 == 0) goto L_0x00af
                return
            L_0x00e0:
                r4 = move-exception
                io.reactivex.exceptions.Exceptions.throwIfFatal(r4)
                r10.dispose()
                io.reactivex.internal.util.AtomicThrowable r11 = r14.errors
                r11.addThrowable(r4)
                boolean r4 = r14.checkTerminate()
                if (r4 == 0) goto L_0x00f3
                return
            L_0x00f3:
                r14.removeInner(r10)
                int r3 = r3 + 1
                r4 = 1
            L_0x00f9:
                int r3 = r3 + r1
                goto L_0x0098
            L_0x00fb:
                r14.lastIndex = r7
                r3 = r5[r7]
                long r5 = r3.id
                r14.lastId = r5
                r9 = r4
            L_0x0104:
                if (r9 == 0) goto L_0x0126
                int r3 = r14.maxConcurrency
                if (r3 == r8) goto L_0x0004
                monitor-enter(r14)
                java.util.Queue<io.reactivex.ObservableSource<? extends U>> r3 = r14.sources     // Catch:{ all -> 0x0123 }
                java.lang.Object r3 = r3.poll()     // Catch:{ all -> 0x0123 }
                io.reactivex.ObservableSource r3 = (io.reactivex.ObservableSource) r3     // Catch:{ all -> 0x0123 }
                if (r3 != 0) goto L_0x011d
                int r3 = r14.wip     // Catch:{ all -> 0x0123 }
                int r3 = r3 - r1
                r14.wip = r3     // Catch:{ all -> 0x0123 }
                monitor-exit(r14)     // Catch:{ all -> 0x0123 }
                goto L_0x0004
            L_0x011d:
                monitor-exit(r14)     // Catch:{ all -> 0x0123 }
                r14.subscribeInner(r3)
                goto L_0x0004
            L_0x0123:
                r0 = move-exception
                monitor-exit(r14)     // Catch:{ all -> 0x0123 }
                throw r0
            L_0x0126:
                int r2 = -r2
                int r2 = r14.addAndGet(r2)
                if (r2 != 0) goto L_0x0004
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: io.reactivex.internal.operators.observable.ObservableFlatMap.MergeObserver.drainLoop():void");
        }

        /* access modifiers changed from: package-private */
        public boolean checkTerminate() {
            if (this.cancelled) {
                return true;
            }
            Throwable th = (Throwable) this.errors.get();
            if (this.delayErrors || th == null) {
                return false;
            }
            disposeAll();
            Throwable terminate = this.errors.terminate();
            if (terminate != ExceptionHelper.TERMINATED) {
                this.actual.onError(terminate);
            }
            return true;
        }

        /* access modifiers changed from: package-private */
        public boolean disposeAll() {
            InnerObserver<?, ?>[] innerObserverArr;
            this.s.dispose();
            InnerObserver<?, ?>[] innerObserverArr2 = (InnerObserver[]) this.observers.get();
            InnerObserver<?, ?>[] innerObserverArr3 = CANCELLED;
            if (innerObserverArr2 == innerObserverArr3 || (innerObserverArr = (InnerObserver[]) this.observers.getAndSet(innerObserverArr3)) == CANCELLED) {
                return false;
            }
            for (InnerObserver<?, ?> dispose : innerObserverArr) {
                dispose.dispose();
            }
            return true;
        }
    }

    static final class InnerObserver<T, U> extends AtomicReference<Disposable> implements Observer<U> {
        private static final long serialVersionUID = -4606175640614850599L;
        volatile boolean done;
        int fusionMode;
        final long id;
        final MergeObserver<T, U> parent;
        volatile SimpleQueue<U> queue;

        InnerObserver(MergeObserver<T, U> mergeObserver, long j) {
            this.id = j;
            this.parent = mergeObserver;
        }

        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.setOnce(this, disposable) && (disposable instanceof QueueDisposable)) {
                QueueDisposable queueDisposable = (QueueDisposable) disposable;
                int requestFusion = queueDisposable.requestFusion(7);
                if (requestFusion == 1) {
                    this.fusionMode = requestFusion;
                    this.queue = queueDisposable;
                    this.done = true;
                    this.parent.drain();
                } else if (requestFusion == 2) {
                    this.fusionMode = requestFusion;
                    this.queue = queueDisposable;
                }
            }
        }

        public void onNext(U u) {
            if (this.fusionMode == 0) {
                this.parent.tryEmit(u, this);
            } else {
                this.parent.drain();
            }
        }

        public void onError(Throwable th) {
            if (this.parent.errors.addThrowable(th)) {
                if (!this.parent.delayErrors) {
                    this.parent.disposeAll();
                }
                this.done = true;
                this.parent.drain();
                return;
            }
            RxJavaPlugins.onError(th);
        }

        public void onComplete() {
            this.done = true;
            this.parent.drain();
        }

        public void dispose() {
            DisposableHelper.dispose(this);
        }
    }
}
