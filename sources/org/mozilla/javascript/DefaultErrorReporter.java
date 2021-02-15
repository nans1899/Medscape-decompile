package org.mozilla.javascript;

class DefaultErrorReporter implements ErrorReporter {
    static final DefaultErrorReporter instance = new DefaultErrorReporter();
    private ErrorReporter chainedReporter;
    private boolean forEval;

    private DefaultErrorReporter() {
    }

    static ErrorReporter forEval(ErrorReporter errorReporter) {
        DefaultErrorReporter defaultErrorReporter = new DefaultErrorReporter();
        defaultErrorReporter.forEval = true;
        defaultErrorReporter.chainedReporter = errorReporter;
        return defaultErrorReporter;
    }

    public void warning(String str, String str2, int i, String str3, int i2) {
        ErrorReporter errorReporter = this.chainedReporter;
        if (errorReporter != null) {
            errorReporter.warning(str, str2, i, str3, i2);
        }
    }

    public void error(String str, String str2, int i, String str3, int i2) {
        String str4;
        if (this.forEval) {
            if (str.startsWith("TypeError: ")) {
                str = str.substring(11);
                str4 = "TypeError";
            } else {
                str4 = "SyntaxError";
            }
            throw ScriptRuntime.constructError(str4, str, str2, i, str3, i2);
        }
        ErrorReporter errorReporter = this.chainedReporter;
        if (errorReporter != null) {
            errorReporter.error(str, str2, i, str3, i2);
            return;
        }
        throw runtimeError(str, str2, i, str3, i2);
    }

    public EvaluatorException runtimeError(String str, String str2, int i, String str3, int i2) {
        ErrorReporter errorReporter = this.chainedReporter;
        if (errorReporter != null) {
            return errorReporter.runtimeError(str, str2, i, str3, i2);
        }
        return new EvaluatorException(str, str2, i, str3, i2);
    }
}
