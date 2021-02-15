package com.google.longrunning;

import com.google.longrunning.Operation;
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
public final class ListOperationsResponse extends GeneratedMessageLite<ListOperationsResponse, Builder> implements ListOperationsResponseOrBuilder {
    /* access modifiers changed from: private */
    public static final ListOperationsResponse DEFAULT_INSTANCE;
    public static final int NEXT_PAGE_TOKEN_FIELD_NUMBER = 2;
    public static final int OPERATIONS_FIELD_NUMBER = 1;
    private static volatile Parser<ListOperationsResponse> PARSER;
    private int bitField0_;
    private String nextPageToken_ = "";
    private Internal.ProtobufList<Operation> operations_ = emptyProtobufList();

    private ListOperationsResponse() {
    }

    public List<Operation> getOperationsList() {
        return this.operations_;
    }

    public List<? extends OperationOrBuilder> getOperationsOrBuilderList() {
        return this.operations_;
    }

    public int getOperationsCount() {
        return this.operations_.size();
    }

    public Operation getOperations(int i) {
        return (Operation) this.operations_.get(i);
    }

    public OperationOrBuilder getOperationsOrBuilder(int i) {
        return (OperationOrBuilder) this.operations_.get(i);
    }

    private void ensureOperationsIsMutable() {
        if (!this.operations_.isModifiable()) {
            this.operations_ = GeneratedMessageLite.mutableCopy(this.operations_);
        }
    }

    /* access modifiers changed from: private */
    public void setOperations(int i, Operation operation) {
        if (operation != null) {
            ensureOperationsIsMutable();
            this.operations_.set(i, operation);
            return;
        }
        throw null;
    }

    /* access modifiers changed from: private */
    public void setOperations(int i, Operation.Builder builder) {
        ensureOperationsIsMutable();
        this.operations_.set(i, (Operation) builder.build());
    }

    /* access modifiers changed from: private */
    public void addOperations(Operation operation) {
        if (operation != null) {
            ensureOperationsIsMutable();
            this.operations_.add(operation);
            return;
        }
        throw null;
    }

    /* access modifiers changed from: private */
    public void addOperations(int i, Operation operation) {
        if (operation != null) {
            ensureOperationsIsMutable();
            this.operations_.add(i, operation);
            return;
        }
        throw null;
    }

    /* access modifiers changed from: private */
    public void addOperations(Operation.Builder builder) {
        ensureOperationsIsMutable();
        this.operations_.add((Operation) builder.build());
    }

    /* access modifiers changed from: private */
    public void addOperations(int i, Operation.Builder builder) {
        ensureOperationsIsMutable();
        this.operations_.add(i, (Operation) builder.build());
    }

    /* access modifiers changed from: private */
    public void addAllOperations(Iterable<? extends Operation> iterable) {
        ensureOperationsIsMutable();
        AbstractMessageLite.addAll(iterable, this.operations_);
    }

    /* access modifiers changed from: private */
    public void clearOperations() {
        this.operations_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    public void removeOperations(int i) {
        ensureOperationsIsMutable();
        this.operations_.remove(i);
    }

    public String getNextPageToken() {
        return this.nextPageToken_;
    }

    public ByteString getNextPageTokenBytes() {
        return ByteString.copyFromUtf8(this.nextPageToken_);
    }

    /* access modifiers changed from: private */
    public void setNextPageToken(String str) {
        if (str != null) {
            this.nextPageToken_ = str;
            return;
        }
        throw null;
    }

    /* access modifiers changed from: private */
    public void clearNextPageToken() {
        this.nextPageToken_ = getDefaultInstance().getNextPageToken();
    }

    /* access modifiers changed from: private */
    public void setNextPageTokenBytes(ByteString byteString) {
        if (byteString != null) {
            checkByteStringIsUtf8(byteString);
            this.nextPageToken_ = byteString.toStringUtf8();
            return;
        }
        throw null;
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        for (int i = 0; i < this.operations_.size(); i++) {
            codedOutputStream.writeMessage(1, (MessageLite) this.operations_.get(i));
        }
        if (!this.nextPageToken_.isEmpty()) {
            codedOutputStream.writeString(2, getNextPageToken());
        }
    }

    public int getSerializedSize() {
        int i = this.memoizedSerializedSize;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < this.operations_.size(); i3++) {
            i2 += CodedOutputStream.computeMessageSize(1, (MessageLite) this.operations_.get(i3));
        }
        if (!this.nextPageToken_.isEmpty()) {
            i2 += CodedOutputStream.computeStringSize(2, getNextPageToken());
        }
        this.memoizedSerializedSize = i2;
        return i2;
    }

    public static ListOperationsResponse parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (ListOperationsResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static ListOperationsResponse parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ListOperationsResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static ListOperationsResponse parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (ListOperationsResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static ListOperationsResponse parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ListOperationsResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static ListOperationsResponse parseFrom(InputStream inputStream) throws IOException {
        return (ListOperationsResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static ListOperationsResponse parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ListOperationsResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static ListOperationsResponse parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (ListOperationsResponse) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static ListOperationsResponse parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ListOperationsResponse) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static ListOperationsResponse parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (ListOperationsResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static ListOperationsResponse parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ListOperationsResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(ListOperationsResponse listOperationsResponse) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(listOperationsResponse);
    }

    /* compiled from: com.google.firebase:protolite-well-known-types@@16.0.1 */
    public static final class Builder extends GeneratedMessageLite.Builder<ListOperationsResponse, Builder> implements ListOperationsResponseOrBuilder {
        /* synthetic */ Builder(AnonymousClass1 r1) {
            this();
        }

        private Builder() {
            super(ListOperationsResponse.DEFAULT_INSTANCE);
        }

        public List<Operation> getOperationsList() {
            return Collections.unmodifiableList(((ListOperationsResponse) this.instance).getOperationsList());
        }

        public int getOperationsCount() {
            return ((ListOperationsResponse) this.instance).getOperationsCount();
        }

        public Operation getOperations(int i) {
            return ((ListOperationsResponse) this.instance).getOperations(i);
        }

        public Builder setOperations(int i, Operation operation) {
            copyOnWrite();
            ((ListOperationsResponse) this.instance).setOperations(i, operation);
            return this;
        }

        public Builder setOperations(int i, Operation.Builder builder) {
            copyOnWrite();
            ((ListOperationsResponse) this.instance).setOperations(i, builder);
            return this;
        }

        public Builder addOperations(Operation operation) {
            copyOnWrite();
            ((ListOperationsResponse) this.instance).addOperations(operation);
            return this;
        }

        public Builder addOperations(int i, Operation operation) {
            copyOnWrite();
            ((ListOperationsResponse) this.instance).addOperations(i, operation);
            return this;
        }

        public Builder addOperations(Operation.Builder builder) {
            copyOnWrite();
            ((ListOperationsResponse) this.instance).addOperations(builder);
            return this;
        }

        public Builder addOperations(int i, Operation.Builder builder) {
            copyOnWrite();
            ((ListOperationsResponse) this.instance).addOperations(i, builder);
            return this;
        }

        public Builder addAllOperations(Iterable<? extends Operation> iterable) {
            copyOnWrite();
            ((ListOperationsResponse) this.instance).addAllOperations(iterable);
            return this;
        }

        public Builder clearOperations() {
            copyOnWrite();
            ((ListOperationsResponse) this.instance).clearOperations();
            return this;
        }

        public Builder removeOperations(int i) {
            copyOnWrite();
            ((ListOperationsResponse) this.instance).removeOperations(i);
            return this;
        }

        public String getNextPageToken() {
            return ((ListOperationsResponse) this.instance).getNextPageToken();
        }

        public ByteString getNextPageTokenBytes() {
            return ((ListOperationsResponse) this.instance).getNextPageTokenBytes();
        }

        public Builder setNextPageToken(String str) {
            copyOnWrite();
            ((ListOperationsResponse) this.instance).setNextPageToken(str);
            return this;
        }

        public Builder clearNextPageToken() {
            copyOnWrite();
            ((ListOperationsResponse) this.instance).clearNextPageToken();
            return this;
        }

        public Builder setNextPageTokenBytes(ByteString byteString) {
            copyOnWrite();
            ((ListOperationsResponse) this.instance).setNextPageTokenBytes(byteString);
            return this;
        }
    }

    /* renamed from: com.google.longrunning.ListOperationsResponse$1  reason: invalid class name */
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.longrunning.ListOperationsResponse.AnonymousClass1.<clinit>():void");
        }
    }

    /* access modifiers changed from: protected */
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
            case 1:
                return new ListOperationsResponse();
            case 2:
                return DEFAULT_INSTANCE;
            case 3:
                this.operations_.makeImmutable();
                return null;
            case 4:
                return new Builder((AnonymousClass1) null);
            case 5:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                ListOperationsResponse listOperationsResponse = (ListOperationsResponse) obj2;
                this.operations_ = visitor.visitList(this.operations_, listOperationsResponse.operations_);
                this.nextPageToken_ = visitor.visitString(!this.nextPageToken_.isEmpty(), this.nextPageToken_, true ^ listOperationsResponse.nextPageToken_.isEmpty(), listOperationsResponse.nextPageToken_);
                if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                    this.bitField0_ |= listOperationsResponse.bitField0_;
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
                                if (!this.operations_.isModifiable()) {
                                    this.operations_ = GeneratedMessageLite.mutableCopy(this.operations_);
                                }
                                this.operations_.add((Operation) codedInputStream.readMessage(Operation.parser(), extensionRegistryLite));
                            } else if (readTag == 18) {
                                this.nextPageToken_ = codedInputStream.readStringRequireUtf8();
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
                    synchronized (ListOperationsResponse.class) {
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
        ListOperationsResponse listOperationsResponse = new ListOperationsResponse();
        DEFAULT_INSTANCE = listOperationsResponse;
        listOperationsResponse.makeImmutable();
    }

    public static ListOperationsResponse getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<ListOperationsResponse> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
