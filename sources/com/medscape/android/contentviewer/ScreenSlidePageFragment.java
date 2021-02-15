package com.medscape.android.contentviewer;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import com.bumptech.glide.request.target.CustomViewTarget;
import com.bumptech.glide.request.transition.Transition;
import com.medscape.android.R;
import com.medscape.android.contentviewer.interfaces.IImageLoadedEvent;
import com.medscape.android.contentviewer.interfaces.IViewPagerClickListener;
import com.medscape.android.contentviewer.model.Slide;
import com.medscape.android.util.ExtensionsAspectRatio;
import com.medscape.android.util.GlideApp;
import com.medscape.android.util.StringUtil;
import com.medscape.android.util.Util;
import com.medscape.android.view.ZoomableImageView;

public class ScreenSlidePageFragment extends Fragment {
    private View mCaptionContainer;
    private ImageView mExpandCollapseTextToggleButton;
    /* access modifiers changed from: private */
    public IImageLoadedEvent mImageLoadedCallback;
    private CustomViewTarget mImageTarget;
    private boolean mIsFullScreen;
    /* access modifiers changed from: private */
    public ProgressBar mProgressbar;
    private ViewGroup mRootView;
    private Slide mSlide;
    private TextView mSlideCaptionsTextView;
    private int mSlideCount;
    private TextView mSlideCounterTextView;
    /* access modifiers changed from: private */
    public ZoomableImageView mSlideImageView;
    /* access modifiers changed from: private */
    public int mSlideNumber;
    /* access modifiers changed from: private */
    public IViewPagerClickListener mViewPagerClickListener;
    private int slideImageHeightPadding = 100;

    public static ScreenSlidePageFragment newInstance(Slide slide, int i, int i2, boolean z, IViewPagerClickListener iViewPagerClickListener, IImageLoadedEvent iImageLoadedEvent) {
        ScreenSlidePageFragment screenSlidePageFragment = new ScreenSlidePageFragment();
        screenSlidePageFragment.mSlide = slide;
        screenSlidePageFragment.mSlideNumber = i;
        screenSlidePageFragment.mSlideCount = i2;
        screenSlidePageFragment.mViewPagerClickListener = iViewPagerClickListener;
        screenSlidePageFragment.mIsFullScreen = z;
        screenSlidePageFragment.mImageLoadedCallback = iImageLoadedEvent;
        return screenSlidePageFragment;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        ViewGroup viewGroup2 = (ViewGroup) layoutInflater.inflate(R.layout.fragment_screen_slide_page, viewGroup, false);
        this.mRootView = viewGroup2;
        viewGroup2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (ScreenSlidePageFragment.this.mViewPagerClickListener != null) {
                    ScreenSlidePageFragment.this.mViewPagerClickListener.onSlideClicked(ScreenSlidePageFragment.this.mSlideNumber);
                }
            }
        });
        this.mSlideCounterTextView = (TextView) this.mRootView.findViewById(R.id.slide_counter);
        this.mSlideImageView = (ZoomableImageView) this.mRootView.findViewById(R.id.slide_image_view);
        this.mSlideCaptionsTextView = (TextView) this.mRootView.findViewById(R.id.caption);
        this.mCaptionContainer = this.mRootView.findViewById(R.id.captionContainer);
        this.mProgressbar = (ProgressBar) this.mRootView.findViewById(R.id.progress_bar);
        ImageView imageView = (ImageView) this.mRootView.findViewById(R.id.expandCollapseTextToggle);
        this.mExpandCollapseTextToggleButton = imageView;
        imageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ScreenSlidePageFragment.this.onExpandCollapseTextToggle(view);
            }
        });
        setImageAspectRatio();
        Slide slide = this.mSlide;
        if (slide == null || StringUtil.isNullOrEmpty(slide.graphicUrl)) {
            return this.mRootView;
        }
        this.mSlideImageView.setVisibility(0);
        this.mImageTarget = new CustomViewTarget<ImageView, Bitmap>(this.mSlideImageView) {
            public void onLoadFailed(Drawable drawable) {
            }

            /* access modifiers changed from: protected */
            public void onResourceCleared(Drawable drawable) {
            }

            /* access modifiers changed from: protected */
            public void onResourceLoading(Drawable drawable) {
                super.onResourceLoading(drawable);
                ScreenSlidePageFragment.this.mProgressbar.setVisibility(0);
            }

            public void onResourceReady(Bitmap bitmap, Transition<? super Bitmap> transition) {
                ScreenSlidePageFragment.this.mImageLoadedCallback.onImageLoaded(ScreenSlidePageFragment.this.mSlideImageView, bitmap);
                ScreenSlidePageFragment.this.mProgressbar.setVisibility(8);
            }
        };
        GlideApp.with(getActivity()).asBitmap().load(this.mSlide.graphicUrl).into(this.mImageTarget);
        if (this.mSlideNumber == 0) {
            TextView textView = this.mSlideCounterTextView;
            textView.setText("1 of " + this.mSlideCount);
        } else {
            TextView textView2 = this.mSlideCounterTextView;
            textView2.setText((this.mSlideNumber + 1) + " of " + this.mSlideCount);
        }
        if (StringUtil.isNotEmpty(this.mSlide.caption)) {
            this.mSlideCaptionsTextView.setText(this.mSlide.caption);
            this.mSlideCaptionsTextView.setMovementMethod(new ScrollingMovementMethod());
            this.mCaptionContainer.setVisibility(0);
        } else {
            this.mCaptionContainer.setVisibility(8);
        }
        return this.mRootView;
    }

    public void setImageAspectRatio() {
        ViewGroup.LayoutParams layoutParams = this.mSlideImageView.getLayoutParams();
        ViewPager.LayoutParams layoutParams2 = (ViewPager.LayoutParams) ((RelativeLayout) this.mRootView.findViewById(R.id.content)).getLayoutParams();
        if (getResources().getConfiguration().orientation == 1 || Util.isPhone()) {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            int adjustedHeight = ExtensionsAspectRatio.getAdjustedHeight(4, 3, displayMetrics.heightPixels - this.slideImageHeightPadding);
            this.mSlideImageView.setMinimumWidth(displayMetrics.widthPixels);
            this.mSlideImageView.setMinimumHeight(adjustedHeight);
            layoutParams.width = displayMetrics.widthPixels;
            layoutParams.height = adjustedHeight;
            layoutParams2.width = displayMetrics.widthPixels;
            layoutParams2.height = adjustedHeight;
        } else if (!this.mIsFullScreen) {
            DisplayMetrics displayMetrics2 = new DisplayMetrics();
            getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics2);
            int adjustedHeight2 = ExtensionsAspectRatio.getAdjustedHeight(16, 9, displayMetrics2.widthPixels);
            this.mSlideImageView.setMinimumWidth(displayMetrics2.widthPixels);
            this.mSlideImageView.setMinimumHeight(adjustedHeight2);
            layoutParams.width = displayMetrics2.widthPixels;
            layoutParams.height = adjustedHeight2;
            layoutParams2.width = displayMetrics2.widthPixels;
            layoutParams2.height = adjustedHeight2;
        } else {
            DisplayMetrics displayMetrics3 = new DisplayMetrics();
            getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics3);
            int adjustedHeight3 = ExtensionsAspectRatio.getAdjustedHeight(3, 4, displayMetrics3.heightPixels);
            this.mSlideImageView.setMinimumWidth(adjustedHeight3);
            this.mSlideImageView.setMinimumHeight(displayMetrics3.heightPixels);
            layoutParams.width = adjustedHeight3;
            layoutParams.height = displayMetrics3.widthPixels;
            layoutParams2.width = adjustedHeight3;
            layoutParams2.height = displayMetrics3.heightPixels;
        }
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
}
