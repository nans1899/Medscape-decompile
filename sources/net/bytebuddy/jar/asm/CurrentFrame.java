package net.bytebuddy.jar.asm;

final class CurrentFrame extends Frame {
    CurrentFrame(Label label) {
        super(label);
    }

    /* access modifiers changed from: package-private */
    public void execute(int i, int i2, Symbol symbol, SymbolTable symbolTable) {
        super.execute(i, i2, symbol, symbolTable);
        Frame frame = new Frame((Label) null);
        merge(symbolTable, frame, 0);
        copyFrom(frame);
    }
}
