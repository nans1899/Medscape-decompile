package com.google.rpc;

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
public final class DebugInfo extends GeneratedMessageLite<DebugInfo, Builder> implements DebugInfoOrBuilder {
    /* access modifiers changed from: private */
    public static final DebugInfo DEFAULT_INSTANCE;
    public static final int DETAIL_FIELD_NUMBER = 2;
    private static volatile Parser<DebugInfo> PARSER = null;
    public static final int STACK_ENTRIES_FIELD_NUMBER = 1;
    private int bitField0_;
    private String detail_ = "";
    private Internal.ProtobufList<String> stackEntries_ = GeneratedMessageLite.emptyProtobufList();

    private DebugInfo() {
    }

    public List<String> getStackEntriesList() {
        return this.stackEntries_;
    }

    public int getStackEntriesCount() {
        return this.stackEntries_.size();
    }

    public String getStackEntries(int i) {
        return (String) this.stackEntries_.get(i);
    }

    public ByteString getStackEntriesBytes(int i) {
        return ByteString.copyFromUtf8((String) this.stackEntries_.get(i));
    }

    private void ensureStackEntriesIsMutable() {
        if (!this.stackEntries_.isModifiable()) {
            this.stackEntries_ = GeneratedMessageLite.mutableCopy(this.stackEntries_);
        }
    }

    /* access modifiers changed from: private */
    public void setStackEntries(int i, String str) {
        if (str != null) {
            ensureStackEntriesIsMutable();
            this.stackEntries_.set(i, str);
            return;
        }
        throw null;
    }

    /* access modifiers changed from: private */
    public void addStackEntries(String str) {
        if (str != null) {
            ensureStackEntriesIsMutable();
            this.stackEntries_.add(str);
            return;
        }
        throw null;
    }

    /* access modifiers changed from: private */
    public void addAllStackEntries(Iterable<String> iterable) {
        ensureStackEntriesIsMutable();
        AbstractMessageLite.addAll(iterable, this.stackEntries_);
    }

    /* access modifiers changed from: private */
    public void clearStackEntries() {
        this.stackEntries_ = GeneratedMessageLite.emptyProtobufList();
    }

    /* access modifiers changed from: private */
    public void addStackEntriesBytes(ByteString byteString) {
        if (byteString != null) {
            checkByteStringIsUtf8(byteString);
            ensureStackEntriesIsMutable();
            this.stackEntries_.add(byteString.toStringUtf8());
            return;
        }
        throw null;
    }

    public String getDetail() {
        return this.detail_;
    }

    public ByteString getDetailBytes() {
        return ByteString.copyFromUtf8(this.detail_);
    }

    /* access modifiers changed from: private */
    public void setDetail(String str) {
        if (str != null) {
            this.detail_ = str;
            return;
        }
        throw null;
    }

    /* access modifiers changed from: private */
    public void clearDetail() {
        this.detail_ = getDefaultInstance().getDetail();
    }

    /* access modifiers changed from: private */
    public void setDetailBytes(ByteString byteString) {
        if (byteString != null) {
            checkByteStringIsUtf8(byteString);
            this.detail_ = byteString.toStringUtf8();
            return;
        }
        throw null;
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        for (int i = 0; i < this.stackEntries_.size(); i++) {
            codedOutputStream.writeString(1, (String) this.stackEntries_.get(i));
        }
        if (!this.detail_.isEmpty()) {
            codedOutputStream.writeString(2, getDetail());
        }
    }

    public int getSerializedSize() {
        int i = this.memoizedSerializedSize;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < this.stackEntries_.size(); i3++) {
            i2 += CodedOutputStream.computeStringSizeNoTag((String) this.stackEntries_.get(i3));
        }
        int size = 0 + i2 + (getStackEntriesList().size() * 1);
        if (!this.detail_.isEmpty()) {
            size += CodedOutputStream.computeStringSize(2, getDetail());
        }
        this.memoizedSerializedSize = size;
        return size;
    }

    public static DebugInfo parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (DebugInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static DebugInfo parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (DebugInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static DebugInfo parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (DebugInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static DebugInfo parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (DebugInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static DebugInfo parseFrom(InputStream inputStream) throws IOException {
        return (DebugInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static DebugInfo parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (DebugInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static DebugInfo parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (DebugInfo) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static DebugInfo parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (DebugInfo) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static DebugInfo parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (DebugInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static DebugInfo parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (DebugInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(DebugInfo debugInfo) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(debugInfo);
    }

    /* compiled from: com.google.firebase:protolite-well-known-types@@16.0.1 */
    public static final class Builder extends GeneratedMessageLite.Builder<DebugInfo, Builder> implements DebugInfoOrBuilder {
        /* synthetic */ Builder(AnonymousClass1 r1) {
            this();
        }

        private Builder() {
            super(DebugInfo.DEFAULT_INSTANCE);
        }

        public List<String> getStackEntriesList() {
            return Collections.unmodifiableList(((DebugInfo) this.instance).getStackEntriesList());
        }

        public int getStackEntriesCount() {
            return ((DebugInfo) this.instance).getStackEntriesCount();
        }

        public String getStackEntries(int i) {
            return ((DebugInfo) this.instance).getStackEntries(i);
        }

        public ByteString getStackEntriesBytes(int i) {
            return ((DebugInfo) this.instance).getStackEntriesBytes(i);
        }

        public Builder setStackEntries(int i, String str) {
            copyOnWrite();
            ((DebugInfo) this.instance).setStackEntries(i, str);
            return this;
        }

        public Builder addStackEntries(String str) {
            copyOnWrite();
            ((DebugInfo) this.instance).addStackEntries(str);
            return this;
        }

        public Builder addAllStackEntries(Iterable<String> iterable) {
            copyOnWrite();
            ((DebugInfo) this.instance).addAllStackEntries(iterable);
            return this;
        }

        public Builder clearStackEntries() {
            copyOnWrite();
            ((DebugInfo) this.instance).clearStackEntries();
            return this;
        }

        public Builder addStackEntriesBytes(ByteString byteString) {
            copyOnWrite();
            ((DebugInfo) this.instance).addStackEntriesBytes(byteString);
            return this;
        }

        public String getDetail() {
            return ((DebugInfo) this.instance).getDetail();
        }

        public ByteString getDetailBytes() {
            return ((DebugInfo) this.instance).getDetailBytes();
        }

        public Builder setDetail(String str) {
            copyOnWrite();
            ((DebugInfo) this.instance).setDetail(str);
            return this;
        }

        public Builder clearDetail() {
            copyOnWrite();
            ((DebugInfo) this.instance).clearDetail();
            return this;
        }

        public Builder setDetailBytes(ByteString byteString) {
            copyOnWrite();
            ((DebugInfo) this.instance).setDetailBytes(byteString);
            return this;
        }
    }

    /* renamed from: com.google.rpc.DebugInfo$1  reason: invalid class name */
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.rpc.DebugInfo.AnonymousClass1.<clinit>():void");
        }
    }

    /* access modifiers changed from: protected */
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
            case 1:
                return new DebugInfo();
            case 2:
                return DEFAULT_INSTANCE;
            case 3:
                this.stackEntries_.makeImmutable();
                return null;
            case 4:
                return new Builder((AnonymousClass1) null);
            case 5:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                DebugInfo debugInfo = (DebugInfo) obj2;
                this.stackEntries_ = visitor.visitList(this.stackEntries_, debugInfo.stackEntries_);
                this.detail_ = visitor.visitString(!this.detail_.isEmpty(), this.detail_, true ^ debugInfo.detail_.isEmpty(), debugInfo.detail_);
                if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                    this.bitField0_ |= debugInfo.bitField0_;
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
                                String readStringRequireUtf8 = codedInputStream.readStringRequireUtf8();
                                if (!this.stackEntries_.isModifiable()) {
                                    this.stackEntries_ = GeneratedMessageLite.mutableCopy(this.stackEntries_);
                                }
                                this.stackEntries_.add(readStringRequireUtf8);
                            } else if (readTag == 18) {
                                this.detail_ = codedInputStream.readStringRequireUtf8();
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
                    synchronized (DebugInfo.class) {
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
        DebugInfo debugInfo = new DebugInfo();
        DEFAULT_INSTANCE = debugInfo;
        debugInfo.makeImmutable();
    }

    public static DebugInfo getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<DebugInfo> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
