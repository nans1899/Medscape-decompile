package net.bytebuddy.description.modifier;

import net.bytebuddy.description.modifier.ModifierContributor;

public enum Visibility implements ModifierContributor.ForType, ModifierContributor.ForMethod, ModifierContributor.ForField {
    PUBLIC(1),
    PACKAGE_PRIVATE(0),
    PROTECTED(4),
    PRIVATE(2);
    
    private final int mask;

    public int getRange() {
        return 7;
    }

    private Visibility(int i) {
        this.mask = i;
    }

    public int getMask() {
        return this.mask;
    }

    public boolean isDefault() {
        return this == PACKAGE_PRIVATE;
    }

    public boolean isPublic() {
        return (this.mask & 1) != 0;
    }

    public boolean isProtected() {
        return (this.mask & 4) != 0;
    }

    public boolean isPackagePrivate() {
        return !isPublic() && !isPrivate() && !isProtected();
    }

    public boolean isPrivate() {
        return (this.mask & 2) != 0;
    }

    /* renamed from: net.bytebuddy.description.modifier.Visibility$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$net$bytebuddy$description$modifier$Visibility = null;

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        static {
            /*
                net.bytebuddy.description.modifier.Visibility[] r0 = net.bytebuddy.description.modifier.Visibility.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$net$bytebuddy$description$modifier$Visibility = r0
                net.bytebuddy.description.modifier.Visibility r1 = net.bytebuddy.description.modifier.Visibility.PUBLIC     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$net$bytebuddy$description$modifier$Visibility     // Catch:{ NoSuchFieldError -> 0x001d }
                net.bytebuddy.description.modifier.Visibility r1 = net.bytebuddy.description.modifier.Visibility.PROTECTED     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$net$bytebuddy$description$modifier$Visibility     // Catch:{ NoSuchFieldError -> 0x0028 }
                net.bytebuddy.description.modifier.Visibility r1 = net.bytebuddy.description.modifier.Visibility.PACKAGE_PRIVATE     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$net$bytebuddy$description$modifier$Visibility     // Catch:{ NoSuchFieldError -> 0x0033 }
                net.bytebuddy.description.modifier.Visibility r1 = net.bytebuddy.description.modifier.Visibility.PRIVATE     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: net.bytebuddy.description.modifier.Visibility.AnonymousClass1.<clinit>():void");
        }
    }

    public Visibility expandTo(Visibility visibility) {
        int i = AnonymousClass1.$SwitchMap$net$bytebuddy$description$modifier$Visibility[visibility.ordinal()];
        if (i == 1) {
            return PUBLIC;
        }
        if (i == 2) {
            Visibility visibility2 = PUBLIC;
            return this == visibility2 ? visibility2 : visibility;
        } else if (i == 3) {
            return this == PRIVATE ? PACKAGE_PRIVATE : this;
        } else {
            if (i == 4) {
                return this;
            }
            throw new IllegalStateException("Unexpected visibility: " + visibility);
        }
    }
}
