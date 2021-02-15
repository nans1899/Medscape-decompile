package com.google.zxing.maxicode.decoder;

import androidx.core.app.FrameMetricsAggregator;
import androidx.core.view.InputDeviceCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import com.comscore.streaming.AdvertisementType;
import com.comscore.streaming.ContentDeliveryAdvertisementCapability;
import com.comscore.streaming.ContentDeliveryComposition;
import com.comscore.streaming.ContentDeliveryMode;
import com.comscore.streaming.ContentDeliverySubscriptionType;
import com.comscore.streaming.ContentFeedType;
import com.comscore.streaming.WindowState;
import com.facebook.internal.FacebookRequestErrorClassification;
import com.google.logging.type.LogSeverity;
import com.google.zxing.common.BitMatrix;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.medscape.android.activity.update.UpdateReferenceMainActivity;
import com.medscape.android.util.DialogUtil;
import com.theartofdev.edmodo.cropper.CropImage;
import com.wbmd.qxcalculator.model.api.APIResponse;
import io.grpc.internal.GrpcUtil;
import net.bytebuddy.jar.asm.Opcodes;
import okhttp3.internal.http.StatusLine;
import org.jetbrains.anko.DimensionsKt;
import org.mozilla.classfile.ByteCode;

final class BitMatrixParser {
    private static final int[][] BITNR = {new int[]{121, 120, 127, 126, 133, 132, 139, 138, 145, 144, 151, 150, 157, 156, 163, 162, 169, 168, 175, 174, 181, 180, 187, Opcodes.INVOKEDYNAMIC, 193, 192, 199, 198, -2, -2}, new int[]{123, 122, 129, 128, 135, 134, 141, 140, 147, 146, 153, 152, 159, 158, 165, 164, 171, 170, 177, 176, 183, 182, 189, 188, 195, 194, 201, 200, 816, -3}, new int[]{125, 124, 131, 130, 137, 136, 143, 142, 149, 148, 155, 154, 161, 160, 167, 166, 173, 172, 179, 178, 185, 184, 191, 190, 197, ByteCode.WIDE, CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE, ByteCode.BREAKPOINT, 818, 817}, new int[]{283, 282, 277, 276, 271, 270, 265, 264, 259, 258, 253, 252, 247, 246, 241, DimensionsKt.HDPI, AdvertisementType.BRANDED_DURING_LIVE, AdvertisementType.BRANDED_AS_CONTENT, 229, 228, 223, 222, 217, 216, AdvertisementType.ON_DEMAND_PRE_ROLL, 210, 205, 204, 819, -3}, new int[]{285, 284, 279, 278, 273, 272, 267, 266, 261, 260, 255, ByteCode.IMPDEP1, 249, 248, 243, 242, 237, 236, AdvertisementType.BRANDED_ON_DEMAND_PRE_ROLL, 230, 225, 224, 219, 218, 213, AdvertisementType.ON_DEMAND_MID_ROLL, 207, 206, 821, 820}, new int[]{287, 286, 281, 280, 275, 274, 269, 268, 263, 262, 257, 256, 251, ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION, 245, 244, 239, 238, AdvertisementType.BRANDED_ON_DEMAND_POST_ROLL, AdvertisementType.BRANDED_ON_DEMAND_MID_ROLL, 227, 226, AdvertisementType.LIVE, 220, 215, 214, 209, 208, 822, -3}, new int[]{289, 288, 295, 294, ContentFeedType.EAST_HD, 300, 307, 306, 313, 312, 319, 318, PullToRefreshBase.SMOOTH_SCROLL_LONG_DURATION_MS, 324, 331, 330, 337, 336, 343, 342, 349, 348, 355, 354, 361, 360, 367, 366, 824, 823}, new int[]{291, 290, 297, 296, ContentFeedType.EAST_SD, ContentFeedType.WEST_HD, 309, 308, 315, 314, 321, DimensionsKt.XHDPI, 327, 326, DialogUtil.MSG_SHOW_PDF_NOT_SUPPORTED_DIALOG, 332, 339, 338, 345, 344, 351, 350, 357, 356, 363, 362, 369, 368, 825, -3}, new int[]{293, 292, 299, 298, 305, ContentFeedType.WEST_SD, 311, 310, 317, 316, 323, 322, 329, 328, 335, 334, FacebookRequestErrorClassification.EC_TOO_MANY_USER_ACTION_CALLS, 340, 347, 346, 353, 352, 359, 358, 365, 364, 371, 370, 827, 826}, new int[]{409, APIResponse.RESPONSE_CODE_TIMEOUT, WindowState.MAXIMIZED, WindowState.MINIMIZED, 397, 396, 391, 390, 79, 78, -2, -2, 13, 12, 37, 36, 2, -1, 44, 43, 109, 108, 385, 384, 379, 378, 373, 372, 828, -3}, new int[]{411, 410, 405, 404, 399, 398, 393, 392, 81, 80, 40, -2, 15, 14, 39, 38, 3, -1, -1, 45, 111, 110, 387, 386, 381, 380, 375, 374, 830, 829}, new int[]{413, FacebookRequestErrorClassification.EC_APP_NOT_INSTALLED, 407, 406, 401, 400, 395, 394, 83, 82, 41, -3, -3, -3, -3, -3, 5, 4, 47, 46, 113, 112, 389, 388, 383, 382, 377, 376, 831, -3}, new int[]{415, 414, StatusLine.HTTP_MISDIRECTED_REQUEST, 420, 427, 426, 103, 102, 55, 54, 16, -3, -3, -3, -3, -3, -3, -3, 20, 19, 85, 84, 433, 432, 439, 438, 445, 444, 833, 832}, new int[]{417, 416, 423, 422, 429, 428, 105, 104, 57, 56, -3, -3, -3, -3, -3, -3, -3, -3, 22, 21, 87, 86, 435, 434, 441, 440, 447, 446, 834, -3}, new int[]{419, 418, 425, 424, 431, 430, 107, 106, 59, 58, -3, -3, -3, -3, -3, -3, -3, -3, -3, 23, 89, 88, 437, 436, GrpcUtil.DEFAULT_PORT_SSL, 442, 449, 448, 836, 835}, new int[]{481, DimensionsKt.XXHDPI, 475, 474, 469, 468, 48, -2, 30, -3, -3, -3, -3, -3, -3, -3, -3, -3, -3, 0, 53, 52, 463, 462, 457, 456, 451, 450, 837, -3}, new int[]{483, 482, 477, 476, 471, 470, 49, -1, -2, -3, -3, -3, -3, -3, -3, -3, -3, -3, -3, -3, -2, -1, 465, 464, 459, FacebookRequestErrorClassification.ESC_APP_NOT_INSTALLED, 453, 452, 839, 838}, new int[]{485, 484, 479, 478, 473, 472, 51, 50, 31, -3, -3, -3, -3, -3, -3, -3, -3, -3, -3, 1, -2, 42, 467, 466, 461, 460, 455, 454, 840, -3}, new int[]{487, 486, FacebookRequestErrorClassification.ESC_APP_INACTIVE, 492, 499, 498, 97, 96, 61, 60, -3, -3, -3, -3, -3, -3, -3, -3, -3, 26, 91, 90, 505, 504, FrameMetricsAggregator.EVERY_DURATION, 510, 517, 516, 842, 841}, new int[]{489, 488, 495, 494, ContentDeliveryMode.LINEAR, 500, 99, 98, 63, 62, -3, -3, -3, -3, -3, -3, -3, -3, 28, 27, 93, 92, 507, 506, InputDeviceCompat.SOURCE_DPAD, 512, 519, 518, 843, -3}, new int[]{491, 490, 497, 496, 503, ContentDeliveryMode.ON_DEMAND, 101, 100, 65, 64, 17, -3, -3, -3, -3, -3, -3, -3, 18, 29, 95, 94, 509, 508, 515, 514, 521, 520, 845, 844}, new int[]{559, 558, 553, 552, 547, 546, 541, 540, 73, 72, 32, -3, -3, -3, -3, -3, -3, 10, 67, 66, 115, 114, 535, 534, 529, 528, 523, 522, 846, -3}, new int[]{561, 560, UpdateReferenceMainActivity.DATA_UPDATE_FROM_UPDATE_REFERENCE, 554, 549, 548, 543, 542, 75, 74, -2, -1, 7, 6, 35, 34, 11, -2, 69, 68, 117, 116, 537, 536, 531, 530, 525, 524, 848, 847}, new int[]{563, 562, 557, 556, 551, 550, 545, 544, 77, 76, -2, 33, 9, 8, 25, 24, -1, -2, 71, 70, 119, 118, 539, 538, 533, 532, 527, 526, 849, -3}, new int[]{565, 564, 571, 570, 577, 576, 583, 582, 589, 588, 595, 594, ContentDeliverySubscriptionType.TRADITIONAL_MVPD, LogSeverity.CRITICAL_VALUE, 607, ContentDeliverySubscriptionType.PREMIUM, 613, 612, 619, 618, 625, 624, 631, 630, 637, 636, 643, 642, 851, 850}, new int[]{567, 566, 573, 572, 579, 578, 585, 584, 591, 590, 597, 596, ContentDeliverySubscriptionType.SUBSCRIPTION, ContentDeliverySubscriptionType.VIRTUAL_MVPD, 609, 608, 615, 614, 621, 620, 627, 626, 633, 632, 639, 638, 645, 644, 852, -3}, new int[]{569, 568, 575, 574, 581, 580, 587, 586, 593, 592, 599, 598, ContentDeliverySubscriptionType.TRANSACTIONAL, ContentDeliverySubscriptionType.ADVERTISING, 611, 610, 617, 616, 623, 622, 629, 628, 635, 634, 641, DimensionsKt.XXXHDPI, 647, 646, 854, 853}, new int[]{727, 726, 721, 720, 715, 714, 709, 708, 703, ContentDeliveryComposition.EMBED, 697, 696, 691, 690, 685, 684, 679, 678, 673, 672, 667, 666, 661, 660, 655, 654, 649, 648, 855, -3}, new int[]{729, 728, 723, 722, 717, 716, 711, 710, 705, 704, 699, 698, 693, 692, 687, 686, 681, 680, 675, 674, 669, 668, 663, 662, 657, 656, 651, 650, 857, 856}, new int[]{731, 730, 725, 724, 719, 718, 713, 712, 707, 706, ContentDeliveryComposition.CLEAN, LogSeverity.ALERT_VALUE, 695, 694, 689, 688, 683, 682, 677, 676, 671, 670, 665, 664, 659, 658, 653, 652, 858, -3}, new int[]{733, 732, 739, 738, 745, 744, 751, BranchStrongMatchHelper.STRONG_MATCH_URL_HIT_DELAY, 757, 756, 763, 762, 769, 768, 775, 774, 781, 780, 787, 786, 793, 792, 799, 798, ContentDeliveryAdvertisementCapability.LINEAR_2DAY, ContentDeliveryAdvertisementCapability.LINEAR_1DAY, 811, ContentDeliveryAdvertisementCapability.LINEAR_7DAY, 860, 859}, new int[]{735, 734, 741, 740, 747, 746, 753, 752, 759, 758, 765, 764, 771, 770, 777, 776, 783, 782, 789, 788, 795, 794, ContentDeliveryAdvertisementCapability.NONE, 800, ContentDeliveryAdvertisementCapability.LINEAR_4DAY, ContentDeliveryAdvertisementCapability.LINEAR_3DAY, 813, 812, 861, -3}, new int[]{737, 736, 743, 742, 749, 748, 755, 754, 761, 760, 767, 766, 773, 772, 779, 778, 785, 784, 791, 790, 797, 796, ContentDeliveryAdvertisementCapability.DYNAMIC_REPLACEMENT, ContentDeliveryAdvertisementCapability.DYNAMIC_LOAD, ContentDeliveryAdvertisementCapability.LINEAR_6DAY, ContentDeliveryAdvertisementCapability.LINEAR_5DAY, 815, 814, 863, 862}};
    private final BitMatrix bitMatrix;

    BitMatrixParser(BitMatrix bitMatrix2) {
        this.bitMatrix = bitMatrix2;
    }

    /* access modifiers changed from: package-private */
    public byte[] readCodewords() {
        byte[] bArr = new byte[144];
        int height = this.bitMatrix.getHeight();
        int width = this.bitMatrix.getWidth();
        for (int i = 0; i < height; i++) {
            int[] iArr = BITNR[i];
            for (int i2 = 0; i2 < width; i2++) {
                int i3 = iArr[i2];
                if (i3 >= 0 && this.bitMatrix.get(i2, i)) {
                    int i4 = i3 / 6;
                    bArr[i4] = (byte) (((byte) (1 << (5 - (i3 % 6)))) | bArr[i4]);
                }
            }
        }
        return bArr;
    }
}
