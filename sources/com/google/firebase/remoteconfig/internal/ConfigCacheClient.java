package com.google.firebase.remoteconfig.internal;

import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* compiled from: com.google.firebase:firebase-config@@19.0.3 */
public class ConfigCacheClient {
    private static final Executor DIRECT_EXECUTOR = ConfigCacheClient$$Lambda$4.lambdaFactory$();
    static final long DISK_READ_TIMEOUT_IN_SECONDS = 5;
    private static final Map<String, ConfigCacheClient> clientInstances = new HashMap();
    private Task<ConfigContainer> cachedContainerTask = null;
    private final ExecutorService executorService;
    private final ConfigStorageClient storageClient;

    private ConfigCacheClient(ExecutorService executorService2, ConfigStorageClient configStorageClient) {
        this.executorService = executorService2;
        this.storageClient = configStorageClient;
    }

    public Task<ConfigContainer> putWithoutWaitingForDiskWrite(ConfigContainer configContainer) {
        updateInMemoryConfigContainer(configContainer);
        return put(configContainer, false);
    }

    public ConfigContainer getBlocking() {
        return getBlocking(5);
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0024, code lost:
        return (com.google.firebase.remoteconfig.internal.ConfigContainer) await(get(), r3, java.util.concurrent.TimeUnit.SECONDS);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0025, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x002a, code lost:
        android.util.Log.d(com.google.firebase.remoteconfig.FirebaseRemoteConfig.TAG, "Reading from storage file failed.", r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0032, code lost:
        return null;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.google.firebase.remoteconfig.internal.ConfigContainer getBlocking(long r3) {
        /*
            r2 = this;
            monitor-enter(r2)
            com.google.android.gms.tasks.Task<com.google.firebase.remoteconfig.internal.ConfigContainer> r0 = r2.cachedContainerTask     // Catch:{ all -> 0x0033 }
            if (r0 == 0) goto L_0x0017
            com.google.android.gms.tasks.Task<com.google.firebase.remoteconfig.internal.ConfigContainer> r0 = r2.cachedContainerTask     // Catch:{ all -> 0x0033 }
            boolean r0 = r0.isSuccessful()     // Catch:{ all -> 0x0033 }
            if (r0 == 0) goto L_0x0017
            com.google.android.gms.tasks.Task<com.google.firebase.remoteconfig.internal.ConfigContainer> r3 = r2.cachedContainerTask     // Catch:{ all -> 0x0033 }
            java.lang.Object r3 = r3.getResult()     // Catch:{ all -> 0x0033 }
            com.google.firebase.remoteconfig.internal.ConfigContainer r3 = (com.google.firebase.remoteconfig.internal.ConfigContainer) r3     // Catch:{ all -> 0x0033 }
            monitor-exit(r2)     // Catch:{ all -> 0x0033 }
            return r3
        L_0x0017:
            monitor-exit(r2)     // Catch:{ all -> 0x0033 }
            com.google.android.gms.tasks.Task r0 = r2.get()     // Catch:{ InterruptedException -> 0x0029, ExecutionException -> 0x0027, TimeoutException -> 0x0025 }
            java.util.concurrent.TimeUnit r1 = java.util.concurrent.TimeUnit.SECONDS     // Catch:{ InterruptedException -> 0x0029, ExecutionException -> 0x0027, TimeoutException -> 0x0025 }
            java.lang.Object r3 = await(r0, r3, r1)     // Catch:{ InterruptedException -> 0x0029, ExecutionException -> 0x0027, TimeoutException -> 0x0025 }
            com.google.firebase.remoteconfig.internal.ConfigContainer r3 = (com.google.firebase.remoteconfig.internal.ConfigContainer) r3     // Catch:{ InterruptedException -> 0x0029, ExecutionException -> 0x0027, TimeoutException -> 0x0025 }
            return r3
        L_0x0025:
            r3 = move-exception
            goto L_0x002a
        L_0x0027:
            r3 = move-exception
            goto L_0x002a
        L_0x0029:
            r3 = move-exception
        L_0x002a:
            java.lang.String r4 = "FirebaseRemoteConfig"
            java.lang.String r0 = "Reading from storage file failed."
            android.util.Log.d(r4, r0, r3)
            r3 = 0
            return r3
        L_0x0033:
            r3 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x0033 }
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.remoteconfig.internal.ConfigCacheClient.getBlocking(long):com.google.firebase.remoteconfig.internal.ConfigContainer");
    }

    public Task<ConfigContainer> put(ConfigContainer configContainer) {
        return put(configContainer, true);
    }

    public Task<ConfigContainer> put(ConfigContainer configContainer, boolean z) {
        return Tasks.call(this.executorService, ConfigCacheClient$$Lambda$1.lambdaFactory$(this, configContainer)).onSuccessTask(this.executorService, ConfigCacheClient$$Lambda$2.lambdaFactory$(this, z, configContainer));
    }

    static /* synthetic */ Task lambda$put$1(ConfigCacheClient configCacheClient, boolean z, ConfigContainer configContainer, Void voidR) throws Exception {
        if (z) {
            configCacheClient.updateInMemoryConfigContainer(configContainer);
        }
        return Tasks.forResult(configContainer);
    }

    public synchronized Task<ConfigContainer> get() {
        if (this.cachedContainerTask == null || (this.cachedContainerTask.isComplete() && !this.cachedContainerTask.isSuccessful())) {
            ExecutorService executorService2 = this.executorService;
            ConfigStorageClient configStorageClient = this.storageClient;
            configStorageClient.getClass();
            this.cachedContainerTask = Tasks.call(executorService2, ConfigCacheClient$$Lambda$3.lambdaFactory$(configStorageClient));
        }
        return this.cachedContainerTask;
    }

    public void clear() {
        synchronized (this) {
            this.cachedContainerTask = Tasks.forResult(null);
        }
        this.storageClient.clear();
    }

    private synchronized void updateInMemoryConfigContainer(ConfigContainer configContainer) {
        this.cachedContainerTask = Tasks.forResult(configContainer);
    }

    /* access modifiers changed from: package-private */
    public synchronized Task<ConfigContainer> getCachedContainerTask() {
        return this.cachedContainerTask;
    }

    public static synchronized ConfigCacheClient getInstance(ExecutorService executorService2, ConfigStorageClient configStorageClient) {
        ConfigCacheClient configCacheClient;
        synchronized (ConfigCacheClient.class) {
            String fileName = configStorageClient.getFileName();
            if (!clientInstances.containsKey(fileName)) {
                clientInstances.put(fileName, new ConfigCacheClient(executorService2, configStorageClient));
            }
            configCacheClient = clientInstances.get(fileName);
        }
        return configCacheClient;
    }

    public static synchronized void clearInstancesForTest() {
        synchronized (ConfigCacheClient.class) {
            clientInstances.clear();
        }
    }

    private static <TResult> TResult await(Task<TResult> task, long j, TimeUnit timeUnit) throws ExecutionException, InterruptedException, TimeoutException {
        AwaitListener awaitListener = new AwaitListener();
        task.addOnSuccessListener(DIRECT_EXECUTOR, (OnSuccessListener<? super TResult>) awaitListener);
        task.addOnFailureListener(DIRECT_EXECUTOR, (OnFailureListener) awaitListener);
        task.addOnCanceledListener(DIRECT_EXECUTOR, (OnCanceledListener) awaitListener);
        if (!awaitListener.await(j, timeUnit)) {
            throw new TimeoutException("Task await timed out.");
        } else if (task.isSuccessful()) {
            return task.getResult();
        } else {
            throw new ExecutionException(task.getException());
        }
    }

    /* compiled from: com.google.firebase:firebase-config@@19.0.3 */
    private static class AwaitListener<TResult> implements OnSuccessListener<TResult>, OnFailureListener, OnCanceledListener {
        private final CountDownLatch latch;

        private AwaitListener() {
            this.latch = new CountDownLatch(1);
        }

        public void onSuccess(TResult tresult) {
            this.latch.countDown();
        }

        public void onFailure(Exception exc) {
            this.latch.countDown();
        }

        public void onCanceled() {
            this.latch.countDown();
        }

        public void await() throws InterruptedException {
            this.latch.await();
        }

        public boolean await(long j, TimeUnit timeUnit) throws InterruptedException {
            return this.latch.await(j, timeUnit);
        }
    }
}
