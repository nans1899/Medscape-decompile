package com.wbmd.qxcalculator.model.contentItems.calculator;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.JsonReader;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.medscape.android.Constants;
import com.wbmd.qxcalculator.managers.DataManager;
import com.wbmd.qxcalculator.managers.UserManager;
import com.wbmd.qxcalculator.model.api.parser.APIParser;
import com.wbmd.qxcalculator.model.contentItems.calculator.Unit;
import com.wbmd.qxcalculator.model.db.DBQuestion;
import com.wbmd.qxcalculator.util.CalculationJavascriptCallback;
import com.wbmd.qxcalculator.util.CrashLogger;
import com.wbmd.qxcalculator.util.jsevaluator.QxJsEvaluator;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import org.liquidplayer.javascript.JSContext;

public class Question implements Parcelable {
    public static final Parcelable.Creator<Question> CREATOR = new Parcelable.Creator<Question>() {
        public Question createFromParcel(Parcel parcel) {
            Question question = new Question();
            question.identifier = parcel.readString();
            question.orderedId = Long.valueOf(parcel.readLong());
            question.position = Integer.valueOf(parcel.readInt());
            question.sectionName = (String) parcel.readValue(String.class.getClassLoader());
            question.title = parcel.readString();
            question.type = parcel.readString();
            question.moreInformation = (String) parcel.readValue(String.class.getClassLoader());
            question.moreInfoSource = (String) parcel.readValue(String.class.getClassLoader());
            question.linkedCalculatorTitle = (String) parcel.readValue(String.class.getClassLoader());
            question.lastUsedUnits = (String) parcel.readValue(String.class.getClassLoader());
            question.initialValue = (Double) parcel.readValue(Double.class.getClassLoader());
            question.inputtedDays = (Integer) parcel.readValue(Integer.class.getClassLoader());
            question.inputtedDateMs = (Long) parcel.readValue(Long.class.getClassLoader());
            question.inputtedValue = (String) parcel.readValue(String.class.getClassLoader());
            question.selectedAnswerChoiceIndex = (Integer) parcel.readValue(Integer.class.getClassLoader());
            question.selectedUnitIndex = (Integer) parcel.readValue(Integer.class.getClassLoader());
            question.allowNegativeAnswer = (Boolean) parcel.readValue(Boolean.class.getClassLoader());
            question.answerState = (AnswerState) parcel.readValue(AnswerState.class.getClassLoader());
            question.linkedCalculatorResultValue = (Double) parcel.readValue(Double.class.getClassLoader());
            question.linkedCalculatorConvertResult = (String) parcel.readValue(String.class.getClassLoader());
            question.linkedCalculatorConvertResultUnit = (Integer) parcel.readValue(Integer.class.getClassLoader());
            question.linkedCalculatorResultSections = parcel.createTypedArrayList(LinkedCalculatorResultSection.CREATOR);
            question.linkedCalculatorItems = parcel.createTypedArrayList(LinkedCalculatorItem.CREATOR);
            question.units = new ArrayList();
            parcel.readTypedList(question.units, Unit.CREATOR);
            question.answerChoices = new ArrayList();
            parcel.readTypedList(question.answerChoices, AnswerChoice.CREATOR);
            question.numberFormat.setMaximumFractionDigits(1);
            question.numberFormat.setGroupingUsed(true);
            return question;
        }

        public Question[] newArray(int i) {
            return new Question[i];
        }
    };
    public Boolean allowNegativeAnswer;
    public List<AnswerChoice> answerChoices;
    public AnswerState answerState;
    public String identifier;
    public Double initialValue;
    public Long inputtedDateMs;
    public Integer inputtedDays;
    public String inputtedValue;
    public String lastUsedUnits;
    public String linkedCalculatorConvertResult;
    public Integer linkedCalculatorConvertResultUnit;
    public List<LinkedCalculatorItem> linkedCalculatorItems;
    public List<LinkedCalculatorResultSection> linkedCalculatorResultSections;
    public Double linkedCalculatorResultValue;
    public String linkedCalculatorTitle;
    public String moreInfoSource;
    public String moreInformation;
    public NumberFormat numberFormat;
    public Long orderedId;
    public Integer position;
    public String sectionName;
    public Integer selectedAnswerChoiceIndex;
    public Integer selectedUnitIndex;
    public String title;
    public String type;
    public List<Unit> units;

    public enum AnswerState {
        UNANSWERED,
        OK,
        NaN,
        OUT_OF_RANGE_MAX,
        OUT_OF_RANGE_MIN
    }

    private interface JavascriptCompletionHandler {
        void onJavascriptCompletion(String str);
    }

    public enum QuestionType {
        QuestionTypeMultipleChoice,
        QuestionTypeNumericEntryNoUnits,
        QuestionTypeNumericEntrySingleUnits,
        QuestionTypeNumericEntryMultipleUnits,
        QuestionTypeLinkedCalculator,
        QuestionTypeDateInput,
        QuestionTypeDaysInput,
        QuestionTypeNotSet
    }

    public int describeContents() {
        return 0;
    }

    public static ArrayList<Question> questions(List<DBQuestion> list) {
        if (list == null) {
            return null;
        }
        ArrayList<Question> arrayList = new ArrayList<>(list.size());
        for (DBQuestion question : list) {
            arrayList.add(new Question(question));
        }
        return arrayList;
    }

    public Question(DBQuestion dBQuestion) {
        this.numberFormat = NumberFormat.getInstance(Locale.US);
        if (dBQuestion != null) {
            this.identifier = dBQuestion.getIdentifier();
            this.orderedId = dBQuestion.getOrderedId();
            this.position = dBQuestion.getPosition();
            this.sectionName = dBQuestion.getSectionName();
            this.title = dBQuestion.getTitle();
            this.type = dBQuestion.getType();
            this.moreInformation = dBQuestion.getMoreInformation();
            this.moreInfoSource = dBQuestion.getMoreInfoSource();
            this.linkedCalculatorTitle = dBQuestion.getLinkedCalculatorTitle();
            this.lastUsedUnits = dBQuestion.getLastUsedUnits();
            this.initialValue = dBQuestion.getInitialValue();
            this.allowNegativeAnswer = dBQuestion.getAllowNegativeAnswer();
            this.linkedCalculatorItems = LinkedCalculatorItem.linkedCalculatorItems(dBQuestion.getLinkedCalculatorItems());
            this.units = Unit.units(dBQuestion.getUnits());
            this.answerChoices = AnswerChoice.answerChoices(dBQuestion.getAnswerChoices());
            this.numberFormat.setMaximumFractionDigits(1);
            this.numberFormat.setGroupingUsed(true);
        }
    }

    public Question() {
        this((DBQuestion) null);
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x0090  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x00b2  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00ba  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00ae A[EDGE_INSN: B:36:0x00ae->B:28:0x00ae ?: BREAK  , SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:38:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void initializeQuestion(com.wbmd.qxcalculator.model.db.DBUser r6, android.content.Context r7) {
        /*
            r5 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "init question "
            r0.append(r1)
            java.lang.String r1 = r6.getDefaultUnits()
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            java.lang.String r1 = "API"
            android.util.Log.d(r1, r0)
            com.wbmd.qxcalculator.model.contentItems.calculator.Question$AnswerState r0 = com.wbmd.qxcalculator.model.contentItems.calculator.Question.AnswerState.UNANSWERED
            r5.answerState = r0
            java.util.List<com.wbmd.qxcalculator.model.contentItems.calculator.Unit> r0 = r5.units
            if (r0 == 0) goto L_0x002a
            com.wbmd.qxcalculator.model.contentItems.calculator.Question$1 r2 = new com.wbmd.qxcalculator.model.contentItems.calculator.Question$1
            r2.<init>()
            java.util.Collections.sort(r0, r2)
        L_0x002a:
            int[] r0 = com.wbmd.qxcalculator.model.contentItems.calculator.Question.AnonymousClass5.$SwitchMap$com$wbmd$qxcalculator$model$contentItems$calculator$Question$QuestionType
            com.wbmd.qxcalculator.model.contentItems.calculator.Question$QuestionType r2 = r5.getQuestionType()
            int r2 = r2.ordinal()
            r0 = r0[r2]
            r2 = 1
            r3 = 0
            if (r0 == r2) goto L_0x00cb
            r4 = 2
            if (r0 == r4) goto L_0x00c4
            r4 = 3
            if (r0 == r4) goto L_0x0042
            goto L_0x00d1
        L_0x0042:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r4 = "default units "
            r0.append(r4)
            java.lang.String r4 = r6.getDefaultUnits()
            r0.append(r4)
            java.lang.String r0 = r0.toString()
            android.util.Log.d(r1, r0)
            java.lang.String r0 = r5.lastUsedUnits
            java.lang.String r1 = "primary"
            java.lang.String r4 = "secondary"
            if (r0 == 0) goto L_0x0078
            boolean r6 = com.wbmd.qxcalculator.model.contentItems.calculator.Unit.UnitType.isLegacyDefaultUnitValue(r0)
            if (r6 == 0) goto L_0x0075
            java.lang.String r6 = r5.lastUsedUnits
            com.wbmd.qxcalculator.model.contentItems.calculator.Unit$UnitType r6 = com.wbmd.qxcalculator.model.contentItems.calculator.Unit.UnitType.convertStringToType(r6)
            com.wbmd.qxcalculator.model.contentItems.calculator.Unit$UnitType r0 = com.wbmd.qxcalculator.model.contentItems.calculator.Unit.UnitType.SI_UNITS
            if (r6 != r0) goto L_0x0073
            goto L_0x0087
        L_0x0073:
            r1 = r4
            goto L_0x0087
        L_0x0075:
            java.lang.String r1 = r5.lastUsedUnits
            goto L_0x0086
        L_0x0078:
            java.lang.String r6 = r6.getDefaultUnits()
            com.wbmd.qxcalculator.model.contentItems.calculator.Unit$UnitType r6 = com.wbmd.qxcalculator.model.contentItems.calculator.Unit.UnitType.convertStringToType(r6)
            com.wbmd.qxcalculator.model.contentItems.calculator.Unit$UnitType r0 = com.wbmd.qxcalculator.model.contentItems.calculator.Unit.UnitType.SI_UNITS
            if (r6 != r0) goto L_0x0085
            goto L_0x0086
        L_0x0085:
            r1 = r4
        L_0x0086:
            r2 = 0
        L_0x0087:
            r6 = 0
        L_0x0088:
            java.util.List<com.wbmd.qxcalculator.model.contentItems.calculator.Unit> r0 = r5.units
            int r0 = r0.size()
            if (r6 >= r0) goto L_0x00ae
            java.util.List<com.wbmd.qxcalculator.model.contentItems.calculator.Unit> r0 = r5.units
            java.lang.Object r0 = r0.get(r6)
            com.wbmd.qxcalculator.model.contentItems.calculator.Unit r0 = (com.wbmd.qxcalculator.model.contentItems.calculator.Unit) r0
            java.lang.String r0 = r0.type
            java.lang.String r0 = r0.toLowerCase()
            boolean r0 = r0.contains(r1)
            if (r0 == 0) goto L_0x00ab
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)
            r5.selectedUnitIndex = r6
            goto L_0x00ae
        L_0x00ab:
            int r6 = r6 + 1
            goto L_0x0088
        L_0x00ae:
            java.lang.Integer r6 = r5.selectedUnitIndex
            if (r6 != 0) goto L_0x00b8
            java.lang.Integer r6 = java.lang.Integer.valueOf(r3)
            r5.selectedUnitIndex = r6
        L_0x00b8:
            if (r2 == 0) goto L_0x00d1
            java.lang.Integer r6 = r5.selectedUnitIndex
            int r6 = r6.intValue()
            r5.setLastUsedUnits(r6, r7)
            goto L_0x00d1
        L_0x00c4:
            java.lang.Integer r6 = java.lang.Integer.valueOf(r3)
            r5.selectedUnitIndex = r6
            goto L_0x00d1
        L_0x00cb:
            java.lang.Integer r6 = java.lang.Integer.valueOf(r3)
            r5.selectedUnitIndex = r6
        L_0x00d1:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wbmd.qxcalculator.model.contentItems.calculator.Question.initializeQuestion(com.wbmd.qxcalculator.model.db.DBUser, android.content.Context):void");
    }

    /* renamed from: com.wbmd.qxcalculator.model.contentItems.calculator.Question$5  reason: invalid class name */
    static /* synthetic */ class AnonymousClass5 {
        static final /* synthetic */ int[] $SwitchMap$com$wbmd$qxcalculator$model$contentItems$calculator$Question$QuestionType;

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
                int[] r0 = $SwitchMap$com$wbmd$qxcalculator$model$contentItems$calculator$Question$QuestionType     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.wbmd.qxcalculator.model.contentItems.calculator.Question$QuestionType r1 = com.wbmd.qxcalculator.model.contentItems.calculator.Question.QuestionType.QuestionTypeMultipleChoice     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$com$wbmd$qxcalculator$model$contentItems$calculator$Question$QuestionType     // Catch:{ NoSuchFieldError -> 0x003e }
                com.wbmd.qxcalculator.model.contentItems.calculator.Question$QuestionType r1 = com.wbmd.qxcalculator.model.contentItems.calculator.Question.QuestionType.QuestionTypeDateInput     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$com$wbmd$qxcalculator$model$contentItems$calculator$Question$QuestionType     // Catch:{ NoSuchFieldError -> 0x0049 }
                com.wbmd.qxcalculator.model.contentItems.calculator.Question$QuestionType r1 = com.wbmd.qxcalculator.model.contentItems.calculator.Question.QuestionType.QuestionTypeDaysInput     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                int[] r0 = $SwitchMap$com$wbmd$qxcalculator$model$contentItems$calculator$Question$QuestionType     // Catch:{ NoSuchFieldError -> 0x0054 }
                com.wbmd.qxcalculator.model.contentItems.calculator.Question$QuestionType r1 = com.wbmd.qxcalculator.model.contentItems.calculator.Question.QuestionType.QuestionTypeLinkedCalculator     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r2 = 7
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.wbmd.qxcalculator.model.contentItems.calculator.Question.AnonymousClass5.<clinit>():void");
        }
    }

    public void setLastUsedUnits(int i, Context context) {
        this.lastUsedUnits = this.units.get(i).type;
        DataManager.getInstance().setLastUsedUnits(this, this.lastUsedUnits);
    }

    public QuestionType getQuestionType() {
        if (this.type.equalsIgnoreCase("multiple_choice")) {
            return QuestionType.QuestionTypeMultipleChoice;
        }
        if (this.type.equalsIgnoreCase("numeric_input")) {
            List<Unit> list = this.units;
            if (list != null && list.size() == 1 && (this.units.get(0).title == null || this.units.get(0).title.isEmpty())) {
                return QuestionType.QuestionTypeNumericEntryNoUnits;
            }
            List<Unit> list2 = this.units;
            if (list2 != null && list2.size() == 1) {
                return QuestionType.QuestionTypeNumericEntrySingleUnits;
            }
            List<Unit> list3 = this.units;
            if (list3 == null || list3.size() <= 1) {
                return QuestionType.QuestionTypeNotSet;
            }
            return QuestionType.QuestionTypeNumericEntryMultipleUnits;
        } else if (this.type.equalsIgnoreCase("date_input")) {
            return QuestionType.QuestionTypeDateInput;
        } else {
            if (this.type.equalsIgnoreCase("days_input")) {
                return QuestionType.QuestionTypeDaysInput;
            }
            if (this.type.equalsIgnoreCase("linked_calculator")) {
                return QuestionType.QuestionTypeLinkedCalculator;
            }
            return QuestionType.QuestionTypeNotSet;
        }
    }

    public String getAnswerString() {
        StringBuilder sb = new StringBuilder();
        switch (AnonymousClass5.$SwitchMap$com$wbmd$qxcalculator$model$contentItems$calculator$Question$QuestionType[getQuestionType().ordinal()]) {
            case 1:
                if (this.answerState != AnswerState.OK) {
                    return null;
                }
                sb.append(getInputtedValueAsString(true));
                return sb.toString();
            case 2:
                if (this.answerState != AnswerState.OK) {
                    return null;
                }
                sb.append(getInputtedValueAsString(true));
                sb.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
                sb.append(this.units.get(0).title);
                return sb.toString();
            case 3:
                if (this.answerState != AnswerState.OK) {
                    return null;
                }
                sb.append(getInputtedValueAsString(true));
                sb.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
                sb.append(this.units.get(this.selectedUnitIndex.intValue()).title);
                return sb.toString();
            case 4:
                for (AnswerChoice next : this.answerChoices) {
                    if (next.isSelected != null && next.isSelected.booleanValue()) {
                        if (next.titleSecondary == null || next.titleSecondary.isEmpty()) {
                            return next.titlePrimary;
                        }
                        if (Unit.UnitType.convertStringToType(UserManager.getInstance().getDbUser().getDefaultUnits()) == Unit.UnitType.SI_UNITS) {
                            return next.titlePrimary;
                        }
                        return next.titleSecondary;
                    }
                }
                return null;
            case 5:
                if (this.inputtedDateMs != null) {
                    return new SimpleDateFormat("MMM d, yyyy", Locale.US).format(new Date(this.inputtedDateMs.longValue()));
                }
                return null;
            case 6:
                Integer num = this.inputtedDays;
                if (num == null) {
                    return null;
                }
                int intValue = num.intValue() / 7;
                int intValue2 = this.inputtedDays.intValue() % 7;
                if (intValue > 0) {
                    sb.append(intValue);
                    if (intValue == 1) {
                        sb.append(" Week");
                    } else {
                        sb.append(" Weeks");
                    }
                }
                if (intValue2 > 0) {
                    if (intValue > 0) {
                        sb.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
                    }
                    sb.append(intValue2);
                    if (intValue2 == 1) {
                        sb.append(" Day");
                    } else {
                        sb.append(" Days");
                    }
                }
                if (sb.length() == 0) {
                    sb.append("0 Weeks, 0 Days");
                }
                return sb.toString();
            case 7:
                if (this.linkedCalculatorResultValue == null) {
                    return null;
                }
                for (LinkedCalculatorResultSection next2 : this.linkedCalculatorResultSections) {
                    if (sb.length() > 0) {
                        sb.append(";\n");
                    }
                    sb.append(next2.title);
                    sb.append(": ");
                    sb.append(next2.answer);
                }
                return sb.toString();
            default:
                return null;
        }
    }

    public String getInputtedValueAsString(boolean z) {
        return this.inputtedValue;
    }

    public boolean isAnswered() {
        switch (AnonymousClass5.$SwitchMap$com$wbmd$qxcalculator$model$contentItems$calculator$Question$QuestionType[getQuestionType().ordinal()]) {
            case 1:
                if (this.answerState == AnswerState.OK) {
                    return true;
                }
                return false;
            case 2:
                if (this.answerState == AnswerState.OK) {
                    return true;
                }
                return false;
            case 3:
                if (this.answerState == AnswerState.OK) {
                    return true;
                }
                return false;
            case 4:
                for (AnswerChoice next : this.answerChoices) {
                    if (next.isSelected != null && next.isSelected.booleanValue()) {
                        return true;
                    }
                }
                return false;
            case 5:
                if (this.inputtedDateMs != null) {
                    return true;
                }
                return false;
            case 6:
                if (this.inputtedDays != null) {
                    return true;
                }
                return false;
            case 7:
                return this.linkedCalculatorResultValue != null;
            default:
                return false;
        }
    }

    public double getAnswerValue() {
        double doubleValue;
        double doubleValue2;
        switch (AnonymousClass5.$SwitchMap$com$wbmd$qxcalculator$model$contentItems$calculator$Question$QuestionType[getQuestionType().ordinal()]) {
            case 1:
                doubleValue = this.numberFormat.parse(this.inputtedValue, new ParsePosition(0)).doubleValue();
                doubleValue2 = this.units.get(0).unitFactor.doubleValue();
                break;
            case 2:
                doubleValue = this.numberFormat.parse(this.inputtedValue, new ParsePosition(0)).doubleValue();
                doubleValue2 = this.units.get(0).unitFactor.doubleValue();
                break;
            case 3:
                return this.numberFormat.parse(this.inputtedValue, new ParsePosition(0)).doubleValue() * this.units.get(this.selectedUnitIndex.intValue()).unitFactor.doubleValue();
            case 4:
                for (AnswerChoice next : this.answerChoices) {
                    if (next.isSelected != null && next.isSelected.booleanValue()) {
                        return next.answerFactor.doubleValue();
                    }
                }
                return FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
            case 5:
                return (double) this.inputtedDateMs.longValue();
            case 6:
                return (double) (((long) this.inputtedDays.intValue()) * Constants.DAY_IN_MILLIS);
            case 7:
                return this.linkedCalculatorResultValue.doubleValue();
            default:
                return FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
        }
        return doubleValue * doubleValue2;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.identifier);
        parcel.writeLong(this.orderedId.longValue());
        parcel.writeInt(this.position.intValue());
        parcel.writeValue(this.sectionName);
        parcel.writeString(this.title);
        parcel.writeString(this.type);
        parcel.writeValue(this.moreInformation);
        parcel.writeValue(this.moreInfoSource);
        parcel.writeValue(this.linkedCalculatorTitle);
        parcel.writeValue(this.lastUsedUnits);
        parcel.writeValue(this.initialValue);
        parcel.writeValue(this.inputtedDays);
        parcel.writeValue(this.inputtedDateMs);
        parcel.writeValue(this.inputtedValue);
        parcel.writeValue(this.selectedAnswerChoiceIndex);
        parcel.writeValue(this.selectedUnitIndex);
        parcel.writeValue(this.allowNegativeAnswer);
        parcel.writeValue(this.answerState);
        parcel.writeValue(this.linkedCalculatorResultValue);
        parcel.writeValue(this.linkedCalculatorConvertResult);
        parcel.writeValue(this.linkedCalculatorConvertResultUnit);
        parcel.writeTypedList(this.linkedCalculatorResultSections);
        parcel.writeTypedList(this.linkedCalculatorItems);
        parcel.writeTypedList(this.units);
        parcel.writeTypedList(this.answerChoices);
    }

    public static List<Question> convertJsonToQuestions(JsonReader jsonReader) throws IOException, ParseException {
        ArrayList arrayList = new ArrayList(30);
        jsonReader.beginArray();
        while (jsonReader.hasNext()) {
            arrayList.add(convertJsonToQuestion(jsonReader));
        }
        jsonReader.endArray();
        return arrayList;
    }

    public static Question convertJsonToQuestion(JsonReader jsonReader) throws IOException, ParseException {
        Question question = new Question();
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            String nextName = jsonReader.nextName();
            if (nextName.equalsIgnoreCase("id")) {
                question.identifier = APIParser.readString(jsonReader);
            } else if (nextName.equalsIgnoreCase("ordered_id")) {
                question.orderedId = APIParser.readLong(jsonReader);
            } else if (nextName.equalsIgnoreCase("position")) {
                question.position = APIParser.readInteger(jsonReader);
            } else if (nextName.equalsIgnoreCase("section_name")) {
                question.sectionName = APIParser.readString(jsonReader);
            } else if (nextName.equalsIgnoreCase("title")) {
                question.title = APIParser.readString(jsonReader);
            } else if (nextName.equalsIgnoreCase("type")) {
                question.type = APIParser.readString(jsonReader);
            } else if (nextName.equalsIgnoreCase("more_information")) {
                question.moreInformation = APIParser.readString(jsonReader);
            } else if (nextName.equalsIgnoreCase("more_information_src")) {
                question.moreInfoSource = APIParser.readString(jsonReader);
            } else if (nextName.equalsIgnoreCase("initial_value")) {
                question.initialValue = APIParser.readDouble(jsonReader);
            } else if (nextName.equalsIgnoreCase("allow_negative_answer")) {
                question.allowNegativeAnswer = APIParser.readBoolean(jsonReader);
            } else if (nextName.equalsIgnoreCase("units")) {
                question.units = Unit.convertJsonToUnits(jsonReader);
            } else if (nextName.equalsIgnoreCase("choices")) {
                question.answerChoices = AnswerChoice.convertJsonToAnswerChoices(jsonReader);
            } else if (nextName.equalsIgnoreCase("linked_calculator_title")) {
                question.linkedCalculatorTitle = APIParser.readString(jsonReader);
            } else if (nextName.equalsIgnoreCase("linked_items")) {
                question.linkedCalculatorItems = LinkedCalculatorItem.convertJsonToLinkedCalculatorItems(jsonReader);
            } else {
                jsonReader.skipValue();
            }
        }
        jsonReader.endObject();
        return question;
    }

    public void calculateLinkedCalculatorConvertFormula(int i, Calculator calculator, final CalculationJavascriptCallback.LinkedCalculatorConvertResultJavascriptCompletionHandler linkedCalculatorConvertResultJavascriptCompletionHandler) {
        LinkedCalculatorItem linkedCalculatorItem = this.linkedCalculatorItems.get(i);
        if (linkedCalculatorItem.resultUnits != null && !linkedCalculatorItem.resultUnits.isEmpty()) {
            this.linkedCalculatorConvertResultUnit = Integer.valueOf(convertUnitStringToInteger(linkedCalculatorItem.resultUnits));
        }
        String replaceReferencesInString = replaceReferencesInString(linkedCalculatorItem.resultConvertFormula, calculator, true);
        if (replaceReferencesInString != null) {
            evaluateFormula(replaceReferencesInString, new JavascriptCompletionHandler() {
                public void onJavascriptCompletion(String str) {
                    Question.this.linkedCalculatorConvertResult = str;
                    linkedCalculatorConvertResultJavascriptCompletionHandler.onResultJavascriptCompleted(true);
                }
            });
        } else {
            linkedCalculatorConvertResultJavascriptCompletionHandler.onResultJavascriptCompleted(false);
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

    private int convertUnitStringToInteger(String str) {
        if (str.contains("$si_units")) {
            return 0;
        }
        return str.contains("$us_units") ? 1 : -1;
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(5:11|12|13|14|24) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x004f */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String replaceReferencesInString(java.lang.String r8, com.wbmd.qxcalculator.model.contentItems.calculator.Calculator r9, boolean r10) {
        /*
            r7 = this;
            java.lang.String r0 = "\""
            r1 = 0
            java.lang.StringBuffer r2 = new java.lang.StringBuffer     // Catch:{ Exception -> 0x00c1 }
            r2.<init>()     // Catch:{ Exception -> 0x00c1 }
            java.lang.String r3 = "\\$result([\\d]+)"
            r4 = 34
            java.util.regex.Pattern r3 = java.util.regex.Pattern.compile(r3, r4)     // Catch:{ Exception -> 0x00c1 }
            java.util.regex.Matcher r8 = r3.matcher(r8)     // Catch:{ Exception -> 0x00c1 }
        L_0x0014:
            boolean r3 = r8.find()     // Catch:{ Exception -> 0x00c1 }
            if (r3 == 0) goto L_0x0065
            r3 = 1
            java.lang.String r3 = r8.group(r3)     // Catch:{ Exception -> 0x00c1 }
            java.lang.Long r3 = java.lang.Long.valueOf(r3)     // Catch:{ Exception -> 0x00c1 }
            long r5 = r3.longValue()     // Catch:{ Exception -> 0x00c1 }
            java.lang.Long r3 = java.lang.Long.valueOf(r5)     // Catch:{ Exception -> 0x00c1 }
            com.wbmd.qxcalculator.model.contentItems.calculator.Result r3 = r9.getResultForId(r3)     // Catch:{ Exception -> 0x00c1 }
            java.lang.String r3 = r3.formulaResult     // Catch:{ Exception -> 0x00c1 }
            if (r10 == 0) goto L_0x0061
            java.lang.String r5 = r3.toLowerCase()     // Catch:{ Exception -> 0x00c1 }
            java.lang.String r6 = "true"
            boolean r5 = r5.equals(r6)     // Catch:{ Exception -> 0x00c1 }
            if (r5 != 0) goto L_0x0061
            java.lang.String r5 = r3.toLowerCase()     // Catch:{ Exception -> 0x00c1 }
            java.lang.String r6 = "false"
            boolean r5 = r5.equals(r6)     // Catch:{ Exception -> 0x00c1 }
            if (r5 != 0) goto L_0x0061
            java.lang.Double.parseDouble(r3)     // Catch:{ Exception -> 0x004f }
            goto L_0x0061
        L_0x004f:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00c1 }
            r5.<init>()     // Catch:{ Exception -> 0x00c1 }
            r5.append(r0)     // Catch:{ Exception -> 0x00c1 }
            r5.append(r3)     // Catch:{ Exception -> 0x00c1 }
            r5.append(r0)     // Catch:{ Exception -> 0x00c1 }
            java.lang.String r3 = r5.toString()     // Catch:{ Exception -> 0x00c1 }
        L_0x0061:
            r8.appendReplacement(r2, r3)     // Catch:{ Exception -> 0x00c1 }
            goto L_0x0014
        L_0x0065:
            r8.appendTail(r2)     // Catch:{ Exception -> 0x00c1 }
            java.lang.String r8 = "\\$user_units\\S*"
            java.util.regex.Pattern r8 = java.util.regex.Pattern.compile(r8, r4)     // Catch:{ Exception -> 0x00c1 }
            java.lang.String r9 = r2.toString()     // Catch:{ Exception -> 0x00c1 }
            java.util.regex.Matcher r8 = r8.matcher(r9)     // Catch:{ Exception -> 0x00c1 }
            java.lang.StringBuffer r9 = new java.lang.StringBuffer     // Catch:{ Exception -> 0x00c1 }
            r9.<init>()     // Catch:{ Exception -> 0x00c1 }
        L_0x007b:
            boolean r10 = r8.find()     // Catch:{ Exception -> 0x00c1 }
            if (r10 == 0) goto L_0x0099
            com.wbmd.qxcalculator.managers.UserManager r10 = com.wbmd.qxcalculator.managers.UserManager.getInstance()     // Catch:{ Exception -> 0x00c1 }
            com.wbmd.qxcalculator.model.db.DBUser r10 = r10.getDbUser()     // Catch:{ Exception -> 0x00c1 }
            java.lang.String r10 = r10.getDefaultUnits()     // Catch:{ Exception -> 0x00c1 }
            int r10 = com.wbmd.qxcalculator.model.contentItems.calculator.Unit.UnitType.convertUnitStringToInt(r10)     // Catch:{ Exception -> 0x00c1 }
            java.lang.String r10 = java.lang.String.valueOf(r10)     // Catch:{ Exception -> 0x00c1 }
            r8.appendReplacement(r9, r10)     // Catch:{ Exception -> 0x00c1 }
            goto L_0x007b
        L_0x0099:
            r8.appendTail(r9)     // Catch:{ Exception -> 0x00c1 }
            java.lang.String r8 = r9.toString()     // Catch:{ Exception -> 0x00c1 }
            java.lang.String r9 = "$si_units"
            com.wbmd.qxcalculator.model.contentItems.calculator.Unit$UnitType r10 = com.wbmd.qxcalculator.model.contentItems.calculator.Unit.UnitType.SI_UNITS     // Catch:{ Exception -> 0x00c1 }
            int r10 = com.wbmd.qxcalculator.model.contentItems.calculator.Unit.UnitType.convertUnitTypeToInt(r10)     // Catch:{ Exception -> 0x00c1 }
            java.lang.String r10 = java.lang.String.valueOf(r10)     // Catch:{ Exception -> 0x00c1 }
            java.lang.String r1 = r8.replace(r9, r10)     // Catch:{ Exception -> 0x00c1 }
            java.lang.String r8 = "$us_units"
            com.wbmd.qxcalculator.model.contentItems.calculator.Unit$UnitType r9 = com.wbmd.qxcalculator.model.contentItems.calculator.Unit.UnitType.US_UNITS     // Catch:{ Exception -> 0x00c1 }
            int r9 = com.wbmd.qxcalculator.model.contentItems.calculator.Unit.UnitType.convertUnitTypeToInt(r9)     // Catch:{ Exception -> 0x00c1 }
            java.lang.String r9 = java.lang.String.valueOf(r9)     // Catch:{ Exception -> 0x00c1 }
            java.lang.String r8 = r1.replace(r8, r9)     // Catch:{ Exception -> 0x00c1 }
            goto L_0x00c6
        L_0x00c1:
            r8 = move-exception
            r8.printStackTrace()
            r8 = r1
        L_0x00c6:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.wbmd.qxcalculator.model.contentItems.calculator.Question.replaceReferencesInString(java.lang.String, com.wbmd.qxcalculator.model.contentItems.calculator.Calculator, boolean):java.lang.String");
    }

    public String toString() {
        return "Question [contentSpecificIdentifier=" + this.identifier + ", position=" + this.position + ", title=" + this.title + ", moreInformation=" + this.moreInformation + ", moreInfoSource=" + this.moreInfoSource + ", Units=" + this.units + ", AnswerChoices=" + this.answerChoices + "]";
    }
}
