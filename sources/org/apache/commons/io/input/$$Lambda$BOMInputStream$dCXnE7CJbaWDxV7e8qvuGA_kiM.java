package org.apache.commons.io.input;

import java.util.Comparator;
import org.apache.commons.io.ByteOrderMark;

/* renamed from: org.apache.commons.io.input.-$$Lambda$BOMInputStream$dCXnE7C-JbaWDxV7e8qvuGA_kiM  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$BOMInputStream$dCXnE7CJbaWDxV7e8qvuGA_kiM implements Comparator {
    public static final /* synthetic */ $$Lambda$BOMInputStream$dCXnE7CJbaWDxV7e8qvuGA_kiM INSTANCE = new $$Lambda$BOMInputStream$dCXnE7CJbaWDxV7e8qvuGA_kiM();

    private /* synthetic */ $$Lambda$BOMInputStream$dCXnE7CJbaWDxV7e8qvuGA_kiM() {
    }

    public final int compare(Object obj, Object obj2) {
        return Integer.compare(((ByteOrderMark) obj2).length(), ((ByteOrderMark) obj).length());
    }
}
