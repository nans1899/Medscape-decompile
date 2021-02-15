package com.webmd.medscape.live.explorelivevents.ui.fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\n\b\u0000\u0010\u0002\u0018\u0001*\u00020\u0003H\n¢\u0006\u0002\b\u0004¨\u0006\u0005"}, d2 = {"<anonymous>", "Landroid/os/Bundle;", "Args", "Landroidx/navigation/NavArgs;", "invoke", "androidx/navigation/fragment/FragmentNavArgsLazyKt$navArgs$1"}, k = 3, mv = {1, 4, 0})
/* compiled from: FragmentNavArgsLazy.kt */
public final class LiveEventFragment$$special$$inlined$navArgs$1 extends Lambda implements Function0<Bundle> {
    final /* synthetic */ Fragment $this_navArgs;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public LiveEventFragment$$special$$inlined$navArgs$1(Fragment fragment) {
        super(0);
        this.$this_navArgs = fragment;
    }

    public final Bundle invoke() {
        Bundle arguments = this.$this_navArgs.getArguments();
        if (arguments != null) {
            return arguments;
        }
        throw new IllegalStateException("Fragment " + this.$this_navArgs + " has null arguments");
    }
}
