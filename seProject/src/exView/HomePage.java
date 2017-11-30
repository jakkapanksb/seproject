package exView;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;

import company.DatabaseConnector;
import company.Personnel;
import company.TimeSystem;

public class HomePage {
	
	private TimeSystem timeSys;
	private Boolean status;
	private Personnel personnel;
	private String personnelID;
	private JFrame frame;
	private JTable infoTable;

	public HomePage(Boolean inStatus,Personnel inPersonnel) {
		status = inStatus;
		personnel = inPersonnel;
		personnelID = inPersonnel.getID();
		timeSys = new TimeSystem();
		
		if (personnel.getPermission() == 1){
			homeLevel1();
		} else if (personnel.getPermission() == 2){
			homeLevel2();
		}
	}
	
	private void homeLevel1(){
		
		frame = new JFrame();
		frame.setBounds(100, 100, 575, 460);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setTitle("Company");
		
		JLabel lblHome = new JLabel("Home");
		lblHome.setFont(new Font(lblHome.getFont().getName(), lblHome.getFont().getStyle(), 30));
		
		String[] column = {"title","info"};
		DefaultTableModel tableModel = new DefaultTableModel(column,0);
		tableModel.addRow(new String[]{"ID", personnel.getID()});
		tableModel.addRow(new String[]{"Name", personnel.getName()});
		tableModel.addRow(new String[]{"Position", personnel.getPosition()});
		tableModel.addRow(new String[]{"Department", personnel.getDepartment()});
		infoTable = new JTable(tableModel);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
				LoginPage login = new LoginPage();
			}
		});
		
		JButton btnClockIn = new JButton("Clock In");
		btnClockIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TimeSystem.createInTime(personnelID);
				frame.removeAll();
				homeLevel1();
				frame.validate();
			}
		});
		btnClockIn.setPreferredSize(new Dimension(90, 25));
		
		JButton btnClockOut = new JButton("Clock Out");
		btnClockOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TimeSystem.createOutTime(personnelID);
				frame.removeAll();
				homeLevel1();
				frame.validate();
			}
		});
		btnClockOut.setPreferredSize(new Dimension(90, 25));
		
		if (status == false){
			btnClockOut.setEnabled(false);
		} else{
			btnClockIn.setEnabled(false);
		}
		
		JLabel lblInformation = new JLabel("Information");
		lblInformation.setHorizontalAlignment(JLabel.CENTER);
		lblInformation.setOpaque(true);
		lblInformation.setBackground(new Color(102, 178, 255));
		
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(56)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(infoTable, GroupLayout.DEFAULT_SIZE, 446, Short.MAX_VALUE)
						.addComponent(lblInformation, GroupLayout.DEFAULT_SIZE, 446, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnClockIn, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnClockOut, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(57))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(242, Short.MAX_VALUE)
					.addComponent(lblHome)
					.addGap(240))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(248, Short.MAX_VALUE)
					.addComponent(btnLogout)
					.addGap(246))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(lblHome)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnClockIn, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnClockOut, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblInformation, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(infoTable, GroupLayout.PREFERRED_SIZE, 244, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnLogout)
					.addGap(19))
		);
		frame.getContentPane().setLayout(groupLayout);
	}
	
private void homeLevel2(){
	
		frame = new JFrame();
		frame.setBounds(100, 100, 575, 460);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setTitle("Company");
		
		JLabel lblHome = new JLabel("Home");
		lblHome.setFont(new Font(lblHome.getFont().getName(), lblHome.getFont().getStyle(), 30));
		
		String[] column = {"title","info"};
		DefaultTableModel tableModel = new DefaultTableModel(column,0);
		tableModel.addRow(new String[]{"ID", personnel.getID()});
		tableModel.addRow(new String[]{"Name", personnel.getName()});
		tableModel.addRow(new String[]{"Position", personnel.getPosition()});
		tableModel.addRow(new String[]{"Department", personnel.getDepartment()});
		infoTable = new JTable(tableModel);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				LoginPage login = new LoginPage();
			}
		});
		
		JButton btnClockIn = new JButton("Clock In");
		btnClockIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TimeSystem.createInTime(personnelID);
				frame.removeAll();
				homeLevel2();
				frame.validate();
			}
		});
		btnClockIn.setPreferredSize(new Dimension(90, 25));
		
		JButton btnClockOut = new JButton("Clock Out");
		btnClockOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TimeSystem.createOutTime(personnelID);
				frame.removeAll();
				homeLevel2();
				frame.validate();
			}
		});
		btnClockOut.setPreferredSize(new Dimension(90, 25));
		
		if (status == false){
			btnClockOut.setEnabled(false);
		} else{
			btnClockIn.setEnabled(false);
		}
		
		JButton btnSetWorkingTime = new JButton("Set Working Time");
		btnSetWorkingTime.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SetWorkTimePage swt = new SetWorkTimePage(status,personnel);
				System.exit(0);
			}
		});
		
		JLabel lblInformation = new JLabel("Information");
		lblInformation.setHorizontalAlignment(JLabel.CENTER);
		lblInformation.setOpaque(true);
		lblInformation.setBackground(new Color(102, 178, 255));
		
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(56)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(infoTable, GroupLayout.DEFAULT_SIZE, 446, Short.MAX_VALUE)
						.addComponent(lblInformation, GroupLayout.DEFAULT_SIZE, 446, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnClockIn, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnClockOut, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnSetWorkingTime)))
					.addGap(57))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(242, Short.MAX_VALUE)
					.addComponent(lblHome)
					.addGap(240))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(248, Short.MAX_VALUE)
					.addComponent(btnLogout)
					.addGap(246))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(lblHome)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnClockIn, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnClockOut, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSetWorkingTime))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblInformation, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(infoTable, GroupLayout.PREFERRED_SIZE, 244, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnLogout)
					.addGap(19))
		);
		frame.getContentPane().setLayout(groupLayout);
	}
}