package com.wbmd.wbmddrugscommons.parsers;

import com.wbmd.wbmdcommons.extensions.StringExtensions;
import com.wbmd.wbmdcommons.logging.Trace;
import com.wbmd.wbmddrugscommons.constants.Constants;
import com.wbmd.wbmddrugscommons.data.DrugTTSData;
import com.wbmd.wbmddrugscommons.model.DrugDosage;
import com.wbmd.wbmddrugscommons.model.DrugMonograph;
import com.wbmd.wbmddrugscommons.model.DrugMonographSection;
import com.wbmd.wbmddrugscommons.model.Slide;
import com.wbmd.wbmddrugscommons.model.Tug;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DrugMonographParser {
    private static final String TAG = DrugMonographParser.class.getSimpleName();
    private JSONObject mJsonObject;
    private DrugMonograph mRequest;

    public DrugMonographParser(DrugMonograph drugMonograph, JSONObject jSONObject) {
        this.mJsonObject = jSONObject;
        this.mRequest = drugMonograph;
    }

    public DrugMonograph parse() {
        JSONObject jSONObject;
        JSONObject jSONObject2 = this.mJsonObject;
        DrugMonograph drugMonograph = null;
        if (jSONObject2 == null || this.mRequest == null) {
            return null;
        }
        try {
            if (!jSONObject2.getString("status").equalsIgnoreCase("ok") || (jSONObject = this.mJsonObject.getJSONObject("data")) == null) {
                return null;
            }
            DrugMonograph drugMonograph2 = new DrugMonograph();
            try {
                drugMonograph2.idType = this.mRequest.idType;
                drugMonograph2.ndc = this.mRequest.ndc;
                String str = "";
                drugMonograph2.FDB_id = !StringExtensions.isNullOrEmpty(jSONObject.getString(Constants.WBMDDrugResponseKeyAZDrugId)) ? jSONObject.getString(Constants.WBMDDrugResponseKeyAZDrugId) : str;
                drugMonograph2.tDrugName = !StringExtensions.isNullOrEmpty(jSONObject.getString(Constants.WBMDDrugResponseKeyDrugName)) ? jSONObject.getString(Constants.WBMDDrugResponseKeyDrugName) : str;
                drugMonograph2.monographId = !StringExtensions.isNullOrEmpty(jSONObject.getString(Constants.WBMDDrugResponseKeyAZDrugMonoId)) ? jSONObject.getString(Constants.WBMDDrugResponseKeyAZDrugMonoId) : str;
                if (!StringExtensions.isNullOrEmpty(jSONObject.getString(Constants.WBMDDrugResponseKeyAZUrlSuffix))) {
                    str = jSONObject.getString(Constants.WBMDDrugResponseKeyAZUrlSuffix);
                }
                drugMonograph2.urlSuffix = str;
                drugMonograph2.drugDescription = jSONObject.getString("description");
                drugMonograph2.primaryTopicId = jSONObject.optString(Constants.WBMDDrugResponseKeyDrugTopicId);
                drugMonograph2.genericDrugId = parseDrugGenericIds(jSONObject);
                drugMonograph2.tGenericName = parseDrugDetailsCommaSeparated(jSONObject, Constants.WBMDDrugResponseKeyDrugGenericNames);
                drugMonograph2.gp10 = jSONObject.optString(Constants.WBMDDrugResponseKeyDrugGP10);
                drugMonograph2.gp14 = parseGp(jSONObject, Constants.WBMDDrugResponseKeyDrugGP14);
                drugMonograph2.ndcList = parseList(jSONObject, Constants.WBMDDrugResponseKeyDrugNDC);
                drugMonograph2.drugMonographSectionsMap = parseSections(jSONObject);
                drugMonograph2.tBrandNames = parseBrandNames(jSONObject);
                String[] split = drugMonograph2.FDB_id.split("_");
                if (split.length <= 1 || split[1] == null) {
                    drugMonograph2.id = split[0];
                } else {
                    drugMonograph2.id = split[1];
                }
                drugMonograph2.tugs = parseTTSData(drugMonograph2.id);
                drugMonograph2.images = parseImages(drugMonograph2.monographId, parseDosageForms(drugMonograph2.monographId, jSONObject), jSONObject);
                drugMonograph2.dosages = parseDosages(drugMonograph2.monographId, jSONObject);
                return drugMonograph2;
            } catch (JSONException e) {
                e = e;
                drugMonograph = drugMonograph2;
                Trace.e(TAG, e.getMessage());
                return drugMonograph;
            }
        } catch (JSONException e2) {
            e = e2;
            Trace.e(TAG, e.getMessage());
            return drugMonograph;
        }
    }

    private ArrayList<Tug> parseTTSData(String str) {
        ArrayList<Tug> arrayList = null;
        if (StringExtensions.isNullOrEmpty(str)) {
            return null;
        }
        HashMap<String, Tug> tTSData = DrugTTSData.get().getTTSData();
        if (tTSData != null && tTSData.size() > 0) {
            arrayList = new ArrayList<>();
            for (Map.Entry next : tTSData.entrySet()) {
                if (((String) next.getKey()).equals(str)) {
                    PrintStream printStream = System.out;
                    printStream.println("Key = " + ((String) next.getKey()) + ", Value = " + ((Tug) next.getValue()).getId());
                    arrayList.add(next.getValue());
                }
            }
        }
        return arrayList;
    }

    private List<String> parseGp(JSONObject jSONObject, String str) {
        String optString = jSONObject.optString(str);
        if (optString == null || optString.length() <= 0) {
            return null;
        }
        return Arrays.asList(optString.split("\\s*,\\s*"));
    }

    private ArrayList<String> parseList(JSONObject jSONObject, String str) {
        ArrayList<String> arrayList = new ArrayList<>();
        try {
            JSONArray jSONArray = jSONObject.getJSONArray(str);
            if (jSONArray != null && jSONArray.length() > 0) {
                for (int i = 0; i < jSONArray.length(); i++) {
                    arrayList.add(jSONArray.getString(i));
                }
            }
        } catch (JSONException e) {
            Trace.e(TAG, e.getMessage());
        }
        return arrayList;
    }

    private String parseDrugGenericName(JSONObject jSONObject) {
        StringBuilder sb = new StringBuilder();
        try {
            JSONArray jSONArray = jSONObject.getJSONArray(Constants.WBMDDrugResponseKeyDrugGenericNames);
            if (jSONArray != null && jSONArray.length() > 0) {
                for (int i = 0; i < jSONArray.length(); i++) {
                    sb.append(jSONArray.getString(i));
                    if (i < jSONArray.length() - 1) {
                        sb.append(", ");
                    }
                }
            }
        } catch (JSONException e) {
            Trace.e(TAG, e.getMessage());
        }
        return sb.toString();
    }

    private String parseDrugDetailsCommaSeparated(JSONObject jSONObject, String str) {
        StringBuilder sb = new StringBuilder();
        try {
            JSONArray jSONArray = jSONObject.getJSONArray(str);
            if (jSONArray != null && jSONArray.length() > 0) {
                for (int i = 0; i < jSONArray.length(); i++) {
                    sb.append(jSONArray.getString(i));
                    if (i < jSONArray.length() - 1) {
                        sb.append(", ");
                    }
                }
            }
        } catch (JSONException e) {
            Trace.e(TAG, e.getMessage());
        }
        return sb.toString();
    }

    private String parseDrugGenericIds(JSONObject jSONObject) {
        StringBuilder sb = new StringBuilder();
        try {
            JSONArray jSONArray = jSONObject.getJSONArray(Constants.WBMDDrugResponseKeyDrugGenericIds);
            if (jSONArray != null && jSONArray.length() > 0) {
                for (int i = 0; i < jSONArray.length(); i++) {
                    sb.append(jSONArray.getString(i));
                    if (i < jSONArray.length() - 1) {
                        sb.append(", ");
                    }
                }
            }
        } catch (JSONException e) {
            Trace.e(TAG, e.getMessage());
        }
        return sb.toString();
    }

    private LinkedHashMap<String, DrugMonographSection> parseSections(JSONObject jSONObject) {
        LinkedHashMap<String, DrugMonographSection> linkedHashMap = new LinkedHashMap<>();
        try {
            JSONObject jSONObject2 = jSONObject.getJSONObject(Constants.WBMDDrugResponseKeyDrugMonograph);
            if (jSONObject2 != null) {
                Iterator<String> keys = jSONObject2.keys();
                while (keys.hasNext()) {
                    String next = keys.next();
                    String string = jSONObject2.getString(next);
                    DrugMonographSection drugMonographSection = new DrugMonographSection();
                    drugMonographSection.id = next;
                    drugMonographSection.content = string;
                    linkedHashMap.put(next, drugMonographSection);
                }
            }
        } catch (JSONException e) {
            Trace.e(TAG, e.getMessage());
        }
        return linkedHashMap;
    }

    private String parseBrandNames(JSONObject jSONObject) {
        StringBuilder sb = new StringBuilder();
        try {
            JSONArray jSONArray = jSONObject.getJSONArray(Constants.WBMDDrugResponseKeyDrugBrands);
            if (jSONArray != null && jSONArray.length() > 0) {
                for (int i = 0; i < jSONArray.length(); i++) {
                    sb.append(jSONArray.getString(i));
                    if (i < jSONArray.length() - 1) {
                        sb.append(", ");
                    }
                }
            }
        } catch (JSONException e) {
            Trace.e(TAG, e.getMessage());
        }
        return sb.toString();
    }

    private HashMap<String, String> parseDosageForms(String str, JSONObject jSONObject) {
        HashMap<String, String> hashMap = new HashMap<>();
        try {
            JSONArray jSONArray = jSONObject.getJSONArray(Constants.WBMDDrugResponseKeyDrugDosages);
            if (!(str == null || jSONArray == null || jSONArray.length() <= 0)) {
                for (int i = 0; i < jSONArray.length(); i++) {
                    JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                    hashMap.put(jSONObject2.optString(Constants.WBMDDrugKeyDispensableDrugId), jSONObject2.optString(Constants.WBMDDrugKeyTag));
                }
            }
        } catch (JSONException e) {
            Trace.e(TAG, e.getMessage());
        }
        return hashMap;
    }

    private ArrayList<DrugDosage> parseDosages(String str, JSONObject jSONObject) {
        ArrayList<DrugDosage> arrayList = new ArrayList<>();
        try {
            JSONArray jSONArray = jSONObject.getJSONArray(Constants.WBMDDrugResponseKeyDrugDosages);
            if (!(str == null || jSONArray == null || jSONArray.length() <= 0)) {
                for (int i = 0; i < jSONArray.length(); i++) {
                    JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                    DrugDosage drugDosage = new DrugDosage();
                    drugDosage.setName(jSONObject2.optString("name"));
                    drugDosage.setTag(jSONObject2.optString(Constants.WBMDDrugKeyTag));
                    drugDosage.setType(jSONObject2.optString("type"));
                    drugDosage.setForms(jSONObject2.optString(Constants.WBMDDrugKeyDosageForms));
                    drugDosage.setStrength(jSONObject2.optString(Constants.WBMDDrugKeyMedStrength));
                    drugDosage.setUnit(jSONObject2.optString(Constants.WBMDDrugKeyMedUnit));
                    drugDosage.setRoute(jSONObject2.optString(Constants.WBMDDrugKeyRoute));
                    drugDosage.setDispensableDrugId(jSONObject2.optString(Constants.WBMDDrugKeyDispensableDrugId));
                    drugDosage.setStatusCode(jSONObject2.optString(Constants.WBMDDrugKeyDispensableDrugStatus));
                    JSONArray optJSONArray = jSONObject2.optJSONArray(Constants.WBMDDrugResponseKeyDrugMonographIds);
                    if (optJSONArray != null && optJSONArray.length() > 0) {
                        ArrayList arrayList2 = new ArrayList();
                        for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                            String optString = optJSONArray.optString(i2);
                            if (str.equals(optString)) {
                                arrayList2.add(optString);
                            }
                        }
                        drugDosage.setMonographIds(arrayList2);
                    }
                    arrayList.add(drugDosage);
                }
            }
        } catch (JSONException e) {
            Trace.e(TAG, e.getMessage());
        }
        return arrayList;
    }

    private ArrayList<Slide> parseImages(String str, HashMap<String, String> hashMap, JSONObject jSONObject) {
        ArrayList<Slide> arrayList = new ArrayList<>();
        try {
            JSONArray jSONArray = jSONObject.getJSONArray(Constants.WBMDDrugResponseKeyDrugImages);
            if (!(str == null || jSONArray == null || jSONArray.length() <= 0)) {
                for (int i = 0; i < jSONArray.length(); i++) {
                    JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                    JSONArray jSONArray2 = jSONObject2.getJSONArray(Constants.WBMDDrugResponseKeyDrugMonographIds);
                    if (jSONArray2 != null && jSONArray2.length() > 0 && str.equals(jSONArray2.getString(0))) {
                        Slide slide = new Slide();
                        slide.graphicUrl = jSONObject2.optString("path");
                        slide.caption = jSONObject2.optString("alt_tag");
                        slide.objectId = jSONObject2.optString(Constants.WBMDDrugKeyDispensableDrugId);
                        slide.label = hashMap.get(slide.objectId);
                        slide.name = jSONObject2.optString("name");
                        arrayList.add(slide);
                    }
                }
            }
        } catch (JSONException e) {
            Trace.e(TAG, e.getMessage());
        }
        return arrayList;
    }
}
