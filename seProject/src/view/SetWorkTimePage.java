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
import company.Time;

import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;


public class SetWorkTimePage {
	
	private Time time;
	private Boolean status;
	private Personnel personnel;
	private JFrame frame;
	
	public SetWorkTimePage(Boolean inStatus,Personnel inPersonnel) {
		status = inStatus;
		personnel = inPersonnel;
		time = new Time(personnel.getID());
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
		setWorkTime();
	}
	
	private void setWorkTime(){
		
		JLabel lblSetWorkingTime = new JLabel("Set Working Time");
		lblSetWorkingTime.setFont(new Font(lblSetWorkingTime.getFont().getName(), lblSetWorkingTime.getFont().getStyle(), 30));
		
		JLabel lblClockIn = new JLabel("Clock In");
		
		Date timeClockIn = time.getClockin_out()[0];
		SpinnerDateModel sm = new SpinnerDateModel(timeClockIn, null, null, Calendar.HOUR_OF_DAY);
		JSpinner spinnerClockIn = new JSpinner(sm);
		JSpinner.DateEditor de = new JSpinner.DateEditor(spinnerClockIn, "HH:mm");
		spinnerClockIn.setEditor(de);
		
		JLabel lblClockOut = new JLabel("Clock Out");
		
		Date timeClockOut = time.getClockin_out()[1];
		sm = new SpinnerDateModel(timeClockOut, null, null, Calendar.HOUR_OF_DAY);
		JSpinner spinnerClockOut = new JSpinner(sm);
		de = new JSpinner.DateEditor(spinnerClockOut, "HH:mm");
		spinnerClockOut.setEditor(de);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				HomePage homePage = new HomePage(status,personnel);
			}
		});
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Object[] options = {"Yes","No"};
				int dialogResult  = JOptionPane.showOptionDialog(frame,
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
					time.changeWorkTime(clockInTime,clockOutTime);
					HomePage homePage = new HomePage(status,personnel);
				}
			}
		});
		
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(88)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(83)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblClockIn, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addGap(171))
								.addComponent(spinnerClockIn, GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
								.addComponent(lblClockOut)
								.addComponent(spinnerClockOut, GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(btnCancel)
									.addPreferredGap(ComponentPlacement.RELATED, 73, Short.MAX_VALUE)
									.addComponent(btnOk)))
							.addGap(169))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblSetWorkingTime)
							.addGap(148))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(50)
					.addComponent(lblSetWorkingTime)
					.addGap(37)
					.addComponent(lblClockIn)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(spinnerClockIn, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(12)
					.addComponent(lblClockOut)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(spinnerClockOut, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(27)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCancel)
						.addComponent(btnOk))
					.addContainerGap(136, Short.MAX_VALUE))
		);
		frame.getContentPane().setLayout(groupLayout);
	}
}