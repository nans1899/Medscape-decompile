package com.bea.xml.stream;

import com.bea.xml.stream.filters.NameFilter;
import com.bea.xml.stream.filters.TypeFilter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import javax.xml.namespace.QName;
import javax.xml.stream.StreamFilter;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

public class StreamReaderFilter extends ReaderDelegate {
    private StreamFilter filter;

    public StreamReaderFilter(XMLStreamReader xMLStreamReader) {
        super(xMLStreamReader);
    }

    public StreamReaderFilter(XMLStreamReader xMLStreamReader, StreamFilter streamFilter) {
        super(xMLStreamReader);
        setFilter(streamFilter);
    }

    public void setFilter(StreamFilter streamFilter) {
        this.filter = streamFilter;
    }

    public int next() throws XMLStreamException {
        if (hasNext()) {
            return super.next();
        }
        throw new IllegalStateException("next() may not be called  when there are no more  items to return");
    }

    public boolean hasNext() throws XMLStreamException {
        while (super.hasNext()) {
            if (this.filter.accept(getDelegate())) {
                return true;
            }
            super.next();
        }
        return false;
    }

    public static void main(String[] strArr) throws Exception {
        System.setProperty("javax.xml.stream.XMLInputFactory", "com.bea.xml.stream.MXParserFactory");
        XMLInputFactory newInstance = XMLInputFactory.newInstance();
        TypeFilter typeFilter = new TypeFilter();
        typeFilter.addType(1);
        typeFilter.addType(2);
        XMLStreamReader createFilteredReader = newInstance.createFilteredReader(newInstance.createXMLStreamReader((Reader) new FileReader(strArr[0])), (StreamFilter) typeFilter);
        while (createFilteredReader.hasNext()) {
            System.out.println(createFilteredReader.getLocalName());
            createFilteredReader.next();
        }
        XMLStreamReader createFilteredReader2 = newInstance.createFilteredReader(newInstance.createXMLStreamReader((Reader) new FileReader(strArr[0])), (StreamFilter) new NameFilter(new QName("banana", "B")));
        XMLStreamRecorder xMLStreamRecorder = new XMLStreamRecorder(new OutputStreamWriter(new FileOutputStream("out.stream")));
        while (createFilteredReader2.hasNext()) {
            xMLStreamRecorder.write(createFilteredReader2);
            createFilteredReader2.next();
        }
        xMLStreamRecorder.flush();
    }
}
