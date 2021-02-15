package com.webmd.wbmdcmepulse.models.utils.closecaptioning;

import com.webmd.wbmdcmepulse.models.utils.Extensions;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

public class CloseCaptionParser {
    public static ArrayList<CloseCaption> parseCloseCaptions(String str) {
        ArrayList<CloseCaption> arrayList = new ArrayList<>();
        if (!Extensions.isStringNullOrEmpty(str) && str.length() > 3) {
            String substring = str.substring(3);
            try {
                XmlPullParserFactory newInstance = XmlPullParserFactory.newInstance();
                newInstance.setNamespaceAware(true);
                XmlPullParser newPullParser = newInstance.newPullParser();
                newPullParser.setInput(new StringReader(substring));
                String str2 = "";
                CloseCaption closeCaption = null;
                for (int eventType = newPullParser.getEventType(); eventType != 1; eventType = newPullParser.next()) {
                    String name = newPullParser.getName();
                    if (eventType != 2) {
                        if (eventType != 3) {
                            if (eventType == 4) {
                                str2 = newPullParser.getText();
                            }
                        } else if (name.equalsIgnoreCase("p") && closeCaption != null) {
                            closeCaption.setCaptionString(str2);
                            arrayList.add(closeCaption);
                            closeCaption = null;
                        }
                    } else if (name.equalsIgnoreCase("p")) {
                        closeCaption = new CloseCaption();
                        closeCaption.setStartTime(parseTime(newPullParser.getAttributeValue((String) null, "begin")));
                        closeCaption.setEndTime(parseTime(newPullParser.getAttributeValue((String) null, "end")));
                    }
                }
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
        return arrayList;
    }

    private static long parseTime(String str) {
        return (Long.parseLong(str.split(":")[0].trim()) * 60 * 60 * 1000) + (Long.parseLong(str.split(":")[1].trim()) * 60 * 1000) + (Long.parseLong(str.split(":")[2].trim()) * 1000);
    }
}
