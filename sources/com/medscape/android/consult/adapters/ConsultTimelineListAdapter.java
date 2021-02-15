package com.medscape.android.consult.adapters;

import android.app.Activity;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.facebook.appevents.AppEventsConstants;
import com.medscape.android.Constants;
import com.medscape.android.R;
import com.medscape.android.Settings;
import com.medscape.android.consult.interfaces.ILoadMoreListener;
import com.medscape.android.consult.interfaces.IMedStudentNotificationListener;
import com.medscape.android.consult.interfaces.IPostDeletedListener;
import com.medscape.android.consult.interfaces.ISetVisibilityOfLowQualityPosts;
import com.medscape.android.consult.managers.ConsultMedStudentFlagsApiManager;
import com.medscape.android.consult.models.ConsultFeed;
import com.medscape.android.consult.models.ConsultFeedItem;
import com.medscape.android.consult.models.ConsultPost;
import com.medscape.android.consult.util.ConsultConstants;
import com.medscape.android.consult.util.ConsultUtils;
import com.medscape.android.consult.viewholders.ConsultDummyViewHolder;
import com.medscape.android.consult.viewholders.ConsultHeaderViewHolder;
import com.medscape.android.consult.viewholders.ConsultLoadMoreViewHolder;
import com.medscape.android.consult.viewholders.ConsultSponsoredPostViewHolder;
import com.medscape.android.consult.viewholders.ConsultTimelinePostBodyViewHolder;
import com.medscape.android.consult.viewholders.MedicalStudentsPostNotificationViewHolder;
import com.medscape.android.homescreen.user.UserProfileProvider;
import com.medscape.android.util.StringUtil;
import com.tonicartos.superslim.GridSLM;
import com.tonicartos.superslim.LinearSLM;
import com.webmd.wbmdproffesionalauthentication.model.UserProfile;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class ConsultTimelineListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements ISetVisibilityOfLowQualityPosts {
    private static final int VIEW_TYPE_DEFAULT = 4;
    private static final int VIEW_TYPE_LIST_LOAD_MORE = 3;
    private static final int VIEW_TYPE_MED_STUDENT_NOTIFICATION = 5;
    private static final int VIEW_TYPE_SPONSORED_POST = 6;
    private static final int VIEW_TYPE_TIMELINE_BODY = 2;
    private static final int VIEW_TYPE_TIMELINE_HEADER = 1;
    private int feedType = Constants.CONSULT_FEEDTYPE_ALL;
    private List<ConsultFeedItem> mActualItems = new ArrayList();
    /* access modifiers changed from: private */
    public Activity mContext;
    /* access modifiers changed from: private */
    public ConsultFeed mFeed;
    private boolean mHideSpinnerDueToError = false;
    protected List<ConsultPost> mItems = new ArrayList();
    /* access modifiers changed from: private */
    public IMedStudentNotificationListener mListener;
    private ILoadMoreListener mLoadMoreListener;
    public ConsultPost mSponsoredPost;
    /* access modifiers changed from: private */
    public boolean showNotificationHint = false;
    protected int sponsoredPostPosition = -1;

    public ConsultTimelineListAdapter(Activity activity, ILoadMoreListener iLoadMoreListener, int i, IMedStudentNotificationListener iMedStudentNotificationListener) {
        this.mContext = activity;
        this.mLoadMoreListener = iLoadMoreListener;
        this.feedType = i;
        this.mListener = iMedStudentNotificationListener;
        this.showNotificationHint = Settings.singleton(activity).getSetting(ConsultConstants.MED_STUDENTS_NOTIFICATION_HINT, false);
    }

    public void setItemsForFeed(ConsultFeed consultFeed) {
        this.mHideSpinnerDueToError = false;
        this.mFeed = consultFeed;
        ArrayList arrayList = new ArrayList();
        this.mItems = arrayList;
        addMedStudentNotificationItem(arrayList);
        if (consultFeed != null) {
            this.mActualItems = consultFeed.getFeedItems();
        }
        if (this.mActualItems != null) {
            this.sponsoredPostPosition = -1;
            boolean z = false;
            for (int i = 0; i < this.mActualItems.size(); i++) {
                if (this.mActualItems.get(i) instanceof ConsultPost) {
                    ConsultPost consultPost = (ConsultPost) this.mActualItems.get(i);
                    if (this.sponsoredPostPosition == -1 && i > 1 && !z && !ConsultUtils.isPartnerPost(consultPost)) {
                        this.sponsoredPostPosition = this.mItems.size();
                    }
                    z = ConsultUtils.isPartnerPost(consultPost);
                    if (!consultPost.isShowSponsored()) {
                        ConsultPost consultPost2 = new ConsultPost();
                        consultPost2.setPostId(consultPost.getPostId());
                        consultPost2.setPoster(consultPost.getPoster());
                        consultPost2.setTimestamp(consultPost.getTimestamp());
                        consultPost2.setIsLowQualityPost(consultPost.isLowQualityPost());
                        consultPost2.setIsLowQualityPostShown(consultPost.isLowQualityPostShown());
                        consultPost2.setDownVoteCount(consultPost.getDownVoteCount());
                        consultPost2.setIsHeader(true);
                        this.mItems.add(consultPost2);
                    }
                    this.mItems.add(consultPost);
                }
            }
            ConsultPost consultPost3 = this.mSponsoredPost;
            if (consultPost3 != null) {
                addSponsoredPost(consultPost3);
            }
            notifyDataSetChanged();
        }
    }

    public void setItemsAndRefresh(ConsultFeed consultFeed) {
        this.mHideSpinnerDueToError = false;
        this.mFeed = consultFeed;
        this.mItems = new ArrayList();
        if (consultFeed != null) {
            List<ConsultFeedItem> feedItems = consultFeed.getFeedItems();
            this.mActualItems = feedItems;
            if (feedItems != null) {
                for (int i = 0; i < this.mActualItems.size(); i++) {
                    if (this.mActualItems.get(i) instanceof ConsultPost) {
                        ConsultPost consultPost = (ConsultPost) this.mActualItems.get(i);
                        ConsultPost consultPost2 = new ConsultPost();
                        consultPost2.setPoster(consultPost.getPoster());
                        consultPost2.setTimestamp(consultPost.getTimestamp());
                        consultPost2.setIsHeader(true);
                        consultPost2.setIsLowQualityPost(consultPost.isLowQualityPost());
                        consultPost2.setIsLowQualityPostShown(consultPost.isLowQualityPostShown());
                        consultPost2.setDownVoteCount(consultPost.getDownVoteCount());
                        this.mItems.add(consultPost2);
                        this.mItems.add(consultPost);
                    }
                }
                if (this.mItems.size() > 0) {
                    addMedStudentNotificationItem(this.mItems);
                }
            }
        }
        new Handler().post(new Runnable() {
            public final void run() {
                ConsultTimelineListAdapter.this.notifyDataSetChanged();
            }
        });
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater from = LayoutInflater.from(viewGroup.getContext());
        if (i == 1) {
            return new ConsultHeaderViewHolder(from.inflate(R.layout.consult_timeline_header, viewGroup, false), this.mContext);
        }
        if (i == 2) {
            return new ConsultTimelinePostBodyViewHolder(from.inflate(R.layout.consult_timeline_body, viewGroup, false), this.mContext, new IPostDeletedListener() {
                public void onPostDeleted(int i) {
                    if (i > 0) {
                        ConsultTimelineListAdapter.this.mFeed.setTotalItems(ConsultTimelineListAdapter.this.mFeed.getTotalItems() - 1);
                        ConsultTimelineListAdapter.this.mFeed.getFeedItems().remove(i / 2);
                        ConsultTimelineListAdapter.this.mItems.remove(i);
                        ConsultTimelineListAdapter.this.mItems.remove(i - 1);
                        ConsultTimelineListAdapter.this.notifyDataSetChanged();
                    }
                }
            });
        }
        if (i == 3) {
            return new ConsultLoadMoreViewHolder(from.inflate(R.layout.consult_list_load_more, viewGroup, false), this.mLoadMoreListener);
        }
        if (i == 5) {
            return new MedicalStudentsPostNotificationViewHolder(from.inflate(R.layout.med_student_posts_notification_layout, viewGroup, false));
        }
        if (i == 6) {
            return new ConsultSponsoredPostViewHolder(from.inflate(R.layout.consult_sponser_post, viewGroup, false), this.mContext, this.feedType);
        }
        return new ConsultDummyViewHolder(from.inflate(R.layout.consult_list_loading, viewGroup, false));
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        int i2;
        View view = viewHolder.itemView;
        GridSLM.LayoutParams from = GridSLM.LayoutParams.from(view.getLayoutParams());
        from.setSlm(LinearSLM.ID);
        if (i >= this.mItems.size() || !this.mItems.get(i).isHeader()) {
            i2 = i;
            while (true) {
                if (i2 > -1) {
                    if (i2 < this.mItems.size() && this.mItems.get(i2).isHeader()) {
                        break;
                    }
                    i2--;
                } else {
                    i2 = 0;
                    break;
                }
            }
        } else {
            i2 = i;
        }
        from.setFirstPosition(i2);
        view.setLayoutParams(from);
        if (viewHolder instanceof ConsultHeaderViewHolder) {
            ((ConsultHeaderViewHolder) viewHolder).bindPost(this.mItems.get(i), i, this);
        } else if (viewHolder instanceof ConsultSponsoredPostViewHolder) {
            from.setFirstPosition(i);
            ((ConsultSponsoredPostViewHolder) viewHolder).bindPost(this.mItems.get(i), this);
        } else if (viewHolder instanceof ConsultTimelinePostBodyViewHolder) {
            ((ConsultTimelinePostBodyViewHolder) viewHolder).bindPost(this.mItems.get(i), i, this);
        } else if (viewHolder instanceof ConsultLoadMoreViewHolder) {
            ((ConsultLoadMoreViewHolder) viewHolder).bindText(this.mContext.getString(R.string.consult_more_posts));
        } else if (viewHolder instanceof MedicalStudentsPostNotificationViewHolder) {
            MedicalStudentsPostNotificationViewHolder medicalStudentsPostNotificationViewHolder = (MedicalStudentsPostNotificationViewHolder) viewHolder;
            medicalStudentsPostNotificationViewHolder.bindViews(this.showNotificationHint);
            if (this.showNotificationHint) {
                removeMedStudentNotification();
            }
            medicalStudentsPostNotificationViewHolder.showInFeed.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    ConsultTimelineListAdapter.this.saveMedStudentFlags(true);
                    Settings.singleton(ConsultTimelineListAdapter.this.mContext).saveSetting(ConsultConstants.MED_STUDENTS_NOTIFICATION_HINT, true);
                    boolean unused = ConsultTimelineListAdapter.this.showNotificationHint = true;
                    if (ConsultTimelineListAdapter.this.mListener != null) {
                        ConsultTimelineListAdapter.this.mListener.onShowInFeedClicked();
                    }
                    ConsultTimelineListAdapter.this.notifyItemChanged(0);
                }
            });
            medicalStudentsPostNotificationViewHolder.notNow.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    ConsultTimelineListAdapter.this.saveMedStudentFlags(false);
                    Settings.singleton(ConsultTimelineListAdapter.this.mContext).saveSetting(ConsultConstants.MED_STUDENTS_NOTIFICATION_HINT, true);
                    boolean unused = ConsultTimelineListAdapter.this.showNotificationHint = true;
                    if (ConsultTimelineListAdapter.this.mListener != null) {
                        ConsultTimelineListAdapter.this.mListener.onHideClicked();
                    }
                    ConsultTimelineListAdapter.this.notifyItemChanged(0);
                }
            });
        }
    }

    public int getItemCount() {
        if (this.mFeed == null || this.mActualItems.size() >= this.mFeed.getTotalItems() || this.mHideSpinnerDueToError) {
            return this.mItems.size();
        }
        return this.mItems.size() + 1;
    }

    public int getItemViewType(int i) {
        if (i == this.mItems.size()) {
            return 4;
        }
        if (StringUtil.isNotEmpty(this.mItems.get(i).getPostId()) && this.mItems.get(i).getPostId().equalsIgnoreCase(Constants.CONSULT_LIST_MORE_ERROR)) {
            return 3;
        }
        if (this.mItems.get(i).isShowSponsored()) {
            return 6;
        }
        if (this.mItems.get(i).isHeader()) {
            return 1;
        }
        return (!StringUtil.isNotEmpty(this.mItems.get(i).getPostId()) || !this.mItems.get(i).getPostId().equalsIgnoreCase(ConsultConstants.MED_STUDENTS_POSTS_NOTIFICATION_ID)) ? 2 : 5;
    }

    public void updateListWithProfileItems(String str) {
        if (StringUtil.isNotEmpty(str)) {
            refreshDataForMessage(str);
        }
    }

    private void refreshDataForMessage(String str) {
        if (str.equalsIgnoreCase(Constants.CONSULT_LIST_ERROR)) {
            List<ConsultPost> list = this.mItems;
            if (list != null && list.size() > 0) {
                List<ConsultPost> list2 = this.mItems;
                if (!list2.get(list2.size() - 1).getPostId().equals(Constants.CONSULT_LIST_MORE_ERROR)) {
                    this.mHideSpinnerDueToError = true;
                    ConsultPost consultPost = new ConsultPost();
                    consultPost.setPostId(Constants.CONSULT_LIST_MORE_ERROR);
                    this.mItems.add(consultPost);
                    notifyItemChanged(this.mItems.size() - 1);
                }
            }
        } else if (str.equalsIgnoreCase(Constants.CONSULT_LIST_LOADING)) {
            this.mHideSpinnerDueToError = false;
            List<ConsultPost> list3 = this.mItems;
            if (list3 != null && list3.size() > 0) {
                List<ConsultPost> list4 = this.mItems;
                list4.remove(list4.size() - 1);
                notifyItemChanged(this.mItems.size() - 1);
            }
        }
    }

    public void setVisibilityOfPost(int i, boolean z) {
        ConsultPost consultPost = this.mItems.get(i);
        if (consultPost != null) {
            consultPost.setIsLowQualityPostShown(z);
        }
        ConsultPost consultPost2 = this.mItems.get(i + 1);
        if (consultPost2 != null) {
            consultPost2.setIsLowQualityPostShown(z);
            notifyItemRangeChanged(i, 3);
        }
    }

    private void addMedStudentNotificationItem(List<ConsultPost> list) {
        if (showStudentNotification()) {
            ConsultPost consultPost = new ConsultPost();
            consultPost.setPostId(ConsultConstants.MED_STUDENTS_POSTS_NOTIFICATION_ID);
            if (list != null) {
                list.add(0, consultPost);
            } else {
                new ArrayList().add(consultPost);
            }
        }
    }

    private boolean showStudentNotification() {
        int i;
        String setting;
        if (UserProfileProvider.INSTANCE.getUserProfile(this.mContext).getProfessionId().equals(UserProfile.MEDICAL_LABORATORY_ID) || (i = this.feedType) == 3006 || i == 3002 || i == 3001 || i == 3000) {
            return false;
        }
        if (!this.showNotificationHint && (setting = Settings.singleton(this.mContext).getSetting((String) ConsultConstants.MED_STUDENTS_FILTER_NOTIFICATION, (String) AppEventsConstants.EVENT_PARAM_VALUE_NO)) != null && setting.equals(AppEventsConstants.EVENT_PARAM_VALUE_YES)) {
            return false;
        }
        return true;
    }

    private void removeMedStudentNotification() {
        new Handler().postDelayed(new Runnable() {
            public void run() {
                if (ConsultTimelineListAdapter.this.mItems != null && ConsultTimelineListAdapter.this.mItems.size() > 0 && ConsultTimelineListAdapter.this.mItems.get(0).getPostId() != null && ConsultTimelineListAdapter.this.mItems.get(0).getPostId().equals(ConsultConstants.MED_STUDENTS_POSTS_NOTIFICATION_ID)) {
                    ConsultTimelineListAdapter.this.mItems.remove(0);
                    Settings.singleton(ConsultTimelineListAdapter.this.mContext).saveSetting(ConsultConstants.MED_STUDENTS_NOTIFICATION_HINT, false);
                    boolean unused = ConsultTimelineListAdapter.this.showNotificationHint = false;
                    ConsultTimelineListAdapter.this.notifyDataSetChanged();
                }
            }
        }, 7000);
    }

    /* access modifiers changed from: private */
    public void saveMedStudentFlags(boolean z) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(ConsultConstants.MED_STUDENTS_FILTER_NOTIFICATION, AppEventsConstants.EVENT_PARAM_VALUE_YES);
            if (z) {
                jSONObject.put(ConsultConstants.MED_STUDENTS_FILTER_SHOW, AppEventsConstants.EVENT_PARAM_VALUE_YES);
            } else {
                jSONObject.put(ConsultConstants.MED_STUDENTS_FILTER_SHOW, AppEventsConstants.EVENT_PARAM_VALUE_NO);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ConsultMedStudentFlagsApiManager.saveMedStudentFlag(this.mContext, jSONObject);
    }

    public void addSponsoredPost(ConsultPost consultPost) {
        int i;
        List<ConsultPost> list = this.mItems;
        if (list == null || list.size() <= (i = this.sponsoredPostPosition) || i <= 0) {
            this.mSponsoredPost = consultPost;
            return;
        }
        if (!this.mItems.get(i).isFromAD() || !this.mItems.get(this.sponsoredPostPosition).isShowSponsored()) {
            this.mItems.add(this.sponsoredPostPosition, consultPost);
        } else {
            this.mItems.set(this.sponsoredPostPosition, consultPost);
        }
        this.mSponsoredPost = null;
    }

    public void saveSponsoredPost() {
        int i;
        List<ConsultPost> list = this.mItems;
        if (list != null && list.size() > (i = this.sponsoredPostPosition) && i > 0 && this.mItems.get(i).isShowSponsored()) {
            this.mSponsoredPost = this.mItems.get(this.sponsoredPostPosition);
        }
    }

    public void resetSponsoredPost() {
        this.mSponsoredPost = null;
        this.sponsoredPostPosition = -1;
    }
}
