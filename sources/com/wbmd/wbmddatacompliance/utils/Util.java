package com.wbmd.wbmddatacompliance.utils;

import android.content.Context;
import java.io.InputStream;

public class Util {
    public static String readResource(int i, Context context) throws Exception {
        InputStream openRawResource = context.getResources().openRawResource(i);
        byte[] bArr = new byte[openRawResource.available()];
        openRawResource.read(bArr);
        return new String(bArr);
    }
}
