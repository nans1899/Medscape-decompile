package com.medscape.android.consult.viewholders;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import com.medscape.android.R;
import com.medscape.android.consult.interfaces.IImageSelectedListener;
import com.medscape.android.consult.models.ConsultPost;
import com.medscape.android.util.GlideApp;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0016\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u0015"}, d2 = {"Lcom/medscape/android/consult/viewholders/ConsultImageViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "imageSelectedListener", "Lcom/medscape/android/consult/interfaces/IImageSelectedListener;", "(Landroid/view/View;Lcom/medscape/android/consult/interfaces/IImageSelectedListener;)V", "imageLayout", "Landroid/widget/FrameLayout;", "getImageLayout", "()Landroid/widget/FrameLayout;", "imageView", "Landroid/widget/ImageView;", "getImageView", "()Landroid/widget/ImageView;", "onBind", "", "post", "Lcom/medscape/android/consult/models/ConsultPost;", "url", "", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: ConsultImageViewHolder.kt */
public final class ConsultImageViewHolder extends RecyclerView.ViewHolder {
    private final FrameLayout imageLayout;
    /* access modifiers changed from: private */
    public final IImageSelectedListener imageSelectedListener;
    private final ImageView imageView;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ConsultImageViewHolder(View view, IImageSelectedListener iImageSelectedListener) {
        super(view);
        Intrinsics.checkNotNullParameter(view, "itemView");
        Intrinsics.checkNotNullParameter(iImageSelectedListener, "imageSelectedListener");
        this.imageSelectedListener = iImageSelectedListener;
        View findViewById = view.findViewById(R.id.image);
        Intrinsics.checkNotNullExpressionValue(findViewById, "itemView.findViewById(R.id.image)");
        this.imageView = (ImageView) findViewById;
        View findViewById2 = view.findViewById(R.id.main);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "itemView.findViewById(R.id.main)");
        this.imageLayout = (FrameLayout) findViewById2;
        this.imageView.setOnClickListener(new View.OnClickListener(this) {
            final /* synthetic */ ConsultImageViewHolder this$0;

            {
                this.this$0 = r1;
            }

            public final void onClick(View view) {
                this.this$0.imageSelectedListener.onImageSelected(this.this$0.getAdapterPosition());
            }
        });
    }

    public final ImageView getImageView() {
        return this.imageView;
    }

    public final FrameLayout getImageLayout() {
        return this.imageLayout;
    }

    public final void onBind(ConsultPost consultPost, String str) {
        Intrinsics.checkNotNullParameter(consultPost, "post");
        Intrinsics.checkNotNullParameter(str, "url");
        if (consultPost.isShowSponsored()) {
            FrameLayout frameLayout = this.imageLayout;
            Context context = frameLayout.getContext();
            Intrinsics.checkNotNullExpressionValue(context, "imageLayout.context");
            frameLayout.setBackgroundColor(context.getResources().getColor(R.color.consult_sponsored_post));
        } else {
            FrameLayout frameLayout2 = this.imageLayout;
            Context context2 = frameLayout2.getContext();
            Intrinsics.checkNotNullExpressionValue(context2, "imageLayout.context");
            frameLayout2.setBackgroundColor(context2.getResources().getColor(R.color.light_grey));
        }
        GlideApp.with(this.imageView.getContext()).load(str).placeholder((int) R.drawable.small_progress).fitCenter().into(this.imageView);
    }
}
