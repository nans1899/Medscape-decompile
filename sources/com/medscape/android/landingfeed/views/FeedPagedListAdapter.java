package com.medscape.android.landingfeed.views;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.gms.ads.formats.NativeCustomTemplateAd;
import com.medscape.android.R;
import com.medscape.android.activity.cme.views.CMELandingActivity;
import com.medscape.android.ads.NativeDFPAD;
import com.medscape.android.landingfeed.model.FeedAdItem;
import com.medscape.android.landingfeed.model.FeedConstants;
import com.medscape.android.landingfeed.model.FeedDataItem;
import com.medscape.android.landingfeed.model.FeedNoMoreItems;
import com.medscape.android.landingfeed.repository.NetworkState;
import com.medscape.android.landingfeed.viewmodel.LandingFeedViewModel;
import com.webmd.wbmdcmepulse.live_events.model.LiveEventItem;
import com.webmd.wbmdcmepulse.live_events.viewholders.LiveEventViewHolder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u0000 22\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u00012B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u001e\u0010\u001d\u001a\u00020\u001e2\u0016\u0010\u001f\u001a\u0012\u0012\u0004\u0012\u00020\u000b0\nj\b\u0012\u0004\u0012\u00020\u000b`\fJ\b\u0010 \u001a\u00020\u0013H\u0016J\u0010\u0010!\u001a\u00020\u00132\u0006\u0010\"\u001a\u00020\u0013H\u0016J\b\u0010#\u001a\u00020$H\u0002J\u000e\u0010%\u001a\u00020\u001e2\u0006\u0010\"\u001a\u00020\u0013J\u0018\u0010&\u001a\u00020\u001e2\u0006\u0010'\u001a\u00020\u00032\u0006\u0010\"\u001a\u00020\u0013H\u0016J\u0018\u0010(\u001a\u00020\u00032\u0006\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020\u0013H\u0016J\u0018\u0010,\u001a\u00020\u001e2\u0006\u0010-\u001a\u00020.2\u0006\u0010\"\u001a\u00020\u0013H\u0002J\u0010\u0010/\u001a\u00020\u001e2\b\u00100\u001a\u0004\u0018\u00010\u0017J\u0006\u00101\u001a\u00020\u001eR\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R*\u0010\t\u001a\u0012\u0012\u0004\u0012\u00020\u000b0\nj\b\u0012\u0004\u0012\u00020\u000b`\fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R*\u0010\u0011\u001a\u001e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u00140\u0012j\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u0014`\u0015X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u000e¢\u0006\u0002\n\u0000R!\u0010\u0018\u001a\u0012\u0012\u0004\u0012\u00020\u00130\u0019j\b\u0012\u0004\u0012\u00020\u0013`\u001a¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001c¨\u00063"}, d2 = {"Lcom/medscape/android/landingfeed/views/FeedPagedListAdapter;", "Landroidx/paging/PagedListAdapter;", "Lcom/medscape/android/landingfeed/model/FeedDataItem;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "mContext", "Landroidx/fragment/app/FragmentActivity;", "(Landroidx/fragment/app/FragmentActivity;)V", "landingViewModel", "Lcom/medscape/android/landingfeed/viewmodel/LandingFeedViewModel;", "liveEventItems", "Ljava/util/ArrayList;", "Lcom/webmd/wbmdcmepulse/live_events/model/LiveEventItem;", "Lkotlin/collections/ArrayList;", "getLiveEventItems", "()Ljava/util/ArrayList;", "setLiveEventItems", "(Ljava/util/ArrayList;)V", "mNativeAdviewMap", "Ljava/util/HashMap;", "", "Lcom/medscape/android/ads/NativeDFPAD;", "Lkotlin/collections/HashMap;", "networkState", "Lcom/medscape/android/landingfeed/repository/NetworkState;", "saveStateUpdatePosSet", "Ljava/util/HashSet;", "Lkotlin/collections/HashSet;", "getSaveStateUpdatePosSet", "()Ljava/util/HashSet;", "addLiveEvents", "", "liveEvents", "getItemCount", "getItemViewType", "position", "hasExtraRow", "", "initPreloadAds", "onBindViewHolder", "holder", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "requestCustomNativeAd", "context", "Landroid/content/Context;", "setNetworkState", "newNetworkState", "updateSaveStateOfAllItems", "Companion", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: FeedPagedListAdapter.kt */
public final class FeedPagedListAdapter extends PagedListAdapter<FeedDataItem, RecyclerView.ViewHolder> {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final DiffUtil.ItemCallback<FeedDataItem> ITEM_COMPARATOR = new FeedPagedListAdapter$Companion$ITEM_COMPARATOR$1();
    private final LandingFeedViewModel landingViewModel;
    private ArrayList<LiveEventItem> liveEventItems = new ArrayList<>();
    private final FragmentActivity mContext;
    /* access modifiers changed from: private */
    public HashMap<Integer, NativeDFPAD> mNativeAdviewMap = new HashMap<>();
    private NetworkState networkState;
    private final HashSet<Integer> saveStateUpdatePosSet = new HashSet<>();

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public FeedPagedListAdapter(FragmentActivity fragmentActivity) {
        super(ITEM_COMPARATOR);
        Intrinsics.checkNotNullParameter(fragmentActivity, "mContext");
        this.mContext = fragmentActivity;
        ViewModel viewModel = ViewModelProviders.of(this.mContext).get(LandingFeedViewModel.class);
        Intrinsics.checkNotNullExpressionValue(viewModel, "ViewModelProviders.of(mC…eedViewModel::class.java)");
        this.landingViewModel = (LandingFeedViewModel) viewModel;
    }

    public final HashSet<Integer> getSaveStateUpdatePosSet() {
        return this.saveStateUpdatePosSet;
    }

    public final ArrayList<LiveEventItem> getLiveEventItems() {
        return this.liveEventItems;
    }

    public final void setLiveEventItems(ArrayList<LiveEventItem> arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
        this.liveEventItems = arrayList;
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        NativeCustomTemplateAd nativeAD;
        Intrinsics.checkNotNullParameter(viewHolder, "holder");
        if (this.saveStateUpdatePosSet.size() > 0 && this.saveStateUpdatePosSet.remove(Integer.valueOf(i)) && i < getItemCount()) {
            try {
                LandingFeedViewModel landingFeedViewModel = this.landingViewModel;
                Object item = getItem(i);
                Intrinsics.checkNotNull(item);
                Intrinsics.checkNotNullExpressionValue(item, "getItem(position)!!");
                landingFeedViewModel.updateSavedState((FeedDataItem) item, this.mContext);
            } catch (Throwable unused) {
            }
        }
        int itemViewType = getItemViewType(i);
        switch (itemViewType) {
            case R.layout.cme_feed:
                ((FeedItemCMEViewHolder) viewHolder).setData((FeedDataItem) getItem(i), this.mContext);
                return;
            case R.layout.home_native_ad_layout:
                Object item2 = getItem(i);
                if (item2 != null) {
                    FeedAdItem feedAdItem = (FeedAdItem) item2;
                    if (!feedAdItem.isAdRequested()) {
                        feedAdItem.setAdRequested(true);
                        View view = viewHolder.itemView;
                        Intrinsics.checkNotNullExpressionValue(view, "holder.itemView");
                        Context context = view.getContext();
                        Intrinsics.checkNotNullExpressionValue(context, "holder.itemView.context");
                        requestCustomNativeAd(context, i);
                    } else if (this.mNativeAdviewMap.containsKey(Integer.valueOf(i))) {
                        feedAdItem.setNativeDFPAD(this.mNativeAdviewMap.get(Integer.valueOf(i)));
                        NativeDFPAD nativeDFPAD = feedAdItem.getNativeDFPAD();
                        if (!(nativeDFPAD == null || (nativeAD = nativeDFPAD.getNativeAD()) == null)) {
                            nativeAD.recordImpression();
                        }
                        this.mNativeAdviewMap.remove(Integer.valueOf(i));
                    }
                    ((FeedItemNativeADViewHolder) viewHolder).bindAD(this.mContext, this.landingViewModel, feedAdItem);
                    return;
                }
                throw new NullPointerException("null cannot be cast to non-null type com.medscape.android.landingfeed.model.FeedAdItem");
            case R.layout.network_state_item:
                ((NetworkStateItemViewHolder) viewHolder).bindTo(this.networkState);
                return;
            case R.layout.upcoming_live_events:
                LiveEventViewHolder liveEventViewHolder = (LiveEventViewHolder) viewHolder;
                List list = this.liveEventItems;
                Context context2 = this.mContext;
                LandingFeedViewModel landingFeedViewModel2 = this.landingViewModel;
                liveEventViewHolder.setData(list, context2, (landingFeedViewModel2 != null ? Boolean.valueOf(landingFeedViewModel2.getLiveEventsLoaded()) : null).booleanValue());
                return;
            default:
                switch (itemViewType) {
                    case R.layout.home_card_consult:
                        ((FeedItemConsultViewHolder) viewHolder).setData((FeedDataItem) getItem(i), this.mContext);
                        return;
                    case R.layout.home_card_gen:
                        ((FeedItemGenViewHolder) viewHolder).setData((FeedDataItem) getItem(i), this.mContext);
                        return;
                    case R.layout.home_card_invitation:
                        ((FeedItemInvitationViewHolder) viewHolder).setData((FeedDataItem) getItem(i), this.mContext, this.landingViewModel.getFeedType());
                        return;
                    case R.layout.home_card_no_more_items:
                        FeedItemNoMoreHolder feedItemNoMoreHolder = (FeedItemNoMoreHolder) viewHolder;
                        return;
                    case R.layout.home_card_title:
                        ((FeedItemTitleViewHolder) viewHolder).setData((FeedDataItem) getItem(i), this.mContext);
                        return;
                    default:
                        return;
                }
        }
    }

    private final void requestCustomNativeAd(Context context, int i) {
        this.landingViewModel.requestAd(context, new FeedPagedListAdapter$requestCustomNativeAd$1(this, i));
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        Intrinsics.checkNotNullParameter(viewGroup, "parent");
        switch (i) {
            case R.layout.cme_feed:
                return FeedItemCMEViewHolder.Companion.create(viewGroup);
            case R.layout.home_native_ad_layout:
                return FeedItemNativeADViewHolder.Companion.create(viewGroup);
            case R.layout.network_state_item:
                return NetworkStateItemViewHolder.Companion.create(viewGroup);
            case R.layout.upcoming_live_events:
                return LiveEventViewHolder.Companion.create(viewGroup);
            default:
                switch (i) {
                    case R.layout.home_card_consult:
                        return FeedItemConsultViewHolder.Companion.create(viewGroup);
                    case R.layout.home_card_gen:
                        return FeedItemGenViewHolder.Companion.create(viewGroup);
                    case R.layout.home_card_invitation:
                        return FeedItemInvitationViewHolder.Companion.create(viewGroup);
                    case R.layout.home_card_no_more_items:
                        return FeedItemNoMoreHolder.Companion.create(viewGroup);
                    case R.layout.home_card_title:
                        return FeedItemTitleViewHolder.Companion.create(viewGroup);
                    default:
                        throw new IllegalArgumentException("unknown view type " + i);
                }
        }
    }

    private final boolean hasExtraRow() {
        NetworkState networkState2 = this.networkState;
        return networkState2 != null && (Intrinsics.areEqual((Object) networkState2, (Object) NetworkState.Companion.getLOADED()) ^ true);
    }

    public int getItemViewType(int i) {
        if (hasExtraRow() && i == getItemCount() - 1) {
            return R.layout.network_state_item;
        }
        if (getItem(i) instanceof FeedAdItem) {
            return R.layout.home_native_ad_layout;
        }
        FeedDataItem feedDataItem = (FeedDataItem) getItem(i);
        String str = null;
        if (StringsKt.equals(feedDataItem != null ? feedDataItem.getType() : null, "consult", true)) {
            return R.layout.home_card_consult;
        }
        FeedDataItem feedDataItem2 = (FeedDataItem) getItem(i);
        if (StringsKt.equals(feedDataItem2 != null ? feedDataItem2.getType() : null, FeedConstants.CME_ITEM, true) && (this.mContext instanceof CMELandingActivity)) {
            return R.layout.cme_feed;
        }
        FeedDataItem feedDataItem3 = (FeedDataItem) getItem(i);
        if (!StringsKt.equals(feedDataItem3 != null ? feedDataItem3.getType() : null, FeedConstants.DATA_UPDATE_ITEM, true)) {
            FeedDataItem feedDataItem4 = (FeedDataItem) getItem(i);
            if (!StringsKt.equals(feedDataItem4 != null ? feedDataItem4.getType() : null, FeedConstants.CLINICAL_ADVANCES_ITEM, true)) {
                FeedDataItem feedDataItem5 = (FeedDataItem) getItem(i);
                if (StringsKt.equals(feedDataItem5 != null ? feedDataItem5.getType() : null, FeedConstants.LIVE_EVENTS_ITEM, true)) {
                    return R.layout.upcoming_live_events;
                }
                if (getItem(i) instanceof FeedNoMoreItems) {
                    return R.layout.home_card_no_more_items;
                }
                FeedDataItem feedDataItem6 = (FeedDataItem) getItem(i);
                if (StringsKt.equals(feedDataItem6 != null ? feedDataItem6.getType() : null, FeedConstants.SPECIAL_COVERAGE_ITEM, true)) {
                    return R.layout.home_card_gen;
                }
                FeedDataItem feedDataItem7 = (FeedDataItem) getItem(i);
                if (!StringsKt.equals(feedDataItem7 != null ? feedDataItem7.getType() : null, FeedConstants.INVITATION_GENERIC, true)) {
                    FeedDataItem feedDataItem8 = (FeedDataItem) getItem(i);
                    if (feedDataItem8 != null) {
                        str = feedDataItem8.getType();
                    }
                    if (StringsKt.equals(str, FeedConstants.INVITATION_AD, true)) {
                        return R.layout.home_card_invitation;
                    }
                    return R.layout.home_card_gen;
                }
                return R.layout.home_card_invitation;
            }
        }
        return R.layout.home_card_title;
    }

    public int getItemCount() {
        return super.getItemCount() + (hasExtraRow() ? 1 : 0);
    }

    public final void addLiveEvents(ArrayList<LiveEventItem> arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "liveEvents");
        this.liveEventItems = arrayList;
        notifyDataSetChanged();
    }

    public final void setNetworkState(NetworkState networkState2) {
        if (getItemCount() > 1) {
            NetworkState networkState3 = this.networkState;
            boolean hasExtraRow = hasExtraRow();
            this.networkState = networkState2;
            boolean hasExtraRow2 = hasExtraRow();
            if (hasExtraRow != hasExtraRow2) {
                if (hasExtraRow) {
                    notifyItemRemoved(super.getItemCount());
                } else {
                    notifyItemInserted(super.getItemCount());
                }
            } else if (hasExtraRow2 && (!Intrinsics.areEqual((Object) networkState3, (Object) networkState2))) {
                notifyItemChanged(getItemCount() - 1);
            }
        }
    }

    public final void updateSaveStateOfAllItems() {
        int itemCount = getItemCount() > 20 ? getItemCount() - 20 : 1;
        for (int i = 0; i < itemCount; i++) {
            LandingFeedViewModel landingFeedViewModel = this.landingViewModel;
            Object item = getItem(i);
            Intrinsics.checkNotNull(item);
            Intrinsics.checkNotNullExpressionValue(item, "getItem(i)!!");
            landingFeedViewModel.updateSavedState((FeedDataItem) item, this.mContext);
        }
        if (itemCount < getItemCount()) {
            int itemCount2 = getItemCount();
            while (itemCount < itemCount2) {
                this.saveStateUpdatePosSet.add(Integer.valueOf(itemCount));
                itemCount++;
            }
        }
    }

    public final void initPreloadAds(int i) {
        requestCustomNativeAd(this.mContext, i);
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/medscape/android/landingfeed/views/FeedPagedListAdapter$Companion;", "", "()V", "ITEM_COMPARATOR", "Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "Lcom/medscape/android/landingfeed/model/FeedDataItem;", "getITEM_COMPARATOR", "()Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "medscape_release"}, k = 1, mv = {1, 4, 0})
    /* compiled from: FeedPagedListAdapter.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final DiffUtil.ItemCallback<FeedDataItem> getITEM_COMPARATOR() {
            return FeedPagedListAdapter.ITEM_COMPARATOR;
        }
    }
}
