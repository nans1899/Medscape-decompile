package com.google.api;

import com.google.api.AuthProvider;
import com.google.api.AuthenticationRule;
import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLite;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

/* compiled from: com.google.firebase:protolite-well-known-types@@16.0.1 */
public final class Authentication extends GeneratedMessageLite<Authentication, Builder> implements AuthenticationOrBuilder {
    /* access modifiers changed from: private */
    public static final Authentication DEFAULT_INSTANCE;
    private static volatile Parser<Authentication> PARSER = null;
    public static final int PROVIDERS_FIELD_NUMBER = 4;
    public static final int RULES_FIELD_NUMBER = 3;
    private Internal.ProtobufList<AuthProvider> providers_ = emptyProtobufList();
    private Internal.ProtobufList<AuthenticationRule> rules_ = emptyProtobufList();

    private Authentication() {
    }

    public List<AuthenticationRule> getRulesList() {
        return this.rules_;
    }

    public List<? extends AuthenticationRuleOrBuilder> getRulesOrBuilderList() {
        return this.rules_;
    }

    public int getRulesCount() {
        return this.rules_.size();
    }

    public AuthenticationRule getRules(int i) {
        return (AuthenticationRule) this.rules_.get(i);
    }

    public AuthenticationRuleOrBuilder getRulesOrBuilder(int i) {
        return (AuthenticationRuleOrBuilder) this.rules_.get(i);
    }

    private void ensureRulesIsMutable() {
        if (!this.rules_.isModifiable()) {
            this.rules_ = GeneratedMessageLite.mutableCopy(this.rules_);
        }
    }

    /* access modifiers changed from: private */
    public void setRules(int i, AuthenticationRule authenticationRule) {
        if (authenticationRule != null) {
            ensureRulesIsMutable();
            this.rules_.set(i, authenticationRule);
            return;
        }
        throw null;
    }

    /* access modifiers changed from: private */
    public void setRules(int i, AuthenticationRule.Builder builder) {
        ensureRulesIsMutable();
        this.rules_.set(i, (AuthenticationRule) builder.build());
    }

    /* access modifiers changed from: private */
    public void addRules(AuthenticationRule authenticationRule) {
        if (authenticationRule != null) {
            ensureRulesIsMutable();
            this.rules_.add(authenticationRule);
            return;
        }
        throw null;
    }

    /* access modifiers changed from: private */
    public void addRules(int i, AuthenticationRule authenticationRule) {
        if (authenticationRule != null) {
            ensureRulesIsMutable();
            this.rules_.add(i, authenticationRule);
            return;
        }
        throw null;
    }

    /* access modifiers changed from: private */
    public void addRules(AuthenticationRule.Builder builder) {
        ensureRulesIsMutable();
        this.rules_.add((AuthenticationRule) builder.build());
    }

    /* access modifiers changed from: private */
    public void addRules(int i, AuthenticationRule.Builder builder) {
        ensureRulesIsMutable();
        this.rules_.add(i, (AuthenticationRule) builder.build());
    }

    /* access modifiers changed from: private */
    public void addAllRules(Iterable<? extends AuthenticationRule> iterable) {
        ensureRulesIsMutable();
        AbstractMessageLite.addAll(iterable, this.rules_);
    }

    /* access modifiers changed from: private */
    public void clearRules() {
        this.rules_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    public void removeRules(int i) {
        ensureRulesIsMutable();
        this.rules_.remove(i);
    }

    public List<AuthProvider> getProvidersList() {
        return this.providers_;
    }

    public List<? extends AuthProviderOrBuilder> getProvidersOrBuilderList() {
        return this.providers_;
    }

    public int getProvidersCount() {
        return this.providers_.size();
    }

    public AuthProvider getProviders(int i) {
        return (AuthProvider) this.providers_.get(i);
    }

    public AuthProviderOrBuilder getProvidersOrBuilder(int i) {
        return (AuthProviderOrBuilder) this.providers_.get(i);
    }

    private void ensureProvidersIsMutable() {
        if (!this.providers_.isModifiable()) {
            this.providers_ = GeneratedMessageLite.mutableCopy(this.providers_);
        }
    }

    /* access modifiers changed from: private */
    public void setProviders(int i, AuthProvider authProvider) {
        if (authProvider != null) {
            ensureProvidersIsMutable();
            this.providers_.set(i, authProvider);
            return;
        }
        throw null;
    }

    /* access modifiers changed from: private */
    public void setProviders(int i, AuthProvider.Builder builder) {
        ensureProvidersIsMutable();
        this.providers_.set(i, (AuthProvider) builder.build());
    }

    /* access modifiers changed from: private */
    public void addProviders(AuthProvider authProvider) {
        if (authProvider != null) {
            ensureProvidersIsMutable();
            this.providers_.add(authProvider);
            return;
        }
        throw null;
    }

    /* access modifiers changed from: private */
    public void addProviders(int i, AuthProvider authProvider) {
        if (authProvider != null) {
            ensureProvidersIsMutable();
            this.providers_.add(i, authProvider);
            return;
        }
        throw null;
    }

    /* access modifiers changed from: private */
    public void addProviders(AuthProvider.Builder builder) {
        ensureProvidersIsMutable();
        this.providers_.add((AuthProvider) builder.build());
    }

    /* access modifiers changed from: private */
    public void addProviders(int i, AuthProvider.Builder builder) {
        ensureProvidersIsMutable();
        this.providers_.add(i, (AuthProvider) builder.build());
    }

    /* access modifiers changed from: private */
    public void addAllProviders(Iterable<? extends AuthProvider> iterable) {
        ensureProvidersIsMutable();
        AbstractMessageLite.addAll(iterable, this.providers_);
    }

    /* access modifiers changed from: private */
    public void clearProviders() {
        this.providers_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    public void removeProviders(int i) {
        ensureProvidersIsMutable();
        this.providers_.remove(i);
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        for (int i = 0; i < this.rules_.size(); i++) {
            codedOutputStream.writeMessage(3, (MessageLite) this.rules_.get(i));
        }
        for (int i2 = 0; i2 < this.providers_.size(); i2++) {
            codedOutputStream.writeMessage(4, (MessageLite) this.providers_.get(i2));
        }
    }

    public int getSerializedSize() {
        int i = this.memoizedSerializedSize;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < this.rules_.size(); i3++) {
            i2 += CodedOutputStream.computeMessageSize(3, (MessageLite) this.rules_.get(i3));
        }
        for (int i4 = 0; i4 < this.providers_.size(); i4++) {
            i2 += CodedOutputStream.computeMessageSize(4, (MessageLite) this.providers_.get(i4));
        }
        this.memoizedSerializedSize = i2;
        return i2;
    }

    public static Authentication parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (Authentication) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static Authentication parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Authentication) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static Authentication parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (Authentication) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static Authentication parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Authentication) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static Authentication parseFrom(InputStream inputStream) throws IOException {
        return (Authentication) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static Authentication parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Authentication) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static Authentication parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (Authentication) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static Authentication parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Authentication) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static Authentication parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (Authentication) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static Authentication parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Authentication) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(Authentication authentication) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(authentication);
    }

    /* compiled from: com.google.firebase:protolite-well-known-types@@16.0.1 */
    public static final class Builder extends GeneratedMessageLite.Builder<Authentication, Builder> implements AuthenticationOrBuilder {
        /* synthetic */ Builder(AnonymousClass1 r1) {
            this();
        }

        private Builder() {
            super(Authentication.DEFAULT_INSTANCE);
        }

        public List<AuthenticationRule> getRulesList() {
            return Collections.unmodifiableList(((Authentication) this.instance).getRulesList());
        }

        public int getRulesCount() {
            return ((Authentication) this.instance).getRulesCount();
        }

        public AuthenticationRule getRules(int i) {
            return ((Authentication) this.instance).getRules(i);
        }

        public Builder setRules(int i, AuthenticationRule authenticationRule) {
            copyOnWrite();
            ((Authentication) this.instance).setRules(i, authenticationRule);
            return this;
        }

        public Builder setRules(int i, AuthenticationRule.Builder builder) {
            copyOnWrite();
            ((Authentication) this.instance).setRules(i, builder);
            return this;
        }

        public Builder addRules(AuthenticationRule authenticationRule) {
            copyOnWrite();
            ((Authentication) this.instance).addRules(authenticationRule);
            return this;
        }

        public Builder addRules(int i, AuthenticationRule authenticationRule) {
            copyOnWrite();
            ((Authentication) this.instance).addRules(i, authenticationRule);
            return this;
        }

        public Builder addRules(AuthenticationRule.Builder builder) {
            copyOnWrite();
            ((Authentication) this.instance).addRules(builder);
            return this;
        }

        public Builder addRules(int i, AuthenticationRule.Builder builder) {
            copyOnWrite();
            ((Authentication) this.instance).addRules(i, builder);
            return this;
        }

        public Builder addAllRules(Iterable<? extends AuthenticationRule> iterable) {
            copyOnWrite();
            ((Authentication) this.instance).addAllRules(iterable);
            return this;
        }

        public Builder clearRules() {
            copyOnWrite();
            ((Authentication) this.instance).clearRules();
            return this;
        }

        public Builder removeRules(int i) {
            copyOnWrite();
            ((Authentication) this.instance).removeRules(i);
            return this;
        }

        public List<AuthProvider> getProvidersList() {
            return Collections.unmodifiableList(((Authentication) this.instance).getProvidersList());
        }

        public int getProvidersCount() {
            return ((Authentication) this.instance).getProvidersCount();
        }

        public AuthProvider getProviders(int i) {
            return ((Authentication) this.instance).getProviders(i);
        }

        public Builder setProviders(int i, AuthProvider authProvider) {
            copyOnWrite();
            ((Authentication) this.instance).setProviders(i, authProvider);
            return this;
        }

        public Builder setProviders(int i, AuthProvider.Builder builder) {
            copyOnWrite();
            ((Authentication) this.instance).setProviders(i, builder);
            return this;
        }

        public Builder addProviders(AuthProvider authProvider) {
            copyOnWrite();
            ((Authentication) this.instance).addProviders(authProvider);
            return this;
        }

        public Builder addProviders(int i, AuthProvider authProvider) {
            copyOnWrite();
            ((Authentication) this.instance).addProviders(i, authProvider);
            return this;
        }

        public Builder addProviders(AuthProvider.Builder builder) {
            copyOnWrite();
            ((Authentication) this.instance).addProviders(builder);
            return this;
        }

        public Builder addProviders(int i, AuthProvider.Builder builder) {
            copyOnWrite();
            ((Authentication) this.instance).addProviders(i, builder);
            return this;
        }

        public Builder addAllProviders(Iterable<? extends AuthProvider> iterable) {
            copyOnWrite();
            ((Authentication) this.instance).addAllProviders(iterable);
            return this;
        }

        public Builder clearProviders() {
            copyOnWrite();
            ((Authentication) this.instance).clearProviders();
            return this;
        }

        public Builder removeProviders(int i) {
            copyOnWrite();
            ((Authentication) this.instance).removeProviders(i);
            return this;
        }
    }

    /* renamed from: com.google.api.Authentication$1  reason: invalid class name */
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.api.Authentication.AnonymousClass1.<clinit>():void");
        }
    }

    /* access modifiers changed from: protected */
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
            case 1:
                return new Authentication();
            case 2:
                return DEFAULT_INSTANCE;
            case 3:
                this.rules_.makeImmutable();
                this.providers_.makeImmutable();
                return null;
            case 4:
                return new Builder((AnonymousClass1) null);
            case 5:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                Authentication authentication = (Authentication) obj2;
                this.rules_ = visitor.visitList(this.rules_, authentication.rules_);
                this.providers_ = visitor.visitList(this.providers_, authentication.providers_);
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
                            if (readTag == 26) {
                                if (!this.rules_.isModifiable()) {
                                    this.rules_ = GeneratedMessageLite.mutableCopy(this.rules_);
                                }
                                this.rules_.add((AuthenticationRule) codedInputStream.readMessage(AuthenticationRule.parser(), extensionRegistryLite));
                            } else if (readTag == 34) {
                                if (!this.providers_.isModifiable()) {
                                    this.providers_ = GeneratedMessageLite.mutableCopy(this.providers_);
                                }
                                this.providers_.add((AuthProvider) codedInputStream.readMessage(AuthProvider.parser(), extensionRegistryLite));
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
                    synchronized (Authentication.class) {
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
        Authentication authentication = new Authentication();
        DEFAULT_INSTANCE = authentication;
        authentication.makeImmutable();
    }

    public static Authentication getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Authentication> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
