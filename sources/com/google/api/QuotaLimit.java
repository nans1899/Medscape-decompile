package com.google.api;

import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MapEntryLite;
import com.google.protobuf.MapFieldLite;
import com.google.protobuf.Parser;
import com.google.protobuf.WireFormat;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.Map;

/* compiled from: com.google.firebase:protolite-well-known-types@@16.0.1 */
public final class QuotaLimit extends GeneratedMessageLite<QuotaLimit, Builder> implements QuotaLimitOrBuilder {
    /* access modifiers changed from: private */
    public static final QuotaLimit DEFAULT_INSTANCE;
    public static final int DEFAULT_LIMIT_FIELD_NUMBER = 3;
    public static final int DESCRIPTION_FIELD_NUMBER = 2;
    public static final int DISPLAY_NAME_FIELD_NUMBER = 12;
    public static final int DURATION_FIELD_NUMBER = 5;
    public static final int FREE_TIER_FIELD_NUMBER = 7;
    public static final int MAX_LIMIT_FIELD_NUMBER = 4;
    public static final int METRIC_FIELD_NUMBER = 8;
    public static final int NAME_FIELD_NUMBER = 6;
    private static volatile Parser<QuotaLimit> PARSER = null;
    public static final int UNIT_FIELD_NUMBER = 9;
    public static final int VALUES_FIELD_NUMBER = 10;
    private int bitField0_;
    private long defaultLimit_;
    private String description_ = "";
    private String displayName_ = "";
    private String duration_ = "";
    private long freeTier_;
    private long maxLimit_;
    private String metric_ = "";
    private String name_ = "";
    private String unit_ = "";
    private MapFieldLite<String, Long> values_ = MapFieldLite.emptyMapField();

    private QuotaLimit() {
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

    public String getDescription() {
        return this.description_;
    }

    public ByteString getDescriptionBytes() {
        return ByteString.copyFromUtf8(this.description_);
    }

    /* access modifiers changed from: private */
    public void setDescription(String str) {
        if (str != null) {
            this.description_ = str;
            return;
        }
        throw null;
    }

    /* access modifiers changed from: private */
    public void clearDescription() {
        this.description_ = getDefaultInstance().getDescription();
    }

    /* access modifiers changed from: private */
    public void setDescriptionBytes(ByteString byteString) {
        if (byteString != null) {
            checkByteStringIsUtf8(byteString);
            this.description_ = byteString.toStringUtf8();
            return;
        }
        throw null;
    }

    public long getDefaultLimit() {
        return this.defaultLimit_;
    }

    /* access modifiers changed from: private */
    public void setDefaultLimit(long j) {
        this.defaultLimit_ = j;
    }

    /* access modifiers changed from: private */
    public void clearDefaultLimit() {
        this.defaultLimit_ = 0;
    }

    public long getMaxLimit() {
        return this.maxLimit_;
    }

    /* access modifiers changed from: private */
    public void setMaxLimit(long j) {
        this.maxLimit_ = j;
    }

    /* access modifiers changed from: private */
    public void clearMaxLimit() {
        this.maxLimit_ = 0;
    }

    public long getFreeTier() {
        return this.freeTier_;
    }

    /* access modifiers changed from: private */
    public void setFreeTier(long j) {
        this.freeTier_ = j;
    }

    /* access modifiers changed from: private */
    public void clearFreeTier() {
        this.freeTier_ = 0;
    }

    public String getDuration() {
        return this.duration_;
    }

    public ByteString getDurationBytes() {
        return ByteString.copyFromUtf8(this.duration_);
    }

    /* access modifiers changed from: private */
    public void setDuration(String str) {
        if (str != null) {
            this.duration_ = str;
            return;
        }
        throw null;
    }

    /* access modifiers changed from: private */
    public void clearDuration() {
        this.duration_ = getDefaultInstance().getDuration();
    }

    /* access modifiers changed from: private */
    public void setDurationBytes(ByteString byteString) {
        if (byteString != null) {
            checkByteStringIsUtf8(byteString);
            this.duration_ = byteString.toStringUtf8();
            return;
        }
        throw null;
    }

    public String getMetric() {
        return this.metric_;
    }

    public ByteString getMetricBytes() {
        return ByteString.copyFromUtf8(this.metric_);
    }

    /* access modifiers changed from: private */
    public void setMetric(String str) {
        if (str != null) {
            this.metric_ = str;
            return;
        }
        throw null;
    }

    /* access modifiers changed from: private */
    public void clearMetric() {
        this.metric_ = getDefaultInstance().getMetric();
    }

    /* access modifiers changed from: private */
    public void setMetricBytes(ByteString byteString) {
        if (byteString != null) {
            checkByteStringIsUtf8(byteString);
            this.metric_ = byteString.toStringUtf8();
            return;
        }
        throw null;
    }

    public String getUnit() {
        return this.unit_;
    }

    public ByteString getUnitBytes() {
        return ByteString.copyFromUtf8(this.unit_);
    }

    /* access modifiers changed from: private */
    public void setUnit(String str) {
        if (str != null) {
            this.unit_ = str;
            return;
        }
        throw null;
    }

    /* access modifiers changed from: private */
    public void clearUnit() {
        this.unit_ = getDefaultInstance().getUnit();
    }

    /* access modifiers changed from: private */
    public void setUnitBytes(ByteString byteString) {
        if (byteString != null) {
            checkByteStringIsUtf8(byteString);
            this.unit_ = byteString.toStringUtf8();
            return;
        }
        throw null;
    }

    /* compiled from: com.google.firebase:protolite-well-known-types@@16.0.1 */
    private static final class ValuesDefaultEntryHolder {
        static final MapEntryLite<String, Long> defaultEntry = MapEntryLite.newDefaultInstance(WireFormat.FieldType.STRING, "", WireFormat.FieldType.INT64, 0L);

        private ValuesDefaultEntryHolder() {
        }
    }

    private MapFieldLite<String, Long> internalGetValues() {
        return this.values_;
    }

    private MapFieldLite<String, Long> internalGetMutableValues() {
        if (!this.values_.isMutable()) {
            this.values_ = this.values_.mutableCopy();
        }
        return this.values_;
    }

    public int getValuesCount() {
        return internalGetValues().size();
    }

    public boolean containsValues(String str) {
        if (str != null) {
            return internalGetValues().containsKey(str);
        }
        throw null;
    }

    @Deprecated
    public Map<String, Long> getValues() {
        return getValuesMap();
    }

    public Map<String, Long> getValuesMap() {
        return Collections.unmodifiableMap(internalGetValues());
    }

    public long getValuesOrDefault(String str, long j) {
        if (str != null) {
            MapFieldLite<String, Long> internalGetValues = internalGetValues();
            return internalGetValues.containsKey(str) ? internalGetValues.get(str).longValue() : j;
        }
        throw null;
    }

    public long getValuesOrThrow(String str) {
        if (str != null) {
            MapFieldLite<String, Long> internalGetValues = internalGetValues();
            if (internalGetValues.containsKey(str)) {
                return internalGetValues.get(str).longValue();
            }
            throw new IllegalArgumentException();
        }
        throw null;
    }

    /* access modifiers changed from: private */
    public Map<String, Long> getMutableValuesMap() {
        return internalGetMutableValues();
    }

    public String getDisplayName() {
        return this.displayName_;
    }

    public ByteString getDisplayNameBytes() {
        return ByteString.copyFromUtf8(this.displayName_);
    }

    /* access modifiers changed from: private */
    public void setDisplayName(String str) {
        if (str != null) {
            this.displayName_ = str;
            return;
        }
        throw null;
    }

    /* access modifiers changed from: private */
    public void clearDisplayName() {
        this.displayName_ = getDefaultInstance().getDisplayName();
    }

    /* access modifiers changed from: private */
    public void setDisplayNameBytes(ByteString byteString) {
        if (byteString != null) {
            checkByteStringIsUtf8(byteString);
            this.displayName_ = byteString.toStringUtf8();
            return;
        }
        throw null;
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (!this.description_.isEmpty()) {
            codedOutputStream.writeString(2, getDescription());
        }
        long j = this.defaultLimit_;
        if (j != 0) {
            codedOutputStream.writeInt64(3, j);
        }
        long j2 = this.maxLimit_;
        if (j2 != 0) {
            codedOutputStream.writeInt64(4, j2);
        }
        if (!this.duration_.isEmpty()) {
            codedOutputStream.writeString(5, getDuration());
        }
        if (!this.name_.isEmpty()) {
            codedOutputStream.writeString(6, getName());
        }
        long j3 = this.freeTier_;
        if (j3 != 0) {
            codedOutputStream.writeInt64(7, j3);
        }
        if (!this.metric_.isEmpty()) {
            codedOutputStream.writeString(8, getMetric());
        }
        if (!this.unit_.isEmpty()) {
            codedOutputStream.writeString(9, getUnit());
        }
        for (Map.Entry next : internalGetValues().entrySet()) {
            ValuesDefaultEntryHolder.defaultEntry.serializeTo(codedOutputStream, 10, (String) next.getKey(), (Long) next.getValue());
        }
        if (!this.displayName_.isEmpty()) {
            codedOutputStream.writeString(12, getDisplayName());
        }
    }

    public int getSerializedSize() {
        int i = this.memoizedSerializedSize;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        if (!this.description_.isEmpty()) {
            i2 = 0 + CodedOutputStream.computeStringSize(2, getDescription());
        }
        long j = this.defaultLimit_;
        if (j != 0) {
            i2 += CodedOutputStream.computeInt64Size(3, j);
        }
        long j2 = this.maxLimit_;
        if (j2 != 0) {
            i2 += CodedOutputStream.computeInt64Size(4, j2);
        }
        if (!this.duration_.isEmpty()) {
            i2 += CodedOutputStream.computeStringSize(5, getDuration());
        }
        if (!this.name_.isEmpty()) {
            i2 += CodedOutputStream.computeStringSize(6, getName());
        }
        long j3 = this.freeTier_;
        if (j3 != 0) {
            i2 += CodedOutputStream.computeInt64Size(7, j3);
        }
        if (!this.metric_.isEmpty()) {
            i2 += CodedOutputStream.computeStringSize(8, getMetric());
        }
        if (!this.unit_.isEmpty()) {
            i2 += CodedOutputStream.computeStringSize(9, getUnit());
        }
        for (Map.Entry next : internalGetValues().entrySet()) {
            i2 += ValuesDefaultEntryHolder.defaultEntry.computeMessageSize(10, (String) next.getKey(), (Long) next.getValue());
        }
        if (!this.displayName_.isEmpty()) {
            i2 += CodedOutputStream.computeStringSize(12, getDisplayName());
        }
        this.memoizedSerializedSize = i2;
        return i2;
    }

    public static QuotaLimit parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (QuotaLimit) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static QuotaLimit parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (QuotaLimit) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static QuotaLimit parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (QuotaLimit) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static QuotaLimit parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (QuotaLimit) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static QuotaLimit parseFrom(InputStream inputStream) throws IOException {
        return (QuotaLimit) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static QuotaLimit parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (QuotaLimit) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static QuotaLimit parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (QuotaLimit) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static QuotaLimit parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (QuotaLimit) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static QuotaLimit parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (QuotaLimit) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static QuotaLimit parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (QuotaLimit) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(QuotaLimit quotaLimit) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(quotaLimit);
    }

    /* compiled from: com.google.firebase:protolite-well-known-types@@16.0.1 */
    public static final class Builder extends GeneratedMessageLite.Builder<QuotaLimit, Builder> implements QuotaLimitOrBuilder {
        /* synthetic */ Builder(AnonymousClass1 r1) {
            this();
        }

        private Builder() {
            super(QuotaLimit.DEFAULT_INSTANCE);
        }

        public String getName() {
            return ((QuotaLimit) this.instance).getName();
        }

        public ByteString getNameBytes() {
            return ((QuotaLimit) this.instance).getNameBytes();
        }

        public Builder setName(String str) {
            copyOnWrite();
            ((QuotaLimit) this.instance).setName(str);
            return this;
        }

        public Builder clearName() {
            copyOnWrite();
            ((QuotaLimit) this.instance).clearName();
            return this;
        }

        public Builder setNameBytes(ByteString byteString) {
            copyOnWrite();
            ((QuotaLimit) this.instance).setNameBytes(byteString);
            return this;
        }

        public String getDescription() {
            return ((QuotaLimit) this.instance).getDescription();
        }

        public ByteString getDescriptionBytes() {
            return ((QuotaLimit) this.instance).getDescriptionBytes();
        }

        public Builder setDescription(String str) {
            copyOnWrite();
            ((QuotaLimit) this.instance).setDescription(str);
            return this;
        }

        public Builder clearDescription() {
            copyOnWrite();
            ((QuotaLimit) this.instance).clearDescription();
            return this;
        }

        public Builder setDescriptionBytes(ByteString byteString) {
            copyOnWrite();
            ((QuotaLimit) this.instance).setDescriptionBytes(byteString);
            return this;
        }

        public long getDefaultLimit() {
            return ((QuotaLimit) this.instance).getDefaultLimit();
        }

        public Builder setDefaultLimit(long j) {
            copyOnWrite();
            ((QuotaLimit) this.instance).setDefaultLimit(j);
            return this;
        }

        public Builder clearDefaultLimit() {
            copyOnWrite();
            ((QuotaLimit) this.instance).clearDefaultLimit();
            return this;
        }

        public long getMaxLimit() {
            return ((QuotaLimit) this.instance).getMaxLimit();
        }

        public Builder setMaxLimit(long j) {
            copyOnWrite();
            ((QuotaLimit) this.instance).setMaxLimit(j);
            return this;
        }

        public Builder clearMaxLimit() {
            copyOnWrite();
            ((QuotaLimit) this.instance).clearMaxLimit();
            return this;
        }

        public long getFreeTier() {
            return ((QuotaLimit) this.instance).getFreeTier();
        }

        public Builder setFreeTier(long j) {
            copyOnWrite();
            ((QuotaLimit) this.instance).setFreeTier(j);
            return this;
        }

        public Builder clearFreeTier() {
            copyOnWrite();
            ((QuotaLimit) this.instance).clearFreeTier();
            return this;
        }

        public String getDuration() {
            return ((QuotaLimit) this.instance).getDuration();
        }

        public ByteString getDurationBytes() {
            return ((QuotaLimit) this.instance).getDurationBytes();
        }

        public Builder setDuration(String str) {
            copyOnWrite();
            ((QuotaLimit) this.instance).setDuration(str);
            return this;
        }

        public Builder clearDuration() {
            copyOnWrite();
            ((QuotaLimit) this.instance).clearDuration();
            return this;
        }

        public Builder setDurationBytes(ByteString byteString) {
            copyOnWrite();
            ((QuotaLimit) this.instance).setDurationBytes(byteString);
            return this;
        }

        public String getMetric() {
            return ((QuotaLimit) this.instance).getMetric();
        }

        public ByteString getMetricBytes() {
            return ((QuotaLimit) this.instance).getMetricBytes();
        }

        public Builder setMetric(String str) {
            copyOnWrite();
            ((QuotaLimit) this.instance).setMetric(str);
            return this;
        }

        public Builder clearMetric() {
            copyOnWrite();
            ((QuotaLimit) this.instance).clearMetric();
            return this;
        }

        public Builder setMetricBytes(ByteString byteString) {
            copyOnWrite();
            ((QuotaLimit) this.instance).setMetricBytes(byteString);
            return this;
        }

        public String getUnit() {
            return ((QuotaLimit) this.instance).getUnit();
        }

        public ByteString getUnitBytes() {
            return ((QuotaLimit) this.instance).getUnitBytes();
        }

        public Builder setUnit(String str) {
            copyOnWrite();
            ((QuotaLimit) this.instance).setUnit(str);
            return this;
        }

        public Builder clearUnit() {
            copyOnWrite();
            ((QuotaLimit) this.instance).clearUnit();
            return this;
        }

        public Builder setUnitBytes(ByteString byteString) {
            copyOnWrite();
            ((QuotaLimit) this.instance).setUnitBytes(byteString);
            return this;
        }

        public int getValuesCount() {
            return ((QuotaLimit) this.instance).getValuesMap().size();
        }

        public boolean containsValues(String str) {
            if (str != null) {
                return ((QuotaLimit) this.instance).getValuesMap().containsKey(str);
            }
            throw null;
        }

        public Builder clearValues() {
            copyOnWrite();
            ((QuotaLimit) this.instance).getMutableValuesMap().clear();
            return this;
        }

        public Builder removeValues(String str) {
            if (str != null) {
                copyOnWrite();
                ((QuotaLimit) this.instance).getMutableValuesMap().remove(str);
                return this;
            }
            throw null;
        }

        @Deprecated
        public Map<String, Long> getValues() {
            return getValuesMap();
        }

        public Map<String, Long> getValuesMap() {
            return Collections.unmodifiableMap(((QuotaLimit) this.instance).getValuesMap());
        }

        public long getValuesOrDefault(String str, long j) {
            if (str != null) {
                Map<String, Long> valuesMap = ((QuotaLimit) this.instance).getValuesMap();
                return valuesMap.containsKey(str) ? valuesMap.get(str).longValue() : j;
            }
            throw null;
        }

        public long getValuesOrThrow(String str) {
            if (str != null) {
                Map<String, Long> valuesMap = ((QuotaLimit) this.instance).getValuesMap();
                if (valuesMap.containsKey(str)) {
                    return valuesMap.get(str).longValue();
                }
                throw new IllegalArgumentException();
            }
            throw null;
        }

        public Builder putValues(String str, long j) {
            if (str != null) {
                copyOnWrite();
                ((QuotaLimit) this.instance).getMutableValuesMap().put(str, Long.valueOf(j));
                return this;
            }
            throw null;
        }

        public Builder putAllValues(Map<String, Long> map) {
            copyOnWrite();
            ((QuotaLimit) this.instance).getMutableValuesMap().putAll(map);
            return this;
        }

        public String getDisplayName() {
            return ((QuotaLimit) this.instance).getDisplayName();
        }

        public ByteString getDisplayNameBytes() {
            return ((QuotaLimit) this.instance).getDisplayNameBytes();
        }

        public Builder setDisplayName(String str) {
            copyOnWrite();
            ((QuotaLimit) this.instance).setDisplayName(str);
            return this;
        }

        public Builder clearDisplayName() {
            copyOnWrite();
            ((QuotaLimit) this.instance).clearDisplayName();
            return this;
        }

        public Builder setDisplayNameBytes(ByteString byteString) {
            copyOnWrite();
            ((QuotaLimit) this.instance).setDisplayNameBytes(byteString);
            return this;
        }
    }

    /* renamed from: com.google.api.QuotaLimit$1  reason: invalid class name */
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.api.QuotaLimit.AnonymousClass1.<clinit>():void");
        }
    }

    /* access modifiers changed from: protected */
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        boolean z = false;
        switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
            case 1:
                return new QuotaLimit();
            case 2:
                return DEFAULT_INSTANCE;
            case 3:
                this.values_.makeImmutable();
                return null;
            case 4:
                return new Builder((AnonymousClass1) null);
            case 5:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                QuotaLimit quotaLimit = (QuotaLimit) obj2;
                this.name_ = visitor.visitString(!this.name_.isEmpty(), this.name_, !quotaLimit.name_.isEmpty(), quotaLimit.name_);
                this.description_ = visitor.visitString(!this.description_.isEmpty(), this.description_, !quotaLimit.description_.isEmpty(), quotaLimit.description_);
                this.defaultLimit_ = visitor.visitLong(this.defaultLimit_ != 0, this.defaultLimit_, quotaLimit.defaultLimit_ != 0, quotaLimit.defaultLimit_);
                this.maxLimit_ = visitor.visitLong(this.maxLimit_ != 0, this.maxLimit_, quotaLimit.maxLimit_ != 0, quotaLimit.maxLimit_);
                this.freeTier_ = visitor.visitLong(this.freeTier_ != 0, this.freeTier_, quotaLimit.freeTier_ != 0, quotaLimit.freeTier_);
                this.duration_ = visitor.visitString(!this.duration_.isEmpty(), this.duration_, !quotaLimit.duration_.isEmpty(), quotaLimit.duration_);
                this.metric_ = visitor.visitString(!this.metric_.isEmpty(), this.metric_, !quotaLimit.metric_.isEmpty(), quotaLimit.metric_);
                this.unit_ = visitor.visitString(!this.unit_.isEmpty(), this.unit_, !quotaLimit.unit_.isEmpty(), quotaLimit.unit_);
                this.values_ = visitor.visitMap(this.values_, quotaLimit.internalGetValues());
                this.displayName_ = visitor.visitString(!this.displayName_.isEmpty(), this.displayName_, !quotaLimit.displayName_.isEmpty(), quotaLimit.displayName_);
                if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                    this.bitField0_ |= quotaLimit.bitField0_;
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
                            case 18:
                                this.description_ = codedInputStream.readStringRequireUtf8();
                                break;
                            case 24:
                                this.defaultLimit_ = codedInputStream.readInt64();
                                break;
                            case 32:
                                this.maxLimit_ = codedInputStream.readInt64();
                                break;
                            case 42:
                                this.duration_ = codedInputStream.readStringRequireUtf8();
                                break;
                            case 50:
                                this.name_ = codedInputStream.readStringRequireUtf8();
                                break;
                            case 56:
                                this.freeTier_ = codedInputStream.readInt64();
                                break;
                            case 66:
                                this.metric_ = codedInputStream.readStringRequireUtf8();
                                break;
                            case 74:
                                this.unit_ = codedInputStream.readStringRequireUtf8();
                                break;
                            case 82:
                                if (!this.values_.isMutable()) {
                                    this.values_ = this.values_.mutableCopy();
                                }
                                ValuesDefaultEntryHolder.defaultEntry.parseInto(this.values_, codedInputStream, extensionRegistryLite);
                                break;
                            case 98:
                                this.displayName_ = codedInputStream.readStringRequireUtf8();
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
                    synchronized (QuotaLimit.class) {
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
        QuotaLimit quotaLimit = new QuotaLimit();
        DEFAULT_INSTANCE = quotaLimit;
        quotaLimit.makeImmutable();
    }

    public static QuotaLimit getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<QuotaLimit> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
