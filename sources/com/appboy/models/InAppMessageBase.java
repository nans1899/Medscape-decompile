package com.appboy.models;

import android.graphics.Bitmap;
import android.net.Uri;
import bo.app.bq;
import bo.app.bz;
import bo.app.cf;
import bo.app.fk;
import bo.app.fm;
import com.appboy.enums.inappmessage.ClickAction;
import com.appboy.enums.inappmessage.CropType;
import com.appboy.enums.inappmessage.DismissType;
import com.appboy.enums.inappmessage.InAppMessageFailureType;
import com.appboy.enums.inappmessage.Orientation;
import com.appboy.enums.inappmessage.TextAlign;
import com.appboy.support.AppboyLogger;
import com.appboy.support.StringUtils;
import com.facebook.applinks.AppLinkData;
import com.facebook.share.internal.MessengerShareContentUtility;
import com.facebook.share.internal.ShareConstants;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class InAppMessageBase implements IInAppMessage {
    public static final int INAPP_MESSAGE_DURATION_DEFAULT_MILLIS = 5000;
    public static final int INAPP_MESSAGE_DURATION_MIN_MILLIS = 999;
    public static final String IS_CONTROL = "is_control";
    public static final String TYPE = "type";
    protected static final String a = AppboyLogger.getAppboyLogTag(InAppMessageBase.class);
    private boolean A;
    private boolean B;
    private boolean C;
    private boolean D;
    private String E;
    private long F;
    String b;
    String c;
    String d;
    protected CropType e;
    protected TextAlign f;
    protected boolean g;
    protected JSONObject h;
    protected bq i;
    private String j;
    private Map<String, String> k;
    private boolean l;
    private boolean m;
    private ClickAction n;
    private Uri o;
    private DismissType p;
    private int q;
    private int r;
    private int s;
    private int t;
    private int u;
    private String v;
    private String w;
    private Orientation x;
    private Bitmap y;
    private boolean z;

    protected InAppMessageBase() {
        this.l = true;
        this.m = true;
        this.n = ClickAction.NONE;
        this.p = DismissType.AUTO_DISMISS;
        this.q = 5000;
        this.r = 0;
        this.s = 0;
        this.t = 0;
        this.u = 0;
        this.x = Orientation.ANY;
        this.z = false;
        this.e = CropType.FIT_CENTER;
        this.f = TextAlign.CENTER;
        this.g = false;
        this.A = false;
        this.B = false;
        this.C = false;
        this.D = false;
        this.F = -1;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public InAppMessageBase(org.json.JSONObject r29, bo.app.bq r30) {
        /*
            r28 = this;
            r15 = r29
            r0 = r28
            r23 = r29
            r24 = r30
            java.lang.String r1 = "message"
            java.lang.String r1 = r15.optString(r1)
            java.lang.String r2 = "extras"
            org.json.JSONObject r2 = r15.optJSONObject(r2)
            java.util.HashMap r3 = new java.util.HashMap
            r3.<init>()
            java.util.Map r2 = bo.app.ed.a((org.json.JSONObject) r2, (java.util.Map<java.lang.String, java.lang.String>) r3)
            java.lang.String r3 = "animate_in"
            r4 = 1
            boolean r3 = r15.optBoolean(r3, r4)
            java.lang.String r5 = "animate_out"
            boolean r4 = r15.optBoolean(r5, r4)
            java.lang.Class<com.appboy.enums.inappmessage.ClickAction> r5 = com.appboy.enums.inappmessage.ClickAction.class
            com.appboy.enums.inappmessage.ClickAction r6 = com.appboy.enums.inappmessage.ClickAction.NONE
            java.lang.String r7 = "click_action"
            java.lang.Enum r5 = bo.app.ed.a(r15, r7, r5, r6)
            com.appboy.enums.inappmessage.ClickAction r5 = (com.appboy.enums.inappmessage.ClickAction) r5
            java.lang.String r6 = "uri"
            java.lang.String r6 = r15.optString(r6)
            java.lang.String r7 = "bg_color"
            int r7 = r15.optInt(r7)
            java.lang.String r8 = "icon_color"
            int r8 = r15.optInt(r8)
            java.lang.String r9 = "icon_bg_color"
            int r9 = r15.optInt(r9)
            java.lang.String r10 = "text_color"
            int r10 = r15.optInt(r10)
            java.lang.String r11 = "icon"
            java.lang.String r11 = r15.optString(r11)
            java.lang.String r12 = "image_url"
            java.lang.String r12 = r15.optString(r12)
            java.lang.Class<com.appboy.enums.inappmessage.DismissType> r13 = com.appboy.enums.inappmessage.DismissType.class
            com.appboy.enums.inappmessage.DismissType r14 = com.appboy.enums.inappmessage.DismissType.AUTO_DISMISS
            r25 = r0
            java.lang.String r0 = "message_close"
            java.lang.Enum r0 = bo.app.ed.a(r15, r0, r13, r14)
            r13 = r0
            com.appboy.enums.inappmessage.DismissType r13 = (com.appboy.enums.inappmessage.DismissType) r13
            java.lang.String r0 = "duration"
            int r14 = r15.optInt(r0)
            java.lang.String r0 = "campaign_id"
            java.lang.String r0 = r15.optString(r0)
            r30 = r1
            r1 = r15
            r15 = r0
            java.lang.String r0 = "card_id"
            java.lang.String r16 = r1.optString(r0)
            java.lang.String r0 = "trigger_id"
            java.lang.String r17 = r1.optString(r0)
            java.lang.Class<com.appboy.enums.inappmessage.Orientation> r0 = com.appboy.enums.inappmessage.Orientation.class
            r26 = r2
            com.appboy.enums.inappmessage.Orientation r2 = com.appboy.enums.inappmessage.Orientation.ANY
            r27 = r3
            java.lang.String r3 = "orientation"
            java.lang.Enum r0 = bo.app.ed.a(r1, r3, r0, r2)
            r20 = r0
            com.appboy.enums.inappmessage.Orientation r20 = (com.appboy.enums.inappmessage.Orientation) r20
            java.lang.String r0 = "use_webview"
            r2 = 0
            boolean r21 = r1.optBoolean(r0, r2)
            java.lang.String r0 = "is_control"
            boolean r22 = r1.optBoolean(r0)
            r18 = 0
            r19 = 0
            r1 = r30
            r0 = r25
            r2 = r26
            r3 = r27
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appboy.models.InAppMessageBase.<init>(org.json.JSONObject, bo.app.bq):void");
    }

    private InAppMessageBase(String str, Map<String, String> map, boolean z2, boolean z3, ClickAction clickAction, String str2, int i2, int i3, int i4, int i5, String str3, String str4, DismissType dismissType, int i6, String str5, String str6, String str7, boolean z4, boolean z5, Orientation orientation, boolean z6, boolean z7, JSONObject jSONObject, bq bqVar) {
        ClickAction clickAction2 = clickAction;
        DismissType dismissType2 = dismissType;
        this.l = true;
        this.m = true;
        this.n = ClickAction.NONE;
        this.p = DismissType.AUTO_DISMISS;
        this.q = 5000;
        this.r = 0;
        this.s = 0;
        this.t = 0;
        this.u = 0;
        this.x = Orientation.ANY;
        this.z = false;
        this.e = CropType.FIT_CENTER;
        this.f = TextAlign.CENTER;
        this.g = false;
        this.A = false;
        this.B = false;
        this.C = false;
        this.D = false;
        this.F = -1;
        this.j = str;
        this.k = map;
        this.l = z2;
        this.m = z3;
        this.n = clickAction2;
        if (clickAction2 == ClickAction.URI && !StringUtils.isNullOrBlank(str2)) {
            this.o = Uri.parse(str2);
        }
        if (dismissType2 == DismissType.SWIPE) {
            this.p = DismissType.MANUAL;
        } else {
            this.p = dismissType2;
        }
        setDurationInMilliseconds(i6);
        this.r = i2;
        this.t = i3;
        this.u = i4;
        this.s = i5;
        this.v = str3;
        this.w = str4;
        this.x = orientation;
        this.b = str5;
        this.c = str6;
        this.d = str7;
        this.A = z4;
        this.B = z5;
        this.g = z6;
        this.D = z7;
        this.h = jSONObject;
        this.i = bqVar;
    }

    public String getMessage() {
        return this.j;
    }

    public Map<String, String> getExtras() {
        return this.k;
    }

    public int getDurationInMilliseconds() {
        return this.q;
    }

    public int getBackgroundColor() {
        return this.r;
    }

    public int getIconColor() {
        return this.t;
    }

    public int getIconBackgroundColor() {
        return this.u;
    }

    public int getMessageTextColor() {
        return this.s;
    }

    public String getIcon() {
        return this.v;
    }

    public String getImageUrl() {
        return getRemoteImageUrl();
    }

    public String getRemoteImageUrl() {
        return this.w;
    }

    public String getLocalImageUrl() {
        return this.E;
    }

    public boolean getAnimateIn() {
        return this.l;
    }

    public boolean getAnimateOut() {
        return this.m;
    }

    public ClickAction getClickAction() {
        return this.n;
    }

    public Uri getUri() {
        return this.o;
    }

    public Bitmap getBitmap() {
        return this.y;
    }

    public DismissType getDismissType() {
        return this.p;
    }

    public boolean getImageDownloadSuccessful() {
        return this.z;
    }

    public String getRemoteAssetPathForPrefetch() {
        return getRemoteImageUrl();
    }

    public Orientation getOrientation() {
        return this.x;
    }

    public CropType getCropType() {
        return this.e;
    }

    public TextAlign getMessageTextAlign() {
        return this.f;
    }

    public long getExpirationTimestamp() {
        return this.F;
    }

    public boolean getOpenUriInWebView() {
        return this.g;
    }

    public void setOpenUriInWebView(boolean z2) {
        this.g = z2;
    }

    public void setExpirationTimestamp(long j2) {
        this.F = j2;
    }

    public void setMessageTextAlign(TextAlign textAlign) {
        this.f = textAlign;
    }

    public void setCropType(CropType cropType) {
        this.e = cropType;
    }

    public void setOrientation(Orientation orientation) {
        this.x = orientation;
    }

    public void setMessage(String str) {
        this.j = str;
    }

    public void setAnimateIn(boolean z2) {
        this.l = z2;
    }

    public void setAnimateOut(boolean z2) {
        this.m = z2;
    }

    public boolean setClickAction(ClickAction clickAction) {
        if (clickAction != ClickAction.URI) {
            this.n = clickAction;
            this.o = null;
            return true;
        }
        AppboyLogger.e(a, "A non-null URI is required in order to set the message ClickAction to URI.");
        return false;
    }

    public boolean setClickAction(ClickAction clickAction, Uri uri) {
        if (uri == null && clickAction == ClickAction.URI) {
            AppboyLogger.e(a, "A non-null URI is required in order to set the message ClickAction to URI.");
            return false;
        } else if (uri == null || clickAction != ClickAction.URI) {
            return setClickAction(clickAction);
        } else {
            this.n = clickAction;
            this.o = uri;
            return true;
        }
    }

    public void setDismissType(DismissType dismissType) {
        this.p = dismissType;
    }

    public void setDurationInMilliseconds(int i2) {
        if (i2 < 999) {
            this.q = 5000;
            String str = a;
            AppboyLogger.w(str, "Requested in-app message duration " + i2 + " is lower than the minimum of " + 999 + ". Defaulting to " + this.q + " milliseconds.");
            return;
        }
        this.q = i2;
        String str2 = a;
        AppboyLogger.d(str2, "Set in-app message duration to " + this.q + " milliseconds.");
    }

    public void setBackgroundColor(int i2) {
        this.r = i2;
    }

    public void setIconColor(int i2) {
        this.t = i2;
    }

    public void setIconBackgroundColor(int i2) {
        this.u = i2;
    }

    public void setMessageTextColor(int i2) {
        this.s = i2;
    }

    public void setIcon(String str) {
        this.v = str;
    }

    public void setImageUrl(String str) {
        setRemoteImageUrl(str);
    }

    public void setRemoteImageUrl(String str) {
        this.w = str;
    }

    public void setLocalImageUrl(String str) {
        this.E = str;
    }

    public void setLocalAssetPathForPrefetch(String str) {
        setLocalImageUrl(str);
    }

    public void setBitmap(Bitmap bitmap) {
        this.y = bitmap;
    }

    public void setImageDownloadSuccessful(boolean z2) {
        this.z = z2;
    }

    public boolean logImpression() {
        if (StringUtils.isNullOrEmpty(this.b) && StringUtils.isNullOrEmpty(this.c) && StringUtils.isNullOrEmpty(this.d)) {
            AppboyLogger.d(a, "Campaign, card, and trigger Ids not found. Not logging in-app message impression.");
            return false;
        } else if (this.A) {
            AppboyLogger.i(a, "Impression already logged for this in-app message. Ignoring.");
            return false;
        } else if (this.C) {
            AppboyLogger.i(a, "Display failure already logged for this in-app message. Ignoring.");
            return false;
        } else if (this.i == null) {
            AppboyLogger.e(a, "Cannot log an in-app message impression because the AppboyManager is null.");
            return false;
        } else {
            try {
                this.i.a((bz) cf.b(this.b, this.c, this.d));
                this.A = true;
                return true;
            } catch (JSONException e2) {
                this.i.a((Throwable) e2);
                return false;
            }
        }
    }

    public boolean logClick() {
        if (StringUtils.isNullOrBlank(this.b) && StringUtils.isNullOrBlank(this.c) && StringUtils.isNullOrBlank(this.d)) {
            AppboyLogger.d(a, "Campaign, card, and trigger Ids not found. Not logging in-app message click.");
            return false;
        } else if (this.B) {
            AppboyLogger.i(a, "Click already logged for this in-app message. Ignoring.");
            return false;
        } else if (this.C) {
            AppboyLogger.i(a, "Display failure already logged for this in-app message. Ignoring.");
            return false;
        } else if (this.i == null) {
            AppboyLogger.e(a, "Cannot log an in-app message click because the AppboyManager is null.");
            return false;
        } else {
            try {
                this.i.a((bz) cf.c(this.b, this.c, this.d));
                this.B = true;
                return true;
            } catch (JSONException e2) {
                this.i.a((Throwable) e2);
                return false;
            }
        }
    }

    public void onAfterClosed() {
        if (this.B && !StringUtils.isNullOrEmpty(this.d)) {
            this.i.a((fk) new fm(this.d));
        }
    }

    public boolean isControl() {
        return this.D;
    }

    public boolean logDisplayFailure(InAppMessageFailureType inAppMessageFailureType) {
        if (StringUtils.isNullOrBlank(this.b) && StringUtils.isNullOrBlank(this.c) && StringUtils.isNullOrBlank(this.d)) {
            AppboyLogger.d(a, "Campaign, card, and trigger Ids not found. Not logging in-app message display failure.");
            return false;
        } else if (this.C) {
            AppboyLogger.i(a, "Display failure already logged for this in-app message. Ignoring.");
            return false;
        } else if (this.B) {
            AppboyLogger.i(a, "Click already logged for this in-app message. Ignoring.");
            return false;
        } else if (this.A) {
            AppboyLogger.i(a, "Impression already logged for this in-app message. Ignoring.");
            return false;
        } else if (this.i == null) {
            AppboyLogger.e(a, "Cannot log an in-app message display failure because the AppboyManager is null.");
            return false;
        } else {
            try {
                this.i.a((bz) cf.a(this.b, this.c, this.d, inAppMessageFailureType));
                this.C = true;
                return true;
            } catch (JSONException e2) {
                this.i.a((Throwable) e2);
                return false;
            }
        }
    }

    public JSONObject forJsonPut() {
        JSONObject jSONObject = this.h;
        if (jSONObject != null) {
            return jSONObject;
        }
        try {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.putOpt(ShareConstants.WEB_DIALOG_PARAM_MESSAGE, this.j);
            jSONObject2.put("duration", this.q);
            jSONObject2.putOpt("campaign_id", this.b);
            jSONObject2.putOpt("card_id", this.c);
            jSONObject2.putOpt("trigger_id", this.d);
            jSONObject2.putOpt("click_action", this.n.toString());
            jSONObject2.putOpt("message_close", this.p.toString());
            if (this.o != null) {
                jSONObject2.put("uri", this.o.toString());
            }
            jSONObject2.put("use_webview", this.g);
            jSONObject2.put("animate_in", this.l);
            jSONObject2.put("animate_out", this.m);
            jSONObject2.put("bg_color", this.r);
            jSONObject2.put("text_color", this.s);
            jSONObject2.put("icon_color", this.t);
            jSONObject2.put("icon_bg_color", this.u);
            jSONObject2.putOpt("icon", this.v);
            jSONObject2.putOpt(MessengerShareContentUtility.IMAGE_URL, this.w);
            jSONObject2.putOpt("crop_type", this.e.toString());
            jSONObject2.putOpt("orientation", this.x.toString());
            jSONObject2.putOpt("text_align_message", this.f.toString());
            jSONObject2.putOpt(IS_CONTROL, Boolean.valueOf(this.D));
            if (this.k != null) {
                JSONObject jSONObject3 = new JSONObject();
                for (String next : this.k.keySet()) {
                    jSONObject3.put(next, this.k.get(next));
                }
                jSONObject2.put(AppLinkData.ARGUMENTS_EXTRAS_KEY, jSONObject3);
            }
            return jSONObject2;
        } catch (JSONException unused) {
            return null;
        }
    }
}
