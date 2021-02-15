package com.webmd.webmdrx.omnitureextensions;

import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.webmd.wbmdcmepulse.models.utils.Constants;
import com.webmd.wbmdomnituremanager.WBMDOmnitureManager;
import com.webmd.wbmdomnituremanager.WBMDOmnitureModule;
import com.webmd.wbmdomnituremanager.WBMDOmniturePageNames;
import com.webmd.webmdrx.activities.PharmacyDetailActivity;
import com.webmd.webmdrx.activities.pricing.PricingMapFullScreenActivity;
import com.webmd.webmdrx.fragments.DrugChooserFragmentDialog;
import com.webmd.webmdrx.fragments.ShareFragmentDialog;

public class OmniturePageNames extends WBMDOmniturePageNames {
    private static WBMDOmnitureModule getWebViewFragmentPageModule(Object obj) {
        return null;
    }

    public String getPageNameForException(Object obj) {
        String simpleName = obj.getClass().getSimpleName();
        String pageNameForPharmacyDetailActivity = simpleName.equals("PharmacyDetailActivity") ? getPageNameForPharmacyDetailActivity(obj) : "";
        if (simpleName.equals("PricingMapFullScreenActivity")) {
            pageNameForPharmacyDetailActivity = getPricingMapFullScreenActivityPageName(obj);
        }
        return simpleName.equals("ShareFragmentDialog") ? getShareFragmentDialogPageName(obj) : pageNameForPharmacyDetailActivity;
    }

    public WBMDOmnitureModule getModuleForException(Object obj) {
        String simpleName = obj.getClass().getSimpleName();
        return simpleName.equals("DrugChooserFragmentDialog") ? getModuleNameForFilters(obj) : simpleName.equals("WebViewFragment") ? getWebViewFragmentPageModule(obj) : null;
    }

    public static String getPageNameForPharmacyDetailActivity(Object obj) {
        String replaceAll = ((PharmacyDetailActivity) obj).getCurrentPrice().getPharmacy().getName().toLowerCase().replaceAll(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR, "-");
        return "" + "drug-prices/view/" + replaceAll;
    }

    private static String getPricingMapFullScreenActivityPageName(Object obj) {
        String replaceAll = ((PricingMapFullScreenActivity) obj).getPrice().getPharmacy().getName().toLowerCase().replaceAll(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR, "-");
        return "drug-prices/view/" + replaceAll + "/expanded-map";
    }

    private static String getShareFragmentDialogPageName(Object obj) {
        String simpleName = ((ShareFragmentDialog) obj).getAttachedActivity().getClass().getSimpleName();
        if (simpleName.equals(Constants.HOME_ACTIVITY_NAME)) {
            return "" + "home-screen/showcard/share";
        } else if (!simpleName.equals("PricingActivity") && !simpleName.equals("PricingMapActivity")) {
            return "";
        } else {
            return "" + "drug-prices/showcard/share";
        }
    }

    public static WBMDOmnitureModule getModuleNameForFilters(Object obj) {
        int filter = ((DrugChooserFragmentDialog) obj).getFilter();
        String str = "";
        if (filter == 0) {
            str = str + "wrx-filter-drug";
        } else if (filter == 1) {
            str = str + "wrx-filter-form";
        } else if (filter == 2) {
            str = str + "wrx-filter-dose";
        } else if (filter == 3) {
            str = str + "wrx-filter-quantity";
        }
        return new WBMDOmnitureModule(str, (String) null, WBMDOmnitureManager.shared.getLastSentPage());
    }
}
