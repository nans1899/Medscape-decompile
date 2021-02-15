package com.webmd.wbmdcmepulse.activities;

import android.os.Bundle;
import android.view.MenuItem;
import androidx.fragment.app.Fragment;
import com.webmd.wbmdcmepulse.R;
import com.webmd.wbmdcmepulse.fragments.ArticleDisclosuresFragment;
import com.webmd.wbmdcmepulse.models.articles.Contributor;
import com.webmd.wbmdcmepulse.models.utils.Constants;
import java.util.List;

public class CmeArticleDisclosuresActivity extends CmeBaseActivity {
    private List<Contributor> mContributors;

    /* access modifiers changed from: package-private */
    public void trackOmniturePageView() {
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.cme_activity_article_disclosure);
        initializeToolBar();
        setUpDefaultActionBar(getString(R.string.disclosures_link_text), true);
        this.mContributors = getIntent().getParcelableArrayListExtra(Constants.BUNDLE_KEY_DISCLOSURES);
        setUpFragment();
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 16908332) {
            return super.onOptionsItemSelected(menuItem);
        }
        finish();
        return true;
    }

    private void setUpFragment() {
        if (((ArticleDisclosuresFragment) getSupportFragmentManager().findFragmentByTag(Constants.FRAGMENT_TAG_ARTICLE_DISCLOSURES)) == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, (Fragment) ArticleDisclosuresFragment.newInstance(this.mContributors, getSupportFragmentManager()), Constants.FRAGMENT_TAG_ARTICLE_REFERENCES).commit();
        }
    }
}
