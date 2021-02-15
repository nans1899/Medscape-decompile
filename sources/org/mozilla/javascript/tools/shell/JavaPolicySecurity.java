package org.mozilla.javascript.tools.shell;

import com.dd.plist.ASCIIPropertyListParser;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.AccessControlContext;
import java.security.AccessControlException;
import java.security.AccessController;
import java.security.CodeSource;
import java.security.Permission;
import java.security.PermissionCollection;
import java.security.Policy;
import java.security.PrivilegedAction;
import java.security.ProtectionDomain;
import java.security.cert.Certificate;
import java.util.Enumeration;
import org.mozilla.javascript.Callable;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.GeneratedClassLoader;
import org.mozilla.javascript.Scriptable;

public class JavaPolicySecurity extends SecurityProxy {
    public Class<?> getStaticSecurityDomainClassInternal() {
        return ProtectionDomain.class;
    }

    private static class Loader extends ClassLoader implements GeneratedClassLoader {
        private ProtectionDomain domain;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        Loader(ClassLoader classLoader, ProtectionDomain protectionDomain) {
            super(classLoader == null ? getSystemClassLoader() : classLoader);
            this.domain = protectionDomain;
        }

        public Class<?> defineClass(String str, byte[] bArr) {
            return super.defineClass(str, bArr, 0, bArr.length, this.domain);
        }

        public void linkClass(Class<?> cls) {
            resolveClass(cls);
        }
    }

    private static class ContextPermissions extends PermissionCollection {
        static final long serialVersionUID = -1721494496320750721L;
        AccessControlContext _context = AccessController.getContext();
        PermissionCollection _statisPermissions;

        ContextPermissions(ProtectionDomain protectionDomain) {
            if (protectionDomain != null) {
                this._statisPermissions = protectionDomain.getPermissions();
            }
            setReadOnly();
        }

        public void add(Permission permission) {
            throw new RuntimeException("NOT IMPLEMENTED");
        }

        public boolean implies(Permission permission) {
            PermissionCollection permissionCollection = this._statisPermissions;
            if (permissionCollection != null && !permissionCollection.implies(permission)) {
                return false;
            }
            try {
                this._context.checkPermission(permission);
                return true;
            } catch (AccessControlException unused) {
                return false;
            }
        }

        public Enumeration<Permission> elements() {
            return new Enumeration<Permission>() {
                public boolean hasMoreElements() {
                    return false;
                }

                public Permission nextElement() {
                    return null;
                }
            };
        }

        public String toString() {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(getClass().getName());
            stringBuffer.append('@');
            stringBuffer.append(Integer.toHexString(System.identityHashCode(this)));
            stringBuffer.append(" (context=");
            stringBuffer.append(this._context);
            stringBuffer.append(", static_permitions=");
            stringBuffer.append(this._statisPermissions);
            stringBuffer.append(ASCIIPropertyListParser.ARRAY_END_TOKEN);
            return stringBuffer.toString();
        }
    }

    public JavaPolicySecurity() {
        new CodeSource((URL) null, (Certificate[]) null);
    }

    /* access modifiers changed from: protected */
    public void callProcessFileSecure(final Context context, final Scriptable scriptable, final String str) {
        AccessController.doPrivileged(new PrivilegedAction<Object>() {
            public Object run() {
                URL access$000 = JavaPolicySecurity.this.getUrlObj(str);
                try {
                    Main.processFileSecure(context, scriptable, access$000.toExternalForm(), JavaPolicySecurity.this.getUrlDomain(access$000));
                    return null;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public URL getUrlObj(String str) {
        try {
            return new URL(str);
        } catch (MalformedURLException unused) {
            String replace = System.getProperty("user.dir").replace('\\', '/');
            if (!replace.endsWith("/")) {
                replace = replace + '/';
            }
            try {
                return new URL(new URL("file:" + replace), str);
            } catch (MalformedURLException e) {
                throw new RuntimeException("Can not construct file URL for '" + str + "':" + e.getMessage());
            }
        }
    }

    /* access modifiers changed from: private */
    public ProtectionDomain getUrlDomain(URL url) {
        CodeSource codeSource = new CodeSource(url, (Certificate[]) null);
        return new ProtectionDomain(codeSource, Policy.getPolicy().getPermissions(codeSource));
    }

    public GeneratedClassLoader createClassLoader(final ClassLoader classLoader, Object obj) {
        final ProtectionDomain protectionDomain = (ProtectionDomain) obj;
        return (GeneratedClassLoader) AccessController.doPrivileged(new PrivilegedAction<Loader>() {
            public Loader run() {
                return new Loader(classLoader, protectionDomain);
            }
        });
    }

    public Object getDynamicSecurityDomain(Object obj) {
        return getDynamicDomain((ProtectionDomain) obj);
    }

    private ProtectionDomain getDynamicDomain(ProtectionDomain protectionDomain) {
        return new ProtectionDomain((CodeSource) null, new ContextPermissions(protectionDomain));
    }

    public Object callWithDomain(Object obj, Context context, Callable callable, Scriptable scriptable, Scriptable scriptable2, Object[] objArr) {
        final Callable callable2 = callable;
        final Context context2 = context;
        final Scriptable scriptable3 = scriptable;
        final Scriptable scriptable4 = scriptable2;
        final Object[] objArr2 = objArr;
        return AccessController.doPrivileged(new PrivilegedAction<Object>() {
            public Object run() {
                return callable2.call(context2, scriptable3, scriptable4, objArr2);
            }
        }, new AccessControlContext(new ProtectionDomain[]{getDynamicDomain((ProtectionDomain) obj)}));
    }
}
