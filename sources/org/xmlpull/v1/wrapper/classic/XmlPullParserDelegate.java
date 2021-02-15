package org.xmlpull.v1.wrapper.classic;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class XmlPullParserDelegate implements XmlPullParser {
    protected XmlPullParser pp;

    public XmlPullParserDelegate(XmlPullParser xmlPullParser) {
        this.pp = xmlPullParser;
    }

    public String getText() {
        return this.pp.getText();
    }

    public void setFeature(String str, boolean z) throws XmlPullParserException {
        this.pp.setFeature(str, z);
    }

    public char[] getTextCharacters(int[] iArr) {
        return this.pp.getTextCharacters(iArr);
    }

    public int getColumnNumber() {
        return this.pp.getColumnNumber();
    }

    public int getNamespaceCount(int i) throws XmlPullParserException {
        return this.pp.getNamespaceCount(i);
    }

    public String getNamespacePrefix(int i) throws XmlPullParserException {
        return this.pp.getNamespacePrefix(i);
    }

    public String getAttributeName(int i) {
        return this.pp.getAttributeName(i);
    }

    public String getName() {
        return this.pp.getName();
    }

    public boolean getFeature(String str) {
        return this.pp.getFeature(str);
    }

    public String getInputEncoding() {
        return this.pp.getInputEncoding();
    }

    public String getAttributeValue(int i) {
        return this.pp.getAttributeValue(i);
    }

    public String getNamespace(String str) {
        return this.pp.getNamespace(str);
    }

    public void setInput(Reader reader) throws XmlPullParserException {
        this.pp.setInput(reader);
    }

    public int getLineNumber() {
        return this.pp.getLineNumber();
    }

    public Object getProperty(String str) {
        return this.pp.getProperty(str);
    }

    public boolean isEmptyElementTag() throws XmlPullParserException {
        return this.pp.isEmptyElementTag();
    }

    public boolean isAttributeDefault(int i) {
        return this.pp.isAttributeDefault(i);
    }

    public String getNamespaceUri(int i) throws XmlPullParserException {
        return this.pp.getNamespaceUri(i);
    }

    public int next() throws XmlPullParserException, IOException {
        return this.pp.next();
    }

    public int nextToken() throws XmlPullParserException, IOException {
        return this.pp.nextToken();
    }

    public void defineEntityReplacementText(String str, String str2) throws XmlPullParserException {
        this.pp.defineEntityReplacementText(str, str2);
    }

    public int getAttributeCount() {
        return this.pp.getAttributeCount();
    }

    public boolean isWhitespace() throws XmlPullParserException {
        return this.pp.isWhitespace();
    }

    public String getPrefix() {
        return this.pp.getPrefix();
    }

    public void require(int i, String str, String str2) throws XmlPullParserException, IOException {
        this.pp.require(i, str, str2);
    }

    public String nextText() throws XmlPullParserException, IOException {
        return this.pp.nextText();
    }

    public String getAttributeType(int i) {
        return this.pp.getAttributeType(i);
    }

    public int getDepth() {
        return this.pp.getDepth();
    }

    public int nextTag() throws XmlPullParserException, IOException {
        return this.pp.nextTag();
    }

    public int getEventType() throws XmlPullParserException {
        return this.pp.getEventType();
    }

    public String getAttributePrefix(int i) {
        return this.pp.getAttributePrefix(i);
    }

    public void setInput(InputStream inputStream, String str) throws XmlPullParserException {
        this.pp.setInput(inputStream, str);
    }

    public String getAttributeValue(String str, String str2) {
        return this.pp.getAttributeValue(str, str2);
    }

    public void setProperty(String str, Object obj) throws XmlPullParserException {
        this.pp.setProperty(str, obj);
    }

    public String getPositionDescription() {
        return this.pp.getPositionDescription();
    }

    public String getNamespace() {
        return this.pp.getNamespace();
    }

    public String getAttributeNamespace(int i) {
        return this.pp.getAttributeNamespace(i);
    }
}
