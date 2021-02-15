package com.webmd.wbmdcmepulse.models.parsers.articles;

import com.facebook.internal.ServerProtocol;
import com.facebook.places.model.PlaceFields;
import com.webmd.wbmdcmepulse.models.articles.ActivityTest;
import com.webmd.wbmdcmepulse.models.articles.Answer;
import com.webmd.wbmdcmepulse.models.articles.Question;
import com.webmd.wbmdcmepulse.models.articles.Questionnaire;
import com.webmd.wbmdcmepulse.models.interfaces.IQuestionnaireParser;
import com.webmd.wbmdcmepulse.models.utils.Constants;
import com.webmd.wbmdcmepulse.models.utils.Extensions;
import com.webmd.wbmdcmepulse.models.utils.QnaScoreCalculator;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class QuestionnaireParser extends ArticleParserBase implements IQuestionnaireParser {
    private final String XML;
    private Questionnaire mQuestionnaire;

    public QuestionnaireParser(String str) throws IOException {
        Questionnaire questionnaire = new Questionnaire();
        this.mQuestionnaire = questionnaire;
        this.XML = str;
        questionnaire.isPassed = true;
    }

    public Questionnaire parse() throws IOException, XmlPullParserException {
        XmlPullParser initializeXmlParser = initializeXmlParser(this.XML);
        this.mQuestionnaire.id = initializeXmlParser.getAttributeValue("", "id");
        initializeXmlParser.require(2, (String) null, "questionnaire");
        while (initializeXmlParser.next() != 3) {
            String name = initializeXmlParser.getName();
            if (name != null) {
                if (name.equals("title")) {
                    this.mQuestionnaire.title = getInnerXmlWithoutHtml(initializeXmlParser, "title");
                } else if (name.equals("passScore")) {
                    String innerXmlWithoutHtml = getInnerXmlWithoutHtml(initializeXmlParser, "passScore");
                    if (Extensions.tryParseDouble(innerXmlWithoutHtml)) {
                        this.mQuestionnaire.passScore = Double.parseDouble(innerXmlWithoutHtml);
                    }
                } else if (name.equals("passMessage")) {
                    this.mQuestionnaire.passMessage = getInnerXmlWithoutHtml(initializeXmlParser, "passMessage");
                } else if (name.equals("failMessage")) {
                    this.mQuestionnaire.failMessage = getInnerXmlWithoutHtml(initializeXmlParser, "failMessage");
                } else if (name.equals("isGated")) {
                    String innerXmlWithoutHtml2 = getInnerXmlWithoutHtml(initializeXmlParser, "isGated");
                    if (Extensions.tryParseBoolean(innerXmlWithoutHtml2)) {
                        this.mQuestionnaire.isGated = Boolean.parseBoolean(innerXmlWithoutHtml2);
                    }
                } else if (name.equals("expirationDate")) {
                    this.mQuestionnaire.expirationDate = getInnerXmlWithoutHtml(initializeXmlParser, "expirationDate");
                } else if (name.equals(PlaceFields.PAGE)) {
                    String attributeValue = initializeXmlParser.getAttributeValue("", "formtype");
                    this.mQuestionnaire.checkIfEvalScreen(attributeValue);
                    String attributeValue2 = initializeXmlParser.getAttributeValue("", "id");
                    if (attributeValue.equals(ActivityTest.FORMAT_TYPE_MISCELLANEOUS)) {
                        skipwithPassCheck(initializeXmlParser);
                    } else if (attributeValue.equals(ActivityTest.FORMAT_TYPE_EVAL)) {
                        skipwithPassCheck(initializeXmlParser);
                    } else {
                        ActivityTest parsePage = parsePage(initializeXmlParser, attributeValue);
                        parsePage.formatType = attributeValue;
                        parsePage.id = attributeValue2;
                        if (parsePage != null && parsePage.questions.size() > 0) {
                            boolean z = true;
                            int i = 0;
                            boolean z2 = true;
                            for (Question next : parsePage.questions) {
                                if (!next.isScorable) {
                                    z2 = false;
                                }
                                if (!next.isPassed) {
                                    i++;
                                }
                            }
                            if (this.mQuestionnaire.isPassed && !parsePage.isPassed && parsePage.isScorable) {
                                this.mQuestionnaire.isPassed = false;
                                z = false;
                            }
                            this.mQuestionnaire.initialParsedAnswersScore = QnaScoreCalculator.getCurrentScore(i, parsePage.questions.size());
                            parsePage.isPassed = z;
                            parsePage.isScorable = z2;
                            this.mQuestionnaire.tests.add(parsePage);
                        }
                    }
                } else if (name.equals("question")) {
                    Question parseQuestion = parseQuestion(initializeXmlParser);
                    ActivityTest activityTest = new ActivityTest();
                    activityTest.type = ActivityTest.FORMAT_TYPE_NONE;
                    activityTest.questions.add(parseQuestion);
                    this.mQuestionnaire.tests.add(activityTest);
                } else {
                    skipwithPassCheck(initializeXmlParser);
                }
            }
        }
        return this.mQuestionnaire;
    }

    private ActivityTest parsePage(XmlPullParser xmlPullParser, String str) throws XmlPullParserException, IOException {
        ActivityTest activityTest = new ActivityTest();
        String str2 = null;
        xmlPullParser.require(2, (String) null, PlaceFields.PAGE);
        while (xmlPullParser.next() != 3) {
            String name = xmlPullParser.getName();
            if (name != null) {
                if (name.equals("intro")) {
                    str2 = getInnerXmlWithoutHtml(xmlPullParser, "intro");
                } else if (name.equals("title")) {
                    activityTest.title = getInnerXmlWithoutHtml(xmlPullParser, "title");
                } else if (name.equals("question")) {
                    int i = -1;
                    boolean z = true;
                    boolean z2 = Extensions.tryParseBoolean(xmlPullParser.getAttributeValue("", "required")) && xmlPullParser.getAttributeValue("", "required").equals(ServerProtocol.DIALOG_RETURN_SCOPES_TRUE);
                    boolean z3 = Extensions.tryParseBoolean(xmlPullParser.getAttributeValue("", "poll")) && xmlPullParser.getAttributeValue("", "poll").equals(ServerProtocol.DIALOG_RETURN_SCOPES_TRUE);
                    boolean z4 = Extensions.tryParseBoolean(xmlPullParser.getAttributeValue("", "showAnsTable")) && xmlPullParser.getAttributeValue("", "showAnsTable").equals(ServerProtocol.DIALOG_RETURN_SCOPES_TRUE);
                    if (!Extensions.tryParseBoolean(xmlPullParser.getAttributeValue("", "scorable")) || !xmlPullParser.getAttributeValue("", "scorable").equals(ServerProtocol.DIALOG_RETURN_SCOPES_TRUE)) {
                        z = false;
                    }
                    if (Extensions.tryParseInteger(xmlPullParser.getAttributeValue("", "id"))) {
                        i = Integer.parseInt(xmlPullParser.getAttributeValue("", "id"));
                    }
                    if (activityTest.type == null || !activityTest.type.equals(ActivityTest.FORMAT_TYPE_PRE)) {
                        Question parseQuestion = parseQuestion(xmlPullParser);
                        parseQuestion.pageIntro = str2;
                        parseQuestion.isRequired = z2;
                        parseQuestion.formatType = str;
                        parseQuestion.isScorable = z;
                        parseQuestion.isPoll = z3;
                        parseQuestion.showAnswersTable = z4;
                        parseQuestion.id = i;
                        activityTest.questions.add(parseQuestion);
                        if (this.mQuestionnaire.isPassed && (parseQuestion.isScorable || parseQuestion.formatType.equalsIgnoreCase("post"))) {
                            this.mQuestionnaire.isPassed = parseQuestion.isPassed;
                        }
                    } else {
                        Question parseQuestion2 = parseQuestion(xmlPullParser);
                        parseQuestion2.pageIntro = str2;
                        parseQuestion2.isRequired = z2;
                        parseQuestion2.formatType = str;
                        parseQuestion2.isScorable = z;
                        parseQuestion2.isPoll = z3;
                        parseQuestion2.id = i;
                        activityTest.questions.add(parseQuestion2);
                    }
                } else {
                    skipwithPassCheck(xmlPullParser);
                }
            }
        }
        return activityTest;
    }

    private Question parseQuestion(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        Question question = new Question();
        xmlPullParser.require(2, (String) null, "question");
        while (xmlPullParser.next() != 3) {
            String name = xmlPullParser.getName();
            if (name != null) {
                if (name.equals("questionText")) {
                    question.questionText = getInnerXmlWithoutHtml(xmlPullParser, "questionText");
                } else if (name.equals("questionIntro")) {
                    question.questionIntro = getInnerXmlWithoutHtml(xmlPullParser, "questionIntro");
                } else if (name.equals("displayRule")) {
                    question.displayRule = getInnerXmlWithoutHtml(xmlPullParser, "displayRule");
                } else if (name.equals("userTypeName")) {
                    question.userTypeName = getInnerXmlWithoutHtml(xmlPullParser, "userTypeName");
                } else if (name.equals(Constants.FEEDBACK_VERSION)) {
                    question.feedback = getInnerXmlWithoutHtml(xmlPullParser, Constants.FEEDBACK_VERSION);
                } else if (name.equals("responded")) {
                    String innerXmlWithoutHtml = getInnerXmlWithoutHtml(xmlPullParser, "responded");
                    if (Extensions.tryParseBoolean(innerXmlWithoutHtml)) {
                        question.isResponded = Boolean.parseBoolean(innerXmlWithoutHtml);
                    }
                } else if (name.equals("totalResponse")) {
                    String innerXml = getInnerXml(xmlPullParser, "totalResponse");
                    if (Extensions.tryParseInteger(innerXml)) {
                        question.totalResponse = Integer.parseInt(innerXml);
                    }
                } else if (name.equals("passed")) {
                    String innerXmlWithoutHtml2 = getInnerXmlWithoutHtml(xmlPullParser, "passed");
                    if (Extensions.tryParseBoolean(innerXmlWithoutHtml2)) {
                        question.isPassed = Boolean.parseBoolean(innerXmlWithoutHtml2);
                    }
                } else if (name.equals("isViewResults")) {
                    String innerXmlWithoutHtml3 = getInnerXmlWithoutHtml(xmlPullParser, "isViewResults");
                    if (Extensions.tryParseBoolean(innerXmlWithoutHtml3)) {
                        question.isViewResults = Boolean.parseBoolean(innerXmlWithoutHtml3);
                    }
                } else if (name.equals("choice")) {
                    question.answers.add(parserAnswer(xmlPullParser));
                } else {
                    skipwithPassCheck(xmlPullParser);
                }
            }
        }
        return question;
    }

    private Answer parserAnswer(XmlPullParser xmlPullParser) throws IOException, XmlPullParserException {
        Answer answer = new Answer();
        xmlPullParser.require(2, (String) null, "choice");
        String attributeValue = xmlPullParser.getAttributeValue("", "id");
        if (Extensions.tryParseInteger(attributeValue)) {
            answer.id = Integer.parseInt(attributeValue);
        }
        String attributeValue2 = xmlPullParser.getAttributeValue("", "order");
        if (Extensions.tryParseInteger(attributeValue2)) {
            answer.order = Integer.parseInt(attributeValue2);
        }
        while (xmlPullParser.next() != 3) {
            String name = xmlPullParser.getName();
            if (name != null) {
                if (name.equals("choiceText")) {
                    answer.text = getInnerXmlWithoutHtml(xmlPullParser, "choiceText");
                } else if (name.equals("choiceExplanation")) {
                    answer.choiceExplination = getInnerXmlWithoutHtml(xmlPullParser, "choiceExplanation");
                } else if (name.equals("isCorrect")) {
                    String innerXmlWithoutHtml = getInnerXmlWithoutHtml(xmlPullParser, "isCorrect");
                    if (Extensions.tryParseBoolean(innerXmlWithoutHtml)) {
                        answer.isCorrect = Boolean.parseBoolean(innerXmlWithoutHtml);
                    }
                } else if (name.equals("selected")) {
                    String innerXmlWithoutHtml2 = getInnerXmlWithoutHtml(xmlPullParser, "selected");
                    if (Extensions.tryParseBoolean(innerXmlWithoutHtml2)) {
                        answer.isSelected = Boolean.parseBoolean(innerXmlWithoutHtml2);
                    }
                } else if (name.equals("totalResponse")) {
                    String innerXmlWithoutHtml3 = getInnerXmlWithoutHtml(xmlPullParser, "totalResponse");
                    if (Extensions.tryParseInteger(innerXmlWithoutHtml3)) {
                        answer.totalResponse = Integer.parseInt(innerXmlWithoutHtml3);
                    }
                } else {
                    skipwithPassCheck(xmlPullParser);
                }
            }
        }
        return answer;
    }

    /* access modifiers changed from: protected */
    public void skipwithPassCheck(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        if (xmlPullParser.getEventType() == 2) {
            int i = 1;
            while (i != 0) {
                int next = xmlPullParser.next();
                if (next != 2) {
                    if (next == 3) {
                        i--;
                    }
                } else if (!this.mQuestionnaire.getShouldGoToEvaluation() || this.mQuestionnaire.evaluationCompleted || !xmlPullParser.getName().equals("passed")) {
                    i++;
                } else {
                    String innerXmlWithoutHtml = getInnerXmlWithoutHtml(xmlPullParser, "passed");
                    if (Extensions.tryParseBoolean(innerXmlWithoutHtml)) {
                        this.mQuestionnaire.evaluationCompleted = Boolean.parseBoolean(innerXmlWithoutHtml);
                    }
                }
            }
            return;
        }
        throw new IllegalStateException();
    }
}
