package net.media.android.bidder.base.models.internal;

import com.dd.plist.ASCIIPropertyListParser;
import com.mnet.gson.e;
import com.mnet.gson.internal.bind.i;
import com.mnet.gson.v;
import com.wbmd.adlibrary.constants.AdParameterKeys;
import mnetinternal.c;
import mnetinternal.da;
import mnetinternal.g;
import mnetinternal.h;
import mnetinternal.j;
import mnetinternal.p;
import net.bytebuddy.implementation.auxiliary.TypeProxy;
import net.media.android.bidder.base.models.internal.GeoLocation;

public final class DeviceInfo {
    public static final int DEVICE_TYPE_PHONE = 4;
    public static final int DEVICE_TYPE_PHONE_OR_TABLET = 1;
    public static final int DEVICE_TYPE_TABLET = 5;
    @c(a = "ifa")
    protected String mAdvertId;
    @c(a = "carrier")
    protected String mCarrier;
    @c(a = "connectiontype")
    protected int mConnectionType;
    @c(a = "mccmnc")
    protected String mCountryCode;
    @c(a = "language")
    protected String mDeviceLang;
    @c(a = "model")
    protected String mDeviceModel;
    @c(a = "devicetype")
    protected int mDeviceType = 1;
    @c(a = "h")
    protected int mDisplayHeight;
    @c(a = "w")
    protected int mDisplayWidth;
    @c(a = "dnt")
    protected int mDnt;
    @c(a = "geo")
    protected GeoLocation mGeoLocation;
    @c(a = "hwv")
    protected String mHardwareVersion;
    @c(a = "ip")
    protected String mIPv4Address;
    @c(a = "ipv6")
    protected String mIPv6Address;
    @c(a = "emu")
    protected boolean mIsEmulator;
    @c(a = "lmt")
    protected int mIsLimitedAdTracking = 0;
    @c(a = "rooted")
    protected boolean mIsRooted;
    @c(a = "js")
    protected int mJavaScriptSupport = 1;
    @c(a = "geofetch")
    protected int mLocationAllowed;
    @c(a = "make")
    protected String mManufacturer;
    @c(a = "os")
    protected String mOs;
    @c(a = "os_api_level")
    protected int mOsApiLevel;
    @c(a = "os_build")
    protected String mOsBuild;
    @c(a = "osv")
    protected String mOsVersion;
    @c(a = "ppi")
    protected int mPixelDensity;
    @c(a = "pxratio")
    protected float mPixelRatio;
    @c(a = "program_memory")
    protected long mProgramMemory;
    @c(a = "ua")
    protected String mUserAgent;

    public final class TypeAdapter extends v<DeviceInfo> {
        public static final g<DeviceInfo> TYPE_TOKEN = g.b(DeviceInfo.class);
        private final e mGson;
        private final v<GeoLocation> mTypeAdapter0;

        public TypeAdapter(e eVar) {
            this.mGson = eVar;
            this.mTypeAdapter0 = eVar.a(GeoLocation.TypeAdapter.TYPE_TOKEN);
        }

        public void write(j jVar, DeviceInfo deviceInfo) {
            if (deviceInfo == null) {
                jVar.f();
                return;
            }
            jVar.d();
            jVar.a("dnt");
            jVar.a((long) deviceInfo.mDnt);
            jVar.a("ua");
            if (deviceInfo.mUserAgent != null) {
                i.A.write(jVar, deviceInfo.mUserAgent);
            } else {
                jVar.f();
            }
            jVar.a("ip");
            if (deviceInfo.mIPv4Address != null) {
                i.A.write(jVar, deviceInfo.mIPv4Address);
            } else {
                jVar.f();
            }
            jVar.a("ipv6");
            if (deviceInfo.mIPv6Address != null) {
                i.A.write(jVar, deviceInfo.mIPv6Address);
            } else {
                jVar.f();
            }
            jVar.a("hwv");
            if (deviceInfo.mHardwareVersion != null) {
                i.A.write(jVar, deviceInfo.mHardwareVersion);
            } else {
                jVar.f();
            }
            jVar.a("h");
            jVar.a((long) deviceInfo.mDisplayHeight);
            jVar.a("w");
            jVar.a((long) deviceInfo.mDisplayWidth);
            jVar.a("ppi");
            jVar.a((long) deviceInfo.mPixelDensity);
            jVar.a("pxratio");
            jVar.a((double) deviceInfo.mPixelRatio);
            jVar.a("mccmnc");
            if (deviceInfo.mCountryCode != null) {
                i.A.write(jVar, deviceInfo.mCountryCode);
            } else {
                jVar.f();
            }
            jVar.a("js");
            jVar.a((long) deviceInfo.mJavaScriptSupport);
            jVar.a("geofetch");
            jVar.a((long) deviceInfo.mLocationAllowed);
            jVar.a("ifa");
            if (deviceInfo.mAdvertId != null) {
                i.A.write(jVar, deviceInfo.mAdvertId);
            } else {
                jVar.f();
            }
            jVar.a("carrier");
            if (deviceInfo.mCarrier != null) {
                i.A.write(jVar, deviceInfo.mCarrier);
            } else {
                jVar.f();
            }
            jVar.a("language");
            if (deviceInfo.mDeviceLang != null) {
                i.A.write(jVar, deviceInfo.mDeviceLang);
            } else {
                jVar.f();
            }
            jVar.a(TypeProxy.REFLECTION_METHOD);
            if (deviceInfo.mManufacturer != null) {
                i.A.write(jVar, deviceInfo.mManufacturer);
            } else {
                jVar.f();
            }
            jVar.a("model");
            if (deviceInfo.mDeviceModel != null) {
                i.A.write(jVar, deviceInfo.mDeviceModel);
            } else {
                jVar.f();
            }
            jVar.a(AdParameterKeys.OS);
            if (deviceInfo.mOs != null) {
                i.A.write(jVar, deviceInfo.mOs);
            } else {
                jVar.f();
            }
            jVar.a("osv");
            if (deviceInfo.mOsVersion != null) {
                i.A.write(jVar, deviceInfo.mOsVersion);
            } else {
                jVar.f();
            }
            jVar.a("os_build");
            if (deviceInfo.mOsBuild != null) {
                i.A.write(jVar, deviceInfo.mOsBuild);
            } else {
                jVar.f();
            }
            jVar.a("os_api_level");
            jVar.a((long) deviceInfo.mOsApiLevel);
            jVar.a("rooted");
            jVar.a(deviceInfo.mIsRooted);
            jVar.a("program_memory");
            jVar.a(deviceInfo.mProgramMemory);
            jVar.a("connectiontype");
            jVar.a((long) deviceInfo.mConnectionType);
            jVar.a("devicetype");
            jVar.a((long) deviceInfo.mDeviceType);
            jVar.a("geo");
            if (deviceInfo.mGeoLocation != null) {
                this.mTypeAdapter0.write(jVar, deviceInfo.mGeoLocation);
            } else {
                jVar.f();
            }
            jVar.a("emu");
            jVar.a(deviceInfo.mIsEmulator);
            jVar.a("lmt");
            jVar.a((long) deviceInfo.mIsLimitedAdTracking);
            jVar.e();
        }

        public DeviceInfo read(h hVar) {
            mnetinternal.i f = hVar.f();
            if (mnetinternal.i.NULL == f) {
                hVar.j();
                return null;
            } else if (mnetinternal.i.BEGIN_OBJECT != f) {
                hVar.n();
                return null;
            } else {
                hVar.c();
                DeviceInfo deviceInfo = new DeviceInfo();
                while (hVar.e()) {
                    String g = hVar.g();
                    char c = 65535;
                    switch (g.hashCode()) {
                        case -1613589672:
                            if (g.equals("language")) {
                                c = 14;
                                break;
                            }
                            break;
                        case -1079903691:
                            if (g.equals("mccmnc")) {
                                c = 9;
                                break;
                            }
                            break;
                        case -925311743:
                            if (g.equals("rooted")) {
                                c = 21;
                                break;
                            }
                            break;
                        case -668926180:
                            if (g.equals("program_memory")) {
                                c = 22;
                                break;
                            }
                            break;
                        case -618530684:
                            if (g.equals("os_api_level")) {
                                c = 20;
                                break;
                            }
                            break;
                        case -135018749:
                            if (g.equals("pxratio")) {
                                c = 8;
                                break;
                            }
                            break;
                        case 104:
                            if (g.equals("h")) {
                                c = 5;
                                break;
                            }
                            break;
                        case 119:
                            if (g.equals("w")) {
                                c = 6;
                                break;
                            }
                            break;
                        case 3367:
                            if (g.equals("ip")) {
                                c = 2;
                                break;
                            }
                            break;
                        case 3401:
                            if (g.equals("js")) {
                                c = 10;
                                break;
                            }
                            break;
                        case 3556:
                            if (g.equals(AdParameterKeys.OS)) {
                                c = 17;
                                break;
                            }
                            break;
                        case 3724:
                            if (g.equals("ua")) {
                                c = 1;
                                break;
                            }
                            break;
                        case 99626:
                            if (g.equals("dnt")) {
                                c = 0;
                                break;
                            }
                            break;
                        case 100557:
                            if (g.equals("emu")) {
                                c = 26;
                                break;
                            }
                            break;
                        case 102225:
                            if (g.equals("geo")) {
                                c = 25;
                                break;
                            }
                            break;
                        case 103751:
                            if (g.equals("hwv")) {
                                c = 4;
                                break;
                            }
                            break;
                        case 104164:
                            if (g.equals("ifa")) {
                                c = 12;
                                break;
                            }
                            break;
                        case 107283:
                            if (g.equals("lmt")) {
                                c = 27;
                                break;
                            }
                            break;
                        case 110354:
                            if (g.equals("osv")) {
                                c = 18;
                                break;
                            }
                            break;
                        case 111209:
                            if (g.equals("ppi")) {
                                c = 7;
                                break;
                            }
                            break;
                        case 3239399:
                            if (g.equals("ipv6")) {
                                c = 3;
                                break;
                            }
                            break;
                        case 3343854:
                            if (g.equals(TypeProxy.REFLECTION_METHOD)) {
                                c = 15;
                                break;
                            }
                            break;
                        case 104069929:
                            if (g.equals("model")) {
                                c = 16;
                                break;
                            }
                            break;
                        case 554360568:
                            if (g.equals("carrier")) {
                                c = ASCIIPropertyListParser.WHITESPACE_CARRIAGE_RETURN;
                                break;
                            }
                            break;
                        case 782144144:
                            if (g.equals("devicetype")) {
                                c = 24;
                                break;
                            }
                            break;
                        case 1271164696:
                            if (g.equals("connectiontype")) {
                                c = 23;
                                break;
                            }
                            break;
                        case 1839555081:
                            if (g.equals("geofetch")) {
                                c = 11;
                                break;
                            }
                            break;
                        case 1975991379:
                            if (g.equals("os_build")) {
                                c = 19;
                                break;
                            }
                            break;
                    }
                    switch (c) {
                        case 0:
                            deviceInfo.mDnt = p.j.a(hVar, deviceInfo.mDnt);
                            break;
                        case 1:
                            deviceInfo.mUserAgent = i.A.read(hVar);
                            break;
                        case 2:
                            deviceInfo.mIPv4Address = i.A.read(hVar);
                            break;
                        case 3:
                            deviceInfo.mIPv6Address = i.A.read(hVar);
                            break;
                        case 4:
                            deviceInfo.mHardwareVersion = i.A.read(hVar);
                            break;
                        case 5:
                            deviceInfo.mDisplayHeight = p.j.a(hVar, deviceInfo.mDisplayHeight);
                            break;
                        case 6:
                            deviceInfo.mDisplayWidth = p.j.a(hVar, deviceInfo.mDisplayWidth);
                            break;
                        case 7:
                            deviceInfo.mPixelDensity = p.j.a(hVar, deviceInfo.mPixelDensity);
                            break;
                        case 8:
                            deviceInfo.mPixelRatio = p.i.a(hVar, deviceInfo.mPixelRatio);
                            break;
                        case 9:
                            deviceInfo.mCountryCode = i.A.read(hVar);
                            break;
                        case 10:
                            deviceInfo.mJavaScriptSupport = p.j.a(hVar, deviceInfo.mJavaScriptSupport);
                            break;
                        case 11:
                            deviceInfo.mLocationAllowed = p.j.a(hVar, deviceInfo.mLocationAllowed);
                            break;
                        case 12:
                            deviceInfo.mAdvertId = i.A.read(hVar);
                            break;
                        case 13:
                            deviceInfo.mCarrier = i.A.read(hVar);
                            break;
                        case 14:
                            deviceInfo.mDeviceLang = i.A.read(hVar);
                            break;
                        case 15:
                            deviceInfo.mManufacturer = i.A.read(hVar);
                            break;
                        case 16:
                            deviceInfo.mDeviceModel = i.A.read(hVar);
                            break;
                        case 17:
                            deviceInfo.mOs = i.A.read(hVar);
                            break;
                        case 18:
                            deviceInfo.mOsVersion = i.A.read(hVar);
                            break;
                        case 19:
                            deviceInfo.mOsBuild = i.A.read(hVar);
                            break;
                        case 20:
                            deviceInfo.mOsApiLevel = p.j.a(hVar, deviceInfo.mOsApiLevel);
                            break;
                        case 21:
                            deviceInfo.mIsRooted = p.g.a(hVar, deviceInfo.mIsRooted);
                            break;
                        case 22:
                            deviceInfo.mProgramMemory = p.k.a(hVar, deviceInfo.mProgramMemory);
                            break;
                        case 23:
                            deviceInfo.mConnectionType = p.j.a(hVar, deviceInfo.mConnectionType);
                            break;
                        case 24:
                            deviceInfo.mDeviceType = p.j.a(hVar, deviceInfo.mDeviceType);
                            break;
                        case 25:
                            deviceInfo.mGeoLocation = this.mTypeAdapter0.read(hVar);
                            break;
                        case 26:
                            deviceInfo.mIsEmulator = p.g.a(hVar, deviceInfo.mIsEmulator);
                            break;
                        case 27:
                            deviceInfo.mIsLimitedAdTracking = p.j.a(hVar, deviceInfo.mIsLimitedAdTracking);
                            break;
                        default:
                            hVar.n();
                            break;
                    }
                }
                hVar.d();
                return deviceInfo;
            }
        }
    }

    protected DeviceInfo() {
    }

    /* access modifiers changed from: private */
    public void setUserAgent(String str) {
        this.mUserAgent = str;
    }

    /* access modifiers changed from: private */
    public void setIPv4Address(String str) {
        this.mIPv4Address = str;
    }

    /* access modifiers changed from: private */
    public void setIPv6Address(String str) {
        this.mIPv6Address = str;
    }

    /* access modifiers changed from: private */
    public void setHardwareVersion(String str) {
        this.mHardwareVersion = str;
    }

    /* access modifiers changed from: private */
    public void setDisplayHeight(int i) {
        this.mDisplayHeight = i;
    }

    /* access modifiers changed from: private */
    public void setDisplayWidth(int i) {
        this.mDisplayWidth = i;
    }

    /* access modifiers changed from: private */
    public void setPixelDensity(int i) {
        this.mPixelDensity = i;
    }

    /* access modifiers changed from: private */
    public void setPixelRatio(float f) {
        this.mPixelRatio = f;
    }

    /* access modifiers changed from: private */
    public void setCountryCode(String str) {
        this.mCountryCode = str;
    }

    /* access modifiers changed from: private */
    public void isLocationAllowed(boolean z) {
        if (z) {
            this.mLocationAllowed = 1;
        } else {
            this.mLocationAllowed = 0;
        }
    }

    /* access modifiers changed from: private */
    public void setAdvertId(String str) {
        this.mAdvertId = str;
    }

    /* access modifiers changed from: private */
    public void setCarrier(String str) {
        this.mCarrier = str;
    }

    /* access modifiers changed from: private */
    public void setDeviceLanguage(String str) {
        this.mDeviceLang = str;
    }

    /* access modifiers changed from: private */
    public void setManufacturer(String str) {
        this.mManufacturer = str;
    }

    /* access modifiers changed from: private */
    public void setDeviceModel(String str) {
        this.mDeviceModel = str;
    }

    /* access modifiers changed from: private */
    public void setOs(String str) {
        this.mOs = str;
    }

    /* access modifiers changed from: private */
    public void setOsBuild(String str) {
        this.mOsBuild = str;
    }

    /* access modifiers changed from: private */
    public void setOsApiLevel(int i) {
        this.mOsApiLevel = i;
    }

    /* access modifiers changed from: private */
    public void setIsRooted(boolean z) {
        this.mIsRooted = z;
    }

    /* access modifiers changed from: private */
    public void setProgramMemory(long j) {
        this.mProgramMemory = j;
    }

    /* access modifiers changed from: private */
    public void setOsVersion(String str) {
        this.mOsVersion = str;
    }

    /* access modifiers changed from: private */
    public void setConnectionType(int i) {
        this.mConnectionType = i;
    }

    /* access modifiers changed from: private */
    public void setLocation(GeoLocation geoLocation) {
        this.mGeoLocation = geoLocation;
    }

    /* access modifiers changed from: private */
    public void setIsEmulator(Boolean bool) {
        this.mIsEmulator = bool.booleanValue();
    }

    /* access modifiers changed from: private */
    public void setDeviceType(int i) {
        this.mDeviceType = i;
    }

    /* access modifiers changed from: private */
    public void setIsLimitedAdTracking(boolean z) {
        if (z) {
            this.mIsLimitedAdTracking = 1;
        } else {
            this.mIsLimitedAdTracking = 0;
        }
    }

    public static class Builder {
        private boolean mDataPrivacy;
        private DeviceInfo mDeviceInfo = new DeviceInfo();

        public Builder(Boolean bool) {
            this.mDataPrivacy = bool.booleanValue();
        }

        public Builder setUserAgent(String str) {
            this.mDeviceInfo.setUserAgent(str);
            return this;
        }

        public Builder setIPv4Address(String str) {
            if (this.mDataPrivacy) {
                str = da.c(str);
            }
            this.mDeviceInfo.setIPv4Address(str);
            return this;
        }

        public Builder setIPv6Address(String str) {
            if (this.mDataPrivacy) {
                return this;
            }
            this.mDeviceInfo.setIPv6Address(str);
            return this;
        }

        public Builder setHardwareVersion(String str) {
            if (this.mDataPrivacy) {
                return this;
            }
            this.mDeviceInfo.setHardwareVersion(str);
            return this;
        }

        public Builder setDisplayHeight(int i) {
            this.mDeviceInfo.setDisplayHeight(i);
            return this;
        }

        public Builder setDisplayWidth(int i) {
            this.mDeviceInfo.setDisplayWidth(i);
            return this;
        }

        public Builder setPixelDensity(int i) {
            this.mDeviceInfo.setPixelDensity(i);
            return this;
        }

        public Builder setPixelRatio(float f) {
            this.mDeviceInfo.setPixelRatio(f);
            return this;
        }

        public Builder setCountryCode(String str) {
            if (this.mDataPrivacy) {
                return this;
            }
            this.mDeviceInfo.setCountryCode(str);
            return this;
        }

        public Builder isLocationAllowed(boolean z) {
            this.mDeviceInfo.isLocationAllowed(z);
            return this;
        }

        public Builder setAdvertId(String str) {
            if (this.mDataPrivacy) {
                return this;
            }
            this.mDeviceInfo.setAdvertId(str);
            return this;
        }

        public Builder setCarrier(String str) {
            if (this.mDataPrivacy) {
                return this;
            }
            this.mDeviceInfo.setCarrier(str);
            return this;
        }

        public Builder setDeviceLanguage(String str) {
            if (this.mDataPrivacy) {
                return this;
            }
            this.mDeviceInfo.setDeviceLanguage(str);
            return this;
        }

        public Builder setManufacturer(String str) {
            this.mDeviceInfo.setManufacturer(str);
            return this;
        }

        public Builder setDeviceModel(String str) {
            this.mDeviceInfo.setDeviceModel(str);
            return this;
        }

        public Builder setOs(String str) {
            this.mDeviceInfo.setOs(str);
            return this;
        }

        public Builder setOsBuild(String str) {
            this.mDeviceInfo.setOsBuild(str);
            return this;
        }

        public Builder setOsApiLevel(int i) {
            this.mDeviceInfo.setOsApiLevel(i);
            return this;
        }

        public Builder setIsRooted(boolean z) {
            this.mDeviceInfo.setIsRooted(z);
            return this;
        }

        public Builder setProgramMemory(long j) {
            this.mDeviceInfo.setProgramMemory(j);
            return this;
        }

        public Builder setOsVersion(String str) {
            this.mDeviceInfo.setOsVersion(str);
            return this;
        }

        public Builder setConnectionType(int i) {
            if (this.mDataPrivacy) {
                return this;
            }
            this.mDeviceInfo.setConnectionType(i);
            return this;
        }

        public Builder setLocation(GeoLocation geoLocation) {
            if (this.mDataPrivacy) {
                return this;
            }
            this.mDeviceInfo.setLocation(geoLocation);
            return this;
        }

        public Builder setIsEmulator(boolean z) {
            this.mDeviceInfo.setIsEmulator(Boolean.valueOf(z));
            return this;
        }

        public Builder setDeviceType(int i) {
            if (this.mDataPrivacy) {
                return this;
            }
            this.mDeviceInfo.setDeviceType(i);
            return this;
        }

        public Builder setIsLimitedAdTracking(boolean z) {
            this.mDeviceInfo.setIsLimitedAdTracking(z);
            return this;
        }

        public DeviceInfo build() {
            return this.mDeviceInfo;
        }
    }
}
