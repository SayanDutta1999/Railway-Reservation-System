package Railway_Ticket_Reservation_System;



import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;
import javax.swing.JToolBar;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.HashMap;

import javax.swing.JComboBox;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.border.MatteBorder;



import java.awt.Color;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.border.LineBorder;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.event.ChangeListener;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.swing.JRViewer;
import net.sf.jasperreports.view.JasperViewer;

import javax.swing.event.ChangeEvent;

public class Booking extends JFrame {

	private JPanel contentPane;
	private JTextField pssengerNameTextField;
	private JTextField mobileNoTextField;
	private JTextField aadhaarNoTextField;
	private JTextField ageTextField;
	private JLabel sourceStation;
	private JLabel destStation;
	private JLabel date;
	public JLabel trainNo;
	private JLabel trainName;
	private JLabel time;
	private JLabel total;
	private JComboBox classComboBox;
	private JComboBox stateComboBox;
	private static Connection con = null;
	public String comboBoxClassItem;
	public String comboBoxStateItem;
	public int adultCount;
	public int childCount;
	public int trainId;
	public String totalFare;
	public Integer passId;
	public int ticketId;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Booking frame = new Booking();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void reportLoad() {
		HashMap<String, Object> hmap = new HashMap<String, Object>();
		hmap.put("passengersId", passId);
		con = DBConnection.connnectDB();
		try {
			JasperDesign jasDesign = JRXmlLoader.load("F:\\eclipse  location\\Railway Ticket Reservation System\\src\\Railway_Ticket_Reservation_System\\Ticket.jrxml");
			JasperReport jasReport = JasperCompileManager.compileReport(jasDesign);
			JasperPrint jasPrint = (JasperPrint) JasperFillManager.fillReport(jasReport, hmap, con);
			JasperViewer jasViewer = new JasperViewer(jasPrint);
			jasViewer.setVisible(true);
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void getTicketId() {
		Statement stmt = null;
		ResultSet rs = null;
		PreparedStatement prepstmt = null;
		con = DBConnection.connnectDB();
	
			try {
				prepstmt =  con.prepareStatement("INSERT INTO T_TRAIN_TICKET (TRAIN_NO) VALUES (?)");
				prepstmt.setInt(1, trainId);
				int count = prepstmt.executeUpdate();
				
				
				
				stmt = con.createStatement();
				rs = stmt.executeQuery("select TICKET_ID from T_TRAIN_TICKET where TRAIN_NO = " + trainId + "");
				while(rs.next()) {
					ticketId = rs.getInt("TICKET_ID");
					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	}
	
	public void getPassengerId() {
		Statement stmt = null;
		ResultSet rs = null;
		con = DBConnection.connnectDB();
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery("select PASSENGER_ID from T_PASSENGER where AADHAAR_NO = " + aadhaarNoTextField.getText() + "");
			while(rs.next()) {
				passId = rs.getInt("PASSENGER_ID");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public void submitPassDetails() {
		PreparedStatement prepstmt = null;
		int count;
		con = DBConnection.connnectDB();
		String economyClass = "Economy Class";
		String firstClass = "First Class";
		try {
			if(comboBoxClassItem.equals(firstClass)) {
				prepstmt =  con.prepareStatement("INSERT INTO T_PASSENGER (PASSENGERS_NAME, AGE, MOBILE_NO, AADHAAR_NO, STATE, "
						+ "TRAIN_NO, TRAIN_NAME, SRC, DESTINATION, DEPARTURE_TIME, AVAIL_DATE, TICKET_PRICE, TICKET_NO, TICKET_CATEGORY) "
						+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, STR_TO_DATE(?,'%Y-%m-%d'), ?, ?, ?)");
				prepstmt.setString(1, pssengerNameTextField.getText());
				prepstmt.setString(2, ageTextField.getText());
				prepstmt.setString(3, mobileNoTextField.getText());
				prepstmt.setString(4, aadhaarNoTextField.getText());
				prepstmt.setString(5, comboBoxStateItem);
				prepstmt.setString(6, trainNo.getText());
				prepstmt.setString(7, trainName.getText());
				prepstmt.setString(8, sourceStation.getText());
				prepstmt.setString(9, destStation.getText());
				prepstmt.setString(10, time.getText());
				prepstmt.setString(11, date.getText());
				prepstmt.setString(12, total.getText());
				prepstmt.setInt(13, ticketId);
				prepstmt.setString(14, comboBoxClassItem);
				
			}else if(comboBoxClassItem.equals(economyClass)) {
				prepstmt =  con.prepareStatement("INSERT INTO T_PASSENGER (PASSENGERS_NAME, AGE, MOBILE_NO, AADHAAR_NO, STATE, "
						+ "TRAIN_NO, TRAIN_NAME, SRC, DESTINATION, DEPARTURE_TIME, AVAIL_DATE, TICKET_PRICE, TICKET_NO, TICKET_CATEGORY) "
						+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, STR_TO_DATE(?,'%Y-%m-%d'), ?, ?, ?)");
				prepstmt.setString(1, pssengerNameTextField.getText());
				prepstmt.setString(2, ageTextField.getText());
				prepstmt.setString(3, mobileNoTextField.getText());
				prepstmt.setString(4, aadhaarNoTextField.getText());
				prepstmt.setString(5, comboBoxStateItem);
				prepstmt.setString(6, trainNo.getText());
				prepstmt.setString(7, trainName.getText());
				prepstmt.setString(8, sourceStation.getText());
				prepstmt.setString(9, destStation.getText());
				prepstmt.setString(10, time.getText());
				prepstmt.setString(11, date.getText());
				prepstmt.setString(12, total.getText());
				prepstmt.setInt(13, ticketId);
				prepstmt.setString(14, comboBoxClassItem);
				
			}
			
			count = prepstmt.executeUpdate();
			if(count > 0) {
				JOptionPane.showMessageDialog(null, "Click on Get Ticket");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void totalFare() {
		
		Statement stmt = null;
		ResultSet rs = null; 
		String economyClass = "Economy Class";
		String firstClass = "First Class";
		String queryOne = "select FIRST_CLASS_PRICE from T_TRAIN_FARE where TRAIN_NO = " + trainId + "";
		String querySec = "select ECONOMY_CLASS_PRICE from T_TRAIN_FARE where TRAIN_NO = " + trainId + "";
		con = DBConnection.connnectDB();
		try {
			stmt = con.createStatement();
			if(comboBoxClassItem.equals(firstClass)) {
				rs = stmt.executeQuery(queryOne);
				while(rs.next()) {
					totalFare = rs.getString("FIRST_CLASS_PRICE");
					total.setText(totalFare);
				}
			}
			else if(comboBoxClassItem.equals(economyClass)) {
				rs = stmt.executeQuery(querySec);
				while(rs.next()) {
					totalFare = rs.getString("ECONOMY_CLASS_PRICE");
					total.setText(totalFare);
				}
			}
				
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	public Booking(String id) throws SQLException {
		this();
		ResultSet rs = null;
		Statement stmt = null;
		trainId = Integer.parseInt(id);
		String query = "Select SRC, DESTINATION, AVAIL_DATE, TRAIN_NO, TRAIN_NAME, DEPARTURE_TIME from T_TRAIN where TRAIN_NO = " + trainId + "";
		con = DBConnection.connnectDB();
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			while(rs.next()) {
				sourceStation.setText(rs.getString("SRC"));
				destStation.setText(rs.getString("DESTINATION"));
				date.setText(rs.getString("AVAIL_DATE"));
				trainNo.setText(rs.getString("TRAIN_NO"));
				trainName.setText(rs.getString("TRAIN_NAME"));
				time.setText(rs.getString("DEPARTURE_TIME"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		stmt.close();
		rs.close();
		
	}
	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public Booking() throws SQLException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1083, 542);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 4));
		panel.setBackground(new Color(230, 230, 250));
		panel.setBounds(24, 21, 1024, 455);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(34, 57, 946, 376);
		panel.add(panel_1);
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0), 9));
		panel_1.setLayout(null);
		
		JLabel sourceStaionLabel = new JLabel("Source Station :");
		sourceStaionLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		sourceStaionLabel.setBounds(55, 33, 145, 28);
		panel_1.add(sourceStaionLabel);
		
		sourceStation = new JLabel("");
		sourceStation.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		sourceStation.setBounds(210, 33, 153, 28);
		panel_1.add(sourceStation);
		
		JLabel destStationLabel = new JLabel("Destination Station :");
		destStationLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		destStationLabel.setBounds(376, 33, 163, 28);
		panel_1.add(destStationLabel);
		
		destStation = new JLabel("");
		destStation.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		destStation.setBounds(549, 33, 145, 28);
		panel_1.add(destStation);
		
		JLabel trainNoLabel = new JLabel("Train No. :");
		trainNoLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		trainNoLabel.setBounds(55, 90, 96, 28);
		panel_1.add(trainNoLabel);
		
		trainNo = new JLabel("");
		trainNo.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		trainNo.setBounds(161, 90, 125, 28);
		panel_1.add(trainNo);
		
		JLabel trainNameLabel = new JLabel("Train Name :");
		trainNameLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		trainNameLabel.setBounds(309, 90, 107, 28);
		panel_1.add(trainNameLabel);
		
		trainName = new JLabel("");
		trainName.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		trainName.setBounds(426, 90, 163, 28);
		panel_1.add(trainName);
		
		JLabel dateLabel = new JLabel("Date :");
		dateLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		dateLabel.setBounds(705, 33, 58, 28);
		panel_1.add(dateLabel);
		
		date = new JLabel("");
		date.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		date.setBounds(773, 33, 125, 28);
		panel_1.add(date);
		
		JLabel timeLabel = new JLabel("Time :");
		timeLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		timeLabel.setBounds(618, 90, 58, 28);
		panel_1.add(timeLabel);
		
		time = new JLabel("");
		time.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		time.setBounds(680, 90, 107, 28);
		panel_1.add(time);
		
		JLabel passengerNameLabel = new JLabel("Passenger Name :");
		passengerNameLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		passengerNameLabel.setBounds(55, 152, 145, 28);
		panel_1.add(passengerNameLabel);
		
		pssengerNameTextField = new JTextField();
		pssengerNameTextField.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		pssengerNameTextField.setBounds(216, 152, 200, 28);
		panel_1.add(pssengerNameTextField);
		pssengerNameTextField.setColumns(10);
		
		JLabel passenContactNoLabel = new JLabel("Mobile No. :");
		passenContactNoLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		passenContactNoLabel.setBounds(55, 238, 113, 28);
		panel_1.add(passenContactNoLabel);
		
		mobileNoTextField = new JTextField();
		mobileNoTextField.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		mobileNoTextField.setColumns(10);
		mobileNoTextField.setBounds(216, 238, 200, 28);
		panel_1.add(mobileNoTextField);
		
		JLabel passenAdharNoLabel = new JLabel("Aadhaar No. :");
		passenAdharNoLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		passenAdharNoLabel.setBounds(55, 195, 125, 28);
		panel_1.add(passenAdharNoLabel);
		
		aadhaarNoTextField = new JTextField();
		aadhaarNoTextField.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		aadhaarNoTextField.setColumns(10);
		aadhaarNoTextField.setBounds(216, 195, 200, 28);
		panel_1.add(aadhaarNoTextField);
		
		JLabel AgeLabel = new JLabel("Age :");
		AgeLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		AgeLabel.setBounds(463, 152, 51, 28);
		panel_1.add(AgeLabel);
		
		ageTextField = new JTextField();
		ageTextField.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		ageTextField.setColumns(10);
		ageTextField.setBounds(549, 152, 81, 28);
		panel_1.add(ageTextField);
		
		JLabel stateLabel = new JLabel("State :");
		stateLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
		stateLabel.setBounds(464, 195, 75, 28);
		panel_1.add(stateLabel);
		
		stateComboBox = new JComboBox();
		stateComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBoxStateItem = (String) stateComboBox.getSelectedItem();
			}
		});
		stateComboBox.setModel(new DefaultComboBoxModel(new String[] {"", "Andhra Pradesh", "Arunachal Pradesh", 
				"Assam", "Bihar", "Chhattisgarh", "Goa", "Gujarat", "Haryana", "Himachal Pradesh", 
				"Jharkhand", "Karnataka", "Kerala", "Madhya Pradesh", "Maharashtra", "Manipur", "Meghalaya", 
				"Mizoram", "Nagaland", "Odisha", "Punjab", "Rajasthan", "Sikkim", "Tamil Nadu", 
				"Telangana", "Tripura", "Uttar Pradesh", "Uttarakhand", "West Bengal"}));
		stateComboBox.setBounds(549, 195, 145, 28);
		panel_1.add(stateComboBox);
		
		JButton btnBook = new JButton("Submit");
		btnBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getTicketId();
				submitPassDetails();
				getPassengerId();
				
				
			}
		});
		btnBook.setBackground(new Color(224, 255, 255));
		btnBook.setFont(new Font("Times New Roman", Font.BOLD, 19));
		btnBook.setBounds(309, 307, 125, 28);
		panel_1.add(btnBook);
		
		JButton btnGetTicket = new JButton("Get Ticket");
		btnGetTicket.setBounds(495, 307, 125, 28);
		panel_1.add(btnGetTicket);
		btnGetTicket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reportLoad();
			}
		});
		btnGetTicket.setFont(new Font("Times New Roman", Font.BOLD, 20));
		
		classComboBox = new JComboBox();
		classComboBox.setBounds(549, 240, 190, 28);
		panel_1.add(classComboBox);
		classComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				comboBoxClassItem = (String) classComboBox.getSelectedItem();
				totalFare();
				
			}
		});
		
			
			classComboBox.setModel(new DefaultComboBoxModel(new String[] {"", "First Class", "Economy Class"}));
			
			JLabel classLabel = new JLabel("Class :");
			classLabel.setBounds(463, 238, 65, 28);
			panel_1.add(classLabel);
			classLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
			
			JLabel totalFareLabel = new JLabel("Total Fare :");
			totalFareLabel.setBounds(657, 152, 106, 28);
			panel_1.add(totalFareLabel);
			totalFareLabel.setFont(new Font("Times New Roman", Font.BOLD, 18));
			
			total = new JLabel("");
			total.setBounds(773, 152, 120, 28);
			panel_1.add(total);
			total.setFont(new Font("Times New Roman", Font.BOLD, 17));
			total.setBackground(new Color(0, 0, 0));
			total.setForeground(new Color(0, 0, 0));
		
		JLabel passengerPanel = new JLabel("Passenger's Details");
		passengerPanel.setHorizontalAlignment(SwingConstants.CENTER);
		passengerPanel.setFont(new Font("Times New Roman", Font.BOLD, 21));
		passengerPanel.setBounds(393, 19, 186, 28);
		panel.add(passengerPanel);
		
	}
}
