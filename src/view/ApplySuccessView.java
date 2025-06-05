package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
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
import javax.swing.SwingUtilities;

import config.Common;

@SuppressWarnings("serial")
public class ApplySuccessView extends JFrame {
	private JPanel contentPane;

	public ApplySuccessView(int loggedInId) {
		setTitle("Myanmar DCR Recruitment System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());

		// Create a wrapper panel with GridBagLayout
		JPanel wrapper = new JPanel(new GridBagLayout());

		// Create the content pane and set its preferred size
		contentPane = new JPanel();
		contentPane.setPreferredSize(new Dimension(650, 480));
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
		lblTitleBar.setBounds(183, 10, 342, 30);
		panel.add(lblTitleBar);

		// Load the image and create a JLabel
		ImageIcon imageIcon = new ImageIcon(getClass().getResource("/image/apply6.png"));
		JLabel imageLabel = new JLabel(imageIcon);
		imageLabel.setBounds(117, 0, 384, 212); // Adjust the position as needed
		contentPane.add(imageLabel);

		JLabel lblSuccess = new JLabel("Application Applied Successfully !");
		lblSuccess.setFont(new Font("MS UI Gothic", Font.PLAIN, 18));
		lblSuccess.setBounds(203, 210, 275, 32);
		contentPane.add(lblSuccess);
		
		JLabel lblApplySuccess_1 = new JLabel(
				"<html>Thank you for Applying for the Recruitment at Myanmar DCR. Our HR Team is <br></html>");
		lblApplySuccess_1.setBounds(117, 252, 494, 32);
		contentPane.add(lblApplySuccess_1);

		JLabel lblApplySuccess_2 = new JLabel("<html>Reviewing Your Application and will contact you.</html>");
		lblApplySuccess_2.setBounds(203, 282, 298, 23);
		contentPane.add(lblApplySuccess_2);
		
		JLabel lblAppreciate = new JLabel("We appreciate your interest in joining our team.");
		lblAppreciate.setBounds(203, 326, 275, 23);
		contentPane.add(lblAppreciate);
		
		wrapper.add(contentPane);

		JButton btnClose = Common.addActionButton("Close");
//		btnClose.setForeground(new Color(255, 255, 255));
//		btnClose.setBackground(new Color(94, 148, 255));
//		btnClose.setBorderPainted(false);
		btnClose.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				LoginView loginView = new LoginView();
				loginView.setExtendedState(JFrame.MAXIMIZED_BOTH);
				loginView.setVisible(true);
			}
		});
		btnClose.setBounds(230, 406, 200, 32);
		contentPane.add(btnClose);
		
//		JButton btnApply = new JButton("Apply Form");
//		btnApply.setForeground(new Color(255, 255, 255));
//		btnApply.setBackground(new Color(94, 148, 255));
//		btnApply.setBorderPainted(false);
//		btnApply.setCursor(new Cursor(Cursor.HAND_CURSOR));
//		btnApply.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				dispose();
//				ApplyFormView applyFormView = new ApplyFormView(loggedInId);
//				applyFormView.setExtendedState(JFrame.MAXIMIZED_BOTH);
//				applyFormView.setVisible(true);
//			}
//		});
//		btnApply.setBounds(100, 406, 200, 32);
//		contentPane.add(btnApply);
		
		// Add the wrapper to the main content pane
		getContentPane().add(wrapper, BorderLayout.CENTER);

		// Pack the frame to fit the preferred size of its components
		pack();

		// Center the window on the screen
		setLocationRelativeTo(null);
	}


	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			ApplySuccessView view = new ApplySuccessView(1);
			view.setVisible(true);
		});
	}
}
