package company;

import java.util.ArrayList;

public class PersonnelManagement {
	
	private DatabaseConnector database;
	private ArrayList<Personnel> allPersonnel;
	private Personnel user;
	
	public PersonnelManagement() {
		database = new DatabaseConnector();
		database.connectDB();
		allPersonnel = new ArrayList<Personnel>();
		}
	
	public void setPersonnel(String username){
		user.setID(database.getID_withUsername(username));
	}
	
	public Boolean checkStatus(){
		return database.getStatus(user.getID());
	}
	
	public Personnel getPersonnel(){
		return user;
	}
	
	public ArrayList<Personnel> getAllPersonnel(){
		allPersonnel = database.getAllPersonnel();
		return allPersonnel;
	}
	
	public void addPersonnel(String name, String position, String department,int permission, float salary) {
		Personnel newPersonnel = new Personnel("0",name,position,department,permission,salary);
		getAllPersonnel();
		String id = allPersonnel.get(allPersonnel.size()-1).getID();
		database.addPersonnelToDB(id, newPersonnel);
	}
	
	public void editPersonnel() {
		database.editPersonnelToDB();
	}
	
	public void deletePersonnel(String id) {
		database.deletePersonnelDB(id);
	}

}
