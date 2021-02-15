package com.google.api;

import com.google.api.LabelDescriptor;
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
public final class MetricDescriptor extends GeneratedMessageLite<MetricDescriptor, Builder> implements MetricDescriptorOrBuilder {
    /* access modifiers changed from: private */
    public static final MetricDescriptor DEFAULT_INSTANCE;
    public static final int DESCRIPTION_FIELD_NUMBER = 6;
    public static final int DISPLAY_NAME_FIELD_NUMBER = 7;
    public static final int LABELS_FIELD_NUMBER = 2;
    public static final int METRIC_KIND_FIELD_NUMBER = 3;
    public static final int NAME_FIELD_NUMBER = 1;
    private static volatile Parser<MetricDescriptor> PARSER = null;
    public static final int TYPE_FIELD_NUMBER = 8;
    public static final int UNIT_FIELD_NUMBER = 5;
    public static final int VALUE_TYPE_FIELD_NUMBER = 4;
    private int bitField0_;
    private String description_ = "";
    private String displayName_ = "";
    private Internal.ProtobufList<LabelDescriptor> labels_ = emptyProtobufList();
    private int metricKind_;
    private String name_ = "";
    private String type_ = "";
    private String unit_ = "";
    private int valueType_;

    private MetricDescriptor() {
    }

    /* compiled from: com.google.firebase:protolite-well-known-types@@16.0.1 */
    public enum MetricKind implements Internal.EnumLite {
        METRIC_KIND_UNSPECIFIED(0),
        GAUGE(1),
        DELTA(2),
        CUMULATIVE(3),
        UNRECOGNIZED(-1);
        
        public static final int CUMULATIVE_VALUE = 3;
        public static final int DELTA_VALUE = 2;
        public static final int GAUGE_VALUE = 1;
        public static final int METRIC_KIND_UNSPECIFIED_VALUE = 0;
        private static final Internal.EnumLiteMap<MetricKind> internalValueMap = null;
        private final int value;

        static {
            internalValueMap = new Internal.EnumLiteMap<MetricKind>() {
                public MetricKind findValueByNumber(int i) {
                    return MetricKind.forNumber(i);
                }
            };
        }

        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static MetricKind valueOf(int i) {
            return forNumber(i);
        }

        public static MetricKind forNumber(int i) {
            if (i == 0) {
                return METRIC_KIND_UNSPECIFIED;
            }
            if (i == 1) {
                return GAUGE;
            }
            if (i == 2) {
                return DELTA;
            }
            if (i != 3) {
                return null;
            }
            return CUMULATIVE;
        }

        public static Internal.EnumLiteMap<MetricKind> internalGetValueMap() {
            return internalValueMap;
        }

        private MetricKind(int i) {
            this.value = i;
        }
    }

    /* compiled from: com.google.firebase:protolite-well-known-types@@16.0.1 */
    public enum ValueType implements Internal.EnumLite {
        VALUE_TYPE_UNSPECIFIED(0),
        BOOL(1),
        INT64(2),
        DOUBLE(3),
        STRING(4),
        DISTRIBUTION(5),
        MONEY(6),
        UNRECOGNIZED(-1);
        
        public static final int BOOL_VALUE = 1;
        public static final int DISTRIBUTION_VALUE = 5;
        public static final int DOUBLE_VALUE = 3;
        public static final int INT64_VALUE = 2;
        public static final int MONEY_VALUE = 6;
        public static final int STRING_VALUE = 4;
        public static final int VALUE_TYPE_UNSPECIFIED_VALUE = 0;
        private static final Internal.EnumLiteMap<ValueType> internalValueMap = null;
        private final int value;

        static {
            internalValueMap = new Internal.EnumLiteMap<ValueType>() {
                public ValueType findValueByNumber(int i) {
                    return ValueType.forNumber(i);
                }
            };
        }

        public final int getNumber() {
            return this.value;
        }

        @Deprecated
        public static ValueType valueOf(int i) {
            return forNumber(i);
        }

        public static ValueType forNumber(int i) {
            switch (i) {
                case 0:
                    return VALUE_TYPE_UNSPECIFIED;
                case 1:
                    return BOOL;
                case 2:
                    return INT64;
                case 3:
                    return DOUBLE;
                case 4:
                    return STRING;
                case 5:
                    return DISTRIBUTION;
                case 6:
                    return MONEY;
                default:
                    return null;
            }
        }

        public static Internal.EnumLiteMap<ValueType> internalGetValueMap() {
            return internalValueMap;
        }

        private ValueType(int i) {
            this.value = i;
        }
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

    public String getType() {
        return this.type_;
    }

    public ByteString getTypeBytes() {
        return ByteString.copyFromUtf8(this.type_);
    }

    /* access modifiers changed from: private */
    public void setType(String str) {
        if (str != null) {
            this.type_ = str;
            return;
        }
        throw null;
    }

    /* access modifiers changed from: private */
    public void clearType() {
        this.type_ = getDefaultInstance().getType();
    }

    /* access modifiers changed from: private */
    public void setTypeBytes(ByteString byteString) {
        if (byteString != null) {
            checkByteStringIsUtf8(byteString);
            this.type_ = byteString.toStringUtf8();
            return;
        }
        throw null;
    }

    public List<LabelDescriptor> getLabelsList() {
        return this.labels_;
    }

    public List<? extends LabelDescriptorOrBuilder> getLabelsOrBuilderList() {
        return this.labels_;
    }

    public int getLabelsCount() {
        return this.labels_.size();
    }

    public LabelDescriptor getLabels(int i) {
        return (LabelDescriptor) this.labels_.get(i);
    }

    public LabelDescriptorOrBuilder getLabelsOrBuilder(int i) {
        return (LabelDescriptorOrBuilder) this.labels_.get(i);
    }

    private void ensureLabelsIsMutable() {
        if (!this.labels_.isModifiable()) {
            this.labels_ = GeneratedMessageLite.mutableCopy(this.labels_);
        }
    }

    /* access modifiers changed from: private */
    public void setLabels(int i, LabelDescriptor labelDescriptor) {
        if (labelDescriptor != null) {
            ensureLabelsIsMutable();
            this.labels_.set(i, labelDescriptor);
            return;
        }
        throw null;
    }

    /* access modifiers changed from: private */
    public void setLabels(int i, LabelDescriptor.Builder builder) {
        ensureLabelsIsMutable();
        this.labels_.set(i, (LabelDescriptor) builder.build());
    }

    /* access modifiers changed from: private */
    public void addLabels(LabelDescriptor labelDescriptor) {
        if (labelDescriptor != null) {
            ensureLabelsIsMutable();
            this.labels_.add(labelDescriptor);
            return;
        }
        throw null;
    }

    /* access modifiers changed from: private */
    public void addLabels(int i, LabelDescriptor labelDescriptor) {
        if (labelDescriptor != null) {
            ensureLabelsIsMutable();
            this.labels_.add(i, labelDescriptor);
            return;
        }
        throw null;
    }

    /* access modifiers changed from: private */
    public void addLabels(LabelDescriptor.Builder builder) {
        ensureLabelsIsMutable();
        this.labels_.add((LabelDescriptor) builder.build());
    }

    /* access modifiers changed from: private */
    public void addLabels(int i, LabelDescriptor.Builder builder) {
        ensureLabelsIsMutable();
        this.labels_.add(i, (LabelDescriptor) builder.build());
    }

    /* access modifiers changed from: private */
    public void addAllLabels(Iterable<? extends LabelDescriptor> iterable) {
        ensureLabelsIsMutable();
        AbstractMessageLite.addAll(iterable, this.labels_);
    }

    /* access modifiers changed from: private */
    public void clearLabels() {
        this.labels_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    public void removeLabels(int i) {
        ensureLabelsIsMutable();
        this.labels_.remove(i);
    }

    public int getMetricKindValue() {
        return this.metricKind_;
    }

    public MetricKind getMetricKind() {
        MetricKind forNumber = MetricKind.forNumber(this.metricKind_);
        return forNumber == null ? MetricKind.UNRECOGNIZED : forNumber;
    }

    /* access modifiers changed from: private */
    public void setMetricKindValue(int i) {
        this.metricKind_ = i;
    }

    /* access modifiers changed from: private */
    public void setMetricKind(MetricKind metricKind) {
        if (metricKind != null) {
            this.metricKind_ = metricKind.getNumber();
            return;
        }
        throw null;
    }

    /* access modifiers changed from: private */
    public void clearMetricKind() {
        this.metricKind_ = 0;
    }

    public int getValueTypeValue() {
        return this.valueType_;
    }

    public ValueType getValueType() {
        ValueType forNumber = ValueType.forNumber(this.valueType_);
        return forNumber == null ? ValueType.UNRECOGNIZED : forNumber;
    }

    /* access modifiers changed from: private */
    public void setValueTypeValue(int i) {
        this.valueType_ = i;
    }

    /* access modifiers changed from: private */
    public void setValueType(ValueType valueType) {
        if (valueType != null) {
            this.valueType_ = valueType.getNumber();
            return;
        }
        throw null;
    }

    /* access modifiers changed from: private */
    public void clearValueType() {
        this.valueType_ = 0;
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
        if (!this.name_.isEmpty()) {
            codedOutputStream.writeString(1, getName());
        }
        for (int i = 0; i < this.labels_.size(); i++) {
            codedOutputStream.writeMessage(2, (MessageLite) this.labels_.get(i));
        }
        if (this.metricKind_ != MetricKind.METRIC_KIND_UNSPECIFIED.getNumber()) {
            codedOutputStream.writeEnum(3, this.metricKind_);
        }
        if (this.valueType_ != ValueType.VALUE_TYPE_UNSPECIFIED.getNumber()) {
            codedOutputStream.writeEnum(4, this.valueType_);
        }
        if (!this.unit_.isEmpty()) {
            codedOutputStream.writeString(5, getUnit());
        }
        if (!this.description_.isEmpty()) {
            codedOutputStream.writeString(6, getDescription());
        }
        if (!this.displayName_.isEmpty()) {
            codedOutputStream.writeString(7, getDisplayName());
        }
        if (!this.type_.isEmpty()) {
            codedOutputStream.writeString(8, getType());
        }
    }

    public int getSerializedSize() {
        int i = this.memoizedSerializedSize;
        if (i != -1) {
            return i;
        }
        int computeStringSize = !this.name_.isEmpty() ? CodedOutputStream.computeStringSize(1, getName()) + 0 : 0;
        for (int i2 = 0; i2 < this.labels_.size(); i2++) {
            computeStringSize += CodedOutputStream.computeMessageSize(2, (MessageLite) this.labels_.get(i2));
        }
        if (this.metricKind_ != MetricKind.METRIC_KIND_UNSPECIFIED.getNumber()) {
            computeStringSize += CodedOutputStream.computeEnumSize(3, this.metricKind_);
        }
        if (this.valueType_ != ValueType.VALUE_TYPE_UNSPECIFIED.getNumber()) {
            computeStringSize += CodedOutputStream.computeEnumSize(4, this.valueType_);
        }
        if (!this.unit_.isEmpty()) {
            computeStringSize += CodedOutputStream.computeStringSize(5, getUnit());
        }
        if (!this.description_.isEmpty()) {
            computeStringSize += CodedOutputStream.computeStringSize(6, getDescription());
        }
        if (!this.displayName_.isEmpty()) {
            computeStringSize += CodedOutputStream.computeStringSize(7, getDisplayName());
        }
        if (!this.type_.isEmpty()) {
            computeStringSize += CodedOutputStream.computeStringSize(8, getType());
        }
        this.memoizedSerializedSize = computeStringSize;
        return computeStringSize;
    }

    public static MetricDescriptor parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (MetricDescriptor) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static MetricDescriptor parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (MetricDescriptor) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static MetricDescriptor parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (MetricDescriptor) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static MetricDescriptor parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (MetricDescriptor) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static MetricDescriptor parseFrom(InputStream inputStream) throws IOException {
        return (MetricDescriptor) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static MetricDescriptor parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (MetricDescriptor) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static MetricDescriptor parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (MetricDescriptor) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static MetricDescriptor parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (MetricDescriptor) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static MetricDescriptor parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (MetricDescriptor) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static MetricDescriptor parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (MetricDescriptor) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(MetricDescriptor metricDescriptor) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(metricDescriptor);
    }

    /* compiled from: com.google.firebase:protolite-well-known-types@@16.0.1 */
    public static final class Builder extends GeneratedMessageLite.Builder<MetricDescriptor, Builder> implements MetricDescriptorOrBuilder {
        /* synthetic */ Builder(AnonymousClass1 r1) {
            this();
        }

        private Builder() {
            super(MetricDescriptor.DEFAULT_INSTANCE);
        }

        public String getName() {
            return ((MetricDescriptor) this.instance).getName();
        }

        public ByteString getNameBytes() {
            return ((MetricDescriptor) this.instance).getNameBytes();
        }

        public Builder setName(String str) {
            copyOnWrite();
            ((MetricDescriptor) this.instance).setName(str);
            return this;
        }

        public Builder clearName() {
            copyOnWrite();
            ((MetricDescriptor) this.instance).clearName();
            return this;
        }

        public Builder setNameBytes(ByteString byteString) {
            copyOnWrite();
            ((MetricDescriptor) this.instance).setNameBytes(byteString);
            return this;
        }

        public String getType() {
            return ((MetricDescriptor) this.instance).getType();
        }

        public ByteString getTypeBytes() {
            return ((MetricDescriptor) this.instance).getTypeBytes();
        }

        public Builder setType(String str) {
            copyOnWrite();
            ((MetricDescriptor) this.instance).setType(str);
            return this;
        }

        public Builder clearType() {
            copyOnWrite();
            ((MetricDescriptor) this.instance).clearType();
            return this;
        }

        public Builder setTypeBytes(ByteString byteString) {
            copyOnWrite();
            ((MetricDescriptor) this.instance).setTypeBytes(byteString);
            return this;
        }

        public List<LabelDescriptor> getLabelsList() {
            return Collections.unmodifiableList(((MetricDescriptor) this.instance).getLabelsList());
        }

        public int getLabelsCount() {
            return ((MetricDescriptor) this.instance).getLabelsCount();
        }

        public LabelDescriptor getLabels(int i) {
            return ((MetricDescriptor) this.instance).getLabels(i);
        }

        public Builder setLabels(int i, LabelDescriptor labelDescriptor) {
            copyOnWrite();
            ((MetricDescriptor) this.instance).setLabels(i, labelDescriptor);
            return this;
        }

        public Builder setLabels(int i, LabelDescriptor.Builder builder) {
            copyOnWrite();
            ((MetricDescriptor) this.instance).setLabels(i, builder);
            return this;
        }

        public Builder addLabels(LabelDescriptor labelDescriptor) {
            copyOnWrite();
            ((MetricDescriptor) this.instance).addLabels(labelDescriptor);
            return this;
        }

        public Builder addLabels(int i, LabelDescriptor labelDescriptor) {
            copyOnWrite();
            ((MetricDescriptor) this.instance).addLabels(i, labelDescriptor);
            return this;
        }

        public Builder addLabels(LabelDescriptor.Builder builder) {
            copyOnWrite();
            ((MetricDescriptor) this.instance).addLabels(builder);
            return this;
        }

        public Builder addLabels(int i, LabelDescriptor.Builder builder) {
            copyOnWrite();
            ((MetricDescriptor) this.instance).addLabels(i, builder);
            return this;
        }

        public Builder addAllLabels(Iterable<? extends LabelDescriptor> iterable) {
            copyOnWrite();
            ((MetricDescriptor) this.instance).addAllLabels(iterable);
            return this;
        }

        public Builder clearLabels() {
            copyOnWrite();
            ((MetricDescriptor) this.instance).clearLabels();
            return this;
        }

        public Builder removeLabels(int i) {
            copyOnWrite();
            ((MetricDescriptor) this.instance).removeLabels(i);
            return this;
        }

        public int getMetricKindValue() {
            return ((MetricDescriptor) this.instance).getMetricKindValue();
        }

        public Builder setMetricKindValue(int i) {
            copyOnWrite();
            ((MetricDescriptor) this.instance).setMetricKindValue(i);
            return this;
        }

        public MetricKind getMetricKind() {
            return ((MetricDescriptor) this.instance).getMetricKind();
        }

        public Builder setMetricKind(MetricKind metricKind) {
            copyOnWrite();
            ((MetricDescriptor) this.instance).setMetricKind(metricKind);
            return this;
        }

        public Builder clearMetricKind() {
            copyOnWrite();
            ((MetricDescriptor) this.instance).clearMetricKind();
            return this;
        }

        public int getValueTypeValue() {
            return ((MetricDescriptor) this.instance).getValueTypeValue();
        }

        public Builder setValueTypeValue(int i) {
            copyOnWrite();
            ((MetricDescriptor) this.instance).setValueTypeValue(i);
            return this;
        }

        public ValueType getValueType() {
            return ((MetricDescriptor) this.instance).getValueType();
        }

        public Builder setValueType(ValueType valueType) {
            copyOnWrite();
            ((MetricDescriptor) this.instance).setValueType(valueType);
            return this;
        }

        public Builder clearValueType() {
            copyOnWrite();
            ((MetricDescriptor) this.instance).clearValueType();
            return this;
        }

        public String getUnit() {
            return ((MetricDescriptor) this.instance).getUnit();
        }

        public ByteString getUnitBytes() {
            return ((MetricDescriptor) this.instance).getUnitBytes();
        }

        public Builder setUnit(String str) {
            copyOnWrite();
            ((MetricDescriptor) this.instance).setUnit(str);
            return this;
        }

        public Builder clearUnit() {
            copyOnWrite();
            ((MetricDescriptor) this.instance).clearUnit();
            return this;
        }

        public Builder setUnitBytes(ByteString byteString) {
            copyOnWrite();
            ((MetricDescriptor) this.instance).setUnitBytes(byteString);
            return this;
        }

        public String getDescription() {
            return ((MetricDescriptor) this.instance).getDescription();
        }

        public ByteString getDescriptionBytes() {
            return ((MetricDescriptor) this.instance).getDescriptionBytes();
        }

        public Builder setDescription(String str) {
            copyOnWrite();
            ((MetricDescriptor) this.instance).setDescription(str);
            return this;
        }

        public Builder clearDescription() {
            copyOnWrite();
            ((MetricDescriptor) this.instance).clearDescription();
            return this;
        }

        public Builder setDescriptionBytes(ByteString byteString) {
            copyOnWrite();
            ((MetricDescriptor) this.instance).setDescriptionBytes(byteString);
            return this;
        }

        public String getDisplayName() {
            return ((MetricDescriptor) this.instance).getDisplayName();
        }

        public ByteString getDisplayNameBytes() {
            return ((MetricDescriptor) this.instance).getDisplayNameBytes();
        }

        public Builder setDisplayName(String str) {
            copyOnWrite();
            ((MetricDescriptor) this.instance).setDisplayName(str);
            return this;
        }

        public Builder clearDisplayName() {
            copyOnWrite();
            ((MetricDescriptor) this.instance).clearDisplayName();
            return this;
        }

        public Builder setDisplayNameBytes(ByteString byteString) {
            copyOnWrite();
            ((MetricDescriptor) this.instance).setDisplayNameBytes(byteString);
            return this;
        }
    }

    /* renamed from: com.google.api.MetricDescriptor$1  reason: invalid class name */
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.api.MetricDescriptor.AnonymousClass1.<clinit>():void");
        }
    }

    /* access modifiers changed from: protected */
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        boolean z = false;
        switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
            case 1:
                return new MetricDescriptor();
            case 2:
                return DEFAULT_INSTANCE;
            case 3:
                this.labels_.makeImmutable();
                return null;
            case 4:
                return new Builder((AnonymousClass1) null);
            case 5:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                MetricDescriptor metricDescriptor = (MetricDescriptor) obj2;
                this.name_ = visitor.visitString(!this.name_.isEmpty(), this.name_, !metricDescriptor.name_.isEmpty(), metricDescriptor.name_);
                this.type_ = visitor.visitString(!this.type_.isEmpty(), this.type_, !metricDescriptor.type_.isEmpty(), metricDescriptor.type_);
                this.labels_ = visitor.visitList(this.labels_, metricDescriptor.labels_);
                this.metricKind_ = visitor.visitInt(this.metricKind_ != 0, this.metricKind_, metricDescriptor.metricKind_ != 0, metricDescriptor.metricKind_);
                boolean z2 = this.valueType_ != 0;
                int i = this.valueType_;
                if (metricDescriptor.valueType_ != 0) {
                    z = true;
                }
                this.valueType_ = visitor.visitInt(z2, i, z, metricDescriptor.valueType_);
                this.unit_ = visitor.visitString(!this.unit_.isEmpty(), this.unit_, !metricDescriptor.unit_.isEmpty(), metricDescriptor.unit_);
                this.description_ = visitor.visitString(!this.description_.isEmpty(), this.description_, !metricDescriptor.description_.isEmpty(), metricDescriptor.description_);
                this.displayName_ = visitor.visitString(!this.displayName_.isEmpty(), this.displayName_, !metricDescriptor.displayName_.isEmpty(), metricDescriptor.displayName_);
                if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                    this.bitField0_ |= metricDescriptor.bitField0_;
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
                                this.name_ = codedInputStream.readStringRequireUtf8();
                            } else if (readTag == 18) {
                                if (!this.labels_.isModifiable()) {
                                    this.labels_ = GeneratedMessageLite.mutableCopy(this.labels_);
                                }
                                this.labels_.add((LabelDescriptor) codedInputStream.readMessage(LabelDescriptor.parser(), extensionRegistryLite));
                            } else if (readTag == 24) {
                                this.metricKind_ = codedInputStream.readEnum();
                            } else if (readTag == 32) {
                                this.valueType_ = codedInputStream.readEnum();
                            } else if (readTag == 42) {
                                this.unit_ = codedInputStream.readStringRequireUtf8();
                            } else if (readTag == 50) {
                                this.description_ = codedInputStream.readStringRequireUtf8();
                            } else if (readTag == 58) {
                                this.displayName_ = codedInputStream.readStringRequireUtf8();
                            } else if (readTag == 66) {
                                this.type_ = codedInputStream.readStringRequireUtf8();
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
                    synchronized (MetricDescriptor.class) {
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
        MetricDescriptor metricDescriptor = new MetricDescriptor();
        DEFAULT_INSTANCE = metricDescriptor;
        metricDescriptor.makeImmutable();
    }

    public static MetricDescriptor getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<MetricDescriptor> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
