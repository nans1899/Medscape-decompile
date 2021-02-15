package com.webmd.wbmdproffesionalauthentication.providers;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.android.volley.AuthFailureError;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
import com.wbmd.wbmdcommons.callbacks.ICallbackEvent;
import com.wbmd.wbmdcommons.extensions.StringExtensions;
import com.wbmd.wbmdhttpclient.VolleyRestClient;
import com.webmd.wbmdproffesionalauthentication.R;
import com.webmd.wbmdproffesionalauthentication.constants.SharedPreferenceConstants;
import com.webmd.wbmdproffesionalauthentication.encryption.EncryptionHelper;
import com.webmd.wbmdproffesionalauthentication.model.EnvironmentData;
import com.webmd.wbmdproffesionalauthentication.parser.LoginErrorParser;
import com.webmd.wbmdproffesionalauthentication.utilities.AuthComponentConstants;
import com.webmd.wbmdproffesionalauthentication.utilities.EnvironmentUtil;
import com.webmd.wbmdproffesionalauthentication.utilities.Util;
import com.webmd.wbmdproffesionalauthentication.utilities.VolleyErrorConverter;
import com.webmd.wbmdsimullytics.routers.FirebaseRouter;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class AccountProvider {
    private static final String MESSAGE = "msg";
    private static final String PASSWORD_PARAM_KEY = "password";
    private static final String PASSWORD_USERNAME_ENABLE = "checkUsernameAndEmail";
    private static final String USER_NAME_PARAM_KEY = "username";
    /* access modifiers changed from: private */
    public static int screenComingFrom = -1;

    public static void signInUser(Context context, String str, String str2, int i, ICallbackEvent<Object, Exception> iCallbackEvent) {
        screenComingFrom = i;
        signInUser(context, str, str2, iCallbackEvent);
    }

    public static boolean signInUser(final Context context, final String str, final String str2, final ICallbackEvent<Object, Exception> iCallbackEvent) {
        if (context == null) {
            logCrashlyticEvent(context, new Exception("Context null"), "", "Context null", true);
            iCallbackEvent.onError(new Exception("Context null"));
            return false;
        }
        HashMap hashMap = new HashMap();
        hashMap.put(USER_NAME_PARAM_KEY, str);
        hashMap.put(PASSWORD_PARAM_KEY, str2);
        hashMap.put(PASSWORD_USERNAME_ENABLE, true);
        VolleyRestClient instance = VolleyRestClient.getInstance(context);
        instance.post(EnvironmentUtil.Companion.getUrl(context, EnvironmentData.LOGIN_URL) + "?src=" + Util.getAppNameForUrl(context), (Map<String, Object>) hashMap, (ICallbackEvent) new ICallbackEvent<Object, Exception>() {
            public void onError(Exception exc) {
                String str;
                AuthFailureError authFailureError;
                if (iCallbackEvent != null) {
                    String str2 = "Network Error";
                    String str3 = "Unknown Cause";
                    if (exc != null) {
                        try {
                            if (exc.getCause() != null) {
                                str3 = exc.getCause().toString();
                                if (!(!(exc.getCause() instanceof AuthFailureError) || (authFailureError = (AuthFailureError) exc.getCause()) == null || authFailureError.networkResponse == null || authFailureError.networkResponse.data == null)) {
                                    str = new String(authFailureError.networkResponse.data);
                                    try {
                                        JSONObject jSONObject = new JSONObject(new String(authFailureError.networkResponse.data));
                                        int optInt = jSONObject.optInt("errorCode");
                                        String optString = jSONObject.optString("errorMessage");
                                        if (optInt != 101 || StringExtensions.isNullOrEmpty(optString)) {
                                            exc = new Exception(str2);
                                        }
                                    } catch (Throwable unused) {
                                        exc = new Exception(str2);
                                        str2 = str;
                                        AccountProvider.logCrashlyticEvent(context, new Exception(str2), str3, str2, true);
                                        iCallbackEvent.onError(exc);
                                    }
                                    str2 = str;
                                }
                                AccountProvider.logCrashlyticEvent(context, new Exception(str2), str3, str2, true);
                                iCallbackEvent.onError(exc);
                            }
                        } catch (Throwable unused2) {
                            str = str2;
                        }
                    }
                    exc = new Exception(str2);
                    AccountProvider.logCrashlyticEvent(context, new Exception(str2), str3, str2, true);
                    iCallbackEvent.onError(exc);
                }
            }

            public void onCompleted(Object obj) {
                String parseLoginError = LoginErrorParser.parseLoginError(context, (JSONObject) obj);
                if (StringExtensions.isNullOrEmpty(parseLoginError)) {
                    SharedPreferenceProvider.get().save(SharedPreferenceConstants.USER_NAME_KEYCHAIN_KEY, str, context);
                    SharedPreferenceProvider.get().save(SharedPreferenceConstants.PASSWORD_KEYCHAIN_KEY, str2, context);
                    try {
                        FirebaseAnalytics instance = FirebaseAnalytics.getInstance(context);
                        Bundle bundle = new Bundle();
                        bundle.putBoolean("success", true);
                        instance.logEvent("login", bundle);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Intent intent = new Intent(AuthComponentConstants.EXTRA_LOGIN_BROADCAST);
                    intent.putExtra(AuthComponentConstants.EXTRA_LOGIN_DATA, obj.toString());
                    intent.putExtra(AuthComponentConstants.LOGIN_STATUS, AuthComponentConstants.LOGIN_SUCCESS);
                    intent.putExtra(AuthComponentConstants.TRIGGER_SCREEN, AccountProvider.screenComingFrom);
                    LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
                    ICallbackEvent iCallbackEvent = iCallbackEvent;
                    if (iCallbackEvent != null) {
                        iCallbackEvent.onCompleted(obj);
                        return;
                    }
                    return;
                }
                if (iCallbackEvent != null) {
                    Exception exc = new Exception(parseLoginError);
                    if (parseLoginError.equals("INVALID USER CREDENTIALS")) {
                        exc.initCause(new AuthFailureError());
                    }
                    iCallbackEvent.onError(exc);
                }
                AccountProvider.logCrashlyticEvent(context, new Exception(parseLoginError), "parseLoginError", parseLoginError, true);
            }
        });
        instance.flushQueue();
        return false;
    }

    public static void signIn(Context context, ICallbackEvent<Object, Exception> iCallbackEvent) {
        String str = "";
        if (context == null) {
            logCrashlyticEvent(context, new Exception("Context null"), str, "Context null", true);
            iCallbackEvent.onError(new Exception("Context null"));
        } else if (isUserLoggedIn(context)) {
            try {
                signInUser(context, EncryptionHelper.decrypt(SharedPreferenceProvider.get().getString(SharedPreferenceConstants.USER_NAME_KEYCHAIN_KEY, str, context)), EncryptionHelper.decrypt(SharedPreferenceProvider.get().getString(SharedPreferenceConstants.PASSWORD_KEYCHAIN_KEY, str, context)), iCallbackEvent);
            } catch (Exception e) {
                if (e.getCause() != null) {
                    str = e.getCause().toString();
                }
                logCrashlyticEvent(context, e, "signIn", str, true);
                iCallbackEvent.onError(new Exception("Login Failed"));
            }
        } else {
            logCrashlyticEvent(context, new Exception("Login failed"), "signIn", "userNotLoggedIn", true);
            iCallbackEvent.onError(new Exception("Login failed"));
        }
    }

    public static void sendPasswordResetRequest(final Context context, String str, String str2, final ICallbackEvent<JSONObject, String> iCallbackEvent) {
        if (StringExtensions.isNullOrEmpty(str) && iCallbackEvent != null) {
            iCallbackEvent.onError("Email can't be empty");
        } else if (context != null) {
            String url = EnvironmentUtil.Companion.getUrl(context, EnvironmentData.PASSWORD_RESET_URL);
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("email", str);
                jSONObject.put("locale", str2);
                HashMap hashMap = new HashMap();
                hashMap.put("isEncrypted", "false");
                VolleyRestClient.getInstance(context).post(url, jSONObject, (Map<String, String>) hashMap, (ICallbackEvent) new ICallbackEvent() {
                    public void onError(Object obj) {
                        if (obj instanceof Exception) {
                            ICallbackEvent iCallbackEvent = iCallbackEvent;
                            if (iCallbackEvent != null) {
                                iCallbackEvent.onError(VolleyErrorConverter.exceptionToMessage(context, (Exception) obj));
                                return;
                            }
                            return;
                        }
                        ICallbackEvent iCallbackEvent2 = iCallbackEvent;
                        if (iCallbackEvent2 != null) {
                            iCallbackEvent2.onError(context.getString(R.string.error_api_unknown));
                        }
                    }

                    public void onCompleted(Object obj) {
                        if (obj instanceof JSONObject) {
                            ICallbackEvent iCallbackEvent = iCallbackEvent;
                            if (iCallbackEvent != null) {
                                iCallbackEvent.onCompleted((JSONObject) obj);
                                return;
                            }
                            return;
                        }
                        ICallbackEvent iCallbackEvent2 = iCallbackEvent;
                        if (iCallbackEvent2 != null) {
                            iCallbackEvent2.onCompleted(null);
                        }
                    }
                });
            } catch (Exception unused) {
                if (iCallbackEvent != null) {
                    iCallbackEvent.onError("Request failed");
                }
            }
        } else if (iCallbackEvent != null) {
            iCallbackEvent.onError("Request failed");
        }
    }

    public static boolean isUserLoggedIn(Context context) {
        if (context != null) {
            String string = SharedPreferenceProvider.get().getString(SharedPreferenceConstants.USER_NAME_KEYCHAIN_KEY, "", context);
            String string2 = SharedPreferenceProvider.get().getString(SharedPreferenceConstants.PASSWORD_KEYCHAIN_KEY, "", context);
            if (StringExtensions.isNullOrEmpty(string) || StringExtensions.isNullOrEmpty(string2)) {
                return false;
            }
            return true;
        }
        return false;
    }

    public static void logOut(Context context) {
        if (context != null) {
            SharedPreferenceProvider.get().save(SharedPreferenceConstants.USER_NAME_KEYCHAIN_KEY, "", context);
            SharedPreferenceProvider.get().save(SharedPreferenceConstants.PASSWORD_KEYCHAIN_KEY, "", context);
        }
    }

    public static void logCrashlyticEvent(Context context, Exception exc, String str, String str2, boolean z) {
        Bundle bundle;
        String message;
        int i;
        Context context2 = context;
        Exception exc2 = exc;
        String str3 = str;
        try {
            FirebaseCrashlytics.getInstance().recordException(exc2);
            bundle = new Bundle();
            message = StringExtensions.isNullOrEmpty(str2) ? exc.getMessage() : str2;
            i = 99;
            if (StringExtensions.isNotEmpty(message)) {
                String str4 = "";
                JSONObject jSONObject = new JSONObject(message);
                if (!jSONObject.isNull("errorMessage")) {
                    str4 = jSONObject.optString("errorMessage");
                }
                if (!jSONObject.isNull("errorDescription")) {
                    str4 = jSONObject.optString("errorDescription");
                    if (str4.contains(",")) {
                        str4 = str4.split(",")[1].trim();
                    }
                }
                bundle.putString("msg", str4.substring(0, str4.length() > 100 ? 99 : str4.length() - 1));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Throwable unused) {
            exc.printStackTrace();
            FirebaseCrashlytics.getInstance().recordException(exc2);
            return;
        }
        if (StringExtensions.isNotEmpty(str)) {
            if (str.length() <= 100) {
                i = str.length() - 1;
            }
            message = str3.substring(0, i);
            bundle.putString("cause", str3);
        }
        String str5 = z ? "login_fail" : "registration_fail";
        if (context2 != null) {
            new FirebaseRouter(context2).routeEvent(str5, bundle);
        }
        FirebaseAnalytics instance = FirebaseAnalytics.getInstance(context);
        Bundle bundle2 = new Bundle();
        bundle2.putBoolean("success", false);
        bundle2.putString("msg", message);
        instance.logEvent("login", bundle2);
    }
}
