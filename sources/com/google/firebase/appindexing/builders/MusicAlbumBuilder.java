package com.google.firebase.appindexing.builders;

/* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
public final class MusicAlbumBuilder extends IndexableBuilder<MusicAlbumBuilder> {
    MusicAlbumBuilder() {
        super("MusicAlbum");
    }

    public final MusicAlbumBuilder setByArtist(MusicGroupBuilder musicGroupBuilder) {
        return (MusicAlbumBuilder) put("byArtist", (S[]) new MusicGroupBuilder[]{musicGroupBuilder});
    }

    public final MusicAlbumBuilder setNumTracks(int i) {
        return (MusicAlbumBuilder) put("numTracks", (long) i);
    }

    public final MusicAlbumBuilder setTrack(MusicRecordingBuilder... musicRecordingBuilderArr) {
        return (MusicAlbumBuilder) put("track", (S[]) musicRecordingBuilderArr);
    }
}
