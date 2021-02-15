package com.webmd.wbmdcmepulse.activities;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.viewpager.widget.ViewPager;
import com.wbmd.wbmdcommons.utils.ChronicleIDUtil;
import com.webmd.wbmdcmepulse.R;
import com.webmd.wbmdcmepulse.adapters.ScreenSlidePagerAdapter;
import com.webmd.wbmdcmepulse.customviews.NoNetworkViewPager;
import com.webmd.wbmdcmepulse.models.articles.Slide;
import com.webmd.wbmdcmepulse.models.interfaces.IImageLoadedEvent;
import com.webmd.wbmdcmepulse.models.interfaces.IViewPagerClickListener;
import com.webmd.wbmdcmepulse.models.utils.Constants;
import com.webmd.wbmdcmepulse.models.utils.ExtensionsAspectRatio;
import com.webmd.wbmdcmepulse.models.utils.Utilities;
import com.webmd.wbmdcmepulse.omniture.OmnitureData;
import com.webmd.wbmdomnituremanager.WBMDOmnitureManager;
import com.webmd.wbmdomnituremanager.WBMDOmnitureModule;
import java.util.ArrayList;
import java.util.HashMap;

public class CmeImageViewerActivity extends CmeBaseActivity {
    private ScreenSlidePagerAdapter mAdapter;
    private String mArticleAssetId;
    /* access modifiers changed from: private */
    public String mArticleId;
    private Context mContext;
    /* access modifiers changed from: private */
    public int mCurrentPosition;
    private RelativeLayout mRootView;
    private ArrayList<Slide> mSlides;
    private ImageView mUpButtonImageView;
    /* access modifiers changed from: private */
    public NoNetworkViewPager mViewPager;

    /* access modifiers changed from: package-private */
    public void trackOmniturePageView() {
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.cme_activity_image_viewer);
        this.mContext = this;
        if (Utilities.isPhone()) {
            setRequestedOrientation(-1);
        }
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        Intent intent = getIntent();
        this.mRootView = (RelativeLayout) findViewById(R.id.root_view);
        this.mSlides = intent.getParcelableArrayListExtra("bundle_key_image_slides");
        this.mCurrentPosition = intent.getIntExtra("bundle_key_image_slide_position", 0);
        this.mArticleId = intent.getStringExtra(Constants.BUNDLE_KEY_ARTICLE_ID);
        this.mArticleAssetId = intent.getStringExtra(Constants.BUNDLE_KEY_ASSET_ID);
        ImageView imageView = (ImageView) findViewById(R.id.up_button);
        this.mUpButtonImageView = imageView;
        imageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CmeImageViewerActivity.this.finishWithResult();
            }
        });
        this.mUpButtonImageView.setImageDrawable(getResources().getDrawable(R.drawable.ic_up_white_24dp));
        WBMDOmnitureManager.sendPageView(String.format(OmnitureData.PAGE_NAME_ARTICLE_FULL_SCREEN_IMAGE, new Object[]{this.mArticleId, Integer.toString(this.mCurrentPosition + 1)}), generateChronicleID(String.format(OmnitureData.PAGE_NAME_ARTICLE_FULL_SCREEN_IMAGE, new Object[]{this.mArticleId, Integer.toString(this.mCurrentPosition + 1)})), (WBMDOmnitureModule) null);
        setUpImageFetcher();
        setUpImageViewPager();
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putParcelableArrayList("bundle_key_image_slides", this.mSlides);
        bundle.putInt("bundle_key_image_slide_position", this.mCurrentPosition);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        setUpImageFetcher();
        setUpImageViewPager();
    }

    public void onBackPressed() {
        finishWithResult();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_done, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        if (itemId != 16908332 && itemId != R.id.done) {
            return super.onOptionsItemSelected(menuItem);
        }
        finishWithResult();
        return true;
    }

    /* access modifiers changed from: private */
    public void finishWithResult() {
        Intent intent = getIntent();
        intent.putExtra("bundle_key_image_slide_position", this.mViewPager.getCurrentItem());
        setResult(-1, intent);
        finish();
    }

    private void setUpImageViewPager() {
        this.mAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager(), this.mSlides, this.mContext, this.mArticleId, true, new IViewPagerClickListener() {
            public void onSlideClicked(int i) {
                int unused = CmeImageViewerActivity.this.mCurrentPosition = i;
            }
        }, new IImageLoadedEvent() {
            public void loadImage(String str, ImageView imageView) {
            }

            public void loadImage(String str, ImageView imageView, int i, int i2) {
            }

            public void onImageLoaded(final ImageView imageView, final Bitmap bitmap) {
                CmeImageViewerActivity.this.mViewPager.post(new Runnable() {
                    public void run() {
                        imageView.post(new Runnable() {
                            public void run() {
                                imageView.setImageBitmap(bitmap);
                                CmeImageViewerActivity.this.mViewPager.post(new Runnable() {
                                    public void run() {
                                        CmeImageViewerActivity.this.mViewPager.requestLayout();
                                    }
                                });
                            }
                        });
                    }
                });
            }
        });
        NoNetworkViewPager noNetworkViewPager = (NoNetworkViewPager) findViewById(R.id.slides_pager);
        this.mViewPager = noNetworkViewPager;
        noNetworkViewPager.setAdapter(this.mAdapter);
        this.mViewPager.setCurrentItem(this.mCurrentPosition);
        this.mViewPager.setRootView(this.mRootView);
        this.mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageScrollStateChanged(int i) {
            }

            public void onPageScrolled(int i, float f, int i2) {
            }

            public void onPageSelected(int i) {
                if (CmeImageViewerActivity.this.mCurrentPosition != i) {
                    int unused = CmeImageViewerActivity.this.mCurrentPosition = i;
                    CmeImageViewerActivity cmeImageViewerActivity = CmeImageViewerActivity.this;
                    int i2 = i + 1;
                    WBMDOmnitureManager.sendPageView(String.format(OmnitureData.PAGE_NAME_ARTICLE_FULL_SCREEN_IMAGE, new Object[]{CmeImageViewerActivity.this.mArticleId, Integer.toString(i2)}), cmeImageViewerActivity.generateChronicleID(String.format(OmnitureData.PAGE_NAME_ARTICLE_FULL_SCREEN_IMAGE, new Object[]{cmeImageViewerActivity.mArticleId, Integer.toString(i2)})), (WBMDOmnitureModule) null);
                }
            }
        });
    }

    private void setUpImageFetcher() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int statusBarHeight = getStatusBarHeight();
        int i = displayMetrics.widthPixels;
        int i2 = displayMetrics.heightPixels - statusBarHeight;
        if (getResources().getConfiguration().orientation == 2) {
            ExtensionsAspectRatio.getAdjustedWidth(4, 3, i2);
        } else {
            ExtensionsAspectRatio.getAdjustedHeight(4, 3, i);
        }
    }

    private int getStatusBarHeight() {
        int identifier = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (identifier > 0) {
            return getResources().getDimensionPixelSize(identifier);
        }
        return 0;
    }

    public HashMap<String, String> generateChronicleID(String str) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("wapp.asset", new ChronicleIDUtil().generateAssetId(this.mArticleId, this.mArticleAssetId, str));
        return hashMap;
    }
}
