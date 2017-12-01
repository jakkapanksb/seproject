package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import javax.swing.JFormattedTextField;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import company.Personnel;
import company.PersonnelManagement;
import company.TimeReported;
import company.TimeSystem;
import view.SetWorkTimePage;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class HomePage {
	
	private TimeSystem timeSys;
	private Boolean status;
	private Personnel personnel;
	private String personnelID;
	private JFrame frmHomePage;
	private JTable infoTable;
	private JTable reportTable;
	private JTable manageTable;
	private JButton btnClockin;
	private JButton btnClockout;
	private PersonnelManagement pm;


	/**
	 * Create the application.
	 * @param personnel2 
	 * @param status2 
	 * @param personnel2 
	 * @param status2 
	 */
	public HomePage(Boolean inStatus,Personnel inPersonnel) {
		pm = new PersonnelManagement();
		status = inStatus;
		personnel = inPersonnel;
		personnelID = inPersonnel.getID();
		timeSys = new TimeSystem();
		
		frmHomePage = new JFrame();
		frmHomePage.setIconImage(Toolkit.getDefaultToolkit().getImage(HomePage.class.getResource("/javax/swing/plaf/metal/icons/ocean/homeFolder.gif")));
		frmHomePage.setTitle("Home Page");
		frmHomePage.getContentPane().setBackground(new Color(255, 250, 205));
		frmHomePage.setBounds(100, 100, 575, 460);
		frmHomePage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmHomePage.setVisible(true);
		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @param <btnClockout>
	 */
	private <btnClockout> void initialize() {
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(new Color(230, 230, 250));
		tabbedPane.setBounds(10, 11, 539, 362);
		frmHomePage.getContentPane().add(tabbedPane);
		
		JPanel infoPanel = new JPanel();
		infoPanel.setBackground(new Color(255, 255, 240));
		tabbedPane.addTab("Infomation", null, infoPanel, null);
		infoPanel.setLayout(null);
		
		String[] infoHeader = {"TITLE","INFO"};
		infoTable = new JTable();
		infoTable.setBounds(10, 11, 514, 312);
		DefaultTableModel tableModel = new DefaultTableModel(0,0);
		tableModel.setColumnIdentifiers(infoHeader);
		infoTable.setModel(tableModel);
		tableModel.addRow(new String[]{"ID", personnel.getID()});
		tableModel.addRow(new String[]{"Name", personnel.getName()});
		tableModel.addRow(new String[]{"Position", personnel.getPosition()});
		tableModel.addRow(new String[]{"Department", personnel.getDepartment()});
		infoPanel.add(infoTable);
		
		JPanel reportPanel = new JPanel();
		reportPanel.setBackground(new Color(255, 255, 240));
		tabbedPane.addTab("Time Report", null, reportPanel, null);
		reportPanel.setLayout(null);
				
		JScrollPane reportScrollPane = new JScrollPane();
		reportScrollPane.setBounds(10, 11, 514, 312);
		reportPanel.add(reportScrollPane);
		
		String[] reportHeader = {"ID","NAME","DATE","CHECK IN","CHECK OUT","SALARY"};
		reportTable = new JTable();
		DefaultTableModel dtm = new DefaultTableModel(0, 0);
		dtm.setColumnIdentifiers(reportHeader);
		if(personnel.getPermission()==1){
			ArrayList<TimeReported> timeReported = timeSys.getUserTimeReported(personnel.getID());
			for(TimeReported report : timeReported){
				dtm.addRow(new Object[] { report.getID(), report.getName(), report.getDate()
				, report.getInTime(), report.getOutTime(), report.getSalary()});
			}
		}
		else if(personnel.getPermission()==2){
			ArrayList<TimeReported> timeReported = timeSys.getAllTimeReported();
			for(TimeReported report : timeReported){
				dtm.addRow(new Object[] { report.getID(), report.getName(), report.getDate()
				, report.getInTime(), report.getOutTime(), report.getSalary()});
			}
		}
		reportTable.setModel(dtm);
		reportScrollPane.setViewportView(reportTable);
		
		if(personnel.getPermission()==2){
			JPanel managePanel = new JPanel();
			managePanel.setBackground(new Color(255, 255, 240));
			tabbedPane.addTab("Personnel Management", null, managePanel, null);
			managePanel.setLayout(null);
			
			JScrollPane manageScrollPane = new JScrollPane();
			manageScrollPane.setBounds(10, 11, 514, 278);
			managePanel.add(manageScrollPane);
			
			String[] manageHeader = {"ID","NAME","POSITION","DEPARTMENT","PERMISSION","SALARY"};
			manageTable = new JTable();
			DefaultTableModel manageModel = new DefaultTableModel(0, 0);
			manageModel.setColumnIdentifiers(manageHeader);
			ArrayList<Personnel> personnelList = pm.getAllPersonnel();
			for(Personnel person : personnelList){
				manageModel.addRow(new Object[] { person.getID(), person.getName(), person.getPosition()
				, person.getDepartment(), person.getPermission(), person.getSalary()});
			}
			manageTable.setModel(manageModel);
			manageScrollPane.setViewportView(manageTable);
			
			JButton btnDelete = new JButton("Delete");
			btnDelete.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					//freeze homePage until close manageDelete
					frmHomePage.setEnabled(false);
					
					ManageDeletePage manageDelete = new ManageDeletePage(frmHomePage);
					
					//build new manageTable
					manageTable = new JTable();
					DefaultTableModel manageModel = new DefaultTableModel(0, 0);
					manageModel.setColumnIdentifiers(manageHeader);
					ArrayList<Personnel> personnelList = pm.getAllPersonnel();
					for(Personnel person : personnelList){
						manageModel.addRow(new Object[] { person.getID(), person.getName(), person.getPosition()
						, person.getDepartment(), person.getPermission(), person.getSalary()});
					}
					manageModel.addRow(new Object[] { "afterdelete1", "data", "data","data", "data", "data" });
					manageModel.addRow(new Object[] { "afterdelete2", "data", "data","data", "data", "data" });
					manageTable.setModel(manageModel);
					manageScrollPane.setViewportView(manageTable);
				}
			});
			
			btnDelete.setBackground(new Color(240, 255, 240));
			btnDelete.setBounds(10, 300, 89, 23);
			managePanel.add(btnDelete);
			
			JButton btnAdd = new JButton("Add");
			btnAdd.setBackground(new Color(240, 255, 240));
			btnAdd.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					frmHomePage.setEnabled(false);
					AddOrEdit edit = new AddOrEdit("Add",frmHomePage);
				}
			});
			btnAdd.setBounds(435, 300, 89, 23);
			managePanel.add(btnAdd);
			
			JButton btnEdit = new JButton("Edit");
			btnEdit.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					frmHomePage.setEnabled(false);
					AddOrEdit edit = new AddOrEdit("Edit",frmHomePage);
				}
			});
			btnEdit.setBackground(new Color(240, 255, 240));
			btnEdit.setBounds(220, 300, 89, 23);
			managePanel.add(btnEdit);
			
			JButton btnSetWorkingTime = new JButton("Set Working Time");
			btnSetWorkingTime.setBackground(new Color(224, 255, 255));
			btnSetWorkingTime.setBounds(212, 384, 152, 23);
			btnSetWorkingTime.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					frmHomePage.setEnabled(false);
					SetWorkTimePage swt = new SetWorkTimePage(status,personnel,frmHomePage);
				}
			});
			frmHomePage.getContentPane().add(btnSetWorkingTime);
		}
		
		btnClockin = new JButton("Clockin");
		btnClockin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				timeSys.createInTime(personnelID);
				btnClockout.setEnabled(true);
			}
		});
		btnClockin.setBackground(new Color(224, 255, 255));
		btnClockin.setBounds(10, 384, 89, 23);
		frmHomePage.getContentPane().add(btnClockin);
		
		JButton btnClockout = new JButton("Clockout");
		btnClockout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				timeSys.createOutTime(personnelID);
				btnClockout.setEnabled(false);
			}
		});
		btnClockout.setBackground(new Color(224, 255, 255));
		btnClockout.setBounds(111, 384, 89, 23);
		frmHomePage.getContentPane().add(btnClockout);
		
		if (status == false){
			btnClockout.setEnabled(false);
		} else{
			btnClockin.setEnabled(false);
		}
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frmHomePage.dispose();
				LoginPage loginPage = new LoginPage();
			}
		});
		btnLogout.setBounds(460, 384, 89, 23);
		btnLogout.setBackground(new Color(255, 192, 203));
		frmHomePage.getContentPane().add(btnLogout);
		
		frmHomePage.getContentPane().setLayout(null);
		}
}
