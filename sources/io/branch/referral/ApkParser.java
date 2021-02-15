package io.branch.referral;

import com.facebook.appevents.AppEventsConstants;
import com.google.common.base.Ascii;
import com.tapstream.sdk.http.RequestBuilders;
import io.branch.referral.Defines;
import net.media.android.bidder.base.common.Constants;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class ApkParser {
    private static final int endDocTag = 1048833;
    private static final int endTag = 1048835;
    private static final int startTag = 1048834;

    ApkParser() {
    }

    public JSONObject decompressXMLForValidator(byte[] bArr) {
        int i;
        int i2;
        JSONObject jSONObject;
        String str;
        String str2;
        String str3;
        byte[] bArr2 = bArr;
        JSONObject jSONObject2 = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        JSONObject jSONObject3 = new JSONObject();
        int i3 = 36;
        int LEW = (LEW(bArr2, 16) * 4) + 36;
        int LEW2 = LEW(bArr2, 12);
        int i4 = LEW2;
        while (true) {
            int length = bArr2.length - 4;
            i = startTag;
            if (i4 >= length) {
                break;
            } else if (LEW(bArr2, i4) == startTag) {
                LEW2 = i4;
                break;
            } else {
                i4 += 4;
            }
        }
        String str4 = "";
        while (true) {
            try {
                if (i2 >= bArr2.length) {
                    jSONObject = jSONObject3;
                    break;
                }
                int LEW3 = LEW(bArr2, i2);
                if (LEW3 != i) {
                    jSONObject = jSONObject3;
                    if (LEW3 != endTag) {
                        break;
                    }
                    i2 += 24;
                } else {
                    int LEW4 = LEW(bArr2, i2 + 28);
                    i2 += 36;
                    int i5 = 0;
                    while (i5 < LEW4) {
                        int LEW5 = LEW(bArr2, i2 + 4);
                        int LEW6 = LEW(bArr2, i2 + 8);
                        int LEW7 = LEW(bArr2, i2 + 16);
                        int i6 = i2 + 20;
                        String compXmlString = compXmlString(bArr2, i3, LEW, LEW5);
                        int i7 = i6;
                        int i8 = LEW4;
                        JSONObject jSONObject4 = jSONObject3;
                        if ("scheme".equals(compXmlString)) {
                            if (LEW6 != -1) {
                                try {
                                    str3 = compXmlString(bArr2, 36, LEW, LEW6);
                                } catch (JSONException e) {
                                    e = e;
                                    jSONObject3 = jSONObject4;
                                    e.printStackTrace();
                                    return jSONObject3;
                                }
                            } else {
                                str3 = "resourceID 0x" + Integer.toHexString(LEW7);
                            }
                            if (validURI(str3)) {
                                if (!jSONObject2.has(str3)) {
                                    jSONObject2.put(str3, new JSONArray());
                                } else if (jSONObject2.has(AppEventsConstants.EVENT_PARAM_VALUE_NO)) {
                                    jSONObject2.put(str3, (JSONArray) jSONObject2.get(AppEventsConstants.EVENT_PARAM_VALUE_NO));
                                    jSONObject2.remove(AppEventsConstants.EVENT_PARAM_VALUE_NO);
                                }
                                str4 = str3;
                            }
                            if ("http".equals(str3) || RequestBuilders.DEFAULT_SCHEME.equals(str3)) {
                                str4 = str3;
                            }
                        } else if ("host".equals(compXmlString)) {
                            if (LEW6 != -1) {
                                str2 = compXmlString(bArr2, 36, LEW, LEW6);
                            } else {
                                str2 = "resourceID 0x" + Integer.toHexString(LEW7);
                            }
                            if (!jSONObject2.has(str4) || str4 == null || RequestBuilders.DEFAULT_SCHEME.equals(str4) || "http".equals(str4)) {
                                if (!RequestBuilders.DEFAULT_SCHEME.equals(str4)) {
                                    if (!"http".equals(str4)) {
                                        JSONArray jSONArray2 = new JSONArray();
                                        jSONArray2.put(str2);
                                        jSONObject2.put(AppEventsConstants.EVENT_PARAM_VALUE_NO, jSONArray2);
                                    }
                                }
                                jSONArray.put(str2);
                            } else {
                                JSONArray jSONArray3 = jSONObject2.getJSONArray(str4);
                                jSONArray3.put(str2);
                                jSONObject2.put(str4, jSONArray3);
                            }
                        } else if ("name".equals(compXmlString)) {
                            if (LEW6 != -1) {
                                str = compXmlString(bArr2, 36, LEW, LEW6);
                            } else {
                                str = "resourceID 0x" + Integer.toHexString(LEW7);
                            }
                            if ("android.intent.action.VIEW".equals(str)) {
                                str4 = null;
                            }
                            i5++;
                            i2 = i7;
                            LEW4 = i8;
                            jSONObject3 = jSONObject4;
                            i3 = 36;
                        }
                        i5++;
                        i2 = i7;
                        LEW4 = i8;
                        jSONObject3 = jSONObject4;
                        i3 = 36;
                    }
                    jSONObject = jSONObject3;
                }
                jSONObject3 = jSONObject;
                i3 = 36;
                i = startTag;
            } catch (JSONException e2) {
                e = e2;
            }
        }
        jSONObject2.remove(AppEventsConstants.EVENT_PARAM_VALUE_NO);
        jSONObject3 = jSONObject;
        jSONObject3.put(Defines.Jsonkey.URIScheme.getKey(), jSONObject2);
        jSONObject3.put(Defines.Jsonkey.AppLinks.getKey(), jSONArray);
        return jSONObject3;
    }

    private boolean validURI(String str) {
        return str != null && !"http".equals(str) && !str.equals(RequestBuilders.DEFAULT_SCHEME) && !str.equals("geo") && !str.equals("*") && !str.equals(Constants.APP_PACKAGE) && !str.equals("sms") && !str.equals("smsto") && !str.equals("mms") && !str.equals("mmsto") && !str.equals("tel") && !str.equals("voicemail") && !str.equals("file") && !str.equals("content") && !str.equals("mailto");
    }

    private String compXmlString(byte[] bArr, int i, int i2, int i3) {
        if (i3 < 0) {
            return null;
        }
        return compXmlStringAt(bArr, i2 + LEW(bArr, i + (i3 * 4)));
    }

    private String compXmlStringAt(byte[] bArr, int i) {
        int i2 = ((bArr[i + 1] << 8) & 65280) | (bArr[i] & 255);
        byte[] bArr2 = new byte[i2];
        for (int i3 = 0; i3 < i2; i3++) {
            bArr2[i3] = bArr[i + 2 + (i3 * 2)];
        }
        return new String(bArr2);
    }

    private int LEW(byte[] bArr, int i) {
        return (bArr[i] & 255) | ((bArr[i + 3] << Ascii.CAN) & -16777216) | ((bArr[i + 2] << Ascii.DLE) & 16711680) | ((bArr[i + 1] << 8) & 65280);
    }
}
