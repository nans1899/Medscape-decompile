package com.medscape.android.reference;

import android.animation.Animator;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProviders;
import com.ib.clickstream.ClickstreamConstants;
import com.ib.clickstream.Impression;
import com.medscape.android.BI.omniture.OmnitureManager;
import com.medscape.android.Constants;
import com.medscape.android.R;
import com.medscape.android.activity.AbstractContentViewerActvity;
import com.medscape.android.analytics.ClickStreamManager;
import com.medscape.android.auth.AuthenticationManager;
import com.medscape.android.contentviewer.ClinicalReferenceArticleLandingFragment;
import com.medscape.android.interfaces.INightModeListener;
import com.medscape.android.provider.SharedPreferenceProvider;
import com.medscape.android.reference.adapters.ReferenceTOCDataAdapter;
import com.medscape.android.reference.interfaces.ISectionItemClickListener;
import com.medscape.android.reference.model.ClinicalReferenceArticle;
import com.medscape.android.reference.viewmodels.ClinicalReferenceArticleViewModel;
import com.medscape.android.util.OldConstants;
import com.medscape.android.util.Util;
import com.wbmd.qxcalculator.model.contentItems.common.Category;
import com.wbmd.qxcalculator.util.legacy.ContentParser;
import com.wbmd.wbmdcommons.utils.ChronicleIDUtil;
import com.webmd.wbmdsimullytics.platform.PlatformRouteDispatcher;
import java.util.HashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010.\u001a\u00020/H\u0002J\b\u00100\u001a\u000201H\u0014J\b\u00102\u001a\u000201H\u0014J\b\u00103\u001a\u000201H\u0014J\b\u00104\u001a\u000205H\u0014J\b\u00106\u001a\u000205H\u0002J\u0012\u00107\u001a\u00020/2\b\u00108\u001a\u0004\u0018\u000101H\u0002J\u0010\u00109\u001a\u00020/2\u0006\u0010+\u001a\u00020:H\u0002J\b\u0010;\u001a\u00020\u001cH\u0014J\b\u0010<\u001a\u00020/H\u0016J\u0012\u0010=\u001a\u00020/2\b\u0010>\u001a\u0004\u0018\u00010?H\u0014J\u0010\u0010@\u001a\u00020\u001c2\u0006\u0010A\u001a\u00020BH\u0016J\u0010\u0010C\u001a\u00020/2\u0006\u0010D\u001a\u00020\u001cH\u0016J\u0010\u0010E\u001a\u00020\u001c2\u0006\u0010F\u001a\u00020GH\u0016J\u0012\u0010H\u001a\u00020\u001c2\b\u0010A\u001a\u0004\u0018\u00010BH\u0016J\b\u0010I\u001a\u00020/H\u0014J\b\u0010J\u001a\u00020/H\u0014J\u0006\u0010K\u001a\u00020/J\u000e\u0010L\u001a\u00020/2\u0006\u0010M\u001a\u000201J\b\u0010N\u001a\u00020/H\u0002J\b\u0010O\u001a\u00020/H\u0014R\u001a\u0010\u0004\u001a\u00020\u0005X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\f\u001a\u00020\rX.¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0012\u001a\u00020\u0013X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001a\u0010\u0018\u001a\u00020\u0005X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0007\"\u0004\b\u001a\u0010\tR\u001a\u0010\u001b\u001a\u00020\u001cX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u001c\u0010 \u001a\u0004\u0018\u00010\u0002X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R\u001a\u0010%\u001a\u00020&X.¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010(\"\u0004\b)\u0010*R\u001a\u0010+\u001a\u00020\u0005X.¢\u0006\u000e\n\u0000\u001a\u0004\b,\u0010\u0007\"\u0004\b-\u0010\t¨\u0006P"}, d2 = {"Lcom/medscape/android/reference/ClinicalReferenceArticleLandingActivity;", "Lcom/medscape/android/activity/AbstractContentViewerActvity;", "Lcom/medscape/android/interfaces/INightModeListener;", "()V", "contentSettingsLayout", "Landroid/widget/RelativeLayout;", "getContentSettingsLayout", "()Landroid/widget/RelativeLayout;", "setContentSettingsLayout", "(Landroid/widget/RelativeLayout;)V", "crLandingViewModel", "Lcom/medscape/android/reference/viewmodels/ClinicalReferenceArticleViewModel;", "fontSeekBar", "Landroid/widget/SeekBar;", "getFontSeekBar", "()Landroid/widget/SeekBar;", "setFontSeekBar", "(Landroid/widget/SeekBar;)V", "fragment", "Lcom/medscape/android/contentviewer/ClinicalReferenceArticleLandingFragment;", "getFragment", "()Lcom/medscape/android/contentviewer/ClinicalReferenceArticleLandingFragment;", "setFragment", "(Lcom/medscape/android/contentviewer/ClinicalReferenceArticleLandingFragment;)V", "functionsLayout", "getFunctionsLayout", "setFunctionsLayout", "isShowingContributors", "", "()Z", "setShowingContributors", "(Z)V", "nightModeListener", "getNightModeListener", "()Lcom/medscape/android/interfaces/INightModeListener;", "setNightModeListener", "(Lcom/medscape/android/interfaces/INightModeListener;)V", "nightSwitch", "Landroidx/appcompat/widget/SwitchCompat;", "getNightSwitch", "()Landroidx/appcompat/widget/SwitchCompat;", "setNightSwitch", "(Landroidx/appcompat/widget/SwitchCompat;)V", "rootView", "getRootView", "setRootView", "fillViews", "", "getContentLink", "", "getContentTeaserForEmail", "getContentTitle", "getMenuItemsLayout", "", "getMenuLayout", "handleTextOptions", "action", "initViews", "Landroid/view/View;", "isContentTitleDisplayed", "onBackPressed", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateOptionsMenu", "menu", "Landroid/view/Menu;", "onNightModeChanged", "isNightModeActive", "onOptionsItemSelected", "item", "Landroid/view/MenuItem;", "onPrepareOptionsMenu", "onResume", "saveContent", "sendOmniturePing", "setTitle", "actionBarTitle", "setUpMenuListeners", "setupActionBar", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: ClinicalReferenceArticleLandingActivity.kt */
public final class ClinicalReferenceArticleLandingActivity extends AbstractContentViewerActvity implements INightModeListener {
    private HashMap _$_findViewCache;
    public RelativeLayout contentSettingsLayout;
    /* access modifiers changed from: private */
    public ClinicalReferenceArticleViewModel crLandingViewModel;
    public SeekBar fontSeekBar;
    public ClinicalReferenceArticleLandingFragment fragment;
    public RelativeLayout functionsLayout;
    private boolean isShowingContributors;
    private INightModeListener nightModeListener;
    public SwitchCompat nightSwitch;
    public RelativeLayout rootView;

    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        View findViewById = findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), findViewById);
        return findViewById;
    }

    /* access modifiers changed from: protected */
    public String getContentTeaserForEmail() {
        return "";
    }

    /* access modifiers changed from: protected */
    public boolean isContentTitleDisplayed() {
        return true;
    }

    public final RelativeLayout getRootView() {
        RelativeLayout relativeLayout = this.rootView;
        if (relativeLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rootView");
        }
        return relativeLayout;
    }

    public final void setRootView(RelativeLayout relativeLayout) {
        Intrinsics.checkNotNullParameter(relativeLayout, "<set-?>");
        this.rootView = relativeLayout;
    }

    public final RelativeLayout getContentSettingsLayout() {
        RelativeLayout relativeLayout = this.contentSettingsLayout;
        if (relativeLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("contentSettingsLayout");
        }
        return relativeLayout;
    }

    public final void setContentSettingsLayout(RelativeLayout relativeLayout) {
        Intrinsics.checkNotNullParameter(relativeLayout, "<set-?>");
        this.contentSettingsLayout = relativeLayout;
    }

    public final RelativeLayout getFunctionsLayout() {
        RelativeLayout relativeLayout = this.functionsLayout;
        if (relativeLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("functionsLayout");
        }
        return relativeLayout;
    }

    public final void setFunctionsLayout(RelativeLayout relativeLayout) {
        Intrinsics.checkNotNullParameter(relativeLayout, "<set-?>");
        this.functionsLayout = relativeLayout;
    }

    public final SeekBar getFontSeekBar() {
        SeekBar seekBar = this.fontSeekBar;
        if (seekBar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fontSeekBar");
        }
        return seekBar;
    }

    public final void setFontSeekBar(SeekBar seekBar) {
        Intrinsics.checkNotNullParameter(seekBar, "<set-?>");
        this.fontSeekBar = seekBar;
    }

    public final SwitchCompat getNightSwitch() {
        SwitchCompat switchCompat = this.nightSwitch;
        if (switchCompat == null) {
            Intrinsics.throwUninitializedPropertyAccessException("nightSwitch");
        }
        return switchCompat;
    }

    public final void setNightSwitch(SwitchCompat switchCompat) {
        Intrinsics.checkNotNullParameter(switchCompat, "<set-?>");
        this.nightSwitch = switchCompat;
    }

    public final ClinicalReferenceArticleLandingFragment getFragment() {
        ClinicalReferenceArticleLandingFragment clinicalReferenceArticleLandingFragment = this.fragment;
        if (clinicalReferenceArticleLandingFragment == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fragment");
        }
        return clinicalReferenceArticleLandingFragment;
    }

    public final void setFragment(ClinicalReferenceArticleLandingFragment clinicalReferenceArticleLandingFragment) {
        Intrinsics.checkNotNullParameter(clinicalReferenceArticleLandingFragment, "<set-?>");
        this.fragment = clinicalReferenceArticleLandingFragment;
    }

    public final INightModeListener getNightModeListener() {
        return this.nightModeListener;
    }

    public final void setNightModeListener(INightModeListener iNightModeListener) {
        this.nightModeListener = iNightModeListener;
    }

    public final boolean isShowingContributors() {
        return this.isShowingContributors;
    }

    public final void setShowingContributors(boolean z) {
        this.isShowingContributors = z;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_landing_clinref_article);
        View findViewById = findViewById(R.id.root_toc);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(R.id.root_toc)");
        this.rootView = (RelativeLayout) findViewById;
        this.crLandingViewModel = (ClinicalReferenceArticleViewModel) ViewModelProviders.of((FragmentActivity) this).get(ClinicalReferenceArticleViewModel.class);
        RelativeLayout relativeLayout = this.rootView;
        if (relativeLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rootView");
        }
        initViews(relativeLayout);
        ClinicalReferenceArticleViewModel clinicalReferenceArticleViewModel = this.crLandingViewModel;
        if (clinicalReferenceArticleViewModel != null) {
            Intent intent = getIntent();
            Intrinsics.checkNotNullExpressionValue(intent, "intent");
            clinicalReferenceArticleViewModel.getIntentData(this, intent);
        }
        setupActionBar();
        setUpMenuListeners();
        this.nightModeListener = this;
        this.fragment = ClinicalReferenceArticleLandingFragment.Companion.newInstance();
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        ClinicalReferenceArticleLandingFragment clinicalReferenceArticleLandingFragment = this.fragment;
        if (clinicalReferenceArticleLandingFragment == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fragment");
        }
        beginTransaction.replace(R.id.toc_container, clinicalReferenceArticleLandingFragment).commit();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        setTitle("");
        fillViews();
    }

    private final void initViews(View view) {
        View findViewById = view.findViewById(R.id.content_setting_layout);
        Intrinsics.checkNotNullExpressionValue(findViewById, "rootView.findViewById(R.id.content_setting_layout)");
        this.contentSettingsLayout = (RelativeLayout) findViewById;
        View findViewById2 = view.findViewById(R.id.content_settings_functions);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "rootView.findViewById(R.…ntent_settings_functions)");
        this.functionsLayout = (RelativeLayout) findViewById2;
        View findViewById3 = view.findViewById(R.id.content_setting_seek_bar);
        Intrinsics.checkNotNullExpressionValue(findViewById3, "rootView.findViewById(R.…content_setting_seek_bar)");
        this.fontSeekBar = (SeekBar) findViewById3;
        View findViewById4 = view.findViewById(R.id.content_settings_night_switch);
        Intrinsics.checkNotNullExpressionValue(findViewById4, "rootView.findViewById(R.…nt_settings_night_switch)");
        this.nightSwitch = (SwitchCompat) findViewById4;
    }

    /* access modifiers changed from: protected */
    public void setupActionBar() {
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayShowTitleEnabled(true);
        }
        ActionBar supportActionBar2 = getSupportActionBar();
        if (supportActionBar2 != null) {
            supportActionBar2.setTitle((CharSequence) "");
        }
        ActionBar supportActionBar3 = getSupportActionBar();
        if (supportActionBar3 != null) {
            supportActionBar3.setDisplayHomeAsUpEnabled(true);
        }
        ActionBar supportActionBar4 = getSupportActionBar();
        if (supportActionBar4 != null) {
            supportActionBar4.setHomeAsUpIndicator((int) R.drawable.ic_arrow_white_24dp);
        }
    }

    public final void setTitle(String str) {
        Intrinsics.checkNotNullParameter(str, "actionBarTitle");
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setTitle((CharSequence) Html.fromHtml("<font color=#ffffff>" + str + "</font>"));
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        Intrinsics.checkNotNullParameter(menu, Category.K_MENU_CATEGORY);
        menu.clear();
        getMenuInflater().inflate(getMenuLayout(), menu);
        return true;
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        Intrinsics.checkNotNull(menu);
        MenuItem findItem = menu.findItem(R.id.action_find);
        Intrinsics.checkNotNullExpressionValue(findItem, "menuFind");
        findItem.setVisible(false);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        Intrinsics.checkNotNullParameter(menuItem, ContentParser.ITEM);
        int itemId = menuItem.getItemId();
        if (itemId == 16908332) {
            onBackPressed();
            return true;
        } else if (itemId != R.id.action_content_setting || this.isShowingContributors) {
            return super.onOptionsItemSelected(menuItem);
        } else {
            RelativeLayout relativeLayout = this.contentSettingsLayout;
            if (relativeLayout == null) {
                Intrinsics.throwUninitializedPropertyAccessException("contentSettingsLayout");
            }
            if (relativeLayout.getVisibility() == 0) {
                handleTextOptions("close");
            } else {
                RelativeLayout relativeLayout2 = this.contentSettingsLayout;
                if (relativeLayout2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("contentSettingsLayout");
                }
                relativeLayout2.setVisibility(0);
                Context context = this;
                OmnitureManager.get().trackModule(context, Constants.OMNITURE_CHANNEL_REFERENCE, "ckb-drawer", Constants.OMNITURE_MLINK_OPEN, (Map<String, Object>) null);
                RelativeLayout relativeLayout3 = this.functionsLayout;
                if (relativeLayout3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("functionsLayout");
                }
                relativeLayout3.animate().translationY(Util.dpToPixel(context, 70)).setListener((Animator.AnimatorListener) null).start();
            }
            return true;
        }
    }

    private final int getMenuLayout() {
        ClinicalReferenceArticle crArticle;
        ClinicalReferenceArticleViewModel clinicalReferenceArticleViewModel;
        ClinicalReferenceArticleViewModel clinicalReferenceArticleViewModel2 = this.crLandingViewModel;
        Boolean bool = null;
        if (!(clinicalReferenceArticleViewModel2 == null || (crArticle = clinicalReferenceArticleViewModel2.getCrArticle()) == null || (clinicalReferenceArticleViewModel = this.crLandingViewModel) == null)) {
            bool = Boolean.valueOf(clinicalReferenceArticleViewModel.isContentSaved(this, crArticle));
        }
        if (bool == null || !bool.booleanValue()) {
            return R.menu.clinical_content_page_save_empty;
        }
        return R.menu.clinical_content_page_save_full;
    }

    /* access modifiers changed from: protected */
    public int getMenuItemsLayout() {
        return getMenuLayout();
    }

    /* access modifiers changed from: protected */
    public void saveContent() {
        ClinicalReferenceArticleViewModel clinicalReferenceArticleViewModel = this.crLandingViewModel;
        if (clinicalReferenceArticleViewModel != null) {
            clinicalReferenceArticleViewModel.saveContent(this, this, clinicalReferenceArticleViewModel != null ? clinicalReferenceArticleViewModel.getCrArticle() : null);
        }
    }

    /* access modifiers changed from: protected */
    public String getContentLink() {
        ClinicalReferenceArticle crArticle;
        ClinicalReferenceArticleViewModel clinicalReferenceArticleViewModel = this.crLandingViewModel;
        Integer num = null;
        if ((clinicalReferenceArticleViewModel != null ? clinicalReferenceArticleViewModel.getCrArticle() : null) == null) {
            return OldConstants.CR_WEB_URL;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(OldConstants.CR_WEB_URL);
        ClinicalReferenceArticleViewModel clinicalReferenceArticleViewModel2 = this.crLandingViewModel;
        if (!(clinicalReferenceArticleViewModel2 == null || (crArticle = clinicalReferenceArticleViewModel2.getCrArticle()) == null)) {
            num = Integer.valueOf(crArticle.getArticleId());
        }
        sb.append(num);
        sb.append("-overview");
        return sb.toString();
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0004, code lost:
        r0 = r0.getCrArticle();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String getContentTitle() {
        /*
            r1 = this;
            com.medscape.android.reference.viewmodels.ClinicalReferenceArticleViewModel r0 = r1.crLandingViewModel
            if (r0 == 0) goto L_0x000f
            com.medscape.android.reference.model.ClinicalReferenceArticle r0 = r0.getCrArticle()
            if (r0 == 0) goto L_0x000f
            java.lang.String r0 = r0.getTitle()
            goto L_0x0010
        L_0x000f:
            r0 = 0
        L_0x0010:
            java.lang.String r0 = java.lang.String.valueOf(r0)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.medscape.android.reference.ClinicalReferenceArticleLandingActivity.getContentTitle():java.lang.String");
    }

    private final void setUpMenuListeners() {
        RelativeLayout relativeLayout = this.contentSettingsLayout;
        if (relativeLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("contentSettingsLayout");
        }
        relativeLayout.setOnClickListener(new ClinicalReferenceArticleLandingActivity$setUpMenuListeners$1(this));
        SeekBar seekBar = this.fontSeekBar;
        if (seekBar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fontSeekBar");
        }
        seekBar.setOnSeekBarChangeListener(new ClinicalReferenceArticleLandingActivity$setUpMenuListeners$2(this));
        SwitchCompat switchCompat = this.nightSwitch;
        if (switchCompat == null) {
            Intrinsics.throwUninitializedPropertyAccessException("nightSwitch");
        }
        switchCompat.setOnClickListener(new ClinicalReferenceArticleLandingActivity$setUpMenuListeners$3(this));
    }

    /* access modifiers changed from: private */
    public final void handleTextOptions(String str) {
        Context context = this;
        OmnitureManager.get().trackModule(context, Constants.OMNITURE_CHANNEL_REFERENCE, "ckb-drawer", str, (Map<String, Object>) null);
        RelativeLayout relativeLayout = this.functionsLayout;
        if (relativeLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("functionsLayout");
        }
        relativeLayout.animate().translationY(Util.dpToPixel(context, -70)).setListener(new ClinicalReferenceArticleLandingActivity$handleTextOptions$1(this)).start();
    }

    public void onNightModeChanged(boolean z) {
        ClinicalReferenceArticleLandingFragment clinicalReferenceArticleLandingFragment = this.fragment;
        if (clinicalReferenceArticleLandingFragment == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fragment");
        }
        clinicalReferenceArticleLandingFragment.setupNightMode(z);
    }

    public final void sendOmniturePing() {
        ClinicalReferenceArticle crArticle;
        ClinicalReferenceArticle crArticle2;
        ClinicalReferenceArticle crArticle3;
        HashMap<String, String> screenSpecificMap;
        HashMap<String, Object> mOmnitureContentData;
        HashMap<String, Object> mOmnitureContentData2;
        ClinicalReferenceArticle crArticle4;
        HashMap<String, Object> mOmnitureContentData3;
        ClinicalReferenceArticleViewModel clinicalReferenceArticleViewModel;
        ClinicalReferenceArticle crArticle5;
        ClinicalReferenceArticleViewModel clinicalReferenceArticleViewModel2 = this.crLandingViewModel;
        Integer num = null;
        if ((clinicalReferenceArticleViewModel2 != null ? clinicalReferenceArticleViewModel2.getCrArticle() : null) != null) {
            ClinicalReferenceArticleViewModel clinicalReferenceArticleViewModel3 = this.crLandingViewModel;
            Integer valueOf = (clinicalReferenceArticleViewModel3 == null || (crArticle5 = clinicalReferenceArticleViewModel3.getCrArticle()) == null) ? null : Integer.valueOf(crArticle5.getArticleId());
            if (valueOf != null && valueOf.intValue() > 0) {
                String str = valueOf + '-' + "toc";
                ChronicleIDUtil chronicleIDUtil = new ChronicleIDUtil();
                String valueOf2 = String.valueOf(valueOf.intValue());
                ClinicalReferenceArticleViewModel clinicalReferenceArticleViewModel4 = this.crLandingViewModel;
                String generateAssetId = chronicleIDUtil.generateAssetId(valueOf2, clinicalReferenceArticleViewModel4 != null ? clinicalReferenceArticleViewModel4.getAssetId() : null, '/' + "clinical-ref-article" + "/view/" + str);
                ClinicalReferenceArticleViewModel clinicalReferenceArticleViewModel5 = this.crLandingViewModel;
                if ((clinicalReferenceArticleViewModel5 != null ? clinicalReferenceArticleViewModel5.getMOmnitureContentData() : null) == null && (clinicalReferenceArticleViewModel = this.crLandingViewModel) != null) {
                    clinicalReferenceArticleViewModel.setMOmnitureContentData(new HashMap());
                }
                ClinicalReferenceArticleViewModel clinicalReferenceArticleViewModel6 = this.crLandingViewModel;
                if (!(clinicalReferenceArticleViewModel6 == null || (mOmnitureContentData3 = clinicalReferenceArticleViewModel6.getMOmnitureContentData()) == null)) {
                    mOmnitureContentData3.put("wapp.asset", String.valueOf(generateAssetId));
                }
                ClinicalReferenceArticleViewModel clinicalReferenceArticleViewModel7 = this.crLandingViewModel;
                if (!(clinicalReferenceArticleViewModel7 == null || (mOmnitureContentData2 = clinicalReferenceArticleViewModel7.getMOmnitureContentData()) == null)) {
                    ClinicalReferenceArticleViewModel clinicalReferenceArticleViewModel8 = this.crLandingViewModel;
                    mOmnitureContentData2.put("wapp.pgtitle", String.valueOf((clinicalReferenceArticleViewModel8 == null || (crArticle4 = clinicalReferenceArticleViewModel8.getCrArticle()) == null) ? null : crArticle4.getTitle()));
                }
                ClinicalReferenceArticleViewModel clinicalReferenceArticleViewModel9 = this.crLandingViewModel;
                if (!(clinicalReferenceArticleViewModel9 == null || (mOmnitureContentData = clinicalReferenceArticleViewModel9.getMOmnitureContentData()) == null)) {
                    mOmnitureContentData.putAll(OmnitureManager.sReferringData);
                }
                OmnitureManager omnitureManager = OmnitureManager.get();
                Context context = this;
                ClinicalReferenceArticleViewModel clinicalReferenceArticleViewModel10 = this.crLandingViewModel;
                this.mPvid = omnitureManager.trackPageView(context, Constants.OMNITURE_CHANNEL_REFERENCE, "clinical-ref-article", "view", str, (String) null, clinicalReferenceArticleViewModel10 != null ? clinicalReferenceArticleViewModel10.getMOmnitureContentData() : null);
                ClinicalReferenceArticleViewModel clinicalReferenceArticleViewModel11 = this.crLandingViewModel;
                if (!(clinicalReferenceArticleViewModel11 == null || (screenSpecificMap = clinicalReferenceArticleViewModel11.getScreenSpecificMap()) == null)) {
                    screenSpecificMap.put("pvid", this.mPvid);
                }
                ClickStreamManager clickStreamManager = ClickStreamManager.INSTANCE;
                ClickstreamConstants.EventType eventType = ClickstreamConstants.EventType.pageView;
                ClinicalReferenceArticleViewModel clinicalReferenceArticleViewModel12 = this.crLandingViewModel;
                String title = (clinicalReferenceArticleViewModel12 == null || (crArticle3 = clinicalReferenceArticleViewModel12.getCrArticle()) == null) ? null : crArticle3.getTitle();
                ClinicalReferenceArticleViewModel clinicalReferenceArticleViewModel13 = this.crLandingViewModel;
                clickStreamManager.sendEvent(context, eventType, title, clinicalReferenceArticleViewModel13 != null ? clinicalReferenceArticleViewModel13.getScreenSpecificMap() : null, (Impression[]) null, (String[]) null, this.mPvid, generateAssetId);
                OmnitureManager omnitureManager2 = OmnitureManager.get();
                ClinicalReferenceArticleViewModel clinicalReferenceArticleViewModel14 = this.crLandingViewModel;
                HashMap<String, String> adSegVars = clinicalReferenceArticleViewModel14 != null ? clinicalReferenceArticleViewModel14.getAdSegVars() : null;
                String str2 = this.mPvid;
                ClinicalReferenceArticleViewModel clinicalReferenceArticleViewModel15 = this.crLandingViewModel;
                omnitureManager2.setReferringData(adSegVars, str2, (clinicalReferenceArticleViewModel15 == null || (crArticle2 = clinicalReferenceArticleViewModel15.getCrArticle()) == null) ? null : crArticle2.getTitle());
                PlatformRouteDispatcher platformRouteDispatcher = new PlatformRouteDispatcher(this, false, true);
                ClinicalReferenceArticleViewModel clinicalReferenceArticleViewModel16 = this.crLandingViewModel;
                if (!(clinicalReferenceArticleViewModel16 == null || (crArticle = clinicalReferenceArticleViewModel16.getCrArticle()) == null)) {
                    num = Integer.valueOf(crArticle.getArticleId());
                }
                platformRouteDispatcher.routeEvent(String.valueOf(num));
            }
        }
    }

    private final void fillViews() {
        ReferenceTOCDataAdapter adapter;
        ReferenceTOCDataAdapter adapter2;
        ReferenceTOCDataAdapter adapter3;
        MutableLiveData<Integer> textSize;
        SharedPreferenceProvider sharedPreferenceProvider = SharedPreferenceProvider.get();
        StringBuilder sb = new StringBuilder();
        sb.append(Constants.PREF_REFERENCE_NIGHT_MODE);
        Context context = this;
        AuthenticationManager instance = AuthenticationManager.getInstance(context);
        Intrinsics.checkNotNullExpressionValue(instance, "AuthenticationManager.getInstance(this)");
        sb.append(instance.getMaskedGuid());
        boolean z = false;
        int i = sharedPreferenceProvider.get(sb.toString(), 0);
        SharedPreferenceProvider sharedPreferenceProvider2 = SharedPreferenceProvider.get();
        StringBuilder sb2 = new StringBuilder();
        sb2.append(Constants.PREF_REFERENCE_TEXT_SIZE_INDEX);
        AuthenticationManager instance2 = AuthenticationManager.getInstance(context);
        Intrinsics.checkNotNullExpressionValue(instance2, "AuthenticationManager.getInstance(this)");
        sb2.append(instance2.getMaskedGuid());
        int i2 = sharedPreferenceProvider2.get(sb2.toString(), -1);
        if (i2 > -1) {
            SeekBar seekBar = this.fontSeekBar;
            if (seekBar == null) {
                Intrinsics.throwUninitializedPropertyAccessException("fontSeekBar");
            }
            seekBar.setProgress(i2);
            ClinicalReferenceArticleViewModel clinicalReferenceArticleViewModel = this.crLandingViewModel;
            if (!(clinicalReferenceArticleViewModel == null || (textSize = clinicalReferenceArticleViewModel.getTextSize()) == null)) {
                textSize.setValue(Integer.valueOf(i2));
            }
            ClinicalReferenceArticleViewModel clinicalReferenceArticleViewModel2 = this.crLandingViewModel;
            if (!(clinicalReferenceArticleViewModel2 == null || (adapter3 = clinicalReferenceArticleViewModel2.getAdapter(context, (ISectionItemClickListener) null)) == null)) {
                adapter3.setTextSizeIndex(i2);
            }
        }
        if (i == 1) {
            SwitchCompat switchCompat = this.nightSwitch;
            if (switchCompat == null) {
                Intrinsics.throwUninitializedPropertyAccessException("nightSwitch");
            }
            switchCompat.setChecked(true);
            ClinicalReferenceArticleViewModel clinicalReferenceArticleViewModel3 = this.crLandingViewModel;
            if (!(clinicalReferenceArticleViewModel3 == null || (adapter2 = clinicalReferenceArticleViewModel3.getAdapter(context, (ISectionItemClickListener) null)) == null)) {
                adapter2.setNightMode(true);
            }
            RelativeLayout relativeLayout = this.rootView;
            if (relativeLayout == null) {
                Intrinsics.throwUninitializedPropertyAccessException("rootView");
            }
            relativeLayout.setBackgroundColor(getResources().getColor(R.color.black));
        } else {
            SwitchCompat switchCompat2 = this.nightSwitch;
            if (switchCompat2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("nightSwitch");
            }
            switchCompat2.setChecked(false);
            ClinicalReferenceArticleViewModel clinicalReferenceArticleViewModel4 = this.crLandingViewModel;
            if (!(clinicalReferenceArticleViewModel4 == null || (adapter = clinicalReferenceArticleViewModel4.getAdapter(context, (ISectionItemClickListener) null)) == null)) {
                adapter.setNightMode(false);
            }
            RelativeLayout relativeLayout2 = this.rootView;
            if (relativeLayout2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("rootView");
            }
            relativeLayout2.setBackgroundColor(getResources().getColor(R.color.white));
        }
        INightModeListener iNightModeListener = this.nightModeListener;
        if (iNightModeListener != null && iNightModeListener != null) {
            if (i == 1) {
                z = true;
            }
            iNightModeListener.onNightModeChanged(z);
        }
    }

    public void onBackPressed() {
        super.onBackPressed();
        if (this.isShowingContributors) {
            this.isShowingContributors = false;
            fillViews();
        }
    }
}
