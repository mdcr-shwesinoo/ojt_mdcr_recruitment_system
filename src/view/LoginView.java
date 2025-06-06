package view;

import java.awt.*;
import javax.swing.*;
import config.Common;
import config.Checking;
import controller.UserAccountController;
import model.ApplicantModel;
import model.UserAccountModel;

import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginView extends JFrame {
	Common common = new Common();
	int id;
	private JTextField txtEmail;
	private JLabel lblEmailError;
	private JLabel lblPasswordError;
	private JLabel lblError;
	JButton btnLogin;
	private JPasswordField txtPassword;
	private ImageIcon iconShow, iconHide;
	String emailPlaceholder = "Enter your email";
	String pwdPlaceholder = "Enter your password";

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				LoginView frame = new LoginView();
				frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Full screen
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public LoginView() {
		setTitle("Myanmar DCR Recruitment System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());

		// Wrapper panel to center the main panel
		JPanel wrapper = new JPanel(new GridBagLayout()); // This will center content
//
//		// Add wrapper to center of frame
		getContentPane().add(wrapper, BorderLayout.CENTER);
		wrapper.setBackground(new Color(240, 240, 240)); // Optional: background color

		// Main panel with your form
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(650, 410));
		panel.setBackground(SystemColor.inactiveCaption);

		JPanel panelMenu = new JPanel();
		panelMenu.setBackground(new Color(0, 0, 0));
		panelMenu.setPreferredSize(new Dimension(450, 50));
		panelMenu.setLayout(null);
		getContentPane().add(panelMenu, BorderLayout.NORTH);

		ImageIcon titleImage = new ImageIcon(getClass().getResource("/image/mdcr3.png"));
		JLabel imageTitleLabel = new JLabel(titleImage);
		imageTitleLabel.setBounds(25, 0, 141, 50);
		panelMenu.add(imageTitleLabel);

		JLabel lblNewLabel = new JLabel("Myanmar DCR Recruitment System");
		lblNewLabel.setBounds(216, 10, 340, 30);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("MS UI Gothic", Font.PLAIN, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		
		panelMenu.add(lblNewLabel);

		// Profile logo
		ImageIcon profileIcon = new ImageIcon("../ojt_mdcr_recruitment_system/src/image/user2.png");
		JLabel profileLabel = new JLabel(profileIcon);
		profileLabel.setBounds(1300, 0, 50, 50);
		profileLabel.setVisible(true);

		panelMenu.add(profileLabel);

		// Load the image and create a JLabel
		ImageIcon imageIcon = new ImageIcon(getClass().getResource("/image/account.png"));
		panel.setLayout(null);
		JLabel imageLabel = new JLabel(imageIcon);
		imageLabel.setBounds(278, 21, imageIcon.getIconWidth(), imageIcon.getIconHeight());
		panel.add(imageLabel);

		JLabel lblSignIn = new JLabel("Sign In");
		lblSignIn.setFont(new Font("MS UI Gothic", Font.PLAIN, 14));
		lblSignIn.setBounds(300, 95, 55, 32);
		panel.add(lblSignIn);

		btnLogin = Common.addActionButton("Login");
		btnLogin.setBounds(220, 287, 200, 32);

		btnLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String email = txtEmail.getText();
				String password = txtPassword.getText();
				String errorMessage = "";

				if (Checking.IsEmpty(email) || txtEmail.getText().equals(emailPlaceholder)) {
					errorMessage = "Email is required";
					lblEmailError.setText(errorMessage);
					lblError.setText("");
					lblEmailError.setForeground(Color.RED);
				} else {
					lblEmailError.setText("");
				}
				if (Checking.IsEmpty(password) || txtPassword.getText().equals(pwdPlaceholder)) {
					errorMessage = "Password is required";
					lblPasswordError.setText(errorMessage);
					lblError.setText("");
					lblPasswordError.setForeground(Color.RED);
				} else {
					lblPasswordError.setText("");
				}

				if (errorMessage.isEmpty()) {
					UserAccountController uc = new UserAccountController();
					UserAccountModel um = new UserAccountModel();
					um.setEmail(email);
					um.setPassword(password);

					String userEmail = email;
					String userPassword = password;
					boolean exist = uc.emailExists(userEmail);
					boolean passwordCheck = uc.checkPassword(userEmail, userPassword);

					try {
						id = uc.getID(userEmail);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					// System.out.println(exist);
					if (!exist || !passwordCheck) {
						errorMessage = "Email or Password Incorrect.";
						lblError.setText(errorMessage);
						lblEmailError.setText("");
						lblPasswordError.setText("");
						lblError.setForeground(Color.RED);
					} else if (exist) {
						String rs = uc.searchUserAccount(um);
						if (!passwordCheck) {
							lblPasswordError.setText("Password is incorrect");
						} else if (rs.equals("Admin")) {
							ApplicantListView appView = new ApplicantListView(id);
							dispose();
							appView.setExtendedState(JFrame.MAXIMIZED_BOTH);
							appView.setVisible(true);
						} else {
							ApplyFormView applyView = new ApplyFormView(id);
							dispose();
							applyView.setExtendedState(JFrame.MAXIMIZED_BOTH);
							applyView.setVisible(true);
						}

					} else {
						lblEmailError.setText("Email not found in the system.");
					}
				}

			}
		});
		panel.add(btnLogin);

		// Add your main panel into the center of wrapper
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		wrapper.add(panel, gbc_panel);

		lblEmailError = new JLabel("");
		lblEmailError.setBounds(427, 131, 290, 28);
		lblEmailError.setForeground(Color.RED);
		panel.add(lblEmailError);

		lblPasswordError = new JLabel("");
		lblPasswordError.setBounds(427, 190, 290, 28);
		lblPasswordError.setForeground(Color.RED);
		panel.add(lblPasswordError);

		lblError = new JLabel("");
		
		lblError.setBounds(220, 240, 255, 13);
		lblError.setForeground(Color.RED);
		panel.add(lblError);

		txtEmail = new JTextField();
		txtEmail.setBounds(220, 131, 200, 30);
		txtEmail.setBorder(BorderFactory.createEmptyBorder());
		common.setPlaceholder(txtEmail, emailPlaceholder);
		panel.add(txtEmail);

		// Container panel for password field and eye icon button
		JPanel passwordPanel = new JPanel();
		passwordPanel.setBounds(220, 190, 200, 30);
		passwordPanel.setLayout(new BorderLayout());
		panel.add(passwordPanel);

		//Password Field
		txtPassword = new JPasswordField();
		common.setPlaceholder(txtPassword, pwdPlaceholder);
		txtPassword.setBorder(BorderFactory.createEmptyBorder());
		passwordPanel.add(txtPassword, BorderLayout.CENTER);
		txtPassword.setEchoChar('*');
		// Eye icon button for toggling visibility
		JButton btnToggle = new JButton();
		btnToggle.setPreferredSize(new Dimension(40, 30));
		btnToggle.setFocusPainted(false);
		btnToggle.setContentAreaFilled(false);
		btnToggle.setBorderPainted(false);

		iconShow = new ImageIcon(getClass().getResource("/image/view.png"));
		iconHide = new ImageIcon(getClass().getResource("/image/hide.png"));

		// Resize icons
		iconShow = new ImageIcon(iconShow.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
		iconHide = new ImageIcon(iconHide.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
		btnToggle.setIcon(iconHide); // initially password hidden

		btnToggle.addActionListener(e -> {
			if (txtPassword.getEchoChar() != (char) 0) {
				txtPassword.setEchoChar((char) 0); // show password
				btnToggle.setIcon(iconShow);
			} else {
				txtPassword.setEchoChar('*'); // hide password
				btnToggle.setIcon(iconHide);
			}
		});

		passwordPanel.add(btnToggle, BorderLayout.EAST);

		

		JLabel lblText = new JLabel("Don't have an account?");
		lblText.setBounds(220, 338, 140, 13);
		panel.add(lblText);

		JLabel lblForgetPassword = new JLabel("Forgot Password?");
		lblForgetPassword.setCursor(new Cursor(Cursor.HAND_CURSOR));
		lblForgetPassword.setBounds(320, 263, 127, 13);
		lblForgetPassword.setForeground(Color.BLUE); 
		lblForgetPassword.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ForgetPasswordView forgetView = new ForgetPasswordView();
				// forgetView.setExtendedState(JFrame.MAXIMIZED_BOTH);
				forgetView.setVisible(true);
			}
		});
		panel.add(lblForgetPassword);

		JLabel lblRegister = new JLabel("Register ");
		lblRegister.setCursor(new Cursor(Cursor.HAND_CURSOR));
		lblRegister.setBounds(365, 338, 89, 13);
		lblRegister.setForeground(Color.BLUE); 
		lblRegister.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				SignUpView signUpView = new SignUpView();
				dispose();
				signUpView.setExtendedState(JFrame.MAXIMIZED_BOTH);
				signUpView.setVisible(true);
			}
		});
		panel.add(lblRegister);
	}
	
}
