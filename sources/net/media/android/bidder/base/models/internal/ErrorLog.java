package net.media.android.bidder.base.models.internal;

import android.content.pm.PackageManager;
import android.location.Location;
import android.text.TextUtils;
import bolts.Bolts;
import java.io.PrintWriter;
import java.io.StringWriter;
import mnetinternal.c;
import mnetinternal.ck;
import mnetinternal.da;
import net.media.android.bidder.base.MNet;
import net.media.android.bidder.base.common.b;

public final class ErrorLog {
    @c(a = "device_info")
    private DeviceInfo mDeviceInfo;
    @c(a = "error")
    private String mError;
    @c(a = "internal_version_code")
    private int mInternalVersionCode;
    @c(a = "internal_version_name")
    private String mInternalVersionName;
    @c(a = "message")
    private String mMessage;
    @c(a = "package_name")
    private String mPackageName;
    @c(a = "release_stage")
    private String mReleaseStage;
    @c(a = "stacktrace")
    private String mStackTrace;
    @c(a = "tag")
    private String mTag;
    @c(a = "version_code")
    private int mVersionCode;
    @c(a = "version_name")
    private String mVersionName;

    public ErrorLog(String str, String str2) {
        this.mTag = str;
        this.mMessage = str2;
    }

    public ErrorLog(String str, String str2, Throwable th) {
        if (TextUtils.isEmpty(str)) {
            this.mTag = "unhandled_exception";
        } else {
            this.mTag = str;
        }
        this.mMessage = str2;
        this.mDeviceInfo = ck.a().a(b.a().e(), (Location) null);
        this.mPackageName = MNet.getContext().getPackageName();
        this.mVersionCode = da.b(MNet.getContext());
        this.mReleaseStage = "production";
        try {
            this.mVersionName = da.a(MNet.getContext());
        } catch (PackageManager.NameNotFoundException unused) {
        }
        this.mInternalVersionCode = 24;
        this.mInternalVersionName = Bolts.VERSION;
        if (th != null) {
            if (TextUtils.isEmpty(this.mMessage)) {
                this.mMessage = th.getMessage();
            }
            this.mError = th.getMessage();
            this.mStackTrace = getStackTraceString(th);
        }
    }

    private String getStackTraceString(Throwable th) {
        if (!(th == null || th.getStackTrace() == null)) {
            try {
                StringWriter stringWriter = new StringWriter();
                th.printStackTrace(new PrintWriter(stringWriter));
                return stringWriter.toString();
            } catch (Exception unused) {
            }
        }
        return "";
    }
}
