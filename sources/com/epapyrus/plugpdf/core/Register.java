package com.epapyrus.plugpdf.core;

public class Register {
    private static SearchInfo mSearchInfo;

    public static SearchInfo getSearchInfo() {
        return mSearchInfo;
    }

    public static void setSearchInfo(SearchInfo searchInfo) {
        mSearchInfo = searchInfo;
    }
}
