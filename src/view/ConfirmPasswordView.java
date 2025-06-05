package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

import config.Common;

import java.sql.*;
import java.util.regex.Pattern;

public class ConfirmPasswordView extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JPanel fieldPanel;
    private JPasswordField txtNewPassword;
    private JPasswordField txtConfirmPassword;
    private ImageIcon iconShow;
    private ImageIcon iconHide;
    private JLabel rfvAllFields;


    private String userEmail;

    // Database
    private final String dbUrl = "jdbc:mysql://localhost:3306/mdcrrecruitment";
    private final String dbUsername = "root";
    private final String dbPassword = "";

    // Password validation
    private final String passwordRegex = "^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[@$!%*#?&]).{7,}$";

    public ConfirmPasswordView(String email) {
        this.userEmail = email;

        setTitle("Reset Password");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 600, 300); // Increased width for better layout
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(contentPane);
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

        // Title Panel
        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        titlePanel.setBackground(Color.BLACK);

        JLabel lblTitle = new JLabel("Reset Password");
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 16));
        lblTitle.setBorder(new EmptyBorder(10, 10, 10, 10));
        titlePanel.add(lblTitle, BorderLayout.WEST);

        // Field Panel
        fieldPanel = new JPanel();
        fieldPanel.setLayout(new GridBagLayout());
        fieldPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 120));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel lblNewPass = new JLabel("New Password:");
        lblNewPass.setFont(new Font("Arial", Font.PLAIN, 14));
        fieldPanel.add(lblNewPass, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;

        JPanel passwordPanel = new JPanel();
		passwordPanel.setBounds(125, 184, 250, 36);
		passwordPanel.setLayout(new BorderLayout());
		fieldPanel.add(passwordPanel, gbc);
 
		txtNewPassword = new JPasswordField(15);
		stylePasswordField(txtNewPassword);
		txtNewPassword.setEchoChar('*');
		setPlaceholder(txtNewPassword, "Enter new password");
//		txtNewPassword.setBorder(BorderFactory.createEmptyBorder());
		txtNewPassword.setBackground(Color.WHITE);
		passwordPanel.add(txtNewPassword, BorderLayout.CENTER);
		
		// Eye icon button for toggling visibility
		JButton btnToggle = new JButton();
		btnToggle.setPreferredSize(new Dimension(40, 30));
		btnToggle.setFocusPainted(false);
		btnToggle.setContentAreaFilled(false);
		btnToggle.setBorderPainted(false);
		
		btnToggle.setOpaque(true);
		btnToggle.setBackground(new Color(230,230,235));
		
		iconShow = new ImageIcon(getClass().getResource("/image/view.png"));
		iconHide = new ImageIcon(getClass().getResource("/image/hide.png"));
 
		// Resize icons
		iconShow = new ImageIcon(iconShow.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
		iconHide = new ImageIcon(iconHide.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
		btnToggle.setIcon(iconHide); // initially password hidden
 
		btnToggle.addActionListener(e -> {
			if (txtNewPassword.getEchoChar() != (char) 0) {
				txtNewPassword.setEchoChar((char) 0); // show password
				btnToggle.setIcon(iconShow);
			} else {
				txtNewPassword.setEchoChar('*'); // hide password
				btnToggle.setIcon(iconHide);
			}
		});
 
		passwordPanel.add(btnToggle, BorderLayout.EAST);
 
		
        gbc.gridx = 0;
        gbc.gridy = 1;
        JLabel lblConfirmPass = new JLabel("Confirm Password:");
        lblConfirmPass.setFont(new Font("Arial", Font.PLAIN, 14));
        fieldPanel.add(lblConfirmPass, gbc);
 
        gbc.gridx = 1;
        gbc.gridy = 1;
        JPanel passwordPanel1 = new JPanel();
        passwordPanel1.setBounds(125, 184, 250, 36);
        passwordPanel1.setLayout(new BorderLayout());
		fieldPanel.add(passwordPanel1, gbc);
 
		// Eye icon button for toggling visibility
		JButton btnToggleConfirm = new JButton();
		btnToggleConfirm.setPreferredSize(new Dimension(40, 30));
		btnToggleConfirm.setFocusPainted(false);
		btnToggleConfirm.setContentAreaFilled(false);
		btnToggleConfirm.setBorderPainted(false);
		btnToggleConfirm.setBackground(new Color(225,0, 0));
		
		iconShow = new ImageIcon(getClass().getResource("/image/view.png"));
		iconHide = new ImageIcon(getClass().getResource("/image/hide.png"));
 
		// Resize icons
		iconShow = new ImageIcon(iconShow.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
		iconHide = new ImageIcon(iconHide.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
		btnToggleConfirm.setIcon(iconHide); // initially password hidden
 
		btnToggleConfirm.addActionListener(e -> {
			if (txtConfirmPassword.getEchoChar() != (char) 0) {
				txtConfirmPassword.setEchoChar((char) 0); // show password
				btnToggleConfirm.setIcon(iconShow);
			} else {
				txtConfirmPassword.setEchoChar('*'); // hide password
				btnToggleConfirm.setIcon(iconHide);
			}
		});
		btnToggleConfirm.setBackground(Color.red);
 
		passwordPanel1.add(btnToggleConfirm, BorderLayout.EAST);
 
		txtConfirmPassword = new JPasswordField(15);
		stylePasswordField(txtConfirmPassword);
		txtConfirmPassword.setEchoChar('*');
		setPlaceholder(txtConfirmPassword, "Confirm new password");
		passwordPanel1.add(txtConfirmPassword, BorderLayout.CENTER);
//		txtConfirmPassword.setBorder(BorderFactory.createEmptyBorder());
		txtConfirmPassword.setBackground(Color.WHITE);
		passwordPanel1.setBackground(new Color(220,220,220));
    	
    	gbc.gridx = 1;
    	gbc.gridy = 2;
    	gbc.insets = new Insets(0, 0, 0, 0);
    	rfvAllFields = new JLabel(" ");
    	rfvAllFields.setForeground(Color.red);
    	fieldPanel.add(rfvAllFields, gbc);
    	
    	
    	
		
        // Button Panel
    	gbc.gridx = 1;
    	gbc.gridy = 3;
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton btnSave = Common.addActionButton("Save");
        btnSave.setPreferredSize(new Dimension(100, 30));
        buttonPanel.add(btnSave);
        fieldPanel.add(buttonPanel, gbc);

        contentPane.add(titlePanel);
        contentPane.add(Box.createVerticalStrut(20));
        contentPane.add(fieldPanel);
//        contentPane.add(Box.createVerticalStrut(10));
//        contentPane.add(buttonPanel);
        contentPane.add(Box.createVerticalGlue());

        btnSave.addActionListener(e -> handleChangePassword(gbc));
    }

    private void stylePasswordField(JPasswordField passwordField) {
        passwordField.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        passwordField.setBackground(new Color(245, 245, 245));
        passwordField.setBorder(new CompoundBorder(
            new LineBorder(new Color(200, 200, 200), 1, true),
            new EmptyBorder(4, 8, 4, 8)
        ));
    }

    private void handleChangePassword(GridBagConstraints gbc) {
        String newPassword = String.valueOf(txtNewPassword.getPassword()).trim();
        String confirmPassword = String.valueOf(txtConfirmPassword.getPassword()).trim();

        if (newPassword.equals("Enter new password") || confirmPassword.equals("Confirm new password")) {
        	rfvAllFields.setText("Please fill in both fields.");
            return;
        }

        if (!newPassword.equals(confirmPassword)) {
        	rfvAllFields.setText("Passwords do not match");
//            JOptionPane.showMessageDialog(this, "Passwords do not match.", "Mismatch", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!Pattern.matches(passwordRegex, newPassword)) {
        	rfvAllFields.setText("Password must be at least 7 characters, include letters, numbers, and a special character.");
//            JOptionPane.showMessageDialog(this,
//                "Password must be at least 7 characters, include letters, numbers, and a special character.",
//                "Invalid Password",
//                JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (updatePasswordInDatabase(newPassword)) {
            JOptionPane.showMessageDialog(this, "Password updated successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
           
            // Assuming LoginView exists and should be shown after successful password reset
            LoginView loginView = new LoginView();
            loginView.setExtendedState(JFrame.MAXIMIZED_BOTH);
            loginView.setVisible(true);

            // Close current window
            this.dispose();

        } else {
            JOptionPane.showMessageDialog(this, "Failed to update password.", "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean updatePasswordInDatabase(String newPassword) {
        try (Connection conn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword)) {
            String sql = "UPDATE useraccount SET user_password = ? WHERE user_email = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, newPassword);
            stmt.setString(2, userEmail);
            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void setPlaceholder(JPasswordField passwordField, String placeholder) {
        final char defaultEchoChar = passwordField.getEchoChar(); // Save original echo char
        final boolean[] showingPlaceholder = {true};

        // Set initial placeholder
        passwordField.setEchoChar((char) 0);
        passwordField.setForeground(Color.GRAY);
        passwordField.setText(placeholder);

        // Focus behavior
        passwordField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (showingPlaceholder[0]) {
                    passwordField.setText("");
                    passwordField.setForeground(Color.BLACK);
                    passwordField.setEchoChar(defaultEchoChar);
                    showingPlaceholder[0] = false;
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (String.valueOf(passwordField.getPassword()).isEmpty()) {
                    passwordField.setEchoChar((char) 0);
                    passwordField.setForeground(Color.GRAY);
                    passwordField.setText(placeholder);
                    showingPlaceholder[0] = true;
                }
            }
        });

        // Key listener: clear placeholder on first key
        passwordField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (showingPlaceholder[0]) {
                    passwordField.setText("");
                    passwordField.setForeground(Color.BLACK);
                    passwordField.setEchoChar(defaultEchoChar);
                    showingPlaceholder[0] = false;
                }
            }
        });

        // Document listener: detect deletion and restore placeholder immediately
        passwordField.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            private void update() {
                SwingUtilities.invokeLater(() -> {
                    String currentText = String.valueOf(passwordField.getPassword());
                    if (!showingPlaceholder[0] && currentText.isEmpty()) {
                        passwordField.setEchoChar((char) 0);
                        passwordField.setForeground(Color.GRAY);
                        passwordField.setText(placeholder);
                        showingPlaceholder[0] = true;
                        passwordField.setCaretPosition(0); // Optional: move caret to start
                    }
                });
            }

            public void insertUpdate(javax.swing.event.DocumentEvent e) { update(); }
            public void removeUpdate(javax.swing.event.DocumentEvent e) { update(); }
            public void changedUpdate(javax.swing.event.DocumentEvent e) { update(); }
        });
    }

   
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            ConfirmPasswordView view = new ConfirmPasswordView("userEmail"); // Pass an empty string or a test email for testing
            view.setVisible(true);
        });
    }
}