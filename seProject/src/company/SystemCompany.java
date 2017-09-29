package company;

public class SystemCompany {

	private String personnelID;
	private DatabaseConnector database;
	
	public SystemCompany() {
		database = new DatabaseConnector();
		database.connectDB();
	}
	
	public Boolean checkPW(String inUsername,String inPassword){
		String password = database.getAccountPW(inUsername);
		if (inPassword.equals(password)){
			return true;
		}
		return false;
	}
	
	public void setPersonnelID(String username){
		personnelID = database.getID_withUsername(username);
	}
	
	public Boolean checkStatus(){
		return database.getStatus(personnelID);
	}
	
	public Personnel getPersonnel(){
		return database.getPersonnel_withID(personnelID);
	}
}