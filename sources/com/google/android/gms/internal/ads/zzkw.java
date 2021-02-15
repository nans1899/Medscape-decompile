package com.google.android.gms.internal.ads;

import android.util.Log;
import android.util.Pair;
import com.github.jasminb.jsonapi.JSONAPISpecConstants;
import com.google.android.gms.internal.ads.zzmc;
import java.util.ArrayList;
import java.util.List;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzkw {
    private static final int zzavt = zzpq.zzbl(JSONAPISpecConstants.META);
    private static final int zzawt = zzpq.zzbl("vide");
    private static final int zzawu = zzpq.zzbl("soun");
    private static final int zzawv = zzpq.zzbl("text");
    private static final int zzaww = zzpq.zzbl("sbtl");
    private static final int zzawx = zzpq.zzbl("subt");
    private static final int zzawy = zzpq.zzbl("clcp");
    private static final int zzawz = zzpq.zzbl("cenc");

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v52, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r30v11, resolved type: byte[]} */
    /*  JADX ERROR: JadxRuntimeException in pass: IfRegionVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Don't wrap MOVE or CONST insns: 0x0551: MOVE  (r7v16 java.lang.String) = (r45v0 java.lang.String)
        	at jadx.core.dex.instructions.args.InsnArg.wrapArg(InsnArg.java:164)
        	at jadx.core.dex.visitors.shrink.CodeShrinkVisitor.assignInline(CodeShrinkVisitor.java:133)
        	at jadx.core.dex.visitors.shrink.CodeShrinkVisitor.checkInline(CodeShrinkVisitor.java:118)
        	at jadx.core.dex.visitors.shrink.CodeShrinkVisitor.shrinkBlock(CodeShrinkVisitor.java:65)
        	at jadx.core.dex.visitors.shrink.CodeShrinkVisitor.shrinkMethod(CodeShrinkVisitor.java:43)
        	at jadx.core.dex.visitors.regions.TernaryMod.makeTernaryInsn(TernaryMod.java:122)
        	at jadx.core.dex.visitors.regions.TernaryMod.visitRegion(TernaryMod.java:34)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:73)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:78)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:78)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:78)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:78)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:78)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:78)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:78)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterative(DepthRegionTraversal.java:27)
        	at jadx.core.dex.visitors.regions.IfRegionVisitor.visit(IfRegionVisitor.java:31)
        */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:193:0x038c  */
    /* JADX WARNING: Removed duplicated region for block: B:194:0x0396  */
    /* JADX WARNING: Removed duplicated region for block: B:197:0x039e  */
    /* JADX WARNING: Removed duplicated region for block: B:198:0x03a1  */
    /* JADX WARNING: Removed duplicated region for block: B:233:0x03f5  */
    /* JADX WARNING: Removed duplicated region for block: B:283:0x0555  */
    /* JADX WARNING: Removed duplicated region for block: B:292:0x058a  */
    /* JADX WARNING: Removed duplicated region for block: B:389:0x07c5  */
    /* JADX WARNING: Removed duplicated region for block: B:405:0x0817  */
    /* JADX WARNING: Removed duplicated region for block: B:408:0x0820 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:409:0x0821  */
    /* JADX WARNING: Removed duplicated region for block: B:66:0x00ee  */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x00f7  */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x010a  */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x010d  */
    /* JADX WARNING: Removed duplicated region for block: B:74:0x011a  */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x011d  */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x0149  */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x014c  */
    /* JADX WARNING: Removed duplicated region for block: B:82:0x0157  */
    /* JADX WARNING: Removed duplicated region for block: B:83:0x0159  */
    /* JADX WARNING: Removed duplicated region for block: B:86:0x01b6  */
    public static com.google.android.gms.internal.ads.zzln zza(com.google.android.gms.internal.ads.zzku r49, com.google.android.gms.internal.ads.zzkt r50, long r51, com.google.android.gms.internal.ads.zzjn r53, boolean r54) throws com.google.android.gms.internal.ads.zzhv {
        /*
            r0 = r49
            r15 = r53
            int r1 = com.google.android.gms.internal.ads.zzkr.zzatw
            com.google.android.gms.internal.ads.zzku r1 = r0.zzav(r1)
            int r2 = com.google.android.gms.internal.ads.zzkr.zzauk
            com.google.android.gms.internal.ads.zzkt r2 = r1.zzau(r2)
            com.google.android.gms.internal.ads.zzpk r2 = r2.zzawi
            r14 = 16
            r2.zzbo(r14)
            int r2 = r2.readInt()
            int r3 = zzawu
            r4 = 4
            r11 = -1
            if (r2 != r3) goto L_0x0023
            r10 = 1
            goto L_0x0043
        L_0x0023:
            int r3 = zzawt
            if (r2 != r3) goto L_0x0029
            r10 = 2
            goto L_0x0043
        L_0x0029:
            int r3 = zzawv
            if (r2 == r3) goto L_0x0042
            int r3 = zzaww
            if (r2 == r3) goto L_0x0042
            int r3 = zzawx
            if (r2 == r3) goto L_0x0042
            int r3 = zzawy
            if (r2 != r3) goto L_0x003a
            goto L_0x0042
        L_0x003a:
            int r3 = zzavt
            if (r2 != r3) goto L_0x0040
            r10 = 4
            goto L_0x0043
        L_0x0040:
            r10 = -1
            goto L_0x0043
        L_0x0042:
            r10 = 3
        L_0x0043:
            r8 = 0
            if (r10 != r11) goto L_0x0047
            return r8
        L_0x0047:
            int r2 = com.google.android.gms.internal.ads.zzkr.zzaug
            com.google.android.gms.internal.ads.zzkt r2 = r0.zzau(r2)
            com.google.android.gms.internal.ads.zzpk r2 = r2.zzawi
            r7 = 8
            r2.zzbo(r7)
            int r3 = r2.readInt()
            int r3 = com.google.android.gms.internal.ads.zzkr.zzaq(r3)
            if (r3 != 0) goto L_0x0061
            r5 = 8
            goto L_0x0063
        L_0x0061:
            r5 = 16
        L_0x0063:
            r2.zzbp(r5)
            int r5 = r2.readInt()
            r2.zzbp(r4)
            int r6 = r2.getPosition()
            if (r3 != 0) goto L_0x0075
            r12 = 4
            goto L_0x0077
        L_0x0075:
            r12 = 8
        L_0x0077:
            r9 = 0
        L_0x0078:
            if (r9 >= r12) goto L_0x0088
            byte[] r8 = r2.data
            int r20 = r6 + r9
            byte r8 = r8[r20]
            if (r8 == r11) goto L_0x0084
            r6 = 0
            goto L_0x0089
        L_0x0084:
            int r9 = r9 + 1
            r8 = 0
            goto L_0x0078
        L_0x0088:
            r6 = 1
        L_0x0089:
            r20 = 0
            r8 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            if (r6 == 0) goto L_0x0097
            r2.zzbp(r12)
        L_0x0095:
            r11 = r8
            goto L_0x00a9
        L_0x0097:
            if (r3 != 0) goto L_0x009e
            long r22 = r2.zzjc()
            goto L_0x00a2
        L_0x009e:
            long r22 = r2.zzjg()
        L_0x00a2:
            int r3 = (r22 > r20 ? 1 : (r22 == r20 ? 0 : -1))
            if (r3 != 0) goto L_0x00a7
            goto L_0x0095
        L_0x00a7:
            r11 = r22
        L_0x00a9:
            r2.zzbp(r14)
            int r3 = r2.readInt()
            int r6 = r2.readInt()
            r2.zzbp(r4)
            int r4 = r2.readInt()
            int r2 = r2.readInt()
            r14 = 65536(0x10000, float:9.18355E-41)
            r13 = -65536(0xffffffffffff0000, float:NaN)
            if (r3 != 0) goto L_0x00ce
            if (r6 != r14) goto L_0x00ce
            if (r4 != r13) goto L_0x00ce
            if (r2 != 0) goto L_0x00ce
            r2 = 90
            goto L_0x00e5
        L_0x00ce:
            if (r3 != 0) goto L_0x00d9
            if (r6 != r13) goto L_0x00d9
            if (r4 != r14) goto L_0x00d9
            if (r2 != 0) goto L_0x00d9
            r2 = 270(0x10e, float:3.78E-43)
            goto L_0x00e5
        L_0x00d9:
            if (r3 != r13) goto L_0x00e4
            if (r6 != 0) goto L_0x00e4
            if (r4 != 0) goto L_0x00e4
            if (r2 != r13) goto L_0x00e4
            r2 = 180(0xb4, float:2.52E-43)
            goto L_0x00e5
        L_0x00e4:
            r2 = 0
        L_0x00e5:
            com.google.android.gms.internal.ads.zzlc r14 = new com.google.android.gms.internal.ads.zzlc
            r14.<init>(r5, r11, r2)
            int r2 = (r51 > r8 ? 1 : (r51 == r8 ? 0 : -1))
            if (r2 != 0) goto L_0x00f7
            long r2 = r14.zzdh
            r26 = r2
            r2 = r50
            goto L_0x00fb
        L_0x00f7:
            r2 = r50
            r26 = r51
        L_0x00fb:
            com.google.android.gms.internal.ads.zzpk r2 = r2.zzawi
            r2.zzbo(r7)
            int r3 = r2.readInt()
            int r3 = com.google.android.gms.internal.ads.zzkr.zzaq(r3)
            if (r3 != 0) goto L_0x010d
            r3 = 8
            goto L_0x010f
        L_0x010d:
            r3 = 16
        L_0x010f:
            r2.zzbp(r3)
            long r32 = r2.zzjc()
            int r2 = (r26 > r8 ? 1 : (r26 == r8 ? 0 : -1))
            if (r2 != 0) goto L_0x011d
            r26 = r8
            goto L_0x0128
        L_0x011d:
            r28 = 1000000(0xf4240, double:4.940656E-318)
            r30 = r32
            long r2 = com.google.android.gms.internal.ads.zzpq.zza((long) r26, (long) r28, (long) r30)
            r26 = r2
        L_0x0128:
            int r2 = com.google.android.gms.internal.ads.zzkr.zzatx
            com.google.android.gms.internal.ads.zzku r2 = r1.zzav(r2)
            int r3 = com.google.android.gms.internal.ads.zzkr.zzaty
            com.google.android.gms.internal.ads.zzku r2 = r2.zzav(r3)
            int r3 = com.google.android.gms.internal.ads.zzkr.zzauj
            com.google.android.gms.internal.ads.zzkt r1 = r1.zzau(r3)
            com.google.android.gms.internal.ads.zzpk r1 = r1.zzawi
            r1.zzbo(r7)
            int r3 = r1.readInt()
            int r3 = com.google.android.gms.internal.ads.zzkr.zzaq(r3)
            if (r3 != 0) goto L_0x014c
            r4 = 8
            goto L_0x014e
        L_0x014c:
            r4 = 16
        L_0x014e:
            r1.zzbp(r4)
            long r4 = r1.zzjc()
            if (r3 != 0) goto L_0x0159
            r3 = 4
            goto L_0x015b
        L_0x0159:
            r3 = 8
        L_0x015b:
            r1.zzbp(r3)
            int r1 = r1.readUnsignedShort()
            int r3 = r1 >> 10
            r3 = r3 & 31
            int r3 = r3 + 96
            char r3 = (char) r3
            int r6 = r1 >> 5
            r6 = r6 & 31
            int r6 = r6 + 96
            char r6 = (char) r6
            r1 = r1 & 31
            int r1 = r1 + 96
            char r1 = (char) r1
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r9 = 3
            r8.<init>(r9)
            r8.append(r3)
            r8.append(r6)
            r8.append(r1)
            java.lang.String r1 = r8.toString()
            java.lang.Long r3 = java.lang.Long.valueOf(r4)
            android.util.Pair r13 = android.util.Pair.create(r3, r1)
            int r1 = com.google.android.gms.internal.ads.zzkr.zzaul
            com.google.android.gms.internal.ads.zzkt r1 = r2.zzau(r1)
            com.google.android.gms.internal.ads.zzpk r12 = r1.zzawi
            int r22 = r14.id
            int r28 = r14.zzahh
            java.lang.Object r1 = r13.second
            r11 = r1
            java.lang.String r11 = (java.lang.String) r11
            r1 = 12
            r12.zzbo(r1)
            int r9 = r12.readInt()
            com.google.android.gms.internal.ads.zzkx r8 = new com.google.android.gms.internal.ads.zzkx
            r8.<init>(r9)
            r6 = 0
        L_0x01b4:
            if (r6 >= r9) goto L_0x07a9
            int r5 = r12.getPosition()
            int r4 = r12.readInt()
            if (r4 <= 0) goto L_0x01c2
            r1 = 1
            goto L_0x01c3
        L_0x01c2:
            r1 = 0
        L_0x01c3:
            java.lang.String r3 = "childAtomSize should be positive"
            com.google.android.gms.internal.ads.zzoz.checkArgument(r1, r3)
            int r1 = r12.readInt()
            int r2 = com.google.android.gms.internal.ads.zzkr.zzast
            if (r1 == r2) goto L_0x05a2
            int r2 = com.google.android.gms.internal.ads.zzkr.zzasu
            if (r1 == r2) goto L_0x05a2
            int r2 = com.google.android.gms.internal.ads.zzkr.zzaur
            if (r1 == r2) goto L_0x05a2
            int r2 = com.google.android.gms.internal.ads.zzkr.zzavd
            if (r1 == r2) goto L_0x05a2
            int r2 = com.google.android.gms.internal.ads.zzkr.zzasv
            if (r1 == r2) goto L_0x05a2
            int r2 = com.google.android.gms.internal.ads.zzkr.zzasw
            if (r1 == r2) goto L_0x05a2
            int r2 = com.google.android.gms.internal.ads.zzkr.zzasx
            if (r1 == r2) goto L_0x05a2
            int r2 = com.google.android.gms.internal.ads.zzkr.zzawc
            if (r1 == r2) goto L_0x05a2
            int r2 = com.google.android.gms.internal.ads.zzkr.zzawd
            if (r1 != r2) goto L_0x01f2
            goto L_0x05a2
        L_0x01f2:
            int r2 = com.google.android.gms.internal.ads.zzkr.zzata
            if (r1 == r2) goto L_0x030c
            int r2 = com.google.android.gms.internal.ads.zzkr.zzaus
            if (r1 == r2) goto L_0x030c
            int r2 = com.google.android.gms.internal.ads.zzkr.zzatf
            if (r1 == r2) goto L_0x030c
            int r2 = com.google.android.gms.internal.ads.zzkr.zzath
            if (r1 == r2) goto L_0x030c
            int r2 = com.google.android.gms.internal.ads.zzkr.zzatj
            if (r1 == r2) goto L_0x030c
            int r2 = com.google.android.gms.internal.ads.zzkr.zzatm
            if (r1 == r2) goto L_0x030c
            int r2 = com.google.android.gms.internal.ads.zzkr.zzatk
            if (r1 == r2) goto L_0x030c
            int r2 = com.google.android.gms.internal.ads.zzkr.zzatl
            if (r1 == r2) goto L_0x030c
            int r2 = com.google.android.gms.internal.ads.zzkr.zzavq
            if (r1 == r2) goto L_0x030c
            int r2 = com.google.android.gms.internal.ads.zzkr.zzavr
            if (r1 == r2) goto L_0x030c
            int r2 = com.google.android.gms.internal.ads.zzkr.zzatd
            if (r1 == r2) goto L_0x030c
            int r2 = com.google.android.gms.internal.ads.zzkr.zzate
            if (r1 == r2) goto L_0x030c
            int r2 = com.google.android.gms.internal.ads.zzkr.zzatb
            if (r1 == r2) goto L_0x030c
            int r2 = com.google.android.gms.internal.ads.zzkr.zzawg
            if (r1 != r2) goto L_0x022c
            goto L_0x030c
        L_0x022c:
            int r2 = com.google.android.gms.internal.ads.zzkr.zzavb
            if (r1 == r2) goto L_0x026a
            int r2 = com.google.android.gms.internal.ads.zzkr.zzavm
            if (r1 == r2) goto L_0x026a
            int r2 = com.google.android.gms.internal.ads.zzkr.zzavn
            if (r1 == r2) goto L_0x026a
            int r2 = com.google.android.gms.internal.ads.zzkr.zzavo
            if (r1 == r2) goto L_0x026a
            int r2 = com.google.android.gms.internal.ads.zzkr.zzavp
            if (r1 != r2) goto L_0x0241
            goto L_0x026a
        L_0x0241:
            int r2 = com.google.android.gms.internal.ads.zzkr.zzawf
            if (r1 != r2) goto L_0x0254
            java.lang.String r1 = java.lang.Integer.toString(r22)
            java.lang.String r2 = "application/x-camera-motion"
            r3 = -1
            r7 = 0
            com.google.android.gms.internal.ads.zzho r1 = com.google.android.gms.internal.ads.zzho.zza(r1, r2, r7, r3, r15)
            r8.zzahx = r1
            goto L_0x0255
        L_0x0254:
            r7 = 0
        L_0x0255:
            r23 = r4
            r24 = r5
            r37 = r6
            r0 = r8
            r31 = r9
            r19 = r10
            r36 = r11
            r16 = r12
            r48 = r13
            r18 = r14
            goto L_0x0365
        L_0x026a:
            r3 = -1
            r7 = 0
            int r2 = r5 + 8
            r19 = 8
            int r2 = r2 + 8
            r12.zzbo(r2)
            r29 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
            int r2 = com.google.android.gms.internal.ads.zzkr.zzavb
            java.lang.String r24 = "application/ttml+xml"
            if (r1 != r2) goto L_0x0285
            r17 = r7
            r2 = 1
            r7 = 0
            goto L_0x02bf
        L_0x0285:
            int r2 = com.google.android.gms.internal.ads.zzkr.zzavm
            if (r1 != r2) goto L_0x029f
            int r1 = r4 + -8
            int r1 = r1 + -8
            byte[] r2 = new byte[r1]
            r7 = 0
            r12.zze(r2, r7, r1)
            java.util.List r1 = java.util.Collections.singletonList(r2)
            java.lang.String r2 = "application/x-quicktime-tx3g"
            r17 = r1
            r24 = r2
            r2 = 1
            goto L_0x02bf
        L_0x029f:
            r7 = 0
            int r2 = com.google.android.gms.internal.ads.zzkr.zzavn
            if (r1 != r2) goto L_0x02ac
            java.lang.String r1 = "application/x-mp4-vtt"
            r24 = r1
        L_0x02a8:
            r2 = 1
        L_0x02a9:
            r17 = 0
            goto L_0x02bf
        L_0x02ac:
            int r2 = com.google.android.gms.internal.ads.zzkr.zzavo
            if (r1 != r2) goto L_0x02b3
            r29 = r20
            goto L_0x02a8
        L_0x02b3:
            int r2 = com.google.android.gms.internal.ads.zzkr.zzavp
            if (r1 != r2) goto L_0x0306
            r2 = 1
            r8.zzaxb = r2
            java.lang.String r1 = "application/x-mp4-cea-608"
            r24 = r1
            goto L_0x02a9
        L_0x02bf:
            java.lang.String r1 = java.lang.Integer.toString(r22)
            r18 = 0
            r34 = -1
            r35 = 0
            r36 = -1
            r37 = 1
            r2 = r24
            r24 = -1
            r3 = r18
            r18 = r14
            r14 = r4
            r4 = r34
            r38 = r5
            r5 = r35
            r39 = r6
            r6 = r11
            r19 = 0
            r31 = 0
            r7 = r36
            r40 = r8
            r8 = r53
            r31 = r9
            r19 = r10
            r9 = r29
            r0 = r11
            r11 = r17
            com.google.android.gms.internal.ads.zzho r1 = com.google.android.gms.internal.ads.zzho.zza(r1, r2, r3, r4, r5, r6, r7, r8, r9, r11)
            r11 = r40
            r11.zzahx = r1
            r36 = r0
            r0 = r11
            r16 = r12
            r48 = r13
            r23 = r14
            r24 = r38
            goto L_0x0363
        L_0x0306:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r0.<init>()
            throw r0
        L_0x030c:
            r38 = r5
            r39 = r6
            r31 = r9
            r19 = r10
            r0 = r11
            r18 = r14
            r14 = r4
            r11 = r8
            r10 = r38
            int r5 = r10 + 8
            r9 = 8
            int r5 = r5 + r9
            r12.zzbo(r5)
            r2 = 6
            if (r54 == 0) goto L_0x032e
            int r4 = r12.readUnsignedShort()
            r12.zzbp(r2)
            goto L_0x0332
        L_0x032e:
            r12.zzbp(r9)
            r4 = 0
        L_0x0332:
            if (r4 == 0) goto L_0x036f
            r8 = 1
            if (r4 != r8) goto L_0x0339
            r7 = 2
            goto L_0x0371
        L_0x0339:
            r7 = 2
            if (r4 != r7) goto L_0x0358
            r2 = 16
            r12.zzbp(r2)
            long r4 = r12.readLong()
            double r4 = java.lang.Double.longBitsToDouble(r4)
            long r4 = java.lang.Math.round(r4)
            int r2 = (int) r4
            int r4 = r12.zzjf()
            r5 = 20
            r12.zzbp(r5)
            goto L_0x0384
        L_0x0358:
            r36 = r0
            r24 = r10
            r0 = r11
            r16 = r12
            r48 = r13
            r23 = r14
        L_0x0363:
            r37 = r39
        L_0x0365:
            r17 = -1
            r25 = 3
            r29 = 0
        L_0x036b:
            r30 = 16
            goto L_0x078c
        L_0x036f:
            r7 = 2
            r8 = 1
        L_0x0371:
            int r5 = r12.readUnsignedShort()
            r12.zzbp(r2)
            int r2 = r12.zzje()
            if (r4 != r8) goto L_0x0383
            r4 = 16
            r12.zzbp(r4)
        L_0x0383:
            r4 = r5
        L_0x0384:
            int r5 = r12.getPosition()
            int r6 = com.google.android.gms.internal.ads.zzkr.zzaus
            if (r1 != r6) goto L_0x0396
            r6 = r39
            int r1 = zza((com.google.android.gms.internal.ads.zzpk) r12, (int) r10, (int) r14, (com.google.android.gms.internal.ads.zzkx) r11, (int) r6)
            r12.zzbo(r5)
            goto L_0x0398
        L_0x0396:
            r6 = r39
        L_0x0398:
            int r7 = com.google.android.gms.internal.ads.zzkr.zzatf
            java.lang.String r8 = "audio/raw"
            if (r1 != r7) goto L_0x03a1
            java.lang.String r1 = "audio/ac3"
            goto L_0x03ea
        L_0x03a1:
            int r7 = com.google.android.gms.internal.ads.zzkr.zzath
            if (r1 != r7) goto L_0x03a8
            java.lang.String r1 = "audio/eac3"
            goto L_0x03ea
        L_0x03a8:
            int r7 = com.google.android.gms.internal.ads.zzkr.zzatj
            if (r1 != r7) goto L_0x03af
            java.lang.String r1 = "audio/vnd.dts"
            goto L_0x03ea
        L_0x03af:
            int r7 = com.google.android.gms.internal.ads.zzkr.zzatk
            if (r1 == r7) goto L_0x03e8
            int r7 = com.google.android.gms.internal.ads.zzkr.zzatl
            if (r1 != r7) goto L_0x03b8
            goto L_0x03e8
        L_0x03b8:
            int r7 = com.google.android.gms.internal.ads.zzkr.zzatm
            if (r1 != r7) goto L_0x03bf
            java.lang.String r1 = "audio/vnd.dts.hd;profile=lbr"
            goto L_0x03ea
        L_0x03bf:
            int r7 = com.google.android.gms.internal.ads.zzkr.zzavq
            if (r1 != r7) goto L_0x03c6
            java.lang.String r1 = "audio/3gpp"
            goto L_0x03ea
        L_0x03c6:
            int r7 = com.google.android.gms.internal.ads.zzkr.zzavr
            if (r1 != r7) goto L_0x03cd
            java.lang.String r1 = "audio/amr-wb"
            goto L_0x03ea
        L_0x03cd:
            int r7 = com.google.android.gms.internal.ads.zzkr.zzatd
            if (r1 == r7) goto L_0x03e6
            int r7 = com.google.android.gms.internal.ads.zzkr.zzate
            if (r1 != r7) goto L_0x03d6
            goto L_0x03e6
        L_0x03d6:
            int r7 = com.google.android.gms.internal.ads.zzkr.zzatb
            if (r1 != r7) goto L_0x03dd
            java.lang.String r1 = "audio/mpeg"
            goto L_0x03ea
        L_0x03dd:
            int r7 = com.google.android.gms.internal.ads.zzkr.zzawg
            if (r1 != r7) goto L_0x03e4
            java.lang.String r1 = "audio/alac"
            goto L_0x03ea
        L_0x03e4:
            r1 = 0
            goto L_0x03ea
        L_0x03e6:
            r1 = r8
            goto L_0x03ea
        L_0x03e8:
            java.lang.String r1 = "audio/vnd.dts.hd"
        L_0x03ea:
            r7 = r1
            r29 = r2
            r17 = r4
            r30 = 0
        L_0x03f1:
            int r1 = r5 - r10
            if (r1 >= r14) goto L_0x0540
            r12.zzbo(r5)
            int r4 = r12.readInt()
            if (r4 <= 0) goto L_0x0400
            r1 = 1
            goto L_0x0401
        L_0x0400:
            r1 = 0
        L_0x0401:
            com.google.android.gms.internal.ads.zzoz.checkArgument(r1, r3)
            int r1 = r12.readInt()
            int r2 = com.google.android.gms.internal.ads.zzkr.zzaub
            if (r1 == r2) goto L_0x04b9
            if (r54 == 0) goto L_0x0414
            int r2 = com.google.android.gms.internal.ads.zzkr.zzatc
            if (r1 != r2) goto L_0x0414
            goto L_0x04b9
        L_0x0414:
            int r2 = com.google.android.gms.internal.ads.zzkr.zzatg
            if (r1 != r2) goto L_0x0438
            int r1 = r5 + 8
            r12.zzbo(r1)
            java.lang.String r1 = java.lang.Integer.toString(r22)
            com.google.android.gms.internal.ads.zzho r1 = com.google.android.gms.internal.ads.zzic.zza(r12, r1, r0, r15)
            r11.zzahx = r1
        L_0x0427:
            r41 = r3
            r2 = r4
            r44 = r6
            r45 = r7
            r46 = r8
            r47 = r10
            r15 = r11
            r11 = 0
            r16 = 2
            goto L_0x04b2
        L_0x0438:
            int r2 = com.google.android.gms.internal.ads.zzkr.zzati
            if (r1 != r2) goto L_0x044c
            int r1 = r5 + 8
            r12.zzbo(r1)
            java.lang.String r1 = java.lang.Integer.toString(r22)
            com.google.android.gms.internal.ads.zzho r1 = com.google.android.gms.internal.ads.zzic.zzb(r12, r1, r0, r15)
            r11.zzahx = r1
            goto L_0x0427
        L_0x044c:
            int r2 = com.google.android.gms.internal.ads.zzkr.zzatn
            if (r1 != r2) goto L_0x0488
            java.lang.String r1 = java.lang.Integer.toString(r22)
            r34 = 0
            r35 = -1
            r36 = -1
            r37 = 0
            r38 = 0
            r2 = r7
            r41 = r3
            r3 = r34
            r42 = r4
            r4 = r35
            r43 = r5
            r5 = r36
            r44 = r6
            r6 = r17
            r45 = r7
            r16 = 2
            r7 = r29
            r46 = r8
            r8 = r37
            r9 = r53
            r47 = r10
            r10 = r38
            r15 = r11
            r11 = r0
            com.google.android.gms.internal.ads.zzho r1 = com.google.android.gms.internal.ads.zzho.zza(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11)
            r15.zzahx = r1
            goto L_0x04ad
        L_0x0488:
            r41 = r3
            r42 = r4
            r43 = r5
            r44 = r6
            r45 = r7
            r46 = r8
            r47 = r10
            r15 = r11
            r16 = 2
            int r2 = com.google.android.gms.internal.ads.zzkr.zzawg
            if (r1 != r2) goto L_0x04ad
            r2 = r42
            byte[] r1 = new byte[r2]
            r5 = r43
            r12.zzbo(r5)
            r11 = 0
            r12.zze(r1, r11, r2)
            r30 = r1
            goto L_0x04b2
        L_0x04ad:
            r2 = r42
            r5 = r43
            r11 = 0
        L_0x04b2:
            r4 = r41
            r7 = r45
            r10 = -1
            goto L_0x0531
        L_0x04b9:
            r41 = r3
            r2 = r4
            r44 = r6
            r45 = r7
            r46 = r8
            r47 = r10
            r15 = r11
            r11 = 0
            r16 = 2
            int r3 = com.google.android.gms.internal.ads.zzkr.zzaub
            if (r1 != r3) goto L_0x04d1
            r1 = r5
            r4 = r41
        L_0x04cf:
            r10 = -1
            goto L_0x04fb
        L_0x04d1:
            int r1 = r12.getPosition()
        L_0x04d5:
            int r3 = r1 - r5
            if (r3 >= r2) goto L_0x04f7
            r12.zzbo(r1)
            int r3 = r12.readInt()
            r4 = r41
            if (r3 <= 0) goto L_0x04e6
            r9 = 1
            goto L_0x04e7
        L_0x04e6:
            r9 = 0
        L_0x04e7:
            com.google.android.gms.internal.ads.zzoz.checkArgument(r9, r4)
            int r6 = r12.readInt()
            int r7 = com.google.android.gms.internal.ads.zzkr.zzaub
            if (r6 != r7) goto L_0x04f3
            goto L_0x04cf
        L_0x04f3:
            int r1 = r1 + r3
            r41 = r4
            goto L_0x04d5
        L_0x04f7:
            r4 = r41
            r1 = -1
            goto L_0x04cf
        L_0x04fb:
            if (r1 == r10) goto L_0x052f
            android.util.Pair r1 = zzb(r12, r1)
            java.lang.Object r3 = r1.first
            java.lang.String r3 = (java.lang.String) r3
            java.lang.Object r1 = r1.second
            r30 = r1
            byte[] r30 = (byte[]) r30
            java.lang.String r1 = "audio/mp4a-latm"
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L_0x052d
            android.util.Pair r1 = com.google.android.gms.internal.ads.zzpc.zze(r30)
            java.lang.Object r6 = r1.first
            java.lang.Integer r6 = (java.lang.Integer) r6
            int r6 = r6.intValue()
            java.lang.Object r1 = r1.second
            java.lang.Integer r1 = (java.lang.Integer) r1
            int r1 = r1.intValue()
            r17 = r1
            r7 = r3
            r29 = r6
            goto L_0x0531
        L_0x052d:
            r7 = r3
            goto L_0x0531
        L_0x052f:
            r7 = r45
        L_0x0531:
            int r5 = r5 + r2
            r3 = r4
            r11 = r15
            r6 = r44
            r8 = r46
            r10 = r47
            r9 = 8
            r15 = r53
            goto L_0x03f1
        L_0x0540:
            r44 = r6
            r45 = r7
            r46 = r8
            r47 = r10
            r15 = r11
            r10 = -1
            r11 = 0
            r16 = 2
            com.google.android.gms.internal.ads.zzho r1 = r15.zzahx
            if (r1 != 0) goto L_0x058a
            r7 = r45
            if (r7 == 0) goto L_0x058a
            r1 = r46
            boolean r1 = r1.equals(r7)
            if (r1 == 0) goto L_0x055f
            r8 = 2
            goto L_0x0560
        L_0x055f:
            r8 = -1
        L_0x0560:
            java.lang.String r1 = java.lang.Integer.toString(r22)
            r3 = 0
            r4 = -1
            r5 = -1
            if (r30 != 0) goto L_0x056b
            r9 = 0
            goto L_0x0570
        L_0x056b:
            java.util.List r2 = java.util.Collections.singletonList(r30)
            r9 = r2
        L_0x0570:
            r24 = 0
            r2 = r7
            r6 = r17
            r7 = r29
            r17 = -1
            r10 = r53
            r29 = 0
            r11 = r24
            r50 = r13
            r13 = r12
            r12 = r0
            com.google.android.gms.internal.ads.zzho r1 = com.google.android.gms.internal.ads.zzho.zza(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12)
            r15.zzahx = r1
            goto L_0x0591
        L_0x058a:
            r50 = r13
            r17 = -1
            r29 = 0
            r13 = r12
        L_0x0591:
            r48 = r50
            r36 = r0
            r16 = r13
            r23 = r14
            r0 = r15
            r37 = r44
            r24 = r47
        L_0x059e:
            r25 = 3
            goto L_0x036b
        L_0x05a2:
            r47 = r5
            r44 = r6
            r15 = r8
            r31 = r9
            r19 = r10
            r0 = r11
            r50 = r13
            r18 = r14
            r17 = -1
            r29 = 0
            r14 = r4
            r13 = r12
            r4 = r3
            r12 = r47
            int r5 = r12 + 8
            r11 = 8
            int r5 = r5 + r11
            r13.zzbo(r5)
            r10 = 16
            r13.zzbp(r10)
            int r6 = r13.readUnsignedShort()
            int r7 = r13.readUnsignedShort()
            r2 = 1065353216(0x3f800000, float:1.0)
            r3 = 50
            r13.zzbp(r3)
            int r3 = r13.getPosition()
            int r5 = com.google.android.gms.internal.ads.zzkr.zzaur
            r9 = r44
            if (r1 != r5) goto L_0x05e6
            int r1 = zza((com.google.android.gms.internal.ads.zzpk) r13, (int) r12, (int) r14, (com.google.android.gms.internal.ads.zzkx) r15, (int) r9)
            r13.zzbo(r3)
        L_0x05e6:
            r2 = 0
            r5 = 0
            r23 = 0
            r24 = 1065353216(0x3f800000, float:1.0)
            r30 = 0
            r34 = -1
        L_0x05f0:
            int r8 = r3 - r12
            if (r8 >= r14) goto L_0x073d
            r13.zzbo(r3)
            int r8 = r13.getPosition()
            int r10 = r13.readInt()
            if (r10 != 0) goto L_0x0609
            int r36 = r13.getPosition()
            int r11 = r36 - r12
            if (r11 == r14) goto L_0x073d
        L_0x0609:
            if (r10 <= 0) goto L_0x060d
            r11 = 1
            goto L_0x060e
        L_0x060d:
            r11 = 0
        L_0x060e:
            com.google.android.gms.internal.ads.zzoz.checkArgument(r11, r4)
            int r11 = r13.readInt()
            r36 = r0
            int r0 = com.google.android.gms.internal.ads.zzkr.zzatz
            if (r11 != r0) goto L_0x063b
            if (r2 != 0) goto L_0x061f
            r0 = 1
            goto L_0x0620
        L_0x061f:
            r0 = 0
        L_0x0620:
            com.google.android.gms.internal.ads.zzoz.checkState(r0)
            int r8 = r8 + 8
            r13.zzbo(r8)
            com.google.android.gms.internal.ads.zzps r0 = com.google.android.gms.internal.ads.zzps.zzg(r13)
            java.util.List<byte[]> r2 = r0.zzahe
            int r8 = r0.zzasr
            r15.zzasr = r8
            if (r5 != 0) goto L_0x0638
            float r0 = r0.zzbkc
            r24 = r0
        L_0x0638:
            java.lang.String r0 = "video/avc"
            goto L_0x0658
        L_0x063b:
            int r0 = com.google.android.gms.internal.ads.zzkr.zzaua
            if (r11 != r0) goto L_0x0664
            if (r2 != 0) goto L_0x0643
            r0 = 1
            goto L_0x0644
        L_0x0643:
            r0 = 0
        L_0x0644:
            com.google.android.gms.internal.ads.zzoz.checkState(r0)
            int r8 = r8 + 8
            r13.zzbo(r8)
            com.google.android.gms.internal.ads.zzpy r0 = com.google.android.gms.internal.ads.zzpy.zzi(r13)
            java.util.List<byte[]> r2 = r0.zzahe
            int r0 = r0.zzasr
            r15.zzasr = r0
            java.lang.String r0 = "video/hevc"
        L_0x0658:
            r37 = r1
            r23 = r2
            r41 = r4
            r4 = 2
            r8 = 1
            r11 = 3
            r2 = r0
            goto L_0x0730
        L_0x0664:
            int r0 = com.google.android.gms.internal.ads.zzkr.zzawe
            if (r11 != r0) goto L_0x067b
            if (r2 != 0) goto L_0x066c
            r0 = 1
            goto L_0x066d
        L_0x066c:
            r0 = 0
        L_0x066d:
            com.google.android.gms.internal.ads.zzoz.checkState(r0)
            int r0 = com.google.android.gms.internal.ads.zzkr.zzawc
            if (r1 != r0) goto L_0x0677
            java.lang.String r0 = "video/x-vnd.on2.vp8"
            goto L_0x0679
        L_0x0677:
            java.lang.String r0 = "video/x-vnd.on2.vp9"
        L_0x0679:
            r2 = r0
            goto L_0x0689
        L_0x067b:
            int r0 = com.google.android.gms.internal.ads.zzkr.zzasy
            if (r11 != r0) goto L_0x068f
            if (r2 != 0) goto L_0x0683
            r0 = 1
            goto L_0x0684
        L_0x0683:
            r0 = 0
        L_0x0684:
            com.google.android.gms.internal.ads.zzoz.checkState(r0)
            java.lang.String r2 = "video/3gpp"
        L_0x0689:
            r37 = r1
            r41 = r4
        L_0x068d:
            r4 = 2
            goto L_0x06c7
        L_0x068f:
            int r0 = com.google.android.gms.internal.ads.zzkr.zzaub
            if (r11 != r0) goto L_0x06ac
            if (r2 != 0) goto L_0x0697
            r0 = 1
            goto L_0x0698
        L_0x0697:
            r0 = 0
        L_0x0698:
            com.google.android.gms.internal.ads.zzoz.checkState(r0)
            android.util.Pair r0 = zzb(r13, r8)
            java.lang.Object r2 = r0.first
            java.lang.String r2 = (java.lang.String) r2
            java.lang.Object r0 = r0.second
            byte[] r0 = (byte[]) r0
            java.util.List r23 = java.util.Collections.singletonList(r0)
            goto L_0x0689
        L_0x06ac:
            int r0 = com.google.android.gms.internal.ads.zzkr.zzava
            if (r11 != r0) goto L_0x06cb
            int r8 = r8 + 8
            r13.zzbo(r8)
            int r0 = r13.zzjf()
            int r5 = r13.zzjf()
            float r0 = (float) r0
            float r5 = (float) r5
            float r24 = r0 / r5
            r37 = r1
            r41 = r4
            r4 = 2
            r5 = 1
        L_0x06c7:
            r8 = 1
            r11 = 3
            goto L_0x0730
        L_0x06cb:
            int r0 = com.google.android.gms.internal.ads.zzkr.zzawa
            if (r11 != r0) goto L_0x06fe
            int r0 = r8 + 8
        L_0x06d1:
            int r11 = r0 - r8
            if (r11 >= r10) goto L_0x06f6
            r13.zzbo(r0)
            int r11 = r13.readInt()
            r37 = r1
            int r1 = r13.readInt()
            r41 = r4
            int r4 = com.google.android.gms.internal.ads.zzkr.zzawb
            if (r1 != r4) goto L_0x06f0
            byte[] r1 = r13.data
            int r11 = r11 + r0
            byte[] r8 = java.util.Arrays.copyOfRange(r1, r0, r11)
            goto L_0x06fb
        L_0x06f0:
            int r0 = r0 + r11
            r1 = r37
            r4 = r41
            goto L_0x06d1
        L_0x06f6:
            r37 = r1
            r41 = r4
            r8 = 0
        L_0x06fb:
            r30 = r8
            goto L_0x068d
        L_0x06fe:
            r37 = r1
            r41 = r4
            int r0 = com.google.android.gms.internal.ads.zzkr.zzavz
            if (r11 != r0) goto L_0x068d
            int r0 = r13.readUnsignedByte()
            r11 = 3
            r13.zzbp(r11)
            if (r0 != 0) goto L_0x072e
            int r0 = r13.readUnsignedByte()
            if (r0 == 0) goto L_0x0729
            r8 = 1
            if (r0 == r8) goto L_0x0725
            r4 = 2
            if (r0 == r4) goto L_0x0722
            if (r0 == r11) goto L_0x071f
            goto L_0x0730
        L_0x071f:
            r34 = 3
            goto L_0x0730
        L_0x0722:
            r34 = 2
            goto L_0x0730
        L_0x0725:
            r4 = 2
            r34 = 1
            goto L_0x0730
        L_0x0729:
            r4 = 2
            r8 = 1
            r34 = 0
            goto L_0x0730
        L_0x072e:
            r4 = 2
            r8 = 1
        L_0x0730:
            int r3 = r3 + r10
            r0 = r36
            r1 = r37
            r4 = r41
            r10 = 16
            r11 = 8
            goto L_0x05f0
        L_0x073d:
            r36 = r0
            r4 = 2
            r8 = 1
            r11 = 3
            if (r2 == 0) goto L_0x077f
            java.lang.String r1 = java.lang.Integer.toString(r22)
            r3 = 0
            r0 = -1
            r5 = -1
            r10 = -1082130432(0xffffffffbf800000, float:-1.0)
            r16 = 0
            r25 = 2
            r4 = r0
            r0 = 1
            r8 = r10
            r37 = r9
            r9 = r23
            r23 = 16
            r10 = r28
            r0 = 8
            r35 = 3
            r11 = r24
            r24 = r12
            r12 = r30
            r48 = r50
            r0 = r13
            r25 = 3
            r13 = r34
            r23 = r14
            r30 = 16
            r14 = r16
            r16 = r0
            r0 = r15
            r15 = r53
            com.google.android.gms.internal.ads.zzho r1 = com.google.android.gms.internal.ads.zzho.zza(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15)
            r0.zzahx = r1
            goto L_0x078c
        L_0x077f:
            r48 = r50
            r37 = r9
            r24 = r12
            r16 = r13
            r23 = r14
            r0 = r15
            goto L_0x059e
        L_0x078c:
            int r5 = r24 + r23
            r1 = r16
            r1.zzbo(r5)
            int r6 = r37 + 1
            r15 = r53
            r8 = r0
            r12 = r1
            r14 = r18
            r10 = r19
            r9 = r31
            r11 = r36
            r13 = r48
            r7 = 8
            r0 = r49
            goto L_0x01b4
        L_0x07a9:
            r0 = r8
            r19 = r10
            r48 = r13
            r18 = r14
            r29 = 0
            int r1 = com.google.android.gms.internal.ads.zzkr.zzauh
            r2 = r49
            com.google.android.gms.internal.ads.zzku r1 = r2.zzav(r1)
            if (r1 == 0) goto L_0x0817
            int r2 = com.google.android.gms.internal.ads.zzkr.zzaui
            com.google.android.gms.internal.ads.zzkt r1 = r1.zzau(r2)
            if (r1 != 0) goto L_0x07c5
            goto L_0x0817
        L_0x07c5:
            com.google.android.gms.internal.ads.zzpk r1 = r1.zzawi
            r2 = 8
            r1.zzbo(r2)
            int r2 = r1.readInt()
            int r2 = com.google.android.gms.internal.ads.zzkr.zzaq(r2)
            int r3 = r1.zzjf()
            long[] r4 = new long[r3]
            long[] r5 = new long[r3]
            r9 = 0
        L_0x07dd:
            if (r9 >= r3) goto L_0x0810
            r6 = 1
            if (r2 != r6) goto L_0x07e7
            long r7 = r1.zzjg()
            goto L_0x07eb
        L_0x07e7:
            long r7 = r1.zzjc()
        L_0x07eb:
            r4[r9] = r7
            if (r2 != r6) goto L_0x07f4
            long r7 = r1.readLong()
            goto L_0x07f9
        L_0x07f4:
            int r7 = r1.readInt()
            long r7 = (long) r7
        L_0x07f9:
            r5[r9] = r7
            short r7 = r1.readShort()
            if (r7 != r6) goto L_0x0808
            r7 = 2
            r1.zzbp(r7)
            int r9 = r9 + 1
            goto L_0x07dd
        L_0x0808:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "Unsupported media rate."
            r0.<init>(r1)
            throw r0
        L_0x0810:
            android.util.Pair r1 = android.util.Pair.create(r4, r5)
            r2 = r1
            r1 = 0
            goto L_0x081c
        L_0x0817:
            r1 = 0
            android.util.Pair r2 = android.util.Pair.create(r1, r1)
        L_0x081c:
            com.google.android.gms.internal.ads.zzho r3 = r0.zzahx
            if (r3 != 0) goto L_0x0821
            return r1
        L_0x0821:
            com.google.android.gms.internal.ads.zzln r1 = new com.google.android.gms.internal.ads.zzln
            int r16 = r18.id
            r3 = r48
            java.lang.Object r3 = r3.first
            java.lang.Long r3 = (java.lang.Long) r3
            long r3 = r3.longValue()
            com.google.android.gms.internal.ads.zzho r5 = r0.zzahx
            int r6 = r0.zzaxb
            com.google.android.gms.internal.ads.zzlq[] r7 = r0.zzaxa
            int r0 = r0.zzasr
            java.lang.Object r8 = r2.first
            r28 = r8
            long[] r28 = (long[]) r28
            java.lang.Object r2 = r2.second
            r29 = r2
            long[] r29 = (long[]) r29
            r15 = r1
            r17 = r19
            r18 = r3
            r20 = r32
            r22 = r26
            r24 = r5
            r25 = r6
            r26 = r7
            r27 = r0
            r15.<init>(r16, r17, r18, r20, r22, r24, r25, r26, r27, r28, r29)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzkw.zza(com.google.android.gms.internal.ads.zzku, com.google.android.gms.internal.ads.zzkt, long, com.google.android.gms.internal.ads.zzjn, boolean):com.google.android.gms.internal.ads.zzln");
    }

    public static zzls zza(zzln zzln, zzku zzku, zzjz zzjz) throws zzhv {
        zzky zzky;
        boolean z;
        int i;
        int i2;
        long j;
        int i3;
        int[] iArr;
        int i4;
        long[] jArr;
        int[] iArr2;
        long[] jArr2;
        zzln zzln2;
        int[] iArr3;
        int i5;
        boolean z2;
        long[] jArr3;
        long[] jArr4;
        int[] iArr4;
        int i6;
        int i7;
        int i8;
        int i9;
        zzln zzln3 = zzln;
        zzku zzku2 = zzku;
        zzjz zzjz2 = zzjz;
        zzkt zzau = zzku2.zzau(zzkr.zzavi);
        if (zzau != null) {
            zzky = new zzla(zzau);
        } else {
            zzkt zzau2 = zzku2.zzau(zzkr.zzavj);
            if (zzau2 != null) {
                zzky = new zzkz(zzau2);
            } else {
                throw new zzhv("Track has no sample table size information");
            }
        }
        int zzgy = zzky.zzgy();
        if (zzgy == 0) {
            return new zzls(new long[0], new int[0], 0, new long[0], new int[0]);
        }
        zzkt zzau3 = zzku2.zzau(zzkr.zzavk);
        if (zzau3 == null) {
            zzau3 = zzku2.zzau(zzkr.zzavl);
            z = true;
        } else {
            z = false;
        }
        zzpk zzpk = zzau3.zzawi;
        zzpk zzpk2 = zzku2.zzau(zzkr.zzavh).zzawi;
        zzpk zzpk3 = zzku2.zzau(zzkr.zzave).zzawi;
        zzkt zzau4 = zzku2.zzau(zzkr.zzavf);
        zzpk zzpk4 = null;
        zzpk zzpk5 = zzau4 != null ? zzau4.zzawi : null;
        zzkt zzau5 = zzku2.zzau(zzkr.zzavg);
        zzpk zzpk6 = zzau5 != null ? zzau5.zzawi : null;
        zzkv zzkv = new zzkv(zzpk2, zzpk, z);
        zzpk3.zzbo(12);
        int zzjf = zzpk3.zzjf() - 1;
        int zzjf2 = zzpk3.zzjf();
        int zzjf3 = zzpk3.zzjf();
        if (zzpk6 != null) {
            zzpk6.zzbo(12);
            i = zzpk6.zzjf();
        } else {
            i = 0;
        }
        int i10 = -1;
        if (zzpk5 != null) {
            zzpk5.zzbo(12);
            i2 = zzpk5.zzjf();
            if (i2 > 0) {
                i10 = zzpk5.zzjf() - 1;
                zzpk4 = zzpk5;
            }
        } else {
            zzpk4 = zzpk5;
            i2 = 0;
        }
        long j2 = 0;
        if (!(zzky.zzha() && "audio/raw".equals(zzln3.zzahx.zzahc) && zzjf == 0 && i == 0 && i2 == 0)) {
            long[] jArr5 = new long[zzgy];
            iArr = new int[zzgy];
            jArr = new long[zzgy];
            int i11 = i2;
            iArr2 = new int[zzgy];
            zzpk zzpk7 = zzpk3;
            int i12 = i10;
            long j3 = 0;
            j = 0;
            int i13 = 0;
            i4 = 0;
            int i14 = 0;
            int i15 = 0;
            int i16 = 0;
            int i17 = i;
            int i18 = zzjf3;
            int i19 = zzjf2;
            int i20 = zzjf;
            int i21 = i11;
            while (i13 < zzgy) {
                while (i15 == 0) {
                    zzoz.checkState(zzkv.zzgx());
                    j3 = zzkv.zzawn;
                    i15 = zzkv.zzawm;
                    i18 = i18;
                    i19 = i19;
                }
                int i22 = i19;
                int i23 = i18;
                if (zzpk6 != null) {
                    while (i14 == 0 && i17 > 0) {
                        i14 = zzpk6.zzjf();
                        i16 = zzpk6.readInt();
                        i17--;
                    }
                    i14--;
                }
                int i24 = i16;
                jArr5[i13] = j3;
                iArr[i13] = zzky.zzgz();
                if (iArr[i13] > i4) {
                    i4 = iArr[i13];
                }
                int i25 = zzgy;
                zzky zzky2 = zzky;
                jArr[i13] = j + ((long) i24);
                iArr2[i13] = zzpk4 == null ? 1 : 0;
                if (i13 == i12) {
                    iArr2[i13] = 1;
                    i21--;
                    if (i21 > 0) {
                        i12 = zzpk4.zzjf() - 1;
                    }
                }
                int i26 = i23;
                long[] jArr6 = jArr5;
                j += (long) i26;
                int i27 = i22 - 1;
                if (i27 != 0 || i20 <= 0) {
                    int i28 = i27;
                    i8 = i26;
                    i9 = i28;
                } else {
                    i9 = zzpk7.zzjf();
                    i8 = zzpk7.zzjf();
                    i20--;
                }
                int i29 = i9;
                j3 += (long) iArr[i13];
                i15--;
                i13++;
                zzgy = i25;
                jArr5 = jArr6;
                i12 = i12;
                i16 = i24;
                i19 = i29;
                zzky zzky3 = zzky2;
                i18 = i8;
                zzky = zzky3;
            }
            i3 = zzgy;
            long[] jArr7 = jArr5;
            int i30 = i19;
            zzoz.checkArgument(i14 == 0);
            while (i17 > 0) {
                zzoz.checkArgument(zzpk6.zzjf() == 0);
                zzpk6.readInt();
                i17--;
            }
            if (i21 == 0 && i30 == 0) {
                i7 = i15;
                if (i7 == 0 && i20 == 0) {
                    zzln2 = zzln;
                    jArr2 = jArr7;
                }
            } else {
                i7 = i15;
            }
            zzln2 = zzln;
            int i31 = zzln2.id;
            StringBuilder sb = new StringBuilder(215);
            sb.append("Inconsistent stbl box for track ");
            sb.append(i31);
            sb.append(": remainingSynchronizationSamples ");
            sb.append(i21);
            sb.append(", remainingSamplesAtTimestampDelta ");
            sb.append(i30);
            sb.append(", remainingSamplesInChunk ");
            sb.append(i7);
            sb.append(", remainingTimestampDeltaChanges ");
            sb.append(i20);
            Log.w("AtomParsers", sb.toString());
            jArr2 = jArr7;
        } else {
            zzln2 = zzln3;
            i3 = zzgy;
            zzky zzky4 = zzky;
            long[] jArr8 = new long[zzkv.length];
            int i32 = zzkv.length;
            int[] iArr5 = new int[i32];
            while (zzkv.zzgx()) {
                jArr8[zzkv.index] = zzkv.zzawn;
                iArr5[zzkv.index] = zzkv.zzawm;
            }
            int zzgz = zzky4.zzgz();
            long j4 = (long) zzjf3;
            int i33 = 8192 / zzgz;
            int i34 = 0;
            for (int i35 = 0; i35 < i32; i35++) {
                i34 += zzpq.zzf(iArr5[i35], i33);
            }
            long[] jArr9 = new long[i34];
            int[] iArr6 = new int[i34];
            long[] jArr10 = new long[i34];
            int[] iArr7 = new int[i34];
            int i36 = 0;
            int i37 = 0;
            int i38 = 0;
            int i39 = 0;
            while (i36 < i32) {
                int i40 = iArr5[i36];
                long j5 = jArr8[i36];
                int i41 = i40;
                long[] jArr11 = jArr8;
                int i42 = i41;
                while (i42 > 0) {
                    int min = Math.min(i33, i42);
                    jArr9[i38] = j5;
                    iArr6[i38] = zzgz * min;
                    i39 = Math.max(i39, iArr6[i38]);
                    jArr10[i38] = ((long) i37) * j4;
                    iArr7[i38] = 1;
                    j5 += (long) iArr6[i38];
                    i37 += min;
                    i42 -= min;
                    i38++;
                    i32 = i32;
                    iArr5 = iArr5;
                }
                int i43 = i32;
                int[] iArr8 = iArr5;
                i36++;
                jArr8 = jArr11;
            }
            zzld zzld = new zzld(jArr9, iArr6, i39, jArr10, iArr7);
            jArr2 = zzld.zzaoo;
            iArr = zzld.zzaon;
            i4 = zzld.zzaxi;
            jArr = zzld.zzaxj;
            iArr2 = zzld.zzaxk;
            j = 0;
        }
        if (zzln2.zzbag == null || zzjz.zzgs()) {
            zzpq.zza(jArr, 1000000, zzln2.zzdg);
            return new zzls(jArr2, iArr, i4, jArr, iArr2);
        }
        if (zzln2.zzbag.length == 1 && zzln2.type == 1 && jArr.length >= 2) {
            long j6 = zzln2.zzbah[0];
            long zza = zzpq.zza(zzln2.zzbag[0], zzln2.zzdg, zzln2.zzbad) + j6;
            if (jArr[0] <= j6 && j6 < jArr[1] && jArr[jArr.length - 1] < zza && zza <= j) {
                long j7 = j - zza;
                long zza2 = zzpq.zza(j6 - jArr[0], (long) zzln2.zzahx.zzahn, zzln2.zzdg);
                long zza3 = zzpq.zza(j7, (long) zzln2.zzahx.zzahn, zzln2.zzdg);
                if (!(zza2 == 0 && zza3 == 0) && zza2 <= 2147483647L && zza3 <= 2147483647L) {
                    int i44 = (int) zza2;
                    zzjz zzjz3 = zzjz;
                    zzjz3.zzahp = i44;
                    zzjz3.zzahq = (int) zza3;
                    zzpq.zza(jArr, 1000000, zzln2.zzdg);
                    return new zzls(jArr2, iArr, i4, jArr, iArr2);
                }
            }
        }
        if (zzln2.zzbag.length == 1) {
            char c = 0;
            if (zzln2.zzbag[0] == 0) {
                int i45 = 0;
                while (i45 < jArr.length) {
                    jArr[i45] = zzpq.zza(jArr[i45] - zzln2.zzbah[c], 1000000, zzln2.zzdg);
                    i45++;
                    c = 0;
                }
                return new zzls(jArr2, iArr, i4, jArr, iArr2);
            }
        }
        boolean z3 = zzln2.type == 1;
        boolean z4 = false;
        int i46 = 0;
        int i47 = 0;
        int i48 = 0;
        while (i47 < zzln2.zzbag.length) {
            long j8 = zzln2.zzbah[i47];
            if (j8 != -1) {
                i6 = i4;
                long zza4 = zzpq.zza(zzln2.zzbag[i47], zzln2.zzdg, zzln2.zzbad);
                int zzb = zzpq.zzb(jArr, j8, true, true);
                int zzb2 = zzpq.zzb(jArr, j8 + zza4, z3, false);
                i46 += zzb2 - zzb;
                z4 |= i48 != zzb;
                i48 = zzb2;
            } else {
                i6 = i4;
            }
            i47++;
            i4 = i6;
        }
        int i49 = i4;
        boolean z5 = z4 | (i46 != i3);
        long[] jArr12 = z5 ? new long[i46] : jArr2;
        int[] iArr9 = z5 ? new int[i46] : iArr;
        int i50 = z5 ? 0 : i49;
        int[] iArr10 = z5 ? new int[i46] : iArr2;
        long[] jArr13 = new long[i46];
        int i51 = i50;
        int i52 = 0;
        int i53 = 0;
        while (i52 < zzln2.zzbag.length) {
            long j9 = zzln2.zzbah[i52];
            long j10 = zzln2.zzbag[i52];
            if (j9 != -1) {
                int[] iArr11 = iArr10;
                i5 = i52;
                long j11 = zzln2.zzdg;
                long[] jArr14 = jArr12;
                long[] jArr15 = jArr2;
                int zzb3 = zzpq.zzb(jArr, j9, true, true);
                int zzb4 = zzpq.zzb(jArr, zzpq.zza(j10, j11, zzln2.zzbad) + j9, z3, false);
                if (z5) {
                    int i54 = zzb4 - zzb3;
                    jArr3 = jArr14;
                    jArr4 = jArr15;
                    System.arraycopy(jArr4, zzb3, jArr3, i53, i54);
                    System.arraycopy(iArr, zzb3, iArr9, i53, i54);
                    z2 = z3;
                    iArr4 = iArr11;
                    System.arraycopy(iArr2, zzb3, iArr4, i53, i54);
                } else {
                    jArr3 = jArr14;
                    z2 = z3;
                    iArr4 = iArr11;
                    jArr4 = jArr15;
                }
                int i55 = i51;
                while (zzb3 < zzb4) {
                    int[] iArr12 = iArr4;
                    int i56 = zzb4;
                    long j12 = j9;
                    jArr13[i53] = zzpq.zza(j2, 1000000, zzln2.zzbad) + zzpq.zza(jArr[zzb3] - j9, 1000000, zzln2.zzdg);
                    if (z5 && iArr9[i53] > i55) {
                        i55 = iArr[zzb3];
                    }
                    i53++;
                    zzb3++;
                    j9 = j12;
                    zzb4 = i56;
                    iArr4 = iArr12;
                }
                iArr3 = iArr4;
                i51 = i55;
            } else {
                z2 = z3;
                jArr3 = jArr12;
                iArr3 = iArr10;
                i5 = i52;
                jArr4 = jArr2;
            }
            j2 += j10;
            i52 = i5 + 1;
            jArr2 = jArr4;
            jArr12 = jArr3;
            z3 = z2;
            iArr10 = iArr3;
        }
        long[] jArr16 = jArr12;
        int[] iArr13 = iArr10;
        boolean z6 = false;
        for (int i57 = 0; i57 < iArr13.length && !z6; i57++) {
            z6 |= (iArr13[i57] & 1) != 0;
        }
        if (z6) {
            return new zzls(jArr16, iArr9, i51, jArr13, iArr13);
        }
        throw new zzhv("The edited sample sequence does not contain a sync sample.");
    }

    public static zzmc zza(zzkt zzkt, boolean z) {
        if (z) {
            return null;
        }
        zzpk zzpk = zzkt.zzawi;
        zzpk.zzbo(8);
        while (zzpk.zzja() >= 8) {
            int position = zzpk.getPosition();
            int readInt = zzpk.readInt();
            if (zzpk.readInt() == zzkr.zzavt) {
                zzpk.zzbo(position);
                int i = position + readInt;
                zzpk.zzbp(12);
                while (true) {
                    if (zzpk.getPosition() >= i) {
                        break;
                    }
                    int position2 = zzpk.getPosition();
                    int readInt2 = zzpk.readInt();
                    if (zzpk.readInt() == zzkr.zzavu) {
                        zzpk.zzbo(position2);
                        int i2 = position2 + readInt2;
                        zzpk.zzbp(8);
                        ArrayList arrayList = new ArrayList();
                        while (zzpk.getPosition() < i2) {
                            zzmc.zza zzd = zzlk.zzd(zzpk);
                            if (zzd != null) {
                                arrayList.add(zzd);
                            }
                        }
                        if (!arrayList.isEmpty()) {
                            return new zzmc((List<? extends zzmc.zza>) arrayList);
                        }
                    } else {
                        zzpk.zzbp(readInt2 - 8);
                    }
                }
                return null;
            }
            zzpk.zzbp(readInt - 8);
        }
        return null;
    }

    private static Pair<String, byte[]> zzb(zzpk zzpk, int i) {
        zzpk.zzbo(i + 8 + 4);
        zzpk.zzbp(1);
        zzc(zzpk);
        zzpk.zzbp(2);
        int readUnsignedByte = zzpk.readUnsignedByte();
        if ((readUnsignedByte & 128) != 0) {
            zzpk.zzbp(2);
        }
        if ((readUnsignedByte & 64) != 0) {
            zzpk.zzbp(zzpk.readUnsignedShort());
        }
        if ((readUnsignedByte & 32) != 0) {
            zzpk.zzbp(2);
        }
        zzpk.zzbp(1);
        zzc(zzpk);
        int readUnsignedByte2 = zzpk.readUnsignedByte();
        String str = null;
        if (readUnsignedByte2 == 32) {
            str = "video/mp4v-es";
        } else if (readUnsignedByte2 == 33) {
            str = "video/avc";
        } else if (readUnsignedByte2 != 35) {
            if (readUnsignedByte2 != 64) {
                if (readUnsignedByte2 == 107) {
                    return Pair.create("audio/mpeg", (Object) null);
                }
                if (readUnsignedByte2 == 165) {
                    str = "audio/ac3";
                } else if (readUnsignedByte2 != 166) {
                    switch (readUnsignedByte2) {
                        case 102:
                        case 103:
                        case 104:
                            break;
                        default:
                            switch (readUnsignedByte2) {
                                case 169:
                                case 172:
                                    return Pair.create("audio/vnd.dts", (Object) null);
                                case 170:
                                case 171:
                                    return Pair.create("audio/vnd.dts.hd", (Object) null);
                            }
                    }
                } else {
                    str = "audio/eac3";
                }
            }
            str = "audio/mp4a-latm";
        } else {
            str = "video/hevc";
        }
        zzpk.zzbp(12);
        zzpk.zzbp(1);
        int zzc = zzc(zzpk);
        byte[] bArr = new byte[zzc];
        zzpk.zze(bArr, 0, zzc);
        return Pair.create(str, bArr);
    }

    private static int zza(zzpk zzpk, int i, int i2, zzkx zzkx, int i3) {
        zzpk zzpk2 = zzpk;
        int position = zzpk.getPosition();
        while (true) {
            boolean z = false;
            if (position - i >= i2) {
                return 0;
            }
            zzpk.zzbo(position);
            int readInt = zzpk.readInt();
            zzoz.checkArgument(readInt > 0, "childAtomSize should be positive");
            if (zzpk.readInt() == zzkr.zzaun) {
                int i4 = position + 8;
                Pair pair = null;
                Integer num = null;
                zzlq zzlq = null;
                boolean z2 = false;
                while (i4 - position < readInt) {
                    zzpk.zzbo(i4);
                    int readInt2 = zzpk.readInt();
                    int readInt3 = zzpk.readInt();
                    if (readInt3 == zzkr.zzaut) {
                        num = Integer.valueOf(zzpk.readInt());
                    } else if (readInt3 == zzkr.zzauo) {
                        zzpk.zzbp(4);
                        z2 = zzpk.readInt() == zzawz;
                    } else if (readInt3 == zzkr.zzaup) {
                        int i5 = i4 + 8;
                        while (true) {
                            if (i5 - i4 >= readInt2) {
                                zzlq = null;
                                break;
                            }
                            zzpk.zzbo(i5);
                            int readInt4 = zzpk.readInt();
                            if (zzpk.readInt() == zzkr.zzauq) {
                                zzpk.zzbp(6);
                                boolean z3 = zzpk.readUnsignedByte() == 1;
                                int readUnsignedByte = zzpk.readUnsignedByte();
                                byte[] bArr = new byte[16];
                                zzpk.zze(bArr, 0, 16);
                                zzlq = new zzlq(z3, readUnsignedByte, bArr);
                            } else {
                                i5 += readInt4;
                            }
                        }
                    }
                    i4 += readInt2;
                }
                if (z2) {
                    zzoz.checkArgument(num != null, "frma atom is mandatory");
                    if (zzlq != null) {
                        z = true;
                    }
                    zzoz.checkArgument(z, "schi->tenc atom is mandatory");
                    pair = Pair.create(num, zzlq);
                }
                if (pair != null) {
                    zzkx.zzaxa[i3] = (zzlq) pair.second;
                    return ((Integer) pair.first).intValue();
                }
            }
            zzkx zzkx2 = zzkx;
            position += readInt;
        }
    }

    private static int zzc(zzpk zzpk) {
        int readUnsignedByte = zzpk.readUnsignedByte();
        int i = readUnsignedByte & 127;
        while ((readUnsignedByte & 128) == 128) {
            readUnsignedByte = zzpk.readUnsignedByte();
            i = (i << 7) | (readUnsignedByte & 127);
        }
        return i;
    }
}
