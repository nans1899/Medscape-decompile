package com.wbmd.omniture;

import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\t\bf\u0018\u00002\u00020\u0001R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0012\u0010\u0006\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\u0005R\u0012\u0010\b\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\u0005R\u0012\u0010\n\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\u0005R\u0012\u0010\f\u001a\u00020\rX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0012\u0010\u0010\u001a\u00020\rX¦\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u000fR\u0012\u0010\u0012\u001a\u00020\rX¦\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u000fR\u0012\u0010\u0014\u001a\u00020\rX¦\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u000f¨\u0006\u0016"}, d2 = {"Lcom/wbmd/omniture/IOmnitureAppSettings;", "", "appId", "", "getAppId", "()Ljava/lang/String;", "baseUrl", "getBaseUrl", "defaultActionName", "getDefaultActionName", "defaultSection", "getDefaultSection", "enableTrailingForwardSlash", "", "getEnableTrailingForwardSlash", "()Z", "normalizePageNames", "getNormalizePageNames", "pageNameToLowerCase", "getPageNameToLowerCase", "replaceSpaceWithDash", "getReplaceSpaceWithDash", "wbmd.omniture_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: IOmnitureAppSettings.kt */
public interface IOmnitureAppSettings {
    String getAppId();

    String getBaseUrl();

    String getDefaultActionName();

    String getDefaultSection();

    boolean getEnableTrailingForwardSlash();

    boolean getNormalizePageNames();

    boolean getPageNameToLowerCase();

    boolean getReplaceSpaceWithDash();
}
