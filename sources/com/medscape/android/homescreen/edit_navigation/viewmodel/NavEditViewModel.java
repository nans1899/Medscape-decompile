package com.medscape.android.homescreen.edit_navigation.viewmodel;

import android.content.Context;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.medscape.android.Constants;
import com.medscape.android.MedscapeApplication;
import com.medscape.android.analytics.remoteconfig.reference.FeatureConfigManager;
import com.medscape.android.analytics.remoteconfig.reference.FeatureConfigModel;
import com.medscape.android.auth.AuthenticationManager;
import com.medscape.android.homescreen.home_nav_tray.helpers.HomeNavMenuConverter;
import com.medscape.android.homescreen.home_nav_tray.models.NavItem;
import com.medscape.android.homescreen.home_nav_tray.repositories.NavItemManager;
import com.medscape.android.provider.SharedPreferenceProvider;
import com.wbmd.qxcalculator.util.legacy.ContentParser;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.joda.time.DateTimeConstants;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0011\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u0014\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00190\u00182\u0006\u0010\u001a\u001a\u00020\u001bJ\u0016\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00190\u00182\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bJ\u0012\u0010\u001d\u001a\u000e\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u00020 0\u001eJ\"\u0010!\u001a\u00020\u000b2\u0012\u0010\"\u001a\u000e\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u00020 0\u001e2\u0006\u0010#\u001a\u00020\u0019R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR \u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\f\"\u0004\b\r\u0010\u000eR\u0011\u0010\u000f\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0013\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0004¨\u0006$"}, d2 = {"Lcom/medscape/android/homescreen/edit_navigation/viewmodel/NavEditViewModel;", "Landroidx/lifecycle/ViewModel;", "sharePrefProvider", "Lcom/medscape/android/provider/SharedPreferenceProvider;", "(Lcom/medscape/android/provider/SharedPreferenceProvider;)V", "converter", "Lcom/medscape/android/homescreen/home_nav_tray/helpers/HomeNavMenuConverter;", "getConverter", "()Lcom/medscape/android/homescreen/home_nav_tray/helpers/HomeNavMenuConverter;", "isEditMode", "Landroidx/lifecycle/MutableLiveData;", "", "()Landroidx/lifecycle/MutableLiveData;", "setEditMode", "(Landroidx/lifecycle/MutableLiveData;)V", "navManager", "Lcom/medscape/android/homescreen/home_nav_tray/repositories/NavItemManager;", "getNavManager", "()Lcom/medscape/android/homescreen/home_nav_tray/repositories/NavItemManager;", "sharePref", "getSharePref", "()Lcom/medscape/android/provider/SharedPreferenceProvider;", "setSharePref", "getItems", "", "Lcom/medscape/android/homescreen/home_nav_tray/models/NavItem;", "context", "Landroid/content/Context;", "getItemsForPage", "getReferenceConfigModel", "Ljava/util/HashMap;", "", "Lcom/medscape/android/analytics/remoteconfig/reference/FeatureConfigModel;", "shouldDisplayRedDot", "configList", "item", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: NavEditViewModel.kt */
public final class NavEditViewModel extends ViewModel {
    private final HomeNavMenuConverter converter;
    private MutableLiveData<Boolean> isEditMode;
    private final NavItemManager navManager;
    private SharedPreferenceProvider sharePref;

    public NavEditViewModel() {
        this((SharedPreferenceProvider) null, 1, (DefaultConstructorMarker) null);
    }

    public NavEditViewModel(SharedPreferenceProvider sharedPreferenceProvider) {
        this.isEditMode = new MutableLiveData<>();
        this.navManager = new NavItemManager();
        this.converter = new HomeNavMenuConverter();
        this.isEditMode.setValue(false);
        if (sharedPreferenceProvider != null) {
            this.sharePref = sharedPreferenceProvider;
            return;
        }
        SharedPreferenceProvider sharedPreferenceProvider2 = SharedPreferenceProvider.get();
        Intrinsics.checkNotNullExpressionValue(sharedPreferenceProvider2, "SharedPreferenceProvider.get()");
        this.sharePref = sharedPreferenceProvider2;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ NavEditViewModel(SharedPreferenceProvider sharedPreferenceProvider, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : sharedPreferenceProvider);
    }

    public final MutableLiveData<Boolean> isEditMode() {
        return this.isEditMode;
    }

    public final void setEditMode(MutableLiveData<Boolean> mutableLiveData) {
        Intrinsics.checkNotNullParameter(mutableLiveData, "<set-?>");
        this.isEditMode = mutableLiveData;
    }

    public final NavItemManager getNavManager() {
        return this.navManager;
    }

    public final SharedPreferenceProvider getSharePref() {
        return this.sharePref;
    }

    public final void setSharePref(SharedPreferenceProvider sharedPreferenceProvider) {
        Intrinsics.checkNotNullParameter(sharedPreferenceProvider, "<set-?>");
        this.sharePref = sharedPreferenceProvider;
    }

    public final HomeNavMenuConverter getConverter() {
        return this.converter;
    }

    public final List<NavItem> getItems(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        String str = this.sharePref.get(Constants.HOME_NAV_ORDER, "");
        NavItemManager navItemManager = this.navManager;
        HomeNavMenuConverter homeNavMenuConverter = this.converter;
        Intrinsics.checkNotNullExpressionValue(str, "order");
        return navItemManager.getOrderedItems(homeNavMenuConverter.decode(str), context);
    }

    public final List<NavItem> getItemsForPage(Context context) {
        String str = this.sharePref.get(Constants.HOME_NAV_ORDER, "");
        NavItemManager navItemManager = this.navManager;
        HomeNavMenuConverter homeNavMenuConverter = this.converter;
        Intrinsics.checkNotNullExpressionValue(str, "order");
        List<NavItem> itemsForPage = navItemManager.getItemsForPage(homeNavMenuConverter.decode(str), context);
        int size = itemsForPage.size();
        for (int i = 0; i < size; i++) {
            itemsForPage.get(i).setShouldDisplayRedDot(shouldDisplayRedDot(getReferenceConfigModel(), itemsForPage.get(i)));
        }
        return itemsForPage;
    }

    public final HashMap<String, FeatureConfigModel> getReferenceConfigModel() {
        return FeatureConfigManager.Companion.getReferenceConfig();
    }

    public final boolean shouldDisplayRedDot(HashMap<String, FeatureConfigModel> hashMap, NavItem navItem) {
        Intrinsics.checkNotNullParameter(hashMap, "configList");
        Intrinsics.checkNotNullParameter(navItem, ContentParser.ITEM);
        FeatureConfigModel featureConfigModel = hashMap.get(navItem.getType());
        if (featureConfigModel == null) {
            return false;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(featureConfigModel.getType());
        sb.append("_");
        AuthenticationManager instance = AuthenticationManager.getInstance(MedscapeApplication.get());
        Intrinsics.checkNotNullExpressionValue(instance, "AuthenticationManager.ge…edscapeApplication.get())");
        sb.append(instance.getMaskedGuid());
        String sb2 = sb.toString();
        if (!Intrinsics.areEqual((Object) featureConfigModel.getType(), (Object) navItem.getType()) || SharedPreferenceProvider.get().get(sb2, false)) {
            return false;
        }
        String str = featureConfigModel.getType() + "_" + navItem.getText();
        if (SharedPreferenceProvider.get().get(str, 0) <= 0) {
            SharedPreferenceProvider.get().save(str, Long.valueOf(new Date().getTime()));
        }
        long j = SharedPreferenceProvider.get().get(str, 0);
        if (j >= featureConfigModel.getIndicator().getEndDate() || j + ((long) (featureConfigModel.getIndicator().getTtl() * DateTimeConstants.SECONDS_PER_HOUR * 1000)) < new Date().getTime()) {
            return false;
        }
        return true;
    }
}
