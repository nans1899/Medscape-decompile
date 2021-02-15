package com.dd.plist;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public abstract class NSObject {
    static final int ASCII_LINE_LENGTH = 80;
    static final String INDENT = "\t";
    static final String NEWLINE = System.getProperty("line.separator");

    /* access modifiers changed from: protected */
    public abstract void toASCII(StringBuilder sb, int i);

    /* access modifiers changed from: protected */
    public abstract void toASCIIGnuStep(StringBuilder sb, int i);

    /* access modifiers changed from: package-private */
    public abstract void toBinary(BinaryPropertyListWriter binaryPropertyListWriter) throws IOException;

    /* access modifiers changed from: package-private */
    public abstract void toXML(StringBuilder sb, int i);

    /* access modifiers changed from: package-private */
    public void assignIDs(BinaryPropertyListWriter binaryPropertyListWriter) {
        binaryPropertyListWriter.assignID(this);
    }

    public String toXMLPropertyList() {
        StringBuilder sb = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        sb.append(NEWLINE);
        sb.append("<!DOCTYPE plist PUBLIC \"-//Apple//DTD PLIST 1.0//EN\" \"http://www.apple.com/DTDs/PropertyList-1.0.dtd\">");
        sb.append(NEWLINE);
        sb.append("<plist version=\"1.0\">");
        sb.append(NEWLINE);
        toXML(sb, 0);
        sb.append(NEWLINE);
        sb.append("</plist>");
        return sb.toString();
    }

    /* access modifiers changed from: package-private */
    public void indent(StringBuilder sb, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            sb.append(INDENT);
        }
    }

    public static NSString wrap(String str) {
        return new NSString(str);
    }

    public static NSNumber wrap(long j) {
        return new NSNumber(j);
    }

    public static NSNumber wrap(double d) {
        return new NSNumber(d);
    }

    public static NSNumber wrap(boolean z) {
        return new NSNumber(z);
    }

    public static NSDate wrap(Date date) {
        return new NSDate(date);
    }

    public static NSData wrap(byte[] bArr) {
        return new NSData(bArr);
    }

    public static NSArray wrap(Object[] objArr) {
        NSArray nSArray = new NSArray(objArr.length);
        for (int i = 0; i < objArr.length; i++) {
            nSArray.setValue(i, wrap(objArr[i]));
        }
        return nSArray;
    }

    public static NSDictionary wrap(Map<String, Object> map) {
        NSDictionary nSDictionary = new NSDictionary();
        for (String next : map.keySet()) {
            nSDictionary.put(next, wrap(map.get(next)));
        }
        return nSDictionary;
    }

    public static NSSet wrap(Set<Object> set) {
        NSSet nSSet = new NSSet();
        for (Object wrap : set.toArray()) {
            nSSet.addObject(wrap(wrap));
        }
        return nSSet;
    }

    public static NSObject wrap(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof NSObject) {
            return (NSObject) obj;
        }
        Class<?> cls = obj.getClass();
        if (Boolean.class.isAssignableFrom(cls)) {
            return wrap(((Boolean) obj).booleanValue());
        }
        if (Integer.class.isAssignableFrom(cls)) {
            return wrap((long) ((Integer) obj).intValue());
        }
        if (Long.class.isAssignableFrom(cls)) {
            return wrap(((Long) obj).longValue());
        }
        if (Short.class.isAssignableFrom(cls)) {
            return wrap((long) ((Short) obj).shortValue());
        }
        if (Byte.class.isAssignableFrom(cls)) {
            return wrap((long) ((Byte) obj).byteValue());
        }
        if (Float.class.isAssignableFrom(cls)) {
            return wrap((double) ((Float) obj).floatValue());
        }
        if (Double.class.isAssignableFrom(cls)) {
            return wrap(((Double) obj).doubleValue());
        }
        if (String.class.equals(cls)) {
            return wrap((String) obj);
        }
        if (Date.class.equals(cls)) {
            return wrap((Date) obj);
        }
        if (byte[].class.equals(cls)) {
            return wrap((byte[]) obj);
        }
        if (Object[].class.isAssignableFrom(cls)) {
            return wrap((Object[]) obj);
        }
        if (Map.class.isAssignableFrom(cls)) {
            Map map = (Map) obj;
            Set keySet = map.keySet();
            NSDictionary nSDictionary = new NSDictionary();
            for (Object next : keySet) {
                nSDictionary.put(String.valueOf(next), wrap(map.get(next)));
            }
            return nSDictionary;
        } else if (Collection.class.isAssignableFrom(cls)) {
            return wrap(((Collection) obj).toArray());
        } else {
            return wrapSerialized(obj);
        }
    }

    public static NSData wrapSerialized(Object obj) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            new ObjectOutputStream(byteArrayOutputStream).writeObject(obj);
            return new NSData(byteArrayOutputStream.toByteArray());
        } catch (IOException unused) {
            throw new RuntimeException("The given object of class " + obj.getClass().toString() + " could not be serialized and stored in a NSData object.");
        }
    }

    public Object toJavaObject() {
        Set set;
        if (this instanceof NSArray) {
            NSObject[] array = ((NSArray) this).getArray();
            Object[] objArr = new Object[array.length];
            for (int i = 0; i < array.length; i++) {
                objArr[i] = array[i].toJavaObject();
            }
            return objArr;
        } else if (this instanceof NSDictionary) {
            HashMap<String, NSObject> hashMap = ((NSDictionary) this).getHashMap();
            HashMap hashMap2 = new HashMap(hashMap.size());
            for (String next : hashMap.keySet()) {
                hashMap2.put(next, hashMap.get(next).toJavaObject());
            }
            return hashMap2;
        } else if (this instanceof NSSet) {
            Set<NSObject> set2 = ((NSSet) this).getSet();
            if (set2 instanceof LinkedHashSet) {
                set = new LinkedHashSet(set2.size());
            } else {
                set = new TreeSet();
            }
            for (NSObject javaObject : set2) {
                set.add(javaObject.toJavaObject());
            }
            return set;
        } else if (this instanceof NSNumber) {
            NSNumber nSNumber = (NSNumber) this;
            int type = nSNumber.type();
            if (type == 0) {
                long longValue = nSNumber.longValue();
                if (longValue > 2147483647L || longValue < -2147483648L) {
                    return Long.valueOf(longValue);
                }
                return Integer.valueOf(nSNumber.intValue());
            } else if (type == 1) {
                return Double.valueOf(nSNumber.doubleValue());
            } else {
                if (type != 2) {
                    return Double.valueOf(nSNumber.doubleValue());
                }
                return Boolean.valueOf(nSNumber.boolValue());
            }
        } else if (this instanceof NSString) {
            return ((NSString) this).getContent();
        } else {
            if (this instanceof NSData) {
                return ((NSData) this).bytes();
            }
            if (this instanceof NSDate) {
                return ((NSDate) this).getDate();
            }
            return this instanceof UID ? ((UID) this).getBytes() : this;
        }
    }
}
