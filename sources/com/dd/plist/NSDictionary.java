package com.dd.plist;

import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.webmd.wbmdcmepulse.models.articles.HtmlObject;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class NSDictionary extends NSObject implements Map<String, NSObject> {
    private HashMap<String, NSObject> dict = new LinkedHashMap();

    public HashMap<String, NSObject> getHashMap() {
        return this.dict;
    }

    public NSObject objectForKey(String str) {
        return this.dict.get(str);
    }

    public void put(String str, Object obj) {
        this.dict.put(str, NSObject.wrap(obj));
    }

    public int size() {
        return this.dict.size();
    }

    public boolean isEmpty() {
        return this.dict.isEmpty();
    }

    public boolean containsKey(Object obj) {
        return this.dict.containsKey(obj);
    }

    public boolean containsValue(Object obj) {
        return this.dict.containsValue(NSObject.wrap(obj));
    }

    public NSObject get(Object obj) {
        return this.dict.get(obj);
    }

    public void putAll(Map<? extends String, ? extends NSObject> map) {
        for (Map.Entry next : map.entrySet()) {
            put((String) next.getKey(), (NSObject) next.getValue());
        }
    }

    public NSObject put(String str, NSObject nSObject) {
        return this.dict.put(str, nSObject);
    }

    public NSObject put(String str, String str2) {
        return put(str, (NSObject) new NSString(str2));
    }

    public NSObject put(String str, long j) {
        return put(str, (NSObject) new NSNumber(j));
    }

    public NSObject put(String str, double d) {
        return put(str, (NSObject) new NSNumber(d));
    }

    public NSObject put(String str, boolean z) {
        return put(str, (NSObject) new NSNumber(z));
    }

    public NSObject put(String str, Date date) {
        return put(str, (NSObject) new NSDate(date));
    }

    public NSObject put(String str, byte[] bArr) {
        return put(str, (NSObject) new NSData(bArr));
    }

    public NSObject remove(String str) {
        return this.dict.remove(str);
    }

    public NSObject remove(Object obj) {
        return this.dict.remove(obj);
    }

    public void clear() {
        this.dict.clear();
    }

    public Set<String> keySet() {
        return this.dict.keySet();
    }

    public Collection<NSObject> values() {
        return this.dict.values();
    }

    public Set<Map.Entry<String, NSObject>> entrySet() {
        return this.dict.entrySet();
    }

    public boolean containsKey(String str) {
        return this.dict.containsKey(str);
    }

    public boolean containsValue(NSObject nSObject) {
        return this.dict.containsValue(nSObject);
    }

    public boolean containsValue(String str) {
        for (NSObject next : this.dict.values()) {
            if (next.getClass().equals(NSString.class) && ((NSString) next).getContent().equals(str)) {
                return true;
            }
        }
        return false;
    }

    public boolean containsValue(long j) {
        for (NSObject next : this.dict.values()) {
            if (next.getClass().equals(NSNumber.class)) {
                NSNumber nSNumber = (NSNumber) next;
                if (nSNumber.isInteger() && ((long) nSNumber.intValue()) == j) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean containsValue(double d) {
        for (NSObject next : this.dict.values()) {
            if (next.getClass().equals(NSNumber.class)) {
                NSNumber nSNumber = (NSNumber) next;
                if (nSNumber.isReal() && nSNumber.doubleValue() == d) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean containsValue(boolean z) {
        for (NSObject next : this.dict.values()) {
            if (next.getClass().equals(NSNumber.class)) {
                NSNumber nSNumber = (NSNumber) next;
                if (nSNumber.isBoolean() && nSNumber.boolValue() == z) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean containsValue(Date date) {
        for (NSObject next : this.dict.values()) {
            if (next.getClass().equals(NSDate.class) && ((NSDate) next).getDate().equals(date)) {
                return true;
            }
        }
        return false;
    }

    public boolean containsValue(byte[] bArr) {
        for (NSObject next : this.dict.values()) {
            if (next.getClass().equals(NSData.class) && Arrays.equals(((NSData) next).bytes(), bArr)) {
                return true;
            }
        }
        return false;
    }

    public int count() {
        return this.dict.size();
    }

    public boolean equals(Object obj) {
        return obj.getClass().equals(getClass()) && ((NSDictionary) obj).dict.equals(this.dict);
    }

    public String[] allKeys() {
        return (String[]) this.dict.keySet().toArray(new String[0]);
    }

    public int hashCode() {
        HashMap<String, NSObject> hashMap = this.dict;
        return 581 + (hashMap != null ? hashMap.hashCode() : 0);
    }

    /* access modifiers changed from: package-private */
    public void toXML(StringBuilder sb, int i) {
        indent(sb, i);
        sb.append("<dict>");
        sb.append(NSObject.NEWLINE);
        for (String next : this.dict.keySet()) {
            NSObject objectForKey = objectForKey(next);
            int i2 = i + 1;
            indent(sb, i2);
            sb.append("<key>");
            if (next.contains("&") || next.contains(HtmlObject.HtmlMarkUp.OPEN_BRACKER) || next.contains(HtmlObject.HtmlMarkUp.CLOSE_BRACKER)) {
                sb.append("<![CDATA[");
                sb.append(next.replaceAll("]]>", "]]]]><![CDATA[>"));
                sb.append("]]>");
            } else {
                sb.append(next);
            }
            sb.append("</key>");
            sb.append(NSObject.NEWLINE);
            objectForKey.toXML(sb, i2);
            sb.append(NSObject.NEWLINE);
        }
        indent(sb, i);
        sb.append("</dict>");
    }

    /* access modifiers changed from: package-private */
    public void assignIDs(BinaryPropertyListWriter binaryPropertyListWriter) {
        super.assignIDs(binaryPropertyListWriter);
        for (Map.Entry next : this.dict.entrySet()) {
            new NSString((String) next.getKey()).assignIDs(binaryPropertyListWriter);
            ((NSObject) next.getValue()).assignIDs(binaryPropertyListWriter);
        }
    }

    /* access modifiers changed from: package-private */
    public void toBinary(BinaryPropertyListWriter binaryPropertyListWriter) throws IOException {
        binaryPropertyListWriter.writeIntHeader(13, this.dict.size());
        Set<Map.Entry<String, NSObject>> entrySet = this.dict.entrySet();
        for (Map.Entry<String, NSObject> key : entrySet) {
            binaryPropertyListWriter.writeID(binaryPropertyListWriter.getID(new NSString((String) key.getKey())));
        }
        for (Map.Entry<String, NSObject> value : entrySet) {
            binaryPropertyListWriter.writeID(binaryPropertyListWriter.getID((NSObject) value.getValue()));
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
        sb.append(ASCIIPropertyListParser.DICTIONARY_BEGIN_TOKEN);
        sb.append(NEWLINE);
        String[] strArr = (String[]) this.dict.keySet().toArray(new String[0]);
        for (String str : strArr) {
            NSObject objectForKey = objectForKey(str);
            indent(sb, i + 1);
            sb.append("\"");
            sb.append(NSString.escapeStringForASCII(str));
            sb.append("\" =");
            Class<?> cls = objectForKey.getClass();
            if (cls.equals(NSDictionary.class) || cls.equals(NSArray.class) || cls.equals(NSData.class)) {
                sb.append(NEWLINE);
                objectForKey.toASCII(sb, i + 2);
            } else {
                sb.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
                objectForKey.toASCII(sb, 0);
            }
            sb.append(';');
            sb.append(NEWLINE);
        }
        indent(sb, i);
        sb.append(ASCIIPropertyListParser.DICTIONARY_END_TOKEN);
    }

    /* access modifiers changed from: protected */
    public void toASCIIGnuStep(StringBuilder sb, int i) {
        indent(sb, i);
        sb.append(ASCIIPropertyListParser.DICTIONARY_BEGIN_TOKEN);
        sb.append(NEWLINE);
        String[] strArr = (String[]) this.dict.keySet().toArray(new String[0]);
        for (String str : strArr) {
            NSObject objectForKey = objectForKey(str);
            indent(sb, i + 1);
            sb.append("\"");
            sb.append(NSString.escapeStringForASCII(str));
            sb.append("\" =");
            Class<?> cls = objectForKey.getClass();
            if (cls.equals(NSDictionary.class) || cls.equals(NSArray.class) || cls.equals(NSData.class)) {
                sb.append(NEWLINE);
                objectForKey.toASCIIGnuStep(sb, i + 2);
            } else {
                sb.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
                objectForKey.toASCIIGnuStep(sb, 0);
            }
            sb.append(';');
            sb.append(NEWLINE);
        }
        indent(sb, i);
        sb.append(ASCIIPropertyListParser.DICTIONARY_END_TOKEN);
    }
}
