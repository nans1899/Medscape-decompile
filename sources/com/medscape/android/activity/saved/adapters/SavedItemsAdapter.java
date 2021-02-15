package com.medscape.android.activity.saved.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.medscape.android.R;
import com.medscape.android.activity.calc.model.CalcArticle;
import com.medscape.android.activity.saved.viewmodel.SavedFeedViewModel;
import com.medscape.android.cache.Cache;
import com.medscape.android.cache.DrugCache;
import com.medscape.android.reference.model.ClinicalReferenceArticle;
import com.wbmd.qxcalculator.model.db.DBContentItem;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\f\u0012\b\u0012\u00060\u0002R\u00020\u00000\u0001:\u0003456B+\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fJ\u001c\u0010%\u001a\u00020&2\n\u0010'\u001a\u00060\u0002R\u00020\u00002\u0006\u0010(\u001a\u00020)H\u0002J\b\u0010*\u001a\u00020+H\u0016J\u001c\u0010,\u001a\u00020&2\n\u0010'\u001a\u00060\u0002R\u00020\u00002\u0006\u0010-\u001a\u00020+H\u0016J\u001c\u0010.\u001a\u00060\u0002R\u00020\u00002\u0006\u0010/\u001a\u0002002\u0006\u00101\u001a\u00020+H\u0016J\u0014\u00102\u001a\u00020&2\f\u00103\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006R\u001a\u0010\u0003\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\n\u001a\u00020\u000bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0015\u001a\u00020\u0016X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u001a\u0010\b\u001a\u00020\tX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR \u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R\u000e\u0010#\u001a\u00020$X\u000e¢\u0006\u0002\n\u0000¨\u00067"}, d2 = {"Lcom/medscape/android/activity/saved/adapters/SavedItemsAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/medscape/android/activity/saved/adapters/SavedItemsAdapter$ViewHolder;", "context", "Landroidx/fragment/app/FragmentActivity;", "objects", "", "", "listener", "Lcom/medscape/android/activity/saved/adapters/SavedItemsAdapter$OnSavedItemClicked;", "deleteListener", "Lcom/medscape/android/activity/saved/adapters/SavedItemsAdapter$OnSavedItemDelete;", "(Landroidx/fragment/app/FragmentActivity;Ljava/util/List;Lcom/medscape/android/activity/saved/adapters/SavedItemsAdapter$OnSavedItemClicked;Lcom/medscape/android/activity/saved/adapters/SavedItemsAdapter$OnSavedItemDelete;)V", "getContext", "()Landroidx/fragment/app/FragmentActivity;", "setContext", "(Landroidx/fragment/app/FragmentActivity;)V", "getDeleteListener", "()Lcom/medscape/android/activity/saved/adapters/SavedItemsAdapter$OnSavedItemDelete;", "setDeleteListener", "(Lcom/medscape/android/activity/saved/adapters/SavedItemsAdapter$OnSavedItemDelete;)V", "inflater", "Landroid/view/LayoutInflater;", "getInflater", "()Landroid/view/LayoutInflater;", "setInflater", "(Landroid/view/LayoutInflater;)V", "getListener", "()Lcom/medscape/android/activity/saved/adapters/SavedItemsAdapter$OnSavedItemClicked;", "setListener", "(Lcom/medscape/android/activity/saved/adapters/SavedItemsAdapter$OnSavedItemClicked;)V", "getObjects", "()Ljava/util/List;", "setObjects", "(Ljava/util/List;)V", "saveViewModel", "Lcom/medscape/android/activity/saved/viewmodel/SavedFeedViewModel;", "calcViewsVisibility", "", "holder", "title", "", "getItemCount", "", "onBindViewHolder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "updateList", "newList", "OnSavedItemClicked", "OnSavedItemDelete", "ViewHolder", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: SavedItemsAdapter.kt */
public final class SavedItemsAdapter extends RecyclerView.Adapter<ViewHolder> {
    private FragmentActivity context;
    private OnSavedItemDelete deleteListener;
    private LayoutInflater inflater;
    private OnSavedItemClicked listener;
    private List<Object> objects;
    private SavedFeedViewModel saveViewModel = ((SavedFeedViewModel) ViewModelProviders.of(this.context).get(new SavedFeedViewModel().getClass()));

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, d2 = {"Lcom/medscape/android/activity/saved/adapters/SavedItemsAdapter$OnSavedItemClicked;", "", "onSavedItemClicked", "", "position", "", "medscape_release"}, k = 1, mv = {1, 4, 0})
    /* compiled from: SavedItemsAdapter.kt */
    public interface OnSavedItemClicked {
        void onSavedItemClicked(int i);
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&¨\u0006\b"}, d2 = {"Lcom/medscape/android/activity/saved/adapters/SavedItemsAdapter$OnSavedItemDelete;", "", "onSavedItemDelete", "", "view", "Landroid/view/View;", "position", "", "medscape_release"}, k = 1, mv = {1, 4, 0})
    /* compiled from: SavedItemsAdapter.kt */
    public interface OnSavedItemDelete {
        void onSavedItemDelete(View view, int i);
    }

    public final FragmentActivity getContext() {
        return this.context;
    }

    public final List<Object> getObjects() {
        return this.objects;
    }

    public final void setContext(FragmentActivity fragmentActivity) {
        Intrinsics.checkNotNullParameter(fragmentActivity, "<set-?>");
        this.context = fragmentActivity;
    }

    public final void setObjects(List<Object> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.objects = list;
    }

    public SavedItemsAdapter(FragmentActivity fragmentActivity, List<Object> list, OnSavedItemClicked onSavedItemClicked, OnSavedItemDelete onSavedItemDelete) {
        Intrinsics.checkNotNullParameter(fragmentActivity, "context");
        Intrinsics.checkNotNullParameter(list, "objects");
        Intrinsics.checkNotNullParameter(onSavedItemClicked, ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        Intrinsics.checkNotNullParameter(onSavedItemDelete, "deleteListener");
        this.context = fragmentActivity;
        this.objects = list;
        this.listener = onSavedItemClicked;
        this.deleteListener = onSavedItemDelete;
        LayoutInflater from = LayoutInflater.from(fragmentActivity);
        Intrinsics.checkNotNullExpressionValue(from, "LayoutInflater.from(context)");
        this.inflater = from;
    }

    public final OnSavedItemDelete getDeleteListener() {
        return this.deleteListener;
    }

    public final OnSavedItemClicked getListener() {
        return this.listener;
    }

    public final void setDeleteListener(OnSavedItemDelete onSavedItemDelete) {
        Intrinsics.checkNotNullParameter(onSavedItemDelete, "<set-?>");
        this.deleteListener = onSavedItemDelete;
    }

    public final void setListener(OnSavedItemClicked onSavedItemClicked) {
        Intrinsics.checkNotNullParameter(onSavedItemClicked, "<set-?>");
        this.listener = onSavedItemClicked;
    }

    public final LayoutInflater getInflater() {
        return this.inflater;
    }

    public final void setInflater(LayoutInflater layoutInflater) {
        Intrinsics.checkNotNullParameter(layoutInflater, "<set-?>");
        this.inflater = layoutInflater;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        Intrinsics.checkNotNullParameter(viewGroup, "parent");
        View inflate = this.inflater.inflate(R.layout.adapter_saved_item, viewGroup, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "view");
        return new ViewHolder(this, inflate);
    }

    public int getItemCount() {
        return this.objects.size();
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        ViewHolder viewHolder2 = viewHolder;
        int i2 = i;
        Intrinsics.checkNotNullParameter(viewHolder2, "holder");
        Object obj = this.objects.get(i2);
        String str = "";
        if (obj instanceof String) {
            viewHolder.getHeaderName().setText((CharSequence) obj);
            viewHolder.getHeaderName().setVisibility(0);
            viewHolder.getSavedLayout().setVisibility(8);
            viewHolder.getViewLayout().setVisibility(8);
            viewHolder.getNewsLayout().setVisibility(8);
        } else if (obj instanceof View) {
            viewHolder.getHeaderName().setVisibility(8);
            viewHolder.getSavedLayout().setVisibility(8);
            viewHolder.getViewLayout().setVisibility(0);
            viewHolder.getNewsLayout().setVisibility(8);
        } else if (obj instanceof Cache) {
            Cache cache = (Cache) obj;
            if (cache.getType() == Cache.CME) {
                str = String.valueOf(i) + "|cme";
                String title = cache.getTitle();
                Intrinsics.checkNotNullExpressionValue(title, "cached.title");
                viewHolder.getHeaderName().setVisibility(8);
                viewHolder.getSavedLayout().setVisibility(0);
                viewHolder.getViewLayout().setVisibility(8);
                viewHolder.getNewsLayout().setVisibility(8);
                viewHolder.getItemName().setText(title);
            } else if (cache.getType() == Cache.NEWS) {
                str = String.valueOf(i) + "|n";
                String title2 = cache.getTitle();
                Intrinsics.checkNotNullExpressionValue(title2, "cached.title");
                viewHolder.getHeaderName().setVisibility(8);
                viewHolder.getSavedLayout().setVisibility(8);
                viewHolder.getViewLayout().setVisibility(8);
                viewHolder.getNewsLayout().setVisibility(0);
                viewHolder.getNewsTitle().setText(title2);
                CharSequence imageUrl = cache.getImageUrl();
                if (!(imageUrl == null || StringsKt.isBlank(imageUrl))) {
                    Glide.with(this.context).asBitmap().load(cache.getImageUrl()).into(viewHolder.getNewsImage());
                    viewHolder.getImageFrame().setVisibility(0);
                } else {
                    viewHolder.getImageFrame().setVisibility(8);
                }
                CharSequence byline = cache.getByline();
                if (!(byline == null || StringsKt.isBlank(byline))) {
                    String byline2 = cache.getByline();
                    Intrinsics.checkNotNullExpressionValue(byline2, "cached.byline");
                    if (StringsKt.contains$default((CharSequence) byline2, (CharSequence) "|", false, 2, (Object) null)) {
                        String byline3 = cache.getByline();
                        Intrinsics.checkNotNullExpressionValue(byline3, "cached.byline");
                        String byline4 = cache.getByline();
                        Intrinsics.checkNotNullExpressionValue(byline4, "cached.byline");
                        int indexOf$default = StringsKt.indexOf$default((CharSequence) byline4, "|", 0, false, 6, (Object) null);
                        if (byline3 != null) {
                            String substring = byline3.substring(0, indexOf$default);
                            Intrinsics.checkNotNullExpressionValue(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                            String byline5 = cache.getByline();
                            Intrinsics.checkNotNullExpressionValue(byline5, "cached.byline");
                            String byline6 = cache.getByline();
                            Intrinsics.checkNotNullExpressionValue(byline6, "cached.byline");
                            int indexOf$default2 = StringsKt.indexOf$default((CharSequence) byline6, "|", 0, false, 6, (Object) null) + 1;
                            int length = cache.getByline().length();
                            if (byline5 != null) {
                                String substring2 = byline5.substring(indexOf$default2, length);
                                Intrinsics.checkNotNullExpressionValue(substring2, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                                viewHolder.getNewsDate().setText(substring2);
                                viewHolder.getNewsInfo().setText(substring);
                                viewHolder.getNewsDate().setVisibility(0);
                                viewHolder.getNewsInfo().setVisibility(0);
                            } else {
                                throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
                            }
                        } else {
                            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
                        }
                    }
                }
                viewHolder.getNewsDate().setVisibility(8);
                viewHolder.getNewsInfo().setVisibility(8);
            }
        } else if (obj instanceof DrugCache) {
            str = String.valueOf(i) + "|d";
            String drugName = ((DrugCache) obj).getDrugName();
            Intrinsics.checkNotNullExpressionValue(drugName, "dCached.drugName");
            viewHolder.getHeaderName().setVisibility(8);
            viewHolder.getSavedLayout().setVisibility(0);
            viewHolder.getViewLayout().setVisibility(8);
            viewHolder.getNewsLayout().setVisibility(8);
            viewHolder.getItemName().setText(drugName);
        } else if (obj instanceof ClinicalReferenceArticle) {
            str = String.valueOf(i) + "|c";
            String title3 = ((ClinicalReferenceArticle) obj).getTitle();
            Intrinsics.checkNotNullExpressionValue(title3, "cr.title");
            viewHolder.getHeaderName().setVisibility(8);
            viewHolder.getSavedLayout().setVisibility(0);
            viewHolder.getViewLayout().setVisibility(8);
            viewHolder.getNewsLayout().setVisibility(8);
            viewHolder.getItemName().setText(title3);
        } else if (obj instanceof CalcArticle) {
            str = String.valueOf(i) + "|calc";
            String title4 = ((CalcArticle) obj).getTitle();
            Intrinsics.checkNotNullExpressionValue(title4, "savedElement.title");
            calcViewsVisibility(viewHolder2, title4);
        } else if (obj instanceof DBContentItem) {
            str = String.valueOf(i) + "|newcalc";
            String name = ((DBContentItem) obj).getName();
            Intrinsics.checkNotNullExpressionValue(name, "savedElement.name");
            calcViewsVisibility(viewHolder2, name);
        }
        if (this.saveViewModel.getEditModeActive()) {
            viewHolder.getNewsDelete().setImageDrawable(this.context.getDrawable(R.drawable.ic_remove_circle_outline_24dp));
            viewHolder.getDeleteItem().setImageDrawable(this.context.getDrawable(R.drawable.ic_remove_circle_outline_24dp));
            viewHolder.getNewsDelete().setVisibility(0);
            viewHolder.getDeleteItem().setVisibility(0);
        } else {
            viewHolder.getNewsDelete().setVisibility(8);
            viewHolder.getDeleteItem().setVisibility(8);
        }
        if (!(obj instanceof Cache) || ((Cache) obj).getType() != Cache.NEWS) {
            viewHolder.getDeleteItem().setTag(str);
        } else {
            viewHolder.getNewsDelete().setTag(str);
        }
        viewHolder.getDeleteItem().setClickable(this.saveViewModel.getEditModeActive());
        viewHolder.getNewsDelete().setClickable(this.saveViewModel.getEditModeActive());
        viewHolder.getDeleteItem().setOnClickListener(new SavedItemsAdapter$onBindViewHolder$1(this, viewHolder2, i2));
        viewHolder.getNewsDelete().setOnClickListener(new SavedItemsAdapter$onBindViewHolder$2(this, viewHolder2, i2));
        viewHolder2.itemView.setOnClickListener(new SavedItemsAdapter$onBindViewHolder$3(this, i2));
    }

    private final void calcViewsVisibility(ViewHolder viewHolder, String str) {
        viewHolder.getHeaderName().setVisibility(8);
        viewHolder.getSavedLayout().setVisibility(0);
        viewHolder.getViewLayout().setVisibility(8);
        viewHolder.getNewsLayout().setVisibility(8);
        viewHolder.getItemName().setText(str);
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\n\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\r\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0011\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\fR\u0011\u0010\u0013\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\fR\u0011\u0010\u0015\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\bR\u0011\u0010\u0017\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\bR\u0011\u0010\u0019\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\fR\u0011\u0010\u001b\u001a\u00020\u001c¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0011\u0010\u001f\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b \u0010\fR\u0011\u0010!\u001a\u00020\u001c¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u001eR\u0011\u0010#\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b$\u0010%¨\u0006&"}, d2 = {"Lcom/medscape/android/activity/saved/adapters/SavedItemsAdapter$ViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "(Lcom/medscape/android/activity/saved/adapters/SavedItemsAdapter;Landroid/view/View;)V", "deleteItem", "Landroid/widget/ImageView;", "getDeleteItem", "()Landroid/widget/ImageView;", "headerName", "Landroid/widget/TextView;", "getHeaderName", "()Landroid/widget/TextView;", "imageFrame", "Landroid/widget/FrameLayout;", "getImageFrame", "()Landroid/widget/FrameLayout;", "itemName", "getItemName", "newsDate", "getNewsDate", "newsDelete", "getNewsDelete", "newsImage", "getNewsImage", "newsInfo", "getNewsInfo", "newsLayout", "Landroid/widget/RelativeLayout;", "getNewsLayout", "()Landroid/widget/RelativeLayout;", "newsTitle", "getNewsTitle", "savedLayout", "getSavedLayout", "viewLayout", "getViewLayout", "()Landroid/view/View;", "medscape_release"}, k = 1, mv = {1, 4, 0})
    /* compiled from: SavedItemsAdapter.kt */
    public final class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView deleteItem;
        private final TextView headerName;
        private final FrameLayout imageFrame;
        private final TextView itemName;
        private final TextView newsDate;
        private final ImageView newsDelete;
        private final ImageView newsImage;
        private final TextView newsInfo;
        private final RelativeLayout newsLayout;
        private final TextView newsTitle;
        private final RelativeLayout savedLayout;
        final /* synthetic */ SavedItemsAdapter this$0;
        private final View viewLayout;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public ViewHolder(SavedItemsAdapter savedItemsAdapter, View view) {
            super(view);
            Intrinsics.checkNotNullParameter(view, "itemView");
            this.this$0 = savedItemsAdapter;
            View findViewById = view.findViewById(R.id.header_name);
            if (findViewById != null) {
                this.headerName = (TextView) findViewById;
                View findViewById2 = view.findViewById(R.id.saved_layout);
                if (findViewById2 != null) {
                    this.savedLayout = (RelativeLayout) findViewById2;
                    View findViewById3 = view.findViewById(R.id.item_name);
                    if (findViewById3 != null) {
                        this.itemName = (TextView) findViewById3;
                        View findViewById4 = view.findViewById(R.id.delete_saved);
                        if (findViewById4 != null) {
                            this.deleteItem = (ImageView) findViewById4;
                            View findViewById5 = view.findViewById(R.id.view_layout);
                            if (findViewById5 != null) {
                                this.viewLayout = findViewById5;
                                View findViewById6 = view.findViewById(R.id.news_saved_layout);
                                if (findViewById6 != null) {
                                    this.newsLayout = (RelativeLayout) findViewById6;
                                    View findViewById7 = view.findViewById(R.id.news_logo);
                                    if (findViewById7 != null) {
                                        this.newsImage = (ImageView) findViewById7;
                                        View findViewById8 = view.findViewById(R.id.news_title);
                                        if (findViewById8 != null) {
                                            this.newsTitle = (TextView) findViewById8;
                                            View findViewById9 = view.findViewById(R.id.news_date);
                                            if (findViewById9 != null) {
                                                this.newsDate = (TextView) findViewById9;
                                                View findViewById10 = view.findViewById(R.id.news_info);
                                                if (findViewById10 != null) {
                                                    this.newsInfo = (TextView) findViewById10;
                                                    View findViewById11 = view.findViewById(R.id.delete_news_saved);
                                                    if (findViewById11 != null) {
                                                        this.newsDelete = (ImageView) findViewById11;
                                                        View findViewById12 = view.findViewById(R.id.image_frame);
                                                        if (findViewById12 != null) {
                                                            this.imageFrame = (FrameLayout) findViewById12;
                                                            return;
                                                        }
                                                        throw new NullPointerException("null cannot be cast to non-null type android.widget.FrameLayout");
                                                    }
                                                    throw new NullPointerException("null cannot be cast to non-null type android.widget.ImageView");
                                                }
                                                throw new NullPointerException("null cannot be cast to non-null type android.widget.TextView");
                                            }
                                            throw new NullPointerException("null cannot be cast to non-null type android.widget.TextView");
                                        }
                                        throw new NullPointerException("null cannot be cast to non-null type android.widget.TextView");
                                    }
                                    throw new NullPointerException("null cannot be cast to non-null type android.widget.ImageView");
                                }
                                throw new NullPointerException("null cannot be cast to non-null type android.widget.RelativeLayout");
                            }
                            throw new NullPointerException("null cannot be cast to non-null type android.view.View");
                        }
                        throw new NullPointerException("null cannot be cast to non-null type android.widget.ImageView");
                    }
                    throw new NullPointerException("null cannot be cast to non-null type android.widget.TextView");
                }
                throw new NullPointerException("null cannot be cast to non-null type android.widget.RelativeLayout");
            }
            throw new NullPointerException("null cannot be cast to non-null type android.widget.TextView");
        }

        public final TextView getHeaderName() {
            return this.headerName;
        }

        public final RelativeLayout getSavedLayout() {
            return this.savedLayout;
        }

        public final TextView getItemName() {
            return this.itemName;
        }

        public final ImageView getDeleteItem() {
            return this.deleteItem;
        }

        public final View getViewLayout() {
            return this.viewLayout;
        }

        public final RelativeLayout getNewsLayout() {
            return this.newsLayout;
        }

        public final ImageView getNewsImage() {
            return this.newsImage;
        }

        public final TextView getNewsTitle() {
            return this.newsTitle;
        }

        public final TextView getNewsDate() {
            return this.newsDate;
        }

        public final TextView getNewsInfo() {
            return this.newsInfo;
        }

        public final ImageView getNewsDelete() {
            return this.newsDelete;
        }

        public final FrameLayout getImageFrame() {
            return this.imageFrame;
        }
    }

    public final void updateList(List<Object> list) {
        Intrinsics.checkNotNullParameter(list, "newList");
        this.objects.clear();
        this.objects.addAll(list);
        notifyDataSetChanged();
    }
}
