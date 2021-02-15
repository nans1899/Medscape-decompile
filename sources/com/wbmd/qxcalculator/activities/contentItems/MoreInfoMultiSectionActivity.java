package com.wbmd.qxcalculator.activities.contentItems;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.qxmd.qxrecyclerview.QxRecyclerView;
import com.qxmd.qxrecyclerview.QxRecyclerViewAdapter;
import com.qxmd.qxrecyclerview.QxRecyclerViewRowItem;
import com.wbmd.qxcalculator.R;
import com.wbmd.qxcalculator.activities.common.QxMDActivity;
import com.wbmd.qxcalculator.managers.FileHelper;
import com.wbmd.qxcalculator.model.contentItems.calculator.MoreInfoSection;
import com.wbmd.qxcalculator.model.contentItems.calculator.MoreInfoSectionItem;
import com.wbmd.qxcalculator.model.contentItems.common.ContentItem;
import com.wbmd.qxcalculator.model.rowItems.MoreInfoSectionGroupRowItem;
import com.wbmd.qxcalculator.model.rowItems.MoreInfoSectionItemRowItem;
import com.wbmd.qxcalculator.model.rowItems.headers.InvisibleHeaderRowItem;
import com.wbmd.qxcalculator.util.AnalyticsHandler;
import com.wbmd.qxcalculator.util.legacy.ContentParser;
import com.wbmd.qxcalculator.views.ArrowView;
import java.util.ArrayList;

public class MoreInfoMultiSectionActivity extends QxMDActivity implements QxRecyclerViewAdapter.OnRecyclerViewRowItemClickedListener, QxRecyclerViewAdapter.OnRecyclerViewRowItemExpandCollapseListener {
    private QxRecyclerViewAdapter adapter;
    private ContentItem contentItem;
    private QxRecyclerView listView;

    /* access modifiers changed from: protected */
    public int getLayoutResourceId() {
        return R.layout.activity_more_info_multi_section;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ContentItem contentItem2 = (ContentItem) getIntent().getParcelableExtra(ContentItemActivity.KEY_EXTRA_CONTENT_ITEM);
        this.contentItem = contentItem2;
        if (contentItem2 == null) {
            finish();
            return;
        }
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.more_info_activity_title);
        }
        if (this.adapter == null) {
            QxRecyclerViewAdapter qxRecyclerViewAdapter = new QxRecyclerViewAdapter();
            this.adapter = qxRecyclerViewAdapter;
            qxRecyclerViewAdapter.setOnClickListener(this);
            this.adapter.setOnExpandCollapseListener(this);
        }
        QxRecyclerView qxRecyclerView = (QxRecyclerView) findViewById(R.id.recycler_view);
        this.listView = qxRecyclerView;
        qxRecyclerView.setAdapter(this.adapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(1);
        this.listView.setLayoutManager(linearLayoutManager);
    }

    public void onResume() {
        super.onResume();
        AnalyticsHandler.getInstance().trackPageView(this, "MoreInfoMultiSection");
        if (!this.adapter.getHasBeenInitialized()) {
            buildCalculatorRowItems();
        }
    }

    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.close_enter_modal, R.anim.close_exit_modal);
    }

    /* access modifiers changed from: protected */
    public void buildCalculatorRowItems() {
        this.adapter.reset();
        new ArrayList();
        for (MoreInfoSection next : this.contentItem.moreInfoSections) {
            ArrayList arrayList = new ArrayList(next.sectionItems.size());
            for (MoreInfoSectionItem buildExtraSectionItem : next.sectionItems) {
                QxRecyclerViewRowItem buildExtraSectionItem2 = buildExtraSectionItem(buildExtraSectionItem);
                if (buildExtraSectionItem2 != null) {
                    arrayList.add(buildExtraSectionItem2);
                }
            }
            if (!arrayList.isEmpty()) {
                this.adapter.addSectionWithHeaderItem(new InvisibleHeaderRowItem(), arrayList);
            }
        }
        this.adapter.notifyDataSetChanged();
    }

    private QxRecyclerViewRowItem buildExtraSectionItem(MoreInfoSectionItem moreInfoSectionItem) {
        if (moreInfoSectionItem.subSectionItems == null || moreInfoSectionItem.subSectionItems.isEmpty()) {
            return new MoreInfoSectionItemRowItem(moreInfoSectionItem, this.contentItem, this);
        }
        MoreInfoSectionGroupRowItem moreInfoSectionGroupRowItem = new MoreInfoSectionGroupRowItem(moreInfoSectionItem, this.contentItem, this);
        for (MoreInfoSectionItem buildExtraSectionItem : moreInfoSectionItem.subSectionItems) {
            QxRecyclerViewRowItem buildExtraSectionItem2 = buildExtraSectionItem(buildExtraSectionItem);
            if (buildExtraSectionItem2 != null) {
                moreInfoSectionGroupRowItem.addChild(buildExtraSectionItem2);
            }
        }
        return moreInfoSectionGroupRowItem;
    }

    public void onRecyclerViewRowItemClicked(QxRecyclerViewRowItem qxRecyclerViewRowItem, QxRecyclerViewAdapter qxRecyclerViewAdapter, View view, int i) {
        Intent intent;
        if (qxRecyclerViewRowItem instanceof MoreInfoSectionItemRowItem) {
            MoreInfoSectionItemRowItem moreInfoSectionItemRowItem = (MoreInfoSectionItemRowItem) qxRecyclerViewRowItem;
            String str = moreInfoSectionItemRowItem.moreInfoSectionItem.source;
            if (str.toLowerCase().contains(ContentParser.PDF)) {
                intent = new Intent(this, PdfViewerActivity.class);
                if (!str.toLowerCase().startsWith("http")) {
                    str = FileHelper.getInstance().getResourceFolderPathForContentItem(this.contentItem) + str;
                }
                intent.putExtra(PdfViewerActivity.KEY_BUNDLE_URL, str);
                intent.putExtra(QxMDActivity.KEY_TRACKER_PAGE_NAME, this.contentItem.name + " - " + moreInfoSectionItemRowItem.moreInfoSectionItem.title);
                if (this.contentItem.trackerId != null && !this.contentItem.trackerId.isEmpty()) {
                    intent.putExtra(QxMDActivity.KEY_TRACKER_ID, this.contentItem.trackerId);
                }
            } else {
                intent = new Intent(this, FileSourceHtmlActivity.class);
                if (!str.toLowerCase().startsWith("http")) {
                    str = "file://" + FileHelper.getInstance().getResourceFolderPathForContentItem(this.contentItem) + str;
                }
                intent.putExtra(FileSourceHtmlActivity.KEY_INTENT_URL, str);
                intent.putExtra(ContentItemActivity.KEY_IS_MORE_INFO_SECTION, true);
                intent.putExtra(QxMDActivity.KEY_TRACKER_PAGE_NAME, this.contentItem.name + " - " + moreInfoSectionItemRowItem.moreInfoSectionItem.title);
                if (this.contentItem.trackerId != null && !this.contentItem.trackerId.isEmpty()) {
                    intent.putExtra(QxMDActivity.KEY_TRACKER_ID, this.contentItem.trackerId);
                }
                intent.putExtra(ContentItemActivity.KEY_EXTRA_CONTENT_ITEM, this.contentItem);
            }
            startActivity(intent);
        }
    }

    public void onRecyclerViewRowItemExpanded(QxRecyclerViewRowItem qxRecyclerViewRowItem, QxRecyclerViewAdapter qxRecyclerViewAdapter, View view, int i) {
        ArrowView arrowView = (ArrowView) view.findViewById(R.id.arrow_view);
        if (arrowView != null) {
            arrowView.expandArrow(true, true);
        }
    }

    public void onRecyclerViewRowItemCollapsed(QxRecyclerViewRowItem qxRecyclerViewRowItem, QxRecyclerViewAdapter qxRecyclerViewAdapter, View view, int i) {
        ArrowView arrowView = (ArrowView) view.findViewById(R.id.arrow_view);
        if (arrowView != null) {
            arrowView.expandArrow(false, true);
        }
    }
}
