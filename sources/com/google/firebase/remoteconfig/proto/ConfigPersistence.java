package com.google.firebase.remoteconfig.proto;

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

/* compiled from: com.google.firebase:firebase-config@@19.0.3 */
public final class ConfigPersistence {

    /* compiled from: com.google.firebase:firebase-config@@19.0.3 */
    public interface ConfigHolderOrBuilder extends MessageLiteOrBuilder {
        ByteString getExperimentPayload(int i);

        int getExperimentPayloadCount();

        List<ByteString> getExperimentPayloadList();

        NamespaceKeyValue getNamespaceKeyValue(int i);

        int getNamespaceKeyValueCount();

        List<NamespaceKeyValue> getNamespaceKeyValueList();

        long getTimestamp();

        boolean hasTimestamp();
    }

    /* compiled from: com.google.firebase:firebase-config@@19.0.3 */
    public interface KeyValueOrBuilder extends MessageLiteOrBuilder {
        String getKey();

        ByteString getKeyBytes();

        ByteString getValue();

        boolean hasKey();

        boolean hasValue();
    }

    /* compiled from: com.google.firebase:firebase-config@@19.0.3 */
    public interface MetadataOrBuilder extends MessageLiteOrBuilder {
        boolean getDeveloperModeEnabled();

        int getLastFetchStatus();

        long getLastKnownExperimentStartTime();

        boolean hasDeveloperModeEnabled();

        boolean hasLastFetchStatus();

        boolean hasLastKnownExperimentStartTime();
    }

    /* compiled from: com.google.firebase:firebase-config@@19.0.3 */
    public interface NamespaceKeyValueOrBuilder extends MessageLiteOrBuilder {
        KeyValue getKeyValue(int i);

        int getKeyValueCount();

        List<KeyValue> getKeyValueList();

        String getNamespace();

        ByteString getNamespaceBytes();

        boolean hasNamespace();
    }

    /* compiled from: com.google.firebase:firebase-config@@19.0.3 */
    public interface PersistedConfigOrBuilder extends MessageLiteOrBuilder {
        ConfigHolder getActiveConfigHolder();

        Resource getAppliedResource(int i);

        int getAppliedResourceCount();

        List<Resource> getAppliedResourceList();

        ConfigHolder getDefaultsConfigHolder();

        ConfigHolder getFetchedConfigHolder();

        Metadata getMetadata();

        boolean hasActiveConfigHolder();

        boolean hasDefaultsConfigHolder();

        boolean hasFetchedConfigHolder();

        boolean hasMetadata();
    }

    /* compiled from: com.google.firebase:firebase-config@@19.0.3 */
    public interface ResourceOrBuilder extends MessageLiteOrBuilder {
        long getAppUpdateTime();

        String getNamespace();

        ByteString getNamespaceBytes();

        int getResourceId();

        boolean hasAppUpdateTime();

        boolean hasNamespace();

        boolean hasResourceId();
    }

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }

    private ConfigPersistence() {
    }

    /* compiled from: com.google.firebase:firebase-config@@19.0.3 */
    public static final class PersistedConfig extends GeneratedMessageLite<PersistedConfig, Builder> implements PersistedConfigOrBuilder {
        public static final int ACTIVE_CONFIG_HOLDER_FIELD_NUMBER = 2;
        public static final int APPLIED_RESOURCE_FIELD_NUMBER = 5;
        public static final int DEFAULTS_CONFIG_HOLDER_FIELD_NUMBER = 3;
        /* access modifiers changed from: private */
        public static final PersistedConfig DEFAULT_INSTANCE;
        public static final int FETCHED_CONFIG_HOLDER_FIELD_NUMBER = 1;
        public static final int METADATA_FIELD_NUMBER = 4;
        private static volatile Parser<PersistedConfig> PARSER;
        private ConfigHolder activeConfigHolder_;
        private Internal.ProtobufList<Resource> appliedResource_ = emptyProtobufList();
        private int bitField0_;
        private ConfigHolder defaultsConfigHolder_;
        private ConfigHolder fetchedConfigHolder_;
        private Metadata metadata_;

        private PersistedConfig() {
        }

        public boolean hasFetchedConfigHolder() {
            return (this.bitField0_ & 1) == 1;
        }

        public ConfigHolder getFetchedConfigHolder() {
            ConfigHolder configHolder = this.fetchedConfigHolder_;
            return configHolder == null ? ConfigHolder.getDefaultInstance() : configHolder;
        }

        /* access modifiers changed from: private */
        public void setFetchedConfigHolder(ConfigHolder configHolder) {
            if (configHolder != null) {
                this.fetchedConfigHolder_ = configHolder;
                this.bitField0_ |= 1;
                return;
            }
            throw null;
        }

        /* access modifiers changed from: private */
        public void setFetchedConfigHolder(ConfigHolder.Builder builder) {
            this.fetchedConfigHolder_ = (ConfigHolder) builder.build();
            this.bitField0_ |= 1;
        }

        /* access modifiers changed from: private */
        public void mergeFetchedConfigHolder(ConfigHolder configHolder) {
            ConfigHolder configHolder2 = this.fetchedConfigHolder_;
            if (configHolder2 == null || configHolder2 == ConfigHolder.getDefaultInstance()) {
                this.fetchedConfigHolder_ = configHolder;
            } else {
                this.fetchedConfigHolder_ = (ConfigHolder) ((ConfigHolder.Builder) ConfigHolder.newBuilder(this.fetchedConfigHolder_).mergeFrom(configHolder)).buildPartial();
            }
            this.bitField0_ |= 1;
        }

        /* access modifiers changed from: private */
        public void clearFetchedConfigHolder() {
            this.fetchedConfigHolder_ = null;
            this.bitField0_ &= -2;
        }

        public boolean hasActiveConfigHolder() {
            return (this.bitField0_ & 2) == 2;
        }

        public ConfigHolder getActiveConfigHolder() {
            ConfigHolder configHolder = this.activeConfigHolder_;
            return configHolder == null ? ConfigHolder.getDefaultInstance() : configHolder;
        }

        /* access modifiers changed from: private */
        public void setActiveConfigHolder(ConfigHolder configHolder) {
            if (configHolder != null) {
                this.activeConfigHolder_ = configHolder;
                this.bitField0_ |= 2;
                return;
            }
            throw null;
        }

        /* access modifiers changed from: private */
        public void setActiveConfigHolder(ConfigHolder.Builder builder) {
            this.activeConfigHolder_ = (ConfigHolder) builder.build();
            this.bitField0_ |= 2;
        }

        /* access modifiers changed from: private */
        public void mergeActiveConfigHolder(ConfigHolder configHolder) {
            ConfigHolder configHolder2 = this.activeConfigHolder_;
            if (configHolder2 == null || configHolder2 == ConfigHolder.getDefaultInstance()) {
                this.activeConfigHolder_ = configHolder;
            } else {
                this.activeConfigHolder_ = (ConfigHolder) ((ConfigHolder.Builder) ConfigHolder.newBuilder(this.activeConfigHolder_).mergeFrom(configHolder)).buildPartial();
            }
            this.bitField0_ |= 2;
        }

        /* access modifiers changed from: private */
        public void clearActiveConfigHolder() {
            this.activeConfigHolder_ = null;
            this.bitField0_ &= -3;
        }

        public boolean hasDefaultsConfigHolder() {
            return (this.bitField0_ & 4) == 4;
        }

        public ConfigHolder getDefaultsConfigHolder() {
            ConfigHolder configHolder = this.defaultsConfigHolder_;
            return configHolder == null ? ConfigHolder.getDefaultInstance() : configHolder;
        }

        /* access modifiers changed from: private */
        public void setDefaultsConfigHolder(ConfigHolder configHolder) {
            if (configHolder != null) {
                this.defaultsConfigHolder_ = configHolder;
                this.bitField0_ |= 4;
                return;
            }
            throw null;
        }

        /* access modifiers changed from: private */
        public void setDefaultsConfigHolder(ConfigHolder.Builder builder) {
            this.defaultsConfigHolder_ = (ConfigHolder) builder.build();
            this.bitField0_ |= 4;
        }

        /* access modifiers changed from: private */
        public void mergeDefaultsConfigHolder(ConfigHolder configHolder) {
            ConfigHolder configHolder2 = this.defaultsConfigHolder_;
            if (configHolder2 == null || configHolder2 == ConfigHolder.getDefaultInstance()) {
                this.defaultsConfigHolder_ = configHolder;
            } else {
                this.defaultsConfigHolder_ = (ConfigHolder) ((ConfigHolder.Builder) ConfigHolder.newBuilder(this.defaultsConfigHolder_).mergeFrom(configHolder)).buildPartial();
            }
            this.bitField0_ |= 4;
        }

        /* access modifiers changed from: private */
        public void clearDefaultsConfigHolder() {
            this.defaultsConfigHolder_ = null;
            this.bitField0_ &= -5;
        }

        public boolean hasMetadata() {
            return (this.bitField0_ & 8) == 8;
        }

        public Metadata getMetadata() {
            Metadata metadata = this.metadata_;
            return metadata == null ? Metadata.getDefaultInstance() : metadata;
        }

        /* access modifiers changed from: private */
        public void setMetadata(Metadata metadata) {
            if (metadata != null) {
                this.metadata_ = metadata;
                this.bitField0_ |= 8;
                return;
            }
            throw null;
        }

        /* access modifiers changed from: private */
        public void setMetadata(Metadata.Builder builder) {
            this.metadata_ = (Metadata) builder.build();
            this.bitField0_ |= 8;
        }

        /* access modifiers changed from: private */
        public void mergeMetadata(Metadata metadata) {
            Metadata metadata2 = this.metadata_;
            if (metadata2 == null || metadata2 == Metadata.getDefaultInstance()) {
                this.metadata_ = metadata;
            } else {
                this.metadata_ = (Metadata) ((Metadata.Builder) Metadata.newBuilder(this.metadata_).mergeFrom(metadata)).buildPartial();
            }
            this.bitField0_ |= 8;
        }

        /* access modifiers changed from: private */
        public void clearMetadata() {
            this.metadata_ = null;
            this.bitField0_ &= -9;
        }

        public List<Resource> getAppliedResourceList() {
            return this.appliedResource_;
        }

        public List<? extends ResourceOrBuilder> getAppliedResourceOrBuilderList() {
            return this.appliedResource_;
        }

        public int getAppliedResourceCount() {
            return this.appliedResource_.size();
        }

        public Resource getAppliedResource(int i) {
            return (Resource) this.appliedResource_.get(i);
        }

        public ResourceOrBuilder getAppliedResourceOrBuilder(int i) {
            return (ResourceOrBuilder) this.appliedResource_.get(i);
        }

        private void ensureAppliedResourceIsMutable() {
            if (!this.appliedResource_.isModifiable()) {
                this.appliedResource_ = GeneratedMessageLite.mutableCopy(this.appliedResource_);
            }
        }

        /* access modifiers changed from: private */
        public void setAppliedResource(int i, Resource resource) {
            if (resource != null) {
                ensureAppliedResourceIsMutable();
                this.appliedResource_.set(i, resource);
                return;
            }
            throw null;
        }

        /* access modifiers changed from: private */
        public void setAppliedResource(int i, Resource.Builder builder) {
            ensureAppliedResourceIsMutable();
            this.appliedResource_.set(i, (Resource) builder.build());
        }

        /* access modifiers changed from: private */
        public void addAppliedResource(Resource resource) {
            if (resource != null) {
                ensureAppliedResourceIsMutable();
                this.appliedResource_.add(resource);
                return;
            }
            throw null;
        }

        /* access modifiers changed from: private */
        public void addAppliedResource(int i, Resource resource) {
            if (resource != null) {
                ensureAppliedResourceIsMutable();
                this.appliedResource_.add(i, resource);
                return;
            }
            throw null;
        }

        /* access modifiers changed from: private */
        public void addAppliedResource(Resource.Builder builder) {
            ensureAppliedResourceIsMutable();
            this.appliedResource_.add((Resource) builder.build());
        }

        /* access modifiers changed from: private */
        public void addAppliedResource(int i, Resource.Builder builder) {
            ensureAppliedResourceIsMutable();
            this.appliedResource_.add(i, (Resource) builder.build());
        }

        /* access modifiers changed from: private */
        public void addAllAppliedResource(Iterable<? extends Resource> iterable) {
            ensureAppliedResourceIsMutable();
            AbstractMessageLite.addAll(iterable, this.appliedResource_);
        }

        /* access modifiers changed from: private */
        public void clearAppliedResource() {
            this.appliedResource_ = emptyProtobufList();
        }

        /* access modifiers changed from: private */
        public void removeAppliedResource(int i) {
            ensureAppliedResourceIsMutable();
            this.appliedResource_.remove(i);
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                codedOutputStream.writeMessage(1, getFetchedConfigHolder());
            }
            if ((this.bitField0_ & 2) == 2) {
                codedOutputStream.writeMessage(2, getActiveConfigHolder());
            }
            if ((this.bitField0_ & 4) == 4) {
                codedOutputStream.writeMessage(3, getDefaultsConfigHolder());
            }
            if ((this.bitField0_ & 8) == 8) {
                codedOutputStream.writeMessage(4, getMetadata());
            }
            for (int i = 0; i < this.appliedResource_.size(); i++) {
                codedOutputStream.writeMessage(5, (MessageLite) this.appliedResource_.get(i));
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int computeMessageSize = (this.bitField0_ & 1) == 1 ? CodedOutputStream.computeMessageSize(1, getFetchedConfigHolder()) + 0 : 0;
            if ((this.bitField0_ & 2) == 2) {
                computeMessageSize += CodedOutputStream.computeMessageSize(2, getActiveConfigHolder());
            }
            if ((this.bitField0_ & 4) == 4) {
                computeMessageSize += CodedOutputStream.computeMessageSize(3, getDefaultsConfigHolder());
            }
            if ((this.bitField0_ & 8) == 8) {
                computeMessageSize += CodedOutputStream.computeMessageSize(4, getMetadata());
            }
            for (int i2 = 0; i2 < this.appliedResource_.size(); i2++) {
                computeMessageSize += CodedOutputStream.computeMessageSize(5, (MessageLite) this.appliedResource_.get(i2));
            }
            int serializedSize = computeMessageSize + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        public static PersistedConfig parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (PersistedConfig) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static PersistedConfig parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (PersistedConfig) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static PersistedConfig parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (PersistedConfig) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static PersistedConfig parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (PersistedConfig) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static PersistedConfig parseFrom(InputStream inputStream) throws IOException {
            return (PersistedConfig) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static PersistedConfig parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (PersistedConfig) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static PersistedConfig parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (PersistedConfig) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static PersistedConfig parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (PersistedConfig) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static PersistedConfig parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (PersistedConfig) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static PersistedConfig parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (PersistedConfig) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(PersistedConfig persistedConfig) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(persistedConfig);
        }

        /* compiled from: com.google.firebase:firebase-config@@19.0.3 */
        public static final class Builder extends GeneratedMessageLite.Builder<PersistedConfig, Builder> implements PersistedConfigOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 r1) {
                this();
            }

            private Builder() {
                super(PersistedConfig.DEFAULT_INSTANCE);
            }

            public boolean hasFetchedConfigHolder() {
                return ((PersistedConfig) this.instance).hasFetchedConfigHolder();
            }

            public ConfigHolder getFetchedConfigHolder() {
                return ((PersistedConfig) this.instance).getFetchedConfigHolder();
            }

            public Builder setFetchedConfigHolder(ConfigHolder configHolder) {
                copyOnWrite();
                ((PersistedConfig) this.instance).setFetchedConfigHolder(configHolder);
                return this;
            }

            public Builder setFetchedConfigHolder(ConfigHolder.Builder builder) {
                copyOnWrite();
                ((PersistedConfig) this.instance).setFetchedConfigHolder(builder);
                return this;
            }

            public Builder mergeFetchedConfigHolder(ConfigHolder configHolder) {
                copyOnWrite();
                ((PersistedConfig) this.instance).mergeFetchedConfigHolder(configHolder);
                return this;
            }

            public Builder clearFetchedConfigHolder() {
                copyOnWrite();
                ((PersistedConfig) this.instance).clearFetchedConfigHolder();
                return this;
            }

            public boolean hasActiveConfigHolder() {
                return ((PersistedConfig) this.instance).hasActiveConfigHolder();
            }

            public ConfigHolder getActiveConfigHolder() {
                return ((PersistedConfig) this.instance).getActiveConfigHolder();
            }

            public Builder setActiveConfigHolder(ConfigHolder configHolder) {
                copyOnWrite();
                ((PersistedConfig) this.instance).setActiveConfigHolder(configHolder);
                return this;
            }

            public Builder setActiveConfigHolder(ConfigHolder.Builder builder) {
                copyOnWrite();
                ((PersistedConfig) this.instance).setActiveConfigHolder(builder);
                return this;
            }

            public Builder mergeActiveConfigHolder(ConfigHolder configHolder) {
                copyOnWrite();
                ((PersistedConfig) this.instance).mergeActiveConfigHolder(configHolder);
                return this;
            }

            public Builder clearActiveConfigHolder() {
                copyOnWrite();
                ((PersistedConfig) this.instance).clearActiveConfigHolder();
                return this;
            }

            public boolean hasDefaultsConfigHolder() {
                return ((PersistedConfig) this.instance).hasDefaultsConfigHolder();
            }

            public ConfigHolder getDefaultsConfigHolder() {
                return ((PersistedConfig) this.instance).getDefaultsConfigHolder();
            }

            public Builder setDefaultsConfigHolder(ConfigHolder configHolder) {
                copyOnWrite();
                ((PersistedConfig) this.instance).setDefaultsConfigHolder(configHolder);
                return this;
            }

            public Builder setDefaultsConfigHolder(ConfigHolder.Builder builder) {
                copyOnWrite();
                ((PersistedConfig) this.instance).setDefaultsConfigHolder(builder);
                return this;
            }

            public Builder mergeDefaultsConfigHolder(ConfigHolder configHolder) {
                copyOnWrite();
                ((PersistedConfig) this.instance).mergeDefaultsConfigHolder(configHolder);
                return this;
            }

            public Builder clearDefaultsConfigHolder() {
                copyOnWrite();
                ((PersistedConfig) this.instance).clearDefaultsConfigHolder();
                return this;
            }

            public boolean hasMetadata() {
                return ((PersistedConfig) this.instance).hasMetadata();
            }

            public Metadata getMetadata() {
                return ((PersistedConfig) this.instance).getMetadata();
            }

            public Builder setMetadata(Metadata metadata) {
                copyOnWrite();
                ((PersistedConfig) this.instance).setMetadata(metadata);
                return this;
            }

            public Builder setMetadata(Metadata.Builder builder) {
                copyOnWrite();
                ((PersistedConfig) this.instance).setMetadata(builder);
                return this;
            }

            public Builder mergeMetadata(Metadata metadata) {
                copyOnWrite();
                ((PersistedConfig) this.instance).mergeMetadata(metadata);
                return this;
            }

            public Builder clearMetadata() {
                copyOnWrite();
                ((PersistedConfig) this.instance).clearMetadata();
                return this;
            }

            public List<Resource> getAppliedResourceList() {
                return Collections.unmodifiableList(((PersistedConfig) this.instance).getAppliedResourceList());
            }

            public int getAppliedResourceCount() {
                return ((PersistedConfig) this.instance).getAppliedResourceCount();
            }

            public Resource getAppliedResource(int i) {
                return ((PersistedConfig) this.instance).getAppliedResource(i);
            }

            public Builder setAppliedResource(int i, Resource resource) {
                copyOnWrite();
                ((PersistedConfig) this.instance).setAppliedResource(i, resource);
                return this;
            }

            public Builder setAppliedResource(int i, Resource.Builder builder) {
                copyOnWrite();
                ((PersistedConfig) this.instance).setAppliedResource(i, builder);
                return this;
            }

            public Builder addAppliedResource(Resource resource) {
                copyOnWrite();
                ((PersistedConfig) this.instance).addAppliedResource(resource);
                return this;
            }

            public Builder addAppliedResource(int i, Resource resource) {
                copyOnWrite();
                ((PersistedConfig) this.instance).addAppliedResource(i, resource);
                return this;
            }

            public Builder addAppliedResource(Resource.Builder builder) {
                copyOnWrite();
                ((PersistedConfig) this.instance).addAppliedResource(builder);
                return this;
            }

            public Builder addAppliedResource(int i, Resource.Builder builder) {
                copyOnWrite();
                ((PersistedConfig) this.instance).addAppliedResource(i, builder);
                return this;
            }

            public Builder addAllAppliedResource(Iterable<? extends Resource> iterable) {
                copyOnWrite();
                ((PersistedConfig) this.instance).addAllAppliedResource(iterable);
                return this;
            }

            public Builder clearAppliedResource() {
                copyOnWrite();
                ((PersistedConfig) this.instance).clearAppliedResource();
                return this;
            }

            public Builder removeAppliedResource(int i) {
                copyOnWrite();
                ((PersistedConfig) this.instance).removeAppliedResource(i);
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
                case 1:
                    return new PersistedConfig();
                case 2:
                    return DEFAULT_INSTANCE;
                case 3:
                    this.appliedResource_.makeImmutable();
                    return null;
                case 4:
                    return new Builder((AnonymousClass1) null);
                case 5:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                    PersistedConfig persistedConfig = (PersistedConfig) obj2;
                    this.fetchedConfigHolder_ = (ConfigHolder) visitor.visitMessage(this.fetchedConfigHolder_, persistedConfig.fetchedConfigHolder_);
                    this.activeConfigHolder_ = (ConfigHolder) visitor.visitMessage(this.activeConfigHolder_, persistedConfig.activeConfigHolder_);
                    this.defaultsConfigHolder_ = (ConfigHolder) visitor.visitMessage(this.defaultsConfigHolder_, persistedConfig.defaultsConfigHolder_);
                    this.metadata_ = (Metadata) visitor.visitMessage(this.metadata_, persistedConfig.metadata_);
                    this.appliedResource_ = visitor.visitList(this.appliedResource_, persistedConfig.appliedResource_);
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                        this.bitField0_ |= persistedConfig.bitField0_;
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
                                    ConfigHolder.Builder builder = (this.bitField0_ & 1) == 1 ? (ConfigHolder.Builder) this.fetchedConfigHolder_.toBuilder() : null;
                                    ConfigHolder configHolder = (ConfigHolder) codedInputStream.readMessage(ConfigHolder.parser(), extensionRegistryLite);
                                    this.fetchedConfigHolder_ = configHolder;
                                    if (builder != null) {
                                        builder.mergeFrom(configHolder);
                                        this.fetchedConfigHolder_ = (ConfigHolder) builder.buildPartial();
                                    }
                                    this.bitField0_ |= 1;
                                } else if (readTag == 18) {
                                    ConfigHolder.Builder builder2 = (this.bitField0_ & 2) == 2 ? (ConfigHolder.Builder) this.activeConfigHolder_.toBuilder() : null;
                                    ConfigHolder configHolder2 = (ConfigHolder) codedInputStream.readMessage(ConfigHolder.parser(), extensionRegistryLite);
                                    this.activeConfigHolder_ = configHolder2;
                                    if (builder2 != null) {
                                        builder2.mergeFrom(configHolder2);
                                        this.activeConfigHolder_ = (ConfigHolder) builder2.buildPartial();
                                    }
                                    this.bitField0_ |= 2;
                                } else if (readTag == 26) {
                                    ConfigHolder.Builder builder3 = (this.bitField0_ & 4) == 4 ? (ConfigHolder.Builder) this.defaultsConfigHolder_.toBuilder() : null;
                                    ConfigHolder configHolder3 = (ConfigHolder) codedInputStream.readMessage(ConfigHolder.parser(), extensionRegistryLite);
                                    this.defaultsConfigHolder_ = configHolder3;
                                    if (builder3 != null) {
                                        builder3.mergeFrom(configHolder3);
                                        this.defaultsConfigHolder_ = (ConfigHolder) builder3.buildPartial();
                                    }
                                    this.bitField0_ |= 4;
                                } else if (readTag == 34) {
                                    Metadata.Builder builder4 = (this.bitField0_ & 8) == 8 ? (Metadata.Builder) this.metadata_.toBuilder() : null;
                                    Metadata metadata = (Metadata) codedInputStream.readMessage(Metadata.parser(), extensionRegistryLite);
                                    this.metadata_ = metadata;
                                    if (builder4 != null) {
                                        builder4.mergeFrom(metadata);
                                        this.metadata_ = (Metadata) builder4.buildPartial();
                                    }
                                    this.bitField0_ |= 8;
                                } else if (readTag == 42) {
                                    if (!this.appliedResource_.isModifiable()) {
                                        this.appliedResource_ = GeneratedMessageLite.mutableCopy(this.appliedResource_);
                                    }
                                    this.appliedResource_.add((Resource) codedInputStream.readMessage(Resource.parser(), extensionRegistryLite));
                                } else if (!parseUnknownField(readTag, codedInputStream)) {
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
                        synchronized (PersistedConfig.class) {
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
            PersistedConfig persistedConfig = new PersistedConfig();
            DEFAULT_INSTANCE = persistedConfig;
            persistedConfig.makeImmutable();
        }

        public static PersistedConfig getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<PersistedConfig> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    /* renamed from: com.google.firebase.remoteconfig.proto.ConfigPersistence$1  reason: invalid class name */
    /* compiled from: com.google.firebase:firebase-config@@19.0.3 */
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.remoteconfig.proto.ConfigPersistence.AnonymousClass1.<clinit>():void");
        }
    }

    /* compiled from: com.google.firebase:firebase-config@@19.0.3 */
    public static final class KeyValue extends GeneratedMessageLite<KeyValue, Builder> implements KeyValueOrBuilder {
        /* access modifiers changed from: private */
        public static final KeyValue DEFAULT_INSTANCE;
        public static final int KEY_FIELD_NUMBER = 1;
        private static volatile Parser<KeyValue> PARSER = null;
        public static final int VALUE_FIELD_NUMBER = 2;
        private int bitField0_;
        private String key_ = "";
        private ByteString value_ = ByteString.EMPTY;

        private KeyValue() {
        }

        public boolean hasKey() {
            return (this.bitField0_ & 1) == 1;
        }

        public String getKey() {
            return this.key_;
        }

        public ByteString getKeyBytes() {
            return ByteString.copyFromUtf8(this.key_);
        }

        /* access modifiers changed from: private */
        public void setKey(String str) {
            if (str != null) {
                this.bitField0_ |= 1;
                this.key_ = str;
                return;
            }
            throw null;
        }

        /* access modifiers changed from: private */
        public void clearKey() {
            this.bitField0_ &= -2;
            this.key_ = getDefaultInstance().getKey();
        }

        /* access modifiers changed from: private */
        public void setKeyBytes(ByteString byteString) {
            if (byteString != null) {
                this.bitField0_ |= 1;
                this.key_ = byteString.toStringUtf8();
                return;
            }
            throw null;
        }

        public boolean hasValue() {
            return (this.bitField0_ & 2) == 2;
        }

        public ByteString getValue() {
            return this.value_;
        }

        /* access modifiers changed from: private */
        public void setValue(ByteString byteString) {
            if (byteString != null) {
                this.bitField0_ |= 2;
                this.value_ = byteString;
                return;
            }
            throw null;
        }

        /* access modifiers changed from: private */
        public void clearValue() {
            this.bitField0_ &= -3;
            this.value_ = getDefaultInstance().getValue();
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                codedOutputStream.writeString(1, getKey());
            }
            if ((this.bitField0_ & 2) == 2) {
                codedOutputStream.writeBytes(2, this.value_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            if ((this.bitField0_ & 1) == 1) {
                i2 = 0 + CodedOutputStream.computeStringSize(1, getKey());
            }
            if ((this.bitField0_ & 2) == 2) {
                i2 += CodedOutputStream.computeBytesSize(2, this.value_);
            }
            int serializedSize = i2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        public static KeyValue parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (KeyValue) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static KeyValue parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (KeyValue) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static KeyValue parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (KeyValue) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static KeyValue parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (KeyValue) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static KeyValue parseFrom(InputStream inputStream) throws IOException {
            return (KeyValue) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static KeyValue parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (KeyValue) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static KeyValue parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (KeyValue) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static KeyValue parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (KeyValue) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static KeyValue parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (KeyValue) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static KeyValue parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (KeyValue) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(KeyValue keyValue) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(keyValue);
        }

        /* compiled from: com.google.firebase:firebase-config@@19.0.3 */
        public static final class Builder extends GeneratedMessageLite.Builder<KeyValue, Builder> implements KeyValueOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 r1) {
                this();
            }

            private Builder() {
                super(KeyValue.DEFAULT_INSTANCE);
            }

            public boolean hasKey() {
                return ((KeyValue) this.instance).hasKey();
            }

            public String getKey() {
                return ((KeyValue) this.instance).getKey();
            }

            public ByteString getKeyBytes() {
                return ((KeyValue) this.instance).getKeyBytes();
            }

            public Builder setKey(String str) {
                copyOnWrite();
                ((KeyValue) this.instance).setKey(str);
                return this;
            }

            public Builder clearKey() {
                copyOnWrite();
                ((KeyValue) this.instance).clearKey();
                return this;
            }

            public Builder setKeyBytes(ByteString byteString) {
                copyOnWrite();
                ((KeyValue) this.instance).setKeyBytes(byteString);
                return this;
            }

            public boolean hasValue() {
                return ((KeyValue) this.instance).hasValue();
            }

            public ByteString getValue() {
                return ((KeyValue) this.instance).getValue();
            }

            public Builder setValue(ByteString byteString) {
                copyOnWrite();
                ((KeyValue) this.instance).setValue(byteString);
                return this;
            }

            public Builder clearValue() {
                copyOnWrite();
                ((KeyValue) this.instance).clearValue();
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
                case 1:
                    return new KeyValue();
                case 2:
                    return DEFAULT_INSTANCE;
                case 3:
                    return null;
                case 4:
                    return new Builder((AnonymousClass1) null);
                case 5:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                    KeyValue keyValue = (KeyValue) obj2;
                    this.key_ = visitor.visitString(hasKey(), this.key_, keyValue.hasKey(), keyValue.key_);
                    this.value_ = visitor.visitByteString(hasValue(), this.value_, keyValue.hasValue(), keyValue.value_);
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                        this.bitField0_ |= keyValue.bitField0_;
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
                                    String readString = codedInputStream.readString();
                                    this.bitField0_ = 1 | this.bitField0_;
                                    this.key_ = readString;
                                } else if (readTag == 18) {
                                    this.bitField0_ |= 2;
                                    this.value_ = codedInputStream.readBytes();
                                } else if (!parseUnknownField(readTag, codedInputStream)) {
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
                        synchronized (KeyValue.class) {
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
            KeyValue keyValue = new KeyValue();
            DEFAULT_INSTANCE = keyValue;
            keyValue.makeImmutable();
        }

        public static KeyValue getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<KeyValue> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    /* compiled from: com.google.firebase:firebase-config@@19.0.3 */
    public static final class NamespaceKeyValue extends GeneratedMessageLite<NamespaceKeyValue, Builder> implements NamespaceKeyValueOrBuilder {
        /* access modifiers changed from: private */
        public static final NamespaceKeyValue DEFAULT_INSTANCE;
        public static final int KEY_VALUE_FIELD_NUMBER = 2;
        public static final int NAMESPACE_FIELD_NUMBER = 1;
        private static volatile Parser<NamespaceKeyValue> PARSER;
        private int bitField0_;
        private Internal.ProtobufList<KeyValue> keyValue_ = emptyProtobufList();
        private String namespace_ = "";

        private NamespaceKeyValue() {
        }

        public boolean hasNamespace() {
            return (this.bitField0_ & 1) == 1;
        }

        public String getNamespace() {
            return this.namespace_;
        }

        public ByteString getNamespaceBytes() {
            return ByteString.copyFromUtf8(this.namespace_);
        }

        /* access modifiers changed from: private */
        public void setNamespace(String str) {
            if (str != null) {
                this.bitField0_ |= 1;
                this.namespace_ = str;
                return;
            }
            throw null;
        }

        /* access modifiers changed from: private */
        public void clearNamespace() {
            this.bitField0_ &= -2;
            this.namespace_ = getDefaultInstance().getNamespace();
        }

        /* access modifiers changed from: private */
        public void setNamespaceBytes(ByteString byteString) {
            if (byteString != null) {
                this.bitField0_ |= 1;
                this.namespace_ = byteString.toStringUtf8();
                return;
            }
            throw null;
        }

        public List<KeyValue> getKeyValueList() {
            return this.keyValue_;
        }

        public List<? extends KeyValueOrBuilder> getKeyValueOrBuilderList() {
            return this.keyValue_;
        }

        public int getKeyValueCount() {
            return this.keyValue_.size();
        }

        public KeyValue getKeyValue(int i) {
            return (KeyValue) this.keyValue_.get(i);
        }

        public KeyValueOrBuilder getKeyValueOrBuilder(int i) {
            return (KeyValueOrBuilder) this.keyValue_.get(i);
        }

        private void ensureKeyValueIsMutable() {
            if (!this.keyValue_.isModifiable()) {
                this.keyValue_ = GeneratedMessageLite.mutableCopy(this.keyValue_);
            }
        }

        /* access modifiers changed from: private */
        public void setKeyValue(int i, KeyValue keyValue) {
            if (keyValue != null) {
                ensureKeyValueIsMutable();
                this.keyValue_.set(i, keyValue);
                return;
            }
            throw null;
        }

        /* access modifiers changed from: private */
        public void setKeyValue(int i, KeyValue.Builder builder) {
            ensureKeyValueIsMutable();
            this.keyValue_.set(i, (KeyValue) builder.build());
        }

        /* access modifiers changed from: private */
        public void addKeyValue(KeyValue keyValue) {
            if (keyValue != null) {
                ensureKeyValueIsMutable();
                this.keyValue_.add(keyValue);
                return;
            }
            throw null;
        }

        /* access modifiers changed from: private */
        public void addKeyValue(int i, KeyValue keyValue) {
            if (keyValue != null) {
                ensureKeyValueIsMutable();
                this.keyValue_.add(i, keyValue);
                return;
            }
            throw null;
        }

        /* access modifiers changed from: private */
        public void addKeyValue(KeyValue.Builder builder) {
            ensureKeyValueIsMutable();
            this.keyValue_.add((KeyValue) builder.build());
        }

        /* access modifiers changed from: private */
        public void addKeyValue(int i, KeyValue.Builder builder) {
            ensureKeyValueIsMutable();
            this.keyValue_.add(i, (KeyValue) builder.build());
        }

        /* access modifiers changed from: private */
        public void addAllKeyValue(Iterable<? extends KeyValue> iterable) {
            ensureKeyValueIsMutable();
            AbstractMessageLite.addAll(iterable, this.keyValue_);
        }

        /* access modifiers changed from: private */
        public void clearKeyValue() {
            this.keyValue_ = emptyProtobufList();
        }

        /* access modifiers changed from: private */
        public void removeKeyValue(int i) {
            ensureKeyValueIsMutable();
            this.keyValue_.remove(i);
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                codedOutputStream.writeString(1, getNamespace());
            }
            for (int i = 0; i < this.keyValue_.size(); i++) {
                codedOutputStream.writeMessage(2, (MessageLite) this.keyValue_.get(i));
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int computeStringSize = (this.bitField0_ & 1) == 1 ? CodedOutputStream.computeStringSize(1, getNamespace()) + 0 : 0;
            for (int i2 = 0; i2 < this.keyValue_.size(); i2++) {
                computeStringSize += CodedOutputStream.computeMessageSize(2, (MessageLite) this.keyValue_.get(i2));
            }
            int serializedSize = computeStringSize + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        public static NamespaceKeyValue parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (NamespaceKeyValue) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static NamespaceKeyValue parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (NamespaceKeyValue) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static NamespaceKeyValue parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (NamespaceKeyValue) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static NamespaceKeyValue parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (NamespaceKeyValue) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static NamespaceKeyValue parseFrom(InputStream inputStream) throws IOException {
            return (NamespaceKeyValue) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static NamespaceKeyValue parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (NamespaceKeyValue) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static NamespaceKeyValue parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (NamespaceKeyValue) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static NamespaceKeyValue parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (NamespaceKeyValue) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static NamespaceKeyValue parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (NamespaceKeyValue) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static NamespaceKeyValue parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (NamespaceKeyValue) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(NamespaceKeyValue namespaceKeyValue) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(namespaceKeyValue);
        }

        /* compiled from: com.google.firebase:firebase-config@@19.0.3 */
        public static final class Builder extends GeneratedMessageLite.Builder<NamespaceKeyValue, Builder> implements NamespaceKeyValueOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 r1) {
                this();
            }

            private Builder() {
                super(NamespaceKeyValue.DEFAULT_INSTANCE);
            }

            public boolean hasNamespace() {
                return ((NamespaceKeyValue) this.instance).hasNamespace();
            }

            public String getNamespace() {
                return ((NamespaceKeyValue) this.instance).getNamespace();
            }

            public ByteString getNamespaceBytes() {
                return ((NamespaceKeyValue) this.instance).getNamespaceBytes();
            }

            public Builder setNamespace(String str) {
                copyOnWrite();
                ((NamespaceKeyValue) this.instance).setNamespace(str);
                return this;
            }

            public Builder clearNamespace() {
                copyOnWrite();
                ((NamespaceKeyValue) this.instance).clearNamespace();
                return this;
            }

            public Builder setNamespaceBytes(ByteString byteString) {
                copyOnWrite();
                ((NamespaceKeyValue) this.instance).setNamespaceBytes(byteString);
                return this;
            }

            public List<KeyValue> getKeyValueList() {
                return Collections.unmodifiableList(((NamespaceKeyValue) this.instance).getKeyValueList());
            }

            public int getKeyValueCount() {
                return ((NamespaceKeyValue) this.instance).getKeyValueCount();
            }

            public KeyValue getKeyValue(int i) {
                return ((NamespaceKeyValue) this.instance).getKeyValue(i);
            }

            public Builder setKeyValue(int i, KeyValue keyValue) {
                copyOnWrite();
                ((NamespaceKeyValue) this.instance).setKeyValue(i, keyValue);
                return this;
            }

            public Builder setKeyValue(int i, KeyValue.Builder builder) {
                copyOnWrite();
                ((NamespaceKeyValue) this.instance).setKeyValue(i, builder);
                return this;
            }

            public Builder addKeyValue(KeyValue keyValue) {
                copyOnWrite();
                ((NamespaceKeyValue) this.instance).addKeyValue(keyValue);
                return this;
            }

            public Builder addKeyValue(int i, KeyValue keyValue) {
                copyOnWrite();
                ((NamespaceKeyValue) this.instance).addKeyValue(i, keyValue);
                return this;
            }

            public Builder addKeyValue(KeyValue.Builder builder) {
                copyOnWrite();
                ((NamespaceKeyValue) this.instance).addKeyValue(builder);
                return this;
            }

            public Builder addKeyValue(int i, KeyValue.Builder builder) {
                copyOnWrite();
                ((NamespaceKeyValue) this.instance).addKeyValue(i, builder);
                return this;
            }

            public Builder addAllKeyValue(Iterable<? extends KeyValue> iterable) {
                copyOnWrite();
                ((NamespaceKeyValue) this.instance).addAllKeyValue(iterable);
                return this;
            }

            public Builder clearKeyValue() {
                copyOnWrite();
                ((NamespaceKeyValue) this.instance).clearKeyValue();
                return this;
            }

            public Builder removeKeyValue(int i) {
                copyOnWrite();
                ((NamespaceKeyValue) this.instance).removeKeyValue(i);
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
                case 1:
                    return new NamespaceKeyValue();
                case 2:
                    return DEFAULT_INSTANCE;
                case 3:
                    this.keyValue_.makeImmutable();
                    return null;
                case 4:
                    return new Builder((AnonymousClass1) null);
                case 5:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                    NamespaceKeyValue namespaceKeyValue = (NamespaceKeyValue) obj2;
                    this.namespace_ = visitor.visitString(hasNamespace(), this.namespace_, namespaceKeyValue.hasNamespace(), namespaceKeyValue.namespace_);
                    this.keyValue_ = visitor.visitList(this.keyValue_, namespaceKeyValue.keyValue_);
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                        this.bitField0_ |= namespaceKeyValue.bitField0_;
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
                                    String readString = codedInputStream.readString();
                                    this.bitField0_ = 1 | this.bitField0_;
                                    this.namespace_ = readString;
                                } else if (readTag == 18) {
                                    if (!this.keyValue_.isModifiable()) {
                                        this.keyValue_ = GeneratedMessageLite.mutableCopy(this.keyValue_);
                                    }
                                    this.keyValue_.add((KeyValue) codedInputStream.readMessage(KeyValue.parser(), extensionRegistryLite));
                                } else if (!parseUnknownField(readTag, codedInputStream)) {
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
                        synchronized (NamespaceKeyValue.class) {
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
            NamespaceKeyValue namespaceKeyValue = new NamespaceKeyValue();
            DEFAULT_INSTANCE = namespaceKeyValue;
            namespaceKeyValue.makeImmutable();
        }

        public static NamespaceKeyValue getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<NamespaceKeyValue> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    /* compiled from: com.google.firebase:firebase-config@@19.0.3 */
    public static final class ConfigHolder extends GeneratedMessageLite<ConfigHolder, Builder> implements ConfigHolderOrBuilder {
        /* access modifiers changed from: private */
        public static final ConfigHolder DEFAULT_INSTANCE;
        public static final int EXPERIMENT_PAYLOAD_FIELD_NUMBER = 3;
        public static final int NAMESPACE_KEY_VALUE_FIELD_NUMBER = 1;
        private static volatile Parser<ConfigHolder> PARSER = null;
        public static final int TIMESTAMP_FIELD_NUMBER = 2;
        private int bitField0_;
        private Internal.ProtobufList<ByteString> experimentPayload_ = emptyProtobufList();
        private Internal.ProtobufList<NamespaceKeyValue> namespaceKeyValue_ = emptyProtobufList();
        private long timestamp_;

        private ConfigHolder() {
        }

        public List<NamespaceKeyValue> getNamespaceKeyValueList() {
            return this.namespaceKeyValue_;
        }

        public List<? extends NamespaceKeyValueOrBuilder> getNamespaceKeyValueOrBuilderList() {
            return this.namespaceKeyValue_;
        }

        public int getNamespaceKeyValueCount() {
            return this.namespaceKeyValue_.size();
        }

        public NamespaceKeyValue getNamespaceKeyValue(int i) {
            return (NamespaceKeyValue) this.namespaceKeyValue_.get(i);
        }

        public NamespaceKeyValueOrBuilder getNamespaceKeyValueOrBuilder(int i) {
            return (NamespaceKeyValueOrBuilder) this.namespaceKeyValue_.get(i);
        }

        private void ensureNamespaceKeyValueIsMutable() {
            if (!this.namespaceKeyValue_.isModifiable()) {
                this.namespaceKeyValue_ = GeneratedMessageLite.mutableCopy(this.namespaceKeyValue_);
            }
        }

        /* access modifiers changed from: private */
        public void setNamespaceKeyValue(int i, NamespaceKeyValue namespaceKeyValue) {
            if (namespaceKeyValue != null) {
                ensureNamespaceKeyValueIsMutable();
                this.namespaceKeyValue_.set(i, namespaceKeyValue);
                return;
            }
            throw null;
        }

        /* access modifiers changed from: private */
        public void setNamespaceKeyValue(int i, NamespaceKeyValue.Builder builder) {
            ensureNamespaceKeyValueIsMutable();
            this.namespaceKeyValue_.set(i, (NamespaceKeyValue) builder.build());
        }

        /* access modifiers changed from: private */
        public void addNamespaceKeyValue(NamespaceKeyValue namespaceKeyValue) {
            if (namespaceKeyValue != null) {
                ensureNamespaceKeyValueIsMutable();
                this.namespaceKeyValue_.add(namespaceKeyValue);
                return;
            }
            throw null;
        }

        /* access modifiers changed from: private */
        public void addNamespaceKeyValue(int i, NamespaceKeyValue namespaceKeyValue) {
            if (namespaceKeyValue != null) {
                ensureNamespaceKeyValueIsMutable();
                this.namespaceKeyValue_.add(i, namespaceKeyValue);
                return;
            }
            throw null;
        }

        /* access modifiers changed from: private */
        public void addNamespaceKeyValue(NamespaceKeyValue.Builder builder) {
            ensureNamespaceKeyValueIsMutable();
            this.namespaceKeyValue_.add((NamespaceKeyValue) builder.build());
        }

        /* access modifiers changed from: private */
        public void addNamespaceKeyValue(int i, NamespaceKeyValue.Builder builder) {
            ensureNamespaceKeyValueIsMutable();
            this.namespaceKeyValue_.add(i, (NamespaceKeyValue) builder.build());
        }

        /* access modifiers changed from: private */
        public void addAllNamespaceKeyValue(Iterable<? extends NamespaceKeyValue> iterable) {
            ensureNamespaceKeyValueIsMutable();
            AbstractMessageLite.addAll(iterable, this.namespaceKeyValue_);
        }

        /* access modifiers changed from: private */
        public void clearNamespaceKeyValue() {
            this.namespaceKeyValue_ = emptyProtobufList();
        }

        /* access modifiers changed from: private */
        public void removeNamespaceKeyValue(int i) {
            ensureNamespaceKeyValueIsMutable();
            this.namespaceKeyValue_.remove(i);
        }

        public boolean hasTimestamp() {
            return (this.bitField0_ & 1) == 1;
        }

        public long getTimestamp() {
            return this.timestamp_;
        }

        /* access modifiers changed from: private */
        public void setTimestamp(long j) {
            this.bitField0_ |= 1;
            this.timestamp_ = j;
        }

        /* access modifiers changed from: private */
        public void clearTimestamp() {
            this.bitField0_ &= -2;
            this.timestamp_ = 0;
        }

        public List<ByteString> getExperimentPayloadList() {
            return this.experimentPayload_;
        }

        public int getExperimentPayloadCount() {
            return this.experimentPayload_.size();
        }

        public ByteString getExperimentPayload(int i) {
            return (ByteString) this.experimentPayload_.get(i);
        }

        private void ensureExperimentPayloadIsMutable() {
            if (!this.experimentPayload_.isModifiable()) {
                this.experimentPayload_ = GeneratedMessageLite.mutableCopy(this.experimentPayload_);
            }
        }

        /* access modifiers changed from: private */
        public void setExperimentPayload(int i, ByteString byteString) {
            if (byteString != null) {
                ensureExperimentPayloadIsMutable();
                this.experimentPayload_.set(i, byteString);
                return;
            }
            throw null;
        }

        /* access modifiers changed from: private */
        public void addExperimentPayload(ByteString byteString) {
            if (byteString != null) {
                ensureExperimentPayloadIsMutable();
                this.experimentPayload_.add(byteString);
                return;
            }
            throw null;
        }

        /* access modifiers changed from: private */
        public void addAllExperimentPayload(Iterable<? extends ByteString> iterable) {
            ensureExperimentPayloadIsMutable();
            AbstractMessageLite.addAll(iterable, this.experimentPayload_);
        }

        /* access modifiers changed from: private */
        public void clearExperimentPayload() {
            this.experimentPayload_ = emptyProtobufList();
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            for (int i = 0; i < this.namespaceKeyValue_.size(); i++) {
                codedOutputStream.writeMessage(1, (MessageLite) this.namespaceKeyValue_.get(i));
            }
            if ((this.bitField0_ & 1) == 1) {
                codedOutputStream.writeFixed64(2, this.timestamp_);
            }
            for (int i2 = 0; i2 < this.experimentPayload_.size(); i2++) {
                codedOutputStream.writeBytes(3, (ByteString) this.experimentPayload_.get(i2));
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            for (int i3 = 0; i3 < this.namespaceKeyValue_.size(); i3++) {
                i2 += CodedOutputStream.computeMessageSize(1, (MessageLite) this.namespaceKeyValue_.get(i3));
            }
            if ((this.bitField0_ & 1) == 1) {
                i2 += CodedOutputStream.computeFixed64Size(2, this.timestamp_);
            }
            int i4 = 0;
            for (int i5 = 0; i5 < this.experimentPayload_.size(); i5++) {
                i4 += CodedOutputStream.computeBytesSizeNoTag((ByteString) this.experimentPayload_.get(i5));
            }
            int size = i2 + i4 + (getExperimentPayloadList().size() * 1) + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size;
            return size;
        }

        public static ConfigHolder parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (ConfigHolder) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static ConfigHolder parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (ConfigHolder) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static ConfigHolder parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (ConfigHolder) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static ConfigHolder parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (ConfigHolder) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static ConfigHolder parseFrom(InputStream inputStream) throws IOException {
            return (ConfigHolder) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static ConfigHolder parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ConfigHolder) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static ConfigHolder parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (ConfigHolder) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static ConfigHolder parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ConfigHolder) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static ConfigHolder parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (ConfigHolder) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static ConfigHolder parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ConfigHolder) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(ConfigHolder configHolder) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(configHolder);
        }

        /* compiled from: com.google.firebase:firebase-config@@19.0.3 */
        public static final class Builder extends GeneratedMessageLite.Builder<ConfigHolder, Builder> implements ConfigHolderOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 r1) {
                this();
            }

            private Builder() {
                super(ConfigHolder.DEFAULT_INSTANCE);
            }

            public List<NamespaceKeyValue> getNamespaceKeyValueList() {
                return Collections.unmodifiableList(((ConfigHolder) this.instance).getNamespaceKeyValueList());
            }

            public int getNamespaceKeyValueCount() {
                return ((ConfigHolder) this.instance).getNamespaceKeyValueCount();
            }

            public NamespaceKeyValue getNamespaceKeyValue(int i) {
                return ((ConfigHolder) this.instance).getNamespaceKeyValue(i);
            }

            public Builder setNamespaceKeyValue(int i, NamespaceKeyValue namespaceKeyValue) {
                copyOnWrite();
                ((ConfigHolder) this.instance).setNamespaceKeyValue(i, namespaceKeyValue);
                return this;
            }

            public Builder setNamespaceKeyValue(int i, NamespaceKeyValue.Builder builder) {
                copyOnWrite();
                ((ConfigHolder) this.instance).setNamespaceKeyValue(i, builder);
                return this;
            }

            public Builder addNamespaceKeyValue(NamespaceKeyValue namespaceKeyValue) {
                copyOnWrite();
                ((ConfigHolder) this.instance).addNamespaceKeyValue(namespaceKeyValue);
                return this;
            }

            public Builder addNamespaceKeyValue(int i, NamespaceKeyValue namespaceKeyValue) {
                copyOnWrite();
                ((ConfigHolder) this.instance).addNamespaceKeyValue(i, namespaceKeyValue);
                return this;
            }

            public Builder addNamespaceKeyValue(NamespaceKeyValue.Builder builder) {
                copyOnWrite();
                ((ConfigHolder) this.instance).addNamespaceKeyValue(builder);
                return this;
            }

            public Builder addNamespaceKeyValue(int i, NamespaceKeyValue.Builder builder) {
                copyOnWrite();
                ((ConfigHolder) this.instance).addNamespaceKeyValue(i, builder);
                return this;
            }

            public Builder addAllNamespaceKeyValue(Iterable<? extends NamespaceKeyValue> iterable) {
                copyOnWrite();
                ((ConfigHolder) this.instance).addAllNamespaceKeyValue(iterable);
                return this;
            }

            public Builder clearNamespaceKeyValue() {
                copyOnWrite();
                ((ConfigHolder) this.instance).clearNamespaceKeyValue();
                return this;
            }

            public Builder removeNamespaceKeyValue(int i) {
                copyOnWrite();
                ((ConfigHolder) this.instance).removeNamespaceKeyValue(i);
                return this;
            }

            public boolean hasTimestamp() {
                return ((ConfigHolder) this.instance).hasTimestamp();
            }

            public long getTimestamp() {
                return ((ConfigHolder) this.instance).getTimestamp();
            }

            public Builder setTimestamp(long j) {
                copyOnWrite();
                ((ConfigHolder) this.instance).setTimestamp(j);
                return this;
            }

            public Builder clearTimestamp() {
                copyOnWrite();
                ((ConfigHolder) this.instance).clearTimestamp();
                return this;
            }

            public List<ByteString> getExperimentPayloadList() {
                return Collections.unmodifiableList(((ConfigHolder) this.instance).getExperimentPayloadList());
            }

            public int getExperimentPayloadCount() {
                return ((ConfigHolder) this.instance).getExperimentPayloadCount();
            }

            public ByteString getExperimentPayload(int i) {
                return ((ConfigHolder) this.instance).getExperimentPayload(i);
            }

            public Builder setExperimentPayload(int i, ByteString byteString) {
                copyOnWrite();
                ((ConfigHolder) this.instance).setExperimentPayload(i, byteString);
                return this;
            }

            public Builder addExperimentPayload(ByteString byteString) {
                copyOnWrite();
                ((ConfigHolder) this.instance).addExperimentPayload(byteString);
                return this;
            }

            public Builder addAllExperimentPayload(Iterable<? extends ByteString> iterable) {
                copyOnWrite();
                ((ConfigHolder) this.instance).addAllExperimentPayload(iterable);
                return this;
            }

            public Builder clearExperimentPayload() {
                copyOnWrite();
                ((ConfigHolder) this.instance).clearExperimentPayload();
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
                case 1:
                    return new ConfigHolder();
                case 2:
                    return DEFAULT_INSTANCE;
                case 3:
                    this.namespaceKeyValue_.makeImmutable();
                    this.experimentPayload_.makeImmutable();
                    return null;
                case 4:
                    return new Builder((AnonymousClass1) null);
                case 5:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                    ConfigHolder configHolder = (ConfigHolder) obj2;
                    this.namespaceKeyValue_ = visitor.visitList(this.namespaceKeyValue_, configHolder.namespaceKeyValue_);
                    this.timestamp_ = visitor.visitLong(hasTimestamp(), this.timestamp_, configHolder.hasTimestamp(), configHolder.timestamp_);
                    this.experimentPayload_ = visitor.visitList(this.experimentPayload_, configHolder.experimentPayload_);
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                        this.bitField0_ |= configHolder.bitField0_;
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
                                    if (!this.namespaceKeyValue_.isModifiable()) {
                                        this.namespaceKeyValue_ = GeneratedMessageLite.mutableCopy(this.namespaceKeyValue_);
                                    }
                                    this.namespaceKeyValue_.add((NamespaceKeyValue) codedInputStream.readMessage(NamespaceKeyValue.parser(), extensionRegistryLite));
                                } else if (readTag == 17) {
                                    this.bitField0_ |= 1;
                                    this.timestamp_ = codedInputStream.readFixed64();
                                } else if (readTag == 26) {
                                    if (!this.experimentPayload_.isModifiable()) {
                                        this.experimentPayload_ = GeneratedMessageLite.mutableCopy(this.experimentPayload_);
                                    }
                                    this.experimentPayload_.add(codedInputStream.readBytes());
                                } else if (!parseUnknownField(readTag, codedInputStream)) {
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
                        synchronized (ConfigHolder.class) {
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
            ConfigHolder configHolder = new ConfigHolder();
            DEFAULT_INSTANCE = configHolder;
            configHolder.makeImmutable();
        }

        public static ConfigHolder getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<ConfigHolder> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    /* compiled from: com.google.firebase:firebase-config@@19.0.3 */
    public static final class Metadata extends GeneratedMessageLite<Metadata, Builder> implements MetadataOrBuilder {
        /* access modifiers changed from: private */
        public static final Metadata DEFAULT_INSTANCE;
        public static final int DEVELOPER_MODE_ENABLED_FIELD_NUMBER = 2;
        public static final int LAST_FETCH_STATUS_FIELD_NUMBER = 1;
        public static final int LAST_KNOWN_EXPERIMENT_START_TIME_FIELD_NUMBER = 3;
        private static volatile Parser<Metadata> PARSER;
        private int bitField0_;
        private boolean developerModeEnabled_;
        private int lastFetchStatus_;
        private long lastKnownExperimentStartTime_;

        private Metadata() {
        }

        public boolean hasLastFetchStatus() {
            return (this.bitField0_ & 1) == 1;
        }

        public int getLastFetchStatus() {
            return this.lastFetchStatus_;
        }

        /* access modifiers changed from: private */
        public void setLastFetchStatus(int i) {
            this.bitField0_ |= 1;
            this.lastFetchStatus_ = i;
        }

        /* access modifiers changed from: private */
        public void clearLastFetchStatus() {
            this.bitField0_ &= -2;
            this.lastFetchStatus_ = 0;
        }

        public boolean hasDeveloperModeEnabled() {
            return (this.bitField0_ & 2) == 2;
        }

        public boolean getDeveloperModeEnabled() {
            return this.developerModeEnabled_;
        }

        /* access modifiers changed from: private */
        public void setDeveloperModeEnabled(boolean z) {
            this.bitField0_ |= 2;
            this.developerModeEnabled_ = z;
        }

        /* access modifiers changed from: private */
        public void clearDeveloperModeEnabled() {
            this.bitField0_ &= -3;
            this.developerModeEnabled_ = false;
        }

        public boolean hasLastKnownExperimentStartTime() {
            return (this.bitField0_ & 4) == 4;
        }

        public long getLastKnownExperimentStartTime() {
            return this.lastKnownExperimentStartTime_;
        }

        /* access modifiers changed from: private */
        public void setLastKnownExperimentStartTime(long j) {
            this.bitField0_ |= 4;
            this.lastKnownExperimentStartTime_ = j;
        }

        /* access modifiers changed from: private */
        public void clearLastKnownExperimentStartTime() {
            this.bitField0_ &= -5;
            this.lastKnownExperimentStartTime_ = 0;
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                codedOutputStream.writeInt32(1, this.lastFetchStatus_);
            }
            if ((this.bitField0_ & 2) == 2) {
                codedOutputStream.writeBool(2, this.developerModeEnabled_);
            }
            if ((this.bitField0_ & 4) == 4) {
                codedOutputStream.writeFixed64(3, this.lastKnownExperimentStartTime_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            if ((this.bitField0_ & 1) == 1) {
                i2 = 0 + CodedOutputStream.computeInt32Size(1, this.lastFetchStatus_);
            }
            if ((this.bitField0_ & 2) == 2) {
                i2 += CodedOutputStream.computeBoolSize(2, this.developerModeEnabled_);
            }
            if ((this.bitField0_ & 4) == 4) {
                i2 += CodedOutputStream.computeFixed64Size(3, this.lastKnownExperimentStartTime_);
            }
            int serializedSize = i2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        public static Metadata parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (Metadata) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static Metadata parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Metadata) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static Metadata parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (Metadata) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static Metadata parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Metadata) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static Metadata parseFrom(InputStream inputStream) throws IOException {
            return (Metadata) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static Metadata parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Metadata) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static Metadata parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (Metadata) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static Metadata parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Metadata) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static Metadata parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (Metadata) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static Metadata parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Metadata) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(Metadata metadata) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(metadata);
        }

        /* compiled from: com.google.firebase:firebase-config@@19.0.3 */
        public static final class Builder extends GeneratedMessageLite.Builder<Metadata, Builder> implements MetadataOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 r1) {
                this();
            }

            private Builder() {
                super(Metadata.DEFAULT_INSTANCE);
            }

            public boolean hasLastFetchStatus() {
                return ((Metadata) this.instance).hasLastFetchStatus();
            }

            public int getLastFetchStatus() {
                return ((Metadata) this.instance).getLastFetchStatus();
            }

            public Builder setLastFetchStatus(int i) {
                copyOnWrite();
                ((Metadata) this.instance).setLastFetchStatus(i);
                return this;
            }

            public Builder clearLastFetchStatus() {
                copyOnWrite();
                ((Metadata) this.instance).clearLastFetchStatus();
                return this;
            }

            public boolean hasDeveloperModeEnabled() {
                return ((Metadata) this.instance).hasDeveloperModeEnabled();
            }

            public boolean getDeveloperModeEnabled() {
                return ((Metadata) this.instance).getDeveloperModeEnabled();
            }

            public Builder setDeveloperModeEnabled(boolean z) {
                copyOnWrite();
                ((Metadata) this.instance).setDeveloperModeEnabled(z);
                return this;
            }

            public Builder clearDeveloperModeEnabled() {
                copyOnWrite();
                ((Metadata) this.instance).clearDeveloperModeEnabled();
                return this;
            }

            public boolean hasLastKnownExperimentStartTime() {
                return ((Metadata) this.instance).hasLastKnownExperimentStartTime();
            }

            public long getLastKnownExperimentStartTime() {
                return ((Metadata) this.instance).getLastKnownExperimentStartTime();
            }

            public Builder setLastKnownExperimentStartTime(long j) {
                copyOnWrite();
                ((Metadata) this.instance).setLastKnownExperimentStartTime(j);
                return this;
            }

            public Builder clearLastKnownExperimentStartTime() {
                copyOnWrite();
                ((Metadata) this.instance).clearLastKnownExperimentStartTime();
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
                case 1:
                    return new Metadata();
                case 2:
                    return DEFAULT_INSTANCE;
                case 3:
                    return null;
                case 4:
                    return new Builder((AnonymousClass1) null);
                case 5:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                    Metadata metadata = (Metadata) obj2;
                    this.lastFetchStatus_ = visitor.visitInt(hasLastFetchStatus(), this.lastFetchStatus_, metadata.hasLastFetchStatus(), metadata.lastFetchStatus_);
                    this.developerModeEnabled_ = visitor.visitBoolean(hasDeveloperModeEnabled(), this.developerModeEnabled_, metadata.hasDeveloperModeEnabled(), metadata.developerModeEnabled_);
                    this.lastKnownExperimentStartTime_ = visitor.visitLong(hasLastKnownExperimentStartTime(), this.lastKnownExperimentStartTime_, metadata.hasLastKnownExperimentStartTime(), metadata.lastKnownExperimentStartTime_);
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                        this.bitField0_ |= metadata.bitField0_;
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
                                if (readTag == 8) {
                                    this.bitField0_ |= 1;
                                    this.lastFetchStatus_ = codedInputStream.readInt32();
                                } else if (readTag == 16) {
                                    this.bitField0_ |= 2;
                                    this.developerModeEnabled_ = codedInputStream.readBool();
                                } else if (readTag == 25) {
                                    this.bitField0_ |= 4;
                                    this.lastKnownExperimentStartTime_ = codedInputStream.readFixed64();
                                } else if (!parseUnknownField(readTag, codedInputStream)) {
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
                        synchronized (Metadata.class) {
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
            Metadata metadata = new Metadata();
            DEFAULT_INSTANCE = metadata;
            metadata.makeImmutable();
        }

        public static Metadata getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Metadata> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    /* compiled from: com.google.firebase:firebase-config@@19.0.3 */
    public static final class Resource extends GeneratedMessageLite<Resource, Builder> implements ResourceOrBuilder {
        public static final int APP_UPDATE_TIME_FIELD_NUMBER = 2;
        /* access modifiers changed from: private */
        public static final Resource DEFAULT_INSTANCE;
        public static final int NAMESPACE_FIELD_NUMBER = 3;
        private static volatile Parser<Resource> PARSER = null;
        public static final int RESOURCE_ID_FIELD_NUMBER = 1;
        private long appUpdateTime_;
        private int bitField0_;
        private String namespace_ = "";
        private int resourceId_;

        private Resource() {
        }

        public boolean hasResourceId() {
            return (this.bitField0_ & 1) == 1;
        }

        public int getResourceId() {
            return this.resourceId_;
        }

        /* access modifiers changed from: private */
        public void setResourceId(int i) {
            this.bitField0_ |= 1;
            this.resourceId_ = i;
        }

        /* access modifiers changed from: private */
        public void clearResourceId() {
            this.bitField0_ &= -2;
            this.resourceId_ = 0;
        }

        public boolean hasAppUpdateTime() {
            return (this.bitField0_ & 2) == 2;
        }

        public long getAppUpdateTime() {
            return this.appUpdateTime_;
        }

        /* access modifiers changed from: private */
        public void setAppUpdateTime(long j) {
            this.bitField0_ |= 2;
            this.appUpdateTime_ = j;
        }

        /* access modifiers changed from: private */
        public void clearAppUpdateTime() {
            this.bitField0_ &= -3;
            this.appUpdateTime_ = 0;
        }

        public boolean hasNamespace() {
            return (this.bitField0_ & 4) == 4;
        }

        public String getNamespace() {
            return this.namespace_;
        }

        public ByteString getNamespaceBytes() {
            return ByteString.copyFromUtf8(this.namespace_);
        }

        /* access modifiers changed from: private */
        public void setNamespace(String str) {
            if (str != null) {
                this.bitField0_ |= 4;
                this.namespace_ = str;
                return;
            }
            throw null;
        }

        /* access modifiers changed from: private */
        public void clearNamespace() {
            this.bitField0_ &= -5;
            this.namespace_ = getDefaultInstance().getNamespace();
        }

        /* access modifiers changed from: private */
        public void setNamespaceBytes(ByteString byteString) {
            if (byteString != null) {
                this.bitField0_ |= 4;
                this.namespace_ = byteString.toStringUtf8();
                return;
            }
            throw null;
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                codedOutputStream.writeInt32(1, this.resourceId_);
            }
            if ((this.bitField0_ & 2) == 2) {
                codedOutputStream.writeFixed64(2, this.appUpdateTime_);
            }
            if ((this.bitField0_ & 4) == 4) {
                codedOutputStream.writeString(3, getNamespace());
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            if ((this.bitField0_ & 1) == 1) {
                i2 = 0 + CodedOutputStream.computeInt32Size(1, this.resourceId_);
            }
            if ((this.bitField0_ & 2) == 2) {
                i2 += CodedOutputStream.computeFixed64Size(2, this.appUpdateTime_);
            }
            if ((this.bitField0_ & 4) == 4) {
                i2 += CodedOutputStream.computeStringSize(3, getNamespace());
            }
            int serializedSize = i2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        public static Resource parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (Resource) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static Resource parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Resource) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static Resource parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (Resource) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static Resource parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Resource) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static Resource parseFrom(InputStream inputStream) throws IOException {
            return (Resource) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static Resource parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Resource) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static Resource parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (Resource) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static Resource parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Resource) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static Resource parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (Resource) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static Resource parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Resource) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(Resource resource) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(resource);
        }

        /* compiled from: com.google.firebase:firebase-config@@19.0.3 */
        public static final class Builder extends GeneratedMessageLite.Builder<Resource, Builder> implements ResourceOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 r1) {
                this();
            }

            private Builder() {
                super(Resource.DEFAULT_INSTANCE);
            }

            public boolean hasResourceId() {
                return ((Resource) this.instance).hasResourceId();
            }

            public int getResourceId() {
                return ((Resource) this.instance).getResourceId();
            }

            public Builder setResourceId(int i) {
                copyOnWrite();
                ((Resource) this.instance).setResourceId(i);
                return this;
            }

            public Builder clearResourceId() {
                copyOnWrite();
                ((Resource) this.instance).clearResourceId();
                return this;
            }

            public boolean hasAppUpdateTime() {
                return ((Resource) this.instance).hasAppUpdateTime();
            }

            public long getAppUpdateTime() {
                return ((Resource) this.instance).getAppUpdateTime();
            }

            public Builder setAppUpdateTime(long j) {
                copyOnWrite();
                ((Resource) this.instance).setAppUpdateTime(j);
                return this;
            }

            public Builder clearAppUpdateTime() {
                copyOnWrite();
                ((Resource) this.instance).clearAppUpdateTime();
                return this;
            }

            public boolean hasNamespace() {
                return ((Resource) this.instance).hasNamespace();
            }

            public String getNamespace() {
                return ((Resource) this.instance).getNamespace();
            }

            public ByteString getNamespaceBytes() {
                return ((Resource) this.instance).getNamespaceBytes();
            }

            public Builder setNamespace(String str) {
                copyOnWrite();
                ((Resource) this.instance).setNamespace(str);
                return this;
            }

            public Builder clearNamespace() {
                copyOnWrite();
                ((Resource) this.instance).clearNamespace();
                return this;
            }

            public Builder setNamespaceBytes(ByteString byteString) {
                copyOnWrite();
                ((Resource) this.instance).setNamespaceBytes(byteString);
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
                case 1:
                    return new Resource();
                case 2:
                    return DEFAULT_INSTANCE;
                case 3:
                    return null;
                case 4:
                    return new Builder((AnonymousClass1) null);
                case 5:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                    Resource resource = (Resource) obj2;
                    this.resourceId_ = visitor.visitInt(hasResourceId(), this.resourceId_, resource.hasResourceId(), resource.resourceId_);
                    this.appUpdateTime_ = visitor.visitLong(hasAppUpdateTime(), this.appUpdateTime_, resource.hasAppUpdateTime(), resource.appUpdateTime_);
                    this.namespace_ = visitor.visitString(hasNamespace(), this.namespace_, resource.hasNamespace(), resource.namespace_);
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                        this.bitField0_ |= resource.bitField0_;
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
                                if (readTag == 8) {
                                    this.bitField0_ |= 1;
                                    this.resourceId_ = codedInputStream.readInt32();
                                } else if (readTag == 17) {
                                    this.bitField0_ |= 2;
                                    this.appUpdateTime_ = codedInputStream.readFixed64();
                                } else if (readTag == 26) {
                                    String readString = codedInputStream.readString();
                                    this.bitField0_ |= 4;
                                    this.namespace_ = readString;
                                } else if (!parseUnknownField(readTag, codedInputStream)) {
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
                        synchronized (Resource.class) {
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
            Resource resource = new Resource();
            DEFAULT_INSTANCE = resource;
            resource.makeImmutable();
        }

        public static Resource getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Resource> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }
}
