package org.apache.commons.io.input;

import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.apache.commons.io.input.ObservableInputStream;

public class MessageDigestCalculatingInputStream extends ObservableInputStream {
    private final MessageDigest messageDigest;

    public static class MessageDigestMaintainingObserver extends ObservableInputStream.Observer {
        private final MessageDigest messageDigest;

        public MessageDigestMaintainingObserver(MessageDigest messageDigest2) {
            this.messageDigest = messageDigest2;
        }

        public void data(int i) throws IOException {
            this.messageDigest.update((byte) i);
        }

        public void data(byte[] bArr, int i, int i2) throws IOException {
            this.messageDigest.update(bArr, i, i2);
        }
    }

    public MessageDigestCalculatingInputStream(InputStream inputStream, MessageDigest messageDigest2) {
        super(inputStream);
        this.messageDigest = messageDigest2;
        add(new MessageDigestMaintainingObserver(messageDigest2));
    }

    public MessageDigestCalculatingInputStream(InputStream inputStream, String str) throws NoSuchAlgorithmException {
        this(inputStream, MessageDigest.getInstance(str));
    }

    public MessageDigestCalculatingInputStream(InputStream inputStream) throws NoSuchAlgorithmException {
        this(inputStream, MessageDigest.getInstance("MD5"));
    }

    public MessageDigest getMessageDigest() {
        return this.messageDigest;
    }
}
