package Railway_Ticket_Reservation_System;



import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.UIManager;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.BevelBorder;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.ImageIcon;
import javax.swing.border.MatteBorder;
import javax.swing.border.LineBorder;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.border.Border;
import javax.swing.JEditorPane;
import javax.swing.JSlider;

public class Index extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField fromTextField;
	private JTextField toTextField;
	private JScrollPane tableScrollPane;
	private static Connection con = DBConnection.connnectDB();
	private static PreparedStatement  prestmt = null;
	private JTextField trainDatetextField;
	public String trainIdField;



	/**
	 * Search Trains
	 * */
	public void searchTrains() {
		ResultSet rs = null;
		ResultSetMetaData rsmd = null;
		int cols;
	
		try {
			prestmt =  con.prepareStatement("SELECT * FROM T_TRAIN  WHERE SRC = ? AND  "
					+ "DESTINATION = ? AND AVAIL_DATE = STR_TO_DATE(?,'%d/%m/%Y');");
			prestmt.setString(1, fromTextField.getText().toUpperCase());
			prestmt.setString(2, toTextField.getText().toUpperCase());
			prestmt.setString(3, trainDatetextField.getText());
			rs = prestmt.executeQuery();
			rsmd =  rs.getMetaData();
			cols = rsmd.getColumnCount();
			DefaultTableModel dtm = (DefaultTableModel) table.getModel();
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
	public Index() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1146, 716);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(175, 238, 238));
		contentPane.setBorder(new BevelBorder(BevelBorder.LOWERED, new Color(32, 178, 170), null, null, null));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel controlPanel = new JPanel();
		controlPanel.setBorder(new LineBorder(new Color(0, 0, 0), 4));
		controlPanel.setBackground(new Color(255, 228, 196));
		controlPanel.setBounds(46, 117, 1043, 99);
		contentPane.add(controlPanel);
		controlPanel.setLayout(null);
		
		JLabel fromLabel = new JLabel("From :");
		fromLabel.setBounds(26, 36, 59, 26);
		fromLabel.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		controlPanel.add(fromLabel);
		
		JLabel toLabel = new JLabel("To :");
		toLabel.setBounds(370, 36, 49, 31);
		toLabel.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		controlPanel.add(toLabel);
		
		JLabel dateLabel = new JLabel("Date :");
		dateLabel.setBounds(703, 36, 72, 31);
		dateLabel.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		controlPanel.add(dateLabel);
		
		fromTextField = new JTextField();
		fromTextField.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		fromTextField.setBounds(111, 36, 225, 31);
		controlPanel.add(fromTextField);
		fromTextField.setColumns(10);
		
		toTextField = new JTextField();
		toTextField.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		toTextField.setColumns(10);
		toTextField.setBounds(429, 36, 225, 31);
		controlPanel.add(toTextField);
		
		trainDatetextField = new JTextField();
		trainDatetextField.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		trainDatetextField.setBounds(785, 37, 225, 31);
		controlPanel.add(trainDatetextField);
		trainDatetextField.setColumns(10);
		
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 searchTrains();
			}
		});
		btnSearch.setForeground(new Color(0, 0, 0));
		btnSearch.setBackground(new Color(192, 192, 192));
		btnSearch.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnSearch.setBounds(325, 237, 136, 42);
		contentPane.add(btnSearch);
		
		JPanel headingPanel = new JPanel();
		headingPanel.setBackground(new Color(100, 149, 237));
		headingPanel.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		headingPanel.setBounds(74, 22, 976, 85);
		contentPane.add(headingPanel);
		headingPanel.setLayout(null);
		
		JLabel headingTextField = new JLabel("TRAIN RESERVATION SYSTEM");
		headingTextField.setBounds(272, 10, 399, 65);
		headingPanel.add(headingTextField);
		headingTextField.setIcon(null);
		headingTextField.setBackground(SystemColor.window);
		headingTextField.setFont(new Font("Times New Roman", Font.BOLD, 26));
		headingTextField.setHorizontalAlignment(SwingConstants.CENTER);
		
		
		JScrollPane tableScrollPane = new JScrollPane();
		tableScrollPane.setBounds(46, 314, 1036, 329);
		contentPane.add(tableScrollPane);
		tableScrollPane.setVisible(true);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				int selectedRowIndex = table.getSelectedRow();
				trainIdField = model.getValueAt(selectedRowIndex, 0).toString();
			
		}});
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Train No.", "Train Name", "Source Station", "Destination Station", "Departure Time", "Date "
			}
		));
		table.getColumnModel().getColumn(2).setPreferredWidth(85);
		table.getColumnModel().getColumn(3).setPreferredWidth(85);
		table.getColumnModel().getColumn(4).setPreferredWidth(85);
		tableScrollPane.setViewportView(table);
		
		JButton btnBook = new JButton("Book");
		btnBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Login(trainIdField).setVisible(true);		
						
				
			}
		});
		btnBook.setForeground(Color.BLACK);
		btnBook.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnBook.setBackground(Color.LIGHT_GRAY);
		btnBook.setBounds(640, 237, 136, 42);
		contentPane.add(btnBook);
		
		
		
}	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Index frame = new Index();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}


