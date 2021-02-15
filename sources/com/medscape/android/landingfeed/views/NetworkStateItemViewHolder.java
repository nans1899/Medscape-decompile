package com.medscape.android.landingfeed.views;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import androidx.recyclerview.widget.RecyclerView;
import com.medscape.android.R;
import com.medscape.android.landingfeed.repository.NetworkState;
import com.medscape.android.landingfeed.repository.Status;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \f2\u00020\u0001:\u0001\fB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bR\u0016\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/medscape/android/landingfeed/views/NetworkStateItemViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "view", "Landroid/view/View;", "(Landroid/view/View;)V", "progressBar", "Landroid/widget/ProgressBar;", "kotlin.jvm.PlatformType", "bindTo", "", "networkState", "Lcom/medscape/android/landingfeed/repository/NetworkState;", "Companion", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: NetworkStateItemViewHolder.kt */
public final class NetworkStateItemViewHolder extends RecyclerView.ViewHolder {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final ProgressBar progressBar;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public NetworkStateItemViewHolder(View view) {
        super(view);
        Intrinsics.checkNotNullParameter(view, "view");
        this.progressBar = (ProgressBar) view.findViewById(R.id.progress_bar);
    }

    public final void bindTo(NetworkState networkState) {
        ProgressBar progressBar2 = this.progressBar;
        Intrinsics.checkNotNullExpressionValue(progressBar2, "progressBar");
        progressBar2.setVisibility(Companion.toVisibility((networkState != null ? networkState.getStatus() : null) == Status.RUNNING));
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n¨\u0006\u000b"}, d2 = {"Lcom/medscape/android/landingfeed/views/NetworkStateItemViewHolder$Companion;", "", "()V", "create", "Lcom/medscape/android/landingfeed/views/NetworkStateItemViewHolder;", "parent", "Landroid/view/ViewGroup;", "toVisibility", "", "constraint", "", "medscape_release"}, k = 1, mv = {1, 4, 0})
    /* compiled from: NetworkStateItemViewHolder.kt */
    public static final class Companion {
        public final int toVisibility(boolean z) {
            return z ? 0 : 8;
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final NetworkStateItemViewHolder create(ViewGroup viewGroup) {
            Intrinsics.checkNotNullParameter(viewGroup, "parent");
            View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.network_state_item, viewGroup, false);
            Intrinsics.checkNotNullExpressionValue(inflate, "view");
            return new NetworkStateItemViewHolder(inflate);
        }
    }
}
