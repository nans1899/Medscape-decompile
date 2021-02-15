package com.bea.xml.stream.util;

import com.appboy.Constants;
import java.io.PrintStream;
import java.util.Iterator;
import javax.xml.XMLConstants;
import javax.xml.namespace.NamespaceContext;

public class NamespaceContextImpl implements NamespaceContext {
    SymbolTable prefixTable;
    NamespaceContext rootContext;
    SymbolTable uriTable;

    public NamespaceContextImpl() {
        this.prefixTable = new SymbolTable();
        this.uriTable = new SymbolTable();
        init();
    }

    public NamespaceContextImpl(NamespaceContext namespaceContext) {
        this.prefixTable = new SymbolTable();
        this.uriTable = new SymbolTable();
        this.rootContext = null;
        init();
    }

    public void init() {
        bindNamespace("xml", XMLConstants.XML_NS_URI);
        bindNamespace(XMLConstants.XMLNS_ATTRIBUTE, XMLConstants.XML_NS_URI);
    }

    public void openScope() {
        this.prefixTable.openScope();
        this.uriTable.openScope();
    }

    public void closeScope() {
        this.prefixTable.closeScope();
        this.uriTable.closeScope();
    }

    public void bindNamespace(String str, String str2) {
        this.prefixTable.put(str, str2);
        this.uriTable.put(str2, str);
    }

    public int getDepth() {
        return this.prefixTable.getDepth();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0008, code lost:
        r1 = r2.rootContext;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String getNamespaceURI(java.lang.String r3) {
        /*
            r2 = this;
            com.bea.xml.stream.util.SymbolTable r0 = r2.prefixTable
            java.lang.String r0 = r0.get(r3)
            if (r0 != 0) goto L_0x0011
            javax.xml.namespace.NamespaceContext r1 = r2.rootContext
            if (r1 == 0) goto L_0x0011
            java.lang.String r3 = r1.getNamespaceURI(r3)
            return r3
        L_0x0011:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bea.xml.stream.util.NamespaceContextImpl.getNamespaceURI(java.lang.String):java.lang.String");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0008, code lost:
        r1 = r2.rootContext;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String getPrefix(java.lang.String r3) {
        /*
            r2 = this;
            com.bea.xml.stream.util.SymbolTable r0 = r2.uriTable
            java.lang.String r0 = r0.get(r3)
            if (r0 != 0) goto L_0x0011
            javax.xml.namespace.NamespaceContext r1 = r2.rootContext
            if (r1 == 0) goto L_0x0011
            java.lang.String r3 = r1.getPrefix(r3)
            return r3
        L_0x0011:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bea.xml.stream.util.NamespaceContextImpl.getPrefix(java.lang.String):java.lang.String");
    }

    public void bindDefaultNameSpace(String str) {
        bindNamespace("", str);
    }

    public void unbindDefaultNameSpace() {
        bindNamespace("", (String) null);
    }

    public void unbindNamespace(String str, String str2) {
        this.prefixTable.put(str, (String) null);
        this.prefixTable.put(str2, (String) null);
    }

    public String getDefaultNameSpace() {
        return getNamespaceURI("");
    }

    public Iterator getPrefixes(String str) {
        return this.uriTable.getAll(str).iterator();
    }

    public static void main(String[] strArr) throws Exception {
        NamespaceContextImpl namespaceContextImpl = new NamespaceContextImpl();
        namespaceContextImpl.openScope();
        namespaceContextImpl.bindNamespace(Constants.APPBOY_PUSH_CONTENT_KEY, "uri");
        namespaceContextImpl.bindNamespace("b", "uri");
        PrintStream printStream = System.out;
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("a=");
        stringBuffer.append(namespaceContextImpl.getNamespaceURI(Constants.APPBOY_PUSH_CONTENT_KEY));
        printStream.println(stringBuffer.toString());
        PrintStream printStream2 = System.out;
        StringBuffer stringBuffer2 = new StringBuffer();
        stringBuffer2.append("uri=");
        stringBuffer2.append(namespaceContextImpl.getPrefix("uri"));
        printStream2.println(stringBuffer2.toString());
        Iterator prefixes = namespaceContextImpl.getPrefixes("uri");
        while (prefixes.hasNext()) {
            PrintStream printStream3 = System.out;
            StringBuffer stringBuffer3 = new StringBuffer();
            stringBuffer3.append("1 uri->");
            stringBuffer3.append(prefixes.next());
            printStream3.println(stringBuffer3.toString());
        }
        namespaceContextImpl.openScope();
        namespaceContextImpl.bindNamespace(Constants.APPBOY_PUSH_CONTENT_KEY, "uri2");
        Iterator prefixes2 = namespaceContextImpl.getPrefixes("uri");
        while (prefixes2.hasNext()) {
            PrintStream printStream4 = System.out;
            StringBuffer stringBuffer4 = new StringBuffer();
            stringBuffer4.append("2 uri->");
            stringBuffer4.append(prefixes2.next());
            printStream4.println(stringBuffer4.toString());
        }
        namespaceContextImpl.closeScope();
        namespaceContextImpl.closeScope();
    }
}
