package com.wbmd.qxcalculator;

import com.wbmd.qxcalculator.model.db.DBContentItem;

public interface QxSearchEventsCallback {
    void onClickSearchButton();

    void onClickSearchResult(DBContentItem dBContentItem, int i);

    void onSearchResultsLoaded();
}
