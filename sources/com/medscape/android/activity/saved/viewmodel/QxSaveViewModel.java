package com.medscape.android.activity.saved.viewmodel;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;
import androidx.lifecycle.ViewModel;
import com.medscape.android.Constants;
import com.medscape.android.MedscapeMenu;
import com.medscape.android.R;
import com.medscape.android.activity.calc.model.CalcArticle;
import com.medscape.android.activity.search.RecentlyViewedSuggestionHelper;
import com.medscape.android.appboy.AppboyEventsHandler;
import com.medscape.android.auth.AuthenticationManager;
import com.medscape.android.drugs.helper.SearchHelper;
import com.medscape.android.util.LogUtil;
import com.medscape.android.util.StringUtil;
import com.medscape.android.util.Util;
import com.medscape.android.util.constants.AppboyConstants;
import com.wbmd.qxcalculator.managers.ContentDataManager;
import com.wbmd.qxcalculator.model.contentItems.common.ContentItem;
import com.wbmd.qxcalculator.model.db.DBContentItem;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J'\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\r\u001a\u00020\u000eH\u0000¢\u0006\u0002\b\u000fJ\u001f\u0010\u0010\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0000¢\u0006\u0002\b\u0011J\"\u0010\u0012\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\b\u0010\u0013\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0002J \u0010\u0017\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0002J\u0018\u0010\u0018\u001a\u00020\u00192\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0013\u001a\u00020\u0014H\u0002J\u001f\u0010\u001a\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0000¢\u0006\u0002\b\u001bR\u0014\u0010\u0003\u001a\u00020\u0004XD¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u001c"}, d2 = {"Lcom/medscape/android/activity/saved/viewmodel/QxSaveViewModel;", "Landroidx/lifecycle/ViewModel;", "()V", "TAG", "", "getTAG", "()Ljava/lang/String;", "changeSaveIcon", "", "activity", "Landroid/app/Activity;", "contentItem", "Lcom/wbmd/qxcalculator/model/contentItems/common/ContentItem;", "favMenuItem", "Landroid/view/MenuItem;", "changeSaveIcon$medscape_release", "onOptionsItemSelected", "onOptionsItemSelected$medscape_release", "removeCalculator", "crArticle", "Lcom/medscape/android/activity/calc/model/CalcArticle;", "dbContentItem", "Lcom/wbmd/qxcalculator/model/db/DBContentItem;", "saveCalculator", "saveInfo", "", "saveToRecentlyViewed", "saveToRecentlyViewed$medscape_release", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: QxSaveViewModel.kt */
public final class QxSaveViewModel extends ViewModel {
    private final String TAG = "QxSaveViewModel";

    public final String getTAG() {
        return this.TAG;
    }

    public final void onOptionsItemSelected$medscape_release(Activity activity, ContentItem contentItem) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        if (contentItem != null) {
            if (contentItem.isFavorite != null) {
                contentItem.isFavorite = Boolean.valueOf(!contentItem.isFavorite.booleanValue());
            } else {
                contentItem.isFavorite = true;
            }
            Boolean bool = contentItem.isFavorite;
            Intrinsics.checkNotNullExpressionValue(bool, "contentItem.isFavorite");
            if (bool.booleanValue()) {
                Toast.makeText(activity, activity.getString(R.string.calculator_saved), 0).show();
            }
            DBContentItem contentItemForIdentifier = ContentDataManager.getInstance().getContentItemForIdentifier(contentItem.identifier);
            CalcArticle calcArticleFromContentItem = Util.getCalcArticleFromContentItem(activity, contentItem);
            if (!(contentItemForIdentifier == null || contentItemForIdentifier.getIsFavorite() == null)) {
                Boolean isFavorite = contentItemForIdentifier.getIsFavorite();
                Intrinsics.checkNotNullExpressionValue(isFavorite, "dbContentItem.isFavorite");
                if (isFavorite.booleanValue()) {
                    removeCalculator(activity, calcArticleFromContentItem, contentItemForIdentifier);
                    return;
                }
            }
            Intrinsics.checkNotNullExpressionValue(calcArticleFromContentItem, "crArticle");
            Intrinsics.checkNotNullExpressionValue(contentItemForIdentifier, "dbContentItem");
            saveCalculator(activity, calcArticleFromContentItem, contentItemForIdentifier);
        }
    }

    private final void saveCalculator(Activity activity, CalcArticle calcArticle, DBContentItem dBContentItem) {
        if (saveInfo(activity, calcArticle)) {
            dBContentItem.setIsFavorite(true);
            dBContentItem.update();
            Context context = activity;
            AppboyEventsHandler.logDailyEvent(context, AppboyConstants.APPBOY_EVENT_CALC_SAVED, activity);
            MedscapeMenu.sendSaveBIPings(context, Constants.OMNITURE_CHANNEL_REFERENCE, Constants.OMNITURE_MLINK_CALC);
        }
    }

    private final boolean saveInfo(Activity activity, CalcArticle calcArticle) {
        try {
            AuthenticationManager instance = AuthenticationManager.getInstance(activity);
            Intrinsics.checkNotNullExpressionValue(instance, "AuthenticationManager.getInstance(activity)");
            String maskedGuid = instance.getMaskedGuid();
            if (StringUtil.isNotEmpty(maskedGuid)) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("isSaved", 1);
                contentValues.put("title", calcArticle.getTitle());
                contentValues.put("type", Integer.valueOf(calcArticle.getType()));
                contentValues.put("userGuid", maskedGuid);
                contentValues.put("calcId", calcArticle.getCalcId());
                Uri insert = activity.getContentResolver().insert(CalcArticle.CalcArticles.CONTENT_URI, contentValues);
                LogUtil.e(this.TAG, "Save crArticle.setTitle = %s", calcArticle.getTitle());
                if (insert != null) {
                    LogUtil.e(this.TAG, "Save Article  urp = %s", insert.toString());
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0033, code lost:
        if (r4.booleanValue() == false) goto L_0x0035;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void changeSaveIcon$medscape_release(android.app.Activity r3, com.wbmd.qxcalculator.model.contentItems.common.ContentItem r4, android.view.MenuItem r5) {
        /*
            r2 = this;
            java.lang.String r0 = "activity"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
            java.lang.String r0 = "favMenuItem"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r0)
            com.wbmd.qxcalculator.managers.ContentDataManager r0 = com.wbmd.qxcalculator.managers.ContentDataManager.getInstance()
            if (r4 == 0) goto L_0x0013
            java.lang.String r1 = r4.identifier
            goto L_0x0014
        L_0x0013:
            r1 = 0
        L_0x0014:
            com.wbmd.qxcalculator.model.db.DBContentItem r0 = r0.getContentItemForIdentifier(r1)
            android.content.Context r3 = (android.content.Context) r3
            com.medscape.android.activity.calc.model.CalcArticle r3 = com.medscape.android.util.Util.getCalcArticleFromContentItem(r3, r4)
            if (r0 == 0) goto L_0x0035
            java.lang.Boolean r4 = r0.getIsFavorite()
            if (r4 == 0) goto L_0x0035
            java.lang.Boolean r4 = r0.getIsFavorite()
            java.lang.String r0 = "dbContentItem.isFavorite"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r4, r0)
            boolean r4 = r4.booleanValue()
            if (r4 != 0) goto L_0x0040
        L_0x0035:
            java.lang.String r4 = "calcArticle"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r4)
            boolean r3 = r3.isSaved()
            if (r3 == 0) goto L_0x0047
        L_0x0040:
            r3 = 2131231202(0x7f0801e2, float:1.8078478E38)
            r5.setIcon(r3)
            goto L_0x004d
        L_0x0047:
            r3 = 2131231318(0x7f080256, float:1.8078714E38)
            r5.setIcon(r3)
        L_0x004d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.medscape.android.activity.saved.viewmodel.QxSaveViewModel.changeSaveIcon$medscape_release(android.app.Activity, com.wbmd.qxcalculator.model.contentItems.common.ContentItem, android.view.MenuItem):void");
    }

    public final void saveToRecentlyViewed$medscape_release(Activity activity, ContentItem contentItem) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        if (contentItem != null) {
            Bundle bundle = new Bundle();
            Context context = activity;
            CalcArticle calcArticleFromContentItem = Util.getCalcArticleFromContentItem(context, contentItem);
            bundle.putString("type", SearchHelper.TYPE_CALCULATOR);
            bundle.putSerializable(RecentlyViewedSuggestionHelper.META_CLINICAL_REF_ARTICLE, calcArticleFromContentItem);
            RecentlyViewedSuggestionHelper.addToRecentlyViewed(context, contentItem.name, bundle);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0038 A[Catch:{ Exception -> 0x004f }] */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0043 A[Catch:{ Exception -> 0x004f }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void removeCalculator(android.app.Activity r9, com.medscape.android.activity.calc.model.CalcArticle r10, com.wbmd.qxcalculator.model.db.DBContentItem r11) {
        /*
            r8 = this;
            r0 = 0
            android.content.ContentResolver r1 = r9.getContentResolver()     // Catch:{ Exception -> 0x004f }
            android.net.Uri r2 = com.medscape.android.activity.calc.model.CalcArticle.CalcArticles.CONTENT_URI     // Catch:{ Exception -> 0x004f }
            java.lang.String r3 = "calcId like ? OR title like ?"
            r4 = 2
            java.lang.String[] r4 = new java.lang.String[r4]     // Catch:{ Exception -> 0x004f }
            java.lang.String r5 = "null cannot be cast to non-null type kotlin.CharSequence"
            r6 = 0
            if (r10 == 0) goto L_0x002a
            java.lang.String r7 = r10.getCalcId()     // Catch:{ Exception -> 0x004f }
            if (r7 == 0) goto L_0x002a
            if (r7 == 0) goto L_0x0024
            java.lang.CharSequence r7 = (java.lang.CharSequence) r7     // Catch:{ Exception -> 0x004f }
            java.lang.CharSequence r7 = kotlin.text.StringsKt.trim((java.lang.CharSequence) r7)     // Catch:{ Exception -> 0x004f }
            java.lang.String r7 = r7.toString()     // Catch:{ Exception -> 0x004f }
            goto L_0x002b
        L_0x0024:
            java.lang.NullPointerException r10 = new java.lang.NullPointerException     // Catch:{ Exception -> 0x004f }
            r10.<init>(r5)     // Catch:{ Exception -> 0x004f }
            throw r10     // Catch:{ Exception -> 0x004f }
        L_0x002a:
            r7 = r6
        L_0x002b:
            r4[r0] = r7     // Catch:{ Exception -> 0x004f }
            r7 = 1
            if (r10 == 0) goto L_0x0049
            java.lang.String r10 = r10.getTitle()     // Catch:{ Exception -> 0x004f }
            if (r10 == 0) goto L_0x0049
            if (r10 == 0) goto L_0x0043
            java.lang.CharSequence r10 = (java.lang.CharSequence) r10     // Catch:{ Exception -> 0x004f }
            java.lang.CharSequence r10 = kotlin.text.StringsKt.trim((java.lang.CharSequence) r10)     // Catch:{ Exception -> 0x004f }
            java.lang.String r6 = r10.toString()     // Catch:{ Exception -> 0x004f }
            goto L_0x0049
        L_0x0043:
            java.lang.NullPointerException r10 = new java.lang.NullPointerException     // Catch:{ Exception -> 0x004f }
            r10.<init>(r5)     // Catch:{ Exception -> 0x004f }
            throw r10     // Catch:{ Exception -> 0x004f }
        L_0x0049:
            r4[r7] = r6     // Catch:{ Exception -> 0x004f }
            r1.delete(r2, r3, r4)     // Catch:{ Exception -> 0x004f }
            goto L_0x0053
        L_0x004f:
            r10 = move-exception
            r10.printStackTrace()
        L_0x0053:
            java.lang.Boolean r10 = java.lang.Boolean.valueOf(r0)
            r11.setIsFavorite(r10)
            r11.update()
            com.medscape.android.BI.omniture.OmnitureManager r0 = com.medscape.android.BI.omniture.OmnitureManager.get()
            r1 = r9
            android.content.Context r1 = (android.content.Context) r1
            r5 = 0
            java.lang.String r2 = "reference and tools"
            java.lang.String r3 = "save"
            java.lang.String r4 = "delete"
            r0.trackModule(r1, r2, r3, r4, r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.medscape.android.activity.saved.viewmodel.QxSaveViewModel.removeCalculator(android.app.Activity, com.medscape.android.activity.calc.model.CalcArticle, com.wbmd.qxcalculator.model.db.DBContentItem):void");
    }
}
