package com.webmd.webmdrx.parsers;

import com.facebook.places.model.PlaceFields;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.medscape.android.settings.Settings;
import com.wbmd.wbmddrugscommons.constants.Constants;
import com.webmd.webmdrx.models.DrugInteractionInfo;
import com.webmd.webmdrx.models.Pharmacy;
import com.webmd.webmdrx.models.RxData;
import com.webmd.webmdrx.models.RxDrugDetail;
import com.webmd.webmdrx.models.SavedDrugData;
import com.webmd.webmdrx.models.SavedRxDosage;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RxProfileParser {
    public static SavedRxDosage parseSavedRxDosage(JSONObject jSONObject) {
        if (jSONObject == null || jSONObject.length() <= 0) {
            return null;
        }
        SavedRxDosage savedRxDosage = new SavedRxDosage();
        savedRxDosage.setNdc(jSONObject.optString(Constants.WBMDDrugResponseKeyDrugNDC));
        savedRxDosage.setForm(jSONObject.optString("form"));
        savedRxDosage.setStrength(jSONObject.optString("strength"));
        savedRxDosage.setQuantity(jSONObject.optInt(FirebaseAnalytics.Param.QUANTITY));
        savedRxDosage.setPkg_size(jSONObject.optDouble("pkg_size", FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE));
        savedRxDosage.setDrugName(jSONObject.optString(Constants.WBMDDrugResponseKeyDrugName));
        savedRxDosage.setOtherName(jSONObject.optString("genericName"));
        savedRxDosage.setIsGeneric(jSONObject.optBoolean("isGeneric") ? 1 : 0);
        savedRxDosage.setGpi(jSONObject.optString("gpi"));
        savedRxDosage.setUpdateDate(jSONObject.optString("UpdateDate"));
        return savedRxDosage;
    }

    public static RxData parseRxData(JSONObject jSONObject) {
        if (jSONObject == null || jSONObject.length() <= 0) {
            return null;
        }
        RxData rxData = new RxData();
        rxData.setDeleted(jSONObject.optInt("deleted"));
        try {
            JSONArray jSONArray = jSONObject.getJSONArray("dosages");
            for (int i = 0; i < jSONArray.length(); i++) {
                SavedRxDosage parseSavedRxDosage = parseSavedRxDosage(jSONArray.getJSONObject(i));
                if (parseSavedRxDosage != null) {
                    rxData.addToDosagesList(parseSavedRxDosage);
                }
            }
        } catch (JSONException unused) {
        }
        return rxData;
    }

    public static SavedDrugData parseSavedDrugData(JSONObject jSONObject) {
        if (jSONObject == null || jSONObject.length() <= 0) {
            return null;
        }
        SavedDrugData savedDrugData = new SavedDrugData();
        savedDrugData.setType(jSONObject.optString("type"));
        savedDrugData.setDeleted(jSONObject.optInt("deleted"));
        savedDrugData.set_id(jSONObject.optString("_id"));
        savedDrugData.setItem_id(jSONObject.optString(FirebaseAnalytics.Param.ITEM_ID));
        savedDrugData.setTitle(jSONObject.optString("title"));
        savedDrugData.setSummary(jSONObject.optString("summary"));
        savedDrugData.setThumbnailURLString(jSONObject.optString("thumbnailURLString"));
        savedDrugData.setSecondaryID(jSONObject.optString("secondaryID"));
        savedDrugData.setCreateDate(jSONObject.optString("CreatedDate"));
        savedDrugData.setUpdateDate(jSONObject.optString("UpdateDate"));
        try {
            savedDrugData.setDrugDetail(parseRxData(jSONObject.getJSONObject("RxData")));
        } catch (JSONException unused) {
            savedDrugData.setDrugDetail((RxData) null);
        }
        return savedDrugData;
    }

    public static RxDrugDetail parseDrugDetails(JSONObject jSONObject) {
        if (jSONObject != null && jSONObject.length() > 0) {
            try {
                JSONObject jSONObject2 = jSONObject.getJSONObject("data");
                RxDrugDetail rxDrugDetail = new RxDrugDetail();
                rxDrugDetail.setFdbId(jSONObject2.optString(Constants.WBMDDrugResponseKeyAZDrugId));
                rxDrugDetail.setMonoId(jSONObject2.optString(Constants.WBMDDrugResponseKeyAZDrugMonoId));
                rxDrugDetail.setDrugName(jSONObject2.optString(Constants.WBMDDrugResponseKeyDrugName));
                rxDrugDetail.setGp10Id(jSONObject2.optString(Constants.WBMDDrugResponseKeyDrugGP10));
                return rxDrugDetail;
            } catch (JSONException unused) {
            }
        }
        return null;
    }

    public static Pharmacy parsePapixPharmacy(JSONObject jSONObject) {
        JSONObject jSONObject2;
        if (jSONObject != null && jSONObject.length() > 0) {
            Pharmacy pharmacy = new Pharmacy();
            if (!jSONObject.isNull("title")) {
                pharmacy.setName(jSONObject.optString("title"));
                if (!jSONObject.isNull("thumbnailURLString")) {
                    pharmacy.setImage(jSONObject.optString("thumbnailURLString"));
                } else {
                    pharmacy.setImage("");
                }
                if (!jSONObject.isNull(FirebaseAnalytics.Param.ITEM_ID)) {
                    pharmacy.setRxPharmID(jSONObject.optString(FirebaseAnalytics.Param.ITEM_ID));
                    pharmacy.setCreateDate(jSONObject.optString("CreatedDate"));
                    pharmacy.setUpdateDate(jSONObject.optString("UpdateDate"));
                    try {
                        JSONObject jSONObject3 = jSONObject.getJSONObject("RxPharmacyData");
                        if (!jSONObject3.isNull(PlaceFields.PHONE)) {
                            pharmacy.setPhone(jSONObject3.getString(PlaceFields.PHONE));
                            if (!jSONObject3.isNull("rewardsID")) {
                                pharmacy.setRewardsID(jSONObject3.getString("rewardsID"));
                            } else {
                                pharmacy.setRewardsID("");
                            }
                            JSONObject jSONObject4 = jSONObject3.getJSONObject("address");
                            if (!jSONObject4.isNull("street") && !jSONObject4.isNull(Settings.CITY) && !jSONObject4.isNull("state") && !jSONObject4.isNull(Settings.ZIP)) {
                                pharmacy.setAddress1(jSONObject4.optString("street"));
                                pharmacy.setCity(jSONObject4.optString(Settings.CITY));
                                pharmacy.setState(jSONObject4.optString("state"));
                                pharmacy.setPostalCode(jSONObject4.optString(Settings.ZIP));
                                if (jSONObject3.has("location") && !jSONObject3.isNull("location") && (jSONObject2 = jSONObject3.getJSONObject("location")) != null && jSONObject2.length() > 0) {
                                    pharmacy.setLongitude(Double.valueOf(jSONObject2.optDouble(Settings.LONGITUDE_KEY, FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE)));
                                    pharmacy.setLatitude(Double.valueOf(jSONObject2.optDouble(Settings.LATITUDE_KEY, FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE)));
                                }
                                return pharmacy;
                            }
                        }
                    } catch (JSONException unused) {
                    }
                }
            }
        }
        return null;
    }

    public static DrugInteractionInfo parseDrugInteractionObject(JSONObject jSONObject) {
        DrugInteractionInfo drugInteractionInfo = new DrugInteractionInfo();
        if (jSONObject != null && jSONObject.length() > 0) {
            try {
                JSONObject jSONObject2 = jSONObject.getJSONObject("Subject");
                if (jSONObject2 != null) {
                    if (jSONObject2.length() > 0 && !jSONObject2.isNull("Name")) {
                        drugInteractionInfo.setDrug1Name(jSONObject2.optString("Name"));
                    }
                }
                JSONObject jSONObject3 = jSONObject.getJSONObject("Object");
                if (jSONObject3 != null && jSONObject3.length() > 0 && !jSONObject3.isNull("Name")) {
                    drugInteractionInfo.setDrug2Name(jSONObject3.optString("Name"));
                }
                if (!jSONObject.isNull("SeverityId")) {
                    drugInteractionInfo.setSeverityId(jSONObject.optInt("SeverityId", -1));
                }
                JSONObject jSONObject4 = jSONObject.getJSONArray("MetaInfo").getJSONObject(0);
                if (!jSONObject4.isNull("Mechanism")) {
                    drugInteractionInfo.setMechanism(jSONObject4.optString("Mechanism"));
                }
                if (!jSONObject4.isNull("DirectionalityEffect1")) {
                    drugInteractionInfo.setEffect1(jSONObject4.optString("DirectionalityEffect1"));
                }
                if (!jSONObject4.isNull("DirectionalityEffect2")) {
                    drugInteractionInfo.setEffect2(jSONObject4.optString("DirectionalityEffect2"));
                }
                if (!jSONObject4.isNull("Comments")) {
                    drugInteractionInfo.setComment(jSONObject4.optString("Comments"));
                }
                if (drugInteractionInfo.isValid()) {
                    return drugInteractionInfo;
                }
            } catch (JSONException unused) {
            }
        }
        return null;
    }

    public static List<DrugInteractionInfo> parseDrugInteractionArray(JSONArray jSONArray) {
        DrugInteractionInfo parseDrugInteractionObject;
        ArrayList arrayList = new ArrayList();
        if (jSONArray != null && jSONArray.length() > 0) {
            for (int i = 0; i < jSONArray.length(); i++) {
                try {
                    JSONObject jSONObject = jSONArray.getJSONObject(i);
                    if (!(jSONObject == null || jSONObject.length() <= 0 || (parseDrugInteractionObject = parseDrugInteractionObject(jSONObject)) == null)) {
                        arrayList.add(parseDrugInteractionObject);
                    }
                } catch (JSONException unused) {
                }
            }
        }
        return arrayList;
    }
}
