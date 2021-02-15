package com.webmd.wbmdcmepulse.live_events.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import com.webmd.wbmdcmepulse.R;
import com.webmd.wbmdcmepulse.live_events.model.LiveEventItem;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0016\u0018\u00002\f\u0012\b\u0012\u00060\u0002R\u00020\u00000\u0001:\u0001\u001cB%\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0016\u0010\u0005\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u0006j\b\u0012\u0004\u0012\u00020\u0007`\b¢\u0006\u0002\u0010\tJ\b\u0010\u0012\u001a\u00020\u0013H\u0016J\u001c\u0010\u0014\u001a\u00020\u00152\n\u0010\u0016\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u0017\u001a\u00020\u0013H\u0016J\u001c\u0010\u0018\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u0013H\u0016R\u001a\u0010\u0003\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR*\u0010\u0005\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u0006j\b\u0012\u0004\u0012\u00020\u0007`\bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011¨\u0006\u001d"}, d2 = {"Lcom/webmd/wbmdcmepulse/live_events/adapters/LiveEventsAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/webmd/wbmdcmepulse/live_events/adapters/LiveEventsAdapter$ViewHolder;", "context", "Landroidx/fragment/app/FragmentActivity;", "liveEvents", "Ljava/util/ArrayList;", "Lcom/webmd/wbmdcmepulse/live_events/model/LiveEventItem;", "Lkotlin/collections/ArrayList;", "(Landroidx/fragment/app/FragmentActivity;Ljava/util/ArrayList;)V", "getContext", "()Landroidx/fragment/app/FragmentActivity;", "setContext", "(Landroidx/fragment/app/FragmentActivity;)V", "getLiveEvents", "()Ljava/util/ArrayList;", "setLiveEvents", "(Ljava/util/ArrayList;)V", "getItemCount", "", "onBindViewHolder", "", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "ViewHolder", "wbmdcmepulse_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: LiveEventsAdapter.kt */
public class LiveEventsAdapter extends RecyclerView.Adapter<ViewHolder> {
    private FragmentActivity context;
    private ArrayList<LiveEventItem> liveEvents;

    public LiveEventsAdapter(FragmentActivity fragmentActivity, ArrayList<LiveEventItem> arrayList) {
        Intrinsics.checkNotNullParameter(fragmentActivity, "context");
        Intrinsics.checkNotNullParameter(arrayList, "liveEvents");
        this.context = fragmentActivity;
        this.liveEvents = arrayList;
    }

    public final FragmentActivity getContext() {
        return this.context;
    }

    public final ArrayList<LiveEventItem> getLiveEvents() {
        return this.liveEvents;
    }

    public final void setContext(FragmentActivity fragmentActivity) {
        Intrinsics.checkNotNullParameter(fragmentActivity, "<set-?>");
        this.context = fragmentActivity;
    }

    public final void setLiveEvents(ArrayList<LiveEventItem> arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
        this.liveEvents = arrayList;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        Intrinsics.checkNotNullParameter(viewGroup, "parent");
        View inflate = LayoutInflater.from(this.context).inflate(R.layout.live_events_card, viewGroup, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "view");
        return new ViewHolder(this, inflate);
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Intrinsics.checkNotNullParameter(viewHolder, "holder");
        LiveEventItem liveEventItem = this.liveEvents.get(i);
        Intrinsics.checkNotNullExpressionValue(liveEventItem, "liveEvents[position]");
        LiveEventItem liveEventItem2 = liveEventItem;
        viewHolder.getEventTitle().setText(liveEventItem2.getEventTitle());
        viewHolder.getEventDate().setText(liveEventItem2.getEventDate());
        viewHolder.getEventLocation().setText(liveEventItem2.getEventLocation());
        viewHolder.getRegisterNow().setText(liveEventItem2.getRegisterLabel());
        viewHolder.getRegisterNow().setVisibility(0);
        viewHolder.getRootView().setOnClickListener(new LiveEventsAdapter$onBindViewHolder$1(this, liveEventItem2));
    }

    public int getItemCount() {
        return this.liveEvents.size();
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\bR\u0011\u0010\u000b\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\bR\u0011\u0010\r\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\bR\u0011\u0010\u000f\u001a\u00020\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0013"}, d2 = {"Lcom/webmd/wbmdcmepulse/live_events/adapters/LiveEventsAdapter$ViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "(Lcom/webmd/wbmdcmepulse/live_events/adapters/LiveEventsAdapter;Landroid/view/View;)V", "eventDate", "Landroid/widget/TextView;", "getEventDate", "()Landroid/widget/TextView;", "eventLocation", "getEventLocation", "eventTitle", "getEventTitle", "registerNow", "getRegisterNow", "rootView", "Landroidx/cardview/widget/CardView;", "getRootView", "()Landroidx/cardview/widget/CardView;", "wbmdcmepulse_release"}, k = 1, mv = {1, 4, 0})
    /* compiled from: LiveEventsAdapter.kt */
    public final class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView eventDate;
        private final TextView eventLocation;
        private final TextView eventTitle;
        private final TextView registerNow;
        private final CardView rootView;
        final /* synthetic */ LiveEventsAdapter this$0;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public ViewHolder(LiveEventsAdapter liveEventsAdapter, View view) {
            super(view);
            Intrinsics.checkNotNullParameter(view, "itemView");
            this.this$0 = liveEventsAdapter;
            View findViewById = view.findViewById(R.id.live_event_title);
            Intrinsics.checkNotNullExpressionValue(findViewById, "itemView.findViewById(R.id.live_event_title)");
            this.eventTitle = (TextView) findViewById;
            View findViewById2 = view.findViewById(R.id.date);
            Intrinsics.checkNotNullExpressionValue(findViewById2, "itemView.findViewById(R.id.date)");
            this.eventDate = (TextView) findViewById2;
            View findViewById3 = view.findViewById(R.id.location);
            Intrinsics.checkNotNullExpressionValue(findViewById3, "itemView.findViewById(R.id.location)");
            this.eventLocation = (TextView) findViewById3;
            View findViewById4 = view.findViewById(R.id.register_now);
            Intrinsics.checkNotNullExpressionValue(findViewById4, "itemView.findViewById(R.id.register_now)");
            this.registerNow = (TextView) findViewById4;
            View findViewById5 = view.findViewById(R.id.live_root_view);
            Intrinsics.checkNotNullExpressionValue(findViewById5, "itemView.findViewById(R.id.live_root_view)");
            this.rootView = (CardView) findViewById5;
        }

        public final TextView getEventTitle() {
            return this.eventTitle;
        }

        public final TextView getEventDate() {
            return this.eventDate;
        }

        public final TextView getEventLocation() {
            return this.eventLocation;
        }

        public final TextView getRegisterNow() {
            return this.registerNow;
        }

        public final CardView getRootView() {
            return this.rootView;
        }
    }
}
