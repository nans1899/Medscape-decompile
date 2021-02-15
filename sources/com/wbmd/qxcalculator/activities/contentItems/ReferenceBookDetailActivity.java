package com.wbmd.qxcalculator.activities.contentItems;

import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import androidx.fragment.app.Fragment;
import com.wbmd.qxcalculator.R;
import com.wbmd.qxcalculator.activities.common.QxMDActivity;
import com.wbmd.qxcalculator.fragments.referencebook.ReferenceBookDetailFragment;
import com.wbmd.qxcalculator.model.contentItems.common.ContentItem;
import com.wbmd.qxcalculator.model.contentItems.referencebook.ReferenceBookSectionItem;
import com.wbmd.qxcalculator.util.AnalyticsHandler;
import com.wbmd.qxcalculator.util.ColorHelper;

public class ReferenceBookDetailActivity extends QxMDActivity implements ReferenceBookDetailFragment.ReferenceBookDetailListener {
    private int customActionBarColor = 0;
    private int customStatusBarColor = 0;
    private FrameLayout frameLayout;
    private ProgressBar progressBar;

    public int getStatusBarColor() {
        int i = this.customStatusBarColor;
        return i == 0 ? getResources().getColor(R.color.status_bar_color_default) : i;
    }

    public int getActionBarColor() {
        int i = this.customActionBarColor;
        return i == 0 ? getResources().getColor(R.color.action_bar_color_default) : i;
    }

    /* access modifiers changed from: protected */
    public int getLayoutResourceId() {
        return R.layout.activity_reference_book_detail;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        Drawable drawable;
        super.onCreate(bundle);
        Log.d("API", "referenceBookDetailActivity onCreate");
        ReferenceBookSectionItem referenceBookSectionItem = (ReferenceBookSectionItem) getIntent().getParcelableExtra(ReferenceBookActivity.KEY_REFERENCE_BOOK_SECTION_ITEM);
        ContentItem contentItem = (ContentItem) getIntent().getParcelableExtra(ContentItemActivity.KEY_EXTRA_CONTENT_ITEM);
        if (referenceBookSectionItem == null || contentItem == null) {
            finish();
            return;
        }
        this.progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        if (Build.VERSION.SDK_INT < 21) {
            drawable = getResources().getDrawable(R.drawable.custom_progress_bar);
        } else {
            drawable = getResources().getDrawable(R.drawable.custom_progress_bar, (Resources.Theme) null);
        }
        this.progressBar.setProgressDrawable(drawable);
        if (bundle == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, (Fragment) ReferenceBookDetailFragment.newInstance(contentItem, referenceBookSectionItem)).commit();
        }
    }

    public void showActionBarProgressBar(boolean z) {
        this.progressBar.setVisibility(z ? 0 : 8);
    }

    public void setActionBarProgressBarProgress(int i) {
        this.progressBar.setProgress(i);
        if (this.progressBar.getVisibility() == 8) {
            showActionBarProgressBar(true);
        }
    }

    public void onResume() {
        super.onResume();
        AnalyticsHandler.getInstance().trackPageView(this, "referenceBookPage");
    }

    public void didDisplayItem(ReferenceBookSectionItem referenceBookSectionItem) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle((CharSequence) Html.fromHtml(referenceBookSectionItem.topLevelItemName).toString());
            getSupportActionBar().setSubtitle((CharSequence) Html.fromHtml(referenceBookSectionItem.breadcrumbPath).toString());
        }
    }

    private void updateBarColors(int i) {
        if (i == 0) {
            this.customStatusBarColor = 0;
            this.customActionBarColor = 0;
            return;
        }
        this.customActionBarColor = i;
        this.customStatusBarColor = ColorHelper.getInstance().addColors(i, Color.parseColor("#26000000"));
    }
}
