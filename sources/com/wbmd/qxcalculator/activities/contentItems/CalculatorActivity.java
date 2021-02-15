package com.wbmd.qxcalculator.activities.contentItems;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.gms.ads.doubleclick.PublisherAdView;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.qxmd.qxrecyclerview.QxRecyclerView;
import com.qxmd.qxrecyclerview.QxRecyclerViewAdapter;
import com.qxmd.qxrecyclerview.QxRecyclerViewRowItem;
import com.qxmd.qxrecyclerview.QxRecyclerViewRowItemWrapper;
import com.qxmd.qxrecyclerview.SmoothScrollLinearLayoutManager;
import com.wbmd.omniture.utils.OmnitureHelper;
import com.wbmd.qxcalculator.R;
import com.wbmd.qxcalculator.managers.ContentItemLaunchManager;
import com.wbmd.qxcalculator.managers.DataManager;
import com.wbmd.qxcalculator.managers.EventsManager;
import com.wbmd.qxcalculator.managers.QxFirebaseEventManager;
import com.wbmd.qxcalculator.managers.UserManager;
import com.wbmd.qxcalculator.model.contentItems.calculator.Calculator;
import com.wbmd.qxcalculator.model.contentItems.calculator.ErrorCheck;
import com.wbmd.qxcalculator.model.contentItems.calculator.ExtraSection;
import com.wbmd.qxcalculator.model.contentItems.calculator.ExtraSectionItem;
import com.wbmd.qxcalculator.model.contentItems.calculator.Question;
import com.wbmd.qxcalculator.model.contentItems.calculator.Result;
import com.wbmd.qxcalculator.model.contentItems.common.ContentItem;
import com.wbmd.qxcalculator.model.db.DBUser;
import com.wbmd.qxcalculator.model.rowItems.calculator.ChartImageRowItem;
import com.wbmd.qxcalculator.model.rowItems.calculator.ErrorCheckRowItem;
import com.wbmd.qxcalculator.model.rowItems.calculator.ExtraSectionGroupRowItem;
import com.wbmd.qxcalculator.model.rowItems.calculator.ExtraSectionHeaderRowItem;
import com.wbmd.qxcalculator.model.rowItems.calculator.ExtraSectionItemRowItem;
import com.wbmd.qxcalculator.model.rowItems.calculator.NoResultRowItem;
import com.wbmd.qxcalculator.model.rowItems.calculator.QuestionSummaryRowItem;
import com.wbmd.qxcalculator.model.rowItems.calculator.ResultDefaultRowItem;
import com.wbmd.qxcalculator.model.rowItems.calculator.ResultNoAnswerRowItem;
import com.wbmd.qxcalculator.model.rowItems.calculator.ResultNoteDefaultRowItem;
import com.wbmd.qxcalculator.model.rowItems.calculator.ResultNoteSectionTitleRowItem;
import com.wbmd.qxcalculator.model.rowItems.calculator.ResultNoteWTitleSubTitleRowItem;
import com.wbmd.qxcalculator.model.rowItems.headers.DefaultHeaderNoTitleRowItem;
import com.wbmd.qxcalculator.model.rowItems.headers.DefaultHeaderRowItem;
import com.wbmd.qxcalculator.util.AnalyticsHandler;
import com.wbmd.qxcalculator.util.ColorHelper;
import com.wbmd.qxcalculator.util.FirebaseEventsConstants;
import com.wbmd.qxcalculator.util.SharedPreferenceHelper;
import com.wbmd.qxcalculator.util.Util;
import com.wbmd.qxcalculator.views.ArrowView;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CalculatorActivity extends ContentItemActivity implements QxRecyclerViewAdapter.OnRecyclerViewRowItemClickedListener, QxRecyclerViewAdapter.OnRecyclerViewRowItemExpandCollapseListener {
    public static final String KEY_ALL_QUESTIONS_ALREADY_ANSWERED = "CalculatorActivity.KEY_ALL_QUESTIONS_ALREADY_ANSWERED";
    private static final String KEY_EXPANDED_IDS = "KEY_EXPANDED_IDS";
    public static final String KEY_EXTRA_CALCULATOR = "CalculatorActivity.KEY_EXTRA_CALCULATOR";
    public static final String KEY_EXTRA_CALC_FROM_SECTION = "CalculatorActivity.KEY_EXTRA_CALC_FROM_SECTION";
    public static final String KEY_EXTRA_IS_SUB_CALC = "CalculatorActivity.KEY_EXTRA_IS_SUB_CALC";
    public static final String KEY_EXTRA_SELECTED_QUESTION = "CalculatorActivity.KEY_EXTRA_SELECTED_QUESTION";
    public static final String KEY_EXTRA_SUB_CALC_QUESTION = "CalculatorActivity.KEY_EXTRA_SUB_CALC_QUESTION";
    public static final String KEY_EXTRA_SUB_CALC_QUESTION_LINKED_CALC_INDEX = "CalculatorActivity.KEY_EXTRA_SUB_CALC_QUESTION_LINKED_CALC_INDEX";
    private static final String KEY_SHOULD_AUTO_ENTER_FIRST_QUESTION = "CalculatorActivity.KEY_SHOULD_AUTO_ENTER_FIRST_QUESTION";
    private static final String KEY_SHOULD_HAS_SHOWN_LUNCH_BAR = "CalculatorActivity.KEY_SHOULD_HAS_SHOWN_LUNCH_BAR";
    private static final String KEY_SHOULD_SCROLL_TO_RESULTS = "CalculatorActivity.KEY_SHOULD_SCROLL_TO_RESULTS";
    protected static final int REQUEST_CODE_CALCULATE = 1;
    /* access modifiers changed from: protected */
    public QxRecyclerViewAdapter adapter;
    private List<String> expandedRowIds;
    private View greyDisabledOverlay;
    private boolean hasShownLunchBar = false;
    /* access modifiers changed from: private */
    public boolean isActivityOnTop = false;
    protected boolean isSubCalc;
    private SmoothScrollLinearLayoutManager layoutManager;
    /* access modifiers changed from: protected */
    public QxRecyclerView listView;
    private PublisherAdView mAdView;
    protected List<QuestionSummaryRowItem> questionSummaryRowItems;
    /* access modifiers changed from: private */
    public View scrollForMoreResultsArrowView;
    protected String sectionCalculatorWasOpenedFrom;
    /* access modifiers changed from: private */
    public boolean shouldAutoEnterFirstQuestion = false;
    public boolean shouldScrollToResults = true;
    /* access modifiers changed from: private */
    public boolean shouldUpdateStatusBarColor = false;
    protected Question subCalcQuestion;
    protected int subCalcQuestionLinkedCalcIndex;

    /* access modifiers changed from: protected */
    public void dialogDismissed() {
    }

    public int getStatusBarColor() {
        int color = getResources().getColor(R.color.status_bar_color_default);
        if (!this.shouldAutoEnterFirstQuestion) {
            return color;
        }
        return ColorHelper.getInstance().addColors(color, getResources().getColor(R.color.calculator_disable_overlay));
    }

    public int getActionBarColor() {
        return getResources().getColor(R.color.action_bar_color_default);
    }

    /* access modifiers changed from: protected */
    public int getLayoutResourceId() {
        return R.layout.activity_calculator;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Log.d("API", "CalculatorActivity ONCREATE");
        if (this.contentItem != null || !requiresContentItem()) {
            boolean z = false;
            this.isSubCalc = getIntent().getBooleanExtra(KEY_EXTRA_IS_SUB_CALC, false);
            this.subCalcQuestion = (Question) getIntent().getParcelableExtra(KEY_EXTRA_SUB_CALC_QUESTION);
            this.subCalcQuestionLinkedCalcIndex = getIntent().getIntExtra(KEY_EXTRA_SUB_CALC_QUESTION_LINKED_CALC_INDEX, 0);
            this.sectionCalculatorWasOpenedFrom = getIntent().getStringExtra(KEY_EXTRA_CALC_FROM_SECTION);
            DBUser dbUser = UserManager.getInstance().getDbUser();
            if (dbUser.getAutoEnterFirstQuestion() != null) {
                z = dbUser.getAutoEnterFirstQuestion().booleanValue();
            }
            this.shouldAutoEnterFirstQuestion = z;
            setupAdapter();
            ViewGroup viewGroup = (ViewGroup) findViewById(R.id.container_view);
            this.scrollForMoreResultsArrowView = findViewById(R.id.arrow_view);
            QxRecyclerView qxRecyclerView = (QxRecyclerView) findViewById(R.id.recycler_view);
            this.listView = qxRecyclerView;
            qxRecyclerView.setAdapter(this.adapter);
            SmoothScrollLinearLayoutManager smoothScrollLinearLayoutManager = new SmoothScrollLinearLayoutManager(this);
            this.layoutManager = smoothScrollLinearLayoutManager;
            smoothScrollLinearLayoutManager.setOrientation(1);
            this.layoutManager.setSnapPreference(-1);
            this.listView.setLayoutManager(this.layoutManager);
            this.listView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                public void onScrolled(RecyclerView recyclerView, int i, int i2) {
                    super.onScrolled(recyclerView, i, i2);
                    if (recyclerView.canScrollVertically(1)) {
                        CalculatorActivity.this.scrollForMoreResultsArrowView.setVisibility(0);
                    } else {
                        CalculatorActivity.this.scrollForMoreResultsArrowView.setVisibility(4);
                    }
                }
            });
            this.greyDisabledOverlay = findViewById(R.id.grey_overlay);
            PublisherAdView publisherAdView = (PublisherAdView) findViewById(R.id.adView);
            this.mAdView = publisherAdView;
            publisherAdView.setVisibility(8);
            if (!this.isSubCalc && bundle != null) {
                this.shouldAutoEnterFirstQuestion = bundle.getBoolean(KEY_SHOULD_AUTO_ENTER_FIRST_QUESTION);
                this.shouldScrollToResults = bundle.getBoolean(KEY_SHOULD_SCROLL_TO_RESULTS);
                this.hasShownLunchBar = bundle.getBoolean(KEY_SHOULD_HAS_SHOWN_LUNCH_BAR);
                ArrayList<String> stringArrayList = bundle.getStringArrayList(KEY_EXPANDED_IDS);
                this.expandedRowIds = stringArrayList;
                buildCalculatorRowItems(stringArrayList);
            }
            setBarColors(getStatusBarColor(), getActionBarColor());
            String str = this.sectionCalculatorWasOpenedFrom;
            if (str != null && str.equalsIgnoreCase(getString(R.string.feed))) {
                SharedPreferenceHelper.getInstance().setNumberOfCalculatorsOpened(SharedPreferenceHelper.getInstance().getNumberOfCalculatorsOpened() + 1);
            }
            sendFirebaseEventForCalculatorPath(FirebaseEventsConstants.VIEWED_CALCULATOR);
            sendPageNameForOmniture();
            return;
        }
        finish();
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (!this.isSubCalc) {
            bundle.putStringArrayList(KEY_EXPANDED_IDS, getExpandedRowIds());
            bundle.putBoolean(KEY_SHOULD_AUTO_ENTER_FIRST_QUESTION, this.shouldAutoEnterFirstQuestion);
            bundle.putBoolean(KEY_SHOULD_SCROLL_TO_RESULTS, this.shouldScrollToResults);
            bundle.putBoolean(KEY_SHOULD_HAS_SHOWN_LUNCH_BAR, this.hasShownLunchBar);
        }
    }

    public void setupAdapter() {
        if (this.adapter == null) {
            Log.d("API", "CalculatorActivity ONCREATE adapter null");
            QxRecyclerViewAdapter qxRecyclerViewAdapter = new QxRecyclerViewAdapter();
            this.adapter = qxRecyclerViewAdapter;
            qxRecyclerViewAdapter.setHasStableIds(false);
            this.adapter.setOnClickListener(this);
            this.adapter.setOnExpandCollapseListener(this);
        }
    }

    private ArrayList<String> getExpandedRowIds() {
        ArrayList<String> arrayList = new ArrayList<>();
        for (QxRecyclerViewRowItem next : this.adapter.getRowItems()) {
            if (next.isExpanded) {
                arrayList.add(next.getId());
            }
        }
        return arrayList;
    }

    public void onStart() {
        super.onStart();
        if (this.shouldUpdateStatusBarColor) {
            setBarColors(getStatusBarColor(), getActionBarColor());
            this.shouldUpdateStatusBarColor = false;
        }
    }

    public void onResume() {
        super.onResume();
        this.isActivityOnTop = true;
        Log.d("API", "CalculatorActivity onResume, isFinishing " + isFinishing());
        AnalyticsHandler.getInstance().trackPageView(this, "summary_screen");
        if (!isFinishing()) {
            int i = 0;
            if (this.isSubCalc) {
                openQuestion(0);
            } else {
                View view = this.greyDisabledOverlay;
                if (!this.shouldAutoEnterFirstQuestion) {
                    i = 8;
                }
                view.setVisibility(i);
                if (!this.adapter.getHasBeenInitialized()) {
                    buildCalculatorRowItems((List<String>) null);
                }
                boolean booleanValue = UserManager.getInstance().getDbUser().getShouldShowPromptForAutoEnterFirstQuestion().booleanValue();
                boolean allQuestionsAnswered = this.contentItem.calculator.allQuestionsAnswered();
                if (!this.shouldAutoEnterFirstQuestion && booleanValue && !this.hasShownLunchBar && !allQuestionsAnswered) {
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            if (CalculatorActivity.this.isActivityOnTop) {
                                final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(CalculatorActivity.this);
                                View inflate = CalculatorActivity.this.getLayoutInflater().inflate(R.layout.lunch_auto_enter_first_question, (ViewGroup) null);
                                ((TextView) inflate.findViewById(R.id.button_yes)).setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View view) {
                                        DataManager.getInstance().setAutoEnterFirstQuestion(true);
                                        DataManager.getInstance().setShouldShowAutoEnterFirstQuestionPrompt(false);
                                        bottomSheetDialog.dismiss();
                                    }
                                });
                                ((TextView) inflate.findViewById(R.id.button_no)).setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View view) {
                                        DataManager.getInstance().setAutoEnterFirstQuestion(false);
                                        DataManager.getInstance().setShouldShowAutoEnterFirstQuestionPrompt(false);
                                        bottomSheetDialog.dismiss();
                                    }
                                });
                                bottomSheetDialog.setContentView(inflate);
                                bottomSheetDialog.show();
                                bottomSheetDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                                    public void onDismiss(DialogInterface dialogInterface) {
                                        CalculatorActivity.this.dialogDismissed();
                                    }
                                });
                            }
                        }
                    }, 350);
                } else if (this.shouldAutoEnterFirstQuestion) {
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            CalculatorActivity.this.openQuestion(0);
                            boolean unused = CalculatorActivity.this.shouldAutoEnterFirstQuestion = false;
                            boolean unused2 = CalculatorActivity.this.shouldUpdateStatusBarColor = true;
                        }
                    }, 550);
                }
            }
        }
        this.shouldScrollToResults = true;
    }

    public void onPause() {
        super.onPause();
        this.isActivityOnTop = false;
        this.expandedRowIds = getExpandedRowIds();
    }

    public void onStop() {
        super.onStop();
    }

    /* access modifiers changed from: protected */
    public void buildCalculatorRowItems(List<String> list) {
        String str;
        QxRecyclerViewRowItem buildExtraSectionItem;
        this.adapter.reset();
        Calculator calculator = this.contentItem.calculator;
        this.questionSummaryRowItems = new ArrayList();
        for (Question questionSummaryRowItem : calculator.questions) {
            this.questionSummaryRowItems.add(new QuestionSummaryRowItem(questionSummaryRowItem, this));
        }
        boolean allQuestionsAnswered = calculator.allQuestionsAnswered();
        if (allQuestionsAnswered) {
            str = getString(R.string.question_header_answered);
        } else {
            str = getString(R.string.question_header_unanswered);
        }
        this.adapter.addSectionWithHeaderItem(new DefaultHeaderRowItem(str, true), this.questionSummaryRowItems);
        ArrayList arrayList = new ArrayList();
        if (!allQuestionsAnswered) {
            arrayList.add(new NoResultRowItem());
        } else {
            if (calculator.errorChecks != null && !calculator.errorChecks.isEmpty()) {
                Iterator<ErrorCheck> it = calculator.errorChecks.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    ErrorCheck next = it.next();
                    if (next.hasError.booleanValue()) {
                        arrayList.add(new ErrorCheckRowItem(next));
                        break;
                    }
                }
            }
            if (arrayList.isEmpty()) {
                buildResultRowItems(arrayList);
            }
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        if (calculator.extraSections != null && !calculator.extraSections.isEmpty()) {
            for (ExtraSection next2 : calculator.extraSections) {
                if (allQuestionsAnswered || (next2.hideWhenNoResults != null && !next2.hideWhenNoResults.booleanValue())) {
                    if (next2.conditionFormula == null || next2.conditionFormula.isEmpty() || (next2.conditionFormulaResult != null && next2.conditionFormulaResult.booleanValue())) {
                        ArrayList arrayList2 = new ArrayList(next2.sectionItems.size());
                        for (ExtraSectionItem next3 : next2.sectionItems) {
                            if ((allQuestionsAnswered || (next2.hideWhenNoResults != null && !next3.hideWhenNoResults.booleanValue())) && (buildExtraSectionItem = buildExtraSectionItem(next3)) != null) {
                                arrayList2.add(buildExtraSectionItem);
                            }
                        }
                        if (!arrayList2.isEmpty()) {
                            if (next2.sectionIndex.intValue() == 1) {
                                arrayList.addAll(arrayList2);
                            } else {
                                linkedHashMap.put(next2, arrayList2);
                            }
                        }
                    }
                }
            }
        }
        if (!arrayList.isEmpty()) {
            this.adapter.addSectionWithHeaderItem(new DefaultHeaderRowItem(getString(R.string.results_header)), arrayList);
        }
        if (allQuestionsAnswered && this.shouldScrollToResults) {
            final int size = calculator.questions.size() + 1;
            this.layoutManager.scrollToPositionWithOffset(calculator.questions.size() + 1, this.listView.getHeight());
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    CalculatorActivity.this.listView.smoothScrollToPosition(size);
                }
            }, 500);
            this.shouldScrollToResults = false;
            sendFirebaseEventForCalculator(FirebaseEventsConstants.VIEWED_CALC_RESULTS);
            ArrayList arrayList3 = new ArrayList();
            arrayList3.add(getString(R.string.calc));
            arrayList3.add(OmnitureHelper.INSTANCE.getCalculatorIdForOmniture(this.contentItem.identifier));
            arrayList3.add(OmnitureHelper.INSTANCE.getCalcPageName(this.contentItem.name));
            arrayList3.add(getString(R.string.results));
            OmnitureHelper.INSTANCE.sendOmniturePageView(arrayList3);
        }
        if (!linkedHashMap.isEmpty()) {
            for (Map.Entry entry : linkedHashMap.entrySet()) {
                ExtraSection extraSection = (ExtraSection) entry.getKey();
                List list2 = (List) entry.getValue();
                if (extraSection.title != null) {
                    this.adapter.addSectionWithHeaderItem(new DefaultHeaderRowItem(extraSection.title), list2);
                } else {
                    this.adapter.addSectionWithHeaderItem(new DefaultHeaderNoTitleRowItem(), list2);
                }
            }
        }
        this.adapter.expandChildrenContainedInExpandedIds((List<QxRecyclerViewRowItemWrapper>) null, list);
        this.adapter.notifyDataSetChanged();
    }

    /* access modifiers changed from: protected */
    public void buildResultRowItems(List<QxRecyclerViewRowItem> list) {
        QxRecyclerViewRowItem rowItemForResult;
        Result.ResultType resultType = null;
        for (Result next : this.contentItem.calculator.results) {
            if ((next.conditionFormulaResult == null || next.conditionFormulaResult.booleanValue()) && (rowItemForResult = getRowItemForResult(next, resultType)) != null) {
                list.add(rowItemForResult);
                resultType = next.getResultType();
            }
        }
    }

    /* renamed from: com.wbmd.qxcalculator.activities.contentItems.CalculatorActivity$5  reason: invalid class name */
    static /* synthetic */ class AnonymousClass5 {
        static final /* synthetic */ int[] $SwitchMap$com$wbmd$qxcalculator$model$contentItems$calculator$Result$ResultType;

        /* JADX WARNING: Can't wrap try/catch for region: R(14:0|1|2|3|4|5|6|7|8|9|10|11|12|(3:13|14|16)) */
        /* JADX WARNING: Can't wrap try/catch for region: R(16:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|16) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x0049 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                com.wbmd.qxcalculator.model.contentItems.calculator.Result$ResultType[] r0 = com.wbmd.qxcalculator.model.contentItems.calculator.Result.ResultType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$wbmd$qxcalculator$model$contentItems$calculator$Result$ResultType = r0
                com.wbmd.qxcalculator.model.contentItems.calculator.Result$ResultType r1 = com.wbmd.qxcalculator.model.contentItems.calculator.Result.ResultType.DEFAULT     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$wbmd$qxcalculator$model$contentItems$calculator$Result$ResultType     // Catch:{ NoSuchFieldError -> 0x001d }
                com.wbmd.qxcalculator.model.contentItems.calculator.Result$ResultType r1 = com.wbmd.qxcalculator.model.contentItems.calculator.Result.ResultType.NO_ANSWER     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$wbmd$qxcalculator$model$contentItems$calculator$Result$ResultType     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.wbmd.qxcalculator.model.contentItems.calculator.Result$ResultType r1 = com.wbmd.qxcalculator.model.contentItems.calculator.Result.ResultType.NOTE_DEFAULT     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$wbmd$qxcalculator$model$contentItems$calculator$Result$ResultType     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.wbmd.qxcalculator.model.contentItems.calculator.Result$ResultType r1 = com.wbmd.qxcalculator.model.contentItems.calculator.Result.ResultType.NOTE_SECTION_TITLE     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$com$wbmd$qxcalculator$model$contentItems$calculator$Result$ResultType     // Catch:{ NoSuchFieldError -> 0x003e }
                com.wbmd.qxcalculator.model.contentItems.calculator.Result$ResultType r1 = com.wbmd.qxcalculator.model.contentItems.calculator.Result.ResultType.NOTE_WITH_TITLE_SUB_TITLE     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$com$wbmd$qxcalculator$model$contentItems$calculator$Result$ResultType     // Catch:{ NoSuchFieldError -> 0x0049 }
                com.wbmd.qxcalculator.model.contentItems.calculator.Result$ResultType r1 = com.wbmd.qxcalculator.model.contentItems.calculator.Result.ResultType.NOTE_WITH_TITLE_SUB_TITLE_LEFT_JUSTIFIED     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                int[] r0 = $SwitchMap$com$wbmd$qxcalculator$model$contentItems$calculator$Result$ResultType     // Catch:{ NoSuchFieldError -> 0x0054 }
                com.wbmd.qxcalculator.model.contentItems.calculator.Result$ResultType r1 = com.wbmd.qxcalculator.model.contentItems.calculator.Result.ResultType.CHART_IMAGE     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.wbmd.qxcalculator.activities.contentItems.CalculatorActivity.AnonymousClass5.<clinit>():void");
        }
    }

    /* access modifiers changed from: protected */
    public QxRecyclerViewRowItem getRowItemForResult(Result result, Result.ResultType resultType) {
        boolean z = false;
        switch (AnonymousClass5.$SwitchMap$com$wbmd$qxcalculator$model$contentItems$calculator$Result$ResultType[result.getResultType().ordinal()]) {
            case 1:
                return new ResultDefaultRowItem(result);
            case 2:
                return new ResultNoAnswerRowItem(result);
            case 3:
                return new ResultNoteDefaultRowItem(result);
            case 4:
                return new ResultNoteSectionTitleRowItem(result);
            case 5:
                if (resultType != null && (resultType.equals(Result.ResultType.NOTE_WITH_TITLE_SUB_TITLE) || resultType.equals(Result.ResultType.NOTE_WITH_TITLE_SUB_TITLE_LEFT_JUSTIFIED))) {
                    z = true;
                }
                return new ResultNoteWTitleSubTitleRowItem(result, z);
            case 6:
                if (resultType != null && (resultType.equals(Result.ResultType.NOTE_WITH_TITLE_SUB_TITLE_LEFT_JUSTIFIED) || resultType.equals(Result.ResultType.NOTE_WITH_TITLE_SUB_TITLE))) {
                    z = true;
                }
                return new ResultNoteWTitleSubTitleRowItem(result, z, true);
            case 7:
                return new ChartImageRowItem(this.contentItem, result, this.listView);
            default:
                return null;
        }
    }

    /* access modifiers changed from: protected */
    public QxRecyclerViewRowItem buildExtraSectionItem(ExtraSectionItem extraSectionItem) {
        if (extraSectionItem.subSectionItems == null || extraSectionItem.subSectionItems.isEmpty()) {
            Log.d("API", "extra section title " + extraSectionItem.title);
            if (extraSectionItem.getType().equals(ExtraSectionItem.ExtraSectionType.DEFAULT)) {
                return new ExtraSectionItemRowItem(extraSectionItem, this.contentItem, this);
            }
            if (extraSectionItem.getType().equals(ExtraSectionItem.ExtraSectionType.HEADER)) {
                return new ExtraSectionHeaderRowItem(extraSectionItem, this.contentItem, this);
            }
            return null;
        }
        ExtraSectionGroupRowItem extraSectionGroupRowItem = new ExtraSectionGroupRowItem(extraSectionItem, this.contentItem, this);
        for (ExtraSectionItem buildExtraSectionItem : extraSectionItem.subSectionItems) {
            QxRecyclerViewRowItem buildExtraSectionItem2 = buildExtraSectionItem(buildExtraSectionItem);
            if (buildExtraSectionItem2 != null) {
                extraSectionGroupRowItem.addChild(buildExtraSectionItem2);
            }
        }
        return extraSectionGroupRowItem;
    }

    public void onRecyclerViewRowItemClicked(QxRecyclerViewRowItem qxRecyclerViewRowItem, QxRecyclerViewAdapter qxRecyclerViewAdapter, View view, int i) {
        if (qxRecyclerViewRowItem instanceof QuestionSummaryRowItem) {
            openQuestion(i - 1);
        } else if (qxRecyclerViewRowItem instanceof ExtraSectionItemRowItem) {
            ExtraSectionItemRowItem extraSectionItemRowItem = (ExtraSectionItemRowItem) qxRecyclerViewRowItem;
            String str = extraSectionItemRowItem.extraSectionItem.source;
            if (str != null && !str.isEmpty()) {
                ExtraSectionItem extraSectionItem = extraSectionItemRowItem.extraSectionItem;
                String obj = Html.fromHtml(extraSectionItem.title).toString();
                String str2 = this.contentItem.trackerId;
                if (extraSectionItem.trackerId != null && !extraSectionItem.trackerId.isEmpty()) {
                    str2 = extraSectionItem.trackerId;
                }
                String str3 = str2;
                if (str3 != null && !str3.isEmpty()) {
                    String str4 = extraSectionItem.trackerCategory;
                    String str5 = extraSectionItem.trackerEvent;
                    String str6 = extraSectionItem.trackerLabel;
                    if (str4 == null) {
                        String str7 = this.contentItem.name;
                    }
                }
                if (this.contentItem.promotionToUse != null) {
                    EventsManager.getInstance().trackExtraSectionConversion(this.contentItem, extraSectionItem, this.contentItem.promotionToUse);
                }
                ContentItemLaunchManager.getInstance().launchExtraSectionItem(extraSectionItemRowItem.extraSectionItem, this.contentItem.identifier, str3, obj, obj, this, (Intent) null, -1);
            }
        }
    }

    public void onRecyclerViewRowItemExpanded(QxRecyclerViewRowItem qxRecyclerViewRowItem, QxRecyclerViewAdapter qxRecyclerViewAdapter, View view, int i) {
        ArrowView arrowView = (ArrowView) view.findViewById(R.id.arrow_view);
        if (arrowView != null) {
            arrowView.expandArrow(true, true);
        }
        if (this.contentItem.trackerId != null && !this.contentItem.trackerId.isEmpty() && (qxRecyclerViewRowItem instanceof ExtraSectionItemRowItem)) {
            Html.fromHtml(((ExtraSectionItemRowItem) qxRecyclerViewRowItem).extraSectionItem.title).toString();
        }
        if (this.contentItem.promotionToUse != null && (qxRecyclerViewRowItem instanceof ExtraSectionItemRowItem)) {
            EventsManager.getInstance().trackExtraSectionSectionExpanded(this.contentItem, ((ExtraSectionItemRowItem) qxRecyclerViewRowItem).extraSectionItem, this.contentItem.promotionToUse);
        }
    }

    public void onRecyclerViewRowItemCollapsed(QxRecyclerViewRowItem qxRecyclerViewRowItem, QxRecyclerViewAdapter qxRecyclerViewAdapter, View view, int i) {
        ArrowView arrowView = (ArrowView) view.findViewById(R.id.arrow_view);
        if (arrowView != null) {
            arrowView.expandArrow(false, true);
        }
    }

    /* access modifiers changed from: protected */
    public void openQuestion(int i) {
        Intent intent = new Intent(this, QuestionPagerActivity.class);
        intent.putExtra(ContentItemActivity.KEY_EXTRA_CONTENT_ITEM, this.contentItem);
        intent.putExtra(KEY_EXTRA_SELECTED_QUESTION, i);
        intent.putExtra(KEY_EXTRA_IS_SUB_CALC, this.isSubCalc);
        Question question = this.subCalcQuestion;
        if (question != null) {
            intent.putExtra(KEY_EXTRA_SUB_CALC_QUESTION, question);
            intent.putExtra(KEY_EXTRA_SUB_CALC_QUESTION_LINKED_CALC_INDEX, this.subCalcQuestionLinkedCalcIndex);
        }
        if (this.contentItem.calculator.allQuestionsAnswered()) {
            intent.putExtra(KEY_ALL_QUESTIONS_ALREADY_ANSWERED, true);
        }
        startActivityForResult(intent, 1, (Bundle) null);
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        Log.d("API", "CalculatorActivity onActivityResult");
        if (i != 1) {
            return;
        }
        if (intent == null) {
            finish();
            return;
        }
        this.contentItem = (ContentItem) intent.getParcelableExtra(ContentItemActivity.KEY_EXTRA_CONTENT_ITEM);
        if (this.isSubCalc) {
            Intent intent2 = new Intent();
            intent2.putExtra(KEY_EXTRA_CALCULATOR, this.contentItem);
            intent2.putExtra(KEY_EXTRA_SUB_CALC_QUESTION, (Question) intent.getParcelableExtra(KEY_EXTRA_SUB_CALC_QUESTION));
            setResult(-1, intent2);
            finish();
            overridePendingTransition(R.anim.close_enter_modal, R.anim.close_exit_modal);
            return;
        }
        buildCalculatorRowItems(this.expandedRowIds);
    }

    private void sendFirebaseEventForCalculator(String str) {
        String str2;
        if (this.contentItem != null && this.contentItem.name != null && !this.contentItem.name.isEmpty() && (str2 = this.sectionCalculatorWasOpenedFrom) != null && str2.equalsIgnoreCase(getString(R.string.feed))) {
            Bundle bundle = new Bundle();
            bundle.putString(FirebaseEventsConstants.CALCULATOR_ID_KEY, Util.truncateCalculatorIdForFirebase(this.contentItem.identifier));
            bundle.putString(FirebaseEventsConstants.CALCULATOR_NAME_KEY, Util.truncateFirebaseValue(this.contentItem.name));
            QxFirebaseEventManager.getInstance(this).sendEventName(str, bundle);
        }
    }

    private void sendFirebaseEventForCalculatorPath(String str) {
        String str2 = this.sectionCalculatorWasOpenedFrom;
        if (str2 != null && !str2.isEmpty()) {
            Bundle bundle = new Bundle();
            bundle.putString(FirebaseEventsConstants.CALCULATOR_PATH_KEY, this.sectionCalculatorWasOpenedFrom);
            QxFirebaseEventManager.getInstance(this).sendEventName(str, bundle);
        }
    }

    /* access modifiers changed from: protected */
    public void sendPageNameForOmniture() {
        ArrayList arrayList = new ArrayList();
        if (this.contentItem.footer == null || !this.contentItem.footer.equalsIgnoreCase(getString(R.string.sponsored))) {
            String str = this.sectionCalculatorWasOpenedFrom;
            if (str == null || !str.equalsIgnoreCase(getString(R.string.search))) {
                arrayList.add(getString(R.string.calc));
            } else {
                arrayList.add(getString(R.string.search));
                arrayList.add(getString(R.string.results_header));
            }
        } else {
            arrayList.add(getString(R.string.calc));
            arrayList.add(getString(R.string.sponsored).toLowerCase());
        }
        arrayList.add(OmnitureHelper.INSTANCE.getCalculatorIdForOmniture(this.contentItem.identifier));
        arrayList.add(OmnitureHelper.INSTANCE.getCalcPageName(this.contentItem.name));
        OmnitureHelper.INSTANCE.sendOmniturePageView(arrayList);
    }
}
