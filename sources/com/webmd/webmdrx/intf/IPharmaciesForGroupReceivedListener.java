package com.webmd.webmdrx.intf;

import com.webmd.webmdrx.models.RxPricing;
import com.webmd.webmdrx.util.WebMDException;

public interface IPharmaciesForGroupReceivedListener {
    void onPharmaciesReceived(RxPricing rxPricing);

    void onPharmacyRequestFailed(WebMDException webMDException);
}
