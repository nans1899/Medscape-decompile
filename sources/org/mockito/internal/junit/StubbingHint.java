package org.mockito.internal.junit;

import org.apache.commons.io.IOUtils;

class StubbingHint {
    private final StringBuilder hint;

    StubbingHint(String str) {
        StringBuilder sb = new StringBuilder("[MockitoHint] ");
        sb.append(str);
        sb.append(" (see javadoc for MockitoHint):");
        this.hint = sb;
    }

    /* access modifiers changed from: package-private */
    public void appendLine(Object... objArr) {
        this.hint.append("\n[MockitoHint] ");
        for (Object append : objArr) {
            this.hint.append(append);
        }
    }

    public String toString() {
        return this.hint.toString() + IOUtils.LINE_SEPARATOR_UNIX;
    }
}
