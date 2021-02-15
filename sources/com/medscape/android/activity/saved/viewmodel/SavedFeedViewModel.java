package com.medscape.android.activity.saved.viewmodel;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;
import com.medscape.android.BI.omniture.OmnitureManager;
import com.medscape.android.Constants;
import com.medscape.android.activity.calc.model.CalcArticle;
import com.medscape.android.activity.saved.adapters.SavedItemsAdapter;
import com.medscape.android.activity.saved.adapters.SavedTabLayoutAdapter;
import com.medscape.android.activity.saved.adapters.ViewPagerAdapter;
import com.medscape.android.activity.saved.model.TabLayoutElement;
import com.medscape.android.cache.Cache;
import com.medscape.android.cache.CacheManager;
import com.medscape.android.cache.DrugCache;
import com.medscape.android.landingfeed.model.FeedConstants;
import com.medscape.android.reference.model.ClinicalReferenceArticle;
import com.medscape.android.util.Util;
import com.wbmd.qxcalculator.managers.ContentDataManager;
import com.wbmd.qxcalculator.model.db.DBContentItem;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000¬\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u001b\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u0015\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010t\u001a\u00020u2\u0006\u0010v\u001a\u00020 2\u0006\u0010w\u001a\u00020xH\u0002J\u0018\u0010y\u001a\u00020u2\u0006\u0010z\u001a\u00020 2\u0006\u0010w\u001a\u00020xH\u0002J \u0010{\u001a\u00020u2\u0006\u0010|\u001a\u00020 2\u0006\u0010}\u001a\u00020\u00042\u0006\u0010w\u001a\u00020~H\u0002J\u0018\u0010\u001a\u00020u2\u0006\u0010v\u001a\u00020 2\u0006\u0010w\u001a\u00020xH\u0002J\u001a\u0010\u0001\u001a\u00020u2\u0007\u0010\u0001\u001a\u00020M2\u0006\u0010w\u001a\u00020~H\u0002J\u0019\u0010\u0001\u001a\u00020u2\u0006\u0010v\u001a\u00020 2\u0006\u0010w\u001a\u00020xH\u0002J\u001a\u0010\u0001\u001a\u00020u2\u0007\u0010\u0001\u001a\u00020 2\u0006\u0010w\u001a\u00020xH\u0002J\u0019\u0010\u0001\u001a\u00020u2\u0006\u0010v\u001a\u00020 2\u0006\u0010w\u001a\u00020xH\u0002J\u001a\u0010\u0001\u001a\u00020u2\u0007\u0010\u0001\u001a\u00020\u00042\u0006\u0010w\u001a\u00020~H\u0002J\u0019\u0010\u0001\u001a\u00020u2\b\u0010\u0001\u001a\u00030\u00012\u0006\u0010w\u001a\u00020xJ\u000f\u0010\u0001\u001a\u00020u2\u0006\u0010w\u001a\u00020~J\u0015\u0010\u0001\u001a\b\u0012\u0004\u0012\u00020.0-2\u0006\u0010w\u001a\u00020~J\u0015\u0010\u0001\u001a\b\u0012\u0004\u0012\u00020I0-2\u0006\u0010w\u001a\u00020~J\u0015\u0010\u0001\u001a\b\u0012\u0004\u0012\u00020M0-2\u0006\u0010w\u001a\u00020~J\u0015\u0010\u0001\u001a\b\u0012\u0004\u0012\u00020I0-2\u0006\u0010w\u001a\u00020~J\u0015\u0010\u0001\u001a\b\u0012\u0004\u0012\u00020Q0-2\u0006\u0010w\u001a\u00020~J\u001a\u0010\u0001\u001a\u00020u2\u0006\u0010w\u001a\u00020~2\u0007\u0010\u0001\u001a\u00020\u0004H\u0002R\u0014\u0010\u0003\u001a\u00020\u0004XD¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u0004XD¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006R\u0014\u0010\t\u001a\u00020\u0004XD¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0006R\u0014\u0010\u000b\u001a\u00020\u0004XD¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u0006R\u0014\u0010\r\u001a\u00020\u0004XD¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u0006R\u0014\u0010\u000f\u001a\u00020\u0004XD¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0006R\u0014\u0010\u0011\u001a\u00020\u0004XD¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0006R\u0014\u0010\u0013\u001a\u00020\u0004XD¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0006R\u0014\u0010\u0015\u001a\u00020\u0004XD¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0006R\u0014\u0010\u0017\u001a\u00020\u0004XD¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0006R\u0014\u0010\u0019\u001a\u00020\u0004XD¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0006R\u001a\u0010\u001b\u001a\u00020\u0004X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u0006\"\u0004\b\u001d\u0010\u001eR\u001a\u0010\u001f\u001a\u00020 X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R \u0010%\u001a\b\u0012\u0004\u0012\u00020'0&X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010)\"\u0004\b*\u0010+R \u0010,\u001a\b\u0012\u0004\u0012\u00020.0-X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b/\u00100\"\u0004\b1\u00102R \u00103\u001a\b\u0012\u0004\u0012\u00020.0-X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b4\u00100\"\u0004\b5\u00102R \u00106\u001a\b\u0012\u0004\u0012\u00020.0-X.¢\u0006\u000e\n\u0000\u001a\u0004\b7\u00100\"\u0004\b8\u00102R\u000e\u00109\u001a\u00020:X.¢\u0006\u0002\n\u0000R\u001a\u0010;\u001a\u00020'X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b<\u0010=\"\u0004\b>\u0010?R*\u0010@\u001a\u0012\u0012\u0004\u0012\u00020B0Aj\b\u0012\u0004\u0012\u00020B`CX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bD\u0010E\"\u0004\bF\u0010GR \u0010H\u001a\b\u0012\u0004\u0012\u00020I0-X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bJ\u00100\"\u0004\bK\u00102R \u0010L\u001a\b\u0012\u0004\u0012\u00020M0-X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bN\u00100\"\u0004\bO\u00102R \u0010P\u001a\b\u0012\u0004\u0012\u00020Q0-X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bR\u00100\"\u0004\bS\u00102R\u001a\u0010T\u001a\u00020UX.¢\u0006\u000e\n\u0000\u001a\u0004\bV\u0010W\"\u0004\bX\u0010YR \u0010Z\u001a\b\u0012\u0004\u0012\u00020[0-X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\\\u00100\"\u0004\b]\u00102R \u0010^\u001a\b\u0012\u0004\u0012\u00020[0-X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b_\u00100\"\u0004\b`\u00102R \u0010a\u001a\b\u0012\u0004\u0012\u00020I0-X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bb\u00100\"\u0004\bc\u00102R \u0010d\u001a\b\u0012\u0004\u0012\u00020e0-X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bf\u00100\"\u0004\bg\u00102R\u001a\u0010h\u001a\u00020iX.¢\u0006\u000e\n\u0000\u001a\u0004\bj\u0010k\"\u0004\bl\u0010mR\u001a\u0010n\u001a\u00020oX.¢\u0006\u000e\n\u0000\u001a\u0004\bp\u0010q\"\u0004\br\u0010s¨\u0006\u0001"}, d2 = {"Lcom/medscape/android/activity/saved/viewmodel/SavedFeedViewModel;", "Landroidx/lifecycle/ViewModel;", "()V", "ALL", "", "getALL", "()Ljava/lang/String;", "CALC", "getCALC", "CALC_TITLE", "getCALC_TITLE", "DRUGS", "getDRUGS", "DRUGS_TITLE", "getDRUGS_TITLE", "EDU", "getEDU", "EDU_TITLE", "getEDU_TITLE", "NEWS", "getNEWS", "NEWS_TITLE", "getNEWS_TITLE", "REF", "getREF", "REF_TITLE", "getREF_TITLE", "activeFragment", "getActiveFragment", "setActiveFragment", "(Ljava/lang/String;)V", "activePosition", "", "getActivePosition", "()I", "setActivePosition", "(I)V", "countLiveFlag", "Landroidx/lifecycle/MutableLiveData;", "", "getCountLiveFlag", "()Landroidx/lifecycle/MutableLiveData;", "setCountLiveFlag", "(Landroidx/lifecycle/MutableLiveData;)V", "crConditionsList", "", "Lcom/medscape/android/reference/model/ClinicalReferenceArticle;", "getCrConditionsList", "()Ljava/util/List;", "setCrConditionsList", "(Ljava/util/List;)V", "crProceduresList", "getCrProceduresList", "setCrProceduresList", "crRefList", "getCrRefList", "setCrRefList", "dataSavedSize", "", "editModeActive", "getEditModeActive", "()Z", "setEditModeActive", "(Z)V", "listRecyclerViews", "Ljava/util/ArrayList;", "Landroidx/recyclerview/widget/RecyclerView;", "Lkotlin/collections/ArrayList;", "getListRecyclerViews", "()Ljava/util/ArrayList;", "setListRecyclerViews", "(Ljava/util/ArrayList;)V", "savedCMEList", "Lcom/medscape/android/cache/Cache;", "getSavedCMEList", "setSavedCMEList", "savedCalcList", "Lcom/medscape/android/activity/calc/model/CalcArticle;", "getSavedCalcList", "setSavedCalcList", "savedDrugList", "Lcom/medscape/android/cache/DrugCache;", "getSavedDrugList", "setSavedDrugList", "savedItemsAdapter", "Lcom/medscape/android/activity/saved/adapters/SavedItemsAdapter;", "getSavedItemsAdapter", "()Lcom/medscape/android/activity/saved/adapters/SavedItemsAdapter;", "setSavedItemsAdapter", "(Lcom/medscape/android/activity/saved/adapters/SavedItemsAdapter;)V", "savedList", "", "getSavedList", "setSavedList", "savedListByType", "getSavedListByType", "setSavedListByType", "savedNewsList", "getSavedNewsList", "setSavedNewsList", "tabElements", "Lcom/medscape/android/activity/saved/model/TabLayoutElement;", "getTabElements$medscape_release", "setTabElements$medscape_release", "tabsAdapter", "Lcom/medscape/android/activity/saved/adapters/SavedTabLayoutAdapter;", "getTabsAdapter", "()Lcom/medscape/android/activity/saved/adapters/SavedTabLayoutAdapter;", "setTabsAdapter", "(Lcom/medscape/android/activity/saved/adapters/SavedTabLayoutAdapter;)V", "viewPagerAdapter", "Lcom/medscape/android/activity/saved/adapters/ViewPagerAdapter;", "getViewPagerAdapter", "()Lcom/medscape/android/activity/saved/adapters/ViewPagerAdapter;", "setViewPagerAdapter", "(Lcom/medscape/android/activity/saved/adapters/ViewPagerAdapter;)V", "deleteCME", "", "index", "context", "Landroidx/appcompat/app/AppCompatActivity;", "deleteCRArticle", "mSelectedIndex", "deleteCRArticleFromDB", "articleID", "idType", "Landroid/content/Context;", "deleteCalc", "deleteCalcArticleFromDB", "calcArticle", "deleteDrug", "deleteDrugFromDB", "contentID", "deleteNews", "deleteNewsFromDB", "url", "deleteSavedItem", "view", "Landroid/view/View;", "getAllSavedArticles", "getArticles", "getCMEFeed", "getCalcArticles", "getRSSFeed", "getSavedDrugs", "updateDbContentValues", "calcId", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: SavedFeedViewModel.kt */
public final class SavedFeedViewModel extends ViewModel {
    private final String ALL = "ALL";
    private final String CALC = "CALCULATORS";
    private final String CALC_TITLE = "Calculators";
    private final String DRUGS = "DRUGS";
    private final String DRUGS_TITLE = "Drugs";
    private final String EDU = "EDUCATION";
    private final String EDU_TITLE = Constants.CONSULT_USER_EDUCATION;
    private final String NEWS = "NEWS & PERSPECTIVE";
    private final String NEWS_TITLE = "News & Perspective";
    private final String REF = "CONDITIONS & PROCEDURES";
    private final String REF_TITLE = "Conditions & Procedures";
    public String activeFragment;
    private int activePosition;
    private MutableLiveData<Boolean> countLiveFlag = new MutableLiveData<>();
    private List<ClinicalReferenceArticle> crConditionsList = new ArrayList();
    private List<ClinicalReferenceArticle> crProceduresList = new ArrayList();
    public List<ClinicalReferenceArticle> crRefList;
    private int[] dataSavedSize;
    private boolean editModeActive;
    private ArrayList<RecyclerView> listRecyclerViews = new ArrayList<>();
    private List<Cache> savedCMEList = new ArrayList();
    private List<CalcArticle> savedCalcList = new ArrayList();
    private List<DrugCache> savedDrugList = new ArrayList();
    public SavedItemsAdapter savedItemsAdapter;
    private List<Object> savedList = new ArrayList();
    private List<Object> savedListByType = new ArrayList();
    private List<Cache> savedNewsList = new ArrayList();
    private List<TabLayoutElement> tabElements = new ArrayList();
    public SavedTabLayoutAdapter tabsAdapter;
    public ViewPagerAdapter viewPagerAdapter;

    public final String getALL() {
        return this.ALL;
    }

    public final String getDRUGS() {
        return this.DRUGS;
    }

    public final String getREF() {
        return this.REF;
    }

    public final String getCALC() {
        return this.CALC;
    }

    public final String getNEWS() {
        return this.NEWS;
    }

    public final String getEDU() {
        return this.EDU;
    }

    public final String getDRUGS_TITLE() {
        return this.DRUGS_TITLE;
    }

    public final String getREF_TITLE() {
        return this.REF_TITLE;
    }

    public final String getCALC_TITLE() {
        return this.CALC_TITLE;
    }

    public final String getNEWS_TITLE() {
        return this.NEWS_TITLE;
    }

    public final String getEDU_TITLE() {
        return this.EDU_TITLE;
    }

    public final List<DrugCache> getSavedDrugList() {
        return this.savedDrugList;
    }

    public final void setSavedDrugList(List<DrugCache> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.savedDrugList = list;
    }

    public final List<Cache> getSavedNewsList() {
        return this.savedNewsList;
    }

    public final void setSavedNewsList(List<Cache> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.savedNewsList = list;
    }

    public final List<Cache> getSavedCMEList() {
        return this.savedCMEList;
    }

    public final void setSavedCMEList(List<Cache> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.savedCMEList = list;
    }

    public final List<ClinicalReferenceArticle> getCrProceduresList() {
        return this.crProceduresList;
    }

    public final void setCrProceduresList(List<ClinicalReferenceArticle> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.crProceduresList = list;
    }

    public final List<CalcArticle> getSavedCalcList() {
        return this.savedCalcList;
    }

    public final void setSavedCalcList(List<CalcArticle> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.savedCalcList = list;
    }

    public final List<Object> getSavedList() {
        return this.savedList;
    }

    public final void setSavedList(List<Object> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.savedList = list;
    }

    public final MutableLiveData<Boolean> getCountLiveFlag() {
        return this.countLiveFlag;
    }

    public final void setCountLiveFlag(MutableLiveData<Boolean> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.countLiveFlag = mutableLiveData;
    }

    public final List<Object> getSavedListByType() {
        return this.savedListByType;
    }

    public final void setSavedListByType(List<Object> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.savedListByType = list;
    }

    public final List<ClinicalReferenceArticle> getCrConditionsList() {
        return this.crConditionsList;
    }

    public final void setCrConditionsList(List<ClinicalReferenceArticle> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.crConditionsList = list;
    }

    public final List<TabLayoutElement> getTabElements$medscape_release() {
        return this.tabElements;
    }

    public final void setTabElements$medscape_release(List<TabLayoutElement> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.tabElements = list;
    }

    public final List<ClinicalReferenceArticle> getCrRefList() {
        List<ClinicalReferenceArticle> list = this.crRefList;
        if (list == null) {
            Intrinsics.throwUninitializedPropertyAccessException("crRefList");
        }
        return list;
    }

    public final void setCrRefList(List<ClinicalReferenceArticle> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.crRefList = list;
    }

    public final SavedItemsAdapter getSavedItemsAdapter() {
        SavedItemsAdapter savedItemsAdapter2 = this.savedItemsAdapter;
        if (savedItemsAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("savedItemsAdapter");
        }
        return savedItemsAdapter2;
    }

    public final void setSavedItemsAdapter(SavedItemsAdapter savedItemsAdapter2) {
        Intrinsics.checkNotNullParameter(savedItemsAdapter2, "<set-?>");
        this.savedItemsAdapter = savedItemsAdapter2;
    }

    public final ViewPagerAdapter getViewPagerAdapter() {
        ViewPagerAdapter viewPagerAdapter2 = this.viewPagerAdapter;
        if (viewPagerAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewPagerAdapter");
        }
        return viewPagerAdapter2;
    }

    public final void setViewPagerAdapter(ViewPagerAdapter viewPagerAdapter2) {
        Intrinsics.checkNotNullParameter(viewPagerAdapter2, "<set-?>");
        this.viewPagerAdapter = viewPagerAdapter2;
    }

    public final SavedTabLayoutAdapter getTabsAdapter() {
        SavedTabLayoutAdapter savedTabLayoutAdapter = this.tabsAdapter;
        if (savedTabLayoutAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tabsAdapter");
        }
        return savedTabLayoutAdapter;
    }

    public final void setTabsAdapter(SavedTabLayoutAdapter savedTabLayoutAdapter) {
        Intrinsics.checkNotNullParameter(savedTabLayoutAdapter, "<set-?>");
        this.tabsAdapter = savedTabLayoutAdapter;
    }

    public final ArrayList<RecyclerView> getListRecyclerViews() {
        return this.listRecyclerViews;
    }

    public final void setListRecyclerViews(ArrayList<RecyclerView> arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
        this.listRecyclerViews = arrayList;
    }

    public final boolean getEditModeActive() {
        return this.editModeActive;
    }

    public final void setEditModeActive(boolean z) {
        this.editModeActive = z;
    }

    public final String getActiveFragment() {
        String str = this.activeFragment;
        if (str == null) {
            Intrinsics.throwUninitializedPropertyAccessException("activeFragment");
        }
        return str;
    }

    public final void setActiveFragment(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.activeFragment = str;
    }

    public final int getActivePosition() {
        return this.activePosition;
    }

    public final void setActivePosition(int i) {
        this.activePosition = i;
    }

    public final void getAllSavedArticles(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        View view = new View(context);
        List arrayList = new ArrayList();
        this.dataSavedSize = new int[]{0, 0, 0, 0, 0};
        this.savedList = new ArrayList();
        this.savedListByType = new ArrayList();
        this.crRefList = new ArrayList();
        this.crConditionsList = new ArrayList();
        this.savedList.clear();
        this.savedDrugList.clear();
        this.savedCalcList.clear();
        this.savedCMEList.clear();
        this.savedNewsList.clear();
        this.crConditionsList.clear();
        List<ClinicalReferenceArticle> list = this.crRefList;
        if (list == null) {
            Intrinsics.throwUninitializedPropertyAccessException("crRefList");
        }
        list.clear();
        this.tabElements.clear();
        arrayList.clear();
        this.savedDrugList = getSavedDrugs(context);
        this.savedNewsList = getRSSFeed(context);
        this.savedCMEList = getCMEFeed(context);
        this.crProceduresList = getArticles(context);
        this.savedCalcList = getCalcArticles(context);
        boolean z = true;
        if (!this.savedDrugList.isEmpty()) {
            List<Object> list2 = this.savedList;
            if (list2 != null) {
                ((ArrayList) list2).add(this.DRUGS_TITLE);
                this.tabElements.add(new TabLayoutElement(this.DRUGS, false));
                arrayList.add(this.DRUGS);
                List<Object> list3 = this.savedList;
                if (list3 != null) {
                    ((ArrayList) list3).addAll(this.savedDrugList);
                    List<Object> list4 = this.savedList;
                    if (list4 != null) {
                        ((ArrayList) list4).add(view);
                        int[] iArr = this.dataSavedSize;
                        if (iArr == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("dataSavedSize");
                        }
                        iArr[0] = this.savedDrugList.size();
                    } else {
                        throw new NullPointerException("null cannot be cast to non-null type kotlin.collections.ArrayList<kotlin.Any> /* = java.util.ArrayList<kotlin.Any> */");
                    }
                } else {
                    throw new NullPointerException("null cannot be cast to non-null type kotlin.collections.ArrayList<kotlin.Any> /* = java.util.ArrayList<kotlin.Any> */");
                }
            } else {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.collections.ArrayList<kotlin.Any> /* = java.util.ArrayList<kotlin.Any> */");
            }
        }
        if (!this.crProceduresList.isEmpty()) {
            if (!arrayList.contains(this.REF)) {
                List<Object> list5 = this.savedList;
                if (list5 != null) {
                    ((ArrayList) list5).add(this.REF_TITLE);
                    this.tabElements.add(new TabLayoutElement(this.REF, false));
                    arrayList.add(this.REF);
                } else {
                    throw new NullPointerException("null cannot be cast to non-null type kotlin.collections.ArrayList<kotlin.Any> /* = java.util.ArrayList<kotlin.Any> */");
                }
            }
            List<Object> list6 = this.savedList;
            if (list6 != null) {
                ((ArrayList) list6).addAll(this.crProceduresList);
                List<ClinicalReferenceArticle> list7 = this.crRefList;
                if (list7 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("crRefList");
                }
                list7.addAll(this.crProceduresList);
            } else {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.collections.ArrayList<kotlin.Any> /* = java.util.ArrayList<kotlin.Any> */");
            }
        }
        List<ClinicalReferenceArticle> list8 = this.crConditionsList;
        if (list8 != null) {
            if (((ArrayList) list8).size() > 0) {
                if (!arrayList.contains(this.REF)) {
                    List<Object> list9 = this.savedList;
                    if (list9 != null) {
                        ((ArrayList) list9).add(this.REF_TITLE);
                        this.tabElements.add(new TabLayoutElement(this.REF, false));
                        arrayList.add(this.REF);
                    } else {
                        throw new NullPointerException("null cannot be cast to non-null type kotlin.collections.ArrayList<kotlin.Any> /* = java.util.ArrayList<kotlin.Any> */");
                    }
                }
                List<Object> list10 = this.savedList;
                if (list10 != null) {
                    ArrayList arrayList2 = (ArrayList) list10;
                    List<ClinicalReferenceArticle> list11 = this.crConditionsList;
                    if (list11 != null) {
                        arrayList2.addAll((ArrayList) list11);
                        List<ClinicalReferenceArticle> list12 = this.crRefList;
                        if (list12 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("crRefList");
                        }
                        list12.addAll(this.crConditionsList);
                    } else {
                        throw new NullPointerException("null cannot be cast to non-null type kotlin.collections.ArrayList<com.medscape.android.reference.model.ClinicalReferenceArticle> /* = java.util.ArrayList<com.medscape.android.reference.model.ClinicalReferenceArticle> */");
                    }
                } else {
                    throw new NullPointerException("null cannot be cast to non-null type kotlin.collections.ArrayList<kotlin.Any> /* = java.util.ArrayList<kotlin.Any> */");
                }
            }
            List<ClinicalReferenceArticle> list13 = this.crRefList;
            if (list13 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("crRefList");
            }
            if (list13.size() > 0) {
                List<Object> list14 = this.savedList;
                if (list14 != null) {
                    ((ArrayList) list14).add(view);
                    int[] iArr2 = this.dataSavedSize;
                    if (iArr2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("dataSavedSize");
                    }
                    List<ClinicalReferenceArticle> list15 = this.crRefList;
                    if (list15 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("crRefList");
                    }
                    iArr2[1] = list15.size();
                } else {
                    throw new NullPointerException("null cannot be cast to non-null type kotlin.collections.ArrayList<kotlin.Any> /* = java.util.ArrayList<kotlin.Any> */");
                }
            }
            if (!this.savedCalcList.isEmpty()) {
                List<Object> list16 = this.savedList;
                if (list16 != null) {
                    ((ArrayList) list16).add(this.CALC_TITLE);
                    this.tabElements.add(new TabLayoutElement(this.CALC, false));
                    arrayList.add(this.CALC);
                    List<Object> list17 = this.savedList;
                    if (list17 != null) {
                        ((ArrayList) list17).addAll(this.savedCalcList);
                        List<Object> list18 = this.savedList;
                        if (list18 != null) {
                            ((ArrayList) list18).add(view);
                            int[] iArr3 = this.dataSavedSize;
                            if (iArr3 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("dataSavedSize");
                            }
                            iArr3[2] = this.savedCalcList.size();
                        } else {
                            throw new NullPointerException("null cannot be cast to non-null type kotlin.collections.ArrayList<kotlin.Any> /* = java.util.ArrayList<kotlin.Any> */");
                        }
                    } else {
                        throw new NullPointerException("null cannot be cast to non-null type kotlin.collections.ArrayList<kotlin.Any> /* = java.util.ArrayList<kotlin.Any> */");
                    }
                } else {
                    throw new NullPointerException("null cannot be cast to non-null type kotlin.collections.ArrayList<kotlin.Any> /* = java.util.ArrayList<kotlin.Any> */");
                }
            }
            if (!this.savedNewsList.isEmpty()) {
                List<Object> list19 = this.savedList;
                if (list19 != null) {
                    ((ArrayList) list19).add(this.NEWS_TITLE);
                    this.tabElements.add(new TabLayoutElement(this.NEWS, false));
                    arrayList.add(this.NEWS);
                    List<Object> list20 = this.savedList;
                    if (list20 != null) {
                        ((ArrayList) list20).addAll(this.savedNewsList);
                        List<Object> list21 = this.savedList;
                        if (list21 != null) {
                            ((ArrayList) list21).add(view);
                            int[] iArr4 = this.dataSavedSize;
                            if (iArr4 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("dataSavedSize");
                            }
                            iArr4[3] = this.savedNewsList.size();
                        } else {
                            throw new NullPointerException("null cannot be cast to non-null type kotlin.collections.ArrayList<kotlin.Any> /* = java.util.ArrayList<kotlin.Any> */");
                        }
                    } else {
                        throw new NullPointerException("null cannot be cast to non-null type kotlin.collections.ArrayList<kotlin.Any> /* = java.util.ArrayList<kotlin.Any> */");
                    }
                } else {
                    throw new NullPointerException("null cannot be cast to non-null type kotlin.collections.ArrayList<kotlin.Any> /* = java.util.ArrayList<kotlin.Any> */");
                }
            }
            if (!this.savedCMEList.isEmpty()) {
                List<Object> list22 = this.savedList;
                if (list22 != null) {
                    ((ArrayList) list22).add(this.EDU_TITLE);
                    this.tabElements.add(new TabLayoutElement(this.EDU, false));
                    arrayList.add(this.EDU);
                    List<Object> list23 = this.savedList;
                    if (list23 != null) {
                        ((ArrayList) list23).addAll(this.savedCMEList);
                        List<Object> list24 = this.savedList;
                        if (list24 != null) {
                            ((ArrayList) list24).add(view);
                            int[] iArr5 = this.dataSavedSize;
                            if (iArr5 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("dataSavedSize");
                            }
                            iArr5[4] = this.savedCMEList.size();
                        } else {
                            throw new NullPointerException("null cannot be cast to non-null type kotlin.collections.ArrayList<kotlin.Any> /* = java.util.ArrayList<kotlin.Any> */");
                        }
                    } else {
                        throw new NullPointerException("null cannot be cast to non-null type kotlin.collections.ArrayList<kotlin.Any> /* = java.util.ArrayList<kotlin.Any> */");
                    }
                } else {
                    throw new NullPointerException("null cannot be cast to non-null type kotlin.collections.ArrayList<kotlin.Any> /* = java.util.ArrayList<kotlin.Any> */");
                }
            }
            if (this.savedList.size() > 0) {
                this.tabElements.add(0, new TabLayoutElement(this.ALL, true));
                this.savedListByType.clear();
                this.savedListByType.addAll(this.savedList);
            }
            MutableLiveData<Boolean> mutableLiveData = this.countLiveFlag;
            if (this.savedList.size() != 0) {
                z = false;
            }
            mutableLiveData.setValue(Boolean.valueOf(z));
            return;
        }
        throw new NullPointerException("null cannot be cast to non-null type kotlin.collections.ArrayList<com.medscape.android.reference.model.ClinicalReferenceArticle> /* = java.util.ArrayList<com.medscape.android.reference.model.ClinicalReferenceArticle> */");
    }

    public final List<DrugCache> getSavedDrugs(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        CacheManager cacheManager = new CacheManager(context);
        this.savedListByType.clear();
        List<Object> list = this.savedListByType;
        List<DrugCache> savedDrugCache = cacheManager.getSavedDrugCache();
        Intrinsics.checkNotNullExpressionValue(savedDrugCache, "drugCacheManager.savedDrugCache");
        list.addAll(savedDrugCache);
        List<DrugCache> savedDrugCache2 = cacheManager.getSavedDrugCache();
        Intrinsics.checkNotNullExpressionValue(savedDrugCache2, "drugCacheManager.savedDrugCache");
        return savedDrugCache2;
    }

    public final List<Cache> getRSSFeed(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        CacheManager cacheManager = new CacheManager(context);
        this.savedListByType.clear();
        List<Object> list = this.savedListByType;
        List<Cache> newsSavedCache = cacheManager.getNewsSavedCache();
        Intrinsics.checkNotNullExpressionValue(newsSavedCache, "cacheManager.newsSavedCache");
        list.addAll(newsSavedCache);
        List<Cache> newsSavedCache2 = cacheManager.getNewsSavedCache();
        Intrinsics.checkNotNullExpressionValue(newsSavedCache2, "cacheManager.newsSavedCache");
        return newsSavedCache2;
    }

    public final List<Cache> getCMEFeed(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        CacheManager cacheManager = new CacheManager(context);
        this.savedListByType.clear();
        List<Object> list = this.savedListByType;
        List<Cache> cMESavedCache = cacheManager.getCMESavedCache();
        Intrinsics.checkNotNullExpressionValue(cMESavedCache, "cacheManager.cmeSavedCache");
        list.addAll(cMESavedCache);
        List<Cache> cMESavedCache2 = cacheManager.getCMESavedCache();
        Intrinsics.checkNotNullExpressionValue(cMESavedCache2, "cacheManager.cmeSavedCache");
        return cMESavedCache2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x007d, code lost:
        if (r1 != null) goto L_0x007f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x007f, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0089, code lost:
        if (r1 == null) goto L_0x008c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x008c, code lost:
        r10.savedListByType.clear();
        r10.savedListByType.addAll(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x009b, code lost:
        return r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.List<com.medscape.android.reference.model.ClinicalReferenceArticle> getArticles(android.content.Context r11) {
        /*
            r10 = this;
            java.lang.String r0 = "context"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r11, r0)
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r1 = 0
            android.database.Cursor r1 = (android.database.Cursor) r1
            com.medscape.android.MedscapeApplication r2 = com.medscape.android.MedscapeApplication.get()     // Catch:{ Exception -> 0x0085 }
            android.content.Context r2 = (android.content.Context) r2     // Catch:{ Exception -> 0x0085 }
            com.medscape.android.auth.AuthenticationManager r2 = com.medscape.android.auth.AuthenticationManager.getInstance(r2)     // Catch:{ Exception -> 0x0085 }
            java.lang.String r3 = "AuthenticationManager.ge…edscapeApplication.get())"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r3)     // Catch:{ Exception -> 0x0085 }
            java.lang.String r2 = r2.getMaskedGuid()     // Catch:{ Exception -> 0x0085 }
            boolean r3 = com.medscape.android.util.StringUtil.isNotEmpty(r2)     // Catch:{ Exception -> 0x0085 }
            if (r3 == 0) goto L_0x007d
            java.lang.String r7 = "userGuid='' OR (isSaved=? AND userGuid=?)"
            android.content.ContentResolver r4 = r11.getContentResolver()     // Catch:{ Exception -> 0x0085 }
            android.net.Uri r5 = com.medscape.android.reference.model.ClinicalReferenceArticle.ClinicalReferenceArticles.CONTENT_URI     // Catch:{ Exception -> 0x0085 }
            r6 = 0
            r11 = 2
            java.lang.String[] r8 = new java.lang.String[r11]     // Catch:{ Exception -> 0x0085 }
            r3 = 0
            java.lang.String r9 = "1"
            r8[r3] = r9     // Catch:{ Exception -> 0x0085 }
            r3 = 1
            r8[r3] = r2     // Catch:{ Exception -> 0x0085 }
            r9 = 0
            android.database.Cursor r1 = r4.query(r5, r6, r7, r8, r9)     // Catch:{ Exception -> 0x0085 }
            if (r1 == 0) goto L_0x007d
        L_0x0041:
            boolean r2 = r1.moveToNext()     // Catch:{ Exception -> 0x0085 }
            if (r2 == 0) goto L_0x007d
            com.medscape.android.reference.model.ClinicalReferenceArticle r2 = new com.medscape.android.reference.model.ClinicalReferenceArticle     // Catch:{ Exception -> 0x0085 }
            r2.<init>()     // Catch:{ Exception -> 0x0085 }
            int r4 = r1.getInt(r3)     // Catch:{ Exception -> 0x0085 }
            r2.setUniqueId(r4)     // Catch:{ Exception -> 0x0085 }
            java.lang.String r4 = r1.getString(r11)     // Catch:{ Exception -> 0x0085 }
            r2.setTitle(r4)     // Catch:{ Exception -> 0x0085 }
            r2.setSaved(r3)     // Catch:{ Exception -> 0x0085 }
            r4 = 3
            int r4 = r1.getInt(r4)     // Catch:{ Exception -> 0x0085 }
            r2.setArticleId(r4)     // Catch:{ Exception -> 0x0085 }
            r4 = 4
            int r4 = r1.getInt(r4)     // Catch:{ Exception -> 0x0085 }
            r2.setType(r4)     // Catch:{ Exception -> 0x0085 }
            int r4 = r2.getType()     // Catch:{ Exception -> 0x0085 }
            if (r4 != r3) goto L_0x0077
            r0.add(r2)     // Catch:{ Exception -> 0x0085 }
            goto L_0x0041
        L_0x0077:
            java.util.List<com.medscape.android.reference.model.ClinicalReferenceArticle> r4 = r10.crConditionsList     // Catch:{ Exception -> 0x0085 }
            r4.add(r2)     // Catch:{ Exception -> 0x0085 }
            goto L_0x0041
        L_0x007d:
            if (r1 == 0) goto L_0x008c
        L_0x007f:
            r1.close()
            goto L_0x008c
        L_0x0083:
            r11 = move-exception
            goto L_0x009c
        L_0x0085:
            r11 = move-exception
            r11.printStackTrace()     // Catch:{ all -> 0x0083 }
            if (r1 == 0) goto L_0x008c
            goto L_0x007f
        L_0x008c:
            java.util.List<java.lang.Object> r11 = r10.savedListByType
            r11.clear()
            java.util.List<java.lang.Object> r11 = r10.savedListByType
            r1 = r0
            java.util.Collection r1 = (java.util.Collection) r1
            r11.addAll(r1)
            java.util.List r0 = (java.util.List) r0
            return r0
        L_0x009c:
            if (r1 == 0) goto L_0x00a1
            r1.close()
        L_0x00a1:
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.medscape.android.activity.saved.viewmodel.SavedFeedViewModel.getArticles(android.content.Context):java.util.List");
    }

    public final List<CalcArticle> getCalcArticles(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        ArrayList<CalcArticle> oldSavedCalculators = Util.getOldSavedCalculators(context);
        this.savedListByType.clear();
        List<Object> list = this.savedListByType;
        Intrinsics.checkNotNullExpressionValue(oldSavedCalculators, "calcList");
        list.addAll(oldSavedCalculators);
        return oldSavedCalculators;
    }

    public final void deleteSavedItem(View view, AppCompatActivity appCompatActivity) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(appCompatActivity, "context");
        try {
            String obj = view.getTag().toString();
            int indexOf$default = StringsKt.indexOf$default((CharSequence) obj, "|", 0, false, 6, (Object) null);
            if (obj != null) {
                Intrinsics.checkNotNullExpressionValue(obj.substring(0, indexOf$default), "(this as java.lang.Strin…ing(startIndex, endIndex)");
                int indexOf$default2 = StringsKt.indexOf$default((CharSequence) obj, "|", 0, false, 6, (Object) null);
                if (obj != null) {
                    String substring = obj.substring(0, indexOf$default2);
                    Intrinsics.checkNotNullExpressionValue(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                    int parseInt = Integer.parseInt(substring);
                    int indexOf$default3 = StringsKt.indexOf$default((CharSequence) obj, "|", 0, false, 6, (Object) null) + 1;
                    int length = obj.length();
                    if (obj != null) {
                        String substring2 = obj.substring(indexOf$default3, length);
                        Intrinsics.checkNotNullExpressionValue(substring2, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                        int hashCode = substring2.hashCode();
                        if (hashCode != 99) {
                            if (hashCode != 100) {
                                if (hashCode != 110) {
                                    if (hashCode != 98619) {
                                        if (hashCode == 3045973) {
                                            if (substring2.equals(Constants.OMNITURE_MLINK_CALC)) {
                                                deleteCalc(parseInt, appCompatActivity);
                                            }
                                        }
                                    } else if (substring2.equals(FeedConstants.CME_ITEM)) {
                                        deleteCME(parseInt, appCompatActivity);
                                    }
                                } else if (substring2.equals(com.appboy.Constants.APPBOY_PUSH_CUSTOM_NOTIFICATION_ID)) {
                                    deleteNews(parseInt, appCompatActivity);
                                }
                            } else if (substring2.equals(com.appboy.Constants.APPBOY_PUSH_NOTIFICATION_SOUND_DEFAULT_VALUE)) {
                                deleteDrug(parseInt, appCompatActivity);
                            }
                        } else if (substring2.equals("c")) {
                            deleteCRArticle(parseInt, appCompatActivity);
                        }
                        getAllSavedArticles(appCompatActivity);
                        ViewPagerAdapter viewPagerAdapter2 = this.viewPagerAdapter;
                        if (viewPagerAdapter2 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("viewPagerAdapter");
                        }
                        viewPagerAdapter2.notifyDataSetChanged();
                        return;
                    }
                    throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
                }
                throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
            }
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private final void deleteDrug(int i, AppCompatActivity appCompatActivity) {
        int i2;
        List arrayList = new ArrayList();
        int[] iArr = this.dataSavedSize;
        if (iArr == null) {
            Intrinsics.throwUninitializedPropertyAccessException("dataSavedSize");
        }
        iArr[0] = iArr[0] - 1;
        if (this.activePosition == 0) {
            Object obj = this.savedList.get(i);
            if (obj != null) {
                i2 = ((DrugCache) obj).getContentId();
                int[] iArr2 = this.dataSavedSize;
                if (iArr2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("dataSavedSize");
                }
                if (iArr2[0] == 0) {
                    int i3 = i - 1;
                    this.savedList.remove(i3);
                    this.savedList.remove(i);
                    this.savedList.remove(i3);
                    Iterator<TabLayoutElement> it = this.tabElements.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        TabLayoutElement next = it.next();
                        if (Intrinsics.areEqual((Object) next.getName(), (Object) this.DRUGS)) {
                            this.tabElements.remove(next);
                            break;
                        }
                    }
                } else {
                    this.savedList.remove(i);
                }
                arrayList.addAll(this.savedList);
                Iterator<DrugCache> it2 = this.savedDrugList.iterator();
                while (true) {
                    if (!it2.hasNext()) {
                        break;
                    }
                    DrugCache next2 = it2.next();
                    if (next2.getContentId() == i2) {
                        this.savedDrugList.remove(next2);
                        break;
                    }
                }
            } else {
                throw new NullPointerException("null cannot be cast to non-null type com.medscape.android.cache.DrugCache");
            }
        } else {
            i2 = this.savedDrugList.get(i).getContentId();
            this.savedDrugList.remove(i);
            arrayList.addAll(this.savedDrugList);
            Iterator<Object> it3 = this.savedList.iterator();
            while (true) {
                if (!it3.hasNext()) {
                    break;
                }
                Object next3 = it3.next();
                if ((next3 instanceof DrugCache) && ((DrugCache) next3).getContentId() == i2) {
                    int indexOf = this.savedList.indexOf(next3);
                    int[] iArr3 = this.dataSavedSize;
                    if (iArr3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("dataSavedSize");
                    }
                    if (iArr3[0] == 0) {
                        int i4 = indexOf - 1;
                        this.savedList.remove(i4);
                        this.savedList.remove(indexOf);
                        this.savedList.remove(i4);
                    } else {
                        this.savedList.remove(next3);
                    }
                }
            }
            if (this.savedDrugList.size() == 0) {
                Iterator<TabLayoutElement> it4 = this.tabElements.iterator();
                while (true) {
                    if (!it4.hasNext()) {
                        break;
                    }
                    TabLayoutElement next4 = it4.next();
                    if (Intrinsics.areEqual((Object) next4.getName(), (Object) this.DRUGS)) {
                        this.tabElements.remove(next4);
                        break;
                    }
                }
            }
        }
        deleteDrugFromDB(i2, appCompatActivity);
        OmnitureManager.get().trackModule(appCompatActivity, Constants.OMNITURE_CHANNEL_REFERENCE, "save", Constants.OMNITURE_MLINK_UNSAVE, (Map<String, Object>) null);
    }

    private final void deleteDrugFromDB(int i, AppCompatActivity appCompatActivity) {
        try {
            appCompatActivity.getContentResolver().delete(DrugCache.DrugCaches.CONTENT_URI, "contentId= ? ", new String[]{String.valueOf(i)});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private final void deleteNews(int i, AppCompatActivity appCompatActivity) {
        String str;
        List arrayList = new ArrayList();
        int[] iArr = this.dataSavedSize;
        if (iArr == null) {
            Intrinsics.throwUninitializedPropertyAccessException("dataSavedSize");
        }
        iArr[3] = iArr[3] - 1;
        if (this.activePosition == 0) {
            Object obj = this.savedList.get(i);
            if (obj != null) {
                str = ((Cache) obj).getUrl();
                Intrinsics.checkNotNullExpressionValue(str, "(this.savedList[index] as Cache).url");
                int[] iArr2 = this.dataSavedSize;
                if (iArr2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("dataSavedSize");
                }
                if (iArr2[3] == 0) {
                    int i2 = i - 1;
                    this.savedList.remove(i2);
                    this.savedList.remove(i);
                    this.savedList.remove(i2);
                    Iterator<TabLayoutElement> it = this.tabElements.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        TabLayoutElement next = it.next();
                        if (Intrinsics.areEqual((Object) next.getName(), (Object) this.NEWS)) {
                            this.tabElements.remove(next);
                            break;
                        }
                    }
                } else {
                    this.savedList.remove(i);
                }
                arrayList.addAll(this.savedList);
                Iterator<Cache> it2 = this.savedNewsList.iterator();
                while (true) {
                    if (!it2.hasNext()) {
                        break;
                    }
                    Cache next2 = it2.next();
                    if (Intrinsics.areEqual((Object) next2.getUrl(), (Object) str)) {
                        this.savedNewsList.remove(next2);
                        break;
                    }
                }
            } else {
                throw new NullPointerException("null cannot be cast to non-null type com.medscape.android.cache.Cache");
            }
        } else {
            str = this.savedNewsList.get(i).getUrl();
            Intrinsics.checkNotNullExpressionValue(str, "(this.savedNewsList[index]).url");
            this.savedNewsList.remove(i);
            arrayList.addAll(this.savedNewsList);
            Iterator<Object> it3 = this.savedList.iterator();
            while (true) {
                if (!it3.hasNext()) {
                    break;
                }
                Object next3 = it3.next();
                if ((next3 instanceof Cache) && Intrinsics.areEqual((Object) ((Cache) next3).getUrl(), (Object) str)) {
                    int indexOf = this.savedList.indexOf(next3);
                    int[] iArr3 = this.dataSavedSize;
                    if (iArr3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("dataSavedSize");
                    }
                    if (iArr3[3] == 0) {
                        int i3 = indexOf - 1;
                        this.savedList.remove(i3);
                        this.savedList.remove(indexOf);
                        this.savedList.remove(i3);
                    } else {
                        this.savedList.remove(next3);
                    }
                }
            }
            if (this.savedNewsList.size() == 0) {
                Iterator<TabLayoutElement> it4 = this.tabElements.iterator();
                while (true) {
                    if (!it4.hasNext()) {
                        break;
                    }
                    TabLayoutElement next4 = it4.next();
                    if (Intrinsics.areEqual((Object) next4.getName(), (Object) this.NEWS)) {
                        this.tabElements.remove(next4);
                        break;
                    }
                }
            }
        }
        Context context = appCompatActivity;
        deleteNewsFromDB(str, context);
        OmnitureManager.get().trackModule(context, Constants.OMNITURE_CHANNEL_NEWS, "save", Constants.OMNITURE_MLINK_UNSAVE, (Map<String, Object>) null);
    }

    private final void deleteCME(int i, AppCompatActivity appCompatActivity) {
        String str;
        List arrayList = new ArrayList();
        int[] iArr = this.dataSavedSize;
        if (iArr == null) {
            Intrinsics.throwUninitializedPropertyAccessException("dataSavedSize");
        }
        iArr[4] = iArr[4] - 1;
        if (this.activePosition == 0) {
            Object obj = this.savedList.get(i);
            if (obj != null) {
                str = ((Cache) obj).getUrl();
                Intrinsics.checkNotNullExpressionValue(str, "(this.savedList[index] as Cache).url");
                int[] iArr2 = this.dataSavedSize;
                if (iArr2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("dataSavedSize");
                }
                if (iArr2[4] == 0) {
                    int i2 = i - 1;
                    this.savedList.remove(i2);
                    this.savedList.remove(i);
                    this.savedList.remove(i2);
                    Iterator<TabLayoutElement> it = this.tabElements.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        TabLayoutElement next = it.next();
                        if (Intrinsics.areEqual((Object) next.getName(), (Object) this.EDU)) {
                            this.tabElements.remove(next);
                            break;
                        }
                    }
                } else {
                    this.savedList.remove(i);
                }
                arrayList.addAll(this.savedList);
                Iterator<Cache> it2 = this.savedNewsList.iterator();
                while (true) {
                    if (!it2.hasNext()) {
                        break;
                    }
                    Cache next2 = it2.next();
                    if (Intrinsics.areEqual((Object) next2.getUrl(), (Object) str)) {
                        this.savedCMEList.remove(next2);
                        break;
                    }
                }
            } else {
                throw new NullPointerException("null cannot be cast to non-null type com.medscape.android.cache.Cache");
            }
        } else {
            str = this.savedCMEList.get(i).getUrl();
            Intrinsics.checkNotNullExpressionValue(str, "(this.savedCMEList[index]).url");
            this.savedCMEList.remove(i);
            arrayList.addAll(this.savedCMEList);
            Iterator<Object> it3 = this.savedList.iterator();
            while (true) {
                if (!it3.hasNext()) {
                    break;
                }
                Object next3 = it3.next();
                if ((next3 instanceof Cache) && Intrinsics.areEqual((Object) ((Cache) next3).getUrl(), (Object) str)) {
                    int indexOf = this.savedList.indexOf(next3);
                    int[] iArr3 = this.dataSavedSize;
                    if (iArr3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("dataSavedSize");
                    }
                    if (iArr3[4] == 0) {
                        int i3 = indexOf - 1;
                        this.savedList.remove(i3);
                        this.savedList.remove(indexOf);
                        this.savedList.remove(i3);
                    } else {
                        this.savedList.remove(next3);
                    }
                }
            }
            if (this.savedCMEList.size() == 0) {
                Iterator<TabLayoutElement> it4 = this.tabElements.iterator();
                while (true) {
                    if (!it4.hasNext()) {
                        break;
                    }
                    TabLayoutElement next4 = it4.next();
                    if (Intrinsics.areEqual((Object) next4.getName(), (Object) this.EDU)) {
                        this.tabElements.remove(next4);
                        break;
                    }
                }
            }
        }
        Context context = appCompatActivity;
        deleteNewsFromDB(str, context);
        OmnitureManager.get().trackModule(context, "education", "save", Constants.OMNITURE_MLINK_UNSAVE, (Map<String, Object>) null);
    }

    private final void deleteCalc(int i, AppCompatActivity appCompatActivity) {
        CalcArticle calcArticle;
        List arrayList = new ArrayList();
        int[] iArr = this.dataSavedSize;
        if (iArr == null) {
            Intrinsics.throwUninitializedPropertyAccessException("dataSavedSize");
        }
        iArr[2] = iArr[2] - 1;
        if (this.activePosition == 0) {
            Object obj = this.savedList.get(i);
            if (obj != null) {
                calcArticle = (CalcArticle) obj;
                String calcId = calcArticle.getCalcId();
                Intrinsics.checkNotNullExpressionValue(calcId, "calcArticle.calcId");
                int[] iArr2 = this.dataSavedSize;
                if (iArr2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("dataSavedSize");
                }
                if (iArr2[2] == 0) {
                    int i2 = i - 1;
                    this.savedList.remove(i2);
                    this.savedList.remove(i);
                    this.savedList.remove(i2);
                    Iterator<TabLayoutElement> it = this.tabElements.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        TabLayoutElement next = it.next();
                        if (Intrinsics.areEqual((Object) next.getName(), (Object) this.CALC)) {
                            this.tabElements.remove(next);
                            break;
                        }
                    }
                } else {
                    this.savedList.remove(i);
                }
                arrayList.addAll(this.savedList);
                Iterator<CalcArticle> it2 = this.savedCalcList.iterator();
                while (true) {
                    if (!it2.hasNext()) {
                        break;
                    }
                    CalcArticle next2 = it2.next();
                    if (Intrinsics.areEqual((Object) next2.getCalcId(), (Object) calcId)) {
                        this.savedCalcList.remove(next2);
                        break;
                    }
                }
            } else {
                throw new NullPointerException("null cannot be cast to non-null type com.medscape.android.activity.calc.model.CalcArticle");
            }
        } else {
            calcArticle = this.savedCalcList.get(i);
            String calcId2 = calcArticle.getCalcId();
            Intrinsics.checkNotNullExpressionValue(calcId2, "calcArticle.calcId");
            this.savedCalcList.remove(i);
            arrayList.addAll(this.savedCalcList);
            Iterator<Object> it3 = this.savedList.iterator();
            while (true) {
                if (!it3.hasNext()) {
                    break;
                }
                Object next3 = it3.next();
                if ((next3 instanceof CalcArticle) && Intrinsics.areEqual((Object) ((CalcArticle) next3).getCalcId(), (Object) calcId2)) {
                    int indexOf = this.savedList.indexOf(next3);
                    int[] iArr3 = this.dataSavedSize;
                    if (iArr3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("dataSavedSize");
                    }
                    if (iArr3[2] == 0) {
                        int i3 = indexOf - 1;
                        this.savedList.remove(i3);
                        this.savedList.remove(indexOf);
                        this.savedList.remove(i3);
                    } else {
                        this.savedList.remove(next3);
                    }
                }
            }
            if (this.savedCalcList.size() == 0) {
                Iterator<TabLayoutElement> it4 = this.tabElements.iterator();
                while (true) {
                    if (!it4.hasNext()) {
                        break;
                    }
                    TabLayoutElement next4 = it4.next();
                    if (Intrinsics.areEqual((Object) next4.getName(), (Object) this.CALC)) {
                        this.tabElements.remove(next4);
                        break;
                    }
                }
            }
        }
        Context context = appCompatActivity;
        deleteCalcArticleFromDB(calcArticle, context);
        OmnitureManager.get().trackModule(context, Constants.OMNITURE_CHANNEL_REFERENCE, "save", Constants.OMNITURE_MLINK_UNSAVE, (Map<String, Object>) null);
    }

    private final void deleteCalcArticleFromDB(CalcArticle calcArticle, Context context) {
        try {
            ContentResolver contentResolver = context.getContentResolver();
            Uri uri = CalcArticle.CalcArticles.CONTENT_URI;
            String[] strArr = new String[2];
            String calcId = calcArticle.getCalcId();
            Intrinsics.checkNotNullExpressionValue(calcId, "calcArticle.calcId");
            if (calcId != null) {
                strArr[0] = StringsKt.trim((CharSequence) calcId).toString();
                String title = calcArticle.getTitle();
                Intrinsics.checkNotNullExpressionValue(title, "calcArticle.title");
                if (title != null) {
                    strArr[1] = StringsKt.trim((CharSequence) title).toString();
                    contentResolver.delete(uri, "calcId like ? OR title like ?", strArr);
                    String calcId2 = calcArticle.getCalcId();
                    Intrinsics.checkNotNullExpressionValue(calcId2, "calcArticle.calcId");
                    updateDbContentValues(context, calcId2);
                    return;
                }
                throw new NullPointerException("null cannot be cast to non-null type kotlin.CharSequence");
            }
            throw new NullPointerException("null cannot be cast to non-null type kotlin.CharSequence");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private final void updateDbContentValues(Context context, String str) {
        DBContentItem contentItemForIdentifier = ContentDataManager.getInstance().getContentItemForIdentifier(str);
        if (contentItemForIdentifier == null) {
            contentItemForIdentifier = ContentDataManager.getInstance().getContentItemForIdentifier(Util.getCalculatorId(context, str, false));
        }
        Intrinsics.checkNotNullExpressionValue(contentItemForIdentifier, "dbContentItem");
        contentItemForIdentifier.setIsFavorite(false);
        contentItemForIdentifier.update();
    }

    private final void deleteNewsFromDB(String str, Context context) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("isSaved", 0);
        String[] strArr = new String[1];
        CharSequence charSequence = str;
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
        strArr[0] = charSequence.subSequence(i, length + 1).toString();
        new CacheManager(context).updateCache(contentValues, "url = ?", strArr);
    }

    /* JADX WARNING: Removed duplicated region for block: B:35:0x00b4  */
    /* JADX WARNING: Removed duplicated region for block: B:97:0x01a6 A[EDGE_INSN: B:97:0x01a6->B:93:0x01a6 ?: BREAK  , SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void deleteCRArticle(int r12, androidx.appcompat.app.AppCompatActivity r13) {
        /*
            r11 = this;
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.util.List r0 = (java.util.List) r0
            int[] r1 = r11.dataSavedSize
            java.lang.String r2 = "dataSavedSize"
            if (r1 != 0) goto L_0x0010
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r2)
        L_0x0010:
            r3 = 1
            r4 = r1[r3]
            int r4 = r4 + -1
            r1[r3] = r4
            int r1 = r11.activePosition
            java.lang.String r4 = "articleId"
            java.lang.String r5 = "uniqueId"
            java.lang.String r6 = "crRefList"
            if (r1 != 0) goto L_0x00de
            java.util.List<java.lang.Object> r1 = r11.savedList
            java.lang.Object r1 = r1.get(r12)
            java.lang.String r7 = "null cannot be cast to non-null type com.medscape.android.reference.model.ClinicalReferenceArticle"
            if (r1 == 0) goto L_0x00d8
            com.medscape.android.reference.model.ClinicalReferenceArticle r1 = (com.medscape.android.reference.model.ClinicalReferenceArticle) r1
            int r1 = r1.getUniqueId()
            if (r1 != 0) goto L_0x0048
            java.util.List<java.lang.Object> r1 = r11.savedList
            java.lang.Object r1 = r1.get(r12)
            if (r1 == 0) goto L_0x0042
            com.medscape.android.reference.model.ClinicalReferenceArticle r1 = (com.medscape.android.reference.model.ClinicalReferenceArticle) r1
            int r1 = r1.getArticleId()
            goto L_0x0057
        L_0x0042:
            java.lang.NullPointerException r12 = new java.lang.NullPointerException
            r12.<init>(r7)
            throw r12
        L_0x0048:
            java.util.List<java.lang.Object> r1 = r11.savedList
            java.lang.Object r1 = r1.get(r12)
            if (r1 == 0) goto L_0x00d2
            com.medscape.android.reference.model.ClinicalReferenceArticle r1 = (com.medscape.android.reference.model.ClinicalReferenceArticle) r1
            int r1 = r1.getUniqueId()
            r4 = r5
        L_0x0057:
            int[] r5 = r11.dataSavedSize
            if (r5 != 0) goto L_0x005e
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r2)
        L_0x005e:
            r2 = r5[r3]
            if (r2 != 0) goto L_0x0097
            java.util.List<java.lang.Object> r2 = r11.savedList
            int r3 = r12 + -1
            r2.remove(r3)
            java.util.List<java.lang.Object> r2 = r11.savedList
            r2.remove(r12)
            java.util.List<java.lang.Object> r12 = r11.savedList
            r12.remove(r3)
            java.util.List<com.medscape.android.activity.saved.model.TabLayoutElement> r12 = r11.tabElements
            java.util.Iterator r12 = r12.iterator()
        L_0x0079:
            boolean r2 = r12.hasNext()
            if (r2 == 0) goto L_0x009c
            java.lang.Object r2 = r12.next()
            com.medscape.android.activity.saved.model.TabLayoutElement r2 = (com.medscape.android.activity.saved.model.TabLayoutElement) r2
            java.lang.String r3 = r2.getName()
            java.lang.String r5 = r11.REF
            boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r3, (java.lang.Object) r5)
            if (r3 == 0) goto L_0x0079
            java.util.List<com.medscape.android.activity.saved.model.TabLayoutElement> r12 = r11.tabElements
            r12.remove(r2)
            goto L_0x009c
        L_0x0097:
            java.util.List<java.lang.Object> r2 = r11.savedList
            r2.remove(r12)
        L_0x009c:
            java.util.List<java.lang.Object> r12 = r11.savedList
            java.util.Collection r12 = (java.util.Collection) r12
            r0.addAll(r12)
            java.util.List<com.medscape.android.reference.model.ClinicalReferenceArticle> r12 = r11.crRefList
            if (r12 != 0) goto L_0x00aa
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r6)
        L_0x00aa:
            java.util.Iterator r12 = r12.iterator()
        L_0x00ae:
            boolean r0 = r12.hasNext()
            if (r0 == 0) goto L_0x01a6
            java.lang.Object r0 = r12.next()
            com.medscape.android.reference.model.ClinicalReferenceArticle r0 = (com.medscape.android.reference.model.ClinicalReferenceArticle) r0
            int r2 = r0.getArticleId()
            if (r2 == r1) goto L_0x00c6
            int r2 = r0.getUniqueId()
            if (r2 != r1) goto L_0x00ae
        L_0x00c6:
            java.util.List<com.medscape.android.reference.model.ClinicalReferenceArticle> r12 = r11.crRefList
            if (r12 != 0) goto L_0x00cd
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r6)
        L_0x00cd:
            r12.remove(r0)
            goto L_0x01a6
        L_0x00d2:
            java.lang.NullPointerException r12 = new java.lang.NullPointerException
            r12.<init>(r7)
            throw r12
        L_0x00d8:
            java.lang.NullPointerException r12 = new java.lang.NullPointerException
            r12.<init>(r7)
            throw r12
        L_0x00de:
            java.util.List<com.medscape.android.reference.model.ClinicalReferenceArticle> r1 = r11.crRefList
            if (r1 != 0) goto L_0x00e5
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r6)
        L_0x00e5:
            java.lang.Object r1 = r1.get(r12)
            com.medscape.android.reference.model.ClinicalReferenceArticle r1 = (com.medscape.android.reference.model.ClinicalReferenceArticle) r1
            int r1 = r1.getUniqueId()
            if (r1 != 0) goto L_0x0103
            java.util.List<com.medscape.android.reference.model.ClinicalReferenceArticle> r1 = r11.crRefList
            if (r1 != 0) goto L_0x00f8
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r6)
        L_0x00f8:
            java.lang.Object r1 = r1.get(r12)
            com.medscape.android.reference.model.ClinicalReferenceArticle r1 = (com.medscape.android.reference.model.ClinicalReferenceArticle) r1
            int r1 = r1.getArticleId()
            goto L_0x0115
        L_0x0103:
            java.util.List<com.medscape.android.reference.model.ClinicalReferenceArticle> r1 = r11.crRefList
            if (r1 != 0) goto L_0x010a
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r6)
        L_0x010a:
            java.lang.Object r1 = r1.get(r12)
            com.medscape.android.reference.model.ClinicalReferenceArticle r1 = (com.medscape.android.reference.model.ClinicalReferenceArticle) r1
            int r1 = r1.getUniqueId()
            r4 = r5
        L_0x0115:
            java.util.List<com.medscape.android.reference.model.ClinicalReferenceArticle> r5 = r11.crRefList
            if (r5 != 0) goto L_0x011c
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r6)
        L_0x011c:
            r5.remove(r12)
            java.util.List<com.medscape.android.reference.model.ClinicalReferenceArticle> r12 = r11.crRefList
            if (r12 != 0) goto L_0x0126
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r6)
        L_0x0126:
            java.util.Collection r12 = (java.util.Collection) r12
            r0.addAll(r12)
            java.util.List<java.lang.Object> r12 = r11.savedList
            java.util.Iterator r12 = r12.iterator()
        L_0x0131:
            boolean r0 = r12.hasNext()
            if (r0 == 0) goto L_0x0176
            java.lang.Object r0 = r12.next()
            boolean r5 = r0 instanceof com.medscape.android.reference.model.ClinicalReferenceArticle
            if (r5 == 0) goto L_0x0131
            r5 = r0
            com.medscape.android.reference.model.ClinicalReferenceArticle r5 = (com.medscape.android.reference.model.ClinicalReferenceArticle) r5
            int r7 = r5.getArticleId()
            if (r7 == r1) goto L_0x014e
            int r5 = r5.getUniqueId()
            if (r5 != r1) goto L_0x0131
        L_0x014e:
            java.util.List<java.lang.Object> r12 = r11.savedList
            int r12 = r12.indexOf(r0)
            int[] r5 = r11.dataSavedSize
            if (r5 != 0) goto L_0x015b
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r2)
        L_0x015b:
            r2 = r5[r3]
            if (r2 != 0) goto L_0x0171
            java.util.List<java.lang.Object> r0 = r11.savedList
            int r2 = r12 + -1
            r0.remove(r2)
            java.util.List<java.lang.Object> r0 = r11.savedList
            r0.remove(r12)
            java.util.List<java.lang.Object> r12 = r11.savedList
            r12.remove(r2)
            goto L_0x0176
        L_0x0171:
            java.util.List<java.lang.Object> r12 = r11.savedList
            r12.remove(r0)
        L_0x0176:
            java.util.List<com.medscape.android.reference.model.ClinicalReferenceArticle> r12 = r11.crRefList
            if (r12 != 0) goto L_0x017d
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r6)
        L_0x017d:
            int r12 = r12.size()
            if (r12 != 0) goto L_0x01a6
            java.util.List<com.medscape.android.activity.saved.model.TabLayoutElement> r12 = r11.tabElements
            java.util.Iterator r12 = r12.iterator()
        L_0x0189:
            boolean r0 = r12.hasNext()
            if (r0 == 0) goto L_0x01a6
            java.lang.Object r0 = r12.next()
            com.medscape.android.activity.saved.model.TabLayoutElement r0 = (com.medscape.android.activity.saved.model.TabLayoutElement) r0
            java.lang.String r2 = r0.getName()
            java.lang.String r3 = r11.REF
            boolean r2 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r2, (java.lang.Object) r3)
            if (r2 == 0) goto L_0x0189
            java.util.List<com.medscape.android.activity.saved.model.TabLayoutElement> r12 = r11.tabElements
            r12.remove(r0)
        L_0x01a6:
            r6 = r13
            android.content.Context r6 = (android.content.Context) r6
            r11.deleteCRArticleFromDB(r1, r4, r6)
            com.medscape.android.BI.omniture.OmnitureManager r5 = com.medscape.android.BI.omniture.OmnitureManager.get()
            r10 = 0
            java.lang.String r7 = "reference and tools"
            java.lang.String r8 = "save"
            java.lang.String r9 = "delete"
            r5.trackModule(r6, r7, r8, r9, r10)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.medscape.android.activity.saved.viewmodel.SavedFeedViewModel.deleteCRArticle(int, androidx.appcompat.app.AppCompatActivity):void");
    }

    private final void deleteCRArticleFromDB(int i, String str, Context context) {
        try {
            if (Intrinsics.areEqual((Object) str, (Object) "articleId")) {
                context.getContentResolver().delete(ClinicalReferenceArticle.ClinicalReferenceArticles.CONTENT_URI, "articleId= ?", new String[]{String.valueOf(i)});
                return;
            }
            context.getContentResolver().delete(ClinicalReferenceArticle.ClinicalReferenceArticles.CONTENT_URI, "uniqueId= ?", new String[]{String.valueOf(i)});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
