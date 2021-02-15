package org.xmlpull.mxp1;

import org.xmlpull.mxp1_serializer.MXSerializer;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
import org.xmlpull.v1.XmlSerializer;

public class MXParserFactory extends XmlPullParserFactory {
    protected static boolean stringCachedParserAvailable = true;

    /* JADX WARNING: Removed duplicated region for block: B:13:0x0022  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0010  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.xmlpull.v1.XmlPullParser newPullParser() throws org.xmlpull.v1.XmlPullParserException {
        /*
            r4 = this;
            boolean r0 = stringCachedParserAvailable
            if (r0 == 0) goto L_0x000d
            org.xmlpull.mxp1.MXParserCachingStrings r0 = new org.xmlpull.mxp1.MXParserCachingStrings     // Catch:{ Exception -> 0x000a }
            r0.<init>()     // Catch:{ Exception -> 0x000a }
            goto L_0x000e
        L_0x000a:
            r0 = 0
            stringCachedParserAvailable = r0
        L_0x000d:
            r0 = 0
        L_0x000e:
            if (r0 != 0) goto L_0x0015
            org.xmlpull.mxp1.MXParser r0 = new org.xmlpull.mxp1.MXParser
            r0.<init>()
        L_0x0015:
            java.util.Hashtable r1 = r4.features
            java.util.Enumeration r1 = r1.keys()
        L_0x001b:
            boolean r2 = r1.hasMoreElements()
            if (r2 != 0) goto L_0x0022
            return r0
        L_0x0022:
            java.lang.Object r2 = r1.nextElement()
            java.lang.String r2 = (java.lang.String) r2
            java.util.Hashtable r3 = r4.features
            java.lang.Object r3 = r3.get(r2)
            java.lang.Boolean r3 = (java.lang.Boolean) r3
            if (r3 == 0) goto L_0x001b
            boolean r3 = r3.booleanValue()
            if (r3 == 0) goto L_0x001b
            r3 = 1
            r0.setFeature(r2, r3)
            goto L_0x001b
        */
        throw new UnsupportedOperationException("Method not decompiled: org.xmlpull.mxp1.MXParserFactory.newPullParser():org.xmlpull.v1.XmlPullParser");
    }

    public XmlSerializer newSerializer() throws XmlPullParserException {
        return new MXSerializer();
    }
}
