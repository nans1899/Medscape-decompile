package com.medscape.android.reference;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.medscape.android.BI.omniture.OmnitureManager;
import com.medscape.android.Constants;
import com.medscape.android.R;
import com.medscape.android.base.SearchableActivity;
import com.medscape.android.reference.data.ClinicalReferenceRepository;
import com.medscape.android.reference.model.ClinicalReferenceArticle;
import com.medscape.android.util.StringUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ClinicalReferenceTitleActivity extends SearchableActivity implements AdapterView.OnItemClickListener {
    private ArrayList<ClinicalReferenceArticle> articles;
    private int folderId;
    String folderName;
    int omnitureBrowseType;
    int omnitureClickPosition;

    public boolean enableDropDown() {
        return true;
    }

    public int getDefaultFilterSelectionViewId() {
        return R.id.all;
    }

    public int getFilterListResourceId() {
        return R.layout.search_filter_reference_list;
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        OmnitureManager omnitureManager = OmnitureManager.get();
        StringBuilder sb = new StringBuilder();
        sb.append("brwslst-");
        int i2 = i + 1;
        sb.append(i2);
        String sb2 = sb.toString();
        omnitureManager.markModule(true, sb2, "" + i2, (Map<String, Object>) null);
        Intent intent = new Intent(this, ClinicalReferenceArticleActivity.class);
        intent.putExtra("article", this.articles.get(i));
        intent.putExtra("folderId", this.folderId);
        startActivity(intent);
    }

    public void onCreate(Bundle bundle) {
        String str;
        super.onCreate(bundle);
        setContentView(R.layout.generic_list);
        this.articles = new ArrayList<>();
        this.folderId = getIntent().getExtras().getInt("folderId");
        this.omnitureClickPosition = getIntent().getIntExtra("omnitureClickPosition", -1);
        this.folderName = getIntent().getExtras().getString("folderName");
        this.omnitureBrowseType = getIntent().getIntExtra("omnitureBrowseType", -1);
        String str2 = this.folderName;
        if (str2 == null || str2.equals("")) {
            str = getResources().getString(R.string.clinical_procedure_header_text_view);
        } else {
            str = this.folderName;
        }
        setTitle(str);
        new ClinicalReferenceRepository(getApplicationContext()).getArticles(this.folderId);
        updateListView();
    }

    private void sendOmniturePing(String str) {
        StringBuilder sb = new StringBuilder();
        int i = this.omnitureBrowseType;
        if (i == 1) {
            sb.append("conditions/browse");
        } else if (i == 2) {
            sb.append("procedures/browse");
        }
        if (StringUtil.isNotEmpty(str)) {
            sb.append("/");
            sb.append(str.toLowerCase().replace(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR, "-"));
        }
        if (this.omnitureClickPosition > 0) {
            sb.append("/");
            sb.append(this.omnitureClickPosition);
        }
        this.mPvid = OmnitureManager.get().trackPageView(this, Constants.OMNITURE_CHANNEL_REFERENCE, sb.toString(), "view", (String) null, (String) null, (Map<String, Object>) null);
    }

    /* access modifiers changed from: protected */
    public void updateListView() {
        ArrayList arrayList = new ArrayList();
        String[] strArr = {"CLASS_NAME"};
        int[] iArr = {R.id.rowTitle};
        Iterator<ClinicalReferenceArticle> it = this.articles.iterator();
        while (it.hasNext()) {
            HashMap hashMap = new HashMap();
            hashMap.put("CLASS_NAME", it.next().getTitle().replaceAll("''", "'"));
            arrayList.add(hashMap);
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(this, arrayList, R.layout.single_textview_row_no_arrow, strArr, iArr);
        ListView listView = (ListView) findViewById(16908298);
        listView.setAdapter(simpleAdapter);
        listView.setOnItemClickListener(this);
    }

    /* access modifiers changed from: protected */
    public void setupActionBar() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(true);
            getSupportActionBar().setDisplayUseLogoEnabled(false);
        }
    }

    public void onResume() {
        super.onResume();
        sendOmniturePing(this.folderName);
    }
}
