package com.bumptech.glide.load.data;

import android.os.Build;
import android.os.ParcelFileDescriptor;
import android.system.ErrnoException;
import android.system.Os;
import android.system.OsConstants;
import com.bumptech.glide.load.data.DataRewinder;
import java.io.IOException;

public final class ParcelFileDescriptorRewinder implements DataRewinder<ParcelFileDescriptor> {
    private final InternalRewinder rewinder;

    public void cleanup() {
    }

    public static boolean isSupported() {
        return Build.VERSION.SDK_INT >= 21;
    }

    public ParcelFileDescriptorRewinder(ParcelFileDescriptor parcelFileDescriptor) {
        this.rewinder = new InternalRewinder(parcelFileDescriptor);
    }

    public ParcelFileDescriptor rewindAndGet() throws IOException {
        return this.rewinder.rewind();
    }

    public static final class Factory implements DataRewinder.Factory<ParcelFileDescriptor> {
        public DataRewinder<ParcelFileDescriptor> build(ParcelFileDescriptor parcelFileDescriptor) {
            return new ParcelFileDescriptorRewinder(parcelFileDescriptor);
        }

        public Class<ParcelFileDescriptor> getDataClass() {
            return ParcelFileDescriptor.class;
        }
    }

    private static final class InternalRewinder {
        private final ParcelFileDescriptor parcelFileDescriptor;

        InternalRewinder(ParcelFileDescriptor parcelFileDescriptor2) {
            this.parcelFileDescriptor = parcelFileDescriptor2;
        }

        /* access modifiers changed from: package-private */
        public ParcelFileDescriptor rewind() throws IOException {
            try {
                Os.lseek(this.parcelFileDescriptor.getFileDescriptor(), 0, OsConstants.SEEK_SET);
                return this.parcelFileDescriptor;
            } catch (ErrnoException e) {
                throw new IOException(e);
            }
        }
    }
}
