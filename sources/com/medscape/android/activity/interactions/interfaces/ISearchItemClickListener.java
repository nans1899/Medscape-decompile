package com.medscape.android.activity.interactions.interfaces;

import com.medscape.android.db.model.Drug;

public interface ISearchItemClickListener {
    void onSearchItemClicked(Drug drug);
}
