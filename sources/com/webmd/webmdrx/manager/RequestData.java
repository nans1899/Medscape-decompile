package com.webmd.webmdrx.manager;

import android.content.Context;
import webmd.com.environmentswitcher.EnvironmentManagerData;

public class RequestData extends EnvironmentManagerData {
    public RequestData(Context context) {
        super(context);
        super.addAppCustomLink("WRX_TYPEAHEAD_URL", "search/2/api/rx/find/");
        super.addAppCustomLink("WRX_DRUG_FORMS_URL", "search/2/api/rx/forms/");
        super.addAppCustomLink("WRX_DRUG_PRICING_URL", "search/2/api/rx/pricing/");
        super.addAppCustomLink("WRX_SHARE_SAVINGS_EMAIL_URL", "search/2/api/sendemail/");
        super.addAppCustomLink("WRX_SHARE_SAVINGS_PHONE_URL", "search/2/api/rx/sendsms/");
    }
}
