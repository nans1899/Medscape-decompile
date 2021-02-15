package com.wbmd.qxcalculator;

public class CalculateModule {
    private static ApplicationState applicationState = ApplicationState.InForeground;
    private static CalculateModule instance;
    public static boolean isDebug = false;
    public static ModuleOwner moduleOwner = ModuleOwner.WebMD;

    public enum ApplicationState {
        TransitioningView,
        InBackground,
        InForeground
    }

    public enum ModuleOwner {
        WebMD,
        QxMD
    }

    public static ApplicationState getApplicationState() {
        return applicationState;
    }

    public static void setApplicationState(ApplicationState applicationState2) {
        applicationState = applicationState2;
    }
}
