package com.medscape.android.drugs;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import com.facebook.applinks.AppLinkData;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.medscape.android.BI.omniture.OmnitureManager;
import com.medscape.android.Constants;
import com.medscape.android.R;
import com.medscape.android.db.DatabaseHelper;
import com.medscape.android.db.DrugDataHelper;
import com.medscape.android.db.model.Drug;
import com.medscape.android.drugs.model.DrugsContract;
import com.medscape.android.util.StringUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DrugListActivity extends DrugMainActivity implements AdapterView.OnItemClickListener {
    private List<Drug> drugs;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.drugs = new ArrayList();
        int intExtra = getIntent().getIntExtra("classId", 0);
        String stringExtra = getIntent().getStringExtra("className");
        setTitle(stringExtra);
        try {
            DatabaseHelper databaseHelper = new DatabaseHelper(this);
            Cursor rawQuery = databaseHelper.getDatabase().rawQuery("SELECT DISTINCT tblClassMapping.ContentID as ContentID, DrugName FROM tblClassMapping JOIN tblDrugs ON tblClassMapping.ContentID = tblDrugs.ContentID WHERE ClassID = ? ORDER BY DrugName COLLATE NOCASE", new String[]{String.valueOf(intExtra)});
            while (rawQuery.moveToNext()) {
                Drug drug = new Drug();
                drug.setContentId(rawQuery.getInt(rawQuery.getColumnIndex(rawQuery.getColumnName(0))));
                drug.setDrugName(rawQuery.getString(rawQuery.getColumnIndex(DrugsContract.Drug.DRUG_NAME)));
                this.drugs.add(drug);
            }
            rawQuery.close();
            databaseHelper.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        updateListView();
        sendOmniturePing(stringExtra);
    }

    private void sendOmniturePing(String str) {
        StringBuilder sb = new StringBuilder("drugs/browse");
        if (StringUtil.isNotEmpty(str)) {
            sb.append("/");
            sb.append(str.toLowerCase().replace(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR, "-"));
        }
        this.mPvid = OmnitureManager.get().trackPageView(this, Constants.OMNITURE_CHANNEL_REFERENCE, sb.toString(), "view", (String) null, (String) null, (Map<String, Object>) null);
    }

    /* access modifiers changed from: protected */
    public void updateListView() {
        ArrayList arrayList = new ArrayList();
        String[] strArr = {"DRUG_NAME"};
        int[] iArr = {R.id.rowTitle};
        for (Drug drugName : this.drugs) {
            HashMap hashMap = new HashMap();
            hashMap.put("DRUG_NAME", drugName.getDrugName().replaceAll("''", "'"));
            arrayList.add(hashMap);
        }
        ((ListView) findViewById(16908298)).setAdapter(new SimpleAdapter(this, arrayList, R.layout.single_textview_row_no_arrow, strArr, iArr));
        ((ListView) findViewById(16908298)).setOnItemClickListener(this);
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        Drug drug = this.drugs.get(i);
        DrugDataHelper.findUniqueIdFromContentId(this, drug.getContentId(), drug.getDrugName());
        Intent intent = new Intent(this, DrugMonographMainActivity.class);
        intent.putExtra(Constants.EXTRA_CONTENT_ID, drug.getContentId());
        intent.putExtra("drugName", drug.getDrugName());
        intent.putExtra(Constants.EXTRA_PUSH_LAUNCH, true);
        startActivity(intent);
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle bundle) {
        bundle.putBundle(AppLinkData.ARGUMENTS_EXTRAS_KEY, getIntent().getExtras());
        super.onSaveInstanceState(bundle);
    }
}
