package com.webmd.webmdrx.activities;

import com.webmd.webmdrx.manager.ApiManager;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\n \u0002*\u0004\u0018\u00010\u00010\u0001H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "Lcom/webmd/webmdrx/manager/ApiManager;", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 4, 0})
/* compiled from: CouponActivity.kt */
final class CouponActivity$apiManager$2 extends Lambda implements Function0<ApiManager> {
    public static final CouponActivity$apiManager$2 INSTANCE = new CouponActivity$apiManager$2();

    CouponActivity$apiManager$2() {
        super(0);
    }

    public final ApiManager invoke() {
        return ApiManager.getInstance();
    }
}
