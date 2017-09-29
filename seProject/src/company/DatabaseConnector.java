package company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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

	public Personnel getPersonnel_withID(String id) {
		Personnel personnel = null;
		try {
			String query = "Select * from personnel where id=" + id;
			ResultSet resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				String newId = resultSet.getString(1);
				String name = resultSet.getString(2);
				String position = resultSet.getString(3);
				String department = resultSet.getString(4);
				int permission = resultSet.getInt(5);
				personnel = new Personnel(newId, name, position, department, permission);
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

}
