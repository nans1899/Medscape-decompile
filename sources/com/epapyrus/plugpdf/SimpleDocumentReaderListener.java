package com.epapyrus.plugpdf;

import com.epapyrus.plugpdf.core.viewer.DocumentState;

public interface SimpleDocumentReaderListener {
    void onLoadFinish(DocumentState.OPEN open);
}
