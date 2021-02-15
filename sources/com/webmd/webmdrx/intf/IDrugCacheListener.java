package com.webmd.webmdrx.intf;

public interface IDrugCacheListener {
    void onDrugCacheFailure();

    void onDrugCacheSuccess();
}
