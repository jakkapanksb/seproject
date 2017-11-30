package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import company.SystemCompany;
import view.HomePage;

import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class LoginPage {

	private JFrame frmLogin;
	JLabel bgPanel;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private SystemCompany system;
	private JLabel bgPanel_1;

	/**
	 * Create the application.
	 */
	public LoginPage() {
		
	//apply look and feel
			try {
			    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
			        if ("Nimbus".equals(info.getName())) {
			            UIManager.setLookAndFeel(info.getClassName());
			            break;
			        }
			    }
			} catch (Exception e) {
			    // If Nimbus is not available, you can set the GUI to another look and feel.
			}
			
	//create contents
			
			system = new SystemCompany();
			frmLogin = new JFrame();
			frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frmLogin.setIconImage(Toolkit.getDefaultToolkit().getImage(LoginPage.class.getResource("/com/sun/java/swing/plaf/windows/icons/Computer.gif")));
			frmLogin.setTitle("Login");
			frmLogin.setBounds(100, 100, 450, 390);
			frmLogin.getContentPane().setLayout(null);
			frmLogin.setVisible(true);
			
			
			initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		BufferedImage img = null;
		Image dimg = null;
		JLabel bgPanel =new JLabel();
		bgPanel.setBounds(0, 0, 434, 351);
		try {
			img = ImageIO.read(this.getClass().getResource("bg.png"));
			dimg = img.getScaledInstance(bgPanel.getWidth(), bgPanel.getHeight(),Image.SCALE_SMOOTH);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		JPanel bgPanel = new JPanel();
		bgPanel_1 =new JLabel(new ImageIcon(dimg));			
		//bgPanel.setBackground(Color.PINK);
		bgPanel_1.setBounds(0, 0, 434, 351);
		frmLogin.getContentPane().add(bgPanel_1);
		bgPanel_1.setLayout(null);
		JButton loginButton = new JButton("Login");
		loginButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				String inUsername = usernameField.getText();
				String inPassword = passwordField.getText();
				Boolean checkPW = system.checkPW(inUsername, inPassword);
				if (checkPW == true){
					system.setPersonnelID(inUsername);
					Boolean status = system.checkStatus();
					//System.exit(0);
					frmLogin.dispose();
					HomePage homePage = new HomePage(status,system.getPersonnel());
				} else{
					JOptionPane t = new JOptionPane();
		        	t.showMessageDialog(frmLogin,
		        		    "Username or Password is incorrect.",
		        		    "Fail to Login",
		        		    t.ERROR_MESSAGE);
				}
			}
		});
		loginButton.setFont(new Font("Verdana", Font.PLAIN, 16));
		loginButton.setBounds(10, 302, 203, 38);
		bgPanel_1.add(loginButton);
		loginButton.setForeground(new Color(0, 0, 0));
		loginButton.setBackground(new Color(135, 206, 235));
		
		JLabel imgPanel = new JLabel();
		imgPanel.setBounds(10, 101, 115, 130);
		try {
			img = ImageIO.read(this.getClass().getResource("account.png"));
			dimg = img.getScaledInstance(imgPanel.getWidth(), imgPanel.getHeight(),Image.SCALE_SMOOTH);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		imgPanel = new JLabel(new ImageIcon(dimg));
		imgPanel.setBounds(10, 101, 115, 130);
		bgPanel_1.add(imgPanel);
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setFont(new Font("Verdana", Font.PLAIN, 28));
		lblLogin.setBounds(175, 30, 84, 52);
		bgPanel_1.add(lblLogin);
		
		JButton closeButton = new JButton("Close");
		closeButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		closeButton.setBackground(new Color(255, 192, 203));
		closeButton.setFont(new Font("Verdana", Font.PLAIN, 16));
		closeButton.setBounds(223, 302, 201, 38);
		bgPanel_1.add(closeButton);
		
		usernameField = new JTextField();
		usernameField.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 14));
		usernameField.setBounds(135, 126, 289, 31);
		bgPanel_1.add(usernameField);
		usernameField.setColumns(10);
		usernameField.requestFocusInWindow();
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 14));
		passwordField.setBounds(135, 193, 289, 31);
		bgPanel_1.add(passwordField);
		
		JLabel lblUsername = new JLabel("Username :");
		lblUsername.setFont(new Font("Verdana", Font.PLAIN, 14));
		lblUsername.setBounds(135, 101, 101, 14);
		bgPanel_1.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password :");
		lblPassword.setFont(new Font("Verdana", Font.PLAIN, 14));
		lblPassword.setBounds(135, 168, 92, 14);
		bgPanel_1.add(lblPassword);
		
		frmLogin.repaint();
	}
}
