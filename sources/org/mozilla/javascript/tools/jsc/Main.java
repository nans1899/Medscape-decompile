package org.mozilla.javascript.tools.jsc;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import org.mozilla.javascript.CompilerEnvirons;
import org.mozilla.javascript.optimizer.ClassCompiler;
import org.mozilla.javascript.tools.SourceReader;
import org.mozilla.javascript.tools.ToolErrorReporter;

public class Main {
    private String characterEncoding;
    private ClassCompiler compiler;
    private CompilerEnvirons compilerEnv;
    private String destinationDir;
    private boolean printHelp;
    private ToolErrorReporter reporter = new ToolErrorReporter(true);
    private String targetName;
    private String targetPackage;

    public static void main(String[] strArr) {
        Main main = new Main();
        String[] processOptions = main.processOptions(strArr);
        if (processOptions == null) {
            if (main.printHelp) {
                System.out.println(ToolErrorReporter.getMessage("msg.jsc.usage", Main.class.getName()));
                System.exit(0);
            }
            System.exit(1);
        }
        if (!main.reporter.hasReportedError()) {
            main.processSource(processOptions);
        }
    }

    public Main() {
        CompilerEnvirons compilerEnvirons = new CompilerEnvirons();
        this.compilerEnv = compilerEnvirons;
        compilerEnvirons.setErrorReporter(this.reporter);
        this.compiler = new ClassCompiler(this.compilerEnv);
    }

    public String[] processOptions(String[] strArr) {
        this.targetPackage = "";
        this.compilerEnv.setGenerateDebugInfo(false);
        int i = 0;
        while (i < strArr.length) {
            String str = strArr[i];
            if (!str.startsWith("-")) {
                int length = strArr.length - i;
                String str2 = this.targetName;
                if (str2 == null || length <= 1) {
                    String[] strArr2 = new String[length];
                    for (int i2 = 0; i2 != length; i2++) {
                        strArr2[i2] = strArr[i + i2];
                    }
                    return strArr2;
                }
                addError("msg.multiple.js.to.file", str2);
                return null;
            } else if (str.equals("-help") || str.equals("-h") || str.equals("--help")) {
                this.printHelp = true;
                return null;
            } else {
                try {
                    if (str.equals("-version") && (i = i + 1) < strArr.length) {
                        this.compilerEnv.setLanguageVersion(Integer.parseInt(strArr[i]));
                    } else if ((str.equals("-opt") || str.equals("-O")) && (i = i + 1) < strArr.length) {
                        this.compilerEnv.setOptimizationLevel(Integer.parseInt(strArr[i]));
                    } else if (str.equals("-nosource")) {
                        this.compilerEnv.setGeneratingSource(false);
                    } else if (str.equals("-debug") || str.equals("-g")) {
                        this.compilerEnv.setGenerateDebugInfo(true);
                    } else if (str.equals("-main-method-class") && (i = i + 1) < strArr.length) {
                        this.compiler.setMainMethodClass(strArr[i]);
                    } else if (str.equals("-encoding") && (i = i + 1) < strArr.length) {
                        this.characterEncoding = strArr[i];
                    } else if (!str.equals("-o") || (i = i + 1) >= strArr.length) {
                        if (str.equals("-observe-instruction-count")) {
                            this.compilerEnv.setGenerateObserverCount(true);
                        }
                        if (str.equals("-package") && (i = i + 1) < strArr.length) {
                            String str3 = strArr[i];
                            int length2 = str3.length();
                            int i3 = 0;
                            while (i3 != length2) {
                                char charAt = str3.charAt(i3);
                                if (Character.isJavaIdentifierStart(charAt)) {
                                    do {
                                        i3++;
                                        if (i3 == length2) {
                                            break;
                                        }
                                        charAt = str3.charAt(i3);
                                    } while (Character.isJavaIdentifierPart(charAt));
                                    if (i3 == length2) {
                                        break;
                                    } else if (charAt == '.' && i3 != length2 - 1) {
                                        i3++;
                                    }
                                }
                                addError("msg.package.name", this.targetPackage);
                                return null;
                            }
                            this.targetPackage = str3;
                        } else if (str.equals("-extends") && (i = i + 1) < strArr.length) {
                            try {
                                this.compiler.setTargetExtends(Class.forName(strArr[i]));
                            } catch (ClassNotFoundException e) {
                                throw new Error(e.toString());
                            }
                        } else if (str.equals("-implements") && (i = i + 1) < strArr.length) {
                            StringTokenizer stringTokenizer = new StringTokenizer(strArr[i], ",");
                            ArrayList arrayList = new ArrayList();
                            while (stringTokenizer.hasMoreTokens()) {
                                try {
                                    arrayList.add(Class.forName(stringTokenizer.nextToken()));
                                } catch (ClassNotFoundException e2) {
                                    throw new Error(e2.toString());
                                }
                            }
                            this.compiler.setTargetImplements((Class[]) arrayList.toArray(new Class[arrayList.size()]));
                        } else if (!str.equals("-d") || (i = i + 1) >= strArr.length) {
                            badUsage(str);
                            return null;
                        } else {
                            this.destinationDir = strArr[i];
                        }
                    } else {
                        String str4 = strArr[i];
                        int length3 = str4.length();
                        if (length3 == 0 || !Character.isJavaIdentifierStart(str4.charAt(0))) {
                            addError("msg.invalid.classfile.name", str4);
                        } else {
                            int i4 = 1;
                            while (true) {
                                if (i4 >= length3) {
                                    break;
                                }
                                char charAt2 = str4.charAt(i4);
                                if (Character.isJavaIdentifierPart(charAt2)) {
                                    i4++;
                                } else if (charAt2 == '.' && i4 == length3 - 6 && str4.endsWith(".class")) {
                                    str4 = str4.substring(0, i4);
                                } else {
                                    addError("msg.invalid.classfile.name", str4);
                                }
                            }
                            this.targetName = str4;
                        }
                    }
                    i++;
                } catch (NumberFormatException unused) {
                    badUsage(strArr[i]);
                    return null;
                }
            }
        }
        p(ToolErrorReporter.getMessage("msg.no.file"));
        return null;
    }

    private static void badUsage(String str) {
        System.err.println(ToolErrorReporter.getMessage("msg.jsc.bad.usage", Main.class.getName(), str));
    }

    public void processSource(String[] strArr) {
        FileOutputStream fileOutputStream;
        int i = 0;
        while (i != strArr.length) {
            String str = strArr[i];
            if (!str.endsWith(".js")) {
                addError("msg.extension.not.js", str);
                return;
            }
            File file = new File(str);
            String readSource = readSource(file);
            if (readSource != null) {
                String str2 = this.targetName;
                if (str2 == null) {
                    String name = file.getName();
                    str2 = getClassName(name.substring(0, name.length() - 3));
                }
                if (this.targetPackage.length() != 0) {
                    str2 = this.targetPackage + "." + str2;
                }
                Object[] compileToClassFiles = this.compiler.compileToClassFiles(readSource, str, 1, str2);
                if (compileToClassFiles != null && compileToClassFiles.length != 0) {
                    File file2 = null;
                    if (this.destinationDir != null) {
                        file2 = new File(this.destinationDir);
                    } else {
                        String parent = file.getParent();
                        if (parent != null) {
                            file2 = new File(parent);
                        }
                    }
                    for (int i2 = 0; i2 != compileToClassFiles.length; i2 += 2) {
                        byte[] bArr = (byte[]) compileToClassFiles[i2 + 1];
                        try {
                            fileOutputStream = new FileOutputStream(getOutputFile(file2, (String) compileToClassFiles[i2]));
                            fileOutputStream.write(bArr);
                            fileOutputStream.close();
                        } catch (IOException e) {
                            addFormatedError(e.toString());
                        } catch (Throwable th) {
                            fileOutputStream.close();
                            throw th;
                        }
                    }
                    i++;
                } else {
                    return;
                }
            } else {
                return;
            }
        }
    }

    private String readSource(File file) {
        String absolutePath = file.getAbsolutePath();
        if (!file.isFile()) {
            addError("msg.jsfile.not.found", absolutePath);
            return null;
        }
        try {
            return (String) SourceReader.readFileOrUrl(absolutePath, true, this.characterEncoding);
        } catch (FileNotFoundException unused) {
            addError("msg.couldnt.open", absolutePath);
            return null;
        } catch (IOException e) {
            addFormatedError(e.toString());
            return null;
        }
    }

    private File getOutputFile(File file, String str) {
        File file2 = new File(file, str.replace('.', File.separatorChar).concat(".class"));
        String parent = file2.getParent();
        if (parent != null) {
            File file3 = new File(parent);
            if (!file3.exists()) {
                file3.mkdirs();
            }
        }
        return file2;
    }

    /* access modifiers changed from: package-private */
    public String getClassName(String str) {
        int i = 1;
        char[] cArr = new char[(str.length() + 1)];
        int i2 = 0;
        if (!Character.isJavaIdentifierStart(str.charAt(0))) {
            cArr[0] = '_';
        } else {
            i = 0;
        }
        while (i2 < str.length()) {
            char charAt = str.charAt(i2);
            if (Character.isJavaIdentifierPart(charAt)) {
                cArr[i] = charAt;
            } else {
                cArr[i] = '_';
            }
            i2++;
            i++;
        }
        return new String(cArr).trim();
    }

    private static void p(String str) {
        System.out.println(str);
    }

    private void addError(String str, String str2) {
        String str3;
        if (str2 == null) {
            str3 = ToolErrorReporter.getMessage(str);
        } else {
            str3 = ToolErrorReporter.getMessage(str, str2);
        }
        addFormatedError(str3);
    }

    private void addFormatedError(String str) {
        this.reporter.error(str, (String) null, -1, (String) null, -1);
    }
}
