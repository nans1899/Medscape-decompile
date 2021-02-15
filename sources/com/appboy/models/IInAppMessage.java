package com.appboy.models;

import android.graphics.Bitmap;
import android.net.Uri;
import com.appboy.enums.inappmessage.ClickAction;
import com.appboy.enums.inappmessage.CropType;
import com.appboy.enums.inappmessage.DismissType;
import com.appboy.enums.inappmessage.InAppMessageFailureType;
import com.appboy.enums.inappmessage.Orientation;
import com.appboy.enums.inappmessage.TextAlign;
import java.util.Map;
import org.json.JSONObject;

public interface IInAppMessage extends IPutIntoJson<JSONObject> {
    boolean getAnimateIn();

    boolean getAnimateOut();

    int getBackgroundColor();

    Bitmap getBitmap();

    ClickAction getClickAction();

    CropType getCropType();

    DismissType getDismissType();

    int getDurationInMilliseconds();

    long getExpirationTimestamp();

    Map<String, String> getExtras();

    String getIcon();

    int getIconBackgroundColor();

    int getIconColor();

    boolean getImageDownloadSuccessful();

    String getImageUrl();

    String getLocalImageUrl();

    String getMessage();

    TextAlign getMessageTextAlign();

    int getMessageTextColor();

    boolean getOpenUriInWebView();

    Orientation getOrientation();

    String getRemoteAssetPathForPrefetch();

    String getRemoteImageUrl();

    Uri getUri();

    boolean isControl();

    boolean logClick();

    boolean logDisplayFailure(InAppMessageFailureType inAppMessageFailureType);

    boolean logImpression();

    void onAfterClosed();

    void setAnimateIn(boolean z);

    void setAnimateOut(boolean z);

    void setBackgroundColor(int i);

    void setBitmap(Bitmap bitmap);

    boolean setClickAction(ClickAction clickAction);

    boolean setClickAction(ClickAction clickAction, Uri uri);

    void setCropType(CropType cropType);

    void setDismissType(DismissType dismissType);

    void setDurationInMilliseconds(int i);

    void setExpirationTimestamp(long j);

    void setIcon(String str);

    void setIconBackgroundColor(int i);

    void setIconColor(int i);

    void setImageDownloadSuccessful(boolean z);

    void setImageUrl(String str);

    void setLocalAssetPathForPrefetch(String str);

    void setLocalImageUrl(String str);

    void setMessage(String str);

    void setMessageTextAlign(TextAlign textAlign);

    void setMessageTextColor(int i);

    void setOpenUriInWebView(boolean z);

    void setOrientation(Orientation orientation);

    void setRemoteImageUrl(String str);
}
