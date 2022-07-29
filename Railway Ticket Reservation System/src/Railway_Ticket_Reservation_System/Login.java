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
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class Login extends JFrame {

	private JPanel contentPane;
	private JPasswordField passwordField;
	private JTextField usernameTextField;
	private static Connection con = null;
	private Statement stmt = null;
	public String trainId;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	
	public Login (String trainId) {
		this();
		this.trainId = trainId;
		
	}

	/**
	 * check login credential
	 * */
	public void checkCredential(String id) {
//		System.out.println(id);
		String query = "SELECT * FROM T_USERDATA "
				+ "where PASSWORD = AES_DECRYPT(AES_ENCRYPT(PASSWORD, 'key1234'), 'key1234') AND EMAIL = '"+ usernameTextField.getText() +"'";
		ResultSet rs = null;
		try {
			con = DBConnection.connnectDB();
			stmt =  con.createStatement();
			rs = stmt.executeQuery(query);
			if(rs.next()) {
				JOptionPane.showMessageDialog(null, "Login Successful");
				Booking book = new Booking(id);
				book.setVisible(true);
				dispose();
			}
			else
				JOptionPane.showMessageDialog(null, "Login failed");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	
	
	/**
	 * Create the frame.
	 */
	public Login() {
		setBackground(Color.WHITE);
		setTitle("LOGIN");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 868, 569);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 228, 196));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("WELCOME");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(230, 49, 362, 48);
		contentPane.add(lblNewLabel);
		
		JLabel iconLabel = new JLabel("");
		iconLabel.setIcon(new ImageIcon("F:\\eclipse  location\\FirstProject\\Icon\\login.png"));
		iconLabel.setBounds(30, 150, 256, 256);
		contentPane.add(iconLabel);
		
		JLabel usernameLabel = new JLabel("Email ID");
		usernameLabel.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		usernameLabel.setBounds(311, 178, 213, 30);
		contentPane.add(usernameLabel);
		
		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setFont(new Font("Times New Roman", Font.PLAIN, 22));
		passwordLabel.setBounds(311, 292, 213, 30);
		contentPane.add(passwordLabel);
		
		usernameTextField = new JTextField();
		usernameTextField.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		usernameTextField.setBounds(570, 178, 215, 25);
		contentPane.add(usernameTextField);
		usernameTextField.setColumns(10);
		
		JLabel errorMsg_1 = new JLabel("");
		errorMsg_1.setForeground(Color.RED);
		errorMsg_1.setFont(new Font("Tahoma", Font.ITALIC, 14));
		errorMsg_1.setBounds(570, 236, 215, 25);
		contentPane.add(errorMsg_1);
		
		JLabel errorMsg_2 = new JLabel("");
		errorMsg_2.setForeground(Color.RED);
		errorMsg_2.setFont(new Font("Tahoma", Font.ITALIC, 14));
		errorMsg_2.setBounds(570, 350, 215, 25);
		contentPane.add(errorMsg_2);
		
		
		
		JLabel noticeLabel = new JLabel("Don't have any account? ->\r\n");
		noticeLabel.setForeground(Color.BLACK);
		noticeLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		noticeLabel.setBounds(423, 474, 202, 30);
		contentPane.add(noticeLabel);
		
		JButton btnRegister = new JButton("REGISTER");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Registration rsg = new Registration(trainId);
				rsg.setVisible(true);
				dispose();
			}
		});
		btnRegister.setBackground(new Color(0, 255, 255));
		btnRegister.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnRegister.setBounds(635, 469, 150, 41);
		contentPane.add(btnRegister);
		
		JButton btnLogin = new JButton("LOGIN");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkCredential(trainId);
			}
		});
		btnLogin.setForeground(new Color(0, 0, 0));
		btnLogin.setBackground(new Color(0, 255, 255));
		btnLogin.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnLogin.setBounds(397, 405, 147, 39);
		contentPane.add(btnLogin);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		passwordField.setBounds(570, 301, 215, 25);
		contentPane.add(passwordField);
	}
}

