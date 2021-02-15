package com.bumptech.glide.request;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import com.bumptech.glide.GlideContext;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.Engine;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.resource.drawable.DrawableDecoderCompat;
import com.bumptech.glide.request.target.SizeReadyCallback;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.TransitionFactory;
import com.bumptech.glide.util.LogTime;
import com.bumptech.glide.util.Util;
import com.bumptech.glide.util.pool.StateVerifier;
import java.util.List;
import java.util.concurrent.Executor;

public final class SingleRequest<R> implements Request, SizeReadyCallback, ResourceCallback {
    private static final String GLIDE_TAG = "Glide";
    private static final boolean IS_VERBOSE_LOGGABLE = Log.isLoggable(TAG, 2);
    private static final String TAG = "Request";
    private final TransitionFactory<? super R> animationFactory;
    private final Executor callbackExecutor;
    private final Context context;
    private volatile Engine engine;
    private Drawable errorDrawable;
    private Drawable fallbackDrawable;
    private final GlideContext glideContext;
    private int height;
    private boolean isCallingCallbacks;
    private Engine.LoadStatus loadStatus;
    private final Object model;
    private final int overrideHeight;
    private final int overrideWidth;
    private Drawable placeholderDrawable;
    private final Priority priority;
    private final RequestCoordinator requestCoordinator;
    private final List<RequestListener<R>> requestListeners;
    private final Object requestLock;
    private final BaseRequestOptions<?> requestOptions;
    private RuntimeException requestOrigin;
    private Resource<R> resource;
    private long startTime;
    private final StateVerifier stateVerifier;
    private Status status;
    private final String tag;
    private final Target<R> target;
    private final RequestListener<R> targetListener;
    private final Class<R> transcodeClass;
    private int width;

    private enum Status {
        PENDING,
        RUNNING,
        WAITING_FOR_SIZE,
        COMPLETE,
        FAILED,
        CLEARED
    }

    public static <R> SingleRequest<R> obtain(Context context2, GlideContext glideContext2, Object obj, Object obj2, Class<R> cls, BaseRequestOptions<?> baseRequestOptions, int i, int i2, Priority priority2, Target<R> target2, RequestListener<R> requestListener, List<RequestListener<R>> list, RequestCoordinator requestCoordinator2, Engine engine2, TransitionFactory<? super R> transitionFactory, Executor executor) {
        return new SingleRequest(context2, glideContext2, obj, obj2, cls, baseRequestOptions, i, i2, priority2, target2, requestListener, list, requestCoordinator2, engine2, transitionFactory, executor);
    }

    private SingleRequest(Context context2, GlideContext glideContext2, Object obj, Object obj2, Class<R> cls, BaseRequestOptions<?> baseRequestOptions, int i, int i2, Priority priority2, Target<R> target2, RequestListener<R> requestListener, List<RequestListener<R>> list, RequestCoordinator requestCoordinator2, Engine engine2, TransitionFactory<? super R> transitionFactory, Executor executor) {
        this.tag = IS_VERBOSE_LOGGABLE ? String.valueOf(super.hashCode()) : null;
        this.stateVerifier = StateVerifier.newInstance();
        this.requestLock = obj;
        this.context = context2;
        this.glideContext = glideContext2;
        this.model = obj2;
        this.transcodeClass = cls;
        this.requestOptions = baseRequestOptions;
        this.overrideWidth = i;
        this.overrideHeight = i2;
        this.priority = priority2;
        this.target = target2;
        this.targetListener = requestListener;
        this.requestListeners = list;
        this.requestCoordinator = requestCoordinator2;
        this.engine = engine2;
        this.animationFactory = transitionFactory;
        this.callbackExecutor = executor;
        this.status = Status.PENDING;
        if (this.requestOrigin == null && glideContext2.isLoggingRequestOriginsEnabled()) {
            this.requestOrigin = new RuntimeException("Glide request origin trace");
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:37:0x00a6, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void begin() {
        /*
            r4 = this;
            java.lang.Object r0 = r4.requestLock
            monitor-enter(r0)
            r4.assertNotCallingCallbacks()     // Catch:{ all -> 0x00af }
            com.bumptech.glide.util.pool.StateVerifier r1 = r4.stateVerifier     // Catch:{ all -> 0x00af }
            r1.throwIfRecycled()     // Catch:{ all -> 0x00af }
            long r1 = com.bumptech.glide.util.LogTime.getLogTime()     // Catch:{ all -> 0x00af }
            r4.startTime = r1     // Catch:{ all -> 0x00af }
            java.lang.Object r1 = r4.model     // Catch:{ all -> 0x00af }
            if (r1 != 0) goto L_0x003c
            int r1 = r4.overrideWidth     // Catch:{ all -> 0x00af }
            int r2 = r4.overrideHeight     // Catch:{ all -> 0x00af }
            boolean r1 = com.bumptech.glide.util.Util.isValidDimensions(r1, r2)     // Catch:{ all -> 0x00af }
            if (r1 == 0) goto L_0x0027
            int r1 = r4.overrideWidth     // Catch:{ all -> 0x00af }
            r4.width = r1     // Catch:{ all -> 0x00af }
            int r1 = r4.overrideHeight     // Catch:{ all -> 0x00af }
            r4.height = r1     // Catch:{ all -> 0x00af }
        L_0x0027:
            android.graphics.drawable.Drawable r1 = r4.getFallbackDrawable()     // Catch:{ all -> 0x00af }
            if (r1 != 0) goto L_0x002f
            r1 = 5
            goto L_0x0030
        L_0x002f:
            r1 = 3
        L_0x0030:
            com.bumptech.glide.load.engine.GlideException r2 = new com.bumptech.glide.load.engine.GlideException     // Catch:{ all -> 0x00af }
            java.lang.String r3 = "Received null model"
            r2.<init>(r3)     // Catch:{ all -> 0x00af }
            r4.onLoadFailed(r2, r1)     // Catch:{ all -> 0x00af }
            monitor-exit(r0)     // Catch:{ all -> 0x00af }
            return
        L_0x003c:
            com.bumptech.glide.request.SingleRequest$Status r1 = r4.status     // Catch:{ all -> 0x00af }
            com.bumptech.glide.request.SingleRequest$Status r2 = com.bumptech.glide.request.SingleRequest.Status.RUNNING     // Catch:{ all -> 0x00af }
            if (r1 == r2) goto L_0x00a7
            com.bumptech.glide.request.SingleRequest$Status r1 = r4.status     // Catch:{ all -> 0x00af }
            com.bumptech.glide.request.SingleRequest$Status r2 = com.bumptech.glide.request.SingleRequest.Status.COMPLETE     // Catch:{ all -> 0x00af }
            if (r1 != r2) goto L_0x0051
            com.bumptech.glide.load.engine.Resource<R> r1 = r4.resource     // Catch:{ all -> 0x00af }
            com.bumptech.glide.load.DataSource r2 = com.bumptech.glide.load.DataSource.MEMORY_CACHE     // Catch:{ all -> 0x00af }
            r4.onResourceReady(r1, r2)     // Catch:{ all -> 0x00af }
            monitor-exit(r0)     // Catch:{ all -> 0x00af }
            return
        L_0x0051:
            com.bumptech.glide.request.SingleRequest$Status r1 = com.bumptech.glide.request.SingleRequest.Status.WAITING_FOR_SIZE     // Catch:{ all -> 0x00af }
            r4.status = r1     // Catch:{ all -> 0x00af }
            int r1 = r4.overrideWidth     // Catch:{ all -> 0x00af }
            int r2 = r4.overrideHeight     // Catch:{ all -> 0x00af }
            boolean r1 = com.bumptech.glide.util.Util.isValidDimensions(r1, r2)     // Catch:{ all -> 0x00af }
            if (r1 == 0) goto L_0x0067
            int r1 = r4.overrideWidth     // Catch:{ all -> 0x00af }
            int r2 = r4.overrideHeight     // Catch:{ all -> 0x00af }
            r4.onSizeReady(r1, r2)     // Catch:{ all -> 0x00af }
            goto L_0x006c
        L_0x0067:
            com.bumptech.glide.request.target.Target<R> r1 = r4.target     // Catch:{ all -> 0x00af }
            r1.getSize(r4)     // Catch:{ all -> 0x00af }
        L_0x006c:
            com.bumptech.glide.request.SingleRequest$Status r1 = r4.status     // Catch:{ all -> 0x00af }
            com.bumptech.glide.request.SingleRequest$Status r2 = com.bumptech.glide.request.SingleRequest.Status.RUNNING     // Catch:{ all -> 0x00af }
            if (r1 == r2) goto L_0x0078
            com.bumptech.glide.request.SingleRequest$Status r1 = r4.status     // Catch:{ all -> 0x00af }
            com.bumptech.glide.request.SingleRequest$Status r2 = com.bumptech.glide.request.SingleRequest.Status.WAITING_FOR_SIZE     // Catch:{ all -> 0x00af }
            if (r1 != r2) goto L_0x0087
        L_0x0078:
            boolean r1 = r4.canNotifyStatusChanged()     // Catch:{ all -> 0x00af }
            if (r1 == 0) goto L_0x0087
            com.bumptech.glide.request.target.Target<R> r1 = r4.target     // Catch:{ all -> 0x00af }
            android.graphics.drawable.Drawable r2 = r4.getPlaceholderDrawable()     // Catch:{ all -> 0x00af }
            r1.onLoadStarted(r2)     // Catch:{ all -> 0x00af }
        L_0x0087:
            boolean r1 = IS_VERBOSE_LOGGABLE     // Catch:{ all -> 0x00af }
            if (r1 == 0) goto L_0x00a5
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x00af }
            r1.<init>()     // Catch:{ all -> 0x00af }
            java.lang.String r2 = "finished run method in "
            r1.append(r2)     // Catch:{ all -> 0x00af }
            long r2 = r4.startTime     // Catch:{ all -> 0x00af }
            double r2 = com.bumptech.glide.util.LogTime.getElapsedMillis(r2)     // Catch:{ all -> 0x00af }
            r1.append(r2)     // Catch:{ all -> 0x00af }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x00af }
            r4.logV(r1)     // Catch:{ all -> 0x00af }
        L_0x00a5:
            monitor-exit(r0)     // Catch:{ all -> 0x00af }
            return
        L_0x00a7:
            java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException     // Catch:{ all -> 0x00af }
            java.lang.String r2 = "Cannot restart a running request"
            r1.<init>(r2)     // Catch:{ all -> 0x00af }
            throw r1     // Catch:{ all -> 0x00af }
        L_0x00af:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x00af }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.request.SingleRequest.begin():void");
    }

    private void cancel() {
        assertNotCallingCallbacks();
        this.stateVerifier.throwIfRecycled();
        this.target.removeCallback(this);
        Engine.LoadStatus loadStatus2 = this.loadStatus;
        if (loadStatus2 != null) {
            loadStatus2.cancel();
            this.loadStatus = null;
        }
    }

    private void assertNotCallingCallbacks() {
        if (this.isCallingCallbacks) {
            throw new IllegalStateException("You can't start or clear loads in RequestListener or Target callbacks. If you're trying to start a fallback request when a load fails, use RequestBuilder#error(RequestBuilder). Otherwise consider posting your into() or clear() calls to the main thread using a Handler instead.");
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0034, code lost:
        if (r2 == null) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0036, code lost:
        r4.engine.release(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void clear() {
        /*
            r4 = this;
            java.lang.Object r0 = r4.requestLock
            monitor-enter(r0)
            r4.assertNotCallingCallbacks()     // Catch:{ all -> 0x003c }
            com.bumptech.glide.util.pool.StateVerifier r1 = r4.stateVerifier     // Catch:{ all -> 0x003c }
            r1.throwIfRecycled()     // Catch:{ all -> 0x003c }
            com.bumptech.glide.request.SingleRequest$Status r1 = r4.status     // Catch:{ all -> 0x003c }
            com.bumptech.glide.request.SingleRequest$Status r2 = com.bumptech.glide.request.SingleRequest.Status.CLEARED     // Catch:{ all -> 0x003c }
            if (r1 != r2) goto L_0x0013
            monitor-exit(r0)     // Catch:{ all -> 0x003c }
            return
        L_0x0013:
            r4.cancel()     // Catch:{ all -> 0x003c }
            com.bumptech.glide.load.engine.Resource<R> r1 = r4.resource     // Catch:{ all -> 0x003c }
            r2 = 0
            if (r1 == 0) goto L_0x0020
            com.bumptech.glide.load.engine.Resource<R> r1 = r4.resource     // Catch:{ all -> 0x003c }
            r4.resource = r2     // Catch:{ all -> 0x003c }
            r2 = r1
        L_0x0020:
            boolean r1 = r4.canNotifyCleared()     // Catch:{ all -> 0x003c }
            if (r1 == 0) goto L_0x002f
            com.bumptech.glide.request.target.Target<R> r1 = r4.target     // Catch:{ all -> 0x003c }
            android.graphics.drawable.Drawable r3 = r4.getPlaceholderDrawable()     // Catch:{ all -> 0x003c }
            r1.onLoadCleared(r3)     // Catch:{ all -> 0x003c }
        L_0x002f:
            com.bumptech.glide.request.SingleRequest$Status r1 = com.bumptech.glide.request.SingleRequest.Status.CLEARED     // Catch:{ all -> 0x003c }
            r4.status = r1     // Catch:{ all -> 0x003c }
            monitor-exit(r0)     // Catch:{ all -> 0x003c }
            if (r2 == 0) goto L_0x003b
            com.bumptech.glide.load.engine.Engine r0 = r4.engine
            r0.release(r2)
        L_0x003b:
            return
        L_0x003c:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x003c }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.request.SingleRequest.clear():void");
    }

    public void pause() {
        synchronized (this.requestLock) {
            if (isRunning()) {
                clear();
            }
        }
    }

    public boolean isRunning() {
        boolean z;
        synchronized (this.requestLock) {
            if (this.status != Status.RUNNING) {
                if (this.status != Status.WAITING_FOR_SIZE) {
                    z = false;
                }
            }
            z = true;
        }
        return z;
    }

    public boolean isComplete() {
        boolean z;
        synchronized (this.requestLock) {
            z = this.status == Status.COMPLETE;
        }
        return z;
    }

    public boolean isCleared() {
        boolean z;
        synchronized (this.requestLock) {
            z = this.status == Status.CLEARED;
        }
        return z;
    }

    public boolean isAnyResourceSet() {
        boolean z;
        synchronized (this.requestLock) {
            z = this.status == Status.COMPLETE;
        }
        return z;
    }

    private Drawable getErrorDrawable() {
        if (this.errorDrawable == null) {
            Drawable errorPlaceholder = this.requestOptions.getErrorPlaceholder();
            this.errorDrawable = errorPlaceholder;
            if (errorPlaceholder == null && this.requestOptions.getErrorId() > 0) {
                this.errorDrawable = loadDrawable(this.requestOptions.getErrorId());
            }
        }
        return this.errorDrawable;
    }

    private Drawable getPlaceholderDrawable() {
        if (this.placeholderDrawable == null) {
            Drawable placeholderDrawable2 = this.requestOptions.getPlaceholderDrawable();
            this.placeholderDrawable = placeholderDrawable2;
            if (placeholderDrawable2 == null && this.requestOptions.getPlaceholderId() > 0) {
                this.placeholderDrawable = loadDrawable(this.requestOptions.getPlaceholderId());
            }
        }
        return this.placeholderDrawable;
    }

    private Drawable getFallbackDrawable() {
        if (this.fallbackDrawable == null) {
            Drawable fallbackDrawable2 = this.requestOptions.getFallbackDrawable();
            this.fallbackDrawable = fallbackDrawable2;
            if (fallbackDrawable2 == null && this.requestOptions.getFallbackId() > 0) {
                this.fallbackDrawable = loadDrawable(this.requestOptions.getFallbackId());
            }
        }
        return this.fallbackDrawable;
    }

    private Drawable loadDrawable(int i) {
        return DrawableDecoderCompat.getDrawable((Context) this.glideContext, i, this.requestOptions.getTheme() != null ? this.requestOptions.getTheme() : this.context.getTheme());
    }

    private void setErrorPlaceholder() {
        if (canNotifyStatusChanged()) {
            Drawable drawable = null;
            if (this.model == null) {
                drawable = getFallbackDrawable();
            }
            if (drawable == null) {
                drawable = getErrorDrawable();
            }
            if (drawable == null) {
                drawable = getPlaceholderDrawable();
            }
            this.target.onLoadFailed(drawable);
        }
    }

    public void onSizeReady(int i, int i2) {
        Object obj;
        this.stateVerifier.throwIfRecycled();
        Object obj2 = this.requestLock;
        synchronized (obj2) {
            try {
                if (IS_VERBOSE_LOGGABLE) {
                    logV("Got onSizeReady in " + LogTime.getElapsedMillis(this.startTime));
                }
                if (this.status == Status.WAITING_FOR_SIZE) {
                    this.status = Status.RUNNING;
                    float sizeMultiplier = this.requestOptions.getSizeMultiplier();
                    this.width = maybeApplySizeMultiplier(i, sizeMultiplier);
                    this.height = maybeApplySizeMultiplier(i2, sizeMultiplier);
                    if (IS_VERBOSE_LOGGABLE) {
                        logV("finished setup for calling load in " + LogTime.getElapsedMillis(this.startTime));
                    }
                    obj = obj2;
                    try {
                        this.loadStatus = this.engine.load(this.glideContext, this.model, this.requestOptions.getSignature(), this.width, this.height, this.requestOptions.getResourceClass(), this.transcodeClass, this.priority, this.requestOptions.getDiskCacheStrategy(), this.requestOptions.getTransformations(), this.requestOptions.isTransformationRequired(), this.requestOptions.isScaleOnlyOrNoTransform(), this.requestOptions.getOptions(), this.requestOptions.isMemoryCacheable(), this.requestOptions.getUseUnlimitedSourceGeneratorsPool(), this.requestOptions.getUseAnimationPool(), this.requestOptions.getOnlyRetrieveFromCache(), this, this.callbackExecutor);
                        if (this.status != Status.RUNNING) {
                            this.loadStatus = null;
                        }
                        if (IS_VERBOSE_LOGGABLE) {
                            logV("finished onSizeReady in " + LogTime.getElapsedMillis(this.startTime));
                        }
                    } catch (Throwable th) {
                        th = th;
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                obj = obj2;
                throw th;
            }
        }
    }

    private static int maybeApplySizeMultiplier(int i, float f) {
        return i == Integer.MIN_VALUE ? i : Math.round(f * ((float) i));
    }

    private boolean canSetResource() {
        RequestCoordinator requestCoordinator2 = this.requestCoordinator;
        return requestCoordinator2 == null || requestCoordinator2.canSetImage(this);
    }

    private boolean canNotifyCleared() {
        RequestCoordinator requestCoordinator2 = this.requestCoordinator;
        return requestCoordinator2 == null || requestCoordinator2.canNotifyCleared(this);
    }

    private boolean canNotifyStatusChanged() {
        RequestCoordinator requestCoordinator2 = this.requestCoordinator;
        return requestCoordinator2 == null || requestCoordinator2.canNotifyStatusChanged(this);
    }

    private boolean isFirstReadyResource() {
        RequestCoordinator requestCoordinator2 = this.requestCoordinator;
        return requestCoordinator2 == null || !requestCoordinator2.getRoot().isAnyResourceSet();
    }

    private void notifyLoadSuccess() {
        RequestCoordinator requestCoordinator2 = this.requestCoordinator;
        if (requestCoordinator2 != null) {
            requestCoordinator2.onRequestSuccess(this);
        }
    }

    private void notifyLoadFailed() {
        RequestCoordinator requestCoordinator2 = this.requestCoordinator;
        if (requestCoordinator2 != null) {
            requestCoordinator2.onRequestFailed(this);
        }
    }

    /* JADX WARNING: type inference failed for: r0v1, types: [com.bumptech.glide.load.engine.Engine$LoadStatus, com.bumptech.glide.load.engine.Resource<R>] */
    /* JADX WARNING: type inference failed for: r0v2, types: [com.bumptech.glide.load.engine.Resource] */
    /* JADX WARNING: type inference failed for: r0v4 */
    /* JADX WARNING: type inference failed for: r0v8 */
    /* JADX WARNING: type inference failed for: r0v9 */
    /* JADX WARNING: type inference failed for: r0v10 */
    /* JADX WARNING: type inference failed for: r0v11 */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x004f, code lost:
        if (r6 == null) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0051, code lost:
        r5.engine.release(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:?, code lost:
        return;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onResourceReady(com.bumptech.glide.load.engine.Resource<?> r6, com.bumptech.glide.load.DataSource r7) {
        /*
            r5 = this;
            com.bumptech.glide.util.pool.StateVerifier r0 = r5.stateVerifier
            r0.throwIfRecycled()
            r0 = 0
            java.lang.Object r1 = r5.requestLock     // Catch:{ all -> 0x00bc }
            monitor-enter(r1)     // Catch:{ all -> 0x00bc }
            r5.loadStatus = r0     // Catch:{ all -> 0x00b9 }
            if (r6 != 0) goto L_0x002f
            com.bumptech.glide.load.engine.GlideException r6 = new com.bumptech.glide.load.engine.GlideException     // Catch:{ all -> 0x00b9 }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ all -> 0x00b9 }
            r7.<init>()     // Catch:{ all -> 0x00b9 }
            java.lang.String r2 = "Expected to receive a Resource<R> with an object of "
            r7.append(r2)     // Catch:{ all -> 0x00b9 }
            java.lang.Class<R> r2 = r5.transcodeClass     // Catch:{ all -> 0x00b9 }
            r7.append(r2)     // Catch:{ all -> 0x00b9 }
            java.lang.String r2 = " inside, but instead got null."
            r7.append(r2)     // Catch:{ all -> 0x00b9 }
            java.lang.String r7 = r7.toString()     // Catch:{ all -> 0x00b9 }
            r6.<init>(r7)     // Catch:{ all -> 0x00b9 }
            r5.onLoadFailed(r6)     // Catch:{ all -> 0x00b9 }
            monitor-exit(r1)     // Catch:{ all -> 0x00b9 }
            return
        L_0x002f:
            java.lang.Object r2 = r6.get()     // Catch:{ all -> 0x00b9 }
            if (r2 == 0) goto L_0x005c
            java.lang.Class<R> r3 = r5.transcodeClass     // Catch:{ all -> 0x00b9 }
            java.lang.Class r4 = r2.getClass()     // Catch:{ all -> 0x00b9 }
            boolean r3 = r3.isAssignableFrom(r4)     // Catch:{ all -> 0x00b9 }
            if (r3 != 0) goto L_0x0042
            goto L_0x005c
        L_0x0042:
            boolean r3 = r5.canSetResource()     // Catch:{ all -> 0x00b9 }
            if (r3 != 0) goto L_0x0057
            r5.resource = r0     // Catch:{ all -> 0x00b5 }
            com.bumptech.glide.request.SingleRequest$Status r7 = com.bumptech.glide.request.SingleRequest.Status.COMPLETE     // Catch:{ all -> 0x00b5 }
            r5.status = r7     // Catch:{ all -> 0x00b5 }
            monitor-exit(r1)     // Catch:{ all -> 0x00b5 }
            if (r6 == 0) goto L_0x0056
            com.bumptech.glide.load.engine.Engine r7 = r5.engine
            r7.release(r6)
        L_0x0056:
            return
        L_0x0057:
            r5.onResourceReady(r6, r2, r7)     // Catch:{ all -> 0x00b9 }
            monitor-exit(r1)     // Catch:{ all -> 0x00b9 }
            return
        L_0x005c:
            r5.resource = r0     // Catch:{ all -> 0x00b5 }
            com.bumptech.glide.load.engine.GlideException r7 = new com.bumptech.glide.load.engine.GlideException     // Catch:{ all -> 0x00b5 }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x00b5 }
            r0.<init>()     // Catch:{ all -> 0x00b5 }
            java.lang.String r3 = "Expected to receive an object of "
            r0.append(r3)     // Catch:{ all -> 0x00b5 }
            java.lang.Class<R> r3 = r5.transcodeClass     // Catch:{ all -> 0x00b5 }
            r0.append(r3)     // Catch:{ all -> 0x00b5 }
            java.lang.String r3 = " but instead got "
            r0.append(r3)     // Catch:{ all -> 0x00b5 }
            if (r2 == 0) goto L_0x007b
            java.lang.Class r3 = r2.getClass()     // Catch:{ all -> 0x00b5 }
            goto L_0x007d
        L_0x007b:
            java.lang.String r3 = ""
        L_0x007d:
            r0.append(r3)     // Catch:{ all -> 0x00b5 }
            java.lang.String r3 = "{"
            r0.append(r3)     // Catch:{ all -> 0x00b5 }
            r0.append(r2)     // Catch:{ all -> 0x00b5 }
            java.lang.String r3 = "} inside Resource{"
            r0.append(r3)     // Catch:{ all -> 0x00b5 }
            r0.append(r6)     // Catch:{ all -> 0x00b5 }
            java.lang.String r3 = "}."
            r0.append(r3)     // Catch:{ all -> 0x00b5 }
            if (r2 == 0) goto L_0x009d
            java.lang.String r2 = ""
            goto L_0x009f
        L_0x009d:
            java.lang.String r2 = " To indicate failure return a null Resource object, rather than a Resource object containing null data."
        L_0x009f:
            r0.append(r2)     // Catch:{ all -> 0x00b5 }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x00b5 }
            r7.<init>(r0)     // Catch:{ all -> 0x00b5 }
            r5.onLoadFailed(r7)     // Catch:{ all -> 0x00b5 }
            monitor-exit(r1)     // Catch:{ all -> 0x00b5 }
            if (r6 == 0) goto L_0x00b4
            com.bumptech.glide.load.engine.Engine r7 = r5.engine
            r7.release(r6)
        L_0x00b4:
            return
        L_0x00b5:
            r7 = move-exception
            r0 = r6
            r6 = r7
            goto L_0x00ba
        L_0x00b9:
            r6 = move-exception
        L_0x00ba:
            monitor-exit(r1)     // Catch:{ all -> 0x00b9 }
            throw r6     // Catch:{ all -> 0x00bc }
        L_0x00bc:
            r6 = move-exception
            if (r0 == 0) goto L_0x00c4
            com.bumptech.glide.load.engine.Engine r7 = r5.engine
            r7.release(r0)
        L_0x00c4:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.request.SingleRequest.onResourceReady(com.bumptech.glide.load.engine.Resource, com.bumptech.glide.load.DataSource):void");
    }

    /* JADX INFO: finally extract failed */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x00ac A[Catch:{ all -> 0x00bd }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void onResourceReady(com.bumptech.glide.load.engine.Resource<R> r11, R r12, com.bumptech.glide.load.DataSource r13) {
        /*
            r10 = this;
            boolean r6 = r10.isFirstReadyResource()
            com.bumptech.glide.request.SingleRequest$Status r0 = com.bumptech.glide.request.SingleRequest.Status.COMPLETE
            r10.status = r0
            r10.resource = r11
            com.bumptech.glide.GlideContext r11 = r10.glideContext
            int r11 = r11.getLogLevel()
            r0 = 3
            if (r11 > r0) goto L_0x006b
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>()
            java.lang.String r0 = "Finished loading "
            r11.append(r0)
            java.lang.Class r0 = r12.getClass()
            java.lang.String r0 = r0.getSimpleName()
            r11.append(r0)
            java.lang.String r0 = " from "
            r11.append(r0)
            r11.append(r13)
            java.lang.String r0 = " for "
            r11.append(r0)
            java.lang.Object r0 = r10.model
            r11.append(r0)
            java.lang.String r0 = " with size ["
            r11.append(r0)
            int r0 = r10.width
            r11.append(r0)
            java.lang.String r0 = "x"
            r11.append(r0)
            int r0 = r10.height
            r11.append(r0)
            java.lang.String r0 = "] in "
            r11.append(r0)
            long r0 = r10.startTime
            double r0 = com.bumptech.glide.util.LogTime.getElapsedMillis(r0)
            r11.append(r0)
            java.lang.String r0 = " ms"
            r11.append(r0)
            java.lang.String r11 = r11.toString()
            java.lang.String r0 = "Glide"
            android.util.Log.d(r0, r11)
        L_0x006b:
            r11 = 1
            r10.isCallingCallbacks = r11
            r7 = 0
            java.util.List<com.bumptech.glide.request.RequestListener<R>> r0 = r10.requestListeners     // Catch:{ all -> 0x00bd }
            if (r0 == 0) goto L_0x0093
            java.util.List<com.bumptech.glide.request.RequestListener<R>> r0 = r10.requestListeners     // Catch:{ all -> 0x00bd }
            java.util.Iterator r8 = r0.iterator()     // Catch:{ all -> 0x00bd }
            r9 = 0
        L_0x007a:
            boolean r0 = r8.hasNext()     // Catch:{ all -> 0x00bd }
            if (r0 == 0) goto L_0x0094
            java.lang.Object r0 = r8.next()     // Catch:{ all -> 0x00bd }
            com.bumptech.glide.request.RequestListener r0 = (com.bumptech.glide.request.RequestListener) r0     // Catch:{ all -> 0x00bd }
            java.lang.Object r2 = r10.model     // Catch:{ all -> 0x00bd }
            com.bumptech.glide.request.target.Target<R> r3 = r10.target     // Catch:{ all -> 0x00bd }
            r1 = r12
            r4 = r13
            r5 = r6
            boolean r0 = r0.onResourceReady(r1, r2, r3, r4, r5)     // Catch:{ all -> 0x00bd }
            r9 = r9 | r0
            goto L_0x007a
        L_0x0093:
            r9 = 0
        L_0x0094:
            com.bumptech.glide.request.RequestListener<R> r0 = r10.targetListener     // Catch:{ all -> 0x00bd }
            if (r0 == 0) goto L_0x00a8
            com.bumptech.glide.request.RequestListener<R> r0 = r10.targetListener     // Catch:{ all -> 0x00bd }
            java.lang.Object r2 = r10.model     // Catch:{ all -> 0x00bd }
            com.bumptech.glide.request.target.Target<R> r3 = r10.target     // Catch:{ all -> 0x00bd }
            r1 = r12
            r4 = r13
            r5 = r6
            boolean r0 = r0.onResourceReady(r1, r2, r3, r4, r5)     // Catch:{ all -> 0x00bd }
            if (r0 == 0) goto L_0x00a8
            goto L_0x00a9
        L_0x00a8:
            r11 = 0
        L_0x00a9:
            r11 = r11 | r9
            if (r11 != 0) goto L_0x00b7
            com.bumptech.glide.request.transition.TransitionFactory<? super R> r11 = r10.animationFactory     // Catch:{ all -> 0x00bd }
            com.bumptech.glide.request.transition.Transition r11 = r11.build(r13, r6)     // Catch:{ all -> 0x00bd }
            com.bumptech.glide.request.target.Target<R> r13 = r10.target     // Catch:{ all -> 0x00bd }
            r13.onResourceReady(r12, r11)     // Catch:{ all -> 0x00bd }
        L_0x00b7:
            r10.isCallingCallbacks = r7
            r10.notifyLoadSuccess()
            return
        L_0x00bd:
            r11 = move-exception
            r10.isCallingCallbacks = r7
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bumptech.glide.request.SingleRequest.onResourceReady(com.bumptech.glide.load.engine.Resource, java.lang.Object, com.bumptech.glide.load.DataSource):void");
    }

    public void onLoadFailed(GlideException glideException) {
        onLoadFailed(glideException, 5);
    }

    public Object getLock() {
        this.stateVerifier.throwIfRecycled();
        return this.requestLock;
    }

    /* JADX INFO: finally extract failed */
    private void onLoadFailed(GlideException glideException, int i) {
        boolean z;
        this.stateVerifier.throwIfRecycled();
        synchronized (this.requestLock) {
            glideException.setOrigin(this.requestOrigin);
            int logLevel = this.glideContext.getLogLevel();
            if (logLevel <= i) {
                Log.w(GLIDE_TAG, "Load failed for " + this.model + " with size [" + this.width + "x" + this.height + "]", glideException);
                if (logLevel <= 4) {
                    glideException.logRootCauses(GLIDE_TAG);
                }
            }
            this.loadStatus = null;
            this.status = Status.FAILED;
            boolean z2 = true;
            this.isCallingCallbacks = true;
            try {
                if (this.requestListeners != null) {
                    z = false;
                    for (RequestListener<R> onLoadFailed : this.requestListeners) {
                        z |= onLoadFailed.onLoadFailed(glideException, this.model, this.target, isFirstReadyResource());
                    }
                } else {
                    z = false;
                }
                if (this.targetListener == null || !this.targetListener.onLoadFailed(glideException, this.model, this.target, isFirstReadyResource())) {
                    z2 = false;
                }
                if (!z && !z2) {
                    setErrorPlaceholder();
                }
                this.isCallingCallbacks = false;
                notifyLoadFailed();
            } catch (Throwable th) {
                this.isCallingCallbacks = false;
                throw th;
            }
        }
    }

    public boolean isEquivalentTo(Request request) {
        int i;
        int i2;
        Object obj;
        Class<R> cls;
        BaseRequestOptions<?> baseRequestOptions;
        Priority priority2;
        int size;
        int i3;
        int i4;
        Object obj2;
        Class<R> cls2;
        BaseRequestOptions<?> baseRequestOptions2;
        Priority priority3;
        int size2;
        Request request2 = request;
        if (!(request2 instanceof SingleRequest)) {
            return false;
        }
        synchronized (this.requestLock) {
            i = this.overrideWidth;
            i2 = this.overrideHeight;
            obj = this.model;
            cls = this.transcodeClass;
            baseRequestOptions = this.requestOptions;
            priority2 = this.priority;
            size = this.requestListeners != null ? this.requestListeners.size() : 0;
        }
        SingleRequest singleRequest = (SingleRequest) request2;
        synchronized (singleRequest.requestLock) {
            i3 = singleRequest.overrideWidth;
            i4 = singleRequest.overrideHeight;
            obj2 = singleRequest.model;
            cls2 = singleRequest.transcodeClass;
            baseRequestOptions2 = singleRequest.requestOptions;
            priority3 = singleRequest.priority;
            size2 = singleRequest.requestListeners != null ? singleRequest.requestListeners.size() : 0;
        }
        return i == i3 && i2 == i4 && Util.bothModelsNullEquivalentOrEquals(obj, obj2) && cls.equals(cls2) && baseRequestOptions.equals(baseRequestOptions2) && priority2 == priority3 && size == size2;
    }

    private void logV(String str) {
        Log.v(TAG, str + " this: " + this.tag);
    }
}
