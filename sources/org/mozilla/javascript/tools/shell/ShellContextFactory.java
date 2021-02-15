package org.mozilla.javascript.tools.shell;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.ContextFactory;
import org.mozilla.javascript.ErrorReporter;

public class ShellContextFactory extends ContextFactory {
    private boolean allowReservedKeywords = true;
    private String characterEncoding;
    private ErrorReporter errorReporter;
    private boolean generatingDebug;
    private int languageVersion = 170;
    private int optimizationLevel;
    private boolean strictMode;
    private boolean warningAsError;

    /* access modifiers changed from: protected */
    public boolean hasFeature(Context context, int i) {
        if (i == 3) {
            return this.allowReservedKeywords;
        }
        switch (i) {
            case 8:
            case 9:
            case 11:
                return this.strictMode;
            case 10:
                return this.generatingDebug;
            case 12:
                return this.warningAsError;
            default:
                return super.hasFeature(context, i);
        }
    }

    /* access modifiers changed from: protected */
    public void onContextCreated(Context context) {
        context.setLanguageVersion(this.languageVersion);
        context.setOptimizationLevel(this.optimizationLevel);
        ErrorReporter errorReporter2 = this.errorReporter;
        if (errorReporter2 != null) {
            context.setErrorReporter(errorReporter2);
        }
        context.setGeneratingDebug(this.generatingDebug);
        super.onContextCreated(context);
    }

    public void setStrictMode(boolean z) {
        checkNotSealed();
        this.strictMode = z;
    }

    public void setWarningAsError(boolean z) {
        checkNotSealed();
        this.warningAsError = z;
    }

    public void setLanguageVersion(int i) {
        Context.checkLanguageVersion(i);
        checkNotSealed();
        this.languageVersion = i;
    }

    public void setOptimizationLevel(int i) {
        Context.checkOptimizationLevel(i);
        checkNotSealed();
        this.optimizationLevel = i;
    }

    public void setErrorReporter(ErrorReporter errorReporter2) {
        if (errorReporter2 != null) {
            this.errorReporter = errorReporter2;
            return;
        }
        throw new IllegalArgumentException();
    }

    public void setGeneratingDebug(boolean z) {
        this.generatingDebug = z;
    }

    public String getCharacterEncoding() {
        return this.characterEncoding;
    }

    public void setCharacterEncoding(String str) {
        this.characterEncoding = str;
    }

    public void setAllowReservedKeywords(boolean z) {
        this.allowReservedKeywords = z;
    }
}
