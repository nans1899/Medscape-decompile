package com.google.firebase.crashlytics.internal.model;

import com.google.firebase.crashlytics.internal.model.CrashlyticsReport;

final class AutoValue_CrashlyticsReport_Session_Event_Device extends CrashlyticsReport.Session.Event.Device {
    private final Double batteryLevel;
    private final int batteryVelocity;
    private final long diskUsed;
    private final int orientation;
    private final boolean proximityOn;
    private final long ramUsed;

    private AutoValue_CrashlyticsReport_Session_Event_Device(Double d, int i, boolean z, int i2, long j, long j2) {
        this.batteryLevel = d;
        this.batteryVelocity = i;
        this.proximityOn = z;
        this.orientation = i2;
        this.ramUsed = j;
        this.diskUsed = j2;
    }

    public Double getBatteryLevel() {
        return this.batteryLevel;
    }

    public int getBatteryVelocity() {
        return this.batteryVelocity;
    }

    public boolean isProximityOn() {
        return this.proximityOn;
    }

    public int getOrientation() {
        return this.orientation;
    }

    public long getRamUsed() {
        return this.ramUsed;
    }

    public long getDiskUsed() {
        return this.diskUsed;
    }

    public String toString() {
        return "Device{batteryLevel=" + this.batteryLevel + ", batteryVelocity=" + this.batteryVelocity + ", proximityOn=" + this.proximityOn + ", orientation=" + this.orientation + ", ramUsed=" + this.ramUsed + ", diskUsed=" + this.diskUsed + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CrashlyticsReport.Session.Event.Device)) {
            return false;
        }
        CrashlyticsReport.Session.Event.Device device = (CrashlyticsReport.Session.Event.Device) obj;
        Double d = this.batteryLevel;
        if (d != null ? d.equals(device.getBatteryLevel()) : device.getBatteryLevel() == null) {
            if (this.batteryVelocity == device.getBatteryVelocity() && this.proximityOn == device.isProximityOn() && this.orientation == device.getOrientation() && this.ramUsed == device.getRamUsed() && this.diskUsed == device.getDiskUsed()) {
                return true;
            }
            return false;
        }
        return false;
    }

    public int hashCode() {
        Double d = this.batteryLevel;
        int hashCode = ((((d == null ? 0 : d.hashCode()) ^ 1000003) * 1000003) ^ this.batteryVelocity) * 1000003;
        int i = this.proximityOn ? 1231 : 1237;
        long j = this.ramUsed;
        long j2 = this.diskUsed;
        return ((((((hashCode ^ i) * 1000003) ^ this.orientation) * 1000003) ^ ((int) (j ^ (j >>> 32)))) * 1000003) ^ ((int) (j2 ^ (j2 >>> 32)));
    }

    static final class Builder extends CrashlyticsReport.Session.Event.Device.Builder {
        private Double batteryLevel;
        private Integer batteryVelocity;
        private Long diskUsed;
        private Integer orientation;
        private Boolean proximityOn;
        private Long ramUsed;

        Builder() {
        }

        public CrashlyticsReport.Session.Event.Device.Builder setBatteryLevel(Double d) {
            this.batteryLevel = d;
            return this;
        }

        public CrashlyticsReport.Session.Event.Device.Builder setBatteryVelocity(int i) {
            this.batteryVelocity = Integer.valueOf(i);
            return this;
        }

        public CrashlyticsReport.Session.Event.Device.Builder setProximityOn(boolean z) {
            this.proximityOn = Boolean.valueOf(z);
            return this;
        }

        public CrashlyticsReport.Session.Event.Device.Builder setOrientation(int i) {
            this.orientation = Integer.valueOf(i);
            return this;
        }

        public CrashlyticsReport.Session.Event.Device.Builder setRamUsed(long j) {
            this.ramUsed = Long.valueOf(j);
            return this;
        }

        public CrashlyticsReport.Session.Event.Device.Builder setDiskUsed(long j) {
            this.diskUsed = Long.valueOf(j);
            return this;
        }

        public CrashlyticsReport.Session.Event.Device build() {
            String str = "";
            if (this.batteryVelocity == null) {
                str = str + " batteryVelocity";
            }
            if (this.proximityOn == null) {
                str = str + " proximityOn";
            }
            if (this.orientation == null) {
                str = str + " orientation";
            }
            if (this.ramUsed == null) {
                str = str + " ramUsed";
            }
            if (this.diskUsed == null) {
                str = str + " diskUsed";
            }
            if (str.isEmpty()) {
                return new AutoValue_CrashlyticsReport_Session_Event_Device(this.batteryLevel, this.batteryVelocity.intValue(), this.proximityOn.booleanValue(), this.orientation.intValue(), this.ramUsed.longValue(), this.diskUsed.longValue());
            }
            throw new IllegalStateException("Missing required properties:" + str);
        }
    }
}
