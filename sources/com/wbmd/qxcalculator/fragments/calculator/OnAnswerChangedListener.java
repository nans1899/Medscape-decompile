package com.wbmd.qxcalculator.fragments.calculator;

import com.wbmd.qxcalculator.model.contentItems.calculator.AnswerChoice;
import com.wbmd.qxcalculator.model.contentItems.calculator.Question;

public interface OnAnswerChangedListener {
    void onAnswerChanged(Question question);

    void onAnswerSelected(Question question, AnswerChoice answerChoice);

    void onAnswerSelected(Question question, AnswerChoice answerChoice, boolean z);

    void onDateChanged(Question question);

    void onNextButtonPressed(Question question);
}
