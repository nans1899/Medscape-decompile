package org.mozilla.javascript.tools.debugger;

import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.google.logging.type.LogSeverity;
import java.awt.ActiveEvent;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.MenuComponent;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileFilter;
import javax.swing.text.BadLocationException;
import org.mozilla.javascript.Kit;
import org.mozilla.javascript.SecurityUtilities;
import org.mozilla.javascript.tools.debugger.Dim;

public class SwingGui extends JFrame implements GuiCallback {
    private static final long serialVersionUID = -8217029773456711621L;
    private EventQueue awtEventQueue;
    private JSInternalConsole console;
    private ContextWindow context;
    private FileWindow currentWindow;
    private JDesktopPane desk;
    Dim dim;
    JFileChooser dlg;
    private Runnable exitAction;
    private final Map<String, FileWindow> fileWindows = Collections.synchronizedMap(new HashMap());
    private Menubar menubar;
    private JSplitPane split1;
    private JLabel statusBar;
    private JToolBar toolBar;
    private final Map<String, JFrame> toplevels = Collections.synchronizedMap(new HashMap());

    public SwingGui(Dim dim2, String str) {
        super(str);
        this.dim = dim2;
        init();
        dim2.setGuiCallback(this);
    }

    public Menubar getMenubar() {
        return this.menubar;
    }

    public void setExitAction(Runnable runnable) {
        this.exitAction = runnable;
    }

    public JSInternalConsole getConsole() {
        return this.console;
    }

    public void setVisible(boolean z) {
        SwingGui.super.setVisible(z);
        if (z) {
            this.console.consoleTextArea.requestFocus();
            this.context.split.setDividerLocation(0.5d);
            try {
                this.console.setMaximum(true);
                this.console.setSelected(true);
                this.console.show();
                this.console.consoleTextArea.requestFocus();
            } catch (Exception unused) {
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void addTopLevel(String str, JFrame jFrame) {
        if (jFrame != this) {
            this.toplevels.put(str, jFrame);
        }
    }

    private void init() {
        Menubar menubar2 = new Menubar(this);
        this.menubar = menubar2;
        setJMenuBar(menubar2);
        this.toolBar = new JToolBar();
        String[] strArr = {"Break (Pause)", "Go (F5)", "Step Into (F11)", "Step Over (F7)", "Step Out (F8)"};
        JButton jButton = new JButton("Break");
        jButton.setToolTipText("Break");
        jButton.setActionCommand("Break");
        jButton.addActionListener(this.menubar);
        jButton.setEnabled(true);
        jButton.setToolTipText(strArr[0]);
        JButton jButton2 = new JButton("Go");
        jButton2.setToolTipText("Go");
        jButton2.setActionCommand("Go");
        jButton2.addActionListener(this.menubar);
        jButton2.setEnabled(false);
        jButton2.setToolTipText(strArr[1]);
        JButton jButton3 = new JButton("Step Into");
        jButton3.setToolTipText("Step Into");
        jButton3.setActionCommand("Step Into");
        jButton3.addActionListener(this.menubar);
        jButton3.setEnabled(false);
        jButton3.setToolTipText(strArr[2]);
        JButton jButton4 = new JButton("Step Over");
        jButton4.setToolTipText("Step Over");
        jButton4.setActionCommand("Step Over");
        jButton4.setEnabled(false);
        jButton4.addActionListener(this.menubar);
        jButton4.setToolTipText(strArr[3]);
        JButton jButton5 = new JButton("Step Out");
        jButton5.setToolTipText("Step Out");
        jButton5.setActionCommand("Step Out");
        jButton5.setEnabled(false);
        jButton5.addActionListener(this.menubar);
        jButton5.setToolTipText(strArr[4]);
        Dimension preferredSize = jButton4.getPreferredSize();
        jButton.setPreferredSize(preferredSize);
        jButton.setMinimumSize(preferredSize);
        jButton.setMaximumSize(preferredSize);
        jButton.setSize(preferredSize);
        jButton2.setPreferredSize(preferredSize);
        jButton2.setMinimumSize(preferredSize);
        jButton2.setMaximumSize(preferredSize);
        jButton3.setPreferredSize(preferredSize);
        jButton3.setMinimumSize(preferredSize);
        jButton3.setMaximumSize(preferredSize);
        jButton4.setPreferredSize(preferredSize);
        jButton4.setMinimumSize(preferredSize);
        jButton4.setMaximumSize(preferredSize);
        jButton5.setPreferredSize(preferredSize);
        jButton5.setMinimumSize(preferredSize);
        jButton5.setMaximumSize(preferredSize);
        this.toolBar.add(jButton);
        this.toolBar.add(jButton2);
        this.toolBar.add(jButton3);
        this.toolBar.add(jButton4);
        this.toolBar.add(jButton5);
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new BorderLayout());
        getContentPane().add(this.toolBar, "North");
        getContentPane().add(jPanel, "Center");
        JDesktopPane jDesktopPane = new JDesktopPane();
        this.desk = jDesktopPane;
        jDesktopPane.setPreferredSize(new Dimension(LogSeverity.CRITICAL_VALUE, 300));
        this.desk.setMinimumSize(new Dimension(150, 50));
        JDesktopPane jDesktopPane2 = this.desk;
        JSInternalConsole jSInternalConsole = new JSInternalConsole("JavaScript Console");
        this.console = jSInternalConsole;
        jDesktopPane2.add(jSInternalConsole);
        ContextWindow contextWindow = new ContextWindow(this);
        this.context = contextWindow;
        contextWindow.setPreferredSize(new Dimension(LogSeverity.CRITICAL_VALUE, 120));
        this.context.setMinimumSize(new Dimension(50, 50));
        JSplitPane jSplitPane = new JSplitPane(0, this.desk, this.context);
        this.split1 = jSplitPane;
        jSplitPane.setOneTouchExpandable(true);
        setResizeWeight(this.split1, 0.66d);
        jPanel.add(this.split1, "Center");
        JLabel jLabel = new JLabel();
        this.statusBar = jLabel;
        jLabel.setText("Thread: ");
        jPanel.add(this.statusBar, "South");
        this.dlg = new JFileChooser();
        this.dlg.addChoosableFileFilter(new FileFilter() {
            public String getDescription() {
                return "JavaScript Files (*.js)";
            }

            public boolean accept(File file) {
                if (file.isDirectory()) {
                    return true;
                }
                String name = file.getName();
                int lastIndexOf = name.lastIndexOf(46);
                if (lastIndexOf <= 0 || lastIndexOf >= name.length() - 1 || !name.substring(lastIndexOf + 1).toLowerCase().equals("js")) {
                    return false;
                }
                return true;
            }
        });
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                SwingGui.this.exit();
            }
        });
    }

    /* access modifiers changed from: private */
    public void exit() {
        Runnable runnable = this.exitAction;
        if (runnable != null) {
            SwingUtilities.invokeLater(runnable);
        }
        this.dim.setReturnValue(5);
    }

    /* access modifiers changed from: package-private */
    public FileWindow getFileWindow(String str) {
        if (str == null || str.equals("<stdin>")) {
            return null;
        }
        return this.fileWindows.get(str);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0010, code lost:
        r0 = r0 + 1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static java.lang.String getShortName(java.lang.String r2) {
        /*
            r0 = 47
            int r0 = r2.lastIndexOf(r0)
            if (r0 >= 0) goto L_0x000e
            r0 = 92
            int r0 = r2.lastIndexOf(r0)
        L_0x000e:
            if (r0 < 0) goto L_0x001c
            int r0 = r0 + 1
            int r1 = r2.length()
            if (r0 >= r1) goto L_0x001c
            java.lang.String r2 = r2.substring(r0)
        L_0x001c:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.tools.debugger.SwingGui.getShortName(java.lang.String):java.lang.String");
    }

    /* access modifiers changed from: package-private */
    public void removeWindow(FileWindow fileWindow) {
        this.fileWindows.remove(fileWindow.getUrl());
        JMenu windowMenu = getWindowMenu();
        int itemCount = windowMenu.getItemCount();
        int i = itemCount - 1;
        JMenuItem item = windowMenu.getItem(i);
        String shortName = getShortName(fileWindow.getUrl());
        int i2 = 5;
        while (true) {
            if (i2 >= itemCount) {
                break;
            }
            JMenuItem item2 = windowMenu.getItem(i2);
            if (item2 != null) {
                String text = item2.getText();
                if (text.substring(text.indexOf(32) + 1).equals(shortName)) {
                    windowMenu.remove(item2);
                    if (itemCount == 6) {
                        windowMenu.remove(4);
                    } else {
                        int i3 = i2 - 4;
                        while (i2 < i) {
                            JMenuItem item3 = windowMenu.getItem(i2);
                            if (item3 != null) {
                                String text2 = item3.getText();
                                if (text2.equals("More Windows...")) {
                                    break;
                                }
                                int indexOf = text2.indexOf(32);
                                StringBuilder sb = new StringBuilder();
                                int i4 = i3 + 48;
                                sb.append((char) i4);
                                sb.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
                                sb.append(text2.substring(indexOf + 1));
                                item3.setText(sb.toString());
                                item3.setMnemonic(i4);
                                i3++;
                            }
                            i2++;
                        }
                        if (itemCount - 6 == 0 && item != item2 && item.getText().equals("More Windows...")) {
                            windowMenu.remove(item);
                        }
                    }
                }
            }
            i2++;
        }
        windowMenu.revalidate();
    }

    /* access modifiers changed from: package-private */
    public void showStopLine(Dim.StackFrame stackFrame) {
        String url = stackFrame.getUrl();
        if (url != null && !url.equals("<stdin>")) {
            showFileWindow(url, -1);
            int lineNumber = stackFrame.getLineNumber();
            FileWindow fileWindow = getFileWindow(url);
            if (fileWindow != null) {
                setFilePosition(fileWindow, lineNumber);
            }
        } else if (this.console.isVisible()) {
            this.console.show();
        }
    }

    /* access modifiers changed from: protected */
    public void showFileWindow(String str, int i) {
        FileWindow fileWindow = getFileWindow(str);
        if (fileWindow == null) {
            createFileWindow(this.dim.sourceInfo(str), -1);
            fileWindow = getFileWindow(str);
        }
        if (i > -1) {
            int position = fileWindow.getPosition(i - 1);
            fileWindow.textArea.select(position);
            fileWindow.textArea.setCaretPosition(position);
            fileWindow.textArea.moveCaretPosition(fileWindow.getPosition(i) - 1);
        }
        try {
            if (fileWindow.isIcon()) {
                fileWindow.setIcon(false);
            }
            fileWindow.setVisible(true);
            fileWindow.moveToFront();
            fileWindow.setSelected(true);
            requestFocus();
            fileWindow.requestFocus();
            fileWindow.textArea.requestFocus();
        } catch (Exception unused) {
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0024 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void createFileWindow(org.mozilla.javascript.tools.debugger.Dim.SourceInfo r5, int r6) {
        /*
            r4 = this;
            java.lang.String r0 = r5.url()
            org.mozilla.javascript.tools.debugger.FileWindow r1 = new org.mozilla.javascript.tools.debugger.FileWindow
            r1.<init>(r4, r5)
            java.util.Map<java.lang.String, org.mozilla.javascript.tools.debugger.FileWindow> r5 = r4.fileWindows
            r5.put(r0, r1)
            r5 = -1
            if (r6 == r5) goto L_0x0032
            org.mozilla.javascript.tools.debugger.FileWindow r2 = r4.currentWindow
            if (r2 == 0) goto L_0x0018
            r2.setPosition(r5)
        L_0x0018:
            org.mozilla.javascript.tools.debugger.FileTextArea r2 = r1.textArea     // Catch:{ BadLocationException -> 0x0024 }
            int r3 = r6 + -1
            int r2 = r2.getLineStartOffset(r3)     // Catch:{ BadLocationException -> 0x0024 }
            r1.setPosition(r2)     // Catch:{ BadLocationException -> 0x0024 }
            goto L_0x0032
        L_0x0024:
            org.mozilla.javascript.tools.debugger.FileTextArea r2 = r1.textArea     // Catch:{ BadLocationException -> 0x002f }
            r3 = 0
            int r2 = r2.getLineStartOffset(r3)     // Catch:{ BadLocationException -> 0x002f }
            r1.setPosition(r2)     // Catch:{ BadLocationException -> 0x002f }
            goto L_0x0032
        L_0x002f:
            r1.setPosition(r5)
        L_0x0032:
            javax.swing.JDesktopPane r2 = r4.desk
            r2.add(r1)
            if (r6 == r5) goto L_0x003b
            r4.currentWindow = r1
        L_0x003b:
            org.mozilla.javascript.tools.debugger.Menubar r5 = r4.menubar
            r5.addFile(r0)
            r5 = 1
            r1.setVisible(r5)
            r1.setMaximum(r5)     // Catch:{ Exception -> 0x004d }
            r1.setSelected(r5)     // Catch:{ Exception -> 0x004d }
            r1.moveToFront()     // Catch:{ Exception -> 0x004d }
        L_0x004d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.tools.debugger.SwingGui.createFileWindow(org.mozilla.javascript.tools.debugger.Dim$SourceInfo, int):void");
    }

    /* access modifiers changed from: protected */
    public boolean updateFileWindow(Dim.SourceInfo sourceInfo) {
        FileWindow fileWindow = getFileWindow(sourceInfo.url());
        if (fileWindow == null) {
            return false;
        }
        fileWindow.updateText(sourceInfo);
        fileWindow.show();
        return true;
    }

    private void setFilePosition(FileWindow fileWindow, int i) {
        FileTextArea fileTextArea = fileWindow.textArea;
        if (i == -1) {
            try {
                fileWindow.setPosition(-1);
                if (this.currentWindow == fileWindow) {
                    this.currentWindow = null;
                }
            } catch (BadLocationException unused) {
            }
        } else {
            int lineStartOffset = fileTextArea.getLineStartOffset(i - 1);
            if (!(this.currentWindow == null || this.currentWindow == fileWindow)) {
                this.currentWindow.setPosition(-1);
            }
            fileWindow.setPosition(lineStartOffset);
            this.currentWindow = fileWindow;
        }
        if (fileWindow.isIcon()) {
            this.desk.getDesktopManager().deiconifyFrame(fileWindow);
        }
        this.desk.getDesktopManager().activateFrame(fileWindow);
        try {
            fileWindow.show();
            fileWindow.toFront();
            fileWindow.setSelected(true);
        } catch (Exception unused2) {
        }
    }

    /* access modifiers changed from: package-private */
    public void enterInterruptImpl(Dim.StackFrame stackFrame, String str, String str2) {
        String str3;
        this.statusBar.setText("Thread: " + str);
        showStopLine(stackFrame);
        if (str2 != null) {
            MessageDialogWrapper.showMessageDialog(this, str2, "Exception in Script", 0);
        }
        updateEnabled(true);
        Dim.ContextData contextData = stackFrame.contextData();
        JComboBox jComboBox = this.context.context;
        List<String> list = this.context.toolTips;
        this.context.disableUpdate();
        int frameCount = contextData.frameCount();
        jComboBox.removeAllItems();
        jComboBox.setSelectedItem((Object) null);
        list.clear();
        for (int i = 0; i < frameCount; i++) {
            Dim.StackFrame frame = contextData.getFrame(i);
            String url = frame.getUrl();
            int lineNumber = frame.getLineNumber();
            if (url.length() > 20) {
                str3 = "..." + url.substring(url.length() - 17);
            } else {
                str3 = url;
            }
            jComboBox.insertItemAt("\"" + str3 + "\", line " + lineNumber, i);
            list.add("\"" + url + "\", line " + lineNumber);
        }
        this.context.enableUpdate();
        jComboBox.setSelectedIndex(0);
        jComboBox.setMinimumSize(new Dimension(50, jComboBox.getMinimumSize().height));
    }

    private JMenu getWindowMenu() {
        return this.menubar.getMenu(3);
    }

    private String chooseFile(String str) {
        this.dlg.setDialogTitle(str);
        String systemProperty = SecurityUtilities.getSystemProperty("user.dir");
        File file = systemProperty != null ? new File(systemProperty) : null;
        if (file != null) {
            this.dlg.setCurrentDirectory(file);
        }
        if (this.dlg.showOpenDialog(this) == 0) {
            try {
                String canonicalPath = this.dlg.getSelectedFile().getCanonicalPath();
                File parentFile = this.dlg.getSelectedFile().getParentFile();
                Properties properties = System.getProperties();
                properties.put("user.dir", parentFile.getPath());
                System.setProperties(properties);
                return canonicalPath;
            } catch (IOException | SecurityException unused) {
            }
        }
        return null;
    }

    private JInternalFrame getSelectedFrame() {
        JInternalFrame[] allFrames = this.desk.getAllFrames();
        for (int i = 0; i < allFrames.length; i++) {
            if (allFrames[i].isShowing()) {
                return allFrames[i];
            }
        }
        return allFrames[allFrames.length - 1];
    }

    private void updateEnabled(boolean z) {
        getJMenuBar().updateEnabled(z);
        int componentCount = this.toolBar.getComponentCount();
        int i = 0;
        while (i < componentCount) {
            this.toolBar.getComponent(i).setEnabled(i == 0 ? !z : z);
            i++;
        }
        if (z) {
            this.toolBar.setEnabled(true);
            if (getExtendedState() == 1) {
                setExtendedState(0);
            }
            toFront();
            this.context.setEnabled(true);
            return;
        }
        FileWindow fileWindow = this.currentWindow;
        if (fileWindow != null) {
            fileWindow.setPosition(-1);
        }
        this.context.setEnabled(false);
    }

    static void setResizeWeight(JSplitPane jSplitPane, double d) {
        Class<JSplitPane> cls = JSplitPane.class;
        try {
            cls.getMethod("setResizeWeight", new Class[]{Double.TYPE}).invoke(jSplitPane, new Object[]{new Double(d)});
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException unused) {
        }
    }

    private String readFile(String str) {
        FileReader fileReader;
        try {
            fileReader = new FileReader(str);
            String readReader = Kit.readReader(fileReader);
            fileReader.close();
            return readReader;
        } catch (IOException e) {
            String message = e.getMessage();
            MessageDialogWrapper.showMessageDialog(this, message, "Error reading " + str, 0);
            return null;
        } catch (Throwable th) {
            fileReader.close();
            throw th;
        }
    }

    public void updateSourceText(Dim.SourceInfo sourceInfo) {
        RunProxy runProxy = new RunProxy(this, 3);
        runProxy.sourceInfo = sourceInfo;
        SwingUtilities.invokeLater(runProxy);
    }

    public void enterInterrupt(Dim.StackFrame stackFrame, String str, String str2) {
        if (SwingUtilities.isEventDispatchThread()) {
            enterInterruptImpl(stackFrame, str, str2);
            return;
        }
        RunProxy runProxy = new RunProxy(this, 4);
        runProxy.lastFrame = stackFrame;
        runProxy.threadTitle = str;
        runProxy.alertMessage = str2;
        SwingUtilities.invokeLater(runProxy);
    }

    public boolean isGuiEventThread() {
        return SwingUtilities.isEventDispatchThread();
    }

    public void dispatchNextGuiEvent() throws InterruptedException {
        EventQueue eventQueue = this.awtEventQueue;
        if (eventQueue == null) {
            eventQueue = Toolkit.getDefaultToolkit().getSystemEventQueue();
            this.awtEventQueue = eventQueue;
        }
        ActiveEvent nextEvent = eventQueue.getNextEvent();
        if (nextEvent instanceof ActiveEvent) {
            nextEvent.dispatch();
            return;
        }
        Object source = nextEvent.getSource();
        if (source instanceof Component) {
            ((Component) source).dispatchEvent(nextEvent);
        } else if (source instanceof MenuComponent) {
            ((MenuComponent) source).dispatchEvent(nextEvent);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:104:0x020c  */
    /* JADX WARNING: Removed duplicated region for block: B:112:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void actionPerformed(java.awt.event.ActionEvent r20) {
        /*
            r19 = this;
            r0 = r19
            java.lang.String r1 = r20.getActionCommand()
            java.lang.String r2 = "Cut"
            boolean r3 = r1.equals(r2)
            r4 = 2
            r5 = -1
            r6 = 1
            r7 = 0
            if (r3 != 0) goto L_0x01f8
            java.lang.String r3 = "Copy"
            boolean r8 = r1.equals(r3)
            if (r8 != 0) goto L_0x01f8
            java.lang.String r8 = "Paste"
            boolean r9 = r1.equals(r8)
            if (r9 == 0) goto L_0x0024
            goto L_0x01f8
        L_0x0024:
            java.lang.String r9 = "Step Over"
            boolean r9 = r1.equals(r9)
            if (r9 == 0) goto L_0x002f
            r4 = 0
            goto L_0x020a
        L_0x002f:
            java.lang.String r9 = "Step Into"
            boolean r9 = r1.equals(r9)
            if (r9 == 0) goto L_0x003a
            r4 = 1
            goto L_0x020a
        L_0x003a:
            java.lang.String r9 = "Step Out"
            boolean r9 = r1.equals(r9)
            if (r9 == 0) goto L_0x0044
            goto L_0x020a
        L_0x0044:
            java.lang.String r9 = "Go"
            boolean r9 = r1.equals(r9)
            if (r9 == 0) goto L_0x004f
            r4 = 3
            goto L_0x020a
        L_0x004f:
            java.lang.String r9 = "Break"
            boolean r9 = r1.equals(r9)
            if (r9 == 0) goto L_0x005e
            org.mozilla.javascript.tools.debugger.Dim r1 = r0.dim
            r1.setBreak()
            goto L_0x0209
        L_0x005e:
            java.lang.String r9 = "Exit"
            boolean r9 = r1.equals(r9)
            if (r9 == 0) goto L_0x006b
            r19.exit()
            goto L_0x0209
        L_0x006b:
            java.lang.String r9 = "Open"
            boolean r9 = r1.equals(r9)
            if (r9 == 0) goto L_0x0094
            java.lang.String r1 = "Select a file to compile"
            java.lang.String r1 = r0.chooseFile(r1)
            if (r1 == 0) goto L_0x0209
            java.lang.String r2 = r0.readFile(r1)
            if (r2 == 0) goto L_0x0209
            org.mozilla.javascript.tools.debugger.RunProxy r3 = new org.mozilla.javascript.tools.debugger.RunProxy
            r3.<init>(r0, r6)
            r3.fileName = r1
            r3.text = r2
            java.lang.Thread r1 = new java.lang.Thread
            r1.<init>(r3)
            r1.start()
            goto L_0x0209
        L_0x0094:
            java.lang.String r9 = "Load"
            boolean r9 = r1.equals(r9)
            if (r9 == 0) goto L_0x00bd
            java.lang.String r1 = "Select a file to execute"
            java.lang.String r1 = r0.chooseFile(r1)
            if (r1 == 0) goto L_0x0209
            java.lang.String r2 = r0.readFile(r1)
            if (r2 == 0) goto L_0x0209
            org.mozilla.javascript.tools.debugger.RunProxy r3 = new org.mozilla.javascript.tools.debugger.RunProxy
            r3.<init>(r0, r4)
            r3.fileName = r1
            r3.text = r2
            java.lang.Thread r1 = new java.lang.Thread
            r1.<init>(r3)
            r1.start()
            goto L_0x0209
        L_0x00bd:
            java.lang.String r4 = "More Windows..."
            boolean r4 = r1.equals(r4)
            if (r4 == 0) goto L_0x00d5
            org.mozilla.javascript.tools.debugger.MoreWindows r1 = new org.mozilla.javascript.tools.debugger.MoreWindows
            java.util.Map<java.lang.String, org.mozilla.javascript.tools.debugger.FileWindow> r2 = r0.fileWindows
            java.lang.String r3 = "Window"
            java.lang.String r4 = "Files"
            r1.<init>(r0, r2, r3, r4)
            r1.showDialog(r0)
            goto L_0x0209
        L_0x00d5:
            java.lang.String r4 = "Console"
            boolean r4 = r1.equals(r4)
            if (r4 == 0) goto L_0x0109
            org.mozilla.javascript.tools.debugger.JSInternalConsole r1 = r0.console
            boolean r1 = r1.isIcon()
            if (r1 == 0) goto L_0x00f0
            javax.swing.JDesktopPane r1 = r0.desk
            javax.swing.DesktopManager r1 = r1.getDesktopManager()
            org.mozilla.javascript.tools.debugger.JSInternalConsole r2 = r0.console
            r1.deiconifyFrame(r2)
        L_0x00f0:
            org.mozilla.javascript.tools.debugger.JSInternalConsole r1 = r0.console
            r1.show()
            javax.swing.JDesktopPane r1 = r0.desk
            javax.swing.DesktopManager r1 = r1.getDesktopManager()
            org.mozilla.javascript.tools.debugger.JSInternalConsole r2 = r0.console
            r1.activateFrame(r2)
            org.mozilla.javascript.tools.debugger.JSInternalConsole r1 = r0.console
            org.mozilla.javascript.tools.shell.ConsoleTextArea r1 = r1.consoleTextArea
            r1.requestFocus()
            goto L_0x0209
        L_0x0109:
            boolean r2 = r1.equals(r2)
            if (r2 == 0) goto L_0x0111
            goto L_0x0209
        L_0x0111:
            boolean r2 = r1.equals(r3)
            if (r2 == 0) goto L_0x0119
            goto L_0x0209
        L_0x0119:
            boolean r2 = r1.equals(r8)
            if (r2 == 0) goto L_0x0121
            goto L_0x0209
        L_0x0121:
            java.lang.String r2 = "Go to function..."
            boolean r2 = r1.equals(r2)
            if (r2 == 0) goto L_0x0137
            org.mozilla.javascript.tools.debugger.FindFunction r1 = new org.mozilla.javascript.tools.debugger.FindFunction
            java.lang.String r2 = "Go to function"
            java.lang.String r3 = "Function"
            r1.<init>(r0, r2, r3)
            r1.showDialog(r0)
            goto L_0x0209
        L_0x0137:
            java.lang.String r2 = "Tile"
            boolean r2 = r1.equals(r2)
            if (r2 == 0) goto L_0x019c
            javax.swing.JDesktopPane r1 = r0.desk
            javax.swing.JInternalFrame[] r1 = r1.getAllFrames()
            int r2 = r1.length
            double r3 = (double) r2
            double r3 = java.lang.Math.sqrt(r3)
            int r3 = (int) r3
            int r4 = r3 * r3
            if (r4 >= r2) goto L_0x015e
            int r4 = r3 + 1
            int r6 = r3 * r4
            if (r6 >= r2) goto L_0x0158
            r3 = r4
            goto L_0x015f
        L_0x0158:
            r18 = r4
            r4 = r3
            r3 = r18
            goto L_0x015f
        L_0x015e:
            r4 = r3
        L_0x015f:
            javax.swing.JDesktopPane r2 = r0.desk
            java.awt.Dimension r2 = r2.getSize()
            int r6 = r2.width
            int r6 = r6 / r3
            int r2 = r2.height
            int r2 = r2 / r4
            r14 = 0
            r15 = 0
        L_0x016d:
            if (r14 >= r4) goto L_0x0209
            r13 = 0
            r16 = 0
        L_0x0172:
            if (r13 >= r3) goto L_0x0198
            int r8 = r14 * r3
            int r8 = r8 + r13
            int r9 = r1.length
            if (r8 < r9) goto L_0x017b
            goto L_0x0198
        L_0x017b:
            r9 = r1[r8]
            r9.setIcon(r7)     // Catch:{ Exception -> 0x0183 }
            r9.setMaximum(r7)     // Catch:{ Exception -> 0x0183 }
        L_0x0183:
            javax.swing.JDesktopPane r8 = r0.desk
            javax.swing.DesktopManager r8 = r8.getDesktopManager()
            r10 = r16
            r11 = r15
            r12 = r6
            r17 = r13
            r13 = r2
            r8.setBoundsForFrame(r9, r10, r11, r12, r13)
            int r16 = r16 + r6
            int r13 = r17 + 1
            goto L_0x0172
        L_0x0198:
            int r15 = r15 + r2
            int r14 = r14 + 1
            goto L_0x016d
        L_0x019c:
            java.lang.String r2 = "Cascade"
            boolean r2 = r1.equals(r2)
            if (r2 == 0) goto L_0x01dd
            javax.swing.JDesktopPane r1 = r0.desk
            javax.swing.JInternalFrame[] r1 = r1.getAllFrames()
            int r2 = r1.length
            javax.swing.JDesktopPane r3 = r0.desk
            int r3 = r3.getHeight()
            int r3 = r3 / r2
            r4 = 30
            if (r3 <= r4) goto L_0x01b8
            r3 = 30
        L_0x01b8:
            int r2 = r2 - r6
            r4 = 0
            r6 = 0
        L_0x01bb:
            if (r2 < 0) goto L_0x0209
            r9 = r1[r2]
            r9.setIcon(r7)     // Catch:{ Exception -> 0x01c5 }
            r9.setMaximum(r7)     // Catch:{ Exception -> 0x01c5 }
        L_0x01c5:
            java.awt.Dimension r8 = r9.getPreferredSize()
            int r12 = r8.width
            int r13 = r8.height
            javax.swing.JDesktopPane r8 = r0.desk
            javax.swing.DesktopManager r8 = r8.getDesktopManager()
            r10 = r4
            r11 = r6
            r8.setBoundsForFrame(r9, r10, r11, r12, r13)
            int r2 = r2 + -1
            int r4 = r4 + r3
            int r6 = r6 + r3
            goto L_0x01bb
        L_0x01dd:
            org.mozilla.javascript.tools.debugger.FileWindow r1 = r0.getFileWindow(r1)
            if (r1 == 0) goto L_0x0209
            org.mozilla.javascript.tools.debugger.FileWindow r1 = (org.mozilla.javascript.tools.debugger.FileWindow) r1
            boolean r2 = r1.isIcon()     // Catch:{ Exception -> 0x0209 }
            if (r2 == 0) goto L_0x01ee
            r1.setIcon(r7)     // Catch:{ Exception -> 0x0209 }
        L_0x01ee:
            r1.setVisible(r6)     // Catch:{ Exception -> 0x0209 }
            r1.moveToFront()     // Catch:{ Exception -> 0x0209 }
            r1.setSelected(r6)     // Catch:{ Exception -> 0x0209 }
            goto L_0x0209
        L_0x01f8:
            javax.swing.JInternalFrame r1 = r19.getSelectedFrame()
            if (r1 == 0) goto L_0x0209
            boolean r2 = r1 instanceof java.awt.event.ActionListener
            if (r2 == 0) goto L_0x0209
            java.awt.event.ActionListener r1 = (java.awt.event.ActionListener) r1
            r2 = r20
            r1.actionPerformed(r2)
        L_0x0209:
            r4 = -1
        L_0x020a:
            if (r4 == r5) goto L_0x0214
            r0.updateEnabled(r7)
            org.mozilla.javascript.tools.debugger.Dim r1 = r0.dim
            r1.setReturnValue(r4)
        L_0x0214:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.mozilla.javascript.tools.debugger.SwingGui.actionPerformed(java.awt.event.ActionEvent):void");
    }
}
