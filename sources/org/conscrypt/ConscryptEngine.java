package org.conscrypt;

import java.io.IOException;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.ReadOnlyBufferException;
import java.security.Principal;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.crypto.SecretKey;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLEngineResult;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSession;
import javax.net.ssl.X509ExtendedKeyManager;
import javax.net.ssl.X509KeyManager;
import javax.net.ssl.X509TrustManager;
import javax.security.auth.x500.X500Principal;
import org.conscrypt.ExternalSession;
import org.conscrypt.NativeCrypto;
import org.conscrypt.NativeRef;
import org.conscrypt.NativeSsl;
import org.conscrypt.SSLParametersImpl;

final class ConscryptEngine extends AbstractConscryptEngine implements NativeCrypto.SSLHandshakeCallbacks, SSLParametersImpl.AliasChooser, SSLParametersImpl.PSKCallbacks {
    private static final SSLEngineResult CLOSED_NOT_HANDSHAKING = new SSLEngineResult(SSLEngineResult.Status.CLOSED, SSLEngineResult.HandshakeStatus.NOT_HANDSHAKING, 0, 0);
    private static final SSLEngineResult NEED_UNWRAP_CLOSED = new SSLEngineResult(SSLEngineResult.Status.CLOSED, SSLEngineResult.HandshakeStatus.NEED_UNWRAP, 0, 0);
    private static final SSLEngineResult NEED_UNWRAP_OK = new SSLEngineResult(SSLEngineResult.Status.OK, SSLEngineResult.HandshakeStatus.NEED_UNWRAP, 0, 0);
    private static final SSLEngineResult NEED_WRAP_CLOSED = new SSLEngineResult(SSLEngineResult.Status.CLOSED, SSLEngineResult.HandshakeStatus.NEED_WRAP, 0, 0);
    private static final SSLEngineResult NEED_WRAP_OK = new SSLEngineResult(SSLEngineResult.Status.OK, SSLEngineResult.HandshakeStatus.NEED_WRAP, 0, 0);
    private static BufferAllocator defaultBufferAllocator = null;
    private ActiveSession activeSession;
    private BufferAllocator bufferAllocator = defaultBufferAllocator;
    private OpenSSLKey channelIdPrivateKey;
    private SessionSnapshot closedSession;
    private final SSLSession externalSession = Platform.wrapSSLSession(new ExternalSession(new ExternalSession.Provider() {
        public ConscryptSession provideSession() {
            return ConscryptEngine.this.provideSession();
        }
    }));
    private boolean handshakeFinished;
    private HandshakeListener handshakeListener;
    private ByteBuffer lazyDirectBuffer;
    private int maxSealOverhead;
    private final NativeSsl.BioWrapper networkBio;
    private String peerHostname;
    private final PeerInfoProvider peerInfoProvider;
    private final ByteBuffer[] singleDstBuffer = new ByteBuffer[1];
    private final ByteBuffer[] singleSrcBuffer = new ByteBuffer[1];
    private final NativeSsl ssl;
    private final SSLParametersImpl sslParameters;
    private int state = 0;

    public Runnable getDelegatedTask() {
        return null;
    }

    public long serverSessionRequested(byte[] bArr) {
        return 0;
    }

    ConscryptEngine(SSLParametersImpl sSLParametersImpl) {
        this.sslParameters = sSLParametersImpl;
        this.peerInfoProvider = PeerInfoProvider.nullProvider();
        NativeSsl newSsl = newSsl(sSLParametersImpl, this);
        this.ssl = newSsl;
        this.networkBio = newSsl.newBio();
    }

    ConscryptEngine(String str, int i, SSLParametersImpl sSLParametersImpl) {
        this.sslParameters = sSLParametersImpl;
        this.peerInfoProvider = PeerInfoProvider.forHostAndPort(str, i);
        NativeSsl newSsl = newSsl(sSLParametersImpl, this);
        this.ssl = newSsl;
        this.networkBio = newSsl.newBio();
    }

    ConscryptEngine(SSLParametersImpl sSLParametersImpl, PeerInfoProvider peerInfoProvider2) {
        this.sslParameters = sSLParametersImpl;
        this.peerInfoProvider = (PeerInfoProvider) Preconditions.checkNotNull(peerInfoProvider2, "peerInfoProvider");
        NativeSsl newSsl = newSsl(sSLParametersImpl, this);
        this.ssl = newSsl;
        this.networkBio = newSsl.newBio();
    }

    private static NativeSsl newSsl(SSLParametersImpl sSLParametersImpl, ConscryptEngine conscryptEngine) {
        try {
            return NativeSsl.newInstance(sSLParametersImpl, conscryptEngine, conscryptEngine, conscryptEngine);
        } catch (SSLException e) {
            throw new RuntimeException(e);
        }
    }

    static void setDefaultBufferAllocator(BufferAllocator bufferAllocator2) {
        defaultBufferAllocator = bufferAllocator2;
    }

    static BufferAllocator getDefaultBufferAllocator() {
        return defaultBufferAllocator;
    }

    /* access modifiers changed from: package-private */
    public void setBufferAllocator(BufferAllocator bufferAllocator2) {
        synchronized (this.ssl) {
            if (!isHandshakeStarted()) {
                this.bufferAllocator = bufferAllocator2;
            } else {
                throw new IllegalStateException("Could not set buffer allocator after the initial handshake has begun.");
            }
        }
    }

    /* access modifiers changed from: package-private */
    public int maxSealOverhead() {
        return this.maxSealOverhead;
    }

    /* access modifiers changed from: package-private */
    public void setChannelIdEnabled(boolean z) {
        synchronized (this.ssl) {
            if (getUseClientMode()) {
                throw new IllegalStateException("Not allowed in client mode");
            } else if (!isHandshakeStarted()) {
                this.sslParameters.channelIdEnabled = z;
            } else {
                throw new IllegalStateException("Could not enable/disable Channel ID after the initial handshake has begun.");
            }
        }
    }

    /* access modifiers changed from: package-private */
    public byte[] getChannelId() throws SSLException {
        byte[] tlsChannelId;
        synchronized (this.ssl) {
            if (getUseClientMode()) {
                throw new IllegalStateException("Not allowed in client mode");
            } else if (!isHandshakeStarted()) {
                tlsChannelId = this.ssl.getTlsChannelId();
            } else {
                throw new IllegalStateException("Channel ID is only available after handshake completes");
            }
        }
        return tlsChannelId;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Can't wrap try/catch for region: R(9:12|13|14|(1:16)|(1:18)|19|20|21|22) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:20:0x003d */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void setChannelIdPrivateKey(java.security.PrivateKey r5) {
        /*
            r4 = this;
            boolean r0 = r4.getUseClientMode()
            if (r0 == 0) goto L_0x004a
            org.conscrypt.NativeSsl r0 = r4.ssl
            monitor-enter(r0)
            boolean r1 = r4.isHandshakeStarted()     // Catch:{ all -> 0x0047 }
            if (r1 != 0) goto L_0x003f
            r1 = 0
            if (r5 != 0) goto L_0x001b
            org.conscrypt.SSLParametersImpl r5 = r4.sslParameters     // Catch:{ all -> 0x0047 }
            r2 = 0
            r5.channelIdEnabled = r2     // Catch:{ all -> 0x0047 }
            r4.channelIdPrivateKey = r1     // Catch:{ all -> 0x0047 }
            monitor-exit(r0)     // Catch:{ all -> 0x0047 }
            return
        L_0x001b:
            org.conscrypt.SSLParametersImpl r2 = r4.sslParameters     // Catch:{ all -> 0x0047 }
            r3 = 1
            r2.channelIdEnabled = r3     // Catch:{ all -> 0x0047 }
            boolean r2 = r5 instanceof java.security.interfaces.ECKey     // Catch:{ InvalidKeyException -> 0x003d }
            if (r2 == 0) goto L_0x002b
            r1 = r5
            java.security.interfaces.ECKey r1 = (java.security.interfaces.ECKey) r1     // Catch:{ InvalidKeyException -> 0x003d }
            java.security.spec.ECParameterSpec r1 = r1.getParams()     // Catch:{ InvalidKeyException -> 0x003d }
        L_0x002b:
            if (r1 != 0) goto L_0x0037
            java.lang.String r1 = "prime256v1"
            org.conscrypt.OpenSSLECGroupContext r1 = org.conscrypt.OpenSSLECGroupContext.getCurveByName(r1)     // Catch:{ InvalidKeyException -> 0x003d }
            java.security.spec.ECParameterSpec r1 = r1.getECParameterSpec()     // Catch:{ InvalidKeyException -> 0x003d }
        L_0x0037:
            org.conscrypt.OpenSSLKey r5 = org.conscrypt.OpenSSLKey.fromECPrivateKeyForTLSStackOnly(r5, r1)     // Catch:{ InvalidKeyException -> 0x003d }
            r4.channelIdPrivateKey = r5     // Catch:{ InvalidKeyException -> 0x003d }
        L_0x003d:
            monitor-exit(r0)     // Catch:{ all -> 0x0047 }
            return
        L_0x003f:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException     // Catch:{ all -> 0x0047 }
            java.lang.String r1 = "Could not change Channel ID private key after the initial handshake has begun."
            r5.<init>(r1)     // Catch:{ all -> 0x0047 }
            throw r5     // Catch:{ all -> 0x0047 }
        L_0x0047:
            r5 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0047 }
            throw r5
        L_0x004a:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r0 = "Not allowed in server mode"
            r5.<init>(r0)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.conscrypt.ConscryptEngine.setChannelIdPrivateKey(java.security.PrivateKey):void");
    }

    /* access modifiers changed from: package-private */
    public void setHandshakeListener(HandshakeListener handshakeListener2) {
        synchronized (this.ssl) {
            if (!isHandshakeStarted()) {
                this.handshakeListener = handshakeListener2;
            } else {
                throw new IllegalStateException("Handshake listener must be set before starting the handshake.");
            }
        }
    }

    private boolean isHandshakeStarted() {
        int i = this.state;
        return (i == 0 || i == 1) ? false : true;
    }

    /* access modifiers changed from: package-private */
    public void setHostname(String str) {
        this.sslParameters.setUseSni(str != null);
        this.peerHostname = str;
    }

    /* access modifiers changed from: package-private */
    public String getHostname() {
        String str = this.peerHostname;
        return str != null ? str : this.peerInfoProvider.getHostname();
    }

    public String getPeerHost() {
        String str = this.peerHostname;
        return str != null ? str : this.peerInfoProvider.getHostnameOrIP();
    }

    public int getPeerPort() {
        return this.peerInfoProvider.getPort();
    }

    public void beginHandshake() throws SSLException {
        synchronized (this.ssl) {
            beginHandshakeInternal();
        }
    }

    private void beginHandshakeInternal() throws SSLException {
        NativeSslSession cachedSession;
        int i = this.state;
        if (i == 0) {
            throw new IllegalStateException("Client/server mode must be set before handshake");
        } else if (i == 1) {
            transitionTo(2);
            try {
                this.ssl.initialize(getHostname(), this.channelIdPrivateKey);
                if (getUseClientMode() && (cachedSession = clientSessionContext().getCachedSession(getHostname(), getPeerPort(), this.sslParameters)) != null) {
                    cachedSession.offerToResume(this.ssl);
                }
                this.maxSealOverhead = this.ssl.getMaxSealOverhead();
                handshake();
            } catch (IOException e) {
                if (e.getMessage().contains("unexpected CCS")) {
                    Platform.logEvent(String.format("ssl_unexpected_ccs: host=%s", new Object[]{getPeerHost()}));
                }
                throw SSLUtils.toSSLHandshakeException(e);
            } catch (Throwable th) {
                closeAndFreeResources();
                throw th;
            }
        } else if (i == 6 || i == 7 || i == 8) {
            throw new IllegalStateException("Engine has already been closed");
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0029, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x002b, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void closeInbound() {
        /*
            r5 = this;
            org.conscrypt.NativeSsl r0 = r5.ssl
            monitor-enter(r0)
            int r1 = r5.state     // Catch:{ all -> 0x002c }
            r2 = 8
            if (r1 == r2) goto L_0x002a
            int r1 = r5.state     // Catch:{ all -> 0x002c }
            r3 = 6
            if (r1 != r3) goto L_0x000f
            goto L_0x002a
        L_0x000f:
            boolean r1 = r5.isHandshakeStarted()     // Catch:{ all -> 0x002c }
            if (r1 == 0) goto L_0x0025
            int r1 = r5.state     // Catch:{ all -> 0x002c }
            r4 = 7
            if (r1 != r4) goto L_0x001e
            r5.transitionTo(r2)     // Catch:{ all -> 0x002c }
            goto L_0x0021
        L_0x001e:
            r5.transitionTo(r3)     // Catch:{ all -> 0x002c }
        L_0x0021:
            r5.freeIfDone()     // Catch:{ all -> 0x002c }
            goto L_0x0028
        L_0x0025:
            r5.closeAndFreeResources()     // Catch:{ all -> 0x002c }
        L_0x0028:
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            return
        L_0x002a:
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            return
        L_0x002c:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002c }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.conscrypt.ConscryptEngine.closeInbound():void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x002c, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x002e, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void closeOutbound() {
        /*
            r5 = this;
            org.conscrypt.NativeSsl r0 = r5.ssl
            monitor-enter(r0)
            int r1 = r5.state     // Catch:{ all -> 0x002f }
            r2 = 8
            if (r1 == r2) goto L_0x002d
            int r1 = r5.state     // Catch:{ all -> 0x002f }
            r3 = 7
            if (r1 != r3) goto L_0x000f
            goto L_0x002d
        L_0x000f:
            boolean r1 = r5.isHandshakeStarted()     // Catch:{ all -> 0x002f }
            if (r1 == 0) goto L_0x0028
            int r1 = r5.state     // Catch:{ all -> 0x002f }
            r4 = 6
            if (r1 != r4) goto L_0x001e
            r5.transitionTo(r2)     // Catch:{ all -> 0x002f }
            goto L_0x0021
        L_0x001e:
            r5.transitionTo(r3)     // Catch:{ all -> 0x002f }
        L_0x0021:
            r5.sendSSLShutdown()     // Catch:{ all -> 0x002f }
            r5.freeIfDone()     // Catch:{ all -> 0x002f }
            goto L_0x002b
        L_0x0028:
            r5.closeAndFreeResources()     // Catch:{ all -> 0x002f }
        L_0x002b:
            monitor-exit(r0)     // Catch:{ all -> 0x002f }
            return
        L_0x002d:
            monitor-exit(r0)     // Catch:{ all -> 0x002f }
            return
        L_0x002f:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x002f }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.conscrypt.ConscryptEngine.closeOutbound():void");
    }

    public String[] getEnabledCipherSuites() {
        return this.sslParameters.getEnabledCipherSuites();
    }

    public String[] getEnabledProtocols() {
        return this.sslParameters.getEnabledProtocols();
    }

    public boolean getEnableSessionCreation() {
        return this.sslParameters.getEnableSessionCreation();
    }

    public SSLParameters getSSLParameters() {
        SSLParameters sSLParameters = super.getSSLParameters();
        Platform.getSSLParameters(sSLParameters, this.sslParameters, this);
        return sSLParameters;
    }

    public void setSSLParameters(SSLParameters sSLParameters) {
        super.setSSLParameters(sSLParameters);
        Platform.setSSLParameters(sSLParameters, this.sslParameters, this);
    }

    public SSLEngineResult.HandshakeStatus getHandshakeStatus() {
        SSLEngineResult.HandshakeStatus handshakeStatusInternal;
        synchronized (this.ssl) {
            handshakeStatusInternal = getHandshakeStatusInternal();
        }
        return handshakeStatusInternal;
    }

    private SSLEngineResult.HandshakeStatus getHandshakeStatusInternal() {
        if (this.handshakeFinished) {
            return SSLEngineResult.HandshakeStatus.NOT_HANDSHAKING;
        }
        switch (this.state) {
            case 0:
            case 1:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
                return SSLEngineResult.HandshakeStatus.NOT_HANDSHAKING;
            case 2:
                return pendingStatus(pendingOutboundEncryptedBytes());
            case 3:
                return SSLEngineResult.HandshakeStatus.NEED_WRAP;
            default:
                throw new IllegalStateException("Unexpected engine state: " + this.state);
        }
    }

    /* access modifiers changed from: package-private */
    public int pendingOutboundEncryptedBytes() {
        return this.networkBio.getPendingWrittenBytes();
    }

    private int pendingInboundCleartextBytes() {
        return this.ssl.getPendingReadableBytes();
    }

    private static SSLEngineResult.HandshakeStatus pendingStatus(int i) {
        return i > 0 ? SSLEngineResult.HandshakeStatus.NEED_WRAP : SSLEngineResult.HandshakeStatus.NEED_UNWRAP;
    }

    public boolean getNeedClientAuth() {
        return this.sslParameters.getNeedClientAuth();
    }

    /* access modifiers changed from: package-private */
    public SSLSession handshakeSession() {
        synchronized (this.ssl) {
            if (this.state != 2) {
                return null;
            }
            SSLSession wrapSSLSession = Platform.wrapSSLSession(new ExternalSession(new ExternalSession.Provider() {
                public ConscryptSession provideSession() {
                    return ConscryptEngine.this.provideHandshakeSession();
                }
            }));
            return wrapSSLSession;
        }
    }

    public SSLSession getSession() {
        return this.externalSession;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0015, code lost:
        return r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.conscrypt.ConscryptSession provideSession() {
        /*
            r3 = this;
            org.conscrypt.NativeSsl r0 = r3.ssl
            monitor-enter(r0)
            int r1 = r3.state     // Catch:{ all -> 0x0025 }
            r2 = 8
            if (r1 != r2) goto L_0x0016
            org.conscrypt.SessionSnapshot r1 = r3.closedSession     // Catch:{ all -> 0x0025 }
            if (r1 == 0) goto L_0x0010
            org.conscrypt.SessionSnapshot r1 = r3.closedSession     // Catch:{ all -> 0x0025 }
            goto L_0x0014
        L_0x0010:
            org.conscrypt.ConscryptSession r1 = org.conscrypt.SSLNullSession.getNullSession()     // Catch:{ all -> 0x0025 }
        L_0x0014:
            monitor-exit(r0)     // Catch:{ all -> 0x0025 }
            return r1
        L_0x0016:
            int r1 = r3.state     // Catch:{ all -> 0x0025 }
            r2 = 3
            if (r1 >= r2) goto L_0x0021
            org.conscrypt.ConscryptSession r1 = org.conscrypt.SSLNullSession.getNullSession()     // Catch:{ all -> 0x0025 }
            monitor-exit(r0)     // Catch:{ all -> 0x0025 }
            return r1
        L_0x0021:
            org.conscrypt.ActiveSession r1 = r3.activeSession     // Catch:{ all -> 0x0025 }
            monitor-exit(r0)     // Catch:{ all -> 0x0025 }
            return r1
        L_0x0025:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0025 }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.conscrypt.ConscryptEngine.provideSession():org.conscrypt.ConscryptSession");
    }

    /* access modifiers changed from: private */
    public ConscryptSession provideHandshakeSession() {
        ConscryptSession conscryptSession;
        synchronized (this.ssl) {
            if (this.state == 2) {
                conscryptSession = this.activeSession;
            } else {
                conscryptSession = SSLNullSession.getNullSession();
            }
        }
        return conscryptSession;
    }

    public String[] getSupportedCipherSuites() {
        return NativeCrypto.getSupportedCipherSuites();
    }

    public String[] getSupportedProtocols() {
        return NativeCrypto.getSupportedProtocols();
    }

    public boolean getUseClientMode() {
        return this.sslParameters.getUseClientMode();
    }

    public boolean getWantClientAuth() {
        return this.sslParameters.getWantClientAuth();
    }

    public boolean isInboundDone() {
        boolean z;
        synchronized (this.ssl) {
            z = (this.state == 8 || this.state == 6 || this.ssl.wasShutdownReceived()) && pendingInboundCleartextBytes() == 0;
        }
        return z;
    }

    public boolean isOutboundDone() {
        boolean z;
        synchronized (this.ssl) {
            z = (this.state == 8 || this.state == 7 || this.ssl.wasShutdownSent()) && pendingOutboundEncryptedBytes() == 0;
        }
        return z;
    }

    public void setEnabledCipherSuites(String[] strArr) {
        this.sslParameters.setEnabledCipherSuites(strArr);
    }

    public void setEnabledProtocols(String[] strArr) {
        this.sslParameters.setEnabledProtocols(strArr);
    }

    public void setEnableSessionCreation(boolean z) {
        this.sslParameters.setEnableSessionCreation(z);
    }

    public void setNeedClientAuth(boolean z) {
        this.sslParameters.setNeedClientAuth(z);
    }

    public void setUseClientMode(boolean z) {
        synchronized (this.ssl) {
            if (!isHandshakeStarted()) {
                transitionTo(1);
                this.sslParameters.setUseClientMode(z);
            } else {
                throw new IllegalArgumentException("Can not change mode after handshake: state == " + this.state);
            }
        }
    }

    public void setWantClientAuth(boolean z) {
        this.sslParameters.setWantClientAuth(z);
    }

    public SSLEngineResult unwrap(ByteBuffer byteBuffer, ByteBuffer byteBuffer2) throws SSLException {
        SSLEngineResult unwrap;
        synchronized (this.ssl) {
            try {
                unwrap = unwrap(singleSrcBuffer(byteBuffer), singleDstBuffer(byteBuffer2));
                resetSingleSrcBuffer();
                resetSingleDstBuffer();
            } catch (Throwable th) {
                resetSingleSrcBuffer();
                resetSingleDstBuffer();
                throw th;
            }
        }
        return unwrap;
    }

    public SSLEngineResult unwrap(ByteBuffer byteBuffer, ByteBuffer[] byteBufferArr) throws SSLException {
        SSLEngineResult unwrap;
        synchronized (this.ssl) {
            try {
                unwrap = unwrap(singleSrcBuffer(byteBuffer), byteBufferArr);
                resetSingleSrcBuffer();
            } catch (Throwable th) {
                resetSingleSrcBuffer();
                throw th;
            }
        }
        return unwrap;
    }

    public SSLEngineResult unwrap(ByteBuffer byteBuffer, ByteBuffer[] byteBufferArr, int i, int i2) throws SSLException {
        SSLEngineResult unwrap;
        synchronized (this.ssl) {
            try {
                unwrap = unwrap(singleSrcBuffer(byteBuffer), 0, 1, byteBufferArr, i, i2);
                resetSingleSrcBuffer();
            } catch (Throwable th) {
                resetSingleSrcBuffer();
                throw th;
            }
        }
        return unwrap;
    }

    /* access modifiers changed from: package-private */
    public SSLEngineResult unwrap(ByteBuffer[] byteBufferArr, ByteBuffer[] byteBufferArr2) throws SSLException {
        boolean z = true;
        Preconditions.checkArgument(byteBufferArr != null, "srcs is null");
        if (byteBufferArr2 == null) {
            z = false;
        }
        Preconditions.checkArgument(z, "dsts is null");
        return unwrap(byteBufferArr, 0, byteBufferArr.length, byteBufferArr2, 0, byteBufferArr2.length);
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:100:0x0122, code lost:
        r0 = newResult(r10, r0, r13);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:103:0x0127, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:105:?, code lost:
        closeInbound();
        sendSSLShutdown();
        r3 = javax.net.ssl.SSLEngineResult.Status.CLOSED;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:106:0x0136, code lost:
        if (pendingOutboundEncryptedBytes() <= 0) goto L_0x013b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:107:0x0138, code lost:
        r4 = javax.net.ssl.SSLEngineResult.HandshakeStatus.NEED_WRAP;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:108:0x013b, code lost:
        r4 = javax.net.ssl.SSLEngineResult.HandshakeStatus.NOT_HANDSHAKING;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:109:0x013d, code lost:
        r2 = new javax.net.ssl.SSLEngineResult(r3, r4, r10, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:112:0x0141, code lost:
        return r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:133:0x0170, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:134:0x0171, code lost:
        sendSSLShutdown();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:135:0x0178, code lost:
        throw convertException(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:136:0x0179, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:137:0x017a, code lost:
        closeAll();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:138:0x0181, code lost:
        throw convertException(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:143:0x0188, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:144:0x0189, code lost:
        sendSSLShutdown();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:145:0x0190, code lost:
        throw convertException(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:92:0x010f, code lost:
        if (r5 == -6) goto L_0x0128;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:94:0x0112, code lost:
        if (r5 == -3) goto L_0x0122;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:96:0x0115, code lost:
        if (r5 != -2) goto L_0x0118;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:98:0x0118, code lost:
        sendSSLShutdown();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:99:0x0121, code lost:
        throw newSslExceptionWithMessage("SSL_read");
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [B:81:0x00f4, B:115:0x0144] */
    /* JADX WARNING: Removed duplicated region for block: B:133:0x0170 A[Catch:{ SSLException -> 0x0188, InterruptedIOException -> 0x0182, EOFException -> 0x0179, IOException -> 0x0170 }, ExcHandler: IOException (r0v8 'e' java.io.IOException A[CUSTOM_DECLARE, Catch:{  }]), Splitter:B:81:0x00f4] */
    /* JADX WARNING: Removed duplicated region for block: B:136:0x0179 A[Catch:{ SSLException -> 0x0188, InterruptedIOException -> 0x0182, EOFException -> 0x0179, IOException -> 0x0170 }, ExcHandler: EOFException (r0v6 'e' java.io.EOFException A[CUSTOM_DECLARE, Catch:{  }]), Splitter:B:81:0x00f4] */
    /* JADX WARNING: Removed duplicated region for block: B:143:0x0188 A[Catch:{ SSLException -> 0x0188, InterruptedIOException -> 0x0182, EOFException -> 0x0179, IOException -> 0x0170 }, ExcHandler: SSLException (r0v3 'e' javax.net.ssl.SSLException A[CUSTOM_DECLARE, Catch:{  }]), Splitter:B:81:0x00f4] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public javax.net.ssl.SSLEngineResult unwrap(java.nio.ByteBuffer[] r18, int r19, int r20, java.nio.ByteBuffer[] r21, int r22, int r23) throws javax.net.ssl.SSLException {
        /*
            r17 = this;
            r1 = r17
            r0 = r18
            r2 = r19
            r3 = r21
            r4 = r22
            r5 = 1
            r6 = 0
            if (r0 == 0) goto L_0x0010
            r7 = 1
            goto L_0x0011
        L_0x0010:
            r7 = 0
        L_0x0011:
            java.lang.String r8 = "srcs is null"
            org.conscrypt.Preconditions.checkArgument(r7, r8)
            if (r3 == 0) goto L_0x001a
            r7 = 1
            goto L_0x001b
        L_0x001a:
            r7 = 0
        L_0x001b:
            java.lang.String r8 = "dsts is null"
            org.conscrypt.Preconditions.checkArgument(r7, r8)
            int r7 = r2 + r20
            int r8 = r0.length
            org.conscrypt.Preconditions.checkPositionIndexes(r2, r7, r8)
            int r8 = r4 + r23
            int r9 = r3.length
            org.conscrypt.Preconditions.checkPositionIndexes(r4, r8, r9)
            int r9 = calcDstsLength(r21, r22, r23)
            long r10 = calcSrcsLength(r0, r2, r7)
            org.conscrypt.NativeSsl r12 = r1.ssl
            monitor-enter(r12)
            int r13 = r1.state     // Catch:{ all -> 0x0199 }
            if (r13 == 0) goto L_0x0191
            r14 = 8
            if (r13 == r5) goto L_0x0055
            r15 = 6
            if (r13 == r15) goto L_0x0045
            if (r13 == r14) goto L_0x0045
            goto L_0x0058
        L_0x0045:
            r17.freeIfDone()     // Catch:{ all -> 0x0199 }
            javax.net.ssl.SSLEngineResult r0 = new javax.net.ssl.SSLEngineResult     // Catch:{ all -> 0x0199 }
            javax.net.ssl.SSLEngineResult$Status r2 = javax.net.ssl.SSLEngineResult.Status.CLOSED     // Catch:{ all -> 0x0199 }
            javax.net.ssl.SSLEngineResult$HandshakeStatus r3 = r17.getHandshakeStatusInternal()     // Catch:{ all -> 0x0199 }
            r0.<init>(r2, r3, r6, r6)     // Catch:{ all -> 0x0199 }
            monitor-exit(r12)     // Catch:{ all -> 0x0199 }
            return r0
        L_0x0055:
            r17.beginHandshakeInternal()     // Catch:{ all -> 0x0199 }
        L_0x0058:
            javax.net.ssl.SSLEngineResult$HandshakeStatus r13 = javax.net.ssl.SSLEngineResult.HandshakeStatus.NOT_HANDSHAKING     // Catch:{ all -> 0x0199 }
            boolean r15 = r1.handshakeFinished     // Catch:{ all -> 0x0199 }
            if (r15 != 0) goto L_0x0072
            javax.net.ssl.SSLEngineResult$HandshakeStatus r13 = r17.handshake()     // Catch:{ all -> 0x0199 }
            javax.net.ssl.SSLEngineResult$HandshakeStatus r15 = javax.net.ssl.SSLEngineResult.HandshakeStatus.NEED_WRAP     // Catch:{ all -> 0x0199 }
            if (r13 != r15) goto L_0x006a
            javax.net.ssl.SSLEngineResult r0 = NEED_WRAP_OK     // Catch:{ all -> 0x0199 }
            monitor-exit(r12)     // Catch:{ all -> 0x0199 }
            return r0
        L_0x006a:
            int r15 = r1.state     // Catch:{ all -> 0x0199 }
            if (r15 != r14) goto L_0x0072
            javax.net.ssl.SSLEngineResult r0 = NEED_WRAP_CLOSED     // Catch:{ all -> 0x0199 }
            monitor-exit(r12)     // Catch:{ all -> 0x0199 }
            return r0
        L_0x0072:
            int r14 = r17.pendingInboundCleartextBytes()     // Catch:{ all -> 0x0199 }
            if (r14 > 0) goto L_0x0079
            goto L_0x007a
        L_0x0079:
            r5 = 0
        L_0x007a:
            r14 = 0
            int r16 = (r10 > r14 ? 1 : (r10 == r14 ? 0 : -1))
            if (r16 <= 0) goto L_0x00b5
            if (r5 == 0) goto L_0x00b5
            r14 = 5
            int r5 = (r10 > r14 ? 1 : (r10 == r14 ? 0 : -1))
            if (r5 >= 0) goto L_0x0095
            javax.net.ssl.SSLEngineResult r0 = new javax.net.ssl.SSLEngineResult     // Catch:{ all -> 0x0199 }
            javax.net.ssl.SSLEngineResult$Status r2 = javax.net.ssl.SSLEngineResult.Status.BUFFER_UNDERFLOW     // Catch:{ all -> 0x0199 }
            javax.net.ssl.SSLEngineResult$HandshakeStatus r3 = r17.getHandshakeStatus()     // Catch:{ all -> 0x0199 }
            r0.<init>(r2, r3, r6, r6)     // Catch:{ all -> 0x0199 }
            monitor-exit(r12)     // Catch:{ all -> 0x0199 }
            return r0
        L_0x0095:
            int r5 = org.conscrypt.SSLUtils.getEncryptedPacketLength(r18, r19)     // Catch:{ all -> 0x0199 }
            if (r5 < 0) goto L_0x00ad
            long r14 = (long) r5     // Catch:{ all -> 0x0199 }
            int r16 = (r10 > r14 ? 1 : (r10 == r14 ? 0 : -1))
            if (r16 >= 0) goto L_0x00c5
            javax.net.ssl.SSLEngineResult r0 = new javax.net.ssl.SSLEngineResult     // Catch:{ all -> 0x0199 }
            javax.net.ssl.SSLEngineResult$Status r2 = javax.net.ssl.SSLEngineResult.Status.BUFFER_UNDERFLOW     // Catch:{ all -> 0x0199 }
            javax.net.ssl.SSLEngineResult$HandshakeStatus r3 = r17.getHandshakeStatus()     // Catch:{ all -> 0x0199 }
            r0.<init>(r2, r3, r6, r6)     // Catch:{ all -> 0x0199 }
            monitor-exit(r12)     // Catch:{ all -> 0x0199 }
            return r0
        L_0x00ad:
            javax.net.ssl.SSLException r0 = new javax.net.ssl.SSLException     // Catch:{ all -> 0x0199 }
            java.lang.String r2 = "Unable to parse TLS packet header"
            r0.<init>(r2)     // Catch:{ all -> 0x0199 }
            throw r0     // Catch:{ all -> 0x0199 }
        L_0x00b5:
            if (r5 == 0) goto L_0x00c4
            javax.net.ssl.SSLEngineResult r0 = new javax.net.ssl.SSLEngineResult     // Catch:{ all -> 0x0199 }
            javax.net.ssl.SSLEngineResult$Status r2 = javax.net.ssl.SSLEngineResult.Status.BUFFER_UNDERFLOW     // Catch:{ all -> 0x0199 }
            javax.net.ssl.SSLEngineResult$HandshakeStatus r3 = r17.getHandshakeStatus()     // Catch:{ all -> 0x0199 }
            r0.<init>(r2, r3, r6, r6)     // Catch:{ all -> 0x0199 }
            monitor-exit(r12)     // Catch:{ all -> 0x0199 }
            return r0
        L_0x00c4:
            r5 = 0
        L_0x00c5:
            if (r5 <= 0) goto L_0x00ee
            if (r2 >= r7) goto L_0x00ee
            r10 = 0
        L_0x00ca:
            r11 = r0[r2]     // Catch:{ all -> 0x0199 }
            int r14 = r11.remaining()     // Catch:{ all -> 0x0199 }
            if (r14 != 0) goto L_0x00d5
        L_0x00d2:
            int r2 = r2 + 1
            goto L_0x00e7
        L_0x00d5:
            int r15 = java.lang.Math.min(r5, r14)     // Catch:{ all -> 0x0199 }
            int r11 = r1.writeEncryptedData(r11, r15)     // Catch:{ all -> 0x0199 }
            if (r11 <= 0) goto L_0x00ea
            int r10 = r10 + r11
            int r5 = r5 - r11
            if (r5 != 0) goto L_0x00e4
            goto L_0x00ef
        L_0x00e4:
            if (r11 != r14) goto L_0x00ef
            goto L_0x00d2
        L_0x00e7:
            if (r2 < r7) goto L_0x00ca
            goto L_0x00ef
        L_0x00ea:
            org.conscrypt.NativeCrypto.SSL_clear_error()     // Catch:{ all -> 0x0199 }
            goto L_0x00ef
        L_0x00ee:
            r10 = 0
        L_0x00ef:
            if (r9 <= 0) goto L_0x0144
            r0 = 0
        L_0x00f2:
            if (r4 >= r8) goto L_0x014a
            r2 = r3[r4]     // Catch:{ SSLException -> 0x0188, InterruptedIOException -> 0x0142, EOFException -> 0x0179, IOException -> 0x0170 }
            boolean r5 = r2.hasRemaining()     // Catch:{ SSLException -> 0x0188, InterruptedIOException -> 0x0142, EOFException -> 0x0179, IOException -> 0x0170 }
            if (r5 != 0) goto L_0x00fd
            goto L_0x010b
        L_0x00fd:
            int r5 = r1.readPlaintextData(r2)     // Catch:{ SSLException -> 0x0188, InterruptedIOException -> 0x0142, EOFException -> 0x0179, IOException -> 0x0170 }
            if (r5 <= 0) goto L_0x010e
            int r0 = r0 + r5
            boolean r2 = r2.hasRemaining()     // Catch:{ SSLException -> 0x0188, InterruptedIOException -> 0x0142, EOFException -> 0x0179, IOException -> 0x0170 }
            if (r2 == 0) goto L_0x010b
            goto L_0x014a
        L_0x010b:
            int r4 = r4 + 1
            goto L_0x00f2
        L_0x010e:
            r2 = -6
            if (r5 == r2) goto L_0x0128
            r2 = -3
            if (r5 == r2) goto L_0x0122
            r2 = -2
            if (r5 != r2) goto L_0x0118
            goto L_0x0122
        L_0x0118:
            r17.sendSSLShutdown()     // Catch:{ SSLException -> 0x0188, InterruptedIOException -> 0x0142, EOFException -> 0x0179, IOException -> 0x0170 }
            java.lang.String r2 = "SSL_read"
            javax.net.ssl.SSLException r2 = r1.newSslExceptionWithMessage(r2)     // Catch:{ SSLException -> 0x0188, InterruptedIOException -> 0x0142, EOFException -> 0x0179, IOException -> 0x0170 }
            throw r2     // Catch:{ SSLException -> 0x0188, InterruptedIOException -> 0x0142, EOFException -> 0x0179, IOException -> 0x0170 }
        L_0x0122:
            javax.net.ssl.SSLEngineResult r0 = r1.newResult(r10, r0, r13)     // Catch:{ SSLException -> 0x0188, InterruptedIOException -> 0x0142, EOFException -> 0x0179, IOException -> 0x0170 }
            monitor-exit(r12)     // Catch:{ all -> 0x0199 }
            return r0
        L_0x0128:
            r17.closeInbound()     // Catch:{ SSLException -> 0x0188, InterruptedIOException -> 0x0142, EOFException -> 0x0179, IOException -> 0x0170 }
            r17.sendSSLShutdown()     // Catch:{ SSLException -> 0x0188, InterruptedIOException -> 0x0142, EOFException -> 0x0179, IOException -> 0x0170 }
            javax.net.ssl.SSLEngineResult r2 = new javax.net.ssl.SSLEngineResult     // Catch:{ SSLException -> 0x0188, InterruptedIOException -> 0x0142, EOFException -> 0x0179, IOException -> 0x0170 }
            javax.net.ssl.SSLEngineResult$Status r3 = javax.net.ssl.SSLEngineResult.Status.CLOSED     // Catch:{ SSLException -> 0x0188, InterruptedIOException -> 0x0142, EOFException -> 0x0179, IOException -> 0x0170 }
            int r4 = r17.pendingOutboundEncryptedBytes()     // Catch:{ SSLException -> 0x0188, InterruptedIOException -> 0x0142, EOFException -> 0x0179, IOException -> 0x0170 }
            if (r4 <= 0) goto L_0x013b
            javax.net.ssl.SSLEngineResult$HandshakeStatus r4 = javax.net.ssl.SSLEngineResult.HandshakeStatus.NEED_WRAP     // Catch:{ SSLException -> 0x0188, InterruptedIOException -> 0x0142, EOFException -> 0x0179, IOException -> 0x0170 }
            goto L_0x013d
        L_0x013b:
            javax.net.ssl.SSLEngineResult$HandshakeStatus r4 = javax.net.ssl.SSLEngineResult.HandshakeStatus.NOT_HANDSHAKING     // Catch:{ SSLException -> 0x0188, InterruptedIOException -> 0x0142, EOFException -> 0x0179, IOException -> 0x0170 }
        L_0x013d:
            r2.<init>(r3, r4, r10, r0)     // Catch:{ SSLException -> 0x0188, InterruptedIOException -> 0x0142, EOFException -> 0x0179, IOException -> 0x0170 }
            monitor-exit(r12)     // Catch:{ all -> 0x0199 }
            return r2
        L_0x0142:
            r6 = r0
            goto L_0x0182
        L_0x0144:
            org.conscrypt.NativeSsl r0 = r1.ssl     // Catch:{ SSLException -> 0x0188, InterruptedIOException -> 0x0182, EOFException -> 0x0179, IOException -> 0x0170 }
            r0.forceRead()     // Catch:{ SSLException -> 0x0188, InterruptedIOException -> 0x0182, EOFException -> 0x0179, IOException -> 0x0170 }
            r0 = 0
        L_0x014a:
            boolean r2 = r1.handshakeFinished     // Catch:{ all -> 0x0199 }
            if (r2 == 0) goto L_0x0152
            int r6 = r17.pendingInboundCleartextBytes()     // Catch:{ all -> 0x0199 }
        L_0x0152:
            if (r6 <= 0) goto L_0x016a
            javax.net.ssl.SSLEngineResult r2 = new javax.net.ssl.SSLEngineResult     // Catch:{ all -> 0x0199 }
            javax.net.ssl.SSLEngineResult$Status r3 = javax.net.ssl.SSLEngineResult.Status.BUFFER_OVERFLOW     // Catch:{ all -> 0x0199 }
            javax.net.ssl.SSLEngineResult$HandshakeStatus r4 = javax.net.ssl.SSLEngineResult.HandshakeStatus.FINISHED     // Catch:{ all -> 0x0199 }
            if (r13 != r4) goto L_0x015d
            goto L_0x0161
        L_0x015d:
            javax.net.ssl.SSLEngineResult$HandshakeStatus r13 = r17.getHandshakeStatusInternal()     // Catch:{ all -> 0x0199 }
        L_0x0161:
            javax.net.ssl.SSLEngineResult$HandshakeStatus r4 = r1.mayFinishHandshake(r13)     // Catch:{ all -> 0x0199 }
            r2.<init>(r3, r4, r10, r0)     // Catch:{ all -> 0x0199 }
            monitor-exit(r12)     // Catch:{ all -> 0x0199 }
            return r2
        L_0x016a:
            javax.net.ssl.SSLEngineResult r0 = r1.newResult(r10, r0, r13)     // Catch:{ all -> 0x0199 }
            monitor-exit(r12)     // Catch:{ all -> 0x0199 }
            return r0
        L_0x0170:
            r0 = move-exception
            r17.sendSSLShutdown()     // Catch:{ all -> 0x0199 }
            javax.net.ssl.SSLException r0 = r1.convertException(r0)     // Catch:{ all -> 0x0199 }
            throw r0     // Catch:{ all -> 0x0199 }
        L_0x0179:
            r0 = move-exception
            r17.closeAll()     // Catch:{ all -> 0x0199 }
            javax.net.ssl.SSLException r0 = r1.convertException(r0)     // Catch:{ all -> 0x0199 }
            throw r0     // Catch:{ all -> 0x0199 }
        L_0x0182:
            javax.net.ssl.SSLEngineResult r0 = r1.newResult(r10, r6, r13)     // Catch:{ all -> 0x0199 }
            monitor-exit(r12)     // Catch:{ all -> 0x0199 }
            return r0
        L_0x0188:
            r0 = move-exception
            r17.sendSSLShutdown()     // Catch:{ all -> 0x0199 }
            javax.net.ssl.SSLException r0 = r1.convertException(r0)     // Catch:{ all -> 0x0199 }
            throw r0     // Catch:{ all -> 0x0199 }
        L_0x0191:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException     // Catch:{ all -> 0x0199 }
            java.lang.String r2 = "Client/server mode must be set before calling unwrap"
            r0.<init>(r2)     // Catch:{ all -> 0x0199 }
            throw r0     // Catch:{ all -> 0x0199 }
        L_0x0199:
            r0 = move-exception
            monitor-exit(r12)     // Catch:{ all -> 0x0199 }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.conscrypt.ConscryptEngine.unwrap(java.nio.ByteBuffer[], int, int, java.nio.ByteBuffer[], int, int):javax.net.ssl.SSLEngineResult");
    }

    private static int calcDstsLength(ByteBuffer[] byteBufferArr, int i, int i2) {
        int i3 = 0;
        int i4 = 0;
        while (i3 < byteBufferArr.length) {
            ByteBuffer byteBuffer = byteBufferArr[i3];
            Preconditions.checkArgument(byteBuffer != null, "dsts[%d] is null", Integer.valueOf(i3));
            if (!byteBuffer.isReadOnly()) {
                if (i3 >= i && i3 < i + i2) {
                    i4 += byteBuffer.remaining();
                }
                i3++;
            } else {
                throw new ReadOnlyBufferException();
            }
        }
        return i4;
    }

    private static long calcSrcsLength(ByteBuffer[] byteBufferArr, int i, int i2) {
        long j = 0;
        while (i < i2) {
            ByteBuffer byteBuffer = byteBufferArr[i];
            if (byteBuffer != null) {
                j += (long) byteBuffer.remaining();
                i++;
            } else {
                throw new IllegalArgumentException("srcs[" + i + "] is null");
            }
        }
        return j;
    }

    private SSLEngineResult.HandshakeStatus handshake() throws SSLException {
        try {
            int doHandshake = this.ssl.doHandshake();
            if (doHandshake == 2) {
                return pendingStatus(pendingOutboundEncryptedBytes());
            }
            if (doHandshake == 3) {
                return SSLEngineResult.HandshakeStatus.NEED_WRAP;
            }
            this.activeSession.onPeerCertificateAvailable(getPeerHost(), getPeerPort());
            finishHandshake();
            return SSLEngineResult.HandshakeStatus.FINISHED;
        } catch (SSLException e) {
            sendSSLShutdown();
            throw e;
        } catch (IOException e2) {
            sendSSLShutdown();
            throw e2;
        } catch (Exception e3) {
            throw SSLUtils.toSSLHandshakeException(e3);
        }
    }

    private void finishHandshake() throws SSLException {
        this.handshakeFinished = true;
        HandshakeListener handshakeListener2 = this.handshakeListener;
        if (handshakeListener2 != null) {
            handshakeListener2.onHandshakeFinished();
        }
    }

    private int writePlaintextData(ByteBuffer byteBuffer, int i) throws SSLException {
        int i2;
        try {
            int position = byteBuffer.position();
            if (byteBuffer.isDirect()) {
                i2 = writePlaintextDataDirect(byteBuffer, position, i);
            } else {
                i2 = writePlaintextDataHeap(byteBuffer, position, i);
            }
            if (i2 > 0) {
                byteBuffer.position(position + i2);
            }
            return i2;
        } catch (Exception e) {
            throw convertException(e);
        }
    }

    private int writePlaintextDataDirect(ByteBuffer byteBuffer, int i, int i2) throws IOException {
        return this.ssl.writeDirectByteBuffer(directByteBufferAddress(byteBuffer, i), i2);
    }

    private int writePlaintextDataHeap(ByteBuffer byteBuffer, int i, int i2) throws IOException {
        ByteBuffer byteBuffer2;
        AllocatedBuffer allocatedBuffer = null;
        try {
            if (this.bufferAllocator != null) {
                allocatedBuffer = this.bufferAllocator.allocateDirectBuffer(i2);
                byteBuffer2 = allocatedBuffer.nioBuffer();
            } else {
                byteBuffer2 = getOrCreateLazyDirectBuffer();
            }
            int limit = byteBuffer.limit();
            int min = Math.min(i2, byteBuffer2.remaining());
            byteBuffer.limit(i + min);
            byteBuffer2.put(byteBuffer);
            byteBuffer2.flip();
            byteBuffer.limit(limit);
            byteBuffer.position(i);
            return writePlaintextDataDirect(byteBuffer2, 0, min);
        } finally {
            if (allocatedBuffer != null) {
                allocatedBuffer.release();
            }
        }
    }

    private int readPlaintextData(ByteBuffer byteBuffer) throws IOException {
        try {
            int position = byteBuffer.position();
            int min = Math.min(16709, byteBuffer.limit() - position);
            if (!byteBuffer.isDirect()) {
                return readPlaintextDataHeap(byteBuffer, min);
            }
            int readPlaintextDataDirect = readPlaintextDataDirect(byteBuffer, position, min);
            if (readPlaintextDataDirect > 0) {
                byteBuffer.position(position + readPlaintextDataDirect);
            }
            return readPlaintextDataDirect;
        } catch (CertificateException e) {
            throw convertException(e);
        }
    }

    private int readPlaintextDataDirect(ByteBuffer byteBuffer, int i, int i2) throws IOException, CertificateException {
        return this.ssl.readDirectByteBuffer(directByteBufferAddress(byteBuffer, i), i2);
    }

    private int readPlaintextDataHeap(ByteBuffer byteBuffer, int i) throws IOException, CertificateException {
        ByteBuffer byteBuffer2;
        AllocatedBuffer allocatedBuffer = null;
        try {
            if (this.bufferAllocator != null) {
                allocatedBuffer = this.bufferAllocator.allocateDirectBuffer(i);
                byteBuffer2 = allocatedBuffer.nioBuffer();
            } else {
                byteBuffer2 = getOrCreateLazyDirectBuffer();
            }
            int readPlaintextDataDirect = readPlaintextDataDirect(byteBuffer2, 0, Math.min(i, byteBuffer2.remaining()));
            if (readPlaintextDataDirect > 0) {
                byteBuffer2.position(readPlaintextDataDirect);
                byteBuffer2.flip();
                byteBuffer.put(byteBuffer2);
            }
            return readPlaintextDataDirect;
        } finally {
            if (allocatedBuffer != null) {
                allocatedBuffer.release();
            }
        }
    }

    private SSLException convertException(Throwable th) {
        if ((th instanceof SSLHandshakeException) || !this.handshakeFinished) {
            return SSLUtils.toSSLHandshakeException(th);
        }
        return SSLUtils.toSSLException(th);
    }

    private int writeEncryptedData(ByteBuffer byteBuffer, int i) throws SSLException {
        int i2;
        try {
            int position = byteBuffer.position();
            if (byteBuffer.isDirect()) {
                i2 = writeEncryptedDataDirect(byteBuffer, position, i);
            } else {
                i2 = writeEncryptedDataHeap(byteBuffer, position, i);
            }
            if (i2 > 0) {
                byteBuffer.position(position + i2);
            }
            return i2;
        } catch (IOException e) {
            throw new SSLException(e);
        }
    }

    private int writeEncryptedDataDirect(ByteBuffer byteBuffer, int i, int i2) throws IOException {
        return this.networkBio.writeDirectByteBuffer(directByteBufferAddress(byteBuffer, i), i2);
    }

    private int writeEncryptedDataHeap(ByteBuffer byteBuffer, int i, int i2) throws IOException {
        ByteBuffer byteBuffer2;
        AllocatedBuffer allocatedBuffer = null;
        try {
            if (this.bufferAllocator != null) {
                allocatedBuffer = this.bufferAllocator.allocateDirectBuffer(i2);
                byteBuffer2 = allocatedBuffer.nioBuffer();
            } else {
                byteBuffer2 = getOrCreateLazyDirectBuffer();
            }
            int limit = byteBuffer.limit();
            int min = Math.min(Math.min(limit - i, i2), byteBuffer2.remaining());
            byteBuffer.limit(i + min);
            byteBuffer2.put(byteBuffer);
            byteBuffer.limit(limit);
            byteBuffer.position(i);
            int writeEncryptedDataDirect = writeEncryptedDataDirect(byteBuffer2, 0, min);
            byteBuffer.position(i);
            return writeEncryptedDataDirect;
        } finally {
            if (allocatedBuffer != null) {
                allocatedBuffer.release();
            }
        }
    }

    private ByteBuffer getOrCreateLazyDirectBuffer() {
        if (this.lazyDirectBuffer == null) {
            this.lazyDirectBuffer = ByteBuffer.allocateDirect(Math.max(16384, 16709));
        }
        this.lazyDirectBuffer.clear();
        return this.lazyDirectBuffer;
    }

    private long directByteBufferAddress(ByteBuffer byteBuffer, int i) {
        return NativeCrypto.getDirectBufferAddress(byteBuffer) + ((long) i);
    }

    private SSLEngineResult readPendingBytesFromBIO(ByteBuffer byteBuffer, int i, int i2, SSLEngineResult.HandshakeStatus handshakeStatus) throws SSLException {
        try {
            int pendingOutboundEncryptedBytes = pendingOutboundEncryptedBytes();
            if (pendingOutboundEncryptedBytes <= 0) {
                return null;
            }
            if (byteBuffer.remaining() < pendingOutboundEncryptedBytes) {
                SSLEngineResult.Status status = SSLEngineResult.Status.BUFFER_OVERFLOW;
                if (handshakeStatus != SSLEngineResult.HandshakeStatus.FINISHED) {
                    handshakeStatus = getHandshakeStatus(pendingOutboundEncryptedBytes);
                }
                return new SSLEngineResult(status, mayFinishHandshake(handshakeStatus), i, i2);
            }
            int readEncryptedData = readEncryptedData(byteBuffer, pendingOutboundEncryptedBytes);
            if (readEncryptedData <= 0) {
                NativeCrypto.SSL_clear_error();
            } else {
                i2 += readEncryptedData;
                pendingOutboundEncryptedBytes -= readEncryptedData;
            }
            SSLEngineResult.Status engineStatus = getEngineStatus();
            if (handshakeStatus != SSLEngineResult.HandshakeStatus.FINISHED) {
                handshakeStatus = getHandshakeStatus(pendingOutboundEncryptedBytes);
            }
            return new SSLEngineResult(engineStatus, mayFinishHandshake(handshakeStatus), i, i2);
        } catch (Exception e) {
            throw convertException(e);
        }
    }

    private int readEncryptedData(ByteBuffer byteBuffer, int i) throws SSLException {
        try {
            int position = byteBuffer.position();
            if (byteBuffer.remaining() < i) {
                return 0;
            }
            int min = Math.min(i, byteBuffer.limit() - position);
            if (!byteBuffer.isDirect()) {
                return readEncryptedDataHeap(byteBuffer, min);
            }
            int readEncryptedDataDirect = readEncryptedDataDirect(byteBuffer, position, min);
            if (readEncryptedDataDirect <= 0) {
                return readEncryptedDataDirect;
            }
            byteBuffer.position(position + readEncryptedDataDirect);
            return readEncryptedDataDirect;
        } catch (Exception e) {
            throw convertException(e);
        }
    }

    private int readEncryptedDataDirect(ByteBuffer byteBuffer, int i, int i2) throws IOException {
        return this.networkBio.readDirectByteBuffer(directByteBufferAddress(byteBuffer, i), i2);
    }

    private int readEncryptedDataHeap(ByteBuffer byteBuffer, int i) throws IOException {
        ByteBuffer byteBuffer2;
        AllocatedBuffer allocatedBuffer = null;
        try {
            if (this.bufferAllocator != null) {
                allocatedBuffer = this.bufferAllocator.allocateDirectBuffer(i);
                byteBuffer2 = allocatedBuffer.nioBuffer();
            } else {
                byteBuffer2 = getOrCreateLazyDirectBuffer();
            }
            int readEncryptedDataDirect = readEncryptedDataDirect(byteBuffer2, 0, Math.min(i, byteBuffer2.remaining()));
            if (readEncryptedDataDirect > 0) {
                byteBuffer2.position(readEncryptedDataDirect);
                byteBuffer2.flip();
                byteBuffer.put(byteBuffer2);
            }
            return readEncryptedDataDirect;
        } finally {
            if (allocatedBuffer != null) {
                allocatedBuffer.release();
            }
        }
    }

    private SSLEngineResult.HandshakeStatus mayFinishHandshake(SSLEngineResult.HandshakeStatus handshakeStatus) throws SSLException {
        return (this.handshakeFinished || handshakeStatus != SSLEngineResult.HandshakeStatus.NOT_HANDSHAKING) ? handshakeStatus : handshake();
    }

    private SSLEngineResult.HandshakeStatus getHandshakeStatus(int i) {
        return !this.handshakeFinished ? pendingStatus(i) : SSLEngineResult.HandshakeStatus.NOT_HANDSHAKING;
    }

    private SSLEngineResult.Status getEngineStatus() {
        int i = this.state;
        if (i == 6 || i == 7 || i == 8) {
            return SSLEngineResult.Status.CLOSED;
        }
        return SSLEngineResult.Status.OK;
    }

    private void closeAll() {
        closeOutbound();
        closeInbound();
    }

    private void freeIfDone() {
        if (isInboundDone() && isOutboundDone()) {
            closeAndFreeResources();
        }
    }

    private SSLException newSslExceptionWithMessage(String str) {
        if (!this.handshakeFinished) {
            return new SSLException(str);
        }
        return new SSLHandshakeException(str);
    }

    private SSLEngineResult newResult(int i, int i2, SSLEngineResult.HandshakeStatus handshakeStatus) throws SSLException {
        SSLEngineResult.Status engineStatus = getEngineStatus();
        if (handshakeStatus != SSLEngineResult.HandshakeStatus.FINISHED) {
            handshakeStatus = getHandshakeStatusInternal();
        }
        return new SSLEngineResult(engineStatus, mayFinishHandshake(handshakeStatus), i, i2);
    }

    public SSLEngineResult wrap(ByteBuffer byteBuffer, ByteBuffer byteBuffer2) throws SSLException {
        SSLEngineResult wrap;
        synchronized (this.ssl) {
            try {
                wrap = wrap(singleSrcBuffer(byteBuffer), byteBuffer2);
                resetSingleSrcBuffer();
            } catch (Throwable th) {
                resetSingleSrcBuffer();
                throw th;
            }
        }
        return wrap;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:100:0x012a, code lost:
        return r12;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:106:0x013e, code lost:
        return r12;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:92:0x0115, code lost:
        return r12;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public javax.net.ssl.SSLEngineResult wrap(java.nio.ByteBuffer[] r12, int r13, int r14, java.nio.ByteBuffer r15) throws javax.net.ssl.SSLException {
        /*
            r11 = this;
            r0 = 1
            r1 = 0
            if (r12 == 0) goto L_0x0006
            r2 = 1
            goto L_0x0007
        L_0x0006:
            r2 = 0
        L_0x0007:
            java.lang.String r3 = "srcs is null"
            org.conscrypt.Preconditions.checkArgument(r2, r3)
            if (r15 == 0) goto L_0x0010
            r2 = 1
            goto L_0x0011
        L_0x0010:
            r2 = 0
        L_0x0011:
            java.lang.String r3 = "dst is null"
            org.conscrypt.Preconditions.checkArgument(r2, r3)
            int r14 = r14 + r13
            int r2 = r12.length
            org.conscrypt.Preconditions.checkPositionIndexes(r13, r14, r2)
            boolean r2 = r15.isReadOnly()
            if (r2 != 0) goto L_0x015e
            org.conscrypt.NativeSsl r2 = r11.ssl
            monitor-enter(r2)
            int r3 = r11.state     // Catch:{ all -> 0x015b }
            if (r3 == 0) goto L_0x0153
            r4 = 8
            if (r3 == r0) goto L_0x004c
            r5 = 7
            if (r3 == r5) goto L_0x0032
            if (r3 == r4) goto L_0x0032
            goto L_0x004f
        L_0x0032:
            javax.net.ssl.SSLEngineResult$HandshakeStatus r12 = javax.net.ssl.SSLEngineResult.HandshakeStatus.NOT_HANDSHAKING     // Catch:{ all -> 0x015b }
            javax.net.ssl.SSLEngineResult r12 = r11.readPendingBytesFromBIO(r15, r1, r1, r12)     // Catch:{ all -> 0x015b }
            if (r12 == 0) goto L_0x003f
            r11.freeIfDone()     // Catch:{ all -> 0x015b }
            monitor-exit(r2)     // Catch:{ all -> 0x015b }
            return r12
        L_0x003f:
            javax.net.ssl.SSLEngineResult r12 = new javax.net.ssl.SSLEngineResult     // Catch:{ all -> 0x015b }
            javax.net.ssl.SSLEngineResult$Status r13 = javax.net.ssl.SSLEngineResult.Status.CLOSED     // Catch:{ all -> 0x015b }
            javax.net.ssl.SSLEngineResult$HandshakeStatus r14 = r11.getHandshakeStatusInternal()     // Catch:{ all -> 0x015b }
            r12.<init>(r13, r14, r1, r1)     // Catch:{ all -> 0x015b }
            monitor-exit(r2)     // Catch:{ all -> 0x015b }
            return r12
        L_0x004c:
            r11.beginHandshakeInternal()     // Catch:{ all -> 0x015b }
        L_0x004f:
            javax.net.ssl.SSLEngineResult$HandshakeStatus r3 = javax.net.ssl.SSLEngineResult.HandshakeStatus.NOT_HANDSHAKING     // Catch:{ all -> 0x015b }
            boolean r5 = r11.handshakeFinished     // Catch:{ all -> 0x015b }
            if (r5 != 0) goto L_0x0069
            javax.net.ssl.SSLEngineResult$HandshakeStatus r3 = r11.handshake()     // Catch:{ all -> 0x015b }
            javax.net.ssl.SSLEngineResult$HandshakeStatus r5 = javax.net.ssl.SSLEngineResult.HandshakeStatus.NEED_UNWRAP     // Catch:{ all -> 0x015b }
            if (r3 != r5) goto L_0x0061
            javax.net.ssl.SSLEngineResult r12 = NEED_UNWRAP_OK     // Catch:{ all -> 0x015b }
            monitor-exit(r2)     // Catch:{ all -> 0x015b }
            return r12
        L_0x0061:
            int r5 = r11.state     // Catch:{ all -> 0x015b }
            if (r5 != r4) goto L_0x0069
            javax.net.ssl.SSLEngineResult r12 = NEED_UNWRAP_CLOSED     // Catch:{ all -> 0x015b }
            monitor-exit(r2)     // Catch:{ all -> 0x015b }
            return r12
        L_0x0069:
            r4 = r13
            r5 = 0
        L_0x006b:
            r6 = 16384(0x4000, float:2.2959E-41)
            if (r4 >= r14) goto L_0x00a0
            r7 = r12[r4]     // Catch:{ all -> 0x015b }
            if (r7 == 0) goto L_0x0084
            if (r5 != r6) goto L_0x0076
            goto L_0x0081
        L_0x0076:
            int r7 = r7.remaining()     // Catch:{ all -> 0x015b }
            int r5 = r5 + r7
            if (r5 > r6) goto L_0x007f
            if (r5 >= 0) goto L_0x0081
        L_0x007f:
            r5 = 16384(0x4000, float:2.2959E-41)
        L_0x0081:
            int r4 = r4 + 1
            goto L_0x006b
        L_0x0084:
            java.lang.IllegalArgumentException r12 = new java.lang.IllegalArgumentException     // Catch:{ all -> 0x015b }
            java.lang.StringBuilder r13 = new java.lang.StringBuilder     // Catch:{ all -> 0x015b }
            r13.<init>()     // Catch:{ all -> 0x015b }
            java.lang.String r14 = "srcs["
            r13.append(r14)     // Catch:{ all -> 0x015b }
            r13.append(r4)     // Catch:{ all -> 0x015b }
            java.lang.String r14 = "] is null"
            r13.append(r14)     // Catch:{ all -> 0x015b }
            java.lang.String r13 = r13.toString()     // Catch:{ all -> 0x015b }
            r12.<init>(r13)     // Catch:{ all -> 0x015b }
            throw r12     // Catch:{ all -> 0x015b }
        L_0x00a0:
            int r4 = r15.remaining()     // Catch:{ all -> 0x015b }
            int r5 = org.conscrypt.SSLUtils.calculateOutNetBufSize(r5)     // Catch:{ all -> 0x015b }
            if (r4 >= r5) goto L_0x00b7
            javax.net.ssl.SSLEngineResult r12 = new javax.net.ssl.SSLEngineResult     // Catch:{ all -> 0x015b }
            javax.net.ssl.SSLEngineResult$Status r13 = javax.net.ssl.SSLEngineResult.Status.BUFFER_OVERFLOW     // Catch:{ all -> 0x015b }
            javax.net.ssl.SSLEngineResult$HandshakeStatus r14 = r11.getHandshakeStatusInternal()     // Catch:{ all -> 0x015b }
            r12.<init>(r13, r14, r1, r1)     // Catch:{ all -> 0x015b }
            monitor-exit(r2)     // Catch:{ all -> 0x015b }
            return r12
        L_0x00b7:
            r4 = 0
            r5 = 0
        L_0x00b9:
            if (r13 >= r14) goto L_0x0143
            r7 = r12[r13]     // Catch:{ all -> 0x015b }
            if (r7 == 0) goto L_0x00c1
            r8 = 1
            goto L_0x00c2
        L_0x00c1:
            r8 = 0
        L_0x00c2:
            java.lang.String r9 = "srcs[%d] is null"
            java.lang.Integer r10 = java.lang.Integer.valueOf(r13)     // Catch:{ all -> 0x015b }
            org.conscrypt.Preconditions.checkArgument(r8, r9, r10)     // Catch:{ all -> 0x015b }
        L_0x00cb:
            boolean r8 = r7.hasRemaining()     // Catch:{ all -> 0x015b }
            if (r8 == 0) goto L_0x013f
            int r8 = r7.remaining()     // Catch:{ all -> 0x015b }
            int r9 = 16384 - r5
            int r8 = java.lang.Math.min(r8, r9)     // Catch:{ all -> 0x015b }
            int r8 = r11.writePlaintextData(r7, r8)     // Catch:{ all -> 0x015b }
            if (r8 <= 0) goto L_0x00f9
            int r5 = r5 + r8
            javax.net.ssl.SSLEngineResult r8 = r11.readPendingBytesFromBIO(r15, r5, r4, r3)     // Catch:{ all -> 0x015b }
            if (r8 == 0) goto L_0x00f6
            javax.net.ssl.SSLEngineResult$Status r4 = r8.getStatus()     // Catch:{ all -> 0x015b }
            javax.net.ssl.SSLEngineResult$Status r9 = javax.net.ssl.SSLEngineResult.Status.OK     // Catch:{ all -> 0x015b }
            if (r4 == r9) goto L_0x00f2
            monitor-exit(r2)     // Catch:{ all -> 0x015b }
            return r8
        L_0x00f2:
            int r4 = r8.bytesProduced()     // Catch:{ all -> 0x015b }
        L_0x00f6:
            if (r5 != r6) goto L_0x00cb
            goto L_0x0143
        L_0x00f9:
            org.conscrypt.NativeSsl r12 = r11.ssl     // Catch:{ all -> 0x015b }
            int r12 = r12.getError(r8)     // Catch:{ all -> 0x015b }
            r13 = 2
            if (r12 == r13) goto L_0x012b
            r13 = 3
            if (r12 == r13) goto L_0x0120
            r13 = 6
            if (r12 != r13) goto L_0x0116
            r11.closeAll()     // Catch:{ all -> 0x015b }
            javax.net.ssl.SSLEngineResult r12 = r11.readPendingBytesFromBIO(r15, r5, r4, r3)     // Catch:{ all -> 0x015b }
            if (r12 == 0) goto L_0x0112
            goto L_0x0114
        L_0x0112:
            javax.net.ssl.SSLEngineResult r12 = CLOSED_NOT_HANDSHAKING     // Catch:{ all -> 0x015b }
        L_0x0114:
            monitor-exit(r2)     // Catch:{ all -> 0x015b }
            return r12
        L_0x0116:
            r11.sendSSLShutdown()     // Catch:{ all -> 0x015b }
            java.lang.String r12 = "SSL_write"
            javax.net.ssl.SSLException r12 = r11.newSslExceptionWithMessage(r12)     // Catch:{ all -> 0x015b }
            throw r12     // Catch:{ all -> 0x015b }
        L_0x0120:
            javax.net.ssl.SSLEngineResult r12 = r11.readPendingBytesFromBIO(r15, r5, r4, r3)     // Catch:{ all -> 0x015b }
            if (r12 == 0) goto L_0x0127
            goto L_0x0129
        L_0x0127:
            javax.net.ssl.SSLEngineResult r12 = NEED_WRAP_CLOSED     // Catch:{ all -> 0x015b }
        L_0x0129:
            monitor-exit(r2)     // Catch:{ all -> 0x015b }
            return r12
        L_0x012b:
            javax.net.ssl.SSLEngineResult r12 = r11.readPendingBytesFromBIO(r15, r5, r4, r3)     // Catch:{ all -> 0x015b }
            if (r12 == 0) goto L_0x0132
            goto L_0x013d
        L_0x0132:
            javax.net.ssl.SSLEngineResult r12 = new javax.net.ssl.SSLEngineResult     // Catch:{ all -> 0x015b }
            javax.net.ssl.SSLEngineResult$Status r13 = r11.getEngineStatus()     // Catch:{ all -> 0x015b }
            javax.net.ssl.SSLEngineResult$HandshakeStatus r14 = javax.net.ssl.SSLEngineResult.HandshakeStatus.NEED_UNWRAP     // Catch:{ all -> 0x015b }
            r12.<init>(r13, r14, r5, r4)     // Catch:{ all -> 0x015b }
        L_0x013d:
            monitor-exit(r2)     // Catch:{ all -> 0x015b }
            return r12
        L_0x013f:
            int r13 = r13 + 1
            goto L_0x00b9
        L_0x0143:
            if (r5 != 0) goto L_0x014d
            javax.net.ssl.SSLEngineResult r12 = r11.readPendingBytesFromBIO(r15, r1, r4, r3)     // Catch:{ all -> 0x015b }
            if (r12 == 0) goto L_0x014d
            monitor-exit(r2)     // Catch:{ all -> 0x015b }
            return r12
        L_0x014d:
            javax.net.ssl.SSLEngineResult r12 = r11.newResult(r5, r4, r3)     // Catch:{ all -> 0x015b }
            monitor-exit(r2)     // Catch:{ all -> 0x015b }
            return r12
        L_0x0153:
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException     // Catch:{ all -> 0x015b }
            java.lang.String r13 = "Client/server mode must be set before calling wrap"
            r12.<init>(r13)     // Catch:{ all -> 0x015b }
            throw r12     // Catch:{ all -> 0x015b }
        L_0x015b:
            r12 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x015b }
            throw r12
        L_0x015e:
            java.nio.ReadOnlyBufferException r12 = new java.nio.ReadOnlyBufferException
            r12.<init>()
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: org.conscrypt.ConscryptEngine.wrap(java.nio.ByteBuffer[], int, int, java.nio.ByteBuffer):javax.net.ssl.SSLEngineResult");
    }

    public int clientPSKKeyRequested(String str, byte[] bArr, byte[] bArr2) {
        return this.ssl.clientPSKKeyRequested(str, bArr, bArr2);
    }

    public int serverPSKKeyRequested(String str, String str2, byte[] bArr) {
        return this.ssl.serverPSKKeyRequested(str, str2, bArr);
    }

    public void onSSLStateChange(int i, int i2) {
        synchronized (this.ssl) {
            if (i == 16) {
                transitionTo(2);
            } else if (i == 32) {
                if (this.state != 2) {
                    if (this.state != 4) {
                        throw new IllegalStateException("Completed handshake while in mode " + this.state);
                    }
                }
                transitionTo(3);
            }
        }
    }

    public void onNewSessionEstablished(long j) {
        try {
            NativeCrypto.SSL_SESSION_up_ref(j);
            sessionContext().cacheSession(NativeSslSession.newInstance(new NativeRef.SSL_SESSION(j), this.activeSession));
        } catch (Exception unused) {
        }
    }

    public void verifyCertificateChain(byte[][] bArr, String str) throws CertificateException {
        if (bArr != null) {
            try {
                if (bArr.length != 0) {
                    X509Certificate[] decodeX509CertificateChain = SSLUtils.decodeX509CertificateChain(bArr);
                    X509TrustManager x509TrustManager = this.sslParameters.getX509TrustManager();
                    if (x509TrustManager != null) {
                        this.activeSession.onPeerCertificatesReceived(getPeerHost(), getPeerPort(), decodeX509CertificateChain);
                        if (getUseClientMode()) {
                            Platform.checkServerTrusted(x509TrustManager, decodeX509CertificateChain, str, this);
                            return;
                        } else {
                            Platform.checkClientTrusted(x509TrustManager, decodeX509CertificateChain, decodeX509CertificateChain[0].getPublicKey().getAlgorithm(), this);
                            return;
                        }
                    } else {
                        throw new CertificateException("No X.509 TrustManager");
                    }
                }
            } catch (CertificateException e) {
                throw e;
            } catch (Exception e2) {
                throw new CertificateException(e2);
            }
        }
        throw new CertificateException("Peer sent no certificate");
    }

    public void clientCertificateRequested(byte[] bArr, int[] iArr, byte[][] bArr2) throws CertificateEncodingException, SSLException {
        this.ssl.chooseClientCertificate(bArr, iArr, bArr2);
    }

    private void sendSSLShutdown() {
        try {
            this.ssl.shutdown();
        } catch (IOException unused) {
        }
    }

    private void closeAndFreeResources() {
        transitionTo(8);
        if (!this.ssl.isClosed()) {
            this.ssl.close();
            this.networkBio.close();
        }
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        try {
            transitionTo(8);
        } finally {
            super.finalize();
        }
    }

    public String chooseServerAlias(X509KeyManager x509KeyManager, String str) {
        if (x509KeyManager instanceof X509ExtendedKeyManager) {
            return ((X509ExtendedKeyManager) x509KeyManager).chooseEngineServerAlias(str, (Principal[]) null, this);
        }
        return x509KeyManager.chooseServerAlias(str, (Principal[]) null, (Socket) null);
    }

    public String chooseClientAlias(X509KeyManager x509KeyManager, X500Principal[] x500PrincipalArr, String[] strArr) {
        if (x509KeyManager instanceof X509ExtendedKeyManager) {
            return ((X509ExtendedKeyManager) x509KeyManager).chooseEngineClientAlias(strArr, x500PrincipalArr, this);
        }
        return x509KeyManager.chooseClientAlias(strArr, x500PrincipalArr, (Socket) null);
    }

    public String chooseServerPSKIdentityHint(PSKKeyManager pSKKeyManager) {
        return pSKKeyManager.chooseServerKeyIdentityHint((SSLEngine) this);
    }

    public String chooseClientPSKIdentity(PSKKeyManager pSKKeyManager, String str) {
        return pSKKeyManager.chooseClientKeyIdentity(str, (SSLEngine) this);
    }

    public SecretKey getPSKKey(PSKKeyManager pSKKeyManager, String str, String str2) {
        return pSKKeyManager.getKey(str, str2, (SSLEngine) this);
    }

    /* access modifiers changed from: package-private */
    public void setUseSessionTickets(boolean z) {
        this.sslParameters.setUseSessionTickets(z);
    }

    /* access modifiers changed from: package-private */
    public String[] getApplicationProtocols() {
        return this.sslParameters.getApplicationProtocols();
    }

    /* access modifiers changed from: package-private */
    public void setApplicationProtocols(String[] strArr) {
        this.sslParameters.setApplicationProtocols(strArr);
    }

    /* access modifiers changed from: package-private */
    public void setApplicationProtocolSelector(ApplicationProtocolSelector applicationProtocolSelector) {
        setApplicationProtocolSelector(applicationProtocolSelector == null ? null : new ApplicationProtocolSelectorAdapter((SSLEngine) this, applicationProtocolSelector));
    }

    /* access modifiers changed from: package-private */
    public byte[] getTlsUnique() {
        return this.ssl.getTlsUnique();
    }

    /* access modifiers changed from: package-private */
    public byte[] exportKeyingMaterial(String str, byte[] bArr, int i) throws SSLException {
        synchronized (this.ssl) {
            if (this.state >= 3) {
                if (this.state != 8) {
                    return this.ssl.exportKeyingMaterial(str, bArr, i);
                }
            }
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    public void setApplicationProtocolSelector(ApplicationProtocolSelectorAdapter applicationProtocolSelectorAdapter) {
        this.sslParameters.setApplicationProtocolSelector(applicationProtocolSelectorAdapter);
    }

    public String getApplicationProtocol() {
        return SSLUtils.toProtocolString(this.ssl.getApplicationProtocol());
    }

    public String getHandshakeApplicationProtocol() {
        String applicationProtocol;
        synchronized (this.ssl) {
            applicationProtocol = this.state == 2 ? getApplicationProtocol() : null;
        }
        return applicationProtocol;
    }

    private ByteBuffer[] singleSrcBuffer(ByteBuffer byteBuffer) {
        ByteBuffer[] byteBufferArr = this.singleSrcBuffer;
        byteBufferArr[0] = byteBuffer;
        return byteBufferArr;
    }

    private void resetSingleSrcBuffer() {
        this.singleSrcBuffer[0] = null;
    }

    private ByteBuffer[] singleDstBuffer(ByteBuffer byteBuffer) {
        ByteBuffer[] byteBufferArr = this.singleDstBuffer;
        byteBufferArr[0] = byteBuffer;
        return byteBufferArr;
    }

    private void resetSingleDstBuffer() {
        this.singleDstBuffer[0] = null;
    }

    private ClientSessionContext clientSessionContext() {
        return this.sslParameters.getClientSessionContext();
    }

    private AbstractSessionContext sessionContext() {
        return this.sslParameters.getSessionContext();
    }

    private void transitionTo(int i) {
        int i2;
        if (i == 2) {
            this.handshakeFinished = false;
            this.activeSession = new ActiveSession(this.ssl, this.sslParameters.getSessionContext());
        } else if (i == 8 && !this.ssl.isClosed() && (i2 = this.state) >= 2 && i2 < 8) {
            this.closedSession = new SessionSnapshot(this.activeSession);
        }
        this.state = i;
    }
}
