package com.wbmd.ads;

import com.wbmd.ads.model.AdContainer;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J\u0010\u0010\b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\t"}, d2 = {"Lcom/wbmd/ads/IAdListener;", "", "onAdFailed", "", "adContainer", "Lcom/wbmd/ads/model/AdContainer;", "errorCode", "", "onAdLoaded", "wbmdadsdk_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: IAdListener.kt */
public interface IAdListener {
    void onAdFailed(AdContainer adContainer, int i);

    void onAdLoaded(AdContainer adContainer);
}
