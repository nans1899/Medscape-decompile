package com.wbmd.decisionpoint.viewmodels;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import com.android.volley.Response;
import com.google.gson.Gson;
import com.wbmd.decisionpoint.Constants;
import com.wbmd.decisionpoint.domain.decisionpoints.DecisionPoint;
import com.wbmd.decisionpoint.domain.decisionpoints.DecisionPoints;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import org.json.JSONObject;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Lorg/json/JSONObject;", "kotlin.jvm.PlatformType", "onResponse"}, k = 3, mv = {1, 4, 0})
/* compiled from: HubViewModel.kt */
final class HubViewModel$getDecisionPointsFromServer$request$1<T> implements Response.Listener<JSONObject> {
    final /* synthetic */ boolean $isAvailable;
    final /* synthetic */ View $layout;
    final /* synthetic */ HubViewModel this$0;

    HubViewModel$getDecisionPointsFromServer$request$1(HubViewModel hubViewModel, boolean z, View view) {
        this.this$0 = hubViewModel;
        this.$isAvailable = z;
        this.$layout = view;
    }

    public final void onResponse(JSONObject jSONObject) {
        List<DecisionPoint> data = ((DecisionPoints) new Gson().fromJson(jSONObject.toString(), DecisionPoints.class)).getData();
        if (!this.$isAvailable) {
            HubViewModel hubViewModel = this.this$0;
            Context context = this.$layout.getContext();
            Intrinsics.checkNotNullExpressionValue(context, "layout.context");
            hubViewModel.addDecisionPoints(data, context);
        }
        SharedPreferences access$getPreferences$p = HubViewModel.access$getPreferences$p(this.this$0);
        String jSONObject2 = jSONObject.toString();
        SharedPreferences.Editor edit = access$getPreferences$p.edit();
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(String.class);
        if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Boolean.TYPE))) {
            if (jSONObject2 != null) {
                edit.putBoolean(Constants.PREF_KEY_CACHED_DECISION_POINT, ((Boolean) jSONObject2).booleanValue());
            } else {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.Boolean");
            }
        } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Float.TYPE))) {
            if (jSONObject2 != null) {
                edit.putFloat(Constants.PREF_KEY_CACHED_DECISION_POINT, ((Float) jSONObject2).floatValue());
            } else {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.Float");
            }
        } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Integer.TYPE))) {
            if (jSONObject2 != null) {
                edit.putInt(Constants.PREF_KEY_CACHED_DECISION_POINT, ((Integer) jSONObject2).intValue());
            } else {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.Int");
            }
        } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(Long.TYPE))) {
            if (jSONObject2 != null) {
                edit.putLong(Constants.PREF_KEY_CACHED_DECISION_POINT, ((Long) jSONObject2).longValue());
            } else {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.Long");
            }
        } else if (Intrinsics.areEqual((Object) orCreateKotlinClass, (Object) Reflection.getOrCreateKotlinClass(String.class))) {
            if (jSONObject2 != null) {
                edit.putString(Constants.PREF_KEY_CACHED_DECISION_POINT, jSONObject2);
            } else {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.String");
            }
        } else if (jSONObject2 instanceof Set) {
            edit.putStringSet(Constants.PREF_KEY_CACHED_DECISION_POINT, (Set) jSONObject2);
        }
        edit.apply();
    }
}
