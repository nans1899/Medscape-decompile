package com.medscape.android.consult.adapters;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.medscape.android.Constants;
import com.medscape.android.R;
import com.medscape.android.activity.cme.CMEHelper;
import com.medscape.android.consult.interfaces.ICommentReplySelectedListener;
import com.medscape.android.consult.interfaces.IEarnCMEClickedListener;
import com.medscape.android.consult.interfaces.ILoadMoreListener;
import com.medscape.android.consult.interfaces.ILoadMoreRepliesListener;
import com.medscape.android.consult.interfaces.IPostDeletedListener;
import com.medscape.android.consult.interfaces.IPostDetailCommentSelectedListener;
import com.medscape.android.consult.interfaces.IResponseFilterListener;
import com.medscape.android.consult.interfaces.ISetVisibilityOfLowQualityPosts;
import com.medscape.android.consult.managers.ConsultDataManager;
import com.medscape.android.consult.models.BodyUpdates;
import com.medscape.android.consult.models.ConsultComment;
import com.medscape.android.consult.models.ConsultFeed;
import com.medscape.android.consult.models.ConsultFeedItem;
import com.medscape.android.consult.models.ConsultPost;
import com.medscape.android.consult.models.ConsultUser;
import com.medscape.android.consult.postupdates.interfaces.PostUpdateSelectedListener;
import com.medscape.android.consult.postupdates.viewholders.ConsultAddUpdateButtonViewHolder;
import com.medscape.android.consult.postupdates.viewholders.ConsultUpdateViewHolder;
import com.medscape.android.consult.postupdates.views.ConsultPostUpdateActivity;
import com.medscape.android.consult.util.ConsultUtils;
import com.medscape.android.consult.viewholders.ConsultCommentViewHolder;
import com.medscape.android.consult.viewholders.ConsultDummyViewHolder;
import com.medscape.android.consult.viewholders.ConsultEarnCMEViewHolder;
import com.medscape.android.consult.viewholders.ConsultGetRepliesViewHolder;
import com.medscape.android.consult.viewholders.ConsultHeaderViewHolder;
import com.medscape.android.consult.viewholders.ConsultLoadMoreViewHolder;
import com.medscape.android.consult.viewholders.ConsultPostDetailBodyBottomViewHolder;
import com.medscape.android.consult.viewholders.ConsultPostDetailBodyViewHolder;
import com.medscape.android.consult.viewholders.ConsultResponseFilterViewHolder;
import com.medscape.android.consult.viewholders.ConsultSponsoredPostBottomViewHolder;
import com.medscape.android.consult.viewholders.ConsultTextViewHolder;
import com.medscape.android.databinding.ItemConsultPostUpdateBinding;
import com.medscape.android.parser.model.Article;
import com.medscape.android.util.StringUtil;
import com.wbmd.wbmdcommons.logging.Trace;
import java.util.ArrayList;
import java.util.List;

public class ConsultPostDetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements ISetVisibilityOfLowQualityPosts {
    private static final int MAX_INLINE_COMMENT_REPLIES_TO_SHOW = 2;
    private static final String TAG = ConsultPostDetailAdapter.class.getSimpleName();
    private static final int VIEW_TYPE_ADD_UPDATE_BUTTON = 4;
    private static final int VIEW_TYPE_BODY_BOTTOM = 5;
    private static final int VIEW_TYPE_COMMENT_BODY = 8;
    private static final int VIEW_TYPE_COMMENT_HEADER = 9;
    private static final int VIEW_TYPE_DEFAULT = 17;
    private static final int VIEW_TYPE_EARN_CME = 16;
    private static final int VIEW_TYPE_EMPTY = 15;
    private static final int VIEW_TYPE_LOAD_MORE_COMMENTS = 13;
    private static final int VIEW_TYPE_LOAD_MORE_REPLIES = 14;
    private static final int VIEW_TYPE_REPLY_BODY = 11;
    private static final int VIEW_TYPE_REPLY_HEADER = 10;
    private static final int VIEW_TYPE_RESPONSE_FILTER = 7;
    private static final int VIEW_TYPE_RESPONSE_LIST_EMPTY = 12;
    private static final int VIEW_TYPE_SPONSOR_BODY_BOTTOM = 6;
    private static final int VIEW_TYPE_TIMELINE_BODY = 2;
    private static final int VIEW_TYPE_TIMELINE_HEADER = 1;
    private static final int VIEW_TYPE_UPDATE_ITEM = 3;
    private int mApprovedAnswerCount = 0;
    /* access modifiers changed from: private */
    public Activity mContext;
    private ConsultFeed mCurrentFeed;
    /* access modifiers changed from: private */
    public IResponseFilterListener mFilterListener;
    private int mFilterViewPos = 4;
    private List<Object> mItems = new ArrayList();
    private ILoadMoreListener mLoadMoreListener;
    private ILoadMoreRepliesListener mMoreRepliesListener;
    private ConsultPost mPost;
    private IPostDeletedListener mPostDeletedListener;
    private IPostDetailCommentSelectedListener mPostDetailCommentSelectedListener;
    private ICommentReplySelectedListener mReplySelectedListener;
    private boolean mScrollToFilter = false;

    public ConsultPostDetailAdapter(Activity activity, IResponseFilterListener iResponseFilterListener, ILoadMoreListener iLoadMoreListener, ILoadMoreRepliesListener iLoadMoreRepliesListener, ICommentReplySelectedListener iCommentReplySelectedListener, IPostDetailCommentSelectedListener iPostDetailCommentSelectedListener, IPostDeletedListener iPostDeletedListener) {
        this.mContext = activity;
        this.mFilterListener = iResponseFilterListener;
        this.mLoadMoreListener = iLoadMoreListener;
        this.mMoreRepliesListener = iLoadMoreRepliesListener;
        this.mReplySelectedListener = iCommentReplySelectedListener;
        this.mPostDetailCommentSelectedListener = iPostDetailCommentSelectedListener;
        this.mPostDeletedListener = iPostDeletedListener;
    }

    public void setPost(ConsultPost consultPost, boolean z) {
        if (consultPost != null) {
            this.mPost = consultPost;
            this.mScrollToFilter = z;
            this.mItems = new ArrayList();
            if (!z) {
                ConsultUser poster = consultPost.getPoster();
                if (poster != null) {
                    ConsultPost consultPost2 = new ConsultPost();
                    poster.setCanShowSponsoredLabel(true);
                    consultPost2.setPoster(poster);
                    consultPost2.setIsHeader(true);
                    consultPost2.setTimestamp(consultPost.getTimestamp());
                    this.mItems.add(consultPost2);
                }
                this.mItems.add(consultPost);
            }
            if (consultPost.getConsultBodyUpdateList() != null && consultPost.getConsultBodyUpdateList().size() > 0) {
                this.mItems.addAll(consultPost.getConsultBodyUpdateList());
            }
            if (ConsultDataManager.getInstance(this.mContext, (Activity) null).isCurrentUser(consultPost.getPoster())) {
                this.mItems.add("show_add_update_button");
            }
            if (ConsultUtils.isSponsoredUser(this.mPost.getPoster())) {
                this.mItems.add("add_sponsor_body_bottom");
            } else {
                this.mItems.add("add_body_bottom");
            }
            if (consultPost.isCMEEligible()) {
                this.mItems.add("show_cme_row");
            }
            this.mItems.add("response_filter");
            this.mFilterViewPos = this.mItems.size();
            List<ConsultFeedItem> approvedAnswers = consultPost.getApprovedAnswers();
            if (approvedAnswers != null && !approvedAnswers.isEmpty()) {
                addCommentsFromFeed(approvedAnswers);
            }
        }
    }

    public int getCountForPostBeforeCommentsLoaded() {
        return this.mFilterViewPos;
    }

    public void clearResultsForSort(ConsultPost consultPost) {
        setPost(consultPost, false);
        notifyDataSetChanged();
    }

    public void handleLoadCommentsFailure() {
        if (this.mItems.size() == getCountForPostBeforeCommentsLoaded()) {
            this.mItems.add("load_failed");
        } else if (this.mItems.size() > 0) {
            List<Object> list = this.mItems;
            list.remove(list.size() - 1);
        }
        notifyDataSetChanged();
    }

    public void handleLoadRepliesFailure(int i) {
        this.mItems.remove(i);
        ConsultComment commentForReplyThread = getCommentForReplyThread(i - 1);
        if (commentForReplyThread != null) {
            ConsultComment consultComment = new ConsultComment();
            consultComment.setCommentId(commentForReplyThread.getCommentId());
            consultComment.setParentThreadId(commentForReplyThread.getParentThreadId());
            consultComment.setRepliesFeed(commentForReplyThread.getRepliesFeed());
            consultComment.setCommentType(Constants.CONSULT_COMMENT_TYPE_REPLY);
            consultComment.setIsLoadMorePlaceHolder(true);
            this.mItems.add(i, consultComment);
        }
        notifyDataSetChanged();
    }

    public void setComments(ConsultFeed consultFeed) {
        this.mCurrentFeed = consultFeed;
        int i = 0;
        if (consultFeed != null) {
            List<ConsultFeedItem> feedItems = consultFeed.getFeedItems();
            if (feedItems == null || feedItems.isEmpty()) {
                if (this.mScrollToFilter) {
                    setPost(this.mPost, false);
                }
                this.mItems.add(Constants.CONSULT_LIST_EMPTY);
                List<Object> list = this.mItems;
                if (list != null && list.size() > 3) {
                    i = this.mFilterViewPos;
                }
            } else {
                setPost(this.mPost, false);
                int size = this.mItems.size();
                addCommentsFromFeed(feedItems);
                i = size;
            }
        } else {
            this.mItems.add(Constants.CONSULT_LIST_EMPTY);
        }
        notifyItemRangeChanged(i, this.mItems.size() - 1);
    }

    private void setRepliesForComment(ConsultComment consultComment, ConsultFeed consultFeed, int i) {
        List<ConsultFeedItem> feedItems = consultFeed.getFeedItems();
        int size = this.mItems.size();
        int i2 = 0;
        for (ConsultFeedItem next : feedItems) {
            if ((next instanceof ConsultComment) && i2 < i) {
                ConsultComment consultComment2 = (ConsultComment) next;
                ConsultComment consultComment3 = new ConsultComment();
                consultComment3.setIsHeader(true);
                consultComment3.setCommentType(consultComment2.getCommentType());
                consultComment3.setPoster(consultComment2.getPoster());
                consultComment3.setTimestamp(consultComment2.getTimestamp());
                consultComment3.setIsLowQualityPost(consultComment2.isLowQualityPost());
                consultComment3.setIsLowQualityPostShown(consultComment2.isLowQualityPostShown());
                consultComment3.setIsParentHidden(consultComment.isLowQualityPost());
                this.mItems.add(size, consultComment3);
                int i3 = size + 1;
                consultComment2.setIsParentHidden(consultComment.isLowQualityPost());
                this.mItems.add(i3, next);
                size = i3 + 1;
                i2++;
            }
        }
        if (i2 < consultFeed.getTotalItems()) {
            ConsultComment consultComment4 = new ConsultComment();
            consultComment4.setCommentId(consultComment.getCommentId());
            consultComment4.setParentThreadId(consultComment.getParentThreadId());
            consultComment4.setRepliesFeed(consultComment.getRepliesFeed());
            consultComment4.setCommentType(Constants.CONSULT_COMMENT_TYPE_REPLY);
            consultComment4.setIsLoadMorePlaceHolder(true);
            consultComment4.setIsParentHidden(consultComment.isLowQualityPost());
            this.mItems.add(consultComment4);
        }
    }

    public void setRepliesForCommentAtPosition(ConsultComment consultComment, ConsultFeed consultFeed, int i) {
        int removeAllRepliesForCommentUntilPosition = removeAllRepliesForCommentUntilPosition(i);
        for (ConsultFeedItem next : consultFeed.getFeedItems()) {
            if (next instanceof ConsultComment) {
                ConsultComment consultComment2 = (ConsultComment) next;
                ConsultComment consultComment3 = new ConsultComment();
                consultComment3.setIsHeader(true);
                consultComment3.setCommentType(consultComment2.getCommentType());
                consultComment3.setPoster(consultComment2.getPoster());
                consultComment3.setTimestamp(consultComment2.getTimestamp());
                consultComment3.setIsLowQualityPost(consultComment2.isLowQualityPost());
                consultComment3.setIsLowQualityPostShown(consultComment2.isLowQualityPostShown());
                this.mItems.add(removeAllRepliesForCommentUntilPosition, consultComment3);
                int i2 = removeAllRepliesForCommentUntilPosition + 1;
                this.mItems.add(i2, next);
                removeAllRepliesForCommentUntilPosition = i2 + 1;
            }
        }
        if (consultFeed.getFeedItems().size() + consultComment.getRepliesFeed().getFeedItems().size() < consultFeed.getTotalItems()) {
            ConsultComment consultComment4 = new ConsultComment();
            consultComment4.setCommentId(consultComment.getCommentId());
            consultComment4.setRepliesFeed(consultFeed);
            consultComment4.setParentThreadId(consultComment.getParentThreadId());
            consultComment4.setCommentType(Constants.CONSULT_COMMENT_TYPE_REPLY);
            consultComment4.setIsLoadMorePlaceHolder(true);
            this.mItems.add(removeAllRepliesForCommentUntilPosition, consultComment4);
        }
        this.mItems.remove(removeAllRepliesForCommentUntilPosition);
        notifyDataSetChanged();
    }

    private int removeAllRepliesForCommentUntilPosition(int i) {
        while (i > 0) {
            if (this.mItems.get(i) instanceof ConsultComment) {
                if (((ConsultComment) this.mItems.get(i)).getCommentType() == 3030) {
                    break;
                }
                this.mItems.remove(i);
            }
            i--;
        }
        return i + 1;
    }

    public void showLoadingMoreProgressSpinner(int i) {
        if (i > 0) {
            this.mItems.remove(i);
            this.mItems.add(i, "loading");
        } else {
            this.mItems.add("loading");
        }
        notifyDataSetChanged();
    }

    private ConsultComment getCommentForReplyThread(int i) {
        while (i > 2) {
            Object obj = this.mItems.get(i);
            if (obj instanceof ConsultComment) {
                ConsultComment consultComment = (ConsultComment) obj;
                if (consultComment.getCommentType() == 3030) {
                    return consultComment;
                }
            }
            i--;
        }
        return null;
    }

    public boolean shouldUpdateUiWithReply(int i) {
        int i2 = i + 1;
        if (this.mItems.size() > i2) {
            while (i2 < this.mItems.size()) {
                Object obj = this.mItems.get(i2);
                if (obj instanceof ConsultComment) {
                    ConsultComment consultComment = (ConsultComment) obj;
                    if (consultComment.isLoadMorePlaceHolder()) {
                        return false;
                    }
                    if (consultComment.getCommentType() == 3030) {
                        return true;
                    }
                }
                i2++;
            }
        }
        return true;
    }

    private int getLastReplyInThread(int i) {
        String str = TAG;
        Trace.i(str, "Position is " + i);
        int i2 = i + 1;
        boolean z = false;
        if (this.mItems.size() > i2) {
            while (true) {
                if (i2 >= this.mItems.size()) {
                    break;
                }
                Object obj = this.mItems.get(i2);
                if (obj instanceof ConsultComment) {
                    if (((ConsultComment) obj).getCommentType() == 3030) {
                        z = true;
                        break;
                    }
                    i = i2;
                }
                i2++;
            }
        }
        if (!z) {
            Trace.i(TAG, "Position was last item in list");
            i++;
        }
        String str2 = TAG;
        Trace.i(str2, "Returning " + i);
        return i;
    }

    public void appendReplyToEnd(ConsultFeedItem consultFeedItem, int i) {
        incrementCommentCountForPost();
        incrementReplyCountForComment(i);
        if (shouldUpdateUiWithReply(i)) {
            int lastReplyInThread = getLastReplyInThread(i);
            ConsultComment consultComment = (ConsultComment) consultFeedItem;
            ConsultComment consultComment2 = new ConsultComment();
            consultComment2.setIsHeader(true);
            consultComment2.setCommentType(Constants.CONSULT_COMMENT_TYPE_REPLY);
            consultComment2.setPoster(consultComment.getPoster());
            consultComment2.setTimestamp(consultComment.getTimestamp());
            consultComment2.setIsLowQualityPost(consultComment.isLowQualityPost());
            consultComment2.setIsLowQualityPostShown(consultComment.isLowQualityPostShown());
            this.mItems.add(lastReplyInThread, consultComment2);
            this.mItems.add(lastReplyInThread + 1, consultFeedItem);
            notifyDataSetChanged();
        }
    }

    public void appendCommentToEnd(ConsultFeedItem consultFeedItem) {
        incrementCommentCountForPost();
        this.mItems.remove(Constants.CONSULT_LIST_EMPTY);
        ConsultComment consultComment = (ConsultComment) consultFeedItem;
        ConsultComment consultComment2 = new ConsultComment();
        consultComment2.setIsHeader(true);
        consultComment2.setPoster(consultComment.getPoster());
        consultComment2.setTimestamp(consultComment.getTimestamp());
        consultComment2.setIsLowQualityPost(consultComment.isLowQualityPost());
        consultComment2.setIsLowQualityPostShown(consultComment.isLowQualityPostShown());
        this.mItems.add(consultComment2);
        this.mItems.add(consultFeedItem);
        if (consultComment.getReplyCount() > 0) {
            ConsultComment consultComment3 = new ConsultComment();
            consultComment3.setCommentId(consultComment.getCommentId());
            consultComment3.setRepliesFeed(consultComment.getRepliesFeed());
            consultComment3.setParentThreadId(consultComment.getParentThreadId());
            consultComment3.setCommentType(Constants.CONSULT_COMMENT_TYPE_REPLY);
            consultComment3.setIsLoadMorePlaceHolder(true);
            this.mItems.add(consultComment3);
        }
        notifyDataSetChanged();
    }

    public void insertCommentInBeginning(ConsultFeedItem consultFeedItem) {
        incrementCommentCountForPost();
        this.mItems.remove(Constants.CONSULT_LIST_EMPTY);
        ConsultComment consultComment = (ConsultComment) consultFeedItem;
        ConsultComment consultComment2 = new ConsultComment();
        consultComment2.setIsHeader(true);
        consultComment2.setPoster(consultComment.getPoster());
        consultComment2.setTimestamp(consultComment.getTimestamp());
        consultComment2.setIsLowQualityPost(consultComment.isLowQualityPost());
        consultComment2.setIsLowQualityPostShown(consultComment.isLowQualityPostShown());
        if (consultComment.getReplyCount() > 0) {
            ConsultComment consultComment3 = new ConsultComment();
            consultComment3.setCommentId(consultComment.getCommentId());
            consultComment3.setRepliesFeed(consultComment.getRepliesFeed());
            consultComment3.setParentThreadId(consultComment.getParentThreadId());
            consultComment3.setCommentType(Constants.CONSULT_COMMENT_TYPE_REPLY);
            consultComment3.setIsLoadMorePlaceHolder(true);
            this.mItems.add(3, consultComment3);
        }
        this.mItems.add(3, consultFeedItem);
        this.mItems.add(3, consultComment2);
        notifyDataSetChanged();
    }

    private void incrementCommentCountForPost() {
        Object obj = this.mItems.get(1);
        if (obj instanceof ConsultPost) {
            ConsultPost consultPost = (ConsultPost) obj;
            if (!consultPost.isHeader()) {
                consultPost.setCommentCount(consultPost.getCommentCount() + 1);
            }
        }
    }

    private void incrementReplyCountForComment(int i) {
        ConsultComment commentForReplyThread = getCommentForReplyThread(i);
        if (commentForReplyThread != null) {
            commentForReplyThread.setReplyCount(commentForReplyThread.getReplyCount() + 1);
        }
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater from = LayoutInflater.from(viewGroup.getContext());
        if (i == 1) {
            return new ConsultHeaderViewHolder(from.inflate(R.layout.consult_timeline_header, viewGroup, false), this.mContext);
        }
        if (i == 12) {
            return new ConsultTextViewHolder(from.inflate(R.layout.consult_profile_list_empty, viewGroup, false));
        }
        if (i == 15) {
            View inflate = from.inflate(R.layout.consult_profile_list_empty, viewGroup, false);
            inflate.findViewById(R.id.empty_list).setVisibility(4);
            return new ConsultTextViewHolder(inflate);
        } else if (i == 2) {
            return new ConsultPostDetailBodyViewHolder(from.inflate(R.layout.consult_post_details_body, viewGroup, false), this.mContext);
        } else {
            if (i == 7) {
                return new ConsultResponseFilterViewHolder(from.inflate(R.layout.consult_response_filter, viewGroup, false), this.mContext, new IResponseFilterListener() {
                    public void onResponseFilterSelected(int i) {
                        ConsultPostDetailAdapter.this.mFilterListener.onResponseFilterSelected(i);
                    }
                });
            }
            if (i == 9) {
                return new ConsultHeaderViewHolder(from.inflate(R.layout.consult_timeline_header, viewGroup, false), this.mContext);
            }
            if (i == 8) {
                return new ConsultCommentViewHolder(from.inflate(R.layout.consult_comment_body, viewGroup, false), this.mContext, this.mReplySelectedListener);
            }
            if (i == 10) {
                return new ConsultHeaderViewHolder(from.inflate(R.layout.consult_reply_header, viewGroup, false), this.mContext);
            }
            if (i == 11) {
                return new ConsultCommentViewHolder(from.inflate(R.layout.consult_reply_body, viewGroup, false), this.mContext, this.mReplySelectedListener);
            }
            if (i == 14) {
                return new ConsultGetRepliesViewHolder(from.inflate(R.layout.consult_comment_get_replies, viewGroup, false), this.mMoreRepliesListener);
            }
            if (i == 13) {
                return new ConsultLoadMoreViewHolder(from.inflate(R.layout.consult_list_load_more, viewGroup, false), this.mLoadMoreListener);
            }
            if (i == 16) {
                return new ConsultEarnCMEViewHolder(from.inflate(R.layout.consult_list_earn_cme, viewGroup, false), new IEarnCMEClickedListener() {
                    public void onEarnCMEClicked(String str) {
                        Article article = new Article();
                        article.mArticleId = str;
                        CMEHelper.launchCMEArticle(ConsultPostDetailAdapter.this.mContext, article, false, false);
                    }
                });
            }
            if (i == 3) {
                return new ConsultUpdateViewHolder(ItemConsultPostUpdateBinding.inflate(LayoutInflater.from(viewGroup.getContext())), new PostUpdateSelectedListener() {
                    public void onUpdateSelected(ConsultPost consultPost, BodyUpdates bodyUpdates) {
                        ConsultPostDetailAdapter.this.startConsultPostUpdateActivity(consultPost, bodyUpdates, true);
                    }
                });
            }
            if (i == 4) {
                return new ConsultAddUpdateButtonViewHolder(from.inflate(R.layout.item_consult_add_update_button, viewGroup, false), new PostUpdateSelectedListener() {
                    public void onUpdateSelected(ConsultPost consultPost, BodyUpdates bodyUpdates) {
                        ConsultPostDetailAdapter.this.startConsultPostUpdateActivity(consultPost, bodyUpdates, false);
                    }
                });
            }
            if (i == 5) {
                return new ConsultPostDetailBodyBottomViewHolder(from.inflate(R.layout.consult_post_details_body_bottom, viewGroup, false), this.mContext, this.mPostDeletedListener, this.mPostDetailCommentSelectedListener);
            }
            if (i == 6) {
                return new ConsultSponsoredPostBottomViewHolder(from.inflate(R.layout.consult_sponsored_post_bottom_layout, viewGroup, false), this.mContext, this.mPostDetailCommentSelectedListener);
            }
            return new ConsultDummyViewHolder(from.inflate(R.layout.consult_list_loading, viewGroup, false));
        }
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof ConsultHeaderViewHolder) {
            Object obj = this.mItems.get(i);
            if (obj instanceof ConsultPost) {
                ((ConsultHeaderViewHolder) viewHolder).bindPost((ConsultPost) this.mItems.get(i), i, (ISetVisibilityOfLowQualityPosts) null);
            } else if (obj instanceof ConsultComment) {
                ((ConsultHeaderViewHolder) viewHolder).bindComment((ConsultComment) this.mItems.get(i), i, this);
            }
        } else if (viewHolder instanceof ConsultPostDetailBodyViewHolder) {
            ((ConsultPost) this.mItems.get(i)).setIsLowQualityPostShown(true);
            ((ConsultPostDetailBodyViewHolder) viewHolder).bindPost((ConsultPost) this.mItems.get(i), this);
        } else if (viewHolder instanceof ConsultSponsoredPostBottomViewHolder) {
            ((ConsultSponsoredPostBottomViewHolder) viewHolder).bindSponsoredPost(this.mPost);
        } else if (viewHolder instanceof ConsultPostDetailBodyBottomViewHolder) {
            ((ConsultPostDetailBodyBottomViewHolder) viewHolder).bindPost(this.mPost, i);
        } else if (viewHolder instanceof ConsultCommentViewHolder) {
            ((ConsultCommentViewHolder) viewHolder).bindComment((ConsultComment) this.mItems.get(i), getCommentForReplyThread(i), i);
        } else if (viewHolder instanceof ConsultTextViewHolder) {
            if (this.mItems.get(i) instanceof String) {
                ((ConsultTextViewHolder) viewHolder).bindPost(this.mContext.getResources().getString(R.string.consult_no_response));
            }
        } else if (viewHolder instanceof ConsultLoadMoreViewHolder) {
            ((ConsultLoadMoreViewHolder) viewHolder).bindText(this.mContext.getResources().getString(R.string.consult_comments_load_more));
        } else if (viewHolder instanceof ConsultGetRepliesViewHolder) {
            Object obj2 = this.mItems.get(i);
            if (obj2 instanceof ConsultComment) {
                ConsultComment consultComment = (ConsultComment) obj2;
                consultComment.setCommentType(Constants.CONSULT_COMMENT_TYPE_LOAD_MORE_REPLY);
                if (consultComment.getRepliesFeed() == null || consultComment.getRepliesFeed().getFeedItems() == null || consultComment.getRepliesFeed().getFeedItems().size() == 0) {
                    ((ConsultGetRepliesViewHolder) viewHolder).bindText(this.mContext.getResources().getString(R.string.consult_comment_get_replies), consultComment, i);
                } else {
                    ((ConsultGetRepliesViewHolder) viewHolder).bindText(this.mContext.getResources().getString(R.string.consult_replies_load_more), consultComment, i);
                }
            }
        } else if (viewHolder instanceof ConsultEarnCMEViewHolder) {
            if (this.mPost != null) {
                ((ConsultEarnCMEViewHolder) viewHolder).bindData("" + this.mPost.getCMEInfo().getCMECreditCount() + " CME", this.mPost.getCMEInfo().getCMEArticleId());
            }
        } else if (viewHolder instanceof ConsultResponseFilterViewHolder) {
            ((ConsultResponseFilterViewHolder) viewHolder).setCurrentResponseCount(ConsultUtils.getFormattedConsultCount(this.mPost.getCommentCount()));
        } else if (viewHolder instanceof ConsultUpdateViewHolder) {
            BodyUpdates bodyUpdates = (BodyUpdates) this.mItems.get(i);
            bodyUpdates.setCurrentUser(ConsultDataManager.getInstance(this.mContext, (Activity) null).isCurrentUser(this.mPost.getPoster()));
            ((ConsultUpdateViewHolder) viewHolder).onBind(this.mPost, bodyUpdates);
        } else if (viewHolder instanceof ConsultAddUpdateButtonViewHolder) {
            ((ConsultAddUpdateButtonViewHolder) viewHolder).onBind(this.mPost);
        }
    }

    public int getItemCount() {
        List<Object> list = this.mItems;
        if (list == null || list.size() == 0) {
            return 0;
        }
        if (isLoadingResponsesForPost()) {
            return this.mItems.size() + 1;
        }
        return this.mItems.size();
    }

    private boolean isLoadingResponsesForPost() {
        return isLoadingInitialCommentsForPost() || haveMoreCommentsToLoad();
    }

    private boolean isLoadingInitialCommentsForPost() {
        return this.mItems.size() == 1 || this.mItems.size() == getCountForPostBeforeCommentsLoaded();
    }

    private boolean haveMoreCommentsToLoad() {
        ConsultFeed consultFeed = this.mCurrentFeed;
        return consultFeed != null && consultFeed.getFeedItems().size() + this.mApprovedAnswerCount < this.mCurrentFeed.getTotalItems();
    }

    public int getItemViewType(int i) {
        if (i == this.mItems.size()) {
            if (isLoadingInitialCommentsForPost()) {
                return 17;
            }
            return 13;
        } else if (this.mItems.get(i) instanceof ConsultPost) {
            return ((ConsultPost) this.mItems.get(i)).isHeader() ? 1 : 2;
        } else {
            if (this.mItems.get(i) instanceof ConsultComment) {
                ConsultComment consultComment = (ConsultComment) this.mItems.get(i);
                if (!consultComment.isLoadMorePlaceHolder()) {
                    return consultComment.isHeader() ? consultComment.getCommentType() == 3031 ? 10 : 9 : consultComment.getCommentType() == 3031 ? 11 : 8;
                }
                return 14;
            } else if (this.mItems.get(i) instanceof BodyUpdates) {
                return 3;
            } else {
                if (this.mItems.get(i) instanceof String) {
                    String str = (String) this.mItems.get(i);
                    if (str.equalsIgnoreCase("response_filter")) {
                        return 7;
                    }
                    if (str.equalsIgnoreCase("show_cme_row")) {
                        return 16;
                    }
                    if (str.equalsIgnoreCase(Constants.CONSULT_LIST_EMPTY)) {
                        return 12;
                    }
                    if (str.equalsIgnoreCase("load_failed")) {
                        return 15;
                    }
                    if (str.equalsIgnoreCase("show_add_update_button")) {
                        return 4;
                    }
                    if (str.equalsIgnoreCase("add_body_bottom")) {
                        return 5;
                    }
                    if (str.equalsIgnoreCase("add_sponsor_body_bottom")) {
                        return 6;
                    }
                }
                return 17;
            }
        }
    }

    public void addCommentsFromFeed(List<ConsultFeedItem> list) {
        for (ConsultFeedItem next : list) {
            if ((next instanceof ConsultComment) && this.mItems != null) {
                ConsultComment consultComment = (ConsultComment) next;
                ConsultComment consultComment2 = new ConsultComment();
                consultComment2.setIsHeader(true);
                consultComment2.setPoster(consultComment.getPoster());
                consultComment2.setTimestamp(consultComment.getTimestamp());
                consultComment2.setIsLowQualityPost(consultComment.isLowQualityPost());
                consultComment2.setIsLowQualityPostShown(consultComment.isLowQualityPostShown());
                this.mItems.add(consultComment2);
                this.mItems.add(next);
                if (consultComment.getReplyCount() > 0) {
                    this.mApprovedAnswerCount++;
                    if (consultComment.isMedscapeApprovedAnswer() && !StringUtil.isNotEmpty(consultComment.getParentCommentId())) {
                        ConsultComment consultComment3 = new ConsultComment();
                        consultComment3.setCommentId(consultComment.getCommentId());
                        consultComment3.setParentThreadId(consultComment.getParentThreadId());
                        consultComment3.setRepliesFeed(consultComment.getRepliesFeed());
                        consultComment3.setCommentType(Constants.CONSULT_COMMENT_TYPE_REPLY);
                        consultComment3.setIsLoadMorePlaceHolder(true);
                        this.mItems.add(consultComment3);
                    } else if (!consultComment.isMedscapeApprovedAnswer()) {
                        setRepliesForComment(consultComment, consultComment.getRepliesFeed(), 2);
                    }
                }
            }
        }
    }

    public void setVisibilityOfPost(int i, boolean z) {
        if (checkIfCurrentItemIsCommentType(i)) {
            ((ConsultComment) this.mItems.get(i)).setIsLowQualityPostShown(z);
            int i2 = i + 1;
            if (checkIfCurrentItemIsCommentType(i2)) {
                ConsultComment consultComment = (ConsultComment) this.mItems.get(i2);
                consultComment.setIsLowQualityPostShown(z);
                if (consultComment.getCommentType() == 3030) {
                    i2++;
                    if (checkIfCurrentItemIsCommentType(i2)) {
                        Object obj = this.mItems.get(i2);
                        while (true) {
                            ConsultComment consultComment2 = (ConsultComment) obj;
                            if (consultComment2.getCommentType() != 3031 && consultComment2.getCommentType() != 3034) {
                                break;
                            }
                            consultComment2.setIsParentHidden(!z);
                            i2++;
                            if (!checkIfCurrentItemIsCommentType(i2)) {
                                break;
                            }
                            obj = this.mItems.get(i2);
                        }
                    }
                }
            }
            notifyItemRangeChanged(i, (i2 + 2) - i);
        }
    }

    private boolean checkIfCurrentItemIsCommentType(int i) {
        return i < this.mItems.size() && (this.mItems.get(i) instanceof ConsultComment);
    }

    /* access modifiers changed from: private */
    public void startConsultPostUpdateActivity(ConsultPost consultPost, BodyUpdates bodyUpdates, Boolean bool) {
        Intent intent = new Intent(this.mContext, ConsultPostUpdateActivity.class);
        intent.putExtra(Constants.EXTRA_IS_EDITING, bool);
        intent.putExtra(Constants.EXTRA_CONSULT_POST, consultPost);
        intent.putExtra(Constants.EXTRA_CONSULT_POST_BODY, bodyUpdates);
        this.mContext.startActivityForResult(intent, Constants.REQUEST_CODE_POST_UPDATE);
    }
}
