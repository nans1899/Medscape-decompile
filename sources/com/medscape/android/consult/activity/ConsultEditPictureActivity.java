package com.medscape.android.consult.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.SparseArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.ViewCompat;
import com.google.android.gms.vision.Frame;
import com.google.android.gms.vision.face.Face;
import com.google.android.gms.vision.face.FaceDetector;
import com.medscape.android.BI.omniture.OmnitureManager;
import com.medscape.android.Constants;
import com.medscape.android.R;
import com.medscape.android.base.BaseActivity;
import com.medscape.android.consult.interfaces.IFileCreatedListener;
import com.medscape.android.consult.models.GraphicLine;
import com.medscape.android.consult.tasks.CreateFileForBitMapTask;
import com.medscape.android.util.MedscapeException;
import com.medscape.android.util.StringUtil;
import com.medscape.android.util.media.PhotoUtil;
import com.wbmd.wbmdcommons.logging.Trace;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ConsultEditPictureActivity extends BaseActivity {
    /* access modifiers changed from: private */
    public static final String TAG = ConsultEditPictureActivity.class.getSimpleName();
    /* access modifiers changed from: private */
    public float downx = 0.0f;
    /* access modifiers changed from: private */
    public float downy = 0.0f;
    /* access modifiers changed from: private */
    public Bitmap mAlteredBitmap;
    private View mBlackPaintView;
    /* access modifiers changed from: private */
    public Canvas mCanvas;
    /* access modifiers changed from: private */
    public boolean mFaceDetectionEnabled = true;
    private SparseArray<Face> mFaces;
    private View mFivePixelView;
    /* access modifiers changed from: private */
    public ImageView mImageView;
    private boolean mIsEditingPhoto = false;
    /* access modifiers changed from: private */
    public boolean mIsRunningCreateFileForBitmap = false;
    /* access modifiers changed from: private */
    public List<GraphicLine> mLines = new ArrayList();
    /* access modifiers changed from: private */
    public Bitmap mOriginalBitmap;
    private int mOriginalImageViewHeight = -1;
    private int mOriginalImageViewWidth = -1;
    /* access modifiers changed from: private */
    public Paint mPaint;
    /* access modifiers changed from: private */
    public View mProgressBar;
    private View mRedPaintView;
    /* access modifiers changed from: private */
    public View mRootView;
    int mSelectedResetOptionValue = -1;
    /* access modifiers changed from: private */
    public int mStatusBarHeight;
    private View mTenPixelView;
    private View mThreePixelView;
    /* access modifiers changed from: private */
    public float upx = 0.0f;
    /* access modifiers changed from: private */
    public float upy = 0.0f;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_consult_edit_photo);
        this.mRootView = findViewById(R.id.root);
        this.mProgressBar = findViewById(R.id.progressBar);
        this.mImageView = (ImageView) findViewById(R.id.image);
        this.mBlackPaintView = findViewById(R.id.black_paint);
        this.mRedPaintView = findViewById(R.id.red_paint);
        this.mThreePixelView = findViewById(R.id.three_pixels);
        this.mFivePixelView = findViewById(R.id.five_pixes);
        this.mTenPixelView = findViewById(R.id.ten_pixels);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator((int) R.drawable.ic_arrow_back_white_24dp);
        }
        showDisclaimer();
        fetchStatusBarHeight();
        loadImageForEditing();
        addTouchListenerForImage();
        this.mPvid = OmnitureManager.get().trackPageView(this, "consult", "consult", "photoedit", (String) null, (String) null, (Map<String, Object>) null);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.consult_edit_pic_menu, menu);
        MenuItem item = menu.getItem(0);
        SpannableString spannableString = new SpannableString(menu.getItem(0).getTitle().toString());
        spannableString.setSpan(new ForegroundColorSpan(-1), 0, spannableString.length(), 0);
        item.setTitle(spannableString);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == 16908332) {
            recycleBitmapsAndReturn();
            return true;
        } else if (menuItem.getItemId() != R.id.action_post_save) {
            return true;
        } else {
            saveAndReturnImage();
            return true;
        }
    }

    private void recycleBitmapsAndReturn() {
        if (!this.mIsEditingPhoto) {
            Bitmap bitmap = this.mOriginalBitmap;
            if (bitmap != null && !bitmap.isRecycled()) {
                this.mOriginalBitmap.recycle();
                this.mOriginalBitmap = null;
            }
            Bitmap bitmap2 = this.mAlteredBitmap;
            if (bitmap2 != null && !bitmap2.isRecycled() && !this.mIsRunningCreateFileForBitmap) {
                this.mAlteredBitmap = null;
            }
        }
        System.gc();
        Runtime.getRuntime().gc();
        finish();
    }

    public void onBackPressed() {
        super.onBackPressed();
        recycleBitmapsAndReturn();
    }

    private void showDisclaimer() {
        try {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(getResources().getString(R.string.consult_edit_pic_disclaimer));
            builder.setPositiveButton(getResources().getString(R.string.alert_dialog_confirmation_ok_button_text), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
            builder.show();
        } catch (Exception unused) {
            Trace.w(TAG, "Failed to show disclaimer for consult photo editing");
        }
    }

    private void saveAndReturnImage() {
        View view = this.mProgressBar;
        if (view != null) {
            view.setVisibility(0);
        }
        new CreateFileForBitMapTask(this, this.mAlteredBitmap, new IFileCreatedListener() {
            public void onFileCreated(String str) {
                boolean unused = ConsultEditPictureActivity.this.mIsRunningCreateFileForBitmap = false;
                if (ConsultEditPictureActivity.this.mProgressBar != null) {
                    ConsultEditPictureActivity.this.mProgressBar.setVisibility(8);
                }
                Intent intent = new Intent();
                if (StringUtil.isNotEmpty(str)) {
                    intent.putExtra(Constants.EXTRA_CONSULT_POST_BITMAP, str);
                } else {
                    new MedscapeException(ConsultEditPictureActivity.this.getResources().getString(R.string.failed_to_save_image)).showToast(ConsultEditPictureActivity.this, 1);
                }
                ConsultEditPictureActivity.this.setResult(-1, intent);
                ConsultEditPictureActivity.this.finish();
            }
        }).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        this.mIsRunningCreateFileForBitmap = true;
    }

    private void fetchStatusBarHeight() {
        View view = this.mRootView;
        if (view != null) {
            view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                public void onGlobalLayout() {
                    View decorView;
                    Rect rect = new Rect();
                    Window window = ConsultEditPictureActivity.this.getWindow();
                    if (window != null && (decorView = window.getDecorView()) != null) {
                        decorView.getWindowVisibleDisplayFrame(rect);
                        int i = rect.top;
                        View findViewById = window.findViewById(16908290);
                        if (findViewById != null) {
                            int unused = ConsultEditPictureActivity.this.mStatusBarHeight = Math.abs(i - findViewById.getTop());
                            try {
                                if (Build.VERSION.SDK_INT < 16) {
                                    ConsultEditPictureActivity.this.mRootView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                                } else {
                                    ConsultEditPictureActivity.this.mRootView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                                }
                            } catch (Exception unused2) {
                                Trace.w(ConsultEditPictureActivity.TAG, "Failed to remove global layout listener");
                            }
                        }
                    }
                }
            });
        }
    }

    private void loadImageForEditing() {
        Bundle extras;
        Intent intent = getIntent();
        if (intent != null && (extras = intent.getExtras()) != null) {
            String string = extras.getString(Constants.EXTRA_CONSULT_POST_BITMAP);
            if (StringUtil.isNotEmpty(string)) {
                this.mIsEditingPhoto = true;
                loadPreviouslyEditedPhoto(string);
                return;
            }
            loadImageFromFilePath(extras.getString(Constants.EXTRA_CONSULT_IMAGE_PATH));
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0014, code lost:
        r0 = r0.copy(android.graphics.Bitmap.Config.ARGB_8888, true);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void loadPreviouslyEditedPhoto(java.lang.String r5) {
        /*
            r4 = this;
            r0 = 0
            java.io.FileInputStream r5 = r4.openFileInput(r5)     // Catch:{ Exception -> 0x000d }
            android.graphics.Bitmap r0 = android.graphics.BitmapFactory.decodeStream(r5)     // Catch:{ Exception -> 0x000d }
            r5.close()     // Catch:{ Exception -> 0x000d }
            goto L_0x0011
        L_0x000d:
            r5 = move-exception
            r5.printStackTrace()
        L_0x0011:
            r5 = 1
            if (r0 == 0) goto L_0x003f
            android.graphics.Bitmap$Config r1 = android.graphics.Bitmap.Config.ARGB_8888
            android.graphics.Bitmap r0 = r0.copy(r1, r5)
            if (r0 == 0) goto L_0x003f
            int r1 = r0.getHeight()
            int r2 = r0.getWidth()
            android.widget.RelativeLayout$LayoutParams r3 = new android.widget.RelativeLayout$LayoutParams
            r3.<init>(r2, r1)
            r1 = 13
            r3.addRule(r1)
            android.widget.ImageView r1 = r4.mImageView
            r1.setLayoutParams(r3)
            r1 = 0
            android.widget.ImageView r2 = r4.mImageView
            com.medscape.android.consult.activity.ConsultEditPictureActivity$4 r3 = new com.medscape.android.consult.activity.ConsultEditPictureActivity$4
            r3.<init>(r0)
            r2.post(r3)
            goto L_0x0040
        L_0x003f:
            r1 = 1
        L_0x0040:
            if (r1 == 0) goto L_0x0054
            com.medscape.android.util.MedscapeException r0 = new com.medscape.android.util.MedscapeException
            r1 = 2131952508(0x7f13037c, float:1.954146E38)
            java.lang.String r1 = r4.getString(r1)
            r0.<init>(r1)
            r0.showToast(r4, r5)
            r4.finish()
        L_0x0054:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.medscape.android.consult.activity.ConsultEditPictureActivity.loadPreviouslyEditedPhoto(java.lang.String):void");
    }

    private void loadImageFromFilePath(String str) {
        int width = ((WindowManager) getSystemService("window")).getDefaultDisplay().getWidth();
        boolean z = !StringUtil.isNotEmpty(str);
        Trace.i(TAG, "Calling get bitmap for picture");
        if (width > 1080) {
            width = 1080;
        }
        Bitmap bitMapForPicture = PhotoUtil.getBitMapForPicture(str, width);
        Trace.i(TAG, "Retrieved bitmap to edit");
        if (z) {
            Trace.i(TAG, "Syncing gallery");
            PhotoUtil.galleryAddPic(this);
        }
        PhotoUtil.clearRecentPhotoLocation();
        handleInitialBitmapLoad(bitMapForPicture);
    }

    /* access modifiers changed from: private */
    public void handleInitialBitmapLoad(Bitmap bitmap) {
        if (bitmap != null) {
            this.mOriginalBitmap = bitmap;
            this.mImageView.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                public boolean onPreDraw() {
                    ConsultEditPictureActivity.this.mImageView.getViewTreeObserver().removeOnPreDrawListener(this);
                    Paint unused = ConsultEditPictureActivity.this.mPaint = new Paint();
                    ConsultEditPictureActivity.this.mPaint.setColor(ViewCompat.MEASURED_STATE_MASK);
                    ConsultEditPictureActivity.this.mPaint.setStrokeWidth(3.0f);
                    ConsultEditPictureActivity.this.refreshBitMapForEditingAndCanvas();
                    return true;
                }
            });
            return;
        }
        new MedscapeException(getString(R.string.failed_to_load_image)).showToast(this, 1);
        finish();
    }

    /* access modifiers changed from: private */
    public void detectFaces() {
        if (this.mAlteredBitmap != null) {
            FaceDetector build = new FaceDetector.Builder(this).setTrackingEnabled(false).setMode(1).setLandmarkType(0).build();
            if (build.isOperational()) {
                this.mFaces = build.detect(new Frame.Builder().setBitmap(this.mAlteredBitmap).build());
                build.release();
                System.gc();
                Runtime.getRuntime().gc();
            }
            SparseArray<Face> sparseArray = this.mFaces;
            if (sparseArray == null || sparseArray.size() <= 0) {
                Trace.i(TAG, "No faces detected in image");
                return;
            }
            String str = TAG;
            Trace.i(str, this.mFaces.size() + " faces detected in image");
            coverFaces(this.mCanvas);
            return;
        }
        Trace.w(TAG, "Tried to detect faces for null bitmap");
    }

    /* access modifiers changed from: private */
    public void coverFaces(Canvas canvas) {
        if (this.mFaces != null && canvas != null) {
            Paint paint = new Paint();
            paint.setColor(ViewCompat.MEASURED_STATE_MASK);
            paint.setStrokeWidth(5.0f);
            for (int i = 0; i < this.mFaces.size(); i++) {
                Face valueAt = this.mFaces.valueAt(i);
                float f = valueAt.getPosition().x;
                float f2 = valueAt.getPosition().y;
                float width = valueAt.getPosition().x + valueAt.getWidth();
                float height = valueAt.getPosition().y + valueAt.getHeight();
                int abs = (int) Math.abs(f - width);
                int abs2 = (int) Math.abs(f2 - height);
                canvas.drawCircle((float) ((int) (f + ((float) (abs / 2)))), (float) ((int) (f2 + ((float) (abs2 / 2)))), (float) (abs2 > abs ? abs2 / 2 : abs / 2), paint);
            }
        }
    }

    public void onCropClick(View view) {
        View view2 = this.mProgressBar;
        if (view2 != null) {
            view2.setVisibility(0);
        }
        new CreateFileForBitMapTask(this, this.mAlteredBitmap, new IFileCreatedListener() {
            public void onFileCreated(String str) {
                boolean unused = ConsultEditPictureActivity.this.mIsRunningCreateFileForBitmap = false;
                if (ConsultEditPictureActivity.this.mProgressBar != null) {
                    ConsultEditPictureActivity.this.mProgressBar.setVisibility(8);
                }
                Intent intent = new Intent(ConsultEditPictureActivity.this, ConsultCropImageActivity.class);
                if (StringUtil.isNotEmpty(str)) {
                    intent.putExtra(Constants.EXTRA_CONSULT_CROP_IMAGE, str);
                    ConsultEditPictureActivity.this.startActivityForResult(intent, Constants.REQUEST_CODE_PICTURE_CROP);
                    return;
                }
                new MedscapeException(ConsultEditPictureActivity.this.getResources().getString(R.string.failed_to_load_image_for_crop)).showToast(ConsultEditPictureActivity.this, 1);
            }
        }).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        this.mIsRunningCreateFileForBitmap = true;
    }

    public void onBlackPaintClick(View view) {
        this.mBlackPaintView.setBackgroundColor(getResources().getColor(17170432));
        this.mRedPaintView.setBackgroundColor(getResources().getColor(R.color.consult_action_bar));
        Paint paint = this.mPaint;
        if (paint != null) {
            paint.setColor(ViewCompat.MEASURED_STATE_MASK);
        }
    }

    public void onRedPaintClick(View view) {
        this.mRedPaintView.setBackgroundColor(getResources().getColor(17170432));
        this.mBlackPaintView.setBackgroundColor(getResources().getColor(R.color.consult_action_bar));
        Paint paint = this.mPaint;
        if (paint != null) {
            paint.setColor(-65536);
        }
    }

    public void onThreePixelClick(View view) {
        this.mThreePixelView.setBackgroundColor(getResources().getColor(17170432));
        this.mFivePixelView.setBackgroundColor(getResources().getColor(R.color.consult_action_bar));
        this.mTenPixelView.setBackgroundColor(getResources().getColor(R.color.consult_action_bar));
        Paint paint = this.mPaint;
        if (paint != null) {
            paint.setStrokeWidth(3.0f);
        }
    }

    public void onFivePixelClick(View view) {
        this.mFivePixelView.setBackgroundColor(getResources().getColor(17170432));
        this.mThreePixelView.setBackgroundColor(getResources().getColor(R.color.consult_action_bar));
        this.mTenPixelView.setBackgroundColor(getResources().getColor(R.color.consult_action_bar));
        Paint paint = this.mPaint;
        if (paint != null) {
            paint.setStrokeWidth(5.0f);
        }
    }

    public void onTenPixelClick(View view) {
        this.mTenPixelView.setBackgroundColor(getResources().getColor(17170432));
        this.mThreePixelView.setBackgroundColor(getResources().getColor(R.color.consult_action_bar));
        this.mFivePixelView.setBackgroundColor(getResources().getColor(R.color.consult_action_bar));
        Paint paint = this.mPaint;
        if (paint != null) {
            paint.setStrokeWidth(10.0f);
        }
    }

    public void onResetClick(View view) {
        try {
            CharSequence[] charSequenceArr = {getResources().getString(R.string.consult_edit_picture_reset_face), getResources().getString(R.string.consult_edit_picture_reset_annotations)};
            this.mSelectedResetOptionValue = 0;
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Reset Edits");
            builder.setSingleChoiceItems(charSequenceArr, 0, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    ConsultEditPictureActivity.this.mSelectedResetOptionValue = i;
                }
            });
            builder.setPositiveButton(getResources().getString(R.string.consult_edit_picture_reset), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    if (ConsultEditPictureActivity.this.mSelectedResetOptionValue > -1) {
                        int i2 = ConsultEditPictureActivity.this.mSelectedResetOptionValue;
                        if (i2 != 0) {
                            if (i2 == 1) {
                                ConsultEditPictureActivity.this.resetAnnotations();
                            }
                        } else if (ConsultEditPictureActivity.this.mFaceDetectionEnabled) {
                            ConsultEditPictureActivity.this.showResetFaceWarning();
                        } else {
                            ConsultEditPictureActivity.this.resetFaceDetections();
                        }
                    }
                }
            });
            builder.setNegativeButton(getResources().getString(R.string.cancel), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
            builder.show();
        } catch (Exception unused) {
            Trace.w(TAG, "Failed to show disclaimer for consult photo editing");
        }
    }

    /* access modifiers changed from: private */
    public void showResetFaceWarning() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getResources().getString(R.string.dialog_warning_title));
        builder.setMessage(getResources().getString(R.string.consult_edit_picture_reset_face_warning));
        builder.setPositiveButton(getResources().getString(R.string.alert_dialog_confirmation_positive_button_text), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                ConsultEditPictureActivity.this.resetFaceDetections();
            }
        });
        builder.setNegativeButton(getResources().getString(R.string.alert_dialog_confirmation_negative_button_text), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.show();
    }

    /* access modifiers changed from: private */
    public void resetAnnotations() {
        resetToOriginalImage(new ViewTreeObserver.OnPreDrawListener() {
            public boolean onPreDraw() {
                ConsultEditPictureActivity.this.mLines.clear();
                ConsultEditPictureActivity.this.mImageView.getViewTreeObserver().removeOnPreDrawListener(this);
                ConsultEditPictureActivity.this.refreshBitMapForEditingAndCanvas();
                if (!ConsultEditPictureActivity.this.mFaceDetectionEnabled) {
                    return true;
                }
                ConsultEditPictureActivity consultEditPictureActivity = ConsultEditPictureActivity.this;
                consultEditPictureActivity.coverFaces(consultEditPictureActivity.mCanvas);
                return true;
            }
        });
    }

    /* access modifiers changed from: private */
    public void redrawLines() {
        List<GraphicLine> list = this.mLines;
        if (list != null && !list.isEmpty()) {
            for (GraphicLine next : this.mLines) {
                this.mCanvas.drawLine(next.getDownX(), next.getDownY(), next.getUpX(), next.getUpY(), next.getPaint());
            }
            this.mImageView.invalidate();
        }
    }

    /* access modifiers changed from: private */
    public void resetFaceDetections() {
        if (this.mFaceDetectionEnabled) {
            this.mFaceDetectionEnabled = false;
            resetToOriginalImage(new ViewTreeObserver.OnPreDrawListener() {
                public boolean onPreDraw() {
                    ConsultEditPictureActivity.this.mImageView.getViewTreeObserver().removeOnPreDrawListener(this);
                    ConsultEditPictureActivity.this.refreshBitMapForEditingAndCanvas();
                    ConsultEditPictureActivity.this.redrawLines();
                    ConsultEditPictureActivity.this.showFaceDetectionRemovedWarning();
                    return true;
                }
            });
            return;
        }
        this.mFaceDetectionEnabled = true;
        resetToOriginalImage(new ViewTreeObserver.OnPreDrawListener() {
            public boolean onPreDraw() {
                ConsultEditPictureActivity.this.mImageView.getViewTreeObserver().removeOnPreDrawListener(this);
                ConsultEditPictureActivity.this.refreshBitMapForEditingAndCanvas();
                ConsultEditPictureActivity consultEditPictureActivity = ConsultEditPictureActivity.this;
                consultEditPictureActivity.coverFaces(consultEditPictureActivity.mCanvas);
                ConsultEditPictureActivity.this.redrawLines();
                ConsultEditPictureActivity.this.showFaceDetectionRestoredMessage();
                return true;
            }
        });
    }

    private void setImageViewLayoutSizeToBitmapSize() {
        int height = this.mOriginalBitmap.getHeight();
        int width = this.mOriginalBitmap.getWidth();
        Bitmap bitmap = this.mAlteredBitmap;
        if (bitmap != null) {
            height = bitmap.getHeight();
            width = this.mAlteredBitmap.getWidth();
        }
        double d = (double) width;
        double d2 = (double) height;
        double min = Math.min(((double) this.mOriginalImageViewWidth) / d, ((double) this.mOriginalImageViewHeight) / d2);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams((int) (d * min), (int) (d2 * min));
        layoutParams.addRule(13);
        this.mImageView.setLayoutParams(layoutParams);
    }

    /* access modifiers changed from: private */
    public void refreshBitMapForEditingAndCanvas() {
        ImageView imageView;
        boolean z;
        if (this.mOriginalBitmap != null && (imageView = this.mImageView) != null && imageView.getMeasuredHeight() > 0 && this.mImageView.getMeasuredWidth() > 0) {
            if (this.mOriginalImageViewHeight == -1 || this.mOriginalImageViewWidth == -1) {
                z = true;
                this.mOriginalImageViewHeight = this.mImageView.getMeasuredHeight();
                this.mOriginalImageViewWidth = this.mImageView.getMeasuredWidth();
                if (this.mOriginalImageViewHeight > 1080) {
                    this.mOriginalImageViewHeight = 1080;
                }
                if (this.mOriginalImageViewWidth > 1080) {
                    this.mOriginalImageViewWidth = 1080;
                }
            } else {
                z = false;
            }
            if (z) {
                setImageViewLayoutSizeToBitmapSize();
                this.mImageView.post(new Runnable() {
                    public void run() {
                        ConsultEditPictureActivity.this.mImageView.requestLayout();
                        Trace.i(ConsultEditPictureActivity.TAG, "Making a scaled copy of the bitmap");
                        if (ConsultEditPictureActivity.this.mAlteredBitmap != null) {
                            ConsultEditPictureActivity consultEditPictureActivity = ConsultEditPictureActivity.this;
                            Bitmap unused = consultEditPictureActivity.mAlteredBitmap = Bitmap.createScaledBitmap(consultEditPictureActivity.mAlteredBitmap, ConsultEditPictureActivity.this.mImageView.getMeasuredWidth(), ConsultEditPictureActivity.this.mImageView.getMeasuredHeight(), false);
                        } else {
                            ConsultEditPictureActivity consultEditPictureActivity2 = ConsultEditPictureActivity.this;
                            Bitmap unused2 = consultEditPictureActivity2.mAlteredBitmap = Bitmap.createScaledBitmap(consultEditPictureActivity2.mOriginalBitmap, ConsultEditPictureActivity.this.mImageView.getMeasuredWidth(), ConsultEditPictureActivity.this.mImageView.getMeasuredHeight(), false);
                        }
                        Trace.i(ConsultEditPictureActivity.TAG, "Created scaled copy of the bitmap");
                        ConsultEditPictureActivity.this.refreshCanvas();
                        ConsultEditPictureActivity.this.detectFaces();
                    }
                });
                return;
            }
            this.mAlteredBitmap = Bitmap.createScaledBitmap(this.mOriginalBitmap, this.mImageView.getMeasuredWidth(), this.mImageView.getMeasuredHeight(), false);
            refreshCanvas();
        }
    }

    /* access modifiers changed from: private */
    public void refreshCanvas() {
        Canvas canvas = new Canvas(this.mAlteredBitmap);
        this.mCanvas = canvas;
        canvas.clipRect(0, 0, this.mImageView.getMeasuredWidth(), this.mImageView.getMeasuredHeight());
        this.mCanvas.drawBitmap(this.mAlteredBitmap, new Matrix(), this.mPaint);
        this.mImageView.setImageBitmap(this.mAlteredBitmap);
        this.mImageView.post(new Runnable() {
            public void run() {
                ConsultEditPictureActivity.this.mImageView.requestLayout();
            }
        });
    }

    /* access modifiers changed from: private */
    public void showFaceDetectionRemovedWarning() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getResources().getString(R.string.dialog_warning_title));
        builder.setMessage(getResources().getString(R.string.consult_face_detection_removed));
        builder.setPositiveButton(getResources().getString(R.string.alert_dialog_test_drive_ok_button_text), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.show();
    }

    /* access modifiers changed from: private */
    public void showFaceDetectionRestoredMessage() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(getResources().getString(R.string.consult_face_detection_restored));
        builder.setPositiveButton(getResources().getString(R.string.alert_dialog_test_drive_ok_button_text), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.show();
    }

    private void resetToOriginalImage(final ViewTreeObserver.OnPreDrawListener onPreDrawListener) {
        int height = this.mOriginalBitmap.getHeight();
        double width = (double) this.mOriginalBitmap.getWidth();
        double d = (double) height;
        double min = Math.min(((double) this.mOriginalImageViewWidth) / width, ((double) this.mOriginalImageViewHeight) / d);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams((int) (width * min), (int) (d * min));
        layoutParams.addRule(13);
        this.mImageView.setLayoutParams(layoutParams);
        this.mImageView.post(new Runnable() {
            public void run() {
                ConsultEditPictureActivity.this.mImageView.requestLayout();
                ConsultEditPictureActivity.this.mImageView.getViewTreeObserver().addOnPreDrawListener(onPreDrawListener);
            }
        });
    }

    private void addTouchListenerForImage() {
        ImageView imageView = this.mImageView;
        if (imageView != null) {
            imageView.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    int action = motionEvent.getAction();
                    ActionBar supportActionBar = ConsultEditPictureActivity.this.getSupportActionBar();
                    float y = ConsultEditPictureActivity.this.mImageView.getY();
                    float x = ConsultEditPictureActivity.this.mImageView.getX();
                    float height = y + ((float) (supportActionBar != null ? supportActionBar.getHeight() : 0)) + ((float) ConsultEditPictureActivity.this.mStatusBarHeight);
                    if (action == 0) {
                        float unused = ConsultEditPictureActivity.this.downx = motionEvent.getRawX() - x;
                        float unused2 = ConsultEditPictureActivity.this.downy = motionEvent.getRawY() - height;
                    } else if (action == 1) {
                        float unused3 = ConsultEditPictureActivity.this.upx = motionEvent.getRawX() - x;
                        float unused4 = ConsultEditPictureActivity.this.upy = motionEvent.getRawY() - height;
                        ConsultEditPictureActivity.this.mLines.add(new GraphicLine(ConsultEditPictureActivity.this.mPaint.getColor(), ConsultEditPictureActivity.this.mPaint.getStrokeWidth(), ConsultEditPictureActivity.this.downx, ConsultEditPictureActivity.this.downy, ConsultEditPictureActivity.this.upx, ConsultEditPictureActivity.this.upy));
                        ConsultEditPictureActivity.this.mCanvas.drawLine(ConsultEditPictureActivity.this.downx, ConsultEditPictureActivity.this.downy, ConsultEditPictureActivity.this.upx, ConsultEditPictureActivity.this.upy, ConsultEditPictureActivity.this.mPaint);
                        ConsultEditPictureActivity.this.mImageView.invalidate();
                    } else if (action == 2) {
                        float unused5 = ConsultEditPictureActivity.this.upx = motionEvent.getRawX() - x;
                        float unused6 = ConsultEditPictureActivity.this.upy = motionEvent.getRawY() - height;
                        ConsultEditPictureActivity.this.mLines.add(new GraphicLine(ConsultEditPictureActivity.this.mPaint.getColor(), ConsultEditPictureActivity.this.mPaint.getStrokeWidth(), ConsultEditPictureActivity.this.downx, ConsultEditPictureActivity.this.downy, ConsultEditPictureActivity.this.upx, ConsultEditPictureActivity.this.upy));
                        ConsultEditPictureActivity.this.mCanvas.drawLine(ConsultEditPictureActivity.this.downx, ConsultEditPictureActivity.this.downy, ConsultEditPictureActivity.this.upx, ConsultEditPictureActivity.this.upy, ConsultEditPictureActivity.this.mPaint);
                        ConsultEditPictureActivity.this.mImageView.invalidate();
                        ConsultEditPictureActivity consultEditPictureActivity = ConsultEditPictureActivity.this;
                        float unused7 = consultEditPictureActivity.downx = consultEditPictureActivity.upx;
                        ConsultEditPictureActivity consultEditPictureActivity2 = ConsultEditPictureActivity.this;
                        float unused8 = consultEditPictureActivity2.downy = consultEditPictureActivity2.upy;
                    }
                    return true;
                }
            });
        }
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == -1 && i == 4012) {
            Bitmap bitmap = null;
            try {
                FileInputStream openFileInput = openFileInput(intent.getStringExtra(Constants.EXTRA_CONSULT_CROP_IMAGE));
                bitmap = BitmapFactory.decodeStream(openFileInput);
                openFileInput.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (bitmap != null) {
                Bitmap bitmap2 = this.mAlteredBitmap;
                if (bitmap2 != null && !bitmap2.isRecycled()) {
                    this.mAlteredBitmap.recycle();
                }
                this.mAlteredBitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true);
                setImageViewLayoutSizeToBitmapSize();
                this.mImageView.post(new Runnable() {
                    public void run() {
                        ConsultEditPictureActivity.this.mImageView.requestLayout();
                        ConsultEditPictureActivity consultEditPictureActivity = ConsultEditPictureActivity.this;
                        Bitmap unused = consultEditPictureActivity.mAlteredBitmap = Bitmap.createScaledBitmap(consultEditPictureActivity.mAlteredBitmap, ConsultEditPictureActivity.this.mImageView.getMeasuredWidth(), ConsultEditPictureActivity.this.mImageView.getMeasuredHeight(), false);
                        ConsultEditPictureActivity.this.refreshCanvas();
                    }
                });
            }
        }
    }

    public void onLowMemory() {
        super.onLowMemory();
        Trace.e(TAG, "MEMORY IS LOW!!");
    }
}
