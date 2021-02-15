package com.medscape.android.homescreen.views;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.KeyEvent;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
import com.medscape.android.MedscapeApplication;
import com.medscape.android.R;
import com.medscape.android.activity.install.DrugInstallationActivity;
import com.medscape.android.update.BackgroundClinicalUpdateService;
import com.medscape.android.updater.UpdateManager;
import com.medscape.android.util.DialogUtil;
import com.medscape.android.util.Util;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.anko.AlertBuilder;
import org.jetbrains.anko.AndroidDialogsKt;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "run"}, k = 3, mv = {1, 4, 0})
/* compiled from: HomeScreenDialog.kt */
final class HomeScreenDialog$showDialogs$1 implements Runnable {
    final /* synthetic */ String $message;
    final /* synthetic */ int $msgID;
    final /* synthetic */ HomeScreenDialog this$0;

    HomeScreenDialog$showDialogs$1(HomeScreenDialog homeScreenDialog, int i, String str) {
        this.this$0 = homeScreenDialog;
        this.$msgID = i;
        this.$message = str;
    }

    public final void run() {
        try {
            if (!this.this$0.getHome().isFinishing()) {
                switch (this.$msgID) {
                    case 1:
                        AlertBuilder<AlertDialog> alert = AndroidDialogsKt.alert((Context) this.this$0.getHome(), (CharSequence) this.$message, (CharSequence) this.this$0.getString(R.string.alert_dialog_clinical_reference_update_available_title), (Function1<? super AlertBuilder<? extends DialogInterface>, Unit>) new Function1<AlertBuilder<? extends DialogInterface>, Unit>(this) {
                            final /* synthetic */ HomeScreenDialog$showDialogs$1 this$0;

                            {
                                this.this$0 = r1;
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                                invoke((AlertBuilder<? extends DialogInterface>) (AlertBuilder) obj);
                                return Unit.INSTANCE;
                            }

                            public final void invoke(AlertBuilder<? extends DialogInterface> alertBuilder) {
                                Intrinsics.checkNotNullParameter(alertBuilder, "$receiver");
                                alertBuilder.positiveButton((int) R.string.alert_dialog_update_now_button_text, (Function1<? super DialogInterface, Unit>) new Function1<DialogInterface, Unit>(this) {
                                    final /* synthetic */ AnonymousClass1 this$0;

                                    {
                                        this.this$0 = r1;
                                    }

                                    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                                        invoke((DialogInterface) obj);
                                        return Unit.INSTANCE;
                                    }

                                    public final void invoke(DialogInterface dialogInterface) {
                                        Intrinsics.checkNotNullParameter(dialogInterface, "dialog");
                                        if (!Util.isSDCardAvailable()) {
                                            DialogUtil.showAlertDialog(9, (String) null, this.this$0.this$0.this$0.getString(R.string.alert_dialog_sdcard_required_message), this.this$0.this$0.this$0.getHome()).show();
                                        } else {
                                            MedscapeApplication medscapeApplication = MedscapeApplication.get();
                                            Intrinsics.checkNotNullExpressionValue(medscapeApplication, "MedscapeApplication.get()");
                                            if (!medscapeApplication.isBackgroundUpdateInProgress()) {
                                                this.this$0.this$0.this$0.getHome().startService(new Intent(this.this$0.this$0.this$0.getHome(), BackgroundClinicalUpdateService.class));
                                            }
                                        }
                                        dialogInterface.cancel();
                                    }
                                });
                                alertBuilder.negativeButton((int) R.string.alert_dialog_update_later_button_text, (Function1<? super DialogInterface, Unit>) AnonymousClass2.INSTANCE);
                            }
                        });
                        alert.setCancelable(false);
                        alert.show();
                        return;
                    case 2:
                        AlertBuilder<AlertDialog> alert2 = AndroidDialogsKt.alert((Context) this.this$0.getHome(), (CharSequence) this.$message, (CharSequence) this.this$0.getString(R.string.alert_dialog_clinical_reference_update_available_title), (Function1<? super AlertBuilder<? extends DialogInterface>, Unit>) new Function1<AlertBuilder<? extends DialogInterface>, Unit>(this) {
                            final /* synthetic */ HomeScreenDialog$showDialogs$1 this$0;

                            {
                                this.this$0 = r1;
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                                invoke((AlertBuilder<? extends DialogInterface>) (AlertBuilder) obj);
                                return Unit.INSTANCE;
                            }

                            public final void invoke(AlertBuilder<? extends DialogInterface> alertBuilder) {
                                Intrinsics.checkNotNullParameter(alertBuilder, "$receiver");
                                alertBuilder.positiveButton((int) R.string.alert_dialog_update_now_button_text, (Function1<? super DialogInterface, Unit>) new Function1<DialogInterface, Unit>(this) {
                                    final /* synthetic */ AnonymousClass3 this$0;

                                    {
                                        this.this$0 = r1;
                                    }

                                    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                                        invoke((DialogInterface) obj);
                                        return Unit.INSTANCE;
                                    }

                                    public final void invoke(DialogInterface dialogInterface) {
                                        Intrinsics.checkNotNullParameter(dialogInterface, "dialog");
                                        if (!Util.isSDCardAvailable()) {
                                            DialogUtil.showAlertDialog(9, (String) null, this.this$0.this$0.this$0.getString(R.string.alert_dialog_sdcard_required_message), this.this$0.this$0.this$0.getHome()).show();
                                        } else {
                                            MedscapeApplication medscapeApplication = MedscapeApplication.get();
                                            Intrinsics.checkNotNullExpressionValue(medscapeApplication, "MedscapeApplication.get()");
                                            if (!medscapeApplication.isBackgroundUpdateInProgress()) {
                                                this.this$0.this$0.this$0.getHome().startService(new Intent(this.this$0.this$0.this$0.getHome(), BackgroundClinicalUpdateService.class));
                                            }
                                        }
                                        dialogInterface.cancel();
                                    }
                                });
                            }
                        });
                        alert2.setCancelable(false);
                        alert2.show();
                        return;
                    case 5:
                        HomeScreenDialog homeScreenDialog = this.this$0;
                        String str = UpdateManager.OPTIONAL_DATA_MESSAGE;
                        Intrinsics.checkNotNullExpressionValue(str, "UpdateManager.OPTIONAL_DATA_MESSAGE");
                        String str2 = UpdateManager.OPTIONAL_DATA_MESSAGE;
                        Intrinsics.checkNotNullExpressionValue(str2, "UpdateManager.OPTIONAL_DATA_MESSAGE");
                        AlertBuilder<AlertDialog> alert3 = AndroidDialogsKt.alert((Context) this.this$0.getHome(), (CharSequence) homeScreenDialog.getDataUpdateMsg(str, str2), (CharSequence) this.this$0.getString(R.string.alert_dialog_drug_update_available_title), (Function1<? super AlertBuilder<? extends DialogInterface>, Unit>) new Function1<AlertBuilder<? extends DialogInterface>, Unit>(this) {
                            final /* synthetic */ HomeScreenDialog$showDialogs$1 this$0;

                            {
                                this.this$0 = r1;
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                                invoke((AlertBuilder<? extends DialogInterface>) (AlertBuilder) obj);
                                return Unit.INSTANCE;
                            }

                            public final void invoke(AlertBuilder<? extends DialogInterface> alertBuilder) {
                                Intrinsics.checkNotNullParameter(alertBuilder, "$receiver");
                                alertBuilder.positiveButton((int) R.string.alert_dialog_update_now_button_text, (Function1<? super DialogInterface, Unit>) new Function1<DialogInterface, Unit>(this) {
                                    final /* synthetic */ AnonymousClass5 this$0;

                                    {
                                        this.this$0 = r1;
                                    }

                                    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                                        invoke((DialogInterface) obj);
                                        return Unit.INSTANCE;
                                    }

                                    public final void invoke(DialogInterface dialogInterface) {
                                        Intrinsics.checkNotNullParameter(dialogInterface, "dialog");
                                        this.this$0.this$0.this$0.getHome().startActivityForResult(new Intent(this.this$0.this$0.this$0.getHome(), DrugInstallationActivity.class), 6);
                                        dialogInterface.cancel();
                                    }
                                });
                                alertBuilder.negativeButton((int) R.string.alert_dialog_update_later_button_text, (Function1<? super DialogInterface, Unit>) new Function1<DialogInterface, Unit>(this) {
                                    final /* synthetic */ AnonymousClass5 this$0;

                                    {
                                        this.this$0 = r1;
                                    }

                                    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                                        invoke((DialogInterface) obj);
                                        return Unit.INSTANCE;
                                    }

                                    public final void invoke(DialogInterface dialogInterface) {
                                        Intrinsics.checkNotNullParameter(dialogInterface, "dialog");
                                        this.this$0.this$0.this$0.getHome().getHomeViewModel().dataUpdateFinish();
                                        dialogInterface.cancel();
                                    }
                                });
                            }
                        });
                        alert3.setCancelable(false);
                        alert3.show();
                        return;
                    case 6:
                        HomeScreenDialog homeScreenDialog2 = this.this$0;
                        String str3 = UpdateManager.OPTIONAL_DATA_MESSAGE;
                        Intrinsics.checkNotNullExpressionValue(str3, "UpdateManager.OPTIONAL_DATA_MESSAGE");
                        String str4 = UpdateManager.MANDATORY_DATA_MESSAGE;
                        Intrinsics.checkNotNullExpressionValue(str4, "UpdateManager.MANDATORY_DATA_MESSAGE");
                        AlertBuilder<AlertDialog> alert4 = AndroidDialogsKt.alert((Context) this.this$0.getHome(), (CharSequence) homeScreenDialog2.getDataUpdateMsg(str3, str4), (CharSequence) this.this$0.getString(R.string.alert_dialog_drug_update_available_title), (Function1<? super AlertBuilder<? extends DialogInterface>, Unit>) new Function1<AlertBuilder<? extends DialogInterface>, Unit>(this) {
                            final /* synthetic */ HomeScreenDialog$showDialogs$1 this$0;

                            {
                                this.this$0 = r1;
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                                invoke((AlertBuilder<? extends DialogInterface>) (AlertBuilder) obj);
                                return Unit.INSTANCE;
                            }

                            public final void invoke(AlertBuilder<? extends DialogInterface> alertBuilder) {
                                Intrinsics.checkNotNullParameter(alertBuilder, "$receiver");
                                alertBuilder.positiveButton((int) R.string.alert_dialog_update_now_button_text, (Function1<? super DialogInterface, Unit>) new Function1<DialogInterface, Unit>(this) {
                                    final /* synthetic */ AnonymousClass7 this$0;

                                    {
                                        this.this$0 = r1;
                                    }

                                    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                                        invoke((DialogInterface) obj);
                                        return Unit.INSTANCE;
                                    }

                                    public final void invoke(DialogInterface dialogInterface) {
                                        Intrinsics.checkNotNullParameter(dialogInterface, "dialog");
                                        this.this$0.this$0.this$0.getHome().startActivityForResult(new Intent(this.this$0.this$0.this$0.getHome(), DrugInstallationActivity.class), 6);
                                        dialogInterface.cancel();
                                    }
                                });
                                alertBuilder.negativeButton((int) R.string.alert_dialog_update_later_button_text, (Function1<? super DialogInterface, Unit>) new Function1<DialogInterface, Unit>(this) {
                                    final /* synthetic */ AnonymousClass7 this$0;

                                    {
                                        this.this$0 = r1;
                                    }

                                    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                                        invoke((DialogInterface) obj);
                                        return Unit.INSTANCE;
                                    }

                                    public final void invoke(DialogInterface dialogInterface) {
                                        Intrinsics.checkNotNullParameter(dialogInterface, "dialog");
                                        this.this$0.this$0.this$0.getHome().getHomeViewModel().dataUpdateFinish();
                                        dialogInterface.dismiss();
                                    }
                                });
                            }
                        });
                        alert4.setCancelable(false);
                        alert4.show();
                        return;
                    case 7:
                        HomeScreenDialog homeScreenDialog3 = this.this$0;
                        String str5 = UpdateManager.MANDATORY_DATA_MESSAGE;
                        Intrinsics.checkNotNullExpressionValue(str5, "UpdateManager.MANDATORY_DATA_MESSAGE");
                        String str6 = UpdateManager.MANDATORY_DATA_MESSAGE;
                        Intrinsics.checkNotNullExpressionValue(str6, "UpdateManager.MANDATORY_DATA_MESSAGE");
                        AlertBuilder<AlertDialog> alert5 = AndroidDialogsKt.alert((Context) this.this$0.getHome(), (CharSequence) homeScreenDialog3.getDataUpdateMsg(str5, str6), (CharSequence) this.this$0.getString(R.string.alert_dialog_drug_update_available_title), (Function1<? super AlertBuilder<? extends DialogInterface>, Unit>) new Function1<AlertBuilder<? extends DialogInterface>, Unit>(this) {
                            final /* synthetic */ HomeScreenDialog$showDialogs$1 this$0;

                            {
                                this.this$0 = r1;
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                                invoke((AlertBuilder<? extends DialogInterface>) (AlertBuilder) obj);
                                return Unit.INSTANCE;
                            }

                            public final void invoke(AlertBuilder<? extends DialogInterface> alertBuilder) {
                                Intrinsics.checkNotNullParameter(alertBuilder, "$receiver");
                                alertBuilder.positiveButton((int) R.string.alert_dialog_update_now_button_text, (Function1<? super DialogInterface, Unit>) new Function1<DialogInterface, Unit>(this) {
                                    final /* synthetic */ AnonymousClass9 this$0;

                                    {
                                        this.this$0 = r1;
                                    }

                                    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                                        invoke((DialogInterface) obj);
                                        return Unit.INSTANCE;
                                    }

                                    public final void invoke(DialogInterface dialogInterface) {
                                        Intrinsics.checkNotNullParameter(dialogInterface, "dialog");
                                        this.this$0.this$0.this$0.getHome().startActivityForResult(new Intent(this.this$0.this$0.this$0.getHome(), DrugInstallationActivity.class), 6);
                                        dialogInterface.cancel();
                                    }
                                });
                            }
                        });
                        alert5.setCancelable(false);
                        alert5.show();
                        return;
                    case 8:
                        AlertBuilder<AlertDialog> alert6 = AndroidDialogsKt.alert((Context) this.this$0.getHome(), (CharSequence) "Failed to read Legal Data. Can not proceed.", (CharSequence) "Legal Update Required", (Function1<? super AlertBuilder<? extends DialogInterface>, Unit>) new Function1<AlertBuilder<? extends DialogInterface>, Unit>(this) {
                            final /* synthetic */ HomeScreenDialog$showDialogs$1 this$0;

                            {
                                this.this$0 = r1;
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                                invoke((AlertBuilder<? extends DialogInterface>) (AlertBuilder) obj);
                                return Unit.INSTANCE;
                            }

                            public final void invoke(AlertBuilder<? extends DialogInterface> alertBuilder) {
                                Intrinsics.checkNotNullParameter(alertBuilder, "$receiver");
                                alertBuilder.positiveButton("OK", (Function1<? super DialogInterface, Unit>) new Function1<DialogInterface, Unit>(this) {
                                    final /* synthetic */ AnonymousClass11 this$0;

                                    {
                                        this.this$0 = r1;
                                    }

                                    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                                        invoke((DialogInterface) obj);
                                        return Unit.INSTANCE;
                                    }

                                    public final void invoke(DialogInterface dialogInterface) {
                                        Intrinsics.checkNotNullParameter(dialogInterface, "it");
                                        this.this$0.this$0.this$0.getHome().finish();
                                    }
                                });
                            }
                        });
                        alert6.setCancelable(false);
                        alert6.show();
                        return;
                    case 11:
                        AlertBuilder<AlertDialog> alert7 = AndroidDialogsKt.alert((Context) this.this$0.getHome(), (CharSequence) "A Network Connection is required to perform necessary updates.", (CharSequence) "Update Required", (Function1<? super AlertBuilder<? extends DialogInterface>, Unit>) new Function1<AlertBuilder<? extends DialogInterface>, Unit>(this) {
                            final /* synthetic */ HomeScreenDialog$showDialogs$1 this$0;

                            {
                                this.this$0 = r1;
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                                invoke((AlertBuilder<? extends DialogInterface>) (AlertBuilder) obj);
                                return Unit.INSTANCE;
                            }

                            public final void invoke(AlertBuilder<? extends DialogInterface> alertBuilder) {
                                Intrinsics.checkNotNullParameter(alertBuilder, "$receiver");
                                alertBuilder.positiveButton("Retry", (Function1<? super DialogInterface, Unit>) new Function1<DialogInterface, Unit>(this) {
                                    final /* synthetic */ AnonymousClass13 this$0;

                                    {
                                        this.this$0 = r1;
                                    }

                                    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                                        invoke((DialogInterface) obj);
                                        return Unit.INSTANCE;
                                    }

                                    public final void invoke(DialogInterface dialogInterface) {
                                        Intrinsics.checkNotNullParameter(dialogInterface, "it");
                                        this.this$0.this$0.this$0.getHome().getHomeViewModel().getUpdateManager().checkVerXml(0);
                                    }
                                });
                            }
                        });
                        alert7.setCancelable(false);
                        alert7.show();
                        return;
                    case 12:
                        AlertBuilder<AlertDialog> alert8 = AndroidDialogsKt.alert((Context) this.this$0.getHome(), (CharSequence) this.this$0.getString(R.string.ad_blocker_dialog_message), (CharSequence) this.this$0.getString(R.string.ad_blocker_dialog_title), (Function1<? super AlertBuilder<? extends DialogInterface>, Unit>) new Function1<AlertBuilder<? extends DialogInterface>, Unit>(this) {
                            final /* synthetic */ HomeScreenDialog$showDialogs$1 this$0;

                            {
                                this.this$0 = r1;
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                                invoke((AlertBuilder<? extends DialogInterface>) (AlertBuilder) obj);
                                return Unit.INSTANCE;
                            }

                            public final void invoke(AlertBuilder<? extends DialogInterface> alertBuilder) {
                                Intrinsics.checkNotNullParameter(alertBuilder, "$receiver");
                                alertBuilder.positiveButton("Retry", (Function1<? super DialogInterface, Unit>) new Function1<DialogInterface, Unit>(this) {
                                    final /* synthetic */ AnonymousClass15 this$0;

                                    {
                                        this.this$0 = r1;
                                    }

                                    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                                        invoke((DialogInterface) obj);
                                        return Unit.INSTANCE;
                                    }

                                    public final void invoke(DialogInterface dialogInterface) {
                                        Intrinsics.checkNotNullParameter(dialogInterface, "it");
                                        this.this$0.this$0.this$0.getHome().getHomeViewModel().getUpdateManager().checkVerXml(0);
                                    }
                                });
                            }
                        });
                        alert8.setCancelable(false);
                        alert8.show();
                        return;
                    case 13:
                        AlertBuilder alert$default = AndroidDialogsKt.alert$default((Context) this.this$0.getHome(), (CharSequence) this.$message, (CharSequence) null, (Function1) new Function1<AlertBuilder<? extends DialogInterface>, Unit>(this) {
                            final /* synthetic */ HomeScreenDialog$showDialogs$1 this$0;

                            {
                                this.this$0 = r1;
                            }

                            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                                invoke((AlertBuilder<? extends DialogInterface>) (AlertBuilder) obj);
                                return Unit.INSTANCE;
                            }

                            public final void invoke(AlertBuilder<? extends DialogInterface> alertBuilder) {
                                Intrinsics.checkNotNullParameter(alertBuilder, "$receiver");
                                alertBuilder.positiveButton("Retry", (Function1<? super DialogInterface, Unit>) new Function1<DialogInterface, Unit>(this) {
                                    final /* synthetic */ AnonymousClass17 this$0;

                                    {
                                        this.this$0 = r1;
                                    }

                                    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                                        invoke((DialogInterface) obj);
                                        return Unit.INSTANCE;
                                    }

                                    public final void invoke(DialogInterface dialogInterface) {
                                        Intrinsics.checkNotNullParameter(dialogInterface, "dialog");
                                        dialogInterface.cancel();
                                        this.this$0.this$0.this$0.showDialogs(13, this.this$0.this$0.$message);
                                    }
                                });
                            }
                        }, 2, (Object) null);
                        alert$default.setCancelable(false);
                        ((AlertDialog) alert$default.show()).setOnKeyListener(new DialogInterface.OnKeyListener(this) {
                            final /* synthetic */ HomeScreenDialog$showDialogs$1 this$0;

                            {
                                this.this$0 = r1;
                            }

                            public final boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                                if (i == 4) {
                                    if (dialogInterface != null) {
                                        dialogInterface.dismiss();
                                        this.this$0.this$0.getHome().finish();
                                    }
                                } else if (i == 84 || i == 82) {
                                    return true;
                                } else {
                                    return false;
                                }
                                return false;
                            }
                        });
                        return;
                    default:
                        return;
                }
                th.printStackTrace();
                FirebaseCrashlytics.getInstance().recordException(th);
            }
        } catch (Throwable th) {
            th.printStackTrace();
            FirebaseCrashlytics.getInstance().recordException(th);
        }
    }
}
