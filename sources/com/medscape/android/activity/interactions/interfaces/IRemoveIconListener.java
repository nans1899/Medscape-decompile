package com.medscape.android.activity.interactions.interfaces;

import com.medscape.android.db.model.Drug;

public interface IRemoveIconListener {
    void onRemoveDrugIconClicked(Drug drug, int i);

    void onRemoveListIconClicked(int i, int i2);
}
