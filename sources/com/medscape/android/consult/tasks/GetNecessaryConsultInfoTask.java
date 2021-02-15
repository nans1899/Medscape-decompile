package com.medscape.android.consult.tasks;

import android.content.Context;
import android.os.AsyncTask;
import com.google.firebase.crashlytics.internal.common.AbstractSpiCall;
import com.medscape.android.R;
import com.medscape.android.auth.AuthenticationManager;
import com.medscape.android.consult.ConsultUrls;
import com.medscape.android.consult.interfaces.IInitialConsultInfoReceivedListener;
import com.medscape.android.consult.models.ConsultUser;
import com.medscape.android.consult.models.InitialConsultData;
import com.medscape.android.consult.models.ZimbraConfigResponse;
import com.medscape.android.consult.parsers.CommunityUserParser;
import com.medscape.android.consult.parsers.ZimbraConfigParser;
import com.medscape.android.http.HttpRequestObject;
import com.medscape.android.http.HttpResponseObject;
import com.medscape.android.util.HttpUtils;
import com.medscape.android.util.MedscapeException;
import com.medscape.android.util.StringUtil;
import com.medscape.android.util.Util;
import java.util.HashMap;

public class GetNecessaryConsultInfoTask extends AsyncTask<Void, Void, InitialConsultData> {
    private Context mContext;
    private IInitialConsultInfoReceivedListener mListener;

    public GetNecessaryConsultInfoTask(Context context, IInitialConsultInfoReceivedListener iInitialConsultInfoReceivedListener) {
        this.mContext = context;
        this.mListener = iInitialConsultInfoReceivedListener;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00aa  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00c2 A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.medscape.android.consult.models.InitialConsultData doInBackground(java.lang.Void... r10) {
        /*
            r9 = this;
            android.content.Context r10 = r9.mContext
            com.medscape.android.auth.AuthenticationManager r10 = com.medscape.android.auth.AuthenticationManager.getInstance(r10)
            com.medscape.android.consult.models.ZimbraConfigResponse r0 = r9.getZimbraConfigResponse()
            boolean r1 = r9.isZimbraResponseComplete(r0)
            r2 = 1
            r3 = 0
            r4 = 0
            if (r1 == 0) goto L_0x0091
            if (r10 == 0) goto L_0x001e
            java.lang.String r1 = r10.getAuthenticationToken()
            com.medscape.android.consult.models.ConsultUser r1 = r9.getCurrentUserResponse(r1)
            goto L_0x001f
        L_0x001e:
            r1 = r3
        L_0x001f:
            if (r1 == 0) goto L_0x00a7
            java.lang.String r5 = r1.getErrorFromServer()
            boolean r5 = com.medscape.android.util.StringUtil.isNotEmpty(r5)
            if (r5 == 0) goto L_0x0034
            java.lang.String r10 = r1.getErrorFromServer()
            r9.returnErrorFromServer(r10)
            goto L_0x00a4
        L_0x0034:
            java.lang.String r5 = r10.getAuthenticationToken()
            java.lang.String r6 = r0.getGroupId()
            com.medscape.android.http.HttpResponseObject r5 = r9.joinConsultGroup(r5, r6)
            if (r5 == 0) goto L_0x00a7
            int r6 = r5.getResponseCode()
            r7 = 200(0xc8, float:2.8E-43)
            if (r6 != r7) goto L_0x004f
            com.medscape.android.consult.models.InitialConsultData r10 = r9.addDataToIinitialConsultData(r10, r3, r0, r1)
            goto L_0x00a5
        L_0x004f:
            int r6 = r5.getResponseCode()
            r8 = -1205(0xfffffffffffffb4b, float:NaN)
            if (r6 != r8) goto L_0x007f
            android.content.Context r5 = r9.mContext
            r10.refreshAuthToken(r5)
            java.lang.String r5 = r10.getAuthenticationToken()
            boolean r5 = com.medscape.android.util.StringUtil.isNotEmpty(r5)
            if (r5 == 0) goto L_0x00a7
            java.lang.String r5 = r10.getAuthenticationToken()
            java.lang.String r6 = r0.getGroupId()
            com.medscape.android.http.HttpResponseObject r5 = r9.joinConsultGroup(r5, r6)
            if (r5 == 0) goto L_0x00a7
            int r5 = r5.getResponseCode()
            if (r5 != r7) goto L_0x00a7
            com.medscape.android.consult.models.InitialConsultData r10 = r9.addDataToIinitialConsultData(r10, r3, r0, r1)
            goto L_0x00a5
        L_0x007f:
            java.lang.String r10 = r5.getResponseErrorMsg()
            boolean r10 = com.medscape.android.util.StringUtil.isNotEmpty(r10)
            if (r10 == 0) goto L_0x00a7
            java.lang.String r10 = r5.getResponseErrorMsg()
            r9.returnErrorFromServer(r10)
            goto L_0x00a4
        L_0x0091:
            if (r0 == 0) goto L_0x00a7
            java.lang.String r10 = r0.getErrorFromServer()
            boolean r10 = com.medscape.android.util.StringUtil.isNotEmpty(r10)
            if (r10 == 0) goto L_0x00a7
            java.lang.String r10 = r0.getErrorFromServer()
            r9.returnErrorFromServer(r10)
        L_0x00a4:
            r10 = r3
        L_0x00a5:
            r2 = 0
            goto L_0x00a8
        L_0x00a7:
            r10 = r3
        L_0x00a8:
            if (r2 == 0) goto L_0x00c2
            com.medscape.android.util.MedscapeException r10 = new com.medscape.android.util.MedscapeException
            android.content.Context r0 = r9.mContext
            r1 = 2131952035(0x7f1301a3, float:1.9540501E38)
            java.lang.String r0 = r0.getString(r1)
            java.lang.String r1 = "Authentication Failed"
            r10.<init>((java.lang.String) r0, (java.lang.String) r1)
            com.medscape.android.consult.interfaces.IInitialConsultInfoReceivedListener r0 = r9.mListener
            if (r0 == 0) goto L_0x00c1
            r0.onFailedToReceiveInitialConsultInfo(r10)
        L_0x00c1:
            return r3
        L_0x00c2:
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.medscape.android.consult.tasks.GetNecessaryConsultInfoTask.doInBackground(java.lang.Void[]):com.medscape.android.consult.models.InitialConsultData");
    }

    /* access modifiers changed from: protected */
    public void onPostExecute(InitialConsultData initialConsultData) {
        super.onPostExecute(initialConsultData);
        IInitialConsultInfoReceivedListener iInitialConsultInfoReceivedListener = this.mListener;
        if (iInitialConsultInfoReceivedListener != null && initialConsultData != null) {
            iInitialConsultInfoReceivedListener.onInitialConsultInfoReceived(initialConsultData);
        }
    }

    private InitialConsultData addDataToIinitialConsultData(AuthenticationManager authenticationManager, InitialConsultData initialConsultData, ZimbraConfigResponse zimbraConfigResponse, ConsultUser consultUser) {
        InitialConsultData initialConsultData2 = new InitialConsultData();
        initialConsultData2.setAuthToken(authenticationManager.getAuthenticationToken());
        initialConsultData2.setZimbraConfigResponse(zimbraConfigResponse);
        initialConsultData2.setConsultUser(consultUser);
        return initialConsultData2;
    }

    private ZimbraConfigResponse getZimbraConfigResponse() {
        HttpRequestObject httpRequestObject = new HttpRequestObject();
        httpRequestObject.setRequestMethod("GET");
        httpRequestObject.setUrl(ConsultUrls.getZimbraConfigUrl(this.mContext));
        httpRequestObject.setContentType(AbstractSpiCall.ACCEPT_JSON_VALUE);
        HttpResponseObject sendHttpRequest = HttpUtils.sendHttpRequest(httpRequestObject, this.mContext, true);
        if (sendHttpRequest == null) {
            return null;
        }
        ZimbraConfigResponse parseZimbraConfigResponse = ZimbraConfigParser.parseZimbraConfigResponse(sendHttpRequest.getResponseData());
        if (parseZimbraConfigResponse == null) {
            parseZimbraConfigResponse = new ZimbraConfigResponse();
        }
        parseZimbraConfigResponse.setErrorFromServer(sendHttpRequest.getResponseErrorMsg());
        return parseZimbraConfigResponse;
    }

    private ConsultUser getCurrentUserResponse(String str) {
        HttpRequestObject httpRequestObject = new HttpRequestObject();
        httpRequestObject.setRequestMethod("GET");
        httpRequestObject.setAuthorization(str);
        httpRequestObject.setUrl(ConsultUrls.getCurrentProfileUrl(this.mContext));
        httpRequestObject.setContentType(AbstractSpiCall.ACCEPT_JSON_VALUE);
        HttpResponseObject sendHttpRequest = HttpUtils.sendHttpRequest(httpRequestObject, this.mContext, true);
        if (sendHttpRequest == null) {
            return null;
        }
        ConsultUser parseCommunityUserResponse = CommunityUserParser.parseCommunityUserResponse(sendHttpRequest.getResponseData());
        if (parseCommunityUserResponse != null || !StringUtil.isNotEmpty(sendHttpRequest.getResponseErrorMsg())) {
            return parseCommunityUserResponse;
        }
        ConsultUser consultUser = new ConsultUser();
        consultUser.setErrorFromServer(sendHttpRequest.getResponseErrorMsg());
        return consultUser;
    }

    private boolean isZimbraResponseComplete(ZimbraConfigResponse zimbraConfigResponse) {
        if (zimbraConfigResponse != null) {
            String groupId = zimbraConfigResponse.getGroupId();
            String forumId = zimbraConfigResponse.getForumId();
            String mediaGalleryId = zimbraConfigResponse.getMediaGalleryId();
            String hiddenForumId = zimbraConfigResponse.getHiddenForumId();
            return groupId != null && !groupId.trim().equalsIgnoreCase("") && forumId != null && !forumId.trim().equalsIgnoreCase("") && mediaGalleryId != null && !mediaGalleryId.trim().equalsIgnoreCase("") && hiddenForumId != null && !hiddenForumId.trim().equalsIgnoreCase("");
        }
    }

    private HttpResponseObject joinConsultGroup(String str, String str2) {
        HttpRequestObject httpRequestObject = new HttpRequestObject();
        httpRequestObject.setRequestMethod("POST");
        httpRequestObject.setAuthorization(str);
        String joinGroupUrl = ConsultUrls.getJoinGroupUrl(this.mContext);
        HashMap hashMap = new HashMap();
        hashMap.put("groupId", str2);
        httpRequestObject.setUrl(Util.getUrlFromUrlWithMap(joinGroupUrl, hashMap));
        httpRequestObject.setContentType(AbstractSpiCall.ACCEPT_JSON_VALUE);
        return HttpUtils.sendHttpRequest(httpRequestObject, this.mContext, true);
    }

    private void returnErrorFromServer(String str) {
        MedscapeException medscapeException = new MedscapeException(this.mContext.getString(R.string.consult_error_message_title), str);
        IInitialConsultInfoReceivedListener iInitialConsultInfoReceivedListener = this.mListener;
        if (iInitialConsultInfoReceivedListener != null) {
            iInitialConsultInfoReceivedListener.onFailedToReceiveInitialConsultInfo(medscapeException);
        }
    }
}
