package org.xmlpull.v1.builder.impl;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;
import org.xmlpull.v1.builder.XmlAttribute;
import org.xmlpull.v1.builder.XmlBuilderException;
import org.xmlpull.v1.builder.XmlComment;
import org.xmlpull.v1.builder.XmlContainer;
import org.xmlpull.v1.builder.XmlDocument;
import org.xmlpull.v1.builder.XmlElement;
import org.xmlpull.v1.builder.XmlNamespace;
import org.xmlpull.v1.builder.XmlPullBuilder;
import org.xmlpull.v1.builder.XmlSerializable;

public class XmlPullBuilderImpl extends XmlPullBuilder {
    private static final String PROPERTY_XMLDECL_STANDALONE = "http://xmlpull.org/v1/doc/properties.html#xmldecl-standalone";
    private static final String PROPERTY_XMLDECL_VERSION = "http://xmlpull.org/v1/doc/properties.html#xmldecl-version";

    public XmlDocument newDocument(String str, Boolean bool, String str2) {
        return new XmlDocumentImpl(str, bool, str2);
    }

    public XmlElement newFragment(String str) {
        return new XmlElementImpl((XmlNamespace) null, str);
    }

    public XmlElement newFragment(String str, String str2) {
        return new XmlElementImpl(str, str2);
    }

    public XmlElement newFragment(XmlNamespace xmlNamespace, String str) {
        return new XmlElementImpl(xmlNamespace, str);
    }

    public XmlNamespace newNamespace(String str) {
        return new XmlNamespaceImpl((String) null, str);
    }

    public XmlNamespace newNamespace(String str, String str2) {
        return new XmlNamespaceImpl(str, str2);
    }

    public XmlDocument parse(XmlPullParser xmlPullParser) {
        XmlDocument parseDocumentStart = parseDocumentStart(xmlPullParser);
        parseDocumentStart.setDocumentElement(parseFragment(xmlPullParser));
        return parseDocumentStart;
    }

    public Object parseItem(XmlPullParser xmlPullParser) {
        try {
            int eventType = xmlPullParser.getEventType();
            if (eventType == 2) {
                return parseStartTag(xmlPullParser);
            }
            if (eventType == 4) {
                return xmlPullParser.getText();
            }
            if (eventType == 0) {
                return parseDocumentStart(xmlPullParser);
            }
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("currently unsupported event type ");
            stringBuffer.append(XmlPullParser.TYPES[eventType]);
            stringBuffer.append(xmlPullParser.getPositionDescription());
            throw new XmlBuilderException(stringBuffer.toString());
        } catch (XmlPullParserException e) {
            throw new XmlBuilderException("could not parse XML item", e);
        }
    }

    private XmlDocument parseDocumentStart(XmlPullParser xmlPullParser) {
        try {
            if (xmlPullParser.getEventType() == 0) {
                xmlPullParser.next();
                return new XmlDocumentImpl((String) xmlPullParser.getProperty(PROPERTY_XMLDECL_VERSION), (Boolean) xmlPullParser.getProperty(PROPERTY_XMLDECL_STANDALONE), xmlPullParser.getInputEncoding());
            }
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("parser must be positioned on beginning of document and not ");
            stringBuffer.append(xmlPullParser.getPositionDescription());
            throw new XmlBuilderException(stringBuffer.toString());
        } catch (XmlPullParserException e) {
            throw new XmlBuilderException("could not parse XML document prolog", e);
        } catch (IOException e2) {
            throw new XmlBuilderException("could not read XML document prolog", e2);
        }
    }

    public XmlElement parseFragment(XmlPullParser xmlPullParser) {
        try {
            int eventType = xmlPullParser.getEventType();
            if (eventType == 2) {
                XmlElement parseStartTag = parseStartTag(xmlPullParser);
                while (true) {
                    int next = xmlPullParser.next();
                    if (next == 2) {
                        XmlElement parseStartTag2 = parseStartTag(xmlPullParser);
                        parseStartTag.addChild(parseStartTag2);
                        parseStartTag = parseStartTag2;
                    } else if (next == 3) {
                        XmlContainer parent = parseStartTag.getParent();
                        if (parent == null) {
                            return parseStartTag;
                        }
                        parseStartTag = (XmlElement) parent;
                    } else if (next == 4) {
                        parseStartTag.addChild(xmlPullParser.getText());
                    }
                }
            } else {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("expected parser to be on start tag and not ");
                stringBuffer.append(XmlPullParser.TYPES[eventType]);
                stringBuffer.append(xmlPullParser.getPositionDescription());
                throw new XmlBuilderException(stringBuffer.toString());
            }
        } catch (XmlPullParserException e) {
            throw new XmlBuilderException("could not build tree from XML", e);
        } catch (IOException e2) {
            throw new XmlBuilderException("could not read XML tree content", e2);
        }
    }

    public XmlElement parseStartTag(XmlPullParser xmlPullParser) {
        try {
            if (xmlPullParser.getEventType() == 2) {
                XmlElementImpl xmlElementImpl = new XmlElementImpl(xmlPullParser.getNamespace(), xmlPullParser.getName());
                for (int namespaceCount = xmlPullParser.getNamespaceCount(xmlPullParser.getDepth() - 1); namespaceCount < xmlPullParser.getNamespaceCount(xmlPullParser.getDepth()); namespaceCount++) {
                    String namespacePrefix = xmlPullParser.getNamespacePrefix(namespaceCount);
                    if (namespacePrefix == null) {
                        namespacePrefix = "";
                    }
                    xmlElementImpl.declareNamespace(namespacePrefix, xmlPullParser.getNamespaceUri(namespaceCount));
                }
                for (int i = 0; i < xmlPullParser.getAttributeCount(); i++) {
                    xmlElementImpl.addAttribute(xmlPullParser.getAttributeType(i), xmlPullParser.getAttributePrefix(i), xmlPullParser.getAttributeNamespace(i), xmlPullParser.getAttributeName(i), xmlPullParser.getAttributeValue(i), !xmlPullParser.isAttributeDefault(i));
                }
                return xmlElementImpl;
            }
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("parser must be on START_TAG and not ");
            stringBuffer.append(xmlPullParser.getPositionDescription());
            throw new XmlBuilderException(stringBuffer.toString());
        } catch (XmlPullParserException e) {
            throw new XmlBuilderException("could not parse XML start tag", e);
        }
    }

    public XmlDocument parseLocation(String str) {
        try {
            try {
                return parseInputStream(new URL(str).openStream());
            } catch (IOException e) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("could not open connection to URL ");
                stringBuffer.append(str);
                throw new XmlBuilderException(stringBuffer.toString(), e);
            }
        } catch (MalformedURLException e2) {
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append("could not parse URL ");
            stringBuffer2.append(str);
            throw new XmlBuilderException(stringBuffer2.toString(), e2);
        }
    }

    public void serialize(Object obj, XmlSerializer xmlSerializer) {
        if (obj instanceof XmlContainer) {
            serializeContainer((XmlContainer) obj, xmlSerializer);
        } else {
            serializeItem(obj, xmlSerializer);
        }
    }

    private void serializeContainer(XmlContainer xmlContainer, XmlSerializer xmlSerializer) {
        if (xmlContainer instanceof XmlSerializable) {
            ((XmlSerializable) xmlContainer).serialize(xmlSerializer);
        } else if (xmlContainer instanceof XmlDocument) {
            serializeDocument((XmlDocument) xmlContainer, xmlSerializer);
        } else if (xmlContainer instanceof XmlElement) {
            serializeFragment((XmlElement) xmlContainer, xmlSerializer);
        } else {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("could not serialzie unknown XML container ");
            stringBuffer.append(xmlContainer.getClass());
            throw new IllegalArgumentException(stringBuffer.toString());
        }
    }

    public void serializeItem(Object obj, XmlSerializer xmlSerializer) {
        try {
            if (obj instanceof XmlSerializable) {
                ((XmlSerializable) obj).serialize(xmlSerializer);
            } else if (obj instanceof String) {
                xmlSerializer.text(obj.toString());
            } else if (obj instanceof XmlComment) {
                xmlSerializer.comment(((XmlComment) obj).getContent());
            } else {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("could not serialize ");
                stringBuffer.append(obj.getClass());
                throw new IllegalArgumentException(stringBuffer.toString());
            }
        } catch (IOException e) {
            throw new XmlBuilderException("serializing XML start tag failed", e);
        }
    }

    public void serializeStartTag(XmlElement xmlElement, XmlSerializer xmlSerializer) {
        try {
            if (xmlElement.hasNamespaceDeclarations()) {
                Iterator namespaces = xmlElement.namespaces();
                while (namespaces.hasNext()) {
                    XmlNamespace xmlNamespace = (XmlNamespace) namespaces.next();
                    xmlSerializer.setPrefix(xmlNamespace.getPrefix(), xmlNamespace.getNamespaceName());
                }
            }
            xmlSerializer.startTag(xmlElement.getNamespaceName(), xmlElement.getName());
            if (xmlElement.hasAttributes()) {
                Iterator attributes = xmlElement.attributes();
                while (attributes.hasNext()) {
                    XmlAttribute xmlAttribute = (XmlAttribute) attributes.next();
                    xmlSerializer.attribute(xmlAttribute.getNamespaceName(), xmlAttribute.getName(), xmlAttribute.getValue());
                }
            }
        } catch (IOException e) {
            throw new XmlBuilderException("serializing XML start tag failed", e);
        }
    }

    public void serializeEndTag(XmlElement xmlElement, XmlSerializer xmlSerializer) {
        try {
            xmlSerializer.endTag(xmlElement.getNamespaceName(), xmlElement.getName());
        } catch (IOException e) {
            throw new XmlBuilderException("serializing XML end tag failed", e);
        }
    }

    private void serializeDocument(XmlDocument xmlDocument, XmlSerializer xmlSerializer) {
        try {
            xmlSerializer.startDocument(xmlDocument.getCharacterEncodingScheme(), xmlDocument.isStandalone());
            serializeFragment(xmlDocument.getDocumentElement(), xmlSerializer);
            try {
                xmlSerializer.endDocument();
            } catch (IOException e) {
                throw new XmlBuilderException("serializing XML document end failed", e);
            }
        } catch (IOException e2) {
            throw new XmlBuilderException("serialziing XML document start failed", e2);
        }
    }

    private void serializeFragment(XmlElement xmlElement, XmlSerializer xmlSerializer) {
        serializeStartTag(xmlElement, xmlSerializer);
        if (xmlElement.hasChildren()) {
            Iterator children = xmlElement.children();
            while (children.hasNext()) {
                Object next = children.next();
                if (next instanceof XmlSerializable) {
                    ((XmlSerializable) next).serialize(xmlSerializer);
                } else if (next instanceof XmlElement) {
                    serializeFragment((XmlElement) next, xmlSerializer);
                } else {
                    serializeItem(next, xmlSerializer);
                }
            }
        }
        serializeEndTag(xmlElement, xmlSerializer);
    }
}
