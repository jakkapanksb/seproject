package company;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeReported {

	private String personnelID;
	private String name;
	private Date date;
	private Date inTime;
	private Date outTime;
	private float salary;
	private DatabaseConnector database;
	private float salaryPerHour;
	
	public TimeReported(String id,String inName,String inDate,String inTime,String outTime,float inSalary){
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		personnelID = id;
		name = inName;
		try {
			date = sdf.parse(inDate);
			this.inTime = sdf.parse(inTime);
			this.outTime = sdf.parse(outTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		salary = inSalary;
		salaryPerHour = salary/6;
		calHour();
	}
	
	private void calHour(){
		TimeSystem tsystem = new TimeSystem();
		Date[] clockin_out = tsystem.getClockin_out();;
		int checkIn = inTime.getHours();
		if(inTime.getMinutes()>=45){
			checkIn++;
		}
		if(checkIn > clockin_out[0].getHours()){
			salary -= (checkIn - clockin_out[0].getHours())*salaryPerHour;
		}
		int checkOut = outTime.getHours();
		if(outTime.getMinutes()>=45){
			checkOut++;
		}
		if(checkOut > clockin_out[1].getHours()){
			salary += (checkOut - clockin_out[1].getHours())*salaryPerHour;
		}
		
	}

	public String getID() {
		return personnelID;
	}
}
