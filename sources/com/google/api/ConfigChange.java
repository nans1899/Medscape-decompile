package com.google.api;

import com.google.api.Advice;
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
public final class ConfigChange extends GeneratedMessageLite<ConfigChange, Builder> implements ConfigChangeOrBuilder {
    public static final int ADVICES_FIELD_NUMBER = 5;
    public static final int CHANGE_TYPE_FIELD_NUMBER = 4;
    /* access modifiers changed from: private */
    public static final ConfigChange DEFAULT_INSTANCE;
    public static final int ELEMENT_FIELD_NUMBER = 1;
    public static final int NEW_VALUE_FIELD_NUMBER = 3;
    public static final int OLD_VALUE_FIELD_NUMBER = 2;
    private static volatile Parser<ConfigChange> PARSER;
    private Internal.ProtobufList<Advice> advices_ = emptyProtobufList();
    private int bitField0_;
    private int changeType_;
    private String element_ = "";
    private String newValue_ = "";
    private String oldValue_ = "";

    private ConfigChange() {
    }

    public String getElement() {
        return this.element_;
    }

    public ByteString getElementBytes() {
        return ByteString.copyFromUtf8(this.element_);
    }

    /* access modifiers changed from: private */
    public void setElement(String str) {
        if (str != null) {
            this.element_ = str;
            return;
        }
        throw null;
    }

    /* access modifiers changed from: private */
    public void clearElement() {
        this.element_ = getDefaultInstance().getElement();
    }

    /* access modifiers changed from: private */
    public void setElementBytes(ByteString byteString) {
        if (byteString != null) {
            checkByteStringIsUtf8(byteString);
            this.element_ = byteString.toStringUtf8();
            return;
        }
        throw null;
    }

    public String getOldValue() {
        return this.oldValue_;
    }

    public ByteString getOldValueBytes() {
        return ByteString.copyFromUtf8(this.oldValue_);
    }

    /* access modifiers changed from: private */
    public void setOldValue(String str) {
        if (str != null) {
            this.oldValue_ = str;
            return;
        }
        throw null;
    }

    /* access modifiers changed from: private */
    public void clearOldValue() {
        this.oldValue_ = getDefaultInstance().getOldValue();
    }

    /* access modifiers changed from: private */
    public void setOldValueBytes(ByteString byteString) {
        if (byteString != null) {
            checkByteStringIsUtf8(byteString);
            this.oldValue_ = byteString.toStringUtf8();
            return;
        }
        throw null;
    }

    public String getNewValue() {
        return this.newValue_;
    }

    public ByteString getNewValueBytes() {
        return ByteString.copyFromUtf8(this.newValue_);
    }

    /* access modifiers changed from: private */
    public void setNewValue(String str) {
        if (str != null) {
            this.newValue_ = str;
            return;
        }
        throw null;
    }

    /* access modifiers changed from: private */
    public void clearNewValue() {
        this.newValue_ = getDefaultInstance().getNewValue();
    }

    /* access modifiers changed from: private */
    public void setNewValueBytes(ByteString byteString) {
        if (byteString != null) {
            checkByteStringIsUtf8(byteString);
            this.newValue_ = byteString.toStringUtf8();
            return;
        }
        throw null;
    }

    public int getChangeTypeValue() {
        return this.changeType_;
    }

    public ChangeType getChangeType() {
        ChangeType forNumber = ChangeType.forNumber(this.changeType_);
        return forNumber == null ? ChangeType.UNRECOGNIZED : forNumber;
    }

    /* access modifiers changed from: private */
    public void setChangeTypeValue(int i) {
        this.changeType_ = i;
    }

    /* access modifiers changed from: private */
    public void setChangeType(ChangeType changeType) {
        if (changeType != null) {
            this.changeType_ = changeType.getNumber();
            return;
        }
        throw null;
    }

    /* access modifiers changed from: private */
    public void clearChangeType() {
        this.changeType_ = 0;
    }

    public List<Advice> getAdvicesList() {
        return this.advices_;
    }

    public List<? extends AdviceOrBuilder> getAdvicesOrBuilderList() {
        return this.advices_;
    }

    public int getAdvicesCount() {
        return this.advices_.size();
    }

    public Advice getAdvices(int i) {
        return (Advice) this.advices_.get(i);
    }

    public AdviceOrBuilder getAdvicesOrBuilder(int i) {
        return (AdviceOrBuilder) this.advices_.get(i);
    }

    private void ensureAdvicesIsMutable() {
        if (!this.advices_.isModifiable()) {
            this.advices_ = GeneratedMessageLite.mutableCopy(this.advices_);
        }
    }

    /* access modifiers changed from: private */
    public void setAdvices(int i, Advice advice) {
        if (advice != null) {
            ensureAdvicesIsMutable();
            this.advices_.set(i, advice);
            return;
        }
        throw null;
    }

    /* access modifiers changed from: private */
    public void setAdvices(int i, Advice.Builder builder) {
        ensureAdvicesIsMutable();
        this.advices_.set(i, (Advice) builder.build());
    }

    /* access modifiers changed from: private */
    public void addAdvices(Advice advice) {
        if (advice != null) {
            ensureAdvicesIsMutable();
            this.advices_.add(advice);
            return;
        }
        throw null;
    }

    /* access modifiers changed from: private */
    public void addAdvices(int i, Advice advice) {
        if (advice != null) {
            ensureAdvicesIsMutable();
            this.advices_.add(i, advice);
            return;
        }
        throw null;
    }

    /* access modifiers changed from: private */
    public void addAdvices(Advice.Builder builder) {
        ensureAdvicesIsMutable();
        this.advices_.add((Advice) builder.build());
    }

    /* access modifiers changed from: private */
    public void addAdvices(int i, Advice.Builder builder) {
        ensureAdvicesIsMutable();
        this.advices_.add(i, (Advice) builder.build());
    }

    /* access modifiers changed from: private */
    public void addAllAdvices(Iterable<? extends Advice> iterable) {
        ensureAdvicesIsMutable();
        AbstractMessageLite.addAll(iterable, this.advices_);
    }

    /* access modifiers changed from: private */
    public void clearAdvices() {
        this.advices_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    public void removeAdvices(int i) {
        ensureAdvicesIsMutable();
        this.advices_.remove(i);
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (!this.element_.isEmpty()) {
            codedOutputStream.writeString(1, getElement());
        }
        if (!this.oldValue_.isEmpty()) {
            codedOutputStream.writeString(2, getOldValue());
        }
        if (!this.newValue_.isEmpty()) {
            codedOutputStream.writeString(3, getNewValue());
        }
        if (this.changeType_ != ChangeType.CHANGE_TYPE_UNSPECIFIED.getNumber()) {
            codedOutputStream.writeEnum(4, this.changeType_);
        }
        for (int i = 0; i < this.advices_.size(); i++) {
            codedOutputStream.writeMessage(5, (MessageLite) this.advices_.get(i));
        }
    }

    public int getSerializedSize() {
        int i = this.memoizedSerializedSize;
        if (i != -1) {
            return i;
        }
        int computeStringSize = !this.element_.isEmpty() ? CodedOutputStream.computeStringSize(1, getElement()) + 0 : 0;
        if (!this.oldValue_.isEmpty()) {
            computeStringSize += CodedOutputStream.computeStringSize(2, getOldValue());
        }
        if (!this.newValue_.isEmpty()) {
            computeStringSize += CodedOutputStream.computeStringSize(3, getNewValue());
        }
        if (this.changeType_ != ChangeType.CHANGE_TYPE_UNSPECIFIED.getNumber()) {
            computeStringSize += CodedOutputStream.computeEnumSize(4, this.changeType_);
        }
        for (int i2 = 0; i2 < this.advices_.size(); i2++) {
            computeStringSize += CodedOutputStream.computeMessageSize(5, (MessageLite) this.advices_.get(i2));
        }
        this.memoizedSerializedSize = computeStringSize;
        return computeStringSize;
    }

    public static ConfigChange parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (ConfigChange) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static ConfigChange parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ConfigChange) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static ConfigChange parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (ConfigChange) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static ConfigChange parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ConfigChange) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static ConfigChange parseFrom(InputStream inputStream) throws IOException {
        return (ConfigChange) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static ConfigChange parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ConfigChange) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static ConfigChange parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (ConfigChange) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static ConfigChange parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ConfigChange) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static ConfigChange parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (ConfigChange) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static ConfigChange parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ConfigChange) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(ConfigChange configChange) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(configChange);
    }

    /* compiled from: com.google.firebase:protolite-well-known-types@@16.0.1 */
    public static final class Builder extends GeneratedMessageLite.Builder<ConfigChange, Builder> implements ConfigChangeOrBuilder {
        /* synthetic */ Builder(AnonymousClass1 r1) {
            this();
        }

        private Builder() {
            super(ConfigChange.DEFAULT_INSTANCE);
        }

        public String getElement() {
            return ((ConfigChange) this.instance).getElement();
        }

        public ByteString getElementBytes() {
            return ((ConfigChange) this.instance).getElementBytes();
        }

        public Builder setElement(String str) {
            copyOnWrite();
            ((ConfigChange) this.instance).setElement(str);
            return this;
        }

        public Builder clearElement() {
            copyOnWrite();
            ((ConfigChange) this.instance).clearElement();
            return this;
        }

        public Builder setElementBytes(ByteString byteString) {
            copyOnWrite();
            ((ConfigChange) this.instance).setElementBytes(byteString);
            return this;
        }

        public String getOldValue() {
            return ((ConfigChange) this.instance).getOldValue();
        }

        public ByteString getOldValueBytes() {
            return ((ConfigChange) this.instance).getOldValueBytes();
        }

        public Builder setOldValue(String str) {
            copyOnWrite();
            ((ConfigChange) this.instance).setOldValue(str);
            return this;
        }

        public Builder clearOldValue() {
            copyOnWrite();
            ((ConfigChange) this.instance).clearOldValue();
            return this;
        }

        public Builder setOldValueBytes(ByteString byteString) {
            copyOnWrite();
            ((ConfigChange) this.instance).setOldValueBytes(byteString);
            return this;
        }

        public String getNewValue() {
            return ((ConfigChange) this.instance).getNewValue();
        }

        public ByteString getNewValueBytes() {
            return ((ConfigChange) this.instance).getNewValueBytes();
        }

        public Builder setNewValue(String str) {
            copyOnWrite();
            ((ConfigChange) this.instance).setNewValue(str);
            return this;
        }

        public Builder clearNewValue() {
            copyOnWrite();
            ((ConfigChange) this.instance).clearNewValue();
            return this;
        }

        public Builder setNewValueBytes(ByteString byteString) {
            copyOnWrite();
            ((ConfigChange) this.instance).setNewValueBytes(byteString);
            return this;
        }

        public int getChangeTypeValue() {
            return ((ConfigChange) this.instance).getChangeTypeValue();
        }

        public Builder setChangeTypeValue(int i) {
            copyOnWrite();
            ((ConfigChange) this.instance).setChangeTypeValue(i);
            return this;
        }

        public ChangeType getChangeType() {
            return ((ConfigChange) this.instance).getChangeType();
        }

        public Builder setChangeType(ChangeType changeType) {
            copyOnWrite();
            ((ConfigChange) this.instance).setChangeType(changeType);
            return this;
        }

        public Builder clearChangeType() {
            copyOnWrite();
            ((ConfigChange) this.instance).clearChangeType();
            return this;
        }

        public List<Advice> getAdvicesList() {
            return Collections.unmodifiableList(((ConfigChange) this.instance).getAdvicesList());
        }

        public int getAdvicesCount() {
            return ((ConfigChange) this.instance).getAdvicesCount();
        }

        public Advice getAdvices(int i) {
            return ((ConfigChange) this.instance).getAdvices(i);
        }

        public Builder setAdvices(int i, Advice advice) {
            copyOnWrite();
            ((ConfigChange) this.instance).setAdvices(i, advice);
            return this;
        }

        public Builder setAdvices(int i, Advice.Builder builder) {
            copyOnWrite();
            ((ConfigChange) this.instance).setAdvices(i, builder);
            return this;
        }

        public Builder addAdvices(Advice advice) {
            copyOnWrite();
            ((ConfigChange) this.instance).addAdvices(advice);
            return this;
        }

        public Builder addAdvices(int i, Advice advice) {
            copyOnWrite();
            ((ConfigChange) this.instance).addAdvices(i, advice);
            return this;
        }

        public Builder addAdvices(Advice.Builder builder) {
            copyOnWrite();
            ((ConfigChange) this.instance).addAdvices(builder);
            return this;
        }

        public Builder addAdvices(int i, Advice.Builder builder) {
            copyOnWrite();
            ((ConfigChange) this.instance).addAdvices(i, builder);
            return this;
        }

        public Builder addAllAdvices(Iterable<? extends Advice> iterable) {
            copyOnWrite();
            ((ConfigChange) this.instance).addAllAdvices(iterable);
            return this;
        }

        public Builder clearAdvices() {
            copyOnWrite();
            ((ConfigChange) this.instance).clearAdvices();
            return this;
        }

        public Builder removeAdvices(int i) {
            copyOnWrite();
            ((ConfigChange) this.instance).removeAdvices(i);
            return this;
        }
    }

    /* renamed from: com.google.api.ConfigChange$1  reason: invalid class name */
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.api.ConfigChange.AnonymousClass1.<clinit>():void");
        }
    }

    /* access modifiers changed from: protected */
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        boolean z = false;
        switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
            case 1:
                return new ConfigChange();
            case 2:
                return DEFAULT_INSTANCE;
            case 3:
                this.advices_.makeImmutable();
                return null;
            case 4:
                return new Builder((AnonymousClass1) null);
            case 5:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                ConfigChange configChange = (ConfigChange) obj2;
                this.element_ = visitor.visitString(!this.element_.isEmpty(), this.element_, !configChange.element_.isEmpty(), configChange.element_);
                this.oldValue_ = visitor.visitString(!this.oldValue_.isEmpty(), this.oldValue_, !configChange.oldValue_.isEmpty(), configChange.oldValue_);
                this.newValue_ = visitor.visitString(!this.newValue_.isEmpty(), this.newValue_, !configChange.newValue_.isEmpty(), configChange.newValue_);
                boolean z2 = this.changeType_ != 0;
                int i = this.changeType_;
                if (configChange.changeType_ != 0) {
                    z = true;
                }
                this.changeType_ = visitor.visitInt(z2, i, z, configChange.changeType_);
                this.advices_ = visitor.visitList(this.advices_, configChange.advices_);
                if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                    this.bitField0_ |= configChange.bitField0_;
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
                                this.element_ = codedInputStream.readStringRequireUtf8();
                            } else if (readTag == 18) {
                                this.oldValue_ = codedInputStream.readStringRequireUtf8();
                            } else if (readTag == 26) {
                                this.newValue_ = codedInputStream.readStringRequireUtf8();
                            } else if (readTag == 32) {
                                this.changeType_ = codedInputStream.readEnum();
                            } else if (readTag == 42) {
                                if (!this.advices_.isModifiable()) {
                                    this.advices_ = GeneratedMessageLite.mutableCopy(this.advices_);
                                }
                                this.advices_.add((Advice) codedInputStream.readMessage(Advice.parser(), extensionRegistryLite));
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
                    synchronized (ConfigChange.class) {
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
        ConfigChange configChange = new ConfigChange();
        DEFAULT_INSTANCE = configChange;
        configChange.makeImmutable();
    }

    public static ConfigChange getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<ConfigChange> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
