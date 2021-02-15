package com.dd.plist;

import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.theartofdev.edmodo.cropper.CropImage;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

public class NSSet extends NSObject {
    private boolean ordered;
    private Set<NSObject> set;

    public NSSet() {
        this.ordered = false;
        this.set = new LinkedHashSet();
    }

    public NSSet(boolean z) {
        this.ordered = false;
        this.ordered = z;
        if (!z) {
            this.set = new LinkedHashSet();
        } else {
            this.set = new TreeSet();
        }
    }

    public NSSet(NSObject... nSObjectArr) {
        this.ordered = false;
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        this.set = linkedHashSet;
        linkedHashSet.addAll(Arrays.asList(nSObjectArr));
    }

    public NSSet(boolean z, NSObject... nSObjectArr) {
        this.ordered = false;
        this.ordered = z;
        if (!z) {
            this.set = new LinkedHashSet();
        } else {
            this.set = new TreeSet();
        }
        this.set.addAll(Arrays.asList(nSObjectArr));
    }

    public synchronized void addObject(NSObject nSObject) {
        this.set.add(nSObject);
    }

    public synchronized void removeObject(NSObject nSObject) {
        this.set.remove(nSObject);
    }

    public synchronized NSObject[] allObjects() {
        return (NSObject[]) this.set.toArray(new NSObject[count()]);
    }

    public synchronized NSObject anyObject() {
        if (this.set.isEmpty()) {
            return null;
        }
        return this.set.iterator().next();
    }

    public boolean containsObject(NSObject nSObject) {
        return this.set.contains(nSObject);
    }

    public synchronized NSObject member(NSObject nSObject) {
        for (NSObject next : this.set) {
            if (next.equals(nSObject)) {
                return next;
            }
        }
        return null;
    }

    public synchronized boolean intersectsSet(NSSet nSSet) {
        for (NSObject containsObject : this.set) {
            if (nSSet.containsObject(containsObject)) {
                return true;
            }
        }
        return false;
    }

    public synchronized boolean isSubsetOfSet(NSSet nSSet) {
        for (NSObject containsObject : this.set) {
            if (!nSSet.containsObject(containsObject)) {
                return false;
            }
        }
        return true;
    }

    public synchronized Iterator<NSObject> objectIterator() {
        return this.set.iterator();
    }

    /* access modifiers changed from: package-private */
    public Set<NSObject> getSet() {
        return this.set;
    }

    public int hashCode() {
        Set<NSObject> set2 = this.set;
        return CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE + (set2 != null ? set2.hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Set<NSObject> set2 = this.set;
        Set<NSObject> set3 = ((NSSet) obj).set;
        if (set2 == set3) {
            return true;
        }
        if (set2 == null || !set2.equals(set3)) {
            return false;
        }
        return true;
    }

    public synchronized int count() {
        return this.set.size();
    }

    /* access modifiers changed from: package-private */
    public void toXML(StringBuilder sb, int i) {
        indent(sb, i);
        sb.append("<array>");
        sb.append(NSObject.NEWLINE);
        for (NSObject xml : this.set) {
            xml.toXML(sb, i + 1);
            sb.append(NSObject.NEWLINE);
        }
        indent(sb, i);
        sb.append("</array>");
    }

    /* access modifiers changed from: package-private */
    public void assignIDs(BinaryPropertyListWriter binaryPropertyListWriter) {
        super.assignIDs(binaryPropertyListWriter);
        for (NSObject assignIDs : this.set) {
            assignIDs.assignIDs(binaryPropertyListWriter);
        }
    }

    /* access modifiers changed from: package-private */
    public void toBinary(BinaryPropertyListWriter binaryPropertyListWriter) throws IOException {
        if (this.ordered) {
            binaryPropertyListWriter.writeIntHeader(11, this.set.size());
        } else {
            binaryPropertyListWriter.writeIntHeader(12, this.set.size());
        }
        for (NSObject id : this.set) {
            binaryPropertyListWriter.writeID(binaryPropertyListWriter.getID(id));
        }
    }

    /* access modifiers changed from: protected */
    public void toASCII(StringBuilder sb, int i) {
        indent(sb, i);
        NSObject[] allObjects = allObjects();
        sb.append(ASCIIPropertyListParser.ARRAY_BEGIN_TOKEN);
        int lastIndexOf = sb.lastIndexOf(NEWLINE);
        for (int i2 = 0; i2 < allObjects.length; i2++) {
            Class<?> cls = allObjects[i2].getClass();
            if ((cls.equals(NSDictionary.class) || cls.equals(NSArray.class) || cls.equals(NSData.class)) && lastIndexOf != sb.length()) {
                sb.append(NEWLINE);
                lastIndexOf = sb.length();
                allObjects[i2].toASCII(sb, i + 1);
            } else {
                if (i2 != 0) {
                    sb.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
                }
                allObjects[i2].toASCII(sb, 0);
            }
            if (i2 != allObjects.length - 1) {
                sb.append(ASCIIPropertyListParser.ARRAY_ITEM_DELIMITER_TOKEN);
            }
            if (sb.length() - lastIndexOf > 80) {
                sb.append(NEWLINE);
                lastIndexOf = sb.length();
            }
        }
        sb.append(ASCIIPropertyListParser.ARRAY_END_TOKEN);
    }

    /* access modifiers changed from: protected */
    public void toASCIIGnuStep(StringBuilder sb, int i) {
        indent(sb, i);
        NSObject[] allObjects = allObjects();
        sb.append(ASCIIPropertyListParser.ARRAY_BEGIN_TOKEN);
        int lastIndexOf = sb.lastIndexOf(NEWLINE);
        for (int i2 = 0; i2 < allObjects.length; i2++) {
            Class<?> cls = allObjects[i2].getClass();
            if ((cls.equals(NSDictionary.class) || cls.equals(NSArray.class) || cls.equals(NSData.class)) && lastIndexOf != sb.length()) {
                sb.append(NEWLINE);
                lastIndexOf = sb.length();
                allObjects[i2].toASCIIGnuStep(sb, i + 1);
            } else {
                if (i2 != 0) {
                    sb.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
                }
                allObjects[i2].toASCIIGnuStep(sb, 0);
            }
            if (i2 != allObjects.length - 1) {
                sb.append(ASCIIPropertyListParser.ARRAY_ITEM_DELIMITER_TOKEN);
            }
            if (sb.length() - lastIndexOf > 80) {
                sb.append(NEWLINE);
                lastIndexOf = sb.length();
            }
        }
        sb.append(ASCIIPropertyListParser.ARRAY_END_TOKEN);
    }
}
