package com.webmd.wbmdcmepulse.models.interfaces;

import com.webmd.wbmdcmepulse.models.articles.Questionnaire;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParserException;

public interface IQuestionnaireParser {
    Questionnaire parse() throws IOException, XmlPullParserException;
}
