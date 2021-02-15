package com.epapyrus.plugpdf.core.annotation.acroform;

public interface FieldEventListener {
    boolean onChangedValue(BaseField baseField);

    boolean onClick(BaseField baseField);

    boolean onFocusChange(BaseField baseField, boolean z);
}
