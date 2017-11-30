package company;

import java.util.ArrayList;

public class PersonnelManagement {
	
	private DatabaseConnector database;
	private ArrayList<Personnel> allPersonnel;
	private Personnel user;
	
	public PersonnelManagement() {
		database = new DatabaseConnector();
		database.connectDB();
		user = new Personnel(null,null,null,null,0,0);
		allPersonnel = new ArrayList<Personnel>();
		}
	
	public void setPersonnelID(String username){
		user.setID(database.getID_withUsername(username));
	}
	
	public Boolean checkStatus(){
		return database.getStatus(user.getID());
	}
	
	public Personnel getPersonnel(){
		return database.getPersonnel_withID(user.getID());
	}
	
	public ArrayList<Personnel> getAllPersonnel(){
		return allPersonnel;
	}
	
	public void addPersonnel(String name, String position, String department,int permission, float salary) {
		
	}
	
	public void editPersonnel() {
		
	}
	
	public void deletePersonnel() {
		
	}

}
