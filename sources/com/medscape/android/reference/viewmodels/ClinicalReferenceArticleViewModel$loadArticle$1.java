package com.medscape.android.reference.viewmodels;

import android.app.Activity;
import com.medscape.android.landingfeed.repository.Status;
import com.medscape.android.reference.ClinicalReferenceContentManager;
import com.medscape.android.reference.interfaces.ContentLoaderCallback;
import com.medscape.android.reference.model.ClinicalReferenceArticle;
import com.medscape.android.reference.model.ClinicalReferenceContent;
import com.medscape.android.reference.task.ParseClinicalReferenceArticleXMLTask;
import com.medscape.android.reference.viewmodels.ClinicalReferenceArticleViewModel;
import com.webmd.wbmdcmepulse.models.CPEvent;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Deferred;
import kotlinx.coroutines.Dispatchers;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 4, 0})
@DebugMetadata(c = "com.medscape.android.reference.viewmodels.ClinicalReferenceArticleViewModel$loadArticle$1", f = "ClinicalReferenceArticleViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: ClinicalReferenceArticleViewModel.kt */
final class ClinicalReferenceArticleViewModel$loadArticle$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Activity $activity;
    int label;
    private CoroutineScope p$;
    final /* synthetic */ ClinicalReferenceArticleViewModel this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ClinicalReferenceArticleViewModel$loadArticle$1(ClinicalReferenceArticleViewModel clinicalReferenceArticleViewModel, Activity activity, Continuation continuation) {
        super(2, continuation);
        this.this$0 = clinicalReferenceArticleViewModel;
        this.$activity = activity;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        Intrinsics.checkNotNullParameter(continuation, CPEvent.ACTIVITY_NAME_COMPLETION);
        ClinicalReferenceArticleViewModel$loadArticle$1 clinicalReferenceArticleViewModel$loadArticle$1 = new ClinicalReferenceArticleViewModel$loadArticle$1(this.this$0, this.$activity, continuation);
        clinicalReferenceArticleViewModel$loadArticle$1.p$ = (CoroutineScope) obj;
        return clinicalReferenceArticleViewModel$loadArticle$1;
    }

    public final Object invoke(Object obj, Object obj2) {
        return ((ClinicalReferenceArticleViewModel$loadArticle$1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H@¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, k = 3, mv = {1, 4, 0})
    @DebugMetadata(c = "com.medscape.android.reference.viewmodels.ClinicalReferenceArticleViewModel$loadArticle$1$1", f = "ClinicalReferenceArticleViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.medscape.android.reference.viewmodels.ClinicalReferenceArticleViewModel$loadArticle$1$1  reason: invalid class name */
    /* compiled from: ClinicalReferenceArticleViewModel.kt */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;
        private CoroutineScope p$;
        final /* synthetic */ ClinicalReferenceArticleViewModel$loadArticle$1 this$0;

        {
            this.this$0 = r1;
        }

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            Intrinsics.checkNotNullParameter(continuation, CPEvent.ACTIVITY_NAME_COMPLETION);
            AnonymousClass1 r0 = new AnonymousClass1(this.this$0, continuation);
            r0.p$ = (CoroutineScope) obj;
            return r0;
        }

        public final Object invoke(Object obj, Object obj2) {
            return ((AnonymousClass1) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                ClinicalReferenceContentManager mClinicalReferenceContentManager = this.this$0.this$0.getMClinicalReferenceContentManager();
                if (mClinicalReferenceContentManager == null) {
                    return null;
                }
                mClinicalReferenceContentManager.fetchArticleContent(this.this$0.this$0.getCrArticle(), new ContentLoaderCallback(this) {
                    final /* synthetic */ AnonymousClass1 this$0;

                    {
                        this.this$0 = r1;
                    }

                    public void handleContentLoadingError(String str) {
                        this.this$0.this$0.this$0.setErrMessage(str);
                        this.this$0.this$0.this$0.setContentErrorLoading(ClinicalReferenceArticleViewModel.ContentError.CONTENT_LOADING_ERROR);
                        this.this$0.this$0.this$0.getNetworkStateStatus().postValue(Status.FAILED);
                    }

                    public void handleContentloaded(ClinicalReferenceContent clinicalReferenceContent) {
                        ClinicalReferenceArticle crArticle = this.this$0.this$0.this$0.getCrArticle();
                        if (crArticle != null) {
                            crArticle.setContent(clinicalReferenceContent);
                        }
                        this.this$0.this$0.this$0.getNetworkStateStatus().postValue(Status.SUCCESS);
                    }

                    public void handleContentNotDownloaded() {
                        this.this$0.this$0.this$0.setContentErrorLoading(ClinicalReferenceArticleViewModel.ContentError.CONTENT_NOT_DOWNLOADED);
                        this.this$0.this$0.this$0.getNetworkStateStatus().postValue(Status.FAILED);
                    }

                    public void handleContentNotAvailable(String str, boolean z) {
                        this.this$0.this$0.this$0.setErrorUrl(str);
                        this.this$0.this$0.this$0.setErrorAlert(Boolean.valueOf(z));
                        this.this$0.this$0.this$0.setContentErrorLoading(ClinicalReferenceArticleViewModel.ContentError.CONTENT_NOT_AVAILABLE);
                        this.this$0.this$0.this$0.getNetworkStateStatus().postValue(Status.FAILED);
                    }

                    public void handleContentDownloaded(String str) {
                        ClinicalReferenceArticle crArticle = this.this$0.this$0.this$0.getCrArticle();
                        if (crArticle != null) {
                            crArticle.setLocalXmlFilePath(str);
                        }
                        ParseClinicalReferenceArticleXMLTask parseClinicalReferenceArticleXMLTask = new ParseClinicalReferenceArticleXMLTask(this.this$0.this$0.$activity, this);
                        String[] strArr = new String[1];
                        ClinicalReferenceArticle crArticle2 = this.this$0.this$0.this$0.getCrArticle();
                        strArr[0] = crArticle2 != null ? crArticle2.getLocalXMLPath() : null;
                        parseClinicalReferenceArticleXMLTask.execute(strArr);
                    }
                });
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            Deferred unused = BuildersKt__Builders_commonKt.async$default(this.p$, Dispatchers.getDefault(), (CoroutineStart) null, new AnonymousClass1(this, (Continuation) null), 2, (Object) null);
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
