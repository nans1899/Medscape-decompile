package mnetinternal;

import android.content.Context;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import mnetinternal.ad;
import net.media.android.bidder.base.common.b;
import net.media.android.bidder.base.logging.Logger;

final class cj extends ch {
    private Geocoder b;
    /* access modifiers changed from: private */
    public LocationManager c;

    cj(Context context) {
        super(context, 2);
    }

    /* access modifiers changed from: protected */
    public void a() {
        if (cy.a().c() || cy.a().b()) {
            d();
            a(true);
        }
    }

    /* access modifiers changed from: package-private */
    public void a(int i) {
        if (b(i) && c() && b()) {
            e();
        }
    }

    private void d() {
        Logger.debug("##LocationDataTracker##", "init");
        this.b = new Geocoder((Context) this.a.get());
        this.a.a(new cz<Context>() {
            public void a() {
            }

            public void a(Context context) {
                LocationManager unused = cj.this.c = (LocationManager) context.getSystemService("location");
            }
        });
    }

    private void e() {
        Location lastKnownLocation;
        if (!b.a().e()) {
            if (cy.a().c() || cy.a().b()) {
                try {
                    if (ad.b() && ad.a()) {
                        Logger.debug("##LocationDataTracker##", "getting location from fused provider");
                        ad.a((ad.a) new ad.a() {
                            public void a(Location location) {
                                if (location != null) {
                                    cj.this.a(location);
                                }
                            }
                        });
                    } else if (this.c != null) {
                        Criteria criteria = new Criteria();
                        if (cy.a().b()) {
                            criteria.setAccuracy(1);
                        } else {
                            criteria.setAccuracy(2);
                        }
                        String bestProvider = this.c.getBestProvider(criteria, true);
                        Logger.debug("##LocationDataTracker##", "getting location from provider: " + bestProvider);
                        if (bestProvider != null && (lastKnownLocation = this.c.getLastKnownLocation(bestProvider)) != null) {
                            a(lastKnownLocation);
                        }
                    }
                } catch (Exception e) {
                    Logger.notify("##LocationDataTracker##", "Failed to retrieve location", e);
                }
            }
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x00c4  */
    /* JADX WARNING: Removed duplicated region for block: B:29:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(android.location.Location r14) {
        /*
            r13 = this;
            java.lang.String r0 = "zip_code"
            java.lang.String r1 = "city"
            java.lang.String r2 = "region"
            java.lang.String r3 = "country_code"
            if (r14 == 0) goto L_0x0124
            net.media.android.bidder.base.common.b r4 = net.media.android.bidder.base.common.b.a()
            boolean r4 = r4.e()
            if (r4 == 0) goto L_0x0016
            goto L_0x0124
        L_0x0016:
            java.lang.String r4 = "##LocationDataTracker##"
            java.lang.String r5 = "location updated"
            net.media.android.bidder.base.logging.Logger.debug(r4, r5)
            net.media.android.bidder.base.configs.AdUnitConfig r5 = net.media.android.bidder.base.configs.AdUnitConfig.getInstance()
            net.media.android.bidder.base.models.internal.PublisherConfig r5 = r5.getPublisherConfig()
            boolean r5 = r5.shouldMaskLocation()
            if (r5 == 0) goto L_0x0057
            double r5 = r14.getLongitude()
            r7 = 4621819117588971520(0x4024000000000000, double:10.0)
            double r5 = r5 * r7
            long r5 = java.lang.Math.round(r5)
            double r5 = (double) r5
            r9 = 4591870180066957722(0x3fb999999999999a, double:0.1)
            double r5 = r5 * r9
            r14.setLongitude(r5)
            double r5 = r14.getLatitude()
            double r5 = r5 * r7
            long r5 = java.lang.Math.round(r5)
            double r5 = (double) r5
            double r5 = r5 * r9
            r14.setLatitude(r5)
            r5 = 1165623296(0x457a0000, float:4000.0)
            r14.setAccuracy(r5)
        L_0x0057:
            mnetinternal.ck r5 = mnetinternal.ck.a()
            java.lang.String r6 = "location"
            r5.a((java.lang.String) r6, (java.lang.Object) r14)
            r5 = 0
            android.location.Geocoder r6 = r13.b     // Catch:{ IOException -> 0x00ae }
            double r7 = r14.getLatitude()     // Catch:{ IOException -> 0x00ae }
            double r9 = r14.getLongitude()     // Catch:{ IOException -> 0x00ae }
            r11 = 1
            java.util.List r6 = r6.getFromLocation(r7, r9, r11)     // Catch:{ IOException -> 0x00ae }
            if (r6 == 0) goto L_0x00ba
            boolean r7 = r6.isEmpty()     // Catch:{ IOException -> 0x00ae }
            if (r7 != 0) goto L_0x00ba
            r7 = 0
            java.lang.Object r6 = r6.get(r7)     // Catch:{ IOException -> 0x00ae }
            android.location.Address r6 = (android.location.Address) r6     // Catch:{ IOException -> 0x00ae }
            mnetinternal.ck r5 = mnetinternal.ck.a()     // Catch:{ IOException -> 0x00ac }
            java.lang.String r7 = r6.getCountryCode()     // Catch:{ IOException -> 0x00ac }
            r5.a((java.lang.String) r3, (java.lang.Object) r7)     // Catch:{ IOException -> 0x00ac }
            mnetinternal.ck r5 = mnetinternal.ck.a()     // Catch:{ IOException -> 0x00ac }
            java.lang.String r7 = r6.getCountryCode()     // Catch:{ IOException -> 0x00ac }
            r5.a((java.lang.String) r2, (java.lang.Object) r7)     // Catch:{ IOException -> 0x00ac }
            mnetinternal.ck r5 = mnetinternal.ck.a()     // Catch:{ IOException -> 0x00ac }
            java.lang.String r7 = r6.getCountryCode()     // Catch:{ IOException -> 0x00ac }
            r5.a((java.lang.String) r1, (java.lang.Object) r7)     // Catch:{ IOException -> 0x00ac }
            mnetinternal.ck r5 = mnetinternal.ck.a()     // Catch:{ IOException -> 0x00ac }
            java.lang.String r7 = r6.getCountryCode()     // Catch:{ IOException -> 0x00ac }
            r5.a((java.lang.String) r0, (java.lang.Object) r7)     // Catch:{ IOException -> 0x00ac }
            goto L_0x00b9
        L_0x00ac:
            r5 = move-exception
            goto L_0x00b2
        L_0x00ae:
            r6 = move-exception
            r12 = r6
            r6 = r5
            r5 = r12
        L_0x00b2:
            java.lang.String r5 = r5.getMessage()
            net.media.android.bidder.base.logging.Logger.error(r4, r5)
        L_0x00b9:
            r5 = r6
        L_0x00ba:
            net.media.android.bidder.base.common.b r4 = net.media.android.bidder.base.common.b.a()
            boolean r4 = r4.e()
            if (r4 != 0) goto L_0x0124
            r4 = 5
            java.lang.String r6 = "sdk_data"
            net.media.android.bidder.base.models.internal.AnalyticsEvent r4 = net.media.android.bidder.base.models.internal.AnalyticsEvent.Events.newSdkEvent(r6, r4)
            double r6 = r14.getLatitude()
            java.lang.Double r6 = java.lang.Double.valueOf(r6)
            java.lang.String r7 = "lat"
            r4.addProperty(r7, r6)
            double r6 = r14.getLongitude()
            java.lang.Double r6 = java.lang.Double.valueOf(r6)
            java.lang.String r7 = "lng"
            r4.addProperty(r7, r6)
            float r6 = r14.getAccuracy()
            int r6 = java.lang.Math.round(r6)
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)
            java.lang.String r7 = "location_accuracy"
            r4.addProperty(r7, r6)
            java.lang.String r14 = r14.getProvider()
            java.lang.String r6 = "location_provider"
            r4.addProperty(r6, r14)
            if (r5 == 0) goto L_0x011d
            java.lang.String r14 = r5.getCountryCode()
            r4.addProperty(r3, r14)
            java.lang.String r14 = r5.getAdminArea()
            r4.addProperty(r2, r14)
            java.lang.String r14 = r5.getLocality()
            r4.addProperty(r1, r14)
            java.lang.String r14 = r5.getPostalCode()
            r4.addProperty(r0, r14)
        L_0x011d:
            net.media.android.bidder.base.analytics.b r14 = net.media.android.bidder.base.analytics.b.a()
            r14.a(r4)
        L_0x0124:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: mnetinternal.cj.a(android.location.Location):void");
    }
}
