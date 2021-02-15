package net.media.android.bidder.base.models;

import com.dd.plist.ASCIIPropertyListParser;
import com.medscape.android.settings.Settings;
import com.mnet.gson.e;
import com.mnet.gson.internal.bind.i;
import com.mnet.gson.v;
import java.util.Map;
import mnetinternal.c;
import mnetinternal.g;
import mnetinternal.h;
import mnetinternal.j;
import mnetinternal.p;

public final class MNetUser {
    public static final String FEMALE = "F";
    public static final String MALE = "M";
    public static final String UNKNOWN = "U";
    @c(a = "data")
    protected Map<String, Object> mData;
    @c(a = "gender")
    protected String mGender;
    @c(a = "id")
    protected String mId;
    @c(a = "keywords")
    protected String mKeyWords;
    @c(a = "yob")
    protected int mYob;

    public final class TypeAdapter extends v<MNetUser> {
        public static final g<MNetUser> TYPE_TOKEN = g.b(MNetUser.class);
        private final e mGson;
        private final v<Object> mTypeAdapter0;
        private final v<Map<String, Object>> mTypeAdapter1 = new p.f(i.A, this.mTypeAdapter0, new p.e());

        public TypeAdapter(e eVar) {
            this.mGson = eVar;
            this.mTypeAdapter0 = eVar.a(g.b(Object.class));
        }

        public void write(j jVar, MNetUser mNetUser) {
            if (mNetUser == null) {
                jVar.f();
                return;
            }
            jVar.d();
            jVar.a("id");
            if (mNetUser.mId != null) {
                i.A.write(jVar, mNetUser.mId);
            } else {
                jVar.f();
            }
            jVar.a(Settings.GENDER);
            if (mNetUser.mGender != null) {
                i.A.write(jVar, mNetUser.mGender);
            } else {
                jVar.f();
            }
            jVar.a("yob");
            jVar.a((long) mNetUser.mYob);
            jVar.a("data");
            if (mNetUser.mData != null) {
                this.mTypeAdapter1.write(jVar, mNetUser.mData);
            } else {
                jVar.f();
            }
            jVar.a("keywords");
            if (mNetUser.mKeyWords != null) {
                i.A.write(jVar, mNetUser.mKeyWords);
            } else {
                jVar.f();
            }
            jVar.e();
        }

        public MNetUser read(h hVar) {
            mnetinternal.i f = hVar.f();
            if (mnetinternal.i.NULL == f) {
                hVar.j();
                return null;
            } else if (mnetinternal.i.BEGIN_OBJECT != f) {
                hVar.n();
                return null;
            } else {
                hVar.c();
                MNetUser mNetUser = new MNetUser();
                while (hVar.e()) {
                    String g = hVar.g();
                    char c = 65535;
                    switch (g.hashCode()) {
                        case -1249512767:
                            if (g.equals(Settings.GENDER)) {
                                c = 1;
                                break;
                            }
                            break;
                        case 3355:
                            if (g.equals("id")) {
                                c = 0;
                                break;
                            }
                            break;
                        case 119820:
                            if (g.equals("yob")) {
                                c = 2;
                                break;
                            }
                            break;
                        case 3076010:
                            if (g.equals("data")) {
                                c = 3;
                                break;
                            }
                            break;
                        case 523149226:
                            if (g.equals("keywords")) {
                                c = 4;
                                break;
                            }
                            break;
                    }
                    if (c == 0) {
                        mNetUser.mId = i.A.read(hVar);
                    } else if (c == 1) {
                        mNetUser.mGender = i.A.read(hVar);
                    } else if (c == 2) {
                        mNetUser.mYob = p.j.a(hVar, mNetUser.mYob);
                    } else if (c == 3) {
                        mNetUser.mData = this.mTypeAdapter1.read(hVar);
                    } else if (c != 4) {
                        hVar.n();
                    } else {
                        mNetUser.mKeyWords = i.A.read(hVar);
                    }
                }
                hVar.d();
                return mNetUser;
            }
        }
    }

    protected MNetUser() {
    }

    public static class Builder {
        private MNetUser mUser = new MNetUser();

        public Builder withId(String str) {
            this.mUser.setId(str);
            return this;
        }

        public Builder withYearOfBirth(int i) {
            this.mUser.setYearOfBirth(i);
            return this;
        }

        public Builder withGender(String str) {
            this.mUser.setGender(str);
            return this;
        }

        public Builder withKeyWords(String str) {
            this.mUser.setKeyWords(str);
            return this;
        }

        public Builder withData(Map<String, Object> map) {
            this.mUser.setData(map);
            return this;
        }

        public MNetUser build() {
            return this.mUser;
        }
    }

    public String getId() {
        return this.mId;
    }

    public String getGender() {
        return this.mGender;
    }

    public int getYob() {
        return this.mYob;
    }

    public String getKeyWords() {
        return this.mKeyWords;
    }

    public Map<String, Object> getData() {
        return this.mData;
    }

    /* access modifiers changed from: private */
    public void setId(String str) {
        this.mId = str;
    }

    /* access modifiers changed from: private */
    public void setGender(String str) {
        this.mGender = str;
    }

    /* access modifiers changed from: private */
    public void setYearOfBirth(int i) {
        this.mYob = i;
    }

    /* access modifiers changed from: private */
    public void setKeyWords(String str) {
        this.mKeyWords = str;
    }

    /* access modifiers changed from: private */
    public void setData(Map<String, Object> map) {
        this.mData = map;
    }

    public String toString() {
        return "MNetUser{mId='" + this.mId + '\'' + ", mGender='" + this.mGender + '\'' + ", mYob=" + this.mYob + ", mData=" + this.mData + ", mKeyWords='" + this.mKeyWords + '\'' + ASCIIPropertyListParser.DICTIONARY_END_TOKEN;
    }
}
