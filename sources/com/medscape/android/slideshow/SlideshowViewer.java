package com.medscape.android.slideshow;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.PopupMenu;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.viewpager.widget.ViewPager;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.google.android.gms.ads.formats.NativeContentAd;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.medscape.android.BI.BIManager;
import com.medscape.android.CP.CPEventAsyncTask;
import com.medscape.android.CP.CPGetLastVisitedPageAsyncTask;
import com.medscape.android.CP.WPPresenationAsyncTask;
import com.medscape.android.R;
import com.medscape.android.activity.rss.RSSExternalArticleActivity;
import com.medscape.android.ads.AdsSegvar;
import com.medscape.android.ads.AdsSegvarIntf;
import com.medscape.android.ads.CustomModalViewActivity;
import com.medscape.android.ads.DFPNewsAdListener;
import com.medscape.android.ads.OnAdListener;
import com.medscape.android.base.BaseActivity;
import com.medscape.android.helper.AsyncTaskHelper;
import com.medscape.android.parser.model.Slideshow;
import com.medscape.android.task.GetSlideshow;
import com.medscape.android.util.MedscapeException;
import com.medscape.android.util.Util;
import com.medscape.android.view.CustomWebView;
import com.medscape.android.view.DropDownListView;
import com.medscape.android.view.ViewCompat;
import com.medscape.android.view.ViewPagerWSwipeToggle;
import com.wbmd.wbmdcommons.utils.Extensions;
import com.webmd.wbmdcmepulse.models.CPEvent;
import com.webmd.wbmdcmepulse.models.utils.Constants;
import com.webmd.wbmdcmepulse.omniture.OmnitureData;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONException;
import org.json.JSONObject;

public class SlideshowViewer extends BaseActivity implements ViewPager.OnPageChangeListener, OnAdListener, DFPNewsAdListener, AdsSegvarIntf, GetSlideshow.GetSlideShowListener {
    private static final String COMPLETION_SENT = "completionSent";
    private static final String DID_RESUMEPAGE_SHOW = "didResumePageShow";
    private static final String DID_ROADBLOCK_SHOW = "didRoadblockShow";
    protected static final int DOWNLOAD_ERROR_MSG = 1;
    private static final String PARTICIPATION_SENT = "participationSent";
    private static final String PCLASS = "content";
    static final ExecutorService threadPoolExecutor = Executors.newSingleThreadExecutor();
    private String activityId = "";
    View bottomPane;
    private boolean completionSent;
    boolean didResumePageShow;
    boolean didRoadblockShow;
    private String encryptedParId = "";
    public Handler h = new Handler(new Handler.Callback() {
        public boolean handleMessage(Message message) {
            if (message.what == 1) {
                try {
                    SlideshowViewer.this.showDialog(5);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return true;
        }
    });
    public boolean isBrandPlay = false;
    private boolean isEditorial = false;
    SlideshowAudioHandler mAudioHandler;
    /* access modifiers changed from: private */
    public PopupWindow mChaptersPopupMenu;
    DemoCollectionPagerAdapter mDemoCollectionPagerAdapter;
    private boolean mHasShownHintToast = false;
    boolean mHaveChapters;
    boolean mHaveNotes;
    private View mHeader;
    private SequentialAnimationSet mHintBubblesAnimation = new SequentialAnimationSet();
    /* access modifiers changed from: private */
    public View mRootView;
    private int mSlideNumber = 1;
    /* access modifiers changed from: private */
    public Slideshow mSlideShow;
    /* access modifiers changed from: private */
    public String mSlideShowUrl;
    Toast mSwipeToast;
    ViewPagerWSwipeToggle mViewPager;
    public String orientationLock;
    private String parId = "";
    private boolean participationSent;
    private String pos = NativeContentAd.ASSET_LOGO;
    /* access modifiers changed from: private */
    public HashMap<String, String> screenSpecificMap = new HashMap<>();
    public String slideType;
    private String tacticId = "";
    View topPane;

    public void isAdExpandedByUser(boolean z) {
    }

    public void setScreenSpecificMap() {
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Util.setCookie(this);
        this.didRoadblockShow = bundle != null && bundle.getBoolean(DID_ROADBLOCK_SHOW);
        this.didResumePageShow = bundle != null && bundle.getBoolean(DID_RESUMEPAGE_SHOW);
        this.participationSent = bundle != null && bundle.getBoolean(PARTICIPATION_SENT);
        this.completionSent = bundle != null && bundle.getBoolean(COMPLETION_SENT);
        this.mSlideShow = bundle != null ? (Slideshow) bundle.getParcelable("slideShow") : null;
        setContentView((int) R.layout.slideshowviewer);
        super.setupAd();
        this.mHeader = findViewById(R.id.header);
        this.mRootView = findViewById(R.id.root_layout);
        this.mSlideShowUrl = (String) getIntent().getExtras().get("slideshowUrl");
        this.isEditorial = getIntent().getExtras().getBoolean("isEditorial");
        this.activityId = (String) getIntent().getExtras().get("activityId");
        this.tacticId = (String) getIntent().getExtras().get("tacticId");
        this.parId = (String) getIntent().getExtras().get("parId");
        this.encryptedParId = (String) getIntent().getExtras().get("encrypted_parId");
        this.isBrandPlay = getIntent().getExtras().getBoolean("isBrandPlay");
        this.orientationLock = getIntent().getExtras().getString("orientationLock");
        this.slideType = getIntent().getExtras().getString("slideType");
        String str = this.orientationLock;
        if (str == null) {
            setRequestedOrientation(-1);
        } else if (str.equalsIgnoreCase("portrait")) {
            setRequestedOrientation(1);
        } else if (this.orientationLock.equalsIgnoreCase("landscape")) {
            setRequestedOrientation(0);
        }
        GetSlideshow getSlideshow = new GetSlideshow(this, this, isDemoModeOn());
        if (this.mSlideShow == null) {
            AsyncTaskHelper.execute(threadPoolExecutor, getSlideshow, this.mSlideShowUrl);
        } else {
            setupSlideshow();
        }
        Log.e("TEST", "onCreateComplete");
    }

    public Dialog onCreateDialog(int i) {
        Dialog onCreateDialog = super.onCreateDialog(i);
        if (onCreateDialog != null) {
            return onCreateDialog;
        }
        if (i != 5) {
            return null;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(getResources().getString(R.string.alert_dialog_slideshow_error)).setCancelable(false).setIcon(17301543);
        builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
                SlideshowViewer.this.finish();
            }
        });
        builder.setPositiveButton(getString(R.string.retry), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                SlideshowViewer slideshowViewer = SlideshowViewer.this;
                GetSlideshow getSlideshow = new GetSlideshow(slideshowViewer, slideshowViewer, slideshowViewer.isDemoModeOn());
                if (SlideshowViewer.this.mSlideShow == null) {
                    AsyncTaskHelper.execute(SlideshowViewer.threadPoolExecutor, getSlideshow, SlideshowViewer.this.mSlideShowUrl);
                    return;
                }
                SlideshowViewer.this.setupSlideshow();
            }
        });
        return builder.create();
    }

    public void hideHeader() {
        View view = this.mHeader;
        if (view != null) {
            view.setVisibility(8);
        }
    }

    public void showHeader() {
        View view = this.mHeader;
        if (view != null) {
            view.setVisibility(0);
        }
    }

    public void enableSwipe() {
        ViewPagerWSwipeToggle viewPagerWSwipeToggle = this.mViewPager;
        if (viewPagerWSwipeToggle != null) {
            viewPagerWSwipeToggle.enableSwipe();
        }
    }

    public void disableSwipe() {
        ViewPagerWSwipeToggle viewPagerWSwipeToggle = this.mViewPager;
        if (viewPagerWSwipeToggle != null) {
            viewPagerWSwipeToggle.disableSwipe();
        }
    }

    /* access modifiers changed from: protected */
    public void setupActionBar() {
        getSupportActionBar().hide();
    }

    public void setupSlideshow() {
        if (shouldShowRoadblock()) {
            showRoadblock();
        } else {
            doResumeSlideShow();
        }
    }

    private void showHintToast() {
        Toast makeText = Toast.makeText(this, "Swipe to advance.", 0);
        this.mSwipeToast = makeText;
        TextView textView = (TextView) makeText.getView().findViewById(16908299);
        if (textView != null) {
            textView.setGravity(17);
            textView.setBackgroundResource(R.drawable.brandadvance_toast);
        }
        this.mSwipeToast.show();
    }

    public void pageFinished() {
        if (!this.mHintBubblesAnimation.empty()) {
            this.mHintBubblesAnimation.playSequence();
        }
        if (!this.mHasShownHintToast && !this.isBrandPlay) {
            this.mHasShownHintToast = true;
            showHintToast();
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        if (this.mAudioHandler != null && !this.mSlideShow.slides[getSlideNumber() - 1].audioUrl.isEmpty()) {
            this.mAudioHandler.resumeIfPaused();
        }
        DemoCollectionPagerAdapter demoCollectionPagerAdapter = this.mDemoCollectionPagerAdapter;
        if (demoCollectionPagerAdapter != null) {
            if (demoCollectionPagerAdapter.getCurrentFragment() != null) {
                ((SlideshowPageFragment) this.mDemoCollectionPagerAdapter.getCurrentFragment()).resumeWebview();
            } else if (this.isBrandPlay && this.mDemoCollectionPagerAdapter.getFragments().get(0) != null) {
                ((SlideshowPageFragment) this.mDemoCollectionPagerAdapter.getFragments().get(0)).resumeWebview();
            }
            this.mDemoCollectionPagerAdapter.notifyDataSetChanged();
        }
    }

    private void sendSlidePing() {
        String str;
        if (this.mSlideShow != null) {
            str = Util.MD5(this.mSlideShow.sfNumber + "_SLIDE" + this.mSlideNumber);
        } else {
            str = null;
        }
        if (str == null || !this.isBrandPlay) {
            sendBrandAdvanceBI("view", str);
        }
        if (this.slideType != null || !this.mSlideShow.isEditorial) {
            String str2 = this.slideType;
            if (str2 == null) {
                return;
            }
            if (str2.equalsIgnoreCase("editorial")) {
                sendEditorialSlideShowBI();
            } else if (this.slideType.equalsIgnoreCase(OmnitureData.LINK_NAME_VIDEO_PLAY) && this.mSlideShow != null) {
                sendBrandPlayBI("view", str);
            }
        } else {
            sendEditorialSlideShowBI();
        }
    }

    private void sendRoadBlockBiPing() {
        if (this.mSlideShow != null) {
            String MD5 = Util.MD5(this.mSlideShow.sfNumber + "_ROADBLOCK");
            if (this.isBrandPlay) {
                sendBrandPlayBI("view", MD5);
            } else {
                sendBrandAdvanceBI("view", MD5);
            }
        }
    }

    public void onBackPressed() {
        DemoCollectionPagerAdapter demoCollectionPagerAdapter = this.mDemoCollectionPagerAdapter;
        boolean z = true;
        if (!(demoCollectionPagerAdapter == null || this.mViewPager == null || demoCollectionPagerAdapter.getFragments() == null)) {
            for (Fragment next : this.mDemoCollectionPagerAdapter.getFragments().values()) {
                if (next != null && (next instanceof SlideshowPageFragment)) {
                    SlideshowPageFragment slideshowPageFragment = (SlideshowPageFragment) next;
                    if (!slideshowPageFragment.isSlideVisible()) {
                        z = false;
                        slideshowPageFragment.onBackPressed();
                    } else if (!(this.mDemoCollectionPagerAdapter == null || this.mViewPager == null)) {
                        slideshowPageFragment.stopWebview();
                    }
                }
            }
        }
        if (z) {
            if (this.mSlideShow != null) {
                this.mBIManager = new BIManager();
                if (this.isBrandPlay) {
                    sendBrandPlayBI("click_close", (String) null);
                } else {
                    sendBrandAdvanceBI("click_close", (String) null);
                }
            }
            super.onBackPressed();
        }
    }

    public void onPause() {
        super.onPause();
        SlideshowAudioHandler slideshowAudioHandler = this.mAudioHandler;
        if (slideshowAudioHandler != null) {
            if (slideshowAudioHandler.isPlaying) {
                this.mAudioHandler.isPausedByApp = true;
            }
            this.mAudioHandler.stopIfPlaying();
        }
        ((AudioManager) getSystemService("audio")).requestAudioFocus(new AudioManager.OnAudioFocusChangeListener() {
            public void onAudioFocusChange(int i) {
            }
        }, 3, 2);
        DemoCollectionPagerAdapter demoCollectionPagerAdapter = this.mDemoCollectionPagerAdapter;
        if (demoCollectionPagerAdapter == null) {
            return;
        }
        if (demoCollectionPagerAdapter.getCurrentFragment() != null) {
            ((SlideshowPageFragment) this.mDemoCollectionPagerAdapter.getCurrentFragment()).stopWebview();
        } else if (this.isBrandPlay && this.mDemoCollectionPagerAdapter.getFragments().get(0) != null) {
            ((SlideshowPageFragment) this.mDemoCollectionPagerAdapter.getFragments().get(0)).stopWebview();
        }
    }

    public void doResumeSlideShow() {
        if (canResumeSlideshow()) {
            AsyncTaskHelper.execute(threadPoolExecutor, new CPGetLastVisitedPageAsyncTask(this, this.mSlideShow.activityId, new CPGetLastVisitedPageAsyncTask.CPGetLastEventListener() {
                public void onCPLastEventRecieved(JSONObject jSONObject) {
                    if (jSONObject != null) {
                        try {
                            if (jSONObject.getJSONArray("activities").getJSONObject(0).optJSONObject(CPEvent.ACTIVITY_NAME_COMPLETION) == null) {
                                String string = jSONObject.getJSONArray("activities").getJSONObject(0).getJSONObject("scene").getString("name");
                                SlideshowViewer.this.handleResumeSlide(Integer.parseInt(string.substring(string.indexOf("scn") + 3)) - 1);
                                return;
                            }
                        } catch (JSONException unused) {
                        }
                    }
                    SlideshowViewer.this.showSlideshow(-1);
                }
            }), new Void[0]);
            return;
        }
        showSlideshow(-1);
    }

    public void showSlideshow(int i) {
        this.mDemoCollectionPagerAdapter = new DemoCollectionPagerAdapter(this, getSupportFragmentManager(), this.mSlideShow);
        ViewPagerWSwipeToggle viewPagerWSwipeToggle = (ViewPagerWSwipeToggle) findViewById(R.id.pager);
        this.mViewPager = viewPagerWSwipeToggle;
        viewPagerWSwipeToggle.setAdapter(this.mDemoCollectionPagerAdapter);
        this.mViewPager.setOnPageChangeListener(this);
        this.mAudioHandler = new SlideshowAudioHandler();
        if (i > -1) {
            this.mViewPager.setCurrentItem(i);
        }
        TextView textView = (TextView) findViewById(R.id.chapters_tip);
        if (this.mSlideShow.chapterTip.isEmpty()) {
            textView.setText(getString(R.string.default_slideshow_chapters_tip));
        } else {
            textView.setText(this.mSlideShow.chapterTip);
        }
        TextView textView2 = (TextView) findViewById(R.id.notes_tip);
        if (this.mSlideShow.slideTip.isEmpty()) {
            textView2.setText(getString(this.isBrandPlay ? R.string.default_slideshow_brandplay_notes_tip : R.string.default_slideshow_notes_tip));
        } else {
            textView2.setText(this.mSlideShow.slideTip);
        }
        this.mViewPager.setVisibility(0);
        if (this.mSlideShow.chapters != null && this.mSlideShow.chapters.length > 0) {
            initChapterPopup();
            findViewById(R.id.action_chapters).setVisibility(0);
            View findViewById = findViewById(R.id.callout_chapters);
            if (this.mSlideShow.chapterTipWidth > 0) {
                FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) findViewById.getLayoutParams();
                int pixelToDP = Util.pixelToDP(this, this.mSlideShow.chapterTipWidth);
                int i2 = layoutParams.rightMargin + (layoutParams.width / 2);
                int i3 = i2 - (pixelToDP / 2);
                if (i3 < 0) {
                    pixelToDP = i2 * 2;
                    i3 = 0;
                }
                layoutParams.width = pixelToDP;
                layoutParams.rightMargin = i3;
            }
            ToolTipDrawable toolTipDrawable = new ToolTipDrawable(this);
            toolTipDrawable.setCornerRadius(15.0f);
            toolTipDrawable.setPadding(10, 20, 10, 10);
            toolTipDrawable.setPaintBorderVisibility(true);
            ViewCompat.setBackground(findViewById, toolTipDrawable);
            this.mHintBubblesAnimation.add(fadein(findViewById));
            this.mHintBubblesAnimation.add(fadeout(findViewById));
        }
        if (this.mSlideShow.menuOptions != null && this.mSlideShow.menuOptions.length > 0) {
            findViewById(R.id.action_share).setVisibility(0);
        }
        ((TextView) findViewById(R.id.close)).setText("Close");
        setPageNumber(this.mViewPager.getCurrentItem() + 1);
        Slideshow slideshow = this.mSlideShow;
        if (slideshow != null && slideshow.slides != null && this.mSlideShow.slides.length > 0 && this.mViewPager.getCurrentItem() == 0 && !this.participationSent) {
            onPageSelected(0);
        }
    }

    private void setPageNumber(int i) {
        String str;
        TextView textView = (TextView) findViewById(R.id.count);
        if (this.isBrandPlay) {
            str = MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR;
        } else {
            str = i + " of " + this.mSlideShow.slides.length;
        }
        textView.setText(str);
    }

    public void handleResumeSlide(final int i) {
        if (i == 0) {
            showSlideshow(0);
            return;
        }
        CustomWebView customWebView = (CustomWebView) findViewById(R.id.resumeShowWebview);
        customWebView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                if (str == null) {
                    return false;
                }
                Uri parse = Uri.parse(str);
                if ("resume".equalsIgnoreCase(parse.getScheme())) {
                    destroy(webView);
                    SlideshowViewer.this.showSlideshow(i);
                    return true;
                } else if (!"restart".equalsIgnoreCase(parse.getScheme())) {
                    return false;
                } else {
                    destroy(webView);
                    SlideshowViewer.this.showSlideshow(0);
                    return true;
                }
            }

            /* access modifiers changed from: package-private */
            public void destroy(WebView webView) {
                ViewGroup viewGroup = (ViewGroup) webView.getParent();
                if (viewGroup != null) {
                    viewGroup.removeView(webView);
                }
                webView.destroy();
            }
        });
        customWebView.loadUrl(this.mSlideShow.resumeUrl);
        customWebView.setVisibility(0);
        ((TextView) findViewById(R.id.close)).setText("Cancel");
        this.didResumePageShow = true;
    }

    public void showRoadblock() {
        CustomWebView customWebView = (CustomWebView) findViewById(R.id.roadBlockWebview);
        customWebView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                Intent intent;
                if (str == null) {
                    return false;
                }
                if ("accept".equalsIgnoreCase(Uri.parse(str).getScheme())) {
                    SlideshowViewer.this.didRoadblockShow = true;
                    destroy(webView);
                    SlideshowViewer.this.doResumeSlideShow();
                    return true;
                }
                if ("portrait".equalsIgnoreCase(SlideshowViewer.this.orientationLock)) {
                    intent = new Intent(SlideshowViewer.this, LinkBrowserPortraitActivity.class);
                } else if ("landscape".equalsIgnoreCase(SlideshowViewer.this.orientationLock)) {
                    intent = new Intent(SlideshowViewer.this, LinkBrowserLandscapeActivity.class);
                } else {
                    intent = new Intent(SlideshowViewer.this, RSSExternalArticleActivity.class);
                }
                intent.putExtra("url", str);
                intent.putExtra("contentType", "promo");
                SlideshowViewer.this.startActivity(intent);
                return true;
            }

            /* access modifiers changed from: package-private */
            public void destroy(WebView webView) {
                ViewGroup viewGroup = (ViewGroup) webView.getParent();
                if (viewGroup != null) {
                    viewGroup.removeView(webView);
                }
                webView.destroy();
            }
        });
        customWebView.loadUrl(this.mSlideShow.roadblockUrl);
        customWebView.setVisibility(0);
        sendRoadBlockBiPing();
        ((TextView) findViewById(R.id.close)).setText("Cancel");
    }

    private boolean canResumeSlideshow() {
        return this.mSlideShow.resumeUrl != null && !this.mSlideShow.resumeUrl.isEmpty() && this.mSlideShow.activityId != null && !this.mSlideShow.activityId.isEmpty() && !this.didResumePageShow;
    }

    private boolean shouldShowRoadblock() {
        return this.mSlideShow.roadblockUrl != null && !this.mSlideShow.roadblockUrl.isEmpty() && !this.didRoadblockShow;
    }

    public void removeFragment(Fragment fragment) {
        this.mDemoCollectionPagerAdapter.removeFragment(fragment);
    }

    public class DemoCollectionPagerAdapter extends FragmentStatePagerAdapter {
        Fragment fragment;
        Context mContext;
        HashMap<Integer, Fragment> mFragments = new HashMap<>();
        Slideshow slideShow;

        DemoCollectionPagerAdapter(Context context, FragmentManager fragmentManager, Slideshow slideshow) {
            super(fragmentManager);
            this.slideShow = slideshow;
            this.mContext = context;
        }

        public HashMap<Integer, Fragment> getFragments() {
            return this.mFragments;
        }

        public Fragment getItem(int i) {
            SlideshowPageFragment newInstance = SlideshowPageFragment.newInstance(this.slideShow, i);
            this.mFragments.put(Integer.valueOf(i), newInstance);
            return newInstance;
        }

        /* access modifiers changed from: package-private */
        public Fragment getCurrentFragment() {
            return this.fragment;
        }

        /* access modifiers changed from: package-private */
        public void setCurrentFragment(Fragment fragment2) {
            this.fragment = fragment2;
        }

        /* access modifiers changed from: package-private */
        public void removeFragment(Fragment fragment2) {
            this.mFragments.remove(fragment2);
        }

        public int getCount() {
            return this.slideShow.slides.length;
        }

        public CharSequence getPageTitle(int i) {
            Log.e("TEST", "Position is " + i);
            return "" + (i + 1);
        }
    }

    /* access modifiers changed from: package-private */
    public void showFullScreenModal(String str, boolean z, boolean z2, boolean z3) {
        Intent intent = new Intent(this, CustomModalViewActivity.class);
        intent.setData(Uri.parse(str));
        intent.putExtra("navBar", z3);
        intent.putExtra("toolBar", false);
        intent.putExtra("headerText", MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
        intent.putExtra("headerColor", "DDDDDD");
        intent.putExtra("buttonText", "Cancel");
        intent.putExtra("isImmediate", true);
        intent.putExtra("isFullScreenAd", false);
        intent.putExtra("announceURL", str);
        intent.putExtra("adID", "");
        intent.putExtra("isNotesModal", z);
        intent.putExtra("isZoomModal", z2);
        intent.putExtra("slideNumber", this.mSlideNumber);
        intent.putExtra("sfNumber", this.mSlideShow.sfNumber);
        intent.putExtra("orientationLock", this.orientationLock);
        intent.putExtra("isBrandPlay", this.isBrandPlay);
        Util.isFullScreenAd = true;
        intent.addFlags(65536);
        startActivity(intent);
        if (Build.VERSION.SDK_INT >= 5) {
            overridePendingTransition(R.anim.slide_in_up, R.anim.ad_current_screen_anim);
        }
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle bundle) {
        bundle.putBoolean(DID_ROADBLOCK_SHOW, this.didRoadblockShow);
        bundle.putBoolean(DID_RESUMEPAGE_SHOW, this.didResumePageShow);
        bundle.putBoolean(PARTICIPATION_SENT, this.participationSent);
        bundle.putBoolean(COMPLETION_SENT, this.completionSent);
        bundle.putParcelable("slideShow", this.mSlideShow);
        super.onSaveInstanceState(bundle);
    }

    public void onCloseClicked(View view) {
        DemoCollectionPagerAdapter demoCollectionPagerAdapter = this.mDemoCollectionPagerAdapter;
        if (!(demoCollectionPagerAdapter == null || this.mViewPager == null || demoCollectionPagerAdapter.getFragments() == null)) {
            for (Fragment next : this.mDemoCollectionPagerAdapter.getFragments().values()) {
                if (!(next == null || !(next instanceof SlideshowPageFragment) || this.mDemoCollectionPagerAdapter == null || this.mViewPager == null)) {
                    ((SlideshowPageFragment) next).stopWebview();
                }
            }
        }
        if (this.mSlideShow != null) {
            this.mBIManager = new BIManager();
            if (this.isBrandPlay) {
                sendBrandPlayBI("click_close", (String) null);
            } else {
                sendBrandAdvanceBI("click_close", (String) null);
            }
        }
        finish();
    }

    public void onShareClicked(View view) {
        final PopupMenu popupMenu = new PopupMenu(this, view);
        Slideshow.MenuOptions[] menuOptionsArr = this.mSlideShow.menuOptions;
        int length = menuOptionsArr.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            popupMenu.getMenu().add(0, i2, 0, menuOptionsArr[i].name);
            i++;
            i2++;
        }
        if (this.isBrandPlay) {
            popupMenu.getMenu().add(0, i2, 0, getResources().getString(R.string.cancel));
        }
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem menuItem) {
                Intent intent;
                if (!(SlideshowViewer.this.mSlideShow == null || menuItem == null)) {
                    if (menuItem.getTitle() == null || !SlideshowViewer.this.getResources().getString(R.string.cancel).contentEquals(menuItem.getTitle())) {
                        String MD5 = Util.MD5(SlideshowViewer.this.mSlideShow.sfNumber + "_OPTION" + (menuItem.getItemId() + 1));
                        if (SlideshowViewer.this.isBrandPlay) {
                            SlideshowViewer.this.sendBrandPlayBI("click_action", MD5);
                        } else {
                            SlideshowViewer.this.sendBrandAdvanceBI("click_action", MD5);
                        }
                    } else {
                        popupMenu.dismiss();
                        return true;
                    }
                }
                if ("portrait".equalsIgnoreCase(SlideshowViewer.this.orientationLock)) {
                    intent = new Intent(SlideshowViewer.this, LinkBrowserPortraitActivity.class);
                } else if ("landscape".equalsIgnoreCase(SlideshowViewer.this.orientationLock)) {
                    intent = new Intent(SlideshowViewer.this, LinkBrowserLandscapeActivity.class);
                } else {
                    intent = new Intent(SlideshowViewer.this, RSSExternalArticleActivity.class);
                }
                intent.putExtra("overrideOrientation", true);
                intent.putExtra("url", SlideshowViewer.this.mSlideShow.menuOptions[menuItem.getItemId()].url);
                intent.putExtra("contentType", "promo");
                SlideshowViewer.this.startActivity(intent);
                return true;
            }
        });
        if (this.mSlideShow != null) {
            this.mBIManager = new BIManager();
            if (this.isBrandPlay) {
                sendBrandPlayBI("click_action_sheet", (String) null);
            } else {
                sendBrandAdvanceBI("click_action_sheet", (String) null);
            }
        }
        popupMenu.show();
    }

    public void onShowChaptersClicked(View view) {
        this.mChaptersPopupMenu.setWidth((int) Util.dpToPixel(this, ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION));
        if (this.mChaptersPopupMenu.isShowing()) {
            this.mChaptersPopupMenu.dismiss();
        } else {
            this.mChaptersPopupMenu.showAsDropDown(view, -((int) Util.dpToPixel(this, 152)), 0);
        }
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        PopupWindow popupWindow;
        View findViewById = findViewById(R.id.action_chapters);
        if (motionEvent.getAction() == 0 && (popupWindow = this.mChaptersPopupMenu) != null && popupWindow.isShowing() && findViewById.getVisibility() == 0) {
            int[] iArr = new int[2];
            int[] iArr2 = new int[2];
            this.mChaptersPopupMenu.getContentView().getLocationOnScreen(iArr);
            findViewById.getLocationOnScreen(iArr2);
            Rect rect = new Rect(iArr[0], iArr[1], iArr[0] + this.mChaptersPopupMenu.getContentView().getWidth(), iArr[1] + this.mChaptersPopupMenu.getContentView().getHeight());
            Rect rect2 = new Rect(iArr2[0], iArr2[1], iArr2[0] + findViewById.getWidth(), iArr2[1] + findViewById.getHeight());
            if (!rect.contains((int) motionEvent.getRawX(), (int) motionEvent.getRawY()) && !rect2.contains((int) motionEvent.getRawX(), (int) motionEvent.getRawY())) {
                this.mChaptersPopupMenu.dismiss();
            }
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    public int getSlideNumber() {
        return this.mSlideNumber;
    }

    public void onPageSelected(int i) {
        DemoCollectionPagerAdapter demoCollectionPagerAdapter = this.mDemoCollectionPagerAdapter;
        if (demoCollectionPagerAdapter != null) {
            if (demoCollectionPagerAdapter.getCurrentFragment() != null) {
                ((SlideshowPageFragment) this.mDemoCollectionPagerAdapter.getCurrentFragment()).stopWebview();
            }
            DemoCollectionPagerAdapter demoCollectionPagerAdapter2 = this.mDemoCollectionPagerAdapter;
            demoCollectionPagerAdapter2.setCurrentFragment(demoCollectionPagerAdapter2.getFragments().get(Integer.valueOf(i)));
        }
        this.mSlideNumber = i + 1;
        sendSlidePing();
        setPageNumber(this.mSlideNumber);
        sendCPPings(i);
        setAudio(i);
        setNotes(i);
        getAd();
    }

    private void sendCPPings(int i) {
        if (!this.isBrandPlay && !this.mSlideShow.isEditorial && this.mSlideShow.slides[i].topPane != null && this.mSlideShow.slides[i].topPane.url != null && this.mSlideShow.slides[i].topScale > FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
            ExecutorService executorService = threadPoolExecutor;
            String str = this.mSlideShow.activityId;
            AsyncTaskHelper.execute(executorService, new CPEventAsyncTask(this, "ippcontent", str, "scn" + this.mSlideNumber, this.mSlideShow.parId, this.mSlideShow.tacticId, this.mSlideShow.slideShowUrl), new Void[0]);
        }
        if (!this.isBrandPlay && !this.mSlideShow.isEditorial && this.mSlideNumber == 1 && !this.participationSent) {
            AsyncTaskHelper.execute(threadPoolExecutor, new CPEventAsyncTask(this, "ippevent", this.mSlideShow.activityId, CPEvent.ACTIVITY_NAME_PARTICIPATION, this.mSlideShow.parId, this.mSlideShow.tacticId, this.mSlideShow.slideShowUrl), new Void[0]);
            AsyncTaskHelper.execute(threadPoolExecutor, new WPPresenationAsyncTask(this, this.mSlideShow.activityId, "startPresentation", this.mSlideShow.encryptedParId), new Void[0]);
            this.participationSent = true;
        }
        if (!this.isBrandPlay && !this.mSlideShow.isEditorial && this.mSlideNumber == this.mSlideShow.slides.length && !this.completionSent) {
            AsyncTaskHelper.execute(threadPoolExecutor, new CPEventAsyncTask(this, "ippevent", this.mSlideShow.activityId, CPEvent.ACTIVITY_NAME_COMPLETION, this.mSlideShow.parId, this.mSlideShow.tacticId, this.mSlideShow.slideShowUrl), new Void[0]);
            AsyncTaskHelper.execute(threadPoolExecutor, new WPPresenationAsyncTask(this, this.mSlideShow.activityId, "endActivity", this.mSlideShow.encryptedParId), new Void[0]);
            this.completionSent = true;
        }
    }

    private void setAudio(int i) {
        View findViewById = findViewById(R.id.action_audio);
        if (!this.mSlideShow.slides[i].audioUrl.isEmpty()) {
            findViewById.setOnClickListener(this.mAudioHandler);
            findViewById.setBackgroundResource(R.drawable.ic_action_play);
            findViewById.setVisibility(0);
            this.mAudioHandler.setAudioUrl(this.mSlideShow.slides[i].audioUrl);
            return;
        }
        findViewById.setVisibility(8);
    }

    private void setNotes(int i) {
        View findViewById = findViewById(R.id.action_notes);
        View findViewById2 = findViewById(R.id.callout_notes);
        if (this.mSlideShow.slides[i].notesUrl == null || this.mSlideShow.slides[i].notesUrl.isEmpty()) {
            findViewById2.setVisibility(8);
            findViewById2.clearAnimation();
            findViewById.setVisibility(8);
            return;
        }
        int i2 = 0;
        findViewById.setOnClickListener(new ModalViewOpener(this.mSlideShow.slides[i].notesUrl, true, false));
        findViewById.setVisibility(0);
        if (this.mSlideShow.slideTipWidth > 0) {
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) findViewById2.getLayoutParams();
            int pixelToDP = Util.pixelToDP(this, this.mSlideShow.slideTipWidth);
            int i3 = layoutParams.rightMargin + (layoutParams.width / 2);
            int i4 = i3 - (pixelToDP / 2);
            if (i4 < 0) {
                pixelToDP = i3 * 2;
            } else {
                i2 = i4;
            }
            layoutParams.width = pixelToDP;
            layoutParams.rightMargin = i2;
        }
        ToolTipDrawable toolTipDrawable = new ToolTipDrawable(this);
        toolTipDrawable.setCornerRadius(15.0f);
        toolTipDrawable.setPadding(10, 20, 10, 10);
        toolTipDrawable.setPaintBorderVisibility(true);
        ViewCompat.setBackground(findViewById2, toolTipDrawable);
        this.mHintBubblesAnimation.add(fadein(findViewById2));
        this.mHintBubblesAnimation.add(fadeout(findViewById2));
    }

    private AbstractMap.SimpleEntry<Animation, View> fadein(View view) {
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation.setDuration(200);
        view.setVisibility(4);
        return new AbstractMap.SimpleEntry<>(alphaAnimation, view);
    }

    private AbstractMap.SimpleEntry<Animation, View> fadeout(View view) {
        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
        alphaAnimation.setStartOffset(2000);
        alphaAnimation.setDuration(500);
        return new AbstractMap.SimpleEntry<>(alphaAnimation, view);
    }

    public void onPageScrolled(int i, float f, int i2) {
        this.mAudioHandler.stopIfPlaying();
        this.mAudioHandler.removeMediaPlayer();
        findViewById(R.id.action_audio).setBackgroundResource(R.drawable.ic_action_play);
        Toast toast = this.mSwipeToast;
        if (toast != null) {
            toast.cancel();
        }
        DemoCollectionPagerAdapter demoCollectionPagerAdapter = this.mDemoCollectionPagerAdapter;
        if (demoCollectionPagerAdapter == null) {
            return;
        }
        if (demoCollectionPagerAdapter.getCurrentFragment() != null) {
            ((SlideshowPageFragment) this.mDemoCollectionPagerAdapter.getCurrentFragment()).resumeWebview();
        } else if (this.isBrandPlay && this.mDemoCollectionPagerAdapter.getFragments().get(0) != null) {
            ((SlideshowPageFragment) this.mDemoCollectionPagerAdapter.getFragments().get(0)).resumeWebview();
        }
    }

    public void onPageScrollStateChanged(int i) {
        if (i == 1) {
            this.mHintBubblesAnimation.clearAnimation();
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        SlideshowAudioHandler slideshowAudioHandler = this.mAudioHandler;
        if (slideshowAudioHandler != null) {
            slideshowAudioHandler.removeMediaPlayer();
        }
    }

    class ModalViewOpener implements View.OnClickListener {
        static final String zoomSuffix = "?zoom";
        boolean isNotes;
        boolean isZoom;
        String mUrl;

        ModalViewOpener(String str, boolean z, boolean z2) {
            this.mUrl = z2 ? str.concat(zoomSuffix) : str;
            this.isNotes = z;
            this.isZoom = z2;
        }

        public void onClick(View view) {
            SlideshowViewer.this.showFullScreenModal(this.mUrl, this.isNotes, this.isZoom, false);
        }
    }

    /* access modifiers changed from: private */
    public void sendBrandAdvanceBI(String str, String str2) {
        String str3;
        if (!this.mSlideShow.isEditorial && this.mSlideShow != null) {
            this.mBIManager = new BIManager();
            if (str2 != null) {
                str3 = "www.medscape.com/brandadvance/" + this.mSlideShow.sfNumber + "/" + str2;
            } else {
                str3 = "(null)";
            }
            this.mBIManager.startBI(this, str, "brandadvance_" + this.mSlideShow.sfNumber, str3);
        }
    }

    /* access modifiers changed from: private */
    public void sendBrandPlayBI(String str, String str2) {
        String str3;
        if (!this.mSlideShow.isEditorial && this.mSlideShow != null) {
            this.mBIManager = new BIManager();
            if (str2 != null) {
                str3 = "www.medscape.com/brandplay/" + this.mSlideShow.sfNumber + "/" + str2;
            } else {
                str3 = "(null)";
            }
            this.mBIManager.startBI(this, str, "brandplay_" + this.mSlideShow.sfNumber, str3);
        }
    }

    private void sendEditorialSlideShowBI() {
        if (this.mSlideShow != null) {
            this.mBIManager = new BIManager();
            String str = this.mSlideShow.slideShowUrl;
            if (str != null && str.startsWith("http") && str.indexOf("//") > 0) {
                str = str.substring(str.indexOf("//") + 2);
            }
            if (str != null && str.endsWith("responsetype=manifest")) {
                str = str.substring(0, str.indexOf("responsetype=manifest") - 1);
            }
            this.mBIManager.startBI(this, "view", Constants.SECTION_TYPE_SLIDE, str + "_" + this.mSlideNumber);
        }
    }

    public void setScreenSpecificMap(String str) {
        this.screenSpecificMap.put("pos", getResources().getString(R.string.banner_ad_pos));
        this.screenSpecificMap.put("pc", "content");
        String[] screenSpecificDataFromJSON = AdsSegvar.getInstance().getScreenSpecificDataFromJSON(str);
        for (int i = 0; i < screenSpecificDataFromJSON.length; i++) {
            if (screenSpecificDataFromJSON.length >= i) {
                String str2 = screenSpecificDataFromJSON[i];
                if (str2.contains("=")) {
                    String[] split = str2.split("=");
                    if (split.length == 2) {
                        this.screenSpecificMap.put(split[0], split[1]);
                    }
                }
            }
        }
    }

    public void onAdAvailable() {
        this.adView.setVisibility(0);
    }

    public void onAdNotAvilable() {
        this.adLayout.setVisibility(8);
    }

    public void getAd() {
        if (this.mSlideShow == null) {
            return;
        }
        if (Util.isOnline(this) && Util.getDisplayOrientation(this) == 0 && !this.isPause && !this.screenSpecificMap.isEmpty() && (this.mSlideShow.showAds || this.mSlideShow.isEditorial)) {
            this.adAction.setOnUpdateListener(this);
            this.adAction.makeADRequestWithoutBidding(this.screenSpecificMap);
        } else if (Util.isOnline(this) && Util.getDisplayOrientation(this) == 0 && !this.isPause && this.screenSpecificMap.isEmpty()) {
            if (this.mSlideShow.showAds || this.mSlideShow.isEditorial) {
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        if (!SlideshowViewer.this.screenSpecificMap.isEmpty()) {
                            SlideshowViewer.this.adAction.setOnUpdateListener(SlideshowViewer.this);
                            SlideshowViewer.this.adAction.makeADRequestWithoutBidding(SlideshowViewer.this.screenSpecificMap);
                        }
                    }
                }, 2000);
            }
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        if (configuration.orientation == 2) {
            onAdNotAvilable();
        } else {
            getAd();
        }
        super.onConfigurationChanged(configuration);
    }

    public void onSlideshowDownloaded(Slideshow slideshow) {
        String str;
        String str2;
        String str3;
        String str4;
        if (slideshow == null || slideshow.slides == null || slideshow.slides.length <= 0) {
            onSlideshowDownloadError();
            return;
        }
        this.mSlideShow = slideshow;
        String str5 = this.mSlideShowUrl;
        if (str5 != null && str5.endsWith("responsetype=manifest")) {
            String str6 = this.mSlideShowUrl;
            this.mSlideShowUrl = str6.substring(0, str6.indexOf("responsetype=manifest") - 1);
        }
        this.mSlideShow.slideShowUrl = this.mSlideShowUrl;
        if ((this.mSlideShow.activityId == null || this.mSlideShow.activityId.isEmpty()) && (str4 = this.parId) != null && !str4.isEmpty()) {
            this.mSlideShow.activityId = this.activityId;
        }
        if ((this.mSlideShow.tacticId == null || this.mSlideShow.tacticId.isEmpty()) && (str3 = this.parId) != null && !str3.isEmpty()) {
            this.mSlideShow.tacticId = this.tacticId;
        }
        if ((this.mSlideShow.parId == null || this.mSlideShow.parId.isEmpty()) && (str2 = this.parId) != null && !str2.isEmpty()) {
            this.mSlideShow.parId = this.parId;
        }
        if ((this.mSlideShow.encryptedParId == null || this.mSlideShow.encryptedParId.isEmpty()) && (str = this.encryptedParId) != null && !str.isEmpty()) {
            this.mSlideShow.encryptedParId = this.encryptedParId;
        }
        if (!this.mSlideShow.isEditorial) {
            this.mSlideShow.isEditorial = this.isEditorial;
        }
        setupSlideshow();
    }

    public void onSlideshowDownloadError() {
        this.h.sendEmptyMessage(1);
    }

    private void initChapterPopup() {
        FrameLayout frameLayout = new FrameLayout(this);
        final DropDownListView dropDownListView = new DropDownListView(this);
        dropDownListView.setBackgroundResource(R.drawable.drop_shadow_four);
        dropDownListView.setDivider(getResources().getDrawable(R.drawable.color_c4c4c4));
        dropDownListView.setDividerHeight(1);
        dropDownListView.setAdapter(new ArrayAdapter<Slideshow.Chapters>(this, 0, this.mSlideShow.chapters) {
            public View getView(int i, View view, ViewGroup viewGroup) {
                if (view == null) {
                    view = View.inflate(SlideshowViewer.this, R.layout.chapters_menu_item, (ViewGroup) null);
                }
                ((TextView) view.findViewById(R.id.title)).setText(SlideshowViewer.this.mSlideShow.chapters[i].name);
                return view;
            }
        });
        dropDownListView.setVerticalFadingEdgeEnabled(true);
        dropDownListView.setFocusable(true);
        dropDownListView.setFocusableInTouchMode(true);
        dropDownListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                if (Util.isOnline(SlideshowViewer.this.getApplicationContext())) {
                    String str = SlideshowViewer.this.mSlideShow.chapters[i].url;
                    if (!Extensions.IsNullOrEmpty(str)) {
                        ((SlideshowPageFragment) SlideshowViewer.this.mDemoCollectionPagerAdapter.getFragments().get(0)).loadChapter(str);
                    } else {
                        ((SlideshowPageFragment) SlideshowViewer.this.mDemoCollectionPagerAdapter.getFragments().get(0)).gotoAnchorInTopPane("" + (i + 1));
                    }
                    SlideshowViewer.this.mChaptersPopupMenu.dismiss();
                    return;
                }
                new MedscapeException(SlideshowViewer.this.getResources().getString(R.string.internet_required)).showSnackBar(SlideshowViewer.this.mRootView, -1);
            }
        });
        dropDownListView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                DropDownListView dropDownListView;
                if (Util.isOnline(SlideshowViewer.this.getApplicationContext()) && i != -1 && (dropDownListView = dropDownListView) != null) {
                    dropDownListView.setListSelectionHidden(false);
                }
            }
        });
        dropDownListView.post(new Runnable() {
            public void run() {
                SlideshowViewer.this.mChaptersPopupMenu.setWindowLayoutMode((int) Util.dpToPixel(SlideshowViewer.this, 200), -2);
            }
        });
        frameLayout.addView(dropDownListView);
        this.mChaptersPopupMenu = new PopupWindow(frameLayout);
    }
}
