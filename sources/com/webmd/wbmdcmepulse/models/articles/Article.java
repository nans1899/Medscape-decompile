package com.webmd.wbmdcmepulse.models.articles;

import android.os.Parcel;
import android.os.Parcelable;
import com.webmd.wbmdcmepulse.models.video.VideoChapters;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Article implements Parcelable {
    public static final Parcelable.Creator<Article> CREATOR = new Parcelable.Creator<Article>() {
        public Article createFromParcel(Parcel parcel) {
            return new Article(parcel);
        }

        public Article[] newArray(int i) {
            return new Article[i];
        }
    };
    public String[] abbreviations;
    public String assetId;
    public List<Contributor> authors;
    public String[] blockCode;
    public String byLine;
    public String cmeFlag;
    public Contributor cmeReviwereNursePlanner;
    public String contentGroup;
    public String contentType;
    public List<Contributor> contributors;
    public CreditInstructions creditInstructions;
    public List<Eligibility> eligibilities;
    public Calendar expirationDate;
    public String goalStatement;
    public String id;
    public boolean isMocEligible;
    public boolean isPostTestPassed;
    public String leadConcept;
    public String leadSpec;
    public String metaDescription;
    public MiscProvider miscProvider;
    public List<HtmlObject> objectiveStatement;
    public List<ArticlePage> pages;
    public HtmlObject pearlsForPractice;
    public String prescriptionToLeanUrl;
    public String qnaId;
    public List<QuestionPageMapItem> questionPageMap;
    public References references;
    public Calendar releaseDate;
    public String siteOn;
    public ArrayList<Slide> slides;
    public String slidesDownloadUrl;
    public HtmlObject studyHighlights;
    public List<String> supportGrantBadges;
    public boolean suppressCmeLink;
    public String targetAudienceStatement;
    public String title;
    public List<Version> versions;
    public String videoCCURL;
    public VideoChapters videoChapters;
    public String videoConfigURL;
    public String videoUrl;
    public String webReprintUrl;

    public int describeContents() {
        return 0;
    }

    public Article() {
        this.pages = new ArrayList();
        this.authors = new ArrayList();
        this.supportGrantBadges = new ArrayList();
        this.contributors = new ArrayList();
        this.questionPageMap = new ArrayList();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.id);
        parcel.writeString(this.title);
        parcel.writeString(this.byLine);
        parcel.writeString(this.cmeFlag);
        parcel.writeString(this.qnaId);
        parcel.writeString(this.siteOn);
        parcel.writeString(this.contentType);
        parcel.writeString(this.videoUrl);
        parcel.writeString(this.slidesDownloadUrl);
        parcel.writeString(this.prescriptionToLeanUrl);
        parcel.writeString(this.webReprintUrl);
        parcel.writeByte(this.suppressCmeLink ? (byte) 1 : 0);
        parcel.writeParcelable(this.references, i);
        parcel.writeString(this.targetAudienceStatement);
        parcel.writeString(this.goalStatement);
        parcel.writeSerializable(this.releaseDate);
        parcel.writeSerializable(this.expirationDate);
        parcel.writeStringList(this.supportGrantBadges);
        parcel.writeTypedList(this.slides);
        parcel.writeTypedList(this.pages);
        parcel.writeTypedList(this.objectiveStatement);
        parcel.writeParcelable(this.creditInstructions, i);
        parcel.writeTypedList(this.contributors);
        parcel.writeTypedList(this.authors);
        parcel.writeParcelable(this.cmeReviwereNursePlanner, i);
        parcel.writeTypedList(this.eligibilities);
        parcel.writeTypedList(this.versions);
        parcel.writeParcelable(this.pearlsForPractice, i);
        parcel.writeParcelable(this.studyHighlights, i);
        parcel.writeString(this.contentGroup);
        parcel.writeStringArray(this.blockCode);
        parcel.writeString(this.leadConcept);
        parcel.writeString(this.leadSpec);
        parcel.writeString(this.assetId);
        parcel.writeTypedList(this.questionPageMap);
        parcel.writeStringArray(this.abbreviations);
        parcel.writeByte(this.isMocEligible ? (byte) 1 : 0);
        parcel.writeByte(this.isPostTestPassed ? (byte) 1 : 0);
        parcel.writeString(this.metaDescription);
        parcel.writeParcelable(this.miscProvider, i);
        parcel.writeString(this.videoConfigURL);
        parcel.writeParcelable(this.videoChapters, i);
        parcel.writeString(this.videoCCURL);
    }

    protected Article(Parcel parcel) {
        this.id = parcel.readString();
        this.title = parcel.readString();
        this.byLine = parcel.readString();
        this.cmeFlag = parcel.readString();
        this.qnaId = parcel.readString();
        this.siteOn = parcel.readString();
        this.contentType = parcel.readString();
        this.videoUrl = parcel.readString();
        this.slidesDownloadUrl = parcel.readString();
        this.prescriptionToLeanUrl = parcel.readString();
        this.webReprintUrl = parcel.readString();
        boolean z = true;
        this.suppressCmeLink = parcel.readByte() != 0;
        this.references = (References) parcel.readParcelable(References.class.getClassLoader());
        this.targetAudienceStatement = parcel.readString();
        this.goalStatement = parcel.readString();
        this.releaseDate = (Calendar) parcel.readSerializable();
        this.expirationDate = (Calendar) parcel.readSerializable();
        this.supportGrantBadges = parcel.createStringArrayList();
        this.slides = parcel.createTypedArrayList(Slide.CREATOR);
        this.pages = parcel.createTypedArrayList(ArticlePage.CREATOR);
        this.objectiveStatement = parcel.createTypedArrayList(HtmlObject.CREATOR);
        this.creditInstructions = (CreditInstructions) parcel.readParcelable(CreditInstructions.class.getClassLoader());
        this.contributors = parcel.createTypedArrayList(Contributor.CREATOR);
        this.authors = parcel.createTypedArrayList(Contributor.CREATOR);
        this.cmeReviwereNursePlanner = (Contributor) parcel.readParcelable(Contributor.class.getClassLoader());
        this.eligibilities = parcel.createTypedArrayList(Eligibility.CREATOR);
        this.versions = parcel.createTypedArrayList(Version.CREATOR);
        this.pearlsForPractice = (HtmlObject) parcel.readParcelable(HtmlObject.class.getClassLoader());
        this.studyHighlights = (HtmlObject) parcel.readParcelable(HtmlObject.class.getClassLoader());
        this.contentGroup = parcel.readString();
        this.blockCode = parcel.createStringArray();
        this.leadConcept = parcel.readString();
        this.leadSpec = parcel.readString();
        this.assetId = parcel.readString();
        this.questionPageMap = parcel.createTypedArrayList(QuestionPageMapItem.CREATOR);
        this.abbreviations = parcel.createStringArray();
        this.isMocEligible = parcel.readByte() != 0;
        this.isPostTestPassed = parcel.readByte() == 0 ? false : z;
        this.metaDescription = parcel.readString();
        this.miscProvider = (MiscProvider) parcel.readParcelable(MiscProvider.class.getClassLoader());
        this.videoConfigURL = parcel.readString();
        this.videoChapters = (VideoChapters) parcel.readParcelable(VideoChapters.class.getClassLoader());
        this.videoCCURL = parcel.readString();
    }
}
