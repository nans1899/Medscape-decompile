package com.medscape.android.activity.calc;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.medscape.android.BI.omniture.OmnitureManager;
import com.medscape.android.Constants;
import com.medscape.android.R;
import com.medscape.android.activity.calc.model.CalcArticle;
import com.medscape.android.activity.calc.model.CalcsContract;
import com.medscape.android.db.DatabaseHelper;
import com.medscape.android.reference.ReferenceMainActivity;
import com.medscape.android.util.StringUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class CalcTitleActivity extends ReferenceMainActivity implements AdapterView.OnItemClickListener {
    private ArrayList<CalcArticle> articles;
    private String folderId;
    String folderName;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.generic_list);
        this.articles = new ArrayList<>();
        this.folderId = getIntent().getExtras().getString("folderId");
        String string = getIntent().getExtras().getString("folderName");
        this.folderName = string;
        if (string == null || string.equals("")) {
            setTitle(getResources().getString(R.string.calculators));
        } else {
            setTitle(this.folderName);
        }
        getDataFromDB();
        updateListView();
    }

    private void sendOmniturePing(String str) {
        StringBuilder sb = new StringBuilder("calculators/browse");
        if (StringUtil.isNotEmpty(str)) {
            sb.append("/");
            sb.append(str.toLowerCase().replace(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR, "-"));
        }
        this.mPvid = OmnitureManager.get().trackPageView(this, Constants.OMNITURE_CHANNEL_REFERENCE, sb.toString(), "view", (String) null, (String) null, (Map<String, Object>) null);
    }

    /* access modifiers changed from: protected */
    public void updateListView() {
        ArrayList arrayList = new ArrayList();
        String[] strArr = {"CLASS_NAME"};
        int[] iArr = {R.id.rowTitle};
        Iterator<CalcArticle> it = this.articles.iterator();
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

    private void getDataFromDB() {
        try {
            DatabaseHelper databaseHelper = new DatabaseHelper(this);
            Cursor rawQuery = databaseHelper.getDatabase().rawQuery("SELECT Title, CalcID, Type FROM tblCalcTitles, tblCalcsMapping WHERE tblCalcTitles.UniqueID = tblCalcsMapping.UniqueID AND FolderID = ? ORDER BY Title COLLATE NOCASE", new String[]{this.folderId});
            while (rawQuery.moveToNext()) {
                CalcArticle calcArticle = new CalcArticle();
                calcArticle.setCalcId(rawQuery.getString(rawQuery.getColumnIndex(CalcsContract.CALC_ID)));
                calcArticle.setTitle(rawQuery.getString(rawQuery.getColumnIndex("Title")));
                calcArticle.setType(rawQuery.getInt(rawQuery.getColumnIndex(CalcsContract.TYPE)));
                this.articles.add(calcArticle);
            }
            rawQuery.close();
            databaseHelper.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        OmnitureManager omnitureManager = OmnitureManager.get();
        StringBuilder sb = new StringBuilder();
        sb.append("brwslst-");
        int i2 = i + 1;
        sb.append(i2);
        String sb2 = sb.toString();
        omnitureManager.markModule(true, sb2, "" + i2, (Map<String, Object>) null);
        Intent intent = new Intent(this, CalcArticleActivity.class);
        intent.putExtra("article", this.articles.get(i));
        intent.putExtra("folderId", this.folderId);
        startActivity(intent);
    }

    public void onResume() {
        super.onResume();
        sendOmniturePing(this.folderName);
    }
}
