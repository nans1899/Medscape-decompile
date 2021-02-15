package developers.mobile.abt;

import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

/* compiled from: com.google.firebase:firebase-abt@@19.0.1 */
public final class FirebaseAbt {

    /* compiled from: com.google.firebase:firebase-abt@@19.0.1 */
    public interface ExperimentLiteOrBuilder extends MessageLiteOrBuilder {
        String getExperimentId();

        ByteString getExperimentIdBytes();
    }

    /* compiled from: com.google.firebase:firebase-abt@@19.0.1 */
    public interface ExperimentPayloadOrBuilder extends MessageLiteOrBuilder {
        String getActivateEventToLog();

        ByteString getActivateEventToLogBytes();

        String getClearEventToLog();

        ByteString getClearEventToLogBytes();

        String getExperimentId();

        ByteString getExperimentIdBytes();

        long getExperimentStartTimeMillis();

        ExperimentLite getOngoingExperiments(int i);

        int getOngoingExperimentsCount();

        List<ExperimentLite> getOngoingExperimentsList();

        ExperimentPayload.ExperimentOverflowPolicy getOverflowPolicy();

        int getOverflowPolicyValue();

        String getSetEventToLog();

        ByteString getSetEventToLogBytes();

        long getTimeToLiveMillis();

        String getTimeoutEventToLog();

        ByteString getTimeoutEventToLogBytes();

        String getTriggerEvent();

        ByteString getTriggerEventBytes();

        long getTriggerTimeoutMillis();

        String getTtlExpiryEventToLog();

        ByteString getTtlExpiryEventToLogBytes();

        String getVariantId();

        ByteString getVariantIdBytes();
    }

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }

    private FirebaseAbt() {
    }

    /* compiled from: com.google.firebase:firebase-abt@@19.0.1 */
    public static final class ExperimentLite extends GeneratedMessageLite<ExperimentLite, Builder> implements ExperimentLiteOrBuilder {
        /* access modifiers changed from: private */
        public static final ExperimentLite DEFAULT_INSTANCE;
        public static final int EXPERIMENT_ID_FIELD_NUMBER = 1;
        private static volatile Parser<ExperimentLite> PARSER;
        private String experimentId_ = "";

        private ExperimentLite() {
        }

        public String getExperimentId() {
            return this.experimentId_;
        }

        public ByteString getExperimentIdBytes() {
            return ByteString.copyFromUtf8(this.experimentId_);
        }

        /* access modifiers changed from: private */
        public void setExperimentId(String str) {
            if (str != null) {
                this.experimentId_ = str;
                return;
            }
            throw null;
        }

        /* access modifiers changed from: private */
        public void clearExperimentId() {
            this.experimentId_ = getDefaultInstance().getExperimentId();
        }

        /* access modifiers changed from: private */
        public void setExperimentIdBytes(ByteString byteString) {
            if (byteString != null) {
                checkByteStringIsUtf8(byteString);
                this.experimentId_ = byteString.toStringUtf8();
                return;
            }
            throw null;
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (!this.experimentId_.isEmpty()) {
                codedOutputStream.writeString(1, getExperimentId());
            }
        }

        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            if (!this.experimentId_.isEmpty()) {
                i2 = 0 + CodedOutputStream.computeStringSize(1, getExperimentId());
            }
            this.memoizedSerializedSize = i2;
            return i2;
        }

        public static ExperimentLite parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (ExperimentLite) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static ExperimentLite parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (ExperimentLite) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static ExperimentLite parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (ExperimentLite) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static ExperimentLite parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (ExperimentLite) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static ExperimentLite parseFrom(InputStream inputStream) throws IOException {
            return (ExperimentLite) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static ExperimentLite parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ExperimentLite) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static ExperimentLite parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (ExperimentLite) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static ExperimentLite parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ExperimentLite) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static ExperimentLite parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (ExperimentLite) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static ExperimentLite parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ExperimentLite) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(ExperimentLite experimentLite) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(experimentLite);
        }

        /* compiled from: com.google.firebase:firebase-abt@@19.0.1 */
        public static final class Builder extends GeneratedMessageLite.Builder<ExperimentLite, Builder> implements ExperimentLiteOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 r1) {
                this();
            }

            private Builder() {
                super(ExperimentLite.DEFAULT_INSTANCE);
            }

            public String getExperimentId() {
                return ((ExperimentLite) this.instance).getExperimentId();
            }

            public ByteString getExperimentIdBytes() {
                return ((ExperimentLite) this.instance).getExperimentIdBytes();
            }

            public Builder setExperimentId(String str) {
                copyOnWrite();
                ((ExperimentLite) this.instance).setExperimentId(str);
                return this;
            }

            public Builder clearExperimentId() {
                copyOnWrite();
                ((ExperimentLite) this.instance).clearExperimentId();
                return this;
            }

            public Builder setExperimentIdBytes(ByteString byteString) {
                copyOnWrite();
                ((ExperimentLite) this.instance).setExperimentIdBytes(byteString);
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
                case 1:
                    return new ExperimentLite();
                case 2:
                    return DEFAULT_INSTANCE;
                case 3:
                    return null;
                case 4:
                    return new Builder((AnonymousClass1) null);
                case 5:
                    ExperimentLite experimentLite = (ExperimentLite) obj2;
                    this.experimentId_ = ((GeneratedMessageLite.Visitor) obj).visitString(!this.experimentId_.isEmpty(), this.experimentId_, true ^ experimentLite.experimentId_.isEmpty(), experimentLite.experimentId_);
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
                                    this.experimentId_ = codedInputStream.readStringRequireUtf8();
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
                        synchronized (ExperimentLite.class) {
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
            ExperimentLite experimentLite = new ExperimentLite();
            DEFAULT_INSTANCE = experimentLite;
            experimentLite.makeImmutable();
        }

        public static ExperimentLite getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<ExperimentLite> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    /* renamed from: developers.mobile.abt.FirebaseAbt$1  reason: invalid class name */
    /* compiled from: com.google.firebase:firebase-abt@@19.0.1 */
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
            throw new UnsupportedOperationException("Method not decompiled: developers.mobile.abt.FirebaseAbt.AnonymousClass1.<clinit>():void");
        }
    }

    /* compiled from: com.google.firebase:firebase-abt@@19.0.1 */
    public static final class ExperimentPayload extends GeneratedMessageLite<ExperimentPayload, Builder> implements ExperimentPayloadOrBuilder {
        public static final int ACTIVATE_EVENT_TO_LOG_FIELD_NUMBER = 8;
        public static final int CLEAR_EVENT_TO_LOG_FIELD_NUMBER = 9;
        /* access modifiers changed from: private */
        public static final ExperimentPayload DEFAULT_INSTANCE;
        public static final int EXPERIMENT_ID_FIELD_NUMBER = 1;
        public static final int EXPERIMENT_START_TIME_MILLIS_FIELD_NUMBER = 3;
        public static final int ONGOING_EXPERIMENTS_FIELD_NUMBER = 13;
        public static final int OVERFLOW_POLICY_FIELD_NUMBER = 12;
        private static volatile Parser<ExperimentPayload> PARSER = null;
        public static final int SET_EVENT_TO_LOG_FIELD_NUMBER = 7;
        public static final int TIMEOUT_EVENT_TO_LOG_FIELD_NUMBER = 10;
        public static final int TIME_TO_LIVE_MILLIS_FIELD_NUMBER = 6;
        public static final int TRIGGER_EVENT_FIELD_NUMBER = 4;
        public static final int TRIGGER_TIMEOUT_MILLIS_FIELD_NUMBER = 5;
        public static final int TTL_EXPIRY_EVENT_TO_LOG_FIELD_NUMBER = 11;
        public static final int VARIANT_ID_FIELD_NUMBER = 2;
        private String activateEventToLog_ = "";
        private int bitField0_;
        private String clearEventToLog_ = "";
        private String experimentId_ = "";
        private long experimentStartTimeMillis_;
        private Internal.ProtobufList<ExperimentLite> ongoingExperiments_ = emptyProtobufList();
        private int overflowPolicy_;
        private String setEventToLog_ = "";
        private long timeToLiveMillis_;
        private String timeoutEventToLog_ = "";
        private String triggerEvent_ = "";
        private long triggerTimeoutMillis_;
        private String ttlExpiryEventToLog_ = "";
        private String variantId_ = "";

        private ExperimentPayload() {
        }

        /* compiled from: com.google.firebase:firebase-abt@@19.0.1 */
        public enum ExperimentOverflowPolicy implements Internal.EnumLite {
            POLICY_UNSPECIFIED(0),
            DISCARD_OLDEST(1),
            IGNORE_NEWEST(2),
            UNRECOGNIZED(-1);
            
            public static final int DISCARD_OLDEST_VALUE = 1;
            public static final int IGNORE_NEWEST_VALUE = 2;
            public static final int POLICY_UNSPECIFIED_VALUE = 0;
            private static final Internal.EnumLiteMap<ExperimentOverflowPolicy> internalValueMap = null;
            private final int value;

            static {
                internalValueMap = new Internal.EnumLiteMap<ExperimentOverflowPolicy>() {
                    public ExperimentOverflowPolicy findValueByNumber(int i) {
                        return ExperimentOverflowPolicy.forNumber(i);
                    }
                };
            }

            public final int getNumber() {
                return this.value;
            }

            @Deprecated
            public static ExperimentOverflowPolicy valueOf(int i) {
                return forNumber(i);
            }

            public static ExperimentOverflowPolicy forNumber(int i) {
                if (i == 0) {
                    return POLICY_UNSPECIFIED;
                }
                if (i == 1) {
                    return DISCARD_OLDEST;
                }
                if (i != 2) {
                    return null;
                }
                return IGNORE_NEWEST;
            }

            public static Internal.EnumLiteMap<ExperimentOverflowPolicy> internalGetValueMap() {
                return internalValueMap;
            }

            private ExperimentOverflowPolicy(int i) {
                this.value = i;
            }
        }

        public String getExperimentId() {
            return this.experimentId_;
        }

        public ByteString getExperimentIdBytes() {
            return ByteString.copyFromUtf8(this.experimentId_);
        }

        /* access modifiers changed from: private */
        public void setExperimentId(String str) {
            if (str != null) {
                this.experimentId_ = str;
                return;
            }
            throw null;
        }

        /* access modifiers changed from: private */
        public void clearExperimentId() {
            this.experimentId_ = getDefaultInstance().getExperimentId();
        }

        /* access modifiers changed from: private */
        public void setExperimentIdBytes(ByteString byteString) {
            if (byteString != null) {
                checkByteStringIsUtf8(byteString);
                this.experimentId_ = byteString.toStringUtf8();
                return;
            }
            throw null;
        }

        public String getVariantId() {
            return this.variantId_;
        }

        public ByteString getVariantIdBytes() {
            return ByteString.copyFromUtf8(this.variantId_);
        }

        /* access modifiers changed from: private */
        public void setVariantId(String str) {
            if (str != null) {
                this.variantId_ = str;
                return;
            }
            throw null;
        }

        /* access modifiers changed from: private */
        public void clearVariantId() {
            this.variantId_ = getDefaultInstance().getVariantId();
        }

        /* access modifiers changed from: private */
        public void setVariantIdBytes(ByteString byteString) {
            if (byteString != null) {
                checkByteStringIsUtf8(byteString);
                this.variantId_ = byteString.toStringUtf8();
                return;
            }
            throw null;
        }

        public long getExperimentStartTimeMillis() {
            return this.experimentStartTimeMillis_;
        }

        /* access modifiers changed from: private */
        public void setExperimentStartTimeMillis(long j) {
            this.experimentStartTimeMillis_ = j;
        }

        /* access modifiers changed from: private */
        public void clearExperimentStartTimeMillis() {
            this.experimentStartTimeMillis_ = 0;
        }

        public String getTriggerEvent() {
            return this.triggerEvent_;
        }

        public ByteString getTriggerEventBytes() {
            return ByteString.copyFromUtf8(this.triggerEvent_);
        }

        /* access modifiers changed from: private */
        public void setTriggerEvent(String str) {
            if (str != null) {
                this.triggerEvent_ = str;
                return;
            }
            throw null;
        }

        /* access modifiers changed from: private */
        public void clearTriggerEvent() {
            this.triggerEvent_ = getDefaultInstance().getTriggerEvent();
        }

        /* access modifiers changed from: private */
        public void setTriggerEventBytes(ByteString byteString) {
            if (byteString != null) {
                checkByteStringIsUtf8(byteString);
                this.triggerEvent_ = byteString.toStringUtf8();
                return;
            }
            throw null;
        }

        public long getTriggerTimeoutMillis() {
            return this.triggerTimeoutMillis_;
        }

        /* access modifiers changed from: private */
        public void setTriggerTimeoutMillis(long j) {
            this.triggerTimeoutMillis_ = j;
        }

        /* access modifiers changed from: private */
        public void clearTriggerTimeoutMillis() {
            this.triggerTimeoutMillis_ = 0;
        }

        public long getTimeToLiveMillis() {
            return this.timeToLiveMillis_;
        }

        /* access modifiers changed from: private */
        public void setTimeToLiveMillis(long j) {
            this.timeToLiveMillis_ = j;
        }

        /* access modifiers changed from: private */
        public void clearTimeToLiveMillis() {
            this.timeToLiveMillis_ = 0;
        }

        public String getSetEventToLog() {
            return this.setEventToLog_;
        }

        public ByteString getSetEventToLogBytes() {
            return ByteString.copyFromUtf8(this.setEventToLog_);
        }

        /* access modifiers changed from: private */
        public void setSetEventToLog(String str) {
            if (str != null) {
                this.setEventToLog_ = str;
                return;
            }
            throw null;
        }

        /* access modifiers changed from: private */
        public void clearSetEventToLog() {
            this.setEventToLog_ = getDefaultInstance().getSetEventToLog();
        }

        /* access modifiers changed from: private */
        public void setSetEventToLogBytes(ByteString byteString) {
            if (byteString != null) {
                checkByteStringIsUtf8(byteString);
                this.setEventToLog_ = byteString.toStringUtf8();
                return;
            }
            throw null;
        }

        public String getActivateEventToLog() {
            return this.activateEventToLog_;
        }

        public ByteString getActivateEventToLogBytes() {
            return ByteString.copyFromUtf8(this.activateEventToLog_);
        }

        /* access modifiers changed from: private */
        public void setActivateEventToLog(String str) {
            if (str != null) {
                this.activateEventToLog_ = str;
                return;
            }
            throw null;
        }

        /* access modifiers changed from: private */
        public void clearActivateEventToLog() {
            this.activateEventToLog_ = getDefaultInstance().getActivateEventToLog();
        }

        /* access modifiers changed from: private */
        public void setActivateEventToLogBytes(ByteString byteString) {
            if (byteString != null) {
                checkByteStringIsUtf8(byteString);
                this.activateEventToLog_ = byteString.toStringUtf8();
                return;
            }
            throw null;
        }

        public String getClearEventToLog() {
            return this.clearEventToLog_;
        }

        public ByteString getClearEventToLogBytes() {
            return ByteString.copyFromUtf8(this.clearEventToLog_);
        }

        /* access modifiers changed from: private */
        public void setClearEventToLog(String str) {
            if (str != null) {
                this.clearEventToLog_ = str;
                return;
            }
            throw null;
        }

        /* access modifiers changed from: private */
        public void clearClearEventToLog() {
            this.clearEventToLog_ = getDefaultInstance().getClearEventToLog();
        }

        /* access modifiers changed from: private */
        public void setClearEventToLogBytes(ByteString byteString) {
            if (byteString != null) {
                checkByteStringIsUtf8(byteString);
                this.clearEventToLog_ = byteString.toStringUtf8();
                return;
            }
            throw null;
        }

        public String getTimeoutEventToLog() {
            return this.timeoutEventToLog_;
        }

        public ByteString getTimeoutEventToLogBytes() {
            return ByteString.copyFromUtf8(this.timeoutEventToLog_);
        }

        /* access modifiers changed from: private */
        public void setTimeoutEventToLog(String str) {
            if (str != null) {
                this.timeoutEventToLog_ = str;
                return;
            }
            throw null;
        }

        /* access modifiers changed from: private */
        public void clearTimeoutEventToLog() {
            this.timeoutEventToLog_ = getDefaultInstance().getTimeoutEventToLog();
        }

        /* access modifiers changed from: private */
        public void setTimeoutEventToLogBytes(ByteString byteString) {
            if (byteString != null) {
                checkByteStringIsUtf8(byteString);
                this.timeoutEventToLog_ = byteString.toStringUtf8();
                return;
            }
            throw null;
        }

        public String getTtlExpiryEventToLog() {
            return this.ttlExpiryEventToLog_;
        }

        public ByteString getTtlExpiryEventToLogBytes() {
            return ByteString.copyFromUtf8(this.ttlExpiryEventToLog_);
        }

        /* access modifiers changed from: private */
        public void setTtlExpiryEventToLog(String str) {
            if (str != null) {
                this.ttlExpiryEventToLog_ = str;
                return;
            }
            throw null;
        }

        /* access modifiers changed from: private */
        public void clearTtlExpiryEventToLog() {
            this.ttlExpiryEventToLog_ = getDefaultInstance().getTtlExpiryEventToLog();
        }

        /* access modifiers changed from: private */
        public void setTtlExpiryEventToLogBytes(ByteString byteString) {
            if (byteString != null) {
                checkByteStringIsUtf8(byteString);
                this.ttlExpiryEventToLog_ = byteString.toStringUtf8();
                return;
            }
            throw null;
        }

        public int getOverflowPolicyValue() {
            return this.overflowPolicy_;
        }

        public ExperimentOverflowPolicy getOverflowPolicy() {
            ExperimentOverflowPolicy forNumber = ExperimentOverflowPolicy.forNumber(this.overflowPolicy_);
            return forNumber == null ? ExperimentOverflowPolicy.UNRECOGNIZED : forNumber;
        }

        /* access modifiers changed from: private */
        public void setOverflowPolicyValue(int i) {
            this.overflowPolicy_ = i;
        }

        /* access modifiers changed from: private */
        public void setOverflowPolicy(ExperimentOverflowPolicy experimentOverflowPolicy) {
            if (experimentOverflowPolicy != null) {
                this.overflowPolicy_ = experimentOverflowPolicy.getNumber();
                return;
            }
            throw null;
        }

        /* access modifiers changed from: private */
        public void clearOverflowPolicy() {
            this.overflowPolicy_ = 0;
        }

        public List<ExperimentLite> getOngoingExperimentsList() {
            return this.ongoingExperiments_;
        }

        public List<? extends ExperimentLiteOrBuilder> getOngoingExperimentsOrBuilderList() {
            return this.ongoingExperiments_;
        }

        public int getOngoingExperimentsCount() {
            return this.ongoingExperiments_.size();
        }

        public ExperimentLite getOngoingExperiments(int i) {
            return (ExperimentLite) this.ongoingExperiments_.get(i);
        }

        public ExperimentLiteOrBuilder getOngoingExperimentsOrBuilder(int i) {
            return (ExperimentLiteOrBuilder) this.ongoingExperiments_.get(i);
        }

        private void ensureOngoingExperimentsIsMutable() {
            if (!this.ongoingExperiments_.isModifiable()) {
                this.ongoingExperiments_ = GeneratedMessageLite.mutableCopy(this.ongoingExperiments_);
            }
        }

        /* access modifiers changed from: private */
        public void setOngoingExperiments(int i, ExperimentLite experimentLite) {
            if (experimentLite != null) {
                ensureOngoingExperimentsIsMutable();
                this.ongoingExperiments_.set(i, experimentLite);
                return;
            }
            throw null;
        }

        /* access modifiers changed from: private */
        public void setOngoingExperiments(int i, ExperimentLite.Builder builder) {
            ensureOngoingExperimentsIsMutable();
            this.ongoingExperiments_.set(i, (ExperimentLite) builder.build());
        }

        /* access modifiers changed from: private */
        public void addOngoingExperiments(ExperimentLite experimentLite) {
            if (experimentLite != null) {
                ensureOngoingExperimentsIsMutable();
                this.ongoingExperiments_.add(experimentLite);
                return;
            }
            throw null;
        }

        /* access modifiers changed from: private */
        public void addOngoingExperiments(int i, ExperimentLite experimentLite) {
            if (experimentLite != null) {
                ensureOngoingExperimentsIsMutable();
                this.ongoingExperiments_.add(i, experimentLite);
                return;
            }
            throw null;
        }

        /* access modifiers changed from: private */
        public void addOngoingExperiments(ExperimentLite.Builder builder) {
            ensureOngoingExperimentsIsMutable();
            this.ongoingExperiments_.add((ExperimentLite) builder.build());
        }

        /* access modifiers changed from: private */
        public void addOngoingExperiments(int i, ExperimentLite.Builder builder) {
            ensureOngoingExperimentsIsMutable();
            this.ongoingExperiments_.add(i, (ExperimentLite) builder.build());
        }

        /* access modifiers changed from: private */
        public void addAllOngoingExperiments(Iterable<? extends ExperimentLite> iterable) {
            ensureOngoingExperimentsIsMutable();
            AbstractMessageLite.addAll(iterable, this.ongoingExperiments_);
        }

        /* access modifiers changed from: private */
        public void clearOngoingExperiments() {
            this.ongoingExperiments_ = emptyProtobufList();
        }

        /* access modifiers changed from: private */
        public void removeOngoingExperiments(int i) {
            ensureOngoingExperimentsIsMutable();
            this.ongoingExperiments_.remove(i);
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (!this.experimentId_.isEmpty()) {
                codedOutputStream.writeString(1, getExperimentId());
            }
            if (!this.variantId_.isEmpty()) {
                codedOutputStream.writeString(2, getVariantId());
            }
            long j = this.experimentStartTimeMillis_;
            if (j != 0) {
                codedOutputStream.writeInt64(3, j);
            }
            if (!this.triggerEvent_.isEmpty()) {
                codedOutputStream.writeString(4, getTriggerEvent());
            }
            long j2 = this.triggerTimeoutMillis_;
            if (j2 != 0) {
                codedOutputStream.writeInt64(5, j2);
            }
            long j3 = this.timeToLiveMillis_;
            if (j3 != 0) {
                codedOutputStream.writeInt64(6, j3);
            }
            if (!this.setEventToLog_.isEmpty()) {
                codedOutputStream.writeString(7, getSetEventToLog());
            }
            if (!this.activateEventToLog_.isEmpty()) {
                codedOutputStream.writeString(8, getActivateEventToLog());
            }
            if (!this.clearEventToLog_.isEmpty()) {
                codedOutputStream.writeString(9, getClearEventToLog());
            }
            if (!this.timeoutEventToLog_.isEmpty()) {
                codedOutputStream.writeString(10, getTimeoutEventToLog());
            }
            if (!this.ttlExpiryEventToLog_.isEmpty()) {
                codedOutputStream.writeString(11, getTtlExpiryEventToLog());
            }
            if (this.overflowPolicy_ != ExperimentOverflowPolicy.POLICY_UNSPECIFIED.getNumber()) {
                codedOutputStream.writeEnum(12, this.overflowPolicy_);
            }
            for (int i = 0; i < this.ongoingExperiments_.size(); i++) {
                codedOutputStream.writeMessage(13, (MessageLite) this.ongoingExperiments_.get(i));
            }
        }

        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int computeStringSize = !this.experimentId_.isEmpty() ? CodedOutputStream.computeStringSize(1, getExperimentId()) + 0 : 0;
            if (!this.variantId_.isEmpty()) {
                computeStringSize += CodedOutputStream.computeStringSize(2, getVariantId());
            }
            long j = this.experimentStartTimeMillis_;
            if (j != 0) {
                computeStringSize += CodedOutputStream.computeInt64Size(3, j);
            }
            if (!this.triggerEvent_.isEmpty()) {
                computeStringSize += CodedOutputStream.computeStringSize(4, getTriggerEvent());
            }
            long j2 = this.triggerTimeoutMillis_;
            if (j2 != 0) {
                computeStringSize += CodedOutputStream.computeInt64Size(5, j2);
            }
            long j3 = this.timeToLiveMillis_;
            if (j3 != 0) {
                computeStringSize += CodedOutputStream.computeInt64Size(6, j3);
            }
            if (!this.setEventToLog_.isEmpty()) {
                computeStringSize += CodedOutputStream.computeStringSize(7, getSetEventToLog());
            }
            if (!this.activateEventToLog_.isEmpty()) {
                computeStringSize += CodedOutputStream.computeStringSize(8, getActivateEventToLog());
            }
            if (!this.clearEventToLog_.isEmpty()) {
                computeStringSize += CodedOutputStream.computeStringSize(9, getClearEventToLog());
            }
            if (!this.timeoutEventToLog_.isEmpty()) {
                computeStringSize += CodedOutputStream.computeStringSize(10, getTimeoutEventToLog());
            }
            if (!this.ttlExpiryEventToLog_.isEmpty()) {
                computeStringSize += CodedOutputStream.computeStringSize(11, getTtlExpiryEventToLog());
            }
            if (this.overflowPolicy_ != ExperimentOverflowPolicy.POLICY_UNSPECIFIED.getNumber()) {
                computeStringSize += CodedOutputStream.computeEnumSize(12, this.overflowPolicy_);
            }
            for (int i2 = 0; i2 < this.ongoingExperiments_.size(); i2++) {
                computeStringSize += CodedOutputStream.computeMessageSize(13, (MessageLite) this.ongoingExperiments_.get(i2));
            }
            this.memoizedSerializedSize = computeStringSize;
            return computeStringSize;
        }

        public static ExperimentPayload parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (ExperimentPayload) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static ExperimentPayload parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (ExperimentPayload) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static ExperimentPayload parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (ExperimentPayload) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static ExperimentPayload parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (ExperimentPayload) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static ExperimentPayload parseFrom(InputStream inputStream) throws IOException {
            return (ExperimentPayload) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static ExperimentPayload parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ExperimentPayload) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static ExperimentPayload parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (ExperimentPayload) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static ExperimentPayload parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ExperimentPayload) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static ExperimentPayload parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (ExperimentPayload) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static ExperimentPayload parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ExperimentPayload) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(ExperimentPayload experimentPayload) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(experimentPayload);
        }

        /* compiled from: com.google.firebase:firebase-abt@@19.0.1 */
        public static final class Builder extends GeneratedMessageLite.Builder<ExperimentPayload, Builder> implements ExperimentPayloadOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 r1) {
                this();
            }

            private Builder() {
                super(ExperimentPayload.DEFAULT_INSTANCE);
            }

            public String getExperimentId() {
                return ((ExperimentPayload) this.instance).getExperimentId();
            }

            public ByteString getExperimentIdBytes() {
                return ((ExperimentPayload) this.instance).getExperimentIdBytes();
            }

            public Builder setExperimentId(String str) {
                copyOnWrite();
                ((ExperimentPayload) this.instance).setExperimentId(str);
                return this;
            }

            public Builder clearExperimentId() {
                copyOnWrite();
                ((ExperimentPayload) this.instance).clearExperimentId();
                return this;
            }

            public Builder setExperimentIdBytes(ByteString byteString) {
                copyOnWrite();
                ((ExperimentPayload) this.instance).setExperimentIdBytes(byteString);
                return this;
            }

            public String getVariantId() {
                return ((ExperimentPayload) this.instance).getVariantId();
            }

            public ByteString getVariantIdBytes() {
                return ((ExperimentPayload) this.instance).getVariantIdBytes();
            }

            public Builder setVariantId(String str) {
                copyOnWrite();
                ((ExperimentPayload) this.instance).setVariantId(str);
                return this;
            }

            public Builder clearVariantId() {
                copyOnWrite();
                ((ExperimentPayload) this.instance).clearVariantId();
                return this;
            }

            public Builder setVariantIdBytes(ByteString byteString) {
                copyOnWrite();
                ((ExperimentPayload) this.instance).setVariantIdBytes(byteString);
                return this;
            }

            public long getExperimentStartTimeMillis() {
                return ((ExperimentPayload) this.instance).getExperimentStartTimeMillis();
            }

            public Builder setExperimentStartTimeMillis(long j) {
                copyOnWrite();
                ((ExperimentPayload) this.instance).setExperimentStartTimeMillis(j);
                return this;
            }

            public Builder clearExperimentStartTimeMillis() {
                copyOnWrite();
                ((ExperimentPayload) this.instance).clearExperimentStartTimeMillis();
                return this;
            }

            public String getTriggerEvent() {
                return ((ExperimentPayload) this.instance).getTriggerEvent();
            }

            public ByteString getTriggerEventBytes() {
                return ((ExperimentPayload) this.instance).getTriggerEventBytes();
            }

            public Builder setTriggerEvent(String str) {
                copyOnWrite();
                ((ExperimentPayload) this.instance).setTriggerEvent(str);
                return this;
            }

            public Builder clearTriggerEvent() {
                copyOnWrite();
                ((ExperimentPayload) this.instance).clearTriggerEvent();
                return this;
            }

            public Builder setTriggerEventBytes(ByteString byteString) {
                copyOnWrite();
                ((ExperimentPayload) this.instance).setTriggerEventBytes(byteString);
                return this;
            }

            public long getTriggerTimeoutMillis() {
                return ((ExperimentPayload) this.instance).getTriggerTimeoutMillis();
            }

            public Builder setTriggerTimeoutMillis(long j) {
                copyOnWrite();
                ((ExperimentPayload) this.instance).setTriggerTimeoutMillis(j);
                return this;
            }

            public Builder clearTriggerTimeoutMillis() {
                copyOnWrite();
                ((ExperimentPayload) this.instance).clearTriggerTimeoutMillis();
                return this;
            }

            public long getTimeToLiveMillis() {
                return ((ExperimentPayload) this.instance).getTimeToLiveMillis();
            }

            public Builder setTimeToLiveMillis(long j) {
                copyOnWrite();
                ((ExperimentPayload) this.instance).setTimeToLiveMillis(j);
                return this;
            }

            public Builder clearTimeToLiveMillis() {
                copyOnWrite();
                ((ExperimentPayload) this.instance).clearTimeToLiveMillis();
                return this;
            }

            public String getSetEventToLog() {
                return ((ExperimentPayload) this.instance).getSetEventToLog();
            }

            public ByteString getSetEventToLogBytes() {
                return ((ExperimentPayload) this.instance).getSetEventToLogBytes();
            }

            public Builder setSetEventToLog(String str) {
                copyOnWrite();
                ((ExperimentPayload) this.instance).setSetEventToLog(str);
                return this;
            }

            public Builder clearSetEventToLog() {
                copyOnWrite();
                ((ExperimentPayload) this.instance).clearSetEventToLog();
                return this;
            }

            public Builder setSetEventToLogBytes(ByteString byteString) {
                copyOnWrite();
                ((ExperimentPayload) this.instance).setSetEventToLogBytes(byteString);
                return this;
            }

            public String getActivateEventToLog() {
                return ((ExperimentPayload) this.instance).getActivateEventToLog();
            }

            public ByteString getActivateEventToLogBytes() {
                return ((ExperimentPayload) this.instance).getActivateEventToLogBytes();
            }

            public Builder setActivateEventToLog(String str) {
                copyOnWrite();
                ((ExperimentPayload) this.instance).setActivateEventToLog(str);
                return this;
            }

            public Builder clearActivateEventToLog() {
                copyOnWrite();
                ((ExperimentPayload) this.instance).clearActivateEventToLog();
                return this;
            }

            public Builder setActivateEventToLogBytes(ByteString byteString) {
                copyOnWrite();
                ((ExperimentPayload) this.instance).setActivateEventToLogBytes(byteString);
                return this;
            }

            public String getClearEventToLog() {
                return ((ExperimentPayload) this.instance).getClearEventToLog();
            }

            public ByteString getClearEventToLogBytes() {
                return ((ExperimentPayload) this.instance).getClearEventToLogBytes();
            }

            public Builder setClearEventToLog(String str) {
                copyOnWrite();
                ((ExperimentPayload) this.instance).setClearEventToLog(str);
                return this;
            }

            public Builder clearClearEventToLog() {
                copyOnWrite();
                ((ExperimentPayload) this.instance).clearClearEventToLog();
                return this;
            }

            public Builder setClearEventToLogBytes(ByteString byteString) {
                copyOnWrite();
                ((ExperimentPayload) this.instance).setClearEventToLogBytes(byteString);
                return this;
            }

            public String getTimeoutEventToLog() {
                return ((ExperimentPayload) this.instance).getTimeoutEventToLog();
            }

            public ByteString getTimeoutEventToLogBytes() {
                return ((ExperimentPayload) this.instance).getTimeoutEventToLogBytes();
            }

            public Builder setTimeoutEventToLog(String str) {
                copyOnWrite();
                ((ExperimentPayload) this.instance).setTimeoutEventToLog(str);
                return this;
            }

            public Builder clearTimeoutEventToLog() {
                copyOnWrite();
                ((ExperimentPayload) this.instance).clearTimeoutEventToLog();
                return this;
            }

            public Builder setTimeoutEventToLogBytes(ByteString byteString) {
                copyOnWrite();
                ((ExperimentPayload) this.instance).setTimeoutEventToLogBytes(byteString);
                return this;
            }

            public String getTtlExpiryEventToLog() {
                return ((ExperimentPayload) this.instance).getTtlExpiryEventToLog();
            }

            public ByteString getTtlExpiryEventToLogBytes() {
                return ((ExperimentPayload) this.instance).getTtlExpiryEventToLogBytes();
            }

            public Builder setTtlExpiryEventToLog(String str) {
                copyOnWrite();
                ((ExperimentPayload) this.instance).setTtlExpiryEventToLog(str);
                return this;
            }

            public Builder clearTtlExpiryEventToLog() {
                copyOnWrite();
                ((ExperimentPayload) this.instance).clearTtlExpiryEventToLog();
                return this;
            }

            public Builder setTtlExpiryEventToLogBytes(ByteString byteString) {
                copyOnWrite();
                ((ExperimentPayload) this.instance).setTtlExpiryEventToLogBytes(byteString);
                return this;
            }

            public int getOverflowPolicyValue() {
                return ((ExperimentPayload) this.instance).getOverflowPolicyValue();
            }

            public Builder setOverflowPolicyValue(int i) {
                copyOnWrite();
                ((ExperimentPayload) this.instance).setOverflowPolicyValue(i);
                return this;
            }

            public ExperimentOverflowPolicy getOverflowPolicy() {
                return ((ExperimentPayload) this.instance).getOverflowPolicy();
            }

            public Builder setOverflowPolicy(ExperimentOverflowPolicy experimentOverflowPolicy) {
                copyOnWrite();
                ((ExperimentPayload) this.instance).setOverflowPolicy(experimentOverflowPolicy);
                return this;
            }

            public Builder clearOverflowPolicy() {
                copyOnWrite();
                ((ExperimentPayload) this.instance).clearOverflowPolicy();
                return this;
            }

            public List<ExperimentLite> getOngoingExperimentsList() {
                return Collections.unmodifiableList(((ExperimentPayload) this.instance).getOngoingExperimentsList());
            }

            public int getOngoingExperimentsCount() {
                return ((ExperimentPayload) this.instance).getOngoingExperimentsCount();
            }

            public ExperimentLite getOngoingExperiments(int i) {
                return ((ExperimentPayload) this.instance).getOngoingExperiments(i);
            }

            public Builder setOngoingExperiments(int i, ExperimentLite experimentLite) {
                copyOnWrite();
                ((ExperimentPayload) this.instance).setOngoingExperiments(i, experimentLite);
                return this;
            }

            public Builder setOngoingExperiments(int i, ExperimentLite.Builder builder) {
                copyOnWrite();
                ((ExperimentPayload) this.instance).setOngoingExperiments(i, builder);
                return this;
            }

            public Builder addOngoingExperiments(ExperimentLite experimentLite) {
                copyOnWrite();
                ((ExperimentPayload) this.instance).addOngoingExperiments(experimentLite);
                return this;
            }

            public Builder addOngoingExperiments(int i, ExperimentLite experimentLite) {
                copyOnWrite();
                ((ExperimentPayload) this.instance).addOngoingExperiments(i, experimentLite);
                return this;
            }

            public Builder addOngoingExperiments(ExperimentLite.Builder builder) {
                copyOnWrite();
                ((ExperimentPayload) this.instance).addOngoingExperiments(builder);
                return this;
            }

            public Builder addOngoingExperiments(int i, ExperimentLite.Builder builder) {
                copyOnWrite();
                ((ExperimentPayload) this.instance).addOngoingExperiments(i, builder);
                return this;
            }

            public Builder addAllOngoingExperiments(Iterable<? extends ExperimentLite> iterable) {
                copyOnWrite();
                ((ExperimentPayload) this.instance).addAllOngoingExperiments(iterable);
                return this;
            }

            public Builder clearOngoingExperiments() {
                copyOnWrite();
                ((ExperimentPayload) this.instance).clearOngoingExperiments();
                return this;
            }

            public Builder removeOngoingExperiments(int i) {
                copyOnWrite();
                ((ExperimentPayload) this.instance).removeOngoingExperiments(i);
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            boolean z = false;
            switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
                case 1:
                    return new ExperimentPayload();
                case 2:
                    return DEFAULT_INSTANCE;
                case 3:
                    this.ongoingExperiments_.makeImmutable();
                    return null;
                case 4:
                    return new Builder((AnonymousClass1) null);
                case 5:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                    ExperimentPayload experimentPayload = (ExperimentPayload) obj2;
                    this.experimentId_ = visitor.visitString(!this.experimentId_.isEmpty(), this.experimentId_, !experimentPayload.experimentId_.isEmpty(), experimentPayload.experimentId_);
                    this.variantId_ = visitor.visitString(!this.variantId_.isEmpty(), this.variantId_, !experimentPayload.variantId_.isEmpty(), experimentPayload.variantId_);
                    this.experimentStartTimeMillis_ = visitor.visitLong(this.experimentStartTimeMillis_ != 0, this.experimentStartTimeMillis_, experimentPayload.experimentStartTimeMillis_ != 0, experimentPayload.experimentStartTimeMillis_);
                    this.triggerEvent_ = visitor.visitString(!this.triggerEvent_.isEmpty(), this.triggerEvent_, !experimentPayload.triggerEvent_.isEmpty(), experimentPayload.triggerEvent_);
                    this.triggerTimeoutMillis_ = visitor.visitLong(this.triggerTimeoutMillis_ != 0, this.triggerTimeoutMillis_, experimentPayload.triggerTimeoutMillis_ != 0, experimentPayload.triggerTimeoutMillis_);
                    this.timeToLiveMillis_ = visitor.visitLong(this.timeToLiveMillis_ != 0, this.timeToLiveMillis_, experimentPayload.timeToLiveMillis_ != 0, experimentPayload.timeToLiveMillis_);
                    this.setEventToLog_ = visitor.visitString(!this.setEventToLog_.isEmpty(), this.setEventToLog_, !experimentPayload.setEventToLog_.isEmpty(), experimentPayload.setEventToLog_);
                    this.activateEventToLog_ = visitor.visitString(!this.activateEventToLog_.isEmpty(), this.activateEventToLog_, !experimentPayload.activateEventToLog_.isEmpty(), experimentPayload.activateEventToLog_);
                    this.clearEventToLog_ = visitor.visitString(!this.clearEventToLog_.isEmpty(), this.clearEventToLog_, !experimentPayload.clearEventToLog_.isEmpty(), experimentPayload.clearEventToLog_);
                    this.timeoutEventToLog_ = visitor.visitString(!this.timeoutEventToLog_.isEmpty(), this.timeoutEventToLog_, !experimentPayload.timeoutEventToLog_.isEmpty(), experimentPayload.timeoutEventToLog_);
                    this.ttlExpiryEventToLog_ = visitor.visitString(!this.ttlExpiryEventToLog_.isEmpty(), this.ttlExpiryEventToLog_, !experimentPayload.ttlExpiryEventToLog_.isEmpty(), experimentPayload.ttlExpiryEventToLog_);
                    boolean z2 = this.overflowPolicy_ != 0;
                    int i = this.overflowPolicy_;
                    if (experimentPayload.overflowPolicy_ != 0) {
                        z = true;
                    }
                    this.overflowPolicy_ = visitor.visitInt(z2, i, z, experimentPayload.overflowPolicy_);
                    this.ongoingExperiments_ = visitor.visitList(this.ongoingExperiments_, experimentPayload.ongoingExperiments_);
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                        this.bitField0_ |= experimentPayload.bitField0_;
                    }
                    return this;
                case 6:
                    CodedInputStream codedInputStream = (CodedInputStream) obj;
                    ExtensionRegistryLite extensionRegistryLite = (ExtensionRegistryLite) obj2;
                    while (!z) {
                        try {
                            int readTag = codedInputStream.readTag();
                            switch (readTag) {
                                case 0:
                                    z = true;
                                    break;
                                case 10:
                                    this.experimentId_ = codedInputStream.readStringRequireUtf8();
                                    break;
                                case 18:
                                    this.variantId_ = codedInputStream.readStringRequireUtf8();
                                    break;
                                case 24:
                                    this.experimentStartTimeMillis_ = codedInputStream.readInt64();
                                    break;
                                case 34:
                                    this.triggerEvent_ = codedInputStream.readStringRequireUtf8();
                                    break;
                                case 40:
                                    this.triggerTimeoutMillis_ = codedInputStream.readInt64();
                                    break;
                                case 48:
                                    this.timeToLiveMillis_ = codedInputStream.readInt64();
                                    break;
                                case 58:
                                    this.setEventToLog_ = codedInputStream.readStringRequireUtf8();
                                    break;
                                case 66:
                                    this.activateEventToLog_ = codedInputStream.readStringRequireUtf8();
                                    break;
                                case 74:
                                    this.clearEventToLog_ = codedInputStream.readStringRequireUtf8();
                                    break;
                                case 82:
                                    this.timeoutEventToLog_ = codedInputStream.readStringRequireUtf8();
                                    break;
                                case 90:
                                    this.ttlExpiryEventToLog_ = codedInputStream.readStringRequireUtf8();
                                    break;
                                case 96:
                                    this.overflowPolicy_ = codedInputStream.readEnum();
                                    break;
                                case 106:
                                    if (!this.ongoingExperiments_.isModifiable()) {
                                        this.ongoingExperiments_ = GeneratedMessageLite.mutableCopy(this.ongoingExperiments_);
                                    }
                                    this.ongoingExperiments_.add((ExperimentLite) codedInputStream.readMessage(ExperimentLite.parser(), extensionRegistryLite));
                                    break;
                                default:
                                    if (codedInputStream.skipField(readTag)) {
                                        break;
                                    }
                                    z = true;
                                    break;
                            }
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
                        synchronized (ExperimentPayload.class) {
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
            ExperimentPayload experimentPayload = new ExperimentPayload();
            DEFAULT_INSTANCE = experimentPayload;
            experimentPayload.makeImmutable();
        }

        public static ExperimentPayload getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<ExperimentPayload> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }
}
