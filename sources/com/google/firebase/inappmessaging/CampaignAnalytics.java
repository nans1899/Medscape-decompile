package com.google.firebase.inappmessaging;

import com.google.firebase.inappmessaging.ClientAppInfo;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;

/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
public final class CampaignAnalytics extends GeneratedMessageLite<CampaignAnalytics, Builder> implements CampaignAnalyticsOrBuilder {
    public static final int CAMPAIGN_ID_FIELD_NUMBER = 2;
    public static final int CLIENT_APP_FIELD_NUMBER = 3;
    public static final int CLIENT_TIMESTAMP_MILLIS_FIELD_NUMBER = 4;
    /* access modifiers changed from: private */
    public static final CampaignAnalytics DEFAULT_INSTANCE;
    public static final int DISMISS_TYPE_FIELD_NUMBER = 6;
    public static final int ENGAGEMENTMETRICS_DELIVERY_RETRY_COUNT_FIELD_NUMBER = 10;
    public static final int EVENT_TYPE_FIELD_NUMBER = 5;
    public static final int FETCH_ERROR_REASON_FIELD_NUMBER = 8;
    public static final int FIAM_SDK_VERSION_FIELD_NUMBER = 9;
    private static volatile Parser<CampaignAnalytics> PARSER = null;
    public static final int PROJECT_NUMBER_FIELD_NUMBER = 1;
    public static final int RENDER_ERROR_REASON_FIELD_NUMBER = 7;
    private int bitField0_;
    private String campaignId_ = "";
    private ClientAppInfo clientApp_;
    private long clientTimestampMillis_;
    private int engagementMetricsDeliveryRetryCount_;
    private int eventCase_ = 0;
    private Object event_;
    private String fiamSdkVersion_ = "";
    private String projectNumber_ = "";

    private CampaignAnalytics() {
    }

    /* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
    public enum EventCase implements Internal.EnumLite {
        EVENT_TYPE(5),
        DISMISS_TYPE(6),
        RENDER_ERROR_REASON(7),
        FETCH_ERROR_REASON(8),
        EVENT_NOT_SET(0);
        
        private final int value;

        private EventCase(int i) {
            this.value = i;
        }

        @Deprecated
        public static EventCase valueOf(int i) {
            return forNumber(i);
        }

        public static EventCase forNumber(int i) {
            if (i == 0) {
                return EVENT_NOT_SET;
            }
            if (i == 5) {
                return EVENT_TYPE;
            }
            if (i == 6) {
                return DISMISS_TYPE;
            }
            if (i == 7) {
                return RENDER_ERROR_REASON;
            }
            if (i != 8) {
                return null;
            }
            return FETCH_ERROR_REASON;
        }

        public int getNumber() {
            return this.value;
        }
    }

    public EventCase getEventCase() {
        return EventCase.forNumber(this.eventCase_);
    }

    /* access modifiers changed from: private */
    public void clearEvent() {
        this.eventCase_ = 0;
        this.event_ = null;
    }

    public boolean hasProjectNumber() {
        return (this.bitField0_ & 1) == 1;
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
            this.bitField0_ |= 1;
            this.projectNumber_ = str;
            return;
        }
        throw null;
    }

    /* access modifiers changed from: private */
    public void clearProjectNumber() {
        this.bitField0_ &= -2;
        this.projectNumber_ = getDefaultInstance().getProjectNumber();
    }

    /* access modifiers changed from: private */
    public void setProjectNumberBytes(ByteString byteString) {
        if (byteString != null) {
            this.bitField0_ |= 1;
            this.projectNumber_ = byteString.toStringUtf8();
            return;
        }
        throw null;
    }

    public boolean hasCampaignId() {
        return (this.bitField0_ & 2) == 2;
    }

    public String getCampaignId() {
        return this.campaignId_;
    }

    public ByteString getCampaignIdBytes() {
        return ByteString.copyFromUtf8(this.campaignId_);
    }

    /* access modifiers changed from: private */
    public void setCampaignId(String str) {
        if (str != null) {
            this.bitField0_ |= 2;
            this.campaignId_ = str;
            return;
        }
        throw null;
    }

    /* access modifiers changed from: private */
    public void clearCampaignId() {
        this.bitField0_ &= -3;
        this.campaignId_ = getDefaultInstance().getCampaignId();
    }

    /* access modifiers changed from: private */
    public void setCampaignIdBytes(ByteString byteString) {
        if (byteString != null) {
            this.bitField0_ |= 2;
            this.campaignId_ = byteString.toStringUtf8();
            return;
        }
        throw null;
    }

    public boolean hasClientApp() {
        return (this.bitField0_ & 4) == 4;
    }

    public ClientAppInfo getClientApp() {
        ClientAppInfo clientAppInfo = this.clientApp_;
        return clientAppInfo == null ? ClientAppInfo.getDefaultInstance() : clientAppInfo;
    }

    /* access modifiers changed from: private */
    public void setClientApp(ClientAppInfo clientAppInfo) {
        if (clientAppInfo != null) {
            this.clientApp_ = clientAppInfo;
            this.bitField0_ |= 4;
            return;
        }
        throw null;
    }

    /* access modifiers changed from: private */
    public void setClientApp(ClientAppInfo.Builder builder) {
        this.clientApp_ = (ClientAppInfo) builder.build();
        this.bitField0_ |= 4;
    }

    /* access modifiers changed from: private */
    public void mergeClientApp(ClientAppInfo clientAppInfo) {
        ClientAppInfo clientAppInfo2 = this.clientApp_;
        if (clientAppInfo2 == null || clientAppInfo2 == ClientAppInfo.getDefaultInstance()) {
            this.clientApp_ = clientAppInfo;
        } else {
            this.clientApp_ = (ClientAppInfo) ((ClientAppInfo.Builder) ClientAppInfo.newBuilder(this.clientApp_).mergeFrom(clientAppInfo)).buildPartial();
        }
        this.bitField0_ |= 4;
    }

    /* access modifiers changed from: private */
    public void clearClientApp() {
        this.clientApp_ = null;
        this.bitField0_ &= -5;
    }

    public boolean hasClientTimestampMillis() {
        return (this.bitField0_ & 8) == 8;
    }

    public long getClientTimestampMillis() {
        return this.clientTimestampMillis_;
    }

    /* access modifiers changed from: private */
    public void setClientTimestampMillis(long j) {
        this.bitField0_ |= 8;
        this.clientTimestampMillis_ = j;
    }

    /* access modifiers changed from: private */
    public void clearClientTimestampMillis() {
        this.bitField0_ &= -9;
        this.clientTimestampMillis_ = 0;
    }

    public boolean hasEventType() {
        return this.eventCase_ == 5;
    }

    public EventType getEventType() {
        if (this.eventCase_ != 5) {
            return EventType.UNKNOWN_EVENT_TYPE;
        }
        EventType forNumber = EventType.forNumber(((Integer) this.event_).intValue());
        return forNumber == null ? EventType.UNKNOWN_EVENT_TYPE : forNumber;
    }

    /* access modifiers changed from: private */
    public void setEventType(EventType eventType) {
        if (eventType != null) {
            this.eventCase_ = 5;
            this.event_ = Integer.valueOf(eventType.getNumber());
            return;
        }
        throw null;
    }

    /* access modifiers changed from: private */
    public void clearEventType() {
        if (this.eventCase_ == 5) {
            this.eventCase_ = 0;
            this.event_ = null;
        }
    }

    public boolean hasDismissType() {
        return this.eventCase_ == 6;
    }

    public DismissType getDismissType() {
        if (this.eventCase_ != 6) {
            return DismissType.UNKNOWN_DISMISS_TYPE;
        }
        DismissType forNumber = DismissType.forNumber(((Integer) this.event_).intValue());
        return forNumber == null ? DismissType.UNKNOWN_DISMISS_TYPE : forNumber;
    }

    /* access modifiers changed from: private */
    public void setDismissType(DismissType dismissType) {
        if (dismissType != null) {
            this.eventCase_ = 6;
            this.event_ = Integer.valueOf(dismissType.getNumber());
            return;
        }
        throw null;
    }

    /* access modifiers changed from: private */
    public void clearDismissType() {
        if (this.eventCase_ == 6) {
            this.eventCase_ = 0;
            this.event_ = null;
        }
    }

    public boolean hasRenderErrorReason() {
        return this.eventCase_ == 7;
    }

    public RenderErrorReason getRenderErrorReason() {
        if (this.eventCase_ != 7) {
            return RenderErrorReason.UNSPECIFIED_RENDER_ERROR;
        }
        RenderErrorReason forNumber = RenderErrorReason.forNumber(((Integer) this.event_).intValue());
        return forNumber == null ? RenderErrorReason.UNSPECIFIED_RENDER_ERROR : forNumber;
    }

    /* access modifiers changed from: private */
    public void setRenderErrorReason(RenderErrorReason renderErrorReason) {
        if (renderErrorReason != null) {
            this.eventCase_ = 7;
            this.event_ = Integer.valueOf(renderErrorReason.getNumber());
            return;
        }
        throw null;
    }

    /* access modifiers changed from: private */
    public void clearRenderErrorReason() {
        if (this.eventCase_ == 7) {
            this.eventCase_ = 0;
            this.event_ = null;
        }
    }

    public boolean hasFetchErrorReason() {
        return this.eventCase_ == 8;
    }

    public FetchErrorReason getFetchErrorReason() {
        if (this.eventCase_ != 8) {
            return FetchErrorReason.UNSPECIFIED_FETCH_ERROR;
        }
        FetchErrorReason forNumber = FetchErrorReason.forNumber(((Integer) this.event_).intValue());
        return forNumber == null ? FetchErrorReason.UNSPECIFIED_FETCH_ERROR : forNumber;
    }

    /* access modifiers changed from: private */
    public void setFetchErrorReason(FetchErrorReason fetchErrorReason) {
        if (fetchErrorReason != null) {
            this.eventCase_ = 8;
            this.event_ = Integer.valueOf(fetchErrorReason.getNumber());
            return;
        }
        throw null;
    }

    /* access modifiers changed from: private */
    public void clearFetchErrorReason() {
        if (this.eventCase_ == 8) {
            this.eventCase_ = 0;
            this.event_ = null;
        }
    }

    public boolean hasFiamSdkVersion() {
        return (this.bitField0_ & 256) == 256;
    }

    public String getFiamSdkVersion() {
        return this.fiamSdkVersion_;
    }

    public ByteString getFiamSdkVersionBytes() {
        return ByteString.copyFromUtf8(this.fiamSdkVersion_);
    }

    /* access modifiers changed from: private */
    public void setFiamSdkVersion(String str) {
        if (str != null) {
            this.bitField0_ |= 256;
            this.fiamSdkVersion_ = str;
            return;
        }
        throw null;
    }

    /* access modifiers changed from: private */
    public void clearFiamSdkVersion() {
        this.bitField0_ &= -257;
        this.fiamSdkVersion_ = getDefaultInstance().getFiamSdkVersion();
    }

    /* access modifiers changed from: private */
    public void setFiamSdkVersionBytes(ByteString byteString) {
        if (byteString != null) {
            this.bitField0_ |= 256;
            this.fiamSdkVersion_ = byteString.toStringUtf8();
            return;
        }
        throw null;
    }

    public boolean hasEngagementMetricsDeliveryRetryCount() {
        return (this.bitField0_ & 512) == 512;
    }

    public int getEngagementMetricsDeliveryRetryCount() {
        return this.engagementMetricsDeliveryRetryCount_;
    }

    /* access modifiers changed from: private */
    public void setEngagementMetricsDeliveryRetryCount(int i) {
        this.bitField0_ |= 512;
        this.engagementMetricsDeliveryRetryCount_ = i;
    }

    /* access modifiers changed from: private */
    public void clearEngagementMetricsDeliveryRetryCount() {
        this.bitField0_ &= -513;
        this.engagementMetricsDeliveryRetryCount_ = 0;
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if ((this.bitField0_ & 1) == 1) {
            codedOutputStream.writeString(1, getProjectNumber());
        }
        if ((this.bitField0_ & 2) == 2) {
            codedOutputStream.writeString(2, getCampaignId());
        }
        if ((this.bitField0_ & 4) == 4) {
            codedOutputStream.writeMessage(3, getClientApp());
        }
        if ((this.bitField0_ & 8) == 8) {
            codedOutputStream.writeInt64(4, this.clientTimestampMillis_);
        }
        if (this.eventCase_ == 5) {
            codedOutputStream.writeEnum(5, ((Integer) this.event_).intValue());
        }
        if (this.eventCase_ == 6) {
            codedOutputStream.writeEnum(6, ((Integer) this.event_).intValue());
        }
        if (this.eventCase_ == 7) {
            codedOutputStream.writeEnum(7, ((Integer) this.event_).intValue());
        }
        if (this.eventCase_ == 8) {
            codedOutputStream.writeEnum(8, ((Integer) this.event_).intValue());
        }
        if ((this.bitField0_ & 256) == 256) {
            codedOutputStream.writeString(9, getFiamSdkVersion());
        }
        if ((this.bitField0_ & 512) == 512) {
            codedOutputStream.writeInt32(10, this.engagementMetricsDeliveryRetryCount_);
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
            i2 = 0 + CodedOutputStream.computeStringSize(1, getProjectNumber());
        }
        if ((this.bitField0_ & 2) == 2) {
            i2 += CodedOutputStream.computeStringSize(2, getCampaignId());
        }
        if ((this.bitField0_ & 4) == 4) {
            i2 += CodedOutputStream.computeMessageSize(3, getClientApp());
        }
        if ((this.bitField0_ & 8) == 8) {
            i2 += CodedOutputStream.computeInt64Size(4, this.clientTimestampMillis_);
        }
        if (this.eventCase_ == 5) {
            i2 += CodedOutputStream.computeEnumSize(5, ((Integer) this.event_).intValue());
        }
        if (this.eventCase_ == 6) {
            i2 += CodedOutputStream.computeEnumSize(6, ((Integer) this.event_).intValue());
        }
        if (this.eventCase_ == 7) {
            i2 += CodedOutputStream.computeEnumSize(7, ((Integer) this.event_).intValue());
        }
        if (this.eventCase_ == 8) {
            i2 += CodedOutputStream.computeEnumSize(8, ((Integer) this.event_).intValue());
        }
        if ((this.bitField0_ & 256) == 256) {
            i2 += CodedOutputStream.computeStringSize(9, getFiamSdkVersion());
        }
        if ((this.bitField0_ & 512) == 512) {
            i2 += CodedOutputStream.computeInt32Size(10, this.engagementMetricsDeliveryRetryCount_);
        }
        int serializedSize = i2 + this.unknownFields.getSerializedSize();
        this.memoizedSerializedSize = serializedSize;
        return serializedSize;
    }

    public static CampaignAnalytics parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (CampaignAnalytics) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static CampaignAnalytics parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (CampaignAnalytics) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static CampaignAnalytics parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (CampaignAnalytics) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static CampaignAnalytics parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (CampaignAnalytics) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static CampaignAnalytics parseFrom(InputStream inputStream) throws IOException {
        return (CampaignAnalytics) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static CampaignAnalytics parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (CampaignAnalytics) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static CampaignAnalytics parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (CampaignAnalytics) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static CampaignAnalytics parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (CampaignAnalytics) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static CampaignAnalytics parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (CampaignAnalytics) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static CampaignAnalytics parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (CampaignAnalytics) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(CampaignAnalytics campaignAnalytics) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(campaignAnalytics);
    }

    /* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
    public static final class Builder extends GeneratedMessageLite.Builder<CampaignAnalytics, Builder> implements CampaignAnalyticsOrBuilder {
        /* synthetic */ Builder(AnonymousClass1 r1) {
            this();
        }

        private Builder() {
            super(CampaignAnalytics.DEFAULT_INSTANCE);
        }

        public EventCase getEventCase() {
            return ((CampaignAnalytics) this.instance).getEventCase();
        }

        public Builder clearEvent() {
            copyOnWrite();
            ((CampaignAnalytics) this.instance).clearEvent();
            return this;
        }

        public boolean hasProjectNumber() {
            return ((CampaignAnalytics) this.instance).hasProjectNumber();
        }

        public String getProjectNumber() {
            return ((CampaignAnalytics) this.instance).getProjectNumber();
        }

        public ByteString getProjectNumberBytes() {
            return ((CampaignAnalytics) this.instance).getProjectNumberBytes();
        }

        public Builder setProjectNumber(String str) {
            copyOnWrite();
            ((CampaignAnalytics) this.instance).setProjectNumber(str);
            return this;
        }

        public Builder clearProjectNumber() {
            copyOnWrite();
            ((CampaignAnalytics) this.instance).clearProjectNumber();
            return this;
        }

        public Builder setProjectNumberBytes(ByteString byteString) {
            copyOnWrite();
            ((CampaignAnalytics) this.instance).setProjectNumberBytes(byteString);
            return this;
        }

        public boolean hasCampaignId() {
            return ((CampaignAnalytics) this.instance).hasCampaignId();
        }

        public String getCampaignId() {
            return ((CampaignAnalytics) this.instance).getCampaignId();
        }

        public ByteString getCampaignIdBytes() {
            return ((CampaignAnalytics) this.instance).getCampaignIdBytes();
        }

        public Builder setCampaignId(String str) {
            copyOnWrite();
            ((CampaignAnalytics) this.instance).setCampaignId(str);
            return this;
        }

        public Builder clearCampaignId() {
            copyOnWrite();
            ((CampaignAnalytics) this.instance).clearCampaignId();
            return this;
        }

        public Builder setCampaignIdBytes(ByteString byteString) {
            copyOnWrite();
            ((CampaignAnalytics) this.instance).setCampaignIdBytes(byteString);
            return this;
        }

        public boolean hasClientApp() {
            return ((CampaignAnalytics) this.instance).hasClientApp();
        }

        public ClientAppInfo getClientApp() {
            return ((CampaignAnalytics) this.instance).getClientApp();
        }

        public Builder setClientApp(ClientAppInfo clientAppInfo) {
            copyOnWrite();
            ((CampaignAnalytics) this.instance).setClientApp(clientAppInfo);
            return this;
        }

        public Builder setClientApp(ClientAppInfo.Builder builder) {
            copyOnWrite();
            ((CampaignAnalytics) this.instance).setClientApp(builder);
            return this;
        }

        public Builder mergeClientApp(ClientAppInfo clientAppInfo) {
            copyOnWrite();
            ((CampaignAnalytics) this.instance).mergeClientApp(clientAppInfo);
            return this;
        }

        public Builder clearClientApp() {
            copyOnWrite();
            ((CampaignAnalytics) this.instance).clearClientApp();
            return this;
        }

        public boolean hasClientTimestampMillis() {
            return ((CampaignAnalytics) this.instance).hasClientTimestampMillis();
        }

        public long getClientTimestampMillis() {
            return ((CampaignAnalytics) this.instance).getClientTimestampMillis();
        }

        public Builder setClientTimestampMillis(long j) {
            copyOnWrite();
            ((CampaignAnalytics) this.instance).setClientTimestampMillis(j);
            return this;
        }

        public Builder clearClientTimestampMillis() {
            copyOnWrite();
            ((CampaignAnalytics) this.instance).clearClientTimestampMillis();
            return this;
        }

        public boolean hasEventType() {
            return ((CampaignAnalytics) this.instance).hasEventType();
        }

        public EventType getEventType() {
            return ((CampaignAnalytics) this.instance).getEventType();
        }

        public Builder setEventType(EventType eventType) {
            copyOnWrite();
            ((CampaignAnalytics) this.instance).setEventType(eventType);
            return this;
        }

        public Builder clearEventType() {
            copyOnWrite();
            ((CampaignAnalytics) this.instance).clearEventType();
            return this;
        }

        public boolean hasDismissType() {
            return ((CampaignAnalytics) this.instance).hasDismissType();
        }

        public DismissType getDismissType() {
            return ((CampaignAnalytics) this.instance).getDismissType();
        }

        public Builder setDismissType(DismissType dismissType) {
            copyOnWrite();
            ((CampaignAnalytics) this.instance).setDismissType(dismissType);
            return this;
        }

        public Builder clearDismissType() {
            copyOnWrite();
            ((CampaignAnalytics) this.instance).clearDismissType();
            return this;
        }

        public boolean hasRenderErrorReason() {
            return ((CampaignAnalytics) this.instance).hasRenderErrorReason();
        }

        public RenderErrorReason getRenderErrorReason() {
            return ((CampaignAnalytics) this.instance).getRenderErrorReason();
        }

        public Builder setRenderErrorReason(RenderErrorReason renderErrorReason) {
            copyOnWrite();
            ((CampaignAnalytics) this.instance).setRenderErrorReason(renderErrorReason);
            return this;
        }

        public Builder clearRenderErrorReason() {
            copyOnWrite();
            ((CampaignAnalytics) this.instance).clearRenderErrorReason();
            return this;
        }

        public boolean hasFetchErrorReason() {
            return ((CampaignAnalytics) this.instance).hasFetchErrorReason();
        }

        public FetchErrorReason getFetchErrorReason() {
            return ((CampaignAnalytics) this.instance).getFetchErrorReason();
        }

        public Builder setFetchErrorReason(FetchErrorReason fetchErrorReason) {
            copyOnWrite();
            ((CampaignAnalytics) this.instance).setFetchErrorReason(fetchErrorReason);
            return this;
        }

        public Builder clearFetchErrorReason() {
            copyOnWrite();
            ((CampaignAnalytics) this.instance).clearFetchErrorReason();
            return this;
        }

        public boolean hasFiamSdkVersion() {
            return ((CampaignAnalytics) this.instance).hasFiamSdkVersion();
        }

        public String getFiamSdkVersion() {
            return ((CampaignAnalytics) this.instance).getFiamSdkVersion();
        }

        public ByteString getFiamSdkVersionBytes() {
            return ((CampaignAnalytics) this.instance).getFiamSdkVersionBytes();
        }

        public Builder setFiamSdkVersion(String str) {
            copyOnWrite();
            ((CampaignAnalytics) this.instance).setFiamSdkVersion(str);
            return this;
        }

        public Builder clearFiamSdkVersion() {
            copyOnWrite();
            ((CampaignAnalytics) this.instance).clearFiamSdkVersion();
            return this;
        }

        public Builder setFiamSdkVersionBytes(ByteString byteString) {
            copyOnWrite();
            ((CampaignAnalytics) this.instance).setFiamSdkVersionBytes(byteString);
            return this;
        }

        public boolean hasEngagementMetricsDeliveryRetryCount() {
            return ((CampaignAnalytics) this.instance).hasEngagementMetricsDeliveryRetryCount();
        }

        public int getEngagementMetricsDeliveryRetryCount() {
            return ((CampaignAnalytics) this.instance).getEngagementMetricsDeliveryRetryCount();
        }

        public Builder setEngagementMetricsDeliveryRetryCount(int i) {
            copyOnWrite();
            ((CampaignAnalytics) this.instance).setEngagementMetricsDeliveryRetryCount(i);
            return this;
        }

        public Builder clearEngagementMetricsDeliveryRetryCount() {
            copyOnWrite();
            ((CampaignAnalytics) this.instance).clearEngagementMetricsDeliveryRetryCount();
            return this;
        }
    }

    /* renamed from: com.google.firebase.inappmessaging.CampaignAnalytics$1  reason: invalid class name */
    /* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$firebase$inappmessaging$CampaignAnalytics$EventCase;
        static final /* synthetic */ int[] $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke;

        /* JADX WARNING: Can't wrap try/catch for region: R(29:0|(2:1|2)|3|(2:5|6)|7|9|10|11|(2:13|14)|15|17|18|19|20|21|22|(2:23|24)|25|27|28|29|30|31|32|33|34|35|36|38) */
        /* JADX WARNING: Can't wrap try/catch for region: R(30:0|1|2|3|(2:5|6)|7|9|10|11|(2:13|14)|15|17|18|19|20|21|22|(2:23|24)|25|27|28|29|30|31|32|33|34|35|36|38) */
        /* JADX WARNING: Can't wrap try/catch for region: R(31:0|1|2|3|(2:5|6)|7|9|10|11|13|14|15|17|18|19|20|21|22|(2:23|24)|25|27|28|29|30|31|32|33|34|35|36|38) */
        /* JADX WARNING: Can't wrap try/catch for region: R(33:0|1|2|3|5|6|7|9|10|11|13|14|15|17|18|19|20|21|22|23|24|25|27|28|29|30|31|32|33|34|35|36|38) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x0049 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x0054 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:29:0x0071 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:31:0x007b */
        /* JADX WARNING: Missing exception handler attribute for start block: B:33:0x0085 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:35:0x008f */
        static {
            /*
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke[] r0 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke = r0
                r1 = 1
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r2 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0[r2] = r1     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                r0 = 2
                int[] r2 = $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke     // Catch:{ NoSuchFieldError -> 0x001d }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r3 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.IS_INITIALIZED     // Catch:{ NoSuchFieldError -> 0x001d }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2[r3] = r0     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                r2 = 3
                int[] r3 = $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r4 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.MAKE_IMMUTABLE     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r3[r4] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                r3 = 4
                int[] r4 = $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r5 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.NEW_BUILDER     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r5 = r5.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r4[r5] = r3     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                r4 = 5
                int[] r5 = $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke     // Catch:{ NoSuchFieldError -> 0x003e }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r6 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.VISIT     // Catch:{ NoSuchFieldError -> 0x003e }
                int r6 = r6.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r5[r6] = r4     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r5 = $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke     // Catch:{ NoSuchFieldError -> 0x0049 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r6 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.MERGE_FROM_STREAM     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r6 = r6.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r7 = 6
                r5[r6] = r7     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                int[] r5 = $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke     // Catch:{ NoSuchFieldError -> 0x0054 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r6 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r6 = r6.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r7 = 7
                r5[r6] = r7     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                int[] r5 = $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke     // Catch:{ NoSuchFieldError -> 0x0060 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r6 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.GET_PARSER     // Catch:{ NoSuchFieldError -> 0x0060 }
                int r6 = r6.ordinal()     // Catch:{ NoSuchFieldError -> 0x0060 }
                r7 = 8
                r5[r6] = r7     // Catch:{ NoSuchFieldError -> 0x0060 }
            L_0x0060:
                com.google.firebase.inappmessaging.CampaignAnalytics$EventCase[] r5 = com.google.firebase.inappmessaging.CampaignAnalytics.EventCase.values()
                int r5 = r5.length
                int[] r5 = new int[r5]
                $SwitchMap$com$google$firebase$inappmessaging$CampaignAnalytics$EventCase = r5
                com.google.firebase.inappmessaging.CampaignAnalytics$EventCase r6 = com.google.firebase.inappmessaging.CampaignAnalytics.EventCase.EVENT_TYPE     // Catch:{ NoSuchFieldError -> 0x0071 }
                int r6 = r6.ordinal()     // Catch:{ NoSuchFieldError -> 0x0071 }
                r5[r6] = r1     // Catch:{ NoSuchFieldError -> 0x0071 }
            L_0x0071:
                int[] r1 = $SwitchMap$com$google$firebase$inappmessaging$CampaignAnalytics$EventCase     // Catch:{ NoSuchFieldError -> 0x007b }
                com.google.firebase.inappmessaging.CampaignAnalytics$EventCase r5 = com.google.firebase.inappmessaging.CampaignAnalytics.EventCase.DISMISS_TYPE     // Catch:{ NoSuchFieldError -> 0x007b }
                int r5 = r5.ordinal()     // Catch:{ NoSuchFieldError -> 0x007b }
                r1[r5] = r0     // Catch:{ NoSuchFieldError -> 0x007b }
            L_0x007b:
                int[] r0 = $SwitchMap$com$google$firebase$inappmessaging$CampaignAnalytics$EventCase     // Catch:{ NoSuchFieldError -> 0x0085 }
                com.google.firebase.inappmessaging.CampaignAnalytics$EventCase r1 = com.google.firebase.inappmessaging.CampaignAnalytics.EventCase.RENDER_ERROR_REASON     // Catch:{ NoSuchFieldError -> 0x0085 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0085 }
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0085 }
            L_0x0085:
                int[] r0 = $SwitchMap$com$google$firebase$inappmessaging$CampaignAnalytics$EventCase     // Catch:{ NoSuchFieldError -> 0x008f }
                com.google.firebase.inappmessaging.CampaignAnalytics$EventCase r1 = com.google.firebase.inappmessaging.CampaignAnalytics.EventCase.FETCH_ERROR_REASON     // Catch:{ NoSuchFieldError -> 0x008f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x008f }
                r0[r1] = r3     // Catch:{ NoSuchFieldError -> 0x008f }
            L_0x008f:
                int[] r0 = $SwitchMap$com$google$firebase$inappmessaging$CampaignAnalytics$EventCase     // Catch:{ NoSuchFieldError -> 0x0099 }
                com.google.firebase.inappmessaging.CampaignAnalytics$EventCase r1 = com.google.firebase.inappmessaging.CampaignAnalytics.EventCase.EVENT_NOT_SET     // Catch:{ NoSuchFieldError -> 0x0099 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0099 }
                r0[r1] = r4     // Catch:{ NoSuchFieldError -> 0x0099 }
            L_0x0099:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.inappmessaging.CampaignAnalytics.AnonymousClass1.<clinit>():void");
        }
    }

    /* access modifiers changed from: protected */
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        boolean z = false;
        switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
            case 1:
                return new CampaignAnalytics();
            case 2:
                return DEFAULT_INSTANCE;
            case 3:
                return null;
            case 4:
                return new Builder((AnonymousClass1) null);
            case 5:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                CampaignAnalytics campaignAnalytics = (CampaignAnalytics) obj2;
                this.projectNumber_ = visitor.visitString(hasProjectNumber(), this.projectNumber_, campaignAnalytics.hasProjectNumber(), campaignAnalytics.projectNumber_);
                this.campaignId_ = visitor.visitString(hasCampaignId(), this.campaignId_, campaignAnalytics.hasCampaignId(), campaignAnalytics.campaignId_);
                this.clientApp_ = (ClientAppInfo) visitor.visitMessage(this.clientApp_, campaignAnalytics.clientApp_);
                this.clientTimestampMillis_ = visitor.visitLong(hasClientTimestampMillis(), this.clientTimestampMillis_, campaignAnalytics.hasClientTimestampMillis(), campaignAnalytics.clientTimestampMillis_);
                this.fiamSdkVersion_ = visitor.visitString(hasFiamSdkVersion(), this.fiamSdkVersion_, campaignAnalytics.hasFiamSdkVersion(), campaignAnalytics.fiamSdkVersion_);
                this.engagementMetricsDeliveryRetryCount_ = visitor.visitInt(hasEngagementMetricsDeliveryRetryCount(), this.engagementMetricsDeliveryRetryCount_, campaignAnalytics.hasEngagementMetricsDeliveryRetryCount(), campaignAnalytics.engagementMetricsDeliveryRetryCount_);
                int i = AnonymousClass1.$SwitchMap$com$google$firebase$inappmessaging$CampaignAnalytics$EventCase[campaignAnalytics.getEventCase().ordinal()];
                if (i == 1) {
                    if (this.eventCase_ == 5) {
                        z = true;
                    }
                    this.event_ = visitor.visitOneofInt(z, this.event_, campaignAnalytics.event_);
                } else if (i == 2) {
                    if (this.eventCase_ == 6) {
                        z = true;
                    }
                    this.event_ = visitor.visitOneofInt(z, this.event_, campaignAnalytics.event_);
                } else if (i == 3) {
                    if (this.eventCase_ == 7) {
                        z = true;
                    }
                    this.event_ = visitor.visitOneofInt(z, this.event_, campaignAnalytics.event_);
                } else if (i == 4) {
                    if (this.eventCase_ == 8) {
                        z = true;
                    }
                    this.event_ = visitor.visitOneofInt(z, this.event_, campaignAnalytics.event_);
                } else if (i == 5) {
                    if (this.eventCase_ != 0) {
                        z = true;
                    }
                    visitor.visitOneofNotSet(z);
                }
                if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                    int i2 = campaignAnalytics.eventCase_;
                    if (i2 != 0) {
                        this.eventCase_ = i2;
                    }
                    this.bitField0_ |= campaignAnalytics.bitField0_;
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
                                String readString = codedInputStream.readString();
                                this.bitField0_ |= 1;
                                this.projectNumber_ = readString;
                                break;
                            case 18:
                                String readString2 = codedInputStream.readString();
                                this.bitField0_ |= 2;
                                this.campaignId_ = readString2;
                                break;
                            case 26:
                                ClientAppInfo.Builder builder = (this.bitField0_ & 4) == 4 ? (ClientAppInfo.Builder) this.clientApp_.toBuilder() : null;
                                ClientAppInfo clientAppInfo = (ClientAppInfo) codedInputStream.readMessage(ClientAppInfo.parser(), extensionRegistryLite);
                                this.clientApp_ = clientAppInfo;
                                if (builder != null) {
                                    builder.mergeFrom(clientAppInfo);
                                    this.clientApp_ = (ClientAppInfo) builder.buildPartial();
                                }
                                this.bitField0_ |= 4;
                                break;
                            case 32:
                                this.bitField0_ |= 8;
                                this.clientTimestampMillis_ = codedInputStream.readInt64();
                                break;
                            case 40:
                                int readEnum = codedInputStream.readEnum();
                                if (EventType.forNumber(readEnum) != null) {
                                    this.eventCase_ = 5;
                                    this.event_ = Integer.valueOf(readEnum);
                                    break;
                                } else {
                                    super.mergeVarintField(5, readEnum);
                                    break;
                                }
                            case 48:
                                int readEnum2 = codedInputStream.readEnum();
                                if (DismissType.forNumber(readEnum2) != null) {
                                    this.eventCase_ = 6;
                                    this.event_ = Integer.valueOf(readEnum2);
                                    break;
                                } else {
                                    super.mergeVarintField(6, readEnum2);
                                    break;
                                }
                            case 56:
                                int readEnum3 = codedInputStream.readEnum();
                                if (RenderErrorReason.forNumber(readEnum3) != null) {
                                    this.eventCase_ = 7;
                                    this.event_ = Integer.valueOf(readEnum3);
                                    break;
                                } else {
                                    super.mergeVarintField(7, readEnum3);
                                    break;
                                }
                            case 64:
                                int readEnum4 = codedInputStream.readEnum();
                                if (FetchErrorReason.forNumber(readEnum4) != null) {
                                    this.eventCase_ = 8;
                                    this.event_ = Integer.valueOf(readEnum4);
                                    break;
                                } else {
                                    super.mergeVarintField(8, readEnum4);
                                    break;
                                }
                            case 74:
                                String readString3 = codedInputStream.readString();
                                this.bitField0_ |= 256;
                                this.fiamSdkVersion_ = readString3;
                                break;
                            case 80:
                                this.bitField0_ |= 512;
                                this.engagementMetricsDeliveryRetryCount_ = codedInputStream.readInt32();
                                break;
                            default:
                                if (parseUnknownField(readTag, codedInputStream)) {
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
                    synchronized (CampaignAnalytics.class) {
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
        CampaignAnalytics campaignAnalytics = new CampaignAnalytics();
        DEFAULT_INSTANCE = campaignAnalytics;
        campaignAnalytics.makeImmutable();
    }

    public static CampaignAnalytics getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<CampaignAnalytics> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
