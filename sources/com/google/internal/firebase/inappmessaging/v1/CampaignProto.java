package com.google.internal.firebase.inappmessaging.v1;

import com.google.firebase.inappmessaging.CommonTypesProto;
import com.google.firebase.inappmessaging.MessagesProto;
import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MapEntryLite;
import com.google.protobuf.MapFieldLite;
import com.google.protobuf.MessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import com.google.protobuf.WireFormat;
import developers.mobile.abt.FirebaseAbt;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
public final class CampaignProto {

    /* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
    public interface ExperimentalCampaignPayloadOrBuilder extends MessageLiteOrBuilder {
        long getCampaignEndTimeMillis();

        String getCampaignId();

        ByteString getCampaignIdBytes();

        String getCampaignName();

        ByteString getCampaignNameBytes();

        long getCampaignStartTimeMillis();

        FirebaseAbt.ExperimentPayload getExperimentPayload();

        boolean hasExperimentPayload();
    }

    /* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
    public interface ExperimentalCampaignRolloutOrBuilder extends MessageLiteOrBuilder {
        CommonTypesProto.CampaignTime getEndTime();

        String getExperimentId();

        ByteString getExperimentIdBytes();

        CommonTypesProto.Priority getPriority();

        int getSelectedVariantIndex();

        CommonTypesProto.CampaignTime getStartTime();

        boolean hasEndTime();

        boolean hasPriority();

        boolean hasStartTime();
    }

    /* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
    public interface ThickContentOrBuilder extends MessageLiteOrBuilder {
        boolean containsDataBundle(String str);

        MessagesProto.Content getContent();

        @Deprecated
        Map<String, String> getDataBundle();

        int getDataBundleCount();

        Map<String, String> getDataBundleMap();

        String getDataBundleOrDefault(String str, String str2);

        String getDataBundleOrThrow(String str);

        ExperimentalCampaignPayload getExperimentalPayload();

        boolean getIsTestCampaign();

        ThickContent.PayloadCase getPayloadCase();

        CommonTypesProto.Priority getPriority();

        CommonTypesProto.TriggeringCondition getTriggeringConditions(int i);

        int getTriggeringConditionsCount();

        List<CommonTypesProto.TriggeringCondition> getTriggeringConditionsList();

        VanillaCampaignPayload getVanillaPayload();

        boolean hasContent();

        boolean hasPriority();
    }

    /* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
    public interface VanillaCampaignPayloadOrBuilder extends MessageLiteOrBuilder {
        long getCampaignEndTimeMillis();

        String getCampaignId();

        ByteString getCampaignIdBytes();

        String getCampaignName();

        ByteString getCampaignNameBytes();

        long getCampaignStartTimeMillis();

        String getExperimentalCampaignId();

        ByteString getExperimentalCampaignIdBytes();
    }

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }

    private CampaignProto() {
    }

    /* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
    public static final class ExperimentalCampaignRollout extends GeneratedMessageLite<ExperimentalCampaignRollout, Builder> implements ExperimentalCampaignRolloutOrBuilder {
        /* access modifiers changed from: private */
        public static final ExperimentalCampaignRollout DEFAULT_INSTANCE;
        public static final int END_TIME_FIELD_NUMBER = 5;
        public static final int EXPERIMENT_ID_FIELD_NUMBER = 1;
        private static volatile Parser<ExperimentalCampaignRollout> PARSER = null;
        public static final int PRIORITY_FIELD_NUMBER = 3;
        public static final int SELECTED_VARIANT_INDEX_FIELD_NUMBER = 2;
        public static final int START_TIME_FIELD_NUMBER = 4;
        private CommonTypesProto.CampaignTime endTime_;
        private String experimentId_ = "";
        private CommonTypesProto.Priority priority_;
        private int selectedVariantIndex_;
        private CommonTypesProto.CampaignTime startTime_;

        private ExperimentalCampaignRollout() {
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

        public int getSelectedVariantIndex() {
            return this.selectedVariantIndex_;
        }

        /* access modifiers changed from: private */
        public void setSelectedVariantIndex(int i) {
            this.selectedVariantIndex_ = i;
        }

        /* access modifiers changed from: private */
        public void clearSelectedVariantIndex() {
            this.selectedVariantIndex_ = 0;
        }

        public boolean hasPriority() {
            return this.priority_ != null;
        }

        public CommonTypesProto.Priority getPriority() {
            CommonTypesProto.Priority priority = this.priority_;
            return priority == null ? CommonTypesProto.Priority.getDefaultInstance() : priority;
        }

        /* access modifiers changed from: private */
        public void setPriority(CommonTypesProto.Priority priority) {
            if (priority != null) {
                this.priority_ = priority;
                return;
            }
            throw null;
        }

        /* access modifiers changed from: private */
        public void setPriority(CommonTypesProto.Priority.Builder builder) {
            this.priority_ = (CommonTypesProto.Priority) builder.build();
        }

        /* access modifiers changed from: private */
        public void mergePriority(CommonTypesProto.Priority priority) {
            CommonTypesProto.Priority priority2 = this.priority_;
            if (priority2 == null || priority2 == CommonTypesProto.Priority.getDefaultInstance()) {
                this.priority_ = priority;
            } else {
                this.priority_ = (CommonTypesProto.Priority) ((CommonTypesProto.Priority.Builder) CommonTypesProto.Priority.newBuilder(this.priority_).mergeFrom(priority)).buildPartial();
            }
        }

        /* access modifiers changed from: private */
        public void clearPriority() {
            this.priority_ = null;
        }

        public boolean hasStartTime() {
            return this.startTime_ != null;
        }

        public CommonTypesProto.CampaignTime getStartTime() {
            CommonTypesProto.CampaignTime campaignTime = this.startTime_;
            return campaignTime == null ? CommonTypesProto.CampaignTime.getDefaultInstance() : campaignTime;
        }

        /* access modifiers changed from: private */
        public void setStartTime(CommonTypesProto.CampaignTime campaignTime) {
            if (campaignTime != null) {
                this.startTime_ = campaignTime;
                return;
            }
            throw null;
        }

        /* access modifiers changed from: private */
        public void setStartTime(CommonTypesProto.CampaignTime.Builder builder) {
            this.startTime_ = (CommonTypesProto.CampaignTime) builder.build();
        }

        /* access modifiers changed from: private */
        public void mergeStartTime(CommonTypesProto.CampaignTime campaignTime) {
            CommonTypesProto.CampaignTime campaignTime2 = this.startTime_;
            if (campaignTime2 == null || campaignTime2 == CommonTypesProto.CampaignTime.getDefaultInstance()) {
                this.startTime_ = campaignTime;
            } else {
                this.startTime_ = (CommonTypesProto.CampaignTime) ((CommonTypesProto.CampaignTime.Builder) CommonTypesProto.CampaignTime.newBuilder(this.startTime_).mergeFrom(campaignTime)).buildPartial();
            }
        }

        /* access modifiers changed from: private */
        public void clearStartTime() {
            this.startTime_ = null;
        }

        public boolean hasEndTime() {
            return this.endTime_ != null;
        }

        public CommonTypesProto.CampaignTime getEndTime() {
            CommonTypesProto.CampaignTime campaignTime = this.endTime_;
            return campaignTime == null ? CommonTypesProto.CampaignTime.getDefaultInstance() : campaignTime;
        }

        /* access modifiers changed from: private */
        public void setEndTime(CommonTypesProto.CampaignTime campaignTime) {
            if (campaignTime != null) {
                this.endTime_ = campaignTime;
                return;
            }
            throw null;
        }

        /* access modifiers changed from: private */
        public void setEndTime(CommonTypesProto.CampaignTime.Builder builder) {
            this.endTime_ = (CommonTypesProto.CampaignTime) builder.build();
        }

        /* access modifiers changed from: private */
        public void mergeEndTime(CommonTypesProto.CampaignTime campaignTime) {
            CommonTypesProto.CampaignTime campaignTime2 = this.endTime_;
            if (campaignTime2 == null || campaignTime2 == CommonTypesProto.CampaignTime.getDefaultInstance()) {
                this.endTime_ = campaignTime;
            } else {
                this.endTime_ = (CommonTypesProto.CampaignTime) ((CommonTypesProto.CampaignTime.Builder) CommonTypesProto.CampaignTime.newBuilder(this.endTime_).mergeFrom(campaignTime)).buildPartial();
            }
        }

        /* access modifiers changed from: private */
        public void clearEndTime() {
            this.endTime_ = null;
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (!this.experimentId_.isEmpty()) {
                codedOutputStream.writeString(1, getExperimentId());
            }
            int i = this.selectedVariantIndex_;
            if (i != 0) {
                codedOutputStream.writeInt32(2, i);
            }
            if (this.priority_ != null) {
                codedOutputStream.writeMessage(3, getPriority());
            }
            if (this.startTime_ != null) {
                codedOutputStream.writeMessage(4, getStartTime());
            }
            if (this.endTime_ != null) {
                codedOutputStream.writeMessage(5, getEndTime());
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
            int i3 = this.selectedVariantIndex_;
            if (i3 != 0) {
                i2 += CodedOutputStream.computeInt32Size(2, i3);
            }
            if (this.priority_ != null) {
                i2 += CodedOutputStream.computeMessageSize(3, getPriority());
            }
            if (this.startTime_ != null) {
                i2 += CodedOutputStream.computeMessageSize(4, getStartTime());
            }
            if (this.endTime_ != null) {
                i2 += CodedOutputStream.computeMessageSize(5, getEndTime());
            }
            this.memoizedSerializedSize = i2;
            return i2;
        }

        public static ExperimentalCampaignRollout parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (ExperimentalCampaignRollout) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static ExperimentalCampaignRollout parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (ExperimentalCampaignRollout) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static ExperimentalCampaignRollout parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (ExperimentalCampaignRollout) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static ExperimentalCampaignRollout parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (ExperimentalCampaignRollout) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static ExperimentalCampaignRollout parseFrom(InputStream inputStream) throws IOException {
            return (ExperimentalCampaignRollout) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static ExperimentalCampaignRollout parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ExperimentalCampaignRollout) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static ExperimentalCampaignRollout parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (ExperimentalCampaignRollout) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static ExperimentalCampaignRollout parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ExperimentalCampaignRollout) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static ExperimentalCampaignRollout parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (ExperimentalCampaignRollout) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static ExperimentalCampaignRollout parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ExperimentalCampaignRollout) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(ExperimentalCampaignRollout experimentalCampaignRollout) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(experimentalCampaignRollout);
        }

        /* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
        public static final class Builder extends GeneratedMessageLite.Builder<ExperimentalCampaignRollout, Builder> implements ExperimentalCampaignRolloutOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 r1) {
                this();
            }

            private Builder() {
                super(ExperimentalCampaignRollout.DEFAULT_INSTANCE);
            }

            public String getExperimentId() {
                return ((ExperimentalCampaignRollout) this.instance).getExperimentId();
            }

            public ByteString getExperimentIdBytes() {
                return ((ExperimentalCampaignRollout) this.instance).getExperimentIdBytes();
            }

            public Builder setExperimentId(String str) {
                copyOnWrite();
                ((ExperimentalCampaignRollout) this.instance).setExperimentId(str);
                return this;
            }

            public Builder clearExperimentId() {
                copyOnWrite();
                ((ExperimentalCampaignRollout) this.instance).clearExperimentId();
                return this;
            }

            public Builder setExperimentIdBytes(ByteString byteString) {
                copyOnWrite();
                ((ExperimentalCampaignRollout) this.instance).setExperimentIdBytes(byteString);
                return this;
            }

            public int getSelectedVariantIndex() {
                return ((ExperimentalCampaignRollout) this.instance).getSelectedVariantIndex();
            }

            public Builder setSelectedVariantIndex(int i) {
                copyOnWrite();
                ((ExperimentalCampaignRollout) this.instance).setSelectedVariantIndex(i);
                return this;
            }

            public Builder clearSelectedVariantIndex() {
                copyOnWrite();
                ((ExperimentalCampaignRollout) this.instance).clearSelectedVariantIndex();
                return this;
            }

            public boolean hasPriority() {
                return ((ExperimentalCampaignRollout) this.instance).hasPriority();
            }

            public CommonTypesProto.Priority getPriority() {
                return ((ExperimentalCampaignRollout) this.instance).getPriority();
            }

            public Builder setPriority(CommonTypesProto.Priority priority) {
                copyOnWrite();
                ((ExperimentalCampaignRollout) this.instance).setPriority(priority);
                return this;
            }

            public Builder setPriority(CommonTypesProto.Priority.Builder builder) {
                copyOnWrite();
                ((ExperimentalCampaignRollout) this.instance).setPriority(builder);
                return this;
            }

            public Builder mergePriority(CommonTypesProto.Priority priority) {
                copyOnWrite();
                ((ExperimentalCampaignRollout) this.instance).mergePriority(priority);
                return this;
            }

            public Builder clearPriority() {
                copyOnWrite();
                ((ExperimentalCampaignRollout) this.instance).clearPriority();
                return this;
            }

            public boolean hasStartTime() {
                return ((ExperimentalCampaignRollout) this.instance).hasStartTime();
            }

            public CommonTypesProto.CampaignTime getStartTime() {
                return ((ExperimentalCampaignRollout) this.instance).getStartTime();
            }

            public Builder setStartTime(CommonTypesProto.CampaignTime campaignTime) {
                copyOnWrite();
                ((ExperimentalCampaignRollout) this.instance).setStartTime(campaignTime);
                return this;
            }

            public Builder setStartTime(CommonTypesProto.CampaignTime.Builder builder) {
                copyOnWrite();
                ((ExperimentalCampaignRollout) this.instance).setStartTime(builder);
                return this;
            }

            public Builder mergeStartTime(CommonTypesProto.CampaignTime campaignTime) {
                copyOnWrite();
                ((ExperimentalCampaignRollout) this.instance).mergeStartTime(campaignTime);
                return this;
            }

            public Builder clearStartTime() {
                copyOnWrite();
                ((ExperimentalCampaignRollout) this.instance).clearStartTime();
                return this;
            }

            public boolean hasEndTime() {
                return ((ExperimentalCampaignRollout) this.instance).hasEndTime();
            }

            public CommonTypesProto.CampaignTime getEndTime() {
                return ((ExperimentalCampaignRollout) this.instance).getEndTime();
            }

            public Builder setEndTime(CommonTypesProto.CampaignTime campaignTime) {
                copyOnWrite();
                ((ExperimentalCampaignRollout) this.instance).setEndTime(campaignTime);
                return this;
            }

            public Builder setEndTime(CommonTypesProto.CampaignTime.Builder builder) {
                copyOnWrite();
                ((ExperimentalCampaignRollout) this.instance).setEndTime(builder);
                return this;
            }

            public Builder mergeEndTime(CommonTypesProto.CampaignTime campaignTime) {
                copyOnWrite();
                ((ExperimentalCampaignRollout) this.instance).mergeEndTime(campaignTime);
                return this;
            }

            public Builder clearEndTime() {
                copyOnWrite();
                ((ExperimentalCampaignRollout) this.instance).clearEndTime();
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            boolean z = false;
            switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
                case 1:
                    return new ExperimentalCampaignRollout();
                case 2:
                    return DEFAULT_INSTANCE;
                case 3:
                    return null;
                case 4:
                    return new Builder((AnonymousClass1) null);
                case 5:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                    ExperimentalCampaignRollout experimentalCampaignRollout = (ExperimentalCampaignRollout) obj2;
                    this.experimentId_ = visitor.visitString(!this.experimentId_.isEmpty(), this.experimentId_, !experimentalCampaignRollout.experimentId_.isEmpty(), experimentalCampaignRollout.experimentId_);
                    boolean z2 = this.selectedVariantIndex_ != 0;
                    int i = this.selectedVariantIndex_;
                    if (experimentalCampaignRollout.selectedVariantIndex_ != 0) {
                        z = true;
                    }
                    this.selectedVariantIndex_ = visitor.visitInt(z2, i, z, experimentalCampaignRollout.selectedVariantIndex_);
                    this.priority_ = (CommonTypesProto.Priority) visitor.visitMessage(this.priority_, experimentalCampaignRollout.priority_);
                    this.startTime_ = (CommonTypesProto.CampaignTime) visitor.visitMessage(this.startTime_, experimentalCampaignRollout.startTime_);
                    this.endTime_ = (CommonTypesProto.CampaignTime) visitor.visitMessage(this.endTime_, experimentalCampaignRollout.endTime_);
                    GeneratedMessageLite.MergeFromVisitor mergeFromVisitor = GeneratedMessageLite.MergeFromVisitor.INSTANCE;
                    return this;
                case 6:
                    CodedInputStream codedInputStream = (CodedInputStream) obj;
                    ExtensionRegistryLite extensionRegistryLite = (ExtensionRegistryLite) obj2;
                    while (!z) {
                        try {
                            int readTag = codedInputStream.readTag();
                            if (readTag != 0) {
                                if (readTag == 10) {
                                    this.experimentId_ = codedInputStream.readStringRequireUtf8();
                                } else if (readTag == 16) {
                                    this.selectedVariantIndex_ = codedInputStream.readInt32();
                                } else if (readTag == 26) {
                                    CommonTypesProto.Priority.Builder builder = this.priority_ != null ? (CommonTypesProto.Priority.Builder) this.priority_.toBuilder() : null;
                                    CommonTypesProto.Priority priority = (CommonTypesProto.Priority) codedInputStream.readMessage(CommonTypesProto.Priority.parser(), extensionRegistryLite);
                                    this.priority_ = priority;
                                    if (builder != null) {
                                        builder.mergeFrom(priority);
                                        this.priority_ = (CommonTypesProto.Priority) builder.buildPartial();
                                    }
                                } else if (readTag == 34) {
                                    CommonTypesProto.CampaignTime.Builder builder2 = this.startTime_ != null ? (CommonTypesProto.CampaignTime.Builder) this.startTime_.toBuilder() : null;
                                    CommonTypesProto.CampaignTime campaignTime = (CommonTypesProto.CampaignTime) codedInputStream.readMessage(CommonTypesProto.CampaignTime.parser(), extensionRegistryLite);
                                    this.startTime_ = campaignTime;
                                    if (builder2 != null) {
                                        builder2.mergeFrom(campaignTime);
                                        this.startTime_ = (CommonTypesProto.CampaignTime) builder2.buildPartial();
                                    }
                                } else if (readTag == 42) {
                                    CommonTypesProto.CampaignTime.Builder builder3 = this.endTime_ != null ? (CommonTypesProto.CampaignTime.Builder) this.endTime_.toBuilder() : null;
                                    CommonTypesProto.CampaignTime campaignTime2 = (CommonTypesProto.CampaignTime) codedInputStream.readMessage(CommonTypesProto.CampaignTime.parser(), extensionRegistryLite);
                                    this.endTime_ = campaignTime2;
                                    if (builder3 != null) {
                                        builder3.mergeFrom(campaignTime2);
                                        this.endTime_ = (CommonTypesProto.CampaignTime) builder3.buildPartial();
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
                        synchronized (ExperimentalCampaignRollout.class) {
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
            ExperimentalCampaignRollout experimentalCampaignRollout = new ExperimentalCampaignRollout();
            DEFAULT_INSTANCE = experimentalCampaignRollout;
            experimentalCampaignRollout.makeImmutable();
        }

        public static ExperimentalCampaignRollout getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<ExperimentalCampaignRollout> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    /* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
    public static final class ThickContent extends GeneratedMessageLite<ThickContent, Builder> implements ThickContentOrBuilder {
        public static final int CONTENT_FIELD_NUMBER = 3;
        public static final int DATA_BUNDLE_FIELD_NUMBER = 8;
        /* access modifiers changed from: private */
        public static final ThickContent DEFAULT_INSTANCE;
        public static final int EXPERIMENTAL_PAYLOAD_FIELD_NUMBER = 2;
        public static final int IS_TEST_CAMPAIGN_FIELD_NUMBER = 7;
        private static volatile Parser<ThickContent> PARSER = null;
        public static final int PRIORITY_FIELD_NUMBER = 4;
        public static final int TRIGGERING_CONDITIONS_FIELD_NUMBER = 5;
        public static final int VANILLA_PAYLOAD_FIELD_NUMBER = 1;
        private int bitField0_;
        private MessagesProto.Content content_;
        private MapFieldLite<String, String> dataBundle_ = MapFieldLite.emptyMapField();
        private boolean isTestCampaign_;
        private int payloadCase_ = 0;
        private Object payload_;
        private CommonTypesProto.Priority priority_;
        private Internal.ProtobufList<CommonTypesProto.TriggeringCondition> triggeringConditions_ = emptyProtobufList();

        private ThickContent() {
        }

        /* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
        public enum PayloadCase implements Internal.EnumLite {
            VANILLA_PAYLOAD(1),
            EXPERIMENTAL_PAYLOAD(2),
            PAYLOAD_NOT_SET(0);
            
            private final int value;

            private PayloadCase(int i) {
                this.value = i;
            }

            @Deprecated
            public static PayloadCase valueOf(int i) {
                return forNumber(i);
            }

            public static PayloadCase forNumber(int i) {
                if (i == 0) {
                    return PAYLOAD_NOT_SET;
                }
                if (i == 1) {
                    return VANILLA_PAYLOAD;
                }
                if (i != 2) {
                    return null;
                }
                return EXPERIMENTAL_PAYLOAD;
            }

            public int getNumber() {
                return this.value;
            }
        }

        public PayloadCase getPayloadCase() {
            return PayloadCase.forNumber(this.payloadCase_);
        }

        /* access modifiers changed from: private */
        public void clearPayload() {
            this.payloadCase_ = 0;
            this.payload_ = null;
        }

        public VanillaCampaignPayload getVanillaPayload() {
            if (this.payloadCase_ == 1) {
                return (VanillaCampaignPayload) this.payload_;
            }
            return VanillaCampaignPayload.getDefaultInstance();
        }

        /* access modifiers changed from: private */
        public void setVanillaPayload(VanillaCampaignPayload vanillaCampaignPayload) {
            if (vanillaCampaignPayload != null) {
                this.payload_ = vanillaCampaignPayload;
                this.payloadCase_ = 1;
                return;
            }
            throw null;
        }

        /* access modifiers changed from: private */
        public void setVanillaPayload(VanillaCampaignPayload.Builder builder) {
            this.payload_ = builder.build();
            this.payloadCase_ = 1;
        }

        /* access modifiers changed from: private */
        public void mergeVanillaPayload(VanillaCampaignPayload vanillaCampaignPayload) {
            if (this.payloadCase_ != 1 || this.payload_ == VanillaCampaignPayload.getDefaultInstance()) {
                this.payload_ = vanillaCampaignPayload;
            } else {
                this.payload_ = ((VanillaCampaignPayload.Builder) VanillaCampaignPayload.newBuilder((VanillaCampaignPayload) this.payload_).mergeFrom(vanillaCampaignPayload)).buildPartial();
            }
            this.payloadCase_ = 1;
        }

        /* access modifiers changed from: private */
        public void clearVanillaPayload() {
            if (this.payloadCase_ == 1) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        public ExperimentalCampaignPayload getExperimentalPayload() {
            if (this.payloadCase_ == 2) {
                return (ExperimentalCampaignPayload) this.payload_;
            }
            return ExperimentalCampaignPayload.getDefaultInstance();
        }

        /* access modifiers changed from: private */
        public void setExperimentalPayload(ExperimentalCampaignPayload experimentalCampaignPayload) {
            if (experimentalCampaignPayload != null) {
                this.payload_ = experimentalCampaignPayload;
                this.payloadCase_ = 2;
                return;
            }
            throw null;
        }

        /* access modifiers changed from: private */
        public void setExperimentalPayload(ExperimentalCampaignPayload.Builder builder) {
            this.payload_ = builder.build();
            this.payloadCase_ = 2;
        }

        /* access modifiers changed from: private */
        public void mergeExperimentalPayload(ExperimentalCampaignPayload experimentalCampaignPayload) {
            if (this.payloadCase_ != 2 || this.payload_ == ExperimentalCampaignPayload.getDefaultInstance()) {
                this.payload_ = experimentalCampaignPayload;
            } else {
                this.payload_ = ((ExperimentalCampaignPayload.Builder) ExperimentalCampaignPayload.newBuilder((ExperimentalCampaignPayload) this.payload_).mergeFrom(experimentalCampaignPayload)).buildPartial();
            }
            this.payloadCase_ = 2;
        }

        /* access modifiers changed from: private */
        public void clearExperimentalPayload() {
            if (this.payloadCase_ == 2) {
                this.payloadCase_ = 0;
                this.payload_ = null;
            }
        }

        public boolean hasContent() {
            return this.content_ != null;
        }

        public MessagesProto.Content getContent() {
            MessagesProto.Content content = this.content_;
            return content == null ? MessagesProto.Content.getDefaultInstance() : content;
        }

        /* access modifiers changed from: private */
        public void setContent(MessagesProto.Content content) {
            if (content != null) {
                this.content_ = content;
                return;
            }
            throw null;
        }

        /* access modifiers changed from: private */
        public void setContent(MessagesProto.Content.Builder builder) {
            this.content_ = (MessagesProto.Content) builder.build();
        }

        /* access modifiers changed from: private */
        public void mergeContent(MessagesProto.Content content) {
            MessagesProto.Content content2 = this.content_;
            if (content2 == null || content2 == MessagesProto.Content.getDefaultInstance()) {
                this.content_ = content;
            } else {
                this.content_ = (MessagesProto.Content) ((MessagesProto.Content.Builder) MessagesProto.Content.newBuilder(this.content_).mergeFrom(content)).buildPartial();
            }
        }

        /* access modifiers changed from: private */
        public void clearContent() {
            this.content_ = null;
        }

        public boolean hasPriority() {
            return this.priority_ != null;
        }

        public CommonTypesProto.Priority getPriority() {
            CommonTypesProto.Priority priority = this.priority_;
            return priority == null ? CommonTypesProto.Priority.getDefaultInstance() : priority;
        }

        /* access modifiers changed from: private */
        public void setPriority(CommonTypesProto.Priority priority) {
            if (priority != null) {
                this.priority_ = priority;
                return;
            }
            throw null;
        }

        /* access modifiers changed from: private */
        public void setPriority(CommonTypesProto.Priority.Builder builder) {
            this.priority_ = (CommonTypesProto.Priority) builder.build();
        }

        /* access modifiers changed from: private */
        public void mergePriority(CommonTypesProto.Priority priority) {
            CommonTypesProto.Priority priority2 = this.priority_;
            if (priority2 == null || priority2 == CommonTypesProto.Priority.getDefaultInstance()) {
                this.priority_ = priority;
            } else {
                this.priority_ = (CommonTypesProto.Priority) ((CommonTypesProto.Priority.Builder) CommonTypesProto.Priority.newBuilder(this.priority_).mergeFrom(priority)).buildPartial();
            }
        }

        /* access modifiers changed from: private */
        public void clearPriority() {
            this.priority_ = null;
        }

        public List<CommonTypesProto.TriggeringCondition> getTriggeringConditionsList() {
            return this.triggeringConditions_;
        }

        public List<? extends CommonTypesProto.TriggeringConditionOrBuilder> getTriggeringConditionsOrBuilderList() {
            return this.triggeringConditions_;
        }

        public int getTriggeringConditionsCount() {
            return this.triggeringConditions_.size();
        }

        public CommonTypesProto.TriggeringCondition getTriggeringConditions(int i) {
            return (CommonTypesProto.TriggeringCondition) this.triggeringConditions_.get(i);
        }

        public CommonTypesProto.TriggeringConditionOrBuilder getTriggeringConditionsOrBuilder(int i) {
            return (CommonTypesProto.TriggeringConditionOrBuilder) this.triggeringConditions_.get(i);
        }

        private void ensureTriggeringConditionsIsMutable() {
            if (!this.triggeringConditions_.isModifiable()) {
                this.triggeringConditions_ = GeneratedMessageLite.mutableCopy(this.triggeringConditions_);
            }
        }

        /* access modifiers changed from: private */
        public void setTriggeringConditions(int i, CommonTypesProto.TriggeringCondition triggeringCondition) {
            if (triggeringCondition != null) {
                ensureTriggeringConditionsIsMutable();
                this.triggeringConditions_.set(i, triggeringCondition);
                return;
            }
            throw null;
        }

        /* access modifiers changed from: private */
        public void setTriggeringConditions(int i, CommonTypesProto.TriggeringCondition.Builder builder) {
            ensureTriggeringConditionsIsMutable();
            this.triggeringConditions_.set(i, (CommonTypesProto.TriggeringCondition) builder.build());
        }

        /* access modifiers changed from: private */
        public void addTriggeringConditions(CommonTypesProto.TriggeringCondition triggeringCondition) {
            if (triggeringCondition != null) {
                ensureTriggeringConditionsIsMutable();
                this.triggeringConditions_.add(triggeringCondition);
                return;
            }
            throw null;
        }

        /* access modifiers changed from: private */
        public void addTriggeringConditions(int i, CommonTypesProto.TriggeringCondition triggeringCondition) {
            if (triggeringCondition != null) {
                ensureTriggeringConditionsIsMutable();
                this.triggeringConditions_.add(i, triggeringCondition);
                return;
            }
            throw null;
        }

        /* access modifiers changed from: private */
        public void addTriggeringConditions(CommonTypesProto.TriggeringCondition.Builder builder) {
            ensureTriggeringConditionsIsMutable();
            this.triggeringConditions_.add((CommonTypesProto.TriggeringCondition) builder.build());
        }

        /* access modifiers changed from: private */
        public void addTriggeringConditions(int i, CommonTypesProto.TriggeringCondition.Builder builder) {
            ensureTriggeringConditionsIsMutable();
            this.triggeringConditions_.add(i, (CommonTypesProto.TriggeringCondition) builder.build());
        }

        /* access modifiers changed from: private */
        public void addAllTriggeringConditions(Iterable<? extends CommonTypesProto.TriggeringCondition> iterable) {
            ensureTriggeringConditionsIsMutable();
            AbstractMessageLite.addAll(iterable, this.triggeringConditions_);
        }

        /* access modifiers changed from: private */
        public void clearTriggeringConditions() {
            this.triggeringConditions_ = emptyProtobufList();
        }

        /* access modifiers changed from: private */
        public void removeTriggeringConditions(int i) {
            ensureTriggeringConditionsIsMutable();
            this.triggeringConditions_.remove(i);
        }

        public boolean getIsTestCampaign() {
            return this.isTestCampaign_;
        }

        /* access modifiers changed from: private */
        public void setIsTestCampaign(boolean z) {
            this.isTestCampaign_ = z;
        }

        /* access modifiers changed from: private */
        public void clearIsTestCampaign() {
            this.isTestCampaign_ = false;
        }

        /* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
        private static final class DataBundleDefaultEntryHolder {
            static final MapEntryLite<String, String> defaultEntry = MapEntryLite.newDefaultInstance(WireFormat.FieldType.STRING, "", WireFormat.FieldType.STRING, "");

            private DataBundleDefaultEntryHolder() {
            }
        }

        private MapFieldLite<String, String> internalGetDataBundle() {
            return this.dataBundle_;
        }

        private MapFieldLite<String, String> internalGetMutableDataBundle() {
            if (!this.dataBundle_.isMutable()) {
                this.dataBundle_ = this.dataBundle_.mutableCopy();
            }
            return this.dataBundle_;
        }

        public int getDataBundleCount() {
            return internalGetDataBundle().size();
        }

        public boolean containsDataBundle(String str) {
            if (str != null) {
                return internalGetDataBundle().containsKey(str);
            }
            throw null;
        }

        @Deprecated
        public Map<String, String> getDataBundle() {
            return getDataBundleMap();
        }

        public Map<String, String> getDataBundleMap() {
            return Collections.unmodifiableMap(internalGetDataBundle());
        }

        public String getDataBundleOrDefault(String str, String str2) {
            if (str != null) {
                MapFieldLite<String, String> internalGetDataBundle = internalGetDataBundle();
                return internalGetDataBundle.containsKey(str) ? internalGetDataBundle.get(str) : str2;
            }
            throw null;
        }

        public String getDataBundleOrThrow(String str) {
            if (str != null) {
                MapFieldLite<String, String> internalGetDataBundle = internalGetDataBundle();
                if (internalGetDataBundle.containsKey(str)) {
                    return internalGetDataBundle.get(str);
                }
                throw new IllegalArgumentException();
            }
            throw null;
        }

        /* access modifiers changed from: private */
        public Map<String, String> getMutableDataBundleMap() {
            return internalGetMutableDataBundle();
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (this.payloadCase_ == 1) {
                codedOutputStream.writeMessage(1, (VanillaCampaignPayload) this.payload_);
            }
            if (this.payloadCase_ == 2) {
                codedOutputStream.writeMessage(2, (ExperimentalCampaignPayload) this.payload_);
            }
            if (this.content_ != null) {
                codedOutputStream.writeMessage(3, getContent());
            }
            if (this.priority_ != null) {
                codedOutputStream.writeMessage(4, getPriority());
            }
            for (int i = 0; i < this.triggeringConditions_.size(); i++) {
                codedOutputStream.writeMessage(5, (MessageLite) this.triggeringConditions_.get(i));
            }
            boolean z = this.isTestCampaign_;
            if (z) {
                codedOutputStream.writeBool(7, z);
            }
            for (Map.Entry next : internalGetDataBundle().entrySet()) {
                DataBundleDefaultEntryHolder.defaultEntry.serializeTo(codedOutputStream, 8, (String) next.getKey(), (String) next.getValue());
            }
        }

        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int computeMessageSize = this.payloadCase_ == 1 ? CodedOutputStream.computeMessageSize(1, (VanillaCampaignPayload) this.payload_) + 0 : 0;
            if (this.payloadCase_ == 2) {
                computeMessageSize += CodedOutputStream.computeMessageSize(2, (ExperimentalCampaignPayload) this.payload_);
            }
            if (this.content_ != null) {
                computeMessageSize += CodedOutputStream.computeMessageSize(3, getContent());
            }
            if (this.priority_ != null) {
                computeMessageSize += CodedOutputStream.computeMessageSize(4, getPriority());
            }
            for (int i2 = 0; i2 < this.triggeringConditions_.size(); i2++) {
                computeMessageSize += CodedOutputStream.computeMessageSize(5, (MessageLite) this.triggeringConditions_.get(i2));
            }
            boolean z = this.isTestCampaign_;
            if (z) {
                computeMessageSize += CodedOutputStream.computeBoolSize(7, z);
            }
            for (Map.Entry next : internalGetDataBundle().entrySet()) {
                computeMessageSize += DataBundleDefaultEntryHolder.defaultEntry.computeMessageSize(8, (String) next.getKey(), (String) next.getValue());
            }
            this.memoizedSerializedSize = computeMessageSize;
            return computeMessageSize;
        }

        public static ThickContent parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (ThickContent) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static ThickContent parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (ThickContent) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static ThickContent parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (ThickContent) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static ThickContent parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (ThickContent) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static ThickContent parseFrom(InputStream inputStream) throws IOException {
            return (ThickContent) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static ThickContent parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ThickContent) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static ThickContent parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (ThickContent) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static ThickContent parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ThickContent) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static ThickContent parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (ThickContent) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static ThickContent parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ThickContent) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(ThickContent thickContent) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(thickContent);
        }

        /* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
        public static final class Builder extends GeneratedMessageLite.Builder<ThickContent, Builder> implements ThickContentOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 r1) {
                this();
            }

            private Builder() {
                super(ThickContent.DEFAULT_INSTANCE);
            }

            public PayloadCase getPayloadCase() {
                return ((ThickContent) this.instance).getPayloadCase();
            }

            public Builder clearPayload() {
                copyOnWrite();
                ((ThickContent) this.instance).clearPayload();
                return this;
            }

            public VanillaCampaignPayload getVanillaPayload() {
                return ((ThickContent) this.instance).getVanillaPayload();
            }

            public Builder setVanillaPayload(VanillaCampaignPayload vanillaCampaignPayload) {
                copyOnWrite();
                ((ThickContent) this.instance).setVanillaPayload(vanillaCampaignPayload);
                return this;
            }

            public Builder setVanillaPayload(VanillaCampaignPayload.Builder builder) {
                copyOnWrite();
                ((ThickContent) this.instance).setVanillaPayload(builder);
                return this;
            }

            public Builder mergeVanillaPayload(VanillaCampaignPayload vanillaCampaignPayload) {
                copyOnWrite();
                ((ThickContent) this.instance).mergeVanillaPayload(vanillaCampaignPayload);
                return this;
            }

            public Builder clearVanillaPayload() {
                copyOnWrite();
                ((ThickContent) this.instance).clearVanillaPayload();
                return this;
            }

            public ExperimentalCampaignPayload getExperimentalPayload() {
                return ((ThickContent) this.instance).getExperimentalPayload();
            }

            public Builder setExperimentalPayload(ExperimentalCampaignPayload experimentalCampaignPayload) {
                copyOnWrite();
                ((ThickContent) this.instance).setExperimentalPayload(experimentalCampaignPayload);
                return this;
            }

            public Builder setExperimentalPayload(ExperimentalCampaignPayload.Builder builder) {
                copyOnWrite();
                ((ThickContent) this.instance).setExperimentalPayload(builder);
                return this;
            }

            public Builder mergeExperimentalPayload(ExperimentalCampaignPayload experimentalCampaignPayload) {
                copyOnWrite();
                ((ThickContent) this.instance).mergeExperimentalPayload(experimentalCampaignPayload);
                return this;
            }

            public Builder clearExperimentalPayload() {
                copyOnWrite();
                ((ThickContent) this.instance).clearExperimentalPayload();
                return this;
            }

            public boolean hasContent() {
                return ((ThickContent) this.instance).hasContent();
            }

            public MessagesProto.Content getContent() {
                return ((ThickContent) this.instance).getContent();
            }

            public Builder setContent(MessagesProto.Content content) {
                copyOnWrite();
                ((ThickContent) this.instance).setContent(content);
                return this;
            }

            public Builder setContent(MessagesProto.Content.Builder builder) {
                copyOnWrite();
                ((ThickContent) this.instance).setContent(builder);
                return this;
            }

            public Builder mergeContent(MessagesProto.Content content) {
                copyOnWrite();
                ((ThickContent) this.instance).mergeContent(content);
                return this;
            }

            public Builder clearContent() {
                copyOnWrite();
                ((ThickContent) this.instance).clearContent();
                return this;
            }

            public boolean hasPriority() {
                return ((ThickContent) this.instance).hasPriority();
            }

            public CommonTypesProto.Priority getPriority() {
                return ((ThickContent) this.instance).getPriority();
            }

            public Builder setPriority(CommonTypesProto.Priority priority) {
                copyOnWrite();
                ((ThickContent) this.instance).setPriority(priority);
                return this;
            }

            public Builder setPriority(CommonTypesProto.Priority.Builder builder) {
                copyOnWrite();
                ((ThickContent) this.instance).setPriority(builder);
                return this;
            }

            public Builder mergePriority(CommonTypesProto.Priority priority) {
                copyOnWrite();
                ((ThickContent) this.instance).mergePriority(priority);
                return this;
            }

            public Builder clearPriority() {
                copyOnWrite();
                ((ThickContent) this.instance).clearPriority();
                return this;
            }

            public List<CommonTypesProto.TriggeringCondition> getTriggeringConditionsList() {
                return Collections.unmodifiableList(((ThickContent) this.instance).getTriggeringConditionsList());
            }

            public int getTriggeringConditionsCount() {
                return ((ThickContent) this.instance).getTriggeringConditionsCount();
            }

            public CommonTypesProto.TriggeringCondition getTriggeringConditions(int i) {
                return ((ThickContent) this.instance).getTriggeringConditions(i);
            }

            public Builder setTriggeringConditions(int i, CommonTypesProto.TriggeringCondition triggeringCondition) {
                copyOnWrite();
                ((ThickContent) this.instance).setTriggeringConditions(i, triggeringCondition);
                return this;
            }

            public Builder setTriggeringConditions(int i, CommonTypesProto.TriggeringCondition.Builder builder) {
                copyOnWrite();
                ((ThickContent) this.instance).setTriggeringConditions(i, builder);
                return this;
            }

            public Builder addTriggeringConditions(CommonTypesProto.TriggeringCondition triggeringCondition) {
                copyOnWrite();
                ((ThickContent) this.instance).addTriggeringConditions(triggeringCondition);
                return this;
            }

            public Builder addTriggeringConditions(int i, CommonTypesProto.TriggeringCondition triggeringCondition) {
                copyOnWrite();
                ((ThickContent) this.instance).addTriggeringConditions(i, triggeringCondition);
                return this;
            }

            public Builder addTriggeringConditions(CommonTypesProto.TriggeringCondition.Builder builder) {
                copyOnWrite();
                ((ThickContent) this.instance).addTriggeringConditions(builder);
                return this;
            }

            public Builder addTriggeringConditions(int i, CommonTypesProto.TriggeringCondition.Builder builder) {
                copyOnWrite();
                ((ThickContent) this.instance).addTriggeringConditions(i, builder);
                return this;
            }

            public Builder addAllTriggeringConditions(Iterable<? extends CommonTypesProto.TriggeringCondition> iterable) {
                copyOnWrite();
                ((ThickContent) this.instance).addAllTriggeringConditions(iterable);
                return this;
            }

            public Builder clearTriggeringConditions() {
                copyOnWrite();
                ((ThickContent) this.instance).clearTriggeringConditions();
                return this;
            }

            public Builder removeTriggeringConditions(int i) {
                copyOnWrite();
                ((ThickContent) this.instance).removeTriggeringConditions(i);
                return this;
            }

            public boolean getIsTestCampaign() {
                return ((ThickContent) this.instance).getIsTestCampaign();
            }

            public Builder setIsTestCampaign(boolean z) {
                copyOnWrite();
                ((ThickContent) this.instance).setIsTestCampaign(z);
                return this;
            }

            public Builder clearIsTestCampaign() {
                copyOnWrite();
                ((ThickContent) this.instance).clearIsTestCampaign();
                return this;
            }

            public int getDataBundleCount() {
                return ((ThickContent) this.instance).getDataBundleMap().size();
            }

            public boolean containsDataBundle(String str) {
                if (str != null) {
                    return ((ThickContent) this.instance).getDataBundleMap().containsKey(str);
                }
                throw null;
            }

            public Builder clearDataBundle() {
                copyOnWrite();
                ((ThickContent) this.instance).getMutableDataBundleMap().clear();
                return this;
            }

            public Builder removeDataBundle(String str) {
                if (str != null) {
                    copyOnWrite();
                    ((ThickContent) this.instance).getMutableDataBundleMap().remove(str);
                    return this;
                }
                throw null;
            }

            @Deprecated
            public Map<String, String> getDataBundle() {
                return getDataBundleMap();
            }

            public Map<String, String> getDataBundleMap() {
                return Collections.unmodifiableMap(((ThickContent) this.instance).getDataBundleMap());
            }

            public String getDataBundleOrDefault(String str, String str2) {
                if (str != null) {
                    Map<String, String> dataBundleMap = ((ThickContent) this.instance).getDataBundleMap();
                    return dataBundleMap.containsKey(str) ? dataBundleMap.get(str) : str2;
                }
                throw null;
            }

            public String getDataBundleOrThrow(String str) {
                if (str != null) {
                    Map<String, String> dataBundleMap = ((ThickContent) this.instance).getDataBundleMap();
                    if (dataBundleMap.containsKey(str)) {
                        return dataBundleMap.get(str);
                    }
                    throw new IllegalArgumentException();
                }
                throw null;
            }

            public Builder putDataBundle(String str, String str2) {
                if (str == null) {
                    throw null;
                } else if (str2 != null) {
                    copyOnWrite();
                    ((ThickContent) this.instance).getMutableDataBundleMap().put(str, str2);
                    return this;
                } else {
                    throw null;
                }
            }

            public Builder putAllDataBundle(Map<String, String> map) {
                copyOnWrite();
                ((ThickContent) this.instance).getMutableDataBundleMap().putAll(map);
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            boolean z = false;
            switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
                case 1:
                    return new ThickContent();
                case 2:
                    return DEFAULT_INSTANCE;
                case 3:
                    this.triggeringConditions_.makeImmutable();
                    this.dataBundle_.makeImmutable();
                    return null;
                case 4:
                    return new Builder((AnonymousClass1) null);
                case 5:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                    ThickContent thickContent = (ThickContent) obj2;
                    this.content_ = (MessagesProto.Content) visitor.visitMessage(this.content_, thickContent.content_);
                    this.priority_ = (CommonTypesProto.Priority) visitor.visitMessage(this.priority_, thickContent.priority_);
                    this.triggeringConditions_ = visitor.visitList(this.triggeringConditions_, thickContent.triggeringConditions_);
                    boolean z2 = this.isTestCampaign_;
                    boolean z3 = thickContent.isTestCampaign_;
                    this.isTestCampaign_ = visitor.visitBoolean(z2, z2, z3, z3);
                    this.dataBundle_ = visitor.visitMap(this.dataBundle_, thickContent.internalGetDataBundle());
                    int i = AnonymousClass1.$SwitchMap$com$google$internal$firebase$inappmessaging$v1$CampaignProto$ThickContent$PayloadCase[thickContent.getPayloadCase().ordinal()];
                    if (i == 1) {
                        if (this.payloadCase_ == 1) {
                            z = true;
                        }
                        this.payload_ = visitor.visitOneofMessage(z, this.payload_, thickContent.payload_);
                    } else if (i == 2) {
                        if (this.payloadCase_ == 2) {
                            z = true;
                        }
                        this.payload_ = visitor.visitOneofMessage(z, this.payload_, thickContent.payload_);
                    } else if (i == 3) {
                        if (this.payloadCase_ != 0) {
                            z = true;
                        }
                        visitor.visitOneofNotSet(z);
                    }
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                        int i2 = thickContent.payloadCase_;
                        if (i2 != 0) {
                            this.payloadCase_ = i2;
                        }
                        this.bitField0_ |= thickContent.bitField0_;
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
                                    VanillaCampaignPayload.Builder builder = this.payloadCase_ == 1 ? (VanillaCampaignPayload.Builder) ((VanillaCampaignPayload) this.payload_).toBuilder() : null;
                                    MessageLite readMessage = codedInputStream.readMessage(VanillaCampaignPayload.parser(), extensionRegistryLite);
                                    this.payload_ = readMessage;
                                    if (builder != null) {
                                        builder.mergeFrom((VanillaCampaignPayload) readMessage);
                                        this.payload_ = builder.buildPartial();
                                    }
                                    this.payloadCase_ = 1;
                                } else if (readTag == 18) {
                                    ExperimentalCampaignPayload.Builder builder2 = this.payloadCase_ == 2 ? (ExperimentalCampaignPayload.Builder) ((ExperimentalCampaignPayload) this.payload_).toBuilder() : null;
                                    MessageLite readMessage2 = codedInputStream.readMessage(ExperimentalCampaignPayload.parser(), extensionRegistryLite);
                                    this.payload_ = readMessage2;
                                    if (builder2 != null) {
                                        builder2.mergeFrom((ExperimentalCampaignPayload) readMessage2);
                                        this.payload_ = builder2.buildPartial();
                                    }
                                    this.payloadCase_ = 2;
                                } else if (readTag == 26) {
                                    MessagesProto.Content.Builder builder3 = this.content_ != null ? (MessagesProto.Content.Builder) this.content_.toBuilder() : null;
                                    MessagesProto.Content content = (MessagesProto.Content) codedInputStream.readMessage(MessagesProto.Content.parser(), extensionRegistryLite);
                                    this.content_ = content;
                                    if (builder3 != null) {
                                        builder3.mergeFrom(content);
                                        this.content_ = (MessagesProto.Content) builder3.buildPartial();
                                    }
                                } else if (readTag == 34) {
                                    CommonTypesProto.Priority.Builder builder4 = this.priority_ != null ? (CommonTypesProto.Priority.Builder) this.priority_.toBuilder() : null;
                                    CommonTypesProto.Priority priority = (CommonTypesProto.Priority) codedInputStream.readMessage(CommonTypesProto.Priority.parser(), extensionRegistryLite);
                                    this.priority_ = priority;
                                    if (builder4 != null) {
                                        builder4.mergeFrom(priority);
                                        this.priority_ = (CommonTypesProto.Priority) builder4.buildPartial();
                                    }
                                } else if (readTag == 42) {
                                    if (!this.triggeringConditions_.isModifiable()) {
                                        this.triggeringConditions_ = GeneratedMessageLite.mutableCopy(this.triggeringConditions_);
                                    }
                                    this.triggeringConditions_.add((CommonTypesProto.TriggeringCondition) codedInputStream.readMessage(CommonTypesProto.TriggeringCondition.parser(), extensionRegistryLite));
                                } else if (readTag == 56) {
                                    this.isTestCampaign_ = codedInputStream.readBool();
                                } else if (readTag == 66) {
                                    if (!this.dataBundle_.isMutable()) {
                                        this.dataBundle_ = this.dataBundle_.mutableCopy();
                                    }
                                    DataBundleDefaultEntryHolder.defaultEntry.parseInto(this.dataBundle_, codedInputStream, extensionRegistryLite);
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
                        synchronized (ThickContent.class) {
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
            ThickContent thickContent = new ThickContent();
            DEFAULT_INSTANCE = thickContent;
            thickContent.makeImmutable();
        }

        public static ThickContent getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<ThickContent> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    /* renamed from: com.google.internal.firebase.inappmessaging.v1.CampaignProto$1  reason: invalid class name */
    /* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$internal$firebase$inappmessaging$v1$CampaignProto$ThickContent$PayloadCase;
        static final /* synthetic */ int[] $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke;

        /* JADX WARNING: Can't wrap try/catch for region: R(23:0|(2:1|2)|3|(2:5|6)|7|9|10|11|13|14|15|16|17|18|19|20|21|22|23|24|25|26|(3:27|28|30)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(26:0|1|2|3|(2:5|6)|7|9|10|11|13|14|15|16|17|18|19|20|21|22|23|24|25|26|27|28|30) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0039 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0043 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x004d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x0058 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x0063 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:25:0x006e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x0079 */
        static {
            /*
                com.google.internal.firebase.inappmessaging.v1.CampaignProto$ThickContent$PayloadCase[] r0 = com.google.internal.firebase.inappmessaging.v1.CampaignProto.ThickContent.PayloadCase.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$google$internal$firebase$inappmessaging$v1$CampaignProto$ThickContent$PayloadCase = r0
                r1 = 1
                com.google.internal.firebase.inappmessaging.v1.CampaignProto$ThickContent$PayloadCase r2 = com.google.internal.firebase.inappmessaging.v1.CampaignProto.ThickContent.PayloadCase.VANILLA_PAYLOAD     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0[r2] = r1     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                r0 = 2
                int[] r2 = $SwitchMap$com$google$internal$firebase$inappmessaging$v1$CampaignProto$ThickContent$PayloadCase     // Catch:{ NoSuchFieldError -> 0x001d }
                com.google.internal.firebase.inappmessaging.v1.CampaignProto$ThickContent$PayloadCase r3 = com.google.internal.firebase.inappmessaging.v1.CampaignProto.ThickContent.PayloadCase.EXPERIMENTAL_PAYLOAD     // Catch:{ NoSuchFieldError -> 0x001d }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2[r3] = r0     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                r2 = 3
                int[] r3 = $SwitchMap$com$google$internal$firebase$inappmessaging$v1$CampaignProto$ThickContent$PayloadCase     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.google.internal.firebase.inappmessaging.v1.CampaignProto$ThickContent$PayloadCase r4 = com.google.internal.firebase.inappmessaging.v1.CampaignProto.ThickContent.PayloadCase.PAYLOAD_NOT_SET     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r3[r4] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke[] r3 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.values()
                int r3 = r3.length
                int[] r3 = new int[r3]
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke = r3
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r4 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE     // Catch:{ NoSuchFieldError -> 0x0039 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0039 }
                r3[r4] = r1     // Catch:{ NoSuchFieldError -> 0x0039 }
            L_0x0039:
                int[] r1 = $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke     // Catch:{ NoSuchFieldError -> 0x0043 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r3 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.IS_INITIALIZED     // Catch:{ NoSuchFieldError -> 0x0043 }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x0043 }
                r1[r3] = r0     // Catch:{ NoSuchFieldError -> 0x0043 }
            L_0x0043:
                int[] r0 = $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke     // Catch:{ NoSuchFieldError -> 0x004d }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.MAKE_IMMUTABLE     // Catch:{ NoSuchFieldError -> 0x004d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x004d }
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x004d }
            L_0x004d:
                int[] r0 = $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke     // Catch:{ NoSuchFieldError -> 0x0058 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.NEW_BUILDER     // Catch:{ NoSuchFieldError -> 0x0058 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0058 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0058 }
            L_0x0058:
                int[] r0 = $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke     // Catch:{ NoSuchFieldError -> 0x0063 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.VISIT     // Catch:{ NoSuchFieldError -> 0x0063 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0063 }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0063 }
            L_0x0063:
                int[] r0 = $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke     // Catch:{ NoSuchFieldError -> 0x006e }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.MERGE_FROM_STREAM     // Catch:{ NoSuchFieldError -> 0x006e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x006e }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x006e }
            L_0x006e:
                int[] r0 = $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke     // Catch:{ NoSuchFieldError -> 0x0079 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE     // Catch:{ NoSuchFieldError -> 0x0079 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0079 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0079 }
            L_0x0079:
                int[] r0 = $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke     // Catch:{ NoSuchFieldError -> 0x0085 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r1 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.GET_PARSER     // Catch:{ NoSuchFieldError -> 0x0085 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0085 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0085 }
            L_0x0085:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.internal.firebase.inappmessaging.v1.CampaignProto.AnonymousClass1.<clinit>():void");
        }
    }

    /* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
    public static final class VanillaCampaignPayload extends GeneratedMessageLite<VanillaCampaignPayload, Builder> implements VanillaCampaignPayloadOrBuilder {
        public static final int CAMPAIGN_END_TIME_MILLIS_FIELD_NUMBER = 4;
        public static final int CAMPAIGN_ID_FIELD_NUMBER = 1;
        public static final int CAMPAIGN_NAME_FIELD_NUMBER = 5;
        public static final int CAMPAIGN_START_TIME_MILLIS_FIELD_NUMBER = 3;
        /* access modifiers changed from: private */
        public static final VanillaCampaignPayload DEFAULT_INSTANCE;
        public static final int EXPERIMENTAL_CAMPAIGN_ID_FIELD_NUMBER = 2;
        private static volatile Parser<VanillaCampaignPayload> PARSER;
        private long campaignEndTimeMillis_;
        private String campaignId_ = "";
        private String campaignName_ = "";
        private long campaignStartTimeMillis_;
        private String experimentalCampaignId_ = "";

        private VanillaCampaignPayload() {
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
                this.campaignId_ = str;
                return;
            }
            throw null;
        }

        /* access modifiers changed from: private */
        public void clearCampaignId() {
            this.campaignId_ = getDefaultInstance().getCampaignId();
        }

        /* access modifiers changed from: private */
        public void setCampaignIdBytes(ByteString byteString) {
            if (byteString != null) {
                checkByteStringIsUtf8(byteString);
                this.campaignId_ = byteString.toStringUtf8();
                return;
            }
            throw null;
        }

        public String getExperimentalCampaignId() {
            return this.experimentalCampaignId_;
        }

        public ByteString getExperimentalCampaignIdBytes() {
            return ByteString.copyFromUtf8(this.experimentalCampaignId_);
        }

        /* access modifiers changed from: private */
        public void setExperimentalCampaignId(String str) {
            if (str != null) {
                this.experimentalCampaignId_ = str;
                return;
            }
            throw null;
        }

        /* access modifiers changed from: private */
        public void clearExperimentalCampaignId() {
            this.experimentalCampaignId_ = getDefaultInstance().getExperimentalCampaignId();
        }

        /* access modifiers changed from: private */
        public void setExperimentalCampaignIdBytes(ByteString byteString) {
            if (byteString != null) {
                checkByteStringIsUtf8(byteString);
                this.experimentalCampaignId_ = byteString.toStringUtf8();
                return;
            }
            throw null;
        }

        public long getCampaignStartTimeMillis() {
            return this.campaignStartTimeMillis_;
        }

        /* access modifiers changed from: private */
        public void setCampaignStartTimeMillis(long j) {
            this.campaignStartTimeMillis_ = j;
        }

        /* access modifiers changed from: private */
        public void clearCampaignStartTimeMillis() {
            this.campaignStartTimeMillis_ = 0;
        }

        public long getCampaignEndTimeMillis() {
            return this.campaignEndTimeMillis_;
        }

        /* access modifiers changed from: private */
        public void setCampaignEndTimeMillis(long j) {
            this.campaignEndTimeMillis_ = j;
        }

        /* access modifiers changed from: private */
        public void clearCampaignEndTimeMillis() {
            this.campaignEndTimeMillis_ = 0;
        }

        public String getCampaignName() {
            return this.campaignName_;
        }

        public ByteString getCampaignNameBytes() {
            return ByteString.copyFromUtf8(this.campaignName_);
        }

        /* access modifiers changed from: private */
        public void setCampaignName(String str) {
            if (str != null) {
                this.campaignName_ = str;
                return;
            }
            throw null;
        }

        /* access modifiers changed from: private */
        public void clearCampaignName() {
            this.campaignName_ = getDefaultInstance().getCampaignName();
        }

        /* access modifiers changed from: private */
        public void setCampaignNameBytes(ByteString byteString) {
            if (byteString != null) {
                checkByteStringIsUtf8(byteString);
                this.campaignName_ = byteString.toStringUtf8();
                return;
            }
            throw null;
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (!this.campaignId_.isEmpty()) {
                codedOutputStream.writeString(1, getCampaignId());
            }
            if (!this.experimentalCampaignId_.isEmpty()) {
                codedOutputStream.writeString(2, getExperimentalCampaignId());
            }
            long j = this.campaignStartTimeMillis_;
            if (j != 0) {
                codedOutputStream.writeInt64(3, j);
            }
            long j2 = this.campaignEndTimeMillis_;
            if (j2 != 0) {
                codedOutputStream.writeInt64(4, j2);
            }
            if (!this.campaignName_.isEmpty()) {
                codedOutputStream.writeString(5, getCampaignName());
            }
        }

        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            if (!this.campaignId_.isEmpty()) {
                i2 = 0 + CodedOutputStream.computeStringSize(1, getCampaignId());
            }
            if (!this.experimentalCampaignId_.isEmpty()) {
                i2 += CodedOutputStream.computeStringSize(2, getExperimentalCampaignId());
            }
            long j = this.campaignStartTimeMillis_;
            if (j != 0) {
                i2 += CodedOutputStream.computeInt64Size(3, j);
            }
            long j2 = this.campaignEndTimeMillis_;
            if (j2 != 0) {
                i2 += CodedOutputStream.computeInt64Size(4, j2);
            }
            if (!this.campaignName_.isEmpty()) {
                i2 += CodedOutputStream.computeStringSize(5, getCampaignName());
            }
            this.memoizedSerializedSize = i2;
            return i2;
        }

        public static VanillaCampaignPayload parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (VanillaCampaignPayload) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static VanillaCampaignPayload parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (VanillaCampaignPayload) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static VanillaCampaignPayload parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (VanillaCampaignPayload) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static VanillaCampaignPayload parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (VanillaCampaignPayload) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static VanillaCampaignPayload parseFrom(InputStream inputStream) throws IOException {
            return (VanillaCampaignPayload) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static VanillaCampaignPayload parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (VanillaCampaignPayload) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static VanillaCampaignPayload parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (VanillaCampaignPayload) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static VanillaCampaignPayload parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (VanillaCampaignPayload) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static VanillaCampaignPayload parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (VanillaCampaignPayload) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static VanillaCampaignPayload parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (VanillaCampaignPayload) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(VanillaCampaignPayload vanillaCampaignPayload) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(vanillaCampaignPayload);
        }

        /* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
        public static final class Builder extends GeneratedMessageLite.Builder<VanillaCampaignPayload, Builder> implements VanillaCampaignPayloadOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 r1) {
                this();
            }

            private Builder() {
                super(VanillaCampaignPayload.DEFAULT_INSTANCE);
            }

            public String getCampaignId() {
                return ((VanillaCampaignPayload) this.instance).getCampaignId();
            }

            public ByteString getCampaignIdBytes() {
                return ((VanillaCampaignPayload) this.instance).getCampaignIdBytes();
            }

            public Builder setCampaignId(String str) {
                copyOnWrite();
                ((VanillaCampaignPayload) this.instance).setCampaignId(str);
                return this;
            }

            public Builder clearCampaignId() {
                copyOnWrite();
                ((VanillaCampaignPayload) this.instance).clearCampaignId();
                return this;
            }

            public Builder setCampaignIdBytes(ByteString byteString) {
                copyOnWrite();
                ((VanillaCampaignPayload) this.instance).setCampaignIdBytes(byteString);
                return this;
            }

            public String getExperimentalCampaignId() {
                return ((VanillaCampaignPayload) this.instance).getExperimentalCampaignId();
            }

            public ByteString getExperimentalCampaignIdBytes() {
                return ((VanillaCampaignPayload) this.instance).getExperimentalCampaignIdBytes();
            }

            public Builder setExperimentalCampaignId(String str) {
                copyOnWrite();
                ((VanillaCampaignPayload) this.instance).setExperimentalCampaignId(str);
                return this;
            }

            public Builder clearExperimentalCampaignId() {
                copyOnWrite();
                ((VanillaCampaignPayload) this.instance).clearExperimentalCampaignId();
                return this;
            }

            public Builder setExperimentalCampaignIdBytes(ByteString byteString) {
                copyOnWrite();
                ((VanillaCampaignPayload) this.instance).setExperimentalCampaignIdBytes(byteString);
                return this;
            }

            public long getCampaignStartTimeMillis() {
                return ((VanillaCampaignPayload) this.instance).getCampaignStartTimeMillis();
            }

            public Builder setCampaignStartTimeMillis(long j) {
                copyOnWrite();
                ((VanillaCampaignPayload) this.instance).setCampaignStartTimeMillis(j);
                return this;
            }

            public Builder clearCampaignStartTimeMillis() {
                copyOnWrite();
                ((VanillaCampaignPayload) this.instance).clearCampaignStartTimeMillis();
                return this;
            }

            public long getCampaignEndTimeMillis() {
                return ((VanillaCampaignPayload) this.instance).getCampaignEndTimeMillis();
            }

            public Builder setCampaignEndTimeMillis(long j) {
                copyOnWrite();
                ((VanillaCampaignPayload) this.instance).setCampaignEndTimeMillis(j);
                return this;
            }

            public Builder clearCampaignEndTimeMillis() {
                copyOnWrite();
                ((VanillaCampaignPayload) this.instance).clearCampaignEndTimeMillis();
                return this;
            }

            public String getCampaignName() {
                return ((VanillaCampaignPayload) this.instance).getCampaignName();
            }

            public ByteString getCampaignNameBytes() {
                return ((VanillaCampaignPayload) this.instance).getCampaignNameBytes();
            }

            public Builder setCampaignName(String str) {
                copyOnWrite();
                ((VanillaCampaignPayload) this.instance).setCampaignName(str);
                return this;
            }

            public Builder clearCampaignName() {
                copyOnWrite();
                ((VanillaCampaignPayload) this.instance).clearCampaignName();
                return this;
            }

            public Builder setCampaignNameBytes(ByteString byteString) {
                copyOnWrite();
                ((VanillaCampaignPayload) this.instance).setCampaignNameBytes(byteString);
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            boolean z = false;
            switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
                case 1:
                    return new VanillaCampaignPayload();
                case 2:
                    return DEFAULT_INSTANCE;
                case 3:
                    return null;
                case 4:
                    return new Builder((AnonymousClass1) null);
                case 5:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                    VanillaCampaignPayload vanillaCampaignPayload = (VanillaCampaignPayload) obj2;
                    this.campaignId_ = visitor.visitString(!this.campaignId_.isEmpty(), this.campaignId_, !vanillaCampaignPayload.campaignId_.isEmpty(), vanillaCampaignPayload.campaignId_);
                    this.experimentalCampaignId_ = visitor.visitString(!this.experimentalCampaignId_.isEmpty(), this.experimentalCampaignId_, !vanillaCampaignPayload.experimentalCampaignId_.isEmpty(), vanillaCampaignPayload.experimentalCampaignId_);
                    this.campaignStartTimeMillis_ = visitor.visitLong(this.campaignStartTimeMillis_ != 0, this.campaignStartTimeMillis_, vanillaCampaignPayload.campaignStartTimeMillis_ != 0, vanillaCampaignPayload.campaignStartTimeMillis_);
                    this.campaignEndTimeMillis_ = visitor.visitLong(this.campaignEndTimeMillis_ != 0, this.campaignEndTimeMillis_, vanillaCampaignPayload.campaignEndTimeMillis_ != 0, vanillaCampaignPayload.campaignEndTimeMillis_);
                    this.campaignName_ = visitor.visitString(!this.campaignName_.isEmpty(), this.campaignName_, !vanillaCampaignPayload.campaignName_.isEmpty(), vanillaCampaignPayload.campaignName_);
                    GeneratedMessageLite.MergeFromVisitor mergeFromVisitor = GeneratedMessageLite.MergeFromVisitor.INSTANCE;
                    return this;
                case 6:
                    CodedInputStream codedInputStream = (CodedInputStream) obj;
                    ExtensionRegistryLite extensionRegistryLite = (ExtensionRegistryLite) obj2;
                    while (!z) {
                        try {
                            int readTag = codedInputStream.readTag();
                            if (readTag != 0) {
                                if (readTag == 10) {
                                    this.campaignId_ = codedInputStream.readStringRequireUtf8();
                                } else if (readTag == 18) {
                                    this.experimentalCampaignId_ = codedInputStream.readStringRequireUtf8();
                                } else if (readTag == 24) {
                                    this.campaignStartTimeMillis_ = codedInputStream.readInt64();
                                } else if (readTag == 32) {
                                    this.campaignEndTimeMillis_ = codedInputStream.readInt64();
                                } else if (readTag == 42) {
                                    this.campaignName_ = codedInputStream.readStringRequireUtf8();
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
                        synchronized (VanillaCampaignPayload.class) {
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
            VanillaCampaignPayload vanillaCampaignPayload = new VanillaCampaignPayload();
            DEFAULT_INSTANCE = vanillaCampaignPayload;
            vanillaCampaignPayload.makeImmutable();
        }

        public static VanillaCampaignPayload getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<VanillaCampaignPayload> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    /* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
    public static final class ExperimentalCampaignPayload extends GeneratedMessageLite<ExperimentalCampaignPayload, Builder> implements ExperimentalCampaignPayloadOrBuilder {
        public static final int CAMPAIGN_END_TIME_MILLIS_FIELD_NUMBER = 4;
        public static final int CAMPAIGN_ID_FIELD_NUMBER = 1;
        public static final int CAMPAIGN_NAME_FIELD_NUMBER = 5;
        public static final int CAMPAIGN_START_TIME_MILLIS_FIELD_NUMBER = 3;
        /* access modifiers changed from: private */
        public static final ExperimentalCampaignPayload DEFAULT_INSTANCE;
        public static final int EXPERIMENT_PAYLOAD_FIELD_NUMBER = 2;
        private static volatile Parser<ExperimentalCampaignPayload> PARSER;
        private long campaignEndTimeMillis_;
        private String campaignId_ = "";
        private String campaignName_ = "";
        private long campaignStartTimeMillis_;
        private FirebaseAbt.ExperimentPayload experimentPayload_;

        private ExperimentalCampaignPayload() {
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
                this.campaignId_ = str;
                return;
            }
            throw null;
        }

        /* access modifiers changed from: private */
        public void clearCampaignId() {
            this.campaignId_ = getDefaultInstance().getCampaignId();
        }

        /* access modifiers changed from: private */
        public void setCampaignIdBytes(ByteString byteString) {
            if (byteString != null) {
                checkByteStringIsUtf8(byteString);
                this.campaignId_ = byteString.toStringUtf8();
                return;
            }
            throw null;
        }

        public boolean hasExperimentPayload() {
            return this.experimentPayload_ != null;
        }

        public FirebaseAbt.ExperimentPayload getExperimentPayload() {
            FirebaseAbt.ExperimentPayload experimentPayload = this.experimentPayload_;
            return experimentPayload == null ? FirebaseAbt.ExperimentPayload.getDefaultInstance() : experimentPayload;
        }

        /* access modifiers changed from: private */
        public void setExperimentPayload(FirebaseAbt.ExperimentPayload experimentPayload) {
            if (experimentPayload != null) {
                this.experimentPayload_ = experimentPayload;
                return;
            }
            throw null;
        }

        /* access modifiers changed from: private */
        public void setExperimentPayload(FirebaseAbt.ExperimentPayload.Builder builder) {
            this.experimentPayload_ = (FirebaseAbt.ExperimentPayload) builder.build();
        }

        /* access modifiers changed from: private */
        public void mergeExperimentPayload(FirebaseAbt.ExperimentPayload experimentPayload) {
            FirebaseAbt.ExperimentPayload experimentPayload2 = this.experimentPayload_;
            if (experimentPayload2 == null || experimentPayload2 == FirebaseAbt.ExperimentPayload.getDefaultInstance()) {
                this.experimentPayload_ = experimentPayload;
            } else {
                this.experimentPayload_ = (FirebaseAbt.ExperimentPayload) ((FirebaseAbt.ExperimentPayload.Builder) FirebaseAbt.ExperimentPayload.newBuilder(this.experimentPayload_).mergeFrom(experimentPayload)).buildPartial();
            }
        }

        /* access modifiers changed from: private */
        public void clearExperimentPayload() {
            this.experimentPayload_ = null;
        }

        public long getCampaignStartTimeMillis() {
            return this.campaignStartTimeMillis_;
        }

        /* access modifiers changed from: private */
        public void setCampaignStartTimeMillis(long j) {
            this.campaignStartTimeMillis_ = j;
        }

        /* access modifiers changed from: private */
        public void clearCampaignStartTimeMillis() {
            this.campaignStartTimeMillis_ = 0;
        }

        public long getCampaignEndTimeMillis() {
            return this.campaignEndTimeMillis_;
        }

        /* access modifiers changed from: private */
        public void setCampaignEndTimeMillis(long j) {
            this.campaignEndTimeMillis_ = j;
        }

        /* access modifiers changed from: private */
        public void clearCampaignEndTimeMillis() {
            this.campaignEndTimeMillis_ = 0;
        }

        public String getCampaignName() {
            return this.campaignName_;
        }

        public ByteString getCampaignNameBytes() {
            return ByteString.copyFromUtf8(this.campaignName_);
        }

        /* access modifiers changed from: private */
        public void setCampaignName(String str) {
            if (str != null) {
                this.campaignName_ = str;
                return;
            }
            throw null;
        }

        /* access modifiers changed from: private */
        public void clearCampaignName() {
            this.campaignName_ = getDefaultInstance().getCampaignName();
        }

        /* access modifiers changed from: private */
        public void setCampaignNameBytes(ByteString byteString) {
            if (byteString != null) {
                checkByteStringIsUtf8(byteString);
                this.campaignName_ = byteString.toStringUtf8();
                return;
            }
            throw null;
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (!this.campaignId_.isEmpty()) {
                codedOutputStream.writeString(1, getCampaignId());
            }
            if (this.experimentPayload_ != null) {
                codedOutputStream.writeMessage(2, getExperimentPayload());
            }
            long j = this.campaignStartTimeMillis_;
            if (j != 0) {
                codedOutputStream.writeInt64(3, j);
            }
            long j2 = this.campaignEndTimeMillis_;
            if (j2 != 0) {
                codedOutputStream.writeInt64(4, j2);
            }
            if (!this.campaignName_.isEmpty()) {
                codedOutputStream.writeString(5, getCampaignName());
            }
        }

        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            if (!this.campaignId_.isEmpty()) {
                i2 = 0 + CodedOutputStream.computeStringSize(1, getCampaignId());
            }
            if (this.experimentPayload_ != null) {
                i2 += CodedOutputStream.computeMessageSize(2, getExperimentPayload());
            }
            long j = this.campaignStartTimeMillis_;
            if (j != 0) {
                i2 += CodedOutputStream.computeInt64Size(3, j);
            }
            long j2 = this.campaignEndTimeMillis_;
            if (j2 != 0) {
                i2 += CodedOutputStream.computeInt64Size(4, j2);
            }
            if (!this.campaignName_.isEmpty()) {
                i2 += CodedOutputStream.computeStringSize(5, getCampaignName());
            }
            this.memoizedSerializedSize = i2;
            return i2;
        }

        public static ExperimentalCampaignPayload parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (ExperimentalCampaignPayload) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static ExperimentalCampaignPayload parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (ExperimentalCampaignPayload) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static ExperimentalCampaignPayload parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (ExperimentalCampaignPayload) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static ExperimentalCampaignPayload parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (ExperimentalCampaignPayload) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static ExperimentalCampaignPayload parseFrom(InputStream inputStream) throws IOException {
            return (ExperimentalCampaignPayload) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static ExperimentalCampaignPayload parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ExperimentalCampaignPayload) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static ExperimentalCampaignPayload parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (ExperimentalCampaignPayload) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static ExperimentalCampaignPayload parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ExperimentalCampaignPayload) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static ExperimentalCampaignPayload parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (ExperimentalCampaignPayload) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static ExperimentalCampaignPayload parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ExperimentalCampaignPayload) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(ExperimentalCampaignPayload experimentalCampaignPayload) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(experimentalCampaignPayload);
        }

        /* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
        public static final class Builder extends GeneratedMessageLite.Builder<ExperimentalCampaignPayload, Builder> implements ExperimentalCampaignPayloadOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 r1) {
                this();
            }

            private Builder() {
                super(ExperimentalCampaignPayload.DEFAULT_INSTANCE);
            }

            public String getCampaignId() {
                return ((ExperimentalCampaignPayload) this.instance).getCampaignId();
            }

            public ByteString getCampaignIdBytes() {
                return ((ExperimentalCampaignPayload) this.instance).getCampaignIdBytes();
            }

            public Builder setCampaignId(String str) {
                copyOnWrite();
                ((ExperimentalCampaignPayload) this.instance).setCampaignId(str);
                return this;
            }

            public Builder clearCampaignId() {
                copyOnWrite();
                ((ExperimentalCampaignPayload) this.instance).clearCampaignId();
                return this;
            }

            public Builder setCampaignIdBytes(ByteString byteString) {
                copyOnWrite();
                ((ExperimentalCampaignPayload) this.instance).setCampaignIdBytes(byteString);
                return this;
            }

            public boolean hasExperimentPayload() {
                return ((ExperimentalCampaignPayload) this.instance).hasExperimentPayload();
            }

            public FirebaseAbt.ExperimentPayload getExperimentPayload() {
                return ((ExperimentalCampaignPayload) this.instance).getExperimentPayload();
            }

            public Builder setExperimentPayload(FirebaseAbt.ExperimentPayload experimentPayload) {
                copyOnWrite();
                ((ExperimentalCampaignPayload) this.instance).setExperimentPayload(experimentPayload);
                return this;
            }

            public Builder setExperimentPayload(FirebaseAbt.ExperimentPayload.Builder builder) {
                copyOnWrite();
                ((ExperimentalCampaignPayload) this.instance).setExperimentPayload(builder);
                return this;
            }

            public Builder mergeExperimentPayload(FirebaseAbt.ExperimentPayload experimentPayload) {
                copyOnWrite();
                ((ExperimentalCampaignPayload) this.instance).mergeExperimentPayload(experimentPayload);
                return this;
            }

            public Builder clearExperimentPayload() {
                copyOnWrite();
                ((ExperimentalCampaignPayload) this.instance).clearExperimentPayload();
                return this;
            }

            public long getCampaignStartTimeMillis() {
                return ((ExperimentalCampaignPayload) this.instance).getCampaignStartTimeMillis();
            }

            public Builder setCampaignStartTimeMillis(long j) {
                copyOnWrite();
                ((ExperimentalCampaignPayload) this.instance).setCampaignStartTimeMillis(j);
                return this;
            }

            public Builder clearCampaignStartTimeMillis() {
                copyOnWrite();
                ((ExperimentalCampaignPayload) this.instance).clearCampaignStartTimeMillis();
                return this;
            }

            public long getCampaignEndTimeMillis() {
                return ((ExperimentalCampaignPayload) this.instance).getCampaignEndTimeMillis();
            }

            public Builder setCampaignEndTimeMillis(long j) {
                copyOnWrite();
                ((ExperimentalCampaignPayload) this.instance).setCampaignEndTimeMillis(j);
                return this;
            }

            public Builder clearCampaignEndTimeMillis() {
                copyOnWrite();
                ((ExperimentalCampaignPayload) this.instance).clearCampaignEndTimeMillis();
                return this;
            }

            public String getCampaignName() {
                return ((ExperimentalCampaignPayload) this.instance).getCampaignName();
            }

            public ByteString getCampaignNameBytes() {
                return ((ExperimentalCampaignPayload) this.instance).getCampaignNameBytes();
            }

            public Builder setCampaignName(String str) {
                copyOnWrite();
                ((ExperimentalCampaignPayload) this.instance).setCampaignName(str);
                return this;
            }

            public Builder clearCampaignName() {
                copyOnWrite();
                ((ExperimentalCampaignPayload) this.instance).clearCampaignName();
                return this;
            }

            public Builder setCampaignNameBytes(ByteString byteString) {
                copyOnWrite();
                ((ExperimentalCampaignPayload) this.instance).setCampaignNameBytes(byteString);
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            boolean z = false;
            switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
                case 1:
                    return new ExperimentalCampaignPayload();
                case 2:
                    return DEFAULT_INSTANCE;
                case 3:
                    return null;
                case 4:
                    return new Builder((AnonymousClass1) null);
                case 5:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                    ExperimentalCampaignPayload experimentalCampaignPayload = (ExperimentalCampaignPayload) obj2;
                    this.campaignId_ = visitor.visitString(!this.campaignId_.isEmpty(), this.campaignId_, !experimentalCampaignPayload.campaignId_.isEmpty(), experimentalCampaignPayload.campaignId_);
                    this.experimentPayload_ = (FirebaseAbt.ExperimentPayload) visitor.visitMessage(this.experimentPayload_, experimentalCampaignPayload.experimentPayload_);
                    this.campaignStartTimeMillis_ = visitor.visitLong(this.campaignStartTimeMillis_ != 0, this.campaignStartTimeMillis_, experimentalCampaignPayload.campaignStartTimeMillis_ != 0, experimentalCampaignPayload.campaignStartTimeMillis_);
                    this.campaignEndTimeMillis_ = visitor.visitLong(this.campaignEndTimeMillis_ != 0, this.campaignEndTimeMillis_, experimentalCampaignPayload.campaignEndTimeMillis_ != 0, experimentalCampaignPayload.campaignEndTimeMillis_);
                    this.campaignName_ = visitor.visitString(!this.campaignName_.isEmpty(), this.campaignName_, !experimentalCampaignPayload.campaignName_.isEmpty(), experimentalCampaignPayload.campaignName_);
                    GeneratedMessageLite.MergeFromVisitor mergeFromVisitor = GeneratedMessageLite.MergeFromVisitor.INSTANCE;
                    return this;
                case 6:
                    CodedInputStream codedInputStream = (CodedInputStream) obj;
                    ExtensionRegistryLite extensionRegistryLite = (ExtensionRegistryLite) obj2;
                    while (!z) {
                        try {
                            int readTag = codedInputStream.readTag();
                            if (readTag != 0) {
                                if (readTag == 10) {
                                    this.campaignId_ = codedInputStream.readStringRequireUtf8();
                                } else if (readTag == 18) {
                                    FirebaseAbt.ExperimentPayload.Builder builder = this.experimentPayload_ != null ? (FirebaseAbt.ExperimentPayload.Builder) this.experimentPayload_.toBuilder() : null;
                                    FirebaseAbt.ExperimentPayload experimentPayload = (FirebaseAbt.ExperimentPayload) codedInputStream.readMessage(FirebaseAbt.ExperimentPayload.parser(), extensionRegistryLite);
                                    this.experimentPayload_ = experimentPayload;
                                    if (builder != null) {
                                        builder.mergeFrom(experimentPayload);
                                        this.experimentPayload_ = (FirebaseAbt.ExperimentPayload) builder.buildPartial();
                                    }
                                } else if (readTag == 24) {
                                    this.campaignStartTimeMillis_ = codedInputStream.readInt64();
                                } else if (readTag == 32) {
                                    this.campaignEndTimeMillis_ = codedInputStream.readInt64();
                                } else if (readTag == 42) {
                                    this.campaignName_ = codedInputStream.readStringRequireUtf8();
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
                        synchronized (ExperimentalCampaignPayload.class) {
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
            ExperimentalCampaignPayload experimentalCampaignPayload = new ExperimentalCampaignPayload();
            DEFAULT_INSTANCE = experimentalCampaignPayload;
            experimentalCampaignPayload.makeImmutable();
        }

        public static ExperimentalCampaignPayload getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<ExperimentalCampaignPayload> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }
}
