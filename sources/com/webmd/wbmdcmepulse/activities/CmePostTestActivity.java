package com.webmd.wbmdcmepulse.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import androidx.appcompat.app.ActionBar;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.google.android.material.snackbar.Snackbar;
import com.wbmd.wbmdcommons.callbacks.ICallbackEvent;
import com.wbmd.wbmdcommons.customviews.CustomFontTextView;
import com.wbmd.wbmdcommons.logging.Trace;
import com.wbmd.wbmdcommons.utils.ChronicleIDUtil;
import com.webmd.wbmdcmepulse.R;
import com.webmd.wbmdcmepulse.activities.CmePostTestActivity;
import com.webmd.wbmdcmepulse.customviews.ArticleCopyTextView;
import com.webmd.wbmdcmepulse.fragments.ArticleQuestionsFragment;
import com.webmd.wbmdcmepulse.models.CMEPulseException;
import com.webmd.wbmdcmepulse.models.CPEvent;
import com.webmd.wbmdcmepulse.models.articles.ActivityTest;
import com.webmd.wbmdcmepulse.models.articles.Article;
import com.webmd.wbmdcmepulse.models.articles.Question;
import com.webmd.wbmdcmepulse.models.articles.QuestionRequest;
import com.webmd.wbmdcmepulse.models.articles.QuestionResponse;
import com.webmd.wbmdcmepulse.models.articles.Questionnaire;
import com.webmd.wbmdcmepulse.models.interfaces.ITestScoreListener;
import com.webmd.wbmdcmepulse.models.utils.Constants;
import com.webmd.wbmdcmepulse.models.utils.CpEventsLoggingTask;
import com.webmd.wbmdcmepulse.models.utils.Extensions;
import com.webmd.wbmdcmepulse.models.utils.Utilities;
import com.webmd.wbmdcmepulse.omniture.OmnitureData;
import com.webmd.wbmdcmepulse.providers.CmeArticleProvider;
import com.webmd.wbmdomnituremanager.WBMDOmnitureManager;
import com.webmd.wbmdomnituremanager.WBMDOmnitureModule;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CmePostTestActivity extends CmeBaseActivity {
    private final String FORMAT_TYPE_POST = "POST";
    private final String QUESTIONS_TAG = "STATIC_POST_TEST";
    /* access modifiers changed from: private */
    public final String TAG = CmePostTestActivity.class.getSimpleName();
    /* access modifiers changed from: private */
    public Article mArticle;
    /* access modifiers changed from: private */
    public String mArticleId;
    /* access modifiers changed from: private */
    public ArticleQuestionsFragment mArticleQuestionsFragment;
    private String mArticleTitle;
    /* access modifiers changed from: private */
    public Context mContext;
    private boolean mIsEvaluationRequired;
    private boolean mIsFirstSubmit;
    private boolean mIsInitialized;
    private boolean mIsMocEligible;
    /* access modifiers changed from: private */
    public boolean mIsResponded;
    /* access modifiers changed from: private */
    public boolean mIsScorePassing;
    /* access modifiers changed from: private */
    public double mPassScore;
    /* access modifiers changed from: private */
    public Questionnaire mQuestionnaire;
    /* access modifiers changed from: private */
    public Button mQuestionsContinueButton;
    /* access modifiers changed from: private */
    public RelativeLayout mRootLayout;
    private String mSearchedFormId;
    private String mUserId;

    /* access modifiers changed from: private */
    public void trackOmnitureAnswerSubmitted(boolean z, String str) {
    }

    /* access modifiers changed from: package-private */
    public void trackOmniturePageView() {
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.cme_activity_post_test);
        initializeToolBar();
        this.mIsInitialized = false;
        this.mContext = this;
        this.mIsFirstSubmit = true;
        if (bundle == null) {
            this.mPassScore = getIntent().getDoubleExtra(Constants.BUNDLE_KEY_ARTICLE_PASS_SCORE, 75.0d);
            this.mArticleId = getIntent().getStringExtra(Constants.BUNDLE_KEY_ARTICLE_ID);
            this.mUserId = this.mUserProfile.getBasicProfile().getUserId();
            if (!(getIntent() == null || getIntent().getExtras() == null)) {
                setSavedValues(getIntent().getExtras());
            }
        } else {
            setSavedValues(bundle);
        }
        CustomFontTextView customFontTextView = (CustomFontTextView) findViewById(R.id.article_title_text_view);
        customFontTextView.setText(this.mArticleTitle);
        customFontTextView.setTextSize(1, 18.0f);
        ArticleCopyTextView articleCopyTextView = (ArticleCopyTextView) findViewById(R.id.score_statement_text_view);
        articleCopyTextView.setTextColor(ViewCompat.MEASURED_STATE_MASK);
        articleCopyTextView.setText(String.format(getString(R.string.post_test_score_statement), new Object[]{Double.valueOf(this.mPassScore)}));
        this.mRootLayout = (RelativeLayout) findViewById(R.id.post_test_content_frame);
        setTitleBar("Earn CME Credit");
        setUpQna();
    }

    private void trackOmniture(Intent intent) {
        WBMDOmnitureModule wBMDOmnitureModule;
        boolean booleanExtra = intent.getBooleanExtra(Constants.BUNDLE_KEY_CAME_FROM_TRACKER, false);
        String stringExtra = intent.getStringExtra(Constants.BUNDLE_KEY_REFERRING_PAGE);
        String stringExtra2 = intent.getStringExtra(Constants.BUNDLE_KEY_REFERRING_MODULE);
        String stringExtra3 = intent.getStringExtra(Constants.BUNDLE_KEY_REFERRING_LINK);
        HashMap hashMap = new HashMap();
        String format = String.format(OmnitureData.PAGE_NAME_POST_TEST, new Object[]{this.mQuestionnaire.id});
        hashMap.put("wapp.asset", new ChronicleIDUtil().generateAssetId(this.mArticleId, this.mArticle.assetId, format));
        if (!Extensions.isStringNullOrEmpty(stringExtra)) {
            if (booleanExtra) {
                wBMDOmnitureModule = new WBMDOmnitureModule(stringExtra2, stringExtra3, stringExtra);
            } else {
                wBMDOmnitureModule = new WBMDOmnitureModule(stringExtra2, stringExtra3, String.format(OmnitureData.PAGE_NAME_FEED, new Object[]{stringExtra}));
            }
            WBMDOmnitureManager.sendPageView(format, hashMap, wBMDOmnitureModule);
            return;
        }
        WBMDOmnitureManager.sendPageView(format, hashMap, (WBMDOmnitureModule) null);
        if (this.mIsInitialized) {
            this.mArticleQuestionsFragment.setTestScoreListener(getTestScoreListener("STATIC_POST_TEST", this.mSearchedFormId));
        } else {
            this.mIsInitialized = true;
        }
    }

    public void onResume() {
        super.onResume();
        if (!isLockScreenVisible()) {
            trackOmniture(getIntent());
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        bundle.putParcelable(Constants.BUNDLE_KEY_QNA, this.mQuestionnaire);
        bundle.putDouble(Constants.BUNDLE_KEY_ARTICLE_PASS_SCORE, this.mPassScore);
        bundle.putBoolean(Constants.BUNDLE_KEY_EVAL_REQUIRED, this.mIsEvaluationRequired);
        bundle.putString(Constants.BUNDLE_KEY_ARTICLE_TITLE, this.mArticleTitle);
        bundle.putString(Constants.BUNDLE_KEY_USER_ID, this.mUserId);
        bundle.putParcelable(Constants.BUNDLE_KEY_ARTICLE, this.mArticle);
        super.onSaveInstanceState(bundle);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 16908332) {
            return super.onOptionsItemSelected(menuItem);
        }
        finish();
        return true;
    }

    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    private void setSavedValues(Bundle bundle) {
        this.mQuestionnaire = (Questionnaire) bundle.getParcelable(Constants.BUNDLE_KEY_QNA);
        this.mPassScore = bundle.getDouble(Constants.BUNDLE_KEY_ARTICLE_PASS_SCORE, -1.0d);
        this.mIsEvaluationRequired = bundle.getBoolean(Constants.BUNDLE_KEY_EVAL_REQUIRED, false);
        this.mArticleTitle = bundle.getString(Constants.BUNDLE_KEY_ARTICLE_TITLE);
        this.mArticle = (Article) bundle.getParcelable(Constants.BUNDLE_KEY_ARTICLE);
        if (Extensions.isStringNullOrEmpty(this.mUserId)) {
            this.mUserId = bundle.getString(Constants.BUNDLE_KEY_USER_ID);
        }
        this.mIsMocEligible = getIntent().getBooleanExtra(Constants.BUNDLE_KEY_IS_MOC_ELIGIBLE, false);
    }

    private void setUpQna() {
        this.mQuestionsContinueButton = (Button) findViewById(R.id.questions_remaining_continue);
        this.mIsScorePassing = false;
        if (this.mQuestionnaire.tests.size() == 1) {
            setQuestionsContinueButtonState(this.mQuestionnaire.tests.get(0).questions.size(), "STATIC_POST_TEST");
            setUpArticleTestFragment(this.mQuestionnaire.tests.get(0), "STATIC_POST_TEST");
        } else {
            for (ActivityTest next : this.mQuestionnaire.tests) {
                if (next.formatType.equals("POST")) {
                    setQuestionsContinueButtonState(next.questions.size(), "STATIC_POST_TEST");
                    setUpArticleTestFragment(next, "STATIC_POST_TEST");
                }
            }
        }
        ((ProgressBar) findViewById(R.id.progress_bar)).setVisibility(4);
    }

    private void setUpArticleTestFragment(ActivityTest activityTest, String str) {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        new ArrayList();
        this.mSearchedFormId = "";
        List<Question> list = activityTest.questions;
        String str2 = activityTest.id;
        this.mSearchedFormId = str2;
        ArticleQuestionsFragment articleQuestionsFragment = (ArticleQuestionsFragment) supportFragmentManager.findFragmentByTag(str);
        this.mArticleQuestionsFragment = articleQuestionsFragment;
        if (articleQuestionsFragment != null) {
            articleQuestionsFragment.setTestScoreListener(getTestScoreListener(str, str2));
            return;
        }
        this.mArticleQuestionsFragment = ArticleQuestionsFragment.newInstance(list, this.mPassScore, false, getTestScoreListener(str, str2), this.mUserProfile);
        supportFragmentManager.beginTransaction().replace(R.id.content_frame, (Fragment) this.mArticleQuestionsFragment, str).commit();
    }

    private ITestScoreListener getTestScoreListener(final String str, final String str2) {
        return new ITestScoreListener() {
            public void onTestAnswerChanged(int i, boolean z) {
                CmePostTestActivity.this.setQuestionsContinueButtonState(i, str);
            }

            public void onAnswersSubmitted(boolean z, double d, QuestionResponse[] questionResponseArr, boolean z2) {
                CmePostTestActivity.this.handleAnswersSubmitted(z, questionResponseArr, z2, str, str2);
            }
        };
    }

    /* access modifiers changed from: private */
    public void handleAnswersSubmitted(boolean z, QuestionResponse[] questionResponseArr, boolean z2, String str, String str2) {
        if (!Utilities.isNetworkAvailable(this)) {
            new CMEPulseException(getString(R.string.error_connection_required)).showSnackBar(this.mRootLayout, 0, (String) null, (View.OnClickListener) null);
            return;
        }
        QuestionRequest buildQuestionRequest = buildQuestionRequest(questionResponseArr, str2);
        this.mQuestionsContinueButton.setText("Submitting...");
        final boolean z3 = z;
        final QuestionRequest questionRequest = buildQuestionRequest;
        final boolean z4 = z2;
        final String str3 = str;
        new CmeArticleProvider(this).submitQuestionResponseList(buildQuestionRequest, new ICallbackEvent<String, CMEPulseException>() {
            public void onError(CMEPulseException cMEPulseException) {
                Snackbar make = Snackbar.make((View) CmePostTestActivity.this.mRootLayout, (CharSequence) "Service Unavailable.", -1);
                make.getView().setBackgroundColor(CmePostTestActivity.this.getResources().getColor(R.color.app_accent_color));
                make.show();
                CmePostTestActivity.this.mQuestionsContinueButton.setText("Submit");
                Trace.e(CmePostTestActivity.this.TAG, cMEPulseException.getMessage());
            }

            public void onCompleted(String str) {
                String str2;
                CmePostTestActivity.this.mArticleQuestionsFragment.displayValidation();
                boolean unused = CmePostTestActivity.this.mIsScorePassing = z3;
                CmePostTestActivity.this.trackOmnitureAnswerSubmitted(z3, questionRequest.formId);
                if (!CmePostTestActivity.this.mIsResponded) {
                    try {
                        boolean unused2 = CmePostTestActivity.this.mIsResponded = true;
                        new CpEventsLoggingTask(new CPEvent(CmePostTestActivity.this).buildQnaParticipationEvent(CmePostTestActivity.this.mQuestionnaire.id, CmePostTestActivity.this.mArticle.contentGroup, CmePostTestActivity.this.mArticle.blockCode, CmePostTestActivity.this.mArticle.leadConcept, CmePostTestActivity.this.mArticle.leadSpec, CmePostTestActivity.this.mArticleId), CmePostTestActivity.this.mContext).execute(new Void[0]);
                    } catch (Exception e) {
                        Trace.e(CmePostTestActivity.this.TAG, e.getMessage());
                    }
                }
                if (CmePostTestActivity.this.mIsScorePassing) {
                    try {
                        new CpEventsLoggingTask(new CPEvent(CmePostTestActivity.this).buildQnaCompletionEvent(CmePostTestActivity.this.mQuestionnaire.id, CmePostTestActivity.this.mArticle.contentGroup, CmePostTestActivity.this.mArticle.blockCode, CmePostTestActivity.this.mArticle.leadConcept, CmePostTestActivity.this.mArticle.leadSpec, CmePostTestActivity.this.mArticleId), CmePostTestActivity.this.mContext).execute(new Void[0]);
                    } catch (Exception e2) {
                        Trace.e(CmePostTestActivity.this.TAG, e2.getMessage());
                    }
                } else if (!z4) {
                    new Handler().post(new Runnable() {
                        public void run() {
                            View nextIncorrectQuestion = CmePostTestActivity.this.mArticleQuestionsFragment.getNextIncorrectQuestion();
                            if (nextIncorrectQuestion != null) {
                                nextIncorrectQuestion.setFocusable(true);
                                nextIncorrectQuestion.setFocusableInTouchMode(true);
                                nextIncorrectQuestion.requestFocus();
                                nextIncorrectQuestion.clearFocus();
                            }
                        }
                    });
                } else {
                    if (Math.ceil(CmePostTestActivity.this.mPassScore) == CmePostTestActivity.this.mPassScore) {
                        str2 = String.valueOf((int) CmePostTestActivity.this.mPassScore);
                    } else {
                        str2 = String.valueOf(CmePostTestActivity.this.mPassScore);
                    }
                    RelativeLayout access$200 = CmePostTestActivity.this.mRootLayout;
                    Snackbar make = Snackbar.make((View) access$200, (CharSequence) "You must achieve a minimum score of " + str2 + "% to earn credit for this activity.", -1);
                    make.getView().setBackgroundColor(CmePostTestActivity.this.getResources().getColor(R.color.app_accent_color));
                    make.show();
                }
                CmePostTestActivity.this.setQuestionsContinueButtonState(0, str3);
            }
        }, this);
    }

    /* access modifiers changed from: private */
    public void setQuestionsContinueButtonState(int i, final String str) {
        if (this.mIsScorePassing) {
            Intent intent = new Intent(this, CmeCongratulationsActivity.class);
            intent.putExtra(Constants.BUNDLE_KEY_ARTICLE_TITLE, this.mArticleTitle);
            intent.putExtra(Constants.BUNDLE_KEY_QNA_ID, this.mQuestionnaire.id);
            intent.putExtra(Constants.BUNDLE_KEY_EVAL_REQUIRED, this.mIsEvaluationRequired);
            intent.putExtra(Constants.BUNDLE_KEY_IS_MOC_ELIGIBLE, this.mIsMocEligible);
            intent.putExtra(Constants.BUNDLE_KEY_ARTICLE_ID, this.mArticleId);
            intent.putExtra(Constants.BUNDLE_KEY_USER_PROFILE, this.mUserProfile);
            intent.putExtra(Constants.BUNDLE_KEY_ASSET_ID, this.mArticle.assetId);
            intent.addFlags(67108864);
            intent.addFlags(32768);
            intent.addFlags(268435456);
            startActivity(intent);
            finish();
        } else if (i > 0) {
            this.mQuestionsContinueButton.setTextColor(-1);
            this.mQuestionsContinueButton.setBackgroundDrawable(getResources().getDrawable(R.drawable.grey_button_ripple));
            if (i == 1) {
                Button button = this.mQuestionsContinueButton;
                button.setText(i + " Question Remaining to Proceed");
            } else {
                Button button2 = this.mQuestionsContinueButton;
                button2.setText(i + " Questions Remaining to Proceed");
            }
            this.mQuestionsContinueButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    new Handler().post(new Runnable() {
                        public void run() {
                            View nextIncorrectQuestion = CmePostTestActivity.this.mArticleQuestionsFragment.getNextIncorrectQuestion();
                            if (nextIncorrectQuestion != null) {
                                nextIncorrectQuestion.setFocusable(true);
                                nextIncorrectQuestion.setFocusableInTouchMode(true);
                                nextIncorrectQuestion.requestFocus();
                                nextIncorrectQuestion.clearFocus();
                            }
                        }
                    });
                }
            });
        } else {
            this.mQuestionsContinueButton.setText("Submit");
            this.mQuestionsContinueButton.setTextColor(-1);
            this.mQuestionsContinueButton.setBackgroundDrawable(getResources().getDrawable(R.drawable.cme_continue_button_ripple));
            this.mQuestionsContinueButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!Utilities.isNetworkAvailable(CmePostTestActivity.this)) {
                        new CMEPulseException(CmePostTestActivity.this.getString(R.string.error_connection_required)).showSnackBar(CmePostTestActivity.this.mRootLayout, 0, CmePostTestActivity.this.getString(R.string.retry), new View.OnClickListener(str) {
                            public final /* synthetic */ String f$1;

                            {
                                this.f$1 = r2;
                            }

                            public final void onClick(View view) {
                                CmePostTestActivity.AnonymousClass4.this.lambda$onClick$0$CmePostTestActivity$4(this.f$1, view);
                            }
                        });
                    } else {
                        CmePostTestActivity.this.submitAnswers(str);
                    }
                }

                public /* synthetic */ void lambda$onClick$0$CmePostTestActivity$4(String str, View view) {
                    CmePostTestActivity.this.submitAnswers(str);
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void submitAnswers(String str) {
        ArticleQuestionsFragment articleQuestionsFragment = (ArticleQuestionsFragment) getSupportFragmentManager().findFragmentByTag(str);
        if (articleQuestionsFragment != null) {
            articleQuestionsFragment.submitAnswers();
        }
    }

    private QuestionRequest buildQuestionRequest(QuestionResponse[] questionResponseArr, String str) {
        QuestionRequest questionRequest = new QuestionRequest();
        questionRequest.questionResponseList = questionResponseArr;
        questionRequest.questionnaireId = this.mQuestionnaire.id;
        questionRequest.formId = str;
        questionRequest.guid = this.mUserId;
        questionRequest.questionType = "POST";
        return questionRequest;
    }

    private void setTitleBar(String str) {
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setTitle((CharSequence) str);
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
    }
}
