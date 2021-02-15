package com.wbmd.adlibrary.utilities;

import android.content.Context;
import com.wbmd.adlibrary.R;
import com.wbmd.adlibrary.constants.AdTypes;

public class AdTypeViewResourceMapper {
    private static String TAG = AdTypeViewResourceMapper.class.getSimpleName();
    private Context mContext;

    public AdTypeViewResourceMapper(Context context) {
        this.mContext = context;
    }

    public int getResourceId(String str) {
        if (str.equals(AdTypes.BANNER)) {
            return R.id.publisherAdView;
        }
        if (str.equals(AdTypes.BANNER_INLINE)) {
            return R.id.publisherAdView;
        }
        if (str.equals(AdTypes.BRANDSEEKER)) {
            return R.id.publisherAdView;
        }
        return -1;
    }
}
