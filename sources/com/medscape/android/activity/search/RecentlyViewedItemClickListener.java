package com.medscape.android.activity.search;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import androidx.fragment.app.FragmentActivity;
import com.medscape.android.BI.omniture.OmnitureManager;
import com.medscape.android.Constants;
import com.medscape.android.activity.calc.helper.CalcOmnitureHelper;
import com.medscape.android.activity.calc.model.CalcArticle;
import com.medscape.android.activity.cme.CMEHelper;
import com.medscape.android.activity.webviews.WebviewUtil;
import com.medscape.android.analytics.remoteconfig.reference.ReferenceArticleTOCEnabledManager;
import com.medscape.android.drugs.DrugMonographMainActivity;
import com.medscape.android.drugs.helper.SearchHelper;
import com.medscape.android.parser.model.Article;
import com.medscape.android.reference.ClinicalReferenceArticleActivity;
import com.medscape.android.reference.ClinicalReferenceArticleLandingActivity;
import com.medscape.android.reference.model.ClinicalReferenceArticle;
import com.medscape.android.util.Util;
import com.medscape.android.webmdrx.RxLauncher;
import com.wbmd.qxcalculator.LaunchQxCallback;
import com.wbmd.qxcalculator.managers.ContentDataManager;
import com.wbmd.qxcalculator.managers.ContentItemLaunchManager;
import com.wbmd.qxcalculator.model.db.DBContentItem;
import com.wbmd.qxcalculator.util.legacy.ContentParser;
import com.wbmd.wbmdcommons.extensions.StringExtensions;
import com.wbmd.wbmdcommons.logging.Trace;
import java.util.Map;

public class RecentlyViewedItemClickListener implements AdapterView.OnItemClickListener, LaunchQxCallback {
    private static final String TAG = RecentlyViewedItemClickListener.class.getSimpleName();
    String calcPvid;
    Activity mActivity;
    View rootView;

    public RecentlyViewedItemClickListener(Activity activity) {
        this.mActivity = activity;
    }

    public void onItemClicked(RecentlyViewedItemsAdapter recentlyViewedItemsAdapter, View view, int i) {
        this.rootView = view;
        recentlyViewedItemsAdapter.getCursor().moveToPosition(i);
        launchAppropriateScreen(recentlyViewedItemsAdapter.getCursor());
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [android.widget.AdapterView<?>, android.widget.AdapterView] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onItemClick(android.widget.AdapterView<?> r1, android.view.View r2, int r3, long r4) {
        /*
            r0 = this;
            r0.rootView = r2
            android.widget.Adapter r1 = r1.getAdapter()
            java.lang.Object r1 = r1.getItem(r3)
            android.database.Cursor r1 = (android.database.Cursor) r1
            r0.launchAppropriateScreen(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.medscape.android.activity.search.RecentlyViewedItemClickListener.onItemClick(android.widget.AdapterView, android.view.View, int, long):void");
    }

    public void launchAppropriateScreen(Cursor cursor) {
        try {
            int columnIndex = cursor.getColumnIndex("suggest_text_1");
            int columnIndex2 = cursor.getColumnIndex("suggest_text_2");
            String string = cursor.getString(columnIndex);
            Bundle decodeMeta = RecentlyViewedSuggestionHelper.decodeMeta(this.mActivity, cursor.getString(columnIndex2));
            String string2 = decodeMeta.getString("type");
            if ("T".equals(string2)) {
                launchClinicalReferenceScreen(decodeMeta);
                OmnitureManager.get().markModule("rcntlyvwd", "ref", (Map<String, Object>) null);
            } else if (SearchHelper.TYPE_DRUG.equals(string2)) {
                launchScreenForDrug(string, decodeMeta);
                OmnitureManager.get().markModule("rcntlyvwd", "ref", (Map<String, Object>) null);
            } else if (SearchHelper.TYPE_CALCULATOR.equals(string2)) {
                launchCalculatorScreen(decodeMeta);
            } else if ("news".equals(string2)) {
                decodeMeta.putInt(WebviewUtil.LOGO_EXTRA, 1);
                WebviewUtil.Companion.launchRecentlySavedWebView(this.mActivity, decodeMeta, true, true, string2);
                OmnitureManager.get().markModule("rcntlyvwd", "news", (Map<String, Object>) null);
            } else if ("education".equals(string2)) {
                Object obj = decodeMeta.get("article");
                if (obj instanceof Article) {
                    CMEHelper.launchCMEArticle(this.mActivity, (Article) obj, false, false);
                    OmnitureManager.get().markModule("rcntlyvwd", "edu", (Map<String, Object>) null);
                }
            } else if (RecentlyViewedSuggestionHelper.TYPE_MEDLINE.equals(string2)) {
                decodeMeta.putInt(WebviewUtil.LOGO_EXTRA, 0);
                WebviewUtil.Companion.launchRecentlySavedWebView(this.mActivity, decodeMeta, false, true, string2);
                OmnitureManager.get().markModule("rcntlyvwd", "med", (Map<String, Object>) null);
            } else if (SearchHelper.TYPE_PRICING.equals(string2)) {
                launchPricingScreen(string, decodeMeta);
                OmnitureManager.get().markModule("rcntlyvwd", "ref", (Map<String, Object>) null);
            }
        } catch (Exception e) {
            Trace.e(TAG, e.getMessage());
        }
    }

    private boolean launchCalculatorScreen(Bundle bundle) {
        String str;
        if (bundle == null) {
            return false;
        }
        CalcArticle calcArticle = (CalcArticle) bundle.getSerializable(RecentlyViewedSuggestionHelper.META_CLINICAL_REF_ARTICLE);
        String string = bundle.getString("calcId");
        DBContentItem contentItemForIdentifier = string != null ? ContentDataManager.getInstance().getContentItemForIdentifier(string) : null;
        if (calcArticle != null) {
            str = calcArticle.getCalcId();
        } else {
            str = contentItemForIdentifier != null ? contentItemForIdentifier.getIdentifier() : "";
        }
        if (str.startsWith(ContentParser.CALCULATOR)) {
            CalcOmnitureHelper calcOmnitureHelper = CalcOmnitureHelper.INSTANCE;
            Activity activity = this.mActivity;
            this.calcPvid = calcOmnitureHelper.sendOmnitureCall(activity, "rcntlyvwd", "drgs", Util.getCalcPageNameForOmniture(activity, str));
            if (calcArticle != null && !Util.findMatchingQxCalcForMedscapeCalc(this.mActivity, calcArticle, this)) {
                Util.showNoCalculatorAvailable(this.mActivity, this.rootView);
                return true;
            } else if (string == null) {
                return true;
            } else {
                if (contentItemForIdentifier != null) {
                    ContentItemLaunchManager.getInstance().launchContentItem(contentItemForIdentifier, (FragmentActivity) this.mActivity);
                    return true;
                }
                Util.showNoCalculatorAvailable(this.mActivity, this.rootView);
                return true;
            }
        } else {
            ContentItemLaunchManager.getInstance().launchContentItem(ContentDataManager.getInstance().getContentItemForIdentifier(str), (FragmentActivity) this.mActivity, (LaunchQxCallback) this, (Intent) null);
            return true;
        }
    }

    private void launchScreenForDrug(String str, Bundle bundle) {
        int i = bundle.getInt("contentId");
        Activity activity = this.mActivity;
        if (activity instanceof RecentlyViewedDrugClickListener) {
            ((RecentlyViewedDrugClickListener) activity).onRecentlyViewedDrugClick(i, str);
        } else {
            activity.startActivity(new Intent(this.mActivity, DrugMonographMainActivity.class).putExtra(Constants.EXTRA_CONTENT_ID, i).putExtra("drugName", str));
        }
    }

    private void launchClinicalReferenceScreen(Bundle bundle) {
        Intent intent;
        ClinicalReferenceArticle clinicalReferenceArticle = (ClinicalReferenceArticle) bundle.getSerializable(RecentlyViewedSuggestionHelper.META_CLINICAL_REF_ARTICLE);
        if (new ReferenceArticleTOCEnabledManager().getRefTOCData()) {
            intent = new Intent(this.mActivity, ClinicalReferenceArticleLandingActivity.class);
        } else {
            intent = new Intent(this.mActivity, ClinicalReferenceArticleActivity.class);
        }
        intent.putExtra("article", clinicalReferenceArticle);
        this.mActivity.startActivity(intent);
    }

    private void launchPricingScreen(String str, Bundle bundle) {
        String string = bundle.getString("genericId");
        String string2 = bundle.getString("contentId");
        if (StringExtensions.isNotEmpty(string)) {
            RxLauncher.Companion.launchRxDrug(str, string, string2, (FragmentActivity) this.mActivity);
        }
    }

    public void onQxItemClicked(DBContentItem dBContentItem, Bundle bundle) {
        bundle.putString("pvid", this.calcPvid);
        Util.openQxItem(this.mActivity, dBContentItem, bundle);
    }
}
