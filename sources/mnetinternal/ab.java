package mnetinternal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import net.media.android.bidder.base.logging.Logger;

public abstract class ab extends BroadcastReceiver {
    public abstract void a(Context context, Intent intent);

    public void onReceive(Context context, Intent intent) {
        try {
            a(context, intent);
        } catch (Exception e) {
            Logger.notify("##MnetReceiver", e.getMessage(), e.getCause());
        }
    }
}
