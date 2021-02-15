package com.bea.xml.stream;

import javax.xml.namespace.NamespaceContext;
import javax.xml.namespace.QName;
import javax.xml.stream.Location;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

public class ReaderDelegate implements XMLStreamReader {
    private XMLStreamReader reader;

    public ReaderDelegate(XMLStreamReader xMLStreamReader) {
        this.reader = xMLStreamReader;
    }

    public void setDelegate(XMLStreamReader xMLStreamReader) {
        this.reader = xMLStreamReader;
    }

    public XMLStreamReader getDelegate() {
        return this.reader;
    }

    public int next() throws XMLStreamException {
        return this.reader.next();
    }

    public int nextTag() throws XMLStreamException {
        return this.reader.nextTag();
    }

    public String getElementText() throws XMLStreamException {
        return this.reader.getElementText();
    }

    public void require(int i, String str, String str2) throws XMLStreamException {
        this.reader.require(i, str, str2);
    }

    public boolean hasNext() throws XMLStreamException {
        return this.reader.hasNext();
    }

    public void close() throws XMLStreamException {
        this.reader.close();
    }

    public String getNamespaceURI(String str) {
        return this.reader.getNamespaceURI(str);
    }

    public NamespaceContext getNamespaceContext() {
        return this.reader.getNamespaceContext();
    }

    public boolean isStartElement() {
        return this.reader.isStartElement();
    }

    public boolean isEndElement() {
        return this.reader.isEndElement();
    }

    public boolean isCharacters() {
        return this.reader.isCharacters();
    }

    public boolean isWhiteSpace() {
        return this.reader.isWhiteSpace();
    }

    public QName getAttributeName(int i) {
        return this.reader.getAttributeName(i);
    }

    public int getTextCharacters(int i, char[] cArr, int i2, int i3) throws XMLStreamException {
        return this.reader.getTextCharacters(i, cArr, i2, i3);
    }

    public String getAttributeValue(String str, String str2) {
        return this.reader.getAttributeValue(str, str2);
    }

    public int getAttributeCount() {
        return this.reader.getAttributeCount();
    }

    public String getAttributePrefix(int i) {
        return this.reader.getAttributePrefix(i);
    }

    public String getAttributeNamespace(int i) {
        return this.reader.getAttributeNamespace(i);
    }

    public String getAttributeLocalName(int i) {
        return this.reader.getAttributeLocalName(i);
    }

    public String getAttributeType(int i) {
        return this.reader.getAttributeType(i);
    }

    public String getAttributeValue(int i) {
        return this.reader.getAttributeValue(i);
    }

    public boolean isAttributeSpecified(int i) {
        return this.reader.isAttributeSpecified(i);
    }

    public int getNamespaceCount() {
        return this.reader.getNamespaceCount();
    }

    public String getNamespacePrefix(int i) {
        return this.reader.getNamespacePrefix(i);
    }

    public String getNamespaceURI(int i) {
        return this.reader.getNamespaceURI(i);
    }

    public int getEventType() {
        return this.reader.getEventType();
    }

    public String getText() {
        return this.reader.getText();
    }

    public char[] getTextCharacters() {
        return this.reader.getTextCharacters();
    }

    public int getTextStart() {
        return this.reader.getTextStart();
    }

    public int getTextLength() {
        return this.reader.getTextLength();
    }

    public String getEncoding() {
        return this.reader.getEncoding();
    }

    public boolean hasText() {
        return this.reader.hasText();
    }

    public Location getLocation() {
        return this.reader.getLocation();
    }

    public QName getName() {
        return this.reader.getName();
    }

    public String getLocalName() {
        return this.reader.getLocalName();
    }

    public boolean hasName() {
        return this.reader.hasName();
    }

    public String getNamespaceURI() {
        return this.reader.getNamespaceURI();
    }

    public String getPrefix() {
        return this.reader.getPrefix();
    }

    public String getVersion() {
        return this.reader.getVersion();
    }

    public boolean isStandalone() {
        return this.reader.isStandalone();
    }

    public boolean standaloneSet() {
        return this.reader.standaloneSet();
    }

    public String getCharacterEncodingScheme() {
        return this.reader.getCharacterEncodingScheme();
    }

    public String getPITarget() {
        return this.reader.getPITarget();
    }

    public String getPIData() {
        return this.reader.getPIData();
    }

    public Object getProperty(String str) {
        return this.reader.getProperty(str);
    }
}
