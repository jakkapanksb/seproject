package company;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;

public class TimeSystem {

	private static DatabaseConnector database;
	private ArrayList<TimeReported> timeReporteds;
	
	public TimeSystem(){
		database = new DatabaseConnector();
		database.connectDB();
		timeReporteds = new ArrayList<TimeReported>();
	}
	
	public static void createInTime(String id){
		Date time = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		String stime = sdf.format(time);
		sdf = new SimpleDateFormat("dd/mm/yyyy");
		String date = sdf.format(time);
		database.insertInTime(id,date,stime);
	}
	
	public static void createOutTime(String id){
		Date time = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		String stime = sdf.format(time);
		database.updateOutTime(id,stime);
	}
	
	public Date[] getClockin_out(){
		String[] workTime = database.getWorkTime();
		Date clockIn = new Date();
		Date clockOut = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		try {
			clockIn = sdf.parse(workTime[0]);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		try {
			clockOut = sdf.parse(workTime[1]);
		} catch (ParseException e) {
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
	
	public ArrayList<TimeReported> getAllTimeReported(){
		return timeReporteds;
	}
	
	public TimeReported getUserTimeReported(String id){
		for (TimeReported timeRe : timeReporteds){
			if (timeRe.getID().matches(id)){
				return timeRe;
			}
		}
		return null;
	}
}
