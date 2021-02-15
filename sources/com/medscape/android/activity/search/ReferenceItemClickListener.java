package com.medscape.android.activity.search;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import androidx.fragment.app.FragmentActivity;
import com.medscape.android.BI.omniture.OmnitureManager;
import com.medscape.android.Constants;
import com.medscape.android.R;
import com.medscape.android.activity.calc.CalcTitleActivity;
import com.medscape.android.activity.calc.helper.CalcOmnitureHelper;
import com.medscape.android.activity.search.model.CRData;
import com.medscape.android.analytics.remoteconfig.reference.ReferenceArticleTOCEnabledManager;
import com.medscape.android.drugs.BrowseByChildClassActivity;
import com.medscape.android.drugs.DrugListActivity;
import com.medscape.android.drugs.DrugMonographMainActivity;
import com.medscape.android.drugs.helper.SearchHelper;
import com.medscape.android.reference.ClinicalReferenceArticleActivity;
import com.medscape.android.reference.ClinicalReferenceArticleLandingActivity;
import com.medscape.android.reference.model.ClinicalReferenceArticle;
import com.medscape.android.search.MedscapeSearchActivity;
import com.medscape.android.util.Util;
import com.wbmd.qxcalculator.LaunchQxCallback;
import com.wbmd.qxcalculator.managers.ContentItemLaunchManager;
import com.wbmd.qxcalculator.model.db.DBContentItem;
import java.util.HashMap;
import java.util.Map;

public class ReferenceItemClickListener implements AdapterView.OnItemClickListener, LaunchQxCallback {
    String calcPvid = "";
    public boolean isClickable = true;
    Activity mActivity;
    Context mContext;
    OpenFormularyListener mFormularyListener;
    String mQueryText;
    OpenRxListener mRxListener;

    public interface OpenFormularyListener {
        void openFormulary(int i);
    }

    public interface OpenRxListener {
        void openRxPrescription(String str, String str2, String str3);
    }

    public ReferenceItemClickListener(Activity activity) {
        this.mActivity = activity;
        this.mContext = activity;
        this.isClickable = true;
    }

    public ReferenceItemClickListener(Context context) {
        this.mContext = context;
        this.isClickable = true;
    }

    public void onItemClicked(SearchResultsListAdapter searchResultsListAdapter, View view, int i, String str) {
        CRData cRData = (CRData) searchResultsListAdapter.getItem(i);
        if (cRData.isExternalSearchDriver()) {
            launchExternalSearch();
        } else if (this.isClickable) {
            handleSelectedData(cRData, i, str);
        }
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [android.widget.AdapterView<?>, android.widget.AdapterView] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onItemClick(android.widget.AdapterView<?> r1, android.view.View r2, int r3, long r4) {
        /*
            r0 = this;
            android.widget.Adapter r1 = r1.getAdapter()
            java.lang.Object r1 = r1.getItem(r3)
            com.medscape.android.activity.search.model.CRData r1 = (com.medscape.android.activity.search.model.CRData) r1
            boolean r2 = r1.isExternalSearchDriver()
            if (r2 == 0) goto L_0x0014
            r0.launchExternalSearch()
            goto L_0x0025
        L_0x0014:
            boolean r2 = r0.isClickable
            if (r2 == 0) goto L_0x0025
            com.medscape.android.activity.search.model.SearchSuggestionMsg r2 = r1.getSearchSuggestionMsg()
            if (r2 != 0) goto L_0x0025
            int r3 = r3 + -1
            java.lang.String r2 = r0.mQueryText
            r0.handleSelectedData(r1, r3, r2)
        L_0x0025:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.medscape.android.activity.search.ReferenceItemClickListener.onItemClick(android.widget.AdapterView, android.view.View, int, long):void");
    }

    private void launchExternalSearch() {
        Activity activity = this.mActivity;
        if (activity instanceof MedscapeSearchActivity) {
            ((MedscapeSearchActivity) activity).searchWithQueryAndSubmit(Constants.SEARCH_REFERENCE, 0);
            ((MedscapeSearchActivity) this.mActivity).sendOmniturePageView("search-online");
        }
    }

    private void handleSelectedData(CRData cRData, int i, String str) {
        Intent intent;
        if (cRData != null && cRData.getType() != null) {
            if (cRData.getType().equals("T")) {
                ClinicalReferenceArticle clinicalReferenceArticle = new ClinicalReferenceArticle();
                clinicalReferenceArticle.setArticleId(cRData.getCid());
                clinicalReferenceArticle.setTitle(cRData.getTitle());
                try {
                    clinicalReferenceArticle.setType(cRData.getCrType());
                } catch (Exception unused) {
                }
                OmnitureManager.get().markModule("srch-results", "ref", (Map<String, Object>) null);
                if (new ReferenceArticleTOCEnabledManager().getRefTOCData()) {
                    intent = new Intent(this.mContext, ClinicalReferenceArticleLandingActivity.class);
                } else {
                    intent = new Intent(this.mContext, ClinicalReferenceArticleActivity.class);
                }
                intent.putExtra("article", clinicalReferenceArticle);
                this.mContext.startActivity(intent);
            } else if (cRData.getType().equals(SearchHelper.TYPE_DRUG)) {
                OpenFormularyListener openFormularyListener = this.mFormularyListener;
                if (openFormularyListener != null) {
                    openFormularyListener.openFormulary(cRData.getCid());
                } else {
                    OpenRxListener openRxListener = this.mRxListener;
                    if (openRxListener != null) {
                        openRxListener.openRxPrescription(cRData.getTitle(), String.valueOf(cRData.getGenericId()), String.valueOf(cRData.getCid()));
                    } else {
                        this.mContext.startActivity(new Intent(this.mContext, DrugMonographMainActivity.class).putExtra(Constants.EXTRA_CONTENT_ID, cRData.getCid()).putExtra("drugName", cRData.getTitle()).putExtra(Constants.EXTRA_QUERY, str));
                        Activity activity = this.mActivity;
                        if (activity != null) {
                            activity.overridePendingTransition(R.anim.push_left_in, R.anim.push_none);
                        }
                    }
                }
            } else if (cRData.getType().equals(SearchHelper.TYPE_CALCULATOR)) {
                CalcOmnitureHelper calcOmnitureHelper = CalcOmnitureHelper.INSTANCE;
                Activity activity2 = this.mActivity;
                this.calcPvid = calcOmnitureHelper.sendOmnitureCall(activity2, "main-srch-results", "" + Util.getMLinkForOmniture(cRData.getContentItem()), Util.getCalcPageNameForOmniture(this.mActivity, cRData.getContentItem()));
                saveToRecentlyViewed(cRData);
                ContentItemLaunchManager.getInstance().launchContentItem(cRData.getContentItem(), (FragmentActivity) this.mContext, (LaunchQxCallback) this, (Intent) null);
            } else if (SearchHelper.TYPE_DRUG_CLASS.equals(cRData.getType())) {
                launchScreenForDrugClass(cRData);
            } else if (SearchHelper.TYPE_CALCULATOR_FOLDER.equals(cRData.getType())) {
                this.mContext.startActivity(new Intent(this.mContext, CalcTitleActivity.class).putExtra("folderId", cRData.getCalcId()).putExtra("folderName", cRData.getTitle()));
            }
            if (!SearchHelper.TYPE_DRUG_CLASS.equals(cRData.getType())) {
                HashMap hashMap = new HashMap();
                hashMap.put("wapp.querytext", cRData.getTitle());
                OmnitureManager omnitureManager = OmnitureManager.get();
                omnitureManager.markModule("search-results", "" + (i + 1), hashMap);
                return;
            }
            OmnitureManager.get().clearCurrentModuleData();
        }
    }

    public void launchScreenForDrugClass(CRData cRData) {
        Intent intent = new Intent();
        if (cRData.getSource() == 4) {
            intent.setClass(this.mContext, DrugListActivity.class);
            intent.putExtra("classId", cRData.getCid());
            intent.putExtra("className", cRData.getTitle());
        } else {
            intent.setClass(this.mContext, BrowseByChildClassActivity.class);
            intent.putExtra("parentId", cRData.getCid());
            intent.putExtra("className", cRData.getTitle());
        }
        this.mContext.startActivity(intent);
    }

    public void setFormularyListener(OpenFormularyListener openFormularyListener) {
        this.mFormularyListener = openFormularyListener;
    }

    public void setRxListener(OpenRxListener openRxListener) {
        this.mRxListener = openRxListener;
    }

    public void setClickability(boolean z) {
        this.isClickable = z;
    }

    private void saveToRecentlyViewed(CRData cRData) {
        Bundle bundle = new Bundle();
        bundle.putString("type", SearchHelper.TYPE_CALCULATOR);
        bundle.putString("calcId", cRData.getCalcId());
        RecentlyViewedSuggestionHelper.addToRecentlyViewed(this.mActivity, cRData.getTitle(), bundle);
    }

    public void onQxItemClicked(DBContentItem dBContentItem, Bundle bundle) {
        bundle.putString("pvid", this.calcPvid);
        Util.openQxItem(this.mActivity, dBContentItem, bundle);
    }
}
