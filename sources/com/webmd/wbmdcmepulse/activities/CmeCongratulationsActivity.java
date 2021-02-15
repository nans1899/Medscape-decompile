package com.webmd.wbmdcmepulse.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import androidx.appcompat.app.ActionBar;
import androidx.core.view.ViewCompat;
import com.wbmd.wbmdcommons.callbacks.ICallbackEvent;
import com.wbmd.wbmdcommons.customviews.CustomFontTextView;
import com.wbmd.wbmdcommons.logging.Trace;
import com.wbmd.wbmdcommons.utils.ChronicleIDUtil;
import com.webmd.wbmdcmepulse.R;
import com.webmd.wbmdcmepulse.customviews.ArticleCopyTextView;
import com.webmd.wbmdcmepulse.models.CMEPulseException;
import com.webmd.wbmdcmepulse.models.articles.ActivityTest;
import com.webmd.wbmdcmepulse.models.articles.Answer;
import com.webmd.wbmdcmepulse.models.articles.Question;
import com.webmd.wbmdcmepulse.models.articles.Questionnaire;
import com.webmd.wbmdcmepulse.models.utils.Constants;
import com.webmd.wbmdcmepulse.models.utils.Extensions;
import com.webmd.wbmdcmepulse.models.utils.Utilities;
import com.webmd.wbmdcmepulse.omniture.OmnitureData;
import com.webmd.wbmdcmepulse.providers.CmeArticleProvider;
import com.webmd.wbmdomnituremanager.WBMDOmnitureManager;
import com.webmd.wbmdomnituremanager.WBMDOmnitureModule;
import java.util.HashMap;
import java.util.List;

public class CmeCongratulationsActivity extends CmeBaseActivity {
    private final int MEDSCAPE_RED = Constants.COLOR_MEDSCAPE_RED;
    /* access modifiers changed from: private */
    public final String TAG = CmeCongratulationsActivity.class.getSimpleName();
    private LinearLayout mAnswerExplanationsLinearLayout;
    /* access modifiers changed from: private */
    public String mArticleAssetId;
    /* access modifiers changed from: private */
    public String mArticleId;
    /* access modifiers changed from: private */
    public String mArticleTitle;
    private Context mContext;
    /* access modifiers changed from: private */
    public boolean mIsEvaluationRequired;
    /* access modifiers changed from: private */
    public boolean mIsEvaluationTaken;
    /* access modifiers changed from: private */
    public boolean mIsMocEligible;
    /* access modifiers changed from: private */
    public String mQnaId;
    /* access modifiers changed from: private */
    public Questionnaire mQuestionnaire;

    public void onBackPressed() {
    }

    /* access modifiers changed from: package-private */
    public void trackOmniturePageView() {
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.cme_activity_congratulations);
        initializeToolBar();
        this.mContext = this;
        this.mArticleTitle = getIntent().getStringExtra(Constants.BUNDLE_KEY_ARTICLE_TITLE);
        this.mQnaId = getIntent().getStringExtra(Constants.BUNDLE_KEY_QNA_ID);
        this.mIsMocEligible = getIntent().getBooleanExtra(Constants.BUNDLE_KEY_IS_MOC_ELIGIBLE, false);
        this.mIsEvaluationRequired = getIntent().getBooleanExtra(Constants.BUNDLE_KEY_EVAL_REQUIRED, false);
        this.mIsEvaluationTaken = getIntent().getBooleanExtra(Constants.BUNDLE_KEY_EVAL_IS_TAKEN, false);
        this.mQuestionnaire = (Questionnaire) getIntent().getParcelableExtra(Constants.BUNDLE_KEY_QNA);
        this.mArticleId = getIntent().getStringExtra(Constants.BUNDLE_KEY_ARTICLE_ID);
        this.mArticleAssetId = getIntent().getStringExtra(Constants.BUNDLE_KEY_ASSET_ID);
        this.mAnswerExplanationsLinearLayout = (LinearLayout) findViewById(R.id.qna_explanation);
        setTitleBar(getResources().getString(R.string.article_congratulations_activity_title));
        setUpContinueButton();
        setUpQnaResponse();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        if (!isLockScreenVisible()) {
            HashMap hashMap = new HashMap();
            hashMap.put("wapp.asset", new ChronicleIDUtil().generateAssetId(this.mArticleId, this.mArticleAssetId, String.format(OmnitureData.PAGE_NAME_CONGRATULATIONS_VIEW, new Object[]{this.mQnaId})));
            WBMDOmnitureManager.sendPageView(String.format(OmnitureData.PAGE_NAME_CONGRATULATIONS_VIEW, new Object[]{this.mQnaId}), hashMap, (WBMDOmnitureModule) null);
        }
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 8000 && i2 == 9 && intent != null && intent.getExtras() != null && intent.getBooleanExtra(Constants.BUNDLE_KEY_EVAL_IS_TAKEN, false)) {
            this.mIsEvaluationTaken = true;
        }
    }

    private void setUpContinueButton() {
        Button button = (Button) findViewById(R.id.continue_button);
        if (button != null) {
            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Intent intent;
                    if (!CmeCongratulationsActivity.this.mIsEvaluationRequired) {
                        intent = new Intent(CmeCongratulationsActivity.this, CmeContinueActivity.class);
                    } else {
                        intent = new Intent(CmeCongratulationsActivity.this, CmeEvaluationActivity.class);
                    }
                    intent.putExtra(Constants.BUNDLE_KEY_ARTICLE_TITLE, CmeCongratulationsActivity.this.mArticleTitle);
                    intent.putExtra(Constants.BUNDLE_KEY_QNA_ID, CmeCongratulationsActivity.this.mQnaId);
                    intent.putExtra(Constants.BUNDLE_KEY_IS_MOC_ELIGIBLE, CmeCongratulationsActivity.this.mIsMocEligible);
                    intent.putExtra(Constants.BUNDLE_KEY_QNA, CmeCongratulationsActivity.this.mQuestionnaire);
                    intent.putExtra(Constants.BUNDLE_KEY_EVAL_REQUIRED, CmeCongratulationsActivity.this.mIsEvaluationRequired);
                    intent.putExtra(Constants.BUNDLE_KEY_EVAL_IS_TAKEN, CmeCongratulationsActivity.this.mIsEvaluationTaken);
                    intent.putExtra(Constants.BUNDLE_KEY_ARTICLE_ID, CmeCongratulationsActivity.this.mArticleId);
                    intent.putExtra(Constants.BUNDLE_KEY_USER_PROFILE, CmeCongratulationsActivity.this.mUserProfile);
                    intent.putExtra(Constants.BUNDLE_KEY_ASSET_ID, CmeCongratulationsActivity.this.mArticleAssetId);
                    CmeCongratulationsActivity.this.startActivityForResult(intent, Constants.CMEACTIVITY_REQUEST_CODE);
                }
            });
        }
    }

    private void setUpQnaResponse() {
        CustomFontTextView customFontTextView;
        if (!Extensions.isStringNullOrEmpty(this.mArticleTitle) && (customFontTextView = (CustomFontTextView) findViewById(R.id.article_title_text_view)) != null) {
            customFontTextView.setText(this.mArticleTitle);
        }
        Questionnaire questionnaire = this.mQuestionnaire;
        if (questionnaire == null || questionnaire.tests.size() < 0) {
            new CmeArticleProvider(this).getQna(this.mQnaId, new ICallbackEvent<Questionnaire, CMEPulseException>() {
                public void onError(CMEPulseException cMEPulseException) {
                    Trace.e(CmeCongratulationsActivity.this.TAG, cMEPulseException.getMessage());
                }

                public void onCompleted(Questionnaire questionnaire) {
                    Questionnaire unused = CmeCongratulationsActivity.this.mQuestionnaire = questionnaire;
                    if (CmeCongratulationsActivity.this.mQuestionnaire.tests.size() == 1) {
                        CmeCongratulationsActivity cmeCongratulationsActivity = CmeCongratulationsActivity.this;
                        cmeCongratulationsActivity.addQnaResponse(cmeCongratulationsActivity.mQuestionnaire.tests.get(0).questions);
                    } else {
                        for (ActivityTest next : CmeCongratulationsActivity.this.mQuestionnaire.tests) {
                            if (next.formatType.equals("POST")) {
                                CmeCongratulationsActivity.this.addQnaResponse(next.questions);
                            }
                        }
                    }
                    ProgressBar progressBar = (ProgressBar) CmeCongratulationsActivity.this.findViewById(R.id.progress_bar);
                    if (progressBar != null) {
                        progressBar.setVisibility(8);
                    }
                }
            }, this);
            return;
        }
        if (this.mQuestionnaire.tests.size() == 1) {
            addQnaResponse(this.mQuestionnaire.tests.get(0).questions);
        } else {
            for (ActivityTest next : this.mQuestionnaire.tests) {
                if (next.formatType.equals("POST")) {
                    addQnaResponse(next.questions);
                }
            }
        }
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        if (progressBar != null) {
            progressBar.setVisibility(8);
        }
    }

    /* access modifiers changed from: private */
    public void addQnaResponse(List<Question> list) {
        int i = 1;
        for (Question next : list) {
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
            layoutParams.setMargins(0, 15, 0, 15);
            ArticleCopyTextView articleCopyTextView = new ArticleCopyTextView(this.mContext);
            articleCopyTextView.setTextColor(ViewCompat.MEASURED_STATE_MASK);
            articleCopyTextView.setText(Utilities.getFormattedText(i + ". " + next.questionText));
            articleCopyTextView.setLayoutParams(layoutParams);
            this.mAnswerExplanationsLinearLayout.addView(articleCopyTextView);
            for (Answer next2 : next.answers) {
                if (next2.isCorrect) {
                    ArticleCopyTextView articleCopyTextView2 = new ArticleCopyTextView(this.mContext);
                    articleCopyTextView2.setTextColor(getResources().getColor(R.color.green_positive));
                    articleCopyTextView2.setTypeface(Typeface.DEFAULT_BOLD);
                    articleCopyTextView2.setText("Answer: ");
                    this.mAnswerExplanationsLinearLayout.addView(articleCopyTextView2);
                    ArticleCopyTextView articleCopyTextView3 = new ArticleCopyTextView(this.mContext);
                    articleCopyTextView3.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                    articleCopyTextView3.setText(Utilities.getFormattedText(next2.text));
                    articleCopyTextView3.setLayoutParams(layoutParams);
                    this.mAnswerExplanationsLinearLayout.addView(articleCopyTextView3);
                    if (!Extensions.isStringNullOrEmpty(next2.choiceExplination)) {
                        ArticleCopyTextView articleCopyTextView4 = new ArticleCopyTextView(this.mContext);
                        articleCopyTextView4.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                        articleCopyTextView4.setText(Utilities.getFormattedText(next2.choiceExplination));
                        articleCopyTextView4.setLayoutParams(layoutParams);
                        this.mAnswerExplanationsLinearLayout.addView(articleCopyTextView4);
                    }
                }
            }
            View view = new View(this.mContext);
            LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, 2);
            layoutParams2.setMargins(0, 15, 0, 15);
            view.setBackgroundColor(getResources().getColor(R.color.grey));
            layoutParams2.height = 3;
            view.setLayoutParams(layoutParams2);
            this.mAnswerExplanationsLinearLayout.addView(view);
            i++;
        }
    }

    private void setTitleBar(String str) {
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setTitle((CharSequence) str);
        }
    }
}
