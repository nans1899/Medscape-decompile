package com.wbmd.qxcalculator.util.jsevaluator;

import android.webkit.JavascriptInterface;

public class QxJavaScriptInterface {
    QxJsEvaluator jsEvaluator;

    public QxJavaScriptInterface(QxJsEvaluator qxJsEvaluator) {
        this.jsEvaluator = qxJsEvaluator;
    }

    @JavascriptInterface
    public void returnResultToJava(String str) {
        this.jsEvaluator.jsCallFinished(str);
    }
}
