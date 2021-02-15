package com.medscape.android.drugs;

import android.content.Context;
import com.medscape.android.drugs.model.DrugManufacturer;
import com.medscape.android.task.FetchPListTask;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.io.IOUtils;

public class UpdateManufacturer {
    private static String MANUFACTURER_URL = "https://www.medscape.com/sp/mfr/config/android/drugmfr-app";
    private static final String MANUFACTURER_URL_BIZDEV = "http://www.staging.medscape.com/sp/mfr/config/iphone/drugmfr-app";
    private static final String MANUFACTURER_URL_DEV = "http://184.73.69.209/assets/Contactmanufacturer_android.txt";
    private static final String MANUFACTURER_URL_PROD = "https://www.medscape.com/sp/mfr/config/android/drugmfr-app";
    private static final String MANUFACTURER_URL_QA = "http://www.staging.medscape.com/sp/mfr/config/android/drugmfr-app";
    private static final String MANUFACTURER_URL_SB = "http://www.staging.medscape.com/sp/mfr/config/android/drugmfr-app";
    protected static final String TAG = "UpdateManufacturer";
    public static List<DrugManufacturer> list;
    private Context context;

    public UpdateManufacturer(Context context2) {
        this.context = context2;
        setUrl();
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void setUrl() {
        /*
            r5 = this;
            com.wbmd.environment.EnvironmentManager r0 = new com.wbmd.environment.EnvironmentManager
            r0.<init>()
            android.content.Context r1 = r5.context
            java.lang.String r2 = "module_content"
            java.lang.String r0 = r0.getEnvironmentWithDefault(r1, r2)
            int r1 = r0.hashCode()
            r2 = 3
            r3 = 2
            r4 = 1
            switch(r1) {
                case -463285093: goto L_0x0036;
                case 206969445: goto L_0x002c;
                case 608412604: goto L_0x0022;
                case 891578830: goto L_0x0018;
                default: goto L_0x0017;
            }
        L_0x0017:
            goto L_0x0040
        L_0x0018:
            java.lang.String r1 = "environment_bizdev"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0040
            r0 = 3
            goto L_0x0041
        L_0x0022:
            java.lang.String r1 = "environment_qa"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0040
            r0 = 1
            goto L_0x0041
        L_0x002c:
            java.lang.String r1 = "environment_production"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0040
            r0 = 0
            goto L_0x0041
        L_0x0036:
            java.lang.String r1 = "environment_sandbox"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L_0x0040
            r0 = 2
            goto L_0x0041
        L_0x0040:
            r0 = -1
        L_0x0041:
            if (r0 == 0) goto L_0x0057
            java.lang.String r1 = "http://www.staging.medscape.com/sp/mfr/config/android/drugmfr-app"
            if (r0 == r4) goto L_0x0054
            if (r0 == r3) goto L_0x0051
            if (r0 == r2) goto L_0x004c
            goto L_0x005b
        L_0x004c:
            java.lang.String r0 = "http://www.staging.medscape.com/sp/mfr/config/iphone/drugmfr-app"
            MANUFACTURER_URL = r0
            goto L_0x005b
        L_0x0051:
            MANUFACTURER_URL = r1
            goto L_0x005b
        L_0x0054:
            MANUFACTURER_URL = r1
            goto L_0x005b
        L_0x0057:
            java.lang.String r0 = "https://www.medscape.com/sp/mfr/config/android/drugmfr-app"
            MANUFACTURER_URL = r0
        L_0x005b:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = MANUFACTURER_URL
            r0.append(r1)
            java.lang.String r1 = MANUFACTURER_URL
            java.lang.String r1 = com.wbmd.wbmdcommons.utils.Util.attachSrcTagToUrl(r1)
            r0.append(r1)
            java.lang.String r1 = "&app="
            r0.append(r1)
            android.content.Context r1 = r5.context
            java.lang.String r1 = com.medscape.android.util.Util.getApplicationVersion(r1)
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            MANUFACTURER_URL = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.medscape.android.drugs.UpdateManufacturer.setUrl():void");
    }

    public void getAllManufacturer() {
        GetManufacturerTask getManufacturerTask = new GetManufacturerTask(this.context);
        getManufacturerTask.setGetURLContentsListener(new FetchPListTask.FetchPListListener() {
            public void setContentsMaxProgress(int i) {
            }

            public void setonError(int i) {
            }

            public void showContentsDownloadedProgress(int i) {
            }

            public void onContentsDownloaded(String str) {
                if (str != null && str.length() > 0) {
                    UpdateManufacturer.this.parseManufacturer(str.trim());
                }
            }
        });
        getManufacturerTask.execute(new String[]{MANUFACTURER_URL});
    }

    public void parseManufacturer(String str) {
        String[] split = str.split(IOUtils.LINE_SEPARATOR_UNIX);
        list = new ArrayList();
        for (String tokens : split) {
            DrugManufacturer drugManufacturer = new DrugManufacturer();
            int i = 0;
            for (Object obj : getTokens(tokens)) {
                if (i == 0) {
                    try {
                        drugManufacturer.setDrugId(Integer.parseInt(obj.toString()));
                    } catch (NumberFormatException unused) {
                    }
                } else if (i == 1) {
                    try {
                        drugManufacturer.setTitle(obj.toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else if (i == 2) {
                    drugManufacturer.setTitleColor(obj.toString());
                } else if (i == 3) {
                    drugManufacturer.setSubTitle(obj.toString());
                } else if (i == 4) {
                    drugManufacturer.setUrl(obj.toString());
                } else if (i == 5) {
                    drugManufacturer.setExpirationDate(obj.toString());
                }
                i++;
            }
            list.add(drugManufacturer);
        }
    }

    private Object[] getTokens(String str) {
        ArrayList arrayList = new ArrayList();
        while (true) {
            int indexOf = str.indexOf("|");
            if (indexOf == -1) {
                break;
            }
            arrayList.add(str.substring(0, indexOf));
            str = str.substring(indexOf + 1);
            if (str.startsWith("|")) {
                arrayList.add("");
                str = str.substring(1);
            }
        }
        arrayList.add(str);
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            String str2 = (String) it.next();
        }
        return arrayList.toArray();
    }

    public static DrugManufacturer getDrugManufacturer(int i) {
        List<DrugManufacturer> list2 = list;
        if (list2 == null) {
            return null;
        }
        try {
            Iterator<DrugManufacturer> it = list2.iterator();
            while (it.hasNext()) {
                DrugManufacturer next = it.next();
                if (next.getDrugId() == i) {
                    return next;
                }
                it.remove();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
