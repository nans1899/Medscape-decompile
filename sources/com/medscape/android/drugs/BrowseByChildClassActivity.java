package com.medscape.android.drugs;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.medscape.android.BI.omniture.OmnitureManager;
import com.medscape.android.Constants;
import com.medscape.android.db.DatabaseHelper;
import com.medscape.android.db.model.DrugClass;
import com.medscape.android.util.StringUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BrowseByChildClassActivity extends DrugMainActivity implements AdapterView.OnItemClickListener {
    private List<DrugClass> drugClasses;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.drugClasses = new ArrayList();
        int intExtra = getIntent().getIntExtra("parentId", 0);
        String stringExtra = getIntent().getStringExtra("className");
        setTitle(stringExtra);
        try {
            DatabaseHelper databaseHelper = new DatabaseHelper(this);
            Cursor rawQuery = databaseHelper.getDatabase().rawQuery("SELECT rowid, ParentID, ClassID, ClassName, SingleLevel FROM tblClassNames WHERE ParentID = ? ORDER BY ClassName COLLATE NOCASE", new String[]{String.valueOf(intExtra)});
            while (rawQuery.moveToNext()) {
                DrugClass drugClass = new DrugClass();
                drugClass.setId(rawQuery.getInt(rawQuery.getColumnIndex("rowid")));
                drugClass.setParentId(rawQuery.getInt(rawQuery.getColumnIndex("ParentID")));
                drugClass.setClassId(rawQuery.getInt(rawQuery.getColumnIndex("ClassID")));
                drugClass.setClassName(rawQuery.getString(rawQuery.getColumnIndex("ClassName")));
                drugClass.setSingleLevel(rawQuery.getInt(rawQuery.getColumnIndex("SingleLevel")));
                this.drugClasses.add(drugClass);
            }
            rawQuery.close();
            databaseHelper.close();
            setDrugClasses(this.drugClasses);
        } catch (Exception e) {
            e.printStackTrace();
        }
        updateListView();
        ((ListView) findViewById(16908298)).setOnItemClickListener(this);
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

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        DrugClass drugClass = this.drugClasses.get(i);
        startActivity(new Intent(this, DrugListActivity.class).putExtra("classId", drugClass.getClassId()).putExtra("className", drugClass.getClassName()));
    }
}
