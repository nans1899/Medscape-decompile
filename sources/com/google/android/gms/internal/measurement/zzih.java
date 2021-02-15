package com.google.android.gms.internal.measurement;

import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-measurement-base@@17.6.0 */
public class zzih extends IOException {
    private zzjh zza = null;

    public zzih(String str) {
        super(str);
    }

    static zzih zza() {
        return new zzih("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
    }

    static zzih zzb() {
        return new zzih("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
    }

    static zzih zzc() {
        return new zzih("CodedInputStream encountered a malformed varint.");
    }

    static zzih zzd() {
        return new zzih("Protocol message contained an invalid tag (zero).");
    }

    static zzih zze() {
        return new zzih("Protocol message end-group tag did not match expected tag.");
    }

    static zzik zzf() {
        return new zzik("Protocol message tag had invalid wire type.");
    }

    static zzih zzg() {
        return new zzih("Failed to parse the message.");
    }

    static zzih zzh() {
        return new zzih("Protocol message had invalid UTF-8.");
    }
}
