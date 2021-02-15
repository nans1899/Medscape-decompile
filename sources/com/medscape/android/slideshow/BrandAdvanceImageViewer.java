package com.medscape.android.slideshow;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import com.medscape.android.R;
import com.medscape.android.base.BaseActivity;
import com.medscape.android.util.Util;
import com.medscape.android.view.ZoomableImageView;

public class BrandAdvanceImageViewer extends BaseActivity implements View.OnTouchListener {
    private Button mCloseButton;
    private ImageView mImageView;
    private ProgressBar mProgressBar;
    private ZoomableImageView mZoomImageView;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        Bundle extras;
        String string;
        super.onCreate(bundle);
        setContentView((int) R.layout.image_viewer);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        String string2 = getIntent().getExtras().getString("orientationLock");
        if (string2 != null) {
            if (string2.equalsIgnoreCase("portrait")) {
                setRequestedOrientation(1);
            } else if (string2.equalsIgnoreCase("landscape")) {
                setRequestedOrientation(0);
            }
        }
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
        this.mProgressBar = progressBar;
        progressBar.getIndeterminateDrawable().setColorFilter(-15173719, PorterDuff.Mode.MULTIPLY);
        this.mImageView = (ImageView) findViewById(R.id.image);
        this.mZoomImageView = (ZoomableImageView) findViewById(R.id.zoom_image);
        Button button = (Button) findViewById(R.id.close_button);
        this.mCloseButton = button;
        button.setOnTouchListener(this);
        if (!Util.isOnline(this)) {
            showNetworkError();
            return;
        }
        Intent intent = getIntent();
        if (intent != null && (extras = intent.getExtras()) != null && (string = extras.getString("com.medscape.android.EXTRA_WEBVIEW_URL")) != null) {
            new DownloadImageTask().execute(new String[]{string});
        }
    }

    private void showNetworkError() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(getString(R.string.msg_network_error));
        builder.setCancelable(true);
        builder.setIcon(17301543);
        builder.setPositiveButton(getString(R.string.alert_dialog_confirmation_ok_button_text), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                BrandAdvanceImageViewer.this.finish();
            }
        });
        builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
            public void onCancel(DialogInterface dialogInterface) {
                BrandAdvanceImageViewer.this.finish();
            }
        });
        builder.create().show();
    }

    /* access modifiers changed from: private */
    public void showErrorDownloadingImage() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(getString(R.string.msg_image_download_failed));
        builder.setCancelable(true);
        builder.setIcon(17301543);
        builder.setPositiveButton(getString(R.string.alert_dialog_confirmation_ok_button_text), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                BrandAdvanceImageViewer.this.finish();
            }
        });
        builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
            public void onCancel(DialogInterface dialogInterface) {
                BrandAdvanceImageViewer.this.finish();
            }
        });
        builder.create().show();
    }

    /* access modifiers changed from: private */
    public void setImage(Bitmap bitmap) {
        this.mProgressBar.setVisibility(8);
        if (bitmap != null) {
            this.mImageView.setImageBitmap(bitmap);
            this.mZoomImageView.setImageBitmap(bitmap);
        }
    }

    public void onCloseClick(View view) {
        finish();
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        private DownloadImageTask() {
        }

        /* access modifiers changed from: protected */
        /* JADX WARNING: Can't wrap try/catch for region: R(2:7|8) */
        /* JADX WARNING: Code restructure failed: missing block: B:8:?, code lost:
            android.util.Log.d("error", "error");
         */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0022 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public android.graphics.Bitmap doInBackground(java.lang.String... r7) {
            /*
                r6 = this;
                java.lang.String r0 = "error"
                r1 = 0
                r7 = r7[r1]
                java.net.URL r2 = new java.net.URL     // Catch:{ OutOfMemoryError -> 0x004e, Exception -> 0x0040 }
                r2.<init>(r7)     // Catch:{ OutOfMemoryError -> 0x004e, Exception -> 0x0040 }
                java.io.InputStream r7 = r2.openStream()     // Catch:{ OutOfMemoryError -> 0x004e, Exception -> 0x0040 }
                java.io.ByteArrayOutputStream r2 = new java.io.ByteArrayOutputStream     // Catch:{ OutOfMemoryError -> 0x004e, Exception -> 0x0040 }
                r2.<init>()     // Catch:{ OutOfMemoryError -> 0x004e, Exception -> 0x0040 }
                r3 = 1024(0x400, float:1.435E-42)
                byte[] r3 = new byte[r3]     // Catch:{ OutOfMemoryError -> 0x004e, Exception -> 0x0040 }
            L_0x0017:
                int r4 = r7.read(r3)     // Catch:{ IOException -> 0x0022 }
                r5 = -1
                if (r4 == r5) goto L_0x0025
                r2.write(r3, r1, r4)     // Catch:{ IOException -> 0x0022 }
                goto L_0x0017
            L_0x0022:
                android.util.Log.d(r0, r0)     // Catch:{ OutOfMemoryError -> 0x004e, Exception -> 0x0040 }
            L_0x0025:
                byte[] r7 = r2.toByteArray()     // Catch:{ OutOfMemoryError -> 0x004e, Exception -> 0x0040 }
                com.medscape.android.slideshow.BrandAdvanceImageViewer r0 = com.medscape.android.slideshow.BrandAdvanceImageViewer.this     // Catch:{ OutOfMemoryError -> 0x004e, Exception -> 0x0040 }
                android.view.WindowManager r0 = r0.getWindowManager()     // Catch:{ OutOfMemoryError -> 0x004e, Exception -> 0x0040 }
                android.view.Display r0 = r0.getDefaultDisplay()     // Catch:{ OutOfMemoryError -> 0x004e, Exception -> 0x0040 }
                int r1 = r0.getWidth()     // Catch:{ OutOfMemoryError -> 0x004e, Exception -> 0x0040 }
                int r0 = r0.getHeight()     // Catch:{ OutOfMemoryError -> 0x004e, Exception -> 0x0040 }
                android.graphics.Bitmap r7 = com.medscape.android.slideshow.BrandAdvanceImageViewer.decodeSampledBitmapFromResource(r7, r1, r0)     // Catch:{ OutOfMemoryError -> 0x004e, Exception -> 0x0040 }
                goto L_0x0053
            L_0x0040:
                r7 = move-exception
                java.lang.String r0 = r7.getMessage()
                java.lang.String r1 = "Error"
                android.util.Log.e(r1, r0)
                r7.printStackTrace()
                goto L_0x0052
            L_0x004e:
                r7 = move-exception
                r7.printStackTrace()
            L_0x0052:
                r7 = 0
            L_0x0053:
                return r7
            */
            throw new UnsupportedOperationException("Method not decompiled: com.medscape.android.slideshow.BrandAdvanceImageViewer.DownloadImageTask.doInBackground(java.lang.String[]):android.graphics.Bitmap");
        }

        /* access modifiers changed from: protected */
        public void onPostExecute(Bitmap bitmap) {
            if (bitmap == null) {
                BrandAdvanceImageViewer.this.showErrorDownloadingImage();
            } else {
                BrandAdvanceImageViewer.this.setImage(bitmap);
            }
        }
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            this.mCloseButton.setBackgroundDrawable(getResources().getDrawable(R.drawable.imageviewer_button_on));
            this.mCloseButton.setTextColor(Color.parseColor("#000000"));
            return false;
        }
        this.mCloseButton.setBackgroundDrawable(getResources().getDrawable(R.drawable.imageviewer_button_off));
        this.mCloseButton.setTextColor(Color.parseColor("#FFFFFF"));
        return false;
    }

    public static int calculateInSampleSize(BitmapFactory.Options options, int i, int i2) {
        int i3 = options.outHeight;
        int i4 = options.outWidth;
        int i5 = 1;
        if (i3 > i2 || i4 > i) {
            int i6 = i3 / 2;
            int i7 = i4 / 2;
            while (i6 / i5 > i2 && i7 / i5 > i) {
                i5 *= 2;
            }
        }
        return i5;
    }

    public static Bitmap decodeSampledBitmapFromResource(byte[] bArr, int i, int i2) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeByteArray(bArr, 0, bArr.length, options);
        options.inSampleSize = calculateInSampleSize(options, i, i2);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeByteArray(bArr, 0, bArr.length, options);
    }
}
