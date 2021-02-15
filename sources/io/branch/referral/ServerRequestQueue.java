package io.branch.referral;

import android.content.Context;
import android.content.SharedPreferences;
import io.branch.referral.Branch;
import io.branch.referral.Defines;
import io.branch.referral.ServerRequest;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

class ServerRequestQueue {
    private static final int MAX_ITEMS = 25;
    private static final String PREF_KEY = "BNCServerRequestQueue";
    private static ServerRequestQueue SharedInstance;
    /* access modifiers changed from: private */
    public static final Object reqQueueLockObject = new Object();
    /* access modifiers changed from: private */
    public SharedPreferences.Editor editor;
    /* access modifiers changed from: private */
    public final List<ServerRequest> queue;
    private SharedPreferences sharedPref;

    public static ServerRequestQueue getInstance(Context context) {
        if (SharedInstance == null) {
            synchronized (ServerRequestQueue.class) {
                if (SharedInstance == null) {
                    SharedInstance = new ServerRequestQueue(context);
                }
            }
        }
        return SharedInstance;
    }

    private ServerRequestQueue(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("BNC_Server_Request_Queue", 0);
        this.sharedPref = sharedPreferences;
        this.editor = sharedPreferences.edit();
        this.queue = retrieve(context);
    }

    private void persist() {
        new Thread(new Runnable() {
            /* JADX WARNING: Can't wrap try/catch for region: R(5:13|23|24|25|26) */
            /* JADX WARNING: Can't wrap try/catch for region: R(6:2|3|(4:6|(2:10|33)|30|4)|(2:11|12)|20|21) */
            /* JADX WARNING: Missing exception handler attribute for start block: B:20:0x0074 */
            /* JADX WARNING: Missing exception handler attribute for start block: B:25:0x0089 */
            /* JADX WARNING: Unknown top exception splitter block from list: {B:20:0x0074=Splitter:B:20:0x0074, B:25:0x0089=Splitter:B:25:0x0089} */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void run() {
                /*
                    r6 = this;
                    java.lang.Object r0 = io.branch.referral.ServerRequestQueue.reqQueueLockObject
                    monitor-enter(r0)
                    org.json.JSONArray r1 = new org.json.JSONArray     // Catch:{ all -> 0x008a }
                    r1.<init>()     // Catch:{ all -> 0x008a }
                    io.branch.referral.ServerRequestQueue r2 = io.branch.referral.ServerRequestQueue.this     // Catch:{ all -> 0x008a }
                    java.util.List r2 = r2.queue     // Catch:{ all -> 0x008a }
                    java.util.Iterator r2 = r2.iterator()     // Catch:{ all -> 0x008a }
                L_0x0014:
                    boolean r3 = r2.hasNext()     // Catch:{ all -> 0x008a }
                    if (r3 == 0) goto L_0x0030
                    java.lang.Object r3 = r2.next()     // Catch:{ all -> 0x008a }
                    io.branch.referral.ServerRequest r3 = (io.branch.referral.ServerRequest) r3     // Catch:{ all -> 0x008a }
                    boolean r4 = r3.isPersistable()     // Catch:{ all -> 0x008a }
                    if (r4 == 0) goto L_0x0014
                    org.json.JSONObject r3 = r3.toJSON()     // Catch:{ all -> 0x008a }
                    if (r3 == 0) goto L_0x0014
                    r1.put(r3)     // Catch:{ all -> 0x008a }
                    goto L_0x0014
                L_0x0030:
                    io.branch.referral.ServerRequestQueue r2 = io.branch.referral.ServerRequestQueue.this     // Catch:{ Exception -> 0x0046 }
                    android.content.SharedPreferences$Editor r2 = r2.editor     // Catch:{ Exception -> 0x0046 }
                    java.lang.String r3 = "BNCServerRequestQueue"
                    java.lang.String r4 = r1.toString()     // Catch:{ Exception -> 0x0046 }
                    android.content.SharedPreferences$Editor r2 = r2.putString(r3, r4)     // Catch:{ Exception -> 0x0046 }
                    r2.commit()     // Catch:{ Exception -> 0x0046 }
                    goto L_0x0074
                L_0x0044:
                    r2 = move-exception
                    goto L_0x0076
                L_0x0046:
                    r2 = move-exception
                    java.lang.String r3 = "Persisting Queue: "
                    java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0044 }
                    r4.<init>()     // Catch:{ all -> 0x0044 }
                    java.lang.String r5 = "Failed to persit queue "
                    r4.append(r5)     // Catch:{ all -> 0x0044 }
                    java.lang.String r2 = r2.getMessage()     // Catch:{ all -> 0x0044 }
                    r4.append(r2)     // Catch:{ all -> 0x0044 }
                    java.lang.String r2 = r4.toString()     // Catch:{ all -> 0x0044 }
                    io.branch.referral.PrefHelper.Debug(r3, r2)     // Catch:{ all -> 0x0044 }
                    io.branch.referral.ServerRequestQueue r2 = io.branch.referral.ServerRequestQueue.this     // Catch:{ Exception -> 0x0074 }
                    android.content.SharedPreferences$Editor r2 = r2.editor     // Catch:{ Exception -> 0x0074 }
                    java.lang.String r3 = "BNCServerRequestQueue"
                    java.lang.String r1 = r1.toString()     // Catch:{ Exception -> 0x0074 }
                    android.content.SharedPreferences$Editor r1 = r2.putString(r3, r1)     // Catch:{ Exception -> 0x0074 }
                    r1.commit()     // Catch:{ Exception -> 0x0074 }
                L_0x0074:
                    monitor-exit(r0)     // Catch:{ all -> 0x008a }
                    return
                L_0x0076:
                    io.branch.referral.ServerRequestQueue r3 = io.branch.referral.ServerRequestQueue.this     // Catch:{ Exception -> 0x0089 }
                    android.content.SharedPreferences$Editor r3 = r3.editor     // Catch:{ Exception -> 0x0089 }
                    java.lang.String r4 = "BNCServerRequestQueue"
                    java.lang.String r1 = r1.toString()     // Catch:{ Exception -> 0x0089 }
                    android.content.SharedPreferences$Editor r1 = r3.putString(r4, r1)     // Catch:{ Exception -> 0x0089 }
                    r1.commit()     // Catch:{ Exception -> 0x0089 }
                L_0x0089:
                    throw r2     // Catch:{ all -> 0x008a }
                L_0x008a:
                    r1 = move-exception
                    monitor-exit(r0)     // Catch:{ all -> 0x008a }
                    throw r1
                */
                throw new UnsupportedOperationException("Method not decompiled: io.branch.referral.ServerRequestQueue.AnonymousClass1.run():void");
            }
        }).start();
    }

    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:10:0x0039 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.util.List<io.branch.referral.ServerRequest> retrieve(android.content.Context r7) {
        /*
            r6 = this;
            android.content.SharedPreferences r0 = r6.sharedPref
            java.lang.String r1 = "BNCServerRequestQueue"
            r2 = 0
            java.lang.String r0 = r0.getString(r1, r2)
            java.util.LinkedList r1 = new java.util.LinkedList
            r1.<init>()
            java.util.List r1 = java.util.Collections.synchronizedList(r1)
            java.lang.Object r2 = reqQueueLockObject
            monitor-enter(r2)
            if (r0 == 0) goto L_0x0039
            org.json.JSONArray r3 = new org.json.JSONArray     // Catch:{ JSONException -> 0x0039 }
            r3.<init>(r0)     // Catch:{ JSONException -> 0x0039 }
            r0 = 0
            int r4 = r3.length()     // Catch:{ JSONException -> 0x0039 }
            r5 = 25
            int r4 = java.lang.Math.min(r4, r5)     // Catch:{ JSONException -> 0x0039 }
        L_0x0027:
            if (r0 >= r4) goto L_0x0039
            org.json.JSONObject r5 = r3.getJSONObject(r0)     // Catch:{ JSONException -> 0x0039 }
            io.branch.referral.ServerRequest r5 = io.branch.referral.ServerRequest.fromJSON(r5, r7)     // Catch:{ JSONException -> 0x0039 }
            if (r5 == 0) goto L_0x0036
            r1.add(r5)     // Catch:{ JSONException -> 0x0039 }
        L_0x0036:
            int r0 = r0 + 1
            goto L_0x0027
        L_0x0039:
            monitor-exit(r2)     // Catch:{ all -> 0x003b }
            return r1
        L_0x003b:
            r7 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x003b }
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: io.branch.referral.ServerRequestQueue.retrieve(android.content.Context):java.util.List");
    }

    public int getSize() {
        int size;
        synchronized (reqQueueLockObject) {
            size = this.queue.size();
        }
        return size;
    }

    /* access modifiers changed from: package-private */
    public void enqueue(ServerRequest serverRequest) {
        synchronized (reqQueueLockObject) {
            if (serverRequest != null) {
                this.queue.add(serverRequest);
                if (getSize() >= 25) {
                    this.queue.remove(1);
                }
                persist();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public ServerRequest dequeue() {
        ServerRequest serverRequest;
        synchronized (reqQueueLockObject) {
            ServerRequest serverRequest2 = null;
            try {
                serverRequest = this.queue.remove(0);
                try {
                    persist();
                } catch (IndexOutOfBoundsException | NoSuchElementException unused) {
                    serverRequest2 = serverRequest;
                }
            } catch (IndexOutOfBoundsException | NoSuchElementException unused2) {
                serverRequest = serverRequest2;
                return serverRequest;
            }
        }
        return serverRequest;
    }

    /* access modifiers changed from: package-private */
    public ServerRequest peek() {
        ServerRequest serverRequest;
        synchronized (reqQueueLockObject) {
            try {
                serverRequest = this.queue.get(0);
            } catch (IndexOutOfBoundsException | NoSuchElementException unused) {
                serverRequest = null;
            }
        }
        return serverRequest;
    }

    /* access modifiers changed from: package-private */
    public ServerRequest peekAt(int i) {
        ServerRequest serverRequest;
        synchronized (reqQueueLockObject) {
            try {
                serverRequest = this.queue.get(i);
            } catch (IndexOutOfBoundsException | NoSuchElementException unused) {
                serverRequest = null;
            }
        }
        return serverRequest;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:8:0x001c */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void insert(io.branch.referral.ServerRequest r3, int r4) {
        /*
            r2 = this;
            java.lang.Object r0 = reqQueueLockObject
            monitor-enter(r0)
            java.util.List<io.branch.referral.ServerRequest> r1 = r2.queue     // Catch:{ IndexOutOfBoundsException -> 0x001c }
            int r1 = r1.size()     // Catch:{ IndexOutOfBoundsException -> 0x001c }
            if (r1 >= r4) goto L_0x0011
            java.util.List<io.branch.referral.ServerRequest> r4 = r2.queue     // Catch:{ IndexOutOfBoundsException -> 0x001c }
            int r4 = r4.size()     // Catch:{ IndexOutOfBoundsException -> 0x001c }
        L_0x0011:
            java.util.List<io.branch.referral.ServerRequest> r1 = r2.queue     // Catch:{ IndexOutOfBoundsException -> 0x001c }
            r1.add(r4, r3)     // Catch:{ IndexOutOfBoundsException -> 0x001c }
            r2.persist()     // Catch:{ IndexOutOfBoundsException -> 0x001c }
            goto L_0x001c
        L_0x001a:
            r3 = move-exception
            goto L_0x001e
        L_0x001c:
            monitor-exit(r0)     // Catch:{ all -> 0x001a }
            return
        L_0x001e:
            monitor-exit(r0)     // Catch:{ all -> 0x001a }
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: io.branch.referral.ServerRequestQueue.insert(io.branch.referral.ServerRequest, int):void");
    }

    public ServerRequest removeAt(int i) {
        ServerRequest serverRequest;
        synchronized (reqQueueLockObject) {
            ServerRequest serverRequest2 = null;
            try {
                serverRequest = this.queue.remove(i);
                try {
                    persist();
                } catch (IndexOutOfBoundsException unused) {
                    serverRequest2 = serverRequest;
                }
            } catch (IndexOutOfBoundsException unused2) {
                serverRequest = serverRequest2;
                return serverRequest;
            }
        }
        return serverRequest;
    }

    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:6:0x0010 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean remove(io.branch.referral.ServerRequest r4) {
        /*
            r3 = this;
            java.lang.Object r0 = reqQueueLockObject
            monitor-enter(r0)
            r1 = 0
            java.util.List<io.branch.referral.ServerRequest> r2 = r3.queue     // Catch:{ UnsupportedOperationException -> 0x0010 }
            boolean r1 = r2.remove(r4)     // Catch:{ UnsupportedOperationException -> 0x0010 }
            r3.persist()     // Catch:{ UnsupportedOperationException -> 0x0010 }
            goto L_0x0010
        L_0x000e:
            r4 = move-exception
            goto L_0x0012
        L_0x0010:
            monitor-exit(r0)     // Catch:{ all -> 0x000e }
            return r1
        L_0x0012:
            monitor-exit(r0)     // Catch:{ all -> 0x000e }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: io.branch.referral.ServerRequestQueue.remove(io.branch.referral.ServerRequest):boolean");
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x000e */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void clear() {
        /*
            r2 = this;
            java.lang.Object r0 = reqQueueLockObject
            monitor-enter(r0)
            java.util.List<io.branch.referral.ServerRequest> r1 = r2.queue     // Catch:{ UnsupportedOperationException -> 0x000e }
            r1.clear()     // Catch:{ UnsupportedOperationException -> 0x000e }
            r2.persist()     // Catch:{ UnsupportedOperationException -> 0x000e }
            goto L_0x000e
        L_0x000c:
            r1 = move-exception
            goto L_0x0010
        L_0x000e:
            monitor-exit(r0)     // Catch:{ all -> 0x000c }
            return
        L_0x0010:
            monitor-exit(r0)     // Catch:{ all -> 0x000c }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: io.branch.referral.ServerRequestQueue.clear():void");
    }

    /* access modifiers changed from: package-private */
    public boolean containsClose() {
        synchronized (reqQueueLockObject) {
            for (ServerRequest next : this.queue) {
                if (next != null && next.getRequestPath().equals(Defines.RequestPath.RegisterClose.getPath())) {
                    return true;
                }
            }
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    public boolean containsInstallOrOpen() {
        synchronized (reqQueueLockObject) {
            for (ServerRequest next : this.queue) {
                if (next != null && ((next instanceof ServerRequestRegisterInstall) || (next instanceof ServerRequestRegisterOpen))) {
                    return true;
                }
            }
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    public void moveInstallOrOpenToFront(ServerRequest serverRequest, int i, Branch.BranchReferralInitListener branchReferralInitListener) {
        synchronized (reqQueueLockObject) {
            Iterator<ServerRequest> it = this.queue.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                ServerRequest next = it.next();
                if (next == null || (!(next instanceof ServerRequestRegisterInstall) && !(next instanceof ServerRequestRegisterOpen))) {
                }
            }
            it.remove();
        }
        insert(serverRequest, i == 0 ? 0 : 1);
    }

    /* access modifiers changed from: package-private */
    public void setInstallOrOpenCallback(Branch.BranchReferralInitListener branchReferralInitListener) {
        synchronized (reqQueueLockObject) {
            for (ServerRequest next : this.queue) {
                if (next != null) {
                    if (next instanceof ServerRequestRegisterInstall) {
                        ((ServerRequestRegisterInstall) next).setInitFinishedCallback(branchReferralInitListener);
                    } else if (next instanceof ServerRequestRegisterOpen) {
                        ((ServerRequestRegisterOpen) next).setInitFinishedCallback(branchReferralInitListener);
                    }
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void unlockProcessWait(ServerRequest.PROCESS_WAIT_LOCK process_wait_lock) {
        synchronized (reqQueueLockObject) {
            for (ServerRequest next : this.queue) {
                if (next != null) {
                    next.removeProcessWaitLock(process_wait_lock);
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void setStrongMatchWaitLock() {
        synchronized (reqQueueLockObject) {
            for (ServerRequest next : this.queue) {
                if (next != null && (next instanceof ServerRequestInitSession)) {
                    next.addProcessWaitLock(ServerRequest.PROCESS_WAIT_LOCK.STRONG_MATCH_PENDING_WAIT_LOCK);
                }
            }
        }
    }
}
