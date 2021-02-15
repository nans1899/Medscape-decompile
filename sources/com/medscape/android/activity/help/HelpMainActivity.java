package com.medscape.android.activity.help;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import androidx.core.net.MailTo;
import com.medscape.android.BI.omniture.OmnitureManager;
import com.medscape.android.BuildConfig;
import com.medscape.android.R;
import com.medscape.android.activity.AbstractBreadcrumbNavigableActivity;
import com.medscape.android.activity.help.adapters.HelpMainAdapter;
import com.medscape.android.activity.help.models.HelpMainItem;
import com.medscape.android.activity.webviews.WebviewUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u000b\u001a\u00020\fH\u0002J\b\u0010\r\u001a\u00020\fH\u0002J\u0012\u0010\u000e\u001a\u00020\f2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0014J,\u0010\u0011\u001a\u00020\f2\n\u0010\u0012\u001a\u0006\u0012\u0002\b\u00030\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0016J\b\u0010\u001a\u001a\u00020\fH\u0014R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u000e¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lcom/medscape/android/activity/help/HelpMainActivity;", "Lcom/medscape/android/activity/AbstractBreadcrumbNavigableActivity;", "Landroid/widget/AdapterView$OnItemClickListener;", "()V", "mItemsAdapter", "Lcom/medscape/android/activity/help/adapters/HelpMainAdapter;", "mListItem", "Ljava/util/ArrayList;", "Lcom/medscape/android/activity/help/models/HelpMainItem;", "mListView", "Landroid/widget/ListView;", "initViews", "", "loadData", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onItemClick", "parent", "Landroid/widget/AdapterView;", "v", "Landroid/view/View;", "position", "", "id", "", "onResume", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: HelpMainActivity.kt */
public final class HelpMainActivity extends AbstractBreadcrumbNavigableActivity implements AdapterView.OnItemClickListener {
    private HashMap _$_findViewCache;
    private HelpMainAdapter mItemsAdapter;
    private final ArrayList<HelpMainItem> mListItem = new ArrayList<>();
    private ListView mListView;

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
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.directory_main);
        initViews();
        loadData();
    }

    private final void initViews() {
        setTitle(getString(R.string.information));
        this.mListView = (ListView) findViewById(16908298);
        HelpMainAdapter helpMainAdapter = new HelpMainAdapter(this, this.mListItem);
        this.mItemsAdapter = helpMainAdapter;
        ListView listView = this.mListView;
        if (listView != null) {
            listView.setAdapter(helpMainAdapter);
        }
        ListView listView2 = this.mListView;
        if (listView2 != null) {
            listView2.setOnItemClickListener(this);
        }
    }

    private final void loadData() {
        ArrayList<HelpMainItem> arrayList = this.mListItem;
        String string = getString(R.string.title_report_issue);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.title_report_issue)");
        arrayList.add(new HelpMainItem(0, string));
        ArrayList<HelpMainItem> arrayList2 = this.mListItem;
        String string2 = getString(R.string.title_online_help_preference);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.title_online_help_preference)");
        arrayList2.add(new HelpMainItem(1, string2));
        ArrayList<HelpMainItem> arrayList3 = this.mListItem;
        String string3 = getString(R.string.title_privacy_policy_preference);
        Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.title…rivacy_policy_preference)");
        arrayList3.add(new HelpMainItem(2, string3));
        ArrayList<HelpMainItem> arrayList4 = this.mListItem;
        String string4 = getString(R.string.title_editorial_policy_preference);
        Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.title…torial_policy_preference)");
        arrayList4.add(new HelpMainItem(3, string4));
        ArrayList<HelpMainItem> arrayList5 = this.mListItem;
        String string5 = getString(R.string.title_advertising_policy_preference);
        Intrinsics.checkNotNullExpressionValue(string5, "getString(R.string.title…tising_policy_preference)");
        arrayList5.add(new HelpMainItem(4, string5));
        ArrayList<HelpMainItem> arrayList6 = this.mListItem;
        String string6 = getString(R.string.title_terms_of_use_preference);
        Intrinsics.checkNotNullExpressionValue(string6, "getString(R.string.title_terms_of_use_preference)");
        arrayList6.add(new HelpMainItem(5, string6));
        ArrayList<HelpMainItem> arrayList7 = this.mListItem;
        String string7 = getString(R.string.title_about_preference);
        Intrinsics.checkNotNullExpressionValue(string7, "getString(R.string.title_about_preference)");
        arrayList7.add(new HelpMainItem(6, string7));
        HelpMainAdapter helpMainAdapter = this.mItemsAdapter;
        if (helpMainAdapter != null) {
            helpMainAdapter.setItems(this.mListItem);
        }
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        Intrinsics.checkNotNullParameter(adapterView, "parent");
        Intrinsics.checkNotNullParameter(view, "v");
        switch (this.mListItem.get(i).getItemId()) {
            case 0:
                Intent intent = new Intent("android.intent.action.SENDTO");
                intent.setData(Uri.parse(MailTo.MAILTO_SCHEME));
                intent.putExtra("android.intent.extra.EMAIL", new String[]{getString(R.string.help_issue_email)});
                intent.putExtra("android.intent.extra.SUBJECT", getString(R.string.help_subject));
                intent.putExtra("android.intent.extra.TEXT", getResources().getString(R.string.report_issue_body) + Build.MODEL + ", OS " + Build.VERSION.RELEASE + ", App " + BuildConfig.VERSION_NAME);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
                OmnitureManager.get().trackModule(this, "other", "wv-launch", "issue", (Map<String, Object>) null);
                return;
            case 1:
                Intent intent2 = new Intent("android.intent.action.VIEW");
                intent2.setData(Uri.parse(getResources().getString(R.string.url_online_help_center)));
                String string = getResources().getString(R.string.open_with);
                Intrinsics.checkNotNullExpressionValue(string, "resources.getString(R.string.open_with)");
                startActivity(Intent.createChooser(intent2, string));
                OmnitureManager.get().trackModule(this, "other", "wv-launch", "help", (Map<String, Object>) null);
                return;
            case 2:
                String string2 = getResources().getString(R.string.url_privacy_policy);
                Intrinsics.checkNotNullExpressionValue(string2, "this.resources.getString…tring.url_privacy_policy)");
                WebviewUtil.Companion.launchPlainWebView$default(WebviewUtil.Companion, this, string2, getResources().getString(R.string.title_privacy_policy_preference), "wv-launch", "prpol", "other", "", 1, false, 256, (Object) null);
                return;
            case 3:
                String string3 = getResources().getString(R.string.url_editorial_privacy);
                Intrinsics.checkNotNullExpressionValue(string3, "this.resources.getString…ng.url_editorial_privacy)");
                WebviewUtil.Companion.launchPlainWebView$default(WebviewUtil.Companion, this, string3, getResources().getString(R.string.title_editorial_policy_preference), "wv-launch", "edpol", "other", "", 1, false, 256, (Object) null);
                return;
            case 4:
                String string4 = getResources().getString(R.string.url_advertising_policy);
                Intrinsics.checkNotNullExpressionValue(string4, "this.resources.getString…g.url_advertising_policy)");
                WebviewUtil.Companion.launchPlainWebView$default(WebviewUtil.Companion, this, string4, getResources().getString(R.string.title_advertising_policy_preference), "wv-launch", "adpol", "other", "", 1, false, 256, (Object) null);
                return;
            case 5:
                String string5 = getResources().getString(R.string.url_terms_of_use);
                Intrinsics.checkNotNullExpressionValue(string5, "this.resources.getString….string.url_terms_of_use)");
                WebviewUtil.Companion.launchPlainWebView$default(WebviewUtil.Companion, this, string5, getResources().getString(R.string.title_terms_of_use_preference), "wv-launch", "tou", "other", "", 1, false, 256, (Object) null);
                return;
            case 6:
                String string6 = getResources().getString(R.string.url_about);
                Intrinsics.checkNotNullExpressionValue(string6, "this.resources.getString(R.string.url_about)");
                WebviewUtil.Companion.launchPlainWebView$default(WebviewUtil.Companion, this, string6, getResources().getString(R.string.title_about_preference), "wv-launch", "about", "other", "", 1, false, 256, (Object) null);
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        OmnitureManager.get().trackPageView(this, "other", "help", "view", (String) null, (String) null, (Map<String, Object>) null, false, "");
    }
}
