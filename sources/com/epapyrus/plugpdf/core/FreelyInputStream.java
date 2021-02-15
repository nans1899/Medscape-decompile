package com.epapyrus.plugpdf.core;

import android.util.Log;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PushbackInputStream;
import java.nio.channels.FileChannel;

public class FreelyInputStream extends PushbackInputStream {
    private byte[] mBuffer;
    private int mCurrentPos;
    private int mLastSize;
    private int mLen;
    private int mResetPos = -1;
    private long mark = -1;
    private FileChannel myFileChannel;

    public boolean markSupported() {
        return true;
    }

    public FreelyInputStream(FileInputStream fileInputStream, int i) {
        super(fileInputStream, i);
        try {
            this.mLen = i;
            this.mCurrentPos = i - this.in.available();
            this.myFileChannel = fileInputStream.getChannel();
        } catch (IOException e) {
            Log.e("PlugPDF", "", e);
        }
    }

    public int getPosition() throws IOException {
        return this.mCurrentPos;
    }

    public int size() {
        return this.mLen - this.mCurrentPos;
    }

    public void unread(int i) throws IOException {
        this.mCurrentPos -= i;
        super.unread(i);
    }

    public long skip(long j) throws IOException {
        this.mCurrentPos = (int) (((long) this.mCurrentPos) + j);
        return super.skip(j);
    }

    public synchronized void mark(int i) {
        try {
            this.mark = this.myFileChannel.position();
        } catch (IOException unused) {
            this.mark = -1;
        }
        return;
    }

    public synchronized void reset() throws IOException {
        if (this.mark != -1) {
            this.myFileChannel.position(this.mark);
            this.mResetPos = this.mCurrentPos;
            this.mCurrentPos = 0;
        } else {
            throw new IOException("not marked");
        }
    }

    public byte[] read(int i, int i2) throws IOException {
        int i3 = this.mResetPos;
        int i4 = this.mCurrentPos;
        if (i3 == i4) {
            return this.mBuffer;
        }
        reset();
        if (i4 > 0) {
            skip((long) i4);
        }
        int min = Math.min(available(), i2);
        byte[] bArr = new byte[min];
        this.mBuffer = bArr;
        if (min == 0) {
            return bArr;
        }
        read(bArr);
        this.mCurrentPos += min;
        this.mLastSize = min;
        if (i > 0) {
            skip((long) i);
        } else if (i < 0) {
            unread(-i);
        }
        return this.mBuffer;
    }

    public boolean seekPosition(int i) throws IOException {
        int i2 = this.mLastSize;
        if (i2 > 0) {
            this.mCurrentPos -= i2;
            this.mLastSize = 0;
        }
        return seekOffset(i - this.mCurrentPos);
    }

    public boolean seekOffset(int i) throws IOException {
        int i2 = this.mLastSize;
        if (i2 > 0) {
            this.mCurrentPos -= i2;
            this.mLastSize = 0;
        }
        if (i < -1) {
            i += this.mCurrentPos;
            reset();
        }
        if (i == 0) {
            return true;
        }
        if (i > 0) {
            skip((long) i);
        } else {
            unread(-i);
        }
        return true;
    }

    public boolean seekEnd() throws IOException {
        return seekPosition(this.mLen);
    }
}
