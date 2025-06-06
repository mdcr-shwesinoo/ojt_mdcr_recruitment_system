package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import config.Checking;
import config.Common;
import controller.ApplicantController;
import model.ApplicantModel;
import utils.NRCHelper;

public class ApplyFormView extends JFrame {

	private static final long serialVersionUID = 1L;
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
	private JButton btnApply;
	private JButton btnCancel;
	private JLabel rfvName;
	private JLabel rfvNRC;
	private JLabel rfvFatherName;
	private JLabel rfvDOB;
	private JLabel rfvAddress;
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

	// Placeholder
	private String namePH = "E.g.Su Su";
	private String nrcPH = "343545";
	private String fatherNamePH = "E.g.U Aung Aung";
	private String dobPH = "YYYY/MM/DD";
	private String phonePH = "E.g.09961234567, 01214231";
	private String eduPH = "E.g.B.C.Sc/B.C.Tech/B.E/B.Sc";
	private String mailPH = "soethandaroo@myanmardcr.com";
	private String majorPH = "E.g.Cyber Security";
	private String markPH = "E.g.455";

	private SystemColor bgColor = SystemColor.inactiveCaption;
	private int loggedInId;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ApplyFormView frame = new ApplyFormView(1);
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
	public ApplyFormView(int loggedInId) {
		this.loggedInId = loggedInId;
		setTitle("Recruitment Apply Form");
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
//				formPanel.setBounds(xPosition, 60, panelWidth, 650);
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
		txtName.setBorder(BorderFactory.createEmptyBorder());
		Common.setPlaceholder(txtName, namePH);
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
		regionCodeCb.setPreferredSize(new Dimension(92, regionCodeCb.getPreferredSize().height));
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
		txtNRCView.setBorder(BorderFactory.createEmptyBorder());
		Common.setPlaceholder(txtNRCView, nrcPH);
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
		txtFatherName.setBorder(BorderFactory.createEmptyBorder());
		Common.setPlaceholder(txtFatherName, fatherNamePH);
		formPanel.add(txtFatherName);

		txtPhone = new JTextField();
		txtPhone.setColumns(10);
		txtPhone.setBounds(237, 360, 200, 25);
		txtPhone.setBorder(BorderFactory.createEmptyBorder());
		Common.setPlaceholder(txtPhone, phonePH);
		formPanel.add(txtPhone);

		txtEducation = new JTextField();
		txtEducation.setColumns(10);
		txtEducation.setBounds(237, 409, 200, 25);
		txtEducation.setBorder(BorderFactory.createEmptyBorder());
		Common.setPlaceholder(txtEducation, eduPH);
		formPanel.add(txtEducation);

		txtMail = new JTextField();
		txtMail.setColumns(10);
		txtMail.setBounds(237, 488, 200, 25);
		txtMail.setBorder(BorderFactory.createEmptyBorder());
		Common.setPlaceholder(txtMail, mailPH);
		formPanel.add(txtMail);

		txtMark = new JTextField();
		txtMark.setColumns(10);
		txtMark.setBounds(237, 545, 200, 25);
		txtMark.setBorder(BorderFactory.createEmptyBorder());
		Common.setPlaceholder(txtMark, markPH);
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
		txtDOB.setBorder(BorderFactory.createEmptyBorder());

		// Placeholder For DOB of NRC Card
		editor = (JTextField) txtDOB.getDateEditor().getUiComponent();
		editor.setEditable(false);
		editor.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		editor.setFocusable(false);
		editor.setBorder(BorderFactory.createEmptyBorder());
		Common.setPlaceholder(editor, dobPH);
		editor.setForeground(Color.GRAY);

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
		txtMajor.setBorder(BorderFactory.createEmptyBorder());
		Common.setPlaceholder(txtMajor, majorPH);
		txtMajor.setVisible(false);
		formPanel.add(txtMajor);

		btnApply = Common.addActionButton("Apply");
		btnApply.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!isFormValid()) {
					insertApplicants();
				}
			}
		});
		btnApply.setBounds(194, 600, 91, 25);
//		btnApply.setForeground(Color.white);
//		btnApply.setBackground(new Color(94, 148, 255));
//		btnApply.setBorderPainted(false);
		btnApply.setCursor(new Cursor(Cursor.HAND_CURSOR));
		formPanel.add(btnApply);

		btnCancel = Common.addActionButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Clear();
			}
		});
		btnCancel.setBounds(344, 600, 91, 25);
//		btnCancel.setForeground(Color.white);
//		btnCancel.setBackground(new Color(94, 148, 255));
//		btnCancel.setBorderPainted(false);
		btnCancel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		formPanel.add(btnCancel);

		/*
		 * Error Message Label Start
		 */
		rfvName = new JLabel("Error");
		rfvName.setFont(new Font("MS UI Gothic", Font.PLAIN, 12));
		rfvName.setBounds(237, 54, 200, 13);
		rfvName.setVisible(false);
		formPanel.add(rfvName);

		rfvNRC = new JLabel("Error");
		rfvNRC.setFont(new Font("MS UI Gothic", Font.PLAIN, 12));
		rfvNRC.setBounds(237, 103, 200, 13);
		rfvNRC.setVisible(false);
		formPanel.add(rfvNRC);

		rfvFatherName = new JLabel("Error");
		rfvFatherName.setFont(new Font("MS UI Gothic", Font.PLAIN, 12));
		rfvFatherName.setBounds(237, 153, 220, 13);
		rfvFatherName.setVisible(false);
		formPanel.add(rfvFatherName);

		rfvDOB = new JLabel("Error");
		rfvDOB.setFont(new Font("MS UI Gothic", Font.PLAIN, 12));
		rfvDOB.setBounds(237, 203, 200, 13);
		rfvDOB.setVisible(false);
		formPanel.add(rfvDOB);

		rfvAddress = new JLabel("Error");
		rfvAddress.setFont(new Font("MS UI Gothic", Font.PLAIN, 12));
		rfvAddress.setBounds(237, 337, 220, 13);
		rfvAddress.setVisible(false);
		formPanel.add(rfvAddress);

		rfvMartialStatus = new JLabel("Error");
		rfvMartialStatus.setFont(new Font("MS UI Gothic", Font.PLAIN, 12));
		rfvMartialStatus.setBounds(237, 272, 200, 13);
		rfvMartialStatus.setVisible(false);
		formPanel.add(rfvMartialStatus);

		rfvPhone = new JLabel("Error");
		rfvPhone.setFont(new Font("MS UI Gothic", Font.PLAIN, 12));
		rfvPhone.setBounds(237, 386, 200, 13);
		rfvPhone.setVisible(false);
		formPanel.add(rfvPhone);

		rfvEducation = new JLabel("Error");
		rfvEducation.setFont(new Font("MS UI Gothic", Font.PLAIN, 12));
		rfvEducation.setBounds(237, 465, 200, 13);
		rfvEducation.setVisible(false);
		formPanel.add(rfvEducation);

		rfvMajor = new JLabel("Error");
		rfvMajor.setFont(new Font("MS UI Gothic", Font.PLAIN, 12));
		rfvMajor.setBounds(456, 476, 200, 13);
		rfvMajor.setVisible(false);
		formPanel.add(rfvMajor);

		rfvMail = new JLabel("Error");
		rfvMail.setFont(new Font("MS UI Gothic", Font.PLAIN, 12));
		rfvMail.setBounds(237, 522, 200, 13);
		rfvMail.setVisible(false);
		formPanel.add(rfvMail);

		rfvMark = new JLabel("Error");
		rfvMark.setFont(new Font("MS UI Gothic", Font.PLAIN, 12));
		rfvMark.setBounds(237, 572, 200, 13);
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

		JLabel lblChangePassword = new JLabel("Change Password");
		lblChangePassword.setBounds(1200, 10, 340, 30);
		lblChangePassword.setForeground(new Color(0xCCCCCC));
		lblChangePassword.setFont(new Font("MS UI Gothic", Font.PLAIN, 17));
		menuPanel.add(lblChangePassword);

		lblChangePassword.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ChangePasswordView changePasswordView = new ChangePasswordView(loggedInId, ApplyFormView.this);
				changePasswordView.setVisible(true);
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				lblChangePassword.setForeground(Color.WHITE); // change to blue on hover
			}

			@Override
			public void mouseExited(MouseEvent e) {
				lblChangePassword.setForeground(new Color(0xCCCCCC));
			}

		});
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
					txtMajor.setText(majorPH);
				} else {
					txtMajor.setVisible(false);
					txtMajor.setText("");
					rfvMajor.setVisible(false);
				}
			}
		});
	}

	// Check Validation
	public boolean isFormValid() {
		boolean hasError = false;

		String name = txtName.getText();
		String nrc = txtNRCView.getText();
		String fatherName = txtFatherName.getText();
		String address = txtAddress.getText();
		String phone = txtPhone.getText();
		String education = txtEducation.getText();
		String mail = txtMail.getText();
		String mark = txtMark.getText();
		String major = txtMajor.getText();
		Date dob = txtDOB.getDate();
		nrcNumberField = stateCodeCb.getSelectedItem() + "" + regionCodeCb.getSelectedItem() + "("
				+ nrcTypeCb.getSelectedItem() + ")" + txtNRCView.getText();

		// Validate Name
		if (Checking.IsEmpty(name) || name.equals(namePH)) {
			showError(rfvName, "Name is required");
			hasError = true;
		} else if (!Checking.IsValidName(name)) {
			showError(rfvName, "Name should not contain number");
			hasError = true;
		} else {
			hideError(rfvName);
		}

		// Validate NRC
		if (Checking.IsEmpty(nrc) || nrc.equals(nrcPH)) {
			showError(rfvNRC, "NRC is required");
			hasError = true;
		} else if (Checking.isNrcDuplicate(nrcNumberField)) {
			showError(rfvNRC, "Sorry,you can't apply with equal NRC number");
			hasError = true;
		} else {
			hideError(rfvNRC);
		}

		// Validate Father Name
		if (Checking.IsEmpty(fatherName) || fatherName.equals(fatherNamePH)) {
			showError(rfvFatherName, "Father Name is required");
			hasError = true;
		} else if (!Checking.IsValidName(fatherName)) {
			showError(rfvFatherName, "Father name should not contain number");
			hasError = true;
		} else {
			hideError(rfvFatherName);
		}

		// Validate Address
		if (address.length() > 250) {
			showError(rfvAddress, "Address must be 250 characters or less.");
			hasError = true;
		} else {
			hideError(rfvAddress);
		}

		// Validate Phone
		if (Checking.IsEmpty(phone) || phone.equals(phonePH)) {
			showError(rfvPhone, "Phone is required");
			hasError = true;
		} else if (!Checking.IsValidPhone(phone)) {
			showError(rfvPhone, "Invalid phone number");
			hasError = true;
		} else {
			hideError(rfvPhone);
		}

		// Validate DOB
		if (dob == null || dob.equals(dobPH)) {
			showError(rfvDOB, "DOB is required");
			hasError = true;
		} else {
			hideError(rfvDOB);
		}

		// Validate Major (only when "Other" is selected)
		if ("Other".equals(ddlMajor.getSelectedItem()) && (Checking.IsEmpty(major) || major.equals(majorPH))) {
			showError(rfvMajor, "Major is required");
			hasError = true;
		} else {
			hideError(rfvMajor);
		}

		// Validate education, mail, and mark
		if (Checking.IsEmpty(education) || education.equals(eduPH)) {
			showError(rfvEducation, "Education is required");
			hasError = true;
		} else {
			hideError(rfvEducation);
		}

		if (Checking.IsEmpty(mail) || mail.equals(mailPH)) {
			showError(rfvMail, "Email is required");
			hasError = true;
		} else if (!Checking.IsValidEmail(mail)) {
			showError(rfvMail, "Mail format is incorrect");
			hasError = true;
		} else {
			hideError(rfvMail);
		}

		if (Checking.IsEmpty(mark) || mark.equals(markPH)) {
			showError(rfvMark, "Mark is required");
			hasError = true;
		} else if (!Checking.isValidMark(mark)) {
			showError(rfvMark, "Mark shoud be between 240 and 600");
			hasError = true;
		} else {
			hideError(rfvMark);
		}

		return hasError;
	}

	// Helper method for validating empty fields
	private void validateField(String value, JLabel label, String message) {
		if (Checking.IsEmpty(value)) {
			showError(label, message);
		} else {
			hideError(label);
		}
	}

	// Helper methods to show/hide validation messages
	private void showError(JLabel label, String message) {
		label.setVisible(true);
		label.setText(message);
		label.setForeground(Color.red);
	}

	private void hideError(JLabel label) {
		label.setText("");
		label.setVisible(false);
	}

	public void insertApplicants() {
		try {
			ApplicantModel applicants = new ApplicantModel();
			ApplicantController applicantController = new ApplicantController();

			// NRC number parts
			String nrcNumberField = txtNRCView.getText();
			String stateCode = (String) stateCodeCb.getSelectedItem();
			String regionCode = (String) regionCodeCb.getSelectedItem();
			String nrcType = (String) nrcTypeCb.getSelectedItem();

			// Validate NRC parts
			if (stateCode == null || regionCode == null || nrcType == null || nrcNumberField.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Please complete the NRC fields.");
				return;
			}

			// Construct NRC string
			String fullNRC = stateCode + regionCode + "(" + nrcType + ")" + nrcNumberField;
			applicants.setApplicantNRC(fullNRC);

			// Name, Father, DOB
			applicants.setApplicantName(txtName.getText());
			applicants.setFatherName(txtFatherName.getText());

			if (txtDOB.getDate() != null) {
				LocalDate dob = txtDOB.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				applicants.setDobofNRC(dob);
			}

			// Gender
			String gender = rdoMale.isSelected() ? "Male" : "Female";
			applicants.setGender(gender);

			// Marital Status
			String maritalStatus = rdoSingle.isSelected() ? "Single" : "Married";
			applicants.setMaritalStatus(maritalStatus);

			// Address, Phone, Education
			applicants.setAddress(txtAddress.getText());
			applicants.setPhoneNo(txtPhone.getText());
			applicants.setEducation(txtEducation.getText());

			// Major
			String selectedMajor = ddlMajor.getSelectedItem().toString();
			String major = selectedMajor.equals("Other") ? txtMajor.getText() : selectedMajor;
			applicants.setMajor(major);

			String degreeType = rdoBachelor.isSelected() ? "Bachelor" : "Master";
			applicants.setDegreeType(degreeType);

			// Email and Mark
			applicants.setMail(txtMail.getText());
			applicants.setMark(Integer.parseInt(txtMark.getText())); // You might want to validate this

			// Default status
			applicants.setStatus(ApplicantModel.ApplicantStatus.PENDING);

			// Insert into DB
			boolean inserted = applicantController.insertApplicants(applicants);

			if (inserted) {
//				JOptionPane.showMessageDialog(null, "Applicant inserted successfully!");
				Clear();

				// Open the ApplySuccessView
				ApplySuccessView successView = new ApplySuccessView(loggedInId);
				successView.setExtendedState(JFrame.MAXIMIZED_BOTH);
				successView.setVisible(true);
				dispose();
			} else {
				JOptionPane.showMessageDialog(null, "Failed to insert applicant.");
			}

		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
		}
	}

	// Clears all input fields in the form when the Cancel button is clicked
	public void Clear() {
		txtName.setText(namePH);
		txtFatherName.setText(fatherNamePH);
		txtPhone.setText(phonePH);
		txtEducation.setText(eduPH);
		txtMail.setText(mailPH);
		txtMark.setText(markPH);
		txtMajor.setText(majorPH);
		txtAddress.setText("");
		txtNRCView.setText(nrcPH);
		txtDOB.setDate(null);
		editor.setText(dobPH);
		ddlMajor.setSelectedIndex(0);
		rdoMale.setSelected(true);
		rdoSingle.setSelected(true);
		rdoBachelor.setSelected(true);
		stateCodeCb.setSelectedIndex(0);
		nrcTypeCb.setSelectedIndex(0);
		txtName.requestFocus(true);
		rfvName.setVisible(false);
		rfvNRC.setVisible(false);
		rfvFatherName.setVisible(false);
		rfvDOB.setVisible(false);
		rfvAddress.setVisible(false);
		rfvMartialStatus.setVisible(false);
		rfvPhone.setVisible(false);
		rfvEducation.setVisible(false);
		rfvMajor.setVisible(false);
		rfvMail.setVisible(false);
		rfvMark.setVisible(false);
	}
}
