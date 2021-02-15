package com.webmd.wbmdproffesionalauthentication.model;

import java.util.HashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u0000 \f2\u00020\u0001:\u0001\fB\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\b\u001a\u00020\tJ\u0010\u0010\n\u001a\u00020\u00052\b\u0010\u000b\u001a\u0004\u0018\u00010\u0005R\u001d\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\r"}, d2 = {"Lcom/webmd/wbmdproffesionalauthentication/model/EnvironmentData;", "", "()V", "links", "", "", "getLinks", "()Ljava/util/Map;", "getEnvironmentData", "", "getUrl", "key", "Companion", "wbmdprofessionalauthenticaion_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: EnvironmentData.kt */
public final class EnvironmentData {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String EMAIL_VALIDATION_URL = "WBMD_EMAIL_VALIDATION";
    public static final String LOGIN_URL = "WBMD_LOGIN_URL";
    public static final String PASSWORD_RESET_URL = "WBMD_PASSWORD_RESET";
    public static final String REGISTER_URL = "WBMD_REGISTER_URL";
    public static final String REGISTRATION_DATA_URL = "WBMD_REGISTRATION_DATA_URL";
    private final Map<String, String> links = new HashMap();

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/webmd/wbmdproffesionalauthentication/model/EnvironmentData$Companion;", "", "()V", "EMAIL_VALIDATION_URL", "", "LOGIN_URL", "PASSWORD_RESET_URL", "REGISTER_URL", "REGISTRATION_DATA_URL", "wbmdprofessionalauthenticaion_release"}, k = 1, mv = {1, 4, 0})
    /* compiled from: EnvironmentData.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public final Map<String, String> getLinks() {
        return this.links;
    }

    public final void getEnvironmentData() {
        this.links.put(LOGIN_URL, "https://api%s.medscape.com/authenticate/oauth/authenticate");
        this.links.put(REGISTER_URL, "https://api%s.medscape.com/servicegateway/v1/regservice/register");
        this.links.put(REGISTRATION_DATA_URL, "https://api%s.medscape.com/servicegateway/v1/regservice/getProfRefData");
        this.links.put(EMAIL_VALIDATION_URL, "https://profreg%s.medscape.com/px/validateEmail.do?emailAddress=");
        this.links.put(PASSWORD_RESET_URL, "https://api%s.medscape.com/servicegateway/v1/regservice/forgotpassword");
    }

    public final String getUrl(String str) {
        Boolean bool;
        if (this.links.get(str) == null) {
            return "";
        }
        String str2 = this.links.get(str);
        if (str2 != null) {
            bool = Boolean.valueOf(str2.length() == 0);
        } else {
            bool = null;
        }
        Intrinsics.checkNotNull(bool);
        if (!bool.booleanValue()) {
            return String.valueOf(this.links.get(str));
        }
        return "";
    }
}
