package com.medscape.android.activity.saved.views;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.ActionBar;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.medscape.android.BI.omniture.OmnitureManager;
import com.medscape.android.Constants;
import com.medscape.android.R;
import com.medscape.android.activity.saved.adapters.SavedTabLayoutAdapter;
import com.medscape.android.activity.saved.adapters.ViewPagerAdapter;
import com.medscape.android.activity.saved.model.TabLayoutElement;
import com.medscape.android.activity.saved.viewmodel.SavedFeedViewModel;
import com.medscape.android.appboy.AppboyEventsHandler;
import com.medscape.android.base.NavigableBaseActivity;
import com.medscape.android.util.constants.AppboyConstants;
import java.util.HashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010'\u001a\u00020(H\u0016J\u0012\u0010)\u001a\u00020(2\b\u0010*\u001a\u0004\u0018\u00010+H\u0014J\b\u0010,\u001a\u00020(H\u0014J\b\u0010-\u001a\u00020(H\u0014J \u0010.\u001a\u00020(2\u0006\u0010/\u001a\u00020\u00052\u0006\u00100\u001a\u0002012\u0006\u00102\u001a\u00020\u0013H\u0016J\b\u00103\u001a\u00020(H\u0015J\b\u00104\u001a\u00020(H\u0002J\b\u00105\u001a\u00020(H\u0002J\b\u00106\u001a\u00020(H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u00020\u0007X.¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u000e\u0010\f\u001a\u00020\rX.¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX.¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X.¢\u0006\u0002\n\u0000R\u001a\u0010\u0012\u001a\u00020\u0013X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001a\u0010\u0018\u001a\u00020\u0002X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u001a\u0010\u001d\u001a\u00020\u001eX.¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R\u000e\u0010#\u001a\u00020$X.¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020&X\u000e¢\u0006\u0002\n\u0000¨\u00067"}, d2 = {"Lcom/medscape/android/activity/saved/views/SaveActivity;", "Lcom/medscape/android/base/NavigableBaseActivity;", "Lcom/medscape/android/activity/saved/adapters/SavedTabLayoutAdapter$OnTabClicked;", "()V", "activePosition", "", "delete", "Landroid/widget/ImageView;", "getDelete", "()Landroid/widget/ImageView;", "setDelete", "(Landroid/widget/ImageView;)V", "emptyView", "Landroid/widget/LinearLayout;", "homeLayoutManager", "Landroidx/recyclerview/widget/LinearLayoutManager;", "saveViewModel", "Lcom/medscape/android/activity/saved/viewmodel/SavedFeedViewModel;", "tabClicked", "", "getTabClicked", "()Z", "setTabClicked", "(Z)V", "tabListener", "getTabListener", "()Lcom/medscape/android/activity/saved/adapters/SavedTabLayoutAdapter$OnTabClicked;", "setTabListener", "(Lcom/medscape/android/activity/saved/adapters/SavedTabLayoutAdapter$OnTabClicked;)V", "tabsLayout", "Landroidx/recyclerview/widget/RecyclerView;", "getTabsLayout", "()Landroidx/recyclerview/widget/RecyclerView;", "setTabsLayout", "(Landroidx/recyclerview/widget/RecyclerView;)V", "viewPager", "Landroidx/viewpager/widget/ViewPager;", "voiceQuery", "", "onBackPressed", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onPause", "onResume", "onTabClicked", "position", "selectedElement", "Lcom/medscape/android/activity/saved/model/TabLayoutElement;", "fromTab", "setupActionBar", "setupDelete", "setupSaved", "setupTabs", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: SaveActivity.kt */
public final class SaveActivity extends NavigableBaseActivity implements SavedTabLayoutAdapter.OnTabClicked {
    private HashMap _$_findViewCache;
    private int activePosition;
    public ImageView delete;
    /* access modifiers changed from: private */
    public LinearLayout emptyView;
    private LinearLayoutManager homeLayoutManager;
    /* access modifiers changed from: private */
    public SavedFeedViewModel saveViewModel;
    private boolean tabClicked;
    public SavedTabLayoutAdapter.OnTabClicked tabListener;
    public RecyclerView tabsLayout;
    /* access modifiers changed from: private */
    public ViewPager viewPager;
    private String voiceQuery = "";

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

    public static final /* synthetic */ LinearLayout access$getEmptyView$p(SaveActivity saveActivity) {
        LinearLayout linearLayout = saveActivity.emptyView;
        if (linearLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("emptyView");
        }
        return linearLayout;
    }

    public static final /* synthetic */ SavedFeedViewModel access$getSaveViewModel$p(SaveActivity saveActivity) {
        SavedFeedViewModel savedFeedViewModel = saveActivity.saveViewModel;
        if (savedFeedViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("saveViewModel");
        }
        return savedFeedViewModel;
    }

    public static final /* synthetic */ ViewPager access$getViewPager$p(SaveActivity saveActivity) {
        ViewPager viewPager2 = saveActivity.viewPager;
        if (viewPager2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewPager");
        }
        return viewPager2;
    }

    public void onTabClicked(int i, TabLayoutElement tabLayoutElement, boolean z) {
        Intrinsics.checkNotNullParameter(tabLayoutElement, "selectedElement");
        SavedFeedViewModel savedFeedViewModel = this.saveViewModel;
        if (savedFeedViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("saveViewModel");
        }
        for (TabLayoutElement next : savedFeedViewModel.getTabElements$medscape_release()) {
            next.setSelected(Intrinsics.areEqual((Object) next.getName(), (Object) tabLayoutElement.getName()));
        }
        this.tabClicked = z;
        SavedFeedViewModel savedFeedViewModel2 = this.saveViewModel;
        if (savedFeedViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("saveViewModel");
        }
        savedFeedViewModel2.getTabsAdapter().notifyDataSetChanged();
        ViewPager viewPager2 = this.viewPager;
        if (viewPager2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewPager");
        }
        viewPager2.setCurrentItem(i);
    }

    public final RecyclerView getTabsLayout() {
        RecyclerView recyclerView = this.tabsLayout;
        if (recyclerView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tabsLayout");
        }
        return recyclerView;
    }

    public final void setTabsLayout(RecyclerView recyclerView) {
        Intrinsics.checkNotNullParameter(recyclerView, "<set-?>");
        this.tabsLayout = recyclerView;
    }

    public final ImageView getDelete() {
        ImageView imageView = this.delete;
        if (imageView == null) {
            Intrinsics.throwUninitializedPropertyAccessException(Constants.OMNITURE_MLINK_UNSAVE);
        }
        return imageView;
    }

    public final void setDelete(ImageView imageView) {
        Intrinsics.checkNotNullParameter(imageView, "<set-?>");
        this.delete = imageView;
    }

    public final SavedTabLayoutAdapter.OnTabClicked getTabListener() {
        SavedTabLayoutAdapter.OnTabClicked onTabClicked = this.tabListener;
        if (onTabClicked == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tabListener");
        }
        return onTabClicked;
    }

    public final void setTabListener(SavedTabLayoutAdapter.OnTabClicked onTabClicked) {
        Intrinsics.checkNotNullParameter(onTabClicked, "<set-?>");
        this.tabListener = onTabClicked;
    }

    public final boolean getTabClicked() {
        return this.tabClicked;
    }

    public final void setTabClicked(boolean z) {
        this.tabClicked = z;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        SavedFeedViewModel savedFeedViewModel = (SavedFeedViewModel) new ViewModelProvider(this).get(new SavedFeedViewModel().getClass());
        this.saveViewModel = savedFeedViewModel;
        if (savedFeedViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("saveViewModel");
        }
        savedFeedViewModel.getAllSavedArticles(this);
        setContentView((int) R.layout.activity_saved_articles);
        View findViewById = findViewById(R.id.view_pager);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(R.id.view_pager)");
        ViewPager viewPager2 = (ViewPager) findViewById;
        this.viewPager = viewPager2;
        if (viewPager2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewPager");
        }
        viewPager2.setOffscreenPageLimit(8);
        View findViewById2 = findViewById(R.id.empty_layout);
        Intrinsics.checkNotNullExpressionValue(findViewById2, "findViewById(R.id.empty_layout)");
        this.emptyView = (LinearLayout) findViewById2;
        Intent intent = getIntent();
        Intrinsics.checkNotNullExpressionValue(intent, "this.intent");
        if (intent.hasExtra("editMode")) {
            SavedFeedViewModel savedFeedViewModel2 = this.saveViewModel;
            if (savedFeedViewModel2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("saveViewModel");
            }
            savedFeedViewModel2.setEditModeActive(intent.getBooleanExtra("editMode", false));
            this.activePosition = intent.getIntExtra("activePosition", 0);
        }
        if (intent.hasExtra("voice_query") && intent.getStringExtra("voice_query") != null) {
            String stringExtra = intent.getStringExtra("voice_query");
            Intrinsics.checkNotNull(stringExtra);
            this.voiceQuery = stringExtra;
        }
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        if (this.voiceQuery.length() > 0) {
            finish();
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        setupSaved();
    }

    private final void setupSaved() {
        SavedFeedViewModel savedFeedViewModel = (SavedFeedViewModel) new ViewModelProvider(this).get(new SavedFeedViewModel().getClass());
        this.saveViewModel = savedFeedViewModel;
        if (savedFeedViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("saveViewModel");
        }
        Context context = this;
        savedFeedViewModel.getAllSavedArticles(context);
        this.mPvid = OmnitureManager.get().trackPageView(context, "other", com.webmd.wbmdcmepulse.models.utils.Constants.SUBSCRIPTION_TYPE_SAVED, "view-all", (String) null, (String) null, (Map<String, Object>) null);
        AppboyEventsHandler.logDailyEvent(context, AppboyConstants.APPBOY_EVENT_SAVED_VIEWED, this);
        this.tabListener = this;
        ViewPager viewPager2 = this.viewPager;
        if (viewPager2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewPager");
        }
        viewPager2.addOnPageChangeListener(new SaveActivity$setupSaved$1(this));
        SavedFeedViewModel savedFeedViewModel2 = this.saveViewModel;
        if (savedFeedViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("saveViewModel");
        }
        savedFeedViewModel2.getCountLiveFlag().observe(this, new SaveActivity$setupSaved$2(this));
        SavedFeedViewModel savedFeedViewModel3 = this.saveViewModel;
        if (savedFeedViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("saveViewModel");
        }
        if (savedFeedViewModel3.getSavedList().size() > 0) {
            setupTabs();
            setupDelete();
            SavedFeedViewModel savedFeedViewModel4 = this.saveViewModel;
            if (savedFeedViewModel4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("saveViewModel");
            }
            FragmentManager supportFragmentManager = getSupportFragmentManager();
            Intrinsics.checkNotNullExpressionValue(supportFragmentManager, "supportFragmentManager");
            SavedFeedViewModel savedFeedViewModel5 = this.saveViewModel;
            if (savedFeedViewModel5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("saveViewModel");
            }
            savedFeedViewModel4.setViewPagerAdapter(new ViewPagerAdapter(supportFragmentManager, savedFeedViewModel5.getTabElements$medscape_release()));
            SavedFeedViewModel savedFeedViewModel6 = this.saveViewModel;
            if (savedFeedViewModel6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("saveViewModel");
            }
            savedFeedViewModel6.getViewPagerAdapter().notifyDataSetChanged();
            ViewPager viewPager3 = this.viewPager;
            if (viewPager3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewPager");
            }
            SavedFeedViewModel savedFeedViewModel7 = this.saveViewModel;
            if (savedFeedViewModel7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("saveViewModel");
            }
            viewPager3.setAdapter(savedFeedViewModel7.getViewPagerAdapter());
            SavedFeedViewModel savedFeedViewModel8 = this.saveViewModel;
            if (savedFeedViewModel8 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("saveViewModel");
            }
            if (savedFeedViewModel8.getEditModeActive()) {
                ViewPager viewPager4 = this.viewPager;
                if (viewPager4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewPager");
                }
                viewPager4.setCurrentItem(this.activePosition);
            }
            RecyclerView recyclerView = this.tabsLayout;
            if (recyclerView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("tabsLayout");
            }
            recyclerView.setVisibility(0);
            ViewPager viewPager5 = this.viewPager;
            if (viewPager5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewPager");
            }
            viewPager5.setVisibility(0);
            LinearLayout linearLayout = this.emptyView;
            if (linearLayout == null) {
                Intrinsics.throwUninitializedPropertyAccessException("emptyView");
            }
            linearLayout.setVisibility(8);
            return;
        }
        RecyclerView recyclerView2 = this.tabsLayout;
        if (recyclerView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tabsLayout");
        }
        recyclerView2.setVisibility(8);
        ViewPager viewPager6 = this.viewPager;
        if (viewPager6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewPager");
        }
        viewPager6.setVisibility(8);
        LinearLayout linearLayout2 = this.emptyView;
        if (linearLayout2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("emptyView");
        }
        linearLayout2.setVisibility(0);
        ImageView imageView = this.delete;
        if (imageView == null) {
            Intrinsics.throwUninitializedPropertyAccessException(Constants.OMNITURE_MLINK_UNSAVE);
        }
        imageView.setVisibility(8);
    }

    private final void setupDelete() {
        SavedFeedViewModel savedFeedViewModel = this.saveViewModel;
        if (savedFeedViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("saveViewModel");
        }
        if (savedFeedViewModel.getSavedList().size() > 0) {
            ImageView imageView = this.delete;
            if (imageView == null) {
                Intrinsics.throwUninitializedPropertyAccessException(Constants.OMNITURE_MLINK_UNSAVE);
            }
            imageView.setVisibility(0);
        } else {
            ImageView imageView2 = this.delete;
            if (imageView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(Constants.OMNITURE_MLINK_UNSAVE);
            }
            imageView2.setVisibility(8);
        }
        SavedFeedViewModel savedFeedViewModel2 = this.saveViewModel;
        if (savedFeedViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("saveViewModel");
        }
        if (!savedFeedViewModel2.getEditModeActive()) {
            ImageView imageView3 = this.delete;
            if (imageView3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(Constants.OMNITURE_MLINK_UNSAVE);
            }
            imageView3.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.ic_delete_selected, (Resources.Theme) null));
            return;
        }
        ImageView imageView4 = this.delete;
        if (imageView4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(Constants.OMNITURE_MLINK_UNSAVE);
        }
        imageView4.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.consult_check, (Resources.Theme) null));
    }

    private final void setupTabs() {
        SavedFeedViewModel savedFeedViewModel = this.saveViewModel;
        if (savedFeedViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("saveViewModel");
        }
        Context context = this;
        SavedFeedViewModel savedFeedViewModel2 = this.saveViewModel;
        if (savedFeedViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("saveViewModel");
        }
        savedFeedViewModel.setTabsAdapter(new SavedTabLayoutAdapter(context, savedFeedViewModel2.getTabElements$medscape_release(), this));
        this.homeLayoutManager = new LinearLayoutManager(context, 0, false);
        RecyclerView recyclerView = this.tabsLayout;
        if (recyclerView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tabsLayout");
        }
        LinearLayoutManager linearLayoutManager = this.homeLayoutManager;
        if (linearLayoutManager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("homeLayoutManager");
        }
        recyclerView.setLayoutManager(linearLayoutManager);
        RecyclerView recyclerView2 = this.tabsLayout;
        if (recyclerView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tabsLayout");
        }
        SavedFeedViewModel savedFeedViewModel3 = this.saveViewModel;
        if (savedFeedViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("saveViewModel");
        }
        recyclerView2.setAdapter(savedFeedViewModel3.getTabsAdapter());
    }

    /* access modifiers changed from: protected */
    public void setupActionBar() {
        super.setupActionBar();
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setTitle((CharSequence) "");
            Context context = this;
            supportActionBar.setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(context, R.color.medscape_blue)));
            supportActionBar.setDisplayHomeAsUpEnabled(false);
            View inflate = LayoutInflater.from(context).inflate(R.layout.saved_action_bar, (ViewGroup) null);
            TextView textView = (TextView) inflate.findViewById(R.id.header_title);
            View findViewById = inflate.findViewById(R.id.delete_icon);
            Intrinsics.checkNotNullExpressionValue(findViewById, "customView.findViewById(R.id.delete_icon)");
            this.delete = (ImageView) findViewById;
            View findViewById2 = inflate.findViewById(R.id.tabs_list);
            Intrinsics.checkNotNullExpressionValue(findViewById2, "customView.findViewById(R.id.tabs_list)");
            this.tabsLayout = (RecyclerView) findViewById2;
            Intrinsics.checkNotNullExpressionValue(textView, "title");
            textView.setText(getString(R.string.saved));
            ((ImageView) inflate.findViewById(R.id.back_arrow)).setOnClickListener(new SaveActivity$setupActionBar$1(this));
            SavedFeedViewModel savedFeedViewModel = this.saveViewModel;
            if (savedFeedViewModel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("saveViewModel");
            }
            if (savedFeedViewModel.getSavedList().size() > 0) {
                ImageView imageView = this.delete;
                if (imageView == null) {
                    Intrinsics.throwUninitializedPropertyAccessException(Constants.OMNITURE_MLINK_UNSAVE);
                }
                imageView.setVisibility(0);
            } else {
                ImageView imageView2 = this.delete;
                if (imageView2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException(Constants.OMNITURE_MLINK_UNSAVE);
                }
                imageView2.setVisibility(8);
            }
            ImageView imageView3 = this.delete;
            if (imageView3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(Constants.OMNITURE_MLINK_UNSAVE);
            }
            imageView3.setOnClickListener(new SaveActivity$setupActionBar$2(this));
            supportActionBar.setCustomView(inflate);
        }
    }

    public void onBackPressed() {
        SavedFeedViewModel savedFeedViewModel = this.saveViewModel;
        if (savedFeedViewModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("saveViewModel");
        }
        if (savedFeedViewModel.getEditModeActive()) {
            ImageView imageView = this.delete;
            if (imageView == null) {
                Intrinsics.throwUninitializedPropertyAccessException(Constants.OMNITURE_MLINK_UNSAVE);
            }
            imageView.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.ic_delete_selected, (Resources.Theme) null));
            SavedFeedViewModel savedFeedViewModel2 = this.saveViewModel;
            if (savedFeedViewModel2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("saveViewModel");
            }
            savedFeedViewModel2.setEditModeActive(false);
            ViewPager viewPager2 = this.viewPager;
            if (viewPager2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewPager");
            }
            PagerAdapter adapter = viewPager2.getAdapter();
            if (adapter != null) {
                adapter.notifyDataSetChanged();
                return;
            }
            return;
        }
        super.onBackPressed();
    }
}
