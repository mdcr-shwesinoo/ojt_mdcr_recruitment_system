package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import config.Common;

public class SignUpSuccessView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignUpSuccessView frame = new SignUpSuccessView();
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
	public SignUpSuccessView() {
		setTitle("Myanmar DCR Recruitment System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());

		// Create a wrapper panel with GridBagLayout
		JPanel wrapper = new JPanel(new GridBagLayout());

		// Create the content pane and set its preferred size
		contentPane = new JPanel();
		contentPane.setPreferredSize(new Dimension(650, 380));
		contentPane.setBackground(SystemColor.inactiveCaption);
		contentPane.setLayout(null);

		// Custom title bar panel (black bar)
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 0));
		panel.setPreferredSize(new Dimension(450, 50));
		getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(null);

		ImageIcon titleImage = new ImageIcon("../ojt_mdcr_recruitment_system/src/image/mdcr3.png");
		JLabel imageTitleLabel = new JLabel(titleImage);
		imageTitleLabel.setBounds(25, 0, 141, 50);
		panel.add(imageTitleLabel);

		JLabel lblTitleBar = new JLabel("Myanmar DCR Recruitment System");
		lblTitleBar.setForeground(Color.WHITE);
		lblTitleBar.setFont(new Font("MS UI Gothic", Font.PLAIN, 20));
		lblTitleBar.setBounds(183, 10, 342, 30);
		panel.add(lblTitleBar);

		// Load the image and create a JLabel
		ImageIcon imageIcon = new ImageIcon("../ojt_mdcr_recruitment_system/src/image/SignUP1.png");
		JLabel imageLabel = new JLabel(imageIcon);
		imageLabel.setBounds(270, 27, 107, 87); // Adjust the position as needed
		contentPane.add(imageLabel);

		// Add components to the content pane
		JLabel lblSuccess = new JLabel("Sign Up Success ");
		lblSuccess.setFont(new Font("MS UI Gothic", Font.PLAIN, 18));
		lblSuccess.setBounds(258, 147, 149, 32);
		contentPane.add(lblSuccess);

		JLabel lblCongratolation = new JLabel(
				"Congratulations , your account has  been successfully created.");
		lblCongratolation.setHorizontalAlignment(SwingConstants.LEFT);
		lblCongratolation.setBounds(167, 202, 330, 32);
		contentPane.add(lblCongratolation);

		wrapper.add(contentPane);

//		JButton btnContinue = new JButton("Continue");
//		btnContinue.setForeground(new Color(255, 255, 255));
//		btnContinue.setBackground(new Color(94, 148, 255));
//		btnContinue.setBorderPainted(false);
		JButton btnContinue = Common.addActionButton("Continue");
		btnContinue.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnContinue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				LoginView loginView = new LoginView();
				loginView.setExtendedState(JFrame.MAXIMIZED_BOTH);
				loginView.setVisible(true);
			}
		});
		btnContinue.setBounds(231, 282, 200, 32);
		contentPane.add(btnContinue);
		// Add the wrapper to the main content pane
		getContentPane().add(wrapper, BorderLayout.CENTER);

		// Pack the frame to fit the preferred size of its components
		pack();

		// Center the window on the screen
		setLocationRelativeTo(null);
	}
}
