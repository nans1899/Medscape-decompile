package com.medscape.android.reference;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import com.medscape.android.R;
import java.util.ArrayList;
import java.util.HashMap;

public class ClinicalReferenceMainActivity extends ReferenceMainActivity implements AdapterView.OnItemClickListener {
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        if (i == 0) {
            Intent intent = new Intent(this, ClinicalReferenceFolderActivity.class);
            intent.putExtra("folderId", -1);
            startActivity(intent);
        } else if (i == 1) {
            Intent intent2 = new Intent(this, ClinicalReferenceFolderActivity.class);
            intent2.putExtra("folderId", 30);
            startActivity(intent2);
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.directory_main);
        setTitle(getResources().getString(R.string.clinical_procedure_header_text_view));
        ArrayList arrayList = new ArrayList();
        int[] iArr = {R.id.rowTitle};
        HashMap hashMap = new HashMap();
        hashMap.put("OPTION", "Diseases & Conditions");
        arrayList.add(hashMap);
        HashMap hashMap2 = new HashMap();
        hashMap2.put("OPTION", "Clinical Procedures");
        arrayList.add(hashMap2);
        SimpleAdapter simpleAdapter = new SimpleAdapter(this, arrayList, R.layout.single_textview_row_no_arrow, new String[]{"OPTION"}, iArr);
        ListView listView = (ListView) findViewById(16908298);
        listView.setAdapter(simpleAdapter);
        listView.setOnItemClickListener(this);
    }
}
