package com.wbmd.wbmddrugscommons.parsers;

import android.util.Xml;
import com.wbmd.wbmdcommons.extensions.StringExtensions;
import com.wbmd.wbmddrugscommons.constants.Constants;
import com.wbmd.wbmddrugscommons.exceptions.ContentParsingException;
import com.wbmd.wbmddrugscommons.model.Tug;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.HashSet;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class DrugsTTSParser {
    String mTTSResponse;

    public DrugsTTSParser(String str) {
        this.mTTSResponse = str;
    }

    public HashMap<String, Tug> parse() throws ContentParsingException {
        if (!isContentXML(this.mTTSResponse)) {
            return null;
        }
        if (this.mTTSResponse.indexOf("<?xml") > 1) {
            String str = this.mTTSResponse;
            this.mTTSResponse = str.substring(str.indexOf("<?xml"), this.mTTSResponse.length());
        }
        try {
            return parseXML(initializeXMLParser(this.mTTSResponse.trim()));
        } catch (XmlPullParserException e) {
            e.printStackTrace();
            throw new ContentParsingException("Error parsing TTS Data", e);
        } catch (IOException e2) {
            e2.printStackTrace();
            throw new ContentParsingException("Error parsing TTS Data", e2);
        }
    }

    private HashMap<String, Tug> parseXML(XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        if (xmlPullParser == null) {
            return null;
        }
        HashMap<String, Tug> hashMap = new HashMap<>();
        HashSet hashSet = new HashSet();
        int eventType = xmlPullParser.getEventType();
        Tug tug = null;
        while (eventType != 1) {
            if (eventType == 2 && xmlPullParser.getName().equalsIgnoreCase("tug")) {
                tug = new Tug();
                tug.setId(xmlPullParser.getAttributeValue((String) null, Constants.WBMDTugStringID));
                tug.setPackageType(xmlPullParser.getAttributeValue((String) null, Constants.WBMDTugStringPackageType));
                tug.setProgram(xmlPullParser.getAttributeValue((String) null, Constants.WBMDTugStringProgram));
                tug.setStartDate(xmlPullParser.getAttributeValue((String) null, Constants.WBMDTugStringStartDate));
                tug.setEndDate(xmlPullParser.getAttributeValue((String) null, Constants.WBMDTugStringEndDate));
                tug.setBrand(xmlPullParser.getAttributeValue((String) null, "brand"));
                tug.setClient(xmlPullParser.getAttributeValue((String) null, "client"));
            } else if (eventType == 2 && xmlPullParser.getName().equalsIgnoreCase("target")) {
                String str = xmlPullParser.getAttributeValue((String) null, Constants.WBMDTugStringAssetID).split("-")[0];
                if (hashSet.add(str)) {
                    if (tug != null) {
                        hashMap.put(str, tug);
                    }
                } else if (hashSet.contains(str)) {
                    Tug tug2 = hashMap.get(str);
                    if (tug2.getPackageType() != tug.getPackageType() || tug2.getProgram() != tug.getProgram() || tug2.getStartDate() != tug.getStartDate() || tug2.getEndDate() != tug.getEndDate()) {
                        hashMap.put(str, tug);
                    }
                }
            }
            eventType = xmlPullParser.next();
        }
        return hashMap;
    }

    private boolean isContentXML(String str) {
        if (!StringExtensions.isNullOrEmpty(str) && str.trim().length() != 0 && str.contains("<?xml")) {
            return true;
        }
        return false;
    }

    private XmlPullParser initializeXMLParser(String str) throws XmlPullParserException, IOException {
        XmlPullParser newPullParser = Xml.newPullParser();
        newPullParser.setFeature("http://xmlpull.org/v1/doc/features.html#process-namespaces", false);
        newPullParser.setInput(getInputStream(str), (String) null);
        newPullParser.nextTag();
        return newPullParser;
    }

    private InputStream getInputStream(String str) {
        return new ByteArrayInputStream(str.getBytes(Charset.forName("UTF-8")));
    }
}
