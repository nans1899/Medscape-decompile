package com.medscape.android.homescreen.home_nav_tray.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.medscape.android.R;
import com.medscape.android.analytics.remoteconfig.reference.FeatureConfigModel;
import com.medscape.android.homescreen.home_nav_tray.models.NavItem;
import com.medscape.android.homescreen.home_nav_tray.views.HomeNavItemViewHolder;
import com.medscape.android.homescreen.interfaces.INavItemClickListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001BC\u0012\u000e\b\u0002\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\"\u0010\b\u001a\u001e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\tj\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b`\f¢\u0006\u0002\u0010\rJ\b\u0010\u0018\u001a\u00020\u0019H\u0016J\u0018\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u00022\u0006\u0010\u001d\u001a\u00020\u0019H\u0016J\u0018\u0010\u001e\u001a\u00020\u00022\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\u0019H\u0016R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR \u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R6\u0010\b\u001a\u001e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\tj\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b`\fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017¨\u0006\""}, d2 = {"Lcom/medscape/android/homescreen/home_nav_tray/adapters/HomeNavItemsAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/medscape/android/homescreen/home_nav_tray/views/HomeNavItemViewHolder;", "items", "", "Lcom/medscape/android/homescreen/home_nav_tray/models/NavItem;", "clickListener", "Lcom/medscape/android/homescreen/interfaces/INavItemClickListener;", "referenceModel", "Ljava/util/HashMap;", "", "Lcom/medscape/android/analytics/remoteconfig/reference/FeatureConfigModel;", "Lkotlin/collections/HashMap;", "(Ljava/util/List;Lcom/medscape/android/homescreen/interfaces/INavItemClickListener;Ljava/util/HashMap;)V", "getClickListener", "()Lcom/medscape/android/homescreen/interfaces/INavItemClickListener;", "getItems", "()Ljava/util/List;", "setItems", "(Ljava/util/List;)V", "getReferenceModel", "()Ljava/util/HashMap;", "setReferenceModel", "(Ljava/util/HashMap;)V", "getItemCount", "", "onBindViewHolder", "", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: HomeNavItemsAdapter.kt */
public final class HomeNavItemsAdapter extends RecyclerView.Adapter<HomeNavItemViewHolder> {
    private final INavItemClickListener clickListener;
    private List<NavItem> items;
    private HashMap<String, FeatureConfigModel> referenceModel;

    public HomeNavItemsAdapter(List<NavItem> list, INavItemClickListener iNavItemClickListener, HashMap<String, FeatureConfigModel> hashMap) {
        Intrinsics.checkNotNullParameter(list, FirebaseAnalytics.Param.ITEMS);
        Intrinsics.checkNotNullParameter(hashMap, "referenceModel");
        this.items = list;
        this.clickListener = iNavItemClickListener;
        this.referenceModel = hashMap;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ HomeNavItemsAdapter(List list, INavItemClickListener iNavItemClickListener, HashMap hashMap, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? new ArrayList() : list, iNavItemClickListener, hashMap);
    }

    public final INavItemClickListener getClickListener() {
        return this.clickListener;
    }

    public final List<NavItem> getItems() {
        return this.items;
    }

    public final HashMap<String, FeatureConfigModel> getReferenceModel() {
        return this.referenceModel;
    }

    public final void setItems(List<NavItem> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.items = list;
    }

    public final void setReferenceModel(HashMap<String, FeatureConfigModel> hashMap) {
        Intrinsics.checkNotNullParameter(hashMap, "<set-?>");
        this.referenceModel = hashMap;
    }

    public HomeNavItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        Intrinsics.checkNotNullParameter(viewGroup, "parent");
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.home_nav_icon, viewGroup, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "view");
        return new HomeNavItemViewHolder(inflate);
    }

    public int getItemCount() {
        return this.items.size();
    }

    public void onBindViewHolder(HomeNavItemViewHolder homeNavItemViewHolder, int i) {
        Intrinsics.checkNotNullParameter(homeNavItemViewHolder, "holder");
        NavItem navItem = this.items.get(i);
        homeNavItemViewHolder.bind(navItem);
        homeNavItemViewHolder.itemView.setOnClickListener(new HomeNavItemsAdapter$onBindViewHolder$1(this, navItem, i));
    }
}
