package com.google.android.gms.internal.ads;

import android.util.Log;
import com.google.android.gms.internal.ads.zzmc;
import com.google.common.net.HttpHeaders;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzlk {
    private static final int zzayx = zzpq.zzbl("nam");
    private static final int zzayy = zzpq.zzbl("trk");
    private static final int zzayz = zzpq.zzbl("cmt");
    private static final int zzaza = zzpq.zzbl("day");
    private static final int zzazb = zzpq.zzbl("ART");
    private static final int zzazc = zzpq.zzbl("too");
    private static final int zzazd = zzpq.zzbl("alb");
    private static final int zzaze = zzpq.zzbl("com");
    private static final int zzazf = zzpq.zzbl("wrt");
    private static final int zzazg = zzpq.zzbl("lyr");
    private static final int zzazh = zzpq.zzbl("gen");
    private static final int zzazi = zzpq.zzbl("covr");
    private static final int zzazj = zzpq.zzbl("gnre");
    private static final int zzazk = zzpq.zzbl("grp");
    private static final int zzazl = zzpq.zzbl("disk");
    private static final int zzazm = zzpq.zzbl("trkn");
    private static final int zzazn = zzpq.zzbl("tmpo");
    private static final int zzazo = zzpq.zzbl("cpil");
    private static final int zzazp = zzpq.zzbl("aART");
    private static final int zzazq = zzpq.zzbl("sonm");
    private static final int zzazr = zzpq.zzbl("soal");
    private static final int zzazs = zzpq.zzbl("soar");
    private static final int zzazt = zzpq.zzbl("soaa");
    private static final int zzazu = zzpq.zzbl("soco");
    private static final int zzazv = zzpq.zzbl("rtng");
    private static final int zzazw = zzpq.zzbl("pgap");
    private static final int zzazx = zzpq.zzbl("sosn");
    private static final int zzazy = zzpq.zzbl("tvsh");
    private static final int zzazz = zzpq.zzbl("----");
    private static final String[] zzbaa = {"Blues", "Classic Rock", "Country", "Dance", "Disco", "Funk", "Grunge", "Hip-Hop", "Jazz", "Metal", "New Age", "Oldies", "Other", "Pop", "R&B", "Rap", "Reggae", "Rock", "Techno", "Industrial", "Alternative", "Ska", "Death Metal", "Pranks", "Soundtrack", "Euro-Techno", "Ambient", "Trip-Hop", "Vocal", "Jazz+Funk", "Fusion", "Trance", "Classical", "Instrumental", "Acid", "House", "Game", "Sound Clip", "Gospel", "Noise", "AlternRock", "Bass", "Soul", "Punk", "Space", "Meditative", "Instrumental Pop", "Instrumental Rock", "Ethnic", "Gothic", "Darkwave", "Techno-Industrial", "Electronic", "Pop-Folk", "Eurodance", "Dream", "Southern Rock", "Comedy", "Cult", "Gangsta", "Top 40", "Christian Rap", "Pop/Funk", "Jungle", "Native American", "Cabaret", "New Wave", "Psychadelic", "Rave", "Showtunes", HttpHeaders.TRAILER, "Lo-Fi", "Tribal", "Acid Punk", "Acid Jazz", "Polka", "Retro", "Musical", "Rock & Roll", "Hard Rock", "Folk", "Folk-Rock", "National Folk", "Swing", "Fast Fusion", "Bebob", "Latin", "Revival", "Celtic", "Bluegrass", "Avantgarde", "Gothic Rock", "Progressive Rock", "Psychedelic Rock", "Symphonic Rock", "Slow Rock", "Big Band", "Chorus", "Easy Listening", "Acoustic", "Humour", "Speech", "Chanson", "Opera", "Chamber Music", "Sonata", "Symphony", "Booty Bass", "Primus", "Porn Groove", "Satire", "Slow Jam", "Club", "Tango", "Samba", "Folklore", "Ballad", "Power Ballad", "Rhythmic Soul", "Freestyle", "Duet", "Punk Rock", "Drum Solo", "A capella", "Euro-House", "Dance Hall", "Goa", "Drum & Bass", "Club-House", "Hardcore", "Terror", "Indie", "BritPop", "Negerpunk", "Polsk Punk", "Beat", "Christian Gangsta Rap", "Heavy Metal", "Black Metal", "Crossover", "Contemporary Christian", "Christian Rock", "Merengue", "Salsa", "Thrash Metal", "Anime", "Jpop", "Synthpop"};

    public static zzmc.zza zzd(zzpk zzpk) {
        int position = zzpk.getPosition() + zzpk.readInt();
        int readInt = zzpk.readInt();
        int i = readInt >>> 24;
        zzmj zzmj = null;
        if (i == 169 || i == 65533) {
            int i2 = 16777215 & readInt;
            if (i2 == zzayz) {
                int readInt2 = zzpk.readInt();
                if (zzpk.readInt() == zzkr.zzavx) {
                    zzpk.zzbp(8);
                    String zzbq = zzpk.zzbq(readInt2 - 16);
                    zzmj = new zzmi("und", zzbq, zzbq);
                } else {
                    String valueOf = String.valueOf(zzkr.zzas(readInt));
                    Log.w("MetadataUtil", valueOf.length() != 0 ? "Failed to parse comment attribute: ".concat(valueOf) : new String("Failed to parse comment attribute: "));
                }
                zzpk.zzbo(position);
                return zzmj;
            }
            if (i2 != zzayx) {
                if (i2 != zzayy) {
                    if (i2 != zzaze) {
                        if (i2 != zzazf) {
                            if (i2 == zzaza) {
                                zzmm zza = zza(readInt, "TDRC", zzpk);
                                zzpk.zzbo(position);
                                return zza;
                            } else if (i2 == zzazb) {
                                zzmm zza2 = zza(readInt, "TPE1", zzpk);
                                zzpk.zzbo(position);
                                return zza2;
                            } else if (i2 == zzazc) {
                                zzmm zza3 = zza(readInt, "TSSE", zzpk);
                                zzpk.zzbo(position);
                                return zza3;
                            } else if (i2 == zzazd) {
                                zzmm zza4 = zza(readInt, "TALB", zzpk);
                                zzpk.zzbo(position);
                                return zza4;
                            } else if (i2 == zzazg) {
                                zzmm zza5 = zza(readInt, "USLT", zzpk);
                                zzpk.zzbo(position);
                                return zza5;
                            } else if (i2 == zzazh) {
                                zzmm zza6 = zza(readInt, "TCON", zzpk);
                                zzpk.zzbo(position);
                                return zza6;
                            } else if (i2 == zzazk) {
                                zzmm zza7 = zza(readInt, "TIT1", zzpk);
                                zzpk.zzbo(position);
                                return zza7;
                            }
                        }
                    }
                    zzmm zza8 = zza(readInt, "TCOM", zzpk);
                    zzpk.zzbo(position);
                    return zza8;
                }
            }
            zzmm zza9 = zza(readInt, "TIT2", zzpk);
            zzpk.zzbo(position);
            return zza9;
        }
        try {
            if (readInt == zzazj) {
                int zze = zze(zzpk);
                String str = (zze <= 0 || zze > zzbaa.length) ? null : zzbaa[zze - 1];
                if (str != null) {
                    zzmj = new zzmm("TCON", (String) null, str);
                } else {
                    Log.w("MetadataUtil", "Failed to parse standard genre code");
                }
                return zzmj;
            } else if (readInt == zzazl) {
                zzmm zzb = zzb(readInt, "TPOS", zzpk);
                zzpk.zzbo(position);
                return zzb;
            } else if (readInt == zzazm) {
                zzmm zzb2 = zzb(readInt, "TRCK", zzpk);
                zzpk.zzbo(position);
                return zzb2;
            } else if (readInt == zzazn) {
                zzmj zza10 = zza(readInt, "TBPM", zzpk, true, false);
                zzpk.zzbo(position);
                return zza10;
            } else if (readInt == zzazo) {
                zzmj zza11 = zza(readInt, "TCMP", zzpk, true, true);
                zzpk.zzbo(position);
                return zza11;
            } else if (readInt == zzazi) {
                int readInt3 = zzpk.readInt();
                if (zzpk.readInt() == zzkr.zzavx) {
                    int zzar = zzkr.zzar(zzpk.readInt());
                    String str2 = zzar == 13 ? "image/jpeg" : zzar == 14 ? "image/png" : null;
                    if (str2 == null) {
                        StringBuilder sb = new StringBuilder(41);
                        sb.append("Unrecognized cover art flags: ");
                        sb.append(zzar);
                        Log.w("MetadataUtil", sb.toString());
                    } else {
                        zzpk.zzbp(4);
                        int i3 = readInt3 - 16;
                        byte[] bArr = new byte[i3];
                        zzpk.zze(bArr, 0, i3);
                        zzmj = new zzmg(str2, (String) null, 3, bArr);
                    }
                } else {
                    Log.w("MetadataUtil", "Failed to parse cover art attribute");
                }
                zzpk.zzbo(position);
                return zzmj;
            } else if (readInt == zzazp) {
                zzmm zza12 = zza(readInt, "TPE2", zzpk);
                zzpk.zzbo(position);
                return zza12;
            } else if (readInt == zzazq) {
                zzmm zza13 = zza(readInt, "TSOT", zzpk);
                zzpk.zzbo(position);
                return zza13;
            } else if (readInt == zzazr) {
                zzmm zza14 = zza(readInt, "TSO2", zzpk);
                zzpk.zzbo(position);
                return zza14;
            } else if (readInt == zzazs) {
                zzmm zza15 = zza(readInt, "TSOA", zzpk);
                zzpk.zzbo(position);
                return zza15;
            } else if (readInt == zzazt) {
                zzmm zza16 = zza(readInt, "TSOP", zzpk);
                zzpk.zzbo(position);
                return zza16;
            } else if (readInt == zzazu) {
                zzmm zza17 = zza(readInt, "TSOC", zzpk);
                zzpk.zzbo(position);
                return zza17;
            } else if (readInt == zzazv) {
                zzmj zza18 = zza(readInt, "ITUNESADVISORY", zzpk, false, false);
                zzpk.zzbo(position);
                return zza18;
            } else if (readInt == zzazw) {
                zzmj zza19 = zza(readInt, "ITUNESGAPLESS", zzpk, false, true);
                zzpk.zzbo(position);
                return zza19;
            } else if (readInt == zzazx) {
                zzmm zza20 = zza(readInt, "TVSHOWSORT", zzpk);
                zzpk.zzbo(position);
                return zza20;
            } else if (readInt == zzazy) {
                zzmm zza21 = zza(readInt, "TVSHOW", zzpk);
                zzpk.zzbo(position);
                return zza21;
            } else if (readInt == zzazz) {
                String str3 = null;
                String str4 = null;
                int i4 = -1;
                int i5 = -1;
                while (zzpk.getPosition() < position) {
                    int position2 = zzpk.getPosition();
                    int readInt4 = zzpk.readInt();
                    int readInt5 = zzpk.readInt();
                    zzpk.zzbp(4);
                    if (readInt5 == zzkr.zzavv) {
                        str3 = zzpk.zzbq(readInt4 - 12);
                    } else if (readInt5 == zzkr.zzavw) {
                        str4 = zzpk.zzbq(readInt4 - 12);
                    } else {
                        if (readInt5 == zzkr.zzavx) {
                            i4 = position2;
                            i5 = readInt4;
                        }
                        zzpk.zzbp(readInt4 - 12);
                    }
                }
                if ("com.apple.iTunes".equals(str3) && "iTunSMPB".equals(str4)) {
                    if (i4 != -1) {
                        zzpk.zzbo(i4);
                        zzpk.zzbp(16);
                        zzmj = new zzmi("und", str4, zzpk.zzbq(i5 - 16));
                    }
                }
                zzpk.zzbo(position);
                return zzmj;
            }
        } finally {
            zzpk.zzbo(position);
        }
        String valueOf2 = String.valueOf(zzkr.zzas(readInt));
        Log.d("MetadataUtil", valueOf2.length() != 0 ? "Skipped unknown metadata entry: ".concat(valueOf2) : new String("Skipped unknown metadata entry: "));
        zzpk.zzbo(position);
        return null;
    }

    private static zzmm zza(int i, String str, zzpk zzpk) {
        int readInt = zzpk.readInt();
        if (zzpk.readInt() == zzkr.zzavx) {
            zzpk.zzbp(8);
            return new zzmm(str, (String) null, zzpk.zzbq(readInt - 16));
        }
        String valueOf = String.valueOf(zzkr.zzas(i));
        Log.w("MetadataUtil", valueOf.length() != 0 ? "Failed to parse text attribute: ".concat(valueOf) : new String("Failed to parse text attribute: "));
        return null;
    }

    private static zzmj zza(int i, String str, zzpk zzpk, boolean z, boolean z2) {
        int zze = zze(zzpk);
        if (z2) {
            zze = Math.min(1, zze);
        }
        if (zze < 0) {
            String valueOf = String.valueOf(zzkr.zzas(i));
            Log.w("MetadataUtil", valueOf.length() != 0 ? "Failed to parse uint8 attribute: ".concat(valueOf) : new String("Failed to parse uint8 attribute: "));
            return null;
        } else if (z) {
            return new zzmm(str, (String) null, Integer.toString(zze));
        } else {
            return new zzmi("und", str, Integer.toString(zze));
        }
    }

    private static zzmm zzb(int i, String str, zzpk zzpk) {
        int readInt = zzpk.readInt();
        if (zzpk.readInt() == zzkr.zzavx && readInt >= 22) {
            zzpk.zzbp(10);
            int readUnsignedShort = zzpk.readUnsignedShort();
            if (readUnsignedShort > 0) {
                StringBuilder sb = new StringBuilder(11);
                sb.append(readUnsignedShort);
                String sb2 = sb.toString();
                int readUnsignedShort2 = zzpk.readUnsignedShort();
                if (readUnsignedShort2 > 0) {
                    String valueOf = String.valueOf(sb2);
                    StringBuilder sb3 = new StringBuilder(String.valueOf(valueOf).length() + 12);
                    sb3.append(valueOf);
                    sb3.append("/");
                    sb3.append(readUnsignedShort2);
                    sb2 = sb3.toString();
                }
                return new zzmm(str, (String) null, sb2);
            }
        }
        String valueOf2 = String.valueOf(zzkr.zzas(i));
        Log.w("MetadataUtil", valueOf2.length() != 0 ? "Failed to parse index/count attribute: ".concat(valueOf2) : new String("Failed to parse index/count attribute: "));
        return null;
    }

    private static int zze(zzpk zzpk) {
        zzpk.zzbp(4);
        if (zzpk.readInt() == zzkr.zzavx) {
            zzpk.zzbp(8);
            return zzpk.readUnsignedByte();
        }
        Log.w("MetadataUtil", "Failed to parse uint8 attribute value");
        return -1;
    }
}
