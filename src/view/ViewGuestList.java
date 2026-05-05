package view;

import java.awt.Color;
import java.awt.Font;
import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

public class ViewGuestList extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JButton BTN_back;

    public ViewGuestList() {
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        setResizable(false);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        DefaultListModel<String> ListModel = new DefaultListModel<>();
        JList<String> List = new JList<>(ListModel);

        JScrollPane scrollPane = new JScrollPane(List);
        scrollPane.setBounds(10, 63, 416, 190);
        contentPane.add(scrollPane);

        Box HBOX_TabTitleTop = Box.createHorizontalBox();
        HBOX_TabTitleTop.setOpaque(true);
        HBOX_TabTitleTop.setBounds(0, 0, 436, 50);
        HBOX_TabTitleTop.setBackground(new Color(0, 128, 255));
        contentPane.add(HBOX_TabTitleTop);

        BTN_back = new JButton("\u2190");
        BTN_back.setFont(new Font("Tahoma", Font.PLAIN, 18));
        HBOX_TabTitleTop.add(BTN_back);

        JLabel LabelTitle = new JLabel("  Invitados");
        LabelTitle.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 33));
        HBOX_TabTitleTop.add(LabelTitle);
    }

    public JButton getBTN_back() {
        return BTN_back;
    }
}