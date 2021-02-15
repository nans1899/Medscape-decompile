package com.epapyrus.plugpdf;

import android.app.Activity;

public class SimpleReaderFactory {
    public static SimpleDocumentReader createSimpleViewer(Activity activity, SimpleDocumentReaderListener simpleDocumentReaderListener) {
        SimpleDocumentReader simpleDocumentReader = new SimpleDocumentReader(activity);
        simpleDocumentReader.setListener(simpleDocumentReaderListener);
        return simpleDocumentReader;
    }
}
