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

public class ViewEvents extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public ViewEvents() {
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
		header.setBorder(new EmptyBorder(10, 0, 0, 10));
		header.setLayout(null);

		JButton BTN = new JButton("\u2190");
		BTN.setBounds(10, 11, 75, 30);
		BTN.setOpaque(false);
		BTN.setForeground(new Color(0, 0, 0));
		BTN.setFont(new Font("Dialog", Font.BOLD, 20));
		BTN.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		BTN.setBackground(new Color(0, 0, 0));
		BTN.setPreferredSize(new Dimension(75, 30));
		header.add(BTN);

		JLabel eventTitle = new JLabel("Eventos");
		eventTitle.setBounds(216, 11, 159, 30);
		eventTitle.setBackground(new Color(0, 0, 0));
		eventTitle.setFont(new Font("FreeMono", Font.BOLD, 30));
		eventTitle.setHorizontalTextPosition(SwingConstants.CENTER);
		eventTitle.setHorizontalAlignment(SwingConstants.CENTER);
		eventTitle.setPreferredSize(new Dimension(400, 30));
		header.add(eventTitle);

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
