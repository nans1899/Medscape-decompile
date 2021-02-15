package com.medscape.android.drugs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.PopupMenu;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.doubleclick.PublisherAdView;
import com.ib.clickstream.ClickstreamConstants;
import com.ib.clickstream.Impression;
import com.medscape.android.BI.omniture.OmnitureManager;
import com.medscape.android.CP.FireCPEventWithAdsSegvarAsyncTask;
import com.medscape.android.Constants;
import com.medscape.android.MedscapeMenu;
import com.medscape.android.R;
import com.medscape.android.activity.AbstractContentViewerActvity;
import com.medscape.android.activity.AbstractIndexFragment;
import com.medscape.android.activity.formulary.BrandModel;
import com.medscape.android.activity.formulary.BrandModels;
import com.medscape.android.activity.formulary.FormularyFinder;
import com.medscape.android.activity.formulary.FormularyMyPlanPage;
import com.medscape.android.activity.formulary.SelectBrandActivity;
import com.medscape.android.activity.search.RecentlyViewedSuggestionHelper;
import com.medscape.android.ads.ADBindingHelper;
import com.medscape.android.ads.AdRequestHelper;
import com.medscape.android.ads.AdsSegvar;
import com.medscape.android.ads.AdsSegvarIntf;
import com.medscape.android.ads.DFPAdAction;
import com.medscape.android.ads.DFPReferenceAdListener;
import com.medscape.android.ads.INativeDFPAdLoadListener;
import com.medscape.android.ads.NativeAdAction;
import com.medscape.android.ads.NativeDFPAD;
import com.medscape.android.ads.OnAdListener;
import com.medscape.android.ads.bidding.AdBidder;
import com.medscape.android.ads.sharethrough.FallbackManager;
import com.medscape.android.ads.sharethrough.SharethroughFallback;
import com.medscape.android.analytics.ClickStreamManager;
import com.medscape.android.appboy.AppboyEventsHandler;
import com.medscape.android.cache.CacheManager;
import com.medscape.android.cache.DrugCache;
import com.medscape.android.capabilities.CapabilitiesManager;
import com.medscape.android.contentviewer.ContentSectionDataAdapter;
import com.medscape.android.contentviewer.CrossLink;
import com.medscape.android.contentviewer.LineItem;
import com.medscape.android.contentviewer.fragments.DrugClassesFragment;
import com.medscape.android.contentviewer.view_holders.DataViewHolder;
import com.medscape.android.db.DatabaseHelper;
import com.medscape.android.db.model.Drug;
import com.medscape.android.db.model.DrugClass;
import com.medscape.android.drugs.details.repositories.DrugDetailInteractionRepository;
import com.medscape.android.drugs.details.views.DrugDetailsActivity;
import com.medscape.android.drugs.helper.SearchHelper;
import com.medscape.android.drugs.model.DrugManufactureAd;
import com.medscape.android.drugs.model.DrugManufacturer;
import com.medscape.android.drugs.model.DrugMonograph;
import com.medscape.android.drugs.model.DrugMonographIndexElement;
import com.medscape.android.drugs.model.DrugMonographSectionIndex;
import com.medscape.android.drugs.model.DrugsContract;
import com.medscape.android.drugs.parser.DrugMonographParser;
import com.medscape.android.helper.AsyncTaskHelper;
import com.medscape.android.interfaces.ICallbackEvent;
import com.medscape.android.parser.model.Article;
import com.medscape.android.util.DialogUtil;
import com.medscape.android.util.LogUtil;
import com.medscape.android.util.MedscapeException;
import com.medscape.android.util.NumberUtil;
import com.medscape.android.util.OldConstants;
import com.medscape.android.util.RedirectHandler;
import com.medscape.android.util.StringUtil;
import com.medscape.android.util.Util;
import com.medscape.android.util.constants.AppboyConstants;
import com.medscape.android.welcome.WelcomeActivity;
import com.wbmd.adlibrary.constants.AdParameterKeys;
import com.wbmd.wbmdcommons.utils.ChronicleIDUtil;
import com.webmd.wbmdproffesionalauthentication.providers.AccountProvider;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DrugMonographMainActivity extends AbstractContentViewerActvity implements AdsSegvarIntf, AbstractIndexFragment.Callbacks {
    protected static final int GET_NEXT_AD = 102;
    private static final int SHOW_FORMULARY_NETWORK_ERROR_DIALOG = 109;
    private static final int SHOW_NO_FORMULARY_DIALOG = 110;
    private static final int START_TIMER = 101;
    private static final ExecutorService threadPoolExecutor = Executors.newSingleThreadExecutor();
    AdBidder adBidder = new AdBidder();
    private HashMap<String, String> adSegVars;
    private String assetId;
    protected long autohide;
    public Handler h = new Handler(new Handler.Callback() {
        public boolean handleMessage(Message message) {
            int i = message.what;
            if (i == 51) {
                DrugMonographMainActivity.this.mHandler.postDelayed(DrugMonographMainActivity.this.mAutohideTimer, DrugMonographMainActivity.this.autohide * 1000);
            } else if (i == 52) {
                DrugMonographMainActivity.this.onAdNotAvilable();
            } else if (i == 101) {
                DrugMonographMainActivity.this.mHandler.postDelayed(DrugMonographMainActivity.this.mTimer, DrugMonographMainActivity.this.rotate * 1000);
            } else if (i == 102) {
                DrugMonographMainActivity.this.requestBottomBannerAD(true);
            }
            return true;
        }
    });
    boolean hasInteractions = false;
    private volatile boolean isExpandedByUser = false;
    boolean isFromOnCreate = false;
    private Article mArticleForEmail;
    Runnable mAutohideTimer = new Runnable() {
        public void run() {
            DrugMonographMainActivity.this.h.sendEmptyMessage(52);
        }
    };
    View mContainer;
    public int mContentId = -1;
    DrugMonographMainFragment mDrugMainFragment;
    private DrugMonograph mDrugMonograph;
    private String mDrugName;
    public FormularyFinder mFormularyFinder;
    /* access modifiers changed from: private */
    public Handler mHandler = new Handler();
    private String mHeaderName;
    private MedscapeException mInternetRequiredException;
    boolean mIsItemSelected;
    /* access modifiers changed from: private */
    public boolean mIsViewingToc;
    public HashMap<String, Object> mOmnitureContentData;
    View mPopupView;
    private View mRoot;
    protected DrugMonographSectionIndex mSectionIndex;
    Runnable mTimer = new Runnable() {
        public void run() {
            DrugMonographMainActivity.this.h.sendEmptyMessage(102);
        }
    };
    private volatile String pclass = "toc";
    private PublisherAdView popUpADView;
    private Dialog popUpDialog;
    protected long rotate;
    private HashMap<String, String> screenSpecificMap = new HashMap<>();
    protected String searchQuery;
    boolean showPopUpAd = true;
    String subSectionLink;

    static /* synthetic */ void lambda$showPopupAd$7(View view) {
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
        this.skipForceUpAutoRegister = true;
        super.onCreate(bundle);
        this.mIsViewingToc = true;
        setContentView((int) R.layout.drug_main_menu);
        setupNativeBottomBannerAd(DFPReferenceAdListener.AD_UNIT_ID);
        Bundle redirectBundle = RedirectHandler.getRedirectBundle(getIntent());
        if (StringUtil.isNotEmpty(redirectBundle.getString(Constants.EXTRA_REDIRECT))) {
            OmnitureManager.get().markModule(true, "push", Constants.OMNITURE_MLINK_OPEN, (Map<String, Object>) null);
            if (isLoggedOut() || isDataUpdateRequired()) {
                startActivity(new Intent(this, WelcomeActivity.class).setFlags(67108864).putExtra(Constants.EXTRA_REDIRECT, redirectBundle));
                finish();
            }
        }
        this.mRoot = findViewById(R.id.root);
        this.mContainer = findViewById(R.id.drugMainMenuContainer);
        int i = -1;
        int intExtra = getIntent().getIntExtra(Constants.EXTRA_CONTENT_ID, -1);
        String dataString = getIntent().getDataString();
        if (intExtra == -1) {
            intExtra = getContentIdFromDeepLink(dataString);
        }
        if (intExtra == -1) {
            if (StringUtil.isNotEmpty(dataString)) {
                handleContentNotAvailable(dataString, intExtra);
            } else {
                finish();
                return;
            }
        }
        hasInteractions(intExtra);
        setmContentId(intExtra);
        Util.sendFirebaseContentInfo(this, AdParameterKeys.DRUG_ID, String.valueOf(intExtra));
        FormularyFinder formularyFinder = new FormularyFinder(this);
        this.mFormularyFinder = formularyFinder;
        formularyFinder.checkForFormularies(intExtra);
        this.pclass = getIntent().getBooleanExtra(Constants.EXTRA_PUSH_LAUNCH, false) ? "alertviewer" : "toc";
        this.mDrugName = getIntent().getStringExtra("drugName");
        setScreenSpecificMap();
        this.searchQuery = getIntent().getStringExtra(Constants.EXTRA_QUERY);
        this.mArticleForEmail = new Article();
        this.mDrugMonograph = DrugMonographParser.parse(getContentId());
        populateList();
        setTitle(this.mHeaderName);
        saveToRecentlyViewed();
        this.isFromOnCreate = true;
        this.mInternetRequiredException = new MedscapeException(getResources().getString(R.string.internet_required));
        this.mIsItemSelected = false;
        sendOmniturePing();
        sendCPPing();
        sendCPPingForValidSearch();
        initialSetup();
        try {
            if (StringUtil.isNotEmpty(this.subSectionLink)) {
                Iterator<DrugMonographIndexElement> it = this.mSectionIndex.getIndexElements().iterator();
                while (it.hasNext()) {
                    DrugMonographIndexElement next = it.next();
                    if (next.deepLink != null && next.deepLink.contains(this.subSectionLink)) {
                        i = this.mSectionIndex.getIndexElements().indexOf(next);
                    }
                }
                if (i >= 0 && i <= this.mDrugMonograph.getSections().size()) {
                    openDrugDetailsActivity(i);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        DrugMonograph drugMonograph = this.mDrugMonograph;
        if (drugMonograph == null || drugMonograph.getSections() == null || this.mDrugMonograph.getSections().isEmpty()) {
            handleContentNotAvailable(dataString, getContentId());
        } else {
            initializeDrugmonographFragment();
        }
    }

    /* access modifiers changed from: package-private */
    public int getContentIdFromDeepLink(String str) {
        String str2 = "";
        try {
            if (StringUtil.isNotEmpty(str)) {
                str2 = str.substring(str.lastIndexOf("/") + 1);
                if (str2.isEmpty() || !NumberUtil.isNumber(str2)) {
                    this.subSectionLink = str2;
                    String substring = str.substring(0, str.lastIndexOf("/"));
                    str2 = substring.substring(substring.lastIndexOf("/") + 1);
                }
            }
            if (NumberUtil.isNumber(str2)) {
                return Integer.parseInt(str2);
            }
            return -1;
        } catch (Throwable unused) {
            return -1;
        }
    }

    private void initializeDrugmonographFragment() {
        boolean isNonDrug = isNonDrug(getContentId());
        this.mDrugMainFragment = DrugMonographMainFragment.newInstance();
        Bundle bundle = new Bundle();
        bundle.putSerializable("drug", this.mDrugMonograph);
        bundle.putBoolean("isNonDrug", isNonDrug);
        bundle.putParcelableArrayList("sectionElements", this.mSectionIndex.getIndexElements());
        this.mDrugMainFragment.setArguments(bundle);
        this.mDrugMainFragment.setFormularyFinder(this.mFormularyFinder);
        try {
            getSupportFragmentManager().beginTransaction().replace(R.id.drugMainMenuContainer, this.mDrugMainFragment).commit();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void handleUpAction() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            Animation loadAnimation = AnimationUtils.loadAnimation(this, R.anim.push_right_in);
            loadAnimation.setDuration(250);
            loadAnimation.setAnimationListener(new Animation.AnimationListener() {
                public void onAnimationEnd(Animation animation) {
                }

                public void onAnimationRepeat(Animation animation) {
                }

                public void onAnimationStart(Animation animation) {
                    DrugMonographMainActivity.this.getSupportFragmentManager().popBackStack();
                    DrugMonographMainActivity.this.getSupportFragmentManager().executePendingTransactions();
                    boolean unused = DrugMonographMainActivity.this.mIsViewingToc = true;
                    DrugMonographMainActivity.this.requestBottomBannerAD(true);
                }
            });
            this.mRoot.startAnimation(loadAnimation);
        } else if (getSupportFragmentManager().getBackStackEntryCount() <= 0) {
            finish();
            overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
        } else {
            getSupportFragmentManager().popBackStack();
        }
    }

    public void onBackPressed() {
        try {
            handleUpAction();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private void saveToRecentlyViewed() {
        Bundle bundle = new Bundle(2);
        bundle.putString("type", SearchHelper.TYPE_DRUG);
        bundle.putInt("contentId", this.mContentId);
        RecentlyViewedSuggestionHelper.addToRecentlyViewed(this, this.mDrugName, bundle);
    }

    public void onItemSelected(int i) {
        openDrugDetailsActivity(i);
    }

    private void openDrugDetailsActivity(int i) {
        DrugMonographSectionIndex drugMonographSectionIndex = this.mSectionIndex;
        if (drugMonographSectionIndex != null && drugMonographSectionIndex.getIndexElements().size() > 0 && this.mSectionIndex.getItem(0).isAD) {
            i--;
        }
        if (!this.mIsItemSelected) {
            this.mIsItemSelected = true;
            onAdNotAvilable();
            Intent intent = new Intent(this, DrugDetailsActivity.class);
            intent.putExtra("contentId", getContentId());
            intent.putExtra("drugName", this.mDrugName);
            intent.putExtra(Constants.EXTRA_DRUG_PCLASS, this.pclass);
            intent.putExtra(Constants.EXTRA_DRUG_SELECTION_INDEX, i);
            intent.putExtra(Constants.EXTRA_DRUG_HAS_INTERACTIONS, this.hasInteractions);
            intent.putExtra(Constants.EXTRA_DRUG_SELECTION_INDEX_OBJ, this.mSectionIndex);
            intent.putExtra("drug", this.mDrugMonograph);
            startActivity(intent);
            this.showPopUpAd = false;
            overridePendingTransition(R.anim.push_left_in, R.anim.push_none);
        }
    }

    public void openFormulary(List<BrandModel> list) {
        onAdNotAvilable();
        BrandModels brandModels = new BrandModels();
        brandModels.setBrandModels(list);
        Bundle bundle = new Bundle();
        bundle.putInt(FormularyMyPlanPage.CONTENT_ID, getContentId());
        bundle.putString("TITLE", this.mHeaderName);
        bundle.putSerializable("BRAND_MODELS", brandModels);
        bundle.putString("drugName", this.mDrugName);
        Intent intent = new Intent(this, SelectBrandActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
        overridePendingTransition(R.anim.push_left_in, R.anim.push_none);
    }

    public void drugHeaderClicked(View view) {
        requestBottomBannerAD(false);
        DrugClassesFragment newInstance = DrugClassesFragment.newInstance(new Bundle());
        ArrayList arrayList = new ArrayList();
        List<DrugClass> classes = this.mDrugMonograph.getHeader().getClasses();
        int size = arrayList.size();
        arrayList.add(new LineItem((CrossLink) null, "Classes", size, true, false, false));
        for (DrugClass next : classes) {
            arrayList.add(new LineItem(new CrossLink(CrossLink.Type.CLASS, "" + next.getClassId()), next.getClassName(), size, false, false, true));
        }
        int size2 = arrayList.size();
        arrayList.add(new LineItem((CrossLink) null, "Brands & Other Names", size2, true, false, false));
        for (String lineItem : this.mDrugMonograph.getHeader().getBr().split(",")) {
            arrayList.add(new LineItem((CrossLink) null, lineItem, size2, false, false, true));
        }
        ContentSectionDataAdapter contentSectionDataAdapter = new ContentSectionDataAdapter(arrayList);
        contentSectionDataAdapter.addDataListClickListener(new DataViewHolder.DataListClickListener(contentSectionDataAdapter) {
            public final /* synthetic */ ContentSectionDataAdapter f$1;

            {
                this.f$1 = r2;
            }

            public final void onDataListItemClicked(int i) {
                DrugMonographMainActivity.this.lambda$drugHeaderClicked$0$DrugMonographMainActivity(this.f$1, i);
            }
        });
        newInstance.setDataAdapter(contentSectionDataAdapter);
        try {
            getSupportFragmentManager().beginTransaction().add((int) R.id.drugMainMenuContainer, (Fragment) newInstance, "classes").addToBackStack((String) null).commit();
        } catch (Throwable unused) {
        }
    }

    public /* synthetic */ void lambda$drugHeaderClicked$0$DrugMonographMainActivity(ContentSectionDataAdapter contentSectionDataAdapter, int i) {
        LineItem lineItem = contentSectionDataAdapter.getItems().get(i);
        if (lineItem.crossLink != null && lineItem.crossLink.type.equals(CrossLink.Type.CLASS)) {
            browseClass(lineItem.crossLink.ref, lineItem.text.toString());
        }
    }

    private void sendOmniturePing() {
        String str;
        if (getContentId() > 0) {
            String valueOf = String.valueOf(getContentId());
            ChronicleIDUtil chronicleIDUtil = new ChronicleIDUtil();
            String str2 = this.assetId;
            String generateAssetId = chronicleIDUtil.generateAssetId(valueOf, str2, "/drug/view/" + valueOf);
            this.mOmnitureContentData.put("wapp.asset", generateAssetId);
            this.mOmnitureContentData.put("wapp.pgtitle", this.mDrugName);
            if (!this.pclass.equalsIgnoreCase("alertviewer")) {
                OmnitureManager.sReferringData.clear();
            }
            this.mOmnitureContentData.putAll(OmnitureManager.sReferringData);
            str = generateAssetId;
        } else {
            str = "";
        }
        OmnitureManager omnitureManager = OmnitureManager.get();
        this.mPvid = omnitureManager.trackPageView(this, Constants.OMNITURE_CHANNEL_REFERENCE, "drug", "view", "" + getContentId(), (String) null, this.mOmnitureContentData);
        if (StringUtil.isNotEmpty(this.mHeaderName)) {
            ClickStreamManager.INSTANCE.sendEvent(this, ClickstreamConstants.EventType.pageView, this.mHeaderName, this.screenSpecificMap, (Impression[]) null, (String[]) null, this.mPvid, str);
        }
        OmnitureManager.get().setReferringData(this.adSegVars, this.mPvid, this.mDrugName);
    }

    public void openPharmaServices(String str) {
        MedscapeException medscapeException = this.mInternetRequiredException;
        if (medscapeException != null) {
            medscapeException.dismissSnackBar();
        }
        if (str != null) {
            Uri parse = Uri.parse(str);
            if (!StringUtil.isNotEmpty(parse.getScheme()) || !parse.getScheme().toLowerCase().equals("customurl")) {
                Intent intent = new Intent(this, DrugManufacturerActivity.class);
                intent.putExtra("url", str);
                startActivityForResult(intent, 99);
                return;
            }
            Util.openExternalApp(this, parse);
        }
    }

    public void browseClass(String str, String str2) {
        Intent intent = new Intent(this, DrugListActivity.class);
        intent.putExtra("classId", Integer.parseInt(str));
        intent.putExtra("className", Uri.decode(str2));
        startActivity(intent);
    }

    public Article getArticleForEmail() {
        String str = this.mDrugName;
        if (str != null) {
            this.mArticleForEmail.mTitle = str;
            Article article = this.mArticleForEmail;
            article.mLink = OldConstants.DRUG_WEB_URL + getContentId() + com.wbmd.wbmdcommons.utils.Util.attachSrcTagToUrl(OldConstants.DRUG_WEB_URL) + "&ref=email";
        }
        return this.mArticleForEmail;
    }

    public void hasInteractions(int i) {
        new DrugDetailInteractionRepository(this, String.valueOf(i)).hasInteractions(new ICallbackEvent<Boolean, Exception>() {
            public void onError(Exception exc) {
            }

            public void onCompleted(Boolean bool) {
                DrugMonographMainActivity.this.hasInteractions = bool.booleanValue();
                if (DrugMonographMainActivity.this.hasInteractions) {
                    DrugMonographMainActivity drugMonographMainActivity = DrugMonographMainActivity.this;
                    drugMonographMainActivity.runOnUiThread(new Runnable() {
                        public final void run() {
                            DrugMonographMainActivity.this.invalidateOptionsMenu();
                        }
                    });
                }
            }
        });
    }

    private void populateList() {
        try {
            String gc = this.mDrugMonograph.getHeader().getGc();
            this.mHeaderName = gc;
            if (gc != null && !gc.equals("") && this.mDrugName == null) {
                this.mDrugName = this.mHeaderName;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void handleContentNotAvailable(String str, int i) {
        this.mRoot.setVisibility(8);
        new MedscapeException(getResources().getString(R.string.alert_dialog_content_unavailable_message)).showAlert(this, getResources().getString(R.string.alert_dialog_open_in_browser), new DialogInterface.OnClickListener(str, i) {
            public final /* synthetic */ String f$1;
            public final /* synthetic */ int f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final void onClick(DialogInterface dialogInterface, int i) {
                DrugMonographMainActivity.this.lambda$handleContentNotAvailable$1$DrugMonographMainActivity(this.f$1, this.f$2, dialogInterface, i);
            }
        }, getResources().getString(R.string.alert_dialog_confirmation_button_text_close), new DialogInterface.OnClickListener() {
            public final void onClick(DialogInterface dialogInterface, int i) {
                DrugMonographMainActivity.this.lambda$handleContentNotAvailable$2$DrugMonographMainActivity(dialogInterface, i);
            }
        });
    }

    public /* synthetic */ void lambda$handleContentNotAvailable$1$DrugMonographMainActivity(String str, int i, DialogInterface dialogInterface, int i2) {
        if (StringUtil.isNullOrEmpty(str)) {
            str = "http://reference.medscape.com/drug/" + i;
        }
        Util.openInExternalBrowser(this, str);
        finish();
    }

    public /* synthetic */ void lambda$handleContentNotAvailable$2$DrugMonographMainActivity(DialogInterface dialogInterface, int i) {
        finish();
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        if (i == 99 && i2 == 1 && !isFinishing() && intent.hasExtra("url")) {
            this.mInternetRequiredException.showSnackBar(this.mRoot, -2, getResources().getString(R.string.retry), new View.OnClickListener(intent) {
                public final /* synthetic */ Intent f$1;

                {
                    this.f$1 = r2;
                }

                public final void onClick(View view) {
                    DrugMonographMainActivity.this.lambda$onActivityResult$3$DrugMonographMainActivity(this.f$1, view);
                }
            });
        }
        super.onActivityResult(i, i2, intent);
    }

    public /* synthetic */ void lambda$onActivityResult$3$DrugMonographMainActivity(Intent intent, View view) {
        openPharmaServices(intent.getStringExtra("url"));
    }

    public void showDialogAsMessage(int i) {
        MyAlertDialogFragment.newInstance(i).show(getSupportFragmentManager(), "dialog");
    }

    public static class MyAlertDialogFragment extends DialogFragment {
        public static MyAlertDialogFragment newInstance(int i) {
            MyAlertDialogFragment myAlertDialogFragment = new MyAlertDialogFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("id", i);
            myAlertDialogFragment.setArguments(bundle);
            return myAlertDialogFragment;
        }

        public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
            String str;
            int i = 0;
            View inflate = layoutInflater.inflate(R.layout.text_line_item, viewGroup, false);
            View findViewById = inflate.findViewById(R.id.text);
            if (getArguments() != null) {
                i = getArguments().getInt("id");
            }
            if (i == 16) {
                str = getString(R.string.alert_dialog_rss_article_network_connection_error_message);
            } else if (i == 23) {
                str = "Drug" + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + getResources().getString(R.string.alert_dialog_save_drug);
            } else if (i == 109) {
                str = "You must be connected to the internet in order to setup Formulary";
            } else if (i != 110) {
                str = getString(R.string.alert_dialog_rss_article_network_connection_error_message);
            } else {
                str = getString(R.string.no_formulary_available);
            }
            ((TextView) findViewById).setText(str);
            return inflate;
        }

        public Dialog onCreateDialog(Bundle bundle) {
            int i = getArguments() != null ? getArguments().getInt("id") : 0;
            if (i == 16) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage(getResources().getString(R.string.alert_dialog_rss_article_network_connection_error_message)).setCancelable(false).setIcon(17301543).setPositiveButton(getString(R.string.alert_dialog_confirmation_ok_button_text), $$Lambda$DrugMonographMainActivity$MyAlertDialogFragment$RTmwgthQbKoe2Qum0w2S5csfE.INSTANCE);
                return builder.create();
            } else if (i == 109) {
                AlertDialog.Builder builder2 = new AlertDialog.Builder(getActivity());
                builder2.setTitle("Error").setMessage("You must be connected to the internet in order to setup Formulary").setCancelable(false).setPositiveButton("Ok", $$Lambda$DrugMonographMainActivity$MyAlertDialogFragment$uHNNOTZ8lQAp913PuwJAFsUo3Wk.INSTANCE);
                return builder2.create();
            } else if (i != 110) {
                super.onCreateDialog(bundle);
                return super.onCreateDialog(bundle);
            } else {
                AlertDialog.Builder builder3 = new AlertDialog.Builder(getActivity());
                builder3.setMessage(getString(R.string.no_formulary_available)).setCancelable(false).setPositiveButton("Ok", $$Lambda$DrugMonographMainActivity$MyAlertDialogFragment$cgrxlWGZOE2NkeNygiL1lUsPAZU.INSTANCE);
                return builder3.create();
            }
        }
    }

    public Dialog onCreateDialog(int i) {
        String str;
        Dialog onCreateDialog = super.onCreateDialog(i);
        if (onCreateDialog != null) {
            return onCreateDialog;
        }
        if (i == 16) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(getResources().getString(R.string.alert_dialog_rss_article_network_connection_error_message)).setCancelable(false).setIcon(17301543).setPositiveButton(getString(R.string.alert_dialog_confirmation_ok_button_text), $$Lambda$DrugMonographMainActivity$QHCV_Zm_OaTetMZj04zq7fJbXs.INSTANCE);
            return builder.create();
        } else if (i == 23) {
            if (this.mDrugName != null) {
                str = this.mDrugName + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + getResources().getString(R.string.alert_dialog_save_drug);
            } else {
                str = this.mHeaderName + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + getResources().getString(R.string.alert_dialog_save_drug);
            }
            return DialogUtil.showAlertDialog(23, "", str, this);
        } else if (i != 109) {
            return onCreateDialog;
        } else {
            AlertDialog.Builder builder2 = new AlertDialog.Builder(this);
            builder2.setTitle("Error").setMessage("You must be connected to the internet in order to setup Formulary").setCancelable(false).setPositiveButton("Ok", $$Lambda$DrugMonographMainActivity$av3gVPHvJVKh1YMgbvt2mSTxQ88.INSTANCE);
            return builder2.create();
        }
    }

    public boolean isNonDrug(int i) {
        boolean z;
        try {
            DatabaseHelper databaseHelper = new DatabaseHelper(this);
            Cursor rawQuery = databaseHelper.getDatabase().rawQuery("SELECT DISTINCT IsNonDrug FROM tblDrugs WHERE ContentID = ? LIMIT 1", new String[]{String.valueOf(i)});
            loop0:
            while (true) {
                z = false;
                while (true) {
                    try {
                        if (!rawQuery.moveToNext()) {
                            break loop0;
                        } else if (rawQuery.getInt(0) == 1) {
                            z = true;
                        }
                    } catch (Exception e) {
                        e = e;
                        LogUtil.e(getClass().getName(), "getMessage =%s", e.getMessage());
                        return z;
                    }
                }
                return z;
            }
            rawQuery.close();
            databaseHelper.close();
        } catch (Exception e2) {
            e = e2;
            z = false;
            LogUtil.e(getClass().getName(), "getMessage =%s", e.getMessage());
            return z;
        }
        return z;
    }

    private void removeInfo() {
        if (getContentId() != -1 && this.mDrugName != null) {
            try {
                getContentResolver().delete(DrugCache.DrugCaches.CONTENT_URI, "contentId= ? ", new String[]{String.valueOf(getContentId())});
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void saveContent() {
        if (isContentSaved()) {
            removeInfo();
            OmnitureManager.get().trackModule(this, Constants.OMNITURE_CHANNEL_REFERENCE, "save", Constants.OMNITURE_MLINK_UNSAVE, (Map<String, Object>) null);
        } else {
            AppboyEventsHandler.logDailyEvent(this, AppboyConstants.APPBOY_EVENT_DRUG_SAVED, this);
            if (saveDrug()) {
                if (!isFinishing()) {
                    Toast.makeText(this, getResources().getString(R.string.drug_monograpf_saved_message), 0).show();
                }
                if (getContentId() != -1) {
                    MedscapeMenu.sendSaveBIPings(this, Constants.OMNITURE_CHANNEL_REFERENCE, "drugs");
                }
            }
        }
        invalidateOptionsMenu();
    }

    public boolean saveDrug() {
        if (!(getContentId() == -1 || this.mDrugName == null)) {
            DrugCache drugCache = new DrugCache();
            drugCache.setContentId(getContentId());
            drugCache.setSaved(true);
            drugCache.setType(1);
            drugCache.setDrugName(this.mDrugName);
            try {
                new CacheManager(this).addCache(drugCache);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    private boolean isContentSaved() {
        DrugCache cache = new CacheManager(this).getCache(getContentId());
        return cache != null && cache.isSaved();
    }

    public void onResume() {
        super.onResume();
        if (!AccountProvider.isUserLoggedIn(this)) {
            finish();
            return;
        }
        AppboyEventsHandler.routeDailyEventsToFirebaseOrBraze(this, AppboyConstants.APPBOY_EVENT_DRUGS_VIEWED);
        if (!this.isFromOnCreate) {
            sendOmniturePing();
        }
        this.isFromOnCreate = false;
        if (isSessionValid()) {
            if (Util.getDisplayOrientation(this) != 0 || this.mDrugMonograph == null) {
                onAdNotAvilable();
            } else {
                requestBottomBannerAD(this.mIsViewingToc);
            }
            invalidateOptionsMenu();
            this.mIsItemSelected = false;
            this.showPopUpAd = true;
        }
    }

    private void initialSetup() {
        DrugMonographSectionIndex drugMonographSectionIndex = new DrugMonographSectionIndex(this.mDrugMonograph);
        this.mSectionIndex = drugMonographSectionIndex;
        ArrayList<DrugMonographIndexElement> indexElements = drugMonographSectionIndex.getIndexElements();
        if (Util.isUSuser(this) && isRXorRXOTC(this.mDrugName)) {
            indexElements.add(DrugMonographIndexElement.PRICINGSAVINGS);
        }
        indexElements.add(DrugMonographIndexElement.DISCLAIMER);
    }

    public void onPause() {
        super.onPause();
        this.mHandler.removeCallbacks(this.mAutohideTimer);
        this.mHandler.removeCallbacks(this.mTimer);
        if (this.popUpDialog != null) {
            dismissAdDialog();
        }
    }

    public void requestBottomBannerAD(boolean z) {
        if (this.adLayout == null || !z) {
            this.mIsViewingToc = false;
            if (this.adLayout != null) {
                TextView textView = (TextView) this.adLayout.findViewById(R.id.dfp_adLabel);
                if (textView != null) {
                    textView.setVisibility(0);
                }
                this.screenSpecificMap.put("pc", "content");
            }
        } else {
            this.adLayout.setVisibility(8);
            this.screenSpecificMap.put("pc", "toc");
        }
        Util.setContainerRule(false, this.mContainer, R.id.ad);
        if (this.nativeAdAction != null) {
            this.nativeAdAction.prepADWithCombinedRequests(new INativeDFPAdLoadListener() {
                public void onAdLoaded(NativeDFPAD nativeDFPAD) {
                    if (DrugMonographMainActivity.this.adLayout != null) {
                        DrugMonographMainActivity.this.adLayout.setVisibility(0);
                        ADBindingHelper.Companion.bindCombinedAD(DrugMonographMainActivity.this.adLayout, nativeDFPAD);
                        Util.setContainerRule(true, DrugMonographMainActivity.this.mContainer, R.id.ad);
                    }
                }

                public void onAdFailedToLoad(int i) {
                    DrugMonographMainActivity.this.onAdNotAvilable();
                }
            }, this.nativeAdAction.getBottomBannerADsizes());
            getAd(this.nativeAdAction);
        }
    }

    public void getAd(NativeAdAction nativeAdAction) {
        this.screenSpecificMap.put("pvid", this.mPvid);
        HashMap hashMap = new HashMap();
        hashMap.clear();
        hashMap.putAll(this.screenSpecificMap);
        if (Util.getDisplayOrientation(this) == 0 && !this.isPause) {
            if (nativeAdAction.isSharethrough) {
                hashMap.put("pos", getResources().getString(R.string.sharethrough_ad_pos));
            } else {
                hashMap.put("pos", getResources().getString(R.string.banner_ad_pos));
            }
            this.adBidder.makeADCallWithBidding((Context) this, (HashMap<String, String>) hashMap, nativeAdAction);
        }
    }

    public void getAd(DFPAdAction dFPAdAction) {
        HashMap hashMap = new HashMap();
        hashMap.clear();
        hashMap.putAll(this.screenSpecificMap);
        if (Util.isOnline(this) && Util.getDisplayOrientation(this) == 0 && !this.isPause) {
            hashMap.put("pos", getResources().getString(R.string.sharethrough_ad_pos));
            makeAdRequest(dFPAdAction, hashMap);
        }
    }

    public void setScreenSpecificMap() {
        this.screenSpecificMap.put("pos", getResources().getString(R.string.banner_ad_pos));
        this.screenSpecificMap.put("pc", this.pclass);
        HashMap<String, String> queryDatabase = AdsSegvar.getInstance().queryDatabase(this, getContentId(), 1);
        this.adSegVars = queryDatabase;
        this.assetId = queryDatabase.containsKey("article-id") ? this.adSegVars.get("article-id") : null;
        this.mOmnitureContentData = OmnitureManager.get().getContentBasedOmnitureData(this.adSegVars, -1);
        this.screenSpecificMap.putAll(this.adSegVars);
        HashMap<String, String> hashMap = this.screenSpecificMap;
        hashMap.put("art", "" + getContentId());
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

    public Drug getDrugForInteraction() {
        Drug drug = new Drug();
        drug.setContentId(getContentId());
        drug.setDrugName(this.mDrugName);
        return drug;
    }

    public int getContentId() {
        return this.mContentId;
    }

    public void setmContentId(int i) {
        this.mContentId = i;
    }

    public static void startDrugMonoGraphAvtivity(Context context, int i, String str) {
        Intent intent = new Intent(context, DrugMonographMainActivity.class);
        intent.putExtra(Constants.EXTRA_CONTENT_ID, i);
        intent.putExtra("drugName", str);
        context.startActivity(intent);
    }

    /* access modifiers changed from: protected */
    public void setupActionBar() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(true);
            getSupportActionBar().setDisplayUseLogoEnabled(false);
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        menu.clear();
        getMenuInflater().inflate(getCurrentMenuLayout(), menu);
        return true;
    }

    private int getCurrentMenuLayout() {
        return isContentSaved() ? R.menu.drug_content_page_save_full : R.menu.drug_content_page_save_empty;
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        setMenuMoreOptionVisibility(menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 16908332) {
            return super.onOptionsItemSelected(menuItem);
        }
        onBackPressed();
        return true;
    }

    public boolean onMenuOpened(int i, Menu menu) {
        if (menu != null) {
            OmnitureManager.get().trackModule(this, Constants.OMNITURE_CHANNEL_REFERENCE, "drg-drawer", Constants.OMNITURE_MLINK_OPEN, (Map<String, Object>) null);
        }
        return super.onMenuOpened(i, menu);
    }

    /* access modifiers changed from: protected */
    public String getContentLink() {
        return OldConstants.DRUG_WEB_URL + getContentId();
    }

    /* access modifiers changed from: protected */
    public String getContentTitle() {
        return this.mDrugName;
    }

    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        if (i != 82) {
            return super.onKeyUp(i, keyEvent);
        }
        if (this.mPopupView == null) {
            View view = new View(this);
            this.mPopupView = view;
            ((LinearLayout) this.mRoot).addView(view);
        }
        PopupMenu popupMenu = new PopupMenu(this, this.mPopupView);
        popupMenu.setOnMenuItemClickListener(new AbstractContentViewerActvity.SharePopupMenuItemListener());
        popupMenu.getMenuInflater().inflate(getCurrentMenuLayout(), popupMenu.getMenu());
        setMenuMoreOptionVisibility(popupMenu.getMenu());
        popupMenu.show();
        return true;
    }

    public void setMenuMoreOptionVisibility(Menu menu) {
        MenuItem findItem = menu.findItem(R.id.action_find);
        if (findItem != null) {
            findItem.setVisible(false);
        }
        if (!this.hasInteractions) {
            MenuItem findItem2 = menu.findItem(R.id.action_add_to_interactions);
            if (findItem2 != null) {
                findItem2.setVisible(false);
                return;
            }
            return;
        }
        MenuItem findItem3 = menu.findItem(R.id.action_add_to_interactions);
        SpannableString spannableString = new SpannableString(findItem3.getTitle());
        spannableString.setSpan(new ForegroundColorSpan(ContextCompat.getColor(getApplicationContext(), R.color.black)), 0, spannableString.length(), 0);
        findItem3.setTitle(spannableString);
    }

    private boolean isRXorRXOTC(String str) {
        boolean z;
        if (!StringUtil.isNotEmpty(str)) {
            return false;
        }
        try {
            DatabaseHelper databaseHelper = new DatabaseHelper(this);
            databaseHelper.openDataBase();
            Cursor rawQuery = databaseHelper.getDatabase().rawQuery("SELECT Availability FROM tblDrugs WHERE DrugName = '" + str + "'AND (" + DrugsContract.Drug.GENERIC_ID + "!=0)", (String[]) null);
            z = false;
            while (rawQuery.moveToNext()) {
                try {
                    int i = rawQuery.getInt(rawQuery.getColumnIndex(DrugsContract.Drug.AVAILABILITY));
                    if (i == 1 || i == 4) {
                        z = true;
                    }
                } catch (Exception e) {
                    e = e;
                    LogUtil.e(getClass().getName(), "getMessage =%s", e.getMessage());
                    return z;
                }
            }
            rawQuery.close();
            databaseHelper.close();
        } catch (Exception e2) {
            e = e2;
            z = false;
            LogUtil.e(getClass().getName(), "getMessage =%s", e.getMessage());
            return z;
        }
        return z;
    }

    public void makeNativeDrugManufactureAd() {
        this.mDrugMainFragment.setmanufactureAd(new DrugManufactureAd((NativeDFPAD) null, (DrugManufacturer) null, (SharethroughFallback) null, true));
        NativeAdAction nativeAdAction = new NativeAdAction(this, DFPReferenceAdListener.AD_UNIT_ID, (View) null);
        nativeAdAction.prepADWithCombinedRequests(new INativeDFPAdLoadListener() {
            public void onAdLoaded(NativeDFPAD nativeDFPAD) {
                if (DrugMonographMainActivity.this.mDrugMainFragment != null) {
                    DrugMonographMainActivity.this.mDrugMainFragment.setmanufactureAd(new DrugManufactureAd(nativeDFPAD, (DrugManufacturer) null, (SharethroughFallback) null, false));
                }
            }

            public void onAdFailedToLoad(int i) {
                DrugMonographMainActivity.this.checkManufacturerAd();
            }
        }, (AdSize[]) null, new String[]{"11889305"});
        nativeAdAction.isSharethrough = true;
        getAd(nativeAdAction);
    }

    /* access modifiers changed from: private */
    public void checkManufacturerAd() {
        DrugManufacturer drugManufacturer = UpdateManufacturer.getDrugManufacturer(getContentId());
        if (drugManufacturer != null && drugManufacturer.getExpirationDate() == null) {
            drugManufacturer = null;
        }
        if (!(drugManufacturer == null || drugManufacturer.getExpirationDate() == null || !new Date().after(drugManufacturer.getExpirationDate()))) {
            drugManufacturer = null;
        }
        if (drugManufacturer != null) {
            DrugMonographMainFragment drugMonographMainFragment = this.mDrugMainFragment;
            if (drugMonographMainFragment != null) {
                drugMonographMainFragment.setmanufactureAd(new DrugManufactureAd((NativeDFPAD) null, drugManufacturer, (SharethroughFallback) null, false));
            }
        } else if (CapabilitiesManager.getInstance(this).isSharethroughFeatureAvailable()) {
            makeShareThroughADCall();
        } else {
            loadFallBackAd();
        }
    }

    private void makeShareThroughADCall() {
        NativeAdAction nativeAdAction = new NativeAdAction(this, DFPReferenceAdListener.AD_UNIT_ID, (View) null);
        nativeAdAction.prepADWithCombinedRequests(new INativeDFPAdLoadListener() {
            public void onAdLoaded(NativeDFPAD nativeDFPAD) {
                if (DrugMonographMainActivity.this.mDrugMainFragment != null) {
                    DrugMonographMainActivity.this.mDrugMainFragment.setmanufactureAd(new DrugManufactureAd(nativeDFPAD, (DrugManufacturer) null, (SharethroughFallback) null, false));
                }
            }

            public void onAdFailedToLoad(int i) {
                DrugMonographMainActivity.this.loadFallBackAd();
            }
        }, new AdSize[]{DFPAdAction.ADSIZE_320x80, DFPAdAction.ADSIZE_320x95, DFPAdAction.ADSIZE_1x3});
        nativeAdAction.isSharethrough = true;
        getAd(nativeAdAction);
    }

    /* access modifiers changed from: private */
    public void loadFallBackAd() {
        if (this.mDrugMainFragment != null) {
            this.mDrugMainFragment.setmanufactureAd(new DrugManufactureAd((NativeDFPAD) null, (DrugManufacturer) null, new FallbackManager().getFallbackInfo((Context) this), false));
        }
    }

    public void makePopupAdRequest() {
        PublisherAdView createPopUpAd = AdRequestHelper.createPopUpAd(this);
        this.popUpADView = createPopUpAd;
        if (createPopUpAd != null) {
            createPopUpAd.setVisibility(8);
            getAd(AdRequestHelper.getSharethroughADAction(this, this.popUpADView, new OnAdListener() {
                public void isAdExpandedByUser(boolean z) {
                }

                public void onAdNotAvilable() {
                }

                public void onAdAvailable() {
                    DrugMonographMainActivity.this.showPopupAd();
                }

                public String getCurrentPvid() {
                    return DrugMonographMainActivity.this.mPvid;
                }
            }));
        }
    }

    public void dismissAdDialog() {
        Dialog dialog = this.popUpDialog;
        if (dialog != null) {
            dialog.dismiss();
            PublisherAdView publisherAdView = this.popUpADView;
            if (publisherAdView != null) {
                publisherAdView.destroy();
            }
        }
    }

    public String getmDrugName() {
        return this.mDrugName;
    }

    /* access modifiers changed from: private */
    public void showPopupAd() {
        this.popUpADView.setVisibility(0);
        DrugMonographMainFragment drugMonographMainFragment = this.mDrugMainFragment;
        if (drugMonographMainFragment != null && drugMonographMainFragment.isAdded() && this.mDrugMainFragment.isVisible() && this.showPopUpAd) {
            Dialog dialog = new Dialog(this);
            this.popUpDialog = dialog;
            dialog.requestWindowFeature(1);
            RelativeLayout relativeLayout = (RelativeLayout) LayoutInflater.from(this).inflate(R.layout.transparent_layout, (ViewGroup) null, false);
            relativeLayout.setOnClickListener(new View.OnClickListener() {
                public final void onClick(View view) {
                    DrugMonographMainActivity.this.lambda$showPopupAd$6$DrugMonographMainActivity(view);
                }
            });
            if (this.popUpADView.getParent() != null) {
                ((ViewGroup) this.popUpADView.getParent()).removeAllViews();
            }
            relativeLayout.findViewById(R.id.ad_wrapper).setOnClickListener($$Lambda$DrugMonographMainActivity$L39bCs1QLGmqfoMbOzWsDvtZVE.INSTANCE);
            ((TextView) relativeLayout.findViewById(R.id.close_button)).setOnClickListener(new View.OnClickListener() {
                public final void onClick(View view) {
                    DrugMonographMainActivity.this.lambda$showPopupAd$8$DrugMonographMainActivity(view);
                }
            });
            ((FrameLayout) relativeLayout.findViewById(R.id.ad_content)).addView(this.popUpADView);
            this.popUpDialog.setContentView(relativeLayout);
            Window window = this.popUpDialog.getWindow();
            if (window != null) {
                window.setLayout(-1, -1);
                window.setBackgroundDrawable(new ColorDrawable(0));
            }
            this.popUpDialog.show();
        }
    }

    public /* synthetic */ void lambda$showPopupAd$6$DrugMonographMainActivity(View view) {
        dismissAdDialog();
        HashMap hashMap = new HashMap();
        hashMap.put("wapp.pvid", this.mPvid);
        OmnitureManager.get().trackModuleAbsolute(this, Constants.OMNITURE_CHANNEL_REFERENCE, "man-bg", "dsms", hashMap);
    }

    public /* synthetic */ void lambda$showPopupAd$8$DrugMonographMainActivity(View view) {
        dismissAdDialog();
        HashMap hashMap = new HashMap();
        hashMap.put("wapp.pvid", this.mPvid);
        OmnitureManager.get().trackModuleAbsolute(this, Constants.OMNITURE_CHANNEL_REFERENCE, "man", "dsms", hashMap);
    }

    private void makeAdRequest(DFPAdAction dFPAdAction, HashMap<String, String> hashMap) {
        if (dFPAdAction != null) {
            this.adBidder.makeADCallWithBidding((Context) this, hashMap, dFPAdAction);
        }
    }

    public String getCurrentPvid() {
        return this.mPvid;
    }

    private void sendCPPing() {
        if (getContentId() > 0) {
            ExecutorService executorService = threadPoolExecutor;
            AsyncTaskHelper.execute(executorService, new FireCPEventWithAdsSegvarAsyncTask(this, "drug", "view", "" + getContentId(), OldConstants.DRUG_WEB_URL + this.mDrugName + "-" + getContentId(), (String) null), this.screenSpecificMap);
        }
    }

    /* access modifiers changed from: protected */
    public boolean sendCPPingForValidSearch() {
        if (!StringUtil.isNotEmpty(this.searchQuery)) {
            return false;
        }
        ExecutorService executorService = threadPoolExecutor;
        AsyncTaskHelper.execute(executorService, new FireCPEventWithAdsSegvarAsyncTask(this, "search", "reference", "" + getContentId(), "drugs/browse/view", "{search:" + this.searchQuery + "}"), this.screenSpecificMap);
        return true;
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        dismissAdDialog();
    }
}
