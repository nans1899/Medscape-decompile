package com.google.api;

import com.google.api.MetricRule;
import com.google.api.QuotaLimit;
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
public final class Quota extends GeneratedMessageLite<Quota, Builder> implements QuotaOrBuilder {
    /* access modifiers changed from: private */
    public static final Quota DEFAULT_INSTANCE;
    public static final int LIMITS_FIELD_NUMBER = 3;
    public static final int METRIC_RULES_FIELD_NUMBER = 4;
    private static volatile Parser<Quota> PARSER;
    private Internal.ProtobufList<QuotaLimit> limits_ = emptyProtobufList();
    private Internal.ProtobufList<MetricRule> metricRules_ = emptyProtobufList();

    private Quota() {
    }

    public List<QuotaLimit> getLimitsList() {
        return this.limits_;
    }

    public List<? extends QuotaLimitOrBuilder> getLimitsOrBuilderList() {
        return this.limits_;
    }

    public int getLimitsCount() {
        return this.limits_.size();
    }

    public QuotaLimit getLimits(int i) {
        return (QuotaLimit) this.limits_.get(i);
    }

    public QuotaLimitOrBuilder getLimitsOrBuilder(int i) {
        return (QuotaLimitOrBuilder) this.limits_.get(i);
    }

    private void ensureLimitsIsMutable() {
        if (!this.limits_.isModifiable()) {
            this.limits_ = GeneratedMessageLite.mutableCopy(this.limits_);
        }
    }

    /* access modifiers changed from: private */
    public void setLimits(int i, QuotaLimit quotaLimit) {
        if (quotaLimit != null) {
            ensureLimitsIsMutable();
            this.limits_.set(i, quotaLimit);
            return;
        }
        throw null;
    }

    /* access modifiers changed from: private */
    public void setLimits(int i, QuotaLimit.Builder builder) {
        ensureLimitsIsMutable();
        this.limits_.set(i, (QuotaLimit) builder.build());
    }

    /* access modifiers changed from: private */
    public void addLimits(QuotaLimit quotaLimit) {
        if (quotaLimit != null) {
            ensureLimitsIsMutable();
            this.limits_.add(quotaLimit);
            return;
        }
        throw null;
    }

    /* access modifiers changed from: private */
    public void addLimits(int i, QuotaLimit quotaLimit) {
        if (quotaLimit != null) {
            ensureLimitsIsMutable();
            this.limits_.add(i, quotaLimit);
            return;
        }
        throw null;
    }

    /* access modifiers changed from: private */
    public void addLimits(QuotaLimit.Builder builder) {
        ensureLimitsIsMutable();
        this.limits_.add((QuotaLimit) builder.build());
    }

    /* access modifiers changed from: private */
    public void addLimits(int i, QuotaLimit.Builder builder) {
        ensureLimitsIsMutable();
        this.limits_.add(i, (QuotaLimit) builder.build());
    }

    /* access modifiers changed from: private */
    public void addAllLimits(Iterable<? extends QuotaLimit> iterable) {
        ensureLimitsIsMutable();
        AbstractMessageLite.addAll(iterable, this.limits_);
    }

    /* access modifiers changed from: private */
    public void clearLimits() {
        this.limits_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    public void removeLimits(int i) {
        ensureLimitsIsMutable();
        this.limits_.remove(i);
    }

    public List<MetricRule> getMetricRulesList() {
        return this.metricRules_;
    }

    public List<? extends MetricRuleOrBuilder> getMetricRulesOrBuilderList() {
        return this.metricRules_;
    }

    public int getMetricRulesCount() {
        return this.metricRules_.size();
    }

    public MetricRule getMetricRules(int i) {
        return (MetricRule) this.metricRules_.get(i);
    }

    public MetricRuleOrBuilder getMetricRulesOrBuilder(int i) {
        return (MetricRuleOrBuilder) this.metricRules_.get(i);
    }

    private void ensureMetricRulesIsMutable() {
        if (!this.metricRules_.isModifiable()) {
            this.metricRules_ = GeneratedMessageLite.mutableCopy(this.metricRules_);
        }
    }

    /* access modifiers changed from: private */
    public void setMetricRules(int i, MetricRule metricRule) {
        if (metricRule != null) {
            ensureMetricRulesIsMutable();
            this.metricRules_.set(i, metricRule);
            return;
        }
        throw null;
    }

    /* access modifiers changed from: private */
    public void setMetricRules(int i, MetricRule.Builder builder) {
        ensureMetricRulesIsMutable();
        this.metricRules_.set(i, (MetricRule) builder.build());
    }

    /* access modifiers changed from: private */
    public void addMetricRules(MetricRule metricRule) {
        if (metricRule != null) {
            ensureMetricRulesIsMutable();
            this.metricRules_.add(metricRule);
            return;
        }
        throw null;
    }

    /* access modifiers changed from: private */
    public void addMetricRules(int i, MetricRule metricRule) {
        if (metricRule != null) {
            ensureMetricRulesIsMutable();
            this.metricRules_.add(i, metricRule);
            return;
        }
        throw null;
    }

    /* access modifiers changed from: private */
    public void addMetricRules(MetricRule.Builder builder) {
        ensureMetricRulesIsMutable();
        this.metricRules_.add((MetricRule) builder.build());
    }

    /* access modifiers changed from: private */
    public void addMetricRules(int i, MetricRule.Builder builder) {
        ensureMetricRulesIsMutable();
        this.metricRules_.add(i, (MetricRule) builder.build());
    }

    /* access modifiers changed from: private */
    public void addAllMetricRules(Iterable<? extends MetricRule> iterable) {
        ensureMetricRulesIsMutable();
        AbstractMessageLite.addAll(iterable, this.metricRules_);
    }

    /* access modifiers changed from: private */
    public void clearMetricRules() {
        this.metricRules_ = emptyProtobufList();
    }

    /* access modifiers changed from: private */
    public void removeMetricRules(int i) {
        ensureMetricRulesIsMutable();
        this.metricRules_.remove(i);
    }

    public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
        for (int i = 0; i < this.limits_.size(); i++) {
            codedOutputStream.writeMessage(3, (MessageLite) this.limits_.get(i));
        }
        for (int i2 = 0; i2 < this.metricRules_.size(); i2++) {
            codedOutputStream.writeMessage(4, (MessageLite) this.metricRules_.get(i2));
        }
    }

    public int getSerializedSize() {
        int i = this.memoizedSerializedSize;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < this.limits_.size(); i3++) {
            i2 += CodedOutputStream.computeMessageSize(3, (MessageLite) this.limits_.get(i3));
        }
        for (int i4 = 0; i4 < this.metricRules_.size(); i4++) {
            i2 += CodedOutputStream.computeMessageSize(4, (MessageLite) this.metricRules_.get(i4));
        }
        this.memoizedSerializedSize = i2;
        return i2;
    }

    public static Quota parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (Quota) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static Quota parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Quota) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static Quota parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (Quota) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static Quota parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Quota) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static Quota parseFrom(InputStream inputStream) throws IOException {
        return (Quota) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static Quota parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Quota) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static Quota parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (Quota) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static Quota parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Quota) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static Quota parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (Quota) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static Quota parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Quota) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return (Builder) DEFAULT_INSTANCE.toBuilder();
    }

    public static Builder newBuilder(Quota quota) {
        return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(quota);
    }

    /* compiled from: com.google.firebase:protolite-well-known-types@@16.0.1 */
    public static final class Builder extends GeneratedMessageLite.Builder<Quota, Builder> implements QuotaOrBuilder {
        /* synthetic */ Builder(AnonymousClass1 r1) {
            this();
        }

        private Builder() {
            super(Quota.DEFAULT_INSTANCE);
        }

        public List<QuotaLimit> getLimitsList() {
            return Collections.unmodifiableList(((Quota) this.instance).getLimitsList());
        }

        public int getLimitsCount() {
            return ((Quota) this.instance).getLimitsCount();
        }

        public QuotaLimit getLimits(int i) {
            return ((Quota) this.instance).getLimits(i);
        }

        public Builder setLimits(int i, QuotaLimit quotaLimit) {
            copyOnWrite();
            ((Quota) this.instance).setLimits(i, quotaLimit);
            return this;
        }

        public Builder setLimits(int i, QuotaLimit.Builder builder) {
            copyOnWrite();
            ((Quota) this.instance).setLimits(i, builder);
            return this;
        }

        public Builder addLimits(QuotaLimit quotaLimit) {
            copyOnWrite();
            ((Quota) this.instance).addLimits(quotaLimit);
            return this;
        }

        public Builder addLimits(int i, QuotaLimit quotaLimit) {
            copyOnWrite();
            ((Quota) this.instance).addLimits(i, quotaLimit);
            return this;
        }

        public Builder addLimits(QuotaLimit.Builder builder) {
            copyOnWrite();
            ((Quota) this.instance).addLimits(builder);
            return this;
        }

        public Builder addLimits(int i, QuotaLimit.Builder builder) {
            copyOnWrite();
            ((Quota) this.instance).addLimits(i, builder);
            return this;
        }

        public Builder addAllLimits(Iterable<? extends QuotaLimit> iterable) {
            copyOnWrite();
            ((Quota) this.instance).addAllLimits(iterable);
            return this;
        }

        public Builder clearLimits() {
            copyOnWrite();
            ((Quota) this.instance).clearLimits();
            return this;
        }

        public Builder removeLimits(int i) {
            copyOnWrite();
            ((Quota) this.instance).removeLimits(i);
            return this;
        }

        public List<MetricRule> getMetricRulesList() {
            return Collections.unmodifiableList(((Quota) this.instance).getMetricRulesList());
        }

        public int getMetricRulesCount() {
            return ((Quota) this.instance).getMetricRulesCount();
        }

        public MetricRule getMetricRules(int i) {
            return ((Quota) this.instance).getMetricRules(i);
        }

        public Builder setMetricRules(int i, MetricRule metricRule) {
            copyOnWrite();
            ((Quota) this.instance).setMetricRules(i, metricRule);
            return this;
        }

        public Builder setMetricRules(int i, MetricRule.Builder builder) {
            copyOnWrite();
            ((Quota) this.instance).setMetricRules(i, builder);
            return this;
        }

        public Builder addMetricRules(MetricRule metricRule) {
            copyOnWrite();
            ((Quota) this.instance).addMetricRules(metricRule);
            return this;
        }

        public Builder addMetricRules(int i, MetricRule metricRule) {
            copyOnWrite();
            ((Quota) this.instance).addMetricRules(i, metricRule);
            return this;
        }

        public Builder addMetricRules(MetricRule.Builder builder) {
            copyOnWrite();
            ((Quota) this.instance).addMetricRules(builder);
            return this;
        }

        public Builder addMetricRules(int i, MetricRule.Builder builder) {
            copyOnWrite();
            ((Quota) this.instance).addMetricRules(i, builder);
            return this;
        }

        public Builder addAllMetricRules(Iterable<? extends MetricRule> iterable) {
            copyOnWrite();
            ((Quota) this.instance).addAllMetricRules(iterable);
            return this;
        }

        public Builder clearMetricRules() {
            copyOnWrite();
            ((Quota) this.instance).clearMetricRules();
            return this;
        }

        public Builder removeMetricRules(int i) {
            copyOnWrite();
            ((Quota) this.instance).removeMetricRules(i);
            return this;
        }
    }

    /* renamed from: com.google.api.Quota$1  reason: invalid class name */
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.api.Quota.AnonymousClass1.<clinit>():void");
        }
    }

    /* access modifiers changed from: protected */
    public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
            case 1:
                return new Quota();
            case 2:
                return DEFAULT_INSTANCE;
            case 3:
                this.limits_.makeImmutable();
                this.metricRules_.makeImmutable();
                return null;
            case 4:
                return new Builder((AnonymousClass1) null);
            case 5:
                GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                Quota quota = (Quota) obj2;
                this.limits_ = visitor.visitList(this.limits_, quota.limits_);
                this.metricRules_ = visitor.visitList(this.metricRules_, quota.metricRules_);
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
                            if (readTag == 26) {
                                if (!this.limits_.isModifiable()) {
                                    this.limits_ = GeneratedMessageLite.mutableCopy(this.limits_);
                                }
                                this.limits_.add((QuotaLimit) codedInputStream.readMessage(QuotaLimit.parser(), extensionRegistryLite));
                            } else if (readTag == 34) {
                                if (!this.metricRules_.isModifiable()) {
                                    this.metricRules_ = GeneratedMessageLite.mutableCopy(this.metricRules_);
                                }
                                this.metricRules_.add((MetricRule) codedInputStream.readMessage(MetricRule.parser(), extensionRegistryLite));
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
                    synchronized (Quota.class) {
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
        Quota quota = new Quota();
        DEFAULT_INSTANCE = quota;
        quota.makeImmutable();
    }

    public static Quota getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Quota> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
