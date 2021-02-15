package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import javax.net.ssl.SSLSocketFactory;

/* compiled from: com.google.android.gms:play-services-ads@@19.4.0 */
final class zzben extends SSLSocketFactory {
    private SSLSocketFactory zzekh = ((SSLSocketFactory) SSLSocketFactory.getDefault());
    private final /* synthetic */ zzbek zzeki;

    zzben(zzbek zzbek) {
        this.zzeki = zzbek;
    }

    public final String[] getDefaultCipherSuites() {
        return this.zzekh.getDefaultCipherSuites();
    }

    public final String[] getSupportedCipherSuites() {
        return this.zzekh.getSupportedCipherSuites();
    }

    public final Socket createSocket(Socket socket, String str, int i, boolean z) throws IOException {
        return zzb(this.zzekh.createSocket(socket, str, i, z));
    }

    public final Socket createSocket(String str, int i) throws IOException {
        return zzb(this.zzekh.createSocket(str, i));
    }

    public final Socket createSocket(String str, int i, InetAddress inetAddress, int i2) throws IOException {
        return zzb(this.zzekh.createSocket(str, i, inetAddress, i2));
    }

    public final Socket createSocket(InetAddress inetAddress, int i) throws IOException {
        return zzb(this.zzekh.createSocket(inetAddress, i));
    }

    public final Socket createSocket(InetAddress inetAddress, int i, InetAddress inetAddress2, int i2) throws IOException {
        return zzb(this.zzekh.createSocket(inetAddress, i, inetAddress2, i2));
    }

    private final Socket zzb(Socket socket) throws SocketException {
        if (this.zzeki.zzeka > 0) {
            socket.setReceiveBufferSize(this.zzeki.zzeka);
        }
        this.zzeki.zza(socket);
        return socket;
    }
}
