package com.medscape.android.webmdrx;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.FragmentActivity;
import com.appboy.Constants;
import com.medscape.android.BI.omniture.OmnitureManager;
import com.medscape.android.R;
import com.medscape.android.util.Util;
import com.medscape.android.util.ViewHelper;
import com.medscape.android.webmdrx.RxLauncher;
import com.medscape.android.webmdrx.model.RxData;
import com.medscape.android.webmdrx.model.RxDrugData;
import com.wbmd.wbmddrugscommons.model.DrugMonograph;
import com.webmd.wbmdomnituremanager.WBMDOmnitureManager;
import com.webmd.webmdrx.activities.PrescriptionDetailsActivity;
import com.webmd.webmdrx.omnitureextensions.RxOmnitureSender;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000)\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u001e\u0010\u0003\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J$\u0010\t\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00020\u00062\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00020\u000bH\u0016¨\u0006\f"}, d2 = {"com/medscape/android/webmdrx/RxLauncher$Companion$launchRxDrug$2", "Lretrofit2/Callback;", "Lcom/medscape/android/webmdrx/model/RxDrugData;", "onFailure", "", "call", "Lretrofit2/Call;", "t", "", "onResponse", "response", "Lretrofit2/Response;", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: RxLauncher.kt */
public final class RxLauncher$Companion$launchRxDrug$2 implements Callback<RxDrugData> {
    final /* synthetic */ FragmentActivity $activity;
    final /* synthetic */ String $contentID;
    final /* synthetic */ String $genericID;
    final /* synthetic */ String $title;

    RxLauncher$Companion$launchRxDrug$2(FragmentActivity fragmentActivity, String str, String str2, String str3) {
        this.$activity = fragmentActivity;
        this.$contentID = str;
        this.$genericID = str2;
        this.$title = str3;
    }

    public void onFailure(Call<RxDrugData> call, Throwable th) {
        Intrinsics.checkNotNullParameter(call, NotificationCompat.CATEGORY_CALL);
        Intrinsics.checkNotNullParameter(th, Constants.APPBOY_PUSH_TITLE_KEY);
        View findById = ViewHelper.findById((Activity) this.$activity, 16908301);
        if (findById != null) {
            findById.setVisibility(8);
        }
        int i = Util.isOnline(this.$activity) ? R.string.pricing_not_available : R.string.error_connection_required;
        RxLauncher.Companion companion = RxLauncher.Companion;
        FragmentActivity fragmentActivity = this.$activity;
        String string = fragmentActivity.getResources().getString(i);
        Intrinsics.checkNotNullExpressionValue(string, "activity.resources.getString(errorId)");
        companion.showErrorDialogFragment(fragmentActivity, string, (Intent) null);
    }

    public void onResponse(Call<RxDrugData> call, Response<RxDrugData> response) {
        RxDrugData body;
        RxData data;
        String gp10_s;
        Intrinsics.checkNotNullParameter(call, NotificationCompat.CATEGORY_CALL);
        Intrinsics.checkNotNullParameter(response, "response");
        View findById = ViewHelper.findById((Activity) this.$activity, 16908301);
        if (findById != null) {
            findById.setVisibility(8);
        }
        if (!(!response.isSuccessful() || (body = response.body()) == null || (data = body.getData()) == null || (gp10_s = data.getGp10_s()) == null)) {
            if (gp10_s.length() > 0) {
                RxOmnitureSender.Companion.setProfessional(this.$activity.getResources().getBoolean(R.bool.rx_is_professional));
                WBMDOmnitureManager wBMDOmnitureManager = WBMDOmnitureManager.shared;
                Intrinsics.checkNotNullExpressionValue(wBMDOmnitureManager, "WBMDOmnitureManager.shared");
                wBMDOmnitureManager.setLastSentPage(OmnitureManager.get().getmCurrentPageName());
                Intent intent = new Intent(this.$activity, PrescriptionDetailsActivity.class);
                DrugMonograph drugMonograph = new DrugMonograph();
                drugMonograph.gp10 = gp10_s;
                drugMonograph.tDrugName = data.getTitle();
                drugMonograph.FDB_id = data.getId();
                drugMonograph.professionalContentID = this.$contentID;
                intent.putExtra("drugMonograph", drugMonograph);
                RxLauncher.Companion.saveToRecentlyViewed(this.$activity, this.$genericID, this.$contentID, this.$title);
                this.$activity.startActivity(intent);
                return;
            }
        }
        RxLauncher.Companion companion = RxLauncher.Companion;
        FragmentActivity fragmentActivity = this.$activity;
        String string = fragmentActivity.getResources().getString(R.string.pricing_not_available);
        Intrinsics.checkNotNullExpressionValue(string, "activity.resources.getSt…ng.pricing_not_available)");
        companion.showErrorDialogFragment(fragmentActivity, string, (Intent) null);
    }
}
