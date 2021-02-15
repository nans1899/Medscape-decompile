package com.wbmd.wbmdcommons.utils;

import java.util.Map;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.NativeObject;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;

public class JavascriptExecutor {
    public static Map execute(String str) {
        Context enter = Context.enter();
        enter.setOptimizationLevel(-1);
        return (NativeObject) enter.evaluateString(enter.initStandardObjects(), str, "JavaScript", 1, (Object) null);
    }

    public static Map execute(String str, String str2) {
        Context enter = Context.enter();
        enter.setOptimizationLevel(-1);
        ScriptableObject initStandardObjects = enter.initStandardObjects();
        enter.evaluateString(initStandardObjects, str, "JavaScript", 1, (Object) null);
        return (Map) initStandardObjects.get(str2, (Scriptable) initStandardObjects);
    }
}
