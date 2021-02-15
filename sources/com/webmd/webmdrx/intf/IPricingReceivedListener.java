package com.webmd.webmdrx.intf;

import com.webmd.webmdrx.models.RxPricing;
import com.webmd.webmdrx.util.WebMDException;

public interface IPricingReceivedListener {
    void onPricingReceived(RxPricing rxPricing);

    void onPricingRequestFailed(WebMDException webMDException);
}
