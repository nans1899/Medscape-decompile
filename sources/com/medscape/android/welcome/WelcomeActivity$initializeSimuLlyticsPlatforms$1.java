package com.medscape.android.welcome;

import com.medscape.android.settings.Settings;
import com.wbmd.wbmdcommons.logging.Trace;
import com.webmd.wbmdsimullytics.callbacks.IFetchPlatformsListener;
import com.webmd.wbmdsimullytics.callbacks.IPlatformConfigurationListener;
import com.webmd.wbmdsimullytics.constants.PlatformConstants;
import com.webmd.wbmdsimullytics.model.PlatformUserConfig;
import com.webmd.wbmdsimullytics.platform.PlatformConfigurationManager;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import net.media.android.bidder.base.common.Constants;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0000\u0010\u0000\u001a\u00020\u00012*\u0010\u0002\u001a&\u0012\f\u0012\n \u0005*\u0004\u0018\u00010\u00040\u0004 \u0005*\u0012\u0012\f\u0012\n \u0005*\u0004\u0018\u00010\u00040\u0004\u0018\u00010\u00060\u0003H\n¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "", "platforms", "", "", "kotlin.jvm.PlatformType", "", "onPlatformsFetched"}, k = 3, mv = {1, 4, 0})
/* compiled from: WelcomeActivity.kt */
final class WelcomeActivity$initializeSimuLlyticsPlatforms$1 implements IFetchPlatformsListener {
    final /* synthetic */ PlatformConfigurationManager $platformConfigurationManager;
    final /* synthetic */ WelcomeActivity this$0;

    WelcomeActivity$initializeSimuLlyticsPlatforms$1(WelcomeActivity welcomeActivity, PlatformConfigurationManager platformConfigurationManager) {
        this.this$0 = welcomeActivity;
        this.$platformConfigurationManager = platformConfigurationManager;
    }

    public final void onPlatformsFetched(List<String> list) {
        this.$platformConfigurationManager.getUserConfigs(this.this$0, list, new IPlatformConfigurationListener(this) {
            final /* synthetic */ WelcomeActivity$initializeSimuLlyticsPlatforms$1 this$0;

            {
                this.this$0 = r1;
            }

            public final void onPlatformConfigurationReceived(PlatformUserConfig platformUserConfig) {
                StringBuilder sb = new StringBuilder();
                sb.append("Platform: ");
                Intrinsics.checkNotNullExpressionValue(platformUserConfig, Constants.CONFIG_FILE_NAME);
                sb.append(platformUserConfig.getName());
                sb.append(" - Id: ");
                sb.append(platformUserConfig.getUserId());
                Trace.i("WelcomeActivity", sb.toString());
                try {
                    boolean z = true;
                    if (!StringsKt.equals(platformUserConfig.getName(), PlatformConstants.FIREBASE_SERVICE, true)) {
                        return;
                    }
                    if (this.this$0.this$0.isNotificationEnabled()) {
                        this.this$0.this$0.setCustomAttributes(com.medscape.android.Constants.APPBOY_EVENT_BREAKING_NEWS_PUSH_ENABLED, Boolean.valueOf(!StringsKt.equals(Settings.singleton(this.this$0.this$0.getApplicationContext()).getSetting(Settings.HAS_BREAKING_NEWS_NOTIFICATION, "not_set"), "false", true)));
                        this.this$0.this$0.setCustomAttributes(com.medscape.android.Constants.APPBOY_EVENT_TOP_STORIES_PUSH_ENABLED, Boolean.valueOf(!StringsKt.equals(Settings.singleton(this.this$0.this$0.getApplicationContext()).getSetting(Settings.HAS_TOP_STORIES_NOTIFICATION, "not_set"), "false", true)));
                        String setting = Settings.singleton(this.this$0.this$0.getApplicationContext()).getSetting(Settings.HAS_RECOMMENDED_CONTENT_NOTIFICATION, "not_set");
                        WelcomeActivity welcomeActivity = this.this$0.this$0;
                        if (StringsKt.equals(setting, "false", true)) {
                            z = false;
                        }
                        welcomeActivity.setCustomAttributes(com.medscape.android.Constants.APPBOY_EVENT_RECOMMENDED_CONTENT_PUSH_ENABLED, Boolean.valueOf(z));
                        return;
                    }
                    this.this$0.this$0.setCustomAttributes(com.medscape.android.Constants.APPBOY_EVENT_BREAKING_NEWS_PUSH_ENABLED, false);
                    this.this$0.this$0.setCustomAttributes(com.medscape.android.Constants.APPBOY_EVENT_TOP_STORIES_PUSH_ENABLED, false);
                    this.this$0.this$0.setCustomAttributes(com.medscape.android.Constants.APPBOY_EVENT_RECOMMENDED_CONTENT_PUSH_ENABLED, false);
                } catch (Exception e) {
                    Trace.e("WelcomeActivity", e.getMessage());
                }
            }
        });
    }
}
