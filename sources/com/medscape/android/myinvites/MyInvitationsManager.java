package com.medscape.android.myinvites;

import android.content.Context;
import android.content.SharedPreferences;
import com.appboy.Constants;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.medscape.android.helper.AsyncTaskHelper;
import com.medscape.android.myinvites.specific.Invitation;
import com.medscape.android.task.GetOpenMyInvitationsCount;
import com.medscape.android.updater.UpdateManager;
import com.wbmd.environment.EnvironmentConstants;
import com.wbmd.environment.EnvironmentManager;
import com.wbmd.wbmdcommons.utils.Util;
import com.webmd.wbmdcmepulse.models.articles.HtmlObject;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0003\u0018\u0000 \u001d2\u00020\u0001:\u0001\u001dB\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014J\u0006\u0010\u0015\u001a\u00020\u0012J\u0006\u0010\u0016\u001a\u00020\u0012J\u000e\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0018J\u000e\u0010\u001a\u001a\n\u0012\u0004\u0012\u00020\u0018\u0018\u00010\u001bJ\u000e\u0010\u001c\u001a\u00020\u00122\u0006\u0010\u0005\u001a\u00020\u0006R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u0002\n\u0000R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u00068F¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\u00020\n8BX\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\r\u001a\u00020\n8F¢\u0006\u0006\u001a\u0004\b\u000e\u0010\fR\u0011\u0010\u000f\u001a\u00020\n8F¢\u0006\u0006\u001a\u0004\b\u0010\u0010\f¨\u0006\u001e"}, d2 = {"Lcom/medscape/android/myinvites/MyInvitationsManager;", "", "()V", "ctx", "Landroid/content/Context;", "openInvitations", "", "getOpenInvitations", "()Ljava/lang/Integer;", "openInvitesServiceEndpoint", "", "getOpenInvitesServiceEndpoint", "()Ljava/lang/String;", "specificInvitationEndpoint", "getSpecificInvitationEndpoint", "webUrl", "getWebUrl", "fetchAndListenOpenInvitations", "", "listener", "Lcom/medscape/android/task/GetOpenMyInvitationsCount$GetOpenMyInvitationsCountListener;", "fetchOpenInvitations", "fetchSpecificInvitations", "formatInvitation", "Lcom/medscape/android/myinvites/specific/Invitation;", "value", "getSpecificInvitations", "", "updateOpenInvitations", "Companion", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: MyInvitationsManager.kt */
public final class MyInvitationsManager {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String MI_FEED_DEBUG_URL = ("http://www.<env>medscape.com/invitation/viewTracker.do" + Util.attachSrcTagToUrl("http://www.medscape.com/invitation/viewTracker.do"));
    private static final String MI_FEED_URL = ("https://www.medscape.com/invitation/viewTracker.do" + Util.attachSrcTagToUrl("https://www.medscape.com/invitation/viewTracker.do"));
    /* access modifiers changed from: private */
    public static final String MI_OPENINVITES_SERVICES_DEBUG_ENDPOINT = ("http://www.<env>medscape.com/invitation/view.json?site=2001&clientTypeId=3&channelId=5&status=1" + Util.attachSrcTagToUrl("http://www.<env>medscape.com/invitation/view.json?site=2001&clientTypeId=3&channelId=5&status=1"));
    /* access modifiers changed from: private */
    public static final String MI_OPENINVITES_SERVICES_ENDPOINT = ("https://www.medscape.com/invitation/view.json?site=2001&clientTypeId=3&channelId=5&status=1" + Util.attachSrcTagToUrl("https://www.medscape.com/invitation/view.json?site=2001&clientTypeId=3&channelId=5&status=1"));
    /* access modifiers changed from: private */
    public static final String MY_SPECIFIC_INVITATION_ENDPOINT = ("https://wp.medscape.com/activity/invitation/view.json?site=2001&clientTypeId=1&channelId=4&status=1" + Util.attachSrcTagToUrl("https://wp.medscape.com/activity/invitation/view.json?site=2001&clientTypeId=1&channelId=4&status=1"));
    /* access modifiers changed from: private */
    public static final String MY_SPECIFIC_INVITATION__DEBUG_ENDPOINT = ("https://wp.<env>medscape.com/activity/invitation/view.json?site=2001&clientTypeId=1&channelId=4&status=1" + Util.attachSrcTagToUrl("https://wp.<env>.medscape.com/activity/invitation/view.json?site=2001&clientTypeId=1&channelId=4&status=1"));
    /* access modifiers changed from: private */
    public static final String OPEN_INVITATIONS = "openinvitations";
    /* access modifiers changed from: private */
    public static final String SETTINGS_PREFS = UpdateManager.SETTINGS_PREFS;
    /* access modifiers changed from: private */
    public static boolean TESTMODE;
    /* access modifiers changed from: private */
    public static final MyInvitationsManager obj = new MyInvitationsManager();
    private static final ExecutorService threadPoolExecutor = Executors.newSingleThreadExecutor();
    /* access modifiers changed from: private */
    public Context ctx;

    public final String getWebUrl() {
        com.medscape.android.util.Util.getApplicationVersion(this.ctx);
        String str = MI_FEED_DEBUG_URL + Util.addBasicQueryParams(MI_FEED_DEBUG_URL);
        Context context = this.ctx;
        String environmentWithDefault = context != null ? new EnvironmentManager().getEnvironmentWithDefault(context, EnvironmentConstants.MODULE_SERVICE) : null;
        if (environmentWithDefault != null) {
            int hashCode = environmentWithDefault.hashCode();
            if (hashCode != 446124970) {
                if (hashCode != 568937877) {
                    switch (hashCode) {
                        case 568961724:
                            if (environmentWithDefault.equals(EnvironmentConstants.QA00)) {
                                return StringsKt.replace$default(str, "<env>", "qa00.", false, 4, (Object) null);
                            }
                            break;
                        case 568961725:
                            if (environmentWithDefault.equals(EnvironmentConstants.QA01)) {
                                return StringsKt.replace$default(str, "<env>", "qa01.", false, 4, (Object) null);
                            }
                            break;
                        case 568961726:
                            if (environmentWithDefault.equals(EnvironmentConstants.QA02)) {
                                return StringsKt.replace$default(str, "<env>", "qa02.", false, 4, (Object) null);
                            }
                            break;
                    }
                } else if (environmentWithDefault.equals(EnvironmentConstants.PERF)) {
                    return StringsKt.replace$default(str, "<env>", "staging.", false, 4, (Object) null);
                }
            } else if (environmentWithDefault.equals(EnvironmentConstants.DEV01)) {
                return StringsKt.replace$default(str, "<env>", "dev01.", false, 4, (Object) null);
            }
        }
        return MI_FEED_URL + Util.addBasicQueryParams(MI_FEED_URL);
    }

    public final Integer getOpenInvitations() {
        Context context = this.ctx;
        SharedPreferences sharedPreferences = context != null ? context.getSharedPreferences(SETTINGS_PREFS, 0) : null;
        if (sharedPreferences != null) {
            return Integer.valueOf(sharedPreferences.getInt(OPEN_INVITATIONS, 0));
        }
        return null;
    }

    private final String getOpenInvitesServiceEndpoint() {
        com.medscape.android.util.Util.getApplicationVersion(this.ctx);
        String str = MI_OPENINVITES_SERVICES_DEBUG_ENDPOINT + Util.addBasicQueryParams(MI_OPENINVITES_SERVICES_DEBUG_ENDPOINT);
        Context context = this.ctx;
        String environmentWithDefault = context != null ? new EnvironmentManager().getEnvironmentWithDefault(context, EnvironmentConstants.MODULE_SERVICE) : null;
        if (environmentWithDefault != null) {
            int hashCode = environmentWithDefault.hashCode();
            if (hashCode != 446124970) {
                if (hashCode != 568937877) {
                    switch (hashCode) {
                        case 568961724:
                            if (environmentWithDefault.equals(EnvironmentConstants.QA00)) {
                                return StringsKt.replace$default(str, "<env>", "qa00.", false, 4, (Object) null);
                            }
                            break;
                        case 568961725:
                            if (environmentWithDefault.equals(EnvironmentConstants.QA01)) {
                                return StringsKt.replace$default(str, "<env>", "qa01.", false, 4, (Object) null);
                            }
                            break;
                        case 568961726:
                            if (environmentWithDefault.equals(EnvironmentConstants.QA02)) {
                                return StringsKt.replace$default(str, "<env>", "qa02.", false, 4, (Object) null);
                            }
                            break;
                    }
                } else if (environmentWithDefault.equals(EnvironmentConstants.PERF)) {
                    return StringsKt.replace$default(str, "<env>", "staging.", false, 4, (Object) null);
                }
            } else if (environmentWithDefault.equals(EnvironmentConstants.DEV01)) {
                return StringsKt.replace$default(str, "<env>", "dev01.", false, 4, (Object) null);
            }
        }
        return MI_OPENINVITES_SERVICES_ENDPOINT + Util.addBasicQueryParams(MI_OPENINVITES_SERVICES_ENDPOINT);
    }

    public final String getSpecificInvitationEndpoint() {
        String str = MY_SPECIFIC_INVITATION__DEBUG_ENDPOINT + Util.addBasicQueryParams(MY_SPECIFIC_INVITATION__DEBUG_ENDPOINT);
        Context context = this.ctx;
        String environmentWithDefault = context != null ? new EnvironmentManager().getEnvironmentWithDefault(context, EnvironmentConstants.MODULE_SERVICE) : null;
        if (environmentWithDefault != null && environmentWithDefault.hashCode() == 568961725 && environmentWithDefault.equals(EnvironmentConstants.QA01)) {
            return StringsKt.replace$default(str, "<env>", "qa01.", false, 4, (Object) null);
        }
        return MY_SPECIFIC_INVITATION_ENDPOINT + Util.addBasicQueryParams(MY_SPECIFIC_INVITATION_ENDPOINT);
    }

    public final void fetchOpenInvitations() {
        AsyncTaskHelper.execute(threadPoolExecutor, new GetOpenMyInvitationsCount(new MyInvitationsManager$fetchOpenInvitations$getOpenInvitesCountTask$1(this), this.ctx, getOpenInvitesServiceEndpoint(), TESTMODE), new String[0]);
    }

    public final void fetchSpecificInvitations() {
        Job unused = BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getDefault()), (CoroutineContext) null, (CoroutineStart) null, new MyInvitationsManager$fetchSpecificInvitations$1(this, (Continuation) null), 3, (Object) null);
    }

    /* JADX WARNING: Removed duplicated region for block: B:32:0x009a  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x0067 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.List<com.medscape.android.myinvites.specific.Invitation> getSpecificInvitations() {
        /*
            r10 = this;
            com.medscape.android.provider.SharedPreferenceProvider r0 = com.medscape.android.provider.SharedPreferenceProvider.get()
            java.lang.String r1 = "pref_specific_invitations"
            java.lang.String r2 = ""
            java.lang.String r0 = r0.get((java.lang.String) r1, (java.lang.String) r2)
            r1 = r0
            java.lang.CharSequence r1 = (java.lang.CharSequence) r1
            r2 = 1
            r3 = 0
            if (r1 == 0) goto L_0x001c
            int r1 = r1.length()
            if (r1 != 0) goto L_0x001a
            goto L_0x001c
        L_0x001a:
            r1 = 0
            goto L_0x001d
        L_0x001c:
            r1 = 1
        L_0x001d:
            r4 = 0
            if (r1 == 0) goto L_0x0021
            return r4
        L_0x0021:
            com.google.gson.Gson r1 = new com.google.gson.Gson
            r1.<init>()
            java.lang.Class<com.medscape.android.myinvites.specific.SpecificInvitations> r5 = com.medscape.android.myinvites.specific.SpecificInvitations.class
            java.lang.Object r0 = r1.fromJson((java.lang.String) r0, r5)
            com.medscape.android.myinvites.specific.SpecificInvitations r0 = (com.medscape.android.myinvites.specific.SpecificInvitations) r0
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            if (r0 == 0) goto L_0x005a
            java.util.ArrayList r0 = r0.getInvitations()
            if (r0 == 0) goto L_0x005a
            java.lang.Iterable r0 = (java.lang.Iterable) r0
            java.util.Iterator r0 = r0.iterator()
        L_0x0041:
            boolean r5 = r0.hasNext()
            if (r5 == 0) goto L_0x005a
            java.lang.Object r5 = r0.next()
            com.medscape.android.myinvites.specific.Invitation r5 = (com.medscape.android.myinvites.specific.Invitation) r5
            com.medscape.android.myinvites.MyInvitationsManager r6 = new com.medscape.android.myinvites.MyInvitationsManager
            r6.<init>()
            com.medscape.android.myinvites.specific.Invitation r5 = r6.formatInvitation(r5)
            r1.add(r5)
            goto L_0x0041
        L_0x005a:
            java.lang.Iterable r1 = (java.lang.Iterable) r1
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.util.Collection r0 = (java.util.Collection) r0
            java.util.Iterator r1 = r1.iterator()
        L_0x0067:
            boolean r5 = r1.hasNext()
            if (r5 == 0) goto L_0x009e
            java.lang.Object r5 = r1.next()
            r6 = r5
            com.medscape.android.myinvites.specific.Invitation r6 = (com.medscape.android.myinvites.specific.Invitation) r6
            java.lang.String r7 = r6.isNew()
            r8 = 2
            java.lang.String r9 = "1"
            boolean r7 = kotlin.text.StringsKt.equals$default(r7, r9, r3, r8, r4)
            if (r7 == 0) goto L_0x0097
            java.lang.String r6 = r6.getDescription()
            java.lang.CharSequence r6 = (java.lang.CharSequence) r6
            if (r6 == 0) goto L_0x0092
            int r6 = r6.length()
            if (r6 != 0) goto L_0x0090
            goto L_0x0092
        L_0x0090:
            r6 = 0
            goto L_0x0093
        L_0x0092:
            r6 = 1
        L_0x0093:
            if (r6 == 0) goto L_0x0097
            r6 = 1
            goto L_0x0098
        L_0x0097:
            r6 = 0
        L_0x0098:
            if (r6 == 0) goto L_0x0067
            r0.add(r5)
            goto L_0x0067
        L_0x009e:
            java.util.List r0 = (java.util.List) r0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.medscape.android.myinvites.MyInvitationsManager.getSpecificInvitations():java.util.List");
    }

    public final Invitation formatInvitation(Invitation invitation) {
        Intrinsics.checkNotNullParameter(invitation, "value");
        String description = invitation.getDescription();
        CharSequence charSequence = description;
        if (charSequence == null || charSequence.length() == 0) {
            return invitation;
        }
        if (StringsKt.startsWith$default(description, HtmlObject.HtmlMarkUp.OPEN_BRACKER, false, 2, (Object) null)) {
            invitation.setDescription("");
        } else if (StringsKt.contains$default(charSequence, (CharSequence) HtmlObject.HtmlMarkUp.OPEN_BRACKER, false, 2, (Object) null)) {
            int length = charSequence.length();
            int i = 0;
            while (true) {
                if (i >= length) {
                    i = -1;
                    break;
                } else if (Intrinsics.areEqual((Object) String.valueOf(charSequence.charAt(i)), (Object) HtmlObject.HtmlMarkUp.OPEN_BRACKER)) {
                    break;
                } else {
                    i++;
                }
            }
            if (description != null) {
                String substring = description.substring(0, i);
                Intrinsics.checkNotNullExpressionValue(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                invitation.setDescription(substring);
            } else {
                throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
            }
        }
        Document parse = Jsoup.parse(description);
        Element first = parse.select(Constants.APPBOY_PUSH_CONTENT_KEY).first();
        if (first != null) {
            parse.body();
            invitation.setCta(first.text());
        }
        Element first2 = parse.select("span").first();
        if (first2 != null) {
            invitation.setInfo(first2.text());
        }
        Element first3 = parse.select("div").first();
        if (first3 != null) {
            invitation.setInfo(first3.text());
        }
        return invitation;
    }

    public final void fetchAndListenOpenInvitations(GetOpenMyInvitationsCount.GetOpenMyInvitationsCountListener getOpenMyInvitationsCountListener) {
        Intrinsics.checkNotNullParameter(getOpenMyInvitationsCountListener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        AsyncTaskHelper.execute(threadPoolExecutor, new GetOpenMyInvitationsCount(getOpenMyInvitationsCountListener, this.ctx, getOpenInvitesServiceEndpoint(), TESTMODE), new String[0]);
    }

    public final void updateOpenInvitations(int i) {
        Context context = this.ctx;
        Intrinsics.checkNotNull(context);
        context.getSharedPreferences(SETTINGS_PREFS, 0).edit().putInt(OPEN_INVITATIONS, i).apply();
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0011\u0010\u001a\u001a\u00020\u00162\u0006\u0010\u001b\u001a\u00020\u001cH\u0002J\u0010\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010 R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0006\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\bR\u0011\u0010\u000b\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\bR\u0011\u0010\r\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\bR\u0014\u0010\u000f\u001a\u00020\u0004XD¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\bR\u0014\u0010\u0011\u001a\u00020\u0004XD¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\bR\u000e\u0010\u0013\u001a\u00020\u0014X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0017\u001a\n \u0019*\u0004\u0018\u00010\u00180\u0018X\u0004¢\u0006\u0002\n\u0000¨\u0006!"}, d2 = {"Lcom/medscape/android/myinvites/MyInvitationsManager$Companion;", "", "()V", "MI_FEED_DEBUG_URL", "", "MI_FEED_URL", "MI_OPENINVITES_SERVICES_DEBUG_ENDPOINT", "getMI_OPENINVITES_SERVICES_DEBUG_ENDPOINT", "()Ljava/lang/String;", "MI_OPENINVITES_SERVICES_ENDPOINT", "getMI_OPENINVITES_SERVICES_ENDPOINT", "MY_SPECIFIC_INVITATION_ENDPOINT", "getMY_SPECIFIC_INVITATION_ENDPOINT", "MY_SPECIFIC_INVITATION__DEBUG_ENDPOINT", "getMY_SPECIFIC_INVITATION__DEBUG_ENDPOINT", "OPEN_INVITATIONS", "getOPEN_INVITATIONS", "SETTINGS_PREFS", "getSETTINGS_PREFS", "TESTMODE", "", "obj", "Lcom/medscape/android/myinvites/MyInvitationsManager;", "threadPoolExecutor", "Ljava/util/concurrent/ExecutorService;", "kotlin.jvm.PlatformType", "get", "ctx", "Landroid/content/Context;", "getOpenMyInvitationsCount", "", "json", "Lorg/json/JSONObject;", "medscape_release"}, k = 1, mv = {1, 4, 0})
    /* compiled from: MyInvitationsManager.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final String getSETTINGS_PREFS() {
            return MyInvitationsManager.SETTINGS_PREFS;
        }

        public final String getOPEN_INVITATIONS() {
            return MyInvitationsManager.OPEN_INVITATIONS;
        }

        public final String getMI_OPENINVITES_SERVICES_ENDPOINT() {
            return MyInvitationsManager.MI_OPENINVITES_SERVICES_ENDPOINT;
        }

        public final String getMI_OPENINVITES_SERVICES_DEBUG_ENDPOINT() {
            return MyInvitationsManager.MI_OPENINVITES_SERVICES_DEBUG_ENDPOINT;
        }

        public final String getMY_SPECIFIC_INVITATION_ENDPOINT() {
            return MyInvitationsManager.MY_SPECIFIC_INVITATION_ENDPOINT;
        }

        public final String getMY_SPECIFIC_INVITATION__DEBUG_ENDPOINT() {
            return MyInvitationsManager.MY_SPECIFIC_INVITATION__DEBUG_ENDPOINT;
        }

        public final MyInvitationsManager get(Context context) {
            Intrinsics.checkNotNullParameter(context, "ctx");
            MyInvitationsManager.obj.ctx = context;
            MyInvitationsManager.TESTMODE = false;
            return MyInvitationsManager.obj;
        }

        public final int getOpenMyInvitationsCount(JSONObject jSONObject) {
            JSONArray optJSONArray;
            if (jSONObject == null || (optJSONArray = jSONObject.optJSONArray("campaigns")) == null) {
                return 0;
            }
            return optJSONArray.length();
        }
    }
}
