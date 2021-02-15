package com.bea.xml.stream;

import com.bea.xml.stream.util.ElementTypeNames;
import java.io.FileReader;
import java.io.PrintStream;
import java.io.Reader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

public class SubReader extends ReaderDelegate {
    private int depth = 0;
    private boolean open = true;

    public SubReader(XMLStreamReader xMLStreamReader) throws XMLStreamException {
        super(xMLStreamReader);
        if (xMLStreamReader.isStartElement()) {
            this.open = true;
            this.depth++;
            return;
        }
        throw new XMLStreamException("Unable to instantiate a subReader because the underlying reader was not on a start element.");
    }

    public int next() throws XMLStreamException {
        if (this.depth <= 0) {
            this.open = false;
        }
        int next = super.next();
        if (isStartElement()) {
            this.depth++;
        }
        if (isEndElement()) {
            this.depth--;
        }
        return next;
    }

    public int nextElement() throws XMLStreamException {
        next();
        while (hasNext() && !isStartElement() && !isEndElement()) {
            next();
        }
        return super.getEventType();
    }

    public boolean hasNext() throws XMLStreamException {
        if (!this.open) {
            return false;
        }
        return super.hasNext();
    }

    public boolean moveToStartElement() throws XMLStreamException {
        if (isStartElement()) {
            return true;
        }
        while (hasNext()) {
            if (isStartElement()) {
                return true;
            }
            next();
        }
        return false;
    }

    public boolean moveToStartElement(String str) throws XMLStreamException {
        if (str == null) {
            return false;
        }
        while (moveToStartElement()) {
            if (str.equals(getLocalName())) {
                return true;
            }
            if (!hasNext()) {
                return false;
            }
            next();
        }
        return false;
    }

    public boolean moveToStartElement(String str, String str2) throws XMLStreamException {
        if (!(str == null || str2 == null)) {
            while (moveToStartElement(str)) {
                if (str2.equals(getNamespaceURI())) {
                    return true;
                }
                if (!hasNext()) {
                    return false;
                }
                next();
            }
        }
        return false;
    }

    public boolean moveToEndElement() throws XMLStreamException {
        if (isEndElement()) {
            return true;
        }
        while (hasNext()) {
            if (isEndElement()) {
                return true;
            }
            next();
        }
        return false;
    }

    public boolean moveToEndElement(String str) throws XMLStreamException {
        if (str == null) {
            return false;
        }
        while (moveToEndElement()) {
            if (str.equals(getLocalName())) {
                return true;
            }
            if (!hasNext()) {
                return false;
            }
            next();
        }
        return false;
    }

    public boolean moveToEndElement(String str, String str2) throws XMLStreamException {
        if (!(str == null || str2 == null)) {
            while (moveToEndElement(str)) {
                if (str2.equals(getNamespaceURI())) {
                    return true;
                }
                if (!hasNext()) {
                    return false;
                }
                next();
            }
        }
        return false;
    }

    public static void print(XMLStreamReader xMLStreamReader, int i) throws XMLStreamException {
        PrintStream printStream = System.out;
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("[");
        stringBuffer.append(i);
        stringBuffer.append("]Sub: ");
        stringBuffer.append(ElementTypeNames.getEventTypeString(xMLStreamReader.getEventType()));
        printStream.print(stringBuffer.toString());
        if (xMLStreamReader.hasName()) {
            PrintStream printStream2 = System.out;
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append("->");
            stringBuffer2.append(xMLStreamReader.getLocalName());
            printStream2.println(stringBuffer2.toString());
        } else if (xMLStreamReader.hasText()) {
            PrintStream printStream3 = System.out;
            StringBuffer stringBuffer3 = new StringBuffer();
            stringBuffer3.append("->[");
            stringBuffer3.append(xMLStreamReader.getText());
            stringBuffer3.append("]");
            printStream3.println(stringBuffer3.toString());
        } else {
            System.out.println();
        }
    }

    public static void sub(XMLStreamReader xMLStreamReader, int i) throws Exception {
        while (xMLStreamReader.hasNext()) {
            print(xMLStreamReader, i);
            xMLStreamReader.next();
        }
    }

    public static void main(String[] strArr) throws Exception {
        MXParser mXParser = new MXParser();
        mXParser.setInput((Reader) new FileReader(strArr[0]));
        mXParser.moveToStartElement();
        mXParser.next();
        while (mXParser.moveToStartElement()) {
            PrintStream printStream = System.out;
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("SE->");
            stringBuffer.append(mXParser.getName());
            printStream.println(stringBuffer.toString());
            sub(mXParser.subReader(), 1);
        }
    }
}
