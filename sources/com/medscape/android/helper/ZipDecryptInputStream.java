package com.medscape.android.helper;

import java.io.IOException;
import java.io.InputStream;

public class ZipDecryptInputStream extends InputStream {
    private static final int[] CRC_TABLE = new int[256];
    private static final int DECRYPT_HEADER_SIZE = 12;
    private static final int[] LFH_SIGNATURE = {80, 75, 3, 4};
    private int compressedSize;
    private final InputStream delegate;
    private final int[] keys = new int[3];
    private final String password;
    private int skipBytes;
    private State state = State.SIGNATURE;
    private int value;
    private int valueInc;
    private int valuePos;

    private enum State {
        SIGNATURE,
        FLAGS,
        COMPRESSED_SIZE,
        FN_LENGTH,
        EF_LENGTH,
        HEADER,
        DATA,
        TAIL
    }

    static {
        for (int i = 0; i < 256; i++) {
            int i2 = i;
            for (int i3 = 0; i3 < 8; i3++) {
                i2 = (i2 & 1) == 1 ? (i2 >>> 1) ^ -306674912 : i2 >>> 1;
            }
            CRC_TABLE[i] = i2;
        }
    }

    public ZipDecryptInputStream(InputStream inputStream, String str) {
        this.delegate = inputStream;
        this.password = str;
    }

    public int read() throws IOException {
        int read = this.delegate.read();
        int i = this.skipBytes;
        if (i == 0) {
            switch (AnonymousClass1.$SwitchMap$com$medscape$android$helper$ZipDecryptInputStream$State[this.state.ordinal()]) {
                case 1:
                    int[] iArr = LFH_SIGNATURE;
                    int i2 = this.valuePos;
                    if (read != iArr[i2]) {
                        this.state = State.TAIL;
                        return read;
                    }
                    int i3 = i2 + 1;
                    this.valuePos = i3;
                    if (i3 < iArr.length) {
                        return read;
                    }
                    this.skipBytes = 2;
                    this.state = State.FLAGS;
                    return read;
                case 2:
                    if ((read & 1) == 0) {
                        throw new IllegalStateException("ZIP not password protected.");
                    } else if ((read & 64) == 64) {
                        throw new IllegalStateException("Strong encryption used.");
                    } else if ((read & 8) != 8) {
                        int i4 = read - 1;
                        this.compressedSize = 0;
                        this.valuePos = 0;
                        this.valueInc = 12;
                        this.state = State.COMPRESSED_SIZE;
                        this.skipBytes = 11;
                        return i4;
                    } else {
                        throw new IllegalStateException("Unsupported ZIP format.");
                    }
                case 3:
                    this.compressedSize += read << (this.valuePos * 8);
                    int i5 = read - this.valueInc;
                    if (i5 < 0) {
                        this.valueInc = 1;
                        i5 += 256;
                    } else {
                        this.valueInc = 0;
                    }
                    int i6 = this.valuePos + 1;
                    this.valuePos = i6;
                    if (i6 <= 3) {
                        return i5;
                    }
                    this.valuePos = 0;
                    this.value = 0;
                    this.state = State.FN_LENGTH;
                    this.skipBytes = 4;
                    return i5;
                case 4:
                case 5:
                    int i7 = this.value;
                    int i8 = this.valuePos;
                    this.value = i7 + (read << (i8 * 8));
                    if (i8 == 1) {
                        this.valuePos = 0;
                        if (this.state == State.FN_LENGTH) {
                            this.state = State.EF_LENGTH;
                            return read;
                        }
                        this.state = State.HEADER;
                        this.skipBytes = this.value;
                        return read;
                    }
                    this.valuePos = 1;
                    return read;
                case 6:
                    initKeys(this.password);
                    for (int i9 = 0; i9 < 12; i9++) {
                        updateKeys((byte) (read ^ decryptByte()));
                        read = this.delegate.read();
                    }
                    this.compressedSize -= 12;
                    this.state = State.DATA;
                    break;
                case 7:
                    break;
                default:
                    return read;
            }
            byte decryptByte = (read ^ decryptByte()) & 255;
            updateKeys((byte) decryptByte);
            int i10 = this.compressedSize - 1;
            this.compressedSize = i10;
            if (i10 != 0) {
                return decryptByte;
            }
            this.valuePos = 0;
            this.state = State.SIGNATURE;
            return decryptByte;
        }
        this.skipBytes = i - 1;
        return read;
    }

    /* renamed from: com.medscape.android.helper.ZipDecryptInputStream$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$medscape$android$helper$ZipDecryptInputStream$State;

        /* JADX WARNING: Can't wrap try/catch for region: R(18:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|18) */
        /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0049 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0054 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                com.medscape.android.helper.ZipDecryptInputStream$State[] r0 = com.medscape.android.helper.ZipDecryptInputStream.State.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$medscape$android$helper$ZipDecryptInputStream$State = r0
                com.medscape.android.helper.ZipDecryptInputStream$State r1 = com.medscape.android.helper.ZipDecryptInputStream.State.SIGNATURE     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$medscape$android$helper$ZipDecryptInputStream$State     // Catch:{ NoSuchFieldError -> 0x001d }
                com.medscape.android.helper.ZipDecryptInputStream$State r1 = com.medscape.android.helper.ZipDecryptInputStream.State.FLAGS     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$medscape$android$helper$ZipDecryptInputStream$State     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.medscape.android.helper.ZipDecryptInputStream$State r1 = com.medscape.android.helper.ZipDecryptInputStream.State.COMPRESSED_SIZE     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$medscape$android$helper$ZipDecryptInputStream$State     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.medscape.android.helper.ZipDecryptInputStream$State r1 = com.medscape.android.helper.ZipDecryptInputStream.State.FN_LENGTH     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$com$medscape$android$helper$ZipDecryptInputStream$State     // Catch:{ NoSuchFieldError -> 0x003e }
                com.medscape.android.helper.ZipDecryptInputStream$State r1 = com.medscape.android.helper.ZipDecryptInputStream.State.EF_LENGTH     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$com$medscape$android$helper$ZipDecryptInputStream$State     // Catch:{ NoSuchFieldError -> 0x0049 }
                com.medscape.android.helper.ZipDecryptInputStream$State r1 = com.medscape.android.helper.ZipDecryptInputStream.State.HEADER     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                int[] r0 = $SwitchMap$com$medscape$android$helper$ZipDecryptInputStream$State     // Catch:{ NoSuchFieldError -> 0x0054 }
                com.medscape.android.helper.ZipDecryptInputStream$State r1 = com.medscape.android.helper.ZipDecryptInputStream.State.DATA     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                int[] r0 = $SwitchMap$com$medscape$android$helper$ZipDecryptInputStream$State     // Catch:{ NoSuchFieldError -> 0x0060 }
                com.medscape.android.helper.ZipDecryptInputStream$State r1 = com.medscape.android.helper.ZipDecryptInputStream.State.TAIL     // Catch:{ NoSuchFieldError -> 0x0060 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0060 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0060 }
            L_0x0060:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.medscape.android.helper.ZipDecryptInputStream.AnonymousClass1.<clinit>():void");
        }
    }

    public void close() throws IOException {
        this.delegate.close();
        super.close();
    }

    private void initKeys(String str) {
        int[] iArr = this.keys;
        iArr[0] = 305419896;
        iArr[1] = 591751049;
        iArr[2] = 878082192;
        for (int i = 0; i < str.length(); i++) {
            updateKeys((byte) (str.charAt(i) & 255));
        }
    }

    private void updateKeys(byte b) {
        int[] iArr = this.keys;
        iArr[0] = crc32(iArr[0], b);
        int[] iArr2 = this.keys;
        iArr2[1] = iArr2[1] + (iArr2[0] & 255);
        iArr2[1] = (iArr2[1] * 134775813) + 1;
        iArr2[2] = crc32(iArr2[2], (byte) (iArr2[1] >> 24));
    }

    private byte decryptByte() {
        int i = this.keys[2] | 2;
        return (byte) ((i * (i ^ 1)) >>> 8);
    }

    private int crc32(int i, byte b) {
        return CRC_TABLE[(i ^ b) & 255] ^ (i >>> 8);
    }
}
