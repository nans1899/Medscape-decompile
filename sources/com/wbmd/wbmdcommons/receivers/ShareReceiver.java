package com.wbmd.wbmdcommons.receivers;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import com.comscore.streaming.ContentFeedType;
import com.facebook.share.internal.ShareConstants;
import com.wbmd.adlibrary.constants.AdParameterKeys;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nJ\u0010\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\fJ\u001c\u0010\u000e\u001a\u00020\u000f2\b\u0010\t\u001a\u0004\u0018\u00010\n2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0017J\u0010\u0010\u0012\u001a\u00020\u000f2\b\u0010\t\u001a\u0004\u0018\u00010\nR\u0014\u0010\u0003\u001a\u00020\u0004XD¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0014"}, d2 = {"Lcom/wbmd/wbmdcommons/receivers/ShareReceiver;", "Landroid/content/BroadcastReceiver;", "()V", "requestCode", "", "getRequestCode", "()I", "createPendingIntent", "Landroid/app/PendingIntent;", "context", "Landroid/content/Context;", "getAppLink", "", "value", "onReceive", "", "intent", "Landroid/content/Intent;", "updateTextShareableApps", "Companion", "wbmdcommons_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: ShareReceiver.kt */
public final class ShareReceiver extends BroadcastReceiver {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final String SHARE_MODULE_CONTENT = "share-bar";
    /* access modifiers changed from: private */
    public static final String SHARE_MODULE_FEED = "share-feed";
    /* access modifiers changed from: private */
    public static ArrayList<String> textShareApps;
    private final int requestCode = ContentFeedType.WEST_SD;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u00020\u0004XD¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u0004XD¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006R.\u0010\t\u001a\u0016\u0012\u0004\u0012\u00020\u0004\u0018\u00010\nj\n\u0012\u0004\u0012\u00020\u0004\u0018\u0001`\u000bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f¨\u0006\u0010"}, d2 = {"Lcom/wbmd/wbmdcommons/receivers/ShareReceiver$Companion;", "", "()V", "SHARE_MODULE_CONTENT", "", "getSHARE_MODULE_CONTENT", "()Ljava/lang/String;", "SHARE_MODULE_FEED", "getSHARE_MODULE_FEED", "textShareApps", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "getTextShareApps", "()Ljava/util/ArrayList;", "setTextShareApps", "(Ljava/util/ArrayList;)V", "wbmdcommons_release"}, k = 1, mv = {1, 4, 0})
    /* compiled from: ShareReceiver.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final String getSHARE_MODULE_CONTENT() {
            return ShareReceiver.SHARE_MODULE_CONTENT;
        }

        public final String getSHARE_MODULE_FEED() {
            return ShareReceiver.SHARE_MODULE_FEED;
        }

        public final ArrayList<String> getTextShareApps() {
            return ShareReceiver.textShareApps;
        }

        public final void setTextShareApps(ArrayList<String> arrayList) {
            ShareReceiver.textShareApps = arrayList;
        }
    }

    public final int getRequestCode() {
        return this.requestCode;
    }

    public void onReceive(Context context, Intent intent) {
        if (intent != null && context != null) {
            ComponentName componentName = (ComponentName) intent.getParcelableExtra("android.intent.extra.CHOSEN_COMPONENT");
            ShareDataObservable.INSTANCE.updateData(getAppLink(componentName != null ? componentName.getPackageName() : null));
        }
    }

    public final PendingIntent createPendingIntent(Context context) {
        updateTextShareableApps(context);
        PendingIntent broadcast = PendingIntent.getBroadcast(context, this.requestCode, new Intent(context, ShareReceiver.class), 134217728);
        Intrinsics.checkNotNullExpressionValue(broadcast, "PendingIntent.getBroadca…_UPDATE_CURRENT\n        )");
        return broadcast;
    }

    public final void updateTextShareableApps(Context context) {
        ArrayList<String> arrayList;
        Collection collection = textShareApps;
        if ((collection == null || collection.isEmpty()) && context != null) {
            Intent data = new Intent("android.intent.action.VIEW").addCategory("android.intent.category.DEFAULT").setData(Uri.parse("sms:"));
            Intrinsics.checkNotNullExpressionValue(data, "Intent(Intent.ACTION_VIE…etData(Uri.parse(\"sms:\"))");
            PackageManager packageManager = context.getPackageManager();
            List<ResolveInfo> queryIntentActivities = packageManager != null ? packageManager.queryIntentActivities(data, 0) : null;
            if (queryIntentActivities != null && (!queryIntentActivities.isEmpty())) {
                textShareApps = new ArrayList<>();
                for (ResolveInfo next : queryIntentActivities) {
                    CharSequence charSequence = next.activityInfo.packageName;
                    if (!(charSequence == null || StringsKt.isBlank(charSequence)) && (arrayList = textShareApps) != null) {
                        arrayList.add(next.activityInfo.packageName);
                    }
                }
            }
        }
    }

    public final String getAppLink(String str) {
        if (str == null) {
            return AdParameterKeys.SECTION_ID;
        }
        CharSequence charSequence = str;
        boolean z = false;
        if (StringsKt.contains$default(charSequence, (CharSequence) "mail", false, 2, (Object) null) || StringsKt.contains$default(charSequence, (CharSequence) "android.gm", false, 2, (Object) null)) {
            return "email";
        }
        if (StringsKt.contains$default(charSequence, (CharSequence) "facebook", false, 2, (Object) null)) {
            return "fb";
        }
        if (StringsKt.contains$default(charSequence, (CharSequence) "twitter", false, 2, (Object) null)) {
            return "tw";
        }
        if (StringsKt.contains$default(charSequence, (CharSequence) "linkedin", false, 2, (Object) null)) {
            return "lin";
        }
        if (!StringsKt.contains$default(charSequence, (CharSequence) "com.google.android.apps.messaging", false, 2, (Object) null) && !StringsKt.contains$default(charSequence, (CharSequence) ShareConstants.WEB_DIALOG_PARAM_MESSAGE, false, 2, (Object) null) && !StringsKt.contains$default(charSequence, (CharSequence) "sms", false, 2, (Object) null) && !StringsKt.contains$default(charSequence, (CharSequence) "mms", false, 2, (Object) null)) {
            ArrayList<String> arrayList = textShareApps;
            if (arrayList != null) {
                z = arrayList.contains(str);
            }
            if (z) {
                return "text";
            }
            return AdParameterKeys.SECTION_ID;
        }
        return "text";
    }
}
