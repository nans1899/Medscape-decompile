package com.medscape.android.activity.formulary;

import android.os.Bundle;
import android.view.MenuItem;
import androidx.fragment.app.Fragment;
import com.medscape.android.BI.omniture.OmnitureManager;
import com.medscape.android.Constants;
import com.medscape.android.R;
import com.medscape.android.activity.AbstractBreadcrumbNavigableActivity;
import com.medscape.android.contentviewer.interfaces.ILoadNextListener;
import java.util.Map;

public class SelectBrandActivity extends AbstractBreadcrumbNavigableActivity {
    int contentId;
    String title = "";

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.formulary_main);
        initFragment();
    }

    public void initFragment() {
        getSupportFragmentManager().beginTransaction().replace(R.id.container, getFormularyFragment()).commit();
    }

    public Fragment getFormularyFragment() {
        Fragment newInstance = SelectBrandFragment.newInstance((ILoadNextListener) null);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.title = extras.getString("TITLE");
            this.contentId = extras.getInt(FormularyMyPlanPage.CONTENT_ID);
            if (this.title == null) {
                this.title = "";
            }
            setTitle(this.title);
        }
        newInstance.setArguments(extras);
        return newInstance;
    }

    public void onResume() {
        super.onResume();
        OmnitureManager omnitureManager = OmnitureManager.get();
        this.mPvid = omnitureManager.trackPageView(this, Constants.OMNITURE_CHANNEL_REFERENCE, "formulary", "view", "" + this.contentId, "start", (Map<String, Object>) null);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        finish();
        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
        return true;
    }

    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
    }
}
