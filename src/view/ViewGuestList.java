package view;

import java.awt.*;
import javax.swing.border.*;
import javax.swing.*;
import java.awt.event.*;

public class ViewGuestList extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewGuestList frame = new ViewGuestList();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ViewGuestList() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		HBOX_TabTitleTop.setMaximumSize(new Dimension(450, 0));
		HBOX_TabTitleTop.setOpaque(true);
		HBOX_TabTitleTop.setLocation(0, 0);
		HBOX_TabTitleTop.setPreferredSize(new Dimension(450, 50));
		HBOX_TabTitleTop.setSize(new Dimension(436, 50));
		HBOX_TabTitleTop.setBackground(new Color(0, 128, 255));
		contentPane.add(HBOX_TabTitleTop);
		
		
		
		JButton btnNewButton = new JButton("\u2190");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		HBOX_TabTitleTop.add(btnNewButton);
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JLabel LabelTitle = new JLabel("  Invitados");
		HBOX_TabTitleTop.add(LabelTitle);
		LabelTitle.setMaximumSize(new Dimension(450, 50));
		LabelTitle.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 33));
		LabelTitle.setMinimumSize(new Dimension(450, 50));
	}
}
