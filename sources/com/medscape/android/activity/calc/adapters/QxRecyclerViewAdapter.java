package com.medscape.android.activity.calc.adapters;

import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.gms.ads.AdSize;
import com.medscape.android.R;
import com.medscape.android.activity.calc.ads.AdAppEvents;
import com.medscape.android.activity.calc.ads.AdConversions;
import com.medscape.android.activity.calc.ads.AdParams;
import com.medscape.android.activity.calc.ads.InlineAdLoaded;
import com.medscape.android.ads.DFPAdAction;
import com.medscape.android.ads.NativeADViewModel;
import com.medscape.android.ads.bidding.MedianetBidder;
import com.medscape.android.ads.bidding.ProclivityBidder;
import com.qxmd.qxrecyclerview.QxRecyclerRowItemViewHolder;
import com.qxmd.qxrecyclerview.QxRecyclerViewRowItem;
import com.qxmd.qxrecyclerview.QxRecyclerViewRowItemWrapper;
import com.wbmd.ads.AdManager;
import com.wbmd.ads.AdViewHolder;
import com.wbmd.ads.bidding.AdBiddingProvider;
import com.wbmd.ads.model.AdContainer;
import com.wbmd.ads.model.AdContentData;
import com.wbmd.ads.model.BaseNativeADViewModel;
import com.wbmd.ads.model.Pos;
import com.wbmd.qxcalculator.model.contentItems.common.ContentItem;
import java.util.HashMap;
import java.util.HashSet;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bJ\u0010\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020(H\u0002J\b\u0010)\u001a\u00020\nH\u0016J\u0010\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020\nH\u0016J\u0010\u0010-\u001a\u00020\n2\u0006\u0010,\u001a\u00020\nH\u0016J\u0018\u0010.\u001a\u00020/2\u0006\u00100\u001a\u0002012\u0006\u0010,\u001a\u00020\nH\u0016J\u0018\u00102\u001a\u0002012\u0006\u0010'\u001a\u00020(2\u0006\u00103\u001a\u00020\nH\u0016J\b\u00104\u001a\u00020/H\u0002J\u0006\u00105\u001a\u00020/R\u000e\u0010\t\u001a\u00020\nXD¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX.¢\u0006\u0002\n\u0000R \u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0004\u001a\u00020\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001a\u0010\u0018\u001a\u00020\u0019X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u001a\u0010\u001e\u001a\u00020\u0019X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001b\"\u0004\b\u001f\u0010\u001dR\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R\u000e\u0010$\u001a\u00020\u0003X\u000e¢\u0006\u0002\n\u0000¨\u00066"}, d2 = {"Lcom/medscape/android/activity/calc/adapters/QxRecyclerViewAdapter;", "Lcom/qxmd/qxrecyclerview/QxRecyclerViewAdapter;", "context", "Landroidx/fragment/app/FragmentActivity;", "contentItem", "Lcom/wbmd/qxcalculator/model/contentItems/common/ContentItem;", "listener", "Lcom/medscape/android/activity/calc/ads/InlineAdLoaded;", "(Landroidx/fragment/app/FragmentActivity;Lcom/wbmd/qxcalculator/model/contentItems/common/ContentItem;Lcom/medscape/android/activity/calc/ads/InlineAdLoaded;)V", "CALC_AD_TYPE", "", "adManager", "Lcom/wbmd/ads/AdManager;", "adsSdkSet", "Ljava/util/HashSet;", "Lcom/wbmd/ads/model/AdContainer;", "getAdsSdkSet", "()Ljava/util/HashSet;", "setAdsSdkSet", "(Ljava/util/HashSet;)V", "getContentItem", "()Lcom/wbmd/qxcalculator/model/contentItems/common/ContentItem;", "setContentItem", "(Lcom/wbmd/qxcalculator/model/contentItems/common/ContentItem;)V", "inlineAdRequested", "", "getInlineAdRequested", "()Z", "setInlineAdRequested", "(Z)V", "isInlineADcallComplete", "setInlineADcallComplete", "getListener", "()Lcom/medscape/android/activity/calc/ads/InlineAdLoaded;", "setListener", "(Lcom/medscape/android/activity/calc/ads/InlineAdLoaded;)V", "mContext", "createAdViewHolder", "Lcom/wbmd/ads/AdViewHolder;", "parent", "Landroid/view/ViewGroup;", "getItemCount", "getItemId", "", "position", "getItemViewType", "onBindViewHolder", "", "holder", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "onCreateViewHolder", "viewType", "requestAD", "resetAdHolder", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: QxRecyclerViewAdapter.kt */
public final class QxRecyclerViewAdapter extends com.qxmd.qxrecyclerview.QxRecyclerViewAdapter {
    private final int CALC_AD_TYPE;
    private AdManager adManager;
    private HashSet<AdContainer> adsSdkSet = new HashSet<>();
    private ContentItem contentItem;
    private boolean inlineAdRequested;
    private boolean isInlineADcallComplete;
    private InlineAdLoaded listener;
    private FragmentActivity mContext;

    public QxRecyclerViewAdapter(FragmentActivity fragmentActivity, ContentItem contentItem2, InlineAdLoaded inlineAdLoaded) {
        Intrinsics.checkNotNullParameter(fragmentActivity, "context");
        Intrinsics.checkNotNullParameter(contentItem2, "contentItem");
        this.contentItem = contentItem2;
        this.listener = inlineAdLoaded;
        this.mContext = fragmentActivity;
    }

    public final ContentItem getContentItem() {
        return this.contentItem;
    }

    public final InlineAdLoaded getListener() {
        return this.listener;
    }

    public final void setContentItem(ContentItem contentItem2) {
        Intrinsics.checkNotNullParameter(contentItem2, "<set-?>");
        this.contentItem = contentItem2;
    }

    public final void setListener(InlineAdLoaded inlineAdLoaded) {
        this.listener = inlineAdLoaded;
    }

    public final HashSet<AdContainer> getAdsSdkSet() {
        return this.adsSdkSet;
    }

    public final void setAdsSdkSet(HashSet<AdContainer> hashSet) {
        Intrinsics.checkNotNullParameter(hashSet, "<set-?>");
        this.adsSdkSet = hashSet;
    }

    public final boolean getInlineAdRequested() {
        return this.inlineAdRequested;
    }

    public final void setInlineAdRequested(boolean z) {
        this.inlineAdRequested = z;
    }

    public final boolean isInlineADcallComplete() {
        return this.isInlineADcallComplete;
    }

    public final void setInlineADcallComplete(boolean z) {
        this.isInlineADcallComplete = z;
    }

    public int getItemCount() {
        if (this.wrappedRowItems != null) {
            return this.wrappedRowItems.size() + 1;
        }
        return 0;
    }

    public int getItemViewType(int i) {
        if (i == getItemCount() - 1) {
            return this.CALC_AD_TYPE;
        }
        QxRecyclerViewRowItem qxRecyclerViewRowItem = ((QxRecyclerViewRowItemWrapper) this.wrappedRowItems.get(i)).rowItem;
        Intrinsics.checkNotNullExpressionValue(qxRecyclerViewRowItem, "wrappedRowItems[position].rowItem");
        return qxRecyclerViewRowItem.getResourceId();
    }

    public long getItemId(int i) {
        if (this.wrappedRowItems == null) {
            return 0;
        }
        if (i == getItemCount() - 1) {
            return (long) this.CALC_AD_TYPE;
        }
        QxRecyclerViewRowItem qxRecyclerViewRowItem = ((QxRecyclerViewRowItemWrapper) this.wrappedRowItems.get(i)).rowItem;
        Intrinsics.checkNotNullExpressionValue(qxRecyclerViewRowItem, "wrappedRowItems[position].rowItem");
        return qxRecyclerViewRowItem.getItemId();
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        Intrinsics.checkNotNullParameter(viewGroup, "parent");
        LayoutInflater from = LayoutInflater.from(viewGroup.getContext());
        if (i == this.CALC_AD_TYPE) {
            return createAdViewHolder(viewGroup);
        }
        View inflate = from.inflate(i, viewGroup, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflater.inflate(viewType, parent, false)");
        inflate.setOnClickListener(this);
        if (this.onRecyclerViewRowItemLongClickedListener != null) {
            inflate.setOnLongClickListener(this);
        }
        QxRecyclerRowItemViewHolder constructViewHolderClass = constructViewHolderClass(inflate, i);
        Intrinsics.checkNotNullExpressionValue(constructViewHolderClass, "constructViewHolderClass(view, viewType)");
        return constructViewHolderClass;
    }

    private final AdViewHolder createAdViewHolder(ViewGroup viewGroup) {
        AdViewHolder create = AdViewHolder.Companion.create(viewGroup);
        LinearLayout linearLayout = (LinearLayout) create.getParentView().findViewById(R.id.native_ad_layout);
        Intrinsics.checkNotNullExpressionValue(linearLayout, "holder.parentView.native_ad_layout");
        TextView textView = (TextView) linearLayout.findViewById(R.id.additional_link1_text);
        Intrinsics.checkNotNullExpressionValue(textView, "holder.parentView.native…out.additional_link1_text");
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        LinearLayout linearLayout2 = (LinearLayout) create.getParentView().findViewById(R.id.native_ad_layout);
        Intrinsics.checkNotNullExpressionValue(linearLayout2, "holder.parentView.native_ad_layout");
        TextView textView2 = (TextView) linearLayout2.findViewById(R.id.additional_link2_text);
        Intrinsics.checkNotNullExpressionValue(textView2, "holder.parentView.native…out.additional_link2_text");
        textView2.setMovementMethod(LinkMovementMethod.getInstance());
        return create;
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        Intrinsics.checkNotNullParameter(viewHolder, "holder");
        if (viewHolder instanceof QxRecyclerRowItemViewHolder) {
            QxRecyclerViewRowItemWrapper qxRecyclerViewRowItemWrapper = (QxRecyclerViewRowItemWrapper) this.wrappedRowItems.get(i);
            qxRecyclerViewRowItemWrapper.rowItem.onBindData(viewHolder, i, qxRecyclerViewRowItemWrapper.indexPath, qxRecyclerViewRowItemWrapper.rowPosition, this);
        } else if (!(viewHolder instanceof AdViewHolder)) {
        } else {
            if (this.adsSdkSet.iterator().hasNext()) {
                AdContainer next = this.adsSdkSet.iterator().next();
                Intrinsics.checkNotNullExpressionValue(next, "adsSdkSet.iterator().next()");
                ((AdViewHolder) viewHolder).bindAd(next, new NativeADViewModel());
            } else if (!this.inlineAdRequested) {
                AdViewHolder.bindAd$default((AdViewHolder) viewHolder, (AdContainer) null, (BaseNativeADViewModel) null, 2, (Object) null);
                this.inlineAdRequested = true;
                requestAD();
            }
        }
    }

    private final void requestAD() {
        HashMap hashMap = new HashMap();
        ContentItem contentItem2 = this.contentItem;
        if (contentItem2 != null) {
            if (contentItem2.leadConcept != null) {
                hashMap.put(AdContentData.LEAD_CONCEPT, this.contentItem.leadConcept);
            }
            if (this.contentItem.leadSpecialty != null) {
                hashMap.put(AdContentData.LEAD_SPECIALITY, this.contentItem.leadSpecialty);
            }
            if (this.contentItem.allSpecialty != null) {
                hashMap.put(AdContentData.RELATED_SPECIALITY, this.contentItem.allSpecialty);
            }
        }
        int value = Pos.BottomInstream.value();
        AdSize adSize = DFPAdAction.ADSIZE_300x50;
        Intrinsics.checkNotNullExpressionValue(adSize, "DFPAdAction.ADSIZE_300x50");
        AdSize adSize2 = DFPAdAction.ADSIZE_320x50;
        Intrinsics.checkNotNullExpressionValue(adSize2, "DFPAdAction.ADSIZE_320x50");
        AdSize adSize3 = DFPAdAction.ADSIZE_3x1;
        Intrinsics.checkNotNullExpressionValue(adSize3, "DFPAdAction.ADSIZE_3x1");
        AdSize adSize4 = DFPAdAction.ADSIZE_300x250;
        Intrinsics.checkNotNullExpressionValue(adSize4, "DFPAdAction.ADSIZE_300x250");
        AdSize adSize5 = DFPAdAction.ADSIZE_300x400;
        Intrinsics.checkNotNullExpressionValue(adSize5, "DFPAdAction.ADSIZE_300x400");
        AdParams adParams = new AdParams(value, new AdSize[]{adSize, adSize2, adSize3, adSize4, adSize5}, new String[]{"11864416", "11848473"}, hashMap);
        FragmentActivity fragmentActivity = this.mContext;
        AdManager adManager2 = new AdManager(fragmentActivity, new AdAppEvents(fragmentActivity, true, (View) null, 4, (DefaultConstructorMarker) null), new AdConversions(this.mContext));
        this.adManager = adManager2;
        if (adManager2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adManager");
        }
        FragmentActivity fragmentActivity2 = this.mContext;
        adManager2.loadAd(fragmentActivity2, new AdBiddingProvider[]{new ProclivityBidder(fragmentActivity2), new MedianetBidder(this.mContext)}, new QxRecyclerViewAdapter$requestAD$1(this), adParams);
    }

    public final void resetAdHolder() {
        this.adsSdkSet.clear();
        this.isInlineADcallComplete = false;
        notifyDataSetChanged();
    }
}
