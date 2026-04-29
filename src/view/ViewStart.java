package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import dao.DatabaseConnector;

public class ViewStart extends JFrame {

	private static final long serialVersionUID = 1L;
	private final int FRAME_WIDTH = 444;

	private JButton BTN_show_encounters;
	private JButton BTN_show_events;
	private JButton BTN_show_guests;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					ViewStart frame = new ViewStart();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ViewStart() {
		initialize();
	}

	private void initialize() {
		setTitle("Euskal Encounter (Menu de usuario)");
		setResizable(false);
		setBounds(100, 100, 460, 310);
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		getContentPane().setLayout(null);

		URL iconUrl = ViewStart.class.getResource("/assets/img_ee_icon.png");
		if (iconUrl != null) {
			setIconImage(Toolkit.getDefaultToolkit().getImage(iconUrl));
		}

		Box HBOX_TabTitleTop = Box.createHorizontalBox();
		HBOX_TabTitleTop.setOpaque(true);
		HBOX_TabTitleTop.setBounds(0, 0, FRAME_WIDTH, 50);
		HBOX_TabTitleTop.setBackground(new Color(0, 128, 255));
		getContentPane().add(HBOX_TabTitleTop);

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

		BTN_show_encounters = new JButton("Encuentros");
		BTN_show_encounters.setFont(new Font("Alef", Font.BOLD, 16));
		BTN_show_encounters.setPreferredSize(new Dimension(130, 35));
		PAN_lower_button_choices.add(BTN_show_encounters);

		BTN_show_events = new JButton("Eventos");
		BTN_show_events.setFont(new Font("Alef", Font.BOLD, 24));
		BTN_show_events.setPreferredSize(new Dimension(130, 35));
		PAN_lower_button_choices.add(BTN_show_events);

		BTN_show_guests = new JButton("Invitados");
		BTN_show_guests.setFont(new Font("Alef", Font.BOLD, 17));
		BTN_show_guests.setPreferredSize(new Dimension(130, 35));
		PAN_lower_button_choices.add(BTN_show_guests);

		getContentPane().add(PAN_lower_button_choices);

		JLabel IMG_bg_start_splash = new JLabel("");
		IMG_bg_start_splash.setBounds(0, 50, FRAME_WIDTH, 221);

		URL splashUrl = ViewStart.class.getResource("/assets/img_start_splash.jpg");
		if (splashUrl != null) {
			ImageIcon originalIcon = new ImageIcon(splashUrl);
			Image scaledImage = originalIcon.getImage().getScaledInstance(FRAME_WIDTH, 221, Image.SCALE_SMOOTH);
			IMG_bg_start_splash.setIcon(new ImageIcon(scaledImage));
		}

		getContentPane().add(IMG_bg_start_splash);
		getContentPane().setComponentZOrder(IMG_bg_start_splash, getContentPane().getComponentCount() - 1);
	}

	public JButton getBTN_show_encounters() {
		return BTN_show_encounters;
	}

	public JButton getBTN_show_events() {
		return BTN_show_events;
	}

	public JButton getBTN_show_guests() {
		return BTN_show_guests;
	}
}