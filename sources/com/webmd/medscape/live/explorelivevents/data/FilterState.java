package com.webmd.medscape.live.explorelivevents.data;

import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\b\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u000e\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u0002\n\u0000j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lcom/webmd/medscape/live/explorelivevents/data/FilterState;", "", "value", "", "(Ljava/lang/String;II)V", "ON", "OFF", "wbmd.explorelivevents_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: FilterState.kt */
public enum FilterState {
    ON(0),
    OFF(1);
    
    private int value;

    private FilterState(int i) {
        this.value = i;
    }
}
