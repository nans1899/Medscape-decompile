package org.mozilla.javascript.optimizer;

import com.facebook.appevents.AppEventsConstants;
import org.mozilla.javascript.CompilerEnvirons;
import org.mozilla.javascript.IRFactory;
import org.mozilla.javascript.JavaAdapter;
import org.mozilla.javascript.ObjToIntMap;
import org.mozilla.javascript.Parser;
import org.mozilla.javascript.ScriptRuntime;
import org.mozilla.javascript.ast.FunctionNode;
import org.mozilla.javascript.ast.ScriptNode;

public class ClassCompiler {
    private CompilerEnvirons compilerEnv;
    private String mainMethodClassName;
    private Class<?> targetExtends;
    private Class<?>[] targetImplements;

    public ClassCompiler(CompilerEnvirons compilerEnvirons) {
        if (compilerEnvirons != null) {
            this.compilerEnv = compilerEnvirons;
            this.mainMethodClassName = "org.mozilla.javascript.optimizer.OptRuntime";
            return;
        }
        throw new IllegalArgumentException();
    }

    public void setMainMethodClass(String str) {
        this.mainMethodClassName = str;
    }

    public String getMainMethodClass() {
        return this.mainMethodClassName;
    }

    public CompilerEnvirons getCompilerEnv() {
        return this.compilerEnv;
    }

    public Class<?> getTargetExtends() {
        return this.targetExtends;
    }

    public void setTargetExtends(Class<?> cls) {
        this.targetExtends = cls;
    }

    public Class<?>[] getTargetImplements() {
        Class<?>[] clsArr = this.targetImplements;
        if (clsArr == null) {
            return null;
        }
        return (Class[]) clsArr.clone();
    }

    public void setTargetImplements(Class<?>[] clsArr) {
        this.targetImplements = clsArr == null ? null : (Class[]) clsArr.clone();
    }

    /* access modifiers changed from: protected */
    public String makeAuxiliaryClassName(String str, String str2) {
        return str + str2;
    }

    public Object[] compileToClassFiles(String str, String str2, int i, String str3) {
        String str4;
        ScriptNode transformTree = new IRFactory(this.compilerEnv).transformTree(new Parser(this.compilerEnv).parse(str, str2, i));
        Class<?> targetExtends2 = getTargetExtends();
        Class[] targetImplements2 = getTargetImplements();
        boolean z = targetImplements2 == null && targetExtends2 == null;
        if (z) {
            str4 = str3;
        } else {
            str4 = makeAuxiliaryClassName(str3, AppEventsConstants.EVENT_PARAM_VALUE_YES);
        }
        Codegen codegen = new Codegen();
        codegen.setMainMethodClass(this.mainMethodClassName);
        Object compileToClassFile = codegen.compileToClassFile(this.compilerEnv, str4, transformTree, transformTree.getEncodedSource(), false);
        if (z) {
            return new Object[]{str4, compileToClassFile};
        }
        int functionCount = transformTree.getFunctionCount();
        ObjToIntMap objToIntMap = new ObjToIntMap(functionCount);
        for (int i2 = 0; i2 != functionCount; i2++) {
            FunctionNode functionNode = transformTree.getFunctionNode(i2);
            String name = functionNode.getName();
            if (!(name == null || name.length() == 0)) {
                objToIntMap.put(name, functionNode.getParamCount());
            }
        }
        if (targetExtends2 == null) {
            targetExtends2 = ScriptRuntime.ObjectClass;
        }
        return new Object[]{str3, JavaAdapter.createAdapterCode(objToIntMap, str3, targetExtends2, targetImplements2, str4), str4, compileToClassFile};
    }
}
