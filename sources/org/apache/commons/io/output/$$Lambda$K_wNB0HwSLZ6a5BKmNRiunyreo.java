package org.apache.commons.io.output;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import org.apache.commons.io.output.AbstractByteArrayOutputStream;

/* renamed from: org.apache.commons.io.output.-$$Lambda$K_wNB-0HwSLZ6a5BKmNRiunyreo  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$K_wNB0HwSLZ6a5BKmNRiunyreo implements AbstractByteArrayOutputStream.InputStreamConstructor {
    public static final /* synthetic */ $$Lambda$K_wNB0HwSLZ6a5BKmNRiunyreo INSTANCE = new $$Lambda$K_wNB0HwSLZ6a5BKmNRiunyreo();

    private /* synthetic */ $$Lambda$K_wNB0HwSLZ6a5BKmNRiunyreo() {
    }

    public final InputStream construct(byte[] bArr, int i, int i2) {
        return new ByteArrayInputStream(bArr, i, i2);
    }
}
