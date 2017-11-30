package company;

public class SystemCompany {

	private Personnel user;
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
		String id = database.getID_withUsername(username);
		user = database.getPersonnel_withID(id);
	}
	
	public Boolean checkStatus(){
		return database.getStatus(user.getID());
	}
	
	public Personnel getPersonnel(){
		return user;
	}
}