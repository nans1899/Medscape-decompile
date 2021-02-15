package org.apache.commons.io.input;

import java.util.Collections;
import java.util.Set;

public class CharacterSetFilterReader extends AbstractCharacterFilterReader {
    private static final Set<Integer> EMPTY_SET = Collections.emptySet();
    private final Set<Integer> skipSet;

    /* JADX WARNING: type inference failed for: r2v0, types: [java.util.Set, java.util.Set<java.lang.Integer>] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public CharacterSetFilterReader(java.io.Reader r1, java.util.Set<java.lang.Integer> r2) {
        /*
            r0 = this;
            r0.<init>(r1)
            if (r2 != 0) goto L_0x0008
            java.util.Set<java.lang.Integer> r1 = EMPTY_SET
            goto L_0x000c
        L_0x0008:
            java.util.Set r1 = java.util.Collections.unmodifiableSet(r2)
        L_0x000c:
            r0.skipSet = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.io.input.CharacterSetFilterReader.<init>(java.io.Reader, java.util.Set):void");
    }

    /* access modifiers changed from: protected */
    public boolean filter(int i) {
        return this.skipSet.contains(Integer.valueOf(i));
    }
}
