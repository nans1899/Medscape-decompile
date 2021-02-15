package org.mozilla.javascript;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import java.util.ArrayList;
import java.util.List;
import org.mozilla.javascript.ast.AstRoot;
import org.mozilla.javascript.ast.FunctionNode;
import org.mozilla.javascript.ast.Scope;
import org.mozilla.javascript.ast.ScriptNode;

public class NodeTransformer {
    private boolean hasFinally;
    private ObjArray loopEnds;
    private ObjArray loops;

    /* access modifiers changed from: protected */
    public void visitCall(Node node, ScriptNode scriptNode) {
    }

    /* access modifiers changed from: protected */
    public void visitNew(Node node, ScriptNode scriptNode) {
    }

    public final void transform(ScriptNode scriptNode) {
        transformCompilationUnit(scriptNode);
        for (int i = 0; i != scriptNode.getFunctionCount(); i++) {
            transform(scriptNode.getFunctionNode(i));
        }
    }

    private void transformCompilationUnit(ScriptNode scriptNode) {
        this.loops = new ObjArray();
        this.loopEnds = new ObjArray();
        this.hasFinally = false;
        boolean z = scriptNode.getType() != 109 || ((FunctionNode) scriptNode).requiresActivation();
        scriptNode.flattenSymbolTable(!z);
        transformCompilationUnit_r(scriptNode, scriptNode, scriptNode, z, (scriptNode instanceof AstRoot) && ((AstRoot) scriptNode).isInStrictMode());
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v8, resolved type: org.mozilla.javascript.ast.Scope} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v39, resolved type: org.mozilla.javascript.Node} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v24, resolved type: org.mozilla.javascript.ast.Jump} */
    /* JADX WARNING: type inference failed for: r1v40 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:198:0x039c  */
    /* JADX WARNING: Removed duplicated region for block: B:199:0x03a1  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void transformCompilationUnit_r(org.mozilla.javascript.ast.ScriptNode r19, org.mozilla.javascript.Node r20, org.mozilla.javascript.ast.Scope r21, boolean r22, boolean r23) {
        /*
            r18 = this;
            r6 = r18
            r7 = r19
            r8 = r20
            r9 = r21
            r10 = 0
            r0 = r10
        L_0x000a:
            if (r0 != 0) goto L_0x0012
            org.mozilla.javascript.Node r0 = r20.getFirstChild()
            r1 = r10
            goto L_0x001b
        L_0x0012:
            org.mozilla.javascript.Node r1 = r0.getNext()
            r17 = r1
            r1 = r0
            r0 = r17
        L_0x001b:
            if (r0 != 0) goto L_0x001e
            return
        L_0x001e:
            int r2 = r0.getType()
            r3 = 153(0x99, float:2.14E-43)
            r4 = 129(0x81, float:1.81E-43)
            r5 = 158(0x9e, float:2.21E-43)
            r11 = 39
            if (r22 == 0) goto L_0x0087
            r12 = 157(0x9d, float:2.2E-43)
            if (r2 == r4) goto L_0x0036
            r13 = 132(0x84, float:1.85E-43)
            if (r2 == r13) goto L_0x0036
            if (r2 != r12) goto L_0x0087
        L_0x0036:
            boolean r13 = r0 instanceof org.mozilla.javascript.ast.Scope
            if (r13 == 0) goto L_0x0087
            r13 = r0
            org.mozilla.javascript.ast.Scope r13 = (org.mozilla.javascript.ast.Scope) r13
            java.util.Map r14 = r13.getSymbolTable()
            if (r14 == 0) goto L_0x0087
            org.mozilla.javascript.Node r14 = new org.mozilla.javascript.Node
            if (r2 != r12) goto L_0x004a
            r2 = 158(0x9e, float:2.21E-43)
            goto L_0x004c
        L_0x004a:
            r2 = 153(0x99, float:2.14E-43)
        L_0x004c:
            r14.<init>(r2)
            org.mozilla.javascript.Node r2 = new org.mozilla.javascript.Node
            r2.<init>(r3)
            r14.addChildToBack(r2)
            java.util.Map r12 = r13.getSymbolTable()
            java.util.Set r12 = r12.keySet()
            java.util.Iterator r12 = r12.iterator()
        L_0x0063:
            boolean r15 = r12.hasNext()
            if (r15 == 0) goto L_0x0077
            java.lang.Object r15 = r12.next()
            java.lang.String r15 = (java.lang.String) r15
            org.mozilla.javascript.Node r15 = org.mozilla.javascript.Node.newString(r11, r15)
            r2.addChildToBack(r15)
            goto L_0x0063
        L_0x0077:
            r13.setSymbolTable(r10)
            org.mozilla.javascript.Node r2 = replaceCurrent(r8, r1, r0, r14)
            int r12 = r2.getType()
            r14.addChildToBack(r0)
            r0 = r2
            r2 = r12
        L_0x0087:
            r12 = 3
            if (r2 == r12) goto L_0x037c
            r13 = 4
            r14 = 135(0x87, float:1.89E-43)
            r15 = 123(0x7b, float:1.72E-43)
            r16 = 0
            r4 = 81
            if (r2 == r13) goto L_0x02da
            r13 = 7
            if (r2 == r13) goto L_0x027a
            r13 = 49
            r10 = 8
            if (r2 == r10) goto L_0x0201
            r10 = 38
            if (r2 == r10) goto L_0x01fc
            if (r2 == r11) goto L_0x0208
            r10 = 72
            if (r2 == r10) goto L_0x01f4
            if (r2 == r4) goto L_0x01dc
            r10 = 114(0x72, float:1.6E-43)
            if (r2 == r10) goto L_0x01cb
            r10 = 137(0x89, float:1.92E-43)
            if (r2 == r10) goto L_0x01bc
            if (r2 == r5) goto L_0x013e
            switch(r2) {
                case 30: goto L_0x0139;
                case 31: goto L_0x0208;
                case 32: goto L_0x027a;
                default: goto L_0x00b7;
            }
        L_0x00b7:
            switch(r2) {
                case 120: goto L_0x00db;
                case 121: goto L_0x00db;
                case 122: goto L_0x0163;
                case 123: goto L_0x00c2;
                default: goto L_0x00ba;
            }
        L_0x00ba:
            switch(r2) {
                case 130: goto L_0x01cb;
                case 131: goto L_0x037c;
                case 132: goto L_0x01cb;
                default: goto L_0x00bd;
            }
        L_0x00bd:
            switch(r2) {
                case 153: goto L_0x013e;
                case 154: goto L_0x0163;
                case 155: goto L_0x0208;
                default: goto L_0x00c0;
            }
        L_0x00c0:
            goto L_0x0396
        L_0x00c2:
            org.mozilla.javascript.ObjArray r1 = r6.loops
            r1.push(r0)
            org.mozilla.javascript.Node r1 = r0.getNext()
            int r2 = r1.getType()
            if (r2 == r12) goto L_0x00d4
            org.mozilla.javascript.Kit.codeBug()
        L_0x00d4:
            org.mozilla.javascript.ObjArray r2 = r6.loopEnds
            r2.push(r1)
            goto L_0x0396
        L_0x00db:
            r3 = r0
            org.mozilla.javascript.ast.Jump r3 = (org.mozilla.javascript.ast.Jump) r3
            org.mozilla.javascript.ast.Jump r5 = r3.getJumpStatement()
            if (r5 != 0) goto L_0x00e7
            org.mozilla.javascript.Kit.codeBug()
        L_0x00e7:
            org.mozilla.javascript.ObjArray r10 = r6.loops
            int r10 = r10.size()
        L_0x00ed:
            if (r10 == 0) goto L_0x0134
            int r10 = r10 + -1
            org.mozilla.javascript.ObjArray r11 = r6.loops
            java.lang.Object r11 = r11.get(r10)
            org.mozilla.javascript.Node r11 = (org.mozilla.javascript.Node) r11
            if (r11 != r5) goto L_0x0110
            r1 = 120(0x78, float:1.68E-43)
            if (r2 != r1) goto L_0x0104
            org.mozilla.javascript.Node r1 = r5.target
            r3.target = r1
            goto L_0x010a
        L_0x0104:
            org.mozilla.javascript.Node r1 = r5.getContinue()
            r3.target = r1
        L_0x010a:
            r1 = 5
            r3.setType(r1)
            goto L_0x0396
        L_0x0110:
            int r13 = r11.getType()
            if (r13 != r15) goto L_0x0120
            org.mozilla.javascript.Node r11 = new org.mozilla.javascript.Node
            r11.<init>(r12)
            org.mozilla.javascript.Node r1 = addBeforeCurrent(r8, r1, r0, r11)
            goto L_0x00ed
        L_0x0120:
            if (r13 != r4) goto L_0x00ed
            org.mozilla.javascript.ast.Jump r11 = (org.mozilla.javascript.ast.Jump) r11
            org.mozilla.javascript.ast.Jump r13 = new org.mozilla.javascript.ast.Jump
            r13.<init>(r14)
            org.mozilla.javascript.Node r11 = r11.getFinally()
            r13.target = r11
            org.mozilla.javascript.Node r1 = addBeforeCurrent(r8, r1, r0, r13)
            goto L_0x00ed
        L_0x0134:
            java.lang.RuntimeException r0 = org.mozilla.javascript.Kit.codeBug()
            throw r0
        L_0x0139:
            r6.visitNew(r0, r7)
            goto L_0x0396
        L_0x013e:
            org.mozilla.javascript.Node r4 = r0.getFirstChild()
            int r4 = r4.getType()
            if (r4 != r3) goto L_0x0163
            int r2 = r19.getType()
            r3 = 109(0x6d, float:1.53E-43)
            if (r2 != r3) goto L_0x015c
            r2 = r7
            org.mozilla.javascript.ast.FunctionNode r2 = (org.mozilla.javascript.ast.FunctionNode) r2
            boolean r2 = r2.requiresActivation()
            if (r2 == 0) goto L_0x015a
            goto L_0x015c
        L_0x015a:
            r2 = 0
            goto L_0x015d
        L_0x015c:
            r2 = 1
        L_0x015d:
            org.mozilla.javascript.Node r0 = r6.visitLet(r2, r8, r1, r0)
            goto L_0x0396
        L_0x0163:
            org.mozilla.javascript.Node r3 = new org.mozilla.javascript.Node
            r4 = 129(0x81, float:1.81E-43)
            r3.<init>(r4)
            org.mozilla.javascript.Node r4 = r0.getFirstChild()
        L_0x016e:
            if (r4 == 0) goto L_0x01b6
            org.mozilla.javascript.Node r10 = r4.getNext()
            int r12 = r4.getType()
            if (r12 != r11) goto L_0x019b
            boolean r12 = r4.hasChildren()
            if (r12 != 0) goto L_0x0181
            goto L_0x01af
        L_0x0181:
            org.mozilla.javascript.Node r12 = r4.getFirstChild()
            r4.removeChild(r12)
            r4.setType(r13)
            org.mozilla.javascript.Node r14 = new org.mozilla.javascript.Node
            r15 = 154(0x9a, float:2.16E-43)
            if (r2 != r15) goto L_0x0194
            r15 = 155(0x9b, float:2.17E-43)
            goto L_0x0196
        L_0x0194:
            r15 = 8
        L_0x0196:
            r14.<init>((int) r15, (org.mozilla.javascript.Node) r4, (org.mozilla.javascript.Node) r12)
            r4 = r14
            goto L_0x01a1
        L_0x019b:
            int r12 = r4.getType()
            if (r12 != r5) goto L_0x01b1
        L_0x01a1:
            org.mozilla.javascript.Node r12 = new org.mozilla.javascript.Node
            r14 = 133(0x85, float:1.86E-43)
            int r15 = r0.getLineno()
            r12.<init>((int) r14, (org.mozilla.javascript.Node) r4, (int) r15)
            r3.addChildToBack(r12)
        L_0x01af:
            r4 = r10
            goto L_0x016e
        L_0x01b1:
            java.lang.RuntimeException r0 = org.mozilla.javascript.Kit.codeBug()
            throw r0
        L_0x01b6:
            org.mozilla.javascript.Node r0 = replaceCurrent(r8, r1, r0, r3)
            goto L_0x0396
        L_0x01bc:
            java.lang.String r1 = r0.getString()
            org.mozilla.javascript.ast.Scope r1 = r9.getDefiningScope(r1)
            if (r1 == 0) goto L_0x0396
            r0.setScope(r1)
            goto L_0x0396
        L_0x01cb:
            org.mozilla.javascript.ObjArray r1 = r6.loops
            r1.push(r0)
            org.mozilla.javascript.ObjArray r1 = r6.loopEnds
            r2 = r0
            org.mozilla.javascript.ast.Jump r2 = (org.mozilla.javascript.ast.Jump) r2
            org.mozilla.javascript.Node r2 = r2.target
            r1.push(r2)
            goto L_0x0396
        L_0x01dc:
            r1 = r0
            org.mozilla.javascript.ast.Jump r1 = (org.mozilla.javascript.ast.Jump) r1
            org.mozilla.javascript.Node r1 = r1.getFinally()
            if (r1 == 0) goto L_0x0396
            r2 = 1
            r6.hasFinally = r2
            org.mozilla.javascript.ObjArray r2 = r6.loops
            r2.push(r0)
            org.mozilla.javascript.ObjArray r2 = r6.loopEnds
            r2.push(r1)
            goto L_0x0396
        L_0x01f4:
            r1 = r7
            org.mozilla.javascript.ast.FunctionNode r1 = (org.mozilla.javascript.ast.FunctionNode) r1
            r1.addResumptionPoint(r0)
            goto L_0x0396
        L_0x01fc:
            r6.visitCall(r0, r7)
            goto L_0x0396
        L_0x0201:
            if (r23 == 0) goto L_0x0208
            r3 = 73
            r0.setType(r3)
        L_0x0208:
            if (r22 == 0) goto L_0x020c
            goto L_0x0396
        L_0x020c:
            r3 = 31
            if (r2 != r11) goto L_0x0212
            r4 = r0
            goto L_0x0225
        L_0x0212:
            org.mozilla.javascript.Node r4 = r0.getFirstChild()
            int r5 = r4.getType()
            if (r5 == r13) goto L_0x0225
            if (r2 != r3) goto L_0x0220
            goto L_0x0396
        L_0x0220:
            java.lang.RuntimeException r0 = org.mozilla.javascript.Kit.codeBug()
            throw r0
        L_0x0225:
            org.mozilla.javascript.ast.Scope r5 = r4.getScope()
            if (r5 == 0) goto L_0x022d
            goto L_0x0396
        L_0x022d:
            java.lang.String r5 = r4.getString()
            org.mozilla.javascript.ast.Scope r5 = r9.getDefiningScope(r5)
            if (r5 == 0) goto L_0x0396
            r4.setScope(r5)
            if (r2 != r11) goto L_0x0243
            r1 = 55
            r0.setType(r1)
            goto L_0x0396
        L_0x0243:
            r5 = 41
            r10 = 8
            if (r2 == r10) goto L_0x0270
            r10 = 73
            if (r2 != r10) goto L_0x024e
            goto L_0x0270
        L_0x024e:
            r10 = 155(0x9b, float:2.17E-43)
            if (r2 != r10) goto L_0x025c
            r1 = 156(0x9c, float:2.19E-43)
            r0.setType(r1)
            r4.setType(r5)
            goto L_0x0396
        L_0x025c:
            if (r2 != r3) goto L_0x026b
            org.mozilla.javascript.Node r2 = new org.mozilla.javascript.Node
            r3 = 44
            r2.<init>(r3)
            org.mozilla.javascript.Node r0 = replaceCurrent(r8, r1, r0, r2)
            goto L_0x0396
        L_0x026b:
            java.lang.RuntimeException r0 = org.mozilla.javascript.Kit.codeBug()
            throw r0
        L_0x0270:
            r1 = 56
            r0.setType(r1)
            r4.setType(r5)
            goto L_0x0396
        L_0x027a:
            org.mozilla.javascript.Node r1 = r0.getFirstChild()
            r3 = 7
            if (r2 != r3) goto L_0x02cb
        L_0x0281:
            int r2 = r1.getType()
            r3 = 26
            if (r2 != r3) goto L_0x028e
            org.mozilla.javascript.Node r1 = r1.getFirstChild()
            goto L_0x0281
        L_0x028e:
            int r2 = r1.getType()
            r3 = 12
            if (r2 == r3) goto L_0x029e
            int r2 = r1.getType()
            r3 = 13
            if (r2 != r3) goto L_0x02cb
        L_0x029e:
            org.mozilla.javascript.Node r2 = r1.getFirstChild()
            org.mozilla.javascript.Node r3 = r1.getLastChild()
            int r4 = r2.getType()
            java.lang.String r5 = "undefined"
            if (r4 != r11) goto L_0x02ba
            java.lang.String r4 = r2.getString()
            boolean r4 = r4.equals(r5)
            if (r4 == 0) goto L_0x02ba
            r1 = r3
            goto L_0x02cb
        L_0x02ba:
            int r4 = r3.getType()
            if (r4 != r11) goto L_0x02cb
            java.lang.String r3 = r3.getString()
            boolean r3 = r3.equals(r5)
            if (r3 == 0) goto L_0x02cb
            r1 = r2
        L_0x02cb:
            int r2 = r1.getType()
            r3 = 33
            if (r2 != r3) goto L_0x0396
            r2 = 34
            r1.setType(r2)
            goto L_0x0396
        L_0x02da:
            int r2 = r19.getType()
            r3 = 109(0x6d, float:1.53E-43)
            if (r2 != r3) goto L_0x02ed
            r2 = r7
            org.mozilla.javascript.ast.FunctionNode r2 = (org.mozilla.javascript.ast.FunctionNode) r2
            boolean r2 = r2.isGenerator()
            if (r2 == 0) goto L_0x02ed
            r16 = 1
        L_0x02ed:
            if (r16 == 0) goto L_0x02f6
            r2 = 20
            r3 = 1
            r0.putIntProp(r2, r3)
            goto L_0x02f7
        L_0x02f6:
            r3 = 1
        L_0x02f7:
            boolean r2 = r6.hasFinally
            if (r2 != 0) goto L_0x02fd
            goto L_0x0396
        L_0x02fd:
            org.mozilla.javascript.ObjArray r2 = r6.loops
            int r2 = r2.size()
            int r2 = r2 - r3
            r3 = 0
        L_0x0305:
            if (r2 < 0) goto L_0x0346
            org.mozilla.javascript.ObjArray r5 = r6.loops
            java.lang.Object r5 = r5.get(r2)
            org.mozilla.javascript.Node r5 = (org.mozilla.javascript.Node) r5
            int r10 = r5.getType()
            if (r10 == r4) goto L_0x031b
            if (r10 != r15) goto L_0x0318
            goto L_0x031b
        L_0x0318:
            r11 = 129(0x81, float:1.81E-43)
            goto L_0x0343
        L_0x031b:
            if (r10 != r4) goto L_0x032b
            org.mozilla.javascript.ast.Jump r10 = new org.mozilla.javascript.ast.Jump
            r10.<init>(r14)
            org.mozilla.javascript.ast.Jump r5 = (org.mozilla.javascript.ast.Jump) r5
            org.mozilla.javascript.Node r5 = r5.getFinally()
            r10.target = r5
            goto L_0x0330
        L_0x032b:
            org.mozilla.javascript.Node r10 = new org.mozilla.javascript.Node
            r10.<init>(r12)
        L_0x0330:
            if (r3 != 0) goto L_0x033e
            org.mozilla.javascript.Node r3 = new org.mozilla.javascript.Node
            int r5 = r0.getLineno()
            r11 = 129(0x81, float:1.81E-43)
            r3.<init>((int) r11, (int) r5)
            goto L_0x0340
        L_0x033e:
            r11 = 129(0x81, float:1.81E-43)
        L_0x0340:
            r3.addChildToBack(r10)
        L_0x0343:
            int r2 = r2 + -1
            goto L_0x0305
        L_0x0346:
            if (r3 == 0) goto L_0x0396
            org.mozilla.javascript.Node r2 = r0.getFirstChild()
            org.mozilla.javascript.Node r10 = replaceCurrent(r8, r1, r0, r3)
            if (r2 == 0) goto L_0x0378
            if (r16 == 0) goto L_0x0355
            goto L_0x0378
        L_0x0355:
            org.mozilla.javascript.Node r4 = new org.mozilla.javascript.Node
            r0 = 134(0x86, float:1.88E-43)
            r4.<init>((int) r0, (org.mozilla.javascript.Node) r2)
            r3.addChildToFront(r4)
            org.mozilla.javascript.Node r0 = new org.mozilla.javascript.Node
            r1 = 64
            r0.<init>(r1)
            r3.addChildToBack(r0)
            r0 = r18
            r1 = r19
            r2 = r4
            r3 = r21
            r4 = r22
            r5 = r23
            r0.transformCompilationUnit_r(r1, r2, r3, r4, r5)
            goto L_0x03ae
        L_0x0378:
            r3.addChildToBack(r0)
            goto L_0x03ae
        L_0x037c:
            org.mozilla.javascript.ObjArray r1 = r6.loopEnds
            boolean r1 = r1.isEmpty()
            if (r1 != 0) goto L_0x0396
            org.mozilla.javascript.ObjArray r1 = r6.loopEnds
            java.lang.Object r1 = r1.peek()
            if (r1 != r0) goto L_0x0396
            org.mozilla.javascript.ObjArray r1 = r6.loopEnds
            r1.pop()
            org.mozilla.javascript.ObjArray r1 = r6.loops
            r1.pop()
        L_0x0396:
            r10 = r0
            boolean r0 = r10 instanceof org.mozilla.javascript.ast.Scope
            if (r0 == 0) goto L_0x03a1
            r0 = r10
            org.mozilla.javascript.ast.Scope r0 = (org.mozilla.javascript.ast.Scope) r0
            r3 = r0
            goto L_0x03a2
        L_0x03a1:
            r3 = r9
        L_0x03a2:
            r0 = r18
            r1 = r19
            r2 = r10
            r4 = r22
            r5 = r23
            r0.transformCompilationUnit_r(r1, r2, r3, r4, r5)
        L_0x03ae:
            r0 = r10
            r10 = 0
            goto L_0x000a
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.NodeTransformer.transformCompilationUnit_r(org.mozilla.javascript.ast.ScriptNode, org.mozilla.javascript.Node, org.mozilla.javascript.ast.Scope, boolean, boolean):void");
    }

    /* access modifiers changed from: protected */
    public Node visitLet(boolean z, Node node, Node node2, Node node3) {
        Node node4;
        Node node5;
        Node node6;
        Node node7;
        Node node8;
        Node node9 = node;
        Node node10 = node2;
        Node node11 = node3;
        Node firstChild = node3.getFirstChild();
        Node next = firstChild.getNext();
        node11.removeChild(firstChild);
        node11.removeChild(next);
        int i = 158;
        boolean z2 = node3.getType() == 158;
        int i2 = 153;
        int i3 = 89;
        if (z) {
            node4 = replaceCurrent(node9, node10, node11, new Node(z2 ? 159 : 129));
            ArrayList arrayList = new ArrayList();
            Node node12 = new Node(66);
            Node firstChild2 = firstChild.getFirstChild();
            while (firstChild2 != null) {
                if (firstChild2.getType() == i) {
                    List list = (List) firstChild2.getProp(22);
                    Node firstChild3 = firstChild2.getFirstChild();
                    if (firstChild3.getType() == i2) {
                        if (z2) {
                            node7 = new Node(i3, firstChild3.getNext(), next);
                        } else {
                            node7 = new Node(129, new Node(133, firstChild3.getNext()), next);
                        }
                        if (list != null) {
                            arrayList.addAll(list);
                            for (int i4 = 0; i4 < list.size(); i4++) {
                                node12.addChildToBack(new Node(126, Node.newNumber(FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE)));
                            }
                        }
                        node8 = firstChild3.getFirstChild();
                    } else {
                        throw Kit.codeBug();
                    }
                } else {
                    node7 = next;
                    node8 = firstChild2;
                }
                if (node8.getType() == 39) {
                    arrayList.add(ScriptRuntime.getIndexObject(node8.getString()));
                    Node firstChild4 = node8.getFirstChild();
                    if (firstChild4 == null) {
                        firstChild4 = new Node(126, Node.newNumber(FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE));
                    }
                    node12.addChildToBack(firstChild4);
                    firstChild2 = firstChild2.getNext();
                    next = node7;
                    i = 158;
                    i2 = 153;
                    i3 = 89;
                } else {
                    throw Kit.codeBug();
                }
            }
            node12.putProp(12, arrayList.toArray());
            node4.addChildToBack(new Node(2, node12));
            node4.addChildToBack(new Node(123, next));
            node4.addChildToBack(new Node(3));
        } else {
            node4 = replaceCurrent(node9, node10, node11, new Node(z2 ? 89 : 129));
            Node node13 = new Node(89);
            Node firstChild5 = firstChild.getFirstChild();
            while (firstChild5 != null) {
                if (firstChild5.getType() == 158) {
                    Node firstChild6 = firstChild5.getFirstChild();
                    if (firstChild6.getType() == 153) {
                        if (z2) {
                            node5 = new Node(89, firstChild6.getNext(), next);
                        } else {
                            node5 = new Node(129, new Node(133, firstChild6.getNext()), next);
                        }
                        Scope.joinScopes((Scope) firstChild5, (Scope) node11);
                        node6 = firstChild6.getFirstChild();
                    } else {
                        throw Kit.codeBug();
                    }
                } else {
                    node5 = next;
                    node6 = firstChild5;
                }
                if (node6.getType() == 39) {
                    Node newString = Node.newString(node6.getString());
                    newString.setScope((Scope) node11);
                    Node firstChild7 = node6.getFirstChild();
                    if (firstChild7 == null) {
                        firstChild7 = new Node(126, Node.newNumber(FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE));
                    }
                    node13.addChildToBack(new Node(56, newString, firstChild7));
                    firstChild5 = firstChild5.getNext();
                    next = node5;
                } else {
                    throw Kit.codeBug();
                }
            }
            if (z2) {
                node4.addChildToBack(node13);
                node11.setType(89);
                node4.addChildToBack(node11);
                node11.addChildToBack(next);
                if (next instanceof Scope) {
                    Scope scope = (Scope) next;
                    Scope parentScope = scope.getParentScope();
                    Scope scope2 = (Scope) node11;
                    scope.setParentScope(scope2);
                    scope2.setParentScope(parentScope);
                }
            } else {
                node4.addChildToBack(new Node(133, node13));
                node11.setType(129);
                node4.addChildToBack(node11);
                node11.addChildrenToBack(next);
                if (next instanceof Scope) {
                    Scope scope3 = (Scope) next;
                    Scope parentScope2 = scope3.getParentScope();
                    Scope scope4 = (Scope) node11;
                    scope3.setParentScope(scope4);
                    scope4.setParentScope(parentScope2);
                }
            }
        }
        return node4;
    }

    private static Node addBeforeCurrent(Node node, Node node2, Node node3, Node node4) {
        if (node2 == null) {
            if (node3 != node.getFirstChild()) {
                Kit.codeBug();
            }
            node.addChildToFront(node4);
        } else {
            if (node3 != node2.getNext()) {
                Kit.codeBug();
            }
            node.addChildAfter(node4, node2);
        }
        return node4;
    }

    private static Node replaceCurrent(Node node, Node node2, Node node3, Node node4) {
        if (node2 == null) {
            if (node3 != node.getFirstChild()) {
                Kit.codeBug();
            }
            node.replaceChild(node3, node4);
        } else if (node2.next == node3) {
            node.replaceChildAfter(node2, node4);
        } else {
            node.replaceChild(node3, node4);
        }
        return node4;
    }
}
