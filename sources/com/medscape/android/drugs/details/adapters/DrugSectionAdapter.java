package com.medscape.android.drugs.details.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.medscape.android.R;
import com.medscape.android.ads.DFPAdAction;
import com.medscape.android.ads.DFPReferenceAdListener;
import com.medscape.android.ads.NativeAdAction;
import com.medscape.android.ads.NativeDFPAD;
import com.medscape.android.ads.ShareThroughNativeADViewHolder;
import com.medscape.android.contentviewer.view_holders.DataViewHolder;
import com.medscape.android.contentviewer.view_holders.NextSectionViewHolder;
import com.medscape.android.contentviewer.view_holders.PreCachedNativeDfpInlineAdViewHolder;
import com.medscape.android.drugs.details.datamodels.InlineAdLineItem;
import com.medscape.android.drugs.details.datamodels.LineItem;
import com.medscape.android.drugs.details.viewholders.ContentViewHolder;
import com.medscape.android.drugs.details.viewholders.ExpandableContentView;
import com.medscape.android.drugs.details.viewholders.HeaderViewHolder;
import com.medscape.android.drugs.details.viewholders.SubHeaderViewHolder;
import com.medscape.android.drugs.details.views.DrugDetailsActivity;
import com.medscape.android.drugs.viewholders.ErrorMessageViewHolder;
import com.medscape.android.util.Util;
import com.tonicartos.superslim.GridSLM;
import com.tonicartos.superslim.LinearSLM;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\b\u0016\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u001b\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0006\u0010+\u001a\u00020\nJ\b\u0010,\u001a\u00020\nH\u0016J\u0010\u0010-\u001a\u00020\n2\u0006\u0010.\u001a\u00020\nH\u0016J\u0018\u0010/\u001a\u0002002\u0006\u00101\u001a\u00020\u00022\u0006\u0010.\u001a\u00020\nH\u0016J\u0018\u00102\u001a\u00020\u00022\u0006\u00103\u001a\u0002042\u0006\u00105\u001a\u00020\nH\u0016J\u0010\u00106\u001a\u0002002\u0006\u00101\u001a\u00020\u0002H\u0016J\u0010\u00107\u001a\u0002002\u0006\u00108\u001a\u000209H\u0002R\u000e\u0010\t\u001a\u00020\nXD¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\nXD¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\nXD¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\nXD¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\nXD¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\nXD¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\nXD¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\nXD¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\nX\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0013\u001a\u00020\u0014X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001a\u0010\u0006\u001a\u00020\u0007X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u001c\u0010\u001c\u001a\u0004\u0018\u00010\u001dX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R \u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%R\u001a\u0010&\u001a\u00020\nX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010(\"\u0004\b)\u0010*¨\u0006:"}, d2 = {"Lcom/medscape/android/drugs/details/adapters/DrugSectionAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "mItems", "", "Lcom/medscape/android/drugs/details/datamodels/LineItem;", "listener", "Lcom/medscape/android/contentviewer/view_holders/DataViewHolder$DataListClickListener;", "(Ljava/util/List;Lcom/medscape/android/contentviewer/view_holders/DataViewHolder$DataListClickListener;)V", "VIEW_TYPE_AD", "", "VIEW_TYPE_CONTENT", "VIEW_TYPE_ERROR_MSG", "VIEW_TYPE_EXPANDABLE", "VIEW_TYPE_HEADER", "VIEW_TYPE_NATIVE_AD", "VIEW_TYPE_NEXT", "VIEW_TYPE_SUBHEADER", "adPosition", "isSharethroughADLoadingDone", "", "()Z", "setSharethroughADLoadingDone", "(Z)V", "getListener", "()Lcom/medscape/android/contentviewer/view_holders/DataViewHolder$DataListClickListener;", "setListener", "(Lcom/medscape/android/contentviewer/view_holders/DataViewHolder$DataListClickListener;)V", "mContext", "Landroid/content/Context;", "getMContext", "()Landroid/content/Context;", "setMContext", "(Landroid/content/Context;)V", "getMItems", "()Ljava/util/List;", "setMItems", "(Ljava/util/List;)V", "previousDetailsShown", "getPreviousDetailsShown", "()I", "setPreviousDetailsShown", "(I)V", "getAdPosition", "getItemCount", "getItemViewType", "position", "onBindViewHolder", "", "holder", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "onViewAttachedToWindow", "requestShareThroughAD", "sharethroughADItem", "Lcom/medscape/android/drugs/details/datamodels/InlineAdLineItem;", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: DrugSectionAdapter.kt */
public class DrugSectionAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int VIEW_TYPE_AD = 5;
    private final int VIEW_TYPE_CONTENT;
    private final int VIEW_TYPE_ERROR_MSG = 7;
    private final int VIEW_TYPE_EXPANDABLE = 3;
    private final int VIEW_TYPE_HEADER = 1;
    private final int VIEW_TYPE_NATIVE_AD = 6;
    private final int VIEW_TYPE_NEXT = 4;
    private final int VIEW_TYPE_SUBHEADER = 2;
    private int adPosition = -1;
    private boolean isSharethroughADLoadingDone;
    private DataViewHolder.DataListClickListener listener;
    private Context mContext;
    private List<? extends LineItem> mItems;
    private int previousDetailsShown = -1;

    public DrugSectionAdapter(List<? extends LineItem> list, DataViewHolder.DataListClickListener dataListClickListener) {
        Intrinsics.checkNotNullParameter(list, "mItems");
        Intrinsics.checkNotNullParameter(dataListClickListener, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        this.mItems = list;
        this.listener = dataListClickListener;
        if (!list.isEmpty()) {
            for (LineItem lineItem : this.mItems) {
                if ((lineItem instanceof InlineAdLineItem) && !((InlineAdLineItem) lineItem).isSharethrough()) {
                    this.adPosition = this.mItems.indexOf(lineItem);
                }
            }
        }
    }

    public final DataViewHolder.DataListClickListener getListener() {
        return this.listener;
    }

    public final List<LineItem> getMItems() {
        return this.mItems;
    }

    public final void setListener(DataViewHolder.DataListClickListener dataListClickListener) {
        Intrinsics.checkNotNullParameter(dataListClickListener, "<set-?>");
        this.listener = dataListClickListener;
    }

    public final void setMItems(List<? extends LineItem> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.mItems = list;
    }

    public final int getPreviousDetailsShown() {
        return this.previousDetailsShown;
    }

    public final void setPreviousDetailsShown(int i) {
        this.previousDetailsShown = i;
    }

    public final boolean isSharethroughADLoadingDone() {
        return this.isSharethroughADLoadingDone;
    }

    public final void setSharethroughADLoadingDone(boolean z) {
        this.isSharethroughADLoadingDone = z;
    }

    public final Context getMContext() {
        return this.mContext;
    }

    public final void setMContext(Context context) {
        this.mContext = context;
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        Intrinsics.checkNotNullParameter(viewGroup, "parent");
        this.mContext = viewGroup.getContext();
        LayoutInflater from = LayoutInflater.from(viewGroup.getContext());
        if (i == this.VIEW_TYPE_HEADER) {
            View inflate = from.inflate(R.layout.content_section_header_item, viewGroup, false);
            Intrinsics.checkNotNullExpressionValue(inflate, "inflator.inflate(R.layou…ader_item, parent, false)");
            return new HeaderViewHolder(inflate);
        } else if (i == this.VIEW_TYPE_SUBHEADER) {
            View inflate2 = from.inflate(R.layout.content_section_sub_header_item, viewGroup, false);
            Intrinsics.checkNotNullExpressionValue(inflate2, "inflator.inflate(R.layou…ader_item, parent, false)");
            return new SubHeaderViewHolder(inflate2);
        } else if (i == this.VIEW_TYPE_CONTENT) {
            View inflate3 = from.inflate(R.layout.text_line_item_references, viewGroup, false);
            Intrinsics.checkNotNullExpressionValue(inflate3, "inflator.inflate(R.layou…eferences, parent, false)");
            return new ContentViewHolder(inflate3, this.listener);
        } else if (i == this.VIEW_TYPE_EXPANDABLE) {
            View inflate4 = from.inflate(R.layout.drug_details_interaction_layout, viewGroup, false);
            Intrinsics.checkNotNullExpressionValue(inflate4, "inflator.inflate(R.layou…on_layout, parent, false)");
            return new ExpandableContentView(inflate4);
        } else if (i == this.VIEW_TYPE_NEXT) {
            return new NextSectionViewHolder(from.inflate(R.layout.clinical_next_section_item, viewGroup, false), this.listener);
        } else {
            if (i == this.VIEW_TYPE_AD) {
                return new PreCachedNativeDfpInlineAdViewHolder(from.inflate(R.layout.combined_ad_layout, viewGroup, false));
            }
            if (i == this.VIEW_TYPE_ERROR_MSG) {
                View inflate5 = from.inflate(R.layout.view_holder_error_message, viewGroup, false);
                Intrinsics.checkNotNullExpressionValue(inflate5, "inflator.inflate(R.layou…r_message, parent, false)");
                return new ErrorMessageViewHolder(inflate5);
            } else if (i == this.VIEW_TYPE_NATIVE_AD) {
                View inflate6 = from.inflate(R.layout.sharethrough_reference_inline_ad, viewGroup, false);
                Intrinsics.checkNotNullExpressionValue(inflate6, "view");
                ShareThroughNativeADViewHolder shareThroughNativeADViewHolder = new ShareThroughNativeADViewHolder(inflate6);
                shareThroughNativeADViewHolder.applyPadding(Util.dpToPixel(inflate6.getContext(), 12));
                return shareThroughNativeADViewHolder;
            } else {
                View inflate7 = from.inflate(R.layout.text_line_item_references, viewGroup, false);
                Intrinsics.checkNotNullExpressionValue(inflate7, "inflator.inflate(R.layou…eferences, parent, false)");
                return new ContentViewHolder(inflate7, this.listener);
            }
        }
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        Intrinsics.checkNotNullParameter(viewHolder, "holder");
        View view = viewHolder.itemView;
        Intrinsics.checkNotNullExpressionValue(view, "holder.itemView");
        GridSLM.LayoutParams from = GridSLM.LayoutParams.from(view.getLayoutParams());
        LineItem lineItem = (LineItem) this.mItems.get(i);
        if (viewHolder instanceof HeaderViewHolder) {
            ((HeaderViewHolder) viewHolder).bindHeader(lineItem);
        } else if (viewHolder instanceof SubHeaderViewHolder) {
            ((SubHeaderViewHolder) viewHolder).bindSubHeader(lineItem);
        } else if (viewHolder instanceof ContentViewHolder) {
            ((ContentViewHolder) viewHolder).bindContent(lineItem);
        } else if (viewHolder instanceof ExpandableContentView) {
            ((ExpandableContentView) viewHolder).bind(lineItem);
        } else if (viewHolder instanceof ErrorMessageViewHolder) {
            ((ErrorMessageViewHolder) viewHolder).bind(lineItem);
        } else if (viewHolder instanceof NextSectionViewHolder) {
            ((NextSectionViewHolder) viewHolder).bind(lineItem.getText());
        } else if (viewHolder instanceof PreCachedNativeDfpInlineAdViewHolder) {
            PreCachedNativeDfpInlineAdViewHolder preCachedNativeDfpInlineAdViewHolder = (PreCachedNativeDfpInlineAdViewHolder) viewHolder;
            if (lineItem != null) {
                preCachedNativeDfpInlineAdViewHolder.bind(((InlineAdLineItem) lineItem).getAdView());
            } else {
                throw new NullPointerException("null cannot be cast to non-null type com.medscape.android.drugs.details.datamodels.InlineAdLineItem");
            }
        } else if (viewHolder instanceof ShareThroughNativeADViewHolder) {
            if (!this.isSharethroughADLoadingDone) {
                if (lineItem != null) {
                    requestShareThroughAD((InlineAdLineItem) lineItem);
                } else {
                    throw new NullPointerException("null cannot be cast to non-null type com.medscape.android.drugs.details.datamodels.InlineAdLineItem");
                }
            }
            ShareThroughNativeADViewHolder shareThroughNativeADViewHolder = (ShareThroughNativeADViewHolder) viewHolder;
            if (lineItem != null) {
                shareThroughNativeADViewHolder.onBind(((InlineAdLineItem) lineItem).getAdView(), true, !this.isSharethroughADLoadingDone);
            } else {
                throw new NullPointerException("null cannot be cast to non-null type com.medscape.android.drugs.details.datamodels.InlineAdLineItem");
            }
        }
        if (lineItem.isExpandable()) {
            viewHolder.itemView.setOnClickListener(new DrugSectionAdapter$onBindViewHolder$1(this, i, lineItem));
        }
        from.setSlm(LinearSLM.ID);
        Intrinsics.checkNotNullExpressionValue(from, "lp");
        from.setFirstPosition(lineItem.getSectionHeaderPosition());
        view.setLayoutParams(from);
    }

    public int getItemViewType(int i) {
        if (this.mItems.size() <= 0) {
            return -1;
        }
        LineItem lineItem = (LineItem) this.mItems.get(i);
        if (lineItem.isHeader()) {
            return this.VIEW_TYPE_HEADER;
        }
        if (lineItem.isSubheader()) {
            return this.VIEW_TYPE_SUBHEADER;
        }
        if (lineItem.isExpandable()) {
            return this.VIEW_TYPE_EXPANDABLE;
        }
        if (lineItem.isNextSection()) {
            return this.VIEW_TYPE_NEXT;
        }
        if (lineItem.isErrorMessage()) {
            return this.VIEW_TYPE_ERROR_MSG;
        }
        boolean z = lineItem instanceof InlineAdLineItem;
        if (z && ((InlineAdLineItem) lineItem).isSharethrough()) {
            return this.VIEW_TYPE_NATIVE_AD;
        }
        if (z) {
            return this.VIEW_TYPE_AD;
        }
        return this.VIEW_TYPE_CONTENT;
    }

    public int getItemCount() {
        return this.mItems.size();
    }

    public void onViewAttachedToWindow(RecyclerView.ViewHolder viewHolder) {
        Intrinsics.checkNotNullParameter(viewHolder, "holder");
        super.onViewAttachedToWindow(viewHolder);
        if (viewHolder instanceof ContentViewHolder) {
            ((ContentViewHolder) viewHolder).enableTextSelection();
        } else if (viewHolder instanceof ExpandableContentView) {
            ((ExpandableContentView) viewHolder).enableTextSelection();
        } else if (viewHolder instanceof HeaderViewHolder) {
            ((HeaderViewHolder) viewHolder).enableTextSelection();
        } else if (viewHolder instanceof SubHeaderViewHolder) {
            ((SubHeaderViewHolder) viewHolder).enableTextSelection();
        }
    }

    private final void requestShareThroughAD(InlineAdLineItem inlineAdLineItem) {
        Context context = this.mContext;
        if (context == null || !Util.isOnline(context)) {
            this.isSharethroughADLoadingDone = true;
            inlineAdLineItem.setAdView((NativeDFPAD) null);
            return;
        }
        Context context2 = this.mContext;
        Intrinsics.checkNotNull(context2);
        NativeAdAction nativeAdAction = new NativeAdAction(context2, DFPReferenceAdListener.AD_UNIT_ID, (View) null);
        AdSize adSize = DFPAdAction.ADSIZE_320x80;
        Intrinsics.checkNotNullExpressionValue(adSize, "DFPAdAction.ADSIZE_320x80");
        AdSize adSize2 = DFPAdAction.ADSIZE_320x95;
        Intrinsics.checkNotNullExpressionValue(adSize2, "DFPAdAction.ADSIZE_320x95");
        AdSize adSize3 = DFPAdAction.ADSIZE_1x3;
        Intrinsics.checkNotNullExpressionValue(adSize3, "DFPAdAction.ADSIZE_1x3");
        nativeAdAction.prepADWithCombinedRequests(new DrugSectionAdapter$requestShareThroughAD$1(this, inlineAdLineItem), new AdSize[]{adSize, adSize2, adSize3});
        nativeAdAction.isSharethrough = true;
        Context context3 = this.mContext;
        if (!(context3 instanceof DrugDetailsActivity)) {
            this.isSharethroughADLoadingDone = true;
        } else if (context3 != null) {
            ((DrugDetailsActivity) context3).makeShareThroughADRequest(nativeAdAction);
            this.isSharethroughADLoadingDone = false;
        } else {
            throw new NullPointerException("null cannot be cast to non-null type com.medscape.android.drugs.details.views.DrugDetailsActivity");
        }
    }

    public final int getAdPosition() {
        int i = this.adPosition;
        if (i > 0) {
            return i;
        }
        if (this.mItems.size() > 2) {
            return this.mItems.size() - 2;
        }
        return -1;
    }
}
