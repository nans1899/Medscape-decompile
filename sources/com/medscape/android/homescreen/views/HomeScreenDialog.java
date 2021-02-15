package com.medscape.android.homescreen.views;

import android.view.View;
import androidx.core.app.NotificationCompat;
import com.facebook.share.internal.ShareConstants;
import com.medscape.android.R;
import com.medscape.android.Settings;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0018\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\fH\u0002J\u0012\u0010\r\u001a\u00020\f2\b\b\u0001\u0010\u000f\u001a\u00020\u0010H\u0002J\u0016\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00020\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0016"}, d2 = {"Lcom/medscape/android/homescreen/views/HomeScreenDialog;", "", "home", "Lcom/medscape/android/homescreen/views/HomeScreenActivity;", "mRootView", "Landroid/view/View;", "(Lcom/medscape/android/homescreen/views/HomeScreenActivity;Landroid/view/View;)V", "getHome", "()Lcom/medscape/android/homescreen/views/HomeScreenActivity;", "getMRootView", "()Landroid/view/View;", "getDataUpdateMsg", "", "getString", "saveString", "id", "", "showDialogs", "", "msgID", "message", "Companion", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: HomeScreenDialog.kt */
public final class HomeScreenDialog {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final int SHOW_DIALOG_AD_BLOCKER = 12;
    public static final int SHOW_DIALOG_CLINICAL_REFERENCE_DOWNLOAD_REQUEST = 1;
    public static final int SHOW_DIALOG_DRUG_DATA_DOWNLOAD_REQUEST = 5;
    public static final int SHOW_DIALOG_FAILED_READ_LEGAL = 8;
    public static final int SHOW_DIALOG_FORCED_CLINICAL_REFERENCE_DOWNLOAD_REQUEST = 2;
    public static final int SHOW_DIALOG_FORCED_DRUG_DATA_DOWNLOAD_REQUEST = 7;
    public static final int SHOW_DIALOG_NETWORK_RETRY = 13;
    public static final int SHOW_DIALOG_OPTIONAL_DRUG_DATA_DOWNLOAD_REQUEST = 6;
    public static final int SHOW_DIALOG_VER_XML_RETRY = 11;
    private final HomeScreenActivity home;
    private final View mRootView;

    public HomeScreenDialog(HomeScreenActivity homeScreenActivity, View view) {
        Intrinsics.checkNotNullParameter(homeScreenActivity, "home");
        Intrinsics.checkNotNullParameter(view, "mRootView");
        this.home = homeScreenActivity;
        this.mRootView = view;
    }

    public final HomeScreenActivity getHome() {
        return this.home;
    }

    public final View getMRootView() {
        return this.mRootView;
    }

    public final void showDialogs(int i, String str) {
        Intrinsics.checkNotNullParameter(str, ShareConstants.WEB_DIALOG_PARAM_MESSAGE);
        this.home.runOnUiThread(new HomeScreenDialog$showDialogs$1(this, i, str));
    }

    /* access modifiers changed from: private */
    public final String getString(int i) {
        String string = this.home.getString(i);
        Intrinsics.checkNotNullExpressionValue(string, "home.getString(id)");
        return string;
    }

    /* access modifiers changed from: private */
    public final String getDataUpdateMsg(String str, String str2) {
        String setting = Settings.singleton(this.home).getSetting(str, "");
        Intrinsics.checkNotNullExpressionValue(setting, NotificationCompat.CATEGORY_MESSAGE);
        if (!StringsKt.isBlank(setting)) {
            Settings.singleton(this.home).saveSetting(str2, "");
        } else {
            setting = getString(R.string.data_update_default_message);
        }
        Intrinsics.checkNotNullExpressionValue(setting, NotificationCompat.CATEGORY_MESSAGE);
        return setting;
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\t\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/medscape/android/homescreen/views/HomeScreenDialog$Companion;", "", "()V", "SHOW_DIALOG_AD_BLOCKER", "", "SHOW_DIALOG_CLINICAL_REFERENCE_DOWNLOAD_REQUEST", "SHOW_DIALOG_DRUG_DATA_DOWNLOAD_REQUEST", "SHOW_DIALOG_FAILED_READ_LEGAL", "SHOW_DIALOG_FORCED_CLINICAL_REFERENCE_DOWNLOAD_REQUEST", "SHOW_DIALOG_FORCED_DRUG_DATA_DOWNLOAD_REQUEST", "SHOW_DIALOG_NETWORK_RETRY", "SHOW_DIALOG_OPTIONAL_DRUG_DATA_DOWNLOAD_REQUEST", "SHOW_DIALOG_VER_XML_RETRY", "medscape_release"}, k = 1, mv = {1, 4, 0})
    /* compiled from: HomeScreenDialog.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }
}
