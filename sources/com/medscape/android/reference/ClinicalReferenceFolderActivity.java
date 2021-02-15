package com.medscape.android.reference;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.medscape.android.BI.omniture.OmnitureManager;
import com.medscape.android.Constants;
import com.medscape.android.R;
import com.medscape.android.analytics.remoteconfig.reference.ReferenceArticleTOCEnabledManager;
import com.medscape.android.reference.adapters.FolderAdapter;
import com.medscape.android.reference.data.ClinicalReferenceRepository;
import com.medscape.android.reference.interfaces.IFolderItemClickCallback;
import com.medscape.android.reference.model.ClinicalReference;
import com.medscape.android.reference.model.ClinicalReferenceArticle;
import com.medscape.android.util.StringUtil;
import java.util.ArrayList;
import java.util.Map;

public class ClinicalReferenceFolderActivity extends ReferenceMainActivity {
    private static final String TAG = ClinicalReferenceFolderActivity.class.getSimpleName();
    /* access modifiers changed from: private */
    public Activity mActivity;
    /* access modifiers changed from: private */
    public ArrayList<ClinicalReferenceArticle> mArticles;
    /* access modifiers changed from: private */
    public ClinicalReferenceRepository mClinicalReferenceRepository;
    private ArrayList<ClinicalReference> mClinicalReferences;
    /* access modifiers changed from: private */
    public int mFolderDepth;
    /* access modifiers changed from: private */
    public int mFolderId;
    private String mFolderName;
    private boolean mIsArticleFolder;
    private RecyclerView mList;
    /* access modifiers changed from: private */
    public int mOmnitureBrowseType;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        String str;
        super.onCreate(bundle);
        handleIntent();
        String str2 = this.mFolderName;
        if (str2 != null && !str2.equals("")) {
            str = this.mFolderName;
        } else if (this.mFolderId == -1) {
            str = getResources().getString(R.string.diseases_and_conditions_text_view);
            this.mOmnitureBrowseType = 1;
        } else {
            str = getResources().getString(R.string.clinical_procedures_text_view);
            this.mOmnitureBrowseType = 2;
        }
        setTitle(str);
        setContentView((int) R.layout.generic_recycler_list);
        this.mActivity = this;
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.list);
        this.mList = recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        if (this.mIsArticleFolder) {
            this.mArticles = this.mClinicalReferenceRepository.getArticles(this.mFolderId);
        } else {
            this.mClinicalReferences = this.mClinicalReferenceRepository.getFolderItems(this.mFolderId);
        }
        if (!(this.mClinicalReferences == null && this.mArticles == null)) {
            updateListView();
        }
        setupActionBar();
    }

    public void handleIntent() {
        this.mIsArticleFolder = getIntent().getBooleanExtra("isArticleFolder", false);
        this.mClinicalReferenceRepository = new ClinicalReferenceRepository(this);
        this.mClinicalReferences = new ArrayList<>();
        this.mFolderId = getIntent().getIntExtra("folderId", -1);
        this.mFolderName = getIntent().getStringExtra("folderName");
        this.mOmnitureBrowseType = getIntent().getIntExtra("omnitureBrowseType", -1);
        this.mFolderDepth = getIntent().getIntExtra("folder_dept", 0);
    }

    public void onResume() {
        super.onResume();
        sendOmniturePing(this.mFolderName);
    }

    public void onBackPressed() {
        super.onBackPressed();
        int i = this.mFolderDepth;
        if (i > 0) {
            this.mFolderDepth = i - 1;
            overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int i = this.mFolderDepth;
        if (i > 0) {
            this.mFolderDepth = i - 1;
            overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
        }
        return super.onOptionsItemSelected(menuItem);
    }

    /* access modifiers changed from: protected */
    public void updateListView() {
        this.mList.setAdapter(new FolderAdapter(this.mClinicalReferences, this.mArticles, getOnFolderItemClickCallback()));
    }

    private IFolderItemClickCallback getOnFolderItemClickCallback() {
        return new IFolderItemClickCallback() {
            public void onItemClicked(ClinicalReference clinicalReference, int i, View view) {
                int folderItemsCount = ClinicalReferenceFolderActivity.this.mClinicalReferenceRepository.getFolderItemsCount(clinicalReference);
                Intent intent = new Intent(ClinicalReferenceFolderActivity.this.getApplicationContext(), ClinicalReferenceFolderActivity.class);
                intent.putExtra("folderId", clinicalReference.getFolderId());
                intent.putExtra("folderName", clinicalReference.getName());
                intent.putExtra("omnitureClickPosition", i + 1);
                intent.putExtra("omnitureBrowseType", ClinicalReferenceFolderActivity.this.mOmnitureBrowseType);
                intent.putExtra("folder_dept", ClinicalReferenceFolderActivity.this.mFolderDepth + 1);
                if (folderItemsCount > 0) {
                    intent.putExtra("isArticleFolder", false);
                } else {
                    intent.putExtra("isArticleFolder", true);
                }
                ClinicalReferenceFolderActivity.this.mActivity.startActivity(intent);
                ClinicalReferenceFolderActivity.this.mActivity.overridePendingTransition(R.anim.push_left_in, R.anim.push_none);
            }

            public void onItemClicked(ClinicalReferenceArticle clinicalReferenceArticle, int i, View view) {
                Intent intent;
                ClinicalReferenceArticle clinicalReferenceArticle2 = (ClinicalReferenceArticle) ClinicalReferenceFolderActivity.this.mArticles.get(i);
                OmnitureManager.get().markModule(true, "brwslst-" + (ClinicalReferenceFolderActivity.this.mFolderDepth + 1), "" + (i + 1), (Map<String, Object>) null);
                if (new ReferenceArticleTOCEnabledManager().getRefTOCData()) {
                    intent = new Intent(ClinicalReferenceFolderActivity.this.mActivity, ClinicalReferenceArticleLandingActivity.class);
                } else {
                    intent = new Intent(ClinicalReferenceFolderActivity.this.mActivity, ClinicalReferenceArticleActivity.class);
                }
                intent.putExtra("article", clinicalReferenceArticle2);
                intent.putExtra("folderId", ClinicalReferenceFolderActivity.this.mFolderId);
                ClinicalReferenceFolderActivity.this.mActivity.startActivity(intent);
                ClinicalReferenceFolderActivity.this.mActivity.overridePendingTransition(R.anim.push_left_in, R.anim.push_none);
            }
        };
    }

    private void sendOmniturePing(String str) {
        StringBuilder sb = new StringBuilder();
        int i = this.mOmnitureBrowseType;
        if (i == 1) {
            sb.append("conditions/browse");
        } else if (i == 2) {
            sb.append("procedures/browse");
        }
        if (StringUtil.isNotEmpty(str)) {
            sb.append("/");
            sb.append(str.toLowerCase().replace(",", "").replace(":", "").replace(" &", "").replace("& ", "").replace(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR, "-"));
        }
        this.mPvid = OmnitureManager.get().trackPageView(this, Constants.OMNITURE_CHANNEL_REFERENCE, sb.toString(), "view", (String) null, (String) null, (Map<String, Object>) null);
    }
}
