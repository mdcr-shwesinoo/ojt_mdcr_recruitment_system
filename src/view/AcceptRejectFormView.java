package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.*;

import java.awt.*;

import com.toedter.calendar.JDateChooser;

import controller.ApplicantController;
import model.ApplicantModel;
import utils.NRCHelper;

public class AcceptRejectFormView extends JFrame {

	private static final long serialVersionUID = 1L;

	// Replace these with your Gmail and App Password
	private final String senderEmail = "hein29939@gmail.com";
	private final String appPassword = "nkhxdxpxgzsqnzbx"; // App password, NOT Gmail password

	private int applicantID;
	private JTextField txtName;
	private JTextField txtFatherName;
	private JTextField txtPhone;
	private JTextField txtEducation;
	private JTextField txtMail;
	private JTextField txtMark;
	private JTextField txtMajor;
	private JTextField editor;
	private JTextArea txtAddress;
	private JRadioButton rdoMale;
	private JRadioButton rdoFemale;
	private ButtonGroup rdoGender;
	private JRadioButton rdoSingle;
	private JRadioButton rdoMarried;
	private ButtonGroup rdoMaritalStatus;
	private JDateChooser txtDOB;
	private JRadioButton rdoBachelor;
	private JRadioButton rdoMaster;
	private ButtonGroup rdoDegreeType;
	private JComboBox ddlMajor;
	private JButton btnAccept;
	private JButton btnReject;
	private JButton btnCancel;
	private JLabel rfvName;
	private JLabel rfvNRC;
	private JLabel rfvFatherName;
	private JLabel rfvDOB;
	private JLabel rfvGender;
	private JLabel rfvMartialStatus;
	private JLabel rfvPhone;
	private JLabel rfvEducation;
	private JLabel rfvMajor;
	private JLabel rfvMail;
	private JLabel rfvMark;

	private JComboBox<String> stateCodeCb;
	private JComboBox<String> regionCodeCb;
	private JComboBox<String> nrcTypeCb;
	private JTextField txtNRCView;
	private String stateCode;
	private String regionCode;
	private String nrcType;
	private String nrcNumberField;

	private SystemColor bgColor = SystemColor.inactiveCaption;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AcceptRejectFormView frame = new AcceptRejectFormView(1, 1);
					Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
					frame.setBounds(0, 0, screenSize.width, screenSize.height);
					frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
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
	public AcceptRejectFormView(int id, int loggedInId) {
		applicantID = id;
		setResizable(false);
		setTitle("Recruitment Accept Reject Form");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 684, 551);
		getContentPane().setLayout(new BorderLayout());
		setExtendedState(JFrame.MAXIMIZED_BOTH);

		// Create a wrapper panel with GridBagLayout
		JPanel wrapper = new JPanel(new GridBagLayout());

		JPanel formPanel = new JPanel();
		formPanel.setPreferredSize(new Dimension(700, 670));
		formPanel.setBackground(bgColor);
		formPanel.setLayout(null);

		wrapper.add(formPanel);

		getContentPane().add(wrapper, BorderLayout.CENTER);

		// Align Panel to Center
//		addComponentListener(new ComponentAdapter() {
//			@Override
//			public void componentResized(ComponentEvent e) {
//				int panelWidth = 680;
//				int parentWidth = getContentPane().getWidth();
//				int xPosition = (parentWidth - panelWidth) / 2;
//				formPanel.setBounds(xPosition, 50, panelWidth, 1000);
//			}
//		});

		JLabel lblName = new JLabel("<html>Name <span style='color:red;'>*</span></html>");
		lblName.setBounds(75, 30, 150, 13);
		formPanel.add(lblName);

		JLabel lblNRC = new JLabel("<html>NRC <span style='color:red;'>*</span></html>");
		lblNRC.setBounds(75, 82, 150, 13);
		formPanel.add(lblNRC);

		JLabel lblFatherName = new JLabel("<html>Father Name <span style='color:red;'>*</span></html>");
		lblFatherName.setBounds(75, 132, 150, 13);
		formPanel.add(lblFatherName);

		JLabel lblDOB = new JLabel("<html>DOB of NRC Card <span style='color:red;'>*</span></html>");
		lblDOB.setBounds(75, 182, 150, 13);
		formPanel.add(lblDOB);

		JLabel lblGender = new JLabel("<html>Gender <span style='color:red;'>*</span></html>");
		lblGender.setBounds(75, 226, 150, 13);
		formPanel.add(lblGender);

		JLabel lblMaritalStatus = new JLabel("<html>Martial Status <span style='color:red;'>*</span></html>");
		lblMaritalStatus.setBounds(75, 272, 150, 13);
		formPanel.add(lblMaritalStatus);

		JLabel lblAddress = new JLabel("Address");
		lblAddress.setBounds(75, 320, 150, 13);
		formPanel.add(lblAddress);

		JLabel lblPhone = new JLabel("<html>Phone <span style='color:red;'>*</span></html>");
		lblPhone.setBounds(75, 366, 150, 13);
		formPanel.add(lblPhone);

		JLabel lblEducation = new JLabel("<html>Education <span style='color:red;'>*</span></html>");
		lblEducation.setBounds(75, 415, 150, 13);
		formPanel.add(lblEducation);

		JLabel lblMail = new JLabel("<html>Mail <span style='color:red;'>*</span></html>");
		lblMail.setBounds(75, 494, 150, 13);
		formPanel.add(lblMail);

		JLabel lblNewLabel_5_5 = new JLabel("<html>Matriculation Mark <span style='color:red;'>*</span></html>");
		lblNewLabel_5_5.setBounds(75, 551, 150, 13);
		formPanel.add(lblNewLabel_5_5);

		txtName = new JTextField();
		txtName.setBounds(237, 24, 200, 25);
		formPanel.add(txtName);
		txtName.setColumns(10);

		/*
		 * NRC Panel Start
		 */
		JPanel nrcInnerPanel = new JPanel(new GridBagLayout());
		nrcInnerPanel.setSize(250, 25);
		nrcInnerPanel.setLocation(237, 77);

		// 12/
		stateCodeCb = new JComboBox<String>();
		for (int i = 1; i <= 14; i++) {
			stateCodeCb.addItem(Integer.toString(i) + "/");
		}

		// TaMaNa
		regionCodeCb = new JComboBox<String>();
//		regionCodeCb.setPreferredSize(new Dimension(92, regionCodeCb.getPreferredSize().height));
		List<String> regions = new NRCHelper().getRegions("1/");
		for (String region : regions) {
			regionCodeCb.addItem(region);
		}

		// N
		nrcTypeCb = new JComboBox<String>();
		nrcTypeCb.addItem("N");
		nrcTypeCb.addItem("E");
		nrcTypeCb.addItem("P");
		nrcTypeCb.addItem("T");
		nrcTypeCb.addItem("R");
		nrcTypeCb.addItem("S");

		// NRC number
		txtNRCView = new JTextField(7);
		txtNRCView.setPreferredSize(new Dimension(txtNRCView.getPreferredSize().width, 25));

		GridBagConstraints gbc_stateCodeCb = new GridBagConstraints();
		gbc_stateCodeCb.anchor = GridBagConstraints.WEST;
		nrcInnerPanel.add(stateCodeCb, gbc_stateCodeCb);

		GridBagConstraints gbc_regionCodeCb = new GridBagConstraints();
		nrcInnerPanel.add(regionCodeCb, gbc_regionCodeCb);

		GridBagConstraints gbc_nrcTypeCb = new GridBagConstraints();
		nrcInnerPanel.add(nrcTypeCb, gbc_nrcTypeCb);

		GridBagConstraints gbc_nrcNumberField = new GridBagConstraints();
		nrcInnerPanel.add(txtNRCView, gbc_nrcNumberField);

		formPanel.add(nrcInnerPanel);
		/*
		 * NRC Panel End
		 */

		txtFatherName = new JTextField();
		txtFatherName.setColumns(10);
		txtFatherName.setBounds(237, 126, 200, 25);
		formPanel.add(txtFatherName);

		txtPhone = new JTextField();
		txtPhone.setColumns(10);
		txtPhone.setBounds(237, 360, 200, 25);
		formPanel.add(txtPhone);

		txtEducation = new JTextField();
		txtEducation.setColumns(10);
		txtEducation.setBounds(237, 409, 200, 25);
		formPanel.add(txtEducation);

		txtMail = new JTextField();
		txtMail.setColumns(10);
		txtMail.setBounds(237, 488, 200, 25);
		formPanel.add(txtMail);

		txtMark = new JTextField();
		txtMark.setColumns(10);
		txtMark.setBounds(237, 545, 200, 25);
		formPanel.add(txtMark);

		txtAddress = new JTextArea();
		txtAddress.setBounds(237, 298, 200, 35);
		formPanel.add(txtAddress);

		rdoMale = new JRadioButton("Male");
		rdoMale.setBounds(233, 222, 100, 21);
		rdoMale.setBackground(bgColor);
		rdoMale.setSelected(true);
		formPanel.add(rdoMale);

		rdoFemale = new JRadioButton("Female");
		rdoFemale.setBounds(337, 222, 100, 21);
		rdoFemale.setBackground(bgColor);
		formPanel.add(rdoFemale);

		rdoGender = new ButtonGroup();
		rdoGender.add(rdoMale);
		rdoGender.add(rdoFemale);

		rdoSingle = new JRadioButton("Single");
		rdoSingle.setBounds(233, 268, 100, 21);
		rdoSingle.setBackground(bgColor);
		rdoSingle.setSelected(true);
		formPanel.add(rdoSingle);

		rdoMarried = new JRadioButton("Married");
		rdoMarried.setBounds(337, 268, 100, 21);
		rdoMarried.setBackground(bgColor);
		formPanel.add(rdoMarried);

		rdoMaritalStatus = new ButtonGroup();
		rdoMaritalStatus.add(rdoSingle);
		rdoMaritalStatus.add(rdoMarried);

		txtDOB = new JDateChooser();
		txtDOB.setBounds(237, 176, 200, 25);
		txtDOB.setDateFormatString("yyyy/MM/dd");
		formPanel.add(txtDOB);

		rdoBachelor = new JRadioButton("Bachelor");
		rdoBachelor.setBounds(233, 438, 100, 21);
		rdoBachelor.setBackground(bgColor);
		rdoBachelor.setSelected(true);
		formPanel.add(rdoBachelor);

		rdoMaster = new JRadioButton("Master");
		rdoMaster.setBounds(337, 438, 100, 21);
		rdoMaster.setBackground(bgColor);
		formPanel.add(rdoMaster);

		rdoDegreeType = new ButtonGroup();
		rdoDegreeType.add(rdoMaster);
		rdoDegreeType.add(rdoBachelor);

		ddlMajor = new JComboBox();
		ddlMajor.setModel(new DefaultComboBoxModel(new String[] { "Computer Science", "Computer Technology",
				"Computer Engineering and Information Technology", "Software Engineering",
				"Information Science and Technology", "Information Technology", "Other" }));
		ddlMajor.setBounds(456, 409, 200, 25);
		formPanel.add(ddlMajor);

		txtMajor = new JTextField();
		txtMajor.setColumns(10);
		txtMajor.setBounds(456, 444, 200, 25);
		txtMajor.setVisible(false);
		formPanel.add(txtMajor);

//		btnAccept = new JButton("Accept");
//		btnAccept.setBorderPainted(false);
//		btnAccept.setForeground(Color.white);
//		btnAccept.setBackground(new Color(94, 148, 255));

		btnAccept = addActionButton("Accept", new Color(54, 118, 247));
		btnAccept.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnAccept.setBounds(174, 600, 91, 25);
		formPanel.add(btnAccept);

//		btnReject = new JButton("Reject");
//		btnReject.setBorderPainted(false);
//		btnReject.setForeground(Color.white);
//		btnReject.setBackground(new Color(94, 148, 255));
		btnReject = addActionButton("Reject", new Color(54, 118, 247));
		btnReject.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnReject.setBounds(286, 600, 91, 25);
		// btnReject.setCursor(new Cursor(Cursor.HAND_CURSOR));
		formPanel.add(btnReject);

		btnAccept.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				String applicantNRC = stateCodeCb.getSelectedItem().toString()
						+ regionCodeCb.getSelectedItem().toString() + "(" + nrcTypeCb.getSelectedItem().toString() + ")"
						+ txtNRCView.getText();

				ApplicantController applicantController = new ApplicantController();
				ApplicantModel applicants = applicantController.getApplicantsDetails(id);
				boolean success = applicantController.updateApplicantStatus(applicantNRC,
						ApplicantModel.ApplicantStatus.ACCEPT);

				if (success) {
					JOptionPane.showMessageDialog(null,
							"Applicant has been accepted and noticed the applicant via Email.");
					String subject = "Welcome to Myanmar DCR Co., Ltd.";

					String htmlMessage = "<div style='font-family: Arial, sans-serif; padding: 20px; color: #333;'>"
							+ "<h2 style='color: #0073e6;'>🎉 Congratulations!</h2>"
							+ "<p>We're excited to have you join our team at <strong>Myanmar DCR Co., Ltd.</strong></p>"
							+ "<p>Please feel free to reach out with any questions.</p>" + "<br>"
							+ "<p style='font-size: 12px; color: gray;'>This is an automated message. Please do not reply.</p>"
							+ "</div>";
					sendEmail(applicants.getMail(), subject, htmlMessage);
					dispose();
					ApplicantListView applicantListView = new ApplicantListView(loggedInId);
					applicantListView.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null, "Failed to update applicant status.", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}

		});

		btnReject.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String applicantNRC = stateCodeCb.getSelectedItem().toString()
						+ regionCodeCb.getSelectedItem().toString() + "(" + nrcTypeCb.getSelectedItem().toString() + ")"
						+ txtNRCView.getText();

				ApplicantController applicantController = new ApplicantController();
				boolean success = applicantController.updateApplicantStatus1(applicantNRC,
						ApplicantModel.ApplicantStatus.REJECT);

				if (success) {
					JOptionPane.showMessageDialog(null, "Applicant has been rejected.");
					ApplicantListView applicantListView = new ApplicantListView(loggedInId);
					applicantListView.setVisible(true);
					dispose();
				} else {
					JOptionPane.showMessageDialog(null, "Failed to update applicant status.", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

//		btnCancel = new JButton("Cancel");
//		btnCancel.setBorderPainted(false);
//		btnCancel.setForeground(Color.white);
		btnCancel = addActionButton("Cancel", new Color(54, 118, 247));
		btnCancel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnCancel.setBackground(new Color(94, 148, 255));
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ApplicantListView applicantListView = new ApplicantListView(loggedInId);
				applicantListView.setVisible(true);
				dispose();
			}
		});
		btnCancel.setBounds(394, 600, 91, 25);
		// btnCancel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		formPanel.add(btnCancel);

		/*
		 * Error Message Label Start
		 */
		rfvName = new JLabel("Error");
		rfvName.setFont(new Font("MS UI Gothic", Font.PLAIN, 12));
		rfvName.setBounds(237, 34, 200, 13);
		rfvName.setVisible(false);
		formPanel.add(rfvName);

		rfvNRC = new JLabel("Error");
		rfvNRC.setFont(new Font("MS UI Gothic", Font.PLAIN, 12));
		rfvNRC.setBounds(237, 83, 200, 13);
		rfvNRC.setVisible(false);
		formPanel.add(rfvNRC);

		rfvFatherName = new JLabel("Error");
		rfvFatherName.setFont(new Font("MS UI Gothic", Font.PLAIN, 12));
		rfvFatherName.setBounds(237, 133, 200, 13);
		rfvFatherName.setVisible(false);
		formPanel.add(rfvFatherName);

		rfvDOB = new JLabel("Error");
		rfvDOB.setFont(new Font("MS UI Gothic", Font.PLAIN, 12));
		rfvDOB.setBounds(237, 183, 200, 13);
		rfvDOB.setVisible(false);
		formPanel.add(rfvDOB);

		rfvGender = new JLabel("Error");
		rfvGender.setFont(new Font("MS UI Gothic", Font.PLAIN, 12));
		rfvGender.setBounds(237, 229, 200, 13);
		rfvGender.setVisible(false);
		formPanel.add(rfvGender);

		rfvMartialStatus = new JLabel("Error");
		rfvMartialStatus.setFont(new Font("MS UI Gothic", Font.PLAIN, 12));
		rfvMartialStatus.setBounds(237, 272, 200, 13);
		rfvMartialStatus.setVisible(false);
		formPanel.add(rfvMartialStatus);

		rfvPhone = new JLabel("Error");
		rfvPhone.setFont(new Font("MS UI Gothic", Font.PLAIN, 12));
		rfvPhone.setBounds(237, 366, 200, 13);
		rfvPhone.setVisible(false);
		formPanel.add(rfvPhone);

		rfvEducation = new JLabel("Error");
		rfvEducation.setFont(new Font("MS UI Gothic", Font.PLAIN, 12));
		rfvEducation.setBounds(237, 445, 200, 13);
		rfvEducation.setVisible(false);
		formPanel.add(rfvEducation);

		rfvMajor = new JLabel("Error");
		rfvMajor.setFont(new Font("MS UI Gothic", Font.PLAIN, 12));
		rfvMajor.setBounds(456, 456, 200, 13);
		rfvMajor.setVisible(false);
		formPanel.add(rfvMajor);

		rfvMail = new JLabel("Error");
		rfvMail.setFont(new Font("MS UI Gothic", Font.PLAIN, 12));
		rfvMail.setBounds(237, 502, 200, 13);
		rfvMail.setVisible(false);
		formPanel.add(rfvMail);

		rfvMark = new JLabel("Error");
		rfvMark.setFont(new Font("MS UI Gothic", Font.PLAIN, 12));
		rfvMark.setBounds(237, 552, 200, 13);
		rfvMark.setVisible(false);
		formPanel.add(rfvMark);

		JPanel menuPanel = new JPanel();
		menuPanel.setBackground(new Color(0, 0, 0));
		menuPanel.setPreferredSize(new Dimension(450, 50));
		getContentPane().add(menuPanel, BorderLayout.NORTH);
		menuPanel.setLayout(null);

		ImageIcon titleImage = new ImageIcon(getClass().getResource("/image/mdcr3.png"));
		JLabel imageTitleLabel = new JLabel(titleImage);
		imageTitleLabel.setBounds(25, 0, 141, 50);
		menuPanel.add(imageTitleLabel);

		JLabel lblTitleBar = new JLabel("Myanmar DCR Recruitment System");
		lblTitleBar.setForeground(Color.WHITE);
		lblTitleBar.setFont(new Font("MS UI Gothic", Font.PLAIN, 20));
		lblTitleBar.setBounds(216, 10, 340, 30);
		menuPanel.add(lblTitleBar);
		/*
		 * Error Message Label End
		 */

		// combo box listener for NRC
		stateCodeCb.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String selectedItem = (String) stateCodeCb.getSelectedItem();
				stateCode = selectedItem;
				regionCodeCb.removeAllItems();
				List<String> regions = new NRCHelper().getRegions(stateCode);
				for (String region : regions) {
					regionCodeCb.addItem(region);
				}
			}
		});

		regionCodeCb.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String selectedItem = (String) regionCodeCb.getSelectedItem();
				regionCode = selectedItem;
			}
		});

		nrcTypeCb.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String selectedItem = (String) nrcTypeCb.getSelectedItem();
				nrcType = selectedItem;
			}
		});

		txtNRCView.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyTyped(java.awt.event.KeyEvent e) {
				char c = e.getKeyChar();
				String currentText = txtNRCView.getText();

				// Allow backspace and delete
				if (Character.isISOControl(c)) {
					return;
				}

				// Only allow digits
				if (!Character.isDigit(c)) {
					e.consume();
					return;
				}

				// Limit to 11 digits
				if (currentText.length() >= 6) {
					e.consume();
				}
			}
		});

		// Major Hide Show For Selected Value Other
		ddlMajor.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (ddlMajor.getSelectedItem().equals("Other")) {
					txtMajor.setVisible(true);
					txtMajor.setText("");
				} else {
					txtMajor.setVisible(false);
					txtMajor.setText("");
				}
			}
		});

		loadApplicantData(applicantID);

	}

	public void loadApplicantData(int applicantID) {
		ApplicantModel applicants = new ApplicantModel();
		ApplicantController applicantController = new ApplicantController();
		applicants = applicantController.getApplicantsDetails(applicantID);

		// Set text fields to read-only (non-editable)
		txtName.setText(applicants.getApplicantName());
		txtName.setForeground(Color.gray);
		txtName.setEditable(false);

		txtFatherName.setText(applicants.getFatherName());
		txtFatherName.setForeground(Color.gray);
		txtFatherName.setEditable(false);

		// Populate NRC fields (this should be set via combo box or text fields)
		populateNrcFields(applicants.getApplicantNRC());

		// Set date of birth
		LocalDate localDate = applicants.getDobofNRC(); // LocalDate from your model
		if (localDate != null) {
			Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
			txtDOB.setDate(date);
		} else {
			txtDOB.setDate(null); // clear date if null
		}
		txtDOB.setEnabled(false);

		SwingUtilities.invokeLater(() -> {
			if (txtDOB.getDateEditor().getUiComponent() instanceof JTextField) {
				JTextField editor = (JTextField) txtDOB.getDateEditor().getUiComponent();
				editor.setForeground(Color.gray);
				editor.setDisabledTextColor(Color.gray);
			}
		});

		// Set phone, education, mail, mark, and address as read-only
		txtPhone.setText(applicants.getPhoneNo());
		txtPhone.setForeground(Color.gray);
		txtPhone.setEditable(false);

		txtEducation.setText(applicants.getEducation());
		txtEducation.setForeground(Color.gray);
		txtEducation.setEditable(false);

		txtMail.setText(applicants.getMail());
		txtMail.setForeground(Color.gray);
		txtMail.setEditable(false);

		txtMark.setText(String.valueOf(applicants.getMark()));
		txtMark.setForeground(Color.gray);
		txtMark.setEditable(false);

		txtAddress.setText(applicants.getAddress());
		txtAddress.setForeground(Color.gray);
		txtAddress.setEditable(false);

		// Set gender radio buttons to read-only
		if ("Male".equalsIgnoreCase(applicants.getGender())) {
			rdoMale.setSelected(true);
		} else {
			rdoFemale.setSelected(true);
		}
		rdoMale.setEnabled(false);
		rdoFemale.setEnabled(false);

		// Set marital status radio buttons to read-only
		if ("Single".equalsIgnoreCase(applicants.getMaritalStatus())) {
			rdoSingle.setSelected(true);
		} else {
			rdoMarried.setSelected(true);
		}
		rdoSingle.setEnabled(false);
		rdoMarried.setEnabled(false);

		if ("Bachelor".equalsIgnoreCase(applicants.getDegreeType())) {
			rdoBachelor.setSelected(true);
		} else {
			rdoMaster.setSelected(true);
		}
		rdoBachelor.setEnabled(false);
		rdoMaster.setEnabled(false);

		// Set major combo box to read-only
		String[] majors = new String[] { "Computer Science", "Computer Technology",
				"Computer Engineering and Information Technology", "Software Engineering",
				"Information Science and Technology", "Information Technology", "Other" };

		for (String major : majors) {
			if (major.equals(applicants.getMajor())) {
				// System.out.println(applicants.getMajor());
				ddlMajor.setSelectedItem(major);
				ddlMajor.setForeground(Color.gray);
				ddlMajor.setEnabled(false);
				break;

			} else {
				// System.out.println("else"+applicants.getMajor());
				ddlMajor.setSelectedItem("Other");
				ddlMajor.setForeground(Color.gray);
				ddlMajor.setEnabled(false);
				txtMajor.setText(applicants.getMajor());
				txtMajor.setForeground(Color.gray);
				txtMajor.setVisible(true);
				txtMajor.setEditable(false); // Make the text field read-only

			}
		}

		UIManager.put("ComboBox.disabledForeground", Color.gray);
	}

	private void populateNrcFields(String fullNrc) {
		// fullNrc looks like "12/BaHaNa(N)108656"
		// 1) Split off the state code (everything up to and including the slash)
		int slash = fullNrc.indexOf('/') + 1;
		String state = fullNrc.substring(0, slash); // "12/"
		String rest = fullNrc.substring(slash); // "BaHaNa(N)108656"

		// 2) Split the region (up to the '(' ) and the rest
		int paren = rest.indexOf('(');
		String region = rest.substring(0, paren); // "BaHaNa"
		String after = rest.substring(paren + 1); // "N)108656"

		// 3) Split type (up to the ')') and the final number
		int closeParen = after.indexOf(')');
		String type = after.substring(0, closeParen); // "N"
		String number = after.substring(closeParen + 1); // "108656"

		// 4) Now set them on your UI (but keep them non-editable)
		stateCodeCb.setSelectedItem(state);
		regionCodeCb.setSelectedItem(region);
		nrcTypeCb.setSelectedItem(type);
		txtNRCView.setText(number);

		// Make the NRC fields read-only
		stateCodeCb.setEnabled(false);
		regionCodeCb.setEnabled(false);
		nrcTypeCb.setEnabled(false);
		txtNRCView.setEditable(false);
	}

	private void sendEmail(String recipient, String subject, String messageText) {
		// SMTP server configuration
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "465");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

		// Create a session with authenticator
		Session session = Session.getInstance(props, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("hein29939@gmail.com", "nkhxdxpxgzsqnzbx");
			}
		});

		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(senderEmail, "Group-3"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
			message.setSubject(subject);
//			message.setText(messageText);
			// Set the HTML content
			message.setContent(messageText, "text/html; charset=utf-8");

			Transport.send(message);
			System.out.println("Email sent successfully!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private JButton addActionButton(String name, Color hoverColor) {
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
				button.setBackground(hoverColor); // Change background on hover
			}

			public void mouseExited(java.awt.event.MouseEvent evt) {
				button.setForeground(new Color(255, 255, 255));
				button.setBackground(new Color(94, 148, 255)); // Change background on hover exit
			}
		});
		return button;
	}

}
