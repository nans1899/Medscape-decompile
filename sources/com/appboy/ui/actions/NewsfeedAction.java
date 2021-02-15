package com.appboy.ui.actions;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.appboy.enums.Channel;
import com.appboy.support.AppboyLogger;
import com.appboy.ui.activities.AppboyFeedActivity;

public class NewsfeedAction implements IAction {
    private final Channel mChannel;
    private final Bundle mExtras;

    public NewsfeedAction(Bundle bundle, Channel channel) {
        this.mExtras = bundle;
        this.mChannel = channel;
    }

    public Channel getChannel() {
        return this.mChannel;
    }

    public void execute(Context context) {
        try {
            Intent intent = new Intent(context, AppboyFeedActivity.class);
            if (this.mExtras != null) {
                intent.putExtras(this.mExtras);
            }
            context.startActivity(intent);
        } catch (Exception e) {
            AppboyLogger.e("ContentValues", "AppboyFeedActivity was not opened successfully.", e);
        }
    }

    public Bundle getExtras() {
        return this.mExtras;
    }
}
