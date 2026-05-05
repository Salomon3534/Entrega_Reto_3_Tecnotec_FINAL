package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import model.Guest;

public class ViewGuestList extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton BTN_back;
	private JList<Guest> guestList;
	private DefaultListModel<Guest> listModel;
	private JLabel guestName;
	private JLabel guestEmail;

	public ViewGuestList() {
		setSize(new Dimension(640, 480));
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel header = new JPanel();
		header.setBackground(new Color(53, 132, 228));
		header.setPreferredSize(new Dimension(9, 50));
		contentPane.add(header, BorderLayout.NORTH);
		header.setLayout(null);

		BTN_back = new JButton("←");
		BTN_back.setBounds(10, 11, 75, 30);
		BTN_back.setFont(new Font("Dialog", Font.BOLD, 20));
		BTN_back.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		header.add(BTN_back);

		JLabel guestTitle = new JLabel("Invitados");
		guestTitle.setBounds(216, 11, 159, 30);
		guestTitle.setFont(new Font("FreeMono", Font.BOLD, 30));
		guestTitle.setHorizontalAlignment(SwingConstants.CENTER);
		header.add(guestTitle);

		JPanel mainPanel = new JPanel();
		contentPane.add(mainPanel, BorderLayout.CENTER);
		mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		listModel = new DefaultListModel<>();
		guestList = new JList<>(listModel);
		JScrollPane scrollPane = new JScrollPane(guestList);
		scrollPane.setPreferredSize(new Dimension(250, 300));
		mainPanel.add(scrollPane);

		JPanel infoPanel = new JPanel();
		infoPanel.setPreferredSize(new Dimension(250, 300));
		infoPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 20));
		mainPanel.add(infoPanel);

		guestName = new JLabel("Selecciona un invitado");
		guestName.setPreferredSize(new Dimension(200, 15));
		infoPanel.add(guestName);

		guestEmail = new JLabel("");
		guestEmail.setPreferredSize(new Dimension(200, 15));
		infoPanel.add(guestEmail);

		JButton viewButton = new JButton("Ver Detalles");
		viewButton.setPreferredSize(new Dimension(150, 25));
		infoPanel.add(viewButton);

		JButton editButton = new JButton("Editar");
		editButton.setPreferredSize(new Dimension(150, 25));
		infoPanel.add(editButton);
	}

	public JButton getBTN_back() {
		return BTN_back;
	}

	public JList<Guest> getGuestList() {
		return guestList;
	}

	public DefaultListModel<Guest> getListModel() {
		return listModel;
	}

	public JLabel getGuestNameLabel() {
		return guestName;
	}

	public JLabel getGuestEmailLabel() {
		return guestEmail;
	}
}