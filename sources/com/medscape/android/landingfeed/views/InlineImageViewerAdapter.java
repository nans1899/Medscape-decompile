package com.medscape.android.landingfeed.views;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import com.medscape.android.R;
import com.medscape.android.util.GlideApp;
import com.medscape.android.util.GlideRequest;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u0013B\u001b\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\b\u0010\t\u001a\u00020\nH\u0016J\u0018\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020\nH\u0016J\u0018\u0010\u000f\u001a\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\nH\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/medscape/android/landingfeed/views/InlineImageViewerAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "imageUrls", "", "", "clickListener", "Landroid/view/View$OnClickListener;", "(Ljava/util/List;Landroid/view/View$OnClickListener;)V", "getItemCount", "", "onBindViewHolder", "", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "InlineImageViewHolder", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: InlineImageViewerAdapter.kt */
public final class InlineImageViewerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final View.OnClickListener clickListener;
    private final List<String> imageUrls;

    public InlineImageViewerAdapter(List<String> list, View.OnClickListener onClickListener) {
        Intrinsics.checkNotNullParameter(list, "imageUrls");
        Intrinsics.checkNotNullParameter(onClickListener, "clickListener");
        this.imageUrls = list;
        this.clickListener = onClickListener;
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        Intrinsics.checkNotNullParameter(viewGroup, "parent");
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.image_item, viewGroup, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "LayoutInflater.from(pare….image_item,parent,false)");
        return new InlineImageViewHolder(inflate, this.clickListener);
    }

    public int getItemCount() {
        return this.imageUrls.size();
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        Intrinsics.checkNotNullParameter(viewHolder, "holder");
        if (viewHolder instanceof InlineImageViewHolder) {
            ((InlineImageViewHolder) viewHolder).onBind(this.imageUrls.get(i));
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fR\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/medscape/android/landingfeed/views/InlineImageViewerAdapter$InlineImageViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "clickListener", "Landroid/view/View$OnClickListener;", "(Landroid/view/View;Landroid/view/View$OnClickListener;)V", "imageView", "Landroid/widget/ImageView;", "onBind", "", "url", "", "medscape_release"}, k = 1, mv = {1, 4, 0})
    /* compiled from: InlineImageViewerAdapter.kt */
    public static final class InlineImageViewHolder extends RecyclerView.ViewHolder {
        private final View.OnClickListener clickListener;
        private final ImageView imageView;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public InlineImageViewHolder(View view, View.OnClickListener onClickListener) {
            super(view);
            Intrinsics.checkNotNullParameter(view, "itemView");
            Intrinsics.checkNotNullParameter(onClickListener, "clickListener");
            this.clickListener = onClickListener;
            View findViewById = view.findViewById(R.id.content_image);
            Intrinsics.checkNotNullExpressionValue(findViewById, "itemView.findViewById(R.id.content_image)");
            ImageView imageView2 = (ImageView) findViewById;
            this.imageView = imageView2;
            imageView2.setOnClickListener(this.clickListener);
        }

        public final void onBind(String str) {
            Intrinsics.checkNotNullParameter(str, "url");
            GlideRequest placeholder = GlideApp.with(this.imageView.getContext()).load(str).dontAnimate().placeholder((int) R.drawable.placeholder_image);
            View view = this.itemView;
            Intrinsics.checkNotNullExpressionValue(view, "itemView");
            placeholder.into((ImageView) view.findViewById(R.id.content_image));
        }
    }
}
