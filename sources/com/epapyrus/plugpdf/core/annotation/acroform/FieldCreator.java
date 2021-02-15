package com.epapyrus.plugpdf.core.annotation.acroform;

import android.content.Context;
import com.epapyrus.plugpdf.core.PDFDocument;
import java.util.List;

public final class FieldCreator {
    public static BaseField create(Context context, PDFDocument pDFDocument, FieldProperty fieldProperty) {
        int type = fieldProperty.getType();
        if (type == 0) {
            return new TextField(context, pDFDocument, fieldProperty);
        }
        if (type == 1) {
            return new CheckBoxField(context, pDFDocument, fieldProperty);
        }
        if (type == 2) {
            return new RadioButtonField(context, pDFDocument, fieldProperty);
        }
        if (type == 3) {
            return new SignatureField(context, pDFDocument, fieldProperty);
        }
        if (type != 4) {
            return null;
        }
        return new ButtonField(context, pDFDocument, fieldProperty);
    }

    public static void grouping(List<BaseField> list) {
        for (BaseField next : list) {
            for (BaseField next2 : list) {
                if ((next instanceof RadioButtonField) && (next2 instanceof RadioButtonField)) {
                    RadioButtonField radioButtonField = (RadioButtonField) next;
                    if (radioButtonField.getGroupID().equals(((RadioButtonField) next2).getGroupID())) {
                        radioButtonField.addSibling(next2);
                    }
                } else if ((((next instanceof TextField) && (next2 instanceof TextField)) || ((next instanceof CheckBoxField) && (next2 instanceof CheckBoxField))) && next.getTitle().equals(next2.getTitle())) {
                    next.addSibling(next2);
                }
            }
        }
    }
}
