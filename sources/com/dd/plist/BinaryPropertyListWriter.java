package com.dd.plist;

import android.support.v4.media.session.PlaybackStateCompat;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class BinaryPropertyListWriter {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final int VERSION_00 = 0;
    public static final int VERSION_10 = 10;
    public static final int VERSION_15 = 15;
    public static final int VERSION_20 = 20;
    private long count;
    private Map<NSObject, Integer> idMap = new HashMap();
    private int idSizeInBytes;
    private OutputStream out;
    private int version = 0;

    private static int computeIdSizeInBytes(int i) {
        if (i < 256) {
            return 1;
        }
        return i < 65536 ? 2 : 4;
    }

    private int computeOffsetSizeInBytes(long j) {
        if (j < 256) {
            return 1;
        }
        if (j < PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH) {
            return 2;
        }
        return j < 4294967296L ? 4 : 8;
    }

    private static int getMinimumRequiredVersion(NSObject nSObject) {
        int i = 10;
        int i2 = 0;
        int i3 = nSObject == null ? 10 : 0;
        if (nSObject instanceof NSDictionary) {
            for (NSObject minimumRequiredVersion : ((NSDictionary) nSObject).getHashMap().values()) {
                int minimumRequiredVersion2 = getMinimumRequiredVersion(minimumRequiredVersion);
                if (minimumRequiredVersion2 > i3) {
                    i3 = minimumRequiredVersion2;
                }
            }
            return i3;
        } else if (nSObject instanceof NSArray) {
            NSObject[] array = ((NSArray) nSObject).getArray();
            int length = array.length;
            while (i2 < length) {
                int minimumRequiredVersion3 = getMinimumRequiredVersion(array[i2]);
                if (minimumRequiredVersion3 > i3) {
                    i3 = minimumRequiredVersion3;
                }
                i2++;
            }
            return i3;
        } else if (!(nSObject instanceof NSSet)) {
            return i3;
        } else {
            NSObject[] allObjects = ((NSSet) nSObject).allObjects();
            int length2 = allObjects.length;
            while (i2 < length2) {
                int minimumRequiredVersion4 = getMinimumRequiredVersion(allObjects[i2]);
                if (minimumRequiredVersion4 > i) {
                    i = minimumRequiredVersion4;
                }
                i2++;
            }
            return i;
        }
    }

    public static void write(File file, NSObject nSObject) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        write((OutputStream) fileOutputStream, nSObject);
        fileOutputStream.close();
    }

    public static void write(OutputStream outputStream, NSObject nSObject) throws IOException {
        int minimumRequiredVersion = getMinimumRequiredVersion(nSObject);
        if (minimumRequiredVersion > 0) {
            String str = minimumRequiredVersion != 10 ? minimumRequiredVersion != 15 ? minimumRequiredVersion == 20 ? "v2.0" : "v0.0" : "v1.5" : "v1.0";
            throw new IOException("The given property list structure cannot be saved. The required version of the binary format (" + str + ") is not yet supported.");
        }
        new BinaryPropertyListWriter(outputStream, minimumRequiredVersion).write(nSObject);
    }

    public static byte[] writeToArray(NSObject nSObject) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        write((OutputStream) byteArrayOutputStream, nSObject);
        return byteArrayOutputStream.toByteArray();
    }

    BinaryPropertyListWriter(OutputStream outputStream) throws IOException {
        this.out = new BufferedOutputStream(outputStream);
    }

    BinaryPropertyListWriter(OutputStream outputStream, int i) throws IOException {
        this.version = i;
        this.out = new BufferedOutputStream(outputStream);
    }

    /* access modifiers changed from: package-private */
    public void write(NSObject nSObject) throws IOException {
        int i;
        write(new byte[]{98, 112, 108, 105, 115, 116});
        int i2 = this.version;
        if (i2 == 0) {
            write(new byte[]{48, 48});
        } else if (i2 == 10) {
            write(new byte[]{49, 48});
        } else if (i2 == 15) {
            write(new byte[]{49, 53});
        } else if (i2 == 20) {
            write(new byte[]{50, 48});
        }
        nSObject.assignIDs(this);
        this.idSizeInBytes = computeIdSizeInBytes(this.idMap.size());
        int size = this.idMap.size();
        long[] jArr = new long[size];
        Iterator<Map.Entry<NSObject, Integer>> it = this.idMap.entrySet().iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Map.Entry next = it.next();
            NSObject nSObject2 = (NSObject) next.getKey();
            jArr[((Integer) next.getValue()).intValue()] = this.count;
            if (nSObject2 == null) {
                write(0);
            } else {
                nSObject2.toBinary(this);
            }
        }
        long j = this.count;
        int computeOffsetSizeInBytes = computeOffsetSizeInBytes(j);
        for (i = 0; i < size; i++) {
            writeBytes(jArr[i], computeOffsetSizeInBytes);
        }
        if (this.version != 15) {
            write(new byte[6]);
            write(computeOffsetSizeInBytes);
            write(this.idSizeInBytes);
            writeLong((long) this.idMap.size());
            writeLong((long) this.idMap.get(nSObject).intValue());
            writeLong(j);
        }
        this.out.flush();
    }

    /* access modifiers changed from: package-private */
    public void assignID(NSObject nSObject) {
        if (!this.idMap.containsKey(nSObject)) {
            Map<NSObject, Integer> map = this.idMap;
            map.put(nSObject, Integer.valueOf(map.size()));
        }
    }

    /* access modifiers changed from: package-private */
    public int getID(NSObject nSObject) {
        return this.idMap.get(nSObject).intValue();
    }

    /* access modifiers changed from: package-private */
    public void writeIntHeader(int i, int i2) throws IOException {
        if (i2 < 15) {
            write((i << 4) + i2);
        } else if (i2 < 256) {
            write((i << 4) + 15);
            write(16);
            writeBytes((long) i2, 1);
        } else if (i2 < 65536) {
            write((i << 4) + 15);
            write(17);
            writeBytes((long) i2, 2);
        } else {
            write((i << 4) + 15);
            write(18);
            writeBytes((long) i2, 4);
        }
    }

    /* access modifiers changed from: package-private */
    public void write(int i) throws IOException {
        this.out.write(i);
        this.count++;
    }

    /* access modifiers changed from: package-private */
    public void write(byte[] bArr) throws IOException {
        this.out.write(bArr);
        this.count += (long) bArr.length;
    }

    /* access modifiers changed from: package-private */
    public void writeBytes(long j, int i) throws IOException {
        for (int i2 = i - 1; i2 >= 0; i2--) {
            write((int) (j >> (i2 * 8)));
        }
    }

    /* access modifiers changed from: package-private */
    public void writeID(int i) throws IOException {
        writeBytes((long) i, this.idSizeInBytes);
    }

    /* access modifiers changed from: package-private */
    public void writeLong(long j) throws IOException {
        writeBytes(j, 8);
    }

    /* access modifiers changed from: package-private */
    public void writeDouble(double d) throws IOException {
        writeLong(Double.doubleToRawLongBits(d));
    }
}
