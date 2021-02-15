package com.medscape.android.activity.webviews;

import android.os.Bundle;
import androidx.lifecycle.Observer;
import com.medscape.android.appboy.AppboyEventsHandler;
import com.medscape.android.util.constants.AppboyConstants;
import com.webmd.wbmdsimullytics.platform.PlatformRouteDispatcher;
import kotlin.Metadata;
import kotlin.text.StringsKt;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "leadConcept", "", "kotlin.jvm.PlatformType", "onChanged"}, k = 3, mv = {1, 4, 0})
/* compiled from: CommonWebViewActivity.kt */
final class CommonWebViewActivity$setupObservers$1<T> implements Observer<String> {
    final /* synthetic */ CommonWebViewActivity this$0;

    CommonWebViewActivity$setupObservers$1(CommonWebViewActivity commonWebViewActivity) {
        this.this$0 = commonWebViewActivity;
    }

    public final void onChanged(String str) {
        if (this.this$0.getWebViewModel().getCanSendEvent()) {
            Bundle addUserDataToViewedEvents = AppboyEventsHandler.addUserDataToViewedEvents();
            CharSequence charSequence = str;
            if (!(charSequence == null || StringsKt.isBlank(charSequence))) {
                addUserDataToViewedEvents.putString("leadConcept", str);
            }
            if (AppboyEventsHandler.isLastEventCallWithTwentyFourHours(AppboyConstants.APPBOY_EVENT_NEWS_VIEWED)) {
                new PlatformRouteDispatcher(this.this$0, false, true).routeEvent(AppboyConstants.APPBOY_EVENT_NEWS_VIEWED, addUserDataToViewedEvents);
            } else {
                new PlatformRouteDispatcher(this.this$0).routeEvent(AppboyConstants.APPBOY_EVENT_NEWS_VIEWED, addUserDataToViewedEvents);
            }
            this.this$0.getWebViewModel().setCanSendEvent(false);
        }
    }
}
