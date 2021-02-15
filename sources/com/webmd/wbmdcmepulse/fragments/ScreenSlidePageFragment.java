package com.webmd.wbmdcmepulse.fragments;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;
import com.webmd.wbmdcmepulse.R;
import com.webmd.wbmdcmepulse.customviews.ZoomableImageView;
import com.webmd.wbmdcmepulse.models.articles.Slide;
import com.webmd.wbmdcmepulse.models.interfaces.IImageLoadedEvent;
import com.webmd.wbmdcmepulse.models.interfaces.IViewPagerClickListener;
import com.webmd.wbmdcmepulse.models.utils.BitmapTransformations;
import com.webmd.wbmdcmepulse.models.utils.Constants;
import com.webmd.wbmdcmepulse.models.utils.ExtensionsAspectRatio;

public class ScreenSlidePageFragment extends Fragment {
    private static Context mContext;
    private static int mSlideCount;
    private final int COUNTER_ANIMATION_DELAY = ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION;
    private final int COUNTER_FIRST_SLIDE_ANIMATION_DELAY = 1000;
    private final int SLIDE_INSTRUCTIONS_ANIMATION_DELAY = 2000;
    private Target imageTarget;
    private String mArticleId;
    private BitmapTransformations mBitMapTransformation;
    /* access modifiers changed from: private */
    public IImageLoadedEvent mImageLoadedCallback;
    ImageView mImageView = null;
    private boolean mIsFullScreen;
    private int mOriginalHeight;
    private int mOriginalWidth;
    private ViewGroup mRootView;
    private Slide mSlide;
    private TextView mSlideCounterTextView;
    private ImageView mSlideImageView;
    private TextView mSlideInstructionsTextView;
    /* access modifiers changed from: private */
    public int mSlideNumber;
    private ZoomableImageView mSlideZoomableImageView;
    /* access modifiers changed from: private */
    public IViewPagerClickListener mViewPagerClickListener;

    public static ScreenSlidePageFragment newInstance(Slide slide, int i, int i2, Context context, String str, boolean z, IViewPagerClickListener iViewPagerClickListener, IImageLoadedEvent iImageLoadedEvent) {
        ScreenSlidePageFragment screenSlidePageFragment = new ScreenSlidePageFragment();
        screenSlidePageFragment.mSlide = slide;
        screenSlidePageFragment.mSlideNumber = i;
        mSlideCount = i2;
        screenSlidePageFragment.mArticleId = str;
        mContext = context;
        screenSlidePageFragment.mViewPagerClickListener = iViewPagerClickListener;
        screenSlidePageFragment.mIsFullScreen = z;
        screenSlidePageFragment.mImageLoadedCallback = iImageLoadedEvent;
        return screenSlidePageFragment;
    }

    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        if (z) {
            TextView textView = this.mSlideInstructionsTextView;
            if (textView != null) {
                animateView(textView, 2000);
            }
            TextView textView2 = this.mSlideCounterTextView;
            if (textView2 != null) {
                animateView(textView2, ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION);
            }
        }
        ZoomableImageView zoomableImageView = this.mSlideZoomableImageView;
        if (zoomableImageView != null) {
            zoomableImageView.setImageBitmap(zoomableImageView.getPhotoBitmap());
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putBoolean(Constants.BUNDLE_KEY_IMAGE_FULL_SCREEN, this.mIsFullScreen);
        bundle.putParcelable(Constants.BUNDLE_KEY_IMAGE_SLIDE, this.mSlide);
        bundle.putInt("bundle_key_image_slide_position", this.mSlideNumber);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (bundle != null) {
            this.mIsFullScreen = bundle.getBoolean(Constants.BUNDLE_KEY_IMAGE_FULL_SCREEN);
            this.mSlide = (Slide) bundle.getParcelable(Constants.BUNDLE_KEY_IMAGE_SLIDE);
            this.mSlideNumber = bundle.getInt("bundle_key_image_slide_position");
        }
        if (this.mBitMapTransformation == null) {
            setUpImageTransformation();
        }
        if (this.mIsFullScreen) {
            this.mRootView = (ViewGroup) layoutInflater.inflate(R.layout.cme_fragment_full_screen_slide_page, viewGroup, false);
        } else {
            this.mRootView = (ViewGroup) layoutInflater.inflate(R.layout.cme_fragment_screen_slide_page, viewGroup, false);
        }
        this.mRootView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (ScreenSlidePageFragment.this.mViewPagerClickListener != null) {
                    ScreenSlidePageFragment.this.mViewPagerClickListener.onSlideClicked(ScreenSlidePageFragment.this.mSlideNumber);
                }
            }
        });
        this.mSlideCounterTextView = (TextView) this.mRootView.findViewById(R.id.slide_counter);
        ZoomableImageView zoomableImageView = (ZoomableImageView) this.mRootView.findViewById(R.id.slide_image_view_zoom);
        this.mSlideZoomableImageView = zoomableImageView;
        if (mSlideCount > 1) {
            zoomableImageView.setDefaultScale(0);
        }
        if (getResources().getConfiguration().orientation == 2) {
            this.mSlideZoomableImageView.setOrientation(0);
        } else {
            this.mSlideZoomableImageView.setOrientation(1);
        }
        ImageView imageView = (ImageView) this.mRootView.findViewById(R.id.slide_image_view);
        this.mSlideImageView = imageView;
        this.mImageView = null;
        if (this.mIsFullScreen) {
            if (imageView != null) {
                imageView.setVisibility(8);
            }
            ZoomableImageView zoomableImageView2 = this.mSlideZoomableImageView;
            if (zoomableImageView2 != null) {
                this.mImageView = zoomableImageView2;
            }
        } else {
            if (imageView != null) {
                this.mImageView = imageView;
            }
            ZoomableImageView zoomableImageView3 = this.mSlideZoomableImageView;
            if (zoomableImageView3 != null) {
                zoomableImageView3.setVisibility(8);
            }
        }
        ImageView imageView2 = this.mImageView;
        if (imageView2 != null) {
            imageView2.setVisibility(0);
            this.mImageView.setTag(this.mSlide.graphicUrl);
            this.imageTarget = new Target() {
                public void onBitmapFailed(Exception exc, Drawable drawable) {
                }

                public void onPrepareLoad(Drawable drawable) {
                }

                public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom loadedFrom) {
                    if (ScreenSlidePageFragment.this.mImageView != null) {
                        ScreenSlidePageFragment.this.mImageView.setImageBitmap(bitmap);
                    }
                    if (ScreenSlidePageFragment.this.mImageLoadedCallback != null) {
                        ScreenSlidePageFragment.this.mImageLoadedCallback.onImageLoaded(ScreenSlidePageFragment.this.mImageView, bitmap);
                    }
                }
            };
            Picasso.get().load(this.mSlide.graphicUrl).placeholder(R.drawable.cme_placeholder_image).into(this.imageTarget);
        }
        setUpSlideSettings();
        return this.mRootView;
    }

    public ZoomableImageView getmImageView() {
        return this.mSlideZoomableImageView;
    }

    private void setUpSlideSettings() {
        if (mSlideCount == 1) {
            this.mSlideCounterTextView.setVisibility(8);
        } else if (this.mSlideNumber == 0) {
            TextView textView = this.mSlideCounterTextView;
            textView.setText("1 of " + mSlideCount);
            TextView textView2 = (TextView) this.mRootView.findViewById(R.id.swipe_message);
            this.mSlideInstructionsTextView = textView2;
            if (textView2 != null) {
                animateView(textView2, 2000);
            }
            animateView(this.mSlideCounterTextView, 1000);
        } else {
            TextView textView3 = this.mSlideCounterTextView;
            if (textView3 != null) {
                textView3.setText((this.mSlideNumber + 1) + " of " + mSlideCount);
            }
        }
    }

    private void setUpImageTransformation() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        this.mOriginalWidth = displayMetrics.widthPixels;
        this.mOriginalHeight = displayMetrics.heightPixels;
        if (getResources().getConfiguration().orientation == 2) {
            this.mOriginalWidth = ExtensionsAspectRatio.getAdjustedWidth(4, 3, this.mOriginalHeight);
        } else if (getResources().getConfiguration().orientation == 1) {
            this.mOriginalHeight = ExtensionsAspectRatio.getAdjustedHeight(4, 3, this.mOriginalWidth);
        }
        this.mBitMapTransformation = new BitmapTransformations();
        if (getResources().getConfiguration().orientation != 1) {
            this.mBitMapTransformation.setTargetWidth(this.mOriginalWidth);
            this.mBitMapTransformation.setTargetHeight(this.mOriginalHeight);
        }
    }

    private void animateView(final View view, int i) {
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

    public void onDestroyView() {
        if (this.imageTarget != null) {
            Picasso.get().cancelRequest(this.imageTarget);
        }
        super.onDestroyView();
    }
}
