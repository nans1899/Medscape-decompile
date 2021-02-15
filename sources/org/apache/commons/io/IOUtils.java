package org.apache.commons.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.CharArrayWriter;
import java.io.Closeable;
import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.Selector;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import org.apache.commons.io.function.IOConsumer;
import org.apache.commons.io.output.AppendableWriter;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.commons.io.output.NullOutputStream;
import org.apache.commons.io.output.StringBuilderWriter;

public class IOUtils {
    public static final int DEFAULT_BUFFER_SIZE = 8192;
    public static final char DIR_SEPARATOR = File.separatorChar;
    public static final char DIR_SEPARATOR_UNIX = '/';
    public static final char DIR_SEPARATOR_WINDOWS = '\\';
    private static final byte[] EMPTY_BYTE_ARRAY = new byte[0];
    public static final int EOF = -1;
    @Deprecated
    public static final String LINE_SEPARATOR = System.lineSeparator();
    public static final String LINE_SEPARATOR_UNIX = "\n";
    public static final String LINE_SEPARATOR_WINDOWS = "\r\n";
    private static final byte[] SKIP_BYTE_BUFFER = new byte[8192];
    private static char[] SKIP_CHAR_BUFFER;

    public static BufferedInputStream buffer(InputStream inputStream) {
        Objects.requireNonNull(inputStream, "inputStream");
        return inputStream instanceof BufferedInputStream ? (BufferedInputStream) inputStream : new BufferedInputStream(inputStream);
    }

    public static BufferedInputStream buffer(InputStream inputStream, int i) {
        Objects.requireNonNull(inputStream, "inputStream");
        return inputStream instanceof BufferedInputStream ? (BufferedInputStream) inputStream : new BufferedInputStream(inputStream, i);
    }

    public static BufferedOutputStream buffer(OutputStream outputStream) {
        Objects.requireNonNull(outputStream, "outputStream");
        return outputStream instanceof BufferedOutputStream ? (BufferedOutputStream) outputStream : new BufferedOutputStream(outputStream);
    }

    public static BufferedOutputStream buffer(OutputStream outputStream, int i) {
        Objects.requireNonNull(outputStream, "outputStream");
        return outputStream instanceof BufferedOutputStream ? (BufferedOutputStream) outputStream : new BufferedOutputStream(outputStream, i);
    }

    public static BufferedReader buffer(Reader reader) {
        return reader instanceof BufferedReader ? (BufferedReader) reader : new BufferedReader(reader);
    }

    public static BufferedReader buffer(Reader reader, int i) {
        return reader instanceof BufferedReader ? (BufferedReader) reader : new BufferedReader(reader, i);
    }

    public static BufferedWriter buffer(Writer writer) {
        return writer instanceof BufferedWriter ? (BufferedWriter) writer : new BufferedWriter(writer);
    }

    public static BufferedWriter buffer(Writer writer, int i) {
        return writer instanceof BufferedWriter ? (BufferedWriter) writer : new BufferedWriter(writer, i);
    }

    public static void close(Closeable closeable) throws IOException {
        if (closeable != null) {
            closeable.close();
        }
    }

    public static void close(Closeable... closeableArr) throws IOException {
        if (closeableArr != null) {
            for (Closeable close : closeableArr) {
                close(close);
            }
        }
    }

    public static void close(Closeable closeable, IOConsumer<IOException> iOConsumer) throws IOException {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                if (iOConsumer != null) {
                    iOConsumer.accept(e);
                }
            }
        }
    }

    public static void close(URLConnection uRLConnection) {
        if (uRLConnection instanceof HttpURLConnection) {
            ((HttpURLConnection) uRLConnection).disconnect();
        }
    }

    @Deprecated
    public static void closeQuietly(Closeable closeable) {
        closeQuietly(closeable, (Consumer<IOException>) null);
    }

    @Deprecated
    public static void closeQuietly(Closeable... closeableArr) {
        if (closeableArr != null) {
            for (Closeable closeQuietly : closeableArr) {
                closeQuietly(closeQuietly);
            }
        }
    }

    public static void closeQuietly(Closeable closeable, Consumer<IOException> consumer) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                if (consumer != null) {
                    consumer.accept(e);
                }
            }
        }
    }

    @Deprecated
    public static void closeQuietly(InputStream inputStream) {
        closeQuietly((Closeable) inputStream);
    }

    @Deprecated
    public static void closeQuietly(OutputStream outputStream) {
        closeQuietly((Closeable) outputStream);
    }

    @Deprecated
    public static void closeQuietly(Reader reader) {
        closeQuietly((Closeable) reader);
    }

    @Deprecated
    public static void closeQuietly(Selector selector) {
        closeQuietly((Closeable) selector);
    }

    @Deprecated
    public static void closeQuietly(ServerSocket serverSocket) {
        closeQuietly((Closeable) serverSocket);
    }

    @Deprecated
    public static void closeQuietly(Socket socket) {
        closeQuietly((Closeable) socket);
    }

    @Deprecated
    public static void closeQuietly(Writer writer) {
        closeQuietly((Closeable) writer);
    }

    public static long consume(InputStream inputStream) throws IOException {
        return copyLarge(inputStream, (OutputStream) NullOutputStream.NULL_OUTPUT_STREAM, SKIP_BYTE_BUFFER);
    }

    public static boolean contentEquals(InputStream inputStream, InputStream inputStream2) throws IOException {
        if (inputStream == inputStream2) {
            return true;
        }
        if ((inputStream == null) ^ (inputStream2 == null)) {
            return false;
        }
        BufferedInputStream buffer = buffer(inputStream);
        BufferedInputStream buffer2 = buffer(inputStream2);
        for (int read = buffer.read(); -1 != read; read = buffer.read()) {
            if (read != buffer2.read()) {
                return false;
            }
        }
        if (buffer2.read() == -1) {
            return true;
        }
        return false;
    }

    public static boolean contentEquals(Reader reader, Reader reader2) throws IOException {
        if (reader == reader2) {
            return true;
        }
        if ((reader == null) ^ (reader2 == null)) {
            return false;
        }
        BufferedReader bufferedReader = toBufferedReader(reader);
        BufferedReader bufferedReader2 = toBufferedReader(reader2);
        for (int read = bufferedReader.read(); -1 != read; read = bufferedReader.read()) {
            if (read != bufferedReader2.read()) {
                return false;
            }
        }
        if (bufferedReader2.read() == -1) {
            return true;
        }
        return false;
    }

    public static boolean contentEqualsIgnoreEOL(Reader reader, Reader reader2) throws IOException {
        boolean z = true;
        if (reader == reader2) {
            return true;
        }
        boolean z2 = reader == null;
        if (reader2 != null) {
            z = false;
        }
        if (z ^ z2) {
            return false;
        }
        BufferedReader bufferedReader = toBufferedReader(reader);
        BufferedReader bufferedReader2 = toBufferedReader(reader2);
        String readLine = bufferedReader.readLine();
        String readLine2 = bufferedReader2.readLine();
        while (readLine != null && readLine.equals(readLine2)) {
            readLine = bufferedReader.readLine();
            readLine2 = bufferedReader2.readLine();
        }
        return Objects.equals(readLine, readLine2);
    }

    public static int copy(InputStream inputStream, OutputStream outputStream) throws IOException {
        long copyLarge = copyLarge(inputStream, outputStream);
        if (copyLarge > 2147483647L) {
            return -1;
        }
        return (int) copyLarge;
    }

    public static long copy(InputStream inputStream, OutputStream outputStream, int i) throws IOException {
        return copyLarge(inputStream, outputStream, new byte[i]);
    }

    @Deprecated
    public static void copy(InputStream inputStream, Writer writer) throws IOException {
        copy(inputStream, writer, Charset.defaultCharset());
    }

    public static void copy(InputStream inputStream, Writer writer, Charset charset) throws IOException {
        copy((Reader) new InputStreamReader(inputStream, Charsets.toCharset(charset)), writer);
    }

    public static void copy(InputStream inputStream, Writer writer, String str) throws IOException {
        copy(inputStream, writer, Charsets.toCharset(str));
    }

    public static long copy(Reader reader, Appendable appendable) throws IOException {
        return copy(reader, appendable, CharBuffer.allocate(8192));
    }

    public static long copy(Reader reader, Appendable appendable, CharBuffer charBuffer) throws IOException {
        long j = 0;
        while (true) {
            int read = reader.read(charBuffer);
            if (-1 == read) {
                return j;
            }
            charBuffer.flip();
            appendable.append(charBuffer, 0, read);
            j += (long) read;
        }
    }

    @Deprecated
    public static void copy(Reader reader, OutputStream outputStream) throws IOException {
        copy(reader, outputStream, Charset.defaultCharset());
    }

    public static void copy(Reader reader, OutputStream outputStream, Charset charset) throws IOException {
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, Charsets.toCharset(charset));
        copy(reader, (Writer) outputStreamWriter);
        outputStreamWriter.flush();
    }

    public static void copy(Reader reader, OutputStream outputStream, String str) throws IOException {
        copy(reader, outputStream, Charsets.toCharset(str));
    }

    public static int copy(Reader reader, Writer writer) throws IOException {
        long copyLarge = copyLarge(reader, writer);
        if (copyLarge > 2147483647L) {
            return -1;
        }
        return (int) copyLarge;
    }

    public static long copyLarge(InputStream inputStream, OutputStream outputStream) throws IOException {
        return copy(inputStream, outputStream, 8192);
    }

    public static long copyLarge(InputStream inputStream, OutputStream outputStream, byte[] bArr) throws IOException {
        long j = 0;
        if (inputStream != null) {
            while (true) {
                int read = inputStream.read(bArr);
                if (-1 == read) {
                    break;
                }
                outputStream.write(bArr, 0, read);
                j += (long) read;
            }
        }
        return j;
    }

    public static long copyLarge(InputStream inputStream, OutputStream outputStream, long j, long j2) throws IOException {
        return copyLarge(inputStream, outputStream, j, j2, new byte[8192]);
    }

    public static long copyLarge(InputStream inputStream, OutputStream outputStream, long j, long j2, byte[] bArr) throws IOException {
        long j3 = 0;
        if (j > 0) {
            skipFully(inputStream, j);
        }
        int i = (j2 > 0 ? 1 : (j2 == 0 ? 0 : -1));
        if (i == 0) {
            return 0;
        }
        int length = bArr.length;
        int i2 = (i <= 0 || j2 >= ((long) length)) ? length : (int) j2;
        while (i2 > 0) {
            int read = inputStream.read(bArr, 0, i2);
            if (-1 == read) {
                break;
            }
            outputStream.write(bArr, 0, read);
            j3 += (long) read;
            if (i > 0) {
                i2 = (int) Math.min(j2 - j3, (long) length);
            }
        }
        return j3;
    }

    public static long copyLarge(Reader reader, Writer writer) throws IOException {
        return copyLarge(reader, writer, new char[8192]);
    }

    public static long copyLarge(Reader reader, Writer writer, char[] cArr) throws IOException {
        long j = 0;
        while (true) {
            int read = reader.read(cArr);
            if (-1 == read) {
                return j;
            }
            writer.write(cArr, 0, read);
            j += (long) read;
        }
    }

    public static long copyLarge(Reader reader, Writer writer, long j, long j2) throws IOException {
        return copyLarge(reader, writer, j, j2, new char[8192]);
    }

    public static long copyLarge(Reader reader, Writer writer, long j, long j2, char[] cArr) throws IOException {
        long j3 = 0;
        if (j > 0) {
            skipFully(reader, j);
        }
        int i = (j2 > 0 ? 1 : (j2 == 0 ? 0 : -1));
        if (i == 0) {
            return 0;
        }
        int length = cArr.length;
        if (i > 0 && j2 < ((long) cArr.length)) {
            length = (int) j2;
        }
        while (length > 0) {
            int read = reader.read(cArr, 0, length);
            if (-1 == read) {
                break;
            }
            writer.write(cArr, 0, read);
            j3 += (long) read;
            if (i > 0) {
                length = (int) Math.min(j2 - j3, (long) cArr.length);
            }
        }
        return j3;
    }

    public static int length(byte[] bArr) {
        if (bArr == null) {
            return 0;
        }
        return bArr.length;
    }

    public static int length(char[] cArr) {
        if (cArr == null) {
            return 0;
        }
        return cArr.length;
    }

    public static int length(CharSequence charSequence) {
        if (charSequence == null) {
            return 0;
        }
        return charSequence.length();
    }

    public static int length(Object[] objArr) {
        if (objArr == null) {
            return 0;
        }
        return objArr.length;
    }

    public static LineIterator lineIterator(InputStream inputStream, Charset charset) throws IOException {
        return new LineIterator(new InputStreamReader(inputStream, Charsets.toCharset(charset)));
    }

    public static LineIterator lineIterator(InputStream inputStream, String str) throws IOException {
        return lineIterator(inputStream, Charsets.toCharset(str));
    }

    public static LineIterator lineIterator(Reader reader) {
        return new LineIterator(reader);
    }

    public static int read(InputStream inputStream, byte[] bArr) throws IOException {
        return read(inputStream, bArr, 0, bArr.length);
    }

    public static int read(InputStream inputStream, byte[] bArr, int i, int i2) throws IOException {
        if (i2 >= 0) {
            int i3 = i2;
            while (i3 > 0) {
                int read = inputStream.read(bArr, (i2 - i3) + i, i3);
                if (-1 == read) {
                    break;
                }
                i3 -= read;
            }
            return i2 - i3;
        }
        throw new IllegalArgumentException("Length must not be negative: " + i2);
    }

    /* JADX WARNING: Removed duplicated region for block: B:1:0x0004 A[LOOP:0: B:1:0x0004->B:4:0x000f, LOOP_START] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int read(java.nio.channels.ReadableByteChannel r3, java.nio.ByteBuffer r4) throws java.io.IOException {
        /*
            int r0 = r4.remaining()
        L_0x0004:
            int r1 = r4.remaining()
            if (r1 <= 0) goto L_0x0011
            int r1 = r3.read(r4)
            r2 = -1
            if (r2 != r1) goto L_0x0004
        L_0x0011:
            int r3 = r4.remaining()
            int r0 = r0 - r3
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.io.IOUtils.read(java.nio.channels.ReadableByteChannel, java.nio.ByteBuffer):int");
    }

    public static int read(Reader reader, char[] cArr) throws IOException {
        return read(reader, cArr, 0, cArr.length);
    }

    public static int read(Reader reader, char[] cArr, int i, int i2) throws IOException {
        if (i2 >= 0) {
            int i3 = i2;
            while (i3 > 0) {
                int read = reader.read(cArr, (i2 - i3) + i, i3);
                if (-1 == read) {
                    break;
                }
                i3 -= read;
            }
            return i2 - i3;
        }
        throw new IllegalArgumentException("Length must not be negative: " + i2);
    }

    public static void readFully(InputStream inputStream, byte[] bArr) throws IOException {
        readFully(inputStream, bArr, 0, bArr.length);
    }

    public static void readFully(InputStream inputStream, byte[] bArr, int i, int i2) throws IOException {
        int read = read(inputStream, bArr, i, i2);
        if (read != i2) {
            throw new EOFException("Length to read: " + i2 + " actual: " + read);
        }
    }

    public static byte[] readFully(InputStream inputStream, int i) throws IOException {
        byte[] bArr = new byte[i];
        readFully(inputStream, bArr, 0, i);
        return bArr;
    }

    public static void readFully(ReadableByteChannel readableByteChannel, ByteBuffer byteBuffer) throws IOException {
        int remaining = byteBuffer.remaining();
        int read = read(readableByteChannel, byteBuffer);
        if (read != remaining) {
            throw new EOFException("Length to read: " + remaining + " actual: " + read);
        }
    }

    public static void readFully(Reader reader, char[] cArr) throws IOException {
        readFully(reader, cArr, 0, cArr.length);
    }

    public static void readFully(Reader reader, char[] cArr, int i, int i2) throws IOException {
        int read = read(reader, cArr, i, i2);
        if (read != i2) {
            throw new EOFException("Length to read: " + i2 + " actual: " + read);
        }
    }

    @Deprecated
    public static List<String> readLines(InputStream inputStream) throws IOException {
        return readLines(inputStream, Charset.defaultCharset());
    }

    public static List<String> readLines(InputStream inputStream, Charset charset) throws IOException {
        return readLines((Reader) new InputStreamReader(inputStream, Charsets.toCharset(charset)));
    }

    public static List<String> readLines(InputStream inputStream, String str) throws IOException {
        return readLines(inputStream, Charsets.toCharset(str));
    }

    public static List<String> readLines(Reader reader) throws IOException {
        BufferedReader bufferedReader = toBufferedReader(reader);
        ArrayList arrayList = new ArrayList();
        while (true) {
            String readLine = bufferedReader.readLine();
            if (readLine == null) {
                return arrayList;
            }
            arrayList.add(readLine);
        }
    }

    public static byte[] resourceToByteArray(String str) throws IOException {
        return resourceToByteArray(str, (ClassLoader) null);
    }

    public static byte[] resourceToByteArray(String str, ClassLoader classLoader) throws IOException {
        return toByteArray(resourceToURL(str, classLoader));
    }

    public static String resourceToString(String str, Charset charset) throws IOException {
        return resourceToString(str, charset, (ClassLoader) null);
    }

    public static String resourceToString(String str, Charset charset, ClassLoader classLoader) throws IOException {
        return toString(resourceToURL(str, classLoader), charset);
    }

    public static URL resourceToURL(String str) throws IOException {
        return resourceToURL(str, (ClassLoader) null);
    }

    public static URL resourceToURL(String str, ClassLoader classLoader) throws IOException {
        URL resource = classLoader == null ? IOUtils.class.getResource(str) : classLoader.getResource(str);
        if (resource != null) {
            return resource;
        }
        throw new IOException("Resource not found: " + str);
    }

    public static long skip(InputStream inputStream, long j) throws IOException {
        if (j >= 0) {
            long j2 = j;
            while (j2 > 0) {
                byte[] bArr = SKIP_BYTE_BUFFER;
                long read = (long) inputStream.read(bArr, 0, (int) Math.min(j2, (long) bArr.length));
                if (read < 0) {
                    break;
                }
                j2 -= read;
            }
            return j - j2;
        }
        throw new IllegalArgumentException("Skip count must be non-negative, actual: " + j);
    }

    public static long skip(ReadableByteChannel readableByteChannel, long j) throws IOException {
        if (j >= 0) {
            ByteBuffer allocate = ByteBuffer.allocate((int) Math.min(j, (long) SKIP_BYTE_BUFFER.length));
            long j2 = j;
            while (j2 > 0) {
                allocate.position(0);
                allocate.limit((int) Math.min(j2, (long) SKIP_BYTE_BUFFER.length));
                int read = readableByteChannel.read(allocate);
                if (read == -1) {
                    break;
                }
                j2 -= (long) read;
            }
            return j - j2;
        }
        throw new IllegalArgumentException("Skip count must be non-negative, actual: " + j);
    }

    public static long skip(Reader reader, long j) throws IOException {
        if (j >= 0) {
            if (SKIP_CHAR_BUFFER == null) {
                SKIP_CHAR_BUFFER = new char[SKIP_BYTE_BUFFER.length];
            }
            long j2 = j;
            while (j2 > 0) {
                long read = (long) reader.read(SKIP_CHAR_BUFFER, 0, (int) Math.min(j2, (long) SKIP_BYTE_BUFFER.length));
                if (read < 0) {
                    break;
                }
                j2 -= read;
            }
            return j - j2;
        }
        throw new IllegalArgumentException("Skip count must be non-negative, actual: " + j);
    }

    public static void skipFully(InputStream inputStream, long j) throws IOException {
        if (j >= 0) {
            long skip = skip(inputStream, j);
            if (skip != j) {
                throw new EOFException("Bytes to skip: " + j + " actual: " + skip);
            }
            return;
        }
        throw new IllegalArgumentException("Bytes to skip must not be negative: " + j);
    }

    public static void skipFully(ReadableByteChannel readableByteChannel, long j) throws IOException {
        if (j >= 0) {
            long skip = skip(readableByteChannel, j);
            if (skip != j) {
                throw new EOFException("Bytes to skip: " + j + " actual: " + skip);
            }
            return;
        }
        throw new IllegalArgumentException("Bytes to skip must not be negative: " + j);
    }

    public static void skipFully(Reader reader, long j) throws IOException {
        long skip = skip(reader, j);
        if (skip != j) {
            throw new EOFException("Chars to skip: " + j + " actual: " + skip);
        }
    }

    public static InputStream toBufferedInputStream(InputStream inputStream) throws IOException {
        return ByteArrayOutputStream.toBufferedInputStream(inputStream);
    }

    public static InputStream toBufferedInputStream(InputStream inputStream, int i) throws IOException {
        return ByteArrayOutputStream.toBufferedInputStream(inputStream, i);
    }

    public static BufferedReader toBufferedReader(Reader reader) {
        return reader instanceof BufferedReader ? (BufferedReader) reader : new BufferedReader(reader);
    }

    public static BufferedReader toBufferedReader(Reader reader, int i) {
        return reader instanceof BufferedReader ? (BufferedReader) reader : new BufferedReader(reader, i);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0017, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0018, code lost:
        r2.addSuppressed(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001b, code lost:
        throw r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0012, code lost:
        r1 = move-exception;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static byte[] toByteArray(java.io.InputStream r2) throws java.io.IOException {
        /*
            org.apache.commons.io.output.ByteArrayOutputStream r0 = new org.apache.commons.io.output.ByteArrayOutputStream
            r0.<init>()
            copy((java.io.InputStream) r2, (java.io.OutputStream) r0)     // Catch:{ all -> 0x0010 }
            byte[] r2 = r0.toByteArray()     // Catch:{ all -> 0x0010 }
            r0.close()
            return r2
        L_0x0010:
            r2 = move-exception
            throw r2     // Catch:{ all -> 0x0012 }
        L_0x0012:
            r1 = move-exception
            r0.close()     // Catch:{ all -> 0x0017 }
            goto L_0x001b
        L_0x0017:
            r0 = move-exception
            r2.addSuppressed(r0)
        L_0x001b:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.io.IOUtils.toByteArray(java.io.InputStream):byte[]");
    }

    public static byte[] toByteArray(InputStream inputStream, int i) throws IOException {
        if (i < 0) {
            throw new IllegalArgumentException("Size must be equal or greater than zero: " + i);
        } else if (i == 0) {
            return EMPTY_BYTE_ARRAY;
        } else {
            byte[] bArr = new byte[i];
            int i2 = 0;
            while (i2 < i) {
                int read = inputStream.read(bArr, i2, i - i2);
                if (read == -1) {
                    break;
                }
                i2 += read;
            }
            if (i2 == i) {
                return bArr;
            }
            throw new IOException("Unexpected read size. current: " + i2 + ", expected: " + i);
        }
    }

    public static byte[] toByteArray(InputStream inputStream, long j) throws IOException {
        if (j <= 2147483647L) {
            return toByteArray(inputStream, (int) j);
        }
        throw new IllegalArgumentException("Size cannot be greater than Integer max value: " + j);
    }

    @Deprecated
    public static byte[] toByteArray(Reader reader) throws IOException {
        return toByteArray(reader, Charset.defaultCharset());
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0017, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0018, code lost:
        r1.addSuppressed(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001b, code lost:
        throw r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0012, code lost:
        r2 = move-exception;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static byte[] toByteArray(java.io.Reader r1, java.nio.charset.Charset r2) throws java.io.IOException {
        /*
            org.apache.commons.io.output.ByteArrayOutputStream r0 = new org.apache.commons.io.output.ByteArrayOutputStream
            r0.<init>()
            copy((java.io.Reader) r1, (java.io.OutputStream) r0, (java.nio.charset.Charset) r2)     // Catch:{ all -> 0x0010 }
            byte[] r1 = r0.toByteArray()     // Catch:{ all -> 0x0010 }
            r0.close()
            return r1
        L_0x0010:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x0012 }
        L_0x0012:
            r2 = move-exception
            r0.close()     // Catch:{ all -> 0x0017 }
            goto L_0x001b
        L_0x0017:
            r0 = move-exception
            r1.addSuppressed(r0)
        L_0x001b:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.io.IOUtils.toByteArray(java.io.Reader, java.nio.charset.Charset):byte[]");
    }

    public static byte[] toByteArray(Reader reader, String str) throws IOException {
        return toByteArray(reader, Charsets.toCharset(str));
    }

    @Deprecated
    public static byte[] toByteArray(String str) throws IOException {
        return str.getBytes(Charset.defaultCharset());
    }

    public static byte[] toByteArray(URI uri) throws IOException {
        return toByteArray(uri.toURL());
    }

    public static byte[] toByteArray(URL url) throws IOException {
        URLConnection openConnection = url.openConnection();
        try {
            return toByteArray(openConnection);
        } finally {
            close(openConnection);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0011, code lost:
        if (r2 != null) goto L_0x0013;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:?, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0017, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0018, code lost:
        r0.addSuppressed(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x001b, code lost:
        throw r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0010, code lost:
        r1 = move-exception;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static byte[] toByteArray(java.net.URLConnection r2) throws java.io.IOException {
        /*
            java.io.InputStream r2 = r2.getInputStream()
            byte[] r0 = toByteArray((java.io.InputStream) r2)     // Catch:{ all -> 0x000e }
            if (r2 == 0) goto L_0x000d
            r2.close()
        L_0x000d:
            return r0
        L_0x000e:
            r0 = move-exception
            throw r0     // Catch:{ all -> 0x0010 }
        L_0x0010:
            r1 = move-exception
            if (r2 == 0) goto L_0x001b
            r2.close()     // Catch:{ all -> 0x0017 }
            goto L_0x001b
        L_0x0017:
            r2 = move-exception
            r0.addSuppressed(r2)
        L_0x001b:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.io.IOUtils.toByteArray(java.net.URLConnection):byte[]");
    }

    @Deprecated
    public static char[] toCharArray(InputStream inputStream) throws IOException {
        return toCharArray(inputStream, Charset.defaultCharset());
    }

    public static char[] toCharArray(InputStream inputStream, Charset charset) throws IOException {
        CharArrayWriter charArrayWriter = new CharArrayWriter();
        copy(inputStream, (Writer) charArrayWriter, charset);
        return charArrayWriter.toCharArray();
    }

    public static char[] toCharArray(InputStream inputStream, String str) throws IOException {
        return toCharArray(inputStream, Charsets.toCharset(str));
    }

    public static char[] toCharArray(Reader reader) throws IOException {
        CharArrayWriter charArrayWriter = new CharArrayWriter();
        copy(reader, (Writer) charArrayWriter);
        return charArrayWriter.toCharArray();
    }

    @Deprecated
    public static InputStream toInputStream(CharSequence charSequence) {
        return toInputStream(charSequence, Charset.defaultCharset());
    }

    public static InputStream toInputStream(CharSequence charSequence, Charset charset) {
        return toInputStream(charSequence.toString(), charset);
    }

    public static InputStream toInputStream(CharSequence charSequence, String str) throws IOException {
        return toInputStream(charSequence, Charsets.toCharset(str));
    }

    @Deprecated
    public static InputStream toInputStream(String str) {
        return toInputStream(str, Charset.defaultCharset());
    }

    public static InputStream toInputStream(String str, Charset charset) {
        return new ByteArrayInputStream(str.getBytes(Charsets.toCharset(charset)));
    }

    public static InputStream toInputStream(String str, String str2) throws IOException {
        return new ByteArrayInputStream(str.getBytes(Charsets.toCharset(str2)));
    }

    @Deprecated
    public static String toString(byte[] bArr) throws IOException {
        return new String(bArr, Charset.defaultCharset());
    }

    public static String toString(byte[] bArr, String str) throws IOException {
        return new String(bArr, Charsets.toCharset(str));
    }

    @Deprecated
    public static String toString(InputStream inputStream) throws IOException {
        return toString(inputStream, Charset.defaultCharset());
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0017, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0018, code lost:
        r1.addSuppressed(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001b, code lost:
        throw r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0012, code lost:
        r2 = move-exception;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String toString(java.io.InputStream r1, java.nio.charset.Charset r2) throws java.io.IOException {
        /*
            org.apache.commons.io.output.StringBuilderWriter r0 = new org.apache.commons.io.output.StringBuilderWriter
            r0.<init>()
            copy((java.io.InputStream) r1, (java.io.Writer) r0, (java.nio.charset.Charset) r2)     // Catch:{ all -> 0x0010 }
            java.lang.String r1 = r0.toString()     // Catch:{ all -> 0x0010 }
            r0.close()
            return r1
        L_0x0010:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x0012 }
        L_0x0012:
            r2 = move-exception
            r0.close()     // Catch:{ all -> 0x0017 }
            goto L_0x001b
        L_0x0017:
            r0 = move-exception
            r1.addSuppressed(r0)
        L_0x001b:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.io.IOUtils.toString(java.io.InputStream, java.nio.charset.Charset):java.lang.String");
    }

    public static String toString(InputStream inputStream, String str) throws IOException {
        return toString(inputStream, Charsets.toCharset(str));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0017, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0018, code lost:
        r2.addSuppressed(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001b, code lost:
        throw r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0012, code lost:
        r1 = move-exception;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String toString(java.io.Reader r2) throws java.io.IOException {
        /*
            org.apache.commons.io.output.StringBuilderWriter r0 = new org.apache.commons.io.output.StringBuilderWriter
            r0.<init>()
            copy((java.io.Reader) r2, (java.io.Writer) r0)     // Catch:{ all -> 0x0010 }
            java.lang.String r2 = r0.toString()     // Catch:{ all -> 0x0010 }
            r0.close()
            return r2
        L_0x0010:
            r2 = move-exception
            throw r2     // Catch:{ all -> 0x0012 }
        L_0x0012:
            r1 = move-exception
            r0.close()     // Catch:{ all -> 0x0017 }
            goto L_0x001b
        L_0x0017:
            r0 = move-exception
            r2.addSuppressed(r0)
        L_0x001b:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.io.IOUtils.toString(java.io.Reader):java.lang.String");
    }

    @Deprecated
    public static String toString(URI uri) throws IOException {
        return toString(uri, Charset.defaultCharset());
    }

    public static String toString(URI uri, Charset charset) throws IOException {
        return toString(uri.toURL(), Charsets.toCharset(charset));
    }

    public static String toString(URI uri, String str) throws IOException {
        return toString(uri, Charsets.toCharset(str));
    }

    @Deprecated
    public static String toString(URL url) throws IOException {
        return toString(url, Charset.defaultCharset());
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0011, code lost:
        if (r1 != null) goto L_0x0013;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0017, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0018, code lost:
        r2.addSuppressed(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x001b, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0010, code lost:
        r0 = move-exception;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String toString(java.net.URL r1, java.nio.charset.Charset r2) throws java.io.IOException {
        /*
            java.io.InputStream r1 = r1.openStream()
            java.lang.String r2 = toString((java.io.InputStream) r1, (java.nio.charset.Charset) r2)     // Catch:{ all -> 0x000e }
            if (r1 == 0) goto L_0x000d
            r1.close()
        L_0x000d:
            return r2
        L_0x000e:
            r2 = move-exception
            throw r2     // Catch:{ all -> 0x0010 }
        L_0x0010:
            r0 = move-exception
            if (r1 == 0) goto L_0x001b
            r1.close()     // Catch:{ all -> 0x0017 }
            goto L_0x001b
        L_0x0017:
            r1 = move-exception
            r2.addSuppressed(r1)
        L_0x001b:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.io.IOUtils.toString(java.net.URL, java.nio.charset.Charset):java.lang.String");
    }

    public static String toString(URL url, String str) throws IOException {
        return toString(url, Charsets.toCharset(str));
    }

    public static void write(byte[] bArr, OutputStream outputStream) throws IOException {
        if (bArr != null) {
            outputStream.write(bArr);
        }
    }

    @Deprecated
    public static void write(byte[] bArr, Writer writer) throws IOException {
        write(bArr, writer, Charset.defaultCharset());
    }

    public static void write(byte[] bArr, Writer writer, Charset charset) throws IOException {
        if (bArr != null) {
            writer.write(new String(bArr, Charsets.toCharset(charset)));
        }
    }

    public static void write(byte[] bArr, Writer writer, String str) throws IOException {
        write(bArr, writer, Charsets.toCharset(str));
    }

    @Deprecated
    public static void write(char[] cArr, OutputStream outputStream) throws IOException {
        write(cArr, outputStream, Charset.defaultCharset());
    }

    public static void write(char[] cArr, OutputStream outputStream, Charset charset) throws IOException {
        if (cArr != null) {
            outputStream.write(new String(cArr).getBytes(Charsets.toCharset(charset)));
        }
    }

    public static void write(char[] cArr, OutputStream outputStream, String str) throws IOException {
        write(cArr, outputStream, Charsets.toCharset(str));
    }

    public static void write(char[] cArr, Writer writer) throws IOException {
        if (cArr != null) {
            writer.write(cArr);
        }
    }

    @Deprecated
    public static void write(CharSequence charSequence, OutputStream outputStream) throws IOException {
        write(charSequence, outputStream, Charset.defaultCharset());
    }

    public static void write(CharSequence charSequence, OutputStream outputStream, Charset charset) throws IOException {
        if (charSequence != null) {
            write(charSequence.toString(), outputStream, charset);
        }
    }

    public static void write(CharSequence charSequence, OutputStream outputStream, String str) throws IOException {
        write(charSequence, outputStream, Charsets.toCharset(str));
    }

    public static void write(CharSequence charSequence, Writer writer) throws IOException {
        if (charSequence != null) {
            write(charSequence.toString(), writer);
        }
    }

    @Deprecated
    public static void write(String str, OutputStream outputStream) throws IOException {
        write(str, outputStream, Charset.defaultCharset());
    }

    public static void write(String str, OutputStream outputStream, Charset charset) throws IOException {
        if (str != null) {
            outputStream.write(str.getBytes(Charsets.toCharset(charset)));
        }
    }

    public static void write(String str, OutputStream outputStream, String str2) throws IOException {
        write(str, outputStream, Charsets.toCharset(str2));
    }

    public static void write(String str, Writer writer) throws IOException {
        if (str != null) {
            writer.write(str);
        }
    }

    @Deprecated
    public static void write(StringBuffer stringBuffer, OutputStream outputStream) throws IOException {
        write(stringBuffer, outputStream, (String) null);
    }

    @Deprecated
    public static void write(StringBuffer stringBuffer, OutputStream outputStream, String str) throws IOException {
        if (stringBuffer != null) {
            outputStream.write(stringBuffer.toString().getBytes(Charsets.toCharset(str)));
        }
    }

    @Deprecated
    public static void write(StringBuffer stringBuffer, Writer writer) throws IOException {
        if (stringBuffer != null) {
            writer.write(stringBuffer.toString());
        }
    }

    public static void writeChunked(byte[] bArr, OutputStream outputStream) throws IOException {
        if (bArr != null) {
            int length = bArr.length;
            int i = 0;
            while (length > 0) {
                int min = Math.min(length, 8192);
                outputStream.write(bArr, i, min);
                length -= min;
                i += min;
            }
        }
    }

    public static void writeChunked(char[] cArr, Writer writer) throws IOException {
        if (cArr != null) {
            int length = cArr.length;
            int i = 0;
            while (length > 0) {
                int min = Math.min(length, 8192);
                writer.write(cArr, i, min);
                length -= min;
                i += min;
            }
        }
    }

    @Deprecated
    public static void writeLines(Collection<?> collection, String str, OutputStream outputStream) throws IOException {
        writeLines(collection, str, outputStream, Charset.defaultCharset());
    }

    public static void writeLines(Collection<?> collection, String str, OutputStream outputStream, Charset charset) throws IOException {
        if (collection != null) {
            if (str == null) {
                str = System.lineSeparator();
            }
            Charset charset2 = Charsets.toCharset(charset);
            for (Object next : collection) {
                if (next != null) {
                    outputStream.write(next.toString().getBytes(charset2));
                }
                outputStream.write(str.getBytes(charset2));
            }
        }
    }

    public static void writeLines(Collection<?> collection, String str, OutputStream outputStream, String str2) throws IOException {
        writeLines(collection, str, outputStream, Charsets.toCharset(str2));
    }

    public static void writeLines(Collection<?> collection, String str, Writer writer) throws IOException {
        if (collection != null) {
            if (str == null) {
                str = System.lineSeparator();
            }
            for (Object next : collection) {
                if (next != null) {
                    writer.write(next.toString());
                }
                writer.write(str);
            }
        }
    }

    public static Writer writer(Appendable appendable) {
        Objects.requireNonNull(appendable, "appendable");
        if (appendable instanceof Writer) {
            return (Writer) appendable;
        }
        if (appendable instanceof StringBuilder) {
            return new StringBuilderWriter((StringBuilder) appendable);
        }
        return new AppendableWriter(appendable);
    }
}
