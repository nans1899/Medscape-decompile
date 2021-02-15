package com.medscape.android.reference.interfaces;

public interface AccordionAnimation {
    boolean collapseCurrentlyExpandedGroup();

    int getCurrentExpandedGroupPosition();

    void resetCurrentExpandedGroupPosition();
}
