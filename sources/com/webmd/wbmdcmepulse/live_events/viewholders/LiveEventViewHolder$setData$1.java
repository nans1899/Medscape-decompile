package com.webmd.wbmdcmepulse.live_events.viewholders;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import com.webmd.wbmdcmepulse.live_events.model.LiveEventItem;
import com.webmd.wbmdcmepulse.live_events.views.LiveEventWebActivity;
import com.webmd.wbmdcmepulse.live_events.views.LiveEventsActivity;
import com.webmd.wbmdcmepulse.models.utils.Constants;
import java.io.Serializable;
import java.util.List;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, k = 3, mv = {1, 4, 0})
/* compiled from: LiveEventViewHolder.kt */
final class LiveEventViewHolder$setData$1 implements View.OnClickListener {
    final /* synthetic */ Context $activity;
    final /* synthetic */ List $data;
    final /* synthetic */ LiveEventItem $firstItem;

    LiveEventViewHolder$setData$1(List list, Context context, LiveEventItem liveEventItem) {
        this.$data = list;
        this.$activity = context;
        this.$firstItem = liveEventItem;
    }

    public final void onClick(View view) {
        if (this.$data.size() > 1) {
            Intent intent = new Intent(this.$activity, LiveEventsActivity.class);
            List list = this.$data;
            if (list != null) {
                intent.putExtra(Constants.LIVE_EVENTS_DATA, (Serializable) list);
                this.$activity.startActivity(intent);
                return;
            }
            throw new NullPointerException("null cannot be cast to non-null type java.io.Serializable");
        }
        String registerLink = this.$firstItem.getRegisterLink();
        if (registerLink != null) {
            Intent intent2 = new Intent(this.$activity, LiveEventWebActivity.class);
            intent2.putExtra(Constants.BUNDLE_KEY_LIVE_EVENT_REGISTRATION_URL, registerLink);
            this.$activity.startActivity(intent2);
        }
    }
}
