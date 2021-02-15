package com.wbmd.qxcalculator.model.calcList;

public class CalcListUtils {
    public static int getTabIndexForTabId(String str) {
        if (str == null || str.equals("Grouped")) {
            return 0;
        }
        if (str.equals("Recents")) {
            return 1;
        }
        if (str.equals("Favorites")) {
            return 2;
        }
        return 0;
    }

    public static CalcListType getCalcListTypeForTabPosition(int i) {
        if (i == 0) {
            return CalcListType.GROUPED;
        }
        if (i == 1) {
            return CalcListType.RECENTS;
        }
        if (i == 2) {
            return CalcListType.FAVORITES;
        }
        return CalcListType.GROUPED;
    }
}
