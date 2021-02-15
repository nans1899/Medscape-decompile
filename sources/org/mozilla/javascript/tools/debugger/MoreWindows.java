package org.mozilla.javascript.tools.debugger;

import androidx.recyclerview.widget.ItemTouchHelper;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import org.jetbrains.anko.DimensionsKt;

/* compiled from: SwingGui */
class MoreWindows extends JDialog implements ActionListener {
    private static final long serialVersionUID = 5177066296457377546L;
    private JButton cancelButton = new JButton("Cancel");
    private JList list;
    /* access modifiers changed from: private */
    public JButton setButton = new JButton("Select");
    private SwingGui swingGui;
    /* access modifiers changed from: private */
    public String value;

    MoreWindows(SwingGui swingGui2, Map<String, FileWindow> map, String str, String str2) {
        super(swingGui2, str, true);
        this.swingGui = swingGui2;
        this.cancelButton.addActionListener(this);
        this.setButton.addActionListener(this);
        getRootPane().setDefaultButton(this.setButton);
        JList jList = new JList(new DefaultListModel());
        this.list = jList;
        DefaultListModel model = jList.getModel();
        model.clear();
        for (String addElement : map.keySet()) {
            model.addElement(addElement);
        }
        this.list.setSelectedIndex(0);
        this.setButton.setEnabled(true);
        this.list.setSelectionMode(1);
        this.list.addMouseListener(new MouseHandler());
        JScrollPane jScrollPane = new JScrollPane(this.list);
        jScrollPane.setPreferredSize(new Dimension(DimensionsKt.XHDPI, DimensionsKt.HDPI));
        jScrollPane.setMinimumSize(new Dimension(ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION, 80));
        jScrollPane.setAlignmentX(0.0f);
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new BoxLayout(jPanel, 1));
        JLabel jLabel = new JLabel(str2);
        jLabel.setLabelFor(this.list);
        jPanel.add(jLabel);
        jPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        jPanel.add(jScrollPane);
        jPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JPanel jPanel2 = new JPanel();
        jPanel2.setLayout(new BoxLayout(jPanel2, 0));
        jPanel2.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
        jPanel2.add(Box.createHorizontalGlue());
        jPanel2.add(this.cancelButton);
        jPanel2.add(Box.createRigidArea(new Dimension(10, 0)));
        jPanel2.add(this.setButton);
        Container contentPane = getContentPane();
        contentPane.add(jPanel, "Center");
        contentPane.add(jPanel2, "South");
        pack();
        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent keyEvent) {
                if (keyEvent.getKeyCode() == 27) {
                    keyEvent.consume();
                    String unused = MoreWindows.this.value = null;
                    MoreWindows.this.setVisible(false);
                }
            }
        });
    }

    public String showDialog(Component component) {
        this.value = null;
        setLocationRelativeTo(component);
        setVisible(true);
        return this.value;
    }

    public void actionPerformed(ActionEvent actionEvent) {
        String actionCommand = actionEvent.getActionCommand();
        if (actionCommand.equals("Cancel")) {
            setVisible(false);
            this.value = null;
        } else if (actionCommand.equals("Select")) {
            this.value = (String) this.list.getSelectedValue();
            setVisible(false);
            this.swingGui.showFileWindow(this.value, -1);
        }
    }

    /* compiled from: SwingGui */
    private class MouseHandler extends MouseAdapter {
        private MouseHandler() {
        }

        public void mouseClicked(MouseEvent mouseEvent) {
            if (mouseEvent.getClickCount() == 2) {
                MoreWindows.this.setButton.doClick();
            }
        }
    }
}
