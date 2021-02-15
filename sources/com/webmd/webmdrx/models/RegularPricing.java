package com.webmd.webmdrx.models;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Locale;

public class RegularPricing implements Parcelable {
    public static final Parcelable.Creator<RegularPricing> CREATOR = new Parcelable.Creator<RegularPricing>() {
        public RegularPricing createFromParcel(Parcel parcel) {
            return new RegularPricing(parcel);
        }

        public RegularPricing[] newArray(int i) {
            return new RegularPricing[i];
        }
    };
    private double Avg;
    private double Max;
    private double Median;
    private double Min;
    private double Mode;
    private double WAvg;

    public enum typePrice {
        AVG,
        MAX,
        MEDIAN,
        MIN,
        MODE,
        W_AVG
    }

    public int describeContents() {
        return 0;
    }

    public double getAvg() {
        return this.Avg;
    }

    public void setAvg(double d) {
        this.Avg = d;
    }

    public double getMax() {
        return this.Max;
    }

    public void setMax(double d) {
        this.Max = d;
    }

    public double getMedian() {
        return this.Median;
    }

    public void setMedian(double d) {
        this.Median = d;
    }

    public double getMin() {
        return this.Min;
    }

    public void setMin(double d) {
        this.Min = d;
    }

    public double getMode() {
        return this.Mode;
    }

    public void setMode(double d) {
        this.Mode = d;
    }

    public double getWAvg() {
        return this.WAvg;
    }

    public void setWAvg(double d) {
        this.WAvg = d;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeDouble(this.Avg);
        parcel.writeDouble(this.Max);
        parcel.writeDouble(this.Median);
        parcel.writeDouble(this.Min);
        parcel.writeDouble(this.Mode);
        parcel.writeDouble(this.WAvg);
    }

    protected RegularPricing(Parcel parcel) {
        this.Avg = parcel.readDouble();
        this.Max = parcel.readDouble();
        this.Median = parcel.readDouble();
        this.Min = parcel.readDouble();
        this.Mode = parcel.readDouble();
        this.WAvg = parcel.readDouble();
    }

    /* renamed from: com.webmd.webmdrx.models.RegularPricing$2  reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$webmd$webmdrx$models$RegularPricing$typePrice;

        /* JADX WARNING: Can't wrap try/catch for region: R(14:0|1|2|3|4|5|6|7|8|9|10|11|12|14) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                com.webmd.webmdrx.models.RegularPricing$typePrice[] r0 = com.webmd.webmdrx.models.RegularPricing.typePrice.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$webmd$webmdrx$models$RegularPricing$typePrice = r0
                com.webmd.webmdrx.models.RegularPricing$typePrice r1 = com.webmd.webmdrx.models.RegularPricing.typePrice.AVG     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$webmd$webmdrx$models$RegularPricing$typePrice     // Catch:{ NoSuchFieldError -> 0x001d }
                com.webmd.webmdrx.models.RegularPricing$typePrice r1 = com.webmd.webmdrx.models.RegularPricing.typePrice.MAX     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$webmd$webmdrx$models$RegularPricing$typePrice     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.webmd.webmdrx.models.RegularPricing$typePrice r1 = com.webmd.webmdrx.models.RegularPricing.typePrice.MEDIAN     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$webmd$webmdrx$models$RegularPricing$typePrice     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.webmd.webmdrx.models.RegularPricing$typePrice r1 = com.webmd.webmdrx.models.RegularPricing.typePrice.MIN     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$com$webmd$webmdrx$models$RegularPricing$typePrice     // Catch:{ NoSuchFieldError -> 0x003e }
                com.webmd.webmdrx.models.RegularPricing$typePrice r1 = com.webmd.webmdrx.models.RegularPricing.typePrice.MODE     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$com$webmd$webmdrx$models$RegularPricing$typePrice     // Catch:{ NoSuchFieldError -> 0x0049 }
                com.webmd.webmdrx.models.RegularPricing$typePrice r1 = com.webmd.webmdrx.models.RegularPricing.typePrice.W_AVG     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.webmd.webmdrx.models.RegularPricing.AnonymousClass2.<clinit>():void");
        }
    }

    public String toString(typePrice typeprice) {
        switch (AnonymousClass2.$SwitchMap$com$webmd$webmdrx$models$RegularPricing$typePrice[typeprice.ordinal()]) {
            case 1:
                return "$" + String.format(Locale.US, "%.2f", new Object[]{Double.valueOf(this.Avg)});
            case 2:
                return "$" + String.format(Locale.US, "%.2f", new Object[]{Double.valueOf(this.Max)});
            case 3:
                return "$" + String.format(Locale.US, "%.2f", new Object[]{Double.valueOf(this.Median)});
            case 4:
                return "$" + String.format(Locale.US, "%.2f", new Object[]{Double.valueOf(this.Min)});
            case 5:
                return "$" + String.format(Locale.US, "%.2f", new Object[]{Double.valueOf(this.Mode)});
            case 6:
                return "$" + String.format(Locale.US, "%.2f", new Object[]{Double.valueOf(this.WAvg)});
            default:
                return "$" + String.format(Locale.US, "%.2f", new Object[]{Double.valueOf(this.Avg)});
        }
    }
}
