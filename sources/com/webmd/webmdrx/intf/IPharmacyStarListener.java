package com.webmd.webmdrx.intf;

import android.widget.CheckBox;
import com.webmd.webmdrx.models.Pharmacy;

public interface IPharmacyStarListener {
    void onRemovePharmacy(Pharmacy pharmacy, CheckBox checkBox);

    void onSavePharmacy(Pharmacy pharmacy, CheckBox checkBox);
}
