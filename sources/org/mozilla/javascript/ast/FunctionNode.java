package org.mozilla.javascript.ast;

import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.io.IOUtils;
import org.mozilla.javascript.Node;

public class FunctionNode extends ScriptNode {
    public static final int FUNCTION_EXPRESSION = 2;
    public static final int FUNCTION_EXPRESSION_STATEMENT = 3;
    public static final int FUNCTION_STATEMENT = 1;
    private static final List<AstNode> NO_PARAMS = Collections.unmodifiableList(new ArrayList());
    private AstNode body;
    private Form functionForm = Form.FUNCTION;
    private Name functionName;
    private int functionType;
    private List<Node> generatorResumePoints;
    private boolean isExpressionClosure;
    private boolean isGenerator;
    private Map<Node, int[]> liveLocals;
    private int lp = -1;
    private AstNode memberExprNode;
    private boolean needsActivation;
    private List<AstNode> params;
    private int rp = -1;

    public enum Form {
        FUNCTION,
        GETTER,
        SETTER
    }

    public FunctionNode() {
        this.type = 109;
    }

    public FunctionNode(int i) {
        super(i);
        this.type = 109;
    }

    public FunctionNode(int i, Name name) {
        super(i);
        this.type = 109;
        setFunctionName(name);
    }

    public Name getFunctionName() {
        return this.functionName;
    }

    public void setFunctionName(Name name) {
        this.functionName = name;
        if (name != null) {
            name.setParent(this);
        }
    }

    public String getName() {
        Name name = this.functionName;
        return name != null ? name.getIdentifier() : "";
    }

    public List<AstNode> getParams() {
        List<AstNode> list = this.params;
        return list != null ? list : NO_PARAMS;
    }

    public void setParams(List<AstNode> list) {
        if (list == null) {
            this.params = null;
            return;
        }
        List<AstNode> list2 = this.params;
        if (list2 != null) {
            list2.clear();
        }
        for (AstNode addParam : list) {
            addParam(addParam);
        }
    }

    public void addParam(AstNode astNode) {
        assertNotNull(astNode);
        if (this.params == null) {
            this.params = new ArrayList();
        }
        this.params.add(astNode);
        astNode.setParent(this);
    }

    public boolean isParam(AstNode astNode) {
        List<AstNode> list = this.params;
        if (list == null) {
            return false;
        }
        return list.contains(astNode);
    }

    public AstNode getBody() {
        return this.body;
    }

    public void setBody(AstNode astNode) {
        assertNotNull(astNode);
        this.body = astNode;
        if (Boolean.TRUE.equals(astNode.getProp(25))) {
            setIsExpressionClosure(true);
        }
        int position = astNode.getPosition() + astNode.getLength();
        astNode.setParent(this);
        setLength(position - this.position);
        setEncodedSourceBounds(this.position, position);
    }

    public int getLp() {
        return this.lp;
    }

    public void setLp(int i) {
        this.lp = i;
    }

    public int getRp() {
        return this.rp;
    }

    public void setRp(int i) {
        this.rp = i;
    }

    public void setParens(int i, int i2) {
        this.lp = i;
        this.rp = i2;
    }

    public boolean isExpressionClosure() {
        return this.isExpressionClosure;
    }

    public void setIsExpressionClosure(boolean z) {
        this.isExpressionClosure = z;
    }

    public boolean requiresActivation() {
        return this.needsActivation;
    }

    public void setRequiresActivation() {
        this.needsActivation = true;
    }

    public boolean isGenerator() {
        return this.isGenerator;
    }

    public void setIsGenerator() {
        this.isGenerator = true;
    }

    public void addResumptionPoint(Node node) {
        if (this.generatorResumePoints == null) {
            this.generatorResumePoints = new ArrayList();
        }
        this.generatorResumePoints.add(node);
    }

    public List<Node> getResumptionPoints() {
        return this.generatorResumePoints;
    }

    public Map<Node, int[]> getLiveLocals() {
        return this.liveLocals;
    }

    public void addLiveLocals(Node node, int[] iArr) {
        if (this.liveLocals == null) {
            this.liveLocals = new HashMap();
        }
        this.liveLocals.put(node, iArr);
    }

    public int addFunction(FunctionNode functionNode) {
        int addFunction = super.addFunction(functionNode);
        if (getFunctionCount() > 0) {
            this.needsActivation = true;
        }
        return addFunction;
    }

    public int getFunctionType() {
        return this.functionType;
    }

    public void setFunctionType(int i) {
        this.functionType = i;
    }

    public boolean isGetterOrSetter() {
        return this.functionForm == Form.GETTER || this.functionForm == Form.SETTER;
    }

    public boolean isGetter() {
        return this.functionForm == Form.GETTER;
    }

    public boolean isSetter() {
        return this.functionForm == Form.SETTER;
    }

    public void setFunctionIsGetter() {
        this.functionForm = Form.GETTER;
    }

    public void setFunctionIsSetter() {
        this.functionForm = Form.SETTER;
    }

    public void setMemberExprNode(AstNode astNode) {
        this.memberExprNode = astNode;
        if (astNode != null) {
            astNode.setParent(this);
        }
    }

    public AstNode getMemberExprNode() {
        return this.memberExprNode;
    }

    public String toSource(int i) {
        StringBuilder sb = new StringBuilder();
        sb.append(makeIndent(i));
        sb.append("function");
        if (this.functionName != null) {
            sb.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
            sb.append(this.functionName.toSource(0));
        }
        if (this.params == null) {
            sb.append("() ");
        } else {
            sb.append("(");
            printList(this.params, sb);
            sb.append(") ");
        }
        if (this.isExpressionClosure) {
            AstNode body2 = getBody();
            if (body2.getLastChild() instanceof ReturnStatement) {
                sb.append(((ReturnStatement) body2.getLastChild()).getReturnValue().toSource(0));
                if (this.functionType == 1) {
                    sb.append(";");
                }
            } else {
                sb.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
                sb.append(body2.toSource(0));
            }
        } else {
            sb.append(getBody().toSource(i).trim());
        }
        if (this.functionType == 1) {
            sb.append(IOUtils.LINE_SEPARATOR_UNIX);
        }
        return sb.toString();
    }

    public void visit(NodeVisitor nodeVisitor) {
        AstNode astNode;
        if (nodeVisitor.visit(this)) {
            Name name = this.functionName;
            if (name != null) {
                name.visit(nodeVisitor);
            }
            for (AstNode visit : getParams()) {
                visit.visit(nodeVisitor);
            }
            getBody().visit(nodeVisitor);
            if (!this.isExpressionClosure && (astNode = this.memberExprNode) != null) {
                astNode.visit(nodeVisitor);
            }
        }
    }
}
