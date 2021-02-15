package com.medscape.android.consult.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.medscape.android.R;
import com.medscape.android.consult.interfaces.IImageSelectedListener;
import com.medscape.android.consult.models.ConsultAsset;
import com.medscape.android.consult.models.ConsultPost;
import com.medscape.android.consult.viewholders.ConsultImageViewHolder;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B%\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u000e\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u0016\u0010\u000b\u001a\u00020\f2\u000e\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006J\b\u0010\u000e\u001a\u00020\u000fH\u0016J\u0018\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00022\u0006\u0010\u0013\u001a\u00020\u000fH\u0016J\u0018\u0010\u0014\u001a\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u000fH\u0016R\u0016\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/medscape/android/consult/adapters/ConsultHorizontalImageAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "post", "Lcom/medscape/android/consult/models/ConsultPost;", "assetItems", "", "Lcom/medscape/android/consult/models/ConsultAsset;", "mImageSelectedListener", "Lcom/medscape/android/consult/interfaces/IImageSelectedListener;", "(Lcom/medscape/android/consult/models/ConsultPost;Ljava/util/List;Lcom/medscape/android/consult/interfaces/IImageSelectedListener;)V", "areNotSame", "", "items", "getItemCount", "", "onBindViewHolder", "", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: ConsultHorizontalImageAdapter.kt */
public final class ConsultHorizontalImageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final List<ConsultAsset> assetItems;
    private final IImageSelectedListener mImageSelectedListener;
    private final ConsultPost post;

    public ConsultHorizontalImageAdapter(ConsultPost consultPost, List<? extends ConsultAsset> list, IImageSelectedListener iImageSelectedListener) {
        Intrinsics.checkNotNullParameter(consultPost, "post");
        Intrinsics.checkNotNullParameter(iImageSelectedListener, "mImageSelectedListener");
        this.post = consultPost;
        this.assetItems = list;
        this.mImageSelectedListener = iImageSelectedListener;
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        Intrinsics.checkNotNullParameter(viewGroup, "parent");
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.consult_imageview_item, viewGroup, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "LayoutInflater.from(pare…view_item, parent, false)");
        return new ConsultImageViewHolder(inflate, this.mImageSelectedListener);
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        Intrinsics.checkNotNullParameter(viewHolder, "holder");
        if (viewHolder instanceof ConsultImageViewHolder) {
            ConsultPost consultPost = this.post;
            List<ConsultAsset> list = this.assetItems;
            Intrinsics.checkNotNull(list);
            String assetUrl = list.get(i).getAssetUrl();
            Intrinsics.checkNotNullExpressionValue(assetUrl, "assetItems!![position].assetUrl");
            ((ConsultImageViewHolder) viewHolder).onBind(consultPost, assetUrl);
        }
    }

    public int getItemCount() {
        List<ConsultAsset> list = this.assetItems;
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    public final boolean areNotSame(List<? extends ConsultAsset> list) {
        try {
            Intrinsics.checkNotNull(list);
            for (ConsultAsset contains : list) {
                List<ConsultAsset> list2 = this.assetItems;
                Intrinsics.checkNotNull(list2);
                if (!list2.contains(contains)) {
                    return true;
                }
            }
            return false;
        } catch (Throwable unused) {
            return true;
        }
    }
}
