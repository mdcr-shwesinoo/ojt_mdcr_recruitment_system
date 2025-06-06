package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import config.Common;
import controller.UserAccountController;
import model.UserAccountModel;

public class SignUpView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtName;
	private JTextField txtEmail;
	private JPasswordField txtPassword;
	JLabel lblErrorMessage, lblErrorName, lblErrorEmail, lblErrorPassword;
	private ImageIcon iconShow, iconHide;
	UserAccountModel userAccountModel = new UserAccountModel();
	UserAccountController userAccountController = new UserAccountController();
	Common common = new Common();

	String placeHolderName = "Enter your name ";
	String placeHolderEmail = "Enter your email ";
	String placeHolderPassword = "Enter your password ";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignUpView signUpFrame = new SignUpView();
					signUpFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
					signUpFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */

	public SignUpView() {
		setTitle("Myanmar DCR Recruitment System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());

		// Create a wrapper panel with GridBagLayout
		JPanel wrapper = new JPanel(new GridBagLayout());

		// Create the content pane and set its preferred size
		contentPane = new JPanel();
		contentPane.setPreferredSize(new Dimension(650, 450));
		contentPane.setBackground(SystemColor.inactiveCaption);
		contentPane.setLayout(null);

		// Custom title bar panel (black bar)
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 0));
		panel.setPreferredSize(new Dimension(450, 50));
		getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(null);

		ImageIcon titleImage = new ImageIcon(getClass().getResource("/image/mdcr3.png"));
		JLabel imageTitleLabel = new JLabel(titleImage);
		imageTitleLabel.setBounds(25, 0, 141, 50);
		panel.add(imageTitleLabel);

		JLabel lblTitleBar = new JLabel("Myanmar DCR Recruitment System");
		lblTitleBar.setForeground(Color.WHITE);
		lblTitleBar.setFont(new Font("MS UI Gothic", Font.PLAIN, 20));
		lblTitleBar.setBounds(216, 10, 340, 30);
		panel.add(lblTitleBar);

		// Load the image and create a JLabel
		ImageIcon imageIcon = new ImageIcon(getClass().getResource("/image/account.png"));
		JLabel imageLabel = new JLabel(imageIcon);
		imageLabel.setBounds(278, 21, imageIcon.getIconWidth(), imageIcon.getIconHeight());

		contentPane.add(imageLabel);

		// Add components to the content pane
		JLabel lblSignUp = new JLabel("Sign Up");
		lblSignUp.setFont(new Font("MS UI Gothic", Font.PLAIN, 14));
		lblSignUp.setBounds(300, 95, 55, 32);
		contentPane.add(lblSignUp);

		txtName = new JTextField();
		txtName.setToolTipText("");
		txtName.setBounds(220, 137, 200, 30);
		contentPane.add(txtName);
		txtName.setBorder(BorderFactory.createEmptyBorder());
		txtName.setColumns(10);

		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(220, 186, 200, 30);
		txtEmail.setBorder(BorderFactory.createEmptyBorder());
		contentPane.add(txtEmail);

		// Container panel for password field and eye icon button
		JPanel passwordPanel = new JPanel();
		passwordPanel.setBounds(220, 237, 200, 30);
		passwordPanel.setLayout(new BorderLayout());
		contentPane.add(passwordPanel);

		// Password field
		txtPassword = new JPasswordField();
		txtPassword.setEchoChar('*');
		txtPassword.setBorder(BorderFactory.createEmptyBorder());
		passwordPanel.add(txtPassword, BorderLayout.CENTER);

		// Eye icon button for toggling visibility
		JButton btnToggle = new JButton();
		btnToggle.setPreferredSize(new Dimension(40, 30));
		btnToggle.setFocusPainted(false);
		btnToggle.setContentAreaFilled(false);
		btnToggle.setBorderPainted(false);

		// Load icons - replace these paths with your icon files
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

		lblErrorMessage = new JLabel("");
		lblErrorMessage.setBounds(220, 277, 200, 21);
		contentPane.add(lblErrorMessage);

		lblErrorName = new JLabel("");
		lblErrorName.setBounds(427, 139, 290, 28);
		lblErrorName.setForeground(Color.RED);
		contentPane.add(lblErrorName);

		lblErrorEmail = new JLabel("");
		lblErrorEmail.setBounds(427, 188, 290, 28);
		lblErrorEmail.setForeground(Color.RED);
		contentPane.add(lblErrorEmail);

		lblErrorPassword = new JLabel("");
		lblErrorPassword.setBounds(427, 239, 290, 28);
		lblErrorPassword.setForeground(Color.RED);
		contentPane.add(lblErrorPassword);

		JButton btnSignUp = Common.addActionButton("Sign Up");
		btnSignUp.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String userName = txtName.getText().trim();
				String email = txtEmail.getText().trim();
				String password = txtPassword.getText().trim();
				String errorMessage = "";

				// Validate all bank
//				if ((common.IsEmpty(userName) && common.IsEmpty(email) && common.IsEmpty(password))
//						|| (txtName.getText().equals(placeHolderName) && txtEmail.getText().equals(placeHolderEmail)
//						&& txtPassword.getText().equals(placeHolderPassword))) {
//					errorMessage = "All fields are blank!\n";
//					lblErrorMessage.setText(errorMessage);
//					lblErrorMessage.setForeground(Color.RED);
//					lblErrorName.setText("");
//					lblErrorEmail.setText("");
//					lblErrorPassword.setText("");
//					return;
//				}

				// Validate Name
				if (common.IsEmpty(txtName.getText()) || txtName.getText().equals(placeHolderName)) {
					errorMessage = "Name is required!";
					lblErrorMessage.setText("");
					lblErrorName.setText(errorMessage);
				} else if (!common.validateUsername(userName)) {
					errorMessage = "Username should only contain letters.";
					lblErrorName.setText(errorMessage);
					lblErrorMessage.setText("");
				} else {
					lblErrorName.setText("");
				}

				// Validate Email
				if (common.IsEmpty(txtEmail.getText()) || txtEmail.getText().equals(placeHolderEmail)) {
					errorMessage = "Email is required!";
					lblErrorMessage.setText("");
					lblErrorEmail.setText(errorMessage);
				} else if (!common.validateEmail(email)) {
					errorMessage = "Invalid email address.";
					lblErrorEmail.setText(errorMessage);
					lblErrorMessage.setText("");
				} else {
					lblErrorEmail.setText("");
				}
//					try {
//						userAccountModel.setEmail(email);
//						if (userAccountController.isduplicate(userAccountModel)) {
//							lblErrorEmail.setText("There is a duplicate email");
//							txtEmail.requestFocus(true);
//							txtEmail.selectAll();
//							lblErrorMessage.setText("");
//						} else {
//							lblErrorEmail.setText("");
//						}
//					} catch (SQLException e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					}

				// Validate Password
				if (common.IsEmpty(txtPassword.getText()) || txtPassword.getText().equals(placeHolderPassword)) {
					errorMessage = "Password is required!";
					lblErrorMessage.setText("");
					lblErrorPassword.setText(errorMessage);
				} else if (!common.validatePassword(password)) {
					errorMessage = "<html>Password must have length 7 <br>and a special character.</html>";
					lblErrorPassword.setText(errorMessage);
					lblErrorMessage.setText("");
				} else {
					lblErrorPassword.setText("");
				}

				// Only proceed if there are no empty fields
				if (errorMessage.isEmpty()) {
					userAccountModel.setName(userName);
					userAccountModel.setEmail(email);
					userAccountModel.setPassword(password);
					
					try {
						if (userAccountController.isduplicate(userAccountModel)) {
							lblErrorEmail.setText("There is a duplicate email");
							txtEmail.requestFocus(true);
							txtEmail.selectAll();
						} else {
							int rs = userAccountController.insert(userAccountModel);
							if (rs == 1) {
								dispose();
								SignUpSuccessView signUpSuccessView = new SignUpSuccessView();
								signUpSuccessView.setExtendedState(JFrame.MAXIMIZED_BOTH);
								signUpSuccessView.setVisible(true);
								clear();
							}

						}
					} catch (HeadlessException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btnSignUp.setBounds(220, 304, 200, 32);
		contentPane.add(btnSignUp);

		// Add the content pane to the wrapper
		wrapper.add(contentPane);

		// Add the wrapper to the main content pane
		getContentPane().add(wrapper, BorderLayout.CENTER);

		// Set the custom UI to display placeholder
		common.setPlaceholder(txtName, placeHolderName);
		common.setPlaceholder(txtEmail, placeHolderEmail);
		common.setPlaceholder(txtPassword, placeHolderPassword);
		
		JLabel lblNewLabel = new JLabel("Already have an account?");
		lblNewLabel.setBounds(220, 360, 150, 13);
		contentPane.add(lblNewLabel);
		
		JLabel lblSignIn = new JLabel("Sign In");
		lblSignIn.setForeground(Color.BLUE);
		lblSignIn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		lblSignIn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				LoginView loginView = new LoginView();
				loginView.setExtendedState(JFrame.MAXIMIZED_BOTH);
				loginView.setVisible(true);
				dispose();
			}
		});
		lblSignIn.setBounds(375, 360, 45, 13);
		contentPane.add(lblSignIn);

		// Pack the frame to fit the preferred size of its components
		pack();

		// Center the window on the screen
		setLocationRelativeTo(null);
	}

	// Text Box Clear Function
	public void clear() {
		txtName.setText("");
		txtEmail.setText("");
		txtPassword.setText("");
	}
}
