package com.appboy.ui.inappmessage.config;

public class AppboyInAppMessageParams {
    public static final double GRAPHIC_MODAL_MAX_HEIGHT_DP = 290.0d;
    public static final double GRAPHIC_MODAL_MAX_WIDTH_DP = 290.0d;
    public static final double MODALIZED_IMAGE_RADIUS_DP = 9.0d;
    private static double sGraphicModalMaxHeightDp = 290.0d;
    private static double sGraphicModalMaxWidthDp = 290.0d;
    private static double sModalizedImageRadiusDp = 9.0d;

    public static double getModalizedImageRadiusDp() {
        return sModalizedImageRadiusDp;
    }

    public static double getGraphicModalMaxWidthDp() {
        return sGraphicModalMaxWidthDp;
    }

    public static double getGraphicModalMaxHeightDp() {
        return sGraphicModalMaxHeightDp;
    }

    public static void setModalizedImageRadiusDp(double d) {
        sModalizedImageRadiusDp = d;
    }

    public static void setGraphicModalMaxWidthDp(double d) {
        sGraphicModalMaxWidthDp = d;
    }

    public static void setGraphicModalMaxHeightDp(double d) {
        sGraphicModalMaxHeightDp = d;
    }
}
