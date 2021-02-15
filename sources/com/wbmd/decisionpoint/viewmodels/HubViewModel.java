package com.wbmd.decisionpoint.viewmodels;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.wbmd.decisionpoint.Constants;
import com.wbmd.decisionpoint.domain.contributors.Contributor;
import com.wbmd.decisionpoint.domain.contributors.ContributorsData;
import com.wbmd.decisionpoint.domain.contributors.ContributorsType;
import com.wbmd.decisionpoint.domain.decisionpoints.DecisionPoint;
import com.wbmd.decisionpoint.ui.adapters.HubAdapter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\"\u0010\f\u001a\u00020\r2\u0010\u0010\u000e\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0010\u0018\u00010\u000f2\u0006\u0010\u0011\u001a\u00020\u0012H\u0002J\"\u0010\u0013\u001a\u0012\u0012\u0004\u0012\u00020\u00150\u0014j\b\u0012\u0004\u0012\u00020\u0015`\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0007J\u0010\u0010\u0019\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u0012H\u0002J\u000e\u0010\u001a\u001a\u00020\r2\u0006\u0010\u001b\u001a\u00020\u001cJ\u0018\u0010\u001d\u001a\u00020\r2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001e\u001a\u00020\u0007H\u0002J\u0006\u0010\u001f\u001a\u00020\u0004J\u000e\u0010 \u001a\u00020\r2\u0006\u0010\u0003\u001a\u00020\u0004J\u0006\u0010!\u001a\u00020\rR\u000e\u0010\u0003\u001a\u00020\u0004X.¢\u0006\u0002\n\u0000R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u000e\u0010\n\u001a\u00020\u000bX.¢\u0006\u0002\n\u0000¨\u0006\""}, d2 = {"Lcom/wbmd/decisionpoint/viewmodels/HubViewModel;", "Landroidx/lifecycle/ViewModel;", "()V", "adapter", "Lcom/wbmd/decisionpoint/ui/adapters/HubAdapter;", "loadingData", "Landroidx/lifecycle/MutableLiveData;", "", "getLoadingData", "()Landroidx/lifecycle/MutableLiveData;", "preferences", "Landroid/content/SharedPreferences;", "addDecisionPoints", "", "decisionItems", "", "Lcom/wbmd/decisionpoint/domain/decisionpoints/DecisionPoint;", "context", "Landroid/content/Context;", "generateContributors", "Ljava/util/ArrayList;", "Lcom/wbmd/decisionpoint/ui/adapters/HubAdapter$Row;", "Lkotlin/collections/ArrayList;", "contributorData", "Lcom/wbmd/decisionpoint/domain/contributors/ContributorsData;", "getContributors", "getDecisionPoints", "layout", "Landroid/view/View;", "getDecisionPointsFromServer", "isAvailable", "getHubAdapter", "setHubAdapter", "setItems", "wbmd.decisionpoint_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: HubViewModel.kt */
public final class HubViewModel extends ViewModel {
    /* access modifiers changed from: private */
    public HubAdapter adapter;
    private final MutableLiveData<Boolean> loadingData = new MutableLiveData<>(false);
    /* access modifiers changed from: private */
    public SharedPreferences preferences;

    public static final /* synthetic */ HubAdapter access$getAdapter$p(HubViewModel hubViewModel) {
        HubAdapter hubAdapter = hubViewModel.adapter;
        if (hubAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
        }
        return hubAdapter;
    }

    public static final /* synthetic */ SharedPreferences access$getPreferences$p(HubViewModel hubViewModel) {
        SharedPreferences sharedPreferences = hubViewModel.preferences;
        if (sharedPreferences == null) {
            Intrinsics.throwUninitializedPropertyAccessException("preferences");
        }
        return sharedPreferences;
    }

    public final MutableLiveData<Boolean> getLoadingData() {
        return this.loadingData;
    }

    public final void setItems() {
        HubAdapter hubAdapter = this.adapter;
        if (hubAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
        }
        hubAdapter.addItems(CollectionsKt.arrayListOf(new HubAdapter.HeaderRow()));
    }

    public final ArrayList<HubAdapter.Row> generateContributors(ContributorsData contributorsData) {
        ArrayList<HubAdapter.Row> arrayList = new ArrayList<>();
        ArrayList<ContributorsType> contributorsType = contributorsData != null ? contributorsData.getContributorsType() : null;
        if (contributorsType != null) {
            for (ContributorsType contributorsType2 : contributorsType) {
                ArrayList<Contributor> data = contributorsType2.getData();
                Collection collection = data;
                if (!(collection == null || collection.isEmpty())) {
                    arrayList.add(new HubAdapter.ContributorTypeRow(contributorsType2.getTitle()));
                    for (Contributor contributorItemRow : data) {
                        arrayList.add(new HubAdapter.ContributorItemRow(contributorItemRow));
                    }
                }
            }
        }
        return arrayList;
    }

    public final void setHubAdapter(HubAdapter hubAdapter) {
        Intrinsics.checkNotNullParameter(hubAdapter, "adapter");
        this.adapter = hubAdapter;
    }

    public final HubAdapter getHubAdapter() {
        HubAdapter hubAdapter = this.adapter;
        if (hubAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
        }
        return hubAdapter;
    }

    /* JADX WARNING: type inference failed for: r0v9, types: [java.lang.Long] */
    /* JADX WARNING: type inference failed for: r0v11, types: [java.lang.Integer] */
    /* JADX WARNING: type inference failed for: r0v13, types: [java.lang.Float] */
    /* JADX WARNING: type inference failed for: r0v15, types: [java.lang.Boolean] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void getDecisionPoints(android.view.View r8) {
        /*
            r7 = this;
            java.lang.String r0 = "layout"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r8, r0)
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r0 = r7.loadingData
            r1 = 1
            java.lang.Boolean r2 = java.lang.Boolean.valueOf(r1)
            r0.postValue(r2)
            android.content.Context r0 = r8.getContext()
            java.lang.String r2 = "decision_point_prefs"
            r3 = 0
            android.content.SharedPreferences r0 = r0.getSharedPreferences(r2, r3)
            java.lang.String r2 = "layout.context.getShared…ES, Context.MODE_PRIVATE)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r2)
            r7.preferences = r0
            if (r0 != 0) goto L_0x0028
            java.lang.String r2 = "preferences"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r2)
        L_0x0028:
            java.lang.String r2 = "cached_decision_point_key"
            java.lang.String r4 = ""
            java.lang.Class<java.lang.String> r5 = java.lang.String.class
            kotlin.reflect.KClass r5 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r5)
            java.lang.Class r6 = java.lang.Boolean.TYPE
            kotlin.reflect.KClass r6 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r6)
            boolean r6 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r5, (java.lang.Object) r6)
            if (r6 == 0) goto L_0x0051
            java.lang.Boolean r4 = (java.lang.Boolean) r4
            boolean r4 = r4.booleanValue()
            boolean r0 = r0.getBoolean(r2, r4)
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)
            r4 = r0
            java.lang.String r4 = (java.lang.String) r4
            goto L_0x00dc
        L_0x0051:
            java.lang.Class r6 = java.lang.Float.TYPE
            kotlin.reflect.KClass r6 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r6)
            boolean r6 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r5, (java.lang.Object) r6)
            if (r6 == 0) goto L_0x006f
            java.lang.Float r4 = (java.lang.Float) r4
            float r4 = r4.floatValue()
            float r0 = r0.getFloat(r2, r4)
            java.lang.Float r0 = java.lang.Float.valueOf(r0)
            r4 = r0
            java.lang.String r4 = (java.lang.String) r4
            goto L_0x00dc
        L_0x006f:
            java.lang.Class r6 = java.lang.Integer.TYPE
            kotlin.reflect.KClass r6 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r6)
            boolean r6 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r5, (java.lang.Object) r6)
            if (r6 == 0) goto L_0x008d
            java.lang.Integer r4 = (java.lang.Integer) r4
            int r4 = r4.intValue()
            int r0 = r0.getInt(r2, r4)
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            r4 = r0
            java.lang.String r4 = (java.lang.String) r4
            goto L_0x00dc
        L_0x008d:
            java.lang.Class r6 = java.lang.Long.TYPE
            kotlin.reflect.KClass r6 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r6)
            boolean r6 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r5, (java.lang.Object) r6)
            if (r6 == 0) goto L_0x00ab
            java.lang.Long r4 = (java.lang.Long) r4
            long r4 = r4.longValue()
            long r4 = r0.getLong(r2, r4)
            java.lang.Long r0 = java.lang.Long.valueOf(r4)
            r4 = r0
            java.lang.String r4 = (java.lang.String) r4
            goto L_0x00dc
        L_0x00ab:
            java.lang.Class<java.lang.String> r6 = java.lang.String.class
            kotlin.reflect.KClass r6 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r6)
            boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r5, (java.lang.Object) r6)
            java.lang.String r6 = "null cannot be cast to non-null type kotlin.String"
            if (r5 == 0) goto L_0x00c6
            java.lang.String r4 = r0.getString(r2, r4)
            if (r4 == 0) goto L_0x00c0
            goto L_0x00dc
        L_0x00c0:
            java.lang.NullPointerException r8 = new java.lang.NullPointerException
            r8.<init>(r6)
            throw r8
        L_0x00c6:
            boolean r5 = r4 instanceof java.util.Set
            if (r5 == 0) goto L_0x00dc
            java.util.Set r4 = (java.util.Set) r4
            java.util.Set r0 = r0.getStringSet(r2, r4)
            if (r0 == 0) goto L_0x00d6
            r4 = r0
            java.lang.String r4 = (java.lang.String) r4
            goto L_0x00dc
        L_0x00d6:
            java.lang.NullPointerException r8 = new java.lang.NullPointerException
            r8.<init>(r6)
            throw r8
        L_0x00dc:
            r0 = r4
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0
            if (r0 == 0) goto L_0x00ea
            int r2 = r0.length()
            if (r2 != 0) goto L_0x00e8
            goto L_0x00ea
        L_0x00e8:
            r2 = 0
            goto L_0x00eb
        L_0x00ea:
            r2 = 1
        L_0x00eb:
            if (r2 != 0) goto L_0x010a
            com.google.gson.Gson r2 = new com.google.gson.Gson
            r2.<init>()
            java.lang.Class<com.wbmd.decisionpoint.domain.decisionpoints.DecisionPoints> r5 = com.wbmd.decisionpoint.domain.decisionpoints.DecisionPoints.class
            java.lang.Object r2 = r2.fromJson((java.lang.String) r4, r5)
            com.wbmd.decisionpoint.domain.decisionpoints.DecisionPoints r2 = (com.wbmd.decisionpoint.domain.decisionpoints.DecisionPoints) r2
            java.util.List r2 = r2.getData()
            android.content.Context r4 = r8.getContext()
            java.lang.String r5 = "layout.context"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r4, r5)
            r7.addDecisionPoints(r2, r4)
        L_0x010a:
            if (r0 == 0) goto L_0x0112
            int r0 = r0.length()
            if (r0 != 0) goto L_0x0113
        L_0x0112:
            r3 = 1
        L_0x0113:
            r0 = r3 ^ 1
            r7.getDecisionPointsFromServer(r8, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wbmd.decisionpoint.viewmodels.HubViewModel.getDecisionPoints(android.view.View):void");
    }

    private final void getDecisionPointsFromServer(View view, boolean z) {
        RequestQueue newRequestQueue = Volley.newRequestQueue(view.getContext());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(0, Constants.ALL_DECISIONPOINTS_URL, (JSONObject) null, new HubViewModel$getDecisionPointsFromServer$request$1(this, z, view), new HubViewModel$getDecisionPointsFromServer$request$2(this, z, view));
        jsonObjectRequest.setShouldCache(false);
        newRequestQueue.add(jsonObjectRequest);
    }

    /* access modifiers changed from: private */
    public final void addDecisionPoints(List<DecisionPoint> list, Context context) {
        ArrayList arrayList = new ArrayList();
        if (list != null) {
            for (DecisionPoint decisionPoint : list) {
                if (decisionPoint != null) {
                    arrayList.add(new HubAdapter.DecisionRow(decisionPoint));
                }
            }
        }
        arrayList.add(new HubAdapter.AnswersRow());
        HubAdapter hubAdapter = this.adapter;
        if (hubAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
        }
        hubAdapter.addItems(arrayList);
        getContributors(context);
    }

    private final void getContributors(Context context) {
        this.loadingData.postValue(true);
        RequestQueue newRequestQueue = Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(0, Constants.CONTRIBUTORS_URL, (JSONObject) null, new HubViewModel$getContributors$request$1(this), new HubViewModel$getContributors$request$2(this));
        jsonObjectRequest.setShouldCache(false);
        newRequestQueue.add(jsonObjectRequest);
    }
}
