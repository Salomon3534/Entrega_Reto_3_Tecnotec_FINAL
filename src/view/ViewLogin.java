package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.net.URL;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

public class ViewLogin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private JButton loginButton;
	private JLabel messageLabel;

	public ViewLogin() {
		initialize();
	}

	private void initialize() {
		setTitle("Euskal Encounter - Login");
		setResizable(false);
		setBounds(100, 100, 416, 339);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		URL iconUrl = ViewLogin.class.getResource("/assets/img_ee_icon.png");
		if (iconUrl != null) {
			setIconImage(Toolkit.getDefaultToolkit().getImage(iconUrl));
		}

		JPanel headerPanel = new JPanel();
		headerPanel.setBackground(new Color(0, 128, 255));
		headerPanel.setPreferredSize(new Dimension(400, 60));
		headerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

		JLabel titleLabel = new JLabel("Iniciar Sesión");
		titleLabel.setFont(new Font("Alef", Font.BOLD, 28));
		titleLabel.setForeground(Color.WHITE);
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		headerPanel.add(titleLabel);

		JPanel mainPanel = new JPanel();
		mainPanel.setBackground(Color.WHITE);
		mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 10));
		mainPanel.setPreferredSize(new Dimension(400, 200));

		JLabel usernameLabel = new JLabel("Usuario:");
		usernameLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		usernameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		usernameLabel.setPreferredSize(new Dimension(350, 20));
		mainPanel.add(usernameLabel);

		usernameField = new JTextField();
		usernameField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		usernameField.setPreferredSize(new Dimension(350, 30));
		mainPanel.add(usernameField);

		JLabel passwordLabel = new JLabel("Contraseña:");
		passwordLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		passwordLabel.setHorizontalAlignment(SwingConstants.CENTER);
		passwordLabel.setPreferredSize(new Dimension(350, 20));
		mainPanel.add(passwordLabel);

		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		passwordField.setPreferredSize(new Dimension(350, 30));
		mainPanel.add(passwordField);

		messageLabel = new JLabel("");
		messageLabel.setFont(new Font("Tahoma", Font.PLAIN, 10));
		messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
		messageLabel.setForeground(new Color(255, 0, 0));
		messageLabel.setPreferredSize(new Dimension(350, 20));
		mainPanel.add(messageLabel);

		loginButton = new JButton("Ingresar");
		loginButton.setFont(new Font("Alef", Font.BOLD, 13));
		loginButton.setPreferredSize(new Dimension(150, 35));
		loginButton.setHorizontalAlignment(SwingConstants.CENTER);
		loginButton.setBackground(new Color(0, 128, 255));
		loginButton.setForeground(Color.WHITE);
		loginButton.setFocusPainted(false);
		mainPanel.add(loginButton);

		getContentPane().add(headerPanel, "North");
		getContentPane().add(mainPanel, "Center");
	}

	public String getUsername() {
		return usernameField.getText();
	}

	public String getPassword() {
		return new String(passwordField.getPassword());
	}

	public void clearFields() {
		usernameField.setText("");
		passwordField.setText("");
	}

	public void showMessage(String message) {
		messageLabel.setText(message);
	}

	public void clearMessage() {
		messageLabel.setText("");
	}

	public JButton getLoginButton() {
		return loginButton;
	}
}
