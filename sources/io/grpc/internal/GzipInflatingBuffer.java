package io.grpc.internal;

import com.google.common.base.Preconditions;
import java.io.Closeable;
import java.util.zip.CRC32;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;
import java.util.zip.ZipException;

class GzipInflatingBuffer implements Closeable {
    private static final int GZIP_HEADER_MIN_SIZE = 10;
    private static final int GZIP_MAGIC = 35615;
    private static final int GZIP_TRAILER_SIZE = 8;
    private static final int HEADER_COMMENT_FLAG = 16;
    private static final int HEADER_CRC_FLAG = 2;
    private static final int HEADER_EXTRA_FLAG = 4;
    private static final int HEADER_NAME_FLAG = 8;
    private static final int INFLATE_BUFFER_SIZE = 512;
    private static final int UNSIGNED_SHORT_SIZE = 2;
    private int bytesConsumed = 0;
    private boolean closed = false;
    /* access modifiers changed from: private */
    public final CRC32 crc = new CRC32();
    private int deflatedBytesConsumed = 0;
    private long expectedGzipTrailerIsize;
    private int gzipHeaderFlag;
    private final GzipMetadataReader gzipMetadataReader = new GzipMetadataReader(this, (AnonymousClass1) null);
    /* access modifiers changed from: private */
    public final CompositeReadableBuffer gzippedData = new CompositeReadableBuffer();
    private int headerExtraToRead;
    private Inflater inflater;
    /* access modifiers changed from: private */
    public final byte[] inflaterInput = new byte[512];
    /* access modifiers changed from: private */
    public int inflaterInputEnd;
    /* access modifiers changed from: private */
    public int inflaterInputStart;
    private boolean isStalled = true;
    private State state = State.HEADER;

    private enum State {
        HEADER,
        HEADER_EXTRA_LEN,
        HEADER_EXTRA,
        HEADER_NAME,
        HEADER_COMMENT,
        HEADER_CRC,
        INITIALIZE_INFLATER,
        INFLATING,
        INFLATER_NEEDS_INPUT,
        TRAILER
    }

    GzipInflatingBuffer() {
    }

    static /* synthetic */ int access$112(GzipInflatingBuffer gzipInflatingBuffer, int i) {
        int i2 = gzipInflatingBuffer.inflaterInputStart + i;
        gzipInflatingBuffer.inflaterInputStart = i2;
        return i2;
    }

    static /* synthetic */ int access$512(GzipInflatingBuffer gzipInflatingBuffer, int i) {
        int i2 = gzipInflatingBuffer.bytesConsumed + i;
        gzipInflatingBuffer.bytesConsumed = i2;
        return i2;
    }

    private class GzipMetadataReader {
        private GzipMetadataReader() {
        }

        /* synthetic */ GzipMetadataReader(GzipInflatingBuffer gzipInflatingBuffer, AnonymousClass1 r2) {
            this();
        }

        /* access modifiers changed from: private */
        public int readUnsignedByte() {
            int i;
            if (GzipInflatingBuffer.this.inflaterInputEnd - GzipInflatingBuffer.this.inflaterInputStart > 0) {
                i = GzipInflatingBuffer.this.inflaterInput[GzipInflatingBuffer.this.inflaterInputStart] & 255;
                GzipInflatingBuffer.access$112(GzipInflatingBuffer.this, 1);
            } else {
                i = GzipInflatingBuffer.this.gzippedData.readUnsignedByte();
            }
            GzipInflatingBuffer.this.crc.update(i);
            GzipInflatingBuffer.access$512(GzipInflatingBuffer.this, 1);
            return i;
        }

        /* access modifiers changed from: private */
        public void skipBytes(int i) {
            int i2;
            int access$000 = GzipInflatingBuffer.this.inflaterInputEnd - GzipInflatingBuffer.this.inflaterInputStart;
            if (access$000 > 0) {
                int min = Math.min(access$000, i);
                GzipInflatingBuffer.this.crc.update(GzipInflatingBuffer.this.inflaterInput, GzipInflatingBuffer.this.inflaterInputStart, min);
                GzipInflatingBuffer.access$112(GzipInflatingBuffer.this, min);
                i2 = i - min;
            } else {
                i2 = i;
            }
            if (i2 > 0) {
                byte[] bArr = new byte[512];
                int i3 = 0;
                while (i3 < i2) {
                    int min2 = Math.min(i2 - i3, 512);
                    GzipInflatingBuffer.this.gzippedData.readBytes(bArr, 0, min2);
                    GzipInflatingBuffer.this.crc.update(bArr, 0, min2);
                    i3 += min2;
                }
            }
            GzipInflatingBuffer.access$512(GzipInflatingBuffer.this, i);
        }

        /* access modifiers changed from: private */
        public int readableBytes() {
            return (GzipInflatingBuffer.this.inflaterInputEnd - GzipInflatingBuffer.this.inflaterInputStart) + GzipInflatingBuffer.this.gzippedData.readableBytes();
        }

        /* access modifiers changed from: private */
        public boolean readBytesUntilZero() {
            while (readableBytes() > 0) {
                if (readUnsignedByte() == 0) {
                    return true;
                }
            }
            return false;
        }

        /* access modifiers changed from: private */
        public int readUnsignedShort() {
            return readUnsignedByte() | (readUnsignedByte() << 8);
        }

        /* access modifiers changed from: private */
        public long readUnsignedInt() {
            return ((long) readUnsignedShort()) | (((long) readUnsignedShort()) << 16);
        }
    }

    /* access modifiers changed from: package-private */
    public boolean isStalled() {
        Preconditions.checkState(!this.closed, "GzipInflatingBuffer is closed");
        return this.isStalled;
    }

    /* access modifiers changed from: package-private */
    public boolean hasPartialData() {
        Preconditions.checkState(!this.closed, "GzipInflatingBuffer is closed");
        if (this.gzipMetadataReader.readableBytes() == 0 && this.state == State.HEADER) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public void addGzippedBytes(ReadableBuffer readableBuffer) {
        Preconditions.checkState(!this.closed, "GzipInflatingBuffer is closed");
        this.gzippedData.addBuffer(readableBuffer);
        this.isStalled = false;
    }

    public void close() {
        if (!this.closed) {
            this.closed = true;
            this.gzippedData.close();
            Inflater inflater2 = this.inflater;
            if (inflater2 != null) {
                inflater2.end();
                this.inflater = null;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public int getAndResetBytesConsumed() {
        int i = this.bytesConsumed;
        this.bytesConsumed = 0;
        return i;
    }

    /* access modifiers changed from: package-private */
    public int getAndResetDeflatedBytesConsumed() {
        int i = this.deflatedBytesConsumed;
        this.deflatedBytesConsumed = 0;
        return i;
    }

    /* access modifiers changed from: package-private */
    public int inflateBytes(byte[] bArr, int i, int i2) throws DataFormatException, ZipException {
        boolean z = true;
        Preconditions.checkState(!this.closed, "GzipInflatingBuffer is closed");
        boolean z2 = true;
        int i3 = 0;
        while (z2) {
            int i4 = i2 - i3;
            if (i4 > 0) {
                switch (AnonymousClass1.$SwitchMap$io$grpc$internal$GzipInflatingBuffer$State[this.state.ordinal()]) {
                    case 1:
                        z2 = processHeader();
                        break;
                    case 2:
                        z2 = processHeaderExtraLen();
                        break;
                    case 3:
                        z2 = processHeaderExtra();
                        break;
                    case 4:
                        z2 = processHeaderName();
                        break;
                    case 5:
                        z2 = processHeaderComment();
                        break;
                    case 6:
                        z2 = processHeaderCrc();
                        break;
                    case 7:
                        z2 = initializeInflater();
                        break;
                    case 8:
                        i3 += inflate(bArr, i + i3, i4);
                        if (this.state != State.TRAILER) {
                            z2 = true;
                            break;
                        } else {
                            z2 = processTrailer();
                            break;
                        }
                    case 9:
                        z2 = fill();
                        break;
                    case 10:
                        z2 = processTrailer();
                        break;
                    default:
                        throw new AssertionError("Invalid state: " + this.state);
                }
            } else {
                if (z2 && (this.state != State.HEADER || this.gzipMetadataReader.readableBytes() >= 10)) {
                    z = false;
                }
                this.isStalled = z;
                return i3;
            }
        }
        z = false;
        this.isStalled = z;
        return i3;
    }

    /* renamed from: io.grpc.internal.GzipInflatingBuffer$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$io$grpc$internal$GzipInflatingBuffer$State;

        /* JADX WARNING: Can't wrap try/catch for region: R(20:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|(3:19|20|22)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(22:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|19|20|22) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0049 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0054 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0060 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x006c */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                io.grpc.internal.GzipInflatingBuffer$State[] r0 = io.grpc.internal.GzipInflatingBuffer.State.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$io$grpc$internal$GzipInflatingBuffer$State = r0
                io.grpc.internal.GzipInflatingBuffer$State r1 = io.grpc.internal.GzipInflatingBuffer.State.HEADER     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$io$grpc$internal$GzipInflatingBuffer$State     // Catch:{ NoSuchFieldError -> 0x001d }
                io.grpc.internal.GzipInflatingBuffer$State r1 = io.grpc.internal.GzipInflatingBuffer.State.HEADER_EXTRA_LEN     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$io$grpc$internal$GzipInflatingBuffer$State     // Catch:{ NoSuchFieldError -> 0x0028 }
                io.grpc.internal.GzipInflatingBuffer$State r1 = io.grpc.internal.GzipInflatingBuffer.State.HEADER_EXTRA     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$io$grpc$internal$GzipInflatingBuffer$State     // Catch:{ NoSuchFieldError -> 0x0033 }
                io.grpc.internal.GzipInflatingBuffer$State r1 = io.grpc.internal.GzipInflatingBuffer.State.HEADER_NAME     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$io$grpc$internal$GzipInflatingBuffer$State     // Catch:{ NoSuchFieldError -> 0x003e }
                io.grpc.internal.GzipInflatingBuffer$State r1 = io.grpc.internal.GzipInflatingBuffer.State.HEADER_COMMENT     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$io$grpc$internal$GzipInflatingBuffer$State     // Catch:{ NoSuchFieldError -> 0x0049 }
                io.grpc.internal.GzipInflatingBuffer$State r1 = io.grpc.internal.GzipInflatingBuffer.State.HEADER_CRC     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                int[] r0 = $SwitchMap$io$grpc$internal$GzipInflatingBuffer$State     // Catch:{ NoSuchFieldError -> 0x0054 }
                io.grpc.internal.GzipInflatingBuffer$State r1 = io.grpc.internal.GzipInflatingBuffer.State.INITIALIZE_INFLATER     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                int[] r0 = $SwitchMap$io$grpc$internal$GzipInflatingBuffer$State     // Catch:{ NoSuchFieldError -> 0x0060 }
                io.grpc.internal.GzipInflatingBuffer$State r1 = io.grpc.internal.GzipInflatingBuffer.State.INFLATING     // Catch:{ NoSuchFieldError -> 0x0060 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0060 }
                r2 = 8
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0060 }
            L_0x0060:
                int[] r0 = $SwitchMap$io$grpc$internal$GzipInflatingBuffer$State     // Catch:{ NoSuchFieldError -> 0x006c }
                io.grpc.internal.GzipInflatingBuffer$State r1 = io.grpc.internal.GzipInflatingBuffer.State.INFLATER_NEEDS_INPUT     // Catch:{ NoSuchFieldError -> 0x006c }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x006c }
                r2 = 9
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x006c }
            L_0x006c:
                int[] r0 = $SwitchMap$io$grpc$internal$GzipInflatingBuffer$State     // Catch:{ NoSuchFieldError -> 0x0078 }
                io.grpc.internal.GzipInflatingBuffer$State r1 = io.grpc.internal.GzipInflatingBuffer.State.TRAILER     // Catch:{ NoSuchFieldError -> 0x0078 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0078 }
                r2 = 10
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0078 }
            L_0x0078:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: io.grpc.internal.GzipInflatingBuffer.AnonymousClass1.<clinit>():void");
        }
    }

    private boolean processHeader() throws ZipException {
        if (this.gzipMetadataReader.readableBytes() < 10) {
            return false;
        }
        if (this.gzipMetadataReader.readUnsignedShort() != GZIP_MAGIC) {
            throw new ZipException("Not in GZIP format");
        } else if (this.gzipMetadataReader.readUnsignedByte() == 8) {
            this.gzipHeaderFlag = this.gzipMetadataReader.readUnsignedByte();
            this.gzipMetadataReader.skipBytes(6);
            this.state = State.HEADER_EXTRA_LEN;
            return true;
        } else {
            throw new ZipException("Unsupported compression method");
        }
    }

    private boolean processHeaderExtraLen() {
        if ((this.gzipHeaderFlag & 4) != 4) {
            this.state = State.HEADER_NAME;
            return true;
        } else if (this.gzipMetadataReader.readableBytes() < 2) {
            return false;
        } else {
            this.headerExtraToRead = this.gzipMetadataReader.readUnsignedShort();
            this.state = State.HEADER_EXTRA;
            return true;
        }
    }

    private boolean processHeaderExtra() {
        int access$700 = this.gzipMetadataReader.readableBytes();
        int i = this.headerExtraToRead;
        if (access$700 < i) {
            return false;
        }
        this.gzipMetadataReader.skipBytes(i);
        this.state = State.HEADER_NAME;
        return true;
    }

    private boolean processHeaderName() {
        if ((this.gzipHeaderFlag & 8) != 8) {
            this.state = State.HEADER_COMMENT;
            return true;
        } else if (!this.gzipMetadataReader.readBytesUntilZero()) {
            return false;
        } else {
            this.state = State.HEADER_COMMENT;
            return true;
        }
    }

    private boolean processHeaderComment() {
        if ((this.gzipHeaderFlag & 16) != 16) {
            this.state = State.HEADER_CRC;
            return true;
        } else if (!this.gzipMetadataReader.readBytesUntilZero()) {
            return false;
        } else {
            this.state = State.HEADER_CRC;
            return true;
        }
    }

    private boolean processHeaderCrc() throws ZipException {
        if ((this.gzipHeaderFlag & 2) != 2) {
            this.state = State.INITIALIZE_INFLATER;
            return true;
        } else if (this.gzipMetadataReader.readableBytes() < 2) {
            return false;
        } else {
            if ((65535 & ((int) this.crc.getValue())) == this.gzipMetadataReader.readUnsignedShort()) {
                this.state = State.INITIALIZE_INFLATER;
                return true;
            }
            throw new ZipException("Corrupt GZIP header");
        }
    }

    private boolean initializeInflater() {
        Inflater inflater2 = this.inflater;
        if (inflater2 == null) {
            this.inflater = new Inflater(true);
        } else {
            inflater2.reset();
        }
        this.crc.reset();
        int i = this.inflaterInputEnd;
        int i2 = this.inflaterInputStart;
        int i3 = i - i2;
        if (i3 > 0) {
            this.inflater.setInput(this.inflaterInput, i2, i3);
            this.state = State.INFLATING;
        } else {
            this.state = State.INFLATER_NEEDS_INPUT;
        }
        return true;
    }

    private int inflate(byte[] bArr, int i, int i2) throws DataFormatException, ZipException {
        Preconditions.checkState(this.inflater != null, "inflater is null");
        try {
            int totalIn = this.inflater.getTotalIn();
            int inflate = this.inflater.inflate(bArr, i, i2);
            int totalIn2 = this.inflater.getTotalIn() - totalIn;
            this.bytesConsumed += totalIn2;
            this.deflatedBytesConsumed += totalIn2;
            this.inflaterInputStart += totalIn2;
            this.crc.update(bArr, i, inflate);
            if (this.inflater.finished()) {
                this.expectedGzipTrailerIsize = this.inflater.getBytesWritten() & 4294967295L;
                this.state = State.TRAILER;
            } else if (this.inflater.needsInput()) {
                this.state = State.INFLATER_NEEDS_INPUT;
            }
            return inflate;
        } catch (DataFormatException e) {
            throw new DataFormatException("Inflater data format exception: " + e.getMessage());
        }
    }

    private boolean fill() {
        Preconditions.checkState(this.inflater != null, "inflater is null");
        Preconditions.checkState(this.inflaterInputStart == this.inflaterInputEnd, "inflaterInput has unconsumed bytes");
        int min = Math.min(this.gzippedData.readableBytes(), 512);
        if (min == 0) {
            return false;
        }
        this.inflaterInputStart = 0;
        this.inflaterInputEnd = min;
        this.gzippedData.readBytes(this.inflaterInput, 0, min);
        this.inflater.setInput(this.inflaterInput, this.inflaterInputStart, min);
        this.state = State.INFLATING;
        return true;
    }

    private boolean processTrailer() throws ZipException {
        if (this.inflater != null && this.gzipMetadataReader.readableBytes() <= 18) {
            this.inflater.end();
            this.inflater = null;
        }
        if (this.gzipMetadataReader.readableBytes() < 8) {
            return false;
        }
        if (this.crc.getValue() == this.gzipMetadataReader.readUnsignedInt() && this.expectedGzipTrailerIsize == this.gzipMetadataReader.readUnsignedInt()) {
            this.crc.reset();
            this.state = State.HEADER;
            return true;
        }
        throw new ZipException("Corrupt GZIP trailer");
    }
}
