package testNewView;

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
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

public class LoginPage {

	private JFrame frmLogin;
	private JTextField usernameField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginPage window = new LoginPage();
					window.frmLogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LoginPage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
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
		BufferedImage img = null;
		Image dimg = null;
		frmLogin = new JFrame();
		frmLogin.setIconImage(Toolkit.getDefaultToolkit().getImage(LoginPage.class.getResource("/com/sun/java/swing/plaf/windows/icons/Computer.gif")));
		frmLogin.setTitle("Login");
		frmLogin.setBounds(100, 100, 450, 390);
		frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLogin.getContentPane().setLayout(null);
		
		JLabel bgPanel =new JLabel();
		bgPanel.setBounds(0, 0, 434, 351);
		try {
			img = ImageIO.read(this.getClass().getResource("fate-bw.png"));
			dimg = img.getScaledInstance(bgPanel.getWidth(), bgPanel.getHeight(),Image.SCALE_SMOOTH);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		JPanel bgPanel = new JPanel();
		bgPanel =new JLabel(new ImageIcon(dimg));
		//bgPanel.setBackground(Color.PINK);
		bgPanel.setBounds(0, 0, 434, 351);
		frmLogin.getContentPane().add(bgPanel);
		bgPanel.setLayout(null);
		
		JButton loginButton = new JButton("Login");
		loginButton.setFont(new Font("Verdana", Font.PLAIN, 16));
		loginButton.setBounds(10, 302, 203, 38);
		bgPanel.add(loginButton);
		loginButton.setForeground(new Color(0, 0, 0));
		loginButton.setBackground(new Color(135, 206, 235));
		
		JLabel imgPanel = new JLabel();
		imgPanel.setBounds(10, 101, 92, 130);
		try {
			img = ImageIO.read(this.getClass().getResource("account.png"));
			dimg = img.getScaledInstance(imgPanel.getWidth(), imgPanel.getHeight(),Image.SCALE_SMOOTH);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		imgPanel = new JLabel(new ImageIcon(dimg));
		imgPanel.setBounds(10, 101, 115, 130);
		bgPanel.add(imgPanel);
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setFont(new Font("Verdana", Font.PLAIN, 28));
		lblLogin.setBounds(175, 30, 84, 52);
		bgPanel.add(lblLogin);
		
		JButton closeButton = new JButton("Close");
		closeButton.setBackground(new Color(255, 192, 203));
		closeButton.setFont(new Font("Verdana", Font.PLAIN, 16));
		closeButton.setBounds(223, 302, 201, 38);
		bgPanel.add(closeButton);
		
		usernameField = new JTextField();
		usernameField.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 14));
		usernameField.setBounds(135, 126, 289, 31);
		bgPanel.add(usernameField);
		usernameField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Segoe UI Symbol", Font.PLAIN, 14));
		passwordField.setBounds(135, 193, 289, 31);
		bgPanel.add(passwordField);
		
		JLabel lblUsername = new JLabel("Username :");
		lblUsername.setFont(new Font("Verdana", Font.PLAIN, 14));
		lblUsername.setBounds(135, 101, 101, 14);
		bgPanel.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password :");
		lblPassword.setFont(new Font("Verdana", Font.PLAIN, 14));
		lblPassword.setBounds(135, 168, 92, 14);
		bgPanel.add(lblPassword);
	}
}
