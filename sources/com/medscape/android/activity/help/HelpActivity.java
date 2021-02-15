package com.medscape.android.activity.help;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.medscape.android.BI.omniture.OmnitureManager;
import com.medscape.android.MedscapeMenu;
import com.medscape.android.R;
import com.medscape.android.activity.help.adapters.HelpAdapter;
import com.medscape.android.activity.login.LoginManager;
import com.medscape.android.appboy.AppboyEventsHandler;
import com.medscape.android.base.BaseActivity;
import com.medscape.android.capabilities.CapabilitiesManager;
import com.medscape.android.contentviewer.interfaces.IDataListItemClickListener;
import com.medscape.android.myinvites.MyInvitationsManager;
import com.medscape.android.task.GetOpenMyInvitationsCount;
import com.medscape.android.util.constants.AppboyConstants;
import com.wbmd.qxcalculator.util.legacy.ContentParser;
import java.util.HashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\u0019\u001a\u00020\u001aH\u0002J\"\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001d2\b\u0010\u001f\u001a\u0004\u0018\u00010 H\u0014J\u0012\u0010!\u001a\u00020\u001a2\b\u0010\"\u001a\u0004\u0018\u00010#H\u0014J\u0010\u0010$\u001a\u00020\u001a2\u0006\u0010%\u001a\u00020\u001dH\u0016J\u0012\u0010&\u001a\u00020\u001a2\b\u0010'\u001a\u0004\u0018\u00010(H\u0016J\b\u0010)\u001a\u00020\u001aH\u0016J\b\u0010*\u001a\u00020\u001aH\u0016J\u0010\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020.H\u0016J\u0010\u0010/\u001a\u00020\u001a2\u0006\u00100\u001a\u00020\u001dH\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\u00020\bX.¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001a\u0010\r\u001a\u00020\u000eX.¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0013\u001a\u00020\u0014X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018¨\u00061"}, d2 = {"Lcom/medscape/android/activity/help/HelpActivity;", "Lcom/medscape/android/base/BaseActivity;", "Lcom/medscape/android/contentviewer/interfaces/IDataListItemClickListener;", "Lcom/medscape/android/task/GetOpenMyInvitationsCount$GetOpenMyInvitationsCountListener;", "()V", "currentLanguage", "", "dataProvider", "Lcom/medscape/android/activity/help/HelpDataProvider;", "getDataProvider", "()Lcom/medscape/android/activity/help/HelpDataProvider;", "setDataProvider", "(Lcom/medscape/android/activity/help/HelpDataProvider;)V", "mAdapter", "Lcom/medscape/android/activity/help/adapters/HelpAdapter;", "getMAdapter", "()Lcom/medscape/android/activity/help/adapters/HelpAdapter;", "setMAdapter", "(Lcom/medscape/android/activity/help/adapters/HelpAdapter;)V", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "getRecyclerView", "()Landroidx/recyclerview/widget/RecyclerView;", "setRecyclerView", "(Landroidx/recyclerview/widget/RecyclerView;)V", "checkForInvitations", "", "onActivityResult", "requestCode", "", "resultCode", "data", "Landroid/content/Intent;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDataListItemClicked", "position", "onGetOpenMyInvitationsCountComplete", "json", "Lorg/json/JSONObject;", "onGetOpenMyInvitationsCountError", "onGetOpenMyInvitationsCountNoResults", "onOptionsItemSelected", "", "item", "Landroid/view/MenuItem;", "setInvitationsCountIcon", "invites", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: HelpActivity.kt */
public final class HelpActivity extends BaseActivity implements IDataListItemClickListener, GetOpenMyInvitationsCount.GetOpenMyInvitationsCountListener {
    private HashMap _$_findViewCache;
    private String currentLanguage = "English";
    public HelpDataProvider dataProvider;
    public HelpAdapter mAdapter;
    public RecyclerView recyclerView;

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

    public void onGetOpenMyInvitationsCountError() {
    }

    public final RecyclerView getRecyclerView() {
        RecyclerView recyclerView2 = this.recyclerView;
        if (recyclerView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("recyclerView");
        }
        return recyclerView2;
    }

    public final void setRecyclerView(RecyclerView recyclerView2) {
        Intrinsics.checkNotNullParameter(recyclerView2, "<set-?>");
        this.recyclerView = recyclerView2;
    }

    public final HelpAdapter getMAdapter() {
        HelpAdapter helpAdapter = this.mAdapter;
        if (helpAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mAdapter");
        }
        return helpAdapter;
    }

    public final void setMAdapter(HelpAdapter helpAdapter) {
        Intrinsics.checkNotNullParameter(helpAdapter, "<set-?>");
        this.mAdapter = helpAdapter;
    }

    public final HelpDataProvider getDataProvider() {
        HelpDataProvider helpDataProvider = this.dataProvider;
        if (helpDataProvider == null) {
            Intrinsics.throwUninitializedPropertyAccessException("dataProvider");
        }
        return helpDataProvider;
    }

    public final void setDataProvider(HelpDataProvider helpDataProvider) {
        Intrinsics.checkNotNullParameter(helpDataProvider, "<set-?>");
        this.dataProvider = helpDataProvider;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_help);
        checkForInvitations();
        View findViewById = findViewById(R.id.helptoolbar);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById(R.id.helptoolbar)");
        setSupportActionBar((Toolbar) findViewById);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        ActionBar supportActionBar2 = getSupportActionBar();
        if (supportActionBar2 != null) {
            supportActionBar2.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.medscape_blue)));
        }
        if (supportActionBar2 != null) {
            supportActionBar2.setTitle((CharSequence) getResources().getString(R.string.help_activity_settings));
        }
        Context context = this;
        HelpDataProvider helpDataProvider = new HelpDataProvider(context, (CapabilitiesManager) null, 2, (DefaultConstructorMarker) null);
        this.dataProvider = helpDataProvider;
        Activity activity = this;
        if (helpDataProvider == null) {
            Intrinsics.throwUninitializedPropertyAccessException("dataProvider");
        }
        this.mAdapter = new HelpAdapter(activity, helpDataProvider.getItemList(), this);
        View findViewById2 = findViewById(R.id.help_activity_recycler_view);
        RecyclerView recyclerView2 = (RecyclerView) findViewById2;
        recyclerView2.setHasFixedSize(true);
        recyclerView2.setLayoutManager(new LinearLayoutManager(context));
        HelpAdapter helpAdapter = this.mAdapter;
        if (helpAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mAdapter");
        }
        recyclerView2.setAdapter(helpAdapter);
        Unit unit = Unit.INSTANCE;
        Intrinsics.checkNotNullExpressionValue(findViewById2, "findViewById<androidx.re…pter = mAdapter\n        }");
        this.recyclerView = recyclerView2;
        AppboyEventsHandler.logDailyEvent(context, AppboyConstants.APPBOY_EVENT_PROFILE_VIEWED, activity);
    }

    public void onDataListItemClicked(int i) {
        HelpDataProvider helpDataProvider = this.dataProvider;
        if (helpDataProvider == null) {
            Intrinsics.throwUninitializedPropertyAccessException("dataProvider");
        }
        if (helpDataProvider.isLogOutWarningActive()) {
            HelpDataProvider helpDataProvider2 = this.dataProvider;
            if (helpDataProvider2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("dataProvider");
            }
            helpDataProvider2.setLogOutState(false);
            HelpAdapter helpAdapter = this.mAdapter;
            if (helpAdapter == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mAdapter");
            }
            helpAdapter.notifyDataSetChanged();
            return;
        }
        HelpDataProvider helpDataProvider3 = this.dataProvider;
        if (helpDataProvider3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("dataProvider");
        }
        String text = helpDataProvider3.getSelectedElement(i).getText();
        if (Intrinsics.areEqual((Object) text, (Object) getString(R.string.drawer_title_consult_profile))) {
            StringBuilder sb = new StringBuilder();
            sb.append("profile/");
            Context context = this;
            sb.append(LoginManager.getStoredGUID(context));
            OmnitureManager.get().trackPageView(context, "consult", "consult", sb.toString(), (String) null, (String) null, (Map<String, Object>) null);
            ProgressBar progressBar = (ProgressBar) _$_findCachedViewById(R.id.help_loading);
            Intrinsics.checkNotNullExpressionValue(progressBar, "help_loading");
            progressBar.setVisibility(0);
            CapabilitiesManager.getInstance(context).startConsultProfileEluaCheck(context, this, (ProgressBar) _$_findCachedViewById(R.id.help_loading));
        } else if (Intrinsics.areEqual((Object) text, (Object) getString(R.string.title_content_preference))) {
            MedscapeMenu.onItemSelected(this, 13);
        } else if (Intrinsics.areEqual((Object) text, (Object) getString(R.string.drawer_title_data))) {
            MedscapeMenu.onItemSelected(this, 10);
        } else if (Intrinsics.areEqual((Object) text, (Object) getString(R.string.drawer_title_calculators))) {
            MedscapeMenu.onItemSelected(this, 23);
        } else if (Intrinsics.areEqual((Object) text, (Object) getString(R.string.drawer_title_notifications))) {
            MedscapeMenu.onItemSelected(this, 20);
        } else if (Intrinsics.areEqual((Object) text, (Object) getString(R.string.drawer_title_help))) {
            MedscapeMenu.onItemSelected(this, 9);
        } else if (Intrinsics.areEqual((Object) text, (Object) getString(R.string.drawer_title_personal_information))) {
            MedscapeMenu.onItemSelected(this, 24);
        } else if (Intrinsics.areEqual((Object) text, (Object) getString(R.string.title_logout_preference))) {
            HelpDataProvider helpDataProvider4 = this.dataProvider;
            if (helpDataProvider4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("dataProvider");
            }
            helpDataProvider4.setLogOutState(true);
            HelpAdapter helpAdapter2 = this.mAdapter;
            if (helpAdapter2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mAdapter");
            }
            helpAdapter2.notifyDataSetChanged();
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        Intrinsics.checkNotNullParameter(menuItem, ContentParser.ITEM);
        if (menuItem.getItemId() != 16908332) {
            return super.onOptionsItemSelected(menuItem);
        }
        onBackPressed();
        return true;
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
    }

    private final void checkForInvitations() {
        MyInvitationsManager.Companion.get(this).fetchAndListenOpenInvitations(this);
    }

    public void onGetOpenMyInvitationsCountComplete(JSONObject jSONObject) {
        setInvitationsCountIcon(MyInvitationsManager.Companion.getOpenMyInvitationsCount(jSONObject));
    }

    public void onGetOpenMyInvitationsCountNoResults() {
        setInvitationsCountIcon(0);
    }

    private final void setInvitationsCountIcon(int i) {
        MyInvitationsManager.Companion.get(this).updateOpenInvitations(i);
        HelpAdapter helpAdapter = this.mAdapter;
        if (helpAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mAdapter");
        }
        if (helpAdapter != null) {
            HelpAdapter helpAdapter2 = this.mAdapter;
            if (helpAdapter2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mAdapter");
            }
            helpAdapter2.notifyDataSetChanged();
        }
    }
}
