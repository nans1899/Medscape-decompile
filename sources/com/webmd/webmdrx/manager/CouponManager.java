package com.webmd.webmdrx.manager;

import com.medscape.android.drugs.helper.SearchHelper;
import java.util.Calendar;

public class CouponManager {
    private static final CouponManager mManager = new CouponManager();
    private final String mAlphabet = "ABCDEFGHJKMNOPQRSTUVWXYZ";
    private final String mChannelReferrerString = "X";
    private final String mChannelString = SearchHelper.TYPE_CALCULATOR;
    private final String mPrivateSearchTermString = "XX";

    public String getRxGroup() {
        return "";
    }

    public static CouponManager getInstance() {
        return mManager;
    }

    private CouponManager() {
    }

    private String getYearStringForRxGroup() {
        return String.valueOf("ABCDEFGHJKMNOPQRSTUVWXYZ".charAt((Calendar.getInstance().get(1) - 2016) % 24));
    }

    private String getMonthStringForRxGroup() {
        return String.valueOf("ABCDEFGHJKMNOPQRSTUVWXYZ".charAt(Calendar.getInstance().get(2) % 24));
    }
}
