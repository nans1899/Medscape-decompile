package com.google.firebase.inappmessaging;

import com.google.firebase.inappmessaging.MessagesProto;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
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
import com.google.type.Date;
import com.google.type.TimeOfDay;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

/* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
public final class CommonTypesProto {

    /* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
    public interface CampaignTimeOrBuilder extends MessageLiteOrBuilder {
        Date getDate();

        TimeOfDay getTime();

        String getTimeZone();

        ByteString getTimeZoneBytes();

        boolean hasDate();

        boolean hasTime();
    }

    /* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
    public interface DailyAnalyticsSummaryOrBuilder extends MessageLiteOrBuilder {
        int getClicks();

        int getErrors();

        int getImpressions();

        long getStartOfDayMillis();
    }

    /* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
    public interface DailyConversionSummaryOrBuilder extends MessageLiteOrBuilder {
        int getConversions();

        long getStartOfDayMillis();
    }

    /* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
    public interface EventOrBuilder extends MessageLiteOrBuilder {
        int getCount();

        String getName();

        ByteString getNameBytes();

        long getPreviousTimestampMillis();

        long getTimestampMillis();

        TriggerParam getTriggerParams(int i);

        int getTriggerParamsCount();

        List<TriggerParam> getTriggerParamsList();
    }

    /* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
    public interface ExperimentVariantOrBuilder extends MessageLiteOrBuilder {
        MessagesProto.Content getContent();

        int getIndex();

        boolean hasContent();
    }

    /* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
    public interface PriorityOrBuilder extends MessageLiteOrBuilder {
        int getValue();
    }

    /* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
    public interface ScionConversionEventOrBuilder extends MessageLiteOrBuilder {
        String getName();

        ByteString getNameBytes();
    }

    /* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
    public interface TriggerParamOrBuilder extends MessageLiteOrBuilder {
        double getDoubleValue();

        float getFloatValue();

        long getIntValue();

        String getName();

        ByteString getNameBytes();

        String getStringValue();

        ByteString getStringValueBytes();
    }

    /* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
    public interface TriggeringConditionOrBuilder extends MessageLiteOrBuilder {
        TriggeringCondition.ConditionCase getConditionCase();

        Event getEvent();

        Trigger getFiamTrigger();

        int getFiamTriggerValue();
    }

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }

    private CommonTypesProto() {
    }

    /* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
    public enum Trigger implements Internal.EnumLite {
        UNKNOWN_TRIGGER(0),
        APP_LAUNCH(1),
        ON_FOREGROUND(2),
        UNRECOGNIZED(-1);
        
        public static final int APP_LAUNCH_VALUE = 1;
        public static final int ON_FOREGROUND_VALUE = 2;
        public static final int UNKNOWN_TRIGGER_VALUE = 0;
        private static final Internal.EnumLiteMap<Trigger> internalValueMap = null;
        private final int value;

        static {
            internalValueMap = new Internal.EnumLiteMap<Trigger>() {
                public Trigger findValueByNumber(int i) {
                    return Trigger.forNumber(i);
                }
            };
        }

        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static Trigger valueOf(int i) {
            return forNumber(i);
        }

        public static Trigger forNumber(int i) {
            if (i == 0) {
                return UNKNOWN_TRIGGER;
            }
            if (i == 1) {
                return APP_LAUNCH;
            }
            if (i != 2) {
                return null;
            }
            return ON_FOREGROUND;
        }

        public static Internal.EnumLiteMap<Trigger> internalGetValueMap() {
            return internalValueMap;
        }

        private Trigger(int i) {
            this.value = i;
        }
    }

    /* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
    public enum CampaignState implements Internal.EnumLite {
        UNKNOWN_CAMPAIGN_STATE(0),
        DRAFT(1),
        PUBLISHED(2),
        STOPPED(3),
        DELETED(4),
        UNRECOGNIZED(-1);
        
        public static final int DELETED_VALUE = 4;
        public static final int DRAFT_VALUE = 1;
        public static final int PUBLISHED_VALUE = 2;
        public static final int STOPPED_VALUE = 3;
        public static final int UNKNOWN_CAMPAIGN_STATE_VALUE = 0;
        private static final Internal.EnumLiteMap<CampaignState> internalValueMap = null;
        private final int value;

        static {
            internalValueMap = new Internal.EnumLiteMap<CampaignState>() {
                public CampaignState findValueByNumber(int i) {
                    return CampaignState.forNumber(i);
                }
            };
        }

        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static CampaignState valueOf(int i) {
            return forNumber(i);
        }

        public static CampaignState forNumber(int i) {
            if (i == 0) {
                return UNKNOWN_CAMPAIGN_STATE;
            }
            if (i == 1) {
                return DRAFT;
            }
            if (i == 2) {
                return PUBLISHED;
            }
            if (i == 3) {
                return STOPPED;
            }
            if (i != 4) {
                return null;
            }
            return DELETED;
        }

        public static Internal.EnumLiteMap<CampaignState> internalGetValueMap() {
            return internalValueMap;
        }

        private CampaignState(int i) {
            this.value = i;
        }
    }

    /* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
    public enum ExperimentalCampaignState implements Internal.EnumLite {
        UNKNOWN_EXPERIMENTAL_CAMPAIGN_STATE(0),
        EXPERIMENT_DRAFT(1),
        EXPERIMENT_RUNNING(2),
        EXPERIMENT_STOPPED(3),
        EXPERIMENT_ROLLED_OUT(4),
        UNRECOGNIZED(-1);
        
        public static final int EXPERIMENT_DRAFT_VALUE = 1;
        public static final int EXPERIMENT_ROLLED_OUT_VALUE = 4;
        public static final int EXPERIMENT_RUNNING_VALUE = 2;
        public static final int EXPERIMENT_STOPPED_VALUE = 3;
        public static final int UNKNOWN_EXPERIMENTAL_CAMPAIGN_STATE_VALUE = 0;
        private static final Internal.EnumLiteMap<ExperimentalCampaignState> internalValueMap = null;
        private final int value;

        static {
            internalValueMap = new Internal.EnumLiteMap<ExperimentalCampaignState>() {
                public ExperimentalCampaignState findValueByNumber(int i) {
                    return ExperimentalCampaignState.forNumber(i);
                }
            };
        }

        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static ExperimentalCampaignState valueOf(int i) {
            return forNumber(i);
        }

        public static ExperimentalCampaignState forNumber(int i) {
            if (i == 0) {
                return UNKNOWN_EXPERIMENTAL_CAMPAIGN_STATE;
            }
            if (i == 1) {
                return EXPERIMENT_DRAFT;
            }
            if (i == 2) {
                return EXPERIMENT_RUNNING;
            }
            if (i == 3) {
                return EXPERIMENT_STOPPED;
            }
            if (i != 4) {
                return null;
            }
            return EXPERIMENT_ROLLED_OUT;
        }

        public static Internal.EnumLiteMap<ExperimentalCampaignState> internalGetValueMap() {
            return internalValueMap;
        }

        private ExperimentalCampaignState(int i) {
            this.value = i;
        }
    }

    /* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
    public static final class CampaignTime extends GeneratedMessageLite<CampaignTime, Builder> implements CampaignTimeOrBuilder {
        public static final int DATE_FIELD_NUMBER = 1;
        /* access modifiers changed from: private */
        public static final CampaignTime DEFAULT_INSTANCE;
        private static volatile Parser<CampaignTime> PARSER = null;
        public static final int TIME_FIELD_NUMBER = 2;
        public static final int TIME_ZONE_FIELD_NUMBER = 3;
        private Date date_;
        private String timeZone_ = "";
        private TimeOfDay time_;

        private CampaignTime() {
        }

        public boolean hasDate() {
            return this.date_ != null;
        }

        public Date getDate() {
            Date date = this.date_;
            return date == null ? Date.getDefaultInstance() : date;
        }

        /* access modifiers changed from: private */
        public void setDate(Date date) {
            if (date != null) {
                this.date_ = date;
                return;
            }
            throw null;
        }

        /* access modifiers changed from: private */
        public void setDate(Date.Builder builder) {
            this.date_ = (Date) builder.build();
        }

        /* access modifiers changed from: private */
        public void mergeDate(Date date) {
            Date date2 = this.date_;
            if (date2 == null || date2 == Date.getDefaultInstance()) {
                this.date_ = date;
            } else {
                this.date_ = (Date) ((Date.Builder) Date.newBuilder(this.date_).mergeFrom(date)).buildPartial();
            }
        }

        /* access modifiers changed from: private */
        public void clearDate() {
            this.date_ = null;
        }

        public boolean hasTime() {
            return this.time_ != null;
        }

        public TimeOfDay getTime() {
            TimeOfDay timeOfDay = this.time_;
            return timeOfDay == null ? TimeOfDay.getDefaultInstance() : timeOfDay;
        }

        /* access modifiers changed from: private */
        public void setTime(TimeOfDay timeOfDay) {
            if (timeOfDay != null) {
                this.time_ = timeOfDay;
                return;
            }
            throw null;
        }

        /* access modifiers changed from: private */
        public void setTime(TimeOfDay.Builder builder) {
            this.time_ = (TimeOfDay) builder.build();
        }

        /* access modifiers changed from: private */
        public void mergeTime(TimeOfDay timeOfDay) {
            TimeOfDay timeOfDay2 = this.time_;
            if (timeOfDay2 == null || timeOfDay2 == TimeOfDay.getDefaultInstance()) {
                this.time_ = timeOfDay;
            } else {
                this.time_ = (TimeOfDay) ((TimeOfDay.Builder) TimeOfDay.newBuilder(this.time_).mergeFrom(timeOfDay)).buildPartial();
            }
        }

        /* access modifiers changed from: private */
        public void clearTime() {
            this.time_ = null;
        }

        public String getTimeZone() {
            return this.timeZone_;
        }

        public ByteString getTimeZoneBytes() {
            return ByteString.copyFromUtf8(this.timeZone_);
        }

        /* access modifiers changed from: private */
        public void setTimeZone(String str) {
            if (str != null) {
                this.timeZone_ = str;
                return;
            }
            throw null;
        }

        /* access modifiers changed from: private */
        public void clearTimeZone() {
            this.timeZone_ = getDefaultInstance().getTimeZone();
        }

        /* access modifiers changed from: private */
        public void setTimeZoneBytes(ByteString byteString) {
            if (byteString != null) {
                checkByteStringIsUtf8(byteString);
                this.timeZone_ = byteString.toStringUtf8();
                return;
            }
            throw null;
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (this.date_ != null) {
                codedOutputStream.writeMessage(1, getDate());
            }
            if (this.time_ != null) {
                codedOutputStream.writeMessage(2, getTime());
            }
            if (!this.timeZone_.isEmpty()) {
                codedOutputStream.writeString(3, getTimeZone());
            }
        }

        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            if (this.date_ != null) {
                i2 = 0 + CodedOutputStream.computeMessageSize(1, getDate());
            }
            if (this.time_ != null) {
                i2 += CodedOutputStream.computeMessageSize(2, getTime());
            }
            if (!this.timeZone_.isEmpty()) {
                i2 += CodedOutputStream.computeStringSize(3, getTimeZone());
            }
            this.memoizedSerializedSize = i2;
            return i2;
        }

        public static CampaignTime parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (CampaignTime) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static CampaignTime parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (CampaignTime) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static CampaignTime parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (CampaignTime) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static CampaignTime parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (CampaignTime) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static CampaignTime parseFrom(InputStream inputStream) throws IOException {
            return (CampaignTime) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static CampaignTime parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (CampaignTime) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static CampaignTime parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (CampaignTime) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static CampaignTime parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (CampaignTime) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static CampaignTime parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (CampaignTime) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static CampaignTime parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (CampaignTime) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(CampaignTime campaignTime) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(campaignTime);
        }

        /* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
        public static final class Builder extends GeneratedMessageLite.Builder<CampaignTime, Builder> implements CampaignTimeOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 r1) {
                this();
            }

            private Builder() {
                super(CampaignTime.DEFAULT_INSTANCE);
            }

            public boolean hasDate() {
                return ((CampaignTime) this.instance).hasDate();
            }

            public Date getDate() {
                return ((CampaignTime) this.instance).getDate();
            }

            public Builder setDate(Date date) {
                copyOnWrite();
                ((CampaignTime) this.instance).setDate(date);
                return this;
            }

            public Builder setDate(Date.Builder builder) {
                copyOnWrite();
                ((CampaignTime) this.instance).setDate(builder);
                return this;
            }

            public Builder mergeDate(Date date) {
                copyOnWrite();
                ((CampaignTime) this.instance).mergeDate(date);
                return this;
            }

            public Builder clearDate() {
                copyOnWrite();
                ((CampaignTime) this.instance).clearDate();
                return this;
            }

            public boolean hasTime() {
                return ((CampaignTime) this.instance).hasTime();
            }

            public TimeOfDay getTime() {
                return ((CampaignTime) this.instance).getTime();
            }

            public Builder setTime(TimeOfDay timeOfDay) {
                copyOnWrite();
                ((CampaignTime) this.instance).setTime(timeOfDay);
                return this;
            }

            public Builder setTime(TimeOfDay.Builder builder) {
                copyOnWrite();
                ((CampaignTime) this.instance).setTime(builder);
                return this;
            }

            public Builder mergeTime(TimeOfDay timeOfDay) {
                copyOnWrite();
                ((CampaignTime) this.instance).mergeTime(timeOfDay);
                return this;
            }

            public Builder clearTime() {
                copyOnWrite();
                ((CampaignTime) this.instance).clearTime();
                return this;
            }

            public String getTimeZone() {
                return ((CampaignTime) this.instance).getTimeZone();
            }

            public ByteString getTimeZoneBytes() {
                return ((CampaignTime) this.instance).getTimeZoneBytes();
            }

            public Builder setTimeZone(String str) {
                copyOnWrite();
                ((CampaignTime) this.instance).setTimeZone(str);
                return this;
            }

            public Builder clearTimeZone() {
                copyOnWrite();
                ((CampaignTime) this.instance).clearTimeZone();
                return this;
            }

            public Builder setTimeZoneBytes(ByteString byteString) {
                copyOnWrite();
                ((CampaignTime) this.instance).setTimeZoneBytes(byteString);
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
                case 1:
                    return new CampaignTime();
                case 2:
                    return DEFAULT_INSTANCE;
                case 3:
                    return null;
                case 4:
                    return new Builder((AnonymousClass1) null);
                case 5:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                    CampaignTime campaignTime = (CampaignTime) obj2;
                    this.date_ = (Date) visitor.visitMessage(this.date_, campaignTime.date_);
                    this.time_ = (TimeOfDay) visitor.visitMessage(this.time_, campaignTime.time_);
                    this.timeZone_ = visitor.visitString(!this.timeZone_.isEmpty(), this.timeZone_, true ^ campaignTime.timeZone_.isEmpty(), campaignTime.timeZone_);
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
                                    Date.Builder builder = this.date_ != null ? (Date.Builder) this.date_.toBuilder() : null;
                                    Date date = (Date) codedInputStream.readMessage(Date.parser(), extensionRegistryLite);
                                    this.date_ = date;
                                    if (builder != null) {
                                        builder.mergeFrom(date);
                                        this.date_ = (Date) builder.buildPartial();
                                    }
                                } else if (readTag == 18) {
                                    TimeOfDay.Builder builder2 = this.time_ != null ? (TimeOfDay.Builder) this.time_.toBuilder() : null;
                                    TimeOfDay timeOfDay = (TimeOfDay) codedInputStream.readMessage(TimeOfDay.parser(), extensionRegistryLite);
                                    this.time_ = timeOfDay;
                                    if (builder2 != null) {
                                        builder2.mergeFrom(timeOfDay);
                                        this.time_ = (TimeOfDay) builder2.buildPartial();
                                    }
                                } else if (readTag == 26) {
                                    this.timeZone_ = codedInputStream.readStringRequireUtf8();
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
                        synchronized (CampaignTime.class) {
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
            CampaignTime campaignTime = new CampaignTime();
            DEFAULT_INSTANCE = campaignTime;
            campaignTime.makeImmutable();
        }

        public static CampaignTime getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<CampaignTime> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    /* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
    public static final class TriggeringCondition extends GeneratedMessageLite<TriggeringCondition, Builder> implements TriggeringConditionOrBuilder {
        /* access modifiers changed from: private */
        public static final TriggeringCondition DEFAULT_INSTANCE;
        public static final int EVENT_FIELD_NUMBER = 2;
        public static final int FIAM_TRIGGER_FIELD_NUMBER = 1;
        private static volatile Parser<TriggeringCondition> PARSER;
        private int conditionCase_ = 0;
        private Object condition_;

        private TriggeringCondition() {
        }

        /* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
        public enum ConditionCase implements Internal.EnumLite {
            FIAM_TRIGGER(1),
            EVENT(2),
            CONDITION_NOT_SET(0);
            
            private final int value;

            private ConditionCase(int i) {
                this.value = i;
            }

            @Deprecated
            public static ConditionCase valueOf(int i) {
                return forNumber(i);
            }

            public static ConditionCase forNumber(int i) {
                if (i == 0) {
                    return CONDITION_NOT_SET;
                }
                if (i == 1) {
                    return FIAM_TRIGGER;
                }
                if (i != 2) {
                    return null;
                }
                return EVENT;
            }

            public int getNumber() {
                return this.value;
            }
        }

        public ConditionCase getConditionCase() {
            return ConditionCase.forNumber(this.conditionCase_);
        }

        /* access modifiers changed from: private */
        public void clearCondition() {
            this.conditionCase_ = 0;
            this.condition_ = null;
        }

        public int getFiamTriggerValue() {
            if (this.conditionCase_ == 1) {
                return ((Integer) this.condition_).intValue();
            }
            return 0;
        }

        public Trigger getFiamTrigger() {
            if (this.conditionCase_ != 1) {
                return Trigger.UNKNOWN_TRIGGER;
            }
            Trigger forNumber = Trigger.forNumber(((Integer) this.condition_).intValue());
            return forNumber == null ? Trigger.UNRECOGNIZED : forNumber;
        }

        /* access modifiers changed from: private */
        public void setFiamTriggerValue(int i) {
            this.conditionCase_ = 1;
            this.condition_ = Integer.valueOf(i);
        }

        /* access modifiers changed from: private */
        public void setFiamTrigger(Trigger trigger) {
            if (trigger != null) {
                this.conditionCase_ = 1;
                this.condition_ = Integer.valueOf(trigger.getNumber());
                return;
            }
            throw null;
        }

        /* access modifiers changed from: private */
        public void clearFiamTrigger() {
            if (this.conditionCase_ == 1) {
                this.conditionCase_ = 0;
                this.condition_ = null;
            }
        }

        public Event getEvent() {
            if (this.conditionCase_ == 2) {
                return (Event) this.condition_;
            }
            return Event.getDefaultInstance();
        }

        /* access modifiers changed from: private */
        public void setEvent(Event event) {
            if (event != null) {
                this.condition_ = event;
                this.conditionCase_ = 2;
                return;
            }
            throw null;
        }

        /* access modifiers changed from: private */
        public void setEvent(Event.Builder builder) {
            this.condition_ = builder.build();
            this.conditionCase_ = 2;
        }

        /* access modifiers changed from: private */
        public void mergeEvent(Event event) {
            if (this.conditionCase_ != 2 || this.condition_ == Event.getDefaultInstance()) {
                this.condition_ = event;
            } else {
                this.condition_ = ((Event.Builder) Event.newBuilder((Event) this.condition_).mergeFrom(event)).buildPartial();
            }
            this.conditionCase_ = 2;
        }

        /* access modifiers changed from: private */
        public void clearEvent() {
            if (this.conditionCase_ == 2) {
                this.conditionCase_ = 0;
                this.condition_ = null;
            }
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (this.conditionCase_ == 1) {
                codedOutputStream.writeEnum(1, ((Integer) this.condition_).intValue());
            }
            if (this.conditionCase_ == 2) {
                codedOutputStream.writeMessage(2, (Event) this.condition_);
            }
        }

        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            if (this.conditionCase_ == 1) {
                i2 = 0 + CodedOutputStream.computeEnumSize(1, ((Integer) this.condition_).intValue());
            }
            if (this.conditionCase_ == 2) {
                i2 += CodedOutputStream.computeMessageSize(2, (Event) this.condition_);
            }
            this.memoizedSerializedSize = i2;
            return i2;
        }

        public static TriggeringCondition parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (TriggeringCondition) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static TriggeringCondition parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (TriggeringCondition) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static TriggeringCondition parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (TriggeringCondition) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static TriggeringCondition parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (TriggeringCondition) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static TriggeringCondition parseFrom(InputStream inputStream) throws IOException {
            return (TriggeringCondition) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static TriggeringCondition parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (TriggeringCondition) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static TriggeringCondition parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (TriggeringCondition) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static TriggeringCondition parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (TriggeringCondition) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static TriggeringCondition parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (TriggeringCondition) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static TriggeringCondition parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (TriggeringCondition) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(TriggeringCondition triggeringCondition) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(triggeringCondition);
        }

        /* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
        public static final class Builder extends GeneratedMessageLite.Builder<TriggeringCondition, Builder> implements TriggeringConditionOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 r1) {
                this();
            }

            private Builder() {
                super(TriggeringCondition.DEFAULT_INSTANCE);
            }

            public ConditionCase getConditionCase() {
                return ((TriggeringCondition) this.instance).getConditionCase();
            }

            public Builder clearCondition() {
                copyOnWrite();
                ((TriggeringCondition) this.instance).clearCondition();
                return this;
            }

            public int getFiamTriggerValue() {
                return ((TriggeringCondition) this.instance).getFiamTriggerValue();
            }

            public Builder setFiamTriggerValue(int i) {
                copyOnWrite();
                ((TriggeringCondition) this.instance).setFiamTriggerValue(i);
                return this;
            }

            public Trigger getFiamTrigger() {
                return ((TriggeringCondition) this.instance).getFiamTrigger();
            }

            public Builder setFiamTrigger(Trigger trigger) {
                copyOnWrite();
                ((TriggeringCondition) this.instance).setFiamTrigger(trigger);
                return this;
            }

            public Builder clearFiamTrigger() {
                copyOnWrite();
                ((TriggeringCondition) this.instance).clearFiamTrigger();
                return this;
            }

            public Event getEvent() {
                return ((TriggeringCondition) this.instance).getEvent();
            }

            public Builder setEvent(Event event) {
                copyOnWrite();
                ((TriggeringCondition) this.instance).setEvent(event);
                return this;
            }

            public Builder setEvent(Event.Builder builder) {
                copyOnWrite();
                ((TriggeringCondition) this.instance).setEvent(builder);
                return this;
            }

            public Builder mergeEvent(Event event) {
                copyOnWrite();
                ((TriggeringCondition) this.instance).mergeEvent(event);
                return this;
            }

            public Builder clearEvent() {
                copyOnWrite();
                ((TriggeringCondition) this.instance).clearEvent();
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            int i;
            boolean z = false;
            switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
                case 1:
                    return new TriggeringCondition();
                case 2:
                    return DEFAULT_INSTANCE;
                case 3:
                    return null;
                case 4:
                    return new Builder((AnonymousClass1) null);
                case 5:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                    TriggeringCondition triggeringCondition = (TriggeringCondition) obj2;
                    int i2 = AnonymousClass1.$SwitchMap$com$google$firebase$inappmessaging$CommonTypesProto$TriggeringCondition$ConditionCase[triggeringCondition.getConditionCase().ordinal()];
                    if (i2 == 1) {
                        if (this.conditionCase_ == 1) {
                            z = true;
                        }
                        this.condition_ = visitor.visitOneofInt(z, this.condition_, triggeringCondition.condition_);
                    } else if (i2 == 2) {
                        if (this.conditionCase_ == 2) {
                            z = true;
                        }
                        this.condition_ = visitor.visitOneofMessage(z, this.condition_, triggeringCondition.condition_);
                    } else if (i2 == 3) {
                        if (this.conditionCase_ != 0) {
                            z = true;
                        }
                        visitor.visitOneofNotSet(z);
                    }
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE && (i = triggeringCondition.conditionCase_) != 0) {
                        this.conditionCase_ = i;
                    }
                    return this;
                case 6:
                    CodedInputStream codedInputStream = (CodedInputStream) obj;
                    ExtensionRegistryLite extensionRegistryLite = (ExtensionRegistryLite) obj2;
                    while (!z) {
                        try {
                            int readTag = codedInputStream.readTag();
                            if (readTag != 0) {
                                if (readTag == 8) {
                                    int readEnum = codedInputStream.readEnum();
                                    this.conditionCase_ = 1;
                                    this.condition_ = Integer.valueOf(readEnum);
                                } else if (readTag == 18) {
                                    Event.Builder builder = this.conditionCase_ == 2 ? (Event.Builder) ((Event) this.condition_).toBuilder() : null;
                                    MessageLite readMessage = codedInputStream.readMessage(Event.parser(), extensionRegistryLite);
                                    this.condition_ = readMessage;
                                    if (builder != null) {
                                        builder.mergeFrom((Event) readMessage);
                                        this.condition_ = builder.buildPartial();
                                    }
                                    this.conditionCase_ = 2;
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
                        synchronized (TriggeringCondition.class) {
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
            TriggeringCondition triggeringCondition = new TriggeringCondition();
            DEFAULT_INSTANCE = triggeringCondition;
            triggeringCondition.makeImmutable();
        }

        public static TriggeringCondition getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<TriggeringCondition> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    /* renamed from: com.google.firebase.inappmessaging.CommonTypesProto$1  reason: invalid class name */
    /* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$firebase$inappmessaging$CommonTypesProto$TriggeringCondition$ConditionCase;
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
                com.google.firebase.inappmessaging.CommonTypesProto$TriggeringCondition$ConditionCase[] r0 = com.google.firebase.inappmessaging.CommonTypesProto.TriggeringCondition.ConditionCase.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$google$firebase$inappmessaging$CommonTypesProto$TriggeringCondition$ConditionCase = r0
                r1 = 1
                com.google.firebase.inappmessaging.CommonTypesProto$TriggeringCondition$ConditionCase r2 = com.google.firebase.inappmessaging.CommonTypesProto.TriggeringCondition.ConditionCase.FIAM_TRIGGER     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0[r2] = r1     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                r0 = 2
                int[] r2 = $SwitchMap$com$google$firebase$inappmessaging$CommonTypesProto$TriggeringCondition$ConditionCase     // Catch:{ NoSuchFieldError -> 0x001d }
                com.google.firebase.inappmessaging.CommonTypesProto$TriggeringCondition$ConditionCase r3 = com.google.firebase.inappmessaging.CommonTypesProto.TriggeringCondition.ConditionCase.EVENT     // Catch:{ NoSuchFieldError -> 0x001d }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2[r3] = r0     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                r2 = 3
                int[] r3 = $SwitchMap$com$google$firebase$inappmessaging$CommonTypesProto$TriggeringCondition$ConditionCase     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.google.firebase.inappmessaging.CommonTypesProto$TriggeringCondition$ConditionCase r4 = com.google.firebase.inappmessaging.CommonTypesProto.TriggeringCondition.ConditionCase.CONDITION_NOT_SET     // Catch:{ NoSuchFieldError -> 0x0028 }
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.inappmessaging.CommonTypesProto.AnonymousClass1.<clinit>():void");
        }
    }

    /* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
    public static final class Event extends GeneratedMessageLite<Event, Builder> implements EventOrBuilder {
        public static final int COUNT_FIELD_NUMBER = 5;
        /* access modifiers changed from: private */
        public static final Event DEFAULT_INSTANCE;
        public static final int NAME_FIELD_NUMBER = 2;
        private static volatile Parser<Event> PARSER = null;
        public static final int PREVIOUS_TIMESTAMP_MILLIS_FIELD_NUMBER = 4;
        public static final int TIMESTAMP_MILLIS_FIELD_NUMBER = 3;
        public static final int TRIGGER_PARAMS_FIELD_NUMBER = 1;
        private int bitField0_;
        private int count_;
        private String name_ = "";
        private long previousTimestampMillis_;
        private long timestampMillis_;
        private Internal.ProtobufList<TriggerParam> triggerParams_ = emptyProtobufList();

        private Event() {
        }

        public List<TriggerParam> getTriggerParamsList() {
            return this.triggerParams_;
        }

        public List<? extends TriggerParamOrBuilder> getTriggerParamsOrBuilderList() {
            return this.triggerParams_;
        }

        public int getTriggerParamsCount() {
            return this.triggerParams_.size();
        }

        public TriggerParam getTriggerParams(int i) {
            return (TriggerParam) this.triggerParams_.get(i);
        }

        public TriggerParamOrBuilder getTriggerParamsOrBuilder(int i) {
            return (TriggerParamOrBuilder) this.triggerParams_.get(i);
        }

        private void ensureTriggerParamsIsMutable() {
            if (!this.triggerParams_.isModifiable()) {
                this.triggerParams_ = GeneratedMessageLite.mutableCopy(this.triggerParams_);
            }
        }

        /* access modifiers changed from: private */
        public void setTriggerParams(int i, TriggerParam triggerParam) {
            if (triggerParam != null) {
                ensureTriggerParamsIsMutable();
                this.triggerParams_.set(i, triggerParam);
                return;
            }
            throw null;
        }

        /* access modifiers changed from: private */
        public void setTriggerParams(int i, TriggerParam.Builder builder) {
            ensureTriggerParamsIsMutable();
            this.triggerParams_.set(i, (TriggerParam) builder.build());
        }

        /* access modifiers changed from: private */
        public void addTriggerParams(TriggerParam triggerParam) {
            if (triggerParam != null) {
                ensureTriggerParamsIsMutable();
                this.triggerParams_.add(triggerParam);
                return;
            }
            throw null;
        }

        /* access modifiers changed from: private */
        public void addTriggerParams(int i, TriggerParam triggerParam) {
            if (triggerParam != null) {
                ensureTriggerParamsIsMutable();
                this.triggerParams_.add(i, triggerParam);
                return;
            }
            throw null;
        }

        /* access modifiers changed from: private */
        public void addTriggerParams(TriggerParam.Builder builder) {
            ensureTriggerParamsIsMutable();
            this.triggerParams_.add((TriggerParam) builder.build());
        }

        /* access modifiers changed from: private */
        public void addTriggerParams(int i, TriggerParam.Builder builder) {
            ensureTriggerParamsIsMutable();
            this.triggerParams_.add(i, (TriggerParam) builder.build());
        }

        /* access modifiers changed from: private */
        public void addAllTriggerParams(Iterable<? extends TriggerParam> iterable) {
            ensureTriggerParamsIsMutable();
            AbstractMessageLite.addAll(iterable, this.triggerParams_);
        }

        /* access modifiers changed from: private */
        public void clearTriggerParams() {
            this.triggerParams_ = emptyProtobufList();
        }

        /* access modifiers changed from: private */
        public void removeTriggerParams(int i) {
            ensureTriggerParamsIsMutable();
            this.triggerParams_.remove(i);
        }

        public String getName() {
            return this.name_;
        }

        public ByteString getNameBytes() {
            return ByteString.copyFromUtf8(this.name_);
        }

        /* access modifiers changed from: private */
        public void setName(String str) {
            if (str != null) {
                this.name_ = str;
                return;
            }
            throw null;
        }

        /* access modifiers changed from: private */
        public void clearName() {
            this.name_ = getDefaultInstance().getName();
        }

        /* access modifiers changed from: private */
        public void setNameBytes(ByteString byteString) {
            if (byteString != null) {
                checkByteStringIsUtf8(byteString);
                this.name_ = byteString.toStringUtf8();
                return;
            }
            throw null;
        }

        public long getTimestampMillis() {
            return this.timestampMillis_;
        }

        /* access modifiers changed from: private */
        public void setTimestampMillis(long j) {
            this.timestampMillis_ = j;
        }

        /* access modifiers changed from: private */
        public void clearTimestampMillis() {
            this.timestampMillis_ = 0;
        }

        public long getPreviousTimestampMillis() {
            return this.previousTimestampMillis_;
        }

        /* access modifiers changed from: private */
        public void setPreviousTimestampMillis(long j) {
            this.previousTimestampMillis_ = j;
        }

        /* access modifiers changed from: private */
        public void clearPreviousTimestampMillis() {
            this.previousTimestampMillis_ = 0;
        }

        public int getCount() {
            return this.count_;
        }

        /* access modifiers changed from: private */
        public void setCount(int i) {
            this.count_ = i;
        }

        /* access modifiers changed from: private */
        public void clearCount() {
            this.count_ = 0;
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            for (int i = 0; i < this.triggerParams_.size(); i++) {
                codedOutputStream.writeMessage(1, (MessageLite) this.triggerParams_.get(i));
            }
            if (!this.name_.isEmpty()) {
                codedOutputStream.writeString(2, getName());
            }
            long j = this.timestampMillis_;
            if (j != 0) {
                codedOutputStream.writeInt64(3, j);
            }
            long j2 = this.previousTimestampMillis_;
            if (j2 != 0) {
                codedOutputStream.writeInt64(4, j2);
            }
            int i2 = this.count_;
            if (i2 != 0) {
                codedOutputStream.writeInt32(5, i2);
            }
        }

        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            for (int i3 = 0; i3 < this.triggerParams_.size(); i3++) {
                i2 += CodedOutputStream.computeMessageSize(1, (MessageLite) this.triggerParams_.get(i3));
            }
            if (!this.name_.isEmpty()) {
                i2 += CodedOutputStream.computeStringSize(2, getName());
            }
            long j = this.timestampMillis_;
            if (j != 0) {
                i2 += CodedOutputStream.computeInt64Size(3, j);
            }
            long j2 = this.previousTimestampMillis_;
            if (j2 != 0) {
                i2 += CodedOutputStream.computeInt64Size(4, j2);
            }
            int i4 = this.count_;
            if (i4 != 0) {
                i2 += CodedOutputStream.computeInt32Size(5, i4);
            }
            this.memoizedSerializedSize = i2;
            return i2;
        }

        public static Event parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (Event) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static Event parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Event) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static Event parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (Event) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static Event parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Event) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static Event parseFrom(InputStream inputStream) throws IOException {
            return (Event) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static Event parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Event) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static Event parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (Event) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static Event parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Event) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static Event parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (Event) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static Event parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Event) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(Event event) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(event);
        }

        /* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
        public static final class Builder extends GeneratedMessageLite.Builder<Event, Builder> implements EventOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 r1) {
                this();
            }

            private Builder() {
                super(Event.DEFAULT_INSTANCE);
            }

            public List<TriggerParam> getTriggerParamsList() {
                return Collections.unmodifiableList(((Event) this.instance).getTriggerParamsList());
            }

            public int getTriggerParamsCount() {
                return ((Event) this.instance).getTriggerParamsCount();
            }

            public TriggerParam getTriggerParams(int i) {
                return ((Event) this.instance).getTriggerParams(i);
            }

            public Builder setTriggerParams(int i, TriggerParam triggerParam) {
                copyOnWrite();
                ((Event) this.instance).setTriggerParams(i, triggerParam);
                return this;
            }

            public Builder setTriggerParams(int i, TriggerParam.Builder builder) {
                copyOnWrite();
                ((Event) this.instance).setTriggerParams(i, builder);
                return this;
            }

            public Builder addTriggerParams(TriggerParam triggerParam) {
                copyOnWrite();
                ((Event) this.instance).addTriggerParams(triggerParam);
                return this;
            }

            public Builder addTriggerParams(int i, TriggerParam triggerParam) {
                copyOnWrite();
                ((Event) this.instance).addTriggerParams(i, triggerParam);
                return this;
            }

            public Builder addTriggerParams(TriggerParam.Builder builder) {
                copyOnWrite();
                ((Event) this.instance).addTriggerParams(builder);
                return this;
            }

            public Builder addTriggerParams(int i, TriggerParam.Builder builder) {
                copyOnWrite();
                ((Event) this.instance).addTriggerParams(i, builder);
                return this;
            }

            public Builder addAllTriggerParams(Iterable<? extends TriggerParam> iterable) {
                copyOnWrite();
                ((Event) this.instance).addAllTriggerParams(iterable);
                return this;
            }

            public Builder clearTriggerParams() {
                copyOnWrite();
                ((Event) this.instance).clearTriggerParams();
                return this;
            }

            public Builder removeTriggerParams(int i) {
                copyOnWrite();
                ((Event) this.instance).removeTriggerParams(i);
                return this;
            }

            public String getName() {
                return ((Event) this.instance).getName();
            }

            public ByteString getNameBytes() {
                return ((Event) this.instance).getNameBytes();
            }

            public Builder setName(String str) {
                copyOnWrite();
                ((Event) this.instance).setName(str);
                return this;
            }

            public Builder clearName() {
                copyOnWrite();
                ((Event) this.instance).clearName();
                return this;
            }

            public Builder setNameBytes(ByteString byteString) {
                copyOnWrite();
                ((Event) this.instance).setNameBytes(byteString);
                return this;
            }

            public long getTimestampMillis() {
                return ((Event) this.instance).getTimestampMillis();
            }

            public Builder setTimestampMillis(long j) {
                copyOnWrite();
                ((Event) this.instance).setTimestampMillis(j);
                return this;
            }

            public Builder clearTimestampMillis() {
                copyOnWrite();
                ((Event) this.instance).clearTimestampMillis();
                return this;
            }

            public long getPreviousTimestampMillis() {
                return ((Event) this.instance).getPreviousTimestampMillis();
            }

            public Builder setPreviousTimestampMillis(long j) {
                copyOnWrite();
                ((Event) this.instance).setPreviousTimestampMillis(j);
                return this;
            }

            public Builder clearPreviousTimestampMillis() {
                copyOnWrite();
                ((Event) this.instance).clearPreviousTimestampMillis();
                return this;
            }

            public int getCount() {
                return ((Event) this.instance).getCount();
            }

            public Builder setCount(int i) {
                copyOnWrite();
                ((Event) this.instance).setCount(i);
                return this;
            }

            public Builder clearCount() {
                copyOnWrite();
                ((Event) this.instance).clearCount();
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            boolean z = false;
            switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
                case 1:
                    return new Event();
                case 2:
                    return DEFAULT_INSTANCE;
                case 3:
                    this.triggerParams_.makeImmutable();
                    return null;
                case 4:
                    return new Builder((AnonymousClass1) null);
                case 5:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                    Event event = (Event) obj2;
                    this.triggerParams_ = visitor.visitList(this.triggerParams_, event.triggerParams_);
                    this.name_ = visitor.visitString(!this.name_.isEmpty(), this.name_, !event.name_.isEmpty(), event.name_);
                    this.timestampMillis_ = visitor.visitLong(this.timestampMillis_ != 0, this.timestampMillis_, event.timestampMillis_ != 0, event.timestampMillis_);
                    this.previousTimestampMillis_ = visitor.visitLong(this.previousTimestampMillis_ != 0, this.previousTimestampMillis_, event.previousTimestampMillis_ != 0, event.previousTimestampMillis_);
                    boolean z2 = this.count_ != 0;
                    int i = this.count_;
                    if (event.count_ != 0) {
                        z = true;
                    }
                    this.count_ = visitor.visitInt(z2, i, z, event.count_);
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                        this.bitField0_ |= event.bitField0_;
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
                                    if (!this.triggerParams_.isModifiable()) {
                                        this.triggerParams_ = GeneratedMessageLite.mutableCopy(this.triggerParams_);
                                    }
                                    this.triggerParams_.add((TriggerParam) codedInputStream.readMessage(TriggerParam.parser(), extensionRegistryLite));
                                } else if (readTag == 18) {
                                    this.name_ = codedInputStream.readStringRequireUtf8();
                                } else if (readTag == 24) {
                                    this.timestampMillis_ = codedInputStream.readInt64();
                                } else if (readTag == 32) {
                                    this.previousTimestampMillis_ = codedInputStream.readInt64();
                                } else if (readTag == 40) {
                                    this.count_ = codedInputStream.readInt32();
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
                        synchronized (Event.class) {
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
            Event event = new Event();
            DEFAULT_INSTANCE = event;
            event.makeImmutable();
        }

        public static Event getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Event> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    /* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
    public static final class TriggerParam extends GeneratedMessageLite<TriggerParam, Builder> implements TriggerParamOrBuilder {
        /* access modifiers changed from: private */
        public static final TriggerParam DEFAULT_INSTANCE;
        public static final int DOUBLE_VALUE_FIELD_NUMBER = 5;
        public static final int FLOAT_VALUE_FIELD_NUMBER = 4;
        public static final int INT_VALUE_FIELD_NUMBER = 3;
        public static final int NAME_FIELD_NUMBER = 1;
        private static volatile Parser<TriggerParam> PARSER = null;
        public static final int STRING_VALUE_FIELD_NUMBER = 2;
        private double doubleValue_;
        private float floatValue_;
        private long intValue_;
        private String name_ = "";
        private String stringValue_ = "";

        private TriggerParam() {
        }

        public String getName() {
            return this.name_;
        }

        public ByteString getNameBytes() {
            return ByteString.copyFromUtf8(this.name_);
        }

        /* access modifiers changed from: private */
        public void setName(String str) {
            if (str != null) {
                this.name_ = str;
                return;
            }
            throw null;
        }

        /* access modifiers changed from: private */
        public void clearName() {
            this.name_ = getDefaultInstance().getName();
        }

        /* access modifiers changed from: private */
        public void setNameBytes(ByteString byteString) {
            if (byteString != null) {
                checkByteStringIsUtf8(byteString);
                this.name_ = byteString.toStringUtf8();
                return;
            }
            throw null;
        }

        public String getStringValue() {
            return this.stringValue_;
        }

        public ByteString getStringValueBytes() {
            return ByteString.copyFromUtf8(this.stringValue_);
        }

        /* access modifiers changed from: private */
        public void setStringValue(String str) {
            if (str != null) {
                this.stringValue_ = str;
                return;
            }
            throw null;
        }

        /* access modifiers changed from: private */
        public void clearStringValue() {
            this.stringValue_ = getDefaultInstance().getStringValue();
        }

        /* access modifiers changed from: private */
        public void setStringValueBytes(ByteString byteString) {
            if (byteString != null) {
                checkByteStringIsUtf8(byteString);
                this.stringValue_ = byteString.toStringUtf8();
                return;
            }
            throw null;
        }

        public long getIntValue() {
            return this.intValue_;
        }

        /* access modifiers changed from: private */
        public void setIntValue(long j) {
            this.intValue_ = j;
        }

        /* access modifiers changed from: private */
        public void clearIntValue() {
            this.intValue_ = 0;
        }

        public float getFloatValue() {
            return this.floatValue_;
        }

        /* access modifiers changed from: private */
        public void setFloatValue(float f) {
            this.floatValue_ = f;
        }

        /* access modifiers changed from: private */
        public void clearFloatValue() {
            this.floatValue_ = 0.0f;
        }

        public double getDoubleValue() {
            return this.doubleValue_;
        }

        /* access modifiers changed from: private */
        public void setDoubleValue(double d) {
            this.doubleValue_ = d;
        }

        /* access modifiers changed from: private */
        public void clearDoubleValue() {
            this.doubleValue_ = FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (!this.name_.isEmpty()) {
                codedOutputStream.writeString(1, getName());
            }
            if (!this.stringValue_.isEmpty()) {
                codedOutputStream.writeString(2, getStringValue());
            }
            long j = this.intValue_;
            if (j != 0) {
                codedOutputStream.writeInt64(3, j);
            }
            float f = this.floatValue_;
            if (f != 0.0f) {
                codedOutputStream.writeFloat(4, f);
            }
            double d = this.doubleValue_;
            if (d != FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
                codedOutputStream.writeDouble(5, d);
            }
        }

        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            if (!this.name_.isEmpty()) {
                i2 = 0 + CodedOutputStream.computeStringSize(1, getName());
            }
            if (!this.stringValue_.isEmpty()) {
                i2 += CodedOutputStream.computeStringSize(2, getStringValue());
            }
            long j = this.intValue_;
            if (j != 0) {
                i2 += CodedOutputStream.computeInt64Size(3, j);
            }
            float f = this.floatValue_;
            if (f != 0.0f) {
                i2 += CodedOutputStream.computeFloatSize(4, f);
            }
            double d = this.doubleValue_;
            if (d != FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
                i2 += CodedOutputStream.computeDoubleSize(5, d);
            }
            this.memoizedSerializedSize = i2;
            return i2;
        }

        public static TriggerParam parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (TriggerParam) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static TriggerParam parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (TriggerParam) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static TriggerParam parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (TriggerParam) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static TriggerParam parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (TriggerParam) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static TriggerParam parseFrom(InputStream inputStream) throws IOException {
            return (TriggerParam) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static TriggerParam parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (TriggerParam) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static TriggerParam parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (TriggerParam) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static TriggerParam parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (TriggerParam) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static TriggerParam parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (TriggerParam) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static TriggerParam parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (TriggerParam) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(TriggerParam triggerParam) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(triggerParam);
        }

        /* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
        public static final class Builder extends GeneratedMessageLite.Builder<TriggerParam, Builder> implements TriggerParamOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 r1) {
                this();
            }

            private Builder() {
                super(TriggerParam.DEFAULT_INSTANCE);
            }

            public String getName() {
                return ((TriggerParam) this.instance).getName();
            }

            public ByteString getNameBytes() {
                return ((TriggerParam) this.instance).getNameBytes();
            }

            public Builder setName(String str) {
                copyOnWrite();
                ((TriggerParam) this.instance).setName(str);
                return this;
            }

            public Builder clearName() {
                copyOnWrite();
                ((TriggerParam) this.instance).clearName();
                return this;
            }

            public Builder setNameBytes(ByteString byteString) {
                copyOnWrite();
                ((TriggerParam) this.instance).setNameBytes(byteString);
                return this;
            }

            public String getStringValue() {
                return ((TriggerParam) this.instance).getStringValue();
            }

            public ByteString getStringValueBytes() {
                return ((TriggerParam) this.instance).getStringValueBytes();
            }

            public Builder setStringValue(String str) {
                copyOnWrite();
                ((TriggerParam) this.instance).setStringValue(str);
                return this;
            }

            public Builder clearStringValue() {
                copyOnWrite();
                ((TriggerParam) this.instance).clearStringValue();
                return this;
            }

            public Builder setStringValueBytes(ByteString byteString) {
                copyOnWrite();
                ((TriggerParam) this.instance).setStringValueBytes(byteString);
                return this;
            }

            public long getIntValue() {
                return ((TriggerParam) this.instance).getIntValue();
            }

            public Builder setIntValue(long j) {
                copyOnWrite();
                ((TriggerParam) this.instance).setIntValue(j);
                return this;
            }

            public Builder clearIntValue() {
                copyOnWrite();
                ((TriggerParam) this.instance).clearIntValue();
                return this;
            }

            public float getFloatValue() {
                return ((TriggerParam) this.instance).getFloatValue();
            }

            public Builder setFloatValue(float f) {
                copyOnWrite();
                ((TriggerParam) this.instance).setFloatValue(f);
                return this;
            }

            public Builder clearFloatValue() {
                copyOnWrite();
                ((TriggerParam) this.instance).clearFloatValue();
                return this;
            }

            public double getDoubleValue() {
                return ((TriggerParam) this.instance).getDoubleValue();
            }

            public Builder setDoubleValue(double d) {
                copyOnWrite();
                ((TriggerParam) this.instance).setDoubleValue(d);
                return this;
            }

            public Builder clearDoubleValue() {
                copyOnWrite();
                ((TriggerParam) this.instance).clearDoubleValue();
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            boolean z = false;
            switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
                case 1:
                    return new TriggerParam();
                case 2:
                    return DEFAULT_INSTANCE;
                case 3:
                    return null;
                case 4:
                    return new Builder((AnonymousClass1) null);
                case 5:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                    TriggerParam triggerParam = (TriggerParam) obj2;
                    this.name_ = visitor.visitString(!this.name_.isEmpty(), this.name_, !triggerParam.name_.isEmpty(), triggerParam.name_);
                    this.stringValue_ = visitor.visitString(!this.stringValue_.isEmpty(), this.stringValue_, !triggerParam.stringValue_.isEmpty(), triggerParam.stringValue_);
                    this.intValue_ = visitor.visitLong(this.intValue_ != 0, this.intValue_, triggerParam.intValue_ != 0, triggerParam.intValue_);
                    this.floatValue_ = visitor.visitFloat(this.floatValue_ != 0.0f, this.floatValue_, triggerParam.floatValue_ != 0.0f, triggerParam.floatValue_);
                    this.doubleValue_ = visitor.visitDouble(this.doubleValue_ != FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, this.doubleValue_, triggerParam.doubleValue_ != FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE, triggerParam.doubleValue_);
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
                                    this.name_ = codedInputStream.readStringRequireUtf8();
                                } else if (readTag == 18) {
                                    this.stringValue_ = codedInputStream.readStringRequireUtf8();
                                } else if (readTag == 24) {
                                    this.intValue_ = codedInputStream.readInt64();
                                } else if (readTag == 37) {
                                    this.floatValue_ = codedInputStream.readFloat();
                                } else if (readTag == 41) {
                                    this.doubleValue_ = codedInputStream.readDouble();
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
                        synchronized (TriggerParam.class) {
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
            TriggerParam triggerParam = new TriggerParam();
            DEFAULT_INSTANCE = triggerParam;
            triggerParam.makeImmutable();
        }

        public static TriggerParam getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<TriggerParam> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    /* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
    public static final class Priority extends GeneratedMessageLite<Priority, Builder> implements PriorityOrBuilder {
        /* access modifiers changed from: private */
        public static final Priority DEFAULT_INSTANCE;
        private static volatile Parser<Priority> PARSER = null;
        public static final int VALUE_FIELD_NUMBER = 1;
        private int value_;

        private Priority() {
        }

        public int getValue() {
            return this.value_;
        }

        /* access modifiers changed from: private */
        public void setValue(int i) {
            this.value_ = i;
        }

        /* access modifiers changed from: private */
        public void clearValue() {
            this.value_ = 0;
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            int i = this.value_;
            if (i != 0) {
                codedOutputStream.writeInt32(1, i);
            }
        }

        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            int i3 = this.value_;
            if (i3 != 0) {
                i2 = 0 + CodedOutputStream.computeInt32Size(1, i3);
            }
            this.memoizedSerializedSize = i2;
            return i2;
        }

        public static Priority parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (Priority) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static Priority parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Priority) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static Priority parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (Priority) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static Priority parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Priority) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static Priority parseFrom(InputStream inputStream) throws IOException {
            return (Priority) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static Priority parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Priority) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static Priority parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (Priority) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static Priority parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Priority) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static Priority parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (Priority) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static Priority parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Priority) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(Priority priority) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(priority);
        }

        /* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
        public static final class Builder extends GeneratedMessageLite.Builder<Priority, Builder> implements PriorityOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 r1) {
                this();
            }

            private Builder() {
                super(Priority.DEFAULT_INSTANCE);
            }

            public int getValue() {
                return ((Priority) this.instance).getValue();
            }

            public Builder setValue(int i) {
                copyOnWrite();
                ((Priority) this.instance).setValue(i);
                return this;
            }

            public Builder clearValue() {
                copyOnWrite();
                ((Priority) this.instance).clearValue();
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            boolean z = false;
            switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
                case 1:
                    return new Priority();
                case 2:
                    return DEFAULT_INSTANCE;
                case 3:
                    return null;
                case 4:
                    return new Builder((AnonymousClass1) null);
                case 5:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                    Priority priority = (Priority) obj2;
                    boolean z2 = this.value_ != 0;
                    int i = this.value_;
                    if (priority.value_ != 0) {
                        z = true;
                    }
                    this.value_ = visitor.visitInt(z2, i, z, priority.value_);
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
                                    this.value_ = codedInputStream.readInt32();
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
                        synchronized (Priority.class) {
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
            Priority priority = new Priority();
            DEFAULT_INSTANCE = priority;
            priority.makeImmutable();
        }

        public static Priority getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Priority> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    /* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
    public static final class ScionConversionEvent extends GeneratedMessageLite<ScionConversionEvent, Builder> implements ScionConversionEventOrBuilder {
        /* access modifiers changed from: private */
        public static final ScionConversionEvent DEFAULT_INSTANCE;
        public static final int NAME_FIELD_NUMBER = 1;
        private static volatile Parser<ScionConversionEvent> PARSER;
        private String name_ = "";

        private ScionConversionEvent() {
        }

        public String getName() {
            return this.name_;
        }

        public ByteString getNameBytes() {
            return ByteString.copyFromUtf8(this.name_);
        }

        /* access modifiers changed from: private */
        public void setName(String str) {
            if (str != null) {
                this.name_ = str;
                return;
            }
            throw null;
        }

        /* access modifiers changed from: private */
        public void clearName() {
            this.name_ = getDefaultInstance().getName();
        }

        /* access modifiers changed from: private */
        public void setNameBytes(ByteString byteString) {
            if (byteString != null) {
                checkByteStringIsUtf8(byteString);
                this.name_ = byteString.toStringUtf8();
                return;
            }
            throw null;
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (!this.name_.isEmpty()) {
                codedOutputStream.writeString(1, getName());
            }
        }

        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            if (!this.name_.isEmpty()) {
                i2 = 0 + CodedOutputStream.computeStringSize(1, getName());
            }
            this.memoizedSerializedSize = i2;
            return i2;
        }

        public static ScionConversionEvent parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (ScionConversionEvent) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static ScionConversionEvent parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (ScionConversionEvent) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static ScionConversionEvent parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (ScionConversionEvent) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static ScionConversionEvent parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (ScionConversionEvent) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static ScionConversionEvent parseFrom(InputStream inputStream) throws IOException {
            return (ScionConversionEvent) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static ScionConversionEvent parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ScionConversionEvent) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static ScionConversionEvent parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (ScionConversionEvent) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static ScionConversionEvent parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ScionConversionEvent) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static ScionConversionEvent parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (ScionConversionEvent) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static ScionConversionEvent parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ScionConversionEvent) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(ScionConversionEvent scionConversionEvent) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(scionConversionEvent);
        }

        /* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
        public static final class Builder extends GeneratedMessageLite.Builder<ScionConversionEvent, Builder> implements ScionConversionEventOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 r1) {
                this();
            }

            private Builder() {
                super(ScionConversionEvent.DEFAULT_INSTANCE);
            }

            public String getName() {
                return ((ScionConversionEvent) this.instance).getName();
            }

            public ByteString getNameBytes() {
                return ((ScionConversionEvent) this.instance).getNameBytes();
            }

            public Builder setName(String str) {
                copyOnWrite();
                ((ScionConversionEvent) this.instance).setName(str);
                return this;
            }

            public Builder clearName() {
                copyOnWrite();
                ((ScionConversionEvent) this.instance).clearName();
                return this;
            }

            public Builder setNameBytes(ByteString byteString) {
                copyOnWrite();
                ((ScionConversionEvent) this.instance).setNameBytes(byteString);
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
                case 1:
                    return new ScionConversionEvent();
                case 2:
                    return DEFAULT_INSTANCE;
                case 3:
                    return null;
                case 4:
                    return new Builder((AnonymousClass1) null);
                case 5:
                    ScionConversionEvent scionConversionEvent = (ScionConversionEvent) obj2;
                    this.name_ = ((GeneratedMessageLite.Visitor) obj).visitString(!this.name_.isEmpty(), this.name_, true ^ scionConversionEvent.name_.isEmpty(), scionConversionEvent.name_);
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
                                    this.name_ = codedInputStream.readStringRequireUtf8();
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
                        synchronized (ScionConversionEvent.class) {
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
            ScionConversionEvent scionConversionEvent = new ScionConversionEvent();
            DEFAULT_INSTANCE = scionConversionEvent;
            scionConversionEvent.makeImmutable();
        }

        public static ScionConversionEvent getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<ScionConversionEvent> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    /* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
    public static final class ExperimentVariant extends GeneratedMessageLite<ExperimentVariant, Builder> implements ExperimentVariantOrBuilder {
        public static final int CONTENT_FIELD_NUMBER = 2;
        /* access modifiers changed from: private */
        public static final ExperimentVariant DEFAULT_INSTANCE;
        public static final int INDEX_FIELD_NUMBER = 1;
        private static volatile Parser<ExperimentVariant> PARSER;
        private MessagesProto.Content content_;
        private int index_;

        private ExperimentVariant() {
        }

        public int getIndex() {
            return this.index_;
        }

        /* access modifiers changed from: private */
        public void setIndex(int i) {
            this.index_ = i;
        }

        /* access modifiers changed from: private */
        public void clearIndex() {
            this.index_ = 0;
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

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            int i = this.index_;
            if (i != 0) {
                codedOutputStream.writeInt32(1, i);
            }
            if (this.content_ != null) {
                codedOutputStream.writeMessage(2, getContent());
            }
        }

        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            int i3 = this.index_;
            if (i3 != 0) {
                i2 = 0 + CodedOutputStream.computeInt32Size(1, i3);
            }
            if (this.content_ != null) {
                i2 += CodedOutputStream.computeMessageSize(2, getContent());
            }
            this.memoizedSerializedSize = i2;
            return i2;
        }

        public static ExperimentVariant parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (ExperimentVariant) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static ExperimentVariant parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (ExperimentVariant) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static ExperimentVariant parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (ExperimentVariant) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static ExperimentVariant parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (ExperimentVariant) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static ExperimentVariant parseFrom(InputStream inputStream) throws IOException {
            return (ExperimentVariant) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static ExperimentVariant parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ExperimentVariant) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static ExperimentVariant parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (ExperimentVariant) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static ExperimentVariant parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ExperimentVariant) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static ExperimentVariant parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (ExperimentVariant) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static ExperimentVariant parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ExperimentVariant) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(ExperimentVariant experimentVariant) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(experimentVariant);
        }

        /* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
        public static final class Builder extends GeneratedMessageLite.Builder<ExperimentVariant, Builder> implements ExperimentVariantOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 r1) {
                this();
            }

            private Builder() {
                super(ExperimentVariant.DEFAULT_INSTANCE);
            }

            public int getIndex() {
                return ((ExperimentVariant) this.instance).getIndex();
            }

            public Builder setIndex(int i) {
                copyOnWrite();
                ((ExperimentVariant) this.instance).setIndex(i);
                return this;
            }

            public Builder clearIndex() {
                copyOnWrite();
                ((ExperimentVariant) this.instance).clearIndex();
                return this;
            }

            public boolean hasContent() {
                return ((ExperimentVariant) this.instance).hasContent();
            }

            public MessagesProto.Content getContent() {
                return ((ExperimentVariant) this.instance).getContent();
            }

            public Builder setContent(MessagesProto.Content content) {
                copyOnWrite();
                ((ExperimentVariant) this.instance).setContent(content);
                return this;
            }

            public Builder setContent(MessagesProto.Content.Builder builder) {
                copyOnWrite();
                ((ExperimentVariant) this.instance).setContent(builder);
                return this;
            }

            public Builder mergeContent(MessagesProto.Content content) {
                copyOnWrite();
                ((ExperimentVariant) this.instance).mergeContent(content);
                return this;
            }

            public Builder clearContent() {
                copyOnWrite();
                ((ExperimentVariant) this.instance).clearContent();
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            boolean z = false;
            switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
                case 1:
                    return new ExperimentVariant();
                case 2:
                    return DEFAULT_INSTANCE;
                case 3:
                    return null;
                case 4:
                    return new Builder((AnonymousClass1) null);
                case 5:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                    ExperimentVariant experimentVariant = (ExperimentVariant) obj2;
                    boolean z2 = this.index_ != 0;
                    int i = this.index_;
                    if (experimentVariant.index_ != 0) {
                        z = true;
                    }
                    this.index_ = visitor.visitInt(z2, i, z, experimentVariant.index_);
                    this.content_ = (MessagesProto.Content) visitor.visitMessage(this.content_, experimentVariant.content_);
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
                                    this.index_ = codedInputStream.readInt32();
                                } else if (readTag == 18) {
                                    MessagesProto.Content.Builder builder = this.content_ != null ? (MessagesProto.Content.Builder) this.content_.toBuilder() : null;
                                    MessagesProto.Content content = (MessagesProto.Content) codedInputStream.readMessage(MessagesProto.Content.parser(), extensionRegistryLite);
                                    this.content_ = content;
                                    if (builder != null) {
                                        builder.mergeFrom(content);
                                        this.content_ = (MessagesProto.Content) builder.buildPartial();
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
                        synchronized (ExperimentVariant.class) {
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
            ExperimentVariant experimentVariant = new ExperimentVariant();
            DEFAULT_INSTANCE = experimentVariant;
            experimentVariant.makeImmutable();
        }

        public static ExperimentVariant getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<ExperimentVariant> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    /* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
    public static final class DailyAnalyticsSummary extends GeneratedMessageLite<DailyAnalyticsSummary, Builder> implements DailyAnalyticsSummaryOrBuilder {
        public static final int CLICKS_FIELD_NUMBER = 3;
        /* access modifiers changed from: private */
        public static final DailyAnalyticsSummary DEFAULT_INSTANCE;
        public static final int ERRORS_FIELD_NUMBER = 4;
        public static final int IMPRESSIONS_FIELD_NUMBER = 2;
        private static volatile Parser<DailyAnalyticsSummary> PARSER = null;
        public static final int START_OF_DAY_MILLIS_FIELD_NUMBER = 1;
        private int clicks_;
        private int errors_;
        private int impressions_;
        private long startOfDayMillis_;

        private DailyAnalyticsSummary() {
        }

        public long getStartOfDayMillis() {
            return this.startOfDayMillis_;
        }

        /* access modifiers changed from: private */
        public void setStartOfDayMillis(long j) {
            this.startOfDayMillis_ = j;
        }

        /* access modifiers changed from: private */
        public void clearStartOfDayMillis() {
            this.startOfDayMillis_ = 0;
        }

        public int getImpressions() {
            return this.impressions_;
        }

        /* access modifiers changed from: private */
        public void setImpressions(int i) {
            this.impressions_ = i;
        }

        /* access modifiers changed from: private */
        public void clearImpressions() {
            this.impressions_ = 0;
        }

        public int getClicks() {
            return this.clicks_;
        }

        /* access modifiers changed from: private */
        public void setClicks(int i) {
            this.clicks_ = i;
        }

        /* access modifiers changed from: private */
        public void clearClicks() {
            this.clicks_ = 0;
        }

        public int getErrors() {
            return this.errors_;
        }

        /* access modifiers changed from: private */
        public void setErrors(int i) {
            this.errors_ = i;
        }

        /* access modifiers changed from: private */
        public void clearErrors() {
            this.errors_ = 0;
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            long j = this.startOfDayMillis_;
            if (j != 0) {
                codedOutputStream.writeInt64(1, j);
            }
            int i = this.impressions_;
            if (i != 0) {
                codedOutputStream.writeInt32(2, i);
            }
            int i2 = this.clicks_;
            if (i2 != 0) {
                codedOutputStream.writeInt32(3, i2);
            }
            int i3 = this.errors_;
            if (i3 != 0) {
                codedOutputStream.writeInt32(4, i3);
            }
        }

        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            long j = this.startOfDayMillis_;
            if (j != 0) {
                i2 = 0 + CodedOutputStream.computeInt64Size(1, j);
            }
            int i3 = this.impressions_;
            if (i3 != 0) {
                i2 += CodedOutputStream.computeInt32Size(2, i3);
            }
            int i4 = this.clicks_;
            if (i4 != 0) {
                i2 += CodedOutputStream.computeInt32Size(3, i4);
            }
            int i5 = this.errors_;
            if (i5 != 0) {
                i2 += CodedOutputStream.computeInt32Size(4, i5);
            }
            this.memoizedSerializedSize = i2;
            return i2;
        }

        public static DailyAnalyticsSummary parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (DailyAnalyticsSummary) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static DailyAnalyticsSummary parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (DailyAnalyticsSummary) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static DailyAnalyticsSummary parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (DailyAnalyticsSummary) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static DailyAnalyticsSummary parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (DailyAnalyticsSummary) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static DailyAnalyticsSummary parseFrom(InputStream inputStream) throws IOException {
            return (DailyAnalyticsSummary) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static DailyAnalyticsSummary parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (DailyAnalyticsSummary) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static DailyAnalyticsSummary parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (DailyAnalyticsSummary) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static DailyAnalyticsSummary parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (DailyAnalyticsSummary) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static DailyAnalyticsSummary parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (DailyAnalyticsSummary) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static DailyAnalyticsSummary parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (DailyAnalyticsSummary) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(DailyAnalyticsSummary dailyAnalyticsSummary) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(dailyAnalyticsSummary);
        }

        /* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
        public static final class Builder extends GeneratedMessageLite.Builder<DailyAnalyticsSummary, Builder> implements DailyAnalyticsSummaryOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 r1) {
                this();
            }

            private Builder() {
                super(DailyAnalyticsSummary.DEFAULT_INSTANCE);
            }

            public long getStartOfDayMillis() {
                return ((DailyAnalyticsSummary) this.instance).getStartOfDayMillis();
            }

            public Builder setStartOfDayMillis(long j) {
                copyOnWrite();
                ((DailyAnalyticsSummary) this.instance).setStartOfDayMillis(j);
                return this;
            }

            public Builder clearStartOfDayMillis() {
                copyOnWrite();
                ((DailyAnalyticsSummary) this.instance).clearStartOfDayMillis();
                return this;
            }

            public int getImpressions() {
                return ((DailyAnalyticsSummary) this.instance).getImpressions();
            }

            public Builder setImpressions(int i) {
                copyOnWrite();
                ((DailyAnalyticsSummary) this.instance).setImpressions(i);
                return this;
            }

            public Builder clearImpressions() {
                copyOnWrite();
                ((DailyAnalyticsSummary) this.instance).clearImpressions();
                return this;
            }

            public int getClicks() {
                return ((DailyAnalyticsSummary) this.instance).getClicks();
            }

            public Builder setClicks(int i) {
                copyOnWrite();
                ((DailyAnalyticsSummary) this.instance).setClicks(i);
                return this;
            }

            public Builder clearClicks() {
                copyOnWrite();
                ((DailyAnalyticsSummary) this.instance).clearClicks();
                return this;
            }

            public int getErrors() {
                return ((DailyAnalyticsSummary) this.instance).getErrors();
            }

            public Builder setErrors(int i) {
                copyOnWrite();
                ((DailyAnalyticsSummary) this.instance).setErrors(i);
                return this;
            }

            public Builder clearErrors() {
                copyOnWrite();
                ((DailyAnalyticsSummary) this.instance).clearErrors();
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            boolean z = false;
            switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
                case 1:
                    return new DailyAnalyticsSummary();
                case 2:
                    return DEFAULT_INSTANCE;
                case 3:
                    return null;
                case 4:
                    return new Builder((AnonymousClass1) null);
                case 5:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                    DailyAnalyticsSummary dailyAnalyticsSummary = (DailyAnalyticsSummary) obj2;
                    this.startOfDayMillis_ = visitor.visitLong(this.startOfDayMillis_ != 0, this.startOfDayMillis_, dailyAnalyticsSummary.startOfDayMillis_ != 0, dailyAnalyticsSummary.startOfDayMillis_);
                    this.impressions_ = visitor.visitInt(this.impressions_ != 0, this.impressions_, dailyAnalyticsSummary.impressions_ != 0, dailyAnalyticsSummary.impressions_);
                    this.clicks_ = visitor.visitInt(this.clicks_ != 0, this.clicks_, dailyAnalyticsSummary.clicks_ != 0, dailyAnalyticsSummary.clicks_);
                    boolean z2 = this.errors_ != 0;
                    int i = this.errors_;
                    if (dailyAnalyticsSummary.errors_ != 0) {
                        z = true;
                    }
                    this.errors_ = visitor.visitInt(z2, i, z, dailyAnalyticsSummary.errors_);
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
                                    this.startOfDayMillis_ = codedInputStream.readInt64();
                                } else if (readTag == 16) {
                                    this.impressions_ = codedInputStream.readInt32();
                                } else if (readTag == 24) {
                                    this.clicks_ = codedInputStream.readInt32();
                                } else if (readTag == 32) {
                                    this.errors_ = codedInputStream.readInt32();
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
                        synchronized (DailyAnalyticsSummary.class) {
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
            DailyAnalyticsSummary dailyAnalyticsSummary = new DailyAnalyticsSummary();
            DEFAULT_INSTANCE = dailyAnalyticsSummary;
            dailyAnalyticsSummary.makeImmutable();
        }

        public static DailyAnalyticsSummary getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<DailyAnalyticsSummary> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    /* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
    public static final class DailyConversionSummary extends GeneratedMessageLite<DailyConversionSummary, Builder> implements DailyConversionSummaryOrBuilder {
        public static final int CONVERSIONS_FIELD_NUMBER = 2;
        /* access modifiers changed from: private */
        public static final DailyConversionSummary DEFAULT_INSTANCE;
        private static volatile Parser<DailyConversionSummary> PARSER = null;
        public static final int START_OF_DAY_MILLIS_FIELD_NUMBER = 1;
        private int conversions_;
        private long startOfDayMillis_;

        private DailyConversionSummary() {
        }

        public long getStartOfDayMillis() {
            return this.startOfDayMillis_;
        }

        /* access modifiers changed from: private */
        public void setStartOfDayMillis(long j) {
            this.startOfDayMillis_ = j;
        }

        /* access modifiers changed from: private */
        public void clearStartOfDayMillis() {
            this.startOfDayMillis_ = 0;
        }

        public int getConversions() {
            return this.conversions_;
        }

        /* access modifiers changed from: private */
        public void setConversions(int i) {
            this.conversions_ = i;
        }

        /* access modifiers changed from: private */
        public void clearConversions() {
            this.conversions_ = 0;
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            long j = this.startOfDayMillis_;
            if (j != 0) {
                codedOutputStream.writeInt64(1, j);
            }
            int i = this.conversions_;
            if (i != 0) {
                codedOutputStream.writeInt32(2, i);
            }
        }

        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            long j = this.startOfDayMillis_;
            if (j != 0) {
                i2 = 0 + CodedOutputStream.computeInt64Size(1, j);
            }
            int i3 = this.conversions_;
            if (i3 != 0) {
                i2 += CodedOutputStream.computeInt32Size(2, i3);
            }
            this.memoizedSerializedSize = i2;
            return i2;
        }

        public static DailyConversionSummary parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (DailyConversionSummary) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static DailyConversionSummary parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (DailyConversionSummary) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static DailyConversionSummary parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (DailyConversionSummary) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static DailyConversionSummary parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (DailyConversionSummary) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static DailyConversionSummary parseFrom(InputStream inputStream) throws IOException {
            return (DailyConversionSummary) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static DailyConversionSummary parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (DailyConversionSummary) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static DailyConversionSummary parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (DailyConversionSummary) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static DailyConversionSummary parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (DailyConversionSummary) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static DailyConversionSummary parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (DailyConversionSummary) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static DailyConversionSummary parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (DailyConversionSummary) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(DailyConversionSummary dailyConversionSummary) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(dailyConversionSummary);
        }

        /* compiled from: com.google.firebase:firebase-inappmessaging@@19.0.5 */
        public static final class Builder extends GeneratedMessageLite.Builder<DailyConversionSummary, Builder> implements DailyConversionSummaryOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 r1) {
                this();
            }

            private Builder() {
                super(DailyConversionSummary.DEFAULT_INSTANCE);
            }

            public long getStartOfDayMillis() {
                return ((DailyConversionSummary) this.instance).getStartOfDayMillis();
            }

            public Builder setStartOfDayMillis(long j) {
                copyOnWrite();
                ((DailyConversionSummary) this.instance).setStartOfDayMillis(j);
                return this;
            }

            public Builder clearStartOfDayMillis() {
                copyOnWrite();
                ((DailyConversionSummary) this.instance).clearStartOfDayMillis();
                return this;
            }

            public int getConversions() {
                return ((DailyConversionSummary) this.instance).getConversions();
            }

            public Builder setConversions(int i) {
                copyOnWrite();
                ((DailyConversionSummary) this.instance).setConversions(i);
                return this;
            }

            public Builder clearConversions() {
                copyOnWrite();
                ((DailyConversionSummary) this.instance).clearConversions();
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            boolean z = false;
            switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
                case 1:
                    return new DailyConversionSummary();
                case 2:
                    return DEFAULT_INSTANCE;
                case 3:
                    return null;
                case 4:
                    return new Builder((AnonymousClass1) null);
                case 5:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                    DailyConversionSummary dailyConversionSummary = (DailyConversionSummary) obj2;
                    this.startOfDayMillis_ = visitor.visitLong(this.startOfDayMillis_ != 0, this.startOfDayMillis_, dailyConversionSummary.startOfDayMillis_ != 0, dailyConversionSummary.startOfDayMillis_);
                    boolean z2 = this.conversions_ != 0;
                    int i = this.conversions_;
                    if (dailyConversionSummary.conversions_ != 0) {
                        z = true;
                    }
                    this.conversions_ = visitor.visitInt(z2, i, z, dailyConversionSummary.conversions_);
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
                                    this.startOfDayMillis_ = codedInputStream.readInt64();
                                } else if (readTag == 16) {
                                    this.conversions_ = codedInputStream.readInt32();
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
                        synchronized (DailyConversionSummary.class) {
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
            DailyConversionSummary dailyConversionSummary = new DailyConversionSummary();
            DEFAULT_INSTANCE = dailyConversionSummary;
            dailyConversionSummary.makeImmutable();
        }

        public static DailyConversionSummary getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<DailyConversionSummary> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }
}
