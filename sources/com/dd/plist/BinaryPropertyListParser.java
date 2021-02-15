package com.dd.plist;

import com.google.common.base.Ascii;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.math.BigInteger;

public class BinaryPropertyListParser {
    private byte[] bytes;
    private int majorVersion;
    private int minorVersion;
    private int numObjects;
    private int objectRefSize;
    private int offsetSize;
    private int[] offsetTable;
    private int offsetTableOffset;
    private int topObject;

    protected BinaryPropertyListParser() {
    }

    public static NSObject parse(byte[] bArr) throws Exception {
        return new BinaryPropertyListParser().doParse(bArr);
    }

    private NSObject doParse(byte[] bArr) throws Exception {
        this.bytes = bArr;
        int i = 0;
        String str = new String(copyOfRange(this.bytes, 0, 8));
        if (str.startsWith("bplist")) {
            this.majorVersion = str.charAt(6) - '0';
            this.minorVersion = str.charAt(7) - '0';
            if (this.majorVersion <= 0) {
                byte[] bArr2 = this.bytes;
                byte[] copyOfRange = copyOfRange(bArr2, bArr2.length - 32, bArr2.length);
                this.offsetSize = (int) parseUnsignedInt(copyOfRange(copyOfRange, 6, 7));
                this.objectRefSize = (int) parseUnsignedInt(copyOfRange(copyOfRange, 7, 8));
                this.numObjects = (int) parseUnsignedInt(copyOfRange(copyOfRange, 8, 16));
                this.topObject = (int) parseUnsignedInt(copyOfRange(copyOfRange, 16, 24));
                this.offsetTableOffset = (int) parseUnsignedInt(copyOfRange(copyOfRange, 24, 32));
                this.offsetTable = new int[this.numObjects];
                while (i < this.numObjects) {
                    byte[] bArr3 = this.bytes;
                    int i2 = this.offsetTableOffset;
                    int i3 = this.offsetSize;
                    int i4 = i + 1;
                    this.offsetTable[i] = (int) parseUnsignedInt(copyOfRange(bArr3, (i * i3) + i2, i2 + (i3 * i4)));
                    i = i4;
                }
                return parseObject(this.topObject);
            }
            throw new Exception("Unsupported binary property list format: v" + this.majorVersion + "." + this.minorVersion + ". " + "Version 1.0 and later are not yet supported.");
        }
        throw new Exception("The given data is no binary property list. Wrong magic bytes: " + str);
    }

    public static NSObject parse(InputStream inputStream) throws Exception {
        byte[] readAll = PropertyListParser.readAll(inputStream, Integer.MAX_VALUE);
        inputStream.close();
        return parse(readAll);
    }

    public static NSObject parse(File file) throws Exception {
        if (file.length() <= Runtime.getRuntime().freeMemory()) {
            return parse((InputStream) new FileInputStream(file));
        }
        throw new Exception("To little heap space available! Wanted to read " + file.length() + " bytes, but only " + Runtime.getRuntime().freeMemory() + " are available.");
    }

    private NSObject parseObject(int i) throws Exception {
        int i2 = this.offsetTable[i];
        byte b = this.bytes[i2];
        int i3 = (b & 240) >> 4;
        byte b2 = b & Ascii.SI;
        int i4 = 0;
        switch (i3) {
            case 0:
                if (b2 == 8) {
                    return new NSNumber(false);
                }
                if (b2 != 9) {
                    return null;
                }
                return new NSNumber(true);
            case 1:
                int pow = (int) Math.pow(2.0d, (double) b2);
                if (((long) pow) < Runtime.getRuntime().freeMemory()) {
                    int i5 = i2 + 1;
                    return new NSNumber(copyOfRange(this.bytes, i5, pow + i5), 0);
                }
                throw new Exception("To little heap space available! Wanted to read " + pow + " bytes, but only " + Runtime.getRuntime().freeMemory() + " are available.");
            case 2:
                int pow2 = (int) Math.pow(2.0d, (double) b2);
                if (((long) pow2) < Runtime.getRuntime().freeMemory()) {
                    int i6 = i2 + 1;
                    return new NSNumber(copyOfRange(this.bytes, i6, pow2 + i6), 1);
                }
                throw new Exception("To little heap space available! Wanted to read " + pow2 + " bytes, but only " + Runtime.getRuntime().freeMemory() + " are available.");
            case 3:
                if (b2 != 3) {
                    PrintStream printStream = System.err;
                    printStream.println("BinaryPropertyListParser: Unknown date type :" + b2 + ". Attempting to parse anyway...");
                }
                return new NSDate(copyOfRange(this.bytes, i2 + 1, i2 + 9));
            case 4:
                int[] readLengthAndOffset = readLengthAndOffset(b2, i2);
                int i7 = readLengthAndOffset[0];
                int i8 = readLengthAndOffset[1];
                if (((long) i7) < Runtime.getRuntime().freeMemory()) {
                    int i9 = i2 + i8;
                    return new NSData(copyOfRange(this.bytes, i9, i7 + i9));
                }
                throw new Exception("To little heap space available! Wanted to read " + i7 + " bytes, but only " + Runtime.getRuntime().freeMemory() + " are available.");
            case 5:
                int[] readLengthAndOffset2 = readLengthAndOffset(b2, i2);
                int i10 = readLengthAndOffset2[0];
                int i11 = readLengthAndOffset2[1];
                if (((long) i10) < Runtime.getRuntime().freeMemory()) {
                    int i12 = i2 + i11;
                    return new NSString(copyOfRange(this.bytes, i12, i10 + i12), "ASCII");
                }
                throw new Exception("To little heap space available! Wanted to read " + i10 + " bytes, but only " + Runtime.getRuntime().freeMemory() + " are available.");
            case 6:
                int[] readLengthAndOffset3 = readLengthAndOffset(b2, i2);
                int i13 = readLengthAndOffset3[0];
                int i14 = readLengthAndOffset3[1];
                int i15 = i13 * 2;
                if (((long) i15) < Runtime.getRuntime().freeMemory()) {
                    int i16 = i2 + i14;
                    return new NSString(copyOfRange(this.bytes, i16, i15 + i16), "UTF-16BE");
                }
                throw new Exception("To little heap space available! Wanted to read " + i15 + " bytes, but only " + Runtime.getRuntime().freeMemory() + " are available.");
            case 8:
                int i17 = b2 + 1;
                if (((long) i17) < Runtime.getRuntime().freeMemory()) {
                    int i18 = i2 + 1;
                    return new UID(String.valueOf(i), copyOfRange(this.bytes, i18, i17 + i18));
                }
                throw new Exception("To little heap space available! Wanted to read " + i17 + " bytes, but only " + Runtime.getRuntime().freeMemory() + " are available.");
            case 10:
                int[] readLengthAndOffset4 = readLengthAndOffset(b2, i2);
                int i19 = readLengthAndOffset4[0];
                int i20 = readLengthAndOffset4[1];
                if (((long) (this.objectRefSize * i19)) <= Runtime.getRuntime().freeMemory()) {
                    NSArray nSArray = new NSArray(i19);
                    while (i4 < i19) {
                        byte[] bArr = this.bytes;
                        int i21 = i2 + i20;
                        int i22 = this.objectRefSize;
                        int i23 = i4 + 1;
                        nSArray.setValue(i4, parseObject((int) parseUnsignedInt(copyOfRange(bArr, (i4 * i22) + i21, i21 + (i22 * i23)))));
                        i4 = i23;
                    }
                    return nSArray;
                }
                throw new Exception("To little heap space available!");
            case 11:
                int[] readLengthAndOffset5 = readLengthAndOffset(b2, i2);
                int i24 = readLengthAndOffset5[0];
                int i25 = readLengthAndOffset5[1];
                if (((long) (this.objectRefSize * i24)) <= Runtime.getRuntime().freeMemory()) {
                    NSSet nSSet = new NSSet(true);
                    while (i4 < i24) {
                        byte[] bArr2 = this.bytes;
                        int i26 = i2 + i25;
                        int i27 = this.objectRefSize;
                        i4++;
                        nSSet.addObject(parseObject((int) parseUnsignedInt(copyOfRange(bArr2, (i4 * i27) + i26, i26 + (i27 * i4)))));
                    }
                    return nSSet;
                }
                throw new Exception("To little heap space available!");
            case 12:
                int[] readLengthAndOffset6 = readLengthAndOffset(b2, i2);
                int i28 = readLengthAndOffset6[0];
                int i29 = readLengthAndOffset6[1];
                if (((long) (this.objectRefSize * i28)) <= Runtime.getRuntime().freeMemory()) {
                    NSSet nSSet2 = new NSSet();
                    while (i4 < i28) {
                        byte[] bArr3 = this.bytes;
                        int i30 = i2 + i29;
                        int i31 = this.objectRefSize;
                        i4++;
                        nSSet2.addObject(parseObject((int) parseUnsignedInt(copyOfRange(bArr3, (i4 * i31) + i30, i30 + (i31 * i4)))));
                    }
                    return nSSet2;
                }
                throw new Exception("To little heap space available!");
            case 13:
                int[] readLengthAndOffset7 = readLengthAndOffset(b2, i2);
                int i32 = readLengthAndOffset7[0];
                int i33 = readLengthAndOffset7[1];
                if (((long) (i32 * 2 * this.objectRefSize)) <= Runtime.getRuntime().freeMemory()) {
                    NSDictionary nSDictionary = new NSDictionary();
                    while (i4 < i32) {
                        byte[] bArr4 = this.bytes;
                        int i34 = i2 + i33;
                        int i35 = this.objectRefSize;
                        int i36 = i4 + 1;
                        int parseUnsignedInt = (int) parseUnsignedInt(copyOfRange(bArr4, (i4 * i35) + i34, (i35 * i36) + i34));
                        byte[] bArr5 = this.bytes;
                        int i37 = this.objectRefSize;
                        NSObject parseObject = parseObject(parseUnsignedInt);
                        nSDictionary.put(parseObject.toString(), parseObject((int) parseUnsignedInt(copyOfRange(bArr5, (i32 * i37) + i34 + (i4 * i37), i34 + (i32 * i37) + (i37 * i36)))));
                        i4 = i36;
                    }
                    return nSDictionary;
                }
                throw new Exception("To little heap space available!");
            default:
                PrintStream printStream2 = System.err;
                printStream2.println("Unknown object type: " + i3);
                return null;
        }
    }

    private int[] readLengthAndOffset(int i, int i2) {
        int i3;
        if (i == 15) {
            byte b = this.bytes[i2 + 1];
            int i4 = (b & 240) >> 4;
            if (i4 != 1) {
                PrintStream printStream = System.err;
                printStream.println("BinaryPropertyListParser: Length integer has an unexpected type" + i4 + ". Attempting to parse anyway...");
            }
            int pow = (int) Math.pow(2.0d, (double) (b & Ascii.SI));
            i3 = pow + 2;
            if (pow < 3) {
                int i5 = i2 + 2;
                i = (int) parseUnsignedInt(copyOfRange(this.bytes, i5, pow + i5));
            } else {
                int i6 = i2 + 2;
                i = new BigInteger(copyOfRange(this.bytes, i6, pow + i6)).intValue();
            }
        } else {
            i3 = 1;
        }
        return new int[]{i, i3};
    }

    public static final long parseUnsignedInt(byte[] bArr) {
        long j = 0;
        for (byte b : bArr) {
            j = (j << 8) | ((long) (b & 255));
        }
        return j & 4294967295L;
    }

    public static final long parseLong(byte[] bArr) {
        long j = 0;
        for (byte b : bArr) {
            j = (j << 8) | ((long) (b & 255));
        }
        return j;
    }

    public static final double parseDouble(byte[] bArr) {
        if (bArr.length == 8) {
            return Double.longBitsToDouble(parseLong(bArr));
        }
        if (bArr.length == 4) {
            return (double) Float.intBitsToFloat((int) parseLong(bArr));
        }
        throw new IllegalArgumentException("bad byte array length " + bArr.length);
    }

    public static byte[] copyOfRange(byte[] bArr, int i, int i2) {
        int i3 = i2 - i;
        if (i3 >= 0) {
            byte[] bArr2 = new byte[i3];
            System.arraycopy(bArr, i, bArr2, 0, i3);
            return bArr2;
        }
        throw new IllegalArgumentException("startIndex (" + i + ")" + " > endIndex (" + i2 + ")");
    }
}
