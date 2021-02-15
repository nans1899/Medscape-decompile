package com.wbmd.qxcalculator.fragments.calculator;

import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;
import android.widget.TextView;
import androidx.exifinterface.media.ExifInterface;
import com.facebook.appevents.AppEventsConstants;
import com.wbmd.qxcalculator.R;
import com.wbmd.qxcalculator.model.contentItems.calculator.Question;
import com.wbmd.qxcalculator.model.contentItems.common.ContentItem;
import com.webmd.wbmdproffesionalauthentication.model.UserProfile;

public class QuestionDaysInputFragment extends QuestionFragment implements NumberPicker.OnValueChangeListener {
    private static final String[] dayPickerValues = {AppEventsConstants.EVENT_PARAM_VALUE_NO, AppEventsConstants.EVENT_PARAM_VALUE_YES, ExifInterface.GPS_MEASUREMENT_2D, ExifInterface.GPS_MEASUREMENT_3D, "4", UserProfile.NURSE_PRACTITIONER_ID, "6"};
    private static final int numberOfWeeks = 71;
    private static String[] weekPickerValues;
    /* access modifiers changed from: private */
    public NumberPicker daysPicker;
    /* access modifiers changed from: private */
    public NumberPicker weeksPicker;

    public static QuestionDaysInputFragment newInstance(Question question, ContentItem contentItem) {
        QuestionDaysInputFragment questionDaysInputFragment = new QuestionDaysInputFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("QuestionFragment.KEY_ARG_QUESTION", question);
        bundle.putParcelable("QuestionFragment.KEY_ARG_CONTENT_ITEM", contentItem);
        questionDaysInputFragment.setArguments(bundle);
        return questionDaysInputFragment;
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
        if (weekPickerValues == null) {
            weekPickerValues = new String[71];
            for (int i = 0; i < 71; i++) {
                weekPickerValues[i] = String.valueOf(i);
            }
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        int i;
        int i2;
        View inflate = layoutInflater.inflate(R.layout.fragment_question_days_input, viewGroup, false);
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
        if (this.question.inputtedDays != null) {
            int intValue = this.question.inputtedDays.intValue();
            i = intValue / 7;
            i2 = intValue % 7;
        } else {
            i2 = 0;
            i = 0;
        }
        NumberPicker numberPicker = (NumberPicker) inflate.findViewById(R.id.picker_weeks);
        this.weeksPicker = numberPicker;
        numberPicker.setDisplayedValues(weekPickerValues);
        this.weeksPicker.setMinValue(0);
        this.weeksPicker.setMaxValue(70);
        this.weeksPicker.setValue(i);
        this.weeksPicker.setOnValueChangedListener(this);
        NumberPicker numberPicker2 = (NumberPicker) inflate.findViewById(R.id.picker_days);
        this.daysPicker = numberPicker2;
        numberPicker2.setDisplayedValues(dayPickerValues);
        this.daysPicker.setMinValue(0);
        this.daysPicker.setMaxValue(6);
        this.daysPicker.setValue(i2);
        this.daysPicker.setOnValueChangedListener(this);
        ((ViewGroup) inflate.findViewById(R.id.save_button)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                int value = (QuestionDaysInputFragment.this.weeksPicker.getValue() * 7) + 0 + QuestionDaysInputFragment.this.daysPicker.getValue();
                QuestionDaysInputFragment.this.question.inputtedDays = Integer.valueOf(value);
                ((OnAnswerChangedListener) QuestionDaysInputFragment.this.getParentFragment()).onAnswerChanged(QuestionDaysInputFragment.this.question);
                ((OnAnswerChangedListener) QuestionDaysInputFragment.this.getParentFragment()).onNextButtonPressed(QuestionDaysInputFragment.this.question);
            }
        });
        return inflate;
    }

    public void onValueChange(NumberPicker numberPicker, int i, int i2) {
        int value = (this.weeksPicker.getValue() * 7) + 0 + this.daysPicker.getValue();
        this.question.inputtedDays = Integer.valueOf(value);
        ((OnAnswerChangedListener) getParentFragment()).onAnswerChanged(this.question);
    }
}
