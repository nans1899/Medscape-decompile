package com.google.android.gms.internal.ads;

import android.media.MediaFormat;
import android.os.Parcel;
import android.os.Parcelable;
import com.webmd.wbmdcmepulse.models.parsers.articles.GraphicParser;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzho implements Parcelable {
    public static final Parcelable.Creator<zzho> CREATOR = new zzhr();
    public final int height;
    public final int width;
    private final String zzagx;
    public final int zzagy;
    public final String zzagz;
    private final zzmc zzaha;
    private final String zzahb;
    public final String zzahc;
    public final int zzahd;
    public final List<byte[]> zzahe;
    public final zzjn zzahf;
    public final float zzahg;
    public final int zzahh;
    public final float zzahi;
    private final int zzahj;
    private final byte[] zzahk;
    private final zzpr zzahl;
    public final int zzahm;
    public final int zzahn;
    public final int zzaho;
    private final int zzahp;
    private final int zzahq;
    public final long zzahr;
    public final int zzahs;
    public final String zzaht;
    private final int zzahu;
    private int zzahv;

    public static zzho zza(String str, String str2, String str3, int i, int i2, int i3, int i4, float f, List<byte[]> list, int i5, float f2, byte[] bArr, int i6, zzpr zzpr, zzjn zzjn) {
        return new zzho(str, (String) null, str2, (String) null, -1, i2, i3, i4, -1.0f, i5, f2, bArr, i6, zzpr, -1, -1, -1, -1, -1, 0, (String) null, -1, Long.MAX_VALUE, list, zzjn, (zzmc) null);
    }

    public final int describeContents() {
        return 0;
    }

    public static zzho zza(String str, String str2, String str3, int i, int i2, int i3, int i4, List<byte[]> list, zzjn zzjn, int i5, String str4) {
        return zza(str, str2, (String) null, -1, -1, i3, i4, -1, (List<byte[]>) null, zzjn, 0, str4);
    }

    public static zzho zza(String str, String str2, String str3, int i, int i2, int i3, int i4, int i5, List<byte[]> list, zzjn zzjn, int i6, String str4) {
        return new zzho(str, (String) null, str2, (String) null, -1, i2, -1, -1, -1.0f, -1, -1.0f, (byte[]) null, -1, (zzpr) null, i3, i4, i5, -1, -1, i6, str4, -1, Long.MAX_VALUE, list, zzjn, (zzmc) null);
    }

    public static zzho zza(String str, String str2, String str3, int i, int i2, String str4, zzjn zzjn) {
        return zza(str, str2, (String) null, -1, i2, str4, -1, zzjn, Long.MAX_VALUE, Collections.emptyList());
    }

    public static zzho zza(String str, String str2, String str3, int i, int i2, String str4, int i3, zzjn zzjn, long j, List<byte[]> list) {
        return new zzho(str, (String) null, str2, (String) null, -1, -1, -1, -1, -1.0f, -1, -1.0f, (byte[]) null, -1, (zzpr) null, -1, -1, -1, -1, -1, i2, str4, -1, j, list, zzjn, (zzmc) null);
    }

    public static zzho zza(String str, String str2, String str3, int i, List<byte[]> list, String str4, zzjn zzjn) {
        return new zzho(str, (String) null, str2, (String) null, -1, -1, -1, -1, -1.0f, -1, -1.0f, (byte[]) null, -1, (zzpr) null, -1, -1, -1, -1, -1, 0, str4, -1, Long.MAX_VALUE, list, zzjn, (zzmc) null);
    }

    public static zzho zza(String str, String str2, long j) {
        return new zzho((String) null, (String) null, str2, (String) null, -1, -1, -1, -1, -1.0f, -1, -1.0f, (byte[]) null, -1, (zzpr) null, -1, -1, -1, -1, -1, 0, (String) null, -1, Long.MAX_VALUE, (List<byte[]>) null, (zzjn) null, (zzmc) null);
    }

    public static zzho zza(String str, String str2, String str3, int i, zzjn zzjn) {
        return new zzho(str, (String) null, str2, (String) null, -1, -1, -1, -1, -1.0f, -1, -1.0f, (byte[]) null, -1, (zzpr) null, -1, -1, -1, -1, -1, 0, (String) null, -1, Long.MAX_VALUE, (List<byte[]>) null, zzjn, (zzmc) null);
    }

    private zzho(String str, String str2, String str3, String str4, int i, int i2, int i3, int i4, float f, int i5, float f2, byte[] bArr, int i6, zzpr zzpr, int i7, int i8, int i9, int i10, int i11, int i12, String str5, int i13, long j, List<byte[]> list, zzjn zzjn, zzmc zzmc) {
        this.zzagx = str;
        this.zzahb = str2;
        this.zzahc = str3;
        this.zzagz = str4;
        this.zzagy = i;
        this.zzahd = i2;
        this.width = i3;
        this.height = i4;
        this.zzahg = f;
        this.zzahh = i5;
        this.zzahi = f2;
        this.zzahk = bArr;
        this.zzahj = i6;
        this.zzahl = zzpr;
        this.zzahm = i7;
        this.zzahn = i8;
        this.zzaho = i9;
        this.zzahp = i10;
        this.zzahq = i11;
        this.zzahs = i12;
        this.zzaht = str5;
        this.zzahu = i13;
        this.zzahr = j;
        this.zzahe = list == null ? Collections.emptyList() : list;
        this.zzahf = zzjn;
        this.zzaha = zzmc;
    }

    zzho(Parcel parcel) {
        this.zzagx = parcel.readString();
        this.zzahb = parcel.readString();
        this.zzahc = parcel.readString();
        this.zzagz = parcel.readString();
        this.zzagy = parcel.readInt();
        this.zzahd = parcel.readInt();
        this.width = parcel.readInt();
        this.height = parcel.readInt();
        this.zzahg = parcel.readFloat();
        this.zzahh = parcel.readInt();
        this.zzahi = parcel.readFloat();
        this.zzahk = parcel.readInt() != 0 ? parcel.createByteArray() : null;
        this.zzahj = parcel.readInt();
        this.zzahl = (zzpr) parcel.readParcelable(zzpr.class.getClassLoader());
        this.zzahm = parcel.readInt();
        this.zzahn = parcel.readInt();
        this.zzaho = parcel.readInt();
        this.zzahp = parcel.readInt();
        this.zzahq = parcel.readInt();
        this.zzahs = parcel.readInt();
        this.zzaht = parcel.readString();
        this.zzahu = parcel.readInt();
        this.zzahr = parcel.readLong();
        int readInt = parcel.readInt();
        this.zzahe = new ArrayList(readInt);
        for (int i = 0; i < readInt; i++) {
            this.zzahe.add(parcel.createByteArray());
        }
        this.zzahf = (zzjn) parcel.readParcelable(zzjn.class.getClassLoader());
        this.zzaha = (zzmc) parcel.readParcelable(zzmc.class.getClassLoader());
    }

    public final zzho zzv(int i) {
        String str = this.zzagx;
        return new zzho(str, this.zzahb, this.zzahc, this.zzagz, this.zzagy, i, this.width, this.height, this.zzahg, this.zzahh, this.zzahi, this.zzahk, this.zzahj, this.zzahl, this.zzahm, this.zzahn, this.zzaho, this.zzahp, this.zzahq, this.zzahs, this.zzaht, this.zzahu, this.zzahr, this.zzahe, this.zzahf, this.zzaha);
    }

    public final zzho zzds(long j) {
        return new zzho(this.zzagx, this.zzahb, this.zzahc, this.zzagz, this.zzagy, this.zzahd, this.width, this.height, this.zzahg, this.zzahh, this.zzahi, this.zzahk, this.zzahj, this.zzahl, this.zzahm, this.zzahn, this.zzaho, this.zzahp, this.zzahq, this.zzahs, this.zzaht, this.zzahu, j, this.zzahe, this.zzahf, this.zzaha);
    }

    public final zzho zzb(int i, int i2) {
        String str = this.zzagx;
        return new zzho(str, this.zzahb, this.zzahc, this.zzagz, this.zzagy, this.zzahd, this.width, this.height, this.zzahg, this.zzahh, this.zzahi, this.zzahk, this.zzahj, this.zzahl, this.zzahm, this.zzahn, this.zzaho, i, i2, this.zzahs, this.zzaht, this.zzahu, this.zzahr, this.zzahe, this.zzahf, this.zzaha);
    }

    public final zzho zza(zzjn zzjn) {
        String str = this.zzagx;
        return new zzho(str, this.zzahb, this.zzahc, this.zzagz, this.zzagy, this.zzahd, this.width, this.height, this.zzahg, this.zzahh, this.zzahi, this.zzahk, this.zzahj, this.zzahl, this.zzahm, this.zzahn, this.zzaho, this.zzahp, this.zzahq, this.zzahs, this.zzaht, this.zzahu, this.zzahr, this.zzahe, zzjn, this.zzaha);
    }

    public final zzho zza(zzmc zzmc) {
        String str = this.zzagx;
        return new zzho(str, this.zzahb, this.zzahc, this.zzagz, this.zzagy, this.zzahd, this.width, this.height, this.zzahg, this.zzahh, this.zzahi, this.zzahk, this.zzahj, this.zzahl, this.zzahm, this.zzahn, this.zzaho, this.zzahp, this.zzahq, this.zzahs, this.zzaht, this.zzahu, this.zzahr, this.zzahe, this.zzahf, zzmc);
    }

    public final int zzez() {
        int i;
        int i2 = this.width;
        if (i2 == -1 || (i = this.height) == -1) {
            return -1;
        }
        return i2 * i;
    }

    public final MediaFormat zzfa() {
        MediaFormat mediaFormat = new MediaFormat();
        mediaFormat.setString("mime", this.zzahc);
        String str = this.zzaht;
        if (str != null) {
            mediaFormat.setString("language", str);
        }
        zza(mediaFormat, "max-input-size", this.zzahd);
        zza(mediaFormat, GraphicParser.XML_ATTRIBUTE_VALUE_WIDTH, this.width);
        zza(mediaFormat, GraphicParser.XML_ATRIBUTE_VALUE_HEIGHT, this.height);
        float f = this.zzahg;
        if (f != -1.0f) {
            mediaFormat.setFloat("frame-rate", f);
        }
        zza(mediaFormat, "rotation-degrees", this.zzahh);
        zza(mediaFormat, "channel-count", this.zzahm);
        zza(mediaFormat, "sample-rate", this.zzahn);
        zza(mediaFormat, "encoder-delay", this.zzahp);
        zza(mediaFormat, "encoder-padding", this.zzahq);
        for (int i = 0; i < this.zzahe.size(); i++) {
            StringBuilder sb = new StringBuilder(15);
            sb.append("csd-");
            sb.append(i);
            mediaFormat.setByteBuffer(sb.toString(), ByteBuffer.wrap(this.zzahe.get(i)));
        }
        zzpr zzpr = this.zzahl;
        if (zzpr != null) {
            zza(mediaFormat, "color-transfer", zzpr.zzarx);
            zza(mediaFormat, "color-standard", zzpr.zzarw);
            zza(mediaFormat, "color-range", zzpr.zzary);
            byte[] bArr = zzpr.zzbkr;
            if (bArr != null) {
                mediaFormat.setByteBuffer("hdr-static-info", ByteBuffer.wrap(bArr));
            }
        }
        return mediaFormat;
    }

    public final String toString() {
        String str = this.zzagx;
        String str2 = this.zzahb;
        String str3 = this.zzahc;
        int i = this.zzagy;
        String str4 = this.zzaht;
        int i2 = this.width;
        int i3 = this.height;
        float f = this.zzahg;
        int i4 = this.zzahm;
        int i5 = this.zzahn;
        StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 100 + String.valueOf(str2).length() + String.valueOf(str3).length() + String.valueOf(str4).length());
        sb.append("Format(");
        sb.append(str);
        sb.append(", ");
        sb.append(str2);
        sb.append(", ");
        sb.append(str3);
        sb.append(", ");
        sb.append(i);
        sb.append(", ");
        sb.append(str4);
        sb.append(", [");
        sb.append(i2);
        sb.append(", ");
        sb.append(i3);
        sb.append(", ");
        sb.append(f);
        sb.append("], [");
        sb.append(i4);
        sb.append(", ");
        sb.append(i5);
        sb.append("])");
        return sb.toString();
    }

    public final int hashCode() {
        if (this.zzahv == 0) {
            String str = this.zzagx;
            int i = 0;
            int hashCode = ((str == null ? 0 : str.hashCode()) + 527) * 31;
            String str2 = this.zzahb;
            int hashCode2 = (hashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
            String str3 = this.zzahc;
            int hashCode3 = (hashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
            String str4 = this.zzagz;
            int hashCode4 = (((((((((((hashCode3 + (str4 == null ? 0 : str4.hashCode())) * 31) + this.zzagy) * 31) + this.width) * 31) + this.height) * 31) + this.zzahm) * 31) + this.zzahn) * 31;
            String str5 = this.zzaht;
            int hashCode5 = (((hashCode4 + (str5 == null ? 0 : str5.hashCode())) * 31) + this.zzahu) * 31;
            zzjn zzjn = this.zzahf;
            int hashCode6 = (hashCode5 + (zzjn == null ? 0 : zzjn.hashCode())) * 31;
            zzmc zzmc = this.zzaha;
            if (zzmc != null) {
                i = zzmc.hashCode();
            }
            this.zzahv = hashCode6 + i;
        }
        return this.zzahv;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            zzho zzho = (zzho) obj;
            if (this.zzagy == zzho.zzagy && this.zzahd == zzho.zzahd && this.width == zzho.width && this.height == zzho.height && this.zzahg == zzho.zzahg && this.zzahh == zzho.zzahh && this.zzahi == zzho.zzahi && this.zzahj == zzho.zzahj && this.zzahm == zzho.zzahm && this.zzahn == zzho.zzahn && this.zzaho == zzho.zzaho && this.zzahp == zzho.zzahp && this.zzahq == zzho.zzahq && this.zzahr == zzho.zzahr && this.zzahs == zzho.zzahs && zzpq.zza(this.zzagx, zzho.zzagx) && zzpq.zza(this.zzaht, zzho.zzaht) && this.zzahu == zzho.zzahu && zzpq.zza(this.zzahb, zzho.zzahb) && zzpq.zza(this.zzahc, zzho.zzahc) && zzpq.zza(this.zzagz, zzho.zzagz) && zzpq.zza(this.zzahf, zzho.zzahf) && zzpq.zza(this.zzaha, zzho.zzaha) && zzpq.zza(this.zzahl, zzho.zzahl) && Arrays.equals(this.zzahk, zzho.zzahk) && this.zzahe.size() == zzho.zzahe.size()) {
                for (int i = 0; i < this.zzahe.size(); i++) {
                    if (!Arrays.equals(this.zzahe.get(i), zzho.zzahe.get(i))) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }

    private static void zza(MediaFormat mediaFormat, String str, int i) {
        if (i != -1) {
            mediaFormat.setInteger(str, i);
        }
    }

    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.zzagx);
        parcel.writeString(this.zzahb);
        parcel.writeString(this.zzahc);
        parcel.writeString(this.zzagz);
        parcel.writeInt(this.zzagy);
        parcel.writeInt(this.zzahd);
        parcel.writeInt(this.width);
        parcel.writeInt(this.height);
        parcel.writeFloat(this.zzahg);
        parcel.writeInt(this.zzahh);
        parcel.writeFloat(this.zzahi);
        parcel.writeInt(this.zzahk != null ? 1 : 0);
        byte[] bArr = this.zzahk;
        if (bArr != null) {
            parcel.writeByteArray(bArr);
        }
        parcel.writeInt(this.zzahj);
        parcel.writeParcelable(this.zzahl, i);
        parcel.writeInt(this.zzahm);
        parcel.writeInt(this.zzahn);
        parcel.writeInt(this.zzaho);
        parcel.writeInt(this.zzahp);
        parcel.writeInt(this.zzahq);
        parcel.writeInt(this.zzahs);
        parcel.writeString(this.zzaht);
        parcel.writeInt(this.zzahu);
        parcel.writeLong(this.zzahr);
        int size = this.zzahe.size();
        parcel.writeInt(size);
        for (int i2 = 0; i2 < size; i2++) {
            parcel.writeByteArray(this.zzahe.get(i2));
        }
        parcel.writeParcelable(this.zzahf, 0);
        parcel.writeParcelable(this.zzaha, 0);
    }
}
