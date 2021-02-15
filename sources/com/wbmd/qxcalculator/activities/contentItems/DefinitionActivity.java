package com.wbmd.qxcalculator.activities.contentItems;

import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.google.android.gms.ads.doubleclick.PublisherAdView;
import com.qxmd.qxrecyclerview.QxRecyclerView;
import com.qxmd.qxrecyclerview.QxRecyclerViewAdapter;
import com.wbmd.qxcalculator.R;
import com.wbmd.qxcalculator.model.contentItems.definition.Definition;
import com.wbmd.qxcalculator.model.contentItems.definition.DefinitionSection;
import com.wbmd.qxcalculator.model.rowItems.definition.DefinitionSectionRowItem;
import com.wbmd.qxcalculator.model.rowItems.headers.InvisibleHeaderRowItem;
import com.wbmd.qxcalculator.util.AnalyticsHandler;
import java.util.ArrayList;

public class DefinitionActivity extends ContentItemActivity {
    private QxRecyclerViewAdapter adapter;
    private QxRecyclerView listView;
    private PublisherAdView mAdView;

    public int getStatusBarColor() {
        return getResources().getColor(R.color.status_bar_color_default);
    }

    public int getActionBarColor() {
        return getResources().getColor(R.color.action_bar_color_default);
    }

    /* access modifiers changed from: protected */
    public int getLayoutResourceId() {
        return R.layout.activity_definition;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (this.contentItem != null || !requiresContentItem()) {
            if (this.adapter == null) {
                this.adapter = new QxRecyclerViewAdapter();
            }
            QxRecyclerView qxRecyclerView = (QxRecyclerView) findViewById(R.id.recycler_view);
            this.listView = qxRecyclerView;
            qxRecyclerView.setAdapter(this.adapter);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            linearLayoutManager.setOrientation(1);
            this.listView.setLayoutManager(linearLayoutManager);
            PublisherAdView publisherAdView = (PublisherAdView) findViewById(R.id.adView);
            this.mAdView = publisherAdView;
            publisherAdView.setVisibility(8);
            return;
        }
        finish();
    }

    public void onBackPressed() {
        super.onBackPressed();
    }

    public void onResume() {
        super.onResume();
        AnalyticsHandler.getInstance().trackPageView(this, "definition_screen");
        if (!this.adapter.getHasBeenInitialized()) {
            buildDefinitionSectionRowItems();
        }
    }

    private void buildDefinitionSectionRowItems() {
        this.adapter.reset();
        Definition definition = this.contentItem.definition;
        ArrayList arrayList = new ArrayList(definition.definitionSections.size());
        for (DefinitionSection definitionSectionRowItem : definition.definitionSections) {
            arrayList.add(new DefinitionSectionRowItem(definitionSectionRowItem));
        }
        this.adapter.addSectionWithHeaderItem(new InvisibleHeaderRowItem(), arrayList);
    }
}
