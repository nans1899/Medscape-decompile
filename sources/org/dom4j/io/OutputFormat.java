package org.dom4j.io;

import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import org.apache.commons.io.IOUtils;

public class OutputFormat implements Cloneable {
    protected static final String STANDARD_INDENT = "  ";
    private char attributeQuoteChar = '\"';
    private boolean doXHTML = false;
    private String encoding = "UTF-8";
    private boolean expandEmptyElements = false;
    private String indent = null;
    private String lineSeparator = IOUtils.LINE_SEPARATOR_UNIX;
    private boolean newLineAfterDeclaration = true;
    private int newLineAfterNTags = 0;
    private boolean newlines = false;
    private boolean omitEncoding = false;
    private boolean padText = false;
    private boolean suppressDeclaration = false;
    private boolean trimText = false;

    public OutputFormat() {
    }

    public OutputFormat(String str) {
        this.indent = str;
    }

    public OutputFormat(String str, boolean z) {
        this.indent = str;
        this.newlines = z;
    }

    public OutputFormat(String str, boolean z, String str2) {
        this.indent = str;
        this.newlines = z;
        this.encoding = str2;
    }

    public String getLineSeparator() {
        return this.lineSeparator;
    }

    public void setLineSeparator(String str) {
        this.lineSeparator = str;
    }

    public boolean isNewlines() {
        return this.newlines;
    }

    public void setNewlines(boolean z) {
        this.newlines = z;
    }

    public String getEncoding() {
        return this.encoding;
    }

    public void setEncoding(String str) {
        if (str != null) {
            this.encoding = str;
        }
    }

    public boolean isOmitEncoding() {
        return this.omitEncoding;
    }

    public void setOmitEncoding(boolean z) {
        this.omitEncoding = z;
    }

    public void setSuppressDeclaration(boolean z) {
        this.suppressDeclaration = z;
    }

    public boolean isSuppressDeclaration() {
        return this.suppressDeclaration;
    }

    public void setNewLineAfterDeclaration(boolean z) {
        this.newLineAfterDeclaration = z;
    }

    public boolean isNewLineAfterDeclaration() {
        return this.newLineAfterDeclaration;
    }

    public boolean isExpandEmptyElements() {
        return this.expandEmptyElements;
    }

    public void setExpandEmptyElements(boolean z) {
        this.expandEmptyElements = z;
    }

    public boolean isTrimText() {
        return this.trimText;
    }

    public void setTrimText(boolean z) {
        this.trimText = z;
    }

    public boolean isPadText() {
        return this.padText;
    }

    public void setPadText(boolean z) {
        this.padText = z;
    }

    public String getIndent() {
        return this.indent;
    }

    public void setIndent(String str) {
        if (str != null && str.length() <= 0) {
            str = null;
        }
        this.indent = str;
    }

    public void setIndent(boolean z) {
        if (z) {
            this.indent = STANDARD_INDENT;
        } else {
            this.indent = null;
        }
    }

    public void setIndentSize(int i) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i2 = 0; i2 < i; i2++) {
            stringBuffer.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
        }
        this.indent = stringBuffer.toString();
    }

    public boolean isXHTML() {
        return this.doXHTML;
    }

    public void setXHTML(boolean z) {
        this.doXHTML = z;
    }

    public int getNewLineAfterNTags() {
        return this.newLineAfterNTags;
    }

    public void setNewLineAfterNTags(int i) {
        this.newLineAfterNTags = i;
    }

    public char getAttributeQuoteCharacter() {
        return this.attributeQuoteChar;
    }

    public void setAttributeQuoteCharacter(char c) {
        if (c == '\'' || c == '\"') {
            this.attributeQuoteChar = c;
            return;
        }
        StringBuffer stringBuffer = new StringBuffer("Invalid attribute quote character (");
        stringBuffer.append(c);
        stringBuffer.append(")");
        throw new IllegalArgumentException(stringBuffer.toString());
    }

    public int parseOptions(String[] strArr, int i) {
        int length = strArr.length;
        while (i < length) {
            if (strArr[i].equals("-suppressDeclaration")) {
                setSuppressDeclaration(true);
            } else if (strArr[i].equals("-omitEncoding")) {
                setOmitEncoding(true);
            } else if (strArr[i].equals("-indent")) {
                i++;
                setIndent(strArr[i]);
            } else if (strArr[i].equals("-indentSize")) {
                i++;
                setIndentSize(Integer.parseInt(strArr[i]));
            } else if (strArr[i].startsWith("-expandEmpty")) {
                setExpandEmptyElements(true);
            } else if (strArr[i].equals("-encoding")) {
                i++;
                setEncoding(strArr[i]);
            } else if (strArr[i].equals("-newlines")) {
                setNewlines(true);
            } else if (strArr[i].equals("-lineSeparator")) {
                i++;
                setLineSeparator(strArr[i]);
            } else if (strArr[i].equals("-trimText")) {
                setTrimText(true);
            } else if (strArr[i].equals("-padText")) {
                setPadText(true);
            } else if (!strArr[i].startsWith("-xhtml")) {
                return i;
            } else {
                setXHTML(true);
            }
            i++;
        }
        return i;
    }

    public static OutputFormat createPrettyPrint() {
        OutputFormat outputFormat = new OutputFormat();
        outputFormat.setIndentSize(2);
        outputFormat.setNewlines(true);
        outputFormat.setTrimText(true);
        outputFormat.setPadText(true);
        return outputFormat;
    }

    public static OutputFormat createCompactFormat() {
        OutputFormat outputFormat = new OutputFormat();
        outputFormat.setIndent(false);
        outputFormat.setNewlines(false);
        outputFormat.setTrimText(true);
        return outputFormat;
    }
}
