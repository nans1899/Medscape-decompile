package com.medscape.android.task;

import android.os.AsyncTask;
import com.medscape.android.updater.model.UpdateProcess;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class CheckVerXMLTask extends AsyncTask<String, Double, UpdateProcess> {
    private int mError = -1;
    private CheckVerXMLListener mListener;

    public interface CheckVerXMLListener {
        void onContentsDownloaded(UpdateProcess updateProcess);

        void setonError(int i);
    }

    public CheckVerXMLTask() {
    }

    public CheckVerXMLTask(CheckVerXMLListener checkVerXMLListener) {
        this.mListener = checkVerXMLListener;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0062, code lost:
        r6.mError = 8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0065, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0066, code lost:
        r6.mError = 8;
        r7.printStackTrace();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x006c, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x006d, code lost:
        r7.printStackTrace();
        r6.mError = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0073, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0074, code lost:
        r6.mError = 1;
        r7.printStackTrace();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x007a, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x007b, code lost:
        r7.printStackTrace();
        r6.mError = 1;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0065 A[ExcHandler: FileNotFoundException (r7v4 'e' java.io.FileNotFoundException A[CUSTOM_DECLARE]), Splitter:B:1:0x0004] */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x006c A[ExcHandler: UnknownHostException (r7v3 'e' java.net.UnknownHostException A[CUSTOM_DECLARE]), Splitter:B:1:0x0004] */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0073 A[ExcHandler: SocketTimeoutException (r7v2 'e' java.net.SocketTimeoutException A[CUSTOM_DECLARE]), Splitter:B:1:0x0004] */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x007a A[ExcHandler: SocketException (r7v1 'e' java.net.SocketException A[CUSTOM_DECLARE]), Splitter:B:1:0x0004] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.medscape.android.updater.model.UpdateProcess doInBackground(java.lang.String... r7) {
        /*
            r6 = this;
            r0 = 8
            r1 = 1
            r2 = 0
            java.net.URL r3 = new java.net.URL     // Catch:{ SocketException -> 0x007a, SocketTimeoutException -> 0x0073, UnknownHostException -> 0x006c, FileNotFoundException -> 0x0065, Exception -> 0x0062 }
            r4 = 0
            r7 = r7[r4]     // Catch:{ SocketException -> 0x007a, SocketTimeoutException -> 0x0073, UnknownHostException -> 0x006c, FileNotFoundException -> 0x0065, Exception -> 0x0062 }
            r3.<init>(r7)     // Catch:{ SocketException -> 0x007a, SocketTimeoutException -> 0x0073, UnknownHostException -> 0x006c, FileNotFoundException -> 0x0065, Exception -> 0x0062 }
            java.net.URLConnection r7 = r3.openConnection()     // Catch:{ SocketException -> 0x007a, SocketTimeoutException -> 0x0073, UnknownHostException -> 0x006c, FileNotFoundException -> 0x0065, Exception -> 0x0062 }
            java.net.HttpURLConnection r7 = (java.net.HttpURLConnection) r7     // Catch:{ SocketException -> 0x007a, SocketTimeoutException -> 0x0073, UnknownHostException -> 0x006c, FileNotFoundException -> 0x0065, Exception -> 0x0062 }
            int r3 = com.medscape.android.util.Util.TIMEOUT     // Catch:{ SocketException -> 0x007a, SocketTimeoutException -> 0x0073, UnknownHostException -> 0x006c, FileNotFoundException -> 0x0065, Exception -> 0x0062 }
            r7.setReadTimeout(r3)     // Catch:{ SocketException -> 0x007a, SocketTimeoutException -> 0x0073, UnknownHostException -> 0x006c, FileNotFoundException -> 0x0065, Exception -> 0x0062 }
            javax.xml.parsers.DocumentBuilderFactory r3 = javax.xml.parsers.DocumentBuilderFactory.newInstance()     // Catch:{ SocketException -> 0x007a, SocketTimeoutException -> 0x0073, UnknownHostException -> 0x006c, FileNotFoundException -> 0x0065, Exception -> 0x0062 }
            javax.xml.parsers.DocumentBuilder r3 = r3.newDocumentBuilder()     // Catch:{ Exception -> 0x005b, SocketException -> 0x007a, SocketTimeoutException -> 0x0073, UnknownHostException -> 0x006c, FileNotFoundException -> 0x0065 }
            com.medscape.android.util.AutomationHelper r4 = com.medscape.android.util.AutomationHelper.getInstance()     // Catch:{ Exception -> 0x005b, SocketException -> 0x007a, SocketTimeoutException -> 0x0073, UnknownHostException -> 0x006c, FileNotFoundException -> 0x0065 }
            boolean r4 = r4.isInUITestMode     // Catch:{ Exception -> 0x005b, SocketException -> 0x007a, SocketTimeoutException -> 0x0073, UnknownHostException -> 0x006c, FileNotFoundException -> 0x0065 }
            if (r4 == 0) goto L_0x004e
            com.medscape.android.util.AutomationHelper r4 = com.medscape.android.util.AutomationHelper.getInstance()     // Catch:{ Exception -> 0x005b, SocketException -> 0x007a, SocketTimeoutException -> 0x0073, UnknownHostException -> 0x006c, FileNotFoundException -> 0x0065 }
            java.lang.String r4 = r4.stubbedVerXmlResponse     // Catch:{ Exception -> 0x005b, SocketException -> 0x007a, SocketTimeoutException -> 0x0073, UnknownHostException -> 0x006c, FileNotFoundException -> 0x0065 }
            boolean r5 = com.medscape.android.util.StringUtil.isNotEmpty(r4)     // Catch:{ Exception -> 0x005b, SocketException -> 0x007a, SocketTimeoutException -> 0x0073, UnknownHostException -> 0x006c, FileNotFoundException -> 0x0065 }
            if (r5 == 0) goto L_0x0045
            org.xml.sax.InputSource r7 = new org.xml.sax.InputSource     // Catch:{ Exception -> 0x005b, SocketException -> 0x007a, SocketTimeoutException -> 0x0073, UnknownHostException -> 0x006c, FileNotFoundException -> 0x0065 }
            r7.<init>()     // Catch:{ Exception -> 0x005b, SocketException -> 0x007a, SocketTimeoutException -> 0x0073, UnknownHostException -> 0x006c, FileNotFoundException -> 0x0065 }
            java.io.StringReader r5 = new java.io.StringReader     // Catch:{ Exception -> 0x005b, SocketException -> 0x007a, SocketTimeoutException -> 0x0073, UnknownHostException -> 0x006c, FileNotFoundException -> 0x0065 }
            r5.<init>(r4)     // Catch:{ Exception -> 0x005b, SocketException -> 0x007a, SocketTimeoutException -> 0x0073, UnknownHostException -> 0x006c, FileNotFoundException -> 0x0065 }
            r7.setCharacterStream(r5)     // Catch:{ Exception -> 0x005b, SocketException -> 0x007a, SocketTimeoutException -> 0x0073, UnknownHostException -> 0x006c, FileNotFoundException -> 0x0065 }
            org.w3c.dom.Document r7 = r3.parse(r7)     // Catch:{ Exception -> 0x005b, SocketException -> 0x007a, SocketTimeoutException -> 0x0073, UnknownHostException -> 0x006c, FileNotFoundException -> 0x0065 }
            goto L_0x0056
        L_0x0045:
            java.io.InputStream r7 = r7.getInputStream()     // Catch:{ Exception -> 0x005b, SocketException -> 0x007a, SocketTimeoutException -> 0x0073, UnknownHostException -> 0x006c, FileNotFoundException -> 0x0065 }
            org.w3c.dom.Document r7 = r3.parse(r7)     // Catch:{ Exception -> 0x005b, SocketException -> 0x007a, SocketTimeoutException -> 0x0073, UnknownHostException -> 0x006c, FileNotFoundException -> 0x0065 }
            goto L_0x0056
        L_0x004e:
            java.io.InputStream r7 = r7.getInputStream()     // Catch:{ Exception -> 0x005b, SocketException -> 0x007a, SocketTimeoutException -> 0x0073, UnknownHostException -> 0x006c, FileNotFoundException -> 0x0065 }
            org.w3c.dom.Document r7 = r3.parse(r7)     // Catch:{ Exception -> 0x005b, SocketException -> 0x007a, SocketTimeoutException -> 0x0073, UnknownHostException -> 0x006c, FileNotFoundException -> 0x0065 }
        L_0x0056:
            com.medscape.android.updater.model.UpdateProcess r2 = r6.createUpdateProcess(r7)     // Catch:{ Exception -> 0x005b, SocketException -> 0x007a, SocketTimeoutException -> 0x0073, UnknownHostException -> 0x006c, FileNotFoundException -> 0x0065 }
            goto L_0x0080
        L_0x005b:
            r7 = move-exception
            r6.mError = r1     // Catch:{ SocketException -> 0x007a, SocketTimeoutException -> 0x0073, UnknownHostException -> 0x006c, FileNotFoundException -> 0x0065, Exception -> 0x0062 }
            r7.printStackTrace()     // Catch:{ SocketException -> 0x007a, SocketTimeoutException -> 0x0073, UnknownHostException -> 0x006c, FileNotFoundException -> 0x0065, Exception -> 0x0062 }
            goto L_0x0080
        L_0x0062:
            r6.mError = r0
            goto L_0x0080
        L_0x0065:
            r7 = move-exception
            r6.mError = r0
            r7.printStackTrace()
            goto L_0x0080
        L_0x006c:
            r7 = move-exception
            r7.printStackTrace()
            r6.mError = r1
            goto L_0x0080
        L_0x0073:
            r7 = move-exception
            r6.mError = r1
            r7.printStackTrace()
            goto L_0x0080
        L_0x007a:
            r7 = move-exception
            r7.printStackTrace()
            r6.mError = r1
        L_0x0080:
            if (r2 != 0) goto L_0x0084
            r6.mError = r1
        L_0x0084:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.medscape.android.task.CheckVerXMLTask.doInBackground(java.lang.String[]):com.medscape.android.updater.model.UpdateProcess");
    }

    private UpdateProcess createUpdateProcess(Document document) throws Exception {
        XPath newXPath = XPathFactory.newInstance().newXPath();
        UpdateProcess updateProcess = new UpdateProcess();
        updateProcess.setAdPlaceHolder(UpdateProcess.AdPlaceHolderData.createFromNodeList((NodeList) newXPath.compile("/medscapeupdates/adplaceholder/*").evaluate(document, XPathConstants.NODESET), true));
        updateProcess.setSingleDrugData(UpdateProcess.SilentUpdateData.createFromNodeList((NodeList) newXPath.compile("/medscapeupdates/silentupdate/drug/*").evaluate(document, XPathConstants.NODESET), true));
        updateProcess.setSingleAdSegvarData(UpdateProcess.AdSegvarsData.createFromNodeList((NodeList) newXPath.compile("/medscapeupdates/adsegvars/*").evaluate(document, XPathConstants.NODESET), true));
        updateProcess.setData(UpdateProcess.Data.createFromNodeList((NodeList) newXPath.compile("/medscapeupdates/data/*").evaluate(document, XPathConstants.NODESET), true));
        return updateProcess;
    }

    /* access modifiers changed from: protected */
    public void onPostExecute(UpdateProcess updateProcess) {
        CheckVerXMLListener checkVerXMLListener = this.mListener;
        if (checkVerXMLListener != null) {
            int i = this.mError;
            if (i != -1) {
                checkVerXMLListener.setonError(i);
            } else {
                checkVerXMLListener.onContentsDownloaded(updateProcess);
            }
        }
    }
}
