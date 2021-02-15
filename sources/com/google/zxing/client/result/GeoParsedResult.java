package com.google.zxing.client.result;

import com.dd.plist.ASCIIPropertyListParser;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import net.bytebuddy.asm.Advice;

public final class GeoParsedResult extends ParsedResult {
    private final double altitude;
    private final double latitude;
    private final double longitude;
    private final String query;

    GeoParsedResult(double d, double d2, double d3, String str) {
        super(ParsedResultType.GEO);
        this.latitude = d;
        this.longitude = d2;
        this.altitude = d3;
        this.query = str;
    }

    public String getGeoURI() {
        StringBuilder sb = new StringBuilder();
        sb.append("geo:");
        sb.append(this.latitude);
        sb.append(ASCIIPropertyListParser.ARRAY_ITEM_DELIMITER_TOKEN);
        sb.append(this.longitude);
        if (this.altitude > FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
            sb.append(ASCIIPropertyListParser.ARRAY_ITEM_DELIMITER_TOKEN);
            sb.append(this.altitude);
        }
        if (this.query != null) {
            sb.append('?');
            sb.append(this.query);
        }
        return sb.toString();
    }

    public double getLatitude() {
        return this.latitude;
    }

    public double getLongitude() {
        return this.longitude;
    }

    public double getAltitude() {
        return this.altitude;
    }

    public String getQuery() {
        return this.query;
    }

    public String getDisplayResult() {
        StringBuilder sb = new StringBuilder(20);
        sb.append(this.latitude);
        sb.append(", ");
        sb.append(this.longitude);
        if (this.altitude > FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE) {
            sb.append(", ");
            sb.append(this.altitude);
            sb.append(Advice.OffsetMapping.ForOrigin.Renderer.ForMethodName.SYMBOL);
        }
        if (this.query != null) {
            sb.append(" (");
            sb.append(this.query);
            sb.append(ASCIIPropertyListParser.ARRAY_END_TOKEN);
        }
        return sb.toString();
    }
}
