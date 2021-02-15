package com.webmd.wbmdcmepulse.models.utils.closecaptioning;

import android.content.Context;
import com.wbmd.wbmdcommons.callbacks.ICallbackEvent;
import com.webmd.wbmdcmepulse.models.utils.RequestHelper;
import java.util.ArrayList;
import java.util.Map;

public class CloseCaptionProvider {

    public interface CloseCaptionListener {
        void onCaptionsRecieved(ArrayList<CloseCaption> arrayList);
    }

    public static void getCloseCaptions(Context context, String str, final CloseCaptionListener closeCaptionListener) {
        RequestHelper.getInstance(context).addStringRequest(0, str, (Map<String, String>) null, (Map<String, String>) null, new ICallbackEvent<String, String>() {
            public void onCompleted(String str) {
                closeCaptionListener.onCaptionsRecieved(CloseCaptionParser.parseCloseCaptions(str));
            }

            public void onError(String str) {
                closeCaptionListener.onCaptionsRecieved((ArrayList<CloseCaption>) null);
            }
        });
    }
}
