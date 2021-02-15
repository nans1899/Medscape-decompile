package com.fasterxml.jackson.core.io;

import com.fasterxml.jackson.core.base.GeneratorBase;
import com.google.common.base.Ascii;
import java.io.CharConversionException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

public class UTF32Reader extends Reader {
    protected static final int LAST_VALID_UNICODE_CHAR = 1114111;
    protected static final char NC = 0;
    protected final boolean _bigEndian;
    protected byte[] _buffer;
    protected int _byteCount;
    protected int _charCount;
    protected final IOContext _context;
    protected InputStream _in;
    protected int _length;
    protected final boolean _managedBuffers;
    protected int _ptr;
    protected char _surrogate = 0;
    protected char[] _tmpBuf;

    public UTF32Reader(IOContext iOContext, InputStream inputStream, byte[] bArr, int i, int i2, boolean z) {
        boolean z2 = false;
        this._context = iOContext;
        this._in = inputStream;
        this._buffer = bArr;
        this._ptr = i;
        this._length = i2;
        this._bigEndian = z;
        this._managedBuffers = inputStream != null ? true : z2;
    }

    public void close() throws IOException {
        InputStream inputStream = this._in;
        if (inputStream != null) {
            this._in = null;
            freeBuffers();
            inputStream.close();
        }
    }

    public int read() throws IOException {
        if (this._tmpBuf == null) {
            this._tmpBuf = new char[1];
        }
        if (read(this._tmpBuf, 0, 1) < 1) {
            return -1;
        }
        return this._tmpBuf[0];
    }

    public int read(char[] cArr, int i, int i2) throws IOException {
        int i3;
        byte b;
        int i4;
        int i5;
        if (this._buffer == null) {
            return -1;
        }
        if (i2 < 1) {
            return i2;
        }
        if (i < 0 || i + i2 > cArr.length) {
            reportBounds(cArr, i, i2);
        }
        int i6 = i2 + i;
        char c = this._surrogate;
        if (c != 0) {
            i3 = i + 1;
            cArr[i] = c;
            this._surrogate = 0;
        } else {
            int i7 = this._length - this._ptr;
            if (i7 < 4 && !loadMore(i7)) {
                return -1;
            }
            i3 = i;
        }
        while (true) {
            if (i3 >= i6) {
                break;
            }
            int i8 = this._ptr;
            if (this._bigEndian) {
                byte[] bArr = this._buffer;
                b = (bArr[i8] << Ascii.CAN) | ((bArr[i8 + 1] & 255) << Ascii.DLE) | ((bArr[i8 + 2] & 255) << 8);
                i4 = bArr[i8 + 3] & 255;
            } else {
                byte[] bArr2 = this._buffer;
                b = (bArr2[i8] & 255) | ((bArr2[i8 + 1] & 255) << 8) | ((bArr2[i8 + 2] & 255) << Ascii.DLE);
                i4 = bArr2[i8 + 3] << Ascii.CAN;
            }
            byte b2 = i4 | b;
            this._ptr += 4;
            if (b2 > 65535) {
                if (b2 > LAST_VALID_UNICODE_CHAR) {
                    reportInvalid(b2, i3 - i, "(above " + Integer.toHexString(LAST_VALID_UNICODE_CHAR) + ") ");
                }
                int i9 = b2 - 65536;
                i5 = i3 + 1;
                cArr[i3] = (char) ((i9 >> 10) + GeneratorBase.SURR1_FIRST);
                b2 = (i9 & 1023) | 56320;
                if (i5 >= i6) {
                    this._surrogate = (char) b2;
                    break;
                }
                i3 = i5;
            }
            i5 = i3 + 1;
            cArr[i3] = (char) b2;
            if (this._ptr >= this._length) {
                break;
            }
            i3 = i5;
        }
        i3 = i5;
        int i10 = i3 - i;
        this._charCount += i10;
        return i10;
    }

    private void reportUnexpectedEOF(int i, int i2) throws IOException {
        int i3 = this._charCount;
        throw new CharConversionException("Unexpected EOF in the middle of a 4-byte UTF-32 char: got " + i + ", needed " + i2 + ", at char #" + i3 + ", byte #" + (this._byteCount + i) + ")");
    }

    private void reportInvalid(int i, int i2, String str) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("Invalid UTF-32 character 0x");
        sb.append(Integer.toHexString(i));
        sb.append(str);
        sb.append(" at char #");
        sb.append(this._charCount + i2);
        sb.append(", byte #");
        sb.append((this._byteCount + this._ptr) - 1);
        sb.append(")");
        throw new CharConversionException(sb.toString());
    }

    private boolean loadMore(int i) throws IOException {
        int i2;
        this._byteCount += this._length - i;
        if (i > 0) {
            int i3 = this._ptr;
            if (i3 > 0) {
                byte[] bArr = this._buffer;
                System.arraycopy(bArr, i3, bArr, 0, i);
                this._ptr = 0;
            }
            this._length = i;
        } else {
            this._ptr = 0;
            InputStream inputStream = this._in;
            int read = inputStream == null ? -1 : inputStream.read(this._buffer);
            if (read < 1) {
                this._length = 0;
                if (read < 0) {
                    if (this._managedBuffers) {
                        freeBuffers();
                    }
                    return false;
                }
                reportStrangeStream();
            }
            this._length = read;
        }
        while (true) {
            int i4 = this._length;
            if (i4 >= 4) {
                return true;
            }
            InputStream inputStream2 = this._in;
            if (inputStream2 == null) {
                i2 = -1;
            } else {
                byte[] bArr2 = this._buffer;
                i2 = inputStream2.read(bArr2, i4, bArr2.length - i4);
            }
            if (i2 < 1) {
                if (i2 < 0) {
                    if (this._managedBuffers) {
                        freeBuffers();
                    }
                    reportUnexpectedEOF(this._length, 4);
                }
                reportStrangeStream();
            }
            this._length += i2;
        }
    }

    private void freeBuffers() {
        byte[] bArr = this._buffer;
        if (bArr != null) {
            this._buffer = null;
            this._context.releaseReadIOBuffer(bArr);
        }
    }

    private void reportBounds(char[] cArr, int i, int i2) throws IOException {
        throw new ArrayIndexOutOfBoundsException("read(buf," + i + "," + i2 + "), cbuf[" + cArr.length + "]");
    }

    private void reportStrangeStream() throws IOException {
        throw new IOException("Strange I/O stream, returned 0 bytes on read");
    }
}
