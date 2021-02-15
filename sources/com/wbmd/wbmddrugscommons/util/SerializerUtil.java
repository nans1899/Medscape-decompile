package com.wbmd.wbmddrugscommons.util;

import com.wbmd.wbmddrugscommons.model.Drug;
import com.wbmd.wbmddrugscommons.model.Tug;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.List;

public class SerializerUtil {
    public static byte[] serialize(List<Drug> list) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            new ObjectOutputStream(byteArrayOutputStream).writeObject(list);
            return byteArrayOutputStream.toByteArray();
        } catch (Exception unused) {
            return null;
        }
    }

    public static List<Drug> deserialize(byte[] bArr) {
        try {
            return (List) new ObjectInputStream(new ByteArrayInputStream(bArr)).readObject();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static HashMap<String, Tug> deserializeTugs(byte[] bArr) {
        try {
            return (HashMap) new ObjectInputStream(new ByteArrayInputStream(bArr)).readObject();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Object deserializeString(byte[] bArr) {
        try {
            return new ObjectInputStream(new ByteArrayInputStream(bArr)).readObject();
        } catch (Exception unused) {
            return null;
        }
    }

    public static byte[] serialize(Object obj) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            new ObjectOutputStream(byteArrayOutputStream).writeObject(obj);
            return byteArrayOutputStream.toByteArray();
        } catch (Exception unused) {
            return null;
        }
    }

    public static byte[] serializeTugs(HashMap<String, Tug> hashMap) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            new ObjectOutputStream(byteArrayOutputStream).writeObject(hashMap);
            return byteArrayOutputStream.toByteArray();
        } catch (Exception unused) {
            return null;
        }
    }
}
