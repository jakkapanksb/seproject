package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import company.DatabaseConnector;
import company.SystemCompany;

public class LoginPage {

	private SystemCompany system;
	private JFrame frame;
	private JTextField usernameField;
	private JPasswordField passwordField;
	
	public LoginPage() {
		system = new SystemCompany();
		//DatabaseConnector database = new DatabaseConnector();
		//database.connectDB();
		frame = new JFrame();
		frame.setBounds(100, 100, 575, 460);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		/**frame.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		        database.closeDB();
		        System.out.println("Connection close");
		    }
		});*/
		frame.setTitle("Company");
		login();
	}
	
	private void login(){
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setFont(new Font(lblLogin.getFont().getName(), lblLogin.getFont().getStyle(), 30));
		
		JLabel lblUsername = new JLabel("Username");
		
		usernameField = new JTextField();
		usernameField.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		
		passwordField = new JPasswordField();
		passwordField.setColumns(10);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String inUsername = usernameField.getText();
				String inPassword = passwordField.getText();
				Boolean checkPW = system.checkPW(inUsername, inPassword);
				if (checkPW == true){
					system.setPersonnelID(inUsername);
					Boolean status = system.checkStatus();
					System.exit(0);
					HomePage homePage = new HomePage(status,system.getPersonnel());
				} else{
					JOptionPane t = new JOptionPane();
		        	t.showMessageDialog(frame,
		        		    "Username or Password is incorrect.",
		        		    "Fail to Login",
		        		    t.ERROR_MESSAGE);
				}
			}
		});
		
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(171, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addGroup(groupLayout.createSequentialGroup()
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
									.addComponent(lblPassword)
									.addComponent(lblUsername)
									.addComponent(usernameField, GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
									.addComponent(passwordField))
								.addGap(169))
							.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
								.addComponent(btnLogin)
								.addGap(247)))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(lblLogin)
							.addGap(238))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(70)
					.addComponent(lblLogin)
					.addGap(37)
					.addComponent(lblUsername)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(usernameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblPassword)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(27)
					.addComponent(btnLogin)
					.addContainerGap(124, Short.MAX_VALUE))
		);
		frame.getContentPane().setLayout(groupLayout);
	}
	
}