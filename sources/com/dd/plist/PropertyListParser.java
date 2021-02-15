package com.dd.plist;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class PropertyListParser {
    protected PropertyListParser() {
    }

    protected static byte[] readAll(InputStream inputStream, int i) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        while (i > 0) {
            int read = inputStream.read();
            if (read == -1) {
                break;
            }
            byteArrayOutputStream.write(read);
            i--;
        }
        return byteArrayOutputStream.toByteArray();
    }

    public static NSObject parse(String str) throws Exception {
        return parse(new File(str));
    }

    public static NSObject parse(File file) throws Exception {
        FileInputStream fileInputStream = new FileInputStream(file);
        String str = new String(readAll(fileInputStream, 8), 0, 8);
        fileInputStream.close();
        if (str.startsWith("bplist")) {
            return BinaryPropertyListParser.parse(file);
        }
        if (str.trim().startsWith("(") || str.trim().startsWith("{") || str.trim().startsWith("/")) {
            return ASCIIPropertyListParser.parse(file);
        }
        return XMLPropertyListParser.parse(file);
    }

    public static NSObject parse(byte[] bArr) throws Exception {
        String str = new String(bArr, 0, 8);
        if (str.startsWith("bplist")) {
            return BinaryPropertyListParser.parse(bArr);
        }
        if (str.trim().startsWith("(") || str.trim().startsWith("{") || str.trim().startsWith("/")) {
            return ASCIIPropertyListParser.parse(bArr);
        }
        return XMLPropertyListParser.parse(bArr);
    }

    public static NSObject parse(InputStream inputStream) throws Exception {
        if (!inputStream.markSupported()) {
            return parse(readAll(inputStream, Integer.MAX_VALUE));
        }
        inputStream.mark(10);
        String str = new String(readAll(inputStream, 8), 0, 8);
        inputStream.reset();
        if (str.startsWith("bplist")) {
            return BinaryPropertyListParser.parse(inputStream);
        }
        if (str.trim().startsWith("(") || str.trim().startsWith("{") || str.trim().startsWith("/")) {
            return ASCIIPropertyListParser.parse(inputStream);
        }
        return XMLPropertyListParser.parse(inputStream);
    }

    public static void saveAsXML(NSObject nSObject, File file) throws IOException {
        File parentFile = file.getParentFile();
        if (!parentFile.exists()) {
            parentFile.mkdirs();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        saveAsXML(nSObject, (OutputStream) fileOutputStream);
        fileOutputStream.close();
    }

    public static void saveAsXML(NSObject nSObject, OutputStream outputStream) throws IOException {
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, "UTF-8");
        outputStreamWriter.write(nSObject.toXMLPropertyList());
        outputStreamWriter.close();
    }

    public static void convertToXml(File file, File file2) throws Exception {
        saveAsXML(parse(file), file2);
    }

    public static void saveAsBinary(NSObject nSObject, File file) throws IOException {
        File parentFile = file.getParentFile();
        if (!parentFile.exists()) {
            parentFile.mkdirs();
        }
        BinaryPropertyListWriter.write(file, nSObject);
    }

    public static void saveAsBinary(NSObject nSObject, OutputStream outputStream) throws IOException {
        BinaryPropertyListWriter.write(outputStream, nSObject);
    }

    public static void convertToBinary(File file, File file2) throws Exception {
        saveAsBinary(parse(file), file2);
    }

    public static void saveAsASCII(NSDictionary nSDictionary, File file) throws IOException {
        File parentFile = file.getParentFile();
        if (!parentFile.exists()) {
            parentFile.mkdirs();
        }
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(file), "ASCII");
        outputStreamWriter.write(nSDictionary.toASCIIPropertyList());
        outputStreamWriter.close();
    }

    public static void saveAsASCII(NSArray nSArray, File file) throws IOException {
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(file), "ASCII");
        outputStreamWriter.write(nSArray.toASCIIPropertyList());
        outputStreamWriter.close();
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|8) */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0018, code lost:
        throw new java.lang.Exception("The root of the given input property list is neither a Dictionary nor an Array!");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:?, code lost:
        return;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x000b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void convertToASCII(java.io.File r1, java.io.File r2) throws java.lang.Exception {
        /*
            com.dd.plist.NSObject r1 = parse((java.io.File) r1)
            r0 = r1
            com.dd.plist.NSDictionary r0 = (com.dd.plist.NSDictionary) r0     // Catch:{ Exception -> 0x000b }
            saveAsASCII((com.dd.plist.NSDictionary) r0, (java.io.File) r2)     // Catch:{ Exception -> 0x000b }
            goto L_0x0010
        L_0x000b:
            com.dd.plist.NSArray r1 = (com.dd.plist.NSArray) r1     // Catch:{ Exception -> 0x0011 }
            saveAsASCII((com.dd.plist.NSArray) r1, (java.io.File) r2)     // Catch:{ Exception -> 0x0011 }
        L_0x0010:
            return
        L_0x0011:
            java.lang.Exception r1 = new java.lang.Exception
            java.lang.String r2 = "The root of the given input property list is neither a Dictionary nor an Array!"
            r1.<init>(r2)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.dd.plist.PropertyListParser.convertToASCII(java.io.File, java.io.File):void");
    }

    public static void saveAsGnuStepASCII(NSDictionary nSDictionary, File file) throws IOException {
        File parentFile = file.getParentFile();
        if (!parentFile.exists()) {
            parentFile.mkdirs();
        }
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(file), "ASCII");
        outputStreamWriter.write(nSDictionary.toGnuStepASCIIPropertyList());
        outputStreamWriter.close();
    }

    public static void saveAsGnuStepASCII(NSArray nSArray, File file) throws IOException {
        File parentFile = file.getParentFile();
        if (!parentFile.exists()) {
            parentFile.mkdirs();
        }
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(file), "ASCII");
        outputStreamWriter.write(nSArray.toGnuStepASCIIPropertyList());
        outputStreamWriter.close();
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|8) */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0018, code lost:
        throw new java.lang.Exception("The root of the given input property list is neither a Dictionary nor an Array!");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:?, code lost:
        return;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x000b */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void convertToGnuStepASCII(java.io.File r1, java.io.File r2) throws java.lang.Exception {
        /*
            com.dd.plist.NSObject r1 = parse((java.io.File) r1)
            r0 = r1
            com.dd.plist.NSDictionary r0 = (com.dd.plist.NSDictionary) r0     // Catch:{ Exception -> 0x000b }
            saveAsGnuStepASCII((com.dd.plist.NSDictionary) r0, (java.io.File) r2)     // Catch:{ Exception -> 0x000b }
            goto L_0x0010
        L_0x000b:
            com.dd.plist.NSArray r1 = (com.dd.plist.NSArray) r1     // Catch:{ Exception -> 0x0011 }
            saveAsGnuStepASCII((com.dd.plist.NSArray) r1, (java.io.File) r2)     // Catch:{ Exception -> 0x0011 }
        L_0x0010:
            return
        L_0x0011:
            java.lang.Exception r1 = new java.lang.Exception
            java.lang.String r2 = "The root of the given input property list is neither a Dictionary nor an Array!"
            r1.<init>(r2)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.dd.plist.PropertyListParser.convertToGnuStepASCII(java.io.File, java.io.File):void");
    }
}
