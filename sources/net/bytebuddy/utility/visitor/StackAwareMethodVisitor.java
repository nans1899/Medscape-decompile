package net.bytebuddy.utility.visitor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.implementation.bytecode.StackSize;
import net.bytebuddy.jar.asm.Handle;
import net.bytebuddy.jar.asm.Label;
import net.bytebuddy.jar.asm.MethodVisitor;
import net.bytebuddy.jar.asm.Type;
import net.bytebuddy.utility.CompoundList;
import net.bytebuddy.utility.OpenedClassReader;
import org.mozilla.classfile.ByteCode;

public class StackAwareMethodVisitor extends MethodVisitor {
    private static final int[] SIZE_CHANGE = new int[ByteCode.BREAKPOINT];
    private List<StackSize> current = new ArrayList();
    private int freeIndex;
    private final Map<Label, List<StackSize>> sizes = new HashMap();

    static {
        int i = 0;
        while (true) {
            int[] iArr = SIZE_CHANGE;
            if (i < iArr.length) {
                iArr[i] = "EFFFFFFFFGGFFFGGFFFEEFGFGFEEEEEEEEEEEEEEEEEEEEDEDEDDDDDCDCDEEEEEEEEEEEEEEEEEEEEBABABBBBDCFFFGGGEDCDCDCDCDCDCDCDCDCDCEEEEDDDDDDDCDCDCEFEFDDEEFFDEDEEEBDDBBDDDDDDCCCCCCCCEEEDDDCDCDEEEEEEEEEEFEEEEEEDDEEDDEE".charAt(i) - 'E';
                i++;
            } else {
                return;
            }
        }
    }

    public StackAwareMethodVisitor(MethodVisitor methodVisitor, MethodDescription methodDescription) {
        super(OpenedClassReader.ASM_API, methodVisitor);
        this.freeIndex = methodDescription.getStackSize();
    }

    private void adjustStack(int i) {
        adjustStack(i, 0);
    }

    private void adjustStack(int i, int i2) {
        if (i > 2) {
            throw new IllegalStateException("Cannot push multiple values onto the operand stack: " + i);
        } else if (i > 0) {
            int size = this.current.size();
            while (i2 > 0 && size > 0) {
                size--;
                i2 -= this.current.get(size).getSize();
            }
            if (i2 >= 0) {
                this.current.add(size, StackSize.of(i));
                return;
            }
            throw new IllegalStateException("Unexpected offset underflow: " + i2);
        } else if (i2 == 0) {
            while (i < 0) {
                if (!this.current.isEmpty()) {
                    List<StackSize> list = this.current;
                    i += list.remove(list.size() - 1).getSize();
                } else {
                    return;
                }
            }
            if (i == 1) {
                this.current.add(StackSize.SINGLE);
            } else if (i != 0) {
                throw new IllegalStateException("Unexpected remainder on the operand stack: " + i);
            }
        } else {
            throw new IllegalStateException("Cannot specify non-zero offset " + i2 + " for non-incrementing value: " + i);
        }
    }

    public void drainStack() {
        doDrain(this.current);
    }

    public int drainStack(int i, int i2, StackSize stackSize) {
        List<StackSize> list = this.current;
        int size = list.get(list.size() - 1).getSize() - stackSize.getSize();
        if (this.current.size() == 1 && size == 0) {
            return 0;
        }
        super.visitVarInsn(i, this.freeIndex);
        if (size == 1) {
            super.visitInsn(87);
        } else if (size != 0) {
            throw new IllegalStateException("Unexpected remainder on the operand stack: " + size);
        }
        List<StackSize> list2 = this.current;
        doDrain(list2.subList(0, list2.size() - 1));
        super.visitVarInsn(i2, this.freeIndex);
        return this.freeIndex + stackSize.getSize();
    }

    private void doDrain(List<StackSize> list) {
        ListIterator<StackSize> listIterator = list.listIterator(list.size());
        while (listIterator.hasPrevious()) {
            StackSize previous = listIterator.previous();
            int i = AnonymousClass1.$SwitchMap$net$bytebuddy$implementation$bytecode$StackSize[previous.ordinal()];
            if (i == 1) {
                super.visitInsn(87);
            } else if (i == 2) {
                super.visitInsn(88);
            } else {
                throw new IllegalStateException("Unexpected stack size: " + previous);
            }
        }
    }

    /* renamed from: net.bytebuddy.utility.visitor.StackAwareMethodVisitor$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$net$bytebuddy$implementation$bytecode$StackSize;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        static {
            /*
                net.bytebuddy.implementation.bytecode.StackSize[] r0 = net.bytebuddy.implementation.bytecode.StackSize.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$net$bytebuddy$implementation$bytecode$StackSize = r0
                net.bytebuddy.implementation.bytecode.StackSize r1 = net.bytebuddy.implementation.bytecode.StackSize.SINGLE     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$net$bytebuddy$implementation$bytecode$StackSize     // Catch:{ NoSuchFieldError -> 0x001d }
                net.bytebuddy.implementation.bytecode.StackSize r1 = net.bytebuddy.implementation.bytecode.StackSize.DOUBLE     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: net.bytebuddy.utility.visitor.StackAwareMethodVisitor.AnonymousClass1.<clinit>():void");
        }
    }

    public void register(Label label, List<StackSize> list) {
        this.sizes.put(label, list);
    }

    public void visitInsn(int i) {
        if (i == 47 || i == 49) {
            adjustStack(-2);
            adjustStack(2);
        } else {
            if (i != 133) {
                if (i != 144) {
                    if (i != 191) {
                        if (i != 90) {
                            if (i != 91) {
                                if (i != 93) {
                                    if (i != 94) {
                                        switch (i) {
                                            case 135:
                                                break;
                                            case 136:
                                            case 137:
                                                break;
                                            default:
                                                switch (i) {
                                                    case 140:
                                                    case 141:
                                                        break;
                                                    case 142:
                                                        break;
                                                    default:
                                                        switch (i) {
                                                            case 172:
                                                            case 173:
                                                            case 174:
                                                            case 175:
                                                            case 176:
                                                            case 177:
                                                                break;
                                                            default:
                                                                adjustStack(SIZE_CHANGE[i]);
                                                                break;
                                                        }
                                                }
                                        }
                                    }
                                }
                            }
                            int[] iArr = SIZE_CHANGE;
                            adjustStack(iArr[i], iArr[i] + 2);
                        }
                        int[] iArr2 = SIZE_CHANGE;
                        adjustStack(iArr2[i], iArr2[i] + 1);
                    }
                    this.current.clear();
                }
                adjustStack(-2);
                adjustStack(1);
            }
            adjustStack(-1);
            adjustStack(2);
        }
        super.visitInsn(i);
    }

    public void visitIntInsn(int i, int i2) {
        adjustStack(SIZE_CHANGE[i]);
        super.visitIntInsn(i, i2);
    }

    public void visitVarInsn(int i, int i2) {
        if (i != 169) {
            switch (i) {
                case 54:
                case 56:
                case 58:
                    this.freeIndex = Math.max(this.freeIndex, i2 + 1);
                    break;
                case 55:
                case 57:
                    this.freeIndex = Math.max(this.freeIndex, i2 + 2);
                    break;
            }
        } else {
            this.current.clear();
        }
        adjustStack(SIZE_CHANGE[i]);
        super.visitVarInsn(i, i2);
    }

    public void visitTypeInsn(int i, String str) {
        adjustStack(SIZE_CHANGE[i]);
        super.visitTypeInsn(i, str);
    }

    public void visitFieldInsn(int i, String str, String str2, String str3) {
        int size = Type.getType(str3).getSize();
        switch (i) {
            case 178:
                adjustStack(size);
                break;
            case 179:
                adjustStack(-size);
                break;
            case 180:
                adjustStack(-1);
                adjustStack(size);
                break;
            case 181:
                adjustStack((-size) - 1);
                break;
            default:
                throw new IllegalStateException("Unexpected opcode: " + i);
        }
        super.visitFieldInsn(i, str, str2, str3);
    }

    public void visitMethodInsn(int i, String str, String str2, String str3, boolean z) {
        int argumentsAndReturnSizes = Type.getArgumentsAndReturnSizes(str3);
        adjustStack((-(argumentsAndReturnSizes >> 2)) + (i == 184 ? 1 : 0));
        adjustStack(argumentsAndReturnSizes & 3);
        super.visitMethodInsn(i, str, str2, str3, z);
    }

    public void visitInvokeDynamicInsn(String str, String str2, Handle handle, Object... objArr) {
        int argumentsAndReturnSizes = Type.getArgumentsAndReturnSizes(str2);
        adjustStack((-(argumentsAndReturnSizes >> 2)) + 1);
        adjustStack(argumentsAndReturnSizes & 3);
        super.visitInvokeDynamicInsn(str, str2, handle, objArr);
    }

    public void visitLdcInsn(Object obj) {
        adjustStack(((obj instanceof Long) || (obj instanceof Double)) ? 2 : 1);
        super.visitLdcInsn(obj);
    }

    public void visitMultiANewArrayInsn(String str, int i) {
        adjustStack(1 - i);
        super.visitMultiANewArrayInsn(str, i);
    }

    public void visitJumpInsn(int i, Label label) {
        adjustStack(SIZE_CHANGE[i]);
        this.sizes.put(label, new ArrayList(i == 168 ? CompoundList.of(this.current, StackSize.SINGLE) : this.current));
        if (i == 167) {
            this.current.clear();
        }
        super.visitJumpInsn(i, label);
    }

    public void visitLabel(Label label) {
        List list = this.sizes.get(label);
        if (list != null) {
            this.current = new ArrayList(list);
        }
        super.visitLabel(label);
    }

    public void visitLineNumber(int i, Label label) {
        super.visitLineNumber(i, label);
    }

    public void visitTableSwitchInsn(int i, int i2, Label label, Label... labelArr) {
        adjustStack(-1);
        ArrayList arrayList = new ArrayList(this.current);
        this.sizes.put(label, arrayList);
        for (Label put : labelArr) {
            this.sizes.put(put, arrayList);
        }
        super.visitTableSwitchInsn(i, i2, label, labelArr);
    }

    public void visitLookupSwitchInsn(Label label, int[] iArr, Label[] labelArr) {
        adjustStack(-1);
        ArrayList arrayList = new ArrayList(this.current);
        this.sizes.put(label, arrayList);
        for (Label put : labelArr) {
            this.sizes.put(put, arrayList);
        }
        super.visitLookupSwitchInsn(label, iArr, labelArr);
    }

    public void visitTryCatchBlock(Label label, Label label2, Label label3, String str) {
        this.sizes.put(label3, Collections.singletonList(StackSize.SINGLE));
        super.visitTryCatchBlock(label, label2, label3, str);
    }
}
