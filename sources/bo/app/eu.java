package bo.app;

import com.appboy.models.outgoing.AppboyProperties;
import com.appboy.support.AppboyLogger;
import java.util.Date;
import org.json.JSONException;
import org.json.JSONObject;

public class eu implements eq {
    private static final String a = AppboyLogger.getAppboyLogTag(eu.class);
    private fh b;
    private String c;
    private int d;
    private Object e;

    eu(fh fhVar, String str, int i) {
        this.b = fhVar;
        this.c = str;
        this.d = i;
    }

    protected eu(JSONObject jSONObject) {
        this((fh) ed.a(jSONObject, "property_type", fh.class, fh.UNKNOWN), jSONObject.getString("property_key"), jSONObject.getInt("comparator"));
        if (!jSONObject.has("property_value")) {
            return;
        }
        if (this.b.equals(fh.STRING)) {
            this.e = jSONObject.getString("property_value");
        } else if (this.b.equals(fh.BOOLEAN)) {
            this.e = Boolean.valueOf(jSONObject.getBoolean("property_value"));
        } else if (this.b.equals(fh.NUMBER)) {
            this.e = Double.valueOf(jSONObject.getDouble("property_value"));
        } else if (this.b.equals(fh.DATE)) {
            this.e = Long.valueOf(jSONObject.getLong("property_value"));
        }
    }

    public boolean a(fk fkVar) {
        if (!(fkVar instanceof fl)) {
            return false;
        }
        AppboyProperties f = ((fl) fkVar).f();
        Object obj = null;
        if (f != null) {
            try {
                obj = f.forJsonPut().opt(this.c);
            } catch (Exception e2) {
                AppboyLogger.e(a, "Caught exception checking property filter condition.", e2);
                return false;
            }
        }
        if (obj == null) {
            if (this.d == 12 || this.d == 17 || this.d == 2) {
                return true;
            }
            return false;
        } else if (this.d == 11) {
            return true;
        } else {
            if (this.d == 12) {
                return false;
            }
            int i = AnonymousClass1.a[this.b.ordinal()];
            if (i == 1) {
                return c(obj);
            }
            if (i == 2) {
                return b(obj);
            }
            if (i == 3) {
                return a(obj, fkVar.c());
            }
            if (i != 4) {
                return false;
            }
            return a(obj);
        }
    }

    /* renamed from: bo.app.eu$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] a;

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        static {
            /*
                bo.app.fh[] r0 = bo.app.fh.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                a = r0
                bo.app.fh r1 = bo.app.fh.STRING     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = a     // Catch:{ NoSuchFieldError -> 0x001d }
                bo.app.fh r1 = bo.app.fh.BOOLEAN     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = a     // Catch:{ NoSuchFieldError -> 0x0028 }
                bo.app.fh r1 = bo.app.fh.DATE     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = a     // Catch:{ NoSuchFieldError -> 0x0033 }
                bo.app.fh r1 = bo.app.fh.NUMBER     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: bo.app.eu.AnonymousClass1.<clinit>():void");
        }
    }

    private boolean a(Object obj, long j) {
        Date a2 = obj instanceof String ? du.a((String) obj, u.LONG) : null;
        if (a2 != null) {
            long a3 = du.a(a2);
            long longValue = ((Number) this.e).longValue();
            int i = this.d;
            if (i != 15) {
                if (i != 16) {
                    switch (i) {
                        case 1:
                            if (a3 == longValue) {
                                return true;
                            }
                            return false;
                        case 2:
                            if (a3 != longValue) {
                                return true;
                            }
                            return false;
                        case 3:
                            if (a3 > longValue) {
                                return true;
                            }
                            return false;
                        case 4:
                            if (a3 >= j - longValue) {
                                return true;
                            }
                            return false;
                        case 5:
                            if (a3 < longValue) {
                                return true;
                            }
                            return false;
                        case 6:
                            if (a3 <= j - longValue) {
                                return true;
                            }
                            return false;
                        default:
                            return false;
                    }
                } else if (a3 > j + longValue) {
                    return true;
                } else {
                    return false;
                }
            } else if (a3 < j + longValue) {
                return true;
            } else {
                return false;
            }
        } else if (this.d == 2) {
            return true;
        } else {
            return false;
        }
    }

    private boolean a(Object obj) {
        if ((obj instanceof Integer) || (obj instanceof Double)) {
            double doubleValue = ((Number) obj).doubleValue();
            double doubleValue2 = ((Number) this.e).doubleValue();
            int i = this.d;
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        if (i == 5 && doubleValue < doubleValue2) {
                            return true;
                        }
                        return false;
                    } else if (doubleValue > doubleValue2) {
                        return true;
                    } else {
                        return false;
                    }
                } else if (doubleValue != doubleValue2) {
                    return true;
                } else {
                    return false;
                }
            } else if (doubleValue == doubleValue2) {
                return true;
            } else {
                return false;
            }
        } else if (this.d == 2) {
            return true;
        } else {
            return false;
        }
    }

    private boolean b(Object obj) {
        if (obj instanceof Boolean) {
            int i = this.d;
            if (i == 1) {
                return obj.equals(this.e);
            }
            if (i != 2) {
                return false;
            }
            return !obj.equals(this.e);
        } else if (this.d == 2) {
            return true;
        } else {
            return false;
        }
    }

    private boolean c(Object obj) {
        if (!(obj instanceof String)) {
            int i = this.d;
            if (i == 2 || i == 17) {
                return true;
            }
            return false;
        }
        int i2 = this.d;
        if (i2 == 1) {
            return obj.equals(this.e);
        }
        if (i2 == 2) {
            return !obj.equals(this.e);
        }
        if (i2 == 10) {
            return ((String) obj).matches((String) this.e);
        }
        if (i2 != 17) {
            return false;
        }
        return !((String) obj).matches((String) this.e);
    }

    /* renamed from: a */
    public JSONObject forJsonPut() {
        JSONObject jSONObject = new JSONObject();
        try {
            if (!this.b.equals(fh.UNKNOWN)) {
                jSONObject.put("property_type", this.b.toString());
            }
            jSONObject.put("property_key", this.c);
            jSONObject.put("comparator", this.d);
            jSONObject.put("property_value", this.e);
        } catch (JSONException e2) {
            AppboyLogger.e(a, "Caught exception creating property filter Json.", e2);
        }
        return jSONObject;
    }
}
