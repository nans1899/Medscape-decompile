package com.medscape.android.consult.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.medscape.android.Constants;
import com.medscape.android.R;
import com.medscape.android.consult.interfaces.IDataSetChangedListener;
import com.medscape.android.consult.interfaces.ILoadMoreListener;
import com.medscape.android.consult.interfaces.IPostDeletedListener;
import com.medscape.android.consult.interfaces.IProfileDataChangedListener;
import com.medscape.android.consult.interfaces.IProfileTabChangedListener;
import com.medscape.android.consult.interfaces.ISetVisibilityOfLowQualityPosts;
import com.medscape.android.consult.models.ConsultFeedItem;
import com.medscape.android.consult.models.ConsultPost;
import com.medscape.android.consult.models.ConsultUser;
import com.medscape.android.consult.viewholders.ConsultHeaderViewHolder;
import com.medscape.android.consult.viewholders.ConsultLoadMoreViewHolder;
import com.medscape.android.consult.viewholders.ConsultProfileTabViewHolder;
import com.medscape.android.consult.viewholders.ConsultProfileViewHolder;
import com.medscape.android.consult.viewholders.ConsultProgressBarViewHolder;
import com.medscape.android.consult.viewholders.ConsultResponsesViewHolder;
import com.medscape.android.consult.viewholders.ConsultSponsoredPostViewHolder;
import com.medscape.android.consult.viewholders.ConsultTextViewHolder;
import com.medscape.android.consult.viewholders.ConsultTimelinePostBodyViewHolder;
import com.medscape.android.util.StringUtil;
import java.util.ArrayList;
import java.util.List;

public class ConsultProfileListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements ISetVisibilityOfLowQualityPosts {
    private static final int DEFAULT_ITEM_MULTIPLIER = 1;
    private static final int ITEM_MULTIPLIER_FOR_POST = 2;
    private static final int VIEW_TYPE_DEFAULT = 7;
    private static final int VIEW_TYPE_PROFILE_HEADER = 0;
    private static final int VIEW_TYPE_PROFILE_HEADER_TAB = 6;
    private static final int VIEW_TYPE_PROFILE_LIST_EMPTY = 1;
    private static final int VIEW_TYPE_PROFILE_LIST_EMPTY_NO_TEXT = 4;
    private static final int VIEW_TYPE_PROFILE_LIST_LOAD_MORE = 5;
    private static final int VIEW_TYPE_RESPONSE = 8;
    private static final int VIEW_TYPE_SPONSORED_POST = 9;
    private static final int VIEW_TYPE_TIMELINE_BODY = 3;
    private static final int VIEW_TYPE_TIMELINE_HEADER = 2;
    private String mBitmapPath = null;
    private Activity mContext;
    /* access modifiers changed from: private */
    public int mCurrentTab = 0;
    /* access modifiers changed from: private */
    public boolean mEnableTabs = true;
    private boolean mFailedToReceiveFullProfile = false;
    private boolean mHaveFullProfile = false;
    private boolean mIsLoadingData = false;
    private boolean mIsMedStudent;
    /* access modifiers changed from: private */
    public int mItemMultiplier = 2;
    /* access modifiers changed from: private */
    public List<Object> mItems = new ArrayList();
    private ILoadMoreListener mLoadMoreListener;
    /* access modifiers changed from: private */
    public final IProfileDataChangedListener mProfileDataChangedListener;
    /* access modifiers changed from: private */
    public IProfileTabChangedListener mProfileTabChangedListener;
    private boolean mShouldUpdateProfileBitmap = false;

    public ConsultProfileListAdapter(Activity activity, ILoadMoreListener iLoadMoreListener, IProfileTabChangedListener iProfileTabChangedListener, IProfileDataChangedListener iProfileDataChangedListener, boolean z) {
        this.mContext = activity;
        this.mIsLoadingData = true;
        this.mLoadMoreListener = iLoadMoreListener;
        this.mProfileTabChangedListener = iProfileTabChangedListener;
        this.mProfileDataChangedListener = iProfileDataChangedListener;
        this.mIsMedStudent = z;
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater from = LayoutInflater.from(viewGroup.getContext());
        if (i == 0) {
            return new ConsultProfileViewHolder(from.inflate(R.layout.consult_profile_header, viewGroup, false), this.mContext, new IDataSetChangedListener() {
                public void updateUserAndPosition(int i, ConsultUser consultUser) {
                    ConsultProfileListAdapter.this.mItems.set(0, consultUser);
                    ConsultProfileListAdapter.this.notifyItemChanged(i);
                    if (i == 0) {
                        ConsultProfileListAdapter.this.mProfileDataChangedListener.onAffiliationChangedListener();
                    }
                }
            });
        }
        if (i == 1) {
            View inflate = from.inflate(R.layout.consult_profile_list_empty, viewGroup, false);
            inflate.findViewById(R.id.empty_list).setVisibility(0);
            return new ConsultTextViewHolder(inflate);
        } else if (i == 2) {
            return new ConsultHeaderViewHolder(from.inflate(R.layout.consult_timeline_header, viewGroup, false), this.mContext);
        } else {
            if (i == 9) {
                return new ConsultSponsoredPostViewHolder(from.inflate(R.layout.consult_sponser_post, viewGroup, false), this.mContext, -1);
            }
            if (i == 3) {
                return new ConsultTimelinePostBodyViewHolder(from.inflate(R.layout.consult_timeline_body, viewGroup, false), this.mContext, new IPostDeletedListener() {
                    public void onPostDeleted(int i) {
                        if (i > 0) {
                            ConsultProfileListAdapter.this.mProfileTabChangedListener.onTabChanged(ConsultProfileListAdapter.this.mCurrentTab);
                        }
                    }
                });
            }
            if (i == 8) {
                return new ConsultResponsesViewHolder(from.inflate(R.layout.consult_response_layout, viewGroup, false), this.mContext);
            }
            if (i == 4) {
                View inflate2 = from.inflate(R.layout.consult_profile_list_empty, viewGroup, false);
                inflate2.findViewById(R.id.empty_list).setVisibility(8);
                return new ConsultTextViewHolder(inflate2);
            } else if (i == 5) {
                return new ConsultLoadMoreViewHolder(from.inflate(R.layout.consult_list_load_more, viewGroup, false), this.mLoadMoreListener);
            } else {
                if (i == 6) {
                    return new ConsultProfileTabViewHolder(from.inflate(R.layout.consult_profile_tab_layout, viewGroup, false), this.mContext, new IProfileTabChangedListener() {
                        public void onTabChanged(int i) {
                            if (ConsultProfileListAdapter.this.mCurrentTab != i) {
                                boolean unused = ConsultProfileListAdapter.this.mEnableTabs = false;
                                int unused2 = ConsultProfileListAdapter.this.mCurrentTab = i;
                                if (i != 0) {
                                    int unused3 = ConsultProfileListAdapter.this.mItemMultiplier = 1;
                                } else {
                                    int unused4 = ConsultProfileListAdapter.this.mItemMultiplier = 2;
                                }
                                ConsultProfileListAdapter.this.mProfileTabChangedListener.onTabChanged(i);
                            }
                        }
                    });
                }
                return new ConsultProgressBarViewHolder(from.inflate(R.layout.consult_list_loading, viewGroup, false));
            }
        }
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof ConsultProfileViewHolder) {
            ConsultProfileViewHolder consultProfileViewHolder = (ConsultProfileViewHolder) viewHolder;
            consultProfileViewHolder.bindPost(this.mItems.get(i), this.mHaveFullProfile, this.mFailedToReceiveFullProfile, this.mIsLoadingData);
            if (this.mShouldUpdateProfileBitmap) {
                this.mShouldUpdateProfileBitmap = false;
                consultProfileViewHolder.updateProfileBitMap(this.mBitmapPath);
                this.mBitmapPath = null;
            }
        } else if (viewHolder instanceof ConsultLoadMoreViewHolder) {
            ((ConsultLoadMoreViewHolder) viewHolder).bindText(getMessageForLoadMore());
        } else if (viewHolder instanceof ConsultProgressBarViewHolder) {
            ((ConsultProgressBarViewHolder) viewHolder).bindProgress(!this.mFailedToReceiveFullProfile);
        } else if (i >= this.mItems.size()) {
        } else {
            if (this.mItems.get(i) instanceof ConsultPost) {
                if (viewHolder instanceof ConsultHeaderViewHolder) {
                    ((ConsultHeaderViewHolder) viewHolder).bindPost((ConsultPost) this.mItems.get(i), i, this);
                } else if (viewHolder instanceof ConsultSponsoredPostViewHolder) {
                    ((ConsultSponsoredPostViewHolder) viewHolder).bindPost((ConsultPost) this.mItems.get(i), this);
                } else if (viewHolder instanceof ConsultTimelinePostBodyViewHolder) {
                    ((ConsultTimelinePostBodyViewHolder) viewHolder).bindPost((ConsultPost) this.mItems.get(i), i, this);
                } else if (viewHolder instanceof ConsultTextViewHolder) {
                    ((ConsultTextViewHolder) viewHolder).bindPost(getMessageForEmptyList());
                } else if (viewHolder instanceof ConsultResponsesViewHolder) {
                    ((ConsultResponsesViewHolder) viewHolder).bindPost((ConsultPost) this.mItems.get(i), this.mIsMedStudent);
                }
            } else if (this.mItems.get(i) instanceof ConsultUser) {
                if (viewHolder instanceof ConsultHeaderViewHolder) {
                    ((ConsultHeaderViewHolder) viewHolder).bindPost((ConsultUser) this.mItems.get(i));
                }
            } else if (!(this.mItems.get(i) instanceof String)) {
            } else {
                if (((String) this.mItems.get(i)).equalsIgnoreCase(Constants.CONSULT_PROFILE_TAB_LAYOUT)) {
                    if (viewHolder instanceof ConsultProfileTabViewHolder) {
                        ((ConsultProfileTabViewHolder) viewHolder).bindPost(this.mItems.get(0), this.mCurrentTab, this.mEnableTabs);
                    }
                } else if (((String) this.mItems.get(i)).equalsIgnoreCase(Constants.CONSULT_LIST_ERROR) && (viewHolder instanceof ConsultTextViewHolder)) {
                    ((ConsultTextViewHolder) viewHolder).bindPost(getMessageForEmptyList());
                }
            }
        }
    }

    private String getMessageForEmptyList() {
        String string = this.mContext.getString(R.string.consult_profile_no_posts);
        int i = this.mCurrentTab;
        if (i == 1) {
            return this.mContext.getString(R.string.consult_profile_no_responses);
        }
        if (i == 2) {
            return this.mContext.getString(R.string.consult_profile_not_following);
        }
        return i == 3 ? this.mContext.getString(R.string.consult_profile_no_followers) : string;
    }

    private String getMessageForLoadMore() {
        String string = this.mContext.getString(R.string.consult_more_posts);
        int i = this.mCurrentTab;
        if (i == 1) {
            return this.mContext.getString(R.string.consult_more_responses);
        }
        if (i == 2) {
            return this.mContext.getString(R.string.consult_more_users);
        }
        return i == 3 ? this.mContext.getString(R.string.consult_more_followers) : string;
    }

    public int getItemCount() {
        List<Object> list = this.mItems;
        if (list == null || list.size() == 0) {
            return 0;
        }
        Object obj = this.mItems.get(0);
        if (obj != null && (obj instanceof ConsultUser)) {
            ConsultUser consultUser = (ConsultUser) obj;
            if (this.mItems.size() == 3 && (this.mItems.get(2) instanceof String)) {
                return this.mItems.size();
            }
            if (this.mItems.size() == 2) {
                return this.mItems.size() + 1;
            }
            if (getItemSize() < (getListItemCount(consultUser) * this.mItemMultiplier) + 2) {
                List<Object> list2 = this.mItems;
                Object obj2 = list2.get(list2.size() - 1);
                if (!(obj2 instanceof String) || ((String) obj2).equalsIgnoreCase(Constants.CONSULT_PROFILE_TAB_LAYOUT)) {
                    return this.mItems.size() + 1;
                }
                return this.mItems.size();
            }
        }
        return this.mItems.size();
    }

    private int getItemSize() {
        if (this.mCurrentTab != 1) {
            return this.mItems.size();
        }
        int i = 0;
        for (Object next : this.mItems) {
            if (next instanceof ConsultPost) {
                ConsultPost consultPost = (ConsultPost) next;
                if (consultPost.getReplies() != null) {
                    i += consultPost.getReplies().size();
                }
            }
        }
        return i + 2;
    }

    private int getListItemCount(ConsultUser consultUser) {
        int i = this.mCurrentTab;
        if (i == 0) {
            return consultUser.getPostCount();
        }
        if (i == 1) {
            return consultUser.getResponsesCount();
        }
        if (i == 2) {
            return consultUser.getFollowingCount();
        }
        if (i == 3) {
            return consultUser.getFollowerCount();
        }
        return 0;
    }

    public int getItemViewType(int i) {
        if (i == 0) {
            return 0;
        }
        if (i == this.mItems.size()) {
            return 7;
        }
        if (this.mItems.get(i) instanceof ConsultPost) {
            return getViewTypeForConsultPost((ConsultPost) this.mItems.get(i));
        }
        if (this.mItems.get(i) instanceof ConsultUser) {
            return getViewTypeForConsultUser((ConsultUser) this.mItems.get(i));
        }
        if (this.mItems.get(i) instanceof String) {
            return getViewTypeForString((String) this.mItems.get(i));
        }
        return 7;
    }

    private int getViewTypeForConsultPost(ConsultPost consultPost) {
        String postId = consultPost.getPostId();
        if (!StringUtil.isNotEmpty(postId)) {
            return 7;
        }
        if (consultPost.isShowSponsored()) {
            return 9;
        }
        if (postId.equalsIgnoreCase(Constants.CONSULT_LIST_EMPTY)) {
            return 1;
        }
        if (postId.equalsIgnoreCase(Constants.CONSULT_POST_HEADER)) {
            return 2;
        }
        return consultPost.isRepliesByUser() ? 8 : 3;
    }

    private int getViewTypeForConsultUser(ConsultUser consultUser) {
        String userId = consultUser.getUserId();
        if (StringUtil.isNotEmpty(userId)) {
            return userId.equalsIgnoreCase(Constants.CONSULT_LIST_EMPTY) ? 1 : 2;
        }
        return 7;
    }

    private int getViewTypeForString(String str) {
        if (str.equalsIgnoreCase(Constants.CONSULT_LIST_ERROR)) {
            return 4;
        }
        if (str.equalsIgnoreCase(Constants.CONSULT_LIST_MORE_ERROR)) {
            return 5;
        }
        return str.equalsIgnoreCase(Constants.CONSULT_PROFILE_TAB_LAYOUT) ? 6 : 7;
    }

    public void disableTabs() {
        this.mEnableTabs = false;
    }

    public void setHaveFullProfile(boolean z) {
        if (z) {
            this.mIsLoadingData = false;
        }
        this.mHaveFullProfile = z;
        this.mFailedToReceiveFullProfile = false;
    }

    public void setFailedToReceiveFullProfile(boolean z) {
        if (z) {
            this.mIsLoadingData = false;
        }
        this.mFailedToReceiveFullProfile = z;
    }

    public void setItems(List<Object> list) {
        this.mItems = list;
        if (this.mHaveFullProfile) {
            list.add(Constants.CONSULT_PROFILE_TAB_LAYOUT);
        }
        notifyDataSetChanged();
    }

    public void updateProfileBitMap(String str) {
        this.mIsLoadingData = true;
        this.mShouldUpdateProfileBitmap = true;
        this.mBitmapPath = str;
        notifyDataSetChanged();
    }

    public void updateTotalItemsForFeedInUserObject(int i) {
        List<Object> list = this.mItems;
        if (list != null && list.size() > 0 && (this.mItems.get(0) instanceof ConsultUser)) {
            ConsultUser consultUser = (ConsultUser) this.mItems.get(0);
            int i2 = this.mCurrentTab;
            if (i2 == 0) {
                consultUser.setPostCount(i);
            } else if (i2 == 1) {
                consultUser.setResponsesCount(i);
            } else if (i2 == 2) {
                consultUser.setFollowingCount(i);
            } else if (i2 == 3) {
                consultUser.setFollowerCount(i);
            }
            this.mItems.set(0, consultUser);
        }
    }

    public void updateListWithProfileItems(List<ConsultFeedItem> list, String str, boolean z) {
        refreshTabs(z);
        if (list == null) {
            if (StringUtil.isNotEmpty(str)) {
                refreshDataForMessage(str);
            }
        } else if (list.size() == 0) {
            showNoResults();
        } else {
            List<Object> list2 = this.mItems;
            if (list2 != null && (list2.get(0) instanceof ConsultUser)) {
                ArrayList arrayList = new ArrayList();
                this.mItems = arrayList;
                arrayList.add((ConsultUser) this.mItems.get(0));
                this.mItems.add(Constants.CONSULT_PROFILE_TAB_LAYOUT);
                setUpItemsFromList(list);
                if (this.mItems.size() <= (this.mItemMultiplier * 10) + 2) {
                    notifyItemRangeChanged(2, this.mItems.size());
                } else {
                    notifyItemRangeChanged(this.mItems.size() - (this.mItemMultiplier * 10), this.mItems.size());
                }
            }
        }
    }

    private void setUpItemsFromList(List<ConsultFeedItem> list) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) instanceof ConsultPost) {
                addItemsForPost((ConsultPost) list.get(i));
            } else if (list.get(i) instanceof ConsultUser) {
                this.mItems.add((ConsultUser) list.get(i));
            }
        }
    }

    private void addItemsForPost(ConsultPost consultPost) {
        if (!(this.mCurrentTab == 1 || consultPost == null || consultPost.isShowSponsored())) {
            ConsultPost consultPost2 = new ConsultPost();
            consultPost2.setPoster(consultPost.getPoster());
            consultPost2.setTimestamp(consultPost.getTimestamp());
            consultPost2.setIsHeader(true);
            consultPost2.setPostId(Constants.CONSULT_POST_HEADER);
            consultPost2.setIsLowQualityPost(consultPost.isLowQualityPost());
            consultPost2.setIsLowQualityPostShown(consultPost.isLowQualityPostShown());
            consultPost2.setDownVoteCount(consultPost.getDownVoteCount());
            this.mItems.add(consultPost2);
        }
        this.mItems.add(consultPost);
    }

    private void showNoResults() {
        List<Object> list = this.mItems;
        if (list != null && list.size() == 2) {
            ConsultPost consultPost = new ConsultPost();
            consultPost.setPostId(Constants.CONSULT_LIST_EMPTY);
            this.mItems.add(consultPost);
            notifyDataSetChanged();
        }
    }

    private void refreshDataForMessage(String str) {
        List<Object> list;
        if (str.equalsIgnoreCase(Constants.CONSULT_LIST_ERROR)) {
            List<Object> list2 = this.mItems;
            if (list2 == null || list2.size() <= 2) {
                List<Object> list3 = this.mItems;
                if (list3 != null && list3.size() == 2) {
                    this.mItems.add(Constants.CONSULT_LIST_ERROR);
                    notifyDataSetChanged();
                    return;
                }
                return;
            }
            this.mItems.add(Constants.CONSULT_LIST_MORE_ERROR);
            notifyItemChanged(this.mItems.size() - 1);
        } else if (str.equalsIgnoreCase(Constants.CONSULT_LIST_LOADING) && (list = this.mItems) != null && list.size() > 2) {
            List<Object> list4 = this.mItems;
            list4.remove(list4.size() - 1);
            notifyItemChanged(this.mItems.size() - 1);
        }
    }

    public void refreshTabs(boolean z) {
        this.mEnableTabs = z;
        notifyItemChanged(1);
    }

    public void setVisibilityOfPost(int i, boolean z) {
        Object obj = this.mItems.get(i);
        if (obj instanceof ConsultPost) {
            ((ConsultPost) obj).setIsLowQualityPostShown(z);
        }
        Object obj2 = this.mItems.get(i + 1);
        if (obj2 instanceof ConsultPost) {
            ((ConsultPost) obj2).setIsLowQualityPostShown(z);
            notifyItemRangeChanged(i, 3);
        }
    }
}
