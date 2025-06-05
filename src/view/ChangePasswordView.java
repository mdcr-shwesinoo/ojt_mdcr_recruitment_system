package view;

import java.awt.*;
import java.awt.event.*;
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
    private JLabel rfvNewPassword, rfvOldPassword, rfvRePassword;
    private ImageIcon iconShow, iconHide;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                ChangePasswordView frame = new ChangePasswordView(1, new JFrame());
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
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
        setSize(600, 400);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        setContentPane(contentPane);

        initIcons();
        addTitlePanel();
        addFields();
        addButtonPanel();
    }

    private void initIcons() {
        iconShow = new ImageIcon("../ojt_mdcr_recruitment_system/src/image/view.png");
        iconHide = new ImageIcon("../ojt_mdcr_recruitment_system/src/image/hide.png");
        iconShow = new ImageIcon(iconShow.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
        iconHide = new ImageIcon(iconHide.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
    }

    private void addTitlePanel() {
        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setBackground(Color.BLACK);
        titlePanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));

        JLabel lblTitle = new JLabel("Change Password");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 16));
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setBorder(new EmptyBorder(10, 10, 10, 10));

        titlePanel.add(lblTitle, BorderLayout.WEST);
        contentPane.add(titlePanel);
        contentPane.add(Box.createVerticalStrut(20));
    }

    private void addFields() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 0, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Old Password
        txtOldPassword = new JPasswordField(15);
        txtOldPassword.setEchoChar('*');
        addPasswordField(panel, gbc, 0, "Old Password:", txtOldPassword, rfvOldPassword = new JLabel(" "), true);

        // New Password
        txtNewPassword = new JPasswordField(15);
        txtNewPassword.setEchoChar('*');
        addPasswordField(panel, gbc, 2, "New Password:", txtNewPassword, rfvNewPassword = new JLabel(" "), true);

        // Re-enter New Password
        txtReEnterNewPassword = new JPasswordField(15);
        txtReEnterNewPassword.setEchoChar('*');
        addPasswordField(panel, gbc, 4, "Re-enter New Password:", txtReEnterNewPassword, rfvRePassword = new JLabel(" "), true);

        contentPane.add(panel);
        contentPane.add(Box.createVerticalStrut(10));
    }

    private void addPasswordField(JPanel panel, GridBagConstraints gbc, int row, String labelText, JPasswordField passwordField, JLabel validationLabel, boolean withToggle) {
        gbc.gridx = 0;
        gbc.gridy = row;
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Arial", Font.PLAIN, 14));
        panel.add(label, gbc);

        gbc.gridx = 1;
        JPanel fieldPanel = new JPanel(new BorderLayout());
        fieldPanel.setPreferredSize(new Dimension(250, 30));
        stylePasswordField(passwordField);
        fieldPanel.add(passwordField, BorderLayout.CENTER);
        //setPlaceholder(passwordField, "Enter " + labelText.toLowerCase());
        Common.setPlaceholder(passwordField, "Enter " + labelText.toLowerCase());

        if (withToggle) {
            JButton toggleBtn = createEyeButton(passwordField);
            fieldPanel.add(toggleBtn, BorderLayout.EAST);
        }

        panel.add(fieldPanel, gbc);

        gbc.gridy = row + 1;
        validationLabel.setForeground(Color.RED);
        validationLabel.setText(" ");
        panel.add(validationLabel, gbc);
    }

    private JButton createEyeButton(JPasswordField pwdField) {
        JButton btnToggle = new JButton(iconHide);
        btnToggle.setPreferredSize(new Dimension(40, 30));
        btnToggle.setFocusPainted(false);
        btnToggle.setContentAreaFilled(false);
        btnToggle.setBorderPainted(false);
        btnToggle.setOpaque(true);
        btnToggle.setBackground(new Color(230, 230, 235));

        btnToggle.addActionListener(e -> {
            if (pwdField.getEchoChar() == (char) 0) {
                pwdField.setEchoChar('*');
                btnToggle.setIcon(iconHide);
            } else {
                pwdField.setEchoChar((char) 0);
                btnToggle.setIcon(iconShow);
            }
        });

        return btnToggle;
    }

    private void addButtonPanel() {
        JPanel buttonPanel = new JPanel();
        btnSave = Common.addActionButton("Save");
        btnSave.setPreferredSize(new Dimension(100, 30));
        btnSave.addActionListener(e -> handleChangePassword());
        buttonPanel.add(btnSave);
        contentPane.add(buttonPanel);
        contentPane.add(Box.createVerticalGlue());
    }

    private void stylePasswordField(JPasswordField passwordField) {
        passwordField.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        passwordField.setBackground(new Color(245, 245, 245));
        passwordField.setBorder(new CompoundBorder(new LineBorder(new Color(200, 200, 200), 1, true),
                new EmptyBorder(4, 8, 4, 8)));
    }

    public void setPlaceholder(JPasswordField passwordField, String placeholder) {
        final char defaultEchoChar = '*';
        final Color placeholderColor = Color.GRAY;
        final Color inputColor = Color.BLACK;

        // State to track if placeholder is currently shown
        final boolean[] isShowingPlaceholder = {true};

        // Initially show placeholder
        passwordField.setText(placeholder);
        passwordField.setForeground(placeholderColor);
        passwordField.setEchoChar((char) 0);

        passwordField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (isShowingPlaceholder[0]) {
                    passwordField.setText("");
                    passwordField.setForeground(inputColor);
                    passwordField.setEchoChar(defaultEchoChar);
                    isShowingPlaceholder[0] = false;
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (passwordField.getPassword().length == 0) {
                    passwordField.setText(placeholder);
                    passwordField.setForeground(placeholderColor);
                    passwordField.setEchoChar((char) 0);
                    isShowingPlaceholder[0] = true;
                }
            }
        });

        // Optional: handle typing while placeholder is showing
        passwordField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (isShowingPlaceholder[0]) {
                    passwordField.setText("");
                    passwordField.setForeground(inputColor);
                    passwordField.setEchoChar(defaultEchoChar);
                    isShowingPlaceholder[0] = false;
                }
            }
        });
    }


    private void handleChangePassword() {
        String oldPwdInput = String.valueOf(txtOldPassword.getPassword()).trim();
        String newPwd = String.valueOf(txtNewPassword.getPassword()).trim();
        String reEnterNewPwd = String.valueOf(txtReEnterNewPassword.getPassword()).trim();

        if (oldPwdInput.isEmpty() || newPwd.isEmpty() || reEnterNewPwd.isEmpty()
                || oldPwdInput.equalsIgnoreCase("Enter old password")
                || newPwd.equalsIgnoreCase("Enter new password")
                || reEnterNewPwd.equalsIgnoreCase("Re-enter new password")) {
            rfvRePassword.setText("Please fill in all fields.");
            return;
        }

        if (!oldPwdInput.equals(currentUserPassword)) {
            rfvOldPassword.setText("Old password is incorrect.");
            return;
        } else {
            rfvOldPassword.setText(" ");
        }

        if (!newPwd.equals(reEnterNewPwd)) {
            rfvRePassword.setText("New passwords do not match.");
            return;
        }

        if (newPwd.equals(oldPwdInput)) {
            rfvRePassword.setText("New password cannot be the same as the old password.");
            return;
        }

        if (!newPwd.matches("^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[@$!%*#?&]).{7,}$")) {
            rfvRePassword.setText("<html>Password must be at least 7 length<br>& numbers & special char.</html>");
            return;
        }

        try (Connection conn = config.DBConfig.getConnection()) {
            String sql = "UPDATE useraccount SET user_password = ? WHERE user_email = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, newPwd);
                stmt.setString(2, currentUserEmail);

                int updated = stmt.executeUpdate();
                if (updated > 0) {
                    JOptionPane.showMessageDialog(this, "Password changed successfully.");
                    currentUserPassword = newPwd;
                    new LoginView().setVisible(true);
                    parent.dispose();
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to update password.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
