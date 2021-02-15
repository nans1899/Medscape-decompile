package com.wbmd.qxcalculator.model.api;

import android.text.TextUtils;
import android.util.Log;
import com.facebook.appevents.AppEventsConstants;
import com.wbmd.qxcalculator.CalculateModule;
import com.wbmd.qxcalculator.managers.FileHelper;
import com.wbmd.qxcalculator.managers.HttpClientManager;
import com.wbmd.qxcalculator.managers.UserManager;
import com.wbmd.qxcalculator.model.contentItems.common.PasswordReset;
import com.wbmd.qxcalculator.model.parsedObjects.App;
import com.wbmd.qxcalculator.model.parsedObjects.Device;
import com.wbmd.qxcalculator.model.parsedObjects.User;
import com.wbmd.qxcalculator.util.CrashLogger;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import net.bytebuddy.description.type.TypeDescription;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.Okio;
import org.joda.time.DateTimeConstants;

public class APIRequest {
    private static final String ACCOUNTS_ENDPOINT = (isStaging ? "https://staging.accounts.service.qxmd.com/v1/" : "https://accounts.service.qxmd.com/v1/");
    private static final String ACCOUNTS_HEADER_API_KEY = "x-qxmd-api-key";
    private static final String ACCOUNTS_HEADER_API_USER_ID_FETCH_VALUE = (isStaging ? "7f8f0ef8b5dc96d24ed342791d" : "f980931163f043b38362fc7751");
    private static final String ACCOUNTS_HEADER_API_VALUE;
    private static final String ACCOUNTS_HEADER_AUTH_TOKEN_KEY = "x-qxmd-auth-token";
    private static final String ACCOUNTS_HEADER_FEATURE_FLAG = "x-qxmd-feature-flag";
    private static final String API_ENDPOINT = (isStaging ? "https://api.staging.calculate.qxmd.com/" : "https://calculate.qxmd.com/");
    private static final String CONTENT_DISPOSITION_ZIP_FILE_DOWNLOAD_HEADER_KEY = "Content-Disposition";
    private static final String CONTENT_DISPOSITION_ZIP_FILE_DOWNLOAD_HEADER_VALUE = "attachment";
    private static final String LIST_QXMD_ENDPOINT = "https://list.qxmd.com/";
    private static final String OK_HTTP_TAG_ACCOUNTS = "OK_HTTP_TAG_ACCOUNTS";
    private static final String OK_HTTP_TAG_API_PARSER = "OK_HTTP_TAG_API_PARSER";
    private static final String OK_HTTP_TAG_REGISTRATION = "OK_HTTP_TAG_REGISTRATION";
    private static final String TAG = APIRequest.class.getSimpleName();
    private static boolean isStaging = false;
    private static final String kAPIActionAccounts = "account";
    private static final String kAPIActionAddFavorites = "account/add-favorites";
    private static final String kAPIActionAddRecents = "account/add-recents";
    private static final String kAPIActionFetchItems = "fetch-items";
    private static final String kAPIActionFetchResources = "fetch-files-updated";
    private static final String kAPIActionLocations = "locations?size=-1";
    private static final String kAPIActionLocationsForConsents = "locations";
    private static final String kAPIActionLoginUserWithAccounts = "users/login";
    private static final String kAPIActionLogoutUserWithAccounts = "logout";
    private static final String kAPIActionProfession = "professions?size=-1";
    private static final String kAPIActionRefreshAll = "refresh-all";
    private static final String kAPIActionRefreshAllV2 = "v2/refresh-all";
    private static final String kAPIActionRefreshCalculators = "calculators";
    private static final String kAPIActionRegisterDeviceToken = "add-token";
    private static final String kAPIActionRegisterUser = "user";
    private static final String kAPIActionRegisterUserWithAccounts = "users";
    private static final String kAPIActionRegisterUserWithAccountsSkipValidation = "users?skip_validate_consents=1";
    private static final String kAPIActionRemoveDeviceToken = "remove-token";
    private static final String kAPIActionRemoveFavorites = "account/remove-favorites";
    private static final String kAPIActionSendPasswordResetLinkAccounts = "users/password/forgot";
    private static final String kAPIActionSpecialties = "categories/1/specialties?size=-1";
    private static final String kAPIParameterLocale = "locale";
    private String accountsBody;
    private String apiAction;
    private AccountsApiKeyType apiKeyType = AccountsApiKeyType.DEFAULT;
    private boolean attachAuthKey;
    private boolean attachUserId;
    private final OkHttpClient client = HttpClientManager.getInstance().getHttpClient();
    private long customTimeout = 0;
    private ResponseDataType dataType;
    private HashMap<String, String> headerParams = new HashMap<>();
    private HttpType httpType;
    private boolean ignoreNullValuesInResponse;
    private ArrayList<String> postArrayParamsKeys;
    private ArrayList<String> postArrayParamsValues;
    private HashMap<String, String> postParams;
    private HashMap<String, String> queryItems = new HashMap<>();
    public RequestType type;

    private enum AccountsApiKeyType {
        DEFAULT,
        USER_ID_FETCH
    }

    private enum HttpType {
        POST,
        GET,
        PUT
    }

    public enum RequestType {
        CALCULATE_API,
        CALCULATE_API_RESOURCE_FETCH,
        CALCULATE_ACCOUNT,
        LIST_QXMD,
        ACCOUNTS
    }

    private enum ResponseDataType {
        NONE,
        USER,
        PASSWORD_RESET,
        PROFESSION,
        SPECIALTY,
        LOCATION
    }

    static {
        boolean z = CalculateModule.isDebug;
        isStaging = z;
        ACCOUNTS_HEADER_API_VALUE = z ? "5fa8aa9ef38aa72efe824f55404e16887f87cef722c0d3c6f571" : "c597c938dcbe785f0feccc510d";
    }

    public APIRequest() {
    }

    public APIRequest(String str) {
        this.apiAction = str;
    }

    public static APIRequest registerUserWithAccounts(User user, boolean z) {
        APIRequest aPIRequest = new APIRequest(z ? kAPIActionRegisterUserWithAccountsSkipValidation : kAPIActionRegisterUserWithAccounts);
        aPIRequest.type = RequestType.ACCOUNTS;
        aPIRequest.dataType = ResponseDataType.USER;
        user.app = new App();
        user.device = new Device();
        user.device.identifier = UserManager.getInstance().getDeviceId();
        user.lastUpdated = null;
        aPIRequest.accountsBody = user.convertToJsonApiString();
        aPIRequest.httpType = HttpType.POST;
        aPIRequest.headerParams.put(ACCOUNTS_HEADER_FEATURE_FLAG, "consents");
        return aPIRequest;
    }

    public static APIRequest loginUserWithAccounts(String str, String str2, String str3, String str4, Long l) {
        APIRequest aPIRequest = new APIRequest(kAPIActionLoginUserWithAccounts);
        aPIRequest.type = RequestType.ACCOUNTS;
        aPIRequest.dataType = ResponseDataType.USER;
        User user = new User();
        user.email = str;
        user.password = str2;
        user.facebookAccessToken = str3;
        user.facebookId = str4;
        user.lastUpdated = null;
        user.app = new App();
        user.device = new Device();
        user.device.identifier = UserManager.getInstance().getDeviceId();
        aPIRequest.accountsBody = user.convertToJsonApiString();
        aPIRequest.httpType = HttpType.POST;
        aPIRequest.headerParams.put(ACCOUNTS_HEADER_FEATURE_FLAG, "consents");
        return aPIRequest;
    }

    public static APIRequest logoutUserWithAccounts(String str) {
        APIRequest aPIRequest = new APIRequest("users/" + str + "/" + kAPIActionLogoutUserWithAccounts);
        aPIRequest.type = RequestType.ACCOUNTS;
        aPIRequest.dataType = ResponseDataType.NONE;
        aPIRequest.httpType = HttpType.GET;
        return aPIRequest;
    }

    public static APIRequest sendResetEmailLinkRequest(String str) {
        APIRequest aPIRequest = new APIRequest(kAPIActionSendPasswordResetLinkAccounts);
        aPIRequest.type = RequestType.ACCOUNTS;
        aPIRequest.dataType = ResponseDataType.PASSWORD_RESET;
        PasswordReset passwordReset = new PasswordReset();
        passwordReset.email = str;
        aPIRequest.accountsBody = passwordReset.convertToJsonApiString();
        aPIRequest.httpType = HttpType.POST;
        return aPIRequest;
    }

    public static APIRequest updateUserWithAccounts(User user) {
        APIRequest aPIRequest = new APIRequest("users/" + user.identifier);
        aPIRequest.type = RequestType.ACCOUNTS;
        aPIRequest.dataType = ResponseDataType.USER;
        aPIRequest.httpType = HttpType.PUT;
        aPIRequest.accountsBody = user.convertToJsonApiString();
        aPIRequest.setLocale();
        aPIRequest.headerParams.put(ACCOUNTS_HEADER_FEATURE_FLAG, "consents");
        return aPIRequest;
    }

    public static APIRequest initFetchUserId(String str) {
        if (str == null || str.isEmpty()) {
            return null;
        }
        String md5HashEmail = md5HashEmail(str.toLowerCase());
        APIRequest aPIRequest = new APIRequest("users/identify/" + md5HashEmail);
        aPIRequest.type = RequestType.ACCOUNTS;
        aPIRequest.dataType = ResponseDataType.USER;
        aPIRequest.httpType = HttpType.GET;
        aPIRequest.apiKeyType = AccountsApiKeyType.USER_ID_FETCH;
        return aPIRequest;
    }

    public static String md5HashEmail(String str) {
        try {
            byte[] digest = MessageDigest.getInstance("MD5").digest(str.getBytes("UTF-8"));
            StringBuffer stringBuffer = new StringBuffer();
            for (byte b : digest) {
                stringBuffer.append(Integer.toHexString((b & 255) | 256).substring(1, 3));
            }
            return stringBuffer.toString();
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException unused) {
            return null;
        }
    }

    public static APIRequest initDoesCalculateUniversalAccountExist(Long l) {
        APIRequest aPIRequest = new APIRequest("account/" + l);
        aPIRequest.type = RequestType.CALCULATE_API;
        aPIRequest.httpType = HttpType.GET;
        aPIRequest.attachUserId = false;
        return aPIRequest;
    }

    public static APIRequest initSyncWithCalculateAccount() {
        APIRequest aPIRequest = new APIRequest(kAPIActionAccounts);
        aPIRequest.type = RequestType.CALCULATE_ACCOUNT;
        aPIRequest.httpType = HttpType.POST;
        aPIRequest.attachUserId = true;
        aPIRequest.attachAuthKey = true;
        return aPIRequest;
    }

    public void setDefaultUnits(String str) {
        addPostParam("default_units", str);
    }

    public void setShowItemDescriptions(boolean z) {
        addPostParam("show_item_descriptions", z ? AppEventsConstants.EVENT_PARAM_VALUE_YES : AppEventsConstants.EVENT_PARAM_VALUE_NO);
    }

    public void setAutoOpenFirstQuestion(boolean z) {
        addPostParam("auto_open_first_question", z ? AppEventsConstants.EVENT_PARAM_VALUE_YES : AppEventsConstants.EVENT_PARAM_VALUE_NO);
    }

    public void setPnEnabled(boolean z) {
        addPostParam("pn_enabled", z ? AppEventsConstants.EVENT_PARAM_VALUE_YES : AppEventsConstants.EVENT_PARAM_VALUE_NO);
    }

    public void setDebugType(String str) {
        addPostParam("debug_type", str);
    }

    public void setMinifyEnabled(boolean z) {
        addPostParam("minify", z ? AppEventsConstants.EVENT_PARAM_VALUE_YES : AppEventsConstants.EVENT_PARAM_VALUE_NO);
    }

    public void setLocationId(Long l) {
        if (l != null) {
            addPostParam("user_location_id", l.toString());
        }
    }

    public void setProfessionId(Long l) {
        if (l != null) {
            addPostParam("user_profession_id", l.toString());
        }
    }

    public void setSpecialtyId(Long l) {
        if (l != null) {
            addPostParam("user_specialty_id", l.toString());
        }
    }

    public static APIRequest refreshAll() {
        if (!UserManager.getInstance().hasFinishedUpgradingToUniversalAccounts()) {
            APIRequest aPIRequest = new APIRequest(kAPIActionRefreshAll);
            aPIRequest.type = RequestType.CALCULATE_API;
            aPIRequest.customTimeout = 2500;
            aPIRequest.httpType = HttpType.POST;
            return aPIRequest;
        }
        APIRequest aPIRequest2 = new APIRequest(kAPIActionRefreshAllV2);
        aPIRequest2.type = RequestType.CALCULATE_API;
        aPIRequest2.httpType = HttpType.POST;
        aPIRequest2.attachAuthKey = true;
        aPIRequest2.attachUserId = true;
        aPIRequest2.customTimeout = 10000;
        return aPIRequest2;
    }

    public static APIRequest refreshCalculators() {
        APIRequest aPIRequest = new APIRequest(kAPIActionRefreshCalculators);
        aPIRequest.type = RequestType.CALCULATE_API;
        aPIRequest.httpType = HttpType.POST;
        return aPIRequest;
    }

    public static APIRequest fetchItems() {
        APIRequest aPIRequest = new APIRequest(kAPIActionFetchItems);
        aPIRequest.ignoreNullValuesInResponse = true;
        aPIRequest.type = RequestType.CALCULATE_API;
        aPIRequest.httpType = HttpType.POST;
        aPIRequest.customTimeout = 30000;
        return aPIRequest;
    }

    public static APIRequest fetchResources() {
        APIRequest aPIRequest = new APIRequest(kAPIActionFetchResources);
        aPIRequest.type = RequestType.CALCULATE_API_RESOURCE_FETCH;
        aPIRequest.httpType = HttpType.POST;
        aPIRequest.customTimeout = 30000;
        return aPIRequest;
    }

    public void addItemToFetch(String str) {
        addPostArrayParam("items[]", str);
    }

    public void addItemToFetchResources(String str, String str2) {
        addPostArrayParam("items[" + str + "]", str2);
    }

    public static APIRequest initAddRecents() {
        APIRequest aPIRequest = new APIRequest(kAPIActionAddRecents);
        aPIRequest.type = RequestType.CALCULATE_ACCOUNT;
        aPIRequest.httpType = HttpType.POST;
        aPIRequest.attachUserId = true;
        aPIRequest.attachAuthKey = true;
        return aPIRequest;
    }

    public static APIRequest initAddFavorites() {
        APIRequest aPIRequest = new APIRequest(kAPIActionAddFavorites);
        aPIRequest.type = RequestType.CALCULATE_ACCOUNT;
        aPIRequest.httpType = HttpType.POST;
        aPIRequest.attachUserId = true;
        aPIRequest.attachAuthKey = true;
        return aPIRequest;
    }

    public static APIRequest initRemoveFavorites() {
        APIRequest aPIRequest = new APIRequest(kAPIActionRemoveFavorites);
        aPIRequest.type = RequestType.CALCULATE_ACCOUNT;
        aPIRequest.httpType = HttpType.POST;
        aPIRequest.attachUserId = true;
        aPIRequest.attachAuthKey = true;
        return aPIRequest;
    }

    public void addEmptyRecentList() {
        addPostArrayParam("recents[]", "");
    }

    public void addEmptyFavoriteList() {
        addPostArrayParam("favorites[]", "");
    }

    public void addRecent(String str, Date date) {
        if (str != null) {
            addPostArrayParam("recents[" + str + "]", Long.toString(date.getTime() / 1000));
        }
    }

    public void addFavorite(String str, Date date) {
        if (str != null) {
            addPostArrayParam("favorites[" + str + "]", Long.toString(date.getTime() / 1000));
        }
    }

    public void removeFavorite(String str) {
        if (str != null) {
            addPostArrayParam("favorites[]", str);
        }
    }

    public static APIRequest fetchProfessions() {
        APIRequest aPIRequest = new APIRequest(kAPIActionProfession);
        aPIRequest.type = RequestType.ACCOUNTS;
        aPIRequest.dataType = ResponseDataType.PROFESSION;
        aPIRequest.setLocale();
        return aPIRequest;
    }

    public static APIRequest fetchSpecialties() {
        APIRequest aPIRequest = new APIRequest(kAPIActionSpecialties);
        aPIRequest.type = RequestType.ACCOUNTS;
        aPIRequest.dataType = ResponseDataType.SPECIALTY;
        aPIRequest.setLocale();
        return aPIRequest;
    }

    public static APIRequest fetchLocations() {
        APIRequest aPIRequest = new APIRequest(kAPIActionLocations);
        aPIRequest.type = RequestType.ACCOUNTS;
        aPIRequest.dataType = ResponseDataType.LOCATION;
        aPIRequest.setLocale();
        return aPIRequest;
    }

    public static APIRequest fetchConsentsForLocation(Long l) {
        APIRequest aPIRequest = new APIRequest("locations/" + l.toString());
        aPIRequest.type = RequestType.ACCOUNTS;
        aPIRequest.dataType = ResponseDataType.LOCATION;
        aPIRequest.headerParams.put(ACCOUNTS_HEADER_FEATURE_FLAG, "consents");
        return aPIRequest;
    }

    public void setAccountId(Long l) {
        addPostParam("account_id", Long.toString(l.longValue()));
    }

    public void setAccountToken(String str) {
        addPostParam("account_token", str);
    }

    public static APIRequest registerDeviceIdToken(String str, String str2) {
        try {
            str2 = URLEncoder.encode(str2, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            CrashLogger.getInstance().leaveBreadcrumb("error encoding email for device token");
            CrashLogger.getInstance().logHandledException(e);
        }
        APIRequest aPIRequest = new APIRequest("add-token/" + str2);
        aPIRequest.type = RequestType.LIST_QXMD;
        aPIRequest.httpType = HttpType.POST;
        aPIRequest.addPostParam("token", str);
        return aPIRequest;
    }

    public static APIRequest removeDeviceIdToken(String str) {
        try {
            str = URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            CrashLogger.getInstance().leaveBreadcrumb("error encoding email for removing device token");
            CrashLogger.getInstance().logHandledException(e);
        }
        APIRequest aPIRequest = new APIRequest("remove-token/" + str);
        aPIRequest.type = RequestType.LIST_QXMD;
        aPIRequest.httpType = HttpType.POST;
        return aPIRequest;
    }

    public static APIRequest registerUser() {
        APIRequest aPIRequest = new APIRequest(kAPIActionRegisterUser);
        aPIRequest.type = RequestType.LIST_QXMD;
        aPIRequest.httpType = HttpType.POST;
        return aPIRequest;
    }

    public static APIRequest updateUser(String str) {
        try {
            str = URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            CrashLogger.getInstance().leaveBreadcrumb("error updating user");
            CrashLogger.getInstance().logHandledException(e);
        }
        APIRequest aPIRequest = new APIRequest("user/" + str);
        aPIRequest.type = RequestType.LIST_QXMD;
        aPIRequest.httpType = HttpType.POST;
        return aPIRequest;
    }

    public void setRegistrationOldEmail(String str) {
        if (str != null) {
            try {
                str = URLEncoder.encode(str, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                CrashLogger.getInstance().leaveBreadcrumb("error encoding old email");
                CrashLogger.getInstance().logHandledException(e);
            }
            addPostParam("email", str);
        }
    }

    public void setRegistrationUpdatedEmail(String str) {
        if (str != null) {
            try {
                str = URLEncoder.encode(str, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                CrashLogger.getInstance().leaveBreadcrumb("error encoding updated email");
                CrashLogger.getInstance().logHandledException(e);
            }
            addPostParam("user_new_email", str);
        }
    }

    public void setRegistrationEmail(String str) {
        if (str != null) {
            try {
                str = URLEncoder.encode(str, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                CrashLogger.getInstance().leaveBreadcrumb("error encoding registration email");
                CrashLogger.getInstance().logHandledException(e);
            }
            addPostParam("user_email", str);
        }
    }

    public void setRegistrationFirstName(String str) {
        if (str != null) {
            addPostParam("user_first_name", str);
        }
    }

    public void setRegistrationLastName(String str) {
        if (str != null) {
            addPostParam("user_last_name", str);
        }
    }

    public void setRegistrationLocation(String str) {
        if (str != null) {
            addPostParam("user_country", str);
        }
    }

    public void setRegistrationZip(String str) {
        if (str != null) {
            addPostParam("user_zip_code", str);
        }
    }

    public void setRegistrationProfession(String str) {
        if (str != null) {
            addPostParam("user_profession", str);
        }
    }

    public void setRegistrationSpecialty(String str) {
        if (str != null) {
            addPostParam("user_specialty", str);
        }
    }

    public void setRegistrationAppId(String str) {
        if (str != null) {
            addPostParam("app_id", str);
        }
    }

    public void setRegistrationAppVersion(String str) {
        if (str != null) {
            addPostParam("app_version", str);
        }
    }

    public void setRegistrationOsVersion(String str) {
        if (str != null) {
            addPostParam("os_version", str);
        }
    }

    public void setRegistrationPlatform(String str) {
        if (str != null) {
            addPostParam("platform_name", str);
        }
    }

    public void setHasUpdatedToUniversalAccounts(boolean z) {
        addPostParam("has_universal_account", z ? AppEventsConstants.EVENT_PARAM_VALUE_YES : AppEventsConstants.EVENT_PARAM_VALUE_NO);
    }

    public void setTimezoneOffset() {
        Integer valueOf = Integer.valueOf(TimeZone.getDefault().getRawOffset() / DateTimeConstants.MILLIS_PER_HOUR);
        String str = TAG;
        Log.d(str, "timezone offset " + valueOf);
        addPostParam("timezone_offset", valueOf.toString());
    }

    public void setEmail(String str) {
        if (str != null) {
            addPostParam("user_email", str);
        }
    }

    private void setLocale() {
        addQueryItems(kAPIParameterLocale, Locale.getDefault().getLanguage() + "-" + Locale.getDefault().getCountry());
    }

    private void addQueryItems(String str, String str2) {
        if (str != null && !str.isEmpty() && str2 != null && !str2.isEmpty()) {
            this.queryItems.put(str, str2);
        }
    }

    public void setLongRefreshTimeout() {
        this.customTimeout = 15000;
    }

    private void addPostParam(String str, String str2) {
        if (this.postParams == null) {
            this.postParams = new HashMap<>();
        }
        this.postParams.put(str, str2);
    }

    public void addPostArrayParam(String str, String str2) {
        if (this.postArrayParamsKeys == null) {
            this.postArrayParamsKeys = new ArrayList<>();
            this.postArrayParamsValues = new ArrayList<>();
        }
        this.postArrayParamsKeys.add(str);
        this.postArrayParamsValues.add(str2);
    }

    private String actionQueryString() {
        StringBuilder sb = new StringBuilder(this.apiAction);
        String urlEncodedQueryString = urlEncodedQueryString(this.queryItems);
        if (urlEncodedQueryString.length() > 0) {
            if (!sb.toString().contains(TypeDescription.Generic.OfWildcardType.SYMBOL)) {
                sb.append(TypeDescription.Generic.OfWildcardType.SYMBOL);
                sb.append(urlEncodedQueryString);
            } else {
                sb.append("&");
                sb.append(urlEncodedQueryString);
            }
        }
        return sb.toString();
    }

    public String urlEncodedQueryString(HashMap<String, String> hashMap) {
        ArrayList arrayList = new ArrayList();
        for (Map.Entry next : hashMap.entrySet()) {
            arrayList.add(((String) next.getKey()) + "=" + ((String) next.getValue()));
        }
        return TextUtils.join("&", arrayList);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:125:0x0335, code lost:
        r7 = -1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:130:0x0340, code lost:
        r8 = -1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:182:0x04d7, code lost:
        r4 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:200:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:202:0x0571, code lost:
        r1 = r3.body();
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:139:0x037d A[Catch:{ SocketTimeoutException -> 0x04d7, Exception -> 0x04d5 }] */
    /* JADX WARNING: Removed duplicated region for block: B:176:0x04c7 A[SYNTHETIC, Splitter:B:176:0x04c7] */
    /* JADX WARNING: Removed duplicated region for block: B:179:0x04cc A[Catch:{ Exception -> 0x057e }] */
    /* JADX WARNING: Removed duplicated region for block: B:182:0x04d7 A[ExcHandler: SocketTimeoutException (e java.net.SocketTimeoutException), PHI: r1 
      PHI: (r1v10 java.io.InputStream) = (r1v0 java.io.InputStream), (r1v14 java.io.InputStream), (r1v14 java.io.InputStream), (r1v14 java.io.InputStream), (r1v14 java.io.InputStream) binds: [B:88:0x0240, B:111:0x02ea, B:123:0x032c, B:127:0x0336, B:132:0x0341] A[DONT_GENERATE, DONT_INLINE], Splitter:B:88:0x0240] */
    /* JADX WARNING: Removed duplicated region for block: B:190:0x0511 A[SYNTHETIC, Splitter:B:190:0x0511] */
    /* JADX WARNING: Removed duplicated region for block: B:193:0x0516 A[Catch:{ Exception -> 0x057e }] */
    /* JADX WARNING: Removed duplicated region for block: B:199:0x056c A[SYNTHETIC, Splitter:B:199:0x056c] */
    /* JADX WARNING: Removed duplicated region for block: B:202:0x0571 A[Catch:{ Exception -> 0x057e }] */
    /* JADX WARNING: Removed duplicated region for block: B:205:0x057a A[Catch:{ Exception -> 0x057e }] */
    /* JADX WARNING: Removed duplicated region for block: B:209:0x0582 A[Catch:{ Exception -> 0x057e }] */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x01da A[Catch:{ SocketTimeoutException -> 0x051b, Exception -> 0x04dd, all -> 0x04d9 }] */
    /* JADX WARNING: Removed duplicated region for block: B:86:0x0213 A[Catch:{ SocketTimeoutException -> 0x051b, Exception -> 0x04dd, all -> 0x04d9 }] */
    /* JADX WARNING: Removed duplicated region for block: B:87:0x021e A[Catch:{ SocketTimeoutException -> 0x051b, Exception -> 0x04dd, all -> 0x04d9 }] */
    /* JADX WARNING: Removed duplicated region for block: B:91:0x0298 A[Catch:{ SocketTimeoutException -> 0x04d7, Exception -> 0x04d5 }] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:187:0x04e0=Splitter:B:187:0x04e0, B:196:0x051e=Splitter:B:196:0x051e} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.wbmd.qxcalculator.model.api.APIResponse send() {
        /*
            r13 = this;
            com.wbmd.qxcalculator.model.api.APIResponse r0 = new com.wbmd.qxcalculator.model.api.APIResponse
            r0.<init>()
            r1 = 0
            r2 = 1
            com.wbmd.qxcalculator.model.api.APIRequest$RequestType r3 = r13.type     // Catch:{ SocketTimeoutException -> 0x051b, Exception -> 0x04dd, all -> 0x04d9 }
            com.wbmd.qxcalculator.model.api.APIRequest$RequestType r4 = com.wbmd.qxcalculator.model.api.APIRequest.RequestType.ACCOUNTS     // Catch:{ SocketTimeoutException -> 0x051b, Exception -> 0x04dd, all -> 0x04d9 }
            java.lang.String r5 = "OK_HTTP_TAG_API_PARSER"
            if (r3 != r4) goto L_0x0014
            java.lang.String r3 = ACCOUNTS_ENDPOINT     // Catch:{ SocketTimeoutException -> 0x051b, Exception -> 0x04dd, all -> 0x04d9 }
            java.lang.String r5 = "OK_HTTP_TAG_ACCOUNTS"
            goto L_0x002a
        L_0x0014:
            com.wbmd.qxcalculator.model.api.APIRequest$RequestType r3 = r13.type     // Catch:{ SocketTimeoutException -> 0x051b, Exception -> 0x04dd, all -> 0x04d9 }
            com.wbmd.qxcalculator.model.api.APIRequest$RequestType r4 = com.wbmd.qxcalculator.model.api.APIRequest.RequestType.CALCULATE_API_RESOURCE_FETCH     // Catch:{ SocketTimeoutException -> 0x051b, Exception -> 0x04dd, all -> 0x04d9 }
            if (r3 != r4) goto L_0x001d
            java.lang.String r3 = API_ENDPOINT     // Catch:{ SocketTimeoutException -> 0x051b, Exception -> 0x04dd, all -> 0x04d9 }
            goto L_0x002a
        L_0x001d:
            com.wbmd.qxcalculator.model.api.APIRequest$RequestType r3 = r13.type     // Catch:{ SocketTimeoutException -> 0x051b, Exception -> 0x04dd, all -> 0x04d9 }
            com.wbmd.qxcalculator.model.api.APIRequest$RequestType r4 = com.wbmd.qxcalculator.model.api.APIRequest.RequestType.LIST_QXMD     // Catch:{ SocketTimeoutException -> 0x051b, Exception -> 0x04dd, all -> 0x04d9 }
            if (r3 != r4) goto L_0x0028
            java.lang.String r3 = "https://list.qxmd.com/"
            java.lang.String r5 = "OK_HTTP_TAG_REGISTRATION"
            goto L_0x002a
        L_0x0028:
            java.lang.String r3 = API_ENDPOINT     // Catch:{ SocketTimeoutException -> 0x051b, Exception -> 0x04dd, all -> 0x04d9 }
        L_0x002a:
            java.net.URL r4 = new java.net.URL     // Catch:{ SocketTimeoutException -> 0x051b, Exception -> 0x04dd, all -> 0x04d9 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ SocketTimeoutException -> 0x051b, Exception -> 0x04dd, all -> 0x04d9 }
            r6.<init>()     // Catch:{ SocketTimeoutException -> 0x051b, Exception -> 0x04dd, all -> 0x04d9 }
            r6.append(r3)     // Catch:{ SocketTimeoutException -> 0x051b, Exception -> 0x04dd, all -> 0x04d9 }
            java.lang.String r3 = r13.actionQueryString()     // Catch:{ SocketTimeoutException -> 0x051b, Exception -> 0x04dd, all -> 0x04d9 }
            r6.append(r3)     // Catch:{ SocketTimeoutException -> 0x051b, Exception -> 0x04dd, all -> 0x04d9 }
            java.lang.String r3 = r6.toString()     // Catch:{ SocketTimeoutException -> 0x051b, Exception -> 0x04dd, all -> 0x04d9 }
            r4.<init>(r3)     // Catch:{ SocketTimeoutException -> 0x051b, Exception -> 0x04dd, all -> 0x04d9 }
            okhttp3.Request$Builder r3 = new okhttp3.Request$Builder     // Catch:{ SocketTimeoutException -> 0x051b, Exception -> 0x04dd, all -> 0x04d9 }
            r3.<init>()     // Catch:{ SocketTimeoutException -> 0x051b, Exception -> 0x04dd, all -> 0x04d9 }
            com.wbmd.qxcalculator.model.api.APIRequest$RequestType r6 = r13.type     // Catch:{ SocketTimeoutException -> 0x051b, Exception -> 0x04dd, all -> 0x04d9 }
            com.wbmd.qxcalculator.model.api.APIRequest$RequestType r7 = com.wbmd.qxcalculator.model.api.APIRequest.RequestType.ACCOUNTS     // Catch:{ SocketTimeoutException -> 0x051b, Exception -> 0x04dd, all -> 0x04d9 }
            r8 = 0
            if (r6 != r7) goto L_0x00e8
            com.wbmd.qxcalculator.model.api.APIRequest$AccountsApiKeyType r6 = r13.apiKeyType     // Catch:{ SocketTimeoutException -> 0x051b, Exception -> 0x04dd, all -> 0x04d9 }
            com.wbmd.qxcalculator.model.api.APIRequest$AccountsApiKeyType r7 = com.wbmd.qxcalculator.model.api.APIRequest.AccountsApiKeyType.USER_ID_FETCH     // Catch:{ SocketTimeoutException -> 0x051b, Exception -> 0x04dd, all -> 0x04d9 }
            java.lang.String r9 = "x-qxmd-api-key"
            if (r6 != r7) goto L_0x005c
            java.lang.String r6 = ACCOUNTS_HEADER_API_USER_ID_FETCH_VALUE     // Catch:{ SocketTimeoutException -> 0x051b, Exception -> 0x04dd, all -> 0x04d9 }
            r3.addHeader(r9, r6)     // Catch:{ SocketTimeoutException -> 0x051b, Exception -> 0x04dd, all -> 0x04d9 }
            goto L_0x0061
        L_0x005c:
            java.lang.String r6 = ACCOUNTS_HEADER_API_VALUE     // Catch:{ SocketTimeoutException -> 0x051b, Exception -> 0x04dd, all -> 0x04d9 }
            r3.addHeader(r9, r6)     // Catch:{ SocketTimeoutException -> 0x051b, Exception -> 0x04dd, all -> 0x04d9 }
        L_0x0061:
            com.wbmd.qxcalculator.managers.UserManager r6 = com.wbmd.qxcalculator.managers.UserManager.getInstance()     // Catch:{ SocketTimeoutException -> 0x051b, Exception -> 0x04dd, all -> 0x04d9 }
            java.lang.String r6 = r6.getAuthKey()     // Catch:{ SocketTimeoutException -> 0x051b, Exception -> 0x04dd, all -> 0x04d9 }
            if (r6 == 0) goto L_0x0078
            com.wbmd.qxcalculator.managers.UserManager r6 = com.wbmd.qxcalculator.managers.UserManager.getInstance()     // Catch:{ SocketTimeoutException -> 0x051b, Exception -> 0x04dd, all -> 0x04d9 }
            java.lang.String r6 = r6.getAuthKey()     // Catch:{ SocketTimeoutException -> 0x051b, Exception -> 0x04dd, all -> 0x04d9 }
            java.lang.String r7 = "x-qxmd-auth-token"
            r3.addHeader(r7, r6)     // Catch:{ SocketTimeoutException -> 0x051b, Exception -> 0x04dd, all -> 0x04d9 }
        L_0x0078:
            java.util.HashMap<java.lang.String, java.lang.String> r6 = r13.headerParams     // Catch:{ SocketTimeoutException -> 0x051b, Exception -> 0x04dd, all -> 0x04d9 }
            java.util.Set r6 = r6.entrySet()     // Catch:{ SocketTimeoutException -> 0x051b, Exception -> 0x04dd, all -> 0x04d9 }
            java.util.Iterator r6 = r6.iterator()     // Catch:{ SocketTimeoutException -> 0x051b, Exception -> 0x04dd, all -> 0x04d9 }
            r7 = 0
        L_0x0083:
            boolean r9 = r6.hasNext()     // Catch:{ SocketTimeoutException -> 0x051b, Exception -> 0x04dd, all -> 0x04d9 }
            java.lang.String r10 = "x-qxmd-feature-flag"
            if (r9 == 0) goto L_0x00cf
            java.lang.Object r9 = r6.next()     // Catch:{ SocketTimeoutException -> 0x051b, Exception -> 0x04dd, all -> 0x04d9 }
            java.util.Map$Entry r9 = (java.util.Map.Entry) r9     // Catch:{ SocketTimeoutException -> 0x051b, Exception -> 0x04dd, all -> 0x04d9 }
            java.lang.Object r11 = r9.getKey()     // Catch:{ SocketTimeoutException -> 0x051b, Exception -> 0x04dd, all -> 0x04d9 }
            java.lang.String r11 = (java.lang.String) r11     // Catch:{ SocketTimeoutException -> 0x051b, Exception -> 0x04dd, all -> 0x04d9 }
            boolean r10 = r11.equalsIgnoreCase(r10)     // Catch:{ SocketTimeoutException -> 0x051b, Exception -> 0x04dd, all -> 0x04d9 }
            if (r10 == 0) goto L_0x00bf
            java.lang.Object r7 = r9.getKey()     // Catch:{ SocketTimeoutException -> 0x051b, Exception -> 0x04dd, all -> 0x04d9 }
            java.lang.String r7 = (java.lang.String) r7     // Catch:{ SocketTimeoutException -> 0x051b, Exception -> 0x04dd, all -> 0x04d9 }
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ SocketTimeoutException -> 0x051b, Exception -> 0x04dd, all -> 0x04d9 }
            r10.<init>()     // Catch:{ SocketTimeoutException -> 0x051b, Exception -> 0x04dd, all -> 0x04d9 }
            java.lang.Object r9 = r9.getValue()     // Catch:{ SocketTimeoutException -> 0x051b, Exception -> 0x04dd, all -> 0x04d9 }
            java.lang.String r9 = (java.lang.String) r9     // Catch:{ SocketTimeoutException -> 0x051b, Exception -> 0x04dd, all -> 0x04d9 }
            r10.append(r9)     // Catch:{ SocketTimeoutException -> 0x051b, Exception -> 0x04dd, all -> 0x04d9 }
            java.lang.String r9 = ",v2relationships"
            r10.append(r9)     // Catch:{ SocketTimeoutException -> 0x051b, Exception -> 0x04dd, all -> 0x04d9 }
            java.lang.String r9 = r10.toString()     // Catch:{ SocketTimeoutException -> 0x051b, Exception -> 0x04dd, all -> 0x04d9 }
            r3.addHeader(r7, r9)     // Catch:{ SocketTimeoutException -> 0x051b, Exception -> 0x04dd, all -> 0x04d9 }
            r7 = 1
            goto L_0x0083
        L_0x00bf:
            java.lang.Object r10 = r9.getKey()     // Catch:{ SocketTimeoutException -> 0x051b, Exception -> 0x04dd, all -> 0x04d9 }
            java.lang.String r10 = (java.lang.String) r10     // Catch:{ SocketTimeoutException -> 0x051b, Exception -> 0x04dd, all -> 0x04d9 }
            java.lang.Object r9 = r9.getValue()     // Catch:{ SocketTimeoutException -> 0x051b, Exception -> 0x04dd, all -> 0x04d9 }
            java.lang.String r9 = (java.lang.String) r9     // Catch:{ SocketTimeoutException -> 0x051b, Exception -> 0x04dd, all -> 0x04d9 }
            r3.addHeader(r10, r9)     // Catch:{ SocketTimeoutException -> 0x051b, Exception -> 0x04dd, all -> 0x04d9 }
            goto L_0x0083
        L_0x00cf:
            if (r7 != 0) goto L_0x00d6
            java.lang.String r6 = "v2relationships"
            r3.addHeader(r10, r6)     // Catch:{ SocketTimeoutException -> 0x051b, Exception -> 0x04dd, all -> 0x04d9 }
        L_0x00d6:
            java.lang.String r6 = r13.accountsBody     // Catch:{ SocketTimeoutException -> 0x051b, Exception -> 0x04dd, all -> 0x04d9 }
            if (r6 == 0) goto L_0x0195
            java.lang.String r6 = "application/json; charset=utf-8"
            okhttp3.MediaType r6 = okhttp3.MediaType.parse(r6)     // Catch:{ SocketTimeoutException -> 0x051b, Exception -> 0x04dd, all -> 0x04d9 }
            java.lang.String r7 = r13.accountsBody     // Catch:{ SocketTimeoutException -> 0x051b, Exception -> 0x04dd, all -> 0x04d9 }
            okhttp3.RequestBody r6 = okhttp3.RequestBody.create((okhttp3.MediaType) r6, (java.lang.String) r7)     // Catch:{ SocketTimeoutException -> 0x051b, Exception -> 0x04dd, all -> 0x04d9 }
            goto L_0x0196
        L_0x00e8:
            com.wbmd.qxcalculator.model.api.APIRequest$RequestType r6 = r13.type     // Catch:{ SocketTimeoutException -> 0x051b, Exception -> 0x04dd, all -> 0x04d9 }
            com.wbmd.qxcalculator.model.api.APIRequest$RequestType r7 = com.wbmd.qxcalculator.model.api.APIRequest.RequestType.CALCULATE_API     // Catch:{ SocketTimeoutException -> 0x051b, Exception -> 0x04dd, all -> 0x04d9 }
            if (r6 == r7) goto L_0x00f4
            com.wbmd.qxcalculator.model.api.APIRequest$RequestType r6 = r13.type     // Catch:{ SocketTimeoutException -> 0x051b, Exception -> 0x04dd, all -> 0x04d9 }
            com.wbmd.qxcalculator.model.api.APIRequest$RequestType r7 = com.wbmd.qxcalculator.model.api.APIRequest.RequestType.CALCULATE_ACCOUNT     // Catch:{ SocketTimeoutException -> 0x051b, Exception -> 0x04dd, all -> 0x04d9 }
            if (r6 != r7) goto L_0x0124
        L_0x00f4:
            com.wbmd.qxcalculator.managers.UserManager r6 = com.wbmd.qxcalculator.managers.UserManager.getInstance()     // Catch:{ SocketTimeoutException -> 0x051b, Exception -> 0x04dd, all -> 0x04d9 }
            java.lang.String r6 = r6.getAuthKey()     // Catch:{ SocketTimeoutException -> 0x051b, Exception -> 0x04dd, all -> 0x04d9 }
            com.wbmd.qxcalculator.managers.UserManager r7 = com.wbmd.qxcalculator.managers.UserManager.getInstance()     // Catch:{ SocketTimeoutException -> 0x051b, Exception -> 0x04dd, all -> 0x04d9 }
            boolean r7 = r7.needsUpgradeToUniversalAccounts()     // Catch:{ SocketTimeoutException -> 0x051b, Exception -> 0x04dd, all -> 0x04d9 }
            if (r7 != 0) goto L_0x0115
            boolean r7 = r13.attachUserId     // Catch:{ SocketTimeoutException -> 0x051b, Exception -> 0x04dd, all -> 0x04d9 }
            if (r7 == 0) goto L_0x0115
            com.wbmd.qxcalculator.managers.UserManager r7 = com.wbmd.qxcalculator.managers.UserManager.getInstance()     // Catch:{ SocketTimeoutException -> 0x051b, Exception -> 0x04dd, all -> 0x04d9 }
            java.lang.Long r7 = r7.getActiveUserId()     // Catch:{ SocketTimeoutException -> 0x051b, Exception -> 0x04dd, all -> 0x04d9 }
            r13.setAccountId(r7)     // Catch:{ SocketTimeoutException -> 0x051b, Exception -> 0x04dd, all -> 0x04d9 }
        L_0x0115:
            boolean r7 = r13.attachAuthKey     // Catch:{ SocketTimeoutException -> 0x051b, Exception -> 0x04dd, all -> 0x04d9 }
            if (r7 == 0) goto L_0x0124
            if (r6 == 0) goto L_0x0124
            boolean r7 = r6.isEmpty()     // Catch:{ SocketTimeoutException -> 0x051b, Exception -> 0x04dd, all -> 0x04d9 }
            if (r7 != 0) goto L_0x0124
            r13.setAccountToken(r6)     // Catch:{ SocketTimeoutException -> 0x051b, Exception -> 0x04dd, all -> 0x04d9 }
        L_0x0124:
            java.util.HashMap<java.lang.String, java.lang.String> r6 = r13.postParams     // Catch:{ SocketTimeoutException -> 0x051b, Exception -> 0x04dd, all -> 0x04d9 }
            if (r6 == 0) goto L_0x015b
            java.util.HashMap<java.lang.String, java.lang.String> r6 = r13.postParams     // Catch:{ SocketTimeoutException -> 0x051b, Exception -> 0x04dd, all -> 0x04d9 }
            boolean r6 = r6.isEmpty()     // Catch:{ SocketTimeoutException -> 0x051b, Exception -> 0x04dd, all -> 0x04d9 }
            if (r6 != 0) goto L_0x015b
            okhttp3.FormBody$Builder r6 = new okhttp3.FormBody$Builder     // Catch:{ SocketTimeoutException -> 0x051b, Exception -> 0x04dd, all -> 0x04d9 }
            r6.<init>()     // Catch:{ SocketTimeoutException -> 0x051b, Exception -> 0x04dd, all -> 0x04d9 }
            java.util.HashMap<java.lang.String, java.lang.String> r7 = r13.postParams     // Catch:{ SocketTimeoutException -> 0x051b, Exception -> 0x04dd, all -> 0x04d9 }
            java.util.Set r7 = r7.entrySet()     // Catch:{ SocketTimeoutException -> 0x051b, Exception -> 0x04dd, all -> 0x04d9 }
            java.util.Iterator r7 = r7.iterator()     // Catch:{ SocketTimeoutException -> 0x051b, Exception -> 0x04dd, all -> 0x04d9 }
        L_0x013f:
            boolean r9 = r7.hasNext()     // Catch:{ SocketTimeoutException -> 0x051b, Exception -> 0x04dd, all -> 0x04d9 }
            if (r9 == 0) goto L_0x015c
            java.lang.Object r9 = r7.next()     // Catch:{ SocketTimeoutException -> 0x051b, Exception -> 0x04dd, all -> 0x04d9 }
            java.util.Map$Entry r9 = (java.util.Map.Entry) r9     // Catch:{ SocketTimeoutException -> 0x051b, Exception -> 0x04dd, all -> 0x04d9 }
            java.lang.Object r10 = r9.getKey()     // Catch:{ SocketTimeoutException -> 0x051b, Exception -> 0x04dd, all -> 0x04d9 }
            java.lang.String r10 = (java.lang.String) r10     // Catch:{ SocketTimeoutException -> 0x051b, Exception -> 0x04dd, all -> 0x04d9 }
            java.lang.Object r9 = r9.getValue()     // Catch:{ SocketTimeoutException -> 0x051b, Exception -> 0x04dd, all -> 0x04d9 }
            java.lang.String r9 = (java.lang.String) r9     // Catch:{ SocketTimeoutException -> 0x051b, Exception -> 0x04dd, all -> 0x04d9 }
            r6.add(r10, r9)     // Catch:{ SocketTimeoutException -> 0x051b, Exception -> 0x04dd, all -> 0x04d9 }
            goto L_0x013f
        L_0x015b:
            r6 = r1
        L_0x015c:
            java.util.ArrayList<java.lang.String> r7 = r13.postArrayParamsKeys     // Catch:{ SocketTimeoutException -> 0x051b, Exception -> 0x04dd, all -> 0x04d9 }
            if (r7 == 0) goto L_0x018e
            java.util.ArrayList<java.lang.String> r7 = r13.postArrayParamsKeys     // Catch:{ SocketTimeoutException -> 0x051b, Exception -> 0x04dd, all -> 0x04d9 }
            boolean r7 = r7.isEmpty()     // Catch:{ SocketTimeoutException -> 0x051b, Exception -> 0x04dd, all -> 0x04d9 }
            if (r7 != 0) goto L_0x018e
            if (r6 != 0) goto L_0x016f
            okhttp3.FormBody$Builder r6 = new okhttp3.FormBody$Builder     // Catch:{ SocketTimeoutException -> 0x051b, Exception -> 0x04dd, all -> 0x04d9 }
            r6.<init>()     // Catch:{ SocketTimeoutException -> 0x051b, Exception -> 0x04dd, all -> 0x04d9 }
        L_0x016f:
            r7 = 0
        L_0x0170:
            java.util.ArrayList<java.lang.String> r9 = r13.postArrayParamsKeys     // Catch:{ SocketTimeoutException -> 0x051b, Exception -> 0x04dd, all -> 0x04d9 }
            int r9 = r9.size()     // Catch:{ SocketTimeoutException -> 0x051b, Exception -> 0x04dd, all -> 0x04d9 }
            if (r7 >= r9) goto L_0x018e
            java.util.ArrayList<java.lang.String> r9 = r13.postArrayParamsKeys     // Catch:{ SocketTimeoutException -> 0x051b, Exception -> 0x04dd, all -> 0x04d9 }
            java.lang.Object r9 = r9.get(r7)     // Catch:{ SocketTimeoutException -> 0x051b, Exception -> 0x04dd, all -> 0x04d9 }
            java.lang.String r9 = (java.lang.String) r9     // Catch:{ SocketTimeoutException -> 0x051b, Exception -> 0x04dd, all -> 0x04d9 }
            java.util.ArrayList<java.lang.String> r10 = r13.postArrayParamsValues     // Catch:{ SocketTimeoutException -> 0x051b, Exception -> 0x04dd, all -> 0x04d9 }
            java.lang.Object r10 = r10.get(r7)     // Catch:{ SocketTimeoutException -> 0x051b, Exception -> 0x04dd, all -> 0x04d9 }
            java.lang.String r10 = (java.lang.String) r10     // Catch:{ SocketTimeoutException -> 0x051b, Exception -> 0x04dd, all -> 0x04d9 }
            r6.add(r9, r10)     // Catch:{ SocketTimeoutException -> 0x051b, Exception -> 0x04dd, all -> 0x04d9 }
            int r7 = r7 + 1
            goto L_0x0170
        L_0x018e:
            if (r6 == 0) goto L_0x0195
            okhttp3.FormBody r6 = r6.build()     // Catch:{ SocketTimeoutException -> 0x051b, Exception -> 0x04dd, all -> 0x04d9 }
            goto L_0x0196
        L_0x0195:
            r6 = r1
        L_0x0196:
            java.lang.String r7 = TAG     // Catch:{ SocketTimeoutException -> 0x051b, Exception -> 0x04dd, all -> 0x04d9 }
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ SocketTimeoutException -> 0x051b, Exception -> 0x04dd, all -> 0x04d9 }
            r9.<init>()     // Catch:{ SocketTimeoutException -> 0x051b, Exception -> 0x04dd, all -> 0x04d9 }
            java.lang.String r10 = "Request: "
            r9.append(r10)     // Catch:{ SocketTimeoutException -> 0x051b, Exception -> 0x04dd, all -> 0x04d9 }
            java.lang.String r10 = r4.toString()     // Catch:{ SocketTimeoutException -> 0x051b, Exception -> 0x04dd, all -> 0x04d9 }
            r9.append(r10)     // Catch:{ SocketTimeoutException -> 0x051b, Exception -> 0x04dd, all -> 0x04d9 }
            java.lang.String r9 = r9.toString()     // Catch:{ SocketTimeoutException -> 0x051b, Exception -> 0x04dd, all -> 0x04d9 }
            android.util.Log.d(r7, r9)     // Catch:{ SocketTimeoutException -> 0x051b, Exception -> 0x04dd, all -> 0x04d9 }
            r3.url((java.net.URL) r4)     // Catch:{ SocketTimeoutException -> 0x051b, Exception -> 0x04dd, all -> 0x04d9 }
            r3.tag(r5)     // Catch:{ SocketTimeoutException -> 0x051b, Exception -> 0x04dd, all -> 0x04d9 }
            java.lang.String r5 = "User-Agent"
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ SocketTimeoutException -> 0x051b, Exception -> 0x04dd, all -> 0x04d9 }
            r7.<init>()     // Catch:{ SocketTimeoutException -> 0x051b, Exception -> 0x04dd, all -> 0x04d9 }
            java.lang.String r9 = "Calculate/"
            r7.append(r9)     // Catch:{ SocketTimeoutException -> 0x051b, Exception -> 0x04dd, all -> 0x04d9 }
            com.wbmd.qxcalculator.AppConfiguration r9 = com.wbmd.qxcalculator.AppConfiguration.getInstance()     // Catch:{ SocketTimeoutException -> 0x051b, Exception -> 0x04dd, all -> 0x04d9 }
            java.lang.String r9 = r9.getAppBuildVersion()     // Catch:{ SocketTimeoutException -> 0x051b, Exception -> 0x04dd, all -> 0x04d9 }
            java.lang.String r9 = r9.trim()     // Catch:{ SocketTimeoutException -> 0x051b, Exception -> 0x04dd, all -> 0x04d9 }
            r7.append(r9)     // Catch:{ SocketTimeoutException -> 0x051b, Exception -> 0x04dd, all -> 0x04d9 }
            java.lang.String r7 = r7.toString()     // Catch:{ SocketTimeoutException -> 0x051b, Exception -> 0x04dd, all -> 0x04d9 }
            r3.addHeader(r5, r7)     // Catch:{ SocketTimeoutException -> 0x051b, Exception -> 0x04dd, all -> 0x04d9 }
            if (r6 == 0) goto L_0x01ed
            com.wbmd.qxcalculator.model.api.APIRequest$HttpType r5 = r13.httpType     // Catch:{ SocketTimeoutException -> 0x051b, Exception -> 0x04dd, all -> 0x04d9 }
            com.wbmd.qxcalculator.model.api.APIRequest$HttpType r7 = com.wbmd.qxcalculator.model.api.APIRequest.HttpType.POST     // Catch:{ SocketTimeoutException -> 0x051b, Exception -> 0x04dd, all -> 0x04d9 }
            if (r5 != r7) goto L_0x01e4
            r3.post(r6)     // Catch:{ SocketTimeoutException -> 0x051b, Exception -> 0x04dd, all -> 0x04d9 }
            goto L_0x01ed
        L_0x01e4:
            com.wbmd.qxcalculator.model.api.APIRequest$HttpType r5 = r13.httpType     // Catch:{ SocketTimeoutException -> 0x051b, Exception -> 0x04dd, all -> 0x04d9 }
            com.wbmd.qxcalculator.model.api.APIRequest$HttpType r7 = com.wbmd.qxcalculator.model.api.APIRequest.HttpType.PUT     // Catch:{ SocketTimeoutException -> 0x051b, Exception -> 0x04dd, all -> 0x04d9 }
            if (r5 != r7) goto L_0x01ed
            r3.put(r6)     // Catch:{ SocketTimeoutException -> 0x051b, Exception -> 0x04dd, all -> 0x04d9 }
        L_0x01ed:
            java.lang.String r5 = TAG     // Catch:{ SocketTimeoutException -> 0x051b, Exception -> 0x04dd, all -> 0x04d9 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ SocketTimeoutException -> 0x051b, Exception -> 0x04dd, all -> 0x04d9 }
            r6.<init>()     // Catch:{ SocketTimeoutException -> 0x051b, Exception -> 0x04dd, all -> 0x04d9 }
            java.lang.String r7 = "requestBuilder url "
            r6.append(r7)     // Catch:{ SocketTimeoutException -> 0x051b, Exception -> 0x04dd, all -> 0x04d9 }
            java.lang.String r7 = r3.toString()     // Catch:{ SocketTimeoutException -> 0x051b, Exception -> 0x04dd, all -> 0x04d9 }
            r6.append(r7)     // Catch:{ SocketTimeoutException -> 0x051b, Exception -> 0x04dd, all -> 0x04d9 }
            java.lang.String r6 = r6.toString()     // Catch:{ SocketTimeoutException -> 0x051b, Exception -> 0x04dd, all -> 0x04d9 }
            android.util.Log.d(r5, r6)     // Catch:{ SocketTimeoutException -> 0x051b, Exception -> 0x04dd, all -> 0x04d9 }
            okhttp3.Request r3 = r3.build()     // Catch:{ SocketTimeoutException -> 0x051b, Exception -> 0x04dd, all -> 0x04d9 }
            long r5 = r13.customTimeout     // Catch:{ SocketTimeoutException -> 0x051b, Exception -> 0x04dd, all -> 0x04d9 }
            r9 = 0
            int r7 = (r5 > r9 ? 1 : (r5 == r9 ? 0 : -1))
            if (r7 != 0) goto L_0x021e
            okhttp3.OkHttpClient r5 = r13.client     // Catch:{ SocketTimeoutException -> 0x051b, Exception -> 0x04dd, all -> 0x04d9 }
            okhttp3.Call r3 = r5.newCall(r3)     // Catch:{ SocketTimeoutException -> 0x051b, Exception -> 0x04dd, all -> 0x04d9 }
            okhttp3.Response r3 = r3.execute()     // Catch:{ SocketTimeoutException -> 0x051b, Exception -> 0x04dd, all -> 0x04d9 }
            goto L_0x0240
        L_0x021e:
            okhttp3.OkHttpClient r5 = r13.client     // Catch:{ SocketTimeoutException -> 0x051b, Exception -> 0x04dd, all -> 0x04d9 }
            okhttp3.OkHttpClient$Builder r5 = r5.newBuilder()     // Catch:{ SocketTimeoutException -> 0x051b, Exception -> 0x04dd, all -> 0x04d9 }
            long r6 = r13.customTimeout     // Catch:{ SocketTimeoutException -> 0x051b, Exception -> 0x04dd, all -> 0x04d9 }
            java.util.concurrent.TimeUnit r9 = java.util.concurrent.TimeUnit.MILLISECONDS     // Catch:{ SocketTimeoutException -> 0x051b, Exception -> 0x04dd, all -> 0x04d9 }
            okhttp3.OkHttpClient$Builder r5 = r5.connectTimeout(r6, r9)     // Catch:{ SocketTimeoutException -> 0x051b, Exception -> 0x04dd, all -> 0x04d9 }
            long r6 = r13.customTimeout     // Catch:{ SocketTimeoutException -> 0x051b, Exception -> 0x04dd, all -> 0x04d9 }
            java.util.concurrent.TimeUnit r9 = java.util.concurrent.TimeUnit.MILLISECONDS     // Catch:{ SocketTimeoutException -> 0x051b, Exception -> 0x04dd, all -> 0x04d9 }
            okhttp3.OkHttpClient$Builder r5 = r5.readTimeout(r6, r9)     // Catch:{ SocketTimeoutException -> 0x051b, Exception -> 0x04dd, all -> 0x04d9 }
            okhttp3.OkHttpClient r5 = r5.build()     // Catch:{ SocketTimeoutException -> 0x051b, Exception -> 0x04dd, all -> 0x04d9 }
            okhttp3.Call r3 = r5.newCall(r3)     // Catch:{ SocketTimeoutException -> 0x051b, Exception -> 0x04dd, all -> 0x04d9 }
            okhttp3.Response r3 = r3.execute()     // Catch:{ SocketTimeoutException -> 0x051b, Exception -> 0x04dd, all -> 0x04d9 }
        L_0x0240:
            int r5 = r3.code()     // Catch:{ SocketTimeoutException -> 0x04d7, Exception -> 0x04d5 }
            r0.httpStatusCode = r5     // Catch:{ SocketTimeoutException -> 0x04d7, Exception -> 0x04d5 }
            java.lang.String r5 = TAG     // Catch:{ SocketTimeoutException -> 0x04d7, Exception -> 0x04d5 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ SocketTimeoutException -> 0x04d7, Exception -> 0x04d5 }
            r6.<init>()     // Catch:{ SocketTimeoutException -> 0x04d7, Exception -> 0x04d5 }
            java.lang.String r7 = "Read Response: "
            r6.append(r7)     // Catch:{ SocketTimeoutException -> 0x04d7, Exception -> 0x04d5 }
            java.lang.String r7 = r4.toString()     // Catch:{ SocketTimeoutException -> 0x04d7, Exception -> 0x04d5 }
            r6.append(r7)     // Catch:{ SocketTimeoutException -> 0x04d7, Exception -> 0x04d5 }
            java.lang.String r7 = "; responseCode "
            r6.append(r7)     // Catch:{ SocketTimeoutException -> 0x04d7, Exception -> 0x04d5 }
            int r7 = r0.httpStatusCode     // Catch:{ SocketTimeoutException -> 0x04d7, Exception -> 0x04d5 }
            r6.append(r7)     // Catch:{ SocketTimeoutException -> 0x04d7, Exception -> 0x04d5 }
            java.lang.String r6 = r6.toString()     // Catch:{ SocketTimeoutException -> 0x04d7, Exception -> 0x04d5 }
            android.util.Log.d(r5, r6)     // Catch:{ SocketTimeoutException -> 0x04d7, Exception -> 0x04d5 }
            java.lang.String r5 = TAG     // Catch:{ SocketTimeoutException -> 0x04d7, Exception -> 0x04d5 }
            java.lang.String r6 = "Response (Action, HTTP, API, Message): (%s, %d, %d, %s)"
            r7 = 4
            java.lang.Object[] r7 = new java.lang.Object[r7]     // Catch:{ SocketTimeoutException -> 0x04d7, Exception -> 0x04d5 }
            java.lang.String r9 = r13.apiAction     // Catch:{ SocketTimeoutException -> 0x04d7, Exception -> 0x04d5 }
            r7[r8] = r9     // Catch:{ SocketTimeoutException -> 0x04d7, Exception -> 0x04d5 }
            int r9 = r0.httpStatusCode     // Catch:{ SocketTimeoutException -> 0x04d7, Exception -> 0x04d5 }
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)     // Catch:{ SocketTimeoutException -> 0x04d7, Exception -> 0x04d5 }
            r7[r2] = r9     // Catch:{ SocketTimeoutException -> 0x04d7, Exception -> 0x04d5 }
            r9 = 2
            int r10 = r0.httpStatusCode     // Catch:{ SocketTimeoutException -> 0x04d7, Exception -> 0x04d5 }
            java.lang.Integer r10 = java.lang.Integer.valueOf(r10)     // Catch:{ SocketTimeoutException -> 0x04d7, Exception -> 0x04d5 }
            r7[r9] = r10     // Catch:{ SocketTimeoutException -> 0x04d7, Exception -> 0x04d5 }
            r9 = 3
            java.lang.String r10 = r0.responseMessage     // Catch:{ SocketTimeoutException -> 0x04d7, Exception -> 0x04d5 }
            r7[r9] = r10     // Catch:{ SocketTimeoutException -> 0x04d7, Exception -> 0x04d5 }
            java.lang.String r6 = java.lang.String.format(r6, r7)     // Catch:{ SocketTimeoutException -> 0x04d7, Exception -> 0x04d5 }
            android.util.Log.d(r5, r6)     // Catch:{ SocketTimeoutException -> 0x04d7, Exception -> 0x04d5 }
            com.wbmd.qxcalculator.model.api.APIRequest$RequestType r5 = r13.type     // Catch:{ SocketTimeoutException -> 0x04d7, Exception -> 0x04d5 }
            com.wbmd.qxcalculator.model.api.APIRequest$RequestType r6 = com.wbmd.qxcalculator.model.api.APIRequest.RequestType.ACCOUNTS     // Catch:{ SocketTimeoutException -> 0x04d7, Exception -> 0x04d5 }
            if (r5 != r6) goto L_0x037d
            int r4 = r0.httpStatusCode     // Catch:{ SocketTimeoutException -> 0x04d7, Exception -> 0x04d5 }
            r5 = 204(0xcc, float:2.86E-43)
            if (r4 == r5) goto L_0x04c5
            okhttp3.ResponseBody r4 = r3.body()     // Catch:{ SocketTimeoutException -> 0x04d7, Exception -> 0x04d5 }
            java.io.InputStream r1 = r4.byteStream()     // Catch:{ SocketTimeoutException -> 0x04d7, Exception -> 0x04d5 }
            com.wbmd.qxcalculator.model.api.APIRequest$ResponseDataType r4 = r13.dataType     // Catch:{ ResourceParseException -> 0x02e9 }
            com.wbmd.qxcalculator.model.api.APIRequest$ResponseDataType r5 = com.wbmd.qxcalculator.model.api.APIRequest.ResponseDataType.USER     // Catch:{ ResourceParseException -> 0x02e9 }
            if (r4 != r5) goto L_0x02b4
            com.wbmd.qxcalculator.model.parsedObjects.User r4 = com.wbmd.qxcalculator.model.parsedObjects.User.convertJsonInputStreamToUser(r1)     // Catch:{ ResourceParseException -> 0x02e9 }
            r0.user = r4     // Catch:{ ResourceParseException -> 0x02e9 }
            goto L_0x0358
        L_0x02b4:
            com.wbmd.qxcalculator.model.api.APIRequest$ResponseDataType r4 = r13.dataType     // Catch:{ ResourceParseException -> 0x02e9 }
            com.wbmd.qxcalculator.model.api.APIRequest$ResponseDataType r5 = com.wbmd.qxcalculator.model.api.APIRequest.ResponseDataType.PROFESSION     // Catch:{ ResourceParseException -> 0x02e9 }
            if (r4 != r5) goto L_0x02c2
            java.util.ArrayList r4 = com.wbmd.qxcalculator.model.parsedObjects.Profession.convertJsonInputStreamToProfession(r1)     // Catch:{ ResourceParseException -> 0x02e9 }
            r0.professions = r4     // Catch:{ ResourceParseException -> 0x02e9 }
            goto L_0x0358
        L_0x02c2:
            com.wbmd.qxcalculator.model.api.APIRequest$ResponseDataType r4 = r13.dataType     // Catch:{ ResourceParseException -> 0x02e9 }
            com.wbmd.qxcalculator.model.api.APIRequest$ResponseDataType r5 = com.wbmd.qxcalculator.model.api.APIRequest.ResponseDataType.SPECIALTY     // Catch:{ ResourceParseException -> 0x02e9 }
            if (r4 != r5) goto L_0x02d0
            java.util.ArrayList r4 = com.wbmd.qxcalculator.model.parsedObjects.Specialty.convertJsonInputStreamToSpecialties(r1)     // Catch:{ ResourceParseException -> 0x02e9 }
            r0.specialties = r4     // Catch:{ ResourceParseException -> 0x02e9 }
            goto L_0x0358
        L_0x02d0:
            com.wbmd.qxcalculator.model.api.APIRequest$ResponseDataType r4 = r13.dataType     // Catch:{ ResourceParseException -> 0x02e9 }
            com.wbmd.qxcalculator.model.api.APIRequest$ResponseDataType r5 = com.wbmd.qxcalculator.model.api.APIRequest.ResponseDataType.LOCATION     // Catch:{ ResourceParseException -> 0x02e9 }
            if (r4 != r5) goto L_0x02de
            java.util.ArrayList r4 = com.wbmd.qxcalculator.model.parsedObjects.Location.convertJsonInputStreamToLocations(r1)     // Catch:{ ResourceParseException -> 0x02e9 }
            r0.locations = r4     // Catch:{ ResourceParseException -> 0x02e9 }
            goto L_0x0358
        L_0x02de:
            com.wbmd.qxcalculator.model.api.APIRequest$ResponseDataType r4 = r13.dataType     // Catch:{ ResourceParseException -> 0x02e9 }
            com.wbmd.qxcalculator.model.api.APIRequest$ResponseDataType r5 = com.wbmd.qxcalculator.model.api.APIRequest.ResponseDataType.PASSWORD_RESET     // Catch:{ ResourceParseException -> 0x02e9 }
            if (r4 != r5) goto L_0x0358
            com.wbmd.qxcalculator.model.contentItems.common.PasswordReset.convertJsonInputStreamToPasswordResets(r1)     // Catch:{ ResourceParseException -> 0x02e9 }
            goto L_0x0358
        L_0x02e9:
            r4 = move-exception
            com.wbmd.qxcalculator.util.CrashLogger r5 = com.wbmd.qxcalculator.util.CrashLogger.getInstance()     // Catch:{ SocketTimeoutException -> 0x04d7, Exception -> 0x04d5 }
            java.lang.String r6 = "error parsing accounts response"
            r5.leaveBreadcrumb(r6)     // Catch:{ SocketTimeoutException -> 0x04d7, Exception -> 0x04d5 }
            com.wbmd.qxcalculator.util.CrashLogger r5 = com.wbmd.qxcalculator.util.CrashLogger.getInstance()     // Catch:{ SocketTimeoutException -> 0x04d7, Exception -> 0x04d5 }
            r5.logHandledException(r4)     // Catch:{ SocketTimeoutException -> 0x04d7, Exception -> 0x04d5 }
            com.github.jasminb.jsonapi.models.errors.Errors r5 = r4.getErrors()     // Catch:{ SocketTimeoutException -> 0x04d7, Exception -> 0x04d5 }
            if (r5 == 0) goto L_0x0357
            com.github.jasminb.jsonapi.models.errors.Errors r4 = r4.getErrors()     // Catch:{ SocketTimeoutException -> 0x04d7, Exception -> 0x04d5 }
            java.util.List r4 = r4.getErrors()     // Catch:{ SocketTimeoutException -> 0x04d7, Exception -> 0x04d5 }
            if (r4 == 0) goto L_0x0357
            boolean r5 = r4.isEmpty()     // Catch:{ SocketTimeoutException -> 0x04d7, Exception -> 0x04d5 }
            if (r5 != 0) goto L_0x0357
            java.util.ArrayList r5 = new java.util.ArrayList     // Catch:{ SocketTimeoutException -> 0x04d7, Exception -> 0x04d5 }
            int r6 = r4.size()     // Catch:{ SocketTimeoutException -> 0x04d7, Exception -> 0x04d5 }
            r5.<init>(r6)     // Catch:{ SocketTimeoutException -> 0x04d7, Exception -> 0x04d5 }
            r0.errors = r5     // Catch:{ SocketTimeoutException -> 0x04d7, Exception -> 0x04d5 }
            java.util.Iterator r4 = r4.iterator()     // Catch:{ SocketTimeoutException -> 0x04d7, Exception -> 0x04d5 }
        L_0x031f:
            boolean r5 = r4.hasNext()     // Catch:{ SocketTimeoutException -> 0x04d7, Exception -> 0x04d5 }
            if (r5 == 0) goto L_0x0357
            java.lang.Object r5 = r4.next()     // Catch:{ SocketTimeoutException -> 0x04d7, Exception -> 0x04d5 }
            com.github.jasminb.jsonapi.models.errors.Error r5 = (com.github.jasminb.jsonapi.models.errors.Error) r5     // Catch:{ SocketTimeoutException -> 0x04d7, Exception -> 0x04d5 }
            r6 = -1
            java.lang.String r7 = r5.getId()     // Catch:{ Exception -> 0x0335, SocketTimeoutException -> 0x04d7 }
            int r7 = java.lang.Integer.parseInt(r7)     // Catch:{ Exception -> 0x0335, SocketTimeoutException -> 0x04d7 }
            goto L_0x0336
        L_0x0335:
            r7 = -1
        L_0x0336:
            java.lang.String r8 = r5.getStatus()     // Catch:{ Exception -> 0x0340, SocketTimeoutException -> 0x04d7 }
            int r6 = java.lang.Integer.parseInt(r8)     // Catch:{ Exception -> 0x0340, SocketTimeoutException -> 0x04d7 }
            r8 = r6
            goto L_0x0341
        L_0x0340:
            r8 = -1
        L_0x0341:
            java.util.ArrayList<com.wbmd.qxcalculator.model.QxError> r11 = r0.errors     // Catch:{ SocketTimeoutException -> 0x04d7, Exception -> 0x04d5 }
            com.wbmd.qxcalculator.model.QxError r12 = new com.wbmd.qxcalculator.model.QxError     // Catch:{ SocketTimeoutException -> 0x04d7, Exception -> 0x04d5 }
            com.wbmd.qxcalculator.model.QxError$ErrorType r6 = com.wbmd.qxcalculator.model.QxError.ErrorType.API     // Catch:{ SocketTimeoutException -> 0x04d7, Exception -> 0x04d5 }
            java.lang.String r9 = r5.getTitle()     // Catch:{ SocketTimeoutException -> 0x04d7, Exception -> 0x04d5 }
            java.lang.String r10 = r5.getDetail()     // Catch:{ SocketTimeoutException -> 0x04d7, Exception -> 0x04d5 }
            r5 = r12
            r5.<init>(r6, r7, r8, r9, r10)     // Catch:{ SocketTimeoutException -> 0x04d7, Exception -> 0x04d5 }
            r11.add(r12)     // Catch:{ SocketTimeoutException -> 0x04d7, Exception -> 0x04d5 }
            goto L_0x031f
        L_0x0357:
            r8 = 1
        L_0x0358:
            if (r8 != 0) goto L_0x04c5
            boolean r4 = r0.success()     // Catch:{ SocketTimeoutException -> 0x04d7, Exception -> 0x04d5 }
            if (r4 != 0) goto L_0x04c5
            java.util.ArrayList r4 = new java.util.ArrayList     // Catch:{ SocketTimeoutException -> 0x04d7, Exception -> 0x04d5 }
            r4.<init>(r2)     // Catch:{ SocketTimeoutException -> 0x04d7, Exception -> 0x04d5 }
            r0.errors = r4     // Catch:{ SocketTimeoutException -> 0x04d7, Exception -> 0x04d5 }
            java.util.ArrayList<com.wbmd.qxcalculator.model.QxError> r4 = r0.errors     // Catch:{ SocketTimeoutException -> 0x04d7, Exception -> 0x04d5 }
            com.wbmd.qxcalculator.model.QxError r11 = new com.wbmd.qxcalculator.model.QxError     // Catch:{ SocketTimeoutException -> 0x04d7, Exception -> 0x04d5 }
            com.wbmd.qxcalculator.model.QxError$ErrorType r6 = com.wbmd.qxcalculator.model.QxError.ErrorType.HTML     // Catch:{ SocketTimeoutException -> 0x04d7, Exception -> 0x04d5 }
            r7 = -1
            int r8 = r0.httpStatusCode     // Catch:{ SocketTimeoutException -> 0x04d7, Exception -> 0x04d5 }
            java.lang.String r9 = "Error"
            java.lang.String r10 = "Error contacting accounts server."
            r5 = r11
            r5.<init>(r6, r7, r8, r9, r10)     // Catch:{ SocketTimeoutException -> 0x04d7, Exception -> 0x04d5 }
            r4.add(r11)     // Catch:{ SocketTimeoutException -> 0x04d7, Exception -> 0x04d5 }
            goto L_0x04c5
        L_0x037d:
            com.wbmd.qxcalculator.model.api.APIRequest$RequestType r5 = r13.type     // Catch:{ SocketTimeoutException -> 0x04d7, Exception -> 0x04d5 }
            com.wbmd.qxcalculator.model.api.APIRequest$RequestType r6 = com.wbmd.qxcalculator.model.api.APIRequest.RequestType.CALCULATE_API_RESOURCE_FETCH     // Catch:{ SocketTimeoutException -> 0x04d7, Exception -> 0x04d5 }
            if (r5 != r6) goto L_0x03fa
            boolean r4 = r3.isSuccessful()     // Catch:{ SocketTimeoutException -> 0x04d7, Exception -> 0x04d5 }
            if (r4 == 0) goto L_0x03cd
            okhttp3.Headers r4 = r3.headers()     // Catch:{ SocketTimeoutException -> 0x04d7, Exception -> 0x04d5 }
            java.lang.String r5 = "Content-Disposition"
            java.lang.String r4 = r4.get(r5)     // Catch:{ SocketTimeoutException -> 0x04d7, Exception -> 0x04d5 }
            int r5 = r0.httpStatusCode     // Catch:{ SocketTimeoutException -> 0x04d7, Exception -> 0x04d5 }
            r6 = 200(0xc8, float:2.8E-43)
            if (r5 != r6) goto L_0x03b3
            if (r4 == 0) goto L_0x03af
            java.lang.String r4 = r4.toLowerCase()     // Catch:{ SocketTimeoutException -> 0x04d7, Exception -> 0x04d5 }
            java.lang.String r5 = "attachment"
            boolean r4 = r4.contains(r5)     // Catch:{ SocketTimeoutException -> 0x04d7, Exception -> 0x04d5 }
            if (r4 == 0) goto L_0x03af
            java.io.File r4 = r13.downloadResourceFilesZip(r3)     // Catch:{ SocketTimeoutException -> 0x04d7, Exception -> 0x04d5 }
            r0.downloadedFile = r4     // Catch:{ SocketTimeoutException -> 0x04d7, Exception -> 0x04d5 }
            goto L_0x04c5
        L_0x03af:
            r0.downloadedFile = r1     // Catch:{ SocketTimeoutException -> 0x04d7, Exception -> 0x04d5 }
            goto L_0x04c5
        L_0x03b3:
            java.lang.String r4 = "API"
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ SocketTimeoutException -> 0x04d7, Exception -> 0x04d5 }
            r5.<init>()     // Catch:{ SocketTimeoutException -> 0x04d7, Exception -> 0x04d5 }
            java.lang.String r6 = "no file to download, code "
            r5.append(r6)     // Catch:{ SocketTimeoutException -> 0x04d7, Exception -> 0x04d5 }
            int r6 = r0.httpStatusCode     // Catch:{ SocketTimeoutException -> 0x04d7, Exception -> 0x04d5 }
            r5.append(r6)     // Catch:{ SocketTimeoutException -> 0x04d7, Exception -> 0x04d5 }
            java.lang.String r5 = r5.toString()     // Catch:{ SocketTimeoutException -> 0x04d7, Exception -> 0x04d5 }
            android.util.Log.d(r4, r5)     // Catch:{ SocketTimeoutException -> 0x04d7, Exception -> 0x04d5 }
            goto L_0x04c5
        L_0x03cd:
            okhttp3.ResponseBody r4 = r3.body()     // Catch:{ SocketTimeoutException -> 0x04d7, Exception -> 0x04d5 }
            java.io.Reader r4 = r4.charStream()     // Catch:{ SocketTimeoutException -> 0x04d7, Exception -> 0x04d5 }
            boolean r5 = r13.ignoreNullValuesInResponse     // Catch:{ SocketTimeoutException -> 0x04d7, Exception -> 0x04d5 }
            boolean r4 = com.wbmd.qxcalculator.model.api.parser.APIParser.parseApiResponse(r4, r0, r5)     // Catch:{ SocketTimeoutException -> 0x04d7, Exception -> 0x04d5 }
            if (r4 != 0) goto L_0x04c5
            java.util.ArrayList r4 = new java.util.ArrayList     // Catch:{ SocketTimeoutException -> 0x04d7, Exception -> 0x04d5 }
            r4.<init>(r2)     // Catch:{ SocketTimeoutException -> 0x04d7, Exception -> 0x04d5 }
            r0.errors = r4     // Catch:{ SocketTimeoutException -> 0x04d7, Exception -> 0x04d5 }
            java.util.ArrayList<com.wbmd.qxcalculator.model.QxError> r4 = r0.errors     // Catch:{ SocketTimeoutException -> 0x04d7, Exception -> 0x04d5 }
            com.wbmd.qxcalculator.model.QxError r11 = new com.wbmd.qxcalculator.model.QxError     // Catch:{ SocketTimeoutException -> 0x04d7, Exception -> 0x04d5 }
            com.wbmd.qxcalculator.model.QxError$ErrorType r6 = com.wbmd.qxcalculator.model.QxError.ErrorType.HTML     // Catch:{ SocketTimeoutException -> 0x04d7, Exception -> 0x04d5 }
            r7 = -1
            int r8 = r0.httpStatusCode     // Catch:{ SocketTimeoutException -> 0x04d7, Exception -> 0x04d5 }
            java.lang.String r9 = "Error"
            java.lang.String r10 = "Error contacting files server."
            r5 = r11
            r5.<init>(r6, r7, r8, r9, r10)     // Catch:{ SocketTimeoutException -> 0x04d7, Exception -> 0x04d5 }
            r4.add(r11)     // Catch:{ SocketTimeoutException -> 0x04d7, Exception -> 0x04d5 }
            goto L_0x04c5
        L_0x03fa:
            boolean r5 = r3.isSuccessful()     // Catch:{ SocketTimeoutException -> 0x04d7, Exception -> 0x04d5 }
            if (r5 == 0) goto L_0x04a4
            com.wbmd.qxcalculator.model.api.APIRequest$RequestType r5 = r13.type     // Catch:{ SocketTimeoutException -> 0x04d7, Exception -> 0x04d5 }
            com.wbmd.qxcalculator.model.api.APIRequest$RequestType r6 = com.wbmd.qxcalculator.model.api.APIRequest.RequestType.CALCULATE_API     // Catch:{ SocketTimeoutException -> 0x04d7, Exception -> 0x04d5 }
            if (r5 != r6) goto L_0x04c5
            java.util.Date r5 = new java.util.Date     // Catch:{ SocketTimeoutException -> 0x04d7, Exception -> 0x04d5 }
            r5.<init>()     // Catch:{ SocketTimeoutException -> 0x04d7, Exception -> 0x04d5 }
            r5.getTime()     // Catch:{ SocketTimeoutException -> 0x04d7, Exception -> 0x04d5 }
            okhttp3.ResponseBody r5 = r3.body()     // Catch:{ SocketTimeoutException -> 0x04d7, Exception -> 0x04d5 }
            java.io.Reader r5 = r5.charStream()     // Catch:{ SocketTimeoutException -> 0x04d7, Exception -> 0x04d5 }
            boolean r6 = r13.ignoreNullValuesInResponse     // Catch:{ SocketTimeoutException -> 0x04d7, Exception -> 0x04d5 }
            boolean r5 = com.wbmd.qxcalculator.model.api.parser.APIParser.parseApiResponse(r5, r0, r6)     // Catch:{ SocketTimeoutException -> 0x04d7, Exception -> 0x04d5 }
            java.util.Date r6 = new java.util.Date     // Catch:{ SocketTimeoutException -> 0x04d7, Exception -> 0x04d5 }
            r6.<init>()     // Catch:{ SocketTimeoutException -> 0x04d7, Exception -> 0x04d5 }
            r6.getTime()     // Catch:{ SocketTimeoutException -> 0x04d7, Exception -> 0x04d5 }
            if (r5 != 0) goto L_0x0449
            java.util.ArrayList r4 = new java.util.ArrayList     // Catch:{ SocketTimeoutException -> 0x04d7, Exception -> 0x04d5 }
            r4.<init>(r2)     // Catch:{ SocketTimeoutException -> 0x04d7, Exception -> 0x04d5 }
            r0.errors = r4     // Catch:{ SocketTimeoutException -> 0x04d7, Exception -> 0x04d5 }
            java.util.ArrayList<com.wbmd.qxcalculator.model.QxError> r4 = r0.errors     // Catch:{ SocketTimeoutException -> 0x04d7, Exception -> 0x04d5 }
            com.wbmd.qxcalculator.model.QxError r11 = new com.wbmd.qxcalculator.model.QxError     // Catch:{ SocketTimeoutException -> 0x04d7, Exception -> 0x04d5 }
            com.wbmd.qxcalculator.model.QxError$ErrorType r6 = com.wbmd.qxcalculator.model.QxError.ErrorType.PROCESSING     // Catch:{ SocketTimeoutException -> 0x04d7, Exception -> 0x04d5 }
            r7 = -1
            r8 = 0
            java.lang.String r9 = "Error"
            java.lang.String r10 = "Error processing refresh response."
            r5 = r11
            r5.<init>(r6, r7, r8, r9, r10)     // Catch:{ SocketTimeoutException -> 0x04d7, Exception -> 0x04d5 }
            r4.add(r11)     // Catch:{ SocketTimeoutException -> 0x04d7, Exception -> 0x04d5 }
            java.lang.String r4 = TAG     // Catch:{ SocketTimeoutException -> 0x04d7, Exception -> 0x04d5 }
            java.lang.String r5 = "Response parsed failed"
            android.util.Log.d(r4, r5)     // Catch:{ SocketTimeoutException -> 0x04d7, Exception -> 0x04d5 }
            goto L_0x04c5
        L_0x0449:
            java.util.ArrayList<com.wbmd.qxcalculator.model.QxError> r5 = r0.errors     // Catch:{ SocketTimeoutException -> 0x04d7, Exception -> 0x04d5 }
            if (r5 == 0) goto L_0x0458
            java.util.ArrayList<com.wbmd.qxcalculator.model.QxError> r5 = r0.errors     // Catch:{ SocketTimeoutException -> 0x04d7, Exception -> 0x04d5 }
            boolean r5 = r5.isEmpty()     // Catch:{ SocketTimeoutException -> 0x04d7, Exception -> 0x04d5 }
            if (r5 != 0) goto L_0x0458
            r0.accountsErrorOnRefreshAll = r2     // Catch:{ SocketTimeoutException -> 0x04d7, Exception -> 0x04d5 }
            goto L_0x04c5
        L_0x0458:
            java.lang.String r5 = r13.apiAction     // Catch:{ SocketTimeoutException -> 0x04d7, Exception -> 0x04d5 }
            java.lang.String r6 = "refresh-all"
            boolean r5 = r5.equals(r6)     // Catch:{ SocketTimeoutException -> 0x04d7, Exception -> 0x04d5 }
            if (r5 == 0) goto L_0x0489
            java.util.List<com.wbmd.qxcalculator.model.contentItems.common.ContentItem> r5 = r0.contentItems     // Catch:{ SocketTimeoutException -> 0x04d7, Exception -> 0x04d5 }
            if (r5 == 0) goto L_0x046e
            java.util.List<com.wbmd.qxcalculator.model.contentItems.common.ContentItem> r5 = r0.contentItems     // Catch:{ SocketTimeoutException -> 0x04d7, Exception -> 0x04d5 }
            boolean r5 = r5.isEmpty()     // Catch:{ SocketTimeoutException -> 0x04d7, Exception -> 0x04d5 }
            if (r5 == 0) goto L_0x0489
        L_0x046e:
            java.util.ArrayList r4 = new java.util.ArrayList     // Catch:{ SocketTimeoutException -> 0x04d7, Exception -> 0x04d5 }
            r4.<init>(r2)     // Catch:{ SocketTimeoutException -> 0x04d7, Exception -> 0x04d5 }
            r0.errors = r4     // Catch:{ SocketTimeoutException -> 0x04d7, Exception -> 0x04d5 }
            java.util.ArrayList<com.wbmd.qxcalculator.model.QxError> r4 = r0.errors     // Catch:{ SocketTimeoutException -> 0x04d7, Exception -> 0x04d5 }
            com.wbmd.qxcalculator.model.QxError r11 = new com.wbmd.qxcalculator.model.QxError     // Catch:{ SocketTimeoutException -> 0x04d7, Exception -> 0x04d5 }
            com.wbmd.qxcalculator.model.QxError$ErrorType r6 = com.wbmd.qxcalculator.model.QxError.ErrorType.PROCESSING     // Catch:{ SocketTimeoutException -> 0x04d7, Exception -> 0x04d5 }
            r7 = -1
            r8 = 0
            java.lang.String r9 = "Error"
            java.lang.String r10 = "Error processing calculators. No calculators found.\\n\\nPlease reinstall the app."
            r5 = r11
            r5.<init>(r6, r7, r8, r9, r10)     // Catch:{ SocketTimeoutException -> 0x04d7, Exception -> 0x04d5 }
            r4.add(r11)     // Catch:{ SocketTimeoutException -> 0x04d7, Exception -> 0x04d5 }
            goto L_0x04c5
        L_0x0489:
            java.lang.String r5 = TAG     // Catch:{ SocketTimeoutException -> 0x04d7, Exception -> 0x04d5 }
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ SocketTimeoutException -> 0x04d7, Exception -> 0x04d5 }
            r6.<init>()     // Catch:{ SocketTimeoutException -> 0x04d7, Exception -> 0x04d5 }
            java.lang.String r7 = "Response parsed: "
            r6.append(r7)     // Catch:{ SocketTimeoutException -> 0x04d7, Exception -> 0x04d5 }
            java.lang.String r4 = r4.toString()     // Catch:{ SocketTimeoutException -> 0x04d7, Exception -> 0x04d5 }
            r6.append(r4)     // Catch:{ SocketTimeoutException -> 0x04d7, Exception -> 0x04d5 }
            java.lang.String r4 = r6.toString()     // Catch:{ SocketTimeoutException -> 0x04d7, Exception -> 0x04d5 }
            android.util.Log.d(r5, r4)     // Catch:{ SocketTimeoutException -> 0x04d7, Exception -> 0x04d5 }
            goto L_0x04c5
        L_0x04a4:
            java.util.ArrayList r4 = new java.util.ArrayList     // Catch:{ SocketTimeoutException -> 0x04d7, Exception -> 0x04d5 }
            r4.<init>(r2)     // Catch:{ SocketTimeoutException -> 0x04d7, Exception -> 0x04d5 }
            r0.errors = r4     // Catch:{ SocketTimeoutException -> 0x04d7, Exception -> 0x04d5 }
            java.util.ArrayList<com.wbmd.qxcalculator.model.QxError> r4 = r0.errors     // Catch:{ SocketTimeoutException -> 0x04d7, Exception -> 0x04d5 }
            com.wbmd.qxcalculator.model.QxError r11 = new com.wbmd.qxcalculator.model.QxError     // Catch:{ SocketTimeoutException -> 0x04d7, Exception -> 0x04d5 }
            com.wbmd.qxcalculator.model.QxError$ErrorType r6 = com.wbmd.qxcalculator.model.QxError.ErrorType.HTML     // Catch:{ SocketTimeoutException -> 0x04d7, Exception -> 0x04d5 }
            r7 = -1
            int r8 = r0.httpStatusCode     // Catch:{ SocketTimeoutException -> 0x04d7, Exception -> 0x04d5 }
            java.lang.String r9 = "Error"
            okhttp3.ResponseBody r5 = r3.body()     // Catch:{ SocketTimeoutException -> 0x04d7, Exception -> 0x04d5 }
            java.lang.String r10 = r5.string()     // Catch:{ SocketTimeoutException -> 0x04d7, Exception -> 0x04d5 }
            r5 = r11
            r5.<init>(r6, r7, r8, r9, r10)     // Catch:{ SocketTimeoutException -> 0x04d7, Exception -> 0x04d5 }
            r4.add(r11)     // Catch:{ SocketTimeoutException -> 0x04d7, Exception -> 0x04d5 }
        L_0x04c5:
            if (r1 == 0) goto L_0x04ca
            r1.close()     // Catch:{ Exception -> 0x057e }
        L_0x04ca:
            if (r3 == 0) goto L_0x059a
            okhttp3.ResponseBody r1 = r3.body()     // Catch:{ Exception -> 0x057e }
        L_0x04d0:
            r1.close()     // Catch:{ Exception -> 0x057e }
            goto L_0x059a
        L_0x04d5:
            r4 = move-exception
            goto L_0x04e0
        L_0x04d7:
            r4 = move-exception
            goto L_0x051e
        L_0x04d9:
            r2 = move-exception
            r3 = r1
            goto L_0x0578
        L_0x04dd:
            r3 = move-exception
            r4 = r3
            r3 = r1
        L_0x04e0:
            r4.printStackTrace()     // Catch:{ all -> 0x0577 }
            com.wbmd.qxcalculator.util.CrashLogger r5 = com.wbmd.qxcalculator.util.CrashLogger.getInstance()     // Catch:{ all -> 0x0577 }
            java.lang.String r6 = "error http"
            r5.leaveBreadcrumb(r6)     // Catch:{ all -> 0x0577 }
            com.wbmd.qxcalculator.util.CrashLogger r5 = com.wbmd.qxcalculator.util.CrashLogger.getInstance()     // Catch:{ all -> 0x0577 }
            r5.logHandledException(r4)     // Catch:{ all -> 0x0577 }
            java.util.ArrayList r5 = new java.util.ArrayList     // Catch:{ all -> 0x0577 }
            r5.<init>(r2)     // Catch:{ all -> 0x0577 }
            r0.errors = r5     // Catch:{ all -> 0x0577 }
            java.util.ArrayList<com.wbmd.qxcalculator.model.QxError> r2 = r0.errors     // Catch:{ all -> 0x0577 }
            com.wbmd.qxcalculator.model.QxError r11 = new com.wbmd.qxcalculator.model.QxError     // Catch:{ all -> 0x0577 }
            com.wbmd.qxcalculator.model.QxError$ErrorType r6 = com.wbmd.qxcalculator.model.QxError.ErrorType.PROCESSING     // Catch:{ all -> 0x0577 }
            r7 = -1
            r8 = 0
            java.lang.String r9 = "Error"
            java.lang.String r10 = r4.getMessage()     // Catch:{ all -> 0x0577 }
            r5 = r11
            r5.<init>(r6, r7, r8, r9, r10)     // Catch:{ all -> 0x0577 }
            r2.add(r11)     // Catch:{ all -> 0x0577 }
            if (r1 == 0) goto L_0x0514
            r1.close()     // Catch:{ Exception -> 0x057e }
        L_0x0514:
            if (r3 == 0) goto L_0x059a
            okhttp3.ResponseBody r1 = r3.body()     // Catch:{ Exception -> 0x057e }
            goto L_0x04d0
        L_0x051b:
            r3 = move-exception
            r4 = r3
            r3 = r1
        L_0x051e:
            r4.printStackTrace()     // Catch:{ all -> 0x0577 }
            r5 = 408(0x198, float:5.72E-43)
            r0.httpStatusCode = r5     // Catch:{ all -> 0x0577 }
            java.util.ArrayList r5 = new java.util.ArrayList     // Catch:{ all -> 0x0577 }
            r5.<init>(r2)     // Catch:{ all -> 0x0577 }
            r0.errors = r5     // Catch:{ all -> 0x0577 }
            java.util.ArrayList<com.wbmd.qxcalculator.model.QxError> r2 = r0.errors     // Catch:{ all -> 0x0577 }
            com.wbmd.qxcalculator.model.QxError r11 = new com.wbmd.qxcalculator.model.QxError     // Catch:{ all -> 0x0577 }
            com.wbmd.qxcalculator.model.QxError$ErrorType r6 = com.wbmd.qxcalculator.model.QxError.ErrorType.API     // Catch:{ all -> 0x0577 }
            r7 = -1
            int r8 = r0.httpStatusCode     // Catch:{ all -> 0x0577 }
            java.lang.String r9 = "Error"
            java.lang.String r10 = "Request Timeout"
            r5 = r11
            r5.<init>(r6, r7, r8, r9, r10)     // Catch:{ all -> 0x0577 }
            r2.add(r11)     // Catch:{ all -> 0x0577 }
            com.wbmd.qxcalculator.util.CrashLogger r2 = com.wbmd.qxcalculator.util.CrashLogger.getInstance()     // Catch:{ all -> 0x0577 }
            java.lang.String r5 = "error socket timeout"
            r2.leaveBreadcrumb(r5)     // Catch:{ all -> 0x0577 }
            com.wbmd.qxcalculator.util.CrashLogger r2 = com.wbmd.qxcalculator.util.CrashLogger.getInstance()     // Catch:{ all -> 0x0577 }
            r2.logHandledException(r4)     // Catch:{ all -> 0x0577 }
            java.lang.String r2 = TAG     // Catch:{ all -> 0x0577 }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x0577 }
            r5.<init>()     // Catch:{ all -> 0x0577 }
            java.lang.String r6 = "Response parsed: timeout: "
            r5.append(r6)     // Catch:{ all -> 0x0577 }
            java.lang.String r4 = r4.getMessage()     // Catch:{ all -> 0x0577 }
            r5.append(r4)     // Catch:{ all -> 0x0577 }
            java.lang.String r4 = r5.toString()     // Catch:{ all -> 0x0577 }
            android.util.Log.d(r2, r4)     // Catch:{ all -> 0x0577 }
            if (r1 == 0) goto L_0x056f
            r1.close()     // Catch:{ Exception -> 0x057e }
        L_0x056f:
            if (r3 == 0) goto L_0x059a
            okhttp3.ResponseBody r1 = r3.body()     // Catch:{ Exception -> 0x057e }
            goto L_0x04d0
        L_0x0577:
            r2 = move-exception
        L_0x0578:
            if (r1 == 0) goto L_0x0580
            r1.close()     // Catch:{ Exception -> 0x057e }
            goto L_0x0580
        L_0x057e:
            r1 = move-exception
            goto L_0x058a
        L_0x0580:
            if (r3 == 0) goto L_0x0589
            okhttp3.ResponseBody r1 = r3.body()     // Catch:{ Exception -> 0x057e }
            r1.close()     // Catch:{ Exception -> 0x057e }
        L_0x0589:
            throw r2     // Catch:{ Exception -> 0x057e }
        L_0x058a:
            com.wbmd.qxcalculator.util.CrashLogger r2 = com.wbmd.qxcalculator.util.CrashLogger.getInstance()
            java.lang.String r3 = "error sending request"
            r2.leaveBreadcrumb(r3)
            com.wbmd.qxcalculator.util.CrashLogger r2 = com.wbmd.qxcalculator.util.CrashLogger.getInstance()
            r2.logHandledException(r1)
        L_0x059a:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wbmd.qxcalculator.model.api.APIRequest.send():com.wbmd.qxcalculator.model.api.APIResponse");
    }

    private File downloadResourceFilesZip(Response response) throws IOException {
        Log.d(TAG, "downloadPDF");
        File masterZipCacheFile = FileHelper.getInstance().getMasterZipCacheFile();
        if (masterZipCacheFile == null) {
            return null;
        }
        response.body().contentLength();
        byte[] bArr = new byte[2048];
        BufferedSink buffer = Okio.buffer(Okio.sink(masterZipCacheFile));
        BufferedSource source = response.body().source();
        int i = 0;
        while (true) {
            int read = source.read(bArr);
            if (read != -1) {
                buffer.write(bArr, 0, read);
                int i2 = i % 25;
                i++;
            } else {
                buffer.flush();
                buffer.close();
                source.close();
                return masterZipCacheFile;
            }
        }
    }
}
