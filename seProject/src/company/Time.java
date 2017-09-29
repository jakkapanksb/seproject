package company;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Time {
	
	private String personnelID;
	private Date inTime;
	private Date outTime;
	private DatabaseConnector database;
	
	public Time(String id){
		database = new DatabaseConnector();
		database.connectDB();
		personnelID = id;
	}
	
	public void setInTime(){
		inTime = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		String time = sdf.format(inTime);
		sdf = new SimpleDateFormat("dd/mm/yyyy");
		String date = sdf.format(inTime);
		database.insertInTime(personnelID,date,time);
	}
	
	public void setOutTime(){
		outTime = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		String time = sdf.format(outTime);
		database.updateOutTime(personnelID,time);
	}
	
	public void calHour(){
		//connect with DB
	}
	
	public Date[] getClockin_out(){
		String[] workTime = database.getWorkTime();
		Date clockIn = new Date();
		Date clockOut = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		try {
			clockIn = sdf.parse(workTime[0]);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			clockOut = sdf.parse(workTime[1]);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Date[] list = {clockIn,clockOut};
		return list;
		
	}
	
	public void changeWorkTime(Date startTime, Date endTime){
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		String clockInTime = sdf.format(startTime);
		String clockOutTime = sdf.format(endTime);
		database.updateWorkTime(clockInTime,clockOutTime);
	}

//	test function
//	public void getTime(){
//		System.out.println(logInTime);
//		System.out.println(logOutTime);
//	}
}
