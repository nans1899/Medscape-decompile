package org.conscrypt;

import java.io.FileDescriptor;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.nio.channels.SocketChannel;
import java.security.PrivateKey;
import javax.net.ssl.HandshakeCompletedListener;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;

public abstract class OpenSSLSocketImpl extends AbstractConscryptSocket {
    public abstract byte[] getChannelId() throws SSLException;

    public abstract SSLSession getHandshakeSession();

    public abstract void setChannelIdEnabled(boolean z);

    public abstract void setChannelIdPrivateKey(PrivateKey privateKey);

    public abstract void setUseSessionTickets(boolean z);

    public /* bridge */ /* synthetic */ void addHandshakeCompletedListener(HandshakeCompletedListener handshakeCompletedListener) {
        super.addHandshakeCompletedListener(handshakeCompletedListener);
    }

    public /* bridge */ /* synthetic */ void bind(SocketAddress socketAddress) throws IOException {
        super.bind(socketAddress);
    }

    public /* bridge */ /* synthetic */ void close() throws IOException {
        super.close();
    }

    public /* bridge */ /* synthetic */ SocketChannel getChannel() {
        return super.getChannel();
    }

    public /* bridge */ /* synthetic */ InetAddress getInetAddress() {
        return super.getInetAddress();
    }

    public /* bridge */ /* synthetic */ InputStream getInputStream() throws IOException {
        return super.getInputStream();
    }

    public /* bridge */ /* synthetic */ boolean getKeepAlive() throws SocketException {
        return super.getKeepAlive();
    }

    public /* bridge */ /* synthetic */ InetAddress getLocalAddress() {
        return super.getLocalAddress();
    }

    public /* bridge */ /* synthetic */ int getLocalPort() {
        return super.getLocalPort();
    }

    public /* bridge */ /* synthetic */ SocketAddress getLocalSocketAddress() {
        return super.getLocalSocketAddress();
    }

    public /* bridge */ /* synthetic */ boolean getOOBInline() throws SocketException {
        return super.getOOBInline();
    }

    public /* bridge */ /* synthetic */ OutputStream getOutputStream() throws IOException {
        return super.getOutputStream();
    }

    public /* bridge */ /* synthetic */ int getReceiveBufferSize() throws SocketException {
        return super.getReceiveBufferSize();
    }

    public /* bridge */ /* synthetic */ SocketAddress getRemoteSocketAddress() {
        return super.getRemoteSocketAddress();
    }

    public /* bridge */ /* synthetic */ boolean getReuseAddress() throws SocketException {
        return super.getReuseAddress();
    }

    public /* bridge */ /* synthetic */ int getSendBufferSize() throws SocketException {
        return super.getSendBufferSize();
    }

    public /* bridge */ /* synthetic */ int getSoLinger() throws SocketException {
        return super.getSoLinger();
    }

    public /* bridge */ /* synthetic */ boolean getTcpNoDelay() throws SocketException {
        return super.getTcpNoDelay();
    }

    public /* bridge */ /* synthetic */ int getTrafficClass() throws SocketException {
        return super.getTrafficClass();
    }

    public /* bridge */ /* synthetic */ boolean isBound() {
        return super.isBound();
    }

    public /* bridge */ /* synthetic */ boolean isClosed() {
        return super.isClosed();
    }

    public /* bridge */ /* synthetic */ boolean isConnected() {
        return super.isConnected();
    }

    public /* bridge */ /* synthetic */ boolean isInputShutdown() {
        return super.isInputShutdown();
    }

    public /* bridge */ /* synthetic */ boolean isOutputShutdown() {
        return super.isOutputShutdown();
    }

    public /* bridge */ /* synthetic */ void removeHandshakeCompletedListener(HandshakeCompletedListener handshakeCompletedListener) {
        super.removeHandshakeCompletedListener(handshakeCompletedListener);
    }

    public /* bridge */ /* synthetic */ void setKeepAlive(boolean z) throws SocketException {
        super.setKeepAlive(z);
    }

    public /* bridge */ /* synthetic */ void setPerformancePreferences(int i, int i2, int i3) {
        super.setPerformancePreferences(i, i2, i3);
    }

    public /* bridge */ /* synthetic */ void setReceiveBufferSize(int i) throws SocketException {
        super.setReceiveBufferSize(i);
    }

    public /* bridge */ /* synthetic */ void setReuseAddress(boolean z) throws SocketException {
        super.setReuseAddress(z);
    }

    public /* bridge */ /* synthetic */ void setSendBufferSize(int i) throws SocketException {
        super.setSendBufferSize(i);
    }

    public /* bridge */ /* synthetic */ void setSoLinger(boolean z, int i) throws SocketException {
        super.setSoLinger(z, i);
    }

    public /* bridge */ /* synthetic */ void setTcpNoDelay(boolean z) throws SocketException {
        super.setTcpNoDelay(z);
    }

    public /* bridge */ /* synthetic */ void setTrafficClass(int i) throws SocketException {
        super.setTrafficClass(i);
    }

    public /* bridge */ /* synthetic */ void shutdownInput() throws IOException {
        super.shutdownInput();
    }

    public /* bridge */ /* synthetic */ void shutdownOutput() throws IOException {
        super.shutdownOutput();
    }

    public /* bridge */ /* synthetic */ String toString() {
        return super.toString();
    }

    OpenSSLSocketImpl() throws IOException {
    }

    OpenSSLSocketImpl(String str, int i) throws IOException {
        super(str, i);
    }

    OpenSSLSocketImpl(InetAddress inetAddress, int i) throws IOException {
        super(inetAddress, i);
    }

    OpenSSLSocketImpl(String str, int i, InetAddress inetAddress, int i2) throws IOException {
        super(str, i, inetAddress, i2);
    }

    OpenSSLSocketImpl(InetAddress inetAddress, int i, InetAddress inetAddress2, int i2) throws IOException {
        super(inetAddress, i, inetAddress2, i2);
    }

    OpenSSLSocketImpl(Socket socket, String str, int i, boolean z) throws IOException {
        super(socket, str, i, z);
    }

    public String getHostname() {
        return super.getHostname();
    }

    public void setHostname(String str) {
        super.setHostname(str);
    }

    public String getHostnameOrIP() {
        return super.getHostnameOrIP();
    }

    public FileDescriptor getFileDescriptor$() {
        return super.getFileDescriptor$();
    }

    public void setSoWriteTimeout(int i) throws SocketException {
        super.setSoWriteTimeout(i);
    }

    public int getSoWriteTimeout() throws SocketException {
        return super.getSoWriteTimeout();
    }

    public void setHandshakeTimeout(int i) throws SocketException {
        super.setHandshakeTimeout(i);
    }

    @Deprecated
    public final byte[] getNpnSelectedProtocol() {
        return super.getNpnSelectedProtocol();
    }

    @Deprecated
    public final void setNpnProtocols(byte[] bArr) {
        super.setNpnProtocols(bArr);
    }

    @Deprecated
    public final void setAlpnProtocols(String[] strArr) {
        if (strArr == null) {
            strArr = EmptyArray.STRING;
        }
        setApplicationProtocols(strArr);
    }

    @Deprecated
    public final byte[] getAlpnSelectedProtocol() {
        return SSLUtils.toProtocolBytes(getApplicationProtocol());
    }

    @Deprecated
    public final void setAlpnProtocols(byte[] bArr) {
        if (bArr == null) {
            bArr = EmptyArray.BYTE;
        }
        setApplicationProtocols(SSLUtils.decodeProtocols(bArr));
    }
}
