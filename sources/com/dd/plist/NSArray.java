package com.dd.plist;

import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.io.IOException;
import java.util.Arrays;

public class NSArray extends NSObject {
    private NSObject[] array;

    public NSArray(int i) {
        this.array = new NSObject[i];
    }

    public NSArray(NSObject... nSObjectArr) {
        this.array = nSObjectArr;
    }

    public NSObject objectAtIndex(int i) {
        return this.array[i];
    }

    public void remove(int i) {
        NSObject[] nSObjectArr = this.array;
        if (i >= nSObjectArr.length || i < 0) {
            throw new ArrayIndexOutOfBoundsException("invalid index:" + i + ";the array length is " + this.array.length);
        }
        NSObject[] nSObjectArr2 = new NSObject[(nSObjectArr.length - 1)];
        System.arraycopy(nSObjectArr, 0, nSObjectArr2, 0, i);
        NSObject[] nSObjectArr3 = this.array;
        System.arraycopy(nSObjectArr3, i + 1, nSObjectArr2, i, (nSObjectArr3.length - i) - 1);
        this.array = nSObjectArr2;
    }

    public void setValue(int i, NSObject nSObject) {
        this.array[i] = nSObject;
    }

    public NSObject[] getArray() {
        return this.array;
    }

    public int count() {
        return this.array.length;
    }

    public boolean containsObject(NSObject nSObject) {
        for (NSObject equals : this.array) {
            if (equals.equals(nSObject)) {
                return true;
            }
        }
        return false;
    }

    public int indexOfObject(NSObject nSObject) {
        int i = 0;
        while (true) {
            NSObject[] nSObjectArr = this.array;
            if (i >= nSObjectArr.length) {
                return -1;
            }
            if (nSObjectArr[i].equals(nSObject)) {
                return i;
            }
            i++;
        }
    }

    public int indexOfIdenticalObject(NSObject nSObject) {
        int i = 0;
        while (true) {
            NSObject[] nSObjectArr = this.array;
            if (i >= nSObjectArr.length) {
                return -1;
            }
            if (nSObjectArr[i] == nSObject) {
                return i;
            }
            i++;
        }
    }

    public NSObject lastObject() {
        NSObject[] nSObjectArr = this.array;
        return nSObjectArr[nSObjectArr.length - 1];
    }

    public NSObject[] objectsAtIndexes(int... iArr) {
        NSObject[] nSObjectArr = new NSObject[iArr.length];
        Arrays.sort(iArr);
        for (int i = 0; i < iArr.length; i++) {
            nSObjectArr[i] = this.array[iArr[i]];
        }
        return nSObjectArr;
    }

    public boolean equals(Object obj) {
        return obj.getClass().equals(getClass()) && Arrays.equals(((NSArray) obj).getArray(), this.array);
    }

    public int hashCode() {
        return 623 + Arrays.deepHashCode(this.array);
    }

    /* access modifiers changed from: package-private */
    public void toXML(StringBuilder sb, int i) {
        indent(sb, i);
        sb.append("<array>");
        sb.append(NSObject.NEWLINE);
        for (NSObject xml : this.array) {
            xml.toXML(sb, i + 1);
            sb.append(NSObject.NEWLINE);
        }
        indent(sb, i);
        sb.append("</array>");
    }

    /* access modifiers changed from: package-private */
    public void assignIDs(BinaryPropertyListWriter binaryPropertyListWriter) {
        super.assignIDs(binaryPropertyListWriter);
        for (NSObject assignIDs : this.array) {
            assignIDs.assignIDs(binaryPropertyListWriter);
        }
    }

    /* access modifiers changed from: package-private */
    public void toBinary(BinaryPropertyListWriter binaryPropertyListWriter) throws IOException {
        binaryPropertyListWriter.writeIntHeader(10, this.array.length);
        for (NSObject id : this.array) {
            binaryPropertyListWriter.writeID(binaryPropertyListWriter.getID(id));
        }
    }

    public String toASCIIPropertyList() {
        StringBuilder sb = new StringBuilder();
        toASCII(sb, 0);
        sb.append(NEWLINE);
        return sb.toString();
    }

    public String toGnuStepASCIIPropertyList() {
        StringBuilder sb = new StringBuilder();
        toASCIIGnuStep(sb, 0);
        sb.append(NEWLINE);
        return sb.toString();
    }

    /* access modifiers changed from: protected */
    public void toASCII(StringBuilder sb, int i) {
        indent(sb, i);
        sb.append(ASCIIPropertyListParser.ARRAY_BEGIN_TOKEN);
        int lastIndexOf = sb.lastIndexOf(NEWLINE);
        int i2 = 0;
        while (true) {
            NSObject[] nSObjectArr = this.array;
            if (i2 < nSObjectArr.length) {
                Class<?> cls = nSObjectArr[i2].getClass();
                if ((cls.equals(NSDictionary.class) || cls.equals(NSArray.class) || cls.equals(NSData.class)) && lastIndexOf != sb.length()) {
                    sb.append(NEWLINE);
                    lastIndexOf = sb.length();
                    this.array[i2].toASCII(sb, i + 1);
                } else {
                    if (i2 != 0) {
                        sb.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
                    }
                    this.array[i2].toASCII(sb, 0);
                }
                if (i2 != this.array.length - 1) {
                    sb.append(ASCIIPropertyListParser.ARRAY_ITEM_DELIMITER_TOKEN);
                }
                if (sb.length() - lastIndexOf > 80) {
                    sb.append(NEWLINE);
                    lastIndexOf = sb.length();
                }
                i2++;
            } else {
                sb.append(ASCIIPropertyListParser.ARRAY_END_TOKEN);
                return;
            }
        }
    }

    /* access modifiers changed from: protected */
    public void toASCIIGnuStep(StringBuilder sb, int i) {
        indent(sb, i);
        sb.append(ASCIIPropertyListParser.ARRAY_BEGIN_TOKEN);
        int lastIndexOf = sb.lastIndexOf(NEWLINE);
        int i2 = 0;
        while (true) {
            NSObject[] nSObjectArr = this.array;
            if (i2 < nSObjectArr.length) {
                Class<?> cls = nSObjectArr[i2].getClass();
                if ((cls.equals(NSDictionary.class) || cls.equals(NSArray.class) || cls.equals(NSData.class)) && lastIndexOf != sb.length()) {
                    sb.append(NEWLINE);
                    lastIndexOf = sb.length();
                    this.array[i2].toASCIIGnuStep(sb, i + 1);
                } else {
                    if (i2 != 0) {
                        sb.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
                    }
                    this.array[i2].toASCIIGnuStep(sb, 0);
                }
                if (i2 != this.array.length - 1) {
                    sb.append(ASCIIPropertyListParser.ARRAY_ITEM_DELIMITER_TOKEN);
                }
                if (sb.length() - lastIndexOf > 80) {
                    sb.append(NEWLINE);
                    lastIndexOf = sb.length();
                }
                i2++;
            } else {
                sb.append(ASCIIPropertyListParser.ARRAY_END_TOKEN);
                return;
            }
        }
    }
}
