package com.android.webmd.parser;

import com.android.webmd.model.Device;
import java.util.ArrayList;
import java.util.List;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class DeviceHandler extends DefaultHandler {
    static final String ITEM = "item";
    private StringBuilder builder;
    Device device = null;
    List<Device> deviceList = null;

    public void characters(char[] cArr, int i, int i2) throws SAXException {
        super.characters(cArr, i, i2);
        this.builder.append(cArr, i, i2);
    }

    public void endElement(String str, String str2, String str3) throws SAXException {
        super.endElement(str, str2, str3);
        if (str2.equalsIgnoreCase("item")) {
            this.device.setDeviceId(this.builder.toString());
            this.deviceList.add(this.device);
        }
        this.builder.setLength(0);
    }

    public void startDocument() throws SAXException {
        super.startDocument();
        this.deviceList = new ArrayList();
        this.builder = new StringBuilder();
    }

    public void startElement(String str, String str2, String str3, Attributes attributes) throws SAXException {
        super.startElement(str, str2, str3, attributes);
        if (str2.equalsIgnoreCase("item")) {
            this.device = new Device();
        }
    }

    public List<Device> getDevice() {
        return this.deviceList;
    }
}
