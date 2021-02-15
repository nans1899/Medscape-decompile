package com.webmd.webmdrx.activities;

import android.widget.TextView;
import androidx.core.app.NotificationCompat;
import com.appboy.Constants;
import com.webmd.webmdrx.models.Coupon;
import com.webmd.webmdrx.models.CouponContact;
import com.webmd.webmdrx.models.CouponData;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Typography;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000)\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u001e\u0010\u0003\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J$\u0010\t\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00020\u00062\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00020\u000bH\u0016¨\u0006\f"}, d2 = {"com/webmd/webmdrx/activities/CouponActivity$fetchRxCardDetails$2", "Lretrofit2/Callback;", "Lcom/webmd/webmdrx/models/Coupon;", "onFailure", "", "call", "Lretrofit2/Call;", "t", "", "onResponse", "response", "Lretrofit2/Response;", "wbmdrx_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: CouponActivity.kt */
public final class CouponActivity$fetchRxCardDetails$2 implements Callback<Coupon> {
    final /* synthetic */ CouponActivity this$0;

    CouponActivity$fetchRxCardDetails$2(CouponActivity couponActivity) {
        this.this$0 = couponActivity;
    }

    public void onFailure(Call<Coupon> call, Throwable th) {
        Intrinsics.checkNotNullParameter(call, NotificationCompat.CATEGORY_CALL);
        Intrinsics.checkNotNullParameter(th, Constants.APPBOY_PUSH_TITLE_KEY);
        th.printStackTrace();
    }

    public void onResponse(Call<Coupon> call, Response<Coupon> response) {
        Intrinsics.checkNotNullParameter(call, NotificationCompat.CATEGORY_CALL);
        Intrinsics.checkNotNullParameter(response, "response");
        if (response.body() != null) {
            Coupon body = response.body();
            Intrinsics.checkNotNull(body);
            Intrinsics.checkNotNullExpressionValue(body, "response.body()!!");
            CouponData data = body.getData();
            if (CouponActivity.access$getMPharmacyName$p(this.this$0).equals(this.this$0.CVS_PHARMACY)) {
                TextView access$getCouponAuth$p = CouponActivity.access$getCouponAuth$p(this.this$0);
                CouponActivity couponActivity = this.this$0;
                StringBuilder sb = new StringBuilder();
                sb.append("AUTH # ");
                Intrinsics.checkNotNullExpressionValue(data, "couponData");
                CouponContact couponContact = data.getContacts().get(0);
                Intrinsics.checkNotNullExpressionValue(couponContact, "couponData.contacts[0]");
                sb.append(couponContact.getMemberNumber());
                access$getCouponAuth$p.setText(couponActivity.styleStringText(sb.toString(), 5));
                TextView access$getCouponBin$p = CouponActivity.access$getCouponBin$p(this.this$0);
                CouponActivity couponActivity2 = this.this$0;
                StringBuilder sb2 = new StringBuilder();
                sb2.append("BIN   ");
                CouponContact couponContact2 = data.getContacts().get(0);
                Intrinsics.checkNotNullExpressionValue(couponContact2, "couponData.contacts[0]");
                sb2.append(couponContact2.getBIN());
                access$getCouponBin$p.setText(couponActivity2.styleStringText(sb2.toString(), 6));
                TextView access$getCouponGrp$p = CouponActivity.access$getCouponGrp$p(this.this$0);
                CouponActivity couponActivity3 = this.this$0;
                StringBuilder sb3 = new StringBuilder();
                sb3.append("GRP  ");
                CouponContact couponContact3 = data.getContacts().get(0);
                Intrinsics.checkNotNullExpressionValue(couponContact3, "couponData.contacts[0]");
                sb3.append(couponContact3.getGroupNumber());
                access$getCouponGrp$p.setText(couponActivity3.styleStringText(sb3.toString(), 5));
                TextView access$getCouponPcn$p = CouponActivity.access$getCouponPcn$p(this.this$0);
                CouponActivity couponActivity4 = this.this$0;
                StringBuilder sb4 = new StringBuilder();
                sb4.append("PCN  ");
                CouponContact couponContact4 = data.getContacts().get(0);
                Intrinsics.checkNotNullExpressionValue(couponContact4, "couponData.contacts[0]");
                sb4.append(couponContact4.getPCN());
                access$getCouponPcn$p.setText(couponActivity4.styleStringText(sb4.toString(), 5));
            } else if (CouponActivity.access$getMPharmacyName$p(this.this$0).equals(this.this$0.WALMART_PHARMACY)) {
                TextView access$getCouponAuth$p2 = CouponActivity.access$getCouponAuth$p(this.this$0);
                CouponActivity couponActivity5 = this.this$0;
                StringBuilder sb5 = new StringBuilder();
                sb5.append("AUTH# ");
                Intrinsics.checkNotNullExpressionValue(data, "couponData");
                CouponContact couponContact5 = data.getContacts().get(0);
                Intrinsics.checkNotNullExpressionValue(couponContact5, "couponData.contacts[0]");
                sb5.append(couponContact5.getMemberNumber());
                access$getCouponAuth$p2.setText(couponActivity5.styleStringText(sb5.toString(), 0));
                TextView access$getCouponBin$p2 = CouponActivity.access$getCouponBin$p(this.this$0);
                CouponActivity couponActivity6 = this.this$0;
                StringBuilder sb6 = new StringBuilder();
                sb6.append("BIN   ");
                CouponContact couponContact6 = data.getContacts().get(0);
                Intrinsics.checkNotNullExpressionValue(couponContact6, "couponData.contacts[0]");
                sb6.append(couponContact6.getBIN());
                access$getCouponBin$p2.setText(couponActivity6.styleStringText(sb6.toString(), 0));
                TextView access$getCouponGrp$p2 = CouponActivity.access$getCouponGrp$p(this.this$0);
                CouponActivity couponActivity7 = this.this$0;
                StringBuilder sb7 = new StringBuilder();
                sb7.append("GRP  ");
                CouponContact couponContact7 = data.getContacts().get(0);
                Intrinsics.checkNotNullExpressionValue(couponContact7, "couponData.contacts[0]");
                sb7.append(couponContact7.getGroupNumber());
                access$getCouponGrp$p2.setText(couponActivity7.styleStringText(sb7.toString(), 0));
                TextView access$getCouponPcn$p2 = CouponActivity.access$getCouponPcn$p(this.this$0);
                CouponActivity couponActivity8 = this.this$0;
                StringBuilder sb8 = new StringBuilder();
                sb8.append("PCN  ");
                CouponContact couponContact8 = data.getContacts().get(0);
                Intrinsics.checkNotNullExpressionValue(couponContact8, "couponData.contacts[0]");
                sb8.append(couponContact8.getPCN());
                access$getCouponPcn$p2.setText(couponActivity8.styleStringText(sb8.toString(), 0));
            } else {
                CouponActivity.access$getDrugNameWebmd$p(this.this$0).setText(CouponActivity.access$getMDrugName$p(this.this$0));
                TextView access$getDrugDetailWebmd$p = CouponActivity.access$getDrugDetailWebmd$p(this.this$0);
                StringBuilder sb9 = new StringBuilder();
                sb9.append(CouponActivity.access$getMDosage$p(this.this$0));
                sb9.append(", ");
                sb9.append(Integer.toString((int) this.this$0.mQuantity));
                sb9.append(' ');
                String access$getMForm$p = CouponActivity.access$getMForm$p(this.this$0);
                if (access$getMForm$p != null) {
                    String lowerCase = access$getMForm$p.toLowerCase();
                    Intrinsics.checkNotNullExpressionValue(lowerCase, "(this as java.lang.String).toLowerCase()");
                    sb9.append(lowerCase);
                    access$getDrugDetailWebmd$p.setText(sb9.toString());
                    TextView access$getCouponAuthWebmd$p = CouponActivity.access$getCouponAuthWebmd$p(this.this$0);
                    Intrinsics.checkNotNullExpressionValue(data, "couponData");
                    CouponContact couponContact9 = data.getContacts().get(0);
                    Intrinsics.checkNotNullExpressionValue(couponContact9, "couponData.contacts[0]");
                    access$getCouponAuthWebmd$p.setText(couponContact9.getMemberNumber());
                    TextView access$getCouponBinWebmd$p = CouponActivity.access$getCouponBinWebmd$p(this.this$0);
                    CouponContact couponContact10 = data.getContacts().get(0);
                    Intrinsics.checkNotNullExpressionValue(couponContact10, "couponData.contacts[0]");
                    access$getCouponBinWebmd$p.setText(couponContact10.getBIN());
                    TextView access$getCouponGrpWebmd$p = CouponActivity.access$getCouponGrpWebmd$p(this.this$0);
                    CouponContact couponContact11 = data.getContacts().get(0);
                    Intrinsics.checkNotNullExpressionValue(couponContact11, "couponData.contacts[0]");
                    access$getCouponGrpWebmd$p.setText(couponContact11.getGroupNumber());
                    TextView access$getCouponPcnWebmd$p = CouponActivity.access$getCouponPcnWebmd$p(this.this$0);
                    CouponContact couponContact12 = data.getContacts().get(0);
                    Intrinsics.checkNotNullExpressionValue(couponContact12, "couponData.contacts[0]");
                    access$getCouponPcnWebmd$p.setText(couponContact12.getPCN());
                    TextView access$getCouponDrugPriceWebmd$p = CouponActivity.access$getCouponDrugPriceWebmd$p(this.this$0);
                    StringBuilder sb10 = new StringBuilder();
                    sb10.append(Typography.dollar);
                    sb10.append(this.this$0.mDrugPrice);
                    access$getCouponDrugPriceWebmd$p.setText(sb10.toString());
                    CouponActivity.access$getCouponPharmacyWebmd$p(this.this$0).setText(CouponActivity.access$getMPharmacyName$p(this.this$0));
                } else {
                    throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
                }
            }
            this.this$0.displayData();
        }
    }
}
