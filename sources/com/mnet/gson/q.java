package com.mnet.gson;

import com.mnet.gson.internal.a;
import com.mnet.gson.internal.e;
import java.math.BigDecimal;
import java.math.BigInteger;

public final class q extends k {
    private static final Class<?>[] a = {Integer.TYPE, Long.TYPE, Short.TYPE, Float.TYPE, Double.TYPE, Byte.TYPE, Boolean.TYPE, Character.TYPE, Integer.class, Long.class, Short.class, Float.class, Double.class, Byte.class, Boolean.class, Character.class};
    private Object b;

    public q(Boolean bool) {
        a((Object) bool);
    }

    public q(Number number) {
        a((Object) number);
    }

    public q(String str) {
        a((Object) str);
    }

    q(Object obj) {
        a(obj);
    }

    /* access modifiers changed from: package-private */
    public void a(Object obj) {
        if (obj instanceof Character) {
            this.b = String.valueOf(((Character) obj).charValue());
            return;
        }
        a.a((obj instanceof Number) || b(obj));
        this.b = obj;
    }

    public boolean a() {
        return this.b instanceof Boolean;
    }

    /* access modifiers changed from: package-private */
    public Boolean q() {
        return (Boolean) this.b;
    }

    public boolean h() {
        if (a()) {
            return q().booleanValue();
        }
        return Boolean.parseBoolean(c());
    }

    public boolean r() {
        return this.b instanceof Number;
    }

    public Number b() {
        Object obj = this.b;
        return obj instanceof String ? new e((String) this.b) : (Number) obj;
    }

    public boolean s() {
        return this.b instanceof String;
    }

    public String c() {
        if (r()) {
            return b().toString();
        }
        if (a()) {
            return q().toString();
        }
        return (String) this.b;
    }

    public double d() {
        return r() ? b().doubleValue() : Double.parseDouble(c());
    }

    public BigDecimal e() {
        Object obj = this.b;
        return obj instanceof BigDecimal ? (BigDecimal) obj : new BigDecimal(this.b.toString());
    }

    public long f() {
        return r() ? b().longValue() : Long.parseLong(c());
    }

    public int g() {
        return r() ? b().intValue() : Integer.parseInt(c());
    }

    private static boolean b(Object obj) {
        if (obj instanceof String) {
            return true;
        }
        Class<?> cls = obj.getClass();
        for (Class<?> isAssignableFrom : a) {
            if (isAssignableFrom.isAssignableFrom(cls)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        long doubleToLongBits;
        if (this.b == null) {
            return 31;
        }
        if (a(this)) {
            doubleToLongBits = b().longValue();
        } else {
            Object obj = this.b;
            if (!(obj instanceof Number)) {
                return obj.hashCode();
            }
            doubleToLongBits = Double.doubleToLongBits(b().doubleValue());
        }
        return (int) ((doubleToLongBits >>> 32) ^ doubleToLongBits);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        q qVar = (q) obj;
        if (this.b == null) {
            if (qVar.b == null) {
                return true;
            }
            return false;
        } else if (!a(this) || !a(qVar)) {
            if (!(this.b instanceof Number) || !(qVar.b instanceof Number)) {
                return this.b.equals(qVar.b);
            }
            double doubleValue = b().doubleValue();
            double doubleValue2 = qVar.b().doubleValue();
            if (doubleValue == doubleValue2) {
                return true;
            }
            if (!Double.isNaN(doubleValue) || !Double.isNaN(doubleValue2)) {
                return false;
            }
            return true;
        } else if (b().longValue() == qVar.b().longValue()) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean a(q qVar) {
        Object obj = qVar.b;
        if (!(obj instanceof Number)) {
            return false;
        }
        Number number = (Number) obj;
        if ((number instanceof BigInteger) || (number instanceof Long) || (number instanceof Integer) || (number instanceof Short) || (number instanceof Byte)) {
            return true;
        }
        return false;
    }
}
