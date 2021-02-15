package com.wbmd.wbmdcommons.utils;

import android.app.Activity;
import android.view.inputmethod.InputMethodManager;

public class KeyboardUtil {
    public static void showKeyboard(Activity activity) {
        Object systemService;
        if (activity != null && (systemService = activity.getSystemService("input_method")) != null && (systemService instanceof InputMethodManager)) {
            ((InputMethodManager) systemService).showSoftInput(activity.getCurrentFocus(), 2);
        }
    }

    public static void hideKeyboard(Activity activity) {
        Object systemService;
        if (activity != null && (systemService = activity.getSystemService("input_method")) != null && (systemService instanceof InputMethodManager)) {
            InputMethodManager inputMethodManager = (InputMethodManager) systemService;
            if (activity.getCurrentFocus() != null && activity.getCurrentFocus().getWindowToken() != null) {
                inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
            }
        }
    }
}
