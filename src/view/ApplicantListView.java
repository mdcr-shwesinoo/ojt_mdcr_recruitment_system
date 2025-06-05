package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import controller.ApplicantController;
import model.ApplicantModel;

public class ApplicantListView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtName;
	private DefaultTableModel tableModel;
	private JPanel tablePanel;
	private ApplicantController controller;
	private JLabel lblRowCount;
	private int loggedInId;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ApplicantListView frame = new ApplicantListView(1);
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
	public ApplicantListView(int loggedInId) {
		this.loggedInId = loggedInId;
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		controller = new ApplicantController();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		setBounds(100, 100, 1200, 700);
		setLocationRelativeTo(null);
//		contentPane.setBorder(new EmptyBorder(0, 5, 5, 5));
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));

		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		
		JPanel titlePanel = new JPanel();
		titlePanel.setBackground(new Color(0, 0, 0));
		titlePanel.setPreferredSize(new Dimension(Integer.MAX_VALUE, 50));
		titlePanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
		titlePanel.setLayout(null);
		getContentPane().add(titlePanel, BorderLayout.NORTH);

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
		
		JLabel lblChangePassword = new JLabel("Change Password");
		lblChangePassword.setBounds(1300, 10, 340, 30);
		lblChangePassword.setForeground(new Color(0xCCCCCC));
		lblChangePassword.setFont(new Font("MS UI Gothic", Font.PLAIN, 15));
		titlePanel.add(lblChangePassword);
		
		lblChangePassword.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ChangePasswordView changePasswordView = new ChangePasswordView(loggedInId, ApplicantListView.this);
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

		// Form field
		JPanel fieldPanel = new JPanel();
//		fieldPanel.setBackground(Color.white);
		fieldPanel.setLayout(new GridBagLayout());
		fieldPanel.setPreferredSize(new Dimension(0, 50));
		fieldPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(0, 0, 0, 20);
		gbc.anchor = GridBagConstraints.NORTH;
		gbc.fill = GridBagConstraints.HORIZONTAL;

		JLabel lblGender = new JLabel("Gender");
		lblGender.setFont(new Font("Arial", Font.PLAIN, 15));

		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Arial", Font.PLAIN, 15));

		txtName = new JTextField(20);
		txtName.setPreferredSize(new Dimension(180, 30));

		JComboBox<String> comboBox = new JComboBox<>(new String[] { "", "Male", "Female" });
		comboBox.setFont(new Font("Arial", Font.PLAIN, 15));
		comboBox.setPreferredSize(new Dimension(180, 30));

		gbc.gridx = 0;
		gbc.gridy = 0;
		fieldPanel.add(lblGender, gbc);

		gbc.insets = new Insets(0, 0, 0, 80);
		gbc.gridx++;
		fieldPanel.add(comboBox, gbc);

		gbc.insets = new Insets(0, 0, 0, 20);
		gbc.gridx++;
		fieldPanel.add(lblName, gbc);

		gbc.gridx++;
		fieldPanel.add(txtName, gbc);

		// Button Panel
		JPanel buttonPanel = new JPanel();
//		buttonPanel.setBackground(Color.white);
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));
//		JButton btnSearch = new JButton("Search");
		JButton btnSearch = addActionButton("Search",  new Color(54, 118, 247));
		JButton btnCancel = addActionButton("Cancel",  new Color(54, 118, 247));
		JButton btnAccepted = addActionButton("Accepted Applicants",  new Color(54, 118, 247));
		btnAccepted.setPreferredSize(new Dimension(160, 30)); // Set preferred size

//		JButton btnCancel = new JButton("Cancel");
//		JButton btnAccepted = new JButton("Accepted Applicants");

		btnSearch.addActionListener(e -> {
			String gender = comboBox.getSelectedItem().toString();
			String name = txtName.getText();
			System.out.println(gender);
			refreshTable(controller.searchApplicantsByGender(gender));

			if (gender.isBlank() && name.isBlank()) {
				refreshTable(controller.getPendingApplicants());
			}

			if (gender.isBlank() && !name.isBlank()) {
				refreshTable(controller.searchApplicantsByName(name));
			}

			if (!gender.isBlank() && name.isBlank()) {
				refreshTable(controller.searchApplicantsByGender(gender));
			}

			if (!gender.isBlank() && !name.isBlank()) {
				refreshTable(controller.searchApplicantsByGenderAndName(gender, name));
			}
		});

		btnCancel.addActionListener(e -> {
			comboBox.setSelectedIndex(0);
			txtName.setText("");
			refreshTable(controller.getPendingApplicants());
		});

		btnAccepted.addActionListener(e -> {
			AcceptListView acceptForm = new AcceptListView(loggedInId);
			acceptForm.setVisible(true);
			dispose();
		});

		buttonPanel.add(btnSearch);
		buttonPanel.add(btnCancel);
		buttonPanel.add(btnAccepted);

		// Table panel
		tablePanel = new JPanel(new FlowLayout(FlowLayout.CENTER)); // centers component
//		tablePanel.setBackground(Color.white);
		JPanel dataPanel = new JPanel();
//		dataPanel.setBackground(Color.white);
		dataPanel.setLayout(new BoxLayout(dataPanel, BoxLayout.Y_AXIS));

		JPanel rowCountPanel = new JPanel(new FlowLayout(FlowLayout.CENTER)); // centers component
//		rowCountPanel.setBackground(Color.white);
		lblRowCount = new JLabel("No Records Found");
		rowCountPanel.add(lblRowCount);

		initTable();
		refreshTable(controller.getPendingApplicants());

		dataPanel.add(rowCountPanel);
		dataPanel.add(tablePanel);

		contentPane.add(titlePanel);
		contentPane.add(Box.createVerticalGlue());
		contentPane.add(fieldPanel);
		contentPane.add(Box.createVerticalGlue());
		contentPane.add(buttonPanel);
		contentPane.add(dataPanel);
		contentPane.add(Box.createVerticalGlue());

	}

	private void initTable() {
		String[] columnNames = { "No.", "", "", "Name", "NRC", "Id" };

		tableModel = new DefaultTableModel(columnNames, 0) {
			@Override
			public boolean isCellEditable(int row, int column) {
				// Make only "Update" and "Accept" columns editable (to catch button clicks)
				return column == 1 || column == 2;
			}
		};

		JTable table = new JTable(tableModel);
//		table.setBackground(Color.white);
		table.setPreferredScrollableViewportSize(new Dimension(600, 200));
		table.setRowHeight(40);

		// Create ActionListener for Update button
		ActionListener updateActionListener = e -> {
			int id = Integer.parseInt(e.getActionCommand());
			// Open the UpdateForm with the clicked ID
			UpdateFormView updateForm = new UpdateFormView(id);
			updateForm.setVisible(true);
			dispose();
		};

		// Create ActionListener for Accept/Reject button
		ActionListener acceptRejectActionListener = e -> {
			int id = Integer.parseInt(e.getActionCommand());
			// Open the AcceptRejectForm with the clicked ID
			System.out.println("ID from listview: " + id);
			AcceptRejectFormView acceptRejectForm = new AcceptRejectFormView(id, loggedInId);
			acceptRejectForm.setVisible(true);
			dispose();
		};

		// Set custom renderer and editor for "Update" and "Accept" button columns
		table.getColumnModel().getColumn(1).setCellRenderer(new ButtonRenderer());
		table.getColumnModel().getColumn(1)
				.setCellEditor(new ButtonEditor(new JCheckBox(), "Update", table, updateActionListener));

		table.getColumnModel().getColumn(2).setCellRenderer(new ButtonRenderer());
		table.getColumnModel().getColumn(2)
				.setCellEditor(new ButtonEditor(new JCheckBox(), "Accept/Reject", table, acceptRejectActionListener));

		table.getColumnModel().getColumn(5).setMinWidth(0);
		table.getColumnModel().getColumn(5).setMaxWidth(0);
		table.getColumnModel().getColumn(5).setWidth(0);

		// Set header color and font explicitly
		table.getTableHeader().setBackground(new Color(94, 148, 255));
		table.getTableHeader().setForeground(new Color(255, 255, 255));
		table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setColumnHeaderView(table.getTableHeader());
//		scrollPane.getViewport().setBackground(Color.white);
		table.getTableHeader().setPreferredSize(new Dimension(table.getColumnModel().getColumn(0).getWidth(), 40));
		tablePanel.add(scrollPane);

	}

	private void refreshTable(List<ApplicantModel> applicants) {
		tableModel.setRowCount(0);

		if (applicants.size() == 0) {
			lblRowCount.setText("No Records Found");
		} else {
			lblRowCount.setText("Total " + applicants.size() + " records");
		}

		int number = 1;
		for (ApplicantModel applicant : applicants) {
			tableModel.addRow(new Object[] { number, "Update", "Accept/Reject", applicant.getApplicantName(),
					applicant.getApplicantNRC(), applicant.getApplicantID() });
			number++;
		}
		tableModel.fireTableDataChanged();
	}
	
	static class ButtonRenderer extends JPanel implements TableCellRenderer {

	    private final JButton button;

	    public ButtonRenderer() {
	        setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
	        button = new JButton();
	        button.setMargin(new Insets(2, 10, 2, 10));
	        button.setFocusable(false);
	        button.setOpaque(true);
	        button.setContentAreaFilled(true);
	        button.setForeground(Color.WHITE);
	        button.setBackground(new Color(94, 148, 255));
	        button.setFocusPainted(false);

	        // Hover effect
	        button.addMouseListener(new java.awt.event.MouseAdapter() {
	            public void mouseEntered(java.awt.event.MouseEvent evt) {
	                button.setBackground(new Color(54, 118, 247));
	            }
	            public void mouseExited(java.awt.event.MouseEvent evt) {
	                button.setBackground(new Color(94, 148, 255));
	            }
	        });

	        add(button);
	        setOpaque(true);
	    }

	    @Override
	    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
	                                                   int row, int column) {
	        button.setText((value == null) ? "" : value.toString());

	        // Set button size based on column
	        if (column == 1) {
	            button.setPreferredSize(new Dimension(80, 30));  // Smaller for Update
	        } else if (column == 2) {
	            button.setPreferredSize(new Dimension(110, 30)); // Bigger for Accept/Reject
	        }

	        if (isSelected) {
	            setBackground(table.getSelectionBackground());
	        } else {
	            setBackground(table.getBackground());
	        }

	        return this;
	    }
	}
	
	static class ButtonEditor extends DefaultCellEditor {
	    private final JPanel panel;
	    private final JButton button;
	    private final String label;
	    private boolean clicked;
	    private final JTable table;
	    private final ActionListener actionListener;
	    private int id;

	    private int currentColumn; // track column

	    public ButtonEditor(JCheckBox checkBox, String label, JTable table, ActionListener actionListener) {
	        super(checkBox);
	        this.table = table;
	        this.label = label;
	        this.actionListener = actionListener;

	        button = new JButton(label);
	        button.setFocusable(false);
	        button.setMargin(new Insets(2, 10, 2, 10));
	        button.addActionListener(e -> fireEditingStopped());

	        panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
	        panel.add(button);
	        panel.setOpaque(true);
	    }

	    @Override
	    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected,
	                                                 int row, int column) {
	        currentColumn = column;

	        int modelRow = table.convertRowIndexToModel(row);
	        Object idObj = table.getModel().getValueAt(modelRow, 5); // hidden ID
	        this.id = Integer.parseInt(idObj.toString());

	        button.setText(label);

	        // Set size based on column
	        if (column == 1) {
	            button.setPreferredSize(new Dimension(80, 30));
	        } else if (column == 2) {
	            button.setPreferredSize(new Dimension(110, 30));
	        }

	        clicked = true;

	        return panel;
	    }

	    @Override
	    public Object getCellEditorValue() {
	        if (clicked) {
	            actionListener.actionPerformed(new ActionEvent(button, ActionEvent.ACTION_PERFORMED, String.valueOf(id)));
	        }
	        clicked = false;
	        return label;
	    }

	    @Override
	    public boolean stopCellEditing() {
	        clicked = false;
	        return super.stopCellEditing();
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
