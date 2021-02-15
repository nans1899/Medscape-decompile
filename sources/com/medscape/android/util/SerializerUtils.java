package com.medscape.android.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerializerUtils {
    public static byte[] serialize(Object obj) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            new ObjectOutputStream(byteArrayOutputStream).writeObject(obj);
            return byteArrayOutputStream.toByteArray();
        } catch (Exception unused) {
            return null;
        }
    }

    public static Object deserialize(byte[] bArr) {
        try {
            return new ObjectInputStream(new ByteArrayInputStream(bArr)).readObject();
        } catch (Exception unused) {
            return null;
        }
    }
}
