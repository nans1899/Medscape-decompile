package com.wbmd.qxcalculator;

public interface AuthorizationProvider {
    boolean isLoggingIn();

    boolean isRegistering();
}
