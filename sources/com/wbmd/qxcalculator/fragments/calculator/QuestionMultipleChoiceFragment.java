package com.wbmd.qxcalculator.fragments.calculator;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.CheckBox;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.qxmd.qxrecyclerview.QxRecyclerView;
import com.qxmd.qxrecyclerview.QxRecyclerViewAdapter;
import com.qxmd.qxrecyclerview.QxRecyclerViewRowItem;
import com.wbmd.qxcalculator.R;
import com.wbmd.qxcalculator.model.contentItems.calculator.AnswerChoice;
import com.wbmd.qxcalculator.model.contentItems.calculator.Question;
import com.wbmd.qxcalculator.model.contentItems.common.ContentItem;
import com.wbmd.qxcalculator.model.rowItems.calculator.LinkedCalculatorRowItem;
import com.wbmd.qxcalculator.model.rowItems.calculator.QuestionMoreInfoRowItem;
import com.wbmd.qxcalculator.model.rowItems.calculator.QuestionMultipleChoiceRowItem;
import com.wbmd.qxcalculator.model.rowItems.calculator.QuestionMultipleChoiceWSecondaryRowItem;
import com.wbmd.qxcalculator.model.rowItems.calculator.QuestionTitleRowItem;
import com.wbmd.qxcalculator.model.rowItems.headers.DefaultHeaderRowItem;
import com.wbmd.qxcalculator.views.ObservableScrollView;
import java.util.ArrayList;
import java.util.Iterator;

public class QuestionMultipleChoiceFragment extends QuestionFragment implements QxRecyclerViewAdapter.OnRecyclerViewRowItemClickedListener {
    protected QxRecyclerViewAdapter adapter;
    protected QxRecyclerView listView;

    /* access modifiers changed from: protected */
    public String getAnalyticsScreenName() {
        return "QuestionMultipleChoiceFragment";
    }

    public static QuestionMultipleChoiceFragment newInstance(Question question, ContentItem contentItem) {
        Log.d("API", "new instance");
        QuestionMultipleChoiceFragment questionMultipleChoiceFragment = new QuestionMultipleChoiceFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("QuestionFragment.KEY_ARG_QUESTION", question);
        bundle.putParcelable("QuestionFragment.KEY_ARG_CONTENT_ITEM", contentItem);
        questionMultipleChoiceFragment.setArguments(bundle);
        return questionMultipleChoiceFragment;
    }

    public QuestionMultipleChoiceFragment() {
        Log.d("API", "QuestionMultipleChoiceFragment constructor");
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Log.d("API", "QuestionMultipleChoiceFragment onCreate");
        if (bundle != null) {
            this.question = (Question) bundle.getParcelable("QuestionFragment.KEY_ARG_QUESTION");
        } else if (getArguments() != null) {
            this.question = (Question) getArguments().getParcelable("QuestionFragment.KEY_ARG_QUESTION");
        }
        if (getArguments() != null) {
            this.contentItem = (ContentItem) getArguments().getParcelable("QuestionFragment.KEY_ARG_CONTENT_ITEM");
        }
        setupAdapter();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.listView = (QxRecyclerView) layoutInflater.inflate(R.layout.fragment_question_multiple_choice, viewGroup, false);
        setupList();
        return this.listView;
    }

    public void setupList() {
        this.listView.setAdapter(this.adapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(1);
        this.listView.setLayoutManager(linearLayoutManager);
    }

    public void onResume() {
        super.onResume();
        Log.d("API", "QuestionMultipleChoiceFragment onresume");
        if (!this.adapter.getHasBeenInitialized()) {
            buildListViewRowItems();
        }
    }

    public void onDetach() {
        super.onDetach();
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
    }

    public void setupAdapter() {
        if (this.adapter == null) {
            QxRecyclerViewAdapter qxRecyclerViewAdapter = new QxRecyclerViewAdapter();
            this.adapter = qxRecyclerViewAdapter;
            qxRecyclerViewAdapter.setOnClickListener(this);
            this.adapter.shouldDelaySelection = false;
        }
    }

    private void buildListViewRowItems() {
        Object obj;
        this.adapter.reset();
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(new QuestionTitleRowItem(this.question));
        this.adapter.addSectionWithHeaderItem(new DefaultHeaderRowItem(getString(R.string.calculator_question_title), true), arrayList);
        ArrayList arrayList2 = new ArrayList(this.question.answerChoices.size());
        for (AnswerChoice next : this.question.answerChoices) {
            if (next.titleSecondary == null || next.titleSecondary.isEmpty()) {
                Log.d("API", "rowItem is selected " + next.isSelected);
                obj = new QuestionMultipleChoiceRowItem(next);
            } else {
                obj = new QuestionMultipleChoiceWSecondaryRowItem(next);
            }
            arrayList2.add(obj);
        }
        if (this.question.linkedCalculatorItems != null && !this.question.linkedCalculatorItems.isEmpty()) {
            String str = this.question.linkedCalculatorTitle;
            if (str == null || str.isEmpty()) {
                str = "<i>" + getString(R.string.linked_calculator_row_text_default) + "</i>";
            }
            arrayList2.add(new LinkedCalculatorRowItem(str));
        }
        this.adapter.addSectionWithHeaderItem(new DefaultHeaderRowItem(getString(R.string.calculator_question_choices), true, true), arrayList2);
        if (this.question.moreInformation != null && !this.question.moreInformation.isEmpty()) {
            QuestionMoreInfoRowItem questionMoreInfoRowItem = new QuestionMoreInfoRowItem(this.question, this.contentItem);
            ArrayList arrayList3 = new ArrayList(1);
            arrayList3.add(questionMoreInfoRowItem);
            this.adapter.addSectionWithHeaderItem(new DefaultHeaderRowItem(getString(R.string.question_more_info_header), getResources().getColor(R.color.calculator_question_more_info_bkg)), arrayList3);
            this.listView.setBackgroundColor(getResources().getColor(R.color.calculator_question_more_info_bkg));
        }
    }

    public void onRecyclerViewRowItemClicked(QxRecyclerViewRowItem qxRecyclerViewRowItem, QxRecyclerViewAdapter qxRecyclerViewAdapter, View view, int i) {
        final AnswerChoice answerChoice;
        if (!(qxRecyclerViewRowItem instanceof QuestionTitleRowItem)) {
            if (qxRecyclerViewRowItem instanceof LinkedCalculatorRowItem) {
                linkedCalculatorRowTapped(view);
                return;
            }
            if (qxRecyclerViewRowItem instanceof QuestionMultipleChoiceRowItem) {
                answerChoice = ((QuestionMultipleChoiceRowItem) qxRecyclerViewRowItem).answerChoice;
            } else {
                answerChoice = qxRecyclerViewRowItem instanceof QuestionMultipleChoiceWSecondaryRowItem ? ((QuestionMultipleChoiceWSecondaryRowItem) qxRecyclerViewRowItem).answerChoice : null;
            }
            if (answerChoice != null) {
                if (answerChoice.errorMessage != null && !answerChoice.errorMessage.isEmpty()) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setTitle(R.string.error_message_title);
                    View inflate = getActivity().getLayoutInflater().inflate(R.layout.dialog_spanned_view, (ViewGroup) null);
                    TextView textView = (TextView) inflate.findViewById(R.id.text);
                    final ObservableScrollView observableScrollView = (ObservableScrollView) inflate.findViewById(R.id.scrollview);
                    final View findViewById = inflate.findViewById(R.id.top_sep);
                    final View findViewById2 = inflate.findViewById(R.id.bot_sep);
                    observableScrollView.setOnScrollListener(new ObservableScrollView.OnScrollListener() {
                        public void onEndScroll(ObservableScrollView observableScrollView) {
                        }

                        public void onScrollChanged(ObservableScrollView observableScrollView, int i, int i2, int i3, int i4) {
                            int i5 = 0;
                            findViewById2.setVisibility(observableScrollView.canScrollVertically(1) ? 0 : 4);
                            View view = findViewById;
                            if (!observableScrollView.canScrollVertically(-1)) {
                                i5 = 4;
                            }
                            view.setVisibility(i5);
                        }
                    });
                    observableScrollView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                        public void onGlobalLayout() {
                            Log.d("API", "onGlobalLayout");
                            observableScrollView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                            int i = 0;
                            findViewById2.setVisibility(observableScrollView.canScrollVertically(1) ? 0 : 4);
                            View view = findViewById;
                            if (!observableScrollView.canScrollVertically(-1)) {
                                i = 4;
                            }
                            view.setVisibility(i);
                        }
                    });
                    textView.setMovementMethod(LinkMovementMethod.getInstance());
                    textView.setText(Html.fromHtml(answerChoice.errorMessage));
                    builder.setView(inflate);
                    builder.setPositiveButton(R.string.dismiss, (DialogInterface.OnClickListener) null);
                    builder.create().show();
                    return;
                } else if (answerChoice.warningMessage != null && !answerChoice.warningMessage.isEmpty()) {
                    AlertDialog.Builder builder2 = new AlertDialog.Builder(getActivity());
                    builder2.setTitle(R.string.warning_message_title);
                    View inflate2 = getActivity().getLayoutInflater().inflate(R.layout.dialog_spanned_view, (ViewGroup) null);
                    TextView textView2 = (TextView) inflate2.findViewById(R.id.text);
                    final ObservableScrollView observableScrollView2 = (ObservableScrollView) inflate2.findViewById(R.id.scrollview);
                    final View findViewById3 = inflate2.findViewById(R.id.top_sep);
                    final View findViewById4 = inflate2.findViewById(R.id.bot_sep);
                    observableScrollView2.setOnScrollListener(new ObservableScrollView.OnScrollListener() {
                        public void onEndScroll(ObservableScrollView observableScrollView) {
                        }

                        public void onScrollChanged(ObservableScrollView observableScrollView, int i, int i2, int i3, int i4) {
                            int i5 = 0;
                            findViewById4.setVisibility(observableScrollView.canScrollVertically(1) ? 0 : 4);
                            View view = findViewById3;
                            if (!observableScrollView.canScrollVertically(-1)) {
                                i5 = 4;
                            }
                            view.setVisibility(i5);
                        }
                    });
                    observableScrollView2.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                        public void onGlobalLayout() {
                            Log.d("API", "onGlobalLayout");
                            observableScrollView2.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                            int i = 0;
                            findViewById4.setVisibility(observableScrollView2.canScrollVertically(1) ? 0 : 4);
                            View view = findViewById3;
                            if (!observableScrollView2.canScrollVertically(-1)) {
                                i = 4;
                            }
                            view.setVisibility(i);
                        }
                    });
                    textView2.setMovementMethod(LinkMovementMethod.getInstance());
                    textView2.setText(Html.fromHtml(answerChoice.warningMessage));
                    builder2.setView(inflate2);
                    builder2.setPositiveButton(R.string.proceed, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogInterface, int i) {
                            QuestionMultipleChoiceFragment.this.selectedAnswerChoice(answerChoice);
                        }
                    });
                    builder2.setNegativeButton(R.string.cancel, (DialogInterface.OnClickListener) null);
                    builder2.create().show();
                    return;
                } else if (answerChoice.successMessage != null && !answerChoice.successMessage.isEmpty()) {
                    AlertDialog.Builder builder3 = new AlertDialog.Builder(getActivity());
                    View inflate3 = getActivity().getLayoutInflater().inflate(R.layout.dialog_spanned_view, (ViewGroup) null);
                    TextView textView3 = (TextView) inflate3.findViewById(R.id.text);
                    final ObservableScrollView observableScrollView3 = (ObservableScrollView) inflate3.findViewById(R.id.scrollview);
                    final View findViewById5 = inflate3.findViewById(R.id.top_sep);
                    final View findViewById6 = inflate3.findViewById(R.id.bot_sep);
                    observableScrollView3.setOnScrollListener(new ObservableScrollView.OnScrollListener() {
                        public void onEndScroll(ObservableScrollView observableScrollView) {
                        }

                        public void onScrollChanged(ObservableScrollView observableScrollView, int i, int i2, int i3, int i4) {
                            int i5 = 0;
                            findViewById6.setVisibility(observableScrollView.canScrollVertically(1) ? 0 : 4);
                            View view = findViewById5;
                            if (!observableScrollView.canScrollVertically(-1)) {
                                i5 = 4;
                            }
                            view.setVisibility(i5);
                        }
                    });
                    observableScrollView3.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                        public void onGlobalLayout() {
                            Log.d("API", "onGlobalLayout");
                            observableScrollView3.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                            int i = 0;
                            findViewById6.setVisibility(observableScrollView3.canScrollVertically(1) ? 0 : 4);
                            View view = findViewById5;
                            if (!observableScrollView3.canScrollVertically(-1)) {
                                i = 4;
                            }
                            view.setVisibility(i);
                        }
                    });
                    textView3.setMovementMethod(LinkMovementMethod.getInstance());
                    textView3.setText(Html.fromHtml(answerChoice.successMessage));
                    builder3.setView(inflate3);
                    builder3.setPositiveButton(R.string.proceed, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogInterface, int i) {
                            QuestionMultipleChoiceFragment.this.selectedAnswerChoice(answerChoice);
                        }
                    });
                    builder3.create().show();
                    return;
                }
            }
            selectedAnswerChoice(answerChoice);
        }
    }

    /* access modifiers changed from: private */
    public void selectedAnswerChoice(AnswerChoice answerChoice) {
        selectedAnswerChoice(answerChoice, false);
    }

    private void selectedAnswerChoice(AnswerChoice answerChoice, boolean z) {
        CheckBox checkBox;
        CheckBox checkBox2;
        if (answerChoice != null) {
            if (answerChoice.isSelected == null || !answerChoice.isSelected.booleanValue()) {
                AnswerChoice answerChoice2 = null;
                Iterator<AnswerChoice> it = this.question.answerChoices.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    AnswerChoice next = it.next();
                    if (next.isSelected != null && next.isSelected.booleanValue()) {
                        answerChoice2 = next;
                        break;
                    }
                }
                View findViewByPosition = this.listView.getLayoutManager().findViewByPosition(this.question.answerChoices.indexOf(answerChoice) + 3);
                answerChoice.isSelected = true;
                if (!(findViewByPosition == null || (checkBox2 = (CheckBox) findViewByPosition.findViewById(R.id.checkbox)) == null)) {
                    checkBox2.setChecked(true);
                }
                if (answerChoice2 != null) {
                    answerChoice2.isSelected = false;
                    View findViewByPosition2 = ((LinearLayoutManager) this.listView.getLayoutManager()).findViewByPosition(this.question.answerChoices.indexOf(answerChoice2) + 3);
                    if (!(findViewByPosition2 == null || (checkBox = (CheckBox) findViewByPosition2.findViewById(R.id.checkbox)) == null)) {
                        checkBox.setChecked(false);
                    }
                }
                ((OnAnswerChangedListener) getParentFragment()).onAnswerSelected(this.question, answerChoice, z);
                return;
            }
            ((OnAnswerChangedListener) getParentFragment()).onAnswerSelected(this.question, answerChoice, z);
        }
    }

    /* access modifiers changed from: protected */
    public void onLinkedCalculatorResultsReady() {
        try {
            Log.d("API", "linked calc result " + this.question.linkedCalculatorConvertResult);
            int parseDouble = (int) Double.parseDouble(this.question.linkedCalculatorConvertResult);
            Log.d("API", "index to select " + parseDouble);
            if (parseDouble < this.question.answerChoices.size()) {
                selectedAnswerChoice(this.question.answerChoices.get(parseDouble), true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
