package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;

import company.DatabaseConnector;
import company.Personnel;
import company.TimeSystem;

import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Color;
import java.awt.Toolkit;


public class SetWorkTimePage {
	
	private TimeSystem timeSys;
	private Boolean status;
	private Personnel personnel;
	private JFrame frmSetWorkkingTime;
	private JFrame frmHomePage;
	
	public SetWorkTimePage(Boolean inStatus,Personnel inPersonnel, JFrame frmHomePage) {
		status = inStatus;
		personnel = inPersonnel;
		timeSys = new TimeSystem();
		this.frmHomePage = frmHomePage;

		frmSetWorkkingTime = new JFrame();
		frmSetWorkkingTime.setIconImage(Toolkit.getDefaultToolkit().getImage(SetWorkTimePage.class.getResource("/javax/swing/plaf/metal/icons/ocean/collapsed-rtl.gif")));
		frmSetWorkkingTime.getContentPane().setBackground(new Color(255, 255, 224));
		frmSetWorkkingTime.setBounds(100, 100, 302, 235);
		frmSetWorkkingTime.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frmSetWorkkingTime.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		    	frmHomePage.setEnabled(true);
		    	frmSetWorkkingTime.dispose();
		    }
		});
		frmSetWorkkingTime.setVisible(true);
		frmSetWorkkingTime.setTitle("Set Workking Time");
		setWorkTime();
	}
	
	private void setWorkTime(){
		
		JLabel lblClockIn = new JLabel("Clock In");
		
		Date timeClockIn = timeSys.getClockin_out()[0];
		SpinnerDateModel sm = new SpinnerDateModel(timeClockIn, null, null, Calendar.HOUR_OF_DAY);
		JSpinner spinnerClockIn = new JSpinner(sm);
		JSpinner.DateEditor de = new JSpinner.DateEditor(spinnerClockIn, "HH:mm");
		spinnerClockIn.setEditor(de);
		
		JLabel lblClockOut = new JLabel("Clock Out");
		
		Date timeClockOut = timeSys.getClockin_out()[1];
		sm = new SpinnerDateModel(timeClockOut, null, null, Calendar.HOUR_OF_DAY);
		JSpinner spinnerClockOut = new JSpinner(sm);
		de = new JSpinner.DateEditor(spinnerClockOut, "HH:mm");
		spinnerClockOut.setEditor(de);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBackground(new Color(255, 192, 203));
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmHomePage.setEnabled(true);
				frmSetWorkkingTime.dispose();
			}
		});
		
		JButton btnOk = new JButton("OK");
		btnOk.setBackground(new Color(240, 255, 240));
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Object[] options = {"Yes","No"};
				int dialogResult  = JOptionPane.showOptionDialog(frmSetWorkkingTime,
								"Are you sure want change working time?",
								"Set Working Time",
								JOptionPane.YES_NO_OPTION,
								JOptionPane.WARNING_MESSAGE,
								null,     //do not use a custom Icon
								options,  //the titles of buttons
								options[0]); //default button title
				if(dialogResult == JOptionPane.YES_OPTION){
					Date clockInTime = (Date) spinnerClockIn.getValue();
					Date clockOutTime = (Date) spinnerClockOut.getValue();
					timeSys.changeWorkTime(clockInTime,clockOutTime);
					System.exit(0);
					HomePage homePage = new HomePage(status,personnel);
				}
			}
		});
		
		GroupLayout groupLayout = new GroupLayout(frmSetWorkkingTime.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addGap(20)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblClockOut)
							.addContainerGap())
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(lblClockIn, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addGap(228))
							.addGroup(groupLayout.createSequentialGroup()
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
									.addComponent(spinnerClockOut, Alignment.LEADING)
									.addComponent(spinnerClockIn, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE)
									.addGroup(groupLayout.createSequentialGroup()
										.addComponent(btnOk)
										.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(btnCancel)))
								.addContainerGap()))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(24)
					.addComponent(lblClockIn)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(spinnerClockIn, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(lblClockOut)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(spinnerClockOut, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCancel)
						.addComponent(btnOk))
					.addContainerGap(33, Short.MAX_VALUE))
		);
		frmSetWorkkingTime.getContentPane().setLayout(groupLayout);
	}
}