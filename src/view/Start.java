package view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.Box;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Image;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import java.awt.Toolkit;

public class Start {

	private JFrame frmEuskalEncountermenu;
	private final int BUTTON_FONT_SIZE = 24;
	private final Font BUTTON_FONT = new Font("Gabriola", Font.BOLD, BUTTON_FONT_SIZE);

	private final int FRAME_WIDTH = 444;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Start window = new Start();
					window.frmEuskalEncountermenu.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Start() {
		initialize();
	}

	private void initialize() {
		frmEuskalEncountermenu = new JFrame();
		frmEuskalEncountermenu.setTitle("Euskal Encounter (Menu de usuario)");
		frmEuskalEncountermenu
				.setIconImage(Toolkit.getDefaultToolkit().getImage(Start.class.getResource("/assets/img_ee_icon.png")));
		frmEuskalEncountermenu.setResizable(false);
		frmEuskalEncountermenu.setBounds(100, 100, 460, 310);
		frmEuskalEncountermenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmEuskalEncountermenu.getContentPane().setLayout(null);

		Box HBOX_TabTitleTop = Box.createHorizontalBox();
		HBOX_TabTitleTop.setOpaque(true);
		HBOX_TabTitleTop.setBounds(0, 0, FRAME_WIDTH, 50);
		HBOX_TabTitleTop.setBackground(new Color(0, 128, 255));
		frmEuskalEncountermenu.getContentPane().add(HBOX_TabTitleTop);

		JLabel LBL_TabTitle = new JLabel("Menú principal de usuario");
		LBL_TabTitle.setFont(new Font("Alef", Font.BOLD, 33));
		LBL_TabTitle.setHorizontalAlignment(SwingConstants.CENTER);
		LBL_TabTitle.setVerticalAlignment(SwingConstants.CENTER);
		LBL_TabTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
		LBL_TabTitle.setPreferredSize(new Dimension(FRAME_WIDTH, 50));
		LBL_TabTitle.setMaximumSize(new Dimension(FRAME_WIDTH, 50));
		HBOX_TabTitleTop.add(LBL_TabTitle);

		JPanel PAN_lower_button_choices = new JPanel();
		PAN_lower_button_choices.setOpaque(false);
		PAN_lower_button_choices.setBounds(0, 211, FRAME_WIDTH, 50);
		PAN_lower_button_choices.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JButton BTN_show_encounters = new JButton("Encuentros");
		BTN_show_encounters.setFont(new Font("Alef", Font.BOLD, 16));
		BTN_show_encounters.setPreferredSize(new Dimension(130, 35));
		PAN_lower_button_choices.add(BTN_show_encounters);

		JButton BTN_show_events = new JButton("Eventos");
		BTN_show_events.setFont(new Font("Alef", Font.BOLD, 24));
		BTN_show_events.setPreferredSize(new Dimension(130, 35));
		PAN_lower_button_choices.add(BTN_show_events);

		JButton BTN_show_guests = new JButton("Invitados");
		BTN_show_guests.setFont(new Font("Alef", Font.BOLD, 17));
		BTN_show_guests.setPreferredSize(new Dimension(130, 35));
		PAN_lower_button_choices.add(BTN_show_guests);

		frmEuskalEncountermenu.getContentPane().add(PAN_lower_button_choices);

		JLabel IMG_bg_start_splash = new JLabel("");
		IMG_bg_start_splash.setBounds(0, 50, FRAME_WIDTH, 221);

		ImageIcon originalIcon = new ImageIcon(Start.class.getResource("/assets/img_start_splash.jpg"));
		Image scaledImage = originalIcon.getImage().getScaledInstance(FRAME_WIDTH, 221, Image.SCALE_SMOOTH);
		IMG_bg_start_splash.setIcon(new ImageIcon(scaledImage));

		frmEuskalEncountermenu.getContentPane().add(IMG_bg_start_splash);
	}
}