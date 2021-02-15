package androidx.navigation.dynamicfeatures.fragment.ui;

import android.os.Bundle;
import androidx.navigation.dynamicfeatures.Constants;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Landroid/os/Bundle;", "invoke"}, k = 3, mv = {1, 1, 16})
/* compiled from: AbstractProgressFragment.kt */
final class AbstractProgressFragment$destinationArgs$2 extends Lambda implements Function0<Bundle> {
    final /* synthetic */ AbstractProgressFragment this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    AbstractProgressFragment$destinationArgs$2(AbstractProgressFragment abstractProgressFragment) {
        super(0);
        this.this$0 = abstractProgressFragment;
    }

    public final Bundle invoke() {
        return this.this$0.requireArguments().getBundle(Constants.DESTINATION_ARGS);
    }
}
