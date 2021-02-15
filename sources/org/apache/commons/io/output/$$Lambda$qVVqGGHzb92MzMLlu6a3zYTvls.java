package org.apache.commons.io.output;

import java.io.InputStream;
import org.apache.commons.io.input.UnsynchronizedByteArrayInputStream;
import org.apache.commons.io.output.AbstractByteArrayOutputStream;

/* renamed from: org.apache.commons.io.output.-$$Lambda$qVVqGGHzb92MzMLl-u6a3zYTvls  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$qVVqGGHzb92MzMLlu6a3zYTvls implements AbstractByteArrayOutputStream.InputStreamConstructor {
    public static final /* synthetic */ $$Lambda$qVVqGGHzb92MzMLlu6a3zYTvls INSTANCE = new $$Lambda$qVVqGGHzb92MzMLlu6a3zYTvls();

    private /* synthetic */ $$Lambda$qVVqGGHzb92MzMLlu6a3zYTvls() {
    }

    public final InputStream construct(byte[] bArr, int i, int i2) {
        return new UnsynchronizedByteArrayInputStream(bArr, i, i2);
    }
}
