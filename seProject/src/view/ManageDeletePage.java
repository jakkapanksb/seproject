package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.Window.Type;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.Toolkit;

public class ManageDeletePage {

	private JFrame frmDeletePersonnel;
	private JTable table;
	private JFrame frmHomePage;

	/**
	 * Create the application.
	 * @param frmHomePage 
	 */
	public ManageDeletePage(JFrame frmHomePage) {
		this.frmHomePage = frmHomePage;
		
		frmDeletePersonnel = new JFrame();
		frmDeletePersonnel.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frmDeletePersonnel.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		    	frmHomePage.setEnabled(true);
		    	frmDeletePersonnel.dispose();
		    }
		});
		frmDeletePersonnel.setIconImage(Toolkit.getDefaultToolkit().getImage(ManageDeletePage.class.getResource("/com/sun/javafx/scene/web/skin/Cut_16x16_JFX.png")));
		frmDeletePersonnel.getContentPane().setBackground(new Color(255, 255, 240));
		frmDeletePersonnel.setType(Type.POPUP);
		frmDeletePersonnel.setTitle("Delete Personnel");
		frmDeletePersonnel.setBounds(100, 100, 565, 365);
		frmDeletePersonnel.setVisible(true);
		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 529, 270);
		frmDeletePersonnel.getContentPane().add(scrollPane);
		
		String[] manageHeader = {"SELECT","ID","NAME","POSITION","DEPARTMENT","PERMISSION","SALARY"};
		DefaultTableModel manageModel = new DefaultTableModel(0, 0);
		table = new JTable(){

            private static final long serialVersionUID = 1L;

            /*@Override
            public Class getColumnClass(int column) {
            return getValueAt(0, column).getClass();
            }*/
            @Override
            public Class getColumnClass(int column) {
                switch (column) {
	                case 0:
	                    return Boolean.class;
	                case 1:
	                    return String.class;
	                case 2:
						return String.class;
	                case 3:
						return String.class;
	                case 4:
						return String.class;
	                case 5:
						return String.class;
	                case 6:
						return int.class;
	                default:
	                    return float.class;
                }
            }
        };
		manageModel.setColumnIdentifiers(manageHeader);
		//ArrayList<Personnel> personnelList = PersonnelManagement.getAllPersonnel();
		//for(Personnel person : personnelList){
		//	manageModel.addRow(new Object[] { false, person.getID(), person.getName(), person.getPosition()
		//	, person.getDepartment(), person.getPermission(), person.getSalary()});
		//}
		manageModel.addRow(new Object[] { false,"1", "data", "data","data", "data", "data" });
		manageModel.addRow(new Object[] { false,"2", "data", "data","data", "data", "data" });
		table.setModel(manageModel);
		scrollPane.setViewportView(table);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ArrayList<String> id = new ArrayList();
				for(int i=0;i<table.getRowCount();i++){
					if(Boolean.valueOf((boolean) table.getValueAt(i, 0)) == true){
						id.add((String) table.getValueAt(i, 1));
					}
				}
				String warning = "";
				if(id.isEmpty()==true){
					warning = "Plase select some elements.";
					JOptionPane.showMessageDialog(frmDeletePersonnel, warning);
				}
				else{
					warning = "Are you sure to delete"+"\n";
					for(String value:id){
						warning+=value+"\n";
					}
					int dialogResult =  JOptionPane.showConfirmDialog(frmDeletePersonnel, warning);
					if(dialogResult == JOptionPane.YES_OPTION){
						for(String value:id){
							//PerSonnelManagement.deletePersonnel(value);
						}
					}
				}
				
				
			}
		});
		btnDelete.setBackground(new Color(224, 255, 255));
		btnDelete.setBounds(50, 292, 89, 23);
		frmDeletePersonnel.getContentPane().add(btnDelete);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//unfreeze homePage
				frmHomePage.setEnabled(true);
				frmDeletePersonnel.dispose();
			}
		});
		btnCancel.setBackground(new Color(255, 192, 203));
		btnCancel.setBounds(400, 292, 89, 23);
		frmDeletePersonnel.getContentPane().add(btnCancel);
		
		frmDeletePersonnel.getContentPane().setLayout(null);
	}

}
