package com.medscape.android.landingfeed.repository;

import android.content.Context;
import androidx.fragment.app.FragmentActivity;
import com.medscape.android.Constants;
import com.medscape.android.R;
import com.medscape.android.Settings;
import com.medscape.android.activity.search.RecentlyViewedSuggestionHelper;
import com.medscape.android.activity.webviews.WebviewUtil;
import com.medscape.android.homescreen.util.DateUtils;
import com.medscape.android.landingfeed.model.FeedConstants;
import com.medscape.android.landingfeed.model.FeedDataItem;
import com.medscape.android.landingfeed.viewmodel.LandingFeedViewModel;
import com.medscape.android.myinvites.MyInvitationsManager;
import com.wbmd.qxcalculator.util.legacy.ContentParser;
import com.webmd.wbmdcmepulse.models.utils.Utilities;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lcom/medscape/android/landingfeed/repository/FeedRepository;", "", "()V", "Companion", "medscape_release"}, k = 1, mv = {1, 4, 0})
/* compiled from: FeedRepository.kt */
public final class FeedRepository {
    public static final int CME = 3;
    public static final int CONSULT = 4;
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final String FEED_EDUCATION = "education";
    private static final String FEED_HOME = "home";
    private static final String FEED_NEWS = "news";
    public static final int HOME = 1;
    public static final int NEWS = 2;
    /* access modifiers changed from: private */
    public static List<? extends FeedDataItem> feedItems;
    /* access modifiers changed from: private */
    public static int specificInvitationPos;
    /* access modifiers changed from: private */
    public static final Map<Integer, String> typeMap = MapsKt.mapOf(TuplesKt.to(1, FEED_HOME), TuplesKt.to(2, "news"), TuplesKt.to(3, "education"));

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0012\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u0013\u001a\u00020\u000e2\u0006\u0010\u0014\u001a\u00020\u0015J\n\u0010\u0016\u001a\u0004\u0018\u00010\u000eH\u0002J2\u0010\u0017\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\r2\u0006\u0010\u0018\u001a\u00020\u00042\u001a\u0010\u0019\u001a\u0016\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u001aj\n\u0012\u0004\u0012\u00020\u000e\u0018\u0001`\u001bJ<\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u000e0\u001d2\u0006\u0010\u0014\u001a\u00020\u001e2\u000e\u0010\u001f\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u001d2\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020%J\u0010\u0010&\u001a\u00020%2\u0006\u0010\u0018\u001a\u00020\u0004H\u0002J\u001c\u0010'\u001a\u0004\u0018\u00010\u00072\b\u0010(\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u0014\u001a\u00020\u0015H\u0002J&\u0010)\u001a\u00020*2\u0006\u0010\u0014\u001a\u00020\u001e2\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u000e0\u001d2\u0006\u0010\"\u001a\u00020#H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u0016\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\rX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00070\u0011X\u0004¢\u0006\u0002\n\u0000¨\u0006+"}, d2 = {"Lcom/medscape/android/landingfeed/repository/FeedRepository$Companion;", "", "()V", "CME", "", "CONSULT", "FEED_EDUCATION", "", "FEED_HOME", "FEED_NEWS", "HOME", "NEWS", "feedItems", "", "Lcom/medscape/android/landingfeed/model/FeedDataItem;", "specificInvitationPos", "typeMap", "", "generateInvitationTitle", "item", "context", "Landroid/content/Context;", "getDataUpdateItem", "getFeedItemsByType", "feedType", "configItems", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "insertExtras", "", "Landroidx/fragment/app/FragmentActivity;", "items", "adConfig", "Lcom/medscape/android/analytics/remoteconfig/AdRemoteConfigModel;", "landingViewModel", "Lcom/medscape/android/landingfeed/viewmodel/LandingFeedViewModel;", "initialLoad", "", "isFeedADEligible", "parseGenericTitle", "title", "setupItems", "", "medscape_release"}, k = 1, mv = {1, 4, 0})
    /* compiled from: FeedRepository.kt */
    public static final class Companion {
        private final boolean isFeedADEligible(int i) {
            return i == 1 || i == 2;
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX WARNING: Removed duplicated region for block: B:146:0x0359  */
        /* JADX WARNING: Removed duplicated region for block: B:171:0x03e4  */
        /* JADX WARNING: Removed duplicated region for block: B:17:0x004e A[Catch:{ all -> 0x0168 }] */
        /* JADX WARNING: Removed duplicated region for block: B:181:0x0430  */
        /* JADX WARNING: Removed duplicated region for block: B:184:0x0440  */
        /* JADX WARNING: Removed duplicated region for block: B:225:0x05fa  */
        /* JADX WARNING: Removed duplicated region for block: B:232:0x0052 A[SYNTHETIC] */
        /* JADX WARNING: Removed duplicated region for block: B:32:0x0084 A[Catch:{ all -> 0x0168 }] */
        /* JADX WARNING: Removed duplicated region for block: B:38:0x0090 A[Catch:{ all -> 0x0168 }] */
        /* JADX WARNING: Removed duplicated region for block: B:46:0x018f  */
        /* JADX WARNING: Removed duplicated region for block: B:58:0x01c2 A[ADDED_TO_REGION] */
        /* JADX WARNING: Removed duplicated region for block: B:63:0x01cd  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final java.util.List<com.medscape.android.landingfeed.model.FeedDataItem> insertExtras(androidx.fragment.app.FragmentActivity r100, java.util.List<com.medscape.android.landingfeed.model.FeedDataItem> r101, com.medscape.android.analytics.remoteconfig.AdRemoteConfigModel r102, com.medscape.android.landingfeed.viewmodel.LandingFeedViewModel r103, boolean r104) {
            /*
                r99 = this;
                r1 = r100
                r2 = r101
                r3 = r103
                java.lang.String r0 = "context"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r1, r0)
                java.lang.String r0 = "adConfig"
                r4 = r102
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
                java.lang.String r0 = "landingViewModel"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
                if (r2 == 0) goto L_0x05fd
                java.lang.String r5 = "dataUpdate"
                r7 = 0
                r8 = 1
                if (r104 == 0) goto L_0x030d
                com.medscape.android.landingfeed.repository.FeedRepository.specificInvitationPos = r7
                int r0 = r103.getFeedType()
                r9 = 3
                if (r0 != r9) goto L_0x016c
                java.lang.String r0 = r103.getCurrentSpecialityName()     // Catch:{ all -> 0x0168 }
                com.medscape.android.analytics.remoteconfig.ClinicalAdvancesConfigManager$Companion r9 = com.medscape.android.analytics.remoteconfig.ClinicalAdvancesConfigManager.Companion     // Catch:{ all -> 0x0168 }
                java.util.List r9 = r9.getClinicalAdvancesConfig()     // Catch:{ all -> 0x0168 }
                r10 = r9
                java.util.Collection r10 = (java.util.Collection) r10     // Catch:{ all -> 0x0168 }
                boolean r10 = r10.isEmpty()     // Catch:{ all -> 0x0168 }
                r10 = r10 ^ r8
                if (r10 == 0) goto L_0x0108
                r10 = r0
                java.lang.CharSequence r10 = (java.lang.CharSequence) r10     // Catch:{ all -> 0x0168 }
                if (r10 == 0) goto L_0x004b
                int r10 = r10.length()     // Catch:{ all -> 0x0168 }
                if (r10 != 0) goto L_0x0049
                goto L_0x004b
            L_0x0049:
                r10 = 0
                goto L_0x004c
            L_0x004b:
                r10 = 1
            L_0x004c:
                if (r10 != 0) goto L_0x0108
                java.util.Iterator r9 = r9.iterator()     // Catch:{ all -> 0x0168 }
            L_0x0052:
                boolean r10 = r9.hasNext()     // Catch:{ all -> 0x0168 }
                if (r10 == 0) goto L_0x0108
                java.lang.Object r10 = r9.next()     // Catch:{ all -> 0x0168 }
                com.medscape.android.analytics.remoteconfig.ClinicalAdvancesConfigModel r10 = (com.medscape.android.analytics.remoteconfig.ClinicalAdvancesConfigModel) r10     // Catch:{ all -> 0x0168 }
                java.lang.String r11 = r10.getSpeciality()     // Catch:{ all -> 0x0168 }
                java.lang.CharSequence r11 = (java.lang.CharSequence) r11     // Catch:{ all -> 0x0168 }
                if (r11 == 0) goto L_0x006f
                int r11 = r11.length()     // Catch:{ all -> 0x0168 }
                if (r11 != 0) goto L_0x006d
                goto L_0x006f
            L_0x006d:
                r11 = 0
                goto L_0x0070
            L_0x006f:
                r11 = 1
            L_0x0070:
                if (r11 != 0) goto L_0x0052
                java.lang.String r11 = r10.getSpeciality()     // Catch:{ all -> 0x0168 }
                boolean r11 = kotlin.text.StringsKt.equals(r11, r0, r8)     // Catch:{ all -> 0x0168 }
                if (r11 == 0) goto L_0x0052
                java.lang.String r11 = r10.getUrl()     // Catch:{ all -> 0x0168 }
                java.lang.CharSequence r11 = (java.lang.CharSequence) r11     // Catch:{ all -> 0x0168 }
                if (r11 == 0) goto L_0x008d
                int r11 = r11.length()     // Catch:{ all -> 0x0168 }
                if (r11 != 0) goto L_0x008b
                goto L_0x008d
            L_0x008b:
                r11 = 0
                goto L_0x008e
            L_0x008d:
                r11 = 1
            L_0x008e:
                if (r11 != 0) goto L_0x0052
                java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ all -> 0x0168 }
                r11.<init>()     // Catch:{ all -> 0x0168 }
                r12 = 2131951909(0x7f130125, float:1.9540246E38)
                java.lang.String r12 = r1.getString(r12)     // Catch:{ all -> 0x0168 }
                r11.append(r12)     // Catch:{ all -> 0x0168 }
                java.lang.String r12 = " "
                r11.append(r12)     // Catch:{ all -> 0x0168 }
                r11.append(r0)     // Catch:{ all -> 0x0168 }
                java.lang.String r22 = r11.toString()     // Catch:{ all -> 0x0168 }
                com.medscape.android.landingfeed.model.FeedDataItem r11 = new com.medscape.android.landingfeed.model.FeedDataItem     // Catch:{ all -> 0x0168 }
                r14 = 0
                r15 = 0
                r16 = 0
                r17 = 0
                java.lang.String r18 = "Clinical Advances"
                r19 = 0
                r21 = 0
                java.lang.String r20 = r10.getUrl()     // Catch:{ all -> 0x0168 }
                r23 = 0
                r24 = 0
                r25 = 0
                r26 = 0
                r27 = 0
                r28 = 0
                r29 = 0
                r30 = 0
                r31 = 0
                r32 = 0
                r33 = 0
                r34 = 0
                r35 = 0
                r36 = 0
                r37 = 0
                r38 = 0
                r39 = 0
                r40 = 0
                r41 = 0
                r42 = 0
                r43 = 0
                r44 = 0
                r45 = 0
                r46 = 0
                r47 = 0
                r48 = 0
                r49 = 0
                r50 = 0
                r51 = 0
                r52 = 0
                r53 = -337(0xfffffffffffffeaf, float:NaN)
                r54 = 127(0x7f, float:1.78E-43)
                r55 = 0
                r13 = r11
                r13.<init>(r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27, r28, r29, r30, r31, r32, r33, r34, r35, r36, r37, r38, r39, r40, r41, r42, r43, r44, r45, r46, r47, r48, r49, r50, r51, r52, r53, r54, r55)     // Catch:{ all -> 0x0168 }
                r2.add(r7, r11)     // Catch:{ all -> 0x0168 }
                goto L_0x0052
            L_0x0108:
                com.medscape.android.landingfeed.model.FeedDataItem r0 = new com.medscape.android.landingfeed.model.FeedDataItem     // Catch:{ all -> 0x0168 }
                r57 = 0
                r58 = 0
                r59 = 0
                r60 = 0
                java.lang.String r61 = "Live Events"
                r62 = 0
                java.lang.String r65 = ""
                r64 = 0
                java.lang.String r63 = ""
                r66 = 0
                r67 = 0
                r68 = 0
                r69 = 0
                r70 = 0
                r71 = 0
                r72 = 0
                r73 = 0
                r74 = 0
                r75 = 0
                r76 = 0
                r77 = 0
                r78 = 0
                r79 = 0
                r80 = 0
                r81 = 0
                r82 = 0
                r83 = 0
                r84 = 0
                r85 = 0
                r86 = 0
                r87 = 0
                r88 = 0
                r89 = 0
                r90 = 0
                r91 = 0
                r92 = 0
                r93 = 0
                r94 = 0
                r95 = 0
                r96 = -337(0xfffffffffffffeaf, float:NaN)
                r97 = 127(0x7f, float:1.78E-43)
                r98 = 0
                r56 = r0
                r56.<init>(r57, r58, r59, r60, r61, r62, r63, r64, r65, r66, r67, r68, r69, r70, r71, r72, r73, r74, r75, r76, r77, r78, r79, r80, r81, r82, r83, r84, r85, r86, r87, r88, r89, r90, r91, r92, r93, r94, r95, r96, r97, r98)     // Catch:{ all -> 0x0168 }
                r2.add(r7, r0)     // Catch:{ all -> 0x0168 }
                r0 = 1
                goto L_0x016d
            L_0x0168:
                r0 = move-exception
                r0.printStackTrace()
            L_0x016c:
                r0 = 0
            L_0x016d:
                com.medscape.android.analytics.remoteconfig.feed.FeedConfigManager r9 = new com.medscape.android.analytics.remoteconfig.feed.FeedConfigManager
                r9.<init>()
                java.util.ArrayList r9 = r9.getFeedData()
                r10 = r99
                com.medscape.android.landingfeed.repository.FeedRepository$Companion r10 = (com.medscape.android.landingfeed.repository.FeedRepository.Companion) r10
                int r11 = r103.getFeedType()
                java.util.List r9 = r10.getFeedItemsByType(r11, r9)
                com.medscape.android.landingfeed.repository.FeedRepository.feedItems = r9
                com.medscape.android.landingfeed.model.FeedDataItem r9 = r10.getDataUpdateItem()
                java.util.List r10 = com.medscape.android.landingfeed.repository.FeedRepository.feedItems
                if (r10 == 0) goto L_0x01b7
                java.lang.Iterable r10 = (java.lang.Iterable) r10
                java.util.Iterator r10 = r10.iterator()
            L_0x0195:
                boolean r11 = r10.hasNext()
                if (r11 == 0) goto L_0x01ad
                java.lang.Object r11 = r10.next()
                r12 = r11
                com.medscape.android.landingfeed.model.FeedDataItem r12 = (com.medscape.android.landingfeed.model.FeedDataItem) r12
                java.lang.String r12 = r12.getType()
                boolean r12 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r12, (java.lang.Object) r5)
                if (r12 == 0) goto L_0x0195
                goto L_0x01ae
            L_0x01ad:
                r11 = 0
            L_0x01ae:
                com.medscape.android.landingfeed.model.FeedDataItem r11 = (com.medscape.android.landingfeed.model.FeedDataItem) r11
                if (r11 == 0) goto L_0x01b7
                java.lang.Boolean r10 = r11.getEnabled()
                goto L_0x01b8
            L_0x01b7:
                r10 = 0
            L_0x01b8:
                java.lang.Boolean r11 = java.lang.Boolean.valueOf(r8)
                boolean r10 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r10, (java.lang.Object) r11)
                if (r10 == 0) goto L_0x01c6
                if (r9 == 0) goto L_0x01c6
                r10 = 1
                goto L_0x01c7
            L_0x01c6:
                r10 = 0
            L_0x01c7:
                java.util.List r11 = com.medscape.android.landingfeed.repository.FeedRepository.feedItems
                if (r11 == 0) goto L_0x030d
                java.lang.Iterable r11 = (java.lang.Iterable) r11
                java.util.Iterator r11 = r11.iterator()
            L_0x01d3:
                boolean r12 = r11.hasNext()
                if (r12 == 0) goto L_0x030b
                java.lang.Object r12 = r11.next()
                com.medscape.android.landingfeed.model.FeedDataItem r12 = (com.medscape.android.landingfeed.model.FeedDataItem) r12
                java.util.ArrayList r13 = r12.getConfigItems()
                if (r13 == 0) goto L_0x01f6
                java.lang.Object r13 = r13.get(r7)
                com.medscape.android.analytics.remoteconfig.feed.FeedConfigModel r13 = (com.medscape.android.analytics.remoteconfig.feed.FeedConfigModel) r13
                if (r13 == 0) goto L_0x01f6
                int r13 = r13.getPosition()
                java.lang.Integer r13 = java.lang.Integer.valueOf(r13)
                goto L_0x01f7
            L_0x01f6:
                r13 = 0
            L_0x01f7:
                if (r13 == 0) goto L_0x026a
                int r14 = r13.intValue()
                if (r14 <= 0) goto L_0x0209
                int r13 = r13.intValue()
                int r13 = r13 - r8
                java.lang.Integer r13 = java.lang.Integer.valueOf(r13)
                goto L_0x020d
            L_0x0209:
                java.lang.Integer r13 = java.lang.Integer.valueOf(r7)
            L_0x020d:
                if (r0 == 0) goto L_0x0218
                int r13 = r13.intValue()
                int r13 = r13 + r8
                java.lang.Integer r13 = java.lang.Integer.valueOf(r13)
            L_0x0218:
                java.lang.String r14 = r12.getType()
                boolean r14 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r14, (java.lang.Object) r5)
                if (r14 == 0) goto L_0x022f
                if (r10 == 0) goto L_0x026a
                int r13 = r13.intValue()
                kotlin.jvm.internal.Intrinsics.checkNotNull(r9)
                r2.add(r13, r9)
                goto L_0x026a
            L_0x022f:
                java.lang.String r14 = r12.getType()
                java.lang.String r15 = "invitation-generic"
                boolean r14 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r14, (java.lang.Object) r15)
                if (r14 == 0) goto L_0x0263
                com.medscape.android.myinvites.MyInvitationsManager$Companion r14 = com.medscape.android.myinvites.MyInvitationsManager.Companion
                r15 = r1
                android.content.Context r15 = (android.content.Context) r15
                com.medscape.android.myinvites.MyInvitationsManager r14 = r14.get(r15)
                java.lang.Integer r14 = r14.getOpenInvitations()
                if (r14 == 0) goto L_0x024f
                int r14 = r14.intValue()
                goto L_0x0250
            L_0x024f:
                r14 = 0
            L_0x0250:
                if (r14 <= 0) goto L_0x026a
                com.medscape.android.landingfeed.repository.FeedRepository$Companion r14 = com.medscape.android.landingfeed.repository.FeedRepository.Companion
                java.lang.String r14 = r14.generateInvitationTitle(r12, r15)
                r12.setTitle(r14)
                int r13 = r13.intValue()
                r2.add(r13, r12)
                goto L_0x026a
            L_0x0263:
                int r13 = r13.intValue()
                r2.add(r13, r12)
            L_0x026a:
                java.util.ArrayList r13 = r12.getConfigItems()
                if (r13 == 0) goto L_0x02a6
                java.lang.Iterable r13 = (java.lang.Iterable) r13
                java.util.Iterator r13 = r13.iterator()
            L_0x0276:
                boolean r14 = r13.hasNext()
                if (r14 == 0) goto L_0x02a2
                java.lang.Object r14 = r13.next()
                r15 = r14
                com.medscape.android.analytics.remoteconfig.feed.FeedConfigModel r15 = (com.medscape.android.analytics.remoteconfig.feed.FeedConfigModel) r15
                java.lang.String r15 = r15.getFeedType()
                java.util.Map r6 = com.medscape.android.landingfeed.repository.FeedRepository.typeMap
                int r17 = r103.getFeedType()
                java.lang.Integer r7 = java.lang.Integer.valueOf(r17)
                java.lang.Object r6 = r6.get(r7)
                java.lang.String r6 = (java.lang.String) r6
                boolean r6 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r15, (java.lang.Object) r6)
                if (r6 == 0) goto L_0x02a0
                goto L_0x02a3
            L_0x02a0:
                r7 = 0
                goto L_0x0276
            L_0x02a2:
                r14 = 0
            L_0x02a3:
                com.medscape.android.analytics.remoteconfig.feed.FeedConfigModel r14 = (com.medscape.android.analytics.remoteconfig.feed.FeedConfigModel) r14
                goto L_0x02a7
            L_0x02a6:
                r14 = 0
            L_0x02a7:
                if (r14 == 0) goto L_0x0308
                int r6 = r14.getRepeatEvery()
                if (r6 <= r8) goto L_0x0304
                java.util.ArrayList r6 = r12.getConfigItems()
                if (r6 == 0) goto L_0x02e9
                java.lang.Iterable r6 = (java.lang.Iterable) r6
                java.util.Iterator r6 = r6.iterator()
            L_0x02bb:
                boolean r7 = r6.hasNext()
                if (r7 == 0) goto L_0x02e5
                java.lang.Object r7 = r6.next()
                r12 = r7
                com.medscape.android.analytics.remoteconfig.feed.FeedConfigModel r12 = (com.medscape.android.analytics.remoteconfig.feed.FeedConfigModel) r12
                java.lang.String r12 = r12.getFeedType()
                java.util.Map r13 = com.medscape.android.landingfeed.repository.FeedRepository.typeMap
                int r15 = r103.getFeedType()
                java.lang.Integer r15 = java.lang.Integer.valueOf(r15)
                java.lang.Object r13 = r13.get(r15)
                java.lang.String r13 = (java.lang.String) r13
                boolean r12 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r12, (java.lang.Object) r13)
                if (r12 == 0) goto L_0x02bb
                goto L_0x02e6
            L_0x02e5:
                r7 = 0
            L_0x02e6:
                com.medscape.android.analytics.remoteconfig.feed.FeedConfigModel r7 = (com.medscape.android.analytics.remoteconfig.feed.FeedConfigModel) r7
                goto L_0x02ea
            L_0x02e9:
                r7 = 0
            L_0x02ea:
                if (r7 == 0) goto L_0x0304
                int r6 = r14.getPosition()
                if (r6 <= 0) goto L_0x02f5
                int r6 = r6 + -1
                goto L_0x02f6
            L_0x02f5:
                r6 = 0
            L_0x02f6:
                if (r0 == 0) goto L_0x02fa
                int r6 = r6 + 1
            L_0x02fa:
                int r12 = r14.getRepeatEvery()
                int r6 = r6 + r12
                r7.setNextRepeatPosition(r6)
                kotlin.Unit r6 = kotlin.Unit.INSTANCE
            L_0x0304:
                kotlin.Unit r6 = kotlin.Unit.INSTANCE
                kotlin.Unit r6 = kotlin.Unit.INSTANCE
            L_0x0308:
                r7 = 0
                goto L_0x01d3
            L_0x030b:
                kotlin.Unit r0 = kotlin.Unit.INSTANCE
            L_0x030d:
                r0 = r99
                com.medscape.android.landingfeed.repository.FeedRepository$Companion r0 = (com.medscape.android.landingfeed.repository.FeedRepository.Companion) r0
                com.medscape.android.landingfeed.model.FeedDataItem r6 = r0.getDataUpdateItem()
                java.util.List r7 = com.medscape.android.landingfeed.repository.FeedRepository.feedItems
                if (r7 == 0) goto L_0x0343
                java.lang.Iterable r7 = (java.lang.Iterable) r7
                java.util.Iterator r7 = r7.iterator()
            L_0x0321:
                boolean r9 = r7.hasNext()
                if (r9 == 0) goto L_0x0339
                java.lang.Object r9 = r7.next()
                r10 = r9
                com.medscape.android.landingfeed.model.FeedDataItem r10 = (com.medscape.android.landingfeed.model.FeedDataItem) r10
                java.lang.String r10 = r10.getType()
                boolean r10 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r10, (java.lang.Object) r5)
                if (r10 == 0) goto L_0x0321
                goto L_0x033a
            L_0x0339:
                r9 = 0
            L_0x033a:
                com.medscape.android.landingfeed.model.FeedDataItem r9 = (com.medscape.android.landingfeed.model.FeedDataItem) r9
                if (r9 == 0) goto L_0x0343
                java.lang.Boolean r7 = r9.getEnabled()
                goto L_0x0344
            L_0x0343:
                r7 = 0
            L_0x0344:
                java.lang.Boolean r9 = java.lang.Boolean.valueOf(r8)
                boolean r7 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r7, (java.lang.Object) r9)
                if (r7 == 0) goto L_0x0352
                if (r6 == 0) goto L_0x0352
                r7 = 1
                goto L_0x0353
            L_0x0352:
                r7 = 0
            L_0x0353:
                java.util.List r9 = com.medscape.android.landingfeed.repository.FeedRepository.feedItems
                if (r9 == 0) goto L_0x03d0
                java.lang.Iterable r9 = (java.lang.Iterable) r9
                java.util.Iterator r9 = r9.iterator()
            L_0x035f:
                boolean r10 = r9.hasNext()
                if (r10 == 0) goto L_0x03ce
                java.lang.Object r10 = r9.next()
                com.medscape.android.landingfeed.model.FeedDataItem r10 = (com.medscape.android.landingfeed.model.FeedDataItem) r10
                java.util.ArrayList r11 = r10.getConfigItems()
                if (r11 == 0) goto L_0x035f
                java.lang.Iterable r11 = (java.lang.Iterable) r11
                java.util.Iterator r11 = r11.iterator()
            L_0x0377:
                boolean r12 = r11.hasNext()
                if (r12 == 0) goto L_0x03cb
                java.lang.Object r12 = r11.next()
                com.medscape.android.analytics.remoteconfig.feed.FeedConfigModel r12 = (com.medscape.android.analytics.remoteconfig.feed.FeedConfigModel) r12
                int r13 = r12.getRepeatEvery()
                if (r13 <= r8) goto L_0x0377
            L_0x0389:
                int r13 = r12.getNextRepeatPosition()
                int r14 = r101.size()
                if (r13 >= r14) goto L_0x03be
                java.lang.String r13 = r10.getType()
                boolean r13 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r13, (java.lang.Object) r5)
                if (r13 == 0) goto L_0x03aa
                if (r7 == 0) goto L_0x03b1
                int r13 = r12.getNextRepeatPosition()
                kotlin.jvm.internal.Intrinsics.checkNotNull(r6)
                r2.add(r13, r6)
                goto L_0x03b1
            L_0x03aa:
                int r13 = r12.getNextRepeatPosition()
                r2.add(r13, r10)
            L_0x03b1:
                int r13 = r12.getNextRepeatPosition()
                int r14 = r12.getRepeatEvery()
                int r13 = r13 + r14
                r12.setNextRepeatPosition(r13)
                goto L_0x0389
            L_0x03be:
                int r13 = r12.getNextRepeatPosition()
                int r14 = r101.size()
                int r13 = r13 - r14
                r12.setNextRepeatPosition(r13)
                goto L_0x0377
            L_0x03cb:
                kotlin.Unit r10 = kotlin.Unit.INSTANCE
                goto L_0x035f
            L_0x03ce:
                kotlin.Unit r5 = kotlin.Unit.INSTANCE
            L_0x03d0:
                int r5 = r103.getFeedType()
                boolean r5 = r0.isFeedADEligible(r5)
                if (r5 == 0) goto L_0x0430
                int r5 = r101.size()
                int r6 = r102.getPositionStart()
                if (r5 <= r6) goto L_0x0430
                int r5 = r102.getPositionStart()
                java.lang.Object r5 = r2.get(r5)
                com.medscape.android.landingfeed.model.FeedDataItem r5 = (com.medscape.android.landingfeed.model.FeedDataItem) r5
                java.lang.String r5 = r5.getTitle()
                r6 = 2
                java.lang.String r7 = "ADVERTISEMENT"
                r9 = 0
                r10 = 0
                boolean r5 = kotlin.text.StringsKt.equals$default(r5, r7, r10, r6, r9)
                if (r5 != 0) goto L_0x0409
                int r5 = r102.getPositionStart()
                com.medscape.android.landingfeed.model.FeedAdItem r6 = new com.medscape.android.landingfeed.model.FeedAdItem
                r6.<init>(r7)
                r2.add(r5, r6)
            L_0x0409:
                int r5 = r102.getPositionStart()
                int r6 = r102.getPositionEvery()
                int r5 = r5 + r6
                int r5 = r5 + r8
            L_0x0413:
                int r6 = r101.size()
                if (r5 >= r6) goto L_0x0431
                java.lang.Object r6 = r2.get(r5)
                boolean r6 = r6 instanceof com.medscape.android.landingfeed.model.FeedAdItem
                if (r6 != 0) goto L_0x0429
                com.medscape.android.landingfeed.model.FeedAdItem r6 = new com.medscape.android.landingfeed.model.FeedAdItem
                r6.<init>(r7)
                r2.add(r5, r6)
            L_0x0429:
                int r6 = r102.getPositionEvery()
                int r6 = r6 + r8
                int r5 = r5 + r6
                goto L_0x0413
            L_0x0430:
                r9 = 0
            L_0x0431:
                com.medscape.android.myinvites.MyInvitationsManager$Companion r5 = com.medscape.android.myinvites.MyInvitationsManager.Companion
                r6 = r1
                android.content.Context r6 = (android.content.Context) r6
                com.medscape.android.myinvites.MyInvitationsManager r5 = r5.get(r6)
                java.util.List r5 = r5.getSpecificInvitations()
                if (r5 == 0) goto L_0x05f4
                r6 = r5
                java.util.Collection r6 = (java.util.Collection) r6
                boolean r6 = r6.isEmpty()
                r6 = r6 ^ r8
                if (r6 == 0) goto L_0x05f2
                int r6 = com.medscape.android.landingfeed.repository.FeedRepository.specificInvitationPos
                int r7 = r5.size()
                if (r6 >= r7) goto L_0x05f2
                java.util.List r4 = r102.getSpecialAds()
                if (r4 == 0) goto L_0x05f2
                java.lang.Iterable r4 = (java.lang.Iterable) r4
                java.util.Iterator r4 = r4.iterator()
            L_0x0460:
                boolean r6 = r4.hasNext()
                if (r6 == 0) goto L_0x05f0
                java.lang.Object r6 = r4.next()
                com.medscape.android.analytics.remoteconfig.AdRemoteConfigModel$SpecialAd r6 = (com.medscape.android.analytics.remoteconfig.AdRemoteConfigModel.SpecialAd) r6
                java.lang.Boolean r7 = r6.getEnabled()
                java.lang.Boolean r10 = java.lang.Boolean.valueOf(r8)
                boolean r7 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r7, (java.lang.Object) r10)
                if (r7 == 0) goto L_0x05eb
                java.util.List r7 = r6.getConfigs()
                if (r7 == 0) goto L_0x05eb
                java.lang.Iterable r7 = (java.lang.Iterable) r7
                java.util.Iterator r7 = r7.iterator()
            L_0x0486:
                boolean r10 = r7.hasNext()
                if (r10 == 0) goto L_0x04b0
                java.lang.Object r10 = r7.next()
                r11 = r10
                com.medscape.android.analytics.remoteconfig.AdRemoteConfigModel$Config r11 = (com.medscape.android.analytics.remoteconfig.AdRemoteConfigModel.Config) r11
                java.lang.String r11 = r11.getFeedType()
                java.util.Map r12 = com.medscape.android.landingfeed.repository.FeedRepository.typeMap
                int r13 = r103.getFeedType()
                java.lang.Integer r13 = java.lang.Integer.valueOf(r13)
                java.lang.Object r12 = r12.get(r13)
                java.lang.String r12 = (java.lang.String) r12
                boolean r11 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r11, (java.lang.Object) r12)
                if (r11 == 0) goto L_0x0486
                goto L_0x04b1
            L_0x04b0:
                r10 = r9
            L_0x04b1:
                com.medscape.android.analytics.remoteconfig.AdRemoteConfigModel$Config r10 = (com.medscape.android.analytics.remoteconfig.AdRemoteConfigModel.Config) r10
                if (r10 == 0) goto L_0x05eb
                java.lang.Integer r7 = r10.getPositionStart()
                if (r7 == 0) goto L_0x05eb
                java.lang.Number r7 = (java.lang.Number) r7
                int r7 = r7.intValue()
                int r11 = r101.size()
                if (r11 <= r7) goto L_0x0544
                int r11 = com.medscape.android.landingfeed.repository.FeedRepository.specificInvitationPos
                java.lang.Object r11 = r5.get(r11)
                com.medscape.android.myinvites.specific.Invitation r11 = (com.medscape.android.myinvites.specific.Invitation) r11
                com.medscape.android.landingfeed.model.FeedDataItem r15 = new com.medscape.android.landingfeed.model.FeedDataItem
                r12 = r15
                r13 = 0
                r14 = 0
                r16 = 0
                r9 = r15
                r15 = r16
                java.lang.String r21 = r11.getTitle()
                r18 = 0
                java.lang.String r17 = r6.getType()
                r20 = 0
                java.lang.String r28 = r6.getCategory()
                java.lang.String r19 = r11.getDestUrl()
                r23 = 0
                r24 = 0
                r25 = 0
                r26 = 0
                r27 = 0
                java.lang.String r43 = r11.getDescription()
                r29 = 0
                r30 = 0
                r31 = 0
                r32 = 0
                r33 = 0
                r34 = 0
                r35 = 0
                r36 = 0
                r37 = 0
                r38 = 0
                r39 = 0
                r40 = 0
                r41 = 0
                r42 = 0
                java.lang.String r22 = r11.getInfo()
                r44 = 0
                r45 = 0
                r46 = 0
                r47 = 0
                r48 = 0
                r49 = 0
                r50 = 0
                java.lang.String r51 = r11.getCta()
                r52 = -1073775441(0xffffffffbfff7caf, float:-1.9959925)
                r53 = 63
                r54 = 0
                r12.<init>(r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27, r28, r29, r30, r31, r32, r33, r34, r35, r36, r37, r38, r39, r40, r41, r42, r43, r44, r45, r46, r47, r48, r49, r50, r51, r52, r53, r54)
                r2.add(r7, r9)
                int r9 = com.medscape.android.landingfeed.repository.FeedRepository.specificInvitationPos
                int r9 = r9 + r8
                com.medscape.android.landingfeed.repository.FeedRepository.specificInvitationPos = r9
            L_0x0544:
                java.lang.Integer r9 = r10.getPositionEvery()
                if (r9 == 0) goto L_0x05eb
                java.lang.Number r9 = (java.lang.Number) r9
                int r9 = r9.intValue()
                int r7 = r7 + r9
                int r7 = r7 + r8
            L_0x0552:
                int r10 = r101.size()
                if (r7 >= r10) goto L_0x05e7
                int r10 = com.medscape.android.landingfeed.repository.FeedRepository.specificInvitationPos
                int r11 = r5.size()
                if (r10 >= r11) goto L_0x05e7
                int r10 = com.medscape.android.landingfeed.repository.FeedRepository.specificInvitationPos
                java.lang.Object r10 = r5.get(r10)
                com.medscape.android.myinvites.specific.Invitation r10 = (com.medscape.android.myinvites.specific.Invitation) r10
                com.medscape.android.landingfeed.model.FeedDataItem r15 = new com.medscape.android.landingfeed.model.FeedDataItem
                r11 = r15
                r12 = 0
                r13 = 0
                r14 = 0
                r16 = 0
                r8 = r15
                r15 = r16
                java.lang.String r20 = r10.getTitle()
                r17 = 0
                java.lang.String r16 = r6.getType()
                r19 = 0
                java.lang.String r27 = r6.getCategory()
                java.lang.String r18 = r10.getDestUrl()
                r22 = 0
                r23 = 0
                r24 = 0
                r25 = 0
                r26 = 0
                java.lang.String r42 = r10.getDescription()
                r28 = 0
                r29 = 0
                r30 = 0
                r31 = 0
                r32 = 0
                r33 = 0
                r34 = 0
                r35 = 0
                r36 = 0
                r37 = 0
                r38 = 0
                r39 = 0
                r40 = 0
                r41 = 0
                java.lang.String r21 = r10.getInfo()
                r43 = 0
                r44 = 0
                r45 = 0
                r46 = 0
                r47 = 0
                r48 = 0
                r49 = 0
                java.lang.String r50 = r10.getCta()
                r51 = -1073775441(0xffffffffbfff7caf, float:-1.9959925)
                r52 = 63
                r53 = 0
                r11.<init>(r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27, r28, r29, r30, r31, r32, r33, r34, r35, r36, r37, r38, r39, r40, r41, r42, r43, r44, r45, r46, r47, r48, r49, r50, r51, r52, r53)
                r2.add(r7, r8)
                int r8 = com.medscape.android.landingfeed.repository.FeedRepository.specificInvitationPos
                r10 = 1
                int r8 = r8 + r10
                com.medscape.android.landingfeed.repository.FeedRepository.specificInvitationPos = r8
                int r8 = r9 + 1
                int r7 = r7 + r8
                r8 = 1
                goto L_0x0552
            L_0x05e7:
                r10 = 1
                kotlin.Unit r6 = kotlin.Unit.INSTANCE
                goto L_0x05ec
            L_0x05eb:
                r10 = 1
            L_0x05ec:
                r8 = 1
                r9 = 0
                goto L_0x0460
            L_0x05f0:
                kotlin.Unit r4 = kotlin.Unit.INSTANCE
            L_0x05f2:
                kotlin.Unit r4 = kotlin.Unit.INSTANCE
            L_0x05f4:
                int r4 = r101.size()
                if (r4 <= 0) goto L_0x05fd
                r0.setupItems(r1, r2, r3)
            L_0x05fd:
                kotlin.jvm.internal.Intrinsics.checkNotNull(r101)
                return r2
            */
            throw new UnsupportedOperationException("Method not decompiled: com.medscape.android.landingfeed.repository.FeedRepository.Companion.insertExtras(androidx.fragment.app.FragmentActivity, java.util.List, com.medscape.android.analytics.remoteconfig.AdRemoteConfigModel, com.medscape.android.landingfeed.viewmodel.LandingFeedViewModel, boolean):java.util.List");
        }

        public final String generateInvitationTitle(FeedDataItem feedDataItem, Context context) {
            Intrinsics.checkNotNullParameter(feedDataItem, ContentParser.ITEM);
            Intrinsics.checkNotNullParameter(context, "context");
            CharSequence customTitle = feedDataItem.getCustomTitle();
            if (customTitle == null || customTitle.length() == 0) {
                return parseGenericTitle(feedDataItem.getTitle(), context);
            }
            return parseGenericTitle(feedDataItem.getCustomTitle(), context);
        }

        private final String parseGenericTitle(String str, Context context) {
            String setting = Settings.singleton(context).getSetting(Constants.USER_DISPLAYNAME, "Doctor");
            Integer openInvitations = MyInvitationsManager.Companion.get(context).getOpenInvitations();
            int intValue = openInvitations != null ? openInvitations.intValue() : 0;
            String quantityString = context.getResources().getQuantityString(R.plurals.home_invitation_count, intValue, new Object[]{Integer.valueOf(intValue)});
            Intrinsics.checkNotNullExpressionValue(quantityString, "context.resources.getQua…tion_count, count, count)");
            if (str == null) {
                return str;
            }
            CharSequence charSequence = str;
            if (!StringsKt.contains(charSequence, (CharSequence) "%name", true) || !StringsKt.contains$default(charSequence, (CharSequence) "%invitations_count_phrase", false, 2, (Object) null)) {
                return str;
            }
            Intrinsics.checkNotNullExpressionValue(setting, "name");
            return StringsKt.replace$default(StringsKt.replace$default(str, "%name", setting, false, 4, (Object) null), "%invitations_count_phrase", quantityString, false, 4, (Object) null);
        }

        /* JADX WARNING: Code restructure failed: missing block: B:15:0x0050, code lost:
            if ((r2 == null || r2.isEmpty()) == false) goto L_0x0054;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final java.util.List<com.medscape.android.landingfeed.model.FeedDataItem> getFeedItemsByType(int r7, java.util.ArrayList<com.medscape.android.landingfeed.model.FeedDataItem> r8) {
            /*
                r6 = this;
                if (r8 == 0) goto L_0x0072
                java.lang.Iterable r8 = (java.lang.Iterable) r8
                java.util.ArrayList r0 = new java.util.ArrayList
                r0.<init>()
                java.util.Collection r0 = (java.util.Collection) r0
                java.util.Iterator r8 = r8.iterator()
            L_0x000f:
                boolean r1 = r8.hasNext()
                if (r1 == 0) goto L_0x005a
                java.lang.Object r1 = r8.next()
                r2 = r1
                com.medscape.android.landingfeed.model.FeedDataItem r2 = (com.medscape.android.landingfeed.model.FeedDataItem) r2
                java.util.ArrayList r3 = r2.getConfigItems()
                if (r3 == 0) goto L_0x002e
                java.util.List r3 = (java.util.List) r3
                com.medscape.android.landingfeed.repository.FeedRepository$Companion$getFeedItemsByType$$inlined$filter$lambda$1 r4 = new com.medscape.android.landingfeed.repository.FeedRepository$Companion$getFeedItemsByType$$inlined$filter$lambda$1
                r4.<init>(r7)
                kotlin.jvm.functions.Function1 r4 = (kotlin.jvm.functions.Function1) r4
                kotlin.collections.CollectionsKt.removeAll(r3, r4)
            L_0x002e:
                java.lang.Boolean r3 = r2.getEnabled()
                r4 = 1
                java.lang.Boolean r5 = java.lang.Boolean.valueOf(r4)
                boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r3, (java.lang.Object) r5)
                r5 = 0
                if (r3 == 0) goto L_0x0053
                java.util.ArrayList r2 = r2.getConfigItems()
                java.util.Collection r2 = (java.util.Collection) r2
                if (r2 == 0) goto L_0x004f
                boolean r2 = r2.isEmpty()
                if (r2 == 0) goto L_0x004d
                goto L_0x004f
            L_0x004d:
                r2 = 0
                goto L_0x0050
            L_0x004f:
                r2 = 1
            L_0x0050:
                if (r2 != 0) goto L_0x0053
                goto L_0x0054
            L_0x0053:
                r4 = 0
            L_0x0054:
                if (r4 == 0) goto L_0x000f
                r0.add(r1)
                goto L_0x000f
            L_0x005a:
                java.util.List r0 = (java.util.List) r0
                java.lang.Iterable r0 = (java.lang.Iterable) r0
                java.util.Comparator r7 = kotlin.comparisons.ComparisonsKt.naturalOrder()
                java.util.Comparator r7 = kotlin.comparisons.ComparisonsKt.nullsLast(r7)
                com.medscape.android.landingfeed.repository.FeedRepository$Companion$getFeedItemsByType$$inlined$compareBy$1 r8 = new com.medscape.android.landingfeed.repository.FeedRepository$Companion$getFeedItemsByType$$inlined$compareBy$1
                r8.<init>(r7)
                java.util.Comparator r8 = (java.util.Comparator) r8
                java.util.List r7 = kotlin.collections.CollectionsKt.sortedWith(r0, r8)
                goto L_0x0073
            L_0x0072:
                r7 = 0
            L_0x0073:
                return r7
            */
            throw new UnsupportedOperationException("Method not decompiled: com.medscape.android.landingfeed.repository.FeedRepository.Companion.getFeedItemsByType(int, java.util.ArrayList):java.util.List");
        }

        /* JADX WARNING: Code restructure failed: missing block: B:21:0x0057, code lost:
            if (r2 == false) goto L_0x0059;
         */
        /* JADX WARNING: Removed duplicated region for block: B:16:0x004a A[Catch:{ all -> 0x00bf }] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private final com.medscape.android.landingfeed.model.FeedDataItem getDataUpdateItem() {
            /*
                r51 = this;
                java.lang.String r0 = "pref_update_time"
                r1 = 0
                com.medscape.android.provider.SharedPreferenceProvider r2 = com.medscape.android.provider.SharedPreferenceProvider.get()     // Catch:{ all -> 0x00bf }
                r3 = 0
                long r5 = r2.get((java.lang.String) r0, (long) r3)     // Catch:{ all -> 0x00bf }
                int r2 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
                if (r2 == 0) goto L_0x00bf
                long r2 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x00bf }
                long r2 = r2 - r5
                java.util.concurrent.TimeUnit r4 = java.util.concurrent.TimeUnit.DAYS     // Catch:{ all -> 0x00bf }
                r5 = 3
                long r4 = r4.toMillis(r5)     // Catch:{ all -> 0x00bf }
                java.lang.String r6 = "pref_update_msg"
                java.lang.String r7 = "pref_update_title"
                int r8 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
                if (r8 >= 0) goto L_0x00aa
                com.medscape.android.provider.SharedPreferenceProvider r0 = com.medscape.android.provider.SharedPreferenceProvider.get()     // Catch:{ all -> 0x00bf }
                java.lang.String r17 = r0.get((java.lang.String) r7, (java.lang.String) r1)     // Catch:{ all -> 0x00bf }
                com.medscape.android.provider.SharedPreferenceProvider r0 = com.medscape.android.provider.SharedPreferenceProvider.get()     // Catch:{ all -> 0x00bf }
                java.lang.String r18 = r0.get((java.lang.String) r6, (java.lang.String) r1)     // Catch:{ all -> 0x00bf }
                r0 = r17
                java.lang.CharSequence r0 = (java.lang.CharSequence) r0     // Catch:{ all -> 0x00bf }
                r2 = 0
                r3 = 1
                if (r0 == 0) goto L_0x0047
                boolean r0 = kotlin.text.StringsKt.isBlank(r0)     // Catch:{ all -> 0x00bf }
                if (r0 == 0) goto L_0x0045
                goto L_0x0047
            L_0x0045:
                r0 = 0
                goto L_0x0048
            L_0x0047:
                r0 = 1
            L_0x0048:
                if (r0 == 0) goto L_0x0059
                r0 = r18
                java.lang.CharSequence r0 = (java.lang.CharSequence) r0     // Catch:{ all -> 0x00bf }
                if (r0 == 0) goto L_0x0056
                boolean r0 = kotlin.text.StringsKt.isBlank(r0)     // Catch:{ all -> 0x00bf }
                if (r0 == 0) goto L_0x0057
            L_0x0056:
                r2 = 1
            L_0x0057:
                if (r2 != 0) goto L_0x00bf
            L_0x0059:
                com.medscape.android.landingfeed.model.FeedDataItem r0 = new com.medscape.android.landingfeed.model.FeedDataItem     // Catch:{ all -> 0x00bf }
                r9 = 0
                r10 = 0
                r11 = 0
                r12 = 0
                java.lang.String r13 = "dataUpdate"
                r14 = 0
                r15 = 0
                r16 = 0
                r19 = 0
                r20 = 0
                r21 = 0
                r22 = 0
                r23 = 0
                r24 = 0
                r25 = 0
                r26 = 0
                r27 = 0
                r28 = 0
                r29 = 0
                r30 = 0
                r31 = 0
                r32 = 0
                r33 = 0
                r34 = 0
                r35 = 0
                r36 = 0
                r37 = 0
                r38 = 0
                r39 = 0
                r40 = 0
                r41 = 0
                r42 = 0
                r43 = 0
                r44 = 0
                r45 = 0
                r46 = 0
                r47 = 0
                r48 = -785(0xfffffffffffffcef, float:NaN)
                r49 = 127(0x7f, float:1.78E-43)
                r50 = 0
                r8 = r0
                r8.<init>(r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27, r28, r29, r30, r31, r32, r33, r34, r35, r36, r37, r38, r39, r40, r41, r42, r43, r44, r45, r46, r47, r48, r49, r50)     // Catch:{ all -> 0x00bf }
                return r0
            L_0x00aa:
                com.medscape.android.provider.SharedPreferenceProvider r2 = com.medscape.android.provider.SharedPreferenceProvider.get()     // Catch:{ all -> 0x00bf }
                r2.remove(r0)     // Catch:{ all -> 0x00bf }
                com.medscape.android.provider.SharedPreferenceProvider r0 = com.medscape.android.provider.SharedPreferenceProvider.get()     // Catch:{ all -> 0x00bf }
                r0.remove(r7)     // Catch:{ all -> 0x00bf }
                com.medscape.android.provider.SharedPreferenceProvider r0 = com.medscape.android.provider.SharedPreferenceProvider.get()     // Catch:{ all -> 0x00bf }
                r0.remove(r6)     // Catch:{ all -> 0x00bf }
            L_0x00bf:
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.medscape.android.landingfeed.repository.FeedRepository.Companion.getDataUpdateItem():com.medscape.android.landingfeed.model.FeedDataItem");
        }

        private final void setupItems(FragmentActivity fragmentActivity, List<FeedDataItem> list, LandingFeedViewModel landingFeedViewModel) {
            String str;
            for (FeedDataItem next : list) {
                String type = next.getType();
                boolean z = true;
                if (type != null) {
                    CharSequence url = next.getUrl();
                    if (url == null || StringsKt.isBlank(url)) {
                        CharSequence contentId = next.getContentId();
                        if (!(contentId == null || StringsKt.isBlank(contentId))) {
                            int hashCode = type.hashCode();
                            if (hashCode != 98619) {
                                if (hashCode != 3377875) {
                                    if (hashCode == 951516140 && type.equals("consult")) {
                                        str = "https://www.medscape.com/consult/post?id=" + next.getContentId();
                                        next.setUrl(str);
                                    }
                                } else if (type.equals("news")) {
                                    str = WebviewUtil.Companion.getNewsUrl(next.getContentId());
                                    next.setUrl(str);
                                }
                            } else if (type.equals(FeedConstants.CME_ITEM)) {
                                str = Utilities.getCMEUrl(fragmentActivity, next.getContentId());
                                next.setUrl(str);
                            }
                            str = "";
                            next.setUrl(str);
                        }
                    }
                    if (Intrinsics.areEqual((Object) type, (Object) "news")) {
                        CharSequence publishDate = next.getPublishDate();
                        if (!(publishDate == null || publishDate.length() == 0)) {
                            if (next.getPublicationName() != null) {
                                String publicationName = next.getPublicationName();
                                Intrinsics.checkNotNull(publicationName);
                                if (!StringsKt.contains$default((CharSequence) publicationName, (CharSequence) "|", false, 2, (Object) null)) {
                                    next.setPublicationName(next.getPublicationName() + " | " + DateUtils.Companion.getDateWithMonth(next.getPublishDate()));
                                }
                            }
                            next.setPublicationName(DateUtils.Companion.getDateWithMonth(next.getPublishDate()));
                        }
                    }
                }
                CharSequence byLine = next.getByLine();
                if (byLine == null || byLine.length() == 0) {
                    next.setByLine(next.getChiefEditor());
                }
                if (next.getUrl() != null) {
                    String url2 = next.getUrl();
                    next.setUrl(url2 != null ? StringsKt.replace$default(url2, "http:", "https:", false, 4, (Object) null) : null);
                }
                landingFeedViewModel.updateSavedState(next, fragmentActivity);
                if (next.getUrl() != null) {
                    String url3 = next.getUrl();
                    Intrinsics.checkNotNull(url3);
                    if (StringsKt.contains$default((CharSequence) url3, (CharSequence) RecentlyViewedSuggestionHelper.TYPE_MEDLINE, false, 2, (Object) null)) {
                        next.setMedlineArticle(Boolean.valueOf(z));
                    }
                }
                z = false;
                next.setMedlineArticle(Boolean.valueOf(z));
            }
        }
    }
}
