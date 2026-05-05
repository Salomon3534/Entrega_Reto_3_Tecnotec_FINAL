package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class ViewEventDetailed extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton BTN_back, BTN_action, BTN_cancel;
	private JLabel lblTitle, lblLocation, lblDates, lblSpecificInfo;
	private JTextArea txtDescription;

	public ViewEventDetailed() {
		setSize(new Dimension(500, 600));
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel header = new JPanel();
		header.setBackground(new Color(53, 132, 228));
		header.setPreferredSize(new Dimension(9, 60));
		header.setLayout(null);
		contentPane.add(header, BorderLayout.NORTH);

		BTN_back = new JButton("←");
		BTN_back.setBounds(10, 15, 60, 30);
		BTN_back.setFont(new Font("Dialog", Font.BOLD, 20));
		BTN_back.setBorder(new LineBorder(Color.BLACK, 1, true));
		header.add(BTN_back);

		lblTitle = new JLabel("Nombre del Evento");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("FreeMono", Font.BOLD, 22));
		lblTitle.setBounds(80, 15, 330, 30);
		header.add(lblTitle);

		JPanel mainPanel = new JPanel();
		mainPanel.setBackground(Color.WHITE);
		mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 20));
		contentPane.add(mainPanel, BorderLayout.CENTER);

		lblLocation = new JLabel("Ubicación: ---");
		lblLocation.setPreferredSize(new Dimension(450, 20));
		lblLocation.setFont(new Font("Tahoma", Font.BOLD, 14));
		mainPanel.add(lblLocation);

		lblDates = new JLabel("Fecha: --- | Horario: ---");
		lblDates.setPreferredSize(new Dimension(450, 20));
		mainPanel.add(lblDates);

		txtDescription = new JTextArea("");
		txtDescription.setEditable(false);
		txtDescription.setLineWrap(true);
		txtDescription.setWrapStyleWord(true);
		txtDescription.setPreferredSize(new Dimension(450, 150));
		txtDescription.setBorder(new LineBorder(Color.LIGHT_GRAY));
		mainPanel.add(txtDescription);

		lblSpecificInfo = new JLabel("");
		lblSpecificInfo.setForeground(new Color(53, 132, 228));
		lblSpecificInfo.setFont(new Font("Tahoma", Font.ITALIC, 13));
		lblSpecificInfo.setPreferredSize(new Dimension(450, 30));
		mainPanel.add(lblSpecificInfo);

		JPanel footer = new JPanel();
		footer.setBackground(Color.WHITE);
		footer.setPreferredSize(new Dimension(10, 80));
		contentPane.add(footer, BorderLayout.SOUTH);

		BTN_action = new JButton("Inscribirse");
		BTN_action.setPreferredSize(new Dimension(180, 40));
		BTN_action.setBackground(new Color(53, 132, 228));
		BTN_action.setForeground(Color.WHITE);
		BTN_action.setFont(new Font("Tahoma", Font.BOLD, 13));
		footer.add(BTN_action);

		BTN_cancel = new JButton("Anular Inscripción");
		BTN_cancel.setPreferredSize(new Dimension(180, 40));
		footer.add(BTN_cancel);
	}

	public JButton getBTN_back() {
		return BTN_back;
	}

	public JButton getBTN_action() {
		return BTN_action;
	}

	public JButton getBTN_cancel() {
		return BTN_cancel;
	}

	public JLabel getLblTitle() {
		return lblTitle;
	}

	public JLabel getLblLocation() {
		return lblLocation;
	}

	public JLabel getLblDates() {
		return lblDates;
	}

	public JLabel getLblSpecificInfo() {
		return lblSpecificInfo;
	}

	public JTextArea getTxtDescription() {
		return txtDescription;
	}
}