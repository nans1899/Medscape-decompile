package com.medscape.android.reference;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.PopupMenu;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.core.net.MailTo;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.google.android.gms.ads.AdSize;
import com.ib.clickstream.ClickstreamConstants;
import com.ib.clickstream.Impression;
import com.medscape.android.BI.omniture.OmnitureManager;
import com.medscape.android.CP.FireCPEventWithAdsSegvarAsyncTask;
import com.medscape.android.Constants;
import com.medscape.android.R;
import com.medscape.android.Settings;
import com.medscape.android.activity.AbstractContentViewerActvity;
import com.medscape.android.activity.search.RecentlyViewedSuggestionHelper;
import com.medscape.android.activity.webviews.WebviewUtil;
import com.medscape.android.ads.ADBindingHelper;
import com.medscape.android.ads.AdsConstants;
import com.medscape.android.ads.AdsSegvar;
import com.medscape.android.ads.AdsSegvarIntf;
import com.medscape.android.ads.DFPAdAction;
import com.medscape.android.ads.DFPReferenceAdListener;
import com.medscape.android.ads.INativeDFPAdLoadListener;
import com.medscape.android.ads.NativeAdAction;
import com.medscape.android.ads.NativeDFPAD;
import com.medscape.android.ads.bidding.AdBidder;
import com.medscape.android.analytics.ClickStreamManager;
import com.medscape.android.appboy.AppboyEventsHandler;
import com.medscape.android.auth.AuthenticationManager;
import com.medscape.android.capabilities.CapabilitiesManager;
import com.medscape.android.contentviewer.ClinicalReferenceArticleFragment;
import com.medscape.android.contentviewer.ContentUtils;
import com.medscape.android.contentviewer.CrossLink;
import com.medscape.android.contentviewer.ImageViewerActivity;
import com.medscape.android.contentviewer.LineItem;
import com.medscape.android.contentviewer.NextSectionLineItem;
import com.medscape.android.contentviewer.model.Slide;
import com.medscape.android.contentviewer.view_holders.DataViewHolder;
import com.medscape.android.custom.CustomMenu;
import com.medscape.android.drugs.DrugMonographMainActivity;
import com.medscape.android.helper.AsyncTaskHelper;
import com.medscape.android.interfaces.INightModeListener;
import com.medscape.android.parser.model.Article;
import com.medscape.android.player.VideoPlayer;
import com.medscape.android.provider.SharedPreferenceProvider;
import com.medscape.android.reference.ClinicalReferenceArticleActivity;
import com.medscape.android.reference.exception.ContentNotFoundException;
import com.medscape.android.reference.exception.ContentNotLoadedException;
import com.medscape.android.reference.interfaces.ContentLoaderCallback;
import com.medscape.android.reference.interfaces.CrossLinkListener;
import com.medscape.android.reference.model.ClinicalReferenceArticle;
import com.medscape.android.reference.model.ClinicalReferenceContent;
import com.medscape.android.reference.model.Figure;
import com.medscape.android.reference.model.ReferenceItem;
import com.medscape.android.reference.model.Sect1;
import com.medscape.android.reference.model.Sect2;
import com.medscape.android.reference.task.ParseClinicalReferenceArticleXMLTask;
import com.medscape.android.reference.view.CalloutPopover;
import com.medscape.android.reference.view.ClinicalReferenceMenuAdapter;
import com.medscape.android.reference.view.ContributorsDataAdapter;
import com.medscape.android.reference.view.LocatableLinkMovementMethod;
import com.medscape.android.reference.view.ReferencesDataAdapter;
import com.medscape.android.reference.viewmodels.ClinicalReferenceArticleViewModel;
import com.medscape.android.updater.EnvConstants;
import com.medscape.android.util.MedscapeException;
import com.medscape.android.util.OldConstants;
import com.medscape.android.util.RedirectHandler;
import com.medscape.android.util.StringUtil;
import com.medscape.android.util.Util;
import com.medscape.android.util.constants.AppboyConstants;
import com.medscape.android.welcome.WelcomeActivity;
import com.tapstream.sdk.http.RequestBuilders;
import com.wbmd.qxcalculator.model.contentItems.common.Category;
import com.wbmd.wbmdcommons.logging.Trace;
import com.wbmd.wbmdcommons.utils.ChronicleIDUtil;
import com.webmd.wbmdproffesionalauthentication.providers.AccountProvider;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ClinicalReferenceArticleActivity extends AbstractContentViewerActvity implements EnvConstants, AdsSegvarIntf, ContentLoaderCallback, CrossLinkListener, INightModeListener {
    private static final String BUNDLE_KEY_CRARTICLE = "crArticle";
    public static final String CONSULT_HINT_SHOWN = "flag_consult_hint_shown";
    private static final int DEFAULT_SECTION_INDEX = 0;
    protected static final int GET_NEXT_AD = 102;
    protected static final int HIDE_PROGRESS_BAR = 1;
    protected static final int OPEN_MENU = 290;
    private static final int START_TIMER = 101;
    private static final String TAG = "ClinicalReferenceArticleActivity";
    private static final ExecutorService threadPoolExecutor = Executors.newSingleThreadExecutor();
    AdBidder adBidder = new AdBidder();
    private HashMap<String, String> adSegVars;
    private String assetId;
    protected long autohide;
    private Map<String, String> bannerScreenSpecificMap = new HashMap();
    ClinicalReferenceArticle crArticle;
    ListView dropDownList;
    private EditText findEditText;
    private boolean fromLandingPage = false;
    public Handler h = new Handler(new Handler.Callback() {
        public boolean handleMessage(Message message) {
            int i = message.what;
            if (i == 1) {
                ClinicalReferenceArticleActivity.this.progressBar.setVisibility(8);
            } else if (i == ClinicalReferenceArticleActivity.OPEN_MENU) {
                ClinicalReferenceArticleActivity.this.openOptionsMenu();
            } else if (i == 51) {
                ClinicalReferenceArticleActivity.this.mHandler.postDelayed(ClinicalReferenceArticleActivity.this.mAutohideTimer, ClinicalReferenceArticleActivity.this.autohide * 1000);
            } else if (i == 52) {
                ClinicalReferenceArticleActivity.this.onAdNotAvilable();
            } else if (i == 101) {
                ClinicalReferenceArticleActivity.this.mHandler.postDelayed(ClinicalReferenceArticleActivity.this.mTimer, ClinicalReferenceArticleActivity.this.rotate * 1000);
            } else if (i == 102) {
                ClinicalReferenceArticleActivity.this.requestBottomBannerAD();
            }
            return true;
        }
    });
    private Map<String, String> inlineScreenSpecificMap = new HashMap();
    private volatile boolean isExpandedByUser = false;
    private boolean isFirstTime;
    private boolean isLoadfromTop;
    private Article mArticleForEmail;
    Runnable mAutohideTimer = new Runnable() {
        public final void run() {
            ClinicalReferenceArticleActivity.this.lambda$new$14$ClinicalReferenceArticleActivity();
        }
    };
    ClinicalReferenceArticleContentDataAdapter mClinicalReferenceContentDataAdapter;
    ClinicalReferenceContentManager mClinicalReferenceContentManager;
    ClinicalReferenceMenuAdapter mClinicalReferenceMenuAdapter;
    PopupWindow mClinicalReferenceNavigationMenu;
    CalloutPopover mConsultHintBubble;
    FrameLayout mContainer;
    /* access modifiers changed from: private */
    public ClinicalReferenceArticleFragment mContentSectionFragment;
    private int mCurrentFindPosition = 0;
    private int mCurrentSectionIndex;
    /* access modifiers changed from: private */
    public TextView mFindCounter;
    private String mFindQuery = "";
    /* access modifiers changed from: private */
    public Handler mHandler = new Handler();
    private View mHeaderLayout;
    CalloutPopover mInlineReferencePopover;
    private boolean mIsFindMode;
    private int mNumberOfAds = 0;
    public HashMap<String, Object> mOmnitureContentData;
    /* access modifiers changed from: private */
    public View mRoot;
    private String mSectionId;
    private String mSelectedSectionId;
    Runnable mTimer = new Runnable() {
        public final void run() {
            ClinicalReferenceArticleActivity.this.lambda$new$15$ClinicalReferenceArticleActivity();
        }
    };
    String mTitle = null;
    private int mTotalFinds = -1;
    private String omnitureData = "";
    private boolean onCreateCompleted = false;
    private String pageNumber = null;
    private String pclass = "content";
    /* access modifiers changed from: private */
    public ProgressBar progressBar;
    protected long rotate;
    private HashMap<String, String> screenSpecificMap = new HashMap<>();
    private int selectedSection;
    private ClinicalReferenceArticleViewModel viewModel;

    static /* synthetic */ boolean lambda$onCreateDialog$17(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
        return i == 84 || i == 82;
    }

    static /* synthetic */ boolean lambda$onCreateDialog$19(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
        return i == 84 || i == 82;
    }

    static /* synthetic */ void lambda$showContributorsFragment$13(int i) {
    }

    static /* synthetic */ void lambda$showReferencesFragment$12(int i) {
    }

    private void setUpListeners() {
    }

    /* access modifiers changed from: protected */
    public String getContentTeaserForEmail() {
        return "";
    }

    /* access modifiers changed from: protected */
    public int getMenuItemsLayout() {
        return R.menu.content_share;
    }

    /* access modifiers changed from: protected */
    public boolean isContentTitleDisplayed() {
        return true;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.layout_clinref_content_viewer);
        setupNativeBottomBannerAd(DFPReferenceAdListener.AD_UNIT_ID);
        Bundle redirectBundle = RedirectHandler.getRedirectBundle(getIntent());
        if (redirectBundle != null && StringUtil.isNotEmpty(redirectBundle.getString(Constants.EXTRA_REDIRECT))) {
            OmnitureManager.get().markModule(true, "push", Constants.OMNITURE_MLINK_OPEN, (Map<String, Object>) null);
            if (isLoggedOut() || isDataUpdateRequired()) {
                startActivity(new Intent(this, WelcomeActivity.class).setFlags(67108864).putExtra(Constants.EXTRA_REDIRECT, redirectBundle));
                finish();
            }
        }
        Util.setCookie(this);
        this.mContainer = (FrameLayout) findViewById(R.id.container);
        this.mRoot = findViewById(R.id.root);
        this.mInlineReferencePopover = new CalloutPopover(this, findViewById(R.id.dimmmer), this.mRoot);
        ProgressBar progressBar2 = (ProgressBar) findViewById(R.id.progress);
        this.progressBar = progressBar2;
        boolean z = false;
        progressBar2.setVisibility(0);
        this.mClinicalReferenceContentManager = new ClinicalReferenceContentManager(this);
        this.pclass = getIntent().getBooleanExtra(Constants.EXTRA_PUSH_LAUNCH, false) ? "alertviewer" : "content";
        this.viewModel = (ClinicalReferenceArticleViewModel) ViewModelProviders.of((FragmentActivity) this).get(ClinicalReferenceArticleViewModel.class);
        if (getIntent().hasExtra("fromLandingPage")) {
            z = getIntent().getBooleanExtra("fromLandingPage", false);
        }
        this.fromLandingPage = z;
        loadClinicalReferenceData();
    }

    private void loadClinicalReferenceData() {
        String str;
        ClinicalReferenceArticle clinicalReferenceArticle;
        try {
            if (this.fromLandingPage) {
                this.crArticle = (ClinicalReferenceArticle) getIntent().getSerializableExtra("article");
                this.selectedSection = getIntent().getIntExtra("section", 0);
                str = getIntent().getStringExtra("uri");
                this.pageNumber = getIntent().getStringExtra("pageNumber");
            } else {
                String dataString = getIntent().getDataString();
                this.pageNumber = Util.getPageNumberFromUrl(dataString);
                str = Util.getUrlWithoutPageNumber(dataString);
                if (str == null) {
                    clinicalReferenceArticle = (ClinicalReferenceArticle) getIntent().getSerializableExtra("article");
                } else {
                    clinicalReferenceArticle = this.mClinicalReferenceContentManager.fetchArticleWithId(parseArticleIdFromUrl(str));
                }
                this.crArticle = clinicalReferenceArticle;
            }
            this.mSelectedSectionId = parseArticleSectionIdFromUrl(str);
            if (this.crArticle != null) {
                setScreenSpecificMap();
                this.mArticleForEmail = new Article();
                Util.sendFirebaseContentInfo(this, "ckb", String.valueOf(this.crArticle.getArticleId()));
                setTitle();
                setupHeader();
                setupDropdown();
                initializeView(this.crArticle);
                setUpListeners();
                if (this.fromLandingPage) {
                    handleContentDownloaded(this.crArticle.getLocalXMLPath());
                } else {
                    this.mClinicalReferenceContentManager.fetchArticleContent(this.crArticle, this);
                }
                saveToRecentlyViewed();
                this.onCreateCompleted = true;
                return;
            }
            handleContentNotAvailable(str, true);
        } catch (ContentNotLoadedException unused) {
            handleContentNotAvailable((String) null, true);
        } catch (Exception e) {
            handleContentLoadingError(e.getMessage());
        }
    }

    private void setTitle() {
        ClinicalReferenceArticle clinicalReferenceArticle = this.crArticle;
        if (clinicalReferenceArticle == null || clinicalReferenceArticle.getTitle().equals("")) {
            this.mTitle = getResources().getString(R.string.clinical_procedure_header_text_view);
        } else {
            this.mTitle = this.crArticle.getTitle();
        }
        if (getSupportActionBar() != null) {
            ActionBar supportActionBar = getSupportActionBar();
            supportActionBar.setTitle((CharSequence) Html.fromHtml("<font color=#ffffff>" + this.mTitle + "</font>"));
        }
    }

    private void setupHeader() {
        ViewGroup viewGroup;
        Toolbar actionBarToolBar = getActionBarToolBar();
        View view = this.mHeaderLayout;
        if (!(view == null || (viewGroup = (ViewGroup) view.getParent()) == null)) {
            viewGroup.removeView(this.mHeaderLayout);
        }
        this.mHeaderLayout = LayoutInflater.from(this).inflate(this.mIsFindMode ? R.layout.find_actionbar : R.layout.clinical_reference_header, actionBarToolBar, false);
        actionBarToolBar.addView(this.mHeaderLayout, new ActionBar.LayoutParams(-1, -2));
        setActionBarBackButtonVisibility(!this.mIsFindMode);
        if (!this.mIsFindMode) {
            ((TextView) this.mHeaderLayout.findViewById(R.id.articleTitle)).setText(this.mTitle);
            return;
        }
        EditText editText = (EditText) this.mHeaderLayout.findViewById(R.id.find_query);
        this.findEditText = editText;
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public final boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                return ClinicalReferenceArticleActivity.this.lambda$setupHeader$1$ClinicalReferenceArticleActivity(textView, i, keyEvent);
            }
        });
        this.findEditText.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable editable) {
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                if (charSequence.length() == 0) {
                    ClinicalReferenceArticleActivity.this.mClinicalReferenceContentDataAdapter.setFindQuery("");
                    ClinicalReferenceArticleActivity.this.mFindCounter.setText("");
                    ClinicalReferenceArticleActivity.this.mContentSectionFragment.scrollToPosition(0);
                }
            }
        });
        Util.showKeyboard(this);
        this.findEditText.requestFocus();
        TextView textView = (TextView) findViewById(R.id.find_counter);
        this.mFindCounter = textView;
        textView.setText("");
        this.mHeaderLayout.findViewById(R.id.next_find).setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                ClinicalReferenceArticleActivity.this.lambda$setupHeader$2$ClinicalReferenceArticleActivity(view);
            }
        });
        this.mHeaderLayout.findViewById(R.id.prev_find).setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                ClinicalReferenceArticleActivity.this.lambda$setupHeader$3$ClinicalReferenceArticleActivity(view);
            }
        });
        this.mHeaderLayout.findViewById(R.id.close_find).setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                ClinicalReferenceArticleActivity.this.lambda$setupHeader$4$ClinicalReferenceArticleActivity(view);
            }
        });
    }

    public /* synthetic */ boolean lambda$setupHeader$1$ClinicalReferenceArticleActivity(TextView textView, int i, KeyEvent keyEvent) {
        if (i != 3) {
            return false;
        }
        this.mFindQuery = textView.getText().toString();
        OmnitureManager.get().trackModule(this, Constants.OMNITURE_CHANNEL_REFERENCE, "find-on-page", "ref", (Map<String, Object>) null);
        Util.hideKeyboard(this);
        this.mClinicalReferenceContentDataAdapter.setFindQuery(this.mFindQuery);
        this.mHeaderLayout.postDelayed(new Runnable() {
            public final void run() {
                ClinicalReferenceArticleActivity.this.lambda$null$0$ClinicalReferenceArticleActivity();
            }
        }, 200);
        return true;
    }

    public /* synthetic */ void lambda$null$0$ClinicalReferenceArticleActivity() {
        ClinicalReferenceArticleContentDataAdapter clinicalReferenceArticleContentDataAdapter;
        if (this.mContentSectionFragment != null && (clinicalReferenceArticleContentDataAdapter = this.mClinicalReferenceContentDataAdapter) != null && clinicalReferenceArticleContentDataAdapter.mCurrentFindItem != null) {
            this.mContentSectionFragment.scrollToPosition(this.mClinicalReferenceContentDataAdapter.mCurrentFindItem.contentRow);
        }
    }

    public /* synthetic */ void lambda$setupHeader$2$ClinicalReferenceArticleActivity(View view) {
        ClinicalReferenceArticleContentDataAdapter clinicalReferenceArticleContentDataAdapter = this.mClinicalReferenceContentDataAdapter;
        if (clinicalReferenceArticleContentDataAdapter != null && clinicalReferenceArticleContentDataAdapter.mFindPositions != null) {
            if (this.mTotalFinds < 1) {
                this.mTotalFinds = this.mClinicalReferenceContentDataAdapter.mFindPositions.size();
            }
            int i = this.mCurrentFindPosition;
            if (i < this.mTotalFinds - 1) {
                this.mCurrentFindPosition = i + 1;
                ClinicalReferenceArticleFragment clinicalReferenceArticleFragment = this.mContentSectionFragment;
                if (clinicalReferenceArticleFragment != null) {
                    clinicalReferenceArticleFragment.moveCurrentFind(true);
                }
            }
        }
    }

    public /* synthetic */ void lambda$setupHeader$3$ClinicalReferenceArticleActivity(View view) {
        int i = this.mCurrentFindPosition;
        if (i > 0) {
            this.mCurrentFindPosition = i - 1;
            this.mContentSectionFragment.moveCurrentFind(false);
        }
    }

    public /* synthetic */ void lambda$setupHeader$4$ClinicalReferenceArticleActivity(View view) {
        clearFindMatches();
    }

    private void setupDropdown() {
        FrameLayout frameLayout = new FrameLayout(this);
        this.dropDownList = new ListView(this);
        SharedPreferenceProvider sharedPreferenceProvider = SharedPreferenceProvider.get();
        boolean z = false;
        if (sharedPreferenceProvider.get(Constants.PREF_REFERENCE_NIGHT_MODE + AuthenticationManager.getInstance(this).getMaskedGuid(), 0) == 1) {
            z = true;
        }
        setDropDownMenuNightMode(z);
        ClinicalReferenceMenuAdapter clinicalReferenceMenuAdapter = new ClinicalReferenceMenuAdapter(this, new ArrayList(), z);
        this.mClinicalReferenceMenuAdapter = clinicalReferenceMenuAdapter;
        this.dropDownList.setAdapter(clinicalReferenceMenuAdapter);
        this.dropDownList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public final void onItemClick(AdapterView adapterView, View view, int i, long j) {
                ClinicalReferenceArticleActivity.this.lambda$setupDropdown$5$ClinicalReferenceArticleActivity(adapterView, view, i, j);
            }
        });
        frameLayout.addView(this.dropDownList);
        PopupWindow popupWindow = new PopupWindow(frameLayout);
        this.mClinicalReferenceNavigationMenu = popupWindow;
        popupWindow.setFocusable(true);
    }

    public /* synthetic */ void lambda$setupDropdown$5$ClinicalReferenceArticleActivity(AdapterView adapterView, View view, int i, long j) {
        this.mSectionId = generateSectionID(this.crArticle.getContent(), i);
        this.isLoadfromTop = false;
        browseToSection(i, false);
        if (this.mClinicalReferenceNavigationMenu.isShowing()) {
            this.mClinicalReferenceNavigationMenu.dismiss();
        }
    }

    private void initializeView(ClinicalReferenceArticle clinicalReferenceArticle) {
        new Bundle().putSerializable(BUNDLE_KEY_CRARTICLE, clinicalReferenceArticle);
        ClinicalReferenceArticleContentDataAdapter clinicalReferenceArticleContentDataAdapter = new ClinicalReferenceArticleContentDataAdapter(this, this.fromLandingPage);
        this.mClinicalReferenceContentDataAdapter = clinicalReferenceArticleContentDataAdapter;
        clinicalReferenceArticleContentDataAdapter.addDataListClickListener(new DataViewHolder.DataListClickListener() {
            public final void onDataListItemClicked(int i) {
                ClinicalReferenceArticleActivity.this.lambda$initializeView$6$ClinicalReferenceArticleActivity(i);
            }
        });
        this.isFirstTime = true;
    }

    public /* synthetic */ void lambda$initializeView$6$ClinicalReferenceArticleActivity(int i) {
        LineItem lineItem = this.mClinicalReferenceContentDataAdapter.getItems().get(i);
        if (lineItem.isContributor) {
            showContributorsFragment();
            return;
        }
        if (lineItem.crossLink != null) {
            onLinkClicked((TextView) null, lineItem.crossLink);
        }
        if (lineItem instanceof NextSectionLineItem) {
            int i2 = this.mCurrentSectionIndex + 1;
            this.mCurrentSectionIndex = i2;
            browseToSection(i2, true);
        }
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            PopupWindow popupWindow = this.mClinicalReferenceNavigationMenu;
            if (popupWindow != null && popupWindow.isShowing()) {
                int[] iArr = new int[2];
                this.mClinicalReferenceNavigationMenu.getContentView().getLocationOnScreen(iArr);
                Rect rect = new Rect(iArr[0], iArr[1], iArr[0] + this.mClinicalReferenceNavigationMenu.getContentView().getWidth(), iArr[1] + this.mClinicalReferenceNavigationMenu.getContentView().getHeight());
                this.mHeaderLayout.getLocationOnScreen(iArr);
                Rect rect2 = new Rect(iArr[0], iArr[1], iArr[0] + this.mHeaderLayout.getWidth(), iArr[1] + this.mHeaderLayout.getHeight());
                if (!rect.contains((int) motionEvent.getRawX(), (int) motionEvent.getRawY()) && !rect2.contains((int) motionEvent.getRawX(), (int) motionEvent.getRawY())) {
                    this.mClinicalReferenceNavigationMenu.dismiss();
                }
            }
            CalloutPopover calloutPopover = this.mInlineReferencePopover;
            if (calloutPopover != null) {
                calloutPopover.handleTouchEvent(motionEvent);
            }
            if (this.mConsultHintBubble != null) {
                int[] iArr2 = new int[2];
                View view = this.mHeaderLayout;
                if (view != null) {
                    view.getLocationOnScreen(iArr2);
                    if (((float) new Rect(iArr2[0], iArr2[1], iArr2[0] + this.mHeaderLayout.getWidth(), iArr2[1] + this.mHeaderLayout.getHeight()).bottom) < motionEvent.getRawY()) {
                        Settings.singleton(this).saveSetting(CONSULT_HINT_SHOWN, "YES");
                        this.mConsultHintBubble.handleTouchEvent(motionEvent);
                    }
                }
            }
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    public void onHeaderClicked(View view) {
        if (this.mHeaderLayout.findViewById(R.id.selectedSection).isEnabled()) {
            toggleNavigationMenu(view);
            ClinicalReferenceArticleFragment clinicalReferenceArticleFragment = this.mContentSectionFragment;
            if (clinicalReferenceArticleFragment != null) {
                clinicalReferenceArticleFragment.handleTextOptions("close");
            }
        }
    }

    public void toggleNavigationMenu(View view) {
        if (this.mClinicalReferenceNavigationMenu.isShowing()) {
            this.mClinicalReferenceNavigationMenu.dismiss();
            return;
        }
        this.mClinicalReferenceNavigationMenu.setHeight(-2);
        this.mClinicalReferenceNavigationMenu.setWidth((int) Util.dpToPixel(this, ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION));
        this.mClinicalReferenceNavigationMenu.setBackgroundDrawable(new ColorDrawable(0));
        this.mClinicalReferenceNavigationMenu.showAsDropDown(view, (int) Util.dpToPixel(this, 0), 0);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        menu.clear();
        getMenuInflater().inflate(getCurrentMenuLayout(), menu);
        return true;
    }

    public boolean onMenuOpened(int i, Menu menu) {
        ClinicalReferenceArticleFragment clinicalReferenceArticleFragment = this.mContentSectionFragment;
        if (clinicalReferenceArticleFragment == null || menu == null) {
            return false;
        }
        clinicalReferenceArticleFragment.handleTextOptions(Constants.OMNITURE_MLINK_OPEN);
        return false;
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        setMenuMoreOptionVisibility(menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != R.id.action_find) {
            return super.onOptionsItemSelected(menuItem);
        }
        this.mIsFindMode = true;
        invalidateOptionsMenu();
        setActionBarBackButtonVisibility(false);
        setupHeader();
        OmnitureManager.get().trackModule(this, Constants.OMNITURE_CHANNEL_REFERENCE, "find-on-page", "ref", (Map<String, Object>) null);
        return true;
    }

    public void browseToSection(int i, boolean z) {
        this.mNumberOfAds = 0;
        if (this.mIsFindMode) {
            clearFindMatches();
        }
        try {
            setNextSectionName(i);
        } catch (ContentNotFoundException e) {
            e.printStackTrace();
        }
        if (CapabilitiesManager.getInstance(this).isSharethroughFeatureAvailable()) {
            loadSharethroughAd(i);
        } else {
            updateAdapter(i, (NativeDFPAD) null, false);
        }
        try {
            if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
                getSupportFragmentManager().popBackStack();
            }
            FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
            if (!this.isFirstTime) {
                if (this.isLoadfromTop) {
                    beginTransaction.setCustomAnimations(R.anim.slide_in_from_top, R.anim.slide_out_to_bottom);
                } else {
                    beginTransaction.setCustomAnimations(R.anim.slide_in_from_bottom, R.anim.slide_out_to_top);
                }
            }
            if (!this.isFirstTime) {
                String str = Category.K_MENU_CATEGORY;
                if (z) {
                    str = "pull";
                }
                OmnitureManager.get().markModule(NotificationCompat.CATEGORY_NAVIGATION, str, (Map<String, Object>) null);
            } else {
                this.isFirstTime = false;
            }
            ClinicalReferenceArticleFragment newInstance = ClinicalReferenceArticleFragment.newInstance(new Bundle());
            this.mContentSectionFragment = newInstance;
            newInstance.setAdapter(this.mClinicalReferenceContentDataAdapter);
            this.mContentSectionFragment.setNightModeListener(this);
            beginTransaction.replace(R.id.container, this.mContentSectionFragment).addToBackStack((String) null).commit();
        } catch (IllegalStateException e2) {
            Trace.e(TAG, e2.getMessage());
        }
        setSelectionInHeader(i);
        this.mCurrentSectionIndex = i;
        requestBottomBannerAD();
    }

    /* access modifiers changed from: private */
    public void updateAdapter(int i, NativeDFPAD nativeDFPAD, boolean z) {
        this.mClinicalReferenceContentDataAdapter.setTopAdView(nativeDFPAD, z);
        this.mClinicalReferenceContentDataAdapter.setCurrentItem(i);
        this.mClinicalReferenceContentDataAdapter.notifyDataSetChanged();
    }

    private void loadSharethroughAd(final int i) {
        if (Util.isOnline(this)) {
            NativeAdAction nativeAdAction = new NativeAdAction(this, DFPReferenceAdListener.AD_UNIT_ID, (View) null);
            nativeAdAction.prepADWithCombinedRequests(new INativeDFPAdLoadListener() {
                public void onAdLoaded(NativeDFPAD nativeDFPAD) {
                    ClinicalReferenceArticleActivity.this.updateAdapter(i, nativeDFPAD, false);
                }

                public void onAdFailedToLoad(int i) {
                    ClinicalReferenceArticleActivity.this.updateAdapter(i, (NativeDFPAD) null, false);
                }
            }, new AdSize[]{DFPAdAction.ADSIZE_320x80, DFPAdAction.ADSIZE_320x95, DFPAdAction.ADSIZE_1x3});
            nativeAdAction.isSharethrough = true;
            getAd(nativeAdAction);
            updateAdapter(i, (NativeDFPAD) null, true);
            return;
        }
        updateAdapter(i, (NativeDFPAD) null, false);
    }

    public void browseToSection(String str) {
        this.mNumberOfAds = 0;
        browseToSection(this.crArticle.getContent().getSectionIndexById(str), false);
    }

    public void setSelectionInHeader(int i) {
        try {
            if (this.crArticle != null && !this.crArticle.isSinglePageArticle()) {
                if (this.crArticle.getContent() != null) {
                    ((TextView) this.mHeaderLayout.findViewById(R.id.selectedSection)).setText(this.crArticle.getContent().getSectionTitle(i));
                }
                this.mHeaderLayout.findViewById(R.id.sectionTitleLayout).setVisibility(0);
                this.mHeaderLayout.findViewById(R.id.selectedSection).setEnabled(true);
                return;
            }
        } catch (ContentNotFoundException unused) {
            this.mHeaderLayout.findViewById(R.id.sectionTitleLayout).setVisibility(8);
            this.mHeaderLayout.findViewById(R.id.selectedSection).setEnabled(true);
        }
        this.mHeaderLayout.findViewById(R.id.selectedSection).setEnabled(false);
    }

    public void showStaticHeader(String str) {
        View view = this.mHeaderLayout;
        if (view != null) {
            view.findViewById(R.id.dropdownHeader).setVisibility(8);
            this.mHeaderLayout.findViewById(R.id.staticSectionHeader).setVisibility(0);
            ((TextView) this.mHeaderLayout.findViewById(R.id.staticSectionTitle)).setText(str);
        }
    }

    public void hideStaticHeader() {
        View view = this.mHeaderLayout;
        if (view != null) {
            view.findViewById(R.id.dropdownHeader).setVisibility(0);
            this.mHeaderLayout.findViewById(R.id.staticSectionHeader).setVisibility(8);
        }
    }

    public void handleUpAction() {
        dismissConsultHintBubble();
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            getSupportFragmentManager().popBackStack();
            getSupportFragmentManager().executePendingTransactions();
            hideStaticHeader();
            return;
        }
        finish();
        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
    }

    public void onBackPressed() {
        handleUpAction();
    }

    private void saveToRecentlyViewed() {
        Bundle bundle = new Bundle(2);
        bundle.putString("type", "T");
        bundle.putSerializable(RecentlyViewedSuggestionHelper.META_CLINICAL_REF_ARTICLE, this.crArticle);
        RecentlyViewedSuggestionHelper.addToRecentlyViewed(this, this.mTitle, bundle);
    }

    public Article getArticleForEmail() {
        this.mArticleForEmail.mTitle = this.crArticle.getTitle();
        Article article = this.mArticleForEmail;
        article.mLink = OldConstants.CR_WEB_URL + this.crArticle.getArticleId() + "-overview" + com.wbmd.wbmdcommons.utils.Util.attachSrcTagToUrl(OldConstants.CR_WEB_URL) + "&ref=email";
        return this.mArticleForEmail;
    }

    public void handleContentDownloaded(String str) {
        this.crArticle.setLocalXmlFilePath(str);
        this.mClinicalReferenceContentDataAdapter.setData(this.crArticle.getContent());
        this.mClinicalReferenceContentDataAdapter.notifyDataSetChanged();
        new ParseClinicalReferenceArticleXMLTask(this, this).execute(new String[]{this.crArticle.getLocalXMLPath()});
    }

    public void handleContentloaded(ClinicalReferenceContent clinicalReferenceContent) {
        if (this.assetId == null && clinicalReferenceContent.adSupportMap.containsKey("article-id")) {
            this.assetId = clinicalReferenceContent.adSupportMap.get("article-id");
        }
        this.mRoot.setVisibility(0);
        this.h.sendEmptyMessage(1);
        this.crArticle.setContent(clinicalReferenceContent);
        this.mClinicalReferenceContentDataAdapter.setData(this.crArticle.getContent());
        try {
            setNextSectionName(this.crArticle.getContent().getSectionIndexById(this.mSelectedSectionId));
        } catch (ContentNotFoundException e) {
            e.printStackTrace();
        }
        this.mClinicalReferenceMenuAdapter.setData(clinicalReferenceContent);
        if (this.fromLandingPage) {
            this.mSectionId = generateSectionID(clinicalReferenceContent, this.selectedSection);
        } else {
            this.mSectionId = generateSectionID(clinicalReferenceContent, 0);
        }
        sendCPPing();
        try {
            if (StringUtil.isNotEmpty(this.pageNumber)) {
                int parseInt = Integer.parseInt(this.pageNumber);
                if (this.crArticle.getContent() == null || parseInt < 0 || parseInt > this.crArticle.getContent().sections.size()) {
                    browseToSection(this.mSelectedSectionId);
                } else {
                    browseToSection(parseInt, false);
                }
            } else if (this.fromLandingPage) {
                browseToSection(this.selectedSection, false);
            } else {
                browseToSection(this.mSelectedSectionId);
            }
        } catch (Exception e2) {
            browseToSection(this.mSelectedSectionId);
            e2.printStackTrace();
        }
    }

    public void handleContentNotDownloaded() {
        this.h.sendEmptyMessage(1);
        final String string = getResources().getString(R.string.retry);
        final MedscapeException medscapeException = new MedscapeException(getResources().getString(R.string.alert_dialog_clinical_article_network_connection_error_message));
        new Handler().postDelayed(new Runnable() {
            public /* synthetic */ void lambda$run$0$ClinicalReferenceArticleActivity$3(View view) {
                ClinicalReferenceArticleActivity.this.mClinicalReferenceContentManager.fetchArticleContent(ClinicalReferenceArticleActivity.this.crArticle, ClinicalReferenceArticleActivity.this);
            }

            public void run() {
                medscapeException.showSnackBar(ClinicalReferenceArticleActivity.this.mRoot, -2, string, new View.OnClickListener() {
                    public final void onClick(View view) {
                        ClinicalReferenceArticleActivity.AnonymousClass3.this.lambda$run$0$ClinicalReferenceArticleActivity$3(view);
                    }
                });
            }
        }, 200);
    }

    public void handleNoInternetForMediaGallery() {
        this.h.sendEmptyMessage(1);
        new MedscapeException(getResources().getString(R.string.internet_required)).showSnackBar(this.mRoot, -1, getResources().getString(R.string.retry), new View.OnClickListener() {
            public final void onClick(View view) {
                ClinicalReferenceArticleActivity.this.lambda$handleNoInternetForMediaGallery$7$ClinicalReferenceArticleActivity(view);
            }
        });
    }

    public /* synthetic */ void lambda$handleNoInternetForMediaGallery$7$ClinicalReferenceArticleActivity(View view) {
        showMediaGallery();
    }

    public void handleContentNotAvailable(String str, boolean z) {
        this.mRoot.setVisibility(8);
        this.h.sendEmptyMessage(1);
        MedscapeException medscapeException = new MedscapeException(getResources().getString(R.string.alert_dialog_content_unavailable_message));
        String string = getResources().getString(R.string.alert_dialog_open_in_browser);
        String string2 = getResources().getString(R.string.alert_dialog_confirmation_button_text_close);
        if (z) {
            medscapeException.showAlert(this, string, new DialogInterface.OnClickListener(str) {
                public final /* synthetic */ String f$1;

                {
                    this.f$1 = r2;
                }

                public final void onClick(DialogInterface dialogInterface, int i) {
                    ClinicalReferenceArticleActivity.this.lambda$handleContentNotAvailable$8$ClinicalReferenceArticleActivity(this.f$1, dialogInterface, i);
                }
            }, string2, new DialogInterface.OnClickListener() {
                public final void onClick(DialogInterface dialogInterface, int i) {
                    ClinicalReferenceArticleActivity.this.lambda$handleContentNotAvailable$9$ClinicalReferenceArticleActivity(dialogInterface, i);
                }
            });
            return;
        }
        medscapeException.showSnackBar(this.mRoot, -2, getResources().getString(R.string.alert_dialog_open_in_browser), new View.OnClickListener(str) {
            public final /* synthetic */ String f$1;

            {
                this.f$1 = r2;
            }

            public final void onClick(View view) {
                ClinicalReferenceArticleActivity.this.lambda$handleContentNotAvailable$10$ClinicalReferenceArticleActivity(this.f$1, view);
            }
        });
    }

    public /* synthetic */ void lambda$handleContentNotAvailable$8$ClinicalReferenceArticleActivity(String str, DialogInterface dialogInterface, int i) {
        String str2 = "http://emedicine.medscape.com";
        if (StringUtil.isNotEmpty(parseArticleIdFromUrl(str))) {
            str2 = str2 + "/article/" + StringUtil.optString(parseArticleIdFromUrl(str));
        }
        Util.openInExternalBrowser(this, str2);
        finish();
    }

    public /* synthetic */ void lambda$handleContentNotAvailable$9$ClinicalReferenceArticleActivity(DialogInterface dialogInterface, int i) {
        finish();
    }

    public /* synthetic */ void lambda$handleContentNotAvailable$10$ClinicalReferenceArticleActivity(String str, View view) {
        Util.openInExternalBrowser(this, "http://emedicine.medscape.com/article/" + StringUtil.optString(parseArticleIdFromUrl(str)));
    }

    public void handleContentLoadingError(String str) {
        if (this.crArticle != null) {
            handleContentNotAvailable("" + this.crArticle.getArticleId(), true);
            return;
        }
        this.h.sendEmptyMessage(1);
        new MedscapeException(str).showSnackBar(this.mRoot, -2, getResources().getString(R.string.alert_dialog_confirmation_positive_button_text_OK), new View.OnClickListener() {
            public final void onClick(View view) {
                ClinicalReferenceArticleActivity.this.lambda$handleContentLoadingError$11$ClinicalReferenceArticleActivity(view);
            }
        });
    }

    public /* synthetic */ void lambda$handleContentLoadingError$11$ClinicalReferenceArticleActivity(View view) {
        onBackPressed();
    }

    public void handleFeatureDisabled(String str) {
        this.h.sendEmptyMessage(1);
        getResources().getString(R.string.retry);
        new MedscapeException(str + " not implemented").showSnackBar(this.mRoot, -1, (String) null, (View.OnClickListener) null);
    }

    private void showReferencesFragment() {
        if (this.mIsFindMode) {
            clearFindMatches();
        }
        SharedPreferenceProvider sharedPreferenceProvider = SharedPreferenceProvider.get();
        boolean z = false;
        if (sharedPreferenceProvider.get(Constants.PREF_REFERENCE_NIGHT_MODE + AuthenticationManager.getInstance(this).getMaskedGuid(), 0) == 1) {
            z = true;
        }
        ClinicalReferenceArticleFragment newInstance = ClinicalReferenceArticleFragment.newInstance(new Bundle());
        ReferencesDataAdapter referencesDataAdapter = new ReferencesDataAdapter(this, this.crArticle.getContent().references);
        referencesDataAdapter.setNightMode(z);
        referencesDataAdapter.addDataListClickListener($$Lambda$ClinicalReferenceArticleActivity$otaloQaZyRmxQogjYqV74c0tP8A.INSTANCE);
        newInstance.setAdapter(referencesDataAdapter);
        newInstance.setSectionNameForOmniture(this.viewModel.buildSubSectionNameForPing(this.crArticle, "references"));
        getSupportFragmentManager().beginTransaction().replace(R.id.container, newInstance).addToBackStack((String) null).commit();
        showStaticHeader(getString(R.string.clinical_reference_viewer_references));
    }

    private void showContributorsFragment() {
        if (this.mIsFindMode) {
            clearFindMatches();
        }
        SharedPreferenceProvider sharedPreferenceProvider = SharedPreferenceProvider.get();
        boolean z = false;
        int i = sharedPreferenceProvider.get(Constants.PREF_REFERENCE_NIGHT_MODE + AuthenticationManager.getInstance(this).getMaskedGuid(), 0);
        ClinicalReferenceArticleFragment newInstance = ClinicalReferenceArticleFragment.newInstance(new Bundle());
        ContributorsDataAdapter contributorsDataAdapter = new ContributorsDataAdapter(this.crArticle.getContent().contributors);
        if (i == 1) {
            z = true;
        }
        contributorsDataAdapter.setNightMode(z);
        contributorsDataAdapter.addDataListClickListener($$Lambda$ClinicalReferenceArticleActivity$RREiPAGpZJcqt3SRWczJHKfdFCM.INSTANCE);
        newInstance.setAdapter(contributorsDataAdapter);
        newInstance.setSectionNameForOmniture(this.viewModel.buildSubSectionNameForPing(this.crArticle, "contributor-information"));
        try {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, newInstance).addToBackStack((String) null).commit();
            showStaticHeader(getString(R.string.clinical_reference_viewer_contributors));
        } catch (Throwable unused) {
        }
    }

    private void showMediaGallery() {
        if (this.mIsFindMode) {
            clearFindMatches();
        }
        if (!Util.isOnline(this)) {
            handleNoInternetForMediaGallery();
            return;
        }
        ClinicalReferenceArticle clinicalReferenceArticle = this.crArticle;
        if (clinicalReferenceArticle != null && clinicalReferenceArticle.getContent() != null && this.crArticle.getContent().hasMedia(true)) {
            ArrayList arrayList = new ArrayList();
            for (Map.Entry<String, Figure> value : this.crArticle.getContent().mediaGalleryMap.entrySet()) {
                Figure figure = (Figure) value.getValue();
                if (figure.isImage() && StringUtil.isNotEmpty(figure.graphic.href)) {
                    Slide slide = new Slide();
                    slide.graphicUrl = figure.graphic.href;
                    if (figure.caption != null) {
                        slide.caption = figure.caption.toString();
                    }
                    arrayList.add(slide);
                }
            }
            if (!arrayList.isEmpty()) {
                OmnitureManager.get().markModule("media", Constants.OMNITURE_MLINK_OPEN, (Map<String, Object>) null);
                Intent intent = new Intent(this, ImageViewerActivity.class);
                intent.putParcelableArrayListExtra("bundle_key_image_slides", arrayList);
                intent.putExtra(Constants.BUNDLE_KEY_IMAGE_NO_DOWNLOAD, true);
                String str = this.crArticle.isSinglePageArticle() ? "tp-article" : "clinical-ref-article";
                intent.putExtra(Constants.BUNDLE_KEY_ASSET_ID, this.assetId);
                intent.putExtra(Constants.BUNDLE_KEY_ARTICLE_ID, this.crArticle.getArticleId());
                intent.putExtra(Constants.BUNDLE_KEY_PAGE_NAME, str);
                intent.putExtra("bundle_key_image_slide_position", 0);
                intent.putExtra(Constants.BUNDLE_KEY_DO_SHOW_SLIDECOUNTER, true);
                if (this.crArticle != null) {
                    intent.putExtra(Constants.BUNDLE_KEY_FROM_CKB, true);
                    adjustOmnitureForMedia();
                }
                startActivity(intent);
            }
        }
    }

    private boolean isContentSaved() {
        Cursor query;
        try {
            String maskedGuid = AuthenticationManager.getInstance(this).getMaskedGuid();
            if (!StringUtil.isNotEmpty(maskedGuid) || this.crArticle == null || (query = getContentResolver().query(ClinicalReferenceArticle.ClinicalReferenceArticles.CONTENT_URI, (String[]) null, "articleId=? AND (userGuid='' OR userGuid=?)", new String[]{String.valueOf(this.crArticle.getArticleId()), maskedGuid}, (String) null)) == null || !query.moveToFirst()) {
                return false;
            }
            query.close();
            return true;
        } catch (Exception e) {
            Trace.e(TAG, e.getMessage());
            return false;
        }
    }

    public void saveContent() {
        this.viewModel.saveContent(this, this, this.crArticle);
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle bundle) {
        bundle.putSerializable(BUNDLE_KEY_CRARTICLE, this.crArticle);
        super.onSaveInstanceState(bundle);
    }

    /* access modifiers changed from: protected */
    public void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);
        if (bundle != null) {
            this.crArticle = (ClinicalReferenceArticle) bundle.get(BUNDLE_KEY_CRARTICLE);
        }
    }

    public void onResume() {
        super.onResume();
        if (!AccountProvider.isUserLoggedIn(this)) {
            finish();
        } else if (isSessionValid()) {
            AppboyEventsHandler.routeDailyEventsToFirebaseOrBraze(this, AppboyConstants.APPBOY_EVENT_CKB_VIEWED);
            if (Util.getDisplayOrientation(this) != 0) {
                onAdNotAvilable();
            } else if (this.onCreateCompleted) {
                this.onCreateCompleted = false;
            } else if (this.crArticle != null) {
                requestBottomBannerAD();
            } else {
                onAdNotAvilable();
            }
            invalidateOptionsMenu();
        }
    }

    public void sendOmniturePing(String str) {
        ClinicalReferenceArticle clinicalReferenceArticle = this.crArticle;
        if (clinicalReferenceArticle != null) {
            String str2 = clinicalReferenceArticle.isSinglePageArticle() ? "tp-article" : "clinical-ref-article";
            if (StringUtil.isNullOrEmpty(str)) {
                str = buildSectionNameForPing(this.mCurrentSectionIndex);
            }
            String str3 = str;
            if (this.crArticle.getArticleId() > 0) {
                ChronicleIDUtil chronicleIDUtil = new ChronicleIDUtil();
                String valueOf = String.valueOf(this.crArticle.getArticleId());
                String str4 = this.assetId;
                this.omnitureData = chronicleIDUtil.generateAssetId(valueOf, str4, "/" + str2 + "/view/" + str3);
                if (this.mOmnitureContentData == null) {
                    this.mOmnitureContentData = new HashMap<>();
                }
                this.mOmnitureContentData.put("wapp.asset", this.omnitureData);
                this.mOmnitureContentData.put("wapp.pgtitle", this.crArticle.getTitle());
                if (!this.pclass.equalsIgnoreCase("alertviewer")) {
                    OmnitureManager.sReferringData.clear();
                }
                this.mOmnitureContentData.putAll(OmnitureManager.sReferringData);
            }
            this.mPvid = OmnitureManager.get().trackPageView(this, Constants.OMNITURE_CHANNEL_REFERENCE, str2, "view", str3, (String) null, this.mOmnitureContentData);
            ClickStreamManager.INSTANCE.sendEvent(this, ClickstreamConstants.EventType.pageView, this.crArticle.getTitle(), this.screenSpecificMap, (Impression[]) null, (String[]) null, this.mPvid, this.omnitureData);
            OmnitureManager.get().setReferringData(this.adSegVars, this.mPvid, this.crArticle.getTitle());
        }
    }

    private void adjustOmnitureForMedia() {
        ClinicalReferenceArticle clinicalReferenceArticle = this.crArticle;
        if (clinicalReferenceArticle != null) {
            OmnitureManager.get().setmCurrentPageName(OmnitureManager.get().buildPageName(clinicalReferenceArticle.isSinglePageArticle() ? "tp-article" : "clinical-ref-article", "view", String.valueOf(this.crArticle.getArticleId()), (String) null));
        }
    }

    public void onPause() {
        super.onPause();
        this.isPause = true;
        this.mHandler.removeCallbacks(this.mAutohideTimer);
        this.mHandler.removeCallbacks(this.mTimer);
    }

    /* access modifiers changed from: private */
    public void requestBottomBannerAD() {
        TextView textView;
        if (!(this.adLayout == null || !this.isFirstTime || (textView = (TextView) this.adLayout.findViewById(R.id.dfp_adLabel)) == null)) {
            textView.setVisibility(0);
        }
        if (this.adLayout != null) {
            Util.setContainerRule(false, this.mContainer, R.id.ad);
        }
        if (this.nativeAdAction != null) {
            this.nativeAdAction.prepADWithCombinedRequests(new INativeDFPAdLoadListener() {
                public void onAdLoaded(NativeDFPAD nativeDFPAD) {
                    if (ClinicalReferenceArticleActivity.this.adLayout != null) {
                        ClinicalReferenceArticleActivity.this.adLayout.setVisibility(0);
                        ADBindingHelper.Companion.bindCombinedAD(ClinicalReferenceArticleActivity.this.adLayout, nativeDFPAD);
                        Util.setContainerRule(true, ClinicalReferenceArticleActivity.this.mContainer, R.id.ad);
                    }
                }

                public void onAdFailedToLoad(int i) {
                    ClinicalReferenceArticleActivity.this.onAdNotAvilable();
                }
            }, this.nativeAdAction.getBottomBannerADsizes());
            getAd(this.nativeAdAction);
        }
    }

    private void makeAdRequest(DFPAdAction dFPAdAction, HashMap<String, String> hashMap) {
        if (dFPAdAction != null) {
            this.adBidder.makeADCallWithBidding((Context) this, hashMap, dFPAdAction);
        }
    }

    public void getAd(NativeAdAction nativeAdAction) {
        if (Util.isOnline(this) && Util.getDisplayOrientation(this) == 0 && !this.isPause && nativeAdAction != null) {
            this.screenSpecificMap.put("pvid", this.mPvid);
            HashMap hashMap = new HashMap();
            if (nativeAdAction.isSharethrough) {
                this.screenSpecificMap.put("pos", getString(R.string.sharethrough_ad_pos));
                hashMap.clear();
                hashMap.putAll(this.screenSpecificMap);
            } else if (nativeAdAction.isInlineAd) {
                this.mNumberOfAds++;
                this.screenSpecificMap.put("pos", getResources().getString(R.string.inline_ad_pos));
                this.screenSpecificMap.put(AdsConstants.PG, String.valueOf(this.mNumberOfAds));
                this.inlineScreenSpecificMap.putAll(this.screenSpecificMap);
                hashMap.clear();
                hashMap.putAll(this.screenSpecificMap);
            } else {
                this.screenSpecificMap.put("pos", getResources().getString(R.string.banner_ad_pos));
                this.bannerScreenSpecificMap.putAll(this.screenSpecificMap);
                hashMap.clear();
                hashMap.putAll(this.screenSpecificMap);
            }
            this.adBidder.makeADCallWithBidding((Context) this, (HashMap<String, String>) hashMap, nativeAdAction);
        }
    }

    public String getCurrentPvid() {
        return this.mPvid;
    }

    public /* synthetic */ void lambda$new$14$ClinicalReferenceArticleActivity() {
        this.h.sendEmptyMessage(52);
    }

    public /* synthetic */ void lambda$new$15$ClinicalReferenceArticleActivity() {
        this.h.sendEmptyMessage(102);
    }

    public void setScreenSpecificMap() {
        this.screenSpecificMap.put("pos", getResources().getString(R.string.banner_ad_pos));
        this.screenSpecificMap.put("pc", this.pclass);
        if (this.crArticle != null) {
            HashMap<String, String> queryDatabase = AdsSegvar.getInstance().queryDatabase(this, this.crArticle.getArticleId(), 2);
            this.adSegVars = queryDatabase;
            this.assetId = queryDatabase.containsKey("article-id") ? this.adSegVars.get("article-id") : null;
            this.mOmnitureContentData = OmnitureManager.get().getContentBasedOmnitureData(this.adSegVars, -1);
            this.screenSpecificMap.putAll(this.adSegVars);
            HashMap<String, String> hashMap = this.screenSpecificMap;
            hashMap.put("art", "" + this.crArticle.getArticleId());
        }
    }

    public void isAdExpandedByUser(boolean z) {
        if (z) {
            this.mHandler.removeCallbacks(this.mAutohideTimer);
            this.mHandler.removeCallbacks(this.mTimer);
            this.isExpandedByUser = true;
            return;
        }
        if (this.isExpandedByUser) {
            long j = this.rotate;
            if (j > 0) {
                this.mHandler.postDelayed(this.mTimer, j * 1000);
            }
        }
        this.isExpandedByUser = false;
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
    }

    public void onConfigurationChanged(Configuration configuration) {
        if (configuration.orientation == 2) {
            onAdNotAvilable();
        } else {
            requestBottomBannerAD();
        }
        if (CustomMenu.isShowing()) {
            CustomMenu.hide();
            openOptionsMenu();
        }
        super.onConfigurationChanged(configuration);
    }

    /* access modifiers changed from: protected */
    public void setupActionBar() {
        showUpButton();
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setDisplayUseLogoEnabled(false);
            getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.medscape_blue)));
        }
    }

    /* access modifiers changed from: protected */
    public String getContentLink() {
        String str;
        if (this.crArticle == null || (str = this.mSectionId) == null) {
            return OldConstants.CR_WEB_URL;
        }
        if (str.equalsIgnoreCase("DDx")) {
            str = "differential";
        }
        return OldConstants.CR_WEB_URL + this.crArticle.getArticleId() + "-" + str;
    }

    public String generateSectionID(ClinicalReferenceContent clinicalReferenceContent, int i) {
        String str;
        if (clinicalReferenceContent == null || clinicalReferenceContent.sections == null || clinicalReferenceContent.sections.isEmpty()) {
            return null;
        }
        Sect1 sect1 = clinicalReferenceContent.sections.get(i);
        ArrayList<Sect2> arrayList = sect1.subsections;
        if (arrayList == null || arrayList.isEmpty()) {
            str = sect1.title.toLowerCase();
        } else if (arrayList.get(0).id != null) {
            str = sect1.id;
        } else {
            str = sect1.title.toLowerCase();
        }
        return str.replaceAll(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR, "%20");
    }

    /* access modifiers changed from: protected */
    public String getContentTitle() {
        return this.crArticle.getTitle();
    }

    public void onLinkClicked(TextView textView, CrossLink crossLink) {
        if (crossLink != null) {
            if (crossLink.type.equals(CrossLink.Type.DISABLED)) {
                handleFeatureDisabled(crossLink.toString());
            } else if (crossLink.type.equals(CrossLink.Type.REFERENCES)) {
                showReferencesFragment();
            } else if (crossLink.type.equals(CrossLink.Type.CONTRIBUTORS)) {
                showContributorsFragment();
            } else if (crossLink.type.equals(CrossLink.Type.MEDIAGALLERY)) {
                showMediaGallery();
            } else if (crossLink.type.equals(CrossLink.Type.REFLINK)) {
                showInlineReferences(textView, crossLink);
            } else if (crossLink.type.equals(CrossLink.Type.CONTENTLINK)) {
                handleDeeplink(crossLink.ref);
            } else if (crossLink.type.equals(CrossLink.Type.A)) {
                handleExternalURls(crossLink);
            } else if (crossLink.type.equals(CrossLink.Type.DRUG)) {
                handleExternalURls(crossLink);
            } else if (crossLink.type.equals(CrossLink.Type.VIDEO)) {
                showFullScreenVideo(crossLink);
            } else if (crossLink.type.equals(CrossLink.Type.IMAGE)) {
                showFullScreenImage(crossLink);
            }
            Util.hideKeyboard(this);
        }
    }

    public void showInlineReferences(TextView textView, CrossLink crossLink) {
        if (crossLink != null) {
            SharedPreferenceProvider sharedPreferenceProvider = SharedPreferenceProvider.get();
            StringBuilder sb = new StringBuilder();
            sb.append(Constants.PREF_REFERENCE_NIGHT_MODE);
            sb.append(AuthenticationManager.getInstance(this).getMaskedGuid());
            boolean z = sharedPreferenceProvider.get(sb.toString(), 0) == 1;
            int i = SharedPreferenceProvider.get().get(Constants.PREF_REFERENCE_TEXT_SIZE_INDEX + AuthenticationManager.getInstance(this).getMaskedGuid(), -1);
            LinearLayout linearLayout = new LinearLayout(this);
            linearLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
            linearLayout.setOrientation(1);
            ClinicalReferenceArticle clinicalReferenceArticle = this.crArticle;
            if (!(clinicalReferenceArticle == null || clinicalReferenceArticle.getContent() == null || this.crArticle.getContent().references == null || crossLink.refs == null)) {
                for (String str : crossLink.refs) {
                    ReferenceItem referenceItem = this.crArticle.getContent().references.get(str);
                    if (!(referenceItem == null || referenceItem.para == null)) {
                        TextView textView2 = new TextView(this);
                        textView2.setMovementMethod(LocatableLinkMovementMethod.getInstance());
                        textView2.setText(referenceItem.para);
                        if (z) {
                            textView2.setTextColor(getResources().getColor(17170443));
                        } else {
                            textView2.setTextColor(getResources().getColor(17170444));
                        }
                        linearLayout.addView(textView2);
                        textView2.setTextSize((float) ContentUtils.getPopOverFontSize(i));
                    }
                }
            }
            if (linearLayout.getChildCount() > 0) {
                ScrollView scrollView = new ScrollView(this);
                scrollView.setBackgroundColor(0);
                scrollView.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
                scrollView.addView(linearLayout);
                this.mInlineReferencePopover.setNightMode(z);
                this.mInlineReferencePopover.show(textView, scrollView);
            }
        }
    }

    public void handleExternalURls(CrossLink crossLink) {
        if (crossLink != null && crossLink.ref != null) {
            String str = crossLink.ref;
            if (str.contains(".medscape.com/medline")) {
                WebviewUtil.Companion.launchMedline(this, str, "", "", "medln", Constants.OMNITURE_CHANNEL_REFERENCE);
            } else if (str.contains("emedicine.medscape.com/article")) {
                handleDeeplink(str);
            } else if (str.startsWith("ckb://")) {
                handleDeeplink(str);
            } else if (str.contains("mp4")) {
                Intent intent = new Intent(this, VideoPlayer.class);
                intent.putExtra("path", str);
                intent.putExtra("articleTitle", this.mTitle);
                startActivity(intent);
            } else if (str.startsWith("tel:")) {
                Uri.parse(str);
                startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
            } else if (str.equals("back://blank")) {
                finish();
            } else if (str.startsWith("drugs")) {
                String[] split = str.split("//");
                Intent intent2 = new Intent(this, DrugMonographMainActivity.class);
                intent2.putExtra(Constants.EXTRA_CONTENT_ID, Integer.parseInt(split[1]));
                intent2.putExtra(Constants.EXTRA_PUSH_LAUNCH, true);
                startActivity(intent2);
            } else if (crossLink.type != null && crossLink.type.equals(CrossLink.Type.DRUG)) {
                Intent intent3 = new Intent(this, DrugMonographMainActivity.class);
                intent3.putExtra(Constants.EXTRA_CONTENT_ID, Integer.parseInt(crossLink.ref));
                intent3.putExtra(Constants.EXTRA_PUSH_LAUNCH, true);
                startActivity(intent3);
            } else if (str.startsWith(MailTo.MAILTO_SCHEME)) {
                Util.handleMailToWithExternalApp(this, str);
            } else {
                WebviewUtil.Companion.launchPlainWebView(this, str, "", "", "none", Constants.OMNITURE_CHANNEL_REFERENCE, "", 0, false);
            }
        }
    }

    public String parseArticleIdFromUrl(String str) {
        String lastPathSegment;
        String lastPathSegment2;
        if (str == null) {
            return str;
        }
        Uri parse = Uri.parse(str);
        String scheme = parse.getScheme();
        if ("http".equals(scheme) || RequestBuilders.DEFAULT_SCHEME.equals(scheme)) {
            if (!"emedicine.medscape.com".equals(parse.getHost()) || (lastPathSegment = parse.getLastPathSegment()) == null) {
                return str;
            }
            String[] split = lastPathSegment.split("-");
            return split.length > 0 ? split[0] : str;
        } else if ("ckb".equals(scheme)) {
            return parse.getHost();
        } else {
            if (scheme != null || (lastPathSegment2 = parse.getLastPathSegment()) == null) {
                return str;
            }
            String[] split2 = lastPathSegment2.split("-");
            if (split2.length > 0) {
                return split2[0];
            }
            return str;
        }
    }

    public String parseArticleSectionIdFromUrl(String str) {
        String lastPathSegment;
        String lastPathSegment2;
        if (str != null) {
            Uri parse = Uri.parse(str);
            String scheme = parse.getScheme();
            if ("http".equals(scheme) || RequestBuilders.DEFAULT_SCHEME.equals(scheme)) {
                if ("emedicine.medscape.com".equals(parse.getHost()) && (lastPathSegment = parse.getLastPathSegment()) != null) {
                    String[] split = lastPathSegment.split("-");
                    if (split.length <= 1 || !StringUtil.isNotEmpty(split[1])) {
                        return null;
                    }
                    return split[1];
                }
            } else if ("ckb".equals(scheme)) {
                if (StringUtil.isNotEmpty(parse.getQueryParameter("section"))) {
                    return parse.getQueryParameter("section");
                }
                return null;
            } else if (scheme == null && (lastPathSegment2 = parse.getLastPathSegment()) != null) {
                String[] split2 = lastPathSegment2.split("-");
                if (split2.length <= 1 || !StringUtil.isNotEmpty(split2[1])) {
                    return null;
                }
                return split2[1];
            }
        }
        return str;
    }

    public void handleDeeplink(String str) {
        String parseArticleIdFromUrl = parseArticleIdFromUrl(str);
        String parseArticleSectionIdFromUrl = parseArticleSectionIdFromUrl(str);
        String str2 = "";
        if (this.crArticle != null) {
            if ((str2 + this.crArticle.getArticleId()).equals(parseArticleIdFromUrl)) {
                browseToSection(parseArticleSectionIdFromUrl);
                return;
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append("ckb://");
        sb.append(parseArticleIdFromUrl);
        if (StringUtil.isNotEmpty(parseArticleSectionIdFromUrl)) {
            str2 = "?section=" + parseArticleSectionIdFromUrl;
        }
        sb.append(str2);
        startActivity(new Intent().setAction("android.intent.action.VIEW").addCategory("android.intent.category.BROWSABLE").setData(Uri.parse(sb.toString())).putExtra(Constants.EXTRA_PUSH_LAUNCH, true));
    }

    private void showFullScreenImage(CrossLink crossLink) {
        if (!Util.isOnline(this)) {
            handleNoInternetForMediaGallery();
        } else if (crossLink != null && crossLink.ref != null) {
            Figure figure = this.crArticle.getContent().mediaGalleryMap.get(crossLink.ref);
            if (figure.isImage() && StringUtil.isNotEmpty(figure.graphic.href)) {
                Slide slide = new Slide();
                slide.graphicUrl = figure.graphic.href;
                if (figure.caption != null) {
                    slide.caption = figure.caption.toString();
                }
                ArrayList arrayList = new ArrayList();
                arrayList.add(slide);
                Intent intent = new Intent(this, ImageViewerActivity.class);
                intent.putParcelableArrayListExtra("bundle_key_image_slides", arrayList);
                intent.putExtra("bundle_key_image_slide_position", 0);
                startActivity(intent);
            }
        }
    }

    private void showFullScreenVideo(CrossLink crossLink) {
        if (crossLink != null && crossLink.ref != null) {
            Figure figure = this.crArticle.getContent().mediaGalleryMap.get(crossLink.ref);
            if (figure.isVideo() && StringUtil.isNotEmpty(figure.graphic.href)) {
                Intent intent = new Intent(this, FullScreenVideoPlayer.class);
                intent.setData(Uri.parse(crossLink.ref));
                intent.putExtra(Constants.BUNDLE_KEY_VIDEO_URL, figure.graphic.href);
                intent.putExtra(Constants.BUNDLE_KEY_VIDEO_AUTOPLAY, false);
                startActivity(intent);
            }
        }
    }

    private void sendCPPing() {
        ClinicalReferenceArticle clinicalReferenceArticle = this.crArticle;
        if (clinicalReferenceArticle != null && clinicalReferenceArticle.getContent() != null) {
            String str = this.crArticle.isSinglePageArticle() ? "tp-article" : "clinical-ref-article";
            String str2 = str + "/" + "view" + "/" + buildSectionNameForPing(0);
            HashMap<String, String> queryDatabase = AdsSegvar.getInstance().queryDatabase(this, this.crArticle.getArticleId(), 2);
            AsyncTaskHelper.execute(threadPoolExecutor, new FireCPEventWithAdsSegvarAsyncTask(this, "refarticle", "view", "" + this.crArticle.getArticleId(), str2, (String) null), queryDatabase);
        }
    }

    public String buildSectionNameForPing(int i) {
        StringBuilder sb = new StringBuilder();
        ClinicalReferenceArticle clinicalReferenceArticle = this.crArticle;
        if (!(clinicalReferenceArticle == null || clinicalReferenceArticle.getContent() == null)) {
            try {
                String str = this.crArticle.getContent().getSection(i).title;
                if (StringUtil.isNotEmpty(str)) {
                    sb.append(this.crArticle.getArticleId());
                    sb.append("-");
                    sb.append(str.toLowerCase().replace(' ', '-'));
                } else {
                    sb.append(this.crArticle.getArticleId());
                }
            } catch (ContentNotFoundException unused) {
            }
        }
        return sb.toString();
    }

    private void setNextSectionName(int i) throws ContentNotFoundException {
        String str;
        if (i != this.crArticle.getContent().sections.size() - 1) {
            str = this.crArticle.getContent().getSectionTitle(i + 1);
        } else {
            str = "";
        }
        this.mClinicalReferenceContentDataAdapter.setNextSectionName(str);
    }

    public Dialog onCreateDialog(int i) {
        Dialog onCreateDialog = super.onCreateDialog(i);
        if (onCreateDialog != null) {
            return onCreateDialog;
        }
        if (i == 5) {
            String string = getResources().getString(R.string.alert_dialog_network_connection_error_message);
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(string).setCancelable(false).setIcon(17301543).setPositiveButton(getString(R.string.alert_dialog_confirmation_button_text_close), $$Lambda$ClinicalReferenceArticleActivity$nwy5nyAgnjxVti207SBId56avLQ.INSTANCE).setOnKeyListener($$Lambda$ClinicalReferenceArticleActivity$6iy1f9uM8eYFZbUqoNWrfIVvZo.INSTANCE);
            return builder.create();
        } else if (i != 14) {
            return null;
        } else {
            String string2 = getResources().getString(R.string.alert_dialog_clinical_article_network_connection_error_message);
            AlertDialog.Builder builder2 = new AlertDialog.Builder(this);
            builder2.setMessage(string2).setCancelable(false).setIcon(17301543).setPositiveButton(getString(R.string.alert_dialog_confirmation_button_text_close), new DialogInterface.OnClickListener() {
                public final void onClick(DialogInterface dialogInterface, int i) {
                    ClinicalReferenceArticleActivity.this.lambda$onCreateDialog$18$ClinicalReferenceArticleActivity(dialogInterface, i);
                }
            }).setOnKeyListener($$Lambda$ClinicalReferenceArticleActivity$oiehlrytdQYVfGCwI9kYT0T9NU.INSTANCE);
            return builder2.create();
        }
    }

    public /* synthetic */ void lambda$onCreateDialog$18$ClinicalReferenceArticleActivity(DialogInterface dialogInterface, int i) {
        dialogInterface.cancel();
        finish();
    }

    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        if (i != 82) {
            return super.onKeyUp(i, keyEvent);
        }
        PopupMenu popupMenu = new PopupMenu(this, findViewById(R.id.oldMenu));
        popupMenu.setOnMenuItemClickListener(new AbstractContentViewerActvity.SharePopupMenuItemListener());
        popupMenu.getMenuInflater().inflate(getCurrentMenuLayout(), popupMenu.getMenu());
        setMenuMoreOptionVisibility(popupMenu.getMenu());
        popupMenu.show();
        return true;
    }

    private void dismissConsultHintBubble() {
        CalloutPopover calloutPopover = this.mConsultHintBubble;
        if (calloutPopover != null) {
            calloutPopover.dismiss();
            this.mConsultHintBubble = null;
        }
    }

    public ClinicalReferenceArticle getCrArticle() {
        return this.crArticle;
    }

    private int getCurrentMenuLayout() {
        if (this.mIsFindMode) {
            return R.menu.empty_menu;
        }
        return isContentSaved() ? R.menu.clinical_content_page_save_full : R.menu.clinical_content_page_save_empty;
    }

    private void setActionBarBackButtonVisibility(boolean z) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(z);
        }
    }

    public void showCurrentFindPosition(int i, int i2) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        if (i2 > 0) {
            String str = (i + 1) + "/" + i2;
            if (str.length() > 6) {
                str = str.replace("/", "/\n");
            }
            spannableStringBuilder.append(str);
        } else {
            spannableStringBuilder.append("0/0");
            spannableStringBuilder.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this, R.color.red)), 0, spannableStringBuilder.length(), 33);
        }
        this.mFindCounter.setText(spannableStringBuilder);
    }

    private void clearFindMatches() {
        this.mTotalFinds = -1;
        this.mCurrentFindPosition = 0;
        this.mFindQuery = "";
        this.mIsFindMode = false;
        setupHeader();
        setSelectionInHeader(this.mCurrentSectionIndex);
        invalidateOptionsMenu();
        this.mClinicalReferenceContentDataAdapter.setFindQuery(this.mFindQuery);
        Util.hideKeyboard(this);
    }

    public int getCurrentSectionIndex() {
        return this.mCurrentSectionIndex;
    }

    private void setMenuMoreOptionVisibility(Menu menu) {
        MenuItem findItem = menu.findItem(R.id.action_find);
        if (findItem != null) {
            ClinicalReferenceArticleFragment clinicalReferenceArticleFragment = this.mContentSectionFragment;
            if (clinicalReferenceArticleFragment == null || !clinicalReferenceArticleFragment.isAdded()) {
                findItem.setVisible(false);
            } else {
                findItem.setVisible(true);
            }
        }
    }

    public void onNightModeChanged(boolean z) {
        ClinicalReferenceMenuAdapter clinicalReferenceMenuAdapter = this.mClinicalReferenceMenuAdapter;
        if (clinicalReferenceMenuAdapter != null) {
            clinicalReferenceMenuAdapter.setNightMode(z);
        }
        setDropDownMenuNightMode(z);
    }

    private void setDropDownMenuNightMode(boolean z) {
        ListView listView = this.dropDownList;
        if (listView == null) {
            return;
        }
        if (z) {
            listView.setBackgroundResource(R.drawable.drop_shadow_black);
            this.dropDownList.setDivider(new ColorDrawable(-1));
            this.dropDownList.setDividerHeight(1);
            return;
        }
        listView.setBackgroundResource(R.drawable.drop_shadow_four);
        this.dropDownList.setDivider(new ColorDrawable(ViewCompat.MEASURED_STATE_MASK));
        this.dropDownList.setDividerHeight(1);
    }
}
