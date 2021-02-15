package com.google.api;

import com.google.api.AuthorizationConfig;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;

/* compiled from: com.google.firebase:protolite-well-known-types@@16.0.1 */
public final class Experimental extends GeneratedMessageLite<Experimental, Builder> implements ExperimentalOrBuilder {
    public static final int AUTHORIZATION_FIELD_NUMBER = 8;
    /* access modifiers changed from: private */
    public static final Experimental DEFAULT_INSTANCE;
    private static volatile Parser<Experimental> PARSER;
    private AuthorizationConfig authorization_;

    private Experimental() {
    }

    public boolean hasAuthorization() {
        return this.authorization_ != null;
    }

    public AuthorizationConfig getAuthorization() {
        AuthorizationConfig authorizationConfig = this.authorization_;
        return authorizationConfig == null ? AuthorizationConfig.getDefaultInstance() : authorizationConfig;
    }

    /* access modifiers changed from: private */
    public void setAuthorization(AuthorizationConfig authorizationConfig) {
        if (authorizationConfig != null) {
            this.authorization_ = authorizationConfig;
            return;
        }
        throw null;
    }

    /* access modifiers changed from: private */
    public void setAuthorization(AuthorizationConfig.Builder builder) {
        this.authorization_ = (AuthorizationConfig) builder.build();
    }

    /* access modifiers changed from: private */
    public void mergeAuthorization(AuthorizationConfig authorizationConfig) {
        AuthorizationConfig authorizationConfig2 = this.authorization_;
        if (authorizationConfig2 == null || authorizationConfig2 == AuthorizationConfig.getDefaultInstance()) {
            this.authorization_ = authorizationConfig;
        } else {
            this.authorization_ = (AuthorizationConfig) ((AuthorizationConfig.Builder) AuthorizationConfig.newBuilder(this.authorization_).mergeFrom(authorizationConfig)).buildPartial();
        }
    }

    /* access modifiers changed from: private */
    public void clearAuthorization() {
        this.authorization_ = null;
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (this.authorization_ != null) {
            codedOutputStream.writeMessage(8, getAuthorization());
        }
    }

    public int getSerializedSize() {
        int i = this.memoizedSerializedSize;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        if (this.authorization_ != null) {
            i2 = 0 + CodedOutputStream.computeMessageSize(8, getAuthorization());
        }
        this.memoizedSerializedSize = i2;
        return i2;
    }

    public static Experimental parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (Experimental) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static Experimental parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Experimental) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static Experimental parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (Experimental) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static Experimental parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Experimental) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static Experimental parseFrom(InputStream inputStream) throws IOException {
        return (Experimental) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static Experimental parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Experimental) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static Experimental parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (Experimental) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static Experimental parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Experimental) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static Experimental parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (Experimental) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static Experimental parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Experimental) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(Experimental experimental) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(experimental);
    }

    /* compiled from: com.google.firebase:protolite-well-known-types@@16.0.1 */
    public static final class Builder extends GeneratedMessageLite.Builder<Experimental, Builder> implements ExperimentalOrBuilder {
        /* synthetic */ Builder(AnonymousClass1 r1) {
            this();
        }

        private Builder() {
            super(Experimental.DEFAULT_INSTANCE);
        }

        public boolean hasAuthorization() {
            return ((Experimental) this.instance).hasAuthorization();
        }

        public AuthorizationConfig getAuthorization() {
            return ((Experimental) this.instance).getAuthorization();
        }

        public Builder setAuthorization(AuthorizationConfig authorizationConfig) {
            copyOnWrite();
            ((Experimental) this.instance).setAuthorization(authorizationConfig);
            return this;
        }

        public Builder setAuthorization(AuthorizationConfig.Builder builder) {
            copyOnWrite();
            ((Experimental) this.instance).setAuthorization(builder);
            return this;
        }

        public Builder mergeAuthorization(AuthorizationConfig authorizationConfig) {
            copyOnWrite();
            ((Experimental) this.instance).mergeAuthorization(authorizationConfig);
            return this;
        }

        public Builder clearAuthorization() {
            copyOnWrite();
            ((Experimental) this.instance).clearAuthorization();
            return this;
        }
    }

    /* renamed from: com.google.api.Experimental$1  reason: invalid class name */
    /* compiled from: com.google.firebase:protolite-well-known-types@@16.0.1 */
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.api.Experimental.AnonymousClass1.<clinit>():void");
        }
    }

    /* access modifiers changed from: protected */
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
            case 1:
                return new Experimental();
            case 2:
                return DEFAULT_INSTANCE;
            case 3:
                return null;
            case 4:
                return new Builder((AnonymousClass1) null);
            case 5:
                this.authorization_ = (AuthorizationConfig) ((GeneratedMessageLite.Visitor) obj).visitMessage(this.authorization_, ((Experimental) obj2).authorization_);
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
                            if (readTag == 66) {
                                AuthorizationConfig.Builder builder = this.authorization_ != null ? (AuthorizationConfig.Builder) this.authorization_.toBuilder() : null;
                                AuthorizationConfig authorizationConfig = (AuthorizationConfig) codedInputStream.readMessage(AuthorizationConfig.parser(), extensionRegistryLite);
                                this.authorization_ = authorizationConfig;
                                if (builder != null) {
                                    builder.mergeFrom(authorizationConfig);
                                    this.authorization_ = (AuthorizationConfig) builder.buildPartial();
                                }
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
                    synchronized (Experimental.class) {
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
        Experimental experimental = new Experimental();
        DEFAULT_INSTANCE = experimental;
        experimental.makeImmutable();
    }

    public static Experimental getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Experimental> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}