package view;

import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.*;
import javax.swing.border.*;

import config.Common;
import controller.UserAccountController;

public class ChangePasswordView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPasswordField txtOldPassword;
	private JPasswordField txtNewPassword;
	private JPasswordField txtReEnterNewPassword;
	private JButton btnSave;
	private String currentUserEmail;
	private String currentUserPassword;
	private JFrame parent;
	private ImageIcon iconShow, iconHide;
	private JLabel rfvNewPassword, rfvOldPassword, rfvRePassword;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChangePasswordView frame = new ChangePasswordView(1, new JFrame()); // from login
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ChangePasswordView(int id, JFrame parent) {
		this.parent = parent;
		UserAccountController controller = new UserAccountController();
		var model = controller.findById(id);
		this.currentUserEmail = model.getEmail();
		this.currentUserPassword = model.getPassword();
		setTitle("Change Password");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 370);
		setLocationRelativeTo(null);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

		JPanel titlePanel = new JPanel(new BorderLayout());
		titlePanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
		titlePanel.setBackground(Color.BLACK);

		JLabel lblChangePassword = new JLabel("Change Password");
		lblChangePassword.setForeground(Color.WHITE);
		lblChangePassword.setFont(new Font("Arial", Font.BOLD, 16));
		lblChangePassword.setBorder(new EmptyBorder(10, 10, 10, 10));
		titlePanel.add(lblChangePassword, BorderLayout.WEST);

		JPanel fieldPanel = new JPanel();
		fieldPanel.setLayout(new GridBagLayout());
		fieldPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 120));
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 0, 10);
		gbc.fill = GridBagConstraints.HORIZONTAL;

		// Old Password
		gbc.gridx = 0;
		gbc.gridy = 0;
		JLabel lblOldPassword = new JLabel("Old Password:");
		lblOldPassword.setFont(new Font("Arial", Font.PLAIN, 14));
		fieldPanel.add(lblOldPassword, gbc);

		gbc.gridx = 1;
		gbc.gridy = 0;
		JPanel passwordPanel = new JPanel();
		passwordPanel.setBounds(125, 184, 250, 36);
		passwordPanel.setLayout(new BorderLayout());
		fieldPanel.add(passwordPanel, gbc);

		txtOldPassword = new JPasswordField(15);
		stylePasswordField(txtOldPassword);
		txtOldPassword.setEchoChar('*');
		fieldPanel.add(txtOldPassword, gbc);
		setPlaceholder(txtOldPassword, "Enter old password");
//		txtOldPassword.setBorder(BorderFactory.createEmptyBorder());
		txtOldPassword.setBackground(Color.WHITE);
		passwordPanel.add(txtOldPassword, BorderLayout.CENTER);
		
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.insets = new Insets(0, 10, 10, 10);
		rfvOldPassword = new JLabel(" ");
		rfvOldPassword.setForeground(Color.red);
		fieldPanel.add(rfvOldPassword, gbc);

		// Eye icon button for toggling visibility
		JButton btnToggle = new JButton();
		btnToggle.setPreferredSize(new Dimension(40, 30));
		btnToggle.setFocusPainted(false);
		btnToggle.setContentAreaFilled(false);
		btnToggle.setBorderPainted(false);

		btnToggle.setOpaque(true);
		btnToggle.setBackground(new Color(230, 230, 235));

		iconShow = new ImageIcon("../ojt_mdcr_recruitment_system/src/image/view.png");
		iconHide = new ImageIcon("../ojt_mdcr_recruitment_system/src/image/hide.png");

		// Resize icons
		iconShow = new ImageIcon(iconShow.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
		iconHide = new ImageIcon(iconHide.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
		btnToggle.setIcon(iconHide);

		btnToggle.addActionListener(e -> {
			if (txtOldPassword.getEchoChar() != (char) 0) {
				txtOldPassword.setEchoChar((char) 0); // show password
				btnToggle.setIcon(iconShow);
			} else {
				txtOldPassword.setEchoChar('*'); // hide password
				btnToggle.setIcon(iconHide);
			}
		});
		passwordPanel.add(btnToggle, BorderLayout.EAST);

//      		//New Password
		gbc.gridx = 0;
		gbc.gridy = 2;
		JLabel lblNewPassword = new JLabel("New Password:");
		lblNewPassword.setFont(new Font("Arial", Font.PLAIN, 14));
		fieldPanel.add(lblNewPassword, gbc);

		gbc.gridx = 1;
		gbc.gridy = 2;
		JPanel newPasswordPanel = new JPanel();
		newPasswordPanel.setBounds(125, 184, 250, 36);
		newPasswordPanel.setLayout(new BorderLayout());
		fieldPanel.add(newPasswordPanel, gbc);

		txtNewPassword = new JPasswordField(15);
		stylePasswordField(txtNewPassword);
		txtNewPassword.setEchoChar('*');
		fieldPanel.add(txtNewPassword, gbc);
		setPlaceholder(txtNewPassword, "Enter new password");
//		txtNewPassword.setBorder(BorderFactory.createEmptyBorder());
		txtNewPassword.setBackground(Color.WHITE);
		newPasswordPanel.add(txtNewPassword, BorderLayout.CENTER);
		
		gbc.gridx = 1;
		gbc.gridy = 3;
		gbc.insets = new Insets(0, 10, 0, 10);
		rfvNewPassword = new JLabel(" ");
		rfvNewPassword.setForeground(Color.red);
		fieldPanel.add(rfvNewPassword, gbc);

		// Eye icon button for toggling visibility
		JButton btnToggleNewPWS = new JButton();
		btnToggleNewPWS.setPreferredSize(new Dimension(40, 30));
		btnToggleNewPWS.setFocusPainted(false);
		btnToggleNewPWS.setContentAreaFilled(false);
		btnToggleNewPWS.setBorderPainted(false);

		btnToggleNewPWS.setOpaque(true);
		btnToggleNewPWS.setBackground(new Color(230, 230, 235));

		iconShow = new ImageIcon("../ojt_mdcr_recruitment_system/src/image/view.png");
		iconHide = new ImageIcon("../ojt_mdcr_recruitment_system/src/image/hide.png");

		// Resize icons
		iconShow = new ImageIcon(iconShow.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
		iconHide = new ImageIcon(iconHide.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
		btnToggleNewPWS.setIcon(iconHide);

		btnToggleNewPWS.addActionListener(e -> {
			if (txtNewPassword.getEchoChar() != (char) 0) {
				txtNewPassword.setEchoChar((char) 0); // show password
				btnToggleNewPWS.setIcon(iconShow);
			} else {
				txtNewPassword.setEchoChar('*'); // hide password
				btnToggleNewPWS.setIcon(iconHide);
			}
		});
		newPasswordPanel.add(btnToggleNewPWS, BorderLayout.EAST);

		gbc.gridx = 0;
		gbc.gridy = 4;
		JLabel lblReEnterNewPassword = new JLabel("Re-enter New Password:");
		lblReEnterNewPassword.setFont(new Font("Arial", Font.PLAIN, 14));
		fieldPanel.add(lblReEnterNewPassword, gbc);

		gbc.gridx = 1;
		gbc.gridy = 4;
		JPanel rePasswordPanel = new JPanel();
		rePasswordPanel.setBounds(125, 184, 250, 36);
		rePasswordPanel.setLayout(new BorderLayout());
		fieldPanel.add(rePasswordPanel, gbc);

		txtReEnterNewPassword = new JPasswordField(15);
		stylePasswordField(txtReEnterNewPassword);
		txtReEnterNewPassword.setEchoChar('*');
		fieldPanel.add(txtReEnterNewPassword, gbc);
		setPlaceholder(txtReEnterNewPassword, "Re - enter new password");
//		txtReEnterNewPassword.setBorder(BorderFactory.createEmptyBorder());
		txtReEnterNewPassword.setBackground(Color.WHITE);
		rePasswordPanel.add(txtReEnterNewPassword, BorderLayout.CENTER);
		
		gbc.gridx = 1;
		gbc.gridy = 5;
		gbc.insets = new Insets(0, 10, 10, 10);
		rfvRePassword = new JLabel(" ");
		rfvRePassword.setForeground(Color.red);
		fieldPanel.add(rfvRePassword, gbc);

		// Eye icon button for toggling visibility
		JButton btnToggleRePWS = new JButton();
		btnToggleRePWS.setPreferredSize(new Dimension(40, 30));
		btnToggleRePWS.setFocusPainted(false);
		btnToggleRePWS.setContentAreaFilled(false);
		btnToggleRePWS.setBorderPainted(false);

		btnToggleRePWS.setOpaque(true);
		btnToggleRePWS.setBackground(new Color(230, 230, 235));

		iconShow = new ImageIcon("../ojt_mdcr_recruitment_system/src/image/view.png");
		iconHide = new ImageIcon("../ojt_mdcr_recruitment_system/src/image/hide.png");

		// Resize icons
		iconShow = new ImageIcon(iconShow.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
		iconHide = new ImageIcon(iconHide.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
		btnToggleRePWS.setIcon(iconHide);

		btnToggleRePWS.addActionListener(e -> {
			if (txtReEnterNewPassword.getEchoChar() != (char) 0) {
				txtReEnterNewPassword.setEchoChar((char) 0); // show password
				btnToggleRePWS.setIcon(iconShow);
			} else {
				txtReEnterNewPassword.setEchoChar('*'); // hide password
				btnToggleRePWS.setIcon(iconHide);
			}
		});
		rePasswordPanel.add(btnToggleRePWS, BorderLayout.EAST);

		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		btnSave = Common.addActionButton("Save");
		btnSave.setFont(new Font("Arial", Font.PLAIN, 13));
		btnSave.setPreferredSize(new Dimension(100, 30));
		btnSave.addActionListener(e -> handleChangePassword());
		buttonPanel.add(btnSave);

		contentPane.add(titlePanel);
		contentPane.add(Box.createVerticalStrut(20));
		contentPane.add(fieldPanel);
		contentPane.add(Box.createVerticalStrut(10));
		contentPane.add(buttonPanel);
		contentPane.add(Box.createVerticalGlue());
	}

	private void stylePasswordField(JPasswordField passwordField) {
		passwordField.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		passwordField.setBackground(new Color(245, 245, 245));
		passwordField.setBorder(
				new CompoundBorder(new LineBorder(new Color(200, 200, 200), 1, true), new EmptyBorder(4, 8, 4, 8)));
	}

	private void setPlaceholder(JPasswordField passwordField, String placeholder) {

		final char defaultEchoChar = passwordField.getEchoChar();

		// Initially set placeholder
		passwordField.setEchoChar((char) 0);
		passwordField.setForeground(Color.GRAY);
		passwordField.setText(placeholder);

		passwordField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				String text = String.valueOf(passwordField.getPassword());
				if (text.equals(placeholder)) {
					passwordField.setCaretPosition(0);
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				String text = String.valueOf(passwordField.getPassword());
				if (text.isEmpty()) {
					passwordField.setEchoChar((char) 0);
					passwordField.setForeground(Color.GRAY);
					passwordField.setText(placeholder);
				}
			}
		});

		passwordField.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
			private void update() {
				SwingUtilities.invokeLater(() -> {
					String currentText = String.valueOf(passwordField.getPassword());

					if (currentText.isEmpty()) {
						passwordField.setEchoChar((char) 0);
						passwordField.setForeground(Color.GRAY);
						passwordField.setText(placeholder);
						passwordField.setCaretPosition(0);
					} else if (!currentText.equals(placeholder)) {
						passwordField.setForeground(Color.BLACK);
						passwordField.setEchoChar(defaultEchoChar);
					}
				});
			}

			public void insertUpdate(javax.swing.event.DocumentEvent e) {
				update();
			}

			public void removeUpdate(javax.swing.event.DocumentEvent e) {
				update();
			}

			public void changedUpdate(javax.swing.event.DocumentEvent e) {
				update();
			}
		});

		passwordField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (passwordField.getForeground().equals(Color.GRAY)
						&& String.valueOf(passwordField.getPassword()).equals(placeholder)) {

					if (e.getKeyChar() != KeyEvent.CHAR_UNDEFINED && !Character.isISOControl(e.getKeyChar())) {
						passwordField.setText("");
						passwordField.setForeground(Color.BLACK);
						passwordField.setEchoChar(defaultEchoChar);
					}
				}
			}
		});
	}

	private void handleChangePassword() {
		String oldPwdInput = String.valueOf(txtOldPassword.getPassword()).trim();
		String newPwd = String.valueOf(txtNewPassword.getPassword()).trim();
		String reEnterNewPwd = String.valueOf(txtReEnterNewPassword.getPassword()).trim();
		
		if (oldPwdInput.equals("Enter old password") || newPwd.equals("Enter new password") || reEnterNewPwd.equals("Re - enter new password")) {
			rfvRePassword.setText("Please fill in all fields.");
			return;
		}

		// Check if old password matches
		if (!oldPwdInput.equals(currentUserPassword) && !oldPwdInput.equals("Enter old password")) {
			rfvOldPassword.setText("Old password is incorrect.");
			rfvRePassword.setText(" ");
//			JOptionPane.showMessageDialog(this, "Old password is incorrect.", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		// Check if new password fields match
		if (!newPwd.equals(reEnterNewPwd)) {
			rfvRePassword.setText("New passwords do not match.");
			rfvOldPassword.setText(" ");
//			JOptionPane.showMessageDialog(this, "New passwords do not match.", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		// Basic validation: not empty
		if (newPwd.isEmpty()) {
			JOptionPane.showMessageDialog(this, "New password cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		if (newPwd.equals(oldPwdInput)) {
			rfvRePassword.setText("New password cannot be the same as the old password.");
//            JOptionPane.showMessageDialog(this, "New password cannot be the same as the old password.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

		// Validate new password pattern
		if (!newPwd.matches("^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[@$!%*#?&]).{7,}$")) {
			JOptionPane.showMessageDialog(this,
					"New password must be at least 7 characters and include at least one letter, one number, and one special character (@$!%*#?&).",
					"Invalid Password", JOptionPane.ERROR_MESSAGE);
			return;
		}

		// Update in database
		try (Connection conn = config.DBConfig.getConnection()) {
			String sql = "UPDATE useraccount SET user_password = ? WHERE user_email = ?";
			try (PreparedStatement stmt = conn.prepareStatement(sql)) {
				stmt.setString(1, newPwd);
				stmt.setString(2, currentUserEmail);

				int updated = stmt.executeUpdate();
				if (updated > 0) {
					JOptionPane.showMessageDialog(this, "Password changed successfully.");
					currentUserPassword = newPwd;
					LoginView loginView = new LoginView();
					loginView.setExtendedState(JFrame.MAXIMIZED_BOTH);
					loginView.setVisible(true);
					parent.dispose();
					dispose();
				} else {
					JOptionPane.showMessageDialog(this, "Failed to update password.", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

}