package com.medscape.android.contentviewer;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.core.app.ActivityCompat;
import androidx.core.content.PermissionChecker;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.viewpager.widget.ViewPager;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomViewTarget;
import com.bumptech.glide.request.transition.Transition;
import com.medscape.android.BI.omniture.OmnitureManager;
import com.medscape.android.Constants;
import com.medscape.android.R;
import com.medscape.android.base.BaseActivity;
import com.medscape.android.contentviewer.interfaces.IImageLoadedEvent;
import com.medscape.android.contentviewer.interfaces.IViewPagerClickListener;
import com.medscape.android.contentviewer.model.Slide;
import com.medscape.android.util.StringUtil;
import com.medscape.android.util.Util;
import com.medscape.android.util.media.PhotoUtil;
import com.medscape.android.view.ZoomOutPageTransformer;
import com.wbmd.wbmdcommons.utils.ChronicleIDUtil;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ImageViewerActivity extends BaseActivity {
    private static final String TAG = ImageViewerActivity.class.getSimpleName();
    private final int COUNTER_ANIMATION_DELAY = ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION;
    private final int COUNTER_FIRST_SLIDE_ANIMATION_DELAY = 1000;
    private final int ID_REQUEST_STORAGE = 1002;
    private final int SLIDE_INSTRUCTIONS_ANIMATION_DELAY = 2000;
    /* access modifiers changed from: private */
    public String articleId;
    /* access modifiers changed from: private */
    public String assetId;
    private boolean doShowSlideCounterForSingleImage;
    /* access modifiers changed from: private */
    public boolean mCanbeDownloaded;
    private View mCaptionContainer;
    /* access modifiers changed from: private */
    public String mChronicleId;
    /* access modifiers changed from: private */
    public Context mContext;
    private int mCurrentPosition;
    private ImageView mExpandCollapseTextToggleButton;
    private CustomViewTarget mImageTarget;
    /* access modifiers changed from: private */
    public ViewPager mImageViewPager;
    /* access modifiers changed from: private */
    public boolean mIsfromCKB;
    private View mRootView;
    /* access modifiers changed from: private */
    public ImageView mSingleImageView;
    private TextView mSlideCaptionsTextView;
    private TextView mSlideCounter;
    private TextView mSlideInstructionsTextView;
    private ArrayList<Slide> mSlides;
    private ScreenSlidePagerAdapter mSlidesPagerAdapter;
    /* access modifiers changed from: private */
    public String pageName;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_image_viewer);
        this.mContext = this;
        Intent intent = getIntent();
        this.mRootView = findViewById(R.id.root_layout);
        this.mSlides = intent.getParcelableArrayListExtra("bundle_key_image_slides");
        this.mCanbeDownloaded = !intent.getBooleanExtra(Constants.BUNDLE_KEY_IMAGE_NO_DOWNLOAD, false);
        this.mCurrentPosition = intent.getIntExtra("bundle_key_image_slide_position", 0);
        this.doShowSlideCounterForSingleImage = intent.getBooleanExtra(Constants.BUNDLE_KEY_DO_SHOW_SLIDECOUNTER, false);
        this.mIsfromCKB = intent.getBooleanExtra(Constants.BUNDLE_KEY_FROM_CKB, false);
        this.assetId = intent.getStringExtra(Constants.BUNDLE_KEY_ASSET_ID);
        this.articleId = String.valueOf(intent.getIntExtra(Constants.BUNDLE_KEY_ARTICLE_ID, 0));
        this.pageName = intent.getStringExtra(Constants.BUNDLE_KEY_PAGE_NAME);
        setUpViews();
    }

    public void onBackPressed() {
        finishWithResult();
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (configuration.orientation == 2) {
            ImageView imageView = this.mSingleImageView;
            if (imageView != null) {
                imageView.getLayoutParams().height = (int) Util.dpToPixel(this.mContext, ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION);
            }
        } else {
            ImageView imageView2 = this.mSingleImageView;
            if (imageView2 != null) {
                imageView2.getLayoutParams().height = (int) Util.dpToPixel(this.mContext, 400);
            }
        }
        ArrayList<Slide> arrayList = this.mSlides;
        if (arrayList != null && arrayList.size() > 1) {
            ScreenSlidePagerAdapter screenSlidePagerAdapter = this.mSlidesPagerAdapter;
            if (screenSlidePagerAdapter != null) {
                this.mCurrentPosition = screenSlidePagerAdapter.getCurrentPosition() - 1;
            }
            setUpSlidesView();
        }
    }

    private void finishWithResult() {
        if (this.mSlides.size() == 1) {
            finish();
            return;
        }
        Intent intent = getIntent();
        intent.putExtra("bundle_key_image_slide_position", this.mImageViewPager.getCurrentItem());
        setResult(-1, intent);
        finish();
    }

    private void setUpViews() {
        if (this.mSlides.size() == 1) {
            setupSingleImageView(this.mSlides.get(0));
        } else {
            setUpSlidesView();
        }
        if (this.mSlides.size() != 0 && this.mIsfromCKB) {
            this.mChronicleId = new ChronicleIDUtil().generateAssetId(String.valueOf(this.articleId), this.assetId, this.pageName + "/view/" + this.articleId + "-media-1");
            this.mPvid = OmnitureManager.get().trackPageViewWithPrevious(this, "-media-1", this.mChronicleId);
        }
    }

    private void setupSingleImageView(Slide slide) {
        if (slide != null) {
            this.mSlideCounter = (TextView) findViewById(R.id.slide_counter);
            this.mSingleImageView = (ImageView) findViewById(R.id.single_image);
            final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progress_bar);
            progressBar.setVisibility(0);
            this.mSlideCaptionsTextView = (TextView) findViewById(R.id.caption);
            this.mCaptionContainer = findViewById(R.id.captionContainer);
            ImageView imageView = (ImageView) findViewById(R.id.expandCollapseTextToggle);
            this.mExpandCollapseTextToggleButton = imageView;
            imageView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    ImageViewerActivity.this.onExpandCollapseTextToggle(view);
                }
            });
            this.mSingleImageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            this.mSingleImageView.setVisibility(0);
            this.mSlideCounter.setText(getResources().getString(R.string.slides_counter_1_of_1));
            if (this.doShowSlideCounterForSingleImage) {
                this.mSlideCounter.setVisibility(0);
            } else {
                this.mSlideCounter.setVisibility(8);
            }
            if (slide.graphicUrl != null) {
                this.mImageTarget = new CustomViewTarget<ImageView, Bitmap>(this.mSingleImageView) {
                    public void onLoadFailed(Drawable drawable) {
                    }

                    /* access modifiers changed from: protected */
                    public void onResourceCleared(Drawable drawable) {
                    }

                    public void onResourceReady(final Bitmap bitmap, Transition<? super Bitmap> transition) {
                        ImageViewerActivity.this.mSingleImageView.setImageBitmap(bitmap);
                        this.runOnUiThread(new Runnable() {
                            public void run() {
                                progressBar.setVisibility(8);
                                if (ImageViewerActivity.this.mCanbeDownloaded) {
                                    ImageViewerActivity.this.mSingleImageView.setOnLongClickListener(new View.OnLongClickListener() {
                                        public boolean onLongClick(View view) {
                                            AlertDialog.Builder builder = new AlertDialog.Builder(ImageViewerActivity.this.mContext, R.style.MedscapeAppTheme_AlertDialog);
                                            builder.setMessage(R.string.alert_dialog_save_image).setNegativeButton(R.string.alert_dialog_confirmation_cancel_button_text, new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                    dialogInterface.cancel();
                                                }
                                            }).setPositiveButton(R.string.alert_dialog_confirmation_yes_button_text, new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                    ImageViewerActivity.this.saveImage(bitmap);
                                                    dialogInterface.dismiss();
                                                }
                                            });
                                            AlertDialog create = builder.create();
                                            if (Build.VERSION.SDK_INT < 21) {
                                                create.getWindow().setBackgroundDrawable(new ColorDrawable(0));
                                            }
                                            create.show();
                                            return false;
                                        }
                                    });
                                }
                            }
                        });
                    }
                };
                Glide.with((FragmentActivity) this).asBitmap().load(this.mSlides.get(0).graphicUrl).into(this.mImageTarget);
            }
            if (StringUtil.isNotEmpty(slide.caption)) {
                this.mSlideCaptionsTextView.setText(slide.caption);
                this.mSlideCaptionsTextView.setMovementMethod(new ScrollingMovementMethod());
                this.mCaptionContainer.setVisibility(0);
                return;
            }
            this.mCaptionContainer.setVisibility(8);
        }
    }

    /* access modifiers changed from: private */
    public void saveImage(Bitmap bitmap) {
        if (Build.VERSION.SDK_INT < 23 || PermissionChecker.checkSelfPermission(this, "android.permission.WRITE_EXTERNAL_STORAGE") == 0) {
            File outputFile = getOutputFile();
            if (outputFile == null) {
                Log.d(TAG, "Error creating media file ");
                Toast.makeText(this.mContext, "Could not save image", 0).show();
                return;
            }
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(outputFile);
                bitmap.compress(Bitmap.CompressFormat.PNG, 90, fileOutputStream);
                fileOutputStream.close();
                Toast.makeText(this.mContext, "Image Saved", 0).show();
            } catch (FileNotFoundException e) {
                Log.d(TAG, e.getMessage());
                Toast.makeText(this.mContext, "Could not save image", 0).show();
            } catch (IOException e2) {
                Log.d(TAG, e2.getMessage());
                Toast.makeText(this.mContext, "Could not save image", 0).show();
            }
        } else {
            requestStoragePermission();
        }
    }

    private File getOutputFile() {
        File externalPhotoSaveDirectory = PhotoUtil.getExternalPhotoSaveDirectory("Medscape");
        if (!externalPhotoSaveDirectory.exists() && !externalPhotoSaveDirectory.mkdirs()) {
            return null;
        }
        File file = new File(externalPhotoSaveDirectory.getPath() + File.separator + ("Medscape_" + new SimpleDateFormat("ddMMyyyy_HHmm").format(new Date()) + ".jpg"));
        addImageToGallery(file);
        return file;
    }

    private void addImageToGallery(File file) {
        Intent intent = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
        intent.setData(Uri.fromFile(new File(file.getPath())));
        sendBroadcast(intent);
    }

    private void setUpSlidesView() {
        ViewPager viewPager = (ViewPager) findViewById(R.id.slides_pager);
        this.mImageViewPager = viewPager;
        viewPager.setVisibility(0);
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        this.mSlidesPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager(), this.mSlides, true, (IViewPagerClickListener) null, new IImageLoadedEvent() {
            public void loadImage(String str, ImageView imageView) {
            }

            public void onImageLoaded(final ImageView imageView, final Bitmap bitmap) {
                ImageViewerActivity.this.mImageViewPager.post(new Runnable() {
                    public void run() {
                        imageView.post(new Runnable() {
                            public void run() {
                                imageView.setImageBitmap(bitmap);
                                ImageViewerActivity.this.mImageViewPager.post(new Runnable() {
                                    public void run() {
                                        ImageViewerActivity.this.mImageViewPager.requestLayout();
                                    }
                                });
                            }
                        });
                    }
                });
            }
        });
        if (Util.isPhone()) {
            this.mImageViewPager.setPageTransformer(true, new ZoomOutPageTransformer());
        }
        this.mImageViewPager.setAdapter(this.mSlidesPagerAdapter);
        this.mImageViewPager.setCurrentItem(this.mCurrentPosition);
        final Toast makeText = Toast.makeText(getApplicationContext(), getResources().getString(R.string.slides_swipe_instructions), 0);
        ArrayList<Slide> arrayList = this.mSlides;
        if (arrayList != null && this.mCurrentPosition + 1 < arrayList.size()) {
            makeText.show();
        }
        this.mImageViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageScrolled(int i, float f, int i2) {
            }

            public void onPageSelected(int i) {
                if (ImageViewerActivity.this.mIsfromCKB) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(ImageViewerActivity.this.pageName);
                    sb.append("/view/");
                    sb.append(ImageViewerActivity.this.articleId);
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("-media-");
                    int i2 = i + 1;
                    sb2.append(String.valueOf(i2));
                    sb.append(sb2.toString());
                    String unused = ImageViewerActivity.this.mChronicleId = new ChronicleIDUtil().generateAssetId(String.valueOf(ImageViewerActivity.this.articleId), ImageViewerActivity.this.assetId, sb.toString());
                    ImageViewerActivity imageViewerActivity = ImageViewerActivity.this;
                    OmnitureManager omnitureManager = OmnitureManager.get();
                    ImageViewerActivity imageViewerActivity2 = ImageViewerActivity.this;
                    imageViewerActivity.mPvid = omnitureManager.trackPageViewWithPrevious(imageViewerActivity2, "-media-" + String.valueOf(i2), ImageViewerActivity.this.mChronicleId);
                }
            }

            public void onPageScrollStateChanged(int i) {
                Toast toast = makeText;
                if (toast != null) {
                    toast.cancel();
                }
            }
        });
    }

    public void onCloseClick(View view) {
        onBackPressed();
    }

    public void onExpandCollapseTextToggle(View view) {
        ImageView imageView = (ImageView) view;
        if ("expanded".equalsIgnoreCase(imageView.getTag().toString())) {
            this.mSlideCaptionsTextView.setMaxLines(1);
            imageView.setImageResource(R.drawable.ic_expand_less_white_24dp);
            imageView.setTag("collapsed");
            return;
        }
        this.mSlideCaptionsTextView.setMaxLines(Integer.MAX_VALUE);
        imageView.setImageResource(R.drawable.ic_expand_more_white_24dp);
        imageView.setTag("expanded");
    }

    private void animateSlideCounter(final View view, int i) {
        view.setVisibility(0);
        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
        alphaAnimation.setDuration(1000);
        alphaAnimation.setStartOffset((long) i);
        view.startAnimation(alphaAnimation);
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                view.setVisibility(4);
            }
        });
    }

    private void requestStoragePermission() {
        runOnUiThread(new Runnable() {
            public void run() {
                ActivityCompat.requestPermissions(ImageViewerActivity.this, new String[]{"android.permission.WRITE_EXTERNAL_STORAGE"}, 1002);
            }
        });
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        if (i == 1002) {
            if (iArr.length <= 0 || iArr[0] != 0) {
                Toast.makeText(this, "Please check if permission to access External Storage is granted in Medscape App Settings", 1).show();
                return;
            }
            ImageView imageView = this.mSingleImageView;
            if (imageView == null || imageView.getDrawable() == null) {
                Toast.makeText(this, "Could not save image, please try again", 0).show();
            } else {
                saveImage(((BitmapDrawable) this.mSingleImageView.getDrawable()).getBitmap());
            }
        }
    }
}
