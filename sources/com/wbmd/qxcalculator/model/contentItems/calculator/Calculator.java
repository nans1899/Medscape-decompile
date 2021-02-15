package com.wbmd.qxcalculator.model.contentItems.calculator;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.JsonReader;
import com.facebook.internal.ServerProtocol;
import com.wbmd.qxcalculator.managers.UserManager;
import com.wbmd.qxcalculator.model.api.parser.APIParser;
import com.wbmd.qxcalculator.model.contentItems.calculator.Unit;
import com.wbmd.qxcalculator.model.db.DBCalculator;
import com.wbmd.qxcalculator.model.db.DBUser;
import com.wbmd.qxcalculator.util.CalculationJavascriptCallback;
import com.wbmd.qxcalculator.util.CrashLogger;
import com.wbmd.qxcalculator.util.jsevaluator.QxJsEvaluator;
import com.webmd.medscape.live.explorelivevents.common.OmnitureConstants;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.liquidplayer.javascript.JSContext;

public class Calculator implements Parcelable {
    public static final Parcelable.Creator<Calculator> CREATOR = new Parcelable.Creator<Calculator>() {
        public Calculator createFromParcel(Parcel parcel) {
            Calculator calculator = new Calculator();
            calculator.identifier = parcel.readString();
            calculator.questions = parcel.createTypedArrayList(Question.CREATOR);
            calculator.results = parcel.createTypedArrayList(Result.CREATOR);
            calculator.errorChecks = parcel.createTypedArrayList(ErrorCheck.CREATOR);
            calculator.extraSections = parcel.createTypedArrayList(ExtraSection.CREATOR);
            return calculator;
        }

        public Calculator[] newArray(int i) {
            return new Calculator[i];
        }
    };
    private static final String TAG = APIParser.class.getSimpleName();
    public List<ErrorCheck> errorChecks;
    public List<ExtraSection> extraSections;
    public String identifier;
    public List<Question> questions;
    public List<Result> results;

    public interface CalculateAnswerCompletionHandler {
        void onCalculateCompletion(boolean z);
    }

    private interface JavascriptCompletionHandler {
        void onJavascriptCompletion(String str);
    }

    public int describeContents() {
        return 0;
    }

    public static ArrayList<Calculator> calculators(List<DBCalculator> list) {
        if (list == null) {
            return null;
        }
        ArrayList<Calculator> arrayList = new ArrayList<>(list.size());
        for (DBCalculator calculator : list) {
            arrayList.add(new Calculator(calculator));
        }
        return arrayList;
    }

    public Calculator(DBCalculator dBCalculator) {
        if (dBCalculator != null) {
            this.identifier = dBCalculator.getIdentifier();
            this.questions = Question.questions(dBCalculator.getQuestions());
            this.results = Result.results(dBCalculator.getResults());
            this.errorChecks = ErrorCheck.errorChecks(dBCalculator.getErrorChecks());
            this.extraSections = ExtraSection.extraSections(dBCalculator.getExtraSections());
        }
    }

    public Calculator() {
        this((DBCalculator) null);
    }

    public void initializeCalculator(DBUser dBUser, Context context) {
        List<Question> list = this.questions;
        if (list != null) {
            Collections.sort(list, new Comparator<Question>() {
                public int compare(Question question, Question question2) {
                    return question.position.compareTo(question2.position);
                }
            });
            for (Question initializeQuestion : this.questions) {
                initializeQuestion.initializeQuestion(dBUser, context);
            }
        }
        List<Result> list2 = this.results;
        if (list2 != null) {
            Collections.sort(list2, new Comparator<Result>() {
                public int compare(Result result, Result result2) {
                    return result.position.compareTo(result2.position);
                }
            });
            for (Result initializeResult : this.results) {
                initializeResult.initializeResult(dBUser);
            }
        }
        List<ErrorCheck> list3 = this.errorChecks;
        if (list3 != null) {
            Collections.sort(list3, new Comparator<ErrorCheck>() {
                public int compare(ErrorCheck errorCheck, ErrorCheck errorCheck2) {
                    return errorCheck.position.compareTo(errorCheck2.position);
                }
            });
            for (ErrorCheck initializeErrorCheck : this.errorChecks) {
                initializeErrorCheck.initializeErrorCheck(dBUser);
            }
        }
        List<ExtraSection> list4 = this.extraSections;
        if (list4 != null) {
            Collections.sort(list4, new Comparator<ExtraSection>() {
                public int compare(ExtraSection extraSection, ExtraSection extraSection2) {
                    return extraSection.sectionIndex.compareTo(extraSection2.sectionIndex);
                }
            });
            for (ExtraSection initializeExtraSection : this.extraSections) {
                initializeExtraSection.initializeExtraSection(dBUser);
            }
        }
    }

    public boolean allQuestionsAnswered() {
        for (Question isAnswered : this.questions) {
            if (!isAnswered.isAnswered()) {
                return false;
            }
        }
        return true;
    }

    public void resetAnswer() {
        List<Result> list = this.results;
        if (list != null) {
            for (Result next : list) {
                next.conditionFormulaResult = null;
                next.titleFormulaResult = null;
                next.subTitleFormulaResult = null;
                next.formulaResult = null;
                next.answerResult = null;
            }
        }
        List<ErrorCheck> list2 = this.errorChecks;
        if (list2 != null) {
            for (ErrorCheck errorCheck : list2) {
                errorCheck.hasError = null;
            }
        }
        List<ExtraSection> list3 = this.extraSections;
        if (list3 != null) {
            for (ExtraSection extraSection : list3) {
                extraSection.conditionFormulaResult = null;
            }
        }
    }

    public void getAnswer(final CalculateAnswerCompletionHandler calculateAnswerCompletionHandler) {
        if (allQuestionsAnswered()) {
            final CalculationJavascriptCallback calculationJavascriptCallback = new CalculationJavascriptCallback();
            calculationJavascriptCallback.resultJavascriptCompletionHandler = new CalculationJavascriptCallback.ResultJavascriptCompletionHandler() {
                public void onResultJavascriptCompleted(Result result) {
                    int indexOf = Calculator.this.results.indexOf(result);
                    if (indexOf < Calculator.this.results.size() - 1) {
                        Calculator calculator = Calculator.this;
                        calculator.calculateResult(calculator.results.get(indexOf + 1), calculationJavascriptCallback.resultJavascriptCompletionHandler);
                        return;
                    }
                    Calculator.this.calculateExtraSectionFormulas(calculateAnswerCompletionHandler);
                }
            };
            List<ErrorCheck> list = this.errorChecks;
            if (list == null || list.isEmpty()) {
                calculateResult(this.results.get(0), calculationJavascriptCallback.resultJavascriptCompletionHandler);
                return;
            }
            calculationJavascriptCallback.errorCheckJavascriptCompletionHandler = new CalculationJavascriptCallback.ErrorCheckJavascriptCompletionHandler() {
                public void onErrorCheckJavascriptCompleted(ErrorCheck errorCheck, boolean z) {
                    if (z) {
                        errorCheck.hasError = true;
                        calculateAnswerCompletionHandler.onCalculateCompletion(false);
                        return;
                    }
                    errorCheck.hasError = false;
                    int indexOf = Calculator.this.errorChecks.indexOf(errorCheck);
                    if (indexOf < Calculator.this.errorChecks.size() - 1) {
                        Calculator calculator = Calculator.this;
                        calculator.checkForError(calculator.errorChecks.get(indexOf + 1), calculationJavascriptCallback.errorCheckJavascriptCompletionHandler);
                        return;
                    }
                    Calculator calculator2 = Calculator.this;
                    calculator2.calculateResult(calculator2.results.get(0), calculationJavascriptCallback.resultJavascriptCompletionHandler);
                }
            };
            checkForError(this.errorChecks.get(0), calculationJavascriptCallback.errorCheckJavascriptCompletionHandler);
        }
    }

    /* access modifiers changed from: private */
    public void calculateExtraSectionFormulas(final CalculateAnswerCompletionHandler calculateAnswerCompletionHandler) {
        List<ExtraSection> list = this.extraSections;
        if (list == null || list.isEmpty()) {
            calculateAnswerCompletionHandler.onCalculateCompletion(true);
            return;
        }
        final CalculationJavascriptCallback calculationJavascriptCallback = new CalculationJavascriptCallback();
        calculationJavascriptCallback.extraSectionJavascriptCompletionHandler = new CalculationJavascriptCallback.ExtraSectionJavascriptCompletionHandler() {
            public void onExtraSectionJavascriptCompleted(ExtraSection extraSection) {
                int indexOf = Calculator.this.extraSections.indexOf(extraSection);
                if (indexOf < Calculator.this.extraSections.size() - 1) {
                    Calculator calculator = Calculator.this;
                    calculator.calculateExtraSectionCondition(calculator.extraSections.get(indexOf + 1), calculationJavascriptCallback.extraSectionJavascriptCompletionHandler);
                    return;
                }
                calculateAnswerCompletionHandler.onCalculateCompletion(true);
            }
        };
        calculateExtraSectionCondition(this.extraSections.get(0), calculationJavascriptCallback.extraSectionJavascriptCompletionHandler);
    }

    /* access modifiers changed from: private */
    public void calculateExtraSectionCondition(final ExtraSection extraSection, final CalculationJavascriptCallback.ExtraSectionJavascriptCompletionHandler extraSectionJavascriptCompletionHandler) {
        if (extraSection.conditionFormula == null || extraSection.conditionFormula.isEmpty() || extraSection.conditionFormulaResult != null) {
            extraSectionJavascriptCompletionHandler.onExtraSectionJavascriptCompleted(extraSection);
        } else {
            evaluateFormula(replaceReferencesInString(extraSection.conditionFormula, true), new JavascriptCompletionHandler() {
                public void onJavascriptCompletion(String str) {
                    extraSection.conditionFormulaResult = Boolean.valueOf(str.equalsIgnoreCase(ServerProtocol.DIALOG_RETURN_SCOPES_TRUE));
                    Calculator.this.calculateExtraSectionCondition(extraSection, extraSectionJavascriptCompletionHandler);
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void calculateResult(final Result result, final CalculationJavascriptCallback.ResultJavascriptCompletionHandler resultJavascriptCompletionHandler) {
        if (result.conditionFormula != null && !result.conditionFormula.isEmpty()) {
            if (result.conditionFormulaResult == null) {
                evaluateFormula(replaceReferencesInString(result.conditionFormula, true), new JavascriptCompletionHandler() {
                    public void onJavascriptCompletion(String str) {
                        result.conditionFormulaResult = Boolean.valueOf(str.equalsIgnoreCase(ServerProtocol.DIALOG_RETURN_SCOPES_TRUE));
                        Calculator.this.calculateResult(result, resultJavascriptCompletionHandler);
                    }
                });
                return;
            } else if (!result.conditionFormulaResult.booleanValue()) {
                resultJavascriptCompletionHandler.onResultJavascriptCompleted(result);
                return;
            }
        }
        if (result.titleFormula != null && !result.titleFormula.isEmpty() && result.titleFormulaResult == null) {
            evaluateFormula(replaceReferencesInString(result.titleFormula, true), new JavascriptCompletionHandler() {
                public void onJavascriptCompletion(String str) {
                    result.titleFormulaResult = str;
                    Calculator.this.calculateResult(result, resultJavascriptCompletionHandler);
                }
            });
        } else if (result.subTitleFormula != null && !result.subTitleFormula.isEmpty() && result.subTitleFormulaResult == null) {
            evaluateFormula(replaceReferencesInString(result.subTitleFormula, true), new JavascriptCompletionHandler() {
                public void onJavascriptCompletion(String str) {
                    result.subTitleFormulaResult = str;
                    Calculator.this.calculateResult(result, resultJavascriptCompletionHandler);
                }
            });
        } else if (result.formula == null || result.formula.isEmpty() || result.formulaResult != null) {
            if (result.answer != null && !result.answer.isEmpty() && result.answerResult == null) {
                result.answerResult = replaceReferencesInString(result.answer, false);
            }
            resultJavascriptCompletionHandler.onResultJavascriptCompleted(result);
        } else {
            evaluateFormula(replaceReferencesInString(result.formula, true), new JavascriptCompletionHandler() {
                public void onJavascriptCompletion(String str) {
                    result.formulaResult = str;
                    Calculator.this.calculateResult(result, resultJavascriptCompletionHandler);
                }
            });
        }
    }

    private void evaluateFormula(final String str, final JavascriptCompletionHandler javascriptCompletionHandler) {
        QxJsEvaluator.getInstance().evaluate(str, new QxJsEvaluator.QxJsCallback() {
            public void onResult(String str) {
                try {
                    String jSValue = new JSContext().evaluateScript(str).toString();
                    if ((str == null && !jSValue.isEmpty()) || (str != null && !jSValue.equals(str))) {
                        CrashLogger.getInstance().logHandledException(new Exception("discrepancy with liquidcore JS result: " + jSValue + "; legacy result: " + str));
                    }
                } catch (Exception e) {
                    CrashLogger.getInstance().logHandledException(e);
                }
                javascriptCompletionHandler.onJavascriptCompletion(str);
            }
        });
    }

    /* access modifiers changed from: private */
    public void checkForError(final ErrorCheck errorCheck, final CalculationJavascriptCallback.ErrorCheckJavascriptCompletionHandler errorCheckJavascriptCompletionHandler) {
        final String replaceReferencesInString = replaceReferencesInString(errorCheck.formula, true);
        QxJsEvaluator.getInstance().evaluate(replaceReferencesInString, new QxJsEvaluator.QxJsCallback() {
            public void onResult(String str) {
                try {
                    String jSValue = new JSContext().evaluateScript(replaceReferencesInString).toString();
                    if ((str == null && !jSValue.isEmpty()) || (str != null && !jSValue.equals(str))) {
                        CrashLogger.getInstance().logHandledException(new Exception("discrepancy with liquidcore JS result: " + jSValue + "; legacy result: " + str));
                    }
                } catch (Exception e) {
                    CrashLogger.getInstance().logHandledException(e);
                }
                boolean z = true;
                if (str != null && !str.isEmpty() && str.equalsIgnoreCase("false")) {
                    z = false;
                }
                errorCheckJavascriptCompletionHandler.onErrorCheckJavascriptCompleted(errorCheck, z);
            }
        });
    }

    private String replaceReferencesInString(String str, boolean z) {
        Matcher matcher = Pattern.compile("\\$([\\d]+)", 34).matcher(str);
        StringBuffer stringBuffer = new StringBuffer();
        while (matcher.find()) {
            Question questionForId = getQuestionForId(Long.valueOf(Long.valueOf(matcher.group(1)).longValue()));
            if (questionForId != null) {
                matcher.appendReplacement(stringBuffer, String.valueOf(questionForId.getAnswerValue()));
            }
        }
        matcher.appendTail(stringBuffer);
        Matcher matcher2 = Pattern.compile("\\$result([\\d]+)", 34).matcher(stringBuffer.toString());
        StringBuffer stringBuffer2 = new StringBuffer();
        while (matcher2.find()) {
            Result resultForId = getResultForId(Long.valueOf(Long.valueOf(matcher2.group(1)).longValue()));
            if (resultForId != null) {
                String str2 = resultForId.formulaResult;
                if (z && !str2.toLowerCase().equals(ServerProtocol.DIALOG_RETURN_SCOPES_TRUE) && !str2.toLowerCase().equals("false")) {
                    if (str2.matches(".*[a-zA-Z].*")) {
                        str2 = "\"" + str2 + "\"";
                    } else {
                        try {
                            Double.parseDouble(str2);
                        } catch (Exception unused) {
                            str2 = "\"" + str2 + "\"";
                        }
                    }
                }
                matcher2.appendReplacement(stringBuffer2, str2);
            }
        }
        matcher2.appendTail(stringBuffer2);
        Matcher matcher3 = Pattern.compile("\\$user_units\\S*", 34).matcher(stringBuffer2.toString());
        StringBuffer stringBuffer3 = new StringBuffer();
        while (matcher3.find()) {
            matcher3.appendReplacement(stringBuffer3, String.valueOf(Unit.UnitType.convertUnitStringToInt(UserManager.getInstance().getDbUser().getDefaultUnits())));
        }
        matcher3.appendTail(stringBuffer3);
        return stringBuffer3.toString().replace("$si_units", String.valueOf(Unit.UnitType.convertUnitTypeToInt(Unit.UnitType.SI_UNITS))).replace("$us_units", String.valueOf(Unit.UnitType.convertUnitTypeToInt(Unit.UnitType.US_UNITS)));
    }

    private Question getQuestionForId(Long l) {
        List<Question> list = this.questions;
        if (list == null) {
            return null;
        }
        for (Question next : list) {
            if (next.orderedId.equals(l)) {
                return next;
            }
        }
        return null;
    }

    public Result getResultForId(Long l) {
        List<Result> list = this.results;
        if (list == null) {
            return null;
        }
        for (Result next : list) {
            if (next.orderedId.equals(l)) {
                return next;
            }
        }
        return null;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.identifier);
        parcel.writeTypedList(this.questions);
        parcel.writeTypedList(this.results);
        parcel.writeTypedList(this.errorChecks);
        parcel.writeTypedList(this.extraSections);
    }

    public boolean readJsonTag(JsonReader jsonReader, String str) throws IOException, ParseException {
        if (str.equalsIgnoreCase("questions")) {
            this.questions = Question.convertJsonToQuestions(jsonReader);
            return true;
        } else if (str.equalsIgnoreCase(OmnitureConstants.PAGE_NAME_RESULTS)) {
            this.results = Result.convertJsonToResults(jsonReader);
            return true;
        } else if (str.equalsIgnoreCase("error_checks")) {
            this.errorChecks = ErrorCheck.convertJsonToErrorChecks(jsonReader);
            return true;
        } else if (!str.equalsIgnoreCase("extra_sections")) {
            return false;
        } else {
            this.extraSections = ExtraSection.convertJsonToExtraSections(jsonReader);
            return true;
        }
    }

    public String toString() {
        return "Calculator [questions=" + this.questions + ", results=" + this.results + ", errorChecks=" + this.errorChecks + "]";
    }
}
