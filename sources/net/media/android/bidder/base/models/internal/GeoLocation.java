package net.media.android.bidder.base.models.internal;

import com.facebook.appevents.UserDataStore;
import com.medscape.android.settings.Settings;
import com.mnet.gson.e;
import com.mnet.gson.internal.bind.i;
import com.mnet.gson.v;
import com.wbmd.qxcalculator.util.legacy.ContentParser;
import mnetinternal.c;
import mnetinternal.g;
import mnetinternal.h;
import mnetinternal.j;
import mnetinternal.p;

public final class GeoLocation {
    public static final int SOURCE_GPS = 1;
    public static final int SOURCE_IP = 2;
    public static final int SOURCE_USER_PROVIDED = 3;
    @c(a = "accuracy")
    protected int mAccuracy;
    @c(a = "city")
    protected String mCity;
    @c(a = "country")
    protected String mCountry;
    @c(a = "lat")
    protected double mLatitude;
    @c(a = "type")
    protected int mLocationSource;
    @c(a = "lon")
    protected double mLongitude;
    @c(a = "utcoffset")
    protected int mOffset;
    @c(a = "region")
    protected String mRegion;
    @c(a = "zip")
    protected String mZipCode;

    public final class TypeAdapter extends v<GeoLocation> {
        public static final g<GeoLocation> TYPE_TOKEN = g.b(GeoLocation.class);
        private final e mGson;

        public TypeAdapter(e eVar) {
            this.mGson = eVar;
        }

        public void write(j jVar, GeoLocation geoLocation) {
            if (geoLocation == null) {
                jVar.f();
                return;
            }
            jVar.d();
            jVar.a(Settings.LATITUDE_KEY);
            jVar.a(geoLocation.mLatitude);
            jVar.a("lon");
            jVar.a(geoLocation.mLongitude);
            jVar.a("type");
            jVar.a((long) geoLocation.mLocationSource);
            jVar.a("utcoffset");
            jVar.a((long) geoLocation.mOffset);
            jVar.a(UserDataStore.COUNTRY);
            if (geoLocation.mCountry != null) {
                i.A.write(jVar, geoLocation.mCountry);
            } else {
                jVar.f();
            }
            jVar.a(ContentParser.REGION);
            if (geoLocation.mRegion != null) {
                i.A.write(jVar, geoLocation.mRegion);
            } else {
                jVar.f();
            }
            jVar.a(Settings.CITY);
            if (geoLocation.mCity != null) {
                i.A.write(jVar, geoLocation.mCity);
            } else {
                jVar.f();
            }
            jVar.a(Settings.ZIP);
            if (geoLocation.mZipCode != null) {
                i.A.write(jVar, geoLocation.mZipCode);
            } else {
                jVar.f();
            }
            jVar.a("accuracy");
            jVar.a((long) geoLocation.mAccuracy);
            jVar.e();
        }

        public GeoLocation read(h hVar) {
            mnetinternal.i f = hVar.f();
            if (mnetinternal.i.NULL == f) {
                hVar.j();
                return null;
            } else if (mnetinternal.i.BEGIN_OBJECT != f) {
                hVar.n();
                return null;
            } else {
                hVar.c();
                GeoLocation geoLocation = new GeoLocation();
                while (hVar.e()) {
                    String g = hVar.g();
                    char c = 65535;
                    switch (g.hashCode()) {
                        case -2131707655:
                            if (g.equals("accuracy")) {
                                c = 8;
                                break;
                            }
                            break;
                        case -934795532:
                            if (g.equals(ContentParser.REGION)) {
                                c = 5;
                                break;
                            }
                            break;
                        case 106911:
                            if (g.equals(Settings.LATITUDE_KEY)) {
                                c = 0;
                                break;
                            }
                            break;
                        case 107339:
                            if (g.equals("lon")) {
                                c = 1;
                                break;
                            }
                            break;
                        case 120609:
                            if (g.equals(Settings.ZIP)) {
                                c = 7;
                                break;
                            }
                            break;
                        case 3053931:
                            if (g.equals(Settings.CITY)) {
                                c = 6;
                                break;
                            }
                            break;
                        case 3575610:
                            if (g.equals("type")) {
                                c = 2;
                                break;
                            }
                            break;
                        case 227499831:
                            if (g.equals("utcoffset")) {
                                c = 3;
                                break;
                            }
                            break;
                        case 957831062:
                            if (g.equals(UserDataStore.COUNTRY)) {
                                c = 4;
                                break;
                            }
                            break;
                    }
                    switch (c) {
                        case 0:
                            geoLocation.mLatitude = p.h.a(hVar, geoLocation.mLatitude);
                            break;
                        case 1:
                            geoLocation.mLongitude = p.h.a(hVar, geoLocation.mLongitude);
                            break;
                        case 2:
                            geoLocation.mLocationSource = p.j.a(hVar, geoLocation.mLocationSource);
                            break;
                        case 3:
                            geoLocation.mOffset = p.j.a(hVar, geoLocation.mOffset);
                            break;
                        case 4:
                            geoLocation.mCountry = i.A.read(hVar);
                            break;
                        case 5:
                            geoLocation.mRegion = i.A.read(hVar);
                            break;
                        case 6:
                            geoLocation.mCity = i.A.read(hVar);
                            break;
                        case 7:
                            geoLocation.mZipCode = i.A.read(hVar);
                            break;
                        case 8:
                            geoLocation.mAccuracy = p.j.a(hVar, geoLocation.mAccuracy);
                            break;
                        default:
                            hVar.n();
                            break;
                    }
                }
                hVar.d();
                return geoLocation;
            }
        }
    }

    /* access modifiers changed from: private */
    public void setLatitude(double d) {
        this.mLatitude = d;
    }

    /* access modifiers changed from: private */
    public void setLongitude(double d) {
        this.mLongitude = d;
    }

    /* access modifiers changed from: private */
    public void setLocationSource(int i) {
        this.mLocationSource = i;
    }

    /* access modifiers changed from: private */
    public void setCountry(String str) {
        this.mCountry = str;
    }

    /* access modifiers changed from: private */
    public void setRegion(String str) {
        this.mRegion = str;
    }

    /* access modifiers changed from: private */
    public void setCity(String str) {
        this.mCity = str;
    }

    /* access modifiers changed from: private */
    public void setZipCode(String str) {
        this.mZipCode = str;
    }

    /* access modifiers changed from: private */
    public void setAccuracy(int i) {
        this.mAccuracy = i;
    }

    public static class Builder {
        private GeoLocation mLocation = new GeoLocation();

        public Builder setLatitude(double d) {
            this.mLocation.setLatitude(d);
            return this;
        }

        public Builder setLongitude(double d) {
            this.mLocation.setLongitude(d);
            return this;
        }

        public Builder setLocationSource(int i) {
            this.mLocation.setLocationSource(i);
            return this;
        }

        public Builder setCountry(String str) {
            this.mLocation.setCountry(str);
            return this;
        }

        public Builder setRegion(String str) {
            this.mLocation.setRegion(str);
            return this;
        }

        public Builder setCity(String str) {
            this.mLocation.setCity(str);
            return this;
        }

        public Builder setZipCode(String str) {
            this.mLocation.setZipCode(str);
            return this;
        }

        public Builder setAccuracy(int i) {
            this.mLocation.setAccuracy(i);
            return this;
        }

        public GeoLocation build() {
            return this.mLocation;
        }
    }
}
