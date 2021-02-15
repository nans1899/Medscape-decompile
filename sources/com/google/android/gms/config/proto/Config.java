package com.google.android.gms.config.proto;

import com.google.android.gms.config.proto.Logs;
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
public final class Config {

    /* compiled from: com.google.firebase:firebase-config@@19.0.3 */
    public interface AppConfigTableOrBuilder extends MessageLiteOrBuilder {
        String getAppName();

        ByteString getAppNameBytes();

        ByteString getExperimentPayload(int i);

        int getExperimentPayloadCount();

        List<ByteString> getExperimentPayloadList();

        AppNamespaceConfigTable getNamespaceConfig(int i);

        int getNamespaceConfigCount();

        List<AppNamespaceConfigTable> getNamespaceConfigList();

        boolean hasAppName();
    }

    /* compiled from: com.google.firebase:firebase-config@@19.0.3 */
    public interface AppNamespaceConfigTableOrBuilder extends MessageLiteOrBuilder {
        String getDigest();

        ByteString getDigestBytes();

        KeyValue getEntry(int i);

        int getEntryCount();

        List<KeyValue> getEntryList();

        String getNamespace();

        ByteString getNamespaceBytes();

        AppNamespaceConfigTable.NamespaceStatus getStatus();

        boolean hasDigest();

        boolean hasNamespace();

        boolean hasStatus();
    }

    /* compiled from: com.google.firebase:firebase-config@@19.0.3 */
    public interface ConfigFetchRequestOrBuilder extends MessageLiteOrBuilder {
        long getAndroidId();

        int getApiLevel();

        int getClientVersion();

        Logs.AndroidConfigFetchProto getConfig();

        String getDeviceCountry();

        ByteString getDeviceCountryBytes();

        String getDeviceDataVersionInfo();

        ByteString getDeviceDataVersionInfoBytes();

        String getDeviceLocale();

        ByteString getDeviceLocaleBytes();

        int getDeviceSubtype();

        String getDeviceTimezoneId();

        ByteString getDeviceTimezoneIdBytes();

        int getDeviceType();

        int getGmsCoreVersion();

        String getOsVersion();

        ByteString getOsVersionBytes();

        PackageData getPackageData(int i);

        int getPackageDataCount();

        List<PackageData> getPackageDataList();

        long getSecurityToken();

        boolean hasAndroidId();

        boolean hasApiLevel();

        boolean hasClientVersion();

        boolean hasConfig();

        boolean hasDeviceCountry();

        boolean hasDeviceDataVersionInfo();

        boolean hasDeviceLocale();

        boolean hasDeviceSubtype();

        boolean hasDeviceTimezoneId();

        boolean hasDeviceType();

        boolean hasGmsCoreVersion();

        boolean hasOsVersion();

        boolean hasSecurityToken();
    }

    /* compiled from: com.google.firebase:firebase-config@@19.0.3 */
    public interface ConfigFetchResponseOrBuilder extends MessageLiteOrBuilder {
        AppConfigTable getAppConfig(int i);

        int getAppConfigCount();

        List<AppConfigTable> getAppConfigList();

        KeyValue getInternalMetadata(int i);

        int getInternalMetadataCount();

        List<KeyValue> getInternalMetadataList();

        PackageTable getPackageTable(int i);

        int getPackageTableCount();

        List<PackageTable> getPackageTableList();

        ConfigFetchResponse.ResponseStatus getStatus();

        boolean hasStatus();
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
    public interface NamedValueOrBuilder extends MessageLiteOrBuilder {
        String getName();

        ByteString getNameBytes();

        String getValue();

        ByteString getValueBytes();

        boolean hasName();

        boolean hasValue();
    }

    /* compiled from: com.google.firebase:firebase-config@@19.0.3 */
    public interface PackageDataOrBuilder extends MessageLiteOrBuilder {
        int getActiveConfigAgeSeconds();

        NamedValue getAnalyticsUserProperty(int i);

        int getAnalyticsUserPropertyCount();

        List<NamedValue> getAnalyticsUserPropertyList();

        ByteString getAppCertHash();

        String getAppInstanceId();

        ByteString getAppInstanceIdBytes();

        String getAppInstanceIdToken();

        ByteString getAppInstanceIdTokenBytes();

        String getAppVersion();

        ByteString getAppVersionBytes();

        int getAppVersionCode();

        ByteString getCertHash();

        String getConfigId();

        ByteString getConfigIdBytes();

        NamedValue getCustomVariable(int i);

        int getCustomVariableCount();

        List<NamedValue> getCustomVariableList();

        ByteString getDigest();

        int getFetchedConfigAgeSeconds();

        String getGamesProjectId();

        ByteString getGamesProjectIdBytes();

        String getGmpProjectId();

        ByteString getGmpProjectIdBytes();

        NamedValue getNamespaceDigest(int i);

        int getNamespaceDigestCount();

        List<NamedValue> getNamespaceDigestList();

        String getPackageName();

        ByteString getPackageNameBytes();

        int getRequestedCacheExpirationSeconds();

        String getRequestedHiddenNamespace(int i);

        ByteString getRequestedHiddenNamespaceBytes(int i);

        int getRequestedHiddenNamespaceCount();

        List<String> getRequestedHiddenNamespaceList();

        int getSdkVersion();

        int getVersionCode();

        boolean hasActiveConfigAgeSeconds();

        boolean hasAppCertHash();

        boolean hasAppInstanceId();

        boolean hasAppInstanceIdToken();

        boolean hasAppVersion();

        boolean hasAppVersionCode();

        boolean hasCertHash();

        boolean hasConfigId();

        boolean hasDigest();

        boolean hasFetchedConfigAgeSeconds();

        boolean hasGamesProjectId();

        boolean hasGmpProjectId();

        boolean hasPackageName();

        boolean hasRequestedCacheExpirationSeconds();

        boolean hasSdkVersion();

        boolean hasVersionCode();
    }

    /* compiled from: com.google.firebase:firebase-config@@19.0.3 */
    public interface PackageTableOrBuilder extends MessageLiteOrBuilder {
        String getConfigId();

        ByteString getConfigIdBytes();

        KeyValue getEntry(int i);

        int getEntryCount();

        List<KeyValue> getEntryList();

        String getPackageName();

        ByteString getPackageNameBytes();

        boolean hasConfigId();

        boolean hasPackageName();
    }

    public static void registerAllExtensions(ExtensionRegistryLite extensionRegistryLite) {
    }

    private Config() {
    }

    /* compiled from: com.google.firebase:firebase-config@@19.0.3 */
    public static final class PackageData extends GeneratedMessageLite<PackageData, Builder> implements PackageDataOrBuilder {
        public static final int ACTIVE_CONFIG_AGE_SECONDS_FIELD_NUMBER = 20;
        public static final int ANALYTICS_USER_PROPERTY_FIELD_NUMBER = 17;
        public static final int APP_CERT_HASH_FIELD_NUMBER = 10;
        public static final int APP_INSTANCE_ID_FIELD_NUMBER = 12;
        public static final int APP_INSTANCE_ID_TOKEN_FIELD_NUMBER = 14;
        public static final int APP_VERSION_CODE_FIELD_NUMBER = 11;
        public static final int APP_VERSION_FIELD_NUMBER = 13;
        public static final int CERT_HASH_FIELD_NUMBER = 4;
        public static final int CONFIG_ID_FIELD_NUMBER = 5;
        public static final int CUSTOM_VARIABLE_FIELD_NUMBER = 9;
        /* access modifiers changed from: private */
        public static final PackageData DEFAULT_INSTANCE;
        public static final int DIGEST_FIELD_NUMBER = 3;
        public static final int FETCHED_CONFIG_AGE_SECONDS_FIELD_NUMBER = 19;
        public static final int GAMES_PROJECT_ID_FIELD_NUMBER = 7;
        public static final int GMP_PROJECT_ID_FIELD_NUMBER = 6;
        public static final int NAMESPACE_DIGEST_FIELD_NUMBER = 8;
        public static final int PACKAGE_NAME_FIELD_NUMBER = 1;
        private static volatile Parser<PackageData> PARSER = null;
        public static final int REQUESTED_CACHE_EXPIRATION_SECONDS_FIELD_NUMBER = 18;
        public static final int REQUESTED_HIDDEN_NAMESPACE_FIELD_NUMBER = 15;
        public static final int SDK_VERSION_FIELD_NUMBER = 16;
        public static final int VERSION_CODE_FIELD_NUMBER = 2;
        private int activeConfigAgeSeconds_;
        private Internal.ProtobufList<NamedValue> analyticsUserProperty_ = emptyProtobufList();
        private ByteString appCertHash_ = ByteString.EMPTY;
        private String appInstanceIdToken_ = "";
        private String appInstanceId_ = "";
        private int appVersionCode_;
        private String appVersion_ = "";
        private int bitField0_;
        private ByteString certHash_ = ByteString.EMPTY;
        private String configId_ = "";
        private Internal.ProtobufList<NamedValue> customVariable_ = emptyProtobufList();
        private ByteString digest_ = ByteString.EMPTY;
        private int fetchedConfigAgeSeconds_;
        private String gamesProjectId_ = "";
        private String gmpProjectId_ = "";
        private Internal.ProtobufList<NamedValue> namespaceDigest_ = emptyProtobufList();
        private String packageName_ = "";
        private int requestedCacheExpirationSeconds_;
        private Internal.ProtobufList<String> requestedHiddenNamespace_ = GeneratedMessageLite.emptyProtobufList();
        private int sdkVersion_;
        private int versionCode_;

        private PackageData() {
        }

        public boolean hasVersionCode() {
            return (this.bitField0_ & 1) == 1;
        }

        public int getVersionCode() {
            return this.versionCode_;
        }

        /* access modifiers changed from: private */
        public void setVersionCode(int i) {
            this.bitField0_ |= 1;
            this.versionCode_ = i;
        }

        /* access modifiers changed from: private */
        public void clearVersionCode() {
            this.bitField0_ &= -2;
            this.versionCode_ = 0;
        }

        public boolean hasDigest() {
            return (this.bitField0_ & 2) == 2;
        }

        public ByteString getDigest() {
            return this.digest_;
        }

        /* access modifiers changed from: private */
        public void setDigest(ByteString byteString) {
            if (byteString != null) {
                this.bitField0_ |= 2;
                this.digest_ = byteString;
                return;
            }
            throw null;
        }

        /* access modifiers changed from: private */
        public void clearDigest() {
            this.bitField0_ &= -3;
            this.digest_ = getDefaultInstance().getDigest();
        }

        public boolean hasCertHash() {
            return (this.bitField0_ & 4) == 4;
        }

        public ByteString getCertHash() {
            return this.certHash_;
        }

        /* access modifiers changed from: private */
        public void setCertHash(ByteString byteString) {
            if (byteString != null) {
                this.bitField0_ |= 4;
                this.certHash_ = byteString;
                return;
            }
            throw null;
        }

        /* access modifiers changed from: private */
        public void clearCertHash() {
            this.bitField0_ &= -5;
            this.certHash_ = getDefaultInstance().getCertHash();
        }

        public boolean hasConfigId() {
            return (this.bitField0_ & 8) == 8;
        }

        public String getConfigId() {
            return this.configId_;
        }

        public ByteString getConfigIdBytes() {
            return ByteString.copyFromUtf8(this.configId_);
        }

        /* access modifiers changed from: private */
        public void setConfigId(String str) {
            if (str != null) {
                this.bitField0_ |= 8;
                this.configId_ = str;
                return;
            }
            throw null;
        }

        /* access modifiers changed from: private */
        public void clearConfigId() {
            this.bitField0_ &= -9;
            this.configId_ = getDefaultInstance().getConfigId();
        }

        /* access modifiers changed from: private */
        public void setConfigIdBytes(ByteString byteString) {
            if (byteString != null) {
                this.bitField0_ |= 8;
                this.configId_ = byteString.toStringUtf8();
                return;
            }
            throw null;
        }

        public boolean hasPackageName() {
            return (this.bitField0_ & 16) == 16;
        }

        public String getPackageName() {
            return this.packageName_;
        }

        public ByteString getPackageNameBytes() {
            return ByteString.copyFromUtf8(this.packageName_);
        }

        /* access modifiers changed from: private */
        public void setPackageName(String str) {
            if (str != null) {
                this.bitField0_ |= 16;
                this.packageName_ = str;
                return;
            }
            throw null;
        }

        /* access modifiers changed from: private */
        public void clearPackageName() {
            this.bitField0_ &= -17;
            this.packageName_ = getDefaultInstance().getPackageName();
        }

        /* access modifiers changed from: private */
        public void setPackageNameBytes(ByteString byteString) {
            if (byteString != null) {
                this.bitField0_ |= 16;
                this.packageName_ = byteString.toStringUtf8();
                return;
            }
            throw null;
        }

        public boolean hasGmpProjectId() {
            return (this.bitField0_ & 32) == 32;
        }

        public String getGmpProjectId() {
            return this.gmpProjectId_;
        }

        public ByteString getGmpProjectIdBytes() {
            return ByteString.copyFromUtf8(this.gmpProjectId_);
        }

        /* access modifiers changed from: private */
        public void setGmpProjectId(String str) {
            if (str != null) {
                this.bitField0_ |= 32;
                this.gmpProjectId_ = str;
                return;
            }
            throw null;
        }

        /* access modifiers changed from: private */
        public void clearGmpProjectId() {
            this.bitField0_ &= -33;
            this.gmpProjectId_ = getDefaultInstance().getGmpProjectId();
        }

        /* access modifiers changed from: private */
        public void setGmpProjectIdBytes(ByteString byteString) {
            if (byteString != null) {
                this.bitField0_ |= 32;
                this.gmpProjectId_ = byteString.toStringUtf8();
                return;
            }
            throw null;
        }

        public boolean hasGamesProjectId() {
            return (this.bitField0_ & 64) == 64;
        }

        public String getGamesProjectId() {
            return this.gamesProjectId_;
        }

        public ByteString getGamesProjectIdBytes() {
            return ByteString.copyFromUtf8(this.gamesProjectId_);
        }

        /* access modifiers changed from: private */
        public void setGamesProjectId(String str) {
            if (str != null) {
                this.bitField0_ |= 64;
                this.gamesProjectId_ = str;
                return;
            }
            throw null;
        }

        /* access modifiers changed from: private */
        public void clearGamesProjectId() {
            this.bitField0_ &= -65;
            this.gamesProjectId_ = getDefaultInstance().getGamesProjectId();
        }

        /* access modifiers changed from: private */
        public void setGamesProjectIdBytes(ByteString byteString) {
            if (byteString != null) {
                this.bitField0_ |= 64;
                this.gamesProjectId_ = byteString.toStringUtf8();
                return;
            }
            throw null;
        }

        public List<NamedValue> getNamespaceDigestList() {
            return this.namespaceDigest_;
        }

        public List<? extends NamedValueOrBuilder> getNamespaceDigestOrBuilderList() {
            return this.namespaceDigest_;
        }

        public int getNamespaceDigestCount() {
            return this.namespaceDigest_.size();
        }

        public NamedValue getNamespaceDigest(int i) {
            return (NamedValue) this.namespaceDigest_.get(i);
        }

        public NamedValueOrBuilder getNamespaceDigestOrBuilder(int i) {
            return (NamedValueOrBuilder) this.namespaceDigest_.get(i);
        }

        private void ensureNamespaceDigestIsMutable() {
            if (!this.namespaceDigest_.isModifiable()) {
                this.namespaceDigest_ = GeneratedMessageLite.mutableCopy(this.namespaceDigest_);
            }
        }

        /* access modifiers changed from: private */
        public void setNamespaceDigest(int i, NamedValue namedValue) {
            if (namedValue != null) {
                ensureNamespaceDigestIsMutable();
                this.namespaceDigest_.set(i, namedValue);
                return;
            }
            throw null;
        }

        /* access modifiers changed from: private */
        public void setNamespaceDigest(int i, NamedValue.Builder builder) {
            ensureNamespaceDigestIsMutable();
            this.namespaceDigest_.set(i, (NamedValue) builder.build());
        }

        /* access modifiers changed from: private */
        public void addNamespaceDigest(NamedValue namedValue) {
            if (namedValue != null) {
                ensureNamespaceDigestIsMutable();
                this.namespaceDigest_.add(namedValue);
                return;
            }
            throw null;
        }

        /* access modifiers changed from: private */
        public void addNamespaceDigest(int i, NamedValue namedValue) {
            if (namedValue != null) {
                ensureNamespaceDigestIsMutable();
                this.namespaceDigest_.add(i, namedValue);
                return;
            }
            throw null;
        }

        /* access modifiers changed from: private */
        public void addNamespaceDigest(NamedValue.Builder builder) {
            ensureNamespaceDigestIsMutable();
            this.namespaceDigest_.add((NamedValue) builder.build());
        }

        /* access modifiers changed from: private */
        public void addNamespaceDigest(int i, NamedValue.Builder builder) {
            ensureNamespaceDigestIsMutable();
            this.namespaceDigest_.add(i, (NamedValue) builder.build());
        }

        /* access modifiers changed from: private */
        public void addAllNamespaceDigest(Iterable<? extends NamedValue> iterable) {
            ensureNamespaceDigestIsMutable();
            AbstractMessageLite.addAll(iterable, this.namespaceDigest_);
        }

        /* access modifiers changed from: private */
        public void clearNamespaceDigest() {
            this.namespaceDigest_ = emptyProtobufList();
        }

        /* access modifiers changed from: private */
        public void removeNamespaceDigest(int i) {
            ensureNamespaceDigestIsMutable();
            this.namespaceDigest_.remove(i);
        }

        public List<NamedValue> getCustomVariableList() {
            return this.customVariable_;
        }

        public List<? extends NamedValueOrBuilder> getCustomVariableOrBuilderList() {
            return this.customVariable_;
        }

        public int getCustomVariableCount() {
            return this.customVariable_.size();
        }

        public NamedValue getCustomVariable(int i) {
            return (NamedValue) this.customVariable_.get(i);
        }

        public NamedValueOrBuilder getCustomVariableOrBuilder(int i) {
            return (NamedValueOrBuilder) this.customVariable_.get(i);
        }

        private void ensureCustomVariableIsMutable() {
            if (!this.customVariable_.isModifiable()) {
                this.customVariable_ = GeneratedMessageLite.mutableCopy(this.customVariable_);
            }
        }

        /* access modifiers changed from: private */
        public void setCustomVariable(int i, NamedValue namedValue) {
            if (namedValue != null) {
                ensureCustomVariableIsMutable();
                this.customVariable_.set(i, namedValue);
                return;
            }
            throw null;
        }

        /* access modifiers changed from: private */
        public void setCustomVariable(int i, NamedValue.Builder builder) {
            ensureCustomVariableIsMutable();
            this.customVariable_.set(i, (NamedValue) builder.build());
        }

        /* access modifiers changed from: private */
        public void addCustomVariable(NamedValue namedValue) {
            if (namedValue != null) {
                ensureCustomVariableIsMutable();
                this.customVariable_.add(namedValue);
                return;
            }
            throw null;
        }

        /* access modifiers changed from: private */
        public void addCustomVariable(int i, NamedValue namedValue) {
            if (namedValue != null) {
                ensureCustomVariableIsMutable();
                this.customVariable_.add(i, namedValue);
                return;
            }
            throw null;
        }

        /* access modifiers changed from: private */
        public void addCustomVariable(NamedValue.Builder builder) {
            ensureCustomVariableIsMutable();
            this.customVariable_.add((NamedValue) builder.build());
        }

        /* access modifiers changed from: private */
        public void addCustomVariable(int i, NamedValue.Builder builder) {
            ensureCustomVariableIsMutable();
            this.customVariable_.add(i, (NamedValue) builder.build());
        }

        /* access modifiers changed from: private */
        public void addAllCustomVariable(Iterable<? extends NamedValue> iterable) {
            ensureCustomVariableIsMutable();
            AbstractMessageLite.addAll(iterable, this.customVariable_);
        }

        /* access modifiers changed from: private */
        public void clearCustomVariable() {
            this.customVariable_ = emptyProtobufList();
        }

        /* access modifiers changed from: private */
        public void removeCustomVariable(int i) {
            ensureCustomVariableIsMutable();
            this.customVariable_.remove(i);
        }

        public boolean hasAppCertHash() {
            return (this.bitField0_ & 128) == 128;
        }

        public ByteString getAppCertHash() {
            return this.appCertHash_;
        }

        /* access modifiers changed from: private */
        public void setAppCertHash(ByteString byteString) {
            if (byteString != null) {
                this.bitField0_ |= 128;
                this.appCertHash_ = byteString;
                return;
            }
            throw null;
        }

        /* access modifiers changed from: private */
        public void clearAppCertHash() {
            this.bitField0_ &= -129;
            this.appCertHash_ = getDefaultInstance().getAppCertHash();
        }

        public boolean hasAppVersionCode() {
            return (this.bitField0_ & 256) == 256;
        }

        public int getAppVersionCode() {
            return this.appVersionCode_;
        }

        /* access modifiers changed from: private */
        public void setAppVersionCode(int i) {
            this.bitField0_ |= 256;
            this.appVersionCode_ = i;
        }

        /* access modifiers changed from: private */
        public void clearAppVersionCode() {
            this.bitField0_ &= -257;
            this.appVersionCode_ = 0;
        }

        public boolean hasAppVersion() {
            return (this.bitField0_ & 512) == 512;
        }

        public String getAppVersion() {
            return this.appVersion_;
        }

        public ByteString getAppVersionBytes() {
            return ByteString.copyFromUtf8(this.appVersion_);
        }

        /* access modifiers changed from: private */
        public void setAppVersion(String str) {
            if (str != null) {
                this.bitField0_ |= 512;
                this.appVersion_ = str;
                return;
            }
            throw null;
        }

        /* access modifiers changed from: private */
        public void clearAppVersion() {
            this.bitField0_ &= -513;
            this.appVersion_ = getDefaultInstance().getAppVersion();
        }

        /* access modifiers changed from: private */
        public void setAppVersionBytes(ByteString byteString) {
            if (byteString != null) {
                this.bitField0_ |= 512;
                this.appVersion_ = byteString.toStringUtf8();
                return;
            }
            throw null;
        }

        public boolean hasAppInstanceId() {
            return (this.bitField0_ & 1024) == 1024;
        }

        public String getAppInstanceId() {
            return this.appInstanceId_;
        }

        public ByteString getAppInstanceIdBytes() {
            return ByteString.copyFromUtf8(this.appInstanceId_);
        }

        /* access modifiers changed from: private */
        public void setAppInstanceId(String str) {
            if (str != null) {
                this.bitField0_ |= 1024;
                this.appInstanceId_ = str;
                return;
            }
            throw null;
        }

        /* access modifiers changed from: private */
        public void clearAppInstanceId() {
            this.bitField0_ &= -1025;
            this.appInstanceId_ = getDefaultInstance().getAppInstanceId();
        }

        /* access modifiers changed from: private */
        public void setAppInstanceIdBytes(ByteString byteString) {
            if (byteString != null) {
                this.bitField0_ |= 1024;
                this.appInstanceId_ = byteString.toStringUtf8();
                return;
            }
            throw null;
        }

        public boolean hasAppInstanceIdToken() {
            return (this.bitField0_ & 2048) == 2048;
        }

        public String getAppInstanceIdToken() {
            return this.appInstanceIdToken_;
        }

        public ByteString getAppInstanceIdTokenBytes() {
            return ByteString.copyFromUtf8(this.appInstanceIdToken_);
        }

        /* access modifiers changed from: private */
        public void setAppInstanceIdToken(String str) {
            if (str != null) {
                this.bitField0_ |= 2048;
                this.appInstanceIdToken_ = str;
                return;
            }
            throw null;
        }

        /* access modifiers changed from: private */
        public void clearAppInstanceIdToken() {
            this.bitField0_ &= -2049;
            this.appInstanceIdToken_ = getDefaultInstance().getAppInstanceIdToken();
        }

        /* access modifiers changed from: private */
        public void setAppInstanceIdTokenBytes(ByteString byteString) {
            if (byteString != null) {
                this.bitField0_ |= 2048;
                this.appInstanceIdToken_ = byteString.toStringUtf8();
                return;
            }
            throw null;
        }

        public List<String> getRequestedHiddenNamespaceList() {
            return this.requestedHiddenNamespace_;
        }

        public int getRequestedHiddenNamespaceCount() {
            return this.requestedHiddenNamespace_.size();
        }

        public String getRequestedHiddenNamespace(int i) {
            return (String) this.requestedHiddenNamespace_.get(i);
        }

        public ByteString getRequestedHiddenNamespaceBytes(int i) {
            return ByteString.copyFromUtf8((String) this.requestedHiddenNamespace_.get(i));
        }

        private void ensureRequestedHiddenNamespaceIsMutable() {
            if (!this.requestedHiddenNamespace_.isModifiable()) {
                this.requestedHiddenNamespace_ = GeneratedMessageLite.mutableCopy(this.requestedHiddenNamespace_);
            }
        }

        /* access modifiers changed from: private */
        public void setRequestedHiddenNamespace(int i, String str) {
            if (str != null) {
                ensureRequestedHiddenNamespaceIsMutable();
                this.requestedHiddenNamespace_.set(i, str);
                return;
            }
            throw null;
        }

        /* access modifiers changed from: private */
        public void addRequestedHiddenNamespace(String str) {
            if (str != null) {
                ensureRequestedHiddenNamespaceIsMutable();
                this.requestedHiddenNamespace_.add(str);
                return;
            }
            throw null;
        }

        /* access modifiers changed from: private */
        public void addAllRequestedHiddenNamespace(Iterable<String> iterable) {
            ensureRequestedHiddenNamespaceIsMutable();
            AbstractMessageLite.addAll(iterable, this.requestedHiddenNamespace_);
        }

        /* access modifiers changed from: private */
        public void clearRequestedHiddenNamespace() {
            this.requestedHiddenNamespace_ = GeneratedMessageLite.emptyProtobufList();
        }

        /* access modifiers changed from: private */
        public void addRequestedHiddenNamespaceBytes(ByteString byteString) {
            if (byteString != null) {
                ensureRequestedHiddenNamespaceIsMutable();
                this.requestedHiddenNamespace_.add(byteString.toStringUtf8());
                return;
            }
            throw null;
        }

        public boolean hasSdkVersion() {
            return (this.bitField0_ & 4096) == 4096;
        }

        public int getSdkVersion() {
            return this.sdkVersion_;
        }

        /* access modifiers changed from: private */
        public void setSdkVersion(int i) {
            this.bitField0_ |= 4096;
            this.sdkVersion_ = i;
        }

        /* access modifiers changed from: private */
        public void clearSdkVersion() {
            this.bitField0_ &= -4097;
            this.sdkVersion_ = 0;
        }

        public List<NamedValue> getAnalyticsUserPropertyList() {
            return this.analyticsUserProperty_;
        }

        public List<? extends NamedValueOrBuilder> getAnalyticsUserPropertyOrBuilderList() {
            return this.analyticsUserProperty_;
        }

        public int getAnalyticsUserPropertyCount() {
            return this.analyticsUserProperty_.size();
        }

        public NamedValue getAnalyticsUserProperty(int i) {
            return (NamedValue) this.analyticsUserProperty_.get(i);
        }

        public NamedValueOrBuilder getAnalyticsUserPropertyOrBuilder(int i) {
            return (NamedValueOrBuilder) this.analyticsUserProperty_.get(i);
        }

        private void ensureAnalyticsUserPropertyIsMutable() {
            if (!this.analyticsUserProperty_.isModifiable()) {
                this.analyticsUserProperty_ = GeneratedMessageLite.mutableCopy(this.analyticsUserProperty_);
            }
        }

        /* access modifiers changed from: private */
        public void setAnalyticsUserProperty(int i, NamedValue namedValue) {
            if (namedValue != null) {
                ensureAnalyticsUserPropertyIsMutable();
                this.analyticsUserProperty_.set(i, namedValue);
                return;
            }
            throw null;
        }

        /* access modifiers changed from: private */
        public void setAnalyticsUserProperty(int i, NamedValue.Builder builder) {
            ensureAnalyticsUserPropertyIsMutable();
            this.analyticsUserProperty_.set(i, (NamedValue) builder.build());
        }

        /* access modifiers changed from: private */
        public void addAnalyticsUserProperty(NamedValue namedValue) {
            if (namedValue != null) {
                ensureAnalyticsUserPropertyIsMutable();
                this.analyticsUserProperty_.add(namedValue);
                return;
            }
            throw null;
        }

        /* access modifiers changed from: private */
        public void addAnalyticsUserProperty(int i, NamedValue namedValue) {
            if (namedValue != null) {
                ensureAnalyticsUserPropertyIsMutable();
                this.analyticsUserProperty_.add(i, namedValue);
                return;
            }
            throw null;
        }

        /* access modifiers changed from: private */
        public void addAnalyticsUserProperty(NamedValue.Builder builder) {
            ensureAnalyticsUserPropertyIsMutable();
            this.analyticsUserProperty_.add((NamedValue) builder.build());
        }

        /* access modifiers changed from: private */
        public void addAnalyticsUserProperty(int i, NamedValue.Builder builder) {
            ensureAnalyticsUserPropertyIsMutable();
            this.analyticsUserProperty_.add(i, (NamedValue) builder.build());
        }

        /* access modifiers changed from: private */
        public void addAllAnalyticsUserProperty(Iterable<? extends NamedValue> iterable) {
            ensureAnalyticsUserPropertyIsMutable();
            AbstractMessageLite.addAll(iterable, this.analyticsUserProperty_);
        }

        /* access modifiers changed from: private */
        public void clearAnalyticsUserProperty() {
            this.analyticsUserProperty_ = emptyProtobufList();
        }

        /* access modifiers changed from: private */
        public void removeAnalyticsUserProperty(int i) {
            ensureAnalyticsUserPropertyIsMutable();
            this.analyticsUserProperty_.remove(i);
        }

        public boolean hasRequestedCacheExpirationSeconds() {
            return (this.bitField0_ & 8192) == 8192;
        }

        public int getRequestedCacheExpirationSeconds() {
            return this.requestedCacheExpirationSeconds_;
        }

        /* access modifiers changed from: private */
        public void setRequestedCacheExpirationSeconds(int i) {
            this.bitField0_ |= 8192;
            this.requestedCacheExpirationSeconds_ = i;
        }

        /* access modifiers changed from: private */
        public void clearRequestedCacheExpirationSeconds() {
            this.bitField0_ &= -8193;
            this.requestedCacheExpirationSeconds_ = 0;
        }

        public boolean hasFetchedConfigAgeSeconds() {
            return (this.bitField0_ & 16384) == 16384;
        }

        public int getFetchedConfigAgeSeconds() {
            return this.fetchedConfigAgeSeconds_;
        }

        /* access modifiers changed from: private */
        public void setFetchedConfigAgeSeconds(int i) {
            this.bitField0_ |= 16384;
            this.fetchedConfigAgeSeconds_ = i;
        }

        /* access modifiers changed from: private */
        public void clearFetchedConfigAgeSeconds() {
            this.bitField0_ &= -16385;
            this.fetchedConfigAgeSeconds_ = 0;
        }

        public boolean hasActiveConfigAgeSeconds() {
            return (this.bitField0_ & 32768) == 32768;
        }

        public int getActiveConfigAgeSeconds() {
            return this.activeConfigAgeSeconds_;
        }

        /* access modifiers changed from: private */
        public void setActiveConfigAgeSeconds(int i) {
            this.bitField0_ |= 32768;
            this.activeConfigAgeSeconds_ = i;
        }

        /* access modifiers changed from: private */
        public void clearActiveConfigAgeSeconds() {
            this.bitField0_ &= -32769;
            this.activeConfigAgeSeconds_ = 0;
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if ((this.bitField0_ & 16) == 16) {
                codedOutputStream.writeString(1, getPackageName());
            }
            if ((this.bitField0_ & 1) == 1) {
                codedOutputStream.writeInt32(2, this.versionCode_);
            }
            if ((this.bitField0_ & 2) == 2) {
                codedOutputStream.writeBytes(3, this.digest_);
            }
            if ((this.bitField0_ & 4) == 4) {
                codedOutputStream.writeBytes(4, this.certHash_);
            }
            if ((this.bitField0_ & 8) == 8) {
                codedOutputStream.writeString(5, getConfigId());
            }
            if ((this.bitField0_ & 32) == 32) {
                codedOutputStream.writeString(6, getGmpProjectId());
            }
            if ((this.bitField0_ & 64) == 64) {
                codedOutputStream.writeString(7, getGamesProjectId());
            }
            for (int i = 0; i < this.namespaceDigest_.size(); i++) {
                codedOutputStream.writeMessage(8, (MessageLite) this.namespaceDigest_.get(i));
            }
            for (int i2 = 0; i2 < this.customVariable_.size(); i2++) {
                codedOutputStream.writeMessage(9, (MessageLite) this.customVariable_.get(i2));
            }
            if ((this.bitField0_ & 128) == 128) {
                codedOutputStream.writeBytes(10, this.appCertHash_);
            }
            if ((this.bitField0_ & 256) == 256) {
                codedOutputStream.writeInt32(11, this.appVersionCode_);
            }
            if ((this.bitField0_ & 1024) == 1024) {
                codedOutputStream.writeString(12, getAppInstanceId());
            }
            if ((this.bitField0_ & 512) == 512) {
                codedOutputStream.writeString(13, getAppVersion());
            }
            if ((this.bitField0_ & 2048) == 2048) {
                codedOutputStream.writeString(14, getAppInstanceIdToken());
            }
            for (int i3 = 0; i3 < this.requestedHiddenNamespace_.size(); i3++) {
                codedOutputStream.writeString(15, (String) this.requestedHiddenNamespace_.get(i3));
            }
            if ((this.bitField0_ & 4096) == 4096) {
                codedOutputStream.writeInt32(16, this.sdkVersion_);
            }
            for (int i4 = 0; i4 < this.analyticsUserProperty_.size(); i4++) {
                codedOutputStream.writeMessage(17, (MessageLite) this.analyticsUserProperty_.get(i4));
            }
            if ((this.bitField0_ & 8192) == 8192) {
                codedOutputStream.writeInt32(18, this.requestedCacheExpirationSeconds_);
            }
            if ((this.bitField0_ & 16384) == 16384) {
                codedOutputStream.writeInt32(19, this.fetchedConfigAgeSeconds_);
            }
            if ((this.bitField0_ & 32768) == 32768) {
                codedOutputStream.writeInt32(20, this.activeConfigAgeSeconds_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int computeStringSize = (this.bitField0_ & 16) == 16 ? CodedOutputStream.computeStringSize(1, getPackageName()) + 0 : 0;
            if ((this.bitField0_ & 1) == 1) {
                computeStringSize += CodedOutputStream.computeInt32Size(2, this.versionCode_);
            }
            if ((this.bitField0_ & 2) == 2) {
                computeStringSize += CodedOutputStream.computeBytesSize(3, this.digest_);
            }
            if ((this.bitField0_ & 4) == 4) {
                computeStringSize += CodedOutputStream.computeBytesSize(4, this.certHash_);
            }
            if ((this.bitField0_ & 8) == 8) {
                computeStringSize += CodedOutputStream.computeStringSize(5, getConfigId());
            }
            if ((this.bitField0_ & 32) == 32) {
                computeStringSize += CodedOutputStream.computeStringSize(6, getGmpProjectId());
            }
            if ((this.bitField0_ & 64) == 64) {
                computeStringSize += CodedOutputStream.computeStringSize(7, getGamesProjectId());
            }
            for (int i2 = 0; i2 < this.namespaceDigest_.size(); i2++) {
                computeStringSize += CodedOutputStream.computeMessageSize(8, (MessageLite) this.namespaceDigest_.get(i2));
            }
            for (int i3 = 0; i3 < this.customVariable_.size(); i3++) {
                computeStringSize += CodedOutputStream.computeMessageSize(9, (MessageLite) this.customVariable_.get(i3));
            }
            if ((this.bitField0_ & 128) == 128) {
                computeStringSize += CodedOutputStream.computeBytesSize(10, this.appCertHash_);
            }
            if ((this.bitField0_ & 256) == 256) {
                computeStringSize += CodedOutputStream.computeInt32Size(11, this.appVersionCode_);
            }
            if ((this.bitField0_ & 1024) == 1024) {
                computeStringSize += CodedOutputStream.computeStringSize(12, getAppInstanceId());
            }
            if ((this.bitField0_ & 512) == 512) {
                computeStringSize += CodedOutputStream.computeStringSize(13, getAppVersion());
            }
            if ((this.bitField0_ & 2048) == 2048) {
                computeStringSize += CodedOutputStream.computeStringSize(14, getAppInstanceIdToken());
            }
            int i4 = 0;
            for (int i5 = 0; i5 < this.requestedHiddenNamespace_.size(); i5++) {
                i4 += CodedOutputStream.computeStringSizeNoTag((String) this.requestedHiddenNamespace_.get(i5));
            }
            int size = computeStringSize + i4 + (getRequestedHiddenNamespaceList().size() * 1);
            if ((this.bitField0_ & 4096) == 4096) {
                size += CodedOutputStream.computeInt32Size(16, this.sdkVersion_);
            }
            for (int i6 = 0; i6 < this.analyticsUserProperty_.size(); i6++) {
                size += CodedOutputStream.computeMessageSize(17, (MessageLite) this.analyticsUserProperty_.get(i6));
            }
            if ((this.bitField0_ & 8192) == 8192) {
                size += CodedOutputStream.computeInt32Size(18, this.requestedCacheExpirationSeconds_);
            }
            if ((this.bitField0_ & 16384) == 16384) {
                size += CodedOutputStream.computeInt32Size(19, this.fetchedConfigAgeSeconds_);
            }
            if ((this.bitField0_ & 32768) == 32768) {
                size += CodedOutputStream.computeInt32Size(20, this.activeConfigAgeSeconds_);
            }
            int serializedSize = size + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        public static PackageData parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (PackageData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static PackageData parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (PackageData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static PackageData parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (PackageData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static PackageData parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (PackageData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static PackageData parseFrom(InputStream inputStream) throws IOException {
            return (PackageData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static PackageData parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (PackageData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static PackageData parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (PackageData) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static PackageData parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (PackageData) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static PackageData parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (PackageData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static PackageData parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (PackageData) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(PackageData packageData) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(packageData);
        }

        /* compiled from: com.google.firebase:firebase-config@@19.0.3 */
        public static final class Builder extends GeneratedMessageLite.Builder<PackageData, Builder> implements PackageDataOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 r1) {
                this();
            }

            private Builder() {
                super(PackageData.DEFAULT_INSTANCE);
            }

            public boolean hasVersionCode() {
                return ((PackageData) this.instance).hasVersionCode();
            }

            public int getVersionCode() {
                return ((PackageData) this.instance).getVersionCode();
            }

            public Builder setVersionCode(int i) {
                copyOnWrite();
                ((PackageData) this.instance).setVersionCode(i);
                return this;
            }

            public Builder clearVersionCode() {
                copyOnWrite();
                ((PackageData) this.instance).clearVersionCode();
                return this;
            }

            public boolean hasDigest() {
                return ((PackageData) this.instance).hasDigest();
            }

            public ByteString getDigest() {
                return ((PackageData) this.instance).getDigest();
            }

            public Builder setDigest(ByteString byteString) {
                copyOnWrite();
                ((PackageData) this.instance).setDigest(byteString);
                return this;
            }

            public Builder clearDigest() {
                copyOnWrite();
                ((PackageData) this.instance).clearDigest();
                return this;
            }

            public boolean hasCertHash() {
                return ((PackageData) this.instance).hasCertHash();
            }

            public ByteString getCertHash() {
                return ((PackageData) this.instance).getCertHash();
            }

            public Builder setCertHash(ByteString byteString) {
                copyOnWrite();
                ((PackageData) this.instance).setCertHash(byteString);
                return this;
            }

            public Builder clearCertHash() {
                copyOnWrite();
                ((PackageData) this.instance).clearCertHash();
                return this;
            }

            public boolean hasConfigId() {
                return ((PackageData) this.instance).hasConfigId();
            }

            public String getConfigId() {
                return ((PackageData) this.instance).getConfigId();
            }

            public ByteString getConfigIdBytes() {
                return ((PackageData) this.instance).getConfigIdBytes();
            }

            public Builder setConfigId(String str) {
                copyOnWrite();
                ((PackageData) this.instance).setConfigId(str);
                return this;
            }

            public Builder clearConfigId() {
                copyOnWrite();
                ((PackageData) this.instance).clearConfigId();
                return this;
            }

            public Builder setConfigIdBytes(ByteString byteString) {
                copyOnWrite();
                ((PackageData) this.instance).setConfigIdBytes(byteString);
                return this;
            }

            public boolean hasPackageName() {
                return ((PackageData) this.instance).hasPackageName();
            }

            public String getPackageName() {
                return ((PackageData) this.instance).getPackageName();
            }

            public ByteString getPackageNameBytes() {
                return ((PackageData) this.instance).getPackageNameBytes();
            }

            public Builder setPackageName(String str) {
                copyOnWrite();
                ((PackageData) this.instance).setPackageName(str);
                return this;
            }

            public Builder clearPackageName() {
                copyOnWrite();
                ((PackageData) this.instance).clearPackageName();
                return this;
            }

            public Builder setPackageNameBytes(ByteString byteString) {
                copyOnWrite();
                ((PackageData) this.instance).setPackageNameBytes(byteString);
                return this;
            }

            public boolean hasGmpProjectId() {
                return ((PackageData) this.instance).hasGmpProjectId();
            }

            public String getGmpProjectId() {
                return ((PackageData) this.instance).getGmpProjectId();
            }

            public ByteString getGmpProjectIdBytes() {
                return ((PackageData) this.instance).getGmpProjectIdBytes();
            }

            public Builder setGmpProjectId(String str) {
                copyOnWrite();
                ((PackageData) this.instance).setGmpProjectId(str);
                return this;
            }

            public Builder clearGmpProjectId() {
                copyOnWrite();
                ((PackageData) this.instance).clearGmpProjectId();
                return this;
            }

            public Builder setGmpProjectIdBytes(ByteString byteString) {
                copyOnWrite();
                ((PackageData) this.instance).setGmpProjectIdBytes(byteString);
                return this;
            }

            public boolean hasGamesProjectId() {
                return ((PackageData) this.instance).hasGamesProjectId();
            }

            public String getGamesProjectId() {
                return ((PackageData) this.instance).getGamesProjectId();
            }

            public ByteString getGamesProjectIdBytes() {
                return ((PackageData) this.instance).getGamesProjectIdBytes();
            }

            public Builder setGamesProjectId(String str) {
                copyOnWrite();
                ((PackageData) this.instance).setGamesProjectId(str);
                return this;
            }

            public Builder clearGamesProjectId() {
                copyOnWrite();
                ((PackageData) this.instance).clearGamesProjectId();
                return this;
            }

            public Builder setGamesProjectIdBytes(ByteString byteString) {
                copyOnWrite();
                ((PackageData) this.instance).setGamesProjectIdBytes(byteString);
                return this;
            }

            public List<NamedValue> getNamespaceDigestList() {
                return Collections.unmodifiableList(((PackageData) this.instance).getNamespaceDigestList());
            }

            public int getNamespaceDigestCount() {
                return ((PackageData) this.instance).getNamespaceDigestCount();
            }

            public NamedValue getNamespaceDigest(int i) {
                return ((PackageData) this.instance).getNamespaceDigest(i);
            }

            public Builder setNamespaceDigest(int i, NamedValue namedValue) {
                copyOnWrite();
                ((PackageData) this.instance).setNamespaceDigest(i, namedValue);
                return this;
            }

            public Builder setNamespaceDigest(int i, NamedValue.Builder builder) {
                copyOnWrite();
                ((PackageData) this.instance).setNamespaceDigest(i, builder);
                return this;
            }

            public Builder addNamespaceDigest(NamedValue namedValue) {
                copyOnWrite();
                ((PackageData) this.instance).addNamespaceDigest(namedValue);
                return this;
            }

            public Builder addNamespaceDigest(int i, NamedValue namedValue) {
                copyOnWrite();
                ((PackageData) this.instance).addNamespaceDigest(i, namedValue);
                return this;
            }

            public Builder addNamespaceDigest(NamedValue.Builder builder) {
                copyOnWrite();
                ((PackageData) this.instance).addNamespaceDigest(builder);
                return this;
            }

            public Builder addNamespaceDigest(int i, NamedValue.Builder builder) {
                copyOnWrite();
                ((PackageData) this.instance).addNamespaceDigest(i, builder);
                return this;
            }

            public Builder addAllNamespaceDigest(Iterable<? extends NamedValue> iterable) {
                copyOnWrite();
                ((PackageData) this.instance).addAllNamespaceDigest(iterable);
                return this;
            }

            public Builder clearNamespaceDigest() {
                copyOnWrite();
                ((PackageData) this.instance).clearNamespaceDigest();
                return this;
            }

            public Builder removeNamespaceDigest(int i) {
                copyOnWrite();
                ((PackageData) this.instance).removeNamespaceDigest(i);
                return this;
            }

            public List<NamedValue> getCustomVariableList() {
                return Collections.unmodifiableList(((PackageData) this.instance).getCustomVariableList());
            }

            public int getCustomVariableCount() {
                return ((PackageData) this.instance).getCustomVariableCount();
            }

            public NamedValue getCustomVariable(int i) {
                return ((PackageData) this.instance).getCustomVariable(i);
            }

            public Builder setCustomVariable(int i, NamedValue namedValue) {
                copyOnWrite();
                ((PackageData) this.instance).setCustomVariable(i, namedValue);
                return this;
            }

            public Builder setCustomVariable(int i, NamedValue.Builder builder) {
                copyOnWrite();
                ((PackageData) this.instance).setCustomVariable(i, builder);
                return this;
            }

            public Builder addCustomVariable(NamedValue namedValue) {
                copyOnWrite();
                ((PackageData) this.instance).addCustomVariable(namedValue);
                return this;
            }

            public Builder addCustomVariable(int i, NamedValue namedValue) {
                copyOnWrite();
                ((PackageData) this.instance).addCustomVariable(i, namedValue);
                return this;
            }

            public Builder addCustomVariable(NamedValue.Builder builder) {
                copyOnWrite();
                ((PackageData) this.instance).addCustomVariable(builder);
                return this;
            }

            public Builder addCustomVariable(int i, NamedValue.Builder builder) {
                copyOnWrite();
                ((PackageData) this.instance).addCustomVariable(i, builder);
                return this;
            }

            public Builder addAllCustomVariable(Iterable<? extends NamedValue> iterable) {
                copyOnWrite();
                ((PackageData) this.instance).addAllCustomVariable(iterable);
                return this;
            }

            public Builder clearCustomVariable() {
                copyOnWrite();
                ((PackageData) this.instance).clearCustomVariable();
                return this;
            }

            public Builder removeCustomVariable(int i) {
                copyOnWrite();
                ((PackageData) this.instance).removeCustomVariable(i);
                return this;
            }

            public boolean hasAppCertHash() {
                return ((PackageData) this.instance).hasAppCertHash();
            }

            public ByteString getAppCertHash() {
                return ((PackageData) this.instance).getAppCertHash();
            }

            public Builder setAppCertHash(ByteString byteString) {
                copyOnWrite();
                ((PackageData) this.instance).setAppCertHash(byteString);
                return this;
            }

            public Builder clearAppCertHash() {
                copyOnWrite();
                ((PackageData) this.instance).clearAppCertHash();
                return this;
            }

            public boolean hasAppVersionCode() {
                return ((PackageData) this.instance).hasAppVersionCode();
            }

            public int getAppVersionCode() {
                return ((PackageData) this.instance).getAppVersionCode();
            }

            public Builder setAppVersionCode(int i) {
                copyOnWrite();
                ((PackageData) this.instance).setAppVersionCode(i);
                return this;
            }

            public Builder clearAppVersionCode() {
                copyOnWrite();
                ((PackageData) this.instance).clearAppVersionCode();
                return this;
            }

            public boolean hasAppVersion() {
                return ((PackageData) this.instance).hasAppVersion();
            }

            public String getAppVersion() {
                return ((PackageData) this.instance).getAppVersion();
            }

            public ByteString getAppVersionBytes() {
                return ((PackageData) this.instance).getAppVersionBytes();
            }

            public Builder setAppVersion(String str) {
                copyOnWrite();
                ((PackageData) this.instance).setAppVersion(str);
                return this;
            }

            public Builder clearAppVersion() {
                copyOnWrite();
                ((PackageData) this.instance).clearAppVersion();
                return this;
            }

            public Builder setAppVersionBytes(ByteString byteString) {
                copyOnWrite();
                ((PackageData) this.instance).setAppVersionBytes(byteString);
                return this;
            }

            public boolean hasAppInstanceId() {
                return ((PackageData) this.instance).hasAppInstanceId();
            }

            public String getAppInstanceId() {
                return ((PackageData) this.instance).getAppInstanceId();
            }

            public ByteString getAppInstanceIdBytes() {
                return ((PackageData) this.instance).getAppInstanceIdBytes();
            }

            public Builder setAppInstanceId(String str) {
                copyOnWrite();
                ((PackageData) this.instance).setAppInstanceId(str);
                return this;
            }

            public Builder clearAppInstanceId() {
                copyOnWrite();
                ((PackageData) this.instance).clearAppInstanceId();
                return this;
            }

            public Builder setAppInstanceIdBytes(ByteString byteString) {
                copyOnWrite();
                ((PackageData) this.instance).setAppInstanceIdBytes(byteString);
                return this;
            }

            public boolean hasAppInstanceIdToken() {
                return ((PackageData) this.instance).hasAppInstanceIdToken();
            }

            public String getAppInstanceIdToken() {
                return ((PackageData) this.instance).getAppInstanceIdToken();
            }

            public ByteString getAppInstanceIdTokenBytes() {
                return ((PackageData) this.instance).getAppInstanceIdTokenBytes();
            }

            public Builder setAppInstanceIdToken(String str) {
                copyOnWrite();
                ((PackageData) this.instance).setAppInstanceIdToken(str);
                return this;
            }

            public Builder clearAppInstanceIdToken() {
                copyOnWrite();
                ((PackageData) this.instance).clearAppInstanceIdToken();
                return this;
            }

            public Builder setAppInstanceIdTokenBytes(ByteString byteString) {
                copyOnWrite();
                ((PackageData) this.instance).setAppInstanceIdTokenBytes(byteString);
                return this;
            }

            public List<String> getRequestedHiddenNamespaceList() {
                return Collections.unmodifiableList(((PackageData) this.instance).getRequestedHiddenNamespaceList());
            }

            public int getRequestedHiddenNamespaceCount() {
                return ((PackageData) this.instance).getRequestedHiddenNamespaceCount();
            }

            public String getRequestedHiddenNamespace(int i) {
                return ((PackageData) this.instance).getRequestedHiddenNamespace(i);
            }

            public ByteString getRequestedHiddenNamespaceBytes(int i) {
                return ((PackageData) this.instance).getRequestedHiddenNamespaceBytes(i);
            }

            public Builder setRequestedHiddenNamespace(int i, String str) {
                copyOnWrite();
                ((PackageData) this.instance).setRequestedHiddenNamespace(i, str);
                return this;
            }

            public Builder addRequestedHiddenNamespace(String str) {
                copyOnWrite();
                ((PackageData) this.instance).addRequestedHiddenNamespace(str);
                return this;
            }

            public Builder addAllRequestedHiddenNamespace(Iterable<String> iterable) {
                copyOnWrite();
                ((PackageData) this.instance).addAllRequestedHiddenNamespace(iterable);
                return this;
            }

            public Builder clearRequestedHiddenNamespace() {
                copyOnWrite();
                ((PackageData) this.instance).clearRequestedHiddenNamespace();
                return this;
            }

            public Builder addRequestedHiddenNamespaceBytes(ByteString byteString) {
                copyOnWrite();
                ((PackageData) this.instance).addRequestedHiddenNamespaceBytes(byteString);
                return this;
            }

            public boolean hasSdkVersion() {
                return ((PackageData) this.instance).hasSdkVersion();
            }

            public int getSdkVersion() {
                return ((PackageData) this.instance).getSdkVersion();
            }

            public Builder setSdkVersion(int i) {
                copyOnWrite();
                ((PackageData) this.instance).setSdkVersion(i);
                return this;
            }

            public Builder clearSdkVersion() {
                copyOnWrite();
                ((PackageData) this.instance).clearSdkVersion();
                return this;
            }

            public List<NamedValue> getAnalyticsUserPropertyList() {
                return Collections.unmodifiableList(((PackageData) this.instance).getAnalyticsUserPropertyList());
            }

            public int getAnalyticsUserPropertyCount() {
                return ((PackageData) this.instance).getAnalyticsUserPropertyCount();
            }

            public NamedValue getAnalyticsUserProperty(int i) {
                return ((PackageData) this.instance).getAnalyticsUserProperty(i);
            }

            public Builder setAnalyticsUserProperty(int i, NamedValue namedValue) {
                copyOnWrite();
                ((PackageData) this.instance).setAnalyticsUserProperty(i, namedValue);
                return this;
            }

            public Builder setAnalyticsUserProperty(int i, NamedValue.Builder builder) {
                copyOnWrite();
                ((PackageData) this.instance).setAnalyticsUserProperty(i, builder);
                return this;
            }

            public Builder addAnalyticsUserProperty(NamedValue namedValue) {
                copyOnWrite();
                ((PackageData) this.instance).addAnalyticsUserProperty(namedValue);
                return this;
            }

            public Builder addAnalyticsUserProperty(int i, NamedValue namedValue) {
                copyOnWrite();
                ((PackageData) this.instance).addAnalyticsUserProperty(i, namedValue);
                return this;
            }

            public Builder addAnalyticsUserProperty(NamedValue.Builder builder) {
                copyOnWrite();
                ((PackageData) this.instance).addAnalyticsUserProperty(builder);
                return this;
            }

            public Builder addAnalyticsUserProperty(int i, NamedValue.Builder builder) {
                copyOnWrite();
                ((PackageData) this.instance).addAnalyticsUserProperty(i, builder);
                return this;
            }

            public Builder addAllAnalyticsUserProperty(Iterable<? extends NamedValue> iterable) {
                copyOnWrite();
                ((PackageData) this.instance).addAllAnalyticsUserProperty(iterable);
                return this;
            }

            public Builder clearAnalyticsUserProperty() {
                copyOnWrite();
                ((PackageData) this.instance).clearAnalyticsUserProperty();
                return this;
            }

            public Builder removeAnalyticsUserProperty(int i) {
                copyOnWrite();
                ((PackageData) this.instance).removeAnalyticsUserProperty(i);
                return this;
            }

            public boolean hasRequestedCacheExpirationSeconds() {
                return ((PackageData) this.instance).hasRequestedCacheExpirationSeconds();
            }

            public int getRequestedCacheExpirationSeconds() {
                return ((PackageData) this.instance).getRequestedCacheExpirationSeconds();
            }

            public Builder setRequestedCacheExpirationSeconds(int i) {
                copyOnWrite();
                ((PackageData) this.instance).setRequestedCacheExpirationSeconds(i);
                return this;
            }

            public Builder clearRequestedCacheExpirationSeconds() {
                copyOnWrite();
                ((PackageData) this.instance).clearRequestedCacheExpirationSeconds();
                return this;
            }

            public boolean hasFetchedConfigAgeSeconds() {
                return ((PackageData) this.instance).hasFetchedConfigAgeSeconds();
            }

            public int getFetchedConfigAgeSeconds() {
                return ((PackageData) this.instance).getFetchedConfigAgeSeconds();
            }

            public Builder setFetchedConfigAgeSeconds(int i) {
                copyOnWrite();
                ((PackageData) this.instance).setFetchedConfigAgeSeconds(i);
                return this;
            }

            public Builder clearFetchedConfigAgeSeconds() {
                copyOnWrite();
                ((PackageData) this.instance).clearFetchedConfigAgeSeconds();
                return this;
            }

            public boolean hasActiveConfigAgeSeconds() {
                return ((PackageData) this.instance).hasActiveConfigAgeSeconds();
            }

            public int getActiveConfigAgeSeconds() {
                return ((PackageData) this.instance).getActiveConfigAgeSeconds();
            }

            public Builder setActiveConfigAgeSeconds(int i) {
                copyOnWrite();
                ((PackageData) this.instance).setActiveConfigAgeSeconds(i);
                return this;
            }

            public Builder clearActiveConfigAgeSeconds() {
                copyOnWrite();
                ((PackageData) this.instance).clearActiveConfigAgeSeconds();
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
                case 1:
                    return new PackageData();
                case 2:
                    return DEFAULT_INSTANCE;
                case 3:
                    this.namespaceDigest_.makeImmutable();
                    this.customVariable_.makeImmutable();
                    this.requestedHiddenNamespace_.makeImmutable();
                    this.analyticsUserProperty_.makeImmutable();
                    return null;
                case 4:
                    return new Builder((AnonymousClass1) null);
                case 5:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                    PackageData packageData = (PackageData) obj2;
                    this.versionCode_ = visitor.visitInt(hasVersionCode(), this.versionCode_, packageData.hasVersionCode(), packageData.versionCode_);
                    this.digest_ = visitor.visitByteString(hasDigest(), this.digest_, packageData.hasDigest(), packageData.digest_);
                    this.certHash_ = visitor.visitByteString(hasCertHash(), this.certHash_, packageData.hasCertHash(), packageData.certHash_);
                    this.configId_ = visitor.visitString(hasConfigId(), this.configId_, packageData.hasConfigId(), packageData.configId_);
                    this.packageName_ = visitor.visitString(hasPackageName(), this.packageName_, packageData.hasPackageName(), packageData.packageName_);
                    this.gmpProjectId_ = visitor.visitString(hasGmpProjectId(), this.gmpProjectId_, packageData.hasGmpProjectId(), packageData.gmpProjectId_);
                    this.gamesProjectId_ = visitor.visitString(hasGamesProjectId(), this.gamesProjectId_, packageData.hasGamesProjectId(), packageData.gamesProjectId_);
                    this.namespaceDigest_ = visitor.visitList(this.namespaceDigest_, packageData.namespaceDigest_);
                    this.customVariable_ = visitor.visitList(this.customVariable_, packageData.customVariable_);
                    this.appCertHash_ = visitor.visitByteString(hasAppCertHash(), this.appCertHash_, packageData.hasAppCertHash(), packageData.appCertHash_);
                    this.appVersionCode_ = visitor.visitInt(hasAppVersionCode(), this.appVersionCode_, packageData.hasAppVersionCode(), packageData.appVersionCode_);
                    this.appVersion_ = visitor.visitString(hasAppVersion(), this.appVersion_, packageData.hasAppVersion(), packageData.appVersion_);
                    this.appInstanceId_ = visitor.visitString(hasAppInstanceId(), this.appInstanceId_, packageData.hasAppInstanceId(), packageData.appInstanceId_);
                    this.appInstanceIdToken_ = visitor.visitString(hasAppInstanceIdToken(), this.appInstanceIdToken_, packageData.hasAppInstanceIdToken(), packageData.appInstanceIdToken_);
                    this.requestedHiddenNamespace_ = visitor.visitList(this.requestedHiddenNamespace_, packageData.requestedHiddenNamespace_);
                    this.sdkVersion_ = visitor.visitInt(hasSdkVersion(), this.sdkVersion_, packageData.hasSdkVersion(), packageData.sdkVersion_);
                    this.analyticsUserProperty_ = visitor.visitList(this.analyticsUserProperty_, packageData.analyticsUserProperty_);
                    this.requestedCacheExpirationSeconds_ = visitor.visitInt(hasRequestedCacheExpirationSeconds(), this.requestedCacheExpirationSeconds_, packageData.hasRequestedCacheExpirationSeconds(), packageData.requestedCacheExpirationSeconds_);
                    this.fetchedConfigAgeSeconds_ = visitor.visitInt(hasFetchedConfigAgeSeconds(), this.fetchedConfigAgeSeconds_, packageData.hasFetchedConfigAgeSeconds(), packageData.fetchedConfigAgeSeconds_);
                    this.activeConfigAgeSeconds_ = visitor.visitInt(hasActiveConfigAgeSeconds(), this.activeConfigAgeSeconds_, packageData.hasActiveConfigAgeSeconds(), packageData.activeConfigAgeSeconds_);
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                        this.bitField0_ |= packageData.bitField0_;
                    }
                    return this;
                case 6:
                    CodedInputStream codedInputStream = (CodedInputStream) obj;
                    ExtensionRegistryLite extensionRegistryLite = (ExtensionRegistryLite) obj2;
                    boolean z = false;
                    while (!z) {
                        try {
                            int readTag = codedInputStream.readTag();
                            switch (readTag) {
                                case 0:
                                    z = true;
                                    break;
                                case 10:
                                    String readString = codedInputStream.readString();
                                    this.bitField0_ |= 16;
                                    this.packageName_ = readString;
                                    break;
                                case 16:
                                    this.bitField0_ |= 1;
                                    this.versionCode_ = codedInputStream.readInt32();
                                    break;
                                case 26:
                                    this.bitField0_ |= 2;
                                    this.digest_ = codedInputStream.readBytes();
                                    break;
                                case 34:
                                    this.bitField0_ |= 4;
                                    this.certHash_ = codedInputStream.readBytes();
                                    break;
                                case 42:
                                    String readString2 = codedInputStream.readString();
                                    this.bitField0_ |= 8;
                                    this.configId_ = readString2;
                                    break;
                                case 50:
                                    String readString3 = codedInputStream.readString();
                                    this.bitField0_ |= 32;
                                    this.gmpProjectId_ = readString3;
                                    break;
                                case 58:
                                    String readString4 = codedInputStream.readString();
                                    this.bitField0_ |= 64;
                                    this.gamesProjectId_ = readString4;
                                    break;
                                case 66:
                                    if (!this.namespaceDigest_.isModifiable()) {
                                        this.namespaceDigest_ = GeneratedMessageLite.mutableCopy(this.namespaceDigest_);
                                    }
                                    this.namespaceDigest_.add((NamedValue) codedInputStream.readMessage(NamedValue.parser(), extensionRegistryLite));
                                    break;
                                case 74:
                                    if (!this.customVariable_.isModifiable()) {
                                        this.customVariable_ = GeneratedMessageLite.mutableCopy(this.customVariable_);
                                    }
                                    this.customVariable_.add((NamedValue) codedInputStream.readMessage(NamedValue.parser(), extensionRegistryLite));
                                    break;
                                case 82:
                                    this.bitField0_ |= 128;
                                    this.appCertHash_ = codedInputStream.readBytes();
                                    break;
                                case 88:
                                    this.bitField0_ |= 256;
                                    this.appVersionCode_ = codedInputStream.readInt32();
                                    break;
                                case 98:
                                    String readString5 = codedInputStream.readString();
                                    this.bitField0_ |= 1024;
                                    this.appInstanceId_ = readString5;
                                    break;
                                case 106:
                                    String readString6 = codedInputStream.readString();
                                    this.bitField0_ |= 512;
                                    this.appVersion_ = readString6;
                                    break;
                                case 114:
                                    String readString7 = codedInputStream.readString();
                                    this.bitField0_ |= 2048;
                                    this.appInstanceIdToken_ = readString7;
                                    break;
                                case 122:
                                    String readString8 = codedInputStream.readString();
                                    if (!this.requestedHiddenNamespace_.isModifiable()) {
                                        this.requestedHiddenNamespace_ = GeneratedMessageLite.mutableCopy(this.requestedHiddenNamespace_);
                                    }
                                    this.requestedHiddenNamespace_.add(readString8);
                                    break;
                                case 128:
                                    this.bitField0_ |= 4096;
                                    this.sdkVersion_ = codedInputStream.readInt32();
                                    break;
                                case 138:
                                    if (!this.analyticsUserProperty_.isModifiable()) {
                                        this.analyticsUserProperty_ = GeneratedMessageLite.mutableCopy(this.analyticsUserProperty_);
                                    }
                                    this.analyticsUserProperty_.add((NamedValue) codedInputStream.readMessage(NamedValue.parser(), extensionRegistryLite));
                                    break;
                                case 144:
                                    this.bitField0_ |= 8192;
                                    this.requestedCacheExpirationSeconds_ = codedInputStream.readInt32();
                                    break;
                                case 152:
                                    this.bitField0_ |= 16384;
                                    this.fetchedConfigAgeSeconds_ = codedInputStream.readInt32();
                                    break;
                                case 160:
                                    this.bitField0_ |= 32768;
                                    this.activeConfigAgeSeconds_ = codedInputStream.readInt32();
                                    break;
                                default:
                                    if (parseUnknownField(readTag, codedInputStream)) {
                                        break;
                                    }
                                    z = true;
                                    break;
                            }
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
                        synchronized (PackageData.class) {
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
            PackageData packageData = new PackageData();
            DEFAULT_INSTANCE = packageData;
            packageData.makeImmutable();
        }

        public static PackageData getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<PackageData> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    /* renamed from: com.google.android.gms.config.proto.Config$1  reason: invalid class name */
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
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.config.proto.Config.AnonymousClass1.<clinit>():void");
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
    public static final class NamedValue extends GeneratedMessageLite<NamedValue, Builder> implements NamedValueOrBuilder {
        /* access modifiers changed from: private */
        public static final NamedValue DEFAULT_INSTANCE;
        public static final int NAME_FIELD_NUMBER = 1;
        private static volatile Parser<NamedValue> PARSER = null;
        public static final int VALUE_FIELD_NUMBER = 2;
        private int bitField0_;
        private String name_ = "";
        private String value_ = "";

        private NamedValue() {
        }

        public boolean hasName() {
            return (this.bitField0_ & 1) == 1;
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
                this.bitField0_ |= 1;
                this.name_ = str;
                return;
            }
            throw null;
        }

        /* access modifiers changed from: private */
        public void clearName() {
            this.bitField0_ &= -2;
            this.name_ = getDefaultInstance().getName();
        }

        /* access modifiers changed from: private */
        public void setNameBytes(ByteString byteString) {
            if (byteString != null) {
                this.bitField0_ |= 1;
                this.name_ = byteString.toStringUtf8();
                return;
            }
            throw null;
        }

        public boolean hasValue() {
            return (this.bitField0_ & 2) == 2;
        }

        public String getValue() {
            return this.value_;
        }

        public ByteString getValueBytes() {
            return ByteString.copyFromUtf8(this.value_);
        }

        /* access modifiers changed from: private */
        public void setValue(String str) {
            if (str != null) {
                this.bitField0_ |= 2;
                this.value_ = str;
                return;
            }
            throw null;
        }

        /* access modifiers changed from: private */
        public void clearValue() {
            this.bitField0_ &= -3;
            this.value_ = getDefaultInstance().getValue();
        }

        /* access modifiers changed from: private */
        public void setValueBytes(ByteString byteString) {
            if (byteString != null) {
                this.bitField0_ |= 2;
                this.value_ = byteString.toStringUtf8();
                return;
            }
            throw null;
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                codedOutputStream.writeString(1, getName());
            }
            if ((this.bitField0_ & 2) == 2) {
                codedOutputStream.writeString(2, getValue());
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
                i2 = 0 + CodedOutputStream.computeStringSize(1, getName());
            }
            if ((this.bitField0_ & 2) == 2) {
                i2 += CodedOutputStream.computeStringSize(2, getValue());
            }
            int serializedSize = i2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        public static NamedValue parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (NamedValue) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static NamedValue parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (NamedValue) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static NamedValue parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (NamedValue) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static NamedValue parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (NamedValue) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static NamedValue parseFrom(InputStream inputStream) throws IOException {
            return (NamedValue) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static NamedValue parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (NamedValue) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static NamedValue parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (NamedValue) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static NamedValue parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (NamedValue) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static NamedValue parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (NamedValue) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static NamedValue parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (NamedValue) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(NamedValue namedValue) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(namedValue);
        }

        /* compiled from: com.google.firebase:firebase-config@@19.0.3 */
        public static final class Builder extends GeneratedMessageLite.Builder<NamedValue, Builder> implements NamedValueOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 r1) {
                this();
            }

            private Builder() {
                super(NamedValue.DEFAULT_INSTANCE);
            }

            public boolean hasName() {
                return ((NamedValue) this.instance).hasName();
            }

            public String getName() {
                return ((NamedValue) this.instance).getName();
            }

            public ByteString getNameBytes() {
                return ((NamedValue) this.instance).getNameBytes();
            }

            public Builder setName(String str) {
                copyOnWrite();
                ((NamedValue) this.instance).setName(str);
                return this;
            }

            public Builder clearName() {
                copyOnWrite();
                ((NamedValue) this.instance).clearName();
                return this;
            }

            public Builder setNameBytes(ByteString byteString) {
                copyOnWrite();
                ((NamedValue) this.instance).setNameBytes(byteString);
                return this;
            }

            public boolean hasValue() {
                return ((NamedValue) this.instance).hasValue();
            }

            public String getValue() {
                return ((NamedValue) this.instance).getValue();
            }

            public ByteString getValueBytes() {
                return ((NamedValue) this.instance).getValueBytes();
            }

            public Builder setValue(String str) {
                copyOnWrite();
                ((NamedValue) this.instance).setValue(str);
                return this;
            }

            public Builder clearValue() {
                copyOnWrite();
                ((NamedValue) this.instance).clearValue();
                return this;
            }

            public Builder setValueBytes(ByteString byteString) {
                copyOnWrite();
                ((NamedValue) this.instance).setValueBytes(byteString);
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
                case 1:
                    return new NamedValue();
                case 2:
                    return DEFAULT_INSTANCE;
                case 3:
                    return null;
                case 4:
                    return new Builder((AnonymousClass1) null);
                case 5:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                    NamedValue namedValue = (NamedValue) obj2;
                    this.name_ = visitor.visitString(hasName(), this.name_, namedValue.hasName(), namedValue.name_);
                    this.value_ = visitor.visitString(hasValue(), this.value_, namedValue.hasValue(), namedValue.value_);
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                        this.bitField0_ |= namedValue.bitField0_;
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
                                    this.name_ = readString;
                                } else if (readTag == 18) {
                                    String readString2 = codedInputStream.readString();
                                    this.bitField0_ |= 2;
                                    this.value_ = readString2;
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
                        synchronized (NamedValue.class) {
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
            NamedValue namedValue = new NamedValue();
            DEFAULT_INSTANCE = namedValue;
            namedValue.makeImmutable();
        }

        public static NamedValue getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<NamedValue> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    /* compiled from: com.google.firebase:firebase-config@@19.0.3 */
    public static final class ConfigFetchRequest extends GeneratedMessageLite<ConfigFetchRequest, Builder> implements ConfigFetchRequestOrBuilder {
        public static final int ANDROID_ID_FIELD_NUMBER = 1;
        public static final int API_LEVEL_FIELD_NUMBER = 8;
        public static final int CLIENT_VERSION_FIELD_NUMBER = 6;
        public static final int CONFIG_FIELD_NUMBER = 5;
        /* access modifiers changed from: private */
        public static final ConfigFetchRequest DEFAULT_INSTANCE;
        public static final int DEVICE_COUNTRY_FIELD_NUMBER = 9;
        public static final int DEVICE_DATA_VERSION_INFO_FIELD_NUMBER = 3;
        public static final int DEVICE_LOCALE_FIELD_NUMBER = 10;
        public static final int DEVICE_SUBTYPE_FIELD_NUMBER = 12;
        public static final int DEVICE_TIMEZONE_ID_FIELD_NUMBER = 14;
        public static final int DEVICE_TYPE_FIELD_NUMBER = 11;
        public static final int GMS_CORE_VERSION_FIELD_NUMBER = 7;
        public static final int OS_VERSION_FIELD_NUMBER = 13;
        public static final int PACKAGE_DATA_FIELD_NUMBER = 2;
        private static volatile Parser<ConfigFetchRequest> PARSER = null;
        public static final int SECURITY_TOKEN_FIELD_NUMBER = 4;
        private long androidId_;
        private int apiLevel_;
        private int bitField0_;
        private int clientVersion_;
        private Logs.AndroidConfigFetchProto config_;
        private String deviceCountry_ = "";
        private String deviceDataVersionInfo_ = "";
        private String deviceLocale_ = "";
        private int deviceSubtype_;
        private String deviceTimezoneId_ = "";
        private int deviceType_;
        private int gmsCoreVersion_;
        private String osVersion_ = "";
        private Internal.ProtobufList<PackageData> packageData_ = emptyProtobufList();
        private long securityToken_;

        private ConfigFetchRequest() {
        }

        public boolean hasConfig() {
            return (this.bitField0_ & 1) == 1;
        }

        public Logs.AndroidConfigFetchProto getConfig() {
            Logs.AndroidConfigFetchProto androidConfigFetchProto = this.config_;
            return androidConfigFetchProto == null ? Logs.AndroidConfigFetchProto.getDefaultInstance() : androidConfigFetchProto;
        }

        /* access modifiers changed from: private */
        public void setConfig(Logs.AndroidConfigFetchProto androidConfigFetchProto) {
            if (androidConfigFetchProto != null) {
                this.config_ = androidConfigFetchProto;
                this.bitField0_ |= 1;
                return;
            }
            throw null;
        }

        /* access modifiers changed from: private */
        public void setConfig(Logs.AndroidConfigFetchProto.Builder builder) {
            this.config_ = (Logs.AndroidConfigFetchProto) builder.build();
            this.bitField0_ |= 1;
        }

        /* access modifiers changed from: private */
        public void mergeConfig(Logs.AndroidConfigFetchProto androidConfigFetchProto) {
            Logs.AndroidConfigFetchProto androidConfigFetchProto2 = this.config_;
            if (androidConfigFetchProto2 == null || androidConfigFetchProto2 == Logs.AndroidConfigFetchProto.getDefaultInstance()) {
                this.config_ = androidConfigFetchProto;
            } else {
                this.config_ = (Logs.AndroidConfigFetchProto) ((Logs.AndroidConfigFetchProto.Builder) Logs.AndroidConfigFetchProto.newBuilder(this.config_).mergeFrom(androidConfigFetchProto)).buildPartial();
            }
            this.bitField0_ |= 1;
        }

        /* access modifiers changed from: private */
        public void clearConfig() {
            this.config_ = null;
            this.bitField0_ &= -2;
        }

        public boolean hasAndroidId() {
            return (this.bitField0_ & 2) == 2;
        }

        public long getAndroidId() {
            return this.androidId_;
        }

        /* access modifiers changed from: private */
        public void setAndroidId(long j) {
            this.bitField0_ |= 2;
            this.androidId_ = j;
        }

        /* access modifiers changed from: private */
        public void clearAndroidId() {
            this.bitField0_ &= -3;
            this.androidId_ = 0;
        }

        public List<PackageData> getPackageDataList() {
            return this.packageData_;
        }

        public List<? extends PackageDataOrBuilder> getPackageDataOrBuilderList() {
            return this.packageData_;
        }

        public int getPackageDataCount() {
            return this.packageData_.size();
        }

        public PackageData getPackageData(int i) {
            return (PackageData) this.packageData_.get(i);
        }

        public PackageDataOrBuilder getPackageDataOrBuilder(int i) {
            return (PackageDataOrBuilder) this.packageData_.get(i);
        }

        private void ensurePackageDataIsMutable() {
            if (!this.packageData_.isModifiable()) {
                this.packageData_ = GeneratedMessageLite.mutableCopy(this.packageData_);
            }
        }

        /* access modifiers changed from: private */
        public void setPackageData(int i, PackageData packageData) {
            if (packageData != null) {
                ensurePackageDataIsMutable();
                this.packageData_.set(i, packageData);
                return;
            }
            throw null;
        }

        /* access modifiers changed from: private */
        public void setPackageData(int i, PackageData.Builder builder) {
            ensurePackageDataIsMutable();
            this.packageData_.set(i, (PackageData) builder.build());
        }

        /* access modifiers changed from: private */
        public void addPackageData(PackageData packageData) {
            if (packageData != null) {
                ensurePackageDataIsMutable();
                this.packageData_.add(packageData);
                return;
            }
            throw null;
        }

        /* access modifiers changed from: private */
        public void addPackageData(int i, PackageData packageData) {
            if (packageData != null) {
                ensurePackageDataIsMutable();
                this.packageData_.add(i, packageData);
                return;
            }
            throw null;
        }

        /* access modifiers changed from: private */
        public void addPackageData(PackageData.Builder builder) {
            ensurePackageDataIsMutable();
            this.packageData_.add((PackageData) builder.build());
        }

        /* access modifiers changed from: private */
        public void addPackageData(int i, PackageData.Builder builder) {
            ensurePackageDataIsMutable();
            this.packageData_.add(i, (PackageData) builder.build());
        }

        /* access modifiers changed from: private */
        public void addAllPackageData(Iterable<? extends PackageData> iterable) {
            ensurePackageDataIsMutable();
            AbstractMessageLite.addAll(iterable, this.packageData_);
        }

        /* access modifiers changed from: private */
        public void clearPackageData() {
            this.packageData_ = emptyProtobufList();
        }

        /* access modifiers changed from: private */
        public void removePackageData(int i) {
            ensurePackageDataIsMutable();
            this.packageData_.remove(i);
        }

        public boolean hasDeviceDataVersionInfo() {
            return (this.bitField0_ & 4) == 4;
        }

        public String getDeviceDataVersionInfo() {
            return this.deviceDataVersionInfo_;
        }

        public ByteString getDeviceDataVersionInfoBytes() {
            return ByteString.copyFromUtf8(this.deviceDataVersionInfo_);
        }

        /* access modifiers changed from: private */
        public void setDeviceDataVersionInfo(String str) {
            if (str != null) {
                this.bitField0_ |= 4;
                this.deviceDataVersionInfo_ = str;
                return;
            }
            throw null;
        }

        /* access modifiers changed from: private */
        public void clearDeviceDataVersionInfo() {
            this.bitField0_ &= -5;
            this.deviceDataVersionInfo_ = getDefaultInstance().getDeviceDataVersionInfo();
        }

        /* access modifiers changed from: private */
        public void setDeviceDataVersionInfoBytes(ByteString byteString) {
            if (byteString != null) {
                this.bitField0_ |= 4;
                this.deviceDataVersionInfo_ = byteString.toStringUtf8();
                return;
            }
            throw null;
        }

        public boolean hasSecurityToken() {
            return (this.bitField0_ & 8) == 8;
        }

        public long getSecurityToken() {
            return this.securityToken_;
        }

        /* access modifiers changed from: private */
        public void setSecurityToken(long j) {
            this.bitField0_ |= 8;
            this.securityToken_ = j;
        }

        /* access modifiers changed from: private */
        public void clearSecurityToken() {
            this.bitField0_ &= -9;
            this.securityToken_ = 0;
        }

        public boolean hasClientVersion() {
            return (this.bitField0_ & 16) == 16;
        }

        public int getClientVersion() {
            return this.clientVersion_;
        }

        /* access modifiers changed from: private */
        public void setClientVersion(int i) {
            this.bitField0_ |= 16;
            this.clientVersion_ = i;
        }

        /* access modifiers changed from: private */
        public void clearClientVersion() {
            this.bitField0_ &= -17;
            this.clientVersion_ = 0;
        }

        public boolean hasGmsCoreVersion() {
            return (this.bitField0_ & 32) == 32;
        }

        public int getGmsCoreVersion() {
            return this.gmsCoreVersion_;
        }

        /* access modifiers changed from: private */
        public void setGmsCoreVersion(int i) {
            this.bitField0_ |= 32;
            this.gmsCoreVersion_ = i;
        }

        /* access modifiers changed from: private */
        public void clearGmsCoreVersion() {
            this.bitField0_ &= -33;
            this.gmsCoreVersion_ = 0;
        }

        public boolean hasApiLevel() {
            return (this.bitField0_ & 64) == 64;
        }

        public int getApiLevel() {
            return this.apiLevel_;
        }

        /* access modifiers changed from: private */
        public void setApiLevel(int i) {
            this.bitField0_ |= 64;
            this.apiLevel_ = i;
        }

        /* access modifiers changed from: private */
        public void clearApiLevel() {
            this.bitField0_ &= -65;
            this.apiLevel_ = 0;
        }

        public boolean hasDeviceCountry() {
            return (this.bitField0_ & 128) == 128;
        }

        public String getDeviceCountry() {
            return this.deviceCountry_;
        }

        public ByteString getDeviceCountryBytes() {
            return ByteString.copyFromUtf8(this.deviceCountry_);
        }

        /* access modifiers changed from: private */
        public void setDeviceCountry(String str) {
            if (str != null) {
                this.bitField0_ |= 128;
                this.deviceCountry_ = str;
                return;
            }
            throw null;
        }

        /* access modifiers changed from: private */
        public void clearDeviceCountry() {
            this.bitField0_ &= -129;
            this.deviceCountry_ = getDefaultInstance().getDeviceCountry();
        }

        /* access modifiers changed from: private */
        public void setDeviceCountryBytes(ByteString byteString) {
            if (byteString != null) {
                this.bitField0_ |= 128;
                this.deviceCountry_ = byteString.toStringUtf8();
                return;
            }
            throw null;
        }

        public boolean hasDeviceLocale() {
            return (this.bitField0_ & 256) == 256;
        }

        public String getDeviceLocale() {
            return this.deviceLocale_;
        }

        public ByteString getDeviceLocaleBytes() {
            return ByteString.copyFromUtf8(this.deviceLocale_);
        }

        /* access modifiers changed from: private */
        public void setDeviceLocale(String str) {
            if (str != null) {
                this.bitField0_ |= 256;
                this.deviceLocale_ = str;
                return;
            }
            throw null;
        }

        /* access modifiers changed from: private */
        public void clearDeviceLocale() {
            this.bitField0_ &= -257;
            this.deviceLocale_ = getDefaultInstance().getDeviceLocale();
        }

        /* access modifiers changed from: private */
        public void setDeviceLocaleBytes(ByteString byteString) {
            if (byteString != null) {
                this.bitField0_ |= 256;
                this.deviceLocale_ = byteString.toStringUtf8();
                return;
            }
            throw null;
        }

        public boolean hasDeviceType() {
            return (this.bitField0_ & 512) == 512;
        }

        public int getDeviceType() {
            return this.deviceType_;
        }

        /* access modifiers changed from: private */
        public void setDeviceType(int i) {
            this.bitField0_ |= 512;
            this.deviceType_ = i;
        }

        /* access modifiers changed from: private */
        public void clearDeviceType() {
            this.bitField0_ &= -513;
            this.deviceType_ = 0;
        }

        public boolean hasDeviceSubtype() {
            return (this.bitField0_ & 1024) == 1024;
        }

        public int getDeviceSubtype() {
            return this.deviceSubtype_;
        }

        /* access modifiers changed from: private */
        public void setDeviceSubtype(int i) {
            this.bitField0_ |= 1024;
            this.deviceSubtype_ = i;
        }

        /* access modifiers changed from: private */
        public void clearDeviceSubtype() {
            this.bitField0_ &= -1025;
            this.deviceSubtype_ = 0;
        }

        public boolean hasOsVersion() {
            return (this.bitField0_ & 2048) == 2048;
        }

        public String getOsVersion() {
            return this.osVersion_;
        }

        public ByteString getOsVersionBytes() {
            return ByteString.copyFromUtf8(this.osVersion_);
        }

        /* access modifiers changed from: private */
        public void setOsVersion(String str) {
            if (str != null) {
                this.bitField0_ |= 2048;
                this.osVersion_ = str;
                return;
            }
            throw null;
        }

        /* access modifiers changed from: private */
        public void clearOsVersion() {
            this.bitField0_ &= -2049;
            this.osVersion_ = getDefaultInstance().getOsVersion();
        }

        /* access modifiers changed from: private */
        public void setOsVersionBytes(ByteString byteString) {
            if (byteString != null) {
                this.bitField0_ |= 2048;
                this.osVersion_ = byteString.toStringUtf8();
                return;
            }
            throw null;
        }

        public boolean hasDeviceTimezoneId() {
            return (this.bitField0_ & 4096) == 4096;
        }

        public String getDeviceTimezoneId() {
            return this.deviceTimezoneId_;
        }

        public ByteString getDeviceTimezoneIdBytes() {
            return ByteString.copyFromUtf8(this.deviceTimezoneId_);
        }

        /* access modifiers changed from: private */
        public void setDeviceTimezoneId(String str) {
            if (str != null) {
                this.bitField0_ |= 4096;
                this.deviceTimezoneId_ = str;
                return;
            }
            throw null;
        }

        /* access modifiers changed from: private */
        public void clearDeviceTimezoneId() {
            this.bitField0_ &= -4097;
            this.deviceTimezoneId_ = getDefaultInstance().getDeviceTimezoneId();
        }

        /* access modifiers changed from: private */
        public void setDeviceTimezoneIdBytes(ByteString byteString) {
            if (byteString != null) {
                this.bitField0_ |= 4096;
                this.deviceTimezoneId_ = byteString.toStringUtf8();
                return;
            }
            throw null;
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if ((this.bitField0_ & 2) == 2) {
                codedOutputStream.writeFixed64(1, this.androidId_);
            }
            for (int i = 0; i < this.packageData_.size(); i++) {
                codedOutputStream.writeMessage(2, (MessageLite) this.packageData_.get(i));
            }
            if ((this.bitField0_ & 4) == 4) {
                codedOutputStream.writeString(3, getDeviceDataVersionInfo());
            }
            if ((this.bitField0_ & 8) == 8) {
                codedOutputStream.writeFixed64(4, this.securityToken_);
            }
            if ((this.bitField0_ & 1) == 1) {
                codedOutputStream.writeMessage(5, getConfig());
            }
            if ((this.bitField0_ & 16) == 16) {
                codedOutputStream.writeInt32(6, this.clientVersion_);
            }
            if ((this.bitField0_ & 32) == 32) {
                codedOutputStream.writeInt32(7, this.gmsCoreVersion_);
            }
            if ((this.bitField0_ & 64) == 64) {
                codedOutputStream.writeInt32(8, this.apiLevel_);
            }
            if ((this.bitField0_ & 128) == 128) {
                codedOutputStream.writeString(9, getDeviceCountry());
            }
            if ((this.bitField0_ & 256) == 256) {
                codedOutputStream.writeString(10, getDeviceLocale());
            }
            if ((this.bitField0_ & 512) == 512) {
                codedOutputStream.writeInt32(11, this.deviceType_);
            }
            if ((this.bitField0_ & 1024) == 1024) {
                codedOutputStream.writeInt32(12, this.deviceSubtype_);
            }
            if ((this.bitField0_ & 2048) == 2048) {
                codedOutputStream.writeString(13, getOsVersion());
            }
            if ((this.bitField0_ & 4096) == 4096) {
                codedOutputStream.writeString(14, getDeviceTimezoneId());
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int computeFixed64Size = (this.bitField0_ & 2) == 2 ? CodedOutputStream.computeFixed64Size(1, this.androidId_) + 0 : 0;
            for (int i2 = 0; i2 < this.packageData_.size(); i2++) {
                computeFixed64Size += CodedOutputStream.computeMessageSize(2, (MessageLite) this.packageData_.get(i2));
            }
            if ((this.bitField0_ & 4) == 4) {
                computeFixed64Size += CodedOutputStream.computeStringSize(3, getDeviceDataVersionInfo());
            }
            if ((this.bitField0_ & 8) == 8) {
                computeFixed64Size += CodedOutputStream.computeFixed64Size(4, this.securityToken_);
            }
            if ((this.bitField0_ & 1) == 1) {
                computeFixed64Size += CodedOutputStream.computeMessageSize(5, getConfig());
            }
            if ((this.bitField0_ & 16) == 16) {
                computeFixed64Size += CodedOutputStream.computeInt32Size(6, this.clientVersion_);
            }
            if ((this.bitField0_ & 32) == 32) {
                computeFixed64Size += CodedOutputStream.computeInt32Size(7, this.gmsCoreVersion_);
            }
            if ((this.bitField0_ & 64) == 64) {
                computeFixed64Size += CodedOutputStream.computeInt32Size(8, this.apiLevel_);
            }
            if ((this.bitField0_ & 128) == 128) {
                computeFixed64Size += CodedOutputStream.computeStringSize(9, getDeviceCountry());
            }
            if ((this.bitField0_ & 256) == 256) {
                computeFixed64Size += CodedOutputStream.computeStringSize(10, getDeviceLocale());
            }
            if ((this.bitField0_ & 512) == 512) {
                computeFixed64Size += CodedOutputStream.computeInt32Size(11, this.deviceType_);
            }
            if ((this.bitField0_ & 1024) == 1024) {
                computeFixed64Size += CodedOutputStream.computeInt32Size(12, this.deviceSubtype_);
            }
            if ((this.bitField0_ & 2048) == 2048) {
                computeFixed64Size += CodedOutputStream.computeStringSize(13, getOsVersion());
            }
            if ((this.bitField0_ & 4096) == 4096) {
                computeFixed64Size += CodedOutputStream.computeStringSize(14, getDeviceTimezoneId());
            }
            int serializedSize = computeFixed64Size + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        public static ConfigFetchRequest parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (ConfigFetchRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static ConfigFetchRequest parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (ConfigFetchRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static ConfigFetchRequest parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (ConfigFetchRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static ConfigFetchRequest parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (ConfigFetchRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static ConfigFetchRequest parseFrom(InputStream inputStream) throws IOException {
            return (ConfigFetchRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static ConfigFetchRequest parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ConfigFetchRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static ConfigFetchRequest parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (ConfigFetchRequest) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static ConfigFetchRequest parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ConfigFetchRequest) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static ConfigFetchRequest parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (ConfigFetchRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static ConfigFetchRequest parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ConfigFetchRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(ConfigFetchRequest configFetchRequest) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(configFetchRequest);
        }

        /* compiled from: com.google.firebase:firebase-config@@19.0.3 */
        public static final class Builder extends GeneratedMessageLite.Builder<ConfigFetchRequest, Builder> implements ConfigFetchRequestOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 r1) {
                this();
            }

            private Builder() {
                super(ConfigFetchRequest.DEFAULT_INSTANCE);
            }

            public boolean hasConfig() {
                return ((ConfigFetchRequest) this.instance).hasConfig();
            }

            public Logs.AndroidConfigFetchProto getConfig() {
                return ((ConfigFetchRequest) this.instance).getConfig();
            }

            public Builder setConfig(Logs.AndroidConfigFetchProto androidConfigFetchProto) {
                copyOnWrite();
                ((ConfigFetchRequest) this.instance).setConfig(androidConfigFetchProto);
                return this;
            }

            public Builder setConfig(Logs.AndroidConfigFetchProto.Builder builder) {
                copyOnWrite();
                ((ConfigFetchRequest) this.instance).setConfig(builder);
                return this;
            }

            public Builder mergeConfig(Logs.AndroidConfigFetchProto androidConfigFetchProto) {
                copyOnWrite();
                ((ConfigFetchRequest) this.instance).mergeConfig(androidConfigFetchProto);
                return this;
            }

            public Builder clearConfig() {
                copyOnWrite();
                ((ConfigFetchRequest) this.instance).clearConfig();
                return this;
            }

            public boolean hasAndroidId() {
                return ((ConfigFetchRequest) this.instance).hasAndroidId();
            }

            public long getAndroidId() {
                return ((ConfigFetchRequest) this.instance).getAndroidId();
            }

            public Builder setAndroidId(long j) {
                copyOnWrite();
                ((ConfigFetchRequest) this.instance).setAndroidId(j);
                return this;
            }

            public Builder clearAndroidId() {
                copyOnWrite();
                ((ConfigFetchRequest) this.instance).clearAndroidId();
                return this;
            }

            public List<PackageData> getPackageDataList() {
                return Collections.unmodifiableList(((ConfigFetchRequest) this.instance).getPackageDataList());
            }

            public int getPackageDataCount() {
                return ((ConfigFetchRequest) this.instance).getPackageDataCount();
            }

            public PackageData getPackageData(int i) {
                return ((ConfigFetchRequest) this.instance).getPackageData(i);
            }

            public Builder setPackageData(int i, PackageData packageData) {
                copyOnWrite();
                ((ConfigFetchRequest) this.instance).setPackageData(i, packageData);
                return this;
            }

            public Builder setPackageData(int i, PackageData.Builder builder) {
                copyOnWrite();
                ((ConfigFetchRequest) this.instance).setPackageData(i, builder);
                return this;
            }

            public Builder addPackageData(PackageData packageData) {
                copyOnWrite();
                ((ConfigFetchRequest) this.instance).addPackageData(packageData);
                return this;
            }

            public Builder addPackageData(int i, PackageData packageData) {
                copyOnWrite();
                ((ConfigFetchRequest) this.instance).addPackageData(i, packageData);
                return this;
            }

            public Builder addPackageData(PackageData.Builder builder) {
                copyOnWrite();
                ((ConfigFetchRequest) this.instance).addPackageData(builder);
                return this;
            }

            public Builder addPackageData(int i, PackageData.Builder builder) {
                copyOnWrite();
                ((ConfigFetchRequest) this.instance).addPackageData(i, builder);
                return this;
            }

            public Builder addAllPackageData(Iterable<? extends PackageData> iterable) {
                copyOnWrite();
                ((ConfigFetchRequest) this.instance).addAllPackageData(iterable);
                return this;
            }

            public Builder clearPackageData() {
                copyOnWrite();
                ((ConfigFetchRequest) this.instance).clearPackageData();
                return this;
            }

            public Builder removePackageData(int i) {
                copyOnWrite();
                ((ConfigFetchRequest) this.instance).removePackageData(i);
                return this;
            }

            public boolean hasDeviceDataVersionInfo() {
                return ((ConfigFetchRequest) this.instance).hasDeviceDataVersionInfo();
            }

            public String getDeviceDataVersionInfo() {
                return ((ConfigFetchRequest) this.instance).getDeviceDataVersionInfo();
            }

            public ByteString getDeviceDataVersionInfoBytes() {
                return ((ConfigFetchRequest) this.instance).getDeviceDataVersionInfoBytes();
            }

            public Builder setDeviceDataVersionInfo(String str) {
                copyOnWrite();
                ((ConfigFetchRequest) this.instance).setDeviceDataVersionInfo(str);
                return this;
            }

            public Builder clearDeviceDataVersionInfo() {
                copyOnWrite();
                ((ConfigFetchRequest) this.instance).clearDeviceDataVersionInfo();
                return this;
            }

            public Builder setDeviceDataVersionInfoBytes(ByteString byteString) {
                copyOnWrite();
                ((ConfigFetchRequest) this.instance).setDeviceDataVersionInfoBytes(byteString);
                return this;
            }

            public boolean hasSecurityToken() {
                return ((ConfigFetchRequest) this.instance).hasSecurityToken();
            }

            public long getSecurityToken() {
                return ((ConfigFetchRequest) this.instance).getSecurityToken();
            }

            public Builder setSecurityToken(long j) {
                copyOnWrite();
                ((ConfigFetchRequest) this.instance).setSecurityToken(j);
                return this;
            }

            public Builder clearSecurityToken() {
                copyOnWrite();
                ((ConfigFetchRequest) this.instance).clearSecurityToken();
                return this;
            }

            public boolean hasClientVersion() {
                return ((ConfigFetchRequest) this.instance).hasClientVersion();
            }

            public int getClientVersion() {
                return ((ConfigFetchRequest) this.instance).getClientVersion();
            }

            public Builder setClientVersion(int i) {
                copyOnWrite();
                ((ConfigFetchRequest) this.instance).setClientVersion(i);
                return this;
            }

            public Builder clearClientVersion() {
                copyOnWrite();
                ((ConfigFetchRequest) this.instance).clearClientVersion();
                return this;
            }

            public boolean hasGmsCoreVersion() {
                return ((ConfigFetchRequest) this.instance).hasGmsCoreVersion();
            }

            public int getGmsCoreVersion() {
                return ((ConfigFetchRequest) this.instance).getGmsCoreVersion();
            }

            public Builder setGmsCoreVersion(int i) {
                copyOnWrite();
                ((ConfigFetchRequest) this.instance).setGmsCoreVersion(i);
                return this;
            }

            public Builder clearGmsCoreVersion() {
                copyOnWrite();
                ((ConfigFetchRequest) this.instance).clearGmsCoreVersion();
                return this;
            }

            public boolean hasApiLevel() {
                return ((ConfigFetchRequest) this.instance).hasApiLevel();
            }

            public int getApiLevel() {
                return ((ConfigFetchRequest) this.instance).getApiLevel();
            }

            public Builder setApiLevel(int i) {
                copyOnWrite();
                ((ConfigFetchRequest) this.instance).setApiLevel(i);
                return this;
            }

            public Builder clearApiLevel() {
                copyOnWrite();
                ((ConfigFetchRequest) this.instance).clearApiLevel();
                return this;
            }

            public boolean hasDeviceCountry() {
                return ((ConfigFetchRequest) this.instance).hasDeviceCountry();
            }

            public String getDeviceCountry() {
                return ((ConfigFetchRequest) this.instance).getDeviceCountry();
            }

            public ByteString getDeviceCountryBytes() {
                return ((ConfigFetchRequest) this.instance).getDeviceCountryBytes();
            }

            public Builder setDeviceCountry(String str) {
                copyOnWrite();
                ((ConfigFetchRequest) this.instance).setDeviceCountry(str);
                return this;
            }

            public Builder clearDeviceCountry() {
                copyOnWrite();
                ((ConfigFetchRequest) this.instance).clearDeviceCountry();
                return this;
            }

            public Builder setDeviceCountryBytes(ByteString byteString) {
                copyOnWrite();
                ((ConfigFetchRequest) this.instance).setDeviceCountryBytes(byteString);
                return this;
            }

            public boolean hasDeviceLocale() {
                return ((ConfigFetchRequest) this.instance).hasDeviceLocale();
            }

            public String getDeviceLocale() {
                return ((ConfigFetchRequest) this.instance).getDeviceLocale();
            }

            public ByteString getDeviceLocaleBytes() {
                return ((ConfigFetchRequest) this.instance).getDeviceLocaleBytes();
            }

            public Builder setDeviceLocale(String str) {
                copyOnWrite();
                ((ConfigFetchRequest) this.instance).setDeviceLocale(str);
                return this;
            }

            public Builder clearDeviceLocale() {
                copyOnWrite();
                ((ConfigFetchRequest) this.instance).clearDeviceLocale();
                return this;
            }

            public Builder setDeviceLocaleBytes(ByteString byteString) {
                copyOnWrite();
                ((ConfigFetchRequest) this.instance).setDeviceLocaleBytes(byteString);
                return this;
            }

            public boolean hasDeviceType() {
                return ((ConfigFetchRequest) this.instance).hasDeviceType();
            }

            public int getDeviceType() {
                return ((ConfigFetchRequest) this.instance).getDeviceType();
            }

            public Builder setDeviceType(int i) {
                copyOnWrite();
                ((ConfigFetchRequest) this.instance).setDeviceType(i);
                return this;
            }

            public Builder clearDeviceType() {
                copyOnWrite();
                ((ConfigFetchRequest) this.instance).clearDeviceType();
                return this;
            }

            public boolean hasDeviceSubtype() {
                return ((ConfigFetchRequest) this.instance).hasDeviceSubtype();
            }

            public int getDeviceSubtype() {
                return ((ConfigFetchRequest) this.instance).getDeviceSubtype();
            }

            public Builder setDeviceSubtype(int i) {
                copyOnWrite();
                ((ConfigFetchRequest) this.instance).setDeviceSubtype(i);
                return this;
            }

            public Builder clearDeviceSubtype() {
                copyOnWrite();
                ((ConfigFetchRequest) this.instance).clearDeviceSubtype();
                return this;
            }

            public boolean hasOsVersion() {
                return ((ConfigFetchRequest) this.instance).hasOsVersion();
            }

            public String getOsVersion() {
                return ((ConfigFetchRequest) this.instance).getOsVersion();
            }

            public ByteString getOsVersionBytes() {
                return ((ConfigFetchRequest) this.instance).getOsVersionBytes();
            }

            public Builder setOsVersion(String str) {
                copyOnWrite();
                ((ConfigFetchRequest) this.instance).setOsVersion(str);
                return this;
            }

            public Builder clearOsVersion() {
                copyOnWrite();
                ((ConfigFetchRequest) this.instance).clearOsVersion();
                return this;
            }

            public Builder setOsVersionBytes(ByteString byteString) {
                copyOnWrite();
                ((ConfigFetchRequest) this.instance).setOsVersionBytes(byteString);
                return this;
            }

            public boolean hasDeviceTimezoneId() {
                return ((ConfigFetchRequest) this.instance).hasDeviceTimezoneId();
            }

            public String getDeviceTimezoneId() {
                return ((ConfigFetchRequest) this.instance).getDeviceTimezoneId();
            }

            public ByteString getDeviceTimezoneIdBytes() {
                return ((ConfigFetchRequest) this.instance).getDeviceTimezoneIdBytes();
            }

            public Builder setDeviceTimezoneId(String str) {
                copyOnWrite();
                ((ConfigFetchRequest) this.instance).setDeviceTimezoneId(str);
                return this;
            }

            public Builder clearDeviceTimezoneId() {
                copyOnWrite();
                ((ConfigFetchRequest) this.instance).clearDeviceTimezoneId();
                return this;
            }

            public Builder setDeviceTimezoneIdBytes(ByteString byteString) {
                copyOnWrite();
                ((ConfigFetchRequest) this.instance).setDeviceTimezoneIdBytes(byteString);
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
                case 1:
                    return new ConfigFetchRequest();
                case 2:
                    return DEFAULT_INSTANCE;
                case 3:
                    this.packageData_.makeImmutable();
                    return null;
                case 4:
                    return new Builder((AnonymousClass1) null);
                case 5:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                    ConfigFetchRequest configFetchRequest = (ConfigFetchRequest) obj2;
                    this.config_ = (Logs.AndroidConfigFetchProto) visitor.visitMessage(this.config_, configFetchRequest.config_);
                    this.androidId_ = visitor.visitLong(hasAndroidId(), this.androidId_, configFetchRequest.hasAndroidId(), configFetchRequest.androidId_);
                    this.packageData_ = visitor.visitList(this.packageData_, configFetchRequest.packageData_);
                    this.deviceDataVersionInfo_ = visitor.visitString(hasDeviceDataVersionInfo(), this.deviceDataVersionInfo_, configFetchRequest.hasDeviceDataVersionInfo(), configFetchRequest.deviceDataVersionInfo_);
                    this.securityToken_ = visitor.visitLong(hasSecurityToken(), this.securityToken_, configFetchRequest.hasSecurityToken(), configFetchRequest.securityToken_);
                    this.clientVersion_ = visitor.visitInt(hasClientVersion(), this.clientVersion_, configFetchRequest.hasClientVersion(), configFetchRequest.clientVersion_);
                    this.gmsCoreVersion_ = visitor.visitInt(hasGmsCoreVersion(), this.gmsCoreVersion_, configFetchRequest.hasGmsCoreVersion(), configFetchRequest.gmsCoreVersion_);
                    this.apiLevel_ = visitor.visitInt(hasApiLevel(), this.apiLevel_, configFetchRequest.hasApiLevel(), configFetchRequest.apiLevel_);
                    this.deviceCountry_ = visitor.visitString(hasDeviceCountry(), this.deviceCountry_, configFetchRequest.hasDeviceCountry(), configFetchRequest.deviceCountry_);
                    this.deviceLocale_ = visitor.visitString(hasDeviceLocale(), this.deviceLocale_, configFetchRequest.hasDeviceLocale(), configFetchRequest.deviceLocale_);
                    this.deviceType_ = visitor.visitInt(hasDeviceType(), this.deviceType_, configFetchRequest.hasDeviceType(), configFetchRequest.deviceType_);
                    this.deviceSubtype_ = visitor.visitInt(hasDeviceSubtype(), this.deviceSubtype_, configFetchRequest.hasDeviceSubtype(), configFetchRequest.deviceSubtype_);
                    this.osVersion_ = visitor.visitString(hasOsVersion(), this.osVersion_, configFetchRequest.hasOsVersion(), configFetchRequest.osVersion_);
                    this.deviceTimezoneId_ = visitor.visitString(hasDeviceTimezoneId(), this.deviceTimezoneId_, configFetchRequest.hasDeviceTimezoneId(), configFetchRequest.deviceTimezoneId_);
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                        this.bitField0_ |= configFetchRequest.bitField0_;
                    }
                    return this;
                case 6:
                    CodedInputStream codedInputStream = (CodedInputStream) obj;
                    ExtensionRegistryLite extensionRegistryLite = (ExtensionRegistryLite) obj2;
                    boolean z = false;
                    while (!z) {
                        try {
                            int readTag = codedInputStream.readTag();
                            switch (readTag) {
                                case 0:
                                    z = true;
                                    break;
                                case 9:
                                    this.bitField0_ |= 2;
                                    this.androidId_ = codedInputStream.readFixed64();
                                    break;
                                case 18:
                                    if (!this.packageData_.isModifiable()) {
                                        this.packageData_ = GeneratedMessageLite.mutableCopy(this.packageData_);
                                    }
                                    this.packageData_.add((PackageData) codedInputStream.readMessage(PackageData.parser(), extensionRegistryLite));
                                    break;
                                case 26:
                                    String readString = codedInputStream.readString();
                                    this.bitField0_ |= 4;
                                    this.deviceDataVersionInfo_ = readString;
                                    break;
                                case 33:
                                    this.bitField0_ |= 8;
                                    this.securityToken_ = codedInputStream.readFixed64();
                                    break;
                                case 42:
                                    Logs.AndroidConfigFetchProto.Builder builder = (this.bitField0_ & 1) == 1 ? (Logs.AndroidConfigFetchProto.Builder) this.config_.toBuilder() : null;
                                    Logs.AndroidConfigFetchProto androidConfigFetchProto = (Logs.AndroidConfigFetchProto) codedInputStream.readMessage(Logs.AndroidConfigFetchProto.parser(), extensionRegistryLite);
                                    this.config_ = androidConfigFetchProto;
                                    if (builder != null) {
                                        builder.mergeFrom(androidConfigFetchProto);
                                        this.config_ = (Logs.AndroidConfigFetchProto) builder.buildPartial();
                                    }
                                    this.bitField0_ |= 1;
                                    break;
                                case 48:
                                    this.bitField0_ |= 16;
                                    this.clientVersion_ = codedInputStream.readInt32();
                                    break;
                                case 56:
                                    this.bitField0_ |= 32;
                                    this.gmsCoreVersion_ = codedInputStream.readInt32();
                                    break;
                                case 64:
                                    this.bitField0_ |= 64;
                                    this.apiLevel_ = codedInputStream.readInt32();
                                    break;
                                case 74:
                                    String readString2 = codedInputStream.readString();
                                    this.bitField0_ |= 128;
                                    this.deviceCountry_ = readString2;
                                    break;
                                case 82:
                                    String readString3 = codedInputStream.readString();
                                    this.bitField0_ |= 256;
                                    this.deviceLocale_ = readString3;
                                    break;
                                case 88:
                                    this.bitField0_ |= 512;
                                    this.deviceType_ = codedInputStream.readInt32();
                                    break;
                                case 96:
                                    this.bitField0_ |= 1024;
                                    this.deviceSubtype_ = codedInputStream.readInt32();
                                    break;
                                case 106:
                                    String readString4 = codedInputStream.readString();
                                    this.bitField0_ |= 2048;
                                    this.osVersion_ = readString4;
                                    break;
                                case 114:
                                    String readString5 = codedInputStream.readString();
                                    this.bitField0_ |= 4096;
                                    this.deviceTimezoneId_ = readString5;
                                    break;
                                default:
                                    if (parseUnknownField(readTag, codedInputStream)) {
                                        break;
                                    }
                                    z = true;
                                    break;
                            }
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
                        synchronized (ConfigFetchRequest.class) {
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
            ConfigFetchRequest configFetchRequest = new ConfigFetchRequest();
            DEFAULT_INSTANCE = configFetchRequest;
            configFetchRequest.makeImmutable();
        }

        public static ConfigFetchRequest getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<ConfigFetchRequest> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    /* compiled from: com.google.firebase:firebase-config@@19.0.3 */
    public static final class PackageTable extends GeneratedMessageLite<PackageTable, Builder> implements PackageTableOrBuilder {
        public static final int CONFIG_ID_FIELD_NUMBER = 3;
        /* access modifiers changed from: private */
        public static final PackageTable DEFAULT_INSTANCE;
        public static final int ENTRY_FIELD_NUMBER = 2;
        public static final int PACKAGE_NAME_FIELD_NUMBER = 1;
        private static volatile Parser<PackageTable> PARSER;
        private int bitField0_;
        private String configId_ = "";
        private Internal.ProtobufList<KeyValue> entry_ = emptyProtobufList();
        private String packageName_ = "";

        private PackageTable() {
        }

        public boolean hasPackageName() {
            return (this.bitField0_ & 1) == 1;
        }

        public String getPackageName() {
            return this.packageName_;
        }

        public ByteString getPackageNameBytes() {
            return ByteString.copyFromUtf8(this.packageName_);
        }

        /* access modifiers changed from: private */
        public void setPackageName(String str) {
            if (str != null) {
                this.bitField0_ |= 1;
                this.packageName_ = str;
                return;
            }
            throw null;
        }

        /* access modifiers changed from: private */
        public void clearPackageName() {
            this.bitField0_ &= -2;
            this.packageName_ = getDefaultInstance().getPackageName();
        }

        /* access modifiers changed from: private */
        public void setPackageNameBytes(ByteString byteString) {
            if (byteString != null) {
                this.bitField0_ |= 1;
                this.packageName_ = byteString.toStringUtf8();
                return;
            }
            throw null;
        }

        public List<KeyValue> getEntryList() {
            return this.entry_;
        }

        public List<? extends KeyValueOrBuilder> getEntryOrBuilderList() {
            return this.entry_;
        }

        public int getEntryCount() {
            return this.entry_.size();
        }

        public KeyValue getEntry(int i) {
            return (KeyValue) this.entry_.get(i);
        }

        public KeyValueOrBuilder getEntryOrBuilder(int i) {
            return (KeyValueOrBuilder) this.entry_.get(i);
        }

        private void ensureEntryIsMutable() {
            if (!this.entry_.isModifiable()) {
                this.entry_ = GeneratedMessageLite.mutableCopy(this.entry_);
            }
        }

        /* access modifiers changed from: private */
        public void setEntry(int i, KeyValue keyValue) {
            if (keyValue != null) {
                ensureEntryIsMutable();
                this.entry_.set(i, keyValue);
                return;
            }
            throw null;
        }

        /* access modifiers changed from: private */
        public void setEntry(int i, KeyValue.Builder builder) {
            ensureEntryIsMutable();
            this.entry_.set(i, (KeyValue) builder.build());
        }

        /* access modifiers changed from: private */
        public void addEntry(KeyValue keyValue) {
            if (keyValue != null) {
                ensureEntryIsMutable();
                this.entry_.add(keyValue);
                return;
            }
            throw null;
        }

        /* access modifiers changed from: private */
        public void addEntry(int i, KeyValue keyValue) {
            if (keyValue != null) {
                ensureEntryIsMutable();
                this.entry_.add(i, keyValue);
                return;
            }
            throw null;
        }

        /* access modifiers changed from: private */
        public void addEntry(KeyValue.Builder builder) {
            ensureEntryIsMutable();
            this.entry_.add((KeyValue) builder.build());
        }

        /* access modifiers changed from: private */
        public void addEntry(int i, KeyValue.Builder builder) {
            ensureEntryIsMutable();
            this.entry_.add(i, (KeyValue) builder.build());
        }

        /* access modifiers changed from: private */
        public void addAllEntry(Iterable<? extends KeyValue> iterable) {
            ensureEntryIsMutable();
            AbstractMessageLite.addAll(iterable, this.entry_);
        }

        /* access modifiers changed from: private */
        public void clearEntry() {
            this.entry_ = emptyProtobufList();
        }

        /* access modifiers changed from: private */
        public void removeEntry(int i) {
            ensureEntryIsMutable();
            this.entry_.remove(i);
        }

        public boolean hasConfigId() {
            return (this.bitField0_ & 2) == 2;
        }

        public String getConfigId() {
            return this.configId_;
        }

        public ByteString getConfigIdBytes() {
            return ByteString.copyFromUtf8(this.configId_);
        }

        /* access modifiers changed from: private */
        public void setConfigId(String str) {
            if (str != null) {
                this.bitField0_ |= 2;
                this.configId_ = str;
                return;
            }
            throw null;
        }

        /* access modifiers changed from: private */
        public void clearConfigId() {
            this.bitField0_ &= -3;
            this.configId_ = getDefaultInstance().getConfigId();
        }

        /* access modifiers changed from: private */
        public void setConfigIdBytes(ByteString byteString) {
            if (byteString != null) {
                this.bitField0_ |= 2;
                this.configId_ = byteString.toStringUtf8();
                return;
            }
            throw null;
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                codedOutputStream.writeString(1, getPackageName());
            }
            for (int i = 0; i < this.entry_.size(); i++) {
                codedOutputStream.writeMessage(2, (MessageLite) this.entry_.get(i));
            }
            if ((this.bitField0_ & 2) == 2) {
                codedOutputStream.writeString(3, getConfigId());
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int computeStringSize = (this.bitField0_ & 1) == 1 ? CodedOutputStream.computeStringSize(1, getPackageName()) + 0 : 0;
            for (int i2 = 0; i2 < this.entry_.size(); i2++) {
                computeStringSize += CodedOutputStream.computeMessageSize(2, (MessageLite) this.entry_.get(i2));
            }
            if ((this.bitField0_ & 2) == 2) {
                computeStringSize += CodedOutputStream.computeStringSize(3, getConfigId());
            }
            int serializedSize = computeStringSize + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        public static PackageTable parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (PackageTable) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static PackageTable parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (PackageTable) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static PackageTable parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (PackageTable) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static PackageTable parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (PackageTable) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static PackageTable parseFrom(InputStream inputStream) throws IOException {
            return (PackageTable) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static PackageTable parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (PackageTable) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static PackageTable parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (PackageTable) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static PackageTable parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (PackageTable) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static PackageTable parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (PackageTable) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static PackageTable parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (PackageTable) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(PackageTable packageTable) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(packageTable);
        }

        /* compiled from: com.google.firebase:firebase-config@@19.0.3 */
        public static final class Builder extends GeneratedMessageLite.Builder<PackageTable, Builder> implements PackageTableOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 r1) {
                this();
            }

            private Builder() {
                super(PackageTable.DEFAULT_INSTANCE);
            }

            public boolean hasPackageName() {
                return ((PackageTable) this.instance).hasPackageName();
            }

            public String getPackageName() {
                return ((PackageTable) this.instance).getPackageName();
            }

            public ByteString getPackageNameBytes() {
                return ((PackageTable) this.instance).getPackageNameBytes();
            }

            public Builder setPackageName(String str) {
                copyOnWrite();
                ((PackageTable) this.instance).setPackageName(str);
                return this;
            }

            public Builder clearPackageName() {
                copyOnWrite();
                ((PackageTable) this.instance).clearPackageName();
                return this;
            }

            public Builder setPackageNameBytes(ByteString byteString) {
                copyOnWrite();
                ((PackageTable) this.instance).setPackageNameBytes(byteString);
                return this;
            }

            public List<KeyValue> getEntryList() {
                return Collections.unmodifiableList(((PackageTable) this.instance).getEntryList());
            }

            public int getEntryCount() {
                return ((PackageTable) this.instance).getEntryCount();
            }

            public KeyValue getEntry(int i) {
                return ((PackageTable) this.instance).getEntry(i);
            }

            public Builder setEntry(int i, KeyValue keyValue) {
                copyOnWrite();
                ((PackageTable) this.instance).setEntry(i, keyValue);
                return this;
            }

            public Builder setEntry(int i, KeyValue.Builder builder) {
                copyOnWrite();
                ((PackageTable) this.instance).setEntry(i, builder);
                return this;
            }

            public Builder addEntry(KeyValue keyValue) {
                copyOnWrite();
                ((PackageTable) this.instance).addEntry(keyValue);
                return this;
            }

            public Builder addEntry(int i, KeyValue keyValue) {
                copyOnWrite();
                ((PackageTable) this.instance).addEntry(i, keyValue);
                return this;
            }

            public Builder addEntry(KeyValue.Builder builder) {
                copyOnWrite();
                ((PackageTable) this.instance).addEntry(builder);
                return this;
            }

            public Builder addEntry(int i, KeyValue.Builder builder) {
                copyOnWrite();
                ((PackageTable) this.instance).addEntry(i, builder);
                return this;
            }

            public Builder addAllEntry(Iterable<? extends KeyValue> iterable) {
                copyOnWrite();
                ((PackageTable) this.instance).addAllEntry(iterable);
                return this;
            }

            public Builder clearEntry() {
                copyOnWrite();
                ((PackageTable) this.instance).clearEntry();
                return this;
            }

            public Builder removeEntry(int i) {
                copyOnWrite();
                ((PackageTable) this.instance).removeEntry(i);
                return this;
            }

            public boolean hasConfigId() {
                return ((PackageTable) this.instance).hasConfigId();
            }

            public String getConfigId() {
                return ((PackageTable) this.instance).getConfigId();
            }

            public ByteString getConfigIdBytes() {
                return ((PackageTable) this.instance).getConfigIdBytes();
            }

            public Builder setConfigId(String str) {
                copyOnWrite();
                ((PackageTable) this.instance).setConfigId(str);
                return this;
            }

            public Builder clearConfigId() {
                copyOnWrite();
                ((PackageTable) this.instance).clearConfigId();
                return this;
            }

            public Builder setConfigIdBytes(ByteString byteString) {
                copyOnWrite();
                ((PackageTable) this.instance).setConfigIdBytes(byteString);
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
                case 1:
                    return new PackageTable();
                case 2:
                    return DEFAULT_INSTANCE;
                case 3:
                    this.entry_.makeImmutable();
                    return null;
                case 4:
                    return new Builder((AnonymousClass1) null);
                case 5:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                    PackageTable packageTable = (PackageTable) obj2;
                    this.packageName_ = visitor.visitString(hasPackageName(), this.packageName_, packageTable.hasPackageName(), packageTable.packageName_);
                    this.entry_ = visitor.visitList(this.entry_, packageTable.entry_);
                    this.configId_ = visitor.visitString(hasConfigId(), this.configId_, packageTable.hasConfigId(), packageTable.configId_);
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                        this.bitField0_ |= packageTable.bitField0_;
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
                                    this.packageName_ = readString;
                                } else if (readTag == 18) {
                                    if (!this.entry_.isModifiable()) {
                                        this.entry_ = GeneratedMessageLite.mutableCopy(this.entry_);
                                    }
                                    this.entry_.add((KeyValue) codedInputStream.readMessage(KeyValue.parser(), extensionRegistryLite));
                                } else if (readTag == 26) {
                                    String readString2 = codedInputStream.readString();
                                    this.bitField0_ |= 2;
                                    this.configId_ = readString2;
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
                        synchronized (PackageTable.class) {
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
            PackageTable packageTable = new PackageTable();
            DEFAULT_INSTANCE = packageTable;
            packageTable.makeImmutable();
        }

        public static PackageTable getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<PackageTable> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    /* compiled from: com.google.firebase:firebase-config@@19.0.3 */
    public static final class AppNamespaceConfigTable extends GeneratedMessageLite<AppNamespaceConfigTable, Builder> implements AppNamespaceConfigTableOrBuilder {
        /* access modifiers changed from: private */
        public static final AppNamespaceConfigTable DEFAULT_INSTANCE;
        public static final int DIGEST_FIELD_NUMBER = 2;
        public static final int ENTRY_FIELD_NUMBER = 3;
        public static final int NAMESPACE_FIELD_NUMBER = 1;
        private static volatile Parser<AppNamespaceConfigTable> PARSER = null;
        public static final int STATUS_FIELD_NUMBER = 4;
        private int bitField0_;
        private String digest_ = "";
        private Internal.ProtobufList<KeyValue> entry_ = emptyProtobufList();
        private String namespace_ = "";
        private int status_;

        private AppNamespaceConfigTable() {
        }

        /* compiled from: com.google.firebase:firebase-config@@19.0.3 */
        public enum NamespaceStatus implements Internal.EnumLite {
            UPDATE(0),
            NO_TEMPLATE(1),
            NO_CHANGE(2),
            EMPTY_CONFIG(3),
            NOT_AUTHORIZED(4);
            
            public static final int EMPTY_CONFIG_VALUE = 3;
            public static final int NOT_AUTHORIZED_VALUE = 4;
            public static final int NO_CHANGE_VALUE = 2;
            public static final int NO_TEMPLATE_VALUE = 1;
            public static final int UPDATE_VALUE = 0;
            private static final Internal.EnumLiteMap<NamespaceStatus> internalValueMap = null;
            private final int value;

            static {
                internalValueMap = new Internal.EnumLiteMap<NamespaceStatus>() {
                    public NamespaceStatus findValueByNumber(int i) {
                        return NamespaceStatus.forNumber(i);
                    }
                };
            }

            public final int getNumber() {
                return this.value;
            }

            @Deprecated
            public static NamespaceStatus valueOf(int i) {
                return forNumber(i);
            }

            public static NamespaceStatus forNumber(int i) {
                if (i == 0) {
                    return UPDATE;
                }
                if (i == 1) {
                    return NO_TEMPLATE;
                }
                if (i == 2) {
                    return NO_CHANGE;
                }
                if (i == 3) {
                    return EMPTY_CONFIG;
                }
                if (i != 4) {
                    return null;
                }
                return NOT_AUTHORIZED;
            }

            public static Internal.EnumLiteMap<NamespaceStatus> internalGetValueMap() {
                return internalValueMap;
            }

            private NamespaceStatus(int i) {
                this.value = i;
            }
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

        public boolean hasDigest() {
            return (this.bitField0_ & 2) == 2;
        }

        public String getDigest() {
            return this.digest_;
        }

        public ByteString getDigestBytes() {
            return ByteString.copyFromUtf8(this.digest_);
        }

        /* access modifiers changed from: private */
        public void setDigest(String str) {
            if (str != null) {
                this.bitField0_ |= 2;
                this.digest_ = str;
                return;
            }
            throw null;
        }

        /* access modifiers changed from: private */
        public void clearDigest() {
            this.bitField0_ &= -3;
            this.digest_ = getDefaultInstance().getDigest();
        }

        /* access modifiers changed from: private */
        public void setDigestBytes(ByteString byteString) {
            if (byteString != null) {
                this.bitField0_ |= 2;
                this.digest_ = byteString.toStringUtf8();
                return;
            }
            throw null;
        }

        public List<KeyValue> getEntryList() {
            return this.entry_;
        }

        public List<? extends KeyValueOrBuilder> getEntryOrBuilderList() {
            return this.entry_;
        }

        public int getEntryCount() {
            return this.entry_.size();
        }

        public KeyValue getEntry(int i) {
            return (KeyValue) this.entry_.get(i);
        }

        public KeyValueOrBuilder getEntryOrBuilder(int i) {
            return (KeyValueOrBuilder) this.entry_.get(i);
        }

        private void ensureEntryIsMutable() {
            if (!this.entry_.isModifiable()) {
                this.entry_ = GeneratedMessageLite.mutableCopy(this.entry_);
            }
        }

        /* access modifiers changed from: private */
        public void setEntry(int i, KeyValue keyValue) {
            if (keyValue != null) {
                ensureEntryIsMutable();
                this.entry_.set(i, keyValue);
                return;
            }
            throw null;
        }

        /* access modifiers changed from: private */
        public void setEntry(int i, KeyValue.Builder builder) {
            ensureEntryIsMutable();
            this.entry_.set(i, (KeyValue) builder.build());
        }

        /* access modifiers changed from: private */
        public void addEntry(KeyValue keyValue) {
            if (keyValue != null) {
                ensureEntryIsMutable();
                this.entry_.add(keyValue);
                return;
            }
            throw null;
        }

        /* access modifiers changed from: private */
        public void addEntry(int i, KeyValue keyValue) {
            if (keyValue != null) {
                ensureEntryIsMutable();
                this.entry_.add(i, keyValue);
                return;
            }
            throw null;
        }

        /* access modifiers changed from: private */
        public void addEntry(KeyValue.Builder builder) {
            ensureEntryIsMutable();
            this.entry_.add((KeyValue) builder.build());
        }

        /* access modifiers changed from: private */
        public void addEntry(int i, KeyValue.Builder builder) {
            ensureEntryIsMutable();
            this.entry_.add(i, (KeyValue) builder.build());
        }

        /* access modifiers changed from: private */
        public void addAllEntry(Iterable<? extends KeyValue> iterable) {
            ensureEntryIsMutable();
            AbstractMessageLite.addAll(iterable, this.entry_);
        }

        /* access modifiers changed from: private */
        public void clearEntry() {
            this.entry_ = emptyProtobufList();
        }

        /* access modifiers changed from: private */
        public void removeEntry(int i) {
            ensureEntryIsMutable();
            this.entry_.remove(i);
        }

        public boolean hasStatus() {
            return (this.bitField0_ & 4) == 4;
        }

        public NamespaceStatus getStatus() {
            NamespaceStatus forNumber = NamespaceStatus.forNumber(this.status_);
            return forNumber == null ? NamespaceStatus.UPDATE : forNumber;
        }

        /* access modifiers changed from: private */
        public void setStatus(NamespaceStatus namespaceStatus) {
            if (namespaceStatus != null) {
                this.bitField0_ |= 4;
                this.status_ = namespaceStatus.getNumber();
                return;
            }
            throw null;
        }

        /* access modifiers changed from: private */
        public void clearStatus() {
            this.bitField0_ &= -5;
            this.status_ = 0;
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            if ((this.bitField0_ & 1) == 1) {
                codedOutputStream.writeString(1, getNamespace());
            }
            if ((this.bitField0_ & 2) == 2) {
                codedOutputStream.writeString(2, getDigest());
            }
            for (int i = 0; i < this.entry_.size(); i++) {
                codedOutputStream.writeMessage(3, (MessageLite) this.entry_.get(i));
            }
            if ((this.bitField0_ & 4) == 4) {
                codedOutputStream.writeEnum(4, this.status_);
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int computeStringSize = (this.bitField0_ & 1) == 1 ? CodedOutputStream.computeStringSize(1, getNamespace()) + 0 : 0;
            if ((this.bitField0_ & 2) == 2) {
                computeStringSize += CodedOutputStream.computeStringSize(2, getDigest());
            }
            for (int i2 = 0; i2 < this.entry_.size(); i2++) {
                computeStringSize += CodedOutputStream.computeMessageSize(3, (MessageLite) this.entry_.get(i2));
            }
            if ((this.bitField0_ & 4) == 4) {
                computeStringSize += CodedOutputStream.computeEnumSize(4, this.status_);
            }
            int serializedSize = computeStringSize + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        public static AppNamespaceConfigTable parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (AppNamespaceConfigTable) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static AppNamespaceConfigTable parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (AppNamespaceConfigTable) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static AppNamespaceConfigTable parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (AppNamespaceConfigTable) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static AppNamespaceConfigTable parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (AppNamespaceConfigTable) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static AppNamespaceConfigTable parseFrom(InputStream inputStream) throws IOException {
            return (AppNamespaceConfigTable) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static AppNamespaceConfigTable parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (AppNamespaceConfigTable) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static AppNamespaceConfigTable parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (AppNamespaceConfigTable) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static AppNamespaceConfigTable parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (AppNamespaceConfigTable) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static AppNamespaceConfigTable parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (AppNamespaceConfigTable) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static AppNamespaceConfigTable parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (AppNamespaceConfigTable) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(AppNamespaceConfigTable appNamespaceConfigTable) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(appNamespaceConfigTable);
        }

        /* compiled from: com.google.firebase:firebase-config@@19.0.3 */
        public static final class Builder extends GeneratedMessageLite.Builder<AppNamespaceConfigTable, Builder> implements AppNamespaceConfigTableOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 r1) {
                this();
            }

            private Builder() {
                super(AppNamespaceConfigTable.DEFAULT_INSTANCE);
            }

            public boolean hasNamespace() {
                return ((AppNamespaceConfigTable) this.instance).hasNamespace();
            }

            public String getNamespace() {
                return ((AppNamespaceConfigTable) this.instance).getNamespace();
            }

            public ByteString getNamespaceBytes() {
                return ((AppNamespaceConfigTable) this.instance).getNamespaceBytes();
            }

            public Builder setNamespace(String str) {
                copyOnWrite();
                ((AppNamespaceConfigTable) this.instance).setNamespace(str);
                return this;
            }

            public Builder clearNamespace() {
                copyOnWrite();
                ((AppNamespaceConfigTable) this.instance).clearNamespace();
                return this;
            }

            public Builder setNamespaceBytes(ByteString byteString) {
                copyOnWrite();
                ((AppNamespaceConfigTable) this.instance).setNamespaceBytes(byteString);
                return this;
            }

            public boolean hasDigest() {
                return ((AppNamespaceConfigTable) this.instance).hasDigest();
            }

            public String getDigest() {
                return ((AppNamespaceConfigTable) this.instance).getDigest();
            }

            public ByteString getDigestBytes() {
                return ((AppNamespaceConfigTable) this.instance).getDigestBytes();
            }

            public Builder setDigest(String str) {
                copyOnWrite();
                ((AppNamespaceConfigTable) this.instance).setDigest(str);
                return this;
            }

            public Builder clearDigest() {
                copyOnWrite();
                ((AppNamespaceConfigTable) this.instance).clearDigest();
                return this;
            }

            public Builder setDigestBytes(ByteString byteString) {
                copyOnWrite();
                ((AppNamespaceConfigTable) this.instance).setDigestBytes(byteString);
                return this;
            }

            public List<KeyValue> getEntryList() {
                return Collections.unmodifiableList(((AppNamespaceConfigTable) this.instance).getEntryList());
            }

            public int getEntryCount() {
                return ((AppNamespaceConfigTable) this.instance).getEntryCount();
            }

            public KeyValue getEntry(int i) {
                return ((AppNamespaceConfigTable) this.instance).getEntry(i);
            }

            public Builder setEntry(int i, KeyValue keyValue) {
                copyOnWrite();
                ((AppNamespaceConfigTable) this.instance).setEntry(i, keyValue);
                return this;
            }

            public Builder setEntry(int i, KeyValue.Builder builder) {
                copyOnWrite();
                ((AppNamespaceConfigTable) this.instance).setEntry(i, builder);
                return this;
            }

            public Builder addEntry(KeyValue keyValue) {
                copyOnWrite();
                ((AppNamespaceConfigTable) this.instance).addEntry(keyValue);
                return this;
            }

            public Builder addEntry(int i, KeyValue keyValue) {
                copyOnWrite();
                ((AppNamespaceConfigTable) this.instance).addEntry(i, keyValue);
                return this;
            }

            public Builder addEntry(KeyValue.Builder builder) {
                copyOnWrite();
                ((AppNamespaceConfigTable) this.instance).addEntry(builder);
                return this;
            }

            public Builder addEntry(int i, KeyValue.Builder builder) {
                copyOnWrite();
                ((AppNamespaceConfigTable) this.instance).addEntry(i, builder);
                return this;
            }

            public Builder addAllEntry(Iterable<? extends KeyValue> iterable) {
                copyOnWrite();
                ((AppNamespaceConfigTable) this.instance).addAllEntry(iterable);
                return this;
            }

            public Builder clearEntry() {
                copyOnWrite();
                ((AppNamespaceConfigTable) this.instance).clearEntry();
                return this;
            }

            public Builder removeEntry(int i) {
                copyOnWrite();
                ((AppNamespaceConfigTable) this.instance).removeEntry(i);
                return this;
            }

            public boolean hasStatus() {
                return ((AppNamespaceConfigTable) this.instance).hasStatus();
            }

            public NamespaceStatus getStatus() {
                return ((AppNamespaceConfigTable) this.instance).getStatus();
            }

            public Builder setStatus(NamespaceStatus namespaceStatus) {
                copyOnWrite();
                ((AppNamespaceConfigTable) this.instance).setStatus(namespaceStatus);
                return this;
            }

            public Builder clearStatus() {
                copyOnWrite();
                ((AppNamespaceConfigTable) this.instance).clearStatus();
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
                case 1:
                    return new AppNamespaceConfigTable();
                case 2:
                    return DEFAULT_INSTANCE;
                case 3:
                    this.entry_.makeImmutable();
                    return null;
                case 4:
                    return new Builder((AnonymousClass1) null);
                case 5:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                    AppNamespaceConfigTable appNamespaceConfigTable = (AppNamespaceConfigTable) obj2;
                    this.namespace_ = visitor.visitString(hasNamespace(), this.namespace_, appNamespaceConfigTable.hasNamespace(), appNamespaceConfigTable.namespace_);
                    this.digest_ = visitor.visitString(hasDigest(), this.digest_, appNamespaceConfigTable.hasDigest(), appNamespaceConfigTable.digest_);
                    this.entry_ = visitor.visitList(this.entry_, appNamespaceConfigTable.entry_);
                    this.status_ = visitor.visitInt(hasStatus(), this.status_, appNamespaceConfigTable.hasStatus(), appNamespaceConfigTable.status_);
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                        this.bitField0_ |= appNamespaceConfigTable.bitField0_;
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
                                    String readString2 = codedInputStream.readString();
                                    this.bitField0_ |= 2;
                                    this.digest_ = readString2;
                                } else if (readTag == 26) {
                                    if (!this.entry_.isModifiable()) {
                                        this.entry_ = GeneratedMessageLite.mutableCopy(this.entry_);
                                    }
                                    this.entry_.add((KeyValue) codedInputStream.readMessage(KeyValue.parser(), extensionRegistryLite));
                                } else if (readTag == 32) {
                                    int readEnum = codedInputStream.readEnum();
                                    if (NamespaceStatus.forNumber(readEnum) == null) {
                                        super.mergeVarintField(4, readEnum);
                                    } else {
                                        this.bitField0_ |= 4;
                                        this.status_ = readEnum;
                                    }
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
                        synchronized (AppNamespaceConfigTable.class) {
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
            AppNamespaceConfigTable appNamespaceConfigTable = new AppNamespaceConfigTable();
            DEFAULT_INSTANCE = appNamespaceConfigTable;
            appNamespaceConfigTable.makeImmutable();
        }

        public static AppNamespaceConfigTable getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<AppNamespaceConfigTable> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    /* compiled from: com.google.firebase:firebase-config@@19.0.3 */
    public static final class AppConfigTable extends GeneratedMessageLite<AppConfigTable, Builder> implements AppConfigTableOrBuilder {
        public static final int APP_NAME_FIELD_NUMBER = 1;
        /* access modifiers changed from: private */
        public static final AppConfigTable DEFAULT_INSTANCE;
        public static final int EXPERIMENT_PAYLOAD_FIELD_NUMBER = 3;
        public static final int NAMESPACE_CONFIG_FIELD_NUMBER = 2;
        private static volatile Parser<AppConfigTable> PARSER;
        private String appName_ = "";
        private int bitField0_;
        private Internal.ProtobufList<ByteString> experimentPayload_ = emptyProtobufList();
        private Internal.ProtobufList<AppNamespaceConfigTable> namespaceConfig_ = emptyProtobufList();

        private AppConfigTable() {
        }

        public boolean hasAppName() {
            return (this.bitField0_ & 1) == 1;
        }

        public String getAppName() {
            return this.appName_;
        }

        public ByteString getAppNameBytes() {
            return ByteString.copyFromUtf8(this.appName_);
        }

        /* access modifiers changed from: private */
        public void setAppName(String str) {
            if (str != null) {
                this.bitField0_ |= 1;
                this.appName_ = str;
                return;
            }
            throw null;
        }

        /* access modifiers changed from: private */
        public void clearAppName() {
            this.bitField0_ &= -2;
            this.appName_ = getDefaultInstance().getAppName();
        }

        /* access modifiers changed from: private */
        public void setAppNameBytes(ByteString byteString) {
            if (byteString != null) {
                this.bitField0_ |= 1;
                this.appName_ = byteString.toStringUtf8();
                return;
            }
            throw null;
        }

        public List<AppNamespaceConfigTable> getNamespaceConfigList() {
            return this.namespaceConfig_;
        }

        public List<? extends AppNamespaceConfigTableOrBuilder> getNamespaceConfigOrBuilderList() {
            return this.namespaceConfig_;
        }

        public int getNamespaceConfigCount() {
            return this.namespaceConfig_.size();
        }

        public AppNamespaceConfigTable getNamespaceConfig(int i) {
            return (AppNamespaceConfigTable) this.namespaceConfig_.get(i);
        }

        public AppNamespaceConfigTableOrBuilder getNamespaceConfigOrBuilder(int i) {
            return (AppNamespaceConfigTableOrBuilder) this.namespaceConfig_.get(i);
        }

        private void ensureNamespaceConfigIsMutable() {
            if (!this.namespaceConfig_.isModifiable()) {
                this.namespaceConfig_ = GeneratedMessageLite.mutableCopy(this.namespaceConfig_);
            }
        }

        /* access modifiers changed from: private */
        public void setNamespaceConfig(int i, AppNamespaceConfigTable appNamespaceConfigTable) {
            if (appNamespaceConfigTable != null) {
                ensureNamespaceConfigIsMutable();
                this.namespaceConfig_.set(i, appNamespaceConfigTable);
                return;
            }
            throw null;
        }

        /* access modifiers changed from: private */
        public void setNamespaceConfig(int i, AppNamespaceConfigTable.Builder builder) {
            ensureNamespaceConfigIsMutable();
            this.namespaceConfig_.set(i, (AppNamespaceConfigTable) builder.build());
        }

        /* access modifiers changed from: private */
        public void addNamespaceConfig(AppNamespaceConfigTable appNamespaceConfigTable) {
            if (appNamespaceConfigTable != null) {
                ensureNamespaceConfigIsMutable();
                this.namespaceConfig_.add(appNamespaceConfigTable);
                return;
            }
            throw null;
        }

        /* access modifiers changed from: private */
        public void addNamespaceConfig(int i, AppNamespaceConfigTable appNamespaceConfigTable) {
            if (appNamespaceConfigTable != null) {
                ensureNamespaceConfigIsMutable();
                this.namespaceConfig_.add(i, appNamespaceConfigTable);
                return;
            }
            throw null;
        }

        /* access modifiers changed from: private */
        public void addNamespaceConfig(AppNamespaceConfigTable.Builder builder) {
            ensureNamespaceConfigIsMutable();
            this.namespaceConfig_.add((AppNamespaceConfigTable) builder.build());
        }

        /* access modifiers changed from: private */
        public void addNamespaceConfig(int i, AppNamespaceConfigTable.Builder builder) {
            ensureNamespaceConfigIsMutable();
            this.namespaceConfig_.add(i, (AppNamespaceConfigTable) builder.build());
        }

        /* access modifiers changed from: private */
        public void addAllNamespaceConfig(Iterable<? extends AppNamespaceConfigTable> iterable) {
            ensureNamespaceConfigIsMutable();
            AbstractMessageLite.addAll(iterable, this.namespaceConfig_);
        }

        /* access modifiers changed from: private */
        public void clearNamespaceConfig() {
            this.namespaceConfig_ = emptyProtobufList();
        }

        /* access modifiers changed from: private */
        public void removeNamespaceConfig(int i) {
            ensureNamespaceConfigIsMutable();
            this.namespaceConfig_.remove(i);
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
            if ((this.bitField0_ & 1) == 1) {
                codedOutputStream.writeString(1, getAppName());
            }
            for (int i = 0; i < this.namespaceConfig_.size(); i++) {
                codedOutputStream.writeMessage(2, (MessageLite) this.namespaceConfig_.get(i));
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
            int computeStringSize = (this.bitField0_ & 1) == 1 ? CodedOutputStream.computeStringSize(1, getAppName()) + 0 : 0;
            for (int i2 = 0; i2 < this.namespaceConfig_.size(); i2++) {
                computeStringSize += CodedOutputStream.computeMessageSize(2, (MessageLite) this.namespaceConfig_.get(i2));
            }
            int i3 = 0;
            for (int i4 = 0; i4 < this.experimentPayload_.size(); i4++) {
                i3 += CodedOutputStream.computeBytesSizeNoTag((ByteString) this.experimentPayload_.get(i4));
            }
            int size = computeStringSize + i3 + (getExperimentPayloadList().size() * 1) + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = size;
            return size;
        }

        public static AppConfigTable parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (AppConfigTable) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static AppConfigTable parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (AppConfigTable) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static AppConfigTable parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (AppConfigTable) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static AppConfigTable parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (AppConfigTable) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static AppConfigTable parseFrom(InputStream inputStream) throws IOException {
            return (AppConfigTable) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static AppConfigTable parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (AppConfigTable) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static AppConfigTable parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (AppConfigTable) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static AppConfigTable parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (AppConfigTable) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static AppConfigTable parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (AppConfigTable) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static AppConfigTable parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (AppConfigTable) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(AppConfigTable appConfigTable) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(appConfigTable);
        }

        /* compiled from: com.google.firebase:firebase-config@@19.0.3 */
        public static final class Builder extends GeneratedMessageLite.Builder<AppConfigTable, Builder> implements AppConfigTableOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 r1) {
                this();
            }

            private Builder() {
                super(AppConfigTable.DEFAULT_INSTANCE);
            }

            public boolean hasAppName() {
                return ((AppConfigTable) this.instance).hasAppName();
            }

            public String getAppName() {
                return ((AppConfigTable) this.instance).getAppName();
            }

            public ByteString getAppNameBytes() {
                return ((AppConfigTable) this.instance).getAppNameBytes();
            }

            public Builder setAppName(String str) {
                copyOnWrite();
                ((AppConfigTable) this.instance).setAppName(str);
                return this;
            }

            public Builder clearAppName() {
                copyOnWrite();
                ((AppConfigTable) this.instance).clearAppName();
                return this;
            }

            public Builder setAppNameBytes(ByteString byteString) {
                copyOnWrite();
                ((AppConfigTable) this.instance).setAppNameBytes(byteString);
                return this;
            }

            public List<AppNamespaceConfigTable> getNamespaceConfigList() {
                return Collections.unmodifiableList(((AppConfigTable) this.instance).getNamespaceConfigList());
            }

            public int getNamespaceConfigCount() {
                return ((AppConfigTable) this.instance).getNamespaceConfigCount();
            }

            public AppNamespaceConfigTable getNamespaceConfig(int i) {
                return ((AppConfigTable) this.instance).getNamespaceConfig(i);
            }

            public Builder setNamespaceConfig(int i, AppNamespaceConfigTable appNamespaceConfigTable) {
                copyOnWrite();
                ((AppConfigTable) this.instance).setNamespaceConfig(i, appNamespaceConfigTable);
                return this;
            }

            public Builder setNamespaceConfig(int i, AppNamespaceConfigTable.Builder builder) {
                copyOnWrite();
                ((AppConfigTable) this.instance).setNamespaceConfig(i, builder);
                return this;
            }

            public Builder addNamespaceConfig(AppNamespaceConfigTable appNamespaceConfigTable) {
                copyOnWrite();
                ((AppConfigTable) this.instance).addNamespaceConfig(appNamespaceConfigTable);
                return this;
            }

            public Builder addNamespaceConfig(int i, AppNamespaceConfigTable appNamespaceConfigTable) {
                copyOnWrite();
                ((AppConfigTable) this.instance).addNamespaceConfig(i, appNamespaceConfigTable);
                return this;
            }

            public Builder addNamespaceConfig(AppNamespaceConfigTable.Builder builder) {
                copyOnWrite();
                ((AppConfigTable) this.instance).addNamespaceConfig(builder);
                return this;
            }

            public Builder addNamespaceConfig(int i, AppNamespaceConfigTable.Builder builder) {
                copyOnWrite();
                ((AppConfigTable) this.instance).addNamespaceConfig(i, builder);
                return this;
            }

            public Builder addAllNamespaceConfig(Iterable<? extends AppNamespaceConfigTable> iterable) {
                copyOnWrite();
                ((AppConfigTable) this.instance).addAllNamespaceConfig(iterable);
                return this;
            }

            public Builder clearNamespaceConfig() {
                copyOnWrite();
                ((AppConfigTable) this.instance).clearNamespaceConfig();
                return this;
            }

            public Builder removeNamespaceConfig(int i) {
                copyOnWrite();
                ((AppConfigTable) this.instance).removeNamespaceConfig(i);
                return this;
            }

            public List<ByteString> getExperimentPayloadList() {
                return Collections.unmodifiableList(((AppConfigTable) this.instance).getExperimentPayloadList());
            }

            public int getExperimentPayloadCount() {
                return ((AppConfigTable) this.instance).getExperimentPayloadCount();
            }

            public ByteString getExperimentPayload(int i) {
                return ((AppConfigTable) this.instance).getExperimentPayload(i);
            }

            public Builder setExperimentPayload(int i, ByteString byteString) {
                copyOnWrite();
                ((AppConfigTable) this.instance).setExperimentPayload(i, byteString);
                return this;
            }

            public Builder addExperimentPayload(ByteString byteString) {
                copyOnWrite();
                ((AppConfigTable) this.instance).addExperimentPayload(byteString);
                return this;
            }

            public Builder addAllExperimentPayload(Iterable<? extends ByteString> iterable) {
                copyOnWrite();
                ((AppConfigTable) this.instance).addAllExperimentPayload(iterable);
                return this;
            }

            public Builder clearExperimentPayload() {
                copyOnWrite();
                ((AppConfigTable) this.instance).clearExperimentPayload();
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
                case 1:
                    return new AppConfigTable();
                case 2:
                    return DEFAULT_INSTANCE;
                case 3:
                    this.namespaceConfig_.makeImmutable();
                    this.experimentPayload_.makeImmutable();
                    return null;
                case 4:
                    return new Builder((AnonymousClass1) null);
                case 5:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                    AppConfigTable appConfigTable = (AppConfigTable) obj2;
                    this.appName_ = visitor.visitString(hasAppName(), this.appName_, appConfigTable.hasAppName(), appConfigTable.appName_);
                    this.namespaceConfig_ = visitor.visitList(this.namespaceConfig_, appConfigTable.namespaceConfig_);
                    this.experimentPayload_ = visitor.visitList(this.experimentPayload_, appConfigTable.experimentPayload_);
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                        this.bitField0_ |= appConfigTable.bitField0_;
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
                                    this.appName_ = readString;
                                } else if (readTag == 18) {
                                    if (!this.namespaceConfig_.isModifiable()) {
                                        this.namespaceConfig_ = GeneratedMessageLite.mutableCopy(this.namespaceConfig_);
                                    }
                                    this.namespaceConfig_.add((AppNamespaceConfigTable) codedInputStream.readMessage(AppNamespaceConfigTable.parser(), extensionRegistryLite));
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
                        synchronized (AppConfigTable.class) {
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
            AppConfigTable appConfigTable = new AppConfigTable();
            DEFAULT_INSTANCE = appConfigTable;
            appConfigTable.makeImmutable();
        }

        public static AppConfigTable getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<AppConfigTable> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    /* compiled from: com.google.firebase:firebase-config@@19.0.3 */
    public static final class ConfigFetchResponse extends GeneratedMessageLite<ConfigFetchResponse, Builder> implements ConfigFetchResponseOrBuilder {
        public static final int APP_CONFIG_FIELD_NUMBER = 4;
        /* access modifiers changed from: private */
        public static final ConfigFetchResponse DEFAULT_INSTANCE;
        public static final int INTERNAL_METADATA_FIELD_NUMBER = 3;
        public static final int PACKAGE_TABLE_FIELD_NUMBER = 1;
        private static volatile Parser<ConfigFetchResponse> PARSER = null;
        public static final int STATUS_FIELD_NUMBER = 2;
        private Internal.ProtobufList<AppConfigTable> appConfig_ = emptyProtobufList();
        private int bitField0_;
        private Internal.ProtobufList<KeyValue> internalMetadata_ = emptyProtobufList();
        private Internal.ProtobufList<PackageTable> packageTable_ = emptyProtobufList();
        private int status_;

        private ConfigFetchResponse() {
        }

        /* compiled from: com.google.firebase:firebase-config@@19.0.3 */
        public enum ResponseStatus implements Internal.EnumLite {
            SUCCESS(0),
            NO_PACKAGES_IN_REQUEST(1);
            
            public static final int NO_PACKAGES_IN_REQUEST_VALUE = 1;
            public static final int SUCCESS_VALUE = 0;
            private static final Internal.EnumLiteMap<ResponseStatus> internalValueMap = null;
            private final int value;

            static {
                internalValueMap = new Internal.EnumLiteMap<ResponseStatus>() {
                    public ResponseStatus findValueByNumber(int i) {
                        return ResponseStatus.forNumber(i);
                    }
                };
            }

            public final int getNumber() {
                return this.value;
            }

            @Deprecated
            public static ResponseStatus valueOf(int i) {
                return forNumber(i);
            }

            public static ResponseStatus forNumber(int i) {
                if (i == 0) {
                    return SUCCESS;
                }
                if (i != 1) {
                    return null;
                }
                return NO_PACKAGES_IN_REQUEST;
            }

            public static Internal.EnumLiteMap<ResponseStatus> internalGetValueMap() {
                return internalValueMap;
            }

            private ResponseStatus(int i) {
                this.value = i;
            }
        }

        public List<PackageTable> getPackageTableList() {
            return this.packageTable_;
        }

        public List<? extends PackageTableOrBuilder> getPackageTableOrBuilderList() {
            return this.packageTable_;
        }

        public int getPackageTableCount() {
            return this.packageTable_.size();
        }

        public PackageTable getPackageTable(int i) {
            return (PackageTable) this.packageTable_.get(i);
        }

        public PackageTableOrBuilder getPackageTableOrBuilder(int i) {
            return (PackageTableOrBuilder) this.packageTable_.get(i);
        }

        private void ensurePackageTableIsMutable() {
            if (!this.packageTable_.isModifiable()) {
                this.packageTable_ = GeneratedMessageLite.mutableCopy(this.packageTable_);
            }
        }

        /* access modifiers changed from: private */
        public void setPackageTable(int i, PackageTable packageTable) {
            if (packageTable != null) {
                ensurePackageTableIsMutable();
                this.packageTable_.set(i, packageTable);
                return;
            }
            throw null;
        }

        /* access modifiers changed from: private */
        public void setPackageTable(int i, PackageTable.Builder builder) {
            ensurePackageTableIsMutable();
            this.packageTable_.set(i, (PackageTable) builder.build());
        }

        /* access modifiers changed from: private */
        public void addPackageTable(PackageTable packageTable) {
            if (packageTable != null) {
                ensurePackageTableIsMutable();
                this.packageTable_.add(packageTable);
                return;
            }
            throw null;
        }

        /* access modifiers changed from: private */
        public void addPackageTable(int i, PackageTable packageTable) {
            if (packageTable != null) {
                ensurePackageTableIsMutable();
                this.packageTable_.add(i, packageTable);
                return;
            }
            throw null;
        }

        /* access modifiers changed from: private */
        public void addPackageTable(PackageTable.Builder builder) {
            ensurePackageTableIsMutable();
            this.packageTable_.add((PackageTable) builder.build());
        }

        /* access modifiers changed from: private */
        public void addPackageTable(int i, PackageTable.Builder builder) {
            ensurePackageTableIsMutable();
            this.packageTable_.add(i, (PackageTable) builder.build());
        }

        /* access modifiers changed from: private */
        public void addAllPackageTable(Iterable<? extends PackageTable> iterable) {
            ensurePackageTableIsMutable();
            AbstractMessageLite.addAll(iterable, this.packageTable_);
        }

        /* access modifiers changed from: private */
        public void clearPackageTable() {
            this.packageTable_ = emptyProtobufList();
        }

        /* access modifiers changed from: private */
        public void removePackageTable(int i) {
            ensurePackageTableIsMutable();
            this.packageTable_.remove(i);
        }

        public boolean hasStatus() {
            return (this.bitField0_ & 1) == 1;
        }

        public ResponseStatus getStatus() {
            ResponseStatus forNumber = ResponseStatus.forNumber(this.status_);
            return forNumber == null ? ResponseStatus.SUCCESS : forNumber;
        }

        /* access modifiers changed from: private */
        public void setStatus(ResponseStatus responseStatus) {
            if (responseStatus != null) {
                this.bitField0_ |= 1;
                this.status_ = responseStatus.getNumber();
                return;
            }
            throw null;
        }

        /* access modifiers changed from: private */
        public void clearStatus() {
            this.bitField0_ &= -2;
            this.status_ = 0;
        }

        public List<KeyValue> getInternalMetadataList() {
            return this.internalMetadata_;
        }

        public List<? extends KeyValueOrBuilder> getInternalMetadataOrBuilderList() {
            return this.internalMetadata_;
        }

        public int getInternalMetadataCount() {
            return this.internalMetadata_.size();
        }

        public KeyValue getInternalMetadata(int i) {
            return (KeyValue) this.internalMetadata_.get(i);
        }

        public KeyValueOrBuilder getInternalMetadataOrBuilder(int i) {
            return (KeyValueOrBuilder) this.internalMetadata_.get(i);
        }

        private void ensureInternalMetadataIsMutable() {
            if (!this.internalMetadata_.isModifiable()) {
                this.internalMetadata_ = GeneratedMessageLite.mutableCopy(this.internalMetadata_);
            }
        }

        /* access modifiers changed from: private */
        public void setInternalMetadata(int i, KeyValue keyValue) {
            if (keyValue != null) {
                ensureInternalMetadataIsMutable();
                this.internalMetadata_.set(i, keyValue);
                return;
            }
            throw null;
        }

        /* access modifiers changed from: private */
        public void setInternalMetadata(int i, KeyValue.Builder builder) {
            ensureInternalMetadataIsMutable();
            this.internalMetadata_.set(i, (KeyValue) builder.build());
        }

        /* access modifiers changed from: private */
        public void addInternalMetadata(KeyValue keyValue) {
            if (keyValue != null) {
                ensureInternalMetadataIsMutable();
                this.internalMetadata_.add(keyValue);
                return;
            }
            throw null;
        }

        /* access modifiers changed from: private */
        public void addInternalMetadata(int i, KeyValue keyValue) {
            if (keyValue != null) {
                ensureInternalMetadataIsMutable();
                this.internalMetadata_.add(i, keyValue);
                return;
            }
            throw null;
        }

        /* access modifiers changed from: private */
        public void addInternalMetadata(KeyValue.Builder builder) {
            ensureInternalMetadataIsMutable();
            this.internalMetadata_.add((KeyValue) builder.build());
        }

        /* access modifiers changed from: private */
        public void addInternalMetadata(int i, KeyValue.Builder builder) {
            ensureInternalMetadataIsMutable();
            this.internalMetadata_.add(i, (KeyValue) builder.build());
        }

        /* access modifiers changed from: private */
        public void addAllInternalMetadata(Iterable<? extends KeyValue> iterable) {
            ensureInternalMetadataIsMutable();
            AbstractMessageLite.addAll(iterable, this.internalMetadata_);
        }

        /* access modifiers changed from: private */
        public void clearInternalMetadata() {
            this.internalMetadata_ = emptyProtobufList();
        }

        /* access modifiers changed from: private */
        public void removeInternalMetadata(int i) {
            ensureInternalMetadataIsMutable();
            this.internalMetadata_.remove(i);
        }

        public List<AppConfigTable> getAppConfigList() {
            return this.appConfig_;
        }

        public List<? extends AppConfigTableOrBuilder> getAppConfigOrBuilderList() {
            return this.appConfig_;
        }

        public int getAppConfigCount() {
            return this.appConfig_.size();
        }

        public AppConfigTable getAppConfig(int i) {
            return (AppConfigTable) this.appConfig_.get(i);
        }

        public AppConfigTableOrBuilder getAppConfigOrBuilder(int i) {
            return (AppConfigTableOrBuilder) this.appConfig_.get(i);
        }

        private void ensureAppConfigIsMutable() {
            if (!this.appConfig_.isModifiable()) {
                this.appConfig_ = GeneratedMessageLite.mutableCopy(this.appConfig_);
            }
        }

        /* access modifiers changed from: private */
        public void setAppConfig(int i, AppConfigTable appConfigTable) {
            if (appConfigTable != null) {
                ensureAppConfigIsMutable();
                this.appConfig_.set(i, appConfigTable);
                return;
            }
            throw null;
        }

        /* access modifiers changed from: private */
        public void setAppConfig(int i, AppConfigTable.Builder builder) {
            ensureAppConfigIsMutable();
            this.appConfig_.set(i, (AppConfigTable) builder.build());
        }

        /* access modifiers changed from: private */
        public void addAppConfig(AppConfigTable appConfigTable) {
            if (appConfigTable != null) {
                ensureAppConfigIsMutable();
                this.appConfig_.add(appConfigTable);
                return;
            }
            throw null;
        }

        /* access modifiers changed from: private */
        public void addAppConfig(int i, AppConfigTable appConfigTable) {
            if (appConfigTable != null) {
                ensureAppConfigIsMutable();
                this.appConfig_.add(i, appConfigTable);
                return;
            }
            throw null;
        }

        /* access modifiers changed from: private */
        public void addAppConfig(AppConfigTable.Builder builder) {
            ensureAppConfigIsMutable();
            this.appConfig_.add((AppConfigTable) builder.build());
        }

        /* access modifiers changed from: private */
        public void addAppConfig(int i, AppConfigTable.Builder builder) {
            ensureAppConfigIsMutable();
            this.appConfig_.add(i, (AppConfigTable) builder.build());
        }

        /* access modifiers changed from: private */
        public void addAllAppConfig(Iterable<? extends AppConfigTable> iterable) {
            ensureAppConfigIsMutable();
            AbstractMessageLite.addAll(iterable, this.appConfig_);
        }

        /* access modifiers changed from: private */
        public void clearAppConfig() {
            this.appConfig_ = emptyProtobufList();
        }

        /* access modifiers changed from: private */
        public void removeAppConfig(int i) {
            ensureAppConfigIsMutable();
            this.appConfig_.remove(i);
        }

        public void writeTo(CodedOutputStream codedOutputStream) throws IOException {
            for (int i = 0; i < this.packageTable_.size(); i++) {
                codedOutputStream.writeMessage(1, (MessageLite) this.packageTable_.get(i));
            }
            if ((this.bitField0_ & 1) == 1) {
                codedOutputStream.writeEnum(2, this.status_);
            }
            for (int i2 = 0; i2 < this.internalMetadata_.size(); i2++) {
                codedOutputStream.writeMessage(3, (MessageLite) this.internalMetadata_.get(i2));
            }
            for (int i3 = 0; i3 < this.appConfig_.size(); i3++) {
                codedOutputStream.writeMessage(4, (MessageLite) this.appConfig_.get(i3));
            }
            this.unknownFields.writeTo(codedOutputStream);
        }

        public int getSerializedSize() {
            int i = this.memoizedSerializedSize;
            if (i != -1) {
                return i;
            }
            int i2 = 0;
            for (int i3 = 0; i3 < this.packageTable_.size(); i3++) {
                i2 += CodedOutputStream.computeMessageSize(1, (MessageLite) this.packageTable_.get(i3));
            }
            if ((this.bitField0_ & 1) == 1) {
                i2 += CodedOutputStream.computeEnumSize(2, this.status_);
            }
            for (int i4 = 0; i4 < this.internalMetadata_.size(); i4++) {
                i2 += CodedOutputStream.computeMessageSize(3, (MessageLite) this.internalMetadata_.get(i4));
            }
            for (int i5 = 0; i5 < this.appConfig_.size(); i5++) {
                i2 += CodedOutputStream.computeMessageSize(4, (MessageLite) this.appConfig_.get(i5));
            }
            int serializedSize = i2 + this.unknownFields.getSerializedSize();
            this.memoizedSerializedSize = serializedSize;
            return serializedSize;
        }

        public static ConfigFetchResponse parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (ConfigFetchResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static ConfigFetchResponse parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (ConfigFetchResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static ConfigFetchResponse parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (ConfigFetchResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static ConfigFetchResponse parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (ConfigFetchResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static ConfigFetchResponse parseFrom(InputStream inputStream) throws IOException {
            return (ConfigFetchResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static ConfigFetchResponse parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ConfigFetchResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static ConfigFetchResponse parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (ConfigFetchResponse) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static ConfigFetchResponse parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ConfigFetchResponse) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static ConfigFetchResponse parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (ConfigFetchResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static ConfigFetchResponse parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (ConfigFetchResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return (Builder) DEFAULT_INSTANCE.toBuilder();
        }

        public static Builder newBuilder(ConfigFetchResponse configFetchResponse) {
            return (Builder) ((Builder) DEFAULT_INSTANCE.toBuilder()).mergeFrom(configFetchResponse);
        }

        /* compiled from: com.google.firebase:firebase-config@@19.0.3 */
        public static final class Builder extends GeneratedMessageLite.Builder<ConfigFetchResponse, Builder> implements ConfigFetchResponseOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 r1) {
                this();
            }

            private Builder() {
                super(ConfigFetchResponse.DEFAULT_INSTANCE);
            }

            public List<PackageTable> getPackageTableList() {
                return Collections.unmodifiableList(((ConfigFetchResponse) this.instance).getPackageTableList());
            }

            public int getPackageTableCount() {
                return ((ConfigFetchResponse) this.instance).getPackageTableCount();
            }

            public PackageTable getPackageTable(int i) {
                return ((ConfigFetchResponse) this.instance).getPackageTable(i);
            }

            public Builder setPackageTable(int i, PackageTable packageTable) {
                copyOnWrite();
                ((ConfigFetchResponse) this.instance).setPackageTable(i, packageTable);
                return this;
            }

            public Builder setPackageTable(int i, PackageTable.Builder builder) {
                copyOnWrite();
                ((ConfigFetchResponse) this.instance).setPackageTable(i, builder);
                return this;
            }

            public Builder addPackageTable(PackageTable packageTable) {
                copyOnWrite();
                ((ConfigFetchResponse) this.instance).addPackageTable(packageTable);
                return this;
            }

            public Builder addPackageTable(int i, PackageTable packageTable) {
                copyOnWrite();
                ((ConfigFetchResponse) this.instance).addPackageTable(i, packageTable);
                return this;
            }

            public Builder addPackageTable(PackageTable.Builder builder) {
                copyOnWrite();
                ((ConfigFetchResponse) this.instance).addPackageTable(builder);
                return this;
            }

            public Builder addPackageTable(int i, PackageTable.Builder builder) {
                copyOnWrite();
                ((ConfigFetchResponse) this.instance).addPackageTable(i, builder);
                return this;
            }

            public Builder addAllPackageTable(Iterable<? extends PackageTable> iterable) {
                copyOnWrite();
                ((ConfigFetchResponse) this.instance).addAllPackageTable(iterable);
                return this;
            }

            public Builder clearPackageTable() {
                copyOnWrite();
                ((ConfigFetchResponse) this.instance).clearPackageTable();
                return this;
            }

            public Builder removePackageTable(int i) {
                copyOnWrite();
                ((ConfigFetchResponse) this.instance).removePackageTable(i);
                return this;
            }

            public boolean hasStatus() {
                return ((ConfigFetchResponse) this.instance).hasStatus();
            }

            public ResponseStatus getStatus() {
                return ((ConfigFetchResponse) this.instance).getStatus();
            }

            public Builder setStatus(ResponseStatus responseStatus) {
                copyOnWrite();
                ((ConfigFetchResponse) this.instance).setStatus(responseStatus);
                return this;
            }

            public Builder clearStatus() {
                copyOnWrite();
                ((ConfigFetchResponse) this.instance).clearStatus();
                return this;
            }

            public List<KeyValue> getInternalMetadataList() {
                return Collections.unmodifiableList(((ConfigFetchResponse) this.instance).getInternalMetadataList());
            }

            public int getInternalMetadataCount() {
                return ((ConfigFetchResponse) this.instance).getInternalMetadataCount();
            }

            public KeyValue getInternalMetadata(int i) {
                return ((ConfigFetchResponse) this.instance).getInternalMetadata(i);
            }

            public Builder setInternalMetadata(int i, KeyValue keyValue) {
                copyOnWrite();
                ((ConfigFetchResponse) this.instance).setInternalMetadata(i, keyValue);
                return this;
            }

            public Builder setInternalMetadata(int i, KeyValue.Builder builder) {
                copyOnWrite();
                ((ConfigFetchResponse) this.instance).setInternalMetadata(i, builder);
                return this;
            }

            public Builder addInternalMetadata(KeyValue keyValue) {
                copyOnWrite();
                ((ConfigFetchResponse) this.instance).addInternalMetadata(keyValue);
                return this;
            }

            public Builder addInternalMetadata(int i, KeyValue keyValue) {
                copyOnWrite();
                ((ConfigFetchResponse) this.instance).addInternalMetadata(i, keyValue);
                return this;
            }

            public Builder addInternalMetadata(KeyValue.Builder builder) {
                copyOnWrite();
                ((ConfigFetchResponse) this.instance).addInternalMetadata(builder);
                return this;
            }

            public Builder addInternalMetadata(int i, KeyValue.Builder builder) {
                copyOnWrite();
                ((ConfigFetchResponse) this.instance).addInternalMetadata(i, builder);
                return this;
            }

            public Builder addAllInternalMetadata(Iterable<? extends KeyValue> iterable) {
                copyOnWrite();
                ((ConfigFetchResponse) this.instance).addAllInternalMetadata(iterable);
                return this;
            }

            public Builder clearInternalMetadata() {
                copyOnWrite();
                ((ConfigFetchResponse) this.instance).clearInternalMetadata();
                return this;
            }

            public Builder removeInternalMetadata(int i) {
                copyOnWrite();
                ((ConfigFetchResponse) this.instance).removeInternalMetadata(i);
                return this;
            }

            public List<AppConfigTable> getAppConfigList() {
                return Collections.unmodifiableList(((ConfigFetchResponse) this.instance).getAppConfigList());
            }

            public int getAppConfigCount() {
                return ((ConfigFetchResponse) this.instance).getAppConfigCount();
            }

            public AppConfigTable getAppConfig(int i) {
                return ((ConfigFetchResponse) this.instance).getAppConfig(i);
            }

            public Builder setAppConfig(int i, AppConfigTable appConfigTable) {
                copyOnWrite();
                ((ConfigFetchResponse) this.instance).setAppConfig(i, appConfigTable);
                return this;
            }

            public Builder setAppConfig(int i, AppConfigTable.Builder builder) {
                copyOnWrite();
                ((ConfigFetchResponse) this.instance).setAppConfig(i, builder);
                return this;
            }

            public Builder addAppConfig(AppConfigTable appConfigTable) {
                copyOnWrite();
                ((ConfigFetchResponse) this.instance).addAppConfig(appConfigTable);
                return this;
            }

            public Builder addAppConfig(int i, AppConfigTable appConfigTable) {
                copyOnWrite();
                ((ConfigFetchResponse) this.instance).addAppConfig(i, appConfigTable);
                return this;
            }

            public Builder addAppConfig(AppConfigTable.Builder builder) {
                copyOnWrite();
                ((ConfigFetchResponse) this.instance).addAppConfig(builder);
                return this;
            }

            public Builder addAppConfig(int i, AppConfigTable.Builder builder) {
                copyOnWrite();
                ((ConfigFetchResponse) this.instance).addAppConfig(i, builder);
                return this;
            }

            public Builder addAllAppConfig(Iterable<? extends AppConfigTable> iterable) {
                copyOnWrite();
                ((ConfigFetchResponse) this.instance).addAllAppConfig(iterable);
                return this;
            }

            public Builder clearAppConfig() {
                copyOnWrite();
                ((ConfigFetchResponse) this.instance).clearAppConfig();
                return this;
            }

            public Builder removeAppConfig(int i) {
                copyOnWrite();
                ((ConfigFetchResponse) this.instance).removeAppConfig(i);
                return this;
            }
        }

        /* access modifiers changed from: protected */
        public final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
                case 1:
                    return new ConfigFetchResponse();
                case 2:
                    return DEFAULT_INSTANCE;
                case 3:
                    this.packageTable_.makeImmutable();
                    this.internalMetadata_.makeImmutable();
                    this.appConfig_.makeImmutable();
                    return null;
                case 4:
                    return new Builder((AnonymousClass1) null);
                case 5:
                    GeneratedMessageLite.Visitor visitor = (GeneratedMessageLite.Visitor) obj;
                    ConfigFetchResponse configFetchResponse = (ConfigFetchResponse) obj2;
                    this.packageTable_ = visitor.visitList(this.packageTable_, configFetchResponse.packageTable_);
                    this.status_ = visitor.visitInt(hasStatus(), this.status_, configFetchResponse.hasStatus(), configFetchResponse.status_);
                    this.internalMetadata_ = visitor.visitList(this.internalMetadata_, configFetchResponse.internalMetadata_);
                    this.appConfig_ = visitor.visitList(this.appConfig_, configFetchResponse.appConfig_);
                    if (visitor == GeneratedMessageLite.MergeFromVisitor.INSTANCE) {
                        this.bitField0_ |= configFetchResponse.bitField0_;
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
                                    if (!this.packageTable_.isModifiable()) {
                                        this.packageTable_ = GeneratedMessageLite.mutableCopy(this.packageTable_);
                                    }
                                    this.packageTable_.add((PackageTable) codedInputStream.readMessage(PackageTable.parser(), extensionRegistryLite));
                                } else if (readTag == 16) {
                                    int readEnum = codedInputStream.readEnum();
                                    if (ResponseStatus.forNumber(readEnum) == null) {
                                        super.mergeVarintField(2, readEnum);
                                    } else {
                                        this.bitField0_ = 1 | this.bitField0_;
                                        this.status_ = readEnum;
                                    }
                                } else if (readTag == 26) {
                                    if (!this.internalMetadata_.isModifiable()) {
                                        this.internalMetadata_ = GeneratedMessageLite.mutableCopy(this.internalMetadata_);
                                    }
                                    this.internalMetadata_.add((KeyValue) codedInputStream.readMessage(KeyValue.parser(), extensionRegistryLite));
                                } else if (readTag == 34) {
                                    if (!this.appConfig_.isModifiable()) {
                                        this.appConfig_ = GeneratedMessageLite.mutableCopy(this.appConfig_);
                                    }
                                    this.appConfig_.add((AppConfigTable) codedInputStream.readMessage(AppConfigTable.parser(), extensionRegistryLite));
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
                        synchronized (ConfigFetchResponse.class) {
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
            ConfigFetchResponse configFetchResponse = new ConfigFetchResponse();
            DEFAULT_INSTANCE = configFetchResponse;
            configFetchResponse.makeImmutable();
        }

        public static ConfigFetchResponse getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<ConfigFetchResponse> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }
}
