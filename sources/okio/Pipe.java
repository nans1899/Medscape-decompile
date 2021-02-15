package okio;

import java.util.concurrent.TimeUnit;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.InlineMarker;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010!\u001a\u00020\"J\u000e\u0010#\u001a\u00020\"2\u0006\u0010\u0017\u001a\u00020\u0010J\r\u0010\u0017\u001a\u00020\u0010H\u0007¢\u0006\u0002\b$J\r\u0010\u001b\u001a\u00020\u001cH\u0007¢\u0006\u0002\b%J&\u0010&\u001a\u00020\"*\u00020\u00102\u0017\u0010'\u001a\u0013\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\"0(¢\u0006\u0002\b)H\bR\u0014\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001c\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0013\u0010\u0017\u001a\u00020\u00108G¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0012R\u001a\u0010\u0018\u001a\u00020\nX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\f\"\u0004\b\u001a\u0010\u000eR\u0013\u0010\u001b\u001a\u00020\u001c8G¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001dR\u001a\u0010\u001e\u001a\u00020\nX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\f\"\u0004\b \u0010\u000e¨\u0006*"}, d2 = {"Lokio/Pipe;", "", "maxBufferSize", "", "(J)V", "buffer", "Lokio/Buffer;", "getBuffer$okio", "()Lokio/Buffer;", "canceled", "", "getCanceled$okio", "()Z", "setCanceled$okio", "(Z)V", "foldedSink", "Lokio/Sink;", "getFoldedSink$okio", "()Lokio/Sink;", "setFoldedSink$okio", "(Lokio/Sink;)V", "getMaxBufferSize$okio", "()J", "sink", "sinkClosed", "getSinkClosed$okio", "setSinkClosed$okio", "source", "Lokio/Source;", "()Lokio/Source;", "sourceClosed", "getSourceClosed$okio", "setSourceClosed$okio", "cancel", "", "fold", "-deprecated_sink", "-deprecated_source", "forward", "block", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "okio"}, k = 1, mv = {1, 4, 0})
/* compiled from: Pipe.kt */
public final class Pipe {
    private final Buffer buffer = new Buffer();
    private boolean canceled;
    private Sink foldedSink;
    private final long maxBufferSize;
    private final Sink sink;
    private boolean sinkClosed;
    private final Source source;
    private boolean sourceClosed;

    public Pipe(long j) {
        this.maxBufferSize = j;
        if (this.maxBufferSize >= 1) {
            this.sink = new Pipe$sink$1(this);
            this.source = new Pipe$source$1(this);
            return;
        }
        throw new IllegalArgumentException(("maxBufferSize < 1: " + this.maxBufferSize).toString());
    }

    public final long getMaxBufferSize$okio() {
        return this.maxBufferSize;
    }

    public final Buffer getBuffer$okio() {
        return this.buffer;
    }

    public final boolean getCanceled$okio() {
        return this.canceled;
    }

    public final void setCanceled$okio(boolean z) {
        this.canceled = z;
    }

    public final boolean getSinkClosed$okio() {
        return this.sinkClosed;
    }

    public final void setSinkClosed$okio(boolean z) {
        this.sinkClosed = z;
    }

    public final boolean getSourceClosed$okio() {
        return this.sourceClosed;
    }

    public final void setSourceClosed$okio(boolean z) {
        this.sourceClosed = z;
    }

    public final Sink getFoldedSink$okio() {
        return this.foldedSink;
    }

    public final void setFoldedSink$okio(Sink sink2) {
        this.foldedSink = sink2;
    }

    public final Sink sink() {
        return this.sink;
    }

    public final Source source() {
        return this.source;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:22:?, code lost:
        r8.write(r3, r3.size());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0049, code lost:
        if (r1 == false) goto L_0x004f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x004b, code lost:
        r8.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x004f, code lost:
        r8.flush();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0053, code lost:
        r8 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0056, code lost:
        monitor-enter(r7.buffer);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:?, code lost:
        r7.sourceClosed = true;
        r1 = r7.buffer;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x005b, code lost:
        if (r1 == null) goto L_0x005d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0064, code lost:
        throw new java.lang.NullPointerException("null cannot be cast to non-null type java.lang.Object");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0065, code lost:
        r1.notifyAll();
        r1 = kotlin.Unit.INSTANCE;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x006d, code lost:
        throw r8;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void fold(okio.Sink r8) throws java.io.IOException {
        /*
            r7 = this;
            java.lang.String r0 = "sink"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r8, r0)
        L_0x0005:
            okio.Buffer r0 = r7.buffer
            monitor-enter(r0)
            okio.Sink r1 = r7.foldedSink     // Catch:{ all -> 0x0093 }
            r2 = 1
            if (r1 != 0) goto L_0x000f
            r1 = 1
            goto L_0x0010
        L_0x000f:
            r1 = 0
        L_0x0010:
            if (r1 == 0) goto L_0x0085
            boolean r1 = r7.canceled     // Catch:{ all -> 0x0093 }
            if (r1 != 0) goto L_0x0079
            okio.Buffer r1 = r7.buffer     // Catch:{ all -> 0x0093 }
            boolean r1 = r1.exhausted()     // Catch:{ all -> 0x0093 }
            if (r1 == 0) goto L_0x0024
            r7.sourceClosed = r2     // Catch:{ all -> 0x0093 }
            r7.foldedSink = r8     // Catch:{ all -> 0x0093 }
            monitor-exit(r0)
            return
        L_0x0024:
            boolean r1 = r7.sinkClosed     // Catch:{ all -> 0x0093 }
            okio.Buffer r3 = new okio.Buffer     // Catch:{ all -> 0x0093 }
            r3.<init>()     // Catch:{ all -> 0x0093 }
            okio.Buffer r4 = r7.buffer     // Catch:{ all -> 0x0093 }
            okio.Buffer r5 = r7.buffer     // Catch:{ all -> 0x0093 }
            long r5 = r5.size()     // Catch:{ all -> 0x0093 }
            r3.write((okio.Buffer) r4, (long) r5)     // Catch:{ all -> 0x0093 }
            okio.Buffer r4 = r7.buffer     // Catch:{ all -> 0x0093 }
            if (r4 == 0) goto L_0x0071
            java.lang.Object r4 = (java.lang.Object) r4     // Catch:{ all -> 0x0093 }
            r4.notifyAll()     // Catch:{ all -> 0x0093 }
            kotlin.Unit r4 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0093 }
            monitor-exit(r0)
            long r4 = r3.size()     // Catch:{ all -> 0x0053 }
            r8.write(r3, r4)     // Catch:{ all -> 0x0053 }
            if (r1 == 0) goto L_0x004f
            r8.close()     // Catch:{ all -> 0x0053 }
            goto L_0x0005
        L_0x004f:
            r8.flush()     // Catch:{ all -> 0x0053 }
            goto L_0x0005
        L_0x0053:
            r8 = move-exception
            okio.Buffer r0 = r7.buffer
            monitor-enter(r0)
            r7.sourceClosed = r2     // Catch:{ all -> 0x006e }
            okio.Buffer r1 = r7.buffer     // Catch:{ all -> 0x006e }
            if (r1 != 0) goto L_0x0065
            java.lang.NullPointerException r8 = new java.lang.NullPointerException     // Catch:{ all -> 0x006e }
            java.lang.String r1 = "null cannot be cast to non-null type java.lang.Object"
            r8.<init>(r1)     // Catch:{ all -> 0x006e }
            throw r8     // Catch:{ all -> 0x006e }
        L_0x0065:
            java.lang.Object r1 = (java.lang.Object) r1     // Catch:{ all -> 0x006e }
            r1.notifyAll()     // Catch:{ all -> 0x006e }
            kotlin.Unit r1 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x006e }
            monitor-exit(r0)
            throw r8
        L_0x006e:
            r8 = move-exception
            monitor-exit(r0)
            throw r8
        L_0x0071:
            java.lang.NullPointerException r8 = new java.lang.NullPointerException     // Catch:{ all -> 0x0093 }
            java.lang.String r1 = "null cannot be cast to non-null type java.lang.Object"
            r8.<init>(r1)     // Catch:{ all -> 0x0093 }
            throw r8     // Catch:{ all -> 0x0093 }
        L_0x0079:
            r7.foldedSink = r8     // Catch:{ all -> 0x0093 }
            java.io.IOException r8 = new java.io.IOException     // Catch:{ all -> 0x0093 }
            java.lang.String r1 = "canceled"
            r8.<init>(r1)     // Catch:{ all -> 0x0093 }
            java.lang.Throwable r8 = (java.lang.Throwable) r8     // Catch:{ all -> 0x0093 }
            throw r8     // Catch:{ all -> 0x0093 }
        L_0x0085:
            java.lang.String r8 = "sink already folded"
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException     // Catch:{ all -> 0x0093 }
            java.lang.String r8 = r8.toString()     // Catch:{ all -> 0x0093 }
            r1.<init>(r8)     // Catch:{ all -> 0x0093 }
            java.lang.Throwable r1 = (java.lang.Throwable) r1     // Catch:{ all -> 0x0093 }
            throw r1     // Catch:{ all -> 0x0093 }
        L_0x0093:
            r8 = move-exception
            monitor-exit(r0)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: okio.Pipe.fold(okio.Sink):void");
    }

    /* access modifiers changed from: private */
    public final void forward(Sink sink2, Function1<? super Sink, Unit> function1) {
        Timeout timeout = sink2.timeout();
        Timeout timeout2 = sink().timeout();
        long timeoutNanos = timeout.timeoutNanos();
        timeout.timeout(Timeout.Companion.minTimeout(timeout2.timeoutNanos(), timeout.timeoutNanos()), TimeUnit.NANOSECONDS);
        if (timeout.hasDeadline()) {
            long deadlineNanoTime = timeout.deadlineNanoTime();
            if (timeout2.hasDeadline()) {
                timeout.deadlineNanoTime(Math.min(timeout.deadlineNanoTime(), timeout2.deadlineNanoTime()));
            }
            try {
                function1.invoke(sink2);
            } finally {
                InlineMarker.finallyStart(1);
                timeout.timeout(timeoutNanos, TimeUnit.NANOSECONDS);
                if (timeout2.hasDeadline()) {
                    timeout.deadlineNanoTime(deadlineNanoTime);
                }
                InlineMarker.finallyEnd(1);
            }
        } else {
            if (timeout2.hasDeadline()) {
                timeout.deadlineNanoTime(timeout2.deadlineNanoTime());
            }
            try {
                function1.invoke(sink2);
            } finally {
                InlineMarker.finallyStart(1);
                timeout.timeout(timeoutNanos, TimeUnit.NANOSECONDS);
                if (timeout2.hasDeadline()) {
                    timeout.clearDeadline();
                }
                InlineMarker.finallyEnd(1);
            }
        }
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "sink", imports = {}))
    /* renamed from: -deprecated_sink  reason: not valid java name */
    public final Sink m1533deprecated_sink() {
        return this.sink;
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "moved to val", replaceWith = @ReplaceWith(expression = "source", imports = {}))
    /* renamed from: -deprecated_source  reason: not valid java name */
    public final Source m1534deprecated_source() {
        return this.source;
    }

    public final void cancel() {
        synchronized (this.buffer) {
            this.canceled = true;
            this.buffer.clear();
            Buffer buffer2 = this.buffer;
            if (buffer2 != null) {
                buffer2.notifyAll();
                Unit unit = Unit.INSTANCE;
            } else {
                throw new NullPointerException("null cannot be cast to non-null type java.lang.Object");
            }
        }
    }
}
