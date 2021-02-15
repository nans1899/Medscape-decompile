package com.wbmd.qxcalculator.model.rowItems;

import com.wbmd.qxcalculator.model.parsedObjects.Specialty;

public class SpecialtyCheckableRowItem extends CheckableRowItem {
    public Specialty specialty;

    public SpecialtyCheckableRowItem(Specialty specialty2) {
        super(specialty2.name);
        this.specialty = specialty2;
    }
}
