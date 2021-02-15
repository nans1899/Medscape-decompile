package org.xmlpull.v1.wrapper;

import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public interface XmlPullParserWrapper extends XmlPullParser {
    public static final String NO_NAMESPACE = "";
    public static final String XSI_NS = "http://www.w3.org/2001/XMLSchema-instance";

    String getAttributeValue(String str);

    String getPIData() throws IllegalStateException;

    String getPITarget() throws IllegalStateException;

    String getRequiredAttributeValue(String str) throws IOException, XmlPullParserException;

    String getRequiredAttributeValue(String str, String str2) throws IOException, XmlPullParserException;

    String getRequiredElementText(String str, String str2) throws IOException, XmlPullParserException;

    boolean isNil() throws IOException, XmlPullParserException;

    boolean matches(int i, String str, String str2) throws XmlPullParserException;

    void nextEndTag() throws XmlPullParserException, IOException;

    void nextEndTag(String str) throws XmlPullParserException, IOException;

    void nextEndTag(String str, String str2) throws XmlPullParserException, IOException;

    void nextStartTag() throws XmlPullParserException, IOException;

    void nextStartTag(String str) throws XmlPullParserException, IOException;

    void nextStartTag(String str, String str2) throws XmlPullParserException, IOException;

    String nextText(String str, String str2) throws IOException, XmlPullParserException;

    double readDouble() throws XmlPullParserException, IOException;

    double readDoubleElement(String str, String str2) throws XmlPullParserException, IOException;

    float readFloat() throws XmlPullParserException, IOException;

    float readFloatElement(String str, String str2) throws XmlPullParserException, IOException;

    int readInt() throws XmlPullParserException, IOException;

    int readIntElement(String str, String str2) throws XmlPullParserException, IOException;

    String readString() throws XmlPullParserException, IOException;

    String readStringElemet(String str, String str2) throws XmlPullParserException, IOException;

    void skipSubTree() throws XmlPullParserException, IOException;
}
