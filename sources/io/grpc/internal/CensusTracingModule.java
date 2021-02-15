package io.grpc.internal;

import com.google.common.base.Preconditions;
import com.google.firebase.analytics.FirebaseAnalytics;
import io.grpc.CallOptions;
import io.grpc.Channel;
import io.grpc.ClientCall;
import io.grpc.ClientInterceptor;
import io.grpc.ClientStreamTracer;
import io.grpc.Context;
import io.grpc.ForwardingClientCall;
import io.grpc.ForwardingClientCallListener;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;
import io.grpc.ServerStreamTracer;
import io.opencensus.trace.BlankSpan;
import io.opencensus.trace.EndSpanOptions;
import io.opencensus.trace.MessageEvent;
import io.opencensus.trace.Span;
import io.opencensus.trace.SpanContext;
import io.opencensus.trace.Status;
import io.opencensus.trace.Tracer;
import io.opencensus.trace.propagation.BinaryFormat;
import io.opencensus.trace.unsafe.ContextUtils;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nullable;

final class CensusTracingModule {
    /* access modifiers changed from: private */
    @Nullable
    public static final AtomicIntegerFieldUpdater<ClientCallTracer> callEndedUpdater;
    /* access modifiers changed from: private */
    public static final Logger logger = Logger.getLogger(CensusTracingModule.class.getName());
    /* access modifiers changed from: private */
    @Nullable
    public static final AtomicIntegerFieldUpdater<ServerTracer> streamClosedUpdater;
    /* access modifiers changed from: private */
    public final Tracer censusTracer;
    private final TracingClientInterceptor clientInterceptor = new TracingClientInterceptor();
    private final ServerTracerFactory serverTracerFactory = new ServerTracerFactory();
    final Metadata.Key<SpanContext> tracingHeader;

    static {
        AtomicIntegerFieldUpdater<ServerTracer> atomicIntegerFieldUpdater;
        AtomicIntegerFieldUpdater<ClientCallTracer> atomicIntegerFieldUpdater2 = null;
        try {
            AtomicIntegerFieldUpdater<ClientCallTracer> newUpdater = AtomicIntegerFieldUpdater.newUpdater(ClientCallTracer.class, "callEnded");
            atomicIntegerFieldUpdater = AtomicIntegerFieldUpdater.newUpdater(ServerTracer.class, "streamClosed");
            atomicIntegerFieldUpdater2 = newUpdater;
        } catch (Throwable th) {
            logger.log(Level.SEVERE, "Creating atomic field updaters failed", th);
            atomicIntegerFieldUpdater = null;
        }
        callEndedUpdater = atomicIntegerFieldUpdater2;
        streamClosedUpdater = atomicIntegerFieldUpdater;
    }

    CensusTracingModule(Tracer tracer, final BinaryFormat binaryFormat) {
        this.censusTracer = (Tracer) Preconditions.checkNotNull(tracer, "censusTracer");
        Preconditions.checkNotNull(binaryFormat, "censusPropagationBinaryFormat");
        this.tracingHeader = Metadata.Key.of("grpc-trace-bin", new Metadata.BinaryMarshaller<SpanContext>() {
            public byte[] toBytes(SpanContext spanContext) {
                return binaryFormat.toByteArray(spanContext);
            }

            public SpanContext parseBytes(byte[] bArr) {
                try {
                    return binaryFormat.fromByteArray(bArr);
                } catch (Exception e) {
                    CensusTracingModule.logger.log(Level.FINE, "Failed to parse tracing header", e);
                    return SpanContext.INVALID;
                }
            }
        });
    }

    /* access modifiers changed from: package-private */
    public ClientCallTracer newClientCallTracer(@Nullable Span span, MethodDescriptor<?, ?> methodDescriptor) {
        return new ClientCallTracer(span, methodDescriptor);
    }

    /* access modifiers changed from: package-private */
    public ServerStreamTracer.Factory getServerTracerFactory() {
        return this.serverTracerFactory;
    }

    /* access modifiers changed from: package-private */
    public ClientInterceptor getClientInterceptor() {
        return this.clientInterceptor;
    }

    /* renamed from: io.grpc.internal.CensusTracingModule$2  reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$io$grpc$Status$Code;

        /* JADX WARNING: Can't wrap try/catch for region: R(36:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|19|20|21|22|23|24|25|26|27|28|29|30|31|32|33|34|36) */
        /* JADX WARNING: Code restructure failed: missing block: B:37:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0049 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0054 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0060 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x006c */
        /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x0078 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x0084 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:25:0x0090 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x009c */
        /* JADX WARNING: Missing exception handler attribute for start block: B:29:0x00a8 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:31:0x00b4 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:33:0x00c0 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                io.grpc.Status$Code[] r0 = io.grpc.Status.Code.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$io$grpc$Status$Code = r0
                io.grpc.Status$Code r1 = io.grpc.Status.Code.OK     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$io$grpc$Status$Code     // Catch:{ NoSuchFieldError -> 0x001d }
                io.grpc.Status$Code r1 = io.grpc.Status.Code.CANCELLED     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$io$grpc$Status$Code     // Catch:{ NoSuchFieldError -> 0x0028 }
                io.grpc.Status$Code r1 = io.grpc.Status.Code.UNKNOWN     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$io$grpc$Status$Code     // Catch:{ NoSuchFieldError -> 0x0033 }
                io.grpc.Status$Code r1 = io.grpc.Status.Code.INVALID_ARGUMENT     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$io$grpc$Status$Code     // Catch:{ NoSuchFieldError -> 0x003e }
                io.grpc.Status$Code r1 = io.grpc.Status.Code.DEADLINE_EXCEEDED     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$io$grpc$Status$Code     // Catch:{ NoSuchFieldError -> 0x0049 }
                io.grpc.Status$Code r1 = io.grpc.Status.Code.NOT_FOUND     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                int[] r0 = $SwitchMap$io$grpc$Status$Code     // Catch:{ NoSuchFieldError -> 0x0054 }
                io.grpc.Status$Code r1 = io.grpc.Status.Code.ALREADY_EXISTS     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                int[] r0 = $SwitchMap$io$grpc$Status$Code     // Catch:{ NoSuchFieldError -> 0x0060 }
                io.grpc.Status$Code r1 = io.grpc.Status.Code.PERMISSION_DENIED     // Catch:{ NoSuchFieldError -> 0x0060 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0060 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0060 }
            L_0x0060:
                int[] r0 = $SwitchMap$io$grpc$Status$Code     // Catch:{ NoSuchFieldError -> 0x006c }
                io.grpc.Status$Code r1 = io.grpc.Status.Code.RESOURCE_EXHAUSTED     // Catch:{ NoSuchFieldError -> 0x006c }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x006c }
                r2 = 9
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x006c }
            L_0x006c:
                int[] r0 = $SwitchMap$io$grpc$Status$Code     // Catch:{ NoSuchFieldError -> 0x0078 }
                io.grpc.Status$Code r1 = io.grpc.Status.Code.FAILED_PRECONDITION     // Catch:{ NoSuchFieldError -> 0x0078 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0078 }
                r2 = 10
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0078 }
            L_0x0078:
                int[] r0 = $SwitchMap$io$grpc$Status$Code     // Catch:{ NoSuchFieldError -> 0x0084 }
                io.grpc.Status$Code r1 = io.grpc.Status.Code.ABORTED     // Catch:{ NoSuchFieldError -> 0x0084 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0084 }
                r2 = 11
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0084 }
            L_0x0084:
                int[] r0 = $SwitchMap$io$grpc$Status$Code     // Catch:{ NoSuchFieldError -> 0x0090 }
                io.grpc.Status$Code r1 = io.grpc.Status.Code.OUT_OF_RANGE     // Catch:{ NoSuchFieldError -> 0x0090 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0090 }
                r2 = 12
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0090 }
            L_0x0090:
                int[] r0 = $SwitchMap$io$grpc$Status$Code     // Catch:{ NoSuchFieldError -> 0x009c }
                io.grpc.Status$Code r1 = io.grpc.Status.Code.UNIMPLEMENTED     // Catch:{ NoSuchFieldError -> 0x009c }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x009c }
                r2 = 13
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x009c }
            L_0x009c:
                int[] r0 = $SwitchMap$io$grpc$Status$Code     // Catch:{ NoSuchFieldError -> 0x00a8 }
                io.grpc.Status$Code r1 = io.grpc.Status.Code.INTERNAL     // Catch:{ NoSuchFieldError -> 0x00a8 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00a8 }
                r2 = 14
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00a8 }
            L_0x00a8:
                int[] r0 = $SwitchMap$io$grpc$Status$Code     // Catch:{ NoSuchFieldError -> 0x00b4 }
                io.grpc.Status$Code r1 = io.grpc.Status.Code.UNAVAILABLE     // Catch:{ NoSuchFieldError -> 0x00b4 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00b4 }
                r2 = 15
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00b4 }
            L_0x00b4:
                int[] r0 = $SwitchMap$io$grpc$Status$Code     // Catch:{ NoSuchFieldError -> 0x00c0 }
                io.grpc.Status$Code r1 = io.grpc.Status.Code.DATA_LOSS     // Catch:{ NoSuchFieldError -> 0x00c0 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00c0 }
                r2 = 16
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00c0 }
            L_0x00c0:
                int[] r0 = $SwitchMap$io$grpc$Status$Code     // Catch:{ NoSuchFieldError -> 0x00cc }
                io.grpc.Status$Code r1 = io.grpc.Status.Code.UNAUTHENTICATED     // Catch:{ NoSuchFieldError -> 0x00cc }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00cc }
                r2 = 17
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x00cc }
            L_0x00cc:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.internal.CensusTracingModule.AnonymousClass2.<clinit>():void");
        }
    }

    static Status convertStatus(io.grpc.Status status) {
        Status status2;
        switch (AnonymousClass2.$SwitchMap$io$grpc$Status$Code[status.getCode().ordinal()]) {
            case 1:
                status2 = Status.OK;
                break;
            case 2:
                status2 = Status.CANCELLED;
                break;
            case 3:
                status2 = Status.UNKNOWN;
                break;
            case 4:
                status2 = Status.INVALID_ARGUMENT;
                break;
            case 5:
                status2 = Status.DEADLINE_EXCEEDED;
                break;
            case 6:
                status2 = Status.NOT_FOUND;
                break;
            case 7:
                status2 = Status.ALREADY_EXISTS;
                break;
            case 8:
                status2 = Status.PERMISSION_DENIED;
                break;
            case 9:
                status2 = Status.RESOURCE_EXHAUSTED;
                break;
            case 10:
                status2 = Status.FAILED_PRECONDITION;
                break;
            case 11:
                status2 = Status.ABORTED;
                break;
            case 12:
                status2 = Status.OUT_OF_RANGE;
                break;
            case 13:
                status2 = Status.UNIMPLEMENTED;
                break;
            case 14:
                status2 = Status.INTERNAL;
                break;
            case 15:
                status2 = Status.UNAVAILABLE;
                break;
            case 16:
                status2 = Status.DATA_LOSS;
                break;
            case 17:
                status2 = Status.UNAUTHENTICATED;
                break;
            default:
                throw new AssertionError("Unhandled status code " + status.getCode());
        }
        return status.getDescription() != null ? status2.withDescription(status.getDescription()) : status2;
    }

    /* access modifiers changed from: private */
    public static EndSpanOptions createEndSpanOptions(io.grpc.Status status, boolean z) {
        return EndSpanOptions.builder().setStatus(convertStatus(status)).setSampleToLocalSpanStore(z).build();
    }

    /* access modifiers changed from: private */
    public static void recordMessageEvent(Span span, MessageEvent.Type type, int i, long j, long j2) {
        MessageEvent.Builder builder = MessageEvent.builder(type, (long) i);
        if (j2 != -1) {
            builder.setUncompressedMessageSize(j2);
        }
        if (j != -1) {
            builder.setCompressedMessageSize(j);
        }
        span.addMessageEvent(builder.build());
    }

    final class ClientCallTracer extends ClientStreamTracer.Factory {
        volatile int callEnded;
        private final boolean isSampledToLocalTracing;
        private final Span span;

        ClientCallTracer(@Nullable Span span2, MethodDescriptor<?, ?> methodDescriptor) {
            Preconditions.checkNotNull(methodDescriptor, FirebaseAnalytics.Param.METHOD);
            this.isSampledToLocalTracing = methodDescriptor.isSampledToLocalTracing();
            this.span = CensusTracingModule.this.censusTracer.spanBuilderWithExplicitParent(CensusTracingModule.generateTraceSpanName(false, methodDescriptor.getFullMethodName()), span2).setRecordEvents(true).startSpan();
        }

        public ClientStreamTracer newClientStreamTracer(ClientStreamTracer.StreamInfo streamInfo, Metadata metadata) {
            if (this.span != BlankSpan.INSTANCE) {
                metadata.discardAll(CensusTracingModule.this.tracingHeader);
                metadata.put(CensusTracingModule.this.tracingHeader, this.span.getContext());
            }
            return new ClientTracer(this.span);
        }

        /* access modifiers changed from: package-private */
        public void callEnded(io.grpc.Status status) {
            if (CensusTracingModule.callEndedUpdater != null) {
                if (CensusTracingModule.callEndedUpdater.getAndSet(this, 1) != 0) {
                    return;
                }
            } else if (this.callEnded == 0) {
                this.callEnded = 1;
            } else {
                return;
            }
            this.span.end(CensusTracingModule.createEndSpanOptions(status, this.isSampledToLocalTracing));
        }
    }

    private static final class ClientTracer extends ClientStreamTracer {
        private final Span span;

        ClientTracer(Span span2) {
            this.span = (Span) Preconditions.checkNotNull(span2, "span");
        }

        public void outboundMessageSent(int i, long j, long j2) {
            CensusTracingModule.recordMessageEvent(this.span, MessageEvent.Type.SENT, i, j, j2);
        }

        public void inboundMessageRead(int i, long j, long j2) {
            CensusTracingModule.recordMessageEvent(this.span, MessageEvent.Type.RECEIVED, i, j, j2);
        }
    }

    private final class ServerTracer extends ServerStreamTracer {
        volatile boolean isSampledToLocalTracing;
        private final Span span;
        volatile int streamClosed;

        ServerTracer(String str, @Nullable SpanContext spanContext) {
            Preconditions.checkNotNull(str, "fullMethodName");
            this.span = CensusTracingModule.this.censusTracer.spanBuilderWithRemoteParent(CensusTracingModule.generateTraceSpanName(true, str), spanContext).setRecordEvents(true).startSpan();
        }

        public void serverCallStarted(ServerStreamTracer.ServerCallInfo<?, ?> serverCallInfo) {
            this.isSampledToLocalTracing = serverCallInfo.getMethodDescriptor().isSampledToLocalTracing();
        }

        public void streamClosed(io.grpc.Status status) {
            if (CensusTracingModule.streamClosedUpdater != null) {
                if (CensusTracingModule.streamClosedUpdater.getAndSet(this, 1) != 0) {
                    return;
                }
            } else if (this.streamClosed == 0) {
                this.streamClosed = 1;
            } else {
                return;
            }
            this.span.end(CensusTracingModule.createEndSpanOptions(status, this.isSampledToLocalTracing));
        }

        public Context filterContext(Context context) {
            return ContextUtils.withValue(context, this.span);
        }

        public void outboundMessageSent(int i, long j, long j2) {
            CensusTracingModule.recordMessageEvent(this.span, MessageEvent.Type.SENT, i, j, j2);
        }

        public void inboundMessageRead(int i, long j, long j2) {
            CensusTracingModule.recordMessageEvent(this.span, MessageEvent.Type.RECEIVED, i, j, j2);
        }
    }

    final class ServerTracerFactory extends ServerStreamTracer.Factory {
        ServerTracerFactory() {
        }

        public ServerStreamTracer newServerStreamTracer(String str, Metadata metadata) {
            SpanContext spanContext = (SpanContext) metadata.get(CensusTracingModule.this.tracingHeader);
            if (spanContext == SpanContext.INVALID) {
                spanContext = null;
            }
            return new ServerTracer(str, spanContext);
        }
    }

    final class TracingClientInterceptor implements ClientInterceptor {
        TracingClientInterceptor() {
        }

        public <ReqT, RespT> ClientCall<ReqT, RespT> interceptCall(MethodDescriptor<ReqT, RespT> methodDescriptor, CallOptions callOptions, Channel channel) {
            final ClientCallTracer newClientCallTracer = CensusTracingModule.this.newClientCallTracer(ContextUtils.getValue(Context.current()), methodDescriptor);
            return new ForwardingClientCall.SimpleForwardingClientCall<ReqT, RespT>(channel.newCall(methodDescriptor, callOptions.withStreamTracerFactory(newClientCallTracer))) {
                public void start(ClientCall.Listener<RespT> listener, Metadata metadata) {
                    delegate().start(new ForwardingClientCallListener.SimpleForwardingClientCallListener<RespT>(listener) {
                        public void onClose(io.grpc.Status status, Metadata metadata) {
                            newClientCallTracer.callEnded(status);
                            super.onClose(status, metadata);
                        }
                    }, metadata);
                }
            };
        }
    }

    static String generateTraceSpanName(boolean z, String str) {
        String str2 = z ? "Recv" : "Sent";
        return str2 + "." + str.replace('/', '.');
    }
}
