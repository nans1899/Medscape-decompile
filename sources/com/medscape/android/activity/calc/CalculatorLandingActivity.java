package com.medscape.android.activity.calc;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.google.android.material.snackbar.Snackbar;
import com.medscape.android.BI.omniture.OmnitureManager;
import com.medscape.android.Constants;
import com.medscape.android.R;
import com.medscape.android.activity.calc.fragments.GroupedListFragment;
import com.medscape.android.activity.calc.helper.CalcOmnitureHelper;
import com.medscape.android.base.BaseActivity;
import com.medscape.android.util.Util;
import com.wbmd.qxcalculator.LaunchQxCallback;
import com.wbmd.qxcalculator.fragments.common.RefreshingFragment;
import com.wbmd.qxcalculator.managers.ContentDataManager;
import com.wbmd.qxcalculator.managers.DataManager;
import com.wbmd.qxcalculator.managers.UserManager;
import com.wbmd.qxcalculator.model.api.ApiResultType;
import com.wbmd.qxcalculator.model.contentItems.common.Category;
import com.wbmd.qxcalculator.model.db.DBContentItem;
import com.wbmd.qxcalculator.util.RowItemBuilder;
import com.wbmd.qxcalculator.util.legacy.ContentParser;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.Job;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0016\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u00042\u00020\u0005B\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\bH\u0002J\u0010\u0010\u000e\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J*\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u00122\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u000e\u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\u0016\u0018\u00010\u0015H\u0002J\u001a\u0010\u0017\u001a\n\u0012\u0004\u0012\u00020\u0019\u0018\u00010\u00182\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0002J\b\u0010\u0007\u001a\u00020\bH\u0016J\u0016\u0010\u001c\u001a\u00020\f2\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00130\u0015H\u0002J\u0012\u0010\u001e\u001a\u00020\f2\b\u0010\u001f\u001a\u0004\u0018\u00010\u0010H\u0016J\u0012\u0010 \u001a\u00020\f2\b\u0010!\u001a\u0004\u0018\u00010\u0010H\u0014J\u0010\u0010\"\u001a\u00020\b2\u0006\u0010#\u001a\u00020$H\u0016J\b\u0010%\u001a\u00020\fH\u0016J\u0010\u0010&\u001a\u00020\b2\u0006\u0010'\u001a\u00020(H\u0016J\u0018\u0010)\u001a\u00020\f2\u0006\u0010*\u001a\u00020\u00162\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0012\u0010+\u001a\u00020\f2\b\u0010\u001f\u001a\u0004\u0018\u00010\u0010H\u0016J\u0012\u0010,\u001a\u00020\f2\b\u0010\u001f\u001a\u0004\u0018\u00010\u0010H\u0002J\b\u0010-\u001a\u00020\fH\u0002J\b\u0010.\u001a\u00020\fH\u0002J\b\u0010/\u001a\u00020\fH\u0002R\u000e\u0010\u0007\u001a\u00020\bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX.¢\u0006\u0002\n\u0000¨\u00060"}, d2 = {"Lcom/medscape/android/activity/calc/CalculatorLandingActivity;", "Lcom/medscape/android/base/BaseActivity;", "Lcom/wbmd/qxcalculator/fragments/common/RefreshingFragment$ContentRefreshListener;", "Lcom/wbmd/qxcalculator/managers/ContentDataManager$OnContentUpdateListener;", "Lcom/wbmd/qxcalculator/managers/ContentDataManager$OnRefreshListener;", "Lcom/wbmd/qxcalculator/LaunchQxCallback;", "()V", "isRefreshing", "", "loadingView", "Landroid/widget/LinearLayout;", "contentItemRefreshComplete", "", "success", "fetchUpdatesAndNewItems", "bundle", "Landroid/os/Bundle;", "getContentIdentifiers", "Ljava/util/ArrayList;", "", "contentItems", "", "Lcom/wbmd/qxcalculator/model/db/DBContentItem;", "getDbContentItemIdsToUpdate", "", "", "dbContentItemIds", "", "notifyOfNewItems", "identifiers", "onContentUpdateCompleted", "results", "onCreate", "savedInstanceState", "onCreateOptionsMenu", "menu", "Landroid/view/Menu;", "onMenuItemUpdateCompleted", "onOptionsItemSelected", "item", "Landroid/view/MenuItem;", "onQxItemClicked", "contentItem", "onRefreshCompleted", "refreshCompletion", "refreshContent", "sendOmniturePing", "syncOrRefreshDataIfNeeded", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: CalculatorLandingActivity.kt */
public final class CalculatorLandingActivity extends BaseActivity implements RefreshingFragment.ContentRefreshListener, ContentDataManager.OnContentUpdateListener, ContentDataManager.OnRefreshListener, LaunchQxCallback {
    private HashMap _$_findViewCache;
    private boolean isRefreshing;
    private LinearLayout loadingView;

    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View findViewById = findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), findViewById);
        return findViewById;
    }

    public void onMenuItemUpdateCompleted() {
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_calculator_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        View findViewById = findViewById(R.id.progress_layout);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(R.id.progress_layout)");
        LinearLayout linearLayout = (LinearLayout) findViewById;
        this.loadingView = linearLayout;
        if (linearLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("loadingView");
        }
        linearLayout.setVisibility(0);
        setSupportActionBar(toolbar);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.container, new GroupedListFragment().newInstance(this, false)).commit();
        if (getIntent().getBooleanExtra(Constants.EXTRA_PUSH_LAUNCH, false)) {
            getIntent().removeExtra(Constants.EXTRA_PUSH_LAUNCH);
            OmnitureManager.get().markModule("push", Constants.OMNITURE_MLINK_OPEN, (Map<String, Object>) null);
        }
        sendOmniturePing();
        Job unused = BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getDefault(), (CoroutineStart) null, new CalculatorLandingActivity$onCreate$1(this, (Continuation) null), 2, (Object) null);
    }

    private final void sendOmniturePing() {
        this.mPvid = OmnitureManager.get().trackPageView(this, Constants.OMNITURE_CHANNEL_REFERENCE, "calc/browse", "view", (String) null, (String) null, (Map<String, Object>) null);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        Intrinsics.checkNotNullParameter(menu, Category.K_MENU_CATEGORY);
        getMenuInflater().inflate(R.menu.menu_search, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        Intrinsics.checkNotNullParameter(menuItem, ContentParser.ITEM);
        int itemId = menuItem.getItemId();
        if (itemId == R.id.search) {
            this.mPvid = CalcOmnitureHelper.INSTANCE.sendOmnitureCall(this, "calcssearch", "ref", "calc/search/recently-viewed");
            startActivity(new Intent(this, SearchCalcActivity.class));
            overridePendingTransition(R.anim.open_enter_modal, R.anim.open_exit_modal);
            return true;
        } else if (itemId != 16908332) {
            return super.onOptionsItemSelected(menuItem);
        } else {
            onBackPressed();
            return true;
        }
    }

    public void onQxItemClicked(DBContentItem dBContentItem, Bundle bundle) {
        Intrinsics.checkNotNullParameter(dBContentItem, "contentItem");
        Intrinsics.checkNotNullParameter(bundle, "bundle");
        bundle.putString("pvid", this.mPvid);
        Util.openQxItem(this, dBContentItem, bundle);
    }

    public boolean isRefreshing() {
        return this.isRefreshing;
    }

    /* access modifiers changed from: private */
    public final void syncOrRefreshDataIfNeeded() {
        if (UserManager.getInstance().accountInitialized()) {
            DataManager.getInstance().appHasStarted = true;
            ContentDataManager instance = ContentDataManager.getInstance();
            Intrinsics.checkNotNullExpressionValue(instance, "ContentDataManager.getInstance()");
            if (instance.getIsFetchingUpdates()) {
                ContentDataManager instance2 = ContentDataManager.getInstance();
                Intrinsics.checkNotNullExpressionValue(instance2, "ContentDataManager.getInstance()");
                instance2.setOnContentUpdateListener(this);
            } else {
                ContentDataManager instance3 = ContentDataManager.getInstance();
                Intrinsics.checkNotNullExpressionValue(instance3, "ContentDataManager.getInstance()");
                if (instance3.getIsRefreshing()) {
                    this.isRefreshing = true;
                    ContentDataManager instance4 = ContentDataManager.getInstance();
                    Intrinsics.checkNotNullExpressionValue(instance4, "ContentDataManager.getInstance()");
                    instance4.setOnRefreshListener(this);
                }
            }
            refreshContent();
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x00c3, code lost:
        r7 = r2.getId();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void refreshContent() {
        /*
            r15 = this;
            r0 = 1
            r15.isRefreshing = r0
            com.wbmd.qxcalculator.managers.ContentDataManager r1 = com.wbmd.qxcalculator.managers.ContentDataManager.getInstance()
            java.lang.String r2 = "ContentDataManager.getInstance()"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r2)
            r2 = r15
            com.wbmd.qxcalculator.managers.ContentDataManager$OnRefreshListener r2 = (com.wbmd.qxcalculator.managers.ContentDataManager.OnRefreshListener) r2
            r1.setOnRefreshListener(r2)
            com.wbmd.mapper.classes.WBMDMappingProvider r1 = new com.wbmd.mapper.classes.WBMDMappingProvider
            com.wbmd.mapper.classes.WBMDMappingType r2 = com.wbmd.mapper.classes.WBMDMappingType.PROFESSION
            java.lang.String r3 = "profession_mapping.json"
            r1.<init>(r3, r2)
            com.wbmd.mapper.classes.WBMDMappingProvider r2 = new com.wbmd.mapper.classes.WBMDMappingProvider
            com.wbmd.mapper.classes.WBMDMappingType r3 = com.wbmd.mapper.classes.WBMDMappingType.SPECIALTY
            java.lang.String r4 = "specialty_mapping.json"
            r2.<init>(r4, r3)
            com.wbmd.mapper.classes.WBMDMappingProvider r3 = new com.wbmd.mapper.classes.WBMDMappingProvider
            com.wbmd.mapper.classes.WBMDMappingType r4 = com.wbmd.mapper.classes.WBMDMappingType.LOCATION
            java.lang.String r5 = "country_mapping.json"
            r3.<init>(r5, r4)
            com.wbmd.mapper.classes.WBMDMapper$Companion r4 = com.wbmd.mapper.classes.WBMDMapper.Companion
            com.wbmd.mapper.classes.WBMDMapper r4 = r4.getInstance()
            if (r4 == 0) goto L_0x004a
            r5 = 3
            com.wbmd.mapper.classes.WBMDMappingProvider[] r5 = new com.wbmd.mapper.classes.WBMDMappingProvider[r5]
            r6 = 0
            r5[r6] = r1
            r5[r0] = r2
            r1 = 2
            r5[r1] = r3
            java.util.List r1 = kotlin.collections.CollectionsKt.listOf(r5)
            r2 = r15
            android.content.Context r2 = (android.content.Context) r2
            r4.addMappingProviders(r1, r2)
        L_0x004a:
            r1 = r15
            android.content.Context r1 = (android.content.Context) r1
            com.medscape.android.Settings r2 = com.medscape.android.Settings.singleton(r1)
            java.lang.String r3 = "wbmd_user_country_code"
            java.lang.String r4 = ""
            java.lang.String r2 = r2.getSetting((java.lang.String) r3, (java.lang.String) r4)
            com.medscape.android.Settings r3 = com.medscape.android.Settings.singleton(r1)
            java.lang.String r5 = "wbmd.medscape.specialty.id"
            java.lang.String r3 = r3.getSetting((java.lang.String) r5, (java.lang.String) r4)
            com.medscape.android.Settings r1 = com.medscape.android.Settings.singleton(r1)
            java.lang.String r5 = "wbmd_professionId"
            java.lang.String r1 = r1.getSetting((java.lang.String) r5, (java.lang.String) r4)
            com.wbmd.mapper.classes.WBMDMapper$Companion r4 = com.wbmd.mapper.classes.WBMDMapper.Companion
            com.wbmd.mapper.classes.WBMDMapper r4 = r4.getInstance()
            r5 = 0
            if (r4 == 0) goto L_0x0082
            java.lang.String r6 = "medscapeUserCountryCode"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r6)
            com.wbmd.mapper.classes.WBMDMappingType r6 = com.wbmd.mapper.classes.WBMDMappingType.LOCATION
            com.wbmd.mapper.model.MappedItem r2 = r4.getAffiliateMapping(r2, r6)
            goto L_0x0083
        L_0x0082:
            r2 = r5
        L_0x0083:
            com.wbmd.mapper.classes.WBMDMapper$Companion r4 = com.wbmd.mapper.classes.WBMDMapper.Companion
            com.wbmd.mapper.classes.WBMDMapper r4 = r4.getInstance()
            if (r4 == 0) goto L_0x0097
            java.lang.String r6 = "medscapeUserSpecialtyId"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r6)
            com.wbmd.mapper.classes.WBMDMappingType r6 = com.wbmd.mapper.classes.WBMDMappingType.SPECIALTY
            com.wbmd.mapper.model.MappedItem r3 = r4.getAffiliateMapping(r3, r6)
            goto L_0x0098
        L_0x0097:
            r3 = r5
        L_0x0098:
            com.wbmd.mapper.classes.WBMDMapper$Companion r4 = com.wbmd.mapper.classes.WBMDMapper.Companion
            com.wbmd.mapper.classes.WBMDMapper r4 = r4.getInstance()
            if (r4 == 0) goto L_0x00ac
            java.lang.String r6 = "medscapeUserProfessionId"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r6)
            com.wbmd.mapper.classes.WBMDMappingType r6 = com.wbmd.mapper.classes.WBMDMappingType.PROFESSION
            com.wbmd.mapper.model.MappedItem r1 = r4.getAffiliateMapping(r1, r6)
            goto L_0x00ad
        L_0x00ac:
            r1 = r5
        L_0x00ad:
            com.wbmd.qxcalculator.managers.UserManager r4 = com.wbmd.qxcalculator.managers.UserManager.getInstance()
            java.lang.String r6 = "UserManager.getInstance()"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r4, r6)
            com.wbmd.qxcalculator.model.db.DBUser r4 = r4.getDbUser()
            java.lang.String r6 = "user"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r4, r6)
            com.wbmd.qxcalculator.model.db.DBLocation r6 = new com.wbmd.qxcalculator.model.db.DBLocation
            if (r2 == 0) goto L_0x00d2
            java.lang.String r7 = r2.getId()
            if (r7 == 0) goto L_0x00d2
            long r7 = java.lang.Long.parseLong(r7)
            java.lang.Long r7 = java.lang.Long.valueOf(r7)
            goto L_0x00d3
        L_0x00d2:
            r7 = r5
        L_0x00d3:
            if (r2 == 0) goto L_0x00e4
            java.lang.String r2 = r2.getId()
            if (r2 == 0) goto L_0x00e4
            long r8 = java.lang.Long.parseLong(r2)
            java.lang.Long r2 = java.lang.Long.valueOf(r8)
            goto L_0x00e5
        L_0x00e4:
            r2 = r5
        L_0x00e5:
            r6.<init>(r7, r2, r5, r5)
            r4.setLocation(r6)
            com.wbmd.qxcalculator.model.db.DBSpecialty r2 = new com.wbmd.qxcalculator.model.db.DBSpecialty
            if (r3 == 0) goto L_0x00ff
            java.lang.String r6 = r3.getId()
            if (r6 == 0) goto L_0x00ff
            long r6 = java.lang.Long.parseLong(r6)
            java.lang.Long r6 = java.lang.Long.valueOf(r6)
            r9 = r6
            goto L_0x0100
        L_0x00ff:
            r9 = r5
        L_0x0100:
            if (r3 == 0) goto L_0x0112
            java.lang.String r3 = r3.getId()
            if (r3 == 0) goto L_0x0112
            long r6 = java.lang.Long.parseLong(r3)
            java.lang.Long r3 = java.lang.Long.valueOf(r6)
            r10 = r3
            goto L_0x0113
        L_0x0112:
            r10 = r5
        L_0x0113:
            r11 = 0
            r12 = 0
            r13 = 0
            r14 = 0
            r8 = r2
            r8.<init>(r9, r10, r11, r12, r13, r14)
            r4.setSpecialty(r2)
            com.wbmd.qxcalculator.model.db.DBProfession r2 = new com.wbmd.qxcalculator.model.db.DBProfession
            if (r1 == 0) goto L_0x0131
            java.lang.String r3 = r1.getId()
            if (r3 == 0) goto L_0x0131
            long r6 = java.lang.Long.parseLong(r3)
            java.lang.Long r3 = java.lang.Long.valueOf(r6)
            goto L_0x0132
        L_0x0131:
            r3 = r5
        L_0x0132:
            if (r1 == 0) goto L_0x0143
            java.lang.String r1 = r1.getId()
            if (r1 == 0) goto L_0x0143
            long r6 = java.lang.Long.parseLong(r1)
            java.lang.Long r1 = java.lang.Long.valueOf(r6)
            goto L_0x0144
        L_0x0143:
            r1 = r5
        L_0x0144:
            r2.<init>(r3, r1, r5, r5)
            r4.setProfession(r2)
            com.wbmd.qxcalculator.managers.ContentDataManager r1 = com.wbmd.qxcalculator.managers.ContentDataManager.getInstance()
            r1.refreshContent(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.medscape.android.activity.calc.CalculatorLandingActivity.refreshContent():void");
    }

    private final void contentItemRefreshComplete(boolean z) {
        if (!z) {
            ContentDataManager instance = ContentDataManager.getInstance();
            RowItemBuilder instance2 = RowItemBuilder.getInstance();
            Intrinsics.checkNotNullExpressionValue(instance2, "RowItemBuilder.getInstance()");
            getContentIdentifiers((Bundle) null, instance.getContentItemsThatWeShouldNotifyAreNowAvailable(instance2.getAllCalcRowItems()));
        }
        Log.d("BROADCAST", "send refresh complete");
        Intent intent = new Intent(RefreshingFragment.KEY_BROADCAST_REFRESH_COMPLETE);
        intent.putExtra(RefreshingFragment.KEY_BROADCAST_REFRESH_SUCCESS_STATUS, z);
        LocalBroadcastManager.getInstance(this).sendBroadcastSync(intent);
    }

    private final void fetchUpdatesAndNewItems(Bundle bundle) {
        ArrayList<String> stringArrayList = bundle.getStringArrayList(ApiResultType.CONTENT_ID_NEW_TO_FETCH);
        List<Long> dbContentItemIdsToUpdate = getDbContentItemIdsToUpdate(bundle.getLongArray(ApiResultType.CONTENT_ITEM_ID_TO_UPDATE));
        if ((stringArrayList == null || !(!stringArrayList.isEmpty())) && (dbContentItemIdsToUpdate == null || !(!dbContentItemIdsToUpdate.isEmpty()))) {
            ContentDataManager instance = ContentDataManager.getInstance();
            RowItemBuilder instance2 = RowItemBuilder.getInstance();
            Intrinsics.checkNotNullExpressionValue(instance2, "RowItemBuilder.getInstance()");
            getContentIdentifiers((Bundle) null, instance.getContentItemsThatWeShouldNotifyAreNowAvailable(instance2.getAllCalcRowItems()));
            return;
        }
        ContentDataManager instance3 = ContentDataManager.getInstance();
        Intrinsics.checkNotNullExpressionValue(instance3, "ContentDataManager.getInstance()");
        instance3.setOnContentUpdateListener(this);
        Log.d("API", "contentItemIdsToFtech: " + stringArrayList + ";\ndbContentItemIdsToUpdate: " + dbContentItemIdsToUpdate);
        ContentDataManager.getInstance().fetchContentItems(stringArrayList, dbContentItemIdsToUpdate);
    }

    private final ArrayList<String> getContentIdentifiers(Bundle bundle, List<? extends DBContentItem> list) {
        ArrayList<String> stringArrayList = bundle != null ? bundle.getStringArrayList(ContentDataManager.KEY_NEW_CONTENT_ITEM_IDENTIFIERS) : null;
        if (stringArrayList == null) {
            stringArrayList = new ArrayList<>();
        }
        if (list != null) {
            for (DBContentItem dBContentItem : list) {
                if (!stringArrayList.contains(dBContentItem.getIdentifier())) {
                    stringArrayList.add(dBContentItem.getIdentifier());
                }
            }
        }
        if (!stringArrayList.isEmpty()) {
            notifyOfNewItems(stringArrayList);
        }
        return stringArrayList;
    }

    private final void notifyOfNewItems(List<String> list) {
        ContentDataManager instance = ContentDataManager.getInstance();
        Intrinsics.checkNotNullExpressionValue(instance, "ContentDataManager.getInstance()");
        instance.setOnContentUpdateListener((ContentDataManager.OnContentUpdateListener) null);
        Snackbar.make(findViewById(R.id.container), (CharSequence) getResources().getQuantityString(R.plurals.newly_downloaded_item_count, list.size(), new Object[]{Integer.valueOf(list.size())}), 0).show();
    }

    private final List<Long> getDbContentItemIdsToUpdate(long[] jArr) {
        List<Long> list = null;
        if (jArr != null) {
            list = new ArrayList<>(jArr.length);
            for (long valueOf : jArr) {
                list.add(Long.valueOf(valueOf));
            }
        }
        return list;
    }

    private final void refreshCompletion(Bundle bundle) {
        this.isRefreshing = false;
        Boolean valueOf = bundle != null ? Boolean.valueOf(bundle.getBoolean(ApiResultType.SUCCESS, false)) : null;
        if (valueOf != null) {
            boolean booleanValue = valueOf.booleanValue();
            contentItemRefreshComplete(booleanValue);
            LinearLayout linearLayout = this.loadingView;
            if (linearLayout == null) {
                Intrinsics.throwUninitializedPropertyAccessException("loadingView");
            }
            linearLayout.setVisibility(8);
            if (booleanValue) {
                fetchUpdatesAndNewItems(bundle);
            }
        }
    }

    public void onContentUpdateCompleted(Bundle bundle) {
        ContentDataManager instance = ContentDataManager.getInstance();
        RowItemBuilder instance2 = RowItemBuilder.getInstance();
        Intrinsics.checkNotNullExpressionValue(instance2, "RowItemBuilder.getInstance()");
        getContentIdentifiers(bundle, instance.getContentItemsThatWeShouldNotifyAreNowAvailable(instance2.getAllCalcRowItems()));
        if (this.isRefreshing) {
            this.isRefreshing = false;
        }
        invalidateOptionsMenu();
    }

    public void onRefreshCompleted(Bundle bundle) {
        ContentDataManager instance = ContentDataManager.getInstance();
        Intrinsics.checkNotNullExpressionValue(instance, "ContentDataManager.getInstance()");
        instance.setOnRefreshListener((ContentDataManager.OnRefreshListener) null);
        refreshCompletion(bundle);
    }
}
