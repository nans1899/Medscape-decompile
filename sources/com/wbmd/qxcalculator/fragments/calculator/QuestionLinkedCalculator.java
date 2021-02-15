package com.wbmd.qxcalculator.fragments.calculator;

import android.os.Bundle;
import android.util.JsonReader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.qxmd.qxrecyclerview.QxRecyclerView;
import com.qxmd.qxrecyclerview.QxRecyclerViewAdapter;
import com.qxmd.qxrecyclerview.QxRecyclerViewRowItem;
import com.wbmd.qxcalculator.R;
import com.wbmd.qxcalculator.model.api.parser.APIParser;
import com.wbmd.qxcalculator.model.contentItems.calculator.LinkedCalculatorResultSection;
import com.wbmd.qxcalculator.model.contentItems.calculator.Question;
import com.wbmd.qxcalculator.model.contentItems.common.ContentItem;
import com.wbmd.qxcalculator.model.rowItems.calculator.LinkedCalculatorRowItem;
import com.wbmd.qxcalculator.model.rowItems.calculator.QuestionLinkedCalculatorResultSectionRowItem;
import com.wbmd.qxcalculator.model.rowItems.calculator.QuestionTitleRowItem;
import com.wbmd.qxcalculator.model.rowItems.headers.DefaultHeaderRowItem;
import java.io.StringReader;
import java.util.ArrayList;

public class QuestionLinkedCalculator extends QuestionFragment implements QxRecyclerViewAdapter.OnRecyclerViewRowItemClickedListener {
    private QxRecyclerViewAdapter adapter;
    private QxRecyclerView listView;
    private ViewGroup nextButton;

    public static QuestionLinkedCalculator newInstance(Question question, ContentItem contentItem) {
        QuestionLinkedCalculator questionLinkedCalculator = new QuestionLinkedCalculator();
        Bundle bundle = new Bundle();
        bundle.putParcelable("QuestionFragment.KEY_ARG_QUESTION", question);
        bundle.putParcelable("QuestionFragment.KEY_ARG_CONTENT_ITEM", contentItem);
        questionLinkedCalculator.setArguments(bundle);
        return questionLinkedCalculator;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle != null) {
            this.question = (Question) bundle.getParcelable("QuestionFragment.KEY_ARG_QUESTION");
        } else if (getArguments() != null) {
            this.question = (Question) getArguments().getParcelable("QuestionFragment.KEY_ARG_QUESTION");
        }
        if (getArguments() != null) {
            this.contentItem = (ContentItem) getArguments().getParcelable("QuestionFragment.KEY_ARG_CONTENT_ITEM");
        }
        if (this.adapter == null) {
            QxRecyclerViewAdapter qxRecyclerViewAdapter = new QxRecyclerViewAdapter();
            this.adapter = qxRecyclerViewAdapter;
            qxRecyclerViewAdapter.setHasStableIds(false);
            this.adapter.setOnClickListener(this);
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_question_linked_calculator, viewGroup, false);
        QxRecyclerView qxRecyclerView = (QxRecyclerView) inflate.findViewById(R.id.recycler_view);
        this.listView = qxRecyclerView;
        qxRecyclerView.setAdapter(this.adapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(1);
        this.listView.setLayoutManager(linearLayoutManager);
        ViewGroup viewGroup2 = (ViewGroup) inflate.findViewById(R.id.save_button);
        this.nextButton = viewGroup2;
        viewGroup2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ((OnAnswerChangedListener) QuestionLinkedCalculator.this.getParentFragment()).onNextButtonPressed(QuestionLinkedCalculator.this.question);
            }
        });
        return inflate;
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

    private void buildListViewRowItems() {
        this.adapter.reset();
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(new QuestionTitleRowItem(this.question));
        this.adapter.addSectionWithHeaderItem(new DefaultHeaderRowItem(getString(R.string.calculator_question_title), true), arrayList);
        ArrayList arrayList2 = new ArrayList();
        if (this.question.linkedCalculatorResultValue != null) {
            for (LinkedCalculatorResultSection questionLinkedCalculatorResultSectionRowItem : this.question.linkedCalculatorResultSections) {
                arrayList2.add(new QuestionLinkedCalculatorResultSectionRowItem(questionLinkedCalculatorResultSectionRowItem));
            }
            this.nextButton.setVisibility(0);
        } else {
            this.nextButton.setVisibility(8);
        }
        String str = this.question.linkedCalculatorTitle;
        if (str == null || str.isEmpty()) {
            str = getString(R.string.linked_calculator_row_text_default);
        }
        arrayList2.add(new LinkedCalculatorRowItem(str));
        this.adapter.addSectionWithHeaderItem(new DefaultHeaderRowItem(getString(R.string.calculator_question_linked_answer), true, true), arrayList2);
        this.adapter.notifyDataSetChanged();
    }

    public void onRecyclerViewRowItemClicked(QxRecyclerViewRowItem qxRecyclerViewRowItem, QxRecyclerViewAdapter qxRecyclerViewAdapter, View view, int i) {
        if (!(qxRecyclerViewRowItem instanceof QuestionTitleRowItem)) {
            if ((qxRecyclerViewRowItem instanceof LinkedCalculatorRowItem) || (qxRecyclerViewRowItem instanceof QuestionLinkedCalculatorResultSectionRowItem)) {
                linkedCalculatorRowTapped(view);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onLinkedCalculatorResultsReady() {
        JsonReader jsonReader = new JsonReader(new StringReader(this.question.linkedCalculatorConvertResult));
        ArrayList arrayList = new ArrayList();
        try {
            jsonReader.beginObject();
            while (jsonReader.hasNext()) {
                String nextName = jsonReader.nextName();
                if (nextName.equals("calculation_value")) {
                    this.question.linkedCalculatorResultValue = APIParser.readDouble(jsonReader);
                } else if (nextName.equalsIgnoreCase("sections")) {
                    jsonReader.beginArray();
                    while (jsonReader.hasNext()) {
                        LinkedCalculatorResultSection linkedCalculatorResultSection = new LinkedCalculatorResultSection();
                        jsonReader.beginObject();
                        while (jsonReader.hasNext()) {
                            String nextName2 = jsonReader.nextName();
                            if (nextName2.equalsIgnoreCase("title")) {
                                linkedCalculatorResultSection.title = APIParser.readString(jsonReader);
                            } else if (nextName2.equalsIgnoreCase("value")) {
                                linkedCalculatorResultSection.answer = APIParser.readString(jsonReader);
                            } else {
                                jsonReader.skipValue();
                            }
                        }
                        jsonReader.endObject();
                        arrayList.add(linkedCalculatorResultSection);
                    }
                    jsonReader.endArray();
                } else {
                    jsonReader.skipValue();
                }
            }
            jsonReader.endObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.question.linkedCalculatorResultSections = arrayList;
        buildListViewRowItems();
        ((OnAnswerChangedListener) getParentFragment()).onAnswerChanged(this.question);
    }
}
