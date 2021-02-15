package com.webmd.wbmdcmepulse.adapters.cmetracker;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import com.webmd.wbmdcmepulse.R;
import com.webmd.wbmdcmepulse.activities.CmeAbimVerificationActivity;
import com.webmd.wbmdcmepulse.models.cmetracker.CMEItem;
import com.webmd.wbmdcmepulse.models.interfaces.ICMETrackerButtonHandler;
import com.webmd.wbmdcmepulse.models.utils.Extensions;
import com.webmd.wbmdproffesionalauthentication.model.UserProfile;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;

public class CMETrackerListAdapter extends ArrayAdapter<CMEItem> implements StickyListHeadersAdapter {
    public ICMETrackerButtonHandler buttonHandler;
    private SimpleDateFormat format = new SimpleDateFormat("mm/dd/yyyy");
    private ArrayList<CMEItem> items;
    /* access modifiers changed from: private */
    public Context mContext;
    private UserProfile mUserprofile;
    private LayoutInflater vi;

    public CMETrackerListAdapter(Context context, ArrayList<CMEItem> arrayList, UserProfile userProfile) {
        super(context, 0, arrayList);
        this.mContext = context;
        this.items = arrayList;
        this.mUserprofile = userProfile;
        this.vi = (LayoutInflater) context.getSystemService("layout_inflater");
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        final CMEItem cMEItem = this.items.get(i);
        if (cMEItem == null) {
            return view;
        }
        if (cMEItem.isSection()) {
            View inflate = this.vi.inflate(R.layout.listviewitem_section_cme_tracker, (ViewGroup) null);
            inflate.setOnClickListener((View.OnClickListener) null);
            inflate.setOnLongClickListener((View.OnLongClickListener) null);
            inflate.setLongClickable(false);
            setTextForTextView(inflate, R.id.cmeTrackerSectionTextView, cMEItem.getTitle());
            return inflate;
        }
        View inflate2 = this.vi.inflate(R.layout.listviewitem_cme_tracker, (ViewGroup) null);
        if (cMEItem.getType().equals(CMEItem.IN_PROGRESS)) {
            inflate2.findViewById(R.id.arrow).setVisibility(4);
            inflate2.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    CMETrackerListAdapter.this.buttonHandler.viewActivityButtonPressed(view, cMEItem);
                    view.setOnClickListener((View.OnClickListener) null);
                }
            });
            inflate2.setBackgroundDrawable(this.mContext.getResources().getDrawable(R.drawable.rectangular_ripple));
        } else {
            inflate2.findViewById(R.id.arrow).setVisibility(0);
        }
        final TextView textView = (TextView) inflate2.findViewById(R.id.moc_status);
        if (textView != null) {
            if (cMEItem.getType().equals(CMEItem.COMPLETE_MOC)) {
                textView.setVisibility(0);
                textView.setText(getMOCStatus(cMEItem));
                textView.setOnClickListener(new View.OnClickListener() {
                    /* JADX WARNING: Can't fix incorrect switch cases order */
                    /* Code decompiled incorrectly, please refer to instructions dump. */
                    public void onClick(android.view.View r5) {
                        /*
                            r4 = this;
                            android.widget.TextView r5 = r0
                            java.lang.CharSequence r5 = r5.getText()
                            java.lang.String r5 = r5.toString()
                            int r0 = r5.hashCode()
                            r1 = 3
                            r2 = 2
                            r3 = 1
                            switch(r0) {
                                case -1344773396: goto L_0x0033;
                                case 162959651: goto L_0x0029;
                                case 1138055074: goto L_0x001f;
                                case 1311167103: goto L_0x0015;
                                default: goto L_0x0014;
                            }
                        L_0x0014:
                            goto L_0x003d
                        L_0x0015:
                            java.lang.String r0 = "Status: Accepted"
                            boolean r5 = r5.equals(r0)
                            if (r5 == 0) goto L_0x003d
                            r5 = 0
                            goto L_0x003e
                        L_0x001f:
                            java.lang.String r0 = "Status: Verification Needed"
                            boolean r5 = r5.equals(r0)
                            if (r5 == 0) goto L_0x003d
                            r5 = 2
                            goto L_0x003e
                        L_0x0029:
                            java.lang.String r0 = "Status: Submitted"
                            boolean r5 = r5.equals(r0)
                            if (r5 == 0) goto L_0x003d
                            r5 = 1
                            goto L_0x003e
                        L_0x0033:
                            java.lang.String r0 = "Status: Submission Error"
                            boolean r5 = r5.equals(r0)
                            if (r5 == 0) goto L_0x003d
                            r5 = 3
                            goto L_0x003e
                        L_0x003d:
                            r5 = -1
                        L_0x003e:
                            if (r5 == 0) goto L_0x0059
                            if (r5 == r3) goto L_0x0053
                            if (r5 == r2) goto L_0x004d
                            if (r5 == r1) goto L_0x0047
                            goto L_0x005e
                        L_0x0047:
                            com.webmd.wbmdcmepulse.adapters.cmetracker.CMETrackerListAdapter r5 = com.webmd.wbmdcmepulse.adapters.cmetracker.CMETrackerListAdapter.this
                            r5.showABIMSubmissionErrorAlert()
                            goto L_0x005e
                        L_0x004d:
                            com.webmd.wbmdcmepulse.adapters.cmetracker.CMETrackerListAdapter r5 = com.webmd.wbmdcmepulse.adapters.cmetracker.CMETrackerListAdapter.this
                            r5.showABIMVerificationNeededAlert()
                            goto L_0x005e
                        L_0x0053:
                            com.webmd.wbmdcmepulse.adapters.cmetracker.CMETrackerListAdapter r5 = com.webmd.wbmdcmepulse.adapters.cmetracker.CMETrackerListAdapter.this
                            r5.showABIMSubmittedAlert()
                            goto L_0x005e
                        L_0x0059:
                            com.webmd.wbmdcmepulse.adapters.cmetracker.CMETrackerListAdapter r5 = com.webmd.wbmdcmepulse.adapters.cmetracker.CMETrackerListAdapter.this
                            r5.showABIMAcceptedAlert()
                        L_0x005e:
                            return
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.webmd.wbmdcmepulse.adapters.cmetracker.CMETrackerListAdapter.AnonymousClass2.onClick(android.view.View):void");
                    }
                });
            } else {
                textView.setVisibility(8);
            }
        }
        setTextForTextView(inflate2, R.id.cmeTrackerTitleTextView, cMEItem.getTitle());
        setTextForTextView(inflate2, R.id.cmeTrackerCoreCompTextView, cMEItem.getCoreCompetency());
        DecimalFormat decimalFormat = new DecimalFormat("#0.00");
        setTextForTextView(inflate2, R.id.cmeTrackerCreditsTextView, decimalFormat.format(cMEItem.getCredit()));
        setTextForTextView(inflate2, R.id.cmeTrackerCredTypeTextView, cMEItem.getCreditType());
        setTextForTextView(inflate2, R.id.cmeTrackerRxCreditsTextView, decimalFormat.format(cMEItem.getRxCredit()));
        int i2 = R.id.cmeTrackerParticipationTextView;
        setTextForTextView(inflate2, i2, "Participation: " + this.format.format(cMEItem.getParticipationDate()));
        final Button button = (Button) inflate2.findViewById(R.id.viewActivityButton);
        final Button button2 = (Button) inflate2.findViewById(R.id.viewCertificateButton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                button.setEnabled(false);
                CMETrackerListAdapter.this.buttonHandler.viewActivityButtonPressed(view, cMEItem);
                button.setEnabled(true);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                button2.setEnabled(false);
                CMETrackerListAdapter.this.buttonHandler.viewCertificateButtonPressed(view, cMEItem);
                button2.setEnabled(true);
            }
        });
        return inflate2;
    }

    /* access modifiers changed from: private */
    public void showABIMAcceptedAlert() {
        if (Build.VERSION.SDK_INT >= 21) {
            new AlertDialog.Builder(this.mContext, R.style.CMEPulseTheme_AlertDialog).setPositiveButton(R.string.alert_ok, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            }).setMessage(R.string.tracker_abim_accepted).setTitle(R.string.tracker_abim_accepted_title).create().show();
        } else {
            new AlertDialog.Builder(this.mContext, R.style.CMEPulseTheme_AlertDialog).setPositiveButton(R.string.alert_ok, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            }).setMessage(R.string.tracker_abim_accepted).setTitle(R.string.tracker_abim_accepted_title).create().show();
        }
    }

    /* access modifiers changed from: private */
    public void showABIMSubmittedAlert() {
        if (Build.VERSION.SDK_INT >= 21) {
            new AlertDialog.Builder(this.mContext, R.style.CMEPulseTheme_AlertDialog).setPositiveButton(R.string.alert_ok, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            }).setMessage(R.string.tracker_abim_submitted).setTitle(R.string.tracker_abim_submitted_title).create().show();
        } else {
            new AlertDialog.Builder(this.mContext, R.style.CMEPulseTheme_AlertDialog).setPositiveButton(R.string.alert_ok, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            }).setMessage(R.string.tracker_abim_submitted).setTitle(R.string.tracker_abim_submitted_title).create().show();
        }
    }

    /* access modifiers changed from: private */
    public void showABIMVerificationNeededAlert() {
        if (Build.VERSION.SDK_INT >= 21) {
            new AlertDialog.Builder(this.mContext, R.style.CMEPulseTheme_AlertDialog).setPositiveButton(R.string.submit_abim_id, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    CMETrackerListAdapter.this.mContext.startActivity(new Intent(CMETrackerListAdapter.this.mContext, CmeAbimVerificationActivity.class));
                }
            }).setNegativeButton(R.string.login_forgot_password_cancel, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            }).setMessage(R.string.tracker_abim_verification_needed).setTitle(R.string.tracker_abim_verification_needed_title).create().show();
        } else {
            new AlertDialog.Builder(this.mContext, R.style.CMEPulseTheme_AlertDialog).setPositiveButton(R.string.submit_abim_id, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    CMETrackerListAdapter.this.mContext.startActivity(new Intent(CMETrackerListAdapter.this.mContext, CmeAbimVerificationActivity.class));
                }
            }).setNegativeButton(R.string.login_forgot_password_cancel, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            }).setMessage(R.string.tracker_abim_verification_needed).setTitle(R.string.tracker_abim_verification_needed_title).create().show();
        }
    }

    /* access modifiers changed from: private */
    public void showABIMSubmissionErrorAlert() {
        if (Build.VERSION.SDK_INT >= 21) {
            new AlertDialog.Builder(this.mContext, R.style.CMEPulseTheme_AlertDialog).setPositiveButton(R.string.resubmit_abim_id, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    CMETrackerListAdapter.this.mContext.startActivity(new Intent(CMETrackerListAdapter.this.mContext, CmeAbimVerificationActivity.class));
                }
            }).setNegativeButton(R.string.login_forgot_password_cancel, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            }).setMessage(R.string.tracker_abim_submission_error).setTitle(R.string.tracker_abim_submission_error_title).create().show();
        } else {
            new AlertDialog.Builder(this.mContext, R.style.CMEPulseTheme_AlertDialog).setPositiveButton(R.string.resubmit_abim_id, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    CMETrackerListAdapter.this.mContext.startActivity(new Intent(CMETrackerListAdapter.this.mContext, CmeAbimVerificationActivity.class));
                }
            }).setNegativeButton(R.string.login_forgot_password_cancel, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            }).setMessage(R.string.tracker_abim_submission_error).setTitle(R.string.tracker_abim_submission_error_title).create().show();
        }
    }

    private String getMOCStatus(CMEItem cMEItem) {
        UserProfile userProfile = this.mUserprofile;
        String str = (userProfile == null || userProfile.getProfessionProfile() == null || !Extensions.isStringNullOrEmpty(this.mUserprofile.getProfessionProfile().getAbimId(this.mContext))) ? "" : "Status: Verification Needed";
        if (Extensions.isStringNullOrEmpty(str) && cMEItem != null) {
            String mOCStatus = cMEItem.getMOCStatus();
            if (!Extensions.isStringNullOrEmpty(mOCStatus)) {
                if (mOCStatus.equalsIgnoreCase("accepted")) {
                    str = "Status: Accepted";
                } else if (mOCStatus.equalsIgnoreCase("Error") && Extensions.isStringNullOrEmpty(str) && cMEItem.getErrorCodes() != null) {
                    List asList = Arrays.asList(new String[]{"621", "622", "623", "624", "661", "664", "665"});
                    Iterator<String> it = cMEItem.getErrorCodes().iterator();
                    while (true) {
                        if (it.hasNext()) {
                            if (asList.contains(it.next())) {
                                str = "Status: Submission Error";
                                break;
                            }
                        } else {
                            break;
                        }
                    }
                }
            }
        }
        return Extensions.isStringNullOrEmpty(str) ? "Status: Submitted" : str;
    }

    public View getHeaderView(int i, View view, ViewGroup viewGroup) {
        View view2;
        HeaderViewHolder headerViewHolder;
        if (view == null) {
            headerViewHolder = new HeaderViewHolder();
            view2 = LayoutInflater.from(this.mContext).inflate(R.layout.listviewitem_section_cme_tracker, viewGroup, false);
            headerViewHolder.text = (TextView) view2.findViewById(R.id.cmeTrackerSectionTextView);
            view2.setTag(headerViewHolder);
        } else {
            view2 = view;
            headerViewHolder = (HeaderViewHolder) view.getTag();
        }
        CMEItem cMEItem = this.items.get(i);
        if (cMEItem == null) {
            headerViewHolder.text.setText("");
        } else if (cMEItem.getType().equals(CMEItem.IN_PROGRESS)) {
            headerViewHolder.text.setText(this.mContext.getString(R.string.cme_tracker_in_progress_header_label));
        } else if (cMEItem.getType().equals(CMEItem.COMPLETE_LOC)) {
            headerViewHolder.text.setText(this.mContext.getString(R.string.cme_tracker_completed_loc_header_label));
        } else if (cMEItem.getType().equals(CMEItem.COMPLETE_MOC)) {
            headerViewHolder.text.setText(this.mContext.getString(R.string.cme_tracker_completed_moc_header_label));
        } else if (cMEItem.getType().equals(CMEItem.COMPLETED)) {
            headerViewHolder.text.setText(this.mContext.getString(R.string.cme_tracker_completed_header_label));
        }
        return view2;
    }

    public long getHeaderId(int i) {
        if (this.items.get(i) == null) {
            return 4;
        }
        if (this.items.get(i).getType().equals(CMEItem.IN_PROGRESS)) {
            return 0;
        }
        if (this.items.get(i).getType().equals(CMEItem.COMPLETED)) {
            return 1;
        }
        if (this.items.get(i).getType().equals(CMEItem.COMPLETE_LOC)) {
            return 2;
        }
        return this.items.get(i).getType().equals(CMEItem.COMPLETE_LOC) ? 3 : 4;
    }

    private void setTextForTextView(View view, int i, String str) {
        TextView textView = (TextView) view.findViewById(i);
        if (textView != null) {
            textView.setText(str);
        }
    }

    class HeaderViewHolder {
        TextView text;

        HeaderViewHolder() {
        }
    }
}
