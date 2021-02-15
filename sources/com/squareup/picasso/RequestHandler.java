package com.squareup.picasso;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.NetworkInfo;
import com.squareup.picasso.Picasso;
import java.io.IOException;
import okio.Source;

public abstract class RequestHandler {
    public abstract boolean canHandleRequest(Request request);

    /* access modifiers changed from: package-private */
    public int getRetryCount() {
        return 0;
    }

    public abstract Result load(Request request, int i) throws IOException;

    /* access modifiers changed from: package-private */
    public boolean shouldRetry(boolean z, NetworkInfo networkInfo) {
        return false;
    }

    /* access modifiers changed from: package-private */
    public boolean supportsReplay() {
        return false;
    }

    public static final class Result {
        private final Bitmap bitmap;
        private final int exifOrientation;
        private final Picasso.LoadedFrom loadedFrom;
        private final Source source;

        public Result(Bitmap bitmap2, Picasso.LoadedFrom loadedFrom2) {
            this((Bitmap) Utils.checkNotNull(bitmap2, "bitmap == null"), (Source) null, loadedFrom2, 0);
        }

        public Result(Source source2, Picasso.LoadedFrom loadedFrom2) {
            this((Bitmap) null, (Source) Utils.checkNotNull(source2, "source == null"), loadedFrom2, 0);
        }

        Result(Bitmap bitmap2, Source source2, Picasso.LoadedFrom loadedFrom2, int i) {
            if ((bitmap2 != null) != (source2 == null ? false : true)) {
                this.bitmap = bitmap2;
                this.source = source2;
                this.loadedFrom = (Picasso.LoadedFrom) Utils.checkNotNull(loadedFrom2, "loadedFrom == null");
                this.exifOrientation = i;
                return;
            }
            throw new AssertionError();
        }

        public Bitmap getBitmap() {
            return this.bitmap;
        }

        public Source getSource() {
            return this.source;
        }

        public Picasso.LoadedFrom getLoadedFrom() {
            return this.loadedFrom;
        }

        /* access modifiers changed from: package-private */
        public int getExifOrientation() {
            return this.exifOrientation;
        }
    }

    static BitmapFactory.Options createBitmapOptions(Request request) {
        boolean hasSize = request.hasSize();
        boolean z = request.config != null;
        BitmapFactory.Options options = null;
        if (hasSize || z || request.purgeable) {
            options = new BitmapFactory.Options();
            options.inJustDecodeBounds = hasSize;
            options.inInputShareable = request.purgeable;
            options.inPurgeable = request.purgeable;
            if (z) {
                options.inPreferredConfig = request.config;
            }
        }
        return options;
    }

    static boolean requiresInSampleSize(BitmapFactory.Options options) {
        return options != null && options.inJustDecodeBounds;
    }

    static void calculateInSampleSize(int i, int i2, BitmapFactory.Options options, Request request) {
        calculateInSampleSize(i, i2, options.outWidth, options.outHeight, options, request);
    }

    static void calculateInSampleSize(int i, int i2, int i3, int i4, BitmapFactory.Options options, Request request) {
        int i5;
        double floor;
        if (i4 > i2 || i3 > i) {
            if (i2 == 0) {
                floor = Math.floor((double) (((float) i3) / ((float) i)));
            } else if (i == 0) {
                floor = Math.floor((double) (((float) i4) / ((float) i2)));
            } else {
                int floor2 = (int) Math.floor((double) (((float) i4) / ((float) i2)));
                int floor3 = (int) Math.floor((double) (((float) i3) / ((float) i)));
                i5 = request.centerInside ? Math.max(floor2, floor3) : Math.min(floor2, floor3);
            }
            i5 = (int) floor;
        } else {
            i5 = 1;
        }
        options.inSampleSize = i5;
        options.inJustDecodeBounds = false;
    }
}
