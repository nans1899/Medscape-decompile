package com.wbmd.qxcalculator.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.provider.Settings;
import android.util.Base64;
import java.util.Map;
import java.util.Set;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

public class ObscuredSharedPreferences implements SharedPreferences {
    protected static final String ALGORITHM = "PBEWithMD5AndDES";
    private static final char[] SEKRIT = "NEK3KF98ReadByQxMDJF7392KFMEKFNVOPE".toCharArray();
    private static final String TAG = ObscuredSharedPreferences.class.getSimpleName();
    protected static final String UTF8 = "utf-8";
    protected Context context;
    protected SharedPreferences sharedPreferences;

    public Set<String> getStringSet(String str, Set<String> set) {
        return null;
    }

    public ObscuredSharedPreferences(Context context2, SharedPreferences sharedPreferences2) {
        this.sharedPreferences = sharedPreferences2;
        this.context = context2;
    }

    public class Editor implements SharedPreferences.Editor {
        protected SharedPreferences.Editor sharedPreferencesEditor;

        public SharedPreferences.Editor putStringSet(String str, Set<String> set) {
            return this;
        }

        public Editor() {
            this.sharedPreferencesEditor = ObscuredSharedPreferences.this.sharedPreferences.edit();
        }

        public Editor putBoolean(String str, boolean z) {
            this.sharedPreferencesEditor.putString(str, ObscuredSharedPreferences.this.encrypt(Boolean.toString(z)));
            return this;
        }

        public Editor putFloat(String str, float f) {
            this.sharedPreferencesEditor.putString(str, ObscuredSharedPreferences.this.encrypt(Float.toString(f)));
            return this;
        }

        public Editor putInt(String str, int i) {
            this.sharedPreferencesEditor.putString(str, ObscuredSharedPreferences.this.encrypt(Integer.toString(i)));
            return this;
        }

        public Editor putLong(String str, long j) {
            this.sharedPreferencesEditor.putString(str, ObscuredSharedPreferences.this.encrypt(Long.toString(j)));
            return this;
        }

        public Editor putString(String str, String str2) {
            this.sharedPreferencesEditor.putString(str, ObscuredSharedPreferences.this.encrypt(str2));
            return this;
        }

        public void apply() {
            this.sharedPreferencesEditor.apply();
        }

        public Editor clear() {
            this.sharedPreferencesEditor.clear();
            return this;
        }

        public boolean commit() {
            return this.sharedPreferencesEditor.commit();
        }

        public Editor remove(String str) {
            this.sharedPreferencesEditor.remove(str);
            return this;
        }
    }

    public Editor edit() {
        return new Editor();
    }

    public Map<String, ?> getAll() {
        throw new UnsupportedOperationException();
    }

    public boolean getBoolean(String str, boolean z) {
        String string = this.sharedPreferences.getString(str, (String) null);
        return string != null ? Boolean.parseBoolean(decrypt(string)) : z;
    }

    public float getFloat(String str, float f) {
        String string = this.sharedPreferences.getString(str, (String) null);
        return string != null ? Float.parseFloat(decrypt(string)) : f;
    }

    public int getInt(String str, int i) {
        String string = this.sharedPreferences.getString(str, (String) null);
        return string != null ? Integer.parseInt(decrypt(string)) : i;
    }

    public long getLong(String str, long j) {
        String string = this.sharedPreferences.getString(str, (String) null);
        return string != null ? Long.parseLong(decrypt(string)) : j;
    }

    public String getString(String str, String str2) {
        String string = this.sharedPreferences.getString(str, (String) null);
        return string != null ? decrypt(string) : str2;
    }

    public boolean contains(String str) {
        return this.sharedPreferences.contains(str);
    }

    public void registerOnSharedPreferenceChangeListener(SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener) {
        this.sharedPreferences.registerOnSharedPreferenceChangeListener(onSharedPreferenceChangeListener);
    }

    public void unregisterOnSharedPreferenceChangeListener(SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener) {
        this.sharedPreferences.unregisterOnSharedPreferenceChangeListener(onSharedPreferenceChangeListener);
    }

    /* access modifiers changed from: protected */
    public String encrypt(String str) {
        byte[] bArr;
        if (str != null) {
            try {
                bArr = str.getBytes(UTF8);
            } catch (Exception e) {
                Log.e(TAG, e.toString());
                return "";
            }
        } else {
            bArr = new byte[0];
        }
        SecretKey generateSecret = SecretKeyFactory.getInstance(ALGORITHM).generateSecret(new PBEKeySpec(SEKRIT));
        Cipher instance = Cipher.getInstance(ALGORITHM);
        instance.init(1, generateSecret, new PBEParameterSpec(Settings.Secure.getString(this.context.getContentResolver(), "android_id").getBytes(UTF8), 20));
        return new String(Base64.encode(instance.doFinal(bArr), 2), UTF8);
    }

    /* access modifiers changed from: protected */
    public String decrypt(String str) {
        byte[] bArr;
        if (str != null) {
            try {
                bArr = Base64.decode(str, 0);
            } catch (Exception e) {
                Log.e(TAG, e.toString());
                return "";
            }
        } else {
            bArr = new byte[0];
        }
        SecretKey generateSecret = SecretKeyFactory.getInstance(ALGORITHM).generateSecret(new PBEKeySpec(SEKRIT));
        Cipher instance = Cipher.getInstance(ALGORITHM);
        instance.init(2, generateSecret, new PBEParameterSpec(Settings.Secure.getString(this.context.getContentResolver(), "android_id").getBytes(UTF8), 20));
        return new String(instance.doFinal(bArr), UTF8);
    }
}
