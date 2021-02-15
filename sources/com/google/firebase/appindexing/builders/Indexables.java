package com.google.firebase.appindexing.builders;

import com.google.android.gms.common.internal.Preconditions;
import com.google.firebase.appindexing.Indexable;

/* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
public final class Indexables {
    private Indexables() {
    }

    public static Indexable newSimple(String str, String str2) {
        Preconditions.checkNotNull(str);
        Preconditions.checkNotNull(str2);
        return ((Indexable.Builder) ((Indexable.Builder) new Indexable.Builder().setUrl(str2)).setName(str)).build();
    }

    public static AudiobookBuilder audiobookBuilder() {
        return new AudiobookBuilder();
    }

    public static BookBuilder bookBuilder() {
        return new BookBuilder();
    }

    public static ConversationBuilder conversationBuilder() {
        return new ConversationBuilder();
    }

    public static DigitalDocumentBuilder digitalDocumentBuilder() {
        return new DigitalDocumentBuilder();
    }

    public static DigitalDocumentBuilder textDigitalDocumentBuilder() {
        return new DigitalDocumentBuilder("TextDigitalDocument");
    }

    public static DigitalDocumentBuilder presentationDigitalDocumentBuilder() {
        return new DigitalDocumentBuilder("PresentationDigitalDocument");
    }

    public static DigitalDocumentBuilder spreadsheetDigitalDocumentBuilder() {
        return new DigitalDocumentBuilder("SpreadsheetDigitalDocument");
    }

    public static DigitalDocumentBuilder noteDigitalDocumentBuilder() {
        return new DigitalDocumentBuilder("NoteDigitalDocument");
    }

    public static MessageBuilder messageBuilder() {
        return new MessageBuilder();
    }

    public static MessageBuilder emailMessageBuilder() {
        return new MessageBuilder("EmailMessage");
    }

    public static MusicAlbumBuilder musicAlbumBuilder() {
        return new MusicAlbumBuilder();
    }

    public static MusicGroupBuilder musicGroupBuilder() {
        return new MusicGroupBuilder();
    }

    public static MusicPlaylistBuilder musicPlaylistBuilder() {
        return new MusicPlaylistBuilder();
    }

    public static MusicRecordingBuilder musicRecordingBuilder() {
        return new MusicRecordingBuilder();
    }

    public static DigitalDocumentPermissionBuilder digitalDocumentPermissionBuilder() {
        return new DigitalDocumentPermissionBuilder();
    }

    public static PersonBuilder personBuilder() {
        return new PersonBuilder();
    }

    public static LocalBusinessBuilder localBusinessBuilder() {
        return new LocalBusinessBuilder();
    }

    public static LocalBusinessBuilder restaurantBuilder() {
        return new LocalBusinessBuilder("Restaurant");
    }

    public static PostalAddressBuilder postalAddressBuilder() {
        return new PostalAddressBuilder();
    }

    public static AggregateRatingBuilder aggregateRatingBuilder() {
        return new AggregateRatingBuilder();
    }

    public static ReservationBuilder reservationBuilder() {
        return new ReservationBuilder();
    }

    public static GeoShapeBuilder geoShapeBuilder() {
        return new GeoShapeBuilder();
    }

    public static StickerBuilder stickerBuilder() {
        return new StickerBuilder();
    }

    public static StickerPackBuilder stickerPackBuilder() {
        return new StickerPackBuilder();
    }

    public static PlaceBuilder placeBuilder() {
        return new PlaceBuilder();
    }

    public static AlarmBuilder alarmBuilder() {
        return new AlarmBuilder();
    }

    public static AlarmInstanceBuilder alarmInstanceBuilder() {
        return new AlarmInstanceBuilder();
    }

    public static StopwatchBuilder stopwatchBuilder() {
        return new StopwatchBuilder();
    }

    public static StopwatchLapBuilder stopwatchLapBuilder() {
        return new StopwatchLapBuilder();
    }

    public static TimerBuilder timerBuilder() {
        return new TimerBuilder();
    }
}
