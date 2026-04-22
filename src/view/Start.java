package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.Box;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Component;
import java.awt.Point;
import java.awt.Dimension;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Rectangle;

public class Start {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Start window = new Start();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Start() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		Box HBOX_TabTitleTop = Box.createHorizontalBox();
		HBOX_TabTitleTop.setMaximumSize(new Dimension(450, 0));
		HBOX_TabTitleTop.setOpaque(true);
		HBOX_TabTitleTop.setLocation(0, 0);
		HBOX_TabTitleTop.setPreferredSize(new Dimension(450, 50));
		HBOX_TabTitleTop.setSize(new Dimension(434, 50));
		HBOX_TabTitleTop.setBackground(new Color(0, 128, 255));
		frame.getContentPane().add(HBOX_TabTitleTop);
		
		JLabel LBL_TabTitle = new JLabel("Men\u00FA principal de usuario");
		LBL_TabTitle.setMaximumSize(new Dimension(450, 50));
		LBL_TabTitle.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 33));
		LBL_TabTitle.setMinimumSize(new Dimension(450, 50));
		LBL_TabTitle.setHorizontalAlignment(SwingConstants.CENTER);
		LBL_TabTitle.setHorizontalTextPosition(SwingConstants.CENTER);
		LBL_TabTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
		HBOX_TabTitleTop.add(LBL_TabTitle);
		
		JButton BTN_ShowEncounters = new JButton("Encuentros");
		BTN_ShowEncounters.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		BTN_ShowEncounters.setBounds(25, 65, 170, 35);
		frame.getContentPane().add(BTN_ShowEncounters);
		
		JButton BTN_ShowEvents = new JButton("Eventos");
		BTN_ShowEvents.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		BTN_ShowEvents.setBounds(225, 65, 170, 35);
		frame.getContentPane().add(BTN_ShowEvents);
		
		JButton BTN_ShowGuests = new JButton("Invitados");
		BTN_ShowGuests.setBounds(new Rectangle(80, 0, 0, 0));
		BTN_ShowGuests.setBounds(125, 110, 170, 35);
		frame.getContentPane().add(BTN_ShowGuests);
		
		//TODO
	}
}
