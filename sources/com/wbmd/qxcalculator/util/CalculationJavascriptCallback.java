package com.wbmd.qxcalculator.util;

import com.wbmd.qxcalculator.model.contentItems.calculator.ErrorCheck;
import com.wbmd.qxcalculator.model.contentItems.calculator.ExtraSection;
import com.wbmd.qxcalculator.model.contentItems.calculator.Result;

public class CalculationJavascriptCallback {
    public ErrorCheckJavascriptCompletionHandler errorCheckJavascriptCompletionHandler;
    public ExtraSectionJavascriptCompletionHandler extraSectionJavascriptCompletionHandler;
    public ResultJavascriptCompletionHandler resultJavascriptCompletionHandler;

    public interface ErrorCheckJavascriptCompletionHandler {
        void onErrorCheckJavascriptCompleted(ErrorCheck errorCheck, boolean z);
    }

    public interface ExtraSectionJavascriptCompletionHandler {
        void onExtraSectionJavascriptCompleted(ExtraSection extraSection);
    }

    public interface LinkedCalculatorConvertResultJavascriptCompletionHandler {
        void onResultJavascriptCompleted(boolean z);
    }

    public interface ResultJavascriptCompletionHandler {
        void onResultJavascriptCompleted(Result result);
    }
}
