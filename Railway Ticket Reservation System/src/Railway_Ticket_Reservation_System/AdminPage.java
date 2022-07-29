package Railway_Ticket_Reservation_System;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
import java.util.Vector;
import javax.swing.border.BevelBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AdminPage extends JFrame {

	private JPanel contentPane;
	private JTextField destStationtextField;
	private JTextField departureTimetextField;
	private JTextField srcStationtextField;
	private JTextField trainNametextField;
	private JTextField trainIdField;
	private JTextField dateTextField;
	private static JTable table;
	private static Connection con = DBConnection.connnectDB();
	private static PreparedStatement  prestmt = null;
	private JTextField availableSeatField;

	
	
	
	/* deleteTableRow */
	public void deleteTableRow() {
		try {
			prestmt =  con.prepareStatement("delete from T_TRAIN where TRAIN_NO = ?;");
			prestmt.setString(1,  trainIdField.getText());
			int i = prestmt.executeUpdate();
			System.out.println(i);
			if(i > 0) {
				JOptionPane.showMessageDialog(this, i + " " + "record deleted");
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				
				if(table.getSelectedRowCount() == 1) {
					model.removeRow(table.getSelectedRow());
					resetTextField();
				}
				else {
					if(table.getSelectedRowCount() == 0) {
						JOptionPane.showMessageDialog(this, "Table is empty");
					}else {
						JOptionPane.showMessageDialog(this, "Please select one ");
					}
				}
			}
			else
				JOptionPane.showMessageDialog(this,  "Record doesn't deleted");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/* updateTableRow */
	public void updateTable() {
		try {
			prestmt =  con.prepareStatement("UPDATE  T_TRAIN set  TRAIN_NAME = ?, SRC = ?, DESTINATION =  ?,"
					+ "DEPARTURE_TIME = ?, AVAIL_DATE = STR_TO_DATE(?,'%d/%m/%Y'), AVAIL_SEATS = ? WHERE TRAIN_NO = ?");
			
			prestmt.setString(1, trainNametextField.getText());
			prestmt.setString(2, srcStationtextField.getText());
			prestmt.setString(3, destStationtextField.getText());
			prestmt.setString(4, departureTimetextField.getText());
			prestmt.setString(5, dateTextField.getText());
			prestmt.setString(6,  availableSeatField.getText());
			prestmt.setString(7,  trainIdField.getText());
			
			int i = prestmt.executeUpdate();
			if(i > 0) {
				JOptionPane.showMessageDialog(this, i + " " + "record updated");
				displayDataOnTable();
			}
			else
				JOptionPane.showMessageDialog(this,  "Record doesn't updated");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	// Reset Text Field
	public void resetTextField() {
		destStationtextField.setText(null);
		departureTimetextField.setText(null);
		srcStationtextField.setText(null);
		trainNametextField.setText(null);
		trainIdField.setText(null);
		dateTextField.setText(null);
		availableSeatField.setText(null);
	}
	/**
	 * Database connection
	 * @throws SQLException 
	 * */
	public void addDataToDB() {
		try {
			prestmt =  con.prepareStatement("insert into T_TRAIN values (?, ?, ?, ?, ?, STR_TO_DATE(?,'%d/%m/%Y'), ?)");
			prestmt.setString(1,  trainIdField.getText());
			prestmt.setString(2, trainNametextField.getText());
			prestmt.setString(3, srcStationtextField.getText());
			prestmt.setString(4, destStationtextField.getText());
			prestmt.setString(5, departureTimetextField.getText());
			prestmt.setString(6, dateTextField.getText());
			prestmt.setString(7,  availableSeatField.getText());
			int i = prestmt.executeUpdate();
			if(i > 0) {
				JOptionPane.showMessageDialog(null, i + " " + "row added");
				resetTextField();
			}
			else
				JOptionPane.showMessageDialog(null,  "Data doesn't stored");
			displayDataOnTable();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null,e);
		}finally {
//			try {
//				con.close();
//				prestmt.close();
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			
		}
		
		
	}
	
	public static void displayDataOnTable() {
		String query = "SELECT * FROM T_TRAIN";
		ResultSet rs = null;
		ResultSetMetaData rsmd = null;
		DefaultTableModel dtm = null;
		int cols;
		try {
			prestmt =  con.prepareStatement(query);
			rs = prestmt.executeQuery();
			rsmd =  rs.getMetaData();
			cols = rsmd.getColumnCount();
			dtm = (DefaultTableModel) table.getModel();
			dtm.setRowCount(0);
			
			while(rs.next()) {
				Vector<String> colomnData = new Vector<String>();
				for(int i = 0; i < cols; i++) {
					colomnData.add(rs.getString("TRAIN_NO"));
					colomnData.add(rs.getString("TRAIN_NAME"));
					colomnData.add(rs.getString("SRC"));
					colomnData.add(rs.getString("DESTINATION"));
					colomnData.add(rs.getString("DEPARTURE_TIME"));
					colomnData.add(rs.getString("AVAIL_DATE"));
					colomnData.add(rs.getString("AVAIL_SEATS"));
				}
				dtm.addRow(colomnData);
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Create the frame.
	 */
	public AdminPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1149, 713);
		contentPane = new JPanel();
		contentPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, Color.BLACK));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel headingPanel = new JPanel();
		headingPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, Color.BLACK));
		headingPanel.setBounds(24, 48, 1070, 78);
		contentPane.add(headingPanel);
		headingPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ADMIN PANEL");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(321, 10, 459, 58);
		headingPanel.add(lblNewLabel);
		
		JLabel trainIdLabel = new JLabel("Train Id.");
		trainIdLabel.setFont(new Font("Times New Roman", Font.BOLD, 22));
		trainIdLabel.setBounds(59, 167, 104, 28);
		contentPane.add(trainIdLabel);
		
		JLabel trainNameLabel = new JLabel("Train Name");
		trainNameLabel.setFont(new Font("Times New Roman", Font.BOLD, 22));
		trainNameLabel.setBounds(59, 232, 120, 28);
		contentPane.add(trainNameLabel);
		
		JLabel sourceStaionLabel = new JLabel("Source Station");
		sourceStaionLabel.setFont(new Font("Times New Roman", Font.BOLD, 22));
		sourceStaionLabel.setBounds(59, 297, 140, 28);
		contentPane.add(sourceStaionLabel);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Departure Time");
		lblNewLabel_1_1_1_1.setFont(new Font("Times New Roman", Font.BOLD, 22));
		lblNewLabel_1_1_1_1.setBounds(59, 427, 160, 28);
		contentPane.add(lblNewLabel_1_1_1_1);
		
		JLabel dateTimeLabel = new JLabel("");
		dateTimeLabel.setBounds(59, 460, 104, 28);
		contentPane.add(dateTimeLabel);
		
		JLabel destStationLabel = new JLabel("Destination Station");
		destStationLabel.setFont(new Font("Times New Roman", Font.BOLD, 22));
		destStationLabel.setBounds(59, 367, 184, 28);
		contentPane.add(destStationLabel);
		
		destStationtextField = new JTextField();
		destStationtextField.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		destStationtextField.setBounds(263, 367, 204, 28);
		contentPane.add(destStationtextField);
		destStationtextField.setColumns(10);
		
		departureTimetextField = new JTextField();
		departureTimetextField.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		departureTimetextField.setColumns(10);
		departureTimetextField.setBounds(263, 427, 204, 28);
		contentPane.add(departureTimetextField);
		
		srcStationtextField = new JTextField();
		srcStationtextField.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		srcStationtextField.setColumns(10);
		srcStationtextField.setBounds(263, 297, 204, 28);
		contentPane.add(srcStationtextField);
		
		trainNametextField = new JTextField();
		trainNametextField.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		trainNametextField.setColumns(10);
		trainNametextField.setBounds(263, 232, 204, 28);
		contentPane.add(trainNametextField);
		
		trainIdField = new JTextField();
		trainIdField.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		trainIdField.setColumns(10);
		trainIdField.setBounds(263, 167, 204, 28);
		contentPane.add(trainIdField);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 7, true));
		panel.setBounds(511, 167, 583, 401);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 563, 381);
		panel.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				int selectedRowIndex = table.getSelectedRow();
				trainIdField.setText(model.getValueAt(selectedRowIndex, 0).toString());
				trainNametextField.setText(model.getValueAt(selectedRowIndex, 1).toString());
				srcStationtextField.setText(model.getValueAt(selectedRowIndex, 2).toString());
				destStationtextField.setText(model.getValueAt(selectedRowIndex, 3).toString());
				departureTimetextField.setText(model.getValueAt(selectedRowIndex, 4).toString());
				dateTextField.setText(model.getValueAt(selectedRowIndex, 5).toString());
				availableSeatField.setText(model.getValueAt(selectedRowIndex, 6).toString());
			}
		});
		table.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Train Name", "Train No.", "Source Station", "Destination Station", "Departure Time", "Date", "Seats"
			}
		));
		table.getColumnModel().getColumn(2).setPreferredWidth(78);
		table.getColumnModel().getColumn(2).setMinWidth(22);
		table.getColumnModel().getColumn(2).setMaxWidth(330);
		table.getColumnModel().getColumn(3).setPreferredWidth(98);
		table.getColumnModel().getColumn(4).setPreferredWidth(82);
		
		JButton btnAdd = new JButton("ADD");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addDataToDB();
				
			}
		});
		btnAdd.setFont(new Font("Times New Roman", Font.BOLD, 22));
		btnAdd.setBounds(59, 601, 120, 37);
		contentPane.add(btnAdd);
		
		JButton btnDelete = new JButton("DELETE");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteTableRow();
			}
		});
		btnDelete.setFont(new Font("Times New Roman", Font.BOLD, 22));
		btnDelete.setBounds(263, 601, 131, 37);
		contentPane.add(btnDelete);
		
		JLabel dateLabel = new JLabel("Date");
		dateLabel.setFont(new Font("Times New Roman", Font.BOLD, 22));
		dateLabel.setBounds(59, 487, 160, 28);
		contentPane.add(dateLabel);
		
		dateTextField = new JTextField();
		dateTextField.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		dateTextField.setColumns(10);
		dateTextField.setBounds(263, 487, 204, 28);
		contentPane.add(dateTextField);
		
		
		JButton btnUpdate = new JButton("UPDATE");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateTable();
			}
		});
		btnUpdate.setFont(new Font("Times New Roman", Font.BOLD, 22));
		btnUpdate.setBounds(467, 601, 131, 37);
		contentPane.add(btnUpdate);
		
		JButton btnReset = new JButton("RESET");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetTextField();
			}
		});
		btnReset.setFont(new Font("Times New Roman", Font.BOLD, 22));
		btnReset.setBounds(671, 601, 131, 37);
		contentPane.add(btnReset);
		
		JButton btnShowTable = new JButton("SHOW TABLE");
		btnShowTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				displayDataOnTable();
			}
		});
		btnShowTable.setFont(new Font("Times New Roman", Font.BOLD, 22));
		btnShowTable.setBounds(875, 601, 219, 37);
		contentPane.add(btnShowTable);
		
		JLabel availableSeatLabel = new JLabel("Seats Available");
		availableSeatLabel.setFont(new Font("Times New Roman", Font.BOLD, 22));
		availableSeatLabel.setBounds(59, 540, 160, 28);
		contentPane.add(availableSeatLabel);
		
		availableSeatField = new JTextField();
		availableSeatField.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		availableSeatField.setColumns(10);
		availableSeatField.setBounds(263, 540, 204, 28);
		contentPane.add(availableSeatField);
	}
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminPage frame = new AdminPage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
