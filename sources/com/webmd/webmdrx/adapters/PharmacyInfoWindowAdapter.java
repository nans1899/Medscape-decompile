package com.webmd.webmdrx.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;
import com.webmd.webmdrx.R;
import com.webmd.webmdrx.models.MarkerWindow;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0014\u0010\r\u001a\u0004\u0018\u00010\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0016J\u0014\u0010\u0011\u001a\u0004\u0018\u00010\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u001a\u0010\u0007\u001a\u00020\bX.¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\f¨\u0006\u0012"}, d2 = {"Lcom/webmd/webmdrx/adapters/PharmacyInfoWindowAdapter;", "Lcom/google/android/gms/maps/GoogleMap$InfoWindowAdapter;", "activity", "Landroid/app/Activity;", "(Landroid/app/Activity;)V", "getActivity", "()Landroid/app/Activity;", "markerData", "Lcom/webmd/webmdrx/models/MarkerWindow;", "getMarkerData", "()Lcom/webmd/webmdrx/models/MarkerWindow;", "setMarkerData", "(Lcom/webmd/webmdrx/models/MarkerWindow;)V", "getInfoContents", "Landroid/view/View;", "marker", "Lcom/google/android/gms/maps/model/Marker;", "getInfoWindow", "wbmdrx_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: PharmacyInfoWindowAdapter.kt */
public final class PharmacyInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {
    private final Activity activity;
    public MarkerWindow markerData;

    public View getInfoContents(Marker marker) {
        return null;
    }

    public PharmacyInfoWindowAdapter(Activity activity2) {
        Intrinsics.checkNotNullParameter(activity2, "activity");
        this.activity = activity2;
    }

    public final MarkerWindow getMarkerData() {
        MarkerWindow markerWindow = this.markerData;
        if (markerWindow == null) {
            Intrinsics.throwUninitializedPropertyAccessException("markerData");
        }
        return markerWindow;
    }

    public final void setMarkerData(MarkerWindow markerWindow) {
        Intrinsics.checkNotNullParameter(markerWindow, "<set-?>");
        this.markerData = markerWindow;
    }

    public final Activity getActivity() {
        return this.activity;
    }

    public View getInfoWindow(Marker marker) {
        Object tag = marker != null ? marker.getTag() : null;
        if (tag != null) {
            this.markerData = (MarkerWindow) tag;
            Object systemService = this.activity.getSystemService("layout_inflater");
            if (systemService != null) {
                View inflate = ((LayoutInflater) systemService).inflate(R.layout.rx_map_info_window, (ViewGroup) null);
                if (inflate != null) {
                    LinearLayout linearLayout = (LinearLayout) inflate;
                    TextView textView = (TextView) linearLayout.findViewById(R.id.map_info_pharmacy_name_text);
                    TextView textView2 = (TextView) linearLayout.findViewById(R.id.map_info_address_text_1);
                    TextView textView3 = (TextView) linearLayout.findViewById(R.id.map_info_address_text_2);
                    TextView textView4 = (TextView) linearLayout.findViewById(R.id.map_info_price_text);
                    Intrinsics.checkNotNullExpressionValue(textView, "pharmacyText");
                    MarkerWindow markerWindow = this.markerData;
                    if (markerWindow == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("markerData");
                    }
                    textView.setText(markerWindow.getPharmacyName());
                    Intrinsics.checkNotNullExpressionValue(textView2, "addressOneText");
                    MarkerWindow markerWindow2 = this.markerData;
                    if (markerWindow2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("markerData");
                    }
                    textView2.setText(markerWindow2.getStreetAddress());
                    Intrinsics.checkNotNullExpressionValue(textView3, "addressTwoText");
                    MarkerWindow markerWindow3 = this.markerData;
                    if (markerWindow3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("markerData");
                    }
                    textView3.setText(markerWindow3.getCityStateZip());
                    Intrinsics.checkNotNullExpressionValue(textView4, "priceText");
                    MarkerWindow markerWindow4 = this.markerData;
                    if (markerWindow4 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("markerData");
                    }
                    textView4.setText(markerWindow4.toStringDiscountPricing());
                    return linearLayout;
                }
                throw new NullPointerException("null cannot be cast to non-null type android.widget.LinearLayout");
            }
            throw new NullPointerException("null cannot be cast to non-null type android.view.LayoutInflater");
        }
        throw new NullPointerException("null cannot be cast to non-null type com.webmd.webmdrx.models.MarkerWindow");
    }
}
