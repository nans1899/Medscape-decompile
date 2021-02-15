package com.webmd.wbmdproffesionalauthentication.utilities;

import android.content.Context;
import com.wbmd.environment.EnvironmentConstants;
import com.wbmd.environment.EnvironmentManager;
import com.webmd.wbmdproffesionalauthentication.model.EnvironmentData;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lcom/webmd/wbmdproffesionalauthentication/utilities/EnvironmentUtil;", "", "()V", "Companion", "wbmdprofessionalauthenticaion_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: EnvironmentUtil.kt */
public final class EnvironmentUtil {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\u0004J\u0010\u0010\b\u001a\u00020\u00042\b\u0010\t\u001a\u0004\u0018\u00010\u0004J\u0012\u0010\n\u001a\u0004\u0018\u00010\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¨\u0006\u000b"}, d2 = {"Lcom/webmd/wbmdproffesionalauthentication/utilities/EnvironmentUtil$Companion;", "", "()V", "getUrl", "", "context", "Landroid/content/Context;", "urlName", "getUrlPrefixForEnv", "environment", "getUrlPrefixForServiceEnv", "wbmdprofessionalauthenticaion_release"}, k = 1, mv = {1, 4, 0})
    /* compiled from: EnvironmentUtil.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final String getUrl(Context context, String str) {
            if (context == null) {
                return "";
            }
            EnvironmentData environmentData = new EnvironmentData();
            environmentData.getEnvironmentData();
            String url = environmentData.getUrl(str);
            if (url == null) {
                return "";
            }
            if (url.length() == 0) {
                return "";
            }
            String urlPrefixForEnv = getUrlPrefixForEnv(new EnvironmentManager().getEnvironmentWithDefault(context, EnvironmentConstants.MODULE_SERVICE));
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            String format = String.format(url, Arrays.copyOf(new Object[]{urlPrefixForEnv}, 1));
            Intrinsics.checkNotNullExpressionValue(format, "java.lang.String.format(format, *args)");
            return format;
        }

        public final String getUrlPrefixForEnv(String str) {
            if (str == null) {
                return "";
            }
            switch (str.hashCode()) {
                case 206969445:
                    boolean equals = str.equals(EnvironmentConstants.PRODUCTION);
                    return "";
                case 446124970:
                    if (str.equals(EnvironmentConstants.DEV01)) {
                        return ".dev01";
                    }
                    return "";
                case 568937877:
                    if (str.equals(EnvironmentConstants.PERF)) {
                        return ".perf";
                    }
                    return "";
                case 568961724:
                    if (str.equals(EnvironmentConstants.QA00)) {
                        return ".qa00";
                    }
                    return "";
                case 568961725:
                    if (str.equals(EnvironmentConstants.QA01)) {
                        return ".qa01";
                    }
                    return "";
                case 568961726:
                    if (str.equals(EnvironmentConstants.QA02)) {
                        return ".qa02";
                    }
                    return "";
                default:
                    return "";
            }
        }

        public final String getUrlPrefixForServiceEnv(Context context) {
            return context != null ? getUrlPrefixForEnv(new EnvironmentManager().getEnvironmentWithDefault(context, EnvironmentConstants.MODULE_SERVICE)) : "";
        }
    }
}
