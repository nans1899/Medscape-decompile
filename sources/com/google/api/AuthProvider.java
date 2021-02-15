package com.google.api;

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
public final class AuthProvider extends GeneratedMessageLite<AuthProvider, Builder> implements AuthProviderOrBuilder {
    public static final int AUDIENCES_FIELD_NUMBER = 4;
    public static final int AUTHORIZATION_URL_FIELD_NUMBER = 5;
    /* access modifiers changed from: private */
    public static final AuthProvider DEFAULT_INSTANCE;
    public static final int ID_FIELD_NUMBER = 1;
    public static final int ISSUER_FIELD_NUMBER = 2;
    public static final int JWKS_URI_FIELD_NUMBER = 3;
    private static volatile Parser<AuthProvider> PARSER;
    private String audiences_ = "";
    private String authorizationUrl_ = "";
    private String id_ = "";
    private String issuer_ = "";
    private String jwksUri_ = "";

    private AuthProvider() {
    }

    public String getId() {
        return this.id_;
    }

    public ByteString getIdBytes() {
        return ByteString.copyFromUtf8(this.id_);
    }

    /* access modifiers changed from: private */
    public void setId(String str) {
        if (str != null) {
            this.id_ = str;
            return;
        }
        throw null;
    }

    /* access modifiers changed from: private */
    public void clearId() {
        this.id_ = getDefaultInstance().getId();
    }

    /* access modifiers changed from: private */
    public void setIdBytes(ByteString byteString) {
        if (byteString != null) {
            checkByteStringIsUtf8(byteString);
            this.id_ = byteString.toStringUtf8();
            return;
        }
        throw null;
    }

    public String getIssuer() {
        return this.issuer_;
    }

    public ByteString getIssuerBytes() {
        return ByteString.copyFromUtf8(this.issuer_);
    }

    /* access modifiers changed from: private */
    public void setIssuer(String str) {
        if (str != null) {
            this.issuer_ = str;
            return;
        }
        throw null;
    }

    /* access modifiers changed from: private */
    public void clearIssuer() {
        this.issuer_ = getDefaultInstance().getIssuer();
    }

    /* access modifiers changed from: private */
    public void setIssuerBytes(ByteString byteString) {
        if (byteString != null) {
            checkByteStringIsUtf8(byteString);
            this.issuer_ = byteString.toStringUtf8();
            return;
        }
        throw null;
    }

    public String getJwksUri() {
        return this.jwksUri_;
    }

    public ByteString getJwksUriBytes() {
        return ByteString.copyFromUtf8(this.jwksUri_);
    }

    /* access modifiers changed from: private */
    public void setJwksUri(String str) {
        if (str != null) {
            this.jwksUri_ = str;
            return;
        }
        throw null;
    }

    /* access modifiers changed from: private */
    public void clearJwksUri() {
        this.jwksUri_ = getDefaultInstance().getJwksUri();
    }

    /* access modifiers changed from: private */
    public void setJwksUriBytes(ByteString byteString) {
        if (byteString != null) {
            checkByteStringIsUtf8(byteString);
            this.jwksUri_ = byteString.toStringUtf8();
            return;
        }
        throw null;
    }

    public String getAudiences() {
        return this.audiences_;
    }

    public ByteString getAudiencesBytes() {
        return ByteString.copyFromUtf8(this.audiences_);
    }

    /* access modifiers changed from: private */
    public void setAudiences(String str) {
        if (str != null) {
            this.audiences_ = str;
            return;
        }
        throw null;
    }

    /* access modifiers changed from: private */
    public void clearAudiences() {
        this.audiences_ = getDefaultInstance().getAudiences();
    }

    /* access modifiers changed from: private */
    public void setAudiencesBytes(ByteString byteString) {
        if (byteString != null) {
            checkByteStringIsUtf8(byteString);
            this.audiences_ = byteString.toStringUtf8();
            return;
        }
        throw null;
    }

    public String getAuthorizationUrl() {
        return this.authorizationUrl_;
    }

    public ByteString getAuthorizationUrlBytes() {
        return ByteString.copyFromUtf8(this.authorizationUrl_);
    }

    /* access modifiers changed from: private */
    public void setAuthorizationUrl(String str) {
        if (str != null) {
            this.authorizationUrl_ = str;
            return;
        }
        throw null;
    }

    /* access modifiers changed from: private */
    public void clearAuthorizationUrl() {
        this.authorizationUrl_ = getDefaultInstance().getAuthorizationUrl();
    }

    /* access modifiers changed from: private */
    public void setAuthorizationUrlBytes(ByteString byteString) {
        if (byteString != null) {
            checkByteStringIsUtf8(byteString);
            this.authorizationUrl_ = byteString.toStringUtf8();
            return;
        }
        throw null;
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (!this.id_.isEmpty()) {
            codedOutputStream.writeString(1, getId());
        }
        if (!this.issuer_.isEmpty()) {
            codedOutputStream.writeString(2, getIssuer());
        }
        if (!this.jwksUri_.isEmpty()) {
            codedOutputStream.writeString(3, getJwksUri());
        }
        if (!this.audiences_.isEmpty()) {
            codedOutputStream.writeString(4, getAudiences());
        }
        if (!this.authorizationUrl_.isEmpty()) {
            codedOutputStream.writeString(5, getAuthorizationUrl());
        }
    }

    public int getSerializedSize() {
        int i = this.memoizedSerializedSize;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        if (!this.id_.isEmpty()) {
            i2 = 0 + CodedOutputStream.computeStringSize(1, getId());
        }
        if (!this.issuer_.isEmpty()) {
            i2 += CodedOutputStream.computeStringSize(2, getIssuer());
        }
        if (!this.jwksUri_.isEmpty()) {
            i2 += CodedOutputStream.computeStringSize(3, getJwksUri());
        }
        if (!this.audiences_.isEmpty()) {
            i2 += CodedOutputStream.computeStringSize(4, getAudiences());
        }
        if (!this.authorizationUrl_.isEmpty()) {
            i2 += CodedOutputStream.computeStringSize(5, getAuthorizationUrl());
        }
        this.memoizedSerializedSize = i2;
        return i2;
    }

    public static AuthProvider parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (AuthProvider) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static AuthProvider parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (AuthProvider) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static AuthProvider parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (AuthProvider) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static AuthProvider parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (AuthProvider) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static AuthProvider parseFrom(InputStream inputStream) throws IOException {
        return (AuthProvider) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static AuthProvider parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (AuthProvider) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static AuthProvider parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (AuthProvider) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static AuthProvider parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (AuthProvider) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static AuthProvider parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (AuthProvider) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static AuthProvider parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (AuthProvider) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(AuthProvider authProvider) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(authProvider);
    }

    /* compiled from: com.google.firebase:protolite-well-known-types@@16.0.1 */
    public static final class Builder extends GeneratedMessageLite.Builder<AuthProvider, Builder> implements AuthProviderOrBuilder {
        /* synthetic */ Builder(AnonymousClass1 r1) {
            this();
        }

        private Builder() {
            super(AuthProvider.DEFAULT_INSTANCE);
        }

        public String getId() {
            return ((AuthProvider) this.instance).getId();
        }

        public ByteString getIdBytes() {
            return ((AuthProvider) this.instance).getIdBytes();
        }

        public Builder setId(String str) {
            copyOnWrite();
            ((AuthProvider) this.instance).setId(str);
            return this;
        }

        public Builder clearId() {
            copyOnWrite();
            ((AuthProvider) this.instance).clearId();
            return this;
        }

        public Builder setIdBytes(ByteString byteString) {
            copyOnWrite();
            ((AuthProvider) this.instance).setIdBytes(byteString);
            return this;
        }

        public String getIssuer() {
            return ((AuthProvider) this.instance).getIssuer();
        }

        public ByteString getIssuerBytes() {
            return ((AuthProvider) this.instance).getIssuerBytes();
        }

        public Builder setIssuer(String str) {
            copyOnWrite();
            ((AuthProvider) this.instance).setIssuer(str);
            return this;
        }

        public Builder clearIssuer() {
            copyOnWrite();
            ((AuthProvider) this.instance).clearIssuer();
            return this;
        }

        public Builder setIssuerBytes(ByteString byteString) {
            copyOnWrite();
            ((AuthProvider) this.instance).setIssuerBytes(byteString);
            return this;
        }

        public String getJwksUri() {
            return ((AuthProvider) this.instance).getJwksUri();
        }

        public ByteString getJwksUriBytes() {
            return ((AuthProvider) this.instance).getJwksUriBytes();
        }

        public Builder setJwksUri(String str) {
            copyOnWrite();
            ((AuthProvider) this.instance).setJwksUri(str);
            return this;
        }

        public Builder clearJwksUri() {
            copyOnWrite();
            ((AuthProvider) this.instance).clearJwksUri();
            return this;
        }

        public Builder setJwksUriBytes(ByteString byteString) {
            copyOnWrite();
            ((AuthProvider) this.instance).setJwksUriBytes(byteString);
            return this;
        }

        public String getAudiences() {
            return ((AuthProvider) this.instance).getAudiences();
        }

        public ByteString getAudiencesBytes() {
            return ((AuthProvider) this.instance).getAudiencesBytes();
        }

        public Builder setAudiences(String str) {
            copyOnWrite();
            ((AuthProvider) this.instance).setAudiences(str);
            return this;
        }

        public Builder clearAudiences() {
            copyOnWrite();
            ((AuthProvider) this.instance).clearAudiences();
            return this;
        }

        public Builder setAudiencesBytes(ByteString byteString) {
            copyOnWrite();
            ((AuthProvider) this.instance).setAudiencesBytes(byteString);
            return this;
        }

        public String getAuthorizationUrl() {
            return ((AuthProvider) this.instance).getAuthorizationUrl();
        }

        public ByteString getAuthorizationUrlBytes() {
            return ((AuthProvider) this.instance).getAuthorizationUrlBytes();
        }

        public Builder setAuthorizationUrl(String str) {
            copyOnWrite();
            ((AuthProvider) this.instance).setAuthorizationUrl(str);
            return this;
        }

        public Builder clearAuthorizationUrl() {
            copyOnWrite();
            ((AuthProvider) this.instance).clearAuthorizationUrl();
            return this;
        }

        public Builder setAuthorizationUrlBytes(ByteString byteString) {
            copyOnWrite();
            ((AuthProvider) this.instance).setAuthorizationUrlBytes(byteString);
            return this;
        }
    }

    /* renamed from: com.google.api.AuthProvider$1  reason: invalid class name */
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.api.AuthProvider.AnonymousClass1.<clinit>():void");
        }
    }

    /* access modifiers changed from: protected */
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
            case 1:
                return new AuthProvider();
            case 2:
                return DEFAULT_INSTANCE;
            case 3:
                return null;
            case 4:
                return new Builder((AnonymousClass1) null);
            case 5:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                AuthProvider authProvider = (AuthProvider) obj2;
                this.id_ = visitor.visitString(!this.id_.isEmpty(), this.id_, !authProvider.id_.isEmpty(), authProvider.id_);
                this.issuer_ = visitor.visitString(!this.issuer_.isEmpty(), this.issuer_, !authProvider.issuer_.isEmpty(), authProvider.issuer_);
                this.jwksUri_ = visitor.visitString(!this.jwksUri_.isEmpty(), this.jwksUri_, !authProvider.jwksUri_.isEmpty(), authProvider.jwksUri_);
                this.audiences_ = visitor.visitString(!this.audiences_.isEmpty(), this.audiences_, !authProvider.audiences_.isEmpty(), authProvider.audiences_);
                this.authorizationUrl_ = visitor.visitString(!this.authorizationUrl_.isEmpty(), this.authorizationUrl_, true ^ authProvider.authorizationUrl_.isEmpty(), authProvider.authorizationUrl_);
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
                                this.id_ = codedInputStream.readStringRequireUtf8();
                            } else if (readTag == 18) {
                                this.issuer_ = codedInputStream.readStringRequireUtf8();
                            } else if (readTag == 26) {
                                this.jwksUri_ = codedInputStream.readStringRequireUtf8();
                            } else if (readTag == 34) {
                                this.audiences_ = codedInputStream.readStringRequireUtf8();
                            } else if (readTag == 42) {
                                this.authorizationUrl_ = codedInputStream.readStringRequireUtf8();
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
                    synchronized (AuthProvider.class) {
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
        AuthProvider authProvider = new AuthProvider();
        DEFAULT_INSTANCE = authProvider;
        authProvider.makeImmutable();
    }

    public static AuthProvider getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<AuthProvider> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
