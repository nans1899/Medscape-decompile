package com.google.api;

import com.google.protobuf.AbstractMessageLite;
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
import java.util.Collections;
import java.util.List;

/* compiled from: com.google.firebase:protolite-well-known-types@@16.0.1 */
public final class ContextRule extends GeneratedMessageLite<ContextRule, Builder> implements ContextRuleOrBuilder {
    /* access modifiers changed from: private */
    public static final ContextRule DEFAULT_INSTANCE;
    private static volatile Parser<ContextRule> PARSER = null;
    public static final int PROVIDED_FIELD_NUMBER = 3;
    public static final int REQUESTED_FIELD_NUMBER = 2;
    public static final int SELECTOR_FIELD_NUMBER = 1;
    private int bitField0_;
    private Internal.ProtobufList<String> provided_ = GeneratedMessageLite.emptyProtobufList();
    private Internal.ProtobufList<String> requested_ = GeneratedMessageLite.emptyProtobufList();
    private String selector_ = "";

    private ContextRule() {
    }

    public String getSelector() {
        return this.selector_;
    }

    public ByteString getSelectorBytes() {
        return ByteString.copyFromUtf8(this.selector_);
    }

    /* access modifiers changed from: private */
    public void setSelector(String str) {
        if (str != null) {
            this.selector_ = str;
            return;
        }
        throw null;
    }

    /* access modifiers changed from: private */
    public void clearSelector() {
        this.selector_ = getDefaultInstance().getSelector();
    }

    /* access modifiers changed from: private */
    public void setSelectorBytes(ByteString byteString) {
        if (byteString != null) {
            checkByteStringIsUtf8(byteString);
            this.selector_ = byteString.toStringUtf8();
            return;
        }
        throw null;
    }

    public List<String> getRequestedList() {
        return this.requested_;
    }

    public int getRequestedCount() {
        return this.requested_.size();
    }

    public String getRequested(int i) {
        return (String) this.requested_.get(i);
    }

    public ByteString getRequestedBytes(int i) {
        return ByteString.copyFromUtf8((String) this.requested_.get(i));
    }

    private void ensureRequestedIsMutable() {
        if (!this.requested_.isModifiable()) {
            this.requested_ = GeneratedMessageLite.mutableCopy(this.requested_);
        }
    }

    /* access modifiers changed from: private */
    public void setRequested(int i, String str) {
        if (str != null) {
            ensureRequestedIsMutable();
            this.requested_.set(i, str);
            return;
        }
        throw null;
    }

    /* access modifiers changed from: private */
    public void addRequested(String str) {
        if (str != null) {
            ensureRequestedIsMutable();
            this.requested_.add(str);
            return;
        }
        throw null;
    }

    /* access modifiers changed from: private */
    public void addAllRequested(Iterable<String> iterable) {
        ensureRequestedIsMutable();
        AbstractMessageLite.addAll(iterable, this.requested_);
    }

    /* access modifiers changed from: private */
    public void clearRequested() {
        this.requested_ = GeneratedMessageLite.emptyProtobufList();
    }

    /* access modifiers changed from: private */
    public void addRequestedBytes(ByteString byteString) {
        if (byteString != null) {
            checkByteStringIsUtf8(byteString);
            ensureRequestedIsMutable();
            this.requested_.add(byteString.toStringUtf8());
            return;
        }
        throw null;
    }

    public List<String> getProvidedList() {
        return this.provided_;
    }

    public int getProvidedCount() {
        return this.provided_.size();
    }

    public String getProvided(int i) {
        return (String) this.provided_.get(i);
    }

    public ByteString getProvidedBytes(int i) {
        return ByteString.copyFromUtf8((String) this.provided_.get(i));
    }

    private void ensureProvidedIsMutable() {
        if (!this.provided_.isModifiable()) {
            this.provided_ = GeneratedMessageLite.mutableCopy(this.provided_);
        }
    }

    /* access modifiers changed from: private */
    public void setProvided(int i, String str) {
        if (str != null) {
            ensureProvidedIsMutable();
            this.provided_.set(i, str);
            return;
        }
        throw null;
    }

    /* access modifiers changed from: private */
    public void addProvided(String str) {
        if (str != null) {
            ensureProvidedIsMutable();
            this.provided_.add(str);
            return;
        }
        throw null;
    }

    /* access modifiers changed from: private */
    public void addAllProvided(Iterable<String> iterable) {
        ensureProvidedIsMutable();
        AbstractMessageLite.addAll(iterable, this.provided_);
    }

    /* access modifiers changed from: private */
    public void clearProvided() {
        this.provided_ = GeneratedMessageLite.emptyProtobufList();
    }

    /* access modifiers changed from: private */
    public void addProvidedBytes(ByteString byteString) {
        if (byteString != null) {
            checkByteStringIsUtf8(byteString);
            ensureProvidedIsMutable();
            this.provided_.add(byteString.toStringUtf8());
            return;
        }
        throw null;
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (!this.selector_.isEmpty()) {
            codedOutputStream.writeString(1, getSelector());
        }
        for (int i = 0; i < this.requested_.size(); i++) {
            codedOutputStream.writeString(2, (String) this.requested_.get(i));
        }
        for (int i2 = 0; i2 < this.provided_.size(); i2++) {
            codedOutputStream.writeString(3, (String) this.provided_.get(i2));
        }
    }

    public int getSerializedSize() {
        int i = this.memoizedSerializedSize;
        if (i != -1) {
            return i;
        }
        int computeStringSize = !this.selector_.isEmpty() ? CodedOutputStream.computeStringSize(1, getSelector()) + 0 : 0;
        int i2 = 0;
        for (int i3 = 0; i3 < this.requested_.size(); i3++) {
            i2 += CodedOutputStream.computeStringSizeNoTag((String) this.requested_.get(i3));
        }
        int size = computeStringSize + i2 + (getRequestedList().size() * 1);
        int i4 = 0;
        for (int i5 = 0; i5 < this.provided_.size(); i5++) {
            i4 += CodedOutputStream.computeStringSizeNoTag((String) this.provided_.get(i5));
        }
        int size2 = size + i4 + (getProvidedList().size() * 1);
        this.memoizedSerializedSize = size2;
        return size2;
    }

    public static ContextRule parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (ContextRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static ContextRule parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ContextRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static ContextRule parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (ContextRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static ContextRule parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ContextRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static ContextRule parseFrom(InputStream inputStream) throws IOException {
        return (ContextRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static ContextRule parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ContextRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static ContextRule parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (ContextRule) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static ContextRule parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ContextRule) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static ContextRule parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (ContextRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static ContextRule parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ContextRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(ContextRule contextRule) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(contextRule);
    }

    /* compiled from: com.google.firebase:protolite-well-known-types@@16.0.1 */
    public static final class Builder extends GeneratedMessageLite.Builder<ContextRule, Builder> implements ContextRuleOrBuilder {
        /* synthetic */ Builder(AnonymousClass1 r1) {
            this();
        }

        private Builder() {
            super(ContextRule.DEFAULT_INSTANCE);
        }

        public String getSelector() {
            return ((ContextRule) this.instance).getSelector();
        }

        public ByteString getSelectorBytes() {
            return ((ContextRule) this.instance).getSelectorBytes();
        }

        public Builder setSelector(String str) {
            copyOnWrite();
            ((ContextRule) this.instance).setSelector(str);
            return this;
        }

        public Builder clearSelector() {
            copyOnWrite();
            ((ContextRule) this.instance).clearSelector();
            return this;
        }

        public Builder setSelectorBytes(ByteString byteString) {
            copyOnWrite();
            ((ContextRule) this.instance).setSelectorBytes(byteString);
            return this;
        }

        public List<String> getRequestedList() {
            return Collections.unmodifiableList(((ContextRule) this.instance).getRequestedList());
        }

        public int getRequestedCount() {
            return ((ContextRule) this.instance).getRequestedCount();
        }

        public String getRequested(int i) {
            return ((ContextRule) this.instance).getRequested(i);
        }

        public ByteString getRequestedBytes(int i) {
            return ((ContextRule) this.instance).getRequestedBytes(i);
        }

        public Builder setRequested(int i, String str) {
            copyOnWrite();
            ((ContextRule) this.instance).setRequested(i, str);
            return this;
        }

        public Builder addRequested(String str) {
            copyOnWrite();
            ((ContextRule) this.instance).addRequested(str);
            return this;
        }

        public Builder addAllRequested(Iterable<String> iterable) {
            copyOnWrite();
            ((ContextRule) this.instance).addAllRequested(iterable);
            return this;
        }

        public Builder clearRequested() {
            copyOnWrite();
            ((ContextRule) this.instance).clearRequested();
            return this;
        }

        public Builder addRequestedBytes(ByteString byteString) {
            copyOnWrite();
            ((ContextRule) this.instance).addRequestedBytes(byteString);
            return this;
        }

        public List<String> getProvidedList() {
            return Collections.unmodifiableList(((ContextRule) this.instance).getProvidedList());
        }

        public int getProvidedCount() {
            return ((ContextRule) this.instance).getProvidedCount();
        }

        public String getProvided(int i) {
            return ((ContextRule) this.instance).getProvided(i);
        }

        public ByteString getProvidedBytes(int i) {
            return ((ContextRule) this.instance).getProvidedBytes(i);
        }

        public Builder setProvided(int i, String str) {
            copyOnWrite();
            ((ContextRule) this.instance).setProvided(i, str);
            return this;
        }

        public Builder addProvided(String str) {
            copyOnWrite();
            ((ContextRule) this.instance).addProvided(str);
            return this;
        }

        public Builder addAllProvided(Iterable<String> iterable) {
            copyOnWrite();
            ((ContextRule) this.instance).addAllProvided(iterable);
            return this;
        }

        public Builder clearProvided() {
            copyOnWrite();
            ((ContextRule) this.instance).clearProvided();
            return this;
        }

        public Builder addProvidedBytes(ByteString byteString) {
            copyOnWrite();
            ((ContextRule) this.instance).addProvidedBytes(byteString);
            return this;
        }
    }

    /* renamed from: com.google.api.ContextRule$1  reason: invalid class name */
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.api.ContextRule.AnonymousClass1.<clinit>():void");
        }
    }

    /* access modifiers changed from: protected */
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
            case 1:
                return new ContextRule();
            case 2:
                return DEFAULT_INSTANCE;
            case 3:
                this.requested_.makeImmutable();
                this.provided_.makeImmutable();
                return null;
            case 4:
                return new Builder((AnonymousClass1) null);
            case 5:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                ContextRule contextRule = (ContextRule) obj2;
                this.selector_ = visitor.visitString(!this.selector_.isEmpty(), this.selector_, true ^ contextRule.selector_.isEmpty(), contextRule.selector_);
                this.requested_ = visitor.visitList(this.requested_, contextRule.requested_);
                this.provided_ = visitor.visitList(this.provided_, contextRule.provided_);
                if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                    this.bitField0_ |= contextRule.bitField0_;
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
                                this.selector_ = codedInputStream.readStringRequireUtf8();
                            } else if (readTag == 18) {
                                String readStringRequireUtf8 = codedInputStream.readStringRequireUtf8();
                                if (!this.requested_.isModifiable()) {
                                    this.requested_ = GeneratedMessageLite.mutableCopy(this.requested_);
                                }
                                this.requested_.add(readStringRequireUtf8);
                            } else if (readTag == 26) {
                                String readStringRequireUtf82 = codedInputStream.readStringRequireUtf8();
                                if (!this.provided_.isModifiable()) {
                                    this.provided_ = GeneratedMessageLite.mutableCopy(this.provided_);
                                }
                                this.provided_.add(readStringRequireUtf82);
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
                    synchronized (ContextRule.class) {
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
        ContextRule contextRule = new ContextRule();
        DEFAULT_INSTANCE = contextRule;
        contextRule.makeImmutable();
    }

    public static ContextRule getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<ContextRule> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
