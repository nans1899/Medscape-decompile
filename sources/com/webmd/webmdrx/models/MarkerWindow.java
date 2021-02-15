package com.webmd.webmdrx.models;

import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.util.Arrays;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0003\b\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0007HÆ\u0003J'\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\u0010\u0010\u0016\u001a\u00020\u00032\u0006\u0010\u0017\u001a\u00020\u0003H\u0002J\u0006\u0010\u0018\u001a\u00020\u0003J\u0006\u0010\u0019\u001a\u00020\u0003J\t\u0010\u001a\u001a\u00020\u001bHÖ\u0001J\t\u0010\u001c\u001a\u00020\u0003HÖ\u0001J\u0006\u0010\u001d\u001a\u00020\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u001e"}, d2 = {"Lcom/webmd/webmdrx/models/MarkerWindow;", "", "pharmacyName", "", "address", "Lcom/webmd/webmdrx/models/Address;", "drugPrice", "", "(Ljava/lang/String;Lcom/webmd/webmdrx/models/Address;D)V", "getAddress", "()Lcom/webmd/webmdrx/models/Address;", "getDrugPrice", "()D", "getPharmacyName", "()Ljava/lang/String;", "component1", "component2", "component3", "copy", "equals", "", "other", "formatCapital", "str", "getCityStateZip", "getStreetAddress", "hashCode", "", "toString", "toStringDiscountPricing", "wbmdrx_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: MarkerWindow.kt */
public final class MarkerWindow {
    private final Address address;
    private final double drugPrice;
    private final String pharmacyName;

    public static /* synthetic */ MarkerWindow copy$default(MarkerWindow markerWindow, String str, Address address2, double d, int i, Object obj) {
        if ((i & 1) != 0) {
            str = markerWindow.pharmacyName;
        }
        if ((i & 2) != 0) {
            address2 = markerWindow.address;
        }
        if ((i & 4) != 0) {
            d = markerWindow.drugPrice;
        }
        return markerWindow.copy(str, address2, d);
    }

    public final String component1() {
        return this.pharmacyName;
    }

    public final Address component2() {
        return this.address;
    }

    public final double component3() {
        return this.drugPrice;
    }

    public final MarkerWindow copy(String str, Address address2, double d) {
        Intrinsics.checkNotNullParameter(str, "pharmacyName");
        Intrinsics.checkNotNullParameter(address2, "address");
        return new MarkerWindow(str, address2, d);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MarkerWindow)) {
            return false;
        }
        MarkerWindow markerWindow = (MarkerWindow) obj;
        return Intrinsics.areEqual((Object) this.pharmacyName, (Object) markerWindow.pharmacyName) && Intrinsics.areEqual((Object) this.address, (Object) markerWindow.address) && Double.compare(this.drugPrice, markerWindow.drugPrice) == 0;
    }

    public int hashCode() {
        String str = this.pharmacyName;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        Address address2 = this.address;
        if (address2 != null) {
            i = address2.hashCode();
        }
        long doubleToLongBits = Double.doubleToLongBits(this.drugPrice);
        return ((hashCode + i) * 31) + ((int) (doubleToLongBits ^ (doubleToLongBits >>> 32)));
    }

    public String toString() {
        return "MarkerWindow(pharmacyName=" + this.pharmacyName + ", address=" + this.address + ", drugPrice=" + this.drugPrice + ")";
    }

    public MarkerWindow(String str, Address address2, double d) {
        Intrinsics.checkNotNullParameter(str, "pharmacyName");
        Intrinsics.checkNotNullParameter(address2, "address");
        this.pharmacyName = str;
        this.address = address2;
        this.drugPrice = d;
    }

    public final Address getAddress() {
        return this.address;
    }

    public final double getDrugPrice() {
        return this.drugPrice;
    }

    public final String getPharmacyName() {
        return this.pharmacyName;
    }

    public final String getStreetAddress() {
        StringBuilder sb = new StringBuilder();
        String address1 = this.address.getAddress1();
        Intrinsics.checkNotNullExpressionValue(address1, "address.address1");
        sb.append(formatCapital(address1));
        if (this.address.getAddress2() != null) {
            String address2 = this.address.getAddress2();
            Intrinsics.checkNotNullExpressionValue(address2, "address.address2");
            if (!(address2.length() == 0)) {
                sb.append(10);
                String address22 = this.address.getAddress2();
                Intrinsics.checkNotNullExpressionValue(address22, "address.address2");
                sb.append(formatCapital(address22));
            }
        }
        String sb2 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(sb2, "builder.toString()");
        return sb2;
    }

    public final String getCityStateZip() {
        StringBuilder sb = new StringBuilder();
        String city = this.address.getCity();
        Intrinsics.checkNotNullExpressionValue(city, "address.city");
        sb.append(formatCapital(city));
        sb.append(", ");
        sb.append(this.address.getState());
        sb.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
        String postalCode = this.address.getPostalCode();
        Intrinsics.checkNotNullExpressionValue(postalCode, "address.postalCode");
        if (postalCode != null) {
            String substring = postalCode.substring(0, 5);
            Intrinsics.checkNotNullExpressionValue(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
            sb.append(substring);
            return sb.toString();
        }
        throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
    }

    private final String formatCapital(String str) {
        StringBuilder sb = new StringBuilder(str);
        int length = sb.length();
        boolean z = true;
        for (int i = 0; i < length; i++) {
            char charAt = sb.charAt(i);
            if (!z) {
                sb.setCharAt(i, Character.toLowerCase(charAt));
            }
            z = Character.isWhitespace(charAt);
        }
        String sb2 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(sb2, "builder.toString()");
        return sb2;
    }

    public final String toStringDiscountPricing() {
        StringBuilder sb = new StringBuilder();
        sb.append("$");
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String format = String.format(Locale.US, "%.2f", Arrays.copyOf(new Object[]{Double.valueOf(this.drugPrice)}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "java.lang.String.format(locale, format, *args)");
        sb.append(format);
        return sb.toString();
    }
}
