package com.ib.foreceup;

import com.ib.foreceup.model.RemoteUpdate;
import com.ib.foreceup.model.RemoteUpdateMessaging;
import com.ib.foreceup.model.UserConditions;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RemoteConfigParser {
    public static List<RemoteUpdate> parseRemoteConfigList(String str) throws JSONException {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject(str);
        if (!jSONObject.isNull("remote_updates")) {
            JSONArray jSONArray = jSONObject.getJSONArray("remote_updates");
            for (int i = 0; i < jSONArray.length(); i++) {
                RemoteUpdate parseRemoteConfig = parseRemoteConfig(jSONArray.getJSONObject(i));
                if (RemoteUpdate.TYPE_MANDATORY.equalsIgnoreCase(parseRemoteConfig.getRemoteUpdateType()) || RemoteUpdate.TYPE_RECOMMENDED.equalsIgnoreCase(parseRemoteConfig.getRemoteUpdateType()) || RemoteUpdate.TYPE_OPTIONAL.equalsIgnoreCase(parseRemoteConfig.getRemoteUpdateType()) || RemoteUpdate.TYPE_SILENT.equalsIgnoreCase(parseRemoteConfig.getRemoteUpdateType())) {
                    arrayList.add(parseRemoteConfig);
                }
            }
        }
        return arrayList;
    }

    public static RemoteUpdate parseRemoteConfig(JSONObject jSONObject) throws JSONException {
        JSONObject jSONObject2 = jSONObject;
        RemoteUpdate remoteUpdate = new RemoteUpdate();
        RemoteUpdateMessaging remoteUpdateMessaging = new RemoteUpdateMessaging();
        remoteUpdate.setRemoteUpdateMessaging(remoteUpdateMessaging);
        if (!jSONObject2.isNull("update_type")) {
            remoteUpdate.setRemoteUpdateType(jSONObject2.getString("update_type"));
        }
        if (!jSONObject2.isNull("minimum_os_version")) {
            remoteUpdate.setMinimumOsVersion(jSONObject2.getString("minimum_os_version"));
        }
        if (!jSONObject2.isNull("target_version")) {
            JSONObject jSONObject3 = jSONObject2.getJSONObject("target_version");
            if (!jSONObject3.isNull("min")) {
                remoteUpdate.setTargetVersionMin(jSONObject3.getString("min"));
            }
            if (!jSONObject3.isNull("max")) {
                remoteUpdate.setTargetVersionMax(jSONObject3.getString("max"));
            }
        }
        JSONObject jSONObject4 = jSONObject2.getJSONObject("update_messaging");
        if (!jSONObject4.isNull("update_available_title")) {
            remoteUpdateMessaging.setTitle(jSONObject4.getString("update_available_title"));
        }
        if (!jSONObject4.isNull("update_text")) {
            remoteUpdateMessaging.setMessage(jSONObject4.getString("update_text"));
        }
        if (!jSONObject4.isNull("update_button_text")) {
            remoteUpdateMessaging.setUpdateButtonText(jSONObject4.getString("update_button_text"));
        }
        if (!jSONObject4.isNull("dismiss_button_text")) {
            remoteUpdateMessaging.setDimissButtonText(jSONObject4.getString("dismiss_button_text"));
        }
        if (!jSONObject4.isNull("skip_button_text")) {
            remoteUpdateMessaging.setSkipButtonText(jSONObject4.getString("skip_button_text"));
        }
        if (jSONObject2.has("additional_criteria")) {
            JSONArray jSONArray = jSONObject2.getJSONArray("additional_criteria");
            HashMap hashMap = new HashMap();
            ArrayList arrayList = new ArrayList();
            String str = "";
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject5 = (JSONObject) jSONArray.get(i);
                Iterator<String> keys = jSONObject5.keys();
                while (keys.hasNext()) {
                    String next = keys.next();
                    if (next.equals("target")) {
                        str = jSONObject5.getString(next);
                    } else if (next.equals("conditions")) {
                        JSONArray jSONArray2 = jSONObject5.getJSONArray("conditions");
                        for (int i2 = 0; i2 < jSONArray2.length(); i2++) {
                            JSONObject jSONObject6 = (JSONObject) jSONArray2.get(i2);
                            UserConditions userConditions = new UserConditions();
                            userConditions.setKay(jSONObject6.getString("key"));
                            JSONArray jSONArray3 = jSONObject6.getJSONArray("value");
                            ArrayList arrayList2 = new ArrayList();
                            if (jSONArray3 != null) {
                                for (int i3 = 0; i3 < jSONArray3.length(); i3++) {
                                    arrayList2.add(jSONArray3.getString(i3));
                                }
                                userConditions.setValue(arrayList2);
                            }
                            userConditions.setInverse(jSONObject6.getBoolean("inverse"));
                            arrayList.add(userConditions);
                        }
                        hashMap.put(str, arrayList);
                    }
                }
            }
            remoteUpdate.setAdditionalCriteria(hashMap);
        } else {
            remoteUpdate.setAdditionalCriteria((HashMap<String, ArrayList<UserConditions>>) null);
        }
        return remoteUpdate;
    }
}
