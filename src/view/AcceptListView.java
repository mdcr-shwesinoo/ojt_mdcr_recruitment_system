package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

import config.Common;
import controller.ApplicantController;
import model.ApplicantModel;

@SuppressWarnings("serial")
public class AcceptListView extends JFrame {
	private JPanel contentPane;
	private JLabel lblTotalApplicants;
	private JTextField textField;
	private JTable table;
	private DefaultTableModel tableModel;
	private ApplicantController controller;
	private List<ApplicantModel> allApplicants;
	private int loggedInId;

	public AcceptListView(int loggedInId) {
		this.loggedInId = loggedInId;
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setTitle("Accepted Applicants");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		setBounds(100, 100, 1200, 700);
		setLocationRelativeTo(null);
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));

		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

		JPanel titlePanel = new JPanel();
		titlePanel.setBackground(new Color(0, 0, 0));
		titlePanel.setPreferredSize(new Dimension(Integer.MAX_VALUE, 50));
		titlePanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
		titlePanel.setLayout(null);
		contentPane.add(titlePanel);

		ImageIcon titleImage = new ImageIcon(getClass().getResource("/image/mdcr3.png"));
		JLabel imageTitleLabel = new JLabel(titleImage);
		imageTitleLabel.setBounds(25, 0, 141, 50);
		titlePanel.add(imageTitleLabel);

		JLabel lblNewLabel = new JLabel("Myanmar DCR Recruitment System");
		// lblNewLabel.setBounds(239, 10, 332, 20);
		lblNewLabel.setBounds(216, 10, 340, 30);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("MS UI Gothic", Font.PLAIN, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT); // center text in available space
		titlePanel.add(lblNewLabel);

		// Labels and TextField
		JPanel searchPanel = new JPanel(new BorderLayout());
		searchPanel.setPreferredSize(new Dimension(Integer.MAX_VALUE, 50));
		searchPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
		lblTotalApplicants = new JLabel();
		lblTotalApplicants.setFont(new Font("MS UI Gothic", Font.BOLD, 14));

		JPanel formPanel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(0, 10, 0, 10);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		
		gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel lblSearch = new JLabel("Search NRC:");
		lblSearch.setFont(new Font("MS UI Gothic", Font.BOLD, 14));
		formPanel.add(lblSearch, gbc);

		gbc.gridx++;
		textField = new JTextField();
		textField.setColumns(18);
		formPanel.add(textField, gbc);
		// Buttons

		gbc.gridx++;
		JButton btnShowAll = Common.addActionButton("Show All");
		btnShowAll.setPreferredSize(new Dimension(100, 30));
		formPanel.add(btnShowAll, gbc);

		gbc.gridx++;
		JButton btnBack = Common.addActionButton("Back");
		formPanel.add(btnBack, gbc);
        
		searchPanel.add(lblTotalApplicants, BorderLayout.WEST);
		searchPanel.add(formPanel, BorderLayout.EAST);
		contentPane.add(searchPanel);

		textField.getDocument().addDocumentListener(new DocumentListener() {
			private void filterNRC() {
				String input = textField.getText().trim().toLowerCase();
				List<ApplicantModel> filtered = allApplicants.stream()
						.filter(a -> a.getApplicantNRC().toLowerCase().contains(input)).toList();
				loadTableData(filtered);
			}

			public void insertUpdate(DocumentEvent e) {
				filterNRC();
			}

			public void removeUpdate(DocumentEvent e) {
				filterNRC();
			}

			public void changedUpdate(DocumentEvent e) {
				filterNRC();
			}
		});

		// Show All button action
		btnShowAll.addActionListener(e -> {
			textField.setText(""); // Clear the search field
			loadTableData(allApplicants); // Reload full data
		});

		// Back button action
		btnBack.addActionListener(e -> {
			ApplicantListView applicantListView = new ApplicantListView(loggedInId);
			applicantListView.setVisible(true);
			dispose();
		});

		// Column names
		String[] columns = { "Name", "NRC", "Father", "DOB", "Gender", "Marital Status", "Address", "Phone",
				"Education", "Major", "Degree Type", "Email", "Mark" };

		// Table setup
		tableModel = new DefaultTableModel(columns, 0);
		table = new JTable(tableModel);
		table.setRowHeight(30);
		table.getTableHeader().setPreferredSize(new Dimension(table.getColumnModel().getColumn(0).getWidth(), 30));
		table.getTableHeader().getColorModel();

		// Set header color and font explicitly
		table.getTableHeader().setBackground(new Color(94, 148, 255));
		table.getTableHeader().setForeground(new Color(255, 255, 255));
		table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));

		JScrollPane scrollPane = new JScrollPane(table);
//		scrollPane.setBounds(0, 104, 1550, 645);
//		getContentPane().add(scrollPane);
		contentPane.add(scrollPane);

		// Controller and data fetch
		controller = new ApplicantController();
		allApplicants = controller.getAcceptedApplicants();

		// Load all data initially
		loadTableData(allApplicants);

	}

	// Helper method to populate the table
	private void loadTableData(List<ApplicantModel> applicants) {
		tableModel.setRowCount(0); // Clear existing data
		for (ApplicantModel a : applicants) {
			Object[] rowData = { a.getApplicantName(), a.getApplicantNRC(), a.getFatherName(), a.getDobofNRC(),
					a.getGender(), a.getMaritalStatus(), a.getAddress(), a.getPhoneNo(), a.getEducation(), a.getMajor(),
					a.getDegreeType(), a.getMail(), a.getMark(), a.getStatus() };
			tableModel.addRow(rowData);
		}
		lblTotalApplicants.setText("Total Accepted Applicants : " + applicants.size());
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			AcceptListView view = new AcceptListView(1);
			view.setVisible(true);
		});
	}
}
