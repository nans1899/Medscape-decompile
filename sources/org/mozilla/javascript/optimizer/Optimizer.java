package org.mozilla.javascript.optimizer;

import org.mozilla.javascript.Node;
import org.mozilla.javascript.ObjArray;
import org.mozilla.javascript.ast.ScriptNode;

class Optimizer {
    static final int AnyType = 3;
    static final int NoType = 0;
    static final int NumberType = 1;
    private boolean inDirectCallFunction;
    private boolean parameterUsedInNumberContext;
    OptFunctionNode theFunction;

    Optimizer() {
    }

    /* access modifiers changed from: package-private */
    public void optimize(ScriptNode scriptNode) {
        int functionCount = scriptNode.getFunctionCount();
        for (int i = 0; i != functionCount; i++) {
            optimizeFunction(OptFunctionNode.get(scriptNode, i));
        }
    }

    private void optimizeFunction(OptFunctionNode optFunctionNode) {
        if (!optFunctionNode.fnode.requiresActivation()) {
            this.inDirectCallFunction = optFunctionNode.isTargetOfDirectCall();
            this.theFunction = optFunctionNode;
            ObjArray objArray = new ObjArray();
            buildStatementList_r(optFunctionNode.fnode, objArray);
            int size = objArray.size();
            Node[] nodeArr = new Node[size];
            objArray.toArray(nodeArr);
            Block.runFlowAnalyzes(optFunctionNode, nodeArr);
            if (!optFunctionNode.fnode.requiresActivation()) {
                this.parameterUsedInNumberContext = false;
                for (int i = 0; i < size; i++) {
                    rewriteForNumberVariables(nodeArr[i], 1);
                }
                optFunctionNode.setParameterNumberContext(this.parameterUsedInNumberContext);
            }
        }
    }

    private void markDCPNumberContext(Node node) {
        if (this.inDirectCallFunction && node.getType() == 55) {
            if (this.theFunction.isParameter(this.theFunction.getVarIndex(node))) {
                this.parameterUsedInNumberContext = true;
            }
        }
    }

    private boolean convertParameter(Node node) {
        if (!this.inDirectCallFunction || node.getType() != 55) {
            return false;
        }
        if (!this.theFunction.isParameter(this.theFunction.getVarIndex(node))) {
            return false;
        }
        node.removeProp(8);
        return true;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:78:0x0121, code lost:
        r10 = r9.getFirstChild();
        r0 = r10.getNext();
        r1 = rewriteForNumberVariables(r10, 1);
        r5 = rewriteForNumberVariables(r0, 1);
        markDCPNumberContext(r10);
        markDCPNumberContext(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:0x0137, code lost:
        if (r1 != 1) goto L_0x0154;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:80:0x0139, code lost:
        if (r5 != 1) goto L_0x013f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x013b, code lost:
        r9.putIntProp(8, 0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x013e, code lost:
        return 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:84:0x0143, code lost:
        if (convertParameter(r0) != false) goto L_0x0153;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:85:0x0145, code lost:
        r9.removeChild(r0);
        r9.addChildToBack(new org.mozilla.javascript.Node(150, r0));
        r9.putIntProp(8, 0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:0x0153, code lost:
        return 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:87:0x0154, code lost:
        if (r5 != 1) goto L_0x016b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:89:0x015a, code lost:
        if (convertParameter(r10) != false) goto L_0x016a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:90:0x015c, code lost:
        r9.removeChild(r10);
        r9.addChildToFront(new org.mozilla.javascript.Node(150, r10));
        r9.putIntProp(8, 0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:91:0x016a, code lost:
        return 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:93:0x016f, code lost:
        if (convertParameter(r10) != false) goto L_0x017c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:94:0x0171, code lost:
        r9.removeChild(r10);
        r9.addChildToFront(new org.mozilla.javascript.Node(150, r10));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:96:0x0180, code lost:
        if (convertParameter(r0) != false) goto L_0x018d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:97:0x0182, code lost:
        r9.removeChild(r0);
        r9.addChildToBack(new org.mozilla.javascript.Node(150, r0));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:98:0x018d, code lost:
        r9.putIntProp(8, 0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:99:0x0190, code lost:
        return 1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int rewriteForNumberVariables(org.mozilla.javascript.Node r9, int r10) {
        /*
            r8 = this;
            int r0 = r9.getType()
            r1 = 40
            r2 = 8
            r3 = 0
            r4 = 1
            if (r0 == r1) goto L_0x02a2
            r1 = 133(0x85, float:1.86E-43)
            if (r0 == r1) goto L_0x0294
            r1 = 140(0x8c, float:1.96E-43)
            r5 = 149(0x95, float:2.09E-43)
            if (r0 == r1) goto L_0x024a
            r1 = 55
            if (r0 == r1) goto L_0x0225
            r10 = 56
            r6 = 150(0x96, float:2.1E-43)
            if (r0 == r10) goto L_0x01c6
            r10 = 106(0x6a, float:1.49E-43)
            if (r0 == r10) goto L_0x0191
            r10 = 107(0x6b, float:1.5E-43)
            if (r0 == r10) goto L_0x0191
            switch(r0) {
                case 9: goto L_0x0121;
                case 10: goto L_0x0121;
                case 11: goto L_0x0121;
                default: goto L_0x002b;
            }
        L_0x002b:
            r10 = 2
            switch(r0) {
                case 14: goto L_0x00da;
                case 15: goto L_0x00da;
                case 16: goto L_0x00da;
                case 17: goto L_0x00da;
                case 18: goto L_0x0121;
                case 19: goto L_0x0121;
                default: goto L_0x002f;
            }
        L_0x002f:
            switch(r0) {
                case 21: goto L_0x0099;
                case 22: goto L_0x0121;
                case 23: goto L_0x0121;
                case 24: goto L_0x0121;
                case 25: goto L_0x0121;
                default: goto L_0x0032;
            }
        L_0x0032:
            switch(r0) {
                case 36: goto L_0x006a;
                case 37: goto L_0x024a;
                case 38: goto L_0x003d;
                default: goto L_0x0035;
            }
        L_0x0035:
            org.mozilla.javascript.Node r10 = r9.getFirstChild()
            r8.rewriteAsObjectChildren(r9, r10)
            return r3
        L_0x003d:
            org.mozilla.javascript.Node r10 = r9.getFirstChild()
            org.mozilla.javascript.Node r0 = r10.getFirstChild()
            r8.rewriteAsObjectChildren(r10, r0)
            org.mozilla.javascript.Node r10 = r10.getNext()
            r0 = 9
            java.lang.Object r0 = r9.getProp(r0)
            org.mozilla.javascript.optimizer.OptFunctionNode r0 = (org.mozilla.javascript.optimizer.OptFunctionNode) r0
            if (r0 == 0) goto L_0x0066
        L_0x0056:
            if (r10 == 0) goto L_0x0069
            int r9 = r8.rewriteForNumberVariables(r10, r4)
            if (r9 != r4) goto L_0x0061
            r8.markDCPNumberContext(r10)
        L_0x0061:
            org.mozilla.javascript.Node r10 = r10.getNext()
            goto L_0x0056
        L_0x0066:
            r8.rewriteAsObjectChildren(r9, r10)
        L_0x0069:
            return r3
        L_0x006a:
            org.mozilla.javascript.Node r0 = r9.getFirstChild()
            org.mozilla.javascript.Node r1 = r0.getNext()
            int r6 = r8.rewriteForNumberVariables(r0, r4)
            if (r6 != r4) goto L_0x0089
            boolean r6 = r8.convertParameter(r0)
            if (r6 != 0) goto L_0x0089
            r9.removeChild(r0)
            org.mozilla.javascript.Node r6 = new org.mozilla.javascript.Node
            r6.<init>((int) r5, (org.mozilla.javascript.Node) r0)
            r9.addChildToFront(r6)
        L_0x0089:
            int r0 = r8.rewriteForNumberVariables(r1, r4)
            if (r0 != r4) goto L_0x0098
            boolean r0 = r8.convertParameter(r1)
            if (r0 != 0) goto L_0x0098
            r9.putIntProp(r2, r10)
        L_0x0098:
            return r3
        L_0x0099:
            org.mozilla.javascript.Node r0 = r9.getFirstChild()
            org.mozilla.javascript.Node r1 = r0.getNext()
            int r5 = r8.rewriteForNumberVariables(r0, r4)
            int r6 = r8.rewriteForNumberVariables(r1, r4)
            boolean r0 = r8.convertParameter(r0)
            if (r0 == 0) goto L_0x00bc
            boolean r0 = r8.convertParameter(r1)
            if (r0 == 0) goto L_0x00b6
            return r3
        L_0x00b6:
            if (r6 != r4) goto L_0x00d9
            r9.putIntProp(r2, r10)
            goto L_0x00d9
        L_0x00bc:
            boolean r0 = r8.convertParameter(r1)
            if (r0 == 0) goto L_0x00c8
            if (r5 != r4) goto L_0x00d9
            r9.putIntProp(r2, r4)
            goto L_0x00d9
        L_0x00c8:
            if (r5 != r4) goto L_0x00d4
            if (r6 != r4) goto L_0x00d0
            r9.putIntProp(r2, r3)
            return r4
        L_0x00d0:
            r9.putIntProp(r2, r4)
            goto L_0x00d9
        L_0x00d4:
            if (r6 != r4) goto L_0x00d9
            r9.putIntProp(r2, r10)
        L_0x00d9:
            return r3
        L_0x00da:
            org.mozilla.javascript.Node r0 = r9.getFirstChild()
            org.mozilla.javascript.Node r1 = r0.getNext()
            int r5 = r8.rewriteForNumberVariables(r0, r4)
            int r6 = r8.rewriteForNumberVariables(r1, r4)
            r8.markDCPNumberContext(r0)
            r8.markDCPNumberContext(r1)
            boolean r0 = r8.convertParameter(r0)
            if (r0 == 0) goto L_0x0103
            boolean r0 = r8.convertParameter(r1)
            if (r0 == 0) goto L_0x00fd
            return r3
        L_0x00fd:
            if (r6 != r4) goto L_0x0120
            r9.putIntProp(r2, r10)
            goto L_0x0120
        L_0x0103:
            boolean r0 = r8.convertParameter(r1)
            if (r0 == 0) goto L_0x010f
            if (r5 != r4) goto L_0x0120
            r9.putIntProp(r2, r4)
            goto L_0x0120
        L_0x010f:
            if (r5 != r4) goto L_0x011b
            if (r6 != r4) goto L_0x0117
            r9.putIntProp(r2, r3)
            goto L_0x0120
        L_0x0117:
            r9.putIntProp(r2, r4)
            goto L_0x0120
        L_0x011b:
            if (r6 != r4) goto L_0x0120
            r9.putIntProp(r2, r10)
        L_0x0120:
            return r3
        L_0x0121:
            org.mozilla.javascript.Node r10 = r9.getFirstChild()
            org.mozilla.javascript.Node r0 = r10.getNext()
            int r1 = r8.rewriteForNumberVariables(r10, r4)
            int r5 = r8.rewriteForNumberVariables(r0, r4)
            r8.markDCPNumberContext(r10)
            r8.markDCPNumberContext(r0)
            if (r1 != r4) goto L_0x0154
            if (r5 != r4) goto L_0x013f
            r9.putIntProp(r2, r3)
            return r4
        L_0x013f:
            boolean r10 = r8.convertParameter(r0)
            if (r10 != 0) goto L_0x0153
            r9.removeChild(r0)
            org.mozilla.javascript.Node r10 = new org.mozilla.javascript.Node
            r10.<init>((int) r6, (org.mozilla.javascript.Node) r0)
            r9.addChildToBack(r10)
            r9.putIntProp(r2, r3)
        L_0x0153:
            return r4
        L_0x0154:
            if (r5 != r4) goto L_0x016b
            boolean r0 = r8.convertParameter(r10)
            if (r0 != 0) goto L_0x016a
            r9.removeChild(r10)
            org.mozilla.javascript.Node r0 = new org.mozilla.javascript.Node
            r0.<init>((int) r6, (org.mozilla.javascript.Node) r10)
            r9.addChildToFront(r0)
            r9.putIntProp(r2, r3)
        L_0x016a:
            return r4
        L_0x016b:
            boolean r1 = r8.convertParameter(r10)
            if (r1 != 0) goto L_0x017c
            r9.removeChild(r10)
            org.mozilla.javascript.Node r1 = new org.mozilla.javascript.Node
            r1.<init>((int) r6, (org.mozilla.javascript.Node) r10)
            r9.addChildToFront(r1)
        L_0x017c:
            boolean r10 = r8.convertParameter(r0)
            if (r10 != 0) goto L_0x018d
            r9.removeChild(r0)
            org.mozilla.javascript.Node r10 = new org.mozilla.javascript.Node
            r10.<init>((int) r6, (org.mozilla.javascript.Node) r0)
            r9.addChildToBack(r10)
        L_0x018d:
            r9.putIntProp(r2, r3)
            return r4
        L_0x0191:
            org.mozilla.javascript.Node r10 = r9.getFirstChild()
            int r0 = r10.getType()
            if (r0 != r1) goto L_0x01af
            int r0 = r8.rewriteForNumberVariables(r10, r4)
            if (r0 != r4) goto L_0x01ae
            boolean r0 = r8.convertParameter(r10)
            if (r0 != 0) goto L_0x01ae
            r9.putIntProp(r2, r3)
            r8.markDCPNumberContext(r10)
            return r4
        L_0x01ae:
            return r3
        L_0x01af:
            int r9 = r10.getType()
            r0 = 36
            if (r9 == r0) goto L_0x01c1
            int r9 = r10.getType()
            r0 = 33
            if (r9 != r0) goto L_0x01c0
            goto L_0x01c1
        L_0x01c0:
            return r3
        L_0x01c1:
            int r9 = r8.rewriteForNumberVariables(r10, r4)
            return r9
        L_0x01c6:
            org.mozilla.javascript.Node r10 = r9.getFirstChild()
            org.mozilla.javascript.Node r10 = r10.getNext()
            int r0 = r8.rewriteForNumberVariables(r10, r4)
            org.mozilla.javascript.optimizer.OptFunctionNode r1 = r8.theFunction
            int r1 = r1.getVarIndex(r9)
            boolean r7 = r8.inDirectCallFunction
            if (r7 == 0) goto L_0x01f5
            org.mozilla.javascript.optimizer.OptFunctionNode r7 = r8.theFunction
            boolean r7 = r7.isParameter(r1)
            if (r7 == 0) goto L_0x01f5
            if (r0 != r4) goto L_0x01f4
            boolean r0 = r8.convertParameter(r10)
            if (r0 != 0) goto L_0x01f0
            r9.putIntProp(r2, r3)
            return r4
        L_0x01f0:
            r8.markDCPNumberContext(r10)
            return r3
        L_0x01f4:
            return r0
        L_0x01f5:
            org.mozilla.javascript.optimizer.OptFunctionNode r7 = r8.theFunction
            boolean r1 = r7.isNumberVar(r1)
            if (r1 == 0) goto L_0x0211
            if (r0 == r4) goto L_0x020a
            r9.removeChild(r10)
            org.mozilla.javascript.Node r0 = new org.mozilla.javascript.Node
            r0.<init>((int) r6, (org.mozilla.javascript.Node) r10)
            r9.addChildToBack(r0)
        L_0x020a:
            r9.putIntProp(r2, r3)
            r8.markDCPNumberContext(r10)
            return r4
        L_0x0211:
            if (r0 != r4) goto L_0x0224
            boolean r0 = r8.convertParameter(r10)
            if (r0 != 0) goto L_0x0224
            r9.removeChild(r10)
            org.mozilla.javascript.Node r0 = new org.mozilla.javascript.Node
            r0.<init>((int) r5, (org.mozilla.javascript.Node) r10)
            r9.addChildToBack(r0)
        L_0x0224:
            return r3
        L_0x0225:
            org.mozilla.javascript.optimizer.OptFunctionNode r0 = r8.theFunction
            int r0 = r0.getVarIndex(r9)
            boolean r1 = r8.inDirectCallFunction
            if (r1 == 0) goto L_0x023d
            org.mozilla.javascript.optimizer.OptFunctionNode r1 = r8.theFunction
            boolean r1 = r1.isParameter(r0)
            if (r1 == 0) goto L_0x023d
            if (r10 != r4) goto L_0x023d
            r9.putIntProp(r2, r3)
            return r4
        L_0x023d:
            org.mozilla.javascript.optimizer.OptFunctionNode r10 = r8.theFunction
            boolean r10 = r10.isNumberVar(r0)
            if (r10 == 0) goto L_0x0249
            r9.putIntProp(r2, r3)
            return r4
        L_0x0249:
            return r3
        L_0x024a:
            org.mozilla.javascript.Node r10 = r9.getFirstChild()
            org.mozilla.javascript.Node r0 = r10.getNext()
            org.mozilla.javascript.Node r1 = r0.getNext()
            int r6 = r8.rewriteForNumberVariables(r10, r4)
            if (r6 != r4) goto L_0x026d
            boolean r6 = r8.convertParameter(r10)
            if (r6 != 0) goto L_0x026d
            r9.removeChild(r10)
            org.mozilla.javascript.Node r6 = new org.mozilla.javascript.Node
            r6.<init>((int) r5, (org.mozilla.javascript.Node) r10)
            r9.addChildToFront(r6)
        L_0x026d:
            int r10 = r8.rewriteForNumberVariables(r0, r4)
            if (r10 != r4) goto L_0x027c
            boolean r10 = r8.convertParameter(r0)
            if (r10 != 0) goto L_0x027c
            r9.putIntProp(r2, r4)
        L_0x027c:
            int r10 = r8.rewriteForNumberVariables(r1, r4)
            if (r10 != r4) goto L_0x0293
            boolean r10 = r8.convertParameter(r1)
            if (r10 != 0) goto L_0x0293
            r9.removeChild(r1)
            org.mozilla.javascript.Node r10 = new org.mozilla.javascript.Node
            r10.<init>((int) r5, (org.mozilla.javascript.Node) r1)
            r9.addChildToBack(r10)
        L_0x0293:
            return r3
        L_0x0294:
            org.mozilla.javascript.Node r10 = r9.getFirstChild()
            int r10 = r8.rewriteForNumberVariables(r10, r4)     // Catch:{ all -> 0x02a6 }
            if (r10 != r4) goto L_0x02a1
            r9.putIntProp(r2, r3)
        L_0x02a1:
            return r3
        L_0x02a2:
            r9.putIntProp(r2, r3)
            return r4
        L_0x02a6:
            r9 = move-exception
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.optimizer.Optimizer.rewriteForNumberVariables(org.mozilla.javascript.Node, int):int");
    }

    private void rewriteAsObjectChildren(Node node, Node node2) {
        while (node2 != null) {
            Node next = node2.getNext();
            if (rewriteForNumberVariables(node2, 0) == 1 && !convertParameter(node2)) {
                node.removeChild(node2);
                Node node3 = new Node(149, node2);
                if (next == null) {
                    node.addChildToBack(node3);
                } else {
                    node.addChildBefore(node3, next);
                }
            }
            node2 = next;
        }
    }

    private static void buildStatementList_r(Node node, ObjArray objArray) {
        int type = node.getType();
        if (type == 129 || type == 141 || type == 132 || type == 109) {
            for (Node firstChild = node.getFirstChild(); firstChild != null; firstChild = firstChild.getNext()) {
                buildStatementList_r(firstChild, objArray);
            }
            return;
        }
        objArray.add(node);
    }
}
