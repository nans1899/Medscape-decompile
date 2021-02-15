package com.wbmd.qxcalculator.model.api;

import com.google.android.gms.common.ConnectionResult;
import com.wbmd.qxcalculator.model.QxError;
import com.wbmd.qxcalculator.model.contentItems.common.ContentItem;
import com.wbmd.qxcalculator.model.contentItems.common.Favorite;
import com.wbmd.qxcalculator.model.contentItems.common.Recent;
import com.wbmd.qxcalculator.model.parsedObjects.Location;
import com.wbmd.qxcalculator.model.parsedObjects.Profession;
import com.wbmd.qxcalculator.model.parsedObjects.Specialty;
import com.wbmd.qxcalculator.model.parsedObjects.User;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class APIResponse {
    public static final int API_RESPONSE_CODE_INVALID_AUTH_KEY = 401;
    public static final int API_RESPONSE_CODE_NOT_SET = 0;
    public static final int HTTP_RESPONSE_CODE_BAD_REQUEST = 400;
    public static final int HTTP_RESPONSE_CODE_INVALID_AUTH_KEY = 401;
    public static final int HTTP_RESPONSE_CODE_NOT_SET = 0;
    public static final int HTTP_RESPONSE_CODE_NO_INTERNET = -1;
    public static final int HTTP_RESPONSE_CODE_TIMEOUT = 258;
    public static final int RESPONSE_CODE_HTTP_RESPONSE_INVALID = 3;
    public static final int RESPONSE_CODE_NO_CONTENT = 204;
    public static final int RESPONSE_CODE_PROCESSING_ERROR = 999;
    public static final int RESPONSE_CODE_SUCCESS = 200;
    public static final int RESPONSE_CODE_TIMEOUT = 408;
    public boolean accountExists;
    public boolean accountsErrorOnRefreshAll = false;
    public Boolean bannerAdsEnabled;
    public List<ContentItem> contentItems = new ArrayList(ConnectionResult.DRIVE_EXTERNAL_STORAGE_REQUIRED);
    public File downloadedFile;
    public ArrayList<QxError> errors = new ArrayList<>();
    public ArrayList<Favorite> favorites = new ArrayList<>();
    public boolean forceSuccess;
    public String httpResponseMessage;
    public int httpStatusCode = 0;
    public boolean isNonCalledResponse;
    public ArrayList<Location> locations = new ArrayList<>();
    public ArrayList<Profession> professions = new ArrayList<>();
    public ArrayList<Recent> recents = new ArrayList<>();
    public String responseMessage;
    public ArrayList<Specialty> specialties = new ArrayList<>();
    public User user;

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x000b, code lost:
        r0 = r2.errors;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean success() {
        /*
            r2 = this;
            boolean r0 = r2.forceSuccess
            if (r0 != 0) goto L_0x0018
            int r0 = r2.httpStatusCode
            int r0 = r0 / 100
            r1 = 2
            if (r0 != r1) goto L_0x0016
            java.util.ArrayList<com.wbmd.qxcalculator.model.QxError> r0 = r2.errors
            if (r0 == 0) goto L_0x0018
            boolean r0 = r0.isEmpty()
            if (r0 == 0) goto L_0x0016
            goto L_0x0018
        L_0x0016:
            r0 = 0
            goto L_0x0019
        L_0x0018:
            r0 = 1
        L_0x0019:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wbmd.qxcalculator.model.api.APIResponse.success():boolean");
    }

    public boolean invalidAuthKey() {
        ArrayList<QxError> arrayList = this.errors;
        if (arrayList == null) {
            return false;
        }
        Iterator<QxError> it = arrayList.iterator();
        while (it.hasNext()) {
            if (it.next().isInvalidAuthKeyError()) {
                return true;
            }
        }
        return false;
    }

    public boolean invalidDeviceId() {
        ArrayList<QxError> arrayList = this.errors;
        if (arrayList == null) {
            return false;
        }
        Iterator<QxError> it = arrayList.iterator();
        while (it.hasNext()) {
            if (it.next().isInvalidDeviceIdError()) {
                return true;
            }
        }
        return false;
    }
}
