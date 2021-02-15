package com.wbmd.qxcalculator.fragments.calculator;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;
import com.wbmd.qxcalculator.R;
import com.wbmd.qxcalculator.model.contentItems.calculator.Question;
import com.wbmd.qxcalculator.model.contentItems.common.ContentItem;
import java.util.Calendar;

public class QuestionDateInputFragment extends QuestionFragment implements DatePicker.OnDateChangedListener {
    /* access modifiers changed from: protected */
    public String getAnalyticsScreenName() {
        return "QuestionDateInputFragment";
    }

    public static QuestionDateInputFragment newInstance(Question question, ContentItem contentItem) {
        QuestionDateInputFragment questionDateInputFragment = new QuestionDateInputFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("QuestionFragment.KEY_ARG_QUESTION", question);
        bundle.putParcelable("QuestionFragment.KEY_ARG_CONTENT_ITEM", contentItem);
        questionDateInputFragment.setArguments(bundle);
        return questionDateInputFragment;
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
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_question_date_input, viewGroup, false);
        View findViewById = inflate.findViewById(R.id.question_header);
        findViewById.findViewById(R.id.separator).setVisibility(8);
        ((TextView) findViewById.findViewById(R.id.text_view)).setText(R.string.calculator_question_title);
        TextView textView = (TextView) inflate.findViewById(R.id.section_name);
        if (this.question.sectionName == null || this.question.sectionName.isEmpty()) {
            textView.setVisibility(8);
        } else {
            textView.setText(Html.fromHtml(this.question.sectionName));
        }
        ((TextView) inflate.findViewById(R.id.result_title_text_view)).setText(Html.fromHtml(this.question.title));
        Calendar instance = Calendar.getInstance();
        final DatePicker datePicker = (DatePicker) inflate.findViewById(R.id.date_picker);
        if (this.question.inputtedDateMs != null) {
            instance.setTimeInMillis(this.question.inputtedDateMs.longValue());
        }
        datePicker.init(instance.get(1), instance.get(2), instance.get(5), this);
        ViewGroup viewGroup2 = (ViewGroup) inflate.findViewById(R.id.save_button);
        viewGroup2.getBackground().setColorFilter(getResources().getColor(R.color.keyboard_bkg_unselected), PorterDuff.Mode.SRC_IN);
        viewGroup2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Calendar instance = Calendar.getInstance();
                instance.set(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth(), 0, 0, 0);
                QuestionDateInputFragment.this.question.inputtedDateMs = Long.valueOf(instance.getTimeInMillis());
                ((OnAnswerChangedListener) QuestionDateInputFragment.this.getParentFragment()).onAnswerChanged(QuestionDateInputFragment.this.question);
                ((OnAnswerChangedListener) QuestionDateInputFragment.this.getParentFragment()).onNextButtonPressed(QuestionDateInputFragment.this.question);
            }
        });
        return inflate;
    }

    public void didAppear() {
        ((QuestionPagerFragment) getParentFragment()).keyboard.hideCustomKeyboard();
    }

    public void onDateChanged(DatePicker datePicker, int i, int i2, int i3) {
        Calendar instance = Calendar.getInstance();
        instance.set(i, i2, i3, 0, 0, 0);
        this.question.inputtedDateMs = Long.valueOf(instance.getTimeInMillis());
        ((OnAnswerChangedListener) getParentFragment()).onAnswerChanged(this.question);
    }
}
