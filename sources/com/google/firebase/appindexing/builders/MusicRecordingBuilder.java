package com.google.firebase.appindexing.builders;

/* compiled from: com.google.firebase:firebase-appindexing@@19.1.0 */
public final class MusicRecordingBuilder extends IndexableBuilder<MusicRecordingBuilder> {
    MusicRecordingBuilder() {
        super("MusicRecording");
    }

    public final MusicRecordingBuilder setDuration(int i) {
        return (MusicRecordingBuilder) put("duration", (long) i);
    }

    public final MusicRecordingBuilder setByArtist(MusicGroupBuilder musicGroupBuilder) {
        return (MusicRecordingBuilder) put("byArtist", (S[]) new MusicGroupBuilder[]{musicGroupBuilder});
    }

    public final MusicRecordingBuilder setInAlbum(MusicAlbumBuilder musicAlbumBuilder) {
        return (MusicRecordingBuilder) put("inAlbum", (S[]) new MusicAlbumBuilder[]{musicAlbumBuilder});
    }

    public final MusicRecordingBuilder setInPlaylist(MusicPlaylistBuilder... musicPlaylistBuilderArr) {
        return (MusicRecordingBuilder) put("inPlaylist", (S[]) musicPlaylistBuilderArr);
    }
}
