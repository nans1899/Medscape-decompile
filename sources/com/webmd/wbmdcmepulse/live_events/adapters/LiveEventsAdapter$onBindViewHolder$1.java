package com.webmd.wbmdcmepulse.live_events.adapters;

import android.content.Intent;
import android.view.View;
import com.webmd.wbmdcmepulse.live_events.model.LiveEventItem;
import com.webmd.wbmdcmepulse.live_events.views.LiveEventWebActivity;
import com.webmd.wbmdcmepulse.models.utils.Constants;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, k = 3, mv = {1, 4, 0})
/* compiled from: LiveEventsAdapter.kt */
final class LiveEventsAdapter$onBindViewHolder$1 implements View.OnClickListener {
    final /* synthetic */ LiveEventItem $liveEvent;
    final /* synthetic */ LiveEventsAdapter this$0;

    LiveEventsAdapter$onBindViewHolder$1(LiveEventsAdapter liveEventsAdapter, LiveEventItem liveEventItem) {
        this.this$0 = liveEventsAdapter;
        this.$liveEvent = liveEventItem;
    }

    public final void onClick(View view) {
        String registerLink = this.$liveEvent.getRegisterLink();
        if (registerLink != null) {
            Intent intent = new Intent(this.this$0.getContext(), LiveEventWebActivity.class);
            intent.putExtra(Constants.BUNDLE_KEY_LIVE_EVENT_REGISTRATION_URL, registerLink);
            this.this$0.getContext().startActivity(intent);
        }
    }
}
