package com.bea.xml.stream;

import java.util.Enumeration;
import java.util.HashSet;
import java.util.Hashtable;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLReporter;
import javax.xml.stream.XMLResolver;
import javax.xml.stream.util.XMLEventAllocator;

public class ConfigurationContextBase {
    private static String ENTITIES = "javax.xml.stream.entities";
    private static String EVENT_FILTER = "RI_EVENT_FILTER";
    private static String NOTATIONS = "javax.xml.stream.notations";
    static final String REPORT_CDATA = "http://java.sun.com/xml/stream/properties/report-cdata-event";
    private static String STREAM_FILTER = "RI_STREAM_FILTER";
    private static HashSet supportedFeatures;
    private Hashtable features;

    public String getVersion() {
        return "1.0";
    }

    static {
        HashSet hashSet = new HashSet();
        supportedFeatures = hashSet;
        hashSet.add(XMLInputFactory.IS_VALIDATING);
        supportedFeatures.add(XMLInputFactory.IS_COALESCING);
        supportedFeatures.add(XMLInputFactory.IS_REPLACING_ENTITY_REFERENCES);
        supportedFeatures.add(XMLInputFactory.IS_SUPPORTING_EXTERNAL_ENTITIES);
        supportedFeatures.add(XMLOutputFactory.IS_REPAIRING_NAMESPACES);
        supportedFeatures.add(XMLInputFactory.IS_NAMESPACE_AWARE);
        supportedFeatures.add(XMLInputFactory.SUPPORT_DTD);
        supportedFeatures.add(XMLInputFactory.REPORTER);
        supportedFeatures.add(XMLInputFactory.RESOLVER);
        supportedFeatures.add(XMLInputFactory.ALLOCATOR);
        supportedFeatures.add(NOTATIONS);
        supportedFeatures.add(ENTITIES);
        supportedFeatures.add(REPORT_CDATA);
    }

    public ConfigurationContextBase() {
        Hashtable hashtable = new Hashtable();
        this.features = hashtable;
        hashtable.put(XMLInputFactory.IS_VALIDATING, Boolean.FALSE);
        this.features.put(XMLInputFactory.IS_COALESCING, Boolean.FALSE);
        this.features.put(XMLInputFactory.IS_REPLACING_ENTITY_REFERENCES, Boolean.TRUE);
        this.features.put(XMLInputFactory.IS_SUPPORTING_EXTERNAL_ENTITIES, Boolean.FALSE);
        this.features.put(XMLInputFactory.IS_NAMESPACE_AWARE, Boolean.TRUE);
        this.features.put(XMLInputFactory.SUPPORT_DTD, Boolean.FALSE);
        this.features.put(XMLOutputFactory.IS_REPAIRING_NAMESPACES, Boolean.FALSE);
    }

    public void setEventAllocator(XMLEventAllocator xMLEventAllocator) {
        this.features.put(XMLInputFactory.ALLOCATOR, xMLEventAllocator);
    }

    public XMLEventAllocator getEventAllocator() {
        return (XMLEventAllocator) this.features.get(XMLInputFactory.ALLOCATOR);
    }

    public void setProperty(String str, Object obj) {
        if (str.equals(XMLInputFactory.IS_VALIDATING)) {
            setValidating(((Boolean) obj).booleanValue());
        } else if (str.equals(XMLInputFactory.IS_SUPPORTING_EXTERNAL_ENTITIES)) {
            setSupportExternalEntities(((Boolean) obj).booleanValue());
        } else if (str.equals(XMLInputFactory.IS_NAMESPACE_AWARE)) {
            setNamespaceAware(((Boolean) obj).booleanValue());
        } else {
            check(str);
            this.features.put(str, obj);
        }
    }

    public void check(String str) {
        if (!supportedFeatures.contains(str)) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Unable to access unsupported property ");
            stringBuffer.append(str);
            throw new IllegalArgumentException(stringBuffer.toString());
        }
    }

    public Object getProperty(String str) {
        check(str);
        return this.features.get(str);
    }

    public void setXMLReporter(XMLReporter xMLReporter) {
        this.features.put(XMLInputFactory.REPORTER, xMLReporter);
    }

    public XMLReporter getXMLReporter() {
        return (XMLReporter) this.features.get(XMLInputFactory.REPORTER);
    }

    public void setXMLResolver(XMLResolver xMLResolver) {
        this.features.put(XMLInputFactory.RESOLVER, xMLResolver);
    }

    public XMLResolver getXMLResolver() {
        return (XMLResolver) this.features.get(XMLInputFactory.RESOLVER);
    }

    public boolean getBool(String str) {
        check(str);
        return ((Boolean) this.features.get(str)).booleanValue();
    }

    public void setBool(String str, boolean z) {
        check(str);
        this.features.put(str, z ? Boolean.TRUE : Boolean.FALSE);
    }

    public void setCoalescing(boolean z) {
        setBool(XMLInputFactory.IS_COALESCING, z);
    }

    public boolean isCoalescing() {
        return getBool(XMLInputFactory.IS_COALESCING);
    }

    public void setValidating(boolean z) {
        if (z) {
            throw new IllegalArgumentException("This implementation does not support validation");
        }
    }

    public boolean isValidating() {
        return getBool(XMLInputFactory.IS_VALIDATING);
    }

    public void setReplacingEntities(boolean z) {
        setBool(XMLInputFactory.IS_REPLACING_ENTITY_REFERENCES, z);
    }

    public boolean isReplacingEntities() {
        return getBool(XMLInputFactory.IS_REPLACING_ENTITY_REFERENCES);
    }

    public void setSupportExternalEntities(boolean z) {
        if (z) {
            throw new IllegalArgumentException("This implementation does not resolve external entities ");
        }
    }

    public boolean isSupportingExternalEntities() {
        return getBool(XMLInputFactory.IS_SUPPORTING_EXTERNAL_ENTITIES);
    }

    public void setPrefixDefaulting(boolean z) {
        setBool(XMLOutputFactory.IS_REPAIRING_NAMESPACES, z);
    }

    public boolean isPrefixDefaulting() {
        return getBool(XMLOutputFactory.IS_REPAIRING_NAMESPACES);
    }

    public void setNamespaceAware(boolean z) {
        if (!z) {
            throw new IllegalArgumentException("This implementation does not allow disabling namespace processing");
        }
    }

    public boolean isNamespaceAware() {
        return getBool(XMLInputFactory.IS_NAMESPACE_AWARE);
    }

    public Enumeration getProperties() {
        return this.features.keys();
    }

    public boolean isPropertySupported(String str) {
        return supportedFeatures.contains(str);
    }
}
