package com.webmd.wbmdcmepulse.models.utils;

import com.webmd.medscape.live.explorelivevents.common.OmnitureConstants;
import com.webmd.wbmdcmepulse.models.CPEvent;
import com.webmd.wbmdcmepulse.models.articles.QuestionRequest;
import com.webmd.wbmdcmepulse.models.articles.QuestionResponse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonObjectConverter {
    public JSONObject convertQuestionRequestToJsonObject(QuestionRequest questionRequest) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("formId", questionRequest.formId);
        JSONArray jSONArray = new JSONArray();
        for (QuestionResponse questionResponse : questionRequest.questionResponseList) {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("choiceId", questionResponse.choideId);
            jSONObject2.put("responseText", questionResponse.responseText);
            jSONObject2.put("questionId", questionResponse.questionId);
            jSONArray.put(jSONObject2);
        }
        jSONObject.put("questionResponseList", jSONArray);
        jSONObject.put("questionnaireId", questionRequest.questionnaireId);
        jSONObject.put("siteid", questionRequest.siteid);
        jSONObject.put("guid", questionRequest.guid);
        return jSONObject;
    }

    public JSONObject convertCPEventToJsonObject(CPEvent cPEvent) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        if (!Extensions.isStringNullOrEmpty(cPEvent.search)) {
            jSONObject.put("search", cPEvent.search);
        }
        if (!Extensions.isStringNullOrEmpty(cPEvent.contentGroup)) {
            jSONObject.put("contentGroup", cPEvent.contentGroup);
        }
        if (!Extensions.isStringNullOrEmpty(cPEvent.uid)) {
            jSONObject.put("uid", cPEvent.uid);
        }
        if (!Extensions.isStringNullOrEmpty(cPEvent.activityId)) {
            jSONObject.put("activityId", cPEvent.activityId);
        }
        if (cPEvent.blockCode != null) {
            JSONArray jSONArray = new JSONArray();
            for (String put : cPEvent.blockCode) {
                jSONArray.put(put);
            }
            jSONObject.put("blockCode", jSONArray);
        }
        if (!Extensions.isStringNullOrEmpty(cPEvent.leadConcept)) {
            jSONObject.put("leadConcept", cPEvent.leadConcept);
        }
        if (!Extensions.isStringNullOrEmpty(cPEvent.pageN)) {
            jSONObject.put("pageN", cPEvent.pageN);
        }
        if (!Extensions.isStringNullOrEmpty(cPEvent.date)) {
            jSONObject.put(OmnitureConstants.OMNITURE_FILTER_DATE, cPEvent.date);
        }
        if (!Extensions.isStringNullOrEmpty(cPEvent.leadSpec)) {
            jSONObject.put("leadSpec", cPEvent.leadSpec);
        }
        if (!Extensions.isStringNullOrEmpty(cPEvent.activityName)) {
            jSONObject.put("activityName", cPEvent.activityName);
        }
        if (!Extensions.isStringNullOrEmpty(cPEvent.appname)) {
            jSONObject.put("appname", cPEvent.appname);
        }
        if (!Extensions.isStringNullOrEmpty(cPEvent.url)) {
            jSONObject.put("url", cPEvent.url);
        }
        return jSONObject;
    }
}
