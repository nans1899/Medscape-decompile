package com.adobe.mobile;

import android.database.DatabaseUtils;
import android.database.SQLException;
import com.adobe.mobile.AbstractDatabaseBacking;
import java.util.Timer;
import java.util.TimerTask;

abstract class AbstractHitDatabase extends AbstractDatabaseBacking {
    protected final Object backgroundMutex = new Object();
    protected boolean bgThreadActive = false;
    protected String dbCreateStatement;
    protected long lastHitTimestamp;
    protected long numberOfUnsentHits;
    private TimerTask referrerTask;
    private Timer referrerTimer;
    private final Object referrerTimerMutex = new Object();

    AbstractHitDatabase() {
    }

    /* access modifiers changed from: protected */
    public Hit selectOldestHit() throws UnsupportedOperationException {
        throw new UnsupportedOperationException("getFirstHitInQueue must be overwritten");
    }

    /* access modifiers changed from: protected */
    public Runnable workerThread() throws UnsupportedOperationException {
        throw new UnsupportedOperationException("workerThread must be overwritten");
    }

    /* access modifiers changed from: protected */
    public void initializeDatabase() {
        try {
            this.database.execSQL(this.dbCreateStatement);
        } catch (NullPointerException e) {
            StaticMethods.logErrorFormat("%s - Unable to create database due to an invalid path (%s)", this.logPrefix, e.getLocalizedMessage());
        } catch (SQLException e2) {
            StaticMethods.logErrorFormat("%s - Unable to create database due to a sql error (%s)", this.logPrefix, e2.getLocalizedMessage());
        } catch (Exception e3) {
            StaticMethods.logErrorFormat("%s - Unable to create database due to an unexpected error (%s)", this.logPrefix, e3.getLocalizedMessage());
        }
    }

    /* access modifiers changed from: protected */
    public void deleteHit(String str) throws AbstractDatabaseBacking.CorruptedDatabaseException {
        if (str == null || str.trim().length() == 0) {
            StaticMethods.logDebugFormat("%s - Unable to delete hit due to an invalid parameter", this.logPrefix);
            return;
        }
        synchronized (this.dbMutex) {
            try {
                this.database.delete("HITS", "ID = ?", new String[]{str});
                this.numberOfUnsentHits--;
            } catch (NullPointerException e) {
                StaticMethods.logErrorFormat("%s - Unable to delete hit due to an unopened database (%s)", this.logPrefix, e.getLocalizedMessage());
            } catch (SQLException e2) {
                StaticMethods.logErrorFormat("%s - Unable to delete hit due to a sql error (%s)", this.logPrefix, e2.getLocalizedMessage());
                throw new AbstractDatabaseBacking.CorruptedDatabaseException("Unable to delete, database probably corrupted (" + e2.getLocalizedMessage() + ")");
            } catch (Exception e3) {
                StaticMethods.logErrorFormat("%s - Unable to delete hit due to an unexpected error (%s)", this.logPrefix, e3.getLocalizedMessage());
                throw new AbstractDatabaseBacking.CorruptedDatabaseException("Unexpected exception, database probably corrupted (" + e3.getLocalizedMessage() + ")");
            }
        }
    }

    /* access modifiers changed from: protected */
    public void clearTrackingQueue() {
        synchronized (this.dbMutex) {
            try {
                this.database.delete("HITS", (String) null, (String[]) null);
                this.numberOfUnsentHits = 0;
            } catch (NullPointerException e) {
                StaticMethods.logErrorFormat("%s - Unable to clear tracking queue due to an unopened database (%s)", this.logPrefix, e.getLocalizedMessage());
            } catch (SQLException e2) {
                StaticMethods.logErrorFormat("%s - Unable to clear tracking queue due to a sql error (%s)", this.logPrefix, e2.getLocalizedMessage());
            } catch (Exception e3) {
                StaticMethods.logErrorFormat("%s - Unable to clear tracking queue due to an unexpected error (%s)", this.logPrefix, e3.getLocalizedMessage());
            }
        }
    }

    /* access modifiers changed from: protected */
    public void bringOnline() {
        if (!this.bgThreadActive) {
            this.bgThreadActive = true;
            synchronized (this.backgroundMutex) {
                new Thread(workerThread(), "ADBMobileBackgroundThread").start();
            }
        }
    }

    /* access modifiers changed from: protected */
    public void kick(boolean z) {
        MobileConfig instance = MobileConfig.getInstance();
        boolean z2 = false;
        if (ReferrerHandler.getReferrerProcessed() || instance.getReferrerTimeout() <= 0) {
            if (this.referrerTimer != null) {
                synchronized (this.referrerTimerMutex) {
                    try {
                        this.referrerTimer.cancel();
                    } catch (Exception e) {
                        StaticMethods.logErrorFormat("%s - Error cancelling referrer timer (%s)", this.logPrefix, e.getMessage());
                    }
                    this.referrerTask = null;
                }
            }
            if (instance.getPrivacyStatus() == MobilePrivacyStatus.MOBILE_PRIVACY_STATUS_OPT_IN) {
                if (!instance.getOfflineTrackingEnabled() || this.numberOfUnsentHits > ((long) instance.getBatchLimit())) {
                    z2 = true;
                }
                if (z2 || z) {
                    bringOnline();
                    return;
                }
                return;
            }
            return;
        }
        synchronized (this.referrerTimerMutex) {
            if (this.referrerTask == null) {
                try {
                    this.referrerTask = new ReferrerTimeoutTask(z);
                    Timer timer = new Timer();
                    this.referrerTimer = timer;
                    timer.schedule(this.referrerTask, (long) MobileConfig.getInstance().getReferrerTimeout());
                } catch (Exception e2) {
                    StaticMethods.logErrorFormat("%s - Error creating referrer timer (%s)", this.logPrefix, e2.getMessage());
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void forceKick() {
        kick(true);
    }

    /* access modifiers changed from: protected */
    public long getTrackingQueueSize() {
        long j;
        synchronized (this.dbMutex) {
            try {
                j = DatabaseUtils.queryNumEntries(this.database, "HITS");
            } catch (NullPointerException e) {
                StaticMethods.logErrorFormat("%s - Unable to get tracking queue size due to an unopened database (%s)", this.logPrefix, e.getLocalizedMessage());
                j = 0;
                return j;
            } catch (SQLException e2) {
                StaticMethods.logErrorFormat("%s - Unable to get tracking queue size due to a sql error (%s)", this.logPrefix, e2.getLocalizedMessage());
                j = 0;
                return j;
            } catch (Exception e3) {
                StaticMethods.logErrorFormat("%s - Unable to get tracking queue size due to an unexpected error (%s)", this.logPrefix, e3.getLocalizedMessage());
                j = 0;
                return j;
            }
        }
        return j;
    }

    /* access modifiers changed from: protected */
    public void postReset() {
        this.numberOfUnsentHits = 0;
    }

    protected static class Hit {
        String identifier;
        String postBody;
        String postType;
        int timeout;
        long timestamp;
        String urlFragment;

        protected Hit() {
        }
    }

    protected class ReferrerTimeoutTask extends TimerTask {
        private boolean kickFlag = false;

        ReferrerTimeoutTask(boolean z) {
            this.kickFlag = z;
        }

        public void run() {
            ReferrerHandler.setReferrerProcessed(true);
            StaticMethods.logDebugFormat("%s - Referrer timeout has expired without referrer data", AbstractHitDatabase.this.logPrefix);
            AbstractHitDatabase.this.kick(this.kickFlag);
        }
    }
}
