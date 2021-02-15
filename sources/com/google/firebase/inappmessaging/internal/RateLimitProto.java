package com.google.firebase.inappmessaging.internal;

import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MapEntryLite;
import com.google.protobuf.MapFieldLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import com.google.protobuf.WireFormat;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.Map;

/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
public final class RateLimitProto {

    /* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
    public interface CounterOrBuilder extends MessageLiteOrBuilder {
        long getStartTimeEpoch();

        long getValue();
    }

    /* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
    public interface RateLimitOrBuilder extends MessageLiteOrBuilder {
        boolean containsLimits(String str);

        @Deprecated
        Map<String, Counter> getLimits();

        int getLimitsCount();

        Map<String, Counter> getLimitsMap();

        Counter getLimitsOrDefault(String str, Counter counter);

        Counter getLimitsOrThrow(String str);
    }

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }

    private RateLimitProto() {
    }

    /* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
    public static final class RateLimit extends GeneratedMessageLite<RateLimit, Builder> implements RateLimitOrBuilder {
        /* access modifiers changed from: private */
        public static final RateLimit DEFAULT_INSTANCE;
        public static final int LIMITS_FIELD_NUMBER = 1;
        private static volatile Parser<RateLimit> PARSER;
        private MapFieldLite<String, Counter> limits_ = MapFieldLite.emptyMapField();

        private RateLimit() {
        }

        /* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
        private static final class LimitsDefaultEntryHolder {
            static final MapEntryLite<String, Counter> defaultEntry = MapEntryLite.newDefaultInstance(WireFormat.FieldType.STRING, "", WireFormat.FieldType.MESSAGE, Counter.getDefaultInstance());

            private LimitsDefaultEntryHolder() {
            }
        }

        private MapFieldLite<String, Counter> internalGetLimits() {
            return this.limits_;
        }

        private MapFieldLite<String, Counter> internalGetMutableLimits() {
            if (!this.limits_.isMutable()) {
                this.limits_ = this.limits_.mutableCopy();
            }
            return this.limits_;
        }

        public int getLimitsCount() {
            return internalGetLimits().size();
        }

        public boolean containsLimits(String str) {
            if (str != null) {
                return internalGetLimits().containsKey(str);
            }
            throw null;
        }

        @Deprecated
        public Map<String, Counter> getLimits() {
            return getLimitsMap();
        }

        public Map<String, Counter> getLimitsMap() {
            return Collections.unmodifiableMap(internalGetLimits());
        }

        public Counter getLimitsOrDefault(String str, Counter counter) {
            if (str != null) {
                MapFieldLite<String, Counter> internalGetLimits = internalGetLimits();
                return internalGetLimits.containsKey(str) ? internalGetLimits.get(str) : counter;
            }
            throw null;
        }

        public Counter getLimitsOrThrow(String str) {
            if (str != null) {
                MapFieldLite<String, Counter> internalGetLimits = internalGetLimits();
                if (internalGetLimits.containsKey(str)) {
                    return internalGetLimits.get(str);
                }
                throw new IllegalArgumentException();
            }
            throw null;
        }

        /* access modifiers changed from: private */
        public Map<String, Counter> getMutableLimitsMap() {
            return internalGetMutableLimits();
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            for (Map.Entry next : internalGetLimits().entrySet()) {
                LimitsDefaultEntryHolder.defaultEntry.serializeTo(codedOutputStream, 1, (String) next.getKey(), (Counter) next.getValue());
            }
        }

        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            for (Map.Entry next : internalGetLimits().entrySet()) {
                i2 += LimitsDefaultEntryHolder.defaultEntry.computeMessageSize(1, (String) next.getKey(), (Counter) next.getValue());
            }
            this.memoizedSerializedSize = i2;
            return i2;
        }

        public static RateLimit parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (RateLimit) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static RateLimit parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (RateLimit) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static RateLimit parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (RateLimit) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static RateLimit parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (RateLimit) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static RateLimit parseFrom(InputStream inputStream) throws IOException {
            return (RateLimit) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static RateLimit parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (RateLimit) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static RateLimit parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (RateLimit) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static RateLimit parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (RateLimit) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static RateLimit parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (RateLimit) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static RateLimit parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (RateLimit) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(RateLimit rateLimit) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(rateLimit);
        }

        /* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
        public static final class Builder extends GeneratedMessageLite.Builder<RateLimit, Builder> implements RateLimitOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 r1) {
                this();
            }

            private Builder() {
                super(RateLimit.DEFAULT_INSTANCE);
            }

            public int getLimitsCount() {
                return ((RateLimit) this.instance).getLimitsMap().size();
            }

            public boolean containsLimits(String str) {
                if (str != null) {
                    return ((RateLimit) this.instance).getLimitsMap().containsKey(str);
                }
                throw null;
            }

            public Builder clearLimits() {
                copyOnWrite();
                ((RateLimit) this.instance).getMutableLimitsMap().clear();
                return this;
            }

            public Builder removeLimits(String str) {
                if (str != null) {
                    copyOnWrite();
                    ((RateLimit) this.instance).getMutableLimitsMap().remove(str);
                    return this;
                }
                throw null;
            }

            @Deprecated
            public Map<String, Counter> getLimits() {
                return getLimitsMap();
            }

            public Map<String, Counter> getLimitsMap() {
                return Collections.unmodifiableMap(((RateLimit) this.instance).getLimitsMap());
            }

            public Counter getLimitsOrDefault(String str, Counter counter) {
                if (str != null) {
                    Map<String, Counter> limitsMap = ((RateLimit) this.instance).getLimitsMap();
                    return limitsMap.containsKey(str) ? limitsMap.get(str) : counter;
                }
                throw null;
            }

            public Counter getLimitsOrThrow(String str) {
                if (str != null) {
                    Map<String, Counter> limitsMap = ((RateLimit) this.instance).getLimitsMap();
                    if (limitsMap.containsKey(str)) {
                        return limitsMap.get(str);
                    }
                    throw new IllegalArgumentException();
                }
                throw null;
            }

            public Builder putLimits(String str, Counter counter) {
                if (str == null) {
                    throw null;
                } else if (counter != null) {
                    copyOnWrite();
                    ((RateLimit) this.instance).getMutableLimitsMap().put(str, counter);
                    return this;
                } else {
                    throw null;
                }
            }

            public Builder putAllLimits(Map<String, Counter> map) {
                copyOnWrite();
                ((RateLimit) this.instance).getMutableLimitsMap().putAll(map);
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
                case 1:
                    return new RateLimit();
                case 2:
                    return DEFAULT_INSTANCE;
                case 3:
                    this.limits_.makeImmutable();
                    return null;
                case 4:
                    return new Builder((AnonymousClass1) null);
                case 5:
                    this.limits_ = ((GeneratedMessageLite.Visitor) obj).visitMap(this.limits_, ((RateLimit) obj2).internalGetLimits());
                    GeneratedMessageLite.MergeFromVisitor mergeFromVisitor = GeneratedMessageLite.MergeFromVisitor.INSTANCE;
                    return this;
                case 6:
                    CodedInputStream codedInputStream = (CodedInputStream) obj;
                    ExtensionRegistryLite extensionRegistryLite = (ExtensionRegistryLite) obj2;
                    boolean z = false;
                    while (!z) {
                        try {
                            int readTag = codedInputStream.readTag();
                            if (readTag != 0) {
                                if (readTag == 10) {
                                    if (!this.limits_.isMutable()) {
                                        this.limits_ = this.limits_.mutableCopy();
                                    }
                                    LimitsDefaultEntryHolder.defaultEntry.parseInto(this.limits_, codedInputStream, extensionRegistryLite);
                                } else if (!codedInputStream.skipField(readTag)) {
                                }
                            }
                            z = true;
                        } catch (InvalidProtocolBufferException e) {
                            throw new RuntimeException(e.setUnfinishedMessage(this));
                        } catch (IOException e2) {
                            throw new RuntimeException(new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this));
                        }
                    }
                    break;
                case 7:
                    break;
                case 8:
                    if (PARSER == null) {
                        synchronized (RateLimit.class) {
                            if (PARSER == null) {
                                PARSER = new GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
                            }
                        }
                    }
                    return PARSER;
                default:
                    throw new UnsupportedOperationException();
            }
            return DEFAULT_INSTANCE;
        }

        static {
            RateLimit rateLimit = new RateLimit();
            DEFAULT_INSTANCE = rateLimit;
            rateLimit.makeImmutable();
        }

        public static RateLimit getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<RateLimit> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    /* renamed from: com.google.firebase.inappmessaging.internal.RateLimitProto$1  reason: invalid class name */
    /* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke;

        /* JADX WARNING: Can't wrap try/catch for region: R(18:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|18) */
        /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0049 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0054 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke[] r0 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke = r0
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke     // Catch:{ NoSuchFieldError -> 0x001d }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.IS_INITIALIZED     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.MAKE_IMMUTABLE     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.NEW_BUILDER     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke     // Catch:{ NoSuchFieldError -> 0x003e }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.VISIT     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke     // Catch:{ NoSuchFieldError -> 0x0049 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.MERGE_FROM_STREAM     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                int[] r0 = $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke     // Catch:{ NoSuchFieldError -> 0x0054 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                int[] r0 = $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke     // Catch:{ NoSuchFieldError -> 0x0060 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.GET_PARSER     // Catch:{ NoSuchFieldError -> 0x0060 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0060 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0060 }
            L_0x0060:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.inappmessaging.internal.RateLimitProto.AnonymousClass1.<clinit>():void");
        }
    }

    /* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
    public static final class Counter extends GeneratedMessageLite<Counter, Builder> implements CounterOrBuilder {
        /* access modifiers changed from: private */
        public static final Counter DEFAULT_INSTANCE;
        private static volatile Parser<Counter> PARSER = null;
        public static final int START_TIME_EPOCH_FIELD_NUMBER = 2;
        public static final int VALUE_FIELD_NUMBER = 1;
        private long startTimeEpoch_;
        private long value_;

        private Counter() {
        }

        public long getValue() {
            return this.value_;
        }

        /* access modifiers changed from: private */
        public void setValue(long j) {
            this.value_ = j;
        }

        /* access modifiers changed from: private */
        public void clearValue() {
            this.value_ = 0;
        }

        public long getStartTimeEpoch() {
            return this.startTimeEpoch_;
        }

        /* access modifiers changed from: private */
        public void setStartTimeEpoch(long j) {
            this.startTimeEpoch_ = j;
        }

        /* access modifiers changed from: private */
        public void clearStartTimeEpoch() {
            this.startTimeEpoch_ = 0;
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            long j = this.value_;
            if (j != 0) {
                codedOutputStream.writeInt64(1, j);
            }
            long j2 = this.startTimeEpoch_;
            if (j2 != 0) {
                codedOutputStream.writeInt64(2, j2);
            }
        }

        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            long j = this.value_;
            if (j != 0) {
                i2 = 0 + CodedOutputStream.computeInt64Size(1, j);
            }
            long j2 = this.startTimeEpoch_;
            if (j2 != 0) {
                i2 += CodedOutputStream.computeInt64Size(2, j2);
            }
            this.memoizedSerializedSize = i2;
            return i2;
        }

        public static Counter parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (Counter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static Counter parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Counter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static Counter parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (Counter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static Counter parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Counter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static Counter parseFrom(InputStream inputStream) throws IOException {
            return (Counter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static Counter parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Counter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static Counter parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (Counter) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static Counter parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Counter) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static Counter parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (Counter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static Counter parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Counter) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(Counter counter) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(counter);
        }

        /* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
        public static final class Builder extends GeneratedMessageLite.Builder<Counter, Builder> implements CounterOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 r1) {
                this();
            }

            private Builder() {
                super(Counter.DEFAULT_INSTANCE);
            }

            public long getValue() {
                return ((Counter) this.instance).getValue();
            }

            public Builder setValue(long j) {
                copyOnWrite();
                ((Counter) this.instance).setValue(j);
                return this;
            }

            public Builder clearValue() {
                copyOnWrite();
                ((Counter) this.instance).clearValue();
                return this;
            }

            public long getStartTimeEpoch() {
                return ((Counter) this.instance).getStartTimeEpoch();
            }

            public Builder setStartTimeEpoch(long j) {
                copyOnWrite();
                ((Counter) this.instance).setStartTimeEpoch(j);
                return this;
            }

            public Builder clearStartTimeEpoch() {
                copyOnWrite();
                ((Counter) this.instance).clearStartTimeEpoch();
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            boolean z = false;
            switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
                case 1:
                    return new Counter();
                case 2:
                    return DEFAULT_INSTANCE;
                case 3:
                    return null;
                case 4:
                    return new Builder((AnonymousClass1) null);
                case 5:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                    Counter counter = (Counter) obj2;
                    this.value_ = visitor.visitLong(this.value_ != 0, this.value_, counter.value_ != 0, counter.value_);
                    this.startTimeEpoch_ = visitor.visitLong(this.startTimeEpoch_ != 0, this.startTimeEpoch_, counter.startTimeEpoch_ != 0, counter.startTimeEpoch_);
                    GeneratedMessageLite.MergeFromVisitor mergeFromVisitor = GeneratedMessageLite.MergeFromVisitor.INSTANCE;
                    return this;
                case 6:
                    CodedInputStream codedInputStream = (CodedInputStream) obj;
                    ExtensionRegistryLite extensionRegistryLite = (ExtensionRegistryLite) obj2;
                    while (!z) {
                        try {
                            int readTag = codedInputStream.readTag();
                            if (readTag != 0) {
                                if (readTag == 8) {
                                    this.value_ = codedInputStream.readInt64();
                                } else if (readTag == 16) {
                                    this.startTimeEpoch_ = codedInputStream.readInt64();
                                } else if (!codedInputStream.skipField(readTag)) {
                                }
                            }
                            z = true;
                        } catch (InvalidProtocolBufferException e) {
                            throw new RuntimeException(e.setUnfinishedMessage(this));
                        } catch (IOException e2) {
                            throw new RuntimeException(new InvalidProtocolBufferException(e2.getMessage()).setUnfinishedMessage(this));
                        }
                    }
                    break;
                case 7:
                    break;
                case 8:
                    if (PARSER == null) {
                        synchronized (Counter.class) {
                            if (PARSER == null) {
                                PARSER = new GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
                            }
                        }
                    }
                    return PARSER;
                default:
                    throw new UnsupportedOperationException();
            }
            return DEFAULT_INSTANCE;
        }

        static {
            Counter counter = new Counter();
            DEFAULT_INSTANCE = counter;
            counter.makeImmutable();
        }

        public static Counter getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Counter> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }
}
