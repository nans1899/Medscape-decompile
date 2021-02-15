package com.fasterxml.jackson.core.io;

import com.fasterxml.jackson.core.util.ByteArrayBuilder;
import com.fasterxml.jackson.core.util.TextBuffer;
import java.lang.ref.SoftReference;

public final class JsonStringEncoder {
    private static final byte[] HB = CharTypes.copyHexBytes();
    private static final char[] HC = CharTypes.copyHexChars();
    private static final int SURR1_FIRST = 55296;
    private static final int SURR1_LAST = 56319;
    private static final int SURR2_FIRST = 56320;
    private static final int SURR2_LAST = 57343;
    protected static final ThreadLocal<SoftReference<JsonStringEncoder>> _threadEncoder = new ThreadLocal<>();
    protected ByteArrayBuilder _bytes;
    protected final char[] _qbuf;
    protected TextBuffer _text;

    public JsonStringEncoder() {
        char[] cArr = new char[6];
        this._qbuf = cArr;
        cArr[0] = '\\';
        cArr[2] = '0';
        cArr[3] = '0';
    }

    public static JsonStringEncoder getInstance() {
        JsonStringEncoder jsonStringEncoder;
        SoftReference softReference = _threadEncoder.get();
        if (softReference == null) {
            jsonStringEncoder = null;
        } else {
            jsonStringEncoder = (JsonStringEncoder) softReference.get();
        }
        if (jsonStringEncoder != null) {
            return jsonStringEncoder;
        }
        JsonStringEncoder jsonStringEncoder2 = new JsonStringEncoder();
        _threadEncoder.set(new SoftReference(jsonStringEncoder2));
        return jsonStringEncoder2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0030, code lost:
        if (r9 >= 0) goto L_0x0039;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0032, code lost:
        r6 = _appendNumeric(r6, r11._qbuf);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0039, code lost:
        r6 = _appendNamed(r9, r11._qbuf);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x003f, code lost:
        r9 = r7 + r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0042, code lost:
        if (r9 <= r1.length) goto L_0x0059;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0044, code lost:
        r9 = r1.length - r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0046, code lost:
        if (r9 <= 0) goto L_0x004d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0048, code lost:
        java.lang.System.arraycopy(r11._qbuf, 0, r1, r7, r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x004d, code lost:
        r1 = r0.finishCurrentSegment();
        r6 = r6 - r9;
        java.lang.System.arraycopy(r11._qbuf, r9, r1, 0, r6);
        r7 = r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0059, code lost:
        java.lang.System.arraycopy(r11._qbuf, 0, r1, r7, r6);
        r7 = r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0028, code lost:
        r8 = r6 + 1;
        r6 = r12.charAt(r6);
        r9 = r2[r6];
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public char[] quoteAsString(java.lang.String r12) {
        /*
            r11 = this;
            com.fasterxml.jackson.core.util.TextBuffer r0 = r11._text
            if (r0 != 0) goto L_0x000c
            com.fasterxml.jackson.core.util.TextBuffer r0 = new com.fasterxml.jackson.core.util.TextBuffer
            r1 = 0
            r0.<init>(r1)
            r11._text = r0
        L_0x000c:
            char[] r1 = r0.emptyAndGetCurrentSegment()
            int[] r2 = com.fasterxml.jackson.core.io.CharTypes.get7BitOutputEscapes()
            int r3 = r2.length
            int r4 = r12.length()
            r5 = 0
            r6 = 0
            r7 = 0
        L_0x001c:
            if (r6 >= r4) goto L_0x0075
        L_0x001e:
            char r8 = r12.charAt(r6)
            if (r8 >= r3) goto L_0x0061
            r9 = r2[r8]
            if (r9 == 0) goto L_0x0061
            int r8 = r6 + 1
            char r6 = r12.charAt(r6)
            r9 = r2[r6]
            if (r9 >= 0) goto L_0x0039
            char[] r9 = r11._qbuf
            int r6 = r11._appendNumeric(r6, r9)
            goto L_0x003f
        L_0x0039:
            char[] r6 = r11._qbuf
            int r6 = r11._appendNamed(r9, r6)
        L_0x003f:
            int r9 = r7 + r6
            int r10 = r1.length
            if (r9 <= r10) goto L_0x0059
            int r9 = r1.length
            int r9 = r9 - r7
            if (r9 <= 0) goto L_0x004d
            char[] r10 = r11._qbuf
            java.lang.System.arraycopy(r10, r5, r1, r7, r9)
        L_0x004d:
            char[] r1 = r0.finishCurrentSegment()
            int r6 = r6 - r9
            char[] r7 = r11._qbuf
            java.lang.System.arraycopy(r7, r9, r1, r5, r6)
            r7 = r6
            goto L_0x005f
        L_0x0059:
            char[] r10 = r11._qbuf
            java.lang.System.arraycopy(r10, r5, r1, r7, r6)
            r7 = r9
        L_0x005f:
            r6 = r8
            goto L_0x001c
        L_0x0061:
            int r9 = r1.length
            if (r7 < r9) goto L_0x0069
            char[] r1 = r0.finishCurrentSegment()
            r7 = 0
        L_0x0069:
            int r9 = r7 + 1
            r1[r7] = r8
            int r6 = r6 + 1
            if (r6 < r4) goto L_0x0073
            r7 = r9
            goto L_0x0075
        L_0x0073:
            r7 = r9
            goto L_0x001e
        L_0x0075:
            r0.setCurrentLength(r7)
            char[] r12 = r0.contentsAsArray()
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.io.JsonStringEncoder.quoteAsString(java.lang.String):char[]");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0041, code lost:
        if (r5 < r2.length) goto L_0x0048;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0043, code lost:
        r2 = r0.finishCurrentSegment();
        r5 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0048, code lost:
        r7 = r4 + 1;
        r4 = r11.charAt(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x004e, code lost:
        if (r4 > 127) goto L_0x005c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0050, code lost:
        r5 = _appendByte(r4, r6[r4], r0, r5);
        r2 = r0.getCurrentSegment();
        r4 = r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x005e, code lost:
        if (r4 > 2047) goto L_0x0071;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0060, code lost:
        r6 = r5 + 1;
        r2[r5] = (byte) ((r4 >> 6) | 192);
        r4 = (r4 & '?') | 128;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x006d, code lost:
        r5 = r4;
        r4 = r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0074, code lost:
        if (r4 < 55296) goto L_0x00d2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0079, code lost:
        if (r4 <= 57343) goto L_0x007c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x007f, code lost:
        if (r4 <= 56319) goto L_0x0084;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0081, code lost:
        _illegal(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0084, code lost:
        if (r7 < r1) goto L_0x0089;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0086, code lost:
        _illegal(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0089, code lost:
        r6 = r7 + 1;
        r4 = _convert(r4, r11.charAt(r7));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0096, code lost:
        if (r4 <= 1114111) goto L_0x009b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0098, code lost:
        _illegal(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x009b, code lost:
        r7 = r5 + 1;
        r2[r5] = (byte) ((r4 >> 18) | org.jetbrains.anko.DimensionsKt.HDPI);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00a5, code lost:
        if (r7 < r2.length) goto L_0x00ac;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00a7, code lost:
        r2 = r0.finishCurrentSegment();
        r7 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00ac, code lost:
        r5 = r7 + 1;
        r2[r7] = (byte) (((r4 >> 12) & 63) | 128);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x00b8, code lost:
        if (r5 < r2.length) goto L_0x00bf;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00ba, code lost:
        r2 = r0.finishCurrentSegment();
        r5 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00bf, code lost:
        r7 = r5 + 1;
        r2[r5] = (byte) (((r4 >> 6) & 63) | 128);
        r5 = (r4 & '?') | 128;
        r4 = r6;
        r6 = r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00d2, code lost:
        r6 = r5 + 1;
        r2[r5] = (byte) ((r4 >> 12) | 224);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00dc, code lost:
        if (r6 < r2.length) goto L_0x00e3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00de, code lost:
        r2 = r0.finishCurrentSegment();
        r6 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00e3, code lost:
        r2[r6] = (byte) (((r4 >> 6) & 63) | 128);
        r4 = (r4 & '?') | 128;
        r6 = r6 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x00f6, code lost:
        if (r6 < r2.length) goto L_0x00fd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x00f8, code lost:
        r2 = r0.finishCurrentSegment();
        r6 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x00fd, code lost:
        r2[r6] = (byte) r5;
        r5 = r6 + 1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public byte[] quoteAsUTF8(java.lang.String r11) {
        /*
            r10 = this;
            com.fasterxml.jackson.core.util.ByteArrayBuilder r0 = r10._bytes
            if (r0 != 0) goto L_0x000c
            com.fasterxml.jackson.core.util.ByteArrayBuilder r0 = new com.fasterxml.jackson.core.util.ByteArrayBuilder
            r1 = 0
            r0.<init>((com.fasterxml.jackson.core.util.BufferRecycler) r1)
            r10._bytes = r0
        L_0x000c:
            int r1 = r11.length()
            byte[] r2 = r0.resetAndGetFirstSegment()
            r3 = 0
            r4 = 0
            r5 = 0
        L_0x0017:
            if (r4 >= r1) goto L_0x0105
            int[] r6 = com.fasterxml.jackson.core.io.CharTypes.get7BitOutputEscapes()
        L_0x001d:
            char r7 = r11.charAt(r4)
            r8 = 127(0x7f, float:1.78E-43)
            if (r7 > r8) goto L_0x0040
            r9 = r6[r7]
            if (r9 == 0) goto L_0x002a
            goto L_0x0040
        L_0x002a:
            int r8 = r2.length
            if (r5 < r8) goto L_0x0032
            byte[] r2 = r0.finishCurrentSegment()
            r5 = 0
        L_0x0032:
            int r8 = r5 + 1
            byte r7 = (byte) r7
            r2[r5] = r7
            int r4 = r4 + 1
            if (r4 < r1) goto L_0x003e
            r5 = r8
            goto L_0x0105
        L_0x003e:
            r5 = r8
            goto L_0x001d
        L_0x0040:
            int r7 = r2.length
            if (r5 < r7) goto L_0x0048
            byte[] r2 = r0.finishCurrentSegment()
            r5 = 0
        L_0x0048:
            int r7 = r4 + 1
            char r4 = r11.charAt(r4)
            if (r4 > r8) goto L_0x005c
            r2 = r6[r4]
            int r5 = r10._appendByte(r4, r2, r0, r5)
            byte[] r2 = r0.getCurrentSegment()
            r4 = r7
            goto L_0x0017
        L_0x005c:
            r6 = 2047(0x7ff, float:2.868E-42)
            if (r4 > r6) goto L_0x0071
            int r6 = r5 + 1
            int r8 = r4 >> 6
            r8 = r8 | 192(0xc0, float:2.69E-43)
            byte r8 = (byte) r8
            r2[r5] = r8
            r4 = r4 & 63
            r4 = r4 | 128(0x80, float:1.794E-43)
        L_0x006d:
            r5 = r4
            r4 = r7
            goto L_0x00f5
        L_0x0071:
            r6 = 55296(0xd800, float:7.7486E-41)
            if (r4 < r6) goto L_0x00d2
            r6 = 57343(0xdfff, float:8.0355E-41)
            if (r4 <= r6) goto L_0x007c
            goto L_0x00d2
        L_0x007c:
            r6 = 56319(0xdbff, float:7.892E-41)
            if (r4 <= r6) goto L_0x0084
            _illegal(r4)
        L_0x0084:
            if (r7 < r1) goto L_0x0089
            _illegal(r4)
        L_0x0089:
            int r6 = r7 + 1
            char r7 = r11.charAt(r7)
            int r4 = _convert(r4, r7)
            r7 = 1114111(0x10ffff, float:1.561202E-39)
            if (r4 <= r7) goto L_0x009b
            _illegal(r4)
        L_0x009b:
            int r7 = r5 + 1
            int r8 = r4 >> 18
            r8 = r8 | 240(0xf0, float:3.36E-43)
            byte r8 = (byte) r8
            r2[r5] = r8
            int r5 = r2.length
            if (r7 < r5) goto L_0x00ac
            byte[] r2 = r0.finishCurrentSegment()
            r7 = 0
        L_0x00ac:
            int r5 = r7 + 1
            int r8 = r4 >> 12
            r8 = r8 & 63
            r8 = r8 | 128(0x80, float:1.794E-43)
            byte r8 = (byte) r8
            r2[r7] = r8
            int r7 = r2.length
            if (r5 < r7) goto L_0x00bf
            byte[] r2 = r0.finishCurrentSegment()
            r5 = 0
        L_0x00bf:
            int r7 = r5 + 1
            int r8 = r4 >> 6
            r8 = r8 & 63
            r8 = r8 | 128(0x80, float:1.794E-43)
            byte r8 = (byte) r8
            r2[r5] = r8
            r4 = r4 & 63
            r4 = r4 | 128(0x80, float:1.794E-43)
            r5 = r4
            r4 = r6
            r6 = r7
            goto L_0x00f5
        L_0x00d2:
            int r6 = r5 + 1
            int r8 = r4 >> 12
            r8 = r8 | 224(0xe0, float:3.14E-43)
            byte r8 = (byte) r8
            r2[r5] = r8
            int r5 = r2.length
            if (r6 < r5) goto L_0x00e3
            byte[] r2 = r0.finishCurrentSegment()
            r6 = 0
        L_0x00e3:
            int r5 = r6 + 1
            int r8 = r4 >> 6
            r8 = r8 & 63
            r8 = r8 | 128(0x80, float:1.794E-43)
            byte r8 = (byte) r8
            r2[r6] = r8
            r4 = r4 & 63
            r4 = r4 | 128(0x80, float:1.794E-43)
            r6 = r5
            goto L_0x006d
        L_0x00f5:
            int r7 = r2.length
            if (r6 < r7) goto L_0x00fd
            byte[] r2 = r0.finishCurrentSegment()
            r6 = 0
        L_0x00fd:
            int r7 = r6 + 1
            byte r5 = (byte) r5
            r2[r6] = r5
            r5 = r7
            goto L_0x0017
        L_0x0105:
            com.fasterxml.jackson.core.util.ByteArrayBuilder r11 = r10._bytes
            byte[] r11 = r11.completeAndCoalesce(r5)
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.io.JsonStringEncoder.quoteAsUTF8(java.lang.String):byte[]");
    }

    /* JADX WARNING: Removed duplicated region for block: B:44:0x00d6  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x00dc A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public byte[] encodeAsUTF8(java.lang.String r11) {
        /*
            r10 = this;
            com.fasterxml.jackson.core.util.ByteArrayBuilder r0 = r10._bytes
            if (r0 != 0) goto L_0x000c
            com.fasterxml.jackson.core.util.ByteArrayBuilder r0 = new com.fasterxml.jackson.core.util.ByteArrayBuilder
            r1 = 0
            r0.<init>((com.fasterxml.jackson.core.util.BufferRecycler) r1)
            r10._bytes = r0
        L_0x000c:
            int r1 = r11.length()
            byte[] r2 = r0.resetAndGetFirstSegment()
            int r3 = r2.length
            r4 = 0
            r5 = 0
            r6 = 0
        L_0x0018:
            if (r5 >= r1) goto L_0x00e8
            int r7 = r5 + 1
            char r5 = r11.charAt(r5)
        L_0x0020:
            r8 = 127(0x7f, float:1.78E-43)
            if (r5 > r8) goto L_0x0040
            if (r6 < r3) goto L_0x002c
            byte[] r2 = r0.finishCurrentSegment()
            int r3 = r2.length
            r6 = 0
        L_0x002c:
            int r8 = r6 + 1
            byte r5 = (byte) r5
            r2[r6] = r5
            if (r7 < r1) goto L_0x0036
            r6 = r8
            goto L_0x00e8
        L_0x0036:
            int r5 = r7 + 1
            char r6 = r11.charAt(r7)
            r7 = r5
            r5 = r6
            r6 = r8
            goto L_0x0020
        L_0x0040:
            if (r6 < r3) goto L_0x0048
            byte[] r2 = r0.finishCurrentSegment()
            int r3 = r2.length
            r6 = 0
        L_0x0048:
            r8 = 2048(0x800, float:2.87E-42)
            if (r5 >= r8) goto L_0x0059
            int r8 = r6 + 1
            int r9 = r5 >> 6
            r9 = r9 | 192(0xc0, float:2.69E-43)
            byte r9 = (byte) r9
            r2[r6] = r9
        L_0x0055:
            r6 = r5
            r5 = r7
            goto L_0x00d4
        L_0x0059:
            r8 = 55296(0xd800, float:7.7486E-41)
            if (r5 < r8) goto L_0x00b6
            r8 = 57343(0xdfff, float:8.0355E-41)
            if (r5 <= r8) goto L_0x0064
            goto L_0x00b6
        L_0x0064:
            r8 = 56319(0xdbff, float:7.892E-41)
            if (r5 <= r8) goto L_0x006c
            _illegal(r5)
        L_0x006c:
            if (r7 < r1) goto L_0x0071
            _illegal(r5)
        L_0x0071:
            int r8 = r7 + 1
            char r7 = r11.charAt(r7)
            int r5 = _convert(r5, r7)
            r7 = 1114111(0x10ffff, float:1.561202E-39)
            if (r5 <= r7) goto L_0x0083
            _illegal(r5)
        L_0x0083:
            int r7 = r6 + 1
            int r9 = r5 >> 18
            r9 = r9 | 240(0xf0, float:3.36E-43)
            byte r9 = (byte) r9
            r2[r6] = r9
            if (r7 < r3) goto L_0x0094
            byte[] r2 = r0.finishCurrentSegment()
            int r3 = r2.length
            r7 = 0
        L_0x0094:
            int r6 = r7 + 1
            int r9 = r5 >> 12
            r9 = r9 & 63
            r9 = r9 | 128(0x80, float:1.794E-43)
            byte r9 = (byte) r9
            r2[r7] = r9
            if (r6 < r3) goto L_0x00a7
            byte[] r2 = r0.finishCurrentSegment()
            int r3 = r2.length
            r6 = 0
        L_0x00a7:
            int r7 = r6 + 1
            int r9 = r5 >> 6
            r9 = r9 & 63
            r9 = r9 | 128(0x80, float:1.794E-43)
            byte r9 = (byte) r9
            r2[r6] = r9
            r6 = r5
            r5 = r8
            r8 = r7
            goto L_0x00d4
        L_0x00b6:
            int r8 = r6 + 1
            int r9 = r5 >> 12
            r9 = r9 | 224(0xe0, float:3.14E-43)
            byte r9 = (byte) r9
            r2[r6] = r9
            if (r8 < r3) goto L_0x00c7
            byte[] r2 = r0.finishCurrentSegment()
            int r3 = r2.length
            r8 = 0
        L_0x00c7:
            int r6 = r8 + 1
            int r9 = r5 >> 6
            r9 = r9 & 63
            r9 = r9 | 128(0x80, float:1.794E-43)
            byte r9 = (byte) r9
            r2[r8] = r9
            r8 = r6
            goto L_0x0055
        L_0x00d4:
            if (r8 < r3) goto L_0x00dc
            byte[] r2 = r0.finishCurrentSegment()
            int r3 = r2.length
            r8 = 0
        L_0x00dc:
            int r7 = r8 + 1
            r6 = r6 & 63
            r6 = r6 | 128(0x80, float:1.794E-43)
            byte r6 = (byte) r6
            r2[r8] = r6
            r6 = r7
            goto L_0x0018
        L_0x00e8:
            com.fasterxml.jackson.core.util.ByteArrayBuilder r11 = r10._bytes
            byte[] r11 = r11.completeAndCoalesce(r6)
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.io.JsonStringEncoder.encodeAsUTF8(java.lang.String):byte[]");
    }

    private int _appendNumeric(int i, char[] cArr) {
        cArr[1] = 'u';
        char[] cArr2 = HC;
        cArr[4] = cArr2[i >> 4];
        cArr[5] = cArr2[i & 15];
        return 6;
    }

    private int _appendNamed(int i, char[] cArr) {
        cArr[1] = (char) i;
        return 2;
    }

    private int _appendByte(int i, int i2, ByteArrayBuilder byteArrayBuilder, int i3) {
        byteArrayBuilder.setCurrentSegmentLength(i3);
        byteArrayBuilder.append(92);
        if (i2 < 0) {
            byteArrayBuilder.append(117);
            if (i > 255) {
                int i4 = i >> 8;
                byteArrayBuilder.append(HB[i4 >> 4]);
                byteArrayBuilder.append(HB[i4 & 15]);
                i &= 255;
            } else {
                byteArrayBuilder.append(48);
                byteArrayBuilder.append(48);
            }
            byteArrayBuilder.append(HB[i >> 4]);
            byteArrayBuilder.append(HB[i & 15]);
        } else {
            byteArrayBuilder.append((byte) i2);
        }
        return byteArrayBuilder.getCurrentSegmentLength();
    }

    private static int _convert(int i, int i2) {
        if (i2 >= 56320 && i2 <= 57343) {
            return ((i - 55296) << 10) + 65536 + (i2 - 56320);
        }
        throw new IllegalArgumentException("Broken surrogate pair: first char 0x" + Integer.toHexString(i) + ", second 0x" + Integer.toHexString(i2) + "; illegal combination");
    }

    private static void _illegal(int i) {
        throw new IllegalArgumentException(UTF8Writer.illegalSurrogateDesc(i));
    }
}
