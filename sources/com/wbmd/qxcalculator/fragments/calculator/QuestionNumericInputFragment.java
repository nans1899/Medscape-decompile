package com.wbmd.qxcalculator.fragments.calculator;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.appcompat.widget.PopupMenu;
import com.wbmd.qxcalculator.R;
import com.wbmd.qxcalculator.managers.FileHelper;
import com.wbmd.qxcalculator.model.contentItems.calculator.Question;
import com.wbmd.qxcalculator.model.contentItems.calculator.Unit;
import com.wbmd.qxcalculator.model.contentItems.common.ContentItem;
import com.wbmd.qxcalculator.util.QxKeyboard;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.ParsePosition;
import java.util.Date;
import me.saket.bettermovementmethod.BetterLinkMovementMethod;

public class QuestionNumericInputFragment extends QuestionFragment implements PopupMenu.OnMenuItemClickListener, QxKeyboard.OnKeyboardSavedListener {
    protected EditText editText;
    /* access modifiers changed from: private */
    public boolean hasLayedOut;
    /* access modifiers changed from: private */
    public boolean hasStopped = false;
    /* access modifiers changed from: private */
    public long onResumeCallTime;
    /* access modifiers changed from: private */
    public PopupMenu popupMenu;
    /* access modifiers changed from: private */
    public boolean requestFocusOnEditTextAfterLayout;
    private Button unitButton;

    /* access modifiers changed from: protected */
    public String getAnalyticsScreenName() {
        return "QuestionNumericInputFragment";
    }

    public static QuestionNumericInputFragment newInstance(Question question, ContentItem contentItem) {
        QuestionNumericInputFragment questionNumericInputFragment = new QuestionNumericInputFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("QuestionFragment.KEY_ARG_QUESTION", question);
        bundle.putParcelable("QuestionFragment.KEY_ARG_CONTENT_ITEM", contentItem);
        questionNumericInputFragment.setArguments(bundle);
        return questionNumericInputFragment;
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
        final View inflate = layoutInflater.inflate(R.layout.fragment_question_numeric_input, viewGroup, false);
        View findViewById = inflate.findViewById(R.id.question_header);
        findViewById.findViewById(R.id.separator).setVisibility(8);
        ((TextView) findViewById.findViewById(R.id.text_view)).setText(R.string.calculator_question_title);
        TextView textView = (TextView) inflate.findViewById(R.id.section_name);
        if (this.question.sectionName == null || this.question.sectionName.isEmpty()) {
            textView.setVisibility(8);
        } else {
            textView.setText(Html.fromHtml(this.question.sectionName));
        }
        initViews(inflate);
        this.editText.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable editable) {
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                Log.d("API", "onTextChanged " + QuestionNumericInputFragment.this.editText.getText().toString());
                QuestionNumericInputFragment questionNumericInputFragment = QuestionNumericInputFragment.this;
                questionNumericInputFragment.inputtedValueChanged(questionNumericInputFragment.editText.getText().toString());
            }
        });
        this.editText.setText(this.question.inputtedValue);
        if (Build.VERSION.SDK_INT >= 21) {
            this.editText.setShowSoftInputOnFocus(false);
        } else {
            Class<EditText> cls = EditText.class;
            try {
                Method method = cls.getMethod("setShowSoftInputOnFocus", new Class[]{Boolean.TYPE});
                method.setAccessible(true);
                method.invoke(this.editText, new Object[]{false});
            } catch (Exception unused) {
            }
        }
        inflate.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                Log.d("API", "onGlobalLayout " + QuestionNumericInputFragment.this.question.title);
                inflate.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                boolean unused = QuestionNumericInputFragment.this.hasLayedOut = true;
                QuestionNumericInputFragment.this.editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    public void onFocusChange(View view, boolean z) {
                        Log.d("API", "onFocusChanged");
                        if (!QuestionNumericInputFragment.this.hasStopped) {
                            Log.d("API", "onFocusChanged continue");
                            if (z) {
                                Log.d("API", "onFocusChanged has focus");
                                final boolean z2 = true;
                                if (new Date().getTime() - QuestionNumericInputFragment.this.onResumeCallTime < 200) {
                                    z2 = false;
                                }
                                Log.d("API", "onFocusChanged animated " + z2);
                                new Handler().postDelayed(new Runnable() {
                                    public void run() {
                                        ((QuestionPagerFragment) QuestionNumericInputFragment.this.getParentFragment()).showPlusMinusKeyboard(QuestionNumericInputFragment.this.question.allowNegativeAnswer != null && QuestionNumericInputFragment.this.question.allowNegativeAnswer.booleanValue());
                                        ((QuestionPagerFragment) QuestionNumericInputFragment.this.getParentFragment()).keyboard.showCustomKeyboard(z2);
                                        ((QuestionPagerFragment) QuestionNumericInputFragment.this.getParentFragment()).keyboard.setOnKeyboardSavedListener(QuestionNumericInputFragment.this);
                                    }
                                }, 0);
                                return;
                            }
                            Log.d("API", "onFocusChanged does not have focus");
                            ((QuestionPagerFragment) QuestionNumericInputFragment.this.getParentFragment()).keyboard.hideCustomKeyboard();
                        }
                    }
                });
                QuestionNumericInputFragment.this.editText.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        ((QuestionPagerFragment) QuestionNumericInputFragment.this.getParentFragment()).showPlusMinusKeyboard(QuestionNumericInputFragment.this.question.allowNegativeAnswer != null && QuestionNumericInputFragment.this.question.allowNegativeAnswer.booleanValue());
                        ((QuestionPagerFragment) QuestionNumericInputFragment.this.getParentFragment()).keyboard.showCustomKeyboard(true);
                        ((QuestionPagerFragment) QuestionNumericInputFragment.this.getParentFragment()).keyboard.setOnKeyboardSavedListener(QuestionNumericInputFragment.this);
                    }
                });
                if (QuestionNumericInputFragment.this.requestFocusOnEditTextAfterLayout) {
                    boolean unused2 = QuestionNumericInputFragment.this.requestFocusOnEditTextAfterLayout = false;
                    QuestionNumericInputFragment.this.editText.requestFocus();
                }
            }
        });
        TextView textView2 = (TextView) inflate.findViewById(R.id.unit_text_view);
        this.unitButton = (Button) inflate.findViewById(R.id.unit_button);
        int i = AnonymousClass6.$SwitchMap$com$wbmd$qxcalculator$model$contentItems$calculator$Question$QuestionType[this.question.getQuestionType().ordinal()];
        if (i == 1) {
            textView2.setVisibility(8);
            this.unitButton.setVisibility(8);
        } else if (i == 2) {
            textView2.setVisibility(0);
            textView2.setText(this.question.units.get(0).title);
            this.unitButton.setVisibility(8);
        } else if (i == 3) {
            textView2.setVisibility(8);
            this.unitButton.setVisibility(0);
            this.unitButton.setText(this.question.units.get(this.question.selectedUnitIndex.intValue()).title);
            this.unitButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    QuestionNumericInputFragment.this.popupMenu.show();
                }
            });
            this.popupMenu = new PopupMenu(getActivity(), this.unitButton);
            for (int i2 = 0; i2 < this.question.units.size(); i2++) {
                this.popupMenu.getMenu().add(0, i2, i2, this.question.units.get(i2).title);
            }
            this.popupMenu.setOnMenuItemClickListener(this);
        }
        View findViewById2 = inflate.findViewById(R.id.linked_calc_button);
        if (this.question.linkedCalculatorItems == null || this.question.linkedCalculatorItems.isEmpty()) {
            findViewById2.setVisibility(8);
        } else {
            findViewById2.setVisibility(0);
            findViewById2.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    QuestionNumericInputFragment.this.linkedCalculatorRowTapped(view);
                }
            });
        }
        View findViewById3 = inflate.findViewById(R.id.more_info_header);
        View findViewById4 = inflate.findViewById(R.id.more_info_container);
        if (this.question.moreInformation == null || this.question.moreInformation.isEmpty()) {
            findViewById3.setVisibility(8);
            findViewById4.setVisibility(8);
        } else {
            ((ScrollView) inflate.findViewById(R.id.scroll_view)).setBackgroundColor(getResources().getColor(R.color.calculator_question_more_info_bkg));
            findViewById3.setVisibility(0);
            TextView textView3 = (TextView) findViewById3.findViewById(R.id.text_view);
            textView3.setText(R.string.question_more_info_header);
            textView3.setBackgroundColor(getResources().getColor(R.color.calculator_question_more_info_bkg));
            findViewById4.setVisibility(0);
            TextView textView4 = (TextView) findViewById4.findViewById(R.id.more_info_text_view);
            textView4.setText(Html.fromHtml(this.question.moreInformation, new Html.ImageGetter() {
                public Drawable getDrawable(String str) {
                    Log.d("API", "getDrawable " + str);
                    BitmapDrawable scaledDrawable = FileHelper.getInstance().getScaledDrawable(QuestionNumericInputFragment.this.contentItem, str, -1.0d);
                    if (scaledDrawable != null) {
                        scaledDrawable.setBounds(0, 0, scaledDrawable.getIntrinsicWidth(), scaledDrawable.getIntrinsicHeight());
                    }
                    return scaledDrawable;
                }
            }, (Html.TagHandler) null));
            textView4.setMovementMethod(BetterLinkMovementMethod.getInstance());
        }
        return inflate;
    }

    /* renamed from: com.wbmd.qxcalculator.fragments.calculator.QuestionNumericInputFragment$6  reason: invalid class name */
    static /* synthetic */ class AnonymousClass6 {
        static final /* synthetic */ int[] $SwitchMap$com$wbmd$qxcalculator$model$contentItems$calculator$Question$QuestionType;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        static {
            /*
                com.wbmd.qxcalculator.model.contentItems.calculator.Question$QuestionType[] r0 = com.wbmd.qxcalculator.model.contentItems.calculator.Question.QuestionType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$wbmd$qxcalculator$model$contentItems$calculator$Question$QuestionType = r0
                com.wbmd.qxcalculator.model.contentItems.calculator.Question$QuestionType r1 = com.wbmd.qxcalculator.model.contentItems.calculator.Question.QuestionType.QuestionTypeNumericEntryNoUnits     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$wbmd$qxcalculator$model$contentItems$calculator$Question$QuestionType     // Catch:{ NoSuchFieldError -> 0x001d }
                com.wbmd.qxcalculator.model.contentItems.calculator.Question$QuestionType r1 = com.wbmd.qxcalculator.model.contentItems.calculator.Question.QuestionType.QuestionTypeNumericEntrySingleUnits     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$wbmd$qxcalculator$model$contentItems$calculator$Question$QuestionType     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.wbmd.qxcalculator.model.contentItems.calculator.Question$QuestionType r1 = com.wbmd.qxcalculator.model.contentItems.calculator.Question.QuestionType.QuestionTypeNumericEntryMultipleUnits     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.wbmd.qxcalculator.fragments.calculator.QuestionNumericInputFragment.AnonymousClass6.<clinit>():void");
        }
    }

    public void initViews(View view) {
        ((TextView) view.findViewById(R.id.result_title_text_view)).setText(Html.fromHtml(this.question.title));
        this.editText = (EditText) view.findViewById(R.id.numeric_entry_edit_text);
    }

    public void didAppear() {
        super.didAppear();
        Log.d("CHAN", "did appear");
        EditText editText2 = this.editText;
        if (editText2 != null && !editText2.hasFocus()) {
            Log.d("CHAN", "did appear: request focus");
            if (this.hasLayedOut) {
                this.editText.requestFocus();
            } else {
                this.requestFocusOnEditTextAfterLayout = true;
            }
        }
    }

    public void didDisappear() {
        super.didDisappear();
    }

    /* access modifiers changed from: private */
    public void inputtedValueChanged(String str) {
        if (str == null || str.isEmpty()) {
            this.question.inputtedValue = null;
            this.question.answerState = Question.AnswerState.UNANSWERED;
        } else {
            try {
                ParsePosition parsePosition = new ParsePosition(0);
                Number parse = this.question.numberFormat.parse(str, parsePosition);
                if (parse == null || parsePosition.getIndex() != str.length()) {
                    throw new ParseException("failed to parse entire string: " + str, parsePosition.getIndex());
                }
                double doubleValue = parse.doubleValue();
                this.editText.setTextColor(getResources().getColor(R.color.input_answer_in_of_range));
                checkIfInputtedValueFallsWithinLimits(doubleValue);
                this.question.inputtedValue = str;
            } catch (ParseException e) {
                Log.d("API", "inputted value parse error " + e.toString());
                this.question.answerState = Question.AnswerState.NaN;
                this.editText.setTextColor(getResources().getColor(R.color.input_answer_out_of_range));
            }
        }
        Log.d("API", "inputted value " + this.question.inputtedValue);
        ((OnAnswerChangedListener) getParentFragment()).onAnswerChanged(this.question);
    }

    public boolean onMenuItemClick(MenuItem menuItem) {
        setSelectedUnitsIndex(menuItem.getItemId());
        return true;
    }

    private void setSelectedUnitsIndex(int i) {
        this.question.selectedUnitIndex = Integer.valueOf(i);
        this.question.setLastUsedUnits(i, getContext());
        this.unitButton.setText(this.question.units.get(this.question.selectedUnitIndex.intValue()).title);
        if (this.editText.getText() != null) {
            inputtedValueChanged(this.editText.getText().toString());
        } else {
            ((OnAnswerChangedListener) getParentFragment()).onAnswerChanged(this.question);
        }
    }

    private void checkIfInputtedValueFallsWithinLimits(double d) {
        Unit unit = this.question.units.get(this.question.selectedUnitIndex.intValue());
        if (unit.maxValue != null && d > unit.maxValue.doubleValue()) {
            this.question.answerState = Question.AnswerState.OUT_OF_RANGE_MAX;
            this.editText.setTextColor(getResources().getColor(R.color.input_answer_out_of_range));
        } else if (unit.minValue == null || d >= unit.minValue.doubleValue()) {
            this.question.answerState = Question.AnswerState.OK;
            this.editText.setTextColor(getResources().getColor(R.color.input_answer_in_of_range));
        } else {
            this.question.answerState = Question.AnswerState.OUT_OF_RANGE_MIN;
            this.editText.setTextColor(getResources().getColor(R.color.input_answer_out_of_range));
        }
    }

    public void onKeyboardSaved() {
        String str;
        if (this.editText.getText() == null || this.editText.getText().toString().isEmpty()) {
            str = getString(R.string.input_value_empty);
        } else if (this.question.answerState == Question.AnswerState.NaN) {
            str = getString(R.string.input_value_parse_error);
        } else if (this.question.answerState == Question.AnswerState.OUT_OF_RANGE_MAX) {
            str = this.question.units.get(this.question.selectedUnitIndex.intValue()).maxValueMessage;
        } else if (this.question.answerState == Question.AnswerState.OUT_OF_RANGE_MIN) {
            str = this.question.units.get(this.question.selectedUnitIndex.intValue()).minValueMessage;
        } else {
            ((OnAnswerChangedListener) getParentFragment()).onNextButtonPressed(this.question);
            str = null;
        }
        if (str != null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle(R.string.oops);
            builder.setMessage(str);
            builder.setPositiveButton(R.string.dismiss, (DialogInterface.OnClickListener) null);
            builder.create().show();
        }
    }

    public void onStart() {
        super.onStart();
        Log.d("API", "QuestionNumericInputFragment onStart");
    }

    public void onResume() {
        super.onResume();
        Log.d("API", "QuestionNumericInputFragment onresume");
        this.onResumeCallTime = new Date().getTime();
        this.hasStopped = false;
    }

    public void onStop() {
        super.onStop();
        Log.d("API", "QuestionNumericInputFragment onStop");
        this.hasStopped = true;
    }

    public void onDetach() {
        super.onDetach();
    }

    /* access modifiers changed from: protected */
    public void onLinkedCalculatorResultsReady() {
        int intValue;
        this.editText.setText(this.question.linkedCalculatorConvertResult);
        if (this.question.linkedCalculatorConvertResultUnit != null && (intValue = this.question.linkedCalculatorConvertResultUnit.intValue()) >= 0) {
            setSelectedUnitsIndex(intValue);
        }
    }
}
