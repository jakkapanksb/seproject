package company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DatabaseConnector {

	private Statement statement;
	private Connection connect;

	public Boolean connectDB() {
		try {
			// setup
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager
					.getConnection("jdbc:mysql://127.0.0.1:3306/company?useSSL=false&user=root&password=root");
			if (connect != null) {
				statement = connect.createStatement();
			}
			return true;
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return false;
	}

	public Boolean closeDB() {
		try {
			connect.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public String getAccountPW(String username) {
		String pass = null;
		try {
			String query = "Select * from account where username=\"" + username + "\"";
			ResultSet resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				pass = resultSet.getString(3);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pass;
	}

	public boolean getStatus(String id) {
		boolean checkStatus = false;
		try {
			int num = Integer.parseInt(id);
			String query = "Select * from account where id=" + num;
			ResultSet resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				int status = resultSet.getInt(4);
				if (status == 1) {
					checkStatus = true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return checkStatus;
	}

	public Personnel getPersonnel_withID(String username) {
		Personnel personnel = null;
		try {
			String query = "Select * from personnel where name=" + username;
			ResultSet resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				String newId = resultSet.getString(1);
				String name = resultSet.getString(2);
				String position = resultSet.getString(3);
				String department = resultSet.getString(4);
				int permission = resultSet.getInt(5);
				float salary = resultSet.getFloat(6);
				personnel = new Personnel(newId, name, position, department, permission,salary);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return personnel;
	}

	public boolean insertInTime(String id, String date, String inTime) {
		int newid = Integer.parseInt(id);
		try {
			String query = "insert into time (id,date,inTime) values (?,?,?)";
			PreparedStatement preparedStmt = connect.prepareStatement(query);
			preparedStmt.setInt(1, newid);
			preparedStmt.setString(2, date);
			preparedStmt.setString(3, inTime);
			// execute the java preparedstatement
			preparedStmt.executeUpdate();
			query = "update account set status=1 where id=?";
			preparedStmt = connect.prepareStatement(query);
			preparedStmt.setInt(1, newid);
			preparedStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean updateOutTime(String id, String outTime) {
		int newid = Integer.parseInt(id);
		try {
			String query = "update time set ouTtime=? where id=?";
			PreparedStatement preparedStmt = connect.prepareStatement(query);
			preparedStmt.setString(1, outTime);
			preparedStmt.setInt(2, newid);
			// execute the java preparedstatement
			preparedStmt.executeUpdate();
			query = "update account set status=0 where id=?";
			preparedStmt = connect.prepareStatement(query);
			preparedStmt.setInt(1, newid);
			preparedStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public String getID_withUsername(String username) {
		String id = null;
		try {
			String query = "Select * from account where username=\"" + username + "\"";
			ResultSet resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				id = resultSet.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}

	public void updateWorkTime(String clockInTime, String clockOutTime) {
		try {
			String query = "update informationtime set inTime=?, outTime=?";
			PreparedStatement preparedStmt = connect.prepareStatement(query);
			preparedStmt.setString(1, clockInTime);
			preparedStmt.setString(2, clockOutTime);
			preparedStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public String[] getTime() {
		return null;
	}

	public String[] getWorkTime() {
		try {
			String query = "Select * from informationtime ";
			ResultSet resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				String[] objectTime = { resultSet.getString(2), resultSet.getString(3) };
				return objectTime;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void setStatus() {

	}
	
	public void updateSalary() {
		
	}
	
	public ArrayList<Time> getAllTime() {
		try {
			String query = "SELECT personnel.id, personnel.name, time.date, time.inTime, time.outTime" + 
					"FROM personnel" + 
					"INNER JOIN time ON time.id=personnel.id";
			ResultSet resultSet = statement.executeQuery(query);
			ArrayList<Time> allTime = new ArrayList<Time>();
			while (resultSet.next()) {
				//Time newTime = new Time(resultSet.getString(1), resultSet.getString(2),resultSet.getString(3), resultSet.getString(4), resultSet.getString(5));
			}
			return allTime;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<Personnel> getAllPersonnel(){
		try {
			String query = "SELECT * FROM personnel";
			ResultSet resultSet = statement.executeQuery(query);
			ArrayList<Personnel> allPersonnel = new ArrayList<Personnel>();
			while (resultSet.next()) {
				Personnel newPersonnel = new Personnel(resultSet.getString(1), resultSet.getString(2),resultSet.getString(3), resultSet.getString(4), resultSet.getInt(5), resultSet.getFloat(6));
				allPersonnel.add(newPersonnel);
			}
			return allPersonnel;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void addPersonnelToDB(String id,Personnel personnel) {
		try {
			int addID = Integer.parseInt(id)+1;
			String query = "INSERT personnel set id=?, name=?, position=?, department=?, permission=?, salary=?";
			PreparedStatement preparedStmt = connect.prepareStatement(query);
			preparedStmt.setString(1, addID+"");
			preparedStmt.setString(2, personnel.getName());
			preparedStmt.setString(3, personnel.getPosition());
			preparedStmt.setString(4, personnel.getDepartment());
			preparedStmt.setInt(5, personnel.getPermission());
			preparedStmt.setFloat(6, personnel.getSalary());
			preparedStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void editPersonnelToDB() {
		// TODO Auto-generated method stub
		
	}

	public void deletePersonnelDB(String id) {
		// TODO Auto-generated method stub
		try {
		String query = "DELETE FROM personnel WHERE id=?";
		PreparedStatement preparedStmt = connect.prepareStatement(query);
		preparedStmt.setString(1, id);
		preparedStmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

}
