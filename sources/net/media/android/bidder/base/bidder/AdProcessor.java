package net.media.android.bidder.base.bidder;

import android.app.Activity;
import android.view.ViewGroup;
import java.util.Map;
import mnetinternal.aa;
import net.media.android.bidder.base.error.ErrorMessage;
import net.media.android.bidder.base.error.MNetError;
import net.media.android.bidder.base.logging.Logger;
import net.media.android.bidder.base.models.AdSize;
import net.media.android.bidder.base.models.ProcessorOptions;

public final class AdProcessor {
    /* access modifiers changed from: private */
    public a impl = new a();

    public void process(final ProcessorOptions processorOptions, final AdProcessListener adProcessListener) {
        aa.a((Runnable) new Runnable() {
            public void run() {
                try {
                    AdProcessor.this.impl.a(processorOptions, adProcessListener);
                } catch (Exception e) {
                    adProcessListener.onError(new MNetError(ErrorMessage.INTERNAL_ERROR.toString(), 5));
                    Logger.notify("AdProcessor", "Exception in adprocessor", e);
                }
            }
        });
    }

    public String printSizes(AdSize[] adSizeArr) {
        return this.impl.a(adSizeArr);
    }

    public void fireAdSlotDebug(ViewGroup viewGroup, String str, Map<String, Object> map, boolean z, String str2, AdSize[] adSizeArr) {
        this.impl.a(viewGroup, str, map, z, str2, adSizeArr);
    }

    public Activity getCurrentActivity(ViewGroup viewGroup) {
        return this.impl.a(viewGroup);
    }
}
