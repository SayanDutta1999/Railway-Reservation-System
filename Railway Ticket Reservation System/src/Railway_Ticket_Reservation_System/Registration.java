package Railway_Ticket_Reservation_System;



import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.sql.*;
public class Registration extends JFrame {

	private JPanel contentPane;
	private JTextField nameTextF;
	private JTextField emailTextF;
	private JTextField phoneTextF;
	private JTextField countryTextF;
	private JTextField stateTextF;
	private JPasswordField createPasswordField;
	private JPasswordField confirmPasswordField;
	private JLabel errorMsg_2;
	private JLabel errorMsg_3;
	private JLabel errorMsg_4;
	private JLabel errorMsg_5;
	private JLabel errorMsg_6;
	private ButtonGroup bg;
	private String passStr;
	private String cpassStr;
	private String malebtn;
	private String femalebtn;
	Connection con = null ;
	String trainId;

	
	

	
	/**
	 * Create Database connection
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * */
	public void registerToDatabase() throws SQLException, ClassNotFoundException {
		String name = nameTextF.getText();
		String email = emailTextF.getText();
		String phoneNo = phoneTextF.getText();
		String createPass = passStr;
		String confirmPass = cpassStr;
		String country = countryTextF.getText();
		String state = stateTextF.getText();
		String male = "MALE";
		String maleValue = new String(malebtn);
		
		if(name.isEmpty() || email.isEmpty() || phoneNo.isEmpty() || createPass.isEmpty() || confirmPass.isEmpty() || country.isEmpty() || state.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Fill text field");
		}
		
		con = DBConnection.connnectDB();
		PreparedStatement  prestmt =  con.prepareStatement("insert into T_USERDATA (NAME, EMAIL, PHONE_NUMBER, PASSWORD, GENDER, COUNTRY, STATE) "
				+ "values (?, ?, ?, AES_ENCRYPT('" + passStr + "', 'key1234'), ?, ?, ?)");
		prestmt.setString(1, name);
		prestmt.setString(2, email);
		prestmt.setString(3, phoneNo);
		if(maleValue.equals(male)) {
			prestmt.setString(4, maleValue);
			
		}
		else
			prestmt.setString(4, femalebtn);
		prestmt.setString(5, country);
		prestmt.setString(6, state);  
		int i = prestmt.executeUpdate();
		if(i > 0) {
			JOptionPane.showMessageDialog(null, i + " " + "row added");
		}
		else {
			JOptionPane.showMessageDialog(null,  "Data doesn't stored");
		}
		con.close();
		prestmt.close();
		
}
	
	public Registration(String trainId) {
		this();
		this.trainId = trainId;
	}
	
	/**
	 * Create the frame.
	 */
	public Registration() {
		setTitle("REGISTER");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 869, 643);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(153, 153, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel titleLabel = new JLabel("Registration Form");
		titleLabel.setBounds(131, 36, 632, 58);
		titleLabel.setFont(new Font("Verdana", Font.PLAIN, 26));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(titleLabel);
		
		JLabel nameLabel = new JLabel("Name");
		nameLabel.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		nameLabel.setBounds(131, 133, 196, 30);
		contentPane.add(nameLabel);
		
		JLabel emailLabel = new JLabel("E-mail  ID");
		emailLabel.setBackground(new Color(240, 240, 240));
		emailLabel.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		emailLabel.setBounds(131, 183, 196, 30);
		contentPane.add(emailLabel);
		
		JLabel phoneLabel = new JLabel("Phone Number");
		phoneLabel.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		phoneLabel.setBounds(131, 233, 196, 30);
		contentPane.add(phoneLabel);
		
		JLabel createpassLabel = new JLabel("Create Password");
		createpassLabel.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		createpassLabel.setBounds(131, 283, 180, 30);
		contentPane.add(createpassLabel);
		
		JLabel confirmpassLabel = new JLabel("Confirm Password");
		confirmpassLabel.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		confirmpassLabel.setBounds(131, 333, 180, 30);
		contentPane.add(confirmpassLabel);
		
		JLabel genderLabel = new JLabel("Gender");
		genderLabel.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		genderLabel.setBounds(131, 383, 196, 30);
		contentPane.add(genderLabel);
		
		JLabel countryLabel = new JLabel("Country");
		countryLabel.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		countryLabel.setBounds(131, 433, 196, 30);
		contentPane.add(countryLabel);
		
		JLabel stateLabel = new JLabel("State");
		stateLabel.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		stateLabel.setBounds(131, 483, 180, 30);
		contentPane.add(stateLabel);
		
		nameTextF = new JTextField();
		nameTextF.setFont(new Font("Tahoma", Font.PLAIN, 14));
		nameTextF.setBounds(322, 133, 215, 25);
		contentPane.add(nameTextF);
		nameTextF.setColumns(10);
		
		emailTextF = new JTextField();
		emailTextF.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String email_pattern = "^[a-z0-9]{0,30}[@][a-z0-9]{0,10}[.][a-z]{0,3}$";
				Pattern pattern = Pattern.compile(email_pattern);
				Matcher matcher = pattern.matcher(emailTextF.getText());
				if(!matcher.matches()) {
					errorMsg_2.setText("Invalid Email!!");
				}
				else
					errorMsg_2.setText(null);
			}
		});
		emailTextF.setFont(new Font("Tahoma", Font.PLAIN, 14));
		emailTextF.setColumns(10);
		emailTextF.setBounds(322, 183, 215, 25);
		contentPane.add(emailTextF);
		
		phoneTextF = new JTextField();
		phoneTextF.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String phoneNoPattern = "^[0-9]{0,10}$";
				Pattern pattern = Pattern.compile(phoneNoPattern);
				Matcher matcher = pattern.matcher(phoneTextF.getText());
				if(!matcher.matches()) {
					errorMsg_3.setText("Phone number is incorrect");
				}
				else
					errorMsg_3.setText(null);
			}
		});
		phoneTextF.setFont(new Font("Tahoma", Font.PLAIN, 14));
		phoneTextF.setColumns(10);
		phoneTextF.setBounds(322, 233, 215, 25);
		contentPane.add(phoneTextF);
		
		createPasswordField = new JPasswordField();
		createPasswordField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String createpPasswdPattern =   "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";
				Pattern pattern = Pattern.compile(createpPasswdPattern);
				char pass[] =  createPasswordField.getPassword();
				passStr = new String(pass);
				Matcher matcher = pattern.matcher(passStr);
				if(!matcher.matches()) {
					errorMsg_4.setText("Password is incorrect");
				}
				else
					errorMsg_4.setText(null);
			}
		});
		createPasswordField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		createPasswordField.setBounds(322, 283, 215, 25);
		contentPane.add(createPasswordField);
		
		confirmPasswordField = new JPasswordField();
		confirmPasswordField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				cpassStr = new String(confirmPasswordField.getPassword());
				
				if(!cpassStr.equals(passStr)) {
					errorMsg_5.setText("Password is incorrect");
				}
				else
					errorMsg_5.setText(null);
			}
		});
		confirmPasswordField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		confirmPasswordField.setBounds(322, 333, 215, 25);
		contentPane.add(confirmPasswordField);
		
		countryTextF = new JTextField();
		countryTextF.setFont(new Font("Tahoma", Font.PLAIN, 14));
		countryTextF.setColumns(10);
		countryTextF.setBounds(322, 433, 215, 25);
		contentPane.add(countryTextF);
		
		stateTextF = new JTextField();
		stateTextF.setFont(new Font("Tahoma", Font.PLAIN, 14));
		stateTextF.setColumns(10);
		stateTextF.setBounds(322, 483, 215, 25);
		contentPane.add(stateTextF);
		

		
		JRadioButton maleRadioButton = new JRadioButton("MALE");
		maleRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				maleRadioButton.setActionCommand("MALE");
				malebtn = new String (bg.getSelection().getActionCommand());
			}
		});
		maleRadioButton.setBackground(new Color(153, 153, 204));
		maleRadioButton.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		maleRadioButton.setBounds(362, 383, 65, 21);
		contentPane.add(maleRadioButton);
		
		
		
		JRadioButton femaleRadioButton = new JRadioButton("FEMALE");
		femaleRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				femaleRadioButton.setActionCommand("FEMALE");
				femalebtn = new String (bg.getSelection().getActionCommand());
			}
		});
		femaleRadioButton.setBackground(new Color(153, 153, 204));
		femaleRadioButton.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		femaleRadioButton.setBounds(429, 383, 83, 21);
		contentPane.add(femaleRadioButton);
		
		bg = new ButtonGroup();
		bg.add(maleRadioButton);
		bg.add(femaleRadioButton);
	
	
		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					try {
						try {
							registerToDatabase();
							new Login(trainId).setVisible(true);
							dispose();
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} 
			}
		});
		
		btnRegister.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnRegister.setBounds(150, 536, 180, 46);
		contentPane.add(btnRegister);
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnCancel.setBounds(539, 536, 180, 46);
		contentPane.add(btnCancel);
		
		errorMsg_2 = new JLabel("");
		errorMsg_2.setForeground(Color.RED);
		errorMsg_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		errorMsg_2.setBounds(615, 183, 215, 25);
		contentPane.add(errorMsg_2);
		
		errorMsg_3 = new JLabel("");
		errorMsg_3.setForeground(Color.RED);
		errorMsg_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		errorMsg_3.setBounds(615, 233, 215, 25);
		contentPane.add(errorMsg_3);
		
		errorMsg_4 = new JLabel("");
		errorMsg_4.setForeground(Color.RED);
		errorMsg_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		errorMsg_4.setBounds(615, 283, 215, 25);
		contentPane.add(errorMsg_4);
		
		errorMsg_5 = new JLabel("");
		errorMsg_5.setForeground(Color.RED);
		errorMsg_5.setFont(new Font("Tahoma", Font.PLAIN, 15));
		errorMsg_5.setBounds(615, 333, 215, 25);
		contentPane.add(errorMsg_5);
		
		errorMsg_6 = new JLabel("");
		errorMsg_6.setForeground(Color.RED);
		errorMsg_6.setBounds(615, 383, 215, 25);
		contentPane.add(errorMsg_6);
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Registration frame = new Registration();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}

