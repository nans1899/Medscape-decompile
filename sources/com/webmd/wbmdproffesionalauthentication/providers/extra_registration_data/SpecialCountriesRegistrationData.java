package com.webmd.wbmdproffesionalauthentication.providers.extra_registration_data;

import java.util.HashMap;
import java.util.Map;

public class SpecialCountriesRegistrationData {
    private Map<String, String> countryPhoneCodes;
    private Map<String, String> licenseHints;
    private Map<String, String> licenseNames;
    private Map<String, Integer> phoneLengthRules;

    public SpecialCountriesRegistrationData() {
        HashMap hashMap = new HashMap();
        this.countryPhoneCodes = hashMap;
        hashMap.put("Canada", "+1");
        this.countryPhoneCodes.put("Mexico", "+52");
        this.countryPhoneCodes.put("Brazil", "+55");
        this.countryPhoneCodes.put("United Kingdom", "+44");
        this.countryPhoneCodes.put("Germany", "+49");
        this.countryPhoneCodes.put("France", "+33");
        this.countryPhoneCodes.put("Italy", "+39");
        this.countryPhoneCodes.put("Spain", "+34");
        HashMap hashMap2 = new HashMap();
        this.licenseNames = hashMap2;
        hashMap2.put("Canada", "HCP License Number");
        this.licenseNames.put("Mexico", "Cédula de Medicina General");
        this.licenseNames.put("Brazil", "Conselho Regional de Medicina (CRM)");
        this.licenseNames.put("United Kingdom", "Registration Number (GMC)");
        this.licenseNames.put("Germany", "LANR");
        this.licenseNames.put("France", "RPPS Number");
        this.licenseNames.put("Italy", "Codice Fiscale");
        this.licenseNames.put("Spain", "Número Colegiado");
        HashMap hashMap3 = new HashMap();
        this.licenseHints = hashMap3;
        hashMap3.put("Canada", "6 Digits");
        this.licenseHints.put("Mexico", "6 to 8 Digits");
        this.licenseHints.put("Brazil", "1 to 8 Digits");
        this.licenseHints.put("United Kingdom", "7 alphanumeric digits");
        this.licenseHints.put("Germany", "9 Digits");
        this.licenseHints.put("France", "11 Digits");
        this.licenseHints.put("Italy", "16 alphanumeric digits");
        this.licenseHints.put("Spain", "5 to 10 Digits");
        HashMap hashMap4 = new HashMap();
        this.phoneLengthRules = hashMap4;
        hashMap4.put("Canada", 10);
        this.phoneLengthRules.put("Mexico", 15);
        this.phoneLengthRules.put("Brazil", 15);
        this.phoneLengthRules.put("United Kingdom", 11);
        this.phoneLengthRules.put("Germany", 11);
        this.phoneLengthRules.put("France", 10);
        this.phoneLengthRules.put("Italy", 10);
        this.phoneLengthRules.put("Spain", 9);
    }

    /* renamed from: com.webmd.wbmdproffesionalauthentication.providers.extra_registration_data.SpecialCountriesRegistrationData$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$webmd$wbmdproffesionalauthentication$providers$extra_registration_data$CountryDataTypes;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        static {
            /*
                com.webmd.wbmdproffesionalauthentication.providers.extra_registration_data.CountryDataTypes[] r0 = com.webmd.wbmdproffesionalauthentication.providers.extra_registration_data.CountryDataTypes.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$webmd$wbmdproffesionalauthentication$providers$extra_registration_data$CountryDataTypes = r0
                com.webmd.wbmdproffesionalauthentication.providers.extra_registration_data.CountryDataTypes r1 = com.webmd.wbmdproffesionalauthentication.providers.extra_registration_data.CountryDataTypes.phoneCode     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$webmd$wbmdproffesionalauthentication$providers$extra_registration_data$CountryDataTypes     // Catch:{ NoSuchFieldError -> 0x001d }
                com.webmd.wbmdproffesionalauthentication.providers.extra_registration_data.CountryDataTypes r1 = com.webmd.wbmdproffesionalauthentication.providers.extra_registration_data.CountryDataTypes.licenseName     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$webmd$wbmdproffesionalauthentication$providers$extra_registration_data$CountryDataTypes     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.webmd.wbmdproffesionalauthentication.providers.extra_registration_data.CountryDataTypes r1 = com.webmd.wbmdproffesionalauthentication.providers.extra_registration_data.CountryDataTypes.licenseHint     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.webmd.wbmdproffesionalauthentication.providers.extra_registration_data.SpecialCountriesRegistrationData.AnonymousClass1.<clinit>():void");
        }
    }

    public String getCountryData(String str, CountryDataTypes countryDataTypes) {
        if (str == null) {
            return "";
        }
        HashMap hashMap = new HashMap();
        int i = AnonymousClass1.$SwitchMap$com$webmd$wbmdproffesionalauthentication$providers$extra_registration_data$CountryDataTypes[countryDataTypes.ordinal()];
        if (i == 1) {
            hashMap.putAll(this.countryPhoneCodes);
        } else if (i == 2) {
            hashMap.putAll(this.licenseNames);
        } else if (i == 3) {
            hashMap.putAll(this.licenseHints);
        }
        return hashMap.containsKey(str) ? (String) hashMap.get(str) : "";
    }

    public Map<String, String> getCountryPhoneCodes() {
        return this.countryPhoneCodes;
    }

    public Map<String, Integer> getPhoneLengthRules() {
        return this.phoneLengthRules;
    }
}
