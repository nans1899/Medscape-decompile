package com.google.rpc;

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
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

/* compiled from: com.google.firebase:protolite-well-known-types@@16.0.1 */
public final class BadRequest extends GeneratedMessageLite<BadRequest, Builder> implements BadRequestOrBuilder {
    /* access modifiers changed from: private */
    public static final BadRequest DEFAULT_INSTANCE;
    public static final int FIELD_VIOLATIONS_FIELD_NUMBER = 1;
    private static volatile Parser<BadRequest> PARSER;
    private Internal.ProtobufList<FieldViolation> fieldViolations_ = emptyProtobufList();

    /* compiled from: com.google.firebase:protolite-well-known-types@@16.0.1 */
    public interface FieldViolationOrBuilder extends MessageLiteOrBuilder {
        String getDescription();

        ByteString getDescriptionBytes();

        String getField();

        ByteString getFieldBytes();
    }

    private BadRequest() {
    }

    /* compiled from: com.google.firebase:protolite-well-known-types@@16.0.1 */
    public static final class FieldViolation extends GeneratedMessageLite<FieldViolation, Builder> implements FieldViolationOrBuilder {
        /* access modifiers changed from: private */
        public static final FieldViolation DEFAULT_INSTANCE;
        public static final int DESCRIPTION_FIELD_NUMBER = 2;
        public static final int FIELD_FIELD_NUMBER = 1;
        private static volatile Parser<FieldViolation> PARSER;
        private String description_ = "";
        private String field_ = "";

        private FieldViolation() {
        }

        public String getField() {
            return this.field_;
        }

        public ByteString getFieldBytes() {
            return ByteString.copyFromUtf8(this.field_);
        }

        /* access modifiers changed from: private */
        public void setField(String str) {
            if (str != null) {
                this.field_ = str;
                return;
            }
            throw null;
        }

        /* access modifiers changed from: private */
        public void clearField() {
            this.field_ = getDefaultInstance().getField();
        }

        /* access modifiers changed from: private */
        public void setFieldBytes(ByteString byteString) {
            if (byteString != null) {
                checkByteStringIsUtf8(byteString);
                this.field_ = byteString.toStringUtf8();
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

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if (!this.field_.isEmpty()) {
                codedOutputStream.writeString(1, getField());
            }
            if (!this.description_.isEmpty()) {
                codedOutputStream.writeString(2, getDescription());
            }
        }

        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            if (!this.field_.isEmpty()) {
                i2 = 0 + CodedOutputStream.computeStringSize(1, getField());
            }
            if (!this.description_.isEmpty()) {
                i2 += CodedOutputStream.computeStringSize(2, getDescription());
            }
            this.memoizedSerializedSize = i2;
            return i2;
        }

        public static FieldViolation parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (FieldViolation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static FieldViolation parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (FieldViolation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static FieldViolation parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (FieldViolation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static FieldViolation parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (FieldViolation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static FieldViolation parseFrom(InputStream inputStream) throws IOException {
            return (FieldViolation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static FieldViolation parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (FieldViolation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static FieldViolation parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (FieldViolation) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static FieldViolation parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (FieldViolation) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static FieldViolation parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (FieldViolation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static FieldViolation parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (FieldViolation) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(FieldViolation fieldViolation) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(fieldViolation);
        }

        /* compiled from: com.google.firebase:protolite-well-known-types@@16.0.1 */
        public static final class Builder extends GeneratedMessageLite.Builder<FieldViolation, Builder> implements FieldViolationOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 r1) {
                this();
            }

            private Builder() {
                super(FieldViolation.DEFAULT_INSTANCE);
            }

            public String getField() {
                return ((FieldViolation) this.instance).getField();
            }

            public ByteString getFieldBytes() {
                return ((FieldViolation) this.instance).getFieldBytes();
            }

            public Builder setField(String str) {
                copyOnWrite();
                ((FieldViolation) this.instance).setField(str);
                return this;
            }

            public Builder clearField() {
                copyOnWrite();
                ((FieldViolation) this.instance).clearField();
                return this;
            }

            public Builder setFieldBytes(ByteString byteString) {
                copyOnWrite();
                ((FieldViolation) this.instance).setFieldBytes(byteString);
                return this;
            }

            public String getDescription() {
                return ((FieldViolation) this.instance).getDescription();
            }

            public ByteString getDescriptionBytes() {
                return ((FieldViolation) this.instance).getDescriptionBytes();
            }

            public Builder setDescription(String str) {
                copyOnWrite();
                ((FieldViolation) this.instance).setDescription(str);
                return this;
            }

            public Builder clearDescription() {
                copyOnWrite();
                ((FieldViolation) this.instance).clearDescription();
                return this;
            }

            public Builder setDescriptionBytes(ByteString byteString) {
                copyOnWrite();
                ((FieldViolation) this.instance).setDescriptionBytes(byteString);
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
                case 1:
                    return new FieldViolation();
                case 2:
                    return DEFAULT_INSTANCE;
                case 3:
                    return null;
                case 4:
                    return new Builder((AnonymousClass1) null);
                case 5:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                    FieldViolation fieldViolation = (FieldViolation) obj2;
                    this.field_ = visitor.visitString(!this.field_.isEmpty(), this.field_, !fieldViolation.field_.isEmpty(), fieldViolation.field_);
                    this.description_ = visitor.visitString(!this.description_.isEmpty(), this.description_, true ^ fieldViolation.description_.isEmpty(), fieldViolation.description_);
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
                                    this.field_ = codedInputStream.readStringRequireUtf8();
                                } else if (readTag == 18) {
                                    this.description_ = codedInputStream.readStringRequireUtf8();
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
                        synchronized (FieldViolation.class) {
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
            FieldViolation fieldViolation = new FieldViolation();
            DEFAULT_INSTANCE = fieldViolation;
            fieldViolation.makeImmutable();
        }

        public static FieldViolation getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<FieldViolation> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    /* renamed from: com.google.rpc.BadRequest$1  reason: invalid class name */
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.rpc.BadRequest.AnonymousClass1.<clinit>():void");
        }
    }

    public List<FieldViolation> getFieldViolationsList() {
        return this.fieldViolations_;
    }

    public List<? extends FieldViolationOrBuilder> getFieldViolationsOrBuilderList() {
        return this.fieldViolations_;
    }

    public int getFieldViolationsCount() {
        return this.fieldViolations_.size();
    }

    public FieldViolation getFieldViolations(int i) {
        return (FieldViolation) this.fieldViolations_.get(i);
    }

    public FieldViolationOrBuilder getFieldViolationsOrBuilder(int i) {
        return (FieldViolationOrBuilder) this.fieldViolations_.get(i);
    }

    private void ensureFieldViolationsIsMutable() {
        if (!this.fieldViolations_.isModifiable()) {
            this.fieldViolations_ = GeneratedMessageLite.mutableCopy(this.fieldViolations_);
        }
    }

    /* access modifiers changed from: private */
    public void setFieldViolations(int i, FieldViolation fieldViolation) {
        if (fieldViolation != null) {
            ensureFieldViolationsIsMutable();
            this.fieldViolations_.set(i, fieldViolation);
            return;
        }
        throw null;
    }

    /* access modifiers changed from: private */
    public void setFieldViolations(int i, FieldViolation.Builder builder) {
        ensureFieldViolationsIsMutable();
        this.fieldViolations_.set(i, (FieldViolation) builder.build());
    }

    /* access modifiers changed from: private */
    public void addFieldViolations(FieldViolation fieldViolation) {
        if (fieldViolation != null) {
            ensureFieldViolationsIsMutable();
            this.fieldViolations_.add(fieldViolation);
            return;
        }
        throw null;
    }

    /* access modifiers changed from: private */
    public void addFieldViolations(int i, FieldViolation fieldViolation) {
        if (fieldViolation != null) {
            ensureFieldViolationsIsMutable();
            this.fieldViolations_.add(i, fieldViolation);
            return;
        }
        throw null;
    }

    /* access modifiers changed from: private */
    public void addFieldViolations(FieldViolation.Builder builder) {
        ensureFieldViolationsIsMutable();
        this.fieldViolations_.add((FieldViolation) builder.build());
    }

    /* access modifiers changed from: private */
    public void addFieldViolations(int i, FieldViolation.Builder builder) {
        ensureFieldViolationsIsMutable();
        this.fieldViolations_.add(i, (FieldViolation) builder.build());
    }

    /* access modifiers changed from: private */
    public void addAllFieldViolations(Iterable<? extends FieldViolation> iterable) {
        ensureFieldViolationsIsMutable();
        AbstractMessageLite.addAll(iterable, this.fieldViolations_);
    }

    /* access modifiers changed from: private */
    public void clearFieldViolations() {
        this.fieldViolations_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    public void removeFieldViolations(int i) {
        ensureFieldViolationsIsMutable();
        this.fieldViolations_.remove(i);
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        for (int i = 0; i < this.fieldViolations_.size(); i++) {
            codedOutputStream.writeMessage(1, (MessageLite) this.fieldViolations_.get(i));
        }
    }

    public int getSerializedSize() {
        int i = this.memoizedSerializedSize;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < this.fieldViolations_.size(); i3++) {
            i2 += CodedOutputStream.computeMessageSize(1, (MessageLite) this.fieldViolations_.get(i3));
        }
        this.memoizedSerializedSize = i2;
        return i2;
    }

    public static BadRequest parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (BadRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static BadRequest parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (BadRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static BadRequest parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (BadRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static BadRequest parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (BadRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static BadRequest parseFrom(InputStream inputStream) throws IOException {
        return (BadRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static BadRequest parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (BadRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static BadRequest parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (BadRequest) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static BadRequest parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (BadRequest) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static BadRequest parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (BadRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static BadRequest parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (BadRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(BadRequest badRequest) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(badRequest);
    }

    /* compiled from: com.google.firebase:protolite-well-known-types@@16.0.1 */
    public static final class Builder extends GeneratedMessageLite.Builder<BadRequest, Builder> implements BadRequestOrBuilder {
        /* synthetic */ Builder(AnonymousClass1 r1) {
            this();
        }

        private Builder() {
            super(BadRequest.DEFAULT_INSTANCE);
        }

        public List<FieldViolation> getFieldViolationsList() {
            return Collections.unmodifiableList(((BadRequest) this.instance).getFieldViolationsList());
        }

        public int getFieldViolationsCount() {
            return ((BadRequest) this.instance).getFieldViolationsCount();
        }

        public FieldViolation getFieldViolations(int i) {
            return ((BadRequest) this.instance).getFieldViolations(i);
        }

        public Builder setFieldViolations(int i, FieldViolation fieldViolation) {
            copyOnWrite();
            ((BadRequest) this.instance).setFieldViolations(i, fieldViolation);
            return this;
        }

        public Builder setFieldViolations(int i, FieldViolation.Builder builder) {
            copyOnWrite();
            ((BadRequest) this.instance).setFieldViolations(i, builder);
            return this;
        }

        public Builder addFieldViolations(FieldViolation fieldViolation) {
            copyOnWrite();
            ((BadRequest) this.instance).addFieldViolations(fieldViolation);
            return this;
        }

        public Builder addFieldViolations(int i, FieldViolation fieldViolation) {
            copyOnWrite();
            ((BadRequest) this.instance).addFieldViolations(i, fieldViolation);
            return this;
        }

        public Builder addFieldViolations(FieldViolation.Builder builder) {
            copyOnWrite();
            ((BadRequest) this.instance).addFieldViolations(builder);
            return this;
        }

        public Builder addFieldViolations(int i, FieldViolation.Builder builder) {
            copyOnWrite();
            ((BadRequest) this.instance).addFieldViolations(i, builder);
            return this;
        }

        public Builder addAllFieldViolations(Iterable<? extends FieldViolation> iterable) {
            copyOnWrite();
            ((BadRequest) this.instance).addAllFieldViolations(iterable);
            return this;
        }

        public Builder clearFieldViolations() {
            copyOnWrite();
            ((BadRequest) this.instance).clearFieldViolations();
            return this;
        }

        public Builder removeFieldViolations(int i) {
            copyOnWrite();
            ((BadRequest) this.instance).removeFieldViolations(i);
            return this;
        }
    }

    /* access modifiers changed from: protected */
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
            case 1:
                return new BadRequest();
            case 2:
                return DEFAULT_INSTANCE;
            case 3:
                this.fieldViolations_.makeImmutable();
                return null;
            case 4:
                return new Builder((AnonymousClass1) null);
            case 5:
                this.fieldViolations_ = ((GeneratedMessageLite.Visitor) obj).visitList(this.fieldViolations_, ((BadRequest) obj2).fieldViolations_);
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
                                if (!this.fieldViolations_.isModifiable()) {
                                    this.fieldViolations_ = GeneratedMessageLite.mutableCopy(this.fieldViolations_);
                                }
                                this.fieldViolations_.add((FieldViolation) codedInputStream.readMessage(FieldViolation.parser(), extensionRegistryLite));
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
                    synchronized (BadRequest.class) {
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
        BadRequest badRequest = new BadRequest();
        DEFAULT_INSTANCE = badRequest;
        badRequest.makeImmutable();
    }

    public static BadRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<BadRequest> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
