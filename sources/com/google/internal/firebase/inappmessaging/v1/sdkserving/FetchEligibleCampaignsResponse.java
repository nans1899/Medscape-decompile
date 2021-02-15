package com.google.internal.firebase.inappmessaging.v1.sdkserving;

import com.google.internal.firebase.inappmessaging.v1.CampaignProto;
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
public final class FetchEligibleCampaignsResponse extends GeneratedMessageLite<FetchEligibleCampaignsResponse, Builder> implements FetchEligibleCampaignsResponseOrBuilder {
    /* access modifiers changed from: private */
    public static final FetchEligibleCampaignsResponse DEFAULT_INSTANCE;
    public static final int EXPIRATION_EPOCH_TIMESTAMP_MILLIS_FIELD_NUMBER = 2;
    public static final int MESSAGES_FIELD_NUMBER = 1;
    private static volatile Parser<FetchEligibleCampaignsResponse> PARSER;
    private int bitField0_;
    private long expirationEpochTimestampMillis_;
    private Internal.ProtobufList<CampaignProto.ThickContent> messages_ = emptyProtobufList();

    private FetchEligibleCampaignsResponse() {
    }

    public List<CampaignProto.ThickContent> getMessagesList() {
        return this.messages_;
    }

    public List<? extends CampaignProto.ThickContentOrBuilder> getMessagesOrBuilderList() {
        return this.messages_;
    }

    public int getMessagesCount() {
        return this.messages_.size();
    }

    public CampaignProto.ThickContent getMessages(int i) {
        return (CampaignProto.ThickContent) this.messages_.get(i);
    }

    public CampaignProto.ThickContentOrBuilder getMessagesOrBuilder(int i) {
        return (CampaignProto.ThickContentOrBuilder) this.messages_.get(i);
    }

    private void ensureMessagesIsMutable() {
        if (!this.messages_.isModifiable()) {
            this.messages_ = GeneratedMessageLite.mutableCopy(this.messages_);
        }
    }

    /* access modifiers changed from: private */
    public void setMessages(int i, CampaignProto.ThickContent thickContent) {
        if (thickContent != null) {
            ensureMessagesIsMutable();
            this.messages_.set(i, thickContent);
            return;
        }
        throw null;
    }

    /* access modifiers changed from: private */
    public void setMessages(int i, CampaignProto.ThickContent.Builder builder) {
        ensureMessagesIsMutable();
        this.messages_.set(i, (CampaignProto.ThickContent) builder.build());
    }

    /* access modifiers changed from: private */
    public void addMessages(CampaignProto.ThickContent thickContent) {
        if (thickContent != null) {
            ensureMessagesIsMutable();
            this.messages_.add(thickContent);
            return;
        }
        throw null;
    }

    /* access modifiers changed from: private */
    public void addMessages(int i, CampaignProto.ThickContent thickContent) {
        if (thickContent != null) {
            ensureMessagesIsMutable();
            this.messages_.add(i, thickContent);
            return;
        }
        throw null;
    }

    /* access modifiers changed from: private */
    public void addMessages(CampaignProto.ThickContent.Builder builder) {
        ensureMessagesIsMutable();
        this.messages_.add((CampaignProto.ThickContent) builder.build());
    }

    /* access modifiers changed from: private */
    public void addMessages(int i, CampaignProto.ThickContent.Builder builder) {
        ensureMessagesIsMutable();
        this.messages_.add(i, (CampaignProto.ThickContent) builder.build());
    }

    /* access modifiers changed from: private */
    public void addAllMessages(Iterable<? extends CampaignProto.ThickContent> iterable) {
        ensureMessagesIsMutable();
        AbstractMessageLite.addAll(iterable, this.messages_);
    }

    /* access modifiers changed from: private */
    public void clearMessages() {
        this.messages_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    public void removeMessages(int i) {
        ensureMessagesIsMutable();
        this.messages_.remove(i);
    }

    public long getExpirationEpochTimestampMillis() {
        return this.expirationEpochTimestampMillis_;
    }

    /* access modifiers changed from: private */
    public void setExpirationEpochTimestampMillis(long j) {
        this.expirationEpochTimestampMillis_ = j;
    }

    /* access modifiers changed from: private */
    public void clearExpirationEpochTimestampMillis() {
        this.expirationEpochTimestampMillis_ = 0;
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        for (int i = 0; i < this.messages_.size(); i++) {
            codedOutputStream.writeMessage(1, (MessageLite) this.messages_.get(i));
        }
        long j = this.expirationEpochTimestampMillis_;
        if (j != 0) {
            codedOutputStream.writeInt64(2, j);
        }
    }

    public int getSerializedSize() {
        int i = this.memoizedSerializedSize;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < this.messages_.size(); i3++) {
            i2 += CodedOutputStream.computeMessageSize(1, (MessageLite) this.messages_.get(i3));
        }
        long j = this.expirationEpochTimestampMillis_;
        if (j != 0) {
            i2 += CodedOutputStream.computeInt64Size(2, j);
        }
        this.memoizedSerializedSize = i2;
        return i2;
    }

    public static FetchEligibleCampaignsResponse parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (FetchEligibleCampaignsResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static FetchEligibleCampaignsResponse parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (FetchEligibleCampaignsResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static FetchEligibleCampaignsResponse parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (FetchEligibleCampaignsResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static FetchEligibleCampaignsResponse parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (FetchEligibleCampaignsResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static FetchEligibleCampaignsResponse parseFrom(InputStream inputStream) throws IOException {
        return (FetchEligibleCampaignsResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static FetchEligibleCampaignsResponse parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (FetchEligibleCampaignsResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static FetchEligibleCampaignsResponse parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (FetchEligibleCampaignsResponse) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static FetchEligibleCampaignsResponse parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (FetchEligibleCampaignsResponse) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static FetchEligibleCampaignsResponse parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (FetchEligibleCampaignsResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static FetchEligibleCampaignsResponse parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (FetchEligibleCampaignsResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(FetchEligibleCampaignsResponse fetchEligibleCampaignsResponse) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(fetchEligibleCampaignsResponse);
    }

    /* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
    public static final class Builder extends GeneratedMessageLite.Builder<FetchEligibleCampaignsResponse, Builder> implements FetchEligibleCampaignsResponseOrBuilder {
        /* synthetic */ Builder(AnonymousClass1 r1) {
            this();
        }

        private Builder() {
            super(FetchEligibleCampaignsResponse.DEFAULT_INSTANCE);
        }

        public List<CampaignProto.ThickContent> getMessagesList() {
            return Collections.unmodifiableList(((FetchEligibleCampaignsResponse) this.instance).getMessagesList());
        }

        public int getMessagesCount() {
            return ((FetchEligibleCampaignsResponse) this.instance).getMessagesCount();
        }

        public CampaignProto.ThickContent getMessages(int i) {
            return ((FetchEligibleCampaignsResponse) this.instance).getMessages(i);
        }

        public Builder setMessages(int i, CampaignProto.ThickContent thickContent) {
            copyOnWrite();
            ((FetchEligibleCampaignsResponse) this.instance).setMessages(i, thickContent);
            return this;
        }

        public Builder setMessages(int i, CampaignProto.ThickContent.Builder builder) {
            copyOnWrite();
            ((FetchEligibleCampaignsResponse) this.instance).setMessages(i, builder);
            return this;
        }

        public Builder addMessages(CampaignProto.ThickContent thickContent) {
            copyOnWrite();
            ((FetchEligibleCampaignsResponse) this.instance).addMessages(thickContent);
            return this;
        }

        public Builder addMessages(int i, CampaignProto.ThickContent thickContent) {
            copyOnWrite();
            ((FetchEligibleCampaignsResponse) this.instance).addMessages(i, thickContent);
            return this;
        }

        public Builder addMessages(CampaignProto.ThickContent.Builder builder) {
            copyOnWrite();
            ((FetchEligibleCampaignsResponse) this.instance).addMessages(builder);
            return this;
        }

        public Builder addMessages(int i, CampaignProto.ThickContent.Builder builder) {
            copyOnWrite();
            ((FetchEligibleCampaignsResponse) this.instance).addMessages(i, builder);
            return this;
        }

        public Builder addAllMessages(Iterable<? extends CampaignProto.ThickContent> iterable) {
            copyOnWrite();
            ((FetchEligibleCampaignsResponse) this.instance).addAllMessages(iterable);
            return this;
        }

        public Builder clearMessages() {
            copyOnWrite();
            ((FetchEligibleCampaignsResponse) this.instance).clearMessages();
            return this;
        }

        public Builder removeMessages(int i) {
            copyOnWrite();
            ((FetchEligibleCampaignsResponse) this.instance).removeMessages(i);
            return this;
        }

        public long getExpirationEpochTimestampMillis() {
            return ((FetchEligibleCampaignsResponse) this.instance).getExpirationEpochTimestampMillis();
        }

        public Builder setExpirationEpochTimestampMillis(long j) {
            copyOnWrite();
            ((FetchEligibleCampaignsResponse) this.instance).setExpirationEpochTimestampMillis(j);
            return this;
        }

        public Builder clearExpirationEpochTimestampMillis() {
            copyOnWrite();
            ((FetchEligibleCampaignsResponse) this.instance).clearExpirationEpochTimestampMillis();
            return this;
        }
    }

    /* renamed from: com.google.internal.firebase.inappmessaging.v1.sdkserving.FetchEligibleCampaignsResponse$1  reason: invalid class name */
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.internal.firebase.inappmessaging.v1.sdkserving.FetchEligibleCampaignsResponse.AnonymousClass1.<clinit>():void");
        }
    }

    /* access modifiers changed from: protected */
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        boolean z = false;
        switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
            case 1:
                return new FetchEligibleCampaignsResponse();
            case 2:
                return DEFAULT_INSTANCE;
            case 3:
                this.messages_.makeImmutable();
                return null;
            case 4:
                return new Builder((AnonymousClass1) null);
            case 5:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                FetchEligibleCampaignsResponse fetchEligibleCampaignsResponse = (FetchEligibleCampaignsResponse) obj2;
                this.messages_ = visitor.visitList(this.messages_, fetchEligibleCampaignsResponse.messages_);
                this.expirationEpochTimestampMillis_ = visitor.visitLong(this.expirationEpochTimestampMillis_ != 0, this.expirationEpochTimestampMillis_, fetchEligibleCampaignsResponse.expirationEpochTimestampMillis_ != 0, fetchEligibleCampaignsResponse.expirationEpochTimestampMillis_);
                if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                    this.bitField0_ |= fetchEligibleCampaignsResponse.bitField0_;
                }
                return this;
            case 6:
                CodedInputStream codedInputStream = (CodedInputStream) obj;
                ExtensionRegistryLite extensionRegistryLite = (ExtensionRegistryLite) obj2;
                while (!z) {
                    try {
                        int readTag = codedInputStream.readTag();
                        if (readTag != 0) {
                            if (readTag == 10) {
                                if (!this.messages_.isModifiable()) {
                                    this.messages_ = GeneratedMessageLite.mutableCopy(this.messages_);
                                }
                                this.messages_.add((CampaignProto.ThickContent) codedInputStream.readMessage(CampaignProto.ThickContent.parser(), extensionRegistryLite));
                            } else if (readTag == 16) {
                                this.expirationEpochTimestampMillis_ = codedInputStream.readInt64();
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
                    synchronized (FetchEligibleCampaignsResponse.class) {
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
        FetchEligibleCampaignsResponse fetchEligibleCampaignsResponse = new FetchEligibleCampaignsResponse();
        DEFAULT_INSTANCE = fetchEligibleCampaignsResponse;
        fetchEligibleCampaignsResponse.makeImmutable();
    }

    public static FetchEligibleCampaignsResponse getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<FetchEligibleCampaignsResponse> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
