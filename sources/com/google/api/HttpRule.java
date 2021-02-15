package com.google.api;

import com.google.api.CustomHttpPattern;
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
public final class HttpRule extends GeneratedMessageLite<HttpRule, Builder> implements HttpRuleOrBuilder {
    public static final int ADDITIONAL_BINDINGS_FIELD_NUMBER = 11;
    public static final int BODY_FIELD_NUMBER = 7;
    public static final int CUSTOM_FIELD_NUMBER = 8;
    /* access modifiers changed from: private */
    public static final HttpRule DEFAULT_INSTANCE;
    public static final int DELETE_FIELD_NUMBER = 5;
    public static final int GET_FIELD_NUMBER = 2;
    private static volatile Parser<HttpRule> PARSER = null;
    public static final int PATCH_FIELD_NUMBER = 6;
    public static final int POST_FIELD_NUMBER = 4;
    public static final int PUT_FIELD_NUMBER = 3;
    public static final int SELECTOR_FIELD_NUMBER = 1;
    private Internal.ProtobufList<HttpRule> additionalBindings_ = emptyProtobufList();
    private int bitField0_;
    private String body_ = "";
    private int patternCase_ = 0;
    private Object pattern_;
    private String selector_ = "";

    private HttpRule() {
    }

    /* compiled from: com.google.firebase:protolite-well-known-types@@16.0.1 */
    public enum PatternCase implements Internal.EnumLite {
        GET(2),
        PUT(3),
        POST(4),
        DELETE(5),
        PATCH(6),
        CUSTOM(8),
        PATTERN_NOT_SET(0);
        
        private final int value;

        private PatternCase(int i) {
            this.value = i;
        }

        @Deprecated
        public static PatternCase valueOf(int i) {
            return forNumber(i);
        }

        public static PatternCase forNumber(int i) {
            if (i == 0) {
                return PATTERN_NOT_SET;
            }
            if (i == 8) {
                return CUSTOM;
            }
            if (i == 2) {
                return GET;
            }
            if (i == 3) {
                return PUT;
            }
            if (i == 4) {
                return POST;
            }
            if (i == 5) {
                return DELETE;
            }
            if (i != 6) {
                return null;
            }
            return PATCH;
        }

        public int getNumber() {
            return this.value;
        }
    }

    public PatternCase getPatternCase() {
        return PatternCase.forNumber(this.patternCase_);
    }

    /* access modifiers changed from: private */
    public void clearPattern() {
        this.patternCase_ = 0;
        this.pattern_ = null;
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

    public String getGet() {
        return this.patternCase_ == 2 ? (String) this.pattern_ : "";
    }

    public ByteString getGetBytes() {
        return ByteString.copyFromUtf8(this.patternCase_ == 2 ? (String) this.pattern_ : "");
    }

    /* access modifiers changed from: private */
    public void setGet(String str) {
        if (str != null) {
            this.patternCase_ = 2;
            this.pattern_ = str;
            return;
        }
        throw null;
    }

    /* access modifiers changed from: private */
    public void clearGet() {
        if (this.patternCase_ == 2) {
            this.patternCase_ = 0;
            this.pattern_ = null;
        }
    }

    /* access modifiers changed from: private */
    public void setGetBytes(ByteString byteString) {
        if (byteString != null) {
            checkByteStringIsUtf8(byteString);
            this.patternCase_ = 2;
            this.pattern_ = byteString.toStringUtf8();
            return;
        }
        throw null;
    }

    public String getPut() {
        return this.patternCase_ == 3 ? (String) this.pattern_ : "";
    }

    public ByteString getPutBytes() {
        return ByteString.copyFromUtf8(this.patternCase_ == 3 ? (String) this.pattern_ : "");
    }

    /* access modifiers changed from: private */
    public void setPut(String str) {
        if (str != null) {
            this.patternCase_ = 3;
            this.pattern_ = str;
            return;
        }
        throw null;
    }

    /* access modifiers changed from: private */
    public void clearPut() {
        if (this.patternCase_ == 3) {
            this.patternCase_ = 0;
            this.pattern_ = null;
        }
    }

    /* access modifiers changed from: private */
    public void setPutBytes(ByteString byteString) {
        if (byteString != null) {
            checkByteStringIsUtf8(byteString);
            this.patternCase_ = 3;
            this.pattern_ = byteString.toStringUtf8();
            return;
        }
        throw null;
    }

    public String getPost() {
        return this.patternCase_ == 4 ? (String) this.pattern_ : "";
    }

    public ByteString getPostBytes() {
        return ByteString.copyFromUtf8(this.patternCase_ == 4 ? (String) this.pattern_ : "");
    }

    /* access modifiers changed from: private */
    public void setPost(String str) {
        if (str != null) {
            this.patternCase_ = 4;
            this.pattern_ = str;
            return;
        }
        throw null;
    }

    /* access modifiers changed from: private */
    public void clearPost() {
        if (this.patternCase_ == 4) {
            this.patternCase_ = 0;
            this.pattern_ = null;
        }
    }

    /* access modifiers changed from: private */
    public void setPostBytes(ByteString byteString) {
        if (byteString != null) {
            checkByteStringIsUtf8(byteString);
            this.patternCase_ = 4;
            this.pattern_ = byteString.toStringUtf8();
            return;
        }
        throw null;
    }

    public String getDelete() {
        return this.patternCase_ == 5 ? (String) this.pattern_ : "";
    }

    public ByteString getDeleteBytes() {
        return ByteString.copyFromUtf8(this.patternCase_ == 5 ? (String) this.pattern_ : "");
    }

    /* access modifiers changed from: private */
    public void setDelete(String str) {
        if (str != null) {
            this.patternCase_ = 5;
            this.pattern_ = str;
            return;
        }
        throw null;
    }

    /* access modifiers changed from: private */
    public void clearDelete() {
        if (this.patternCase_ == 5) {
            this.patternCase_ = 0;
            this.pattern_ = null;
        }
    }

    /* access modifiers changed from: private */
    public void setDeleteBytes(ByteString byteString) {
        if (byteString != null) {
            checkByteStringIsUtf8(byteString);
            this.patternCase_ = 5;
            this.pattern_ = byteString.toStringUtf8();
            return;
        }
        throw null;
    }

    public String getPatch() {
        return this.patternCase_ == 6 ? (String) this.pattern_ : "";
    }

    public ByteString getPatchBytes() {
        return ByteString.copyFromUtf8(this.patternCase_ == 6 ? (String) this.pattern_ : "");
    }

    /* access modifiers changed from: private */
    public void setPatch(String str) {
        if (str != null) {
            this.patternCase_ = 6;
            this.pattern_ = str;
            return;
        }
        throw null;
    }

    /* access modifiers changed from: private */
    public void clearPatch() {
        if (this.patternCase_ == 6) {
            this.patternCase_ = 0;
            this.pattern_ = null;
        }
    }

    /* access modifiers changed from: private */
    public void setPatchBytes(ByteString byteString) {
        if (byteString != null) {
            checkByteStringIsUtf8(byteString);
            this.patternCase_ = 6;
            this.pattern_ = byteString.toStringUtf8();
            return;
        }
        throw null;
    }

    public CustomHttpPattern getCustom() {
        if (this.patternCase_ == 8) {
            return (CustomHttpPattern) this.pattern_;
        }
        return CustomHttpPattern.getDefaultInstance();
    }

    /* access modifiers changed from: private */
    public void setCustom(CustomHttpPattern customHttpPattern) {
        if (customHttpPattern != null) {
            this.pattern_ = customHttpPattern;
            this.patternCase_ = 8;
            return;
        }
        throw null;
    }

    /* access modifiers changed from: private */
    public void setCustom(CustomHttpPattern.Builder builder) {
        this.pattern_ = builder.build();
        this.patternCase_ = 8;
    }

    /* access modifiers changed from: private */
    public void mergeCustom(CustomHttpPattern customHttpPattern) {
        if (this.patternCase_ != 8 || this.pattern_ == CustomHttpPattern.getDefaultInstance()) {
            this.pattern_ = customHttpPattern;
        } else {
            this.pattern_ = ((CustomHttpPattern.Builder) CustomHttpPattern.newBuilder((CustomHttpPattern) this.pattern_).mergeFrom(customHttpPattern)).buildPartial();
        }
        this.patternCase_ = 8;
    }

    /* access modifiers changed from: private */
    public void clearCustom() {
        if (this.patternCase_ == 8) {
            this.patternCase_ = 0;
            this.pattern_ = null;
        }
    }

    public String getBody() {
        return this.body_;
    }

    public ByteString getBodyBytes() {
        return ByteString.copyFromUtf8(this.body_);
    }

    /* access modifiers changed from: private */
    public void setBody(String str) {
        if (str != null) {
            this.body_ = str;
            return;
        }
        throw null;
    }

    /* access modifiers changed from: private */
    public void clearBody() {
        this.body_ = getDefaultInstance().getBody();
    }

    /* access modifiers changed from: private */
    public void setBodyBytes(ByteString byteString) {
        if (byteString != null) {
            checkByteStringIsUtf8(byteString);
            this.body_ = byteString.toStringUtf8();
            return;
        }
        throw null;
    }

    public List<HttpRule> getAdditionalBindingsList() {
        return this.additionalBindings_;
    }

    public List<? extends HttpRuleOrBuilder> getAdditionalBindingsOrBuilderList() {
        return this.additionalBindings_;
    }

    public int getAdditionalBindingsCount() {
        return this.additionalBindings_.size();
    }

    public HttpRule getAdditionalBindings(int i) {
        return (HttpRule) this.additionalBindings_.get(i);
    }

    public HttpRuleOrBuilder getAdditionalBindingsOrBuilder(int i) {
        return (HttpRuleOrBuilder) this.additionalBindings_.get(i);
    }

    private void ensureAdditionalBindingsIsMutable() {
        if (!this.additionalBindings_.isModifiable()) {
            this.additionalBindings_ = GeneratedMessageLite.mutableCopy(this.additionalBindings_);
        }
    }

    /* access modifiers changed from: private */
    public void setAdditionalBindings(int i, HttpRule httpRule) {
        if (httpRule != null) {
            ensureAdditionalBindingsIsMutable();
            this.additionalBindings_.set(i, httpRule);
            return;
        }
        throw null;
    }

    /* access modifiers changed from: private */
    public void setAdditionalBindings(int i, Builder builder) {
        ensureAdditionalBindingsIsMutable();
        this.additionalBindings_.set(i, (HttpRule) builder.build());
    }

    /* access modifiers changed from: private */
    public void addAdditionalBindings(HttpRule httpRule) {
        if (httpRule != null) {
            ensureAdditionalBindingsIsMutable();
            this.additionalBindings_.add(httpRule);
            return;
        }
        throw null;
    }

    /* access modifiers changed from: private */
    public void addAdditionalBindings(int i, HttpRule httpRule) {
        if (httpRule != null) {
            ensureAdditionalBindingsIsMutable();
            this.additionalBindings_.add(i, httpRule);
            return;
        }
        throw null;
    }

    /* access modifiers changed from: private */
    public void addAdditionalBindings(Builder builder) {
        ensureAdditionalBindingsIsMutable();
        this.additionalBindings_.add((HttpRule) builder.build());
    }

    /* access modifiers changed from: private */
    public void addAdditionalBindings(int i, Builder builder) {
        ensureAdditionalBindingsIsMutable();
        this.additionalBindings_.add(i, (HttpRule) builder.build());
    }

    /* access modifiers changed from: private */
    public void addAllAdditionalBindings(Iterable<? extends HttpRule> iterable) {
        ensureAdditionalBindingsIsMutable();
        AbstractMessageLite.addAll(iterable, this.additionalBindings_);
    }

    /* access modifiers changed from: private */
    public void clearAdditionalBindings() {
        this.additionalBindings_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    public void removeAdditionalBindings(int i) {
        ensureAdditionalBindingsIsMutable();
        this.additionalBindings_.remove(i);
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        if (!this.selector_.isEmpty()) {
            codedOutputStream.writeString(1, getSelector());
        }
        if (this.patternCase_ == 2) {
            codedOutputStream.writeString(2, getGet());
        }
        if (this.patternCase_ == 3) {
            codedOutputStream.writeString(3, getPut());
        }
        if (this.patternCase_ == 4) {
            codedOutputStream.writeString(4, getPost());
        }
        if (this.patternCase_ == 5) {
            codedOutputStream.writeString(5, getDelete());
        }
        if (this.patternCase_ == 6) {
            codedOutputStream.writeString(6, getPatch());
        }
        if (!this.body_.isEmpty()) {
            codedOutputStream.writeString(7, getBody());
        }
        if (this.patternCase_ == 8) {
            codedOutputStream.writeMessage(8, (CustomHttpPattern) this.pattern_);
        }
        for (int i = 0; i < this.additionalBindings_.size(); i++) {
            codedOutputStream.writeMessage(11, (MessageLite) this.additionalBindings_.get(i));
        }
    }

    public int getSerializedSize() {
        int i = this.memoizedSerializedSize;
        if (i != -1) {
            return i;
        }
        int computeStringSize = !this.selector_.isEmpty() ? CodedOutputStream.computeStringSize(1, getSelector()) + 0 : 0;
        if (this.patternCase_ == 2) {
            computeStringSize += CodedOutputStream.computeStringSize(2, getGet());
        }
        if (this.patternCase_ == 3) {
            computeStringSize += CodedOutputStream.computeStringSize(3, getPut());
        }
        if (this.patternCase_ == 4) {
            computeStringSize += CodedOutputStream.computeStringSize(4, getPost());
        }
        if (this.patternCase_ == 5) {
            computeStringSize += CodedOutputStream.computeStringSize(5, getDelete());
        }
        if (this.patternCase_ == 6) {
            computeStringSize += CodedOutputStream.computeStringSize(6, getPatch());
        }
        if (!this.body_.isEmpty()) {
            computeStringSize += CodedOutputStream.computeStringSize(7, getBody());
        }
        if (this.patternCase_ == 8) {
            computeStringSize += CodedOutputStream.computeMessageSize(8, (CustomHttpPattern) this.pattern_);
        }
        for (int i2 = 0; i2 < this.additionalBindings_.size(); i2++) {
            computeStringSize += CodedOutputStream.computeMessageSize(11, (MessageLite) this.additionalBindings_.get(i2));
        }
        this.memoizedSerializedSize = computeStringSize;
        return computeStringSize;
    }

    public static HttpRule parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (HttpRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static HttpRule parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (HttpRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static HttpRule parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (HttpRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static HttpRule parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (HttpRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static HttpRule parseFrom(InputStream inputStream) throws IOException {
        return (HttpRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static HttpRule parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (HttpRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static HttpRule parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (HttpRule) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static HttpRule parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (HttpRule) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static HttpRule parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (HttpRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static HttpRule parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (HttpRule) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(HttpRule httpRule) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(httpRule);
    }

    /* compiled from: com.google.firebase:protolite-well-known-types@@16.0.1 */
    public static final class Builder extends GeneratedMessageLite.Builder<HttpRule, Builder> implements HttpRuleOrBuilder {
        /* synthetic */ Builder(AnonymousClass1 r1) {
            this();
        }

        private Builder() {
            super(HttpRule.DEFAULT_INSTANCE);
        }

        public PatternCase getPatternCase() {
            return ((HttpRule) this.instance).getPatternCase();
        }

        public Builder clearPattern() {
            copyOnWrite();
            ((HttpRule) this.instance).clearPattern();
            return this;
        }

        public String getSelector() {
            return ((HttpRule) this.instance).getSelector();
        }

        public ByteString getSelectorBytes() {
            return ((HttpRule) this.instance).getSelectorBytes();
        }

        public Builder setSelector(String str) {
            copyOnWrite();
            ((HttpRule) this.instance).setSelector(str);
            return this;
        }

        public Builder clearSelector() {
            copyOnWrite();
            ((HttpRule) this.instance).clearSelector();
            return this;
        }

        public Builder setSelectorBytes(ByteString byteString) {
            copyOnWrite();
            ((HttpRule) this.instance).setSelectorBytes(byteString);
            return this;
        }

        public String getGet() {
            return ((HttpRule) this.instance).getGet();
        }

        public ByteString getGetBytes() {
            return ((HttpRule) this.instance).getGetBytes();
        }

        public Builder setGet(String str) {
            copyOnWrite();
            ((HttpRule) this.instance).setGet(str);
            return this;
        }

        public Builder clearGet() {
            copyOnWrite();
            ((HttpRule) this.instance).clearGet();
            return this;
        }

        public Builder setGetBytes(ByteString byteString) {
            copyOnWrite();
            ((HttpRule) this.instance).setGetBytes(byteString);
            return this;
        }

        public String getPut() {
            return ((HttpRule) this.instance).getPut();
        }

        public ByteString getPutBytes() {
            return ((HttpRule) this.instance).getPutBytes();
        }

        public Builder setPut(String str) {
            copyOnWrite();
            ((HttpRule) this.instance).setPut(str);
            return this;
        }

        public Builder clearPut() {
            copyOnWrite();
            ((HttpRule) this.instance).clearPut();
            return this;
        }

        public Builder setPutBytes(ByteString byteString) {
            copyOnWrite();
            ((HttpRule) this.instance).setPutBytes(byteString);
            return this;
        }

        public String getPost() {
            return ((HttpRule) this.instance).getPost();
        }

        public ByteString getPostBytes() {
            return ((HttpRule) this.instance).getPostBytes();
        }

        public Builder setPost(String str) {
            copyOnWrite();
            ((HttpRule) this.instance).setPost(str);
            return this;
        }

        public Builder clearPost() {
            copyOnWrite();
            ((HttpRule) this.instance).clearPost();
            return this;
        }

        public Builder setPostBytes(ByteString byteString) {
            copyOnWrite();
            ((HttpRule) this.instance).setPostBytes(byteString);
            return this;
        }

        public String getDelete() {
            return ((HttpRule) this.instance).getDelete();
        }

        public ByteString getDeleteBytes() {
            return ((HttpRule) this.instance).getDeleteBytes();
        }

        public Builder setDelete(String str) {
            copyOnWrite();
            ((HttpRule) this.instance).setDelete(str);
            return this;
        }

        public Builder clearDelete() {
            copyOnWrite();
            ((HttpRule) this.instance).clearDelete();
            return this;
        }

        public Builder setDeleteBytes(ByteString byteString) {
            copyOnWrite();
            ((HttpRule) this.instance).setDeleteBytes(byteString);
            return this;
        }

        public String getPatch() {
            return ((HttpRule) this.instance).getPatch();
        }

        public ByteString getPatchBytes() {
            return ((HttpRule) this.instance).getPatchBytes();
        }

        public Builder setPatch(String str) {
            copyOnWrite();
            ((HttpRule) this.instance).setPatch(str);
            return this;
        }

        public Builder clearPatch() {
            copyOnWrite();
            ((HttpRule) this.instance).clearPatch();
            return this;
        }

        public Builder setPatchBytes(ByteString byteString) {
            copyOnWrite();
            ((HttpRule) this.instance).setPatchBytes(byteString);
            return this;
        }

        public CustomHttpPattern getCustom() {
            return ((HttpRule) this.instance).getCustom();
        }

        public Builder setCustom(CustomHttpPattern customHttpPattern) {
            copyOnWrite();
            ((HttpRule) this.instance).setCustom(customHttpPattern);
            return this;
        }

        public Builder setCustom(CustomHttpPattern.Builder builder) {
            copyOnWrite();
            ((HttpRule) this.instance).setCustom(builder);
            return this;
        }

        public Builder mergeCustom(CustomHttpPattern customHttpPattern) {
            copyOnWrite();
            ((HttpRule) this.instance).mergeCustom(customHttpPattern);
            return this;
        }

        public Builder clearCustom() {
            copyOnWrite();
            ((HttpRule) this.instance).clearCustom();
            return this;
        }

        public String getBody() {
            return ((HttpRule) this.instance).getBody();
        }

        public ByteString getBodyBytes() {
            return ((HttpRule) this.instance).getBodyBytes();
        }

        public Builder setBody(String str) {
            copyOnWrite();
            ((HttpRule) this.instance).setBody(str);
            return this;
        }

        public Builder clearBody() {
            copyOnWrite();
            ((HttpRule) this.instance).clearBody();
            return this;
        }

        public Builder setBodyBytes(ByteString byteString) {
            copyOnWrite();
            ((HttpRule) this.instance).setBodyBytes(byteString);
            return this;
        }

        public List<HttpRule> getAdditionalBindingsList() {
            return Collections.unmodifiableList(((HttpRule) this.instance).getAdditionalBindingsList());
        }

        public int getAdditionalBindingsCount() {
            return ((HttpRule) this.instance).getAdditionalBindingsCount();
        }

        public HttpRule getAdditionalBindings(int i) {
            return ((HttpRule) this.instance).getAdditionalBindings(i);
        }

        public Builder setAdditionalBindings(int i, HttpRule httpRule) {
            copyOnWrite();
            ((HttpRule) this.instance).setAdditionalBindings(i, httpRule);
            return this;
        }

        public Builder setAdditionalBindings(int i, Builder builder) {
            copyOnWrite();
            ((HttpRule) this.instance).setAdditionalBindings(i, builder);
            return this;
        }

        public Builder addAdditionalBindings(HttpRule httpRule) {
            copyOnWrite();
            ((HttpRule) this.instance).addAdditionalBindings(httpRule);
            return this;
        }

        public Builder addAdditionalBindings(int i, HttpRule httpRule) {
            copyOnWrite();
            ((HttpRule) this.instance).addAdditionalBindings(i, httpRule);
            return this;
        }

        public Builder addAdditionalBindings(Builder builder) {
            copyOnWrite();
            ((HttpRule) this.instance).addAdditionalBindings(builder);
            return this;
        }

        public Builder addAdditionalBindings(int i, Builder builder) {
            copyOnWrite();
            ((HttpRule) this.instance).addAdditionalBindings(i, builder);
            return this;
        }

        public Builder addAllAdditionalBindings(Iterable<? extends HttpRule> iterable) {
            copyOnWrite();
            ((HttpRule) this.instance).addAllAdditionalBindings(iterable);
            return this;
        }

        public Builder clearAdditionalBindings() {
            copyOnWrite();
            ((HttpRule) this.instance).clearAdditionalBindings();
            return this;
        }

        public Builder removeAdditionalBindings(int i) {
            copyOnWrite();
            ((HttpRule) this.instance).removeAdditionalBindings(i);
            return this;
        }
    }

    /* renamed from: com.google.api.HttpRule$1  reason: invalid class name */
    /* compiled from: com.google.firebase:protolite-well-known-types@@16.0.1 */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$api$HttpRule$PatternCase;
        static final /* synthetic */ int[] $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke;

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
                com.google.api.HttpRule$PatternCase[] r7 = com.google.api.HttpRule.PatternCase.values()
                int r7 = r7.length
                int[] r7 = new int[r7]
                $SwitchMap$com$google$api$HttpRule$PatternCase = r7
                com.google.api.HttpRule$PatternCase r8 = com.google.api.HttpRule.PatternCase.GET     // Catch:{ NoSuchFieldError -> 0x0071 }
                int r8 = r8.ordinal()     // Catch:{ NoSuchFieldError -> 0x0071 }
                r7[r8] = r1     // Catch:{ NoSuchFieldError -> 0x0071 }
            L_0x0071:
                int[] r1 = $SwitchMap$com$google$api$HttpRule$PatternCase     // Catch:{ NoSuchFieldError -> 0x007b }
                com.google.api.HttpRule$PatternCase r7 = com.google.api.HttpRule.PatternCase.PUT     // Catch:{ NoSuchFieldError -> 0x007b }
                int r7 = r7.ordinal()     // Catch:{ NoSuchFieldError -> 0x007b }
                r1[r7] = r0     // Catch:{ NoSuchFieldError -> 0x007b }
            L_0x007b:
                int[] r0 = $SwitchMap$com$google$api$HttpRule$PatternCase     // Catch:{ NoSuchFieldError -> 0x0085 }
                com.google.api.HttpRule$PatternCase r1 = com.google.api.HttpRule.PatternCase.POST     // Catch:{ NoSuchFieldError -> 0x0085 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0085 }
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0085 }
            L_0x0085:
                int[] r0 = $SwitchMap$com$google$api$HttpRule$PatternCase     // Catch:{ NoSuchFieldError -> 0x008f }
                com.google.api.HttpRule$PatternCase r1 = com.google.api.HttpRule.PatternCase.DELETE     // Catch:{ NoSuchFieldError -> 0x008f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x008f }
                r0[r1] = r3     // Catch:{ NoSuchFieldError -> 0x008f }
            L_0x008f:
                int[] r0 = $SwitchMap$com$google$api$HttpRule$PatternCase     // Catch:{ NoSuchFieldError -> 0x0099 }
                com.google.api.HttpRule$PatternCase r1 = com.google.api.HttpRule.PatternCase.PATCH     // Catch:{ NoSuchFieldError -> 0x0099 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0099 }
                r0[r1] = r4     // Catch:{ NoSuchFieldError -> 0x0099 }
            L_0x0099:
                int[] r0 = $SwitchMap$com$google$api$HttpRule$PatternCase     // Catch:{ NoSuchFieldError -> 0x00a3 }
                com.google.api.HttpRule$PatternCase r1 = com.google.api.HttpRule.PatternCase.CUSTOM     // Catch:{ NoSuchFieldError -> 0x00a3 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00a3 }
                r0[r1] = r5     // Catch:{ NoSuchFieldError -> 0x00a3 }
            L_0x00a3:
                int[] r0 = $SwitchMap$com$google$api$HttpRule$PatternCase     // Catch:{ NoSuchFieldError -> 0x00ad }
                com.google.api.HttpRule$PatternCase r1 = com.google.api.HttpRule.PatternCase.PATTERN_NOT_SET     // Catch:{ NoSuchFieldError -> 0x00ad }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x00ad }
                r0[r1] = r6     // Catch:{ NoSuchFieldError -> 0x00ad }
            L_0x00ad:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.api.HttpRule.AnonymousClass1.<clinit>():void");
        }
    }

    /* access modifiers changed from: protected */
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        boolean z = false;
        switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
            case 1:
                return new HttpRule();
            case 2:
                return DEFAULT_INSTANCE;
            case 3:
                this.additionalBindings_.makeImmutable();
                return null;
            case 4:
                return new Builder((AnonymousClass1) null);
            case 5:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                HttpRule httpRule = (HttpRule) obj2;
                this.selector_ = visitor.visitString(!this.selector_.isEmpty(), this.selector_, !httpRule.selector_.isEmpty(), httpRule.selector_);
                this.body_ = visitor.visitString(!this.body_.isEmpty(), this.body_, !httpRule.body_.isEmpty(), httpRule.body_);
                this.additionalBindings_ = visitor.visitList(this.additionalBindings_, httpRule.additionalBindings_);
                switch (AnonymousClass1.$SwitchMap$com$google$api$HttpRule$PatternCase[httpRule.getPatternCase().ordinal()]) {
                    case 1:
                        if (this.patternCase_ == 2) {
                            z = true;
                        }
                        this.pattern_ = visitor.visitOneofString(z, this.pattern_, httpRule.pattern_);
                        break;
                    case 2:
                        if (this.patternCase_ == 3) {
                            z = true;
                        }
                        this.pattern_ = visitor.visitOneofString(z, this.pattern_, httpRule.pattern_);
                        break;
                    case 3:
                        if (this.patternCase_ == 4) {
                            z = true;
                        }
                        this.pattern_ = visitor.visitOneofString(z, this.pattern_, httpRule.pattern_);
                        break;
                    case 4:
                        if (this.patternCase_ == 5) {
                            z = true;
                        }
                        this.pattern_ = visitor.visitOneofString(z, this.pattern_, httpRule.pattern_);
                        break;
                    case 5:
                        if (this.patternCase_ == 6) {
                            z = true;
                        }
                        this.pattern_ = visitor.visitOneofString(z, this.pattern_, httpRule.pattern_);
                        break;
                    case 6:
                        if (this.patternCase_ == 8) {
                            z = true;
                        }
                        this.pattern_ = visitor.visitOneofMessage(z, this.pattern_, httpRule.pattern_);
                        break;
                    case 7:
                        if (this.patternCase_ != 0) {
                            z = true;
                        }
                        visitor.visitOneofNotSet(z);
                        break;
                }
                if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                    int i = httpRule.patternCase_;
                    if (i != 0) {
                        this.patternCase_ = i;
                    }
                    this.bitField0_ |= httpRule.bitField0_;
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
                                this.selector_ = codedInputStream.readStringRequireUtf8();
                            } else if (readTag == 18) {
                                String readStringRequireUtf8 = codedInputStream.readStringRequireUtf8();
                                this.patternCase_ = 2;
                                this.pattern_ = readStringRequireUtf8;
                            } else if (readTag == 26) {
                                String readStringRequireUtf82 = codedInputStream.readStringRequireUtf8();
                                this.patternCase_ = 3;
                                this.pattern_ = readStringRequireUtf82;
                            } else if (readTag == 34) {
                                String readStringRequireUtf83 = codedInputStream.readStringRequireUtf8();
                                this.patternCase_ = 4;
                                this.pattern_ = readStringRequireUtf83;
                            } else if (readTag == 42) {
                                String readStringRequireUtf84 = codedInputStream.readStringRequireUtf8();
                                this.patternCase_ = 5;
                                this.pattern_ = readStringRequireUtf84;
                            } else if (readTag == 50) {
                                String readStringRequireUtf85 = codedInputStream.readStringRequireUtf8();
                                this.patternCase_ = 6;
                                this.pattern_ = readStringRequireUtf85;
                            } else if (readTag == 58) {
                                this.body_ = codedInputStream.readStringRequireUtf8();
                            } else if (readTag == 66) {
                                CustomHttpPattern.Builder builder = this.patternCase_ == 8 ? (CustomHttpPattern.Builder) ((CustomHttpPattern) this.pattern_).toBuilder() : null;
                                MessageLite readMessage = codedInputStream.readMessage(CustomHttpPattern.parser(), extensionRegistryLite);
                                this.pattern_ = readMessage;
                                if (builder != null) {
                                    builder.mergeFrom((CustomHttpPattern) readMessage);
                                    this.pattern_ = builder.buildPartial();
                                }
                                this.patternCase_ = 8;
                            } else if (readTag == 90) {
                                if (!this.additionalBindings_.isModifiable()) {
                                    this.additionalBindings_ = GeneratedMessageLite.mutableCopy(this.additionalBindings_);
                                }
                                this.additionalBindings_.add((HttpRule) codedInputStream.readMessage(parser(), extensionRegistryLite));
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
                    synchronized (HttpRule.class) {
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
        HttpRule httpRule = new HttpRule();
        DEFAULT_INSTANCE = httpRule;
        httpRule.makeImmutable();
    }

    public static HttpRule getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<HttpRule> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
