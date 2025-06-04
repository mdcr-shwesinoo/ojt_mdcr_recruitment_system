package config;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class Common {
	// DB Connection
	public static Connection con = null;

	static {
		DBConfig dbConfig = new DBConfig();
		con = dbConfig.getConnection();

	}

//Text Box Placeholder

	 public static void setPlaceholder(JTextField textField, String placeholder) {
	        // Set initial placeholder
	        textField.setForeground(Color.GRAY);
	        textField.setText(placeholder);

	        // To keep track of whether placeholder is currently shown
	        final boolean[] showingPlaceholder = {true};

	        // Focus behavior
	        textField.addFocusListener(new FocusAdapter() {
	            @Override
	            public void focusGained(FocusEvent e) {
	                if (showingPlaceholder[0]) {
	                    textField.setText("");
	                    textField.setForeground(Color.BLACK);
	                    showingPlaceholder[0] = false;
	                }
	            }

	            @Override
	            public void focusLost(FocusEvent e) {
	                if (textField.getText().isEmpty()) {
	                    textField.setForeground(Color.GRAY);
	                    textField.setText(placeholder);
	                    showingPlaceholder[0] = true;
	                }
	            }
	        });

	        // Key listener — placeholder gets removed on first key press
	        textField.addKeyListener(new KeyAdapter() {
	            @Override
	            public void keyPressed(KeyEvent e) {
	                if (showingPlaceholder[0]) {
	                    textField.setText("");
	                    textField.setForeground(Color.BLACK);
	                    showingPlaceholder[0] = false;
	                }
	            }
	        });

	        // Document listener — placeholder reappears if text is cleared
	        textField.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
	            private void update() {
	                SwingUtilities.invokeLater(() -> {
	                    if (!showingPlaceholder[0] && textField.getText().isEmpty()) {
	                        textField.setForeground(Color.GRAY);
	                        textField.setText(placeholder);
	                        showingPlaceholder[0] = true;
	                        // Move caret to start to simulate placeholder behavior
	                        textField.setCaretPosition(0);
	                    }
	                });
	            }

	            public void insertUpdate(javax.swing.event.DocumentEvent e) { update(); }
	            public void removeUpdate(javax.swing.event.DocumentEvent e) { update(); }
	            public void changedUpdate(javax.swing.event.DocumentEvent e) { update(); }
	        });
	    }
	    
// Empty Validation
	public static boolean IsEmpty(String str) {
		return str == null || str.trim().isEmpty();
	}

	// User Name Validation
	public boolean validateUsername(String username) {
		return username != null && username.trim().matches("[a-zA-Z  ]+");
	}

	// Email Validation
	public boolean validateEmail(String email) {
		String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
		return email.matches(emailRegex);
	}

	// Password Validation
	public boolean validatePassword(String password) {
		String passwordRegex = "^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[@$!%*#?&]).{7,}$";
		return password.matches(passwordRegex);
	}

	// Duplicate
	public boolean isduplicate(String table, String field, String data) throws SQLException {
		boolean duplicate = false;
		String sql = "select * from mdcrrecruitment." + table + " where " + field + " = ?";
		PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
		ps.setString(1, data);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			duplicate = true;
		} else {
			duplicate = false;
		}
		return duplicate;
	}

	public static JButton addActionButton(String name) {
		JButton button = new JButton(name);
		button.setAlignmentX(Component.CENTER_ALIGNMENT);
		button.setPreferredSize(new Dimension(80, 30)); // Set preferred size

		// Make the button background transparent
		button.setOpaque(true); // Make the button opaque
		button.setContentAreaFilled(true); // Allow to fill the button area
		button.setForeground(new Color(255, 255, 255)); // Set text color
		button.setBackground(new Color(94, 148, 255)); // Default color
		button.setFocusPainted(false); // Remove focus border

		// Add hover effect
		button.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseEntered(java.awt.event.MouseEvent evt) {
				button.setForeground(Color.WHITE);
				button.setBackground(new Color(54, 118, 247)); // Change background on hover
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				button.setForeground(new Color(255, 255, 255));
				button.setBackground(new Color(94, 148, 255)); // Change background on hover exit
			}
		});
		return button;
	}

}
