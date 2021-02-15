package com.appboy.ui.inappmessage;

import com.appboy.enums.inappmessage.CropType;

public interface IInAppMessageImageView {
    void setCornersRadiiPx(float f, float f2, float f3, float f4);

    void setCornersRadiusPx(float f);

    void setInAppMessageImageCropType(CropType cropType);
}
