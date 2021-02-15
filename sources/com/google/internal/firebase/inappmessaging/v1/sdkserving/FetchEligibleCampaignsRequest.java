package com.google.internal.firebase.inappmessaging.v1.sdkserving;

import com.google.developers.mobile.targeting.proto.ClientSignalsProto;
import com.google.internal.firebase.inappmessaging.v1.sdkserving.CampaignImpression;
import com.google.internal.firebase.inappmessaging.v1.sdkserving.ClientAppInfo;
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

/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
public final class FetchEligibleCampaignsRequest extends GeneratedMessageLite<FetchEligibleCampaignsRequest, Builder> implements FetchEligibleCampaignsRequestOrBuilder {
    public static final int ALREADY_SEEN_CAMPAIGNS_FIELD_NUMBER = 3;
    public static final int CLIENT_SIGNALS_FIELD_NUMBER = 4;
    /* access modifiers changed from: private */
    public static final FetchEligibleCampaignsRequest DEFAULT_INSTANCE;
    private static volatile Parser<FetchEligibleCampaignsRequest> PARSER = null;
    public static final int PROJECT_NUMBER_FIELD_NUMBER = 1;
    public static final int REQUESTING_CLIENT_APP_FIELD_NUMBER = 2;
    private Internal.ProtobufList<CampaignImpression> alreadySeenCampaigns_ = emptyProtobufList();
    private int bitField0_;
    private ClientSignalsProto.ClientSignals clientSignals_;
    private String projectNumber_ = "";
    private ClientAppInfo requestingClientApp_;

    private FetchEligibleCampaignsRequest() {
    }

    public String getProjectNumber() {
        return this.projectNumber_;
    }

    public ByteString getProjectNumberBytes() {
        return ByteString.copyFromUtf8(this.projectNumber_);
    }

    /* access modifiers changed from: private */
    public void setProjectNumber(String str) {
        if (str != null) {
            this.projectNumber_ = str;
            return;
        }
        throw null;
    }

    /* access modifiers changed from: private */
    public void clearProjectNumber() {
        this.projectNumber_ = getDefaultInstance().getProjectNumber();
    }

    /* access modifiers changed from: private */
    public void setProjectNumberBytes(ByteString byteString) {
        if (byteString != null) {
            checkByteStringIsUtf8(byteString);
            this.projectNumber_ = byteString.toStringUtf8();
            return;
        }
        throw null;
    }

    public boolean hasRequestingClientApp() {
        return this.requestingClientApp_ != null;
    }

    public ClientAppInfo getRequestingClientApp() {
        ClientAppInfo clientAppInfo = this.requestingClientApp_;
        return clientAppInfo == null ? ClientAppInfo.getDefaultInstance() : clientAppInfo;
    }

    /* access modifiers changed from: private */
    public void setRequestingClientApp(ClientAppInfo clientAppInfo) {
        if (clientAppInfo != null) {
            this.requestingClientApp_ = clientAppInfo;
            return;
        }
        throw null;
    }

    /* access modifiers changed from: private */
    public void setRequestingClientApp(ClientAppInfo.Builder builder) {
        this.requestingClientApp_ = (ClientAppInfo) builder.build();
    }

    /* access modifiers changed from: private */
    public void mergeRequestingClientApp(ClientAppInfo clientAppInfo) {
        ClientAppInfo clientAppInfo2 = this.requestingClientApp_;
        if (clientAppInfo2 == null || clientAppInfo2 == ClientAppInfo.getDefaultInstance()) {
            this.requestingClientApp_ = clientAppInfo;
        } else {
            this.requestingClientApp_ = (ClientAppInfo) ((ClientAppInfo.Builder) ClientAppInfo.newBuilder(this.requestingClientApp_).mergeFrom(clientAppInfo)).buildPartial();
        }
    }

    /* access modifiers changed from: private */
    public void clearRequestingClientApp() {
        this.requestingClientApp_ = null;
    }

    public List<CampaignImpression> getAlreadySeenCampaignsList() {
        return this.alreadySeenCampaigns_;
    }

    public List<? extends CampaignImpressionOrBuilder> getAlreadySeenCampaignsOrBuilderList() {
        return this.alreadySeenCampaigns_;
    }

    public int getAlreadySeenCampaignsCount() {
        return this.alreadySeenCampaigns_.size();
    }

    public CampaignImpression getAlreadySeenCampaigns(int i) {
        return (CampaignImpression) this.alreadySeenCampaigns_.get(i);
    }

    public CampaignImpressionOrBuilder getAlreadySeenCampaignsOrBuilder(int i) {
        return (CampaignImpressionOrBuilder) this.alreadySeenCampaigns_.get(i);
    }

    private void ensureAlreadySeenCampaignsIsMutable() {
        if (!this.alreadySeenCampaigns_.isModifiable()) {
            this.alreadySeenCampaigns_ = GeneratedMessageLite.mutableCopy(this.alreadySeenCampaigns_);
        }
    }

    /* access modifiers changed from: private */
    public void setAlreadySeenCampaigns(int i, CampaignImpression campaignImpression) {
        if (campaignImpression != null) {
            ensureAlreadySeenCampaignsIsMutable();
            this.alreadySeenCampaigns_.set(i, campaignImpression);
            return;
        }
        throw null;
    }

    /* access modifiers changed from: private */
    public void setAlreadySeenCampaigns(int i, CampaignImpression.Builder builder) {
        ensureAlreadySeenCampaignsIsMutable();
        this.alreadySeenCampaigns_.set(i, (CampaignImpression) builder.build());
    }

    /* access modifiers changed from: private */
    public void addAlreadySeenCampaigns(CampaignImpression campaignImpression) {
        if (campaignImpression != null) {
            ensureAlreadySeenCampaignsIsMutable();
            this.alreadySeenCampaigns_.add(campaignImpression);
            return;
        }
        throw null;
    }

    /* access modifiers changed from: private */
    public void addAlreadySeenCampaigns(int i, CampaignImpression campaignImpression) {
        if (campaignImpression != null) {
            ensureAlreadySeenCampaignsIsMutable();
            this.alreadySeenCampaigns_.add(i, campaignImpression);
            return;
        }
        throw null;
    }

    /* access modifiers changed from: private */
    public void addAlreadySeenCampaigns(CampaignImpression.Builder builder) {
        ensureAlreadySeenCampaignsIsMutable();
        this.alreadySeenCampaigns_.add((CampaignImpression) builder.build());
    }

    /* access modifiers changed from: private */
    public void addAlreadySeenCampaigns(int i, CampaignImpression.Builder builder) {
        ensureAlreadySeenCampaignsIsMutable();
        this.alreadySeenCampaigns_.add(i, (CampaignImpression) builder.build());
    }

    /* access modifiers changed from: private */
    public void addAllAlreadySeenCampaigns(Iterable<? extends CampaignImpression> iterable) {
        ensureAlreadySeenCampaignsIsMutable();
        AbstractMessageLite.addAll(iterable, this.alreadySeenCampaigns_);
    }

    /* access modifiers changed from: private */
    public void clearAlreadySeenCampaigns() {
        this.alreadySeenCampaigns_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    public void removeAlreadySeenCampaigns(int i) {
        ensureAlreadySeenCampaignsIsMutable();
        this.alreadySeenCampaigns_.remove(i);
    }

    public boolean hasClientSignals() {
        return this.clientSignals_ != null;
    }

    public ClientSignalsProto.ClientSignals getClientSignals() {
        ClientSignalsProto.ClientSignals clientSignals = this.clientSignals_;
        return clientSignals == null ? ClientSignalsProto.ClientSignals.getDefaultInstance() : clientSignals;
    }

    /* access modifiers changed from: private */
    public void setClientSignals(ClientSignalsProto.ClientSignals clientSignals) {
        if (clientSignals != null) {
            this.clientSignals_ = clientSignals;
            return;
        }
        throw null;
    }

    /* access modifiers changed from: private */
    public void setClientSignals(ClientSignalsProto.ClientSignals.Builder builder) {
        this.clientSignals_ = (ClientSignalsProto.ClientSignals) builder.build();
    }

    /* access modifiers changed from: private */
    public void mergeClientSignals(ClientSignalsProto.ClientSignals clientSignals) {
        ClientSignalsProto.ClientSignals clientSignals2 = this.clientSignals_;
        if (clientSignals2 == null || clientSignals2 == ClientSignalsProto.ClientSignals.getDefaultInstance()) {
            this.clientSignals_ = clientSignals;
        } else {
            this.clientSignals_ = (ClientSignalsProto.ClientSignals) ((ClientSignalsProto.ClientSignals.Builder) ClientSignalsProto.ClientSignals.newBuilder(this.clientSignals_).mergeFrom(clientSignals)).buildPartial();
        }
    }

    /* access modifiers changed from: private */
    public void clearClientSignals() {
        this.clientSignals_ = null;
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (!this.projectNumber_.isEmpty()) {
            codedOutputStream.writeString(1, getProjectNumber());
        }
        if (this.requestingClientApp_ != null) {
            codedOutputStream.writeMessage(2, getRequestingClientApp());
        }
        for (int i = 0; i < this.alreadySeenCampaigns_.size(); i++) {
            codedOutputStream.writeMessage(3, (MessageLite) this.alreadySeenCampaigns_.get(i));
        }
        if (this.clientSignals_ != null) {
            codedOutputStream.writeMessage(4, getClientSignals());
        }
    }

    public int getSerializedSize() {
        int i = this.memoizedSerializedSize;
        if (i != -1) {
            return i;
        }
        int computeStringSize = !this.projectNumber_.isEmpty() ? CodedOutputStream.computeStringSize(1, getProjectNumber()) + 0 : 0;
        if (this.requestingClientApp_ != null) {
            computeStringSize += CodedOutputStream.computeMessageSize(2, getRequestingClientApp());
        }
        for (int i2 = 0; i2 < this.alreadySeenCampaigns_.size(); i2++) {
            computeStringSize += CodedOutputStream.computeMessageSize(3, (MessageLite) this.alreadySeenCampaigns_.get(i2));
        }
        if (this.clientSignals_ != null) {
            computeStringSize += CodedOutputStream.computeMessageSize(4, getClientSignals());
        }
        this.memoizedSerializedSize = computeStringSize;
        return computeStringSize;
    }

    public static FetchEligibleCampaignsRequest parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (FetchEligibleCampaignsRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static FetchEligibleCampaignsRequest parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (FetchEligibleCampaignsRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static FetchEligibleCampaignsRequest parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (FetchEligibleCampaignsRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static FetchEligibleCampaignsRequest parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (FetchEligibleCampaignsRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static FetchEligibleCampaignsRequest parseFrom(InputStream inputStream) throws IOException {
        return (FetchEligibleCampaignsRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static FetchEligibleCampaignsRequest parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (FetchEligibleCampaignsRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static FetchEligibleCampaignsRequest parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (FetchEligibleCampaignsRequest) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static FetchEligibleCampaignsRequest parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (FetchEligibleCampaignsRequest) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static FetchEligibleCampaignsRequest parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (FetchEligibleCampaignsRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static FetchEligibleCampaignsRequest parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (FetchEligibleCampaignsRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(FetchEligibleCampaignsRequest fetchEligibleCampaignsRequest) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(fetchEligibleCampaignsRequest);
    }

    /* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
    public static final class Builder extends GeneratedMessageLite.Builder<FetchEligibleCampaignsRequest, Builder> implements FetchEligibleCampaignsRequestOrBuilder {
        /* synthetic */ Builder(AnonymousClass1 r1) {
            this();
        }

        private Builder() {
            super(FetchEligibleCampaignsRequest.DEFAULT_INSTANCE);
        }

        public String getProjectNumber() {
            return ((FetchEligibleCampaignsRequest) this.instance).getProjectNumber();
        }

        public ByteString getProjectNumberBytes() {
            return ((FetchEligibleCampaignsRequest) this.instance).getProjectNumberBytes();
        }

        public Builder setProjectNumber(String str) {
            copyOnWrite();
            ((FetchEligibleCampaignsRequest) this.instance).setProjectNumber(str);
            return this;
        }

        public Builder clearProjectNumber() {
            copyOnWrite();
            ((FetchEligibleCampaignsRequest) this.instance).clearProjectNumber();
            return this;
        }

        public Builder setProjectNumberBytes(ByteString byteString) {
            copyOnWrite();
            ((FetchEligibleCampaignsRequest) this.instance).setProjectNumberBytes(byteString);
            return this;
        }

        public boolean hasRequestingClientApp() {
            return ((FetchEligibleCampaignsRequest) this.instance).hasRequestingClientApp();
        }

        public ClientAppInfo getRequestingClientApp() {
            return ((FetchEligibleCampaignsRequest) this.instance).getRequestingClientApp();
        }

        public Builder setRequestingClientApp(ClientAppInfo clientAppInfo) {
            copyOnWrite();
            ((FetchEligibleCampaignsRequest) this.instance).setRequestingClientApp(clientAppInfo);
            return this;
        }

        public Builder setRequestingClientApp(ClientAppInfo.Builder builder) {
            copyOnWrite();
            ((FetchEligibleCampaignsRequest) this.instance).setRequestingClientApp(builder);
            return this;
        }

        public Builder mergeRequestingClientApp(ClientAppInfo clientAppInfo) {
            copyOnWrite();
            ((FetchEligibleCampaignsRequest) this.instance).mergeRequestingClientApp(clientAppInfo);
            return this;
        }

        public Builder clearRequestingClientApp() {
            copyOnWrite();
            ((FetchEligibleCampaignsRequest) this.instance).clearRequestingClientApp();
            return this;
        }

        public List<CampaignImpression> getAlreadySeenCampaignsList() {
            return Collections.unmodifiableList(((FetchEligibleCampaignsRequest) this.instance).getAlreadySeenCampaignsList());
        }

        public int getAlreadySeenCampaignsCount() {
            return ((FetchEligibleCampaignsRequest) this.instance).getAlreadySeenCampaignsCount();
        }

        public CampaignImpression getAlreadySeenCampaigns(int i) {
            return ((FetchEligibleCampaignsRequest) this.instance).getAlreadySeenCampaigns(i);
        }

        public Builder setAlreadySeenCampaigns(int i, CampaignImpression campaignImpression) {
            copyOnWrite();
            ((FetchEligibleCampaignsRequest) this.instance).setAlreadySeenCampaigns(i, campaignImpression);
            return this;
        }

        public Builder setAlreadySeenCampaigns(int i, CampaignImpression.Builder builder) {
            copyOnWrite();
            ((FetchEligibleCampaignsRequest) this.instance).setAlreadySeenCampaigns(i, builder);
            return this;
        }

        public Builder addAlreadySeenCampaigns(CampaignImpression campaignImpression) {
            copyOnWrite();
            ((FetchEligibleCampaignsRequest) this.instance).addAlreadySeenCampaigns(campaignImpression);
            return this;
        }

        public Builder addAlreadySeenCampaigns(int i, CampaignImpression campaignImpression) {
            copyOnWrite();
            ((FetchEligibleCampaignsRequest) this.instance).addAlreadySeenCampaigns(i, campaignImpression);
            return this;
        }

        public Builder addAlreadySeenCampaigns(CampaignImpression.Builder builder) {
            copyOnWrite();
            ((FetchEligibleCampaignsRequest) this.instance).addAlreadySeenCampaigns(builder);
            return this;
        }

        public Builder addAlreadySeenCampaigns(int i, CampaignImpression.Builder builder) {
            copyOnWrite();
            ((FetchEligibleCampaignsRequest) this.instance).addAlreadySeenCampaigns(i, builder);
            return this;
        }

        public Builder addAllAlreadySeenCampaigns(Iterable<? extends CampaignImpression> iterable) {
            copyOnWrite();
            ((FetchEligibleCampaignsRequest) this.instance).addAllAlreadySeenCampaigns(iterable);
            return this;
        }

        public Builder clearAlreadySeenCampaigns() {
            copyOnWrite();
            ((FetchEligibleCampaignsRequest) this.instance).clearAlreadySeenCampaigns();
            return this;
        }

        public Builder removeAlreadySeenCampaigns(int i) {
            copyOnWrite();
            ((FetchEligibleCampaignsRequest) this.instance).removeAlreadySeenCampaigns(i);
            return this;
        }

        public boolean hasClientSignals() {
            return ((FetchEligibleCampaignsRequest) this.instance).hasClientSignals();
        }

        public ClientSignalsProto.ClientSignals getClientSignals() {
            return ((FetchEligibleCampaignsRequest) this.instance).getClientSignals();
        }

        public Builder setClientSignals(ClientSignalsProto.ClientSignals clientSignals) {
            copyOnWrite();
            ((FetchEligibleCampaignsRequest) this.instance).setClientSignals(clientSignals);
            return this;
        }

        public Builder setClientSignals(ClientSignalsProto.ClientSignals.Builder builder) {
            copyOnWrite();
            ((FetchEligibleCampaignsRequest) this.instance).setClientSignals(builder);
            return this;
        }

        public Builder mergeClientSignals(ClientSignalsProto.ClientSignals clientSignals) {
            copyOnWrite();
            ((FetchEligibleCampaignsRequest) this.instance).mergeClientSignals(clientSignals);
            return this;
        }

        public Builder clearClientSignals() {
            copyOnWrite();
            ((FetchEligibleCampaignsRequest) this.instance).clearClientSignals();
            return this;
        }
    }

    /* renamed from: com.google.internal.firebase.inappmessaging.v1.sdkserving.FetchEligibleCampaignsRequest$1  reason: invalid class name */
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.internal.firebase.inappmessaging.v1.sdkserving.FetchEligibleCampaignsRequest.AnonymousClass1.<clinit>():void");
        }
    }

    /* access modifiers changed from: protected */
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
            case 1:
                return new FetchEligibleCampaignsRequest();
            case 2:
                return DEFAULT_INSTANCE;
            case 3:
                this.alreadySeenCampaigns_.makeImmutable();
                return null;
            case 4:
                return new Builder((AnonymousClass1) null);
            case 5:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                FetchEligibleCampaignsRequest fetchEligibleCampaignsRequest = (FetchEligibleCampaignsRequest) obj2;
                this.projectNumber_ = visitor.visitString(!this.projectNumber_.isEmpty(), this.projectNumber_, true ^ fetchEligibleCampaignsRequest.projectNumber_.isEmpty(), fetchEligibleCampaignsRequest.projectNumber_);
                this.requestingClientApp_ = (ClientAppInfo) visitor.visitMessage(this.requestingClientApp_, fetchEligibleCampaignsRequest.requestingClientApp_);
                this.alreadySeenCampaigns_ = visitor.visitList(this.alreadySeenCampaigns_, fetchEligibleCampaignsRequest.alreadySeenCampaigns_);
                this.clientSignals_ = (ClientSignalsProto.ClientSignals) visitor.visitMessage(this.clientSignals_, fetchEligibleCampaignsRequest.clientSignals_);
                if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                    this.bitField0_ |= fetchEligibleCampaignsRequest.bitField0_;
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
                                this.projectNumber_ = codedInputStream.readStringRequireUtf8();
                            } else if (readTag == 18) {
                                ClientAppInfo.Builder builder = this.requestingClientApp_ != null ? (ClientAppInfo.Builder) this.requestingClientApp_.toBuilder() : null;
                                ClientAppInfo clientAppInfo = (ClientAppInfo) codedInputStream.readMessage(ClientAppInfo.parser(), extensionRegistryLite);
                                this.requestingClientApp_ = clientAppInfo;
                                if (builder != null) {
                                    builder.mergeFrom(clientAppInfo);
                                    this.requestingClientApp_ = (ClientAppInfo) builder.buildPartial();
                                }
                            } else if (readTag == 26) {
                                if (!this.alreadySeenCampaigns_.isModifiable()) {
                                    this.alreadySeenCampaigns_ = GeneratedMessageLite.mutableCopy(this.alreadySeenCampaigns_);
                                }
                                this.alreadySeenCampaigns_.add((CampaignImpression) codedInputStream.readMessage(CampaignImpression.parser(), extensionRegistryLite));
                            } else if (readTag == 34) {
                                ClientSignalsProto.ClientSignals.Builder builder2 = this.clientSignals_ != null ? (ClientSignalsProto.ClientSignals.Builder) this.clientSignals_.toBuilder() : null;
                                ClientSignalsProto.ClientSignals clientSignals = (ClientSignalsProto.ClientSignals) codedInputStream.readMessage(ClientSignalsProto.ClientSignals.parser(), extensionRegistryLite);
                                this.clientSignals_ = clientSignals;
                                if (builder2 != null) {
                                    builder2.mergeFrom(clientSignals);
                                    this.clientSignals_ = (ClientSignalsProto.ClientSignals) builder2.buildPartial();
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
                    synchronized (FetchEligibleCampaignsRequest.class) {
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
        FetchEligibleCampaignsRequest fetchEligibleCampaignsRequest = new FetchEligibleCampaignsRequest();
        DEFAULT_INSTANCE = fetchEligibleCampaignsRequest;
        fetchEligibleCampaignsRequest.makeImmutable();
    }

    public static FetchEligibleCampaignsRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<FetchEligibleCampaignsRequest> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
