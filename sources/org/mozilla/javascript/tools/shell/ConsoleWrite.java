package org.mozilla.javascript.tools.shell;

/* compiled from: ConsoleTextArea */
class ConsoleWrite implements Runnable {
    private String str;
    private ConsoleTextArea textArea;

    public ConsoleWrite(ConsoleTextArea consoleTextArea, String str2) {
        this.textArea = consoleTextArea;
        this.str = str2;
    }

    public void run() {
        this.textArea.write(this.str);
    }
}
