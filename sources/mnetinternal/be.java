package mnetinternal;

import android.os.Handler;
import android.os.Looper;
import android.text.Spanned;
import android.util.DisplayMetrics;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.TextView;
import com.facebook.internal.ServerProtocol;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.LinkedBlockingQueue;
import mnetinternal.by;
import org.apache.commons.io.IOUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;

final class be {
    private Handler a;

    be() {
    }

    /* access modifiers changed from: package-private */
    public String a(bh bhVar) {
        if (bhVar == null) {
            return null;
        }
        Handler handler = new Handler(Looper.getMainLooper());
        this.a = handler;
        return new a(handler).b(bhVar);
    }

    public void a() {
        Handler handler = this.a;
        if (handler != null) {
            handler.removeCallbacksAndMessages((Object) null);
        }
    }

    private static class a {
        private HashSet<Integer> a;
        /* access modifiers changed from: private */
        public StringBuilder b;
        private Handler c;
        private LinkedBlockingQueue<View> d;
        /* access modifiers changed from: private */
        public int e;
        /* access modifiers changed from: private */
        public int f;
        private float g;

        private a(Handler handler) {
            this.a = new HashSet<>();
            this.b = new StringBuilder();
            this.d = new LinkedBlockingQueue<>();
            this.c = handler;
        }

        private void a(bh bhVar) {
            DisplayMetrics displayMetrics = bhVar.a().getResources().getDisplayMetrics();
            this.e = displayMetrics.widthPixels;
            this.f = displayMetrics.heightPixels;
            this.g = displayMetrics.density;
        }

        /* access modifiers changed from: private */
        public String b(bh bhVar) {
            if (bhVar == null) {
                return null;
            }
            a(bhVar);
            return c(bhVar);
        }

        private String c(bh bhVar) {
            this.b.append("<html>");
            this.b.append("<head>");
            this.b.append("<title>");
            String a2 = bq.a(bhVar.a());
            bi.a("##ContentGenerator##", "Title is : " + a2);
            StringBuilder sb = this.b;
            if (a2 == null) {
                a2 = "null";
            }
            sb.append(a2);
            this.b.append("</title>");
            this.b.append("</head>");
            this.b.append("<body>");
            a((ViewGroup) bhVar.a().getWindow().getDecorView());
            Iterator<View> it = this.d.iterator();
            while (it.hasNext()) {
                b(it.next());
            }
            this.b.append("</body>");
            this.b.append("</html>");
            return a();
        }

        private void a(ViewGroup viewGroup) {
            if (viewGroup != null) {
                for (int i = 0; i < viewGroup.getChildCount(); i++) {
                    View childAt = viewGroup.getChildAt(i);
                    if (!ca.d(childAt)) {
                        if (a(childAt)) {
                            this.d.add(childAt);
                        }
                        if (!(childAt instanceof WebView) && (childAt instanceof ViewGroup)) {
                            List<View> a2 = ca.a((ViewGroup) childAt);
                            if (a2 != null) {
                                for (int i2 = 0; i2 < a2.size(); i2++) {
                                    View view = a2.get(i2);
                                    if (!ca.d(view)) {
                                        if (a(view)) {
                                            this.d.add(view);
                                        }
                                        if (!(view instanceof AdapterView) && !bt.a(view) && !bv.a(view) && !(view instanceof WebView)) {
                                            a((ViewGroup) view);
                                        }
                                    }
                                }
                            } else {
                                return;
                            }
                        }
                    }
                }
            }
        }

        private boolean a(View view) {
            if (!(view instanceof TextView) && !bt.a(view) && !(view instanceof AdapterView) && !bv.a(view)) {
                return view instanceof WebView;
            }
            return true;
        }

        private void b(View view) {
            if (view instanceof TextView) {
                this.b.append(b((TextView) view));
            } else if (view instanceof WebView) {
                bz bzVar = new bz();
                a(bzVar, (WebView) view);
                bzVar.b();
            } else if (bt.a(view) && bt.b(view)) {
                d(view);
            } else if (view instanceof AdapterView) {
                c(view);
            }
        }

        /* access modifiers changed from: private */
        public int a(int i, int i2) {
            if (i2 == 0) {
                return 0;
            }
            return Double.valueOf(((((double) i) * 1.0d) / ((double) i2)) * 100.0d).intValue();
        }

        private void a(final bz bzVar, final WebView webView) {
            this.c.post(new ac() {
                /* JADX WARNING: Removed duplicated region for block: B:19:0x0054 A[Catch:{ Exception -> 0x00af }] */
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public void a() {
                    /*
                        r8 = this;
                        r0 = 0
                        android.webkit.WebView r1 = r4     // Catch:{ Exception -> 0x00af }
                        java.lang.String r1 = r1.getUrl()     // Catch:{ Exception -> 0x00af }
                        java.lang.String r2 = "http"
                        java.lang.String r3 = "www"
                        if (r1 == 0) goto L_0x002c
                        android.webkit.WebView r1 = r4     // Catch:{ Exception -> 0x00af }
                        java.lang.String r1 = r1.getUrl()     // Catch:{ Exception -> 0x00af }
                        boolean r1 = r1.contains(r3)     // Catch:{ Exception -> 0x00af }
                        if (r1 != 0) goto L_0x0025
                        android.webkit.WebView r1 = r4     // Catch:{ Exception -> 0x00af }
                        java.lang.String r1 = r1.getUrl()     // Catch:{ Exception -> 0x00af }
                        boolean r1 = r1.contains(r2)     // Catch:{ Exception -> 0x00af }
                        if (r1 == 0) goto L_0x002c
                    L_0x0025:
                        android.webkit.WebView r0 = r4     // Catch:{ Exception -> 0x00af }
                        java.lang.String r0 = r0.getUrl()     // Catch:{ Exception -> 0x00af }
                        goto L_0x0052
                    L_0x002c:
                        android.webkit.WebView r1 = r4     // Catch:{ Exception -> 0x00af }
                        java.lang.String r1 = r1.getOriginalUrl()     // Catch:{ Exception -> 0x00af }
                        if (r1 == 0) goto L_0x0052
                        android.webkit.WebView r1 = r4     // Catch:{ Exception -> 0x00af }
                        java.lang.String r1 = r1.getOriginalUrl()     // Catch:{ Exception -> 0x00af }
                        boolean r1 = r1.contains(r3)     // Catch:{ Exception -> 0x00af }
                        if (r1 != 0) goto L_0x004c
                        android.webkit.WebView r1 = r4     // Catch:{ Exception -> 0x00af }
                        java.lang.String r1 = r1.getOriginalUrl()     // Catch:{ Exception -> 0x00af }
                        boolean r1 = r1.contains(r2)     // Catch:{ Exception -> 0x00af }
                        if (r1 == 0) goto L_0x0052
                    L_0x004c:
                        android.webkit.WebView r0 = r4     // Catch:{ Exception -> 0x00af }
                        java.lang.String r0 = r0.getOriginalUrl()     // Catch:{ Exception -> 0x00af }
                    L_0x0052:
                        if (r0 == 0) goto L_0x00a9
                        mnetinternal.be$a r1 = mnetinternal.be.a.this     // Catch:{ Exception -> 0x00af }
                        java.lang.StringBuilder r1 = r1.b     // Catch:{ Exception -> 0x00af }
                        mnetinternal.be$a r2 = mnetinternal.be.a.this     // Catch:{ Exception -> 0x00af }
                        android.webkit.WebView r3 = r4     // Catch:{ Exception -> 0x00af }
                        int r3 = r3.getTop()     // Catch:{ Exception -> 0x00af }
                        mnetinternal.be$a r4 = mnetinternal.be.a.this     // Catch:{ Exception -> 0x00af }
                        int r4 = r4.f     // Catch:{ Exception -> 0x00af }
                        int r2 = r2.a((int) r3, (int) r4)     // Catch:{ Exception -> 0x00af }
                        mnetinternal.be$a r3 = mnetinternal.be.a.this     // Catch:{ Exception -> 0x00af }
                        android.webkit.WebView r4 = r4     // Catch:{ Exception -> 0x00af }
                        int r4 = r4.getLeft()     // Catch:{ Exception -> 0x00af }
                        mnetinternal.be$a r5 = mnetinternal.be.a.this     // Catch:{ Exception -> 0x00af }
                        int r5 = r5.e     // Catch:{ Exception -> 0x00af }
                        int r3 = r3.a((int) r4, (int) r5)     // Catch:{ Exception -> 0x00af }
                        mnetinternal.be$a r4 = mnetinternal.be.a.this     // Catch:{ Exception -> 0x00af }
                        android.webkit.WebView r5 = r4     // Catch:{ Exception -> 0x00af }
                        int r5 = r5.getBottom()     // Catch:{ Exception -> 0x00af }
                        mnetinternal.be$a r6 = mnetinternal.be.a.this     // Catch:{ Exception -> 0x00af }
                        int r6 = r6.f     // Catch:{ Exception -> 0x00af }
                        int r4 = r4.a((int) r5, (int) r6)     // Catch:{ Exception -> 0x00af }
                        mnetinternal.be$a r5 = mnetinternal.be.a.this     // Catch:{ Exception -> 0x00af }
                        android.webkit.WebView r6 = r4     // Catch:{ Exception -> 0x00af }
                        int r6 = r6.getRight()     // Catch:{ Exception -> 0x00af }
                        mnetinternal.be$a r7 = mnetinternal.be.a.this     // Catch:{ Exception -> 0x00af }
                        int r7 = r7.e     // Catch:{ Exception -> 0x00af }
                        int r5 = r5.a((int) r6, (int) r7)     // Catch:{ Exception -> 0x00af }
                        java.lang.String r0 = mnetinternal.by.b.a(r0, r2, r3, r4, r5)     // Catch:{ Exception -> 0x00af }
                        r1.append(r0)     // Catch:{ Exception -> 0x00af }
                    L_0x00a9:
                        mnetinternal.bz r0 = r3     // Catch:{ Exception -> 0x00af }
                        r0.a()     // Catch:{ Exception -> 0x00af }
                        goto L_0x00ba
                    L_0x00af:
                        r0 = move-exception
                        java.lang.String r1 = "##ContentGenerator##"
                        mnetinternal.bi.a((java.lang.String) r1, (java.lang.Throwable) r0)
                        mnetinternal.bz r0 = r3
                        r0.a()
                    L_0x00ba:
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: mnetinternal.be.a.AnonymousClass1.a():void");
                }
            });
        }

        private void c(View view) {
            try {
                bi.a("##ContentGenerator##", "Processing list view content");
                this.b.append("<ul>");
                ViewGroup viewGroup = (ViewGroup) view;
                for (int i = 0; i < viewGroup.getChildCount(); i++) {
                    View childAt = viewGroup.getChildAt(i);
                    if (childAt != null) {
                        if (!ca.d(childAt)) {
                            this.b.append(a(childAt.getId(), childAt.isShown()));
                            if (childAt instanceof ViewGroup) {
                                b((ViewGroup) childAt);
                            } else if (childAt instanceof TextView) {
                                this.b.append(b((TextView) childAt));
                            }
                            this.b.append("</li>");
                        }
                    }
                }
            } catch (Exception e2) {
                bi.a("##ContentGenerator##", (Throwable) e2);
            } catch (Throwable th) {
                this.b.append("</ul>");
                throw th;
            }
            this.b.append("</ul>");
        }

        private void b(ViewGroup viewGroup) {
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                View childAt = viewGroup.getChildAt(i);
                bi.a("##ContentGenerator##", childAt.getClass().getCanonicalName());
                if (!ca.d(childAt)) {
                    if (childAt instanceof TextView) {
                        a((TextView) childAt);
                    } else if (childAt instanceof WebView) {
                        bz bzVar = new bz();
                        a(bzVar, (WebView) childAt);
                        bzVar.b();
                    } else if (childAt instanceof ViewGroup) {
                        b((ViewGroup) childAt);
                    }
                }
            }
        }

        private void a(TextView textView) {
            this.b.append(b(textView));
        }

        private String a(int i, boolean z) {
            String str = ServerProtocol.DIALOG_RETURN_SCOPES_TRUE;
            if (i == -1) {
                Object[] objArr = new Object[1];
                if (!z) {
                    str = "false";
                }
                objArr[0] = str;
                return String.format("<li visibility='%s' >", objArr);
            }
            Locale locale = Locale.US;
            Object[] objArr2 = new Object[2];
            objArr2[0] = Integer.valueOf(i);
            if (!z) {
                str = "false";
            }
            objArr2[1] = str;
            return String.format(locale, "<li id='%d' visibility='%s' >", objArr2);
        }

        private void d(View view) {
            bi.a("##ContentGenerator##", "Processing recycler view content");
            try {
                ViewGroup viewGroup = (ViewGroup) view;
                this.b.append("<ul>");
                for (int i = 0; i < viewGroup.getChildCount(); i++) {
                    try {
                        View childAt = viewGroup.getChildAt(i);
                        if (childAt != null) {
                            if (!ca.d(childAt)) {
                                this.b.append(a(childAt.getId(), childAt.isShown()));
                                if (childAt instanceof ViewGroup) {
                                    b((ViewGroup) childAt);
                                } else if (childAt instanceof TextView) {
                                    this.b.append(b((TextView) childAt));
                                }
                                this.b.append("</li>");
                            }
                        }
                    } catch (Exception e2) {
                        bi.a("##ContentGenerator##", (Throwable) e2);
                    }
                }
            } catch (Exception e3) {
                bi.a("##ContentGenerator##", (Throwable) e3);
            } catch (Throwable th) {
                this.b.append("</ul>");
                throw th;
            }
            this.b.append("</ul>");
        }

        private int a(float f2) {
            float f3 = this.g;
            if (0.0f == f3) {
                return Math.round(f2);
            }
            return Math.round(f2 / f3);
        }

        private String b(TextView textView) {
            this.a.add(Integer.valueOf(a(textView.getTextSize())));
            if (textView.getText() instanceof Spanned) {
                return by.d.a(textView, 10).replace(IOUtils.LINE_SEPARATOR_UNIX, "<br>");
            }
            if (textView.getTypeface() != null && textView.getTypeface().getStyle() == 1) {
                return by.a.a(textView, a(textView.getTextSize()));
            }
            if (textView.getTypeface() == null || textView.getTypeface().getStyle() != 2) {
                return by.d.a(textView, a(textView.getTextSize()));
            }
            return by.c.a(textView, a(textView.getTextSize()));
        }

        private String a() {
            try {
                SparseArray sparseArray = new SparseArray();
                ArrayList arrayList = new ArrayList(this.a);
                Collections.sort(arrayList);
                int size = this.a.size();
                for (int i = size - 1; i >= 0; i--) {
                    if (i == 0) {
                        sparseArray.put(((Integer) arrayList.get(i)).intValue(), "p");
                    } else if (sparseArray.indexOfKey(((Integer) arrayList.get(i)).intValue()) < 0) {
                        sparseArray.put(((Integer) arrayList.get(i)).intValue(), "h" + (size - i));
                    }
                }
                Document parse = Jsoup.parse(this.b.toString());
                for (int i2 = 0; i2 < size; i2++) {
                    Iterator it = parse.getElementsByAttributeValue("font-size", String.valueOf(arrayList.get(i2))).iterator();
                    while (it.hasNext()) {
                        ((Element) it.next()).tagName((String) sparseArray.get(((Integer) arrayList.get(i2)).intValue()));
                    }
                }
                return "<!DOCTYPE HTML PUBLIC -//W3C//DTD HTML 4.01//EN http://www.w3.org/TR/html4/strict.dtd>" + Parser.unescapeEntities(parse.toString(), true);
            } catch (Exception e2) {
                bi.a("##ContentGenerator##", (Throwable) e2);
                return this.b.toString();
            }
        }
    }
}
