package com.bea.xml.stream.events;

import com.bea.xml.stream.util.EmptyIterator;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.webmd.wbmdcmepulse.models.articles.HtmlObject;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.xml.namespace.QName;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.Namespace;

public class EndElementEvent extends NamedEvent implements EndElement {
    private List outOfScopeNamespaces;

    public EndElementEvent() {
        init();
    }

    public EndElementEvent(QName qName) {
        super(qName);
        init();
    }

    /* access modifiers changed from: protected */
    public void init() {
        setEventType(2);
    }

    public Iterator getNamespaces() {
        List list = this.outOfScopeNamespaces;
        if (list == null) {
            return EmptyIterator.emptyIterator;
        }
        return list.iterator();
    }

    public void addNamespace(Namespace namespace) {
        if (this.outOfScopeNamespaces == null) {
            this.outOfScopeNamespaces = new ArrayList();
        }
        this.outOfScopeNamespaces.add(namespace);
    }

    public void reset() {
        List list = this.outOfScopeNamespaces;
        if (list != null) {
            list.clear();
        }
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("</");
        stringBuffer.append(nameAsString());
        String stringBuffer2 = stringBuffer.toString();
        Iterator namespaces = getNamespaces();
        while (namespaces.hasNext()) {
            StringBuffer stringBuffer3 = new StringBuffer();
            stringBuffer3.append(stringBuffer2);
            stringBuffer3.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
            stringBuffer3.append(namespaces.next().toString());
            stringBuffer2 = stringBuffer3.toString();
        }
        StringBuffer stringBuffer4 = new StringBuffer();
        stringBuffer4.append(stringBuffer2);
        stringBuffer4.append(HtmlObject.HtmlMarkUp.CLOSE_BRACKER);
        return stringBuffer4.toString();
    }

    /* access modifiers changed from: protected */
    public void doWriteAsEncodedUnicode(Writer writer) throws IOException {
        writer.write("</");
        QName name = getName();
        String prefix = name.getPrefix();
        if (prefix != null && prefix.length() > 0) {
            writer.write(prefix);
            writer.write(58);
        }
        writer.write(name.getLocalPart());
        writer.write(62);
    }
}
