package okhttp3.internal.ws;

import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.common.net.HttpHeaders;
import java.io.Closeable;
import java.io.IOException;
import java.net.ProtocolException;
import java.util.ArrayDeque;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.Call;
import okhttp3.EventListener;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okhttp3.internal.Util;
import okhttp3.internal.concurrent.Task;
import okhttp3.internal.concurrent.TaskQueue;
import okhttp3.internal.concurrent.TaskRunner;
import okhttp3.internal.connection.Exchange;
import okhttp3.internal.connection.RealCall;
import okhttp3.internal.ws.WebSocketReader;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.ByteString;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000¶\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u001c\u0018\u0000 `2\u00020\u00012\u00020\u0002:\u0005_`abcB?\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u000e\u0012\u0006\u0010\u000f\u001a\u00020\f¢\u0006\u0002\u0010\u0010J\u0016\u00102\u001a\u0002032\u0006\u00104\u001a\u00020\f2\u0006\u00105\u001a\u000206J\b\u00107\u001a\u000203H\u0016J\u001f\u00108\u001a\u0002032\u0006\u00109\u001a\u00020:2\b\u0010;\u001a\u0004\u0018\u00010<H\u0000¢\u0006\u0002\b=J\u001a\u0010>\u001a\u00020\u00122\u0006\u0010?\u001a\u00020%2\b\u0010@\u001a\u0004\u0018\u00010\u0018H\u0016J \u0010>\u001a\u00020\u00122\u0006\u0010?\u001a\u00020%2\b\u0010@\u001a\u0004\u0018\u00010\u00182\u0006\u0010A\u001a\u00020\fJ\u000e\u0010B\u001a\u0002032\u0006\u0010C\u001a\u00020DJ\u001c\u0010E\u001a\u0002032\n\u0010F\u001a\u00060Gj\u0002`H2\b\u00109\u001a\u0004\u0018\u00010:J\u0016\u0010I\u001a\u0002032\u0006\u0010\u001e\u001a\u00020\u00182\u0006\u0010*\u001a\u00020+J\u0006\u0010J\u001a\u000203J\u0018\u0010K\u001a\u0002032\u0006\u0010?\u001a\u00020%2\u0006\u0010@\u001a\u00020\u0018H\u0016J\u0010\u0010L\u001a\u0002032\u0006\u0010M\u001a\u00020\u0018H\u0016J\u0010\u0010L\u001a\u0002032\u0006\u0010N\u001a\u00020 H\u0016J\u0010\u0010O\u001a\u0002032\u0006\u0010P\u001a\u00020 H\u0016J\u0010\u0010Q\u001a\u0002032\u0006\u0010P\u001a\u00020 H\u0016J\u000e\u0010R\u001a\u00020\u00122\u0006\u0010P\u001a\u00020 J\u0006\u0010S\u001a\u00020\u0012J\b\u0010!\u001a\u00020\fH\u0016J\u0006\u0010'\u001a\u00020%J\u0006\u0010(\u001a\u00020%J\b\u0010T\u001a\u00020\u0006H\u0016J\b\u0010U\u001a\u000203H\u0002J\u0010\u0010V\u001a\u00020\u00122\u0006\u0010M\u001a\u00020\u0018H\u0016J\u0010\u0010V\u001a\u00020\u00122\u0006\u0010N\u001a\u00020 H\u0016J\u0018\u0010V\u001a\u00020\u00122\u0006\u0010W\u001a\u00020 2\u0006\u0010X\u001a\u00020%H\u0002J\u0006\u0010)\u001a\u00020%J\u0006\u0010Y\u001a\u000203J\r\u0010Z\u001a\u00020\u0012H\u0000¢\u0006\u0002\b[J\r\u0010\\\u001a\u000203H\u0000¢\u0006\u0002\b]J\f\u0010^\u001a\u00020\u0012*\u00020\u000eH\u0002R\u000e\u0010\u0011\u001a\u00020\u0012X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0012X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0012X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0014\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001cX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\fX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001e\u001a\u0004\u0018\u00010\u0018X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020 0\u001cX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\fX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\"\u001a\u0004\u0018\u00010#X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020%X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010&\u001a\u0004\u0018\u00010\u0018X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020%X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020%X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020%X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010*\u001a\u0004\u0018\u00010+X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010,\u001a\u00020-X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010.\u001a\u0004\u0018\u00010/X\u000e¢\u0006\u0002\n\u0000R\u0010\u00100\u001a\u0004\u0018\u000101X\u000e¢\u0006\u0002\n\u0000¨\u0006d"}, d2 = {"Lokhttp3/internal/ws/RealWebSocket;", "Lokhttp3/WebSocket;", "Lokhttp3/internal/ws/WebSocketReader$FrameCallback;", "taskRunner", "Lokhttp3/internal/concurrent/TaskRunner;", "originalRequest", "Lokhttp3/Request;", "listener", "Lokhttp3/WebSocketListener;", "random", "Ljava/util/Random;", "pingIntervalMillis", "", "extensions", "Lokhttp3/internal/ws/WebSocketExtensions;", "minimumDeflateSize", "(Lokhttp3/internal/concurrent/TaskRunner;Lokhttp3/Request;Lokhttp3/WebSocketListener;Ljava/util/Random;JLokhttp3/internal/ws/WebSocketExtensions;J)V", "awaitingPong", "", "call", "Lokhttp3/Call;", "enqueuedClose", "failed", "key", "", "getListener$okhttp", "()Lokhttp3/WebSocketListener;", "messageAndCloseQueue", "Ljava/util/ArrayDeque;", "", "name", "pongQueue", "Lokio/ByteString;", "queueSize", "reader", "Lokhttp3/internal/ws/WebSocketReader;", "receivedCloseCode", "", "receivedCloseReason", "receivedPingCount", "receivedPongCount", "sentPingCount", "streams", "Lokhttp3/internal/ws/RealWebSocket$Streams;", "taskQueue", "Lokhttp3/internal/concurrent/TaskQueue;", "writer", "Lokhttp3/internal/ws/WebSocketWriter;", "writerTask", "Lokhttp3/internal/concurrent/Task;", "awaitTermination", "", "timeout", "timeUnit", "Ljava/util/concurrent/TimeUnit;", "cancel", "checkUpgradeSuccess", "response", "Lokhttp3/Response;", "exchange", "Lokhttp3/internal/connection/Exchange;", "checkUpgradeSuccess$okhttp", "close", "code", "reason", "cancelAfterCloseMillis", "connect", "client", "Lokhttp3/OkHttpClient;", "failWebSocket", "e", "Ljava/lang/Exception;", "Lkotlin/Exception;", "initReaderAndWriter", "loopReader", "onReadClose", "onReadMessage", "text", "bytes", "onReadPing", "payload", "onReadPong", "pong", "processNextFrame", "request", "runWriter", "send", "data", "formatOpcode", "tearDown", "writeOneFrame", "writeOneFrame$okhttp", "writePingFrame", "writePingFrame$okhttp", "isValid", "Close", "Companion", "Message", "Streams", "WriterTask", "okhttp"}, k = 1, mv = {1, 4, 0})
/* compiled from: RealWebSocket.kt */
public final class RealWebSocket implements WebSocket, WebSocketReader.FrameCallback {
    private static final long CANCEL_AFTER_CLOSE_MILLIS = 60000;
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final long DEFAULT_MINIMUM_DEFLATE_SIZE = 1024;
    private static final long MAX_QUEUE_SIZE = 16777216;
    private static final List<Protocol> ONLY_HTTP1 = CollectionsKt.listOf(Protocol.HTTP_1_1);
    private boolean awaitingPong;
    private Call call;
    private boolean enqueuedClose;
    /* access modifiers changed from: private */
    public WebSocketExtensions extensions;
    private boolean failed;
    private final String key;
    private final WebSocketListener listener;
    /* access modifiers changed from: private */
    public final ArrayDeque<Object> messageAndCloseQueue = new ArrayDeque<>();
    private long minimumDeflateSize;
    /* access modifiers changed from: private */
    public String name;
    private final Request originalRequest;
    private final long pingIntervalMillis;
    private final ArrayDeque<ByteString> pongQueue = new ArrayDeque<>();
    private long queueSize;
    private final Random random;
    private WebSocketReader reader;
    private int receivedCloseCode = -1;
    private String receivedCloseReason;
    private int receivedPingCount;
    private int receivedPongCount;
    private int sentPingCount;
    private Streams streams;
    private TaskQueue taskQueue;
    private WebSocketWriter writer;
    private Task writerTask;

    public RealWebSocket(TaskRunner taskRunner, Request request, WebSocketListener webSocketListener, Random random2, long j, WebSocketExtensions webSocketExtensions, long j2) {
        Intrinsics.checkNotNullParameter(taskRunner, "taskRunner");
        Intrinsics.checkNotNullParameter(request, "originalRequest");
        Intrinsics.checkNotNullParameter(webSocketListener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        Intrinsics.checkNotNullParameter(random2, "random");
        this.originalRequest = request;
        this.listener = webSocketListener;
        this.random = random2;
        this.pingIntervalMillis = j;
        this.extensions = webSocketExtensions;
        this.minimumDeflateSize = j2;
        this.taskQueue = taskRunner.newQueue();
        if (Intrinsics.areEqual((Object) "GET", (Object) this.originalRequest.method())) {
            ByteString.Companion companion = ByteString.Companion;
            byte[] bArr = new byte[16];
            this.random.nextBytes(bArr);
            Unit unit = Unit.INSTANCE;
            this.key = ByteString.Companion.of$default(companion, bArr, 0, 0, 3, (Object) null).base64();
            return;
        }
        throw new IllegalArgumentException(("Request must be GET: " + this.originalRequest.method()).toString());
    }

    public final WebSocketListener getListener$okhttp() {
        return this.listener;
    }

    public Request request() {
        return this.originalRequest;
    }

    public synchronized long queueSize() {
        return this.queueSize;
    }

    public void cancel() {
        Call call2 = this.call;
        Intrinsics.checkNotNull(call2);
        call2.cancel();
    }

    public final void connect(OkHttpClient okHttpClient) {
        Intrinsics.checkNotNullParameter(okHttpClient, "client");
        if (this.originalRequest.header("Sec-WebSocket-Extensions") != null) {
            failWebSocket(new ProtocolException("Request header not permitted: 'Sec-WebSocket-Extensions'"), (Response) null);
            return;
        }
        OkHttpClient build = okHttpClient.newBuilder().eventListener(EventListener.NONE).protocols(ONLY_HTTP1).build();
        Request build2 = this.originalRequest.newBuilder().header(HttpHeaders.UPGRADE, "websocket").header(HttpHeaders.CONNECTION, HttpHeaders.UPGRADE).header("Sec-WebSocket-Key", this.key).header("Sec-WebSocket-Version", "13").header("Sec-WebSocket-Extensions", "permessage-deflate").build();
        Call realCall = new RealCall(build, build2, true);
        this.call = realCall;
        Intrinsics.checkNotNull(realCall);
        realCall.enqueue(new RealWebSocket$connect$1(this, build2));
    }

    /* access modifiers changed from: private */
    public final boolean isValid(WebSocketExtensions webSocketExtensions) {
        if (webSocketExtensions.unknownValues || webSocketExtensions.clientMaxWindowBits != null) {
            return false;
        }
        if (webSocketExtensions.serverMaxWindowBits == null) {
            return true;
        }
        int intValue = webSocketExtensions.serverMaxWindowBits.intValue();
        if (8 > intValue || 15 < intValue) {
            return false;
        }
        return true;
    }

    public final void checkUpgradeSuccess$okhttp(Response response, Exchange exchange) throws IOException {
        Intrinsics.checkNotNullParameter(response, "response");
        if (response.code() == 101) {
            String header$default = Response.header$default(response, HttpHeaders.CONNECTION, (String) null, 2, (Object) null);
            if (StringsKt.equals(HttpHeaders.UPGRADE, header$default, true)) {
                String header$default2 = Response.header$default(response, HttpHeaders.UPGRADE, (String) null, 2, (Object) null);
                if (StringsKt.equals("websocket", header$default2, true)) {
                    String header$default3 = Response.header$default(response, "Sec-WebSocket-Accept", (String) null, 2, (Object) null);
                    ByteString.Companion companion = ByteString.Companion;
                    String base64 = companion.encodeUtf8(this.key + WebSocketProtocol.ACCEPT_MAGIC).sha1().base64();
                    if (!Intrinsics.areEqual((Object) base64, (Object) header$default3)) {
                        throw new ProtocolException("Expected 'Sec-WebSocket-Accept' header value '" + base64 + "' but was '" + header$default3 + '\'');
                    } else if (exchange == null) {
                        throw new ProtocolException("Web Socket exchange missing: bad interceptor?");
                    }
                } else {
                    throw new ProtocolException("Expected 'Upgrade' header value 'websocket' but was '" + header$default2 + '\'');
                }
            } else {
                throw new ProtocolException("Expected 'Connection' header value 'Upgrade' but was '" + header$default + '\'');
            }
        } else {
            throw new ProtocolException("Expected HTTP 101 response but was '" + response.code() + ' ' + response.message() + '\'');
        }
    }

    public final void initReaderAndWriter(String str, Streams streams2) throws IOException {
        String str2 = str;
        Streams streams3 = streams2;
        Intrinsics.checkNotNullParameter(str2, "name");
        Intrinsics.checkNotNullParameter(streams3, "streams");
        WebSocketExtensions webSocketExtensions = this.extensions;
        Intrinsics.checkNotNull(webSocketExtensions);
        synchronized (this) {
            this.name = str2;
            this.streams = streams3;
            this.writer = new WebSocketWriter(streams2.getClient(), streams2.getSink(), this.random, webSocketExtensions.perMessageDeflate, webSocketExtensions.noContextTakeover(streams2.getClient()), this.minimumDeflateSize);
            this.writerTask = new WriterTask();
            if (this.pingIntervalMillis != 0) {
                long nanos = TimeUnit.MILLISECONDS.toNanos(this.pingIntervalMillis);
                String str3 = str2 + " ping";
                this.taskQueue.schedule(new RealWebSocket$initReaderAndWriter$$inlined$synchronized$lambda$1(str3, str3, nanos, this, str, streams2, webSocketExtensions), nanos);
            }
            if (!this.messageAndCloseQueue.isEmpty()) {
                runWriter();
            }
            Unit unit = Unit.INSTANCE;
        }
        this.reader = new WebSocketReader(streams2.getClient(), streams2.getSource(), this, webSocketExtensions.perMessageDeflate, webSocketExtensions.noContextTakeover(!streams2.getClient()));
    }

    public final void loopReader() throws IOException {
        while (this.receivedCloseCode == -1) {
            WebSocketReader webSocketReader = this.reader;
            Intrinsics.checkNotNull(webSocketReader);
            webSocketReader.processNextFrame();
        }
    }

    public final boolean processNextFrame() throws IOException {
        try {
            WebSocketReader webSocketReader = this.reader;
            Intrinsics.checkNotNull(webSocketReader);
            webSocketReader.processNextFrame();
            if (this.receivedCloseCode == -1) {
                return true;
            }
            return false;
        } catch (Exception e) {
            failWebSocket(e, (Response) null);
            return false;
        }
    }

    public final void awaitTermination(long j, TimeUnit timeUnit) throws InterruptedException {
        Intrinsics.checkNotNullParameter(timeUnit, "timeUnit");
        this.taskQueue.idleLatch().await(j, timeUnit);
    }

    public final void tearDown() throws InterruptedException {
        this.taskQueue.shutdown();
        this.taskQueue.idleLatch().await(10, TimeUnit.SECONDS);
    }

    public final synchronized int sentPingCount() {
        return this.sentPingCount;
    }

    public final synchronized int receivedPingCount() {
        return this.receivedPingCount;
    }

    public final synchronized int receivedPongCount() {
        return this.receivedPongCount;
    }

    public void onReadMessage(String str) throws IOException {
        Intrinsics.checkNotNullParameter(str, "text");
        this.listener.onMessage((WebSocket) this, str);
    }

    public void onReadMessage(ByteString byteString) throws IOException {
        Intrinsics.checkNotNullParameter(byteString, "bytes");
        this.listener.onMessage((WebSocket) this, byteString);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0028, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void onReadPing(okio.ByteString r2) {
        /*
            r1 = this;
            monitor-enter(r1)
            java.lang.String r0 = "payload"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r0)     // Catch:{ all -> 0x0029 }
            boolean r0 = r1.failed     // Catch:{ all -> 0x0029 }
            if (r0 != 0) goto L_0x0027
            boolean r0 = r1.enqueuedClose     // Catch:{ all -> 0x0029 }
            if (r0 == 0) goto L_0x0017
            java.util.ArrayDeque<java.lang.Object> r0 = r1.messageAndCloseQueue     // Catch:{ all -> 0x0029 }
            boolean r0 = r0.isEmpty()     // Catch:{ all -> 0x0029 }
            if (r0 == 0) goto L_0x0017
            goto L_0x0027
        L_0x0017:
            java.util.ArrayDeque<okio.ByteString> r0 = r1.pongQueue     // Catch:{ all -> 0x0029 }
            r0.add(r2)     // Catch:{ all -> 0x0029 }
            r1.runWriter()     // Catch:{ all -> 0x0029 }
            int r2 = r1.receivedPingCount     // Catch:{ all -> 0x0029 }
            int r2 = r2 + 1
            r1.receivedPingCount = r2     // Catch:{ all -> 0x0029 }
            monitor-exit(r1)
            return
        L_0x0027:
            monitor-exit(r1)
            return
        L_0x0029:
            r2 = move-exception
            monitor-exit(r1)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.ws.RealWebSocket.onReadPing(okio.ByteString):void");
    }

    public synchronized void onReadPong(ByteString byteString) {
        Intrinsics.checkNotNullParameter(byteString, "payload");
        this.receivedPongCount++;
        this.awaitingPong = false;
    }

    public void onReadClose(int i, String str) {
        Intrinsics.checkNotNullParameter(str, "reason");
        boolean z = true;
        if (i != -1) {
            Streams streams2 = null;
            WebSocketReader webSocketReader = null;
            WebSocketWriter webSocketWriter = null;
            synchronized (this) {
                if (this.receivedCloseCode != -1) {
                    z = false;
                }
                if (z) {
                    this.receivedCloseCode = i;
                    this.receivedCloseReason = str;
                    if (this.enqueuedClose && this.messageAndCloseQueue.isEmpty()) {
                        streams2 = this.streams;
                        this.streams = null;
                        webSocketReader = this.reader;
                        this.reader = null;
                        webSocketWriter = this.writer;
                        this.writer = null;
                        this.taskQueue.shutdown();
                    }
                    Unit unit = Unit.INSTANCE;
                } else {
                    throw new IllegalStateException("already closed".toString());
                }
            }
            try {
                this.listener.onClosing(this, i, str);
                if (streams2 != null) {
                    this.listener.onClosed(this, i, str);
                }
            } finally {
                if (streams2 != null) {
                    Util.closeQuietly((Closeable) streams2);
                }
                if (webSocketReader != null) {
                    Util.closeQuietly((Closeable) webSocketReader);
                }
                if (webSocketWriter != null) {
                    Util.closeQuietly((Closeable) webSocketWriter);
                }
            }
        } else {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
    }

    public boolean send(String str) {
        Intrinsics.checkNotNullParameter(str, "text");
        return send(ByteString.Companion.encodeUtf8(str), 1);
    }

    public boolean send(ByteString byteString) {
        Intrinsics.checkNotNullParameter(byteString, "bytes");
        return send(byteString, 2);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x003d, code lost:
        return false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final synchronized boolean send(okio.ByteString r7, int r8) {
        /*
            r6 = this;
            monitor-enter(r6)
            boolean r0 = r6.failed     // Catch:{ all -> 0x003e }
            r1 = 0
            if (r0 != 0) goto L_0x003c
            boolean r0 = r6.enqueuedClose     // Catch:{ all -> 0x003e }
            if (r0 == 0) goto L_0x000b
            goto L_0x003c
        L_0x000b:
            long r2 = r6.queueSize     // Catch:{ all -> 0x003e }
            int r0 = r7.size()     // Catch:{ all -> 0x003e }
            long r4 = (long) r0     // Catch:{ all -> 0x003e }
            long r2 = r2 + r4
            r4 = 16777216(0x1000000, double:8.289046E-317)
            int r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r0 <= 0) goto L_0x0022
            r7 = 1001(0x3e9, float:1.403E-42)
            r8 = 0
            r6.close(r7, r8)     // Catch:{ all -> 0x003e }
            monitor-exit(r6)
            return r1
        L_0x0022:
            long r0 = r6.queueSize     // Catch:{ all -> 0x003e }
            int r2 = r7.size()     // Catch:{ all -> 0x003e }
            long r2 = (long) r2     // Catch:{ all -> 0x003e }
            long r0 = r0 + r2
            r6.queueSize = r0     // Catch:{ all -> 0x003e }
            java.util.ArrayDeque<java.lang.Object> r0 = r6.messageAndCloseQueue     // Catch:{ all -> 0x003e }
            okhttp3.internal.ws.RealWebSocket$Message r1 = new okhttp3.internal.ws.RealWebSocket$Message     // Catch:{ all -> 0x003e }
            r1.<init>(r8, r7)     // Catch:{ all -> 0x003e }
            r0.add(r1)     // Catch:{ all -> 0x003e }
            r6.runWriter()     // Catch:{ all -> 0x003e }
            r7 = 1
            monitor-exit(r6)
            return r7
        L_0x003c:
            monitor-exit(r6)
            return r1
        L_0x003e:
            r7 = move-exception
            monitor-exit(r6)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.ws.RealWebSocket.send(okio.ByteString, int):boolean");
    }

    public final synchronized boolean pong(ByteString byteString) {
        Intrinsics.checkNotNullParameter(byteString, "payload");
        if (!this.failed) {
            if (!this.enqueuedClose || !this.messageAndCloseQueue.isEmpty()) {
                this.pongQueue.add(byteString);
                runWriter();
                return true;
            }
        }
        return false;
    }

    public boolean close(int i, String str) {
        return close(i, str, 60000);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:0x005c, code lost:
        return false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized boolean close(int r9, java.lang.String r10, long r11) {
        /*
            r8 = this;
            monitor-enter(r8)
            okhttp3.internal.ws.WebSocketProtocol r0 = okhttp3.internal.ws.WebSocketProtocol.INSTANCE     // Catch:{ all -> 0x005d }
            r0.validateCloseCode(r9)     // Catch:{ all -> 0x005d }
            r0 = 0
            okio.ByteString r0 = (okio.ByteString) r0     // Catch:{ all -> 0x005d }
            r1 = 0
            r2 = 1
            if (r10 == 0) goto L_0x0041
            okio.ByteString$Companion r0 = okio.ByteString.Companion     // Catch:{ all -> 0x005d }
            okio.ByteString r0 = r0.encodeUtf8(r10)     // Catch:{ all -> 0x005d }
            int r3 = r0.size()     // Catch:{ all -> 0x005d }
            long r3 = (long) r3     // Catch:{ all -> 0x005d }
            r5 = 123(0x7b, double:6.1E-322)
            int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r7 > 0) goto L_0x0020
            r3 = 1
            goto L_0x0021
        L_0x0020:
            r3 = 0
        L_0x0021:
            if (r3 == 0) goto L_0x0024
            goto L_0x0041
        L_0x0024:
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ all -> 0x005d }
            r9.<init>()     // Catch:{ all -> 0x005d }
            java.lang.String r11 = "reason.size() > 123: "
            r9.append(r11)     // Catch:{ all -> 0x005d }
            r9.append(r10)     // Catch:{ all -> 0x005d }
            java.lang.String r9 = r9.toString()     // Catch:{ all -> 0x005d }
            java.lang.IllegalArgumentException r10 = new java.lang.IllegalArgumentException     // Catch:{ all -> 0x005d }
            java.lang.String r9 = r9.toString()     // Catch:{ all -> 0x005d }
            r10.<init>(r9)     // Catch:{ all -> 0x005d }
            java.lang.Throwable r10 = (java.lang.Throwable) r10     // Catch:{ all -> 0x005d }
            throw r10     // Catch:{ all -> 0x005d }
        L_0x0041:
            boolean r10 = r8.failed     // Catch:{ all -> 0x005d }
            if (r10 != 0) goto L_0x005b
            boolean r10 = r8.enqueuedClose     // Catch:{ all -> 0x005d }
            if (r10 == 0) goto L_0x004a
            goto L_0x005b
        L_0x004a:
            r8.enqueuedClose = r2     // Catch:{ all -> 0x005d }
            java.util.ArrayDeque<java.lang.Object> r10 = r8.messageAndCloseQueue     // Catch:{ all -> 0x005d }
            okhttp3.internal.ws.RealWebSocket$Close r1 = new okhttp3.internal.ws.RealWebSocket$Close     // Catch:{ all -> 0x005d }
            r1.<init>(r9, r0, r11)     // Catch:{ all -> 0x005d }
            r10.add(r1)     // Catch:{ all -> 0x005d }
            r8.runWriter()     // Catch:{ all -> 0x005d }
            monitor-exit(r8)
            return r2
        L_0x005b:
            monitor-exit(r8)
            return r1
        L_0x005d:
            r9 = move-exception
            monitor-exit(r8)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.ws.RealWebSocket.close(int, java.lang.String, long):boolean");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0112, code lost:
        r3 = r16;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0114, code lost:
        if (r3 == null) goto L_0x0121;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:?, code lost:
        kotlin.jvm.internal.Intrinsics.checkNotNull(r21);
        r21.writePong(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x011e, code lost:
        r1 = r24;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0121, code lost:
        r1 = r21;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0127, code lost:
        if ((r0.element instanceof okhttp3.internal.ws.RealWebSocket.Message) == false) goto L_0x015b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0129, code lost:
        r0 = r0.element;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x012b, code lost:
        if (r0 == null) goto L_0x0153;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x012d, code lost:
        r0 = (okhttp3.internal.ws.RealWebSocket.Message) r0;
        kotlin.jvm.internal.Intrinsics.checkNotNull(r1);
        r1.writeMessageFrame(r0.getFormatOpcode(), r0.getData());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x013d, code lost:
        monitor-enter(r27);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:?, code lost:
        r15.queueSize -= (long) r0.getData().size();
        r0 = kotlin.Unit.INSTANCE;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:?, code lost:
        monitor-exit(r27);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x015a, code lost:
        throw new java.lang.NullPointerException("null cannot be cast to non-null type okhttp3.internal.ws.RealWebSocket.Message");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x015f, code lost:
        if ((r0.element instanceof okhttp3.internal.ws.RealWebSocket.Close) == false) goto L_0x01cd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x0161, code lost:
        r0 = r0.element;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x0163, code lost:
        if (r0 == null) goto L_0x01bf;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x0165, code lost:
        r0 = (okhttp3.internal.ws.RealWebSocket.Close) r0;
        kotlin.jvm.internal.Intrinsics.checkNotNull(r1);
        r1.writeClose(r0.getCode(), r0.getReason());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x0175, code lost:
        r1 = r24;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x017b, code lost:
        if (((okhttp3.internal.ws.RealWebSocket.Streams) r1.element) == null) goto L_0x0192;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x017d, code lost:
        r3 = r26.element;
        r4 = (java.lang.String) r25.element;
        kotlin.jvm.internal.Intrinsics.checkNotNull(r4);
        r15.listener.onClosed(r15, r3, r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x0192, code lost:
        r1 = (okhttp3.internal.ws.RealWebSocket.Streams) r1.element;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x0197, code lost:
        if (r1 == null) goto L_0x019e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x0199, code lost:
        okhttp3.internal.Util.closeQuietly((java.io.Closeable) r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x019e, code lost:
        r1 = (okhttp3.internal.ws.WebSocketReader) r23.element;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x01a4, code lost:
        if (r1 == null) goto L_0x01ab;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x01a6, code lost:
        okhttp3.internal.Util.closeQuietly((java.io.Closeable) r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x01ab, code lost:
        r1 = (okhttp3.internal.ws.WebSocketWriter) r22.element;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x01b1, code lost:
        if (r1 == null) goto L_0x01b8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x01b3, code lost:
        okhttp3.internal.Util.closeQuietly((java.io.Closeable) r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x01b8, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x01b9, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x01ba, code lost:
        r3 = r22;
        r2 = r23;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x01bf, code lost:
        r3 = r22;
        r2 = r23;
        r1 = r24;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:0x01cc, code lost:
        throw new java.lang.NullPointerException("null cannot be cast to non-null type okhttp3.internal.ws.RealWebSocket.Close");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:0x01cd, code lost:
        r3 = r22;
        r2 = r23;
        r1 = r24;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:0x01da, code lost:
        throw new java.lang.AssertionError();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x01db, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:0x01dd, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:80:0x01de, code lost:
        r3 = r22;
        r2 = r23;
        r1 = r24;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x01e4, code lost:
        r1 = (okhttp3.internal.ws.RealWebSocket.Streams) r1.element;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x01e8, code lost:
        if (r1 != null) goto L_0x01ea;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:0x01ea, code lost:
        okhttp3.internal.Util.closeQuietly((java.io.Closeable) r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:84:0x01ef, code lost:
        r1 = (okhttp3.internal.ws.WebSocketReader) r2.element;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:85:0x01f3, code lost:
        if (r1 != null) goto L_0x01f5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:0x01f5, code lost:
        okhttp3.internal.Util.closeQuietly((java.io.Closeable) r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:87:0x01fa, code lost:
        r1 = (okhttp3.internal.ws.WebSocketWriter) r3.element;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:88:0x01fe, code lost:
        if (r1 != null) goto L_0x0200;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:89:0x0200, code lost:
        okhttp3.internal.Util.closeQuietly((java.io.Closeable) r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:90:0x0205, code lost:
        throw r0;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Removed duplicated region for block: B:83:0x01ea  */
    /* JADX WARNING: Removed duplicated region for block: B:86:0x01f5  */
    /* JADX WARNING: Removed duplicated region for block: B:89:0x0200  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean writeOneFrame$okhttp() throws java.io.IOException {
        /*
            r27 = this;
            r15 = r27
            kotlin.jvm.internal.Ref$ObjectRef r0 = new kotlin.jvm.internal.Ref$ObjectRef
            r0.<init>()
            r1 = 0
            r0.element = r1
            kotlin.jvm.internal.Ref$IntRef r14 = new kotlin.jvm.internal.Ref$IntRef
            r14.<init>()
            r2 = -1
            r14.element = r2
            kotlin.jvm.internal.Ref$ObjectRef r13 = new kotlin.jvm.internal.Ref$ObjectRef
            r13.<init>()
            r3 = r1
            java.lang.String r3 = (java.lang.String) r3
            r13.element = r3
            kotlin.jvm.internal.Ref$ObjectRef r12 = new kotlin.jvm.internal.Ref$ObjectRef
            r12.<init>()
            r3 = r1
            okhttp3.internal.ws.RealWebSocket$Streams r3 = (okhttp3.internal.ws.RealWebSocket.Streams) r3
            r12.element = r3
            kotlin.jvm.internal.Ref$ObjectRef r11 = new kotlin.jvm.internal.Ref$ObjectRef
            r11.<init>()
            r3 = r1
            okhttp3.internal.ws.WebSocketReader r3 = (okhttp3.internal.ws.WebSocketReader) r3
            r11.element = r3
            kotlin.jvm.internal.Ref$ObjectRef r10 = new kotlin.jvm.internal.Ref$ObjectRef
            r10.<init>()
            r3 = r1
            okhttp3.internal.ws.WebSocketWriter r3 = (okhttp3.internal.ws.WebSocketWriter) r3
            r10.element = r3
            monitor-enter(r27)
            boolean r3 = r15.failed     // Catch:{ all -> 0x0206 }
            r4 = 0
            if (r3 == 0) goto L_0x0042
            monitor-exit(r27)
            return r4
        L_0x0042:
            okhttp3.internal.ws.WebSocketWriter r9 = r15.writer     // Catch:{ all -> 0x0206 }
            java.util.ArrayDeque<okio.ByteString> r3 = r15.pongQueue     // Catch:{ all -> 0x0206 }
            java.lang.Object r3 = r3.poll()     // Catch:{ all -> 0x0206 }
            r8 = r3
            okio.ByteString r8 = (okio.ByteString) r8     // Catch:{ all -> 0x0206 }
            if (r8 != 0) goto L_0x0101
            java.util.ArrayDeque<java.lang.Object> r3 = r15.messageAndCloseQueue     // Catch:{ all -> 0x0206 }
            java.lang.Object r3 = r3.poll()     // Catch:{ all -> 0x0206 }
            r0.element = r3     // Catch:{ all -> 0x0206 }
            T r3 = r0.element     // Catch:{ all -> 0x0206 }
            boolean r3 = r3 instanceof okhttp3.internal.ws.RealWebSocket.Close     // Catch:{ all -> 0x0206 }
            if (r3 == 0) goto L_0x00ed
            int r3 = r15.receivedCloseCode     // Catch:{ all -> 0x0206 }
            r14.element = r3     // Catch:{ all -> 0x0206 }
            java.lang.String r3 = r15.receivedCloseReason     // Catch:{ all -> 0x0206 }
            r13.element = r3     // Catch:{ all -> 0x0206 }
            int r3 = r14.element     // Catch:{ all -> 0x0206 }
            if (r3 == r2) goto L_0x008a
            okhttp3.internal.ws.RealWebSocket$Streams r2 = r15.streams     // Catch:{ all -> 0x0206 }
            r12.element = r2     // Catch:{ all -> 0x0206 }
            r2 = r1
            okhttp3.internal.ws.RealWebSocket$Streams r2 = (okhttp3.internal.ws.RealWebSocket.Streams) r2     // Catch:{ all -> 0x0206 }
            r15.streams = r2     // Catch:{ all -> 0x0206 }
            okhttp3.internal.ws.WebSocketReader r2 = r15.reader     // Catch:{ all -> 0x0206 }
            r11.element = r2     // Catch:{ all -> 0x0206 }
            r2 = r1
            okhttp3.internal.ws.WebSocketReader r2 = (okhttp3.internal.ws.WebSocketReader) r2     // Catch:{ all -> 0x0206 }
            r15.reader = r2     // Catch:{ all -> 0x0206 }
            okhttp3.internal.ws.WebSocketWriter r2 = r15.writer     // Catch:{ all -> 0x0206 }
            r10.element = r2     // Catch:{ all -> 0x0206 }
            okhttp3.internal.ws.WebSocketWriter r1 = (okhttp3.internal.ws.WebSocketWriter) r1     // Catch:{ all -> 0x0206 }
            r15.writer = r1     // Catch:{ all -> 0x0206 }
            okhttp3.internal.concurrent.TaskQueue r1 = r15.taskQueue     // Catch:{ all -> 0x0206 }
            r1.shutdown()     // Catch:{ all -> 0x0206 }
            goto L_0x0101
        L_0x008a:
            T r1 = r0.element     // Catch:{ all -> 0x0206 }
            if (r1 == 0) goto L_0x00e5
            okhttp3.internal.ws.RealWebSocket$Close r1 = (okhttp3.internal.ws.RealWebSocket.Close) r1     // Catch:{ all -> 0x0206 }
            long r1 = r1.getCancelAfterCloseMillis()     // Catch:{ all -> 0x0206 }
            okhttp3.internal.concurrent.TaskQueue r7 = r15.taskQueue     // Catch:{ all -> 0x0206 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x0206 }
            r3.<init>()     // Catch:{ all -> 0x0206 }
            java.lang.String r4 = r15.name     // Catch:{ all -> 0x0206 }
            r3.append(r4)     // Catch:{ all -> 0x0206 }
            java.lang.String r4 = " cancel"
            r3.append(r4)     // Catch:{ all -> 0x0206 }
            java.lang.String r4 = r3.toString()     // Catch:{ all -> 0x0206 }
            java.util.concurrent.TimeUnit r3 = java.util.concurrent.TimeUnit.MILLISECONDS     // Catch:{ all -> 0x0206 }
            long r5 = r3.toNanos(r1)     // Catch:{ all -> 0x0206 }
            r16 = 1
            okhttp3.internal.ws.RealWebSocket$writeOneFrame$$inlined$synchronized$lambda$1 r17 = new okhttp3.internal.ws.RealWebSocket$writeOneFrame$$inlined$synchronized$lambda$1     // Catch:{ all -> 0x0206 }
            r1 = r17
            r2 = r4
            r3 = r16
            r18 = r5
            r5 = r16
            r6 = r27
            r20 = r7
            r7 = r9
            r16 = r8
            r21 = r9
            r9 = r0
            r22 = r10
            r10 = r14
            r23 = r11
            r11 = r13
            r24 = r12
            r25 = r13
            r13 = r23
            r26 = r14
            r14 = r22
            r1.<init>(r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14)     // Catch:{ all -> 0x0206 }
            r1 = r17
            okhttp3.internal.concurrent.Task r1 = (okhttp3.internal.concurrent.Task) r1     // Catch:{ all -> 0x0206 }
            r3 = r18
            r2 = r20
            r2.schedule(r1, r3)     // Catch:{ all -> 0x0206 }
            goto L_0x010f
        L_0x00e5:
            java.lang.NullPointerException r0 = new java.lang.NullPointerException     // Catch:{ all -> 0x0206 }
            java.lang.String r1 = "null cannot be cast to non-null type okhttp3.internal.ws.RealWebSocket.Close"
            r0.<init>(r1)     // Catch:{ all -> 0x0206 }
            throw r0     // Catch:{ all -> 0x0206 }
        L_0x00ed:
            r16 = r8
            r21 = r9
            r22 = r10
            r23 = r11
            r24 = r12
            r25 = r13
            r26 = r14
            T r1 = r0.element     // Catch:{ all -> 0x0206 }
            if (r1 != 0) goto L_0x010f
            monitor-exit(r27)
            return r4
        L_0x0101:
            r16 = r8
            r21 = r9
            r22 = r10
            r23 = r11
            r24 = r12
            r25 = r13
            r26 = r14
        L_0x010f:
            kotlin.Unit r1 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0206 }
            monitor-exit(r27)
            r3 = r16
            if (r3 == 0) goto L_0x0121
            kotlin.jvm.internal.Intrinsics.checkNotNull(r21)     // Catch:{ all -> 0x01dd }
            r1 = r21
            r1.writePong(r3)     // Catch:{ all -> 0x01dd }
        L_0x011e:
            r1 = r24
            goto L_0x0192
        L_0x0121:
            r1 = r21
            T r2 = r0.element     // Catch:{ all -> 0x01dd }
            boolean r2 = r2 instanceof okhttp3.internal.ws.RealWebSocket.Message     // Catch:{ all -> 0x01dd }
            if (r2 == 0) goto L_0x015b
            T r0 = r0.element     // Catch:{ all -> 0x01dd }
            if (r0 == 0) goto L_0x0153
            okhttp3.internal.ws.RealWebSocket$Message r0 = (okhttp3.internal.ws.RealWebSocket.Message) r0     // Catch:{ all -> 0x01dd }
            kotlin.jvm.internal.Intrinsics.checkNotNull(r1)     // Catch:{ all -> 0x01dd }
            int r2 = r0.getFormatOpcode()     // Catch:{ all -> 0x01dd }
            okio.ByteString r3 = r0.getData()     // Catch:{ all -> 0x01dd }
            r1.writeMessageFrame(r2, r3)     // Catch:{ all -> 0x01dd }
            monitor-enter(r27)     // Catch:{ all -> 0x01dd }
            long r1 = r15.queueSize     // Catch:{ all -> 0x0150 }
            okio.ByteString r0 = r0.getData()     // Catch:{ all -> 0x0150 }
            int r0 = r0.size()     // Catch:{ all -> 0x0150 }
            long r3 = (long) r0     // Catch:{ all -> 0x0150 }
            long r1 = r1 - r3
            r15.queueSize = r1     // Catch:{ all -> 0x0150 }
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0150 }
            monitor-exit(r27)     // Catch:{ all -> 0x01dd }
            goto L_0x011e
        L_0x0150:
            r0 = move-exception
            monitor-exit(r27)     // Catch:{ all -> 0x01dd }
            throw r0     // Catch:{ all -> 0x01dd }
        L_0x0153:
            java.lang.NullPointerException r0 = new java.lang.NullPointerException     // Catch:{ all -> 0x01dd }
            java.lang.String r1 = "null cannot be cast to non-null type okhttp3.internal.ws.RealWebSocket.Message"
            r0.<init>(r1)     // Catch:{ all -> 0x01dd }
            throw r0     // Catch:{ all -> 0x01dd }
        L_0x015b:
            T r2 = r0.element     // Catch:{ all -> 0x01dd }
            boolean r2 = r2 instanceof okhttp3.internal.ws.RealWebSocket.Close     // Catch:{ all -> 0x01dd }
            if (r2 == 0) goto L_0x01cd
            T r0 = r0.element     // Catch:{ all -> 0x01dd }
            if (r0 == 0) goto L_0x01bf
            okhttp3.internal.ws.RealWebSocket$Close r0 = (okhttp3.internal.ws.RealWebSocket.Close) r0     // Catch:{ all -> 0x01dd }
            kotlin.jvm.internal.Intrinsics.checkNotNull(r1)     // Catch:{ all -> 0x01dd }
            int r2 = r0.getCode()     // Catch:{ all -> 0x01dd }
            okio.ByteString r0 = r0.getReason()     // Catch:{ all -> 0x01dd }
            r1.writeClose(r2, r0)     // Catch:{ all -> 0x01dd }
            r1 = r24
            T r0 = r1.element     // Catch:{ all -> 0x01b9 }
            okhttp3.internal.ws.RealWebSocket$Streams r0 = (okhttp3.internal.ws.RealWebSocket.Streams) r0     // Catch:{ all -> 0x01b9 }
            if (r0 == 0) goto L_0x0192
            okhttp3.WebSocketListener r0 = r15.listener     // Catch:{ all -> 0x01b9 }
            r2 = r15
            okhttp3.WebSocket r2 = (okhttp3.WebSocket) r2     // Catch:{ all -> 0x01b9 }
            r3 = r26
            int r3 = r3.element     // Catch:{ all -> 0x01b9 }
            r4 = r25
            T r4 = r4.element     // Catch:{ all -> 0x01b9 }
            java.lang.String r4 = (java.lang.String) r4     // Catch:{ all -> 0x01b9 }
            kotlin.jvm.internal.Intrinsics.checkNotNull(r4)     // Catch:{ all -> 0x01b9 }
            r0.onClosed(r2, r3, r4)     // Catch:{ all -> 0x01b9 }
        L_0x0192:
            r0 = 1
            T r1 = r1.element
            okhttp3.internal.ws.RealWebSocket$Streams r1 = (okhttp3.internal.ws.RealWebSocket.Streams) r1
            if (r1 == 0) goto L_0x019e
            java.io.Closeable r1 = (java.io.Closeable) r1
            okhttp3.internal.Util.closeQuietly((java.io.Closeable) r1)
        L_0x019e:
            r2 = r23
            T r1 = r2.element
            okhttp3.internal.ws.WebSocketReader r1 = (okhttp3.internal.ws.WebSocketReader) r1
            if (r1 == 0) goto L_0x01ab
            java.io.Closeable r1 = (java.io.Closeable) r1
            okhttp3.internal.Util.closeQuietly((java.io.Closeable) r1)
        L_0x01ab:
            r3 = r22
            T r1 = r3.element
            okhttp3.internal.ws.WebSocketWriter r1 = (okhttp3.internal.ws.WebSocketWriter) r1
            if (r1 == 0) goto L_0x01b8
            java.io.Closeable r1 = (java.io.Closeable) r1
            okhttp3.internal.Util.closeQuietly((java.io.Closeable) r1)
        L_0x01b8:
            return r0
        L_0x01b9:
            r0 = move-exception
            r3 = r22
            r2 = r23
            goto L_0x01e4
        L_0x01bf:
            r3 = r22
            r2 = r23
            r1 = r24
            java.lang.NullPointerException r0 = new java.lang.NullPointerException     // Catch:{ all -> 0x01db }
            java.lang.String r4 = "null cannot be cast to non-null type okhttp3.internal.ws.RealWebSocket.Close"
            r0.<init>(r4)     // Catch:{ all -> 0x01db }
            throw r0     // Catch:{ all -> 0x01db }
        L_0x01cd:
            r3 = r22
            r2 = r23
            r1 = r24
            java.lang.AssertionError r0 = new java.lang.AssertionError     // Catch:{ all -> 0x01db }
            r0.<init>()     // Catch:{ all -> 0x01db }
            java.lang.Throwable r0 = (java.lang.Throwable) r0     // Catch:{ all -> 0x01db }
            throw r0     // Catch:{ all -> 0x01db }
        L_0x01db:
            r0 = move-exception
            goto L_0x01e4
        L_0x01dd:
            r0 = move-exception
            r3 = r22
            r2 = r23
            r1 = r24
        L_0x01e4:
            T r1 = r1.element
            okhttp3.internal.ws.RealWebSocket$Streams r1 = (okhttp3.internal.ws.RealWebSocket.Streams) r1
            if (r1 == 0) goto L_0x01ef
            java.io.Closeable r1 = (java.io.Closeable) r1
            okhttp3.internal.Util.closeQuietly((java.io.Closeable) r1)
        L_0x01ef:
            T r1 = r2.element
            okhttp3.internal.ws.WebSocketReader r1 = (okhttp3.internal.ws.WebSocketReader) r1
            if (r1 == 0) goto L_0x01fa
            java.io.Closeable r1 = (java.io.Closeable) r1
            okhttp3.internal.Util.closeQuietly((java.io.Closeable) r1)
        L_0x01fa:
            T r1 = r3.element
            okhttp3.internal.ws.WebSocketWriter r1 = (okhttp3.internal.ws.WebSocketWriter) r1
            if (r1 == 0) goto L_0x0205
            java.io.Closeable r1 = (java.io.Closeable) r1
            okhttp3.internal.Util.closeQuietly((java.io.Closeable) r1)
        L_0x0205:
            throw r0
        L_0x0206:
            r0 = move-exception
            monitor-exit(r27)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.ws.RealWebSocket.writeOneFrame$okhttp():boolean");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0020, code lost:
        if (r1 == -1) goto L_0x004e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0022, code lost:
        failWebSocket(new java.net.SocketTimeoutException("sent ping but didn't receive pong within " + r7.pingIntervalMillis + "ms (after " + (r1 - 1) + " successful ping/pongs)"), (okhttp3.Response) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x004d, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:?, code lost:
        r0.writePing(okio.ByteString.EMPTY);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0054, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0055, code lost:
        failWebSocket(r0, (okhttp3.Response) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void writePingFrame$okhttp() {
        /*
            r7 = this;
            monitor-enter(r7)
            boolean r0 = r7.failed     // Catch:{ all -> 0x005d }
            if (r0 == 0) goto L_0x0007
            monitor-exit(r7)
            return
        L_0x0007:
            okhttp3.internal.ws.WebSocketWriter r0 = r7.writer     // Catch:{ all -> 0x005d }
            if (r0 == 0) goto L_0x005b
            boolean r1 = r7.awaitingPong     // Catch:{ all -> 0x005d }
            r2 = -1
            if (r1 == 0) goto L_0x0013
            int r1 = r7.sentPingCount     // Catch:{ all -> 0x005d }
            goto L_0x0014
        L_0x0013:
            r1 = -1
        L_0x0014:
            int r3 = r7.sentPingCount     // Catch:{ all -> 0x005d }
            r4 = 1
            int r3 = r3 + r4
            r7.sentPingCount = r3     // Catch:{ all -> 0x005d }
            r7.awaitingPong = r4     // Catch:{ all -> 0x005d }
            kotlin.Unit r3 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x005d }
            monitor-exit(r7)
            r3 = 0
            if (r1 == r2) goto L_0x004e
            java.net.SocketTimeoutException r0 = new java.net.SocketTimeoutException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r5 = "sent ping but didn't receive pong within "
            r2.append(r5)
            long r5 = r7.pingIntervalMillis
            r2.append(r5)
            java.lang.String r5 = "ms (after "
            r2.append(r5)
            int r1 = r1 - r4
            r2.append(r1)
            java.lang.String r1 = " successful ping/pongs)"
            r2.append(r1)
            java.lang.String r1 = r2.toString()
            r0.<init>(r1)
            java.lang.Exception r0 = (java.lang.Exception) r0
            r7.failWebSocket(r0, r3)
            return
        L_0x004e:
            okio.ByteString r1 = okio.ByteString.EMPTY     // Catch:{ IOException -> 0x0054 }
            r0.writePing(r1)     // Catch:{ IOException -> 0x0054 }
            goto L_0x005a
        L_0x0054:
            r0 = move-exception
            java.lang.Exception r0 = (java.lang.Exception) r0
            r7.failWebSocket(r0, r3)
        L_0x005a:
            return
        L_0x005b:
            monitor-exit(r7)
            return
        L_0x005d:
            r0 = move-exception
            monitor-exit(r7)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.ws.RealWebSocket.writePingFrame$okhttp():void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:?, code lost:
        r5.listener.onFailure(r5, r6, r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x004c, code lost:
        r6 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x004d, code lost:
        if (r0 != null) goto L_0x004f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x004f, code lost:
        okhttp3.internal.Util.closeQuietly((java.io.Closeable) r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0054, code lost:
        if (r2 != null) goto L_0x0056;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0056, code lost:
        okhttp3.internal.Util.closeQuietly((java.io.Closeable) r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x005b, code lost:
        if (r3 != null) goto L_0x005d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x005d, code lost:
        okhttp3.internal.Util.closeQuietly((java.io.Closeable) r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0062, code lost:
        throw r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void failWebSocket(java.lang.Exception r6, okhttp3.Response r7) {
        /*
            r5 = this;
            java.lang.String r0 = "e"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r0)
            monitor-enter(r5)
            boolean r0 = r5.failed     // Catch:{ all -> 0x0063 }
            if (r0 == 0) goto L_0x000c
            monitor-exit(r5)
            return
        L_0x000c:
            r0 = 1
            r5.failed = r0     // Catch:{ all -> 0x0063 }
            okhttp3.internal.ws.RealWebSocket$Streams r0 = r5.streams     // Catch:{ all -> 0x0063 }
            r1 = 0
            r2 = r1
            okhttp3.internal.ws.RealWebSocket$Streams r2 = (okhttp3.internal.ws.RealWebSocket.Streams) r2     // Catch:{ all -> 0x0063 }
            r5.streams = r2     // Catch:{ all -> 0x0063 }
            okhttp3.internal.ws.WebSocketReader r2 = r5.reader     // Catch:{ all -> 0x0063 }
            r3 = r1
            okhttp3.internal.ws.WebSocketReader r3 = (okhttp3.internal.ws.WebSocketReader) r3     // Catch:{ all -> 0x0063 }
            r5.reader = r3     // Catch:{ all -> 0x0063 }
            okhttp3.internal.ws.WebSocketWriter r3 = r5.writer     // Catch:{ all -> 0x0063 }
            okhttp3.internal.ws.WebSocketWriter r1 = (okhttp3.internal.ws.WebSocketWriter) r1     // Catch:{ all -> 0x0063 }
            r5.writer = r1     // Catch:{ all -> 0x0063 }
            okhttp3.internal.concurrent.TaskQueue r1 = r5.taskQueue     // Catch:{ all -> 0x0063 }
            r1.shutdown()     // Catch:{ all -> 0x0063 }
            kotlin.Unit r1 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0063 }
            monitor-exit(r5)
            okhttp3.WebSocketListener r1 = r5.listener     // Catch:{ all -> 0x004c }
            r4 = r5
            okhttp3.WebSocket r4 = (okhttp3.WebSocket) r4     // Catch:{ all -> 0x004c }
            java.lang.Throwable r6 = (java.lang.Throwable) r6     // Catch:{ all -> 0x004c }
            r1.onFailure(r4, r6, r7)     // Catch:{ all -> 0x004c }
            if (r0 == 0) goto L_0x003d
            java.io.Closeable r0 = (java.io.Closeable) r0
            okhttp3.internal.Util.closeQuietly((java.io.Closeable) r0)
        L_0x003d:
            if (r2 == 0) goto L_0x0044
            java.io.Closeable r2 = (java.io.Closeable) r2
            okhttp3.internal.Util.closeQuietly((java.io.Closeable) r2)
        L_0x0044:
            if (r3 == 0) goto L_0x004b
            java.io.Closeable r3 = (java.io.Closeable) r3
            okhttp3.internal.Util.closeQuietly((java.io.Closeable) r3)
        L_0x004b:
            return
        L_0x004c:
            r6 = move-exception
            if (r0 == 0) goto L_0x0054
            java.io.Closeable r0 = (java.io.Closeable) r0
            okhttp3.internal.Util.closeQuietly((java.io.Closeable) r0)
        L_0x0054:
            if (r2 == 0) goto L_0x005b
            java.io.Closeable r2 = (java.io.Closeable) r2
            okhttp3.internal.Util.closeQuietly((java.io.Closeable) r2)
        L_0x005b:
            if (r3 == 0) goto L_0x0062
            java.io.Closeable r3 = (java.io.Closeable) r3
            okhttp3.internal.Util.closeQuietly((java.io.Closeable) r3)
        L_0x0062:
            throw r6
        L_0x0063:
            r6 = move-exception
            monitor-exit(r5)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.ws.RealWebSocket.failWebSocket(java.lang.Exception, okhttp3.Response):void");
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Lokhttp3/internal/ws/RealWebSocket$Message;", "", "formatOpcode", "", "data", "Lokio/ByteString;", "(ILokio/ByteString;)V", "getData", "()Lokio/ByteString;", "getFormatOpcode", "()I", "okhttp"}, k = 1, mv = {1, 4, 0})
    /* compiled from: RealWebSocket.kt */
    public static final class Message {
        private final ByteString data;
        private final int formatOpcode;

        public Message(int i, ByteString byteString) {
            Intrinsics.checkNotNullParameter(byteString, "data");
            this.formatOpcode = i;
            this.data = byteString;
        }

        public final int getFormatOpcode() {
            return this.formatOpcode;
        }

        public final ByteString getData() {
            return this.data;
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\b\b\u0000\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u000f"}, d2 = {"Lokhttp3/internal/ws/RealWebSocket$Close;", "", "code", "", "reason", "Lokio/ByteString;", "cancelAfterCloseMillis", "", "(ILokio/ByteString;J)V", "getCancelAfterCloseMillis", "()J", "getCode", "()I", "getReason", "()Lokio/ByteString;", "okhttp"}, k = 1, mv = {1, 4, 0})
    /* compiled from: RealWebSocket.kt */
    public static final class Close {
        private final long cancelAfterCloseMillis;
        private final int code;
        private final ByteString reason;

        public Close(int i, ByteString byteString, long j) {
            this.code = i;
            this.reason = byteString;
            this.cancelAfterCloseMillis = j;
        }

        public final int getCode() {
            return this.code;
        }

        public final ByteString getReason() {
            return this.reason;
        }

        public final long getCancelAfterCloseMillis() {
            return this.cancelAfterCloseMillis;
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\b&\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u000f"}, d2 = {"Lokhttp3/internal/ws/RealWebSocket$Streams;", "Ljava/io/Closeable;", "client", "", "source", "Lokio/BufferedSource;", "sink", "Lokio/BufferedSink;", "(ZLokio/BufferedSource;Lokio/BufferedSink;)V", "getClient", "()Z", "getSink", "()Lokio/BufferedSink;", "getSource", "()Lokio/BufferedSource;", "okhttp"}, k = 1, mv = {1, 4, 0})
    /* compiled from: RealWebSocket.kt */
    public static abstract class Streams implements Closeable {
        private final boolean client;
        private final BufferedSink sink;
        private final BufferedSource source;

        public Streams(boolean z, BufferedSource bufferedSource, BufferedSink bufferedSink) {
            Intrinsics.checkNotNullParameter(bufferedSource, "source");
            Intrinsics.checkNotNullParameter(bufferedSink, "sink");
            this.client = z;
            this.source = bufferedSource;
            this.sink = bufferedSink;
        }

        public final boolean getClient() {
            return this.client;
        }

        public final BufferedSource getSource() {
            return this.source;
        }

        public final BufferedSink getSink() {
            return this.sink;
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\b\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016¨\u0006\u0005"}, d2 = {"Lokhttp3/internal/ws/RealWebSocket$WriterTask;", "Lokhttp3/internal/concurrent/Task;", "(Lokhttp3/internal/ws/RealWebSocket;)V", "runOnce", "", "okhttp"}, k = 1, mv = {1, 4, 0})
    /* compiled from: RealWebSocket.kt */
    private final class WriterTask extends Task {
        public WriterTask() {
            super(RealWebSocket.this.name + " writer", false, 2, (DefaultConstructorMarker) null);
        }

        public long runOnce() {
            try {
                if (RealWebSocket.this.writeOneFrame$okhttp()) {
                    return 0;
                }
                return -1;
            } catch (IOException e) {
                RealWebSocket.this.failWebSocket(e, (Response) null);
                return -1;
            }
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lokhttp3/internal/ws/RealWebSocket$Companion;", "", "()V", "CANCEL_AFTER_CLOSE_MILLIS", "", "DEFAULT_MINIMUM_DEFLATE_SIZE", "MAX_QUEUE_SIZE", "ONLY_HTTP1", "", "Lokhttp3/Protocol;", "okhttp"}, k = 1, mv = {1, 4, 0})
    /* compiled from: RealWebSocket.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    private final void runWriter() {
        if (!Util.assertionsEnabled || Thread.holdsLock(this)) {
            Task task = this.writerTask;
            if (task != null) {
                TaskQueue.schedule$default(this.taskQueue, task, 0, 2, (Object) null);
                return;
            }
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Thread ");
        Thread currentThread = Thread.currentThread();
        Intrinsics.checkNotNullExpressionValue(currentThread, "Thread.currentThread()");
        sb.append(currentThread.getName());
        sb.append(" MUST hold lock on ");
        sb.append(this);
        throw new AssertionError(sb.toString());
    }
}
