package com.google.android.gms.internal.ads;

import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-ads-lite@@19.4.0 */
public class zzekj extends IOException {
    private zzelj zzikc = null;

    public zzekj(String str) {
        super(str);
    }

    public final zzekj zzl(zzelj zzelj) {
        this.zzikc = zzelj;
        return this;
    }

    static zzekj zzbgx() {
        return new zzekj("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
    }

    static zzekj zzbgy() {
        return new zzekj("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
    }

    static zzekj zzbgz() {
        return new zzekj("CodedInputStream encountered a malformed varint.");
    }

    static zzekj zzbha() {
        return new zzekj("Protocol message contained an invalid tag (zero).");
    }

    static zzekj zzbhb() {
        return new zzekj("Protocol message end-group tag did not match expected tag.");
    }

    static zzekm zzbhc() {
        return new zzekm("Protocol message tag had invalid wire type.");
    }

    static zzekj zzbhd() {
        return new zzekj("Protocol message was too large.  May be malicious.  Use CodedInputStream.setSizeLimit() to increase the size limit.");
    }

    static zzekj zzbhe() {
        return new zzekj("Failed to parse the message.");
    }

    static zzekj zzbhf() {
        return new zzekj("Protocol message had invalid UTF-8.");
    }
}
