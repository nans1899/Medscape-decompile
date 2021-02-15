package com.google.firebase.inappmessaging.internal;

import com.google.firebase.inappmessaging.FirebaseInAppMessagingClickListener;
import com.google.firebase.inappmessaging.FirebaseInAppMessagingDisplayCallbacks;
import com.google.firebase.inappmessaging.FirebaseInAppMessagingDisplayErrorListener;
import com.google.firebase.inappmessaging.FirebaseInAppMessagingImpressionListener;
import com.google.firebase.inappmessaging.model.Action;
import com.google.firebase.inappmessaging.model.InAppMessage;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
public class DeveloperListenerManager {
    private static final ThreadPoolExecutor CALLBACK_QUEUE_EXECUTOR;
    private static final int KEEP_ALIVE_TIME_SECONDS = 15;
    private static final int POOL_SIZE = 1;
    public static DeveloperListenerManager instance = new DeveloperListenerManager();
    private static BlockingQueue<Runnable> mCallbackQueue = new LinkedBlockingQueue();
    private Map<FirebaseInAppMessagingClickListener, ClicksExecutorAndListener> registeredClickListeners = new HashMap();
    private Map<FirebaseInAppMessagingDisplayErrorListener, ErrorsExecutorAndListener> registeredErrorListeners = new HashMap();
    private Map<FirebaseInAppMessagingImpressionListener, ImpressionExecutorAndListener> registeredImpressionListeners = new HashMap();

    static {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 1, 15, TimeUnit.SECONDS, mCallbackQueue, new FIAMThreadFactory("EventListeners-"));
        CALLBACK_QUEUE_EXECUTOR = threadPoolExecutor;
        threadPoolExecutor.allowCoreThreadTimeOut(true);
    }

    public void impressionDetected(InAppMessage inAppMessage) {
        for (ImpressionExecutorAndListener next : this.registeredImpressionListeners.values()) {
            next.withExecutor(CALLBACK_QUEUE_EXECUTOR).execute(DeveloperListenerManager$$Lambda$1.lambdaFactory$(next, inAppMessage));
        }
    }

    public void displayErrorEncountered(InAppMessage inAppMessage, FirebaseInAppMessagingDisplayCallbacks.InAppMessagingErrorReason inAppMessagingErrorReason) {
        for (ErrorsExecutorAndListener next : this.registeredErrorListeners.values()) {
            next.withExecutor(CALLBACK_QUEUE_EXECUTOR).execute(DeveloperListenerManager$$Lambda$2.lambdaFactory$(next, inAppMessage, inAppMessagingErrorReason));
        }
    }

    public void messageClicked(InAppMessage inAppMessage, Action action) {
        for (ClicksExecutorAndListener next : this.registeredClickListeners.values()) {
            next.withExecutor(CALLBACK_QUEUE_EXECUTOR).execute(DeveloperListenerManager$$Lambda$3.lambdaFactory$(next, inAppMessage, action));
        }
    }

    public void addImpressionListener(FirebaseInAppMessagingImpressionListener firebaseInAppMessagingImpressionListener) {
        this.registeredImpressionListeners.put(firebaseInAppMessagingImpressionListener, new ImpressionExecutorAndListener(firebaseInAppMessagingImpressionListener));
    }

    public void addClickListener(FirebaseInAppMessagingClickListener firebaseInAppMessagingClickListener) {
        this.registeredClickListeners.put(firebaseInAppMessagingClickListener, new ClicksExecutorAndListener(firebaseInAppMessagingClickListener));
    }

    public void addDisplayErrorListener(FirebaseInAppMessagingDisplayErrorListener firebaseInAppMessagingDisplayErrorListener) {
        this.registeredErrorListeners.put(firebaseInAppMessagingDisplayErrorListener, new ErrorsExecutorAndListener(firebaseInAppMessagingDisplayErrorListener));
    }

    public void addImpressionListener(FirebaseInAppMessagingImpressionListener firebaseInAppMessagingImpressionListener, Executor executor) {
        this.registeredImpressionListeners.put(firebaseInAppMessagingImpressionListener, new ImpressionExecutorAndListener(firebaseInAppMessagingImpressionListener, executor));
    }

    public void addClickListener(FirebaseInAppMessagingClickListener firebaseInAppMessagingClickListener, Executor executor) {
        this.registeredClickListeners.put(firebaseInAppMessagingClickListener, new ClicksExecutorAndListener(firebaseInAppMessagingClickListener, executor));
    }

    public void addDisplayErrorListener(FirebaseInAppMessagingDisplayErrorListener firebaseInAppMessagingDisplayErrorListener, Executor executor) {
        this.registeredErrorListeners.put(firebaseInAppMessagingDisplayErrorListener, new ErrorsExecutorAndListener(firebaseInAppMessagingDisplayErrorListener, executor));
    }

    public void removeImpressionListener(FirebaseInAppMessagingImpressionListener firebaseInAppMessagingImpressionListener) {
        this.registeredImpressionListeners.remove(firebaseInAppMessagingImpressionListener);
    }

    public void removeClickListener(FirebaseInAppMessagingClickListener firebaseInAppMessagingClickListener) {
        this.registeredClickListeners.remove(firebaseInAppMessagingClickListener);
    }

    public void removeDisplayErrorListener(FirebaseInAppMessagingDisplayErrorListener firebaseInAppMessagingDisplayErrorListener) {
        this.registeredErrorListeners.remove(firebaseInAppMessagingDisplayErrorListener);
    }

    /* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
    static class FIAMThreadFactory implements ThreadFactory {
        private final String mNameSuffix;
        private final AtomicInteger threadNumber = new AtomicInteger(1);

        FIAMThreadFactory(String str) {
            this.mNameSuffix = str;
        }

        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(runnable, "FIAM-" + this.mNameSuffix + this.threadNumber.getAndIncrement());
            thread.setDaemon(false);
            thread.setPriority(9);
            return thread;
        }
    }

    /* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
    private static abstract class ExecutorAndListener<T> {
        private final Executor executor;

        public abstract T getListener();

        public Executor withExecutor(Executor executor2) {
            Executor executor3 = this.executor;
            return executor3 == null ? executor2 : executor3;
        }

        public ExecutorAndListener(Executor executor2) {
            this.executor = executor2;
        }
    }

    /* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
    private static class ImpressionExecutorAndListener extends ExecutorAndListener<FirebaseInAppMessagingImpressionListener> {
        FirebaseInAppMessagingImpressionListener listener;

        public ImpressionExecutorAndListener(FirebaseInAppMessagingImpressionListener firebaseInAppMessagingImpressionListener, Executor executor) {
            super(executor);
            this.listener = firebaseInAppMessagingImpressionListener;
        }

        public ImpressionExecutorAndListener(FirebaseInAppMessagingImpressionListener firebaseInAppMessagingImpressionListener) {
            super((Executor) null);
            this.listener = firebaseInAppMessagingImpressionListener;
        }

        public FirebaseInAppMessagingImpressionListener getListener() {
            return this.listener;
        }
    }

    /* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
    private static class ClicksExecutorAndListener extends ExecutorAndListener<FirebaseInAppMessagingClickListener> {
        FirebaseInAppMessagingClickListener listener;

        public ClicksExecutorAndListener(FirebaseInAppMessagingClickListener firebaseInAppMessagingClickListener, Executor executor) {
            super(executor);
            this.listener = firebaseInAppMessagingClickListener;
        }

        public ClicksExecutorAndListener(FirebaseInAppMessagingClickListener firebaseInAppMessagingClickListener) {
            super((Executor) null);
            this.listener = firebaseInAppMessagingClickListener;
        }

        public FirebaseInAppMessagingClickListener getListener() {
            return this.listener;
        }
    }

    /* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
    private static class ErrorsExecutorAndListener extends ExecutorAndListener<FirebaseInAppMessagingDisplayErrorListener> {
        FirebaseInAppMessagingDisplayErrorListener listener;

        public ErrorsExecutorAndListener(FirebaseInAppMessagingDisplayErrorListener firebaseInAppMessagingDisplayErrorListener, Executor executor) {
            super(executor);
            this.listener = firebaseInAppMessagingDisplayErrorListener;
        }

        public ErrorsExecutorAndListener(FirebaseInAppMessagingDisplayErrorListener firebaseInAppMessagingDisplayErrorListener) {
            super((Executor) null);
            this.listener = firebaseInAppMessagingDisplayErrorListener;
        }

        public FirebaseInAppMessagingDisplayErrorListener getListener() {
            return this.listener;
        }
    }
}
