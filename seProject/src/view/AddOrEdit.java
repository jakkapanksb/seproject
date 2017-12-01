package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import company.Personnel;
import company.PersonnelManagement;

import javax.swing.JButton;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class AddOrEdit {

	private JFrame frame;
	private JTextField nameField;
	private JTextField permissionField;
	private JTextField depField;
	private JTextField posField;
	private JTextField salaryField;
	private String status;
	private JFrame frmHomePage;
	private JComboBox comboBox;
	private JTextField idField;
	private PersonnelManagement pm;

	/**
	 * Create the application.
	 * @param frmHomePage 
	 * @param string 
	 */
	public AddOrEdit(String status, JFrame frmHomePage) {
		pm = new PersonnelManagement();
		this.status = status;
		this.frmHomePage = frmHomePage;
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		    	frmHomePage.setEnabled(true);
		    	frame.dispose();
		    }
		});
		frame.setTitle(status + " Personnel");
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(AddOrEdit.class.getResource("/view/account.png")));
		frame.getContentPane().setBackground(new Color(255, 255, 224));
		frame.setVisible(true);
		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		JLabel lblId = new JLabel("ID:");
		lblId.setBounds(10, 25, 46, 14);
		frame.getContentPane().add(lblId);
		
		ArrayList<Personnel> dataFromDB = new ArrayList<Personnel>();
		dataFromDB = pm.getAllPersonnel();
		
		String[] elements = new String[dataFromDB.size()+1];
		elements[0] = "select id";
		for(int i=0; i<dataFromDB.size(); i++){
			elements[i+1] = dataFromDB.get(i).getID();
		}
		if(status.equals("Edit")){
			comboBox = new JComboBox(elements);
			comboBox.setBounds(39, 22, 65, 20);
			frame.getContentPane().add(comboBox);
			comboBox.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					//JComboBox event = (JComboBox) e.getSource();

	                Object selected = comboBox.getSelectedItem();
					String id = selected.toString();
					Personnel per = pm.showPersonnel(id);
					nameField.setText(per.getName());
					posField.setText(per.getPosition());
					depField.setText(per.getDepartment());
					permissionField.setText(per.getPermission() + "");
					salaryField.setText(per.getSalary() + "");
				}
				
			});
		}
		else if(status.equals("Add")){
			idField = new JTextField();
			idField.setBounds(39, 22, 65, 20);
			frame.add(idField);
		}
		
		
		
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(10, 53, 46, 14);
		frame.getContentPane().add(lblName);
		
		nameField = new JTextField();
		nameField.setBounds(49, 50, 157, 20);
		frame.getContentPane().add(nameField);
		nameField.setColumns(10);
		
		JLabel lblPermission = new JLabel("Permission:");
		lblPermission.setBounds(10, 143, 72, 14);
		frame.getContentPane().add(lblPermission);
		
		permissionField = new JTextField();
		permissionField.setBounds(92, 140, 114, 20);
		frame.getContentPane().add(permissionField);
		permissionField.setColumns(10);
		
		JLabel lblDepartment = new JLabel("Department:");
		lblDepartment.setBounds(10, 115, 83, 14);
		frame.getContentPane().add(lblDepartment);
		
		depField = new JTextField();
		depField.setBounds(82, 112, 124, 20);
		frame.getContentPane().add(depField);
		depField.setColumns(10);
		
		JLabel lblPosition = new JLabel("Position:");
		lblPosition.setBounds(10, 84, 53, 14);
		frame.getContentPane().add(lblPosition);
		
		posField = new JTextField();
		posField.setBounds(76, 81, 130, 20);
		frame.getContentPane().add(posField);
		posField.setColumns(10);
		
		salaryField = new JTextField();
		salaryField.setBounds(49, 168, 157, 20);
		frame.getContentPane().add(salaryField);
		salaryField.setColumns(10);
		
		JLabel lblSalary = new JLabel("Salary:");
		lblSalary.setBounds(10, 171, 46, 14);
		frame.getContentPane().add(lblSalary);
		
		JButton btnSubmit = new JButton(status);
		btnSubmit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(status.equals("Edit")){
					pm.editPersonnel(comboBox.getSelectedItem().toString(),
					nameField.getText(), posField.getText(), depField.getText(), 
					permissionField.getText(), salaryField.getText());
				}
				else if(status.equals("Add")){
					pm.addPersonnel(
					nameField.getText(), posField.getText(), depField.getText(), 
					permissionField.getText(), salaryField.getText());
				}
			}
		});
		btnSubmit.setBackground(new Color(240, 255, 240));
		btnSubmit.setBounds(10, 227, 89, 23);
		frame.getContentPane().add(btnSubmit);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frmHomePage.setEnabled(true);
				frame.dispose();
			}
		});
		btnCancel.setBackground(new Color(255, 192, 203));
		btnCancel.setBounds(117, 227, 89, 23);
		frame.getContentPane().add(btnCancel);
		frame.setBounds(100, 100, 238, 300);
		
		frame.getContentPane().setLayout(null);
	}
}
