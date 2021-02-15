package com.webmd.webmdrx.omnitureextensions;

import com.medscape.android.BI.omniture.OmnitureManager;
import com.webmd.wbmdcmepulse.models.utils.Constants;
import com.webmd.wbmdomnituremanager.WBMDOmnitureData;
import java.util.HashMap;
import java.util.Map;

public class OmnitureData extends WBMDOmnitureData {
    public String addAppName() {
        return "wrx-app";
    }

    public WBMDOmnitureData getInstance() {
        return this;
    }

    public Map<String, String> addPageNames() {
        HashMap hashMap = new HashMap();
        hashMap.put("HomeFragment", "home-screen");
        hashMap.put("SearchActivity", "search");
        hashMap.put("PrescriptionDetailsActivity", "search/filter");
        hashMap.put("PricingActivity", "drug-prices/list");
        hashMap.put("PricingMapActivity", "drug-prices/map");
        hashMap.put("DisclaimerFragment", "settings/disclaimer");
        hashMap.put(Constants.HOME_ACTIVITY_NAME, "home-screen");
        hashMap.put("InteractionInfoActivity", "interact");
        hashMap.put("RewardActivity", "rewards");
        hashMap.put("DrugDetailsActivity", "med-detail");
        return hashMap;
    }

    public Map<String, String> addPageNameExceptions() {
        HashMap hashMap = new HashMap();
        hashMap.put("ShareFragmentDialog", "");
        hashMap.put("WebViewFragment", "");
        hashMap.put("PharmacyDetailActivity", "");
        hashMap.put("PricingMapFullScreenActivity", "");
        return hashMap;
    }

    public Map<String, String> addModuleNames() {
        HashMap hashMap = new HashMap();
        hashMap.put("SavingsCardFragment", "wrx-cardinfo");
        hashMap.put("PrescriptionDetailsActivity", "wrx-filter");
        hashMap.put("ShareFragmentDialog", "wrx-share");
        return hashMap;
    }

    public Map<String, String> addModuleNameExceptions() {
        HashMap hashMap = new HashMap();
        hashMap.put("WebViewFragment", "");
        hashMap.put("DrugChooserFragmentDialog", "");
        return hashMap;
    }

    public Map<String, String> addDefaultData() {
        HashMap hashMap = new HashMap();
        hashMap.put("wapp.site", addAppName());
        hashMap.put("wapp.bu", "cns");
        hashMap.put("wapp.class", "ooc");
        hashMap.put("wapp.topic", "ntc");
        hashMap.put("wapp.ccgroup", "ntc");
        hashMap.put("wapp.busref", "ntc");
        hashMap.put("wapp.asset", "ntc");
        hashMap.put("wapp.pubsource", "ntc");
        hashMap.put("wapp.pkgtyp", "ntc");
        hashMap.put("wapp.pkgnm", "ntc");
        hashMap.put("wapp.section", "ntc");
        hashMap.put("wapp.subsection", "ntc");
        hashMap.put("wapp.spuri", "ntc");
        hashMap.put("wapp.usergroup", "consumer-unregistered");
        hashMap.put("wapp.registered", "unregistered");
        hashMap.put("wapp.tarseg", "app-webmdrx_none");
        hashMap.put(OmnitureManager.VISITOR_VALUE, "VisitorAPI Present");
        hashMap.put("wapp.wbmdid", "");
        return hashMap;
    }
}
