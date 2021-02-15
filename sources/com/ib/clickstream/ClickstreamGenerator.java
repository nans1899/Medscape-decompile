package com.ib.clickstream;

import com.dd.plist.ASCIIPropertyListParser;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.CharsKt;
import org.json.JSONObject;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000e\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0018\u001a\u00020\u0019J\u0006\u0010\u001a\u001a\u00020\u0004J\u001e\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001c2\u0006\u0010\u001e\u001a\u00020\n2\u0006\u0010\u001f\u001a\u00020\u0004J\b\u0010 \u001a\u00020\u0004H\u0007J\u0006\u0010!\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004XD¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006XD¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bXD¢\u0006\u0002\n\u0000R\u001a\u0010\t\u001a\u00020\nX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\u000b\"\u0004\b\f\u0010\rR\u001a\u0010\u000e\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0013\u001a\u00020\bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017¨\u0006\""}, d2 = {"Lcom/ib/clickstream/ClickstreamGenerator;", "", "()V", "ALLOWED_CHARACTERS", "", "DIGEST_LENGTH", "", "EXPIRATION", "", "isNewSession", "", "()Z", "setNewSession", "(Z)V", "sessionId", "getSessionId", "()Ljava/lang/String;", "setSessionId", "(Ljava/lang/String;)V", "sessionIdGenerationTimestamp", "getSessionIdGenerationTimestamp", "()J", "setSessionIdGenerationTimestamp", "(J)V", "generateSessionId", "", "generateUniqueId", "getEventJson", "Lorg/json/JSONObject;", "parameterObj", "isNewParty", "eventType", "getIsoTime", "getRandomString", "clickstream_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: ClickstreamGenerator.kt */
public final class ClickstreamGenerator {
    private final String ALLOWED_CHARACTERS = "0123456789qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM~_!";
    private final int DIGEST_LENGTH = 32;
    private final long EXPIRATION = 1800000;
    private boolean isNewSession;
    private String sessionId = "";
    private long sessionIdGenerationTimestamp;

    public final String getSessionId() {
        return this.sessionId;
    }

    public final void setSessionId(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.sessionId = str;
    }

    public final long getSessionIdGenerationTimestamp() {
        return this.sessionIdGenerationTimestamp;
    }

    public final void setSessionIdGenerationTimestamp(long j) {
        this.sessionIdGenerationTimestamp = j;
    }

    public final boolean isNewSession() {
        return this.isNewSession;
    }

    public final void setNewSession(boolean z) {
        this.isNewSession = z;
    }

    public final synchronized JSONObject getEventJson(JSONObject jSONObject, boolean z, String str) {
        JSONObject jSONObject2;
        Intrinsics.checkNotNullParameter(jSONObject, "parameterObj");
        Intrinsics.checkNotNullParameter(str, "eventType");
        jSONObject2 = new JSONObject();
        generateSessionId();
        jSONObject2.put("session_id", this.sessionId);
        jSONObject2.put("event_id", getRandomString());
        jSONObject2.put("is_new_party", z);
        jSONObject2.put("is_new_session", this.isNewSession);
        jSONObject2.put("client_timestamp_iso", getIsoTime());
        jSONObject2.put("event_type", str);
        jSONObject2.put("parameters", jSONObject);
        return jSONObject2;
    }

    public final String getRandomString() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder(this.DIGEST_LENGTH);
        int i = this.DIGEST_LENGTH;
        for (int i2 = 0; i2 < i; i2++) {
            String str = this.ALLOWED_CHARACTERS;
            sb.append(str.charAt(random.nextInt(str.length())));
        }
        String sb2 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(sb2, "sb.toString()");
        return sb2;
    }

    public final String generateUniqueId() {
        String l = Long.toString(new Date().getTime(), CharsKt.checkRadix(36));
        Intrinsics.checkNotNullExpressionValue(l, "java.lang.Long.toString(this, checkRadix(radix))");
        return "0:" + l + ASCIIPropertyListParser.DATE_TIME_FIELD_DELIMITER + getRandomString();
    }

    public final void generateSessionId() {
        long time = new Date().getTime();
        if (time - this.sessionIdGenerationTimestamp > this.EXPIRATION) {
            this.sessionIdGenerationTimestamp = time;
            this.isNewSession = true;
            this.sessionId = generateUniqueId();
            return;
        }
        this.isNewSession = false;
    }

    public final String getIsoTime() {
        String format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZZZZZ").format(new Date());
        Intrinsics.checkNotNullExpressionValue(format, "simpleDateFormat.format(Date())");
        return format;
    }
}
