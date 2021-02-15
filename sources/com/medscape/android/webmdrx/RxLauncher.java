package com.medscape.android.webmdrx;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.FragmentActivity;
import com.facebook.share.internal.ShareConstants;
import com.medscape.android.activity.search.RecentlyViewedSuggestionHelper;
import com.medscape.android.drugs.helper.SearchHelper;
import com.medscape.android.webmdrx.api.RxApi;
import com.webmd.webmdrx.fragments.ErrorFragmentDialog;
import com.webmd.webmdrx.tasks.TaskRequestHelper;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lcom/medscape/android/webmdrx/RxLauncher;", "", "()V", "Companion", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: RxLauncher.kt */
public final class RxLauncher {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J(\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\b\u0010\b\u001a\u0004\u0018\u00010\u00062\u0006\u0010\t\u001a\u00020\nJ*\u0010\u000b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\f2\u0006\u0010\u0007\u001a\u00020\u00062\b\u0010\b\u001a\u0004\u0018\u00010\u00062\b\u0010\r\u001a\u0004\u0018\u00010\u0006J \u0010\u000e\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\u00062\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011¨\u0006\u0012"}, d2 = {"Lcom/medscape/android/webmdrx/RxLauncher$Companion;", "", "()V", "launchRxDrug", "", "title", "", "genericID", "contentID", "activity", "Landroidx/fragment/app/FragmentActivity;", "saveToRecentlyViewed", "Landroid/app/Activity;", "drugName", "showErrorDialogFragment", "message", "action", "Landroid/content/Intent;", "medscape_release"}, k = 1, mv = {1, 4, 0})
    /* compiled from: RxLauncher.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final void launchRxDrug(String str, String str2, String str3, FragmentActivity fragmentActivity) {
            String str4 = str;
            String str5 = str2;
            FragmentActivity fragmentActivity2 = fragmentActivity;
            Intrinsics.checkNotNullParameter(str4, "title");
            Intrinsics.checkNotNullParameter(str5, "genericID");
            Intrinsics.checkNotNullParameter(fragmentActivity2, "activity");
            long currentTimeMillis = System.currentTimeMillis();
            String clientSecretHashForTimestamp = TaskRequestHelper.getClientSecretHashForTimestamp(currentTimeMillis, "8be2e6a4-8099-482c-8dac-ccd022581419", "3454df96-c7a5-47bb-a74e-890fb3c30a0d");
            Map hashMap = new HashMap();
            Intrinsics.checkNotNullExpressionValue(clientSecretHashForTimestamp, "secretHash");
            CharSequence charSequence = clientSecretHashForTimestamp;
            int length = charSequence.length() - 1;
            int i = 0;
            boolean z = false;
            while (i <= length) {
                boolean z2 = Intrinsics.compare((int) charSequence.charAt(!z ? i : length), 32) <= 0;
                if (!z) {
                    if (!z2) {
                        z = true;
                    } else {
                        i++;
                    }
                } else if (!z2) {
                    break;
                } else {
                    length--;
                }
            }
            hashMap.put("enc_data", charSequence.subSequence(i, length + 1).toString());
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            String format = String.format("%s", Arrays.copyOf(new Object[]{Long.valueOf(currentTimeMillis)}, 1));
            Intrinsics.checkNotNullExpressionValue(format, "java.lang.String.format(format, *args)");
            hashMap.put("timestamp", format);
            hashMap.put("client_id", "3454df96-c7a5-47bb-a74e-890fb3c30a0d");
            hashMap.put("Content-Type", "application/json; charset=utf-8");
            RxApi.Companion.create().findDrug(hashMap, str5).enqueue(new RxLauncher$Companion$launchRxDrug$2(fragmentActivity2, str3, str5, str4));
        }

        public final void showErrorDialogFragment(FragmentActivity fragmentActivity, String str, Intent intent) {
            Intrinsics.checkNotNullParameter(fragmentActivity, "activity");
            Intrinsics.checkNotNullParameter(str, ShareConstants.WEB_DIALOG_PARAM_MESSAGE);
            ErrorFragmentDialog errorFragmentDialog = new ErrorFragmentDialog();
            errorFragmentDialog.setErrorMessage(str);
            if (intent != null) {
                errorFragmentDialog.setAction(intent);
            }
            try {
                errorFragmentDialog.show(fragmentActivity.getSupportFragmentManager(), "errorDialog");
            } catch (IllegalStateException e) {
                e.printStackTrace();
            }
        }

        public final void saveToRecentlyViewed(Activity activity, String str, String str2, String str3) {
            Intrinsics.checkNotNullParameter(activity, "activity");
            Intrinsics.checkNotNullParameter(str, "genericID");
            CharSequence charSequence = str3;
            if (!(charSequence == null || StringsKt.isBlank(charSequence))) {
                Bundle bundle = new Bundle(3);
                bundle.putString("type", SearchHelper.TYPE_PRICING);
                bundle.putString("genericId", str);
                bundle.putString("contentId", str2);
                RecentlyViewedSuggestionHelper.addToRecentlyViewed(activity, str3, bundle);
            }
        }
    }
}
