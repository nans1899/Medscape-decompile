package com.comscore.util.crashreport;

import com.comscore.Analytics;
import com.comscore.PublisherConfiguration;
import com.comscore.util.jni.JniComScoreHelper;
import com.comscore.util.setup.PlatformSetup;
import com.comscore.util.setup.Setup;
import com.facebook.appevents.AppEventsConstants;
import com.facebook.internal.ServerProtocol;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import net.media.android.bidder.base.models.internal.Logger;

public class CrashReportDecorator {
    private static final String d = "21193409";
    private static final String e = "9bfbb83ee80ccdee95e73bc93dacd62f";
    private JniComScoreHelper a;
    private long b;
    private String c = null;

    private class a {
        String a;
        boolean b;
        int c;
        int d;
        int e;

        a() {
        }

        a(String str) {
            String trim = str.trim();
            int indexOf = trim.indexOf(32);
            if (indexOf < 0) {
                this.b = false;
                return;
            }
            String substring = trim.substring(0, indexOf);
            this.a = substring;
            String replace = substring.replace(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR, "");
            this.a = replace;
            if (replace.length() == 0) {
                this.b = false;
                return;
            }
            String trim2 = trim.substring(indexOf + 1, trim.length()).trim();
            int indexOf2 = trim2.indexOf(32);
            if (indexOf2 < 0) {
                this.b = false;
                return;
            }
            String substring2 = trim2.substring(0, indexOf2);
            if (substring2.length() != 2) {
                this.b = false;
                return;
            }
            String substring3 = substring2.substring(0, 1);
            String substring4 = substring2.substring(1, 2);
            this.c = Integer.valueOf(substring3).intValue();
            this.d = Integer.valueOf(substring4).intValue();
            String trim3 = trim2.substring(2, trim2.length()).trim();
            int indexOf3 = trim3.indexOf(32);
            if (indexOf3 >= 0) {
                this.b = ServerProtocol.DIALOG_RETURN_SCOPES_TRUE.equals(trim3.substring(0, indexOf3));
                this.e = Integer.valueOf(trim3.substring(indexOf3 + 1, trim3.length()).trim()).intValue();
            }
        }
    }

    public CrashReportDecorator(JniComScoreHelper jniComScoreHelper) {
        this.a = jniComScoreHelper;
        this.b = System.currentTimeMillis();
    }

    private String a() {
        List<PublisherConfiguration> publisherConfigurations = Analytics.getConfiguration().getPublisherConfigurations();
        if (publisherConfigurations == null || publisherConfigurations.size() == 0) {
            return e;
        }
        int i = 0;
        PublisherConfiguration publisherConfiguration = publisherConfigurations.get(0);
        String[] deviceIds = this.a.getDeviceIds();
        a aVar = null;
        if (deviceIds != null) {
            int length = deviceIds.length;
            while (true) {
                if (i >= length) {
                    break;
                }
                a aVar2 = new a(deviceIds[i]);
                if (aVar2.b) {
                    aVar = aVar2;
                    break;
                }
                i++;
            }
        }
        if (aVar == null) {
            aVar = c();
        }
        String md5 = md5("zutphen2011comScore@" + publisherConfiguration.getPublisherId());
        String md52 = md5(aVar.a + md5);
        return md52 + "-cs" + aVar.c + aVar.d;
    }

    private String a(byte[] bArr) {
        Formatter formatter = new Formatter();
        int length = bArr.length;
        for (int i = 0; i < length; i++) {
            formatter.format("%02x", new Object[]{Byte.valueOf(bArr[i])});
        }
        String formatter2 = formatter.toString();
        formatter.close();
        return formatter2;
    }

    private String b() {
        List<PublisherConfiguration> publisherConfigurations = Analytics.getConfiguration().getPublisherConfigurations();
        return (publisherConfigurations == null || publisherConfigurations.size() == 0) ? d : publisherConfigurations.get(0).getPublisherId();
    }

    private a c() {
        if (this.c == null) {
            this.c = UUID.randomUUID().toString();
        }
        a aVar = new a();
        aVar.a = this.c;
        aVar.c = 7;
        aVar.c = 2;
        return aVar;
    }

    public void fillCrashReport(CrashReport crashReport) {
        Map<String, String> extras = crashReport.getExtras();
        extras.put("c1", "22");
        extras.put("c2", b());
        String applicationName = this.a.getApplicationName();
        if (applicationName == null) {
            applicationName = "";
        }
        extras.put("ns_ap_an", applicationName);
        String osName = this.a.getOsName();
        if (osName == null) {
            osName = "";
        }
        extras.put("ns_ap_pn", osName);
        String runtimeVersion = this.a.getRuntimeVersion();
        if (runtimeVersion == null) {
            runtimeVersion = "";
        }
        extras.put("ns_ap_pv", runtimeVersion);
        extras.put("c12", a());
        extras.put("name", "Application");
        extras.put("ns_ap_ev", Logger.LOG);
        String deviceModel = this.a.getDeviceModel();
        if (deviceModel == null) {
            deviceModel = "";
        }
        extras.put("ns_ap_device", deviceModel);
        extras.put("ns_ap_id", String.valueOf(this.b));
        String applicationId = this.a.getApplicationId();
        if (applicationId == null) {
            applicationId = "";
        }
        extras.put("ns_ap_bi", applicationId);
        String runtimeName = this.a.getRuntimeName();
        if (runtimeName == null) {
            runtimeName = "";
        }
        extras.put("ns_ap_pfm", runtimeName);
        String osVersion = this.a.getOsVersion();
        if (osVersion == null) {
            osVersion = "";
        }
        extras.put("ns_ap_pfv", osVersion);
        String applicationVersion = this.a.getApplicationVersion();
        if (applicationVersion == null) {
            applicationVersion = "";
        }
        extras.put("ns_ap_ver", applicationVersion);
        PlatformSetup platformSetup = Setup.getPlatformSetup();
        String javaCodeVersion = platformSetup == null ? "" : platformSetup.getJavaCodeVersion();
        if (javaCodeVersion == null) {
            javaCodeVersion = "";
        }
        extras.put("ns_ap_sv", javaCodeVersion);
        extras.put("ns_ap_bv", "");
        extras.put("ns_ap_smv", "");
        extras.put("ns_type", "hidden");
        extras.put("ns_nc", AppEventsConstants.EVENT_PARAM_VALUE_YES);
        extras.put("ns_ap_ar", this.a.getArchitecture());
        extras.put("ns_ap_cfg", "");
        extras.put("ns_ap_env", "");
        extras.put("ns_ap_ais", "");
        extras.put("ns_ap_i7", "");
    }

    /* access modifiers changed from: protected */
    public String md5(String str) {
        byte[] bytes = str.getBytes();
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.reset();
            instance.update(bytes);
            byte[] digest = instance.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b2 : digest) {
                String hexString = Integer.toHexString(b2 & 255);
                if (hexString.length() == 1) {
                    sb.append('0');
                }
                sb.append(hexString);
            }
            return sb + "";
        } catch (Exception unused) {
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public String sha1(String str) {
        try {
            MessageDigest instance = MessageDigest.getInstance("SHA-1");
            instance.reset();
            instance.update(str.getBytes("UTF-8"));
            return a(instance.digest());
        } catch (NoSuchAlgorithmException e2) {
            e2.printStackTrace();
            return "";
        } catch (UnsupportedEncodingException e3) {
            e3.printStackTrace();
            return "";
        }
    }
}
