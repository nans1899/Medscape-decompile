package org.mozilla.javascript.optimizer;

import java.util.Map;
import org.mozilla.javascript.Kit;
import org.mozilla.javascript.Node;
import org.mozilla.javascript.NodeTransformer;
import org.mozilla.javascript.ObjArray;
import org.mozilla.javascript.ast.ScriptNode;

class OptTransformer extends NodeTransformer {
    private ObjArray directCallTargets;
    private Map<String, OptFunctionNode> possibleDirectCalls;

    OptTransformer(Map<String, OptFunctionNode> map, ObjArray objArray) {
        this.possibleDirectCalls = map;
        this.directCallTargets = objArray;
    }

    /* access modifiers changed from: protected */
    public void visitNew(Node node, ScriptNode scriptNode) {
        detectDirectCall(node, scriptNode);
        super.visitNew(node, scriptNode);
    }

    /* access modifiers changed from: protected */
    public void visitCall(Node node, ScriptNode scriptNode) {
        detectDirectCall(node, scriptNode);
        super.visitCall(node, scriptNode);
    }

    private void detectDirectCall(Node node, ScriptNode scriptNode) {
        OptFunctionNode optFunctionNode;
        if (scriptNode.getType() == 109) {
            Node firstChild = node.getFirstChild();
            int i = 0;
            Node next = firstChild.getNext();
            while (next != null) {
                next = next.getNext();
                i++;
            }
            if (i == 0) {
                OptFunctionNode.get(scriptNode).itsContainsCalls0 = true;
            }
            if (this.possibleDirectCalls != null) {
                String str = null;
                if (firstChild.getType() == 39) {
                    str = firstChild.getString();
                } else if (firstChild.getType() == 33) {
                    str = firstChild.getFirstChild().getNext().getString();
                } else if (firstChild.getType() == 34) {
                    throw Kit.codeBug();
                }
                if (str != null && (optFunctionNode = this.possibleDirectCalls.get(str)) != null && i == optFunctionNode.fnode.getParamCount() && !optFunctionNode.fnode.requiresActivation() && i <= 32) {
                    node.putProp(9, optFunctionNode);
                    if (!optFunctionNode.isTargetOfDirectCall()) {
                        int size = this.directCallTargets.size();
                        this.directCallTargets.add(optFunctionNode);
                        optFunctionNode.setDirectTargetIndex(size);
                    }
                }
            }
        }
    }
}
