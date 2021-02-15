package com.google.protobuf;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.ListValue;
import com.google.protobuf.Struct;
import java.io.IOException;
import java.io.InputStream;

/* compiled from: com.google.firebase:protolite-well-known-types@@16.0.1 */
public final class Value extends GeneratedMessageLite<Value, Builder> implements ValueOrBuilder {
    public static final int BOOL_VALUE_FIELD_NUMBER = 4;
    /* access modifiers changed from: private */
    public static final Value DEFAULT_INSTANCE;
    public static final int LIST_VALUE_FIELD_NUMBER = 6;
    public static final int NULL_VALUE_FIELD_NUMBER = 1;
    public static final int NUMBER_VALUE_FIELD_NUMBER = 2;
    private static volatile Parser<Value> PARSER = null;
    public static final int STRING_VALUE_FIELD_NUMBER = 3;
    public static final int STRUCT_VALUE_FIELD_NUMBER = 5;
    private int kindCase_ = 0;
    private Object kind_;

    private Value() {
    }

    /* compiled from: com.google.firebase:protolite-well-known-types@@16.0.1 */
    public enum KindCase implements Internal.EnumLite {
        NULL_VALUE(1),
        NUMBER_VALUE(2),
        STRING_VALUE(3),
        BOOL_VALUE(4),
        STRUCT_VALUE(5),
        LIST_VALUE(6),
        KIND_NOT_SET(0);
        
        private final int value;

        private KindCase(int i) {
            this.value = i;
        }

        @Deprecated
        public static KindCase valueOf(int i) {
            return forNumber(i);
        }

        public static KindCase forNumber(int i) {
            switch (i) {
                case 0:
                    return KIND_NOT_SET;
                case 1:
                    return NULL_VALUE;
                case 2:
                    return NUMBER_VALUE;
                case 3:
                    return STRING_VALUE;
                case 4:
                    return BOOL_VALUE;
                case 5:
                    return STRUCT_VALUE;
                case 6:
                    return LIST_VALUE;
                default:
                    return null;
            }
        }

        public int getNumber() {
            return this.value;
        }
    }

    public KindCase getKindCase() {
        return KindCase.forNumber(this.kindCase_);
    }

    /* access modifiers changed from: private */
    public void clearKind() {
        this.kindCase_ = 0;
        this.kind_ = null;
    }

    public int getNullValueValue() {
        if (this.kindCase_ == 1) {
            return ((Integer) this.kind_).intValue();
        }
        return 0;
    }

    public NullValue getNullValue() {
        if (this.kindCase_ != 1) {
            return NullValue.NULL_VALUE;
        }
        NullValue forNumber = NullValue.forNumber(((Integer) this.kind_).intValue());
        return forNumber == null ? NullValue.UNRECOGNIZED : forNumber;
    }

    /* access modifiers changed from: private */
    public void setNullValueValue(int i) {
        this.kindCase_ = 1;
        this.kind_ = Integer.valueOf(i);
    }

    /* access modifiers changed from: private */
    public void setNullValue(NullValue nullValue) {
        if (nullValue != null) {
            this.kindCase_ = 1;
            this.kind_ = Integer.valueOf(nullValue.getNumber());
            return;
        }
        throw null;
    }

    /* access modifiers changed from: private */
    public void clearNullValue() {
        if (this.kindCase_ == 1) {
            this.kindCase_ = 0;
            this.kind_ = null;
        }
    }

    public double getNumberValue() {
        return this.kindCase_ == 2 ? ((Double) this.kind_).doubleValue() : FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
    }

    /* access modifiers changed from: private */
    public void setNumberValue(double d) {
        this.kindCase_ = 2;
        this.kind_ = Double.valueOf(d);
    }

    /* access modifiers changed from: private */
    public void clearNumberValue() {
        if (this.kindCase_ == 2) {
            this.kindCase_ = 0;
            this.kind_ = null;
        }
    }

    public String getStringValue() {
        return this.kindCase_ == 3 ? (String) this.kind_ : "";
    }

    public ByteString getStringValueBytes() {
        return ByteString.copyFromUtf8(this.kindCase_ == 3 ? (String) this.kind_ : "");
    }

    /* access modifiers changed from: private */
    public void setStringValue(String str) {
        if (str != null) {
            this.kindCase_ = 3;
            this.kind_ = str;
            return;
        }
        throw null;
    }

    /* access modifiers changed from: private */
    public void clearStringValue() {
        if (this.kindCase_ == 3) {
            this.kindCase_ = 0;
            this.kind_ = null;
        }
    }

    /* access modifiers changed from: private */
    public void setStringValueBytes(ByteString byteString) {
        if (byteString != null) {
            checkByteStringIsUtf8(byteString);
            this.kindCase_ = 3;
            this.kind_ = byteString.toStringUtf8();
            return;
        }
        throw null;
    }

    public boolean getBoolValue() {
        if (this.kindCase_ == 4) {
            return ((Boolean) this.kind_).booleanValue();
        }
        return false;
    }

    /* access modifiers changed from: private */
    public void setBoolValue(boolean z) {
        this.kindCase_ = 4;
        this.kind_ = Boolean.valueOf(z);
    }

    /* access modifiers changed from: private */
    public void clearBoolValue() {
        if (this.kindCase_ == 4) {
            this.kindCase_ = 0;
            this.kind_ = null;
        }
    }

    public Struct getStructValue() {
        if (this.kindCase_ == 5) {
            return (Struct) this.kind_;
        }
        return Struct.getDefaultInstance();
    }

    /* access modifiers changed from: private */
    public void setStructValue(Struct struct) {
        if (struct != null) {
            this.kind_ = struct;
            this.kindCase_ = 5;
            return;
        }
        throw null;
    }

    /* access modifiers changed from: private */
    public void setStructValue(Struct.Builder builder) {
        this.kind_ = builder.build();
        this.kindCase_ = 5;
    }

    /* access modifiers changed from: private */
    public void mergeStructValue(Struct struct) {
        if (this.kindCase_ != 5 || this.kind_ == Struct.getDefaultInstance()) {
            this.kind_ = struct;
        } else {
            this.kind_ = ((Struct.Builder) Struct.newBuilder((Struct) this.kind_).mergeFrom(struct)).buildPartial();
        }
        this.kindCase_ = 5;
    }

    /* access modifiers changed from: private */
    public void clearStructValue() {
        if (this.kindCase_ == 5) {
            this.kindCase_ = 0;
            this.kind_ = null;
        }
    }

    public ListValue getListValue() {
        if (this.kindCase_ == 6) {
            return (ListValue) this.kind_;
        }
        return ListValue.getDefaultInstance();
    }

    /* access modifiers changed from: private */
    public void setListValue(ListValue listValue) {
        if (listValue != null) {
            this.kind_ = listValue;
            this.kindCase_ = 6;
            return;
        }
        throw null;
    }

    /* access modifiers changed from: private */
    public void setListValue(ListValue.Builder builder) {
        this.kind_ = builder.build();
        this.kindCase_ = 6;
    }

    /* access modifiers changed from: private */
    public void mergeListValue(ListValue listValue) {
        if (this.kindCase_ != 6 || this.kind_ == ListValue.getDefaultInstance()) {
            this.kind_ = listValue;
        } else {
            this.kind_ = ((ListValue.Builder) ListValue.newBuilder((ListValue) this.kind_).mergeFrom(listValue)).buildPartial();
        }
        this.kindCase_ = 6;
    }

    /* access modifiers changed from: private */
    public void clearListValue() {
        if (this.kindCase_ == 6) {
            this.kindCase_ = 0;
            this.kind_ = null;
        }
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (this.kindCase_ == 1) {
            codedOutputStream.writeEnum(1, ((Integer) this.kind_).intValue());
        }
        if (this.kindCase_ == 2) {
            codedOutputStream.writeDouble(2, ((Double) this.kind_).doubleValue());
        }
        if (this.kindCase_ == 3) {
            codedOutputStream.writeString(3, getStringValue());
        }
        if (this.kindCase_ == 4) {
            codedOutputStream.writeBool(4, ((Boolean) this.kind_).booleanValue());
        }
        if (this.kindCase_ == 5) {
            codedOutputStream.writeMessage(5, (Struct) this.kind_);
        }
        if (this.kindCase_ == 6) {
            codedOutputStream.writeMessage(6, (ListValue) this.kind_);
        }
    }

    public int getSerializedSize() {
        int i = this.memoizedSerializedSize;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        if (this.kindCase_ == 1) {
            i2 = 0 + CodedOutputStream.computeEnumSize(1, ((Integer) this.kind_).intValue());
        }
        if (this.kindCase_ == 2) {
            i2 += CodedOutputStream.computeDoubleSize(2, ((Double) this.kind_).doubleValue());
        }
        if (this.kindCase_ == 3) {
            i2 += CodedOutputStream.computeStringSize(3, getStringValue());
        }
        if (this.kindCase_ == 4) {
            i2 += CodedOutputStream.computeBoolSize(4, ((Boolean) this.kind_).booleanValue());
        }
        if (this.kindCase_ == 5) {
            i2 += CodedOutputStream.computeMessageSize(5, (Struct) this.kind_);
        }
        if (this.kindCase_ == 6) {
            i2 += CodedOutputStream.computeMessageSize(6, (ListValue) this.kind_);
        }
        this.memoizedSerializedSize = i2;
        return i2;
    }

    public static Value parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (Value) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static Value parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Value) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static Value parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (Value) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static Value parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Value) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static Value parseFrom(InputStream inputStream) throws IOException {
        return (Value) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static Value parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Value) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static Value parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (Value) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static Value parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Value) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static Value parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (Value) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static Value parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Value) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(Value value) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(value);
    }

    /* compiled from: com.google.firebase:protolite-well-known-types@@16.0.1 */
    public static final class Builder extends GeneratedMessageLite.Builder<Value, Builder> implements ValueOrBuilder {
        /* synthetic */ Builder(AnonymousClass1 r1) {
            this();
        }

        private Builder() {
            super(Value.DEFAULT_INSTANCE);
        }

        public KindCase getKindCase() {
            return ((Value) this.instance).getKindCase();
        }

        public Builder clearKind() {
            copyOnWrite();
            ((Value) this.instance).clearKind();
            return this;
        }

        public int getNullValueValue() {
            return ((Value) this.instance).getNullValueValue();
        }

        public Builder setNullValueValue(int i) {
            copyOnWrite();
            ((Value) this.instance).setNullValueValue(i);
            return this;
        }

        public NullValue getNullValue() {
            return ((Value) this.instance).getNullValue();
        }

        public Builder setNullValue(NullValue nullValue) {
            copyOnWrite();
            ((Value) this.instance).setNullValue(nullValue);
            return this;
        }

        public Builder clearNullValue() {
            copyOnWrite();
            ((Value) this.instance).clearNullValue();
            return this;
        }

        public double getNumberValue() {
            return ((Value) this.instance).getNumberValue();
        }

        public Builder setNumberValue(double d) {
            copyOnWrite();
            ((Value) this.instance).setNumberValue(d);
            return this;
        }

        public Builder clearNumberValue() {
            copyOnWrite();
            ((Value) this.instance).clearNumberValue();
            return this;
        }

        public String getStringValue() {
            return ((Value) this.instance).getStringValue();
        }

        public ByteString getStringValueBytes() {
            return ((Value) this.instance).getStringValueBytes();
        }

        public Builder setStringValue(String str) {
            copyOnWrite();
            ((Value) this.instance).setStringValue(str);
            return this;
        }

        public Builder clearStringValue() {
            copyOnWrite();
            ((Value) this.instance).clearStringValue();
            return this;
        }

        public Builder setStringValueBytes(ByteString byteString) {
            copyOnWrite();
            ((Value) this.instance).setStringValueBytes(byteString);
            return this;
        }

        public boolean getBoolValue() {
            return ((Value) this.instance).getBoolValue();
        }

        public Builder setBoolValue(boolean z) {
            copyOnWrite();
            ((Value) this.instance).setBoolValue(z);
            return this;
        }

        public Builder clearBoolValue() {
            copyOnWrite();
            ((Value) this.instance).clearBoolValue();
            return this;
        }

        public Struct getStructValue() {
            return ((Value) this.instance).getStructValue();
        }

        public Builder setStructValue(Struct struct) {
            copyOnWrite();
            ((Value) this.instance).setStructValue(struct);
            return this;
        }

        public Builder setStructValue(Struct.Builder builder) {
            copyOnWrite();
            ((Value) this.instance).setStructValue(builder);
            return this;
        }

        public Builder mergeStructValue(Struct struct) {
            copyOnWrite();
            ((Value) this.instance).mergeStructValue(struct);
            return this;
        }

        public Builder clearStructValue() {
            copyOnWrite();
            ((Value) this.instance).clearStructValue();
            return this;
        }

        public ListValue getListValue() {
            return ((Value) this.instance).getListValue();
        }

        public Builder setListValue(ListValue listValue) {
            copyOnWrite();
            ((Value) this.instance).setListValue(listValue);
            return this;
        }

        public Builder setListValue(ListValue.Builder builder) {
            copyOnWrite();
            ((Value) this.instance).setListValue(builder);
            return this;
        }

        public Builder mergeListValue(ListValue listValue) {
            copyOnWrite();
            ((Value) this.instance).mergeListValue(listValue);
            return this;
        }

        public Builder clearListValue() {
            copyOnWrite();
            ((Value) this.instance).clearListValue();
            return this;
        }
    }

    /* renamed from: com.google.protobuf.Value$1  reason: invalid class name */
    /* compiled from: com.google.firebase:protolite-well-known-types@@16.0.1 */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke;
        static final /* synthetic */ int[] $SwitchMap$com$google$protobuf$Value$KindCase;

        /* JADX WARNING: Can't wrap try/catch for region: R(34:0|(2:1|2)|3|(2:5|6)|7|9|10|11|(2:13|14)|15|(2:17|18)|19|21|22|23|25|26|(2:27|28)|29|31|32|33|34|35|36|37|38|39|40|41|42|43|44|46) */
        /* JADX WARNING: Can't wrap try/catch for region: R(37:0|(2:1|2)|3|5|6|7|9|10|11|(2:13|14)|15|17|18|19|21|22|23|25|26|27|28|29|31|32|33|34|35|36|37|38|39|40|41|42|43|44|46) */
        /* JADX WARNING: Can't wrap try/catch for region: R(39:0|1|2|3|5|6|7|9|10|11|13|14|15|17|18|19|21|22|23|25|26|27|28|29|31|32|33|34|35|36|37|38|39|40|41|42|43|44|46) */
        /* JADX WARNING: Code restructure failed: missing block: B:47:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x0054 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:33:0x0071 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:35:0x007b */
        /* JADX WARNING: Missing exception handler attribute for start block: B:37:0x0085 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:39:0x008f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:41:0x0099 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:43:0x00a3 */
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
                r5 = 6
                int[] r6 = $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke     // Catch:{ NoSuchFieldError -> 0x0049 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r7 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.MERGE_FROM_STREAM     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r7 = r7.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r6[r7] = r5     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                r6 = 7
                int[] r7 = $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke     // Catch:{ NoSuchFieldError -> 0x0054 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r8 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r8 = r8.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r7[r8] = r6     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                int[] r7 = $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke     // Catch:{ NoSuchFieldError -> 0x0060 }
                com.google.protobuf.GeneratedMessageLite$MethodToInvoke r8 = com.google.protobuf.GeneratedMessageLite.MethodToInvoke.GET_PARSER     // Catch:{ NoSuchFieldError -> 0x0060 }
                int r8 = r8.ordinal()     // Catch:{ NoSuchFieldError -> 0x0060 }
                r9 = 8
                r7[r8] = r9     // Catch:{ NoSuchFieldError -> 0x0060 }
            L_0x0060:
                com.google.protobuf.Value$KindCase[] r7 = com.google.protobuf.Value.KindCase.values()
                int r7 = r7.length
                int[] r7 = new int[r7]
                $SwitchMap$com$google$protobuf$Value$KindCase = r7
                com.google.protobuf.Value$KindCase r8 = com.google.protobuf.Value.KindCase.NULL_VALUE     // Catch:{ NoSuchFieldError -> 0x0071 }
                int r8 = r8.ordinal()     // Catch:{ NoSuchFieldError -> 0x0071 }
                r7[r8] = r1     // Catch:{ NoSuchFieldError -> 0x0071 }
            L_0x0071:
                int[] r1 = $SwitchMap$com$google$protobuf$Value$KindCase     // Catch:{ NoSuchFieldError -> 0x007b }
                com.google.protobuf.Value$KindCase r7 = com.google.protobuf.Value.KindCase.NUMBER_VALUE     // Catch:{ NoSuchFieldError -> 0x007b }
                int r7 = r7.ordinal()     // Catch:{ NoSuchFieldError -> 0x007b }
                r1[r7] = r0     // Catch:{ NoSuchFieldError -> 0x007b }
            L_0x007b:
                int[] r0 = $SwitchMap$com$google$protobuf$Value$KindCase     // Catch:{ NoSuchFieldError -> 0x0085 }
                com.google.protobuf.Value$KindCase r1 = com.google.protobuf.Value.KindCase.STRING_VALUE     // Catch:{ NoSuchFieldError -> 0x0085 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0085 }
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0085 }
            L_0x0085:
                int[] r0 = $SwitchMap$com$google$protobuf$Value$KindCase     // Catch:{ NoSuchFieldError -> 0x008f }
                com.google.protobuf.Value$KindCase r1 = com.google.protobuf.Value.KindCase.BOOL_VALUE     // Catch:{ NoSuchFieldError -> 0x008f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x008f }
                r0[r1] = r3     // Catch:{ NoSuchFieldError -> 0x008f }
            L_0x008f:
                int[] r0 = $SwitchMap$com$google$protobuf$Value$KindCase     // Catch:{ NoSuchFieldError -> 0x0099 }
                com.google.protobuf.Value$KindCase r1 = com.google.protobuf.Value.KindCase.STRUCT_VALUE     // Catch:{ NoSuchFieldError -> 0x0099 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0099 }
                r0[r1] = r4     // Catch:{ NoSuchFieldError -> 0x0099 }
            L_0x0099:
                int[] r0 = $SwitchMap$com$google$protobuf$Value$KindCase     // Catch:{ NoSuchFieldError -> 0x00a3 }
                com.google.protobuf.Value$KindCase r1 = com.google.protobuf.Value.KindCase.LIST_VALUE     // Catch:{ NoSuchFieldError -> 0x00a3 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00a3 }
                r0[r1] = r5     // Catch:{ NoSuchFieldError -> 0x00a3 }
            L_0x00a3:
                int[] r0 = $SwitchMap$com$google$protobuf$Value$KindCase     // Catch:{ NoSuchFieldError -> 0x00ad }
                com.google.protobuf.Value$KindCase r1 = com.google.protobuf.Value.KindCase.KIND_NOT_SET     // Catch:{ NoSuchFieldError -> 0x00ad }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00ad }
                r0[r1] = r6     // Catch:{ NoSuchFieldError -> 0x00ad }
            L_0x00ad:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.Value.AnonymousClass1.<clinit>():void");
        }
    }

    /* access modifiers changed from: protected */
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        int i;
        boolean z = false;
        switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
            case 1:
                return new Value();
            case 2:
                return DEFAULT_INSTANCE;
            case 3:
                return null;
            case 4:
                return new Builder((AnonymousClass1) null);
            case 5:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                Value value = (Value) obj2;
                switch (AnonymousClass1.$SwitchMap$com$google$protobuf$Value$KindCase[value.getKindCase().ordinal()]) {
                    case 1:
                        if (this.kindCase_ == 1) {
                            z = true;
                        }
                        this.kind_ = visitor.visitOneofInt(z, this.kind_, value.kind_);
                        break;
                    case 2:
                        if (this.kindCase_ == 2) {
                            z = true;
                        }
                        this.kind_ = visitor.visitOneofDouble(z, this.kind_, value.kind_);
                        break;
                    case 3:
                        if (this.kindCase_ == 3) {
                            z = true;
                        }
                        this.kind_ = visitor.visitOneofString(z, this.kind_, value.kind_);
                        break;
                    case 4:
                        if (this.kindCase_ == 4) {
                            z = true;
                        }
                        this.kind_ = visitor.visitOneofBoolean(z, this.kind_, value.kind_);
                        break;
                    case 5:
                        if (this.kindCase_ == 5) {
                            z = true;
                        }
                        this.kind_ = visitor.visitOneofMessage(z, this.kind_, value.kind_);
                        break;
                    case 6:
                        if (this.kindCase_ == 6) {
                            z = true;
                        }
                        this.kind_ = visitor.visitOneofMessage(z, this.kind_, value.kind_);
                        break;
                    case 7:
                        if (this.kindCase_ != 0) {
                            z = true;
                        }
                        visitor.visitOneofNotSet(z);
                        break;
                }
                if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE && (i = value.kindCase_) != 0) {
                    this.kindCase_ = i;
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
                                this.kindCase_ = 1;
                                this.kind_ = Integer.valueOf(readEnum);
                            } else if (readTag == 17) {
                                this.kindCase_ = 2;
                                this.kind_ = Double.valueOf(codedInputStream.readDouble());
                            } else if (readTag == 26) {
                                String readStringRequireUtf8 = codedInputStream.readStringRequireUtf8();
                                this.kindCase_ = 3;
                                this.kind_ = readStringRequireUtf8;
                            } else if (readTag == 32) {
                                this.kindCase_ = 4;
                                this.kind_ = Boolean.valueOf(codedInputStream.readBool());
                            } else if (readTag == 42) {
                                Struct.Builder builder = this.kindCase_ == 5 ? (Struct.Builder) ((Struct) this.kind_).toBuilder() : null;
                                MessageLite readMessage = codedInputStream.readMessage(Struct.parser(), extensionRegistryLite);
                                this.kind_ = readMessage;
                                if (builder != null) {
                                    builder.mergeFrom((Struct) readMessage);
                                    this.kind_ = builder.buildPartial();
                                }
                                this.kindCase_ = 5;
                            } else if (readTag == 50) {
                                ListValue.Builder builder2 = this.kindCase_ == 6 ? (ListValue.Builder) ((ListValue) this.kind_).toBuilder() : null;
                                MessageLite readMessage2 = codedInputStream.readMessage(ListValue.parser(), extensionRegistryLite);
                                this.kind_ = readMessage2;
                                if (builder2 != null) {
                                    builder2.mergeFrom((ListValue) readMessage2);
                                    this.kind_ = builder2.buildPartial();
                                }
                                this.kindCase_ = 6;
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
                    synchronized (Value.class) {
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
        Value value = new Value();
        DEFAULT_INSTANCE = value;
        value.makeImmutable();
    }

    public static Value getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Value> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
