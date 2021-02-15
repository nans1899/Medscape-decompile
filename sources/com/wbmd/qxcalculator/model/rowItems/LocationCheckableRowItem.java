package com.wbmd.qxcalculator.model.rowItems;

import com.wbmd.qxcalculator.model.parsedObjects.Location;

public class LocationCheckableRowItem extends CheckableRowItem {
    public Location location;

    public LocationCheckableRowItem(Location location2) {
        super(location2.name);
        this.location = location2;
    }
}
