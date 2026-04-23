package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;

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

import model.Event;

import java.awt.Font;

public class events extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					events frame = new events();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public events() {
		setSize(new Dimension(640, 480));
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 30));

		JPanel header = new JPanel();
		header.setPreferredSize(new Dimension(9, 50));
		contentPane.add(header, BorderLayout.NORTH);
		header.setLayout(new BorderLayout(0, 15));
		header.setBorder(new EmptyBorder(10, 0, 0, 10));

		JLabel eventTitle = new JLabel("Eventos");
		eventTitle.setFont(new Font("FreeMono", Font.BOLD, 30));
		eventTitle.setHorizontalTextPosition(SwingConstants.CENTER);
		eventTitle.setHorizontalAlignment(SwingConstants.TRAILING);
		eventTitle.setPreferredSize(new Dimension(300, 15));
		header.add(eventTitle, BorderLayout.WEST);

		JButton closeButton = new JButton("X");
		closeButton.setForeground(new Color(255, 255, 255));
		closeButton.setFont(new Font("Dialog", Font.BOLD, 20));
		closeButton.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		closeButton.setBackground(new Color(224, 27, 36));
		closeButton.setPreferredSize(new Dimension(50, 50));
		header.add(closeButton, BorderLayout.EAST);

		JPanel mainPanel = new JPanel();
		mainPanel.setSize(new Dimension(500, 400));
		mainPanel.setPreferredSize(new Dimension(500, 400));
		contentPane.add(mainPanel, BorderLayout.CENTER);
		mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JScrollPane scrollPane = new JScrollPane();
		mainPanel.add(scrollPane);

		JList<Event> eventList = new JList<Event>();
		eventList.setBorder(new LineBorder(new Color(0, 0, 0)));
		eventList.setPreferredSize(new Dimension(250, 300));
		mainPanel.add(eventList);

		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(250, 300));
		mainPanel.add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 20));

		JLabel eventName = new JLabel("Nombre del evento aqui");
		eventName.setPreferredSize(new Dimension(150, 15));
		panel.add(eventName);

		JLabel eventDescription = new JLabel("Descripci\u00F3n del evento aqui");
		eventDescription.setPreferredSize(new Dimension(150, 15));
		panel.add(eventDescription);

		JButton inscriptionBtn = new JButton("Inscribirse");
		inscriptionBtn.setPreferredSize(new Dimension(150, 25));
		panel.add(inscriptionBtn);

		JButton uninscriptionBtn = new JButton("Desinscribirse");
		uninscriptionBtn.setPreferredSize(new Dimension(150, 25));
		panel.add(uninscriptionBtn);

	}

}
