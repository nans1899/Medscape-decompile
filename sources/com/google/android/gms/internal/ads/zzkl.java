package com.google.android.gms.internal.ads;

import android.util.SparseArray;
import com.comscore.streaming.AdvertisementType;
import com.google.android.gms.internal.ads.zzjn;
import com.google.firebase.FirebaseError;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Locale;
import java.util.UUID;
import net.bytebuddy.jar.asm.Opcodes;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
public final class zzkl implements zzjv {
    private static final zzka zzapj = new zzko();
    private static final byte[] zzapk = {49, 10, 48, 48, 58, 48, 48, 58, 48, 48, 44, 48, 48, 48, 32, 45, 45, 62, 32, 48, 48, 58, 48, 48, 58, 48, 48, 44, 48, 48, 48, 10};
    private static final byte[] zzapl = {32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32, 32};
    /* access modifiers changed from: private */
    public static final UUID zzapm = new UUID(72057594037932032L, -9223371306706625679L);
    private long zzaih;
    private final zzks zzapd;
    private final zzkj zzapn;
    private final SparseArray<zzkq> zzapo;
    private final boolean zzapp;
    private final zzpk zzapq;
    private final zzpk zzapr;
    private final zzpk zzaps;
    private final zzpk zzapt;
    private final zzpk zzapu;
    private final zzpk zzapv;
    private final zzpk zzapw;
    private final zzpk zzapx;
    private final zzpk zzapy;
    private ByteBuffer zzapz;
    private long zzaqa;
    private long zzaqb;
    private long zzaqc;
    private long zzaqd;
    private zzkq zzaqe;
    private boolean zzaqf;
    private int zzaqg;
    private long zzaqh;
    private boolean zzaqi;
    private long zzaqj;
    private long zzaqk;
    private long zzaql;
    private zzpe zzaqm;
    private zzpe zzaqn;
    private boolean zzaqo;
    private int zzaqp;
    private long zzaqq;
    private long zzaqr;
    private int zzaqs;
    private int zzaqt;
    private int[] zzaqu;
    private int zzaqv;
    private int zzaqw;
    private int zzaqx;
    private int zzaqy;
    private boolean zzaqz;
    private boolean zzara;
    private boolean zzarb;
    private boolean zzarc;
    private byte zzard;
    private int zzare;
    private int zzarf;
    private int zzarg;
    private boolean zzarh;
    private boolean zzari;
    private zzjx zzarj;

    public zzkl() {
        this(0);
    }

    static int zzan(int i) {
        switch (i) {
            case 131:
            case 136:
            case 155:
            case 159:
            case 176:
            case 179:
            case Opcodes.INVOKEDYNAMIC /*186*/:
            case 215:
            case AdvertisementType.BRANDED_ON_DEMAND_PRE_ROLL:
            case 241:
            case 251:
            case 16980:
            case 17029:
            case 17143:
            case 18401:
            case 18408:
            case 20529:
            case 20530:
            case 21420:
            case 21432:
            case 21680:
            case 21682:
            case 21690:
            case 21930:
            case 21945:
            case 21946:
            case 21947:
            case 21948:
            case 21949:
            case 22186:
            case 22203:
            case 25188:
            case 2352003:
            case 2807729:
                return 2;
            case 134:
            case FirebaseError.ERROR_WEAK_PASSWORD /*17026*/:
            case 2274716:
                return 3;
            case 160:
            case 174:
            case 183:
            case 187:
            case 224:
            case 225:
            case 18407:
            case 19899:
            case 20532:
            case 20533:
            case 21936:
            case 21968:
            case 25152:
            case 28032:
            case 30320:
            case 290298740:
            case 357149030:
            case 374648427:
            case 408125543:
            case 440786851:
            case 475249515:
            case 524531317:
                return 1;
            case 161:
            case 163:
            case 16981:
            case 18402:
            case 21419:
            case 25506:
            case 30322:
                return 4;
            case 181:
            case 17545:
            case 21969:
            case 21970:
            case 21971:
            case 21972:
            case 21973:
            case 21974:
            case 21975:
            case 21976:
            case 21977:
            case 21978:
                return 5;
            default:
                return 0;
        }
    }

    static boolean zzao(int i) {
        return i == 357149030 || i == 524531317 || i == 475249515 || i == 374648427;
    }

    public final void release() {
    }

    private zzkl(int i) {
        this(new zzki(), 0);
    }

    private zzkl(zzkj zzkj, int i) {
        this.zzaqb = -1;
        this.zzaqc = -9223372036854775807L;
        this.zzaqd = -9223372036854775807L;
        this.zzaih = -9223372036854775807L;
        this.zzaqj = -1;
        this.zzaqk = -1;
        this.zzaql = -9223372036854775807L;
        this.zzapn = zzkj;
        zzkj.zza(new zzkn(this, (zzko) null));
        this.zzapp = true;
        this.zzapd = new zzks();
        this.zzapo = new SparseArray<>();
        this.zzaps = new zzpk(4);
        this.zzapt = new zzpk(ByteBuffer.allocate(4).putInt(-1).array());
        this.zzapu = new zzpk(4);
        this.zzapq = new zzpk(zzpf.zzbjr);
        this.zzapr = new zzpk(4);
        this.zzapv = new zzpk();
        this.zzapw = new zzpk();
        this.zzapx = new zzpk(8);
        this.zzapy = new zzpk();
    }

    public final boolean zza(zzjy zzjy) throws IOException, InterruptedException {
        return new zzkp().zza(zzjy);
    }

    public final void zza(zzjx zzjx) {
        this.zzarj = zzjx;
    }

    public final void zzc(long j, long j2) {
        this.zzaql = -9223372036854775807L;
        this.zzaqp = 0;
        this.zzapn.reset();
        this.zzapd.reset();
        zzgu();
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x0039 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0005 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final int zza(com.google.android.gms.internal.ads.zzjy r9, com.google.android.gms.internal.ads.zzkb r10) throws java.io.IOException, java.lang.InterruptedException {
        /*
            r8 = this;
            r0 = 0
            r8.zzarh = r0
            r1 = 1
            r2 = 1
        L_0x0005:
            if (r2 == 0) goto L_0x003a
            boolean r3 = r8.zzarh
            if (r3 != 0) goto L_0x003a
            com.google.android.gms.internal.ads.zzkj r2 = r8.zzapn
            boolean r2 = r2.zzb(r9)
            if (r2 == 0) goto L_0x0005
            long r3 = r9.getPosition()
            boolean r5 = r8.zzaqi
            if (r5 == 0) goto L_0x0025
            r8.zzaqk = r3
            long r3 = r8.zzaqj
            r10.position = r3
            r8.zzaqi = r0
        L_0x0023:
            r3 = 1
            goto L_0x0037
        L_0x0025:
            boolean r3 = r8.zzaqf
            if (r3 == 0) goto L_0x0036
            long r3 = r8.zzaqk
            r5 = -1
            int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r7 == 0) goto L_0x0036
            r10.position = r3
            r8.zzaqk = r5
            goto L_0x0023
        L_0x0036:
            r3 = 0
        L_0x0037:
            if (r3 == 0) goto L_0x0005
            return r1
        L_0x003a:
            if (r2 == 0) goto L_0x003d
            return r0
        L_0x003d:
            r9 = -1
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzkl.zza(com.google.android.gms.internal.ads.zzjy, com.google.android.gms.internal.ads.zzkb):int");
    }

    /* access modifiers changed from: package-private */
    public final void zzd(int i, long j, long j2) throws zzhv {
        if (i == 160) {
            this.zzari = false;
        } else if (i == 174) {
            this.zzaqe = new zzkq((zzko) null);
        } else if (i == 187) {
            this.zzaqo = false;
        } else if (i == 19899) {
            this.zzaqg = -1;
            this.zzaqh = -1;
        } else if (i == 20533) {
            this.zzaqe.zzaro = true;
        } else if (i == 21968) {
            this.zzaqe.zzarv = true;
        } else if (i == 408125543) {
            long j3 = this.zzaqb;
            if (j3 == -1 || j3 == j) {
                this.zzaqb = j;
                this.zzaqa = j2;
                return;
            }
            throw new zzhv("Multiple Segment elements not supported");
        } else if (i == 475249515) {
            this.zzaqm = new zzpe();
            this.zzaqn = new zzpe();
        } else if (i != 524531317 || this.zzaqf) {
        } else {
            if (!this.zzapp || this.zzaqj == -1) {
                this.zzarj.zza(new zzkd(this.zzaih));
                this.zzaqf = true;
                return;
            }
            this.zzaqi = true;
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzap(int i) throws zzhv {
        zzke zzke;
        zzpe zzpe;
        zzpe zzpe2;
        int i2;
        int i3 = 0;
        if (i != 160) {
            if (i == 174) {
                String str = this.zzaqe.zzarm;
                if ("V_VP8".equals(str) || "V_VP9".equals(str) || "V_MPEG2".equals(str) || "V_MPEG4/ISO/SP".equals(str) || "V_MPEG4/ISO/ASP".equals(str) || "V_MPEG4/ISO/AP".equals(str) || "V_MPEG4/ISO/AVC".equals(str) || "V_MPEGH/ISO/HEVC".equals(str) || "V_MS/VFW/FOURCC".equals(str) || "V_THEORA".equals(str) || "A_OPUS".equals(str) || "A_VORBIS".equals(str) || "A_AAC".equals(str) || "A_MPEG/L2".equals(str) || "A_MPEG/L3".equals(str) || "A_AC3".equals(str) || "A_EAC3".equals(str) || "A_TRUEHD".equals(str) || "A_DTS".equals(str) || "A_DTS/EXPRESS".equals(str) || "A_DTS/LOSSLESS".equals(str) || "A_FLAC".equals(str) || "A_MS/ACM".equals(str) || "A_PCM/INT/LIT".equals(str) || "S_TEXT/UTF8".equals(str) || "S_VOBSUB".equals(str) || "S_HDMV/PGS".equals(str) || "S_DVBSUB".equals(str)) {
                    i3 = 1;
                }
                if (i3 != 0) {
                    zzkq zzkq = this.zzaqe;
                    zzkq.zza(this.zzarj, zzkq.number);
                    this.zzapo.put(this.zzaqe.number, this.zzaqe);
                }
                this.zzaqe = null;
            } else if (i == 19899) {
                int i4 = this.zzaqg;
                if (i4 != -1) {
                    long j = this.zzaqh;
                    if (j != -1) {
                        if (i4 == 475249515) {
                            this.zzaqj = j;
                            return;
                        }
                        return;
                    }
                }
                throw new zzhv("Mandatory element SeekID or SeekPosition not found");
            } else if (i != 25152) {
                if (i != 28032) {
                    if (i == 357149030) {
                        if (this.zzaqc == -9223372036854775807L) {
                            this.zzaqc = 1000000;
                        }
                        long j2 = this.zzaqd;
                        if (j2 != -9223372036854775807L) {
                            this.zzaih = zzea(j2);
                        }
                    } else if (i != 374648427) {
                        if (i == 475249515 && !this.zzaqf) {
                            zzjx zzjx = this.zzarj;
                            if (this.zzaqb == -1 || this.zzaih == -9223372036854775807L || (zzpe = this.zzaqm) == null || zzpe.size() == 0 || (zzpe2 = this.zzaqn) == null || zzpe2.size() != this.zzaqm.size()) {
                                this.zzaqm = null;
                                this.zzaqn = null;
                                zzke = new zzkd(this.zzaih);
                            } else {
                                int size = this.zzaqm.size();
                                int[] iArr = new int[size];
                                long[] jArr = new long[size];
                                long[] jArr2 = new long[size];
                                long[] jArr3 = new long[size];
                                for (int i5 = 0; i5 < size; i5++) {
                                    jArr3[i5] = this.zzaqm.get(i5);
                                    jArr[i5] = this.zzaqb + this.zzaqn.get(i5);
                                }
                                while (true) {
                                    i2 = size - 1;
                                    if (i3 >= i2) {
                                        break;
                                    }
                                    int i6 = i3 + 1;
                                    iArr[i3] = (int) (jArr[i6] - jArr[i3]);
                                    jArr2[i3] = jArr3[i6] - jArr3[i3];
                                    i3 = i6;
                                }
                                iArr[i2] = (int) ((this.zzaqb + this.zzaqa) - jArr[i2]);
                                jArr2[i2] = this.zzaih - jArr3[i2];
                                this.zzaqm = null;
                                this.zzaqn = null;
                                zzke = new zzjt(iArr, jArr, jArr2, jArr3);
                            }
                            zzjx.zza(zzke);
                            this.zzaqf = true;
                        }
                    } else if (this.zzapo.size() != 0) {
                        this.zzarj.zzgr();
                    } else {
                        throw new zzhv("No valid tracks were found");
                    }
                } else if (this.zzaqe.zzaro && this.zzaqe.zzarp != null) {
                    throw new zzhv("Combining encryption and compression is not supported");
                }
            } else if (!this.zzaqe.zzaro) {
            } else {
                if (this.zzaqe.zzarq != null) {
                    this.zzaqe.zzahf = new zzjn(new zzjn.zza(zzha.UUID_NIL, "video/webm", this.zzaqe.zzarq.zzapa));
                    return;
                }
                throw new zzhv("Encrypted Track found but ContentEncKeyID was not found");
            }
        } else if (this.zzaqp == 2) {
            if (!this.zzari) {
                this.zzaqx |= 1;
            }
            zza(this.zzapo.get(this.zzaqv), this.zzaqq);
            this.zzaqp = 0;
        }
    }

    /* access modifiers changed from: package-private */
    public final void zzc(int i, long j) throws zzhv {
        if (i != 20529) {
            if (i != 20530) {
                boolean z = false;
                switch (i) {
                    case 131:
                        this.zzaqe.type = (int) j;
                        return;
                    case 136:
                        zzkq zzkq = this.zzaqe;
                        if (j == 1) {
                            z = true;
                        }
                        zzkq.zzaso = z;
                        return;
                    case 155:
                        this.zzaqr = zzea(j);
                        return;
                    case 159:
                        this.zzaqe.zzahm = (int) j;
                        return;
                    case 176:
                        this.zzaqe.width = (int) j;
                        return;
                    case 179:
                        this.zzaqm.add(zzea(j));
                        return;
                    case Opcodes.INVOKEDYNAMIC /*186*/:
                        this.zzaqe.height = (int) j;
                        return;
                    case 215:
                        this.zzaqe.number = (int) j;
                        return;
                    case AdvertisementType.BRANDED_ON_DEMAND_PRE_ROLL:
                        this.zzaql = zzea(j);
                        return;
                    case 241:
                        if (!this.zzaqo) {
                            this.zzaqn.add(j);
                            this.zzaqo = true;
                            return;
                        }
                        return;
                    case 251:
                        this.zzari = true;
                        return;
                    case 16980:
                        if (j != 3) {
                            StringBuilder sb = new StringBuilder(50);
                            sb.append("ContentCompAlgo ");
                            sb.append(j);
                            sb.append(" not supported");
                            throw new zzhv(sb.toString());
                        }
                        return;
                    case 17029:
                        if (j < 1 || j > 2) {
                            StringBuilder sb2 = new StringBuilder(53);
                            sb2.append("DocTypeReadVersion ");
                            sb2.append(j);
                            sb2.append(" not supported");
                            throw new zzhv(sb2.toString());
                        }
                        return;
                    case 17143:
                        if (j != 1) {
                            StringBuilder sb3 = new StringBuilder(50);
                            sb3.append("EBMLReadVersion ");
                            sb3.append(j);
                            sb3.append(" not supported");
                            throw new zzhv(sb3.toString());
                        }
                        return;
                    case 18401:
                        if (j != 5) {
                            StringBuilder sb4 = new StringBuilder(49);
                            sb4.append("ContentEncAlgo ");
                            sb4.append(j);
                            sb4.append(" not supported");
                            throw new zzhv(sb4.toString());
                        }
                        return;
                    case 18408:
                        if (j != 1) {
                            StringBuilder sb5 = new StringBuilder(56);
                            sb5.append("AESSettingsCipherMode ");
                            sb5.append(j);
                            sb5.append(" not supported");
                            throw new zzhv(sb5.toString());
                        }
                        return;
                    case 21420:
                        this.zzaqh = j + this.zzaqb;
                        return;
                    case 21432:
                        int i2 = (int) j;
                        if (i2 == 0) {
                            this.zzaqe.zzahj = 0;
                            return;
                        } else if (i2 == 1) {
                            this.zzaqe.zzahj = 2;
                            return;
                        } else if (i2 == 3) {
                            this.zzaqe.zzahj = 1;
                            return;
                        } else if (i2 == 15) {
                            this.zzaqe.zzahj = 3;
                            return;
                        } else {
                            return;
                        }
                    case 21680:
                        this.zzaqe.zzars = (int) j;
                        return;
                    case 21682:
                        this.zzaqe.zzaru = (int) j;
                        return;
                    case 21690:
                        this.zzaqe.zzart = (int) j;
                        return;
                    case 21930:
                        zzkq zzkq2 = this.zzaqe;
                        if (j == 1) {
                            z = true;
                        }
                        zzkq2.zzasp = z;
                        return;
                    case 22186:
                        this.zzaqe.zzasm = j;
                        return;
                    case 22203:
                        this.zzaqe.zzasn = j;
                        return;
                    case 25188:
                        this.zzaqe.zzasl = (int) j;
                        return;
                    case 2352003:
                        this.zzaqe.zzarn = (int) j;
                        return;
                    case 2807729:
                        this.zzaqc = j;
                        return;
                    default:
                        switch (i) {
                            case 21945:
                                int i3 = (int) j;
                                if (i3 == 1) {
                                    this.zzaqe.zzary = 2;
                                    return;
                                } else if (i3 == 2) {
                                    this.zzaqe.zzary = 1;
                                    return;
                                } else {
                                    return;
                                }
                            case 21946:
                                int i4 = (int) j;
                                if (i4 != 1) {
                                    if (i4 == 16) {
                                        this.zzaqe.zzarx = 6;
                                        return;
                                    } else if (i4 == 18) {
                                        this.zzaqe.zzarx = 7;
                                        return;
                                    } else if (!(i4 == 6 || i4 == 7)) {
                                        return;
                                    }
                                }
                                this.zzaqe.zzarx = 3;
                                return;
                            case 21947:
                                this.zzaqe.zzarv = true;
                                int i5 = (int) j;
                                if (i5 == 1) {
                                    this.zzaqe.zzarw = 1;
                                    return;
                                } else if (i5 == 9) {
                                    this.zzaqe.zzarw = 6;
                                    return;
                                } else if (i5 == 4 || i5 == 5 || i5 == 6 || i5 == 7) {
                                    this.zzaqe.zzarw = 2;
                                    return;
                                } else {
                                    return;
                                }
                            case 21948:
                                this.zzaqe.zzarz = (int) j;
                                return;
                            case 21949:
                                this.zzaqe.zzasa = (int) j;
                                return;
                            default:
                                return;
                        }
                }
            } else if (j != 1) {
                StringBuilder sb6 = new StringBuilder(55);
                sb6.append("ContentEncodingScope ");
                sb6.append(j);
                sb6.append(" not supported");
                throw new zzhv(sb6.toString());
            }
        } else if (j != 0) {
            StringBuilder sb7 = new StringBuilder(55);
            sb7.append("ContentEncodingOrder ");
            sb7.append(j);
            sb7.append(" not supported");
            throw new zzhv(sb7.toString());
        }
    }

    /* access modifiers changed from: package-private */
    public final void zza(int i, double d) {
        if (i == 181) {
            this.zzaqe.zzahn = (int) d;
        } else if (i != 17545) {
            switch (i) {
                case 21969:
                    this.zzaqe.zzasb = (float) d;
                    return;
                case 21970:
                    this.zzaqe.zzasc = (float) d;
                    return;
                case 21971:
                    this.zzaqe.zzasd = (float) d;
                    return;
                case 21972:
                    this.zzaqe.zzase = (float) d;
                    return;
                case 21973:
                    this.zzaqe.zzasf = (float) d;
                    return;
                case 21974:
                    this.zzaqe.zzasg = (float) d;
                    return;
                case 21975:
                    this.zzaqe.zzash = (float) d;
                    return;
                case 21976:
                    this.zzaqe.zzasi = (float) d;
                    return;
                case 21977:
                    this.zzaqe.zzasj = (float) d;
                    return;
                case 21978:
                    this.zzaqe.zzask = (float) d;
                    return;
                default:
                    return;
            }
        } else {
            this.zzaqd = (long) d;
        }
    }

    /* access modifiers changed from: package-private */
    public final void zza(int i, String str) throws zzhv {
        if (i == 134) {
            this.zzaqe.zzarm = str;
        } else if (i != 17026) {
            if (i == 2274716) {
                String unused = this.zzaqe.zzaht = str;
            }
        } else if (!"webm".equals(str) && !"matroska".equals(str)) {
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 22);
            sb.append("DocType ");
            sb.append(str);
            sb.append(" not supported");
            throw new zzhv(sb.toString());
        }
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:75:0x01ff, code lost:
        throw new com.google.android.gms.internal.ads.zzhv("EBML lacing sample size out of range.");
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(int r22, int r23, com.google.android.gms.internal.ads.zzjy r24) throws java.io.IOException, java.lang.InterruptedException {
        /*
            r21 = this;
            r0 = r21
            r1 = r22
            r2 = r23
            r3 = r24
            r4 = 161(0xa1, float:2.26E-43)
            r5 = 163(0xa3, float:2.28E-43)
            r6 = 0
            r7 = 1
            if (r1 == r4) goto L_0x0097
            if (r1 == r5) goto L_0x0097
            r4 = 16981(0x4255, float:2.3795E-41)
            if (r1 == r4) goto L_0x0089
            r4 = 18402(0x47e2, float:2.5787E-41)
            if (r1 == r4) goto L_0x007a
            r4 = 21419(0x53ab, float:3.0014E-41)
            if (r1 == r4) goto L_0x005b
            r4 = 25506(0x63a2, float:3.5742E-41)
            if (r1 == r4) goto L_0x004d
            r4 = 30322(0x7672, float:4.249E-41)
            if (r1 != r4) goto L_0x0034
            com.google.android.gms.internal.ads.zzkq r1 = r0.zzaqe
            byte[] r4 = new byte[r2]
            r1.zzahk = r4
            com.google.android.gms.internal.ads.zzkq r1 = r0.zzaqe
            byte[] r1 = r1.zzahk
            r3.readFully(r1, r6, r2)
            return
        L_0x0034:
            com.google.android.gms.internal.ads.zzhv r2 = new com.google.android.gms.internal.ads.zzhv
            r3 = 26
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>(r3)
            java.lang.String r3 = "Unexpected id: "
            r4.append(r3)
            r4.append(r1)
            java.lang.String r1 = r4.toString()
            r2.<init>(r1)
            throw r2
        L_0x004d:
            com.google.android.gms.internal.ads.zzkq r1 = r0.zzaqe
            byte[] r4 = new byte[r2]
            r1.zzarr = r4
            com.google.android.gms.internal.ads.zzkq r1 = r0.zzaqe
            byte[] r1 = r1.zzarr
            r3.readFully(r1, r6, r2)
            return
        L_0x005b:
            com.google.android.gms.internal.ads.zzpk r1 = r0.zzapu
            byte[] r1 = r1.data
            java.util.Arrays.fill(r1, r6)
            com.google.android.gms.internal.ads.zzpk r1 = r0.zzapu
            byte[] r1 = r1.data
            int r4 = 4 - r2
            r3.readFully(r1, r4, r2)
            com.google.android.gms.internal.ads.zzpk r1 = r0.zzapu
            r1.zzbo(r6)
            com.google.android.gms.internal.ads.zzpk r1 = r0.zzapu
            long r1 = r1.zzjc()
            int r2 = (int) r1
            r0.zzaqg = r2
            return
        L_0x007a:
            byte[] r1 = new byte[r2]
            r3.readFully(r1, r6, r2)
            com.google.android.gms.internal.ads.zzkq r2 = r0.zzaqe
            com.google.android.gms.internal.ads.zzkf r3 = new com.google.android.gms.internal.ads.zzkf
            r3.<init>(r7, r1)
            r2.zzarq = r3
            return
        L_0x0089:
            com.google.android.gms.internal.ads.zzkq r1 = r0.zzaqe
            byte[] r4 = new byte[r2]
            r1.zzarp = r4
            com.google.android.gms.internal.ads.zzkq r1 = r0.zzaqe
            byte[] r1 = r1.zzarp
            r3.readFully(r1, r6, r2)
            return
        L_0x0097:
            int r4 = r0.zzaqp
            r8 = 8
            if (r4 != 0) goto L_0x00bc
            com.google.android.gms.internal.ads.zzks r4 = r0.zzapd
            long r9 = r4.zza(r3, r6, r7, r8)
            int r4 = (int) r9
            r0.zzaqv = r4
            com.google.android.gms.internal.ads.zzks r4 = r0.zzapd
            int r4 = r4.zzgw()
            r0.zzaqw = r4
            r9 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            r0.zzaqr = r9
            r0.zzaqp = r7
            com.google.android.gms.internal.ads.zzpk r4 = r0.zzaps
            r4.reset()
        L_0x00bc:
            android.util.SparseArray<com.google.android.gms.internal.ads.zzkq> r4 = r0.zzapo
            int r9 = r0.zzaqv
            java.lang.Object r4 = r4.get(r9)
            com.google.android.gms.internal.ads.zzkq r4 = (com.google.android.gms.internal.ads.zzkq) r4
            if (r4 != 0) goto L_0x00d2
            int r1 = r0.zzaqw
            int r1 = r2 - r1
            r3.zzai(r1)
            r0.zzaqp = r6
            return
        L_0x00d2:
            int r9 = r0.zzaqp
            if (r9 != r7) goto L_0x0285
            r9 = 3
            r0.zzb(r3, r9)
            com.google.android.gms.internal.ads.zzpk r10 = r0.zzaps
            byte[] r10 = r10.data
            r11 = 2
            byte r10 = r10[r11]
            r10 = r10 & 6
            int r10 = r10 >> r7
            r12 = 255(0xff, float:3.57E-43)
            if (r10 != 0) goto L_0x00fa
            r0.zzaqt = r7
            int[] r10 = r0.zzaqu
            int[] r10 = zza((int[]) r10, (int) r7)
            r0.zzaqu = r10
            int r13 = r0.zzaqw
            int r2 = r2 - r13
            int r2 = r2 - r9
            r10[r6] = r2
            goto L_0x0213
        L_0x00fa:
            if (r1 != r5) goto L_0x027d
            r13 = 4
            r0.zzb(r3, r13)
            com.google.android.gms.internal.ads.zzpk r14 = r0.zzaps
            byte[] r14 = r14.data
            byte r14 = r14[r9]
            r14 = r14 & r12
            int r14 = r14 + r7
            r0.zzaqt = r14
            int[] r15 = r0.zzaqu
            int[] r14 = zza((int[]) r15, (int) r14)
            r0.zzaqu = r14
            if (r10 != r11) goto L_0x0120
            int r9 = r0.zzaqw
            int r2 = r2 - r9
            int r2 = r2 - r13
            int r9 = r0.zzaqt
            int r2 = r2 / r9
            java.util.Arrays.fill(r14, r6, r9, r2)
            goto L_0x0213
        L_0x0120:
            if (r10 != r7) goto L_0x0157
            r9 = 0
            r10 = 0
        L_0x0124:
            int r14 = r0.zzaqt
            int r15 = r14 + -1
            if (r9 >= r15) goto L_0x014b
            int[] r14 = r0.zzaqu
            r14[r9] = r6
        L_0x012e:
            int r13 = r13 + r7
            r0.zzb(r3, r13)
            com.google.android.gms.internal.ads.zzpk r14 = r0.zzaps
            byte[] r14 = r14.data
            int r15 = r13 + -1
            byte r14 = r14[r15]
            r14 = r14 & r12
            int[] r15 = r0.zzaqu
            r16 = r15[r9]
            int r16 = r16 + r14
            r15[r9] = r16
            if (r14 == r12) goto L_0x012e
            r14 = r15[r9]
            int r10 = r10 + r14
            int r9 = r9 + 1
            goto L_0x0124
        L_0x014b:
            int[] r9 = r0.zzaqu
            int r14 = r14 - r7
            int r15 = r0.zzaqw
            int r2 = r2 - r15
            int r2 = r2 - r13
            int r2 = r2 - r10
            r9[r14] = r2
            goto L_0x0213
        L_0x0157:
            if (r10 != r9) goto L_0x0264
            r9 = 0
            r10 = 0
        L_0x015b:
            int r14 = r0.zzaqt
            int r15 = r14 + -1
            if (r9 >= r15) goto L_0x0208
            int[] r14 = r0.zzaqu
            r14[r9] = r6
            int r13 = r13 + 1
            r0.zzb(r3, r13)
            com.google.android.gms.internal.ads.zzpk r14 = r0.zzaps
            byte[] r14 = r14.data
            int r15 = r13 + -1
            byte r14 = r14[r15]
            if (r14 == 0) goto L_0x0200
            r16 = 0
            r14 = 0
        L_0x0177:
            if (r14 >= r8) goto L_0x01cb
            int r18 = 7 - r14
            int r5 = r7 << r18
            com.google.android.gms.internal.ads.zzpk r11 = r0.zzaps
            byte[] r11 = r11.data
            byte r11 = r11[r15]
            r11 = r11 & r5
            if (r11 == 0) goto L_0x01c1
            int r13 = r13 + r14
            r0.zzb(r3, r13)
            com.google.android.gms.internal.ads.zzpk r11 = r0.zzaps
            byte[] r11 = r11.data
            int r16 = r15 + 1
            byte r11 = r11[r15]
            r11 = r11 & r12
            int r5 = ~r5
            r5 = r5 & r11
            long r6 = (long) r5
            r5 = r16
            r16 = r6
        L_0x019a:
            if (r5 >= r13) goto L_0x01b3
            long r6 = r16 << r8
            com.google.android.gms.internal.ads.zzpk r15 = r0.zzaps
            byte[] r15 = r15.data
            int r16 = r5 + 1
            byte r5 = r15[r5]
            r5 = r5 & r12
            long r11 = (long) r5
            long r5 = r6 | r11
            r12 = 255(0xff, float:3.57E-43)
            r19 = r5
            r5 = r16
            r16 = r19
            goto L_0x019a
        L_0x01b3:
            if (r9 <= 0) goto L_0x01cb
            int r14 = r14 * 7
            int r14 = r14 + 6
            r5 = 1
            long r11 = r5 << r14
            long r11 = r11 - r5
            long r16 = r16 - r11
            goto L_0x01cb
        L_0x01c1:
            int r14 = r14 + 1
            r5 = 163(0xa3, float:2.28E-43)
            r6 = 0
            r7 = 1
            r11 = 2
            r12 = 255(0xff, float:3.57E-43)
            goto L_0x0177
        L_0x01cb:
            r5 = r16
            r11 = -2147483648(0xffffffff80000000, double:NaN)
            int r7 = (r5 > r11 ? 1 : (r5 == r11 ? 0 : -1))
            if (r7 < 0) goto L_0x01f8
            r11 = 2147483647(0x7fffffff, double:1.060997895E-314)
            int r7 = (r5 > r11 ? 1 : (r5 == r11 ? 0 : -1))
            if (r7 > 0) goto L_0x01f8
            int r6 = (int) r5
            int[] r5 = r0.zzaqu
            if (r9 != 0) goto L_0x01e1
            goto L_0x01e6
        L_0x01e1:
            int r7 = r9 + -1
            r7 = r5[r7]
            int r6 = r6 + r7
        L_0x01e6:
            r5[r9] = r6
            int[] r5 = r0.zzaqu
            r5 = r5[r9]
            int r10 = r10 + r5
            int r9 = r9 + 1
            r5 = 163(0xa3, float:2.28E-43)
            r6 = 0
            r7 = 1
            r11 = 2
            r12 = 255(0xff, float:3.57E-43)
            goto L_0x015b
        L_0x01f8:
            com.google.android.gms.internal.ads.zzhv r1 = new com.google.android.gms.internal.ads.zzhv
            java.lang.String r2 = "EBML lacing sample size out of range."
            r1.<init>(r2)
            throw r1
        L_0x0200:
            com.google.android.gms.internal.ads.zzhv r1 = new com.google.android.gms.internal.ads.zzhv
            java.lang.String r2 = "No valid varint length mask found"
            r1.<init>(r2)
            throw r1
        L_0x0208:
            int[] r5 = r0.zzaqu
            r6 = 1
            int r14 = r14 - r6
            int r6 = r0.zzaqw
            int r2 = r2 - r6
            int r2 = r2 - r13
            int r2 = r2 - r10
            r5[r14] = r2
        L_0x0213:
            com.google.android.gms.internal.ads.zzpk r2 = r0.zzaps
            byte[] r2 = r2.data
            r5 = 0
            byte r2 = r2[r5]
            int r2 = r2 << r8
            com.google.android.gms.internal.ads.zzpk r5 = r0.zzaps
            byte[] r5 = r5.data
            r6 = 1
            byte r5 = r5[r6]
            r6 = 255(0xff, float:3.57E-43)
            r5 = r5 & r6
            r2 = r2 | r5
            long r5 = r0.zzaql
            long r9 = (long) r2
            long r9 = r0.zzea(r9)
            long r5 = r5 + r9
            r0.zzaqq = r5
            com.google.android.gms.internal.ads.zzpk r2 = r0.zzaps
            byte[] r2 = r2.data
            r5 = 2
            byte r2 = r2[r5]
            r2 = r2 & r8
            if (r2 != r8) goto L_0x023c
            r2 = 1
            goto L_0x023d
        L_0x023c:
            r2 = 0
        L_0x023d:
            int r6 = r4.type
            if (r6 == r5) goto L_0x0253
            r6 = 163(0xa3, float:2.28E-43)
            if (r1 != r6) goto L_0x0251
            com.google.android.gms.internal.ads.zzpk r6 = r0.zzaps
            byte[] r6 = r6.data
            byte r6 = r6[r5]
            r5 = 128(0x80, float:1.794E-43)
            r6 = r6 & r5
            if (r6 != r5) goto L_0x0251
            goto L_0x0253
        L_0x0251:
            r5 = 0
            goto L_0x0254
        L_0x0253:
            r5 = 1
        L_0x0254:
            if (r2 == 0) goto L_0x0259
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            goto L_0x025a
        L_0x0259:
            r2 = 0
        L_0x025a:
            r2 = r2 | r5
            r0.zzaqx = r2
            r2 = 2
            r0.zzaqp = r2
            r2 = 0
            r0.zzaqs = r2
            goto L_0x0285
        L_0x0264:
            com.google.android.gms.internal.ads.zzhv r1 = new com.google.android.gms.internal.ads.zzhv
            r2 = 36
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>(r2)
            java.lang.String r2 = "Unexpected lacing value: "
            r3.append(r2)
            r3.append(r10)
            java.lang.String r2 = r3.toString()
            r1.<init>(r2)
            throw r1
        L_0x027d:
            com.google.android.gms.internal.ads.zzhv r1 = new com.google.android.gms.internal.ads.zzhv
            java.lang.String r2 = "Lacing only supported in SimpleBlocks."
            r1.<init>(r2)
            throw r1
        L_0x0285:
            r2 = 163(0xa3, float:2.28E-43)
            if (r1 != r2) goto L_0x02b0
        L_0x0289:
            int r1 = r0.zzaqs
            int r2 = r0.zzaqt
            if (r1 >= r2) goto L_0x02ac
            int[] r2 = r0.zzaqu
            r1 = r2[r1]
            r0.zza((com.google.android.gms.internal.ads.zzjy) r3, (com.google.android.gms.internal.ads.zzkq) r4, (int) r1)
            long r1 = r0.zzaqq
            int r5 = r0.zzaqs
            int r6 = r4.zzarn
            int r5 = r5 * r6
            int r5 = r5 / 1000
            long r5 = (long) r5
            long r1 = r1 + r5
            r0.zza((com.google.android.gms.internal.ads.zzkq) r4, (long) r1)
            int r1 = r0.zzaqs
            r2 = 1
            int r1 = r1 + r2
            r0.zzaqs = r1
            goto L_0x0289
        L_0x02ac:
            r1 = 0
            r0.zzaqp = r1
            return
        L_0x02b0:
            r1 = 0
            int[] r2 = r0.zzaqu
            r1 = r2[r1]
            r0.zza((com.google.android.gms.internal.ads.zzjy) r3, (com.google.android.gms.internal.ads.zzkq) r4, (int) r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzkl.zza(int, int, com.google.android.gms.internal.ads.zzjy):void");
    }

    private final void zza(zzkq zzkq, long j) {
        byte[] bArr;
        if ("S_TEXT/UTF8".equals(zzkq.zzarm)) {
            byte[] bArr2 = this.zzapw.data;
            long j2 = this.zzaqr;
            if (j2 == -9223372036854775807L) {
                bArr = zzapl;
            } else {
                int i = (int) (j2 / 3600000000L);
                long j3 = j2 - (((long) i) * 3600000000L);
                int i2 = (int) (j3 / 60000000);
                long j4 = j3 - ((long) (60000000 * i2));
                int i3 = (int) (j4 / 1000000);
                bArr = zzpq.zzbk(String.format(Locale.US, "%02d:%02d:%02d,%03d", new Object[]{Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf((int) ((j4 - ((long) (1000000 * i3))) / 1000))}));
            }
            System.arraycopy(bArr, 0, bArr2, 19, 12);
            zzkg zzkg = zzkq.zzasq;
            zzpk zzpk = this.zzapw;
            zzkg.zza(zzpk, zzpk.limit());
            this.zzarg += this.zzapw.limit();
        }
        zzkq.zzasq.zza(j, this.zzaqx, this.zzarg, 0, zzkq.zzarq);
        this.zzarh = true;
        zzgu();
    }

    private final void zzgu() {
        this.zzaqy = 0;
        this.zzarg = 0;
        this.zzarf = 0;
        this.zzaqz = false;
        this.zzara = false;
        this.zzarc = false;
        this.zzare = 0;
        this.zzard = 0;
        this.zzarb = false;
        this.zzapv.reset();
    }

    private final void zzb(zzjy zzjy, int i) throws IOException, InterruptedException {
        if (this.zzaps.limit() < i) {
            if (this.zzaps.capacity() < i) {
                zzpk zzpk = this.zzaps;
                zzpk.zzc(Arrays.copyOf(zzpk.data, Math.max(this.zzaps.data.length << 1, i)), this.zzaps.limit());
            }
            zzjy.readFully(this.zzaps.data, this.zzaps.limit(), i - this.zzaps.limit());
            this.zzaps.zzbn(i);
        }
    }

    private final void zza(zzjy zzjy, zzkq zzkq, int i) throws IOException, InterruptedException {
        int i2;
        if ("S_TEXT/UTF8".equals(zzkq.zzarm)) {
            int length = zzapk.length + i;
            if (this.zzapw.capacity() < length) {
                this.zzapw.data = Arrays.copyOf(zzapk, length + i);
            }
            zzjy.readFully(this.zzapw.data, zzapk.length, i);
            this.zzapw.zzbo(0);
            this.zzapw.zzbn(length);
            return;
        }
        zzkg zzkg = zzkq.zzasq;
        if (!this.zzaqz) {
            if (zzkq.zzaro) {
                this.zzaqx &= -1073741825;
                int i3 = 128;
                if (!this.zzara) {
                    zzjy.readFully(this.zzaps.data, 0, 1);
                    this.zzaqy++;
                    if ((this.zzaps.data[0] & 128) != 128) {
                        this.zzard = this.zzaps.data[0];
                        this.zzara = true;
                    } else {
                        throw new zzhv("Extension bit is set in signal byte");
                    }
                }
                byte b = this.zzard;
                if ((b & 1) == 1) {
                    boolean z = (b & 2) == 2;
                    this.zzaqx |= 1073741824;
                    if (!this.zzarb) {
                        zzjy.readFully(this.zzapx.data, 0, 8);
                        this.zzaqy += 8;
                        this.zzarb = true;
                        byte[] bArr = this.zzaps.data;
                        if (!z) {
                            i3 = 0;
                        }
                        bArr[0] = (byte) (i3 | 8);
                        this.zzaps.zzbo(0);
                        zzkg.zza(this.zzaps, 1);
                        this.zzarg++;
                        this.zzapx.zzbo(0);
                        zzkg.zza(this.zzapx, 8);
                        this.zzarg += 8;
                    }
                    if (z) {
                        if (!this.zzarc) {
                            zzjy.readFully(this.zzaps.data, 0, 1);
                            this.zzaqy++;
                            this.zzaps.zzbo(0);
                            this.zzare = this.zzaps.readUnsignedByte();
                            this.zzarc = true;
                        }
                        int i4 = this.zzare << 2;
                        this.zzaps.reset(i4);
                        zzjy.readFully(this.zzaps.data, 0, i4);
                        this.zzaqy += i4;
                        short s = (short) ((this.zzare / 2) + 1);
                        int i5 = (s * 6) + 2;
                        ByteBuffer byteBuffer = this.zzapz;
                        if (byteBuffer == null || byteBuffer.capacity() < i5) {
                            this.zzapz = ByteBuffer.allocate(i5);
                        }
                        this.zzapz.position(0);
                        this.zzapz.putShort(s);
                        int i6 = 0;
                        int i7 = 0;
                        while (true) {
                            i2 = this.zzare;
                            if (i6 >= i2) {
                                break;
                            }
                            int zzjf = this.zzaps.zzjf();
                            if (i6 % 2 == 0) {
                                this.zzapz.putShort((short) (zzjf - i7));
                            } else {
                                this.zzapz.putInt(zzjf - i7);
                            }
                            i6++;
                            i7 = zzjf;
                        }
                        int i8 = (i - this.zzaqy) - i7;
                        if (i2 % 2 == 1) {
                            this.zzapz.putInt(i8);
                        } else {
                            this.zzapz.putShort((short) i8);
                            this.zzapz.putInt(0);
                        }
                        this.zzapy.zzc(this.zzapz.array(), i5);
                        zzkg.zza(this.zzapy, i5);
                        this.zzarg += i5;
                    }
                }
            } else if (zzkq.zzarp != null) {
                this.zzapv.zzc(zzkq.zzarp, zzkq.zzarp.length);
            }
            this.zzaqz = true;
        }
        int limit = i + this.zzapv.limit();
        if (!"V_MPEG4/ISO/AVC".equals(zzkq.zzarm) && !"V_MPEGH/ISO/HEVC".equals(zzkq.zzarm)) {
            while (true) {
                int i9 = this.zzaqy;
                if (i9 >= limit) {
                    break;
                }
                zza(zzjy, zzkg, limit - i9);
            }
        } else {
            byte[] bArr2 = this.zzapr.data;
            bArr2[0] = 0;
            bArr2[1] = 0;
            bArr2[2] = 0;
            int i10 = zzkq.zzasr;
            int i11 = 4 - zzkq.zzasr;
            while (this.zzaqy < limit) {
                int i12 = this.zzarf;
                if (i12 == 0) {
                    int min = Math.min(i10, this.zzapv.zzja());
                    zzjy.readFully(bArr2, i11 + min, i10 - min);
                    if (min > 0) {
                        this.zzapv.zze(bArr2, i11, min);
                    }
                    this.zzaqy += i10;
                    this.zzapr.zzbo(0);
                    this.zzarf = this.zzapr.zzjf();
                    this.zzapq.zzbo(0);
                    zzkg.zza(this.zzapq, 4);
                    this.zzarg += 4;
                } else {
                    this.zzarf = i12 - zza(zzjy, zzkg, i12);
                }
            }
        }
        if ("A_VORBIS".equals(zzkq.zzarm)) {
            this.zzapt.zzbo(0);
            zzkg.zza(this.zzapt, 4);
            this.zzarg += 4;
        }
    }

    private final int zza(zzjy zzjy, zzkg zzkg, int i) throws IOException, InterruptedException {
        int i2;
        int zzja = this.zzapv.zzja();
        if (zzja > 0) {
            i2 = Math.min(i, zzja);
            zzkg.zza(this.zzapv, i2);
        } else {
            i2 = zzkg.zza(zzjy, i, false);
        }
        this.zzaqy += i2;
        this.zzarg += i2;
        return i2;
    }

    private final long zzea(long j) throws zzhv {
        long j2 = this.zzaqc;
        if (j2 != -9223372036854775807L) {
            return zzpq.zza(j, j2, 1000);
        }
        throw new zzhv("Can't scale timecode prior to timecodeScale being set.");
    }

    private static int[] zza(int[] iArr, int i) {
        if (iArr == null) {
            return new int[i];
        }
        if (iArr.length >= i) {
            return iArr;
        }
        return new int[Math.max(iArr.length << 1, i)];
    }
}
