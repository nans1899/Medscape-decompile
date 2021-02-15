package com.medscape.android.parser.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.medscape.android.Constants;
import com.medscape.android.MedscapeApplication;
import com.medscape.android.db.FeedDetail;
import com.webmd.wbmdcmepulse.omniture.OmnitureData;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Slideshow implements Parcelable {
    public static Parcelable.Creator<Slideshow> CREATOR = new Parcelable.Creator<Slideshow>() {
        public Slideshow createFromParcel(Parcel parcel) {
            return new Slideshow(parcel);
        }

        public Slideshow[] newArray(int i) {
            return new Slideshow[i];
        }
    };
    public String activityId;
    public String chapterTip;
    public int chapterTipWidth;
    public Chapters[] chapters;
    public int currentPosition;
    public String encryptedParId;
    public boolean isEditorial;
    public MenuOptions[] menuOptions;
    public String parId;
    public String resumeUrl;
    public String roadblockUrl;
    public String sfNumber;
    public boolean showAds;
    public String slideShowUrl;
    public String slideTip;
    public int slideTipWidth;
    public Slides[] slides;
    public String subTitle;
    public String tacticId;
    public String title;

    public int describeContents() {
        return 0;
    }

    public static class Chapters implements Parcelable {
        public static Parcelable.Creator<Chapters> CREATOR = new Parcelable.Creator<Chapters>() {
            public Chapters createFromParcel(Parcel parcel) {
                return new Chapters(parcel);
            }

            public Chapters[] newArray(int i) {
                return new Chapters[i];
            }
        };
        public String name;
        public String url;

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.name);
            parcel.writeString(this.url);
        }

        public Chapters() {
        }

        private Chapters(Parcel parcel) {
            this.name = parcel.readString();
            this.url = parcel.readString();
        }
    }

    public static class MenuOptions implements Parcelable {
        public static Parcelable.Creator<MenuOptions> CREATOR = new Parcelable.Creator<MenuOptions>() {
            public MenuOptions createFromParcel(Parcel parcel) {
                return new MenuOptions(parcel);
            }

            public MenuOptions[] newArray(int i) {
                return new MenuOptions[i];
            }
        };
        public String name;
        public String url;

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.name);
            parcel.writeString(this.url);
        }

        public MenuOptions() {
        }

        private MenuOptions(Parcel parcel) {
            this.name = parcel.readString();
            this.url = parcel.readString();
        }
    }

    public static class Slides implements Parcelable {
        public static Parcelable.Creator<Slides> CREATOR = new Parcelable.Creator<Slides>() {
            public Slides createFromParcel(Parcel parcel) {
                return new Slides(parcel);
            }

            public Slides[] newArray(int i) {
                return new Slides[i];
            }
        };
        public String audioUrl;
        public SlidePane bottomPane;
        public String notesUrl;
        public boolean reverseLandscape;
        public SlidePane topPane;
        public double topScale;

        public int describeContents() {
            return 0;
        }

        public static class SlidePane implements Parcelable {
            public static Parcelable.Creator<SlidePane> CREATOR = new Parcelable.Creator<SlidePane>() {
                public SlidePane createFromParcel(Parcel parcel) {
                    return new SlidePane(parcel);
                }

                public SlidePane[] newArray(int i) {
                    return new SlidePane[i];
                }
            };
            public boolean showsZoom;
            public String url;

            public int describeContents() {
                return 0;
            }

            public void writeToParcel(Parcel parcel, int i) {
                parcel.writeByte(this.showsZoom ? (byte) 1 : 0);
                parcel.writeString(this.url);
            }

            public SlidePane() {
            }

            private SlidePane(Parcel parcel) {
                this.showsZoom = parcel.readByte() != 0;
                this.url = parcel.readString();
            }
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeDouble(this.topScale);
            parcel.writeByte(this.reverseLandscape ? (byte) 1 : 0);
            parcel.writeString(this.audioUrl);
            parcel.writeString(this.notesUrl);
            parcel.writeParcelable(this.topPane, i);
            parcel.writeParcelable(this.bottomPane, i);
        }

        public Slides() {
        }

        private Slides(Parcel parcel) {
            this.topScale = parcel.readDouble();
            this.reverseLandscape = parcel.readByte() != 0;
            this.audioUrl = parcel.readString();
            this.notesUrl = parcel.readString();
            this.topPane = (SlidePane) parcel.readParcelable(SlidePane.class.getClassLoader());
            this.bottomPane = (SlidePane) parcel.readParcelable(SlidePane.class.getClassLoader());
        }
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.slideShowUrl);
        parcel.writeString(this.title);
        parcel.writeString(this.roadblockUrl);
        parcel.writeString(this.activityId);
        parcel.writeString(this.tacticId);
        parcel.writeString(this.parId);
        parcel.writeString(this.encryptedParId);
        parcel.writeString(this.sfNumber);
        parcel.writeString(this.subTitle);
        parcel.writeByte(this.showAds ? (byte) 1 : 0);
        parcel.writeString(this.resumeUrl);
        parcel.writeTypedArray(this.menuOptions, i);
        parcel.writeTypedArray(this.slides, i);
        parcel.writeTypedArray(this.chapters, i);
        parcel.writeInt(this.currentPosition);
        parcel.writeByte(this.isEditorial ? (byte) 1 : 0);
        parcel.writeString(this.chapterTip);
        parcel.writeString(this.slideTip);
        parcel.writeInt(this.slideTipWidth);
        parcel.writeInt(this.chapterTipWidth);
    }

    public Slideshow() {
    }

    private Slideshow(Parcel parcel) {
        this.slideShowUrl = parcel.readString();
        this.title = parcel.readString();
        this.roadblockUrl = parcel.readString();
        this.activityId = parcel.readString();
        this.tacticId = parcel.readString();
        this.parId = parcel.readString();
        this.encryptedParId = parcel.readString();
        this.sfNumber = parcel.readString();
        this.subTitle = parcel.readString();
        boolean z = true;
        this.showAds = parcel.readByte() != 0;
        this.resumeUrl = parcel.readString();
        this.menuOptions = (MenuOptions[]) parcel.createTypedArray(MenuOptions.CREATOR);
        this.slides = (Slides[]) parcel.createTypedArray(Slides.CREATOR);
        this.chapters = (Chapters[]) parcel.createTypedArray(Chapters.CREATOR);
        this.currentPosition = parcel.readInt();
        this.isEditorial = parcel.readByte() == 0 ? false : z;
        this.chapterTip = parcel.readString();
        this.slideTip = parcel.readString();
        this.slideTipWidth = parcel.readInt();
        this.chapterTipWidth = parcel.readInt();
    }

    public static Slideshow createFromJSON(JSONObject jSONObject, String str) throws JSONException {
        Slideshow slideshow = new Slideshow();
        slideshow.slideShowUrl = str;
        slideshow.title = jSONObject.optString("title");
        slideshow.subTitle = jSONObject.optString("subTitle");
        slideshow.roadblockUrl = cleanForDemoMode(jSONObject.optString("roadblockURL"));
        slideshow.showAds = jSONObject.optString("showsAds").equalsIgnoreCase("YES");
        slideshow.resumeUrl = cleanForDemoMode(jSONObject.optString("resumeURL"));
        slideshow.activityId = jSONObject.optString("activityId");
        slideshow.tacticId = jSONObject.optString("tacticId");
        slideshow.parId = jSONObject.optString("parId");
        slideshow.encryptedParId = jSONObject.optString("encryptedParId");
        slideshow.sfNumber = jSONObject.optString("sfNumber");
        slideshow.isEditorial = jSONObject.optBoolean("isEditorial");
        slideshow.chapterTip = jSONObject.optString("chapterTip");
        slideshow.slideTip = jSONObject.optString("slideTip");
        slideshow.slideTipWidth = jSONObject.optInt("slideTipWidth");
        slideshow.chapterTipWidth = jSONObject.optInt("chapterTipWidth");
        JSONArray optJSONArray = jSONObject.optJSONArray("chapters");
        if (optJSONArray != null && optJSONArray.length() > 0) {
            slideshow.chapters = new Chapters[optJSONArray.length()];
            for (int i = 0; i < optJSONArray.length(); i++) {
                Chapters chapters2 = new Chapters();
                JSONObject jSONObject2 = optJSONArray.getJSONObject(i);
                chapters2.name = jSONObject2.optString("name");
                chapters2.url = cleanForDemoMode(jSONObject2.optString("url"));
                slideshow.chapters[i] = chapters2;
            }
        }
        JSONArray optJSONArray2 = jSONObject.optJSONArray("menuOptions");
        if (optJSONArray2 != null && optJSONArray2.length() > 0) {
            slideshow.menuOptions = new MenuOptions[optJSONArray2.length()];
            for (int i2 = 0; i2 < optJSONArray2.length(); i2++) {
                MenuOptions menuOptions2 = new MenuOptions();
                JSONObject jSONObject3 = optJSONArray2.getJSONObject(i2);
                menuOptions2.name = jSONObject3.optString("name");
                menuOptions2.url = cleanForDemoMode(jSONObject3.optString("url"));
                slideshow.menuOptions[i2] = menuOptions2;
            }
        }
        JSONArray optJSONArray3 = jSONObject.optJSONArray(OmnitureData.LINK_NAME_TAB_SLIDES);
        if (optJSONArray3 != null && optJSONArray3.length() > 0) {
            slideshow.slides = new Slides[optJSONArray3.length()];
            for (int i3 = 0; i3 < optJSONArray3.length(); i3++) {
                Slides slides2 = new Slides();
                JSONObject jSONObject4 = optJSONArray3.getJSONObject(i3);
                slides2.topScale = jSONObject4.optDouble("topScale", 1.0d);
                slides2.reverseLandscape = jSONObject4.isNull("invertsLandscapePanes") ? false : jSONObject4.getString("invertsLandscapePanes").equalsIgnoreCase("NO");
                String str2 = "";
                slides2.audioUrl = jSONObject4.isNull("audioURL") ? str2 : cleanForDemoMode(jSONObject4.getString("audioURL"));
                if (!jSONObject4.isNull("notesURL")) {
                    str2 = cleanForDemoMode(jSONObject4.getString("notesURL"));
                }
                slides2.notesUrl = str2;
                JSONObject optJSONObject = jSONObject4.optJSONObject("topPane");
                String str3 = null;
                if (optJSONObject != null) {
                    slides2.topPane = new Slides.SlidePane();
                    slides2.topPane.showsZoom = !optJSONObject.optString("showsZoom").equalsIgnoreCase("NO");
                    slides2.topPane.url = optJSONObject.optString(FeedDetail.F_URL).equalsIgnoreCase("null") ? null : cleanForDemoMode(optJSONObject.optString(FeedDetail.F_URL));
                }
                JSONObject optJSONObject2 = jSONObject4.optJSONObject("bottomPane");
                if (optJSONObject2 != null) {
                    slides2.bottomPane = new Slides.SlidePane();
                    slides2.bottomPane.showsZoom = !optJSONObject2.optString("showsZoom").equalsIgnoreCase("NO");
                    Slides.SlidePane slidePane = slides2.bottomPane;
                    if (!optJSONObject2.optString(FeedDetail.F_URL).equalsIgnoreCase("null")) {
                        str3 = cleanForDemoMode(optJSONObject2.optString(FeedDetail.F_URL));
                    }
                    slidePane.url = str3;
                }
                slideshow.slides[i3] = slides2;
            }
        }
        return slideshow;
    }

    private static String cleanForDemoMode(String str) {
        if (!isDemoModeOn()) {
            return str;
        }
        return str.replace("localfile://", "file://" + MedscapeApplication.get().getPreferences().getString(Constants.PREF_DEMO_ROOT_DIR, "") + "/");
    }

    public static boolean isDemoModeOn() {
        return MedscapeApplication.get().getPreferences().getBoolean(Constants.PREF_DEMO, false);
    }
}
