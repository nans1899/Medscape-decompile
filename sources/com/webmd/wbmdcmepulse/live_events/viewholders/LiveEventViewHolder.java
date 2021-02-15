package com.webmd.wbmdcmepulse.live_events.viewholders;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.webmd.wbmdcmepulse.R;
import com.webmd.wbmdcmepulse.live_events.model.LiveEventItem;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0016\u0018\u0000 \u001c2\u00020\u0001:\u0001\u001cB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J$\u0010\u0011\u001a\u00020\u00122\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u00142\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019J\u0018\u0010\u001a\u001a\u00020\u00122\u0006\u0010\u001b\u001a\u00020\u00192\u0006\u0010\u0002\u001a\u00020\u0003H\u0002R\u001a\u0010\u0005\u001a\u00020\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u00020\fX.¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010¨\u0006\u001d"}, d2 = {"Lcom/webmd/wbmdcmepulse/live_events/viewholders/LiveEventViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "(Landroid/view/View;)V", "rootView", "Landroid/widget/FrameLayout;", "getRootView", "()Landroid/widget/FrameLayout;", "setRootView", "(Landroid/widget/FrameLayout;)V", "viewMoreLayout", "Landroid/widget/LinearLayout;", "getViewMoreLayout", "()Landroid/widget/LinearLayout;", "setViewMoreLayout", "(Landroid/widget/LinearLayout;)V", "setData", "", "data", "", "Lcom/webmd/wbmdcmepulse/live_events/model/LiveEventItem;", "activity", "Landroid/content/Context;", "liveEventsLoaded", "", "setLayoutVisibility", "isVisible", "Companion", "wbmdcmepulse_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: LiveEventViewHolder.kt */
public class LiveEventViewHolder extends RecyclerView.ViewHolder {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public FrameLayout rootView;
    public LinearLayout viewMoreLayout;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public LiveEventViewHolder(View view) {
        super(view);
        Intrinsics.checkNotNullParameter(view, "itemView");
    }

    public final LinearLayout getViewMoreLayout() {
        LinearLayout linearLayout = this.viewMoreLayout;
        if (linearLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewMoreLayout");
        }
        return linearLayout;
    }

    public final void setViewMoreLayout(LinearLayout linearLayout) {
        Intrinsics.checkNotNullParameter(linearLayout, "<set-?>");
        this.viewMoreLayout = linearLayout;
    }

    public final FrameLayout getRootView() {
        FrameLayout frameLayout = this.rootView;
        if (frameLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rootView");
        }
        return frameLayout;
    }

    public final void setRootView(FrameLayout frameLayout) {
        Intrinsics.checkNotNullParameter(frameLayout, "<set-?>");
        this.rootView = frameLayout;
    }

    public final void setData(List<? extends LiveEventItem> list, Context context, boolean z) {
        Intrinsics.checkNotNullParameter(list, "data");
        Intrinsics.checkNotNullParameter(context, "activity");
        View findViewById = this.itemView.findViewById(R.id.live_root_layout);
        Intrinsics.checkNotNullExpressionValue(findViewById, "itemView.findViewById(R.id.live_root_layout)");
        this.rootView = (FrameLayout) findViewById;
        if (!list.isEmpty()) {
            View findViewById2 = this.itemView.findViewById(R.id.view_more_layout);
            Intrinsics.checkNotNullExpressionValue(findViewById2, "itemView.findViewById(R.id.view_more_layout)");
            this.viewMoreLayout = (LinearLayout) findViewById2;
            if (list.size() > 1) {
                LinearLayout linearLayout = this.viewMoreLayout;
                if (linearLayout == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewMoreLayout");
                }
                linearLayout.setVisibility(0);
            } else {
                LinearLayout linearLayout2 = this.viewMoreLayout;
                if (linearLayout2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewMoreLayout");
                }
                linearLayout2.setVisibility(8);
            }
            View view = this.itemView;
            Intrinsics.checkNotNullExpressionValue(view, "itemView");
            FrameLayout frameLayout = (FrameLayout) view.findViewById(R.id.progress_layout);
            Intrinsics.checkNotNullExpressionValue(frameLayout, "itemView.progress_layout");
            frameLayout.setVisibility(8);
            View view2 = this.itemView;
            Intrinsics.checkNotNullExpressionValue(view2, "itemView");
            LinearLayout linearLayout3 = (LinearLayout) view2.findViewById(R.id.live_card_layout);
            Intrinsics.checkNotNullExpressionValue(linearLayout3, "itemView.live_card_layout");
            linearLayout3.setVisibility(0);
            LiveEventItem liveEventItem = (LiveEventItem) list.get(0);
            View view3 = this.itemView;
            Intrinsics.checkNotNullExpressionValue(view3, "itemView");
            TextView textView = (TextView) view3.findViewById(R.id.live_event_title);
            Intrinsics.checkNotNullExpressionValue(textView, "itemView.live_event_title");
            textView.setText(liveEventItem.getEventTitle());
            View view4 = this.itemView;
            Intrinsics.checkNotNullExpressionValue(view4, "itemView");
            TextView textView2 = (TextView) view4.findViewById(R.id.date);
            Intrinsics.checkNotNullExpressionValue(textView2, "itemView.date");
            textView2.setText(liveEventItem.getEventDate());
            View view5 = this.itemView;
            Intrinsics.checkNotNullExpressionValue(view5, "itemView");
            TextView textView3 = (TextView) view5.findViewById(R.id.location);
            Intrinsics.checkNotNullExpressionValue(textView3, "itemView.location");
            textView3.setText(liveEventItem.getEventLocation());
            FrameLayout frameLayout2 = this.rootView;
            if (frameLayout2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("rootView");
            }
            frameLayout2.setOnClickListener(new LiveEventViewHolder$setData$1(list, context, liveEventItem));
            View view6 = this.itemView;
            Intrinsics.checkNotNullExpressionValue(view6, "itemView");
            setLayoutVisibility(true, view6);
        } else if (z) {
            View view7 = this.itemView;
            Intrinsics.checkNotNullExpressionValue(view7, "itemView");
            setLayoutVisibility(false, view7);
        }
    }

    private final void setLayoutVisibility(boolean z, View view) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams != null) {
            RecyclerView.LayoutParams layoutParams2 = (RecyclerView.LayoutParams) layoutParams;
            if (z) {
                layoutParams2.height = -2;
                layoutParams2.width = -1;
                view.setVisibility(0);
            } else {
                view.setVisibility(8);
                layoutParams2.height = 0;
                layoutParams2.width = 0;
            }
            view.setLayoutParams(layoutParams2);
            return;
        }
        throw new NullPointerException("null cannot be cast to non-null type androidx.recyclerview.widget.RecyclerView.LayoutParams");
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lcom/webmd/wbmdcmepulse/live_events/viewholders/LiveEventViewHolder$Companion;", "", "()V", "create", "Lcom/webmd/wbmdcmepulse/live_events/viewholders/LiveEventViewHolder;", "parent", "Landroid/view/ViewGroup;", "wbmdcmepulse_release"}, k = 1, mv = {1, 4, 0})
    /* compiled from: LiveEventViewHolder.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final LiveEventViewHolder create(ViewGroup viewGroup) {
            Intrinsics.checkNotNullParameter(viewGroup, "parent");
            View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.upcoming_live_events, viewGroup, false);
            Intrinsics.checkNotNullExpressionValue(inflate, "view");
            return new LiveEventViewHolder(inflate);
        }
    }
}
