package com.medscape.android.drugs;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.medscape.android.Constants;
import com.medscape.android.R;
import com.medscape.android.db.DatabaseHelper;
import com.medscape.android.db.model.Drug;
import com.medscape.android.db.model.DrugClass;
import com.medscape.android.util.DialogUtil;
import com.medscape.android.util.Util;
import java.util.ArrayList;
import java.util.List;

public class BrowseByClassActivity extends DrugMainActivity implements AdapterView.OnItemClickListener {
    private List<DrugClass> drugClasses;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.drugClasses = new ArrayList();
        setTitle(getResources().getString(R.string.drugs_otc_herbals));
        try {
            DatabaseHelper databaseHelper = new DatabaseHelper(this);
            Cursor rawQuery = databaseHelper.getDatabase().rawQuery("SELECT rowid, ParentID, ClassID, ClassName, SingleLevel FROM tblClassNames WHERE ParentID IS NULL ORDER BY ClassName COLLATE NOCASE", (String[]) null);
            while (rawQuery.moveToNext()) {
                DrugClass drugClass = new DrugClass();
                drugClass.setId(rawQuery.getInt(rawQuery.getColumnIndex("rowid")));
                drugClass.setParentId(rawQuery.getInt(rawQuery.getColumnIndex("ParentID")));
                drugClass.setClassId(rawQuery.getInt(rawQuery.getColumnIndex("ClassID")));
                drugClass.setClassName(rawQuery.getString(rawQuery.getColumnIndex("ClassName")));
                drugClass.setSingleLevel(rawQuery.getInt(rawQuery.getColumnIndex("SingleLevel")));
                this.drugClasses.add(drugClass);
            }
            setDrugClasses(this.drugClasses);
            rawQuery.close();
            databaseHelper.close();
            updateListView();
            ((ListView) findViewById(16908298)).setOnItemClickListener(this);
        } catch (Exception e) {
            e.printStackTrace();
            if (!Util.isSDCardAvailable() && !isFinishing()) {
                showDialog(9);
            }
        }
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        DrugClass drugClass = this.drugClasses.get(i);
        if (drugClass.getSingleLevel() != 1) {
            Intent intent = new Intent(this, BrowseByChildClassActivity.class);
            intent.putExtra("parentId", drugClass.getClassId());
            intent.putExtra("className", drugClass.getClassName());
            startActivity(intent);
            return;
        }
        Intent intent2 = new Intent(this, DrugListActivity.class);
        intent2.putExtra("classId", drugClass.getClassId());
        intent2.putExtra("className", drugClass.getClassName());
        startActivity(intent2);
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        Drug drug;
        super.onActivityResult(i, i2, intent);
        if (intent != null && (drug = (Drug) intent.getExtras().get("drug")) != null) {
            Intent intent2 = new Intent(this, DrugMonographMainActivity.class);
            intent2.putExtra(Constants.EXTRA_CONTENT_ID, drug.getContentId());
            startActivity(intent2);
        }
    }

    public Dialog onCreateDialog(int i) {
        Dialog onCreateDialog = super.onCreateDialog(i);
        if (onCreateDialog != null) {
            return onCreateDialog;
        }
        if (i != 9) {
            return null;
        }
        return DialogUtil.showAlertDialog(9, (String) null, getResources().getString(R.string.alert_dialog_sdcard_required_message), this);
    }
}
