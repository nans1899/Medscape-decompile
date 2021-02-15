package com.bea.xml.stream.util;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.apache.commons.io.IOUtils;

public class SymbolTable {
    private int depth = 0;
    private Stack table = new Stack();
    private Map values = new HashMap();

    public void clear() {
        this.depth = 0;
        this.table.clear();
        this.values.clear();
    }

    public int getDepth() {
        return this.depth;
    }

    public boolean withinElement() {
        return this.depth > 0;
    }

    public void put(String str, String str2) {
        this.table.push(new Symbol(str, str2, this.depth));
        if (!this.values.containsKey(str)) {
            Stack stack = new Stack();
            stack.push(str2);
            this.values.put(str, stack);
            return;
        }
        ((Stack) this.values.get(str)).push(str2);
    }

    public String get(String str) {
        Stack stack = (Stack) this.values.get(str);
        if (stack == null || stack.isEmpty()) {
            return null;
        }
        return (String) stack.peek();
    }

    public Set getAll(String str) {
        HashSet hashSet = new HashSet();
        Iterator it = this.table.iterator();
        while (it.hasNext()) {
            Symbol symbol = (Symbol) it.next();
            if (str.equals(symbol.getName())) {
                hashSet.add(symbol.getValue());
            }
        }
        return hashSet;
    }

    public void openScope() {
        this.depth++;
    }

    public void closeScope() {
        int i = ((Symbol) this.table.peek()).depth;
        while (i == this.depth && !this.table.isEmpty()) {
            ((Stack) this.values.get(((Symbol) this.table.pop()).name)).pop();
            if (this.table.isEmpty()) {
                break;
            }
            i = ((Symbol) this.table.peek()).depth;
        }
        this.depth--;
    }

    public String toString() {
        Iterator it = this.table.iterator();
        String str = "";
        while (it.hasNext()) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(str);
            stringBuffer.append((Symbol) it.next());
            stringBuffer.append(IOUtils.LINE_SEPARATOR_UNIX);
            str = stringBuffer.toString();
        }
        return str;
    }

    public static void main(String[] strArr) throws Exception {
        SymbolTable symbolTable = new SymbolTable();
        symbolTable.openScope();
        symbolTable.put("x", "foo");
        symbolTable.put("y", "bar");
        PrintStream printStream = System.out;
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("1 x:");
        stringBuffer.append(symbolTable.get("x"));
        printStream.println(stringBuffer.toString());
        PrintStream printStream2 = System.out;
        StringBuffer stringBuffer2 = new StringBuffer();
        stringBuffer2.append("1 y:");
        stringBuffer2.append(symbolTable.get("y"));
        printStream2.println(stringBuffer2.toString());
        symbolTable.openScope();
        symbolTable.put("x", "bar");
        symbolTable.put("y", "foo");
        symbolTable.openScope();
        symbolTable.put("x", "barbie");
        symbolTable.openScope();
        symbolTable.closeScope();
        PrintStream printStream3 = System.out;
        StringBuffer stringBuffer3 = new StringBuffer();
        stringBuffer3.append("3 x:");
        stringBuffer3.append(symbolTable.get("x"));
        printStream3.println(stringBuffer3.toString());
        symbolTable.closeScope();
        PrintStream printStream4 = System.out;
        StringBuffer stringBuffer4 = new StringBuffer();
        stringBuffer4.append("2 x:");
        stringBuffer4.append(symbolTable.get("x"));
        printStream4.println(stringBuffer4.toString());
        PrintStream printStream5 = System.out;
        StringBuffer stringBuffer5 = new StringBuffer();
        stringBuffer5.append("2 y:");
        stringBuffer5.append(symbolTable.get("y"));
        printStream5.println(stringBuffer5.toString());
        System.out.print(symbolTable);
        symbolTable.closeScope();
        PrintStream printStream6 = System.out;
        StringBuffer stringBuffer6 = new StringBuffer();
        stringBuffer6.append("1 x:");
        stringBuffer6.append(symbolTable.get("x"));
        printStream6.println(stringBuffer6.toString());
        PrintStream printStream7 = System.out;
        StringBuffer stringBuffer7 = new StringBuffer();
        stringBuffer7.append("1 y:");
        stringBuffer7.append(symbolTable.get("y"));
        printStream7.println(stringBuffer7.toString());
        symbolTable.closeScope();
        System.out.print(symbolTable);
    }
}
