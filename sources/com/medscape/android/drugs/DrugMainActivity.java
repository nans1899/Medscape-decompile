package com.medscape.android.drugs;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import com.medscape.android.R;
import com.medscape.android.activity.AbstractBreadcrumbNavigableActivity;
import com.medscape.android.db.model.DrugClass;
import com.webmd.wbmdproffesionalauthentication.providers.AccountProvider;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DrugMainActivity extends AbstractBreadcrumbNavigableActivity {
    private List<DrugClass> drugClasses;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.drugs_main);
    }

    /* access modifiers changed from: protected */
    public void setupActionBar() {
        getActionBarToolBar().setTitleTextColor(getResources().getColor(R.color.white));
        super.setupActionBar();
    }

    /* access modifiers changed from: protected */
    public void updateListView() {
        try {
            ArrayList arrayList = new ArrayList();
            String[] strArr = {"CLASS_NAME"};
            int[] iArr = {R.id.rowTitle};
            for (DrugClass className : getDrugClasses()) {
                HashMap hashMap = new HashMap();
                hashMap.put("CLASS_NAME", className.getClassName());
                arrayList.add(hashMap);
            }
            ((ListView) findViewById(16908298)).setAdapter(new SimpleAdapter(this, arrayList, R.layout.single_textview_row_no_arrow, strArr, iArr));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setDrugClasses(List<DrugClass> list) {
        this.drugClasses = list;
    }

    public List<DrugClass> getDrugClasses() {
        return this.drugClasses;
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        if (!AccountProvider.isUserLoggedIn(this)) {
            finish();
        }
    }
}
