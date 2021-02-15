package com.webmd.wbmdcmepulse.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.ActionBar;
import com.wbmd.wbmdcommons.customviews.CustomFontTextView;
import com.wbmd.wbmdcommons.logging.Trace;
import com.wbmd.wbmdcommons.utils.ChronicleIDUtil;
import com.webmd.wbmdcmepulse.R;
import com.webmd.wbmdcmepulse.customviews.ArticleCopyTextView;
import com.webmd.wbmdcmepulse.models.articles.Questionnaire;
import com.webmd.wbmdcmepulse.models.utils.Constants;
import com.webmd.wbmdcmepulse.models.utils.Extensions;
import com.webmd.wbmdcmepulse.models.utils.Utilities;
import com.webmd.wbmdcmepulse.omniture.OmnitureData;
import com.webmd.wbmdomnituremanager.WBMDOmnitureManager;
import com.webmd.wbmdomnituremanager.WBMDOmnitureModule;
import com.webmd.wbmdproffesionalauthentication.model.UserProfession;
import java.util.HashMap;

public class CmeContinueActivity extends CmeBaseActivity {
    private final String TAG = CmeContinueActivity.class.getSimpleName();
    private String mArticleAssetId;
    private ArticleCopyTextView mArticleCongratulationsTextView;
    private String mArticleId;
    private String mArticleTitle;
    private Context mContext;
    private View mDividerView;
    private CustomFontTextView mFormMessageTexView;
    private boolean mIsEvalRequired;
    private boolean mIsEvalTaken;
    private boolean mIsGotoEval;
    private boolean mIsMocEligible;
    private boolean mIsStandAlone;
    private Button mMocFormButton;
    private CustomFontTextView mMocInstructionsTextView;
    private UserProfession mProfession;
    private String mQnaId;
    private Questionnaire mQuestionnaire;

    /* access modifiers changed from: package-private */
    public void trackOmniturePageView() {
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.cme_activity_continue);
        initializeToolBar();
        this.mContext = this;
        this.mQnaId = getIntent().getStringExtra(Constants.BUNDLE_KEY_QNA_ID);
        this.mIsEvalTaken = getIntent().getBooleanExtra(Constants.BUNDLE_KEY_EVAL_IS_TAKEN, false);
        this.mIsEvalRequired = getIntent().getBooleanExtra(Constants.BUNDLE_KEY_EVAL_REQUIRED, false);
        this.mQuestionnaire = (Questionnaire) getIntent().getParcelableExtra(Constants.BUNDLE_KEY_QNA);
        this.mArticleTitle = getIntent().getStringExtra(Constants.BUNDLE_KEY_ARTICLE_TITLE);
        this.mIsMocEligible = getIntent().getBooleanExtra(Constants.BUNDLE_KEY_IS_MOC_ELIGIBLE, false);
        this.mIsStandAlone = getIntent().getBooleanExtra(Constants.BUNDLE_KEY_EVAL_STAND_ALONE, false);
        this.mIsGotoEval = getIntent().getBooleanExtra(Constants.BUNDLE_KEY_GOTO_EVAL, false);
        this.mArticleId = getIntent().getStringExtra(Constants.BUNDLE_KEY_ARTICLE_ID);
        this.mProfession = this.mUserProfile.getProfessionProfile();
        setTitleBar(getResources().getString(R.string.article_congratulations_header));
        setUpEvaluationButton();
        setUpCmeTrackerButton();
        setCongratulationsText();
        setUpMocFormButton();
        if (this.mIsGotoEval) {
            launchCMEEvalutaion();
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        if (!isLockScreenVisible()) {
            HashMap hashMap = new HashMap();
            hashMap.put("wapp.asset", new ChronicleIDUtil().generateAssetId(this.mArticleId, this.mArticleAssetId, String.format(OmnitureData.PAGE_NAME_CONTINUE_VIEW, new Object[]{this.mQnaId})));
            WBMDOmnitureManager.sendPageView(String.format(OmnitureData.PAGE_NAME_CONTINUE_VIEW, new Object[]{this.mQnaId}), hashMap, (WBMDOmnitureModule) null);
        }
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i != 6000) {
            if (i == 8000 && i2 == 9 && intent != null && intent.getExtras() != null) {
                boolean booleanExtra = intent.getBooleanExtra(Constants.BUNDLE_KEY_EVAL_IS_TAKEN, false);
                if (!this.mIsGotoEval || booleanExtra) {
                    if (booleanExtra) {
                        this.mIsEvalTaken = true;
                    }
                    setUpEvaluationButton();
                    return;
                }
                finish();
            }
        }
    }

    public void onBackPressed() {
        if (!Extensions.isStringNullOrEmpty(this.mQnaId)) {
            closeContinueActivity();
        } else {
            super.onBackPressed();
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_done, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        int itemId = menuItem.getItemId();
        if (itemId == 16908332) {
            closeContinueActivity();
            return true;
        } else if (itemId != R.id.done) {
            return super.onOptionsItemSelected(menuItem);
        } else {
            if (Utilities.goToHomeScreen(this)) {
                finish();
            }
            return true;
        }
    }

    private void setCongratulationsText() {
        this.mArticleCongratulationsTextView = (ArticleCopyTextView) findViewById(R.id.article_congratulations_text_view);
        this.mFormMessageTexView = (CustomFontTextView) findViewById(R.id.form_msg);
        boolean isUserMocEligible = Utilities.isUserMocEligible(this, this.mUserProfile);
        if (this.mIsMocEligible && Extensions.isStringNullOrEmpty(this.mProfession.getAbimId(this)) && isUserMocEligible) {
            this.mArticleCongratulationsTextView.setVisibility(0);
        } else if (!this.mIsMocEligible || !isUserMocEligible) {
            this.mArticleCongratulationsTextView.setVisibility(0);
        } else if (!this.mIsEvalRequired || this.mIsEvalTaken) {
            this.mArticleCongratulationsTextView.setText(getString(R.string.moc_form_congratulations));
            this.mArticleCongratulationsTextView.setVisibility(0);
        }
        if (this.mIsEvalTaken) {
            ((CustomFontTextView) findViewById(R.id.form_msg)).setVisibility(0);
        }
    }

    private void setUpMocFormButton() {
        try {
            if (this.mIsMocEligible && Utilities.isUserMocEligible(this, this.mUserProfile) && Extensions.isStringNullOrEmpty(this.mProfession.getAbimId(this))) {
                View findViewById = findViewById(R.id.divider);
                this.mDividerView = findViewById;
                findViewById.setVisibility(0);
                CustomFontTextView customFontTextView = (CustomFontTextView) findViewById(R.id.moc_instructions_text_view);
                this.mMocInstructionsTextView = customFontTextView;
                customFontTextView.setVisibility(0);
                Button button = (Button) findViewById(R.id.moc_form_button);
                this.mMocFormButton = button;
                button.setVisibility(0);
                this.mMocFormButton.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        CmeContinueActivity.this.startActivityForResult(new Intent(CmeContinueActivity.this, CmeAbimVerificationActivity.class), Constants.REQUEST_CODE_MOC_ID);
                    }
                });
            } else if (this.mIsMocEligible && !Extensions.isStringNullOrEmpty(this.mProfession.getAbimId(this))) {
                this.mArticleCongratulationsTextView.setText(getResources().getString(R.string.moc_form_congratulations));
                this.mArticleCongratulationsTextView.setVisibility(0);
            }
        } catch (Exception e) {
            Trace.e(this.TAG, e.getMessage());
        }
    }

    private void setUpEvaluationButton() {
        Button button = (Button) findViewById(R.id.evaluation_button);
        if (this.mIsEvalRequired || this.mIsEvalTaken) {
            button.setVisibility(8);
        } else {
            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    CmeContinueActivity.this.launchCMEEvalutaion();
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void launchCMEEvalutaion() {
        Intent intent = new Intent(this, CmeEvaluationActivity.class);
        intent.putExtra(Constants.BUNDLE_KEY_QNA_ID, this.mQnaId);
        intent.putExtra(Constants.BUNDLE_KEY_IS_MOC_ELIGIBLE, this.mIsMocEligible);
        intent.putExtra(Constants.BUNDLE_KEY_USER_PROFILE, this.mUserProfile);
        intent.putExtra(Constants.BUNDLE_KEY_EVAL_STAND_ALONE, this.mIsStandAlone);
        startActivityForResult(intent, Constants.CMEACTIVITY_REQUEST_CODE);
    }

    private void setUpCmeTrackerButton() {
        Button button = (Button) findViewById(R.id.cme_tracker_button);
        button.setText(Utilities.getTrackerLabel(this));
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(CmeContinueActivity.this, CMETrackerActivity.class);
                intent.putExtra(Constants.RETURN_ACTIVITY, Constants.HOME_ACTIVITY_NAME);
                intent.putExtra(Constants.BUNDLE_KEY_USER_PROFILE, CmeContinueActivity.this.mUserProfile);
                intent.addFlags(67108864);
                CmeContinueActivity.this.startActivity(intent);
                CmeContinueActivity.this.finish();
            }
        });
    }

    private void setTitleBar(String str) {
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setTitle((CharSequence) str);
        }
    }

    private void closeContinueActivity() {
        Intent intent = new Intent();
        intent.putExtra(Constants.BUNDLE_KEY_EVAL_IS_TAKEN, this.mIsEvalTaken);
        setResult(9, intent);
        finish();
    }
}
