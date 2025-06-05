package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
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
import controller.ApplicantController;
import model.ApplicantModel;
import utils.NRCHelper;

public class UpdateFormView extends JFrame {

	private int applicantID;
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
	private JButton btnUpdate;
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
	private JLabel rfvAddress;

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
					ApplicantModel am = new ApplicantModel();
					UpdateFormView frame = new UpdateFormView(2);
					frame.setVisible(true);
					Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

					frame.setBounds(0, 0, screenSize.width, screenSize.height);
					frame.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					// frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public UpdateFormView(int id) {
		setResizable(false);
//		setResizable(false);
		System.out.println("Update Id" + id);
		// setResizable(false);
		applicantID = id;
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setTitle("Update Apply Form");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 961, 809);
		getContentPane().setLayout(new BorderLayout());

		JPanel wrapper = new JPanel(new GridBagLayout());

		JPanel formPanel = new JPanel();
		formPanel.setPreferredSize(new Dimension(700, 670));
		formPanel.setLayout(null);
		formPanel.setBackground(bgColor);

		wrapper.add(formPanel);
		getContentPane().add(wrapper, BorderLayout.CENTER);

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
		txtFatherName.setBorder(BorderFactory.createEmptyBorder());
		formPanel.add(txtFatherName);

		txtPhone = new JTextField();
		txtPhone.setColumns(10);
		txtPhone.setBounds(237, 360, 200, 25);
		txtPhone.setBorder(BorderFactory.createEmptyBorder());
		formPanel.add(txtPhone);

		txtEducation = new JTextField();
		txtEducation.setColumns(10);
		txtEducation.setBounds(237, 409, 200, 25);
		txtEducation.setBorder(BorderFactory.createEmptyBorder());
		formPanel.add(txtEducation);

		txtMail = new JTextField();
		txtMail.setColumns(10);
		txtMail.setBounds(237, 488, 200, 25);
		txtMail.setBorder(BorderFactory.createEmptyBorder());
		formPanel.add(txtMail);

		txtMark = new JTextField();
		txtMark.setColumns(10);
		txtMark.setBounds(237, 545, 200, 25);
		txtMark.setBorder(BorderFactory.createEmptyBorder());
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
		// txtMajor.setVisible(false);
		formPanel.add(txtMajor);

		btnUpdate = addActionButton("Update", new Color(54, 118, 247));
		btnUpdate.setCursor(new Cursor(Cursor.HAND_CURSOR));

		// btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!isFormValid()) {

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

						// Degree Type
						String degreeType = rdoBachelor.isSelected() ? "Beachelor" : "Master";
						applicants.setDegreeType(degreeType);

						// Email and Mark
						applicants.setMail(txtMail.getText());
						applicants.setMark(Integer.parseInt(txtMark.getText())); // You might want to validate this
						// System.out.println(txtMark.getText());

						// Default status
						applicants.setStatus(ApplicantModel.ApplicantStatus.PENDING);

						// Insert into DB
						boolean update = applicantController.updateApplicants(applicants, applicantID);
//					if(!isFormValid()) {
						if (update) {

							JOptionPane.showMessageDialog(null, "Update successfully!");
							Clear();
							ApplicantListView applicantListView = new ApplicantListView(id);
							applicantListView.setVisible(true);
							dispose();
						} else {
							JOptionPane.showMessageDialog(null, "Failed to update applicant.");
						}

//					}

					} catch (Exception e1) {
						e1.printStackTrace();
						JOptionPane.showMessageDialog(null, "Error: " + e1.getMessage());
					}
				}
			}
		});
		btnUpdate.setBounds(194, 600, 91, 25);
		formPanel.add(btnUpdate);

		btnCancel = addActionButton("Cancel", new Color(54, 118, 247));
		btnCancel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		// btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ApplicantModel am = new ApplicantModel();
				ApplicantListView applicantListView = new ApplicantListView(am.getApplicantID());
				applicantListView.setVisible(true);
				dispose();
			}
		});
		btnCancel.setBounds(344, 600, 91, 25);
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
		rfvFatherName.setBounds(237, 153, 200, 13);
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

//		rfvGender = new JLabel("Error");
//		rfvGender.setFont(new Font("MS UI Gothic", Font.PLAIN, 12));
//		rfvGender.setBounds(237, 272, 220, 13);
//		rfvGender.setVisible(false);
//		formPanel.add(rfvGender);

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

		// Placeholder For DOB of NRC Card
//			editor = (JTextField) txtDOB.getDateEditor().getUiComponent();
//			editor.setText("YYYY/MM/DD");
//			editor.setForeground(Color.GRAY);
		//
//			editor.addFocusListener(new FocusAdapter() {
//				@Override
//				public void focusGained(FocusEvent e) {
//					if (editor.getText().equals("YYYY/MM/DD")) {
//						editor.setText("");
//						editor.setForeground(Color.BLACK);
//					}
//				}
		//
//				@Override
//				public void focusLost(FocusEvent e) {
//					if (editor.getText().isEmpty()) {
//						editor.setForeground(Color.GRAY);
//						editor.setText("YYYY/MM/DD");
//					}
//				}
//			});	
		loadApplicantData(id);

	}

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
		if (Checking.IsEmpty(name)) {
			showError(rfvName, "Name is required");
			hasError = true;
		} else if (!Checking.IsValidName(name)) {
			showError(rfvName, "Name should not contain number");
			hasError = true;
		} else {
			hideError(rfvName);
		}

		// Validate NRC
		if (Checking.IsEmpty(nrc)) {
			showError(rfvNRC, "NRC is required");
			hasError = true;
		}

		// Validate Father Name
		if (Checking.IsEmpty(fatherName)) {
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
		} else if (Checking.IsEmpty(address)) {
			showError(rfvAddress, "Address is required");
			hasError = true;
		} else {
			hideError(rfvAddress);
		}

		// Validate Phone
		if (Checking.IsEmpty(phone)) {
			showError(rfvPhone, "Phone is required");
			hasError = true;
		} else if (!Checking.IsValidPhone(phone)) {
			showError(rfvPhone, "Invalid phone number");
			hasError = true;
		} else {
			hideError(rfvPhone);
		}

		// Validate DOB
		if (dob == null) {
			showError(rfvDOB, "DOB is required");
			hasError = true;
		} else {
			hideError(rfvDOB);
		}

		// Validate Major (only when "Other" is selected)
		if ("Other".equals(ddlMajor.getSelectedItem()) && (Checking.IsEmpty(major))) {
			showError(rfvMajor, "Major is required");
			hasError = true;
		} else {
			hideError(rfvMajor);
		}

		// Validate education, mail, and mark
		if (Checking.IsEmpty(education)) {
			showError(rfvEducation, "Education is required");
			hasError = true;
		} else {
			hideError(rfvEducation);
		}

		if (Checking.IsEmpty(mail)) {
			showError(rfvMail, "Email is required");
			hasError = true;
		} else if (!Checking.IsValidEmail(mail)) {
			showError(rfvMail, "Mail format is incorrect");
			hasError = true;
		} else {
			hideError(rfvMail);
		}

		if (Checking.IsEmpty(mark)) {
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

	public void loadApplicantData(int applicantID) {
		ApplicantModel applicants = new ApplicantModel();
		ApplicantController applicantController = new ApplicantController();
		applicants = applicantController.getApplicantsDetails(applicantID);

		// Set text fields to read-only (non-editable)
		txtName.setText(applicants.getApplicantName());
		// txtName.setEditable(false);

		txtFatherName.setText(applicants.getFatherName());
		// txtFatherName.setEditable(false);

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
		// txtDOB.setEnabled(false);

		// Set phone, education, mail, mark, and address as read-only
		txtPhone.setText(applicants.getPhoneNo());
		// txtPhone.setEditable(false);

		txtEducation.setText(applicants.getEducation());
		// txtEducation.setEditable(false);

		txtMail.setText(applicants.getMail());
		txtMail.setEditable(false);

		txtMark.setText(String.valueOf(applicants.getMark()));
		// txtMark.setEditable(false);

		txtAddress.setText(applicants.getAddress());
		// txtAddress.setEditable(false);

		// Set gender radio buttons to read-only
		if ("Male".equalsIgnoreCase(applicants.getGender())) {
			rdoMale.setSelected(true);
		} else {
			rdoFemale.setSelected(true);
		}
		// rdoMale.setEnabled(false);
		// rdoFemale.setEnabled(false);

		// Set marital status radio buttons to read-only
		if ("Single".equalsIgnoreCase(applicants.getMaritalStatus())) {
			rdoSingle.setSelected(true);
		} else {
			rdoMarried.setSelected(true);
		}
		// rdoSingle.setEnabled(false);
		// rdoMarried.setEnabled(false);

		if ("Bachelor".equalsIgnoreCase(applicants.getDegreeType())) {
			rdoBachelor.setSelected(true);
		} else {
			rdoMaster.setSelected(true);
		}
		// rdoBachelor.setEnabled(false);
		// rdoMaster.setEnabled(false);

		// Set major combo box to read-only
		ddlMajor.setSelectedItem(applicants.getMajor());
		// ddlMajor.setEnabled(false);

		// If the major is "Other", display the text field but keep it read-only
//	    if ("Other".equals(applicants.getMajor())) {
//	        txtMajor.setText(applicants.getMajor());
//	        txtMajor.setVisible(true);
//	        //txtMajor.setEditable(false); // Make the text field read-only
//	    } else {
//	        txtMajor.setVisible(false);
//	    }

		// Set major combo box to read-only
		String[] majors = new String[] { "Computer Science", "Computer Technology",
				"Computer Engineering and Information Technology", "Software Engineering",
				"Information Science and Technology", "Information Technology", "Other" };

		for (String major : majors) {
			if (major.equals(applicants.getMajor())) {
				ddlMajor.setSelectedItem(applicants.getMajor());
				txtMajor.setVisible(false);
				break;
				// ddlMajor.setEnabled(false);

			} else {
				ddlMajor.setSelectedItem("Other");
				// ddlMajor.setEnabled(false);
				txtMajor.setText(applicants.getMajor());
				txtMajor.setVisible(true);
				txtMajor.setEditable(true); // Make the text field read-only
			}
		}

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

	public void Clear() {
		txtName.setText("");
		txtFatherName.setText("");
		txtPhone.setText("");
		txtEducation.setText("");
		txtMail.setText("");
		txtMark.setText("");
		txtMajor.setText("");
		txtAddress.setText("");

		txtDOB.setDate(null);
		ddlMajor.setSelectedIndex(0);
		txtName.requestFocus(true);
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
