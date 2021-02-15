package com.google.android.gms.config.proto;

import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;

/* compiled from: com.google.firebase:firebase-config@@19.0.3 */
public final class Logs {

    /* compiled from: com.google.firebase:firebase-config@@19.0.3 */
    public interface AndroidConfigFetchProtoOrBuilder extends MessageLiteOrBuilder {
        ConfigFetchReason getReason();

        boolean hasReason();
    }

    /* compiled from: com.google.firebase:firebase-config@@19.0.3 */
    public interface ConfigFetchReasonOrBuilder extends MessageLiteOrBuilder {
        ConfigFetchReason.AndroidConfigFetchType getType();

        boolean hasType();
    }

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }

    private Logs() {
    }

    /* compiled from: com.google.firebase:firebase-config@@19.0.3 */
    public static final class ConfigFetchReason extends GeneratedMessageLite<ConfigFetchReason, Builder> implements ConfigFetchReasonOrBuilder {
        /* access modifiers changed from: private */
        public static final ConfigFetchReason DEFAULT_INSTANCE;
        private static volatile Parser<ConfigFetchReason> PARSER = null;
        public static final int TYPE_FIELD_NUMBER = 1;
        private int bitField0_;
        private int type_;

        private ConfigFetchReason() {
        }

        /* compiled from: com.google.firebase:firebase-config@@19.0.3 */
        public enum AndroidConfigFetchType implements Internal.EnumLite {
            UNKNOWN(0),
            SCHEDULED(1),
            BOOT_COMPLETED(2),
            PACKAGE_ADDED(3),
            PACKAGE_REMOVED(4),
            GMS_CORE_UPDATED(5),
            SECRET_CODE(6);
            
            public static final int BOOT_COMPLETED_VALUE = 2;
            public static final int GMS_CORE_UPDATED_VALUE = 5;
            public static final int PACKAGE_ADDED_VALUE = 3;
            public static final int PACKAGE_REMOVED_VALUE = 4;
            public static final int SCHEDULED_VALUE = 1;
            public static final int SECRET_CODE_VALUE = 6;
            public static final int UNKNOWN_VALUE = 0;
            private static final Internal.EnumLiteMap<AndroidConfigFetchType> internalValueMap = null;
            private final int value;

            static {
                internalValueMap = new Internal.EnumLiteMap<AndroidConfigFetchType>() {
                    public AndroidConfigFetchType findValueByNumber(int i) {
                        return AndroidConfigFetchType.forNumber(i);
                    }
                };
            }

            public final int getNumber() {
                return this.value;
            }

            @Deprecated
            public static AndroidConfigFetchType valueOf(int i) {
                return forNumber(i);
            }

            public static AndroidConfigFetchType forNumber(int i) {
                switch (i) {
                    case 0:
                        return UNKNOWN;
                    case 1:
                        return SCHEDULED;
                    case 2:
                        return BOOT_COMPLETED;
                    case 3:
                        return PACKAGE_ADDED;
                    case 4:
                        return PACKAGE_REMOVED;
                    case 5:
                        return GMS_CORE_UPDATED;
                    case 6:
                        return SECRET_CODE;
                    default:
                        return null;
                }
            }

            public static Internal.EnumLiteMap<AndroidConfigFetchType> internalGetValueMap() {
                return internalValueMap;
            }

            private AndroidConfigFetchType(int i) {
                this.value = i;
            }
        }

        public boolean hasType() {
            return (this.bitField0_ & 1) == 1;
        }

        public AndroidConfigFetchType getType() {
            AndroidConfigFetchType forNumber = AndroidConfigFetchType.forNumber(this.type_);
            return forNumber == null ? AndroidConfigFetchType.UNKNOWN : forNumber;
        }

        /* access modifiers changed from: private */
        public void setType(AndroidConfigFetchType androidConfigFetchType) {
            if (androidConfigFetchType != null) {
                this.bitField0_ |= 1;
                this.type_ = androidConfigFetchType.getNumber();
                return;
            }
            throw null;
        }

        /* access modifiers changed from: private */
        public void clearType() {
            this.bitField0_ &= -2;
            this.type_ = 0;
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                codedOutputStream.writeEnum(1, this.type_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            if ((this.bitField0_ & 1) == 1) {
                i2 = 0 + CodedOutputStream.computeEnumSize(1, this.type_);
            }
            int serializedSize = i2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        public static ConfigFetchReason parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (ConfigFetchReason) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static ConfigFetchReason parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (ConfigFetchReason) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static ConfigFetchReason parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (ConfigFetchReason) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static ConfigFetchReason parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (ConfigFetchReason) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static ConfigFetchReason parseFrom(InputStream inputStream) throws IOException {
            return (ConfigFetchReason) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static ConfigFetchReason parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ConfigFetchReason) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static ConfigFetchReason parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (ConfigFetchReason) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static ConfigFetchReason parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ConfigFetchReason) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static ConfigFetchReason parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (ConfigFetchReason) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static ConfigFetchReason parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ConfigFetchReason) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(ConfigFetchReason configFetchReason) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(configFetchReason);
        }

        /* compiled from: com.google.firebase:firebase-config@@19.0.3 */
        public static final class Builder extends GeneratedMessageLite.Builder<ConfigFetchReason, Builder> implements ConfigFetchReasonOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 r1) {
                this();
            }

            private Builder() {
                super(ConfigFetchReason.DEFAULT_INSTANCE);
            }

            public boolean hasType() {
                return ((ConfigFetchReason) this.instance).hasType();
            }

            public AndroidConfigFetchType getType() {
                return ((ConfigFetchReason) this.instance).getType();
            }

            public Builder setType(AndroidConfigFetchType androidConfigFetchType) {
                copyOnWrite();
                ((ConfigFetchReason) this.instance).setType(androidConfigFetchType);
                return this;
            }

            public Builder clearType() {
                copyOnWrite();
                ((ConfigFetchReason) this.instance).clearType();
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
                case 1:
                    return new ConfigFetchReason();
                case 2:
                    return DEFAULT_INSTANCE;
                case 3:
                    return null;
                case 4:
                    return new Builder((AnonymousClass1) null);
                case 5:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                    ConfigFetchReason configFetchReason = (ConfigFetchReason) obj2;
                    this.type_ = visitor.visitInt(hasType(), this.type_, configFetchReason.hasType(), configFetchReason.type_);
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                        this.bitField0_ |= configFetchReason.bitField0_;
                    }
                    return this;
                case 6:
                    CodedInputStream codedInputStream = (CodedInputStream) obj;
                    ExtensionRegistryLite extensionRegistryLite = (ExtensionRegistryLite) obj2;
                    boolean z = false;
                    while (!z) {
                        try {
                            int readTag = codedInputStream.readTag();
                            if (readTag != 0) {
                                if (readTag == 8) {
                                    int readEnum = codedInputStream.readEnum();
                                    if (AndroidConfigFetchType.forNumber(readEnum) == null) {
                                        super.mergeVarintField(1, readEnum);
                                    } else {
                                        this.bitField0_ = 1 | this.bitField0_;
                                        this.type_ = readEnum;
                                    }
                                } else if (!parseUnknownField(readTag, codedInputStream)) {
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
                        synchronized (ConfigFetchReason.class) {
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
            ConfigFetchReason configFetchReason = new ConfigFetchReason();
            DEFAULT_INSTANCE = configFetchReason;
            configFetchReason.makeImmutable();
        }

        public static ConfigFetchReason getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<ConfigFetchReason> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    /* renamed from: com.google.android.gms.config.proto.Logs$1  reason: invalid class name */
    /* compiled from: com.google.firebase:firebase-config@@19.0.3 */
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.config.proto.Logs.AnonymousClass1.<clinit>():void");
        }
    }

    /* compiled from: com.google.firebase:firebase-config@@19.0.3 */
    public static final class AndroidConfigFetchProto extends GeneratedMessageLite<AndroidConfigFetchProto, Builder> implements AndroidConfigFetchProtoOrBuilder {
        /* access modifiers changed from: private */
        public static final AndroidConfigFetchProto DEFAULT_INSTANCE;
        private static volatile Parser<AndroidConfigFetchProto> PARSER = null;
        public static final int REASON_FIELD_NUMBER = 1;
        private int bitField0_;
        private ConfigFetchReason reason_;

        private AndroidConfigFetchProto() {
        }

        public boolean hasReason() {
            return (this.bitField0_ & 1) == 1;
        }

        public ConfigFetchReason getReason() {
            ConfigFetchReason configFetchReason = this.reason_;
            return configFetchReason == null ? ConfigFetchReason.getDefaultInstance() : configFetchReason;
        }

        /* access modifiers changed from: private */
        public void setReason(ConfigFetchReason configFetchReason) {
            if (configFetchReason != null) {
                this.reason_ = configFetchReason;
                this.bitField0_ |= 1;
                return;
            }
            throw null;
        }

        /* access modifiers changed from: private */
        public void setReason(ConfigFetchReason.Builder builder) {
            this.reason_ = (ConfigFetchReason) builder.build();
            this.bitField0_ |= 1;
        }

        /* access modifiers changed from: private */
        public void mergeReason(ConfigFetchReason configFetchReason) {
            ConfigFetchReason configFetchReason2 = this.reason_;
            if (configFetchReason2 == null || configFetchReason2 == ConfigFetchReason.getDefaultInstance()) {
                this.reason_ = configFetchReason;
            } else {
                this.reason_ = (ConfigFetchReason) ((ConfigFetchReason.Builder) ConfigFetchReason.newBuilder(this.reason_).mergeFrom(configFetchReason)).buildPartial();
            }
            this.bitField0_ |= 1;
        }

        /* access modifiers changed from: private */
        public void clearReason() {
            this.reason_ = null;
            this.bitField0_ &= -2;
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                codedOutputStream.writeMessage(1, getReason());
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            if ((this.bitField0_ & 1) == 1) {
                i2 = 0 + CodedOutputStream.computeMessageSize(1, getReason());
            }
            int serializedSize = i2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        public static AndroidConfigFetchProto parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (AndroidConfigFetchProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static AndroidConfigFetchProto parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (AndroidConfigFetchProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static AndroidConfigFetchProto parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (AndroidConfigFetchProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static AndroidConfigFetchProto parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (AndroidConfigFetchProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static AndroidConfigFetchProto parseFrom(InputStream inputStream) throws IOException {
            return (AndroidConfigFetchProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static AndroidConfigFetchProto parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (AndroidConfigFetchProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static AndroidConfigFetchProto parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (AndroidConfigFetchProto) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static AndroidConfigFetchProto parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (AndroidConfigFetchProto) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static AndroidConfigFetchProto parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (AndroidConfigFetchProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static AndroidConfigFetchProto parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (AndroidConfigFetchProto) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(AndroidConfigFetchProto androidConfigFetchProto) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(androidConfigFetchProto);
        }

        /* compiled from: com.google.firebase:firebase-config@@19.0.3 */
        public static final class Builder extends GeneratedMessageLite.Builder<AndroidConfigFetchProto, Builder> implements AndroidConfigFetchProtoOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 r1) {
                this();
            }

            private Builder() {
                super(AndroidConfigFetchProto.DEFAULT_INSTANCE);
            }

            public boolean hasReason() {
                return ((AndroidConfigFetchProto) this.instance).hasReason();
            }

            public ConfigFetchReason getReason() {
                return ((AndroidConfigFetchProto) this.instance).getReason();
            }

            public Builder setReason(ConfigFetchReason configFetchReason) {
                copyOnWrite();
                ((AndroidConfigFetchProto) this.instance).setReason(configFetchReason);
                return this;
            }

            public Builder setReason(ConfigFetchReason.Builder builder) {
                copyOnWrite();
                ((AndroidConfigFetchProto) this.instance).setReason(builder);
                return this;
            }

            public Builder mergeReason(ConfigFetchReason configFetchReason) {
                copyOnWrite();
                ((AndroidConfigFetchProto) this.instance).mergeReason(configFetchReason);
                return this;
            }

            public Builder clearReason() {
                copyOnWrite();
                ((AndroidConfigFetchProto) this.instance).clearReason();
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
                case 1:
                    return new AndroidConfigFetchProto();
                case 2:
                    return DEFAULT_INSTANCE;
                case 3:
                    return null;
                case 4:
                    return new Builder((AnonymousClass1) null);
                case 5:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                    AndroidConfigFetchProto androidConfigFetchProto = (AndroidConfigFetchProto) obj2;
                    this.reason_ = (ConfigFetchReason) visitor.visitMessage(this.reason_, androidConfigFetchProto.reason_);
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                        this.bitField0_ |= androidConfigFetchProto.bitField0_;
                    }
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
                                    ConfigFetchReason.Builder builder = (this.bitField0_ & 1) == 1 ? (ConfigFetchReason.Builder) this.reason_.toBuilder() : null;
                                    ConfigFetchReason configFetchReason = (ConfigFetchReason) codedInputStream.readMessage(ConfigFetchReason.parser(), extensionRegistryLite);
                                    this.reason_ = configFetchReason;
                                    if (builder != null) {
                                        builder.mergeFrom(configFetchReason);
                                        this.reason_ = (ConfigFetchReason) builder.buildPartial();
                                    }
                                    this.bitField0_ |= 1;
                                } else if (!parseUnknownField(readTag, codedInputStream)) {
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
                        synchronized (AndroidConfigFetchProto.class) {
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
            AndroidConfigFetchProto androidConfigFetchProto = new AndroidConfigFetchProto();
            DEFAULT_INSTANCE = androidConfigFetchProto;
            androidConfigFetchProto.makeImmutable();
        }

        public static AndroidConfigFetchProto getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<AndroidConfigFetchProto> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }
}
