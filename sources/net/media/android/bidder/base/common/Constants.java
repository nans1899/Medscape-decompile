package net.media.android.bidder.base.common;

import android.os.Build;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;

public final class Constants {
    public static final String AES_ENCRYPTION_KEY = "aes_encryption_key";
    public static final String ANDROID = "android";
    public static final String APP_PACKAGE = "package";
    public static final String APP_VERSION_CODE = "app_version_code";
    public static final String APP_VERSION_NAME = "app_version_name";
    public static final int CONFIG_EXPIRED = 918;
    public static final String CONFIG_FILE_NAME = "config";
    public static final String CONFIG_ID = "mnet_id";
    public static final String DEFAULT_DTC = "dtc";
    public static final String DEVICE_MODEL_STRING = (Build.MANUFACTURER + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + Build.MODEL);
    public static final String PUBLISHER_ID = "publisher_id";
    public static final String RSA_PRIVATE_KEY = "rsa_private_key";
    public static final String RSA_PUBLIC_KEY = "rsa_public_key";
    public static final String SDK_DATA_EVENT = "sdk_data";
    public static final String SDK_INT_STRING = ("" + Build.VERSION.SDK_INT);
    public static final String SHARED_PREFERENCES_NAME = "__mnet_pr";
    public static final String VERSION_CODE_STRING = "24";
    public static final String YBNCA_BIDDER_ID = "10000";

    public static class Capabilities {
        public static final String BANNER = "banner";
        public static final String RESPONSIVE_BANNER = "responsive_banner";
        public static final String VIDEO = "video";
    }

    public static class HB {
        public static final String AD_CYCLE_ID = "ad_cycle_id";
    }
}
