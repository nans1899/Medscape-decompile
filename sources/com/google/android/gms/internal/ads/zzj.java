package com.google.android.gms.internal.ads;

import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Principal;
import java.security.PublicKey;
import java.security.SignatureException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateNotYetValidException;
import java.security.cert.X509Certificate;
import java.util.Date;
import java.util.Set;

/* compiled from: com.google.android.gms:play-services-gass@@19.4.0 */
class zzj extends X509Certificate {
    private final X509Certificate zzk;

    public zzj(X509Certificate x509Certificate) {
        this.zzk = x509Certificate;
    }

    public Set<String> getCriticalExtensionOIDs() {
        return this.zzk.getCriticalExtensionOIDs();
    }

    public byte[] getExtensionValue(String str) {
        return this.zzk.getExtensionValue(str);
    }

    public Set<String> getNonCriticalExtensionOIDs() {
        return this.zzk.getNonCriticalExtensionOIDs();
    }

    public boolean hasUnsupportedCriticalExtension() {
        return this.zzk.hasUnsupportedCriticalExtension();
    }

    public void checkValidity() throws CertificateExpiredException, CertificateNotYetValidException {
        this.zzk.checkValidity();
    }

    public void checkValidity(Date date) throws CertificateExpiredException, CertificateNotYetValidException {
        this.zzk.checkValidity(date);
    }

    public int getVersion() {
        return this.zzk.getVersion();
    }

    public BigInteger getSerialNumber() {
        return this.zzk.getSerialNumber();
    }

    public Principal getIssuerDN() {
        return this.zzk.getIssuerDN();
    }

    public Principal getSubjectDN() {
        return this.zzk.getSubjectDN();
    }

    public Date getNotBefore() {
        return this.zzk.getNotBefore();
    }

    public Date getNotAfter() {
        return this.zzk.getNotAfter();
    }

    public byte[] getTBSCertificate() throws CertificateEncodingException {
        return this.zzk.getTBSCertificate();
    }

    public byte[] getSignature() {
        return this.zzk.getSignature();
    }

    public String getSigAlgName() {
        return this.zzk.getSigAlgName();
    }

    public String getSigAlgOID() {
        return this.zzk.getSigAlgOID();
    }

    public byte[] getSigAlgParams() {
        return this.zzk.getSigAlgParams();
    }

    public boolean[] getIssuerUniqueID() {
        return this.zzk.getIssuerUniqueID();
    }

    public boolean[] getSubjectUniqueID() {
        return this.zzk.getSubjectUniqueID();
    }

    public boolean[] getKeyUsage() {
        return this.zzk.getKeyUsage();
    }

    public int getBasicConstraints() {
        return this.zzk.getBasicConstraints();
    }

    public byte[] getEncoded() throws CertificateEncodingException {
        return this.zzk.getEncoded();
    }

    public void verify(PublicKey publicKey) throws CertificateException, NoSuchAlgorithmException, InvalidKeyException, NoSuchProviderException, SignatureException {
        this.zzk.verify(publicKey);
    }

    public void verify(PublicKey publicKey, String str) throws CertificateException, NoSuchAlgorithmException, InvalidKeyException, NoSuchProviderException, SignatureException {
        this.zzk.verify(publicKey, str);
    }

    public String toString() {
        return this.zzk.toString();
    }

    public PublicKey getPublicKey() {
        return this.zzk.getPublicKey();
    }
}
