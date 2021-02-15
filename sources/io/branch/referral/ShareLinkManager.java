package io.branch.referral;

import android.app.Dialog;
import android.content.ClipData;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.ClipboardManager;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import io.branch.referral.Branch;
import io.branch.referral.SharingHelper;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.io.IOUtils;

class ShareLinkManager {
    /* access modifiers changed from: private */
    public static int viewItemMinHeight_ = 100;
    /* access modifiers changed from: private */
    public final int BG_COLOR_DISABLED = Color.argb(20, 17, 4, 56);
    /* access modifiers changed from: private */
    public final int BG_COLOR_ENABLED = Color.argb(60, 17, 4, 56);
    /* access modifiers changed from: private */
    public List<ResolveInfo> appList_;
    /* access modifiers changed from: private */
    public Branch.ShareLinkBuilder builder_;
    Branch.BranchLinkShareListener callback_;
    Branch.IChannelProperties channelPropertiesCallback_;
    Context context_;
    private List<String> excludeFromShareSheet = new ArrayList();
    /* access modifiers changed from: private */
    public int iconSize_ = 50;
    private List<String> includeInShareSheet = new ArrayList();
    /* access modifiers changed from: private */
    public boolean isShareInProgress_ = false;
    final int leftMargin = 100;
    final int padding = 5;
    private int shareDialogThemeID_ = -1;
    AnimatedDialog shareDlg_;
    private Intent shareLinkIntent_;

    ShareLinkManager() {
    }

    public Dialog shareLink(Branch.ShareLinkBuilder shareLinkBuilder) {
        this.builder_ = shareLinkBuilder;
        this.context_ = shareLinkBuilder.getActivity();
        this.callback_ = shareLinkBuilder.getCallback();
        this.channelPropertiesCallback_ = shareLinkBuilder.getChannelPropertiesCallback();
        Intent intent = new Intent("android.intent.action.SEND");
        this.shareLinkIntent_ = intent;
        intent.setType("text/plain");
        this.shareDialogThemeID_ = shareLinkBuilder.getStyleResourceID();
        this.includeInShareSheet = shareLinkBuilder.getIncludedInShareSheet();
        this.excludeFromShareSheet = shareLinkBuilder.getExcludedFromShareSheet();
        this.iconSize_ = shareLinkBuilder.getIconSize();
        try {
            createShareDialog(shareLinkBuilder.getPreferredOptions());
        } catch (Exception e) {
            e.printStackTrace();
            Branch.BranchLinkShareListener branchLinkShareListener = this.callback_;
            if (branchLinkShareListener != null) {
                branchLinkShareListener.onLinkShareResponse((String) null, (String) null, new BranchError("Trouble sharing link", BranchError.ERR_BRANCH_NO_SHARE_OPTION));
            } else {
                Log.i("BranchSDK", "Unable create share options. Couldn't find applications on device to share the link.");
            }
        }
        return this.shareDlg_;
    }

    public void cancelShareLinkDialog(boolean z) {
        AnimatedDialog animatedDialog = this.shareDlg_;
        if (animatedDialog != null && animatedDialog.isShowing()) {
            if (z) {
                this.shareDlg_.cancel();
            } else {
                this.shareDlg_.dismiss();
            }
        }
    }

    private void createShareDialog(List<SharingHelper.SHARE_WITH> list) {
        final ListView listView;
        PackageManager packageManager = this.context_.getPackageManager();
        ArrayList arrayList = new ArrayList();
        List<ResolveInfo> queryIntentActivities = packageManager.queryIntentActivities(this.shareLinkIntent_, 65536);
        List<ResolveInfo> arrayList2 = new ArrayList<>();
        final ArrayList arrayList3 = new ArrayList();
        ArrayList arrayList4 = new ArrayList(list);
        Iterator<ResolveInfo> it = queryIntentActivities.iterator();
        while (true) {
            SharingHelper.SHARE_WITH share_with = null;
            if (!it.hasNext()) {
                break;
            }
            ResolveInfo next = it.next();
            String str = next.activityInfo.packageName;
            Iterator it2 = arrayList4.iterator();
            while (true) {
                if (!it2.hasNext()) {
                    break;
                }
                SharingHelper.SHARE_WITH share_with2 = (SharingHelper.SHARE_WITH) it2.next();
                if (next.activityInfo != null && str.toLowerCase().contains(share_with2.toString().toLowerCase())) {
                    share_with = share_with2;
                    break;
                }
            }
            if (share_with != null) {
                arrayList.add(next);
                list.remove(share_with);
            }
        }
        queryIntentActivities.removeAll(arrayList);
        queryIntentActivities.addAll(0, arrayList);
        if (this.includeInShareSheet.size() > 0) {
            for (ResolveInfo next2 : queryIntentActivities) {
                if (this.includeInShareSheet.contains(next2.activityInfo.packageName)) {
                    arrayList2.add(next2);
                }
            }
        } else {
            arrayList2 = queryIntentActivities;
        }
        for (ResolveInfo next3 : arrayList2) {
            if (!this.excludeFromShareSheet.contains(next3.activityInfo.packageName)) {
                arrayList3.add(next3);
            }
        }
        for (ResolveInfo next4 : queryIntentActivities) {
            Iterator it3 = arrayList4.iterator();
            while (it3.hasNext()) {
                if (((SharingHelper.SHARE_WITH) it3.next()).toString().equalsIgnoreCase(next4.activityInfo.packageName)) {
                    arrayList3.add(next4);
                }
            }
        }
        arrayList3.add(new CopyLinkItem());
        queryIntentActivities.add(new CopyLinkItem());
        arrayList.add(new CopyLinkItem());
        if (arrayList.size() > 1) {
            if (queryIntentActivities.size() > arrayList.size()) {
                arrayList.add(new MoreShareItem());
            }
            this.appList_ = arrayList;
        } else {
            this.appList_ = arrayList3;
        }
        final ChooserArrayAdapter chooserArrayAdapter = new ChooserArrayAdapter();
        if (this.shareDialogThemeID_ <= 1 || Build.VERSION.SDK_INT < 21) {
            listView = new ListView(this.context_);
        } else {
            listView = new ListView(this.context_, (AttributeSet) null, 0, this.shareDialogThemeID_);
        }
        listView.setHorizontalFadingEdgeEnabled(false);
        listView.setBackgroundColor(-1);
        listView.setSelector(new ColorDrawable(0));
        if (this.builder_.getSharingTitleView() != null) {
            listView.addHeaderView(this.builder_.getSharingTitleView(), (Object) null, false);
        } else if (!TextUtils.isEmpty(this.builder_.getSharingTitle())) {
            TextView textView = new TextView(this.context_);
            textView.setText(this.builder_.getSharingTitle());
            textView.setBackgroundColor(this.BG_COLOR_DISABLED);
            textView.setTextColor(this.BG_COLOR_DISABLED);
            textView.setTextAppearance(this.context_, 16973892);
            textView.setTextColor(this.context_.getResources().getColor(17170432));
            textView.setPadding(100, 5, 5, 5);
            listView.addHeaderView(textView, (Object) null, false);
        }
        listView.setAdapter(chooserArrayAdapter);
        if (this.builder_.getDividerHeight() >= 0) {
            listView.setDividerHeight(this.builder_.getDividerHeight());
        } else if (this.builder_.getIsFullWidthStyle()) {
            listView.setDividerHeight(0);
        }
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                if (view.getTag() instanceof MoreShareItem) {
                    List unused = ShareLinkManager.this.appList_ = arrayList3;
                    chooserArrayAdapter.notifyDataSetChanged();
                    return;
                }
                if (ShareLinkManager.this.callback_ != null) {
                    String charSequence = (view.getTag() == null || ShareLinkManager.this.context_ == null || ((ResolveInfo) view.getTag()).loadLabel(ShareLinkManager.this.context_.getPackageManager()) == null) ? "" : ((ResolveInfo) view.getTag()).loadLabel(ShareLinkManager.this.context_.getPackageManager()).toString();
                    ShareLinkManager.this.builder_.getShortLinkBuilder().setChannel(((ResolveInfo) view.getTag()).loadLabel(ShareLinkManager.this.context_.getPackageManager()).toString());
                    ShareLinkManager.this.callback_.onChannelSelected(charSequence);
                }
                chooserArrayAdapter.selectedPos = i - listView.getHeaderViewsCount();
                chooserArrayAdapter.notifyDataSetChanged();
                ShareLinkManager.this.invokeSharingClient((ResolveInfo) view.getTag());
                if (ShareLinkManager.this.shareDlg_ != null) {
                    ShareLinkManager.this.shareDlg_.cancel();
                }
            }
        });
        if (this.builder_.getDialogThemeResourceID() > 0) {
            this.shareDlg_ = new AnimatedDialog(this.context_, this.builder_.getDialogThemeResourceID());
        } else {
            this.shareDlg_ = new AnimatedDialog(this.context_, this.builder_.getIsFullWidthStyle());
        }
        this.shareDlg_.setContentView(listView);
        this.shareDlg_.show();
        Branch.BranchLinkShareListener branchLinkShareListener = this.callback_;
        if (branchLinkShareListener != null) {
            branchLinkShareListener.onShareLinkDialogLaunched();
        }
        this.shareDlg_.setOnDismissListener(new DialogInterface.OnDismissListener() {
            public void onDismiss(DialogInterface dialogInterface) {
                if (ShareLinkManager.this.callback_ != null) {
                    ShareLinkManager.this.callback_.onShareLinkDialogDismissed();
                    ShareLinkManager.this.callback_ = null;
                }
                if (!ShareLinkManager.this.isShareInProgress_) {
                    ShareLinkManager.this.context_ = null;
                    Branch.ShareLinkBuilder unused = ShareLinkManager.this.builder_ = null;
                }
                ShareLinkManager.this.shareDlg_ = null;
            }
        });
    }

    /* access modifiers changed from: private */
    public void invokeSharingClient(final ResolveInfo resolveInfo) {
        this.isShareInProgress_ = true;
        final String charSequence = resolveInfo.loadLabel(this.context_.getPackageManager()).toString();
        this.builder_.getShortLinkBuilder().generateShortUrlInternal(new Branch.BranchLinkCreateListener() {
            public void onLinkCreate(String str, BranchError branchError) {
                if (branchError == null) {
                    ShareLinkManager.this.shareWithClient(resolveInfo, str, charSequence);
                    return;
                }
                String defaultURL = ShareLinkManager.this.builder_.getDefaultURL();
                if (defaultURL == null || defaultURL.trim().length() <= 0) {
                    if (ShareLinkManager.this.callback_ != null) {
                        ShareLinkManager.this.callback_.onLinkShareResponse(str, charSequence, branchError);
                    } else {
                        Log.i("BranchSDK", "Unable to share link " + branchError.getMessage());
                    }
                    if (branchError.getErrorCode() == -113 || branchError.getErrorCode() == -117) {
                        ShareLinkManager.this.shareWithClient(resolveInfo, str, charSequence);
                        return;
                    }
                    ShareLinkManager.this.cancelShareLinkDialog(false);
                    boolean unused = ShareLinkManager.this.isShareInProgress_ = false;
                    return;
                }
                ShareLinkManager.this.shareWithClient(resolveInfo, defaultURL, charSequence);
            }
        }, true);
    }

    /* access modifiers changed from: private */
    public void shareWithClient(ResolveInfo resolveInfo, String str, String str2) {
        Branch.BranchLinkShareListener branchLinkShareListener = this.callback_;
        if (branchLinkShareListener != null) {
            branchLinkShareListener.onLinkShareResponse(str, str2, (BranchError) null);
        } else {
            Log.i("BranchSDK", "Shared link with " + str2);
        }
        if (resolveInfo instanceof CopyLinkItem) {
            addLinkToClipBoard(str, this.builder_.getShareMsg());
            return;
        }
        this.shareLinkIntent_.setPackage(resolveInfo.activityInfo.packageName);
        String shareSub = this.builder_.getShareSub();
        String shareMsg = this.builder_.getShareMsg();
        Branch.IChannelProperties iChannelProperties = this.channelPropertiesCallback_;
        if (iChannelProperties != null) {
            String sharingTitleForChannel = iChannelProperties.getSharingTitleForChannel(str2);
            String sharingMessageForChannel = this.channelPropertiesCallback_.getSharingMessageForChannel(str2);
            if (!TextUtils.isEmpty(sharingTitleForChannel)) {
                shareSub = sharingTitleForChannel;
            }
            if (!TextUtils.isEmpty(sharingMessageForChannel)) {
                shareMsg = sharingMessageForChannel;
            }
        }
        if (shareSub != null && shareSub.trim().length() > 0) {
            this.shareLinkIntent_.putExtra("android.intent.extra.SUBJECT", shareSub);
        }
        Intent intent = this.shareLinkIntent_;
        intent.putExtra("android.intent.extra.TEXT", shareMsg + IOUtils.LINE_SEPARATOR_UNIX + str);
        this.context_.startActivity(this.shareLinkIntent_);
    }

    private void addLinkToClipBoard(String str, String str2) {
        if (Build.VERSION.SDK_INT < 11) {
            ((ClipboardManager) this.context_.getSystemService("clipboard")).setText(str);
        } else {
            ((android.content.ClipboardManager) this.context_.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText(str2, str));
        }
        Toast.makeText(this.context_, this.builder_.getUrlCopiedMessage(), 0).show();
    }

    private class ChooserArrayAdapter extends BaseAdapter {
        public int selectedPos;

        public long getItemId(int i) {
            return (long) i;
        }

        private ChooserArrayAdapter() {
            this.selectedPos = -1;
        }

        public int getCount() {
            return ShareLinkManager.this.appList_.size();
        }

        public Object getItem(int i) {
            return ShareLinkManager.this.appList_.get(i);
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            ShareItemView shareItemView;
            if (view == null) {
                ShareLinkManager shareLinkManager = ShareLinkManager.this;
                shareItemView = new ShareItemView(shareLinkManager.context_);
            } else {
                shareItemView = (ShareItemView) view;
            }
            ResolveInfo resolveInfo = (ResolveInfo) ShareLinkManager.this.appList_.get(i);
            shareItemView.setLabel(resolveInfo.loadLabel(ShareLinkManager.this.context_.getPackageManager()).toString(), resolveInfo.loadIcon(ShareLinkManager.this.context_.getPackageManager()), i == this.selectedPos);
            shareItemView.setTag(resolveInfo);
            shareItemView.setClickable(false);
            return shareItemView;
        }

        public boolean isEnabled(int i) {
            return this.selectedPos < 0;
        }
    }

    private class ShareItemView extends TextView {
        Context context_;
        int iconSizeDP_;

        public ShareItemView(Context context) {
            super(context);
            this.context_ = context;
            setPadding(100, 5, 5, 5);
            setGravity(8388627);
            setMinWidth(this.context_.getResources().getDisplayMetrics().widthPixels);
            this.iconSizeDP_ = ShareLinkManager.this.iconSize_ != 0 ? BranchUtil.dpToPx(context, ShareLinkManager.this.iconSize_) : 0;
        }

        public void setLabel(String str, Drawable drawable, boolean z) {
            setText("\t" + str);
            setTag(str);
            if (drawable == null) {
                setTextAppearance(this.context_, 16973890);
                setCompoundDrawablesWithIntrinsicBounds((Drawable) null, (Drawable) null, (Drawable) null, (Drawable) null);
            } else {
                int i = this.iconSizeDP_;
                if (i != 0) {
                    drawable.setBounds(0, 0, i, i);
                    setCompoundDrawables(drawable, (Drawable) null, (Drawable) null, (Drawable) null);
                } else {
                    setCompoundDrawablesWithIntrinsicBounds(drawable, (Drawable) null, (Drawable) null, (Drawable) null);
                }
                setTextAppearance(this.context_, 16973892);
                int unused = ShareLinkManager.viewItemMinHeight_ = Math.max(ShareLinkManager.viewItemMinHeight_, drawable.getIntrinsicHeight() + 5);
            }
            setMinHeight(ShareLinkManager.viewItemMinHeight_);
            setTextColor(this.context_.getResources().getColor(17170444));
            if (z) {
                setBackgroundColor(ShareLinkManager.this.BG_COLOR_ENABLED);
            } else {
                setBackgroundColor(ShareLinkManager.this.BG_COLOR_DISABLED);
            }
        }
    }

    private class MoreShareItem extends ResolveInfo {
        private MoreShareItem() {
        }

        public CharSequence loadLabel(PackageManager packageManager) {
            return ShareLinkManager.this.builder_.getMoreOptionText();
        }

        public Drawable loadIcon(PackageManager packageManager) {
            return ShareLinkManager.this.builder_.getMoreOptionIcon();
        }
    }

    private class CopyLinkItem extends ResolveInfo {
        private CopyLinkItem() {
        }

        public CharSequence loadLabel(PackageManager packageManager) {
            return ShareLinkManager.this.builder_.getCopyURlText();
        }

        public Drawable loadIcon(PackageManager packageManager) {
            return ShareLinkManager.this.builder_.getCopyUrlIcon();
        }
    }
}
