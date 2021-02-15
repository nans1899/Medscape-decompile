package com.epapyrus.plugpdf.core;

public class PlugPDFException {

    public static class InvalidLicense extends Exception {
        static final long serialVersionUID = 2001;
    }

    public static class LicenseMismatchAppID extends InvalidLicense {
        static final long serialVersionUID = 2006;
    }

    public static class LicenseTrialTimeOut extends InvalidLicense {
        static final long serialVersionUID = 2003;
    }

    public static class LicenseUnusableOS extends InvalidLicense {
        static final long serialVersionUID = 2005;
    }

    public static class LicenseUnusableSDKVersion extends InvalidLicense {
        static final long serialVersionUID = 2004;
    }

    public static class LicenseWrongProductVersion extends InvalidLicense {
        static final long serialVersionUID = 2002;
    }

    public static class WrongPassword extends Exception {
        static final long serialVersionUID = 1000;

        public WrongPassword() {
        }

        public WrongPassword(String str) {
            super(str);
        }
    }

    public static class JetStreamConnectionError extends Exception {
        static final long serialVersionUID = 3000;

        public JetStreamConnectionError() {
        }

        public JetStreamConnectionError(String str) {
            super(str);
        }
    }
}
