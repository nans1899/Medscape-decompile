package org.xmlpull.v1.builder;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
import org.xmlpull.v1.XmlSerializer;
import org.xmlpull.v1.builder.impl.XmlPullBuilderImpl;

public abstract class XmlPullBuilder {
    protected XmlPullParserFactory factory;

    public abstract XmlDocument newDocument(String str, Boolean bool, String str2) throws XmlBuilderException;

    public abstract XmlElement newFragment(String str) throws XmlBuilderException;

    public abstract XmlElement newFragment(String str, String str2) throws XmlBuilderException;

    public abstract XmlElement newFragment(XmlNamespace xmlNamespace, String str) throws XmlBuilderException;

    public abstract XmlNamespace newNamespace(String str) throws XmlBuilderException;

    public abstract XmlNamespace newNamespace(String str, String str2) throws XmlBuilderException;

    public abstract XmlDocument parse(XmlPullParser xmlPullParser) throws XmlBuilderException;

    public abstract XmlElement parseFragment(XmlPullParser xmlPullParser) throws XmlBuilderException;

    public abstract Object parseItem(XmlPullParser xmlPullParser) throws XmlBuilderException;

    public abstract XmlDocument parseLocation(String str) throws XmlBuilderException;

    public abstract XmlElement parseStartTag(XmlPullParser xmlPullParser) throws XmlBuilderException;

    public abstract void serialize(Object obj, XmlSerializer xmlSerializer) throws XmlBuilderException;

    public abstract void serializeEndTag(XmlElement xmlElement, XmlSerializer xmlSerializer) throws XmlBuilderException;

    public abstract void serializeItem(Object obj, XmlSerializer xmlSerializer) throws XmlBuilderException;

    public abstract void serializeStartTag(XmlElement xmlElement, XmlSerializer xmlSerializer) throws XmlBuilderException;

    public static XmlPullBuilder newInstance() throws XmlBuilderException {
        XmlPullBuilderImpl xmlPullBuilderImpl = new XmlPullBuilderImpl();
        try {
            XmlPullParserFactory newInstance = XmlPullParserFactory.newInstance();
            xmlPullBuilderImpl.factory = newInstance;
            newInstance.setNamespaceAware(true);
            return xmlPullBuilderImpl;
        } catch (XmlPullParserException e) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("could not create XmlPull factory:");
            stringBuffer.append(e);
            throw new XmlBuilderException(stringBuffer.toString(), e);
        }
    }

    public XmlPullParserFactory getFactory() throws XmlBuilderException {
        return this.factory;
    }

    public XmlDocument newDocument() throws XmlBuilderException {
        return newDocument((String) null, (Boolean) null, (String) null);
    }

    public XmlDocument parseInputStream(InputStream inputStream) throws XmlBuilderException {
        try {
            XmlPullParser newPullParser = this.factory.newPullParser();
            newPullParser.setInput(inputStream, (String) null);
            return parse(newPullParser);
        } catch (XmlPullParserException e) {
            throw new XmlBuilderException("could not start parsing input stream", e);
        }
    }

    public XmlDocument parseInputStream(InputStream inputStream, String str) throws XmlBuilderException {
        try {
            XmlPullParser newPullParser = this.factory.newPullParser();
            newPullParser.setInput(inputStream, str);
            return parse(newPullParser);
        } catch (XmlPullParserException e) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("could not start parsing input stream (encoding=");
            stringBuffer.append(str);
            stringBuffer.append(")");
            throw new XmlBuilderException(stringBuffer.toString(), e);
        }
    }

    public XmlDocument parseReader(Reader reader) throws XmlBuilderException {
        try {
            XmlPullParser newPullParser = this.factory.newPullParser();
            newPullParser.setInput(reader);
            return parse(newPullParser);
        } catch (XmlPullParserException e) {
            throw new XmlBuilderException("could not start parsing input from reader", e);
        }
    }

    public XmlElement parseFragmentFromInputStream(InputStream inputStream) throws XmlBuilderException {
        try {
            XmlPullParser newPullParser = this.factory.newPullParser();
            newPullParser.setInput(inputStream, (String) null);
            newPullParser.nextTag();
            return parseFragment(newPullParser);
        } catch (IOException e) {
            throw new XmlBuilderException("IO error when starting to parse input stream", e);
        } catch (XmlPullParserException e2) {
            throw new XmlBuilderException("could not start parsing input stream", e2);
        }
    }

    public XmlElement parseFragementFromInputStream(InputStream inputStream, String str) throws XmlBuilderException {
        try {
            XmlPullParser newPullParser = this.factory.newPullParser();
            newPullParser.setInput(inputStream, str);
            newPullParser.nextTag();
            return parseFragment(newPullParser);
        } catch (IOException e) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("IO error when starting to parse input stream (encoding=");
            stringBuffer.append(str);
            stringBuffer.append(")");
            throw new XmlBuilderException(stringBuffer.toString(), e);
        } catch (XmlPullParserException e2) {
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append("could not start parsing input stream (encoding=");
            stringBuffer2.append(str);
            stringBuffer2.append(")");
            throw new XmlBuilderException(stringBuffer2.toString(), e2);
        }
    }

    public XmlElement parseFragmentFromReader(Reader reader) throws XmlBuilderException {
        try {
            XmlPullParser newPullParser = this.factory.newPullParser();
            newPullParser.setInput(reader);
            newPullParser.nextTag();
            return parseFragment(newPullParser);
        } catch (IOException e) {
            throw new XmlBuilderException("IO error when starting to parse from reader", e);
        } catch (XmlPullParserException e2) {
            throw new XmlBuilderException("could not start parsing input from reader", e2);
        }
    }

    public void skipSubTree(XmlPullParser xmlPullParser) throws XmlBuilderException {
        try {
            xmlPullParser.require(2, (String) null, (String) null);
            int i = 1;
            while (i > 0) {
                int next = xmlPullParser.next();
                if (next == 3) {
                    i--;
                } else if (next == 2) {
                    i++;
                }
            }
        } catch (XmlPullParserException e) {
            throw new XmlBuilderException("could not skip subtree", e);
        } catch (IOException e2) {
            throw new XmlBuilderException("IO error when skipping subtree", e2);
        }
    }

    public void serializeToOutputStream(Object obj, OutputStream outputStream) throws XmlBuilderException {
        serializeToOutputStream(obj, outputStream, "UTF8");
    }

    public void serializeToOutputStream(Object obj, OutputStream outputStream, String str) throws XmlBuilderException {
        try {
            XmlSerializer newSerializer = this.factory.newSerializer();
            newSerializer.setOutput(outputStream, str);
            serialize(obj, newSerializer);
        } catch (Exception e) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("could not serialize node to output stream (encoding=");
            stringBuffer.append(str);
            stringBuffer.append(")");
            throw new XmlBuilderException(stringBuffer.toString(), e);
        }
    }

    public void serializeToWriter(Object obj, Writer writer) throws XmlBuilderException {
        try {
            XmlSerializer newSerializer = this.factory.newSerializer();
            newSerializer.setOutput(writer);
            serialize(obj, newSerializer);
        } catch (Exception e) {
            throw new XmlBuilderException("could not serialize node to writer", e);
        }
    }

    public String serializeToString(Object obj) throws XmlBuilderException {
        StringWriter stringWriter = new StringWriter();
        serializeToWriter(obj, stringWriter);
        return stringWriter.toString();
    }
}
